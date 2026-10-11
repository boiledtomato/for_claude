# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 16)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 76

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/moveittransfer-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に MOVEit Transfer を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/moveittransfer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MOVEit Transfer - Microsoft Entra の統合の間でシングル サインオンを構成する方法について説明します。

この記事では、MOVEit Transfer - Microsoft Entra と Microsoft Entra ID の統合を統合する方法について説明します。 MOVEit Transfer - Microsoft Entra 統合を Microsoft Entra ID と統合する場合、次のことができます。

- MOVEit Transfer - Microsoft Entra 統合にアクセスする Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して MOVEit Transfer - Microsoft Entra 統合に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MOVEit Transfer - Microsoft Entra 統合でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- MOVEit Transfer - Microsoft Entra 統合では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの MOVEit Transfer - Microsoft Entra 統合の追加

Microsoft Entra ID への MOVEit Transfer - Microsoft Entra の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MOVEit Transfer - Microsoft Entra 統合を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**MOVEit Transfer - Microsoft Entra 統合**」と入力します。
4. 結果のパネルから **[MOVEit Transfer - Microsoft Entra 統合]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MOVEit Transfer - Microsoft Entra 統合に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使って、Microsoft Entra SSO に対してMOVEit Transfer - Microsoft Entra 統合を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと MOVEit Transfer - Microsoft Entra 統合の関連ユーザーとの間にリンク関係を確立する必要があります。

MOVEit Transfer - Microsoft Entra 統合に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MOVEit Transfer - Microsoft Entra 統合の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MOVEit Transfer - Microsoft Entra 統合テスト ユーザーの作成** - Microsoft Entra のユーザーを表す B.Simon に対応するユーザーを、MOVEit Transfer - Microsoft Entra 統合内で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**MOVEit Transfer - Microsoft Entra integration**&gt;**シングルサインオン**に移動。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、**サービス プロバイダー メタデータ ファイル**がある場合は、次の手順に従います。

    A. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルを選択する]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**と **[応答 URL]** の値が、**[基本的な SAML 構成]** セクションに自動的に設定されます。

    d. **[サインオン URL]** テキスト ボックスに、URL として「`https://contoso.com`」と入力します。

    注意

    **サインオン URL** は、実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値を取得するには、[MOVEit Transfer - Microsoft Entra 統合クライアント サポート](https://community.ipswitch.com/s/support) チームに問い合わせてください。 **サービス プロバイダー メタデータ ファイル**は、後の記事の**「MOVEit Transfer - Microsoft Entra integration Single Sign-On の構成**」セクションで説明されている**サービス プロバイダー メタデータ URL** からダウンロードできます。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[MOVEit Transfer - Microsoft Entra 統合のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MOVEit Transfer - Microsoft Entra 統合の SSO の構成

1. MOVEit Transfer テナントに管理者としてサインオンします。
2. 左側のナビゲーション ウィンドウで、**[設定]** を選択します。

    [Image: アプリ側の [設定] セクション。]
3. [] の下にある &gt;] リンクを選択します。

    [Image: アプリ側の [セキュリティ ポリシー]。]
4. [メタデータ URL] リンクを選択して、メタデータ ドキュメントをダウンロードします。

    [Image: サービス プロバイダー メタデータ URL。]

    A. **EntityDescriptor** の **entityID** の値が、**[基本的な SAML 構成]** セクションの **[識別子]** と一致していることを確認します。

    b。 **AssertionConsumerService** の **Location** の URL が **[基本的な SAML 構成]** セクションの **[応答 URL]** と一致していることを確認します。
5. [ **ID プロバイダーの追加]** ボタンを選択して、新しいフェデレーション ID プロバイダーを追加します。

    [Image: ID プロバイダーの追加。]
6. [ **参照]...** を選択して、Azure portal からダウンロードしたメタデータ ファイルを選択し、[ **Add Identity Provider]\(ID プロバイダーの追加** \) を選択してダウンロードしたファイルをアップロードします。

    [Image: SAML ID プロバイダー。]
7. [**フェデレーション ID プロバイダーの設定の編集]...** ページで **[有効]** として [**はい**] を選択し、[保存] を選択**します**。

    [Image: フェデレーション ID プロバイダーの設定。]
8. **[Edit Federated Identity Provider User Settings](フェデレーション ID プロバイダー ユーザーの設定の編集)** ページで、次の操作を実行します。

    [Image: フェデレーション ID プロバイダーの設定の編集。]

    A. **[Login name (ログイン名)]** として **[SAML NameID]** を選択します。

    b。 **[Full name](フル ネーム)** として **[Other](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/その他)** を選び、**[Attribute name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/属性名)** ボックスに値「`http://schemas.microsoft.com/identity/claims/displayname`」を入力します。

    c. **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** として **[Other](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/その他)** を選び、**[Attribute name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/属性名)** ボックスに値「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`」を入力します。

    d. **[Auto-create account on signon (サインオン時にアカウントを自動作成する)]** で **[Yes (はい)]** を選択します。

    え [ **保存] ボタンを** 選択します。

#### MOVEit Transfer - Microsoft Entra 統合のテスト ユーザーの作成

このセクションの目的は、MOVEit Transfer - Microsoft Entra 統合で Britta Simon というユーザーを作成することです。 MOVEit Transfer - Microsoft Entra 統合では、Just-In-Time プロビジョニングがサポートされています。この設定は有効になっています。 このセクションにはアクション項目はありません。 ユーザーが存在しない場合は、MOVEit Transfer - Microsoft Entra 統合へのアクセスを試みたときに、新しいユーザーが自動的に作成されます。

注意

ユーザーを手動で作成する必要がある場合は、[MOVEit Transfer - Microsoft Entra 統合クライアント サポート チーム](https://community.ipswitch.com/s/support)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる MOVEit Transfer - Microsoft Entra 統合のサインオン URL にリダイレクトされます。
- MOVEit Transfer - Microsoft Entra 統合のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで MOVEit Transfer - Microsoft Entra 統合タイルを選択すると、SSO を設定した MOVEit Transfer - Microsoft Entra 統合に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/movement-by-project44-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Movement by project44 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/movement-by-project44-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Movement by project44 の間でシングル サインオンを構成する方法について説明します。

この記事では、Movement by project44 と Microsoft Entra ID を統合する方法について説明します。 Movement by project44 と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で project44 の Movement へのアクセスを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Movement by project44 に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- project44 での移動でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- project44 による移動では、 **SP** によって開始される SSO がサポートされます。
- project44 による移動では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから project44 の「Movement」を追加する

Microsoft Entra ID への Movement by project44 の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Movement by project44 を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Project44 による移動」**と入力します。
4. 結果パネルから **[Project44 による移動** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Project44 による Movement の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Movement by project44 に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Movement by project44 の関連ユーザーとの間にリンク関係を確立する必要があります。

Movement by project44 で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Movement by project44 SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **project44 テストユーザーによる Movement の作成** - Movement by project44 内で B.Simon に対応するものを用意し、それを Microsoft Entra のユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Movement by project44**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.okta.com/saml2/service-provider/<provider-ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://project44-americas.okta.com/sso/saml2/<IdP-ID>` |
    | `https://project44-europe.okta.com/sso/saml2/<IdP-ID>` |

    c. **[サインオン URL]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://movement.project44.com/login?idpId=<IdP-ID>` |
    | `https://movement.eu.project44.com/login?idpId=<IdP-ID>` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、project44 サポート チームから Movement](mailto:support@project44.com) にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. project44 アプリケーションによる移動では、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]

    注

    上記の既定の属性については、名前空間を手動で削除してください。
7. 上記に加えて、Movement by project44 アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. [ **Project44 による移動の設定** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Project44 SSO による Movement の構成

**Project44 側で Movement に**シングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、Microsoft Entra 管理センターからコピーした適切な URL を [project44 サポート チームによる Movement](mailto:support@project44.com) に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### project44 テストユーザーによるムーブメントの作成

このセクションでは、Britta Simon というユーザーを Project44 の Movement に作成します。 project44 による移動では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Movement by project44 に存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Movement by project44 のサインオン URL にリダイレクトされます。
- Project44 の [サインオン URL による移動] に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Movement by project44] タイルを選択すると、このオプションは Project44 での Movement のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/moveworks-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Moveworks を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/moveworks-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Moveworks の間でシングル サインオンを構成する方法について説明します。

この記事では、Moveworks と Microsoft Entra ID を統合する方法について説明します。 Moveworks と Microsoft Entra ID を統合すると、次のことができます。

- Moveworks にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Moveworks に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Moveworks でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Moveworksでは、**SPとIDPによるSSO**の両方をサポートしています。
- Moveworks では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Moveworks の追加

Microsoft Entra ID への Moveworks の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Moveworks を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Moveworks**」と入力します。
4. 結果パネルから **Moveworks** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Moveworks の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Moveworks に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Moveworks の関連ユーザーとの間にリンク関係を確立する必要があります。

Moveworks に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Moveworks SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Moveworks のテストユーザーを作成する** - Moveworks 内で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Moveworks**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://moveworks.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CustomerName>.moveworks.com/login/sso/saml` |
    | `https://<CustomerName>.am-ca-central.moveworks.com/login/sso/saml` |
    | `https://<CustomerName>.am-eu-central.moveworks.com/login/sso/saml` |
    | `https://<CustomerName>.am-ap-southeast.moveworks.com/login/sso/saml` |
    | `https://<CustomerName>.moveworksgov.com/login/sso/saml` |

    c. **リレー状態**で、次のパターンを使用して値を入力します。`<CustomerName>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CustomerName>.moveworks.com` |
    | `https://<CustomerName>.am-ca-central.moveworks.com` |
    | `https://<CustomerName>.am-eu-central.moveworks.com` |
    | `https://<CustomerName>.am-ap-southeast.moveworks.com` |
    | `https://<CustomerName>.moveworksgov.com` |

    注

    これらの値は実際の値ではありません。 実際の応答 URL、リレー状態、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Moveworks サポート チーム](mailto:support@moveworks.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **Moveworks のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Moveworks SSO の構成

**Moveworks** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、Microsoft Entra 管理センターからコピーした適切な URL を [Moveworks サポート チーム](mailto:support@moveworks.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。 詳細については、 [この](https://help.moveworks.com/docs/microsoft-manual-sso-configuration-guide-saml) リンクを参照してください。

#### Moveworks テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Moveworks に作成します。 Moveworks では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Moveworks にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Moveworks のサインオン URL にリダイレクトします。
- Moveworks のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Moveworks に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Moveworks] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Moveworks に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/moxiengage-tutorial"} -->
## Microsoft Entra ID で Moxi Engage for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/moxiengage-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Moxi Engage の間にシングル サインオンを構成する方法について説明します。

この記事では、Moxi Engage と Microsoft Entra ID を統合する方法について説明します。 Moxi Engage と Microsoft Entra ID を統合すると、次のことができます。

- Moxi Engage にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Moxi Engage に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Moxi Engage でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Moxi Engage では、**SP** initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Moxi Engage の追加

Microsoft Entra ID への Moxi Engage の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Moxi Engage を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Moxi Engage**」と入力します。
4. 結果パネルから **[Moxi Engage]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Moxi Engage 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Moxi Engage に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Moxi Engage の関連ユーザーとの間にリンク関係を確立する必要があります。

Moxi Engage に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Moxi Engage の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Moxi Engage のテストユーザーを作成します** - Moxi Engage で B.Simon に対応するユーザーを作成し、そのユーザーが Microsoft Entra 上の B.Simon にリンクされるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Moxi Engage**&gt;**シングルサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. [**基本的な SAML 構成**] セクションで、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://svc.<moxiworks-integration-domain>/service/v1/auth/inbound/saml/aad` という形式で URL を入力します。

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[Moxi Engage クライアント サポート チーム](mailto:support@moxiworks.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Moxi Engage のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Moxi Engage の SSO の構成

**Moxi Engage** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Moxi Engage サポート チーム](mailto:support@moxiworks.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Moxi Engage テスト ユーザーの作成

このセクションでは、Moxi Engage で Britta Simon というユーザーを作成します。 [Moxi Engage サポート チーム](mailto:support@moxiworks.com)と連携し、Moxi Engage プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Moxi Engage のサインオン URL にリダイレクトされます。
- Moxi Engage のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Moxi Engage] タイルを選択すると、このオプションは Moxi Engage のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/moxtra-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Moxtra を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/moxtra-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Moxtra の間にシングル サインオンを構成する方法について説明します。

この記事では、Moxtra と Microsoft Entra ID を統合する方法について説明します。 Moxtra を Microsoft Entra ID と統合すると、次のことができます。

- Moxtra にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Moxtra に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Moxtra でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Moxtra では、 **SP** Initiated SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Moxtra を追加する

Microsoft Entra ID への Moxtra の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Moxtra を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Moxtra**」と入力します。
4. 結果パネルから **Moxtra** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Moxtra 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Moxtra に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Moxtra の関連ユーザーとの間にリンク関係を確立する必要があります。

Moxtra に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Moxtra SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Moxtra テストユーザーを作成して、Microsoft Entra における B.Simon のユーザーとリンクされる Moxtra の対応ユーザーを持ちます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Moxtra**&gt;**シングルサインオンに**移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.moxtra.com/service/#login`
6. Moxtra アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。 [ **編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: Moxtra アプリケーションの画像を示すスクリーンショット。]
7. その他に、Moxtra アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [ユーザー属性] ダイアログの [ユーザー要求] セクションで、以下の手順を実行して、以下の表のように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | 名字 | User.surname |
    | idpid | &lt; Microsoft Entra 識別子 &gt; |

    注意

    **idpid** 属性の値は実際の値ではありません。 実際の値は、手順 8 の **「Moxtra のセットアップ** 」セクションから取得できます。

    1. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。
    2. [ **名前** ] ボックスに、その行に表示される属性名を入力します。
    3. **名前空間**は空白のままにします。
    4. [ソース] を **[属性**] として選択します。
    5. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。
    6. [ **OK] を選択する**
    7. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **Moxtra のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 設定を適切なURLにコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Moxtra の SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として Moxtra 企業サイトにサインオンします。
2. 左側のツール バーで、[ **管理コンソール] &gt; [SAML シングル サインオン**] を選択し、[ **新規**] を選択します。

    [Image: スクリーンショットは、新しい S A M L シングル サインオンを作成するオプションが表示された [S A M L シングル サインオン] ページを示しています。]
3. **[SAML**] ページで、次の手順を実行します。

    [Image: 説明されている値を入力できる SAML ページを示すスクリーンショット。]

    ある。 [ **名前** ] ボックスに、構成の名前 ( **SAML** など) を入力します。

    b。 **[IdP エンティティ ID**] ボックスに、**Microsoft Entra Identifier** の値を貼り付けます。

    c. [ **ログイン URL** ] ボックスに、 **ログイン URL** の値を貼り付けます。

    d. **AuthnContextClassRef** テキストボックスに、「**urn:oasis:names:tc:SAML:2.0:ac:classes:Password**」と入力します。

    え **[NameID 形式**] ボックスに、「**urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress**」と入力します。

    f. Azure portal からダウンロードした証明書をメモ帳で開き、内容をコピーして、[ **証明書** ] ボックスに貼り付けます。

    ジー SAML 電子メール ドメイン テキストボックスに、SAML 電子メール ドメインを入力します。

    注意

    ドメインを確認する手順を確認するには、以下の "**i**" を選択します。

    h. [ **更新] を**選択します。

#### Moxtra テスト ユーザーの作成

このセクションの目的は、Moxtra で B.Simon というユーザーを作成することです。

**Moxtra で B.simon というユーザーを作成するには、次の手順に従います。**

1. Moxtra 企業サイトに管理者としてサインオンします。
2. 左側のツール バーで、[ **管理コンソール] &gt; [ユーザー管理**] を選択し、[ **ユーザーの追加]** を選択します。

    [Image: [ユーザーの追加] が選択された [ユーザー管理] ページを示すスクリーンショット。]
3. [ **ユーザーの追加** ] ダイアログで、次の手順を実行します。

    ある。 **名** テキストボックスに「**B**」と入力します。

    b。 [ **姓** ] ボックスに「 **Simon**」と入力します。

    c. [ **電子メール** ] ボックスに、Azure portal と同じ B.simon のメール アドレスを入力します。

    d. **部署** テキストボックスに「**Dev**」と入力します。

    え [ **部署** ] ボックスに「 **IT**」と入力します。

    f. [ **管理者**] を選択します。

    ジー **追加**を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Moxtra サインオン URL にリダイレクトされます。
- Moxtra のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Moxtra] タイルを選択すると、このオプションは Moxtra のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mozy-enterprise-tutorial"} -->
## Mozy Enterprise のシングルサインオンを Microsoft Entra ID で構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mozy-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mozy Enterprise 間にシングル サインオンを構成する方法について説明します。

この記事では、Mozy Enterprise と Microsoft Entra ID を統合する方法について説明します。 Mozy Enterprise と Microsoft Entra ID を統合すると、次のような利点があります。

- Mozy Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Mozy Enterprise に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mozy Enterprise でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Mozy Enterprise では、 **SP** Initiated SSO がサポートされます

### ギャラリーからの Mozy Enterprise の追加

Microsoft Entra ID への Mozy Enterprise の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Mozy Enterprise を追加する必要があります。

**ギャラリーから Mozy Enterprise を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**にアクセスします。
3. 検索ボックスに「 **Mozy Enterprise」**と入力し、結果パネルで **Mozy Enterprise** を選択し、[ **追加** ] ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧にある Mozy Enterprise]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Mozy Enterprise で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Mozy Enterprise 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Mozy Enterprise で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Mozy Enterprise シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Mozy Enterprise のテストユーザーを作成する** - Mozy Enterprise 内で Britta Simon の対応ユーザーを設定し、Microsoft Entra のユーザー表現に関連付けます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Mozy Enterprise で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Mozy Enterprise** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantname>.Mozyenterprise.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、 [Mozy Enterprise クライアント サポート チーム](https://www.safenames.net/about-us/contact-us) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Mozy Enterprise のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    ある。 ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Mozy Enterprise でシングル サインオンを構成する

1. 別の Web ブラウザーのウィンドウで、Mozy Enterprise の企業サイトに管理者としてログインします。
2. [ **構成** ] セクションで、[ **認証ポリシー**] を選択します。

    [Image: [構成] で選択されている認証ポリシーを示すスクリーンショット。]
3. [ **認証ポリシー** ] セクションで、次の手順を実行します。

    [Image: スクリーンショットは、説明されている値を入力できる [認証ポリシー] セクションを示しています。]

    ある。 **プロバイダー**として**ディレクトリ サービス**を選択します。

    b。 [ **LDAP プッシュの使用**] を選択します。

    c. [ **SAML 認証** ] タブを選択します。

    d. **[認証 URL**] ボックスにログイン **URL を**貼り付けます。

    え **[SAML エンドポイント**] ボックスに **Microsoft Entra 識別子**を貼り付けます。

    f. ダウンロードした base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーし、証明書全体を **SAML 証明書** ボックスに貼り付けます。

    ジー **[Enable SSO for Admins to log in with their network credentials]\(管理者の SSO を有効にする\) を選択して、自分のネットワーク資格情報でログインします**。

    h. [ **変更の保存] を選択します**。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Mozy Enterprise テスト ユーザーの作成

Microsoft Entra ユーザーが Mozy Enterprise にログインできるようにするには、ユーザーを Mozy Enterprise にプロビジョニングする必要があります。 Mozy Enterprise の場合、プロビジョニングは手動で行います。

注

他の Mozy Enterprise ユーザー アカウント作成ツールや、Mozy Enterprise から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. **Mozy Enterprise** テナントにログインします。
2. [ **ユーザー**] を選択し、[ **新しいユーザーの追加]** を選択します。

    [Image: ユーザー]

    注

    [**新しいユーザーの追加]** オプションは、[**認証ポリシー**] で **Mozy** がプロバイダーとして選択されている場合にのみ表示されます。 SAML 認証が構成されている場合、ユーザーはシングル サインオンでの初回ログイン時に自動的に追加されます。
3. 新しいユーザーのダイアログで、次の手順に従います。

    [Image: ユーザー追加]

    ある。 [ **グループの選択** ] ボックスの一覧からグループを選択します。

    b。 [ **ユーザーの種類** ] ボックスの一覧で、種類を選択します。

    c. [ **ユーザー名** ] ボックスに、Microsoft Entra ユーザーの名前を入力します。

    d. [ **電子メール** ] ボックスに、Microsoft Entra ユーザーのメール アドレスを入力します。

    え [ **ユーザー指示メールの送信]** を選択します。

    f. [ **ユーザーの追加]** を選択します。

    注

    ユーザーの作成後、アカウントがアクティブになる前にアカウントを確認するためのリンクを含む電子メールが Microsoft Entra ユーザーに送信されます。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Mozy Enterprise] タイルを選択すると、SSO を設定した Mozy Enterprise に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ms-azure-sso-access-for-ethidex-compliance-office-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に MS Azure SSO Access for Ethidex Compliance Office™ を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ms-azure-sso-access-for-ethidex-compliance-office-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MS Azure SSO Access for Ethidex Compliance Office™ の間でシングル サインオンを構成する方法について学習します。

この記事では、MS Azure SSO Access for Ethidex Compliance Office™ と Microsoft Entra ID を統合する方法について説明します。 MS Azure SSO Access for Ethidex Compliance Office™ と Microsoft Entra ID を統合すると、次のことができます。

- MS Azure SSO Access for Ethidex Compliance Office™ にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して MS Azure SSO Access for Ethidex Compliance Office™ に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MS Azure SSO Access for Ethidex Compliance Office™ でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MS Azure SSO Access for Ethidex Compliance Office™ では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの MS Azure SSO Access for Ethidex Compliance Office™ の追加

Microsoft Entra ID への MS Azure SSO Access for Ethidex Compliance Office™ の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから MS Azure SSO Access for Ethidex Compliance Office™ を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**MS Azure SSO Access for Ethidex Compliance Office™**」と入力します。
4. 結果のパネルから **MS Azure SSO Access for Ethidex Compliance Office™** を選択し、そのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MS Azure SSO Access for Ethidex Compliance Office™ 用に Microsoft Entra ID を構成してテストする

**B.Simon** というテスト ユーザーを使用して、MS Azure SSO Access for Ethidex Compliance Office™ に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと MS Azure SSO Access for Ethidex Compliance Office™ の関連ユーザーとの間にリンク関係を確立する必要があります。

MS Azure SSO Access for Ethidex Compliance Office™ に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MS Azure SSO Access for Ethidex Compliance Office の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Ethidex Compliance Office 上のテストユーザー用に MS Azure SSO Access を作成** - Microsoft Entra 内のユーザーにリンクされた、Ethidex Compliance Office™ における B.Simon の対応ユーザーを MS Azure SSO Access 内に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**MS Azure SSO Access for Ethidex Compliance Office™**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    あ。 **[識別子]** ボックスに、`com.ethidex.prod.<CLIENTID>` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://www.ethidex.com/saml2/sp/acs/<CLIENTID>` のパターンを使用して URL を入力します

    注意

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 この値を取得するには、[MS Azure SSO Access for Ethidex Compliance Office™ サポート チーム](mailto:support@ethidex.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. MS Azure SSO Access for Ethidex Compliance Office™ アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 MS Azure SSO Access for Ethidex Compliance Office™ アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[MS Azure SSO Access for Ethidex Compliance Office™ の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MS Azure SSO Access for Ethidex Compliance Office の SSO の構成

**MS Azure SSO Access for Ethidex Compliance Office™** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [MS Azure SSO Access for Ethidex Compliance Office™ サポート チーム](mailto:support@ethidex.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### MS Azure SSO Access for Ethidex Compliance Office のテスト ユーザーの作成

このセクションでは、MS Azure SSO Access for Ethidex Compliance Office™ で B.Simon というユーザーを作成します。 [MS Azure SSO Access for Ethidex Compliance Office™ サポート チーム](mailto:support@ethidex.com)と連携して、MS Azure SSO Access for Ethidex Compliance Office™ プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Ethidex Compliance Office™ に自動的にサインインします
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Ethidex Compliance Office]™ タイルを選択すると、SSO を設定した Ethidex Compliance Office™ に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ms-confluence-jira-plugin-adminguide"} -->
## Microsoft Entra ID でシングル サインオン用に Atlassian Jira/Confluence を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ms-confluence-jira-plugin-adminguide
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID で Atlassian Jira と Confluence を使用するための管理者ガイド。.

### 概要

Microsoft Entra シングル サインオン (SSO) プラグインを使用すると、Microsoft Entra のお客様は、Atlassian Jira および Confluence Server ベースの製品へのサインインに職場または学校アカウントを使用できます。 SAML 2.0 ベースの SSO を実装します。

### 動作方法

ユーザーが Atlassian Jira または Confluence アプリケーションにサインインする場合、サインイン ページに **[Microsoft Entra ID を使用してログイン** ] ボタンが表示されます。 選択すると、Microsoft Entra 組織のサインイン ページ (職場または学校アカウント) を使用してサインインする必要があります。

ユーザーが認証されると、ユーザーはアプリケーションにサインインできるようになります。 職場または学校アカウントの ID とパスワードで既に認証されている場合は、アプリケーションに直接サインインします。

サインインは、Jira と Confluence 全体で機能します。 ユーザーが Jira アプリケーションにサインインしていて、Confluence が同じブラウザー ウィンドウで開かれている場合、他のアプリの資格情報を指定する必要はありません。

ユーザーは、職場または学校アカウントでマイ アプリを通じて Atlassian 製品にアクセスすることもできます。 資格情報を求められずにサインインする必要があります。

注

ユーザー プロビジョニングはプラグインを介して行われません。

### 聴衆

Jira と Confluence の管理者は、このプラグインを使用して、Microsoft Entra ID を使用して SSO を有効にすることができます。

### 前提条件

- Jira インスタンスと Confluence インスタンスは HTTPS が有効になっています。
- ユーザーは Jira または Confluence で既に作成されています。
- ユーザーには、Jira または Confluence でロールが割り当てられます。
- 管理者は、プラグインを構成するために必要な情報にアクセスできます。
- Jira または Confluence は、社内ネットワークの外部でも使用できます。
- このプラグインは、Jira と Confluence のオンプレミス バージョンでのみ機能します。

### [前提条件]

プラグインをインストールする前に、次の情報に注意してください。

- Jira と Confluence は、Windows 64 ビット バージョンにインストールされます。
- Jira バージョンと Confluence バージョンでは、HTTPS が有効になっています。
- Jira と Confluence はインターネットで利用できます。
- Jira と Confluence の管理者資格情報が用意されています。
- Microsoft Entra ID の管理者資格情報が用意されています。
- WebSudo は Jira と Confluence で無効になっています。

### サポートされている Jira と Confluence のバージョン

このプラグインでは、次のバージョンの Jira と Confluence がサポートされています。

- Jira Core とソフトウェア: 6.0 から 9.10.0
- Jira サービス デスク: 3.0.0 から 4.22.1。
- JIRA は 5.2 もサポートします。 詳細については、 [JIRA 5.2 の Microsoft Entra シングル サインオンを](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jira52microsoft-tutorial)選択してください。
- Confluence: 5.0 から 5.10。
- Confluence: 6.0.1 から 6.15.9。
- Confluence: 7.0.1 から 8.5.1。

### 取り付け

プラグインをインストールするには、次の手順に従います。

1. Jira または Confluence インスタンスに管理者としてサインインします。
2. Jira/Confluence 管理コンソールに移動し、[アドオン] を選択 **します**。
3. Microsoft ダウンロード センターから、 [Microsoft SAML SSO Plugin for Jira](https://www.microsoft.com/download/details.aspx?id=56506)/ [Microsoft SAML SSO Plugin for Confluence を](https://www.microsoft.com/download/details.aspx?id=56503)ダウンロードします。

    適切なバージョンのプラグインが検索結果に表示されます。
4. プラグインを選択すると、ユニバーサル プラグイン マネージャー (UPM) によってインストールされます。

プラグインがインストールされると、[アドオンの管理] の [ **ユーザーがインストールしたアドオン** ] セクション **に**表示されます。

### プラグインの構成

プラグインの使用を開始する前に、プラグインを構成する必要があります。 プラグインを選択し、[ **構成** ] ボタンを選択して、構成の詳細を指定します。

次の図は、Jira と Confluence の両方の構成画面を示しています。

[Image: プラグイン構成画面]

- **メタデータ URL**: Microsoft Entra ID からフェデレーション メタデータを取得する URL。
- **識別子**: Microsoft Entra ID が要求のソースを検証するために使用する URL。 これは、Microsoft Entra ID の **Identifier** 要素にマップされます。 プラグインは、この URL を https://*&lt;domain:port&gt;*/ として自動的に派生させます。
- **応答 URL**: SAML サインインを開始する ID プロバイダー (IdP) の応答 URL。 Microsoft Entra ID の **Reply URL** 要素にマップされます。 プラグインは、この URL を https://*&lt;domain:port&gt;*/plugins/サーブレット/saml/auth として自動的に派生させます。
- **サインオン URL**: SAML サインインを開始する IdP のサインオン URL。 Microsoft Entra ID の **Sign On** 要素にマップされます。 プラグインは、この URL を https://*&lt;domain:port&gt;*/plugins/サーブレット/saml/auth として自動的に派生させます。
- **IdP エンティティ ID**: IdP が使用するエンティティ ID。 このボックスは、メタデータ URL が解決されるときに設定されます。
- **ログイン URL**: IdP からのサインイン URL。 メタデータ URL が解決されると、このボックスは Microsoft Entra ID から入力されます。
- **ログアウト URL**: IdP からのログアウト URL。 メタデータ URL が解決されると、このボックスは Microsoft Entra ID から入力されます。
- **X.509 証明書**: IdP の X.509 証明書。 メタデータ URL が解決されると、このボックスは Microsoft Entra ID から入力されます。
- **[ログイン ボタン名]:** 組織でユーザーがサインイン ページに表示するサインイン ボタンの名前。
- **SAML ユーザー ID の場所**: SAML 応答で Jira または Confluence ユーザー ID が必要な場所。 **NameID** またはカスタム属性名を使用できます。
- **属性名**: ユーザー ID が必要な属性の名前。
- **ホーム領域検出を有効にする**: 会社が Active Directory フェデレーション サービス (AD FS) ベースのサインインを使用しているかどうかを選択します。
- **ドメイン名**: サインインが AD FS ベースの場合のドメイン名。
- **[シングル サインアウトを有効にする**]: ユーザーが Jira または Confluence からサインアウトするときに Microsoft Entra ID からサインアウトするかどうかを選択します。
- Microsoft Entra 資格情報でのみサインインしたい場合は、**Azure ログインを強制する** チェックボックスを有効にします。
- アプリケーション プロキシのセットアップでオンプレミス atlassian アプリケーションを構成した場合、**[アプリケーション プロキシの使用]** チェックボックスをオンにします。

    - アプリ プロキシのセットアップについては、[Microsoft Entra アプリケーション プロキシのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)の手順に従ってください。

### リリース ノート

JIRA:

| プラグインのバージョン | リリース ノート | サポートされている JIRA バージョン |
| --- | --- | --- |
| 1.0.20 | バグ修正: | Jira Core とソフトウェア: |
|  | JIRA SAML SSO アドオンは、モバイル ブラウザーから正しくない URL にリダイレクトします。 | 7.0.0 から 9.10.0 |
|  | JIRA プラグインを有効にした後のマーク ログ セクション。 |  |
|  | ユーザーが SSO を使用してサインインしても、ユーザーの最後のログイン日は更新されません。 |  |
|  |  |  |
| 1.0.19 | 新機能: | Jira Core とソフトウェア: |
|  | アプリケーション プロキシのサポート - アプリケーション プロキシ モードをポイントする必要性に応じて応答 URL を編集可能にするように、アプリケーション プロキシ モードを切り替えるプラグインの構成画面のチェック ボックスをオンにして、プロキシ サーバーの URL をポイントする必要性に従って応答 URL を編集可能にします | 6.0 から 9.3.1 |
|  |  | Jira サービス デスク: 3.0.0 から 4.22.1 |
|  |  |  |
| 1.0.18 | バグ修正: | Jira Core とソフトウェア: |
|  | Jira Microsoft Entra SSO プラグインの [構成] ボタンを選択した場合の 405 エラーのバグ修正。 | 6.0 から 9.1.0。 |
|  | JIRA サーバーが "プロジェクト設定ページ" を正しくレンダリングしていません。 | Jira サービス デスク: 3.0.0 から 4.22.1。 |
|  | JIRA は Microsoft Entra Login を強制していません。 追加のボタン選択が必要でした。 |  |
|  | これで、このバージョンのセキュリティ修正プログラムが解決されました。 これにより、ユーザーの偽装の脆弱性から保護されます。 |  |
|  | JIRA サービス デスクのログアウトの問題が解決されました。 |  |

合流：

| プラグインのバージョン | リリース ノート | サポートされている JIRA バージョン |
| --- | --- | --- |
| 6.3.9 | バグ修正: | Confluence Server: 7.20.3 から 8.5.1 |
|  | システム エラー: SSO プラグインでメタデータ リンクを構成できません。 |  |
|  |  |  |
| 6.3.8 | 新機能: | Confluence Server: 5.0 から 7.20.1 |
|  | アプリケーション プロキシのサポート - プラグインの構成画面のチェック ボックスをオンにして、プロキシ サーバーの URL をポイントする必要に応じて応答 URL を編集可能にするように、アプリケーション プロキシ モードを切り替えます。 |  |
|  |  |  |
| 6.3.7 | バグ修正: | Confluence Server: 5.0 から 7.19.0 |
|  | "ログインの強制" 機能を使用すると、IT 管理者は Microsoft Entra 認証をユーザーに強制できます。 これにより、ユーザーはユーザー名とパスワード ボックスを表示せず、SSO の使用を強制されます。 |  |
|  | プラグインから「ログインを強制する」を構成可能 |  |
|  | Microsoft Entra ID がユーザーをフェデレーション サーバーに直接リダイレクトできるように、ドメイン文字列を Microsoft Entra ID に渡すことができます。 |  |

### トラブルシューティング

- **複数の証明書エラーが発生しています**。Microsoft Entra ID にサインインし、アプリに対して使用できる複数の証明書を削除します。 証明書が 1 つだけ存在することを確認します。
- **Microsoft Entra ID で証明書の有効期限が切れようと**しています。アドオンが証明書の自動ロールオーバーを処理します。 証明書の有効期限が迫ったら、新しい証明書をアクティブとしてマークし、未使用の証明書を削除する必要があります。 このシナリオでユーザーが Jira にサインインしようとすると、プラグインは新しい証明書をフェッチして保存します。
- **WebSudo を無効にする (セキュリティで保護された管理者セッションを無効にする)**

    - Jira の場合、セキュリティで保護された管理者セッション (つまり、管理機能にアクセスする前のパスワード確認) が既定で有効になります。 Jira インスタンスでこの機能を削除する場合は、jira-config.properties ファイルで次の行を指定します。 `jira.websudo.is.disabled = true`
    - Confluence の場合は、 [Confluence サポート サイト](https://confluence.atlassian.com/doc/configuring-secure-administrator-sessions-218269595.html)の手順に従います。
- **メタデータ URL によって設定されるはずのフィールドは設定されません**。

    - URL が正しいかどうかを確認します。 正しいテナントとアプリ ID がマップされているかどうかを確認します。
    - ブラウザーで URL を入力し、フェデレーション メタデータ XML を受け取るかどうかを確認します。
- **内部サーバー エラーが発生**しました。インストールのログ ディレクトリのログを確認します。 ユーザーが Microsoft Entra SSO を使用してサインインしようとしたときにエラーが発生した場合は、サポート チームとログを共有できます。
- **ユーザーがサインインしようとすると、"ユーザー ID が見つかりません" というエラー**が発生します。Jira または Confluence でユーザー ID を作成します。
- **Microsoft Entra ID に "アプリが見つかりません" というエラーが**表示される: 適切な URL が Microsoft Entra ID のアプリにマップされているかどうかを確認します。
- **サポートが必要**です。 [Microsoft Entra SSO 統合チーム](mailto:SaaSApplicationIntegrations@service.microsoft.com)にお問い合わせください。 チームは 24 ~ 48 営業時間で応答します。

    また、Azure portal チャネルを介して Microsoft とのサポート チケットを発行することもできます。

### プラグインに関する FAQ

このプラグインに関するクエリがある場合は、以下の FAQ を参照してください。

#### プラグインは何をしますか?

このプラグインは、Atlassian Jira (Jira Core、Jira Software、Jira Service Desk など) と Confluence オンプレミス ソフトウェアのシングル サインオン (SSO) 機能を提供します。 このプラグインは、Id プロバイダー (IdP) として Microsoft Entra ID と連携します。

#### プラグインはどの Atlassian 製品で動作しますか?

このプラグインは、オンプレミス バージョンの Jira と Confluence で動作します。

#### プラグインはクラウド バージョンで動作しますか?

いいえ。 このプラグインは、Jira と Confluence のオンプレミス バージョンのみをサポートします。

#### プラグインはどのバージョンの Jira と Confluence をサポートしていますか?

プラグインは、次のバージョンをサポートしています。

- Jira Core とソフトウェア: 6.0 から 9.10.0
- Jira サービス デスク: 3.0.0 から 4.22.1。
- JIRA は 5.2 もサポートします。 詳細については、 [JIRA 5.2 の Microsoft Entra シングル サインオンを](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jira52microsoft-tutorial)選択してください。
- Confluence: 5.0 から 5.10。
- Confluence: 6.0.1 から 6.15.9。
- Confluence: 7.0.1 から 8.5.1。

#### プラグインは無料ですか、それとも有料ですか?

無料のアドオンです。

#### プラグインを展開した後、Jira または Confluence を再起動する必要がありますか?

再起動は必要ありません。 すぐにプラグインの使用を開始できます。

#### プラグインのサポートを受ける方法

このプラグインに必要なサポートについては、 [Microsoft Entra SSO 統合チーム](mailto:SaaSApplicationIntegrations@service.microsoft.com) にお問い合わせください。 チームは 24 ~ 48 営業時間で応答します。

また、Azure portal チャネルを介して Microsoft とのサポート チケットを発行することもできます。

#### プラグインは、Jira と Confluence の Mac または Ubuntu インストールで動作しますか?

このプラグインは、Jira と Confluence の 64 ビット Windows Server インストールでのみテストされています。

#### このプラグインは Microsoft Entra ID 以外の IdP で動作しますか?

いいえ。 Microsoft Entra ID でのみ機能します。

#### プラグインはどのバージョンの SAML で動作しますか?

SAML 2.0 で動作します。

#### プラグインはユーザー プロビジョニングを行いますか?

いいえ。 このプラグインでは、SAML 2.0 ベースの SSO のみが提供されます。 SSO サインインの前に、アプリケーションでユーザーをプロビジョニングする必要があります。

#### プラグインは Jira と Confluence のクラスター バージョンをサポートしていますか?

いいえ。 このプラグインは、オンプレミス バージョンの Jira と Confluence で動作します。

#### このプラグインは、Jira と Confluence の HTTP バージョンで動作しますか?

いいえ。 このプラグインは、HTTPS 対応のインストールでのみ機能します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mural-identity-provisioning-tutorial"} -->
## MICROSOFT Entra ID を使用して自動ユーザー プロビジョニング用に MURAL ID を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mural-identity-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から MURAL Identity に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために、MURAL ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[MURAL Identity](https://www.mural.co/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- MURAL Identity でユーザーを作成する
- アクセスが不要になった場合は、MURAL ID のユーザーを削除します。
- Microsoft Entra ID と MURAL Identity の間でユーザー属性の同期を維持する
- MURAL Identity でグループとメンバーシップをプロビジョニングする。
- MURAL Identity への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mural-identity-tutorial) (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- SCIM プロビジョニングは、MURAL の Enterprise プランでのみ使用できます。 SCIM プロビジョニングを構成する前に、MURAL お客様サポート チームのメンバーに連絡して、この機能を有効にしてください。
- 自動プロビジョニングを構成する前に、SAML ベースの SSO を正しく設定する必要があります。 Microsoft Entra ID を使用して MURAL の SSO を設定する方法に関する手順については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mural-identity-tutorial)を参照してください。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と MURAL Identity の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように MURAL Identity を構成する

MURAL Company ダッシュボードの API キー ページから SCIM URL と一意の API トークンを取得するための[手順](https://developers.mural.co/enterprise/docs/set-up-the-scim-api)に従ってください。 このキーは、**手順 5** の [シークレット トークン] フィールドで使用します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから MURAL Identity を追加する

MURAL Identity へのプロビジョニングの管理を開始するために、Microsoft Entra アプリケーション ギャラリーから MURAL Identity を追加します。 以前に MURAL Identity を SSO 用に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: MURAL Identity への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループ割り当てに基づいて、MURAL Identity でユーザーまたはグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で MURAL Identity 用に自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で、**[MURAL Identity]** を選択します。

    [Image: アプリケーションの一覧内の MURAL Identity のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **テナント URL** フィールドに、自分の MURAL アイデンティティ テナント URL とシークレット トークンを入力します。 Microsoft Entra ID が MURAL ID に接続できることを確認するには、[ **テスト接続** ] を選択します。 接続に失敗した場合は、必要な管理者アクセス許可が自分の MURAL ID アカウントにあることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から MURAL Identity に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作のために MURAL Identity でユーザー アカウントを照合するために使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が、MURAL Identity API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | MURAL Identity に必要です |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | externalId | 糸 |  |  |
12. **[属性マッピング]** セクションで、Microsoft Entra ID から MURAL Identity に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作を行う MURAL Identity のグループを照合するために使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | MURAL Identity に必要です |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
    | externalId | 糸 |  |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### トラブルシューティングのヒント

- ユーザーをプロビジョニングするときは、MURAL では名前フィールド (つまり givenName または familyName) の番号はサポートされません。
- GET エンドポイントで **userName** をフィルター処理する場合は、電子メール アドレスがすべて小文字であることを確認してください。それ以外の場合は、空の結果が返されます。 これは、アカウントをプロビジョニングするときに電子メール アドレスが小文字に変換されるためです。
- エンド ユーザーのプロビジョニングを解除すると (アクティブな属性を false に設定)、ユーザーは論理的に削除され、すべてのワークスペースにアクセスできなくなります。 同じプロビジョニング解除されたエンド ユーザーが後で再度アクティブ化された場合 (アクティブな属性を true に設定)、ユーザーは以前に属していたワークスペースにアクセスできません。 エンドユーザーには、"このワークスペースから非アクティブ化されました" というエラー メッセージと共に、ワークスペース管理者が承認する必要のある再アクティブ化を要求するためのオプションが表示されます。
- ほかに何か問題がある場合は、[MURAL Identity サポート チーム](mailto:support@mural.co)にお問い合わせください。

### 更新履歴

2023 年 6 月 22 日 - **グループ プロビジョニング**のサポートを追加しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mural-identity-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Mural Identity を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mural-identity-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mural Identity の間でシングル サインオンを構成する方法について説明します。

この記事では、Mural Identity と Microsoft Entra ID を統合する方法について説明します。 Mural Identity と Microsoft Entra ID を統合すると、次のことができます。

- Mural Identity にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Mural Identity に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mural Identity でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Mural Identity では、**SP および IDP による SSO** がサポートされます。
- Mural Identity では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Mural Identity では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mural-identity-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Mural Identity の追加

Microsoft Entra ID への Mural Identity の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Mural Identity を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**、&gt;、**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Mural Identity」と**入力します。
4. 結果パネルから **[Mural Identity] を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mural Identity 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Mural Identity に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Mural Identity の関連ユーザーとの間にリンク関係を確立する必要があります。

Mural Identityy での Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mural Identity SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mural Identity テストユーザーを作成** - Microsoft Entra の B.Simon にリンクされた Mural Identity 内の B.Simon の対となるユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Mural Identity**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. Mural Identity アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Mural Identity アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザー.ユーザープリンシパルネーム |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Mural Identity のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mural Identity の SSO の構成

1. 管理者として、Mural ID Web サイトにログインします。
2. ダッシュボードの左下隅にある **自分の名前** を選択し、オプションの一覧から **[会社のダッシュボード** ] を選択します。
3. 左側のサイドバーで **SSO** を選択し、次の手順を実行します。

    [Image: MURAL の構成を示すスクリーンショット。]

ある。 MURAL の **メタデータをダウンロードします**。

b。 [ **サインイン URL** ] ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。

c. **サインイン証明書で**、ダウンロードした**証明書 (PEM)** をアップロードします。

d. 要求バインドの種類として **HTTP-POST** を選択し、サインイン アルゴリズムの種類として **SHA256** を選択します。

え [ **要求マッピング** ] セクションで、次のフィールドに入力します。

- メール アドレス: `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`
- 名: `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`
- 姓: `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname`

f. [ **シングル サインオンのテスト** ] を選択して構成をテストし **、保存します** 。

注

MURAL で SSO を構成する方法の詳細については、 [この](https://support.mural.co/s/article/configure-sso-with-mural-and-azure-ad) サポート ページに従ってください。

#### Mural Identity のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Mural Identity に作成します。 Mural Identity では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Mural Identity にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Mural Identity のサインオン URL にリダイレクトされます。
- Mural Identity のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Mural Identity に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Mural Identity] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Mural Identity に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。

### 変更ログ

- 03/21/2022 - アプリケーション名が更新されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/muzeek-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Muzeek を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/muzeek-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-19
- Summary: Microsoft Entra と Muzeek の間でシングル サインオンを構成する方法について説明します。

この記事では、Muzeek と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Muzeek を統合すると、次のことができます。

Microsoft Entra ID を使用して、Muzeek にアクセスできるユーザーを制御します。 ユーザーが自分の Microsoft Entra アカウントを使用して Muzeek に自動的にサインインできるように設定できます。 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Muzeek でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから Muzeek を追加する

Microsoft Entra ID への Muzeek の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Muzeek を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Muzeek**」と入力します。
4. 結果のパネルで **[Muzeek]** を選択して、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Muzeek**&gt;**シングルサインオン**に移動します。
3. 次のセクションで以下の手順を実行します。

    ある。 **[アプリケーションに移動]**を選択します。

    [Image: ID 構成を示すスクリーンショット。]

    b。 **アプリケーション (クライアント) ID** と**ディレクトリ (テナント) ID** をコピーして、後で Muzeek 側の構成で使用します。

    [Image: アプリケーション クライアント値のスクリーンショット。]
4. 左側のメニューの **[認証]** タブに移動し、次の手順を実行します。

    ある。 **アクセス トークンの** と **ID トークンを有効にする**

    [Image: アクセス トークンを示すスクリーンショット。]

    b。 **[保存] を選択します**。

    注

    **リダイレクト URI** の値は自動的に設定されるため、ここでは手動で構成する必要はありません。
5. 左側のメニューの **[証明書とシークレット]** に移動し、次の手順を実行します。

    1. **[クライアント シークレット]** タブに移動し、**[+ 新しいクライアント シークレット]** を選択します。
    2. テキストボックスに有効な **[説明]** を入力し、要件に応じてドロップダウンから **[有効期限]** 日数を選択し **[追加]** を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、**[値]** が生成されます。 この値をコピーして、後で Muzeek 側の構成で使用します。

        [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Muzeek SSO の構成

OIDC フェデレーションのセットアップを完了するための構成手順を次に示します。

1. Muzeek サイトに管理者としてサインインします。
2. ページの下部にある **[設定]** アイコンを選択し、次の手順を実行します。

    [Image: Muzeek の構成を示すスクリーンショット。]

    ある。 **[統合]** タブに移動します。

    b。 **"ENTRA ドメイン"** フィールドで、`https://login.microsoftonline.com/<Tenant_ID>/oauth2/v2.0/authorize` のパターンを使用してドメイン URL の値を入力します。

    注

    ドメイン URL の値は実際の値ではありません。 **Tenant\_ID** の値を、Entra 側からコピーした実際の **ディレクトリ (テナント) ID** に置き換えます。

    c. **"ENTRA クライアント ID"** フィールドに、Entra ページからコピーした**アプリケーション ID** の値を貼り付けます。

    d. **"ENTRA クライアント シークレット"** フィールドに Entra 側の **[証明書とシークレット]** セクションからコピーした値を貼り付けます。

    え [ **変更の保存] を選択します**。

    f. 保存すると、Muzeek によって**ホーム ページの URL** が設定されます。これは、後で「MyApps 経由で SSO に接続する」セクションで使用できます。 [Image: ホーム ページの詳細を示すスクリーンショット。]

### MyApps 経由で SSO に接続する

Microsoft Entra 管理センターで自分の MyApps アカウントを Muzeek に接続するには、次の手順に従います。

1. **アプリ登録**&gt; \*\*Muzeek **ブランディングおよびプロパティ** に移動します。 [Image: Muzeek のアプリの登録を示すスクリーンショット。]
2. Muzeek ポータルからコピーしたホーム ページ URL を、Microsoft Entra 管理センターの **"ホーム ページ URL"** フィールドに貼り付けます。
3. **[保存] を**選択し、変更がシステムに反映されるまで 10 ~ 15 分待ちます。

完了すると、MyApps にログインした状態で自分の Muzeek アカウントに正常に移動できるようになり、テナントに追加したユーザーも同様に移動できるようになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mx3-diagnostics-connector-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に MX3 Diagnostics Connector を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mx3-diagnostics-connector-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra ID から、MX3 Diagnostics Connector のユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に行う方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために MX3 Diagnostics Connector と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[MX3 Diagnostics Connector](https://www.mx3diagnostics.com/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- MX3 Diagnostics Connector でユーザーを作成する。
- アクセスが不要になったら、MX3 Diagnostics Connector のユーザーを削除します。
- Microsoft Entra ID と MX3 Diagnostics Connector の間でユーザー属性の同期を維持する。
- MX3 Diagnostics Connector でグループおよびメンバーシップを設定する。
- MX3 Diagnostics Connector へのシングル サインオン。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 組織機能が有効な MX3 アカウント。
- SSO が有効な MX3 ポータルのアカウント。

### 手順 1: プロビジョニングの展開を計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と MX3 Diagnostics Connector の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように MX3 Diagnostics Connector を構成する

1. MX3 アカウントで組織機能が有効になっていない場合は、 `https://www.mx3diagnostics.com/files/files/MX3_PortalGuide_0321.pdf`のドキュメントで説明されているように、組織の機能を申請します。このドキュメントにアクセスできるようにするには、MX3 アカウントにサインインしてください。
2. MX3 アカウントでシングル サインオン機能が有効になっていない場合は、このドキュメントの説明に従って Microsoft Entra SSO を設定します。
3. [MX3 ポータル](https://portal.mx3.app)にログインします。 [設定] を選択して [SSO 設定] ページに移動し、[ **シングル サインオン**] を選択します。

    [Image: MX3 Diagnostics Connector の [Single sign-on](シングル サインオン) 設定のスクリーンショット。]
4. 下にスクロールして、トークンを表示します。 トークンをコピーして保存します。 **手順 5**. で必要になります。

    [Image: MX3 Diagnostics Connector の Azure AD のシークレット トークンのスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから MX3 Diagnostics Connector を追加する

Microsoft Entra アプリケーション ギャラリーから MX3 Diagnostics Connector を追加して、MX3 Diagnostics Connector へのプロビジョニングの管理を開始します。 SSO のために MX3 Diagnostics Connector を以前に設定している場合は、その同じアプリケーションを使うことができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: MX3 Diagnostics Connector への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、MX3 Diagnostics Connector でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で MX3 Diagnostics Connector の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードが表示されているスクリーンショット。]
3. アプリケーション一覧で **[MX3 Diagnostics Connector]** を選びます。

    [Image: アプリケーション一覧の [MX3 Diagnostics Connector] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのとその場所のスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、MX3 Diagnostics Connector のテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が MX3 Diagnostics Connector に接続できることを確認します。 接続に失敗した場合は、MX3 Diagnostics Connector アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    Note

    `https://scim.mx3.app` に「」と入力します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から MX3 Diagnostics Connector に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選ばれている属性は、更新処理で MX3 Diagnostics Connector のユーザー アカウントとの照合に使われます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、MX3 Diagnostics Connector API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | externalId | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
12. **[属性マッピング]** セクションで、Microsoft Entra ID から MX3 Diagnostics Connector に同期されるグループ属性を確認します。 **[照合]** プロパティとして選ばれている属性は、更新操作で MX3 Diagnostics Connector のグループとの照合に使われます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | externalId | 糸 |  |
    | members | リファレンス |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/my-ibisworld-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に My IBISWorld を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/my-ibisworld-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と My IBISWorld の間でシングル サインオンを構成する方法について説明します。

この記事では、My IBISWorld と Microsoft Entra ID を統合する方法について説明します。 My IBISWorld と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で My IBISWorld へのアクセス権を持つユーザーを管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して My IBISWorld に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- My IBISWorld でのシングル サインオン (SSO) が有効なサブスクリプション。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- My IBISWorld は、IDP **と** SP によって開始される SSO をサポートします。
- My IBISWorld では、**Just In Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの My IBISWorld の追加

Microsoft Entra ID への My IBISWorld の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に My IBISWorld を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリー **から追加する**] セクションで、検索ボックスに「**My IBISWorld**」と入力します。
4. 結果のパネルから **My IBISWorld** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### My IBISWorld の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、My IBISWorld に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと My IBISWorld の関連ユーザーとの間にリンク関係を確立する必要があります。

My IBISWorld に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **My IBISWorld SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **My IBISWorld のテストユーザーを作成** - Microsoft Entraにリンクされた、My IBISWorld内でのB.Simonに対応するユーザーを作成します。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**My IBISWorld**&gt;**シングルサインオン**に移動します。
3. [**シングル サインオン方法の選択]** ページで、SAML **を**選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを **IDP** 開始モードで構成するには、次の手順に従ってください。

    [**Relay State**] テキストボックスに「URL: `RPID=http://fedlogin.ibisworld.com`」と入力し、[**サインオン URL**] テキストボックスは空のままにしてください。
6. **追加の URL を** 設定を選択し、**SP** 開始モードでアプリケーションを構成する場合、次の手順を実行します。

    IBISWorld のサインオン URL については、IBISWorld クライアント リレーションシップ マネージャーに問い合わせ、**サインオン URL** テキスト ボックスに設定します。
7. [**保存]**を選択します。
8. MY IBISWorld アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: イメージ]
9. 上記に加えて、My IBISWorld アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 部署 | ユーザーの部署 |
    | 言語 | ユーザーの優先言語 |
    | 電話 | ユーザー.電話番号 |
    | タイトル | ユーザーの職位 |
    | ユーザーID | ユーザー.従業員ID |
    | 国 | ユーザーの国 |
10. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書の**] セクションで、[コピー] ボタンを選択して **アプリフェデレーション メタデータ URL** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **アプリのフェデレーション メタデータ URL** (または前の手順のメタデータ ファイル) を IBISWorld クライアント リレーションシップ マネージャーに送信する

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### My IBISWorld SSO の構成

**My IBISWorld** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を** IBISWorld クライアント リレーションシップ マネージャーに送信する必要があります。 両方の側で SAML SSO 接続を正しく設定するには、これが必要です。

ご不明な点がある場合は、IBISWorld クライアント リレーションシップ マネージャーにお問い合わせください。IBISWorld IT 部門にお問い合わせください。

#### My IBISWorld テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを My IBISWorld に作成します。 My IBISWorld では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 My IBISWorld にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはサインイン フローを開始できる My IBISWorld のサインオン URL にリダイレクトされます。
- My IBISWorld のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP開始

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した My IBISWorld に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [My IBISWorld] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した My IBISWorld に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/myaos-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に myAOS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/myaos-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と myAOS の間にシングル サインオンを構成する方法について説明します。

この記事では、myAOS と Microsoft Entra ID を統合する方法について説明します。 myAOS と Microsoft Entra ID を統合すると、次のことができます。

- myAOS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して myAOS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- myAOS でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- myAOS では、 **IDP** によって開始される SSO がサポートされます。

### ギャラリーからの myAOS の追加

Microsoft Entra ID への myAOS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に myAOS を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「myAOS**」と入力します。
4. 結果パネルから **myAOS** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### myAOS 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、myAOS に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと myAOS の関連ユーザーとの間にリンク関係を確立する必要があります。

myAOS に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **myAOS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **myAOSのテストユーザーを作成する - myAOS** で B.Simonに対応するユーザーを作成し、Microsoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**myAOS**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **myAOS のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成に適した U R L をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### myAOS SSO の構成

**myAOS** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [myAOS サポート チーム](mailto:support@vialto.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### myAOS テスト ユーザーの作成

このセクションでは、myAOS テストユーザーの作成で Britta Simon というユーザーを作成します。 [myAOS サポート チーム](mailto:support@vialto.com)と協力して、myAOS プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した myAOS に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで myAOS タイルを選択すると、SSO を設定した myAOS に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/myaryaka-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に MyAryaka を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/myaryaka-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MyAryaka の間にシングル サインオンを構成する方法について説明します。

この記事では、MyAryaka と Microsoft Entra ID を統合する方法について説明します。 MyAryaka と Microsoft Entra ID を統合すると、次のことができます。

- MyAryaka にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して MyAryaka に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な MyAryaka サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MyAryaka では、**SP** initiated SSO がサポートされます。

### ギャラリーからの MyAryaka の追加

Microsoft Entra ID への MyAryaka の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MyAryaka を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**MyAryaka**」と入力します。
4. 結果のパネルから **[MyAryaka]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MyAryaka 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、MyAryaka に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと MyAryaka の関連ユーザーとの間にリンク関係を確立する必要があります。

MyAryaka に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MyAryaka の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MyAryaka テストユーザーの作成** - B.Simon の MyAryaka における対応ユーザーを作成し、それを Microsoft Entra の表示ユーザーにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**MyAryaka**&gt;**シングルサインオン**
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://kso.aryaka.com/auth/realms/<CUSTOMERID>`

    b。 **[サインオン URL]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://my.aryaka.com/` |
    | `https://kso.aryaka.com/auth/realms/<CUSTOMERID>` |

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[MyAryaka のクライアント サポート チーム](mailto:support@aryaka.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MyAryaka の SSO の構成

**MyAryaka** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [MyAryaka のサポート チーム](mailto:support@aryaka.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MyAryaka のテスト ユーザーの作成

このセクションでは、MyAryaka で B.Simon というユーザーを作成します。 [MyAryaka のサポート チーム](mailto:support@aryaka.com)と連携して、MyAryaka プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる MyAryaka Sign-On URL にリダイレクトされます。
- MyAryaka のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [MyAryaka] タイルを選択すると、このオプションは MyAryaka Sign-On URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/myawardpoints-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に My Award Points Top Sub/Top Team を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/myawardpoints-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Award Points Top Sub/Top Team 間でのシングル サインオンを構成する方法について説明します。

この記事では、My Award Points Top Sub/Top Team と Microsoft Entra ID を統合する方法について説明します。 My Award Points Top Sub/Top Team を Microsoft Entra ID と統合すると、次のことができます。

- My Award Points Top Sub/Top Team にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで自動的に My Award Points Top Sub/Top Team にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- My Award Points Top Sub/Top Team でシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- My Award Points Top Sub/Top Team では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから My Award Points Top Sub/Top Team を追加する

Microsoft Entra ID への My Award Points Top Sub/Top Team の統合を構成するには、My Award Points Top Sub/Top Team をギャラリーから管理対象 SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**My Award Points Top Sub/Top Team**」と入力します。
4. 結果パネルから **[My Award Points Top Sub/Top Team]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### My Award Points Top Sub/Top Team の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、My Award Points Top Sub/Top Team 用に Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと My Award Points Top Sub/Top Team の関連ユーザーとの間にリンク関係を確立する必要があります。

My Award Points Top Sub/Top Team 用に Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **My Award Points Top Sub/Top Team のシングル サインオンの構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **My Award Points Top Sub/Top Team テスト ユーザーの作成** - My Award Points Top Sub/Top Team で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[My Award Points Top Sub/Top Team]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://microsoftrr.performnet.com/biwv1auth/Shibboleth.sso/Login?providerId=<Azure AD Identifier>`

    注

    これは実際の値ではありません。 `<Azure AD Identifier>`値は、この記事の後の手順で取得します。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[My Award Points Top Sub/Top Team の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    注

    `<Azure AD Identifier>` セクションの  の代わりに、サインオン URL を含むコピーした Microsoft Entra 識別子を追加します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### My Award Points Top Sub/Top Team の SSO を構成する

**My Award Points Top Sub/Top Team** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [My Award Points Top Sub/Top Team サポート チーム](mailto:myawardpoints@biworldwide.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### My Award Points Top Sub/Top Team テスト ユーザーの作成

このセクションでは、Award Points Top Sub/Top Team で Britta Simon というユーザーを作成します。 [My Award Points Top Sub/Top Team サポート チーム](mailto:myawardpoints@biworldwide.com)と一緒に作業して、My Award Points Top Sub/Top Team プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる My Award Points Top Sub/Top Team のサインオン URL にリダイレクトされます。
- My Award Points Top Sub/Top Team のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [My Award Points Top Sub/Top Team] タイルを選択すると、このオプションは My Award Points Top Sub/Top Team のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/myday-provision-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に myday を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/myday-provision-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID から myday にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために myday ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- myday でユーザーを作成する
- アクセスが不要になったユーザーを myday で削除する
- Microsoft Entra ID と myday の間でユーザー属性の同期を維持する
- mydayでグループとそのメンバーを設定する
- myday へのシングル サインオン (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 管理者アクセス許可を持つ myday のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と myday の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように myday を構成する

Myday の担当者またはサポート チームに連絡して、 **テナント URL** と **シークレット トークン**を受け取ります。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから myday を追加する

Microsoft Entra アプリケーション ギャラリーから myday を追加して、myday へのプロビジョニングの管理を開始します。 以前に myday for Single Sign-on (SSO) を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: myday への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で myday の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示すスクリーンショット。]
3. アプリケーションの一覧で **myday** を選択します。

    [Image: [アプリケーション] の一覧の [myday] リンクを示すスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブを示すスクリーンショット。]
5. **[プロビジョニング モード]** を **[自動]** に設定します。

    [Image: [プロビジョニング] タブの [自動] を示すスクリーンショット。]
6. **[管理者資格情報]** セクションで、先ほど取得したテナント URL 値を **[テナント URL]** に入力します。 先ほど取得したシークレット トークン値を **シークレット トークン**に入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が myday に接続できることを確認します。 接続に失敗した場合は、myday アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: [テナント URL トークン] を示すスクリーンショット。]
7. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーまたはグループのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: [通知用メール] を示すスクリーンショット。]
8. **保存** を選択します。
9. **[マッピング]** セクションで、**[Microsoft Entra ユーザーのプロビジョニング]** を選択します。
10. [属性マッピング] セクションで、Microsoft Entra ID から myday に同期されるユーザー **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で myday のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、myday API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | 活動中 | ブール値 |
    | ディスプレイ名 | 糸 |
    | タイトル | 糸 |
    | emails[type eq "仕事"].value | 糸 |
    | 優先言語 | 糸 |
    | 名前.名 | 糸 |
    | 名前.姓 | 糸 |
    | 名前.整形済み | 糸 |
    | エクスターナルID | 糸 |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |
    | addresses[type eq "work"].フォーマット済み | 糸 |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |
    | アドレス[タイプ eq "other"].フォーマット済み | 糸 |
    | phoneNumbers[type eq "ファックス"].value | 糸 |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |
    | roles[primary eq "True"].display | 糸 |
    | roles[primary eq "主要"]。タイプ | 糸 |
    | 役割[主要 eq "True"].値 | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | リファレンス |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:組織 | 糸 |
11. **マッピング** セクションで、**Microsoft Entra グループをプロビジョニング** を選択します。
12. [属性マッピング] セクションで、Microsoft Entra ID から myday に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で myday のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ディスプレイ名 | 糸 |
    | エクスターナルID | 糸 |
    | メンバー | リファレンス |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. myday の Microsoft Entra プロビジョニング サービスを有効にするには、[**設定]** セクションで **[プロビジョニングの状態]** を **[オン**] に変更します。

    [Image: [プロビジョニングの状態] が [オン] に設定された画面のスクリーンショット。]
15. **[設定]** セクションの **[スコープ**] で目的の値を選択して、myday にプロビジョニングするユーザーやグループを定義します。

    [Image: プロビジョニングのスコープを示すスクリーンショット。]
16. プロビジョニングの準備ができたら、 **[保存]** を選択します。

    [Image: プロビジョニング構成の保存を示すスクリーンショット。]

この操作により、[**設定]** セクションの **[スコープ**] で定義されているすべてのユーザーとグループの初期同期サイクルが開始されます。 最初のサイクルは、Microsoft Entra プロビジョニング サービスが実行されている限り、約 40 分ごとに発生する後続のサイクルよりも実行に時間がかかります。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mygeotab-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に MyGeotab を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mygeotab-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MyGeotab の間でシングル サインオンを構成する方法について説明します。

この記事では、MyGeotab と Microsoft Entra ID を統合する方法について説明します。 MyGeotab と Microsoft Entra ID を統合すると、次のことができます。

- MyGeotab にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して MyGeotab に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MyGeotab でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MyGeotab では、 **IDP** によって開始される SSO のみがサポートされます。

### ギャラリーから MyGeotab を追加する

Microsoft Entra ID への MyGeotab の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MyGeotab を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「MyGeotab**」と入力します。
4. 結果パネルから **MyGeotab** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MyGeotab の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、MyGeotab に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと MyGeotab の関連ユーザーとの間にリンク関係を確立する必要があります。

MyGeotab に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MyGeotab SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MyGeotab テスト ユーザーの作成** - B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**MyGeotab**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリは既に Microsoft Entra と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. MyGeotab アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、MyGeotab アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザー.ユーザープリンシパルネーム |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **MyGeotab のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MyGeotab SSO の構成

1. MyGeotab 企業サイトに管理者としてログインします。
2. [ **管理**&gt;**システム**&gt;**システム設定**] に移動します。
3. [ **ユーザー アカウント ポリシー** ] タブを選択し、[ **SAML ログインの許可]** を有効にします。
4. **[証明書**] に移動し&gt;**新しい証明書を追加**し、Microsoft Entra 管理センターからダウンロードした**証明書 (Base64)** をアップロードします。
5. [ **証明書の発行者** ] フィールドに、 **Microsoft Entra** 管理センターからコピーした Microsoft Entra 識別子の値を貼り付けます。
6. [ **ログイン URL** ] フィールドに、Microsoft Entra 管理センターからコピーした **ログイン URL を** 貼り付けます。
7. [ **ログアウト URL** ] フィールドに、Microsoft Entra 管理センターからコピーした **ログアウト URL を** 貼り付けます。
8. [**証明書**] ページで **[保存] を選択します**。
9. [システム設定] ページで **[保存** **] を選択**します。

#### MyGeotab テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、MyGeotab Web サイトに管理者としてサインインします。
2. [認証の種類] として **[SAML** ] を選択し、MyGeotab SAML 認証ページでユーザーを追加または編集します。
3. **[保存] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した MyGeotab に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [MyGeotab] タイルを選択すると、SSO を設定した MyGeotab に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mymobilityhq-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に myMobilityHQ を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mymobilityhq-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と myMobilityHQ の間にシングル サインオンを構成する方法について説明します。

この記事では、myMobilityHQ を Microsoft Entra ID と統合する方法について説明します。myMobilityHQ は、会社のモビリティ マネージャーが、駐在税プログラムの状態のリアルタイム ダッシュボードを表示できるようにするセキュリティで保護されたポータルです。 myMobilityHQ を Microsoft Entra ID と統合すると、次のことができます。

- myMobilityHQ にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って myMobilityHQ に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で myMobilityHQ 用に Microsoft Entra シングル サインオンを構成してテストします。 myMobilityHQ では、**SP**によって開始された シングル サインオンのみがサポートされます。

### [前提条件]

Microsoft Entra ID を myMobilityHQ と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- myMobilityHQ でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから myMobilityHQ アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから myMobilityHQ を追加する

Microsoft Entra アプリケーション ギャラリーから myMobilityHQ を追加して、myMobilityHQ とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**myMobilityHQ**&gt;**Single のサインオンに移動します**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** テキストボックスに、次のいずれかのパターンで値を入力します。

    | **識別子** |
    | --- |
    | `urn:auth0:prod:s<COMPANYNAME>` |
    | `urn:auth0:stage:s<COMPANYNAME>` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://stage.vialto.auth0app.com/login/callback?connection=s<COMPANYNAME>` |
    | `https://prod.vialto.auth0app.com/login/callback?connection=s<COMPANYNAME>` |
    | `https://auth-stage.vialto.com/login/callback?connection=s<COMPANYNAME>` |
    | `https://auth.vialto.com/login/callback?connection=s<COMPANYNAME>` |

    c. [ **サインオン URL** ] ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://mymobilityhq-stage.vialto.com` |
    | `https://mymobilityhq.vialto.com` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[myMobilityHQサポート チーム](mailto:gbl_vialto_iam_engineering_support@vialto.com)にお問い合わせください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### myMobilityHQ SSO の構成

**myMobilityHQ** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [myMobilityHQ サポート チーム](mailto:gbl_vialto_iam_engineering_support@vialto.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### myMobilityHQ テスト ユーザーの作成

このセクションでは、myMobilityHQの作成で Britta Simon というユーザーを作成します。 [myMobilityHQ サポート チーム](mailto:gbl_vialto_iam_engineering_support@vialto.com)と協力して、myMobilityHQプラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる myMobilityHQ サインオン URL にリダイレクトされます。
- myMobilityHQ のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで myMobilityHQ タイルを選択すると、このオプションは myMobilityHQ のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mypolicies-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に myPolicies を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mypolicies-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: myPolicies に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、myPolicies と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを myPolicies に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.
- [myPolicies テナント](https://mypolicies.com/)。
- Admin アクセス許可がある myPolicies のユーザー アカウント。

### myPolicies へのユーザーの割り当て

Microsoft Entra ID では、 *割り当て* と呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、myPolicies へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定したら、次の手順に従って、これらのユーザーやグループを myPolicies に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを myPolicies に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを myPolicies に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- myPolicies にユーザーを割り当てるときは、アプリケーション固有の有効なロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために myPolicies を設定する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に myPolicies を構成する前に、myPolicies で SCIM プロビジョニングを有効にする必要があります。

1. myPolicies の担当者 ( **support@mypolicies.com** ) に連絡して、SCIM のプロビジョニングを構成するために必要なシークレット トークンを入手します。
2. myPolicies 担当者から提供されたトークン値を保存します。 この値は、myPolicies アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

### ギャラリーから myPolicies を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に myPolicies を構成するには、myPolicies を Microsoft Entra アプリケーション ギャラリーから管理対象の SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから myPolicies を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションに**「myPolicies」**と入力し、検索ボックスで **myPolicies** を選択します。
4. 結果パネルから **myPolicies** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 「myPolicies」が表示された結果一覧]

### myPolicies への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、myPolicies でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

myPolicies のシングル サインオンに関する記事で説明されている手順に従って、 [myPolicies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mypolicies-tutorial) に対して SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で myPolicies の自動ユーザー プロビジョニングを構成するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **myPolicies** を選択します。

    [Image: アプリケーションの一覧の myPolicies のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: myPolicies アプリケーションの [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しいアプリケーション プロビジョニング構成ページのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、myPolicies テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が myPolicies に接続できることを確認します。 接続に失敗した場合は、myPolicies アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    注

    `https://<myPoliciesCustomDomain>.mypolicies.com/scim` を があなたの myPolicies カスタム ドメインである`<myPoliciesCustomDomain>` に入力します。 URL から myPolicies 顧客ドメインを取得できます。 (例: `<demo0-qa>`.mypolicies.com)。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから myPolicies に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で myPolicies のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、myPolicies API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | 活動中 | ブール値 |
    | emails[type eq "仕事"].value | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | name.formatted | 糸 |
    | externalId | 糸 |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### コネクタの制限事項

- myPolicies には常に **userName**、 **email** 、 **externalId** が必要です。
- myPolicies では、ユーザー属性のハード削除はサポートされていません。

### 変更履歴

- 2020 年 9 月 15 日 - Users に "country" 属性のサポートを追加しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mypolicies-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に myPolicies を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mypolicies-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と myPolicies の間でシングル サインオンを構成する方法について説明します。

この記事では、myPolicies と Microsoft Entra ID を統合する方法について説明します。 myPolicies と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で myPolicies へのアクセス権を持つユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して myPolicies に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- myPolicies でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- myPolicies は、IDP **によって開始される SSO** をサポートしています。
- myPolicies では、[自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mypolicies-provisioning-tutorial)をサポートしています。

### ギャラリーから myPolicies を追加する

Microsoft Entra ID への myPolicies の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に myPolicies を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリー **から追加する**] セクションで、検索ボックスに「myPolicies  入力します。
4. 結果パネルから **myPolicies** を選んで、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### myPolicies の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、myPolicies に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと myPolicies の関連ユーザーとの間にリンク関係を確立する必要があります。

myPolicies に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **myPolicies SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **myPolicies テストユーザーの作成** - B.Simon に対応するユーザーを myPolicies で作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**myPolicies**&gt;**シングルサインオン**にアクセスします。
3. [**シングル サインオン方法の選択**] ページで、[SAML **]**を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成] 編集
5. [SAML **を使用して単一 Sign-On を設定する**] ページで、次の手順に従います。

    ある。 [**識別子**] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<tenantname>.mypolicies.com/`

    b。 [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<tenantname>.mypolicies.com/users/auth/saml/callback`

    手記

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値 [取得するには、myPolicies クライアント サポート チーム](mailto:support@mypolicies.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [**myPolicies** のセットアップ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### myPolicies SSO の構成

myPolicies **側** シングル サインオンを構成するには、ダウンロードした **証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を myPolicies サポート チーム 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### myPolicies テスト ユーザーの作成

このセクションでは、myPolicies で Britta Simon というユーザーを作成します。 myPolicies サポート チーム  と連携して、myPolicies プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

myPolicies では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した myPolicies に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [myPolicies] タイルを選択すると、SSO を設定した myPolicies に自動的にサインインします。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mysdworxcom-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの my.sdworx.com を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mysdworxcom-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-10
- Summary: Microsoft Entra ID と my.sdworx.com の間にシングル サインオンを構成する方法について説明します。

この記事では、my.sdworx.com を Microsoft Entra ID と統合する方法について説明します。my.sdworx.com は SD Worx ポータルです。 my.sdworx.com を Microsoft Entra ID と統合すると、次のことができます。

- my.sdworx.com にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って my.sdworx.com に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

テスト環境 (my.acc.sdworx.com) での my.sdworx.com に対して Microsoft Entra のシングル サインオンを構成してテストしますが、このギャラリー アプリ (sp メタデータをインポートして、my.sdworx.com の連絡先から提供される) を使用しないでください。 my.sdworx.com では、 **IDP** と **SP** によって開始されるシングル サインオンがサポートされます。

**SP** 開始シングル サインオンを使用する場合は、"電子メール ドメイン" 領域の検出のみがサポートされます。つまり、会社またはエンタープライズの電子メール アドレスのみが許可されます。

テスト環境 (my.acc.sdworx.com) で my.sdworx.com の Microsoft Entra シングル サインオンを構成してテストすることはできますが、ギャラリー アプリ (sp メタデータをインポートして、my.sdworx.com 連絡先から提供される) を使用することはできません。 My.sdworx.com では、 **IDP** と **SP** によって開始されるシングル サインオンがサポートされます。

**SP** 開始シングル サインオンを使用する場合は、"電子メール ドメイン" 領域の検出のみがサポートされます。つまり、会社またはエンタープライズの電子メール アドレスのみが許可されます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### 前提条件

Microsoft Entra ID を my.sdworx.commy.sdworx.com と統合するには、次のものが必要です。

- Microsoft Entra テナントにアプリケーションを追加する前に、まず SD Worx のコンサルタントに連絡して、自社の SSO をアクティブにするための追跡を始めてください。 SSO は、SD Worx サービス プロバイダー上で実装してアクティブ化しなければ機能しません。
- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な my.sdworx.com のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから my.sdworx.com アプリケーションを追加する必要があります。 アプリケーションに割り当て、シングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから my.sdworx.com を追加する

Microsoft Entra アプリケーション ギャラリーから my.sdworx.com を追加して、my.sdworx.com でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーを作成して割り当てる

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができます。 このウィザードには、シングル サインオン構成ウィンドウへのリンクも表示されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra シングル サインオンを有効にするには、次の手順を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**my.sdworx.com**&gt;**シングルサインオン**を表示します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションが事前に構成されており、必要な URL が既に Microsoft Entra に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を保存する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### my.sdworx.com の SSO を構成する

**my.sdworx.com** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[サポート チーム my.sdworx.com](mailto:prod_cloud&amp;busoper_middleware&amp;hostsol@sdworx.com) 送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### my.sdworx.com テスト ユーザーを作成する

このセクションでは、my.sdworx.com で Britta Simon というユーザーを作成します。 [my.sdworx.com サポート チーム](mailto:prod_cloud&amp;busoper_middleware&amp;hostsol@sdworx.com)と協力して、my.sdworx.com プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した my.sdworx.com に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [my.sdworx.com] タイルを選択すると、SSO を設定した my.sdworx.com に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/myvr-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に MyVR を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/myvr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MyVR の間のシングル サインオンを構成する方法について説明します。

この記事では、MyVR と Microsoft Entra ID を統合する方法について説明します。 MyVR と Microsoft Entra ID を統合すると、次のことができます。

- MyVR にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って MyVR に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な MyVR サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MyVR では、**SPによるSSO**と**IDPによるSSO**がサポートされています。
- MyVR では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの MyVR の追加

Microsoft Entra ID への MyVR の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに MyVR を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「MyVR**」と入力します。
4. 結果パネルから **[MyVR** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MyVR 用に Microsoft Entra のシングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、MyVR に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと MyVR の関連ユーザーとの間にリンク関係を確立する必要があります。

MyVR に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MyVR SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MyVR のテストユーザーを作成** - MyVR 内で B.Simon に対応するユーザーを作成し、それを Microsoft Entra にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**MyVR**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://ess.virtualroster.net/ess/login.aspx`
7. MyVR アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、MyVR アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 従業員ID | ユーザー.社員ID |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **MyVR のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MyVR SSO の構成

**MyVR** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [MyVR サポート チーム](mailto:arno.vandenberg@Kronos.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MyVR テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを MyVR に作成します。 MyVR では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 MyVR にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [MyVR] タイルを選択すると、SSO を設定した MyVR に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/myworkdrive-tutorial"} -->
## Microsoft Entra ID を使用して MyWorkDrive のシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/myworkdrive-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MyWorkDrive の間でシングル サインオンを構成する方法について説明します。

この記事では、MyWorkDrive と Microsoft Entra ID を統合する方法について説明します。 MyWorkDrive を Microsoft Entra ID と統合すると、次のことができます:

- MyWorkDrive にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って MyWorkDrive に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MyWorkDrive でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MyWorkDrive では、**SP** と **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの MyWorkDrive の追加

Microsoft Entra への MyWorkDrive の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MyWorkDrive を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**MyWorkDrive**」と入力します。
4. 結果ウィンドウで **[MyWorkDrive]** を選択し、アプリケーションを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MyWorkDrive 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、MyWorkDrive に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと MyWorkDrive の関連ユーザーとの間にリンク関係を確立する必要があります。

MyWorkDrive に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MyWorkDrive SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MyWorkDrive のテスト ユーザーを作成** - Microsoft Entra の B.Simon に対応するユーザーを MyWorkDrive 上にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**MyWorkDrive** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** ページで、アプリケーションを **IDP** Initiated モードで構成する場合は、次の手順を行います。

    **[応答 URL]** ボックスに、`https://<SERVER.DOMAIN.COM>/SAML/AssertionConsumerService.aspx` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<SERVER.DOMAIN.COM>/Account/Login-saml` という形式で URL を入力します。

    注

    これらの値は実際の値ではありません。 実際の応答 URLとサインオン URL でこれらの値を更新します。 自社の MyWorkDrive サーバーのホスト名を入力します。たとえば、次のようになります。

    応答 URL: `https://yourserver.contoso.com/SAML/AssertionConsumerService.aspx`

    サインオンURL:`https://yourserver.contoso.com/Account/Login-saml`

    これらの値に対して独自のホスト名と TLS/SSL 証明書を設定する方法がわからない場合は、 [MyWorkDrive サポート チーム](mailto:support@myworkdrive.com) にお問い合わせください。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **、アプリのフェデレーション メタデータ URL を** クリップボードにコピーします。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MyWorkDrive の SSO を構成する

1. 別の Web ブラウザー ウィンドウで、MyWorkDrive 企業サイトに管理者としてサインインします
2. 管理パネルの MyWorkDrive サーバーで、[ **エンタープライズ** ] を選択し、次の手順を実行します。

    [Image: 管理]

    ある。 **SAML/ADFS SSO** を有効にする。

    b。 **[SAML - Microsoft Entra ID]** を選択します。

    c. **[Azure アプリ フェデレーション メタデータ URL]** テキストボックスに、前にコピーした **[アプリのフェデレーション メタデータ URL]** の値を貼り付けます。

    d. **保存** を選択します。

    注

    追加情報については、「[MyWorkDrive の Microsoft Entra サポート](https://www.myworkdrive.com/support/saml-single-sign-on-azure-ad/)」記事を参照してください。

#### MyWorkDrive テスト ユーザーの作成

このセクションでは、MyWorkDrive で Britta Simon というユーザーを作成します。 [MyWorkDrive サポート チーム](mailto:support@myworkdrive.com)と連携し、MyWorkDrive プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP を開始しました:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる MyWorkDrive のサインオン URL にリダイレクトされます。
- MyWorkDrive のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した MyWorkDrive に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [MyWorkDrive] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した MyWorkDrive に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/n-able-user-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に N 対応ユーザー プロビジョニングを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/n-able-user-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra ID から N-able User Provisioning に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために N 対応ユーザー プロビジョニングと Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使って、[N-able User Provisioning](https://www.n-able.com) に対するユーザーのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- N-able User Provisioning でユーザーを作成する。
- アクセスが不要になった場合は、N 対応ユーザー プロビジョニングのユーザーを削除します。
- Microsoft Entra ID と N-able User Provisioning の間でユーザー属性の同期を維持する。

注

OAuth2 フローを使用するには、ユーザーは https://portal.azure.com/?feature.userProvisioningV2Authentication=true url を使用し、Azure portal にアクセスする必要があります。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- N-able User Provisioning で管理者アクセス許可を持つユーザー アカウント。

### 手順 1: プロビジョニングの展開を計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- [Microsoft Entra ID と N-able User Provisioning の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra アプリケーション ギャラリーから N-able User Provisioning を追加する

Microsoft Entra アプリケーション ギャラリーから N-able User Provisioning を追加して、N-able User Provisioning へのプロビジョニングの管理を開始します。 SSO のために N-able User Provisioning を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 3: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 4: N-able User Provisioning への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、N-able User Provisioning でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で N-able User Provisioning の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーション一覧で、**[N-able User Provisioning]** を選びます。

    [Image: アプリケーション一覧の N-able User Provisioning リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: アプリケーション設定の [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **テナント URL** フィールドに、N-able ユーザー プロビジョニングのテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が N 可能なユーザー プロビジョニングに接続できることを確認します。 接続に失敗した場合は、N 対応のユーザー プロビジョニング アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    注

    OAuth2 フローを使用するには、ユーザーは https://portal.azure.com/?feature.userProvisioningV2Authentication=true url を使用し、Azure portal にアクセスする必要があります。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から N-able User Provisioning に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で N-able User Provisioning のユーザー アカウントとの照合に使用されます。 [一致する対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づいたユーザーのフィルター処理が確実に N-able User Provisioning API でサポートされているようにする必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | N-able User Provisioning で必須です |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/n2f-expensereports-tutorial"} -->
## Microsoft Entra ID によるシングルサインオンのための N2F 経費報告を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/n2f-expensereports-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と N2F - Expense reports の間にシングル サインオンを構成する方法について説明します。

この記事では、N2F - Expense reports と Microsoft Entra ID を統合する方法について説明します。 N2F - Expense reports と Microsoft Entra ID を統合すると、次のことができます。

- N2F - Expense reports にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して N2F - Expense reports に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- N2F - Expense reports でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- N2F - Expense reports では、**SP** 開始SSO と **IDP** 開始SSO がサポートされます。

### ギャラリーから N2F - Expense reports を追加する

Microsoft Entra ID への N2F - Expense reports の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に N2F - Expense reports を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「N2F - Expense reports**」と入力します。
4. 結果パネルから **N2F - Expense reports** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### N2F - Expense reports 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、N2F - Expense reports に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと N2F - Expense reports の関連ユーザーとの間にリンク関係を確立する必要があります。

N2F - Expense reports 用に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **N2F - Expense reports SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **N2F - Expense reports テストユーザーを作成** - N2F - Expense reports における B.Simon の対応ユーザーを作成し、それを Microsoft Entra のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**N2F - Expense reports**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、アプリが既に Azure に事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://auth.n2f.com/`
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **myPolicies のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### N2F - Expense reports の SSO の構成

1. 別の Web ブラウザー ウィンドウで、N2F - Expense reports 企業サイトに管理者としてサインインします。
2. [ **設定]** を選択し、ドロップダウンから **[詳細設定]** を選択します。

    [Image: [詳細設定] が選択されているスクリーンショット。]
3. [ **アカウント設定** ] タブを選択します。

    [Image: [アカウント設定] が選択されているスクリーンショット。]
4. [ **認証** ] を選択し、[ **+ 認証方法の追加** ] タブを選択します。

    [Image: スクリーンショットは、認証方法を追加できるアカウント設定認証を示しています。]
5. 認証方法として **SAML Microsoft Office 365** を選択します。

    [Image: SAML Microsoft Office 365 が選択されている認証方法を示すスクリーンショット。]
6. [ **認証方法** ] セクションで、次の手順を実行します。

    [Image: スクリーンショットは、説明されている値を入力できる認証方法を示しています。]

    ある。 [ **エンティティ ID** ] ボックスに、先にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    b。 [ **メタデータ URL** ] ボックスに、前にコピーした **アプリのフェデレーション メタデータ URL** の値を貼り付けます。

    c. **[保存] を選択します**。

#### N2F - Expense reports テストユーザーの作成

Microsoft Entra ユーザーが N2F - Expense reports にログインできるようにするには、そのユーザーを N2F - Expense reports にプロビジョニングする必要があります。 N2F - Expense reports の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. N2F - Expense reports 企業サイトに管理者としてログインします。
2. [ **設定]** を選択し、ドロップダウンから **[詳細設定]** を選択します。

    [Image: [詳細設定] が選択されているスクリーンショット。]
3. 左側のナビゲーション パネルから [ **ユーザー** ] タブを選択します。

    [Image: [ユーザー] が選択されているスクリーンショット。]
4. [ **+ 新しいユーザー** ] タブを選択します。

    [Image: [新しいユーザー] オプションを示すスクリーンショット。]
5. [ **ユーザー** ] セクションで、次の手順を実行します。

    [Image: スクリーンショットは、説明されている値を入力できるセクションを示しています。]

    ある。 [ **電子メール アドレス** ] ボックスに、ユーザーのメール アドレス ( **brittasimon@contoso.com**など) を入力します。

    b。 [ **名** ] ボックスに、 **Britta** などのユーザーの名を入力します。

    c. [ **名前** ] ボックスに、 **BrittaSimon** などのユーザーの名前を入力します。

    d. 組織の要件に従って **、ロール、ダイレクト マネージャー (N+1)**、 **および部門** を選択します。

    え [ **検証して招待を送信**する] を選択します。

    注

    ユーザーの追加中に問題が発生した場合は、[N2F - Expense reports サポート チーム](mailto:support@n2f.com)にお問い合わせください

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる N2F - Expense reports のサインオン URL にリダイレクトされます。
- N2F - Expense reports のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した N2F - Expense reports に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [N2F - Expense reports] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した N2F - Expense reports に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/namely-tutorial"} -->
## Microsoft Entra ID で Namely for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/namely-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Namely の間でシングル サインオンを構成する方法について説明します。

この記事では、Namely と Microsoft Entra ID を統合する方法について説明します。 Namely と Microsoft Entra ID を統合すると、次のことができます。

- Namely へのアクセス権を持つユーザーを Microsoft Entra ID で管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Namely に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- つまり、シングル サインオン (SSO) が有効なサブスクリプションです。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- つまり、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから Namely を追加する

Microsoft Entra ID への Namely の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Namely を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックス **に「Namely** 」と入力します。
4. 結果パネルから **Namely** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Namely の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Namely に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Namely の関連ユーザーとの間にリンク関係を確立する必要があります。

Namely に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Namely SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Namely テストユーザーの作成** - Microsoft Entra のユーザーにリンクされる、Namely 上の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;にアクセスし、**つまり**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    エー。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.namely.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.namely.com/saml/metadata`

    手記

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、Namelyのクライアントサポートチームにお問い合わせください。  「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Namely のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Namely SSO の構成

1. 別のブラウザー ウィンドウで、Namely 企業サイトに管理者としてサインオンします。
2. 上部のツール バーで[ **会社**]を選択します。

    [Image: 選択されている会社の値を示すスクリーンショット。]
3. [ **設定]** タブを選択します。

    [Image: [会社の設定] タブが選択されているスクリーンショット。]
4. **[SAML**] を選択します。

    [Image: [SAML] が選択されているスクリーンショット。]
5. [ **SAML 設定]** ページで、次の手順を実行します。

    [Image: [SAML Settings](SAML 設定) を示すスクリーンショット。ここで、説明されている値を入力できます。]

    エー。 [ **SAML を有効にする] を選択します**。

    b。 **ID プロバイダーの SSO URL** ボックスに、**ログイン URL** の値を貼り付けます。

    c. ダウンロードした証明書をメモ帳で開き、内容をコピーし、テキスト ボックス  ID プロバイダー証明書に貼り付けます。

    d. **[保存] を選択します**。

#### Namely テスト ユーザーの作成

このセクションの目的は、Namely で Britta Simon というユーザーを作成することです。

**Namely で Britta Simon というユーザーを作成するには、次の手順に従います。**

1. Namely 企業サイトに管理者としてサインオンします。
2. 上部のツール バーで、[ **ユーザー**] を選択します。

    [Image: [People] の設定が選択されているスクリーンショット。]
3. [ **ディレクトリ** ] タブを選択します。

    [Image: スクリーンショットは、[People Directory] タブが選択されている状態を示しています。]
4. [ **新しいユーザーの追加] を選択します**。

    [Image: [Add New Person](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザーの追加) オプションを示すスクリーンショット。]
5. [ **新しいユーザーの追加** ] ダイアログで、次の手順を実行します。

    エー。 [ **名** ] ボックスに「 **Britta**」と入力します。

    b。 [ **姓** ] ボックスに「 **Simon**」と入力します。

    c. [ **電子メール** ] ボックスに、BrittaSimon の **メール アドレス** を入力します。

    d. **[保存] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Namely のサインオン URL にリダイレクトされます。
- Namely のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Namely] タイルを選択すると、このオプションは Namely のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nature-research-tutorial"} -->
## Microsoft Entra ID で Nature Research for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nature-research-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Nature Research の間でシングル サインオンを構成する方法について説明します。

この記事では、Nature Research と Microsoft Entra ID を統合する方法について説明します。 Nature Research を Microsoft Entra ID を統合すると、次のことができます。

- Nature Research にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Nature Research に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Nature Research でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Nature Research では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

### ギャラリーからの Nature Research の追加

Microsoft Entra ID への Nature Research の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Nature Research を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Nature Research**」と入力します。
4. 結果パネルから **[Nature Research]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Nature Research 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Nature Research に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Nature Research の関連ユーザーとの間にリンク関係を確立する必要があります。

Nature Research に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Nature Research の SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**Nature Research**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **IDP** 開始モードでアプリケーションを構成する場合、 **[基本的な SAML 構成]** セクションでは、識別子と応答 URL の値は既に Azure で事前に設定されていますが、リレー状態の値を入力する必要があります。

    [ **リレー状態** ] テキスト ボックスに、URL を入力します。 `https://www.nature.com`**[保存]** を選択します。
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sp.nature.com/saml/login?idp=<IDP_ENTITY_ID>`

    注

    Sign-On URL 値は実際の値ではありません。 `<IDP_ENTITY_ID>` は、**[Nature Research のセットアップ]** セクションからコピーした Microsoft Entra 識別子です。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
7. **保存** を選択します。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Nature Research の SSO の構成

**Nature Research** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Nature Research サポート チーム](mailto:onlineservice@springernature.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Nature Research のサインオン URL にリダイレクトされます。
- Nature Research のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Nature Research に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [Nature Research] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Nature Research に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/navan-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用のナヴァンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/navan-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Navan 間にシングル サインオンを構成する方法について説明します。

この記事では、ナヴァンと Microsoft Entra ID を統合する方法について説明します。 Navan を Microsoft Entra ID と統合すると、次のことができます。

- Navan にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Navan に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Navan のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ナヴァンでは、**SP**開始SSOと**IDP**開始SSOの両方がサポートされます。
- ナヴァンでは、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Navan を追加する

Microsoft Entra ID への Navan の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Navan を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「ナヴァン**」と入力します。
4. 結果パネルから **[ナバン]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Navan 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、ナヴァンに対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Navan の関連ユーザーとの間にリンク関係を確立する必要があります。

Navan に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ナヴァン SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Navan のテスト ユーザーを作成** - Navan で B.Simon に対応するユーザーを作成し、Microsoft Entra 上のそのユーザー表現にリンクさせるためです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Navan**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションが事前に構成されており、必要な URL が既に Microsoft Entra に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.tripactions.com`
7. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **ナヴァンのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Navan の SSO を構成する

**ナヴァン**側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を[ナヴァン サポート チーム](mailto:launches@tripactions.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Navan のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Navan に作成します。 Navan では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Navan にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できるナヴァン サインオン URL にリダイレクトされます。
- Navan のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定したナヴァンに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ナヴァン] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したナバンに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/navex-irm-keylight-lockpath-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に NAVEX IRM (Lockpath/Keylight) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/navex-irm-keylight-lockpath-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と NAVEX IRM (Lockpath/Keylight) の間でシングル サインオンを構成する方法について説明します。

この記事では、NAVEX IRM (Lockpath/Keylight) と Microsoft Entra ID を統合する方法について説明します。 NAVEX IRM (Lockpath/Keylight) と Microsoft Entra ID を統合すると、次のことができるようになります。

- NAVEX IRM (Lockpath/Keylight) にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して NAVEX IRM (Lockpath/Keylight) に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- NAVEX IRM (Lockpath/Keylight) でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- NAVEX IRM (Lockpath/Keylight) では、**SP** Initiated SSO がサポートされています。
- NAVEX IRM (Lockpath/Keylight) では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの NAVEX IRM (Lockpath/Keylight) を追加する

Microsoft Entra ID への NAVEX IRM (Lockpath/Keylight) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に NAVEX IRM (Lockpath/Keylight) を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**NAVEX IRM (Lockpath/Keylight)**」と入力します。
4. 結果パネルで **[NAVEX IRM (Lockpath/Keylight)]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### NAVEX IRM (Lockpath/Keylight) の Microsoft Entra SSO を構成しテストする

**B.Simon** というテスト ユーザーを使用して、NAVEX IRM (Lockpath/Keylight) を使用した Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと NAVEX IRM (Lockpath/Keylight) の関連ユーザーとの間にリンク関係を確立する必要があります。

NAVEX IRM (Lockpath/Keylight) を使用した Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **NAVEX IRM (Lockpath/Keylight) SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **NAVEX IRM (Lockpath/Keylight) テストユーザーを作成** - Microsoft Entra におけるユーザーの表現にリンクされる、NAVEX IRM (Lockpath/Keylight) でB.Simonに対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**NAVEX IRM (Lockpath/Keylight)**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.keylightgrc.com`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.keylightgrc.com/Login.aspx`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.keylightgrc.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[NAVEX IRM (Lockpath/Keylight) クライアント サポート チーム](https://www.lockpath.com/contact/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[NAVEX IRM (Lockpath/Keylight) のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### NAVEX IRM (Lockpath/Keylight) SSO を構成する

1. NAVEX IRM (Lockpath/Keylight) で SSO を有効にするには、次の手順に従います。

    ある。 管理者として NAVEX IRM (Lockpath/Keylight) アカウントにサインオンします。

    b。 上部のメニューで、[ **ユーザー アイコン**] を選択し、[ **セットアップ]** を選択します。

    c. 左側のツリービューで、[SAML] を選択 **します**。

    [Image: ツリー ビューで [SAML] が選択されているスクリーンショット。]

    d. [ **SAML 設定]** ダイアログで、[ **編集**] を選択します。

    [Image: [Edit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/編集) ボタンが選択されている [SAML Settings](SAML の設定) ウィンドウを示すスクリーンショット。]
2. **[SAML 設定の編集]** ダイアログ ページで、次の手順を実行します。

    [Image: シングルサインオンの設定]

    ある。 **[SAML 認証]** を **[アクティブ]** に設定します。

    b。 **[ID プロバイダーのログイン URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を入力します。

    c. **[ID プロバイダーのログアウト URL]** ボックスに、前にコピーした **[ログアウト URL]** 値を貼り付けます。

    d. [ **ファイルの選択] を選択** してダウンロードした NAVEX IRM (Lockpath/Keylight) 証明書を選択し、[ **開く** ] を選択して証明書をアップロードします。

    え **[SAML ユーザー ID の場所]** を **[Subject ステートメントの NameIdentifier 要素]** に設定します。

    f. **サービス プロバイダー エンティティ ID** を `https://<CompanyName>.keylightgrc.com` の形式で指定します。

    ジー **[ユーザーの自動プロビジョニング]** を **[アクティブ]** に設定します。

    h. **[アカウント タイプの自動プロビジョニング]** を **[すべてのユーザー]** に設定します。

    一. **[Auto-provision security role](セキュリティ ロールの自動プロビジョニング)** を設定し、 **[Standard User with SAML](SAML を使用する標準ユーザー)** を選択します。

    j. **[Auto-provision security config](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ構成の自動プロビジョニング)** を設定し、 **[Standard User Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/標準ユーザー構成)** を選択します。

    ケー **[Email Attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール属性)** ボックスに、「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`」と入力します。

    l. **[First name attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名属性)** ボックスに、「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`」と入力します。

    m. **[Last name attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓属性)** ボックスに、「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname`」と入力します。

    n. **保存** を選択します。

#### NAVEX IRM (Lockpath/Keylight) テスト ユーザーを作成する

このセクションでは、NAVEX IRM (Lockpath/Keylight) で Britta Simon というユーザーを作成します。 NAVEX IRM (Lockpath/Keylight) では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 NAVEX IRM (Lockpath/Keylight) にユーザーがまだ存在していない場合は、認証後に新規に作成されます。 ユーザーを手動で作成する必要がある場合は、[NAVEX IRM (Lockpath/Keylight) カスタマー サポート チーム](https://www.lockpath.com/contact/)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる NAVEX IRM (Lockpath/Keylight) のサインオン URL にリダイレクトされます。
- NAVEX IRM (Lockpath/Keylight) のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで NAVEX IRM (Lockpath/Keylight) タイルを選択すると、このオプションは NAVEX IRM (Lockpath/Keylight) のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/navex-one-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に NAVEX One を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/navex-one-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と NAVEX One の間にシングル サインオンを構成する方法について説明します。

この記事では、NAVEX One と Microsoft Entra ID を統合する方法について説明します。 NAVEX One を Microsoft Entra ID と統合すると、次のことができます。

- NAVEX One にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して NAVEX One に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な NAVEX One サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- NAVEX One では、**SP** によって開始される SSO がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの NAVEX One の追加

Microsoft Entra ID への NAVEX One の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に NAVEX One を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**NAVEX One**」と入力します。
4. 結果パネルから **[NAVEX One]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### NAVEX One 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、NAVEX One に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと NAVEX One の関連ユーザーとの間にリンク関係を確立する必要があります。

NAVEX One に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **NAVEX One の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **NAVEX One テスト ユーザーの作成** - NAVEX One で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 上の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**NAVEX One**&gt;**シングルサインオン**にアクセスしてください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 識別子 |
    | --- |
    | `https://doorman.navexglobal.com/Shibboleth` |
    | `https://doorman.navexglobal.eu/Shibboleth` |
    |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 応答 URL |
    | --- |
    | `https://doorman.navexglobal.com/Shibboleth.sso/SAML2/POST` |
    | `https://doorman.navexglobal.eu/Shibboleth.sso/SAML2/POST` |
    |  |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://<CLIENT_KEY>.navexglobal.com` |
    | `https://<CLIENT_KEY>.navexglobal.eu` |
    |  |

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[NAVEX One クライアント サポート チーム](mailto:ethicspoint@navexglobal.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### NAVEX One の SSO の構成

**NAVEX One** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [NAVEX One サポート チーム](mailto:ethicspoint@navexglobal.com)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### NAVEX One のテスト ユーザーの作成

このセクションでは、NAVEX One で Britta Simon というユーザーを作成します。 [NAVEX One サポート チーム](mailto:ethicspoint@navexglobal.com)と協力して、NAVEX One プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる NAVEX One のサインオン URL にリダイレクトされます。
- NAVEX One のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [NAVEX One] タイルを選択すると、このオプションは NAVEX One のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/navigo-cloud-saml-tutorial"} -->
## Navigo Cloud SAML を Microsoft Entra ID でシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/navigo-cloud-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Navigo Cloud SAML の間でシングル サインオンを構成する方法について説明します。

この記事では、Navigo Cloud SAML と Microsoft Entra ID を統合する方法について説明します。 Navigo Cloud SAML と Microsoft Entra ID を統合すると、次のことができます。

- Navigo Cloud SAML にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Navigo Cloud SAML に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Navigo Cloud SAML でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Navigo Cloud SAML では、 **SP** によって開始される SSO のみがサポートされます。

### ギャラリーからの Navigo Cloud SAML の追加

Microsoft Entra ID への Navigo Cloud SAML の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Navigo Cloud SAML を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Navigo Cloud SAML**」と入力します。
4. 結果パネルから **Navigo Cloud SAML** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Navigo Cloud SAML の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Navigo Cloud SAML に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Navigo Cloud SAML の関連ユーザーとの間にリンク関係を確立する必要があります。

Navigo Cloud SAML に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Navigo Cloud SAML SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Navigo Cloud SAML テスト ユーザーの作成** - Navigo Cloud SAML で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Navigo Cloud SAML**&gt;**シングルサインオン** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかの URL を入力します。

    | **識別子 (エンティティ ID)** |
    | --- |
    | `https://login.navigocloud.com` |
    | `https://navigocloud-dev.fusionauth.io` |
    | `https://navigocloud.com` |
    | `https://beta.navigocloud.com` |
    | `https://demo1.navigocloud.com` |
    | `https://staging.navigocloud.com` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://navigocloud-dev.fusionauth.io/samlv2/acs` |
    | `https://login.navigocloud.com/samlv2/acs` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://login.navigocloud.com` |
    | `https://navigocloud-dev.fusionauth.io` |
    | `https://navigocloud.com` |
    | `https://beta.navigocloud.com` |
    | `https://demo1.navigocloud.com` |
    | `https://staging.navigocloud.com` |
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Navigo Cloud SAML のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Navigo Cloud SAML SSO の構成

**Navigo Cloud SAML** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、Microsoft Entra 管理センターからコピーした適切な URL を [Navigo Cloud SAML サポート チーム](mailto:support@itouchinc.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Navigo Cloud SAML テスト ユーザーの作成

このセクションでは、Navigo Cloud SAML で B.Simon というユーザーを作成します。 [Navigo Cloud SAML サポート チーム](mailto:support@itouchinc.com)と協力して、Navigo Cloud SAML プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Navigo Cloud SAML サインオン URL にリダイレクトします。
- Navigo Cloud SAML のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Navigo Cloud SAML] タイルを選択すると、このオプションは Navigo Cloud SAML サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/negometrixportal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に NegometrixPortal シングル サインオン (SSO) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/negometrixportal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と NegometrixPortal Single Sign On (SSO) の間でシングル サインオンを構成する方法について説明します。

この記事では、NegometrixPortal シングル サインオン (SSO) と Microsoft Entra ID を統合する方法について説明します。 NegometrixPortal Single Sign On (SSO) を Microsoft Entra ID と統合すると、次のことができます。

- NegometrixPortal Single Sign On (SSO) にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して NegometrixPortal Single Sign On (SSO) に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- NegometrixPortal Single Sign On (SSO) シングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- NegometrixPortal Single Sign On (SSO) では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから NegometrixPortal Single Sign On (SSO) を追加する

Microsoft Entra ID への NegometrixPortal Single Sign On (SSO) の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に NegometrixPortal Single Sign On (SSO) を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**NegometrixPortal Single Sign On (SSO)** 」と入力します。
4. 結果パネルから **[NegometrixPortal Single Sign-on (SSO)]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### NegometrixPortal Single Sign On (SSO) に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、NegometrixPortal Single Sign On (SSO) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと NegometrixPortal Single Sign On (SSO) の関連ユーザーとの間にリンク関係を確立する必要があります。

NegometrixPortal Single Sign On (SSO) に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **NegometrixPortal Single Sign On (SSO) のシングル サインオンの構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **NegometrixPortal Single Sign On (SSO) テストユーザーの作成** - NegometrixPortal Single Sign On (SSO) において B.Simon の対応ユーザーとしてテストユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**NegometrixPortal シングル サインオン (SSO)**&gt;**Single サインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://portal.negometrix.com/sso/<CUSTOMURL>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[NegometrixPortal Single Sign On (SSO) クライアント サポート チーム](mailto:sander.hoek@negometrix.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. NegometrixPortal Single Sign On (SSO) アプリケーションは特定の形式の SAML アサーションを予測しているため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、NegometrixPortal Single Sign On (SSO) アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | UPN | ユーザー.ユーザープリンシパルネーム |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### NegometrixPortal Single Sign On (SSO) のシングル サインオンを構成する

**NegometrixPortal Single Sign On (SSO)** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [NegometrixPortal Single Sign On (SSO) サポートチーム](mailto:sander.hoek@negometrix.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### NegometrixPortal Single Sign On (SSO) のテスト ユーザーを作成する

このセクションでは、NegometrixPortal Single Sign On (SSO) で B.Simon というユーザーを作成します。 [NegometrixPortal Single Sign On (SSO) のサポートチーム](mailto:sander.hoek@negometrix.com)と連携し、NegometrixPortal Single Sign On (SSO) プラットフォームでユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる NegometrixPortal シングル サインオン (SSO) のサインオン URL にリダイレクトされます。
- NegometrixPortal Single Sign On (SSO) のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで NegometrixPortal シングル サインオン (SSO) タイルを選択すると、このオプションは NegometrixPortal Single Sign On (SSO) のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/neogov-tutorial"} -->
## Microsoft Entra ID で NEOGOV for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/neogov-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と NEOGOV 間にシングル サインオンを構成する方法について説明します。

この記事では、NEOGOV と Microsoft Entra ID を統合する方法について説明します。 NEOGOV を Microsoft Entra ID と統合すると、次のことができます。

- NEOGOV にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して NEOGOV に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- NEOGOV でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- NEOGOV では、**IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの NEOGOV の追加

Microsoft Entra ID への NEOGOV の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に NEOGOV を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**NEOGOV**」と入力します。
4. 結果のパネルから **[NEOGOV]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### NEOGOV 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、NEOGOV に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと NEOGOV の関連ユーザーとの間にリンク関係を確立する必要があります。

NEOGOV に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **NEOGOV SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **NEOGOV のテスト ユーザーの作成 - NEOGOV** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**NEOGOV**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[SAML でシングル サインオンをセットアップします]** ページで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL パターン |
    | --- | --- |
    | 生産 | `https://login.neogov.com/` |
    | サンドボックス | `https://login.uat.neogov.net/` |
    |  |  |

    b。 **[応答 URL]** ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL パターン |
    | --- | --- |
    | 生産 | `https://login.neogov.com/authentication/saml/consumer` |
    | サンドボックス | `https://login.uat.neogov.net/authentication/saml/consumer` |
    |  |  |
6. NEOGOV アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 NEOGOV アプリケーションでは **、nameidentifier** が **user.objectid** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. その他に、NEOGOV アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### NEOGOV SSO の構成

**NEOGOV** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を NEOGOV 実装コンサルタントまたは NEOGOV サポート チームに送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### NEOGOV テスト ユーザーの作成

このセクションでは、NEOGOV で B.Simon というユーザーを作成します。 NEOGOV 実装コンサルタントまたは NEOGOV サポートチームと協力して、NEOGOV プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した NEOGOV に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで NEOGOV タイルを選択すると、SSO を設定した NEOGOV に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/neota-tutorial"} -->
## Microsoft Entra ID で Neota for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/neota-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Neota の間でシングル サインオンを構成する方法について説明します。

この記事では、Neota と Microsoft Entra ID を統合する方法について説明します。 Neota と Microsoft Entra ID を統合すると、次のことができます。

- Neota にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Neota に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Neota でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Neota では、 **SP** Initiated SSO と **IDP** Initiated SSO の両方がサポートされます。

### ギャラリーから Neota を追加する

Microsoft Entra ID への Neota の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Neota を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Neota**」と入力します。
4. 結果パネルから **Neota** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Neota の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Neota に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Neota の関連ユーザーとの間にリンク関係を確立する必要があります。

Neota に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Neota SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Neota のテストユーザーを作成** - NeotaでB.Simonの対応ユーザーを作成し、Microsoft Entraにおけるユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Neota**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.neotalogic.com/wb`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.neotalogic.com/sso/callback?client_name=<Integration_ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.neotalogic.com/wb`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Neota クライアント サポート チーム](https://neota.com/contact) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Neota アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: アサーションの画像を表示するスクリーンショット。]
8. 上記に加えて、Neota アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名（ファーストネーム） | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
    | 組織 | ユーザー.companyname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Neota SSO の構成

**Neota** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Neota サポート チーム](https://neota.com/contact)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Neota テスト ユーザーの作成

このセクションでは、Neota で Britta Simon というユーザーを作成します。 [Neota サポート チーム](https://neota.com/contact)と協力して、Neota プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Neota のサインオン URL にリダイレクトされます。
- Neota のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Neota] タイルを選択すると、このオプションは Neota のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netcloud-manager-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に NetCloud Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netcloud-manager-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-06
- Summary: Microsoft Entra ID と NetCloud Manager の間のシングル サインオンを構成する方法について学習します。

この記事では、NetCloud Manager と Microsoft Entra ID を統合する方法について説明します。 NetCloud Manager と Microsoft Entra ID を統合すると、次のことができます。

- 誰が NetCloud Manager にアクセスできるかを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って NetCloud Manager に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- NetCloud Manager でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- NetCloud Manager では、**IDP** initiated SSO のみがサポートされます。

### ギャラリーから NetCloud Manager を追加する

Microsoft Entra ID への NetCloud Manager の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに NetCloud Manager を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**NetCloud Manager**」と入力します。
4. 結果ペインから **[NetCloud Manager]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### NetCloud Manager 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使って、NetCloud Manager に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと NetCloud Manager の関連ユーザーとの間にリンク リレーションシップを確立する必要があります。

NetCloud Manager に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **NetCloud Manager の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **NetCloud Manager のテストユーザーを作成する** - Microsoft Entra ID にリンクされた B.Simon に対応するユーザーを NetCloud Manager に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**NetCloud Manager**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://cradlepoint.okta.com/sso/saml2/<ID>` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://cradlepoint.okta.com/sso/saml2/<ID>` のパターンを使用して URL を入力します

    注意

    これらの値は実際の値ではありません。 これらは、アプリケーションの残りの設定を構成するために一時的に使用されます。 実際の値を受け取る場合は、[NetCloud Manager サポート チーム](mailto:support@cradlepoint.com)に連絡して移行プロセスを開始してください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. NetCloud Manager アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性構成の画像を示すスクリーンショット。]
7. その他に、NetCloud Manager アプリケーションでは、次に示すいくつかの属性が SAML 応答で返されることが想定されています。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | 苗字 | User.surname |
    | メール | ユーザー.ユーザープリンシパルネーム |
8. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[NetCloud Manager の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### NetCloud Manager の SSO を構成する

**NetCloud Manager** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [NetCloud Manager サポート チーム](mailto:support@cradlepoint.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### NetCloud Manager のテスト ユーザーを作成する

このセクションでは、NetCloud Manager で B.Simon というユーザーを作成します。 [NetCloud Manager サポート チーム](mailto:support@cradlepoint.com)と連携し、NetCloud Manager プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した NetCloud Manager に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [NetCloud Manager] タイルを選択すると、SSO を設定した NetCloud Manager に自動的にサインインします。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netdocuments-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に NetDocuments を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netdocuments-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と NetDocuments の間にシングル サインオンを構成する方法について説明します。

この記事では、NetDocuments と Microsoft Entra ID を統合する方法について説明します。 NetDocuments を Microsoft Entra ID と統合すると、次のことができます。

- NetDocuments にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して NetDocuments に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- NetDocuments でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- NetDocuments では、**SP** によって開始される SSO がサポートされます

### ギャラリーからの NetDocuments の追加

Microsoft Entra ID への NetDocuments の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に NetDocuments を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**NetDocuments**」と入力します。
4. 結果のパネルから **[NetDocuments]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### NetDocuments 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、NetDocuments に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと NetDocuments の関連ユーザーとの間にリンク関係を確立する必要があります。

NetDocuments に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **NetDocuments の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **NetDocuments テストユーザーを作成する - NetDocuments の B.Simon のカウンターパートを作成し、Microsoft Entra ユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**NetDocuments**&gt;**シングル サインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 **[サインオン URL]** ボックスに、次のいずれかの URL パターンを入力します。

    | サインオン URL |
    | --- |
    | `https://vault.netvoyage.com/neWeb2/docCent.aspx?whr=<Repository ID>` |
    | `https://eu.netdocuments.com/neWeb2/docCent.aspx?whr=<Repository ID>` |
    | `https://de.netdocuments.com/neWeb2/docCent.aspx?whr=<Repository ID>` |
    | `https://au.netdocuments.com/neWeb2/docCent.aspx?whr=<Repository ID>` |
    |  |

    b。 **[識別子 (エンティティ ID)]** ボックスに、いずれかの URL を入力します。

    | 識別子 |
    | --- |
    | `http://netdocuments.com/VAULT` |
    | `http://netdocuments.com/EU` |
    | `http://netdocuments.com/AU` |
    | `http://netdocuments.com/DE` |
    |  |

    c. **[応答 URL]** ボックスに、次のいずれかの URL パターンを入力します。

    | [応答 URL] |
    | --- |
    | `https://vault.netvoyage.com/neWeb2/docCent.aspx?whr=<Repository ID>` |
    | `https://eu.netdocuments.com/neWeb2/docCent.aspx?whr=<Repository ID>` |
    | `https://de.netdocuments.com/neWeb2/docCent.aspx?whr=<Repository ID>` |
    | `https://au.netdocuments.com/neWeb2/docCent.aspx?whr=<Repository ID>` |
    |  |

    注意

    これらの値は実際の値ではありません。 これらの値を、実際のサインオン URL および応答 URL で更新してください。 リポジトリ ID は、**CA-** で始まり、その後に NetDocuments リポジトリに関連付けられている 8 文字のコードが続く値です。 詳細については、[NetDocuments Federated Identity サポート ドキュメント](https://netdocuments.force.com/NetDocumentsSupport/s/article/205220410)を参照してください。 また、上の情報を使用して構成することが難しい場合は、[NetDocuments クライアント サポート チーム](https://netdocuments.force.com/NetDocumentsSupport/s/)に連絡して、これらの値を取得してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. NetDocuments アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 NetDocuments アプリケーションでは、 **nameidentifier** が **ObjectID** または組織に **nameidentifier** として適用されるその他の要求にマップされることを想定しているため、 **編集** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[アプリのフェデレーション メタデータ URL]** を検索し、URL をコピーします。

    [Image: 証明書のダウンロードのリンク]
8. **[NetDocuments のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### NetDocuments の SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として NetDocuments 企業サイトにサインインします。
2. 右上隅から自分の名前を選択し、&gt; を選択します。
3. **[Security Center]**を選択します。

    [Image: セキュリティセンター]
4. **[Advanced Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/高度な認証)** を選択します。

    [Image: 認証オプションの詳細な構成]
5. **[フェデレーション ID]** タブで次の手順に従います。

    [Image: フェデレーション ID]

    1. **[フェデレーション ID のサーバーの種類]** で、 **[Windows Azure Active Directory]** を選択します。
    2. **[ファイルの選択]** を選択して、以前にダウンロードしたメタデータ ファイルをアップロードします。
    3. **[保存]** を選択します。

#### NetDocuments のテスト ユーザーの作成

Microsoft Entra ユーザーが NetDocuments にサインインできるようにするには、そのユーザーを NetDocuments にプロビジョニングする必要があります。 NetDocuments の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. **NetDocuments** 企業サイトに管理者としてサインオンします。
2. 右上隅から自分の名前を選択し、&gt; を選択します。

    [Image: 管理者]
3. **[ユーザーとグループ]** を選択します。

    [Image: ユーザーとグループ]
4. [ **電子メール アドレス]** ボックスに、プロビジョニングする有効な Microsoft Entra アカウントのメール アドレスを入力し、[ **ユーザーの追加**] を選択します。

    [Image: メール アドレス]

    注意

    Microsoft Entra アカウント所有者は、アカウントがアクティブになる前にアカウントを確認するためのリンクを含む電子メールを受け取ります。 他の NetDocuments ユーザー アカウントの作成ツールまたは NetDocuments から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる NetDocuments のサインオン URL にリダイレクトされます。
- NetDocuments のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [NetDocuments] タイルを選択すると、SSO を設定した NetDocuments に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netmotion-mobility-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に NetMotion Mobility を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netmotion-mobility-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と NetMotion Mobility の間でシングル サインオンを構成する方法について説明します。

この記事では、NetMotion Mobility と Microsoft Entra ID を統合する方法について説明します。 NetMotion Mobility と Microsoft Entra ID を統合すると、次のことができます。

- NetMotion Mobility にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して NetMotion Mobility クライアントでサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- NetMotion Mobility 12.50 以降。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra ID でアプリケーションを追加または管理することもできます。 詳細については、Azure 組み込みロール に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- NetMotion Mobility は、**SP** が開始したSSOをサポートします。
- NetMotion Mobility では、**ジャスト・イン・タイム** ユーザー プロビジョニングがサポートされます。

### ギャラリーから NetMotion Mobility を追加する

Microsoft Entra ID への NetMotion Mobility の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に NetMotion Mobility を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリー **から追加する**] セクションで、検索ボックス **「NetMotion Mobility**」と入力します。
4. 結果パネル **NetMotion Mobility** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### NetMotion Mobility の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、NetMotion Mobility に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと NetMotion Mobility の関連ユーザーとの間にリンク関係を確立する必要があります。

NetMotion Mobility に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **SAML ベースの認証** のモビリティを構成する - エンド ユーザーが Microsoft Entra 資格情報を使用して認証できるようにします。
2. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
3. **NetMotion Mobility SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **NetMotion Mobility のテストユーザーを作成 - NetMotion Mobility で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。**
4. **モビリティ クライアント** を使用して SAML ベースのユーザー認証をテストし、構成が機能するかどうかを確認します。

### SAML ベースの認証用にモビリティを構成する

モビリティ コンソールで、[モビリティ管理者ガイド](https://help.netmotionsoftware.com/support/docs/MobilityXG/1250/help/mobilityhelp.htm#page/Mobility%2520Server%2Fintro.01.01.html%23) の手順に従って、次の手順を実行します。

1. 一連のモビリティ ユーザーが SAML プロトコルを使用できるようにするには、SAML の [認証プロファイル](https://help.netmotionsoftware.com/support/docs/MobilityXG/1250/help/mobilityhelp.htm#page/Mobility%2520Server%2Fconfig.05.41.html%23ww2298330) を作成します。
2. モビリティ [で SAML ベースのユーザー認証](https://help.netmotionsoftware.com/support/docs/MobilityXG/1250/help/mobilityhelp.htm#context/nmcfgapp/saml_userconfig)を構成して、SP URL を設定し、後で Microsoft Entra ID にインポートする mobilitySPmetadata.xml ファイルを生成します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**NetMotion Mobility**&gt;**シングルサインオン**にアクセスします。
3. [**シングル サインオン方法の選択**] ページで、[SAML **]**を選択します。
4. [**SAML でのシングル サインオンの設定**] ページで、[**基本的な SAML 構成**] セクションのすぐ上にある **[メタデータ ファイルのアップロード**] を選択して、mobilitySPMetadata.xml ファイルを Microsoft Entra ID にインポートします。

    [Image: スクリーンショットでは、メタデータファイルを選択する方法が示されています。]
5. メタデータ ファイルをインポートした後、**基本的な SAML 構成** セクションで、次の手順を実行して、XML インポートが正常に完了したことを確認します。

    エー。 [**識別子** テキスト ボックスで、URL が次のパターンを使用していることを確認します。ここで、次の URL 例の変数はモビリティ サーバーの変数と一致します。`https://<YourMobilityServerName>.<CustomerDomain>.<tld>/`

    b。 [**応答 URL** テキスト ボックスで、URL が次のパターンを使用していることを確認します。`https://<YourMobilityServerName>.<CustomerDomain>.<tld>/saml/login`
6. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML  検索し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: スクリーンショットには、証明書]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### NetMotion Mobility SSO の構成

モビリティ コンソール [で IdP 設定を](https://help.netmotionsoftware.com/support/docs/MobilityXG/1250/help/mobilityhelp.htm#context/nmcfgapp/saml_userconfig)するためのモビリティ管理者ガイドの手順に従い、Microsoft Entra メタデータ ファイルをモビリティ サーバーにインポートし、IdP 構成の手順を完了します。

1. モビリティ認証設定を構成したら、デバイスまたはデバイス グループに割り当てます。
2. **モビリティ コンソール**&gt;&gt;**クライアント設定の構成** に移動し、SAML ベースの認証を使用する左側のデバイスまたはデバイス グループを選択します。
3. **認証 - 設定** プロファイルを選択し、ドロップダウン リストから作成した設定プロファイルを選択します。
4. [ **適用**] を選択すると、選択したデバイスまたはグループが既定以外の設定にサブスクライブされます。

#### NetMotion Mobility のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを NetMotion Mobility に作成します。 NetMotion Mobility では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 NetMotion Mobility にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### モビリティ クライアントを使用して SAML ベースのユーザー認証をテストする

このセクションでは、クライアント認証用に Microsoft Entra SAML 構成をテストします。

1. [モビリティ クライアントの構成](https://help.netmotionsoftware.com/support/docs/MobilityXG/1250/help/mobilityhelp.htm#page/Mobility%2520Server%2Fusing.06.01.html%23)のガイダンスに従い、SAML ベースの認証プロファイルが割り当てられているクライアント デバイスを構成して、SAML ベースの認証用に構成したモビリティ サーバー プールにアクセスし、接続を試みます。
2. テスト中に問題が発生した場合は、「モビリティ クライアント [のトラブルシューティング](https://help.netmotionsoftware.com/support/docs/MobilityXG/1250/help/mobilityhelp.htm#page/Mobility%2520Server%2Ftrouble.14.02.html)ガイダンスに従ってください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netop-portal-tutorial"} -->
## Microsoft Entra ID で Netop Portal for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netop-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Netop Portal 間にシングル サインオンを構成する方法について学習します。

この記事では、Netop Portal と Microsoft Entra ID を統合する方法について説明します。 Netop Portal を Microsoft Entra ID と統合すると、次のことができます:

- Netop Portal にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Netop Portal に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Netop ポータルは、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- Netop Portal でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Netop Portal は、**IDP** Initiated SSO をサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Netop Portal の追加

Microsoft Entra ID への Netop Portal の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Netop Portal を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Netop Portal**」と入力します。
4. 結果のパネルから **[Netop Portal]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Netop Portal 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Netop Portal に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Netop の関連ユーザーとの間にリンク関係を確立する必要があります。

Netop Portal で Microsoft Entra SSO を構成してテストするには、次の手順を行います:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Netop Portal の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Netop Portal のテストユーザーを作成** - Netop Portal で B.Simon に対応するユーザーを作成し、そのユーザーが Microsoft Entra の B.Simon の表現にリンクされるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. 以下に移動します：**Entra ID**&gt;**Enterprise apps**&gt;**Netop Portal**&gt;**シングルサインオン**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンのセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure で事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Netop Portal アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Netop Portal アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | NRC-ACCOUNT-ID | adfs-demo |
    | NRC-EMAIL | ユーザー.ユーザープリンシパルネーム |
    | NRC-GIVEN-NAME | ユーザー.ファーストネーム |
    | NRC-SURNAME | ユーザーの名字 |
    | NRC-USERNAME | ユーザー.ユーザープリンシパルネーム |
    | ネームアイデンティファイア | ユーザー.ユーザープリンシパルネーム |
    |  |  |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Netop Portal のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Netop Portal の SSO の構成

**Netop Portal** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と Azure portal で取得したログイン URL が必要です。 [こちらの](https://support.netop.com/support/solutions/articles/205000041622-adfs-and-azure-ad-integration)ドキュメントの手順 3 の指示に従って、Microsoft Entra 認証用に NetOp Portal を構成します。

#### Netop Portal のテスト ユーザーの作成

このセクションでは、Netop Portal で Britta Simon というユーザーを作成します。 [Netop Portal サポート チーム](mailto:support@netop.com)と連携して、Netop Portal プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Netop Portal に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Netop Portal] タイルを選択すると、SSO を設定した Netop Portal に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netpresenter-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Netpresenter Next を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netpresenter-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra ID から Netpresenter Next に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Netpresenter Next と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Netpresenter Next](https://www.Netpresenter.com/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Netpresenter Next でユーザーを作成する
- アクセスが不要になった場合に Netpresenter Next でユーザーを削除する
- Microsoft Entra ID と Netpresenter Next の間でユーザー属性の同期を維持する。
- Netpresenter Next への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Netpresenter Next の管理者アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Netpresenter Next の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Netpresenter Next を構成する

1. 管理者アカウントで Netpresenter Next にサインインします。
2. 歯車アイコンを選択して設定ページに移動します。
3. 設定ページで、[ **システム** ] を選択してサブメニューを開き、[ **Microsoft Entra ID**] を選択します。
4. [ **トークンの生成** ] ボタンを選択します。
5. **SCIM エンドポイント URL** と**トークン**を安全な場所に保存します。**手順 5** で必要になります。

    [Image: Netpresenter Next のトークンと URL の値を示すスクリーンショット。]
6. **省略可能:** **[サインイン オプション]** で、["Microsoft アカウントでサインイン" を強制する] を有効または無効にすることができます。 これを有効にすると、Microsoft Entra アカウントを持つユーザーは、自分のローカル アカウントでサインインできなくなります。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Netpresenter Next を追加する

Microsoft Entra アプリケーション ギャラリーから Netpresenter Next を追加して、Netpresenter Next へのプロビジョニングの管理を開始します。 以前に Netpresenter Next を SSO 向けに設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Netpresenter Next への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Netpresenter Next の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する
3. アプリケーションの一覧で **Netpresenter Next** を選択します。
4. **[プロビジョニング]** タブを選択します。

    [Image: アプリケーション設定の [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Netpresenter の次のテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Netpresenter Next に接続できることを確認します。 接続に失敗した場合は、Netpresenter Next アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Netpresenter Next に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Netpresenter Next のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Netpresenter Next API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Netpresenter Next で必須かどうか |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 | ✓ | ✓ |
    | emails[type eq "work"].value | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netsfere-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に NetSfere を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netsfere-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と NetSfere の間でシングル サインオンを構成する方法について説明します。

この記事では、NetSfere と Microsoft Entra ID を統合する方法について説明します。 NetSfere と Microsoft Entra ID を統合すると、次のことができます。

- NetSfere へのアクセス権を持つユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して NetSfere に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- NetSfere でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- NetSfere は、**SP と IDP**によって開始される SSO をサポートします。

### ギャラリーからの NetSfere の追加

Microsoft Entra ID への NetSfere の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に NetSfere を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「NetSfere**」と入力します。
4. 結果パネルから **[NetSfere** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### NetSfere の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、NetSfere に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと NetSfere の関連ユーザーとの間にリンク関係を確立する必要があります。

NetSfere で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **NetSfere SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **NetSfere のためのテストユーザーを作成して、Microsoft Entra のユーザー表現で B.Simon とリンクさせる。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**NetSfere**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `spn:<NetSfere_ID>` |
    | `https://<SUBDOMAIN>.netsfere.com/saml/module.php/saml/sp/metadata.php/default-sp` |
    | `https://<SUBDOMAIN>.netsferetest.com/saml/module.php/saml/sp/metadata.php/default-sp` |
    | `https://<SUBDOMAIN>.netsferedev.com/saml/module.php/saml/sp/metadata.php/default-sp` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<SUBDOMAIN>.netsfere.com/saml/module.php/saml/sp/saml2-acs.php/default-sp` |
    | `https://<SUBDOMAIN>.netsferetest.com/saml/module.php/saml/sp/saml2-acs.php/default-sp` |
    | `https://<SUBDOMAIN>.netsferedev.com/saml/module.php/saml/sp/saml2-acs.php/default-sp` |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<SUBDOMAIN>.netsfere.com` |
    | `https://<SUBDOMAIN>.netsferetest.com` |
    | `https://<SUBDOMAIN>.netsferedev.com` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [NetSfere サポート チーム](mailto:support@netsfere.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. NetSfere アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. 上記に加えて、NetSfere アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ロール | user.assignedroles |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### NetSfere SSO の構成

1. NetSfere 企業サイトに管理者としてログインします。
2. **[設定] (歯車アイコン)**&gt;**Identity Providers** に移動します。

    [Image: 構成の設定を示すスクリーンショット。]
3. [ **Add Identity Provider metadata URL]\(ID プロバイダー メタデータ URL の追加** \) ボックスに、Microsoft Admin Center からコピーした **アプリのフェデレーション メタデータ URL を**貼り付けます。
4. [ **ID プロバイダーの追加] を選択します**。

#### NetSfere テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、NetSfere Web サイトに管理者としてサインインします。
2. [ **ユーザーとグループ**&gt;**アクティブなユーザー** ] に移動し、[ **ユーザーの追加]** を選択します。

    [Image: スクリーンショットは、アプリケーションでユーザーを作成する方法を示しています。]
3. [ **ユーザーの招待** ] セクションで、次の手順を実行します。

    [Image: ページで新しいユーザーを作成する方法を示すスクリーンショット。]

    1. 「**名前**」テキストボックスに、ユーザーの有効な名前を入力します。
    2. **[Email] (電子メール)** テキストボックスに、ユーザーの有効なメール アドレスを入力します。
    3. 組織の要件に従って、ユーザーの **ロール** を選択します。
    4. [ **招待**] を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる NetSfere のサインオン URL にリダイレクトします。
- NetSfere のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した NetSfere に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [NetSfere] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した NetSfere に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netskope-administrator-console-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Netskope User Authentication を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netskope-administrator-console-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-29
- Summary: Microsoft Entra ID を構成して、ユーザー アカウントを Netskope User Authentication に自動的にプロビジョニング/プロビジョニング解除する方法を説明します。

この記事の目的は、Netskope User Authentication と Microsoft Entra ID で実行する手順を示して、Netskope User Authentication に対してユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Netskope User Authentication テナント](https://www.netskope.com/)
- 管理者アクセス許可を持つ Netskope User Authentication のユーザー アカウント。

### Netskope User Authentication へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に*割り当て*という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Netskope User Authentication へのアクセスが必要な Microsoft Entra ID 内のユーザーやグループを決定しておく必要があります。 決定したら、次の手順に従って、これらのユーザーやグループを Netskope User Authentication に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Netskope User Authentication に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを Netskope User Authentication に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Netskope User Authentication にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### プロビジョニングのための Netskope User Authentication の設定

1. [Netskope User Authentication Admin Console](https://netskope.goskope.com/) にサインインします。
2. **ホーム -&gt; 設定 -&gt; 管理 -&gt;管理者**と**ロール -&gt; サービス アカウント**に移動します。
3. 必要な詳細を入力し、種類として **OAuth2** を選択し、有効期間 (日数) を設定します。
4. [ **作成**] をクリックし、生成された **クライアント ID** と **クライアント シークレット** を後で必要に応じてコピーします。

    [Image: Oauth 構成を示すスクリーンショット。]

### ギャラリーから Netskope User Authentication を追加する

Microsoft Entra ID での自動ユーザー プロビジョニング用に Netskope User Authentication を構成する前に、Microsoft Entra ID アプリケーション ギャラリーから Netskope User Authentication をマネージド SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Netskope User Authentication を追加するには、次の手順を実行します。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Netskope User Authentication**」と入力し、検索欄に **Netskope User Authentication** を選択します。
4. 結果のパネルから **[Netskope User Authentication]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の Netskope User Authentication のスクリーンショット。]

### Netskope User Authentication への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Netskope User Authentication でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Netskope User Authentication のシングル サインオンに関する記事で説明されている手順に従って、 [Netskope User Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netskope-cloud-security-tutorial) で SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

注

Netskope User Authentication の SCIM エンドポイントの詳細については、[こちら](https://docs.google.com/document/d/1n9P_TL98_kd1sx5PAvZL2HS6MQAqkQqd-OSkWAAU6ck/edit#heading=h.prxq74iwdpon)を参照してください。

#### Microsoft Entra ID で Netskope User Authentication の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で、 **[Netskope User Authentication]** を選択します。

    [Image: アプリケーションの一覧の Netskope User Authentication リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. 認証方法として **OAuth2 クライアント資格情報の付与** を選択します。

    a. Netskope から取得した **クライアント ID** と **クライアント シークレット** を入力します。

    b. [ **テスト接続]** を選択して、Microsoft Entra ID が Netskope User Authentication に接続できることを確認します。

    c. 接続できない場合は、使用中の Netskope User Authentication アカウントに管理者アクセス許可があることを確認してから、再試行します。

    [Image: トークンのスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Netskope User Authentication に同期されるユーザー属性を確認します。 **一致する**プロパティとして選択されている属性は、更新処理で Netskope User Authentication のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Netskope User Authentication API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Netskope User Authentication User Attributes のスクリーンショット。]
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Netskope User Authentication に同期されるグループ属性を確認します。 **一致する**プロパティとして選択されている属性は、更新処理で Netskope User Authentication のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Netskope User Authentication Group Attributes のスクリーンショット。]
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netskope-cloud-exchange-administration-console-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Netskope Cloud Exchange 管理コンソールを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netskope-cloud-exchange-administration-console-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Netskope Cloud Exchange Administration Console との間のシングル サインオンを構成する方法について説明します。

この記事では、Netskope Cloud Exchange Administration Console と Microsoft Entra ID を統合する方法について説明します。 Netskope Cloud Exchange (CE) は、セキュリティと IT スタック全体で投資を活用するための強力な統合機能を顧客に提供します。 Netskope Cloud Exchange Administration Console を Microsoft Entra ID と統合すると、次のことができます。

- Netskope Cloud Exchange Administration Console にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Netskope Cloud Exchange Administration Console に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Netskope Cloud Exchange Administration Console 向けの Microsoft Entra シングル サインオンを構成してテストします。 Netskope Cloud Exchange 管理コンソールでは、 **SP** によって開始されるシングル サインオンがサポートされます。

### [前提条件]

Microsoft Entra ID を Netskope Cloud Exchange Administration Console と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Netskope Cloud Exchange Administration Console でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Netskope Cloud Exchange Administration Console アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Netskope Cloud Exchange Administration Console を追加する

Microsoft Entra アプリケーション ギャラリーから Netskope Cloud Exchange Administration Console を追加して、Netskope Cloud Exchange Administration Console でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Netskope Cloud Exchange Administration Console**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    エー。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Cloud_Exchange_FQDN>/api/metadata`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Cloud_Exchange_FQDN>/api/ssoauth?acs=true`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Cloud_Exchange_FQDN>/login`

    注

    これらの値は実際の値ではありません。 クラウド交換のデプロイに基づいて、実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 また、 [Netskope Cloud Exchange Administration Console のサポート チーム](mailto:support@netskope.com) に連絡して、これらの値を決定するためのヘルプを受け取ることもできます。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Netskope Cloud Exchange Administration Console アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Netskope Cloud Exchange Administration Console アプリケーションは、いくつかの属性が SAML 応答で返されることを想定しています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユーザー名 | ユーザーのメールアドレス |
    | 役割 | user.assignedroles |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **Netskope Cloud Exchange 管理コンソールのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Netskope Cloud Exchange Administration Console SSO を構成する

**Netskope Cloud Exchange 管理コンソール**側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Netskope Cloud Exchange 管理コンソール サポート チーム](mailto:support@netskope.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Netskope Cloud Exchange Administration Console のテスト ユーザーを作成する

このセクションでは、Netskope Cloud Exchange Administration Console SSO で Britta Simon というユーザーを作成します。 [Netskope Cloud Exchange Administration Console サポート チーム](mailto:support@netskope.com)と協力して、Netskope Cloud Exchange Administration Console SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Netskope Cloud Exchange 管理コンソールのサインオン URL にリダイレクトされます。
- Netskope Cloud Exchange Administration Console のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Netskope Cloud Exchange 管理コンソール] タイルを選択すると、このオプションは Netskope Cloud Exchange 管理コンソールのサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netskope-cloud-security-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Netskope Administrator Console を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netskope-cloud-security-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Netskope Administrator Console の間でシングル サインオンを構成する方法について説明します。

この記事では、Netskope Administrator Console と Microsoft Entra ID を統合する方法について説明します。 Netskope Administrator Console と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID において、誰が Netskope Administrator Console にアクセスできるかを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Netskope Administrator Console に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Netskope Administrator Console でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Netskope Administrator Console では、 **SP Initiated SSO と IDP** Initiated SSO がサポートされます。
- Netskope Administrator Console では、Just-In-Time ユーザー プロビジョニングがサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Netskope Administrator Console の追加

Microsoft Entra ID への Netskope Administrator Console の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Netskope Administrator Console を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Netskope Administrator Console**」と入力します。
4. 結果パネルから **Netskope Administrator Console** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Netskope Administrator Console の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Netskope Administrator Console に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Netskope Administrator Console の関連ユーザーとの間にリンク関係を確立する必要があります。

Netskope Administrator Console で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Netskope Administrator Console の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Netskope Administrator Console のテストユーザーを作成して、Microsoft Entra のユーザー表現にリンクする B.Simon 相当のユーザーを作成します**。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Netskope Administrator Console**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant_host_name>/saml/acs`

    手記

    これは実際の値ではありません。 実際の応答 URL で値を更新します。 この記事の後半で説明する値を取得します。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantname>.goskope.com`

    手記

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL でサインオン URL の値を更新します。 サインオン URL の値を取得するには、 [Netskope Administrator Console クライアント サポート チーム](mailto:support@netskope.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Netskope Administrator Console アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. 上記に加えて、Netskope Administrator Console アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 管理者役割 | user.assignedroles |

    手記

    Microsoft Entra ID でロールを作成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Netskope Administrator Console のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Netskope Administrator Console の SSO の構成

1. ブラウザーで新しいタブを開き、Netskope Administrator Console 企業サイトに管理者としてサインインします。
2. 左側のナビゲーション ウィンドウから **[設定]** タブを選択します。

    [Image: ナビゲーション ウィンドウで選択されている [設定] を示すスクリーンショット。]
3. [ **管理** ] タブを選択します。

    [Image: [設定] で [管理] が選択されているスクリーンショット。]
4. [ **SSO** ] タブを選択します。

    [Image: スクリーンショットは、[管理] で選択された S S O を示しています。]
5. [ **ネットワーク設定]** セクションで、次の手順を実行します。

    [Image: 説明されている値を入力できる [ネットワーク設定] を示すスクリーンショット。]

    ある。 **Assertion Consumer Service の URL 値を**コピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。

    b。 **サービス プロバイダー エンティティ ID の値を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子**] ボックスに貼り付けます。
6. **[SSO/SLO 設定]** セクションで [**設定の編集**] を選択します。

    [Image: スクリーンショットには、[設定の編集] を選択できる S S O/S L O 設定が示されています。]
7. **[設定]** ポップアップ ウィンドウで、次の手順を実行します。

    [Image: 説明されている値を入力できる [設定] ダイアログ ボックスを示すスクリーンショット。]

    ある。 [ **SSO を有効にする] を選択します**。

    b。 **[IDP URL**] ボックスに、先にコピーした**ログイン URL** の値を貼り付けます。

    c. **[IDP ENTITY ID**] ボックスに、前にコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    d. ダウンロードした Base64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、 **IDP CERTIFICATE** テキストボックスに貼り付けます。

    え [ **SSO を有効にする] を選択します**。

    f. **[IDP SLO URL**] ボックスに、前にコピーした**ログアウト URL** の値を貼り付けます。

    ジー **[送信]**を選択します。

#### Netskope Administrator Console のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Netskope Administrator Console に作成します。 Netskope Administrator Console では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Netskope Administrator Console にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Netskope Administrator Console のサインオン URL にリダイレクトされます。
- Netskope Administrator Console のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDPが開始されました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Netskope Administrator Console に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Netskope Administrator Console] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Netskope Administrator Console に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netskope-user-authentication-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Netskope User Authentication を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netskope-user-authentication-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Netskope User Authentication の間でシングル サインオンを構成する方法について説明します。

この記事では、Netskope User Authentication と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Netskope User Authentication を統合すると、次のことが可能になります。

- Netskope User Authentication にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで Netskope User Authentication に自動的にサインインするように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Netskope User Authentication でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Netskope ユーザー認証では、**SP および IDP** による SSO がサポートされます。

### ギャラリーから Netskope User Authentication を追加する

Microsoft Entra ID への Netskope User Authentication の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Netskope User Authentication を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Netskope User Authentication**」と入力します。
4. 結果パネルから **[Netskope User Authentication** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Netskope User Authentication の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Netskope User Authentication に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Netskope User Authentication の関連ユーザーとの間にリンク関係を確立する必要があります。

Netskope User Authentication で Microsoft Entra SSO を構成してテストするには、次の構成要素を完了する必要があります。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Netskope User Authentication の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **テストユーザーを作成して、Netskope User Authentication における B.Simon に相当し、Microsoft Entra にリンクされているユーザーを登録します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Netskope User Authentication**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantname>.goskope.com/<customer entered string>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantname>.goskope.com/nsauth/saml2/http-post/<customer entered string>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値については、この記事の後半で説明します。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantname>.goskope.com`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL でサインオン URL の値を更新します。 サインオン URL の値を取得するには、 [Netskope User Authentication クライアント サポート チーム](mailto:support@netskope.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Netskope User Authentication のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Netskope User Authentication SSO を構成する

1. ブラウザーで新しいタブを開き、Netskope User Authentication の会社のサイトに管理者としてサインインします。
2. [ **アクティブ プラットフォーム** ] タブを選択します。

    [Image: [設定] で選択されている [アクティブ プラットフォーム] を示すスクリーンショット。]
3. [ **FORWARD PROXY** ] まで下にスクロールし、[SAML] を選択 **します**。

    [Image: [Active Platform](アクティブ プラットフォーム) から選択された SAML を示すスクリーンショット。]
4. [ **SAML 設定]** ページで、次の手順を実行します。

    [Image: [SAML Settings](SAML 設定) を示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **SAML エンティティ ID の値を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子**] ボックスに貼り付けます。

    b。 **SAML ACS URL** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。
5. [ **アカウントの追加] を選択します**。

    [Image: [SAML] ウィンドウで [アカウントの追加] が選択されているスクリーンショット。]
6. [ **SAML アカウントの追加]** ページで、次の手順を実行します。

    [Image: 説明されている値を入力できる SAML アカウントの追加を示すスクリーンショット。]

    ある。 **[名前**] ボックスに、Microsoft Entra ID などの名前を指定します。

    b。 **[IDP URL**] ボックスに、先にコピーした**ログイン URL** の値を貼り付けます。

    c. **[IDP ENTITY ID**] ボックスに、前にコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    d. ダウンロードしたメタデータ ファイルをメモ帳で開き、その内容をクリップボードにコピーして、 **IDP CERTIFICATE** テキストボックスに貼り付けます。

    え **[保存] を選択します**。

#### Netskope User Authentication テスト ユーザーを作成する

1. ブラウザーで新しいタブを開き、Netskope User Authentication の会社のサイトに管理者としてサインインします。
2. 左側のナビゲーション ウィンドウから **[設定]** タブを選択します。

    [Image: [設定] が選択されているスクリーンショット。]
3. [ **アクティブ プラットフォーム** ] タブを選択します。

    [Image: [設定] で選択されている [アクティブ プラットフォーム] を示すスクリーンショット。]
4. [ **ユーザー** ] タブを選択します。

    [Image: スクリーンショットは、アクティブ プラットフォームから選択されたユーザーを示しています。]
5. [ **ユーザーの追加] を選択します**。

    [Image: [ユーザー] ダイアログ ボックスを示すスクリーンショット。[ユーザーの追加] を選択できます。]
6. 追加するユーザーのメール アドレスを入力し、[ **追加**] を選択します。

    [Image: スクリーンショットは、ユーザーの一覧を入力できる [ユーザーの追加] を示しています。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Netskope User Authentication のサインオン URL にリダイレクトされます。
- Netskope User Authentication のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Netskope User Authentication に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Netskope User Authentication] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Netskope User Authentication に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netsparker-enterprise-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Netsparker Enterprise を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netsparker-enterprise-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-17
- Summary: Microsoft Entra ID から Netsparker Enterprise に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法についてご確認ください。

この記事では、自動ユーザー プロビジョニングを構成するために Netsparker Enterprise と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Netsparker Enterprise](https://www.netsparker.com/product/enterprise/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Netsparker Enterprise でユーザーを作成します。
- アクセスが不要になったら、Netsparker Enterprise のユーザーを削除します。
- Microsoft Entra ID と Netsparker Enterprise の間でユーザー属性の同期を維持します。
- Netsparker Enterprise でグループとグループ メンバーシップをプロビジョニングします。
- Netsparker Enterprise に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netsparker-enterprise-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Netsparker Enterprise の管理者アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Netsparker Enterprise の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID でのプロビジョニングをサポートするように Netsparker Enterprise を構成する

1. [Netsparker Enterprise 管理コンソール](https://www.netsparkercloud.com)にログインします。
2. プロファイル ロゴを選択し **、[API 設定]** に移動します。
3. **現在のパスワード**を入力し、[**送信]** を選択します。
4. **トークン**をコピーして保存します。この値は、Netsparker Enterprise アプリケーションの [プロビジョニング] タブの [**シークレット トークン**] フィールドに入力されます。
    注

    **トークンをリセットするには、[API トークン**のリセット] を選択します。
5. `https://www.netsparkercloud.com/scim/v2`は、Netsparker Enterprise アプリケーションの [プロビジョニング] タブの [**テナント URL**] フィールドに入力します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Netsparker Enterprise を追加する

Microsoft Entra アプリケーション ギャラリーから Netsparker Enterprise を追加して、Netsparker Enterprise へのプロビジョニングの管理を開始します。 以前に Netsparker Enterprise を SSO 用に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Netsparker Enterprise への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Netsparker Enterprise の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Netsparker Enterprise]** を選択します。

    [Image: アプリケーション一覧の Netsparker Enterprise リンクを示すスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Netsparker Enterprise テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Netsparker Enterprise に接続できることを確認します。 接続に失敗した場合は、Netsparker Enterprise アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで Microsoft Entra ID から Netsparker Enterprise に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Netsparker Enterprise のユーザー アカウントとの照合に使用されます。 [照合する対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づいたユーザーのフィルター処理が Netsparker Enterprise API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Netsparker Enterprise で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | 名前.名 | 糸 |  | ✓ |
    | 名前.姓 | 糸 |  | ✓ |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで Microsoft Entra ID から Netsparker Enterprise に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Netsparker Enterprise のグループの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Netsparker Enterprise で必要 |
    | --- | --- | --- | --- |
    | ディスプレイ名 | 糸 | ✓ | ✓ |
    | メンバー | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netsparker-enterprise-tutorial"} -->
## Microsoft Entra ID で Invicti for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netsparker-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Invicti の間のシングル サインオンを構成する方法について説明します。

この記事では、Invicti と Microsoft Entra ID を統合する方法について説明します。 Invicti を Microsoft Entra ID と統合すると、次のことが可能になります。

- Invicti にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Invicti に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Invicti のシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Invicti では、**SPおよびIDP**によるSSOがサポートされます。
- Invicti では、 **Just In Time** ユーザー プロビジョニングがサポートされます。
- Invicti では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netsparker-enterprise-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Invicti の追加

Microsoft Entra ID への Invicti の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Invicti を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Invicti**」と入力します。
4. 結果パネルから **[Invicti** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Invicti 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Invicti に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Invicti の関連ユーザーとの間にリンク関係を確立する必要があります。

Invicti に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Invicti SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Invicti のテストユーザーを作成** - Microsoft Entra のユーザー表現とリンクされている B.Simon に対応するユーザーを Invicti に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Invicti**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.netsparkercloud.com/account/assertionconsumerservice/?spId=<SPID>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.netsparkercloud.com/account/ssosignin/`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL で値を更新します。 これらの値を取得するには、 [Invicti クライアント サポート チーム](mailto:support@netsparker.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Invicti のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Invicti SSO の構成

1. Invicti に管理者としてログインします。
2. **[設定] &gt;シングル サインオンに移動します**。
3. **[シングル サインオン**] ウィンドウで、[**Microsoft Entra ID**] タブを選択します。
4. 次のページで、以下の手順を実行します。

    [Image: [Microsoft Entra ID] タブ]

    a. **[識別子**] の値をコピーし、[**基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスにこの値を貼り付けます。

    b。 **SAML 2.0 サービス URL** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。

    c. **IdP 識別子**フィールドに識別子の値を貼り付けます。

    d. **SAML 2.0 エンドポイント** フィールドに**応答 URL** 値を貼り付けます。

    e. ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容を **x.509 証明書** ボックスに貼り付けます。

    f. **[自動プロビジョニングを有効にする]** をオンにし、**必要に応じて SAML アサーションを暗号化する必要があります**。

    g. [ **変更の保存] を選択します**。

#### Invicti テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Invicti に作成します。 Invicti では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Invicti にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Invicti のサインオン URL にリダイレクトされます。
- Invicti のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Invicti に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Invicti] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Invicti に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netsuite-tutorial"} -->
## Microsoft Entra ID で NetSuite for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netsuite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と NetSuite 間にシングル サインオンを構成する方法について説明します。

この記事では、NetSuite と Microsoft Entra ID を統合する方法について説明します。 NetSuite を Microsoft Entra ID を統合すると、次のことができます。

- NetSuite にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って NetSuite に自動的にサインインできるように設定できます。
- 1 つの中央の場所 (Azure portal) でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- NetSuite でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

NetSuite では、以下がサポートされます。

- IDP-Initiated SSO。
- JIT (Just-In-Time) ユーザー プロビジョニング。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの NetSuite の追加

NetSuite の Microsoft Entra ID への統合を構成するには、次の手順を実行して、NetSuite をギャラリーからマネージド SaaS アプリの一覧に追加します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**NetSuite**」と入力します。
4. 結果ペインで、 **[NetSuite]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### NetSuite 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、NetSuite に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと NetSuite の関連ユーザーとの間にリンク関係を確立する必要があります。

NetSuite に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. ユーザーがこの機能を使用できるように Microsoft Entra SSO を構成します。
    - Microsoft Entra テスト ユーザーを作成して、ユーザー B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - Microsoft Entra テスト ユーザーを割り当てて、ユーザー B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. NetSuite SSO を構成して、アプリケーション側でシングル サインオン設定を構成します。
    - NetSuite のテスト ユーザーを作成して、NetSuite でユーザー B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. SSO をテスト して、構成が機能することを確認します。

### Microsoft Entra SSO の構成

Azure portal で Microsoft Entra SSO を有効にするには、次の操作を行います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**NetSuite** アプリケーション統合ページに移動し、[**管理**] セクションを探して、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ウィンドウで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ウィンドウで、 **[基本的な SAML 構成]** の横にある **[編集]** ("鉛筆") アイコンを選択します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションの **[応答 URL]** テキスト ボックスに、URL として「`https://system.netsuite.com/saml2/acs`」と入力します。
6. NetSuite アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. 上記に加えて、NetSuite アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | アカウント | `account id` |

    注

    account 属性の値は実際の値ではありません。 この値は、この記事の後半で説明するように更新します。ナビゲーション コントロール内の Netsuite のサンドボックスに運用からジャンプする機能を明示的にブロックする必要がない限り、アカウント ID は必要ありません。
8. [SAML によるシングル サインオンのセットアップ] ページの [SAML 署名証明書] セクションで、[フェデレーション メタデータ XML] を探して [ダウンロード] を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
9. **[NetSuite のセットアップ]** セクションで、実際の要件に応じて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### NetSuite の SSO の構成

1. ブラウザーで新しいタブを開き、NetSuite の会社のサイトに管理者としてサインインします。
2. 上部のナビゲーション バーで、 **[Setup](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セットアップ)** を選択し、 **[Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社)**&gt;**[Enable Features](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/機能の有効化)** を選択します。

    [Image: [会社] で選択した [機能を有効にする] を示すスクリーンショット。]
3. ページの中央にあるツール バーで、 **[SuiteCloud]** を選択します。

    [Image: [SuiteCloud] が選択されていることを示すスクリーンショット。]
4. **[Manage Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の管理)** で、 **[SAML Single Sign-on](SAML シングル サインオン)** チェック ボックスをオンにして、NetSuite での SAML シングル サインオン オプションを有効にします。

    [Image: [認証を管理する] を示すスクリーンショット。ここでは、[SAML シングル サインオン] を選択できます。]
5. 上部のナビゲーション バーで、 **[Setup](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セットアップ)** を選択します。

    [Image: NETSUITE ナビゲーション バーで [セットアップ] が選択されていることを示すスクリーンショット。]
6. **[Setup Tasks](セットアップ タスク)** 一覧で、 **[Integration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合)** を選択します。

    [Image: [セットアップ タスク] で [統合] が選択されていることを示すスクリーンショット。]
7. **[Manage Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の管理)** で、 **[SAML Single Sign-on](SAML シングル サインオン)** を選択します。

    [Image: [セットアップ タスク] の [統合] 項目で [SAML シングル サインオン] が選択されていることを示すスクリーンショット。]
8. **[SAML Setup](SAML セットアップ)** ウィンドウの **[NetSuite Configuration](NetSuite の構成)** で、以下を実行します。

    [Image: [SAML 設定] を示すスクリーンショット。ここでは、説明されている値を入力できます。]

    ある。 **[Primary Authentication Method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プライマリ認証方法)** チェック ボックスをオンにします。

    b。 **[SAMLV2 ID プロバイダー メタデータ]** で、**[IDP メタデータ ファイルのアップロード]** を選択し、次に **[参照]** を選択して、ダウンロードしたメタデータ ファイルをアップロードします。

    c. **送信**を選択します。
9. NetSuite の上部のナビゲーション バーで、 **[Setup](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セットアップ)** を選択し、 **[Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社)**&gt;**[Company Information](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社情報)** を選択します。

    [Image: [会社] で [会社情報] が選択されていることを示すスクリーンショット。]

    [Image: 説明されている値を入力できるウィンドウを示すスクリーンショット。]

    b。 **[Company Information](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社情報)** ウィンドウで、右側の列の **[Account ID](アカウント ID)** の値をコピーします。

    c. NetSuite アカウントからコピーした**アカウント ID** を Microsoft Entra ID の **[属性値]** ボックスに貼り付けます。

    [Image: アカウント ID の値を追加する画面のスクリーンショット]
10. ユーザーは NetSuite にシングル サインオンする前に、まず、NetSuite で適切なアクセス許可が割り当てられている必要があります。 これらのアクセス許可を割り当てるには、以下を実行します。

    ある。 上部のナビゲーション バーで、 **[Setup](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セットアップ)** を選択します。

    [Image: NETSUITE ナビゲーション バーで [セットアップ] が選択されていることを示すスクリーンショット。]

    b。 左側のウィンドウで、 **[Users/Roles](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーとロール)** を選択し、 **[Manage Roles](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ロールの管理)** を選択します。

    [Image: [ロールの管理] ウィンドウを示すスクリーンショット。ここでは、[新しいロール] を選択できます。]

    c. **[New Role](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいロール)** を選択します。

    d. 新しいロールの**名前**を入力します。

    [Image: [セットアップ マネージャー] を示すスクリーンショット。ここでは、ロールの名前を入力できます。]

    え **保存** を選択します。

    f. 上部のナビゲーション バーで、 **[Permissions](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス許可)** を選択します。 次に、 **[Setup](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セットアップ)** を選択します。

    [Image: [セットアップ] タブを示すスクリーンショット。ここでは、説明されている値を入力できます。]

    ジー **[SAML Single Sign-on](SAML シングル サインオン)** を選択し、 **[Add](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加)** を選択します。

    h. **保存** を選択します。

    一. 上部のナビゲーション バーで、 **[Setup](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セットアップ)** を選択し、 **[Setup Manager](セットアップ マネージャー)** を選択します。

    [Image: NETSUITE ナビゲーション バーで [セットアップ] が選択されていることを示すスクリーンショット。]

    j. 左側のウィンドウで、 **[Users/Roles](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーとロール)** を選択し、 **[Manage Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの管理)** を選択します。

    [Image: [ユーザーの管理] ウィンドウを示すスクリーンショット。ここでは、[Suite Demo Team] を選択できます。]

    ケー テスト ユーザーを選択します。 **[Edit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/編集)** を選択して、 **[Access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス)** タブを選択します。

    [Image: [ユーザーの管理] ウィンドウを示すスクリーンショット。ここでは、[編集] を選択できます。]

    l. **[Roles](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ロール)** ウィンドウで、作成した適切なロールを割り当てます。

    [Image: [従業員] で [管理者] が選択されていることを示すスクリーンショット。]

    m. **保存** を選択します。

#### NetSuite のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを NetSuite に作成します。 NetSuite では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 NetSuite にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した NetSuite に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [NetSuite] タイルを選択すると、SSO を設定した NetSuite に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/netvision-compas-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Netvision Compas を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/netvision-compas-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Netvision Compas の間のシングル サインオンを構成する方法について説明します。

この記事では、Netvision Compas と Microsoft Entra ID を統合する方法について説明します。 Netvision Compas を Microsoft Entra ID と統合すると、以下のことが可能になります。

- Netvision Compas にだれがアクセスできるかを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Netvision Compas に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、[Microsoft Entra ID を使ったアプリケーション アクセスとシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)に関する記事を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Netvision Compas でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Netvision Compas では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Netvision Compas を構成したら、組織の機密データの流出と侵入をリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスを拡張したものです。 [Microsoft Defender for Cloud Apps でセッション制御を強制する方法](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-aad)をご覧ください。

### ギャラリーからの Netvision Compas の追加

Microsoft Entra ID への Netvision Compas の統合を構成するには、Netvision Compas をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Netvision Compas**」と入力します。
4. 結果のパネルから **[Netvision Compas]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Netvision Compas の Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、Netvision Compas で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Netvision Compas の関連ユーザーとの間にリンク関係を確立する必要があります。

Netvision Compas で Microsoft Entra SSO を構成してテストするには、以下の構成要素を完了します。

1. **Microsoft Entra SSO の構成**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Netvision Compas の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Netvision Compas テストユーザーを構成する** - Netvision Compas で B.Simon に相当するユーザーを作成し、それを Microsoft Entra ユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Netvision Compas**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    ある。 **[識別子]** ボックスに、`https://<TENANT>.compas.cloud/Identity/Saml20` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<TENANT>.compas.cloud/Identity/Auth/AssertionConsumerService` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<TENANT>.compas.cloud/Identity/Auth/AssertionConsumerService` という形式で URL を入力します。

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Netvision Compas クライアント サポート チーム](mailto:contact@net.vision)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[フェデレーション メタデータ XML]** を見つけて **[ダウンロード]** を選択し、メタデータ ファイルをダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Netvision Compas の SSO の構成

このセクションでは、**Netvision Compas** の SAML SSO を有効にします。

1. 管理者アカウントを使用して **Netvision Compas** にログインし、管理領域にアクセスします。

    [Image: 管理領域]
2. **[Syetem](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/システム)** 領域を見つけて、 **[Identity Providers](ID プロバイダー)** を選択します。

    [Image: IDP の管理]
3. **[追加]** アクションを選択して Microsoft Entra ID を新しい IDP として登録します。

    [Image: IDP の追加]
4. **[Provider type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロバイダーの種類)** で **[SAML]** を選択します。
5. **[Display name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/表示名)** と **[Description](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/説明)** フィールドに、わかりやすい値を入力します。
6. **Netvision Compas** ユーザーを IDP に割り当てるには、 **[Available users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/使用可能なユーザー)** 一覧から選択し、 **[Add selected](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/選択項目の追加)** ボタンを選択します。 プロビジョニングの手順に従って、ユーザーを IDP に割り当てることもできます。
7. [ **メタデータ** SAML] オプションで、[ **ファイルの選択** ] ボタンを選択し、以前にコンピューターに保存したメタデータ ファイルを選択します。
8. **保存** を選択します。

    [Image: IDP の編集]

#### Netvision Compas のテスト ユーザーの構成

このセクションでは、SSO に Microsoft Entra ID を使用するように **Netvision Compas** の既存ユーザーを構成します。

1. 自社で定義している **Netvision Compas** ユーザー プロビジョニング手順に従います。または、既存のユーザー アカウントを編集します。
2. ユーザーのプロファイルを定義するときに、ユーザーの**メール (個人用)** アドレスが Microsoft Entra ユーザー名の username@companydomain.extension と一致するようにします。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。

    [Image: [Edit user]]

シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、Microsoft Entra のシングル サインオン構成をテストします。

#### アクセス パネルを使用する (IDP 開始)

アクセス パネルで [Netvision Compas] タイルを選択すると、SSO を設定した Netvision Compas に自動的にサインインします。 アクセス パネルの詳細については、[アクセス パネルの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関する記事を参照してください。

#### Netvision Compas に直接アクセスする (SP 開始)

1. **Netvision Compas** URL にアクセスします。 たとえば、「 `https://tenant.compas.cloud` 」のように入力します。
2. **Netvision Compas** ユーザー名を入力し、 **[Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ)** を選択します。

    [Image: ログイン ユーザー]
3. **(省略可能)** ユーザーが **Netvision Compas** 内の複数の IDP を割り当てられている場合は、使用可能な IDP の一覧が表示されます。 **Netvision Compas** で先ほど構成した Microsoft Entra IDP を選択します。
4. 認証を実行するために Microsoft Entra ID にリダイレクトされます。 認証が正常に完了すると、SSO を設定した **Netvision Compas** に自動的にサインインします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/neustar-ultradns-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Neustar UltraDNS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/neustar-ultradns-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Neustar UltraDNS 間にシングル サインオンを構成する方法について説明します。

この記事では、Neustar UltraDNS と Microsoft Entra ID を統合する方法について説明します。 Neustar UltraDNS を Microsoft Entra ID を統合すると、次のことができます:

- Neustar UltraDNS にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Neustar UltraDNS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Neustar UltraDNS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Neustar UltraDNS では、**SP Initiated SSO** と **IDP Initiated SSO** の両方がサポートされています。
- Neustar UltraDNS では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Neustar UltraDNS を追加する

Microsoft Entra ID への Neustar UltraDNS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Neustar UltraDNS を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Neustar UltraDNS**」と入力します。
4. 結果パネルから **Neustar UltraDNS** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Neustar UltraDNS 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Neustar UltraDNS に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Neustar UltraDNS の関連ユーザーとの間にリンク関係を確立する必要があります。

Neustar UltraDNS に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Neustar UltraDNS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Neustar UltraDNS テストユーザーを作成** - Microsoft Entra ユーザーである B.Simon に対応するユーザーを Neustar UltraDNS で作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Neustar UltraDNS**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.sso.security.neustar`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには [、Neustar UltraDNS クライアント サポート チーム](mailto:IDMTeam@neustar.biz) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. **[保存] を選択します**。
8. Neustar UltraDNS アプリケーションでは、特定の形式の SAML アサーションが想定されているため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Neustar UltraDNS アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | givenname | 名 |
    | メール | メール アドレス |
    | エスエヌ | 姓 |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. [ **Neustar UltraDNS のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Neustar UltraDNS の SSO の構成

**Neustar UltraDNS** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Neustar UltraDNS サポート チーム](mailto:IDMTeam@neustar.biz)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Neustar UltraDNS のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Neustar UltraDNS に作成します。 Neustar UltraDNS では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Neustar UltraDNS にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Neustar UltraDNS サインオン URL にリダイレクトされます。
- Neustar UltraDNS のサインオン URL に直接移動し、そこからログイン フローを開始します。

IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Neustar UltraDNS に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Neustar UltraDNS タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Neustar UltraDNS に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/new-relic-by-organization-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に New Relic by Organization を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/new-relic-by-organization-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-17
- Summary: Microsoft Entra ID から New Relic by Organization に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、New Relic by Organization と Microsoft Entra ID の両方で実行して、自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して [、New Relic by Organization](https://newrelic.com/) にユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- New Relic by Organization にユーザーを作成する
- アクセスが不要になった場合に New Relic by Organization のユーザーを削除する
- Microsoft Entra ID と New Relic by Organization の間でユーザー属性の同期を維持する
- 組織ごとに New Relic でグループとグループメンバーシップをプロビジョニングする
- New Relic by Organization への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/new-relic-limited-release-tutorial) (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- ユーザーがアクセスできるようにする New Relic by Organization の 1 つまたは複数のアカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と New Relic by Organization の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように New Relic by Organization を構成する

アカウント担当者と協力するか、support.newrelic.com でサポートを受けて、組織用に SCIM と SSO を構成します。 アカウント担当者に次の情報を提供する必要があります。

- 組織名
- 組織に関連付ける New Relic アカウント ID の一覧

アカウント担当者は、この情報を使用して、新しいシステムにお客様の組織のレコードを作成し、その組織にアカウントを関連付けます。

アカウント担当者は、ID プロバイダー用に New Relic SCIM/SSO アプリケーションを構成するために必要な次の情報を提供します。

- SCIM エンドポイント (テナント URL)
- SCIM ベアラー トークン (シークレット トークン)

SCIM ベアラー トークンにより、New Relic でのユーザーのプロビジョニングが許可されるため、この値はセキュリティで保護してください。 アカウント担当者は、安全な方法で SCIM ベアラー トークンをお客様に転送します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから New Relic by Organization を追加する

Microsoft Entra アプリケーション ギャラリーから New Relic by Organization を追加して、New Relic by Organization へのプロビジョニングの管理を開始します。 SSO のために New Relic by Organization を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: New Relic by Organization への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で New Relic by Organization 用に自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**にアクセスする

    [Image: [企業向けアプリケーション] ブレード]
3. アプリケーションの一覧で [ **New Relic by Organization**] を選択します。

    [Image: アプリケーションの一覧の New Relic のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、[テナント URL] に `https://scim-provisioning.service.newrelic.com/scim/v2` を入力します。 先ほど取得した SCIM 認証トークンの値を **シークレット トークン**に入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が New Relic に接続できることを確認します。 接続に失敗した場合は、確実に New Relic アカウントに管理者アクセス許可を付与してから、もう一度試します。

    [Image: [管理者資格情報] ダイアログ ボックスを示すスクリーンショット。テナント U R L とシークレット トークンを入力できます。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から New Relic by Organization に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために New Relic by Organization のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、New Relic by Organization API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | エクスターナルID | 糸 |
    | 活動中 | ブール値 |
    | emails[type eq "仕事"].value | 糸 |
    | 名前.名 | 糸 |
    | 名前.整形済み | 糸 |
    | タイムゾーン | 糸 |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から New Relic by Organization に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で New Relic by Organization のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | 表示名 | 糸 |
    | エクスターナルID | 糸 |
    | メンバー | リファレンス |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/new-relic-limited-release-tutorial"} -->
## Microsoft Entra ID で New Relic for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/new-relic-limited-release-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と New Relic 間にシングル サインオンを構成する方法について説明します。

この記事では、New Relic と Microsoft Entra ID を統合する方法について説明します。 New Relic を Microsoft Entra ID と統合すると、次のことができます:

- New Relic にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して New Relic に自動的にサインインするように設定できます。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

New Relic は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- [New Relic One アカウント／ユーザーモデル](https://docs.newrelic.com/docs/accounts/accounts-billing/new-relic-one-user-management/introduction-managing-users/#user-models)を利用しており、かつ Pro エディションまたは Enterprise エディションのいずれかである New Relic 組織。 詳細については、[New Relic の要件](https://docs.newrelic.com/docs/accounts/accounts-billing/new-relic-one-user-management/authentication-domains-saml-sso-scim-more)を参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- New Relic は、サービスプロバイダーまたはIDプロバイダーのいずれかによって開始されるSSOをサポートしています。
- New Relic では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/new-relic-by-organization-provisioning-tutorial)がサポートされます。

### ギャラリーからの New Relic の追加

Microsoft Entra ID への New Relic の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に **New Relic (By Organization)** を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[Microsoft Entra ギャラリーの参照]** ページで、検索ボックスに「**New Relic (By Organization)**」と入力します。
4. 結果から **[New Relic (By Organization)]** を選択し、 **[作成]** を選択します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### New Relic 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、New Relic に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと New Relic の関連ユーザーとの間にリンク関係を確立する必要があります。

New Relic 用に Microsoft Entra SSO を構成してテストするには:

1. ユーザーがこの機能を使用できるように Microsoft Entra SSO を構成します。
    1. B.Simon で Microsoft Entra のシングル サインオンをテストする Microsoft Entra テスト ユーザーを作成します。
    2. B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます。
2. New Relic の SSO の構成- New Relic 側でシングル サインオン設定を構成します。
    1. New Relic テスト ユーザーを作成する - New Relic で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. SSO をテスト して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New Relic by Organization** アプリケーション統合ページを参照し、[**管理**] セクションを見つけます。 続けて、 **[シングル サインオン]** を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 鉛筆アイコンが強調表示された [SAML でシングル サインオンをセットアップします] のスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、 **[識別子]** および **[応答 URL]** の値を入力します。

    - これらの値は、[New Relic の認証ドメイン UI](https://docs.newrelic.com/docs/accounts/accounts-billing/new-relic-one-user-management/authentication-domains-saml-sso-scim-more/#ui)から取得します。 そこから、次の手順に従います。
        1. 複数の認証ドメインがある場合は、Microsoft Entra SSO を接続するものを選択します。 ほとんどの企業では、**Default (既定値)** という認証ドメインしかありません。 認証ドメインが 1 つしかない場合は、何も選択する必要はありません。
        2. **[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** セクションの **[Assertion consumer URL](アサーション コンシューマー URL)** には、 **[Reply URL](応答 URL)** に使用する値が含まれています。
        3. **[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** セクションの **[Our entity ID](自分たちのエンティティ ID)** には、 **[Identifier](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/識別子)** に使用する値が含まれています。
6. [ **ユーザー属性と要求** ] セクションで、New Relic で使用されている電子メール アドレスを含むフィールドに **一意のユーザー識別子** がマップされていることを確認します。

    - 既定のフィールド **[user.userprincipalname]** の値が New Relic のメール アドレスと同じである場合は、それで適切に機能します。
    - **[user.userprincipalname]** が New Relic のメール アドレスでない場合は、フィールド **[user.mail]** の方が適切に機能する可能性があります。
7. **[SAML Signing Certificate](SAML 署名証明書)** セクションで **[App Federation Metadata Url](アプリのフェデレーション メタデータ URL)** をコピーし、後で使用できるようにその値を保存します。
8. **[Set up New Relic by Organization](New Relic by Organization のセットアップ)** セクションで **[Login URL](ログイン URL)** をコピーし、後で使用できるようにその値を保存します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### New Relic SSO の構成

次の手順に従って、New Relic で SSO を構成します。

1. New Relic に[サインイン](https://login.newrelic.com/)します。
2. [認証ドメイン UI](https://docs.newrelic.com/docs/accounts/accounts-billing/new-relic-one-user-management/authentication-domains-saml-sso-scim-more/#ui) に移動します。
3. Microsoft Entra SSO を接続する認証ドメインを選択します (複数の認証ドメインがある場合)。 ほとんどの企業では、**Default (既定値)** という認証ドメインしかありません。 認証ドメインが 1 つしかない場合は、何も選択する必要はありません。
4. **[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** セクションで、 **[Configure](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成)** を選択します。

    1. **[Source of SAML metadata] (SAML メタデータのソース)** には、以前に Microsoft Entra ID の **[アプリのフェデレーション メタデータ URL]** フィールドから保存した値を入力します。
    2. **[SSO target URL] (SSO ターゲット URL)** には、以前に Microsoft Entra ID の **[ログイン URL]** フィールドから保存した値を入力します。
    3. Microsoft Entra ID 側と New Relic 側の両方で設定がうまくいっていることを確認した後、**[保存]** を選択します。 両側が適切に構成されていない場合、ユーザーは New Relic にサインインできなくなります。

#### New Relic テスト ユーザーを作成する

このセクションでは、New Relic で B.Simon というユーザーを作成します。

1. New Relic に[サインイン](https://login.newrelic.com/)します。
2. [**ユーザー管理** ＵＩ](https://docs.newrelic.com/docs/accounts/accounts-billing/new-relic-one-user-management/add-manage-users-groups-roles/#where) に移動します。
3. [ **ユーザーの追加] を選択します**。

    1. **[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前)** には、「**B.Simon**」と入力します。
    2. **[電子メール]** には、Microsoft Entra SSO によって送信される値を入力します。
    3. ユーザーの **[Type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/種類)** と **[Group](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/グループ)** を選択します。 テスト ユーザーの場合、[Type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/種類) は **[Basic User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/基本ユーザー)** 、[Group](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/グループ) は **[User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** が適切な選択肢です。
    4. ユーザーを保存するには、 **[Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加)** を選択します。

注

New Relic では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/new-relic-by-organization-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる New Relic のサインオン URL にリダイレクトされます。
- New Relic のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した New Relic に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで New Relic タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した New Relic に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/new-relic-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に New Relic by Account を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/new-relic-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と New Relic by Account の間でシングル サインオンを構成する方法について説明します。

この記事では、New Relic by Account と Microsoft Entra ID を統合する方法について説明します。 New Relic by Account と Microsoft Entra ID を統合すると、次のことができます。

- New Relic by Account にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して New Relic by Account に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

手記

このドキュメントは、New Relic で [元のユーザー モデル](https://docs.newrelic.com/docs/accounts/original-accounts-billing/original-users-roles/overview-user-models/) を使用している場合にのみ関連します。 New Relic の新しいユーザー モデルを使用している場合は、 [New Relic (By Organization)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/new-relic-limited-release-tutorial) を参照してください。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- New Relic by Account でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- New Relic by Account では、 **SP** によって開始される SSO がサポートされます
- New Relic では、 [**自動ユーザー プロビジョニングとプロビジョニング解除**](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/new-relic-by-organization-provisioning-tutorial) がサポートされます (推奨)。

### ギャラリーから New Relic by Account を追加する

Microsoft Entra ID への New Relic by Account の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に New Relic by Account を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**New Relic by Account**」と入力します。
4. 結果パネルから **New Relic by Account** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### New Relic by Account の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、New Relic by Account に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと New Relic by Account の関連ユーザーとの間にリンク関係を確立する必要があります。

New Relic by Account で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **New Relic by Account の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **New Relic by Account のテストユーザーの作成** - B.Simon に対応するユーザーを New Relic by Account で作成し、そのユーザーを Microsoft Entra における B.Simon の表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**New Relic by Account**&gt;**シングル サインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    `https://rpm.newrelic.com:443/accounts/{acc_id}/sso/saml/finalize` - 必ず `acc_id` を New Relic by Account の自分のアカウント ID に置き換えてください。

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `rpm.newrelic.com`
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **アカウントによる New Relic のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### New Relic by Account の SSO の構成

1. 別の Web ブラウザー ウィンドウで、 **New Relic by Account** 企業サイトに管理者としてサインオンします。
2. 上部のメニューで、[ **アカウント設定]** を選択します。

    [Image: [アカウント設定] が選択されている [ようこそ] ページを示すスクリーンショット。]
3. [ **セキュリティと認証** ] タブを選択し、[ **シングル サインオン** ] タブを選択します。

    [Image: シングル サインオン]
4. [SAML] ダイアログ ページで、次の手順を実行します。

    [Image: SAML]

    ある。 [ **ファイルの選択] を選択** して、ダウンロードした Microsoft Entra 証明書をアップロードします。

    b。 [ **リモート ログイン URL** ] ボックスに、 **ログイン URL** の値を貼り付けます。

    c. [ **ログアウト ランディング URL** ] ボックスに、 **ログアウト URL** の値を貼り付けます。

    d. [ **変更を保存] を選択します**。

#### New Relic by Account のテスト ユーザーの作成

1. **New Relic by Account** 企業サイトに管理者としてサインインします。
2. 上部のメニューで、[ **アカウント設定]** を選択します。

    [Image: [ようこそ] ページで選択されているアカウント設定を示すスクリーンショット。]
3. 左側の **[アカウント** ] ウィンドウで、[ **概要**] を選択し、[ **ユーザーの追加]** を選択します。

    [Image: スクリーンショットは、[ユーザーの追加] を選択できる [概要] ウィンドウを示しています。]
4. [ **アクティブ ユーザー** ] ダイアログで、次の手順を実行します。

    [Image: アクティブユーザー]

    ある。 [ **電子メール** ] ボックスに、プロビジョニングする有効な Microsoft Entra ユーザーのメール アドレスを入力します。

    b。 **[ロール**] で **[ユーザー**] を選択します。

    c. [ **このユーザーの追加] を選択します**。

手記

New Relic by Account によって提供されるその他の New Relic by Account ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる New Relic by Account のサインオン URL にリダイレクトされます。
- New Relic by Account のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [New Relic by Account] タイルを選択すると、このオプションは New Relic by Account のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/newsignature-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cloud Management Portal for Microsoft Azure を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/newsignature-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cloud Management Portal for Microsoft Azure の間でシングル サインオンを構成する方法について説明します。

この記事では、Cloud Management Portal for Microsoft Azure と Microsoft Entra ID を統合する方法について説明します。 Cloud Management Portal for Microsoft Azure と Microsoft Entra ID を統合すると、次のことが可能になります。

- Cloud Management Portal for Microsoft Azure にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Cloud Management Portal for Microsoft Azure に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cloud Management Portal for Microsoft Azure でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Cloud Management Portal for Microsoft Azure では、**SP** initiated SSO がサポートされます。

### ギャラリーからの Cloud Management Portal for Microsoft Azure の追加

Microsoft Entra への Cloud Management Portal for Microsoft Azure の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Cloud Management Portal for Microsoft Azure を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Cloud Management Portal for Microsoft Azure**」と入力します。
4. 結果のパネルから **[Cloud Management Portal for Microsoft Azure]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cloud Management Portal for Microsoft Azure での Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Cloud Management Portal for Microsoft Azure で Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Cloud Management Portal for Microsoft Azure の関連ユーザーとの間にリンク関係を確立する必要があります。

Cloud Management Portal for Microsoft Azure で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cloud Management Portal for Microsoft Azure の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Microsoft Azure 用の Cloud Management Portal 内にテストユーザーを作成** - Microsoft Entra に表されたユーザーとリンクされるよう、Microsoft Azure の Cloud Management Portal に B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. Microsoft Azure 用 **Entra ID**&gt;**企業向けアプリ**&gt;**Cloud 管理ポータル**&gt;**シングルサインオン** にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ａ。 **[識別子]** ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<subdomain>.igcm.com` |
    | `https://<subdomain>.newsignature.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<subdomain>.igcm.com/<instancename>` |
    | `https://<subdomain>.newsignature.com` |
    | `https://<subdomain>.newsignature.com/<instancename>` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://portal.newsignature.com/<instancename>` |
    | `https://portal.igcm.com/<instancename>` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 [Cloud Management Portal for Microsoft Azure クライアント サポート チーム](mailto:jczernuszka@newsignature.com)に問い合わせて値を取得します。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Cloud Management Portal for Microsoft Azure のセットアップ]** セクションで、要件のとおりに適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cloud Management Portal for Microsoft Azure SSO の構成

**Cloud Management Portal for Microsoft Azure** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Cloud Management Portal for Microsoft Azure サポート チーム](mailto:jczernuszka@newsignature.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cloud Management Portal for Microsoft Azure テスト ユーザーの作成

このセクションでは、Cloud Management Portal for Microsoft Azure で Britta Simon というユーザーを作成します。 [Cloud Management Portal for Microsoft Azure サポート チーム](mailto:jczernuszka@newsignature.com)と連携して、Cloud Management Portal for Microsoft Azure プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Cloud Management Portal for Microsoft Azure のサインオン URL にリダイレクトされます。
- Cloud Management Portal for Microsoft Azure のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Cloud Management Portal for Microsoft Azure] タイルを選択すると、このオプションは Cloud Management Portal for Microsoft Azure のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nexonia-tutorial"} -->
## Microsoft Entra ID で Nexonia for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nexonia-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Nexonia の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、Nexonia と Microsoft Entra ID を統合する方法について説明します。 Nexonia と Microsoft Entra ID を統合すると、次のような利点があります。

- Nexonia にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで Nexonia に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Nexonia でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Nexonia では、 **IDP** Initiated SSO がサポートされます

### ギャラリーから Nexonia を追加する

Microsoft Entra ID への Nexonia の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Nexonia を追加する必要があります。

**ギャラリーから Nexonia を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. 検索ボックスに「 **Nexonia**」と入力し、結果パネルで **Nexonia** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Nexonia]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Nexonia で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Nexonia 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Nexonia で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Nexonia シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Nexonia のテストユーザーの作成** - Nexonia における Britta Simon の対応者を作成し、そのユーザーを Microsoft Entra のユーザー表現とリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Nexonia で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Nexonia** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    [Image: Nexonia ドメインと URL のシングルサインオン情報]

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `Nexonia`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://system.nexonia.com/assistant/saml.do?orgCode=<organizationcode>`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL で値を更新します。 この値を取得するには、 [Nexonia クライアント サポート チーム](https://nexonia.zendesk.com/hc/requests/new) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Nexonia のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    ある。 ログイン URL

    b。 Microsoft Entra アイデンティファイヤー

    c. ログアウト URL

#### Nexonia シングル サインオンの構成

**Nexonia** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Nexonia サポート チーム](https://nexonia.zendesk.com/hc/requests/new)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Nexonia テスト ユーザーの作成

このセクションでは、Nexonia で Britta Simon というユーザーを作成します。 [Nexonia サポート チーム](https://nexonia.zendesk.com/hc/requests/new)と協力して、Nexonia プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Nexonia] タイルを選択すると、SSO を設定した Nexonia に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nexsure-tutorial"} -->
## Microsoft Entra ID で Nexsure for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nexsure-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Nexsure 間にシングル サインオンを構成する方法について説明します。

この記事では、Nexsure と Microsoft Entra ID を統合する方法について説明します。 Nexsure を Microsoft Entra ID と統合すると、次のことができます。

- Nexsure にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Nexsure に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Nexsure でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Nexsure では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーから Nexsure を追加する

Microsoft Entra ID への Nexsure の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Nexsure を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Nexsure**」と入力します。
4. 結果パネルから **Nexsure** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Nexsure 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Nexsure に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Nexsure の関連ユーザーとの間にリンク関係を確立する必要があります。

Nexsure に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Nexsure の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Nexsure のテストユーザーを作成する** - Nexsure で B.Simon の対応ユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Nexsure**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Nexsure のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Nexsure SSO の構成

**Nexsure** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Nexsure サポート チーム](mailto:nexsure.support@xdti.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Nexsure のテスト ユーザーの作成

このセクションでは、Nexsure で Britta Simon というユーザーを作成します。 [Nexsure サポート チーム](mailto:nexsure.support@xdti.com)と協力して、Nexsure プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Nexsure に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Nexsure] タイルを選択すると、SSO を設定した Nexsure に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nice-cxone-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に NICE CXone を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nice-cxone-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と NICE CXone 間にシングル サインオンを構成する方法について説明します。

この記事では、NICE CXone と Microsoft Entra ID を統合する方法について説明します。 NICE CXone を Microsoft Entra ID を統合すると、次のことができます。

- NICE CXone にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って NICE CXone に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な NICE CXone のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- NICE CXone では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから NICE CXone を追加する

Microsoft Entra ID への NICE CXone の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに NICE CXone を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「NICE CXone**」と入力します。
4. 結果パネルから **NICE CXone** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### NICE CXone 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、NICE CXone に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと NICE CXone の関連ユーザーとの間にリンク関係を確立する必要があります。

NICE CXone に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **NICE CXone SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **NICE CXone のテストユーザーの作成** - Microsoft Entra で表現されているユーザーとリンクさせるため、NICE CXone で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**NICE CXone**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://cxone.niceincontact.com/<guid>` |
    | `https://cxone-gov.niceincontact.com/<guid>` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://cxone.niceincontact.com/auth/authorize?tenantId=<guid>` |
    | `https://cxone-gov.niceincontact.com/auth/authorize?tenantId=<guid>` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://cxone.niceincontact.com` |
    | `https://cxone-gov.niceincontact.com` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、NICE CXone サポート チーム](https://www.nice.com/services/customer-support) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **NICE CXone のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成を適切な U R L にコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### NICE CXone SSO を構成する

**NICE CXone** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [NICE CXone サポート チーム](https://www.nice.com/services/customer-support)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### NICE CXone のテスト ユーザーを作成する

このセクションでは、NICE CXone で Britta Simon というユーザーを作成します。 [NICE CXone サポート チーム](https://www.nice.com/services/customer-support)と協力して、NICE CXone プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる NICE CXone サインオン URL にリダイレクトされます。
- NICE CXone のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [NICE CXone] タイルを選択すると、このオプションは NICE CXone のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nimblex-tutorial"} -->
## Microsoft Entra ID で Nimblex for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nimblex-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Nimblex の間にシングル サインオンを構成する方法について説明します。

この記事では、Nimblex と Microsoft Entra ID を統合する方法について説明します。 Nimblex を Microsoft Entra ID と統合すると、次のことができます。

- Nimblex にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Nimblex に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Nimblex でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Nimblex では、**SP** Initiated SSO がサポートされます。
- Nimblex では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Nimblex の追加

Microsoft Entra ID への Nimblex の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Nimblex を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Nimblex**」と入力します。
4. 結果のパネルから **[Nimblex]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Nimblex 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Nimblex に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Nimblex の関連ユーザーとの間にリンク関係を確立する必要があります。

Nimblex に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Nimblex SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Nimblexのテストユーザーを作成する** - Nimblexで、Microsoft EntraのB.Simonの表現とリンクされたB.Simonに対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業用アプリ**&gt;**Nimblex**&gt;**シングルサインオン**に進みます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_APPLICATION_PATH>/Login.aspx`

    b。 **[識別子]** ボックスに、`https://<YOUR_APPLICATION_PATH>/` という形式で URL を入力します。

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<path-to-application>/SamlReply.aspx`

    注

    これらの値は実際の値ではありません。 実際の Sign-On URL、識別子、応答 URL でこれらの値を更新します。 この値を取得するには、[Nimblex クライアント サポート チーム](mailto:support@ebms.com.au)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用したシングル Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Nimblex のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Nimblex SSO の構成

1. 別の Web ブラウザー ウィンドウで、Nimblex にセキュリティ管理者としてサインインします。
2. ページの右上にある **[設定** ] ロゴを選択します。

    [Image: スクリーンショットは、設定アイコンを示しています。]
3. [ **コントロール パネル** ] ページの [ **セキュリティ** ] セクションで、[ **シングル サインオン**] を選択します。

    [Image: スクリーンショットは、[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ) メニューの [Single Sign-On](シングル サインオン) が選択されていることを示しています。]
4. [ **シングル サインオンの管理** ] ページで、インスタンス名を選択し、[ **編集]** を選択します。

    [Image: スクリーンショットは、[Single Sign-On](シングル サインオン) を示しています。ここでは、[Edit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/編集) を選択できます。]
5. **[SSO プロバイダーの編集]** ページで、次の手順を行います。

    [Image: スクリーンショットは、説明した値を入力できる [Edit SSO Provider](SSO プロバイダーの編集) を示しています。]

    ある。 **[説明]** テキストボックスに、インスタンス名を入力します。

    b。 ダウンロードした Base-64 でエンコードされた証明書をメモ帳で開き、その内容をコピーして **[証明書]** ボックスに貼り付けます。

    c. **[ID プロバイダーの SSO ターゲット URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を入力します。

    d. **保存** を選択します。

#### Nimblex のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Nimblex に作成します。 Nimblex では、Just-In-Time ユーザー プロビジョニングがサポートされます。この設定は既定で有効です。 このセクションにはアクション項目はありません。 Nimblex にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Nimblex クライアント サポート チーム](mailto:support@ebms.com.au)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Nimblex のサインオン URL にリダイレクトされます。
- Nimblex のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Nimblex] タイルを選択すると、このオプションは Nimblex のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nimbus-tutorial"} -->
## Microsoft Entra ID で Nimbus for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nimbus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Nimbus の間でシングル サインオンを構成する方法について説明します。

この記事では、Nimbus と Microsoft Entra ID を統合する方法について説明します。 Nimbus を Microsoft Entra ID を統合すると、次のことができます。

- Nimbus にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Nimbus に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Nimbus でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Nimbus では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Nimbus では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Nimbus を追加する

Microsoft Entra ID への Nimbus の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Nimbus を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Nimbus**」と入力します。
4. 結果のパネルから **[Nimbus]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Nimbus 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Nimbus に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Nimbus の関連ユーザーとの間にリンク関係を確立する必要があります。

Nimbus との Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Nimbus SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Nimbus のテスト ユーザーを作成** - Nimbus で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Nimbus**&gt;**シングルサインオン**まで移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.time2work.com/Security/ADFS.aspx`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.time2work.com/Security/ADFS.aspx?authmode=15&saml=2.0`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.time2work.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 この値を取得するには、[Nimbus クライアント サポート チーム](mailto:support@nimbus.cloud)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Nimbus SSO の構成

**Nimbus** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Nimbus サポート チーム](mailto:support@nimbus.cloud)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Nimbus テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Nimbus に作成します。 Nimbus では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Nimbus にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Nimbus サインオン URL にリダイレクトされます。
- Nimbus のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Nimbus に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Nimbus] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Nimbus に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nitro-productivity-suite-tutorial"} -->
## Nitro Productivity Suite を Microsoft Entra ID でシングル サインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nitro-productivity-suite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-03
- Summary: Microsoft Entra ID と Nitro Productivity Suite の間でシングル サインオンを構成する方法について説明します。

この記事では、Nitro Productivity Suite と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Nitro Productivity Suite を統合すると、次のことができます。

- Nitro Productivity Suite にアクセスする Microsoft Entra ID ユーザーを制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Nitro Productivity Suite に自動的にサインインするように設定できます。
- 1 つの中央の場所 (Microsoft Entra 管理センター) でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Nitro Productivity Suite [Enterprise サブスクリプション](https://www.gonitro.com/pricing)。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Nitro Productivity Suite では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。
- Nitro Productivity Suite では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Nitro Productivity Suite の追加

Microsoft Entra ID への Nitro Productivity Suite の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Nitro Productivity Suite を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Nitro Productivity Suite**」と入力します。
4. 結果から **[Nitro Productivity Suite]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Nitro Productivity Suite の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Nitro Productivity Suite に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Nitro Productivity Suite の関連ユーザーとの間にリンク関係を確立する必要があります。

Nitro Productivity Suite に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. ユーザーがこの機能を使用できるように Microsoft Entra SSO を構成します。

    ある。 B.Simon で Microsoft Entra のシングル サインオンをテストする Microsoft Entra テスト ユーザーを作成します。

    b。 B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます。
2. Nitro Productivity Suite のテスト ユーザーを作成して、B.Simon に対応するユーザーを Nitro Productivity Suite に作成し、Microsoft Entra の B.Simon にリンクさせます。
3. SSO をテスト して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Nitro Productivity Suite** アプリケーション統合ページを参照し、[**管理**] セクションを見つけます。 **[シングル サインオン]** を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 鉛筆アイコンが強調表示された [SAML を使用して単一 Sign-On を設定する] ページのスクリーンショット]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    ある。 **[識別子]** ボックスに、**Nitro Admin ポータル**から [\[SAML Entity ID\](SAML エンティティ ID)](https://admin.gonitro.com/) フィールドをコピーして貼り付けます。 この値は `urn:auth0:gonitro-prod:<ENVIRONMENT>` 形式になっています。

    b。 **[応答 URL]** ボックスに、**Nitro Admin ポータル**から [\[ACS URL\]](https://admin.gonitro.com/) フィールドをコピーして貼り付けます。 この値は `https://gonitro-prod.eu.auth0.com/login/callback?connection=<ENVIRONMENT>` 形式になっています。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://sso.gonitro.com/login`
7. **保存** を選択します。
8. [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を見つけます。 [ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [ダウンロード] リンクが強調表示された [SAML 署名証明書] セクションのスクリーンショット]
9. **[Set up Nitro Productivity Suite](Nitro Productivity Suite のセットアップ)** セクションで、 **[ログイン URL]** の横のコピー アイコンを選択します。

    [Image: URL とコピー アイコンが強調表示された [Set up Nitro Productivity Suite](Nitro Productivity Suite のセットアップ) セクションのスクリーンショット]
10. [Nitro Admin ポータル](https://admin.gonitro.com/)の **[Enterprise Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/エンタープライズ設定)** ページで、 **[Single Sign-On](シングル サインオン)** セクションを見つけます。 **[Setup SAML SSO](SAML SSO のセットアップ)** を選択します。

    ある。 手順 9 の **ログイン URL を** **[サインイン URL** ] フィールドに貼り付けます。

    b。 **[X509 署名証明書**] フィールドに**証明書 (Base64)** 手順 8 をアップロードします。

    c. **送信**を選択します。

    d. [ **シングル サインオンを有効にする] を選択します**。
11. Nitro Productivity Suite アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 既定の属性のスクリーンショット]
12. 上記の属性に加えて、Nitro Productivity Suite アプリケーションでは、その他にいくつかの属性が SAML 応答で返されることが想定されています。 これらの属性は値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 社員番号 | user.objectid (ユーザーのオブジェクトID) |

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Nitro Productivity Suite テスト ユーザーを作成する

Nitro Productivity Suite では、Just-In-Time ユーザー プロビジョニンがサポートされています。この設定は既定で有効になっています。 追加のアクションはありません。 Nitro Productivity Suite にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

#### SP 開始:

1. [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる Nitro Productivity Suite のサインオン URL にリダイレクトされます。
2. Nitro Productivity Suite のサインオン URL に直接移動し、そこからログイン フローを開始します。

#### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Nitro Productivity Suite に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで Nitro Productivity Suite タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Nitro Productivity Suite に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nodetrax-project-tutorial"} -->
## Microsoft Entra ID で Nodetrax Project for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nodetrax-project-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Nodetrax Project の間でシングル サインオンを構成する方法について説明します。

この記事では、Nodetrax Project と Microsoft Entra ID を統合する方法について説明します。 Nodetrax Project と Microsoft Entra ID を統合すると、次のことができます。

- Nodetrax Project にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Nodetrax Project に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

Nodetrax Projectは、次の[国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Nodetrax Project でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Nodetrax Project では、**SP と IDP によって開始される SSO** がサポートされます。

### ギャラリーから Nodetrax プロジェクトを追加する

Microsoft Entra ID への Nodetrax Project の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Nodetrax Project を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Nodetrax Project**」と入力します。
4. 結果パネル **Nodetrax Project** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Nodetrax Project の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Nodetrax Project に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Nodetrax Project の関連ユーザーとの間にリンク関係を確立する必要があります。

Nodetrax Project で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Nodetrax Project の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Nodetrax Project のテスト ユーザーを作成 - Nodetrax Project** における B.Simon の対応となるユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Nodetrax Project**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: スクリーンショットは、基本的な S A M L 構成を編集する方法を示しています。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、URL: `https://project.nodetrax.com` を入力します。
7. Nodetrax Project アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットは、Nodetrax Project アプリケーションの画像を示しています。]
8. 上記に加えて、Nodetrax Project アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 身分証明書 | user.objectid (ユーザーのオブジェクトID) |
9. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: スクリーンショットは、証明書のダウンロードリンクを示しています。]
10. [**Nodetrax Project** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: のスクリーンショットは、構成に適した U R L をコピーする手順を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Nodetrax Project の SSO の構成

**Nodetrax Project** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした該当する URL を [Nodetrax Project サポート チーム](mailto:support@nodetrax.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Nodetrax Project のテスト ユーザーの作成

このセクションでは、Nodetrax Project で Britta Simon というユーザーを作成します。 Nodetrax Project サポート チーム  と連携して、Nodetrax Project プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Nodetrax プロジェクトサインオン URL にリダイレクトされます。
- Nodetrax Project のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Nodetrax プロジェクトに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Nodetrax Project] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Nodetrax Project に自動的にサインインされます。 詳細については、「[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nomadesk-tutorial"} -->
## Microsoft Entra ID で Nomadesk for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nomadesk-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Nomadesk 間にシングル サインオンを構成する方法について説明します。

この記事では、Nomadesk と Microsoft Entra ID を統合する方法について説明します。 Nomadesk を Microsoft Entra ID を統合すると、次のことができます。

- Nomadesk にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Nomadesk に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Nomadesk でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Nomadesk では、**SP** によって開始される SSO がサポートされます。
- Nomadesk では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Nomadesk の追加

Microsoft Entra ID への Nomadesk の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Nomadesk を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Nomadesk**」と入力します。
4. 結果のパネルから **[Nomadesk]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Nomadesk 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Nomadesk に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Nomadesk の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Nomadesk と組み合わせて構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Nomadesk SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Nomadesk でテストユーザーを作成する** - Nomadesk で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra における B.Simon の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Nomadesk**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://secure.nomadesk.com/saml/<instancename>`

    b。 [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://mynomadesk.com/logon/saml/<TENANTID>`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 この値を取得するには、[Nomadesk クライアント サポート チーム](mailto:support@nomadesk.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Nomadesk の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Nomadesk SSO の構成

**Nomadesk** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [Nomadesk サポート チーム](mailto:support@nomadesk.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Nomadesk テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Nomadesk に作成します。 Nomadesk では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Nomadesk にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Nomadesk のサポート チーム](mailto:support@nomadesk.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Nomadesk のサインオン URL にリダイレクトされます。
- Nomadesk のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Nomadesk] タイルを選択すると、このオプションは Nomadesk のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nomadic-tutorial"} -->
## Microsoft Entra ID で Nomadic for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nomadic-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Nomadic の間にシングル サインオンを構成する方法について説明します。

この記事では、Nomadic と Microsoft Entra ID を統合する方法について説明します。 Nomadic と Microsoft Entra ID を統合すると、次のような利点があります。

- Nomadic にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで Nomadic に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Nomadic でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Nomadic では、 **SP** Initiated SSO がサポートされます

### ギャラリーからの Nomadic の追加

Microsoft Entra ID への Nomadic の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Nomadic を追加する必要があります。

**ギャラリーから Nomadic を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. 検索ボックスに「 **Nomadic**」と入力し、結果パネルで **Nomadic** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧にあるNomadic]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Nomadic で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Nomadic 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Nomadic で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Nomadic シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Nomadic テストユーザーを作成** - Britta Simon の代わりとなる Nomadic のユーザーを作成し、そのユーザーを Microsoft Entra におけるユーザーの表現と結びつけます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Nomadic で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Nomadic** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: Nomadic のドメインと URL のシングルサインオン情報]

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.nomadic.fm/signin`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。

    ```http
    https://<company name>.nomadic.fm/auth/saml2/sp
    https://<company name>.staging.nomadic.fm/auth/saml2/sp
    ```

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [Nomadic クライアント サポート チーム](mailto:help@nomadic.fm) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Nomadic のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    ある。 ログイン URL

    b。 Microsoft Entra アイデンティファイヤー

    c. ログアウト URL

#### Nomadic シングル サインオンの構成

**Nomadic** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Nomadic サポート チーム](mailto:help@nomadic.fm)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Nomadic のテスト ユーザーの作成

このセクションでは、Nomadic で Britta Simon というユーザーを作成します。 [Nomadic サポート チーム](mailto:help@nomadic.fm)と協力して、Nomadic プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Nomadic] タイルを選択すると、SSO を設定した Nomadic に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nordpass-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に NordPass を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nordpass-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-17
- Summary: Microsoft Entra ID から NordPass に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために NordPass と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーを [NordPass](https://nordpass.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- NordPass でユーザーを作成します。
- アクセスが不要になった場合は、NordPass のユーザーを削除します。
- Microsoft Entra ID と NordPass の間でユーザー属性の同期を維持する。
- NordPass に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Admin アクセス許可がある NordPass のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と NordPass の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように NordPass を構成する

1. [NordPass 管理パネル](https://panel.nordpass.com)にログインします。
2. ユーザー **プロビジョニング &gt; 設定** に移動し、[ **資格情報の取得**] を選択します。
3. 新しいウィンドウに、管理者の資格情報が表示されます。

    [Image: NordPass 管理者の資格情報]
4. 新しいウィンドウに表示される **テナント URL** と **シークレット トークン** をコピーして保存します。 この値は、NordPass アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから NordPass を追加する

Microsoft Entra アプリケーション ギャラリーから NordPass を追加して、NordPass へのプロビジョニングの管理を開始します。 SSO のために NordPass を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: NordPass への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーの割り当てに基づいて、NordPass でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で NordPass の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**にアクセスする

    [Image: エンタープライズ アプリケーションブレード]
3. アプリケーションの一覧で **[NordPass**] を選択します。

    [Image: アプリケーションの一覧の [NordPass] リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、NordPass テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が NordPass に接続できることを確認します。 接続に失敗した場合は、NordPass アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から NordPass に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で NordPass のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が NordPass API でサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | NordPass で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | エクスターナルID | 糸 |  | ✓ |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/notion-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Notion を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/notion-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-17
- Summary: Microsoft Entra ID から Notion に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Notion ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Notion](https://notion.so) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Notion でユーザーを作成する。
- アクセスが不要になった場合は、Notion のユーザーを削除します。
- Microsoft Entra ID と Notion の間でユーザー属性の同期を維持する。
- Notion でグループとグループメンバーシップを設定する。
- Notion への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/notion-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある Notion のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Notion の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Notion を構成する

1. Notion ワークスペースにログインし、[ **設定] と [メンバー] → [ID] タブを** 開き、[ **SCIM プロビジョニング** ] セクションまで下にスクロールします。
2. トークンがまだ生成されていない場合は、[ **+ トークンの追加]** を選択してトークンをコピーします。 このトークンは、手順 5.5 でシークレット トークンとして入力します。
3. Notion の SCIM テナント URL は `https://www.notion.so/scim/v2`であり、手順 5.5 で使用します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Notion を追加する

Microsoft Entra アプリケーション ギャラリーから Notion を追加して、Notion へのプロビジョニングの管理を開始します。 SSO のために Notion を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Notion への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Notion の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Notion]** を選択します。

    [Image: アプリケーション リストの Notion リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Notion テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Notion に接続できることを確認します。 接続に失敗した場合は、Notion アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Notion に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Notion のユーザー アカウントの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Notion API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Notion により求められる |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 名前.整形済み | 糸 |  | ✓ |
    | 活動中 | ブール値 |  | ✓ |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Notion に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Notion のグループの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Notion で必須 |
    | --- | --- | --- | --- |
    | ディスプレイ名 | 糸 | ✓ | ✓ |
    | メンバーズ | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/notion-tutorial"} -->
## Microsoft Entra ID で Notion for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/notion-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Notion の間のシングル サインオンを構成する方法について説明します。

この記事では、Notion と Microsoft Entra ID を統合する方法について説明します。 Notion を Microsoft Entra ID と統合すると、次のことが可能になります。

- Notion にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Notion に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Notion のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Notion では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Notion では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Notion では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/notion-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できる Notion ワークスペースは 1 つだけです。

### ギャラリーからの Notion の追加

Microsoft Entra ID への Notion の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Notion を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Notion**」と入力します。
4. 結果のパネルから **[Notion]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Notion に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Notion に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するためには、Microsoft Entra ユーザーと Notion の関連ユーザーとの間にリンク関係を確立する必要があります。

Notion に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Notion の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Notion テストユーザーを作成** - Microsoft Entra 上のユーザーである B.Simon にリンクされた Notion 内の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Notion**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    **[応答 URL**] テキスト ボックスに、Notion ワークスペース**の [設定] & [メンバー] から**取得できる次のパターンの URL を入力します&gt;**セキュリティと ID**&gt;**Single のサインオン URL**:`https://www.notion.so/sso/saml/<CUSTOM_ID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** テキスト ボックスに、次の URL を入力します: `https://www.notion.so/login`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Notion アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Notion アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL をコピーします**。 **[Notion** **] ワークスペースの [設定] & [メンバー**&gt;**セキュリティと ID**] に移動し、コピーした値を **IDP メタデータ URL** フィールドに貼り付けます。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Notion の SSO の構成

**[Notion** **] ワークスペースの [設定] & [メンバー**&gt;**セキュリティと ID**] に移動し、コピーした**アプリのフェデレーション メタデータ URL** の値を **[IDP メタデータ URL**] フィールドに貼り付けます。

同じ設定ページで、[ **メール ドメイン** ] で [ **サポートに問い合わせる** ] を選択して、組織のメール ドメインを追加します。

メール ドメインが承認され、追加されたら、 **[Enable SAML](SAML を有効にする)** トグルを使用して SAML SSO を有効にします。

テストが成功したら、 **[Enforce SAML](SAML を適用する)** トグルを使用して SAML SSO を適用できます。 Notion ワークスペース管理者はメール アドレスでログインする機能を保持しますが、他のすべてのメンバーは、SAML SSO を使用して Notion にログインする必要があることに注意してください。

#### Notion のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Notion に作成します。 Notion では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Notion にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Notion のサインオン URL にリダイレクトされます。
- Notion のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Notion に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Notion] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Notion に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/novatus-tutorial"} -->
## Microsoft Entra ID で Novatus for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/novatus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Novatus の間にシングル サインオンを構成する方法について学習します。

この記事では、Novatus と Microsoft Entra ID を統合する方法について説明します。 Novatus を Microsoft Entra ID と統合すると、次のことができます。

- Novatus にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Novatus に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Novatus でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Novatus では、 **SP** Initiated SSO がサポートされます。
- Novatus では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Novatus を追加する

Microsoft Entra ID への Novatus の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Novatus を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Novatus**」と入力します。
4. 結果パネルから **Novatus** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Novatus 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Novatus に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Novatus の関連ユーザーとの間にリンク関係を確立する必要があります。

Novatus で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Novatus SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Novatus テストユーザーを作成** - Novatus で B.Simon に対応するユーザーを作成し、そのユーザーが Microsoft Entra でのユーザー表現にリンクされるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Novatus**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] 画面を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sso.novatuscontracts.com/<companyname>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 値を取得するには [、Novatus クライアント サポート チーム](mailto:jvinci@novatusinc.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Novatus のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Novatus SSO の構成

**Novatus** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Novatus サポート チーム](mailto:jvinci@novatusinc.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Novatus のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Novatus に作成します。 Novatus では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Novatus にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Novatus サポート チーム](mailto:jvinci@novatusinc.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Novatus のサインオン URL にリダイレクトされます。
- Novatus のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Novatus] タイルを選択すると、このオプションは Novatus のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ns1-sso-azure-tutorial"} -->
## Microsoft Entra ID で NS1 SSO for Azure for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ns1-sso-azure-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と NS1 SSO for Azure の間でシングル サインオンを構成する方法について説明します。

この記事では、NS1 SSO for Azure と Microsoft Entra ID を統合する方法について説明します。 NS1 SSO for Azure を Microsoft Entra ID と統合すると、次のことができます:

- NS1 SSO for Azure にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って NS1 SSO for Azure に自動的にサインインできるようにします。
- 1 つの中央の場所 (Azure portal) でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- NS1 SSO for Azure でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- NS1 SSO for Azure では、SP Initiated SSO と IDP Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの NS1 SSO for Azure の追加

Microsoft Entra ID への NS1 SSO for Azure の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に NS1 SSO for Azure を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに「**NS1 SSO for Azure**」と入力します。
4. 結果パネルから **NS1 SSO for Azure** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### NS1 SSO for Azure 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、NS1 SSO for Azure に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと NS1 SSO for Azure の関連ユーザーとの間にリンク関係を確立します。

NS1 SSO for Azure で Microsoft Entra SSO を構成してテストする一般的な手順を以下に示します:

1. ユーザーがこの機能を使用できるように **Microsoft Entra SSO を構成**します。

    ある。 B.Simon で Microsoft Entra のシングル サインオンをテストする Microsoft **Entra テスト ユーザーを作成**します。

    b。 **B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます** 。
2. **NS1 SSO for Azure SSO** を構成して、アプリケーション側でシングル サインオン設定を構成します。

    ある。 **NS1 SSO for Azure で B.Simon** に対応するユーザーを作成するために、Azure テスト ユーザー用の NS1 SSO を作成します。 このユーザーを Microsoft Entra の対応するユーザーにリンクさせます。
3. **SSO をテスト** して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**NS1 SSO for Azure** アプリケーション統合ページを参照し、[**管理**] セクションを見つけます。 **[シングル サインオン]** を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 鉛筆アイコンが強調表示されている SAML ページでのシングル サインオンの設定のスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次の URL を入力します。 `https://api.nsone.net/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.nsone.net/saml/sso/<ssoid>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次の URL を入力します。 `https://my.nsone.net/#/login/sso`

    注

    応答 URL は、実際の値ではありません。 応答 URL 値を実際の応答 URL で更新します。 この値を取得するには、 [NS1 SSO for Azure クライアント サポート チーム](mailto:techops@nsone.net) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. NS1 SSO for Azure アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 このアプリケーションに対して次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの **「ユーザー属性と要求** 」セクションから管理できます。 [ **SAML を使用した単一 Sign-On の設定** ] ページで、鉛筆アイコンを選択して [ **ユーザー属性** ] ダイアログ ボックスを開きます。

    [Image: 鉛筆アイコンが強調表示されている [ユーザー属性と要求] セクションのスクリーンショット。]
8. 属性名を選択して要求を編集します。

    [Image: [ユーザー属性と要求] セクションのスクリーンショット。属性名が強調表示されています。]
9. **[変換] を選択します**。

    [Image: [変換] が強調表示されている [要求の管理] セクションのスクリーンショット。]
10. [ **変換の管理** ] セクションで、次の手順を実行します。

    [Image: さまざまなフィールドが強調表示されている [変換の管理] セクションのスクリーンショット。]

    1. 変換として **ExactMailPrefix()** を選択 **します**。
    2. **パラメーター 1** として **user.userprincipalname** を選択します。
    3. **追加**を選択します。
    4. **[保存] を選択します**。
11. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、コピー ボタンを選択します。 これにより、 **アプリのフェデレーション メタデータ URL が** コピーされ、コンピューターに保存されます。

    [Image: [コピー] ボタンが強調表示されている SAML 署名証明書のスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### NS1 SSO for Azure の SSO の構成

NS1 SSO for Azure 側でシングル サインオンを構成するには、アプリのフェデレーション メタデータ URL を [Azure サポート チームの NS1 SSO](mailto:techops@nsone.net) に送信する必要があります。 この設定が構成され、SAML SSO 接続が両側で正しく行われます。

#### NS1 SSO for Azure のテスト ユーザーの作成

このセクションでは、NS1 SSO for Azure で B.Simon というユーザーを作成します。 NS1 SSO for Azure サポート チームと協力して、NS1 SSO for Azure プラットフォームにユーザーを追加します。 ユーザーを作成してアクティブ化するまでは、シングル サインオンを使用できません。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる NS1 SSO for Azure サインオン URL にリダイレクトされます。
- NS1 SSO for Azure サインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した NS1 SSO for Azure に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [NS1 SSO for Azure] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した NS1 SSO for Azure に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nuclino-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Nuclino を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nuclino-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Nuclino の間のシングル サインオンを構成する方法について説明します。

この記事では、Nuclino と Microsoft Entra ID を統合する方法について説明します。 Nuclino を Microsoft Entra ID と統合すると、次のことが可能になります。

- Nuclino にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Nuclino に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Nuclino でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Nuclino では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Nuclino では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Nuclino の追加

Microsoft Entra ID への Nuclino の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Nuclino を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Nuclino**」と入力します。
4. 結果のパネルから **[Nuclino]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Nuclino 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Nuclino に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Nuclino の関連ユーザーとの間にリンク関係を確立する必要があります。

Nuclino に対して Microsoft Entra SSO を構成およびテストするには、以下の手順に従います。

1. **Microsoft Entra SSO の構成**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Nuclino の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Nuclino のテスト ユーザーの作成** - Nuclino で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Nuclino**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** Initiated モードで構成する場合は、次の手順を行います。

    a. **[識別子]** ボックスに、`https://api.nuclino.com/api/sso/<UNIQUE-ID>/metadata` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://api.nuclino.com/api/sso/<UNIQUE-ID>/acs` のパターンを使用して URL を入力します

    Note

    これらの値は実際の値ではありません。 これらの値は、 **認証セクションの** 実際の識別子と応答 URL で更新します。これについては、この記事で後述します。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://app.nuclino.com/<UNIQUE-ID>/login` という形式で URL を入力します。

    Note

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 この値を取得するには、[Nuclino クライアント サポート チーム](mailto:contact@nuclino.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Nuclino アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
8. その他に、Nuclino アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | first\_name | User.givenname |
    | last\_name | User.surname |
9. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
10. **[Nuclino のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Nuclino の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Nuclino 企業サイトに管理者としてサインインします
2. アイコンを選択 **します**。

    [Image: [Microsoft Entra SSO] の横にある]
3. **Microsoft Entra SSO** を選択し、ドロップダウンから **[チームの設定**] を選択します。
4. 左側のナビゲーション ウィンドウから **[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** を選択します。
5. **[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** セクションで、次の手順に従います。

    [Image: Nuclino 構成]

    a. **[SAML-based single sign-on (SSO)](SAML ベースのシングル サインオン (SSO))** を選択します。

    b。 **[ACS URL (You need to copy and paste this to your SSO provider)] (ACS URL (この値をコピーして SSO プロバイダーにコピーしてください))** 値をコピーして、**[基本的な SAML 構成]** セクションの **[応答 URL]** ボックスに貼り付けます。

    c. **[Entity ID (You need to copy and paste this to your SSO provider)] (エンティティ ID (この値をコピーして SSO プロバイダーにコピーしてください))** 値をコピーして、**[基本的な SAML 構成]** セクションの **[識別子]** ボックスに貼り付けます。

    d. **[SSO URL]** テキスト ボックスに、前にコピーした **[ログイン URL]** の値を貼り付けます。

    e. **[エンティティ ID]** ボックスに、前にコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    f. ダウンロードした**証明書 (Base64)** ファイルをメモ帳で開きます。 その内容をクリップボードにコピーし、 **[Public certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/公開証明書)** ボックスに貼り付けます。

    g. [ **変更の保存] を選択します**。

#### Nuclino テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Nuclino に作成します。 Nuclino では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Nuclino にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

Note

ユーザーを手動で作成する必要がある場合は、[Nuclino サポート チーム](mailto:contact@nuclino.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Nuclino のサインオン URL にリダイレクトされます。
- Nuclino のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Nuclino に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Nuclino] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Nuclino に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/nulab-pass-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Nulab Pass (Backlog と Cacoo) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/nulab-pass-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-07-01
- Summary: Microsoft Entra ID と Nulab Pass (Backlog と Cacoo) の間でシングル サインオンを構成する方法について説明します。

この記事では、Nulab Pass (Backlog と Cacoo) と Microsoft Entra ID を統合する方法について説明します。 統合によって、以下のことが可能になります。

- 誰が Microsoft Entra ID 内の Nulab Pass にアクセスできるかを Microsoft Entra ID 内で制御する。
- ユーザーが各自の Microsoft Entra アカウントを使用して Nulab Pass に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Nulab Pass の SSO が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。 Nulab Pass は、**SP開始およびIDP開始**のSSOを両方サポートしています。

### ギャラリーから Nulab Pass を追加する

Nulab Pass の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Nulab Pass を追加します。

1. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
2. **[ギャラリーから追加]** セクションで、検索ボックスに「**Nulab Pass**」と入力します。
3. 結果パネルから **[Nulab Pass]** を選択し、アプリを追加します。
4. お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Nulab Pass の Microsoft Entra SSO を構成してテストする

B.Simon というテスト ユーザーを使用して Nulab Pass での Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Nulab Pass 内の関連ユーザーとの間にリンク関係を確立する必要があります。

Nulab Pass での Microsoft Entra SSO を構成してテストするには:

1. **Microsoft Entra SSO を構成**して、ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成**して、B.Simon を使って Microsoft Entra SSO をテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra SSO を使用できるようにします。
2. **Nulab Pass の SSO の構成**アプリケーション側で SSO 設定を構成します。
    1. **Nulab Pass のテスト ユーザーの作成** Microsoft Entra でのユーザー表現にリンクされる B.Simon に対応するユーザーを Nulab Pass 内に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Nulab Pass (Backlog および Cacoo)**&gt;**シングルサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML でシングル サインオンをセットアップします]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://apps.nulab.com/signin/spaces/<Space_Key>/saml` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://apps.nulab.com/signin/spaces/<Space_Key>/saml/callback` のパターンを使用して URL を入力します
6. 以下の手順を実行して、アプリケーションを **SP** Initiated モードで構成します。

    **[サインオン URL]** ボックスに、URL として「`https://apps.nulab.com/signin`」と入力します。

    注

    これらの値は実際の値ではなく、Nulab Pass 組織の設定で見つかった実際の識別子、応答 URL、サインオン URL で更新する必要があります。 組織設定内で以下を行います。

    1. 左側のメニューから **[シングル サインオン]** を選択します。
    2. **[管理]** ボタンを押して **[SAML 認証の管理]** ダイアログを表示します。
    3. **SP エンティティ ID** と **SP エンドポイント URL (ACS)** の値をコピーして Entra 側の構成に貼り付けます。
    4. 詳細については、「[SAML 認証の設定方法](https://support.nulab.com/hc/en-us/articles/6478805477401)」というドキュメントを参照してください。
7. Nulab Pass アプリケーションでは特定の形式の SAML アサーションが想定されているため、カスタム属性のマッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Nulab Pass ではこれがユーザーのメールにマップされていることが想定されています。 一覧の **user.mail** 属性、または組織構成に基づく適切な属性値を使用します。

    [Image: 画像]
8. [SAML **署名証明書**] セクションの [**SAML でのシングル サインオンの設定**] ページで、**証明書 (Base64)** を見つけます。 **[ダウンロード]** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Nulab Pass の設定]** セクションで、自身の要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Nulab Pass SSO の構成

SSO を構成する前に、**[ドメイン認証](https://support.nulab.com/hc/en-us/articles/6558108028825)**を構成する必要があります。

**Nulab Pass** で SSO を構成するには、アプリケーション構成から**証明書 (Base64)** と URL を設定して、SSO 接続が両側で設定されていることを確認します。 手順は次のとおりです。

1. Nulab Pass の組織設定に移動します。
2. 左側のメニューから **[シングル サインオン]** を選択します。
3. **[管理]** ボタンを押して **[SAML 認証の管理]** ダイアログを表示します。
4. 次のように入力します。
    - IdP エンティティ ID
    - IdP エンドポイント URL
    - X.509 証明書 (Base64)

詳細については、「[SAML 認証の設定方法](https://support.nulab.com/hc/en-us/articles/6478805477401)」というドキュメントを参照してください。

#### Nulab Pass のテスト ユーザーの作成

次に、[管理アカウントを追加する](https://support.nulab.com/hc/en-us/articles/6480291067801)ことで、Nulab Pass 内に`Britta Simon`というユーザーを作成します。 SSO を使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

次に、以下のいずれかの選択肢を使用して Microsoft Entra SSO 構成をテストします。

##### SP開始:

- [ **このアプリケーションをテスト** して Nulab Pass にリダイレクトしてサインインする] を選択します。
- または、Nulab Pass のサインイン ページに直接移動し、そこからフローを開始します。

##### IDP 開始:

- [ **このアプリケーションをテスト** して、SSO 対応 Nulab Pass に自動的にサインインする] を選択します。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Nulab Pass] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。 IDP モードで構成されている場合は、SSO が有効になった Nulab Pass に自動的にサインインされます。 [マイ アプリの詳細について確認してください](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/numlyengage-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に NumlyEngage™ を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/numlyengage-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と NumlyEngage™ の間にシングル サインオンを構成する方法について説明します。

この記事では、NumlyEngage™ と Microsoft Entra ID を統合する方法について説明します。 NumlyEngage™ を Microsoft Entra ID と統合すると、次のことができます。

- NumlyEngage™ にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って NumlyEngage™ に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- NumlyEngage™ でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- NumlyEngage™ では、**SP** Initiated SSO がサポートされます
- NumlyEngage™ を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は条件付きアクセスから拡張されます。 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)でセッション制御を適用する方法について説明します。

### ギャラリーからの NumlyEngage™ の追加

Microsoft Entra ID への NumlyEngage™ の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に NumlyEngage™ を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**NumlyEngage™**」と入力します。
4. 結果のパネルから **[NumlyEngage™]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### NumlyEngage™ 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、NumlyEngage™ に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと NumlyEngage™ の関連ユーザーとの間にリンク関係を確立する必要があります。

NumlyEngage™ に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **NumlyEngage™ SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **NumlyEngage™ テストユーザーを作成** - NumlyEngage™ 内で B.Simon に対応するユーザーを作成し、それを Microsoft Entra ユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**NumlyEngage™**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.numly.io/registration?mail=<CUSTOM_IDENTIFIER>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `urn:amazon:cognito:sp:<NUMLY_ENGAGE_SPECIFIC_IDENTIFIER>`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.NUMLYENGAGE_SPECIFIC_amazoncognito.com/saml2/idpresponse`

    注

    これらの値は実際の値ではありません。 これらの値を実際のサインオン URL、応答 URL、識別子で更新してください。 これらの値を取得するには、[NumlyEngage™ クライアント サポート チーム](mailto:numlyengage-support@numly.io)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. NumlyEngage™ アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、NumlyEngage™ アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザーのメールアドレス |
    | 電話番号 | ユーザー.電話番号 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### NumlyEngage SSO の構成

**NumlyEngage™** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [NumlyEngage™ のサポート チーム](mailto:numlyengage-support@numly.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### NumlyEngage のテスト ユーザーの作成

このセクションでは、NumlyEngage™ で B.Simon というユーザーを作成します。 [NumlyEngage™ サポート チーム](mailto:numlyengage-support@numly.io)と連携して、NumlyEngage™ プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで NumlyEngage™ タイルを選択すると、SSO を設定した NumlyEngage™ に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/observepoint-web-assurance-tutorial"} -->
## Microsoft Entra ID で ObservePoint のシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/observepoint-web-assurance-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-07-15
- Summary: Microsoft Entra ID と ObservePoint の間のシングル サインオンを構成する方法について説明します。

この記事では、ObservePoint と Microsoft Entra ID を統合する方法について説明します。 ObservePoint と Microsoft Entra ID を統合すると、次のことができます。

- ObservePoint にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ObservePoint に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ObservePoint でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ObservePoint では、 **IDP** によって開始される SSO のみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから ObservePoint を追加する

Microsoft Entra ID への ObservePoint の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ObservePoint を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ObservePoint**」と入力します。
4. 結果パネルから **[ObservePoint** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ObservePoint 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ObservePoint に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ObservePoint の関連ユーザーとの間にリンク関係を確立する必要があります。

ObservePoint に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ObservePoint SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ObservePoint テスト ユーザーの作成** - ObservePoint で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ObservePoint**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、値を入力します。 `Observepoint`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.observepoint.com/saml/callback/<companyname>`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、 [ObservePoint サポート チーム](mailto:support@observepoint.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. ObservePoint アプリケーションでは、特定の形式の SAML アサーションが使用されるため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、ObservePoint アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | 名字 | User.surname |
    | メール | User.mail |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **ObservePoint のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ObservePoint SSO を構成する

**ObservePoint** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [ObservePoint サポート チーム](mailto:support@observepoint.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### ObservePoint のテスト ユーザーを作成する

このセクションでは、ObservePoint で B.Simon というユーザーを作成します。 [ObservePoint サポート チーム](mailto:support@observepoint.com)と協力して、ObservePoint プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した ObservePoint に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [ObservePoint] タイルを選択すると、SSO を設定した ObservePoint に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oc-tanner-tutorial"} -->
## Microsoft Entra ID と連携したシングルサインオンのために O.C. Tanner - AppreciateHub を設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oc-tanner-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と O.C. Tanner - AppreciateHub の間にシングル サインオンを構成する方法について説明します。

この記事では、O.C. Tanner - AppreciateHub と Microsoft Entra ID を統合する方法について説明します。 O.C. Tanner - AppreciateHub と Microsoft Entra ID を統合すると、次のことが可能になります。

- O.C. Tanner - AppreciateHub にアクセスできるユーザーを Microsoft Entra ID 内で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して O.C. Tanner - AppreciateHub に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- O.C. Tanner - AppreciateHub でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- O.C. Tanner - AppreciateHub では、**IDP** Initiated SSO がサポートされます。

### ギャラリーから O.C. Tanner - AppreciateHub を追加する

Microsoft Entra ID への O.C. Tanner - AppreciateHub の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に O.C. Tanner - AppreciateHub を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**O.C. Tanner - AppreciateHub**」と入力します。
4. 結果のパネルから **[O.C. Tanner - AppreciateHub]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### O.C. Tanner - AppreciateHub のための Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、O.C. Tanner - AppreciateHub 用の Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと O.C. Tanner - AppreciateHub の関連ユーザーとの間にリンク関係を確立する必要があります。

O.C. Tanner - AppreciateHub 用に Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **O.C. Tanner - AppreciateHub の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **O.C. Tanner - AppreciateHub テスト ユーザーの作成** - O.C. Tanner - AppreciateHub で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[O.C. Tanner - AppreciateHub]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[O.C. Tanner - AppreciateHub の設定]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### O.C. Tanner - AppreciateHub SSO の構成

**O.C. Tanner - AppreciateHub** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [O.C. Tanner - AppreciateHub サポート チーム](mailto:sso@octanner.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### O.C. Tanner - AppreciateHub テスト ユーザーの作成

このセクションの目的は、O.C. Tanner - AppreciateHub で Britta Simon というユーザーを作成することです。

**O.C. Tanner - AppreciateHub で Britta Simon というユーザーを作成するには、次の手順に従います。**

[O.C. Tanner - AppreciateHub サポート チーム](mailto:sso@octanner.com)に、Microsoft Entra ID 内のユーザー名 Britta Simon と同じ値の nameID 属性を持つユーザーを作成することを依頼します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した O.C. Tanner - AppreciateHub に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [O.C. Tanner - AppreciateHub] タイルを選択すると、SSO を設定した O.C. Tanner - AppreciateHub に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ocurus-tutorial"} -->
## Microsoft Entra ID で Ocurus for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ocurus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Ocurus の間でシングル サインオンを構成する方法について説明します。

この記事では、Ocurus と Microsoft Entra ID を統合する方法について説明します。 Ocurus と Microsoft Entra ID を統合すると、次のことができます。

- Ocurus にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Ocurus に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Ocurus でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Ocurus では、 **SP** によって開始される SSO のみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Ocurus を追加する

Microsoft Entra ID への Ocurus の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Ocurus を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Ocurus**」と入力します。
4. 結果パネルから **Ocurus** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Ocurus の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Ocurus に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Ocurus の関連ユーザーとの間にリンク関係を確立する必要があります。

Ocurus に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Ocurus SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Ocurus のテストユーザーを作成し、Microsoft Entra ユーザー表現に関連付けて B.Simon に対応するユーザーを設定します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Ocurus**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://solarturbines.ocurus.com/saml2/ms/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://solarturbines.ocurus.com/saml2/ms/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://solarturbines.ocurus.com/sso-ms`
6. Ocurus アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、Ocurus アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | catcupid | ユーザー.社員ID |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Ocurus SSO の構成

**Ocurus** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Ocurus サポート チーム](mailto:support@ocurus.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Ocurus のテスト ユーザーの作成

このセクションでは、Ocurus で B.Simon というユーザーを作成します。 [Ocurus サポート チーム](mailto:support@ocurus.com)と協力して、Ocurus プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Ocurus のサインオン URL にリダイレクトします。
- Ocurus のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Ocurus] タイルを選択すると、このオプションは Ocurus のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/officespace-software-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に OfficeSpace Software を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/officespace-software-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-17
- Summary: OfficeSpace Software に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、OfficeSpace Software と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを OfficeSpace Software に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [OfficeSpace Software テナント](https://www.officespacesoftware.com/)
- 管理者アクセス許可がある OfficeSpace Software のユーザー アカウント。

### 手順 1: OfficeSpace Software へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に*割り当て*という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、OfficeSpace Software へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定したら、次の手順に従って、これらのユーザーやグループを OfficeSpace Software に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### 手順 2: OfficeSpace Software にユーザーを割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを OfficeSpace Software に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- OfficeSpace Software にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 Azure の **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### 手順 3: プロビジョニング用に OfficeSpace Software を設定する

1. 適切なアクセス許可で OfficeSpace Software アカウントにサインインします。
2. ハンバーガーメニューの[管理]アコーディオンメニューで、**コネクタ**を選択します。
3. **[ディレクトリ同期] &gt; [SCIM]** に移動します。
4. SCIM で、**SCIM 認証キーの**をコピーします。 この値は、Microsoft Entra ID の OfficeSpace Software アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。
5. **SCIM for Authentication** チェックボックスをオンにします。
6. **SCIM 認証トークン**をコピーします。 この値は、OfficeSpace Software アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。

### 手順 4: ギャラリーから OfficeSpace Software を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に OfficeSpace Software を構成する前に、OfficeSpace Software を Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから OfficeSpace Software を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**OfficeSpace Software**」と入力し、検索ボックスから**[OfficeSpace Software]** を選択します。
4. 結果のパネルから **OfficeSpace Software** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の OfficeSpace Software]

### 手順 5: OfficeSpace Software への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、OfficeSpace Software でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

OfficeSpace Software のシングル サインオンに関する記事に記載されている手順に従って、OfficeSpace Software の SAML ベース [のシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/officespace-tutorial)を有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で OfficeSpace Software の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で、 **[OfficeSpace Software]** を選択します。

    [Image: アプリケーションの一覧の OfficeSpace Software リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、OfficeSpace Software テナント URL (形式は 'https://(YOURTENANTNAME).officespacesoftware.com/api/scim/v2/') とシークレット トークンを入力します。 [ **テスト接続** ] を選択して、Microsoft Entra ID が OfficeSpace Software に接続できることを確認します。 接続に失敗した場合は、OfficeSpace Software アカウントに必要な管理者アクセス許可があることを確認してから、やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から OfficeSpace Softwaree に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新操作で OfficeSpace Software のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: OfficeSpace Software ユーザー属性]
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/officespace-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に OfficeSpace Software を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/officespace-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と OfficeSpace Software 間にシングル サインオンを構成する方法について説明します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) 対応の OfficeSpace Software サブスクリプション

### OfficeSpace Software 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、OfficeSpace Software に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと OfficeSpace Software の関連ユーザーとの間にリンク関係を確立する必要があります。

OfficeSpace Software に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **OfficeSpace Software の SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**OfficeSpace Software**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<company name>.officespacesoftware.com/users/sign_in/saml`

    b。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`<company name>.officespacesoftware.com`

    注意

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [OfficeSpace Software クライアント サポート チーム](mailto:support@officespacesoftware.com) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. OfficeSpace Software アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 OfficeSpace Software アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集** ] アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. その他に、OfficeSpace Software アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、必要に応じて確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | User.mail |
    | 名前 | ユーザー表示名 |
    | 名（ファーストネーム） | User.givenname |
    | last\_name | User.surname |
8. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
9. **[SAML 署名証明書] セクション**で、PEM 証明書をダウンロードします。
10. [ **OfficeSpace Software のセットアップ]** セクションで、要件に基づいて 1 つ以上の適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### OfficeSpace Software の SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として OfficeSpace Software テナントにサインインします。
2. **[設定]** に移動し、[コネクタ] を選択**します**。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) ドロップダウンで [Connectors](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/コネクタ) が選択されているスクリーンショット。]
3. [ **SAML 認証] を**選択します。

    [Image: [SAML Authentication](SAML 認証) アクションが選択されている [Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) セクションを示すスクリーンショット。]
4. **[SAML Authentication]** セクションで、次の手順に従います。

    [Image: アプリ側でのシングル サインオンの構成]

    ある。 **[ログアウト プロバイダー URL]** テキスト ボックスに **[ログアウト URL]** の値を貼り付けます。

    b。 **[クライアント idp ターゲット URL]** テキスト ボックスに **[ログイン URL]** の値を貼り付けます。

    c. **[サムプリント]** の値を **[クライアント IDP 証明書フィンガープリント]** テキスト ボックスに貼り付けます。

    d. [ **設定の保存] を選択します**。

#### OfficeSpace Software のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを OfficeSpace Software に作成します。 OfficeSpace Software では、Just-In-Time ユーザー プロビジョニングがサポートされます。この設定は既定で有効です。 このセクションにはアクション項目はありません。 OfficeSpace Software にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注意

ユーザーを手動で作成する必要がある場合は、[OfficeSpace Software サポート チーム](mailto:support@officespacesoftware.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる OfficeSpace Software のサインオン URL にリダイレクトされます。
- OfficeSpace Software のサインオン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [OfficeSpace Software] タイルを選択すると、このオプションは OfficeSpace Software のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->
