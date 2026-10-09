# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 2)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 75

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alibaba-cloud-service-role-based-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Alibaba Cloud Service (ロールベースの SSO) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alibaba-cloud-service-role-based-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Alibaba Cloud Service (ロールベースの SSO) の間でシングル サインオンを構成する方法について説明します。

この記事では、Alibaba Cloud Service (ロールベースの SSO) と Microsoft Entra ID を統合する方法について説明します。 Alibaba Cloud Service (ロールベースの SSO) と Microsoft Entra ID を統合すると、次のことができます。

- Alibaba Cloud Service (ロールベースの SSO) にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Alibaba Cloud Service (ロールベースの SSO) に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

Alibaba Cloud Service は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ |  | ✅ |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Alibaba Cloud Service (ロールベースの SSO) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Alibaba Cloud Service (ロールベースの SSO) では、**IDP** イニシエーッテッド SSO がサポートされます

### ギャラリーから Alibaba Cloud Service（ロールベースのSSO）を追加する

Microsoft Entra ID への Alibaba Cloud Service (ロールベースの SSO) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Alibaba Cloud Service (ロールベースの SSO) を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「Alibaba Cloud Service (ロールベースの SSO)」**と入力します。
4. 結果のパネルから **Alibaba Cloud Service (ロールベースの SSO)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。
5. **Alibaba Cloud Service (ロールベースの SSO)** ページで、左側のナビゲーション ウィンドウで **[プロパティ**] を選択し、**オブジェクト ID を**コピーして、後で使用するためにコンピューターに保存します。

    [Image: プロパティの構成]

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Alibaba Cloud Service の Microsoft Entra SSO の構成とテスト (ロールベースの SSO)

**B.Simon** というテスト ユーザーを使用して、Alibaba Cloud Service (ロールベースの SSO) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Alibaba Cloud Service (ロールベースの SSO) の関連ユーザーとの間にリンク関係を確立する必要があります。

Alibaba Cloud Service (ロールベースの SSO) に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Alibaba Cloud Service で Role-Based Single Sign-On を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Alibaba Cloud Service のロールベース SSO構成** - アプリケーション側でシングル Sign-On の設定を構成します。
    2. **Alibaba Cloud Service (ロールベースの SSO) テストユーザーの作成** - Microsoft Entra のユーザーである Britta Simon に対応するユーザーを Alibaba Cloud Service (ロールベースの SSO) で作成し、それをリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Enterprise アプリ]**&gt;**[Alibaba Cloud Service (ロールベースの SSO)]**&gt;**[Single サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    注

    1. Alibaba Cloud International Site の場合は、 [この](https://signin.alibabacloud.com/saml-role/sp-metadata.xml) リンクからサービス プロバイダーのメタデータをダウンロードしてください。
    2. Alibaba Cloud Service(CN) サイトの場合は、 [この](https://signin.aliyun.com/saml-role/sp-metadata.xml) リンクからサービス プロバイダーのメタデータをダウンロードしてください。

    c. メタデータ ファイルが正常にアップロードされると、 **識別子** と **応答 URL** の値が、Alibaba Cloud Service (ロールベースの SSO) セクションのテキスト ボックスに自動的に設定されます。

    注

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. Alibaba Cloud Service (ロールベースの SSO) では、Microsoft Entra ID でロールを構成する必要があります。 ロール要求は事前構成されているため、構成する必要はありませんが、この [記事](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)を使用して Microsoft Entra ID で作成する必要があります。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Alibaba Cloud Service (ロールベースの SSO) の設定** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Alibaba Cloud Service で Role-Based Single Sign-On を構成する

1. Account1 を使用して、Alibaba Cloud [RAM コンソール](https://account.alibabacloud.com/login/login.htm?oauth_callback=https%3A%2F%2Fram.console.aliyun.com%2F%3Fspm%3Da2c63.p38356.879954.8.7d904e167h6Yg9) にサインインします。
2. 左側のナビゲーション ウィンドウで、[ **SSO**] を選択します。
3. [ **ロールベースの SSO** ] タブで、[ **IdP の作成**] を選択します。
4. 表示されたページで、[IdP 名] フィールドに「 `AAD` 」と入力し、[ **メモ** ] フィールドに説明を入力し、[ **アップロード** ] を選択して、前にダウンロードしたフェデレーション メタデータ ファイルをアップロードし、[ **OK] を選択します**。
5. IdP が正常に作成されたら、[ **RAM ロールの作成**] を選択します。
6. [**RAM ロール名**] フィールドに「`AADrole`」と入力し、[`AAD`] ドロップダウン リストからを選択し、[OK] を選択します。

    注

    必要に応じて、ロールにアクセス許可を付与できます。 IdP と対応するロールを作成した後、後で使用するために IdP の ARN とロールを保存することをお勧めします。 ARN は、IdP 情報ページとロール情報ページで取得できます。
7. Alibaba Cloud RAM ロール (AADrole) を Microsoft Entra ユーザー (u2) に関連付けます。

    RAM ロールを Microsoft Entra ユーザーに関連付けるには、次の手順に従って Microsoft Entra ID でロールを作成する必要があります。

    1. [Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)にサインインします。
    2. ロールを作成するために必要なアクセス許可を取得するには、[ **アクセス許可の変更** ] を選択します。

        [Image: Graph の構成 1]
    3. 次の図に示すように、一覧から次のアクセス許可を選択し、[ **アクセス許可の変更**] を選択します。

        [Image: Graph の構成 2]

        注

        アクセス許可が付与されたら、Graph エクスプローラーにもう一度サインインします。
    4. Graph Explorer ページで、最初のドロップダウン リストから **[GET]** を選択し、2 つ目のドロップダウン リストから **[ベータ]** を選択します。 次に、ドロップダウン リストの横にあるフィールドに「 `https://graph.microsoft.com/beta/servicePrincipals` 」と入力し、[クエリの **実行**] を選択します。

        [Image: Graph config3]

        注

        複数のディレクトリを使用している場合は、クエリのフィールドに `https://graph.microsoft.com/beta/contoso.com/servicePrincipals` を入力できます。
    5. **[Response Preview](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/応答プレビュー)** セクションで、後で使用するために "Service Principal" から appRoles プロパティを抽出します。

        [Image: Graph config4]

        注

        クエリのフィールドに「`https://graph.microsoft.com/beta/servicePrincipals/<objectID>`」と入力することで appRoles プロパティを見つけることができます。 `objectID` は Microsoft Entra ID の **[プロパティ]** ページからコピーしたオブジェクト ID であることに注意してください。
    6. Graph エクスプローラーに戻り、メソッドを **GET** から **PATCH** に変更し、次の内容を **[要求本文** ] セクションに貼り付けて、[ **クエリの実行**] を選択します。

        ```json
          {
            "appRoles": [
              {
                "allowedMemberTypes": [
                  "User"
                ],
                "description": "msiam_access",
                "displayName": "msiam_access",
                "id": "41be2db8-48d9-4277-8e86-f6d22d35****",
                "isEnabled": true,
                "origin": "Application",
                "value": null
              },
              {
                "allowedMemberTypes": [
                  "User"
                ],
                "description": "Admin,AzureADProd",
                "displayName": "Admin,AzureADProd",
                "id": "68adae10-8b6b-47e6-9142-6476078cdbce",
                "isEnabled": true,
                "origin": "ServicePrincipal",
                "value": "acs:ram::187125022722****:role/aadrole,acs:ram::187125022722****:saml-provider/AAD"
              }
            ]
          }
        ```

        注

        `value`は、IdP の ARN と、RAM コンソールで作成したロールです。 ここでは、必要に応じて複数のロールを追加できます。 Microsoft Entra ID は、これらのロールの値を SAML 応答の要求値として送信します。 ただし、パッチ操作では、`msiam_access` 部分の後にのみ、新しいロールを追加できます。 作成過程を速やかに進めるため、GUID Generator など、ID ジェネレーターを使用してリアルタイムで ID を生成することをお勧めします。
    7. 必要なロールで "サービス プリンシパル" に修正プログラムが適用されたら、記事の「 **Microsoft Entra テスト ユーザーの割り当て** 」セクションの手順に従って、Microsoft Entra ユーザー (u2) にロールをアタッチします。

#### Alibaba Cloud Service (ロールベースの SSO) の SSO の構成

**Alibaba Cloud Service (ロールベースの SSO)** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Alibaba Cloud Service (ロールベースの SSO) サポート チーム](https://www.aliyun.com/service/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Alibaba Cloud Service (ロールベースの SSO) テスト ユーザーの作成

このセクションでは、Alibaba Cloud Service (ロールベースの SSO) で Britta Simon というユーザーを作成します。 [Alibaba Cloud Service (ロールベースの SSO) サポート チーム](https://www.aliyun.com/service/)と協力して、Alibaba Cloud Service (ロールベースの SSO) プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

上記の構成が完了したら、次の手順に従って Alibaba Cloud Service (ロールベースの SSO) をテストします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Alibaba Cloud Service (ロールベースの SSO)]** に移動します。
3. [ **シングル サインオン**] を選択し、[ **テスト**] を選択します。

    [Image: config1 をテストする]
4. [ **現在のユーザーとしてサインイン]** を選択します。

    [Image: config2 をテストする]
5. アカウントの選択ページで、u2 を選択します。

    [Image: config3 のテスト]
6. ロールベースの SSO が成功したことを示す次のページが表示されます。

    [Image: config4 のテスト]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alinto-protect-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Cleanmail を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alinto-protect-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-25
- Summary: Microsoft Entra IDから Cleanmail にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、CleanmailとMicrosoft Entra IDの両方で自動ユーザープロビジョニングを設定するために必要な手順について説明します。 Microsoft Entra IDを構成すると、Microsoft Entraプロビジョニングサービスを使用して、ユーザーとグループを[Cleanmail](https://www.alinto.com/)に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Cleanmail でユーザーを作成する
- アクセスが不要になった場合、Cleanmail からユーザーを削除する。
- Microsoft Entra IDと Cleanmail の間でユーザー属性の同期を維持する
- Cleanmail に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者権限を持つ Cleanmail のユーザー アカウント

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra IDとCleanmail](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)の間でマップするデータを決定する。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Cleanmail を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように Cleanmail を構成するには、[Cleanmail サポート](https://www.alinto.com/contact-email-provider/) にお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Cleanmail を追加する

Microsoft Entra アプリケーション ギャラリーから Cleanmail を追加して、Cleanmail へのプロビジョニングの管理を開始します。 SSO のために Cleanmail を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Cleanmail への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDにおけるユーザーとグループの割り当てに基づいて、Cleanmailでのユーザーとグループの作成、更新、無効化を行うためのMicrosoft Entra プロビジョニング サービスの設定手順を案内します。

#### Microsoft Entra IDで Cleanmail の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で [ **Cleanmail**] を選択します。

    [Image: アプリケーションの一覧の Cleanmail リンク]
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
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Cleanmail に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Cleanmail のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Cleanmail API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Cleanmail で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | externalId | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、組織内でより広範に展開する前に、少数のユーザーとの同期を検証します。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/allbound-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Allbound SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/allbound-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Allbound SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、Allbound SSO と Microsoft Entra ID を統合する方法について説明します。 Allbound SSO を Microsoft Entra ID と統合すると、次のことが可能になります。

- Allbound SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Allbound SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/free/?WT.mc_id=A261C142F)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Allbound SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Allbound SSO では、**SP および IDP** によって開始された SSO をサポートしています。
- Allbound SSO では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Allbound SSO を追加する

Microsoft Entra ID への Allbound SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Allbound SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Allbound SSO**」と入力します。
4. 結果パネルから **Allbound SSO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Allbound SSO に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Allbound SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Allbound SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Allbound SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Allbound SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Allbound SSO テストユーザーの作成** - Microsoft Entra におけるユーザーの表現にリンクされた Allbound SSO で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Allbound SSO**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.allbound.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.allbound.com/acs`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.allbound.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Allbound SSO クライアント サポート チーム](mailto:engineering@allbound.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Allbound SSO のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Allbound SSO の構成

**Allbound SSO** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Allbound SSO サポート チーム](mailto:engineering@allbound.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Allbound SSO テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Allbound SSO に作成します。 Allbound SSO では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Allbound SSO にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Allbound SSO サポート チーム](mailto:engineering@allbound.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Allbound SSO サインオン URL にリダイレクトされます。
- Allbound SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Allbound SSO に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Allbound SSO] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Allbound SSO に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/allocadia-tutorial"} -->
## Microsoft Entra ID で Allocadia for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/allocadia-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Allocadia の間のシングル サインオンを構成する方法について説明します。

この記事では、Allocadia と Microsoft Entra ID を統合する方法について説明します。 Allocadia を Microsoft Entra ID と統合すると、次のことが可能になります。

- Allocadia へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Allocadia に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Allocadia のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Allocadia では、 **IDP** によって開始される SSO がサポートされます。
- Allocadia では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Allocadia の追加

Microsoft Entra ID への Allocadia の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Allocadia を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックスに **「Allocadia** 」と入力します。
4. 結果パネルから **Allocadia** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Allocadia に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Allocadia に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Allocadia の関連ユーザーとの間にリンク関係を確立する必要があります。

Allocadia に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Allocadia の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Allocadia のテストユーザーを作成** - Allocadia で B.Simon の対応ユーザーを作り、Microsoft Entra 上のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Allocadia**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **識別子** |
    | --- |
    | テスト環境の場合 - `https://na2standby.allocadia.com` |
    | 運用環境の場合 - `https://na2.allocadia.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | テスト環境の場合 - `https://na2standby.allocadia.com/allocadia/saml/SSO` |
    | 運用環境の場合 - `https://na2.allocadia.com/allocadia/saml/SSO` |
6. Allocadia アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性 の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Allocadia アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | firstname | User.givenname |
    | lastname | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Allocadia のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Allocadia の SSO を構成する

**Allocadia** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Allocadia サポート チーム](mailto:support@allocadia.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Allocadia のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Allocadia に作成します。 Allocadia では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Allocadia にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Allocadia に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Allocadia] タイルを選択すると、SSO を設定した Allocadia に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ally-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Ally.io を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ally-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Ally.io 間のシングル サインオンを構成する方法について説明します。

この記事では、Ally.io と Microsoft Entra ID を統合する方法について説明します。 Ally.io を Microsoft Entra ID と統合すると、次のことが可能になります。

- Ally.io にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Ally.io に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Ally.io でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Ally.io では、**SP および IDP** Initiated SSO がサポートされます。
- Ally.io では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Ally.io を追加する

Microsoft Entra ID への Ally.io の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Ally.io を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Ally.io**」と入力します。
4. 結果のパネルから **[Ally.io]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Ally.io に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Ally.io に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Ally.io の関連ユーザーとの間にリンク関係を確立する必要があります。

Ally.io に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Ally.io SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**Ally.io**&gt;を探して、**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.ally.io/saml/consume/<CUSTOM_GUID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.ally.io/saml/consume/<CUSTOM_GUID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.ally.io/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 この値を取得するには、[Ally.io クライアント サポート チーム](mailto:contact@ally.io)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Ally.io アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 既定の属性一覧を示しているスクリーンショット。]
8. その他に、Ally.io アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | user.userprincipalname |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Set up Ally.io](Ally.io の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Ally.io の SSO の構成

Ally.io 側でシングル サインオンを構成するには、証明書 (Base64) と適切な URL を Azure portal からコピーして、Ally.io に追加する必要があります。

1. 管理者アカウントを使用して Ally.io にサインインします。
2. 画面の左側にあるナビゲーション バーを使用して、** [管理]**&gt;**[統合]** を選択します。
3. スクロールして **[認証]** セクションに移動し、 **[シングル サインオン]** を選択します。 その後、 **[有効化]** を選択します。

    [Image: Ally I O の [有効化] ボタンを示すスクリーンショット。]

    **[SSO 構成]** ページが開き、証明書とコピーした URL を構成できます。

    [Image: Ally I O の [S S O configuration](S S O 構成) ペインを示すスクリーンショット。]
4. **[SSO Configuration](SSO 構成)** で、次の設定を入力または選択します。

    - **Ally**: Microsoft Entra ID
    - **SAML 2.0 エンドポイントの URL**: ログイン URL
    - **ID プロバイダーの発行者 URL**: Microsoft Entra 識別子
    - **公開 (X.509) 証明書**: 証明書 (base 64)

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Ally.io サインオン URL にリダイレクトされます。
- Ally.io のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Ally.io に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Ally.io] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Ally.io に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alohi-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Alohi を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alohi-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-20
- Summary: Microsoft Entra ID から Alohi にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Alohi ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーを [Alohi](https://www.alohi.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Alohi でユーザーを作成します。
- アクセスが不要になったら、Alohi のユーザーを削除します。
- Microsoft Entra ID と Alohi の間でユーザー属性の同期を維持します。
- Alohi に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alohi-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Alohi のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- プロビジョニングのスコープに誰が含まれるかを決定します。
- [Microsoft Entra ID と Alohi の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Alohi を構成する

Microsoft Entra ID を使用したプロビジョニングをサポートするように Alohi を構成するには、Alohi サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Alohi を追加する

Microsoft Entra アプリケーション ギャラリーから Alohi を追加して、Alohi へのプロビジョニングの管理を開始します。 SSO 用に Alohi を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Alohi への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて Alohi のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Alohi の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Alohi**] を選択します。

    [Image: アプリケーションの一覧の [Alohi] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Alohi テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Alohi に接続できることを確認します。 接続に失敗した場合は、Alohi アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. [属性マッピング] セクションで、Microsoft Entra ID から Alohi に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Alohi のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Alohi API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Alohi により必要とされます |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | 名前.名 | 糸 |  | ✓ |
    | 名前.姓 | 糸 |  | ✓ |
12. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alohi-tutorial"} -->
## Microsoft Entra ID で Alohi for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alohi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Alohi の間でシングル サインオンを構成する方法について説明します。

この記事では、Alohi と Microsoft Entra ID を統合する方法について説明します。 Alohi と Microsoft Entra ID を統合すると、次のことができます。

- Alohi にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Alohi に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Alohi でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Alohi では、**SP によって開始されるSSO と IDP** によって開始されるSSO の両方がサポートされます。
- Alohi では、 **Just In Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Alohi を追加する

Microsoft Entra ID への Alohi の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Alohi を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックスに **「Alohi** 」と入力します。
4. 結果パネルから **Alohi** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Alohi の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Alohi に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Alohi の関連ユーザーとの間にリンク関係を確立する必要があります。

Alohi に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Alohi SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Alohi テスト ユーザーの作成** - Microsoft Entra ID にある B.Simon に対応する Alohi ユーザーを作成し、B.Simon の ID 表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Alohi**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.fax.plus/login`
7. Alohi アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. 上記に加えて、Alohi アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 名字 | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. [ **Alohi のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Alohi SSO の構成

**Alohi** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、Microsoft Entra 管理センターからコピーした適切な URL を [Alohi サポート チーム](mailto:support@alohi.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Alohi テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Alohi に作成します。 Alohi では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Alohi にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Alohi のサインオン URL にリダイレクトします。
- Alohi のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Alohi に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Alohi] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Alohi に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/altamira-hrm-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Altamira HRM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/altamira-hrm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Altamira HRM の間のシングル サインオンを構成する方法について説明します。

この記事では、Altamira HRM と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Altamira HRM を統合すると、次のことができます。

- Altamira HRM へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Altamira HRM に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Altamira HRM でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Altamira HRM は、**SP開始のSSO**と**IDP開始のSSO**をサポートします。
- Altamira HRM では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。
- Altamira HRM を構成したら、ご自分の組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は条件付きアクセスから拡張されます。 [Microsoft Defender for Cloud Apps でセッション制御を適用する方法について説明します](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)。

### ギャラリーからの Altamira HRM の追加

Microsoft Entra ID への Altamira HRM の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Altamira HRM を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Altamira HRM**」と入力します。
4. 結果パネルから **Altamira HRM** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Altamira HRM の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Altamira HRM に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Altamira HRM の関連ユーザーとの間にリンク関係を確立する必要があります。

Altamira HRM に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Altamira HRM SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Altamira HRM のテスト ユーザーの作成** - Altamira HRM で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Altamira HRM**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.altamiraweb.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.altamiraweb.com/AssertionService.aspx`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.altamirahrm.com/Default.aspx`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Altamira HRM クライアント サポート チーム](https://altamira.zendesk.com/hc) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Altamira HRM のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Altamira HRM SSO の構成

**Altamira HRM** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Altamira HRM サポート チーム](https://altamira.zendesk.com/hc)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Altamira HRM のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Altamira HRM に作成します。 Altamira HRM では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Altamira HRM にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Altamira HRM サインオン URL にリダイレクトされます。
- Altamira HRM のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Altamira HRM に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Altamira HRM] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Altamira HRM に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alteryx-server-tutorial"} -->
## Microsoft Entra ID で Alteryx Server for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alteryx-server-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Alteryx Server の間でシングル サインオンを構成する方法について説明します。

この記事では、Alteryx Server と Microsoft Entra ID を統合する方法について説明します。 Alteryx Server と Microsoft Entra ID を統合すると、次のことができます。

- Alteryx Server にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Alteryx Server に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Alteryx Server でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Alteryx Server では、 **SP Initiated SSO と IDP** Initiated SSO の両方がサポートされます。

### ギャラリーから Alteryx Server を追加する

Microsoft Entra ID への Alteryx Server の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Alteryx Server を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Alteryx Server」**と入力します。
4. 結果パネルから **Alteryx Server を** 選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Alteryx Server の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Alteryx Server に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Alteryx Server の関連ユーザーとの間にリンク関係を確立する必要があります。

Alteryx Server に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Alteryx Server の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Alteryx Server テストユーザーの作成** - Microsoft Entra ID に関連付けられた B.Simon に対応するユーザーを Alteryx Server に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Alteryx Server**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ａ. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.<DOMAIN>.<EXTENSION>/webapi/Saml2`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.<DOMAIN>.<EXTENSION>/webapi/Saml2/acs`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.<DOMAIN>.<EXTENSION>/webapi/saml2/signin`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Alteryx Server サポート チーム](mailto:support@alteryx.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Alteryx Server の SSO の構成

1. Alteryx Server 企業サイトに管理者としてログインします。
2. **システム設定**&gt;**Server UI**&gt;**General** に移動し、[**サーバー UI の SSL/TLS を有効にする**] を選択して SSL が有効になっていることを確認します。 サーバーに証明書がインストールされている必要があります。 詳細については、「[SSL 用の Alteryx Server の構成: 証明書の取得とインストール](https://knowledge.alteryx.com/index/s/article/Configuring-Alteryx-Server-for-SSL-Obtaining-and-Installing-Certificates-1583459841225)」を参照してください。

    [Image: 構成の設定を示すスクリーンショット。]
3. **システム設定**&gt;**Server UI**&gt;**Authentication** に移動し、次の手順を実行します。

    [Image: [構成] を示すスクリーンショット。]

    1. [認証の種類] として [ **SAML 認証** ] を選択 **します**。
    2. 要件に応じて **、IDP メタデータ URL** または **X509 証明書と IDP SSO URL** のいずれかを選択します。
    3. **ACS ベース URL** フィールドは自動的に設定され、HTTPS で設定されます。
    4. **[IDP URL**] フィールドに、**Microsoft Entra** 管理センターからコピーした Microsoft Entra 識別子を貼り付けます。
    5. **[IDP メタデータ URL**] フィールドに、Microsoft Entra 管理センターからコピーした**アプリのフェデレーション メタデータ URL を**貼り付けます。
    6. [ **IDP の確認] を**選択します。

#### Alteryx Server テスト ユーザーの作成

このセクションでは、Alteryx Server で B.Simon というユーザーを作成します。 Alteryx Server サポート チーム  と連携して、Alteryx Server プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Alteryx Server のサインオン URL にリダイレクトします。
- Alteryx Server のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP が開始されました

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Alteryx Server に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Alteryx Server] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Alteryx Server に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/altoura-tutorial"} -->
## Microsoft Entra ID で Altoura for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/altoura-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Altoura の間でシングル サインオンを構成する方法について説明します。

この記事では、Altoura と Microsoft Entra ID を統合する方法について説明します。 Altoura と Microsoft Entra ID を統合すると、次のことができます。

- Altoura にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Altoura に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Altoura でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Altoura では、**SP** Initiated SSO がサポートされます。

### ギャラリーから Altoura を追加する

Microsoft Entra ID への Altoura の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Altoura を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Altoura**」と入力します。
4. 結果のパネルから Altoura  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Altoura の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Altoura に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Altoura の関連ユーザーとの間にリンク関係を確立する必要があります。

Altoura に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Altoura SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Altoura のテスト ユーザーの作成** - Altoura で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Altoura]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    a. [**識別子** ボックスに、次のパターンを使用して URL を入力します:`https://<SUBDOMAIN>.altoura.com`

    b。 [**応答 URL**] ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://portal.altoura.com/user/saml/client/callback/microsoftsamlclientstrategy` |
    | `https://portal.altoura.com/user/saml/callback/microsoftsamlportalstrategy` |

    c. [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.altoura.com`

    手記

    これらの値は実際の値ではありません。 実際の識別子とサインオン URL でこれらの値を更新します。 これらの値を取得するには、Altoura クライアント サポート チーム  にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML  検索し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [**Altoura** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Altoura SSO の構成

Altoura **側の** シングルサインオンを構成するためには、ダウンロードした **フェデレーションメタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Altoura サポートチーム](mailto:support@altoura.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Altoura テスト ユーザーの作成

このセクションでは、Altoura で Britta Simon というユーザーを作成します。 Altoura サポート チーム  と連携して、Altoura プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Altoura のサインオン URL にリダイレクトされます。
- Altoura のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Altoura] タイルを選択すると、このオプションは Altoura のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alvao-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に ALVAO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alvao-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: ユーザー アカウントを Microsoft Entra ID から ALVAOi に対して自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために ALVAO と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [ALVAO](https://www.alvao.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされる機能

- ALVAO でユーザーを作成する。
- アクセスが不要になったら、ALVAO のユーザーを削除します。
- Microsoft Entra ID と ALVAO 間でユーザー属性の同期を維持する。
- ALVAO でグループとグループ メンバーシップをプロビジョニングする。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- ALVAO の管理者権限を持つユーザー アカウント。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と ALVAO の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように ALVAO を構成する

1. **テナント SCIM エンドポイントの URL を**見つけます。これは、`{ALVAO REST API address}/scim`形式 (たとえば、`https://app.contoso.com/alvaorestapi/scim`) である必要があります。
2. [WebApp - 管理 - 設定 - **Microsoft Entra ID**](https://doc.alvao.com/en/25/ui/administration/settings/microsoft-entra-id) で新しい**シークレット トークン**を生成し、その値をコピーします。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから ALVAO を追加する

Microsoft Entra アプリケーション ギャラリーから ALVAO を追加して、ALVAO へのプロビジョニングの管理を開始します。 SSO のために ALVAO を既に設定している場合は、その同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: ALVAO への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーまたはグループの割り当てに基づいて、ALVAO のユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で ALVAO に対する自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくともアプリ所有者または[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**を閲覧する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **ALVAO** を選択します。

    [Image: アプリケーションの一覧の ALVAO リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、ALVAO テナント URL とシークレット トークンを入力します。 [ **テスト接続** ] を選択して、Microsoft Entra ID が ALVAO に接続できることを確認します。 接続に失敗した場合は、ALVAO アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. [属性マッピング] セクションで、Microsoft Entra ID から ALVAO に同期されるユーザー **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で ALVAO のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が ALVAO API でサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | ALVAO で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | name.formatted | 糸 |  |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | phoneNumbers[type eq "ファックス"].value | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |  |
12. グループを選択 **します**。
13. [属性マッピング] セクションで、Microsoft Entra ID から ALVAO に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で ALVAO のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | ALVAO で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  |  |
    | members | 関連項目 |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/amazing-people-schools-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Amazing People Schools を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amazing-people-schools-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Amazing People Schools の間でシングル サインオンを構成する方法について説明します。

この記事では、Amazing People Schools と Microsoft Entra ID を統合する方法について説明します。 Amazing People Schools と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID を使って Amazing People Schools へのアクセス権を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Amazing People Schools に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Amazing People Schools でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Amazing People Schools では、 **SP** によって開始される SSO がサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから「Amazing People Schools」を追加する

Microsoft Entra ID への Amazing People Schools の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Amazing People Schools を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに「**Amazing People Schools**」と入力します。
4. 結果パネルから **[Amazing People Schools]** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Amazing People Schools の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Amazing People Schools に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Amazing People Schools の関連ユーザーとの間にリンク関係を確立する必要があります。

Amazing People Schools で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Amazing People Schools の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Amazing People Schools のテストユーザーを作成し、**Microsoft Entra のユーザー表現にリンクする B.Simon に対応するユーザーを Amazing People Schools で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Amazing People Schools]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://www.amazingpeopleschools.com/api`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://www.amazingpeopleschools.com/api/school_process_saml_login/microsoft_frontend`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://uk.amazingpeopleschools.com/?saml=true#/login/`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Amazing People Schools のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Amazing People Schools の構成

**Amazing People Schools** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Amazing People Schools サポート チーム](mailto:frances@amazingpeopleschools.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Amazing People Schools のテスト ユーザーの作成

このセクションでは、Amazing People Schools で Britta Simon というユーザーを作成します。 [Amazing People Schools サポート チーム](mailto:frances@amazingpeopleschools.com)と協力して、Amazing People Schools プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Amazing People Schools のサインオン URL にリダイレクトされます。
- Amazing People Schools のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Amazing People Schools] タイルを選択すると、このオプションは Amazing People Schools のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/amazon-business-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Amazon Business を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amazon-business-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-20
- Summary: Microsoft Entra ID から Amazon Business へのユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Amazon Business と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Amazon Business](https://www.amazon.com/b2b/info/amazon-business?layout=landing) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- Amazon Business でユーザーを作成します。
- アクセスが不要になった場合は、Amazon Business のユーザーを削除します。
- Amazon Business 役割をユーザーに割り当てます。
- Microsoft Entra ID と Amazon Business の間でユーザー属性の同期を維持します。
- Amazon Business でグループとグループ メンバーシップをプロビジョニングします。
- Amazon Business に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amazon-business-tutorial)します (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Amazon Business アカウント。
- 管理者アクセス許可を持つ Amazon Business のユーザー アカウント (Amazon Business アカウント内のすべての法人グループの管理者)。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Amazon Business の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように Amazon Business を構成する

プロビジョニング サービスを構成して有効にするには、ユーザーとグループの両方の既定のグループを特定する必要があります。 次のことをお勧めします

- 最小限の特権の原則に従い、既定のユーザー グループに対するアクセス許可のみを "要求者" に付与します。
- このドキュメント全体でグループを簡単に参照するには、次のセクションで参照されているグループの名前付け規則に従ってください。
    - 既定の SCIM 親グループ
        - これは、Amazon Business の SCIM ディレクトリのルートです。 すべての SCIM グループは、この既定のグループの直下に置かれます。 既存のグループを既定の SCIM 親グループとして選択できます。
    - 既定の SCIM ユーザー グループ
        - Amazon Business アプリに割り当てられているユーザーは、既定で、要求者ロールを持つこのグループに配置されます。 このグループは、既定の SCIM 親グループと同じレベルにすることをお勧めします。
        - ユーザーがグループの割り当てなしでプロビジョニングされている場合、既定では、ユーザーは要求者ロールを使用してこのグループに配置されます。
        - 非アクティブ化されたユーザーは、このグループに残ります。 そのため、このグループには、要求者以外のロールを使用しないことをお勧めします。

注

- 既定の SCIM 親グループは、SSO 構成用に選んだ既定のグループと同じにすることができます。
- 既定の SCIM 親グループは [法人] グループにすることができます。 AB アカウント内のグループごとに異なる請求書テンプレートを設定している場合は、既定のグループとして [法人] を選ぶことをお勧めします。
- 現在、Amazon Business アカウント内の 1 つの法人に対してのみ、SCIM を有効にすることがサポートされています。

既定の SCIM グループが特定されたら、Amazon Business アカウント &gt; ビジネス設定 &gt; IDENTITY Management (SCIM) ページに移動し、詳細を入力して [アクティブ化] を選択します。 次の手順に進む前に、この手順を完了する必要があります。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Amazon Business を追加する

Microsoft Entra アプリケーション ギャラリーから Amazon Business を追加して、Amazon Business へのプロビジョニングの管理を開始します。 以前に SSO 用に Amazon Business を設定している場合は、その同じアプリケーションを使用することができます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Amazon Business への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて Amazon Business のユーザーやグループを作成、更新、無効化するよう、 Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Amazon Business の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で、 **[Amazon Business]** を選択します。

    [Image: アプリケーション リストにある Amazon Business のリンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションで、Amazon Business のテナント URL、承認エンドポイントを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Amazon Business に接続できることを確認します。 接続できない場合は、使用中の Amazon Business アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: トークンのスクリーンショット。]

    **テナント URL** と**承認エンドポイント**の値については、次の表を使用します。

    | 国/地域 | テナントの URL | Authorization endpoint (承認エンドポイント) |
    | --- | --- | --- |
    | カナダ | https://na.business-api.amazon.com/scim/v2/ | https://www.amazon.ca/b2b/abws/oauth?state=1&redirect\_uri=https://portal.azure.com/TokenAuthorize&applicationId=amzn1.sp.solution.ee27ec8c-1ee9-4c6b-9e68-26bdc37479d3 |
    | ドイツ | https://eu.business-api.amazon.com/scim/v2/ | https://www.amazon.de/b2b/abws/oauth?state=1&redirect\_uri=https://portal.azure.com/TokenAuthorize&applicationId=amzn1.sp.solution.ee27ec8c-1ee9-4c6b-9e68-26bdc37479d3 |
    | スペイン | https://eu.business-api.amazon.com/scim/v2/ | https://www.amazon.es/b2b/abws/oauth?state=1&redirect\_uri=https://portal.azure.com/TokenAuthorize&applicationId=amzn1.sp.solution.ee27ec8c-1ee9-4c6b-9e68-26bdc37479d3 |
    | フランス | https://eu.business-api.amazon.com/scim/v2/ | https://www.amazon.fr/b2b/abws/oauth?state=1&redirect\_uri=https://portal.azure.com/TokenAuthorize&applicationId=amzn1.sp.solution.ee27ec8c-1ee9-4c6b-9e68-26bdc37479d3 |
    | イギリス | https://eu.business-api.amazon.com/scim/v2/ | https://www.amazon.co.uk/b2b/abws/oauth?state=1&redirect\_uri=https://portal.azure.com/TokenAuthorize&applicationId=amzn1.sp.solution.ee27ec8c-1ee9-4c6b-9e68-26bdc37479d3 |
    | インド | https://eu.business-api.amazon.com/scim/v2/ | https://www.amazon.in/b2b/abws/oauth?state=1&redirect\_uri=https://portal.azure.com/TokenAuthorize&applicationId=amzn1.sp.solution.ee27ec8c-1ee9-4c6b-9e68-26bdc37479d3 |
    | イタリア | https://eu.business-api.amazon.com/scim/v2/ | https://www.amazon.it/b2b/abws/oauth?state=1&redirect\_uri=https://portal.azure.com/TokenAuthorize&applicationId=amzn1.sp.solution.ee27ec8c-1ee9-4c6b-9e68-26bdc37479d3 |
    | 日本 | https://jp.business-api.amazon.com/scim/v2/ | https://www.amazon.co.jp/b2b/abws/oauth?state=1&redirect\_uri=https://portal.azure.com/TokenAuthorize&applicationId=amzn1.sp.solution.ee27ec8c-1ee9-4c6b-9e68-26bdc37479d3 |
    | メキシコ | https://na.business-api.amazon.com/scim/v2/ | https://www.amazon.com.mx/b2b/abws/oauth?state=1&redirect\_uri=https://portal.azure.com/TokenAuthorize&applicationId=amzn1.sp.solution.ee27ec8c-1ee9-4c6b-9e68-26bdc37479d3 |
    | アメリカ | https://na.business-api.amazon.com/scim/v2/ | https://www.amazon.com/b2b/abws/oauth?state=1&redirect\_uri=https://portal.azure.com/TokenAuthorize&applicationId=amzn1.sp.solution.ee27ec8c-1ee9-4c6b-9e68-26bdc37479d3 |
    | オーストラリア | https://jp.business-api.amazon.com/scim/v2/ | https://www.amazon.com.au/b2b/abws/oauth?state=1&redirect\_uri=https://portal.azure.com/TokenAuthorize&applicationId=amzn1.sp.solution.ee27ec8c-1ee9-4c6b-9e68-26bdc37479d3 |
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Amazon Business に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Amazon Business のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Amazon Business API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Amazon Business で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | externalId | 糸 |  |  |
    | roles | appRoleAssignments の一覧 [appRoleAssignments] |  |  |
12. グループを選択 **します**。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Amazon Business に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Amazon Business のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Amazon Business で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | 関連項目 |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 機能の制限

- Amazon Business アカウントにフラット構造が作成されます。つまり、プッシュされたグループはすべて、既定の SCIM グループ配下の同じレベルに配置されます。 入れ子になった構造/階層はサポートされていません。
- グループ名は、Azure アカウントと Amazon Business アカウントで同じです。
- Amazon Business アカウントで新しいグループが作成されると、管理者は必要に応じて新しいグループのビジネス設定 (購入の有効化、共有設定の更新、ガイド付き購入ポリシーの追加など) を再構成する必要があります。
- 古いグループを削除する、または Amazon Business の古いグループからユーザーを削除すると、そのグループで行われた注文に関する情報の可視性を失うことになるため、慎重に対応することをお勧めします。
    - 古いグループ/割り当ては削除されず、
    - 古いグループでの購入が無効化されます。
- 電子メール/ユーザー名の更新 - 現時点では、SCIM を介した電子メールやユーザー名の更新はサポートされていません。
- パスワード同期 - パスワード同期はサポートされていません。
- SSO 要件 - Amazon Business アプリでは SSO を使用せずに SCIM プロビジョニングをアクティブ化することができますが、プロビジョニングされたユーザーが Amazon Business にアクセスするには SSO 認証が必要です。
- 複数法人 (MLE) アカウント - 現在、Amazon Business アカウント内の複数の法人に対する SCIM の有効化はサポートされていません。
- Amazon Business の既存のグループと同じ名前のグループをプロビジョニングすると、これらのグループは自動的にリンクされます (Amazon Business で新しいグループが作成されます)。

### トラブルシューティングのヒント

- Amazon Business 管理者が、SSO のみを使用してログインしているか、パスワードを忘れた場合は、パスワードを忘れた場合のフローを使用してパスワードをリセットした後、Amazon Business にサインインできます。
- 管理者ロールと要求者ロールがグループ内の顧客に既に適用されている場合、Finance ロールまたは Tech ロールを割り当てても、Amazon Business 側では更新されません。
- MASE アカウント (同じメール アドレスの複数アカウント) を持っていて、そのうちの 1 つを削除したユーザーには、新しいユーザーをプロビジョニングするときにアカウントが存在しないというエラーが短期間 (24 から 48 時間) 表示されることがあります。
- 顧客は、オンデマンドプロビジョニングを使用してすぐに削除することはできません。 プロビジョニングを有効にする必要があります。削除は、アクションが実行されてから 40 分後に行われます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/amazon-business-tutorial"} -->
## Microsoft Entra ID で Amazon Business for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amazon-business-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra IDと Amazon Business の間でシングル サインオンを構成する方法について説明します。

この記事では、Amazon Business と Microsoft Entra ID を統合する方法について説明します。 amazon Business と Microsoft Entra ID を統合すると、次のことができます。

- Amazon Business にaccessしたユーザーをMicrosoft Entra IDで制御できます。
- ユーザーが Microsoft Entra アカウントで Amazon ビジネスに自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Amazon ビジネスでのシングル サインオン (SSO) が有効なサブスクリプション。 [Amazon Business](https://business.amazon.com) ページに移動して、Amazon Business アカウントを作成します。

### シナリオの説明

この記事では、既存の Amazon Business アカウントで Microsoft Entra SSO を構成し、テストします。

- Amazon ビジネスでは、**SP と IDP** によって開始される SSO がサポートされます。
- Amazon Business では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。
- Amazon Business では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amazon-business-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Amazon ビジネスの追加

Microsoft Entra IDへの Amazon Business の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Amazon Business を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Amazon Business」**と入力します。
4. 結果パネルから **Amazon Business** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細については](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)を参照してください。

### Amazon ビジネスに対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Amazon Business に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Amazon ビジネスの関連ユーザーの間にリンク関係を確立する必要があります。

Amazon ビジネスに対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Configure Microsoft Entra SSO**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Amazon Business の SSO を構成**する - アプリケーション側でシングル サインオン設定を構成します。
    1. **Amazon Business のテストユーザーを作成** - B.Simon を Amazon Business で再現し、Microsoft Entra 上のユーザーとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Amazon Business** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードで構成する場合は、次の手順を実行します。

    1. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかの URL を入力します。

        | URL | リージョン |
        | --- | --- |
        | `https://www.amazon.com` | North America |
        | `https://www.amazon.co.jp` | 東アジア |
        | `https://www.amazon.de` | ヨーロッパ |
    2. [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

        | URL | リージョン |
        | --- | --- |
        | `https://www.amazon.com/bb/feature/sso/action/3p_redirect?idpid={idpid}` | North America |
        | `https://www.amazon.co.jp/bb/feature/sso/action/3p_redirect?idpid={idpid}` | 東アジア |
        | `https://www.amazon.de/bb/feature/sso/action/3p_redirect?idpid={idpid}` | ヨーロッパ |

        注

        応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 `<idpid>`値は、Amazon Business SSO の構成セクションから取得します。これについては、この記事の後半で説明します。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. **SP** 開始モードでアプリケーションを構成する場合は、[追加 URL の設定] セクションの**サインオン URL に**、Amazon Business 構成で指定された完全な URL **を追加する**必要があります。
7. 次のスクリーンショットには、既定の属性一覧が示されています。 User Attributes & Claims セクションの 鉛筆 アイコンを選択して、属性を編集します。

    [Image: スクリーンショットは、Givenname user.givenname や Emailaddress user.mail などの既定値を持つユーザー属性と要求を示しています。]
8. 属性を編集し、これらの属性の **名前空間** の値をメモ帳にコピーします。

    [Image: [ユーザー属性] と [要求] と [要求の名前と値] 列を示すスクリーンショット。]
9. その他に、Amazon ビジネス アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [**グループ**要求] ダイアログの [**ユーザー属性と要求**] セクションで、次の手順を実行します。

    1. **[要求で返されるグループ]** の横にある**ペン**を選択します。

        [Image: このスクリーンショットは、[クレームで返されるグループ] のアイコンが選択された状態の [ユーザー属性 & 要求] を示しています。]
    2. [ **グループ要求** ] ダイアログで、無線リストから **[すべてのグループ** ] を選択します。
    3. **[ソース] 属性**として **[グループ ID] を**選択します。
    4. [ **グループ要求の名前をカスタマイズする** ] チェック ボックスをオンにし、組織の要件に従ってグループ名を入力します。
    5. **[保存] を選択します**。
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. [ **Amazon Business のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Azure portalで Microsoft Entra セキュリティ グループを割り当てる

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Amazon Business** を参照します。
3. アプリケーションの一覧で、「 **Amazon Business**」と入力して選択します。
4. 左側のメニューで、[ **ユーザーとグループ**] を選択します。
5. [ **追加されたユーザー**] を選択します。
6. 使用するセキュリティ グループを検索し、グループを選択して [メンバーの選択] セクションに追加します。 [ **選択] を選択**し、[ **割り当て]** を選択します。

    [Image: セキュリティ グループの検索]

    注

    メニュー バーの通知で、グループがエンタープライズ アプリケーションに正常に割り当てられたことを確認します。

### Amazon ビジネス SSO の構成

1. 別の Web ブラウザー ウィンドウで、Amazon ビジネス企業サイトに管理者としてサインインします。
2. **[ユーザー プロファイル**] を選択し**、[ビジネス設定]** を選択します。

    [Image: ユーザー プロファイル]
3. **システム統合**ウィザードで、**単一 Sign-On (SSO)** を選択します。

    [Image: シングル サインオン (SSO)]
4. SSO の **セットアップ** ウィザードで、組織の要件に従ってプロバイダーを選択し、[ **次へ**] を選択します。

    注

    Microsoft ADFS はオプションとして表示されていますが、Microsoft Entra SSO では機能しません。
5. **[新しいユーザー アカウントの既定値**] ウィザードで、**既定のグループ**を選択し、組織のユーザー ロールに従って **[既定の購入ロール**] を選択し、[**次へ**] を選択します。

    [Image: スクリーンショットには、[新しいユーザー アカウントの既定値] が表示され、[Microsoft S S O]、[要求者]、[次へ] が選択されています。]
6. **メタデータ ファイルのアップロード** ウィザードで、[**XML リンクの貼り付け**] オプションを選択して**アプリのフェデレーション メタデータ URL** の値を貼り付け、[**検証**] を選択します。

    注

    または、[XML ファイルのアップロード] オプションを選択して **、フェデレーション メタデータ XML** **ファイルをアップロード** することもできます。
7. ダウンロードしたメタデータ ファイルをアップロードすると、[ **接続データ** ] セクションのフィールドが自動的に設定されます。 その後、[ **次へ**] を選択します。
8. **属性ステートメントのアップロード** ウィザードで、[**スキップ**] を選択します。

    [Image: [属性ステートメントのアップロード] を示すスクリーンショット。属性ステートメントを参照できますが、この場合は [スキップ] を選択します。]
9. **属性マッピング** ウィザードで、[+ フィールドの追加] オプションを選択して要件**フィールドを追加します**。 Azure portal の [User Attributes & Claims] セクションからコピーした名前空間を含む属性値を SAML AttributeName フィールドに追加し、Next を選択します。

    [Image: スクリーンショットは、Amazon データの SAML 属性名を編集できる属性マッピングを示しています。]
10. **Amazon 接続データ** ウィザードで、IDP が構成されていることを確認し、[**続行**] を選択します。

    [Image: Amazon 接続データを示すスクリーンショット。次に選択して続行できます。]
11. 構成されている手順の **状態** を確認し、[ **テストの開始**] を選択します。
12. **SSO 接続のテスト** ウィザードで、[**テスト**] を選択します。

    [Image: スクリーンショットは、[テスト] ボタンを使用したテスト S S O 接続を示しています。]
13. **IDP 開始 URL** ウィザードで、[**アクティブ化**] を選択する前に、**idpid** に割り当てられている値をコピーし、[**基本的な SAML 構成**] セクションの**応答 URL** の **idpid** パラメーターに貼り付けます。

    [Image: スクリーンショットは、IDP によって開始された URL を示しています。ここでテストに必要な URL を取得し、[アクティブ化] を選択できます。]
14. アクティブ **な SSO に切り替える準備ができましたか?** ウィザードで、[ **SSO が完全にテスト済みで、ライブに移行する準備ができました** ] チェック ボックスをオンにして、[ **アクティブに切り替える**] を選択します。

    [Image: [アクティブに切り替える] を選択できる [アクティブな S S O に切り替える準備ができましたか] の確認を示すスクリーンショット。]
15. 最後に、[ **SSO 接続の詳細]** セクションで **、[状態]** が **[アクティブ]** と表示されます。

    注

    **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行し、[追加 URL の設定] セクションの [サインオン URL] テキスト ボックスに、上のスクリーンショットの**サインオン URL** **を**貼り付けます。 次の形式を使用します。

    `https://www.amazon.<TLD>/bb/feature/sso/action/start?domain_hint=<UNIQUE_ID>`

#### Amazon ビジネスのテスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Amazon ビジネス内に作成します。 Amazon ビジネスでは、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションでは、ユーザー側で必要な操作はありません。 Amazon ビジネス内にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる Amazon Business のサインオン URL にリダイレクトされます。
- Amazon ビジネスのシングル サインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Amazon Business に自動的にサインインします。

Microsoft My Appsを使用して、任意のモードでアプリケーションをテストすることもできます。 My Appsで [Amazon Business] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Amazon Business に自動的にサインインされます。 My Appsの詳細については、「[My Apps への紹介](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照。

### ADFS から Microsoft Entra ID へのサービス プロバイダー設定の再構成

1. Microsoft Entra ID環境の準備

    1. Microsoft Entra ID Premium サブスクリプションを確認します。シングル サインオン (SSO) やその他の高度な機能に必要な Microsoft Entra ID Premium サブスクリプションがあることを確認してください。
2. アプリケーションをMicrosoft Entra IDに登録する

    1. Azure portal内のMicrosoft Entra IDに移動します。
    2. [App registrations] &gt; [新規登録] を選択します。
    3. 必須の詳細を入力します。
        1. 名前: アプリケーションにわかりやすい名前を入力します。
        2. サポートされているアカウントの種類: 環境に合った適切なオプションを選択します。
        3. リダイレクト URI: 必要なリダイレクト URI (通常はアプリケーションのサインイン URL) を入力します。
3. Microsoft Entra ID SSO の構成

    1. Microsoft Entra IDでシングル サインオンを設定します。
    2. Azure portalで、Microsoft Entra ID &gt; Enterprise アプリケーションに移動します。
    3. 一覧からアプリケーションを選択します。
    4. [管理] で [シングル サインオン] を選びます。
    5. シングル サインオンの方法として [SAML] を選択します。
    6. [基本的な SAML 構成] を編集します。
        1. 識別子 (エンティティ ID): SP エンティティ ID を入力します。
        2. 応答 URL (アサーションコンシューマーサービス URL): SP ACS URL を入力します。
        3. サインオン URL: アプリケーションのサインオン URL を入力します (該当する場合)。
4. ユーザー属性と要求を構成します。

    1. SAML ベースのサインオン設定で、[ユーザー属性と要求] を選択します。
    2. SP に必要な要求と一致するように要求を編集して構成します。 通常、これには次の内容が含まれます。
        1. 名前識別子
        2. Email
        3. GivenName
        4. 名字
        5. その他。
5. Microsoft Entra ID の SSO メタデータをダウンロードする
6. [SAML 署名証明書] セクションで、[フェデレーション メタデータ XML] をダウンロードします。 これは SP の構成に使用されます。
7. サービス プロバイダー (SP) を再構成します。

    1. Microsoft Entra ID メタデータを使用するように SP を更新する
    2. SP の構成設定にアクセスします。
    3. IdP メタデータ URL を更新するか、Microsoft Entra IDメタデータ XML をアップロードします。
    4. アサーションコンシューマーサービス (ACS) の URL、エンティティ ID、およびその他の必須フィールドを、Microsoft Entra ID 構成と一致するように更新します。
8. SAML 証明書を構成します。
9. SP が、Microsoft Entra IDからの署名証明書を信頼するように構成されていることを確認します。 これは、Microsoft Entra ID SSO 構成の SAML 署名証明書セクションにあります。
10. SSO 構成をテストします。
11. SP からテスト ログインを開始します。
12. 認証がMicrosoft Entra IDにリダイレクトされ、ユーザーに正常にログインすることを確認します。
13. 渡される要求をチェックして、SP が想定する内容と一致していることを確認します。
14. DNS とネットワーク設定を更新します (該当する場合)。 SP またはアプリケーションで ADFS に固有の DNS 設定を使用している場合は、これらの設定を更新して、Microsoft Entra IDエンドポイントを指す必要がある場合があります。
15. ロールアウトして、監視します。

    1. ユーザーと通信します。ユーザーに変更を通知し、必要な指示またはドキュメントを提供します。
    2. 認証ログの監視 Microsoft Entra IDサインイン ログを監視して、認証の問題を監視し、迅速に対処します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/amazon-managed-grafana-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Amazon Managed Grafana を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amazon-managed-grafana-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Amazon Managed Grafana の間でシングル サインオンを構成する方法について説明します。

この記事では、Amazon Managed Grafana と Microsoft Entra ID を統合する方法について説明します。 Amazon Managed Grafana と Microsoft Entra ID を統合すると、次のことが可能になります:

- Amazon Managed Grafana にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Amazon Managed Grafana に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- アマゾン ウェブ サービス (AWS) [の無料アカウント](https://aws.amazon.com/free/)。
- Amazon Managed Grafana でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Amazon Managed Grafana では、 **SP** によって開始される SSO がサポートされます。
- Amazon Managed Grafana では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Amazon Managed Grafana を追加する

Microsoft Entra ID への Amazon Managed Grafana の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Amazon Managed Grafana を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックス**に「Amazon Managed Grafana**」と入力します。
4. 結果パネルから **Amazon Managed Grafana** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Amazon Managed Grafana に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Amazon Managed Grafana に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Amazon Managed Grafana の関連ユーザーの間にリンク関係を確立する必要があります。

Amazon Managed Grafana に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Amazon Managed Grafana の SSO を**構成する - アプリケーション側でシングル サインオン設定を構成します。
    1. **Amazon Managed Grafana のテスト ユーザーの作成** - Amazon Managed Grafana で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Amazon Managed Grafana]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<namespace>.grafana-workspace.<region>.amazonaws.com/saml/metadata`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<namespace>.grafana-workspace.<region>.amazonaws.com/login/saml`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 [Amazon Managed Grafana クライアント サポート チーム](https://aws.amazon.com/contact-us/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Amazon Managed Grafana アプリケーションでは、特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Amazon Managed Grafana アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を下記に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | displayName | user.displayname |
    | メール | user.userprincipalname |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Amazon Managed Grafana のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Amazon Managed Grafana SSO の構成

1. Amazon Managed Grafana コンソールに管理者としてログインします。
2. [ **ワークスペースの作成] を選択します**。

    [Image: ワークスペースの作成を示すスクリーンショット。]
3. [ **ワークスペースの詳細の指定** ] ページで、一意の **ワークスペース名** を入力し、[ **次へ**] を選択します。

    [Image: ワークスペースの詳細を示すスクリーンショット。]
4. [ **設定の構成** ] ページで、 **セキュリティ アサーション マークアップ言語 (SAML)** チェック ボックスをオンにし、アクセス許可の種類として **管理されるサービス** を有効にして、[ **次へ**] を選択します。

    [Image: ワークスペースの設定を示すスクリーンショット。]
5. **サービス管理アクセス許可の設定**で、[**現在のアカウント**] を選択し、[**次へ**] を選択します。

    [Image: アクセス許可の設定を示すスクリーンショット。]
6. [ **確認と作成** ] ページで、すべてのワークスペースの詳細を確認し、[ **ワークスペースの作成**] を選択します。

    [Image: スクリーンショットは、確認と作成ページを示しています。]
7. ワークスペースを作成したら、[ **セットアップの完了** ] を選択して SAML 構成を完了します。

    [Image: SAML 構成を示すスクリーンショット。]
8. **[Security Assertion Markup Language(SAML)]** ページで、次の手順を実行します。

    [Image: SAML セットアップを示すスクリーンショット。]

    1. **サービス プロバイダー識別子 (エンティティ ID)** の値をコピーし、[**基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスにこの値を貼り付けます。
    2. **サービス プロバイダーの応答 URL (Assertion Consumer Service URL)** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。
    3. **サービス プロバイダーのログイン URL 値を**コピーし、この値を [**基本的な SAML 構成**] セクションの **[サインオン URL**] テキスト ボックスに貼り付けます。
    4. ダウンロードした **フェデレーション メタデータ XML** をメモ帳に開き、[ファイルの選択] オプションを選択して XML **ファイル** をアップロードします。
    5. [ **アサーション マッピング** ] セクションで、要件に従って必要な値を入力します。
    6. [ **SAML 構成の保存] を選択します**。

#### Amazon Managed Grafana のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Amazon Managed Grafana に作成します。 Amazon Managed Grafana では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Amazon Managed Grafana にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Amazon Managed Grafana のサインオン URL にリダイレクトされます。
- Amazon Managed Grafana のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Amazon Managed Grafana] タイルを選択すると、このオプションは Amazon Managed Grafana のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/amazon-web-service-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に AWS Single-Account Access を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amazon-web-service-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-15
- Summary: Microsoft Entra ID と AWS Single-Account Access の間でシングル サインオンを構成する方法について説明します。

この記事では、AWS Single-Account Access と Microsoft Entra ID を統合する方法について説明します。 AWS Single-Account Access を Microsoft Entra ID と統合すると、次のことが可能になります。

- AWS Single-Account Access にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで AWS Single-Account Access に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### Microsoft Entra アプリケーション ギャラリーのさまざまな AWS アプリケーションについて

下記の情報を利用して、Microsoft Entra アプリケーション ギャラリーの AWS Single Sign-On アプリケーションと AWS Single-Account Access アプリケーションのどちらを使用するかを判断します。

**AWS シングル サインオン**

[AWS Single Sign-On](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aws-single-sign-on-tutorial) は、2021 年 2 月に Microsoft Entra アプリケーション ギャラリーに追加されました。 Microsoft Entra ID を通してサインインすると、複数の AWS アカウントや AWS アプリケーションへのアクセスを一元的に管理しやすくなります。 Microsoft Entra ID と AWS SSO のフェデレーションを一度実行し、AWS SSO を使用して、すべての AWS アカウントにわたるアクセス許可を 1 つの場所から管理します。 AWS SSO では、アクセス許可が自動的にプロビジョニングされ、それらは、ポリシーやアクセスの割り当てを更新するときに最新の状態に維持されます。 エンド ユーザーは、自分の Microsoft Entra 資格情報を使用して認証し、AWS コンソール、コマンド ライン インターフェイス、AWS SSO 統合アプリケーションにアクセスすることができます。

**AWS Single-Account Access**

AWS Single-Account Access は、これまで数年間にわたってお客様に使用されており、これを使用すると、Microsoft Entra ID を 1 つの AWS アカウントにフェデレーションし、Microsoft Entra ID を使用して AWS IAM ロールへのアクセスを管理できます。 AWS IAM 管理者は、各 AWS アカウントにロールとポリシーを定義します。 AWS アカウントごとに、Microsoft Entra 管理者は、AWS IAM へのフェデレーション、アカウントへのユーザーまたはグループの割り当て、ロールのアクセスを承認するアサーションを送信するための Microsoft Entra ID の構成を行います。

| 機能 | AWS Single Sign-On | AWS シングルアカウントアクセス |
| --- | --- | --- |
| 条件付きアクセス | すべての AWS アカウントに対する単一の条件付きアクセス ポリシーがサポートされます。 | すべてのアカウントに対する単一の条件付きアクセス ポリシーまたはアカウントごとのカスタム ポリシーがサポートされます。 |
| CLI アクセス | サポート対象 | サポート対象 |
| Privileged Identity Management | [サポート対象](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aws-single-sign-on-provisioning-tutorial#just-in-time-jit-application-access-with-pim-for-groups) | サポート対象外 |
| アカウント管理の一元化 | AWS でのアカウント管理を一元化します。 | Microsoft Entra ID でアカウント管理を一元化します (アカウントごとに Microsoft Entra エンタープライズ アプリケーションが必要な場合があります)。 |
| SAML 証明書 | 単一の証明書 | アプリやアカウントごとに別個の証明書 |

### AWS Single-Account Access のアーキテクチャ

[Image: Microsoft Entra ID と AWS の関係を示すスクリーンショット。]

複数のインスタンスに対して複数の識別子を構成できます。 例えば次が挙げられます。

- `https://signin.aws.amazon.com/saml#1`
- `https://signin.aws.amazon.com/saml#2`

Microsoft Entra ID はこれらの値から **#** の値を削除し、正しい値 `https://signin.aws.amazon.com/saml` を SAML トークンの対象 URL として送信します。

次の理由により、このアプローチをお勧めします。

- アプリケーションごとに一意の X509 証明書が提供されます。 AWS アプリのインスタンスごとに異なる証明書の有効期限を設定でき、それらを個別の AWS アカウントに基づいて管理できます。 この場合、証明書全体のロールオーバーが容易になります。
- Microsoft Entra ID での AWS アプリによるユーザー プロビジョニングを有効にでき、続いてその AWS アカウントからすべてのロールが Microsoft のサービスによってフェッチされます。 アプリでの AWS ロールの追加や更新は手動で行う必要はありません。
- アプリに対してアプリ所有者を個別に割り当てることができます。 このユーザーは、Microsoft Entra ID で直接アプリを管理できます。

注

必ずギャラリー アプリケーションのみを使用してください。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- AWS で IAM IdP が有効なサブスクリプション。
- クラウド アプリケーション管理者に加え、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができる。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

注

ロールのインポートを行うときは、Microsoft Entra ID でロールを手動で編集しないでください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AWS Single-Account Access では、**SP および IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの AWS Single-Account Access の追加

AWS Single-Account Access と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に AWS Single-Account Access をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**AWS Single-Account Access**」と入力します。
4. 結果のパネルから **[AWS Single-Account Access]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 Microsoft 365 ウィザード [詳細については、](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)を参照してください。

### AWS Single-Account Access 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、AWS Single-Account Access 用の Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、AWS Single-Account Access での関連ユーザーとの間にリンク関係を確立する必要があります。

AWS Single-Account Access で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AWS Single-Account Access の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AWS Single-Account Access のテストユーザーを作成** - AWS Single-Account Access で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーにリンクさせます。
    2. **AWS Single-Account Access でロール プロビジョニングを構成する方法**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AWS Single-Account Access**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **識別子 (エンティティ ID)** と **応答 URL** の両方を正しい値で更新します。 **[保存]** を選択して構成の変更を保存する必要があります。

注

AWS アプリケーションの複数のインスタンスを構成する場合は、**応答 URL** 値に **#1** や **#2** などのサフィックスを追加しないでください。 正しい **応答 URL** 値については、AWS のドキュメントを参照してください。

1. AWS アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 既定の属性を示すスクリーンショット。]
2. その他に、AWS アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 | 名前空間 |
    | --- | --- | --- |
    | RoleSessionName | user.userprincipalname | `https://aws.amazon.com/SAML/Attributes` |
    | 役割 | user.assignedroles | `https://aws.amazon.com/SAML/Attributes` |
    | セッション時間 | user.sessionduration | `https://aws.amazon.com/SAML/Attributes` |

    注

    AWS では、アプリケーションに対してユーザーのロールが割り当てられていることを想定しています。 ユーザーに適切なロールを割り当てることができるように、Microsoft Entra ID でこれらのロールを設定してください。 Microsoft Entra ID でロールを構成する方法については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)をご覧ください。
3. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** (手順 3) ダイアログ ボックスで、 **[証明書の追加]** を選択します。

    [Image: 新しい SAML 証明書の作成を示すスクリーンショット。]
4. 新しい SAML 署名証明書を生成し、 **[新しい証明書]** を選択します。 証明書通知のメール アドレスを入力します。

    [Image: 新しい SAML 証明書を示すスクリーンショット。]
5. (オプション) **[証明書をアクティブにする]** を選択できます。
6. **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。
7. **[AWS Single-Account Access のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: [構成 URL のコピー] を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AWS Single-Account Access SSO の構成

1. 別のブラウザー ウィンドウで、管理者として AWS 企業サイトにサインオンします。
2. AWS のホーム ページで、 **IAM** を検索して選択します。

    [Image: IAM が強調表示された AWS サービス ページのスクリーンショット。]
3. **アクセス管理**&gt;**Identity Providers** に移動し、[プロバイダーの**追加]** ボタンを選択します。

    [Image: [ID プロバイダー] と [プロバイダーの作成] が強調表示された IAM ページのスクリーンショット。]
4. **[ID プロバイダーの追加]** ページで、次の手順を実行します。

    [Image: [プロバイダーの設定] のスクリーンショット。]

    a. **[Provider type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロバイダーの種類)** で **[SAML]** を選択します。

    b。 **[プロバイダー名]** にプロバイダー名を入力します (例: *WAAD*)。

    c. ダウンロードした**メタデータ ファイル**をアップロードするには、 **[ファイルの選択]** をクリックします。

    d. [ **プロバイダーの追加] を選択します**。
5. **[ロール]**&gt;**[ロールの作成]** を選択します。

    [Image: [ロール] ページのスクリーンショット。]
6. **[Create role]** ページで、以下の手順を実行します。

    [Image: [ロールの作成] ページのスクリーンショット。]

    a. **[信頼されたエンティティの種類]** を選択し、**[SAML 2.0 フェデレーション]** を選択します。

    b。 **[SAML 2.0 ベースのプロバイダー]** で、先ほど作成した **SAML プロバイダー**を選択します (例: *WAAD*)。

    c. **[プログラミングによるアクセスおよび AWS 管理コンソール アクセスを許可する]** を選択します。

    d. **[次へ]** を選択します。
7. **[アクセス許可ポリシー]** ダイアログ ボックスで、組織の規定に準拠した適切なポリシーをアタッチします。 **[次へ]** を選択します。

    [Image: アクセス許可ポリシーをアタッチするダイアログ ボックスのスクリーンショット。]
8. **[確認]** ダイアログ ボックスで、次の手順を行います。

    [Image: [確認] ダイアログ ボックスのスクリーンショット。]

    a. **[ロール名]** に、使用するロール名を入力します。

    b。 **[説明]** に、ロールの説明を入力します。

    c. **[ロールの作成]** を選択します。

    d. 必要な数のロールを作成し、それらを ID プロバイダーにマップします。
9. Microsoft Entra ユーザー プロビジョニングの際に AWS アカウントからロールをフェッチするには、AWS サービス アカウントの資格情報を使用します。 そのためには、AWS コンソール ホームを開きます。
10. [IAM] セクションで、[ **ポリシー** ] を選択し、[ **ポリシーの作成**] を選択します。

    [Image: [ポリシー] が強調表示された [IAM] セクションのスクリーンショット。]
11. AWS アカウントからすべてのロールをフェッチする独自のポリシーを作成します。

    [Image: [JSON] が強調表示された [ポリシーの作成] ページのスクリーンショット。]

    a. **[ポリシーの作成]** セクションで、 **[JSON]** タブを選択します。

    b。 ポリシー ドキュメントで、次の JSON を追加します。

    ```json
    {
        "Version": "2012-10-17",
        "Statement": [
            {
                "Effect": "Allow",
                "Action": [
                "iam:ListRoles"
                ],
                "Resource": "*"
            }
        ]
    }
    ```

    c. **[次へ: タグ]** を選択します。
12. 次のページで必要なタグを追加し、[ **次へ: 確認**] を選択することもできます。

    [Image: [ポリシー タグの作成] ページのスクリーンショット。]
13. 新しいポリシーを定義します。 [Image: [名前] と [説明] のフィールドが強調表示された [ポリシーの作成] ページのスクリーンショット。]

    a. **[名前]** に「**AzureAD\_SSOUserRole\_Policy**」と入力します。

    b。 ポリシーの **[説明]** に、「**This policy will allow to fetch the roles from AWS accounts**」と入力します。

    c. **[ポリシーの作成]** を選択します。
14. AWS IAM サービスの新しいユーザー アカウントを作成します。 a. AWS IAM コンソールで、[ **ユーザー** ] を選択し、[ **ユーザーの追加]** を選択します。

    [Image: [ユーザー] が強調表示された AWS IAM コンソールのスクリーンショット。]

    b。 **[ユーザーの詳細の指定]** セクションで、ユーザー名として「**AzureADRoleManager**」を入力し、**[次へ]** を選択します。

    [Image: [ユーザー名] と [アクセスの種類] が強調表示された [ユーザーを追加] ページのスクリーンショット。]

    c. このユーザー用の新しいポリシーを作成します。

    [Image: このスクリーンショットは、[Add user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーを追加) ページを示しています。ここで、ユーザーのポリシーを作成できます。]

    d. **[既存のポリシーを直接アタッチ]** を選択します。

    e. [フィルター] セクションで、新しく作成されたポリシー **AzureAD\_SSOUserRole\_Policy** を検索します。

    f. ポリシーを選択し、**[次へ]** を選択します。
15. 選択内容を確認し、**[ユーザーの作成]** を選択します。

    注

    このユーザーのサード パーティのアクセス キーを作成してダウンロードしてください。 このキーは、Microsoft Entra のユーザープロビジョニングセクションで使用され、AWS コンソールからロールを取得するために用いられます。
16. ユーザーのユーザー資格情報をダウンロードするには、[ **セキュリティ資格情報** ] タブでコンソール アクセスを有効にします。 [Image: [セキュリティ資格情報] を示すスクリーンショット。]
17. これらの資格情報を Microsoft Entra の [ユーザー プロビジョニング] セクションに入力して、AWS コンソールからロールをフェッチします。 [Image: ユーザー資格情報のダウンロードを示すスクリーンショット。]

注

AWS の SSO を構成するには、一連のアクセス許可/制限が必要です。 AWS の制限の詳細については、 [こちらの](https://docs.aws.amazon.com/singlesignon/latest/userguide/limits.html) ページを参照してください。

#### AWS Single-Account Access でロール プロビジョニングを構成する方法

1. Microsoft Entra 管理ポータルの AWS アプリで、 **[プロビジョニング]** に移動します。

    [Image: [プロビジョニング] が強調表示された AWS アプリのスクリーンショット。]

**手記：** コンソール アクセスを有効にするときに返されるユーザー名とパスワードは、clientsecret 値とシークレット トークン値に必要な値ではありません。 代わりに、このステップ用にサード パーティのアクセス キーを作成します。

1. アクセス キーとシークレットをそれぞれ **[クライアント シークレット]** フィールドと **[シークレット トークン]** フィールドに入力します。

    [Image: [管理者資格情報] ダイアログ ボックスのスクリーンショット。]

    a. AWS ユーザーのアクセス キーを **[clientsecret]/(clientsecret/)** フィールドに入力します。

    b。 AWS ユーザー シークレットを **[シークレット トークン]** フィールドに入力します。

    c. **[テスト接続]** を選択します。

    d. **[保存]** を選択して設定を保存します。
2. **[設定]** セクションの **[プロビジョニング状態]** で **[オン]** を選択します。 次に、 **[保存]** を選択します。

    [Image: [オン] が強調表示された [設定] セクションのスクリーンショット。]

注

プロビジョニング サービスは、AWS から Microsoft Entra ID にロールをインポートするだけです。 このサービスは、Microsoft Entra ID から AWS にユーザーとグループをプロビジョニングしません。

注

プロビジョニングの資格情報を保存したら、初回同期サイクルが実行されるまで待機する必要があります。 同期には通常、約 40 分かかります。 その状態は、 **[プロビジョニング]** ページ下部の **[現在の状態]** で確認できます。

#### AWS Single-Account Access のテスト ユーザーの作成

このセクションの目的は、AWS Single-Account Access で B.Simon というユーザーを作成することです。 AWS Single-Account Access では、SSO 用にユーザーをシステムに作成する必要がないため、ここで操作を実行する必要はありません。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる AWS Single-Account アクセス サインオン URL にリダイレクトされます。
- AWS Single-Account Access のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した AWS Single-Account Access に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [AWS Single-Account Access] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した AWS Single-Account Access に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。

### 既知の問題

- AWS Single-Account Access プロビジョニング統合は、AWS 中国リージョンでは使用できません。
- **[プロビジョニング]** セクションの **[マッピング]** サブセクションには、"読み込み中..." というメッセージが表示され、属性マッピングは表示されません。 現在サポートされている唯一のプロビジョニング ワークフローは、ユーザーまたはグループ割り当て時の選択のために、AWS から Microsoft Entra ID にロールをインポートすることです。 このための属性マッピングは事前に決定されており、構成はできません。
- **[準備中]** セクションでは、1 つの AWS テナントに対して、一度に 1 セットの資格情報の入力だけがサポートされています。 インポートされたすべてのロールは、AWS テナントの Microsoft Entra ID `appRoles`の `servicePrincipal` プロパティに書き込まれます。

    プロビジョニングのために複数の AWS テナント (`servicePrincipals` によって表される) をギャラリーから Microsoft Entra ID に追加できます。 ただし、プロビジョニングに使用される複数の AWS `servicePrincipals` からインポートされたすべてのロールを、SSO に使用される単一の `servicePrincipal` に自動的に書き込むことができないという既知の問題があります。

    回避策として、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal) を使用して、プロビジョニングが構成されている各 AWS `appRoles` にインポートされたすべての `servicePrincipal` を抽出できます。 その後、これらのロール文字列を、SSO が構成されている AWS `servicePrincipal` に追加できます。
- AWS から Microsoft Entra ID へのインポート対象となるロールは、次の要件を満たす必要があります。

    - ロールには、AWS で SAML プロバイダーが 1 つだけ定義されている必要があります。
    - ロールの ARN (Amazon Resource Name) と、関連付けられている SAML プロバイダーの ARN を組み合わせた長さが 240 文字未満である必要があります。

### ログの変更

- 01/12/2020 - ロールの長さの上限が 119 文字から 239 文字に増加。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/amms-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に AMMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amms-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AMMS の間のシングル サインオンを構成する方法について説明します。

この記事では、AMMS と Microsoft Entra ID を統合する方法について説明します。 AMMS を Microsoft Entra ID と統合すると、次のことが可能になります。

- AMMS へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して AMMS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

AMMS と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- AMMS でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- AMMS では、**SP** Initiated SSO がサポートされます。

### ギャラリーから AMMS を追加する

Microsoft Entra ID への AMMS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に AMMS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**AMMS**」と入力します。
4. 結果パネルから **[AMMS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AMMS に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、AMMS に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと AMMS の関連ユーザーとの間にリンク関係を確立する必要があります。

AMMS に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AMMS の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AMMS テスト ユーザーの作成** - B.Simon の AMMS 内での対応となるテスト ユーザーを作成し、そのユーザーを Microsoft Entra 上のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**AMMS**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `<SUBDOMAIN>.microwestcloud.com/amms`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.microwestcloud.com/amms/pages/login.aspx`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 この値を取得するには、[AMMS クライアント サポート チーム](mailto:techsupport@microwestsoftware.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AMMS SSO の構成

**AMMS** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [AMMS サポート チーム](mailto:techsupport@microwestsoftware.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### AMMS のテスト ユーザーの作成

このセクションでは、AMMS で Britta Simon というユーザーを作成します。 [AMMS サポート チーム](mailto:techsupport@microwestsoftware.com)と連携して AMMS プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる AMMS サインオン URL にリダイレクトされます。
- AMMS のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [AMMS] タイルを選択すると、このオプションは AMMS のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/amplified-tutorial"} -->
## Microsoft Entra ID を使用して Amplified をシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amplified-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Amped の間でシングル サインオンを構成する方法について説明します。

この記事では、Amped と Microsoft Entra ID を統合する方法について説明します。 Amped と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Amplified へのアクセス権を持つユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Amped に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 増幅されたシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Amplifiedは、**SP開始SSOとIDP開始SSO**の両方をサポートします。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからアンプリファイドを追加する

Microsoft Entra ID への Amped の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Amped を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックス**に「増幅」**と入力します。
4. 結果パネルから **[増幅]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### 増幅用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Amped に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Ampsed の関連ユーザーとの間にリンク関係を確立する必要があります。

増幅に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **増幅 SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **増幅されたテスト ユーザーの作成** - 増幅された B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Amplified**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、値を入力します。 `amplified-saml-sp:azure_ad`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.amplified.ai/sso/sp/consume/<IdPTenantID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.amplified.ai/sso/auth/signin/<IdPTenantID>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには [、増幅されたサポート チーム](mailto:support@amplified.ai) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. 増幅されたアプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. 上記に加えて、増幅されたアプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 表示名 | ユーザー.ユーザープリンシパルネーム |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Amplified SSO を設定する

**増幅側**でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[増幅されたサポート チーム](mailto:support@amplified.ai)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### 増幅されたテスト ユーザーの作成

このセクションでは、Amped で B.Simon というユーザーを作成します。 [増幅されたサポート チーム](mailto:support@amplified.ai)と協力して、増幅されたプラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる増幅サインオン URL にリダイレクトされます。
- 増幅されたサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Amped に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [増幅] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Amped に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/amplitude-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Amplitude を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amplitude-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Amplitude 間のシングル サインオンを構成する方法について説明します。

この記事では、Amplitude と Microsoft Entra ID を統合する方法について説明します。 Amplitude を Microsoft Entra ID と統合すると、次のことが可能になります。

- Amplitude へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Amplitude に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Amplitude でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Amplitude は、**SP および IDP** による SSO をサポートします。
- Amplitude では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Amplitude の追加

Microsoft Entra ID への Amplitude の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Amplitude を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Amplitude」**と入力します。
4. 結果パネルから **[Amplitude]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Amplitude に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Amplitude に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Amplitude の関連ユーザーとの間にリンク関係を確立する必要があります。

Amplitude に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Amplitude SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Amplitude テスト ユーザーの作成** - Amplitude で B.Simon に対応するユーザーを作成し、ユーザーの Microsoft Entra 表示にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Amplitude]**&gt;**[シングル サインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ａ。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://amplitude.com/saml/sso/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://analytics.amplitude.com/saml/sso/<UNIQUE_ID>`

    注

    応答 URL は、実際の値ではありません。 応答 URL の値は、この記事の後半で取得します。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://analytics.amplitude.com/sso`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Amplitude のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Amplitude SSO の構成

1. 別の Web ブラウザーのウィンドウで、Amplitude 企業サイトに管理者としてサインインします。
2. 左側のナビゲーション バーから **プラン管理者** を選択します。

    [Image: [プラン管理者] が選択されている [Amplitude] メニューを示すスクリーンショット。]
3. **SSO 統合**から **Microsoft Entra Metadata** を選択します。

    [Image: Microsoft Entra メタデータ リンクが強調表示された [プラン管理者] ウィンドウを示すスクリーンショット。]
4. [ **シングル サインオンのセットアップ** ] セクションで、次の手順に従います。

    [Image: この手順で説明する値を含む [シングル サインオンのセットアップ] セクションを示すスクリーンショット。]

    ａ。 Azure portal からダウンロードした **メタデータ XML を** メモ帳で開き、 **Microsoft Entra Metadata** テキストボックスにコンテンツを貼り付けます。

    b。 **応答 URL (ACS)** の値をコピーし、[**基本的な SAML 構成**] の **[応答 URL**] ボックスに貼り付けます。

    c. **[保存] を選択する**

#### Amplitude のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Amplitude に作成します。 Amplitude では、Just-In-Time ユーザー プロビジョニングがサポートされ、既定で有効になっています。 このセクションにはアクション項目はありません。 Amplitude にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Amplitude サポート チーム](https://amplitude.zendesk.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Amplitude サインオン URL にリダイレクトされます。
- Amplitude のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Amplitude に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Amplitude] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Amplitude に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/anaplan-tutorial"} -->
## Microsoft Entra ID で Anaplan for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/anaplan-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Anaplan の間のシングル サインオンを構成する方法について説明します。

この記事では、Anaplan と Microsoft Entra ID を統合する方法について説明します。 Anaplan を Microsoft Entra ID と統合すると、次のことが可能になります。

- Anaplan へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Anaplan に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Anaplan でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Anaplan では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの Anaplan の追加

Microsoft Entra ID への Anaplan の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Anaplan を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Anaplan**」と入力します。
4. 結果パネルから **Anaplan** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Anaplan に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Anaplan に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Anaplan の関連ユーザーとの間にリンク関係を確立する必要があります。

Anaplan に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Anaplan SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Anaplan テスト ユーザーの作成** - Anaplan で B.Simon に対応するユーザーを作成し、それを Microsoft Entra の B.Simon とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Anaplan**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [SAML を使用 **した単一 Sign-On の設定** ] ページの **[SAML 署名証明書** ] セクションで、コピー アイコンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、Anaplan SSO 構成で使用するために保存します。

    [Image: [証明書のダウンロード] リンク。]

### Anaplan の SSO の構成

1. Anaplan の Web サイトに管理者としてログインします。
2. [管理] ページで、[ **セキュリティ &gt; シングル サインオン**] に移動します。
3. [ **新規**] を選択します。
4. **[メタデータ**] タブで次の手順を実行します。

    [Image: セキュリティ ページのスクリーンショット。]

    a. **接続名**を入力します。ID プロバイダー インターフェイスの接続名と一致する必要があります。

    b。 [ **XML ファイルから読み込む** ] を選択し、[ **メタデータ URL** ] ボックスにアプリのフェデレーション メタデータ URL を貼り付けます。

    c. **[保存] を**選択して接続を作成します。

    d. [ **有効]** トグルを設定して、接続を有効にします。
5. [ **構成** ] タブで、次の値をコピーして Azure portal に保存します。

    a. **サービス プロバイダーの URL**。 b。 **アサーション コンシューマー サービスの URL**。 c. **エンティティ ID**。

#### Microsoft Entra SSO 構成を完了する

1. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集します。]
2. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、上からコピーしたエンティティ ID を次の形式で貼り付けます。 `https://sdp.anaplan.com/<optional extension>`

    b。 [ **サインオン URL** ] テキスト ボックスに、上からコピーしたサービス プロバイダーの URL を次の形式で貼り付けます。 `https://us1a.app.anaplan.com/samlsp/<connection name>`

    c. **[応答 URL (Assertion Consumer Service URL)]** テキスト ボックスに、上からコピーした Assertion Consumer Service URL を次の形式で貼り付けます。`https://us1a.app.anaplan.com/samlsp/login/callback?connection=<connection name>`

#### Anaplan SSO 構成を完了する

1. [ **詳細設定** ] タブで次の手順を実行します。

    [Image: [詳細設定] ページのスクリーンショット。]

    a. ドロップダウンから **名前 ID 形式** を「メールアドレス」として選択し、残りの値は既定値のままにします。

    b。 **[保存] を選択します**。
2. [ **ワークスペース** ] タブで、ドロップダウンから ID プロバイダーを使用するワークスペースを指定し、[ **保存]** を選択します。

    [Image: [ワークスペース] ページのスクリーンショット。]

    注

    ワークスペースに使用できる接続は 1 つだけです。 ワークスペースで既に別の接続が構成されている場合、そのワークスペースを新しい接続に関連付けることはできません。 元の接続にアクセスして更新するには、その接続からワークスペースを削除したうえで、再度それを新しい接続に関連付けてください。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Anaplan のテスト ユーザーの作成

このセクションでは、Anaplan で Britta Simon というユーザーを作成します。 [Anaplan サポート チーム](mailto:support@anaplan.com)と協力して、Anaplan プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Anaplan のサインオン URL にリダイレクトされます。
- Anaplan のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Anaplan] タイルを選択すると、このオプションは Anaplan のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/anaqua-tutorial"} -->
## Microsoft Entra ID で ANAQUA for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/anaqua-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ANAQUA の間のシングル サインオンを構成する方法について説明します。

この記事では、ANAQUA と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と ANAQUA を統合すると、次のことが可能になります。

- ANAQUA にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで ANAQUA に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ANAQUA でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ANAQUA は、**SP および IDP** による SSO をサポートしています。
- ANAQUA では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから ANAQUA を追加する

Microsoft Entra ID への ANAQUA の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ANAQUA を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ANAQUA**」と入力します。
4. 結果パネルから **ANAQUA** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ANAQUA に対する Microsoft Entra SSO の構成とテスト

**B. Simon** というテスト ユーザーを使用して、ANAQUA に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ANAQUA の関連ユーザーとの間にリンク関係を確立する必要があります。

ANAQUA に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ANAQUA SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ANAQUA テストユーザーを作成する** - B.Simon の ANAQUA 対応ユーザーを作成し、それを Microsoft Entra 内のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ANAQUA** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.anaqua.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.anaqua.com/anaqua/Public/login.aspx`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.anaqua.com/anaqua/Public/login.aspx`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、ANAQUA クライアント サポート チームに問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択してメタデータ ファイルをダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **ANAQUA のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ANAQUA の SSO の構成

**ANAQUA** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を ANAQUA サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ANAQUA テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを ANAQUA に作成します。 ANAQUA では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ANAQUA にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ANAQUA サインオン URL にリダイレクトされます。
- ANAQUA のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ANAQUA に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで ANAQUA タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ANAQUA に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/andfrankly-tutorial"} -->
## Microsoft Entra ID で &frankly のシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/andfrankly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と &frankly の間のシングル サインオンを構成する方法について説明します。

この記事では、Microsoft Entra ID と &frankly を統合する方法について説明します。 Microsoft Entra ID と &frankly を統合すると、次のことができます。

- Microsoft Entra ID で &frankly へのアクセス権を持つユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して自動的に & frankly にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- &frankly でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- &frankly では、**SP起動SSO**と**IDP起動SSO**がサポートされています。

### ギャラリーから & frankly を追加する

Microsoft Entra ID への &frankly の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に &frankly を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「>frankly」と**入力します。
4. 結果パネルから **[&frankly** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO for &frankly の構成とテスト

**B.Simon** というテスト ユーザーを使用して、&frankly に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと関連ユーザーとの間にリンク関係を確立する必要があります (率直に言って)。

Microsoft Entra SSO with &frankly を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **&frankly SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **&frankly のテスト ユーザーの作成** - &frankly で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[&frankly]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://andfrankly.com/saml/simplesaml/www/module.php/saml/sp/metadata.php/<tenant id>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://andfrankly.com/saml/simplesaml/www/module.php/saml/sp/saml2-acs.php/<tenant id>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://andfrankly.com/saml/okta/?saml_sso=<tenant id>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、&frankly クライアント サポート チーム](mailto:help@andfrankly.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### アンドフランクリーSSOを設定する

シングル サインオンを &frankly で有効にするには:

1. &franklyにログインします。 **アカウント**&gt;**ユーザー管理**に移動します。
2. 認証メカニズムを既定から **エンタープライズ サインオン (SAML) に**変更します。
3. 前のセクションの手順 6 でダウンロードした **フェデレーション メタデータ XML** をアップロードします。
4. **[保存] を選択します**。

#### &franklyのテストユーザーを作成する

このセクションでは、&frankly で Britta Simon というユーザーを作成します。 [&frankly サポート チーム](mailto:help@andfrankly.com)と協力して、&frankly プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる & frankly [サインオン URL] にリダイレクトされます。
- [frankly サインオン URL] に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した &frankly に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [&frankly] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した & frankly に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/andromedascm-tutorial"} -->
## Microsoft Entra ID で Andromeda for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/andromedascm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Andromeda の間のシングル サインオンを構成する方法について説明します。

この記事では、Andromeda と Microsoft Entra ID を統合する方法について説明します。 Andromeda を Microsoft Entra ID と統合すると、次のことが可能になります。

- Andromeda へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Andromeda に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Andromeda でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Andromedaでは、**SPおよびIDPによるSSO**がサポートされています。
- Andromeda では、 **Just In Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Andromeda の追加

Microsoft Entra ID への Andromeda の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Andromeda を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Andromeda**」と入力します。
4. 結果パネルから **Andromeda** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Andromeda に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Andromeda に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Andromeda の関連ユーザーの間にリンク関係を確立する必要があります。

Andromeda に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Andromeda SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Andromeda テストユーザーの作成 - Britta Simon に対応する Andromeda 内のユーザーを作成し、Microsoft Entra のユーザーにリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Andromeda**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantURL>.ngcxpress.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantURL>.ngcxpress.com/SAMLConsumer.aspx`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantURL>.ngcxpress.com/SAMLLogon.aspx`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、Sign-On URL を使用して値を更新します。これについては、この記事の後半で説明します。
7. Andromeda アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 このアプリケーションに対して次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの 「 **ユーザー属性」** セクションから管理できます。 [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** ボタンを選択して [ **ユーザー属性] ダイアログを** 開きます。

    [Image: ユーザー属性を示すスクリーンショット。]

    注

    これらの設定中に名前空間の定義をクリアします。
8. [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、[**編集] アイコン**を使用して要求を編集するか、[**新しい要求の追加]** を使用して要求を追加し、上の図に示すように SAML トークン属性を構成し、次の手順を実行します。

    | 名前 | ソース属性 |
    | --- | --- |
    | ロール | アプリ固有のロール |
    | 型 | アプリの種類 |
    | company | カンパニーネーム |

    注

    Andromeda では、アプリケーションに対してユーザーのロールが割り当てられていることを想定しています。 ユーザーに適切なロールを割り当てることができるように、Microsoft Entra ID でこれらのロールを設定してください。 Microsoft Entra ID でロールを構成する方法については、 [こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)。

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [新しい要求を追加して保存する] オプションを含むユーザー要求を示すスクリーンショット。]

    [Image: この手順で説明した値を入力できるユーザー要求の管理を示すスクリーンショット。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. [ソース] を **[属性**] として選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. [ **OK] を選択する**

    g. **[保存] を選択します**。
9. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Andromeda のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Andromeda の SSO の構成

1. Andromeda 企業サイトに管理者としてサインオンします。
2. メニュー バーの上部にある [ **管理** ] を選択し、[ **管理]** に移動します。

    [Image: Andromeda 管理者。]
3. ツール バーの左側にある [ **インターフェイス]** セクションで、[ **SAML 構成**] を選択します。

    [Image: Andromeda SAML。]
4. [ **SAML 構成** ] セクション ページで、次の手順を実行します。

    [Image: Andromeda の構成。]

    a. 「**SAML を用いた SSO を有効にする** を選択します。」

    b。 [**Andromeda 情報**] セクションで、**SP ID の値を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子**] ボックスに貼り付けます。

    c. **[コンシューマー URL**] の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。

    d. **[ログオン URL**] の値をコピーし、[**基本的な SAML 構成**] セクションの **[サインオン URL**] ボックスに貼り付けます。

    e. [ **SAML ID プロバイダー] セクションで** 、IDP 名を入力します。

    f. **[シングル サインオン エンドポイント**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    g. Azure portal からダウンロードした **Base64 でエンコードされた証明書** をメモ帳で開き、[ **X 509 証明書** ] ボックスに貼り付けます。

    h. 以下の属性を対応する値にマップし、Microsoft Entra ID からの SSO ログインを容易にします。 ログインには **ユーザー ID** 属性が必要です。 プロビジョニングには、 **電子メール**、 **会社**、 **UserType**、 **ロール** が必要です。 このセクションでは、Azure portal 内で定義されている値に関連付ける属性マッピング (名前と値) を定義します。

    [Image: Andromeda の属性。]

    一. **[保存] を選択します**。

#### Andromeda のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Andromeda に作成します。 Andromeda では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Andromeda にユーザーがまだ存在していない場合は、認証後に新しく作成されます。 ユーザーを手動で作成する必要がある場合は、Andromeda クライアント サポート チームにお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Andromeda のサインオン URL にリダイレクトされます。
- Andromeda のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Andromeda に自動的にサインインします
- Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Andromeda] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Andromeda に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/animaker-tutorial"} -->
## Microsoft Entra ID で Animaker for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/animaker-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Animaker の間のシングル サインオンを構成する方法について説明します。

この記事では、Animaker と Microsoft Entra ID を統合する方法について説明します。 Animaker を Microsoft Entra ID と統合すると、次のことが可能になります。

- Animaker にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Animaker に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Animaker でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Animaker では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーからの Animaker の追加

Microsoft Entra ID への Animaker の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Animaker を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Animaker**」と入力します。
4. 結果のパネルから **[Animaker]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Animaker 向けに Microsoft Entra SSO を構成してテストする

**B. Simon** というテスト ユーザーを使用して、Animaker に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Animaker の関連ユーザー間にリンク関係を確立する必要があります。

Animaker に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO の構成**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Animaker SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Animakerのテストユーザーを設定 - Animaker**におけるB.Simonの相当ユーザーをMicrosoft Entra内のユーザー表現とリンクするため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

次の手順に従って、Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Animaker]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[SAML でシングル サインオンをセットアップします]** ページで、次のフィールドの値を入力します。

    a. **[識別子]** ボックスに、`https://app.animaker.com/login/samlsuccess/<Anyone_Home_Provided_Unique_Value>` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://app.animaker.com/login/samlsuccess/<Anyone_Home_Provided_Unique_Value>` のパターンを使用して URL を入力します

    Note

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Animaker サポート チーム](mailto:help@animaker.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** テキスト ボックスに、URL として「`https://app.animaker.com/login/samlsuccess/<Anyone_Home_Provided_Unique_Value>`」と入力します。
7. Animaker アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 Appraisd アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: このスクリーンショットは、[編集] アイコンが強調表示された状態の [ユーザー属性] ペインを示しています。]
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Animaker SSO の構成

**Animaker** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Animaker サポート チーム](mailto:help@animaker.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Animaker のテスト ユーザーの作成

このセクションでは、Animaker で Britta Simon というユーザーを作成します。 [Animaker サポート チーム](mailto:help@animaker.com)と連携して、Animaker プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Animaker のサインオン URL にリダイレクトされます。
- Animaker のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Animaker に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Animaker] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Animaker に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/answerhub-tutorial"} -->
## Microsoft Entra ID で AnswerHub for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/answerhub-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AnswerHub の間のシングル サインオンを構成する方法について説明します。

この記事では、AnswerHub と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID に AnswerHub を統合すると、次の利点が得られます。

- AnswerHub へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで AnswerHub に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AnswerHub でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- AnswerHub では、SP Initiated SSO がサポートされます。

### ギャラリーからの AnswerHub の追加

AnswerHub の Microsoft Entra ID への統合を構成するには、AnswerHub をギャラリーから管理対象 SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**AnswerHub**」と入力します。
4. 結果のパネルから **[AnswerHub]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AnswerHub に対する Microsoft Entra SSO の設定とテスト

**B.Simon** というテスト ユーザーを使用して、AnswerHub に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと AnswerHub の関連ユーザーとの間にリンク関係を確立する必要があります。

AnswerHub に対して Microsoft Entra の SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AnswerHub SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AnswerHub テストユーザーの作成** - AnswerHub で B.Simon に相当するユーザーを作成し、ユーザーの Microsoft Entra 表象に関連付けます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[AnswerHub]**&gt;**[シングル サインオン]** を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子 (エンティティ ID)]** ボックスに、次のパターンで URL を入力します。`https://<company>.answerhub.com`

    b。 **[サインオン URL]** ボックスに、次のパターンで URL を入力します。`https://<company>.answerhub.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 この値を取得するには、[AnswerHub サポート チーム](mailto:success@answerhub.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、実際の要件に従って、 **[証明書 (Base64)]** の横にある **[ダウンロード]** リンクを選択し、証明書を自分のコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[AnswerHub のセットアップ]** セクションで、実際の要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AnswerHub SSO を構成する

このセクションでは、AnswerHub のシングル サインオンを設定します。

**AnswerHub のシングル サインオンを構成するには:**

1. 別の Web ブラウザー ウィンドウで、AnswerHub 企業サイトに管理者としてサインインします。

    注

    AnswerHub の構成について不明な点がある場合は、[AnswerHub サポート チーム](mailto:success@answerhub.com.)にお問い合わせください。
2. **[Administration]** に移動します。
3. **[User and Groups](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーとグループ)** タブの左側のウィンドウで、 **[Social Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ソーシャル設定)** セクションの **[SAML Setup](SAML セットアップ)** を選択します。
4. **[IDP Config](IDP 構成)** タブで、以下の手順を実行します。

    [Image: [ユーザーとグループ] タブが選択されている AnswerHub ページを示すスクリーンショット。]

    a. **[IDP ログイン URL]** ボックスに、コピーした**ログイン URL** を貼り付けます。

    b。 **[IDP ログアウト URL]** ボックスに、コピーした**ログアウト URL** を貼り付けます。

    c. **[IDP Name Identifier Format](IDP 名前識別子形式)** ボックスに、Azure portal の **[ユーザー属性]** セクションで選択した**識別子**の値を入力します。

    d. **[Keys and Certificates](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/キーと証明書)** を選択します。
5. **[Keys and Certificates](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/キーと証明書)** セクションで、以下の手順を実行します。

    [Image: [Keys and Certificates](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/キーと証明書) セクション]

    a. ダウンロードした Base64 でエンコードされた証明書をメモ帳で開き、その内容をコピーしてから、**[IDP Public Key (x509 Format)](IDP 公開キー (x509 形式))** ボックスに貼り付けます。

    b。 **保存** を選択します。
6. **[IDP Config](IDP 構成)** タブで、もう一度 **[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存)** を選択します。

#### AnswerHub のテスト ユーザーの作成

Microsoft Entra ユーザーが AnswerHub にサインインできるようにするには、それらを AnswerHub に追加する必要があります。 AnswerHub では、このタスクを手動で実行します。

**ユーザー アカウントを設定するには:**

1. **AnswerHub** 企業サイトに管理者としてサインインします。
2. **[Administration]** に移動します。
3. [ **ユーザーとグループ** ] タブを選択します。
4. 左側のウィンドウの [ユーザーの **管理** ] セクションで、[ **ユーザーの作成またはインポート**] を選択し、[ **ユーザーとグループ**] を選択します。

    [Image: [ユーザーとグループ] タブが選択され、[ユーザーの作成またはインポート] リンクが強調表示されている AnswerHub ページを示すスクリーンショット。]
5. 追加したい有効な Microsoft Entra アカウントの**メール アドレス**、**ユーザー名**、**パスワード**を適切なボックスに入力してから、 **[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存)** を選択します。

注

AnswerHub から提供されている他のユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントを設定できます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる AnswerHub のサインオン URL にリダイレクトされます。
- AnswerHub のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [AnswerHub] タイルを選択すると、このオプションは AnswerHub のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/anyone-home-crm-tutorial"} -->
## Microsoft Entra ID でシングルサインオンを利用して Anyone Home CRM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/anyone-home-crm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Anyone Home CRM の間でシングル サインオンを構成する方法について説明します。

この記事では、Anyone Home CRM と Microsoft Entra ID を統合する方法について説明します。 Anyone Home CRM と Microsoft Entra ID を統合すると、次のことができます。

- Anyone Home CRM にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Anyone Home CRM に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Anyone Home CRM でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- すべての Home CRM では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーから Anyone Home CRM を追加する

Microsoft Entra ID への Anyone Home CRM の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Anyone Home CRM を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**をブラウズします。
3. **[ギャラリーから追加**] セクションで、検索ボックスに「**Anyone Home CRM**」と入力します。
4. 結果パネルから**Anyone Home CRM**を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Anyone Home CRM 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Anyone Home CRM に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Anyone Home CRM の関連ユーザーとの間にリンク関係を確立する必要があります。

Anyone Home CRM を使用して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Anyone Home CRM SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Anyone Home CRM のテスト用ユーザーの作成** - Anyone Home CRM で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現とリンクされた B.Simon に接続します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Anyone Home CRM**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.anyonehome.com/webroot/files/simplesamlphp/www/module.php/saml/sp/metadata.php/<Anyone_Home_Provided_Unique_Value>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.anyonehome.com/webroot/files/simplesamlphp/www/module.php/saml/sp/saml2-acs.php/<Anyone_Home_Provided_Unique_Value>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Anyone Home CRM クライアント サポート チーム](mailto:support@anyonehome.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Anyone Home CRM SSO を構成する

**Anyone Home CRM** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Anyone Home CRM サポート チーム](mailto:support@anyonehome.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Anyone Home CRM テスト ユーザーを作成する

このセクションでは、Anyone Home CRM で Britta Simon というユーザーを作成します。 [Anyone Home CRM サポート チーム](mailto:support@anyonehome.com)と協力して、Anyone Home CRM プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Anyone Home CRM に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Anyone Home CRM] タイルを選択すると、SSO を設定した Anyone Home CRM に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/apexportal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Apex Portal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/apexportal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Apex Portal 間のシングル サインオンを構成する方法について説明します。

この記事では、Apex Portal と Microsoft Entra ID を統合する方法について説明します。 Apex Portal を Microsoft Entra ID と統合すると、次のことが可能になります。

- Apex Portal にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Apex Portal に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Apex Portal でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Apex Portal では、 **IDP** Initiated SSO がサポートされます。
- Apex Portal では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Apex Portal を追加する

Microsoft Entra ID への Apex Portal の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Apex Portal を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**にアクセスします。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Apex Portal**」と入力します。
4. 結果パネルから **Apex Portal** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Apex Portal に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Apex Portal に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Apex Portal の関連ユーザーとの間にリンク関係を確立する必要があります。

Apex Portal で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Apex Portal の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Apex Portal でテストユーザーを作成する** - Apex Portal で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Apex Portal**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer name>.apexanalytix.com/saml/sso.aspx`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer name>.apexanalytix.com/saml/sso.aspx`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Apex Portal クライアント サポート チーム](mailto:support@apexanalytix.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Apex Portal アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Apex Portal アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名前 | User.givenname |
    | 姓 | ユーザーの名字 |
    | MAIL | ユーザーのメールアドレス |
    |  |  |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Apex Portal のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Apex Portal の SSO の構成

**Apex Portal** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Apex Portal サポート チーム](mailto:support@apexanalytix.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Apex Portal のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Apex Portal に作成します。 Apex Portal では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Apex Portal にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Apex Portal サポート チーム](mailto:support@apexanalytix.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Apex Portal に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Apex Portal] タイルを選択すると、SSO を設定した Apex Portal に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/appaegis-isolation-access-cloud-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Appaegis Isolation Access Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appaegis-isolation-access-cloud-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-20
- Summary: Microsoft Entra ID から Appaegis Isolation Access Cloud へのユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Appaegis Isolation Access Cloud と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを Appaegis Isolation Access Cloud に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされる機能

- Appaegis Isolation Access Cloud のユーザーを作成する
- アクセスが不要になったときに Appaegis Isolation Access Cloud のユーザーを削除する
- Microsoft Entra ID と Appaegis Isolation Access Cloud の間でユーザー属性の同期を維持する
- Appaegis Isolation Access Cloud への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appaegis-isolation-access-cloud-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- プロフェッショナル レベルのサブスクリプションを持つ Appaegis Cloud アカウント。
- 管理者アクセス許可が付与されている Appaegis Cloud ユーザー アカウント。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Appaegis Isolation Access Cloud の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように Appaegis Isolation Access Cloud を構成する

1. Appaegis Cloud で [SSO](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appaegis-isolation-access-cloud-tutorial) を有効にしました。
2. **[ID プロバイダーの詳細]** ページ (このページには ACS URL とエンティティ ID が一覧表示されます) で、SCIM URL と SCIM トークンが見つかります。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Appaegis Isolation Access Cloud を追加する

Appaegis Isolation Access Cloud を Microsoft Entra アプリケーション ギャラリーから追加して、Appaegis Isolation Access Cloud に対してプロビジョニングの管理を開始します。 SSO のために Appaegis Isolation Access Cloud を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Appaegis Isolation Access Cloud に対して自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて TestApp 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Appaegis Isolation Access Cloud に対して自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。

    [Image: エンタープライズアプリケーションブレード]
3. アプリケーションの一覧で [ **Appaegis Isolation Access Cloud**] を選択します。

    [Image: アプリケーションの一覧の Appaegis Isolation Access Cloud のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Appaegis Isolation Access Cloud テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Appaegis Isolation Access Cloud に接続できることを確認します。 接続に失敗した場合は、Appaegis Isolation Access Cloud アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. [属性マッピング] セクションで、Microsoft Entra ID から Appaegis Isolation Access Cloud に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために Appaegis Isolation Access Cloud のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Appaegis Isolation Access Cloud API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から Contoso に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Contoso のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | members | 関連項目 |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/appaegis-isolation-access-cloud-tutorial"} -->
## Appaegis Isolation Access Cloud を Microsoft Entra ID でシングル サインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appaegis-isolation-access-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Appaegis Isolation Access Cloud の間でシングル サインオンを構成する方法について説明します。

この記事では、Appaegis Isolation Access Cloud と Microsoft Entra ID を統合する方法について説明します。 Appaegis Isolation Access Cloud を Microsoft Entra ID と統合すると、次のことができます。

- Appaegis Isolation Access Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Appaegis Isolation Access Cloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Appaegis Isolation Access Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Appaegis Isolation Access Cloud では、 **SP Initiated SSO と IDP** Initiated SSO がサポートされます。
- Appaegis Isolation Access Cloud では、 **Just In Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Appaegis Isolation Access Cloud の追加

Microsoft Entra ID への Appaegis Isolation Access Cloud の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Appaegis Isolation Access Cloud を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Appaegis Isolation Access Cloud**」と入力します。
4. 結果パネルから **Appaegis Isolation Access Cloud** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Appaegis Isolation Access Cloud 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Appaegis Isolation Access Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Appaegis Isolation Access Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Appaegis Isolation Access Cloud に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Appaegis Isolation Access Cloud SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Appaegis Isolation Access Cloud のテストユーザーを作成し、Microsoft Entra の B.Simon と対応付けます。** - Appaegis Isolation Access Cloud 内で B.Simon の対応するユーザーを持ち、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Appaegis Isolation Access Cloud**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.appaegis.net`

    b。 [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<RelayState>`

    注

    これらの値は実際の値ではありません。 これらの値を、実際のサインオン URL およびリレー状態で更新してください。 これらの値を取得するには [、Appaegis Isolation Access Cloud クライアント サポート チーム](mailto:support@appaegis.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Appaegis Isolation Access Cloud アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Appaegis Isolation Access Cloud アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザー.ユーザープリンシパルネーム |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Appaegis Isolation Access Cloud のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Appaegis Isolation Access Cloud の SSO の構成

1. Appaegis Isolation Access Cloud 企業サイトに管理者としてログインします。
2. **[設定**&gt;**カスタマイズ**] に移動し、テキスト ボックスに**テナント ドメイン URL を**入力し、[**+IdP**] ボタンを選択します。

    [Image: [アカウントのカスタマイズ] を示すスクリーンショット。]
3. [ **ID プロバイダーの詳細]** ページで、次の手順を実行します。

    [Image: ID プロバイダーの詳細を示すスクリーンショット。]

    1. **ID プロバイダー**のドロップダウンから **Microsoft Entra ID** を選択します。
    2. **ACS URL** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。
    3. **エンティティ ID の値を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスにこの値を貼り付けます。
    4. ダウンロードした **フェデレーション メタデータ XML** をメモ帳で開き、 **SAML ファイルのアップロードにファイルをアップロード**します。
    5. **[状態**] チェックボックスを有効にし、[**保存]** を選択します。

#### Appaegis Isolation Access Cloud のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Appaegis Isolation Access Cloud に作成します。 Appaegis Isolation Access Cloud では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Appaegis Isolation Access Cloud にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Appaegis Isolation Access Cloud のサインオン URL にリダイレクトされます。
- Appaegis Isolation Access Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Appaegis Isolation Access Cloud に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Appaegis Isolation Access Cloud] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Appaegis Isolation Access Cloud に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/appblade-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AppBlade を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appblade-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AppBlade の間のシングル サインオンを構成する方法について説明します。

この記事では、AppBlade と Microsoft Entra ID を統合する方法について説明します。 AppBlade を Microsoft Entra ID と統合すると、次の利点があります。

- AppBlade にアクセスできるユーザーを Microsoft Entra ID で管理できます。
- ユーザーが Microsoft Entra アカウントで AppBlade に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AppBlade でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- AppBlade では、**SP** によって開始される SSO がサポートされます
- AppBlade では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの AppBlade の追加

Microsoft Entra IDへの AppBlade の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に AppBlade を追加する必要があります。

**ギャラリーから AppBlade を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションに「**AppBlade」**と入力し、結果パネルで **AppBlade** を選択し、[**追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の AppBlade]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、AppBlade で Microsoft Entra のシングル サインオンを構成およびテストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと AppBlade の関連ユーザー間にリンク関係を確立する必要があります。

AppBlade に対する Microsoft Entra シングル サインオンを構成およびテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **AppBlade のシングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **AppBlade テスト ユーザーの作成** - Britta Simon に対応するユーザーを AppBlade 内に作成し、Microsoft Entra のユーザー表現にリンクします。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

AppBlade で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AppBlade** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [AppBlade のドメインと URL] のシングル サインオン情報]

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.appblade.com/saml/<tenantid>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[AppBlade クライアント サポート チーム](mailto:support@appblade.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[AppBlade のセットアップ]** セクションで、要件のとおりに適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### AppBlade のシングル サインオンの構成

**AppBlade** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [AppBlade サポート チーム](mailto:support@appblade.com)に送信する必要があります。 また、**[SSO Issuer URL] \(SSO 発行者の URL)** を `https://appblade.com/saml` として構成することも依頼してください。 この設定は、シングル サインオンが動作するために必要です。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### AppBlade のテスト ユーザーの作成

このセクションの目的は、AppBlade で Britta Simon というユーザーを作成することです。 AppBlade では、Just-In-Time プロビジョニングがサポートされています。この設定は、既定で有効になっています。 **ドメイン名が AppBlade でユーザー プロビジョニング用に構成されていることを確認してください。 その後でのみ、ジャストインタイムのユーザー プロビジョニングが機能します。**

ユーザーがアカウントに対して AppBlade によって構成されたドメインで終わるメール アドレスを持っている場合、ユーザーは自動的に、指定したアクセス許可レベルを持つメンバーとしてアカウントに参加します。これは、"Basic" (アプリケーションのみをインストールできる基本ユーザー)、"Team Member" (新しいアプリバージョンをアップロードしてプロジェクトを管理できるユーザー) のいずれかです。 または "管理者" (アカウントに対する完全な管理者特権)。 通常、Basic を選択し、その後、Admin ログオン経由で手動によりユーザーのアクセス許可レベルを上げます (AppBlade では、電子メール ベースの管理者ログインを前もって構成するか、ログイン後、お客様の代わりにユーザーのアクセス許可レベルを上げる必要があります)。

このセクションにはアクション項目はありません。 AppBlade にアクセスしようとすると、ユーザーがまだ存在しない場合は新しいユーザーが作成されます。

注

ユーザーを手動で作成する必要がある場合は、[AppBlade サポート チーム](mailto:support@appblade.com)に問い合わせる必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [AppBlade] タイルを選択すると、SSO を設定した AppBlade に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/appdynamics-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AppDynamics を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appdynamics-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AppDynamics の間のシングル サインオンを構成する方法について説明します。

この記事では、AppDynamics と Microsoft Entra ID を統合する方法について説明します。 AppDynamics を Microsoft Entra ID と統合すると、次のことが可能になります。

- AppDynamics へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して AppDynamics に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AppDynamics でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- AppDynamics では、**SP** Initiated SSO がサポートされます。
- AppDynamics では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの AppDynamics の追加

Microsoft Entra ID への AppDynamics の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に AppDynamics を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**AppDynamics**」と入力します。
4. 結果のパネルから **[AppDynamics]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AppDynamics に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、AppDynamics に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと AppDynamics の関連ユーザーの間にリンク関係を確立する必要があります。

AppDynamics に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AppDynamics SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AppDynamics のテストユーザーを作成します** - B.Simon の対応ユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[AppDynamics]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.saas.appdynamics.com/controller`

    b。 **[応答 URL (Assertion Consumer Service URL)]** テキスト ボックスに、次のパターンを使用して URL を入力します。`https://<companyname>.saas.appdynamics.com/controller/saml-auth?accountName=<companyname>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.saas.appdynamics.com/?accountName=<companyname>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[AppDynamics クライアント サポート チーム](https://www.appdynamics.com/support/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[AppDynamics のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AppDynamics SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として AppDynamics 企業サイトにログインします。
2. 上部のツール バーで、[ **設定]** を選択し、[管理] を選択 **します**。

    [Image: 管理]
3. [ **認証プロバイダー** ] タブを選択します。

    [Image: 認証プロバイダー]
4. [**認証プロバイダー**] セクションで、以下の手順を実行します。

    [Image: SAML 構成]

    a. [**認証プロバイダー**] として [**SAML**] を選択します。

    b。 **[ログイン URL]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    c. **[ログアウト URL]** テキストボックスに **[ログアウト URL]** の値を貼り付けます。

    d. base-64 でエンコードされた証明書をメモ帳で開き、内容をクリップボードにコピーし、[**証明書**] ボックスに貼り付けます。

    e. **保存** を選択します。

#### AppDynamics のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを AppDynamics に作成します。 AppDynamics では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 AppDynamics にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる AppDynamics のサインオン URL にリダイレクトされます。
- AppDynamics のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [AppDynamics] タイルを選択すると、このオプションは AppDynamics のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/appian-tutorial"} -->
## Microsoft Entra ID で Appian for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appian-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Appian の間のシングル サインオンを構成する方法について説明します。

この記事では、Appian と Microsoft Entra ID を統合する方法について説明します。 Appian を Microsoft Entra ID と統合すると、次のことが可能になります。

- Appian へのアクセス権を持つユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Appian に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Appian は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Appian でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Appian では、**SPおよびIDPによるSSO**がサポートされています。
- Appian では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Appian の追加

Microsoft Entra ID への Appian の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Appian を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に進みます。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Appian**」と入力します。
4. 結果パネルから **Appian** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Appian に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Appian に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Appian の関連ユーザーとの間にリンク関係を確立する必要があります。

Appian に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Appian SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Appian のテスト ユーザーの作成** - Appian で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Appian**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.appiancloud.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.appiancloud.com/suite/saml/AssertionConsumer`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.appiancloud.com/suite`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Appian クライアント サポート チーム](mailto:support@appian.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Appian のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]
9. Appian アプリケーションでは、SSO を機能させるためにトークン暗号化を有効にする必要があります。 トークン暗号化をアクティブにするには、**Entra ID**&gt;**Enterprise アプリ**を参照&gt;アプリケーション &gt;**トークン暗号化**を選択します。 詳細については、 [Microsoft Entra SAML トークン暗号化の構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption)に関する記事を参照してください。

    [Image: トークン暗号化のアクティブ化を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Appian の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Appian Web サイトに管理者としてサインインします。
2. **管理コンソール**に移動し、[**SAML を有効にする]** を選択します。
3. [ **SAML ID プロバイダーの追加] を選択します**。
4. IdP の **説明** を入力します。
5. **サービス プロバイダー名**を入力します。これは、サービス プロバイダーと ID プロバイダーの両方の一意の名前にする必要があります。
6. IdP メタデータを **ID プロバイダー メタデータにアップロードします**。
7. IdP に一致する適切な **署名ハッシュ アルゴリズム** を選択します。
8. ダイアログの右上にある [ **この構成のテスト** ] を選択して、構成が有効かどうかを確認します。
9. [正常にサインインした後に **完了]** を選択して、構成ダイアログを閉じます。
10. 新しい IdP のサインイン ページを既定にする場合は、それに応じて **既定の Sign-In ページ** を設定します。
11. ダイアログが閉じられたら、[ **マイ アクセスの確認** ] を選択して、Appian に引き続きサインインできることを確認します。 今回は、現在のユーザーでサインインする必要があります。
12. サインインできることを確認したら、[ **変更の保存]** を選択します。

#### Appian のテスト ユーザーの作成

このセクションでは、Appian で Britta Simon というユーザーを作成します。 Appian では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Appian にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Appian のサインオン URL にリダイレクトされます。
- Appian のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Appian に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Appian] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Appian に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/appinux-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Appinux を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appinux-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Appinux の間のシングル サインオンを構成する方法について説明します。

この記事では、Appinux と Microsoft Entra ID を統合する方法について説明します。 Appinux を Microsoft Entra ID と統合すると、次のことが可能になります。

- Appinux へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Appinux に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Appinux でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Appinux では、**SP** によって開始される SSO がサポートされます
- Appinux では、**Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーから Appinux を追加する

Microsoft Entra ID への Appinux の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Appinux を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Appinux**」と入力します。
4. 結果のパネルから **Appinux** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Appinux に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Appinux に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Appinux の関連ユーザーとの間にリンク関係を確立する必要があります。

Appinux に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Appinux の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Appinux テストユーザーの作成 - Appinux** で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Appinux**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Appinux_SUBDOMAIN>.appinux.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Appinux_SUBDOMAIN>.appinux.com/simplesaml/module.php/saml/sp/metadata.php/default-sp`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 この値を取得するには、[Appinux クライアント サポート チーム](https://support.appinux.com/)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Appinux アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 **[編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: 画像]
7. その他に、Appinux アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | **名前** | **名前空間** | **ソース属性** |
    | --- | --- | --- |
    | `givenname` | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` | `user.givenname` |
    | `surname` | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` | `user.surname` |
    | `emailaddress` | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` | `user.mail` |
    | `name` | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` | `user.userprincipalname` |
    | `UserType` | `http://bcv.appinux.com/claims` | `Provide the value as per your organization` |
    | `Tag` | `http://appinux.com/Tag` | `Provide the value as per your organization` |
    | `Role` | `http://schemas.microsoft.com/ws/2008/06/identity/claims/role` | `user.assignedroles` |
    | `email` | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/email` | `user.mail` |
    | `wanshort` | `http://appinux.com/windowsaccountname2` | `extractmailprefix([userprincipalname])` |
    | `nameidentifier` | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` | `user.employeeid` |

    注

    Appinux では、アプリケーションに対してユーザーのロールが割り当てられていることを想定しています。 ユーザーに適切なロールを割り当てることができるように、Microsoft Entra ID でこれらのロールを設定してください。 Microsoft Entra ID でロールを構成する方法については、 [こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Appinux のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Appinux の SSO の構成

**Appinux** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Appinux サポート チーム](https://support.appinux.com/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Appinux テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Appinux に作成します。 Appinux では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Appinux にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Appinux サポート チーム](https://support.appinux.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Appinux のサインオン URL にリダイレクトされます。
- Appinux のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Appinux] タイルを選択すると、このオプションは Appinux のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/applied-mental-health-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Applied Mental Health を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/applied-mental-health-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Applied Mental Health の間でシングル サインオンを構成する方法について説明します。

この記事では、Applied Mental Health と Microsoft Entra ID を統合する方法について説明します。 Applied Mental Health と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID によって Applied Mental Health へのアクセス権を持つ者を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Applied Mental Health に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 応用メンタルヘルスサービスのシングルサインオン (SSO) が有効化されたサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Applied Mental Health では、**SP と IDP** Initiated SSO がサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから応用メンタルヘルスを追加する

Microsoft Entra ID への Applied Mental Health の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Applied Mental Health を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;から**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに「**Applied Mental Health」**と入力します。
4. 結果パネルから **[Applied Mental Health]** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Applied Mental Health の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Applied Mental Health に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Applied Mental Health の関連ユーザーとの間にリンク関係を確立する必要があります。

Applied Mental Health で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Applied Mental Health SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Applied Mental Health のテストユーザーを作成** - Microsoft Entra ユーザーの表象にリンクした、Applied Mental Health 内で B.Simon に対応するユーザーを作成するため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[Enterprise アプリ]**&gt;**[Applied Mental Health]**&gt;**[シングル サイン オン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.appliedmentalhealth.com.au/saml2/aad/login`
7. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Applied Mental Health のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Applied Mental Health SSO の構成

**Applied Mental Health** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Applied Mental Health サポート チーム](mailto:support@appliedmentalhealth.com.au)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### アプライドメンタルヘルスのテストユーザーを作成する

このセクションでは、Applied Mental Health で Britta Simon というユーザーを作成します。 [Applied Mental Health サポート チーム](mailto:support@appliedmentalhealth.com.au)と協力して、Applied Mental Health プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Applied Mental Health のサインオン URL にリダイレクトされます。
- Applied Mental Health のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Applied Mental Health に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Applied Mental Health] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Applied Mental Health に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/applitools-eyes-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Applitools Eyes を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/applitools-eyes-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Applitools Eyes の間でシングル サインオンを構成する方法について説明します。

この記事では、Applitools Eyes と Microsoft Entra ID を統合する方法について説明します。 Applitools Eyes と Microsoft Entra ID を統合すると、次のことができます。

- Applitools Eyes にアクセスできる権限を持つユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Applitools Eyes に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Applitools Eyes でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Applitools Eyes では、 **SP** によって開始される SSO のみがサポートされます。
- Applitools Eyes では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Applitools Eyes を追加する

Microsoft Entra ID への Applitools Eyes の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Applitools Eyes を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Applitools Eyes**」と入力します。
4. 結果パネルから **Applitools Eyes** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Applitools Eyes の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Applitools Eyes に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Applitools Eyes の関連ユーザーとの間にリンク関係を確立する必要があります。

Applitools Eyes で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Applitools Eyes の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Applitools Eyes のテスト ユーザーの作成** - Applitools Eyes で B.Simon に対応するユーザーを作成し、Microsoft Entra ID の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Applitools Eyes**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `<SUBDOMAIN>.applitools.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.applitools.com`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.applitools.com`

    d. [ **ログアウト URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.applitools.com/api/auth/logout`

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL、およびログアウト URL で更新してください。 これらの値を取得するには [、Applitools Eyes サポート チーム](mailto:support@applitools.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Applitools Eyes のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Applitools Eyes の SSO を構成する

**Applitools Eyes** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、Microsoft Entra 管理センターからコピーした適切な URL を [Applitools Eyes サポート チーム](mailto:support@applitools.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Applitools Eyes のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Applitools Eyes に作成します。 Applitools Eyes では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Applitools Eyes にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Applitools Eyes のサインオン URL にリダイレクトします。
- Applitools Eyes のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Applitools Eyes] タイルを選択すると、このオプションは Applitools Eyes のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/appneta-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に AppNeta Performance Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appneta-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AppNeta Performance Manager の間でシングル サインオンを構成する方法について説明します。

この記事では、AppNeta Performance Manager と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と AppNeta Performance Manager を統合すると、次のことができます。

- AppNeta Performance Manager にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで自動的に AppNeta Performance Manager にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AppNeta Performance Manager でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AppNeta Performance Manager では、 **SP** によって開始される SSO がサポートされます。
- AppNeta Performance Manager では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの AppNeta Performance Manager の追加

Microsoft Entra ID への AppNeta Performance Manager の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に AppNeta Performance Manager を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「AppNeta Performance Manager**」と入力します。
4. 結果パネルから **AppNeta Performance Manager** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AppNeta Performance Manager の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、AppNeta Performance Manager に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと AppNeta Performance Manager の関連ユーザーとの間にリンク関係を確立する必要があります。

AppNeta Performance Manager に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AppNeta Performance Manager の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AppNeta Performance Manager のテスト ユーザーを作成する** - AppNeta Performance Manager で B.Simon のテスト ユーザーを作成し、それを Microsoft Entra における B.Simon の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AppNeta Performance Manager**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.pm.appneta.com`

    b。 [応答 URL (Assertion Consumer Service URL)] フィールドに「`https://access.broadcom.com/default/saml/v1/sp/acs`」と入力します。

    注

    上記のサインオン URL の値は一例です。 実際のサインオン URL でこの値を更新してください。 この値を取得するには [、AppNeta Performance Manager カスタマー サポート チーム](mailto:support@appneta.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. AppNeta Performance Manager アプリケーションでは、特定の形式の SAML アサーションが求められます。そのため、SAML トークン属性の構成に、カスタム属性マッピングを追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: SAML トークンの既定の属性を示すスクリーンショット。]
7. その他に、AppNeta Performance Manager アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | Email | ユーザー.ユーザープリンシパルネーム |
    | グループ | user.assignedroles |
8. "グループ" SAML アサーションを正しく渡すには、アプリ ロールを構成し、AppNeta Performance Manager 内で設定されているロール マッピングと一致するように値を設定する必要があります。 [ **Entra ID**&gt;**App registrations**] で、[ **AppNeta Performance Manager**] を選択します。

    [Image: 下部にある Appneta Performance Manager でのアプリの登録を示すスクリーンショット。]
9. 左側のウィンドウで [ **アプリ** ロール] を選択します。 次の画面が表示されます。

    [Image: 下部に Appneta Performance Manager があるアプリ ロールを示すスクリーンショット。]
10. **アプリ ロールの作成**を選択します。
11. [ **アプリ ロールの作成** ] 画面で、次の手順に従います。

    1. [ **表示名** ] フィールドに、ロールの名前を入力します。
    2. [ **許可されるメンバーの種類** ] フィールドで、[ **ユーザー/グループ**] を選択します。
    3. [ **値** ] フィールドに、AppNeta Performance Manager ロール マッピングで設定されたセキュリティ グループの値を入力します。
    4. [ **説明** ] フィールドに、ロールの説明を入力します。
    5. [ **適用]** を選択します。

    [Image: 説明に従ってフィールドが入力された [アプリ ロールの作成] ダイアログのスクリーンショット。]
12. ロールを作成したら、それらをユーザーまたはグループにマップする必要があります。 **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Appneta Performance Manager**&gt;**ユーザーおよびグループ**に移動します。
13. ユーザーまたはグループを選択し、目的のアプリ ロール (前の手順で作成したもの) を割り当てます。
14. アプリロールをマップしたら、**Entra ID**&gt;**Enterprise apps**&gt;**Appneta Performance Manager**&gt;**シングルサインオン**に移動します。
15. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AppNeta Performance Manager の SSO の構成

**AppNeta Performance Manager** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** を [AppNeta Performance Manager サポート チーム](mailto:support@appneta.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### AppNeta Performance Manager のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを AppNeta Performance Manager に作成します。 AppNeta Performance Manager では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は、既定で有効になっています。 このセクションにはアクション項目はありません。 AppNeta Performance Manager にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [AppNeta Performance Manager サポート チーム](mailto:support@appneta.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Azure portal で、[ **このアプリケーションをテスト**する] を選択します。 このオプションは、ログイン フローを開始できる AppNeta Performance Manager のサインオン URL にリダイレクトされます。
- AppNeta Performance Manager のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリ ポータルで [AppNeta Performance Manager] タイルを選択すると、このオプションは AppNeta Performance Manager のサインオン URL にリダイレクトされます。 マイ アプリ ポータルの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/appraisd-tutorial"} -->
## Microsoft Entra ID で Appraisd for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appraisd-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Appraisd の間のシングル サインオンを構成する方法について説明します。

この記事では、Appraisd と Microsoft Entra ID を統合する方法について説明します。 Appraisd を Microsoft Entra ID と統合すると、次のことが可能になります。

- Appraisd にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Appraisd に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Appraisd サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Appraisd では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーから Appraisd を追加する

Microsoft Entra ID への Appraisd の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Appraisd を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Appraisd**」と入力します。
4. 結果パネルから **Appraisd** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Appraisd に対する Microsoft Entra SSO の構成とテスト

**B. Simon** というテスト ユーザーを使用して、Appraisd に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Appraisd の関連ユーザーとの間にリンク関係を確立する必要があります。

Appraisd に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Appraisd SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Appraisd のテスト ユーザーの作成** - Appraisd で B.Simon に対応するユーザーを作成し、Microsoft Entra におけるユーザーの代表にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

次の手順に従って、Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Appraisd** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を保存し、次の手順を実行する必要があります。

    a. [ **追加の URL の設定] を選択します**。

    b。 [ **リレー状態** ] テキスト ボックスに、次の値を入力します。 `<TENANTCODE>`

    c. **SP** 開始モードでアプリケーションを構成する場合は、[**サインオン URL**] テキスト ボックスに、次のパターンを使用して URL を入力します。`https://app.appraisd.com/saml/<TENANTCODE>`

    Note

    実際のサインオン URL とリレー状態の値は、Appraisd SSO 構成ページで取得します。これについては、この記事の後半で説明します。
6. Appraisd アプリケーションは、特定の形式の SAML アサーションを使用するため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **nameidentifier** は **user.userprincipalname** にマップされています。 Appraisd アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 編集アイコンが強調表示されている [ユーザー属性] ペインを示すスクリーンショット。]
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Appraisd のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Appraisd SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として Appraisd 企業サイトにサインインします。
2. ページの右上にある **[設定]** アイコンを選択し、[ **構成]** に移動します。

    [Image: [構成] リンクが強調表示されているスクリーンショット。]
3. メニューの左側で、[ **SAML シングル サインオン**] を選択します。

    [Image: SAML シングル サインオン オプションが強調表示された [構成] オプションを示すスクリーンショット。]
4. **SAML 2.0 Single Sign-On 構成**ページで、次の手順を実行します。

    [Image: SAML 2.0 シングル Sign-On 構成ページを示すスクリーンショット。このページでは、既定のリレー状態とサービス開始ログイン U R L を編集できます。]

    a. 既定の**リレー状態**の値をコピーし、Azure portal の **[基本的な SAML 構成**] の **[リレー状態**] ボックスに貼り付けます。

    b。 **サービス開始ログイン URL** の値をコピーし、Azure portal の **[基本的な SAML 構成**] の **[サインオン URL**] ボックスに貼り付けます。
5. [ユーザーの識別] で同じページ **を下に**スクロールし、次の手順を実行します。

    [Image: この手順の値を入力できるユーザーの識別を示すスクリーンショット。]

    a. **[Identity Provider Single Sign-On URL**] ボックスに、コピーした**ログイン URL** の値を貼り付け、[**保存]** を選択します。

    b。 [ **Identity Provider Issuer URL]\(ID プロバイダー発行者 URL** \) ボックスに、コピーした **Microsoft Entra Identifier** の値を貼り付けて **、[保存]** を選択します。

    c. メモ帳で、ダウンロードした base-64 でエンコードされた証明書を開き、その内容をコピーして、[ **X.509 証明書** ] ボックスに貼り付けて **、[保存**] を選択します。

#### Appraisd テスト ユーザーを作成する

Microsoft Entra ユーザーが Appraisd にサインインできるようにするには、ユーザーを Appraisd にプロビジョニングする必要があります。 Appraisd では、プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. セキュリティ管理者として Appraisd にサインインします。
2. ページの右上にある **[設定]** アイコンを選択し、[ **管理センター**] に移動します。

    [Image: [設定] オプションを示すスクリーンショット。[管理センター] を選択できます。]
3. ページの上部にあるツール バーで、[ **ユーザー**] を選択し、[ **新しいユーザーの追加]** に移動します。

    [Image: スクリーンショットは、[ユーザー] と [新しいユーザーの追加] が強調表示された [Appraisd] ページを示しています。]
4. [ **新しいユーザーの追加] ページで** 、次の手順を実行します。

    [Image: [新しいユーザーの追加] ページを示すスクリーンショット。]

    a. [ **名** ] テキスト ボックスに、 **Britta** などのユーザーの名を入力します。

    b。 [ **姓** ] テキスト ボックスに、simon のようなユーザーの姓を入力 **します**。

    c. [ **電子メール** ] テキスト ボックスに、ユーザーの電子メール ( `B. Simon@contoso.com`など) を入力します。

    d. [ **ユーザーの追加] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Appraisd のサインオン URL にリダイレクトされます。
- Appraisd のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Appraisd に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Appraisd] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Appraisd に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/appremo-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AppRemo を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appremo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AppRemo の間のシングル サインオンを構成する方法について説明します。

この記事では、AppRemo と Microsoft Entra ID を統合する方法について説明します。 AppRemo を Microsoft Entra ID と統合すると、次のことが可能になります。

- AppRemo にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで AppRemo に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AppRemo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AppRemo では、**SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの AppRemo の追加

Microsoft Entra ID への AppRemo の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に AppRemo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**AppRemo**」と入力します。
4. 結果のパネルから **[AppRemo]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AppRemo に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、AppRemo に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと AppRemo の関連ユーザーとの間にリンク関係を確立する必要があります。

AppRemo に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AppRemo の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AppRemo テスト ユーザーの作成** - Microsoft Entra のユーザー表現にリンクされた、AppRemo での B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[AppRemo]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. **[サインオン URL]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://<ENVIRONMENT>.exexwf.com/wf` |
    | `https://<ENVIRONMENT>.appremo.jp/wf` |
    | `https://<ENVIRONMENT>.fr.appremo.jp/wf` |
    | `https://<ENVIRONMENT>.mlt.appremo.jp/wf` |
    |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://<ENVIRONMENT>.exexwf.com/auth/saml` |
    | `https://<ENVIRONMENT>.appremo.jp/auth/saml` |
    | `https://<ENVIRONMENT>.fr.appremo.jp/auth/saml` |
    | `https://<ENVIRONMENT>.mlt.appremo.jp/auth/saml` |
    |  |

    注

    これらの値は実際の値ではありません。 これらの値を、実際のサインオン URL および応答 URL で更新してください。 これらの値を取得するには、[AppRemo クライアント サポート チーム](mailto:AR-support@system-exe.co.jp)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[AppRemo のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AppRemo SSO の構成

**AppRemo** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション設定からコピーした適切な URL を [AppRemo サポート チーム](mailto:AR-support@system-exe.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### AppRemo テストユーザーの作成

このセクションでは、AppRemo で Britta Simon というユーザーを作成します。 [AppRemo サポートチーム](mailto:AR-support@system-exe.co.jp)と連携し、AppRemo プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる AppRemo のサインオン URL にリダイレクトされます。
- AppRemo のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [AppRemo] タイルを選択すると、このオプションは AppRemo のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/appsec-flow-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Conviso Platform SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appsec-flow-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Conviso Platform SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、Conviso Platform SSO と Microsoft Entra ID を統合する方法について説明します。 Conviso Platform SSO と Microsoft Entra ID を統合すると、次のことができます。

- Conviso Platform SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Conviso プラットフォーム SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Conviso Platform SSO は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Conviso プラットフォーム SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Conviso Platform SSO では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからConviso プラットフォーム SSOを追加する

Microsoft Entra ID への Conviso プラットフォーム SSO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Conviso プラットフォーム SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリ**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックス**に「Conviso Platform SSO**」と入力します。
4. 結果パネルから **Conviso Platform SSO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Conviso Platform SSO に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Conviso Platform SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Conviso プラットフォーム SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Conviso プラットフォーム SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Conviso Platform SSO SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Conviso プラットフォーム SSOのテストユーザーの作成** - Conviso プラットフォーム SSO で B.Simon に対応するユーザーを作成し、Microsoft Entra での同一ユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Conviso Platform SSO]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Conviso Platform SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Conviso プラットフォーム SSO の構成

**Conviso Platform SSO** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Conviso Platform SSO サポート チーム](mailto:sre@convisoappsec.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Conviso プラットフォーム SSO テストユーザーの作成

このセクションでは、Conviso プラットフォーム SSO で Britta Simon というユーザーを作成します。 [Conviso Platform SSO サポート チーム](mailto:sre@convisoappsec.com)と協力して、Conviso Platform SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Conviso Platform SSO に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [Conviso Platform SSO] タイルを選択すると、SSO を設定した Conviso Platform SSO に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/appsecengineer-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AppSecEngineer を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/appsecengineer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AppSecEngineer の間でシングル サインオンを構成する方法について説明します。

この記事では、AppSecEngineer と Microsoft Entra ID を統合する方法について説明します。 AppSecEngineer と Microsoft Entra ID を統合すると、次のことができます。

- AppSecEngineer に対するアクセス権を持つユーザーを Microsoft Entra ID で管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して AppSecEngineer に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/free/?WT.mc_id=A261C142F)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AppSecEngineer でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AppSecEngineer では、 **SP** によって開始される SSO のみがサポートされます。

### ギャラリーから AppSecEngineer を追加する

Microsoft Entra ID への AppSecEngineer の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に AppSecEngineer を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「AppSecEngineer**」と入力します。
4. 結果パネルから **AppSecEngineer** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AppSecEngineer の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、AppSecEngineer に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと AppSecEngineer の関連ユーザーとの間にリンク関係を確立する必要があります。

AppSecEngineer に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AppSecEngineer の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AppSecEngineer テストユーザーの作成** - Microsoft Entra ID で表現されるユーザーにリンクされた AppSecEngineer 内の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AppSecEngineer**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:amazon:cognito:sp:us-east-2_<AppSecEngineer_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://auth.learning.appsecengineer.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.learning.appsecengineer.com/authorize?response_type=code&identity_provider=<PROVIDER>&client_id=<ID>&redirect_uri=https://learning.appsecengineer.com/callback`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 [AppSecEngineer サポート チーム](mailto:help@appsecengineer.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AppSecEngineer SSO の構成

**AppSecEngineer** 側でシングル サインオンを構成するには、**AppSecEngineer サポート チーム**に[アプリのフェデレーション メタデータ URL を](mailto:help@appsecengineer.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### AppSecEngineer テスト ユーザーの作成

このセクションでは、AppSecEngineer で B.Simon というユーザーを作成します。 [AppSecEngineer サポート チーム](mailto:help@appsecengineer.com)と協力して、AppSecEngineer プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる AppSecEngineer のサインオン URL にリダイレクトされます。
- AppSecEngineer のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [AppSecEngineer] タイルを選択すると、このオプションは AppSecEngineer のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/apptio-tutorial"} -->
## Microsoft Entra ID で Apptio for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/apptio-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Apptio 間のシングル サインオンを構成する方法について説明します。

この記事では、Apptio と Microsoft Entra ID を統合する方法について説明します。 Apptio を Microsoft Entra ID と統合すると、次のことが可能になります。

- Apptio にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Apptio に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Apptio でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Apptio では、**IDP** Initiated SSO がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Apptio の追加

Microsoft Entra ID への Apptio の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Apptio を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Apptio**」と入力します。
4. 結果のパネルから **[Apptio]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Apptio に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Apptio に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Apptio の関連ユーザーとの間にリンク関係を確立する必要があります。

Apptio に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Apptio SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Apptioでテストユーザーを作成 - B.Simonに対応するユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Apptio]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **識別子** ] テキスト ボックスに、URL を入力します。 `urn:federation:apptio`
6. ロール要求は事前構成されているため、構成する必要はありませんが、この [記事](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)を使用して Microsoft Entra ID で作成する必要があります。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Set up Apptio](Apptio の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Apptio の SSO の構成

**Apptio** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Apptio サポート チーム](https://www.apptio.com/resources/customer-support/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Apptio テスト ユーザーの作成

このセクションでは、Apptio で B.Simon というユーザーを作成します。 [Apptio サポート チーム](https://www.apptio.com/resources/customer-support/)と協力して、Apptio プラットフォームでユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Apptio に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [Apptio] タイルを選択すると、SSO を設定した Apptio に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/aqua-platform-tutorial"} -->
## Microsoft Entra ID で Aqua Platform for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aqua-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Aqua Platform の間のシングル サインオンを構成する方法について説明します。

この記事では、Aqua Platform と Microsoft Entra ID を統合する方法について説明します。 Aqua Platform を使用すると、お客様はクラウド ネイティブ アプリケーションを安全に構築、スケーリング、自動化できます。 Microsoft Entra ID と Aqua を統合すると、次のことができます。

- Aqua Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Aqua Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Aqua Platform 向けの Microsoft Entra のシングル サインオンを構成およびテストします。 Aqua Platform は、**SP** Initiated シングル サインオンのみサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Aqua Platform と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Aqua Platform のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Aqua Platform アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Aqua Platform を追加する

Microsoft Entra アプリケーション ギャラリーから Aqua Platform を追加して、Aqua Platform でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Aqua Platform**&gt;**シングルサインオン**に進みます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のいずれかの値を入力します。

    | 環境 | URL |
    | --- | --- |
    | us-east-1（米国東部リージョン） | `urn:amazon:cognito:sp:us-east-1_voZ9dTvpW` |
    | アジア-1 | `urn:amazon:cognito:sp:ap-southeast-1_7AUN22FiF` |
    | EU-1 | `urn:amazon:cognito:sp:eu-central-1_RAierVJ6g` |
    | ASIA-2 | `urn:amazon:cognito:sp:ap-northeast-2_n4Oo1PAlV` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | us-east-1（米国東部リージョン） | `https://cloudsploit.auth.us-east-1.amazoncognito.com/saml2/idpresponse` |
    | アジア-1 | `https://auth-sg-prod.auth.ap-southeast-1.amazoncognito.com/saml2/idpresponse` |
    | EU-1 | `https://auth-eu-prod.auth.eu-central-1.amazoncognito.com/saml2/idpresponse` |
    | ASIA-2 | `https://auth-kr-prod.auth.ap-northeast-2.amazoncognito.com/saml2/idpresponse` |

    c. [ **サインオン URL** ] ボックスに、URL を入力します。 `https://cloud.aquasec.com/sso`
6. Aqua Platform アプリケーションでは、特定の形式の SAML アサーションが想定されるため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: トークン属性の画像を示すスクリーンショット。]
7. その他に、Aqua Platform アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を下に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | cspmgroups | user.department |
    | groups | ユーザー.グループ |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Aqua Platform のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Aqua Platform SSO を構成する

**Aqua Platform** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Aqua Platform サポート チーム](mailto:support@aquasec.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Aqua Platform テスト ユーザーを作成する

このセクションでは、Aqua Platform で Britta Simon というユーザーを作成します。 [Aqua Platform サポート チーム](mailto:support@aquasec.com)と協力して、Aqua Platform プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Aqua Platform のサインオン URL にリダイレクトされます。
- Aqua Platform のサインオン URL に直接移動して、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Aqua Platform] タイルを選択すると、このオプションは Aqua Platform のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/aravo-tutorial"} -->
## Microsoft Entra ID で Aravo for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aravo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Aravo の間でシングル サインオンを構成する方法について説明します。

この記事では、Aravo と Microsoft Entra ID を統合する方法について説明します。 Aravo と Microsoft Entra ID の統合には、次の利点があります。

- Aravo にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Aravo (Single Sign-On) に自動的にサインインできるようにすることができます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Aravo でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Aravo は、**IDP** イニシエート SSO をサポートします。

### ギャラリーからの Aravo の追加

Microsoft Entra ID への Aravo の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Aravo を追加する必要があります。

**ギャラリーから Aravo を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**を開きます。
3. **[ギャラリーから追加**] セクションに「**Aravo**」と入力し、結果パネルで **Aravo** を選択し、[**追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Aravo]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Aravo で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Aravo 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Aravo で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Aravo シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Aravo のテストユーザーを作成** - Aravo で Britta Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現とリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Aravo で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Aravo** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    [Image: Aravo のドメインと URL のシングル サインオン情報]

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.aravo.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.aravo.com/aems/login.do`

    手記

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、Aravo クライアント サポート チーム  にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Aravo のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    ある。 ログイン URL

    b。 Microsoft Entra アイデンティファイヤー

    c. ログアウト URL

#### Aravo Single Sign-On の構成

**Aravo** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Aravo サポート チーム](https://www.aravo.com/about-us/contact/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Aravo テスト ユーザーの作成

このセクションでは、Aravo で Britta Simon というユーザーを作成します。 Aravo サポート チーム  と連携して、Aravo プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Aravo] タイルを選択すると、SSO を設定した Aravo に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/arborxr-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ArborXR を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/arborxr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ArborXR の間でシングル サインオンを構成する方法について説明します。

この記事では、ArborXR と Microsoft Entra ID を統合する方法について説明します。 ArborXR と Microsoft Entra ID を統合すると、次のことができます。

- ArborXR にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して ArborXR に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ArborXR でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ArborXR では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから ArborXR を追加する

Microsoft Entra ID への ArborXR の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ArborXR を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ArborXR**」と入力します。
4. 結果パネルから **ArborXR** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ArborXR の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、ArborXR に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ArborXR の関連ユーザーとの間にリンク関係を確立する必要があります。

ArborXR に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ArborXR SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ArborXR テスト ユーザーの作成** - ArborXR で B.Simon に対応するユーザーを作成し、Microsoft Entra ID でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ArborXR**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.xrdm.app/auth/realms/<INSTANCE>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.xrdm.app/auth/realms/<INSTANCE>/broker/SAML2/endpoint`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.xrdm.app/auth/realms/<INSTANCE>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、ArborXR サポート チーム](mailto:support@arborxr.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ArborXR SSO の構成

1. ArborXR 企業サイトに管理者としてログインします。
2. **[設定]**&gt;**[シングル サインオン**]に移動し&gt;**SAML** を選択します。
3. **[Hosted IdP Metadata URL**] ボックスに、Microsoft Entra 管理センターからコピーした**アプリのフェデレーション メタデータ URL を**貼り付けます。

    [Image: 構成の設定を示すスクリーンショット。]
4. [ **変更の適用]** を選択します。

#### ArborXR テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、管理者として ArborXR Web サイトにサインインします。
2. **[設定]**&gt;**[ユーザー**] に移動し、[**ユーザーの追加]** を選択します。

    [Image: スクリーンショットは、アプリケーションでユーザーを作成する方法を示しています。]
3. [ **ユーザーの追加** ] セクションで、次の手順を実行します。

    [Image: ページで新しいユーザーを作成する方法を示すスクリーンショット。]

    1. ドロップダウンから **[ロール** ] を選択します。
    2. [ **電子メールで招待** ] ボックスに有効なメール アドレスを入力します。
    3. [ **招待**] を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる ArborXR のサインオン URL にリダイレクトします。
- ArborXR のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ArborXR] タイルを選択すると、このオプションは ArborXR のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/arc-facilities-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ARC Facilities を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/arc-facilities-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ARC Facilities 間のシングル サインオンを構成する方法について説明します。

この記事では、ARC Facilities と Microsoft Entra ID を統合する方法について説明します。 ARC Facilities を Microsoft Entra ID と統合すると、次のことが可能になります。

- ARC Facilities にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで ARC Facilities に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ARC Facilities でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ARC Facilities では、**IDP** Initiated SSO がサポートされます
- ARC Facilities では、**Just In Time** ユーザー プロビジョニングがサポートされます

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ARC Facilities の追加

ARC Facilities と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に ARC Facilities をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。このとき、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上のアクセス許可があるユーザーを使用します。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**ARC Facilities**」と入力します。
4. 結果のパネルから **[ARC Facilities]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ARC Facilities の Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、ARC Facilities の Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、ARC Facilities での関連ユーザーとの間にリンク関係を確立する必要があります。

ARC Facilities の Microsoft Entra SSO を構成してテストするには、次の構成要素を完了する必要があります。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ARC Facilities の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ARC Facilities のテスト ユーザーを作成する** - ARC ClientVPN で B.Simon に対応するユーザーを作成し、Microsoft Entra でのユーザー表現にリンクします。
3. **SSO をテストする** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。このとき、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上のアクセス許可があるユーザーを使用します。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ARC Facilities]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure で事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. ARC Facilities アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。 **[編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: このスクリーンショットは、[編集] アイコンがコールアウトされた状態の [ユーザー属性] ダイアログ ボックスを示しています。]
7. その他に、ARC Facilities アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 **[グループ要求 (プレビュー)]** ダイアログの **[ユーザー属性と要求]** セクションで、次の手順を実行します。

    a. **[要求で返されるグループ]** の横にある**ペン**を選択します。

    [Image: このスクリーンショットは、[要求で返されるグループ] の横のペンがコールアウトされた状態の [ユーザー属性と要求] を示しています。]

    b。 **[グループ要求]** ダイアログで、ラジオ ボタンのリストから **[すべてのグループ]** を選びます。

    c. **[グループ ID]** の **[ソース属性]** を選択します。

    d. **保存** を選択します。

    Note

    ARC Facilities では、アプリケーションに対してユーザーのロールが割り当てられていることを想定しています。 ユーザーに適切なロールを割り当てることができるように、Microsoft Entra ID でこれらのロールを設定してください。 Microsoft Entra ID でロールを構成する方法については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)をご覧ください。
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
9. **[ARC Facilities のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ARC Facilities の SSO の構成

**ARC Facilities** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を、[ARC Facilities サポート チーム](mailto:support@arcfacilities.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### ARC Facilities のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを ARC Facilities に作成します。 ARC Facilities では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ARC Facilities にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ARC Facilities に自動的にサインインします
- Microsoft マイ アプリを使用することができます。 マイ アプリで [ARC Facilities] タイルを選択すると、SSO を設定した ARC Facilities に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/arc-tutorial"} -->
## Microsoft Entra ID を使ったシングルサインオン（SSO）のために、Arc Publishing を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/arc-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Arc Publishing - SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、Arc Publishing - SSO と Microsoft Entra ID を統合する方法について説明します。 Arc Publishing - SSO と Microsoft Entra ID を統合すると、次のことができます。

- Arc Publishing - SSO にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Arc Publishing - SSO に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Arc Publishing - SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Arc Publishing - SSO では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Arc Publishing - SSO では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Arc Publishing - SSO の追加

Microsoft Entra ID への Arc Publishing - SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Arc Publishing - SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Arc Publishing - SSO**」と入力します。
4. 結果のパネルから **[Arc Publishing - SSO]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Arc Publishing - SSO に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Arc Publishing - SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Arc Publishing - SSO での関連ユーザーとの間にリンク関係を確立する必要があります。

Arc Publishing - SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Arc Publishing - SSO の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Arc Publishing - SSO テストユーザーの作成** - Arc Publishing - SSO で B.Simon に対応するユーザーを作成し、Microsoft Entra ユーザーと関連付けます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Arc Publishing - SSO]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.okta.com/saml2/service-provider/<Unique ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://arcpublishing-<Customer>.okta.com/sso/saml2/<Unique ID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://arcpublishing-<Customer>.okta.com/sso/saml2/<Unique ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 この値を取得するには、[Arc Publishing - SSO クライアント サポート チーム](mailto:inf@washpost.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Arc Publishing - SSO アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Arc Publishing - SSO アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
    | groups | user.assignedroles |

    注

    ここで、**group** 属性は **user.assignedroles** とマップされます。 これらは、アプリケーションでグループ名をマップし直すために Microsoft Entra ID で作成されたカスタム ロールです。 Microsoft Entra ID でカスタム ロールを作成する方法の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Arc Publishing - SSO のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Arc Publishing - SSO の SSO の構成

**Arc Publishing - SSO** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Arc Publishing - SSO サポート チーム](mailto:inf@washpost.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Arc Publishing - SSO のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Arc Publishing - SSO に作成します。 Arc Publishing - SSO では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Arc Publishing - SSO にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Arc Publishing - SSO サポート チーム](mailto:inf@washpost.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Arc Publishing - SSO サインオン URL にリダイレクトされます。
- Arc Publishing - SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Arc Publishing - SSO に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Arc Publishing - SSO] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Arc Publishing - SSO に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/arcgis-tutorial"} -->
## Microsoft Entra ID で ArcGIS Online for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/arcgis-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と ArcGIS Online の間のシングル サインオンを構成する方法について説明します。

この記事では、ArcGIS Online と Microsoft Entra ID を統合する方法について説明します。 ArcGIS Online と Microsoft Entra ID を統合すると、次のことができます:

- ArcGIS Online にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ArcGIS Online に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理する。

ArcGIS Online は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- ArcGIS Online でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ArcGIS Online では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの ArcGIS Online の追加

ArcGIS Online の Microsoft Entra ID への統合を構成するには、ArcGIS Online をギャラリーから管理対象 SaaS アプリの一覧に追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「ArcGIS Online**」と入力します。
4. 結果パネルから **ArcGIS Online** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ArcGIS Online に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ArcGIS Online に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ArcGIS Online の関連ユーザーとの間にリンク関係を確立する必要があります。

ArcGIS Online で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ArcGIS Online の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ArcGIS Online のテスト ユーザーの作成** - ArcGIS Online で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ArcGIS Online]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `<COMPANY_NAME>.maps.arcgis.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.maps.arcgis.com/sharing/rest/oauth2/saml/signin`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.maps.arcgis.com`

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには、 [ArcGIS Online クライアント サポート チーム](https://support.esri.com/en/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **ArcGIS Online** 内での構成を自動化するには、[拡張機能のインストール] を選択して **マイ アプリ Secure Sign-in ブラウザー拡張機能**を**インストールする必要があります**。
8. ブラウザーに拡張機能を追加した後、[ **セットアップ] を選択すると、ArcGIS Online** アプリケーションが表示されます。 そこから、管理者資格情報を提供して ArcGIS Online にサインインします。 ブラウザー拡張機能によってアプリケーションが自動的に構成され、「 **ArcGIS Online シングル サインオンの構成**」セクションの手順が自動化されます。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ArcGIS Online の SSO の構成

1. 別の Web ブラウザー ウィンドウで、ArcGIS Online 企業サイトに管理者としてサインインします。
2. **組織**&gt;**Settings** に移動します。
3. 左側のメニューで、[ **セキュリティ** ] を選択し、[ログイン] タブで **[新しい SAML ログイン** ] を選択します。

    [Image: [セキュリティ] を示すスクリーンショット。]
4. [ **SAML ログインの設定** ] ウィンドウで、構成を **1 つの ID プロバイダー** として選択し、[ **次へ**] を選択します。

    [Image: [エンタープライズ ログイン] を示すスクリーンショット。]
5. [ **プロパティの指定** ] タブで、次の手順を実行します。

    [Image: [Set Identity Provider](ID プロバイダーの設定) を示すスクリーンショット。]

    a. [ **名前** ] ボックスに、組織の名前を入力します。

    b。 **エンタープライズ ID プロバイダーのメタデータ ソースの**場合は、[**ファイル**] を選択します。

    c. [ **ファイルの選択] を選択** して、以前にダウンロードした **フェデレーション メタデータ XML** ファイルをアップロードします。

    d. **[保存] を選択します**。

#### ArcGIS Online のテスト ユーザーの作成

Microsoft Entra ユーザーが ArcGIS Online にログインできるようにするには、そのユーザーを ArcGIS Online にプロビジョニングする必要があります。 ArcGIS Online の場合、プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. **ArcGIS** テナントにログインします。
2. **組織**&gt;**メンバー**に移動し、[**メンバーの招待**] を選択します。
3. [ **招待を送信せずにメンバーを追加する** ] メソッドを選択し、[ **次へ**] を選択します。

    [Image: [メンバーの自動追加] を示すスクリーンショット。]
4. [ **メンバーのコンパイル] ボックスの一覧で**、[ **新しいメンバー** ] を選択し、[ **次へ**] を選択します。
5. 次のページの必須フィールドに入力し、[ **次へ**] を選択します。

    [Image: [追加と確認] を示すスクリーンショット。]
6. 次のページで、追加するメンバーを選択し、[ **次へ**] を選択します。
7. 次のページで必要なメンバー プロパティを設定し、[ **次へ**] を選択します。
8. [ **確認と完了** ] タブで、[ **メンバーの追加]** を選択します。

    [Image: [メンバーの追加] を示すスクリーンショット。]

    注

    Microsoft Entra アカウント所有者は、電子メールを受信し、リンクに従ってアカウントがアクティブになる前にアカウントを確認します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる ArcGIS Online のサインオン URL にリダイレクトされます。
- ArcGIS Online のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [ArcGIS Online] タイルを選択すると、このオプションは ArcGIS Online のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/arcgisenterprise-tutorial"} -->
## Microsoft Entra ID で ArcGIS Enterprise をシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/arcgisenterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と ArcGIS Enterprise の間のシングル サインオンを構成する方法について説明します。

この記事では、ArcGIS Enterprise と Microsoft Entra ID を統合する方法について説明します。 ArcGIS Enterprise と Microsoft Entra ID を統合すると、次のことが可能になります。

- ArcGIS Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ArcGIS Enterprise に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

ArcGIS Enterprise は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- シングル サインオン (SSO) が有効な ArcGIS Enterprise サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ArcGIS Enterprise では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- ArcGIS Enterprise では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの ArcGIS Enterprise の追加

Microsoft Entra ID への ArcGIS Enterprise の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ArcGIS Enterprise を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ArcGIS Enterprise**」と入力します。
4. 結果パネルから **[ArcGIS Enterprise]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ArcGIS Enterprise 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ArcGIS Enterprise に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ArcGIS Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

ArcGIS Enterprise に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO の構成**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ArcGIS Enterprise の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ArcGIS Enterprise のテスト ユーザーの作成** - Microsoft Entra で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

次の手順に従って、Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ArcGIS Enterprise]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、**IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. **[識別子]** ボックスに、`<EXTERNAL_DNS_NAME>.portal` の形式で値を入力します。

    b。 **[応答 URL]** ボックスに、`https://<EXTERNAL_DNS_NAME>/portal/sharing/rest/oauth2/saml/signin` のパターンを使用して URL を入力します

    c. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<EXTERNAL_DNS_NAME>/portal/sharing/rest/oauth2/saml/signin` という形式で URL を入力します。

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[ArcGIS Enterprise クライアント サポート チーム](mailto:support@esri.com)に問い合わせてください。 Id **プロバイダーの設定セクション**から識別子の値を取得します。これについては、この記事の後半で説明します。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ArcGIS Enterprise の SSO の構成

1. 別の Web ブラウザーのウィンドウで、ArcGIS Enterprise の企業サイトに管理者としてサインインします。
2. **[組織]**&gt;**[設定の編集]** を選択します。

    [Image: ArcGIS Enterprise の [組織] タブが表示されているスクリーンショット。[設定の編集] が強調表示されています。]
3. **[セキュリティ]** タブを選びます。

    [Image: [セキュリティ] タブが選択されている画面のスクリーンショット。]
4. **[Enterprise Logins via SAML](SAML 経由でのエンタープライズ ログイン)** セクションまで下へスクロールして**[SET ENTERPRISE LOGIN](エンタープライズ ログインの設定)** を選択します。

    [Image: [Set Enterprise Login](エンタープライズ ログインの設定) を選択できる [Enterprise Logins via SAML](SAML 経由でのエンタープライズ ログイン) 画面のスクリーンショット。]
5. **[Set Identity Provider](ID プロバイダーの設定)** セクションで、次の手順に従います。

    a. **[名前]** テキスト ボックスに、**Microsoft Entra ID Test** のような名前を指定します。

    b。 **[URL]** テキス トボックスに、前にコピーした**アプリのフェデレーション メタデータ URL**の値を貼り付けます。

    c. [**詳細設定の表示**] を選択し、**エンティティ ID** の値をコピーし、Azure portal の **ArcGIS Enterprise の [ドメインと URL]** セクションの **[識別子**] ボックスに貼り付けます。

    [Image: エンティティ ID を取得して ID プロバイダーを更新する画面のスクリーンショット。]

    d. [ **ID プロバイダーの更新] を選択します**。

#### ArcGIS Enterprise テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを ArcGIS Enterprise に作成します。 ArcGIS Enterprise では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ArcGIS Enterprise にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[ArcGIS Enterprise のサポート チーム](mailto:support@esri.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる ArcGIS Enterprise サインオン URL にリダイレクトされます。
- ArcGIS Enterprise のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ArcGIS Enterprise に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで ArcGIS Enterprise タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ArcGIS Enterprise に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/archie-tutorial"} -->
## Microsoft Entra ID で Archie for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/archie-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Archie の間のシングル サインオンを構成する方法について説明します。

この記事では、Archie と Microsoft Entra ID を統合する方法について説明します。 Archie を Microsoft Entra ID と統合すると、次のことが可能になります。

- Archie にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Archie に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Archie でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Archie では、**SP および IDP** によるSSOがサポートされます。
- Archie では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Archie の追加

Archie と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Archie をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション** にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Archie**」と入力します。
4. 結果パネルから **Archie** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Archie の Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、Archie に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Archie での関連ユーザーとの間にリンク関係を確立する必要があります。

Archie の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Archie SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Archie テスト ユーザーの作成** - Archie で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Archie]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、[**基本的な SAML 構成]** セクションで次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://archieapp.co`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://archieapp.co/saml/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://archieapp.co/sign-in/<CustomerName>/`

    注

    この値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには、 [Archie クライアント サポート チーム](mailto:dev@archieapp.co) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Archie アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Archie アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザーのメールアドレス |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Archie のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Archie の SSO の構成

**Archie** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Archie サポート チーム](mailto:dev@archieapp.co)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Archie のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Archie に作成します。 Archie では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Archie にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Archie のサインオン URL にリダイレクトされます。
- Archie のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Archie に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Archie] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Archie に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ardoq-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Ardoq を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ardoq-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-20
- Summary: Microsoft Entra ID から Ardoq に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Ardoq と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Ardoq](https://www.ardoq.com) に対するユーザーのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Ardoq でユーザーを作成する。
- アクセスが不要になったら、Ardoq のユーザーを削除します。
- Microsoft Entra ID と Ardoq の間でユーザー属性の同期を維持する。
- Ardoq に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ardoq-tutorial) (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Ardoq の管理者アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Ardoq の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Ardoq を構成する

- プロビジョニングは、Ardoq の機能切り替えによって制御されます。 SSO を構成する場合、または既に構成している場合、Ardoq は Microsoft Entra ID が使用中であることを自動的に認識し、プロビジョニング機能が自動的に有効になります。
- SSO と共に Microsoft Entra ID のプロビジョニング機能を使用する予定がない場合は、Ardoq カスタマー サポートにお問い合わせください。カスタマー サポートがプロビジョニングのサポートを手動で有効にします。

先に進む前に、Microsoft Entra ID と Ardoq の間でセキュリティで保護された通信を構成するために、"テナントの URL" と "シークレット トークン" を取得する必要があります。

1. Ardoq 管理コンソールにログインします。
2. 左側のメニューでプロファイル ロゴを選択し、 **組織の設定-&gt;Manage Organization-&gt;Manage SCIM トークン**に移動します。
3. **新規作成**を選択します。
4. **トークン**をコピーして保存します。この値は、Ardoq アプリケーションの [プロビジョニング] タブの [**シークレット トークン**] フィールドに入力されます。
5. *テナント URL を*作成するには、次のテンプレートを使用します。プレースホルダー テキストを`https://<YOUR-SUBDOMAIN>.ardoq.com/api/scim/v2`置き換えて`<YOUR-SUBDOMAIN>`します。この値は、Ardoq アプリケーションの [プロビジョニング] タブの [**テナント URL**] フィールドに入力されます。

    注

    `<YOUR-SUBDOMAIN>` は、組織が Ardoq にアクセスするために選択したサブドメインです。 これは、Ardoq アプリにアクセスするときに使用する URL セグメントと同じです。 たとえば、組織が `https://acme.ardoq.com` で Ardoq にアクセスする場合、`acme` と入力します。 米国にいて、`https://piedpiper.us.ardoq.com` で Ardoq にアクセスする場合、`piedpiper.us` と入力します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Ardoq を追加する

Microsoft Entra アプリケーション ギャラリーから Ardoq を追加して、Ardoq へのプロビジョニングの管理を開始します。 SSO のために Ardoq を既に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5:Ardoq への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Ardoq の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Ardoq]** を選択します。

    [Image: アプリケーション リストの Ardoq リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Ardoq テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Ardoq に接続できることを確認します。 接続に失敗した場合は、Ardoq アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Ardoq に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Ardoq のユーザー アカウントの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Ardoq API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Ardoqによって要求される |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | roles[primary eq "True"].value | 糸 |  | ✓ |
12. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ardoq-tutorial"} -->
## Microsoft Entra ID で Ardoq for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ardoq-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Ardoq の間でシングル サインオンを構成する方法について説明します。

この記事では、Ardoq と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Ardoq を統合すると、次のことができます。

- Ardoq にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Ardoq に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Ardoq でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Ardoq では、**SP** によって開始される SSO のみがサポートされます。 **IDP** によって開始される SSO と同様の機能を実現するには、「[IDP によって開始される SAML サインインのサポート](https://help.ardoq.com/en/articles/109003-supporting-idp-initiated-saml-login)」のガイドを参照してください。
- Ardoq では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Ardoq の追加

Microsoft Entra ID への Ardoq の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Ardoq を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Ardoq**」と入力します。
4. 結果のパネルから Ardoq  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Ardoq の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Ardoq に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Ardoq の関連ユーザーとの間にリンク関係を確立する必要があります。

Ardoq に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Ardoq SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Ardoq のテスト ユーザーの作成** - Ardoq で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Ardoq]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    a. **識別子** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://<CustomerName>.us.ardoq.com/saml/v2` |
    | `https://<CustomerName>.ardoq.com/saml/v2` |

    b。 [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<CustomerName>.ardoq.com/saml/v2`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://<CustomerName>.ardoq.com/saml/v2` |
    | `https://<CustomerName>.us.ardoq.com/saml/v2` |

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値 [取得するには、Ardoq クライアント サポート チーム](mailto:support@ardoq.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. Ardoq アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. 上記に加えて、Ardoq アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | displayName | user.displayname |
    | 割り当てられた役割 | user.assignedroles |
    | メール | User.mail |

手記

Ardoq は、アプリケーションに割り当てられているユーザーのロールを想定しています。 ユーザーに適切なロールを割り当てることができるように、これらのロールは必ず Microsoft Entra ID で設定してください。 ロールは、"admin"、"writer"、"reader"、または "contributor" の値で設定する必要があります。

Microsoft Entra ID [内でロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)を構成する方法について学びます。

1. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML  検索し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
2. [**Ardoq** のセットアップ] セクションで、要件に基づいて 1 つ以上の適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Ardoq SSO の構成

**Ardoq** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Ardoq サポート チーム](mailto:support@ardoq.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Ardoq テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Ardoq に作成します。 Ardoq では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Ardoq に存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはサインイン フローを開始できる Ardoq のサインオン URL にリダイレクトされます。
- Ardoq のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Ardoq に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Ardoq] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Ardoq に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/arena-eu-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用にアリーナ EU を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/arena-eu-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Arena EU の間のシングル サインオンを構成する方法について説明します。

この記事では、アリーナ EU と Microsoft Entra ID を統合する方法について説明します。 Arena EU を Microsoft Entra ID と統合すると、次のことが可能になります。

- Arena EU へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Arena EU に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Arena EU でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Arena EU では、**SP** および **IDP** initiated SSO をサポートします。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Arena EU の追加

Microsoft Entra ID への Arena EU の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Arena EU を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Arena EU**」と入力します。
4. 結果パネルから **[Arena EU]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Arena EU に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、Arena EU に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Arena EU の関連ユーザーとの間にリンク関係を確立する必要があります。

Arena EU に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Arena EU SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Arena EU のテスト ユーザーの作成** - Arena EU で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Arena EU]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.europe.arenaplm.com/`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Set up Arena EU] (Arena EU の設定)** セクションで、要件に基づいて該当する URL をコピーします。

    [Image: 適切な構成 URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Arena EU SSO の構成

**Arena EU** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした該当する URL を [Arena EU サポート チーム](mailto:arena-support@ptc.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Arena EU のテスト ユーザーの作成

このセクションでは、Arena EU で Britta Simon というユーザーを作成します。 [Arena EU サポート チーム](mailto:arena-support@ptc.com)と連携して Arena EU プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できるアリーナ EU のサインオン URL にリダイレクトされます。
- Arena EU のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定したアリーナ EU に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [アリーナ EU] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したアリーナ EU に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/arena-tutorial"} -->
## Microsoft Entra ID でアリーナ for シングル サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/arena-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID とアリーナの間でシングル サインオンを構成する方法について説明します。

この記事では、アリーナと Microsoft Entra ID を統合する方法について説明します。 Arena と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID でアリーナへのアクセス権を管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用してアリーナに自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- アリーナでのシングル サインオン (SSO) が有効なサブスクリプション。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra ID でアプリケーションを追加または管理することもできます。 詳細については、Azure 組み込みロール に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Arena では、**SP** および **IDP** 開始の SSO がサポートされます。

### ギャラリーからアリーナを追加する

Microsoft Entra ID へのアリーナの統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧にアリーナを追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Arena**」と入力します。
4. 結果パネルから [アリーナ ] を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### アリーナの Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、アリーナに対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーとアリーナの関連ユーザーとの間にリンク関係を確立する必要があります。

アリーナで Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **アリーナ SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Arena のテスト ユーザーの作成** - Arena で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Arena**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: スクリーンショットは、基本的な S A M L 構成を編集する方法を示しています。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、URL: `https://app.bom.com` を入力します。
7. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: スクリーンショットには、証明書のダウンロードリンクが表示されます。]
8. **[アリーナ** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、適切なURLをコピーするための設定方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### アリーナ SSO の構成

アリーナ **側** シングル サインオンを構成するには、ダウンロードした **証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL をアリーナ サポート チーム 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### アリーナのテスト ユーザーの作成

このセクションでは、アリーナで Britta Simon というユーザーを作成します。 [アリーナ サポート チームの](mailto:arena-support@ptc.com) と連携して、アリーナ プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できるアリーナ のサインオン URL にリダイレクトされます。
- アリーナのサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定したアリーナに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [アリーナ] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したアリーナに自動的にサインインされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ares-for-enterprise-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ARES for Enterprise を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ares-for-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ARES for Enterprise の間でシングル サインオンを構成する方法について説明します。

この記事では、ARES for Enterprise と Microsoft Entra ID を統合する方法について説明します。 ARES for Enterprise を Microsoft Entra ID と統合すると、次のことができます:

- ARES for Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して ARES for Enterpriseに自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ARES for Enterprise でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ARES for Enterprise では、 **SP** Initiated SSO がサポートされます。
- ARES for Enterprise では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ARES for Enterprise の追加

Microsoft Entra ID への ARES for Enterprise の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ARES for Enterprise を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**ARES for Enterprise**」と入力します。
4. 結果パネルから **ARES for Enterprise** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ARES for Enterprise 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ARES for Enterprise に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーとARES for Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を ARES for Enterprise と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ARES for Enterprise SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Enterprise 用の ARES テスト ユーザーを作成する - ARES for Enterprise で B.Simon の対応ユーザーを作成し、それを Microsoft Entra の表現とリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ARES for Enterprise**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://login.graebert.com`
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ARES for Enterprise SSO 用の ARES の構成

**ARES for Enterprise** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[ARES for Enterprise サポート チーム](mailto:support@graebert.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ARES for Enterprise のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを ARES for Enterprise に作成します。 ARES for Enterprise では、 **Just-In-Time プロビジョニング**がサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ ARES for Enterprise に存在しない場合は、ARES for Enterprise にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ARES for Enterprise のサインオン URL にリダイレクトされます。
- ARES for Enterprise のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ARES for Enterprise] タイルを選択すると、このオプションは ARES for Enterprise のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ariba-tutorial"} -->
## Microsoft Entra ID で Ariba for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ariba-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Ariba の間のシングル サインオンを構成する方法について説明します。

この記事では、Ariba と Microsoft Entra ID を統合する方法について説明します。 Ariba を Microsoft Entra ID と統合すると、次のことが可能になります。

- Ariba へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Ariba に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Ariba でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Asana では、**SP** によって開始される SSO がサポートされます

### ギャラリーからの Ariba の追加

Microsoft Entra ID への Ariba の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Ariba を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Ariba**」と入力します。
4. 結果のパネルから **[Ariba]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Ariba に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、Ariba に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Ariba の関連ユーザーとの間にリンク関係を確立する必要があります。

Ariba に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Ariba の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Ariba のテスト ユーザーの作成** - Ariba で B.Simon に相当するユーザーを作成し、それが Microsoft Entra におけるユーザーの表現とリンクされます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Ariba**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [Asana のドメインと URL] のシングル サインオン情報]

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    ```http
    https://<subdomain>.sourcing.ariba.com
    https://<subdomain>.supplier.ariba.com
    ```

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `http://<subdomain>.procurement-2.ariba.com`

    c. [ **応答 URL]** に、次のいずれかの URL パターンを入力します。

    | [応答 URL] |
    | --- |
    | `https://<subdomain>.ariba.com/CUSTOM_URL` |
    | `https://<subdomain>.procurement-eu.ariba.com/CUSTOM_URL` |
    | `https://<subdomain>.procurement-eu.ariba.com` |
    | `https://<subdomain>.procurement-2.ariba.com` |
    | `https://<subdomain>.procurement-2.ariba.com/CUSTOM_URL` |

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、および応答 URL で値を更新します。 ここでは、識別子に一意の文字列値を使用することをお勧めします。 これらの値を取得するには、Ariba クライアント サポート チーム (**1-866-218-2155**) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Ariba の SSO の構成

アプリケーション用に SSO を構成するには、Ariba サポート チームに電話 (**1-866-218-2155**) で連絡し、指示された方法に従って、ダウンロードした**証明書 (Base64)** ファイルを提出してください。

#### Ariba のテスト ユーザーの作成

このセクションでは、Ariba で Britta Simon というユーザーを作成します。 Ariba サポート チーム (**1-866-218-2155**) と協力して、Ariba プラットフォームでユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Ariba のサインオン URL にリダイレクトされます。
- Ariba のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Ariba] タイルを選択すると、このオプションは Ariba のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/articulate360-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用にアーティキュレート 360 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/articulate360-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Articulate 360 の間のシングル サインオンを構成する方法について説明します。

この記事では、アーティキュレート 360 と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Articulate 360 を統合すると、次のことが可能になります。

- Articulate 360 にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Articulate 360 に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Articulate 360 のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- アーティキュレート360では、**SP**と**IDP**によるSSOをサポートしています。

### ギャラリーから Articulate 360 を追加する

Microsoft Entra ID への Articulate 360 の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Articulate 360 を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Articulate 360**」と入力します。
4. 結果パネルから **[アーティキュレーション 360** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Articulate 360 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Articulate 360 に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Articulate 360 の関連ユーザーとの間にリンク関係を確立する必要があります。

Articulate 360 に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Articulate 360 SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Articulate 360 のテスト ユーザーの作成** - Articulate 360 で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Articulate 360**&gt;**シングルサインオン**にブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.okta.com/saml2/service-provider/<SAMPLE>`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://id.articulate.com/sso/saml2`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://id.articulate.com/`

    注

    識別子の値は実際の値ではありません。 この値を実際の識別子で更新します。 この値を取得するには、 [Articulate 360 サポート チーム](mailto:enterprise@articulate.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Articulate 360 アプリケーションは特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の画像を示すスクリーンショット。]
8. Articulate 360 アプリケーションでは、次に示すように、既定の属性を特定の属性に置き換えることが想定されています。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. [ **アーティキュレーション 360 のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成を適切な U R L にコピーするためのスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Articulate 360 の SSO の構成

**アーティキュレート 360** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を[、Articulate 360 サポート チーム](mailto:enterprise@articulate.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Articulate 360 のテスト ユーザーの作成

このセクションでは、Articulate 360 で B. Simon というユーザーを作成します。 Articulate 360 では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Articulate 360 にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できるアーティキュレート 360 Sign-On URL にリダイレクトされます。
- Articulate 360 のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Articulate 360 に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [アーティキュレーション 360] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したアーティキュレート 360 に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/aruba-user-experience-insight-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Aruba User Experience Insight を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aruba-user-experience-insight-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Aruba User Experience Insight の間でシングル サインオンを構成する方法について説明します。

この記事では、Aruba User Experience Insight と Microsoft Entra ID を統合する方法について説明します。 Aruba User Experience Insight と Microsoft Entra ID を統合すると、次のことができます。

- Aruba User Experience Insight にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントで自動的に Aruba User Experience Insight にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Aruba User Experience Insight でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Aruba User Experience Insight では、 **SP と IDP** によって開始される SSO がサポートされます
- Aruba User Experience Insight では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Aruba User Experience Insight の追加

Microsoft Entra ID への Aruba User Experience Insight の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Aruba User Experience Insight を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Aruba User Experience Insight**」と入力します。
4. 結果パネルから **Aruba User Experience Insight** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Aruba User Experience Insight に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Aruba User Experience Insight に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Aruba User Experience Insight の関連ユーザーとの間にリンク関係を確立する必要があります。

Aruba User Experience Insight に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Aruba User Experience Insight の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Aruba User Experience Insight のテスト ユーザーを作成** - Microsoft Entra のユーザーを反映する B.Simon の対応ユーザーを Aruba User Experience Insight で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Aruba User Experience Insight**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `urn:auth0:cape:<CUSTOMER_SSO_UID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cape.auth0.com/login/callback?connection=<CUSTOMER_SSO_UID>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://dashboard.capenetworks.com/login`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Aruba User Experience Insight クライアント サポート チーム](mailto:support@capenetworks.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Aruba User Experience Insight アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Aruba User Experience Insight アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | upn | user.userprincipalname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Aruba User Experience Insight のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Aruba User Experience Insight の SSO の構成

**Aruba User Experience Insight** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Aruba User Experience Insight サポート チーム](mailto:support@capenetworks.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Aruba User Experience Insight のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Aruba User Experience Insight に作成します。 Aruba User Experience Insight では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Aruba User Experience Insight にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Aruba User Experience Insight のサインオン URL にリダイレクトされます。
- Aruba User Experience Insight のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Aruba User Experience Insight に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Aruba User Experience Insight タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Aruba User Experience Insight に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/asana-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Asana を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/asana-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-26
- Summary: Microsoft Entra ID から Asana に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Asana と Microsoft Entra ID の両方で、自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使って、[Asana](https://www.asana.com/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Asana でユーザーを作成する。
- アクセスが不要になった場合に Asana のユーザーを削除します。
- Microsoft Entra ID と Asana の間でユーザー属性の同期を維持します。
- Asana でグループとグループ メンバーシップを設定する。
- Asana に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/asana-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 有効な [Enterprise](https://www.asana.com/pricing) プラン以上の Asana テナントが有効化されています。
- Admin アクセス許可がある Asana のユーザー アカウント

注

Microsoft Entra プロビジョニング統合では、Asana で使用できる [Asana API](https://asana.com/developers/api-reference/users) が必要です。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Asana の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Asana を構成する

ヒント

Asana で SAML ベースのシングル サインオンを有効にするには、説明されている手順に従ってください。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Asana でサービス アカウント トークンを生成する

1. 管理者アカウントを使用して [Asana](https://app.asana.com/-/login) にサインインします。
2. 上部のバーからプロファイル写真を選択し、[ **管理コンソール**] を選択します。
3. 管理コンソールで [アプリ] タブを選択します。
4. [サービス アカウント] を選択します。
5. [ **サービス アカウントの追加]** を選択し、次の手順を実行します。

    [Image: サービス アカウント トークンのスクリーンショット。]

    1. 必要に応じて **、名前** と **説明** を更新します。
    2. [ **アクセス許可スコープ** ] セクションで、[ **スコープ付きアクセス許可** ] と [ **ユーザー プロビジョニング (SCIM)]**を選択します。 次のアクセス許可スコープが選択されていることを確認します。
        - ユーザー: 読み取り
        - ユーザー: 作成と変更
        - Teams: 読み取り
        - Teams：チームの作成と変更
    3. [ **変更の保存] を選択します**。
6. トークンをコピーします。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Asana を追加する

Microsoft Entra アプリケーション ギャラリーから Asana を追加して、Asana へのプロビジョニングの管理を開始します。 SSO のために Asana を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Asana への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて、Asana でユーザーとグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Asana の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Asana]** を選択します。

    [Image: [アプリケーション] 一覧の Asana リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Asana によって提供される Asana テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Asana に接続できることを確認します。 接続できない場合は、Asana にお問い合わせいただき、アカウントのセットアップを確認してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

[Image: プロビジョニングプロパティのスクリーンショット。]

1. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
2. **[属性マッピング]** セクションで、Microsoft Entra ID から Asana に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Asana のユーザー アカウントとの照合に使用されます。 [一致する対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づいたユーザーのフィルター処理が確実に Asana API でサポートされているようにする必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Asana によって要求される |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.formatted | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |  |  |
    | addresses[type eq "work"].country | 糸 |  |  |
    | addresses[type eq "work"].region | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
3. **[グループ] を選択します**。
4. **[属性マッピング]** セクションで、Microsoft Entra ID から Asana に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Asana のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Asana で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
5. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
6. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
7. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

- 11/06/2021 - **externalId, name.givenName および name.familyName** のサポートが削除されました。 **preferredLanguage、title、および urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department** のサポートが追加されました。 **グループ プロビジョニング**が有効になりました。
- 2023 年 5 月 23 日 - **preferredLanguage** のサポートが削除され、**urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager** のサポートが追加されました。
- 09/07/2023 - Added support for **addresses[type eq "work"].locality, addresses[type eq "work"].region, addresses[type eq "work"].country, phoneNumbers[type eq "work"].value, urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber, urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter, urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization and urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division**.
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/asana-tutorial"} -->
## Microsoft Entra ID で Asana for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/asana-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Asana 間のシングル サインオンを構成する方法について説明します。

この記事では、Asana と Microsoft Entra ID を統合する方法について説明します。 Asana を Microsoft Entra ID と統合すると、次のことができます。

- Asana にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Asana に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Asana でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Asana では、 **SP** Initiated SSO がサポートされます。
- Asana では、 [**自動** ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/asana-provisioning-tutorial)。

### ギャラリーからの Asana の追加

Microsoft Entra ID への Asana の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Asana を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を開きます。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Asana」**と入力します。
4. 結果パネルから **Asana** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Asana に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Asana に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Asana での関連ユーザーとの間にリンク関係を確立する必要があります。

Asana に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Asana SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Asana のテストユーザーの作成** - Asana で B.Simon に対応するユーザーを作成し、それを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Asana** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://app.asana.com/`

    注

    識別子 (エンティティ ID) に別の値が必要な場合は、 [Microsoft にお問い合わせください](https://form-beta.asana.com/?k=BT9rHN4rEoRKARjEYg6neA&amp;d=15793206719)。

    b。 [ **応答 URL (Assertion Consumer Service URL)]** テキスト ボックスに、URL を入力します。 `https://app.asana.com/-/saml/consume`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.asana.com/a/<email_suffix>`

    注

    多数のメール ドメインを所有している場合は、いずれかのドメインを自由に使用できます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Asana のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

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

このセクションでは、B.Simon に Asana へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Asana** にアクセスします。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

#### Asana の SSO の構成

1. 別のブラウザー ウィンドウで、Asana アプリケーションにサインオンします。 Asana で SSO を構成するには、画面の右上隅にあるアバターを選択して管理コンソールにアクセスします。 次に、[ **管理コンソール**] を選択します。

    [Image: Asana 管理コンソールの設定を示すスクリーンショット。]
2. [ **セキュリティ** ] タブに移動します。次に、[ **SAML 認証**] を選択します。

    [Image: 単一 Sign-On 組織の設定の構成を示すスクリーンショット。]
3. 次のページで以下の手順を実行します。

    [Image: 単一 Sign-On SAML 認証設定の構成を示すスクリーンショット。]

    a. ゲスト アカウントを除くすべてのメンバーに対して [必須] を選択します。

    b。 **サインイン URL** をサインイン ページ **URL テキスト ボックス**に貼り付けます。 c. **Certificate (Base64)** コンテンツを **X.509 証明書** フィールドに貼り付けてください。 d. メンバーのセッション期間を設定します。 e. **[保存] を選択します**。

注

さらにサポートが必要な場合は、SSO の設定に関する Asana [ガイド](https://asana.com/guide/help/premium/authentication#gl-saml) を参照してください。

#### Asana テスト ユーザーの作成

このセクションの目的は、Asana で Britta Shimon というユーザーを作成することです。 Asana では、自動ユーザー プロビジョニングがサポートされています。この設定は、既定で有効になっています。 自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/asana-provisioning-tutorial) 。

**ユーザーを手動で作成する必要がある場合は、次の手順を実行してください。**

このセクションでは、Asana で Britta Simon というユーザーを作成します。

1. **Asana** で、左側のパネルの **Teams** セクションに移動します。 プラス記号ボタンを選択します。

    [Image: Microsoft Entra テスト ユーザーの作成を示すスクリーンショット。]
2. テキスト ボックスにユーザーの電子メール ( **britta.simon@contoso.com** など) を入力し、[ **招待**] を選択します。
3. [ **招待の送信] を選択します**。 新しいユーザーは、自分のメール アカウントにメールを受信します。 ユーザーはアカウントを作成して検証する必要があります。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Asana のサインオン URL にリダイレクトされます。
- Asana のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Asana タイルを選択すると、このオプションは Asana のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/asccontracts-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に ASC Contracts を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/asccontracts-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ASC Contracts の間にシングル サインオンを構成する方法について説明します。

この記事では、ASC Contracts と Microsoft Entra ID を統合する方法について説明します。 ASC Contracts と Microsoft Entra ID を統合すると、次のことができます。

- ASC Contracts にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って ASC Contracts に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ASC Contracts のシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ASC Contracts では、**IDP** Initiated SSO がサポートされます。

### ギャラリーから ASC Contracts を追加する

Microsoft Entra ID への ASC Contracts の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに ASC Contracts を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ASC Contracts**」と入力します。
4. 結果のパネルから **[ASC Contracts]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ASC Contracts 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ASC Contracts に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ASC Contracts の関連ユーザーとの間にリンク関係を確立する必要があります。

ASC Contracts に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ASC Contracts の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ASC Contracts のテストユーザーを作成する** - これは、ASC Contracts 内で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザーにリンクさせるためです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**ASC Contracts**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** ページで、次の手順を実行します。

    a. **[識別子]** ボックスに、`https://<subdomain>.asccontracts.com/shibboleth` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<subdomain>.asccontracts.com/shibboleth.sso/login` のパターンを使用して URL を入力します

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、ASC Networks Inc. (ASC) チーム (**613.599.6178**) にお問い合わせください。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[ASC Contracts のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 適切な構成 URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### ASC Contracts の SSO の構成

**ASC Contracts** 側のシングル サインオンを構成するには、ASC Networks Inc. (ASC) のサポートに電話して (**613.599.6178**)、ダウンロードした**フェデレーション メタデータ XML** を提供します。 両方の側で SAML SSO 接続を正しく設定するために、サポート チームがこのアプリケーションをセットアップします。

#### ASC Contracts のテスト ユーザーの作成

ASC Networks Inc. (ASC) のサポート チーム (**613.599.6178**) と協力して、ASC Contracts プラットフォームに追加されたユーザーを取得します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ASC Contracts に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ASC Contracts] タイルを選択すると、SSO を設定した ASC Contracts に自動的にサインインします。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ascentis-tutorial"} -->
## Microsoft Entra ID で Ascentis for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ascentis-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Ascentis の間にシングル サインオンを構成する方法について説明します。

この記事では、Ascentis と Microsoft Entra ID を統合する方法について説明します。 Ascentis と Microsoft Entra ID を統合すると、次のことができます。

- Ascentis にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Ascentis に自動的にサインインできるようにする。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Ascentis でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Ascentis では、**SP および IDP** による SSO がサポートされます

### ギャラリーからの Ascentis の追加

Microsoft Entra ID への Ascentis の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Ascentis を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Ascentis**」と入力します。
4. 結果パネルから **[Ascentis** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra のシングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Ascentis に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Ascentis の関連ユーザーとの間にリンク関係を確立する必要があります。

Ascentis に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します:

1. **Microsoft Entra SSO を構成** する - ユーザーがこの機能を使用できるようにします。
2. **Ascentis の SSO の構成** - アプリケーション側で単一 Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Ascentis のテストユーザーを作成する** - Ascentis で Britta Simon に対応するユーザーを作成し、Microsoft Entra のユーザーにリンクします。
6. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Ascentis** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://services.ascentis.com/iam/samlsso?spEntityID=<clientname>.ascentis.com`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```https
    https://selfservice.ascentis.com/<clientname>/STS/signin.aspx?SAMLResponse=true
    https://selfservice2.ascentis.com/<clientname>/STS/signin.aspx?SAMLResponse=true
    ```

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには、 [Ascentis クライアント サポート チーム](mailto:support@ascentis.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Ascentis のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Ascentis SSO の構成

**Ascentis** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Ascentis サポート チーム](mailto:support@ascentis.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

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

このセクションでは、B.Simon に Ascentis へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Ascentis** を参照してください。
3. アプリの概要ページで、[ **管理** ] セクションを見つけて、[ **ユーザーとグループ**] を選択します。

    [Image: [ユーザーとグループ] リンク]
4. [**ユーザーの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。

    [Image: [ユーザーの追加] リンク]
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

#### Ascentis テストユーザーの作成

このセクションでは、Ascentis で Britta Simon というユーザーを作成します。 [Ascentis サポート チーム](mailto:support@ascentis.com)と協力して、Ascentis プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Ascentis] タイルを選択すると、SSO を設定した Ascentis に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/asignet-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AsignetSSOIntegration を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/asignet-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AsignetSSOIntegration の間でシングル サインオンを構成する方法について説明します。

この記事では、AsignetSSOIntegration と Microsoft Entra ID を統合する方法について説明します。 AsignetSSOIntegration を Microsoft Entra ID と統合すると、次のことができるようになります。

- AsignetSSOIntegration にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して AsignetSSOIntegration に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- AsignetSSOIntegration でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AsignetSSOIntegration は、**SP** および **IDP** initiated SSO をサポートします。

### ギャラリーから AsignetSSOIntegration を追加する

AsignetSSOIntegration の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリ リストに AsignetSSOIntegration を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**AsignetSSOIntegration**」と入力します。
4. 結果パネルから **[AsignetSSOIntegration]** を選び、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AsignetSSOIntegration の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使い、AsignetSSOIntegration に対して Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと AsignetSSOIntegration の関連ユーザーとの間にリンク関係を確立する必要があります。

AsignetSSOIntegration に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AsignetSSOIntegration の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AsignetSSOIntegration テストユーザーを作成** - AsignetSSOIntegration で B.Simon に対応するユーザーを作成し、それを Microsoft Entra 上の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AsignetSSOIntegration**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://trim.corp.microsoft.com/sso.ashx`
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[AsignetSSOIntegration のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AsignetSSOIntegration SSO を構成する

**AsignetSSOIntegration** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [AsignetSSOIntegration サポート チーム](mailto:us@asignet.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### AsignetSSOIntegration テスト ユーザーを作成する

このセクションでは、AsignetSSOIntegration で Britta Simon というユーザーを作成します。 AsignetSSOIntegration プラットフォームにユーザーを作成するには、[AsignetSSOIntegration サポート チーム](mailto:us@asignet.com)に相談してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる AsignetSSOIntegration のサインオン URL にリダイレクトされます。
- [AsignetSSOIntegration Sign-on URL] (AsignetSSOIntegration サインオン URL) に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した AsignetSSOIntegration に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [AsignetSSOIntegration] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した AsignetSSOIntegration に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/askspoke-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して自動ユーザー プロビジョニング用に askSpoke を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/askspoke-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-26
- Summary: Microsoft Entra ID から askSpoke に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために askSpoke ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して askSpoke にユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- askSpoke でユーザーを作成する
- アクセスが不要になった場合に askSpoke のユーザーを削除する
- Microsoft Entra ID と askSpoke の間でユーザー属性の同期を維持する
- askSpoke でグループとグループ メンバーシップをプロビジョニングする
- askSpoke への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/askspoke-tutorial) (推奨)

- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある askSpoke のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と askSpoke の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように askSpoke を構成する

1. askSpoke 管理コンソールにログインします。
2. **[設定]** に移動します。
3. [統合] タブ **を** 選択します。
4. SCIM カードまでスクロールします。 [ **接続**] を選択します。

    [Image: 編集]
5. [ **SCIM を有効にする] を選択します**。
6. **API トークン**をコピーして保存します。 この値は、askSpoke アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: API]
7. テナント URL は、askSpoke URL の後に **/scim/v2** が続きます。 (例: `https://example.askspoke.com/scim/v2`)。 この値は、askSpoke アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドに入力されます。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから askSpoke を追加する

Microsoft Entra アプリケーション ギャラリーから askSpoke を追加して、askSpoke へのプロビジョニングの管理を開始します。 SSO のために askSpoke を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: askSpoke への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で askSpoke の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**に移動する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **askSpoke** を選択します。

    [Image: アプリケーションの一覧の askSpoke リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、askSpoke テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が askSpoke に接続できることを確認します。 接続に失敗した場合は、askSpoke アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

[Image: プロビジョニングプロパティのスクリーンショット。]

1. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
2. 「属性マッピング」セクションで、Microsoft Entra ID から askSpoke に同期されるユーザー **属性** を確認します。 **照合**プロパティとして選択されている属性は、更新操作のために askSpoke のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、askSpoke API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | emails[type eq "work"].value | 糸 |  |
    | 活動中 | ブール値 |  |
    | タイトル | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | addresses[type eq "work"].country | 糸 |  |
    | addresses[type eq "work"].region | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |  |
    | urn:ietf:params:scim:schemas:extension:SpokeCustom:2.0:User:startDate | 糸 |  |
3. **[グループ] を選択します**。
4. **属性マッピング** セクションで、Microsoft Entra ID から askSpoke に同期されるグループ属性を確認してください。 **[照合**プロパティ] として選択されている属性は、更新操作のために askSpoke のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | members | リファレンス |  |
5. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
6. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
7. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/askspoke-tutorial"} -->
## Microsoft Entra ID で askSpoke for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/askspoke-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と askSpoke の間のシングル サインオンを構成する方法について説明します。

この記事では、askSpoke と Microsoft Entra ID を統合する方法について説明します。 askSpoke を Microsoft Entra ID と統合すると、次のことが可能になります。

- askSpoke にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで askSpoke に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- askSpoke でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- askSpoke では、**SP と IDP** によって開始される SSO がサポートされます。
- askSpoke では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- askSpoke では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/askspoke-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの askSpoke の追加

Microsoft Entra ID に askSpoke を統合するには、ギャラリーからご自分の管理対象 SaaS アプリの一覧に askSpoke を追加構成する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**askSpoke**」と入力します。
4. 結果のパネルから **[askSpoke]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### askSpoke に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、askSpoke に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと、askSpoke での関連ユーザーとの間にリンク関係を確立する必要があります。

askSpoke に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **askSpoke SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **askSpoke テスト ユーザーの作成** - askSpoke で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[askSpoke]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.askspoke.com/saml/callback`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値は、[askSpoke のクライアント サポート チーム](mailto:support@askspoke.com)に問い合わせて取得してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://askspoke.com/login`
7. askSpoke アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングをお使いの SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、askSpoke アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[askSpoke の設定]** セクションで、ご自分の要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### askSpoke SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として askSpoke Web サイトにサインインします。
2. 左側のナビゲーション ウィンドウから **[設定]** タブを選択します。

    [Image: askSpoke の設定タブ]
3. SSO **まで下** にスクロールし、[ **接続**] を選択します。

    [Image: askSpoke の接続]
4. [ **SAML と SCIM の有効化]** セクションで、次の手順を実行します。

    [Image: askSpoke SAMLおよびSCIMセクションを有効化する]

    1. **[サインオン URL]** ボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。
    2. **[発行者 ID]** ボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。
    3. ダウンロードした **証明書 (Base64)** ファイルをメモ帳で開き、その内容をコピーして **[公開証明書]** テキスト ボックスに貼り付けます。
    4. **[ACS URL]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[応答 URL]** ボックスに貼り付けます。
    5. [ **SAML 接続のテスト] を**選択します。

#### askSpoke テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを askSpoke に作成します。 askSpoke では、Just-In-Time プロビジョニングがサポートされており、これは既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ askSpoke に存在しない場合は、askSpoke にアクセスしたときに新しいユーザーが作成されます。

askSpoke では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/askspoke-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる askspoke のサインオン URL にリダイレクトされます。
- askspoke のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した askspoke に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで askspoke タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した askspoke に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/askyourteam-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AskYourTeam を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/askyourteam-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AskYourTeam の間でシングル サインオンを構成する方法について説明します。

この記事では、AskYourTeam と Microsoft Entra ID を統合する方法について説明します。 AskYourTeam を Microsoft Entra ID と統合すると、次のことが可能になります。

- AskYourTeam にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで AskYourTeam に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AskYourTeam でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AskYourTeam では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーからの AskYourTeam の追加

Microsoft Entra ID への AskYourTeam の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に AskYourTeam を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**AskYourTeam**」と入力します。
4. 結果のパネルから **[AskYourTeam]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AskYourTeam 向けに Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、AskYourTeam に対して Microsoft Entra SSO を構成およびテストします。 SSO を機能させるには、Microsoft Entra ユーザーと AskYourTeam の関連ユーザーの間にリンク関係を確立する必要があります。

AskYourTeam に対して Microsoft Entra SSO を構成およびテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AskYourTeam の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AskYourTeam テストユーザーを作成** - AskYourTeam で B.Simon に対応するユーザーを作成し、このユーザーを Microsoft Entra 内の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[AskYourTeam]**&gt;**[シングル サインオン]**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY>.app.askyourteam.com/users/auth/saml/callback`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY>.app.askyourteam.com/login`

    注

    これらの値は実際の値ではありません。 これらの値は、実際の応答 URL と Sign-On URL 値で更新します。この値については、後で説明します。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[AskYourTeam のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AskYourTeam の SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として AskYourTeam 企業サイトにサインインします。
2. [ **所属組織**] を選択します。

    [Image: [My Organization](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/マイ組織) リンクを示すスクリーンショット。]
3. **統合**を選択します。

    [Image: このスクリーンショットは、[Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) リンクを示しています。]
4. [ **設定の編集] を選択します**。

    [Image: このスクリーンショットは、[Edit Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定の編集) ボタンを備えた [Single Sign-On](シングル サインオン) メッセージを示しています。]
5. **[Edit Single Sign-On Integration](シングル サインオンの統合の編集)** ページで、次の手順を実行します。

    [Image: このスクリーンショットは、[Edit Single Sign-On Integration](シングル サインオンの統合の編集) を示しています。ここで、この手順の値を入力できます。]

    a. **[SAML シングル サインオン サービス URL]** テキスト ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    b。 **[SAML エンティティ ID]** テキスト ボックスに、前にコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    c. **[サインアウト URL]** テキスト ボックスに、前にコピーした**ログアウト URL** の値を貼り付けます。

    d. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[SAML Signing Certificate - Base64] (SAML 署名証明書 - Base64)** テキスト ボックスに貼り付けます。

    注

    または、[**ファイルの選択**] オプションを選択して**、フェデレーション メタデータ XML** ファイルをアップロードすることもできます。

    e. **応答 URL (Assertion Consumer Service URL)** の値をコピーし、その値を **[基本的な SAML 構成]** セクションの **[応答 URL]** テキスト ボックスに貼り付けます。

    f. **[サインオン URL**] の値をコピーし、[**基本的な SAML 構成**] セクションの **[サインオン URL**] テキスト ボックスにこの値を貼り付けます。

    g. **保存** を選択します。

#### AskYourTeam のテスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、管理者として AskYourTeam Web サイトにサインインします。
2. [ **所属組織**] を選択します。

    [Image: このタスクを開始するための [My Organization](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/マイ組織) リンクを示すスクリーンショット。]
3. [ **ユーザー]** を選択し、[ **新しいユーザー**] を選択します。

    [Image: このスクリーンショットは、[New User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザー) が選択された状態の [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) リンクを示しています。]
4. **[New user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザー)** セクションで、次の手順を実行します。

    [Image: このスクリーンショットは、[New user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザー) セクションを示しています。ここで、ユーザー情報を入力します。]

    1. **[First name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** テキスト ボックスに、ユーザーの名前を入力します。
    2. **[Last name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** テキスト ボックスに、ユーザーの姓を入力します。
    3. **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** テキスト ボックスに、ユーザーのメール アドレス (B.Simon@contoso.com など) を入力します。
    4. 組織の要件に従って、ユーザーの**ロール**を選択します。
    5. **保存** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる AskYourTeam サインオン URL にリダイレクトされます。
- AskYourTeam のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した AskYourTeam に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで AskYourTeam タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した AskYourTeam に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/assent-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Assent SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/assent-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Assent SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、Assent SSO と Microsoft Entra ID を統合する方法について説明します。 Assent SSO と Microsoft Entra ID を統合すると、次のことができます。

- Assent SSO にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Assent SSO に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Assent SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Assent SSO では、 **SP** によって開始される SSO のみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Assent SSO を追加する

Microsoft Entra ID への Assent SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Assent SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Assent SSO**」と入力します。
4. 結果パネルから **[Assent SSO** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Assent SSO の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Assent SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Assent SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Assent SSO に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Assent SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Assent SSO テストユーザーの作成** - Assent SSO で B.Simon に対応するテストユーザーを作成し、それを Microsoft Entra ID ユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**アセント SSO**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `http://sts.assentcompliance.com/adfs/services/trust`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://sts.assentcompliance.com/adfs/ls/`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://sts.assentcompliance.com/adfs/ls/`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Assent SSO の構成

**Assent SSO** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Assent SSO サポート チーム](mailto:customersupport@assent.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Assent SSO テスト ユーザーの作成

このセクションでは、Assent SSO で B.Simon というユーザーを作成します。 [Assent SSO サポート チーム](mailto:customersupport@assent.com)と協力して、Assent SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Assent SSO サインオン URL にリダイレクトされます。
- Assent SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Assent SSO] タイルを選択すると、このオプションは Assent SSO のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/asset-infinity-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Asset Infinity を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/asset-infinity-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-09
- Summary: Microsoft Entra ID と Asset Infinity 間にシングル サインオンを構成する方法について学習します。

この記事では、Asset Infinity と Microsoft Entra ID を統合する方法について説明します。 Asset Infinity と Microsoft Entra ID を統合すると、次のことができます:

- Asset Infinity にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Asset Infinity に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Asset Infinity でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Asset Infinity では、 **SP** によって開始される SSO のみがサポートされます。

### ギャラリーから Asset Infinity を追加する

Microsoft Entra ID への Asset Infinity の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Asset Infinity を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Asset Infinity**」と入力します。
4. 結果パネルから **[Asset Infinity]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Asset Infinity 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Asset Infinity に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Asset Infinity の関連ユーザーとの間にリンク関係を確立する必要があります。

Asset Infinity に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Asset Infinity SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Asset Infinity テスト ユーザーの作成 - Asset Infinity** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Asset Infinity**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.assetinfinity.io/api/auth/signinSaml/<CustomerName>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.assetinfinity.io/api/auth/signinSamlAcs/<CustomerName>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.assetinfinity.io/`

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには [、Asset Infinity サポート チーム](mailto:support@assetinfinity.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Asset Infinity SSO を構成する

**Asset Infinity** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Asset Infinity サポート チーム](mailto:support@assetinfinity.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Asset Infinity テスト ユーザーを作成する

このセクションでは、Asset Infinity で B.Simon というユーザーを作成します。 [Asset Infinity サポート チーム](mailto:support@assetinfinity.com)と協力して、Asset Infinity プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Asset Infinity のサインオン URL にリダイレクトします。
- Asset Infinity のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Asset Infinity] タイルを選択すると、このオプションは Asset Infinity のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/asset-planner-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Asset Planner を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/asset-planner-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Asset Planner の間のシングル サインオンを構成する方法について説明します。

この記事では、Asset Planner と Microsoft Entra ID を統合する方法について説明します。 Asset Planner を Microsoft Entra ID と統合すると、次のことが可能になります。

- Asset Planner にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Asset Planner に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Asset Planner でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Asset Planner では、**SP** Initiated SSO がサポートされます。
- Asset Planner では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Asset Planner を追加する

Microsoft Entra ID への Asset Planner の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Asset Planner を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Asset Planner**」と入力します。
4. 結果のパネルから **[Asset Planner]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Asset Planner 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Asset Planner に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Asset Planner の関連ユーザーとの間にリンク関係を確立する必要があります。

Asset Planner に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Asset Planner SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Asset Planner テストユーザーを作成** - Asset Planner で B.Simon に対応するテストユーザーを作成し、そのユーザーを Microsoft Entra 上の B.Simon の表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**Asset Planner**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかの URL を入力します。

    | **識別子** |
    | --- |
    | `https://assetplanner.com` |
    | `https://us.assetplanner.com` |
    | `https://staging.assetplanner.com` |
    | `https://training.assetplanner.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://assetplanner.com/saml/ap_acs/<IDPName>` |
    | `https://us.assetplanner.com/saml/ap_acs/<IDPName>` |
    | `https://staging.assetplanner.com/saml/ap_acs/<IDPName>` |
    | `https://training.assetplanner.com/saml/ap_acs/<IDPName>` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://assetplanner.com` |
    | `https://us.assetplanner.com` |
    | `https://staging.assetplanner.com` |
    | `https://training.assetplanner.com` |
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Set up Asset Planner](Asset Planner の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Asset Planner SSO の構成

**Asset Planner** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)**とアプリケーション構成からコピーした適切な URL を [Asset Planner サポート チーム](mailto:support@assetplanner.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Asset Planner テストユーザーの作成

このセクションでは、Britta Simon というユーザーを Asset Planner に作成します。 Asset Planner では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Asset Planner にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Asset Planner のサインオン URL にリダイレクトされます。
- Asset Planner のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Asset Planner] タイルを選択すると、このオプションは Asset Planner のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/assetbank-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Asset Bank を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/assetbank-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Asset Bank の間のシングル サインオンを構成する方法について説明します。

この記事では、Asset Bank と Microsoft Entra ID を統合する方法について説明します。 Asset Bank を Microsoft Entra ID と統合すると、次のことが可能になります。

- Asset Bank へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Asset Bank に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Asset Bank でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Asset Bank では、 **SP** Initiated SSO がサポートされます。
- Asset Bank では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Asset Bank を追加する

Microsoft Entra ID への Asset Bank の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Asset Bank を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Asset Bank**」と入力します。
4. 結果パネルから **[Asset Bank** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Asset Bank に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Asset Bank に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Asset Bank の関連ユーザーとの間にリンク関係を確立する必要があります。

Asset Bank に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Asset Bank の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Asset Bank テストユーザーの作成** - Asset Bank で B.Simon に対応するテストユーザーを作成し、Microsoft Entra でのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Asset Bank**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.assetbank-server.com/shibboleth`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.assetbank-server.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 [Asset Bank クライアント サポート チーム](mailto:support@assetbank.co.uk) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Asset Bank のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Asset Bank SSO の構成

**Asset Bank** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Asset Bank サポート チーム](mailto:support@assetbank.co.uk)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Asset Bank テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Asset Bank に作成します。 Asset Bank では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Asset Bank にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Asset Bank サポート チーム](mailto:support@assetbank.co.uk)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Asset Bank のサインオン URL にリダイレクトされます。
- Asset Bank のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Asset Bank] タイルを選択すると、このオプションは Asset Bank のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/assetsonar-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AssetSonar を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/assetsonar-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AssetSonar の間のシングル サインオンを構成する方法について説明します。

この記事では、AssetSonar と Microsoft Entra ID を統合する方法について説明します。 AssetSonar を Microsoft Entra ID と統合すると、次のことが可能になります。

- AssetSonar へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで AssetSonar に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AssetSonar でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AssetSonar では、**SP** Initiated SSO がサポートされます。
- AssetSonar では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの AssetSonar の追加

Microsoft Entra ID への AssetSonar の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に AssetSonar を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**AssetSonar**」と入力します。
4. 結果のパネルから **[AssetSonar]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AssetSonar に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、AssetSonar に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと AssetSonar の関連ユーザーとの間にリンク関係を確立する必要があります。

AssetSonar に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AssetSonar の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **B.Simon に対応する AssetSonar テスト ユーザーを作成し、Microsoft Entra 上のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[AssetSonar]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.assetsonar.com/users/sign_in`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 値を取得するには、[AssetSonar クライアント サポート チーム](mailto:support@assetsonar.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. AssetSonar アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、AssetSonar アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | first\_name | User.givenname |
    | last\_name | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Set up AssetSonar](AssetSonar の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AssetSonar の SSO の構成

**AssetSonar** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [AssetSonar サポート チーム](mailto:support@assetsonar.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### AssetSonar のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを AssetSonar に作成します。 AssetSonar では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 AssetSonar にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる AssetSonar のサインオン URL にリダイレクトされます。
- AssetSonar のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [AssetSonar] タイルを選択すると、このオプションは AssetSonar のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/astra-schedule-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Astra Schedule を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/astra-schedule-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Astra Schedule の間でシングル サインオンを構成する方法について説明します。

この記事では、Astra Schedule と Microsoft Entra ID を統合する方法について説明します。 Astra Schedule を Microsoft Entra ID を統合すると、次のことができます。

- Astra Schedule にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Astra Schedule に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Astra Schedule でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Astra Schedule では、 **SP** Initiated SSO がサポートされます。
- Astra Schedule では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Astra Schedule の追加

Microsoft Entra ID への Astra Schedule の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Astra Schedule を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Astra Schedule**」と入力します。
4. 結果パネルから **[Astra Schedule]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Astra Schedule 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Astra Schedule に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Astra Schedule の関連ユーザーとの間にリンク関係を確立する必要があります。

Astra Schedule に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Astra Schedule の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Astra Schedule のテストユーザーを作成し、Microsoft Entra の B.Simon に対応させます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Astra Schedule**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.aaiscloud.com/<CUSTOMER_INSTANCE>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.aaiscloud.com/<CUSTOMER_INSTANCE>/SAML/AssertionConsumerService.aspx`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.aaiscloud.com/<CUSTOMER_INSTANCE>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、Sign-On URL でこれらの値を更新します。 これらの値を取得するには [、Astra Schedule クライアント サポート チーム](https://help.adastra.live) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Astra Schedule のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Astra Schedule SSO の構成

**Astra Schedule** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Astra Schedule サポート チーム](mailto:cloudoperations@aais.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Astra Schedule テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Astra Schedule に作成します。 Astra Schedule では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Astra Schedule にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Astra Schedule のサインオン URL にリダイレクトされます。
- Astra Schedule のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Astra Schedule] タイルを選択すると、このオプションは Astra Schedule のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/astro-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Astro を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/astro-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-26
- Summary: Microsoft Entra ID から Astro にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザーとグループ プロビジョニングを構成するために Astro ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーを [Astro](https://www.astronomer.io/product/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Astro でユーザーを作成します。
- アクセスが不要になったら、Astro のユーザーを削除します。
- Microsoft Entra ID と Astro の間でユーザー属性の同期を維持します。
- Astro でグループとグループ メンバーシップをプロビジョニングする。
- Astro に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/astro-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Astro のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
- [Microsoft Entra ID と Astro の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Astro を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように Astro を構成するには、Astro サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Astro を追加する

Microsoft Entra アプリケーション ギャラリーから Astro を追加して、Astro へのプロビジョニングの管理を開始します。 SSO 用に Astro を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Astro への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて Astro のユーザーとグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Astro の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Astro**] を選択します。

    [Image: アプリケーションの一覧の [Astro] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Astro テナントの URL とシークレット トークンを入力します。 Microsoft Entra ID が Astro に接続できることを確認するには、[ **テスト接続** ] を選択します。 接続に失敗した場合は、Astro アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

[Image: プロビジョニングプロパティのスクリーンショット。]

1. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
2. [属性マッピング] セクションで、Microsoft Entra ID から Astro に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Astro のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Astro API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | アストロによって必須とされる |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |

**[グループ] を選択します**。

1. [属性マッピング] セクションで、Microsoft Entra ID から Astro に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Astro のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | アストロによって必須とされる |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
2. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
3. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
4. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/astro-tutorial"} -->
## Microsoft Entra ID で Astro for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/astro-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Astro の間でシングル サインオンを構成する方法について説明します。

この記事では、Astro と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Astro を統合すると、次のことができます。

- Microsoft Entra ID を使用して、Astro へのアクセスを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Astro に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Astro でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Astro では、**SP開始SSO** と **IDP開始SSO** の両方をサポートしています。
- Astro では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Astro の追加

Microsoft Entra ID への Astro の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Astro を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Astro**」と入力します。
4. 結果パネルから **[Astro** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Astro の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Astro に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Astro の関連ユーザーとの間にリンク関係を確立する必要があります。

Astro に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Astro SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Astro のテスト ユーザーの作成 - Astro** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Astro**&gt;**シングルサインオン**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:astronomer-prod:samlp-<ID>`

    b。 **[応答 URL]** ボックスに、`https://auth.astronomer.io/login/callback?connection=samlp-<ID>` のパターンを使用して URL を入力します
6. あなたが望む場合は、**SP**開始モードでアプリケーションを構成するために、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、URL: `https://cloud.astronomer.io/login` を入力します。

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Astro サポート チーム](mailto:support@astronomer.io) に問い合わせてください。 Microsoft Entra 管理センターの「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. **「SAML でのシングル サインオンの設定」** ページにある **SAML 署名証明書** セクションで、**証明書 (PEM)** を見つけ、**PEM 証明書のダウンロード** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Astro SSO の構成

**Astro** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** と Microsoft Entra 管理センターからコピーした適切な URL を [Astro サポート チーム](mailto:support@astronomer.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Astro テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Astro に作成します。 Astro では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Astro にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで **[このアプリケーションをテスト]** を選択します。 このオプションは、ログイン フローを開始できる Astro Sign on URL にリダイレクトします。
- Astro のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Astro に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Astro] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Astro に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/atea-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Atea を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/atea-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID から Atea へのユーザー アカウントの自動プロビジョニングおよび自動プロビジョニング解除を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Atea と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Atea](https://www.atea.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Atea でユーザーを作成する
- アクセスが不要になった場合に Atea のユーザーを削除する
- Microsoft Entra ID と Atea の間でユーザー属性の同期を維持する
- Code Auth Grant Flow 認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 管理者のアクセス許可がある Atea のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Atea の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように Atea を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように Atea を構成するには、 [Atea](mailto:sso.support@atea.com) サポートにお問い合わせください。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Atea を追加する

Microsoft Entra アプリケーション ギャラリーから Atea を追加して、Atea へのプロビジョニングの管理を開始します。 SSO のために Atea を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Atea への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて Atea のユーザーやグループを作成、更新、無効化するよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Atea の自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧 **で [Atea**] を選択します。

    [Image: アプリケーションの一覧の Atea のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、atea テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが atea に接続できることを確認します。 接続に失敗した場合は、atea アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

[Image: プロビジョニングプロパティのスクリーンショット。]

1. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
2. [属性マッピング] セクションで、Microsoft Entra ID から Atea に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Atea のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Atea API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | LawVu で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | name.formatted | 糸 |  | ✓ |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | ロケール | 糸 |  |  |
3. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
4. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
5. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### 変更履歴

- 2022 年 10 月 25 日 - コア ユーザー属性 **nickName** を削除します。
- 2022 年 10 月 25 日 - コア ユーザー属性 **名.formatted** のマッピングを **Join(" ", [givenName], [surname]) -&gt; name.formatted** に変更しました。
- 2022 年 10 月 25 日 - Atea アプリのすべての OAuth 構成 URL のドメイン名が Atea 所有ドメインに変更されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/athena-systems-login-platform-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用にアテナ システム ログイン プラットフォームを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/athena-systems-login-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Athena Systems Login Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Athena Systems Login Platform と Microsoft Entra ID を統合する方法について説明します。 Athena Systems Login Platform と Microsoft Entra ID を統合すると、次のことが可能になります。

- Athena Systems Login Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Athena Systems Login Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Athena Systems Login Platform でのシングルサインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Athena Systems Login Platform は **、SP と IDP** によって開始される SSO をサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Athena Systems Login Platform の追加

Microsoft Entra への Athena Systems Login Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Athena Systems Login Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [**ギャラリーから追加する**] セクションで、検索ボックスに「**Athena Systems Login Platform**」と入力します。
4. 結果パネルで [**Athena Systems Login Platform**] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Athena Systems Login Platform 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Athena Systems Login Platform に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Athena Systems Login Platform の関連ユーザーの間にリンク関係を確立する必要があります。

Athena Systems Login Platform に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Athena Systems Login Platform SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Athena Systems Login Platform のテスト ユーザーの作成** - Athena Systems Login Platform で B.Simon に対応するユーザーを作成し、Microsoft Entra のこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Athena Systems Login Platform]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **[基本的な SAML 構成]** セクションで、アプリケーションを **SP** 開始モードで構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、値を入力します。 `athenasystems`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://login.athenasystems.com/saml/module.php/saml/sp/saml2-acs.php/default-sp`

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://login.athenasystems.com/`
7. Athena Systems Login Platform アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Athena Systems Login Platform アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 顧客 | user.companyname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Athena Systems Login Platform のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Athena Systems Login Platform SSO を構成する

**Athena Systems Login Platform** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Athena Systems Login Platform サポート チーム](mailto:support@athenasystems.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Athena Systems Login Platform のテストユーザーの作成

このセクションでは、Athena Systems Login Platform で Britta Simon というユーザーを作成します。 [Athena Systems Login Platform サポートチーム](mailto:support@athenasystems.com)と協力して、アテナ Systems ログインプラットフォームプラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる、Athena Systems Login Platform のサインオン URL にリダイレクトされます。
- Athena Systems Login Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Athena Systems Login Platform に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [アテナ システム ログイン プラットフォーム] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Athena Systems Login Platform に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->
