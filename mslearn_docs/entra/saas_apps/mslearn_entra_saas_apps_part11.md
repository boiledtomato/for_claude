# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 11)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 69

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/harness-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Harness を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/harness-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-18
- Summary: Harness へユーザー アカウントを自動的にプロビジョニングおよび自動的にプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、Harness に対してユーザーまたはグループを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。 自動プロビジョニングでは、ID プロバイダーから Harness にユーザー ライフサイクルの変更を同期することで、手動でのユーザー管理が不要になります。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの詳細については、「[Microsoft Entra IDを使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件があることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Harness テナント](https://harness.io/pricing/)
- *管理者*アクセス許可がある Harness のユーザー アカウント

### ユーザーを Harness に割り当てる

プロビジョニングを構成する前に、Microsoft Entra IDの Harness アプリケーションにユーザーまたはグループを割り当てる必要があります。 Microsoft Entra IDでは**、割り当てを**使用して、選択したアプリケーションへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID でアプリケーションに割り当て済みのユーザーまたはグループのみが同期の対象になります。

自動ユーザー プロビジョニングを構成して有効にする前に、Microsoft Entra ID で Harness へのアクセスが必要なユーザーまたはグループを決定します。 その後、「[エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)」の手順に従って、これらのユーザーまたはグループを Harness に割り当てることができます。

#### ユーザー割り当ての推奨事項

組織全体にプロビジョニングをロールアウトする前に、小規模なテスト グループから始めます。 1 人のMicrosoft Entra ユーザーを Harness に割り当てて、自動ユーザー プロビジョニング構成をテストします。 プロビジョニングが正しく機能することを確認したら、追加のユーザーまたはグループを割り当てることができます。

Harness にユーザーを割り当てるときは、[ **割り当て** ] ダイアログ ボックスで有効なアプリケーション固有のロール (使用可能な場合) を選択する必要があります。 *既定のアクセス* ロールのユーザーは、プロビジョニングから除外されます。

現在、Microsoft Entra IDに Harness アプリ統合のセットアップがあり、Harness 用に設定しようとしている場合は、SSO を使用して Harness にログインする前に、ユーザー情報もアプリ統合に含まれていることを確認してください。

### プロビジョニングのための Harness の設定

Microsoft Entra IDでプロビジョニングを構成するには、Harness で SCIM API トークンを生成する必要があります。 このトークンにより、Microsoft Entra IDは Harness SCIM エンドポイントに安全に接続し、ユーザーをプロビジョニングできます。

1. [Harness 管理コンソール](https://app.harness.io/auth/#/signin)にサインインし、ページの左下隅にあるプロファイルを選択して、[**プロファイルの概要]** に移動します。

    [Image: [プロファイルの概要] を開くために使用したプロファイル メニューを含む Harness 管理コンソールのスクリーンショット。]
2. [ **マイ API キー**] で 、[ **+API キー**] を選択します。 API キーを作成するウィンドウが開きます。

    [Image: [マイ API キー] の下の [+API キー] ボタンを示す [Harness Profile Overview](ハーネス プロファイルの概要) ページのスクリーンショット。]
3. **[名前]** を指定し、[**保存]** を選択します。 Harness によって、アカウントの API キーが作成されます。

    [Image: [名前] ボックスと [保存] ボタンが表示された [Harness new API key](Harness の新しい API キー) ダイアログのスクリーンショット。]
4. API キーのトークンを作成するには、新しく作成した API キーの下にある **[+Token** ] を選択します。

    a. 名前を指定し、[ **トークンの生成**] を選択します。

    b。 トークン値を安全な場所にコピーします。 Microsoft Entra IDで接続を構成するには、このトークンが必要です。

    c. **を選択して**を閉じます。

    [Image: [トークンの生成] ボタンと [閉じる] ボタンを示す [Harness token](ハーネス トークン) ダイアログのスクリーンショット。]

### ギャラリーからの Harness の追加

自動ユーザー プロビジョニングを構成する前に、Microsoft Entra アプリケーション ギャラリーから Harness アプリケーションを追加する必要があります。 これにより、Harness がマネージド SaaS アプリケーションとしてMicrosoft Entra テナントに登録されます。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: [すべてのアプリケーション] リンク]
3. 新しいアプリケーションを追加するには、ウィンドウの上部にある **[新しいアプリケーション]** ボタンを選びます。

    [Image: [新しいアプリケーション] ボタン]
4. 検索ボックスに「**Harness**」と入力し、結果一覧から **[Harness]** を選択してから、 **[追加]** ボタンを選択してアプリケーションを追加します。

    [Image: Harness が選択され、[追加] ボタンが選択されているMicrosoft Entra ギャラリーの検索結果のスクリーンショット。]

### Harness への自動ユーザー プロビジョニングを構成する

ギャラリーから Harness を追加し、SCIM トークンを生成したら、プロビジョニング接続を構成できます。 このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Harness でユーザーまたはグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Harness のシングル サインオンに関する記事の手順に従って、Harness の SAML ベースの [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/harness-tutorial)を有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

注

Harness の SCIM エンドポイントの詳細については、Harness の [API キー](https://developer.harness.io/docs/platform/automation/api/add-and-manage-api-keys/)の記事ご覧ください。

Microsoft Entra ID で Harness の自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **[Harness]** を選択します。

    [Image: アプリケーションの一覧の Harness のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** で、次の操作を行います。

    [Image: テナント URL + トークン]

    - **[テナント URL]** ボックスに、「**`https://app.harness.io/gateway/api/scim/account/<your_harness_account_ID>`**」と入力します。 Harness にログインすると、ブラウザーの URL から Harness アカウント ID を取得できます。
    - [ **シークレット トークン** ] ボックスに、「プロビジョニング用の Harness のセットアップ」セクションの手順 3 で保存した SCIM 認証トークンの値を入力します。
    - **[テスト接続]** を選択して、Microsoft Entra ID が Harness に接続できることを確認します。 接続できない場合は、使用中の Harness アカウントに "*管理者*" アクセス許可があることを確認してから、もう一度試します。

        [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Harness に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Harness のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Harness ユーザーの [属性マッピング] ウィンドウ]
12. **[マッピング]** で、**[Harness に Microsoft Entra グループを同期する]** を選択します。
13. [属性マッピング]セクションで、Microsoft Entra IDから Harness に同期されるグループ**属性**を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新操作で Harness のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Harness のグループの [属性マッピング] ウィンドウ]
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### デプロイを監視する

プロビジョニングを開始したら、プロビジョニング ログを監視して、ユーザーとグループが Microsoft Entra ID と Harness の間で正しく同期されることを確認します。

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/harness-tutorial"} -->
## Microsoft Entra ID で Harness for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/harness-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Harness との間でシングル サインオンを構成する方法について説明します。

この記事では、Harness と Microsoft Entra ID を統合する方法について説明します。 Harness を Microsoft Entra ID と統合すると、次のことが可能になります。

- Harness にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Harness に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Harness でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Harness では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Harness では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/harness-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Harness の追加

Microsoft Entra ID への Harness の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Harness を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Harness**」と入力します。
4. 結果パネルで **[Harness]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Harness 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Harness に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するには、Microsoft Entra ユーザーと Harness の関連ユーザー間にリンク関係を確立する必要があります。

Harness に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Harness SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Harness テストユーザーの作成** - Microsoft Entra の B.Simon にリンクされている、Harness 内の B.Simon というユーザーの対応者を作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;にアクセスし、**エンタープライズ アプリ**&gt;、**統合**&gt;、**シングルサインオン**を開きます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.harness.io/gateway/api/users/saml-login?accountId=<harness_account_id>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.harness.io/`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL は、「 **Harness SSO の構成」** セクションから取得します。これについては、この記事の後半で説明します。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Harness のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に Harness へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**ハーネス** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Harness SSO の構成

1. 別の Web ブラウザー ウィンドウで、Harness 企業サイトに管理者としてサインインします。
2. ページの右上にある [**継続的セキュリティ**] &gt; [**アクセス管理**&gt;**認証の設定]** を選択します。

    [Image: [Continuous Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/継続的なセキュリティ) メニューと [Access Management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス管理)、[Authentication Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証設定) が選択されたているスクリーンショット。]
3. [ **SSO プロバイダー** ] セクションで、[ **+ SSO プロバイダーの追加**&gt;**SAML**] を選択します。

    [Image: [+ Add SSO Providers](+ SSO プロバイダーの追加)、[SAML] が選択されている [SSO Providers](SSO プロバイダー) を示すスクリーンショット。]
4. **[SAML Provider](SAML プロバイダー)** ポップアップで、次の手順を実行します。

    [Image: [URL] と [Display Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/表示名) のフィールドが強調表示され、[Choose File](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ファイルの選択) と [Submit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/送信) ボタンが選択されている [SAML Provider](SAML プロバイダー) というポップアップを示すスクリーンショット。]

    a. **[In your SSO Provider, please enable SAML-based login, then enter the following URL](SSO プロバイダーで SAML ベースのログインを有効にした後で次の URL を入力してください)** インスタンスをコピーし、**[基本的な SAML 構成]** セクションの [応答 URL] ボックスに貼り付けます。

    b。 **[Display Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/表示名)** ボックスに表示名を入力します。

    c. [ **ファイルの選択] を選択** して、Microsoft Entra ID からダウンロードしたフェデレーション メタデータ XML ファイルをアップロードします。

    d. **[送信]**を選択します。

#### Harness のテスト ユーザーの作成

Microsoft Entra ユーザーが Harness にサインインできるようにするには、そのユーザーを Harness にプロビジョニングする必要があります。 Harness では、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. 管理者として Harness にサインインします。
2. ページの右上にある [ **継続的セキュリティ**&gt;**アクセス管理**&gt;**ユーザー**] を選択します。

    [Image: [Continuous Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/継続的なセキュリティ) メニューと [Access Management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス管理)、[ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) が選択されたているスクリーンショット。]
3. ページの右側にある [ **+ ユーザーの追加]** を選択します。

    [Image: [+ Add User](+ ユーザーの追加) が選択されている [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) ページを示すスクリーンショット。]
4. **[Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加)** ポップアップで、次の手順を実行します。

    [Image: Harness の構成]

    a. **[Email Address(es)](メール アドレス)** ボックスに、ユーザーのメール アドレスを入力します (例: `B.simon@contoso.com`)。

    b。 対象の**ユーザー グループ**を選択します。

    c. **送信**を選択します。

Harness では、自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/harness-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Harness のサインオン URL にリダイレクトされます。
- Harness のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Harness に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Harness] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Harness に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hashicorp-cloud-platform-hcp-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に HashiCorp Cloud Platform (HCP) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hashicorp-cloud-platform-hcp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-04-30
- Summary: Microsoft Entra ID と HashiCorp Cloud Platform (HCP) の間のシングル サインオンを構成する方法について説明します。

この記事では、HashiCorp Cloud Platform (HCP) と Microsoft Entra ID を統合する方法について説明します。 HashiCorp Cloud Platform は、HashiCorp によって作成された開発者ツール (Terraform、Vault、Boundary、Consul など) のマネージド サービスをホストします。 HashiCorp Cloud Platform (HCP) と Microsoft Entra ID を統合すると、次のことができます。

- HashiCorp Cloud Platform (HCP) にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って HashiCorp Cloud Platform (HCP) に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で HashiCorp Cloud Platform (HCP) 用の Microsoft Entra シングル サインオンを構成してテストします。 HashiCorp Cloud Platform (HCP) では、 **SP** によって開始されるシングル サインオンのみがサポートされます。

### [前提条件]

Microsoft Entra ID と HashiCorp Cloud Platform (HCP) を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- HashiCorp Cloud Platform (HCP) のシングル サインオン (SSO) が有効な組織。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを始める前に、Microsoft Entra ギャラリーから HashiCorp Cloud Platform (HCP) アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから HashiCorp Cloud Platform (HCP) を追加する

Microsoft Entra アプリケーション ギャラリーから HashiCorp Cloud Platform (HCP) を追加して、HashiCorp Cloud Platform (HCP) でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**HashiCorp Cloud Platform (HCP)**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `urn:hashicorp:HCP-SSO-<HCP_ORG_ID>-samlp`
    2. [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://auth.hashicorp.com/login/callback?connection=HCP-SSO-<ORG_ID>-samlp`
    3. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://portal.cloud.hashicorp.com/sign-in?conn-id=HCP-SSO-<HCP_ORG_ID>-samlp`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値は、HashiCorp Cloud Platform (HCP) の Organization 設定内の [Setup SAML SSO] ページで事前生成することもできます。 詳細については、SAML ドキュメントが [HashiCorp の開発者向けサイト](https://developer.hashicorp.com/hcp/docs/hcp/iam/sso/setup/saml)で提供されています。 このプロセスに関する質問については、 [HashiCorp Cloud Platform (HCP) クライアント サポート チーム](mailto:support@hashicorp.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **HashiCorp Cloud Platform (HCP) のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### HashiCorp Cloud Platform (HCP) SSO の構成

**HashiCorp Cloud Platform (HCP)** 側でシングル サインオンを構成するには、ドメイン ホストに検証レコード TXT を追加し、Azure portal からコピーしたダウンロードした**証明書 (Base64)** と**ログイン URL を** HashiCorp Cloud Platform (HCP) 組織の [Setup SAML SSO] ページに追加する必要があります。 [HashiCorp の開発者向けサイト](https://developer.hashicorp.com/hcp/docs/hcp/iam/sso/setup/saml)で提供されている SAML ドキュメントを参照してください。 このプロセスに関する質問については、 [HashiCorp Cloud Platform (HCP) クライアント サポート チーム](mailto:support@hashicorp.com) にお問い合わせください。

### SSO のテスト

前の 「Microsoft Entra テスト ユーザーの作成と割り当て 」セクションでは、B.Simon というユーザーを作成し、Azure portal 内の HashiCorp Cloud Platform (HCP) アプリに割り当てしました。 これを、SSO 接続のテストに使用できます。 また、HashiCorp Cloud Platform (HCP) アプリに既に関連付けられている任意のアカウントを使用することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hashicorp-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に HashiCorp Boundary を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hashicorp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra と HashiCorp Boundary の間でシングル サインオンを構成する方法について説明します。

この記事では、HashiCorp Boundary と Microsoft Entra ID を統合する方法について説明します。 HashiCorp Boundary と Microsoft Entra ID を統合すると、次のことができます。

Microsoft Entra ID を使用して、HashiCorp Boundary にアクセスできるユーザーを制御します。 ユーザーが自分の Microsoft Entra アカウントを使用して HashiCorp Boundary に自動的にサインインできるようにします。 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- HashiCorp Boundary でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから HashiCorp Boundary を追加する

Microsoft Entra ID への HashiCorp Boundary の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に HashiCorp Boundary を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「HashiCorp Boundary**」と入力します。
4. 結果パネルで **[HashiCorp Boundary** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**HashiCorp Boundary**&gt;**シングルサインオン**にアクセスします。
3. 次のセクションで以下の手順を実行します。

    1. [ **アプリケーションに移動] を**選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID を**コピーし、後で HashiCorp 境界側の構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
    3. [ **エンドポイント** ] タブで、 **OpenID Connect メタデータ ドキュメント** リンクをコピーし、後で HashiCorp 境界側の構成で使用します。

        [Image: タブにエンドポイントが表示されているスクリーンショット。]
4. 左側のメニューの [ **認証** ] タブに移動し、次の手順を実行します。

    1. [ **リダイレクト URI** ] ボックスに、HashiCorp Boundary 側からコピーした **コールバック URL** 値を貼り付けます。
    2. **Front-channel ログアウト URL** で、値を `<Hashicorp-Cluster-URL>:3000`として指定し、[**構成**] を選択します。

        [Image: リダイレクト値を示すスクリーンショット。]
5. 左側のメニューの **[証明書とシークレット** ] に移動し、次の手順を実行します。

    1. [ **クライアント シークレット** ] タブに移動し、[ **+新しいクライアント シークレット**] を選択します。
    2. テキストボックスに有効な **説明** を入力し、要件に従ってドロップダウンから **[有効期限** 日] を選択し、[ **追加**] を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、 **値** が生成されます。 値をコピーし、後で HashiCorp 境界側の構成で使用します。

        [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

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

このセクションでは、B.Simon に HashiCorp Boundary へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**HashiCorp Boundary** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### HashiCorp Boundary SSO の構成

OAuth/OIDC フェデレーションのセットアップを完了するための構成手順を次に示します。

1. HashiCorp 境界クラスターにサインインします。
2. **[認証方法**] に移動し、[**新規**] を選択し、[**OIDC**] を選択します。

    [Image: フェデレーションのセットアップを示すスクリーンショット。]
3. **[新しい認証方法**] タブで次の手順を実行します。

    [Image: 新しい認証方法で oidc のセットアップを示すスクリーンショット。]

    ある。 [ **名前** ] フィールドに、ID の名前を入力します。

    b。 [ **説明** ] フィールドに、有効な説明値を入力します。

    c. Entra ページからコピーした **[発行者**] フィールドに Open **ID Connect メタデータ ドキュメント**の値を貼り付け、コピーした値から`.well-known/openid-configuration`を除外します。
4. 次のスクリーンショットに示すフィールドに対して、次の手順を実行します。

    [Image: クライアント ID oidc のセットアップを示すスクリーンショット。]

    ある。 [ **クライアント ID** ] フィールドに、Entra ページからコピーした **アプリケーション ID** の値を貼り付けます。

    b。 [ **クライアント シークレット** ] フィールドに、Entra 側の **[証明書とシークレット** ] セクションからコピーした値を貼り付けます。

    c. [ **署名アルゴリズム** ] フィールドで、[ **rs256** に追加] を選択します。
5. 次のスクリーンショットに示すフィールドに対して、次の手順を実行します。

    [Image: URL プレフィックス oidc のセットアップを示すスクリーンショット。]

    ある。 **API URL プレフィックス**に、`<Hashicorp-Cluster-URL>`の値を入力します。

    b。 **[保存] を選択します**。

    c. **コールバック URL** 値をコピーします。この値は、保存ボタンを選択して後で Entra 側の構成で使用すると生成されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hawkeyebsb-tutorial"} -->
## Microsoft Entra ID で Hawkeye Platform for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hawkeyebsb-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hawkeye Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Hawkeye Platform を Microsoft Entra ID と統合する方法について説明します。 Hawkeye Platformは、顧客が銀行手数料を管理するのを助けるために、レッドブリッジ・デット&トレジャリー・アドバイザリによって開発されました。 Hawkeye Platform を Microsoft Entra ID と統合すると、次のことができます。

- Hawkeye Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Hawkeye Platform に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Hawkeye Platform 向けの Microsoft Entra のシングル サインオンを構成およびテストするします。 Hawkeye Platform では、**SP**開始のシングルサインオンと**IDP**開始のシングルサインオンの両方がサポートされます。

### [前提条件]

Microsoft Entra ID を Hawkeye Platform と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Hawkeye Platform のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Hawkeye Platform アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Hawkeye Platform を追加する

Microsoft Entra アプリケーション ギャラリーから Hawkeye Platform を追加して、Hawkeye Platform でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Hawkeye Platform]**&gt;**[シングル サインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://hawkeye.redbridgeanalytics.com/sso/saml/metadata/<uniqueSlugPerCustomer>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://hawkeye.redbridgeanalytics.com/sso/saml/acs/<uniqueSlugPerCustomer>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://hawkeye.redbridgeanalytics.com/sso/saml/login/<uniqueSlugPerCustomer>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Hawkeye Platform クライアント サポート チーム](mailto:casemanagement@redbridgedta.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Hawkeye Platform のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Hawkeye Platform SSO を構成する

**Hawkeye Platform** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Hawkeye Platform サポート チーム](mailto:casemanagement@redbridgedta.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Hawkeye Platform のテスト ユーザーを作成する

このセクションでは、Hawkeye Platform で Britta Simon というユーザーを作成します。 [Hawkeye Platform サポート チーム](mailto:casemanagement@redbridgedta.com)と協力して、Hawkeye Platform プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

1. [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Hawkeye Platform のサインオン URL にリダイレクトされます。
2. Hawkeye Platform のサインオン URL に直接移動して、そこからログイン フローを開始します。

##### IDP 起動しました。

1. [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Hawkeye Platform に自動的にサインインします。
2. Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Hawkeye Platform] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Hawkeye Platform に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/haystack-tutorial"} -->
## Microsoft Entra ID で Haystack for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/haystack-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-07-24
- Summary: Microsoft Entra ID と Haystack 間にシングル サインオンを構成する方法について学習します。

この記事では、Haystack と Microsoft Entra ID を統合する方法について説明します。 Haystack を Microsoft Entra ID と統合すると、次のことが可能になります。

- Haystack にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Haystack に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Haystack でのシングル サインオン (SSO) が有効な MyGeotab のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Haystack では、**SP および IDP** Initiated SSO がサポートされています。

### ギャラリーからの Haystack の追加

Haystack の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Haystack を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Haystack**」と入力します。
4. 結果パネルから **[Haystack]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Haystack 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Haystack に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Haystack の関連ユーザーとの間にリンク関係を確立する必要があります。

Haystack を使って Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Haystack SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Haystack テスト ユーザーの作成 - Haystack** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Haystack**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://<SUBDOMAIN>.haystack.so/api/saml/metadata` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<SUBDOMAIN>.haystack.so/api/saml/acs` のパターンを使用して URL を入力します
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<SUBDOMAIN>.haystack.so/login` という形式で URL を入力します。

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには、[Haystack サポート チーム](mailto:support@haystackteam.com)にお問い合わせください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書の [ダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra ID テスト ユーザーを作成する

このセクションでは、Microsoft Entra 管理センターで B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部で **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. "**表示名**" フィールドに「`B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **[作成]** を選択します。

#### Microsoft Entra ID テスト ユーザーを割り当てる

このセクションでは、B.Simon に Haystack へのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Haystack に移動します**。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Haystack SSO の構成

**Haystack** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Haystack サポート チーム](mailto:support@haystackteam.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Haystack テスト ユーザーを作成する

このセクションでは、Haystack で B.Simon というユーザーを作成します。 [Haystack サポート チーム](mailto:support@haystackteam.com)と協力して、Haystack プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Haystack のサインオン URL にリダイレクトします。
- Haystack のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Haystack に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Haystack] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Haystack に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hcaptcha-enterprise-tutorial"} -->
## Microsoft Entra ID を使用して hCaptcha Enterprise のシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hcaptcha-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と hCaptcha Enterprise との間でシングル サインオンを構成する方法について説明します。

この記事では、hCaptcha Enterprise と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID に hCaptcha Enterpris を統合すると、次の利点が得られます。

- どのユーザーが hCaptcha Enterprise にアクセスできるかを Microsoft Entra ID で制御できます。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して hCaptcha Enterprise に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

hCaptcha Enterprise は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- シングル サインオン (SSO) が有効な hCaptcha Enterprise サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- hCaptcha Enterprise は、**SP 起動型 SSO および IDP** 起動型 SSO をサポートします。
- hCaptcha Enterprise では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの hCaptcha Enterprise の追加

Microsoft Entra ID への hCaptcha Enterprise の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから hCaptcha Enterprise を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックス**に「hCaptcha Enterprise**」と入力します。
4. 結果パネルから **hCaptcha Enterprise** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### hCaptcha Enterprise 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、hCaptcha Enterprise に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと hCaptcha Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

hCaptcha Enterprise で Microsoft Entra の SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **hCaptcha Enterprise SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **hCaptcha Enterprise のテスト ユーザーの作成** - hCaptcha Enterprise で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**hCaptcha Enterprise**&gt;**シングル サインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://accounts.hcaptcha.com/org/<YOUR_SLUG>/saml/callback`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://accounts.hcaptcha.com/org/<YOUR_SLUG>/saml/callback`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://dashboard.hcaptcha.com/org/<YOUR_SLUG>/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、hCaptcha Enterprise クライアント サポート チーム](mailto:support@hcaptcha.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. hCaptcha Enterprise アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、hCaptcha Enterprise アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを下に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | groups | ユーザー.グループ |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

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

このセクションでは、B.Simon に hCaptcha Enterprise へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**hCaptcha Enterprise** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### hCaptcha Enterprise SSO の構成

**hCaptcha Enterprise** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[hCaptcha Enterprise サポート チーム](mailto:support@hcaptcha.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### hCaptcha Enterprise のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを hCaptcha Enterprise に作成します。 hCaptcha Enterprise では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 hCaptcha Enterprise にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる hCaptcha Enterprise のサインオン URL にリダイレクトされます。
- hCaptcha Enterprise のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した hCaptcha Enterprise に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで hCaptcha Enterprise タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した hCaptcha Enterprise に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hcl-bigfix-platform-tutorial"} -->
## Microsoft Entra ID を使用して HCL BigFix Platform for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hcl-bigfix-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HCL BigFix Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、HCL BigFix Platform と Microsoft Entra ID を統合する方法について説明します。 HCL BigFix Platform と Microsoft Entra ID を統合すると、次のことができます。

- HCL BigFix Platform にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して HCL BigFix Platform に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- HCL BigFix Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- HCL BigFix Platform では、 **SP** によって開始される SSO のみがサポートされます。

### ギャラリーからの HCL BigFix Platform の追加

Microsoft Entra ID への HCL BigFix Platform の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に HCL BigFix Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「HCL BigFix Platform**」と入力します。
4. 結果パネルから **HCL BigFix Platform** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HCL BigFix Platform の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、HCL BigFix Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと HCL BigFix Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

HCL BigFix Platform に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HCL BigFix Platform の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **HCL BigFix Platform のテスト ユーザーの作成** - Microsoft Entra ID のユーザーである B.Simon にリンクされた、HCL BigFix Platform での対応したユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**HCL BigFix Platform**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    エー。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<BigFix_WebUI_server_fqdn>/saml`

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<BigFix_WebUI_server_fqdn>/saml` |
    | `https://<BigFix_Web_Reports_server_fqdn>:8083/saml` |
    | `https://<BigFix_Root_server_fqdn>:52311/saml` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<BigFix_WebUI_server_fqdn>/saml`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [HCL BigFix Platform サポート チーム](https://support.hcltechsw.com/csm) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. [ **HCL BigFix Platform のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra ID テスト ユーザーの作成

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

#### Microsoft Entra ID テスト ユーザーを割り当てる

このセクションでは、B.Simon に HCL BigFix Platform へのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**HCL BigFix Platform** にアクセスします。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### HCL BigFix Platform SSO の構成

**HCL BigFix Platform** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、Microsoft Entra 管理センターからコピーした適切な URL を [HCL BigFix Platform サポート チーム](https://support.hcltechsw.com/csm)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。 詳細については、この [リンク](https://help.hcltechsw.com/bigfix/10.0/platform/Platform/Config/c_how_to_configure_bigfix_to_int.html)を参照してください。

#### HCL BigFix Platform テスト ユーザーの作成

このセクションでは、HCL BigFix Platform で B.Simon というユーザーを作成します。 [HCL BigFix Platform サポート チーム](https://support.hcltechsw.com/csm)と協力して、HCL BigFix Platform プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる HCL BigFix Platform のサインオン URL にリダイレクトします。
- HCL BigFix Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [HCL BigFix Platform] タイルを選択すると、このオプションは HCL BigFix Platform のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/header-citrix-netscaler-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Citrix ADC (ヘッダーベースの認証) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/header-citrix-netscaler-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: ヘッダーベースの認証を使用して Microsoft Entra ID と Citrix ADC の間でシングル サインオン (SSO) を構成する方法について説明します。

この記事では、Citrix ADC と Microsoft Entra ID を統合する方法について説明します。 Citrix ADC を Microsoft Entra ID と統合すると、次のことが可能になります。

- Citrix ADC へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Citrix ADC に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Citrix ADC でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。 この記事には、次のシナリオが含まれています。

- Citrix ADC の **SP Initiated** SSO
- Citrix ADCのジャストインタイムユーザープロビジョニング
- Citrix ADC のヘッダー ベースの認証
- [Citrix ADC の Kerberos ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/citrix-netscaler-tutorial#publish-the-web-server)

### ギャラリーからの Citrix ADC の追加

Citrix ADC を Microsoft Entra ID に統合するには、まずギャラリーからマネージド SaaS アプリの一覧に Citrix ADC を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に進みます。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「Citrix ADC**」と入力します。
4. 結果で **Citrix ADC** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Citrix ADC に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、Citrix ADC に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Citrix ADC の関連ユーザーとの間にリンク関係を確立する必要があります。

Citrix ADC に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. Microsoft Entra SSO を構成 する - ユーザーがこの機能を使用できるようにします。

    1. Microsoft Entra テスト ユーザーを作成する - B.Simon で Microsoft Entra SSO をテストします。
    2. Microsoft Entra テスト ユーザーを割り当てて、B.Simon が Microsoft Entra SSO を使用できるようにします。
2. Citrix ADC の SSO の構成 - アプリケーション側で SSO 設定を構成します。

    - Citrix ADC のテスト ユーザーの作成 - Citrix ADC で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. SSO のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Azure portal を使用して Microsoft Entra SSO を有効にするには、これらの手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Citrix ADC** アプリケーション統合ウィンドウの [**管理**] で、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ウィンドウで、[SAML] を選択 **します**。
4. [**SAML を使用した単一 Sign-On のセットアップ**] ウィンドウで、[**基本的な SAML 構成**] のペン**編集**アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP 開始** モードでアプリケーションを構成します。

    1. [ **識別子** ] テキスト ボックスに、次のパターンの URL を入力します。 `https://<Your FQDN>`
    2. [ **応答 URL** ] テキスト ボックスに、次のパターンの URL を入力します。 `https://<Your FQDN>/CitrixAuthService/AuthService.asmx`
6. **SP 開始**モードでアプリケーションを構成するには、[**追加の URL の設定**] を選択し、次の手順を実行します。

    - [ **サインオン URL** ] テキスト ボックスに、次のパターンの URL を入力します。 `https://<Your FQDN>/CitrixAuthService/AuthService.asmx`

    Note

    - このセクションで使用される URL は、実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL の値で更新してください。 これらの値を取得するには、 [Citrix ADC クライアント サポート チーム](https://www.citrix.com/contact/technical-support.html) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
    - SSO を設定するには、パブリック Web サイトから URL にアクセスできる必要があります。 Citrix ADC 側でファイアウォールまたは他のセキュリティ設定を有効にし、Microsoft Entra ID が構成済みの URL にトークンをポストできるようにする必要があります。
7. [ **SAML を使用した単一 Sign-On の設定** ] ウィンドウの [ **SAML 署名証明書** ] セクションの **[アプリのフェデレーション メタデータ URL**] で、URL をコピーしてメモ帳に保存します。

    [Image: 証明書のダウンロード リンク]
8. Citrix ADC アプリケーションは特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。 **[編集**] アイコンを選択し、属性マッピングを変更します。

    [Image: SAML 属性マッピングを編集する]
9. Citrix ADC アプリケーションでは、さらにいくつかの属性も SAML 応答で返されることが想定されています。 [ **ユーザー属性** ] ダイアログ ボックスの [ **ユーザー要求**] で、次の手順を実行して、表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | mySecretID | user.userprincipalname |

    1. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログ ボックスを開きます。
    2. [ **名前** ] テキスト ボックスに、その行に表示される属性名を入力します。
    3. **名前空間**は空白のままにします。
    4. **[属性]** で [ソース] を選択**します**。
    5. ソース **属性** の一覧で、その行に表示される属性値を入力します。
    6. [ **OK] を選択します**。
    7. **[保存] を選択します**。
10. [ **Citrix ADC のセットアップ** ] セクションで、要件に基づいて関連する URL をコピーします。

    [Image: 構成 URL のコピー]

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

このセクションでは、B. Simon に Citrix ADC へのアクセスを許可することで、このユーザーが Azure SSO を使用できるようにします。

1. **Entra ID**&gt;**エンタープライズ アプリケーション**にアクセスします。
2. アプリケーションの一覧で **Citrix ADC** を選択します。
3. アプリの概要で、[ **管理**] で [ **ユーザーとグループ**] を選択します。
4. [ **ユーザーの追加] を選択します**。 次に、[ **割り当ての追加** ] ダイアログ ボックスで、[ **ユーザーとグループ**] を選択します。
5. [**ユーザーとグループ**] ダイアログ ボックスで、[**ユーザー**] の一覧から **B.Simon** を選択します。 **[選択]** を選択します。
6. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
7. [ **割り当ての追加** ] ダイアログ ボックスで、[ **割り当て**] を選択します。

### Citrix ADC の SSO の構成

構成したい認証の種類に対応する手順のリンクを選択してください。

- ヘッダーベースの認証用に Citrix ADC SSO を構成する
- [Kerberos ベースの認証用に Citrix ADC SSO を構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/citrix-netscaler-tutorial#publish-the-web-server)

#### Web サーバーを公開する

仮想サーバーを作成するには:

1. [ **Traffic Management**&gt;**Load Balancing**&gt;**Services** を選択します。
2. **追加**を選択します。

    [Image: Citrix ADC の構成 - [サービス] ウィンドウ]
3. アプリケーションを実行している Web サーバーに対して、次の値を設定します。

    - **サービス名**
    - **サーバー IP/既存のサーバー**
    - **議定書**
    - **ポート**

        [Image: Citrix ADC の構成ウィンドウ]

#### ロード バランサーを構成します

ロード バランサーを構成するには:

1. **Traffic Management**&gt;**Load Balancing**&gt;**Virtual Servers** に移動します。
2. **追加**を選択します。
3. 下のスクリーンショットに示すように、次の値を設定します。

    - **名前**
    - **議定書**
    - **IPアドレス**
    - **ポート**
4. [ **OK] を選択します**。

    [Image: Citrix ADC の構成 - [基本設定] ウィンドウ]

#### 仮想サーバーをバインドする

ロード バランサーを仮想サーバーにバインドするには:

1. [ **サービスとサービス グループ** ] ウィンドウで、[ **負荷分散仮想サーバー サービス バインドなし**] を選択します。

    [Image: Citrix ADC の構成 - [負荷分散仮想サーバー サービス バインド] ウィンドウ]
2. 次のスクリーンショットに示すように設定を確認し、[ **閉じる**] を選択します。

    [Image: Citrix ADC の構成 - 仮想サーバー サービスのバインドを確認する]

#### 証明書をバインドする

このサービスを TLS として公開するには、サーバー証明書をバインドしてから自分のアプリケーションをテストします。

1. [ **証明書**] で、[ **サーバー証明書なし**] を選択します。

    [Image: Citrix ADC の構成 - [サーバー証明書] ウィンドウ]
2. 次のスクリーンショットに示すように設定を確認し、[ **閉じる**] を選択します。

    [Image: Citrix ADC の構成 - 証明書を確認する]

### Citrix ADC SAML プロファイル

Citrix ADC SAML プロファイルを構成するには、次のセクションを完了します。

#### 認証ポリシーを作成する

認証ポリシーを作成するには:

1. **[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)**&gt;**[AAA - Application Traffic](AAA - アプリケーション トラフィック)**&gt;**[Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ポリシー)**&gt;**[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)**&gt;**[Authentication Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証ポリシー)** の順に移動します。
2. **追加**を選択します。
3. [ **認証ポリシーの作成** ] ウィンドウで、次の値を入力または選択します。

    - **名前**: 認証ポリシーの名前を入力します。
    - **アクション**: **SAML** を入力し、[ **追加**] を選択します。
    - **式**: **true を入力します**。

    [Image: Citrix ADC の構成 - [認証ポリシーの作成] ウィンドウ]
4. **作成**を選択します。

#### 認証 SAML サーバーを作成する

認証 SAML サーバーを作成するには、[ **認証 SAML サーバーの作成** ] ウィンドウに移動し、次の手順を実行します。

1. **[名前]** に、認証 SAML サーバーの名前を入力します。
2. [ **SAML メタデータのエクスポート]** で、次の操作を行います。

    1. [ **メタデータのインポート** ] チェック ボックスをオンにします。
    2. 前に自分がコピーした、Azure SAML UI のフェデレーション メタデータ URL を入力します。
3. **[発行者名]** に、関連する URL を入力します。
4. **作成**を選択します。

[Image: Citrix ADC の構成 - [Create Authentication SAML Server](認証 SAML サーバーの作成) ウィンドウ]

#### 認証仮想サーバーを作成する

認証仮想サーバーを作成するには:

1. **[Security**&gt;**AAA - Application Traffic**&gt;**Policies**&gt;**Authentication**&gt;**Authentication Virtual Servers**] に移動します。
2. [ **追加]** を選択し、次の手順を実行します。

    1. [ **名前]** に、認証仮想サーバーの名前を入力します。
    2. [ **アドレス指定不可** ] チェック ボックスをオンにします。
    3. [ **プロトコル**] で 、[SSL] を選択 **します**。
    4. [ **OK] を選択します**。

    [Image: Citrix ADC の構成 - [認証仮想サーバー] ウィンドウ]

#### Microsoft Entra ID を使用するための認証仮想サーバーを構成する

認証仮想サーバーの 2 つのセクションを変更します。

1. [ **高度な認証ポリシー** ] ウィンドウで、[ **認証ポリシーなし**] を選択します。

    [Image: Citrix ADC の構成 - [高度な認証ポリシー] ウィンドウ]
2. [ **ポリシー バインド** ] ウィンドウで、認証ポリシーを選択し、[ **バインド**] を選択します。

    [Image: Citrix ADC の構成 - [ポリシー バインド] ウィンドウ]
3. **[フォーム ベースの仮想サーバー**] ウィンドウで、[**負荷分散仮想サーバーなし**] を選択します。

    [Image: Citrix ADC の構成 - [フォーム ベースの仮想サーバー] ウィンドウ]
4. **[認証 FQDN]** には、完全修飾ドメイン名 (FQDN) を入力します (必須)。
5. Microsoft Entra 認証によって保護する負荷分散仮想サーバーを選択します。
6. **バインド** を選択します。

    [Image: Citrix ADC の構成 - 負荷分散仮想サーバーのバインディング ウィンドウ]

    Note

    [**認証仮想サーバーの構成**] ウィンドウで必ず **[完了]** を選択してください。
7. 変更を確認するには、ブラウザーでアプリケーションの URL に移動します。 前に表示されていた非認証アクセスではなく、ご自分のテナントのサインイン ページが表示されます。

    [Image: Citrix ADC の構成 - Web ブラウザーのサインイン ページ]

### ヘッダーベースの認証用の Citrix ADC SSO の構成

#### Citrix ADC を構成する

ヘッダーベースの認証用に Citrix ADC を構成するには、次のセクションを完了します。

##### 書き換えアクションを作成する

1. **AppExpert**&gt;**Rewrite**&gt;**Rewrite Actions** に移動します。

    [Image: Citrix ADC の構成 - [操作の書き換え] ウィンドウ]
2. [ **追加]** を選択し、次の手順を実行します。

    1. **[名前]** に、書き換えアクションの名前を入力します。
    2. **[Type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/種類)** には、「**INSERT\_HTTP\_HEADER**」と入力します。
    3. **[ヘッダー名]** にヘッダー名を入力します (この例では *SecretID* を使用します)。
    4. **「式」** に **aaa.USER.ATTRIBUTE("mySecretID")** を入力します。ここで、**mySecretID** は Citrix ADC に送信された Microsoft Entra SAML クレームを指します。
    5. **作成**を選択します。

    [Image: Citrix ADC の構成 - [書き換えアクションの作成] ウィンドウ]

##### 書き換えポリシーを作成する

1. **[AppExpert]**&gt;**[Rewrite](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/書き換え)**&gt;**[Rewrite Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/書き換えポリシー)** の順に移動します。

    [Image: Citrix ADC の構成 - [ポリシーの書き換え] ウィンドウ]
2. [ **追加]** を選択し、次の手順を実行します。

    1. **[名前]** に、書き換えポリシーの名前を入力します。
    2. **[アクション]** で、前のセクションで作成した書き換えアクションを選択します。
    3. **[式]** に**「true」と入力します**。
    4. **作成**を選択します。

    [Image: Citrix ADC の構成 - [書き換えポリシーの作成] ウィンドウ]

#### 書き換えポリシーを仮想サーバーにバインドする

GUI を使用して書き換えポリシーを仮想サーバーにバインドするには:

1. **Traffic Management**&gt;**Load Balancing**&gt;**Virtual Servers** に移動します。
2. 仮想サーバーの一覧で、書き換えポリシーをバインドする仮想サーバーを選択し、[ **開く**] を選択します。
3. [ **負荷分散仮想サーバー** ] ウィンドウの [ **詳細設定]** で、[ポリシー] を選択 **します**。 自分の NetScaler インスタンス用に構成されているすべてのポリシーが、一覧に表示されます。 [Image: Citrix ADC の構成 - [仮想サーバーの負荷分散] ウィンドウ]
4. この仮想サーバーにバインドするポリシーの名前の横にあるチェック ボックスをオンにします。

    [Image: Citrix ADC の構成 - [Load Balancing Virtual Server Traffic Policy Binding](負荷分散仮想サーバー トラフィック ポリシーのバインド) ペイン]
5. [ **種類の選択** ] ダイアログ ボックスで、次の手順を実行します。

    1. [ **ポリシーの選択]** で、[トラフィック] を選択 **します**。
    2. **「種類の選択」** で、**「要求」** を選択します。

    [Image: Citrix ADC の構成 - [ポリシー] ダイアログ ボックス]
6. [ **OK] を選択します**。 ポリシーが正常に構成されたことを示すメッセージが、ステータス バーに表示されます。

#### 要求から属性を抽出するよう SAML サーバーを変更する

1. **[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)**&gt;**[AAA - Application Traffic](AAA - アプリケーション トラフィック)**&gt;**[Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ポリシー)**&gt;**[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)**&gt;**[Advanced Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/高度なポリシー)**&gt;**[Actions](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクション)**&gt;**[Servers](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サーバー)** の順に移動します。
2. アプリケーションに適した認証 SAML サーバーを選択します。

    [Image: Citrix ADC の構成 - [認証 SAML サーバーの構成] ウィンドウ]
3. [ **属性** ] ペインに、抽出する SAML 属性をコンマで区切って入力します。 この例では、`mySecretID` 属性を入力します。

    [Image: Citrix ADC の構成 - [属性] ウィンドウ]
4. アクセスを確認するには、ブラウザーの URL で、[Headers Collection]\( **ヘッダー コレクション**\) で SAML 属性を探します。

    [Image: Citrix ADC の構成 - URL のヘッダー コレクション]

#### Citrix ADC のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Citrix ADC に作成します。 Citrix ADC では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションには、ユーザー側で行うアクションはありません。 Citrix ADC にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

Note

ユーザーを手動で作成する必要がある場合は、 [Citrix ADC クライアント サポート チーム](https://www.citrix.com/contact/technical-support.html)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Citrix ADC のサインオン URL にリダイレクトされます。
- Citrix ADC のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Citrix ADC] タイルを選択すると、このオプションは Citrix ADC のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/headspace-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Headspace を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/headspace-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-11
- Summary: Microsoft Entra ID から Headspace にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Headspace と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーを [ヘッドスペース](https://www.headspace.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Headspace でユーザーを作成します。
- アクセスが不要になった場合は、Headspace のユーザーを削除します。
- Microsoft Entra ID と Headspace の間でユーザー属性の同期を維持します。
- [Headspace へのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/headspace-tutorial) (推奨)。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- Microsoft Entra テナント [の](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- ヘッドスペースを持つ管理者アカウント。

### 手順 1: プロビジョニングのデプロイを計画する

1. プロビジョニング サービスの [のしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)について説明します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra ID と Headspace [の間で、マッピングするデータ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Headspace を構成する

Microsoft Entra ID を使用したプロビジョニングをサポートするように Headspace を構成するには、Headspace サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Headspace を追加する

Microsoft Entra アプリケーション ギャラリーから Headspace を追加して、Headspace へのプロビジョニングの管理を開始します。 SSO 用に Headspace を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Headspace への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて TestApp でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Headspace の自動ユーザー プロビジョニングを構成するには:

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ヘッドスペース] 選択します。

    [Image: アプリケーションの一覧の [ヘッドスペース] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [プロビジョニング] タブの [Image: スクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: 自動プロビジョニング タブのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、ヘッドスペース テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Headspace に接続できることを確認します。 接続に失敗した場合は、Headspace アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **属性マッピングの** セクションで、Microsoft Entra ID から Headspace に同期されるユーザー属性を確認します。 **照合** プロパティとして選択されている属性は、更新操作でヘッドスペースのユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Headspace API でサポートされていることを確認する必要があります。 **[** 保存] ボタンを選択して、変更をコミットします。

    | 属性 | タイプ | フィルター処理でサポートされます | ヘッドスペースが要求する |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | > チェック |
    | アクティブ | ブール値 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | 名前.名 | 糸 |  |  |
    | 名前.姓 | 糸 |  |  |
    | 外部識別子 | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/headspace-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Headspace を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/headspace-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Headspace の間のシングル サインオンを構成する方法について説明します。

この記事では、Headspace と Microsoft Entra ID を統合する方法について説明します。 Headspace を Microsoft Entra ID と統合すると、次のことができます。

- Headspace にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Headspace に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Headspace のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Headspace では、**SP** initiated SSO がサポートされます。
- Headspace では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Headspace では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/headspace-provisioning-tutorial)。

### ギャラリーからの Headspace の追加

Microsoft Entra ID への Headspace の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Headspace を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Headspace**」と入力します。
4. 結果パネルから **[Headspace]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Headspace に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Headspace に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Headspace の関連ユーザー間にリンク関係を確立する必要があります。

Headspace に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Headspace の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Headspace のテスト ユーザーの作成** - Headspace で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Headspace**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`urn:auth0:<Auth0TenantName>:<CustomerConnectionName>` の形式で値を入力します。

    b。 **[応答 URL]** テキスト ボックスに、`https://auth.<Environment>.headspace.com/login/callback?connection=<CustomerConnectionName>` のパターンを使用して値を入力します。

    c. [ **サインオン URL** ] ボックスに、URL を入力します。 `https://headspace.com/sso-login`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Headspace クライアント サポート チーム](mailto:employer-solution-squad@headspace.com)にお問い合わせださい。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Headspace アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: Headspace アプリケーションの画像を示すスクリーンショット。]
7. その他に、Headspace アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | user.userprincipalname |
    | family\_name | ユーザーの名字 |
    | given\_name | User.givenname |
8. Headspace の要件を満たすためには、次の手順に従って、必要な属性と要求を正しく構成してください。

    1. 新しいページを開く必要がある属性と要求モーダルで鉛筆または **編集** を選択します。
    2. 次の図に一致するように要求を更新します。`email` の構成の手順 3 を参照してください。

    [Image: Headspace 属性の画像を示すスクリーンショット。]

    1. `email` 要求を管理するために開き、**[変換]** を [ソースの種類] として選択し、次のスクリーンショットに一致するように変換を構成します。

    [Image: Headspace 電子メール要求の画像を示すスクリーンショット。]
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Headspace のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

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

このセクションでは、B.Simon に Headspace へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Headspace** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Headspace SSO の構成

**Headspace** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** とアプリケーション構成からコピーした適切な URL を [Headspace サポート チーム](mailto:employer-solution-squad@headspace.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Headspace テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Headspace に作成します。 Headspace では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Headspace にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Headspace のサインオン URL にリダイレクトされます。
- Headspace のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ヘッドスペース] タイルを選択すると、このオプションは Headspace のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/health-support-system-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Health Support System を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/health-support-system-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Health Support System との間でシングル サインオンを構成する方法について説明します。

この記事では、Health Support System と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID に Health Support System を統合すると、次の利点が得られます。

- どのユーザーが Health Support System にアクセスできるかを Microsoft Entra ID で制御できます。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して自動的に Health Support System にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Health Support System でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Health Support System では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Health Support System の追加

Microsoft Entra ID への Health Support System の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Health Support System を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Health Support System**」と入力します。
4. 結果のパネルから **[Health Support System]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Health Support System 向けに Microsoft Entra の SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Health Support System で Microsoft Entra の SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Health Support System の関連ユーザーとの間にリンク関係を確立する必要があります。

Health Support System で Microsoft Entra の SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Health Support System の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Health Support System のテストユーザーを作成** - Health Support System で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Health Support System]**&gt;**[シングルサインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://suntory.karakoko.jp`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

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

このセクションでは、B.Simon に Health Support System へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Health Support System]** に移動します。
3. アプリの概要ページで、 **[管理]** セクションを見つけて、 **[ユーザーとグループ]** を選択します。
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Health Support System SSO の構成

**Health Support System** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Health Support System サポート チーム](https://wellcoms.jp/inquiry/)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Health Support System テスト ユーザーの作成

このセクションでは、Health Support System で B.Simon というユーザーを作成します。 [Health Support System サポート チーム](https://wellcoms.jp/inquiry/)と連携して、Health Support System プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できるヘルス サポート システムのサインオン URL にリダイレクトされます。
- Health Support System のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [正常性サポート システム] タイルを選択すると、このオプションは正常性サポート システムのサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/helloid-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に HelloID を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/helloid-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-11
- Summary: ユーザー アカウントを HelloID に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために HelloID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [HelloID](https://www.helloid.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- HelloID でユーザーを作成する
- アクセスが不要になった場合に HelloID のユーザーを削除する
- Microsoft Entra ID と HelloID の間でユーザー属性の同期を維持する
- HelloID にグループとグループ メンバーシップをプロビジョニングする
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [HelloID テナント](https://www.helloid.com/)。
- 管理者アクセス許可がある HelloID のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と HelloID の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように HelloID を構成する

1. HelloID 管理者ダッシュボードにサインインします。

    [Image: HelloID 管理者サインイン]
2. ディレクトリ&gt; に移動**します**。

    [Image: ディレクトリ &gt; Microsoft Entra ID]
3. [ **新しいシークレット** ] ボタンを選択します。

    [Image: [新しいシークレット] ボタン]
4. **URL** フィールドと**シークレット** フィールドが自動的に設定されます。 URL とシークレットをコピーして保存します。 これらの値は、HelloID アプリケーションの [プロビジョニング] タブの [ **テナント URL** \* ] フィールドと [ **シークレット トークン** ] \* フィールドに入力されます。

    [Image: 生成された URL とシークレット]

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから HelloID を追加する

Microsoft Entra アプリケーション ギャラリーから HelloID を追加して、HelloID へのプロビジョニングの管理を開始します。 SSO のために HelloID を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: HelloID への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて HelloID 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で HelloID に対する自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で [ **HelloID**] を選択します。

    [Image: アプリケーションの一覧の HelloID リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、HelloID テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が HelloID に接続できることを確認します。 接続に失敗した場合は、HelloID アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から HelloID に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で HelloID のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、HelloID API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | externalId | 糸 |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から HelloID に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で HelloID のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

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
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/helper-helper-tutorial"} -->
## Microsoft Entra ID でのシングル サインオン用に Helper Helper を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/helper-helper-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Helper Helper の間のシングル サインオンを構成する方法について説明します。

この記事では、ヘルパー ヘルパーと Microsoft Entra ID を統合する方法について説明します。 Helper Helper を Microsoft Entra ID と統合すると、次のことができます。

- Helper Helper にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Helper Helper に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Helper Helper でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ヘルパー ヘルパーは、**SP および IDP による SSO** をサポートし、**ジャストインタイム** ユーザー プロビジョニングをサポートします。

### ギャラリーからの Helper Helper の追加

Microsoft Entra ID への Helper Helper の統合を構成するには、管理対象の SaaS アプリ一覧に Helper Helper をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Helper Helper**」と入力します。
4. 結果パネルから **[ヘルパー ヘルパー** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Helper Helper 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ヘルパー ヘルパーに対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Helper Helper の関連ユーザーとの間にリンク関係を確立する必要があります。

Helper Helper に対する Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Helper Helper SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Helper Helper のテスト ユーザーの作成** - Helper Helper で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Helper Helper** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル** があり、 **IDP** 開始モードで構成する場合は、次の手順を実行します。

    Note

    URL `https://sso.helperhelper.com/saml/<customer_id>` に移動し、サービス プロバイダーのメタデータ ファイルを入手します。 については`<customer_id>`にお問い合わせください。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    Note

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sso.helperhelper.com/saml/<customer_id>/login`

    Note

    サインオン URL の値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには、 [ヘルパー ヘルパー クライアント サポート チーム](mailto:info@helperhelper.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、メモ帳に保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **ヘルパーヘルパーのセットアップ** ] セクションで、ニーズに応じて適切な URL をコピーしてください。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B. Simon というテスト ユーザーを作成します。

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

このセクションでは、B. Simon に Helper Helper へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Helper Helper**に移動します。
3. アプリの概要ページで、[ **管理** ] セクションを見つけて、[ **ユーザーとグループ**] を選択します。
4. [**ユーザーの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] リストから **[B. Simon** ] を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Helper Helper SSO の構成

**ヘルパー ヘルパー**側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[ヘルパー ヘルパー サポート チーム](mailto:info@helperhelper.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Helper Helper のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Helper Helper に作成します。 Helper Helper では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Helper Helper にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できるヘルパー ヘルパー サインオン URL にリダイレクトされます。
- Helper Helper のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定したヘルパー ヘルパーに自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ヘルパー ヘルパー] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したヘルパー ヘルパーに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/helpscout-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Help Scout を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/helpscout-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Help Scout の間のシングル サインオンを構成する方法について説明します。

この記事では、Help Scout と Microsoft Entra ID を統合する方法について説明します。 Help Scout を Microsoft Entra ID と統合すると、次のことが可能になります。

- Help Scout にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Help Scout に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Help Scout でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Help Scout では、**SP と IDP** Initiated SSO がサポートされます。
- Help Scout では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Help Scout を追加する。

Microsoft Entra ID への Help Scout の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Help Scout を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Help Scout**」と入力します。
4. 結果ウィンドウで **[Help Scout]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Help Scout 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Help Scout に対する Microsoft Entra SSO を構成およびテストするします。 SSO を機能させるには、Microsoft Entra ユーザーと Help Scout の関連ユーザーとの間にリンク関係を確立する必要があります。

Help Scout に対して Microsoft Entra SSO を構成およびテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Help Scout SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Help Scout テスト ユーザーの作成** - Help Scout で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID] **&gt;** [エンタープライズ アプリ] **&gt;** [Help Scout] **&gt;** [シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを**IDP** イニシエートモードで構成する場合は、次の手順を実行します。

    a. **識別子**とは、Help Scout の**対象ユーザーの URI (サービス プロバイダーのエンティティ ID)** であり、先頭は `urn:` です

    b。 **応答 URL** とは、Help Scout の**ポスト バック URL (Assertion Consumer Service URL)** で、先頭は `https://` です

    注

    これらの URL の値は、単なる例です。 これらの値は、実際の応答 URL と識別子で更新する必要があります。 これらの値は、[認証] セクションの **[シングル サインオン** ] タブから取得します。これについては、この記事の後半で説明します。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://secure.helpscout.net/members/login/`
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Help Scout のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

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

このセクションでは、B.Simon に Help Scout へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリ**&gt;**Help Scout** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Help Scout の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Help Scout 企業サイトに管理者としてサインインします。
2. 上部のメニューから **[管理** ] を選択し、ドロップダウン メニューから [ **会社** ] を選択します。

    [Image: [Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社) が選択された [Manage](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) メニューのスクリーンショット。]
3. 左側のナビゲーション ウィンドウで **[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** を選択します。

    [Image: 選択された [Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) を示すスクリーンショット。]
4. [SAML 設定] セクションが表示されます。ここで次の手順に従います。

    [Image: 指定された情報を入力できる [Single Sign-On](シングル サインオン) タブを示すスクリーンショット。]

    a. **[Post-back URL (Assertion Consumer Service URL)](ポスト バック URL (Assertion Consumer Service URL))** の値をコピーし、**[基本的な SAML 構成]** セクションの **[応答 URL]** ボックスに貼り付けます。

    b。 **[Audience URI (Service Provider Entity ID)](対象ユーザー URI (サービス プロバイダー エンティティ ID))** の値をコピーし、**[基本的な SAML 構成]** セクションの **[識別子]** ボックスに貼り付けます。
5. **[SAML を有効にする]** をオンにして、次の手順を実行します。

    [Image: [Single Sign-On](シングル サインオン) タブのスクリーンショット。ここで、SAML を有効にしたり他の情報を追加したりします。]

    a. **[シングル サインオン URL]** テキストボックスに、**[ログイン URL]** の値を貼り付けます。

    b。 [ **証明書のアップロード]** を選択して、以前にダウンロードした **証明書 (Base64)** をアップロードします。

    c. `contoso.com` ボックスに、組織のメール ドメイン (例: ) を入力します。 複数のドメインを指定する場合は、コンマで区切ります。 [Help Scout ログイン ページ](https://secure.helpscout.net/members/login/)でその特定のドメインに入った Help Scout ユーザーまたは管理者が、資格情報で認証するために ID プロバイダーにルーティングされるたびに。

    d. 最後に、ユーザーがこの方法以外で Help Scout にログオンできないようにする場合は、**[Force SAML Sign-on](強制 SAML サインオン)** の設定を切り替えてオンにします。 Help Scout 資格情報でも引き続きサインインできるようにする場合は、この設定をオフのままにします。 これを有効にしても、アカウント所有者は、いつでも自身のアカウント パスワードで Help Scout にログインにします。

    e. **保存** を選択します。

#### Help Scout テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Help Scout に作成します。 Help Scout では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Help Scout にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Help Scout のサインオン URL にリダイレクトされます。
- Help Scout のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Help Scout に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Help Scout] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Help Scout に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/helpshift-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Helpshift を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/helpshift-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Helpshift の間のシングル サインオンを構成する方法について説明します。

この記事では、Helpshift と Microsoft Entra ID を統合する方法について説明します。 Helpshift と Microsoft Entra ID を統合すると、次のことができます。

- Helpshift にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Helpshift に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Helpshift でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Helpshift では、**SP および IDP による SSO** がサポートされます。

### ギャラリーからの Helpshift の追加

Microsoft Entra ID への Helpshift の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Helpshift を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Helpshift**」と入力します。
4. 結果パネルから **Helpshift** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Helpshift 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Helpshift に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Helpshift の関連ユーザーとの間にリンク関係を確立する必要があります。

Helpshift に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Helpshift SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Helpshift テスト ユーザーの作成** - Helpshift で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 上のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Helpshift**&gt;**シングルサインオン**をブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_DOMAIN>.helpshift.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_DOMAIN>.helpshift.com/login/saml/acs/`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_DOMAIN>.helpshift.com/login/saml/idp-login/`

    b。 [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_DOMAIN>.helpshift.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、リレー状態でこれらの値を更新します。 これらの値を取得するには、 [Helpshift クライアント サポート チーム](mailto:support@helpshift.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Helpshift のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

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

このセクションでは、B.Simon に Helpshift へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Helpshift** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Helpshift の SSO の構成

1. 別の Web ブラウザーで、Helpshift アプリケーションに管理者としてサインインします。
2. Helpshift **ダッシュボード** を開き、[ **設定] アイコン**を選択します。

    [Image: [Helpshift の設定] アイコンを示すスクリーンショット。]
3. [ **統合** ] タブを選択し、次の手順を実行します。

    [Image: 説明されている手順を実行できる [統合] タブを示すスクリーンショット。]

    ある。 **シングル サインオン (SAML – SSO)** を有効にします。

    b。 **Microsoft Entra ID** として **ID プロバイダー (IDP)** を選択します。

    c. **[SAML 2.0 エンドポイント URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    d. ダウンロードした **証明書 (Base64)** ファイルをメモ帳に開き、ファイルの内容をコピーして ('-BEGIN CERTIFICATE-'、'—-END CERTIFICATE--' 行を使用せずに)、 **X.509 証明書** テキスト ボックスに貼り付けます。

    え **[発行者 URL**] ボックスに、前にコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    f. [ **変更の適用]** を選択します。

#### Helpshift テスト ユーザーの作成

このセクションでは、Helpshift で B.Simon というユーザーを作成します。 [Helpshift クライアント サポート チーム](mailto:support@helpshift.com)と協力して、Helpshift プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Helpshift のサインオン URL にリダイレクトされます。
- Helpshift のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Helpshift に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Helpshift] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Helpshift に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/heroku-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Heroku を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/heroku-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Heroku の間のシングル サインオンを構成する方法について説明します。

この記事では、Heroku と Microsoft Entra ID を統合する方法について説明します。 Heroku を Microsoft Entra ID と統合すると、次のことが可能になります。

- Heroku にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Heroku に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Heroku でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Heroku では、**SP** Initiated SSO がサポートされます。
- Heroku では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Heroku を追加する

Microsoft Entra ID への Heroku の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Heroku を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Heroku**」と入力します。
4. 結果のパネルから **[Heroku]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Heroku に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Heroku に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Heroku の関連ユーザーとの間にリンク関係を確立する必要があります。

Heroku に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Heroku の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Heroku テストユーザーを作成する** - Heroku で B.Simon に対応するテストユーザーを作成し、それを Microsoft Entra におけるユーザー表示にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Heroku**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sso.heroku.com/saml/<company-name>/init`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sso.heroku.com/saml/<company-name>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新してください。 Heroku チームからこれらの値を取得します。これについては、この記事の以降のセクションで説明しています。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Heroku のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

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

このセクションでは、B.Simon に Heroku へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Heroku** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Heroku の SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として Heroku テナントにサインオンします。
2. **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** タブを選択します。
3. **[シングル サインオン] ページで**、[**メタデータのアップロード**] を選択します。
4. 先ほどダウンロードしたメタデータ ファイルをアップロードします。
5. セットアップが成功すると、管理者には確認のダイアログ ボックスと、エンド ユーザー用の SSO ログインの URL が表示されます。
6. **[Heroku Login URL](Heroku ログイン URL)** と **[Heroku Entity ID](Heroku エンティティ ID)** の値をコピーして Azure portal の **[基本的な SAML 構成]** セクションに戻り、それらの値を **[サインオン URL]** と **[識別子 (エンティティ ID)]** ボックスにそれぞれ貼り付けます。

    [Image: シングルサインオンの設定]
7. [**次へ**] を選択します。

#### Heroku テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Heroku に作成します。 Heroku では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Heroku にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Heroku のサインオン URL にリダイレクトされます。
- Heroku のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Heroku] タイルを選択すると、このオプションは Heroku のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/heybuddy-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に HeyBuddy を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/heybuddy-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HeyBuddy の間のシングル サインオンを構成する方法について説明します。

この記事では、HeyBuddy と Microsoft Entra ID を統合する方法について説明します。 HeyBuddy を Microsoft Entra ID と統合すると、次のことが可能になります。

- HeyBuddy にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで HeyBuddy に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- HeyBuddy でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- HeyBuddy では、**SP** によって開始される SSO がサポートされます
- HeyBuddy では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの HeyBuddy の追加

Microsoft Entra ID への HeyBuddy の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に HeyBuddy を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**HeyBuddy**」と入力します。
4. 結果のパネルから **HeyBuddy** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HeyBuddy に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、HeyBuddy に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するには、Microsoft Entra ユーザーと HeyBuddy の関連ユーザー間にリンク関係を確立する必要があります。

HeyBuddy で Microsoft Entra SSO を構成およびテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HeyBuddy の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **HeyBuddy テスト ユーザーの作成** - HeyBuddy で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra における B.Simon の表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID] **&gt;** [エンタープライズ アプリ] **&gt;** [HeyBuddy] **&gt;** [シングル サインオン]** を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.heybuddy.com/auth/<ENTITY ID>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 サインオン URL の `Entity ID` は、組織ごとに自動的に生成されます。 これらの値を取得するには、[HeyBuddy クライアント サポート チーム](mailto:support@heybuddy.com)に問い合わせてください。
6. HeyBuddy アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、EZOfficeInventory アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 役割 | user.assignedroles |
    |  |  |

    注

    アプリケーションのロールの構成および設定の方法については、こちらの[リンク](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)を参照してください。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

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

このセクションでは、B.Simon に HeyBuddy へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID] **&gt;** [エンタープライズ アプリ] **&gt;** [HeyBuddy] ** に移動します。
3. アプリの概要ページで、 **[管理]** セクションを見つけて、 **[ユーザーとグループ]** を選択します。
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. 上記で説明したようにロールを設定している場合は、**[ロールの選択]** ドロップダウンから選択できます。
7. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### HeyBuddy の SSO の構成

**HeyBuddy** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [HeyBuddy サポート チーム](mailto:support@heybuddy.com)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### HeyBuddy テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを HeyBuddy に作成します。 HeyBuddy では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 HeyBuddy にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[HeyBuddy のサポート チーム](mailto:support@heybuddy.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる HeyBuddy のサインオン URL にリダイレクトされます。
- HeyBuddy のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [HeyBuddy] タイルを選択すると、このオプションは HeyBuddy のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hibob-to-active-directory-user-provisioning-tutorial"} -->
## ハイブリッド ユーザー プロビジョニングをActive Directoryするように HiBob を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hibob-to-active-directory-user-provisioning-tutorial
- Service: entra-id / app-provisioning
- Article date: 2026-09-03
- Summary: HiBob でMicrosoft Active Directory (ハイブリッド) 統合を構成して、オンプレミスの Active Directoryでユーザーをプロビジョニングおよび更新する方法について説明します。

この記事では、HiBob (Bob) をオンプレミスの Active Directory へのハイブリッド ユーザー プロビジョニング用に構成する方法について説明します。

統合は、オンプレミスの Active DirectoryがMicrosoft Entra IDに接続されている組織を対象としています。 HiBob は従業員のライフサイクル データのソースとして機能しますが、Microsoft Entraは ID とアクセスを引き続き管理します。 この統合により、ユーザー ライフサイクル管理の自動化、手動管理の削減、人事データと ID データの同期を維持できます。

製品固有の詳細なガイダンスについては、[Bob Marketplace の Microsoft Active Directory (ハイブリッド) 統合](https://www.hibob.com/marketplace/adhybrid/overview)から **[ヘルプ ドキュメント]** リンクを選択します。

### 前提条件

開始する前に、次の内容があることを確認します。

- HiBobテナントと、Bob Marketplace 連携をインストールおよび設定する権限。
- ターゲット Active Directory環境に接続されているMicrosoft Entra テナント。
- [Microsoft Entra ID P1、Microsoft Entra ID P2、またはMicrosoft Entra ID ガバナンス ライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals#api-driven-provisioning)。 API 駆動型のプロビジョニングを通じて統合が提供するすべての ID に対して十分なライセンスが必要です。
- ターゲット Active Directory ドメイン用にインストールおよび構成されたMicrosoft Entra [プロビジョニング エージェント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install)。
- HiBob がユーザーを作成または更新する必要があるActive Directoryドメイン名と組織単位 (OU)。
- 次の API アクセス許可に同意を付与する[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)またはグローバル管理者ロールを持つMicrosoft Entra アカウント。
    - Application.ReadWrite.OwnedBy
    - SynchronizationData-User.Upload.OwnedBy
    - ProvisioningLog.Read.All
- 属性マッピングとプロビジョニング動作の検証に使用できるテスト従業員レコード。

次のセクションでは、Bob Marketplace で統合を構成するための大まかな手順について説明します。

Note

この記事の手順とインターフェイス ラベルでは、HiBob 管理ポータルでの構成エクスペリエンスについて説明します。 製品が更新されると、エクスペリエンスが変わる可能性があります。

### 統合のしくみ

HiBob 統合では[、Microsoft Entra API 主導のプロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts)利用して、ユーザーをActive Directoryにプロビジョニングします。 HiBob は、統合で構成されたマッピングを適用して一括 SCIM ペイロードを作成し、プロビジョニング ジョブの API エンドポイントにペイロードを送信します。 Microsoft Entraは、プロビジョニング ジョブのスコープと属性マッピングを使用してペイロードを処理し、Microsoft Entra プロビジョニング エージェントが変更をActive Directoryに書き込みます。

[Image: HiBob から Active Directory へのエンドツーエンドのフローを示すシーケンス図。]

1. HR 管理者は、従業員プロファイルを作成するか、HiBob で従業員データを更新します。
2. HiBob は、統合で構成された属性マッピングを適用し、従業員の変更を表す SCIM ペイロードを作成します。
3. HiBob は、MICROSOFT ENTRA API 駆動型プロビジョニング ジョブの API エンドポイントに SCIM ペイロードを送信します。
4. プロビジョニング ジョブは、従業員がスコープ内にあるかどうかを判断し、SCIM 属性を構成されたActive Directory属性にマップします。
5. プロビジョニング ジョブは、作成または更新操作をMicrosoft Entraプロビジョニング エージェントに送信します。
6. プロビジョニング エージェントは、構成されたActive Directoryドメインと OU でユーザー アカウントを作成または更新します。
7. Active Directoryは、結果をプロビジョニング エージェントに返します。
8. プロビジョニング エージェントは、操作の状態を Microsoft Entra プロビジョニング ジョブに報告します。ここで、管理者はプロビジョニング ログでそれを確認できます。
9. HiBob は、送信された要求の状態について、Microsoft Entraプロビジョニング ログに対してクエリを実行します。
10. Microsoft Entra はプロビジョニング状態を返し、HiBob はその情報を統合の同期レコードで参照できるようにします。

### コンフィギュレーションの手順

#### 手順 1 - 接続を確立する

この手順では、HiBob 管理者が統合を追加し、Microsoft テナントへのアクセスを承認し、ターゲット Active Directory環境を指定します。

1. HiBob に管理者としてサインインします。
2. Marketplace に移動 **します**。
3. **[ID とアクセス**] カテゴリを開くか、検索ボックスを使用して**ハイブリッドMicrosoft Active Directory**検索します。
4. 統合を開き、その概要と要件を確認します。
5. [ **接続**] を選択し、[ **接続の追加]** を選択します。
6. 接続のわかりやすい名前を入力します。
7. **[承認]** を選択します。
8. 特権ロール管理者またはグローバル管理者ロールを持つMicrosoft Entra アカウントでサインインします。
9. *HiBob ハイブリッド AD 統合*アプリは、次のアクセス許可を要求します。 要求されたアクセス許可を確認して付与します。
    - Application.ReadWrite.OwnedBy
    - SynchronizationData-User.Upload.OwnedBy
    - ProvisioningLog.Read.All
10. HiBob がMicrosoftテナント接続が確立されていることを確認するまで待ちます。
11. ターゲット Active Directoryドメイン名を入力します。
12. 統合によって管理されるユーザーを含める OU を入力します。
13. Microsoft Entra プロビジョニング エージェントがインストールされ、ターゲット Active Directory環境に接続されていることを確認します。
14. **次へ**を選択します。

承認プロセスは、HiBob をMicrosoft テナントに安全に接続します。 接続の確立には数分かかる場合があります。

#### 手順 2 - プロビジョニング スコープと属性マッピングを構成する

この手順では、管理者がスコープ内の従業員を定義し、HiBob フィールドをActive Directory属性にマップします。 HiBob はこれらのマッピングを使用して、従業員データを、Microsoft Entra API 駆動型プロビジョニング ジョブの API エンドポイントに送信する SCIM ペイロードに変換します。

1. プロビジョニング設定ページで、 **プロビジョニングするユーザーを**構成します。
2. HiBob 従業員フィールドからActive Directory属性への既定のマッピングを確認します。
3. マッピングごとに、HiBob ソース フィールドと対応するActive Directoryターゲット属性を確認します。
4. Active Directoryにフローする必要がある追加の従業員データのマッピングを追加します。

    Note

    [Entra ID Governance Lifecycle Workflows](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)を使用するには、`Start date` と `End date`/`Termination date` の情報を Active Directory に送信し、[Entra Connect Sync またはクラウド同期を通じて](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes) Microsoft Entra ID に送信します。
5. 組織で必要のない省略可能なマッピングを変更または削除します。
6. HiBob がActive Directory同期に必要として識別するマッピングを確認します。 必要なマッピングを削除することはできませんが、ソースまたはターゲットを構成できる場合があります。
7. **次へ**を選択します。

Caution

識別子またはその他の必要なマッピングを変更すると、ユーザーの照合と更新に影響する可能性があります。 広範なプロビジョニングを有効にする前に、限られたユーザー セットでマッピングの変更をテストします。

#### 手順 3 - 同期と承認規則を構成する

この手順では、HiBob が従業員のライフサイクルの変更を自動的にActive Directoryに送信する方法を管理者が決定します。

HiBob には、次の同期方法が用意されています。

- **自動同期** - 対象となる変更をActive Directoryに直接送信します。 このオプションは、組織が管理者の関与を最小限に抑えながら、より高速な処理を行う必要がある場合に使用します。
- **承認が必要な同期** - 選択した変更をActive Directoryに送信する前に、承認キューに配置します。 このオプションは、組織が機密性の高い ID の変更をより詳細に制御する必要があるときに使用します。

承認が必要な同期を構成するには:

1. 新しい従業員アカウントの作成時に承認が必要かどうかを選択します。
2. 部署や別の機密性の高いビジネス属性など、承認が必要な更新プログラムを持つ従業員フィールドを選択します。
3. 承認通知を受け取るユーザーまたはグループを構成します。
4. 設定を確認し、接続を保存します。

定期的な更新は自動化されたままですが、リスクの高い変更にはレビューが必要な場合があります。

#### 手順 4 - ユーザー プロビジョニングをテストする

接続を保存したら、より広い対象ユーザーに対して連携を有効にする前に、テスト用従業員アカウントを使ってプロビジョニング フローをテストしてください。

1. HiBob で、テスト従業員レコードを開きます。
2. 属性マッピングに含まれる従業員値を作成または更新します。
3. 承認が必要な同期をテストする場合は、承認が必要なフィールドを更新します。 たとえば、従業員の部署を変更し、有効日を指定します。
4. **Marketplace** に戻り、Microsoft Active Directory ハイブリッド統合を見つけます。
5. [ **管理**] を選択し、作成した接続を開きます。
6. 承認が必要な場合は、承認キューを開きます。
7. 従業員、トリガーの種類、変更されたフィールド、前の値、新しい値、有効日を確認します。
8. [**承認] を**選択して変更をActive Directoryに送信するか、[**拒否**] を選択して拒否します。

承認者は、変更を個別に処理することも、可能な場合は変更を一括で承認または拒否することもできます。

変更を承認した後、期待されるActive Directoryドメインと OU で、想定される属性値を使用してユーザーが作成または更新されたことを確認します。

#### 手順 5 - プロビジョニングの監視

接続管理ページには、構成とプロビジョニング アクティビティを確認するための中心的な場所が表示されます。

接続ページを使用して、次の手順を実行します。

- ウィザードで構成されているプロビジョニング設定と属性マッピングを確認します。
- 必要に応じ、手動同期をトリガーします。
- 承認キュー内の保留中の項目を確認します。
- 監査履歴を確認して、変更を承認または拒否したユーザーと実行されたアクションを特定します。
- 同期レコードを確認して、各操作が成功したか失敗したかを確認します。
- これらのオプションを使用できる場合は、レコードをフィルター処理またはエクスポートします。

プロビジョニング操作が失敗した場合は、影響を受ける従業員の同期レコードを確認します。 接続の承認、provisioning-agent の状態、ターゲット ドメインと OU、プロビジョニング スコープ、属性マッピングを確認します。

### Troubleshooting

#### HiBob、Microsoft Entra テナント、Active Directory間の接続の問題

次のアクションを実行します。

- 承認に使用するMicrosoft Entra アカウントに必要なアクセス許可があることを確認します。
- 承認を再試行し、同意プロンプトを完了します。
- Microsoft Entra テナントがターゲット ハイブリッド Active Directory環境に関連付けられていることを確認します。

#### ユーザーが作成または更新されない

HiBob 管理ポータルで、次の手順を実行します。

- Active Directoryドメインと OU の設定を確認します。
- 従業員が **[プロビジョニングするユーザー** ] スコープに含まれていることを確認します。
- 必要な属性マッピングとカスタム属性マッピングを確認します。
- エラー エントリの同期レコードを確認します。

HiBob 同期レコードでエラーの原因が明確に特定されない場合は、Microsoft Entraプロビジョニング ログを確認します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. **HiBob から Active Directory へのユーザー プロビジョニング** アプリを見つけて選択します。
4. Microsoft Entra プロビジョニング エージェントが実行され、接続されていることを確認します。
5. **プロビジョニング ログ** を選択します。
6. 失敗したプロビジョニング操作を見つけて、その状態情報とエラーの詳細を確認します。

#### カスタム Active Directory スキーマ属性がマッピング一覧に表示されない

たとえば、Active Directoryにカスタム属性があり、マッピング ドロップダウンに表示されない場合は、HiBob でマッピングを構成する前に、プロビジョニング アプリのターゲット属性スキーマにカスタム属性を追加します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. **HiBob から Active Directory へのユーザー プロビジョニング** アプリを見つけて選択します。
4. [ **プロビジョニング**] を選択し、[ **プロビジョニングの編集]** を選択します。
5. [ **マッピング]** を展開し、属性マッピングを選択します。
6. **Active Directory 属性リストの編集** を選択します。
7. カスタム Active Directory属性を一覧に追加し、変更を保存します。
8. HiBob 構成画面に戻り、カスタム属性がマッピング一覧に表示されることを確認します。

#### 変更が保留中のままである

HiBob 管理ポータルで、次の手順を実行します。

1. 統合の承認キューを開きます。
2. 変更されたフィールドが承認を要求するように構成されていることを確認します。
3. 要求を承認または拒否します。
4. 承認通知が目的のレビュー担当者に送信されることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/highgear-tutorial"} -->
## Microsoft Entra ID で HighGear for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/highgear-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HighGear の間でシングル サインオンを構成する方法について説明します。

この記事では、HighGear と Microsoft Entra ID を統合する方法について説明します。 HighGear と Microsoft Entra ID の統合には、次の利点があります。

- HighGear にアクセスする Microsoft Entra ID を制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して HighGear (Single Sign-On) に自動的にサインインできるようにすることができます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「[アプリケーション アクセスとは」を参照し、Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)でのシングル サインオンを参照してください。 Azure サブスクリプションをお持ちでない場合は、開始する前に無料アカウント [を作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- エンタープライズライセンスまたは無制限ライセンスを持つ HighGear システム

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成してテストする方法について説明します。

- HighGear では、**SP と IdP** Initiated SSO がサポートされます

### ギャラリーからの HighGear の追加

Microsoft Entra ID への HighGear の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に HighGear を追加する必要があります。

**ギャラリーから HighGear を追加するには、次の手順を実行します。**

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**HighGear**」と入力します。
4. 結果パネルから**HighGear**を選択し、アプリを追加してください。 アプリがテナントに追加されるまで数秒待ちます。

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、Britta Simon **というテスト ユーザーに基づいて、HighGear システムで Microsoft Entra のシングル サインオン**構成し、テストする方法について説明します。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと HighGear システム内の関連ユーザーとの間にリンク関係が確立されている必要があります。

HighGear システムで Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. **Microsoft Entra シングル サインオン** の構成 - ユーザーがこの機能を使用できるようにします。
2. **HighGear シングル サインオンの構成** - HighGear アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **HighGear のテスト ユーザーの作成** - HighGear で Britta Simon に対応するユーザーを作成し、Microsoft Entra の Britta Simon にリンクさせます。
6. **シングル サインオン** テスト - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にする方法について説明します。

HighGear システムで Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**HighGear** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [**シングル サインオン方法** の選択] ダイアログで、SAML/WS-Fed **モード** 選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで **、[編集** ] アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成] の編集
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    [Image: スクリーンショットには、[基本的な SAML 構成] が示されています。ここで、[識別子]、[応答 URL] の順に入力し、[保存] を選択できます。]

    1. **[識別子**] テキスト ボックスに、HighGear システムの [単一 Sign-On 設定] ページにある **[サービス プロバイダー エンティティ ID**] フィールドの値を貼り付けます。

        [Image: サービスプロバイダーエンティティ ID フィールド]

        手記

        [Single Sign-On Settings]\(シングル Sign-On 設定\) ページにアクセスするには、HighGear システムにログインする必要があります。 ログインしたら、HighGear の [管理] タブの上にマウスを移動し、[単一 Sign-On 設定] メニュー項目を選択します。

        [Image: [Single Sign-On Settings](シングル サインオンの設定) メニュー項目]
    2. **[返信 URL]** テキスト ボックスに、お客様の HighGear システムの [Single Sign-On Settings]\(シングル サインオンの設定\) ページから **[Assertion Consumer Service (ACS) URL]** の値を貼り付けます。

        [Image: アサーション コンシューマー サービス (ACS) の URL フィールド]
    3. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

        [Image: このスクリーンショットは、[追加の U R L を設定します] を示しています。ここで、サインオン U R L を入力できます。]

        [ **サインオン URL** ] テキスト ボックスに、HighGear システムの [シングル Sign-On 設定] ページにある **[サービス プロバイダー エンティティ ID** ] フィールドの値を貼り付けます。 (このエンティティ ID は、SP によって開始されるサインオンに使用される HighGear システムのベース URL でもあります)。

        [Image: サービスプロバイダーエンティティ ID フィールド]

        手記

        これらの値は実際の値ではありません。 HighGear システムの **Single Sign-On Settings** ページの実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 サポートが必要な場合は、[HighGear サポート チーム](mailto:support@highgear.com)にお問い合わせください。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して **証明書 (Base64)** をダウンロードし、コンピューターに保存します。 これは、単一 Sign-On 構成の後の手順で必要になります。

    [Image: 証明書のダウンロード リンク]
7. [**HighGear** の設定] セクションで、次の URL の場所を確認します。

    [Image: 構成 URL のコピー]

    1. ログイン URL。 この値は、以下の「 **HighGear シングル サインオンの構成」** の手順 2 で必要です。
    2. Microsoft Entra 識別子。 この値は、以下の「 **HighGear シングル サインオンの構成」** の手順 3 で必要です。
    3. ログアウト URL。 この値は、以下の「 **HighGear シングル サインオンの構成」** の手順 4 で必要です。

#### HighGear Single Sign-On を設定する

HighGear for Single Sign-On を構成するには、HighGear システムにログインしてください。 ログインしたら、HighGear の [管理] タブの上にマウスを移動し、[単一 Sign-On 設定] メニュー項目を選択します。

[Image: [Single Sign-On Settings](シングル サインオンの設定) メニュー項目]

1. [ **ID プロバイダー名]** に、[ログイン] ページの HighGear の [単一 Sign-On] ボタンに表示される短い説明を入力します。 例: Microsoft Entra ID
2. HighGear の **[シングル Sign-On (SSO) URL**] フィールドに、Azure の [**HighGear の設定**] セクションにある **[ログイン URL**] フィールドの値を貼り付けます。
3. HighGear の **[ID プロバイダー エンティティ ID**] フィールドに、Azure の [**HighGear の設定**] セクションにある **Microsoft Entra Identifier** フィールドの値を貼り付けます。
4. HighGear の **[単一ログアウト (SLO) URL**] フィールドに、Azure の [**HighGear の設定**] セクションにある **[ログアウト URL**] フィールドの値を貼り付けます。
5. メモ帳を使用して、Azure の **SAML 署名証明書** セクションからダウンロードした証明書を開きます。 **証明書 (Base64)** 形式をダウンロードしている必要があります。 メモ帳から証明書の内容をコピーし、HighGear の **ID プロバイダー証明書** フィールドに貼り付けます。
6. HighGear サポート チーム  に電子メールで HighGear 証明書を要求します。 彼らから受け取った指示に従って、**HighGear Certificate** と **HighGear Certificate Password** フィールドを入力します。
7. **[保存**] ボタンを選択して HighGear Single Sign-On 構成を保存します。

#### Microsoft Entra テスト ユーザーの作成

このセクションの目的は、Britta Simon というテスト ユーザーを作成することです。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) に、少なくとも [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [新しいユーザー **作成]**&gt;**[新しいユーザー**の作成] を選択します。
4. **User**プロパティで、次の手順に従います。
    1. [**表示名** フィールドに、「`B.Simon`」と入力します。
    2. [**ユーザー プリンシパル名** フィールドに、username@companydomain.extensionを入力します。 たとえば、`B.Simon@contoso.com`します。
    3. [**パスワード** を表示する] チェック ボックスをオンにし、[**パスワード**] ボックスに表示される値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **[作成]** を選択します。

#### Microsoft Entra テスト ユーザーの割り当て

このセクションでは、Britta Simon に HighGear へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**HighGear** を参照してください。

    アプリケーションの一覧の [HighGear] リンクを
3. アプリの概要ページで、[ユーザーとグループ] 選択します。
4. **ユーザー/グループ**追加 を選択し、**割り当ての追加** ダイアログで **ユーザーとグループ** を選択します。

    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

#### HighGear テスト ユーザーの作成

Single Sign-On 構成をテストする HighGear テスト ユーザーを作成するには、HighGear システムにログインしてください。

1. [ **新しい連絡先の作成** ] ボタンを選択します。

    [Image: [新しい連絡先の作成] ボタン]

    メニューが表示され、作成する連絡先の種類を選択できます。
2. [ **個別** ] メニュー項目を選択して HighGear ユーザーを作成します。

    ウィンドウが右側にスライドして、新しいユーザーの情報を入力できるようにします。[Image: 新しい連絡先フォーム]
3. [**名** フィールドに、連絡先の名前を入力します。 例: Britta Simon
4. [ **その他のオプション]** メニューを選択し、[ **アカウント情報** ] メニュー項目を選択します。

    [Image: [アカウント情報] メニュー項目の選択]
5. **[ログイン可能]** フィールドを [はい] に設定します。

    [シングル サインオン **を有効にする**] フィールドも自動的に [はい] に設定されます。
6. [**Single Sign-On User Id** フィールドに、ユーザーの ID を入力します。 例: BrittaSimon@contoso.com

    [アカウント情報] セクションは次のようになります。[Image: 完成した [Account Info](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント情報) セクション] 。
7. 連絡先を保存するには、ウィンドウの下部にある **[保存** ] ボタンを選択します。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [HighGear] タイルを選択すると、SSO を設定した HighGear に自動的にサインインします。 アクセス パネルの詳細については、「[アクセス パネル](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)の概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/highground-tutorial"} -->
## Microsoft Entra ID で HighGround for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/highground-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HighGround の間のシングル サインオンを構成する方法について説明します。

この記事では、HighGround と Microsoft Entra ID を統合する方法について説明します。 HighGround を Microsoft Entra ID と統合すると、次のことが可能になります。

- HighGround にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで HighGround に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- HighGround でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- HighGround では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- HighGround を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスを拡張したものです。 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)でセッション制御を適用する方法について説明します。

### ギャラリーからの HighGround の追加

Microsoft Entra ID への HighGround の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に HighGround を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**HighGround**」と入力します。
4. 結果のパネルから **[HighGround]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HighGround 向けに Microsoft Entra シングル サインオンを構成およびテストする

**B.Simon** というテスト ユーザーを使用して、HighGround に対する Microsoft Entra SSO を構成およびテストするします。 SSO を機能させるには、Microsoft Entra ユーザーと HighGround の関連ユーザーの間にリンク関係を確立する必要があります。

HighGround との Microsoft Entra SSO を構成・テストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HighGround の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **HighGround テスト ユーザーの作成** - HighGround で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**HighGround**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.highground.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.highground.com/svc/SSONoAuth/SAML?groupid=<company-guid>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.highground.com/#/login/<company-slug>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、HighGround クライアント サポート チームに問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[HighGround のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に HighGround へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**HighGround**を参照します。
3. アプリの概要ページで、 **[管理]** セクションを見つけて、 **[ユーザーとグループ]** を選択します。

    [Image: [ユーザーとグループ] リンク]
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。

    [Image: [ユーザーの追加]リンク]
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### HighGround の SSO の構成

**HighGround** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を HighGround サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### HighGround のテスト ユーザーの作成

このセクションでは、HighGround で Britta Simon というユーザーを作成します。 HighGround サポート チームと協力して、HighGround プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [HighGround] タイルを選択すると、SSO を設定した HighGround に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/highq-tutorial"} -->
## Microsoft Entra ID で HighQ for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/highq-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HighQ の間のシングル サインオンを構成する方法について説明します。

この記事では、HighQ と Microsoft Entra ID を統合する方法について説明します。 Thomson Reuters HighQ は、シンプルで柔軟で拡張可能なソリューションであり、法律事務所や企業法務部門がクライアントや同僚と連携する方法を変革します。 HighQ を Microsoft Entra ID と統合すると、次のことが可能になります。

- HighQ にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで HighQ に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

HighQ に対する Microsoft Entra のシングル サインオンをテスト環境で構成してテストします。 HighQ では、**SP** Initiated シングル サインオンのみがサポートされます。

### [前提条件]

Microsoft Entra ID を HighQ と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- HighQ でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから HighQ アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから HighQ を追加する

Microsoft Entra アプリケーション ギャラリーから HighQ を追加して、HighQ でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[HighQ]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>/domain.extension/`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>/domain.extension/`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>/domain.extension/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[HighQ クライアント サポート チーム](mailto:highq-support@thomsonreuters.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. HighQ アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、HighQ ではこれをユーザーのメール アドレスにマップすることが求められます。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 属性の画像を示すスクリーンショット。]

    注

    残りの既定の属性は、要求セクションから手動で削除してください。
7. その他に、HighQ アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 郵便 | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### HighQ SSO の構成

**HighQ** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [HighQ サポート チーム](mailto:highq-support@thomsonreuters.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### HighQ テスト ユーザーの作成

このセクションでは、HighQ で Britta Simon というユーザーを作成します。 [HighQ サポート チーム](mailto:highq-support@thomsonreuters.com)と協力して、HighQ プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる HighQ サインオン URL にリダイレクトされます。
- HighQ のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [HighQ] タイルを選択すると、このオプションは HighQ サインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hightail-tutorial"} -->
## Microsoft Entra ID で Hightail for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hightail-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hightail の間のシングル サインオンを構成する方法について説明します。

この記事では、Hightail と Microsoft Entra ID を統合する方法について説明します。 Hightail を Microsoft Entra ID と統合すると、次のことができます。

- Hightail にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Hightail に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hightail でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Hightail では、**SP と IDP** によって開始される SSO がサポートされます。
- Hightail では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Hightail を追加する

Microsoft Entra ID への Hightail の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Hightail を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Hightail**」と入力します。
4. 結果ウィンドウで **[Hightail]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hightail に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Hightail に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Hightail の関連ユーザー間にリンク関係を確立する必要があります。

Hightail で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hightail SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Hightail のテスト ユーザーの作成** - Hightail で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. 以下の場所に移動してください: **Entra ID**&gt;**Enterprise apps**&gt;**Hightail**&gt;**シングルサインオン**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://api.spaces.hightail.com/api/v1/saml/consumer`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://api.spaces.hightail.com/api/v1/saml/consumer`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://spaces.hightail.com/corp-login`
7. Hightail アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Hightail アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの名字 |
    | Email | ユーザーのメールアドレス |
    | ユーザーアイデンティティ | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Hightail のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

注

Hightail アプリでシングル サインオンを構成する前に、電子メール ドメインを Hightail チームの許可リストに追加し、そのドメインを使用するすべてのユーザーがシングル サインオン機能を利用できるようにします。

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

このセクションでは、B.Simon に Hightail へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Hightail** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Hightail SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として Hightail 企業サイトにサインインします。
2. ページの右上隅にある **[ユーザー] アイコン** を選択します。

    [Image: ユーザー アイコンを示すスクリーンショット。]
3. [ **管理コンソールの表示** ] タブを選択します。

    [Image: [User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) の [View Admin Console](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理コンソールを表示) ボタンを示すスクリーンショット。]
4. 上部のメニューで 、[ **SAML** ] タブを選択し、次の手順を実行します。

    [Image: [Login U R L](ログイン U R L) および [SAML Certificate](SAML 証明書) を入力できる [SAML] タブを示すスクリーンショット。]

    a. **[ログイン URL]** テキスト ボックスに、Azure portal からコピーした**ログイン URL** の値を貼り付けます。

    b。 Azure portal からダウンロードした Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーしてから、それを **[SAML Certificate](SAML 証明書)** ボックスに貼り付けます。

    c. **COPY** を選択してインスタンスの SAML コンシューマー URL をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。

    d. [ **構成の保存] を選択します**。

#### Hightail テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Hightail に作成します。 Hightail では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Hightail にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Hightail サインオン URL にリダイレクトされます。
- Hightail のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Hightail に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Hightail] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Hightail に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hirebridge-ats-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Hirebridge ATS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hirebridge-ats-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hirebridge ATS との間でシングル サインオンを構成する方法について説明します。

この記事では、Hirebridge ATS と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID に Hirebridge ATS を統合すると、次の利点が得られます。

- どのユーザーが Hirebridge ATS にアクセスできるかを Microsoft Entra ID で制御できます。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して Hirebridge ATS に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hirebridge ATS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Hirebridge ATS では、**IDP** Initiated SSO がサポートされます

### ギャラリーからの Hirebridge ATS の追加

Microsoft Entra ID への Hirebridge ATS の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Hirebridge ATS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Hirebridge ATS**」と入力します。
4. 結果のパネルから **[Hirebridge ATS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hirebridge ATS 向けに Microsoft Entra SSO を構成およびテストするする

**B.Simon** というテスト ユーザーを使用して、Hirebridge ATS で Microsoft Entra の SSO を構成およびテストするします。 SSO が機能するには、Microsoft Entra ユーザーと Hirebridge ATS の関連ユーザーとの間にリンク関係を確立する必要があります。

Hirebridge ATS で Microsoft Entra の SSO を構成およびテストするするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hirebridge ATS の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Hirebridge ATS のテスト ユーザーの作成** - Hirebridge ATS で、Microsoft Entra のユーザー B.Simon のリンク相手とするユーザーを用意し、これらのユーザーの間にリンクを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Hirebridge ATS]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Hirebridge ATS のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

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

このセクションでは、B.Simon に Hirebridge ATS へのアクセスを許可することで、シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**Hirebridge ATS** を参照してください。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Hirebridge ATS の SSO の構成

**Hirebridge ATS** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [Hirebridge ATS サポート チーム](mailto:support@hirebridge.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Hirebridge ATS のテスト ユーザーの作成

このセクションでは、Hirebridge ATS で Britta Simon というユーザーを作成します。 [Hirebridge ATS サポート チーム](mailto:support@hirebridge.com)と連携して、Hirebridge ATS プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

1. [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Hirebridge ATS に自動的にサインインします
2. Microsoft アクセス パネルを使用することができます。 アクセス パネルで [Hirebridge ATS] タイルを選択すると、SSO を設定した Hirebridge ATS に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hiretual-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に hireEZ-SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hiretual-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と hireEZ-SSO の間にシングル サインオンを構成する方法について説明します。

この記事では、hireEZ-SSO と Microsoft Entra ID を統合する方法について説明します。 hireEZ-SSO と Microsoft Entra ID を統合すると、次のことができます。

- hireEZ-SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って hireEZ-SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- hireEZ-SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- hireEZ-SSO では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから hireEZ-SSO を追加する

Microsoft Entra ID への hireEZ-SSO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に hireEZ-SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**hireEZ-SSO**」と入力します。
4. 結果のパネルから **[hireEZ-SSO]** を選択して、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### hireEZ-SSO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、hireEZ-SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと hireEZ-SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

hireEZ-SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **hireEZ-SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **hireEZ-SSO テスト ユーザーの作成 - hireEZ-SSO** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**hireEZ-SSO**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://app.hireez.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.hireez.com/v1/users/saml/login/<teamId>`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 これらの値を取得するには、[hireEZ-SSO クライアント サポート チーム](mailto:support@hiretual.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. 左側のメニュー バーの [ **プロパティ** ] タブを選択し、[ **ユーザー アクセス URL**] の値をコピーして、コンピューターに保存します。

    [Image: ユーザー アクセス URL を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **hireEZ-SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

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

このセクションでは、B.Simon に hireEZ-SSO へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**hireEZ-SSO** にアクセスします。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### hireEZ-SSO の構成

1. hireEZ-SSO の企業サイトに管理者としてログインします。
2. **セキュリティとコンプライアンス**&gt;**シングルサインオン**に移動します。
3. **[SAML2.0 Authentication](SAML2.0 認証)** ページで、次の手順を実行します。

    [Image: SSO 構成を示すスクリーンショット。]

    1. **SAML2.0 SSO URL**テキストボックスに、Microsoft Entra 管理センターからコピーした**ログイン URL**を貼り付けます。
    2. [ **ID プロバイダー発行者** ] ボックスに、Microsoft Entra 管理センターからコピーした Microsoft **Entra 識別子** を貼り付けます。
    3. Microsoft Entra 管理センターからダウンロードした **フェデレーション メタデータ XML** ファイルからコンテンツをコピーし、[ **証明書** ] ボックスに貼り付けます。
    4. **[Single Sign-On Connection Status](シングル サインオン接続状態)** ボタンを有効にします。
    5. 最初にシングル サインオン統合をテストしてから、 **[Admin SP-Initiated Single Sign-On](管理 SP によって開始されるシングル サインオン)** ボタンを有効にします。

    注

    シングル Sign-On 構成でエラーが発生した場合、または Admin SP-Initiated シングル サインオンに接続した後に hireEZ-SSO Web App/Extension にログインできない場合は、 [hireEZ-SSO サポート チーム](mailto:support@hiretual.com)にお問い合わせください。

#### hireEZ-SSO テスト ユーザーの作成

このセクションでは、hireEZ-SSO で Britta Simon というユーザーを作成します。 [hireEZ-SSO サポート チーム](mailto:support@hiretual.com)と一緒に、hireEZ-SSO プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる hireEZ-SSO のサインオン URL にリダイレクトされます。
- hireEZ-SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した hireEZ-SSO に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで hireEZ-SSO タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した hireEZ-SSO に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hirevue-tutorial"} -->
## Microsoft Entra ID で HireVue for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hirevue-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HireVue 間のシングル サインオンを構成する方法について説明します。

この記事では、HireVue と Microsoft Entra ID を統合する方法について説明します。 HireVue を Microsoft Entra ID と統合すると、次のことが可能になります。

- HireVue にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで HireVue に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

HireVue は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- HireVue でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- HireVue では、**SP** Initiated SSO がサポートされます。

### ギャラリーから HireVue を追加する

Microsoft Entra ID への HireVue の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に HireVue を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**HireVue**」と入力します。
4. 結果のパネルから **HireVue** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HireVue に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、HireVue に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと HireVue の関連ユーザー間にリンク関係を確立する必要があります。

HireVue に対して Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HireVue SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **HireVue テスト ユーザーの作成** - Microsoft Entra のユーザー表現にリンクする B.Simon に対応するユーザーを HireVue に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[HireVue]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかの値を使用して URN を入力します。

    | 環境 | URN |
    | --- | --- |
    | 生産 | `urn:federation:hirevue.com:saml:sp:prod` |
    | ステージング | `urn:federation:hirevue.com:saml:sp:staging` |

    b。 **[サインオン URL]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://<COMPANY_NAME>.hirevue.com` |
    | ステージング | `https://<COMPANY_NAME>.stghv.com` |

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[HireVue クライアント サポート チーム](mailto:samlsupport@hirevue.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[HireVue のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

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

このセクションでは、B.Simon に HireVue へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**HireVue** を参照してください。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### HireVue SSO の構成

**HireVue** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [HireVue サポート チーム](mailto:samlsupport@hirevue.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### HireVue のテスト ユーザーを作成する

このセクションでは、HireVue で Britta Simon というユーザーを作成します。 [HireVue サポート チーム](mailto:samlsupport@hirevue.com)と連携して、HireVue プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる HireVue のサインオン URL にリダイレクトされます。
- HireVue のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [HireVue] タイルを選択すると、このオプションは HireVue のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hive-learning-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Hive Learning を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hive-learning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hive Learning の間のシングル サインオンを構成する方法について説明します。

この記事では、Hive Learning と Microsoft Entra ID を統合する方法について説明します。 Hive Learning を Microsoft Entra ID と統合すると、次のことが可能になります。

- Hive Learning にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して HiveLearning に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- HiveLearningでのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- HiveLearningでは、**SP** と**IDP** によって開始される SSO がサポートされます。
- HiveLearningでは、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから HiveLearning を追加する

Microsoft Entra ID への HiveLearning の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に HiveLearning を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに**HiveLearning**と入力します。
4. 結果のパネルから **[HiveLearning]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hive Learning 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、HiveLearning に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Hive Learning での関連ユーザーとの間にリンク関係を確立する必要があります。

HiveLearning に関する Microsoft Entra SSO を構成してテストするには、次のステップを実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HiveLearning の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Hive Learning のテスト ユーザーの作成** - Microsoft Entra におけるユーザーの表現とリンクされる、Hive Learning で B.Simon に対応するユーザーを持つため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Hive Learning**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://auth.hivelearning.com/saml/<ID>/metadata`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://auth.hivelearning.com/saml/<ID>/login`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ID>.hivelearning.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Hive Learning サポート チーム](mailto:help@hivelearning.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

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

このセクションでは、B.Simon に Hive Learning へのアクセスを許可することで、シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Hive Learning** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Hive Learning の SSO の構成

**Hive Learning** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Hive Learning サポート チーム](mailto:help@hivelearning.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Hive Learning のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Hive Learningに作成します。 HiveLearning では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定では有効になっています。 このセクションにはアクション項目はありません。 HiveLearning にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Hive Learning のサインオン URL にリダイレクトされます。
- Hive Learning のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Hive Learning に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Hive Learning] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Hive Learning に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hive-tutorial"} -->
## Microsoft Entra ID で Hive for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hive-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hive の間のシングル サインオンを構成する方法について説明します。

この記事では、Hive と Microsoft Entra ID を統合する方法について説明します。 Hive を Microsoft Entra ID と統合すると、次のことが可能になります。

- Hive にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Hive に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hive でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Hive では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Hive では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Hive の追加

Microsoft Entra ID への Hive の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Hive を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Hive**」と入力します。
4. 結果のパネルから **[Hive]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hive に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Hive に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Hive の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Hive と一緒に構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hive SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Hive のテスト ユーザーの作成** - Hive で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Hive]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://hive.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.hive.com/sso/saml/${workspaceId}`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.hive.com/sso/saml/${workspaceId}`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 `{workspaceId}`については、この記事の後半で説明します。 これらの値を取得するには、[Hive クライアント サポート チーム](https://help.hive.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Hive アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Hive アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Hive のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に Hive へのアクセスを許可することで、シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Hive]** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Hive SSO の構成

1. 別の Web ブラウザー ウィンドウで、Hive Web サイトに管理者としてサインインします。
2. **ユーザー プロファイル**を選択し、ワークスペース**の設定**を選択します。

    [Image: メニューで [Your workspace](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ワークスペース) が選択されている Hive Web サイトを示すスクリーンショット。]
3. **[エンタープライズ セキュリティ]** を選択し、次の手順を実行します。

    [Image: 説明されているタスクを実行する [Auth](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) ページを示すスクリーンショット。]

    a. **[ワークスペース ID]** をコピーし、**[基本的な SAML 構成] セクション**の **[サインオン URL]** および **[応答 URL]** に追加します。

    b。 **[SAML SSO URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    c. **[ID プロバイダー発行者]** テキストボックスに、コピーしておいた **Microsoft Entra 識別子**の値を入力します。

    d. Azure portal からダウンロードした**証明書 (Base64)** ファイルをメモ帳で開き、その内容をコピーして、 **[Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書)** テキストボックスに貼り付けて変更内容を保存します。

#### Hive テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Hive に作成します。 Hive では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Hive にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Hive サインオン URL にリダイレクトされます。
- Hive のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Hive に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Hive] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Hive に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/holmes-cloud-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に ContractS CLM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/holmes-cloud-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-11
- Summary: Microsoft Entra ID から ContractS CLM へのユーザー アカウントの自動プロビジョニングおよび自動プロビジョニング解除を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために ContractS CLM と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[ContractS CLM](https://www.holmescloud.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリケ―ションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- ContractS CLMでユーザーを作成する。
- アクセスが不要になった場合は、ContractS CLM のユーザーを削除します。
- Microsoft Entra ID と ContractS CLMとの間でユーザー属性の同期を維持する。
- ContractS CLM でグループとグループ メンバーシップをプロビジョニングする。
- ContractS CLM に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/holmes-tutorial)する (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [ContractS CLM](https://www.holmescloud.com/) テナント。
- 管理者アクセス許可がある ContractS CLM のユーザー アカウント。
- シングル サインオンとユーザー プロビジョニング サービスが有効になっている ContractS CLM サブスクリプション。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と ContractS CLM の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように ContractS CLM を構成する

注

- サブスクリプションを購入した後、 テナント URL を受け取ります。
- シングル サインオンとユーザー プロビジョニング サービスをサブスクライブしていれば、**[Company Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社設定)** ページで、プロビジョニング サービスを設定するために必要な情報 (エンドポイント URL、トークンなど) を確認できます。

1. ContractS CLM の資格情報を使用して ContractS CLM アカウントにログインします。
2. [会社設定] メニューを選択し、帽子の形をしたアイコンを選択します。
3. [アカウントプロビジョニング] というタイトルのカード メニューで、API トークンなどの情報を確認します。
4. トークンを再生成する場合は、[API キーを発行する] リンクを選択します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから ContractS CLM を追加する

Microsoft Entra アプリケーション ギャラリーから ContractS CLM を追加して、ContractS CLM へのプロビジョニングの管理を開始します。 ContractS CLM 用の SSO が既に設定されている場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5:ContractS CLM への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて ContractS CLM 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で ContractS CLM の自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で、 **[ContractS CLM]** を選択します。

    [Image: アプリケーションの一覧に表示された ContractS CLM リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、ContractS CLM テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が ContractS CLM に接続できることを確認します。 接続に失敗した場合は、ContractS CLM アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から ContractS CLM に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で ContractS CLM のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、ContractS CLM API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | externalId | 糸 |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から ContractS CLM に同期されるグループ属性を確認します。 **[Matching]\(照合\)** プロパティとして選択されている属性は、更新処理で ContractS CLM のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | members | 関連項目 |  |
    | externalId | 糸 |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/holmes-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ContractS CLM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/holmes-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ContractS CLM との間でシングル サインオンを構成する方法について説明します。

この記事では、ContractS CLM と Microsoft Entra ID を統合する方法について説明します。 ContractS CLM をMicrosoft Entra ID と統合すると、次のことができます。

- ContractS CLM にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ContractS CLM に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ContractS CLM でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

### ギャラリーから ContractS CLM を追加する

Microsoft Entra ID への ContractS CLM の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから ContractS CLM を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ContractS CLM**」と入力します。
4. 結果のパネルから **[ContractS CLM]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ContractS CLM に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ContractS CLM で Microsoft Entra の SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ContractS CLM の関連ユーザーとの間にリンク関係を確立する必要があります。

ContractS CLM で Microsoft Entra の SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ContractS CLM の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ContractS CLM のテスト ユーザーの作成** - ContractS CLM に、Microsoft Entra のユーザー表現にリンクされた B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ContractS CLM]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

        `https://<WorkspaceID>.holmescloud.com`
    2. **[応答 URL (Assertion Consumer Service URL)]** テキスト ボックスに、次の URL を入力します。

        `https://holmescloud.com/sso/acs`。
    3. **[ログアウト URL]** テキスト ボックスに、次の URL を入力します。

        `https://holmescloud.com/sso/logout`。

    注

    この値は、ContractS CLM の [管理者] ページを参照する実際の識別子に更新してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[ContractS CLM のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に ContractS CLM へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ContractS CLM** に移動します。
3. アプリの概要ページで、 **[管理]** セクションを見つけて、 **[ユーザーとグループ]** を選択します。
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
7. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### ContractS CLM の SSO を構成する

**ContractS CLM** 側でシングル サインオンを構成するには、ContractS CLM の [管理者] ページで、ダウンロードした**証明書 (Base64)** とコピーした適切な URL を登録する必要があります。

#### ContractS CLM のテスト ユーザーを作成する

このセクションでは、ContractS CLM で B.Simon という名前のユーザーを作成します。 ContractS CLM の [メンバー管理] ページでユーザーを作成または招待できます。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ContractS CLM サインオン URL にリダイレクトされます。
- ContractS CLM のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ContractS CLM に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ContractS CLM] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ContractS CLM に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hone-tutorial"} -->
## Microsoft Entra ID で Hone for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hone-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hone の間でシングル サインオンを構成する方法について説明します。

この記事では、Hone と Microsoft Entra ID を統合する方法について説明します。 Hone と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Hone へのアクセス権を管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Hone に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hone でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Hone では、**SP開始SSOとIDP開始SSO**の両方がサポートされます。

### ギャラリーから Hone を追加する

Microsoft Entra ID への Hone の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Hone を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Hone**」と入力します。
4. 結果パネルから **[Hone** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hone の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Hone に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Hone の関連ユーザーとの間にリンク関係を確立する必要があります。

Hone に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hone SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Hone テスト ユーザーの作成 - Hone** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Hone**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. [ **基本的な SAML 構成]** セクションでは、アプリは既に Microsoft Entra と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.honehq.com`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **Hone のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra ID テスト ユーザーの作成

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

#### Microsoft Entra ID テスト ユーザーを割り当てる

このセクションでは、B.Simon に Hone へのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Hone** を参照してください。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Hone SSO の構成

**Hone** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と Microsoft Entra 管理センターからコピーした適切な URL を [Hone サポート チーム](mailto:support@honehq.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Hone テスト ユーザーの作成

このセクションでは、Hone で B.Simon というユーザーを作成します。 [Hone サポート チーム](mailto:support@honehq.com)と協力して、Hone プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Hone のサインオン URL にリダイレクトします。
- Hone のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Hone に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Hone] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Hone に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/honestly-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Honestly を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/honestly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Honestly との間でシングル サインオンを構成する方法について説明します。

この記事では、Honestly と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID に Honestly を統合すると、次の利点が得られます。

- どのユーザーが Honestly にアクセスできるかを Microsoft Entra ID で制御できます。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して Honestly に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Honestly でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Honestly では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Honestly を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスを拡張したものです。 [Microsoft Defender for Cloud Apps でセッション制御を適用する方法について説明します](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)。

### ギャラリーからの Honestly の追加

Microsoft Entra ID への Honestly の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Honestly を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Honestly**」と入力します。
4. 結果のパネルから **[Honestly** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Honestly 用の Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、Honestly に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Honestly の関連ユーザーとの間にリンク関係を確立する必要があります。

Honestly で Microsoft Entra の SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Honestly SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Honestly のテストユーザーを作成** - Honestly における B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現とリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Honestly]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://webapp.honestly.de/saml2/<client-id>/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://webapp.honestly.de/saml2/<client-id>/acs`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Honestly クライアント サポート チーム](mailto:support@honestly.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://webapp.honestly.de/sso`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Honestly のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

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

このセクションでは、B.Simon に Honestly へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Honestly に**移動します。
3. アプリの概要ページで、[ **管理** ] セクションを見つけて、[ **ユーザーとグループ**] を選択します。

    [Image: [ユーザーとグループ] リンク]
4. [**ユーザーの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。

    [Image: [ユーザーの追加] リンク]
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Honestly の SSO の構成

**Honestly** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Honestly サポート チームに](mailto:support@honestly.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Honestly のテスト ユーザーの作成

このセクションでは、Honestly で Britta Simon というユーザーを作成します。 [Honestly サポート チーム](mailto:support@honestly.com)と協力して、Honestly プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Honestly] タイルを選択すると、SSO を設定した Honestly に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hootsuite-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Hootsuite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hootsuite-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-11
- Summary: ユーザー アカウントを Hootsuite に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Hootsuite ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Hootsuite](https://hootsuite.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリケ―ションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- Hootsuite でユーザーを作成する
- アクセスが不要になった場合に Hootsuite のユーザーを削除する
- Microsoft Entra ID と Hootsuite の間でユーザー属性の同期を維持する
- Hootsuite でグループとグループ メンバーシップをプロビジョニングする
- Hootsuite への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hootsuite-tutorial) (推奨)

Hootsuite は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- 組織に対する[メンバー管理](http://www.hootsuite.com/)のアクセス許可を持つ、**Hootsuite** のユーザー アカウント。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と G Hootsuite 間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Hootsuite を構成する

後の手順で必要な長期トークンについては、Hootsuite の CSM にアクセスしてください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Hootsuite を追加する

Microsoft Entra アプリケーション ギャラリーから Hootsuite を追加して、Hootsuite へのプロビジョニングの管理を開始します。 SSO のために Hootsuite を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Hootsuite への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて TestApp 内のユーザーまたはグループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Hootsuite に対する自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]

    [Image: [すべてのアプリケーション] ブレード]
3. アプリケーションの一覧で **[Hootsuite]** を選択します。

    [Image: アプリケーションの一覧の Hootsuite のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Hootsuite テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Hootsuite に接続できることを確認します。 接続に失敗した場合は、Hootsuite アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Hootsuite に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Hootsuite のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Hootsuite API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | emails[type eq "work"].value | 糸 |
    | 活動中 | ブール値 |
    | displayName | 糸 |
    | 優先言語 | 糸 |
    | タイムゾーン | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Hootsuite に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新操作のために Hootsuite のグループを照合するために使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | externalId | 糸 |
    | members | 関連項目 |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### ログの変更

- 2020 年 10 月 22 日 - ユーザー属性 "name.givenName" および "name.familyName" のサポートが追加されました。 Users のカスタム拡張属性 "organizationIds" と "teamIds" は削除されました。 グループ属性 "displayName"、"members"、および "externalId" のサポートが追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hootsuite-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Hootsuite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hootsuite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hootsuite の間のシングル サインオンを構成する方法について説明します。

この記事では、Hootsuite と Microsoft Entra ID を統合する方法について説明します。 Hootsuite を Microsoft Entra ID と統合すると、次のことができます。

- Hootsuite にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Hootsuite に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hootsuite でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Hootsuite では、**SP 始動型 SSO と IDP 始動型 SSO** がサポートされます。
- Hootsuite では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hootsuite-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Hootsuite の追加

Microsoft Entra ID への Hootsuite の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Hootsuite を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Hootsuite**」と入力します。
4. 結果パネルから **Hootsuite** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hootsuite に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Hootsuite に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Hootsuite の関連ユーザー間にリンク関係を確立する必要があります。

Hootsuite に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hootsuite の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Hootsuiteのテストユーザーを作成する - Hootsuite**において、B.Simonに対応するユーザーを作成し、Microsoft Entraのユーザー顔付けにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[Enterprise apps]**&gt;**[Hootsuite]**&gt;**[シングルサインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://hootsuite.com/login?method=sso`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Hootsuite のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

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
5. **作成** を選択します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に Hootsuite へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[Enterprise アプリ]**&gt;**[Hootsuite]** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Hootsuite の SSO の構成

**Hootsuite** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Hootsuite サポート チーム](https://hootsuite.com/about/contact-us#)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Hootsuite のテスト ユーザーの作成

このセクションでは、Hootsuite で Britta Simon というユーザーを作成します。 [Hootsuite サポート チーム](https://hootsuite.com/about/contact-us#)と協力して、Hootsuite プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Hootsuite では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hootsuite-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Hootsuite のサインオン URL にリダイレクトされます。
- Hootsuite のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Hootsuite に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Hootsuite] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Hootsuite に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hopsworks-ai-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Hopsworks.ai を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hopsworks-ai-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hopsworks.ai の間のシングル サインオンを構成する方法について説明します。

この記事では、Hopsworks.ai と Microsoft Entra ID を統合する方法について説明します。 Hopsworks.ai を Microsoft Entra ID と統合すると、次のことが可能になります。

- Hopsworks.ai にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Hopsworks.ai に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hopsworks.ai でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Hopsworks.ai では、**SP** Initiated SSO がサポートされます。
- Hopsworks.ai では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Hopsworks.ai の追加

Microsoft Entra ID への Hopsworks.ai の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Hopsworks.ai を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Hopsworks.ai**」と入力します。
4. 結果パネルから **[Hopsworks.ai]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hopsworks.ai 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Hopsworks.ai に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Hopsworks.ai の関連ユーザーとの間にリンク関係を確立する必要があります。

Hopsworks.ai に対する Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hopsworks.ai SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Hopsworks.ai のテストユーザーを作成する** - Hopsworks.ai で B.Simon に対応するユーザーを作成し、Microsoft Entra に表現されているユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Hopsworks.ai**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://managed.hopsworks.ai/sso-open/<ORGANIZATION>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `urn:amazon:cognito:sp:us-east-2_<ID>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Hopsworks.ai クライアント サポート チーム](mailto:support@logicalclocks.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

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

このセクションでは、B.Simon に Hopsworks.ai へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID] **&gt;** [エンタープライズ アプリ] **&gt;**[Hopsworks.ai]** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Hopsworks.ai SSO の構成

シングル サインオンを **Hopsworks.ai** 側で構成するには、 **[アプリのフェデレーション メタデータ URL]** を [Hopsworks.ai サポート チーム](mailto:support@logicalclocks.com)に送信する必要があります。彼らはこの設定を、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Hopsworks.ai テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Hopsworks.ai に作成します。 Hopsworks.ai では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Hopsworks.ai にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Hopsworks.ai サインオン URL にリダイレクトされます。
- Hopsworks.ai のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Hopsworks.ai] タイルを選択すると、このオプションは Hopsworks.ai サインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hornbill-tutorial"} -->
## Microsoft Entra ID で Hornbill for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hornbill-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hornbill の間のシングル サインオンを構成する方法について説明します。

この記事では、Hornbill と Microsoft Entra ID を統合する方法について説明します。 Hornbill を Microsoft Entra ID と統合すると、次のことが可能になります。

- Hornbill にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Hornbill に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Hornbill は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Hornbill シングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Hornbill では、**SP** Initiated SSO がサポートされます。
- Hornbill では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Hornbill の追加

Microsoft Entra ID への Hornbill の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Hornbill を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Hornbill**」と入力します。
4. 結果ウィンドウで **[Hornbill]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hornbill の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Hornbill に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Hornbill の関連ユーザーとの間にリンク関係を確立する必要があります。

Hornbill に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hornbill SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Hornbill テスト ユーザーの作成** - Hornbill で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Hornbill**&gt;**シングルサインオン**まで移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sso.hornbill.com/<INSTANCE_NAME>/live`

    注

    Hornbill Mobile Catalog を組織に展開する場合は、次のように追加の識別子 URL を追加する必要があります。 `https://sso.hornbill.com/hornbill/mcatalog`

    b。 **[応答 URL (Assertion Consumer Service URL)]** セクションに、次を追加します。`https://<API_SUBDOMAIN>.hornbill.com/<INSTANCE_NAME>/xmlmc/sso/saml2/authorize/user/live`

    注

    Hornbill Mobile Catalog を組織に展開する場合は、次のように応答 URL を追加する必要があります。 `https://<API_SUBDOMAIN>.hornbill.com/hornbill/xmlmc/sso/saml2/authorize/user/mcatalog`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://live.hornbill.com/<INSTANCE_NAME>/`

    注

    これらの値は実際の値ではありません。 識別子、応答 URL、サインオン URL の実際の値を使用して、&lt;INSTANCE\_NAME&gt; と &lt;API\_SUBDOMAIN&gt; の値を更新します。 これらの値は、Hornbill インスタンスの Hornbill Solution Center で、***[Your usage] &gt; [Support]*** から取得できます。 これらの値を取得する方法については、[Hornbill サポート](https://www.hornbill.com/support)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

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

このセクションでは、B.Simon に Hornbill へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Hornbill]** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Hornbill SSO の構成

1. 別の Web ブラウザー ウィンドウで、Hornbill にセキュリティ管理者としてログインします。
2. [ホーム] ページで、ページの左下にある **[構成** 設定] アイコンを選択します。

    [Image: Hornbill の [システム] を示すスクリーンショット。]
3. **[プラットフォーム構成]** に移動します。

    [Image: Hornbill のプラットフォーム構成を示すスクリーンショット。]
4. [セキュリティ] で **[SSO プロファイル** ] を選択します。

    [Image: Hornbill のシングル サインオンを示すスクリーンショット。]
5. ページの右側にある [ **+ 新しいプロファイルの作成**] を選択します。

    [Image: ロゴの追加を示すスクリーンショット。]
6. [ **プロファイルの詳細** ] バーで、[ **IDP メタデータのインポート** ] ボタンを選択します。

    [Image: Hornbill のメタ ロゴを示すスクリーンショット。]
7. ポップアップの **[URL]** テキスト ボックスに **[アプリのフェデレーション メタデータ URL]** を貼り付けます。 をクリックし、[ **プロセス**] を選択します。

    [Image: Hornbill の [処理] を示すスクリーンショット。]
8. プロセスを選択すると、[ **プロファイルの詳細** ] セクションの値が自動的に設定されます。

    [Image: Hornbill の [プロファイルの詳細] を示すスクリーンショット。]
9. [ **変更の保存] を選択します**。

#### Hornbill のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Hornbill に作成します。 Hornbill では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Hornbill にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Hornbill クライアント サポート チーム](https://www.hornbill.com/support/?request/)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Hornbill のサインオン URL にリダイレクトされます。
- Hornbill のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Hornbill] タイルを選択すると、Hornbill のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hosted-heritage-online-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Hosted Heritage Online SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hosted-heritage-online-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hosted Heritage Online SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、Hosted Heritage Online SSO と Microsoft Entra ID を統合する方法について説明します。 Hosted Heritage Online SSO を Microsoft Entra ID と統合すると、次のことができます。

- Hosted Heritage Online SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して、Hosted Heritage Online SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hosted Heritage Online SSO シングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Hosted Heritage Online SSO では、**SP**によって開始されるSSOがサポートされます。

### ギャラリーから Hosted Heritage Online SSO を追加する

Microsoft Entra ID への Hosted Heritage Online SSO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Hosted Heritage Online SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Hosted Heritage Online SSO**」と入力します。
4. 結果パネルから **[Hosted Heritage Online SSO** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hosted Heritage Online SSO に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Hosted Heritage Online SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Hosted Heritage Online SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Hosted Heritage Online SSO で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hosted Heritage Online の SSO SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Hosted Heritage Online SSO テストユーザーを作成** - Hosted Heritage Online SSO で B.Simon に対応するユーザーを作成し、Microsoft Entra における同じユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Hosted Heritage Online SSO]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.cirqahosting.com/shibboleth`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.cirqahosting.com/Shibboleth.sso/Login`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、Hosted Heritage Online SSO クライアント サポート チーム](mailto:support@isoxford.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

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

このセクションでは、B.Simon に Hosted Heritage Online SSO へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. 次の項目に移動します: **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Hosted Heritage Online SSO**。
3. アプリの概要ページで、[ **管理** ] セクションを見つけて、[ **ユーザーとグループ**] を選択します。
4. [**ユーザーの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Hosted Heritage Online SSO の SSO の構成

**Hosted Heritage Online SSO** 側でシングル サインオンを構成するには、**Hosted Heritage Online SSO サポート チーム**に[アプリのフェデレーション メタデータ URL を](mailto:support@isoxford.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Hosted Heritage Online SSO テスト ユーザーの作成

このセクションでは、Hosted Heritage Online SSO で B.Simon というユーザーを作成します。 [Hosted Heritage Online SSO サポート チーム](mailto:support@isoxford.com)と協力して、Hosted Heritage Online SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Hosted Heritage Online の SSO サインオン URL にリダイレクトされます。
- Hosted Heritage Online SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Hosted Heritage Online SSO] タイルを選択すると、このオプションは Hosted Heritage Online SSO のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hosted-mycirqa-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Hosted MyCirqa SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hosted-mycirqa-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hosted MyCirqa SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、Hosted MyCirqa SSO と Microsoft Entra ID を統合する方法について説明します。 Hosted MyCirqa と Microsoft Entra ID を統合すると、次のことができます:

- Hosted MyCirqa SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Hosted MyCirqa SSO に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hosted MyCirqa SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Hosted MyCirqa SSO では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの Hosted MyCirqa SSO の追加

Microsoft Entra ID への Hosted MyCirqa SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Hosted MyCirqa SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Hosted MyCirqa SSO**」と入力します。
4. 結果パネルから **Hosted MyCirqa SSO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hosted MyCirqa SSO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Hosted MyCirqa SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Hosted MyCirqa SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Hosted MyCirqa SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hosted MyCirqa SSO の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Hosted MyCirqa SSO テスト ユーザーの作成** - Hosted MyCirqa SSO で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterpriseアプリ**&gt;**Hosted MyCirqa SSO**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.cirqahosting.com/CirqaIdentity/external?`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://isoxford.com/<CUSTOMID>/cirqaidentity/saml2`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 この値を取得するには、Hosted MyCirqa SSO クライアント サポート チームにお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

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

このセクションでは、B.Simon に Hosted MyCirqa SSO へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Hosted MyCirqa SSO** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Hosted MyCirqa の SSO の構成

**Hosted MyCirqa SSO** 側でシングル サインオンを構成するには、**アプリケーション フェデレーション メタデータ URL** を [Hosted MyCirqa SSO サポート チーム](mailto:support@isoxford.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

### Hosted MyCirqa SSO のテスト ユーザーの作成

このセクションでは、Hosted MyCirqa SSO で Britta Simon というユーザーを作成します。 [Hosted MyCirqa SSO サポート チーム](mailto:support@isoxford.com)と連携して、Hosted MyCirqa SSO プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Hosted MyCirqa SSO サインオン URL にリダイレクトされます。
- Hosted MyCirqa SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Hosted MyCirqa SSO] タイルを選択すると、このオプションは Hosted MyCirqa SSO のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hostedgraphite-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Hosted Graphite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hostedgraphite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hosted Graphite の間のシングル サインオンを構成する方法について説明します。

この記事では、Hosted Graphite と Microsoft Entra ID を統合する方法について説明します。 Hosted Graphite を Microsoft Entra ID と統合すると、次のことが可能になります。

- Hosted Graphite にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Hosted Graphite に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hosted Graphite シングル サインオン (SSO) 対応のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Hosted Graphite では、**SP および IDP** Initiated SSO がサポートされています。
- Hosted Graphite では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Hosted Graphite の追加

Microsoft Entra ID への Hosted Graphite の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Hosted Graphite を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Hosted Graphite**」と入力します。
4. 結果パネルから **[Hosted Graphite]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hosted Graphite 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Hosted Graphite を使用した Microsoft Entra SSO を構成およびテストします。 SSO が機能するには、Microsoft Entra ユーザーとそれに対応する Hosted Graphite ユーザーをリンクする必要があります。

Hosted Graphite を使用した Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hosted Graphite SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Hosted Graphite のテスト ユーザーの作成** - Hosted Graphite で B.Simon に対応するユーザーを作成し、それを Microsoft Entra 内のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Hosted Graphite]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを**IDP** イニシエートモードで構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.hostedgraphite.com/metadata/<USER_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.hostedgraphite.com/complete/saml/<USER_ID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.hostedgraphite.com/login/saml/<USER_ID>/`

    注

    これらは実際の値ではありません。 実際の識別子、応答 URL、サインオン URL にこれらの値を置き換える必要があります。 これらの値を取得するには、アプリケーション側で [Access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス) -&gt; [SAML setup](SAML のセットアップ) と移動するか、[Hosted Graphite サポート チーム](mailto:help@hostedgraphite.com)に問い合わせてください。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Hosted Graphite のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

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

このセクションでは、B.Simon に Hosted Graphite へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Hosted Graphite** を参照します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Hosted Graphite SSO の構成

1. Hosted Graphite テナントに管理者としてサインオンします。
2. サイド バーの **SAML のセットアップ ページ**に移動します (**[Access (アクセス)] -&gt; [SAML Setup (SAML のセットアップ)]** の順に移動)。

    [Image: [SAML Setup](SAML のセットアップ) が選択された [Access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス) メニューのスクリーンショット。]
3. これらの URL が、**[基本的な SAML 構成]** セクションで行った構成と一致することを確認します。

    [Image: [基本的な SAML 構成] を示すスクリーンショット。]
4. **[エンティティまたは発行者 ID]** および **[SSO ログイン URL]** ボックスに、**Microsoft Entra ID**と**ログイン URL** の値を貼り付けます。

    [Image: ID プロバイダーに関する入力画面のスクリーンショット。]
5. **[Default User Role](既定のユーザー ロール)** として **[Read-only](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/読み取り専用)** を選択します。

    [Image: [Default User Role](既定のユーザー ロール) として [Read-only](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/読み取り専用) が表示された画面のスクリーンショット。]
6. Azure portal からダウンロードした Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、 **X.509 証明書** ボックスに貼り付けます。

    [Image: [X.509 証明書] を示すスクリーンショット。]
7. [ **保存] ボタンを** 選択します。

#### Hosted Graphite のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Hosted Graphite に作成します。 Hosted Graphite では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Hosted Graphite にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Hosted Graphite のサポート チーム](mailto:help@hostedgraphite.com)に問い合わせる必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Hosted Graphite のサインオン URL にリダイレクトされます。
- Hosted Graphite のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Hosted Graphite に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Hosted Graphite] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Hosted Graphite に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hownow-webapp-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に HowNow WebApp SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hownow-webapp-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HowNow WebApp SSO との間でシングル サインオンを構成する方法について説明します。

この記事では、HowNow WebApp SSO と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID に HowNow WebApp SSO を統合すると、次のことが可能になります。

- どのユーザーが HowNow WebApp SSO にアクセスできるかを Microsoft Entra ID で管理する。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して HowNow WebApp SSO に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- HowNow WebApp SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- HowNow WebApp SSO では、**SP** Initiated SSO がサポートされます
- HowNow WebApp SSO では、**Just-In-Time** ユーザー プロビジョニングがサポーされます

### ギャラリーからの HowNow WebApp SSO の追加

Microsoft Entra ID への HowNow WebApp SSO の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから HowNow WebApp SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**HowNow WebApp SSO**」と入力します。
4. 結果パネルから **HowNow WebApp SSO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HowNow WebApp SSO 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、HowNow WebApp SSO で Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと HowNow WebApp SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

HowNow WebApp の SSO で Microsoft Entra の SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HowNow WebApp SSO の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **HowNow WebApp SSO テストユーザーを作成 - Microsoft Entra のユーザー表現にリンクされた HowNow WebApp SSO 内の B.Simon に対応するユーザーを作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**HowNow WebApp SSO**&gt;**シングルサインオン**にブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.hownow.app/users/saml/sign_in`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.hownow.app/users/saml/metadata`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.hownow.app/users/saml/auth`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、応答 URL でこれらの値を更新します。 この値を取得するには、[HowNow WebApp SSO クライアント サポート チーム](mailto:support@gethownow.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. **[HowNow WebApp SSO のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

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

このセクションでは、B.Simon に HowNow WebApp SSO へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**HowNow WebApp SSO** にアクセスします。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### HowNow WebApp SSO の SSO の構成

**HowNow WebApp SSO** 側でシングル サインオンを構成するには、**サムプリントの値**と、アプリケーションの構成からコピーした適切な URL を [HowNow WebApp SSO サポート チーム](mailto:support@gethownow.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### HowNow WebApp SSO のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを HowNow WebApp SSO に作成します。 HowNow WebApp SSO では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 HowNow WebApp SSO にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる HowNow WebApp SSO サインオン URL にリダイレクトされます。
- HowNow WebApp SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft アクセス パネルを使用することができます。 アクセス パネルで [HowNow WebApp SSO] タイルを選択すると、このオプションは HowNow WebApp SSO のサインオン URL にリダイレクトされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/howspace-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して自動ユーザー プロビジョニング用に Howspace を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/howspace-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-09
- Summary: Microsoft Entra ID から Howspace へのユーザー アカウントの自動プロビジョニングおよび自動プロビジョニング解除を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Howspace ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Howspace](https://www.howspace.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Howspace でユーザーを作成します。
- アクセスが不要になった場合は、Howspace のユーザーを削除します。
- Microsoft Entra ID と Howspace の間でユーザー属性の同期を維持する。
- Hootsuite でグループとグループ メンバーシップをプロビジョニングします。
- Howspace に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- シングル サインオンと SCIM の機能が有効になっている Howspace サブスクリプション。
- メイン ユーザー ダッシュボード特権を持つ Howspace のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Howspace の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように Howspace を構成する

#### シングル サインオンの構成

1. Howspace メイン ユーザー ダッシュボードにサインインし、メニューから **[設定]** を選択します。
2. 設定の一覧で、[ **シングル サインオン**] を選択します。

    [Image: 設定一覧の [シングル サインオン] セクションのスクリーンショット。]
3. [ **SSO 構成の追加]** ボタンを選択します。

    [Image: [シングル サインオン] セクションの [SSO 構成の追加] メニューのスクリーンショット。]
4. 組織の Microsoft Entra トポロジに基づいて、 **Microsoft Entra ID (マルチテナント)** または Microsoft Entra **ID** のいずれかを選択します。

    [Image: Microsoft Entra ID (マルチテナント) ダイアログのスクリーンショット。][Image: Microsoft Entra ダイアログのスクリーンショット。]
5. Microsoft Entra テナント ID を入力し、[ **OK] を** 選択して構成を保存します。

#### プロビジョニングの構成

1. 設定の一覧で、 **クロスドメイン ID 管理の [システム**] を選択します。

    [Image: 設定一覧の [System for Cross-domain Identity Management] セクションのスクリーンショット。]
2. [ **ユーザー同期を有効にする]** チェック ボックスをオンにします。
3. Microsoft Entra ID で後で使用するために、テナント URL とシークレット トークンをコピーします。
4. [ **保存] を** 選択して構成を保存します。

#### メイン ユーザー ダッシュボードのアクセス制御の構成

1. 設定の一覧で、[**メイン ユーザー ダッシュボードのアクセス制御**] を選択します。

    [Image: 設定一覧の [メイン ユーザー ダッシュボード のアクセス制御] セクションのスクリーンショット。]
2. [ **メイン ユーザーのシングル サインオンを有効にする** ] チェック ボックスをオンにします。
3. 前の手順で作成した SSO 構成を選択します。
4. メイン ユーザー ダッシュボードにアクセスできる Microsoft Entra ユーザー グループのオブジェクト ID を [次のユーザー **グループへの制限** ] フィールドに入力します。 複数のグループを指定するには、オブジェクト ID をコンマで区切ります。
5. [ **保存] を** 選択して構成を保存します。

#### ワークスペースの既定のアクセス制御の構成

1. 設定の一覧で、[**ワークスペースの既定の設定**] を選択します

    [Image: 設定リストのワークスペースの既定の設定のスクリーンショット。]
2. ワークスペースの既定の設定の一覧で、[**ログイン、登録、SSO**] を選択します

    [Image: ワークスペースの既定の設定の一覧の [ログイン、登録、SSO] セクションのスクリーンショット。]
3. [ **ユーザーはシングル サインオンを使用してログインできます** ] チェック ボックスをオンにします。
4. 前の手順で作成した SSO 構成を選択します。
5. ワークスペースにアクセスできる Microsoft Entra ユーザー グループのオブジェクト ID を [ **次のユーザー グループへの制限** ] フィールドに入力します。 複数のグループを指定するには、オブジェクト ID をコンマで区切ります。
6. ワークスペースの作成後に、各ワークスペースのユーザー グループを個別に変更できます。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Howspace を追加する

Microsoft Entra アプリケーション ギャラリーから Howspace を追加して、Howspace へのプロビジョニングの管理を開始します。 既に SSO のために Howspace を設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Howspace への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Howspace に対する自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Howspace**] を選択します。

    [Image: アプリケーションの一覧の [Howspace] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Howspace テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Howspace に接続できることを確認します。 接続に失敗した場合は、Howspace アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: [プロビジョニング] プロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から Howspace に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Howspace のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Howspace API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Howspace で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | externalId | 糸 |  |  |
13. [マッピング] セクション **で** 、[ **Synchronize Microsoft Entra groups to Howspace]\(Microsoft Entra グループを Howspace に同期する**\) を選択します。
14. [属性マッピング] セクションで、Microsoft Entra ID から Howspace に同期されるグループ **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作の Howspace のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Howspace で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | members | リファレンス |  |  |
15. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
16. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
17. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hoxhunt-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Hoxhunt を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hoxhunt-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-09
- Summary: Microsoft Entra ID から Hoxhunt に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Hoxhunt ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Hoxhunt](https://www.hoxhunt.com/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Hoxhunt でユーザーを作成する
- アクセスが不要になった場合に Hoxhunt のユーザーを削除する
- Microsoft Entra ID と Hoxhunt の間でユーザー属性の同期を維持する
- Hoxhunt への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hoxhunt-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hoxhunt テナント。
- 組織の SCIM API キーと SCIM エンドポイント URL (Hoxhunt サポートによって構成済み)。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Hoxhunt の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Hoxhunt を構成する

[Hoxhunt サポート](mailto:support@hoxhunt.com)に問い合わせて SCIM API キーと SCIM エンドポイント URL を受け取り、Microsoft Entra ID でのプロビジョニングをサポートするように Hoxhunt を構成します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Hoxhunt を追加する

Microsoft Entra アプリケーション ギャラリーから Hoxhunt を追加して、Hoxhunt へのプロビジョニングの管理を開始します。 SSO のために Hoxhunt を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Hoxhunt への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Hoxhunt の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Hoxhunt]** を選択します。

    [Image: アプリケーション リストの Hoxhunt リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Hoxhunt テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Hoxhunt に接続できることを確認します。 接続に失敗した場合は、Hoxhunt アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: [プロビジョニング プロパティの構成] ページのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Hoxhunt に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Hoxhunt のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Hoxhunt API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Hoxhunt で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |  |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更履歴

- 2021/04/20 - コア ユーザー属性 **preferredLanguage** とエンタープライズ拡張属性 **urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division** のサポートが追加されました。
- 2023/08/08 - コア ユーザー属性 **addresses[type eq "work"].locality|String** とエンタープライズ拡張属性 **urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager** のサポートが追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hoxhunt-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Hoxhunt を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hoxhunt-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hoxhunt の間のシングル サインオンを構成する方法について説明します。

この記事では、Hoxhunt と Microsoft Entra ID を統合する方法について説明します。 Hoxhunt を Microsoft Entra ID と統合すると、次のことが可能になります。

- Hoxhunt にアクセスできるかどのユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Hoxhunt に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Hoxhunt サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Hoxhunt では、 **SP** によって開始される SSO がサポートされます。
- Hoxhunt では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hoxhunt-provisioning-tutorial)。

### ギャラリーからの Hoxhunt の追加

Microsoft Entra ID への Hoxhunt の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Hoxhunt を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Hoxhunt**」と入力します。
4. 結果パネルから **[Hoxhunt** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hoxhunt 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Hoxhunt に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Hoxhunt の関連ユーザー間にリンク関係を確立する必要があります。

Hoxhunt に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hoxhunt SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Hoxhunt テストユーザーを作成** - B.Simon に対応するユーザーを Hoxhunt において作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Hoxhunt**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.hoxhunt.com/saml/consume/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.hoxhunt.com/saml/consume/<ID>`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://game.hoxhunt.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Hoxhunt クライアント サポート チーム](mailto:support@hoxhunt.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Hoxhunt のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

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

このセクションでは、B.Simon に Hoxhunt へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Hoxhunt]** を参照します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Hoxhunt SSO の構成

**Hoxhunt** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Hoxhunt サポート チーム](mailto:support@hoxhunt.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Hoxhunt テストユーザーの作成

このセクションでは、Hoxhunt で Britta Simon というユーザーを作成します。 [Hoxhunt サポート チーム](mailto:support@hoxhunt.com)と協力して、Hoxhunt プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Hoxhunt では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hoxhunt-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Hoxhunt のサインオン URL にリダイレクトされます。
- Hoxhunt のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Hoxhunt] タイルを選択すると、このオプションは Hoxhunt のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hpe-aruba-networking-edgeconnect-global-enterprise-orchestrator-tutorial"} -->
## HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator を Microsoft Entra ID でシングル サインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hpe-aruba-networking-edgeconnect-global-enterprise-orchestrator-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-02
- Summary: Microsoft Entra ID と HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator の間でシングル サインオンを構成する方法について説明します。

この記事では、HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator と Microsoft Entra ID を統合する方法について説明します。 HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator と Microsoft Entra ID を統合すると、次のことができるようになります。

- HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator へのアクセス権を持つ Microsoft Entra ID を制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- HPE Aruba Networking EdgeConnect Global Enterprise バージョン:
    - 9.0.6 以降。
    - 10.0.2 以降。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator では、 **SP Initiated SSO と IDP** Initiated SSO がサポートされます。

### ギャラリーから HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator を追加する

Microsoft Entra ID との HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション** に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator**」と入力します。
4. 結果パネルから **HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator 向けに Microsoft Entra SSO を構成し、テストする

**B.Simon** というテスト ユーザーを使用して、HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator の関連ユーザーとの間にリンク関係を確立する必要があります。

HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO の構成** - この手順により、ユーザーはこの機能を使用できるようになります。
2. **Microsoft Entra テスト ユーザーの作成** - この手順では、B.Simon で Microsoft Entra のシングル サインオンをテストできます。
3. **HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator アプリケーションにテスト ユーザーを割り当てる** - この手順では、B.Simon が EdgeConnect Orchestrator で Microsoft Entra シングル サインオンを使用できるようにします。
4. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise アプリケーションHPE Aruba Networking EdgeConnect Global Enterprise Orchestratorシングルサインオンに移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [**基本的な SAML 構成**] セクションの [**識別子 (エンティティ ID)]** テキスト ボックス、**応答 URL (Assertion Consumer Service URL)** テキスト ボックス、**およびログアウト URL (省略可能)** に値を入力します。 これらの値を見つけるには、まず Global Enterprise Orchestrator にログインし、[ **リモート認証** ] ダイアログ ボックス **([管理] &gt; リモート認証)** に移動します。

    [Image: エンタープライズ認証のシステム設定を示すスクリーンショット。]

    b。 [ **リモート認証** ] ダイアログで、[ **+新しいサーバーの追加]** を選択します。

    c. **[種類]** フィールドから **[SAML**] を選択します。

    d. [ **名前** ] フィールドに、SAML 構成の名前を入力します。

    え **ACS URL** フィールドの横にあるコピー アイコンを選択します。

    f. Microsoft の [SAML での**シングル サインオンの設定**] ページの [**基本的な SAML 構成]** セクションに戻り、次の図に示すように値を貼り付けます。

    [Image: スクリーンショットは、エンタープライズ認証の構成を示しています。]

    ジー [ **保存] を** 選択して、[ **基本的な SAML 構成] セクションを** 閉じます。
6. [ **SAML でのシングル サインオンの設定** ] ページの **[属性と要求** ] セクションで、編集アイコンを選択し、下の強調表示されたエントリをコピーし、次に示すように Orchestrator の **[Username Attribute]\(ユーザー名属性** \) フィールドに情報を貼り付けます。

    [Image: スクリーンショットは、エンタープライズ認証の属性設定を示しています。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. 証明書をメモ帳などのテキスト エディターで開きます。 次に示すように、Orchestrator の **IdP X.509 Cert** フィールドに証明書の内容をコピーして貼り付けます。

    [Image: スクリーンショットは、エンタープライズ認証の証明書エディターを示しています。]
9. [ **SAML でのシングル サインオンの設定** ] ページの **[HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator のセットアップ** ] セクションで、 **Microsoft Entra 識別子** をコピーし、[ **発行者 URL** ] フィールドに貼り付けます。 **ログイン URL を**コピーし、**SSO エンドポイント** フィールドに貼り付けます。

    [Image: スクリーンショットは、エンタープライズ認証のログイン設定を示しています。]
10. [ **リモート認証サーバー** ] ダイアログで、[ **既定のロール** ] フィールドを設定します。 (例: SuperAdmin) を設定します (これがドロップダウン リストの最後の項目です)。[属性とクレーム] セクションのロール属性にロールベースのアクセス制御 (RBAC) を定義しなかった場合は、既定のロールが必要です。
11. [リモート認証サーバー] ダイアログで **[保存]** を選択します。
12. これで Orchestrator で SAML SSO 認証が構成されました。 次のステップでは、テスト ユーザーを作成し、そのユーザーに Orchestrator アプリケーションを割り当てて、SAML が正常に構成されているかどうかを確認します。

#### Microsoft Entra ID テスト ユーザーを作成する

このセクションでは、Microsoft Entra 管理センターで B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [ **新しいユーザー**&gt;**新しいユーザー**の作成] を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. [ **ユーザー プリンシパル名** ] フィールドに、 username@companydomain.extensionを入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. [ **パスワードの表示** ] チェック ボックスをオンにし、[ **パスワード** ] ボックスに表示される値を書き留めます。
    4. [ **確認と作成**] を選択します。
5. **作成**を選択します。

#### HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator アプリケーションにテスト ユーザーを割り当てる

このセクションでは、B.Simon に HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator へのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator** を参照してください。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。

    ある。 [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。

    b。 ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。

    c. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator のサインオン URL にリダイレクトします。
- HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hpe-aruba-networking-edgeconnect-orchestrator-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に HPE Aruba Networking EdgeConnect Orchestrator を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hpe-aruba-networking-edgeconnect-orchestrator-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-04-12
- Summary: Microsoft Entra ID と HPE Aruba Networking EdgeConnect Orchestrator の間でシングル サインオンを構成する方法について説明します。

この記事では、HPE Aruba Networking EdgeConnect Orchestrator と Microsoft Entra ID を統合する方法について説明します。 HPE Aruba Networking EdgeConnect Orchestrator と Microsoft Entra ID を統合すると、次のことができます。

- HPE Aruba Networking EdgeConnect Orchestrator にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントで自動的に HPE Aruba Networking EdgeConnect Orchestrator にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- HPE Aruba Networking EdgeConnect Orchestrator バージョン 9.4.1 以降。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- HPE Aruba Networking EdgeConnect Orchestrator では、**SP開始 SSO と IDP開始 SSO** の両方がサポートされます。

### ギャラリーから HPE Aruba Networking EdgeConnect オーケストレーターを追加する

MICROSOFT Entra ID への HPE Aruba Networking EdgeConnect Orchestrator の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に HPE Aruba Networking EdgeConnect Orchestrator を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**HPE Aruba Networking EdgeConnect Orchestrator**」と入力します。
4. 結果パネルから **HPE Aruba Networking EdgeConnect Orchestrator** タイルを選択します。 **名前**を入力し、[**作成**] を選択してアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

    [Image: HPE Aruba Networking EdgeConnect Orchestrator を選択する方法を示すスクリーンショット。]

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HPE Aruba Networking EdgeConnect Orchestrator の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、HPE Aruba Networking EdgeConnect Orchestrator に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと HPE Aruba Networking EdgeConnect Orchestrator の関連ユーザーとの間にリンク関係を確立する必要があります。

HPE Aruba Networking EdgeConnect Orchestrator に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO の構成** - この手順により、ユーザーはこの機能を使用できるようになります。
2. **Microsoft Entra テスト ユーザーの作成** - この手順では、B.Simon で Microsoft Entra のシングル サインオンをテストできます。
3. **HPE Aruba Networking EdgeConnect Orchestrator アプリケーションにテスト ユーザーを割り当てる** - この手順では、B.Simon が EdgeConnect Orchestrator で Microsoft Entra シングル サインオンを使用できるようにします。
4. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動します。 検索バーに、前に作成した **HPE Aruba Networking EdgeConnect Orchestrator** アプリの名前を入力します。 **[概要]** ページが開きます。
3. 左側のウィンドウの [ **管理**] で、[ **シングル サインオン**] を選択します。
4. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
5. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
6. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** テキスト ボックス、**応答 URL (Assertion Consumer Service URL)** テキスト ボックス、およびログアウト URL (省略可能) の値を入力する必要があります。 これらの値を見つけるには、まず **Orchestrator にログイン** し、[ **認証** ] ダイアログ ボックス **(Orchestrator &gt; [ユーザー] と [認証] &gt; 認証)** に移動します。

    [Image: [認証] ダイアログに移動する方法を示すスクリーンショット。]

    b。 [ **認証** ] ダイアログで、[ **+新しいサーバーの追加]** を選択します。

    c. **[種類]** フィールドから **[SAML**] を選択します。

    d. [ **名前** ] フィールドに、SAML 構成の名前を入力します。

    え **ACS URL** フィールドの横にあるコピー アイコンを選択します。

    f. Microsoft の [SAML での**シングル サインオンの設定**] ページの [**基本的な SAML 構成**] セクションに移動します。

    1. [ **識別子 (エンティティ ID)] で**、[識別子リンクの **追加]** を選択します。 識別子のフィールドに ACS URL の値を貼り付けます。

        注

        1. "HPE Aruba Networking EdgeConnect Cloud Orchestrator"、"HPE Aruba Networking EdgeConnect Service Provider Orchestrator"、"HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator" の 3 つのオーケストレーター製品のいずれかで SAML SSO を構成する場合は、次のパターンを使用 `https://<SUBDOMAIN>.silverpeak.cloud/gms/rest/authentication/saml2/consume`。
        2. 自己デプロイ済みの HPE Aruba Networking EdgeConnect Orchestrator (オンプレミスにデプロイされている場合でも、Microsoft Entra などのパブリック クラウド環境でも) で SAML SSO を構成する場合は、次のパターンを使用 `https://<PUBLIC-IP-ADDRESS-OF-ORCHESTRATOR>/gms/rest/authentication/saml2/consume`。
    2. **応答 URL (Assertion Consumer Service URL)**で、**応答 URL の追加リンク**を選択します。 応答 URL のフィールドに同じ ACS URL の値を貼り付けます。

        注

        1. "HPE Aruba Networking EdgeConnect Cloud Orchestrator"、"HPE Aruba Networking EdgeConnect Service Provider Orchestrator"、"HPE Aruba Networking EdgeConnect Global Enterprise Orchestrator" の 3 つのオーケストレーター製品のいずれかで SAML SSO を構成する場合は、次のパターンを使用 `https://<SUBDOMAIN>.silverpeak.cloud/gms/rest/authentication/saml2/consume`。
        2. 自己デプロイ済みの HPE Aruba Networking EdgeConnect Orchestrator (オンプレミスにデプロイされている場合でも、Microsoft Entra などのパブリック クラウド環境でも) で SAML SSO を構成する場合は、次のパターンを使用 `https://<PUBLIC-IP-ADDRESS-OF-ORCHESTRATOR>/gms/rest/authentication/saml2/consume`。
    3. [ **ログアウト URL (省略可能)]**で、次の図に示すように、Orchestrator の [リモート認証サーバー] ページから **EdgeConnect SLO エンドポイント** の値を貼り付けます。

    注

    セルフホステッド Orchestrator で、Orchestrator の [ACS URL] フィールドと [EdgeConnect SLO エンドポイント] フィールドにプライベート IP アドレスが表示されている場合は、Orchestrator のパブリック IP アドレスで更新してください。 以下のスクリーンショットに示すように、5 つのフィールドすべてに、Orchestrator のパブリック IP アドレス (プライベート IP ではなく) が含まれる必要があります。

    [Image: [基本的な SAML 構成] セクションを構成する方法を示すスクリーンショット。]

    ジー [ **保存] を** 選択して [ **基本的な SAML 構成] セクションを** 閉じます
7. [ **SAML でのシングル サインオンの設定** ] ページの **[属性と要求** ] セクションで、編集アイコンを選択し、下の強調表示されたエントリをコピーし、次に示すように Orchestrator の **[Username Attribute]\(ユーザー名属性** \) フィールドに情報を貼り付けます。

    [Image: ユーザー名属性を構成する方法を示すスクリーンショット。]
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードします。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. 証明書をメモ帳などのテキスト エディターで開きます。 次に示すように、Orchestrator の **IdP X.509 Cert** フィールドに証明書の内容をコピーして貼り付けます。

    [Image: 証明書を構成する方法を示すスクリーンショット。]
10. [ **SAML でのシングル サインオンの設定** ] ページの **[HPE Aruba Networking EdgeConnect Orchestrator のセットアップ** ] セクションで、 **Microsoft Entra 識別子** をコピーし、Orchestrator の **[発行者 URL** ] フィールドに貼り付けます。

    [Image: 発行者 URL を構成する方法を示すスクリーンショット。]
11. [プロパティ] タブを選択し、[ **ユーザー アクセス URL] を** コピーし、次に示すように Orchestrator の **[SSO エンドポイント]** フィールドに貼り付けます。

    [Image: SSO エンドポイントを構成する方法を示すスクリーンショット。]
12. [Orchestrator Remote Authentication Server]\(オーケストレーター リモート認証サーバー\) ダイアログで、[ **既定のロール** ] フィールドを設定します。 (例: SuperAdmin) を設定します (これがドロップダウン リストの最後の項目です)。[属性とクレーム] セクションのユーザー属性にロールベースのアクセス制御 (RBAC) を定義しなかった場合は、既定のロールが必要です。
13. [リモート認証サーバー] ダイアログで **[保存]** を選択します。
14. これで Orchestrator で SAML SSO 認証が構成されました。 次のステップでは、テスト ユーザーを作成し、そのユーザーに Orchestrator アプリケーションを割り当てて、SAML が正常に構成されているかどうかを確認します。

#### Microsoft Entra ID テスト ユーザーを作成する

このセクションでは、Microsoft Entra 管理センターで B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [ **新しいユーザー**&gt;**新しいユーザー**の作成] を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. [ **ユーザー プリンシパル名** ] フィールドに、 username@companydomain.extensionを入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. [ **パスワードの表示** ] チェック ボックスをオンにし、[ **パスワード** ] ボックスに表示される値を書き留めます。
    4. [ **確認と作成**] を選択します。
5. **作成**を選択します。

#### HPE Aruba Networking EdgeConnect Orchestrator アプリケーションにテスト ユーザーを割り当てる

このセクションでは、B.Simon に HPE Aruba Networking EdgeConnect Orchestrator へのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**HPE Aruba Networking EdgeConnect Orchestrator** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる HPE Aruba Networking EdgeConnect Orchestrator のサインオン URL にリダイレクトします。
- HPE Aruba Networking EdgeConnect Orchestrator のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した HPE Aruba Networking EdgeConnect Orchestrator に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで HPE Aruba Networking EdgeConnect Orchestrator タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した HPE Aruba Networking EdgeConnect Orchestrator に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hpe-aruba-networking-edgeconnect-service-provider-orchestrator-tutorial"} -->
## MICROSOFT Entra ID でシングル サインオン用に HPE Aruba Networking EdgeConnect Service Provider Orchestrator を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hpe-aruba-networking-edgeconnect-service-provider-orchestrator-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-02
- Summary: Microsoft Entra ID と HPE Aruba Networking EdgeConnect Service Provider Orchestrator 間のシングル サインオンを構成する方法について説明します。

この記事では、HPE Aruba Networking EdgeConnect Service Provider Orchestrator と Microsoft Entra ID を統合する方法について説明します。 HPE Aruba Networking EdgeConnect Service Provider Orchestrator を Microsoft Entra ID と統合すると、次のことが可能になります。

- HPE Aruba Networking EdgeConnect Service Provider Orchestrator にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが各自の Microsoft Entra アカウントを使用して HPE Aruba Networking EdgeConnect Service Provider Orchestrator に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- HPE Aruba Networking EdgeConnect Service Provider Orchestrator シングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- HPE Aruba Networking EdgeConnect Service Provider Orchestrator は、**SP および IDP** によって開始される SSO をサポートしています。

### ギャラリーから HPE Aruba Networking EdgeConnect Service Provider Orchestrator を追加する

HPE Aruba Networking EdgeConnect Service Provider Orchestrator と Microsoft Entra ID の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に HPE Aruba Networking EdgeConnect Service Provider Orchestrator を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加]** セクションで、検索ボックスに「**HPE Aruba Networking EdgeConnect Service Provider Orchestrator**」と入力します。
4. 結果パネルから "**HPE Aruba Networking EdgeConnect Service Provider Orchestrator**" を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HPE Aruba Networking EdgeConnect Service Provider Orchestrator 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、HPE Aruba Networking EdgeConnect Service Provider Orchestrator で Microsoft Entra SSO を構成およびテストします。 SSO を機能させるには、Microsoft Entra ユーザーと HPE Aruba Networking EdgeConnect Service Provider Orchestrator 内の関連ユーザーとの間にリンク関係を確立する必要があります。

HPE Aruba Networking EdgeConnect Service Provider Orchestrator を使用して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する** - この手順に従うと、ユーザーはこの機能を使用できるようになります。
2. **Microsoft Entra テスト ユーザーの作成** - この手順では、B.Simon で Microsoft Entra のシングル サインオンをテストできます。
3. **テスト ユーザーを HPE Aruba Networking EdgeConnect Service Provider Orchestrator アプリケーションに割り当てる** - この手順に従うと、B.Simon は EdgeConnect Orchestrator で Microsoft Entra シングル サインオンを使用できるようになります。
4. **SSO をテストする** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[HPE Aruba Networking EdgeConnect Service Provider Orchestrator]**&gt;**[シングル サインオン]** を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[基本的な SAML 構成]** セクションの **[識別子 (エンティティ ID)]** テキスト ボックス、**[応答 URL (Assertion Consumer Service URL)]** テキスト ボックス、および **[ログアウト URL (省略可能)]** に値を入力します。 これらの値を見つけるには、まず Service Provider Orchestrator にログインし、**[リモート認証]** ダイアログボックス **([管理] &gt; [リモート認証])** に移動します。

    [Image: スクリーンショットは、エンタープライズ認証のシステム設定を示しています。]

    b。 [ **リモート認証** ] ダイアログで、[ **+新しいサーバーの追加]** を選択します。

    c. **[種類]** フィールドから **[SAML]** を選択します。

    d. **[名前]** フィールドに、SAML 構成の名前を入力します。

    e. **ACS URL** フィールドの横にあるコピー アイコンを選択します。

    f. Microsoft の **[SAML を使用したシングル サインオンの設定]** ページの **[基本的な SAML 構成]** セクションに戻り、次の画像に示されているように値を貼り付けます。

    [Image: スクリーンショットはエンタープライズ認証の構成を示しています。]

    g. [ **保存] を** 選択して、[ **基本的な SAML 構成] セクションを** 閉じます。
6. [ **SAML でのシングル サインオンの設定** ] ページの **[属性と要求** ] セクションで、編集アイコンを選択し、下の強調表示されたエントリをコピーし、次に示すように Orchestrator の **[Username Attribute]\(ユーザー名属性** \) フィールドに情報を貼り付けます。

    [Image: スクリーンショットは、エンタープライズ認証の属性の設定を示しています。]
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. メモ帳などのテキスト エディターを使用して証明書を開きます。 以下に示すように、Orchestrator の **[IdP X.509 証明書]** フィールドに証明書の内容をコピーして貼り付けます。

    [Image: スクリーンショットは、エンタープライズ認証の証明書エディターを示しています。]
9. **[SAML を使用したシングル サインオンの設定]** ページの **[HPE Aruba Networking EdgeConnect Service Provider Orchestrator の設定]** セクションで、**[Microsoft Entra 識別子]** をコピーし、**[発行者 URL]** フィールドに貼り付けます。 **[ログイン URL]** をコピーし、**[SSO エンドポイント]** フィールドに貼り付けます。

    [Image: スクリーンショットは、エンタープライズ認証のログイン設定を示しています。]
10. **[リモート認証サーバー]** ダイアログで、**[既定のロール]** フィールドを設定します。 例: SuperAdmin (これはドロップダウン リストの最後の項目です)。[属性とクレーム] セクションのロール属性でロール ベースのアクセス制御 (RBAC) を定義していない場合は、既定のロールが必要になります。
11. [リモート認証サーバー] ダイアログで **[保存]** を選択します。
12. Orchestrator で SAML SSO 認証が正常に構成されました。 次の手順では、テスト ユーザーを作成し、そのユーザーに Orchestrator アプリケーションを割り当てて、SAML が正常に構成されているかどうかを確認します。

#### Microsoft Entra ID テスト ユーザーを作成する

このセクションでは、Microsoft Entra 管理センターで B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面上部の **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. **[表示名]** フィールドに、「`B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **[作成]** を選択します。

#### HPE Aruba Networking EdgeConnect Service Provider Orchestrator アプリケーションにテスト ユーザーを割り当てます。

このセクションでは、B.Simon に HPE Aruba Networking EdgeConnect Service Provider Orchestrator へのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**HPE Aruba Networking EdgeConnect Service Provider Orchestrator** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。

    a. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。

    b。 ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。

    c. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる HPE Aruba Networking EdgeConnect Service Provider Orchestrator のサインオン URL にリダイレクトされます。
- HPE Aruba Networking EdgeConnect Service Provider Orchestrator サインオン URL に直接アクセスし、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した HPE Aruba Networking EdgeConnect Service Provider Orchestrator に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで HPE Aruba Networking EdgeConnect Service Provider Orchestrator タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した HPE Aruba Networking EdgeConnect Service Provider Orchestrator に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hpesaas-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に HPE SaaS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hpesaas-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HPE SaaS の間のシングル サインオンを構成する方法について説明します。

この記事では、HPE SaaS と Microsoft Entra ID を統合する方法について説明します。 HPE SaaS を Microsoft Entra ID と統合すると、次のことが可能になります。

- HPE SaaS にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して HPE SaaS に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- HPE SaaS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- HPE SaaS では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの HPE SaaS の追加

Microsoft Entra ID への HPE SaaS の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に HPE SaaS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**HPE SaaS**」と入力します。
4. 結果のパネルから **[HPE SaaS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HPE SaaS 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、HPE SaaS に対する Microsoft Entra SSO を構成およびテストします。 SSO を機能させるには、Microsoft Entra ユーザーと HPE SaaS の関連ユーザーとの間にリンク関係を確立する必要があります。

HPE SaaS に対して Microsoft Entra SSO を構成およびテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HPE SaaS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **HPE SaaS でテストユーザーを作成 - Microsoft Entra にリンクされた B.Simon の HPE SaaS 上の対応ユーザーを作成するため**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**HPE SaaS**&gt;**シングルサインオン** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[サインオン URL]** ボックスに、URL として「`https://login.saas.hpe.com/msg`」と入力します。

    b。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<SUBDOMAIN>.saas.hpe.com`

    Note

    識別子の値は実際の値ではありません。 実際の識別子でこの値を更新します。 この値を取得するには、[HPE SaaS クライアント サポート チーム](https://support.hpe.com/connect/s/?language=en_US)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[HPE SaaS のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面上部の **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. **[表示名]** フィールドに、「`B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **[作成]** を選択します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に HPE SaaS へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. ブラウズして **Entra ID**&gt;**Enterprise アプリ**&gt;**HPE SaaS** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### HPE SaaS SSO の構成

**HPE SaaS** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [HPE SaaS サポート チーム](https://www.sas.com/en_us/contact.html)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### HPE SaaS テスト ユーザーの作成

このセクションでは、HPE SaaS で Britta Simon というユーザーを作成します。 [HPE SaaS サポート チーム](https://support.hpe.com/connect/s/product?language=en_US)と連携して、HPE SaaS プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる HPE SaaS サインオン URL にリダイレクトされます。
- HPE SaaS のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [HPE SaaS] タイルを選択すると、このオプションは HPE SaaS のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hr2day-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に HR2day by Merces を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hr2day-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HR2day by Merces の間のシングル サインオンを構成する方法について説明します。

この記事では、HR2day by Merces と Microsoft Entra ID を統合する方法について説明します。 HR2day by Merces を Microsoft Entra ID と統合すると、次のことができます。

- HR2day by Merces にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで自動的に HR2day by Merces にサインオンできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

HR2day by Merces と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- HR2day by Merces でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- HR2day by Merces では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの HR2day by Merces を追加する

Microsoft Entra ID への HR2day by Merces の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に HR2day by Merces を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**HR2day by Merces**」と入力します。
4. 結果パネルから **[HR2day by Merces]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HR2day by Merces に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、HR2day by Merces に Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと HR2day by Merces の関連ユーザーとの間にリンク関係を確立する必要があります。

HR2day by Merces に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HR2day by Merces SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **HR2day by Merces のテストユーザーを作成** - Microsoft Entra 上のユーザーの表象である B.Simon にリンクする HR2day by Merces での対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[HR2day by Merces]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://hr2day.force.com/<companyname>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantname>.force.com/<instancename>`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[HR2day by Merces クライアント サポート チーム](mailto:servicedesk@merces.nl)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. HR2day by Merces アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ **ユーザー属性] ダイアログを** 開きます。

    [Image: このスクリーンショットは、[編集] アイコンが選択された状態の [User Attributes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー属性) を示しています。]

    注

    SAML アサーションを構成する前に、[HR2day by Merces クライアント サポート チーム](mailto:servicedesk@merces.nl)に連絡し、テナントの一意識別子属性の値を請求する必要があります。 次のセクションの手順を完了するには、この値が必要です。
7. [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、[**編集] アイコン**を使用して要求を編集するか、[**新しい要求の追加]** を使用して要求を追加し、上の図に示すように SAML トークン属性を構成し、次の手順を実行します。

    | 名前 | ソース属性 |
    | --- | --- |
    | ATTR\_LOGINCLAIM | `join([mail],"102938475Z","@"` |
    |  |  |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [新しい要求の追加] オプションが備わっている [ユーザー要求] のスクリーンショット。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **[OK]** を選択します。

    g. **保存** を選択します。
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[HR2day by Merces のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

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

このセクションでは、B.Simon に HR2day by Merces へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**HR2day by Merces** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### HR2day by Merces SSO の構成

**HR2day by Merces** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [HR2day by Merces サポート チーム](mailto:servicedesk@merces.nl)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

注

Merces チームに、この統合ではエンティティ ID を **https://hr2day.force.com/INSTANCENAME** というパターンで設定する必要があることを伝えます。

#### HR2day by Merces テスト ユーザーの作成

このセクションでは、HR2day by Merces で Britta Simon というユーザーを作成します。 [HR2day by Merces サポート チーム](mailto:servicedesk@merces.nl)と連携し、HR2day by Merces プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

注

ユーザーを手動で作成する必要がある場合は、[HR2day by Merces クライアント サポート チーム](mailto:servicedesk@merces.nl)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる HR2day by Merces のサインオン URL にリダイレクトされます。
- HR2day by Merces のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [HR2day by Merces] タイルを選択すると、このオプションは HR2day by Merces のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hrworks-single-sign-on-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に HRworks Single Sign-On を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hrworks-single-sign-on-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-16
- Summary: Microsoft Entra ID と HRworks Single Sign-On の間でシングル サインオンを構成する方法について説明します。

この記事では、HRworks Single Sign-On と Microsoft Entra ID を統合する方法について説明します。 HRworks Single Sign-On を Microsoft Entra ID と統合すると、次のことが可能になります。

- HRworks Single Sign-On にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで HRworks Single Sign-On に自動的にサインインするように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- HRworks Single Sign-On でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- HRworks Single Sign-On では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの HRworks Single Sign-On の追加

Microsoft Entra ID, への HRworks Single Sign-On の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に HRworks Single Sign-On を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「HRworks Single Sign-On**」と入力します。
4. 結果のパネルから **[HRworks Single Sign-On]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HRworks Single Sign-On 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、HRworks Single Sign-On に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと HRworks Single Sign-On の関連ユーザーとの間にリンク関係を確立する必要があります。

HRworks Single Sign-On に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HRworks Single Sign-On SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **HRworks Single Sign-On テストユーザーを作成** - HRworks Single Sign-On で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[HRworks Single Sign-On]**&gt;**[シングル サインオン]**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.hrworks.de/?companyId=<COMPANY_ID>&directssologin=true`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、HRworks Single Sign-On ヘルプ センターの記事](https://help.hrworks.de/en/single-sign-on) を参照してください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **HRworks Single Sign-On のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### HRworks Single Sign-On の SSO の構成

1. 別の Web ブラウザー ウィンドウで、お使いの HRworks Single Sign-On 企業サイトに管理者としてサインインします。
2. メニュー バーの左側から **[Administrator**&gt;**Basics**&gt;**Security**&gt;**Single Sign-on** ] を選択し、次の手順を実行します。

    [Image: シングル サインオンの構成]

    a. [ **シングル サインオンを使用** する] チェック ボックスをオンにします。

    b。 **XMLメタデータ**を**メタデータ入力方法**として選択します。

    c. NameID の値として**[個々の NameID 識別子** **]を**選択します。

    d. メモ帳で、ダウンロードしたメタデータ XML を開き、その内容をコピーして、[ **メタデータ** ] テキストボックスに貼り付けます。

    e. **[保存] を選択します**。

#### HRworks Single Sign-On のテスト ユーザーの作成

Microsoft Entra ユーザーが HRworks Single Sign-On にサインインできるようにするには、ユーザーを HRworks Single Sign-On にプロビジョニングする必要があります。 HRworks Single Sign-On では、プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. 管理者として HRworks Single Sign-On にサインインします。
2. メニュー バーの左側にある **[管理者**&gt;**ユーザー**&gt;**ユーザー**&gt;**新しいユーザー** を選択します。

    [Image: [Persons](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/人) および [New person](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しい人) が選択されている HRworks ページを示すスクリーンショット。]
3. ポップアップで、[ **次へ**] を選択します。

    [Image: スクリーンショットは、ユーザーが選択できる国の一覧を示しています。]
4. [ **法的用語の国を持つ新しいユーザーを作成** する] ポップアップで、 **名**、 **姓** などのそれぞれの詳細を入力し、[ **作成**] を選択します。

    [Image: スクリーンショットは、ユーザーの姓と名を入力できるテキスト ボックスを示しています。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このリンクは、サインイン フローを開始できる HRworks Single Sign-On サインイン URL にリダイレクトされます。
- HRworks Single Sign-On サインイン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [HRworks Single Sign-On] タイルを選択すると、このオプションは HRworks Single Sign-On サインイン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hsb-thoughtspot-tutorial"} -->
## Microsoft Entra ID で HSB ThoughtSpot for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hsb-thoughtspot-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HSB ThoughtSpot の間でシングル サインオンを構成する方法について説明します。

この記事では、HSB ThoughtSpot と Microsoft Entra ID を統合する方法について説明します。 HSB ThoughtSpot を Microsoft Entra ID を統合すると、以下のことができます。

- HSB ThoughtSpot にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って HSB ThoughtSpot に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な HSB ThoughtSpot サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- HSB ThoughtSpot では、**SP** Initiated SSO がサポートされます
- HSB ThoughtSpot では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの HSB ThoughtSpot の追加

Microsoft Entra ID への HSB ThoughtSpot の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に HSB ThoughtSpot を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**HSB ThoughtSpot**」と入力します。
4. 結果パネルから **[HSB ThoughtSpot]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HSB ThoughtSpot 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、HSB ThoughtSpot に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと HSB ThoughtSpot の関連ユーザーとの間にリンク関係を確立する必要があります。

HSB ThoughtSpot との Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HSB ThoughtSpot の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **HSB ThoughtSpot テストユーザーの作成** - B.Simon に対応するユーザーを HSB ThoughtSpot で作成し、Microsoft Entra 上のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**HSB ThoughtSpot**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | サインオン URL |
    | --- |
    | `https://hsbthoughtspot.mruscloud.com:443` |
    | `https://hsbthoughtspot.mruscloud.com/#/login` |
    |  |
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[HSB ThoughtSpot のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### HSB ThoughtSpot の SSO の構成

**HSB ThoughtSpot** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [HSB ThoughtSpot サポート チーム](mailto:HSB-BDL-IT-SAPBO-ADMIN@hsb.com)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### HSB ThoughtSpot のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを HSB ThoughtSpot に作成します。 HSB ThoughtSpot では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 HSB ThoughtSpot にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる HSB ThoughtSpot のサインオン URL にリダイレクトされます。
- HSB ThoughtSpot のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [HSB ThoughtSpot] タイルを選択すると、このオプションは HSB ThoughtSpot のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hub-planner-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Hub Planner を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hub-planner-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hub Planner の間のシングル サインオンを構成する方法について説明します。

この記事では、Hub Planner と Microsoft Entra ID を統合する方法について説明します。 Hub Planner を Microsoft Entra ID と統合すると、次のことが可能になります。

- Hub Planner にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Hub Planner に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hub Planner でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Hub Planner では、**SP** Initiated SSO がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Hub Planner を追加する

Microsoft Entra ID への Hub Planner の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Hub Planner を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Hub Planner**」と入力します。
4. 結果のパネルから **[Hub Planner]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hub Planner 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Hub Planner に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Hub Planner の関連ユーザーとの間にリンク関係を確立する必要があります。

Hub Planner に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hub Planner の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Hub Planner のテスト ユーザーの作成** - Hub Planner で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Hub Planner**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`https://app.hubplanner.com/sso/metadata` という形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://app.hubplanner.com/sso/callback` のパターンを使用して URL を入力します

    c. **[サインオン URL]** ボックスに、`https://<SUBDOMAIN>.hubplanner.com` という形式で URL を入力します。

    Note

    これらの値は、使用する値です。 必要な変更点は、&lt; の &gt;SUBDOMAIN を、Hub Planner にサインアップしたときに受け取ったサブドメインで置き換えることだけです。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Set up Hub Planner](Hub Planner の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Hub Planner の SSO の構成

**Hub Planner** 側にシングル サインオンを構成するには、Hub Planner アカウントにサインインし、次のタスクを完了する必要があります。

#### Hub Planner に拡張機能をインストールする

SSO 機能を有効にするには、まず拡張機能を有効にする必要があります。 アカウント オーナーとして、または同等のアクセス許可があるアカウントで、次の手順を実行します。

1. **設定** に移動します。
2. サイド メニューで、**[拡張機能の管理]**&gt;**[Add/Remove Extensions](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/拡張機能の追加と削除)** を選択します。
3. シングル サインオンの拡張機能を見つけて追加するか、無料で試します。
4. 確認を求められたら、利用条件に同意して **[今すぐ追加]** を選択します。

#### SSO を有効にする

拡張機能を有効にしたら、アカウントの SSO を有効にする必要があります。

1. **設定** に移動します。
2. サイド メニューで、 **[認証]** を選択します。
3. **[SSO (Single Sign-On)](SSO (シングル サインオン))** を選択します。
4. **[SAML 2.0 エンドポイント URL (HTTP)]** に、**ログイン URL** を入力します。
5. **[ID プロバイダーの発行者]** に、Microsoft Entra 識別子を入力します。
6. **[X.509 証明書]** に、証明書を入力します。
7. **保存**を選択します。

#### Hub Planner のテスト ユーザーの作成

他のユーザーを追加したい場合は、**[設定]**&gt;**[リソースの管理]** に移動し、そこからユーザーを追加します。 必ずそれらのユーザーのメール アドレスを追加して招待してください。 招待されたユーザーはメールを受信したうえで、SSO を介してアクセスできるようになります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Hub Planner のサインオン URL にリダイレクトされます。
- Hub Planner のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Hub Planner] タイルを選択すると、このオプションは Hub Planner のサインオン URL にリダイレクトされます。 詳細については、[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hubble-tutorial"} -->
## Microsoft Entra ID で Hubble for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hubble-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hubble の間のシングル サインオンを構成する方法について説明します。

この記事では、Hubble と Microsoft Entra ID を統合する方法について説明します。 Hubble を Microsoft Entra ID と統合すると、次のことが可能になります。

- Hubble にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Hubble に自動的にサインインできるように設定する。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hubble でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Hubble では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Hubble の追加

Microsoft Entra ID への Hubble の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Hubble を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Hubble**」と入力します。
4. 結果パネルから **Hubble** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hubble 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Hubble に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Hubble の関連ユーザーとの間にリンク関係を確立する必要があります。

Hubble に対して Microsoft Entra SSO を構成およびテストするするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hubble SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Hive のテスト ユーザーの作成** - Hubble で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Hubble**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、URL を入力します。 `https://api.hubble-docs.com/api/v1/organizations/samls/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://api.hubble-docs.com/api/v1/organizations/samls/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.hubble-docs.com/saml-login`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Hubble の SSO の構成

**Hubble** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** を Hubble の構成ページにアップロードする必要があります。

#### Hubble のテスト ユーザーの作成

このセクションでは、Hubble で B.Simon というユーザーを作成します。 [Hubble クライアント サポート チーム](mailto:cs@hubble-inc.jp)と協力して、Hubble プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Hubble のサインオン URL にリダイレクトされます。
- Hubble のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Hubble] タイルを選択すると、このオプションは Hubble のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hubspot-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に HubSpot を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hubspot-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HubSpot の間のシングル サインオンを構成する方法について説明します。

この記事では、HubSpot と Microsoft Entra ID を統合する方法について説明します。 HubSpot を Microsoft Entra ID と統合すると、次のことが可能になります。

- HubSpot へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで HubSpot に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオンが有効な HubSpot のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成してテストし、HubSpot と Microsoft Entra ID を統合します。

HubSpot では、次の機能がサポートされています。

- **SP によって開始されるシングル サインオン**。
- **IDP Initiated シングル サインオン**。

### ギャラリーから HubSpot を追加する

HubSpot と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に HubSpot をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**HubSpot**」と入力します。
4. 結果のパネルから **[HubSpot]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HubSpot 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、HubSpot 用に Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと HubSpot の関連ユーザーとの間にリンク関係を確立する必要があります。

HubSpot に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HubSpot SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **HubSpot のテスト ユーザーの作成** - HubSpot で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**HubSpot** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** ウィンドウで、**IDP 開始モード**を構成するために、次の手順を実行します。

    1. **[識別子]** ボックスに、https://api.hubspot.com/login-api/v1/saml/login?portalId=&lt;CUSTOMER ID&gt; の形式で URL を入力します。
    2. **[応答 URL]** ボックスに、https://api.hubspot.com/login-api/v1/saml/acs?portalId=&lt;CUSTOMER ID&gt; の形式で URL を入力します。

    注

    URL の書式を設定するには、**[基本的な SAML 構成]** ウィンドウに示されているパターンを参照することもできます。
6. *SP-initiated* モードでアプリケーションを構成するには:

    1. [ **追加の URL の設定] を選択します**。
    2. **[サインオン URL]** ボックスに、「**https://app.hubspot.com/login**」と入力します。
7. **[SAML でシングル サインオンをセットアップします]** ウィンドウの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** の横にある **[ダウンロード]** を選択します。 要件に基づいてダウンロード オプションを選択します。 お使いのコンピューターに証明書ファイルを保存します。

    [Image: 証明書 (Base64) ダウンロード オプション]
8. **[HubSpot のセットアップ]** セクションで、要件に基づいて次の URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### HubSpot SSO の構成

1. ブラウザーで新しいタブを開き、HubSpot の管理者アカウントにサインインします。
2. ページの右上隅にある **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** アイコンを選択します。

    [Image: HubSpot の [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) アイコン]
3. **[Account Defaults](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントの既定値)** を選択します。

    [Image: HubSpot の [Account Defaults](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントの既定値) オプション]
4. **[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)** セクションまで下にスクロールし、 **[Set up](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セットアップ)** を選択します。

    [Image: HubSpot の [Set up](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セットアップ) オプション]
5. **[Set up single sign-on](シングル サインオンの設定)** セクションで、次の手順を実行します。

    1. **[Audience URl (Service Provider Entity ID)](オーディエンス URI (サービス プロバイダー エンティティ ID))** ボックスで、 **[Copy](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/コピー)** を選択して値をコピーします。 Azure portal で、 **[基本的な SAML 構成]** ウィンドウの **[識別子]** ボックスに値を貼り付けます。
    2. **[Sign on URL, ACS, Recipient, or Redirect](サインオン URL、ACS、受信者、またはリダイレクト)** ボックスで、 **[Copy](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/コピー)** を選択して値をコピーします。 Azure portal で、 **[基本的な SAML 構成]** ウィンドウの **[応答 URL]** ボックスに値を貼り付けます。
    3. HubSpot の **[Identity Provider Identifier or Issuer URL](ID プロバイダーの識別子または発行者 URL)** ボックスに、コピーした **[Microsoft Entra 識別子]** の値を貼り付けます。
    4. HubSpot の **[Identity Provider Single Sign-On URL](ID プロバイダーのシングル サインオン URL)** ボックスに、コピーした **[ログイン URL]** の値を貼り付けます。
    5. Windows のメモ帳で、ダウンロードした**証明書 (Base64)** ファイルを開きます。 ファイルの内容を選択してコピーします。 次に、HubSpot でそれを **[X.509 Certificate](X.509 証明書)** ボックスに貼り付けます。
    6. [ **確認**] を選択します。

        [Image: HubSpot の [Set up single sign-on](シングル サインオンの設定) セクション]

#### HubSpot のテスト ユーザーの作成

Microsoft Entra ID ユーザーが HubSpot にサインインできるようにするには、そのユーザーが HubSpot でプロビジョニングされている必要があります。 HubSpot では、プロビジョニングは手動で行います。

HubSpot でユーザー アカウントをプロビジョニングするには

1. HubSpot 企業サイトに管理者としてサインインします。
2. ページの右上隅にある **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** アイコンを選択します。

    [Image: HubSpot の [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) アイコン]
3. [ **ユーザーとチーム]** を選択します。

    [Image: HubSpot の [ユーザーとチーム] オプション]
4. **[Create user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成)** を選択します。

    [Image: HubSpot の [Create user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成) オプション]
5. **[Add email address(es)] (メール アドレスの追加)** ボックスにユーザーのメール アドレスを brittasimon@contoso.com という形式で入力し、**[Next] (次へ)** を選択します。

    [Image: HubSpot の [Create users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成) セクションの [Add email addess(es)](メール アドレスの追加) ボックス]
6. **[Create users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成)** セクションで、各タブを選択します。各タブで、ユーザーに関連するオプションとアクセス許可を設定します。 次に、 **[次へ]** を選択します。

    [Image: HubSpot の [Create users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成) セクションのタブ]
7. ユーザーに招待を送信するには、 **[Send](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/送信)** を選択します。

    [Image: HubSpot の [Send](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/送信) オプション]

    注

    ユーザーが招待を受け入れると、ユーザーはアクティブ化されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる HubSpot のサインオン URL にリダイレクトされます。
- HubSpot のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した HubSpot に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [HubSpot] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した HubSpot に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/huddle-tutorial"} -->
## Microsoft Entra ID で Huddle for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/huddle-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Huddle の間のシングル サインオンを構成する方法について説明します。

この記事では、Huddle と Microsoft Entra ID を統合する方法について説明します。 Huddle を Microsoft Entra ID と統合すると、次のことが可能になります。

- Huddle にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Huddle に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Huddle でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Huddle では、**SP および IDP** により開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Huddle を追加する

Microsoft Entra ID への Huddle の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Huddle を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Huddle**」と入力します。
4. 結果パネルから **Huddle** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Huddle 用の Microsoft Entra SSO を構成およびテストする

**B. Simon** というテスト ユーザーを使用して、Huddle に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Huddle の関連ユーザーとの間にリンク関係を確立する必要があります。

Huddle に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. ユーザーがこの機能を使用できるように **Microsoft Entra SSO を構成**します。
    1. B. Simon で Microsoft Entra のシングル サインオンをテストする Microsoft **Entra テスト ユーザーを作成**します。
    2. **Microsoft Entra テスト ユーザーを割り当てて** 、B. Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Huddle SSO を構成**して、アプリケーション側で SSO 設定を構成します。
    1. **Huddle のテスト ユーザーの作成** - Huddle に、Microsoft Entra でのユーザー表現にリンクされた B. Simon の対応ユーザーを作成します。
3. **SSO をテスト** して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Huddle** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    注

    あなたのhuddleインスタンスは、以下に入力されたドメインから自動的に検出されます。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **識別子** |
    | --- |
    | `https://login.huddle.net` |
    | `https://login.huddle.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://login.huddle.net/saml/browser-sso` |
    | `https://login.huddle.com/saml/browser-sso` |
    | `https://login.huddle.com/saml/idp-initiated-sso` |
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<customsubdomain>.huddle.com` |
    | `https://us.huddle.com` |

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値を取得するには、 [Huddle クライアント サポート チーム](https://huddle.zendesk.com) に問い合わせてください。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Huddle のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Huddle の SSO の構成

**Huddle** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Huddle サポート チーム](https://huddle.zendesk.com/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

注

Huddle サポート チームがシングル サインオンを有効にする必要があります。 構成が完了すると、通知が届きます。

#### Huddle のテスト ユーザーの作成

Microsoft Entra ユーザーが Huddle にログインできるようにするには、そのユーザーを Huddle にプロビジョニングする必要があります。 Huddle の場合、プロビジョニングは手動で行います。

**ユーザー プロビジョニングを構成するには、次の手順を実行します。**

1. **Huddle** 企業サイトに管理者としてログインします。
2. **[ワークスペース] を選択します**。
3. [**ユーザー**]&gt;**[ユーザーの招待]**を選択します。

    [Image: 人々]
4. [ **新しい招待の作成** ] セクションで、次の手順を実行します。

    [Image: 新しい招待]

    a. [ **参加するユーザーを招待するチームの選択** ] ボックスの一覧で、[ **チーム**] を選択します。

    b。 有効な Microsoft Entra アカウントの **メール アドレス** を入力し、招待するユーザーの **メール アドレス** 入力欄に入力します。

    c. [ **招待**] を選択します。

    注

    アカウントがアクティブになる前に、Microsoft Entra アカウント所有者に、アカウント確認用のリンクを記述した電子メールが送信されます。

注

Huddle から提供されている他の Huddle ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Huddle のサインオン URL にリダイレクトされます。
- Huddle のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Huddle に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Huddle タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Huddle に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/humanage-tutorial"} -->
## Microsoft Entra ID で Humanage for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/humanage-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Humanage の間にシングル サインオンを構成する方法について説明します。

この記事では、Humanage と Microsoft Entra ID を統合する方法について説明します。 Humanage を Microsoft Entra ID と統合すると、次のことができます。

- Humanage にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Humanage に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Humanage でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Humanage では、**SP**によって開始されたSSOをサポートしています
- Humanage を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスを拡張したものです。 [Microsoft Defender for Cloud Apps でセッション制御を適用する方法について説明します](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)。

### ギャラリーからの Humanage の追加

Microsoft Entra ID への Humanage の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Humanage を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Humanage**」と入力します。
4. 結果パネルから **Humanage** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Humanage 用に Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、Humanage に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Humanage の関連ユーザーとの間にリンク関係を確立する必要があります。

Humanage に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Humanage SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Humanage テスト ユーザーの作成 - Humanage** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Humanage**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cppatest.cslab.com.ar/#/saml/< CUSTOMER NAME >`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cppa.cslab.com.ar/#/entityId/< CUSTOMER NAME >`

    c. [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://authapi.cslab.com.ar/api/SamlConsume/< CUSTOMER NAME >`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、および応答 URL で値を更新します。 これらの値を取得するには、 [Humanage サポート チーム](mailto:support@cardinalconsulting.atlassian.net) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Humanage のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Humanage SSO の構成

**Humanage** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、アプリケーション構成からコピーした適切な URL を [Humanage サポート チーム](mailto:support@cardinalconsulting.atlassian.net)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Humanage のテスト ユーザーの作成

このセクションでは、Humanage で Britta Simon というユーザーを作成します。 [Humanage サポート チーム](mailto:support@cardinalconsulting.atlassian.net)と協力して、Humanage プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Humanage] タイルを選択すると、SSO を設定した Humanage に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/humbol-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Humbol を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/humbol-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-09
- Summary: ユーザー アカウントを Humbol に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Humbol と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Humbol](https://www.humbol.app/en/product/) に対してユーザーの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Humbol でユーザーを作成する。
- アクセスが不要になったら、Humbol のユーザーを削除します。
- Microsoft Entra ID と Humbol の間でユーザー属性の同期を維持する。
- Humbol に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Humbol Inc との SCIM API の利用を含む Humbol とのアクティブな契約。
- 管理者のアクセス許可がある Humbol のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Humbol の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Humbol を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように Humbol を構成するには、Humbol サポートにお問い合わせください。

1. Humbol 管理者として、[Humbol](https://my.humbol.app/login) 組織にログインします。
2. 組織の API の[設定ページ](https://my.humbol.app/settings#apis)に移動します。
    1. このページには、組織の SCIM API URL が記載されています。 コピーします。
    2. SCIM API トークンを作成し、値をコピーします。

    注

    トークン値は Humbol サービス上のどこにも保存されないため、トークンを失った場合は、新しいものを作成し、古いものを削除する必要があります。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Humbol を追加する

Microsoft Entra アプリケーション ギャラリーから Humbol を追加して、Humbol へのプロビジョニングの管理を開始します。 SSO のために Humbol を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Humbol への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて TestApp でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Humbol に対する自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Humbol** を選択します。

    [Image: アプリケーション リストの Humbol リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Humbol テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Humbol に接続できることを確認します。 接続に失敗した場合は、Humbol アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Humbol に同期されるユーザー属性を確認します。 **[Matching]** プロパティとして選択されている属性は、更新処理で Humbol のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Humbol API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Humbol で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | タイトル | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | 優先言語 | 糸 |  |  |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | addresses[type eq "work"].region | 糸 |  |  |
    | addresses[type eq "work"].country | 糸 |  |  |
    | roles[primary eq "True"].value | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |  |

    注

    - `roles[primary eq "True"].value` を含める場合、すべてのユーザーに確実にロールを 1 つ割り当てる必要があります。
    - もう 1 つのオプションは、ロール属性マッピングを削除し、Humbol アプリケーション内で Humbol ユーザー ロールを管理することです。
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hype-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Hype を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hype-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Hype 間にシングル サインオンを構成する方法について説明します。

この記事では、Hype と Microsoft Entra ID を統合する方法について説明します。 Hype を Microsoft Entra ID と統合すると、次のことができます。

- Hype にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Hype に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Hype でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Hype では、**SP** Initiated SSO がサポートされます。
- Hype では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Hype の追加

Microsoft Entra ID への Hype の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Hype を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Hype**」と入力します。
4. 結果のパネルから **Hype** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Hype 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Hype に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Hype の関連ユーザーとの間にリンク関係を確立する必要があります。

Hype に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Hype SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Hypeテストユーザーの作成** - HypeでB.Simonに対応するユーザーを作成し、そのユーザーをMicrosoft Entraの表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Hype** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。

        `https://<SUBDOMAIN>.hypeinnovation.com`
    2. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

        `https://<SUBDOMAIN>.hypeinnovation.com/Shibboleth.sso/Login`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 この値を取得するには、[Hype クライアント サポート チーム](mailto:itsupport@hype.de)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **メタデータ XML** を見つけて **[ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Hype のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Hype の SSO を構成する

**Hype** 側でシングル サインオンを構成するには、ダウンロードした**メタデータ XML** とアプリケーション構成からコピーした適切な URL を、[Hype サポート チーム](mailto:itsupport@hype.de)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Hype のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Hype に作成します。 Hype では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Hype にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Hype サインオン URL にリダイレクトされます。
- Hype のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Hype] タイルを選択すると、このオプションは Hype のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hyperanna-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に HyperAnna を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hyperanna-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HyperAnna の間でシングル サインオンを構成する方法について説明します。

この記事では、HyperAnna と Microsoft Entra ID を統合する方法について説明します。 HyperAnna と Microsoft Entra ID を統合すると、次のことができます。

- HyperAnna にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って HyperAnna に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- HyperAnna でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- HyperAnna では、**SP と IDP** によって開始される SSO がサポートされます

### ギャラリーからの HyperAnna の追加

Microsoft Entra ID への HyperAnna の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に HyperAnna を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**HyperAnna**」と入力します。
4. 結果のパネルから **[HyperAnna]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra のシングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使って、HyperAnna に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと HyperAnna の関連ユーザーとの間にリンク関係を確立する必要があります。

HyperAnna に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する** - ユーザーがこの機能を使用できるようにします。
2. **HyperAnna SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **HyperAnna テスト ユーザーの作成** - HyperAnna で Britta Simon 対応のユーザーを作成し、Microsoft Entra におけるユーザーの表現にリンクさせます。
6. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**HyperAnna** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    **[応答 URL]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://microsoft.hyperanna.com/userservice/auth/saml
    https://anna.hyperanna.com/userservice/auth/saml
    ```
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://microsoft.hyperanna.com/
    https://anna.hyperanna.com/
    ```
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[HyperAnna の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### HyperAnna SSO の構成

**HyperAnna** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [HyperAnna サポート チーム](mailto:support@hyperanna.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### HyperAnna テストユーザーの作成

このセクションでは、HyperAnna で Britta Simon というユーザーを作成します。 [HyperAnna サポート チーム](mailto:support@hyperanna.com)と連携して、HyperAnna プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [HyperAnna] タイルを選択すると、SSO を設定した HyperAnna に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hypervault-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Hypervault を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hypervault-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-09
- Summary: ユーザー アカウントを Hypervault に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Hypervault ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Hypervault](https://hypervault.com) に対してユーザーの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Hypervault でユーザーを作成します。
- アクセスが不要になったら、Hypervault のユーザーを削除します。
- Microsoft Entra ID と Hypervault の間でユーザー属性の同期を維持する。
- Hypervault への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Admin アクセス許可がある Hypervault のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Hypervault 間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように Hypervault を構成する

1. Hypervault アカウントにマネージャーとしてサインインします。
2. **[ワークスペースの設定]** ページに移動します。
3. [ **Microsoft Azure への接続** ] セクションで、[ **ユーザー プロビジョニングを有効にする**] を選択します。
4. ドメインとトークンの値をコピーします。 これらの値は、手順 5 で必要です。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Hypervault を追加する

Microsoft Entra アプリケーション ギャラリーから Hypervault を追加して、Hypervault へのプロビジョニングの管理を開始します。 以前に SSO のために Hypervault をセットアップしている場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Hypervault への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて Hypervault でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Hypervault に対する自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Hypervault]** を選択します。

    [Image: アプリケーションの一覧の Hypervault リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット]
6. [ **テナント URL** ] フィールドに、Hypervault テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Hypervault に接続できることを確認します。 接続に失敗した場合は、Hypervault アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Hypervault に同期されるユーザー属性を確認します。 **[Matching]\(照合\)** プロパティとして選択されている属性は、更新処理で Hypervault のユーザー アカウントとの照合に使用されます。 [照合対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合、その属性に基づいたユーザーのフィルター処理を Hypervault API がサポートしているか確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Hypervault で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | emails[type eq "work"].value | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/iamip-patent-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に IamIP Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/iamip-patent-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IamIP Platform の間のシングル サインオンを構成する方法について説明します。

この記事では、IamIP Platform と Microsoft Entra ID を統合する方法について説明します。 IamIP Platform を Microsoft Entra ID と統合すると、次のことが可能になります。

- IamIP Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して IamIP Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な IamIP Platform サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- IamIP Platform では、SP-Initiated SSO と IDP-Initiated SSO がサポートされます。
- IamIP Platform では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの IamIP Platform の追加

Microsoft Entra ID への IamIP Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に IamIP Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**IamIP Platform**」と入力します。
4. 結果パネルから **[IamIP Platform]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### IamIP Platform 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、IamIP Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと IamIP Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

IamIP Platform に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IamIP Platform の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IamIP Platform のテスト ユーザーの作成** - IamIP Platform で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[IamIP Platform]**&gt;**[シングル サインオン]** を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. [ **基本的な SAML 構成]** セクションで、 **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] ボックスに、URL を入力します。 `https://accounts.iamip.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://accounts.iamip.com/sso-callback` |
    | `https://accounts.iamip.com/sso-login` |
    | `https://accounts.iamip.com/sso-logout` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://accounts.iamip.com/login` |
    | `https://patents.iamip.com/login-user` |
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[IamIP Platform のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IamIP Platform の SSO の構成

**IamIP Platform** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [IamIP Platform サポート チーム](mailto:info@iamip.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IamIP Platform のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを IamIP Platform に作成します。 IamIP Platform では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 IamIP Platform にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションにより、ログイン フローを開始できる IamIP Platform のサインオン URL にリダイレクトされます。
- IamIP Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IamIP プラットフォームに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [IamIP Platform] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した IamIP プラットフォームに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ians-client-portal-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に IANS クライアント ポータルを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ians-client-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IANS クライアント ポータルの間でシングル サインオンを構成する方法について説明します。

この記事では、IANS クライアント ポータルと Microsoft Entra ID を統合する方法について説明します。 IANS クライアント ポータルを Microsoft Entra ID と統合すると、次のことができます。

- IANS クライアント ポータルにアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して IANS クライアント ポータルに自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IANS クライアント ポータルでのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- IANS クライアント ポータルでは、**SP開始SSO** と **IDP開始SSO** の両方がサポートされます。

### ギャラリーからの IANS クライアント ポータルの追加

Microsoft Entra ID への IANS クライアント ポータルの統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に IANS クライアント ポータルを追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「IANS クライアント ポータル**」と入力します。
4. 結果パネルから **IANS クライアント ポータル** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### IANS クライアント ポータルの Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、IANS クライアント ポータルに対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと IANS クライアント ポータルの関連ユーザーとの間にリンク関係を確立する必要があります。

IANS クライアント ポータルで Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IANS クライアント ポータルの SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IANS クライアント ポータルのテストユーザーを作成する。** - このユーザーは IANS クライアント ポータル内で B.Simon に対応し、Microsoft Entra ID にリンクされます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**IANS クライアント ポータル**&gt;**シングル サインオン**ページに移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア。 [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://www.iansresearch.com/account/saml/<Customer_ID>` |
    | `https://beta.iansresearch.com/account/saml/<Customer_ID>` |
    | `https://dev.iansresearch.com/account/saml/<Customer_ID>` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://www.iansresearch.com/account/login/saml-login?id=<Customer_ID>` |
    | `https://beta.iansresearch.com/account/login/saml-login?id=<Customer_ID>` |
    | `https://dev.iansresearch.com/account/login/saml-login?id=<Customer_ID>` |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://www.iansresearch.com` |
    | `https://beta.iansresearch.com` |
    | `https://dev.iansresearch.com` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [IANS クライアント ポータル サポート チーム](mailto:support@iansresearch.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **IANS クライアント ポータルのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IANS クライアント ポータルの SSO の構成

**IANS クライアント ポータル**側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** と、Microsoft Entra 管理センターからコピーした適切な URL を [IANS クライアント ポータル サポート チーム](mailto:support@iansresearch.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IANS クライアント ポータルのテスト ユーザーの作成

このセクションでは、IANS クライアント ポータルで B.Simon というユーザーを作成します。 [IANS クライアント ポータル サポート チーム](mailto:support@iansresearch.com)と協力して、IANS クライアント ポータル プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる IANS クライアント ポータルのサインオン URL にリダイレクトされます。
- IANS クライアント ポータルのサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した IANS クライアント ポータルに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [IANS クライアント ポータル] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した IANS クライアント ポータルに自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ibm-digital-business-automation-on-cloud-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に IBM Digital Business Automation on Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ibm-digital-business-automation-on-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IBM Digital Business Automation on Cloud の間でシングル サインオンを構成する方法について説明します。

この記事では、IBM Digital Business Automation on Cloud と Microsoft Entra ID を統合する方法について説明します。 IBM Digital Business Automation on Cloud と Microsoft Entra ID を統合すると、次のことができるようになります。

- IBM Digital Business Automation on Cloud にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して IBM Digital Business Automation on Cloud に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IBM Digital Business Automation on Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- IBM Digital Business Automation on Cloud では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーからの IBM Digital Business Automation on Cloud の追加

Microsoft Entra ID への IBM Digital Business Automation on Cloud の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に IBM Digital Business Automation on Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**IBM Digital Business Automation on Cloud**」と入力します。
4. 結果のパネルから **[IBM Digital Business Automation on Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### IBM Digital Business Automation on Cloud の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、IBM Digital Business Automation on Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと IBM Digital Business Automation on Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

IBM Digital Business Automation on Cloud に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IBM Digital Business Automation on Cloud の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IBM Digital Business Automation on Cloud のテスト ユーザーの作成** - IBM Digital Business Automation on Cloud で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**IBM Digital Business Automation on Cloud**&gt;**Single のサインオン**の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    c. メタデータ ファイルが正常にアップロードされると、**識別子**と**応答 URL** の値が、IBM Digital Business Automation on Cloud セクションのテキスト ボックスに自動的に設定されます。

    注

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。

    注

    お客様の Cloud サブスクリプションのメタデータ ファイルは、[IBM Digital Business Automation on Cloud クライアント サポート チーム](mailto:supportbpmoncloud@us.ibm.com)から入手できます。
6. **サービス プロバイダーのメタデータ ファイル**がなければ、 **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.automationcloud.ibm.com/isam/sps/<TENANT>/saml20`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.automationcloud.ibm.com/isam/sps/<TENANT>/saml20/login`
7. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.automationcloud.ibm.com/isam/sps/<TENANT>/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[IBM Digital Business Automation on Cloud クライアント サポート チーム](mailto:supportbpmoncloud@us.ibm.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[IBM Digital Business Automation on Cloud のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IBM Digital Business Automation on Cloud の SSO の構成

**IBM Digital Business Automation on Cloud** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [IBM Digital Business Automation on Cloud サポート チーム](mailto:supportbpmoncloud@us.ibm.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IBM Digital Business Automation on Cloud のテスト ユーザーの作成

このセクションでは、IBM Digital Business Automation on Cloud で Britta Simon というユーザーを作成します。 [IBM Digital Business Automation on Cloud サポート チーム](mailto:supportbpmoncloud@us.ibm.com)と連携して、IBM Digital Business Automation on Cloud プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションを選択すると、ログイン フローを開始できる IBM Digital Business Automation on Cloud のサインオン URL にリダイレクトされます。
- IBM Digital Business Automation on Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IBM Digital Business Automation on Cloud に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [IBM Digital Business Automation on Cloud] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した IBM Digital Business Automation on Cloud に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ibm-storage-virtualize-oidc-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に IBM Storage Virtualize を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ibm-storage-virtualize-oidc-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-02
- Summary: Microsoft Entra と IBM Storage Virtualize の間にシングル サインオンを構成する方法について説明します。

この記事では、IBM Storage Virtualize と Microsoft Entra ID を統合する方法について説明します。 IBM Storage Virtualize と Microsoft Entra ID を統合すると、次のことができます。

Microsoft Entra ID を使用して、IBM Storage Virtualize にアクセスできるユーザーを制御する。 ユーザーが自分の Microsoft Entra アカウントを使用して IBM Storage Virtualize に自動的にサインインできるようにする。 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IBM Storage Virtualize でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから IBM Storage Virtualize を追加する

Microsoft Entra ID への IBM Storage Virtualize の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に IBM Storage Virtualize を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新規アプリケーション**に移動します。
3. **「ギャラリーからの追加**」セクションで、検索ボックスに**「IBM Storage Virtualize**」と入力します。
4. 結果パネルで **IBM Storage Virtualize** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**IBM Storage Virtualize**&gt;**シングルサインオン**に移動します。
3. 次のセクションで以下の手順を実行します。

    1. [ **アプリケーションに移動] を**選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID を**コピーし、後で IBM Storage Virtualize 側の構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
4. 左側のメニューの [ **認証** ] タブに移動し、次の手順を実行します。

    1. [ **リダイレクト URI** ] ボックスに、IBM Storage Virtualize 側からコピーした **リダイレクト URI** 値を貼り付けます。

        [Image: リダイレクト値を示すスクリーンショット。]
    2. [ **構成] ボタンを** 選択します。
5. 左側のメニューの **[証明書とシークレット** ] に移動し、次の手順を実行します。

    1. [ **クライアント シークレット** ] タブに移動し、[ **+新しいクライアント シークレット**] を選択します。
    2. テキストボックスに有効な **説明** を入力し、要件に従ってドロップダウンから **[有効期限** 日] を選択し、[ **追加**] を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、 **値** が生成されます。 この値をコピーして、後で IBM Storage Virtualize 側の構成で使用します。

        [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IBM Storage Virtualize SSO を構成する

OAuth/OIDC フェデレーションのセットアップを完了するための構成手順を次に示します。

1. `https://tenant.verify.ibm.com/ui/admin` の URL を使用して、IBM Storage Virtualize 管理者ダッシュボードにサインインします。
2. IBM セキュリティー検査インターフェースで、\*\*アプリケーション追加 **アプリケーション**を選択します。

    注

    各システムは、個別のアプリケーションとして追加する必要があります。
3. [ **全般] タブ** に移動し、次の手順を実行します。

    1. [ **名前** ] フィールドに、システムを識別する一意の名前を入力します。
    2. [ **説明** ] フィールドに、システムの簡単な説明を入力します。
    3. [ **会社名** ] フィールドに、組織または会社の名前を入力します。
4. [サインオン] タブ **に** 移動し、次の手順を実行します。

    1. システムの管理 GUI にアクセスするために使用する **アプリケーション URL を** 入力します。
    2. **承認コード**と **JWT ベアラー**許可の種類を選択します。
    3. [ **クライアント ID** ] フィールドに、Entra ページからコピーした **アプリケーション ID** の値を貼り付けます。
    4. [ **クライアント シークレット** ] フィールドに、Entra 側の **[証明書とシークレット** ] セクションからコピーした値を貼り付けます。
    5. [ユーザーの同意] で、[ **同意を求めない** ] ボタンを選択します。
    6. **リダイレクト URI を**コピーし、後で Entra 構成で使用します。
    7. JWT ベアラー ユーザー ID から **[Username]** を選択します。
    8. JWT ベアラーの既定の ID ソースで **Cloud Directory** が選択されていることを確認します。
    9. [ **更新トークンの生成]** オプションがオフになっていることを確認します。
    10. **[ID トークン] オプションで [すべての既知のユーザー属性を送信する**] がオンになっていることを確認します。
    11. [**アクセス ポリシー**] で、[**既定のポリシーを使用**する] の選択を解除&gt;**[編集**] アイコンを選択&gt;**すべてのデバイスで [常に 2FA が必要**] を選択します&gt;**[OK] を選択します**。
    12. [ **カスタム スコープの制限** ] オプションがオフになっていることを確認します。
    13. **[保存] を選択します**。
    14. 確認ページで、[ **確認** ] を選択して、システムのシングル サインオンを有効にします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ibm-tririga-on-cloud-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に IBM TRIRIGA on Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ibm-tririga-on-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IBM TRIRIGA on Cloud 間にシングル サインオンを構成する方法について説明します。

この記事では、IBM TRIRIGA on Cloud と Microsoft Entra ID を統合する方法について説明します。 不動産、資本プロジェクト、施設、職場運営、ポートフォリオ データ、環境およびエネルギー管理などの機能を単一のテクノロジ プラットフォームで統合した IWMS。 IBM TRIRIGA on Cloud を Microsoft Entra ID と統合すると、次のことができます:

- IBM TRIRIGA on Cloud にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って IBM TRIRIGA on Cloud に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

IBM TRIRIGA on Cloud 向けの Microsoft Entra シングル サインオンの構成とテストはテスト環境で実行します。 IBM TRIRIGA on Cloud では、 **IDP** によって開始されるシングル サインオンがサポートされます。

### [前提条件]

Microsoft Entra ID を IBM TRIRIGA on Cloud と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- IBM TRIRIGA on Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから IBM TRIRIGA on Cloud アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから IBM TRIRIGA on Cloud を追加する

Microsoft Entra アプリケーション ギャラリーから IBM TRIRIGA on Cloud を追加して、IBM TRIRIGA on Cloud でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**IBM TRIRIGA on Cloud**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a） [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<CustomerName>.tririga.com` |
    | `https://<CustomerName-Environment>.tririga.com` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CustomerName>.tririga.com/samlsps` |
    | `https://<CustomerName-Environment>.tririga.com/samlsps` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、IBM TRIRIGA on Cloud サポート チーム](https://www.ibm.com/mysupport) に問い合わせてください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. 「 **IBM TRIRIGA on Cloud のセットアップ** 」セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### IBM TRIRIGA on Cloud の SSO の構成

**IBM TRIRIGA on Cloud** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [IBM TRIRIGA on Cloud サポート チームに](https://www.ibm.com/mysupport)送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### IBM TRIRIGA on Cloud のテスト ユーザーの作成

このセクションでは、IBM TRIRIGA on Cloud で Britta Simon というユーザーを作成します。 [IBM TRIRIGA on Cloud サポート チーム](https://www.ibm.com/mysupport)と協力して、IBM TRIRIGA on Cloud プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IBM TRIRIGA on Cloud に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [IBM TRIRIGA on Cloud] タイルを選択すると、SSO を設定した IBM TRIRIGA on Cloud に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ibmid-tutorial"} -->
## Microsoft Entra ID で IBMid for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ibmid-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IBMid の間のシングル サインオンを構成する方法について説明します。

この記事では、IBMid と Microsoft Entra ID を統合する方法について説明します。 IBMid を Microsoft Entra ID と統合すると、次のことが可能になります。

- IBMid にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで IBMid に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IBMid でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- IBMid では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- IBMid では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから IBMid を追加する

Microsoft Entra ID への IBMid の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に IBMid を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**IBMid**」と入力します。
4. 結果のパネルから **[IBMid]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### IBMid 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、IBMid に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと IBMid の関連ユーザーとの間にリンク関係を確立する必要があります。

IBMid に対する Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IBMid の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IBMid テスト ユーザーの作成** - IBMid で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[IBMid]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 識別子 |
    | --- |
    | 生産： |
    | `https://ibmlogin.ice.ibmcloud.com/saml/sps/saml20sp/saml20` |
    | プレプロダクション |
    | `https://prepiam.ice.ibmcloud.com/saml/sps/saml20sp/saml20` |
    |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | [応答 URL] |
    | --- |
    | 生産： |
    | `https://login.ibm.com/saml/sps/saml20sp/saml20/login` |
    | プレプロダクション |
    | `https://prepiam.ice.ibmcloud.com/saml/sps/saml20sp/saml20/login` |
    |  |
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://login.ibm.com`
7. **保存** を選択します。
8. IBMid アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. 前の手順に加えて、IBMid アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次の表に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 国 | ユーザーの国 |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | メールアドレス | ユーザーのメールアドレス |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[IBMid のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IBMid の SSO の構成

**IBMid** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [IBMid サポート チーム](mailto:ibmidfd@us.ibm.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IBMid のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを IBMid に作成します。 IBMid では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 IBMid にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる IBMid サインオン URL にリダイレクトされます。
- IBMid のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IBMid に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [IBMid] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインイン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した IBMid に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ibmopenpages-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に IBM OpenPages を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ibmopenpages-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IBM OpenPages 間のシングル サインオンを構成する方法について説明します。

この記事では、IBM OpenPages と Microsoft Entra ID を統合する方法について説明します。 IBM OpenPages を Microsoft Entra ID と統合すると、次のことが可能になります。

- IBM OpenPages へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで IBM OpenPages に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IBM OpenPages でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- IBM OpenPages では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからの IBM OpenPages の追加

IBM OpenPages の Microsoft Entra ID への統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に IBM OpenPages を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「IBM OpenPages**」と入力します。
4. 結果パネルから **IBM OpenPages** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### IBM OpenPages 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、IBM OpenPages に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと IBM OpenPages の関連ユーザー間にリンク関係を確立する必要があります。

IBM OpenPages で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IBM OpenPages SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IBM OpenPages テストユーザーの作成** - Microsoft Entra のユーザー表現にリンクされた B.Simon の対応ユーザーを IBM OpenPages で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**IBM OpenPages**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `http://<subdomain>.ibm.com:<ID>/openpages`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.ibm.com:<ID>/samlsps/op`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、IBM OpenPages クライアント サポート チーム](https://www.ibm.com/support/home/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **IBM OpenPages のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IBM OpenPages SSO の構成

**IBM OpenPages** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [IBM OpenPages サポート チーム](https://www.ibm.com/support/home/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IBM OpenPages テスト ユーザーの作成

このセクションでは、IBM OpenPages で Britta Simon というユーザーを作成します。 [IBM OpenPages サポート チーム](https://www.ibm.com/support/home/)と協力して、IBM OpenPages プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IBM OpenPages に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [IBM OpenPages] タイルを選択すると、SSO を設定した IBM OpenPages に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ice-contact-center-tutorial"} -->
## Microsoft Entra ID で ice Contact Center for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ice-contact-center-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ice Contact Center の間でシングル サインオンを構成する方法について説明します。

この記事では、ice Contact Center と Microsoft Entra ID を統合する方法について説明します。 ice Contact Center を Microsoft Entra ID と統合すると、次のことができます。

- ice Contact Center にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ice Contact Center に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

ice Contact Center は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- ice Contact Center でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ice Contact Center では、**SP** によって開始された SSO がサポートされています。

### ギャラリーからの ice Contact Center の追加

Microsoft Entra ID への ice Contact Center の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ice Contact Center を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ice Contact Center」と**入力します。
4. 結果パネルから **ice Contact Center** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ice Contact Center 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ice Contact Center に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ice Contact Center の関連ユーザーとの間にリンク関係を確立する必要があります。

ice Contact Center に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ice Contact Center の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ice Contact Center テスト ユーザーの作成** - ice Contact Center で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**ice Contact Center**&gt;**シングルサインオン**をブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<TENANT>-imrpool.icescape365.com:PORT/identity` |
    | `https://<TENANT>-imrpool.icescape.com:PORT/identity` |
    | `https://<TENANT>-imrpool.iceuc.com:PORT/identity` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<TENANT>-imrpool.icescape365.com:PORT/identity` |
    | `https://<TENANT>-imrpool.icescape.com:PORT/identity` |
    | `https://<TENANT>-imrpool.iceuc.com:PORT/identity` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TENANT>.iceuc.com/iceManager`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [ice Contact Center クライアント サポート チーム](mailto:support@computer-talk.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ice Contact Center SSO の構成

**ice Contact Center** 側でシングル サインオンを構成するには、Ice **Contact Center サポート チーム**に[アプリのフェデレーション メタデータ URL を](mailto:support@computer-talk.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ice Contact Center テスト ユーザーの作成

このセクションでは、ice Contact Center で Britta Simon というユーザーを作成します。 [ice Contact Center サポート チーム](mailto:support@computer-talk.com)と協力して、ice Contact Center プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる ice Contact Center のサインオン URL にリダイレクトされます。
- ice Contact Center のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで ice Contact Center タイルを選択すると、このオプションは ice Contact Center のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/icims-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ICIMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/icims-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-29
- Summary: Microsoft Entra ID と ICIMS の間のシングル サインオンを構成する方法について説明します。

この記事では、ICIMS と Microsoft Entra ID を統合する方法について説明します。 ICIMS を Microsoft Entra ID と統合すると、次のことが可能になります。

- ICIMS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで ICIMS に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- iCIMS ATS サブスクリプション。
- community.icims.com でサポート チケットを送信するためのアクセス。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ICIMS では、**SP** によって開始される SSO がサポートされています。

### ギャラリーからの ICIMS の追加

Microsoft Entra ID への ICIMS の統合を構成するには、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用する必要があります。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)**B.Simon** というテスト ユーザーを使用して、ICIMS に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと ICIMS の関連ユーザーとの間にリンク関係を確立する必要があります。

ICIMS 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra アプリケーションの登録**- ユーザーがこの機能を使用できるようにします。
    1. **Entra アプリケーションへの資格情報の追加** - SSO 統合のクライアント シークレットを確立します。
    2. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    3. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ICIMS の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Entra と iCIMS の間でユーザーをマップする方法を決定する** - iCIMS と Entra の間でユーザー アカウントをマップする方法を構成します。
    2. **SSO 統合のサポート チケットの送信** - SSO 統合の構成に必要な詳細を iCIMS スタッフに提供します。
    3. **ICIMS のテスト ユーザーの作成** - ICIMS に、Microsoft Entra ユーザー表現にリンクされた B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra アプリケーションの登録を構成する

iCIMS アプリケーション用に Microsoft Entra アプリケーションの登録を作成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. 複数のテナントにアクセスできる場合は、トップ メニューの [設定] アイコンを使用して、[ディレクトリ + サブスクリプション] メニューからアプリケーションを登録するテナントに切り替えます。
3. **Entra ID**&gt;**App registrations** に移動し、[**新規登録**] を選択します。
4. アプリケーションの表示名を入力します。 表示名は、サインイン時など、アプリケーションのユーザーがアプリを使用するときに表示されることがあります。 表示名はいつでも変更できます。また、複数のアプリの登録で同じ名前を共有できます。
5. アプリケーションを使用できるユーザー (サインイン対象ユーザーと呼ばれることもあります) を、**[この組織のディレクトリ内のアカウントのみ]** で指定します。
6. ICIMS データセンターに基づいてリダイレクト URI を指定します。

    a. 米国: `https://login.icims.com/login/callback`

    b。 EU（欧州連合）: `https://login.icims.ca/login/callback`

    c. CA: `https://login.icims.eu/login/callback`

注

使用するリダイレクト URI がわからない場合は、ログインせずに iCIMS ATS ドメインに移動します。 ドメインは、`<customernickname>.icims.com` の形式です (例: notacustomer.icims.com)。 手順 6 に示されているオプションのデータセンター ドメインの 1 つとドメインが一致するログイン ページにリダイレクトされます。

1. アプリケーション/client\_id をメモします。

#### 資格情報を Entra アプリケーションに追加する

このセクションでは、アプリケーションのクライアント シークレットを追加します。

1. Microsoft Entra 管理センターの [アプリの登録] でアプリケーションを選択します。
2. **[証明書とシークレット]**&gt;**[クライアント シークレット]**&gt;**[新しいクライアント シークレット]** の順に選択します。
3. クライアント シークレットの説明を追加します。
4. シークレットの有効期限を選択するか、カスタムの有効期間を指定します。

    注

    クライアント シークレットの有効期間は、2 年間 (24 か月) 以下に制限されています。 24 か月を超えるカスタムの有効期間を指定することはできません。 Microsoft では、有効期限の値は 12 か月未満に設定することをお勧めしています。

    注

    新しいシークレットを提供し、サービスの中断を回避するには、有効期限の少なくとも 30 日前に iCIMS テクニカル サポートに連絡する必要があります。
5. クライアント シークレットと有効期限を記録して、iCIMS サポート チームに提供します。

#### Entra アプリケーションにアクセス許可を追加する

このセクションでは、ユーザーをサインインさせ、サインインしているユーザーのプロファイルを読み取るアクセス許可をアプリケーションに追加します。

1. Microsoft Entra 管理センターの [アプリの登録] でアプリケーションを選択します。
2. クライアント アプリケーションの [概要] ページで、**[API のアクセス許可]**&gt;**[アクセス許可の追加]**&gt;**[Microsoft Graph]** の順に選択します。
3. 委任されたアクセス許可 を選択します。
4. [アクセス許可の選択] で次のアクセス許可を選択します。

    a. 委任済みユーザー &gt; User.Read

    注

    iCIMS では、Microsoft Entra ID アプリケーションを信頼するようユーザーに求めるプロンプトを表示しないように、User.Read に管理者の同意を付与することをお勧めしています。

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面上部の **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. **[表示名]** フィールドに、「`B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **[作成]** を選択します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に ICIMS へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ICIMS]** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. 選択した "既定のアクセス" ロールを割り当てます。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### ICIMS の SSO の構成

#### Entra と iCIMS の間でユーザーをマップする方法を決定する

組織の Entra ユーザー アカウントを必要に応じて iCIMS ATS ユーザーにマップするには、[照合元] 設定と [照合先] 設定を決定する必要があります。 [照合元] 設定は、ATS ユーザー アカウントと照合する Microsoft Entra ID 属性を示します。 [照合先] 設定は、照合する ATS ユーザー属性を示します。

##### [照合元] 設定オプション:

- **サブジェクト/NameID**: ユーザーの変更不可の識別子。ユーザーの認証に使用される Microsoft Entra ID アプリケーションに対して一意です。 1 人のユーザーが 2 つの異なるクライアント ID を使用して 2 つの異なるアプリにサインインすると、そのアプリは、サブジェクト要求に対して 2 つの異なる値を受け取ることになります。
- **OID**: Microsoft ID システムのオブジェクト (ここではユーザー アカウント) に対する変更不可の識別子です。 この ID によって、複数のアプリケーションでユーザーが一意に識別されます。 同じユーザーにサインインする 2 つの異なるアプリケーションは、oid 要求で同じ値を受け取ります。 Microsoft Graph は、この ID を、指定されたユーザー アカウントの ID プロパティとして返します。
- **メール**: ユーザーのメール アドレス。 メールは変更可能で、最初のログイン時にのみ一致する必要があります。この時点で、アカウントは ATS ユーザーにバインドされます。 このオプションは、変更可能なメール アドレスには推奨されません。

注

iCIMS では、Corporate SSO 経由で iCIMS ATS にアクセスする前に、Microsoft Entra ID ユーザーのメール アドレスを確認することをお勧めしています。 これにより、アカウントを無効なメール アドレスにリンクするのを防ぐことができます。

##### [照合先] 設定オプション:

- **ログイン**: ATS 個人レコードのログイン フィールド (ユーザー名とも呼ばれます)。
- **メール**: ATS 個人レコードのメール フィールド。
- **ExternalID**: ATS 個人レコードの外部 ID フィールド。

#### SSO 統合のサポート チケットを送信する

このセクションでは、サポート チケットを送信して、SSO 統合を設定するための iCIMS テクニカル サポートを要求します。

1. iCIMS ユーザー管理者に https://community.icims.com/login にアクセスするよう依頼します。
2. [サポート] &gt; [ケースの作成] を選択します。
3. チケットを送信する場合は、次の詳細を入力してください。
    - **[アプリケーション (クライアント) ID]** を指定します。
    - **[クライアント シークレット]** を指定します。
    - **[Microsoft Entra ID ドメイン]** を指定します。
    - **[IdP ドメイン]** を指定します。通常、組織の会社のメール アドレスのドメインです (たとえば、name@corporate-domain.com のドメインは、corporate-domain.com です)。 組織は複数の IdP ドメインを利用できます。 ドメインはリージョン内で一意である必要があります (たとえば、gmail.com は無効なドメインとなります)。
    - 統合の **[表示名]** を指定します。 これは、組織の従業員ユーザーに表示される名前です。
    - 統合のために組織の **[ロゴ URL]** を指定します。 20 x 20 ピクセルの正方形として表示されます。
    - 2 段階認証と **Multi-Factor Authentication** のどちらを適用しているかを明らかにします。 [はい] または [いいえ] で回答します。
    - 前の手順で選択したユーザーの **[照合元]** 設定と **[照合先]** 設定を指定します。

#### ICIMS のテスト ユーザーの作成

このセクションでは、B.Simon がそのユーザーの ICIMS でレコードを作成することで、シングル サインオンを使用できるようにします。

1. `<customernickname>.icims.com` の ATS アプリケーションに移動します。
2. ユーザー管理者としてログインします。
3. [作成] &gt; [ユーザー] &gt; [従業員] を選択します。
4. 名前、メールなど、この従業員の詳細を指定します。
5. **[照合先]** 設定に基づいてユーザーを作成したら、**[照合元]** 設定を使用して送信するデータと一致するように適切なフィールドに入力します。 たとえば、照合元が **OID** で照合先が**外部 ID** の場合は、[ログイン] タブにアクセスし、[外部 ID] を編集して Simon B. の OID にします。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- iCIMS サポートが SSO 統合を設定すると、テスト URL が提供されます。
- URL は、 [https://iam-federated-testing-bff.production.env.icims.tools/login/hs-#####-azure](https://iam-federated-testing-bff.production.env.icims.tools/login/hs-#####-azure)形式です。 URL の数字は、一意の ICIMS ATS 顧客 ID です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/idc-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に IDC を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/idc-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IDC の間でシングル サインオンを構成する方法について説明します。

この記事では、IDC と Microsoft Entra ID を統合する方法について説明します。 IDC と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で IDC へのアクセス権を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して IDC に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IDC でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- IDCでは、**SP開始SSO**と**IDP開始SSO**がサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの IDC の追加

Microsoft Entra ID への IDC の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に IDC を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;と**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「IDC**」と入力します。
4. 結果パネルから **IDC** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### IDC の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、IDC に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと IDC の関連ユーザーとの間にリンク関係を確立する必要があります。

IDC に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IDC SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IDCテストユーザーの作成** - B.Simonに対応するユーザーをIDC内で作成し、そのユーザーをMicrosoft Entraの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**IDC**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://www.idc.com/sp`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cas.idc.com:443/login?client_name=<ClientName>`

    c. [ **リレー状態** ] テキスト ボックスに、URL を入力します。 `https://www.idc.com/j_spring_cas_security_check`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.idc.com/saml-welcome/<SamlWelcomeCode>`

    手記

    応答 URL は、実際の値ではありません。 実際の応答 URL で値を更新します。 この値を取得するには、 [IDC クライアント サポート チーム](mailto:idc_support@idc.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **IDC のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IDC SSO の構成

**IDC** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とコピーした適切な URL を [IDC サポート チーム](mailto:idc_support@idc.com)に送信します。 IDC は、SAML SSO 接続が両方の側で正しく設定されるように、この設定を構成します。

#### IDC テスト ユーザーの作成

ユーザーを IDC で事前に作成する必要はありません。 ユーザーが初めてシングル サインオンを使用すると、自動的に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる IDC サインオン URL にリダイレクトされます。
- IDC サインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IDC に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [IDC] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した IDC に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->
