# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 8)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 79

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/easysso-for-jira-tutorial"} -->
## Microsoft Entra ID で EasySSO for Jira for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/easysso-for-jira-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EasySSO for Jira の間でシングル サインオンを構成する方法について説明します。

この記事では、EasySSO for Jira と Microsoft Entra ID を統合する方法について説明します。 EasySSO for Jira を Microsoft Entra ID と統合すると、次のことができます。

- Jira にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Jira に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EasySSO for Jira でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EasySSO for Jira では、**SPおよびIDP起動のSSO**に対応しています。
- EasySSO for Jira では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから EasySSO for Jira を追加する

EasySSO for Jira と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に EasySSO for Jira をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「EasySSO for Jira**」と入力します。
4. 結果パネルから **EasySSO for Jira** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### EasySSO for Jira 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、EasySSO for Jira に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと、EasySSO for Jira での関連ユーザーとの間にリンク関係を確立する必要があります。

EasySSO for Jira での Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EasySSO for Jira SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EasySSO for Jira のテスト ユーザーを作成する** - EasySSO for Jira で B.Simon に対応するユーザーを作成し、Microsoft Entra での当該ユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[EasySSO for Jira]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/easysso/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/easysso/saml`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/login.jsp`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値が不明な場合は、EasySSO サポート チーム  にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. EasySSO for Jira アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、EasySSO for Jira アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:0.9.2342.19200300.100.1.1 | user.userprincipalname |
    | urn:oid:0.9.2342.19200300.100.1.3 | ユーザーのメールアドレス |
    | urn:oid:2.16.840.1.113730.3.1.241 | user.displayname |
    | urn:oid:2.5.4.4 | ユーザーの名字 |
    | urn:oid:2.5.4.42 | User.givenname |

    Microsoft Entra ユーザーに **sAMAccountName** が構成されている場合は、 **urn:oid:0.9.2342.19200300.100.1.1** を **sAMAccountName** 属性にマップする必要があります。
9. [**SAML でのシングル サインオンの設定**] ページの [**SAML 署名証明書**] セクションで、[**証明書 (Base64)** または**フェデレーション メタデータ XML** オプションのリンクの**ダウンロード**] を選択し、いずれかまたはすべてをコンピューターに保存します。 Jira EasySSO を構成するには、後で必要になります。

    [Image: 証明書のダウンロード リンク]

    証明書を使用して EasySSO for Jira 構成を手動で実行する場合は、以下のセクションから **ログイン URL** と **Microsoft Entra 識別子** をコピーし、コンピューターに保存する必要もあります。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EasySSO for Jira SSO の構成

1. 管理者特権で Atlassian Jira インスタンスにサインインし、[ **アプリの管理** ] セクションに移動します。

    [Image: アプリの管理]
2. 左側で **EasySSO** を見つけて選択します。

    [Image: Easy SSO]
3. **SAML** オプションを選択します。 これにより、SAML 構成セクションが表示されます。

    [Image: SAML]
4. 上部の [ **証明書** ] タブを選択すると、次の画面が表示されます。

    [Image: メタデータ URL]
5. ここで、**Microsoft Entra SSO** 構成の前の手順で保存した**証明書 (Base64)** または**メタデータ ファイル**を見つけます。 続行する方法には、次のオプションがあります。

    a. コンピューター上のローカル **ファイル** にダウンロードしたアプリ フェデレーション メタデータ ファイルを使用します。 [ **アップロード]** ラジオ ボタンを選択し、オペレーティング システムに固有の [ファイルのアップロード] ダイアログに従います。

    **又は**

    b。 アプリのフェデレーション **メタデータ ファイル** を開き、ファイルのコンテンツ (任意のプレーン テキスト エディター) を表示し、クリップボードにコピーします。 **[入力]** オプションを選択し、クリップボードの内容をテキスト フィールドに貼り付けます。

    **又は**

    c. 完全に手動で構成します。 アプリのフェデレーション **証明書 (Base64)** を開き、ファイルの内容 (任意のプレーン テキスト エディター) を表示し、クリップボードにコピーします。 **IdP トークン署名証明書**のテキスト フィールドに貼り付けます。 次に、[ **全般** ] タブに移動し、 **POST バインディング URL** フィールドと **エンティティ ID** フィールドに、前に保存した **ログイン URL** と **Microsoft Entra 識別子** のそれぞれの値を入力します。
6. ページの下部にある **[保存]** ボタンを選択します。 メタデータ ファイルまたは証明書ファイルの内容が構成フィールドに解析されていることがわかります。 これで、EasySSO for Jira の構成は完了しました。
7. 最適なテスト エクスペリエンスを得るには、[ **ルック アンド フィール** ] タブに移動し、[ **SAML Login Button]\(SAML ログイン ボタン** \) オプションをオンにします。 これにより、Microsoft Entra SAML 統合をエンドツーエンドでテストするための、独立した専用のボタンが Jira ログイン画面で有効になります。 このボタンをオンのままにすることで、運用モードでの配置、色、および翻訳を構成することもできます。

    [Image: 外観と操作感]

    注

    問題がある場合は、EasySSO サポート チーム にお問い合わせください。

#### EasySSO for Jira テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Jira に作成します。 EasySSO for Jira では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で **無効になっています** 。 ユーザー プロビジョニングを有効にするには、EasySSO プラグイン構成の [全般] セクションで、[ **ログインに成功したときにユーザーを作成** する] オプションを明示的にオンにする必要があります。 Jira にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

ただし、ユーザーの最初のログインで自動ユーザー プロビジョニングを有効にしない場合は、Jira インスタンスが使用するバックエンド ユーザー ディレクトリ (LDAP や Atlassian Crowd など) にユーザーが存在している必要があります。

[Image: ユーザー プロビジョニング]

### SSO のテスト

#### IdP によって開始されるワークフロー

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した EasySSO for Jira に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [EasySSO for Jira] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した EasySSO for Jira に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。

#### SP によって開始されるワークフロー

このセクションでは、Jira **SAML Login** ボタンを使用して Microsoft Entra のシングル サインオン構成をテストします。

[Image: ユーザー SAML ログイン]

このシナリオでは、Jira EasySSO 構成ページの [**ルック アンド フィール**] タブで **SAML ログイン ボタン**が有効になっていると仮定します (上記を参照)。 既存のセッションとの干渉を避けるために、シークレット モードにしたブラウザーで Jira のログイン URL を開きます。 [ **SAML ログイン** ] ボタンを選択すると、Microsoft Entra ユーザー認証フローにリダイレクトされます。 正常に完了すると、SAML 経由で認証されたユーザーとして Jira インスタンスにリダイレクトされます。

Microsoft Entra ID からのリダイレクト後に、次の画面が表示される可能性があります。

[Image: EasySSO エラー画面]

この場合、 [このページの指示に](https://techtime.co.nz/display/TECHTIME/EasySSO+How+to+get+the+logs#EasySSOHowtogetthelogs-RETRIEVINGTHELOGS) 従って、 **atlassian-jira.log** ファイルにアクセスする必要があります。 エラーの詳細は、EasySSO エラー ページで見つかった参照 ID で確認できます。

ログ メッセージのダイジェストに問題がある場合は、EasySSO サポート チーム にお問い合わせください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/easyterritory-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に EasyTerritory を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/easyterritory-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EasyTerritory 間のシングル サインオンを構成する方法について説明します。

この記事では、EasyTerritory と Microsoft Entra ID を統合する方法について説明します。 EasyTerritory と Microsoft Entra ID の統合には、次の利点があります。

- EasyTerritory にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが Microsoft Entra アカウントで EasyTerritory に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EasyTerritory でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- EasyTerritory では、**SP** および **IDP** によって開始される SSO がサポートされます

### ギャラリーからの EasyTerritory の追加

Microsoft Entra ID への EasyTerritory の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に EasyTerritory を追加する必要があります。

**ギャラリーから EasyTerritory を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **EasyTerritory**」と入力し、結果パネルで **EasyTerritory** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の EasyTerritory]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、EasyTerritory で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと EasyTerritory の関連ユーザー間にリンク関係を確立する必要があります。

EasyTerritory に対する Microsoft Entra シングル サインオンを構成・テストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **EasyTerritory シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **EasyTerritory のテストユーザーを作成する - EasyTerritory において、Britta Simon に対応するユーザーを Microsoft Entra のユーザー表示にリンクさせる**
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

EasyTerritory に対する Microsoft Entra シングル サインオンを構成するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**EasyTerritory** アプリケーション統合ページを参照し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [Image: [識別子]、[Reply U R L]、[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存) ボタンが強調表示されている [Basic S A M L Configuration](基本的な S A M L 構成) を示すスクリーンショット。]

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://apps.easyterritory.com/<tenant id>/dev/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://apps.easyterritory.com/<tenant id>/dev/authservices/acs`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [Image: EasyTerritoryドメインおよびURLのシングルサインオン情報]

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.easyterritory.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、EasyTerritory クライアント サポート チーム](mailto:sales@easyterritory.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **EasyTerritory のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### EasyTerritory シングル サインオンの構成

**EasyTerritory** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [EasyTerritory サポート チーム](mailto:sales@easyterritory.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### EasyTerritory のテスト ユーザーの作成

このセクションでは、EasyTerritory で Britta Simon というユーザーを作成します。 [EasyTerritory サポート チーム](mailto:sales@easyterritory.com)と協力して、EasyTerritory プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [EasyTerritory] タイルを選択すると、SSO を設定した EasyTerritory に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ebsco-tutorial"} -->
## Microsoft Entra ID で EBSCO for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ebsco-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EBSCO の間のシングル サインオンを構成する方法について説明します。

この記事では、EBSCO と Microsoft Entra ID を統合する方法について説明します。 EBSCO を Microsoft Entra ID と統合すると、次のことが可能になります。

- EBSCO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して EBSCO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EBSCO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EBSCO では、**SP および IDP** Initiated SSO がサポートされます。
- EBSCO では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの EBSCO の追加

Microsoft Entra ID への EBSCO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に EBSCO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「EBSCO**」と入力します。
4. 結果パネルから **EBSCO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### EBSCO に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、EBSCO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、EBSCO での関連ユーザーとの間にリンク関係を確立する必要があります。

EBSCO に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EBSCO SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EBSCO のテスト ユーザーの作成** - EBSCO で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**EBSCO**&gt;**シングルサインオン**を閲覧します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **識別子** ] テキスト ボックスに、URL を入力します。 `pingsso.ebscohost.com`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `http://search.ebscohost.com/login.aspx?authtype=sso&custid=<unique EBSCO customer ID>&profile=<profile ID>`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 これらの値を取得するには、 [EBSCO クライアント サポート チーム](mailto:support@ebsco.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。

    o **固有の要素:**

    o **Custid** = 一意の EBSCO 顧客 ID を入力します

    o **プロファイル** = クライアントは、(EBSCO から購入した内容に応じて) ユーザーを特定のプロファイルに誘導するようにリンクを調整できます。 特定のプロファイル ID を入力できます。 メインの ID は eds (EBSCO Discovery Service) と ehost (EBSOCOhost データベース) です。 同じ手順については、EBSCOhost のドキュメントを参照してください。
7. EBSCO アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]

    注

    **name** 属性は必須であり、EBSCO アプリケーションの**名前識別子の値**にマップされます。 これは既定で追加されるため、手動で追加する必要はありません。
8. その他に、EBSCO アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの名字 |
    | Email | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **EBSCO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EBSCO SSO の構成

**EBSCO** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [EBSCO サポート チーム](mailto:support@ebsco.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EBSCO のテスト ユーザーの作成

EBSCO の場合、ユーザー プロビジョニングは自動的に行われます。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

Microsoft Entra ID は、必要なデータを EBSCO アプリケーションに渡します。 EBSCO のユーザー プロビジョニングは自動であるか、1 回限りのフォームが要求されます。 これは、個人設定が保存された、多数の既存の EBSCOhost アカウントをクライアントが持っているかどうかによって異なります。 実装中は [EBSCO サポート チーム](mailto:support@ebsco.com) とその点について議論できます。 どちらの場合でも、テスト前にクライアントが EBSCOhost アカウントを作成する必要はありません。

注

EBSCO ホスト ユーザー プロビジョニングおよびパーソナル化を自動化できます。 Just-In-Time ユーザー プロビジョニングについては、 [EBSCO サポート チーム](mailto:support@ebsco.com) にお問い合わせください。

### SSO のテスト

このセクションでは、マイ アプリを使用して Microsoft Entra のシングル サインオン構成をテストします。

1. マイ アプリで [EBSCO] タイルを選択すると、EBSCO アプリケーションに自動的にサインオンします。 マイ アプリの詳細については、「マイ アプリ [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
2. アプリケーションにログインしたら、右上隅にある **サインイン** ボタンを選択します。

    [Image: EBSCOのサインイン（アプリケーション一覧）]
3. 機関/SAML ログインと**既存の MyEBSCOhost アカウントをインスティテューション アカウントにリンクするか、新しい MyEBSCOhost アカウントを** **作成して教育機関アカウントにリンク**するように求める 1 回限りのプロンプトが表示されます。 アカウントは、EBSCOhost アプリケーションのパーソナル化に使用されます。 [ **新しいアカウントの作成** ] オプションを選択すると、次のスクリーンショットに示すように、個人用設定用のフォームが SAML 応答の値で事前に完了していることがわかります。 **[続行] を**選択して、この選択を保存します。

    [Image: アプリケーションの一覧の EBSCO ユーザー]
4. 以上の設定を完了したら、Cookie/キャッシュをクリアして再びログインします。 これで再び手動でサインインする必要はなくなり、パーソナル化の設定は保持されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/eccentex-appbase-for-azure-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Eccentex AppBase for Azure を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/eccentex-appbase-for-azure-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Eccentex AppBase for Azure の間でシングル サインオンを構成する方法について説明します。

この記事では、Eccentex AppBase for Azure と Microsoft Entra ID を統合する方法について説明します。 Eccentex AppBase for Azure と Microsoft Entra ID を統合すると、次のことが可能になります。

- Eccentex AppBase for Azure にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Eccentex AppBase for Azure に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Eccentex AppBase for Azure でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Eccentex AppBase for Azure では、 **SP** によって開始される SSO がサポートされます。
- Eccentex AppBase for Azure では、 **Just In Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Eccentex AppBase for Azure を追加する

Eccentex AppBase for Azure と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Eccentex AppBase for Azure をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに「**Eccentex AppBase for Azure**」と入力します。
4. 結果パネルから **Eccentex AppBase for Azure** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Eccentex AppBase for Azure 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Eccentex AppBase for Azure に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと、Eccentex AppBase for Azure での関連ユーザーとの間にリンク関係を確立する必要があります。

Eccentex AppBase for Azure 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Eccentex AppBase for Azure SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Eccentex AppBase for Azure のテスト ユーザーを作成** - Eccentex AppBase for Azure で B.Simonのカウンターパートとなるユーザーを作成し、それをMicrosoft Entraのユーザーにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップを実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Eccentex AppBase for Azure]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<CustomerName>.appbase.com/Ecx.Web` |
    | `https://<CustomerName>.eccentex.com:<PortNumber>/Ecx.Web` |

    b。 [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CustomerName>.appbase.com/Ecx.Web/Account/sso?tenantCode=<TenantCode>&authCode=<AuthConfigurationCode>` |
    | `https://<CustomerName>.eccentex.com:<PortNumber>/Ecx.Web/Account/sso?tenantCode=<TenantCode>&authCode=<AuthConfigurationCode>` |

    Note

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、Eccentex AppBase for Azure クライアント サポート チーム](mailto:eccentex.support@eccentex.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Eccentex AppBase for Azure のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Eccentex AppBase for Azure の SSO の構成

1. Eccentex AppBase for Azure 企業サイトに管理者としてログインします。
2. **歯車**アイコンに移動し、[**ユーザーの管理**] を選択します。

    [Image: SAML アカウントの設定を示すスクリーンショット。]
3. **[ユーザー管理**&gt;**認証構成]** に移動し、[SAML の**追加]** ボタンを選択します。
4. [ **新しい SAML 構成]** ページで、次の手順を実行します。

    [Image: Azure SAML 構成を示すスクリーンショット。]

    1. [ **名前** ] ボックスに、短い構成名を入力します。
    2. [ **発行者 URL]** ボックスに、前にコピーした Azure **アプリケーション ID を** 入力します。
    3. **アプリケーション URL** の値をコピーし、この値を [**基本的な SAML 構成**] セクションの **[識別子 (エンティティ ID)]** テキスト ボックスに貼り付けます。
    4. **AppBase の [新しいユーザーのオンボード**] で、ドロップダウンから **[招待のみ**] を選択します。
    5. **AppBase 認証エラーの動作で**、ドロップダウンから **[エラー ページの表示**] を選択します。
    6. 証明書の暗号化に従って、[ **Signature Digest Method]\(署名ダイジェストメソッド** \) と **[Signature Method]\(署名方法** \) を選択します。
    7. [ **証明書の使用**] で、ドロップダウンから **[手動アップロード** ] を選択します。
    8. **[認証コンテキスト クラス名]** で、ドロップダウンから **[パスワード**] を選択します。
    9. **サービス プロバイダーから ID プロバイダーへのバインド**で、ドロップダウンから **[HTTP リダイレクト**] を選択します。

        Note

        **[送信要求に署名**する] がオンになっていないことを確認します。
    10. **Assertion Consumer Service の URL 値を**コピーし、この値を [**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスに貼り付けます。
    11. [ **認証要求の宛先 URL]** ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。
    12. **[サービス プロバイダー リソース URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。
    13. [ **Artifact Identification URL]\(アーティファクト ID URL** \) ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。
    14. **[Auth Request Protocol Binding]\(認証要求プロトコル バインド**\) で、ドロップダウンから **HTTP-POST** を選択します。
    15. [ **認証要求名 ID ポリシー**] で、ドロップダウンから **[永続的]** を選択します。
    16. [ **Artifact Responder URL]\(アーティファクト レスポンダー URL** \) ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。
    17. [ **応答署名の検証の適用** ] チェック ボックスをオンにします。
    18. ダウンロードした **証明書 (未加工)** をメモ帳に開き、[ **SAML 相互証明書のアップロード** ] テキストボックスに内容を貼り付けます。
    19. **ログアウト応答プロトコル バインド**で、ドロップダウンから **HTTP-POST** を選択します。
    20. **[AppBase Custom Logout URL]\(AppBase カスタム ログアウト URL**\) ボックスに、前にコピーした**ログアウト URL** 値を貼り付けます。
    21. **[保存] を選択します**。

#### Eccentex AppBase for Azure のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Eccentex AppBase for Azure に作成します。 Eccentex AppBase for Azure では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Eccentex AppBase for Azure にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Eccentex AppBase for Azure のサインオン URL にリダイレクトされます。
- Eccentex AppBase for Azure のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Eccentex AppBase for Azure] タイルを選択すると、このオプションは Eccentex AppBase for Azure のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ecfreight2-tutorial"} -->
## Microsoft Entra ID で ecFreight2 for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ecfreight2-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-16
- Summary: Microsoft Entra と ecFreight2 の間にシングル サインオンを構成する方法について説明します。

この記事では、ecFreight2 と Microsoft Entra ID を統合する方法について説明します。 ecFreight2 を Microsoft Entra ID と統合すると、次のことができます。

Microsoft Entra ID を使用して、ecFreight2 にアクセスできるユーザーを制御する。 ユーザーが自分の Microsoft Entra アカウントを使用して ecFreight2 に自動的にサインインできるようにする。 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ecFreight2 でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから ecFreight2 を追加する

Microsoft Entra ID への ecFreight2 の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに ecFreight2 を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「ecFreight2**」と入力します。
4. 結果パネルで **ecFreight2** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ecFreight2**&gt;**シングルサインオン**に移動します。
3. 次のセクションで以下の手順を実行します。

    1. [ **アプリケーションに移動] を**選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID を**コピーし、後で ecFreight2 側の構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
    3. [ **エンドポイント** ] タブで、 **OpenID Connect メタデータ ドキュメント** リンクをコピーし、後で ecFreight2 側の構成で使用します。

        [Image: タブにエンドポイントが表示されているスクリーンショット。]
4. 左側のメニューの [ **認証** ] タブに移動し、次の手順を実行します。

    1. [ **リダイレクト URI** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<HOST_NAME>`

        [Image: リダイレクト値を示すスクリーンショット。]
    2. [ **構成] ボタンを** 選択します。
5. 左側のメニューの **[証明書とシークレット** ] に移動し、次の手順を実行します。

    1. [ **クライアント シークレット** ] タブに移動し、[ **+新しいクライアント シークレット**] を選択します。
    2. テキストボックスに有効な **説明** を入力し、要件に従ってドロップダウンから **[有効期限** 日] を選択し、[ **追加**] を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、 **値** が生成されます。 この値をコピーして、後で ecFreight2 側の構成で使用します。

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
5. **[作成] を選択します**。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に ecFreight2 へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**ecFreight2** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### ecFreight2 SSO を構成する

1. ADP アプリケーションに管理者としてログインします。
2. **Admin**&gt;**System Parameter**&gt;**Single Sign On** に移動し、次の手順を実行します。

    [Image: 構成のアカウント設定を示すスクリーンショット。]

    1. **[有効**] チェック ボックスをオンにします。
    2. [ **探索ドキュメント URI** ] ボックスに、Microsoft Entra 管理センターからコピーした **OpenID Connect メタデータ ドキュメント** リンクを貼り付けます。
    3. [ **クライアント ID** ] ボックスに、Microsoft Entra 管理センターからコピーした **アプリケーション (クライアント) ID を**貼り付けます。

注

詳細については、 [ecFreight2 サポート チーム](mailto:support@brio.com.hk)にお問い合わせください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/echospan-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に EchoSpan を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/echospan-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EchoSpan の間のシングル サインオンを構成する方法について説明します。

この記事では、EchoSpan と Microsoft Entra ID を統合する方法について説明します。 EchoSpan を Microsoft Entra ID と統合すると、次のことが可能になります。

- EchoSpan にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで EchoSpan に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な EchoSpan のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EchoSpan では、**SP および IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの EchoSpan の追加

Microsoft Entra ID への EchoSpan の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に EchoSpan を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「EchoSpan**」と入力します。
4. 結果パネルから **EchoSpan** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### EchoSpan 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、EchoSpan に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、EchoSpan での関連ユーザーとの間にリンク関係を確立する必要があります。

EchoSpan を使って Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EchoSpan SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EchoSpan テスト ユーザーを作成** - EchoSpan 内で B.Simon の相当するユーザーを作成し、そのユーザーを Microsoft Entra における B.Simon の表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID] **&gt;** [エンタープライズ アプリ] **&gt;** [EchoSpan] **&gt;** [シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. [ **基本的な SAML 構成]** セクションで、 **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] ボックスに、値を入力します。 `EchoSpanServiceProvider`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://ssoeasy.echospan.com/easyconnect/ACS/Post.aspx`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://ssoeasy.echospan.com/easyconnect/ARS/SOAP.aspx`
7. **[保存] を選択します**。
8. EchoSpan アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. 上記に加えて、EchoSpan アプリケーションでは、次に示すいくつかの属性が SAML 応答で返されることが想定されています。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | clientid | 静的 |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. [ **EchoSpan のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EchoSpan SSO の構成

**EchoSpan** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [EchoSpan サポート チーム](mailto:support@echospan.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EchoSpan のテスト ユーザーの作成

このセクションでは、EchoSpan で Britta Simon というユーザーを作成します。 [EchoSpan サポート チーム](mailto:support@echospan.com)と協力して、EchoSpan プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる EchoSpan サインオン URL にリダイレクトされます。
- EchoSpan のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した EchoSpan に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [EchoSpan] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した EchoSpan に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ecoonline-info-tutorial"} -->
## Microsoft Entra ID で EcoOnline Info Exchange for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ecoonline-info-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EcoOnline Info Exchange の間でシングル サインオンを構成する方法について説明します。

この記事では、EcoOnline Info Exchange と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と EcoOnline Info Exchange を統合すると、次のことができます。

- EcoOnline Info Exchange にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して EcoOnline Info Exchange に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EcoOnline Info Exchange でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

EcoOnline Info Exchange では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーから EcoOnline Info Exchange を追加する

Microsoft Entra ID への EcoOnline Info Exchange の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に EcoOnline Info Exchange を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックス **に「EcoOnline Info Exchange** 」と入力します。
4. 結果パネルから **EcoOnline Info Exchange** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### EcoOnline Info Exchange の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、EcoOnline Info Exchange に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと EcoOnline Info Exchange の関連ユーザーとの間にリンク関係を確立する必要があります。

EcoOnline Info Exchange に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EcoOnline Info Exchange の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **EcoOnline Info Exchange のテストユーザーを作成** - Microsoft Entra 上のユーザーとリンクするために、 EcoOnline Info Exchange で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**EcoOnline Info Exchange**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] ページのスクリーンショット。]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    1. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.info-exchange.com`
    2. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.info-exchange.com/Auth/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、EcoOnline Info Exchange クライアント サポート チーム](mailto:infoexchange.helpdesk@ecoonline.com) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
7. [ **EcoOnline Info Exchange のセットアップ** ] セクションで、要件に従って 1 つ以上の適切な URL をコピーします。

    [Image: [構成 URL のコピー] のスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EcoOnline Info Exchange SSO の構成

**EcoOnline Info Exchange** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [EcoOnline Info Exchange サポート チーム](mailto:infoexchange.helpdesk@ecoonline.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EcoOnline Info Exchange テスト ユーザーの作成

このセクションでは、EcoOnline Info Exchange で Britta Simon というユーザーを作成します。 [EcoOnline Info Exchange サポート チーム](mailto:infoexchange.helpdesk@ecoonline.com)と協力して、EcoOnline Info Exchange プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した EcoOnline Info Exchange に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [EcoOnline Info Exchange] タイルを選択すると、SSO を設定した EcoOnline Info Exchange に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ecornell-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に eCornell を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ecornell-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と eCornell 間のシングル サインオンを構成する方法について説明します。

この記事では、eCornell と Microsoft Entra ID を統合する方法について説明します。 eCornell を Microsoft Entra ID と統合すると、次のことが可能になります。

- eCornell にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで eCornell に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- eCornell でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- eCornell では、**SP** initiated SSO がサポートされます
- eCornell では、**Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの eCornell の追加

Microsoft Entra ID への eCornell の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に eCornell を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**eCornell**」と入力します。
4. 結果パネルで **[eCornell]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### eCornell に Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、eCornell に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、eCornell での関連ユーザーとの間にリンク関係を確立する必要があります。

eCornell との Microsoft Entra SSO を構成・テストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **eCornell SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **eCornell テストユーザーの作成** - B.Simon に対応するユーザーを eCornell で作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**eCornell**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://admin.ecornell.com/sso/clp/<groupCode>`

    b。 **[識別子]** ボックスに、`http://pingone.com/<eCornellCustomGUID>` という形式で URL を入力します。

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sso.connect.pingidentity.com/sso/sp/ACS.saml2?saasid=<CustomGUID>`

    注

    これらの値は実際の値ではありません。 実際の Sign-On URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには、[eCornell クライアント サポート チーム](mailto:jschichor@ecornell.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. eCornell アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、eCornell アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
    | SAML\_SUBJECT | user.userprincipalname |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[eCornell のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### eCornell SSO の構成

**eCornell** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [eCornell サポート チーム](mailto:jschichor@ecornell.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### eCornell のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを eCornell に作成します。 eCornell では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 eCornell にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [eCornell] タイルを選択すると、SSO を設定した eCornell に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/edcor-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Edcor を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/edcor-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Edcor の間にシングル サインオンを構成する方法について説明します。

この記事では、Edcor と Microsoft Entra ID を統合する方法について説明します。 Edcor と Microsoft Entra ID を統合すると、次のことができます。

- Edcor にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Edcor に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Edcor でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Edcor では、**IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Edcor の追加

Microsoft Entra ID への Edcor の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Edcor を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Edcor**」と入力します。
4. 結果パネルから **[Edcor]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Edcor 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Edcor に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Edcor の関連ユーザーの間にリンク関係を確立する必要があります。

Edcor に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Edcor SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Edcor テスト ユーザーの作成** - Edcor 内で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Edcor** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて**、シングル サインオン**を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://sso.edcor.com/sp/ACS.saml2`
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Edcor のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成用URLをコピーするスクリーンショットです。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Edcor SSO の構成

**Edcor** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Edcor サポート チーム](https://www.edcor.com/contact-us/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Edcor テスト ユーザーの作成

このセクションでは、Edcor で Britta Simon というユーザーを作成します。 [Edcor サポート チーム](https://www.edcor.com/contact-us/)と連携し、Edcor プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Edcor に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Edcor] タイルを選択すると、SSO を設定した Edcor に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/edigitalresearch-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に eDigitalResearch を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/edigitalresearch-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と eDigitalResearch の間でシングル サインオンを構成する方法について説明します。

この記事では、eDigitalResearch と Microsoft Entra ID を統合する方法について説明します。 eDigitalResearch と Microsoft Entra ID の統合には、次の利点があります。

- eDigitalResearch にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して eDigitalResearch に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- eDigitalResearch でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- eDigitalResearch では、 **IDP** Initiated SSO がサポートされます

### ギャラリーからの eDigitalResearch の追加

Microsoft Entra ID への eDigitalResearch の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に eDigitalResearch を追加する必要があります。

**ギャラリーから eDigitalResearch を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **eDigitalResearch**」と入力し、結果パネルで **eDigitalResearch** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: eDigitalResearchの結果一覧]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、eDigitalResearch で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと、eDigitalResearch での関連ユーザーとの間にリンク関係が確立されている必要があります。

eDigitalResearch で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を満たす必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **eDigitalResearch シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **eDigitalResearchのテストユーザーを作成** - eDigitalResearchでBritta Simonに相当するユーザーを作成し、そのユーザーをMicrosoft Entraのユーザーの表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

eDigitalResearch で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**eDigitalResearch** アプリケーション統合ページを参照し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    [Image: eDigitalResearch ドメインとURLのシングルサインオン情報]

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-name>.edigitalresearch.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-name>.edigitalresearch.com/login/consume`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [eDigitalResearch クライアント サポート チーム](https://www.maruedr.com/contact) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **eDigitalResearch のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### eDigitalResearch のシングル サインオンの構成

**eDigitalResearch** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [eDigitalResearch サポート チーム](https://www.maruedr.com/contact)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### eDigitalResearch のテスト ユーザーの作成

このセクションでは、eDigitalResearch で Britta Simon というユーザーを作成します。 [eDigitalResearch サポート チーム](https://www.maruedr.com/contact)と協力して、eDigitalResearch プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

注

Microsoft Entra アカウント所有者が電子メールを受信し、リンクに従ってアカウントを確認すると、そのアカウントがアクティブになります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [eDigitalResearch] タイルを選択すると、SSO を設定した eDigitalResearch に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ediwin-saas-edi-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Ediwin SaaS EDI を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ediwin-saas-edi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Ediwin SaaS EDI の間でシングル サインオンを構成する方法について説明します。

この記事では、Ediwin SaaS EDI と Microsoft Entra ID を統合する方法について説明します。 Ediwin SaaS EDI を Microsoft Entra ID と統合すると、次のことが可能になります。

- Ediwin SaaS EDI にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Ediwin SaaS EDI に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Ediwin SaaS EDI のシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Ediwin SaaS EDI では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Ediwin SaaS EDI の追加

Microsoft Entra ID への Ediwin SaaS EDI の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Ediwin SaaS EDI を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Ediwin SaaS EDI**」と入力します。
4. 結果パネルから **[Ediwin SaaS EDI]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Ediwin SaaS EDI 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Ediwin SaaS EDI に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Ediwin SaaS EDI の関連ユーザーとの間にリンク関係を確立する必要があります。

Ediwin SaaS EDI 向けに Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Ediwin SaaS EDI SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Ediwin SaaS EDI テストユーザーを作成する** - Ediwin SaaS EDI で、Microsoft Entra のユーザー代表にリンクされた B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Ediwin SaaS EDI]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://web.sedeb2b.com/<EdiwinDomain>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://web.sedeb2b.com/Ediwin/samlLogin/<EdiwinDomain>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://web.sedeb2b.com/Ediwin/samlLogin/<EdiwinDomain>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Ediwin SaaS EDI サポート チーム](mailto:cau@edicomgroup.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Ediwin SaaS EDI のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Ediwin SaaS EDI SSO の構成

**Ediwin SaaS EDI** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Ediwin SaaS EDI サポート チーム](mailto:cau@edicomgroup.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Ediwin SaaS EDI テスト ユーザーの作成

このセクションでは、Ediwin SaaS EDI で Britta Simon というユーザーを作成します。 [Ediwin SaaS EDI サポート チーム](mailto:cau@edicomgroup.com)と連携して、Ediwin SaaS EDI プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Ediwin SaaS EDI サインオン URL にリダイレクトされます。
- Ediwin SaaS EDI のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Ediwin SaaS EDI] タイルを選択すると、このオプションは Ediwin SaaS EDI のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/edubrite-lms-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に EduBrite LMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/edubrite-lms-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EduBrite LMS の間のシングル サインオンを構成する方法について説明します。

この記事では、EduBrite LMS と Microsoft Entra ID を統合する方法について説明します。 EduBrite LMS を Microsoft Entra ID と統合すると、次のことが可能になります。

- EduBrite LMS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して EduBrite LMS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EduBrite LMS でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- EduBrite LMS では、**SP と IDP** によって開始される SSO がサポートされます。
- EduBrite LMS では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの EduBrite LMS の追加

Microsoft Entra ID への EduBrite LMS の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に EduBrite LMS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**EduBrite LMS**」と入力します。
4. 結果パネルで **[EduBrite LMS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### EduBrite LMS に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、EduBrite LMS 用に Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと EduBrite LMS の関連ユーザー間にリンク関係を確立する必要があります。

EduBrite LMS に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EduBrite LMS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EduBrite LMS テスト ユーザーの作成** - Microsoft Entra における B.Simon のユーザー表現とリンクされた EduBrite LMS 上の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[EduBrite LMS]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer-specific>.edubrite.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer-specific>.edubrite.com/oltpublish/site/samlLoginResponse.do`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer-specific>.edubrite.com/oltpublish/site/samlLoginResponse.do`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[EduBrite LMS クライアント サポート チーム](mailto:support@edubrite.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[EduBrite LMS のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EduBrite LMS SSO の構成

**EduBrite LMS** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [EduBrite LMS サポート チーム](mailto:support@edubrite.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EduBrite LMS のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを EduBrite LMS に作成します。 EduBrite LMS では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 EduBrite LMS にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる EduBrite LMS サインオン URL にリダイレクトされます。
- EduBrite LMS のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した EduBrite LMS に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [EduBrite LMS] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した EduBrite LMS に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/edx-for-business-saml-integration-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に edX for Business SAML Integration を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/edx-for-business-saml-integration-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と edX for Business SAML Integration の間のシングル サインオンを構成する方法について説明します。

この記事では、edX for Business SAML Integration と Microsoft Entra ID を統合する方法について説明します。 edX for Business SAML Integration と Microsoft Entra ID を統合すると、次のことができます。

- edX for Business SAML Integration にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って edX for Business SAML Integration に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- edX for Business SAML Integration でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- edX for Business SAML Integration では、**SP** Initiated SSO がサポートされます。
- edX for Business SAML Integration では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから edX for Business SAML Integration を追加する

Microsoft Entra ID への edX for Business SAML Integration の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に edX for Business SAML Integration を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**edX for Business SAML Integration**」と入力します。
4. 結果のパネルから **[edX for Business SAML Integration]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### edX for Business SAML Integration 用に Microsoft Entra ID SSO を構成してテストする

**B.Simon** というテスト ユーザーを使ってて、edX for Business SAML Integration に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと edX for Business SAML Integration の関連ユーザーとの間にリンク関係を確立する必要があります。

edX for Business SAML Integration に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **edX for Business SAML Integration SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **edX for Business SAML Integration テスト ユーザーの作成** - edX for Business SAML Integration で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**edX for Business SAML Integration**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://courses.edx.org/dashboard?tpa_hint=<INSTANCE_NAME>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[edX for Business SAML Integration クライアント サポート チーム](mailto:api-support@edx.org)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. edX for Business SAML Integration アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、edX for Business SAML Integration アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 国 | ユーザーの国 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### edX for Business SAML Integration SSO の構成

**edX for Business SAML Integration** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [edX for Business SAML Integration サポート チーム](mailto:api-support@edx.org)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### edX for Business SAML Integration のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを edX for Business SAML Integration に作成します。 edX for Business SAML Integration では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 edX for Business SAML Integration にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる edX for Business SAML Integration のサインオン URL にリダイレクトされます。
- edX for Business SAML Integration のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [edX for Business SAML Integration] タイルを選択すると、このオプションは edX for Business SAML Integration のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/efidigitalstorefront-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に EFI Digital StoreFront を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/efidigitalstorefront-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EFI Digital StoreFront の間のシングル サインオンを構成する方法について説明します。

この記事では、EFI Digital StoreFront と Microsoft Entra ID を統合する方法について説明します。 EFI Digital StoreFront を Microsoft Entra ID と統合すると、以下のことが可能になります。

- EFI Digital StoreFront にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して EFI Digital StoreFront に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EFI Digital StoreFront のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- EFI Digital StoreFront では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの EFI Digital StoreFront の追加

Microsoft Entra ID への EFI Digital StoreFront の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に EFI Digital StoreFront を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「EFI Digital StoreFront**」と入力します。
4. 結果パネルから **EFI Digital StoreFront** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### EFI Digital StoreFront に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、EFI Digital StoreFront に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと EFI Digital StoreFront の関連ユーザーとの間にリンク関係を確立する必要があります。

EFI Digital StoreFront に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EFI Digital StoreFront の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EFI Digital StoreFront テストユーザーの作成** - EFI Digital StoreFront で、Microsoft Entra 上のユーザー B.Simon にリンクする対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[EFI Digital StoreFront]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.myprintdesk.net/DSF`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.myprintdesk.net/DSF/asp4/`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [EFI Digital StoreFront クライアント サポート チーム](https://www.efi.com/support-and-downloads/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **EFI Digital StoreFront のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EFI Digital StoreFront の SSO の構成

**EFI Digital StoreFront** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [EFI Digital StoreFront クライアント サポート チーム](https://www.efi.com/support-and-downloads/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EFI Digital StoreFront のテスト ユーザーの作成

このセクションでは、EFI Digital StoreFront で Britta Simon というユーザーを作成します。 [EFI Digital StoreFront サポート チーム](https://www.efi.com/support-and-downloads/)と協力して、EFI Digital StoreFront プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる EFI Digital StoreFront のサインオン URL にリダイレクトされます。
- EFI Digital StoreFront のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [EFI Digital StoreFront] タイルを選択すると、このオプションは EFI Digital StoreFront のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/eflok-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に eFlok を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/eflok-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と eFlok の間でシングル サインオンを構成する方法について説明します。

この記事では、eFlok と Microsoft Entra ID を統合する方法について説明します。 eFlok と Microsoft Entra ID を統合すると、次のことができます。

- eFlok にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して eFlok に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- eFlok でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- eFlok では、 **IDP** によって開始される SSO のみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから eFlok を追加する

Microsoft Entra ID への eFlok の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に eFlok を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「eFlok**」と入力します。
4. 結果のパネルから **eFlok** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### eFlok の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、eFlok に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと eFlok の関連ユーザーとの間にリンク関係を確立する必要があります。

eFlok に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **eFlok SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **eFlok テスト ユーザーの作成** - B.Simon に対応する eFlok のユーザーを作成し、Microsoft Entra のユーザーとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**eFlok**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリは既に Microsoft Entra と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **eFlok のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### eFlok SSO の構成

1. eFlok 企業サイトに管理者としてログインします。
2. **プロファイル設定**&gt;**Enterprise Integrations** に移動し、次の手順を実行します。

    [Image: [構成] を示すスクリーンショット。]

    1. **[SAML サインイン URL**] テキスト ボックスに、Microsoft Entra 管理センターからコピーした**ログイン URL** の値を貼り付けます。
    2. ダウンロードした **証明書 (Raw)** をメモ帳に開き、その内容を **[キー x509 証明書** ] ボックスに貼り付けます。
    3. [ **送信] を選択します**。

#### eFlok テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、管理者として eFlok Web サイトにサインインします。
2. **[プロファイル設定**]&gt;**[組織ユーザー**]に移動し、[**組織のメンバーを招待]**を選択します。

    [Image: スクリーンショットは、新しいユーザーを追加する方法を示しています。]
3. 次のページで以下の手順を実行します。

    [Image: ユーザー情報を入力する [新しいユーザー] セクションを示すスクリーンショット。]

    1. [ **電子メール** ] ボックスに、ユーザーの有効な emailaddress を入力します。
    2. 要件に従って、ドロップダウンから **[部分アクセス** ] または [ **フル アクセス** ] を選択します。
    3. [ **招待の送信]** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した eFlok に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [eFlok] タイルを選択すると、SSO を設定した eFlok に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/egnyte-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Egnyte を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/egnyte-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: ユーザー アカウントをMicrosoft Entra IDから Egnyte に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Egnyte と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra ID が構成されていると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーが[Egnyte](http://www.egnyte.com/)に自動的にプロビジョニングおよび解除されます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Egnyte でユーザーを作成します。
- アクセスが不要になったら、Egnyte のユーザーを削除します。
- Microsoft Entra IDと Egnyte の間でユーザー属性の同期を維持します。
- Egnyte に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/egnyte-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Egnyte のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- Microsoft Entra IDとEgnyteの間でマッピングするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Egnyte を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように Egnyte を構成するには、Egnyte サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Egnyte を追加する

Microsoft Entra アプリケーション ギャラリーから Egnyte を追加して、Egnyte へのプロビジョニングの管理を開始します。 以前に SSO 用に Egnyte を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Egnyte への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて Egnyte でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Egnyte の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Egnyte** を選択します。

    [Image: アプリケーションの一覧の Egnyte リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Egnyte テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Egnyte に接続できることを確認します。 接続に失敗した場合は、Egnyte アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Egnyte に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Egnyte のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Egnyte API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Egnyteに求められる必須項目 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | エクスターナルID | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | 名前.名 | 糸 |  |  |
    | 名前.姓 | 糸 |  |  |
    | ユーザータイプ | 糸 |  |  |
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから Egnyte に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Egnyte のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Egnyteに求められる必須項目 |
    | --- | --- | --- | --- |
    | ディスプレイ名 | 糸 | ✓ | ✓ |
    | メンバー | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/egnyte-tutorial"} -->
## Microsoft Entra ID で Egnyte for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/egnyte-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Egnyte の間にシングル サインオンを構成する方法について説明します。

この記事では、Egnyte と Microsoft Entra ID を統合する方法について説明します。 Egnyte と Microsoft Entra ID を統合すると、次のことができます。

- Egnyte にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Egnyte に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Egnyte でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Egnyte では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Egnyte の追加

Microsoft Entra ID への Egnyte の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Egnyte を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Egnyte**」と入力します。
4. 結果パネルから **Egnyte** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Egnyte 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Form.com に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Form.com の関連ユーザーの間にリンク関係を確立する必要があります。

Form.com に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Egnyte SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Egnyte のテストユーザーを作成する** - B.Simon の Egnyte 上の対応ユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Egnyte**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.egnyte.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.egnyte.com/samlconsumer/AzureAD`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と応答 URL で値を更新してください。 この値を取得するには [、Egnyte クライアント サポート チーム](https://www.egnyte.com/corp/contact_egnyte.html) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Egnyte のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Egnyte の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Egnyte 企業サイトに管理者としてサインインします。
2. [ **設定] を選択します**。

    [Image: 設定 1]
3. メニューの [ **設定]** を選択します。

    [Image: メニュー 1]
4. [ **構成** ] タブを選択し、[ **セキュリティ**] を選択します。

    [Image: セキュリティ]
5. [ **単一 Sign-On 認証** ] セクションで、次の手順に従います。

    [Image: シングル サインオン認証]

    1. **シングル サインオン認証**として、**SAML 2.0** を選択します。
    2. **ID プロバイダー**として、**Microsoft Entra ID を**選択します。
    3. ID プロバイダーの **ログイン URL** ボックスに **ログイン URL を** 貼り付けます。
    4. 持っている**Microsoft Entra 識別子**を**プロバイダーエンティティ ID**ボックスに貼り付けます。
    5. Azure portal からダウンロードした Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、 **ID プロバイダー証明書** のテキスト ボックスに貼り付けます。
    6. **既定のユーザー マッピング**として、[**電子メール アドレス**] を選択します。
    7. **「ドメイン固有の発行者の値を使用」**で、「**無効**」を選択します。
    8. **[保存] を選択します**。

#### Egnyte のテスト ユーザーの作成

Microsoft Entra ユーザーが Egnyte にサインインできるようにするには、Egnyte にプロビジョニングする必要があります。 Egnyte の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. **Egnyte** 企業サイトに管理者としてサインインします。
2. **[設定]**&gt;**[ユーザー]、[グループ] の順に**移動します。
3. [ **新しいユーザーの追加]** を選択し、追加するユーザーの種類を選択します。

    [Image: ユーザー]
4. **新しい Power User** セクションで、次の手順を実行します。

    [Image: 新しい標準ユーザー]

    ある。 [ **電子メール** ] テキスト ボックスに、ユーザーの電子メール ( **Brittasimon@contoso.com**など) を入力します。

    b。 [ **ユーザー名** ] テキスト ボックスに、 **Brittasimon** などのユーザーのユーザー名を入力します。

    c. [**認証の種類**] として [**シングル サインオン]** を選択します。

    d. **[保存] を選択します**。

    注

    Microsoft Entra アカウント保有者に通知メールが届きます。

注

Egnyte から提供されている他の Egnyte ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Egnyte のサインオン URL にリダイレクトされます。
- Egnyte のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Egnyte] タイルを選択すると、このオプションは Egnyte のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/egress-tutorial"} -->
## Microsoft Entra ID で Egress for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/egress-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Egress 間にシングル サインオンを構成する方法について説明します。

この記事では、Egress と Microsoft Entra ID を統合する方法について説明します。 Egress と Microsoft Entra ID を統合すると、次のことができます:

- Egress にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Egress に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Egress でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Egress では、**SPおよびIDPによるSSOの開始**がサポートされます。
- Egress では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Egress の追加

Microsoft Entra ID への Egress の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Egress を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Egress**」と入力します。
4. 結果パネルから **[エグレス]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Egress 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Egress に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Egress の関連ユーザーとの間にリンク関係を確立する必要があります。

Egress に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Egress SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Egress テスト ユーザーの作成** - Egress における B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現とリンクされます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Egress**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://switch.egress.com/ui/`
7. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Egress SSO の構成

1. 別のブラウザー ウィンドウで、Egress 企業サイトに管理者としてサインオンします。
2. 次のページで、以下の手順を実行します。

    [Image: エグレス構成]

    a 左側のメニューで、[ **SSO 構成]** を選択します。

    b。 [ **シングル サインオンを使用** する] ラジオ ボタンを選択して、シングル サインオンを使用します。

    c. [プロバイダーの説明] ボックスに有効な **説明** を入力します。

    d. [ **メタデータ URL** ] ボックスに、コピーした **アプリのフェデレーション メタデータ URL** の値を貼り付けます。

    え [ **メタデータの読み込み]** を選択します。

    f. [ **保存]** ボタンを選択して SSO 構成を更新します。

#### Egress テスト ユーザーの作成

1. **Egress** 企業サイトにサインインします。
2. 左側のメニューで [ **ユーザーの招待** ] を選択し、[ **シングル ユーザーの招待** ] を選択してユーザーを追加します。

    [Image: [1 人のユーザーの招待] ボタンが選択されている [Invite Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの招待) ページを示すスクリーンショット。]
3. 必要なフィールドに入力し、[招待] を選択 **します**。

    [Image: エグレスのテスト ユーザーの作成]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、ログイン フローを開始できるエグレス サインオン URL にリダイレクトされます。
- Egress のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Egress に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [エグレス] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Egress に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ekarda-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ekarda を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ekarda-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ekarda の間のシングル サインオンを構成する方法について説明します。

この記事では、ekarda と Microsoft Entra ID を統合する方法について説明します。 ekarda を Microsoft Entra ID と統合すると、次のことが可能になります。

- ekarda へのアクセス権を持つユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ekarda に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な ekarda サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ekarda によって、SP Initiated SSO と IDP-initiated SSO がサポートされます。
- ekarda によって、Just-In-Time ユーザー プロビジョニングがサポートされます。

### ギャラリーから ekarda を追加する

Microsoft Entra ID への ekarda の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ekarda を追加します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「ekarda**」と入力します。
4. 結果パネルから **ekarda** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ekarda に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ekarda に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと ekarda の関連ユーザーとの間に、リンクされた関係を確立する必要があります。

ekarda 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. ユーザーがこの機能を使用できるように Microsoft Entra SSO を構成します。

    1. B.Simon で Microsoft Entra のシングル サインオンをテストする Microsoft Entra テスト ユーザーを作成します。
    2. B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます。
2. ekarda SSO を構成 して、アプリケーション側でシングル サインオン設定を構成します。

    - ekarda テスト ユーザーを作成し、ekarda で B.Simon に対応するユーザーを作成し、そのユーザーの Microsoft Entra 表現にリンクします。
3. SSO をテスト して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、Azure portal でこれらの手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**ekarda**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、鉛筆アイコンを選択して **基本的な SAML 構成** 設定を編集します。

    [Image: 鉛筆アイコンが強調表示されている [SAML を使用した単一 Sign-On の設定] ページのスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダーメタデータファイル**が表示される場合は、次の手順に従います。

    1. [ **メタデータ ファイルのアップロード]** を選択します。
    2. フォルダー アイコンを選択してメタデータ ファイルを選択し、[ **アップロード**] を選択します。
    3. メタデータ ファイルが正常にアップロードされると、 **識別子** と **応答 URL** の値が ekarda セクションのテキスト ボックスに自動的に表示されます。

    注

    **識別子**と**応答 URL** の値が自動的に表示されない場合は、要件に従って値を手動で入力します。
6. **[基本的な SAML 構成]** セクションに**サービス プロバイダー メタデータ ファイル**が表示されない場合に、IDP 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    1. [ **識別子** ] テキスト ボックスに、次のパターンに従う URL を入力します。 `https://my.ekarda.com/users/saml_metadata/<COMPANY_ID>`
    2. [ **応答 URL** ] テキスト ボックスに、次のパターンに従う URL を入力します。 `https://my.ekarda.com/users/saml_acs/<COMPANY_ID>`
7. SP 開始モードでアプリケーションを構成する場合は、[ **追加の URL の設定]** を選択して、次の操作を行います。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンに従う URL を入力します。 `https://my.ekarda.com/users/saml_sso/<COMPANY_ID>`

    注

    前の 2 つの手順の値は、実際のものではありません。 実際の識別子、応答 URL、サインオン URL の値でこれらの値を更新します。 これらの値を取得するには [、ekarda クライアント サポート チーム](mailto:contact@ekarda.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して証明書 **(Base64)** をコンピューターに保存します。

    [Image: [SAML を使用した単一 Sign-On のセットアップ] ページの [SAML 署名証明書] セクションのスクリーンショット。Base64 証明書のダウンロード リンクが強調表示されています。]
9. [ **ekarda のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: URL コピー リンクが強調表示された、[SAML によるシングル サインオンの設定] ページの [SAML 署名証明書] セクションのスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ekarda SSO の構成

1. 別の Web ブラウザー ウィンドウで、ekarda 企業サイトに管理者としてサインインします。
2. [ **管理者**&gt;**マイ アカウント] を選択します**。

    [Image: [管理] メニューで [マイ アカウント] が強調表示されている ekarda サイト UI のスクリーンショット。]
3. ページの下部にある [ **SAML SETTINGS]** セクションを見つけます。 このセクションで SAML 統合を構成します。
4. [ **SAML SETTINGS]** セクションで、次の手順に従います。

    [Image: SAML 構成フィールドが強調表示されている ekarda SAML SETTINGS ページのスクリーンショット。]

    1. **サービス プロバイダーのメタデータ** リンクを選択し、コンピューターにファイルとして保存します。
    2. [ **SAML を有効にする** ] チェック ボックスをオンにします。
    3. **IDP エンティティ ID** テキスト ボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。
    4. **[IDP ログイン URL**] テキスト ボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。
    5. **[IDP ログアウト URL**] テキスト ボックスに、先ほどコピーした**ログアウト URL** の値を貼り付けます。
    6. メモ帳を使用して、ダウンロードした **証明書 (Base64)** ファイルを開きます。 その内容を **IDP x509 証明書** テキスト ボックスに貼り付けます。
    7. **[OPTIONS**]\(オプション\) セクションの [**Enable SLO]\(SLO を有効にする**\) チェック ボックスをオンにします。
    8. [ **更新] を**選択します。

#### ekarda テスト ユーザーの作成

このセクションでは、B. Simon という名前のユーザーを ekarda に作成します。 ekarda によって、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションで実行する操作はありません。 ekarda に B. Simon という名前のユーザーがまだ存在しない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ekarda のサインオン URL にリダイレクトされます。
- ekarda のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ekarda に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ekarda] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ekarda に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ekincare-tutorial"} -->
## Microsoft Entra ID で eKincare for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ekincare-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と eKincare の間のシングル サインオンを構成する方法について説明します。

この記事では、eKincare と Microsoft Entra ID を統合する方法について説明します。 eKincare を Microsoft Entra ID と統合すると、次のことが可能になります。

- eKincare にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで eKincare に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

eKincare と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- eKincare でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- eKincare では、 **IDP** によって開始される SSO がサポートされます。
- eKincare では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから eKincare を追加する

Microsoft Entra ID への eKincare の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に eKincare を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「eKincare**」と入力します。
4. 結果パネルから **eKincare** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### eKincare の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、eKincare に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、eKincare での関連ユーザーとの間にリンク関係を確立する必要があります。

eKincare 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **eKincare SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **eKincare テストユーザーの作成** - eKincareで、Microsoft Entraでのユーザー表現とリンクされたB.Simonに対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**eKincare**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ａ。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<instancename>.ekincare.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<instancename>.ekincare.com/hul/saml`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [eKincare クライアント サポート チーム](mailto:tech@ekincare.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. eKincare アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 このアプリケーションに対して次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの 「 **ユーザー属性」** セクションから管理できます。 [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** ボタンを選択して [ **ユーザー属性] ダイアログを** 開きます。

    [Image: [編集] ボタンが選択された [ユーザー属性] ダイアログを示すスクリーンショット。]
7. [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、[**編集] アイコン**を使用して要求を編集するか、[**新しい要求の追加]** を使用して要求を追加し、上の図に示すように SAML トークン属性を構成し、次の手順を実行します。

    | 名前 | ソース属性 |
    | --- | --- |
    | 従業員ID | *user.extensionattribute1* |
    | 組織ID | *"uniquevalue"* |
    | オーガニゼーションネーム | *ユーザー.会社名* |

    ａ。 [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [新しい要求の追加] ボタンと [保存] ボタンが選択された [ユーザー要求] ダイアログを示すスクリーンショット。]

    [Image: eKincare アプリケーションの画像を示すスクリーンショット。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. [ソース] を **[属性**] として選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. [ **OK] を選択する**

    g. **[保存] を選択します**。
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **eKincare のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: スクリーンショットは、構成をコピーするための適切な U R L を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### eKincare の SSO を構成する

**eKincare** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [eKincare サポート チーム](mailto:tech@ekincare.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### eKincare テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを eKincare に作成します。 eKincare では、 **Just-In-Time ユーザー プロビジョニング**がサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 eKincare にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した eKincare に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [eKincare] タイルを選択すると、SSO を設定した eKincare に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/elearnposh-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に eLearnPOSH を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elearnposh-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と eLearnPOSH の間のシングル サインオンを構成する方法について説明します。

この記事では、eLearnPOSH と Microsoft Entra ID を統合する方法について説明します。 eLearnPOSH を Microsoft Entra ID と統合すると、次のことが可能になります。

- eLearnPOSH にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで eLearnPOSH に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- eLearnPOSH シングル サインオン (SSO) が有効な InTime のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- eLearnPOSH では、**IDP** によって開始される SSO がサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの eLearnPOSH の追加

eLearnPOSH と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に eLearnPOSH をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**eLearnPOSH**」と入力します。
4. 結果のパネルから **[eLearnPOSH]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### eLearnPOSH 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、eLearnPOSH での Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、eLearnPOSH での関連ユーザーとの間にリンク関係を確立する必要があります。

eLearnPOSH 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **eLearnPOSH の SSO を構成する**- アプリケーション側のシングル サインオン設定を構成します。
    1. **eLearnPOSH テストユーザーを作成** - Microsoft Entra に代表されるユーザーとリンクした B.Simon の対応者を eLearnPOSH で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**eLearnPOSH**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. eLearnPOSH アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、eLearnPOSH アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ユーザー名 | ユーザーのメールアドレス |
    | ファーストネーム | User.givenname |
    | lastname | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### eLearnPOSH SSO の構成

**eLearnPOSH** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [eLearnPOSH サポート チーム](mailto:contact@succeedtech.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### eLearnPOSH テストユーザーの作成

このセクションでは、eLearnPOSH で Britta Simon というユーザーを作成します。 [eLearnPOSH サポートチーム](mailto:contact@succeedtech.com) と協力して、eLearnPOSH プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した eLearnPOSH に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [eLearnPOSH] タイルを選択すると、SSO を設定した eLearnPOSH に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/eletive-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Eletive を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/eletive-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: ユーザー アカウントをMicrosoft Entra IDから Eletive に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Eletive と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスを使用して、[Eletive](https://app.eletive.com/) にユーザーとグループを自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Eletive でユーザーを作成する
- アクセスが不要になった場合に Eletive のユーザーを削除する
- Microsoft Entra IDと Eletive の間でユーザー属性の同期を維持する
- Eletive へのシングル サインオン (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 管理者アクセス権を持つ Eletive のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとEletiveの間で[マップするデータを決定](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)する。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Eletive を構成する

1. [Eletive](https://app.eletive.com/) にサインインします。  &gt;] に移動します。

    [Image: 機能]
2. **統合**と **SCIM 2.0** を有効にします。

    [Image: 統合]
3. **設定**&gt;**Integrations** に移動します。
4. **ユーザー プロビジョニング**を選択します。

    [Image: タブ]
5. [ **接続**] を選択します。

    [Image: ボタン]
6. SCIM 2.0 URL とベアラー トークンをコピーし、保存します。 これらの値は、Eletive アプリケーションの [プロビジョニング] タブの [テナント URL] フィールドと [シークレット トークン] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Eletive を追加する

Microsoft Entra アプリケーション ギャラリーから Eletive を追加して、Eletive へのプロビジョニングの管理を開始します。 以前に Eletive を SSO 用に設定している場合は、同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Eletive への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Eletive の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **Eletive** を選択します。

    [Image: アプリケーションの一覧の Eletive のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Eletive テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Eletive に接続できることを確認します。 接続に失敗した場合は、Eletive アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Eletive に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Eletive のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Eletive API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | emails[type eq "仕事"].value | 糸 |  |
    | externalId | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | 優先言語 | 糸 |  |
    | ユーザータイプ | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:eletive:2.0:User:participateInSurvey | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/elia-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に elia を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elia-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから elia にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために elia と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra IDを構成すると、Microsoft Entra プロビジョニング サービスを使用してユーザーを[elia](https://elia.one)に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされる機能

- elia でユーザーを作成します。
- アクセスが不要になったら、elia のユーザーを削除します。
- Microsoft Entra IDとeliaの間でユーザー属性を常に同期させます。
- elia に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elia-tutorial)します (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者アクセス許可がある elia のユーザー アカウント。

### 手順 1: プロビジョニング展開を計画する

- [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- Microsoft Entra IDとeliaの間でマッピングするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように elia を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように elia を構成するには、elia サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから elia を追加する

Microsoft Entra アプリケーション ギャラリーから elia を追加して、elia へのプロビジョニングの管理を開始します。 SSO のために elia を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: elia への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて elia でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで elia の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[elia**] を選択します。

    [Image: アプリケーションの一覧の [elia] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、elia テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが elia に接続できることを確認します。 接続に失敗した場合は、elia アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから elia に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で elia のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、elia API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | elia に必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | タイトル | 糸 |  |  |
    | 名前.名 | 糸 |  |  |
    | 名前.姓 | 糸 |  |  |
12. Microsoft Entra IDから elia に同期されるグループ属性を、**Attribute-Mapping** セクションで確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で elia のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | elia における必須事項 |
    | --- | --- | --- | --- |
    | 表示名 | 糸 | ✓ | ✓ |
    | エクスターナルID | 糸 |  |  |
    | メンバー | リファレンス |  |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/elia-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に elia を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elia-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と elia の間でシングル サインオンを構成する方法について説明します。

この記事では、elia と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と elia を統合すると、次のことができます。

- elia にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して elia に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- elia でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- elia では、 **SP** Initiated SSO がサポートされます。

### ギャラリーから elia を追加する

Microsoft Entra ID への elia の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に elia を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「elia**」と入力します。
4. 結果パネルから **elia** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### elia の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、elia に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと elia の関連ユーザーとの間にリンク関係を確立する必要があります。

elia に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **elia SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**elia**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:dev-p0tbk3x9:<CONNECTION-NAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://dev-p0tbk3x9.us.auth0.com/login/callback?connection=<CONNECTION-NAME>&organization=<ORGANIZATION-ID>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://elia.one/?organization=<ORGANIZATION-ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、elia サポート チーム](mailto:support@gphy.ca) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. elia アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]

    注

    ドロップダウンから **名前**要求を手動で選択し、**user.userprincipalname** ではなく **user.displayname** をソース属性として更新してください。これはアプリケーション側の要件に従って両方の側で SSO 接続を適切に機能させるためです。その後、次に示すように [保存] を選択してください。 [Image: スクリーンショットは、名前要求の構成を示しています。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **PEM 証明書のダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示す証明書のスクリーンショット。]
8. [ **elia のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### elia SSO の構成

**elia** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** とログイン URL を Microsoft Entra 管理センターから [elia サポート チーム](mailto:support@gphy.ca)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる elia のサインオン URL にリダイレクトします。
- elia のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [elia] タイルを選択すると、このオプションは elia のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/elionboarding-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Eli Onboarding を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elionboarding-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Eli Onboarding の間でシングル サインオンを構成する方法について説明します。

この記事では、Eli Onboarding と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Eli Onboarding を統合すると、次のことができます。

- Microsoft Entra ID で Eli Onboarding へのアクセスを管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Eli Onboarding に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Eli Onboarding でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Eli Onboarding では、**SP** Initiated SSO がサポートされます。

### ギャラリーから Eli Onboarding を追加する

Microsoft Entra ID への Eli Onboarding の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Eli Onboarding を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Eli Onboarding**」と入力します。
4. 結果のパネルから **Eli Onboarding** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Eli Onboarding の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Eli Onboarding に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Eli Onboarding の関連ユーザーとの間にリンク関係を確立する必要があります。

Eli Onboarding で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Eli Onboarding SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Eli Onboarding テスト ユーザーの作成** - Eli Onboarding で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Eli Onboarding]**&gt;**[シングルサインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    a. [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<YOUR DOMAIN URL>/sso/saml/login`

    b。 [**識別子 (エンティティ ID)** テキスト ボックスに、次のパターンを使用して URL を入力します:`https://<YOUR DOMAIN URL>`

    手記

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Eli Onboarding クライアント サポート チーム](mailto:support@geteli.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [**Eli Onboarding** のセットアップ] セクションで、必要に応じて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Eli Onboarding SSO の構成

**Eli Onboarding** 側のシングルサインオンを構成するには、ダウンロードした **フェデレーション メタデータ XML** と、アプリケーションの構成からコピーされた適切な URL を [Eli Onboarding サポート チーム](mailto:support@geteli.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Eli Onboarding テスト ユーザーの作成

このセクションでは、Eli Onboarding で Britta Simon というユーザーを作成します。 [Eli Onboarding サポート チーム](mailto:support@geteli.com) と連携して、Eli Onboarding プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Eli Onboarding のサインオン URL にリダイレクトされます。
- Eli Onboarding のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Eli Onboarding] タイルを選択すると、このオプションは Eli Onboarding のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/elium-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Elium を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elium-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: ユーザー アカウントを Elium に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事では、Elium と Microsoft Entra ID を構成して、ユーザーまたはグループを Elium に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能としくみ、よく寄せられる質問の詳細については、「 Microsoft Entra ID。

このコネクタは、現在プレビューの段階です。 プレビューの詳細については、[オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)に関するページを参照してください。

Elium は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### サポートされている機能

- Elium でユーザーを作成します。
- アクセスが不要になったら、Elium のユーザーを削除します。
- Elium に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elium-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事では、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Elium テナント](https://www.elium.com/pricing/)
- 管理者アクセス許可がある Elium のユーザー アカウント

### Elium へのユーザーの割り当て

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーとグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Microsoft Entra ID で Elium へのアクセスが必要なユーザーとグループを決定します。 その後、[エンタープライズ アプリケーションへのユーザーまたはグループの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)に関する記事の手順に従って、それらのユーザーとグループを Elium に割り当てます。

### ユーザーを Elium に割り当てる際の重要なヒント

Elium に 1 人のMicrosoft Entra ユーザーを割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後で追加のユーザーやグループを割り当てることができます。

Elium にユーザーを割り当てるときは、アプリケーション固有の有効なロール (使用可能な場合) を割り当てダイアログ ボックスで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### Elium をプロビジョニング用に設定する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Elium を構成する前に、Elium でドメイン間 ID 管理 (SCIM) プロビジョニング用に System を有効にする必要があります。 次の手順のようにします。

1. Elium にサインインし、 **[マイ プロファイル]**&gt;**[設定]** に移動します。

    [Image: Elium の [設定] メニュー項目]
2. 左下隅にある **[ADVANCED](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定)** の **[セキュリティ]** を選択します。

    [Image: Elium の [セキュリティ] リンク]
3. **[テナントの URL]** と **[シークレット トークン]** の値をコピーします。 これらの値は後で、Elium アプリケーションの **[プロビジョニング]** タブの対応するフィールドで使用します。

    [Image: Elium の [テナントの URL] フィールドと [シークレット トークン] フィールド]

### ギャラリーから Elium を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Elium を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド サービスとしてのソフトウェア (SaaS) アプリケーションの一覧に Elium を追加する必要もあります。 次の手順のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動します。

    [Image: Microsoft Entra エンタープライズ アプリケーション ブレード]
3. 新しいアプリケーションを追加するには、ウィンドウの上部の **[新しいアプリケーション]** を選択します。

    [Image: [新しいアプリケーション] リンク]
4. 検索ボックスに「**Elium**」と入力し、結果一覧で **[Elium]** を選択してから、 **[追加]** ボタンを選択してアプリケーションを追加します。

    [Image: ギャラリーの検索ボックス]

### Elium への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーとグループの割り当てに基づいて、Elium でユーザーとグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Elium のシングル サインオンに関する記事の手順に従って、Security Assertion Markup Language (SAML) に基づいて [Elium のシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elium-tutorial)を有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

Microsoft Entra IDで Elium の自動ユーザー プロビジョニングを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: Microsoft Entra エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Elium]** を選択します。

    [Image: [エンタープライズ アプリケーション] ブレードのアプリケーション一覧]
4. **[プロビジョニング]** タブを選択します。

    [Image: [エンタープライズ アプリケーション] ブレードの [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Elium テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Elium に接続できることを確認します。 接続に失敗した場合は、Elium アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Elium に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Elium のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Elium API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Microsoft Entra ID と Elium 間の属性マッピング]
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/elium-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Elium を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elium-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Elium の間のシングル サインオンを構成する方法について説明します。

この記事では、Elium と Microsoft Entra ID を統合する方法について説明します。 Elium を Microsoft Entra ID と統合すると、次のことが可能になります。

- Elium にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Elium に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Elium は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Elium でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Elium は、**SPおよびIDPが開始したSSO**をサポートします。
- Elium では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。
- Elium では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elium-provisioning-tutorial)。

### ギャラリーから Elium を追加する

Microsoft Entra ID への Elium の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Elium を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Elium**」と入力します。
4. 結果パネルから **Elium** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Elium に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Elium に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Elium での関連ユーザーとの間にリンク関係を確立する必要があります。

Elium 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Elium SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Elium テスト ユーザーの作成 - Elium** で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現としての B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Elium**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<platform-domain>.elium.com/login/saml2/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<platform-domain>.elium.com/login/saml2/acs`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<platform-domain>.elium.com/login/saml2/login`

    注

    これらの値は実際の値ではありません。 これらの値は、でダウンロードできる `https://<platform-domain>.elium.com/login/saml2/metadata`から取得します。これについては、この記事の後半で説明します。
7. Elium アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Elium アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | first\_name | User.givenname |
    | last\_name | ユーザーの名字 |
    | 職務名 | ユーザー.職名 |
    | company | user.companyname |

    注

    これらは、既定の要求です。 **電子メール要求のみが必要です**。 JIT プロビジョニングの場合も、電子メール要求のみが必須です。 その他のカスタム要求は、顧客プラットフォームによって異なる場合があります。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Elium のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Elium の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Elium 企業サイトに管理者としてサインインします
2. 右上隅から **[ユーザー プロファイル** ] を選択し、[ **設定]** を選択します。

    [Image: シングル Sign-On ユーザー プロファイルを構成します。]
3. **詳細設定**の下で**セキュリティ**を選択します。

    [Image: Single Sign-On Advanced を構成します。]
4. [ **シングル サインオン (SSO)]** セクションまで下にスクロールし、次の手順を実行します。

    [Image: シングル サインオンを構成します。]

    a. [**SAML2 認証がアカウントに対して機能することを確認する**] の値をコピーし**、[基本的な SAML 構成**] セクションの **[サインオン URL**] ボックスに貼り付けます。

    注

    SSO を構成した後で、次の URL で既定のリモート ログイン ページにいつでもアクセスできます: `https://<platform_domain>/login/regular/login`。

    b。 [ **SAML2 フェデレーションを有効にする]** チェック ボックスをオンにします。

    c. [ **JIT プロビジョニング** ] チェック ボックスをオンにします。

    d. **[ダウンロード**] ボタンを選択して **SP メタデータ**を開きます。

    e. **SP メタデータ** ファイルで **entityID** を検索し、**entityID** 値をコピーして、[**基本的な SAML 構成**] セクションの **[識別子**] ボックスに貼り付けます。

    [Image: 単一 Sign-On 構成を構成します。]

    f. **SP メタデータ** ファイルで **AssertionConsumerService** を検索し、**場所**の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。

    [Image: 単一 Sign-On AssertionConsumerService を構成します。]

    g. Azure portal からダウンロードしたメタデータ ファイルをメモ帳に開き、内容をコピーして **IdP メタデータ** テキスト ボックスに貼り付けます。

    h. **[保存] を選択します**。

#### Elium のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Elium に作成します。 Elium では、 **Just-In-Time プロビジョニング**がサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Elium に存在しない場合は、Elium にアクセスしようとしたときに新しいユーザーが作成されます。

Elium では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elium-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Elium のサインオン URL にリダイレクトされます。
- Elium のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Elium に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Elium] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Elium に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/elqano-sso-tutorial"} -->
## Microsoft Entra ID で Elqano SSO for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elqano-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Elqano SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、Elqano SSO と Microsoft Entra ID を統合する方法について説明します。 Elqano SSO を Microsoft Entra ID と統合すると、次のことが可能になります。

- Elqano SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Elqano SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Elqano SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Elqano SSO では、**SP** Initiated SSO がサポートされます
- Elqano SSO を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスを拡張したものです。 [Microsoft Defender for Cloud Apps でセッション制御を適用する方法について説明します](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)。

### ギャラリーからの Elqano SSO の追加

Microsoft Entra ID への Elqano SSO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Elqano SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Elqano SSO**」と入力します。
4. 結果パネルから **Elqano SSO を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Elqano SSO に Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、Elqano SSO に対する Microsoft Entra SSO を構成してテストします。 Elqano SSO が機能するためには、Microsoft Entra ユーザーと、Microsoft Entra での関連ユーザーとの間にリンク関係を確立する必要があります。

Elqano SSO で Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Elqano SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Elqano SSO のテスト ユーザーの作成** - Elqano SSO で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Elqano SSO**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.elqano.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `elqano-<ENVIRONMENT>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、Elqano SSO クライアント サポート チーム](mailto:support@elqano.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. [ **Elqano SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Elqano SSO の構成

**Elqano SSO** 側でシングル サインオンを構成するには、アプリケーション構成から**コピーした拇印の値**と適切な URL を [Elqano SSO サポート チーム](mailto:support@elqano.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Elqano SSO のテスト ユーザーの作成

このセクションでは、Elqano SSO で B.Simon というユーザーを作成します。 [Elqano SSO サポート チーム](mailto:support@elqano.com)と協力して、Elqano SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Elqano SSO] タイルを選択すると、SSO を設定した Elqano SSO に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/elsevier-sp-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Elsevier SP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/elsevier-sp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Elsevier SP の間のシングル サインオンを構成する方法について説明します。

この記事では、Elsevier SP と Microsoft Entra ID を統合する方法について説明します。 Elsevier SP では、Microsoft Entra 資格情報を使用して、組織の Elsevier サブスクリプションにアクセスできます。 Elsevier SP と Microsoft Entra ID を統合すると、次のことができます。

- Elsevier SP にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Elsevier SP に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Elsevier SP 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Elsevier SP は、**SP** Initiated シングル サインオンのみサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Elsevier SP と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Elsevier SP のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Elsevier SP アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Elsevier SP を追加する

Microsoft Entra アプリケーション ギャラリーから Elsevier SP を追加して、Elsevier SP でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Enterprise アプリ]**&gt;**[Elsevier SP]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、URL を入力します。 `https://sdauth.sciencedirect.com/`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://auth.elsevier.com/SHIRE/SAML2/POST`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://auth.elsevier.com/ShibAuth/institutionLogin?entityID=<customer-URL-encoded-entityID>&appReturnURL=https%3A%2F%2Fwww.sciencedirect.com`
6. Elsevier SP アプリケーションでは、特定の形式の SAML アサーションが想定されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは例を示しています。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Elsevier SP では永続名 ID にマップされることが想定されています。 そのため、一覧の **user.objectid** 属性を使用するか、組織構成に基づいて適切な属性値を使用できます。

    [Image: トークン属性の画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Elsevier SP のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

### Elsevier SP SSO を構成する

**Elsevier SP** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Elsevier SP サポート チーム](mailto:iam_platform@elsevier.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Elsevier SP テスト ユーザーを作成する

このセクションでは、Seculio で Britta Simon というユーザーを作成します。 [Elsevier SP サポート チーム](mailto:iam_platform@elsevier.com)と協力して、Elsevier SP プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Elsevier SP サインオン URL にリダイレクトされます。
- Elsevier SP のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Elsevier SP] タイルを選択すると、このオプションは Elsevier SP のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/eluminate-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に eLuminate を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/eluminate-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と eLuminate の間のシングル サインオンを構成する方法について説明します。

この記事では、eLuminate と Microsoft Entra ID を統合する方法について説明します。 eLuminate を Microsoft Entra ID と統合すると、次のことが可能になります。

- eLuminate にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで eLuminate に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な eLuminate のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- eLuminate では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの eLuminate の追加

Microsoft Entra ID への eLuminate の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に eLuminate を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「eLuminate**」と入力します。
4. 結果パネルから **eLuminate** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### eLuminate の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、eLuminate に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、eLuminate での関連ユーザーとの間にリンク関係を確立する必要があります。

eLuminate 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **eLuminate SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **eLuminateのテストユーザーを作成** - Microsoft Entraでのユーザー表現にリンクされた、eLuminate内のB.Simonの対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**eLuminate** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて**、シングル サインオン**を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `Eluminate/ClientShortName`

    b。 [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://ClientShortName.eluminate.ca/azuresso/account/SignIn`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、eLuminate クライアント サポート チーム](mailto:support@intellimedia.ca) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### eLuminate SSO の構成

**eLuminate** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[eLuminate サポート チーム](mailto:support@intellimedia.ca)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### eLuminate テスト ユーザーの作成

このセクションでは、eLuminate で Britta Simon というユーザーを作成します。 [eLuminate サポート チーム](mailto:support@intellimedia.ca)と協力して、eLuminate プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる eLuminate のサインオン URL にリダイレクトされます。
- eLuminate のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [eLuminate] タイルを選択すると、このオプションは eLuminate のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/embark-tutorial"} -->
## Microsoft Entra ID を使用したシングル サインオンのためにEmbarkを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/embark-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Embark 間のシングル サインオンを構成する方法について説明します。

この記事では、Embark と Microsoft Entra ID を統合する方法について説明します。 Embark を Microsoft Entra ID と統合すると、次のことが可能になります。

- Embark にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Embark に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Embark でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Embark では、**SP と IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Embark の追加

Microsoft Entra ID への Embark の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Embark を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Embark**」と入力します。
4. 結果のパネルから **[Embark]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Embark に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Embark に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと Embark の関連ユーザー間にリンク関係を確立する必要があります。

Embark に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Embark の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Embark テスト ユーザーの作成** - Embark で B.Simon に対応するユーザーを作成し、Microsoft Entra における B.Simon の表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Embark]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT>.ehr.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT>.ehr.com`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT>.ehr.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Embark サポート チーム](mailto:wtw.software.support.notification@willistowerswatson.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Embark アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、Embark ではこれをユーザーの従業員 ID とマップすることを想定しています。そのためには、一覧から **user.employeeid** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
8. その他に、Embark プラットフォーム アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を下に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 従業員ID | user.employeeid |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Embark SSO の構成

**Embark** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Embark サポート チーム](mailto:wtw.software.support.notification@willistowerswatson.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Embark テスト ユーザーの作成

このセクションでは、Embark テストユーザーの作成 で Britta Simon というユーザーを作成します。 [Embark サポートチーム](mailto:wtw.software.support.notification@willistowerswatson.com)と連携して、Embark プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる、乗り出しプラットフォームのサインオン URL にリダイレクトされます。
- Embark プラットフォームのサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Embark プラットフォームに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [プラットフォームに乗り出す] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Embark プラットフォームに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/embed-signage-provisioning-tutorial"} -->
## Microsoft Entra ID を使用してユーザーの自動プロビジョニング用に embed signage を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/embed-signage-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-09
- Summary: ユーザー アカウントを Microsoft Entra ID から embed signage に、自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために埋め込みサイネージと Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[embed signage](https://embedsignage.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- 埋め込みサイネージのユーザー作成。
- アクセスが不要になったら、埋め込みサイネージのユーザーを削除します。
- Microsoft Entra ID と embed signage の間でユーザー属性の同期を維持する。
- 埋め込みサイネージにグループとグループ メンバーシップをプロビジョニング。
- embed signage への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/embed-signage-tutorial)(推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者権限を持つ埋め込みサイネージのユーザーアカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と embed signage の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように embed signage を構成する

1. [埋め込みサイネージ管理コンソール](https://app.embedsignage.com/login)にログインします。
2. **[アカウント設定] &gt; [セキュリティ] &gt; [ユーザー プロビジョニング]** に移動します。
3. トークンを作成し、安全な場所にコピーします。 この値は、埋め込みサイネージ アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** \*] フィールドに入力されます。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから embed signage を追加する

Microsoft Entra アプリケーション ギャラリーから embed signage を追加して、embed signage へのプロビジョニングの管理を開始します。 SSO のために以前に埋め込みサイネージを設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: embed signage への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて embed signage のユーザーやグループを作成、更新、無効化するよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で embed signage の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[embed signage]** を選択します。

    [Image: アプリケーション一覧の埋め込みサイネージ リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、埋め込みサイネージのテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が埋め込みサイネージに接続できることを確認します。 接続に失敗した場合は、埋め込みサイネージ アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から embed signage に同期されるユーザー属性を確認します。 [**照合**] プロパティとして選択されている属性は、更新処理で埋め込みサイネージのユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、埋め込みサイネージ API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | 埋め込みサイネージで必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | displayName | 糸 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | 活動中 | ブール値 |  |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から embed signage に同期されるグループ属性を確認します。 [**照合**] プロパティとして選択されている属性は、更新処理で埋め込みサイネージのグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | 埋め込みサイネージで必要 |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/embed-signage-tutorial"} -->
## Microsoft Entra ID でのシングル サインオン用に embed signage を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/embed-signage-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と embed signage の間のシングル サインオンを構成する方法について説明します。

この記事では、埋め込みサイネージと Microsoft Entra ID を統合する方法について説明します。 embed signage を Microsoft Entra ID と統合すると、次のことができます。

- embed signage にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して embed signage に自動的にサインインするようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- embed signage でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- embed signage では、**IDP** によって開始される SSO がサポートされます。

### ギャラリーからの embed signage の追加

Microsoft Entra ID への embed signage の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に embed signage を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックスに **「埋め込みサイネージ** 」と入力します。
4. 結果パネルから **埋め込みサイネージ** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### embed signage 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、埋め込みサイネージに対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと embed signage の関連ユーザー間にリンク関係を確立する必要があります。

embed signage 用に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **埋め込みサイネージの SSO を構成**する - アプリケーション側でシングル サインオン設定を構成します。
    1. **埋め込みサイネージ のテスト ユーザーの作成** - Microsoft Entra のユーザー表現にリンクされた埋め込みサイネージで B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[embed signage]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** ページで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.embedsignage.com/auth/saml/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.embedsignage.com/auth/saml/login/<ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、埋め込みサイネージ クライアント サポート チーム](mailto:support@embedsignage.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **埋め込みサイネージのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### embed signage の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Embed Signage 企業サイトに管理者としてサインインします。
2. **[アカウント設定]** に移動し、[**セキュリティ**&gt;**Single サインオン]** を選択します。
3. [ **シングル サインオン** ] セクションで、次の手順に従います。

    [Image: SSO アカウントを示すスクリーンショット。]

    1. [シングル サインオンを**有効にする**] チェック ボックスをオンにします。
    2. ダウンロードした **フェデレーション メタデータ XML** を開き、 **メタデータ XML ファイルにファイル**をアップロードします。
    3. [ **変更の保存] を選択します**。

#### embed signage テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、embed signage 企業サイトに管理者としてサインインします。
2. **[アカウント設定]** に移動し、[**ユーザー**] &gt;**[新しいユーザー**] を選択します。
3. [ **設定]** セクションで、次のページで必要なフィールドを手動で入力し、[ **ユーザーの作成**] を選択します。

    [Image: ユーザーの作成を示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した埋め込みサイネージに自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで埋め込みサイネージ タイルを選択すると、SSO を設定した埋め込みサイネージに自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/empactis-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Empactis を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/empactis-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Empactis 間にシングル サインオンを構成する方法について学習します。

この記事では、Empactis と Microsoft Entra ID を統合する方法について説明します。 Empactis と Microsoft Entra ID を統合すると、次のことができます。

- Empactis にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Empactis に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Empactis のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Empactis では、**IDP** によって開始される SSO がサポートされます。

### ギャラリーからの Empactis の追加

Microsoft Entra ID への Empactis の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Empactis を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Empactis**」と入力します。
4. 結果のパネルから **[Empactis]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Empactis 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Empactis に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Empactis の関連ユーザーとの間にリンク関係を確立する必要があります。

Empactis に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Empactis のテストユーザーを作成** - Empactis で B.Simon に相当するユーザーを作成し、そのユーザーを Microsoft Entra の表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Empactis** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Empactis のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 適切な構成用URLをコピーするスクリーンショットです。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Empactis SSO の構成

**Empactis** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [Empactis サポート チーム](mailto:support@empactis.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Empactis テスト ユーザーを作成する

このセクションでは、Empactis で Britta Simon というユーザーを作成します。 [Empactis サポート チーム](mailto:support@empactis.com)と連携して、Empactis プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Empactis に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Empactis] タイルを選択すると、SSO を設定した Empactis に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/empcenter-tutorial"} -->
## Microsoft Entra ID で EmpCenter for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/empcenter-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EmpCenter 間にシングル サインオンを構成する方法について説明します。

この記事では、EmpCenter と Microsoft Entra ID を統合する方法について説明します。 EmpCenter と Microsoft Entra ID の統合には、次の利点があります。

- EmpCenter にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで EmpCenter に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

EmpCenter と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、[ここで](https://azure.microsoft.com/pricing/free-trial/) 1 か月の試用版を入手できます
- EmpCenter でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- EmpCenter では、 **SP** Initiated SSO がサポートされます

### ギャラリーから EmpCenter を追加する

Microsoft Entra ID への EmpCenter の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に EmpCenter を追加する必要があります。

**ギャラリーから EmpCenter を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **EmpCenter**」と入力し、結果パネルで **EmpCenter** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: EmpCenterが結果一覧にあります]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、EmpCenter で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと EmpCenter 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

EmpCenter で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **EmpCenter シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **EmpCenter テストユーザーの作成** - Britta Simon に対応する EmpCenter ユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

EmpCenter で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**EmpCenter** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: EmpCenterドメインおよびURLのシングルサインオン情報]

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    ```https
    https://<subdomain>.EmpCenter.com/<instancename>
    https://<subdomain>.workforcehosting.com/<instancename>
    ```

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、 [EmpCenter クライアント サポート チーム](https://workforcesoftware.com/support-offerings/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **EmpCenter のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    エー。 ログイン URL

    b。 Microsoft Entra アイデンティファイヤー

    c. ログアウト URL

#### EmpCenter シングル サインオンの構成

**EmpCenter** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [EmpCenter サポート チーム](https://workforcesoftware.com/support-offerings/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーを作成する

このセクションの目的は、Britta Simon というテスト ユーザーを作成することです。

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

このセクションでは、Britta Simon に EmpCenter へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**EmpCenter** に移動します。

    [Image: [企業向けアプリケーション] パネル]
3. アプリケーションの一覧で **EmpCenter** を選択します。

    [Image: アプリケーションの一覧の EmpCenter のリンク]
4. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
5. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。

    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

#### EmpCenter のテスト ユーザーの作成

Microsoft Entra ユーザーが EmpCenter にログインできるようにするには、そのユーザーを EmpCenter にプロビジョニングする必要があります。 EmpCenter の場合は、 [EmpCenter サポート チーム](https://workforcesoftware.com/support-offerings/)がユーザー アカウントを作成する必要があります。

注

EmpCenter から提供されている他の EmpCenter ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [EmpCenter] タイルを選択すると、SSO を設定した EmpCenter に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/emplifi-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Emplifi プラットフォームを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/emplifi-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Emplifi プラットフォームの間のシングル サインオンを構成する方法について説明します。

この記事では、Emplifi プラットフォームと Microsoft Entra ID を統合する方法について説明します。 Emplifi プラットフォームを Microsoft Entra ID と統合すると、次のことが可能になります。

- Emplifi プラットフォームにアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Emplifi プラットフォームに自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Emplifi platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Emplifi platform は **、SP と IDP** によって開始された SSO をサポートしています。

### ギャラリーから Emplifi platform の追加

Emplifi プラットフォーム と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Emplifi プラットフォームをギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Emplifi platform**」と入力します。
4. 結果パネルから **[Emplifi platform]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Emplifi プラットフォーム用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Emplifi プラットフォーム用の Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと、Emplifi プラットフォームでの関連ユーザーとの間にリンク関係を確立する必要があります。

Emplifi プラットフォーム用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Emplifi プラットフォームの SSO を構成する**- アプリケーション側のシングル サインオン設定を構成します。
    1. **Emplifi プラットフォームのテスト ユーザーを作成する** - Emplifi プラットフォームで、Microsoft Entra における B.Simon の表現とリンクするユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Emplifi platform]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<CustomerName>.account.socialbakers.com` |
    | `https://<CustomerName>.account.emplifi.io` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.account.emplifi.io/login/saml`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.account.emplifi.io`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Emplifi Platform のクライアントサポートチーム](mailto:support@emplifi.io) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Emplifi platform アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、Box はこれがユーザーのメール アドレスにマップされることを想定しています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
7. その他に、Emplifi platform アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Emplifi platform SSO の構成

**Emplifi platform** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [CrowdStrike Falcon Platform サポート チーム](mailto:support@emplifi.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Emplifi platform のテストユーザーの作成

このセクションでは、Emplifi platform で Britta Simon というユーザーを作成します。 [Emplifi platform サポートチーム](mailto:support@emplifi.io)と協力して、Emplifi platform プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Emplifi プラットフォームのサインオン URL にリダイレクトされます。
- Emplifi platform のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Emplifi プラットフォームに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Emplifi platform] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Emplifi プラットフォームに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/enablon-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオンの有効化を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/enablon-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Enablon の間でシングル サインオンを構成する方法について説明します。

この記事では、Enablon と Microsoft Entra ID を統合する方法について説明します。 Enablon と Microsoft Entra ID を統合すると、次のことができます。

- Enablon にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Enableon に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Enablon でのシングル サインオン (SSO) が有効化されたサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Enablon では、 **SP** Initiated SSO がサポートされます

### ギャラリーからの Enablon の追加

Microsoft Entra ID への Enablon の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Enablon を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Enablon**」と入力します。
4. 結果パネルから **Enablon** を選択して、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Enablon の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Enablon に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Enablon の関連ユーザーとの間にリンク関係を確立する必要があります。

Enablon で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Enablon SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Enablon テスト ユーザーの作成** - Enablon で B.Simon に対応するユーザーを作成し、このユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Enablon**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.enablon.com/<SITEID>/`

    b。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `http://<SUBDOMAIN>.enablon.com/adfs/services/trust`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.enablon.com/adfs/ls/`

    手記

    これらの値は実際の値ではありません。 実際の Sign-On URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには [、Enablon クライアント サポート チーム](mailto:ena-dl-ww.it.services@enablon.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Enablon SSO の構成

**Enablon** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Enablon サポート チーム](mailto:ena-dl-ww.it.services@enablon.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Enablon テスト ユーザーの作成

このセクションでは、Enablon で Britta Simon というユーザーを作成します。 [Enablon サポート チーム](mailto:ena-dl-ww.it.services@enablon.com)と協力して、Enablon プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Enablon のサインオン URL にリダイレクトされます。
- EnablonのサインオンURLに直接アクセスし、そこでログインフローを開始します。
- Microsoft マイ アプリを使用できます。 マイアプリで Enablon タイルを選択すると、Enablon サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/encompass-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Encompass を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/encompass-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Encompass の間のシングル サインオンを構成する方法について説明します。

この記事では、Encompass と Microsoft Entra ID を統合する方法について説明します。 Encompass を Microsoft Entra ID と統合すると、次のことが可能になります。

- Encompass にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Encompass に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Encompass でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Encompass では、**IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Encompass の追加

Microsoft Entra ID への Encompass の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Encompass を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Encompass**」と入力します。
4. 結果パネルで **[Encompass]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Encompass 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Encompass に対する Microsoft Entra SSO を構成およびテストするします。 SSO を機能させるには、Microsoft Entra ユーザーと、Encompass での関連ユーザーとの間にリンク関係を確立する必要があります。

Encompass 用の Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Encompass SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Encompass テストユーザーの作成** - Encompass で、Microsoft Entra におけるユーザー B.Simon とリンクする対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Encompass]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    a. **[識別子]** ボックスに顧客固有の値を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.voxmobile.com/voxportal/ws/saml/consume`

    注

    この値は実際の値ではありません。 実際の応答 URL でこの値を更新します。 これらの値を取得するには、[Encompass クライアント サポート チーム](https://www.voxmobile.com/contact/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Encompass のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Encompass SSO の構成

**Encompass** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [Encompass サポート チーム](https://www.voxmobile.com/contact/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Encompass テスト ユーザーの作成

このセクションでは、Encompass で Britta Simon というユーザーを作成します。 [Encompass サポート チーム](https://www.voxmobile.com/contact/)と連携し、Encompass プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Encompass に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Encompass] タイルを選択すると、SSO を設定した Encompass に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/enterprise-advantage-tutorial"} -->
## Microsoft Entra ID で Enterprise Advantage for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/enterprise-advantage-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-03-28
- Summary: Microsoft Entra ID と Enterprise Advantage の間でシングル サインオンを構成する方法について説明します。

この記事では、Enterprise Advantage と Microsoft Entra ID を統合する方法について説明します。 Enterprise Advantage と Microsoft Entra ID を統合すると、次のことができます。

- Enterprise Advantage にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Enterprise Advantage に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Enterprise Advantage でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Enterprise Advantage では、**SP-initiated SSO と IDP-initiated SSO** の両方をサポートします。
- Enterprise Advantage では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Enterprise Advantage を追加する

Microsoft Entra ID への Enterprise Advantage の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Enterprise Advantage を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**にブラウズで移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Enterprise Advantage**」と入力します。
4. 結果パネルから **[エンタープライズ アドバンテージ** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Enterprise Advantage の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Enterprise Advantage に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Enterprise Advantage の関連ユーザーとの間にリンク関係を確立する必要があります。

Enterprise Advantage で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Enterprise Advantage SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Enterprise Advantage のテストユーザーを作成** - Enterprise Advantage において B.Simon に対応するユーザーを作成し、それを Microsoft Entra ID のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Enterprise Advantage**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア. [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://sso.screeningxchange.com/` |
    | ステージング | `https://ssotest.screeningxchange.com/` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://sso.screeningxchange.com/sp/SAML2/POST` |
    | ステージング | `https://ssotest.screeningxchange.com/sp/SAML2/POST` |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://sso.screeningxchange.com/<CustomerLink>` |
    | ステージング | `https://ssotest.screeningxchange.com/<CustomerLink>` |

    注

    サインオン URL の値は実際の値ではありません。 サインオン URL で値を更新します。 この値を取得するには、 [Enterprise Advantage サポート チーム](mailto:globaladvantagesupport@fadv.com) にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Enterprise Advantage アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. さらに、Enterprise Advantage アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. [ **Enterprise Advantage のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Enterprise Advantage SSO の構成

**Enterprise Advantage** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [Enterprise Advantage サポート チーム](mailto:globaladvantagesupport@fadv.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Enterprise Advantage のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Enterprise Advantage に作成します。 Enterprise Advantage では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Enterprise Advantage にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Enterprise Advantage のサインオン URL にリダイレクトします。
- Enterprise Advantage のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Enterprise Advantage に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Enterprise Advantage] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Enterprise Advantage に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/entra-sso-for-doubleyou-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Active Directory SSO for DoubleYou を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/entra-sso-for-doubleyou-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Active Directory SSO for DoubleYou の間でシングル サインオンを構成する方法についてご説明します。

この記事では、Active Directory SSO for DoubleYou と Microsoft Entra ID を統合する方法について説明します。 Active Directory SSO for DoubleYou と Microsoft Entra ID を統合すると、次の作業が可能になります:

- Microsoft Entra ID で Active Directory SSO for DoubleYou にアクセスできるユーザーを制御できます。
- ユーザーが Microsoft Entra アカウントで Active Directory SSO for DoubleYou に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Active Directory SSO for DoubleYou でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Active Directory SSO for DoubleYou は、**SP および IDP** による SSO をサポートします。

### ギャラリーから Active Directory SSO for DoubleYou を追加する

Microsoft Entra ID への Active Directory SSO for DoubleYou の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Active Directory SSO for DoubleYou を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「Active Directory SSO for DoubleYou**」と入力します。
4. 結果パネルから **Active Directory SSO for DoubleYou** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Active Directory SSO for DoubleYou 向け Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Active Directory SSO for DoubleYou に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Active Directory SSO for DoubleYou の関連ユーザーとの間にリンク関係を確立する必要があります。

Active Directory SSO for DoubleYou を Microsoft Entra SSO と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Active Directory SSO for DoubleYou SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **DoubleYou 用 Active Directory SSO のテストユーザーを作成する** - DoubleYou 用 Active Directory SSO で B.Simon に対応するユーザーを作成し、そのユーザーが Microsoft Entra に表現された B.Simon にリンクされるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**Active Directory SSO for DoubleYou**&gt;**シングルサインオン** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<company-id>.welfare.it`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-id>.welfare.it/<store-id>?`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-id>.welfare.it/microsoft/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Active Directory SSO for DoubleYou クライアント サポート チーム](mailto:info@double-you.it) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Active Directory SSO for DoubleYou アプリケーションでは、特定の形式の SAML アサーションを予測しているため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、Active Directory SSO for DoubleYou では、これがユーザーの電子メール アドレスにマップされることを想定しています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Active Directory SSO for DoubleYou SSO の構成

**Active Directory SSO for DoubleYou** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Active Directory SSO for DoubleYou サポート チーム](mailto:info@double-you.it)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Active Directory SSO for DoubleYou テスト ユーザーの作成

このセクションでは、Active Directory SSO for DoubleYou で Britta Simon というユーザーを作成します。 [Active Directory SSO for DoubleYou サポート チーム](mailto:info@double-you.it)と協力して、Active Directory SSO for DoubleYou プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Active Directory SSO for DoubleYou サインオン URL にリダイレクトされます。
- Active Directory SSO for DoubleYou のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Active Directory SSO for DoubleYou に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Active Directory SSO for DoubleYou] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Active Directory SSO for DoubleYou に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/envimmis-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Envi MMIS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/envimmis-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Envi MMIS の間のシングル サインオンを構成する方法について説明します。

この記事では、Envi MMIS と Microsoft Entra ID を統合する方法について説明します。 Envi MMIS を Microsoft Entra ID と統合すると、次のことが可能になります。

- Envi MMIS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Envi MMIS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Envi MMIS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Envi MMIS では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。

### ギャラリーからの Envi MMIS の追加

Microsoft Entra ID への Envi MMIS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Envi MMIS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Envi MMIS**」と入力します。
4. 結果のパネルから **[Envi MMIS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Envi MMIS 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Envi MMIS 用に Microsoft Entra SSO を構成およびテストするします。 SSO が機能するためには、Microsoft Entra ユーザーと、Microsoft Entra での関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra に対する Microsoft Entra SSO を構成およびテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Envi MMIS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Envi MMIS テスト ユーザーの作成** - Envi MMIS で B.Simon に対応するユーザーを作成し、これを Microsoft Entra 上のユーザーとリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Envi MMIS**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを**IDP** イニシエートモードで構成する場合は、次の手順を実行します。

    1. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.<CUSTOMER DOMAIN>.com/Account`
    2. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.<CUSTOMER DOMAIN>.com/Account/Acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.<CUSTOMER DOMAIN>.com/Account`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Envi MMIS クライアント サポート チーム](mailto:support@ioscorp.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用したシングル Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Envi MMIS のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Envi MMIS SSO の構成

1. 別の Web ブラウザー ウィンドウで、Envi MMIS サイトに管理者としてサインインします。
2. [ **マイ ドメイン] タブを** 選択します。

    [Image: [My Domain](マイ ドメイン) が選択されている [User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) メニューを示すスクリーンショット。]
3. **[編集]** を選択します。

    [Image: 選択された [Edit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/編集) ボタンを示すスクリーンショット。]
4. **[Use remote authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/リモート認証を使用する)** チェック ボックスをオンにして、**[Authentication Type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の種類)** ドロップダウンから **[HTTP Redirect](HTTP リダイレクト)** を選びます。

    [Image: [Use remote authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/リモート認証を使用する) がオンになり、[H T T P Redirect](H T T P リダイレクト) が選択されている [Details](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細) タブを示すスクリーンショット。]
5. [ **リソース** ] タブを選択し、[ **メタデータのアップロード**] を選択します。

    [Image: [Upload Metadata](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メタデータのアップロード) アクションが選択された [Resources](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/リソース) タブを示すスクリーンショット。]
6. **[Upload Metadata](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メタデータのアップロード)** ポップアップで、次の手順を実行します。

    [Image: [File](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ファイル) オプションが選択され、ファイルの選択アイコンと [OK] ボタンが強調表示されている [Upload Metadata](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メタデータのアップロード) ポップアップを示すスクリーンショット。]

    1. **[Upload From](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アップロード元)** ドロップダウンから **[File](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ファイル)** オプションを選びます。
    2. **ファイル選択アイコン**を選ぶことにより、Azure portal からダウンロードしたメタデータ ファイルをアップロードします。
    3. **OK** を選択します。
7. ダウンロードしたメタデータ ファイルをアップロードすると、フィールドが自動的に設定されます。 **[更新]** を選択します。

    [Image: [シングル サインオンの構成] の [保存] ボタン]

#### Envi MMIS のテスト ユーザーの作成

Microsoft Entra ユーザーが Envi MMIS にサインインできるようにするには、そのユーザーを Envi MMIS にプロビジョニングする必要があります。 Envi MMIS の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. Envi MMIS 企業サイトに管理者としてサインインします。
2. [ **ユーザー一覧** ] タブを選択します。

    [Image: [User List](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー一覧) が選択されている [User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) メニューを示すスクリーンショット。]
3. [ **ユーザーの追加] ボタンを** 選択します。

    [Image: [Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ボタンが選択されている [User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) セクションを示すスクリーンショット。]
4. **[ユーザーの追加]** セクションで、次の手順を実行します。

    [Image: 従業員の追加を示すスクリーンショット。]

    1. **[User Name]\(ユーザー名)** テキストボックスに、Britta Simon のアカウントのユーザー名を入力します (例: **brittasimon@contoso.com**)。
    2. **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** テキスト ボックスに、Britta Simon の名を入力します (**Britta**)。
    3. **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** テキスト ボックスに、Britta Simon の名を入力します (**Simon**)。
    4. ユーザーの役職を **[Title](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/役職)** テキスト ボックスに入力します。
    5. **[Email Address](電子メール アドレス)** テキストボックスに、Britta Simon のアカウントのメール アドレスを入力します (例: **brittasimon@contoso.com**)。
    6. **[SSO User Name]\(SSO ユーザー名)** テキストボックスに、Britta Simon のアカウントのユーザー名を入力します (例: **brittasimon@contoso.com**)。
    7. **保存** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Envi MMIS サインオン URL にリダイレクトされます。
- Envi MMIS のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Envi MMIS に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Envi MMIS] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Envi MMIS に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/envoy-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Envoy を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/envoy-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: ユーザー アカウントを Envoy に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Envoy ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [Envoy](https://envoy.com/pricing/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Envoy でユーザーを作成する
- アクセスが不要になったときに Envoy のユーザーを削除する
- Microsoft Entra ID と Envoy 間でユーザー属性の同期を維持する
- Envoy でグループとグループ メンバーシップをプロビジョニングする
- Envoy への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/envoy-tutorial) (推奨)

Envoy は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- [Envoy テナント](https://envoy.com/pricing/)。
- Admin アクセス許可がある Envoy のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Envoy の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように Envoy を構成する

1. [Envoy 管理コンソールにサインインします](https://dashboard.envoy.com/login)。 **統合** を選択します。

    [Image: Envoy Integrations]
2. **インストール**を **Microsoft Azure SCIM 統合**のために選択します。

    [Image: Envoyのインストール]
3. [**すべてのユーザーの同期** **] で [保存] を選択します**。

    [Image: エンボイ・セーブ]
4. **OAUTH ベアラー トークンをコピーします**。 この値は、Envoy アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: Envoy OAUTH]

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Envoy を追加する

Microsoft Entra アプリケーション ギャラリーから Envoy を追加して、Envoy へのプロビジョニングの管理を開始します。 SSO のために Envoy を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Envoy への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Envoy に対する自動ユーザー プロビジョニングを構成するには、以下の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **Envoy** を選択します。

    [Image: アプリケーション一覧のEnvoyリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Envoy テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Envoy に接続できることを確認します。 接続に失敗した場合は、Envoy アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Envoy に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Envoy のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Envoy API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | externalId | 糸 |
    | displayName | 糸 |
    | タイトル | 糸 |
    | emails[type eq "仕事"].value | 糸 |
    | 優先言語 | 糸 |
    | 部署 | 糸 |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |
    | addresses[type eq "work"].フォーマット済み | 糸 |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | name.formatted | 糸 |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |
    | ロケール | 糸 |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から Envoy に同期されるグループ **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Envoy のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | externalId | 糸 |
    | members | リファレンス |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/envoy-tutorial"} -->
## Microsoft Entra ID で Envoy for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/envoy-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Envoy の間のシングル サインオンを構成する方法について説明します。

この記事では、Envoy と Microsoft Entra ID を統合する方法について説明します。 Envoy を Microsoft Entra ID と統合すると、次のことが可能になります。

- Envoy にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Envoy に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Envoy は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Envoy でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Envoy では、**SP** Initiated SSO がサポートされます。
- Envoy では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Envoy では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/envoy-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Envoy を追加する

Microsoft Entra ID への Envoy の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Envoy を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Envoy**」と入力します。
4. 結果のパネルから **Envoy** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Envoy に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Envoy に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Envoy での関連ユーザーとの間にリンク関係を確立する必要があります。

Envi MMIS に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Envoy SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Envoy テストユーザーの作成 - Envoy** の B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra で表現されている B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Envoy**&gt;**シングルサインオン**にブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.envoy.com/a/saml/auth/<company-ID-from-Envoy>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 値を取得するには、[Envoy クライアント サポート チーム](https://envoy.com/contact/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. **[Envoy のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Envoy SSO の構成

1. 別の Web ブラウザー ウィンドウで、Envoy 企業サイトに管理者としてサインインします。
2. **[統合**&gt;**すべての統合**に移動し、[**シングル サインオン**] で [SAML の**インストール**] を選択します。

    [Image: SAML 認証]
3. **[Enabled integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/有効な組み込み)** セクションに移動し、次の手順を実行します。

    [Image: シングル サインオン]

    注

    [HQ 場所 ID] の値は、アプリケーションによって自動的に生成されます。

    a. **[指紋]** テキストボックスに、証明書の**拇印**の値を貼り付けます。

    b。 Azure portal からコピーした**ログイン URL** を **[IDENTITY PROVIDER HTTP SAML URL](ID プロバイダーの HTTP SAML URL)** ボックスに貼り付けます。

    c. **保存** を選択します。

#### Envoy テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Envoy に作成します。

Envoy では、自動ユーザー プロビジョニングがサポートされています。自動ユーザー プロビジョニングの構成方法については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/envoy-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Envoy のサインオン URL にリダイレクトされます。
- Envoy のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Envoy] タイルを選択すると、このオプションは Envoy のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ephoto-dam-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に EPHOTO DAM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ephoto-dam-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EPHOTO DAM の間にシングル サインオンを構成する方法について説明します。

この記事では、EPHOTO DAM と Microsoft Entra ID を統合する方法について説明します。 EPHOTO DAM を Microsoft Entra ID と統合すると、次のことができます。

- EPHOTO DAM にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して EPHOTO DAM に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EPHOTO DAM でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EPHOTO DAM では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

### ギャラリーからの EPHOTO DAM の追加

Microsoft Entra ID への EPHOTO DAM の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に EPHOTO DAM を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**EPHOTO DAM**」と入力します。
4. 結果のパネルから **[EPHOTO DAM]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### EPHOTO DAM 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、EPHOTO DAM に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと EPHOTO DAM の関連ユーザーとの間にリンク関係を確立する必要があります。

EPHOTO DAM に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EPHOTO DAM の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EPHOTO DAM テストユーザーの作成** - Microsoft Entra におけるユーザーの表現にリンクされた B.Simon に対応する EPHOTO DAM のユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**EPHOTO DAM**&gt;**シングルサインオン**にアクセスする。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.ephoto.fr/simplesaml/module.php/saml/sp/metadata.php/<CUSTOMER_NAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.ephoto.fr/simplesaml/module.php/saml/sp/saml2-acs.php/<CUSTOMER_NAME>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.ephoto.fr`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[EPHOTO DAM クライアント サポート チーム](mailto:support-systeme@einden.fr)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[EPHOTO DAM のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EPHOTO DAM の SSO の構成

**EPHOTO DAM** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [EPHOTO DAM サポート チーム](mailto:support-systeme@einden.fr)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EPHOTO DAM のテスト ユーザーの作成

このセクションでは、EPHOTO DAM で Britta Simon というユーザーを作成します。 [EPHOTO DAM サポート チーム](mailto:support-systeme@einden.fr)と連携し、EPHOTO DAM プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる EPHOTO DAM サインオン URL にリダイレクトされます。
- EPHOTO DAM のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した EPHOTO DAM に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [EPHOTO DAM] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した EPHOTO DAM に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/eplatform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ePlatform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/eplatform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ePlatform の間のシングル サインオンを構成する方法について説明します。

この記事では、ePlatform と Microsoft Entra ID を統合する方法について説明します。 ePlatform を Microsoft Entra ID と統合すると、次のことが可能になります。

- ePlatform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ePlatform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ePlatform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ePlatform では、 **IDP** Initiated SSO がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ePlatform の追加

Microsoft Entra ID への ePlatform の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ePlatform を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ePlatform**」と入力します。
4. 結果パネルから **ePlatform** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ePlatform 用に Microsoft Entra シングル サインオンを構成およびテストする

**B.Simon** というテスト ユーザーを使用して、ePlatform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと ePlatform の関連ユーザーとの間にリンク関係を確立する必要があります。

ePlatform に対する Microsoft Entra SSO を構成およびテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ePlatform SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **ePlatformのテストユーザーを作成** - B.Simon に対応するユーザーを ePlatform で作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ePlatform]**&gt;**[シングル サインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. ePlatform アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、ePlatform アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | upn | user.userprincipalname |
8. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
9. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
10. [ **ePlatform のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ePlatform の SSO の構成

**ePlatform** 側でシングル サインオンを構成するには、**拇印の値**と、アプリケーション構成からコピーした適切な URL を [ePlatform サポート チーム](https://help.eplatform.co/hc/en-us)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ePlatform のテスト ユーザーの作成

このセクションでは、ePlatform で B.Simon というユーザーを作成します。 [ePlatform サポート チーム](https://help.eplatform.co/hc/en-us)と協力して、ePlatform プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [ePlatform] タイルを選択すると、SSO を設定した ePlatform に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/equifax-workforce-solutions-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Equifax Workforce Solutions を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/equifax-workforce-solutions-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Equifax Workforce Solutions の間でシングル サインオンを構成する方法について説明します。

この記事では、Equifax Workforce Solutions と Microsoft Entra ID を統合する方法について説明します。 Equifax Workforce Solutions と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Equifax Workforce Solutions へのアクセスを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Equifax Workforce Solutions に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Equifax Workforce Solutions でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Equifax Workforce Solutions では、**SP および IDP による SSO の開始**がサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Equifax Workforce Solutions を追加する

Microsoft Entra ID への Equifax Workforce Solutions の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Equifax Workforce Solutions を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション** に移動する。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Equifax Workforce Solutions**」と入力します。
4. 結果パネルから **Equifax Workforce Solutions を** 選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Equifax Workforce Solutions の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Equifax Workforce Solutions に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Equifax Workforce Solutions の関連ユーザーとの間にリンク関係を確立する必要があります。

Equifax Workforce Solutions に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Equifax Workforce Solutions の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Equifax Workforce Solutions のテスト ユーザーの作成** - Equifax Workforce Solutions で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザーをリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Equifax Workforce Solutions**&gt;**シングル サインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. [ **基本的な SAML 構成]** セクションで、 **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `http://federation.talx.com/adfs/services/trust`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://federation.talx.com/adfs/ls/`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://federation.talx.com/adfs/ls/`

    d. [ **リレー状態** ] テキスト ボックスに、次の値を入力します。 `rpid=https%3A%2F%2Ffederationx.talx.com%2FClaimsAwareHelper%2F`
7. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Equifax Workforce Solutions のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Equifax Workforce Solutions の SSO の構成

**Equifax Workforce Solutions** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Equifax Workforce Solutions サポート チーム](mailto:ws.pd.samlsupport@equifax.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Equifax Workforce Solutions のテスト ユーザーの作成

このセクションでは、Equifax Workforce Solutions で Britta Simon というユーザーを作成します。 Equifax Workforce Solutions サポート チーム  と連携して、Equifax Workforce Solutions プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Equifax Workforce Solutions のサインオン URL にリダイレクトされます。
- Equifax Workforce Solutions のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Equifax Workforce Solutions に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Equifax Workforce Solutions タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Equifax Workforce Solutions に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/equinix-federation-app-tutorial"} -->
## Microsoft Entra ID を使用して Equinix Federation App for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/equinix-federation-app-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-19
- Summary: Microsoft Entra ID と Equinix Federation App の間でシングル サインオンを構成する方法について説明します。

この記事では、Equinix フェデレーション アプリと Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Equinix Federation App を統合すると、次のことができます。

- Equinix Federation App にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Equinix Federation App に自動的にサインインできるように設定できます。
- 1 つの場所でアカウントを管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Equinix Federation App サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Equinix フェデレーション アプリでは、 **SP** によって開始される SSO がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Equinix Federation App の追加

Microsoft Entra ID への Equinix Federation App の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Equinix Federation App を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Equinix Federation App**」と入力します。
4. 結果パネルから **Equinix フェデレーション アプリ** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Equinix Federation App 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Equinix フェデレーション アプリに対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Equinix Federation App の関連ユーザーとの間にリンク関係を確立する必要があります。

Equinix Federation App で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Equinix フェデレーション アプリの SSO を構成**する - アプリケーション側でシングル サインオン設定を構成します。
    1. **Equinix Federation App のテスト ユーザーの作成** - Equinix Federation App で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Equinix Federation App]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customerprefix>customerportal.equinix.com`

    Note

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新する必要があります。 この値を取得するには [、Equinix フェデレーション アプリ クライアント サポート チーム](mailto:FederationSupport@equinix.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Equinix フェデレーション アプリのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Equinix Federation App の SSO の構成

**Equinix フェデレーション アプリ**側で単一 Sign-On を構成するには、[リンク](https://docs.equinix.com)に従ってください。

#### Equinix Federation App のテスト ユーザーの作成

このセクションでは、Equinix Federation App で Britta Simon というユーザーを作成します。 [Equinix フェデレーション アプリ サポート チーム](mailto:FederationSupport@equinix.com)と協力して、Equinix フェデレーション アプリ プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

Equinix Federation App のサインオン URL に直接移動し、そこからログイン フローを開始します。

Note

[ **このアプリケーションのテスト** ] リンクを使用するか、[Equinix フェデレーション アプリ] タイルを選択して Azure アプリケーションをテストしようとすると、IdP によって開始される SSO であり、Equinix では既定ではサポートされないため、機能しません。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/equisolve-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Equisolve を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/equisolve-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Equisolve 間のシングル サインオンを構成する方法について説明します。

この記事では、Equisolve と Microsoft Entra ID を統合する方法について説明します。 Equisolve を Microsoft Entra ID と統合すると、次のことが可能になります。

- Equisolve にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Equisolve に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Equisolve でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Equisolve では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

### ギャラリーからの Equisolve の追加

Microsoft Entra ID への Equisolve の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Equisolve を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Equisolve**」と入力します。
4. 結果パネルから **Equisolve** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Equisolve に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Equisolve に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Equisolve の関連ユーザー間にリンク関係を確立する必要があります。

Equisolve に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Equisolve SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Equisolve テスト ユーザーの作成** - Equisolve で B.Simon に対応するユーザーを作成し、このユーザーを Microsoft Entra の表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Equisolve**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://clients.equisolve.com/auth/saml/<ID>/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://clients.equisolve.com/auth/saml/<ID>/auth`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://clients.equisolve.com/auth/saml/<ID>/sign_in`

    b。 [ **ログアウト URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://clients.equisolve.com/auth/saml/<ID>/idp_sign_out`

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL、およびログアウト URL で更新してください。 これらの値を取得するには、 [Equisolve クライアント サポート チーム](mailto:help@equisolve.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Equisolve のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Equisolve の SSO の構成

**Equisolve** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Equisolve サポート チーム](mailto:help@equisolve.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Equisolve のテスト ユーザーの作成

このセクションでは、Equisolve で Britta Simon というユーザーを作成します。 [Equisolve サポート チーム](mailto:help@equisolve.com)と協力して、Equisolve プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる Equisolve サインオン URL にリダイレクトされます。
- Equisolve のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Equisolve に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Equisolve] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Equisolve に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/era-ehs-core-tutorial"} -->
## Microsoft Entra ID でシングル サインオンのERA_EHS_COREを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/era-ehs-core-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ERA_EHS_CORE の間のシングル サインオンを構成する方法について説明します。

この記事では、ERA\_EHS\_COREと Microsoft Entra ID を統合する方法について説明します。 ERA\_EHS\_CORE を Microsoft Entra ID と統合すると、次のことが可能になります。

- ERA\_EHS\_CORE へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ERA\_EHS\_CORE に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な ERA\_EHS\_CORE のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ERA\_EHS\_COREでは、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの ERA\_EHS\_CORE の追加

Microsoft Entra ID への ERA\_EHS\_CORE の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに ERA\_EHS\_CORE を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「ERA\_EHS\_CORE**」と入力します。
4. 結果パネルから **ERA\_EHS\_CORE** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ERA\_EHS\_CORE に Microsoft Entra SSO を構成およびテストするする

**B.Simon** というテスト ユーザーを使用して、ERA\_EHS\_COREに対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ERA\_EHS\_CORE の関連ユーザーとの間にリンク関係を確立する必要があります。

ERA\_EHS\_CORE に対する Microsoft Entra SSO を構成およびテストするするには、次のステップを実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ERA_EHS_CORE SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ERA_EHS_CORE テスト ユーザーの作成** - ERA\_EHS\_CORE で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ERA\_EHS\_CORE]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.era-env.com/era_ehs_core/<customername>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://www.era-env.com/era_ehs_core/<customername>/home/externallogin` |
    | `https://www.era-env.com/era_ehs_core/saml2/spxflow/Acs` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.era-env.com/era_ehs_core/<customername>/home/externallogin`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値ERA\_EHS\_CORE取得するには [、クライアント サポート チーム](mailto:tech_support@era-ehs.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ERA\_EHS\_CORE SSO の構成

**ERA\_EHS\_CORE**側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[サポート チームERA_EHS_CORE](mailto:tech_support@era-ehs.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ERA\_EHS\_CORE テスト ユーザーの作成

このセクションでは、ERA\_EHS\_CORE で Britta Simon というユーザーを作成します。 [ERA_EHS_CORE サポート チーム](mailto:tech_support@era-ehs.com)と協力して、ERA\_EHS\_CORE プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できるERA\_EHS\_COREサインオン URL にリダイレクトされます。
- ERA\_EHS\_CORE のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ERA\_EHS\_CORE] タイルを選択すると、このオプションはERA\_EHS\_COREサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/esalesmanagerremix-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に E Sales Manager Remix を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/esalesmanagerremix-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と E Sales Manager Remix の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、Microsoft Entra ID と E Sales Manager Remix を統合する方法について説明します。

Microsoft Entra ID と E Sales Manager Remix の統合には、次の利点があります。

- E Sales Manager Remix にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで自動的に E Sales Manager Remix にサインオン (シングル サインオン (SSO)) できるように設定できます。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「Microsoft [Entra ID でのアプリケーション アクセスとシングル サインオンとは」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)参照してください。

### 前提条件

E Sales Manager Remix と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション
- E Sales Manager Remix SSO が有効なサブスクリプション

注意

この記事の手順をテストするときは、運用環境を使用 *しないことを* お勧めします。

この記事の手順をテストするには、次の推奨事項に従います。

- 必要な場合を除き、運用環境を使用しないでください。
- Microsoft Entra 試用版環境がない場合は、 [1 か月の試用版を入手](https://azure.microsoft.com/pricing/free-trial/)できます。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンをテストします。

この記事で説明するシナリオは、次の 2 つの主要な構成要素で構成されています。

- ギャラリーからの E Sales Manager Remix の追加
- Microsoft Entra シングル サインオンの構成とテスト

### ギャラリーから E Sales Manager Remix を追加する

Microsoft Entra ID と E Sales Manager Remix の統合を構成するには、次の手順に従って、ギャラリーから管理対象 SaaS アプリのリストに E Sales Manager Remix を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**を参照します。
3. 新しいアプリケーションを追加するには、ウィンドウの上部にある **[新しいアプリケーション** ] を選択します。

    [Image: [新しいアプリケーション] ボタン]
4. 検索ボックスに「 **E Sales Manager Remix**」と入力し、結果一覧で **E Sales Manager Remix** を選択し、[ **追加**] を選択します。

    [Image: 結果一覧にある「E Sales Manager Remix」]

### Microsoft Entra シングル サインオンの構成とテスト

このセクションでは、"Britta Simon" というテスト ユーザーに基づいて、E Sales Manager Remix で Microsoft Entra のシングル サインオンを構成し、テストします。

シングル サインオンを機能させるには、Microsoft Entra ID ユーザーに対応する E Sales Manager Remix ユーザーが Microsoft Entra ID で識別される必要があります。 言い換えると、Microsoft Entra ユーザーと E Sales Manager Remix の同じユーザーの間で、リンク関係が確立されている必要があります。

E Sales Manager Remix で Microsoft Entra のシングル サインオンを構成してテストするには、次の 5 つのセクションで構成要素を完了します。

#### Microsoft Entra シングル サインオンの構成

次の手順に従って、Microsoft Entra で Azure AD のシングル サインオンを有効にし、E Sales Manager Remix アプリケーションでシングル サインオンを構成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**E Sales Manager Remix** アプリケーション統合ページを参照し、[**シングル サインオン**] を選択します。

    [Image: [シングル サインオン] リンク]
3. [ **シングル サインオン** ] ウィンドウの [ **シングル サインオン モード** ] ボックスで、[ **SAML ベースのサインオン**] を選択します。
4. **[E Sales Manager Remix のドメインと URL] で**、次の操作を行います。

    ある。 [ **サインオン URL** ] ボックスに、「 *https://&lt;Server-Based-URL&gt;/&lt;sub-domain&gt;/esales-pc」*の形式で URL を入力します。

    b。 [ **識別子** ] ボックスに、「 *https://&lt;Server-Based-URL&gt;/&lt;sub-domain&gt;/*」の形式で URL を入力します。

    c. この記事で後で使用するための **識別子** の値に注意してください。

    注意

    上記の値は実際の値ではありません。 実際のサインオン URL と識別子で値を更新してください。 値を取得するには、 [E Sales Manager Remix クライアント サポート チーム](mailto:esupport@softbrain.co.jp)にお問い合わせください。
5. [ **SAML 署名証明書**] で [ **証明書 (Base64)]** を選択し、証明書ファイルをコンピューターに保存します。
6. [ **他のすべてのユーザー属性を表示して編集** する] チェック ボックスをオンにし、 **emailaddress** 属性を選択します。

    [Image: [ユーザー属性] ウィンドウ]

    [ **属性の編集]** ウィンドウが開きます。
7. **名前空間**と名前の値をコピー**します**。 パターン *&lt;Namespace&gt;/&lt;Name&gt;* で値を生成し、この記事で後で使用できるように保存します。

    [Image: [属性の編集] ウィンドウ]
8. **E Sales Manager Remix 構成**で、**E Sales Manager Remix の設定を構成**を選択します。

    [ **サインオンの構成]** ウィンドウが開きます。
9. [ **クイック リファレンス** ] セクションで、サインアウト URL と SAML シングル サインオン サービス URL をコピーします。
10. **[保存] を選択します**。

    [Image: [保存] ボタン]
11. E Sales Manager Remix アプリケーションに管理者としてサインインします。
12. 右上の [ **管理者メニューへ**] を選択します。

    [Image: [管理者メニューへ] コマンド]
13. 左側のウィンドウで、 **システム設定**&gt;**外部システムとの相互運用**を選択します。

    [Image: 「システム設定」と「外部システムとの連携」リンク]
14. [ **外部システムとの連携** ] ウィンドウで、[ **SAML**] を選択します。

    [Image: 「外部システムとの連携」ウィンドウ]
15. **[SAML 認証設定**] で、次の操作を行います。

    [Image: [SAML 認証設定] セクション]

    ある。 [ **PC バージョン** ] チェック ボックスをオンにします。

    b。 [ **コラボレーションアイテム** ] セクションのドロップダウン リストで、[ **メール**] を選択します。

    c. [ **コラボレーション] 項目** ボックスに、先ほどコピーした要求値 (つまり、 **`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`**) を貼り付けます。

    d. [ **発行者 (エンティティ ID)]** ボックスに、先ほど **[E Sales Manager Remix のドメインと URL]** セクションからコピーした識別子の値を貼り付けます。

    え ダウンロードした証明書をアップロードするには、[ **ファイルの選択**] を選択します。

    f. **[ID プロバイダーのログイン URL**] ボックスに、先ほどコピーした SAML シングル サインオン サービスの URL を貼り付けます。

    ジー [ **IDENTITY Provider Logout URL** ] ボックスに、先ほどコピーしたサインアウト URL 値を貼り付けます。

    h. [ **設定完了] を選択します**。

ヒント

アプリを設定するときに、 [Azure portal](https://portal.azure.com) で前述の手順の簡潔なバージョンを読むことができます。 **Active Directory**&gt;**Enterprise Applications** セクションにアプリを追加したら、[**シングル サインオン**] タブを選択し、下部にある **[構成**] セクションの埋め込みドキュメントにアクセスします。 埋め込みドキュメント機能の詳細については、 [Microsoft Entra ID の埋め込みドキュメントを参照してください](https://go.microsoft.com/fwlink/?linkid=845985)。

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、テスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [ **新しいユーザー**&gt;**新しいユーザー**の作成] を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. [ **ユーザー プリンシパル名** ] フィールドに、 username@companydomain.extensionを入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. [ **パスワードの表示** ] チェック ボックスをオンにし、[ **パスワード** ] ボックスに表示される値を書き留めます。
    4. [ **確認と作成**] を選択します。
5. **作成**を選択します。

#### E Sales Manager Remix のテスト ユーザーの作成

1. E Sales Manager Remix アプリケーションに管理者としてサインオンします。
2. 右上のメニューから[ **管理者** メニューへ]を選択します。

    [Image: E Sales Manager Remix の構成]
3. **会社の設定**&gt;**部署と従業員の管理**を選択し、[**登録済みの従業員**] を選択します。

    [Image: [Employees registered](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/登録された従業員) タブ]
4. [ **新しい従業員の登録** ] セクションで、次の操作を行います。

    [Image: [新しい従業員の登録] セクション]

    ある。 [ **従業員名** ] ボックスに、ユーザーの名前 ( **Britta** など) を入力します。

    b。 その他の必須フィールドに入力します。

    c. SAML を有効にした場合、管理者はサインイン ページからサインインできません。 [ **管理者ログイン** ] チェック ボックスをオンにして、管理者サインイン特権をユーザーに付与します。

    d. [ **登録**] を選択します。
5. 今後、管理者としてサインインするには、管理者権限を持つユーザーとしてサインインし、右上の [ **管理者メニューへ**] を選択します。

    [Image: [管理者メニューへ] コマンド]

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、ユーザー Britta Simon に E Sales Manager Remix へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。 そのためには、次の手順を実行します。

[Image: ユーザー ロールを割り当てる]

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**を参照します。
3. アプリケーションの一覧 **で** 、 **E Sales Manager Remix** を選択します。

    [Image: E Sales Manager Remix のリンク]
4. 左側のウィンドウで、[ **ユーザーとグループ**] を選択します。

    [Image: [ユーザーとグループ] リンク]
5. [ **追加]** を選択し、[ **割り当ての追加** ] ウィンドウで [ **ユーザーとグループ**] を選択します。

    [Image: [割り当ての追加] ウィンドウ]
6. [ **ユーザーとグループ** ] ウィンドウの **[ユーザー** ] の一覧で、[ **Britta Simon**] を選択します。
7. [選択] ボタンを **選択** します。
8. [ **割り当ての追加]** ウィンドウで、[ **割り当て**] を選択します。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [E Sales Manager Remix] タイルを選択すると、自動的に E Sales Manager Remix アプリケーションにサインインします。

アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ethicspoint-incident-management-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に EthicsPoint Incident Management (EPIM) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ethicspoint-incident-management-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EthicsPoint Incident Management (EPIM) の間でシングル サインオンを構成する方法について説明します。

この記事では、EthicsPoint Incident Management (EPIM) と Microsoft Entra ID を統合する方法について説明します。 EthicsPoint Incident Management (EPIM) を Microsoft Entra ID と統合すると、次のことが可能になります。

- EthicsPoint Incident Management (EPIM) にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra ID アカウントで自動的に EthicsPoint Incident Management (EPIM) にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EthicsPoint Incident Management (EPIM) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- EthicsPoint Incident Management (EPIM) では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの EthicsPoint Incident Management (EPIM) の追加

Microsoft Entra ID への EthicsPoint Incident Management (EPIM) の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に EthicsPoint Incident Management (EPIM) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「EthicsPoint Incident Management (EPIM)」**と入力します。
4. 結果パネルから **EthicsPoint Incident Management (EPIM)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### EthicsPoint Incident Management (EPIM) 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、EthicsPoint Incident Management (EPIM) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと EthicsPoint Incident Management (EPIM) の関連ユーザーとの間にリンク関係を確立する必要があります。

EthicsPoint Incident Management (EPIM) に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EthicsPoint Incident Management (EPIM) SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **EthicsPoint Incident Management (EPIM) のテスト ユーザーの作成** - EthicsPoint Incident Management (EPIM) で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[EthicsPoint Incident Management (EPIM)]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.navexglobal.com/adfs/services/trust`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SERVER_NAME>.navexglobal.com/adfs/ls/`

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://<COMPANY_NAME>.navexglobal.com` |
    | `https://<COMPANY_NAME>.ethicspointvp.com` |
    |  |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、EthicsPoint Incident Management (EPIM) クライアント サポート チーム](https://www.navex.com/en-us/products/navex-ethics-compliance/ethicspoint-hotline-incident-management/) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **EthicsPoint Incident Management (EPIM) のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EthicsPoint Incident Management (EPIM) SSO の構成

**EthicsPoint Incident Management (EPIM)** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [EthicsPoint Incident Management (EPIM) サポート チーム](https://www.navex.com/en-us/products/navex-ethics-compliance/ethicspoint-hotline-incident-management/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EthicsPoint Incident Management (EPIM) のテスト ユーザーの作成

このセクションでは、EthicsPoint Incident Management (EPIM) で Britta Simon というユーザーを作成します。 [EthicsPoint Incident Management (EPIM) サポート チーム](https://www.navex.com/en-us/products/navex-ethics-compliance/ethicspoint-hotline-incident-management/)と協力して、EthicsPoint Incident Management (EPIM) プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる EthicsPoint Incident Management (EPIM) のサインオン URL にリダイレクトされます。
- EthicsPoint Incident Management (EPIM) のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [EthicsPoint Incident Management (EPIM)] タイルを選択すると、EthicsPoint Incident Management (EPIM) のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/etouches-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Aventri を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/etouches-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Aventri の間のシングル サインオンを構成する方法について説明します。

この記事では、Aventri と Microsoft Entra ID を統合する方法について説明します。 Aventri を Microsoft Entra ID と統合すると、次のことが可能になります。

- Aventri へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Aventri に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Aventri でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Aventri では、**SP** Initiated SSO がサポートされます
- Aventri を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスを拡張したものです。 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-aad)でセッション制御を適用する方法について説明します。

### ギャラリーから Aventri を追加する

Microsoft Entra ID への Aventri の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Aventri を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Aventri**」と入力します。
4. 結果のパネルから **[Aventri]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Aventri に Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、Aventri に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Aventri の関連ユーザーとの間にリンク関係を確立する必要があります。

Aventri に対する Microsoft Entra SSO を構成およびテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Aventri SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Aventri のテストユーザーを作成** - B.Simon に対応する Aventri 内のユーザーを作成し、このユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Aventri**&gt;**シングルサインオン**にブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://na-admin.eventscloud.com/saml/accounts/acs/<ACCOUNTID>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://na-admin.eventscloud.com/saml/accounts/sso/<ACCOUNTID>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子を使用して値を更新します。これについては、後で説明します。
6. Aventri アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Aventri アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Aventri のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Aventri SSO の構成

1. お使いのアプリケーション用に構成された SSO を取得するには、Aventri アプリケーションで次の手順を実行します。

    [Image: Aventri の構成]

    a. 管理者権限を使用して **Aventri** アプリケーションにサインインします。

    b。 **[SAML 構成]** に移動します。

    c. **[General Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般設定)** セクションで、Azure Portal からダウンロードした証明書をメモ帳で開き、内容をコピーして、[IDP metadata](IDP メタデータ) ボックスに貼り付けます。

    d. **[保存＆ステイ] ボタンを**選択します。

    e. [SAML **メタデータ** ] セクションの [メタデータの更新] ボタンを選択します。

    f. ページが開き、SSO が実行されます。 SSO が実行されたら、ユーザー名を設定できます。

    g. [Username](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー名) フィールドで、次の画像に示すように **emailaddress** を選びます。

    h. **[SP エンティティ ID]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[識別子]** ボックスに貼り付けます。

    一. **[SSO URL / ACS]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[サインオン URL]** ボックスに貼り付けます。

#### Aventri テスト ユーザーの作成

このセクションでは、Aventri で B.Simon というユーザーを作成します。 [Aventri クライアント サポート チーム](mailto:support@aventri.com)と連携し、Aventri プラットフォームにユーザーを追加してください。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Aventri] タイルを選択すると、SSO を設定した Aventri に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/etu-skillsims-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ETU Skillsims を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/etu-skillsims-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ETU Skillsims の間のシングル サインオンを構成する方法について説明します。

この記事では、ETU Skillsims と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ユーザー向けの ETU Learning Simulation Platform SAML SSO の起動。 ユーザーは、SAML 属性を使用して ETU で管理されます。 ETU を使用すると、イマーシブ学習とシミュレーションベースのトレーニングを大規模に行うことができます。 ETU Skillsims を Microsoft Entra ID と統合すると、次のことが可能になります。

- ETU Skillsims にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで ETU Skillsims に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で ETU Skillsims 向けに Microsoft Entra のシングル サインオンを構成してテストします。 ETU Skillsims は、**SP** と **IDP** によって開始されるシングル サインオンと、**Just In Time** ユーザー プロビジョニングの両方をサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を ETU Skillsims と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な ETU Skillsims のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから ETU Skillsims アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから ETU Skillsims を追加する

Microsoft Entra アプリケーション ギャラリーから ETU Skillsims を追加して、ETU Skillsims でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ETU Skillsims]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、URL を入力します。 `https://etu.skillsims.com/saml`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.skillsims.com/etu_saml/saml/SSO`
6. **SP** Initiated SSO を構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CustomerName>.skillsims.com/etu_saml/etuSaml.do` |
    | `https://<CustomerName>.skillsims.com/etu_saml/etuSaml.do?sid=<SimulationUID>` |

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、[ETU Skillsims クライアント サポート チーム](mailto:developers@etu.co)にお問い合わせください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
7. ETU Skillsims アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. その他に、ETU Skillsims アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 従業員ID | user.employeeid |
9. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[ETU Skillsims のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### ETU Skillsims の SSO を構成する

**ETU Skillsims** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [ETU Skillsims サポート チーム](mailto:developers@etu.co)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ETU Skillsims のテスト ユーザーを作成する

このセクションでは、B. Simon というユーザーを ETU Skillsims に作成します。 ETU Skillsims では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 ETU Skillsims にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ETU Skillsims のサインオン URL にリダイレクトされます。
- ETU Skillsims のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ETU Skillsims に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ETU Skillsims] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ETU Skillsims に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/euromonitor-passport-tutorial"} -->
## Microsoft Entra ID で Euromonitor International for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/euromonitor-passport-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-12
- Summary: Microsoft Entra ID と Euromonitor International の間でシングル サインオンを構成する方法について説明します。

この記事では、Euromonitor International と Microsoft Entra ID を統合する方法について説明します。 Euromonitor International と Microsoft Entra ID を統合すると、次のことができます。

- Euromonitor International にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Euromonitor International に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Euromonitor International サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Euromonitor Internationalは、**SP**が開始したSSOをサポートしています。

### ギャラリーからの Euromonitor International の追加

Microsoft Entra ID への Euromonitor International の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Euromonitor International を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Euromonitor International**」と入力します。
4. 結果パネルから **Euromonitor International** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Euromonitor International の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Euromonitor International に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Euromonitor International の関連ユーザーとの間にリンク関係を確立する必要があります。

Euromonitor International に対する Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Euromonitor International の SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップを実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Euromonitor International]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. **[SAML 署名証明書**] セクションで、[コピー] ボタンを選択して**アプリのフェデレーション メタデータ URL を**コピーし、コンピューターに保存します。

[Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

1. **アプリのフェデレーション メタデータ URL を**[Euromonitor International サポート チーム](mailto:passport.support@euromonitor.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。 応答を待ちます。
2. Euromonitor の応答から構成値を受け取った後、以下に進みます。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して Euromonitor サポートによって提供される URL を貼り付けます。 `https://auth.euromonitor.com/<CustomerID>`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を貼り付けます。 `https://auth.euromonitor.com/saml20/sp/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して Euromonitor サポートから提供される URL を貼り付けます。

    `https://login.euromonitor.com/Account/ExternalLogin?provider=<PROVIDER>&returnUrl=<PROVIDER>-signin-oidc&login_hint=<CustomerID>`

    Note

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 [Euromonitor International サポート チーム](mailto:passport.support@euromonitor.com) は、これらの値を提供します。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Euromonitor International アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Euromonitor International アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 都市 | user.city |
    | 部署 | user.department |
    | 国 | ユーザーの国 |
    | 電話番号 | ユーザー.電話番号 |
    | 字幕 | ユーザー.職名 |
    | 会社名 | user.companyname |
8. 特に要求がない限り、Euromonitor は以下の構成オプションを強くお勧めします。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

- **プロパティ**に移動します。 **[割り当てが必要]** を [いいえ] に設定します。

    [Image: [プロパティ] ページ。]

    [Image: 必須の割り当てオプション。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Euromonitor International の SSO の構成

[Euromonitor International サポート チーム](mailto:passport.support@euromonitor.com)は、すべてのアプリケーション側設定を構成および管理します。

#### Euromonitor International のテスト ユーザーの作成

このセクションでは、Euromonitor International で B.Simon というユーザーを作成します。 SSO を使用すると、Euromonitor サポート アクションなしで、アプリケーション側でテスト ユーザーを自己登録できます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる Euromonitor International Sign on URL にリダイレクトされます。
- Euromonitor International のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Euromonitor International に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Euromonitor International] タイルを選択すると、SSO を設定した Euromonitor International に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/eventfinity-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Eventfinity を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/eventfinity-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Eventfinity の間でシングル サインオンを構成する方法について説明します。

この記事では、Eventfinity と Microsoft Entra ID を統合する方法について説明します。 Eventfinity と Microsoft Entra ID を統合すると、次のことができます。

- Eventfinity にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Eventfinity に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「[アプリケーション アクセスと Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)でのシングル サインオンとは」を参照してください。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Eventfinity でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Eventfinity では、**SP と** IDP による SSO がサポートされます。
- Eventfinity を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用できます。 セッション制御は条件付きアクセスから拡張されます。 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)でセッション制御を適用する方法について説明します。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Eventfinity の追加

Microsoft Entra ID への Eventfinity の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Eventfinity を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Eventfinity**」と入力します。
4. 結果パネルから Eventfinity  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Eventfinity の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon**というテスト ユーザーを使用して、Eventfinity に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Eventfinity の関連ユーザーとの間にリンク関係を確立する必要があります。

Eventfinity に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Eventfinity SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Eventfinityテストユーザーを作成** - EventfinityでB.Simonに対応するユーザーを作成し、Microsoft Entraのユーザー表現にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Eventfinity**&gt;**シングルサインオン**へ移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーション **をIDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://auth.eventfinity.co/saml/<ID>/acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://auth.eventfinity.co/saml/<ID>/sso`

    手記

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値 [取得するには、Eventfinity クライアント サポート チーム](mailto:help@eventfinity.co) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Eventfinity SSO の構成

Eventfinity **側** シングル サインオンを構成するには、Eventfinity サポート チーム [に **アプリフェデレーション メタデータ URL**](mailto:help@eventfinity.co)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Eventfinity テスト ユーザーの作成

このセクションでは、Eventfinity で B.Simon というユーザーを作成します。 Eventfinity サポート チーム  と連携して、Eventfinity プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Eventfinity] タイルを選択すると、SSO を設定した Eventfinity に自動的にサインインします。 アクセス パネルの詳細については、「[アクセス パネル](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)の概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/everbridge-tutorial"} -->
## Microsoft Entra ID で EverBridge for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/everbridge-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-17
- Summary: Microsoft Entra IDと EverBridge の間でシングル サインオンを構成する方法について説明します。

この記事では、EverBridge と Microsoft Entra ID を統合する方法について説明します。 EverBridge と Microsoft Entra ID を統合すると、次のことができます。

- EverBridge にアクセスできるユーザーをMicrosoft Entra IDで制御できます。
- ユーザーが自分のMicrosoft Entra アカウントを使用して EverBridge に自動的にサインインできるようにします。 このアクセス制御はシングル サインオン (SSO) と呼ばれます。
- Azure ポータルを使用して、1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオンを使用する Everbridge サブスクリプション。

### シナリオの説明

この記事では、テスト環境でシングル サインオンMicrosoft Entra構成し、テストします。

- Everbridge では、IDP によって開始される SSO がサポートされます。
- EverBridge では、SP によって開始される SSO がサポートされます。

### ギャラリーから EverBridge を追加する

Microsoft Entra IDへの EverBridge の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に EverBridge を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「EverBridge**」と入力します。
4. 結果パネルから **EverBridge** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細については](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)を参照してください。

### EverBridge の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Microsoft Entra SSO を EverBridge 用に構成およびテストします。 SSO を機能させるには、Microsoft Entra ユーザーと EverBridge の関連ユーザーとの間にリンク関係を確立する必要があります。

EverBridge Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**を構成する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra のテスト ユーザーの作成** - Microsoft Entra のシングル サインオンを B.Simon でテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EverBridge SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EverBridge テストユーザーを作成する** - B.Simon の EverBridge における対応ユーザーを作成し、それを Microsoft Entra 上のユーザーとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**EverBridge** アプリケーション統合ページに移動します。**管理**セクションを見つけて、**シングルサインオン** を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]

    注意

    Azure ポータルと EverBridge ポータルの両方で、アプリケーションを管理者ポータル*もしくは*メンバーポータルとして構成します。 上記の URL は、実際のデータではなく、一般的なパターンを示しています。 &lt;&gt;内の文字列を実際の識別子で更新する必要があります。 これらの値を取得するには、EverBridge Manager ポータルで SSO 構成を確認します。

    a. [ **識別子** ] ボックスに、パターンに従う URL を入力します。 `https://sso.everbridge.net/<API_Name>`

    b。 [ **応答 URL** ] ボックスに、パターンに従う URL を入力します。

    - アカウント レベルの SSO を構成する場合は、 `https://manager.everbridge.net/saml/SSO/<API_Name>/alias/defaultAlias`
    - 組織レベルの SSO を構成する場合は、 `https://manager.everbridge.net/saml/SSO/<API_Name>/<Organization_ID>/alias/defaultAlias`

    注意

    上記の URL は、実際のデータではなく、一般的なパターンを示しています。 &lt;&gt;内の文字列を実際の識別子で更新する必要があります。 これらの値を取得するには、Everbridge Manager ポータルで SSO 構成を確認します。
5. **EverBridge** アプリケーションを **EverBridge メンバー ポータル**として構成するには、[**基本的な SAML 構成]** セクションで、次の手順に従います。

    - IDP 開始モードでアプリケーションを構成する場合は、次の手順に従います。

        a. [ **識別子** ] ボックスに、パターンに従う URL を入力します。 `https://sso.everbridge.net/<API_Name>/<Organization_ID>`

        b。 [ **応答 URL** ] ボックスに、パターンに従う URL を入力します。 `https://member.everbridge.net/saml/SSO/<API_Name>/<Organization_ID>/alias/defaultAlias`
    - SP 開始モードでアプリケーションを構成する場合は、[ **追加の URL の設定** ] を選択し、次の手順に従います。

        a. [ **サインオン URL** ] ボックスに、パターンに従う URL を入力します `https://manager.everbridge.net/saml/login/<API_Name>`

        注意

        上記の URL は、実際のデータではなく、一般的なパターンを示しています。 &lt;&gt;の文字列を実際の値で更新する必要があります。 これらの値を取得するには、Everbridge Manager ポータルで SSO 構成を確認します。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して **フェデレーション メタデータ XML** をダウンロードします。 それを自分のコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **EverBridge のセットアップ** ] セクションで、要件に必要な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entraのテスト ユーザーを作成して割り当てる

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Everbridge の SSO を構成する

**EverBridge マネージャー ポータル** アプリケーションとして **EverBridge** の SSO を構成するには、次の手順に従います。

1. 別の Web ブラウザー ウィンドウで、アカウントまたは組織管理者として EverBridge Manager ポータルにサインインします。
2. 左側のメニューから、[ **設定]** メニューを選択します。 [ **セキュリティ**] で、 **マネージャー ポータルの [単一 Sign-On]** を選択します。

    [Image: シングル サインオンを構成する方法を示すスクリーンショット。]

    a. [ **名前** ] ボックスに、この設定の名前を入力します。 たとえば、自分の会社名などです。

    b。 [ **API 名]** ボックスに、API の名前を入力します。 これは、SSO 構成の一意の識別子です。

    c. ドロップダウン リストから **ID プロバイダー** を選択します。 見つからない場合は、[その他] を選択し、名前を指定します。

    d. 使用する **サービス プロバイダー証明書** を選択します。 3072 ビット長の証明書をお勧めします。

    e. [ **アップロード]** を選択して、IDP からダウンロードしたメタデータ ファイルをアップロードします。

    f. **[SAML Identity Location](SAML ID の場所)** で、**[Identity is in the NameIdentifier element of the Subject statement](ID を Subject ステートメントの NameIdentifier 要素にする)** をオンにします。

    g. [ **ID プロバイダーのログイン URL** ] ボックスに、コピーした **ログイン URL** の値を貼り付けます。

    h. **サービス プロバイダーによって開始された要求バインド**の場合は、[**HTTP リダイレクト**] を選択します。

    一. **保存** をクリックします。

#### Everbridge テスト ユーザーを作成する

EverBridge でテスト ユーザーを作成します。 ユーザーの **SSO ユーザー ID** フィールドが IDP 内のユーザーの識別子と一致していることを確認します。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra シングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した EverBridge に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [EverBridge] タイルを選択すると、SSO を設定した EverBridge に自動的にサインインします。 マイ アプリの詳細については、「[マイ アプリのイントロダクション](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/evercate-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Evercate を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/evercate-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-09
- Summary: ユーザー アカウントを Evercate に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Evercate ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Evercate](https://evercate.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Evercate でユーザーを作成します。
- アクセスが不要になった場合は、Evercate のユーザーを削除します。
- Microsoft Entra ID と Evercate の間でユーザー属性の同期を維持する。
- Evercate でグループとグループ メンバーシップをプロビジョニングします。
- Evercate に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者アクセス許可がある Evercate のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Evercate の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように Evercate を構成する

1. Evercate に管理者としてログインし、上部メニューの **[設定]** を選択します。
2. [設定] で、[ **詳細設定] -&gt; [Microsoft Entra ID の接続**] に移動します。
3. **理解しました、Microsoft Entra ID を接続する** ボタンを選択してプロセスを開始します。 [Image: Microsoft Entra ID を接続する]
4. これで、AD の管理者としてサインインする必要がある Microsoft のサインイン ページが表示されます。

    サインインに使用する Microsoft ユーザーには、次の条件が必要となります。

    - 「エンタープライズ アプリケーション」へのアクセス許可を持つ管理者であること。
    - 個人アカウントではなく AD ユーザーであること。

    [Image: サインイン]
5. [同意する] を選択**する前に、[組織に代わって同意**する] をオンにします。 [Image: 同意を提供する]

    注

    同意チェックボックスをオフにした場合、すべてのユーザーが最初のサインイン時に同様のダイアログを受け取ります。 接続後に組織に同意する方法については、「Azure でのアプリケーションの構成」セクションにある以下を参照してください。
6. Microsoft Entra ID への接続が正常に設定されると、Evercate で有効にする AD 機能を構成できます。
7. **[設定] -&gt; [詳細設定] -&gt; [Microsoft Entra ID の接続**] に移動します。プロビジョニングを有効にするために必要なトークン (Microsoft Entra ID から有効) が表示され、Evercate アカウントのシングル サインオンを許可するチェック ボックスをオンにすることができます。
8. トークンをコピーして保存します。 この値は、Evercate アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** \*] フィールドに入力されます。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Evercate を追加する

Microsoft Entra アプリケーション ギャラリーから Evercate を追加して、Evercate へのプロビジョニングの管理を開始します。 SSO のために Evercate を以前に設定している場合は、同じアプリケーションを使用できます。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Evercate への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて Evercate 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Evercate に対する自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で [ **Evercate**] を選択します。

    [Image: アプリケーションの一覧の Evercate のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Evercate テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Evercate に接続できることを確認します。 接続に失敗した場合は、Evercate アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Evercate に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Evercate のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Evercate API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Evercate で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |  |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から Evercate に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Evercate のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Evercate で必須 |
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
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/evergreen-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Evergreen を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/evergreen-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Evergreen の間のシングル サインオンを構成する方法について説明します。

この記事では、Evergreen と Microsoft Entra ID を統合する方法について説明します。 Evergreen を Microsoft Entra ID と統合すると、次のことが可能になります。

- Evergreen にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Evergreen に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Evergreen サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Evergreen では、**SP および IDP**によるイニシエート SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Evergreen の追加

Microsoft Entra ID への Evergreen の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Evergreen を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Evergreen**」と入力します。
4. 結果パネルから **[Evergreen]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Evergreen 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Evergreen に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Evergreen での関連ユーザーとの間にリンク関係を確立する必要があります。

Evergreen 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Evergreen SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Evergreen テスト ユーザーの作成** - Evergreen で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra における B.Simon のユーザープロファイルにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Evergreen**&gt;**シングルサインオン**に移動してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.tryevergreen.com/saml/acs`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.tryevergreen.com/saml/sso`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには、 [Evergreen クライアント サポート チーム](mailto:support@tryevergreen.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Evergreen のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Evergreen の SSO の構成

**Evergreen** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Evergreen サポート チーム](mailto:support@tryevergreen.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Evergreen のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Evergreen に作成します。 [Evergreen サポート チーム](mailto:support@tryevergreen.com)と協力して、Evergreen プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できるエバーグリーン サインオン URL にリダイレクトされます。
- Evergreen のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Evergreen に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Evergreen] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Evergreen に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/evernote-tutorial"} -->
## Microsoft Entra ID で Evernote for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/evernote-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Evernote の間のシングル サインオンを構成する方法について説明します。

この記事では、Evernote と Microsoft Entra ID を統合する方法について説明します。 Evernote を Microsoft Entra ID と統合すると、次のことが可能になります。

- Evernote にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Evernote に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Evernote でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Evernote では、**SP および IDP による SSO** がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Evernote の追加

Evernote と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に Evernote をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Evernote**」と入力します。
4. 結果パネルから **Evernote** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Evernote 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Evernote に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Evernote での関連ユーザーとの間にリンク関係を確立する必要があります。

Evernote 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Evernote SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Evernote テストユーザーの作成** - B.Simon に対応するユーザーを Evernote に作成して、Microsoft Entra で表現されたユーザーにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップを実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Evernote]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://www.evernote.com/saml2`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.evernote.com/Login.action`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **署名**オプションを変更するには、[**編集**] ボタンを選択して **[SAML 署名証明書**] ダイアログを開きます。

    [Image: [編集] ボタンが選択されている [S A M L 署名証明書] ダイアログを示すスクリーンショット。]

    a. **[署名オプション]** で **[SAML 応答とアサーションへの署名]** オプションを選択します。

    b。 **[保存] を選択する**
9. [ **Evernote のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Evernote の SSO の構成

1. Web ブラウザーの別のウィンドウで、Evernote の企業サイトに管理者としてサインインします
2. **[管理コンソール] に**移動します

    [Image: 管理コンソール]
3. **[管理コンソール**] から [**セキュリティ**] に移動し、[**シングル サインオン**] を選択します

    [Image: SSO-Setting]
4. 次の値を構成します。

    [Image: 証明書の設定]

    a. **SSO を有効にする:** SSO は既定で有効になっています (SSO 要件を **削除するには、[シングル サインオンの無効化** ] を選択します)

    b。 **[ログイン URL**] の値を **[SAML HTTP 要求 URL**] ボックスに貼り付けます。

    c. Microsoft Entra ID からダウンロードした証明書をメモ帳で開き、"BEGIN CERTIFICATE" や "END CERTIFICATE" などの内容をコピーして **、X.509 証明書** ボックスに貼り付けます。

    d.[**変更の保存]** を選択する

#### Evernote テスト ユーザーの作成

Microsoft Entra ユーザーが Evernote にサインインできるようにするには、そのユーザーを Evernote にプロビジョニングする必要があります。 Evernote の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. Evernote の企業サイトに管理者としてサインインします。
2. **[管理コンソール] を選択します**。

    [Image: 管理コンソール]
3. **[管理コンソール**] から、[**ユーザーの追加]** に移動します。

    [Image: [ユーザーの追加] が選択されている [ユーザー] メニューを示すスクリーンショット。]
4. [**電子メール**] ボックスに**チーム メンバーを追加**し、ユーザー アカウントのメール アドレスを入力し、[招待] を選択**します。**

    [Image: Add-testUser]
5. 招待が送信された後、招待を承諾するメールが Microsoft Entra アカウント所有者に届きます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Evernote のサインオン URL にリダイレクトされます。
- Evernote のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Evernote に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Evernote] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Evernote に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/evidence-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Evidence.com を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/evidence-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Evidence.com の間にシングル サインオンを構成する方法について説明します。

この記事では、Evidence.com と Microsoft Entra ID を統合する方法について説明します。 Evidence.com を Microsoft Entra ID と統合すると、次のことができます。

- Evidence.com にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Evidence.com に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Evidence.com でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Evidence.com では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから Evidence.com を追加する

Microsoft Entra ID への Evidence.com の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Evidence.com を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックス **に「Evidence.com** 」と入力します。
4. 結果パネルから **Evidence.com** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Evidence.com 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Evidence.com に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Evidence.com の関連ユーザーの間にリンク関係を確立する必要があります。

Evidence.com に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Evidence.com SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Evidence.com でテストユーザーを作成** - Evidence.com の B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Evidence.com**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次の手順に従います。

    1. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<yourtenant>.evidence.com`
    2. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<yourtenant>.evidence.com`
    3. [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<your tenant>.evidence.com/?class=UIX&proc=Login`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、応答 URL でこれらの値を更新します。 これらの値 Evidence.com 取得するには [、クライアント サポート チーム](https://my.axon.com/s/contactsupport) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Evidence.com のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Evidence.com の SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として Evidence.com テナントにサインインし、[ **管理** ] タブに移動します。
2. **[Agency Single Sign On] を選択します**。
3. **[SAML ベースのシングル サインオン] を選択します**。
4. Azure portal に表示されている **Microsoft Entra 識別子**、 **ログイン URL** 、 **ログアウト URL** の値を、Evidence.com の対応するフィールドにコピーします。
5. ダウンロードした証明書 (Base64) ファイルをメモ帳で開き、その内容をクリップボードにコピーして、[ **セキュリティ証明書** ] ボックスに貼り付けます。
6. Evidence.com の構成を保存します。

#### Evidence.com のテスト ユーザーの作成

Microsoft Entra ユーザーがサインインできるようにするには、ユーザーを Evidence.com アプリケーションにプロビジョニングする必要があります。 このセクションでは、Evidence.com 内で Microsoft Entra ユーザー アカウントを作成する方法について説明します。

**Evidence.com でユーザー アカウントをプロビジョニングするには:**

1. Web ブラウザー ウィンドウで、Evidence.com 企業サイトに管理者としてサインインします。
2. [ **管理** ] タブに移動します。
3. [ **ユーザーの追加] を選択します**。
4. [ **追加** ] ボタンを選択します。
5. 追加されたユーザーの **メール アドレス** は、アクセス権を付与する Microsoft Entra のユーザーのユーザー名と一致する必要があります。 ユーザー名と電子メール アドレスが組織内で同じ値でない場合は、Azure portal の **Evidence.com &gt; 属性 &gt; シングル サインオン** セクションを使用して、Evidence.com に送信される nameidenitifer を電子メール アドレスに変更できます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Evidence.com サインオン URL にリダイレクトされます。
- Evidence.com のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Evidence.com] タイルを選択すると、このオプションは Evidence.com サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/evovia-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Evovia を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/evovia-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Evovia の間のシングル サインオンを構成する方法について説明します。

この記事では、Evovia と Microsoft Entra ID を統合する方法について説明します。 Evovia を Microsoft Entra ID と統合すると、次のことが可能になります。

- Evovia へのアクセス権を持つユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Evovia に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Evovia でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Evovia では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Evovia を追加する

Microsoft Entra ID への Evovia の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Evovia を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;に移動し、**エンタープライズ アプリ**&gt;**新しいアプリケーション**に進みます。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Evovia**」と入力します。
4. 結果パネルから **Evovia** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Evovia の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Evovia に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Evovia の関連ユーザーとの間にリンク関係を確立する必要があります。

Evovia 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Evovia の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Evovia のテスト ユーザーの作成** - Evovia で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Evovia]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://secure.evovia.com/saml/acs`
    2. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://secure.evovia.com`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Evovia SSO の構成

**Evovia** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Evovia サポート チーム](mailto:support@evovia.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Evovia のテスト ユーザーの作成

このセクションでは、Evovia テストユーザーの作成 で Britta Simon というユーザーを作成します。 [Evovia サポート チーム](mailto:support@evovia.com)と協力して、Evovia プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Evovia のサインオン URL にリダイレクトされます。
- Evovia のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Evovia] タイルを選択すると、このオプションは Evovia のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/exactcare-sso-tutorial"} -->
## Microsoft Entra ID で ExactCare SSO for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/exactcare-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ExactCare SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、ExactCare SSO と Microsoft Entra ID を統合する方法について説明します。 ExactCare SSO を Microsoft Entra ID と統合すると、次のことが可能になります。

- ExactCare SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで ExactCare SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ExactCare SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ExactCare SSO では、**SP** Initiated SSO がサポートされます

### ギャラリーからの ExactCare SSO の追加

Microsoft Entra ID への ExactCare SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ExactCare SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「ExactCare SSO**」と入力します。
4. 結果パネルから **ExactCare SSO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ExactCare SSO に Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、ExactCare SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ExactCare SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

ExactCare SSO で Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ExactCare SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **ExactCare SSO テスト ユーザーの作成** - ExactCare SSO で B.Simon に対応するユーザーを作成し、それを Microsoft Entra 内のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**ExactCare SSO**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.exactcarepharmacy.com/self-schedule/api/saml-response?idp=azure-sp`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.exactcarepharmacy.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [ExactCare SSO クライアント サポート チーム](mailto:help@exactcarepharmacy.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **ExactCare SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ExactCare SSO の構成

**ExactCare SSO** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [ExactCare SSO サポート チーム](mailto:help@exactcarepharmacy.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ExactCare SSO のテスト ユーザーの作成

このセクションでは、ExactCare SSO で B.Simon というユーザーを作成します。 [ExactCare SSO サポート チーム](mailto:help@exactcarepharmacy.com)と協力して、ExactCare SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [ExactCare SSO] タイルを選択すると、SSO を設定した ExactCare SSO に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/exceed-ai-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Exceed.ai を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/exceed-ai-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Exceed.ai の間のシングル サインオンを構成する方法について説明します。

この記事では、Exceed.ai と Microsoft Entra ID を統合する方法について説明します。 Exceed.ai を Microsoft Entra ID と統合すると、次のことが可能になります。

- Exceed.ai へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Exceed.ai に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Exceed.ai でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Exceed.ai では、**SP** Initiated SSO がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Exceed.ai の追加

Microsoft Entra ID への Exceed.ai の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Exceed.ai を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Exceed.ai**」と入力します。
4. 結果パネルから **[Exceed.ai]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Exceed.ai 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Exceed.ai での Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Exceed.ai での関連ユーザーとの間にリンク関係を確立する必要があります。

Exceed.ai で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Exceed.ai の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Exceed.ai のテスト ユーザーの作成** - Exceed.ai で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Exceed.ai]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://prod.exceed.ai/saml/sp/discovery`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Exceed.ai の SSO の構成

**Exceed.ai** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Exceed.ai サポート チーム](mailto:support@exceed.ai)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Exceed.ai のテスト ユーザーの作成

このセクションでは、Exceed.ai で Britta Simon というユーザーを作成します。 [Exceed.ai サポート チーム](mailto:support@exceed.ai)と連携して、Exceed.ai プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Exceed.ai サインオン URL にリダイレクトされます。
- Exceed.ai のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Exceed.ai] タイルを選択すると、このオプションは Exceed.ai サインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/excelity-hcm-tutorial"} -->
## Microsoft Entra ID を使用して Excelity HCM for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/excelity-hcm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Excelity HCM 間のシングル サインオンを構成する方法について説明します。

この記事では、Excelity HCM と Microsoft Entra ID を統合する方法について説明します。 Excelity HCM を Microsoft Entra ID と統合すると、次のことが可能になります。

- Excelity HCM へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Excelity HCM に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Excelity HCM でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Excelity HCM では、 **IDP** によって開始される SSO がサポートされます。

### ギャラリーから Excelity HCM を追加する

Microsoft Entra ID への Excelity HCM の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧にExcelity HCM を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Excelity HCM**」と入力します。
4. 結果パネルから **Excelity HCM** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Excelity HCM に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、Excelity HCM に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Excelity HCM の関連ユーザーとの間にリンク関係を確立する必要があります。

Excelity HCM 用に Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Excelity HCM SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Excelity HCM のテストユーザーを作成し、Microsoft Entra のユーザー表現にリンクされる B.Simon の対応ユーザーを Excelity HCM で作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Excelity HCM**&gt;**シングルサインオン** にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Excelity HCM アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Excelity HCM アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 国 | ユーザーの国 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Excelity HCM SSO の構成

**Excelity HCM** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Excelity HCM サポート チーム](mailto:HCM.Support@ceridian.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Excelity HCM テスト ユーザーの作成

このセクションでは、Excelity HCM で Britta Simon というユーザーを作成します。 [Excelity HCM サポート チーム](mailto:HCM.Support@ceridian.com)と協力して、Excelity HCM プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Excelity HCM に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Excelity HCM] タイルを選択すると、SSO を設定した Excelity HCM に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/excelityglobal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ExcelityGlobal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/excelityglobal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ExcelityGlobal の間のシングル サインオンを構成する方法について説明します。

この記事では、ExcelityGlobal と Microsoft Entra ID を統合する方法について説明します。 ExcelityGlobal を Microsoft Entra ID と統合すると、次のことが可能になります。

- ExcelityGlobal にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで ExcelityGlobal に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

ExcelityGlobal と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ExcelityGlobal でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者に加え、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができる。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ExcelityGlobal では、 **IDP** によって開始される SSO がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ExcelityGlobal の追加

Microsoft Entra ID への ExcelityGlobal の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ExcelityGlobal を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ExcelityGlobal**」と入力します。
4. 結果パネルから **ExcelityGlobal** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ExcelityGlobal に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ExcelityGlobal に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、ExcelityGlobal での関連ユーザーとの間にリンク関係を確立する必要があります。

ExcelityGlobal で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ExcelityGlobal SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ExcelityGlobal のテスト ユーザーの作成** - ExcelityGlobal で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ExcelityGlobal]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** ページで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **識別子** |
    | --- |
    | **運用環境の場合** : `https://ess.excelityglobal.com` |
    | **サンドボックス環境の場合** : `https://s6.excelityglobal.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | **運用環境の場合** : `https://ess.excelityglobal.com/ACS` |
    | **サンドボックス環境の場合** : `https://s6.excelityglobal.com/ACS` |
6. ExcelityGlobal アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **nameidentifier** は **user.userprincipalname** にマップされています。 ExcelityGlobal アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: ExcelityGlobal アプリケーションの画像を示すスクリーンショット。]
7. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書を編集するスクリーンショット。]
8. [ **SAML 署名証明書** ] セクションで、 **拇印** をコピーしてコンピューターに保存します。

    [Image: サムプリントの値のコピーを示すスクリーンショット。]
9. [ **ExcelityGlobal のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: スクリーンショットは、構成を適切な U R L にコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ExcelityGlobal の SSO の構成

**ExcelityGlobal** 側でシングル サインオンを構成するには、アプリケーション構成から**コピーした拇印の値**と適切な URL を [ExcelityGlobal サポート チーム](https://www.dayforce.com/contact)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### ExcelityGlobal のテスト ユーザーの作成

このセクションでは、ExcelityGlobal で Britta Simon というユーザーを作成します。 [ExcelityGlobal サポート チーム](https://www.dayforce.com/contact)と協力して、ExcelityGlobal プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ExcelityGlobal に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [ExcelityGlobal] タイルを選択すると、SSO を設定した ExcelityGlobal に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/exium-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Exium を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/exium-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-09
- Summary: ユーザー アカウントを Exium に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Exium ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [Exium](https://exium.net/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Exium でユーザーを作成する
- アクセスが不要になった場合に Exium のユーザーを削除する
- Microsoft Entra ID と Exium 間でユーザー属性の同期を維持する
- Exium でグループとグループ メンバーシップをプロビジョニングする

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 管理者アクセス許可を持つ Exium のユーザー アカウント。
- Microsoft Entra シークレット トークンを生成するための Exium のワークスペース。 [ここで](https://service.exium.net/sign-up)新しいワークスペースを作成できます。
- [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/exium-tutorial) を有効にする必要があります。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Exium の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Exium によるプロビジョニングをサポートするように Microsoft Entra ID を構成する

1. [Exium ワークスペース](https://service.exium.net/sign-in)にログインします。
2. [Exium ワークスペース [プロファイルの設定\]](https://service.exium.net/sign-in) ページで、[ **SCIM 構成]** タブに移動します。
3. **SCIM 2.0 ベアラー トークンをコピーします**。 この値は、Exium アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: Exium SCIM 構成]
4. Exium の**テナント URL** は `https://subapi.exium.net/scim` です。 この値は、Exium アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Exium を追加する

Microsoft Entra アプリケーション ギャラリーから Exium を追加して、Exium へのプロビジョニングの管理を開始します。 SSO のために Exium を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Exium への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Exium に対する自動ユーザー プロビジョニングを構成するには、以下の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**を閲覧する

     ブレード
3. アプリケーションの一覧で [ **Exium**] を選択します。

    [Image: アプリケーションの一覧の [Exium] リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Exium テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Exium に接続できることを確認します。 接続に失敗した場合は、Exium アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Exium に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Exium のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Exium API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | displayName | 糸 |  |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
    | タイムゾーン | 糸 |  |
    | ユーザータイプ | 糸 |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から Exium に同期されるグループ **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Exium のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | members | リファレンス |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/exium-tutorial"} -->
## Microsoft Entra ID で Exium for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/exium-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Exium の間でシングル サインオンを構成する方法について説明します。

この記事では、Exium と Microsoft Entra ID を統合する方法について説明します。 Exium と Microsoft Entra ID を統合すると、次のことができます。

- Exium にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Exium に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Exium でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Exium では、 **SP** Initiated SSO がサポートされます。
- Exium では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/exium-provisioning-tutorial)。

### ギャラリーからの Exium の追加

Microsoft Entra ID への Exium の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Exium を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Exium**」と入力します。
4. 結果パネルから **[Exium** ] を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Exium の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Exium に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Exium の関連ユーザーとの間にリンク関係を確立する必要があります。

Exium に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Exium SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Exium テスト ユーザーの作成 - Exium** で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Exium]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://subapi.exium.net/saml/<WORKSPACE_ID>/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://subapi.exium.net/saml/<WORKSPACE_ID>/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://service.exium.net/sign-in`

    手記

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Exium クライアント サポート チーム](mailto:support@exium.net) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Exium SSO の構成

1. Exium 企業サイトに管理者としてサインインします。
2. **管理コンソール**で、[**会社のプロファイル]** パネルを選択します。

    管理コンソールの[Image: screenshot for admin console]screenshot for admin consoleのスクリーンショット
3. **プロファイル**で、[**SSO 設定] を**選択して**編集します**。
4. **[SSO 設定]** セクションで次の手順を実行します。

    SSO 設定[Image: screenshot for SSO Settings]screenshot for SSO Settingsのスクリーンショット

    a. ドロップダウンから[SSO Type as **Microsoft Entra ID**]\(**SSO の種類**\) を選択します。

    b。 **アプリ フェデレーション メタデータ URL** の値を **SAML 2.0 IDP メタデータ URL** フィールドに貼り付けます。

    c. **SAML 2.0 SSO URL** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。

    d. **SAML 2.0 SP エンティティ ID** の値をコピーし、[**基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスにこの値を貼り付けます。

    e. [ **更新] を**選択します。

#### Exium テスト ユーザーの作成

1. Exium 企業サイトに管理者としてサインインします。
2. **[ユーザー管理] -&gt; [ユーザー**] に移動し、[**ユーザーの追加]** を選択します。

    テスト ユーザー[Image: screenshot for create test user]screenshot for create test userの作成に関するスクリーンショット
3. 次のページに必要なフィールドを入力し、[ **保存]** を選択します。

    [Image: [保存] ボタンを使用したテスト ユーザー フィールドの作成のスクリーンショット]

手記

Exium では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Exium のサインオン URL にリダイレクトされます。
- Exium のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Exium] タイルを選択すると、このオプションは Exium のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/expensein-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ExpenseIn を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/expensein-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ExpenseIn の間にシングル サインオンを構成する方法について学習します。

この記事では、ExpenseIn と Microsoft Entra ID を統合する方法について説明します。 ExpenseIn を Microsoft Entra ID と統合すると、次のことができます。

- ExpenseIn にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って ExpenseIn に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ExpenseIn でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ExpenseIn では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーからの ExpenseIn の追加

Microsoft Entra ID への ExpenseIn の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに ExpenseIn を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**ExpenseIn**」と入力します。
4. 結果ウィンドウで **[ExpenseIn]** を選択し、アプリケーションを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ExpenseIn 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ExpenseIn で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ExpenseIn の関連ユーザーとの間にリンク関係を確立する必要があります。

ExpenseIn で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**して、ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成**し、B.Simon を使用して Microsoft Entra シングル サインオンをテストします。
    2. **B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます** 。
2. **ExpenseIn の SSO の構成**- アプリケーション側で SSO 設定を構成します。
    1. **ExpenseIn テスト ユーザーを作成**し、Microsoft Entra のユーザー表現にリンクするように、ExpenseIn に B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ExpenseIn** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.expensein.com/saml`
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、[ **ダウンロード** ] を選択して **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[ExpenseIn のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ExpenseIn の SSO の構成

1. 別の Web ブラウザー ウィンドウで、ExpenseIn 企業サイトに管理者としてサインインします
2. ページの上部にある [ **管理者** ] を選択し、[ **シングル サインオン** ] に移動し、[ **プロバイダーの追加]** を選択します。

    [Image: [管理者] タブ、[シングル サインオン - プロバイダー] ページ、選択されている [プロバイダーの追加] を示すスクリーンショット。]
3. **[New Identity Provider] (新しい ID プロバイダー)** ポップアップで、次の手順に従います。

    [Image: 値が入力された [Edit Identity Provider](ID プロバイダーの編集) ポップアップを示すスクリーンショット。]

    ある。 **[プロバイダー名]** ボックスに「Azure」などの名前を入力します。

    b。 **[Allow Provider Intitated Sign-On](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロバイダーによって開始されるサインオンを許可する)** で **[Yes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/はい)** を選択します。

    c. **[ターゲット URL]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    d. **[発行者]** テキスト ボックスに、**[Microsoft Entra 識別子]** の値を貼り付けます。

    え 証明書 (Base64) をメモ帳で開き、その内容をコピーして **[証明書]** ボックスに貼り付けます。

    f. **を選択して**を作成します。

#### ExpenseIn のテスト ユーザーの作成

Microsoft Entra ユーザーが ExpenseIn にサインインできるようにするには、そのユーザーを ExpenseIn にプロビジョニングする必要があります。 ExpenseIn では、プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. 管理者として ExpenseIn にサインインします。
2. ページの上部にある [ **管理者** ] を選択し、[ **ユーザー** ] に移動し、[ **新しいユーザー**] を選択します。

    [Image: [管理者] タブと、[新しいユーザー] が選択されている [ユーザーの管理] ページを示すスクリーンショット。]
3. **[詳細]** ポップアップで、次の手順に従います。

    [Image: ExpenseIn の構成]

    ある。 [ **名** ] テキスト ボックスに、ユーザーの名を **B** などと入力します。

    b。 **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (例: **Simon**)。

    c. [ **電子メール** ] テキスト ボックスに、ユーザーの電子メール ( `B.Simon@contoso.com`など) を入力します。

    d. **を選択して**を作成します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ExpenseIn のサインオン URL にリダイレクトされます。
- ExpenseIn のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ExpenseIn に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ExpenseIn] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ExpenseIn に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/expensemepro-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ExpenseMe Pro (by Inlogik) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/expensemepro-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-03
- Summary: Microsoft Entra ID と ExpenseMe Pro (by Inlogik) の間にシングル サインオンを構成する方法について説明します。

この記事では、ExpenseMe Pro (by Inlogik) と Microsoft Entra ID を統合する方法について説明します。 ExpenseMe Pro (by Inlogik) と Microsoft Entra ID を統合すると、次のことができます。

- ExpenseMe Pro (by Inlogik) にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って ExpenseMe Pro (by Inlogik) に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ExpenseMe Pro (by Inlogik) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ExpenseMe Pro (by Inlogik) では、**SP** と **IDP** によって開始される SSO がサポートされます。

### ギャラリーから ExpenseMe Pro (by Inlogik) を追加する

Microsoft Entra ID への ExpenseMe Pro (by Inlogik) の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ExpenseMe Pro (by Inlogik) を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**ExpenseMe Pro (by Inlogik)**」と入力します。
4. 結果パネルから **[ExpenseMe Pro (by Inlogik)]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ExpenseMe Pro (by Inlogik) 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使って、ExpenseMe Pro (by Inlogik) に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと ExpenseMe Pro (by Inlogik) の関連ユーザーとの間にリンク関係を確立する必要があります。

ExpenseMe Pro (by Inlogik) に対して Microsoft Entra SSO を構成するには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ExpenseMe Pro (by Inlogik) の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ExpenseMe Pro (by Inlogik) におけるテストユーザーの作成** - ExpenseMe Pro (by Inlogik) において B.Simon の対応ユーザーを作成して、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**ExpenseMe Pro (by Inlogik)**&gt;**シングルサインオン**を閲覧します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://secure.inlogik.com/<COMPANYNAME>` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://secure.inlogik.com/<COMPANYNAME>/saml/acs` のパターンを使用して URL を入力します
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL (オプション)]** テキスト ボックスに、`https://secure.inlogik.com/<COMPANYNAME>` のパターンを使用して URL を入力します。

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[ExpenseMe Pro (by Inlogik) サポート チーム](https://www.inlogik.com/contact)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ExpenseMe Pro (by Inlogik) の SSO を構成する

**ExpenseMe Pro (by Inlogik)** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [ExpenseMe Pro (by Inlogik) サポート チーム](https://www.inlogik.com/contact)に送る必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### ExpenseMe Pro (by Inlogik) のテスト ユーザーを作成する

このセクションでは、ExpenseMe Pro (by Inlogik) で B.Simon というユーザーを作成します。 [ExpenseMe Pro (by Inlogik) サポート チーム](https://www.inlogik.com/contact)と連携して、ExpenseMe Pro (by Inlogik) プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ExpenseMe Pro (by Inlogik) のサインオン URL にリダイレクトされます。
- ExpenseMe Pro (by Inlogik) のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ExpenseMe Pro (by Inlogik) に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで ExpenseMe Pro (by Inlogik) タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ExpenseMe Pro (by Inlogik) に自動的にサインインされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/expensify-tutorial"} -->
## Microsoft Entra ID で Expensify for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/expensify-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Expensify の間のシングル サインオンを構成する方法について説明します。

この記事では、Expensify と Microsoft Entra ID を統合する方法について説明します。 Expensify を Microsoft Entra ID と統合すると、次のことができます。

- Expensify にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Expensify に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Expensify でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Expensify では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Expensify の追加

Microsoft Entra ID への Expensify の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Expensify を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Expensify**」と入力します。
4. 結果パネルから **Expensify** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Expensify 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Expensify に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Expensify での関連ユーザーとの間にリンク関係を確立する必要があります。

Expensify に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Expensify SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Expensify テスト ユーザーを作成する** - Expensify で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Expensify** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://www.expensify.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.expensify.com/authentication/saml/loginCallback?domain=<yourdomain>`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.expensify.com/authentication/saml/login`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには [、Expensify クライアント サポート チーム](mailto:help@expensify.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **メタデータ XML** を見つけて **[ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Expensify のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Expensify SSO の構成

Expensify で SSO を有効にするには、まずアプリケーションで **Domain Control** を有効にする必要があります。 追加のサポートについては、 [Expensify クライアント サポート チーム](mailto:help@expensify.com)と協力してください。 Domain Control を有効にしたら、以下の手順に従います。

[Image: シングル サインオンの構成]

1. Expensify アプリケーションにサインオンします。
2. 左側のパネルで、[設定] にカーソルを合わせ、[ドメイン] を選択し、[ **SAML**] に移動します。
3. **[SAML ログイン**] オプションを **[有効]** に切り替えます。
4. Microsoft Entra ID からダウンロードしたフェデレーション メタデータをメモ帳で開き、内容をコピーして、[ **ID プロバイダー メタデータ** ] ボックスに貼り付けます。

#### Expensify のテスト ユーザーの作成

このセクションでは、Expensify で B.Simon (例: B.Simon@contoso.com) という同じユーザーを作成します。 メンバーを招待するためのガイド [をここで](https://community.expensify.com/discussion/4869/how-to-manage-domain-members) 確認するか、 [Expensify クライアント サポート チーム](mailto:help@expensify.com) と協力して Expensify プラットフォームにユーザーを追加してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Expensify のサインオン URL にリダイレクトされます。
- Expensify のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Expensify] タイルを選択すると、このオプションは Expensify のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/experience-cloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Experience Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/experience-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Experience Cloud の間でシングル サインオンを構成する方法について説明します。

この記事では、Experience Cloud と Microsoft Entra ID を統合する方法について説明します。 Experience Cloud を Microsoft Entra ID と統合すると、次のことが可能になります。

- Experience Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Experience Cloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Experience Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Experience Cloud では、**SP および IDP によって開始された SSO** がサポートされます。

### ギャラリーから Experience Cloud を追加する

Microsoft Entra ID への Experience Cloud の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Experience Cloud を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに「**Experience Cloud**」と入力します。
4. 結果パネルから **Experience Cloud** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Experience Cloud に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Experience Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Experience Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Experience Cloud の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Experience Cloud の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Experience Cloud のテストユーザーを作成** - Experience Cloud で B.Simon の対応ユーザーを作成し、Microsoft Entra におけるユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Experience Cloud]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<cluster>.medallia.com/sso/<company>` |
    | `https://<cluster>.medallia.ca/sso/<company>` |
    | `https://<cluster>.medallia.eu/sso/<company>` |
    | `https://<cluster>.medallia.au/sso/<company>` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<cluster>.medallia.com/sso/<company>/logonSubmit.do` |
    | `https://<cluster>.medallia.ca/sso/<company>/logonSubmit.do` |
    | `https://<cluster>.medallia.eu/sso/<company>/logonSubmit.do` |
    | `https://<cluster>.medallia.au/sso/<company>/logonSubmit.do` |
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<cluster>.medallia.com/sso/<company>` |
    | `https://<cluster>.medallia.ca/sso/<company>` |
    | `https://<cluster>.medallia.eu/sso/<company>` |
    | `https://<cluster>.medallia.au/sso/<company>` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Experience Cloud クライアント サポート チーム](mailto:support@medallia.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Experience Cloud のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Experience Cloud SSO の構成

**Experience Cloud** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Experience Cloud サポート チーム](mailto:support@medallia.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Experience Cloud のテスト ユーザーの作成

このセクションでは、Experience Cloud で B.Simon というユーザーを作成します。 [Experience Cloud サポート チーム](mailto:support@medallia.com)と協力して、Experience Cloud プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Experience Cloud のサインオン URL にリダイレクトされます。
- Experience Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Experience Cloud に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Experience Cloud] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Experience Cloud に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/expiration-reminder-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの有効期限リマインダーを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/expiration-reminder-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Expiration Reminder の間でシングル サインオンを構成する方法について確認します。

この記事では、Expiration Reminder と Microsoft Entra ID を統合する方法について説明します。 Expiration Reminder と Microsoft Entra ID を統合すると、次のことができます。

- Expiration Reminder にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Expiration Reminder に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Expiration Reminder でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Expiration Reminder では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Expiration Reminder の追加

Microsoft Entra ID への Expiration Reminder の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Expiration Reminder を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Expiration Reminder**」と入力します。
4. 結果パネルから **[Expiration Reminder]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Expiration Reminder に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Expiration Reminder に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Expiration Reminder での関連ユーザーとの間にリンク関係を確立する必要があります。

Expiration Reminder に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Expiration Reminder の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Expiration Reminder テスト ユーザーの作成** - Expiration Reminder 内で B.Simon に相当するユーザーを作成し、Microsoft Entra でのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**有効期限リマインダー**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.expirationreminder.net/account/sso`
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **有効期限アラームの設定** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Expiration Reminder SSO の構成

**Expiration Reminder** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** と、アプリケーション構成からコピーした適切な URL を [Expiration Reminder サポート チーム](mailto:support@expirationreminder.net)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Expiration Reminder テスト ユーザーを作成する

このセクションでは、Expiration Reminder で Britta Simon というユーザーを作成します。 [Expiration Reminder サポート チーム](mailto:support@expirationreminder.net)と協力して、Expiration Reminder プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Expiration Reminder のサインオン URL にリダイレクトされます。
- Expiration Reminder のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Expiration Reminder] タイルを選択すると、このオプションは Expiration Reminder のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/explanation-based-auditing-system-tutorial"} -->
## Microsoft Entra ID でシングル サインオン Explanation-Based 監査システムを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/explanation-based-auditing-system-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Explanation-Based Auditing System の間でシングル サインオンを構成する方法について説明します。

この記事では、Explanation-Based 監査システムと Microsoft Entra ID を統合する方法について説明します。 Explanation-Based Auditing System と Microsoft Entra ID の統合には、次のメリットがあります。

- Explanation-Based Auditing System にアクセスできるユーザーを Microsoft Entra ID で管理できます。
- ユーザーが自分の Microsoft Entra アカウントで Explanation-Based Auditing System に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Explanation-Based Auditing System でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Explanation-Based 監査システムでは、 **SP** によって開始される SSO がサポートされます
- Explanation-Based 監査システムでは、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### Explanation-Based Auditing System をギャラリーから追加する

Microsoft Entra ID への Explanation-Based Auditing System の統合を構成するには、管理対象の SaaS アプリの一覧にギャラリーから Explanation-Based Auditing System を追加する必要があります。

**ギャラリーから監査システム Explanation-Based 追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 ** 監査システムExplanation-Based 入力し**、結果パネルから ** 監査システムExplanation-Based** 選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: Explanation-Based 監査システムが結果一覧に表示される]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Explanation-Based Auditing System で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Explanation-Based Auditing System 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Explanation-Based Auditing System に対して Microsoft Entra のシングル サインオンを構成およびテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Explanation-Based 監査システムのシングル サインオン** の構成 - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Explanation-Based Auditing System のテスト ユーザーの作成** - Explanation-Based Auditing System で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Explanation-Based Auditing System で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;** Auditing System** のアプリケーション統合ページExplanation-Based に移動し、**シングル サインオン** を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: Explanation-Based 監査システムドメインとURLのシングルサインオン情報]

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://ebas.maizeanalytics.com`
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Explanation-Based Auditing System のシングル サインオンの構成

** 監査システム側Explanation-Based** シングル サインオンを構成するには、監査システム ** サポート チーム**に[アプリのフェデレーション メタデータ URLExplanation-Based](mailto:support@maizeanalytics.com) 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Explanation-Based Auditing System のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Explanation-Based Auditing System に作成します。 Explanation-Based Auditing System では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Explanation-Based Auditing System にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Explanation-Based 監査システム] タイルを選択すると、SSO を設定した Explanation-Based 監査システムに自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/exponenthr-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ExponentHR を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/exponenthr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ExponentHR の間にシングル サインオンを構成する方法について説明します。

この記事では、ExponentHR と Microsoft Entra ID を統合する方法について説明します。 ExponentHR と Microsoft Entra ID を統合すると、次のことができます。

- ExponentHR にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って ExponentHR に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ExponentHR でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ExponentHR では、 **SP** Initiated SSO がサポートされます。
- ExponentHR では、 **WS-Fed プロトコルが** サポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ExponentHR の追加

Microsoft Entra ID への ExponentHR の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ExponentHR を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ExponentHR**」と入力します。
4. 結果パネルから **ExponentHR** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ExponentHR 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ExponentHR に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと ExponentHR の関連ユーザーとの間にリンク関係を確立する必要があります。

ExponentHR に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ExponentHR SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ExponentHR のテストユーザーを作成する - ExponentHR において B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザーを表すものにリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ExponentHR**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.exponenthr.com/service/saml/login`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ExponentHR SSO の構成

**ExponentHR** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[ExponentHR サポート チーム](mailto:support@exponenthr.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ExponentHR テスト ユーザーの作成

このセクションでは、ExponentHR で B.Simon というユーザーを作成します。 [ExponentHR サポート チーム](mailto:support@exponenthr.com)と協力して、ExponentHR プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる ExponentHR サインオン URL にリダイレクトされます。
- ExponentHR のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ExponentHR] タイルを選択すると、このオプションは ExponentHR サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/exterro-legal-grc-software-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Exterro Legal GRC Software Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/exterro-legal-grc-software-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Exterro Legal GRC Software Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Exterro Legal GRC Software Platform と Microsoft Entra ID を統合する方法について説明します。 Exterro Platform は、Exterro のすべての電子情報開示および情報ガバナンス ソリューションを 1 つにまとめ、ビジネス ニーズの拡大に合わせて新しい Exterro アプリケーションを簡単に追加できるようにします。 Exterro Legal GRC Software Platform と Microsoft Entra ID を統合すると、次のことが可能になります。

- Exterro Legal GRC Software Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Exterro Legal GRC Software Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Exterro Legal GRC Software Platform 用の Microsoft Entra シングル サインオンを構成してテストします。 Exterro Legal GRC Software Platform は、**SP** と **IDP** によって開始されるシングル サインオンを両方ともサポートしています。

### [前提条件]

Microsoft Entra ID を Exterro Legal GRC Software Platform と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Exterro Legal GRC Software Platform のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Exterro Legal GRC Software Platform アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Exterro Legal GRC Software Platform を追加する

Microsoft Entra アプリケーション ギャラリーから Exterro Legal GRC Software Platform を追加して、Exterro Legal GRC Software Platform でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Exterro Legal GRC Software Platform]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant_id>.exterro.net/exterrosso`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant_id>.exterro.net/exterrosso/saml/SSO`
6. **SP** Initiated SSO を構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<tenant_id>.exterro.net/exterrosso/saml/` |
    | `https://<tenant_id>.<domain>` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Exterro Legal GRC Software Platform のクライアント サポート チーム](mailto:support@exterro.com)にお問い合わせください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Exterro Legal GRC Software Platform のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Exterro Legal GRC Software Platform の SSO を構成する

**Exterro Legal GRC Software Platform** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[Exterro Legal GRC Software Platform サポート チーム](mailto:support@exterro.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Exterro Legal GRC Software Platform のテスト ユーザーを作成する

このセクションでは、Exterro Legal GRC Software Platform で Britta Simon というユーザーを作成します。 [Exterro Legal GRC Software Platform サポート チーム](mailto:support@exterro.com)の協力を得て、Exterro Legal GRC Software Platform プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Exterro Legal GRC Software Platform のサインオン URL にリダイレクトされます。
- Exterro Legal GRC Software Platform のサインオン URL に直接移動して、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Exterro Legal GRC Software Platform に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Exterro Legal GRC Software Platform] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Exterro Legal GRC Software Platform に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ezofficeinventory-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に EZOfficeInventory を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ezofficeinventory-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EZOfficeInventory の間のシングル サインオンを構成する方法について説明します。

この記事では、EZOfficeInventory と Microsoft Entra ID を統合する方法について説明します。 EZOfficeInventory を Microsoft Entra ID と統合すると、次のことができます。

- EZOfficeInventory にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで EZOfficeInventory に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EZOfficeInventory でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EZOfficeInventory では、**SP** Initiated SSO がサポートされます。
- EZOfficeInventory では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの EZOfficeInventory の追加

Microsoft Entra ID への EZOfficeInventory の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に EZOfficeInventory を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**EZOfficeInventory**」と入力します。
4. 結果のパネルから **EZOfficeInventory** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### EZOfficeInventory 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、EZOfficeInventory に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと EZOfficeInventory の関連ユーザーとの間にリンク関係を確立する必要があります。

EZOfficeInventory での Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EZOfficeInventory の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EZOfficeInventory のテスト ユーザーの作成** - EZOfficeInventory で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**EZOfficeInventory**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.ezofficeinventory.com/users/sign_in`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[EZOfficeInventory クライアント サポート チーム](mailto:support@ezofficeinventory.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. EZOfficeInventory アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、EZOfficeInventory アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | first\_name | User.givenname |
    | last\_name | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[EZOfficeInventory のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EZOfficeInventory の SSO の構成

1. 別の Web ブラウザーのウィンドウで、EZOfficeInventory 企業サイトに管理者としてサインインします。
2. ページの右上隅にある [**プロファイル**] を選択し、[**設定]**&gt;**[アドオン**] に移動します。

    [Image: [Add Ons](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アドオン) アクションが選択されている [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) ページを示すスクリーンショット。]
3. 下へスクロールして **[SAML Integration](SAML 統合)** セクションに移動し、次の手順に従います。

    [Image: EZOfficeInventory の構成]

    a. **[Enabled](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/有効)** オプションをオンにします。

    b。 **[ID プロバイダー URL]** テキスト ボックスに、前にコピーした**ログイン URL** 値を貼り付けます。

    c. Base64 でエンコードされた証明書をメモ帳で開き、その内容をコピーして **[Identity Provider Certificate](ID プロバイダー証明書)** ボックスに貼り付けます。

    d. **[Login Button Text](ログイン ボタン テキスト)** ボックスに、ログイン ボタンのテキストを入力します。

    e. **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに「**first\_name**」と入力します。

    f. **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに「**last\_name**」と入力します。

    g. **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メール)** ボックスに「**email**」と入力します。

    h. **[EZOfficeInventory Role By default](既定の EZOfficeInventory ロール)** オプションから要件に従って自分のロールを選択します。

    一. **[更新]** を選択します。

#### EZOfficeInventory のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを EZOfficeInventory に作成します。 EZOfficeInventory では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 EZOfficeInventory にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる EZOfficeInventory のサインオン URL にリダイレクトされます。
- EZOfficeInventory のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで EZOfficeInventory タイルを選択すると、このオプションは EZOfficeInventory のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ezra-coaching-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Ezra Coaching を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ezra-coaching-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Ezra Coaching の間のシングル サインオンを構成する方法について説明します。

この記事では、Ezra Coaching と Microsoft Entra ID を統合する方法について説明します。 Ezra Coaching を Microsoft Entra ID と統合すると、次のことが可能になります。

- Ezra Coaching にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Ezra Coaching に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Ezra Coaching でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Ezra Coaching では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Ezra Coaching の追加

Microsoft Entra ID への Ezra Coaching の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Ezra Coaching を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Ezra Coaching**」と入力します。
4. 結果のパネルから **[Ezra Coaching]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Ezra Coaching 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Ezra Coaching に対して Microsoft Entra SSO を構成およびテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Ezra Coaching の関連ユーザーとの間にリンク関係を確立する必要があります。

Ezra Coaching に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Ezra Coaching SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Ezra Coaching のテスト ユーザーの作成** - Ezra Coaching で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID] **&gt;** [エンタープライズ アプリ] **&gt;** [Ezra Coaching] **&gt;** [シングル サインオン] **に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.helloezra.com/`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Ezra Coaching の設定]** セクションで、要件に応じて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Ezra Coaching SSO の構成

**Ezra Coaching** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Ezra Coaching サポート チーム](mailto:help@helloezra.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Ezra Coaching のテスト ユーザーの作成

このセクションでは、Ezra Coaching で Britta Simon というユーザーを作成します。 [Ezra Coaching サポート チーム](mailto:help@helloezra.com)と連携して、Ezra Coaching プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Ezra Coaching のサインオン URL にリダイレクトされます。
- Ezra Coaching のサインオン URL に直接移動し、そこからログイン フローを開始します。

IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Ezra Coaching に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Ezra Coaching] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Ezra Coaching に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ezrentout-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に EZRentOut を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ezrentout-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EZRentOut. の間のシングル サインオンを構成する方法について説明します。

この記事では、EZRentOut と Microsoft Entra ID を統合する方法について説明します。 EZRentOut を Microsoft Entra ID と統合すると、次のことが可能になります。

- EZRentOut にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで EZRentOut に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EZRentOut でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EZRentOut では、**SP** Initiated SSO がサポートされます。
- EZRentOut では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの EZRentOut の追加

Microsoft Entra ID への EZRentOut の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に EZRentOut を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**EZRentOut**」と入力します。
4. 結果のパネルから **[EZRentOut]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### EZRentOut 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、EZRentOut に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するためには、Microsoft Entra ユーザーと EZRentOut の関連ユーザーとの間にリンク関係を確立する必要があります。

EZRentOut に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EZRentOut SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EZRentOut のテスト ユーザーの作成** - EZRentOut で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[EZRentOut]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.ezrentout.com/users/sign_in`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[EZRentOut クライアント サポート チーム](mailto:support@ezrentout.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. EZRentOut アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、EZRentOut アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | first\_name | User.givenname |
    | last\_name | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[EZRentOut の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EZRentOut の SSO の構成

**EZRentOut** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [EZRentOut サポート チーム](mailto:support@ezrentout.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EZRentOut のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを EZRentOut に作成します。 EZRentOut では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 EZRentOut にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる EZRentOut サインオン URL にリダイレクトされます。
- EZRentOut のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで EZRentOut タイルを選択すると、このオプションは EZRentOut のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/f5-big-ip-headers-easy-button"} -->
## Microsoft Entra ID でシングル サインオン用にヘッダー ベースの SSO 用に F5 の BIG-IP Easy Button を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/f5-big-ip-headers-easy-button
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と F5 のヘッダーベース SSO 用 BIG-IP Easy Button の間で SSO を構成する方法について説明します。

この記事では、F5 と Microsoft Entra ID を統合する方法について説明します。 F5 を Microsoft Entra ID と統合すると、次のことが可能になります。

- F5 へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して F5 に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

注

F5 BIG-IP APM [今すぐ購入](https://azuremarketplace.microsoft.com/en-us/marketplace/apps/f5-networks.f5-big-ip-best?tab=Overview)。

### シナリオの説明

このシナリオでは、 **HTTP 承認ヘッダー** を使用して、保護されたコンテンツへのアクセスを管理する従来のレガシ アプリケーションについて説明します。

従来のアプリケーションには、Microsoft Entra ID との直接的な統合をサポートする最新のプロトコルがありません。 アプリケーションは最新化できますが、コストがかかり、慎重な計画が必要であり、潜在的なダウンタイムのリスクが生じます。 代わりに、プロトコル遷移によって従来のアプリケーションと最新の ID コントロール プレーンとの間のギャップを橋渡しするために、F5 BIG IP Application Delivery Controller (ADC) を使用します。

アプリケーションの前に BIG-IP があると、Microsoft Entra の事前認証とヘッダーベースの SSO でサービスをオーバーレイできるため、アプリケーションの全体的なセキュリティ態勢が大幅に向上します。

注

組織は、 [Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)を使用して、この種類のアプリケーションにリモート アクセスすることもできます。

### シナリオのアーキテクチャ

このシナリオの SHA ソリューションは次のもので構成されています。

**アプリケーション:** 公開されたサービス BIG-IP Microsoft Entra SHA によって保護されます。

**Microsoft Entra ID:** BIG-IP に対するユーザー資格情報、条件付きアクセス、SAML ベースの SSO の検証を担当するセキュリティ アサーション マークアップ言語 (SAML) ID プロバイダー (IdP)。 SSO を介して、Microsoft Entra ID により、必要なセッション属性が BIG-IP に提供されます。

**BIG-IP:** バックエンド アプリケーションに対してヘッダー ベースの SSO を実行する前に認証を SAML IdP に委任して、アプリケーションにリバース プロキシと SAML サービス プロバイダー (SP) を渡します。

このシナリオの SHA では、SP と IdP によって開始されたフローの両方がサポートされます。 次の図は、SP Initiated フローを示しています。

[Image: セキュリティで保護されたハイブリッド アクセス - SP によって開始されるフローのスクリーンショット。]

| 手順 | 説明 |
| --- | --- |
| 1 | ユーザーがアプリケーション エンドポイント (BIG-IP) に接続する |
| 2 | BIG-IP APM アクセス ポリシーは、ユーザーを Microsoft Entra ID (SAML IdP) にリダイレクトする |
| 3 | Microsoft Entra ID によって、ユーザーの事前認証と、条件付きアクセス ポリシーの適用が行われる |
| 4 | ユーザーが BIG-IP (SAML SP) にリダイレクトされ、発行された SAML トークンを使用して SSO が実行される |
| 5 | BIG-IP によって、Microsoft Entra 属性がアプリケーションへの要求のヘッダーとして挿入される |
| 6 | アプリケーションが要求を承認し、ペイロードを返す |

### 前提条件

以前の BIG-IP エクスペリエンスは必要ありませんが、次のものが必要です。

- Microsoft Entra ID 無料サブスクリプション (またはそれ以上)。
- 既存の BIG-IP または [Azure に BIG-IP Virtual Edition (VE) をデプロイします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)。
- 次のいずれかの F5 BIG-IP ライセンス SKU。

    - F5 BIG-IP® Best バンドル。
    - F5 BIG-IP Access Policy Manager™ (APM) スタンドアロン ライセンス。
    - 既存の BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP Access Policy Manager™ (APM) アドオン ライセンス
    - 90日間の BIG-IP 全機能付き [評価版ライセンス](https://www.f5.com/trial/big-ip-trial.php)。
- オンプレミスディレクトリから Microsoft Entra ID に [同期される](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis) ユーザー ID。
- Microsoft Entra アプリケーション管理者 [のアクセス許可](https://learn.microsoft.com/ja-jp/azure/active-directory/users-groups-roles/directory-assign-admin-roles#application-administrator)を持つアカウント。
- HTTPS 経由でサービスを発行するための [SSL Web 証明書](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide#ssl-profile) 、またはテスト中に既定の BIG-IP 証明書を使用します。
- 既存のヘッダー ベースのアプリケーション、またはテスト用 [の単純な IIS ヘッダー アプリを設定](https://learn.microsoft.com/ja-jp/previous-versions/iis/6.0-sdk/ms525396%28v=vs.90%29) します。

### BIG-IP の構成方法

このシナリオで使用する BIG-IP は、テンプレートを使用した 2 つの方法や高度な構成を含め、さまざまな方法で構成できます。 この記事では、Easy ボタン テンプレートを提供する最新のガイド付き構成 16.1 について説明します。 Easy Button を使用すると、管理者は、Microsoft Entra ID と BIG-IP の間を行き来して SHA のためにサービスを有効にする必要がなくなります。 デプロイとポリシー管理は、APM のガイド付き構成ウィザードと Microsoft Graph との間で直接処理されます。 この充実した BIG-IP APM と Microsoft Entra ID の統合により、アプリケーションでは確実に ID フェデレーション、SSO、Microsoft Entra 条件付きアクセスを迅速かつ容易にサポートできるため、管理オーバーヘッドが軽減されます。

注

このガイド全体で参照されている文字列または値の例はすべて、実際の環境に合わせて置き換える必要があります。

### Easy Button を登録する

クライアントまたはサービスが Microsoft Graph にアクセスする前に、[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)によって信頼されている必要があります。

この最初の手順では、Graph への **Easy Button** アクセスを承認するために使用されるテナント アプリの登録を作成します。 これらのアクセス許可により、BIG-IP は、発行されたアプリケーションの SAML SP インスタンスと SAML IdP としての Microsoft Entra ID との間に信頼を確立するために必要な構成をプッシュできます。

1. アプリケーション管理者権限を持つアカウントを使用して [Azure portal](https://portal.azure.com/) にサインインします。
2. 左側のナビゲーション ウィンドウで、 **Microsoft Entra ID サービスを** 選択します。
3. [管理] で、[ **アプリの登録**&gt;**新しい登録**] を選択します。
4. `F5 BIG-IP Easy Button` など、アプリケーションの表示名を入力します。
5. **この組織のディレクトリでのみアプリケーション &gt;Accounts を**使用できるユーザーを指定します。
6. [ **登録** ] を選択して、最初のアプリの登録を完了します。
7. **API のアクセス許可**に移動し、次の Microsoft Graph **アプリケーションのアクセス許可を**承認します。

    - Application.Read.All
    - Application.ReadWrite.All
    - Application.ReadWrite.OwnedBy
    - Directory.Read.All (ディレクトリのすべてを読む)
    - Group.Read.All
    - IdentityRiskyUser.Read.All（アイデンティティリスキーユーザー.リード.オール）
    - Policy.Read.All
    - Policy.ReadWrite.ApplicationConfiguration
    - Policy.ReadWrite.ConditionalAccess
    - User.Read.All
8. 組織に管理者の同意を付与します。
9. [ **証明書とシークレット** ] ブレードで、新しい **クライアント シークレット** を生成し、メモしておきます。
10. [ **概要** ] ブレードで、 **クライアント ID** と **テナント ID を**書き留めます。

### Easy Button を構成する

APM の **ガイド付き構成** を開始して **、Easy Button** テンプレートを起動します。

1. **Microsoft Integration &gt; Access &gt; ガイド付き構成に**移動し、**Microsoft Entra アプリケーション**を選択します。
2. **次の手順を使用してソリューションを構成すると、必要なオブジェクトが作成されます**。構成手順の一覧を確認し、[**次へ**] を選択します。
3. [ **ガイド付き構成]** で、アプリケーションを発行するために必要な一連の手順に従います。

#### Configuration Properties

[ **構成プロパティ** ] タブでは、BIG-IP アプリケーション構成と SSO オブジェクトが作成されます。 先ほど Microsoft Entra テナントに登録したクライアントをアプリケーションとして表すには、 **Azure サービス アカウントの詳細** セクションを検討してください。 これらの設定\*により、BIG-IP\* の OAuth クライアント\*では、通常は手動で構成する\* SSO\* プロパティと共に、SAML SP をテナント\*に直接個別に登録できるようになります。 Easy Button により、公開\*されて SHA が有効になっているすべての BIG-IP\* サービス\*に対してこの操作が行われます。

これらの一部はグローバル設定であるため、より多くのアプリケーションを公開するために再利用でき、デプロイの時間と労力をさらに削減するのに役立ちます。

1. 管理者が Easy Button **の構成** を簡単に区別できるように、一意の構成名を入力します。
2. **単一 Sign-On (SSO) と HTTP ヘッダー**を有効にします。
3. テナントに Easy Button クライアントを登録するときに記録した **テナント ID**、 **クライアント ID**、クライアント **シークレット** を入力します。
4. BIG-IP がテナントに正常に接続できることを確認し、[ **次へ**] を選択します。

    [Image: [構成全般] プロパティと [サービス アカウント] プロパティのスクリーンショット。]

#### サービス プロバイダー

サービス\* プロバイダー\*設定\*では、SHA によって保護されるアプリケーション\*の SAML SP インスタンス\*のプロパティを定義します。

1. **「ホスト」と入力します**。 これは、セキュア アプリケーションのパブリック FQDN です。
2. **エンティティ ID を入力します**。 これは、トークンを要求する SAML SP を識別するために Microsoft Entra ID によって使用される識別子です。

    [Image: サービス プロバイダーの設定のスクリーンショット。]

    オプションの **セキュリティ設定では、** Microsoft Entra ID で発行された SAML アサーションを暗号化するかどうかを指定します。 Microsoft Entra ID と BIG-IP APM の間でアサーションを暗号化すると、コンテンツ トークンが傍受されないこと、および個人や会社のデータが侵害されないことの追加の保証が提供されます。
3. **[Assertion Decryption 秘密キー**] ボックスの一覧で、[**新規作成**] を選択します。

    [Image: [Configure Easy Button- Create New import](簡単なボタンの構成- 新しいインポートの作成) のスクリーンショット。]
4. [ **OK] を選択します**。 [ **SSL 証明書とキーのインポート** ] ダイアログが新しいタブで開きます。
5. **PKCS 12 (IIS)** を選択して、証明書と秘密キーをインポートします。 プロビジョニングが完了したら、ブラウザー タブを閉じて、メイン タブに戻ります。

    [Image: [Configure Easy Button- Import new cert](簡単なボタンの構成 - 新しい証明書のインポート ) のスクリーンショット。]
6. **[Enable Encrypted Assertion](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/暗号化アサーションを有効にする)** をオンにします。
7. 暗号化を有効にしている場合は、[ **Assertion Decryption 秘密キー** ] ボックスの一覧から証明書を選択します。 これは、BIG-IP APM が Microsoft Entra ID アサーションの解読に使用する証明書の秘密キーです。
8. 暗号化を有効にしている場合は、アサーション復号化証明書の一覧から **証明書を** 選択します。 これは、発行された SAML アサーションを暗号化するために BIG IP が Microsoft Entra ID にアップロードする証明書です。

[Image: サービス プロバイダーのセキュリティ設定のスクリーンショット。]

#### マイクロソフト エントラ ID

このセクションでは、Microsoft Entra テナント内で新しい BIG-IP SAML アプリケーションを手動で構成するために通常使用するすべてのプロパティを定義します。 Easy Button には、Oracle PeopleSoft、Oracle E-business Suite、Oracle JD Edwards、SAP ERP、その他のアプリ用の汎用 SHA テンプレート用の定義済みアプリケーション テンプレートのセットが用意されています。

このシナリオでは、[ **Azure 構成]** ページで、 **F5 BIG-IP APM Azure AD Integration**&gt;**Add** を選択します。

##### Azure の構成

**[Azure 構成]** ページで、次の手順に従います。

1. [ **構成プロパティ] に**、BIG-IP が Microsoft Entra テナントで作成するアプリの **表示名** と、 [ユーザーが MyApps ポータル](https://myapplications.microsoft.com/)に表示するアイコンを入力します。
2. IdP によって開始されるサインオンを有効にするために **、サインオン URL (省略可能)** には何も入力しないでください。
3. **署名キー**と**署名証明書**の横にある更新アイコンを選択して、前にインポートした証明書を見つけます。
4. **署名キー**パスフレーズに証明書のパスワードを入力します。
5. **署名オプション**を有効にする (省略可能)。 これにより、BIG-IP は、Microsoft Entra ID によって署名されたトークンと要求のみを受け入れるようになります。

    [Image: Azure 構成のスクリーンショット - 署名証明書情報を追加します。]
6. **ユーザー グループとユーザー グループ** は、Microsoft Entra テナントから動的に照会され、アプリケーションへのアクセスを承認するために使用されます。 後でテストに使用できるユーザーまたはグループを追加します。それ以外の場合は、すべてのアクセスが拒否されます。

    [Image: Azure 構成のスクリーンショット - ユーザーとグループを追加します。]

##### ユーザー属性と要求

ユーザーが正常に認証されると、Microsoft Entra ID は、ユーザーを一意に識別する要求と属性の既定のセットを使用して SAML トークンを発行します。 [ **ユーザー属性と要求] タブには、** 新しいアプリケーションに対して発行する既定の要求が表示されます。 また、さらに多くの要求を構成することもできます。

この例では、属性をもう 1 つ含めることができます。

1. **Header Name**として employeeid を入力します。
2. user.employeeid として **ソース属性** を入力します。

    [Image: ユーザー属性と要求のスクリーンショット。]

##### 追加のユーザー属性

[ **追加のユーザー属性] タブ**では、Oracle、SAP、他のディレクトリに格納されている属性を必要とする他の JAVA ベースの実装など、さまざまな分散システムで必要なセッション拡張を有効にすることができます。 次に、LDAP ソースからフェッチされた属性を追加の SSO ヘッダーとして挿入して、ロール、パートナー ID などに基づいてアクセスをさらに制御できます。

注

この機能は Microsoft Entra ID と相関関係はありませんが、属性のもう 1 つのソースです。

##### 条件付きアクセス ポリシー

条件付きアクセス ポリシーは、デバイス、アプリケーション、場所、リスクの兆候に基づいてアクセスを制御するために、Microsoft Entra の事前認証後に適用されます。

既定では、[ **使用可能なポリシー]** ビューには、ユーザー ベースのアクションを含まないすべての条件付きアクセス ポリシーが一覧表示されます。

既定では、[ **選択したポリシー** ] ビューには、すべてのリソースを対象とするすべてのポリシーが表示されます。 これらのポリシーは、テナント レベルで適用されるため、選択を解除したり、[使用可能なポリシー] リストに移動したりすることはできません。

公開されているアプリケーションに適用するポリシーを選択するには:

1. [使用可能なポリシー] ボックスの一覧で目的 **のポリシーを** 選択します。
2. 右矢印を選択し、[ **選択したポリシー** ] リストに移動します。

選択したポリシーでは、[ **含める]** または **[除外]** オプションがオンになっている必要があります。 両方のオプションをオンにした場合、選択したポリシーは適用されません。

[Image: 条件付きアクセス ポリシーのスクリーンショット。]

注

ポリシーの一覧は、最初にこのタブに切り替えたときに 1 回だけ列挙されます。ウィザードから手動でテナントにクエリを実行するための更新ボタンが用意されていますが、このボタンはアプリケーションがデプロイされている場合にのみ表示されます。

#### 仮想サーバーのプロパティ

仮想サーバーは、アプリケーションに対するクライアント要求をリッスンする仮想 IP アドレスで表される BIG-IP データ プレーン オブジェクトです。 受信したトラフィックは、ポリシーの結果と設定に従って送信される前に、仮想サーバーに関連付けられている APM プロファイルに対して処理および評価されます。

1. **宛先アドレスを入力します**。 これは、BIG-IP がクライアント トラフィックを受信するために使用できる任意の IPv4 または IPv6 アドレスです。 対応するレコードが DNS にも存在する必要があり、それによってクライアントでは、BIG-IP の公開済みアプリケーションの外部 URL を、アプリケーション自体ではなく、この IP に解決できるようになります。 テストでは、テスト PC の localhost DNS を使用しても問題ありません。
2. HTTPS の場合は、 **サービス ポート** を *443* と入力します。
3. [ **リダイレクト ポートを有効にする]** をオンにし、「 **リダイレクト ポート」**と入力します。 これにより、受信 HTTP クライアント トラフィックが HTTPS にリダイレクトされます。
4. クライアント\* SSL\* プロファイル\*を使用すると、HTTPS\* 用の仮想サーバー\*が有効になり、クライアント\*接続が TLS\* で暗号化されるようになります。 前提条件の一部として作成した **クライアント SSL プロファイル** を選択するか、テスト中は既定値のままにします。

    [Image: 仮想サーバーのスクリーンショット。]

#### プールのプロパティ

[ **アプリケーション プール] タブ** では、1 つ以上のアプリケーション サーバーを含むプールとして表される BIG-IP の背後にあるサービスの詳細が表示されます。

1. [ **プールの選択] から選択します**。 新しいプールを作成するか、既存のプールを選択します。
2. として`Round Robin`選択します。
3. **プール サーバー**の場合は、既存のノードを選択するか、ヘッダー ベースのアプリケーションをホストするサーバーの IP とポートを指定します。

    [Image: アプリケーション プールのスクリーンショット。]

バックエンド アプリケーションでは HTTP ポート 80 を使用しますが、HTTPS の場合は当然のことながら 443 に切り替えます。

##### シングル サインオン & HTTP ヘッダー

SSO を有効にすると、ユーザーは資格情報を入力しなくても、BIG-IP で公開されているサービスにアクセスできるようになります。 **Easy Button ウィザード**では、SSO 用の Kerberos、OAuth Bearer、HTTP 承認ヘッダーがサポートされています。後者では、次の構成を有効にします。

- **ヘッダー操作:**`Insert`
- **ヘッダー名:**`upn`
- **ヘッダー値:**`%{session.saml.last.identity}`
- **ヘッダー操作:**`Insert`
- **ヘッダー名:**`employeeid`
- **ヘッダー値:**`%{session.saml.last.attr.name.employeeid}`

    [Image: SSO ヘッダーと HTTP ヘッダーのスクリーンショット。]

注

中かっこ内で定義されている APM セッション変数は、大文字と小文字が区別されます。 たとえば、Microsoft Entra の属性名が orclguid として定義されている場合に「OrclGUID」と入力すると、属性マッピング エラーが発生します。

#### セッションの管理

BIG-IP のセッション管理の設定は、ユーザー セッションが終了されるか続行が許可される条件、ユーザーと IP アドレスの制限、および対応するユーザー情報を定義するために使用されます。 これらの設定の詳細については、 [F5 のドキュメント](https://support.f5.com/csp/article/K18390492) を参照してください。

しかし、ユーザーがサインオフするときに IdP、BIG-IP、およびユーザー エージェント間のすべてのセッションが確実に終了されるようにする、シングル ログアウト (SLO) 機能についての説明はここにはありません。 Easy Button によって SAML アプリケーションが Microsoft Entra テナントでインスタンス化されると、ログアウト URL にも、APM の SLO エンドポイントが設定されます。 このように、Microsoft Entra マイ アプリ ポータルからの IdP Initiated サインアウトでは、BIG-IP とクライアント間のセッションも終了します。

これに加え、テナントから公開済みアプリケーションの SAML フェデレーション メタデータもインポートされて、APM に Microsoft Entra ID の SAML ログアウト エンドポイントが提供されます。 これにより、SP Initiated サインアウトでクライアントと Microsoft Entra ID との間のセッションが確実に終了するようになります。 しかし、これを真に効果的に行うには、APM で、ユーザーがいつアプリケーションからサインアウトしたのかを正確に知る必要があります。

BIG-IP Web トップ ポータルを使用して公開済みアプリケーションにアクセスする場合は、そこからのサインアウトが APM によって処理され、Microsoft Entra サインアウト エンドポイントも呼び出されます。 しかし、BIG-IP Web トップ ポータルが使用されていないために、サインアウトするようにユーザーが APM に指示する方法がないシナリオについて考えてみます。ユーザーがアプリケーション自体からサインアウトした場合でも、BIG-IP では技術的にはこれが認識されません。 このため、SP によって開始されるサインアウトでは、セッションが不要になったときに確実に安全に終了されるように、慎重に検討する必要があります。 これを実現する 1 つの方法は、SLO 関数をアプリケーションのサインアウト ボタンに追加して、クライアントを Microsoft Entra SAML または BIG-IP サインアウト エンドポイントにリダイレクトできるようにすることです。 テナントの SAML サインアウト エンドポイントの URL は、[ **アプリの登録] &gt; [エンドポイント]** にあります。

アプリに変更を加えることができない場合は、BIG-IP でアプリケーションのサインアウト呼び出しをリッスンし、要求を検出したら SLO をトリガーすることを検討してください。 これを実現するために BIG-IP irules を使用する場合は、 [Oracle PeopleSoft SLO ガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-peoplesoft-easy-button#peoplesoft-single-logout) を参照してください。 これを実現するために BIG-IP iRules を使用する方法の詳細については、F5 ナレッジ記事「 [URI 参照ファイル名に基づく自動セッション終了 (ログアウト) の構成](https://support.f5.com/csp/article/K42052145) 」および [「ログアウト URI インクルード」オプションの概要](https://support.f5.com/csp/article/K12056)を参照してください。

### まとめ

この最後の手順では、構成の概要を示します。 [ **デプロイ]** を選択してすべての設定をコミットし、アプリケーションが "エンタープライズ アプリケーション" のテナント一覧に存在することを確認します。

これで、アプリケーションが公開され、その URL を介して直接または Microsoft のアプリケーション ポータルを介して、SHA によりアクセスできるようになります。
<!-- /MSL-PAGE -->
