# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 18)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 79

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pegasystems-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Pega Systems を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pegasystems-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: この記事では、Microsoft Entra ID と Pega Systems の間でシングル サインオンを構成する方法について説明します。

この記事では、Pega Systems と Microsoft Entra ID を統合する方法について説明します。 Pega Systems を Microsoft Entra ID と統合すると、次のことができます。

- Pega Systems にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Pega Systems に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pega Systems でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Pega Systems では、SP と IDP によって開始される SSO がサポートされます。

### ギャラリーからの Pega Systems の追加

Microsoft Entra ID への Pega Systems の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Pega Systems を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Pega Systems**」と入力します。
4. 結果パネルから **Pega Systems** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pega Systems 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Pega Systems に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Pega Systems の関連ユーザーの間にリンク関係を確立する必要があります。

Pega Systems に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pega Systems の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Pega Systems のテストユーザーの作成** - B.Simon に対応する Pega Systems のユーザーを作成し、それを Microsoft Entra 上のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Pega Systems**&gt;**シングル サインオンにアクセスします**。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成** ] ダイアログ ボックスで、IdP 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    1. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。

        `https://<customername>.pegacloud.io:443/prweb/sp/<instanceID>`
    2. [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。

        `https://<customername>.pegacloud.io:443/prweb/PRRestService/WebSSO/SAML/AssertionConsumerService`
6. SP 開始モードでアプリケーションを構成する場合は、[ **追加の URL の設定]** を選択し、次の手順を実行します。

    1. [ **サインオン URL** ] ボックスに、サインオン URL の値を入力します。
    2. [ **リレー状態** ] ボックスに、次のパターンで URL を入力します。 `https://<customername>.pegacloud.io/prweb/sso`

    注

    ここに示されている値はプレースホルダーです。 実際の識別子、応答 URL、サインオン URL、リレー状態 URL を使用する必要があります。 この記事で後述するように、Pega アプリケーションから識別子と応答 URL の値を取得できます。 リレー状態の値を取得するには、 [Pega Systems サポート チーム](https://www.pega.com/contact-us)にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Pega Systems アプリケーションには、特定の形式の SAML アサーションが必要です。 正しい形式のそれらを取得するには、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性を示しています。 **[編集**] アイコンを選択して、[**ユーザー属性**] ダイアログ ボックスを開きます。

    [Image: ユーザー属性]
8. 前のスクリーンショットに示されている属性に加えて、Pega Systems アプリケーションでは、いくつかの追加の属性が SAML 応答で返される必要があります。 [**ユーザー属性**] ダイアログ ボックスの [**ユーザー要求**] セクションで、次の手順を実行して、これらの SAML トークン属性を追加します。

    - `uid`
    - `cn`
    - `mail`
    - `accessgroup`
    - `organization`
    - `orgdivision`
    - `orgunit`
    - `workgroup`
    - `Phone`

    注

    これらの値は組織に固有です。 適切な値を指定します。

    1. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログ ボックスを開きます。

    [Image: [新しい要求の追加] を選択する]

    [Image: [ユーザー要求の管理] ダイアログ ボックス]

    1. [ **名前** ] ボックスに、その行に表示される属性名を入力します。
    2. **[名前空間**] ボックスは空のままにします。
    3. **[ソース**] で、[**属性**] を選択します。
    4. [ **ソース属性** ] ボックスの一覧で、その行に表示される属性値を選択します。
    5. [ **OK] を選択します**。
    6. **[保存] を選択します**。
9. [**SAML を使用した単一 Sign-On の設定**] ページの [**SAML 署名証明書**] セクションで、要件に従って**フェデレーション メタデータ XML** の横にある **[ダウンロード**] リンクを選択し、証明書をコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Pega Systems のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pega Systems の SSO の構成

1. **Pega Systems** 側でシングル サインオンを構成するには、別のブラウザー ウィンドウで管理者アカウントを使用して Pega ポータルにサインインします。
2. [ **作成**&gt;**SysAdmin**&gt;**Authentication Service** を選択します。

    [Image: 認証サービスの選択]
3. **[認証サービスの作成**] 画面で次の手順を実行します。

    [Image: [認証サービスの作成] 画面]

    1. **[種類**] ボックスの一覧で [**SAML 2.0**] を選択します。
    2. [ **名前** ] ボックスに任意の名前 ( **Microsoft Entra SSO** など) を入力します。
    3. [ **簡単な説明** ] ボックスに説明を入力します。
    4. **作成して開く**を選択します。
4. ID **プロバイダー (IdP) 情報** セクションで、[ **IdP メタデータのインポート** ] を選択し、ダウンロードしたメタデータ ファイルを参照します。 [ **送信] を** 選択してメタデータを読み込みます。

    [Image: ID プロバイダー (IdP) 情報セクション]

    インポートによって、次に示すように、IdP データが設定されます。

    [Image: インポートされた IdP データ]
5. **サービス プロバイダー (SP) の設定**セクションで、次の手順を実行します。

    [Image: サービス プロバイダーの設定]

    1. **エンティティ ID** の値をコピーし、[**基本的な SAML 構成**] セクションの **[識別子**] ボックスに貼り付けます。
    2. **Assertion Consumer Service (ACS) の場所**の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。
    3. [ **要求の署名を無効にする] を選択します**。
6. **[保存] を選択します**。

#### Pega Systems テスト ユーザーの作成

次に、Pega Systems で Britta Simon というユーザーを作成する必要があります。 [Pega Systems サポート チーム](https://www.pega.com/contact-us)と協力してユーザーを作成します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Pega Systems のサインオン URL にリダイレクトされます。
- Pega Systems のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Pega Systems に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Pega Systems タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Pega Systems に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pendo-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Pendo を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pendo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Pendo 間にシングル サインオンを構成する方法について学習します。

この記事では、Pendo と Microsoft Entra ID を統合する方法について説明します。 Pendo を Microsoft Entra ID と統合すると、次のことができます。

- Pendo にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Pendo に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pendo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Pendo では、**SP** 開始の SSO と **IDP** 開始の SSO の両方をサポートしています。

### ギャラリーからの Pendo の追加

Microsoft Entra ID への Pendo の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Pendo を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Pendo**」と入力します。
4. 結果パネルから **Pendo** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pendo 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Pendo に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Pendo の関連ユーザーとの間にリンク関係を確立する必要があります。

Pendo で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pendo SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Pendo テストユーザーの作成** - Microsoft Entra における B.Simon にリンクする、Pendo 内の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**Pendo**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML でのシングル サインオンのセットアップ** ] ページで、次の手順に従います。

    ある。 [ **識別子** ] テキスト ボックスに「 `PingConnect`」と入力します。 (この識別子が別のアプリケーションで既に使用されている場合は、 [Pendo サポート チーム](https://support.pendo.io/hc/articles/360034163971-Get-help-with-Pendo-from-Technical-Support)にお問い合わせください)。

    b。 [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://pingone.com/1.0/<CUSTOM_GUID>`

    注意

    これらの値は実際の値ではありません。 実際の識別子とリレー状態でこれらの値を更新します。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Pendo アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **名前** は **user.userprincipalname** にマップされています。 Pendo アプリケーションでは **、名前** が **user.mail** にマップされることを想定しているため、[ **編集** ] アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[フェデレーション メタデータ XML] で **アプリのフェデレーション メタデータ URL** (推奨) またはプレーン **XML** を見つけ、[ **ダウンロード** ] を選択してメタデータをダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Pendo のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pendo の SSO の構成

**Pendo** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** (推奨) またはダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Pendo サポート チーム](https://support.pendo.io/hc/articles/360034163971-Get-help-with-Pendo-from-Technical-Support)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Pendo のテスト ユーザーの作成

このセクションでは、Pendo で Britta Simon というユーザーを作成します。 [Pendo サポート チーム](https://support.pendo.io/hc/articles/360034163971-Get-help-with-Pendo-from-Technical-Support)と協力して、Pendo プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Pendo サインオン URL にリダイレクトされます。
- Pendo のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Pendo に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Pendo] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Pendo に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/penji-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Penji を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/penji-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Penji 間にシングル サインオンを構成する方法について学習します。

この記事では、Penji と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Penji を統合すると、次のことができます。

- Penji にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Penji に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Penji でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Penji では、 **SP** Initiated SSO がサポートされます

### ギャラリーからの Penji の追加

Microsoft Entra ID への Penji の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Penji を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を開きます。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Penji**」と入力します。
4. 結果パネルから **Penji** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Penji 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Penji に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Penji の関連ユーザーとの間にリンク リレーションシップを確立する必要があります。

Penji に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Penji SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Penji のテストユーザーを作成 - B.Simon に相当するユーザーを Penji で作成し、Microsoft Entra での表現とリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Penji**&gt;**シングル サインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://web.penjiapp.com/login`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cloud.penjiapp.com/saml/<ID>/sp/metadata`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cloud.penjiapp.com/saml/<ID>/login/callback`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Penji クライアント サポート チーム](mailto:support@penjiapp.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Penji アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Penji アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:0.9.2342.19200300.100.1.1 | ユーザー.ユーザープリンシパルネーム |
    | urn:oid:0.9.2342.19200300.100.1.3 | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Penji の SSO の構成

**Penji** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Penji サポート チーム](mailto:support@penjiapp.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Penji のテスト ユーザーの作成

このセクションでは、Penji で Britta Simon というユーザーを作成します。 [Penji サポート チーム](mailto:support@penjiapp.com)と協力して、Penji プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Penji のサインオン URL にリダイレクトされます。
- Penji のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Penji] タイルを選択すると、このオプションは Penji のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pennylane-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Penylane を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pennylane-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Pennylane の間にシングル サインオンを構成する方法について説明します。

この記事では、Penylane と Microsoft Entra ID を統合する方法について説明します。 会社の財務データに簡単にリアルタイムでアクセスできます。 会計に費やす時間を短縮し、手動のアクションを制限し、会計士とやり取りします。 Pennylane を Microsoft Entra ID を統合すると、次のことができます。

- Pennylane にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Pennylane に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Pennylane 用の Microsoft Entra シングル サインオンを構成してテストします。 Penylane では、 **SP** によって開始されるシングル サインオンのみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Pennylane と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Pennylane シングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Pennylane アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Pennylane を追加する

Microsoft Entra アプリケーション ギャラリーから Pennylane を追加して、Pennylane に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Pennylane**&gt;**シングルサインオン**に進む。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、値を入力します。 `pennylane`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://app.pennylane.com/auth/saml/callback`

    c. [ **サインオン URL** ] ボックスに、URL を入力します。 `https://app.pennylane.com/auth/login`
6. Pennylane アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Pennylane アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **Penylane のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Penylane SSO の構成

**Penylane** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Penylane サポート チーム](mailto:key-accounts-tech@pennylane.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Penylane テスト ユーザーの作成

このセクションでは、Pennylane で Britta Simon というユーザーを作成します。 [Penylane サポート チーム](mailto:key-accounts-tech@pennylane.com)と協力して、Penylane プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Penylane のサインオン URL にリダイレクトされます。
- Pennylane のサインオン URL に直接移動して、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Penylane] タイルを選択すると、このオプションは Penylane のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/people-tutorial"} -->
## Microsoft Entra ID で People for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/people-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と People 間にシングル サインオンを構成する方法について学習します。

この記事では、People と Microsoft Entra ID を統合する方法について説明します。 People を Microsoft Entra ID と統合すると、次のことができます。

- People にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って People に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- People でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ユーザーは **SP** によって開始される SSO をサポートします
- People モバイル アプリケーションを Microsoft Entra ID と共に構成して SSO を有効にできるようになりました。 この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの People の追加

Microsoft Entra ID への People の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに People を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を開きます。
3. **[ギャラリーから追加**] セクションで、検索ボックスに「**People」**と入力します。
4. 結果パネルから **[ユーザー]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### People 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、People に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと People の関連ユーザーとの間にリンク関係を確立する必要があります。

People に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **People SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Peopleのテストユーザーを作成** - Microsoft Entraの表現にリンクされたPeople内のB.Simonの対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**People** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.peoplehr.net`

    b。 [ **識別子** ] ボックスに、URL を入力します。 `https://www.peoplehr.com`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.peoplehr.net/Pages/Saml/ConsumeAzureAD.aspx`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と応答 URL でこれらの値を更新してください。 これらの値を取得するには [、People クライアント サポート チーム](mailto:customerservices@peoplehr.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **People のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### People の SSO の構成

1. 別の Web ブラウザー ウィンドウで、People 企業サイトに管理者としてサインインします
2. 左側のメニューで、[ **設定]** を選択します。

    [Image: [設定] が選択されている左側のメニューを示すスクリーンショット。]
3. [ **会社**] を選択します。

    [Image: [設定] メニューで [会社] が選択されていることを示すスクリーンショット。]
4. **[シングル サインオン] SAML メタデータ ファイルのアップロードで**、[**参照**] を選択して、ダウンロードしたメタデータ ファイルをアップロードします。

    [Image: シングル サインオンの構成]

#### People テスト ユーザーの作成

このセクションでは、People で B.Simon というユーザーを作成します。 [People クライアント サポート チーム](mailto:customerservices@peoplehr.com)と協力して、People プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる People のサインオン URL にリダイレクトされます。
- People のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ユーザー] タイルを選択すると、このオプションは People のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。

### People (Mobile) の SSO をテストする

1. People Mobile アプリケーションを開きます。 サインイン ページで、 **電子メール ID を** 入力し、[ **シングル サインオン**] を選択します。

    [Image: サインイン]
2. **組織の UserID を**入力し、[**次へ**] を選択します。

    [Image: 電子メール]
3. 最後に、サインインに成功すると、アプリケーションのホームページが次のように表示されます。

    [Image: かつての1度]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/peoplecart-tutorial"} -->
## Microsoft Entra ID で Peoplecart for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/peoplecart-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra IDと Peoplecart の間でシングル サインオンを構成する方法について説明します。

この記事では、Peoplecart と Microsoft Entra ID を統合する方法について説明します。 Peoplecart を Microsoft Entra ID と統合すると、次のことができます。

- Peoplecart にアクセスできるユーザーをMicrosoft Entra IDで制御できます。
- ユーザーが自分のMicrosoft Entra アカウントを使用して Peoplecart に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Peoplecart でのシングル サインオン (SSO) が有効なサブスクリプション。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra IDでアプリケーションを追加または管理することもできます。 詳細については、「[Azure組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を参照してください。

### シナリオの説明

この記事では、テスト環境でシングル サインオンMicrosoft Entra構成し、テストします。

- Peoplecart では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Peoplecart の追加

Microsoft Entra IDへの Peoplecart の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Peoplecart を追加する必要があります。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Peoplecart**」と入力します。
4. 結果パネルから **Peoplecart** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 Microsoft 365 wizards.

### Peoplecart のMicrosoft Entra SSO の構成とテスト

**B.Simon** というテストユーザーを使用して、Peoplecart と共に Microsoft Entra SSO を構成し、テストします。 SSO を機能させるには、Microsoft Entra ユーザーと Peoplecart の関連ユーザーとの間にリンク関係を確立する必要があります。

Peoplecart Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**を構成する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Microsoft Entra のシングル サインオンを B.Simon でテストします。
    2. **Microsoft Entra テストユーザーを割り当てる** - B.Simon が Microsoft Entra シングルサインオンを使用できるようにします。
2. **Peoplecart SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Peoplecart のテストユーザーを作成する** - Peoplecart で B.Simon に対応するユーザーを作り、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Peoplecart]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantname>.peoplecart.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantname>.peoplecart.com/SignIn.aspx`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、Peoplecart クライアント サポート チームに問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Peoplecart のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra のテスト ユーザーを作成して割り当てる

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Peoplecart SSO の構成

**Peoplecart** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を Peoplecart サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Peoplecart のテスト ユーザーの作成

このセクションでは、Peoplecart で Britta Simon というユーザーを作成します。 Peoplecart サポート チームと協力して、Peoplecart プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra シングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Peoplecart のサインオン URL にリダイレクトされます。
- Peoplecart のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft My Appsを使用できます。 My Appsで [Peoplecart] タイルを選択すると、このオプションは Peoplecart のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/per-angusta-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Per Angusta を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/per-angusta-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Per Angusta 間にシングル サインオンを構成する方法について学習します。

この記事では、Per Angusta と Microsoft Entra ID を統合する方法について説明します。 Per Angusta を Microsoft Entra ID と統合すると、次のことができます。

- Per Angusta にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Per Angusta に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Per Angusta でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Per Angusta では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから Per Angusta を追加する

Microsoft Entra ID への Per Angusta の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Per Angusta を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動する。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Per Angusta**」と入力します。
4. 結果パネルから **Per Angusta** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Per Angusta 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Per Angusta に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Per Angusta の関連ユーザーとの間にリンク関係を確立する必要があります。

Per Angusta に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Per Angusta SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Per Angusta のテストユーザーを作成 - Per Angusta** における B.Simon の対応ユーザーを作成し、Microsoft Entra におけるそのユーザーにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Per Angusta**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<SUBDOMAIN>.per-angusta.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.per-angusta.com/saml/consume`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.per-angusta.com/saml/init`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Per Angusta クライアント サポート チーム](mailto:support@per-angusta.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Per Angusta SSO の構成

1. Per Angusta 企業サイトに管理者としてログインします。
2. [Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) タブに移動します。

    [Image: 管理者アカウントを示すスクリーンショット]
3. 左側のメニューの **[構成]** で、[ **SSO SAML**] を選択します。

    [Image: 構成を示すスクリーンショット]
4. 構成ページで、次の手順を実行します。

    [Image: メタデータを示すスクリーンショット]

    [Image: SSO SAML 証明書 SAML 証明書を示すスクリーンショット]

    1. **[応答 URL]** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。
    2. **エンティティ ID の値を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスにこの値を貼り付けます。
    3. **SAML 初期化 URL** の値をコピーし、[**基本的な SAML 構成**] セクションの **[サインオン URL**] テキスト ボックスにこの値を貼り付けます。
    4. 接続をテストする前に **、アクティブ** SSO チェックボックスを有効にします。
    5. **[XML URL**] ボックスに、前にコピーした**アプリのフェデレーション メタデータ URL** の値を貼り付けます。
    6. [ **要求** ] ボックスで、ドロップダウンから **[電子メール** ] を選択します。
    7. [ **NameID Format]\(NameID 形式** \) ボックスで、ドロップダウンから `urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified` を選択してください。
    8. **[保存] を選択します**。

#### Per Angusta テスト ユーザーの作成

このセクションでは、Per Angusta で Britta Simon というユーザーを作成します。 [Per Angusta サポート チーム](mailto:support@per-angusta.com)と協力して、Per Angusta プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Per Angusta のサインオン URL にリダイレクトされます。
- Per Angusta のサインオン URLに直接アクセスし、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Per Angusta] タイルを選択すると、このオプションは Per Angusta のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/perceptionunitedstates-tutorial"} -->
## Microsoft Entra ID で UltiPro Perception for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/perceptionunitedstates-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と UltiPro Perception の間のシングル サインオンを構成する方法について説明します。

この記事では、UltiPro Perception と Microsoft Entra ID を統合する方法について説明します。 UltiPro Perception と Microsoft Entra ID を統合すると、次のことが可能になります。

- UltiPro Perception にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して UltiPro Perception に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- UltiPro Perception でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- UltiPro Perception では、 **IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから UltiPro Perception を追加する

UltiPro Perception と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に UltiPro Perception をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「UltiPro Perception**」と入力します。
4. 結果パネルから **UltiPro Perception** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### UltiPro Perception 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、UltiPro Perception に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、UltiPro Perception での関連ユーザーとの間にリンク関係を確立する必要があります。

UltiPro Perception 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **UltiPro Perception SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **UltiPro Perception テスト ユーザーの作成** - UltiPro Perception で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**UltiPro Perception**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** ページで、次の手順を実行します。

    a. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://perception.kanjoya.com/sso?idp=<entity_id>`

    b。 **UltiPro Perception** アプリケーションでは、**Microsoft Entra Identifier** の値を &lt;entity\_id&gt; として必要とします。この値は、**UltiPro Perception のセットアップ** セクションから取得して URI エンコードする必要があります。 URI にエンコードされた値を取得するには、 **http://www.url-encode-decode.com/** リンクを使用してください。

    c. URI でエンコードされた値を取得した後、次に示すように **応答 URL** と組み合わせます。

    `https://perception.kanjoya.com/sso?idp=<URI encoded entity_id>`

    d. 上記の値を **[応答 URL** ] ボックスに貼り付けます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **UltiPro Perception のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### UltiPro Perception SSO を構成する

1. 別の Web ブラウザー ウィンドウで、管理者として UltiPro Perception 企業サイトにサインオンします。
2. メイン ツールバーで、[ **アカウント設定]** を選択します。

    [Image: メイン ツール バーから [アカウント設定] が選択されていることを示すスクリーンショット。]
3. [ **アカウント設定]** ページで、次の手順を実行します。

    [Image: UltiPro Perception ユーザー]

    a. [ **会社名]** ボックスに、 **会社**の名前を入力します。

    b。 [ **アカウント名]** ボックスに、 **アカウント**の名前を入力します。

    c. **[既定 Reply-To 電子メール**] テキスト ボックスに、有効な**電子メール**を入力します。

    d. **SAML 2.0** として **SSO Identity Provider を**選択します。
4. [ **SSO 構成]** ページで、次の手順を実行します。

    [Image: UltiPro Perception SSO の構成。]

    a. **[SAML NameID Type]\(SAML NameID の種類**\) を EMAIL として選択**します**。

    b。 [ **SSO 構成名]** ボックスに、 **構成**の名前を入力します。

    c. [ **Identity Provider Name]\(ID プロバイダー名\)** ボックスに、 **Microsoft Entra Identifier** の値を貼り付けます。

    d. **[SAML Domain]\(SAML ドメイン\) テキストボックス**に、@contoso.comなどのドメインを入力します。

    e. [ **もう一度アップロード]** を選択して **、メタデータ XML** ファイルをアップロードします。

    f. [ **更新] を**選択します。

#### UltiPro Perception テスト ユーザーを作成する

このセクションでは、UltiPro Perception で Britta Simon というユーザーを作成します。 [UltiPro Perception サポート チーム](https://www.ultimatesoftware.com/Contact/ContactUs)と協力して、UltiPro Perception プラットフォームにユーザーを追加します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した UltiPro Perception に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [UltiPro Perception] タイルを選択すると、SSO を設定した UltiPro Perception に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/perceptyx-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Perceptyx を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/perceptyx-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Perceptyx の間にシングル サインオンを構成する方法について学習します。

この記事では、Perceptyx と Microsoft Entra ID を統合する方法について説明します。 Perceptyx を Microsoft Entra ID と統合すると、次のことができます。

- Perceptyx にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Perceptyx に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Perceptyx でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Perceptyx では、**IDP** によって開始される SSO がサポートされます

### ギャラリーからの Perceptyx の追加

Microsoft Entra ID への Perceptyx の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Perceptyx を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Perceptyx**」と入力します。
4. 結果ウィンドウで **[Perceptyx]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Perceptyx 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Perceptyx に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Perceptyx の関連ユーザーとの間にリンク関係を確立する必要があります。

Perceptyx に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Perceptyx SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Perceptyx テスト ユーザーの作成** - Microsoft Entra にある B.Simon の表現にリンクされる、Perceptyx で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Perceptyx** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** ページで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SubDomain>.perceptyx.com/<SurveyId>/index.cgi/saml-login?o=B`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SubDomain>.perceptyx.com/<SurveyId>/index.cgi/saml-login?o=P`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Perceptyx クライアント サポート チーム](mailto:customersupport@perceptyx.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Perceptyx SSO の構成

**Perceptyx** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Perceptyx サポート チーム](mailto:customersupport@perceptyx.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Perceptyx テスト ユーザーの作成

このセクションでは、Perceptyx で B.Simon というユーザーを作成します。 [Perceptyx サポート チーム](mailto:customersupport@perceptyx.com)と連携して、Perceptyx プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Perceptyx に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Perceptyx] タイルを選択すると、SSO を設定した Perceptyx に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/percolate-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Percolate を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/percolate-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: この記事では、Microsoft Entra ID と Percolate の間でシングル サインオンを構成する方法について説明します。

この記事では、Percolate と Microsoft Entra ID を統合する方法について説明します。 Percolate と Microsoft Entra ID を統合すると、次のことができます。

- Percolate にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Percolate に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオンが有効になっている Percolate サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Percolate では、SP Initiated SSO と IdP Initiated SSO がサポートされます。

### ギャラリーから Percolate を追加する

Microsoft Entra ID への Percolate の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Percolate を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;に進み、**新しいアプリ**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Percolate**」と入力します。
4. 結果のパネルから **[Percolate** ] を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Percolate の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Percolate に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Percolate の関連ユーザーとの間にリンク関係を確立する必要があります。

Percolate に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Percolate SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Percolate のテストユーザーを作成** - Percolate 内で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Percolate**&gt;**シングル サインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成** ] ダイアログ ボックスでは、IdP 開始モードでアプリケーションを構成するためのアクションを実行する必要はありません。 アプリは既に Azure と統合されています。
6. SP 開始モードでアプリケーションを構成する場合は、[ **追加の URL の設定** ] を選択し、[ **サインオン URL** ] ボックスに「 **https://percolate.com/app/login**」と入力します。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **コピー** ] アイコンを選択して **アプリのフェデレーション メタデータ URL をコピーします**。 この URL を保存します。

    [Image: アプリのフェデレーション メタデータ URL をコピーする]
8. [ **Percolate のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Percolate SSO の構成

1. 新しい Web ブラウザー ウィンドウで、管理者として Percolate にサインインします。
2. ホーム ページの左側にある [設定] 選択します。

    [Image: [設定] を選択する]
3. 左側のウィンドウで、[**組織**] で **[SSO**] を選択します。

    [Image: [組織] で [SSO] を選択する]

    1. [ **ログイン URL** ] ボックスに、コピーした **ログイン URL** の値を貼り付けます。
    2. [ **エンティティ ID** ] ボックスに、コピーした **Microsoft Entra 識別子** の値を貼り付けます。
    3. メモ帳で、ダウンロードした base-64 でエンコードされた証明書を開きます。 その内容をコピーし、 **x509 証明書** ボックスに貼り付けます。
    4. [ **電子メール属性** ] ボックスに「 **emailaddress**」と入力します。
    5. **[ID プロバイダー メタデータ URL**] ボックスは省略可能なフィールドです。 **アプリのフェデレーション メタデータ URL を**コピーした場合は、このボックスに貼り付けることができます。
    6. [ **AuthNRequests に署名する必要がありますか?** ] ボックスの一覧で、[いいえ] を選択 **します**。
    7. [ **ENABLE SSO auto-provisioning]\(SSO 自動プロビジョニングを有効にする** \) の一覧で、[いいえ] を選択 **します**。
    8. **[保存] を選択します**。

#### Percolateのテストユーザーを作成

Microsoft Entra ユーザーが Percolate にサインインできるようにするには、ユーザーを Percolate に追加する必要があります。 手動で追加する必要があります。

ユーザー アカウントを作成するには、次の手順を実行します。

1. 管理者として Percolate にサインインします。
2. 左側のウィンドウで、[**組織**] の下の [**ユーザー**] を選択します。 [ **新しいユーザー**] を選択します。

    [Image: [新しいユーザー] を選択する]
3. [ **ユーザーの作成** ] ページで、次の手順を実行します。

    [Image: ユーザー作成ページ]

    1. [ **電子メール** ] ボックスに、ユーザーのメール アドレスを入力します。 たとえば、brittasimon@contoso.comします。
    2. [ **フル ネーム** ] ボックスに、ユーザーの名前を入力します。 たとえば、 **Brittasimon です**。
    3. [ **ユーザーの作成] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Percolate のサインオン URL にリダイレクトされます。
- Percolate のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP開始

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Percolate に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Percolate] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Percolate に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/perforce-helix-core-tutorial"} -->
## Perforce Helix Core の構成 - Microsoft Entra ID を使用したシングルサインオン用の Helix Authentication Service - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/perforce-helix-core-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Perforce Helix Core - Helix Authentication Service の間でシングル サインオンを構成する方法について説明します。

この記事では、Perforce Helix Core - Helix Authentication Service と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Perforce Helix Core - Helix Authentication Service を統合すると、次のことができます。

- Perforce Helix Core - Helix Authentication Service にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Perforce Helix Core- Helix Authentication Service に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

Perforce Helix Core - Helix Authentication Service は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Perforce Helix Core - Helix Authentication Service でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Perforce Helix Core - Helix Authentication Service では、**SP** Initiated SSO がサポートされます。

### ギャラリーから Perforce Helix Core - Helix Authentication Service を追加する

Microsoft Entra ID への Perforce Helix Core - Helix Authentication Service の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Perforce Helix Core - Helix Authentication Service を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Perforce Helix Core - Helix Authentication Service**」と入力します。
4. 結果のパネルから **[Perforce Helix Core - Helix Authentication Service]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Perforce Helix Core - Helix Authentication Service の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Perforce Helix Core - Helix Authentication Service に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Perforce Helix Core - Helix Authentication Service の関連ユーザーとの間にリンク関係を確立する必要があります。

Perforce Helix Core - Helix Authentication Service で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Perforce Helix Core - Helix Authentication Service SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Perforce Helix Core - Helix Authentication Service のテストユーザーを作成** - Perforce Helix Core - Helix Authentication Service で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Perforce Helix Core - Helix Authentication Service**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<HELIX-AUTH-SERVICE>.<CUSTOMER_HOSTNAME>.com/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<HELIX-AUTH-SERVICE>.<CUSTOMER_HOSTNAME>.com/saml/sso`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<HELIX-AUTH-SERVICE>.<CUSTOMER_HOSTNAME>.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Perforce Helix Core - Helix Authentication Service クライアント サポート チーム](mailto:support@perforce.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Perforce Helix Core - Helix Authentication Service SSO の構成

**Perforce Helix Core - Helix Authentication Service** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Perforce Helix Core - Helix Authentication Service サポート チーム](mailto:support@perforce.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Perforce Helix Core - Helix Authentication Service テスト ユーザーの作成

このセクションでは、Perforce Helix Core - Helix Authentication Service で Britta Simon というユーザーを作成します。 [Perforce Helix Core - Helix Authentication Service サポート チーム](mailto:support@perforce.com)と連携して、Perforce Helix Core - Helix Authentication Service プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Perforce Helix Core - Helix Authentication Service のサインオン URL にリダイレクトされます。
- Perforce Helix Core - Helix Authentication Service のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Perforce Helix Core - Helix Authentication Service] タイルを選択すると、このオプションは Perforce Helix Core - Helix Authentication Service のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/performancecentre-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用の PerformanceCentre を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/performancecentre-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PerformanceCentre の間のシングル サインオンを構成する方法について説明します。

この記事では、PerformanceCentre と Microsoft Entra ID を統合する方法について説明します。 PerformanceCentre と Microsoft Entra ID の統合には、次の利点があります。

- PerformanceCentre にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して PerformanceCentre に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PerformanceCentre でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- PerformanceCentre では、**SP** Initiated SSO がサポートされます

### ギャラリーからの PerformanceCentre の追加

PerformanceCentre と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に PerformanceCentre をギャラリーから追加する必要があります。

**ギャラリーから PerformanceCentre を追加するには、次の手順を実行します。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **PerformanceCentre**」と入力し、結果パネルで **PerformanceCentre** を選択し、[ **追加** ] ボタンを選択してアプリケーションを追加します。

    [Image: 結果リストの PerformanceCentre]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、PerformanceCentre で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと、PerformanceCentre での関連ユーザーとの間にリンク関係が確立されている必要があります。

PerformanceCentre で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を満たす必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **PerformanceCentre のシングル サインオンを構成する** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **PerformanceCentre テストユーザーを作成する** - PerformanceCentre において、Microsoft Entra 上のユーザー表現にリンクされた Britta Simon の対応としてテストユーザーを作成します。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

PerformanceCentre で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**PerformanceCentre** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [PerformanceCentre のドメインと URL] のシングル サインオン情報]

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `http://<companyname>.performancecentre.com/saml/SSO`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `http://<companyname>.performancecentre.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[PerformanceCentre クライアント サポート チーム](https://www.performio.co/contact-us)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[PerformanceCentre のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### PerformanceCentre シングル サインオンの構成

1. **PerformanceCentre** 企業サイトに管理者としてサインオンします。
2. 左側のタブで、[ **構成**] を選択します。

    [Image: [PerformanceCenter] メニューのスクリーンショット。[Configure](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) が選択されています。]
3. 左側のタブで、[ **その他**] を選択し、[ **シングル サインオン**] を選択します。

    [Image: [Configure](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) タブのスクリーンショット。[Miscellaneous](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/その他) メニューから [Single Sign On](シングル サインオン) が選択されています。]
4. **[Protocol]** で **[SAML]** を選択します。

    [Image: [Single Sign On](シングル サインオン) セクションのスクリーンショット。[Protocol](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロトコル) メニューから [SAML] が選択されています。]
5. ダウンロードしたメタデータ ファイルをメモ帳で開き、内容をコピーし、[ **ID プロバイダー メタデータ** ] ボックスに貼り付けて、[保存] を選択 **します**。

    [Image: ID プロバイダー メタデータのテキストボックスのスクリーンショット。]
6. **[Entity Base URL]** と **[Entity ID URL]** の値が正しいことを確認します。

    [Image: Microsoft Entra シングル サインオン]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### PerformanceCentre のテスト ユーザーの作成

このセクションの目的は、PerformanceCentre で Britta Simon というユーザーを作成することです。

**PerformanceCentre で Britta Simon というユーザーを作成するには、次の手順を実行します。**

1. PerformanceCentre 企業サイトに管理者としてサインオンします。
2. 左側のメニューで [ **Interrelate**] を選択し、[参加者の **作成**] を選択します。

    [Image: [PerformanceCenter] 企業サイトの [Interrelate -Participants](相互関連 - 参加者) ページのスクリーンショット。[Create Participant](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/参加者の作成) ボタンが選択されています。]
3. **[Interrelate - Create Participant]** ダイアログ ボックスで、次の手順を実行します。

    [Image: [ユーザーの作成]]

    a. 関連するテキスト ボックスに Britta Simon の必要な属性を入力します。

    重要

    PerformanceCentre での Britta の User Name 属性は、Microsoft Entra ID でのユーザー名と同じにする必要があります。

    b。 **[ロールの選択]** で **[クライアント管理者]** を選択します。

    c. **保存** を選択します。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [PerformanceCentre] タイルを選択すると、SSO を設定した PerformanceCentre に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/perimeter-81-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Perimeter 81 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/perimeter-81-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Perimeter 81 間にシングル サインオンを構成する方法について学習します。

この記事では、Perimeter 81 と Microsoft Entra ID を統合する方法について説明します。 Perimeter 81 と Microsoft Entra ID を統合すると、次のことができます。

- Perimeter 81 にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Perimeter 81 に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Perimeter 81 サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Perimeter 81 では、 **SP Initiated SSO と IDP** Initiated SSO がサポートされます
- Perimeter 81 では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Perimeter 81 の追加

Microsoft Entra ID への Perimeter 81 の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Perimeter 81 を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Perimeter 81**」と入力します。
4. 結果パネルから **[Perimeter 81** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Perimeter 81 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Perimeter 81 に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Perimeter 81 の関連ユーザーとの間にリンク関係を確立する必要があります。

Perimeter 81 に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Perimeter 81 SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Perimeter 81 テストユーザーを作成する** - Perimeter 81 において、ユーザー B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Perimeter 81**&gt;**シングルサインオン**
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:perimeter81:<SUBDOMAIN>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.perimeter81.com/login/callback?connection=<SUBDOMAIN>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.perimeter81.com`

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Perimeter 81 クライアント サポート チーム](mailto:support@perimeter81.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **境界 81 のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Perimeter 81 の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Perimeter 81 企業サイトに管理者としてサインインします
2. **[設定]** に移動し、[**ID プロバイダー] を選択します**。

    [Image: Perimeter 81 の設定]
3. [ **プロバイダーの追加] ボタンを** 選択します。

    [Image: Perimeter 81 提供者を追加する]
4. **SAML 2.0 ID プロバイダー**を選択し、[**続行**] ボタンを選択します。
5. **[SAML 2.0 Identity Providers]\(SAML 2.0 ID プロバイダー\**) セクションで、次の手順を実行します。

    [Image: Perimeter 81 が SAML を設定する]

    ある。 [ **サインイン URL** ] テキスト ボックスに、 **ログイン URL** の値を貼り付けます。

    b。 [ **ドメイン エイリアス** ] テキスト ボックスに、ドメイン エイリアスの値を入力します。

    c. ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容を **[X509 署名証明書** ] ボックスに貼り付けます。

    注意

    または、[ **PEM/CERT ファイルのアップロード** ] を選択して、前にダウンロードした **証明書 (Base64)** をアップロードすることもできます。

    d. [ **完了] を選択します**。

#### Perimeter 81 のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Perimeter 81 に作成します。 Perimeter 81 では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Perimeter 81 にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Perimeter 81 のサインオン URL にリダイレクトされます。
- Perimeter 81 のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Perimeter 81 に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [境界 81] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Perimeter 81 に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/perimeterx-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に PerimeterX を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/perimeterx-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PerimeterX の間にシングル サインオンを構成する方法について説明します。

この記事では、PerimeterX と Microsoft Entra ID を統合する方法について説明します。 PerimeterX を Microsoft Entra ID と統合すると、次のことができます。

- PerimeterX にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って PerimeterX に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PerimeterX でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PerimeterX では、**IDP** Initiated SSO がサポートされます

### ギャラリーからの PerimeterX の追加

Microsoft Entra ID への PerimeterX の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に PerimeterX を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**PerimeterX**」と入力します。
4. 結果のパネルから **[PerimeterX]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PerimeterX 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、PerimeterX に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと PerimeterX の関連ユーザーとの間にリンク関係を確立する必要があります。

PerimeterX に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PerimeterX の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PerimeterX テスト ユーザーの作成** - Microsoft Entra の B.Simon にリンクする、PerimeterX での B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**PerimeterX**&gt;**シングルサインオンに移動する**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. PerimeterX アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングをご自分の SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、PerimeterX アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[PerimeterX のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PerimeterX の SSO の構成

1. 管理者のアクセス許可を使用して、PerimeterX コンソールにログインします。
2. **[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) &gt; [ACCOUNTS](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント)** の順に移動します

    [Image: PerimeterX の SSO]
3. **編集**を選択します
4. [Edit Account](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントの編集) ダイアログで、次の手順を実行します。

    [Image: PerimeterX の SSO - [Edit Account](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントの編集)]

    ある。 **[Enable Single Sign-On (SSO)](シングル サインオン (SSO) を有効にする)** をオンにします

    b。 **[Azure SAML]** を選択します。

    c. **[SAML Endpoint](SAML エンドポイント)** テキスト ボックスに、Azure portal からコピーした**ログイン URL** の値を貼り付けます。

    d. **[発行者]** テキストボックスに、コピーした Microsoft Entra 識別子の値を貼り付けます。

    え ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[X.509 証明書]** テキストボックスに貼り付けます。

    f. [ **変更の保存] を選択します**

#### PerimeterX のテスト ユーザーの作成

PerimeterX のテスト ユーザーの作成方法については、[PerimeterX の管理ユーザー ガイド](https://docs.perimeterx.com/pxconsole/docs/managing-users)を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

1. [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した PerimeterX に自動的にサインインします
2. Microsoft アクセス パネルを使用することができます。 アクセス パネルで [PerimeterX] タイルを選択すると、SSO を設定した PerimeterX に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/peripass-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Peripass を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/peripass-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-20
- Summary: Microsoft Entra ID から Peripass に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Peripass と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Peripass](https://www.peripass.com/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Peripass のユーザーを作成する
- アクセスが不要になった場合に Peripass でユーザーを削除する
- Microsoft Entra ID と Peripass の間でユーザー属性の同期を維持する
- Peripass に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Peripass テナント - テナントのセットアップについては [Peripass](https://www.peripass.com/) にお問い合わせください。
- テナントの構成に対するアクセス許可が与えられた Peripass ユーザー。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Peripass の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Peripass を構成する

1. テナントのサインイン URL を使用して Peripass にサインインします。
2. テナントの **[Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成)** に移動します。

    [Image: Peripass のメイン メニューのスクリーンショット]
3. **ID プロバイダーとプロビジョニング設定を**開きます。

    [Image: Peripass テナント構成のスクリーンショット]
4. 構成している ID プロバイダーに**プロバイダー名**を指定します。
5. プロビジョニングしたユーザーに割り当てる**ユーザー ロール**を選択します。
6. テナントの **SCIM エンドポイント**と **SCIM トークン**をメモします (後で Microsoft Entra のエンタープライズ アプリケーションでユーザー プロビジョニングを構成する際に必要になります。**Peripass テナント URL** および**シークレット トークン**として使用します)。

    [Image: Peripass ID プロバイダーの設定のスクリーンショット]
7. 構成の**変更内容を保存**します。

    [Image: プロバイダーの保存のスクリーンショット]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Peripass を追加する

Microsoft Entra アプリケーション ギャラリーから Peripass を追加して、Peripass へのプロビジョニングの管理を開始します。 SSO のために Peripass を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Peripass への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Peripass の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Peripass]** を選択します。

    [Image: アプリケーションの一覧の Peripass のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョン] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Peripass テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Peripass に接続できることを確認します。 接続に失敗した場合は、Peripass アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Peripass に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新操作で Peripass のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Peripass API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | ディスプレイ名 | 糸 |  |
    | エクスターナルID | 糸 |  |
    | 優先言語 | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | 名前.整形済み | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/periscope-data-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Periscope Data を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/periscope-data-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Periscope Data 間にシングル サインオンを構成する方法について説明します。

この記事では、Periscope Data と Microsoft Entra ID を統合する方法について説明します。 Periscope Data を Microsoft Entra ID と統合すると、次のことができます。

- Periscope Data にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Periscope Data に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Periscope Data でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Periscope Data では、**SP** Initiated SSO がサポートされます。

### ギャラリーから Periscope Data を追加する

Microsoft Entra ID への Periscope Data の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Periscope Data を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Periscope Data**」と入力します。
4. 結果のパネルから **[Periscope Data]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Periscope Data 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Periscope Data に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Periscope Data の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Periscope Data と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Periscope Data SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Periscope Data のテストユーザーを作成する** - Microsoft Entra におけるユーザー B.Simon の表現にリンクされた Periscope Data 内の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Periscope Data**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.periscopedata.com/<SITENAME>/sso`

    b。 **[サインオン URL]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://app.periscopedata.com/` |
    | `https://app.periscopedata.com/app/<SITENAME>` |

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新してください。 [Periscope Data クライアント サポート チーム](mailto:support@periscopedata.com)に問い合わせて、この値と、この記事で後述する**「Periscope Data Single Sign-On の構成**」セクションから取得する識別子の値を取得してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Periscope Data SSO を構成する

1. 別の Web ブラウザー ウィンドウで、管理者として Periscope Data にサインインします。
2. 左下隅にあるギア メニューを開き、 **[Billing](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/課金)**&gt;**[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)** メニューの順に開いてから、次の手順を実行します。 これらの設定にアクセスできるのは管理者のみです。

    [Image: [セキュリティ] ダイアログのスクリーンショット。設定が選択されています。]

    ある。 手順 5 の **[SAML 署名証明書]** の **[アプリのフェデレーション メタデータ URL]** をコピーし、ブラウザーで開きます。 XML ドキュメントが開きます。

    b。 **[シングル サインオン]** ボックスで、**[Microsoft Entra ID]** を選択します。

    c. **SingleSignOnService** タグを見つけて、**Location** 値を **[SSO URL](SSO の URL)** ボックスに貼り付けます。

    d. **SingleLogoutService** タグを見つけて、**Location** 値を **[SLO URL](SLO の URL)** ボックスに貼り付けます。

    え インスタンスの **[識別子]** をコピーして、**[基本的な SAML 構成]** セクションの **[識別子 (エンティティ ID)]** ボックスに貼り付けます。

    f. XML ファイルの最初のタグを見つけて、**entityID** の値をコピーし、 **[Issuer](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/発行者)** ボックスに貼り付けます。

    ジー SAML プロトコルで **IDPSSODescriptor** タグを見つけます。 そのセクション内で、**use=signing** を含む **KeyDescriptor** タグを見つけます。 **X509Certificate** の値をコピーして、 **[Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書)** ボックスに貼り付けます。

    h. 複数の領域を含むサイトでは、既定の領域を **[Default Space](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/既定の領域)** ドロップダウンから選択できます。 これは、新しいユーザーが初めて Periscope Data にログインし、Active Directory シングル サインオンを使用してプロビジョニングされるときに追加される領域です。

    一. 最後に、[**保存]** を選択し、「**ログアウト**」と入力して SSO 設定の変更を**確認**します。

    [Image: SSO 構成更新ダイアログのスクリーンショット。テキストボックスに「logout」と入力され、[確認] ボタンが選択されています。]

#### Periscope Data のテスト ユーザーの作成

Microsoft Entra ユーザーが Periscope Data にログインできるようにするには、ユーザーを Periscope Data にプロビジョニングする必要があります。 Periscope Data では、プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. Periscope Data に管理者としてログインします。
2. メニューの左下にある **[設定]** アイコンを選択し、[ **アクセス許可**] に移動します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) メニューのスクリーンショット。[Permissions](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/権限) が選択されています。]
3. **ADD USER** を選択し、次の手順を実行します。

    [Image: Periscope Data の構成情報]

    ある。 **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名を入力します (例: **Britta**)。

    b。 **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (例: **Simon**)。

    c. [ **電子メール** ] テキスト ボックスに、ユーザーの電子メール ( **brittasimon@contoso.com**など) を入力します。

    d. **追加**を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Periscope Data のサインオン URL にリダイレクトされます。
- Periscope Data のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Periscope Data] タイルを選択すると、このオプションは Periscope Data のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/personify-inc-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Personify Inc を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/personify-inc-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-20
- Summary: Microsoft Entra ID から Personify Inc にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Personify Inc と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して [ユーザーを Personify Inc](https://www.personifyinc.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Personify Inc. でユーザーを作成する
- アクセスが不要になったら、Personify Inc のユーザーを削除します。
- Microsoft Entra ID と Personify Inc の間でユーザー属性の同期を維持します。
- Personify Inc に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Personify Inc のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- [Microsoft Entra ID と Personify Inc. の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Personify Inc を構成する

[Personify Inc サポート](https://support.personifyinc.com/s/article/tutorial-azure-active-directory-single-sign-on-sso-integration-with-personify-inc?language=en_US#list-tenant-urls)にアクセスして、Microsoft Entra ID でのプロビジョニングをサポートするように Personify Inc を構成します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Personify Inc を追加する

Microsoft Entra アプリケーション ギャラリーから Personify Inc を追加して、Personify Inc へのプロビジョニングの管理を開始します。以前に SSO 用に Personify Inc を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Personify Inc への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて Personify Inc のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Personify Inc の自動ユーザー プロビジョニングを構成するには

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Personify Inc**. を選択します。

    [Image: アプリケーションの一覧の Personify Inc リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Personify Inc テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Personify Inc に接続できることを確認します。接続に失敗した場合は、Personify Inc アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Personify Inc に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために Personify Inc のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Personify Inc API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Personify Inc が必要とするもの |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | 表示名 | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | 優先言語 | 糸 |  |  |
    | 名前.名 | 糸 |  | ✓ |
    | 名前.姓 | 糸 |  | ✓ |
    | 名前.整形済み | 糸 |  |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | 住所群[type eq "勤務先"].地域 | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | phoneNumbers[type eq "ファックス"].value | 糸 |  |  |
    | エクスターナルID | 糸 | ✓ |  |
    | 名前.敬称 | 糸 |  |  |
    | name.honorificSuffix | 糸 |  |  |
    | ニックネーム | 糸 |  |  |
    | ユーザータイプ | 糸 |  |  |
    | ロケール | 糸 |  |  |
    | タイムゾーン | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:コストセンター | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:組織 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/petrovue-tutorial"} -->
## Microsoft Entra ID で PetroVue for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/petrovue-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PetroVue の間にシングル サインオンを構成する方法について説明します。

この記事では、PetroVue と Microsoft Entra ID を統合する方法について説明します。 PetroVue を Microsoft Entra ID と統合すると、次のことができます。

- PetroVue にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って PetroVue に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PetroVue でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PetroVue では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの PetroVue の追加

Microsoft Entra ID への PetroVue の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に PetroVue を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**PetroVue**」と入力します。
4. 結果のパネルから **[PetroVue]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PetroVue 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、PetroVue に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと PetroVue の関連ユーザーとの間にリンク関係を確立する必要があります。

PetroVue に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PetroVue SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PetroVue のテストユーザーを作成** - PetroVue で B.Simon に相当するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**PetroVue**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.petrolink.net/petrovue/rtv`

    b。 **[識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかの値を入力します。

    | 識別子 |
    | --- |
    | `PV4` |
    | `PetroVue` |
    |  |

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[PetroVue クライアント サポート チーム](mailto:ops@petrolink.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[PetroVue のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PetroVue SSO の構成

**PetroVue** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [PetroVue サポート チーム](mailto:ops@petrolink.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PetroVue テスト ユーザーの作成

このセクションでは、PetroVue で Britta Simon というユーザーを作成します。 [PetroVue サポート チーム](mailto:ops@petrolink.com)と連携して、PetroVue プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる PetroVue のサインオン URL にリダイレクトされます。
- PetroVue のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [PetroVue] タイルを選択すると、このオプションは PetroVue のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pexip-service-mmv-legacy-app"} -->
## Microsoft Entra ID でシングル サインオン用に Pexip Service を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pexip-service-mmv-legacy-app
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Pexip Service の間でシングル サインオンを構成する方法について説明します。

この記事では、Pexip Service と Microsoft Entra ID を統合する方法について説明します。 Pexip Service と Microsoft Entra ID を統合すると、次のことができます。

- Pexip サービスにアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Pexip Service に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pexip Service でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Pexip Service では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Pexip Service を追加する

Microsoft Entra ID への Pexip Service の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Pexip Service を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Pexip Service**」と入力します。
4. 結果パネルから **Pexip Service** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pexip Service の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Pexip Service に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Pexip Service の関連ユーザーとの間にリンク関係を確立する必要があります。

Pexip Service に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pexip Service の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Pexip Service のテスト ユーザーの作成** - Pexip Service で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Pexip Service**&gt;**Single のサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    **[サインオン URL]** テキスト ボックスに、URL として「`https://control.pexip.io`」と入力します。

    (省略可能)My Meeting Video (MMV) のお客様で、ブランド化された my.domain がある場合は、[サインオン URL] テキスト ボックスにブランド化された `https://my.domain` URL を入力します。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Pexip Service のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pexip Service の SSO の構成

**Pexip Service** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Pexip Service サポート チーム](https://help.pexip.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Pexip Service のテスト ユーザーの作成

このセクションでは、Pexip Service で Britta Simon というユーザーを作成します。 [Pexip Service サポート チーム](https://help.pexip.com)と協力して、Pexip Service プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Pexip Service のサインオン URL にリダイレクトされます。
- Pexip Service のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Pexip Service] タイルを選択すると、このオプションは Pexip Service のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/phenom-txm-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Phenom TXM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/phenom-txm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Phenom TXM の間でシングル サインオンを構成する方法について説明します。

この記事では、Phenom TXM と Microsoft Entra ID を統合する方法について説明します。 Phenom TXM を Microsoft Entra ID と統合すると、次のことができます。

- Phenom TXM にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Phenom TXM に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Phenom TXM のシングル サインオン (SSO) が有効なサブスクリプションと、サービス ハブでクライアント管理者ロールを持つユーザー アカウント。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Phenom TXM では、**SP** および **IDP** Initiated SSO がサポートされます。

### ギャラリーから Phenom TXM を追加する

Phenom TXM の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Phenom TXM を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Phenom TXM**」と入力します。
4. 結果のパネルから **[Phenom TXM]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Phenom TXM 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Phenom TXM で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーまたはグループと関連する Phenom TXM アプリケーションの間に割り当てのリレーションシップを確立し、Microsoft Entra ID がユーザーのメール アドレスをユーザー識別子として Phenom TXM に確実に渡す必要があります。

Phenom TXM で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Phenom TXM SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Phenom TXM テストユーザーを作成し、Microsoft Entra のユーザーとして B.Simon に対応する関係を構築します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Phenom TXM**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** テキスト ボックスに、サービス ハブからコピーした **[エンティティ ID]** を入力します。

    b。 **[応答 URL]** テキスト ボックスに、サービス ハブからコピーした**リダイレクト URI (ACS URL)** を入力します。

    1. 最初の **[応答 URL]** テキスト ボックスに、サービス ハブからコピーした **[リダイレクト URI (ACS URL)]** を入力し、[インデックス] の値を **0** に設定します。
    2. 2 つ目の **[応答 URL]** テキスト ボックスに、サービス ハブからコピーした **[Redirect URI (ACS URL) SP Initiated Flow] (リダイレクト URI (ACS URL) SP によって開始されるフロー)** を入力し、[インデックス] の値を **1** に設定します

    注

    **[応答 URL]** が **[既定値]** として設定されていることをチェックボックスを使って確認します。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | サインオン用URL |
    | --- | --- |
    | ステージング | `https://login-stg.phenompro.com` |
    | 生産 | `https://login.phenom.com` |
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Phenom TXM SSO の構成

1. Phenom TXM インスタンス サービス ハブにクライアント管理者ロールを持つユーザーとしてログインします。
2. **[設定]**タブ &gt;**[ID プロバイダー]** に移動します。
3. **[ID プロバイダー]** セクションで、次の手順を実行します。

    [Image: 構成設定を示すスクリーンショット。]

    [Image: ID プロバイダー メタデータを示すスクリーンショット。]

    ある。 ドロップダウン セレクターから **[SAML]** を選びます。

    b。 **[表示名]** ボックスに有効な名前を入力します。

    c. **[Single Sign-On URL](シングル サインオン URL)** テキストボックスに、コピーした**ログイン URL** の値を貼り付けます。

    d. **[Metadata URL](メタデータ URL)** テキスト ボックスに、コピーした **[アプリのフェデレーション メタデータ URL]** の値を貼り付けます。

    え **エンティティ ID の値を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスにこの値を貼り付けます。

    f. **[Redirect URI (ACS URL)](リダイレクト URI (ACS URL))** の値をコピーし、この値を **[Basic SAML Configuration](基本的な SAML 構成)** セクションの 1 つ目の **[応答 URL]** テキスト ボックスに貼り付けます。

    ジー **[Redirect URI (ACS URL) SP Initiated Flow](リダイレクト URI (ACS URL) SP によって開始されるフロー)** の値をコピーし、この値を **[Basic SAML Configuration](基本的な SAML 構成)** セクションの 2 つ目の **[応答 URL]** テキスト ボックスに貼り付けます。

#### Phenom TXM のテスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、Phenom TXM の Web サイトに管理者としてログインします。
2. [**ユーザー**] タブに移動し、[**Create Users]\(ユーザーの作成**\)&gt;**[Create single new User]\(単一の新しいユーザーの作成\**) を選択します。
3. **[ユーザーの作成]** ページで、以下の手順を実行します。

    ある。 [ **ユーザー情報** ] セクションで、テキスト ボックスに有効な **名**、 **姓** 、 **および職場の電子メールを** 入力し、[ **続行**] を選択します。

    [Image: [ユーザー情報] フィールドを示すスクリーンショット。]

    b。 [ **テナントの割り当て** ] セクションで、[ **テナント] を選択** し、[続行] を選択 **します**。

    [Image: テナント情報フィールドを示すスクリーンショット。]

    c. [ **ロールの割り当て** ] セクションで、ドロップダウンから **ロールを選択** し、[ **続行**] を選択します。

    [Image: ユーザーのロール マッピングを示すスクリーンショット。]

    d. [ **概要** ] セクションで、選択内容を確認し、[ **完了]** を選択してユーザーを作成します。

    [Image: Phenom TXM の概要セクションを示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Phenom TXM サインオン URL にリダイレクトされます。
- Phenom TXM のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Phenom TXM に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Phenom TXM] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Phenom TXM に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/phraseanet-tutorial"} -->
## Microsoft Entra ID で Phraseanet for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/phraseanet-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Phraseanet 間にシングル サインオンを構成する方法について学習します。

この記事では、Phraseanet と Microsoft Entra ID を統合する方法について説明します。 Phraseanet と Microsoft Entra ID の統合には、次の利点があります。

- Phraseanet にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントで Phraseanet に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Phraseanet でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Phraseanet では、**SP** によって開始される SSO がサポートされます

### ギャラリーから Phraseanet を追加する

Microsoft Entra ID への Phraseanet の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Phraseanet を追加する必要があります。

**ギャラリーから Phraseanet を追加するには、次の手順を実行します。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Phraseanet**」と入力し、結果パネルで **Phraseanet** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Phraseanet]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Phraseanet で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Phraseanet 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Phraseanet で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Phraseanet シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Phraseanet のテスト ユーザーを作成し、Microosoft Entra のユーザー表現である Britta Simon にリンクされるようにします。**
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Phraseanet で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Phraseanet** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [Phraseanet のドメインと URL] のシングル サインオン情報]

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.alchemyasp.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[Phraseanet クライアント サポート チーム](mailto:support@alchemy.fr)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Phraseanet の設定]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    ある。 ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Phraseanet のシングル サインオンの構成

**Phraseanet** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Peoplecart サポート チーム](mailto:support@alchemy.fr)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Phraseanet テスト ユーザーの作成

このセクションでは、Phraseanet で Britta Simon というユーザーを作成します。 [Phraseanet サポート チーム](mailto:support@alchemy.fr)と連携し、Phraseanet システムにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Phraseanet] タイルを選択すると、SSO を設定した Phraseanet に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/picturepark-tutorial"} -->
## Microsoft Entra ID で Picturepark for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/picturepark-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Picturepark の間のシングル サインオンを構成する方法について説明します。

この記事では、Picturepark と Microsoft Entra ID を統合する方法について説明します。 Picturepark を Microsoft Entra ID と統合すると、次のことが可能になります。

- Picturepark にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Picturepark に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Picturepark でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Picturepark では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Picturepark の追加

Microsoft Entra ID への Picturepark の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Picturepark を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Picturepark**」と入力します。
4. 結果のパネルから **[Picturepark]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Picturepark 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Picturepark に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Picturepark の関連ユーザーの間にリンク関係を確立する必要があります。

Picturepark に対して Microsoft Entra SSO を構成およびテストするには、以下の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Picturepark SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Picturepark テストユーザーの作成 - Microsoft Entra のユーザー表現にリンクされた Picturepark で B.Simon に対応するユーザーを作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Picturepark**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://<COMPANY_NAME>.current-picturepark.com` |
    | `https://<COMPANY_NAME>.picturepark.com` |
    | `https://<COMPANY_NAME>.next-picturepark.com` |
    |  |

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.picturepark.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Picturepark クライアント サポート チーム](https://picturepark.com/company/picturepark-customer-support)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. **[Picturepark のセットアップ]** セクションで、要件に従って適切な URL をコピーします。 **[ログイン URL]** には、次のパターンの値を使用します: `https://login.microsoftonline.com/_my_directory_id_/wsfed`

    注

    *my\_directory\_id* は Microsoft Entra サブスクリプションのテナント ID です。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Picturepark の SSO の構成

1. 別の Web ブラウザーのウィンドウで、Picturepark 企業サイトに管理者としてサインインします。
2. 上部のツール バーで、[ **管理ツール**] を選択し、[ **管理コンソール**] を選択します。

    [Image: 管理コンソール]
3. [ **認証**] を選択し、[ **ID プロバイダー] を選択します**。

    [Image: 認証]
4. [**ID プロバイダーの構成**] セクションで、次の手順を実行します。

    [Image: ID プロバイダーの構成]

    a. [**] を選択し、[**] を追加します。

    b。 構成の名前を入力します。

    c. [**既定として設定**] を選択します。

    d. **[発行者 URI]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    e. **[Trusted Issuer Thumb Print](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/信頼された発行者の拇印)** ボックスに、**[SAML 署名証明書]** セクションからコピーした**拇印**の値を貼り付けます。
5. **JoinDefaultUsersGroup を選択します**。
6. [**要求**] ボックスに **Emailaddress** 属性を設定するには、「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`」と入力し、[**保存]** を選択します。

    [Image: 構成]

#### Picturepark テスト ユーザーの作成

Microsoft Entra ユーザーが Picturepark にサインインできるようにするには、ユーザーを Picturepark にプロビジョニングする必要があります。 Picturepark の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. **Picturepark** テナントにサインインします。
2. 上部のツール バーで、[ **管理ツール**] を選択し、[ユーザー] を選択 **します**。

    [Image: ユーザー]
3. [ **ユーザーの概要** ] タブで、[ **新規**] を選択します。

    [Image: ユーザー管理]
4. **[Create User] (ユーザーの作成)** ダイアログで、プロビジョニングする有効な Microsoft Entra ユーザーを次の手順で設定します。

    [Image: ユーザーの作成]

    a. **[Email Address](電子メール アドレス)** テキストボックスに、ユーザーの**電子メール アドレス**を「`BrittaSimon@contoso.com`」と入力します。

    b。 **[Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード)** および **[Confirm Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/確認パスワード)** ボックスに、BrittaSimon の**パスワード**を入力します。

    c. **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの**名**を「**Britta**」と入力します。

    d. **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの**姓**を「**Simon**」と入力します。

    e. **[Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社)** ボックスに、ユーザーの**会社名**を入力します。

    f. **[Country](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/国)** テキストボックスに、ユーザーの**国/リージョン**を入力します。

    g. **[ZIP](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/郵便番号)** ボックスに、ユーザーの**郵便番号**を入力します。

    h. **[City](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/市区町村)** ボックスに、ユーザーの**市区町村名**を入力します。

    一. **[Language]** を選択します。

    j. **を選択して**を作成します。

注

Picturepark から提供されている他の Picturepark ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Picturepark のサインオン URL にリダイレクトされます。
- Picturepark のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Picturepark] タイルを選択すると、このオプションは Picturepark のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pingboard-provisioning-tutorial"} -->
## Microsoft Entra ID で自動ユーザー プロビジョニング用に Pingboard を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pingboard-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-21
- Summary: Pingboard に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に行うように、Microsoft Entra ID を構成する方法について学習します。

この記事の目的は、Microsoft Entra ID から Pingboard へのユーザー アカウントの自動プロビジョニングとプロビジョニング解除を有効にするために必要な手順を示することです。

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pingboard テナント ([Pro アカウント](https://pingboard.com/pricing))
- 管理者アクセス許可がある Pingboard のユーザー アカウント

注

Microsoft Entra プロビジョニング統合では、ご自分のアカウントから使用できる [Pingboard API](https://pingboard.docs.apiary.io/#) が利用されます。

### Pingboard へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリケーションへのアクセスを付与するユーザーを決定する際に "割り当て" という概念が使用されます。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーのみが同期されます。

プロビジョニング サービスを構成して有効にする前に、Pingboard アプリにアクセスする必要がある Microsoft Entra ID 内のユーザーを決定しておく必要があります。 その後、次の手順でこれらのユーザーを Pingboard アプリに割り当てることができます。

[エンタープライズ アプリケーションにユーザーを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Pingboard に割り当てる際の重要なヒント

Pingboard に単一の Microsoft Entra ユーザーを割り当てて、プロビジョニングの構成をテストすることをお勧めします。 その他のユーザーは後で割り当てることができます。

### Pingboard へのユーザー プロビジョニングの構成

このセクションでは、Pingboard のユーザー アカウント プロビジョニング API に Microsoft Entra ID を接続する手順を説明します。 Microsoft Entra ID でのユーザーの割り当てに基づいて、Pingboard で割り当て済みユーザー アカウントの作成、更新、および無効化を行うようにプロビジョニング サービスを構成することもできます。

ヒント

Pingboard で SAML ベースのシングル サインオンを有効にするには、[Azure Portal](https://portal.azure.com) で説明されている手順に従ってください。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Pingboard への自動ユーザー アカウント プロビジョニングを構成するには

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. シングル サインオンのために Pingboard を既に構成している場合は、検索フィールドで Pingboard のインスタンスを検索します。 それ以外の場合は、**[追加]** を選択してアプリケーション ギャラリーで **Pingboard** を検索します。 検索結果から **Pingboard** を選択してアプリケーションの一覧に追加します。
4. Pingboard のインスタンスを選択してから、**[プロビジョニング]** タブを選択します。
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Pingboard テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Pingboard に接続できることを確認します。 接続に失敗した場合は、Pingboard アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Pingboard に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Pingboard のユーザー アカウントとの照合に使用されます。 すべての変更をコミットするには、 **[保存]** を選択します。 詳細については、[ユーザー プロビジョニング属性マッピングのカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)に関するページを参照してください。
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログの読み方の詳細については、「[自動ユーザー アカウント プロビジョニングについてのレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)」をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pingboard-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Pingboard を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pingboard-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Pingboard 間にシングル サインオンを構成する方法について学習します。

この記事では、Pingboard と Microsoft Entra ID を統合する方法について説明します。 Pingboard と Microsoft Entra ID を統合すると、次のことができます。

- Pingboard にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Pingboard に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pingboard でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Pingboard では、**SP**開始のSSOと**IDP**開始のSSOがサポートされます。
- Pingboard では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pingboard-provisioning-tutorial)。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの PingBoard の追加

Microsoft Entra ID への Pingboard の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Pingboard を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Pingboard**」と入力します。
4. 結果パネルから **Pingboard** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pingboard 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Pingboard に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Pingboard の関連ユーザーとの間にリンク関係を確立する必要があります。

Pingboard で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pingboard の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Pingboard テストユーザーの作成** - PingboardでB.Simonに対応するユーザーを作成し、それをMicrosoft Entra内のユーザーとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Pingboard**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `http://app.pingboard.com/sp`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENTITY_ID>.pingboard.com/auth/saml/consume`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.pingboard.com/sign_in`

    注意

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Pingboard クライアント サポート チーム](https://help.workleap.com/en//) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Pingboard のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pingboard SSO の構成

1. Pingboard 側で SSO を構成するには、新しいブラウザー ウィンドウを開き、Pingboard アカウントにサインインします。 シングル サインオンを設定するには、PingBoard 管理者である必要があります。
2. 上部のメニューから、[**アプリ&gt;統合**] を選択します

    [Image: シングル サインオンの構成]
3. [ **統合** ] ページで、 **Microsoft Entra ID タイルを** 見つけて選択します。
4. 表示されるダイアログで、[ **構成**] を選択します。
5. 次のページに、"Azure SSO 統合が有効になっている" ことを示す通知が表示されます。 ダウンロードしたメタデータ XML ファイルをメモ帳で開き、 **IDP メタデータ**に内容を貼り付けます。

    [Image: Pingboard SSO の構成画面]
6. ファイルが検証されます。すべて正しい場合は、シングル サインオンが有効になります。

#### Pingboard テスト ユーザーの作成

このセクションの目的は、Pingboard に Bitta Simon というユーザーを作成することです。 Pingboard では、 自動ユーザー プロビジョニングがサポートされています。この設定は、規定で有効になっています。 自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pingboard-provisioning-tutorial) 。

**ユーザーを手動で作成する必要がある場合は、次の手順を実行します。**

1. Pingboard 企業サイトに管理者としてサインインします。
2. [**ディレクトリ**] ページの **[従業員の追加**] ボタンを選択します。

    [Image: 従業員の追加]
3. **[従業員の追加**] ダイアログ ページで、次の手順を実行します。

    [Image: ユーザーを招待する]

    ある。 [ **Full Name]\(フル ネーム\)** ボックスに、 **Britta Simon** のようなユーザーのフル ネームを入力します。

    b。 [ **電子メール** ] ボックスに、ユーザーのメール アドレス ( **brittasimon@contoso.com**など) を入力します。

    c. [ **役職** ] ボックスに、Britta Simon の役職を入力します。

    d. [ **場所** ] ドロップダウンで、Britta Simon の場所を選択します。

    え **追加**を選択します。
4. ユーザーの追加を確認するための確認画面が表示されます。

    [Image: 確認する]

    注意

    Microsoft Entra アカウント所有者がメールを受け取り、リンクに従ってアカウントを確認すると、そのアカウントがアクティブになります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Pingboard のサインオン URL にリダイレクトされます。
- Pingboard のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Pingboard に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Pingboard] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Pingboard に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pinpoint-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Pinpoint (SAML) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pinpoint-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Pinpoint (SAML) の間にシングル サインオンを構成する方法について説明します。

この記事では、Pinpoint (SAML) と Microsoft Entra ID を統合する方法について説明します。 DDI の Pinpoint プラットフォームを使用すると、リーダー向けに構成した学習体験を簡単に設計、提供、追跡できます。 Pinpoint は、受賞歴のある DDI のリーダーシップ開発ソリューションとシームレスに統合されています。 Pinpoint (SAML) を Microsoft Entra ID を統合すると、次のことができます。

- Pinpoint (SAML) にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Pinpoint (SAML) に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Pinpoint (SAML) 向けの Microsoft Entra シングル サインオンを構成してテストします。 Pinpoint (SAML) では、 **SP** によって開始されるシングル サインオンのみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID と Pinpoint (SAML) を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Pinpoint (SAML) でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Pinpoint (SAML) アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Pinpoint (SAML) を追加

Microsoft Entra アプリケーション ギャラリーから Pinpoint (SAML) を追加して、Pinpoint (SAML) でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Pinpoint (SAML)**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、URL を入力します。 `https://login.ddiworld.com`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://login.ddiworld.com/SAML/sp/profile/post/acs`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://pinpoint.ddiworld.com/<CustomerName>`

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには、 [Pinpoint (SAML) クライアント サポート チーム](mailto:ssosupport@ddiworld.com) にお問い合わせください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Pinpoint (SAML) のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Pinpoint (SAML) SSO の構成

**Pinpoint (SAML)** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Pinpoint (SAML) サポート チーム](mailto:ssosupport@ddiworld.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Pinpoint (SAML) テスト ユーザーの作成

このセクションでは、Pinpoint (SAML) で Britta Simon というユーザーを作成します。 [Pinpoint (SAML) サポート チーム](mailto:ssosupport@ddiworld.com)と協力して、Pinpoint (SAML) プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Pinpoint (SAML) のサインオン URL にリダイレクトされます。
- Pinpoint (SAML) のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Pinpoint (SAML)] タイルを選択すると、このオプションは Pinpoint (SAML) のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pipedrive-tutorial"} -->
## Microsoft Entra ID で Pipedrive for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pipedrive-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Pipedrive の間にシングル サインオンを構成する方法について説明します。

この記事では、Pipedrive と Microsoft Entra ID を統合する方法について説明します。 Pipedrive を Microsoft Entra ID と統合すると、次のことができます。

- Pipedrive にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Pipedrive に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pipedrive でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Pipedrive では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

### ギャラリーからの Pipedrive の追加

Microsoft Entra ID への Pipedrive の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に IDrive を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Pipedrive**」と入力します。
4. 結果パネルから **[Pipedrive]** を選択し、そのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pipedrive 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Pipedrive に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Pipedrive の関連ユーザーとの間にリンク関係を確立する必要があります。

Pipedrive に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pipedrive SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Pipedrive テスト ユーザーを作成し、B.Simon に対応するユーザーを作成して、Microsoft Entra のユーザーにリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Pipedrive**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY-NAME>.pipedrive.com/sso/auth/samlp/metadata.xml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY-NAME>.pipedrive.com/sso/auth/samlp`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY-NAME>.pipedrive.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Pipedrive クライアント サポート チーム](mailto:support@pipedrive.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Pipedrive アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Pipedrive アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
9. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (Base64)]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして自分のコンピューターに保存します。また、**[アプリのフェデレーション メタデータ URL]** をコピーして自分のコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Pipedrive のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pipedrive SSO の構成

1. 別のブラウザー ウィンドウで、Pipedrive Web サイトに管理者としてサインインします。
2. **[ユーザー プロファイル]** を選択し**、[設定]** を選択します。

    [Image: スクリーンショットは、[User Profile](ユーザー プロファイル) メニューの [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) が選択されていることを示しています。]
3. [Defender for Cloud] まで下にスクロールし、 **[シングル サインオン]** を選択します。

    [Image: スクリーンショットは、[Defender for Cloud] で [シングル サインオン] が選択されていることを示しています。]
4. **[SAML configuration for Pipedrive](Pipedrive の SAML 構成)** セクションで、次の手順に従います。

    [Image: スクリーンショットは、[S A M L configuration for Pipedrive](Pipedrive の S A M L 構成) セクションですべてのテキストボックスが強調表示されていることを示しています。]

    ある。 **[発行者]** テキスト ボックスに、前にコピーした **[アプリのフェデレーション メタデータ URL]** の値を貼り付けます。

    b。 **[シングル サインオン (SSO) URL]** テキストボックスに、前にコピーした **[ログイン URL]** の値を貼り付けます。

    c. **[シングル ログアウト (SLO) URL]** テキストボックスに、前にコピーした**ログアウト URL** の値を貼り付けます。

    d. **[x.509 certificate](x.509 証明書)** ボックスで、Azure portal からダウンロードした**証明書 (Base64)** ファイルをメモ帳で開き、その内容をコピーして、 **[x.509 certificate](x.509 証明書)** に貼り付けて、変更を保存します。

#### Pipedrive テスト ユーザーの作成

1. 別のブラウザー ウィンドウで、Pipedrive Web サイトに管理者としてサインインします。
2. [COMPANY] まで下にスクロールし、 **[Manage users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの管理)** を選択します。

    [Image: スクリーンショットは、[Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社) メニューで [Manage users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの管理) が選択されていることを示しています。]
3. [ **ユーザーの追加] を選択します**。

    [Image: スクリーンショットは、[Manage users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの管理) ページの右側にある [Add users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ボタンが選択されていることを示しています。]
4. **[Manage users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの管理)** セクションで、次の手順を実行します。

    [Image: Pipedrive の構成]

    ある。 [ **電子メール** ] ボックスに、ユーザーのメール アドレス ( `B.Simon@contoso.com`など) を入力します。

    b。 **[First name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名前 (名) を入力します。

    c. **[Last name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します。

    d. [ **確認] を選択し、ユーザーを招待します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Pipedrive のサインオン URL にリダイレクトされます。
- Pipedrive のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Pipedrive に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Pipedrive] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Pipedrive に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pksha-chatagent-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に PKSHA ChatAgent を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pksha-chatagent-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PKSHA ChatAgent の間でシングル サインオンを構成する方法について説明します。

この記事では、PKSHA ChatAgent と Microsoft Entra ID を統合する方法について説明します。 PKSHA ChatAgent は、Web サイトに埋め込むことができるチャット インターフェイスを備えた AI ベースの対話ソリューションです。 PKSHA ChatAgent と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で PKSHA ChatAgent へのアクセス権を管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して PKSHA ChatAgent に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で PKSHA ChatAgent の Microsoft Entra シングル サインオンを構成してテストします。 PKSHA ChatAgent では、 **SP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングのみがサポートされます。

### [前提条件]

Microsoft Entra ID と PKSHA ChatAgent を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- PKSHA ChatAgent でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから PKSHA ChatAgent アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから PKSHA ChatAgent を追加する

Microsoft Entra アプリケーション ギャラリーから PKSHA ChatAgent を追加して、PKSHA ChatAgent でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**PKSHA ChatAgent**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:bedore-idp-production:<CONNECTION_NAME>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.admin.workplace.bedore.jp/login/callback?connection=<CONNECTION_NAME>`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://admin.workplace.bedore.jp?organization=<ORGANIZATION_CODE>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [PKSHA ChatAgent クライアント サポート チーム](mailto:bedore-support@pkshatech.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **PKSHA ChatAgent のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

### PKSHA ChatAgent SSO の構成

**PKSHA ChatAgent** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [PKSHA ChatAgent サポート チーム](mailto:isd.bedore-support@pkshatech.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PKSHA ChatAgent テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを PKSHA ChatAgent に作成します。 PKSHA ChatAgent では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 PKSHA ChatAgent にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる PKSHA ChatAgent サインオン URL にリダイレクトされます。
- PKSHA ChatAgent のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [PKSHA ChatAgent] タイルを選択すると、このオプションは PKSHA ChatAgent のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/plandisc-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して自動ユーザー プロビジョニング用に Plandisc を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/plandisc-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-21
- Summary: Microsoft Entra ID から Plandisc に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Plandisc と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Plandisc](https://plandisc.com) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Plandisc でユーザーを作成する
- アクセスが不要になった場合に Plandisc のユーザーを削除する
- Microsoft Entra ID と Plandisc の間でユーザー属性の同期を維持する
- Plandisc に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨).
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Plandisc Enterprise サブスクリプション
- 管理者権限を持つ Plandisc のユーザー アカウント

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Plandisc の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Plandisc を構成する

1. [Plandisc](https://create.plandisc.com) にサインインし、**Enterprise** に移動します

    [Image: Plandisc navigate Enterprise のスクリーンショット。]
2. **[Manage users with SCIM]** セクションが表示されるまで下にスクロールします。 ここでは、Plandisc アプリケーションの [プロビジョニング] タブに入力する値を確認できます。 **SCIM endpoint** は、[テナント URL] フィールドに挿入します。 **SCIM token** は、[シークレット トークン] フィールドに挿入します。

    [Image: Plandisc から SCIM トークンをコピーするのスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Plandisc を追加する

Microsoft Entra アプリケーション ギャラリーから Plandisc を追加して、Plandisc へのプロビジョニングの管理を開始します。 SSO 用に Plandisc を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Plandisc への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Plandisc でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Plandisc の自動ユーザー プロビジョニングを構成するには、次のようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット]
3. アプリケーションの一覧で **[Plandisc]** を選択します。

    [Image: アプリケーションの一覧の [Plandisc] リンクのスクリーンショット]
4. **[プロビジョニング]** タブを選択します。

    [Image: 自動ユーザー プロビジョニングを構成するための [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Plandisc テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Plandisc に接続できることを確認します。 接続に失敗した場合は、Plandisc アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Plandisc に同期されるユーザー属性を確認します。 "**照合**" プロパティとして選択されている属性は、更新処理で Plandisc のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Plandisc API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Plandisc で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | externalId | 糸 |  | ✓ |
    | 優先言語 | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/plangrid-tutorial"} -->
## Microsoft Entra ID を使用して PlanGrid for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/plangrid-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PlanGrid の間のシングル サインオンを構成する方法について説明します。

この記事では、PlanGrid と Microsoft Entra ID を統合する方法について説明します。 PlanGrid を Microsoft Entra ID と統合すると、次のことが可能になります。

- PlanGrid にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで PlanGrid に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PlanGrid のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- PlanGrid では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの PlanGrid の追加

Microsoft Entra ID への PlanGrid の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に PlanGrid を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**PlanGrid**」と入力します。
4. 結果のパネルから **[PlanGrid]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PlanGrid 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、PlanGrid に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと PlanGrid の関連ユーザーの間にリンク関係を確立する必要があります。

PlanGrid に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PlanGrid SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PlanGrid テストユーザーを作成** - PlanGrid 内で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**PlanGrid**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://io.plangrid.com/sessions/saml/metadata`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.plangrid.com/login`
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[PlanGrid のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PlanGrid SSO の構成

**PlanGrid** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [PlanGrid サポート チーム](mailto:help@plangrid.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PlanGrid のテスト ユーザーの作成

このセクションでは、PlanGrid で Britta Simon というユーザーを作成します。 [PlanGrid サポート チーム](mailto:help@plangrid.com)と連携し、PlanGrid プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる PlanGrid のサインオン URL にリダイレクトされます。
- PlanGrid のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した PlanGrid に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [PlanGrid] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した PlanGrid に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/planmyleave-tutorial"} -->
## Microsoft Entra ID で PlanMyLeave for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/planmyleave-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PlanMyLeave の間のシングル サインオンを構成する方法について説明します。

この記事では、PlanMyLeave と Microsoft Entra ID を統合する方法について説明します。 PlanMyLeave と Microsoft Entra ID の統合には、次の利点があります。

- PlanMyLeave にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで PlanMyLeave に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PlanMyLeave でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- PlanMyLeave では、**SP** Initiated SSO がサポートされます
- PlanMyLeave では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの PlanMyLeave の追加

Microsoft Entra ID への PlanMyLeave の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に PlanMyLeave を追加する必要があります。

**ギャラリーから PlanMyLeave を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **PlanMyLeave**」と入力し、結果パネルで **PlanMyLeave** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果リストの PlanMyLeave]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、PlanMyLeave で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと PlanMyLeave 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

PlanMyLeave で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **PlanMyLeave シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **PlanMyLeaveのテストユーザーを作成** - Britta Simonに対応するユーザーをPlanMyLeaveで作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

PlanMyLeave で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**PlanMyLeave** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [PlanMyLeave のドメインと URL] のシングル サインオン情報]

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-name>.planmyleave.com/Login.aspx`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-name>.planmyleave.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[PlanMyLeave クライアント サポート チーム](mailto:support@planmyleave.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[PlanMyLeave のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### PlanMyLeave のシングル サインオンの構成

1. 別の Web ブラウザーのウィンドウで、管理者として PlanMyLeave テナントにログインします。
2. **[System Setup (システム セットアップ)]** に移動します。 次に、[ **セキュリティ管理** ] セクションで [ **会社の SAML 設定** ] を選択します。

    [Image: [System Setup](システム セットアップ) ページを示すスクリーンショット。[Security Management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ管理) セクションが強調表示され、[Company S A M L settings](会社の S A M L 設定) が選択されています。]
3. [ **SAML 設定]** セクションで、エディター アイコンを選択します。

    [Image: [S A M L Settings](S A M L 設定) セクションを示すスクリーンショット。セクションの右上にある]
4. **[Update SAML Settings (SAML 設定の更新)]** セクションで、次の手順を実行します。

    [Image: アプリ側でシングル Sign-On を構成する]

    a. [ **ログイン URL** ] ボックスに、 **ログイン URL を**貼り付けます。

    b。 ダウンロードしたメタデータを開き、**X509Certificate** 値をコピーして、**[証明書]** ボックスに貼り付けます。

    c. **[Is Enable (有効)]** を **[はい]** に設定します。

    d. **保存** を選択します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### PlanMyLeave のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを PlanMyLeave に作成します。 PlanMyLeave では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 PlanMyLeave にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、[PlanMyLeave のサポート チーム](mailto:support@planmyleave.com)にお問い合わせください。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [PlanMyLeave] タイルを選択すると、SSO を設定した PlanMyLeave に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/planview-admin-tutorial"} -->
## Microsoft Entra ID を使用して Planview Admin for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/planview-admin-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Planview Admin の間にシングル サインオンを構成する方法について説明します。

この記事では、Planview Admin と Microsoft Entra ID を統合する方法について説明します。 Planview Admin と Microsoft Entra ID を統合すると、次のことができます:

- Planview Admin にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Planview Admin に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Planview Admin シングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Planview Admin では、**SP** および **IDP** Initiated SSO がサポートされています。

### ギャラリーから Planview Admin を追加する

Microsoft Entra ID への Planview Admin の統合を構成するには、Planview Admin をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加]** セクションで、検索ボックスに「**Planview Admin**」と入力します。
4. 結果のパネルから **Planview Admin** を選んで、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Planview Admin 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Planview Admin で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Planview Admin の関連ユーザーとの間にリンク関係を確立する必要があります。

Planview Admin に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Planview Admin の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Planview Admin テストユーザーを作成する** - Microsoft Entra にある B.Simon の表現とリンクされた Planview Admin 内の B.Simon の対応ユーザーを持つようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Planview Admin**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://id.planview.com/<EntityID>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Region>.id.planview.com/api/loginsso/callback`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Region>.id.planview.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Planview Admin サポート チーム](mailto:jordan.nguyen@planview.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Planview Admin の SSO を構成する

**Planview Admin** 側でシングル サインオンを構成するには、**アプリ フェデレーション メタデータ URL** を [Planview Admin サポート チーム](mailto:jordan.nguyen@planview.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Planview Admin のテスト ユーザーを作成する

このセクションでは、Planview Admin で Britta Simon というユーザーを作成します。[Planview Admin のサポート チーム](mailto:jordan.nguyen@planview.com)と協力して、Planview Admin プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Planview 管理者のサインオン URL にリダイレクトされます。
- Planview Admin のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Planview 管理者に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Planview Admin] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Planview 管理者に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/planview-enterprise-one-tutorial"} -->
## シングルサインオンのために Microsoft Entra ID を使用して Planview Enterprise One を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/planview-enterprise-one-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Planview Enterprise One の間でシングル サインオンを構成する方法について説明します。

この記事では、Planview Enterprise One と Microsoft Entra ID を統合する方法について説明します。 Planview Enterprise One と Microsoft Entra ID を統合すると、次のことができます。

- Planview Enterprise One にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Planview Enterprise One に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Planview Enterprise One でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Planview Enterprise One では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから Planview Enterprise One を追加する

Microsoft Entra ID への Planview Enterprise One の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Planview Enterprise One を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Planview Enterprise One**」と入力します。
4. 結果パネルから **Planview Enterprise One** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Planview Enterprise One の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Planview Enterprise One に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Planview Enterprise One の関連ユーザーとの間にリンク関係を確立する必要があります。

Planview Enterprise One に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Planview Enterprise One の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Planview Enterprise One のテストユーザーを作成** - Planview Enterprise One で B.Simon に対応するユーザーを作成し、Microsoft Entra でのユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Planview Enterprise One**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.pvcloud.com/planview`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.pvcloud.com/planview`

    手記

    これらの値は実際の値ではありません。 実際の識別子とサインオン URL でこれらの値を更新します。 これらの値を取得するには [、Planview Enterprise One クライアント サポート チーム](mailto:customercare@planview.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Planview Enterprise One のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Planview Enterprise One SSO の構成

**Planview Enterprise One** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Planview Enterprise One サポート チーム](mailto:customercare@planview.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Planview Enterprise One テスト ユーザーの作成

このセクションでは、Planview Enterprise One で B.Simon というユーザーを作成します。 Planview Enterprise One サポート チーム  と連携して、Planview Enterprise One プラットフォームにユーザーを追加してください。シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Planview Enterprise One のサインオン URL にリダイレクトされます。
- Planview Enterprise One のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Planview Enterprise One] タイルを選択すると、このオプションは Planview Enterprise One のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/planview-leankit-tutorial"} -->
## Microsoft Entra ID で Planview LeanKit for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/planview-leankit-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Planview LeanKit の間にシングル サインオンを構成する方法について説明します。

この記事では、Planview LeanKit と Microsoft Entra ID を統合する方法について説明します。 Planview LeanKit と Microsoft Entra ID を統合すると、次のことができます。

- Planview LeanKit にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Planview LeanKit に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Planview LeanKit でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Planview LeanKit では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。

### ギャラリーから Planview LeanKit を追加する

Microsoft Entra ID への Planview LeanKit の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Planview LeanKit を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Planview LeanKit**」と入力します。
4. 結果のパネルから **[Planview LeanKit]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Planview LeanKit 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Planview LeanKit で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Planview LeanKit の関連ユーザーとの間にリンク関係を確立する必要があります。

Planview LeanKit に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Planview LeanKit SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Planview LeanKit のテスト ユーザーの作成** - Planview LeanKit で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Planview LeanKit**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<HostName>.leankit.com`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<HostName>.leankit.com/Account/Membership/ExternalLogin`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<HostName>.leankit.com/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Planview LeanKit サポート チーム](mailto:support@leankit.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Planview LeanKit のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Planview LeanKit SSO の構成

**Planview LeanKit** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Planview LeanKit サポート チーム](mailto:support@leankit.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Planview LeanKit テスト ユーザーを作成する

このセクションでは、Planview LeanKit で Britta Simon というユーザーを作成します。 [Planview LeanKit サポート チーム](mailto:support@leankit.com)と協力して、Planview LeanKit プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Planview LeanKit のサインオン URL にリダイレクトされます。
- Planview LeanKit のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Planview LeanKit に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Planview LeanKit] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Planview LeanKit に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/playvox-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して自動ユーザー プロビジョニング用に Playvox を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/playvox-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-21
- Summary: Microsoft Entra ID から Playvox に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Playvox と Microsoft Entra ID の両方で従って自動ユーザー プロビジョニングを構成する手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使って、[Playvox](https://www.playvox.com) に対するユーザーまたはグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスが実行する内容および動作方法についての重要な情報と、よく寄せられる質問については、[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)に関する記事を参照してください。

### サポートされている機能

- Playvox でユーザーを作成する。
- アクセスが不要になった Playvox のユーザーを削除する。
- Microsoft Entra ID と Playvox の間でユーザー属性の同期を維持する
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事のシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Super Admin アクセス許可がある、[Playvox](https://www.playvox.com) のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープに含まれるユーザーを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)。
3. [Microsoft Entra ID と Playvox の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Playvox を構成する

1. Playvox 管理コンソールにログインし、**[設定] &gt; [API キー]** にアクセスします。
2. **[API キーの作成]** を選択します。

    [Image: Playvox ユーザー インターフェイスの [API キーの作成] ボタンの場所を示すスクリーンショット。]
3. API キーのわかりやすい名前を入力し、 **[保存]** を選択します。 API キーが生成されたら、 **[閉じる]** を選択します。
4. 作成した API キーの **[詳細]** アイコンを選択します。

    [Image: Playvox ユーザー インターフェイスの虫眼鏡である [詳細] アイコンの場所を示すスクリーンショット。]
5. **BASE64 KEY** の値をコピーし、保存します。 その後、Azure portal で、Playvox アプリケーションの [**プロビジョニング**] タブの [**シークレット トークン**] テキスト ボックスにこの値を入力します。

    [Image: BASE64 キーの値が強調表示されている、[API キーの詳細] メッセージ ボックスのスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Playvox を追加する

Playvox へのプロビジョニングの管理を開始するには、アプリケーション ギャラリーから Microsoft Entra テナントに Playvox を追加します。 詳細については、「[クイック スタート: Microsoft Entra テナントにアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)」を参照してください。

シングル サインオン (SSO) のために Playvox を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5:Playvox への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、ユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

Microsoft Entra ID で Playvox の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: エンタープライズ アプリケーションとすべてのアプリケーション項目が強調表示されているAzure ポータルのスクリーンショット。]
3. アプリケーションの一覧で、**Playvox** を検索して選択します。

    [Image: アプリケーションの検索ボックスが強調表示されている、アプリケーションの一覧の部分的なスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] メニュー項目を示す部分的なスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Playvox テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Playvox に接続できることを確認します。 接続に失敗した場合は、Playvox アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Playvox に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Playvox のユーザー アカウントとの照合に使用されます。 [照合する対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づいたユーザーのフィルター処理が Playvox API でサポートされていることを確認します。 [ **保存] を** 選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | externalId | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pleo-tutorial"} -->
## Microsoft Entra ID で Pleo for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pleo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Pleo の間でシングル サインオンを構成する方法について説明します。

この記事では、Pleo と Microsoft Entra ID を統合する方法について説明します。 Pleo と Microsoft Entra ID を統合すると、次のことができます。

- Pleo にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Pleo に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pleo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Pleo は、**SP および IDP による SSO の開始**の両方をサポートします。

### ギャラリーからの Pleo の追加

Microsoft Entra ID への Pleo の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Pleo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Pleo**」と入力します。
4. 結果パネルから **[Pleo** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pleo の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Pleo に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Pleo の関連ユーザーとの間にリンク関係を確立する必要があります。

Pleo に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pleo SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Pleo テスト ユーザーの作成** - Pleo で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Pleo**&gt;**シングルサインオン**に移動
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `pleoio://<CUSTOMER_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.pleo.io/saml/<CUSTOMER_ID>/callback`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.pleo.io/saml/<CUSTOMER_ID>/callback`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Pleo サポート チーム](mailto:support@pleo.io) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **Pleo のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pleo SSO の構成

**Pleo** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、Microsoft Entra 管理センターからコピーした適切な URL を [Pleo サポート チーム](mailto:support@pleo.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Pleo テスト ユーザーの作成

このセクションでは、Pleo で B.Simon というユーザーを作成します。 [Pleo サポート チーム](mailto:support@pleo.io)と協力して、Pleo プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Pleo サインオン URL にリダイレクトします。
- Pleo のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Pleo に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Pleo] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Pleo に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pluralsight-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Pluralsight を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pluralsight-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Pluralsight の間にシングル サインオンを構成する方法について説明します。

この記事では、Pluralsight と Microsoft Entra ID を統合する方法について説明します。 Pluralsight と Microsoft Entra ID を統合すると、次のことができます:

- Pluralsight にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Pluralsight に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pluralsight でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Pluralsight では、**SP** によって開始される SSO がサポートされます
- Pluralsight では、**Just-In-Time** ユーザー プロビジョニングがサポートされています

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Pluralsight の追加

Microsoft Entra ID への Pluralsight の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Pluralsight を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Pluralsight**」と入力します。
4. 結果ウィンドウで **[Pluralsight]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pluralsight 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Pluralsight に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Pluralsight の関連ユーザーとの間にリンク関係を確立する必要があります。

Pluralsight に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pluralsight SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Pluralsightのテストユーザーを作成** - Microsoft EntraのユーザーであるB.Simonに対応するユーザーをPluralsightに作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Pluralsight**&gt;**シングルサインオン** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<instancename>.pluralsight.com/sso/<companyname>`

    b。 [ **識別子** ] ボックスに、URL を入力します。 `www.pluralsight.com`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<instancename>.pluralsight.com/sp/ACS.saml2`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と応答 URL でこれらの値を更新してください。 これらの値を取得するには、[Pluralsight クライアント サポート チーム](mailto:support@pluralsight.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Pluralsight のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pluralsight SSO の構成

**Pluralsight** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[Pluralsight サポート チーム](mailto:support@pluralsight.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Pluralsight のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Pluralsight に作成します。 Pluralsight では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Pluralsight にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Pluralsight のサインオン URL にリダイレクトされます。
- Pluralsight のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Pluralsight タイルを選択すると、SSO を設定した Pluralsight に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pluto-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Pluto を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pluto-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Pluto 間にシングル サインオンを構成する方法について説明します。

この記事では、Pluto と Microsoft Entra ID を統合する方法について説明します。 Pluto と Microsoft Entra ID を統合すると、次のことができます。

- Pluto にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Pluto に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Pluto でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Pluto では、**SP** Initiated SSO がサポートされます。
- Pluto では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Pluto を追加する

Microsoft Entra ID への Pluto の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Pluto を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Pluto**」と入力します。
4. 結果のパネルから **[Pluto]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pluto 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Pluto に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Pluto の関連ユーザーとの間にリンク関係を確立する必要があります。

Pluto に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pluto の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Pluto のテスト ユーザーの作成** - Pluto で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザーとして表現することを目的としています。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Pluto**&gt;**シングルサインオン**
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://api.pluto.bio`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://api.pluto.bio/auth/social/complete/saml/`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://pluto.bio/login/<organization-shortname>`

    注

    この値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには、[Pluto クライアント サポート チーム](mailto:support@pluto.bio)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Pluto アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Pluto アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | 名（ファーストネーム） | ユーザー.ファーストネーム |
    | last\_name | ユーザーの名字 |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Pluto のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pluto の SSO の構成

**Pluto** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Pluto サポート チーム](mailto:support@pluto.bio)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Pluto のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Pluto に作成します。 Pluto では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Pluto にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Pluto のサインオン URL にリダイレクトされます。
- Pluto のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [冥王星] タイルを選択すると、このオプションは Pluto のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/podbean-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Podbean を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/podbean-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Podbean 間にシングル サインオンを構成する方法について説明します。

この記事では、Podbean と Microsoft Entra ID を統合する方法について説明します。 Podbean を Microsoft Entra ID と統合すると、次のことができます。

- Podbean にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Podbean に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Podbean サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Podbean は **SP** によって開始された SSO をサポートしています。
- Podbean では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Podbean の追加

Microsoft Entra ID への Podbean の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Podbean を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Podbean**」と入力します。
4. 結果パネルから **Podbean** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Podbean 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Podbean に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Podbean の関連ユーザーとの間にリンク関係を確立する必要があります。

Podbean に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Podbean SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Podbean のテスト ユーザーの作成** - B.Simon に対応するユーザーを Podbean で作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Podbean**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.podbean.com/sso/<CUSTOM_ID>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、 [Podbean クライアント サポート チーム](mailto:support@podbean.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Podbean のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Podbean の SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として Podbean にサインインします。
2. 左側のサイドバーで **[設定**&gt;**SSOログイン** ]を選択します。
3. 次の図に示す URL を選択して **Podbean SSO メタデータ ファイル** をダウンロードし、お使いのコンピューターに保存します。

    [Image: Podbean SSO メタデータ ファイルをダウンロードするスクリーンショット]
4. **SSO IDP** メタデータ ファイルに**フェデレーション メタデータ XML** ファイルをアップロードします。
5. **電子メール ドメイン**、**SSO 組織名**を設定し、[**送信]** を選択します。

    [Image: メタデータ ファイルをアップロードするスクリーンショット]

#### Podbean のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Podbean に作成します。 Podbean では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Podbean にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

1. [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Podbean のサインオン URL にリダイレクトされます。
2. Podbean のサインオン URL に直接移動し、そこからログイン フローを開始します。
3. Microsoft アクセス パネルを使用することができます。 アクセス パネルで [Podbean] タイルを選択すると、このオプションは Podbean のサインオン URL にリダイレクトされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/policystat-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に PolicyStat を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/policystat-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と PolicyStat の間のシングル サインオンを構成する方法について説明します。

この記事では、PolicyStat と Microsoft Entra ID を統合する方法について説明します。 PolicyStat と Microsoft Entra ID を統合すると、次のことができます。

- PolicyStat にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って PolicyStat に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

PolicyStat は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- シングル サインオン (SSO) が有効な PolicyStat のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- PolicyStat では、**SP** によって開始される SSO がサポートされます。
- PolicyStat では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの PolicyStat の追加

Microsoft Entra ID への PolicyStat の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に PolicyStat を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**PolicyStat**」と入力します。
4. 結果パネルから **[PolicyStat]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PolicyStat 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、PolicyStat に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと PolicyStat の関連ユーザーとの間にリンク関係を確立する必要があります。

PolicyStat に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PolicyStat SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PolicyStat テスト ユーザーの作成 - PolicyStat** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**PolicyStat**&gt;**Single サインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.policystat.com/saml2/metadata/`
    2. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.policystat.com`

        注

        これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[PolicyStat クライアント サポート チーム](https://rldatix.com/en-apac/customer-success/community/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. PolicyStat アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ **ユーザー属性] ダイアログを** 開きます。

    [Image: [編集] アイコンが選択されている [ユーザー属性] ダイアログを示すスクリーンショット。]
8. その他に、PolicyStat アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | uid (ユーザー識別子) | ExtractMailPrefix([メール]) |

    1. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

        [Image: [新しい要求の追加] と [保存] アクションが強調表示されている [ユーザー要求] セクションを示すスクリーンショット。]

        [Image: [ユーザー要求の管理] ダイアログを示すスクリーンショット。[名前]、[変換]、および [パラメーター] のテキスト ボックスが強調表示され、[保存] ボタンが選択されています。]
    2. [ **名前** ] ボックスに、その行に表示される属性名を入力します。
    3. **名前空間**は空白のままにします。
    4. [ソース] として **[変換]** を選択します。
    5. **[変換]** の一覧から、その行に対して表示される値を入力します。
    6. **[パラメーター 1]** の一覧から、その行に対して表示される値を入力します。
    7. **保存** を選択します。
9. **[PolicyStat の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PolicyStat SSO の構成

1. 別の Web ブラウザー ウィンドウで、PolicyStat 企業サイトに管理者としてログインします。
2. [ **管理** ] タブを選択し、左側のナビゲーション ウィンドウで **[単一 Sign-On 構成]** を選択します。

    [Image: [Administrator Menu](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者メニュー)]
3. **IDP メタデータを**選択し、[**IDP メタデータ**] セクションで次の手順を実行します。

    [Image: [Your I D P Metadata](I D P メタデータ) アクションが選択されていることを示すスクリーンショット。]

    1. ダウンロードしたメタデータ ファイルの内容をコピーし、**[Your Identity Provider Metadata (ID プロバイダーのメタデータ)]** テキスト ボックスに貼り付けます。
    2. [ **変更の保存] を選択します**。
4. [ **属性の構成]** を選択し、[ **属性の構成** ] セクションで、Azure 構成にある **CLAIM NAMES** を使用して次の手順を実行します。

    1. [ **Username Attribute]\(ユーザー名属性** \) テキストボックスに、キー ユーザー名属性として渡すユーザー名要求の値を入力します。 Azure の既定値は UPN ですが、PolicyStat にアカウントが既にある場合は、アカウントが重複しないように、または PolicyStat の既存のアカウントを UPN 値に更新するために、これらのユーザー名の値を一致させる必要があります。 既存のユーザー名を一括で更新するには、RLDatix PolicyStat サポート https://websupport.rldatix.com/support-form/にお問い合わせください。 UPN を渡すために入力する既定値は **`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name`** です。
    2. **First Name Attribute** テキスト ボックスに、Azure **`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`** の First Name Attribute の要求名を入力します。
    3. **[Last Name Attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓属性)** ボックスに、Azure の姓属性の要求名 (**`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname`**) を入力します。
    4. **[Email Attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール属性)** ボックスに、Azure の電子メール属性の要求名 (**`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`**) を入力します。
    5. [ **変更の保存] を選択します**。
5. **[Setup]** セクションで、 **[Enable Single Sign-on Integration]** を選択します。

    [Image: シングルサインオンの構成]

#### PolicyStat テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを PolicyStat に作成します。 PolicyStat では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 PolicyStat にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

PolicyStat から提供されている他の PolicyStat ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra のユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる PolicyStat のサインオン URL にリダイレクトされます。
- PolicyStat のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [PolicyStat] タイルを選択すると、このオプションは PolicyStat のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/politemail-sso-tutorial"} -->
## Microsoft Entra ID でのシングル サインオンに対する PoliteMail - SSO の構成 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/politemail-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-11
- Summary: Microsoft Entra ID と PoliteMail - SSO の間にシングル サインオンを構成する方法について説明します。

この記事では、PoliteMail - SSO と Microsoft Entra ID を統合する方法について説明します。 PoliteMail - SSO と Microsoft Entra ID を統合すると、次のことができます。

- どのユーザーに PoliteMail - SSO へのアクセスを許可するかを Microsoft Entra ID 内で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して PoliteMail - SSO に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PoliteMail - SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PoliteMail - SSO では、 **SP** によって開始される SSO がサポートされます。
- PoliteMail - SSO では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから PoliteMail - SSO を追加する

Microsoft Entra ID への Smart Map Pro の統合を構成するには、ギャラリーから、ご利用のマネージド SaaS アプリの一覧に PoliteMail - SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに「**PoliteMail - SSO**」と入力します。
4. 結果パネルから **[PoliteMail - SSO]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PoliteMail - SSO 用に Microsoft Entra SSO を構成し、テストする

**B.Simon** というテスト ユーザーを使用して、PoliteMail - SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、それに対応する PoliteMail - SSO 内のユーザーとの間にリンク関係を確立する必要があります。

PoliteMail - SSO に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PoliteMail - SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PoliteMail - SSO テストユーザーの作成** - PoliteMail - SSO における B.Simon の対応ユーザーを作成し、そのユーザーを Microsoft Entra の表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**PoliteMail - SSO**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_POLITEMAIL_HOSTNAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<YOUR_POLITEMAIL_HOSTNAME>/api/Saml2/Acs` |
    | `https://<YOUR_POLITEMAIL_HOSTNAME>/ssv3/Saml2/Acs` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_POLITEMAIL_HOSTNAME>`

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、PoliteMail - SSO サポート チーム](mailto:serversupport@politemail.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. PoliteMail - SSO アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングをご自分の SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、PoliteMail - SSO アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ロール | user.assignedroles |

    注意

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
8. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書を編集するスクリーンショット。]
9. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: スクリーンショットで拇印の値をコピーする方法を示します]
10. [ **PoliteMail - SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PoliteMail - SSO を構成する

**PoliteMail - SSO** 側でシングル サインオンを構成するには、Microsoft Entra 管理センターから**コピーした拇印の値**と適切な URL を [PoliteMail - SSO サポート チーム](mailto:serversupport@politemail.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### PoliteMail - SSO テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを PoliteMail - SSO に作成します。 PoliteMail - SSO では、Just-In-Time プロビジョニングがサポートされており、これは既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ PoliteMail - SSO に存在しない場合は、PoliteMail - SSO にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる PoliteMail - SSO サインオン URL にリダイレクトされます。
- PoliteMail - SSO サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [PoliteMail - SSO] タイルを選択すると、このオプションは、PoliteMail - SSO サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/poolparty-semantic-suite-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に PoolParty Semantic Suite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/poolparty-semantic-suite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PoolParty Semantic Suite の間にシングル サインオンを構成する方法について説明します。

この記事では、PoolParty Semantic Suite と Microsoft Entra ID を統合する方法について説明します。 PoolParty Semantic Suite と Microsoft Entra ID を統合すると、次のことができます。

- PoolParty Semantic Suite にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って PoolParty Semantic Suite に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PoolParty Semantic Suite でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PoolParty Semantic Suite では、 **SP** によって開始される SSO がサポートされます

### ギャラリーからの PoolParty Semantic Suite の追加

Microsoft Entra ID への PoolParty Semantic Suite の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に PoolParty Semantic Suite を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「PoolParty Semantic Suite**」と入力します。
4. 結果パネルから **PoolParty Semantic Suite** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PoolParty Semantic Suite 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、PoolParty Semantic Suite に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと PoolParty Semantic Suite の関連ユーザーとの間にリンク関係を確立する必要があります。

PoolParty に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PoolParty Semantic Suite の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PoolParty Semantic Suite のテスト ユーザーの作成** - PoolParty Semantic Suite において、B.Simon に対応するユーザーを作成し、それを Microsoft Entra でのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**PoolParty Semantic Suite**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.poolparty.biz/PoolParty/`

    b。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.poolparty.biz/<ID>`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.poolparty.biz/<ID>`

    注

    これらの値は実際の値ではありません。 実際の Sign-On URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには [、PoolParty Semantic Suite クライアント サポート チーム](mailto:support@poolparty.biz) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **PoolParty Semantic Suite のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PoolParty Semantic Suite SSO の構成

**PoolParty Semantic Suite** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [PoolParty Semantic Suite サポート チーム](mailto:support@poolparty.biz)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PoolParty Semantic Suite のテスト ユーザーの作成

このセクションでは、PoolParty Semantic Suite で Britta Simon というユーザーを作成します。 [PoolParty Semantic Suite サポート チーム](mailto:support@poolparty.biz)と協力して、PoolParty Semantic Suite プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる PoolParty Semantic Suite のサインオン URL にリダイレクトされます。
- PoolParty Semantic Suite のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [PoolParty Semantic Suite] タイルを選択すると、このオプションは PoolParty Semantic Suite のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/postbeyond-tutorial"} -->
## Microsoft Entra ID で PostBeyond for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/postbeyond-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PostBeyond の間のシングル サインオンを構成する方法について説明します。

この記事では、PostBeyond と Microsoft Entra ID を統合する方法について説明します。 PostBeyond と Microsoft Entra ID を統合すると、次のことができます。

- PostBeyond にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って PostBeyond に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

Microsoft Entra と PostBeyond の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/free-trial/)を取得できます。
- シングル サインオンが有効になっている PostBeyond サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- PostBeyond では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの PostBeyond の追加

Microsoft Entra ID への PostBeyond の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に PostBeyond を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「PostBeyond**」と入力します。
4. 結果パネルから **PostBeyond** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PostBeyond 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、PostBeyond に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと PostBeyond の関連ユーザーとの間にリンク関係を確立する必要があります。

PostBeyond に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PostBeyond SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PostBeyond のテストユーザーを作成** - これは、Microsoft Entra にリンクされている B.Simon に対応するユーザーです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**PostBeyond** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.postbeyond.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.postbeyond.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、PostBeyond クライアント サポート チーム](mailto:sso@postbeyond.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **PostBeyond のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PostBeyond SSO の構成

**PostBeyond** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [PostBeyond サポート チーム](mailto:sso@postbeyond.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PostBeyond のテスト ユーザーの作成

このセクションでは、PostBeyond で Britta Simon というユーザーを作成します。 [PostBeyond サポート チーム](mailto:sso@postbeyond.com)と協力して、PostBeyond プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる PostBeyond のサインオン URL にリダイレクトされます。
- PostBeyond のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [PostBeyond] タイルを選択すると、このオプションは PostBeyond のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/postman-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Postman を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/postman-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-21
- Summary: ユーザー アカウントを Postman に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Postman と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Postman](https://www.postman.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Postman でユーザーを作成する。
- アクセスが不要になったら、Postman のユーザーを削除します。
- Microsoft Entra ID と Postman の間でユーザー属性の同期を維持する。
- Postman にグループとグループ メンバーシップをプロビジョニングする。
- Postman への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/postman-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Enterprise プラン](https://www.postman.com/pricing/)の Postman テナント。
- 管理者アクセス許可がある Postman のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. ■[プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Postman の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように Postman を構成する

Microsoft Entra ID によるプロビジョニングをサポートするように Postman を構成する前に、Postman 管理コンソール内で SCIM API トークンを生成する必要があります。

注

[Postman SCIM プロビジョニングの概要](https://learning.postman.com/docs/administration/scim-provisioning/scim-provisioning-overview/#enabling-scim-in-postman)に関するページにアクセスし、**Postman で SCIM プロビジョニングを有効にする**手順を参照してください。

1. Postman アカウントにログインして、[Postman 管理コンソール](https://go.postman.co/home)に移動します。
2. ログインしたら、右側の **[チーム** ] を選択し **、[チームの設定]** を選択します。
3. サイドバーで **[認証]** を選択し、**[SCIM プロビジョニング]** トグルをオンにします。

    [Image: [Postman 認証設定] ページのスクリーンショット。]
4. **SCIM プロビジョニングを有効に**するかどうかを確認するポップアップ メッセージが表示されたら、[**有効にする**] を選択して SCIM プロビジョニングを有効にします。

    [Image: SCIM プロビジョニングを有効にするモーダルのスクリーンショット。]
5. **SCIM API キーを生成する**には、次の手順に従います。

    1. **[SCIM プロビジョニング]** セクションの **[SCIM API キーを生成する]** を選択します。

        [Image: Postman で SCIM API キーを生成するスクリーンショット。]
    2. キーの名前を入力し、[ **生成**] を選択します。
    3. 後で使用するために新しい API キーをコピーし、[ **完了]** を選択します。

    注

    このページに再度アクセスして、SCIM API キーを管理できます。 既存の API キーを再生成する場合は、切り替え中に最初のキーをアクティブにしておくオプションがあります。

    注

    SCIM プロビジョニングの有効化を続けるには、「[Microsoft Entra ID による SCIM の構成](https://learning.postman.com/docs/administration/scim-provisioning/configuring-scim-with-azure-ad/)」を参照してください。 SCIM の構成の詳細とサポートについては、[Postman サポートにお問い合わせください](https://www.postman.com/support/)。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Postman を追加する

Microsoft Entra アプリケーション ギャラリーから Postman を追加して、Postman へのプロビジョニングの管理を開始します。 SSO 向けに Postman を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Postman への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Postman の自動ユーザー プロビジョニングを構成するには、以下の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Postman]** を選択します。

    [Image: アプリケーション リストの Postman リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: 自動ユーザー プロビジョニングを構成するための [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Postman テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Postman に接続できることを確認します。 接続に失敗した場合は、Postman アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Postman に同期されるユーザー属性を確認します。 "**照合**" プロパティとして選択されている属性は、更新処理で Postman のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Postman API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Postman で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
12. 左側のパネルで **[属性マッピング** ] を選択し、[ **グループ**] を選択します。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Postman に同期されるグループ属性を確認します。 "**照合**" プロパティとして選択されている属性は、更新処理で Postman のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Postman で必須 |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/postman-tutorial"} -->
## Microsoft Entra ID で Postman for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/postman-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Postman の間にシングル サインオンを構成する方法についてご確認ください。

この記事では、Postman と Microsoft Entra ID を統合する方法について説明します。 Postman を Microsoft Entra ID と統合すると、次のことができます。

- Postman にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Postman に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Postman は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Postman でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Postman は、**SP および IDP 開始の SSO** をサポートしています。
- Postman では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Postman の追加

Microsoft Entra ID への Postman の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Postman を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Postman**」と入力します。
4. 結果のパネルから **[Postman]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Postman 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Postman に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Postman の関連ユーザーとの間にリンク関係を確立する必要があります。

Postman に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Postman の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Postman のテスト ユーザーを作成する** - Postman で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Postman**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://identity.getpostman.com/sso/<INSTANCE_NAME>/callback`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://identity.getpostman.com/sso/<INSTANCE_NAME>/init`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、[Postman クライアント サポート チーム](mailto:help@getpostman.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Postman アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Postman ではこれをユーザーのメール アドレスにマップすることが求められます。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Postman のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Postman SSO を設定する

**Postman** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** をアップロードし、コピーした適切な URL を Postman で更新する必要があります。 Postman の SSO を構成する方法については、[ステップ バイ ステップ ガイド](https://learning.postman.com/docs/administration/sso/admin-sso/)を参照してください。

#### Postman のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Postman に作成します。 Postman では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定を有効にするには、[\[Automatically add new users\](新しいユーザーを自動的に追加する)](https://learning.postman.com/docs/administration/sso/admin-sso/#automatically-adding-new-users) チェック ボックスをオンにします。 この状態で Postman にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Postman のサインオン URL にリダイレクトされます。
- Postman のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Postman に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Postman] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Postman に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/powerschool-performance-matters-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Powerschool Performance Matters を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/powerschool-performance-matters-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Powerschool Performance Matters の間でシングル サインオンを構成する方法について説明します。

この記事では、Powerschool Performance Matters と Microsoft Entra ID を統合する方法について説明します。 Powerschool Performance Matters と Microsoft Entra ID を統合すると、次のことが可能になります。

- Powerschool Performance Matters にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Powerschool Performance Matters に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Powerschool Performance Matters でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Powerschool Performance Matters では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Powerschool Performance Matters の追加

Powerschool Performance Matters と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に Powerschool Performance Matters をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Powerschool Performance Matters**」と入力します。
4. 結果パネルから **Powerschool Performance Matters** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Powerschool Performance Matters 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Form.com に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと、Form.com での関連ユーザーとの間にリンク関係を確立する必要があります。

Form.com 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Powerschool Performance Matters の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Powerschool Performance Matters のテスト ユーザーを作成する** - Powerschool Performance Matters で B.Simon に対応するユーザーを作成し、Microsoft Entra の当該ユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Powerschool Performance Matters**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```https
        https://ola.performancematters.com/ola/?clientcode=<Client Code>
        https://unify.performancematters.com/?idp=<IDP>
    ```

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、Powerschool Performance Matters クライアント サポート チーム](mailto:pmsupport@powerschoo.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Powerschool Performance Matters のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Powerschool Performance Matters の SSO の構成

**Powerschool Performance Matters** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Powerschool Performance Matters サポート チーム](mailto:pmsupport@powerschoo.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Powerschool Performance Matters のテスト ユーザーの作成

このセクションでは、Powerschool Performance Matters で Britta Simon というユーザーを作成します。 [Powerschool Performance Matters サポート チーム](mailto:pmsupport@powerschoo.com)と協力して、Powerschool Performance Matters プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Powerschool Performance Matters のサインオン URL にリダイレクトされます。
- サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Powerschool Performance Matters] タイルを選択すると、このオプションは Powerschool Performance Matters のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/preciate-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Preciate を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/preciate-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-21
- Summary: Microsoft Entra ID から Preciate にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Preciate ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [Preciate](https://preciate.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Preciate でユーザーを作成する
- アクセスが不要になったときに Preciate のユーザーを削除する
- Microsoft Entra ID とPreciate の間でユーザー属性の同期を維持する。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Preciate テナント。
- 管理者アクセス許可がある Preciate のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Preciate の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Preciate を構成する

1. [Preciate 管理ポータル](https://preciate.com/web/admin/keys)にサインインし、[**統合**] ページに移動します。

    [Image: [Preciate secret token configuration](シークレット トークンの事前設定) 構成ページのスクリーンショット。]
2. [ **生成** ] ボタンを選択します。このボタンには Active Directory 統合秘密鍵が表示されます。

    [Image: Preciate シークレット トークン生成ページのスクリーンショット。]
3. 新しい **秘密鍵** が表示されます。 秘密鍵をコピーして保存 **します**。 また、テナント URL が `https://preciate.com/api/v1/scim` であることにも注意してください。 これらの値は、Preciate のアプリケーションの [プロビジョニング] タブの [ **シークレット トークン** と **テナント URL** ] フィールドに入力されます。

注

[生成] ボタンを選択するたびに、新しい秘密鍵が作成されます。 これにより、現在のデータが直ちに無効になります。 統合で現在のキーが既にアクティブに使用されている場合、新しいキーを生成すると、Azure portal の Preciate のアプリケーションでシークレット トークンが更新されるまで、統合が機能しなくなります。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Preciate を追加する

Microsoft Entra アプリケーション ギャラリーから Preciate を追加して、Preciate へのプロビジョニングの管理を開始します。 以前にシングル サインオン (SSO) 用に Preciate を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細について説明 [します](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5:Preciate への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Preciateの自動ユーザー プロビジョニングを構成するには次のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Preciate**] を選択します。

    [Image: アプリケーションの一覧の [Preciate] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: 自動ユーザー プロビジョニングを構成するための [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、事前テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Preciate に接続できることを確認します。 接続に失敗した場合は、Preciate アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Preciate に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Preciate のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Preciate API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | タイトル | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/predict360-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Predict360 SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/predict360-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Predict360 SSO の間にシングル サインオンを構成する方法について説明します。

この記事では、Predict360 SSO を Microsoft Entra ID と統合する方法について説明します。 Predict360 は、中規模の銀行やその他の金融機関向けのガバナンス、リスク、およびコンプライアンスのソリューションです。 Predict360 SSO を Microsoft Entra ID を統合すると、次のことができます。

- Predict360 SSO にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Predict360 SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Predict360 SSO 用に Microsoft Entra のシングル サインオンを構成してテストします。 Predict360 SSO は、**SP** と **IDP** Initiated の両方のシングル サインオンをサポートしています。

### [前提条件]

Microsoft Entra ID と Predict360 SSO を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Predict360 SSO のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Predict360 SSO アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Predict360 SSO を追加する

Microsoft Entra アプリケーション ギャラリーから Predict360 SSO を追加して、Predict360 SSO でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Predict360 SSO**&gt;**シングルサインオンに移動します。**
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする方法を示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: フォルダーでのメタデータ ファイルの選択を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    d. **[リレー状態]** テキスト ボックスに、360factors によって提供される顧客コード/キーを入力します。 コードが小文字で入力されていることを確認します。 これは、**IDP** Initiated モードで必須です。

    注

    **サービス プロバイダー メタデータ ファイル**は、[Predict360 SSO サポート チーム](mailto:support@360factors.com)から取得します。 **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。

    え アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    **[サインオン URL]** テキスト ボックスに、次の形式で顧客固有の URL を入力します。`https://<customer-key>.360factors.com/predict360/login.do`

    注

    この URL は、360factors チームによって共有されます。 `<customer-key>` は、360factors チームによって提供される顧客キーに置き換えられます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[SAML 署名証明書]** セクションで **[証明書 (未加工)]** を見つけ、**[ダウンロード]** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書 (未加工) のダウンロード リンクを示すスクリーンショット。]
8. **[Predict360 SSO のセットアップ]** セクションで、要件に基づいて該当の URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Predict360 SSO を構成する

**Predict360 SSO** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML**、**証明書 (未加工)**、およびアプリケーション構成からコピーした該当の URL を [Predict360 SSO のサポート チーム](mailto:support@360factors.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Predict360 SSO のテスト ユーザーを作成する

このセクションでは、Predict360 SSO で Britta Simon というユーザーを作成します。 [Predict360 SSO のサポート チーム](mailto:support@360factors.com)と協力して、Predict360 SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる Predict360 SSO サインオン URL にリダイレクトされます。
- Predict360 SSO サインオン URL に直接アクセスし、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Predict360 SSO に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Predict360 SSO] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Predict360 SSO に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/predictix-assortment-planning-tutorial"} -->
## Microsoft Entra ID を使用して Predictix Assortment Planning のシングル サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/predictix-assortment-planning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: この記事では、Microsoft Entra ID と Predictix Assortment Planning の間でシングル サインオンを構成する方法について説明します。

この記事では、Predictix Assortment Planning と Microsoft Entra ID を統合する方法について説明します。 この統合には、次の利点があります。

- AMicrosoft Entra ID を使って、Predictix Assortment Planning にアクセスできるユーザーを制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Predictix Assortment Planning に自動的にサインイン (シングル サインオン) するようにできます。
- 1 つの中央サイト (Azure ポータル) でアカウントを管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID でのアプリケーションへのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)」を参照してください。

Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

Microsoft Entra と Predictix Assortment Planning の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/free-trial/)を取得できます。
- シングル サインオンが有効な Predictix Assortment Planning サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Predictix Assortment Planning では、SP によって開始される SSO がサポートされます。

### ギャラリーからの Predictix Assortment Planning の追加

Microsoft Entra ID への Predictix Assortment Planning の統合を設定するには、ギャラリーからマネージド SaaS アプリのリストに Predictix Assortment Planning を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションを追加するには、ウィンドウの上部にある **[新しいアプリケーション** ] を選択します。

    [Image: [新しいアプリケーション] を選択する]
4. 検索ボックスに、「 **Predictix Assortment Planning**」と入力します。 検索結果で **Predictix Assortment Planning** を選択し、[ **追加**] を選択します。

    [Image: 検索結果]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、Britta Simon というテスト ユーザーを使用して、Predictix Assortment Planning で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを有効にするには、Microsoft Entra ユーザーと Predictix Assortment Planning の対応するユーザーの間に関係を確立する必要があります。

Predictix Assortment Planning に対する Microsoft Entra のシングル サインオンを構成してテストするには、次の手順を完了する必要があります。

1. **Microsoft Entra シングル サインオンを構成** して、ユーザーの機能を有効にします。
2. アプリケーション側で **Predictix Assortment Planning のシングル サインオンを構成**します。
3. **Microsoft Entra テスト ユーザーを作成** して、Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てて、ユーザー** の Microsoft Entra シングル サインオンを有効にします。
5. ユーザーの Microsoft Entra 表現にリンクされている **Predictix Assortment Planning テスト ユーザーを作成**します。
6. **シングル サインオンをテスト** して、構成が機能することを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Predictix Assortment Planning での Microsoft Entra のシングル サインオンを構成するには、次の手順のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Predictix Assortment Planning** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: [シングル サインオン] を選択する]
3. [ **シングル サインオン方法の選択** ] ダイアログ ボックスで、[ **SAML/WS-Fed** モード] を選択して、シングル サインオンを有効にします。

    [Image: シングル サインオン方法を選択する]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集** ] アイコンを選択して [ **基本的な SAML 構成** ] ダイアログ ボックスを開きます。

    [Image: 編集アイコン]
5. [ **基本的な SAML 構成** ] ダイアログ ボックスで、次の手順を実行します。

    [Image: [基本的な SAML 構成] ダイアログ ボックス]

    1. [ **サインオン URL** ] ボックスに、次のパターンで URL を入力します。

        ```https
        https://<sub-domain>.ap.predictix.com/sso/request
        https://<sub-domain>.dev.ap.predictix.com/
        ```
    2. [ **識別子 (エンティティ ID)]** ボックスに、次のパターンで URL を入力します。

        ```https
        https://<sub-domain>.ap.predictix.com
        https://<sub-domain>.dev.ap.predictix.com
        ```

    注

    これらの値はプレースホルダーです。 実際のサインオン URL と識別子を使用する必要があります。 値を取得するには、 [Predictix Assortment Planning サポート チーム](https://www.infor.com/support) に問い合わせてください。 [ **基本的な SAML 構成** ] ダイアログ ボックスに表示されるパターンを参照することもできます。
6. [**SAML を使用した単一 Sign-On の設定**] ページの [**SAML 署名証明書**] セクションで、要件に従って **[証明書 (Base64)] の**横にある **[ダウンロード**] リンクを選択し、証明書をコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Predictix Assortment Planning のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    1. **ログイン URL**。
    2. **Microsoft Entra 識別子**。
    3. **ログアウト URL**。

#### Predictix Assortment Planning のシングル サインオンを構成する

Predictix Assortment Planning 側でシングル サインオンを構成するには、ダウンロードした証明書と、 [Predictix Assortment Planning サポート チーム](https://www.infor.com/support)にコピーした URL を送信する必要があります。 このチームは、SAML SSO 接続が両方の側で正しく設定されていることを確認します。

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、Britta Simon という名前のテスト ユーザーを作成します。

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

このセクションでは、Britta Simon に Predictix Assortment Planning へのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Predictix Assortment Planning** にアクセスします。

    [Image: アプリケーションの一覧]
3. 左側のウィンドウで、[ **ユーザーとグループ**] を選択します。

    [Image: ユーザーとグループの選択]
4. [**ユーザーの追加]** を選択し、[**割り当ての追加**] ダイアログ ボックスで [**ユーザーとグループ**] を選択します。

    [Image: [ユーザーの追加] を選択する]
5. [ **ユーザーとグループ** ] ダイアログ ボックスで、ユーザーの一覧で **Britta Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ロールの **選択** ] ダイアログ ボックスで、一覧からユーザーに適したロールを選択します。 画面の下部にある **[選択** ] ボタンを選択します。
7. [ **割り当ての追加** ] ダイアログ ボックスで、[ **割り当て**] を選択します。

#### Predictix Assortment Planning テスト ユーザーの作成

次に、Predictix Assortment Planning で Britta Simon という名前のユーザーを作成する必要があります。 [Predictix Assortment Planning サポート チーム](https://www.infor.com/support)と協力して、ユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

注

Microsoft Entra アカウント所有者がメールを受け取り、リンクを選んでアカウントを確認すると、そのアカウントがアクティブになります。

#### シングル サインオンのテスト

次に、アクセス パネルを使って Microsoft Entra のシングル サインオン構成をテストする必要があります。

アクセス パネルで [Predictix Assortment Planning] タイルを選択すると、SSO を設定した Predictix Assortment Planning インスタンスに自動的にサインインされます。 詳細については、「 [マイ アプリ ポータルでアプリにアクセスして使用する」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/predictixordering-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Predictix Ordering を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/predictixordering-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: この記事では、Microsoft Entra ID と Predictix Ordering の間でシングル サインオンを構成する方法について説明します。

この記事では、Predictix Ordering と Microsoft Entra ID を統合する方法について説明します。 Predictix Ordering を Microsoft Entra ID と統合すると、次のことができます。

- Predictix Ordering にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Predictix Ordering に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

Predictix Ordering と Microsoft Entra の統合を構成するには、次のものを用意する必要があります。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/free-trial/)を取得できます。
- シングル サインオンが有効な Predictix Ordering サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Predictix Ordering では、SP Initiated SSO がサポートされます。

### ギャラリーから Predictix Ordering を追加する

Microsoft Entra ID への Predictix Ordering の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Predictix Ordering を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Predictix Ordering**」と入力します。
4. 結果パネルから **[Predictix Ordering]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Predictix Ordering 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Predictix Ordering に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Predictix Ordering の関連ユーザーとの間にリンク関係を確立する必要があります。

Predictix Ordering に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Predictix Ordering の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Predictix Ordering テストユーザーの作成** - B.Simon に対応するユーザーを Predictix Ordering で作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Predictix Ordering** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** ダイアログ ボックスで、次の手順を実行します:

    ある。 [ **識別子 (エンティティ ID)]** ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<companyname-pricing>.dev.ordering.predictix.com` |
    | `https://<companyname-pricing>.ordering.predictix.com` |

    b。 **[サインオン URL]** ボックスに、`https://<companyname-pricing>.ordering.predictix.com/sso/request` という形式で URL を入力します。

    注

    これらの値はプレースホルダーです。 これらの値を実際の識別子とサインオン URL で更新してください。 値を取得するには、Predictix Ordering サポート チームにお問い合わせください。 **[基本的な SAML 構成]** ダイアログ ボックスに示されているパターンを参照することもできます。
6. [**SAML を使用した単一 Sign-On の設定**] ページの [**SAML 署名証明書**] セクションで、要件に従って **[証明書 (Base64)] の**横にある **[ダウンロード**] リンクを選択し、証明書をコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Predictix Ordering のセットアップ]** セクションで、実際の要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Predictix Ordering SSO の構成

Predictix Ordering 側でシングル サインオンを構成するには、ダウンロードした証明書と、Predictix Ordering サポート チームにコピーした URL を送信する必要があります。 このチームは、SAML SSO 接続が両方の側で正しく設定されていることを確認します。

#### Predictix Ordering テスト ユーザーの作成

次に、Predictix Ordering で Britta Simon という名前のユーザーを作成する必要があります。 Predictix Ordering サポート チームと協力して、ユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Predictix Ordering のサインオン URL にリダイレクトされます。
- Predictix Ordering のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Predictix Ordering] タイルを選択すると、このオプションは Predictix Ordering のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/predictixpricereporting-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Predictix Price Reporting を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/predictixpricereporting-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: この記事では、Microsoft Entra ID と Predictix Price Reporting の間でシングル サインオンを構成する方法について説明します。

この記事では、Predictix Price Reporting と Microsoft Entra ID を統合する方法について説明します。 Predictix Price Reporting を Microsoft Entra ID と統合すると、次のことができます:

- Predictix Price Reporting にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Predictix Price Reporting に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Predictix Price Reporting でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Predictix Price Reporting では、SP によって開始される SSO がサポートされます。

### ギャラリーから Predictix Price Reporting を追加する

Microsoft Entra ID への Predictix Price Reporting の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Predictix Price Reporting を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Predictix Price Reporting**」と入力します。
4. 結果パネルから **Predictix Price Reporting を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Predictix Price Reporting 用に Microsoft Entra SSO を構成とテスト

**B.Simon** というテスト ユーザーを使用して、Predictix Price Reporting に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Predictix Price Reporting の関連ユーザーとの間にリンク関係を確立する必要があります。

Predictix Price Reporting に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Predictix Price Reporting の SSO の**構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Predictix Price Reporting テスト ユーザーの作成** - Microsoft Entra でのユーザー表現にリンクされた Predictix Price Reporting で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Predictix Price Reporting** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成** ] ダイアログ ボックスで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<companyname-pricing>.predictix.com` |
    | `https://<companyname-pricing>.dev.predictix.com` |

    b。 [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname-pricing>.predictix.com/sso/request`

    注

    これらの値はプレースホルダーです。 これらの値を実際の識別子とサインオン URL で更新してください。 値を取得するには、 [Predictix Price Reporting サポート チーム](https://www.infor.com/customer-center) にお問い合わせください。 [ **基本的な SAML 構成** ] ダイアログ ボックスに表示されるパターンを参照することもできます。
6. [**SAML を使用した単一 Sign-On の設定**] ページの [**SAML 署名証明書**] セクションで、要件に従って **[証明書 (Base64)] の**横にある **[ダウンロード**] リンクを選択し、証明書をコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Predictix Price Reporting のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Predictix Price Reporting の SSO を構成する

Predictix Price Reporting 側でシングル サインオンを構成するには、ダウンロードした証明書と、 [Predictix Price Reporting サポート チーム](https://www.infor.com/customer-center)にコピーした URL を送信する必要があります。 このチームは、SAML SSO 接続が両方の側で正しく設定されていることを確認します。

#### Predictix Price Reporting テスト ユーザーの作成

次に、Predictix Price Reporting で Britta Simon という名前のユーザーを作成する必要があります。 [Predictix Price Reporting サポート チーム](https://www.infor.com/customer-center)と協力して、ユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Predictix Price Reporting のサインオン URL にリダイレクトされます。
- Predictix Price Reporting のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Predictix Price Reporting] タイルを選択すると、このオプションは Predictix Price Reporting のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/preset-tutorial"} -->
## Microsoft Entra ID でシングル サインオンのプリセットを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/preset-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Preset の間でシングル サインオンを構成する方法について説明します。

この記事では、Preset と Microsoft Entra ID を統合する方法について説明します。 Preset を Microsoft Entra ID と統合すると、次のことができます。

- Preset にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Preset に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Preset でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Preset では、**SP** および **IDP** 開始の SSO がサポートされます。

### ギャラリーから Preset を追加する

Preset の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Preset を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Preset**」と入力します。
4. 結果パネルから **[Preset]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Preset 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Preset で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Preset の関連ユーザーとの間にリンク関係を確立する必要があります。

Preset で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Preset SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **プリセット テスト ユーザーの作成** - Preset で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Preset**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`urn:auth0:preset-io-prod:<ConnectionID>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://auth.app.preset.io/login/callback?connection=<ConnectionID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://manage.app.preset.io/login`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Preset サポート チーム](mailto:support@preset.io)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Preset アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Preset アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Preset のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Preset SSO の構成

**Preset** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Preset サポート チーム](mailto:support@preset.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Preset テスト ユーザーの作成

このセクションでは、Preset で Britta Simon というユーザーを作成します。 [Preset サポート チーム](mailto:support@preset.io)と連携して、Preset プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できるプリセット サインオン URL にリダイレクトされます。
- Preset のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定したプリセットに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [プリセット] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したプリセットに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/presspage-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に PressPage を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/presspage-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PressPage の間のシングル サインオンを構成する方法について説明します。

この記事では、PressPage と Microsoft Entra ID を統合する方法について説明します。 PressPage を Microsoft Entra ID と統合すると、次のことが可能になります。

- PressPage にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで PressPage に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PressPage でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PressPage では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの PressPage の追加

Microsoft Entra ID への PressPage の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に PressPage を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「PressPage**」と入力します。
4. 結果パネルから **PressPage** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PressPage 向けに Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、PressPage に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと PressPage の関連ユーザーの間にリンク関係を確立する必要があります。

PressPage で Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PressPage SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PressPage テストユーザーの作成** - Microsoft EntraのユーザーとしてのB.Simonに対応するPressPageユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**PressPage**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://manager.presspage.com`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **PressPage のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PressPage SSO の構成

**PressPage** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [PressPage サポート チーム](mailto:support@presspage.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PressPage テスト ユーザーの作成

このセクションでは、PressPage で B.Simon というユーザーを作成します。 [PressPage サポート チーム](mailto:support@presspage.com)と協力して、PressPage プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [PressPage] タイルを選択すると、SSO を設定した PressPage に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pressreader-tutorial"} -->
## Microsoft Entra ID で PressReader for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pressreader-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PressReader の間でシングル サインオンを構成する方法について説明します。

この記事では、PressReader と Microsoft Entra ID を統合する方法について説明します。 PressReader と Microsoft Entra ID を統合すると、次のことができます。

- PressReader にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して PressReader に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PressReader でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PressReader では、 **SP** Initiated SSO がサポートされます。
- PressReader では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから PressReader を追加する

Microsoft Entra ID への PressReader の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に PressReader を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「PressReader**」と入力します。
4. 結果パネルから **PressReader** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PressReader の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、PressReader に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと PressReader の関連ユーザーとの間にリンク関係を確立する必要があります。

PressReader で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PressReader SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PressReader のテストユーザーを作成 - PressReader に B.Simon と対応するユーザーを作成し、Microsoft Entra における B.Simon の表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**PressReader**&gt;**シングルサインオン**にブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://www.pressreader.com/`

    .b [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://www.pressreader.com/externalauth/processsamlauthorization/`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.pressreader.com/<INSTANCE>`

    注

    サインオン URL は実際のものではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには、 [PressReader サポート チーム](mailto:libraries@pressreader.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PressReader SSO の構成

**PressReader** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[PressReader サポート チーム](mailto:libraries@pressreader.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PressReader テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを PressReader に作成します。 PressReader では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ PressReader に存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる PressReader サインオン URL にリダイレクトされます。
- PressReader のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [PressReader] タイルを選択すると、このオプションは PressReader のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/presswise-tutorial"} -->
## Microsoft Entra ID で PressWise for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/presswise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PressWise の間でシングル サインオンを構成する方法について説明します。

この記事では、PressWise と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と PressWise を統合すると、次のことができます。

- PressWise にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して PressWise に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PressWise でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PressWise では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーから PressWise を追加する

Microsoft Entra ID への PressWise の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に PressWise を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「PressWise**」と入力します。
4. 結果パネルから **PressWise** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PressWise の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、PressWise に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと PressWise の関連ユーザーとの間にリンク関係を確立する必要があります。

PressWise で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PressWise SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PressWise テスト ユーザーの作成** - PressWise で B.Simon に対応するテストユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;の**PressWise**&gt;に移動し、**シングルサインオン**を選択してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **基本的な SAML 構成** セクションでは、アプリケーションが事前に構成されており、必要な URL が既に Microsoft Entra に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PressWise SSO の構成

**PressWise** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[PressWise サポート チーム](mailto:support@PressWise.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PressWise テスト ユーザーの作成

このセクションでは、PressWise で B.Simon というユーザーを作成します。 [PressWise サポート チーム](mailto:support@PressWise.com)と協力して、PressWise プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した PressWise に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [PressWise] タイルを選択すると、SSO を設定した PressWise に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/prezi-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Prezi を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/prezi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Prezi 間にシングル サインオンを構成する方法について説明します。

この記事では、Prezi と Microsoft Entra ID を統合する方法について説明します。 Prezi を Microsoft Entra ID と統合すると、次のことができます。

- Prezi にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Prezi に自動的にサインインできるようにする。
- アカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効になっている Prezi サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Prezi では、SP Initiated SSO と IDP Initiated SSO がサポートされます。
- Prezi では、Just-In-Time ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Prezi の追加

Microsoft Entra ID への Prezi の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Prezi を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Prezi**」と入力します。
4. 結果パネルで **[Prezi]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Prezi 用に Microsoft Entra SSO を構成してテストする

B.Simon というテスト ユーザーを使用して、Prezi に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Prezi の関連ユーザーとの間にリンク リレーションシップを確立します。

Prezi に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. ユーザーがこの機能を使用できるように Microsoft Entra SSO を構成します。
    1. Microsoft Entra テスト ユーザーを作成して、B.Simon に対する Microsoft Entra SSO をテストします。
    2. B.Simon が Microsoft Entra SSO を使用できるように、Microsoft Entra テスト ユーザーを割り当てます。
2. Prezi SSO を構成して、アプリケーション側で SSO 設定を構成します。
    1. Prezi テスト ユーザーを作成し、Prezi で B.Simon に対応するユーザーにして、Microsoft Entra の B.Simon にリンクさせます。
3. SSO をテスト して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Azure portal で Microsoft Entra SSO を有効にするには、以下を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Prezi** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで鉛筆アイコンを選択して、 **[基本的な SAML 構成]** で設定を編集します。

    [Image: [基本的な SAML 構成] の設定を編集する]
5. アプリは Azure と事前に統合済みであるため、 **[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. アプリケーションを **SP** Initiated モードで構成する場合は、 **[追加の URL を設定します]** を選択して次の手順を実行します。

    **[サインオン URL]** ボックスに、URL として「`https://prezi.com/login/sso/`」と入力します。
7. **保存** を選択します。
8. Prezi アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: ユーザー属性と要求]
9. Prezi アプリケーションでは、以下に示すように、さらにいくつかの属性も SAML 応答で返されることが想定されています。 これらの属性も値が事前に設定されますが、要件に基づいてそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | given\_name | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
10. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけます。 [ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[Prezi のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Prezi の SSO の構成

1. 別の Web ブラウザー ウィンドウで、チーム アカウントで Prezi にサインインし、[管理コンソール](https://prezi.com/organizations/manage)に移動します。
2. **管理コンソール**で **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** タブを選択します。

    [Image: [設定] タブ]
3. **[Single Sign-On (SSO)](シングル サインオン (SSO))** セクションに移動し、SSO を有効にするようにトグルをオンにします。

    [Image: [Single Sign-On (SSO)](シングル サインオン (SSO)) のトグル]
4. **[Single sign-on (SSO)](シングル サインオン (SSO))** セクションで、以下の手順に従います。

    [Image: [Single sign-on (SSO)](シングル サインオン (SSO)) セクション]

    1. **[識別子または発行者 URL]** ボックスに、コピーした **Microsoft Entra 識別子**の値を貼り付けます。
    2. **[SAML 2.0 Endpoint(HTTP)] (SAML 2.0 エンドポイント (HTTP))** ボックスに、コピーした**ログイン URL** の値を貼り付けます。
    3. ダウンロードした**証明書 (Base64)** をメモ帳で開きます。 証明書の内容をコピーし、その内容を **[Certificate (X.509)](証明書 (X.509))** ボックスに貼り付けます。
    4. **保存** を選択します。

#### Prezi のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Prezi に作成します。 Prezi では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Prezi にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Prezi サインオン URL にリダイレクトされます。
- Prezi のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Prezi に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Prezi] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Prezi に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/printer-logic-saas-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に PrinterLogic SaaS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/printer-logic-saas-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: Microsoft Entra ID から PrinterLogic SaaS に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために PrinterLogic SaaS と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[PrinterLogic SaaS](https://www.printerlogic.com/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- PrinterLogic SaaS でユーザーを作成する
- アクセスが不要になったときに PrinterLogic SaaS のユーザーを削除する
- Microsoft Entra ID と PrinterLogic SaaS の間でユーザー属性の同期を維持する
- PrinterLogic SaaS にグループとグループ メンバーシップをプロビジョニングする
- PrinterLogic SaaS への[シングルサインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/printerlogic-saas-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [PrinterLogic SaaS](https://www.printerlogic.com/) テナント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と PrinterLogic SaaS の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように PrinterLogic SaaS を構成する

1. PrinterLogic で、[ **ツール] &gt; [設定] &gt; [全般]** に移動します。
2. **[Identity Provider Settings](ID プロバイダーの設定)** セクションまでスクロールします。
3. **SCIM** オプションを選択します。
4. ドロップダウン メニューで **Microsoft Entra ID** が選択されていることを確認します。
5. [ **SCIM トークンの生成]** を選択します。

    [Image: SCIM トークン]
6. **[Bearer token] (ベアラー トークン)** をコピーして保存します。 この値は、PrinterLogic SaaS アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。
7. PrinterLogic SaaS アプリケーションの [プロビジョニング] タブの "[https://gw.app.printercloud.com/{instance_name}/scim/v2](https://gw.app.printercloud.com/%7Binstance_name%7D/scim/v2)" フィールドに  を入力します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから PrinterLogic SaaS を追加する

Microsoft Entra アプリケーション ギャラリーから PrinterLogic SaaS を追加して、PrinterLogic SaaS へのプロビジョニングの管理を開始します。 SSO のために PrinterLogic SaaS を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: PrinterLogic SaaS への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で PrinterLogic SaaS の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[PrinterLogic SaaS]** を選択します。

    [Image: アプリケーションの一覧の PrinterLogic SaaS リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、PrinterLogic SaaS テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が PrinterLogic SaaS に接続できることを確認します。 接続に失敗した場合は、PrinterLogic SaaS アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から PrinterLogic SaaS に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で PrinterLogic SaaS のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、PrinterLogic SaaS API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | タイトル | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:printercloud:2.0:User:authPin | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:printercloud:2.0:User:authPinUser | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:printercloud:2.0:User:badgeId | 糸 |  |
12. **[属性マッピング]** セクションで、Microsoft Entra ID から PrinterLogic SaaS に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で PrinterLogic SaaS のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | externalId | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/printerlogic-saas-tutorial"} -->
## Microsoft Entra ID で PrinterLogic for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/printerlogic-saas-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PrinterLogic の間でシングル サインオンを構成する方法について説明します。

この記事では、PrinterLogic と Microsoft Entra ID を統合する方法について説明します。 PrinterLogic と Microsoft Entra ID を統合すると、次のことができます。

- PrinterLogic にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して PrinterLogic に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PrinterLogic でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PrinterLogic では、**SP と IDP によって開始される SSO** サポートされます。
- PrinterLogic では、**Just In Time** ユーザー プロビジョニングがサポートされます。
- PrinterLogic では、[自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/printer-logic-saas-provisioning-tutorial)をサポートしています。

### ギャラリーから PrinterLogic を追加する

Microsoft Entra ID への PrinterLogic の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に PrinterLogic を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリー **から追加する**] セクションで、検索ボックスに「PrinterLogic  入力します。
4. 結果パネル **PrinterLogic** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PrinterLogic の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、PrinterLogic に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと PrinterLogic の関連ユーザーとの間にリンク関係を確立する必要があります。

PrinterLogic で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PrinterLogic SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **PrinterLogic テスト ユーザーを作成** - PrinterLogic で B.Simon に対応するテストユーザーを作成し、そのユーザーを Microsoft Entra 内の B.Simon にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**PrinterLogic**&gt;**シングルサインオン**に移動します。
3. [**シングル サインオン方法の選択]** ページで、[SAML 選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    基本的な SAML 構成を編集
5. [**基本的な SAML 構成**] セクションで、IDP **開始モードでアプリケーション** 構成する場合は、次のフィールドの値を入力します。

    ある。 [**識別子**] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://gw.app.printercloud.com/<my_instance>/authn/idp/azuread/saml2/metadata`

    b。 [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://gw.app.printercloud.com/<my_instance>/authn/idp/azuread/saml2/acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://www.<my_instance>printercloud.com`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[PrinterLogic クライアント サポート チーム](mailto:support@printerlogic.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. PrinterLogic アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: イメージ]
8. 上記に加えて、PrinterLogic アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 役割 | user.assignedroles |

    手記

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
9. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [**PrinterLogic** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PrinterLogic SSO の構成

PrinterLogic **側** シングル サインオンを構成するには、ダウンロードした **証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を PrinterLogic サポート チーム 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PrinterLogic テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを PrinterLogic に作成します。 PrinterLogic では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 PrinterLogic にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

PrinterLogic では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる PrinterLogic サインオン URL にリダイレクトされます。
- PrinterLogic のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDPが開始されました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した PrinterLogic に自動的にサインインします。
- Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで PrinterLogic タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した PrinterLogic に自動的にサインインされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/printix-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Printix を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/printix-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Printix の間のシングル サインオンを構成する方法について説明します。

この記事では、Printix と Microsoft Entra ID を統合する方法について説明します。 Printix を Microsoft Entra ID と統合すると、次のことが可能になります。

- Printix にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Printix に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理する。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Printix でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者に加え、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができる。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

Note

この記事の手順をテストするために、運用環境を使用することはお勧めしません。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Rootly では、 **SP** によって開始される SSO がサポートされます。
- Rootly では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Printix を追加する

Printix と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に Printix をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Printix**」と入力します。
4. 結果パネルから **Printix** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Printix 用に Microsoft Entra SSO を構成してテストする

このセクションでは、"Britta Simon" というテスト ユーザーに基づいて、Printix で Microsoft Entra のシングル サインオンを構成してテストします。

シングル サインオンを機能させるには、Microsoft Entra ID ユーザーに対応する Printix ユーザーが Microsoft Entra ID で認識されている必要があります。 言い換えると、Microsoft Entra ユーザーと、Printix での関連ユーザーとの間で、リンク関係が確立されている必要があります。

Printix で、Microsoft Entra ID の **ユーザー名** の値を **Username** の値として割り当ててリンク関係を確立します。

Printix で Microsoft Entra のシングル サインオンを構成してテストするには、次の手順を実行する必要があります。

1. **Microsoft Entra SSO の構成** - ユーザーがこの機能を使用できるようにします。

    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーの割り当て** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Printix のテスト ユーザーの作成 - Printix** で Britta Simon の対になるユーザーを作成し、Microsoft Entra 表示のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Printix**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **Printix のドメインと URL]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.printix.net`

    Note

    これは実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値を取得するには [、Printix クライアント サポート チーム](mailto:support@printix.net) に問い合わせてください。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **保存] ボタンを** 選択します。
8. Printix テナントに管理者としてサインオンします。
9. 上部のメニューで、右上隅にあるアイコンを選択し、[**認証**] を選択します。

    [Image: メニューから選択された認証を示すスクリーンショット。]
10. [ **セットアップ** ] タブで、[ **Azure/Office 365 認証を有効にする**] を選択します。
11. **[Azure**] タブで、[フェデレーション メタデータ ドキュメント] のテキスト ボックスに**フェデレーション メタデータ** URL を入力します。

    Microsoft Entra ID からダウンロードしたメタデータ xml ファイルを [Printix サポート チーム](mailto:support@printix.net)に添付します。 XML ファイルはサポート チームによってアップロードされ、フェデレーション メタデータの URL が支給されます。

    [Image: フェデレーション メタデータ ドキュメントを指定できる Printix.net ページを示すスクリーンショット。]
12. [テスト] ボタンを選択し、**テスト**が成功した場合は [**OK]** ボタンを選択します。

    **テスト** ボタンを選択すると、Microsoft Entra ID ページが表示されます。 ここで "テストが成功しました" とは、Azure テスト アカウントの資格情報を入力した後、"テストされた設定は OK" というメッセージが表示されることを意味します。次に、[ **OK** ] ボタンを選択します。

    [Image: テストの結果を示すスクリーンショット。]
13. [**認証**] ページの **[保存]** ボタンを選択します。

ヒント

これで、アプリのセットアップ中に、 [Azure portal](https://portal.azure.com) 内でこれらの手順の簡潔なバージョンを読むことができます。 **Active Directory &gt; Enterprise Applications** セクションからこのアプリを追加したら、[**シングル サインオン**] タブを選択し、下部にある **[構成**] セクションから埋め込みドキュメントにアクセスするだけです。 埋め込みドキュメント機能の詳細については、[Microsoft Entra ID の埋め込みドキュメント](https://go.microsoft.com/fwlink/?linkid=845985)を参照してください。

#### Microsoft Entra テスト ユーザーの作成

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

#### Microsoft Entra テスト ユーザーの割り当て

このセクションでは、B.Simon に Printix へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Printix]** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Printix テスト ユーザーの作成

このセクションの目的は、Printix で Britta Simon というユーザーを作成することです。 Printix では、Just-In-Time プロビジョニングがサポートされています。この設定は、既定で有効になっています。

このセクションにはアクション項目はありません。 存在しない Printix ユーザーにアクセスしようとすると、新しいユーザーが自動的に作成されます。

Note

ユーザーを手動で作成する必要がある場合は、 [Printix サポート チーム](mailto:support@printix.net)にお問い合わせください。

### SSO をテストする

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Printix のサインオン URL にリダイレクトされます。
- Printix のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Printix] タイルを選択すると、このオプションは Printix のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/priority-matrix-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して自動ユーザー プロビジョニング用に Priority Matrix を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/priority-matrix-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Priority Matrix に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に行うように、Microsoft Entra ID を構成する方法について説明します。

この記事の目的は、Priority Matrix と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Priority Matrix に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

Priority Matrix は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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
- [Priority Matrix テナント](https://appfluence.com/pricing/)。
- 管理者アクセス許可を持つ Priority Matrix上のユーザー アカウント。

### Priority Matrix にユーザーを割り当てる

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に割り当てという概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Priority Matrix へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定した後、次の手順に従って、これらのユーザーやグループを Priority Matrix に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### Priority Matrix へのユーザーの割り当てに関する重要なヒント

- 自動ユーザー プロビジョニング構成をテストするには、1 人の Microsoft Entra ユーザーを Priority Matrix に割り当てることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Priority Matrix にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### プロビジョニング用に Priority Matrix を設定する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Priority Matrix を構成する前に、Priority Matrix からプロビジョニング情報を取得する必要があります。

1. [Priority Matrix 管理コンソール](https://sync.appfluence.com/accounts/login/?next=/accounts/provisioning)にサインインします。
2. 優先度マトリックスの **Oauth ログイン トークン** を選択する

    [Image: プライオリティマトリックスにSCIMを追加。]
3. [ **新しいトークンの取得** ] ボタンを選択します。 **[Token String](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/トークン文字列)** をコピーします。 この値は、Priority Matrix アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

### ギャラリーから Priority Matrix を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に Priority Matrix を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Priority Matrix を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、「**Priority Matrix**」と入力し、結果パネルで **[Priority Matrix]** を選択します。

    [Image: 結果一覧の Priority Matrix]
4. **[Priority Matrix にサインアップ]** ボタンを選択します。Priority Matrix のログイン ページにリダイレクトされます。

    [Image: Priority Matrix での OIDC の追加]
5. Priority Matrix は OpenIDConnect アプリであるため、Microsoftの職場アカウントを使用して Priority Matrix にサインインすることを選択します。

    [Image: Priority Matrix での OIDC のログイン]
6. 認証に成功した後、同意ページの同意プロンプトを受け入れます。 その後、アプリケーションがテナントに自動的に追加され、Priority Matrix アカウントにリダイレクトされます。

### Priority Matrix への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Priority Matrix でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

注

Priority Matrix の SCIM エンドポイントについて詳しくは、「[ユーザー プロビジョニングと Priority Matrix](https://appfluence.com/help/article/user-provisioning/)」をご覧ください。

#### Microsoft Entra ID で Priority Matrix の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **[Priority Matrix]** を選択します。

    [Image: アプリケーション一覧での [Priority Matrix] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、優先度マトリックスのテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Priority Matrix に接続できることを確認します。 接続に失敗した場合は、Priority Matrix アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    注

    `https://sync.appfluence.com/scim/v2/` に「」と入力します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Priority Matrix に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Priority Matrix のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Priority Matrix API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Priority Matrix ユーザー属性のスクリーンショット。]
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/prisma-cloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Prisma Cloud SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/prisma-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Prisma Cloud SSO の間にシングル サインオンを構成する方法について説明します。

この記事では、Prisma Cloud SSO と Microsoft Entra ID を統合する方法について説明します。 Prisma Cloud SSO を Microsoft Entra ID と統合すると、次のことができます。

- Prisma Cloud SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Prisma Cloud SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Prisma Cloud SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Prisma Cloud SSO では、 **IDP** Initiated SSO がサポートされます。
- Prisma Cloud SSO では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Prisma Cloud SSO の追加

Microsoft Entra ID への Prisma Cloud SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに Prisma Cloud SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「Prisma Cloud SSO**」と入力します。
4. 結果パネルから **Prisma Cloud SSO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Prisma Cloud SSO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Prisma Cloud SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Prisma Cloud SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Prisma Cloud SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Prisma Cloud の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Prisma Cloud SSO テストユーザーの作成** - B.Simon の代わりとなるユーザーを Prisma Cloud SSO に作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Prisma Cloud SSO**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app2.prismacloud.io/customer/<CUSTOMERID>`

    b。 **応答 URL** の値は固定されており、Azure portal では既に事前に設定されています。 実際の要件に応じた適切な URL を選択する必要があります。

    注

    識別子の値は実際の値ではありません。 実際の識別子で値を更新します。 この値を取得するには [、Prisma Cloud SSO クライアント サポート チーム](mailto:support@paloaltonetworks.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Prisma Cloud SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Prisma Cloud の SSO の構成

**Prisma Cloud SSO** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Prisma Cloud SSO サポート チーム](mailto:support@paloaltonetworks.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Prisma Cloud SSO のテスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Prisma Cloud SSO に作成します。 Prisma Cloud SSO では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Prisma Cloud SSO に存在しない場合は、Prisma Cloud SSO にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Prisma Cloud SSO に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで Prisma Cloud SSO タイルを選択すると、SSO を設定した Prisma Cloud SSO に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/proactis-rego-invoice-capture-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Proactis Rego Invoice Capture を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proactis-rego-invoice-capture-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Proactis Rego Invoice Capture の間でシングル サインオンを構成する方法について説明します。

この記事では、Proactis Rego Invoice Capture と Microsoft Entra ID を統合する方法について説明します。 Proactis の AP 自動化を使用すると、すべての請求書をキャプチャして e 請求書に変換し、その正確性、重複、有効なサプライヤーを検証したうえでご自分の財務システムに転送できます。 Microsoft Entra ID と Proactis Rego Invoice Capture を統合すると、以下が可能になります。

- Proactis Rego Invoice Capture にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して Proactis Rego Invoice Capture に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Proactis Rego Invoice Capture のための Microsoft Entra シングル サインオンを構成してテストする。 Proactis Rego Invoice Capture は、**SP initiated** と **IDP initiated** のシングル サインオンに対応しています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID と Proactis Rego Invoice Capture を統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Proactis Rego Invoice Capture のシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Proactis Rego Invoice Capture アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Proactis Rego Invoice Capture を追加する

Microsoft Entra アプリケーション ギャラリーから Proactis Rego Invoice Capture を追加して、Proactis Rego Invoice Capture とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Proactis Rego Invoice Capture**&gt;**シングルサインオン**を閲覧します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** テキストボックスに、次のいずれかの URL を入力します。

    | **識別子** |
    | --- |
    | `https://eu-p5.proactiscloud.com` |
    | `https://eu-p5-uat.proactiscloud.com` |
    | `https://us-p5-icmanaged.proactiscloud.com` |
    | `https://us-p5-icmanageduat.proactiscloud.com` |
    | `https://hosted.proactiscapture.com` |
    | `https://hosteduat.proactiscapture.com` |
    | `https://managed.proactiscapture.com` |
    | `https://manageduat.proactiscapture.com` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://manageduat.proactiscapture.com/SSO/<CustomerName>/AssertionConsumerService` |
    | `https://managed.proactiscapture.com/SSO/<CustomerName>/AssertionConsumerService` |
    | `https://hosteduat.proactiscapture.com/SSO/<CustomerName>/AssertionConsumerService` |
    | `https://hosted.proactiscapture.com/SSO/<CustomerName>/AssertionConsumerService` |
    | `https://us-p5-icmanageduat.proactiscloud.com/SSO/<CustomerName>/AssertionConsumerService` |
    | `https://us-p5-icmanaged.proactiscloud.com/SSO/<CustomerName>/AssertionConsumerService` |
    | `https://eu-p5-uat.proactiscloud.com/SSO/<CustomerName>/AssertionConsumerService` |
    | `https://eu-p5.proactiscloud.com/SSO/<CustomerName>/AssertionConsumerService` |
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://manageduat.proactiscapture.com/SSO/<CustomerName>` |
    | `https://managed.proactiscapture.com/SSO/<CustomerName>` |
    | `https://hosteduat.proactiscapture.com/SSO/<CustomerName>` |
    | `https://hosted.proactiscapture.com/SSO/<CustomerName>` |
    | `https://us-p5-icmanageduat.proactiscloud.com/SSO/<CustomerName>` |
    | `https://us-p5-icmanaged.proactiscloud.com/SSO/<CustomerName>` |
    | `https://eu-p5-uat.proactiscloud.com/SSO/<CustomerName>` |
    | `https://eu-p5.proactiscloud.com/SSO/<CustomerName>` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Proactis Rego Invoice Capture クライアントのサポート チーム](mailto:support@proactis.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Set up Proactis Rego Invoice Capture] (Proactis Rego Invoice Capture のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Proactis Rego Invoice Capture の SSO を構成する

**Proactis Rego Invoice Capture** 側でシングル サインオンを構成するには、**証明書 (PEM)** とアプリケーション構成からコピーした適切な URL を [Proactis Rego Invoice Capture サポート チーム](mailto:support@proactis.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Proactis Rego Invoice Capture のテスト ユーザーを作成する

このセクションでは、Proactis Rego Invoice Capture で Britta Simon というユーザーを作成します。 [Proactis Rego Invoice Capture サポート チーム](mailto:support@proactis.com)と連携して、Proactis Rego Invoice Capture プラットフォームでユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Proactis Rego Invoice Capture のサインオン URL にリダイレクトされます。
- Proactis Rego Invoice Capture のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Proactis Rego Invoice Capture に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Proactis Rego Invoice Capture] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Proactis Rego Invoice Capture に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/proactis-rego-source-to-contract-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Proactis Rego Source-to-Contract を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proactis-rego-source-to-contract-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Proactis Rego Source-to-Contract の間でシングル サインオンを構成する方法について説明します。

この記事では、Proactis Rego Source-to-Contract を Microsoft Entra ID と統合する方法について説明します。 Proactis Rego は、中規模の組織向けに設計された、強力な Source-to-Contract ソフトウェア プラットフォームです。 使いやすく、簡単に統合でき、支出とサプライ チェーンのリスクを管理できます。 Proactis Rego Source-to-Contract を Microsoft Entra ID と統合すると、次のことができます。

- Proactis Rego Source-to-Contract にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して Proactis Rego Source-to-Contract に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Proactis Rego Source-to-Contract のための Microsoft Entra シングル サインオンを構成してテストする。 Proactis Rego Source-to-Contract は、**SP** Initiated シングル サインオンをサポートしています。

### [前提条件]

Microsoft Entra ID を Proactis Rego Source-to-Contract と統合するには、次のことが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Proactis Rego Source-to-Contract のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Proactis Rego Source-to-Contract アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Proactis Rego Source-to-Contract を追加する

Microsoft Entra アプリケーション ギャラリーから Proactis Rego Source-to-Contract を追加して、Proactis Rego Source-to-Contract でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Proactis Rego Source-to-Contract]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.proactisplaza.com/authentication/saml/<CustomerName>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.proactisplaza.com/authentication/saml/<CustomerName>/consume`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.proactisplaza.com/authentication/saml/<CustomerName>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Proactis Rego Source-to-Contract のサポート チーム](mailto:helpdesk@proactis.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Proactis Rego Source-to-Contract のセットアップ]** セクションで、要件に基づいて該当の URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Proactis Rego Source-to-Contract の SSO を構成する

**Proactis Rego Source-to-Contract** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** と、アプリケーション構成からコピーした該当の URL を [Proactis Rego Source-to-Contract のサポート チーム](mailto:helpdesk@proactis.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Proactis Rego Source-to-Contract のテスト ユーザーを作成する

このセクションでは、Proactis Rego Source-to-Contract で Britta Simon というユーザーを作成します。 [Proactis Rego Source-to-Contract のサポート チーム](mailto:helpdesk@proactis.com)と協力して、Proactis Rego Source-to-Contract プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Proactis Rego のソースからコントラクトへのサインオン URL にリダイレクトされます。
- Proactis Rego Source-to-Contract のサインオン URL に直接アクセスし、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Proactis Rego Source-to-Contract] タイルを選択すると、このオプションは Proactis Rego Source-to-Contract のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/proactis-rego-source-to-pay-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Proactis Rego Source-to-Pay を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proactis-rego-source-to-pay-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Proactis Rego Source-to-Pay の間でシングル サインオンを構成する方法について説明します。

この記事では、Proactis Rego Source-to-Pay と Microsoft Entra ID を統合する方法について説明します。 Proactis Rego は、中規模の組織向けに設計された、強力な Source-to-Pay ソフトウェア プラットフォームです。 使いやすく、簡単に統合でき、支出とサプライ チェーンのリスクを管理できます。 Microsoft Entra ID と Proactis Rego Source-to-Pay を統合すると、次のことが可能になります。

- Proactis Rego Source-to-Pay にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して Proactis Rego Source-to-Pay に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Proactis Rego Source-to-Pay の Microsoft Entra シングル サインオンを構成してテストできます。 Proactis Rego Source-to-Pay は、**SP** Initiated シングル サインオンをサポートしています。

### [前提条件]

Microsoft Entra ID と Proactis Rego Source-to-Pay を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Proactis Rego Source-to-Pay のシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Proactis Rego Source-to-Pay アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Proactis Rego Source-to-Pay を追加する

Microsoft Entra アプリケーション ギャラリーから Proactis Rego Source-to-Pay を追加して、Proactis Rego Source-to-Pay とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Proactis Rego Source-to-Pay**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://consult.esize.nl/domain/<domainId>` |
    | `https://start.esize.nl/domain/<domainId>` |
    | `https://bsmuk-uat.proactiscloud.com/domain/<domainId>` |
    | `https://bsmuk.proactiscloud.com/domain/<domainId>` |
    | `https://pxus-con.proactiscloud.com/domain/<domainId>` |
    | `https://bsmus.proactiscloud.com/domain/domainId` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://consult.esize.nl/saml/domain/<domainId>/login` |
    | `https://start.esize.nl/saml/domain/<domainId>/login` |
    | `https://bsmuk-uat.proactiscloud.com/saml/domain/<domainId>/login` |
    | `https://bsmuk.proactiscloud.com/saml/domain/<domainId>/login` |
    | `https://pxus-con.proactiscloud.com/saml/domain/<domainId>/login` |
    | `https://bsmus.proactiscloud.com/saml/domain/<domainId>/login` |

    c. [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://consult.esize.nl/saml/domain/<domainId>` |
    | `https://start.esize.nl/saml/domain/<domainId>` |
    | `https://bsmuk-uat.proactiscloud.com/saml/domain/<domainId>` |
    | `https://bsmuk.proactiscloud.com/saml/domain/<domainId>` |
    | `https://pxus-con.proactiscloud.com/saml/domain/<domainId>` |
    | `https://bsmus.proactiscloud.com/saml/domain/<domainId>` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Proactis Rego Source-to-Pay サポート チーム](mailto:itcrowd@proactis.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクのスクリーンショット。]
7. **[Proactis Rego Source-to-Pay のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Proactis Rego Source-to-Pay SSO の構成

**Proactis Rego Source-to-Pay** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** と、アプリケーション構成からコピーした適切な URL を [Proactis Rego Source-to-Pay サポート チーム](mailto:itcrowd@proactis.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Proactis Rego Source-to-Pay テスト ユーザーの作成

このセクションでは、Proactis Rego Source-to-Pay で Britta Simon というユーザーを作成します。 [Proactis Rego Source-to-Pay サポート チーム](mailto:itcrowd@proactis.com)と連携して、Proactis Rego Source-to-Pay プラットフォームでユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Proactis Rego Source-to-Pay のサインオン URL にリダイレクトされます。
- Proactis Rego Source-to-Pay のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Proactis Rego Source-to-Pay] タイルを選択すると、このオプションは Proactis Rego Source-to-Pay のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/procaire-tutorial"} -->
## Microsoft Entra ID で Procaire for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/procaire-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Procaire の間でシングル サインオンを構成する方法について説明します。

この記事では、Procaire と Microsoft Entra ID を統合する方法について説明します。 Procaire と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Procaire へのアクセス権を管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Procaire に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Procaire でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Procaire は、SPが開始したSSOとIDPが開始したSSOをサポートします。

### ギャラリーからの Procaire の追加

Microsoft Entra ID への Procaire の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Procaire を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Procaire**」と入力します。
4. 結果のパネルから Procaire  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Procaire の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Procaire に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Procaire の関連ユーザーとの間にリンク関係を確立する必要があります。

Procaire に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Procaire の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Procaireテストユーザーの作成 - B.Simonに相当するユーザーをProcaireで作成し、Microsoft Entraのユーザー表現にリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Procaire]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **[保存] を選択します**。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Procaire SSO を構成する

**Procaire** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を** Praisidio サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Procaire テスト ユーザーの作成

このセクションでは、Procaire で Britta Simon というユーザーを作成します。 Praisidio サポート チームと協力して、Procaire プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Procaire のサインオン URL にリダイレクトされます。
- Procaire のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Procaire に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [Procaire] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Procaire に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/processunity-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ProcessUnity を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/processunity-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ProcessUnity の間のシングル サインオンを構成する方法について説明します。

この記事では、ProcessUnity と Microsoft Entra ID を統合する方法について説明します。 ProcessUnity を Microsoft Entra ID と統合すると、次のことが可能になります。

- ProcessUnity にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで ProcessUnity に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ProcessUnity でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ProcessUnity では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- ProcessUnity では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの ProcessUnity の追加

ProcessUnity と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に ProcessUnity をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ProcessUnity**」と入力します。
4. 結果のパネルから **[ProcessUnity]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ProcessUnity 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ProcessUnity 用の Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、ProcessUnity での関連ユーザーとの間にリンク関係を確立する必要があります。

ProcessUnity 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ProcessUnity の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ProcessUnity のテストユーザーを作成する** - ProcessUnity で B.Simon と同等のユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ProcessUnity**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.processunity.net/<DOMAIN_NAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.processunity.net/<DOMAIN_NAME>/SAML/AssertionConsumerServiceV2.aspx`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.processunity.net/<DOMAIN_NAME>/SAML/SamlLoginV2.aspx`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[ProcessUnity クライアント サポート チーム](mailto:customer.support@processunity.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[ProcessUnity のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ProcessUnity の SSO の構成

**ProcessUnity** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[ProcessUnity サポート チーム](mailto:customer.support@processunity.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ProcessUnity のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを ProcessUnity に作成します。 ProcessUnity では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 ProcessUnity にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ProcessUnity サインオン URL にリダイレクトされます。
- ProcessUnity のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ProcessUnity に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ProcessUnity] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ProcessUnity に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/procoresso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Procore SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/procoresso-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Procore SSO の間にシングル サインオンを構成する方法について説明します。

この記事では、Procore SSO と Microsoft Entra ID を統合する方法について説明します。 Procore SSO と Microsoft Entra ID を統合すると、次のことができます。

- Procore SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Procore SSO に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

Procore SSO は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Procore SSO シングル サインオン対応のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Procore SSO では、**IDP** Initiated SSO がサポートされています。

### ギャラリーからの Procore SSO の追加

Microsoft Entra ID への Procore SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Procore SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Procore SSO**」と入力します。
4. 結果のパネルから **[Procore SSO]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Procore SSO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Procore SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Procore SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Procore SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Procore SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Procore SSO テスト ユーザーの作成** - Microsoft Entra のユーザー表現にリンクされた、Procore SSO 内の B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Procore SSO**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Procore SSO のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Procore SSO の構成

1. **Procore SSO** 側のシングル サインオンを構成するために、Procore の企業サイトに管理者としてサインインします。
2. ツールボックスのドロップダウンから [ **管理者** ] を選択し、SSO 設定ページを開きます。

    [Image: [Directory](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ディレクトリ) が選択された Procore の企業サイトを示すスクリーンショット。]
3. 以下の説明に従って、各ボックスに値を貼り付けます。

    [Image: [Add a Person](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ダイアログ ボックスを示すスクリーンショット。]

    ある。 **Single Sign On Issuer URL** テキスト ボックスに、前にコピーした **Microsoft Entra Identifier** の値を貼り付けます。

    b。 **[SAML サインオンのターゲット URL]** ボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    c. 次に、先ほどダウンロードした**フェデレーション メタデータの XML** を開いて、**X509Certificate** という名前のタグ内の証明書をコピーします。 コピーした値を、 **[Single Sign On x509 Certificate] \(シングル サインオン x509 証明書)** ボックスに貼り付けます。
4. [ **変更の保存] を選択します**。
5. これらの設定の後、Procore にログインする **ドメイン名** ( `contoso.com`) を [Procore サポート チーム](https://support.procore.com/) に送信する必要があります。これにより、そのドメインのフェデレーション SSO がアクティブになります。

#### Procore SSO のテスト ユーザーを作成する

以下の手順に従って、Procore SSO の側で Procore テスト ユーザーを作成します。

1. Procore 企業サイトに管理者としてサインインします。
2. ツールボックスのドロップダウンから [ **ディレクトリ** ] を選択して、会社のディレクトリ ページを開きます。

    [Image: ツールボックスから [Directory](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ディレクトリ) が選択された Procore の企業サイトを示すスクリーンショット。]
3. [ **ユーザーの追加] オプションを** 選択してフォームを開き、「次のオプションを実行する」と入力します。

    [Image: ユーザー情報を入力できる [Add a person to Boylan Construction](Boylan Construction にユーザーを追加する) を示すスクリーンショット。]

    ある。 **[First Name] \(名)** テキストボックスに、ユーザーの名を入力します (この例では **Britta**).

    b。 **[Last Name] \(姓)** テキストボックスに、ユーザーの姓を入力します (この例では **Simon**).

    c. **[Email Address] \(メール アドレス)** ボックスに、ユーザーのメール アドレスを入力します (この例では BrittaSimon@contoso.com)。

    d. **[Permission Template] \(アクセス許可テンプレート)** で **[Apply Permission Template Later] \(アクセス許可テンプレートは後で適用する)** を選択します。

    え **[作成]** を選択します。
4. 新しく追加された連絡先の詳細を確認して更新します。

    [Image: ユーザー設定を確認できる編集ページを示すスクリーンショット。]
5. ユーザー登録を完了するには、[ **保存して招待を送信** する (メールによる招待が必要な場合)] または **[保存** (直接保存)] を選択します。

    [Image: 招待状を保存して送信できる [Current Project Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/現在のプロジェクトの設定) を示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Procore SSO に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Procore SSO] タイルを選択すると、SSO を設定した Procore SSO に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/prodog-tutorial"} -->
## Microsoft Entra ID で Prodog for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/prodog-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-01
- Summary: Microsoft Entra ID と Prodog の間のシングル サインオンを構成する方法についてご確認ください。

この記事では、Prodog と Microsoft Entra ID を統合する方法について説明します。 Prodog と Microsoft Entra ID を統合すると、次のことができます。

- Prodog にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Prodog に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Prodog のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Prodog では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Prodog の追加

Microsoft Entra ID への Prodog の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Prodog を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Prodog**」と入力します。
4. 結果のパネルから **[Prodog]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Prodog 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Prodog に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Prodog の関連ユーザーとの間にリンク関係を確立する必要があります。

Prodog に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Prodog SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Prodog テスト ユーザーの作成 - Prodog** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Prodog**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のいずれかの URL を入力します。

    | 識別子 |
    | --- |
    | `https://t.leanwo.com` |
    | `https://u.leanwo.com` |

    b。 **[応答 URL]** ボックスに、次のいずれかの URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://tt.leanwo.com/api/saml/sso/a` |
    | `https://u.leanwo.com/api/saml/sso/a` |

    c. **[サインオン URL]** テキスト ボックスでは、次のいずれかの URL を入力します。

    | サインオン URL |
    | --- |
    | `https://t.leanwo.com` |
    | `https://u.leanwo.com` |
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書の [ダウンロード] リンクを示すスクリーンショット。]
7. **[Prodog のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成のURL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Prodog の SSO を構成する

**Prodog** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と Microsoft Entra 管理センターからコピーした適切な URL を [Prodog サポート チーム](mailto:15800458450@leanwo.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Prodog のテスト ユーザーの作成

このセクションでは、Prodog で B.Simon というユーザーを作成します。 [Prodog サポート チーム](mailto:15800458450@leanwo.com)と連携して、Prodog プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Prodog のサインオン URL にリダイレクトします。
- Prodog のサインオン URL に直接移動し、そこからログイン フローを開始します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/prodpad-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に ProdPad を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/prodpad-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: Microsoft Entra ID から ProdPad に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために ProdPad と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使って、[ProdPad](https://www.prodpad.com/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- ProdPad でユーザーを作成します。
- アクセスが不要になった場合は、ProdPad でユーザーを削除します。
- Microsoft Entra ID と ProdPad の間でユーザー属性の同期を維持する。
- ProdPad に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/prodpad-tutorial)します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある ProdPad のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と ProdPad の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように ProdPad を構成する

1. [ProdPad 管理コンソール](https://app.prodpad.com/)にサインインします。
2. **[プロファイル設定]** に移動します。

    [Image: プロファイル]
3. API キー **に移動して** API キーを取得します。 API キーを再生成する必要がある場合は、再生成キーを選択します。 これにより、以前の API キーも無効になります。

    [Image: API キー]
4. **API キー**をコピーして保存します。 この値は、ProdPad アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから ProdPad を追加する

Microsoft Entra アプリケーション ギャラリーから ProdPad を追加して、ProdPad へのプロビジョニングの管理を開始します。 以前に、[SSO 用に ProdPad](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/prodpad-tutorial) を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: ProdPad への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、ProdPad でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で ProdPad の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[ProdPad]** を選択します。

    [Image: アプリケーションの一覧の ProdPad のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、ProdPad テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が ProdPad に接続できることを確認します。 接続に失敗した場合は、ProdPad アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から ProdPad に同期されるユーザー属性を確認します。 **照合**用プロパティとして選択されている属性は、更新処理で ProdPad のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、ProdPad API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | ProdPad で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | 活動中 | ブール値 |  |  |
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

### トラブルシューティングのヒント

問題が発生した場合は、[ProdPad サポート チーム](mailto:help@prodpad.com)にお問い合わせください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/prodpad-tutorial"} -->
## Microsoft Entra ID で ProdPad for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/prodpad-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ProdPad の間にシングル サインオンを構成する方法について説明します。

この記事では、ProdPad と Microsoft Entra ID を統合する方法について説明します。 ProdPad と Microsoft Entra ID を統合すると、次のことができます:

- ProdPad にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って ProdPad に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ProdPad でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ProdPad では、**SP Initiated SSO と IDP Initiated SSO** がサポートされています。
- ProdPad では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。
- ProdPad では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/prodpad-provisioning-tutorial)がサポートされています。

### ギャラリーからの ProdPad の追加

Microsoft Entra ID への ProdPad の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ProdPad を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ProdPad**」と入力します。
4. 結果のパネルから **[ProdPad]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ProdPad 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ProdPad に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ProdPad の関連ユーザーとの間にリンク関係を確立する必要があります。

ProdPad に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ProdPad の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ProdPadテストユーザーの作成** - ProdPadでB.Simonに対応するユーザーを作成し、そのユーザーをMicrosoft Entra表現のユーザーとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**ProdPad**&gt;**シングル サインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** テキスト ボックスに、URL として「`https://app.prodpad.com/login`」と入力します。
7. **保存** を選択します。
8. ProdPad アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
9. その他に、ProdPad アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユーザー.名 | User.givenname |
    | ユーザーの名字 | User.surname |
    | User.ProdpadRole | user.assignedroles |

    注意

    ProdPad では、アプリケーションに対してユーザーのロールが割り当てられていることを想定しています。 ユーザーに適切なロールを割り当てることができるように、Microsoft Entra ID でこれらのロールを設定してください。 Microsoft Entra ID でロールを構成する方法については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)を参照してください。
10. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
11. **[ProdPad のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ProdPad の SSO の構成

1. [アカウント設定] に移動し、[セキュリティ] タブを選択します。
2. 次に、[SSO/SAML] サブタブを選択します。
3. [認証の種類の追加] ボタンを選択し、ドロップダウンから Microsoft Entra を選択します。
4. Microsoft Entra モーダルの [次へ] ボタンを選択します。
5. ProdPad の "IdP エンティティ ID/URL" とラベル付けされたフィールドに、Microsoft Entra の [Microsoft Entra 識別子] フィールドから URL をコピーします。
6. ProdPad の [IdP SAML シングル サインオン URL] フィールドに、Microsoft Entra の [ログイン URL] フィールドの URL をコピーします。
7. ProdPad の [ログアウト URL] フィールドに、Microsoft Entra の [ログアウト URL] フィールドの URL をコピーします。
8. X.509 証明書 (上記で生成された公開キー) のテキストを、[X.509 証明書] フィールドに貼り付けます。

ここで、ユーザーが IdP Initiated ログインでのみログインするか、IdP および SP Initiated ログインでログインするかを決定する必要があります。

1. IdP のみを選択した場合、ユーザーは ProdPad ログイン ページではなく Microsoft Entra ダッシュボードからログインする必要があります。

    1. [保存] を選択します。 ユーザーは、Microsoft Entra ダッシュボードで ProdPad アプリのリンクを使用できるようになりました。
2. IdP および SP Initiated ログインを選択する場合

    1. ユーザーがログインできるドメインを設定する必要があります。詳細については、[こちら](https://help.prodpad.com/article/704-domain-verification)をご覧ください
    2. セットアップと確認が完了したら、[ドメイン] の一覧からドメインを選択します。
    3. [保存] をクリックします。

**注: ドメインをオプションとしてここに表示するには、[ドメイン] タブで確認する必要があります。**

#### ProdPad のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを ProdPad に作成します。 ProdPad では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 ProdPad にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ProdPad のサインオン URL にリダイレクトされます。
- ProdPad のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ProdPad に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [ProdPad] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ProdPad に自動的にサインインされます。 アクセス パネルの詳細については、[アクセス パネルの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/productboard-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に productboard を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/productboard-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と productboard の間でシングル サインオンを構成する方法について説明します。

この記事では、productboard と Microsoft Entra ID を統合する方法について説明します。 productboard と Microsoft Entra ID を統合すると、次のことができます。

- productboard にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して productboard に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- productboard でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- productboard では、SP起動型SSOとIDP起動型SSOがサポートされます。
- productboard では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの productboard の追加

Microsoft Entra ID への productboard の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に productboard を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「productboard**」と入力します。
4. 結果パネルから **productboard** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### productboard 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、productboard に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、productboard の関連ユーザーとの間にリンク関係を確立する必要があります。

productboard との Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **productboard SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **productboard テスト ユーザーの作成** - Microsoft Entra のユーザー表現とリンクされた B.Simon の productboard 対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**productboard** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<PROJECTNAME>.productboard.com/users/auth/saml/callback`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<PROJECTNAME>.productboard.com/`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには、 [productboard クライアント サポート チーム](mailto:support@productboard.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### productboard SSO の構成

1. **アプリのフェデレーション メタデータ URL を**[productboard サポート チーム](mailto:support@productboard.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### productboard テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを productboard に作成します。 productboard では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 productboard にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはサインイン フローを開始できる productboard のサインオン URL にリダイレクトされます。
- productboard のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した productboard に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで productboard タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインイン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した productboard に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/productive-tutorial"} -->
## Microsoft Entra ID で Productive for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/productive-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Productive の間にシングル サインオンを構成する方法について説明します。

この記事では、Productive と Microsoft Entra ID を統合する方法について説明します。 Productive と Microsoft Entra ID を統合すると、次のことができます。

- Productive にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Productive に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Productive でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Productive では、**SP と IDP** によって開始される SSO がサポートされます。
- Productive では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### Productive をギャラリーから追加する

Microsoft Entra ID への Productive の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Productive を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Productive**」と入力します。
4. 結果のパネルから **[Productive]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Productive 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Productive に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Productive の関連ユーザーとの間にリンク関係を確立する必要があります。

Productive に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Productive SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Productive テスト ユーザーの作成** - Productive において B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 上の B.Simon の表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Productive**&gt;**シングルサインオン**にブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    ある。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.productive.io/api/v2/sessions/consume_single_sign_on?account_id=<ID>&app=https://latest.productive.io/public/sso`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.productive.io/public/sso`

    注

    この値は実際の値ではありません。 実際の応答 URL でこの値を更新します。 これらの値を取得するには、[Productive クライアント サポート チーム](mailto:support@productive.io)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Productive アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Productive ではこれをユーザーのメール アドレスにマップすることが求められます。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Productive SSO の構成

**Productive** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Productive サポート チーム](mailto:support@productive.io)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Productive のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Productive に作成します。 Productive では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Productive にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Productive サインオン URL にリダイレクトされます。
- Productive のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Productive に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Productive] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Productive に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/productplan-tutorial"} -->
## Microsoft Entra ID で ProductPlan for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/productplan-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-31
- Summary: Microsoft Entra ID と ProductPlan の間にシングル サインオンを構成する方法について学習します。

この記事では、ProductPlan と Microsoft Entra ID を統合する方法について説明します。 ProductPlan を Microsoft Entra ID と統合すると、以下のことが可能になります。

- どのユーザーに ProductPlan へのアクセスを許可するかを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って ProductPlan に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な ProductPlan のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ProductPlan では、**SP と IDP** の Initiated SSO のみがサポートされています。
- ProductPlan では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから ProductPlan を追加する

ProductPlan から Microsoft Entra ID への統合を構成するには、ギャラリーから、お使いのマネージド SaaS アプリのリストに ProductPlan を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ProductPlan**」と入力します。
4. 結果のパネルから **[ProductPlan]** を選び、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO を ProductPlan 用に構成し、テストする

**B.Simon** というテスト ユーザーを使用して、ProductPlan で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ProductPlan の関連ユーザーとの間にリンク関係を確立する必要があります。

ProductPlan で Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ProductPlan SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ProductPlan のテストユーザーを作成** - ProductPlan で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**ProductPlan**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、**サービス プロバイダー メタデータ ファイル**がある場合は、次の手順に従います。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルのアップロードの方法を示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択方法を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、**[基本的な SAML 構成]** セクションに値が自動的に設定されます。

    注

    値が自動的に入力されない場合は、要件に従って値を手動で入力してください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** テキスト ボックスに、URL として「`https://app.productplan.com`」と入力します。
7. ProductPlan アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性構成の画像を示すスクリーンショット。]
8. 上記に加えて、ProductPlan アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ディスプレイ名 | ユーザー表示名 |
    | メール | User.mail |
9. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[ProductPlan のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ProductPlan SSO を構成する

1. ProductPlan 企業サイトに管理者としてログインします。
2. **[アカウント]**&gt;**[セキュリティ]** に移動し、次の手順を実行します。[Image: 構成のアカウント設定を示すスクリーンショット。]

    1. Microsoft Entra 管理センターから**フェデレーション メタデータ XML** をダウンロードし、XML コンテンツを **IDP メタデータ** テキスト ボックスに貼り付けます。
    2. サービス プロバイダーのメタデータをダウンロードし、Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションにファイルをアップロードします。
    3. **保存** を選択します。

#### ProductPlan テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを ProductPlan 内に作成します。 ProductPlan では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 ProductPlan にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる ProductPlan サインオン URL にリダイレクトします。
- ProductPlan のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した ProductPlan に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで ProductPlan タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ProductPlan に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/profitco-saml-app-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Profit.co を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/profitco-saml-app-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Profit.co 間にシングル サインオンを構成する方法について学習します。

この記事では、Profit.co と Microsoft Entra ID を統合する方法について説明します。 Profit.co を Microsoft Entra ID と統合すると、次のことができます。

- Profit.co にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Profit.co に自動的にサインインできるようにする。
- 1 つの中央の場所 (Azure portal) でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Profit.co でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Profit.co では、IDP Initiated SSO がサポートされます。

### ギャラリーからの Profit.co の追加

Microsoft Entra ID への Profit.co の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Profit.co を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**にアクセスします。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックス **に「Profit.co** 」と入力します。
4. 結果パネルから **Profit.co** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Profit.co 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Profit.co に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Profit.co の関連ユーザーとの間にリンク関係を確立します。

Profit.co で Microsoft Entra SSO を構成してテストする一般的な手順を以下に示します。

1. ユーザーがこの機能を使用できるように **Microsoft Entra SSO を構成**します。
    1. B.Simon で Microsoft Entra のシングル サインオンをテストする Microsoft **Entra テスト ユーザーを作成**します。
    2. **B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます** 。
2. **Profit.co SSO を構成**して、アプリケーション側でシングル サインオン設定を構成します。
    1. **Profit.co で B.Simon** に対応するユーザーを作成する Profit.co テスト ユーザーを作成します。この対応するユーザーは、Microsoft Entra のユーザー表現にリンクされています。
3. **SSO をテスト** して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Profit.co** アプリケーション統合ページに移動し、[**管理**] セクションを見つけます。 **[シングル サインオン]** を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 鉛筆アイコンが強調表示された [SAML でシングル サインオンを設定する] ページのスクリーンショット]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションが事前に構成されており、必要な URL が既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **コピー** ] ボタンを選択します。 これにより、 **アプリのフェデレーション メタデータ URL が** コピーされ、コンピューターに保存されます。

    [Image: [コピー] ボタンが強調表示されている SAML 署名証明書のスクリーンショット]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Profit.co の SSO の構成

Profit.co 側でシングル サインオンを構成するには、Profit.co [サポート チーム](mailto:support@profit.co)にアプリのフェデレーション メタデータ URL を送信する必要があります。 この設定が構成され、SAML SSO 接続が両側で正しく行われます。

#### Profit.co のテスト ユーザーの作成

このセクションでは、Profit.co で B.Simon というユーザーを作成します。 [Profit.co サポート チーム](mailto:support@profit.co) と協力して、Profit.co プラットフォームにユーザーを追加します。 ユーザーを作成してアクティブ化するまでは、シングル サインオンを使用できません。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Profit.co に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Profit.co] タイルを選択すると、SSO を設定した Profit.co に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/projectplace-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ProjectPlace を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/projectplace-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ProjectPlace の間にシングル サインオンを構成する方法について説明します。

この記事では、ProjectPlace と Microsoft Entra ID を統合する方法について説明します。 ProjectPlace を Microsoft Entra ID と統合すると、次のことができます。

- ProjectPlace にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って ProjectPlace に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。
- ユーザーを ProjectPlace に自動的にプロビジョニングできる。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ProjectPlace でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ProjectPlace では、**SP Initiated SSO と IDP Initiated SSO** のほか、**ジャスト イン タイム** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの ProjectPlace の追加

Microsoft Entra ID への ProjectPlace の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ProjectPlace を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ProjectPlace**」と入力します。
4. 結果のパネルから **[ProjectPlace]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ProjectPlace 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、ProjectPlace に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと ProjectPlace の関連ユーザーの間にリンク関係を確立する必要があります。

ProjectPlace に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ProjectPlace SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ProjectPlace テストユーザーを作成** - Microsoft Entra のユーザー表現にリンクした ProjectPlace の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ProjectPlace** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **IDP** 開始モードでアプリケーションを構成したい場合、 **[基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure で事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順に従います。

    **[サインオン URL (オプション)]** テキスト ボックスに、URL として「`https://service.projectplace.com`」と入力します。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、コピー **アイコン** を選択して要件に従って **アプリのフェデレーション メタデータ URL を**コピーし、メモ帳に保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[ProjectPlace のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ProjectPlace SSO を構成する

**ProjectPlace** 側でシングル サインオンを構成するには、コピーした**アプリのフェデレーション メタデータ URL** を [ProjectPlace サポート チーム](https://success.planview.com/Projectplace/Support)に送信する必要があります。 このチームは、SAML SSO 接続が両方の側で正しく設定されていることを確認します。

注意

シングル サインオンの構成は、[ProjectPlace サポート チーム](https://success.planview.com/Projectplace/Support)が実行する必要があります。 構成が完了すると直ちに、通知が届きます。

#### ProjectPlace のテスト ユーザーの作成

注意

ProjectPlace でプロビジョニングが有効な場合、この手順はスキップしてかまいません。 最初のログイン中に完了したユーザーが ProjectPlace に作成されたら、 [ProjectPlace サポート チーム](https://success.planview.com/Projectplace/Support) にプロビジョニングを有効にするよう依頼できます。

Microsoft Entra ユーザーが ProjectPlace にサインインできるようにするには、それらを ProjectPlace に追加する必要があります。 手動で追加する必要があります。

**ユーザー アカウントを作成するには、以下の手順に従います。**

1. **ProjectPlace** 企業サイトに管理者としてサインインします。
2. **[People](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** に移動し、**[Members](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メンバー)** を選択します。

    [Image: [People](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) に移動し、[Members](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メンバー) を選択する]
3. **[Add Member](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メンバーの追加)**を選択します。

    [Image: [Add Member](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メンバーの追加) を選択する]
4. **[Add Member](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メンバーの追加)** セクションで、次の手順に従います。

    [Image: [Add Member](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メンバーの追加) セクション]

    1. **[New Members] (新しいメンバー)** ボックスに、追加する有効な Microsoft Entra アカウントのメール アドレスを入力します。
    2. **[Send]** を選択します。

    Microsoft Entra のアカウント所有者には、そのアカウントがアクティブになる前に、アカウント確認用のリンクを含むメールが送信されます。

注意

ProjectPlace から提供されている他の任意のユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントを追加することもできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ProjectPlace のサインオン URL にリダイレクトされます。
- ProjectPlace のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ProjectPlace に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ProjectPlace] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ProjectPlace に自動的にサインインされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/prolorus-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Prolorus を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/prolorus-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Prolorus の間にシングル サインオンを構成する方法について説明します。

この記事では、Prolorus と Microsoft Entra ID を統合する方法について説明します。 Prolorus と Microsoft Entra ID を統合すると、次のことができます。

- Prolorus にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Prolorus に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Prolorus は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Prolorus でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Prolorus では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの Prolorus の追加

Microsoft Entra ID への Prolorus の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Prolorus を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Prolorus**」と入力します。
4. 結果パネルから **[Prolorus]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Prolorus 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Prolorus に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Prolorus の関連ユーザーとの間にリンク関係を確立する必要があります。

Prolorus に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Prolorus の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Prolorus の B.Simon に対応するテスト ユーザーを作成し、Microsoft Entra のユーザーにリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Prolorus**&gt;**シングルサインオン**を開きます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.prolorus.app/Login`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.prolorus.app`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.prolorus.app/SAML/AssertionConsumerService`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには、[Prolorus クライアント サポート チーム](mailto:infrastructure@prolorus.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Prolorus のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Prolorus SSO を設定する

**Prolorus** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を、[Prolorus サポート チーム](mailto:infrastructure@prolorus.com)に送信する必要があります。 送信前に最初に証明書を圧縮すると、電子メール システムによってブロックされなくなります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Prolorus のテスト ユーザーの作成

このセクションでは、Prolorus で Britta Simon というユーザーを作成します。 [Prolorus サポート チーム](mailto:infrastructure@prolorus.com)と協力して、Prolorus プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Prolorus のサインオン URL にリダイレクトされます。
- Prolorus のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Prolorus] タイルを選択すると、このオプションは Prolorus のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/promapp-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Promapp を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/promapp-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: ユーザー アカウントを Promapp に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事の目的は、Promapp に対してユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成するために Promapp と Microsoft Entra ID で実行する手順を示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Promapp テナント](https://www.promapp.com/licensing/)。
- 管理者アクセス許可がある Promapp のユーザー アカウント

### Promapp へのユーザーの割り当て

Microsoft Entra ID では、 *割り当て* と呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーとグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Promapp へのアクセスが必要な Microsoft Entra ID 内のユーザー/グループを決定しておく必要があります。 特定した後、次の手順に従い、これらのユーザー、グループ、またはその両方を Promapp に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Promapp に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを Promapp に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Promapp にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために Promapp を設定する

1. Promapp 管理コンソールにサインインします。 ユーザー名の下にある [ **マイ プロファイル**] に移動します。

    [Image: Promapp 管理コンソール]
2. [ **アクセス トークン] で** 、[ **トークンの作成** ] ボタンを選択します。

    [Image: Promapp 追加 SCIM]
3. **[説明**] フィールドに任意の名前を指定し、[**スコープ**] ドロップダウン メニューから **[SCIM**] を選択します。 保存アイコンを選択します。

    [Image: Promapp の名前の追加]
4. アクセス トークンをコピーし、表示できる唯一の時間として保存します。 この値は、Promapp アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。

    [Image: Promapp のトークンの作成]

### ギャラリーから Promapp を追加する

Microsoft Entra ID での自動ユーザー プロビジョニング用に Promapp を構成する前に、Microsoft Entra アプリケーション ギャラリーから Promapp をマネージド SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Promapp を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーから追加**] セクションに「**Promapp」**と入力し、検索ボックスで **Promapp** を選択します。
4. 結果パネルから **Promapp** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

    [Image: 結果リストにあるPromapp。]

### Promapp への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて Promapp 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Promapp のシングル サインオンに関する記事に記載されている手順に従って、Promapp の SAML ベース [のシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/promapp-tutorial)を有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Promapp の自動ユーザー プロビジョニングを構成するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で [ **Promapp**] を選択します。

    [Image: アプリケーションの一覧の Promapp リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Promapp テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Promapp に接続できることを確認します。 接続に失敗した場合は、Promapp アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    注

    `https://api.promapp.com/api/scim` に「」と入力します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Promapp に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Promapp のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Promapp API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: Promapp ユーザー属性のスクリーンショット。]
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pronovos-analytics-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ProNovos Analytics を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pronovos-analytics-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ProNovos Analytics の間でシングル サインオンを構成する方法について説明します。

この記事では、ProNovos Analytics と Microsoft Entra ID を統合する方法について説明します。 ProNovos Analytics と Microsoft Entra ID を統合すると、次のことができます。

- ProNovos Analytics にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して ProNovos Analytics に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ProNovos Analytics でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ProNovos Analytics は、**SP** と **IDP** による SSO をサポートします。
- ProNovos Analytics では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの ProNovos Analytics の追加

Microsoft Entra ID への ProNovos Analytics の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ProNovos Analytics を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「ProNovos Analytics**」と入力します。
4. 結果パネルから **ProNovos Analytics** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ProNovos Analytics の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、ProNovos Analytics に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ProNovos Analytics の関連ユーザーとの間にリンク関係を確立する必要があります。

ProNovos Analytics に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ProNovos Analytics の SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **ProNovos Analytics のテストユーザーを作成 - ProNovos Analytics で B.Simon に対応するテストユーザーを作成し、それを Microsoft Entra のユーザープロファイルにリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ProNovos Analytics** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://analytics.pronovos.com/Pronovos/servlet/mstrWeb`
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **ProNovos Analytics のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ProNovos Analytics SSO の構成

**ProNovos Analytics** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** と、アプリケーション構成からコピーした適切な URL を [ProNovos Analytics サポート チーム](mailto:support@pronovos.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ProNovos Analytics テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを ProNovos Analytics に作成します。 ProNovos Analytics では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ProNovos Analytics にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [ProNovos Analytics] タイルを選択すると、SSO を設定した ProNovos Analytics に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pronovos-ops-manager-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ProNovos Ops Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pronovos-ops-manager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ProNovos Ops Manager の間でシングル サインオンを構成する方法について説明します。

この記事では、ProNovos Ops Manager と Microsoft Entra ID を統合する方法について説明します。 ProNovos Ops Manager と Microsoft Entra ID を統合すると、次のことができます:

- ProNovos Ops Manager にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントで ProNovos Ops Manager に自動的にサインイン するように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ProNovos Ops Manager でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ProNovos Ops Manager では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーから ProNovos Ops Manager を追加する

Microsoft Entra ID への ProNovos Ops Manager の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ProNovos Ops Manager を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ProNovos Ops Manager**」と入力します。
4. 結果のパネルから **[ProNovos Ops Manager]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ProNovos Ops Manager 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ProNovos Ops Manager に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと ProNovos Ops Manager の関連ユーザーとの間にリンク関係を確立する必要があります。

ProNovos Ops Manager に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ProNovos Ops Manager SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ProNovos Ops Manager のテスト ユーザーを作成** - Microsoft Entra のユーザーである B.Simon にリンクした、ProNovos Ops Manager 内の対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ProNovos Ops Manager** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://gly.smartsubz.com/saml2/acs`
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Set up ProNovos Ops Manager](ProNovos Ops Manager のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ProNovos Ops Manager の SSO の構成

**ProNovos Ops Manager** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** とアプリケーション構成からコピーした適切な URL を [ProNovos Ops Manager サポート チーム](mailto:support@pronovos.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ProNovos Ops Manager のテスト ユーザーの作成

このセクションでは、ProNovos Ops Manager で B.Simon というユーザーを作成します。 [ProNovos Ops Manager サポート チーム](mailto:support@pronovos.com)と協力して、ProNovos Ops Manager プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ProNovos Ops Manager のサインオン URL にリダイレクトされます。
- ProNovos Ops Manager のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ProNovos Ops Manager に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ProNovos Ops Manager] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ProNovos Ops Manager に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/proofpoint-ondemand-tutorial"} -->
## Microsoft Entra ID で Proofpoint on Demand for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proofpoint-ondemand-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Proofpoint on Demand 間にシングル サインオンを構成する方法について学習します。

この記事では、Proofpoint on Demand と Microsoft Entra ID を統合する方法について説明します。 Proofpoint on Demand と Microsoft Entra ID を統合すると、次のことができます。

- Proofpoint on Demand にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Proofpoint on Demand に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Proofpoint on Demand のシングル サインオン (SSO) が有効なサブスクリプション。

注

Microsoft Entra ID で MFA またはパスワードレス認証を使用している場合は、SAML 要求の AuthnContext 値をオフにします。 それ以外の場合、Microsoft Entra ID は AuthnContext の不一致でエラーをスローし、トークンをアプリケーションに送り返しません。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Proofpoint on Demand では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの Proofpoint on Demand の追加

Microsoft Entra ID への Proofpoint on Demand の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Proofpoint on Demand を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Proofpoint on Demand**」と入力します。
4. 結果パネルで **[Proofpoint on Demand]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Proofpoint on Demand 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Proofpoint on Demand に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Proofpoint on Demand の関連ユーザーとの間にリンク関係を確立する必要があります。

Proofpoint on Demand に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Proofpoint on Demand の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Proofpoint on Demand テスト ユーザーの作成** - Microsoft EntraのB.SimonとリンクされたProofpoint on Demandの対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業アプリケーション**&gt;**Proofpoint on Demand**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://<hostname>.pphosted.com/ppssamlsp` という形式で URL を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<hostname>.pphosted.com:portnumber/v1/samlauth/samlconsumer`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<hostname>.pphosted.com/ppssamlsp_hostname`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Proofpoint on Demand クライアント サポート チーム](https://www.proofpoint.com/us/support-services)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Proofpoint on Demand のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Proofpoint on Demand の SSO の構成

**Proofpoint on Demand ** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [Proofpoint on Demand サポート チーム](https://www.proofpoint.com/us/support-services)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Proofpoint on Demand のテスト ユーザーの作成

このセクションでは、Proofpoint on Demand で Britta Simon というユーザーを作成します。 [Proofpoint on Demand サポート チーム](https://www.proofpoint.com/us/support-services)と協力して、Proofpoint on Demand プラットフォームにユーザーを追加します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Proofpoint on Demand のサインオン URL にリダイレクトされます。
- Proofpoint on Demand のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Proofpoint on Demand] タイルを選択すると、このオプションは Proofpoint on Demand のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->
