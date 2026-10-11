# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 3)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 73

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/atlassian-cloud-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して、Atlassian Cloud を自動ユーザー プロビジョニング用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/atlassian-cloud-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-26
- Summary: Microsoft Entra IDから Atlassian Cloud にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Atlassian Cloud と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID Microsoft Entra プロビジョニング サービスを使用して、[Atlassian Cloud](https://www.atlassian.com/cloud) にユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Atlassian Cloud で既存のユーザーを[検出](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery)する
- Atlassian Cloud でユーザーを作成する
- アクセスが不要になった Atlassian Cloud のユーザーを削除する
- Microsoft Entra IDと Atlassian Cloud の間でユーザー属性の同期を維持する
- Atlassian Cloud でグループとグループ メンバーシップをプロビジョニングする
- Atlassian Cloud への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/atlassian-cloud-tutorial) (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Atlassian 組織の管理者であることを確認します。 [組織の管理](https://support.atlassian.com/organization-administration/docs/explore-an-atlassian-organization)に関するページを参照してください。
- 組織内の 1 つ以上のドメインを確認します。 [ドメイン検証](https://support.atlassian.com/user-management/docs/verify-a-domain-to-manage-accounts)に関するページを参照してください。
- 組織から Atlassian Access をサブスクライブします。 [Atlassian Access のセキュリティ ポリシーと機能](https://support.atlassian.com/security-and-access-policies/docs/understand-atlassian-access)に関するページを参照してください。
- Atlassian Access のサブスクリプションがある [Atlassian Cloud テナント](https://www.atlassian.com/licensing/cloud)。
- 同期されたユーザーにアクセス権を付与する少なくとも 1 つの Jira または Confluence サイトの管理者であることを確認します。

    注

    この統合は、米国政府機関向けクラウド環境Microsoft Entra使用することもできます。 このアプリケーションは、Microsoft Entra US Government クラウド アプリケーション ギャラリーで見つけ、パブリック クラウドから行うのと同じ方法で構成できます。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとAtlassian Cloudの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Atlassian Cloud を構成する

1. [Atlassian 管理コンソール](http://admin.atlassian.com/)に移動します。 複数の組織がある場合は、組織を選択します。
2. **セキュリティ &gt; ID プロバイダー**を選択します。
3. ID プロバイダー ディレクトリを選択します。
4. **[ユーザー プロビジョニングの設定](Set up user provisioning) **を選択します。
5. **[SCIM base URL](SCIM ベース URL)** と **[API キー]** の値をコピーします。 Azureを構成するときに必要です。
6. **SCIM の構成**を保存します。

    注

    これらの値は再び表示されないため、安全な場所に保存してください。

    ユーザーとグループは、組織に自動的にプロビジョニングされます。 ユーザーとグループを組織と同期する方法の詳細については、「[ユーザー プロビジョニング](https://support.atlassian.com/provisioning-users/docs/understand-user-provisioning)」ページを参照してください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Atlassian Cloud を追加する

Microsoft Entra アプリケーション ギャラリーから Atlassian Cloud を追加して、Atlassian Cloud へのプロビジョニングの管理を開始します。 SSO 用に Atlassian Cloud を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Atlassian Cloud への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Atlassian Cloud でユーザーやグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Atlassian Cloud の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Atlassian Cloud** に移動します。

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Atlassian Cloud]** を選択します。

    [Image: [アプリケーション] リストの [Atlassian Cloud] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. **+ 新しい構成**を設定します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Atlassian Cloud のアカウントから先ほど取得した **テナント URL** と **シークレット トークン** を入力します。 **Test Connection** を選択して、Microsoft Entra IDが Atlassian Cloud に接続できることを確認します。 接続に失敗した場合は、Atlassian Cloud アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

[Image: プロビジョニングプロパティのスクリーンショット。]

1. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
2. **Attribute Mapping** セクションで、Microsoft Entra IDから Atlassian Cloud に同期されるユーザー属性を確認します。 **メール属性は、Atlassian Cloud アカウントとMicrosoft Entra アカウントを照合するために使用されます。** **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | 活動中 | ブール値 |
    | name.familyName | 糸 |
    | name.givenName | 糸 |
    | emails[type eq "work"].value | 糸 |
3. **[グループ] を選択します**。
4. **Attribute Mapping** セクションで、Microsoft Entra IDから Atlassian Cloud に同期されるグループ属性を確認します。 表示名属性は、Atlassian Cloud グループとMicrosoft Entra グループを照合するために使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | externalId | 糸 |
    | members | リファレンス |
5. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
6. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
7. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

- Atlassian Cloud では、ドメインが検証済みであるユーザーに関してのみ、更新プログラムのプロビジョニングがサポートされます。 検証されていないドメインからユーザーに加えられた変更は、Atlassian Cloud にはプッシュされません。 Atlassian の検証済みドメインについて詳しくは、[こちら](https://support.atlassian.com/provisioning-users/docs/understand-user-provisioning/)をご覧ください。
- Atlassian Cloud では、現在、グループ名の変更はサポートされていません。 つまり、Microsoft Entra IDのグループの displayName に対する変更は、Atlassian Cloud では更新されず、反映されません。
- Microsoft Entra ID の **mail** ユーザー属性の値は、ユーザーが Microsoft Exchange メールボックスを持っている場合にのみ設定されます。 ユーザーが持っていない場合は、Atlassian Cloud の **emails** 属性に別の必要な属性をマップすることをお勧めします。

### 変更ログ

- 2020 年 6 月 15 日 - グループ向けのバッチ PATCH のサポートが追加されました。
- 332021 年 4 月 21 日 - **スキーマ検出**のサポートが追加されました。
- 2022 年 10 月 14 日 - コネクタの制限事項を更新しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/atlassian-cloud-tutorial"} -->
## Microsoft Entra ID で Atlassian Cloud のシングルサインオンを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/atlassian-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Atlassian Cloud の間でシングル サインオンを構成する方法について説明します。

この記事では、Atlassian Cloud と Microsoft Entra ID を統合する方法について説明します。 Atlassian Cloud と Microsoft Entra ID を統合すると、次のことができます。

- Atlassian Cloud にアクセスできるユーザーをMicrosoft Entra IDで制御できます。
- ユーザーが自分のMicrosoft Entra アカウントを使用して Atlassian Cloud に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理する。

Atlassian Cloud は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Atlassian Cloud でのシングル サインオン (SSO) が有効なサブスクリプション
- Atlassian Cloud 製品の Security Assertion Markup Language (SAML) シングル サインオンを有効にするには、Atlassian Access を設定する必要があります。 詳細については、「[Atlassian Access](https://www.atlassian.com/enterprise/cloud/identity-manager)」を参照してください。

### シナリオの説明

この記事では、テスト環境で sso Microsoft Entraを構成し、テストします。

- Atlassian Cloud では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Atlassian Cloud では、[自動化されたユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/atlassian-cloud-provisioning-tutorial)がサポートされます。

### ギャラリーからの Atlassian Cloud の追加

Microsoft Entra IDへの Atlassian Cloud の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Atlassian Cloud を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Atlassian Cloud**」と入力します。
4. 結果のパネルから **[Atlassian Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 Microsoft 365 ウィザードの詳細については、[こちら](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides?view=o365-worldwide&preserve-view=true)を参照してください。

### Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Atlassian Cloud Microsoft Entra SSO を構成してテスト>。 SSO を機能させるには、Microsoft Entra ユーザーと Atlassian Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Atlassian Cloud Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Atlassian Cloud SSO**でMicrosoft Entra IDを構成する - ユーザーが Atlassian Cloud で Microsoft Entra ID ベースの SAML SSO を使用できるようにします。
    1. **Microsoft Entra のテスト ユーザーの作成** - Microsoft Entra のシングル サインオンを B.Simon でテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Atlassian Cloud のテスト ユーザーの作成** - Atlassian Cloud で B.Simon に対応するユーザーを作成し、Microsoft Entra の表現にリンクさせることを目的としています。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Atlassian Cloud SSO を使用してMicrosoft Entra IDを構成する

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. Web ブラウザーの別のウィンドウで、Atlassian Cloud 企業サイトに管理者としてサインインします。
2. **ATLASSIAN Admin** ポータルで、**Security**&gt;**Identity providers**&gt;**Microsoft Entra ID** に移動します。
3. **ディレクトリ名**を入力し、[**追加]** ボタンを選択します。
4. **[Set up SAML single sign-on] (SAML シングル サインオンの設定)** ボタンを選択して、ID プロバイダーを Atlassian 組織に接続します。

    [Image: ID プロバイダーのセキュリティを示すスクリーンショット。]
5. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
6. **Entra ID**&gt;**Enterprise apps**&gt;**Atlassian Cloud** アプリケーション統合ページに移動します。 **[管理]** セクションを見つけます。 **[概要]** で、**[シングル サインオンの設定]** を選択します。
7. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
8. **[SAML によるシングル サインオンのセットアップ]** ページで、下へスクロールして **[Atlassian Cloud のセットアップ]** に移動します。

    a. **[構成 URL] を選択します**。

    b。 Azure ポータルから **Login URL** の値をコピーして、Atlassian の [**Identity provider SSO URL**] テキストボックスに貼り付けます。

    c. Azure ポータルから **Microsoft Entra Identifier** の値をコピーして、Atlassian の **Identity provider Entity ID** テキストボックスに貼り付けます。

    [Image: スクリーンショットには、設定値が表示されています。]
9. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 署名証明書]

    [Image: Azure の証明書を表示しているスクリーンショットです。]
10. SAML 構成を保存し、Atlassian で **[次へ** ] を選択します。
11. **[基本的な SAML 構成]** セクションで、次の手順を行います。

    a. Atlassian から **Service プロバイダー エンティティ URL** 値をコピーし、Azureの **Identifier (Entity ID)** ボックスに貼り付けて、既定値として設定します。

    b。 Atlassian から **Service プロバイダー アサーション コンシューマー サービス URL** の値をコピーし、Azureの **Reply URL (Assertion Consumer Service URL)** ボックスに貼り付けて、既定値として設定します。

    c. [**次へ**] を選択します。

    [Image: サービス プロバイダーの画像を示すスクリーンショット。]

    [Image: サービス プロバイダーの値を示すスクリーンショット。]
12. Atlassian Cloud アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 属性マッピングは、[編集] アイコンを選択して **編集** できます。

    [Image: 属性]

    1. Microsoft 365 ライセンスを持つMicrosoft Entra テナントの属性マッピング。

        a. **一意のユーザー識別子 (名前 ID) 要求を**選択します。

        [Image: 属性と要求]

        b。 Atlassian Cloud では、 **nameidentifier** (**一意のユーザー識別子**) がユーザーのメール (**user.mail**) にマップされることを想定しています。 **[ソース属性]** を編集して、「**user.mail**」に変更してください。 要求に対する変更を保存します。

        [Image: 一意のユーザー ID]

        c. 最終的な属性マッピングは、次のようになります。

        [Image: 画像 2]
    2. Microsoft 365 ライセンスのないMicrosoft Entra テナントの属性マッピング。

        a. `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`要求を選択します。

        [Image: 画像 3]

        b。 Azureでは、Microsoft 365ライセンスなしでMicrosoft Entraテナントに作成されたユーザーについて、**user.mail** 属性を設定しませんが、そのようなユーザーの電子メールを **userprincipalname** 属性に格納します。 Atlassian Cloud では、**nameidentifier** (**一意のユーザー ID**) がユーザーのメール (**user.userprincipalname**) にマップされると想定されています。 **[ソース属性]** を編集して、「**user.userprincipalname**」に変更してください。 要求に対する変更を保存します。

        [Image: メールを設定する]

        c. 最終的な属性マッピングは、次のようになります。

        [Image: 画像 4]
13. [ **SAML の停止と保存] ボタンを** 選択します。

    [Image: 構成の保存の画像を示すスクリーンショット。]
14. 認証ポリシーに SAML シングル サインオンを適用するには、次の手順を行います。

    a. **Atlassian 管理**ポータルで、[**セキュリティ**] タブを選択し、[**認証ポリシー**] を選択します。

    b。 適用するポリシーに対して **[編集]** を選択します。

    c. **[設定]** で、管理対象ユーザーに対する **[Enforce single sign-on]\(シングルサインオンの適用\)** を有効にして、SAML リダイレクトが正常に行われるようにします。

    d. **[更新]** を選択します。

    [Image: 認証ポリシーを示すスクリーンショット。]

    注

    管理者は SAML 構成をテストすることができます。それには、最初に個別の認証ポリシーでユーザーのサブセットに対して SSO の適用を有効にするだけとし、それで問題がなければ、すべてのユーザーに対してポリシーを有効にします。

#### Microsoft Entraのテスト ユーザーを作成して割り当てる

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Atlassian Cloud のテスト ユーザーの作成

ユーザー Microsoft Entra Atlassian Cloud にサインインできるようにするには、次の手順に従って Atlassian Cloud でユーザー アカウントを手動でプロビジョニングします。

1. [ **製品** ] タブに移動し、[ **ユーザー** ] を選択し、[ **ユーザーの招待**] を選択します。

    [Image: Atlassian Cloud の [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) リンク]
2. [ **電子メール アドレス** ] ボックスにユーザーのメール アドレスを入力し、[ **ユーザーの招待**] を選択します。

    [Image: Atlassian Cloud ユーザーの作成]

#### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra シングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Atlassian Cloud のサインオン URL にリダイレクトされます。
- Atlassian Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Atlassian Cloud に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Atlassian Cloud タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Atlassian Cloud に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリのイントロダクション](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。

### Atlassian Cloud で既存のユーザーを検出する

Microsoft Entraと統合する前に、Atlassian アカウントに既に 1 人以上のユーザーが存在する可能性があります。 アカウント検出機能を使用すると、Atlassian Cloud のすべてのユーザーのレポートを生成し、Entra で一致するアカウントを持っているユーザーと、Atlassian Cloud にローカルなユーザーを 1 回のクリックで識別できます。 アカウント検出機能の詳細については [、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery)。 これにより、Entra へのオンボードを簡素化しながら、承認されていないアクセスを段階的に監視することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/atmos-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Atmos を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/atmos-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-26
- Summary: Microsoft Entra IDから Atmos にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Atmos と Microsoft Entra ID の両方で自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 Microsoft Entra ID を設定すると、Microsoft Entra プロビジョニング サービスを使用して、[Atmos](https://www.axissecurity.com/) にユーザーとグループを自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Atmos でユーザーを作成する。
- アクセスが必要なくなった場合は、Atmos のユーザーを削除します。
- Microsoft Entra IDと Atmos の間でユーザー属性の同期を維持します。
- Atmos でグループとグループ メンバーシップをプロビジョニングする。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者のアクセス許可を持つ [Axis Security](https://www.axissecurity.com) のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとAtmosの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Atmos を構成する

1. Axis 管理コンソールにログインします。
2. **[設定]** -&gt;**[ID プロバイダー]** 画面に移動します。
3. **Azure ID プロバイダー**にカーソルを合わせ、**edit** を選択します。
4. **[詳細設定]** に移動します。
5. **[User Auto-Provisioning (SCIM)] (ユーザー自動プロビジョニング (SCIM))** に移動します。
6. **[新しいトークンの生成]** を選択します。
7. **[SCIM Service Provider Endpoint] (SCIM サービス プロバイダー エンドポイント)** と **[SCIM Provisioning Token] (SCIM プロビジョニング トークン)** をコピーし、テキスト エディターに貼り付けます。 これらは手順 5 で必要になります。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Atmos を追加する

Microsoft Entra アプリケーション ギャラリーから Atmos を追加して、Atmos へのプロビジョニングの管理を開始します。 SSO のために Atmos を既に設定している場合は、その同じアプリケーションを使用できます。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Atmos への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID におけるユーザーまたはグループの割り当てに基づいて Atmos のユーザーやグループを作成、更新、無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで Atmos の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくともアプリ所有者または [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーション リストで、**[Atmos]** を選択します。

    [Image: アプリケーション リストの Atmos リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Atmos テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Atmos に接続できることを確認します。 接続に失敗した場合は、Atmos アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Atmos に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Atmos のユーザー アカウントの照合に使用されます。 [照合対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合、その属性に基づいたユーザーのフィルター処理を Atmos API がサポートしているか確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Atmos で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  | ✓ |
    | emails[type eq "work"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | externalId | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
12. グループを選択 **します**。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Atmos に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Atmos のグループの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Atmos で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
    | externalId | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/atomiclearning-tutorial"} -->
## Microsoft Entra ID で Atomic Learning for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/atomiclearning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Atomic Learning 間にシングル サインオンを構成する方法について学習します。

この記事では、Atomic Learning と Microsoft Entra ID を統合する方法について説明します。 Atomic Learning を Microsoft Entra ID と統合すると、次のことができます。

- Atomic Learning にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Atomic Learning に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Atomic Learning でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Atomic Learning では、 **SP** Initiated SSO がサポートされます。
- Atomic Learning では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Atomic Learning を追加する

Microsoft Entra ID への Atomic Learning の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Atomic Learning を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Atomic Learning**」と入力します。
4. 結果パネルから **Atomic Learning** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Atomic Learning 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Atomic Learning に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Atomic Learning の関連ユーザーとの間にリンク関係を確立する必要があります。

Atomic Learning に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Atomic Learning の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Atomic Learning テストユーザーの作成** - Microsoft Entra の B.Simon にリンクする Atomic Learning の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Atomic Learning**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://secure2.atomiclearning.com/sso/shibboleth/<companyname>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、 [Atomic Learning クライアント サポート チーム](mailto:cs@atomiclearning.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Atomic Learning のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Atomic Learning SSO を構成する

**Atomic Learning** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Atomic Learning サポート チーム](mailto:cs@atomiclearning.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Atomic Learning のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Atomic Learning に作成します。 Atomic Learning では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Atomic Learning にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Atomic Learning のサインオン URL にリダイレクトされます。
- Atomic Learning のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Atomic Learning] タイルを選択すると、このオプションは Atomic Learning のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/atp-spotlight-and-chronicx-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ATP SpotLight と ChronicX を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/atp-spotlight-and-chronicx-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ATP SpotLight and ChronicX の間でシングル サインオンを構成する方法について説明します。

この記事では、ATP SpotLight と ChronicX を Microsoft Entra ID と統合する方法について説明します。 ATP SpotLight and ChronicX を Microsoft Entra ID と統合すると、次のことが可能になります。

- ATP SpotLight and ChronicX にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ATP SpotLight and ChronicX に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ATP SpotLight and ChronicX でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ATP SpotLight and ChronicX では、**SP** によって開始される SSO がサポートされます。
- ATP SpotLight and ChronicX では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ATP SpotLight and ChronicX の追加

Microsoft Entra ID への ATP SpotLight and ChronicX の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ATP SpotLight and ChronicX を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ATP SpotLight and ChronicX**」と入力します。
4. 結果のパネルから **[ATP SpotLight and ChronicX]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ATP SpotLight and ChronicX 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ATP SpotLight and ChronicX に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ATP SpotLight and ChronicX の関連ユーザーとの間にリンク関係を確立する必要があります。

ATP SpotLight and ChronicX に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ATP SpotLight and ChronicX の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ATP SpotLight と ChronicX のテストユーザーを作成する** - ATP SpotLight と ChronicX で Microsoft Entra のユーザー B.Simon にリンクされた対応者を作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ATP SpotLight and ChronicX]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `urn:amazon:cognito:sp:ca-central-1_ELozbwSTo`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://atpprod.auth.ca-central-1.amazoncognito.com/saml2/idpresponse`

    c. **[サインオン URL]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://sandbox.<AppDomain>.com` |
    | `<CustomerSSOName>` |
    | `https://<CustomerName>.<AppDomain>.com/` |

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには、[ATP SpotLight and ChronicX クライアント サポート チーム](mailto:support@atp.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. ATP SpotLight and ChronicX アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、ATP SpotLight and ChronicX アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 組織コード | &lt;`organizationcode`&gt; |
    | オーガニゼーションネーム | &lt;`organizationname`&gt; |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[ATP SpotLight and ChronicX のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ATP SpotLight and ChronicX の SSO の構成

**ATP SpotLight and ChronicX** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [ATP SpotLight and ChronicX サポート チーム](mailto:support@atp.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ATP SpotLight and ChronicX のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを ATP SpotLight and ChronicX に作成します。 ATP SpotLight and ChronicX では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ATP SpotLight and ChronicX にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる ATP SpotLight と ChronicX のサインオン URL にリダイレクトされます。
- ATP SpotLight and ChronicX のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ATP SpotLight and ChronicX] タイルを選択すると、このオプションは ATP SpotLight と ChronicX のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/attendancemanagementservices-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Attendance Management Services を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/attendancemanagementservices-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Attendance Management Services の間でシングル サインオンを構成する方法について説明します。

この記事では、Attendance Management Services と Microsoft Entra ID を統合する方法について説明します。 Attendance Management Services と Microsoft Entra ID を統合すると、次のことができます。

- Attendance Management Services にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Attendance Management Services に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Attendance Management Services でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Attendance Management Services では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Attendance Management Services の追加

Microsoft Entra ID への Attendance Management Services の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Attendance Management Services を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに **[Attendance Management Services]** と入力します。
4. 結果のパネルから **[Attendance Management Services]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Attendance Management Services 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Attendance Management Services に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Attendance Management Services の関連ユーザーとの間にリンク関係を確立する必要があります。

Attendance Management Services に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Attendance Management Services SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **出席管理サービスのテストユーザーの作成** - 出席管理サービスでB.Simon の対応ユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Attendance Management Services**&gt;**Single サインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://id.obc.jp/<TENANT_INFORMATION>/`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://id.obc.jp/<TENANT_INFORMATION>/`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Attendance Management Services クライアント サポート チーム](https://www.obcnet.jp/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Attendance Management Services のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Attendance Management Services SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として Attendance Management Services 企業サイトにサインオンします。
2. [**セキュリティ管理] セクション**で [**SAML 認証**] を選択します。

    [Image: このスクリーンショットは、非ラテン文字が使用されたページで [SAML 認証] が選択されている状態を示しています。]
3. 次の手順を実行します。

    [Image: このスクリーンショットは、この手順で説明されているタスクを実行できるウィンドウを示しています。]

    ある。 **[SAML 認証を利用する]** を選択します。

    b。 **[Identifier] (識別子)** テキストボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    c. **[認証エンドポイント URL]** テキストボックスに、**[ログイン URL]** の値を貼り付けます。

    d. [ **ファイルの選択] を選択** して、Microsoft Entra ID からダウンロードした証明書をアップロードします。

    え **[パスワード認証を無効する]** を選択します。

    f. [ **登録**] を選択します。

#### Attendance Management Services テスト ユーザーの作成

Microsoft Entra ユーザーが Attendance Management Services にサインインできるようにするには、Attendance Management Services にプロビジョニングする必要があります。 Attendance Management Services の場合、プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. 管理者として Attendance Management Services 企業サイトにサインインします。
2. [セキュリティ管理] セクションの [ **ユーザー** 管理] を **選択します**。

    [Image: このスクリーンショットは、非ラテン文字が使用されたページで [利用者管理] が選択されている状態を示しています。]
3. [ **新しい規則のログイン**] を選択します。

    [Image: このスクリーンショットは、プラス記号オプションの選択を示しています。]
4. **[OBCiD 情報]** セクションで、次の手順を実行します。

    [Image: このスクリーンショットは、説明されているタスクを実行できるウィンドウを示しています。]

    ある。 **[OBCiD]** ボックスに、ユーザーのメール アドレスを入力します (例: `BrittaSimon@contoso.com`)。

    b。 [ **パスワード** ] ボックスに、ユーザーのパスワードを入力します。

    c. **登録**を選択

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Attendance Management Services のサインオン URL にリダイレクトされます。
- Attendance Management Services のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Attendance Management Services] タイルを選択すると、Attendance Management Services のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/auditboard-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に AuditBoard を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/auditboard-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-26
- Summary: Microsoft Entra IDから AuditBoard にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために AuditBoard と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーは [AuditBoard](https://www.auditboard.com/) に自動的にプロビジョニングおよび解除されます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- AuditBoard でユーザーを作成する
- accessが不要になった場合に AuditBoard のユーザーを削除する
- Microsoft Entra IDと AuditBoard の間でユーザー属性の同期を維持する
- AuditBoard への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/auditboard-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AuditBoard サイト (ライブ)。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとAuditBoardの間で[マップするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように AuditBoard を構成する

1. AuditBoard にログインします。 **設定**&gt;**ユーザーとロール**&gt;**Security**&gt;**SCIM** に移動します。
2. [ **トークンの生成]** を選択します。
3. **[トークン]** と **[SCIM ベース URL]** を保存します。 これらの値は、AuditBoard アプリケーションの [プロビジョニング] タブの [テナント URL] フィールドと [シークレット トークン] フィールドに入力されます。

    注

    新しいトークンを生成すると、前のトークンが無効になります。
4. AuditBoard インスタンスでは、SCIM ユーザー ロール (既定ではシステム管理者) に次のユーザーアクセス許可を設定する必要があります。 AuditBoard サポートに接続して、これにより `user:action.administer must be set to allow` および `user:action.edit must be set to allow` が正しく設定されていることを確認してください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから AuditBoard を追加する

Microsoft Entra アプリケーション ギャラリーから AuditBoard を追加して、AuditBoard へのプロビジョニングの管理を開始します。 SSO のために AuditBoard を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: AuditBoard への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づき、AuditBoardでユーザーやグループを作成、更新、無効化するためのMicrosoft Entraプロビジョニングサービスの構成手順について説明します。

#### Microsoft Entra IDで AuditBoard の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくともアプリ所有者または [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[AuditBoard]** を選択します。

    [Image: アプリケーションの一覧の AuditBoard リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、AuditBoard テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが AuditBoard に接続できることを確認します。 接続に失敗した場合は、AuditBoard アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから AuditBoard に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で AuditBoard のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が AuditBoard API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | emails[type eq "work"].value | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | ユーザー名 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
12. グループを選択 **します**。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから AuditBoard に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で AuditBoard のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/auditboard-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に AuditBoard を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/auditboard-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AuditBoard の間でシングル サインオンを構成する方法について説明します。

この記事では、AuditBoard と Microsoft Entra ID を統合する方法について説明します。 AuditBoard と Microsoft Entra ID を統合すると、次のことができます。

- AuditBoard にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して AuditBoard に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AuditBoard でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- AuditBoard では、**SP および IDP によるシングルサインオン (SSO)** がサポートされます。
- AuditBoard では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/auditboard-provisioning-tutorial)。

### ギャラリーから AuditBoard を追加する

Microsoft Entra ID への AuditBoard の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に AuditBoard を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「AuditBoard**」と入力します。
4. 結果パネルから **AuditBoard** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AuditBoard の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、AuditBoard に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと AuditBoard の関連ユーザーとの間にリンク関係を確立する必要があります。

AuditBoard に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AuditBoard SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AuditBoard テスト ユーザーの作成** - AuditBoard で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**AuditBoard**&gt;**シングルサインオン** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP 開始** モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.auditboardapp.com/api/v1/sso/saml/metadata.xml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.auditboardapp.com/api/v1/sso/saml/assert`

    c. **SP 開始**モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    d. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.auditboardapp.com/`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、AuditBoard クライアント サポート チーム  にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AuditBoard SSO の構成

**AuditBoard** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[AuditBoard サポート チーム](mailto:support@auditboard.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### AuditBoard テスト ユーザーの作成

このセクションでは、AuditBoard で Britta Simon というユーザーを作成します。 AuditBoard サポート チーム  と連携して、AuditBoard プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

AuditBoard では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる AuditBoard サインオン URL にリダイレクトされます。
- AuditBoard のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した AuditBoard に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで AuditBoard タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した AuditBoard に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/authenion-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Authenion を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/authenion-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-02
- Summary: Microsoft Entra ID と Heroku の間にシングル サインオンを構成する方法について説明します。

この記事では、Authenion と Microsoft Entra ID を統合する方法について説明します。 Authenion を Microsoft Entra ID と統合すると、次のことが可能になります。

- Microsoft Entra ID を使用して、Authenion にアクセスできるユーザーを制御します。
- ユーザーが 自分のMicrosoft Entra アカウントを使用して Authenion に自動的にサインインできるようにします。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Authenion シングル サインオン (SSO) 対応サブスクリプション。

### ギャラリーから Authenion を追加する

Authenion と Microsoft Entra ID の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Authenion を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Authenion**」と入力します。
4. 結果パネルで **[Authenion** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Authenion**&gt;**シングルサインオンに移動します。**
3. 次のセクションで以下の手順を実行します。

    1. [ **アプリケーションに移動] を**選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID を**コピーし、後で Authenion 側の構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
    3. [ **エンドポイント** ] タブで、 **OpenID Connect メタデータ ドキュメント** リンクをコピーし、後で Authenion 側の構成で使用します。

        [Image: タブにエンドポイントが表示されているスクリーンショット。]
4. 左側のメニューの [ **認証** ] タブに移動し、次の手順を実行します。

    1. [ **リダイレクト URI** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<HOST_NAME>/ssolibrary/oidc/callback`

        [Image: リダイレクト値を示すスクリーンショット。]
    2. [ **構成] ボタンを** 選択します。
5. 左側のメニューの **[証明書とシークレット** ] に移動し、次の手順を実行します。

    1. [ **クライアント シークレット** ] タブに移動し、[ **+新しいクライアント シークレット**] を選択します。
    2. テキストボックスに有効な **説明** を入力し、要件に従ってドロップダウンから **[有効期限** 日] を選択し、[ **追加**] を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、 **値** が生成されます。 値をコピーして、後で Authenion 側の構成で使用します。

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

このセクションでは、Authenion へのアクセスを許可して、B.Simon がシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Authenion** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**追加された割り当て]** ダイアログで **[ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. [ **追加された割り当て** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Authenion SSO の構成

**Authenion** 側で OAuth/OIDC フェデレーションのセットアップを完了するには、テナント ID、アプリケーション ID、クライアント シークレットなどのコピーされた値を Microsoft Entra から [Authenion サポート チーム](mailto:eiksupport@likemindsconsulting.com)に送信する必要があります。 サポート チームはこれを設定して、OIDC 接続が両方の側で正しく設定されるようにします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/authomize-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Authomize を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/authomize-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Authomize の間のシングル サインオンを構成する方法について説明します。

この記事では、Authomize と Microsoft Entra ID を統合する方法について説明します。 Authomize と Microsoft Entra ID を統合すると、次のことができます。

- Authomize にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Authomize に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Authomize でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Authomize では、**SP および IDP によって開始される SSO** がサポートされます。
- Authomize では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Authomize の追加

Microsoft Entra ID への Authomize の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Authomize を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Authomize**」と入力します。
4. 結果パネルから **[Authomize]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Authomize 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Authomize に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Authomize の関連ユーザーとの間にリンク関係を確立する必要があります。

Authomize に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Authomize SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Authomize テスト ユーザーの作成** - Authomize で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせるためです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Authomize]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.authomize.com/api/sso/metadata.xml?domain=<DOMAIN>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.authomize.com/api/sso/assert?domain=<DOMAIN>`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.authomize.com`

    b。 [ **リレー状態** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.authomize.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、リレー状態 URL でこれらの値を更新します。 これらの値を取得するには、 [Authomize クライアント サポート チーム](mailto:support@authomize.com) に問い合わせてください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
7. **[保存] を選択します**。
8. Authomize アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットは、Authomize アプリケーション イメージを示しています。]
9. その他に、Authomize アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユーザーID | ユーザーのメールアドレス |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
11. [ **Authomize のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Authomize の SSO の設定

1. Authomize 企業サイトに管理者としてログインします。
2. **[設定]** (歯車アイコン) &gt;**SSO** に移動します。
3. [ **SSO 設定]** ページで、次の手順を実行します。

    [Image: [構成設定] を示すスクリーンショット。]

    a. [ **SSO を有効にする]** チェック ボックスをオンにします。

    b。 **[タイトル**] ボックスに有効な名前を入力します。

    c. テキスト ボックスに **メール ドメイン** を入力します。

    d. **ID プロバイダーの SSO URL** ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    e. ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容を **[パブリック x509 証明書** ] ボックスに貼り付けます。

    f. [ **構成の保存] を選択します**。

#### Authomize のテスト ユーザーの作成

このセクションでは、Authomize で B. Simon というユーザーを作成します。 Authomize では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定では有効になっています。 このセクションにはアクション項目はありません。 Authomize にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Authomize のサインオン URL にリダイレクトされます。
- Authomize のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Authomize に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Authomize] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Authomize に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/autodesk-sso-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Autodesk SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/autodesk-sso-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-26
- Summary: Microsoft Entra IDから Autodesk SSO にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Autodesk SSO と Microsoft Entra ID の両方で自動ユーザープロビジョニングを構成するために必要な手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループが[Autodesk SSO](https://autodesk.com/) に自動的にプロビジョニングおよびプロビジョニング解除されます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Autodesk SSO でユーザーを作成します。
- ユーザーのアクセスが不要になった場合は、Autodesk SSO からユーザーを削除します。
- Microsoft Entra IDと Autodesk SSO の間でユーザー属性の同期を維持します。
- Autodesk SSO でグループとグループ メンバーシップをプロビジョニングします。
- Autodesk SSO [への](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/autodesk-sso-tutorial)シングル サインオン (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Autodesk 管理ポータル](https://manage.autodesk.com/)にアクセスするためのプライマリ管理者またはSSO管理者のロールを持つユーザーアカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra IDとAutodesk SSOの間でマップするデータ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Autodesk SSO を構成する

1. [Autodesk 管理ポータル](https://manage.autodesk.com/)にサインインします。
2. 左側のナビゲーション メニューから、[グループ別の**ユーザー管理] に&gt;**移動します。 ドロップダウン リストから必要なチームを選択し、チーム設定の歯車アイコンを選択します。

    [Image: ナビゲーション]
3. [ディレクトリ同期のセットアップ] ボタンを選択し、ディレクトリ環境として Microsoft Entra SCIM を選択します。 [次へ] を選択して、Azure管理者の資格情報をaccessします。 以前にディレクトリ同期を設定した場合は、代わりにAccess資格情報を選択します。

    [Image: ディレクトリ同期のセットアップ]
4. ベース URL と API トークンをコピーして保存します。 これらの値は、Autodesk アプリケーションの [プロビジョニング] タブの [テナント URL] フィールドと [シークレット トークン] フィールドにそれぞれ入力されます。

    [Image: 資格情報の取得]

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Autodesk SSO を追加する

Microsoft Entra アプリケーション ギャラリーから Autodesk SSO を追加して、Autodesk SSO へのプロビジョニングの管理を開始します。 SSO のために Autodesk SSO を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Autodesk SSO への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーまたはグループの割り当てに基づき、Autodesk SSO でユーザーやグループを作成、更新、無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで Autodesk SSO の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくともアプリ所有者または [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Autodesk SSO]** を選択します。

    [Image: アプリケーションの一覧の Autodesk SSO リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Autodesk SSO テナント URL とシークレット トークンを入力します。 **Test Connection** を選択してMicrosoft Entra ID Autodesk SSO に接続できることを確認します。 接続に失敗した場合は、Autodesk SSO アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Autodesk SSO に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Autodesk SSO のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Autodesk SSO API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Autodesk SSO で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:AdskUserExt:2.0:User:objectGUID | 糸 |  | ✓ |
12. グループを選択 **します**。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Autodesk SSO に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Autodesk SSO のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Autodesk SSO で必須 |
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

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/autodesk-sso-tutorial"} -->
## Microsoft Entra ID で Autodesk SSO for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/autodesk-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Autodesk SSO 間のシングル サインオンを構成する方法について説明します。

この記事では、Autodesk SSO と Microsoft Entra ID を統合する方法について説明します。 Autodesk SSO を Microsoft Entra ID と統合すると、次のことが可能になります。

- Autodesk SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Autodesk SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Autodesk SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Autodesk SSO では、**SP** Initiated SSO がサポートされます。
- Autodesk SSO では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Autodesk SSO では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/autodesk-sso-provisioning-tutorial)。

### ギャラリーからの Autodesk SSO の追加

Microsoft Entra ID への Autodesk SSO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Autodesk SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Autodesk SSO**」と入力します。
4. 結果のパネルから **[Autodesk SSO]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Autodesk SSO 用に Microsoft Entra SSO を構成してテストする

**B. Simon** というテスト ユーザーを使用して、Autodesk SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Autodesk SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Autodesk SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Autodesk SSO の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Autodesk SSO テスト ユーザーの作成** - Autodesk SSO での B.Simon の対応ユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Autodesk SSO**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.okta.com/saml2/service-provider/<UNIQUE_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://autodesk-prod.okta.com/sso/saml2/<UNIQUE_ID>`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://autodesk-prod.okta.com/sso/saml2/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 この値を取得するには、[Autodesk SSO クライアント サポート チーム](https://knowledge.autodesk.com/contact-support)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Autodesk SSO アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングをご自分の SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. 上記に加えて、Autodesk SSO では、いくつかの追加の属性が SAML 応答で返されることも想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | objectGUID（オブジェクトGUID） | user.objectid (ユーザーのオブジェクトID) |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Autodesk SSO のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Autodesk SSO の構成

**Autodesk SSO** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Autodesk SSO サポート チーム](https://knowledge.autodesk.com/contact-support)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Autodesk SSO のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Autodesk SSO に作成します。 Autodesk SSO では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Autodesk SSO にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

Autodesk SSO をテストするには、Autodesk コンソールを開き、[ **テスト接続** ] ボタンを選択し、「 **Microsoft Entra テスト ユーザーの作成** 」セクションで作成したテスト アカウントを使用して認証します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/autotaskendpointbackup-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Autotask Endpoint Backup を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/autotaskendpointbackup-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Autotask Endpoint Backup の間でシングル サインオンを構成する方法について説明します。

この記事では、Autotask Endpoint Backup と Microsoft Entra ID を統合する方法について説明します。 Autotask Endpoint Backup を Microsoft Entra ID と統合すると、次のことができます。

- Autotask Endpoint Backup にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Autotask Endpoint Backup に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Autotask Endpoint Backup でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Autotask Endpoint Backup では、 **IDP** によって開始される SSO がサポートされます。

### ギャラリーからの Autotask Endpoint Backup の追加

Microsoft Entra ID への Autotask Endpoint Backup の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Autotask Endpoint Backup を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Autotask Endpoint Backup**」と入力します。
4. 結果パネルから **[Autotask Endpoint Backup** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Autotask Endpoint Backup 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Autotask Endpoint Backup に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Autotask Endpoint Backup の関連ユーザーの間で、リンク関係を確立する必要があります。

Autotask Endpoint Backup に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Autotask Endpoint Backup の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Autotask Endpoint Backup テスト ユーザーの作成** - Autotask Endpoint Backup で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Autotask Endpoint Backup**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.backup.autotask.net/singlesignon/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.backup.autotask.net/singlesignon/saml/SSO`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Autotask Endpoint Backup クライアント サポート チーム](https://backup.autotask.net/help/Content/0_HOME/Support_for_End_Clients.htm) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Autotask Endpoint Backup のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Autotask Endpoint Backup の SSO の構成

**Autotask Endpoint Backup** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Autotask Endpoint Backup サポート チーム](https://backup.autotask.net/help/Content/0_HOME/Support_for_End_Clients.htm)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Autotask Endpoint Backup のテスト ユーザーの作成

このセクションでは、Autotask Endpoint Backup で Britta Simon というユーザーを作成します。 [Autotask Endpoint Backup サポート チーム](https://backup.autotask.net/help/Content/0_HOME/Support_for_End_Clients.htm)と協力して、Autotask Endpoint Backup プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Autotask Endpoint Backup に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Autotask Endpoint Backup] タイルを選択すると、SSO を設定した Autotask Endpoint Backup に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/avionte-bold-saml-federated-sso-tutorial"} -->
## Microsoft Entra ID でのシングル サインオンのために、「Avionte Bold SAML Federated SSO」を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/avionte-bold-saml-federated-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Avionte Bold SAML Federated SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、Avionte Bold SAML Federated SSO と Microsoft Entra ID を統合する方法について説明します。 Avionte は、人材紹介業界向けの人材紹介および採用ソフトウェア ソリューションを提供しています。 Avionte Bold SAML Federated SSO と Microsoft Entra ID を統合すると、次のことが可能になります。

- Avionte Bold SAML Federated SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Avionte Bold SAML Federated SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Avionte Bold SAML Federated SSO の Microsoft Entra シングル サインオンを構成してテストします。 Avionte Bold SAML Federated SSO では、 **SP** によって開始されるシングル サインオンがサポートされます。

### [前提条件]

Microsoft Entra ID と Avionte Bold SAML Federated SSO を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Avionte Bold SAML Federated SSO のシングル サインオン (SSO) に対応したサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Avionte Bold SAML Federated SSO アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Avionte Bold SAML Federated SSO を追加する

Microsoft Entra アプリケーション ギャラリーから Avionte Bold SAML Federated SSO を追加して、Avionte Bold SAML Federated SSO のシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Avionte Bold SAML Federated SSO]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:avionte:<CustomerEnvironment>-federated-saml-sso`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.myavionte.com/login/callback?connection=<CustomerEnvironment>-federated-saml-sso`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.myavionte.com/login/callback?connection=<CustomerEnvironment>-federated-saml-sso`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Avionte Bold SAML Federated SSO サポート チーム](mailto:Support@avionte.com) に問い合わせてください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Avionte Bold SAML Federated SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Avionte Bold SAML Federated SSO を構成する

**Avionte Bold SAML Federated SSO** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Avionte Bold SAML Federated SSO サポート チームに](mailto:Support@avionte.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Avionte Bold SAML Federated SSO のテスト ユーザーを作成する

このセクションでは、Avionte Bold SAML Federated SSO で Britta Simon というユーザーを作成します。 [Avionte Bold SAML Federated SSO サポート チーム](mailto:Support@avionte.com)と協力して、Avionte Bold SAML Federated SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Avionte Bold SAML Federated SSO サインオン URL にリダイレクトされます。
- Avionte Bold SAML Federated SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Avionte Bold SAML Federated SSO] タイルを選択すると、このオプションは Avionte Bold SAML Federated SSO のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/awardspring-tutorial"} -->
## Microsoft Entra ID で AwardSpring for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/awardspring-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AwardSpring の間のシングル サインオンを構成する方法について説明します。

この記事では、AwardSpring と Microsoft Entra ID を統合する方法について説明します。 AwardSpring を Microsoft Entra ID と統合すると、次のことが可能になります。

- AwardSpring にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで AwardSpring に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AwardSpring のシングル サインオン (SSO) に対応したサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AwardSpring では、**SP および IDP** Initiated SSO がサポートされます。
- AwardSpring では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから AwardSpring を追加する

AwardSpring と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に AwardSpring をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「AwardSpring**」と入力します。
4. 結果パネルから **AwardSpring** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AwardSpring の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、AwardSpring に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、AwardSpring での関連ユーザーとの間にリンク関係を確立する必要があります。

AwardSpring の Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AwardSpring SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AwardSpring のテスト ユーザーを作成する** - AwardSpring で B.Simon に対応するユーザーを作成し、Microsoft Entra でのユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AwardSpring**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.awardspring.com/SignIn/SamlMetaData`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.awardspring.com/SignIn/SamlAcs`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.awardspring.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [AwardSpring クライアント サポート チーム](mailto:support@awardspring.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. AwardSpring アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、AwardSpring アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名前 | User.givenname |
    | 姓 | ユーザーの名字 |
    | Email | ユーザーのメールアドレス |
    | ユーザー名 | user.userprincipalname |
    | 学籍番号 | &lt; 学生 ID &gt; |

    注

    StudentID 属性は、要求に渡す必要がある実際の学生 ID にマッピングされます。 この値を取得するには、 [AwardSpring クライアント サポート チーム](mailto:support@awardspring.com) にお問い合わせください。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **AwardSpring のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AwardSpring の SSO の構成

**AwardSpring** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [AwardSpring サポート チーム](mailto:support@awardspring.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### AwardSpring のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを AwardSpring に作成します。 AwardSpring では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 AwardSpring にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [AwardSpring サポート チーム](mailto:support@awardspring.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる AwardSpring のサインオン URL にリダイレクトされます。
- AwardSpring のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した AwardSpring に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで AwardSpring タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した AwardSpring に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/awarego-tutorial"} -->
## Microsoft Entra ID で AwareGo for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/awarego-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AwareGo の間でシングル サインオンを構成する方法について説明します。

この記事では、AwareGo と Microsoft Entra ID を統合する方法について説明します。 AwareGo と Microsoft Entra ID を統合すると、次のことができます。

- AwareGo にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して AwareGo に自動的にサインインできるようにします。
- 1 つの中央の場所 (Azure portal) でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AwareGo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。 AwareGo では、サービス プロバイダー (SP) によって開始される SSO がサポートされます。

### ギャラリーからの AwareGo の追加

Microsoft Entra ID への AwareGo の統合を構成するには、ギャラリーから管理対象サービスとしてのソフトウェア (SaaS) アプリの一覧に AwareGo を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「AwareGo」**と入力します。
4. 結果ウィンドウで、AwareGo 選択し、アプリを追加します。 数秒で、アプリがテナントに追加されます。

### AwareGo の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、AwareGo に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと AwareGo の関連ユーザーとの間にリンク関係を確立する必要があります。

AwareGo に対する Microsoft Entra SSO を構成してテストするには、次の操作を行います。

1. ユーザーがこの機能を使用できるように **Microsoft Entra SSO を構成**します。

    a. **Microsoft Entra テスト ユーザーを作成** して、ユーザー B.Simon で Microsoft Entra のシングル サインオンをテストします。 b。 **Microsoft Entra テスト ユーザーを割り当てて** 、ユーザー B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AwareGo SSO を構成** して、アプリケーション側でシングル サインオン設定を構成します。

    a. **AwareGo のテスト ユーザーの作成** - AwareGo で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクさせます。 b。 **SSO をテスト** して、構成が機能することを確認します。

### Microsoft Entra SSO の構成

Azure portal で Microsoft Entra SSO を有効にするには、次の操作を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AwareGo** アプリケーション統合ページの [**管理**] で、**シングル サインオン**を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. 設定を編集するには、[SAML を **使用した単一 Sign-On の設定** ] ウィンドウで [ **編集** ] ボタンを選択します。

    [Image: 基本的な SAML 構成の [編集] ボタンのスクリーンショット。]
5. 編集ウィンドウの [基本的な SAML 構成 で、次の操作を行います。

    a. [ **サインオン URL** ] ボックスに、次のいずれかの URL を入力します。

    - `https://lms.awarego.com/auth/signin/`
    - `https://my.awarego.com/auth/signin/`

    b。 [ **識別子 (エンティティ ID)]** ボックスに、URL を次の形式で入力します。 `https://<SUBDOMAIN>.awarego.com`

    c. [ **応答 URL** ] ボックスに、次の形式で URL を入力します。 `https://<SUBDOMAIN>.awarego.com/auth/sso/callback`

    手記

    上記の値は実際の値ではありません。 実際の識別子と応答 URL で更新します。 値を取得するには、 [AwareGo クライアント サポート チーム](mailto:support@awarego.com)にお問い合わせください。 「 **基本的な SAML 構成** 」セクションの例を参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションの [ **証明書 (Base64)] の**横にある [ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [SAML 署名証明書] ウィンドウの証明書の [ダウンロード] リンクのスクリーンショット。]
7. [ **AwareGo のセットアップ** ] セクションで、要件に応じて 1 つ以上の URL をコピーします。

    [Image: 構成 URL をコピーするための [AwareGo のセットアップ] ウィンドウのスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AwareGo SSO の構成

**AwareGo** 側でシングル サインオンを構成するには、先ほどダウンロードした**証明書 (Base64)** 証明書と、先ほどコピーした URL を [AwareGo サポート チーム](mailto:support@awarego.com)に送信します。 サポート チームは、両方の側で SAML SSO 接続を正しく確立するために、この設定を作成します。

#### AwareGo テスト ユーザーの作成

このセクションでは、AwareGo で Britta Simon というユーザーを作成します。 [AwareGo サポート チーム](mailto:support@awarego.com)と協力して、AwareGo プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のいずれかの操作を行って、Microsoft Entra のシングル サインオン構成をテストできます。

- Azure portal で、[このアプリケーションをテスト ] を選択します。 これにより、AwareGo サインイン ページにリダイレクトされ、サインイン フローを開始できます。
- AwareGo サインイン ページに直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリに移動します。 マイ アプリで **[AwareGo** ] タイルを選択すると、AwareGo サインイン ページにリダイレクトされます。 詳細については、「 [マイ アプリ ポータルからアプリにサインインして起動する」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/aws-clientvpn-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AWS ClientVPN を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aws-clientvpn-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-09
- Summary: Microsoft Entra ID と AWS ClientVPN 間のシングル サインオンを構成する方法について説明します。

この記事では、AWS ClientVPN と Microsoft Entra ID を統合する方法について説明します。 AWS ClientVPN を Microsoft Entra ID と統合すると、次のことが可能になります。

- AWS ClientVPN にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで AWS ClientVPN に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AWS ClientVPN でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AWS ClientVPN では、**SP** 開始の SSO がサポートされます。
- AWS ClientVPN では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの AWS ClientVPN の追加

Microsoft Entra ID への AWS ClientVPN の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に AWS ClientVPN を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**AWS ClientVPN**」と入力します。
4. 結果のパネルから **[AWS ClientVPN]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AWS ClientVPN に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、AWS ClientVPN に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと AWS ClientVPN の関連ユーザー間にリンク関係を確立する必要があります。

AWS ClientVPN に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AWS ClientVPN の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AWS ClientVPN のテスト ユーザーの作成** - AWS ClientVPN で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、以下の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**AWS ClientVPN**&gt;**シングルサインオンを使用する**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、以下の手順を実行します。

    a. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<LOCALHOST>`

    b。 **[応答 URL]** ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | `http://<LOCALHOST>` |
    | `https://self-service.clientvpn.amazonaws.com/api/auth/sso/saml` |
    |  |

    Note

    これらの値は実際の値ではありません。 実際のサインオン URL と応答 URL でこれらの値を更新してください。 サインオン URL と応答 URL の値は同じでもかまいません (`http://127.0.0.1:35001`)。 詳細については、[AWS Client VPN のドキュメント](https://docs.aws.amazon.com/vpn/latest/clientvpn-admin/client-authentication.html#ad)を参照してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。 構成の問題については、[AWS ClientVPN サポート チーム](https://aws.amazon.com/contact-us/)にお問い合わせください。
6. AWS ClientVPN アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
7. 上記に加えて、AWS ClientVPN アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | memberOf | ユーザー.グループ |
    | ファーストネーム | User.givenname |
    | LastName | User.surname |
8. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
9. [ **SAML 署名証明書** ] セクションで、編集アイコンを選択し、 **署名オプション** を変更して **SAML 応答とアサーションに署名**します。 **保存** を選択します。
10. **[AWS ClientVPN のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AWS ClientVPN の SSO の構成

[リンク](https://docs.aws.amazon.com/vpn/latest/clientvpn-admin/client-authentication.html#federated-authentication)に記載されている手順に従って、AWS ClientVPN 側でシングル サインオンを構成します。

#### AWS ClientVPN のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを AWS ClientVPN に作成します。 AWS ClientVPN では、Just-In-Time ユーザー プロビジョニングがサポートされており、これは既定で有効になっています。 このセクションにはアクション項目はありません。 AWS ClientVPN にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる AWS ClientVPN サインオン URL にリダイレクトされます。
- AWS ClientVPN のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイアプリで AWS ClientVPN タイルを選択すると、このオプションは AWS ClientVPN のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/aws-single-sign-on-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザープロビジョニング用に AWS IAM Identity Center (AWS シングル サインオンの後継) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aws-single-sign-on-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra IDから AWS IAM Identity Center に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、AWS IAM Identity Center (AWS シングル サインオンの後継) と、自動ユーザー プロビジョニングを構成するためにMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成されたMicrosoft Entra IDは、Microsoft Entraプロビジョニングサービスを使用して、ユーザーとグループを[AWS IAM Identity Center](https://console.aws.amazon.com/singlesignon)に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- AWS IAM Identity Center でユーザーを作成する
- ACCESSが不要になった場合に AWS IAM Identity Center でユーザーを削除する
- Microsoft Entra IDと AWS IAM Identity Center の間でユーザー属性の同期を維持する
- AWS IAM Identity Center でグループとグループ メンバーシップをプロビジョニングする
- AWS IAM Identity Center への [AWS Single Sign-On ドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aws-single-sign-on-tutorial)
- 有効期間が長いベアラー トークン認証がサポートされています。

AWS IAM Identity Center は、次の [国内クラウドデプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ |  | ✅ |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aws-single-sign-on-tutorial)の説明に従って、Microsoft Entra アカウントから AWS IAM Identity Center への SAML 接続

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra IDと AWS IAM Identity Center](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes) の間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDを使用したプロビジョニングをサポートするように AWS IAM Identity Center を構成する

1. [AWS IAM Identity Center](https://console.aws.amazon.com/singlesignon) を開きます。
2. 左側のナビゲーション ペインで、 **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** を選択します。
3. **[設定]** で、[自動プロビジョニング] セクションで [有効] を選択します。

    [Image: 自動プロビジョニングを有効にする画面のスクリーンショット。]
4. [受信自動プロビジョニング] ダイアログ ボックスで、**SCIM エンドポイント**と**Access トークン**をコピーして保存します ([トークンの表示] を選択した後に表示されます)。 これらの値は、AWS IAM Identity Center アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。 [Image: プロビジョニング構成の抽出のスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから AWS IAM Identity Center を追加する

Microsoft Entra アプリケーション ギャラリーから AWS IAM Identity Center を追加して、AWS IAM Identity Center へのプロビジョニングの管理を開始します。 以前に AWS IAM Identity Center を SSO 用に設定している場合は、同じアプリケーションを使用できます。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: AWS IAM Identity Center への自動ユーザー プロビジョニングを構成する (AWS シングル サインオンの後継)

このセクションでは、Microsoft Entra IDのユーザーおよびグループの割り当てに基づいて、AWS IAM アイデンティティ センター (旧 AWS シングル サインオン) においてユーザーおよびグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで AWS IAM Identity Center の自動ユーザー プロビジョニング (AWS シングル サインオンの後継) を構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくともアプリ所有者または [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で、 **AWS IAM Identity Center (AWS シングル サインオンの後継)** を選択します。

    [Image: アプリケーションの一覧の AWS IAM Identity Center (AWS シングル サインオンの後継) リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、AWS IAM Identity Center (AWS シングル サインオンの後継) テナント URL とシークレット トークンを入力します。 **Test Connection** を選択してMicrosoft Entra IDが AWS IAM Identity Center に接続できることを確認します (AWS シングル サインオンの後継)。 接続に失敗した場合は、AWS IAM Identity Center (AWS シングル サインオンの後継) アカウントに必要な管理者アクセス許可があることを確認してから、やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから AWS IAM Identity Center (AWS シングル サインオンの後継) に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択された属性は、AWS IAM Identity Center (AWS シングル サインオンの後継) で更新操作のユーザー アカウントを照合するために使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、AWS IAM Identity Center (AWS シングル サインオンの後継) API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | タイトル | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 優先言語 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | externalId | 糸 |  |
    | ロケール | 糸 |  |
    | タイムゾーン | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |  |
12. グループを選択 **します**。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから AWS IAM Identity Center (AWS シングル サインオンの後継) に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択された属性は、AWS IAM Identity Center のグループとの照合 (AWS シングル サインオンの後継) で更新操作に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

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

### ジャストインタイム (JIT) アプリケーションのアクセスにおけるグループ用の PIM

PIM for Groups を使用すると、Amazon Web Services のグループに Just-In-Time accessを提供し、AWS の特権グループに永続的なaccessを持つユーザーの数を減らすことができます。

**SSO とプロビジョニング用にエンタープライズ アプリケーションを構成する**

1. AWS IAM Identity Center をテナントに追加し、上記の記事で説明したようにプロビジョニング用に構成し、プロビジョニングを開始します。
2. AWS IAM Identity Center の[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aws-single-sign-on-provisioning-tutorial)を構成します。
3. すべてのユーザーがアプリケーションにアクセスできるようにする [グループ](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-manage-groups) を作成します。
4. AWS IAM Identity Center アプリケーションにグループを割り当てます。
5. 前の手順で作成したグループの直接メンバーとしてテスト ユーザーを割り当てるか、access パッケージを使用してグループにaccessを提供します。 このグループは、AWS の永続的な非管理者accessに使用できます。

**グループの PIM を有効にする**

1. Microsoft Entra IDで 2 つ目のグループを作成します。 このグループは、AWS の管理者権限へのアクセスを提供します。
2. グループを Microsoft Entra PIM の [management](https://learn.microsoft.com/ja-jp/azure/active-directory/privileged-identity-management/groups-discover-groups) の下に移動>。
3. ロールがメンバーに設定されている [PIM のグループの対象](https://learn.microsoft.com/ja-jp/azure/active-directory/privileged-identity-management/groups-assign-member-owner)としてテスト ユーザーを割り当てます。
4. AWS IAM Identity Center アプリケーションに 2 番目のグループを割り当てます。
5. オンデマンド プロビジョニングを使用して、AWS IAM Identity Center でグループを作成します。
6. AWS IAM Identity Center にサインインし、2 番目のグループに管理タスクを実行するために必要なアクセス許可を割り当てます。

PIM でグループの対象となったエンド ユーザーは、グループ メンバーシップを[アクティブ化することで、AWS のグループに JIT access](https://learn.microsoft.com/ja-jp/azure/active-directory/privileged-identity-management/groups-activate-roles#activate-a-role)を取得できるようになりました。

**重要な考慮事項**

- ユーザーがアプリケーションにプロビジョニングされるまでにかかる時間
    - Microsoft Entra ID Privileged Identity Management (PIM) を使用してグループ メンバーシップをアクティブ化する以外のMicrosoft Entra IDのグループにユーザーが追加された場合:
        - グループ メンバーシップは、次の同期サイクルの間に、アプリケーションでプロビジョニングされます。 同期サイクルは 40 分ごとに実行されます。
    - ユーザーが Microsoft Entra ID の PIM でグループメンバーシップをアクティブ化する場合:
        - グループ メンバーシップは 2 から 10 分で設定されます。 一度に行われる要求の数が多い場合、10 秒あたり 5 要求に調整されます。
        - 特定のアプリケーションのグループ メンバーシップをアクティブにしようとするユーザーのうち、10 秒の期間内の最初の 5 人については、2 から 10 分以内にアプリケーションでグループ メンバーシップがプロビジョニングされます。
        - 特定のアプリケーションのグループ メンバーシップをアクティブにしようとするユーザーのうち、10 秒の期間内の 6 人目以降については、次の同期サイクルの間にアプリケーションでグループ メンバーシップがプロビジョニングされます。 同期サイクルは 40 分ごとに実行されます。 エンタープライズ アプリケーションごとに調整の制限が適用されます。
- ユーザーが AWS で必要なグループをaccessできない場合は、以下のトラブルシューティングのヒント、PIM ログ、プロビジョニング ログを確認して、グループ メンバーシップが正常に更新されたことを確認してください。 ターゲット アプリケーションの設計方法によっては、グループ メンバーシップがアプリケーションで有効になるのにさらに時間がかかる場合があります。
- [Azure Monitor](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics) を使用して、エラーのアラートを作成できます。
- 非アクティブ化は、通常の増分サイクル中に行われます。 オンデマンド プロビジョニングによってすぐには処理されません。

### トラブルシューティングのヒント

#### 不足している属性

ユーザーを AWS にプロビジョニングするときは、次の属性が必要です。

- ファーストネーム
- lastName
- displayName
- ユーザー名

これらの属性を持たないユーザーは、次のエラーで失敗します

[Image: errorcode]

#### 複数値属性

AWS では、次の複数値の属性はサポートされていません。

- メール
- 電話番号

上記を複数値属性としてフローしようとすると、次のエラー メッセージが表示されます

[Image: errorcode2]

これを解決するには、次の 2 つの方法があります。

1. ユーザーが持つ電話番号/電子メールの値が 1 つのみであることを確認します。
2. 重複する属性を削除します。 たとえば、2つの異なる属性がAWS側の"phoneNumber\_\_\_"にマップされ、両方の属性にMicrosoft Entra IDで値がある場合、エラーが発生します。 "phoneNumber\_\_\_" 属性にマップされている属性を 1 つのみにすると、エラーが解決されます。

#### 無効な文字

現在、AWS IAM Identity Center は、Microsoft Entra ID がサポートする特定の文字、例えばタブ (\t)、改行 (\n)、リターンキャリッジ (\r)、および "&lt;|&gt;|;|:%" などの文字を許可していません。

AWS IAM Identity Center のトラブルシューティングのヒント[こちら](https://docs.aws.amazon.com/singlesignon/latest/userguide/azure-ad-idp.html#azure-ad-troubleshooting)も確認できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/aws-single-sign-on-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AWS IAM Identity Center (AWS Single Sign-Onの後継) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aws-single-sign-on-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と AWS IAM Identity Center (AWS Single Sign-On の後継) の間でシングル サインオンを構成する方法について説明します。

この記事では、AWS IAM Identity Center (AWS Single Sign-Onの後継) を Microsoft Entra ID と統合する方法について説明します。 AWS IAM Identity Center と Microsoft Entra ID を統合すると、次のことができます。

- AWS IAM Identity Center にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して AWS IAM Identity Center に自動的にサインインできるようにします。
- アカウントを一元的に管理する。

**手記：** AWS 組織を使用する場合は、別のアカウントを Identity Center Administration アカウントとして委任し、そのアカウントで IAM Identity Center を有効にして、ルート管理アカウントではなく、そのアカウントに対する Entra ID SSO を設定することが重要です。 これにより、より安全で管理しやすいセットアップが保証されます。

AWS IAM Identity Center は、次の [国内クラウドデプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ |  | ✅ |

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 別のアカウントが Identity Center の管理アカウントとして委任された AWS Organizations のセットアップ。
- 委任された Identity Center の管理アカウントで有効になっている AWS IAM Identity Center。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

**手記：** 次の手順に進む前に、別のアカウントを Identity Center 管理アカウントとして委任し、そのアカウントで IAM Identity Center を有効にしていることを確認します。

- AWS IAM Identity Center では、**SP および IDP** による SSO の開始がサポートされます。
- AWS IAM Identity Center では、 [**自動ユーザー プロビジョニングがサポートされています**](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aws-single-sign-on-provisioning-tutorial)。

### ギャラリーから AWS IAM Identity Center を追加する

Microsoft Entra ID への AWS IAM Identity Center の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に AWS IAM Identity Center を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「AWS IAM Identity Center」と**入力します。
4. 結果パネルから **AWS IAM Identity Center を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### AWS IAM Identity Center 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、AWS IAM Identity Center に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと AWS IAM Identity Center の関連するユーザーの間のリンク関係を確立する必要があります。

AWS IAM Identity Center に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AWS IAM Identity Center の SSO を構成**する - アプリケーション側でシングル サインオン設定を構成します。
    1. **AWS IAM Identity Center のテスト ユーザーの作成** - AWS IAM Identity Center で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AWS IAM Identity Center**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **サービス プロバイダー メタデータ ファイル**がある場合は、[**基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    b。 **フォルダーロゴ**を選択して、「**AWS IAM Identity Center SSO の構成**」セクションでダウンロードするメタデータファイルを選択し、[**追加**] を選択します。

    [Image: image2]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    Note

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。

    Note

    AWS の ID プロバイダー (つまり、AD から Microsoft Entra ID などの外部プロバイダー) を変更すると、AWS メタデータが変更され、SSO が正しく機能するために Azure に再アップロードされる必要があります。
6. **サービス プロバイダー メタデータ ファイル**がない場合は、[**基本的な SAML 構成]** セクションで次の手順を実行します。**IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<REGION>.signin.aws.amazon.com/platform/saml/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<REGION>.signin.aws.amazon.com/platform/saml/acs/<ID>`
7. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://portal.sso.<REGION>.amazonaws.com/saml/assertion/<ID>`

    Note

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [AWS IAM Identity Center クライアント サポート チーム](mailto:aws-sso-partners@amazon.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
8. AWS IAM Identity Center アプリケーションでは、特定の形式の SAML アサーションが予測されるため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]

    Note

    AWS IAM Identity Center で ABAC が有効になっている場合、追加の属性をセッション タグとして AWS アカウントに直接渡すことができます。
9. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. [ **AWS IAM Identity Center のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AWS IAM Identity Center の SSO の構成

1. 別の Web ブラウザー ウィンドウで、AWS IAM Identity Center 企業サイトに管理者としてサインインします。
2. **[サービス] -&gt; [セキュリティ]、[ID]、[コンプライアンス] -&gt; AWS IAM Identity Center** に移動します。
3. 左側のナビゲーション ウィンドウで、[ **設定]** を選択します。
4. **[設定]** ページで、[**ID ソース] を**探し、[**アクション]** プルダウン メニューを選択し、[**ID ソースの**変更] を選択します。

    [Image: ID ソース変更サービスのスクリーンショット。]
5. [ID ソースの変更] ページで、[ **外部 ID プロバイダー] を選択します**。

    [Image: [外部 ID プロバイダー] セクションを選択するためのスクリーンショット。]
6. **「外部 ID プロバイダーの構成」**セクションの以下の手順を実行します。

    [Image: [メタデータのダウンロードとアップロード] セクションのスクリーンショット。]

    a. **サービス プロバイダーのメタデータ** セクションで、**AWS SSO SAML メタデータ**を見つけ、[**メタデータ ファイルのダウンロード**] を選択してメタデータ ファイルをダウンロードし、コンピューターに保存し、このメタデータ ファイルを使用して Azure portal にアップロードします。

    b。 **AWS アクセス ポータルのサインイン URL 値を**コピーし、この値を [**基本的な SAML 構成] セクション**の **[サインオン URL**] テキスト ボックスに貼り付けます。

    c. [ **ID プロバイダーのメタデータ** ] セクションで、[ **ファイルの選択** ] を選択して、ダウンロードしたメタデータ ファイルをアップロードします。

    d. [ **次へ: 確認]** を選択します。
7. テキスト ボックスに「 **ACCEPT」** と入力して ID ソースを変更します。

    [Image: [構成の確認] のスクリーンショット。]
8. [ **ID ソースの変更] を選択します**。

#### AWS IAM Identity Center のテスト ユーザーの作成

1. **AWS IAM Identity Center コンソールを開きます**。
2. 左側のナビゲーション ウィンドウで、[ユーザー] を選択 **します**。
3. [ユーザー] ページで、[ **ユーザーの追加]** を選択します。
4. [Add user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ページで、これらの手順に従います。

    a. [ **ユーザー名** ] フィールドに「B.Simon」と入力します。

    b。 [ **電子メール アドレス** ] フィールドに、 `username@companydomain.extension`を入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。

    c. [ **確認済みメール アドレス** ] フィールドに、前の手順のメール アドレスを再入力します。

    d. [First name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名) フィールドに、「`Britta`」と入力します。

    e. [Last name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓) フィールドに、「`Simon`」と入力します。

    f. [Display name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/表示名) フィールドに、「`B.Simon`」と入力します。

    g. [ **次へ**] を選択し、[ **次へ]** をもう一度選択します。

    Note

    AWS IAM Identity Center に入力されたユーザー名とメール アドレスが、ユーザーの Microsoft Entra サインイン名と一致していることを確認します。 これは、認証の問題を回避するのに役立ちます。
5. [ **ユーザーの追加] を選択します**。
6. 次に、AWS アカウントにユーザーを割り当てます。 これを行うには、AWS IAM Identity Center コンソールの左側のナビゲーションウィンドウで、 **AWS アカウント**を選択します。
7. [AWS accounts](AWS アカウント) ページで、[AWS organization](AWS 組織) タブを選択し、ユーザーに割り当てる AWS アカウントの横にあるチェック ボックスをオンにします。 次に、[ **ユーザーの割り当て]** を選択します。
8. [Assign users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの割り当て) ページで、ユーザー B. Simon の横にあるチェック ボックス見つけてをオンにします。 次に、**[次へ: 権限セット]** を選択します。
9. アクセス許可セットの選択セクションで、ユーザー B.Simon に割り当てるアクセス許可セットの横にあるチェック ボックスをオンにします。 既存のアクセス許可セットがない場合は、[ **新しいアクセス許可セットの作成**] を選択します。

    Note

    アクセス許可セットによって、ユーザーとグループが AWS アカウントに対して持つアクセス レベルが定義されます。 アクセス許可セットの詳細については、「 **AWS IAM Identity Center マルチアカウントのアクセス許可」** ページを参照してください。
10. [ **完了] を選択します**。

Note

AWS IAM Identity Center では自動ユーザープロビジョニングもサポートされています。自動ユーザープロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aws-single-sign-on-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログインフローを開始できる AWS IAM Identity Center のサインイン URL にリダイレクトされます。
- AWS IAM Identity Center のサインイン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した AWS IAM Identity Center に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイアプリで AWS IAM Identity Center タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した AWS IAM Identity Center に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/axiad-cloud-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Axiad Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/axiad-cloud-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-26
- Summary: Microsoft Entra IDから Axiad Cloud にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために、Axiad Cloud と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループが [Axiad Cloud](https://www.axiad.com) に自動的にプロビジョニングおよびプロビジョニング解除されます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Axiad Cloud でユーザーを作成します。
- accessが不要になったら、Axiad Cloud のユーザーを削除します。
- Microsoft Entra IDと Axiad Cloud の間でユーザー属性の同期を維持します。
- Axiad Cloud でグループとグループ メンバーシップをプロビジョニングします。
- Axiad Cloud への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/axiad-cloud-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Axiad Cloud テナント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra ID と Axiad Cloud の間でどのデータを対応付けるかを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Axiad Cloud を構成する

[Axiad Customer Success](mailto:customer.success@axiad.com) に問い合わせて、Axiad Cloud テナントを Microsoft Entra SCIM プロビジョニング用に構成するよう要求してください。 Axiad Customer Success チームは、次の手順に必要な Axiad Cloud テナントの構成情報と SCIM API 資格情報も提供します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Axiad Cloud を追加する

Microsoft Entra アプリケーション ギャラリーから Axiad Cloud を追加して、Axiad Cloud へのプロビジョニングの管理を開始します。 SSO のために Axiad Cloud を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Axiad Cloud への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーやグループの割り当てに基づいて、Axiad Cloudでユーザーやグループを作成、更新、および無効化するようにMicrosoft Entraプロビジョニングサービスを構成する手順について説明します。

#### Microsoft Entra IDで Axiad Cloud の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくともアプリ所有者または [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Axiad Cloud**] を選択します。

    [Image: アプリケーションの一覧の Axiad Cloud リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Axiad Cloud テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Axiad Cloud に接続できることを確認します。 接続に失敗した場合は、Axiad Cloud アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Axiad Cloud に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作のために Axiad Cloud のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Axiad Cloud API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Axiad Cloud で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | エクスターナルID | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | 表示名 | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | 名前.名 | 糸 |  |  |
    | 名前.姓 | 糸 |  |  |
    | 名前.整形済み | 糸 |  |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:コストセンター | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:組織 | 糸 |  |  |
12. グループを選択 **します**。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Axiad Cloud に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Axiad Cloud のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Axiad Cloud で必須 |
    | --- | --- | --- | --- |
    | 表示名 | 糸 | ✓ | ✓ |
    | エクスターナルID | 糸 | ✓ | ✓ |
    | メンバー | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/axiad-conductor-tutorial"} -->
## Microsoft Entra ID でシングル サインオンを行うために Axiad Conductor を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/axiad-conductor-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Axiad Conductor for Entra ID の間でシングル サインオンを構成する方法について説明します。

この記事では、Axiad Conductor for Entra ID と Microsoft Entra ID を統合する方法について説明します。 Axiad Conductor for Entra ID を Microsoft Entra ID と統合すると、次のことができます。

- Axiad Conductor for Entra ID にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Axiad Conductor for Entra ID に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Axiad Conductor for Entra ID でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Axiad Conductor for Entra ID では、 **SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。
- Axiad Conductor for Entra ID では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/axiad-cloud-provisioning-tutorial)。

### ギャラリーから Axiad Conductor for Entra ID を追加する

Microsoft Entra ID への Axiad Conductor for Entra ID の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Axiad Conductor for Entra ID を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Axiad Conductor for Entra ID**」と入力します。
4. 結果パネルから **Entra ID の Axiad Conductor を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Axiad Conductor for Entra ID の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Axiad Conductor for Entra ID に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Axiad Conductor for Entra ID の関連ユーザーとの間にリンク関係を確立する必要があります。

Axiad Conductor for Entra ID に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Axiad Conductor for Entra ID SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Entra ID のテストユーザーとしての Axiad Conductor の作成** - Microsoft Entra における B.Simon に対応するユーザーを Axiad Conductor に作成しリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Axiad Conductor for Entra ID**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    あ [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://access-<tenantName>.<platform>.axiadids.net/auth/realms/master`

    b。 [ **応答 URL** ] ボックスに、次のいずれかの URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://access-user-<tenantName>.<platform>.axiadids.net/auth/realms/master/broker/saml/endpoint` |
    | `https://access-<tenantName>.<platform>.axiadids.net/auth/realms/master/broker/saml/endpoint` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | サインオン URL |
    | --- |
    | `https://portal-<tenantName>.<platform>.axiadids.net/user` |
    | `https://portal-<tenantName>.<platform>.axiadids.net/operator` |

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには [、Axiad Conductor for Entra ID サポート チーム](mailto:support@axiad.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Entra ID 用の Axiad Conductor のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Entra ID SSO のための Axiad Conductor を設定する

**Axiad Conductor for Entra ID** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を[、Axiad Conductor for Entra ID サポート チーム](mailto:support@axiad.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Entra ID テスト ユーザー用の Axiad Conductor の作成

このセクションでは、Axiad Conductor for Entra ID で Britta Simon というユーザーを作成します。 [Axiad Conductor for Entra ID サポート チーム](mailto:support@axiad.com)と協力して、Axiad Conductor for Entra ID プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Axiad Conductor for Entra ID のサインオン URL にリダイレクトされます。
- Axiad Conductor for Entra ID のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Axiad Conductor for Entra ID] タイルを選択すると、このオプションは Axiad Conductor for Entra ID のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/axway-csos-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Axway CSOS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/axway-csos-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Axway CSOS の間でシングル サインオンを構成する方法について説明します。

この記事では、Axway CSOS と Microsoft Entra ID を統合する方法について説明します。 Axway CSOS と Microsoft Entra ID を統合すると、次のことができます。

- Axway CSOS にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Axway CSOS に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Axway CSOS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Axway CSOS では、 **SP** によって開始される SSO がサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Axway CSOS を追加する

Microsoft Entra ID への Axway CSOS の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Axway CSOS を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Axway CSOS**」と入力します。
4. 結果パネルから **Axway CSOS** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Axway CSOS の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Axway CSOS に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Axway CSOS の関連ユーザーとの間にリンク関係を確立する必要があります。

Axway CSOS で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Axway CSOS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Axway CSOS テスト ユーザーを作成** - ユーザーの Microsoft Entra 表現にリンクされた、Axway CSOS 内で B.Simon に対応するユーザー。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Axway CSOS**&gt;**シングルサインオン**に移動してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://www.axway.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<host>:<port>/ui/core/SsoSamlAssertionConsumer`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<host>:<port>/ui`

    手記

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには [、Axway CSOS クライアント サポート チーム](mailto:support@axway.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Axway CSOS のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Axway CSOS SSO の構成

**Axway CSOS** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Axway CSOS サポート チーム](mailto:support@axway.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Axway CSOS テスト ユーザーの作成

このセクションでは、Axway CSOS で Britta Simon というユーザーを作成します。 [Axway CSOS サポート チーム](mailto:support@axway.com)と協力して、Axway CSOS プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Axway CSOS のサインオン URL にリダイレクトされます。
- Axway CSOS のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Axway CSOS] タイルを選択すると、このオプションは Axway CSOS のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/azure-databricks-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニングのAzure Databricksの構成 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/azure-databricks-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-07-22
- Summary: SCIM を使用して Azure Databricks へユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、Microsoft Entra IDを使用して、Azure Databricks アカウントへの SCIM プロビジョニングを設定する方法について説明します。

注

[自動 ID 管理](https://learn.microsoft.com/azure/databricks/admin/users-groups/automatic-identity-management/)を使用して、Microsoft Entra IDからユーザーとグループを同期することもできます。 自動 ID 管理では、Microsoft Entra ID でアプリケーションを構成する必要はありません。 また、MICROSOFT Entra ID サービス プリンシパルと入れ子になったグループを Azure Databricks に同期することもサポートされています。これは SCIM プロビジョニングを使用してサポートされていません。 自動 ID 管理は、2025 年 8 月 1 日以降に作成されたアカウントに対して既定で有効になっています。

注

- Microsoft Entra ID SCIM プロビジョニング コネクタは、Azure China リージョンでは使用できません。
- SCIM プロビジョニングは、Azure Databricks の認証の構成とは別です。 認証は、OpenID Connect プロトコル フローを使用して、Microsoft Entra ID によって自動的に処理されます。

### 前提条件

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Premium プラン](https://databricks.com/product/pricing/platform-addons)を使用するAzure Databricks アカウント。
- Microsoft Entra IDの**クラウド アプリケーション管理者**ロール。
- グループをプロビジョニングするための **Premium エディション**Microsoft Entra ID アカウント。 ユーザーのプロビジョニングは、Microsoft Entra ID の任意のエディションで利用できます。
- **Azure Databricks アカウント管理者**である必要があります。

注

アカウント コンソールを有効にして、最初のアカウント管理者を設けるには、「[最初のアカウント管理者を設置する](https://learn.microsoft.com/azure/databricks/admin/admin-concepts#establish-first-account-admin)」を参照してください。

### 手順 1: Azure Databricks を構成する

1. Azure Databricks アカウント管理者として、Azure Databricks [アカウント コンソール](https://accounts.azuredatabricks.net)にサインインします。
2. **[セキュリティ]** を選択します。
3. **ユーザー プロビジョニング** を選択します。
4. **[ユーザー プロビジョニングの設定](Set up user provisioning) **を選択します。
5. **SCIM トークン**と**アカウント SCIM URL をコピーします**。 これらの値を使用して、Microsoft Entra IDエンタープライズ アプリケーションを構成します。

注

SCIM トークンは Account SCIM API `/api/2.1/accounts/{account_id}/scim/v2/` に制限されており、他の Databricks REST API への認証には使用できません。

### 手順 2: ギャラリーからAzure Databricksを追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用にAzure Databricksを構成する前に、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧にAzure Databricksを追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **ギャラリーからの追加** セクションで、**Azure Databricks SCIM Provisioning Connector** を検索して選択します。
4. アプリケーションの **名前** を入力し、[追加] を選択 **します**。

### 手順 3: Azure Databricksに自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーとグループの割り当てに基づいて、Azure Databricksでユーザーとグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

Important

ID をワークスペースに直接同期する SCIM コネクタが既にある場合、アカウント レベルの SCIM コネクタが有効になっているときには、これらの SCIM コネクタを無効にする必要があります。 「[ワークスペース レベルの SCIM プロビジョニングをアカウント レベルに移行する](https://learn.microsoft.com/azure/databricks/admin/users-groups/scim/aad#migrate)」を参照してください。

#### プロビジョニング資格情報を構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動し、**Azure Databricks SCIM Provisioning Connector アプリケーションを**選択します。
3. [ **管理** ] メニューの [ **プロビジョニング**] を選択します。
4. **[プロビジョニング モード**] を **[自動**] に設定します。
5. [ **管理者資格情報]** で、 **テナント URL** を手順 1 でコピーした **アカウント SCIM URL** に設定します。
6. 手順 1 で生成した **SCIM トークン**に**シークレット** トークンを設定します。
7. [ **テスト接続]** を選択し、プロビジョニングを有効にするために資格情報が承認されていることを確認するメッセージを待ちます。
8. **保存**を選びます。

#### アプリケーションにユーザーとグループを割り当てる

SCIM アプリケーションに割り当てられたユーザーとグループは、Azure Databricks アカウントにプロビジョニングされます。 既存の Azure Databricks ワークスペースがある場合、Databricks では、それらのワークスペース内のすべての既存のユーザーおよびグループを SCIM アプリケーションに追加することが推奨されます。

注

Microsoft Entra ID では、Azure Databricks へのサービス プリンシパルの自動プロビジョニングはサポートされていません。 「アカウントにサービス プリンシパルを追加する」に従って、Azure Databricks [アカウントにサービス プリンシパルを追加](https://learn.microsoft.com/azure/databricks/admin/users-groups/manage-service-principals#add-sp)できます。

Microsoft Entra ID では、Azure Databricks への入れ子になったグループの自動プロビジョニングはサポートされていません。 Microsoft Entra ID で読み取ってプロビジョニングできるのは、明示的に割り当てられたグループの直接のメンバーであるユーザーだけです。 回避策として、プロビジョニングする必要があるユーザーを含むグループを明示的に割り当てます。

1. **管理**&gt;**プロパティ**に移動します。
2. **[割り当てが必要]** を **[いいえ]** に設定します。 Databricks では、すべてのユーザーが Azure Databricks アカウントにサインインできるように、このオプションを設定することをお勧めします。
3. **管理**&gt;**プロビジョニング**に移動します。
4. Microsoft Entra ID のユーザーとグループの Azure Databricks への同期を開始するには、[ **プロビジョニングの状態] トグルを** **[オン]** に設定します。
5. **保存**を選びます。
6. 管理&gt;**ユーザーとグループに**移動します。
7. [ **ユーザー/グループの追加]** を選択し、ユーザーとグループを選択して、[ **割り当て** ] ボタンを選択します。
8. 数分待って、そのユーザーとグループがご自分の Azure Databricks アカウントに存在することを確認します。

Microsoft Entra ID によって次回の同期がスケジュールされるときに、追加して割り当てるユーザーとグループが、Azure Databricks アカウントに自動的にプロビジョニングされます。

注

アカウント レベルの SCIM アプリケーションからユーザーを削除すると、ID フェデレーションが有効になっているかどうかに関係なく、そのユーザーはアカウンとそのワークスペースからも非アクティブ化されます。

### 手順 4: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### プロビジョニングのヒント

- プロビジョニングを有効にする前に Azure Databricks アカウントに存在していたユーザーとグループは、プロビジョニングの同期時に次の動作を示します。
    - ユーザーとグループは、Microsoft Entra IDにも存在する場合は**マージされます**。
    - ユーザーとグループは、Microsoft Entra IDに存在しない場合は**無視されます**。 Microsoft Entra IDに存在しないユーザーは、Azure Databricksにサインインできません。
- グループのメンバーシップによって複製される、個別に割り当てられたユーザー アクセス許可は、ユーザーのグループ メンバーシップが削除された後でも残ります。
- アカウント コンソールを使用して Azure Databricks アカウントからユーザーを直接削除すると、次のような影響があります。
    - 削除されたユーザーは、その Azure Databricks アカウントとアカウント内のすべてのワークスペースにアクセスできなくなります。
    - 削除されたユーザーは、エンタープライズ アプリケーションに残っている場合でも、Microsoft Entra ID プロビジョニングを使用して再び同期されることはありません。
- 最初の Microsoft Entra ID 同期は、プロビジョニングを有効にした直後にトリガーされます。 以降の同期は、アプリケーション内のユーザーとグループの数に応じて、20 ~ 40 分ごとにトリガーされます。
- Azure Databricks ユーザーのメール アドレスは更新できません。 メール アドレスを更新する必要がある場合は、Azure Databricks アカウント チームにお問い合わせください。
- 入れ子になったグループまたは Microsoft Entra ID サービス プリンシパルを **Azure Databricks SCIM プロビジョニング コネクタ** アプリケーションから同期することはできません。 Azure Databricks SCIM API を対象とする [Databricks Terraform プロバイダー](https://registry.terraform.io/providers/databricks/databricks/latest/docs)またはカスタム スクリプトを使用して、入れ子になったグループまたはMicrosoft Entra IDサービス プリンシパルを同期できます。
- Microsoft Entra ID のグループ名の更新は、Azure Databricks に同期されません。
- パラメーター `userName` と `emails.value` が一致している必要があります。 一致していないと、Microsoft Entra ID SCIM アプリケーションからのユーザー作成要求を Azure Databricks が拒否する可能性があります。 外部ユーザーやエイリアス化された電子メールなどの場合は、エンタープライズ アプリケーションの既定の SCIM マッピングを変更して、`userPrincipalName`ではなく`mail`を使用することが必要になる場合があります。

### (省略可能)Microsoft Graphを使用して SCIM プロビジョニングを自動化する

[Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/auth/auth-concepts) には、SCIM プロビジョニング コネクタ アプリケーションを構成する代わりに、Azure Databricks アカウントまたはワークスペースへのユーザーとグループのプロビジョニングを自動化するためにアプリケーションに統合できる、認証と認可のライブラリが含まれています。

1. [Microsoft Graph にアプリケーションを登録する手順](https://learn.microsoft.com/ja-jp/graph/auth-register-app-v2)に従います。 **アプリケーション ID とアプリケーション**の**テナント ID を**書き留めておきます。
2. アプリケーションの **[概要]**ページに移動します。
    1. アプリケーションのクライアント シークレットを構成し、シークレットをメモします。
    2. アプリケーションに次のアクセス許可を付与します。
        - `Application.ReadWrite.All`
        - `Application.ReadWrite.OwnedBy`
3. Microsoft Entra ID 管理者に、[管理者の同意を許可する](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/grant-admin-consent)よう依頼します。
4. アプリケーションのコードを更新して、 [Microsoft Graph のサポートを追加](https://learn.microsoft.com/ja-jp/graph/migrate-azure-ad-graph-planning-checklist)します。

### トラブルシューティング

#### ユーザーとグループが同期されない

- プロビジョニングの設定に使用する Azure Databricks SCIM トークンが、アカウント コンソールで引き続き有効であることを確認します。
- 入れ子になったグループを同期しようとしないでください。これは、Microsoft Entra ID の自動プロビジョニングではサポートされていません。

#### Microsoft Entra ID のサービス プリンシパルが同期されない

**Azure Databricks SCIM Provisioning Connector** アプリケーションでは、サービス プリンシパルの同期はサポートされていません。

#### 初期同期後、ユーザーとグループは同期を停止します

初期同期後、ユーザーまたはグループの割り当てを変更した直後にMicrosoft Entra IDは同期されません。 ユーザーとグループの数に基づいて、遅延後に同期がスケジュールされます。 即時同期を要求するには、エンタープライズ アプリケーションの **管理**&gt;**プロビジョニング** に移動し、[ **現在の状態をクリアして同期を再開**する] を選択します。

#### Microsoft Entra ID プロビジョニング サービスの IP 範囲にアクセスできない

Microsoft Entra ID プロビジョニング サービスは、特定の IP 範囲で動作します。 ネットワーク アクセスを制限する必要がある場合は、Azure IP `AzureActiveDirectory` ファイル内のの IP アドレスからのトラフィックを許可する必要があります。 `AzureActiveDirectory`などのサブタグではなく、`AzureActiveDirectory.ServiceEndpoint` サービス タグを使用します。 サブタグ IP 範囲には、すべての SCIM プロビジョニング トラフィック IP が含まれているわけではありません。 詳細については、「[IP 範囲](https://learn.microsoft.com/ja-jp/azure/active-directory/app-provisioning/use-scim-to-provision-users-and-groups)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/azure-databricks-with-private-link-workspace-provisioning-tutorial"} -->
## Private Link ワークスペースを使用して Azure Databricks への Microsoft Entra オンプレミス アプリ プロビジョニングを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/azure-databricks-with-private-link-workspace-provisioning-tutorial
- Service: entra-id / app-provisioning
- Article date: 2024-12-30
- Summary: この記事では、Microsoft Entra プロビジョニング サービスを使用して、Private Link ワークスペースを使用して Azure Databricks にユーザーをプロビジョニングする方法について説明します。

Microsoft Entra プロビジョニング サービスでは、ユーザーをクラウドまたはオンプレミスのアプリケーションに自動的にプロビジョニングするために使用できる [SCIM 2.0](https://techcommunity.microsoft.com/t5/identity-standards-blog/provisioning-with-scim-getting-started/ba-p/880010) クライアントがサポートされています。 この記事では、Microsoft Entra プロビジョニング サービスを使用して、パブリック アクセスなしで Azure Databricks ワークスペースにユーザーをプロビジョニングする方法について説明します。

[Image: SCIM アーキテクチャを示す図。]

### [前提条件]

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。 Microsoft Entra ID ガバナンスと Microsoft Entra ID P1 または Premium P2 (または EMS E3 または E5) を使用します。 要件に適したライセンスを見つけるには、「 [Microsoft Entra ID の一般公開機能を比較](https://www.microsoft.com/security/business/microsoft-entra-pricing)する」を参照してください。

- エージェントをインストールするための管理者ロール。 このタスクは 1 回限りの作業であり、少なくとも [ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) ロールを持つアカウントである必要があります。
- アプリケーションをクラウドで構成するための管理者ロールは、[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)です。
- プロビジョニング エージェントをホストするための RAM が少なくとも 3 GB のコンピューター。 コンピューターには Windows Server 2016 以降のバージョンの Windows Server が必要であり、ターゲット アプリケーションへの接続と、login.microsoftonline.com、その他の Microsoft Online Services、Azure ドメインへの送信接続が必要です。 たとえば、Azure IaaS またはプロキシの背後でホストされている Windows Server 2016 仮想マシンがあります。

### Microsoft Entra Connect プロビジョニング エージェント パッケージのダウンロード、インストール、構成

プロビジョニング エージェントを既にダウンロードし、別のオンプレミス アプリケーション用に構成している場合は、次のセクションに進んでください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[Entra Connect]**&gt;**[クラウド同期]** に移動します。

    [Image: 新しい UX 画面のスクリーンショット。]
3. 左側で、[エージェント] を選択 **します**。
4. [ **オンプレミス エージェントのダウンロード**] を選択し、[ **使用条件に同意してダウンロード**] を選択します。

注

オンプレミス アプリケーションのプロビジョニングと Microsoft Entra Connect クラウド同期または人事主導のプロビジョニングには、異なるプロビジョニング エージェントを使用してください。 3 つのシナリオはすべて、同じエージェントで管理しないでください。

1. プロビジョニング エージェント インストーラーを開き、サービス条件に同意して、 **次へ**を選択します。
2. プロビジョニング エージェント ウィザードが開いたら、[ **拡張機能の選択** ] タブに進み、有効にする拡張機能の入力を求められたら **、[オンプレミス アプリケーション プロビジョニング** ] を選択します。
3. プロビジョニング エージェントは、オペレーティング システムの Web ブラウザーを使用して、Microsoft Entra ID および組織の ID プロバイダーに対して認証するためのポップアップ ウィンドウを表示します。 Windows Server のブラウザーとして Internet Explorer を使用している場合は、JavaScript を正しく実行できるように、ブラウザーの信頼済みサイト一覧に Microsoft Web サイトを追加することが必要になる場合があります。
4. 承認を求められたら、Microsoft Entra 管理者の資格情報を入力します。 ユーザーには、少なくとも [ハイブリッド ID 管理者ロールが](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) 必要です。
5. [ **確認]** を選択して設定を確認します。 インストールが成功したら、[ **終了**] を選択し、プロビジョニング エージェント パッケージ インストーラーを閉じてもかまいません。

### SCIM 対応ワークスペースへのプロビジョニング

エージェントがインストールされると、オンプレミスでそれ以上の構成は必要なくなり、すべてのプロビジョニング構成が管理されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. ギャラリーから **オンプレミスの SCIM アプリ** を追加 [します](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。
4. 左側のメニューから、[ **プロビジョニング** ] オプションに移動し、[ **開始]** を選択します。
5. ドロップダウン リストから [ **自動** ] を選択し、[ **オンプレミス接続** ] オプションを展開します。
6. ドロップダウン リストからインストールしたエージェントを選択し、[ **エージェントの割り当て]** を選択します。
7. 次に、10 分待つか **、Microsoft Entra Connect プロビジョニング エージェントを** 再起動してから、次の手順に進み、接続をテストします。
8. [ **テナント URL** ] フィールドに、アプリケーションの SCIM エンドポイント URL を指定します。 URL は通常、各ターゲット アプリケーションに対して一意であり、DNS で解決できる必要があります。 アプリケーションと同じホストにエージェントがインストールされているシナリオの例を次に示します。 `https://localhost:8585/scim`

    [Image: エージェントの割り当てを示すスクリーンショット。]
9. Azure Databricks ユーザー設定コンソールで管理者トークンを作成し、[ **シークレット トークン** ] フィールドに同じトークンを入力します
10. [ **テスト接続]** を選択し、資格情報を保存します。 アプリケーション SCIM エンドポイントは、受信プロビジョニング要求をアクティブにリッスンしている必要があります。それ以外の場合、テストは失敗します。 接続の問題が発生した場合は、 [ここで](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ecma-troubleshoot#troubleshoot-test-connection-issues) の手順を使用します。

注

テスト接続に失敗した場合は、要求が行われたことがわかります。 テスト接続エラー メッセージの URL は切り捨てられますが、アプリケーションに送信された実際の要求には、上記の URL 全体が含まれていることに注意してください。

1. アプリケーションに必要 [な属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes) または [スコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) 規則を構成します。
2. ユーザーとグループをアプリケーションに [割り当てることで、スコープにユーザー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) を追加します。
3. [必要に応](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)じて少数のユーザーのプロビジョニングをテストします。
4. ユーザーをアプリケーションに割り当てることで、スコープにユーザーを追加します。
5. [ **プロビジョニング** ] ウィンドウに移動し、[ **プロビジョニングの開始**] を選択します。
6. [プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)を使用して監視します。

次のビデオでは、オンプレミス プロビジョニングの概要を紹介しています。

### その他の要件

- [SCIM](https://techcommunity.microsoft.com/t5/identity-standards-blog/provisioning-with-scim-getting-started/ba-p/880010) 実装が [Microsoft Entra SCIM 要件](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)を満たしていることを確認します。 Microsoft Entra ID には、開発者が SCIM 実装をブートストラップするために使用できるオープンソース [の参照コード](https://github.com/AzureAD/SCIMReferenceCode/wiki) が用意されています。
- /schemas エンドポイントをサポートして、必要な構成を減らします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/baldwin-safety-&-compliance-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Baldwin Safety and Compliance を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/baldwin-safety-&-compliance-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Baldwin Safety and Compliance の間にシングル サインオンを構成する方法について説明します。

この記事では、Baldwin Safety and Compliance と Microsoft Entra ID を統合する方法について説明します。 Baldwin Safety and Compliance を Microsoft Entra ID と統合すると、次のことができます。

- Baldwin Safety and Compliance にアクセスできるユーザーを Microsoft Entra ID 内で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Baldwin Safety and Compliance に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Baldwin Safety and Compliance でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Baldwin Safety and Compliance では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Baldwin Safety and Compliance の追加

Microsoft Entra ID への Baldwin Safety and Compliance の統合を構成するには、ギャラリーから、お使いの管理対象 SaaS アプリの一覧に Baldwin Safety and Compliance を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Baldwin Safety and Compliance**」と入力します。
4. 結果のパネルから **[Baldwin Safety and Compliance]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Baldwin Safety and Compliance 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Baldwin Safety and Compliance に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Baldwin Safety and Compliance 内の関連ユーザーとの間にリンク関係を確立する必要があります。

Baldwin Safety and Compliance に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Baldwin Safety and Compliance SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Baldwin Safety and Compliance のテストユーザーを作成 - Baldwin** Safety and Compliance で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザ表現に接続します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Baldwin Safety and Compliance**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. **[Baldwin Safety and Compliance のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Baldwin Safety and Compliance SSO の構成

**Baldwin Safety and Compliance** 側でシングル サインオンを構成するには、**拇印の値**と アプリケーションの構成からコピーした適切な URL を [Baldwin Safety and Compliance サポート チーム](mailto:support@baldwinaviation.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Baldwin Safety and Compliance のテスト ユーザーの作成

このセクションでは、Baldwin Safety and Compliance で Britta Simon というユーザーを作成します。 [Baldwin Safety and Compliance サポート チーム](mailto:support@baldwinaviation.com)と連携して、Baldwin Safety and Compliance プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Baldwin Safety and Compliance に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Baldwin Safety and Compliance] タイルを選択すると、SSO を設定した Baldwin Safety and Compliance に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/balsamiq-wireframes-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Balsamiq Wireframes を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/balsamiq-wireframes-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Balsamiq Wireframes の間のシングル サインオンを構成する方法について説明します。

この記事では、Balsamiq Wireframes と Microsoft Entra ID を統合する方法について説明します。 Balsamiq Wireframes を Microsoft Entra ID と統合すると、次のことが可能になります。

- Balsamiq Wireframes にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Balsamiq Wireframes に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Balsamiq Wireframes でのシングル サインオン (SSO) が有効なサブスクリプション。

注

この機能は、200-projects Space プランのユーザーのみが使用できます。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Balsamiq Wireframes では、**SP と IDP** Initiated SSO がサポートされます。
- Balsamiq Wireframes では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Balsamiq Wireframes の追加

Microsoft Entra ID への Balsamiq Wireframes の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Balsamiq Wireframes を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Balsamiq Wireframes**」と入力します。
4. 結果ペインから **[Balsamiq Wireframes]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Balsamiq Wireframes 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Balsamiq Wireframes に対する Microsoft Entra SSO を構成およびテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Balsamiq Wireframes の関連ユーザーとの間にリンク関係を確立する必要があります。

Balsamiq Wireframes に対する Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Balsamiq Wireframes の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Balsamiq Wireframes のテスト ユーザーの作成** - Microsoft Entra のユーザー表現にリンクされた、Balsamiq Wireframes で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Balsamiq Wireframes**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://balsamiq.cloud/samlsso/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://balsamiq.cloud/samlsso/<ID>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://balsamiq.cloud/samlsso/<ID>`

    d. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://balsamiq.cloud/<ID>/projects`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、リレー状態でこれらの値を更新します。 これらの値を取得するには、[Balsamiq Wireframes のクライアント サポート チーム](mailto:support@balsamiq.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Balsamiq Wireframes アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の一覧を示すスクリーンショット。]
7. その他に、Balsamiq Wireframes アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザーのメールアドレス |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Balsamiq Wireframes のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Balsamiq Wireframes の SSO の構成

1. 管理者としてお使いの Balsamiq Wireframes 社のサイトにログインします。
2. **[設定]**&gt;**[スペース設定]**に移動し、[シングル Sign-On 認証] で **[SSO の構成**] を選択します。

    [Image: SSO の設定を示すスクリーンショット。]
3. 必要なすべての値をコピーし、Azure portal の **[基本的な SAML 構成]** セクションに貼り付けて、[ **次へ**] を選択します。

    [Image: サービス プロバイダーの詳細を示すスクリーンショット。]
4. **[IdP の構成]** セクション で、次の手順を実行します。

    [Image: IDP メタデータを示すスクリーンショット。]

    1. **[SAML 2.0 エンドポイント (HTTP)]** ボックスに、**[ログイン URL]** からコピーした値を貼り付けます。
    2. **[ID プロバイダー発行者]** ボックスに、**[Microsoft Entra 識別子]** からコピーした値を貼り付けます。
    3. ダウンロードした**フェデレーション メタデータ XML** ファイルを開き、**[公開証明書]** セクションにそのファイルを**アップロード**します。
    4. [**次へ**] を選択します。

    注

    アップロードする IdP メタデータ ファイルがある場合は、フィールドが自動的に設定されます。
5. SAML 構成を確認し、[ **SAML ログインのテスト** ] ボタンを選択し、[ **次へ**] を選択します。

    [Image: SAML 構成を示すスクリーンショット。]
6. テスト構成が成功したら、[ **今すぐ SAML SSO を有効にする**] を選択します。

    [Image: SAML のテストを示すスクリーンショット。]

#### Balsamiq Wireframes のテストユーザーを作成する

このセクションでは、Britta Simon というユーザーを Balsamiq Wireframes に作成します。 Balsamiq Wireframes では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Balsamiq Wireframes にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Balsamiq Wireframes のサインオン URL にリダイレクトされます。
- Balsamiq Wireframes のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Balsamiq Wireframes に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Balsamiq Wireframes] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Balsamiq Wireframes に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bamboo-hr-tutorial"} -->
## Microsoft Entra ID で BambooHR for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bamboo-hr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BambooHR の間のシングル サインオンを構成する方法について説明します。

この記事では、BambooHR と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と BambooHR を統合すると、次のことが可能になります。

- BambooHR にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで BambooHR に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- BambooHR でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- BambooHR では、**SP** によって開始される SSO がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの BambooHR の追加

BambooHR の Microsoft Entra ID への統合を構成するには、BambooHR をギャラリーから管理対象 SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**BambooHR**」と入力します。
4. 結果のパネルから **[BambooHR]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BambooHR の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、BambooHR に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと BambooHR の関連ユーザーとの間にリンク関係を確立する必要があります。

BambooHR に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BambooHR の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **BambooHR のテストユーザーを作成** - Microsoft Entra における Britta Simon の表現とリンクさせるための、BambooHR での Britta Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID] **&gt;** [エンタープライズ アプリ] **&gt;** [BambooHR] **&gt;** [シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company>.bamboohr.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://<company>.bamboohr.com/saml/consume.php` |
    | `https://<company>.bamboohr.co.uk/saml/consume.php` |

    注

    これらの値は実際の値ではありません。 これらの値を、実際のサインオン URL および応答 URL で更新してください。 値を取得するには、[BambooHR クライアント サポート チーム](https://www.bamboohr.com/contact.php)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[BambooHR のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BambooHR の SSO の構成

1. 新しいウィンドウで、BambooHR ｌ企業サイトに管理者としてサインインします。
2. ホーム ページで、次の操作を行います。

    [Image: BambooHR シングル サインオン ページ]

    a. **アプリ**を選択します。

    b。 **[アプリ]** ウィンドウの **[シングル サインオン]** を選択します。

    c. **[SAML シングル サインオン]** を選択します。
3. **[SAML シングル サインオン]** ウィンドウで、次の手順を実行します。

    [Image: [SAML シングル サインオン] ペイン]

    a. **[SSO ログイン URL]** ボックスに、ステップ 6 でコピーした**ログイン URL** の値を貼り付けます。

    b。 ダウンロードした Base-64 でエンコードされた証明書をメモ帳で開き、その内容をコピーして **[X.509 証明書]** ボックスに貼り付けます。

    c. **保存** を選択します。

#### BambooHR テスト ユーザーの作成

Microsoft Entra ユーザーで BambooHR にサインインできるようにするには、次の手順に従い、それらのユーザーを BambooHR に手動で設定します。

1. **BambooHR** サイトに管理者としてサインインします。
2. 上部のツールバーで **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) ボタン]
3. **[概要]** を選択します。
4. 左側のウィンドウで **[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)**&gt;**[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** を選択します。
5. 設定しようとしている有効な Microsoft Entra アカウントのユーザー名、パスワード、メール アドレスを入力します。
6. **保存** を選択します。

注

Microsoft Entra ユーザー アカウントの設定は、BambooHR のユーザー アカウント作成ツールや API を使って行うこともできます。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

1. [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる BambooHR サインオン URL にリダイレクトされます。
2. BambooHR のサインオン URL に直接移動し、そこからログイン フローを開始します。
3. Microsoft アクセス パネルを使用することができます。 アクセス パネルで [BambooHR] タイルを選択すると、このオプションは BambooHR のサインオン URL にリダイレクトされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bamboo-tutorial"} -->
## Microsoft Entra IDとのシングルサインオンのために、resolution GmbHによるBambooのSAML SSOを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bamboo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と SAML SSO for Bamboo by resolution GmbH の間のシングル サインオンを構成する方法について説明します。

この記事では、SAML SSO for Bamboo by resolution GmbH と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と SAML SSO for Bamboo by resolution GmbH を統合すると、次のことができます:

- SAML SSO for Bamboo by resolution GmbH にアクセスするユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで SAML SSO for Bamboo by resolution GmbH に自動的にサインインするように設定する。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

Microsoft Entra と SAML SSO for Bamboo by resolution GmbH の統合を構成するには、次の項目が必要です:

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SAML SSO for Bamboo by resolution GmbH でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- resolution GmbH による Bamboo 用の SAML SSO では、**SP および IDP による SSO** がサポートされます。
- SAML SSO for Bamboo by resolution GmbH では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの SAML SSO for Bamboo by resolution GmbH の追加

Microsoft Entra ID への SAML SSO for Bamboo by resolution GmbH の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SAML SSO for Bamboo by resolution GmbH を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「SAML SSO for Bamboo by resolution GmbH**」と入力します。
4. 結果のパネルから **SAML SSO for Bamboo by resolution GmbH** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### SAML SSO for Bamboo by resolution GmbH の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SAML SSO for Bamboo by resolution GmbH に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと SAML SSO for Bamboo by resolution GmbH の関連ユーザーとの間にリンクされた関係を確立する必要があります。

SAML SSO for Bamboo by resolution GmbH で Microsoft Entra SSO を構成してテストするには、次の手順に従います:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAML SSO for Bamboo by resolution GmbH SSO**の構成 - アプリケーション側でシングル Sign-On 設定を構成します。
    1. **SAML SSO for Bamboo by resolution GmbH テスト ユーザーの作成** - SAML SSO for Bamboo by resolution GmbHby resolution GmbH で Britta Simon に対応するユーザーを作成し、Microsoft Entra の Britta Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

このセクションでは、Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SAML SSO for Bamboo by resolution GmbH** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **単一の Sign-On 方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/samlsso`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/samlsso`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/samlsso`

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [SAML SSO for Bamboo by resolution GmbH クライアント サポート チーム](https://marketplace.atlassian.com/plugins/com.resolution.atlasplugins.samlsso-bamboo/server/support) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **SAML SSO for Bamboo by resolution GmbH の設定** ] セクションで、要件に従って適切な URL をコピーします。

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
5. **[作成]**を選択します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に SAML SSO for bamboo by resolution GmbH へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. **Entra ID**&gt;**エンタープライズ アプリ**に移動します。
2. アプリケーションの一覧で、[ **SAML SSO for bamboo by resolution GmbH**] を選択します。
3. アプリの概要ページで、[ **管理** ] セクションを見つけて、[ **ユーザーとグループ**] を選択します。
4. [ **ユーザーの追加] を選択します**。 次に、[ **割り当ての追加** ] ダイアログ ボックスで、[ **ユーザーとグループ**] を選択します。
5. [ **ユーザーとグループ** ] ダイアログ ボックスで、ユーザーの一覧から **B.Simon** を選択します。 次に、画面の下部にある **[選択** ] を選択します。
6. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
7. [ **割り当ての追加** ] ダイアログ ボックスで、[ **割り当て**] を選択します。

#### SAML SSO for Bamboo by resolution GmbH の SSO の構成

1. SAML SSO for Bamboo by resolution GmbH 企業サイトに管理者としてサインオンします。
2. メイン ツールバーの右側にある **[設定]**&gt;**[追加**] を選択します。

    [Image: 設定]
3. [セキュリティ] セクションに移動し、メニュー バーの **[SAML SingleSignOn** ] を選択します。

    [Image: The Samlsingle]
4. **[SAML SIngleSignOn プラグインの構成]** ページで、[**IdP の追加]** を選択します。
5. [ **SAML ID プロバイダーの選択** ] ページで、次の手順を実行します。

    ある。 [ **IdP の種類]** を **Microsoft Entra ID** として選択します。

    b。 [ **名前** ] ボックスに名前を入力します。

    c. [ **説明** ] ボックスに、説明を入力します。

    d. [ **次へ**] を選択します。
6. [ **ID プロバイダーの構成** ] ページで、[ **次へ**] を選択します。
7. [ **SAML IdP メタデータのインポート** ] ページで、[ **ファイルの読み込み** ] を選択して、以前にダウンロードした **メタデータ XML** ファイルをアップロードします。
8. [ **次へ**] を選択します。
9. [ **設定の保存] を選択します**。

#### SAML SSO for Bamboo by resolution GmbH のテスト ユーザーの作成

このセクションの目的は、SAML SSO for Bamboo by resolution GmbH で Britta Simon というユーザーを作成することです。 SAML SSO for Bamboo by resolution GmbH は Just-In-Time プロビジョニングをサポートしており、ユーザーを手動で作成することもできます。要件に従って [SAML SSO for Bamboo by resolution GmbH クライアント サポート チーム](https://marketplace.atlassian.com/plugins/com.resolution.atlasplugins.samlsso-bamboo/server/support) にお問い合わせください。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる SAML SSO for Bamboo by resolution GmbH のサインオン URL にリダイレクトされます。
- SAML SSO for Bamboo by resolution GmbH のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SAML SSO for Bamboo by resolution GmbH に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで SAML SSO for Bamboo by resolution GmbH タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SAML SSO for Bamboo by resolution GmbH に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bambubysproutsocial-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Employee Advocacy by Sprout Social を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bambubysproutsocial-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Employee Advocacy by Sprout Social との間でシングル サインオンを構成する方法について説明します。

この記事では、Employee Advocacy by Sprout Social と Microsoft Entra ID を統合する方法について説明します。 Employee Advocacy by Sprout Social を Microsoft Entra ID と統合すると、次のことができます。

- Employee Advocacy by Sprout Social にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Employee Advocacy by Sprout Social に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Employee Advocacy by Sprout Social でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Employee Advocacy by Sprout Social では **SP** and **IDP** Initiated SSO がサポートされます。
- Employee Advocacy by Sprout Social では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Employee Advocacy by Sprout Social を追加する

Microsoft Entra ID への Employee Advocacy by Sprout Social の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Employee Advocacy by Sprout Social を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Employee Advocacy by Sprout Social**」と入力します。
4. 結果のパネルから **[Employee Advocacy by Sprout Social]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Employee Advocacy by Sprout Social の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Employee Advocacy by Sprout Social 用に Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Employee Advocacy by Sprout Social の関連ユーザーとの間にリンク関係を確立する必要があります。

Employee Advocacy by Sprout Social 用に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Employee Advocacy by Sprout Social の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Employee Advocacy by Sprout Social テスト ユーザーを作成する** - Employee Advocacy by Sprout Social で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザーをリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Employee Advocacy by Sprout Social]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://advocacy.sproutsocial.com` |
    | `https://<SUBDOMAIN>.advocacy.sproutsocial.com` |

    注

    この値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには、[Employee Advocacy by Sprout Social Client サポート チーム](mailto:support@getbambu.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Employee Advocacy by Sprout Social アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Employee Advocacy by Sprout Social アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
9. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Employee Advocacy by Sprout Social のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Employee Advocacy by Sprout Social の SSO を構成する

**Employee Advocacy by Sprout Social** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Employee Advocacy by Sprout Social サポート チーム](mailto:support@getbambu.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Employee Advocacy by Sprout Social テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Employee Advocacy by Sprout Social に作成します。 Employee Advocacy by Sprout Social では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Employee Advocacy by Sprout Social にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Employee Advocacy by Sprout Social のサインオン URL にリダイレクトされます。
- Employee Advocacy by Sprout Social のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Employee Advocacy by Sprout Social に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Employee Advocacy by Sprout Social] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Employee Advocacy by Sprout Social に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/banyan-command-center-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Banyan Security Zero Trust Remote Access Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/banyan-command-center-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Banyan Security Zero Trust Remote Access Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Banyan Security Zero Trust Remote Access Platform と Microsoft Entra ID を統合する方法について説明します。 Banyan Security Zero Trust Remote Access Platform と Microsoft Entra ID を統合すると、次のことができます。

- Banyan Security Zero Trust Remote Access Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Banyan Security Zero Trust Remote Access Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Banyan Security Zero Trust Remote Access Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Banyan Security Zero Trust Remote Access Platform では、**SP と IDP** によって開始される SSO がサポートされます。
- Banyan Security Zero Trust Remote Access Platform では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Banyan Security Zero Trust Remote Access Platform の追加

Microsoft Entra ID への Banyan Security Zero Trust Remote Access Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Banyan Security Zero Trust Remote Access Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Banyan Security Zero Trust Remote Access Platform**」と入力します。
4. 結果のパネルから **[Banyan Security Zero Trust Remote Access Platform]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Banyan Security Zero Trust Remote Access Platform のための Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Banyan Security Zero Trust Remote Access Platform 用に Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Banyan Security Zero Trust Remote Access Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

Banyan Security Zero Trust Remote Access Platform 用に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Banyan Security Zero Trust Remote Access Platform の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Banyan Security Zero Trust Remote Access Platformのテストユーザーを作成** - Banyan Security Zero Trust Remote Access PlatformでのB.Simonに対応するユーザーを、Microsoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Banyan Security Zero Trust Remote Access Platform]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://net.banyanops.com/api/v1/sso?orgname=<YOUR_ORG_NAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://net.banyanops.com/api/v1/sso?orgname=<YOUR_ORG_NAME>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://net.banyanops.com/api/v1/sso?orgname=<YOUR_ORG_NAME>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Banyan Security Zero Trust Remote Access Platform クライアント サポート チーム](mailto:support@banyansecurity.io)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Banyan Security Zero Trust Remote Access Platform の SSO の構成

1. Banyan Security Zero Trust Remote Access Platform の Web サイトに管理者としてログインします。
2. **[Admin Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者の設定) &gt; [Admin Sign-on](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者のサインオン)** に移動します。
3. **[Sign-on Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サインオンの設定)** ページで、次の手順を実行します。

    [Image: [Sign-on Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サインオンの設定) のスクリーンショット。]

    a. **[Sign-On Method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サインオンの方法)** として、ドロップダウンから **[Single Sign On - SAML 2.0](シングル サインオン - SAML 2.0)** を選択します。

    b。 **[IDP 発行者]** の値をコピーし、[基本的な SAML 構成] セクションの **[Microsoft Entra 識別子]** ボックスに貼り付けます。

    c. **[アプリのフェデレーション メタデータ URL]** の値を **[IDP Metadata URL](IDP メタデータ URL)** テキスト ボックスに貼り付けます。

    d. **[更新]** ボタンを選択します。

#### Banyan Security Zero Trust Remote Access Platform のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Banyan Security Zero Trust Remote Access Platform に作成します。 Banyan Security Zero Trust Remote Access Platform では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Banyan Security Zero Trust Remote Access Platform にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Banyan Security Zero Trust Remote Access Platform Sign on URL にリダイレクトされます。
- Banyan Security Zero Trust Remote Access Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Banyan Security Zero Trust Remote Access Platform に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Banyan Security Zero Trust Remote Access Platform] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Banyan Security Zero Trust Remote Access Platform に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/battery-management-information-system-tutorial"} -->
## Microsoft Entra ID でシングル サインオンするための BMIS - Battery Management Information System の構成 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/battery-management-information-system-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BMIS - Battery Management Information System との間でシングル サインオンを構成する方法について説明します。

この記事では、BMIS - Battery Management Information System と Microsoft Entra ID を統合する方法について説明します。 BMIS - Battery Management Information System を Microsoft Entra ID と統合すると、次のことができます。

- BMIS - Battery Management Information System にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して BMIS - Battery Management Information System に自動的にサインインできるように設定します。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- BMIS - Battery Management Information System のシングル サインオン (SSO) 対応サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- BMIS - Battery Management Information System では、**IDP** initiated SSO がサポートされます。

### ギャラリーから BMIS - Battery Management Information System を追加する

Microsoft Entra ID への BMIS - Battery Management Information System の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に BMIS - Battery Management Information System を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**BMIS - Battery Management Information System**」と入力します。
4. 結果のパネルから **[BMIS - Battery Management Information System]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BMIS - Battery Management Information System に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、BMIS - Battery Management Information System での Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと BMIS - Battery Management Information System の関連ユーザーとの間にリンク関係を確立する必要があります。

BMIS - Battery Management Information System での Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BMIS - Battery Management Information System の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **BMIS - Battery Management Information System のテストユーザーを作成する** - BMIS - Battery Management Information System における B.Simon 相当のユーザーを作成し、そのユーザーを Microsoft Entra にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**BMIS - Battery Management Information System**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. BMIS - Battery Management Information System アプリケーションでは、特定の形式の SAML アサーションが想定されているため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: Battery Management Information System のアプリケーション画像を示すスクリーンショット。]
7. 上記に加えて、BMIS - Battery Management Information System アプリケーションでは、以下に示すいくつかの追加属性が SAML 応答に返されることが想定されています。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | 名（ファーストネーム） | ユーザー.ファーストネーム |
    | last\_name | ユーザーの名字 |
    | user\_name | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Set up BMIS - Battery Management Information System] (BMIS - Battery Management Information System のセットアップ)** セクションで、要件に基づいて該当する URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BMIS - Battery Management Information System の SSO の構成

**BMIS - Battery Management Information System** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [BMIS - Battery Management Information System サポート チーム](mailto:bmissupport@midtronics.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### BMIS - Battery Management Information System のテスト ユーザーの作成

このセクションでは、BMIS - Battery Management Information System で Britta Simon というユーザーを作成します。 [BMIS - Battery Management Information System サポート チーム](mailto:bmissupport@midtronics.com)と連携して、BMIS - Battery Management Information System プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した BMIS - Battery Management Information System に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [BMIS - Battery Management Information System] タイルを選択すると、SSO を設定した BMIS - Battery Management Information System に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bcinthecloud-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に BC in the Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bcinthecloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BC in the Cloud の間でシングル サインオンを構成する方法について説明します。

この記事では、BC in the Cloud と Microsoft Entra ID を統合する方法について説明します。 BC in the Cloud を Microsoft Entra ID を統合すると、次のことが可能になります。

- BC in the Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで BC in the Cloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- BC in the Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- BC in the Cloud では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの BC in the Cloud の追加

BC in the Cloud と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に BC in the Cloud をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **ギャラリーからの追加**セクションで、検索ボックスに**「BC in the Cloud」**と入力します。
4. 結果パネルから **クラウドで BC** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BC in the Cloud の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、BC in the Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、BC in the Cloud での関連ユーザーとの間にリンク関係を確立する必要があります。

BC in the Cloud の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BC in the Cloud SSO を構成**する - アプリケーション側でシングル サインオン設定を構成します。
    1. **BC in the Cloud のテストユーザーを作成** - これは、Microsoft Entra のユーザーの表現として BC in the Cloud の B.Simon にリンクする、対応するユーザーを作成するためです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[BC in the Cloud]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://app.bcinthecloud.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.bcinthecloud.com/router/loginSaml/<customerid>`

    注

    この値は実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値を取得するには [、クラウド クライアント サポート チームの BC](https://www.bcinthecloud.com/supportcenter/) に問い合わせてください。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **クラウドでの BC のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BC in the Cloud SSO の構成

**クラウド側で BC** でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を[クラウド サポート チームの BC](https://www.bcinthecloud.com/supportcenter/) に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### BC in the Cloud のテスト ユーザーの作成

このセクションでは、BC in the Cloud で Britta Simon というユーザーを作成します。 [クラウド サポート チームの BC](https://www.bcinthecloud.com/supportcenter/) と協力して、BC in the Cloud プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できるクラウド サインオン URL の BC にリダイレクトされます。
- BC in the Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [クラウド] タイルで [BC] を選択すると、このオプションはクラウド サインオン URL の BC にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/beable-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Beable を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/beable-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Beable の間のシングル サインオンを構成する方法について説明します。

この記事では、Beable を Microsoft Entra ID と統合する方法について説明します。 Beable Educationは、インタラクティブで魅力的なオンライン学習プラットフォームや教科書、モバイルアプリを提供し、学生が情報にアクセスして学業で成功できるようサポートしています。 Beable を Microsoft Entra ID と統合すると、次のことが可能になります。

- Beable へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Beable に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Beable に対する Microsoft Entra シングル サインオンをテスト環境で構成およびテストします。 Beable では、**IDP** によって開始されるシングル サインオンがサポートされています。

### [前提条件]

Beable を Microsoft Entra ID と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Beable でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Beable アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Beable を追加する

Microsoft Entra アプリケーション ギャラリーから Beable を追加して、Beable でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Beable**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.beable.com`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://prod-literacy-backend-alb-<ID>.beable.com/login/ssoVerification/?providerId=<ProviderID>&identifier=<DOMAIN>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Beable サポート チーム](https://beable.com/contact/)にお問い合わせください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. Beable アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、Beable アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次の表に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユーザータイプ | user.usertype |
    | 希望言語 | ユーザーの優先言語 |
    | assignedroles | user.assignedroles |
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (Base64)]** を見つけます。**[ダウンロード]** を選択して証明書をダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Beable のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Beable SSO の構成

**Beable** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Beable サポート チーム](https://beable.com/contact/)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Beable テスト ユーザーの作成

このセクションでは、ユーザーは Beable に登録されます。 [Beable サポートチーム](https://beable.com/contact/)と連携して、Beable プラットフォームにユーザーをプロビジョニングします。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Beable に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Beable] タイルを選択すると、SSO を設定した Beable に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bealink-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Bealink を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bealink-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Bealink の間のシングル サインオンを構成する方法について説明します。

この記事では、Bealink と Microsoft Entra ID を統合する方法について説明します。 Bealink を Microsoft Entra ID と統合すると、次のことが可能になります。

- Bealink へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Bealink に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Bealink でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Bealink では、**SP および IDP による SSO** がサポートされます。
- Bealink では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Bealink の追加

Microsoft Entra ID への Bealink の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Bealink を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Bealink**」と入力します。
4. 結果パネルから **Bealink** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Bealink に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、Bealink に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Bealink の関連ユーザーとの間にリンク関係を確立する必要があります。

Bealink に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Bealink SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Bealinkテストユーザーを作成** - BealinkでB.Simonに対応するユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Bealink]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.bealink.io/Saml2https://app.bealink.io/Saml2?company=<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.bealink.io/Saml2/Acs?company=<ID>`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.bealink.io/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Bealink クライアント サポート チーム](mailto:support@bealink.io) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Bealink の SSO の構成

**Bealink** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Bealink サポート チーム](mailto:support@bealink.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Bealink のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Bealink に作成します。 Bealink では、Just-In-Time プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Bealink に存在しない場合は、Bealink にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Bealink のサインオン URL にリダイレクトされます。
- Bealink のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Bealink に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Bealink] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Bealink に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/beatrust-tutorial"} -->
## Microsoft Entra ID で Beatrust for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/beatrust-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Beatrust の間でシングル サインオンを構成する方法について説明します。

この記事では、Beatrust と Microsoft Entra ID を統合する方法について説明します。 Beatrust と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID を使って、誰が Beatrust にアクセスできるかを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Beatrust に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Beatrust でのシングル サインオン (SSO) が有効なサブスクリプション。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra ID でアプリケーションを追加または管理することもできます。 詳細については、Azure 組み込みロール に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Beatrust では、 **SP** Initiated SSO がサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Beatrust を追加する

Microsoft Entra ID への Beatrust の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Beatrust を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Beatrust**」と入力します。
4. 結果パネルから **Beatrust** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Beatrust の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Beatrust に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Beatrust の関連ユーザーとの間にリンク関係を確立する必要があります。

Beatrust に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Beatrust SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Beatrust テスト ユーザーを作成する - Beatrust に B.Simon に対応する Microsoft Entra 表示のユーザーをリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Beatrust**&gt;**シングル サインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://beatrust.com`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://beatrust.com/__/auth/handler`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します: 'https://beatrust.com/&lt;org\_key&gt;

    手記

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには [、Beatrust クライアント サポート チーム](mailto:support@beatrust.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [SAML でシングル サインオンを設定する ] ページの [SAML 署名証明書の] セクションで、[証明書 (Base64) を探し、ダウンロード を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Beatrust のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]
8. **[SAML Certificates]\(SAML 証明書**\) セクションで、アプリのフェデレーション メタデータ URL をコピーします。

    [Image: アプリフェデレーション メタデータ URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Beatrust SSO の構成

**Beatrust** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)**、アプリケーション構成からコピーした適切な URL、およびアプリのフェデレーション メタデータ URL を [Beatrust サポート チーム](mailto:support@beatrust.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Beatrust テスト ユーザーの作成

このセクションでは、Beatrust で Britta Simon というユーザーを作成します。 Beatrust サポート チーム  と連携して、Beatrust プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Beatrust のサインオン URL にリダイレクトされます。
- Beatrust のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Beatrust] タイルを選択すると、このオプションは Beatrust のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/beautiful.ai-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Beautiful.ai を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/beautiful.ai-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Beautiful.ai の間のシングル サインオンを構成する方法について説明します。

この記事では、Beautiful.ai と Microsoft Entra ID を統合する方法について説明します。 Beautiful.ai と Microsoft Entra ID を統合すると、次のことができます。

- Beautiful.ai にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Beautiful.ai に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Beautiful.ai でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Beautiful.ai は、SPおよびIDPが開始したSSOをサポートします
- Beautiful.ai では **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Beautiful.ai の追加

Microsoft Entra ID への Beautiful.ai の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Beautiful.ai を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**にアクセスします。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックス **に「Beautiful.ai** 」と入力します。
4. 結果パネルから **Beautiful.ai** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Beautiful.ai に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Beautiful.ai に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Beautiful.ai の関連ユーザーとの間にリンク関係を確立する必要があります。

Beautiful.ai で Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Beautiful.ai SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Beautiful.ai のテスト ユーザーの作成** - Beautiful.ai で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Beautiful.ai**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.beautiful.ai/login`
7. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Beautiful.ai の SSO の構成

**Beautiful.ai** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[サポート チーム Beautiful.ai](mailto:support@beautiful.ai) 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Beautiful.ai のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Beautiful.ai に作成します。 Beautiful.ai では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Beautiful.ai にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Beautiful.ai サインオン URL にリダイレクトされます。
- Beautiful.ai のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Beautiful.ai に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [Beautiful.ai] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Beautiful.ai に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/beekeeper-azure-ad-data-connector-tutorial"} -->
## Microsoft Entra IDのシングルサインオンのために、Beekeeper Microsoft Entra SSOを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/beekeeper-azure-ad-data-connector-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Beekeeper Microsoft Entra SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、Beekeeper Microsoft Entra SSO と Microsoft Entra ID を統合する方法について説明します。 Beekeeper Microsoft Entra SSO を Microsoft Entra ID を統合すると、次のことができます。

- Beekeeper Microsoft Entra SSO にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Beekeeper Microsoft Entra SSO に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Beekeeper Microsoft Entra SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Beekeeper Microsoft Entra SSO では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Beekeeper Microsoft Entra SSO では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Beekeeper Microsoft Entra SSO の追加

Microsoft Entra ID への Beekeeper Microsoft Entra SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Beekeeper Microsoft Entra SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Beekeeper Microsoft Entra SSO**」と入力します。
4. 結果のパネルから **[Beekeeper Microsoft Entra SSO]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Beekeeper Microsoft Entra SSO に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使って、Beekeeper Microsoft Entra SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Beekeeper Microsoft Entra SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Beekeeper Microsoft Entra SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Beekeeper Microsoft Entra SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Beekeeper Microsoft Entra SSO テストユーザーを作成 - Beekeeper Microsoft Entra SSO で B.Simon の対応ユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Beekeeper Microsoft Entra SSO**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル** があり、 **IDP** 開始モードで構成する場合は、次の手順を実行します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    注

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_COMPANY>.beekeeper.io/login`

    注

    サインオン URL の値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには、[Beekeeper Microsoft Entra SSO クライアント サポート チーム](mailto:support@beekeeper.io)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Beekeeper Microsoft Entra SSO アプリケーションは特定の形式の SAML アサーションを予測しているため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットには、追加のURLを設定する画面が表示されており、ここでサインオンURLを入力することができます。]
8. その他に、Beekeeper Microsoft Entra SSO アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を下に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 名字 | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
    | ユーザー名 | ユーザー.プリンシパル名 |
    | 立場 | ユーザー.職名 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Set up Beekeeper Microsoft Entra SSO] (Beekeeper Microsoft Entra SSO の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Beekeeper Microsoft Entra SSO を構成する

**Beekeeper Microsoft Entra SSO** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Beekeeper Microsoft Entra SSO サポート チーム](mailto:support@beekeeper.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Beekeeper Microsoft Entra SSO テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Beekeeper Microsoft Entra SSO に作成します。 Beekeeper Microsoft Entra SSO では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Beekeeper Microsoft Entra SSO にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Beekeeper Microsoft Entra SSO サインオン URL にリダイレクトされます。
- Beekeeper Microsoft Entra SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Beekeeper Microsoft Entra SSO に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Beekeeper Microsoft Entra SSO タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Beekeeper Microsoft Entra SSO に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/beeline-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Beeline Enterprise を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/beeline-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-29
- Summary: Microsoft Entra ID と Beeline Enterprise の間のシングル サインオンを構成する方法について説明します。

この記事では、Beeline Enterprise と Microsoft Entra ID を統合する方法について説明します。 Beeline Enterprise と Microsoft Entra ID を統合すると、次のことが可能になります。

- Beeline Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Beeline Enterprise に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Beeline Enterprise のシングル サインオン (SSO) に対応したサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Beeline Enterprise では、**SP** initiated SSO と **IDP** initiated SSO がサポートされています。

### Beeline Enterprise をギャラリーから追加する

Beeline Enterprise と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に ArcGIS Enterprise をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新規アプリケーション**に移動します。
3. [ **Microsoft Entra Gallery の参照** ] セクションで、検索ボックスに **「Beeline Enterprise」** と入力します。
4. 結果パネルから **Beeline Enterprise** を選択し、[ **作成**] を選択します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Beeline Enterprise の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Beeline Enterprise に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Beeline Enterprise での関連ユーザーとの間にリンク関係を確立する必要があります。

Beeline Enterprise の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Beeline Enterprise の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Beeline Enterprise のテストユーザーを作成 - Microsoft Entra にあるユーザー B.Simon に対応するユーザーを Beeline にリンクさせるためのものです。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**Beeline Enterprise**&gt;**シングルサインオン**を開いてください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:<Auth0TenantName>:<CustomerName>-SSO`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Auth0TenantName>.<Auth0Environment>.beeline.com/login/callback?connection=<CustomerName>-SSO`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Environment>.beeline.com/<CustomerName>/security/auth0/auth0spinitiatedssohandler.ashx`

    Note

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには [、Beeline Enterprise サポート チーム](mailto:support@beeline.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. **[保存] を選択します**。
8. Beeline Enterprise アプリケーションは、特定の形式で構成された SAML アサーションを想定しています。 最初に [Beeline Enterprise サポート チーム](mailto:support@beeline.com) と協力して、アプリケーションにマップされている正しいユーザー識別子を特定してください。 また、 [Beeline Enterprise サポート チーム](mailto:support@beeline.com) から、このマッピングに使用する属性に関するガイダンスを受けてください。 この属性の値は、アプリケーションの [ **ユーザー属性** ] タブから管理できます。 次のスクリーンショットはその例です。 ここでは、 **ユーザー識別子** 要求を **userprincipalname** 属性にマップしました。この属性は一意のユーザー ID を提供し、成功したすべての SAML 応答で Beeline Enterprise アプリケーションに送信されます。

    [Image: 既定の属性の画像を示すスクリーンショット。]
9. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Beeline Enterprise]**&gt;**[管理]**&gt;**[シングル サインオン]** の順に移動します。
10. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
11. [ **Beeline Enterprise のセットアップ** ] セクションで、 **ログイン URL** と **ログアウト URL をコピーします**。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Beeline Enterprise の SSO を構成する

**Beeline Enterprise** 側でシングル サインオンを構成するには、この記事の前の手順で収集した次の項目を [Beeline Enterprise サポート チーム](mailto:support@beeline.com)に送信する必要があります。 **Beeline Enterprise** 側でシングル サインオンを構成します。

- **証明書 (Base64)**
- **ログイン URL**
- **ログアウト URL**

#### Beeline Enterprise のテスト ユーザーを作成する

このセクションでは、Beeline Enterprise でユーザー Britta Simon を作成します。 Beeline Enterprise アプリケーションでは、シングル サインオンを使用する前に、すべてのユーザーをアプリケーションでプロビジョニングする必要があります。 そのため、 [Beeline Enterprise サポート チーム](mailto:support@beeline.com) と協力して、これらすべてのユーザーをアプリケーションにプロビジョニングします。

### SSO のテスト

このセクションでは、Microsoft Entra のシングル サインオン構成をテストするための 2 つの異なる方法を説明します。

- **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Beeline Enterprise]**&gt;**[管理]**&gt;**[シングル サインオン]** の順に移動します。 [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Beeline Enterprise に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 **マイ アプリ**で **[Beeline Enterprise**] タイルを選択すると、SSO を設定した Beeline Enterprise サイトに自動的にサインインします。 マイ アプリ ポータルの詳細については、「マイ アプリ [ポータルの概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/benchling-tutorial"} -->
## Microsoft Entra ID で Benchling for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/benchling-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Benchling の間でシングル サインオンを構成する方法について説明します。

この記事では、Benchling と Microsoft Entra ID を統合する方法について説明します。 Benchling と Microsoft Entra ID を統合すると、次のことができます。

- Benchling にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Benchling に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Benchling でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Benchling では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Benchling では、 **Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからBenchlingを追加する

Microsoft Entra ID への Benchling の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Benchling を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに「**Benchling**」と入力します。
4. 結果パネルから **Benchling** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Benchling の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Benchling に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Benchling の関連ユーザーとの間にリンク関係を確立する必要があります。

Benchling に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Benchling SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Benchling テストユーザーを作成** - Microsoft Entra のユーザー表現にリンクされ、Benchling 内に B.Simon の対応ユーザーを持つようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Benchling**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.benchling.com/ext/saml/metadata.xml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.benchling.com/ext/saml/signin:finish`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.benchling.com/ext/saml/signin:begin`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Benchling クライアント サポート チーム](mailto:support@benchling.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Benchling アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Benchling アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの姓 |
    | Email | ユーザー.メール |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Benchling の SSO の構成

**Benchling** 側でシングル サインオンを構成するには、**Benchling サポート チーム**に[アプリのフェデレーション メタデータ URL を](mailto:support@benchling.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Benchling テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Benchling に作成します。 Benchling では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Benchling にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Benchling のサインオン URL にリダイレクトされます。
- Benchling のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Benchling に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Benchling タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Benchling に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/benefithub-tutorial"} -->
## Microsoft Entra ID で BenefitHub for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/benefithub-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BenefitHub の間のシングル サインオンを構成する方法について説明します。

この記事では、BenefitHub と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と BenefitHub を統合すると、次のことが可能になります。

- BenefitHub にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで BenefitHub に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- BenefitHub でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- BenefitHub では、 **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの BenefitHub の追加

Microsoft Entra ID への BenefitHub の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に BenefitHub を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「BenefitHub**」と入力します。
4. 結果パネルから **BenefitHub** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BenefitHub の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、BenefitHub に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと BenefitHub の関連ユーザーとの間にリンク関係を確立する必要があります。

BenefitHub に対して Microsoft Entra の SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BenefitHub SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **BenefitHub のテスト ユーザーの作成** - BenefitHub で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**BenefitHub**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、値を入力します。 `urn:benefithub:passport`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://passport.benefithub.info/saml/post/ac`
6. BenefitHub アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の画像を示すスクリーンショット。]
7. その他に、BenefitHub アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 組織ID | &lt; organizationid &gt; |
    |  |  |

    注

    この属性値は実際の値ではありません。 この値を実際の組織 ID で更新します。 実際の組織 ID を取得するには、 [BenefitHub サポート チーム](https://www.benefithub.com/Home/ContactUs) にお問い合わせください。 SAML アサーションを構成するには、その前に [BenefitHub サポート](https://www.benefithub.com/Home/ContactUs)に連絡し、テナントの一意識別子属性の値を要求する必要があります。 この値は、アプリケーションのカスタム要求を構成するのに必要です。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **BenefitHub のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切なURLへの構成のコピーを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BenefitHub の SSO の構成

**BenefitHub** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [BenefitHub サポート チーム](https://www.benefithub.com/Home/ContactUs)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### BenefitHub のテスト ユーザーの作成

このセクションでは、BenefitHub で B.Simon というユーザーを作成します。 [BenefitHub サポート チーム](https://www.benefithub.com/Home/ContactUs)と協力して、BenefitHub プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した BenefitHub に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [BenefitHub] タイルを選択すると、SSO を設定した BenefitHub に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/benefitsolver-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Benefitsolver を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/benefitsolver-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Benefitsolver の間でシングル サインオンを構成する方法について説明します。

この記事では、Benefitsolver と Microsoft Entra ID を統合する方法について説明します。 Benefitsolver と Microsoft Entra ID を統合すると、次のことができます。

- Benefitsolver にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Benefitsolver に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Benefitsolver でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Benefitsolver では、**SP** によって開始される SSO がサポートされます。

### ギャラリーから Benefitsolver を追加する

Microsoft Entra ID への Benefitsolver の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Benefitsolver を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Benefitsolver**」と入力します。
4. 結果パネルで **Benefitsolver** を選択し、その後、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Benefitsolver の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Benefitsolver に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Benefitsolver の関連ユーザーとの間にリンク関係を確立する必要があります。

Benefitsolver に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Benefitsolver SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Benefitsolver テスト ユーザーの作成** - B.Simon の対応ユーザーを Benefitsolver 内で作成し、Microsoft Entra における代表とリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Benefitsolver]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成] の編集
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    a. [**識別子** ボックスに、次のパターンを使用して URL を入力します:`https://<companyname>.benefitsolver.com/saml20`

    b。 [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します。`https://www.benefitsolver.com/benefits/BenefitSolverView?page_name=single_signon_saml`

    c. [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `http://<companyname>.benefitsolver.com`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Benefitsolver クライアント サポート チーム](https://www.businessolver.com/contact-us/) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. Benefitsolver アプリケーションでは、特定の形式の SAML アサーションが必要です。 このアプリケーションに対して次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの **ユーザー属性** セクションから管理できます。 **[SAML によるシングル サインオンの設定]** ページで **[編集]** ボタンをクリックし、**[ユーザー属性]** ダイアログを開きます。

    [Image: スクリーンショットは、編集コントロールが呼び出されたユーザー属性を示しています。]
7. [ユーザー属性] ダイアログの [ユーザー要求] セクションで、[編集] アイコン使用して要求を編集するか、[新しい要求の追加] を使用して要求を追加 、上の図に示すように SAML トークン属性を構成し、次の手順を実行します。

    | 名前 | ソース属性 |
    | --- | --- |
    | ClientID | この値は、[Benefitsolver クライアント サポート チームの](https://www.businessolver.com/contact-us/)から取得する必要があります。 |
    | クライアントキー | この値は、[Benefitsolver クライアント サポート チームの](https://www.businessolver.com/contact-us/)から取得する必要があります。 |
    | ログアウトURL | この値は、[Benefitsolver クライアント サポート チームの](https://www.businessolver.com/contact-us/)から取得する必要があります。 |
    | 従業員ID | この値は、[Benefitsolver クライアント サポート チームの](https://www.businessolver.com/contact-us/)から取得する必要があります。 |
    |  |  |

    a. **新しい要求** を追加を選択して、**マネージド ユーザー要求** ダイアログを開きます。

    [Image: スクリーンショットには、[新しい要求の追加] と [保存] が呼び出されたユーザー要求が示されています。]

    [Image: スクリーンショットは、この手順で説明する値を入力できるユーザー要求の管理を示しています。]

    b。 [**名前** テキストボックスに、その行に表示される属性名を入力します。]

    c. **名前空間** 空白のままにします。

    d. [ソース] として **[属性]** を選択します。

    e. **ソース属性** 一覧から、その行に表示される属性値を入力します。

    f. **[OK]** を選択します。

    g. **保存**を選択します。
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Benefitsolver のセットアップ** ] セクションで、要件に従って 1 つ以上の適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Benefitsolver SSO の構成

**Benefitsolver** 側でシングル サインオンを構成するには、ダウンロードした**メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[Benefitsolver サポート チーム](https://www.businessolver.com/contact-us/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

手記

Benefitsolver サポート チームは、実際の SSO 構成を行う必要があります。 サブスクリプションに対して SSO が有効になっていると、通知が表示されます。

#### Benefitsolver テスト ユーザーの作成

このセクションでは、Benefitsolver で Britta Simon というユーザーを作成します。 Benefitsolver サポート チーム  と連携して、Benefitsolver プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる Benefitsolver のサインオン URL にリダイレクトされます。
- Benefitsolver のサインオン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Benefitsolver タイルを選択すると、このオプションは Benefitsolver のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/benq-iam-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に BenQ IAM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/benq-iam-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-24
- Summary: Microsoft Entra ID から BenQ IAM にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために BenQ IAM と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 設定すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、BenQ IAM [を](https://service-portal.benq.com/login) にユーザーとグループのプロビジョニングおよび解除を自動的に行います。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- BenQ IAM でユーザーを作成する
- アクセスが不要になった場合に BenQ IAM のユーザーを削除する
- Microsoft Entra ID と BenQ IAM の間でユーザー属性の同期を維持する
- [BenQ IAM へのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/benq-iam-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成する  アクセス許可を持つ Microsoft Entra ID のユーザー アカウント ([アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、アプリケーション所有者 など)。
- BenQ IAM を持つ管理者アカウント。

### 手順 1: プロビジョニングの配置を計画する

1. プロビジョニング サービスの [のしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)について説明します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と BenQ IAM の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように BenQ IAM を構成する

1. BenQ 管理者アカウントを使用して BenQ IAM ポータルにサインインし、[アカウント管理] セクションで **[SSO 設定** ] を選択します。 [Image: SSO 設定]
2. ポップアップ **で [SSO by SAML** as SSO Setting] を選択し、[次へ] を選択します。 [Image: sso-with-saml] する
3. [Microsoft Entra SSO と BenQ IAM の統合に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/benq-iam-tutorial)関する記事に従って、必要な設定を完了します。
4. SAML による SSO の設定が完了すると、次の図に示すように成功メッセージが表示されます。 [自動ユーザー プロビジョニング] セクションで [ **トークンの作成** ] を選択します。 [Image: created-token] した
5. トークンを安全な場所にコピーします。 このトークンは、 **手順 5** で Azure portal で使用されます。 [Image: をコピーするトークン]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから BenQ IAM を追加する

Microsoft Entra アプリケーション ギャラリーから BenQ IAM を追加して、BenQ IAM へのプロビジョニングの管理を開始します。 SSO 用に BenQ IAM を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: BenQ IAM への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で BenQ IAM の自動ユーザー プロビジョニングを構成するには:

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **BenQ IAM**を選択します。

    アプリケーションの一覧の [BenQ IAM] リンクを
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、BenQ IAM テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が BenQ IAM に接続できることを確認します。 接続に失敗した場合は、BenQ IAM アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. **属性マッピング** セクションで、Microsoft Entra ID から BenQ IAM に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で BenQ IAM のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、BenQ IAM API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[** 保存] ボタンを選択して、変更をコミットします。

    | 属性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | externalId | 糸 |  |
    | アクティブ | ブール値 |  |
    | displayName | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/benq-iam-tutorial"} -->
## Microsoft Entra ID で BenQ IAM for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/benq-iam-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BenQ IAM の間のシングル サインオンを構成する方法について説明します。

この記事では、BenQ IAM と Microsoft Entra ID を統合する方法について説明します。 BenQ IAM を Microsoft Entra ID と統合すると、次のことが可能になります。

- BenQ IAM にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで BenQ IAM に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- BenQ IAM でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- BenQ IAM では、**SP および IDP** による SSO がサポートされます。

### ギャラリーから BenQ IAM を追加する

BenQ IAM と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に BenQ IAM をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. 以下の手順で **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション** に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「BenQ IAM」**と入力します。
4. 結果パネルから **BenQ IAM** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BenQ IAM 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、BenQ IAM に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、BenQ IAM での関連ユーザーとの間にリンク関係を確立する必要があります。

BenQ IAM 用に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BenQ IAM SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **BenQ IAM のテスト ユーザーを作成する** - BenQ IAM で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間でリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**BenQ IAM**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://service-portal.benq.com/saml/init/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://service-portal.benq.com/saml/consume/<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **ログアウト URL** ] テキスト ボックスに、URL を入力します。 `https://service-portal.benq.com/logout`

    Note

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [BenQ IAM クライアント サポート チーム](mailto:benqcare.us@benq.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. BenQ IAM アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
8. その他に、BenQ IAM アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | displayName | user.displayname |
    | externalId | user.objectid (ユーザーのオブジェクトID) |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **BenQ IAM のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BenQ IAM の SSO の構成

1. BenQ 管理者アカウントで BenQ IAM にログインし、[アカウント管理] セクションで **[SSO 設定** ] を選択します。

    [Image: SSO 設定のスクリーンショット]
2. [ **SSO 設定]** で、[ **SSO by SAML** ] を選択し、[ **次へ**] を選択します。
3. **[SSO 設定]** ページで次の手順を実行します。

    [Image: SSO 構成のスクリーンショット]

    a. [ **Login/SSO URL]\(ログイン/SSO URL** \) ボックスに、前にコピーした **ログイン URL** 値を貼り付けます。

    b。 [ **識別子/エンティティ ID** ] ボックスに、前にコピーした **識別子** の値を貼り付けます。

    c. ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容を **証明書 (Base64)** ボックスに貼り付けます。

    d. 識別子の値**を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスにこの値を貼り付けます。

    e. **[応答 URL]** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。

    f. [ **次へ**] を選択します。

#### BenQ IAM のテスト ユーザーの作成

このセクションでは、BenQ IAM で Britta Simon というユーザーを作成します。 [BenQ IAM サポート チーム](mailto:benqcare.us@benq.com)と協力して、BenQ IAM プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログインフローを開始できる BenQ IAM サインオン URL にリダイレクトされます。
- BenQ IAM のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した BenQ IAM に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで BenQ IAM タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した BenQ IAM に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/benselect-tutorial"} -->
## Microsoft Entra ID で BenSelect for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/benselect-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BenSelect の間のシングル サインオンを構成する方法について説明します。

この記事では、BenSelect と Microsoft Entra ID を統合する方法について説明します。 BenSelect を Microsoft Entra ID と統合すると、次のことが可能になります。

- BenSelect にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで BenSelect に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- BenSelect でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- BenSelect では、 **IDP** Initiated SSO がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの BenSelect の追加

Microsoft Entra ID への BenSelect の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に BenSelect を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「BenSelect**」と入力します。
4. 結果パネルから **BenSelect** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BenSelect の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、BenSelect に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと BenSelect の関連ユーザーとの間にリンク関係を確立する必要があります。

BenSelect に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BenSelect SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **BenSelect のテスト ユーザーの作成** - BenSelect で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[BenSelect]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.benselect.com/enroll/login.aspx?Path=<tenant name>`

    Note

    これは実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには [、BenSelect クライアント サポート チーム](mailto:support@selerix.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. BenSelect アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: スクリーンショットは、givenname user.givenname や emailaddress user.mail などの既定の属性を持つユーザー属性を示しています。]
7. [ **編集** ] アイコンを選択して **、[名前識別子] の値**を編集します。

    [Image: スクリーンショットは、[編集] アイコンが強調表示された [ユーザー属性と要求] ウィンドウを示しています。]
8. [ **ユーザー要求の管理** ] セクションで、次の手順を実行します。

    [Image: この手順で説明する値を入力できるユーザー要求の管理を示すスクリーンショット。]

    a. **変換元**として **[変換**] を選択します。

    b。 [ **変換** ] ドロップダウン リストで、[ **ExtractMailPrefix()**] を選択します。

    c. **[パラメーター 1**] ドロップダウン リストで、**user.userprincipalname** を選択します。

    d. **[保存] を選択します**。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **BenSelect のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BenSelect SSO の構成

**BenSelect** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、アプリケーション構成からコピーした適切な URL を [BenSelect サポート チーム](mailto:support@selerix.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

Note

この統合では、app2101 などの適切なサーバーで SSO を設定するために SHA256 アルゴリズム (SHA1 はサポートされていません) が必要であることを説明する必要があります。

#### BenSelect テスト ユーザーの作成

このセクションでは、BenSelect で Britta Simon というユーザーを作成します。 [BenSelect サポート チーム](mailto:support@selerix.com)と協力して、BenSelect プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した BenSelect に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [BenSelect] タイルを選択すると、SSO を設定した BenSelect に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bentley-automatic-user-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Bentley - Automatic User Provisioning を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bentley-automatic-user-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-24
- Summary: Microsoft Entra ID から Bentley - Automatic User Provisioning に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために、Bentley - Automatic User Provisioning と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成されると、Microsoft Entra ID では、Microsoft Entra プロビジョニング サービスを使用して [Bentley - Automatic User Provisioning](https://www.bentley.com) に対してユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Bentley - Automatic User Provisioning でのユーザーの作成
- Bentley でアクセスが不要になった場合は、ユーザーを自動ユーザープロビジョニングから削除します。
- Microsoft Entra ID と Bentley - Automatic User Provisioning の間で同期されているユーザー属性を維持する
- Bentley - Automatic User Provisioning にグループとグループ メンバーシップをプロビジョニングする
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Bentley IMS とフェデレーションされたアカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Bentley - Automatic User Provisioning の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Bentley - Automatic User Provisioning を構成する

テナント URL と シークレット トークンについて、Bentley User Provisioning [サポート](https://communities.bentley.com/communities/other_communities/licensing_cloud_and_web_services/w/wiki/52836/microsoft-azure-ad-automatic-user-provisioning-configuration) チームに確認します。 これらの値は、Bentleyアプリケーションの[プロビジョニング]タブに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Bentley - Automatic User Provisioning を追加する

Microsoft Entra アプリケーション ギャラリーから Bentley - Automatic User Provisioning を追加して、Bentley - Automatic User Provisioning へのプロビジョニングの管理を開始します。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングする対象を設定する場合は、[アプリケーションにユーザーとグループを割り当てる手順](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)を使用できます。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、[アプリケーション マニフェストを更新して](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)新しいロールを追加してください。

### 手順 5: Bentley - Automatic User Provisioning への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Bentley - Automatic User Provisioning に対して自動ユーザー プロビジョニングを構成するには、次のようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Bentley - Automatic User Provisioning]** を選択します。

    [Image: アプリケーションの一覧の [Bentley - Automatic User Provisioning] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Bentley - Automatic User Provisioning Tenant URL と Secret Token を入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Bentley - Automatic User Provisioning に接続できることを確認します。 接続に失敗した場合は、Bentley - Automatic User Provisioning アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Bentley - Automatic User Provisioning に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Bentley - Automatic User Provisioning のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Bentley - Automatic User Provisioning API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | タイトル | 糸 |  |
    | emails[type eq "work"].value | 糸 |  |
    | 優先言語 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | addresses[type eq "work"].streetAddress | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | addresses[type eq "work"].region | 糸 |  |
    | addresses[type eq "work"].postalCode | 糸 |  |
    | addresses[type eq "work"].country | 糸 |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:Bentley:2.0:User:isSoftDeleted | 糸 |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Bentley - Automatic User Provisioning に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Bentley - Automatic User Provisioning のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | externalId | 糸 |  |
    | members | リファレンス |  |
    | urn:ietf:params:scim:schemas:extension:Bentley:2.0:Group:description | 糸 |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バーの](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/better-stack-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Better Stack を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/better-stack-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-24
- Summary: Microsoft Entra ID から Better Stack に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Better Stack と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Better Stack](https://betterstack.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Better Stack でユーザーを作成する。
- アクセスが不要になったら、Better Stack のユーザーを削除します。
- Microsoft Entra ID と Better Stack の間でユーザー属性の同期を維持する。
- Better Stack でグループとグループ メンバーシップをプロビジョニングする。
- Better Stack に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Admin アクセス許可がある Better Stack のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Better Stack の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID を使用してプロビジョニングをサポートするように Better Stack を構成する

Microsoft Entra プロビジョニングは、Better Stack ダッシュボード内のシングル サインオン設定で構成できます。 有効にすると、以下のプロビジョニング設定で使用できる **テナント ID** と **シークレット トークン** が表示されます。 サポートが必要な場合は、 [Better Stack サポート](mailto:hello@betterstack.com)にお問い合わせください。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Better Stack を追加する

Microsoft Entra アプリケーション ギャラリーから Better Stack を追加して、Better Stack へのプロビジョニングの管理を開始します。 SSO のために以前 Better Stack を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Better Stack への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Better Stack の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**を閲覧する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Better Stack**] を選択します。

    [Image: アプリケーションの一覧の [Better Stack] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Better Stack テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Better Stack に接続できることを確認します。 接続に失敗した場合は、Better Stack アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. [属性マッピング] セクションで、Microsoft Entra ID から Better Stack に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために Better Stack のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Better Stack API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Better Stack で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | externalId | 糸 |  |  |
    | タイムゾーン | 糸 |  |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から Better Stack に同期されるグループ **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Better Stack のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Better Stack で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  |  |
    | members | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/betterworks-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Betterworks を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/betterworks-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Betterworks の間でシングル サインオンを構成する方法について説明します。

この記事では、Betterworks と Microsoft Entra ID を統合する方法について説明します。 Betterworks と Microsoft Entra ID を統合すると、次のことができます。

- Betterworks にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Betterworks に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Betterworks でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Betterworks では、**SPおよびIDPによって開始されたSSO**がサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### Betterworks をギャラリーから追加する

Microsoft Entra ID への Betterworks の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Betterworks を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;に移動し、**エンタープライズアプリ**&gt;と**新規アプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Betterworks**」と入力します。
4. 結果のパネルから Betterworks  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Betterworks の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Betterworks に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Betterworks の関連ユーザーとの間にリンク関係を確立する必要があります。

Betterworks に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Betterworks SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Betterworks テストユーザーを作成し**、Microsoft Entra のユーザー表現にリンクされた B.Simon の代替を Betterworks に持たせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Betterworks**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://app.betterworks.com/saml2/metadata/`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://app.betterworks.com/saml2/acs/`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.betterworks.com`

    手記

    Betterworks の欧州連合のお客様は、これらの URL では `eu.betterworks.com` ではなく、ドメイン名として `app.betterworks.com` を使用してください。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Betterworks のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Betterworks SSO の構成

**Betterworks** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Betterworks サポート チーム](mailto:support@betterworks.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Betterworks テスト ユーザーの作成

このセクションでは、Betterworks で Britta Simon というユーザーを作成します。 Betterworks サポート チーム  と連携して、Betterworks プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Betterworks のサインオン URL にリダイレクトされます。
- Betterworks のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Betterworks に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Betterworks タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Betterworks に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/beyond-identity-admin-console-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Beyond Identity Admin Console を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/beyond-identity-admin-console-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Beyond Identity Admin Console の間でシングル サインオンを構成する方法について説明します。

この記事では、Beyond Identity Admin Console と Microsoft Entra ID を統合する方法について説明します。 Beyond Identity Admin Console を Microsoft Entra ID と統合すると、次のことができます。

- Beyond Identity Admin Console にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Beyond Identity Admin Console に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Beyond Identity Admin Console でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Beyond Identity Admin Console では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの Beyond Identity Admin Console の追加

Microsoft Entra ID への Beyond Identity Admin Console の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Beyond Identity Admin Console を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Beyond Identity Admin Console」と**入力します。
4. 結果パネルから [ **Beyond Identity Admin Console]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Beyond Identity Admin Console に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Beyond Identity Admin Console に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Beyond Identity Admin Console の関連ユーザーとの間にリンク関係を確立する必要があります。

Beyond Identity Admin Console に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Beyond Identity Admin Console の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Beyond Identity Admin Console テスト ユーザーを作成 - Beyond Identity Admin Console で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Beyond Identity Admin Console]**&gt;**[シングル サイン オン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://admin.byndid.com/auth/saml/<azure-tenant-id>/sso/metadata.xml`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://admin.byndid.com/auth/?org_id=<bi-tenant-id>`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、Beyond Identity Admin Console クライアント サポート チーム](mailto:support@beyondidentity.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Beyond Identity Admin Console アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Beyond Identity Admin Console アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | Namespace | ソース属性 |
    | --- | --- | --- |
    | 変更不可能ID (immutableId) | externalId | user.immutableId |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **識別管理コンソールの設定** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Beyond Identity Admin Console の SSO の構成

**Beyond Identity Admin Console** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Beyond Identity Admin Console サポート チーム](mailto:support@beyondidentity.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Beyond Identity Admin Console のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Beyond Identity Admin Console に作成します。 [Beyond Identity Admin Console サポート チーム](mailto:support@beyondidentity.com)と協力して、Beyond Identity Admin Console プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Beyond Identity Admin Console のサインオン URL にリダイレクトされます。
- Beyond Identity Admin Console のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Beyond Identity Admin Console] タイルを選択すると、このオプションは Beyond Identity Admin Console のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bgsonline-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に BGS Online を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bgsonline-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BGS Online の間のシングル サインオンを構成する方法について説明します。

この記事では、BGS Online と Microsoft Entra ID を統合する方法について説明します。 BGS Online を Microsoft Entra ID と統合すると、次の利点があります。

- BGS Online にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して BGS Online に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- BGS Online でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- BGS Online では、**IDP** によって開始される SSO がサポートされます

### ギャラリーからの BGS Online の追加

Microsoft Entra ID への BGS Online の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に BGS Online を追加する必要があります。

**ギャラリーから BGS Online を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーから追加**] セクションに「**BGS Online**」と入力し、結果パネルで **[BGS Online**] を選択し、[**追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の BGS Online]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、BGS Online で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと BGS Online の関連ユーザー間にリンク関係を確立する必要があります。

BGS Online で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **BGS Online シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **BGS Online のテスト ユーザーを作成** - BGS Online で Britta Simon に対応するユーザーを作り、Microsoft Entra のユーザーにリンクします。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

BGS Online で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**BGS Online** アプリケーション統合ページを参照し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    [Image: BGS Online ドメインとURLのシングルサインオン情報]

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。

    運用環境の場合は、次の形式を使用します。`https://<company name>.millwardbrown.report`

    テスト環境の場合は、次の形式を使用します。`https://millwardbrown.marketingtracker.nl/mt5/`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。

    運用環境の場合は、次の形式を使用します。`https://<company name>.millwardbrown.report/sso/saml/AssertionConsumerService.aspx`

    テスト環境の場合は、次の形式を使用します。`https://millwardbrown.marketingtracker.nl/mt5/sso/saml/AssertionConsumerService.aspx`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、BGS Online サポート チーム](mailto:bgsdashboardteam@millwardbrown.com) に問い合わせてください。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **BGS Online のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### BGS Online シングル サインオンの構成

**BGS Online** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [BGS Online サポート チーム](mailto:bgsdashboardteam@millwardbrown.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### BGS Online のテスト ユーザーの作成

このセクションでは、BGS Online で Britta Simon というユーザーを作成します。 [BGS Online サポート チーム](mailto:bgsdashboardteam@millwardbrown.com)と協力して、BGS Online プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [BGS Online] タイルを選択すると、SSO を設定した BGS Online に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bic-cloud-design-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に BIC Cloud Design を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bic-cloud-design-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-24
- Summary: Microsoft Entra ID から BIC Cloud Design へのユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために BIC Cloud Design と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [BIC Cloud Design](https://www.gbtec.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- BIC Cloud Design でユーザーを作成する。
- アクセスが不要になったら、BIC Cloud Design のユーザーを削除します。
- Microsoft Entra ID と BIC Cloud Design 間でユーザー属性の同期を維持する
- BIC Cloud Design でグループとグループ メンバーシップをプロビジョニングする。
- BIC Cloud Design に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bic-cloud-design-tutorial)します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- BIC Cloud Design User Management API が有効なサブスクリプション。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と BIC Cloud Design の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように BIC Cloud Design を構成する

Microsoft Entra ID を使用したプロビジョニングをサポートするように BIC Cloud Design を構成するには、 [BIC Cloud Design サポート チーム](mailto:bicsupport@gbtec.de)にメールを送信してください。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから BIC Cloud Design を追加する

Microsoft Entra アプリケーション ギャラリーから BIC Cloud Design を追加して、BIC Cloud Design へのプロビジョニングの管理を開始します。 SSO のために BIC Cloud Design を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: BIC Cloud Design への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて BIC Cloud Design 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で BIC Cloud Design の自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**に移動して参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で [ **BIC Cloud Design**] を選択します。

    [Image: アプリケーションの一覧の [BIC Cloud Design] リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、BIC Cloud Design テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が BIC Cloud Design に接続できることを確認します。 接続に失敗した場合は、BIC Cloud Design アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. [属性マッピング] セクションで、Microsoft Entra ID から BIC Cloud Design に同期されるユーザー **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作のために BIC Cloud Design のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が BIC Cloud Design API でサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | BIC Cloud Design で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "work"].value | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  | ✓ |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から BIC Cloud Design に同期されるグループ **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作のために BIC Cloud Design のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | BIC Cloud Design で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | members | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bic-cloud-design-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に BIC Process Design を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bic-cloud-design-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と BIC Process Design 間にシングル サインオンを構成する方法について学習します。

この記事では、BIC Process Design と Microsoft Entra ID を統合する方法について説明します。 BIC Process Design を Microsoft Entra ID と統合すると、以下のことが可能になります。

- BIC Process Design にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して BIC Process Design に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

BIC プロセス設計は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- BIC Process Design でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- BIC Process Design では、 **SP** によって開始される SSO がサポートされます。
- BIC プロセス設計では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bic-cloud-design-provisioning-tutorial)。

### ギャラリーから BIC Process Design を追加する

BIC Process Design の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に、BIC Process Design を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「BIC Process Design**」と入力します。
4. 結果パネルから **[BIC Process Design** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BIC Process Design 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、BIC Process Design に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと BIC Process Design の関連ユーザーとの間にリンク関係を確立する必要があります。

BIC Process Design に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BIC Process Design の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **BIC プロセス デザインのテスト ユーザーを作成** - BIC プロセス デザインで B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現とリンクさせるためです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDエンタープライズ アプリBIC Process Designシングルサインオンまで移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    ａ [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションで **識別子** の値が自動的に設定されます。

    d. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://<CUSTOMER_SPECIFIC_NAME/TENANT>.biccloud.com` |
    | `https://<CUSTOMER_SPECIFIC_NAME/TENANT>.biccloud.de` |

    注

    **識別子**の値が自動的に設定されない場合は、要件に従って値を手動で入力してください。 サインオン URL の値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには [、BIC プロセス デザイン クライアント サポート チーム](mailto:bicsupport@gbtec.de) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. BIC Process Design アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、BIC Process Design アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名前 | ユーザー名 |
    | メルアド | ユーザーのメールアドレス |
    | 名前 ID | ユーザー.ユーザープリンシパルネーム |
    | メール | ユーザーのメールアドレス |
    | 名前テスト | ユーザーの表示名 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BIC Process Design SSO の構成

**BIC プロセス デザイン**側でシングル サインオンを構成するには、**BIC プロセス 設計サポート チーム**に[アプリのフェデレーション メタデータ URL を](mailto:bicsupport@gbtec.de)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### BIC Process Design のテスト ユーザーの作成

このセクションでは、BIC Process Design で B.Simon というユーザーを作成します。 [BIC プロセス 設計サポート チーム](mailto:bicsupport@gbtec.de)と協力して、BIC プロセス 設計プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる BIC Process Design のサインオン URL にリダイレクトされます。
- BIC Process Design のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [BIC プロセス デザイン] タイルを選択すると、このオプションは BIC プロセス デザインのサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bigpanda-tutorial"} -->
## Microsoft Entra ID で BigPanda for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bigpanda-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BigPanda の間のシングル サインオンを構成する方法について説明します。

この記事では、BigPanda と Microsoft Entra ID を統合する方法について説明します。 BigPanda は、IT データを実用性のあるインテリジェンスと自動化に変換することで、インシデント対応チームがアップタイム、効率、ベロシティを向上できるようにします。 Microsoft Entra ID と BigPanda を統合すると、次のことが可能になります。

- BigPanda にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで BigPanda に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で BigPanda の Microsoft Entra シングル サインオンを構成してテストします。 BigPanda では、 **SP** と **IDP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングの両方がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID と BigPanda を統合するためには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン ロールがフル アクセスに設定されている BigPanda アカウント。 詳細については、BigPanda ドキュメントの [ロールとリソースのアクセス許可](https://docs.bigpanda.io/docs/roles-management#roles-and-resource-permissions) を参照してください。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから BigPanda アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから BigPanda を追加する

Microsoft Entra アプリケーション ギャラリーから BigPanda を追加して、BigPanda のシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[BigPanda]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、URL を入力します。 `https://bigpanda.io/SAML2`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://api.bigpanda.io/login/<ORG_NAME>/azure/callback`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://api.bigpanda.io/login/<INSTANCE>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択してファイルをダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **BigPanda のセットアップ** ] セクションで、 **ログイン URL をコピーします**。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### BigPanda の SSO を構成する

**BigPanda** 側でシングル サインオンを構成するには、[BigPanda ドキュメント](https://docs.bigpanda.io/en/microsoft-entra-id--formerly-azure-ad-)の指示に従ってください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる BigPanda のサインオン URL にリダイレクトされます。
- BigPanda のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した BigPanda に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [BigPanda] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した BigPanda に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bime-tutorial"} -->
## Microsoft Entra ID で Bime for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bime-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Bime 間のシングル サインオンを構成する方法について説明します。

この記事では、Bime と Microsoft Entra ID を統合する方法について説明します。 Bime を Microsoft Entra ID と統合すると、次のことが可能になります。

- Bime にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Bime に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Bime でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Bime では、**SP** Initiated SSO がサポートされます。

### ギャラリーから Bime を追加する

Microsoft Entra ID への Bime の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Bime を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Bime**」と入力します。
4. 結果のパネルから **[Bime]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Bime に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用し、Bime に対して Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Bime の関連ユーザーとの間にリンク関係を確立する必要があります。

Bime で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Bime SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Bimeテスト ユーザーの作成** - Bime で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Bime]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-name>.Bimeapp.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-name>.Bimeapp.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 この値を取得するには、[Bime クライアント サポート チーム](https://bime.zendesk.com/hc/categories/202604307-Support-tech-notes-and-tips-)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. **[Bime の設定]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Bime SSO を構成する

1. 別の Web ブラウザー ウィンドウで、Bime 企業サイトに管理者としてログインします。
2. ツール バーで、[ **管理者**] を選択し、[アカウント] を選択 **します**。

    [Image: スクリーンショットは、[管理者] および [アカウント] が選択されていることを示しています。]
3. アカウント構成ページで、次の手順を実行します。

    [Image: Configure single sign-on]

    a. **[SAML 認証を有効にする]** を選択します。

    b。 **[リモート ログイン URL]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    c. **[証明書指紋]** テキストボックスに、証明書の**拇印**の値を貼り付けます。

    d. **保存** を選択します。

#### Bime テスト ユーザーの作成

Microsoft Entra ユーザーが Bime にログインできるようにするには、ユーザーを Bime にプロビジョニングする必要があります。 Bime の場合、プロビジョニングは手動で行います。

**ユーザー プロビジョニングを構成するには、次の手順を実行します。**

1. **Bime** テナントにログインします。
2. ツール バーで、[ **管理者**] を選択し、[ユーザー] を選択 **します**。

    [Image: スクリーンショットは、[管理者] および [ユーザー] が選択されていることを示しています。]
3. **[ユーザー] リスト**で、[**新しいユーザーの追加 ]** ("+") を選択します。

    [Image: ユーザー]
4. [**User Details**] ダイアログ ページで、以下の手順を実行します。

    [Image: ユーザー情報]

    a. [ **名** ] ボックスに、 **Britta** などのユーザーの名を入力します。

    b。 **[Last name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (この例では **Simon**)。

    c. [ **電子メール** ] ボックスに、ユーザーの電子メール ( **brittasimon@contoso.com**など) を入力します。

    d. **保存** を選択します。

注

他の Bime ユーザー アカウント作成ツールや、Bime から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Bime サインオン URL にリダイレクトされます。
- Bime のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Bime] タイルを選択すると、このオプションは Bime のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/birst-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Birst Agile Business Analytics を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/birst-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Birst Agile Business Analytics の間でシングル サインオンを構成する方法について説明します。

この記事では、Birst Agile Business Analytics と Microsoft Entra ID を統合する方法について説明します。 Birst Agile Business Analytics を Microsoft Entra ID と統合すると、次のことができます:

- Birst Agile Business Analytics にアクセスできる Microsoft Entra ID ユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Birst Agile Business Analytics に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Birst Agile Business Analytics でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Birst Agile Business Analytics では、 **SP** によって開始される SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Birst Agile Business Analytics の追加

Microsoft Entra ID への Birst Agile Business Analytics の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Birst Agile Business Analytics を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加]** セクションで、検索ボックス**に「Birst Agile Business Analytics**」と入力します。
4. 結果パネルから **Birst Agile Business Analytics** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Birst Agile Business Analytics の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Birst Agile Business Analytics に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Birst Agile Business Analytics の関連ユーザーとの間にリンク関係を確立する必要があります。

Birst Agile Business Analytics に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Birst Agile Business Analytics の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Birst Agile Business Analytics のテスト ユーザーの作成** - Birst Agile Business Analytics で B.Simon に対応するユーザーを作成し、Microsoft Entra でのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Birst Agile Business Analytics**&gt;**シングルサインオンに**移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.bws.birst.com/SAMLSSO/Services.aspx?birst.idpid=<TENANTIDPID>`

    この URL は、Birst アカウントが存在するデータセンターによって異なります。

    - 米国のデータセンターでは、`https://login.bws.birst.com/SAMLSSO/Services.aspx?birst.idpid=<TENANTIDPID>` というパターンを使用します。
    - ヨーロッパのデータセンターでは、`https://login.eu1.birst.com/SAMLSSO/Services.aspx?birst.idpid=<TENANTIDPID>` というパターンを使用します。

        注意

        この値は実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値を取得するには、 [Birst Agile Business Analytics クライアント サポート チーム](mailto:info@birst.com) に問い合わせてください。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Birst Agile Business Analytics のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 設定を適切な U R L にコピーするためのスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Birst Agile Business Analytics SSO の構成

**Birst Agile Business Analytics** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Birst Agile Business Analytics サポート チーム](mailto:info@birst.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

注意

この統合には、 **app2101** などの適切なサーバーで SSO を設定できるように、SHA256 アルゴリズム (SHA1 はサポートされていません) が必要であることを Birst チームに説明します。

#### Birst Agile Business Analytics のテスト ユーザーの作成

このセクションでは、Birst Agile Business Analytics で Britta Simon というユーザーを作成します。 [Birst Agile Business Analytics サポート チーム](mailto:info@birst.com)と協力して、Birst Agile Business Analytics プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Birst Agile Business Analytics のサインオン URL にリダイレクトされます。
- Birst Agile Business Analytics のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Birst Agile Business Analytics] タイルを選択すると、このオプションは Birst Agile Business Analytics のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bis-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に BIS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bis-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-27
- Summary: Microsoft Entra IDから BIS にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために BIS とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスによって、[BIS](https://www.trainanddevelop.ca/) へのユーザーのプロビジョニングと解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- BIS でユーザーを作成する。
- 不要になった場合は、BISからユーザーを削除します。
- Microsoft Entra IDと BIS の間でユーザー属性の同期を維持します。
- BIS への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bis-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- BIS の管理者アカウント。
- 国/地域は、完全な名前ではなく、2 文字または 3 文字のコードとして渡す必要があります。
- BIS 内のすべての既存のアカウントで、重複するアカウントの作成を回避するために、Microsoft Entra IDとデータが同期されていることを確認します (たとえば、Microsoft Entra IDの電子メールは BIS の電子メールと一致する必要があります)。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとBISの間で対応付けるデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように BIS を構成する

承認の資格情報を取得するには、 [BIS サポート](mailto:help@bistrainer.com) またはアカウントのマネージャーにお問い合わせください。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから BIS を追加する

Microsoft Entra アプリケーション ギャラリーから BIS を追加して、BIS へのプロビジョニングの管理を開始します。 SSO 用に BIS を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: BIS に対する自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーまたはグループの割り当てに基づいて、TestApp でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順を説明します。

#### Microsoft Entra IDで BIS の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **BIS を選択**します。

    [Image: アプリケーションの一覧の BIS リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、BIS テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが BIS に接続できることを確認します。 接続に失敗した場合は、BIS アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから BIS に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で BIS のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、BIS API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | BIS で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  | ✓ |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  | ✓ |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | externalId | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | name.middleName | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:BIS:2.0:User:location | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:BIS:2.0:User:startdate | 日付と時間 |  |  |
    | urn:ietf:params:scim:schemas:extension:BIS:2.0:User:terminationdate | 日付と時間 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bis-tutorial"} -->
## Microsoft Entra ID で BIS for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bis-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BIS の間のシングル サインオンを構成する方法について説明します。

この記事では、BIS と Microsoft Entra ID を統合する方法について説明します。 BIS を Microsoft Entra ID と統合すると、次のことが可能になります。

- BIS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで BIS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- BIS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- BIS では、 **SP** Initiated SSO がサポートされます。
- BIS では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの BIS の追加

BIS と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に BIS をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックスに **「BIS** 」と入力します。
4. 結果パネルから **BIS** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BIS の Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、BIS に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、BIS での関連ユーザーとの間にリンク関係を確立する必要があります。

BIS の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **BIS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. SSO 構成を初期化します。
2. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
3. **BIS 側で SSO 構成を完了** し、アプリケーション側で構成を完了します。
4. **SSO のテスト** - 構成が機能するかどうかを確認します。

### BIS の SSO の構成

**BIS** 側でシングル サインオンを構成するには、[統合] ページにアクセスできる **BIS のクライアント セキュリティ管理者**ロール ユーザーが必要です。 次の手順に従います。

1. BIS にサインインします。
2. [会社の設定] に移動し、[統合] ページを開きます。
3. [SSO] セクションで、[ **SSO の開始** ] ボタンを選択します。
4. サインオン URL、エンティティ ID、および応答 URL をコピーします。

#### 必要に応じて BIS テスト ユーザーを作成する

BIS では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 BIS にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[BIS]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子 (エンティティ ID)]** テキスト ボックスに、BIS からコピーしたエンティティ ID を入力するか貼り付けます。 パターン： `https://<www.bissafety.app|custom-domainname>/<tenant-name>`
    2. [ **サインオン URL** ] テキスト ボックスに、BIS からコピーしたサインオン URL を入力するか貼り付けます。 パターン： `https://<www.bissafety.app|custom-domainname>/sso/<tenant-name>cr.cfm`
    3. [ **応答 URL** ] テキスト ボックスに、BIS からコピーした応答 URL を入力するか貼り付けます。 パターン： `https://<www.bissafety.app|custom-domainname>/sso/<tenant-name>.cfm`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **BIS のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BIS 側で SSO 構成を完了する

BIS 側で SSO 構成を完了するには:

1. BIS にサインインします。
2. [会社の設定] に移動し、[統合] ページを開きます。
3. [SSO] セクションで、[ **SSO の開始**] をクリックします。
4. 手順 3 で、ダウンロードした **フェデレーション メタデータ XML** ファイルをアップロードし、[ **メタデータの更新**] をクリックします。
5. 次のセクションで要求属性を選択します。
6. [ **保存] を** クリックして SSO 構成を完了します。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始

- [ **このアプリケーションをテスト**する] を選択します。これにより、ログイン フローを開始できる BIS サインオン URL にリダイレクトされます。
- BIS サインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated

- [ **このアプリケーションをテスト**する] を選択します。SSO を設定した BIS インスタンスに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで BIS タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためにアプリケーションのサインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した BIS インスタンスに自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bitabiz-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に BitaBIZ を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bitabiz-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-27
- Summary: ユーザー アカウントを BitaBIZ に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、BitaBIZ で実行する手順と、ユーザーやグループを BitaBIZ に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するMicrosoft Entra IDを示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)..

- [BitaBIZ テナント](https://bitabiz.dk/en/price/)。
- 管理者アクセス許可がある BitaBIZ のユーザー アカウント。

### BitaBIZ へのユーザーの割り当て

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリにaccessを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザープロビジョニングを構成して有効にする前に、Microsoft Entra ID のどのユーザーやグループが BitaBIZ にアクセスする必要があるかを決定する必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを BitaBIZ に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを BitaBIZ に割り当てるときの重要なヒント

- 1 人の Microsoft Entra ユーザーを BitaBIZ に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- BitaBIZ にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **Default Access** ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニングのための BitaBIZ のセットアップ

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に BitaBIZ を構成する前に、BitaBIZ で SCIM プロビジョニングを有効にする必要があります。

1. [BitaBIZ 管理コンソール](https://www.bitabiz.com/login?lang=en)にサインインします。 [ **セットアップ管理者] を選択します**。

    [Image: BitaBIZ 管理コンソールのスクリーンショット。[Setup admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理設定) が強調表示されています。]
2. **[INTEGRATION](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合)** に移動します。

    [Image: BitaBIZ 管理コンソールのスクリーンショット。[Integration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) が強調表示されています。]
3. **[Microsoft Entra プロビジョニング]** に移動します。 自動ユーザー プロビジョニングで **[Enabled](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/有効)** を選択します。 **[SCIM Provisioning endpoint URL](SCIM プロビジョニング エンドポイント URL)** および **[Bearer Token](ベアラー トークン)** の値をコピーします。 これらの値は、BitaBIZ アプリケーションの [プロビジョニング] タブの [テナント URL] フィールドと [シークレット トークン] フィールドに入力されます。

    [Image: BitaBIZ での SCIM の追加]

### ギャラリーから BitaBIZ を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に BitaBIZ を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に BitaBIZ を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから BitaBIZ を追加するには、以下の手順を行います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、「**BitaBIZ**」と入力し、検索ボックスで **[BitaBIZ]** を選択します。
4. 結果パネルから **BitaBIZ** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: BitaBIZ の結果リスト]

### BitaBIZ への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて、BitaBIZ でユーザーやグループを作成、更新、無効化するために、Microsoft Entra プロビジョニング サービスを設定する手順について説明します。

ヒント

BitaBIZ のシングル サインオンに関する記事で説明されている手順に従って、BitaBIZ に対して SAML ベース [のシングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bitabiz-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは独立に構成できますが、これらの 2 つの機能は互いに補完しあいます。

#### Microsoft Entra IDで BitaBIZ の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[BitaBIZ]** を選択します。

    [Image: アプリケーションの一覧の BitaBIZ リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
6. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
7. [ **テナント URL** ] フィールドに、BitaBIZ テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが BitaBIZ に接続できることを確認します。 接続に失敗した場合は、BitaBIZ アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **Attribute Mapping** セクションで、Microsoft Entra IDから BitaBIZ に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で BitaBIZ のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: BitaBIZ ユーザー属性]
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「 [自動ユーザー アカウント プロビジョニングに関するレポート」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。

### コネクタの制限事項

- BitaBIZ には、必須属性として **userName**、**email**、**firstName**、**lastName** が必要です。
- BitaBIZ では、現在、ハード削除はサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bitabiz-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に BitaBIZ を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bitabiz-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BitaBIZ の間のシングル サインオンを構成する方法について説明します。

この記事では、BitaBIZ と Microsoft Entra ID を統合する方法について説明します。 BitaBIZ を Microsoft Entra ID と統合すると、次のことが可能になります。

- BitaBIZ へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで BitaBIZ に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- BitaBIZ でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- BitaBIZ では、**SPおよびIDPによるSSOのイニシエート**がサポートされます。
- BitaBIZ では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bitabiz-provisioning-tutorial)。

### ギャラリーから BitaBIZ を追加する

Microsoft Entra ID への BitaBIZ の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に BitaBIZ を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「BitaBIZ**」と入力します。
4. 結果パネルから **BitaBIZ** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BitaBIZ に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、BitaBIZ に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと BitaBIZ の関連ユーザーとの間にリンク関係を確立する必要があります。

BitaBIZ に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BitaBIZ SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **BitaBIZ テストユーザーの作成** - BitaBIZ において、Microsoft Entra の Britta Simon に対応するユーザーを作成し、リンクするためのものです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**BitaBIZ**&gt;**シングルサインオンへ**移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP 開始** モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.bitabiz.com/<INSTANCE_ID>`

    Note

    上記の URL の値は、単なる例です。 実際の識別子で値を更新します。これについては、この記事の後半で説明します。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.bitabiz.com/dashboard`
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **BitaBIZ のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BitaBIZ SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として BitaBIZ テナントにサインオンします。
2. [ **セットアップ管理者] を選択します**。

    [Image: [セットアップ管理者] が選択されているブラウザー ウィンドウの一部を示すスクリーンショット。]
3. [**値の追加]** セクション**で [Microsoft 統合**] を選択します。
4. **Microsoft Entra ID (シングル サインオンを有効にする)** セクションまで下にスクロールし、指定されたフィールドに適切な値を入力します。

    a. **エンティティ ID (Microsoft Entra ID の [識別子] )** ボックスから値をコピーし、Azure portal の [**基本的な SAML 構成**] セクションの **[識別子**] ボックスに貼り付けます。

    b。 **[Microsoft Entra Single Sign-On Service URL**] ボックスに、**ログイン URL を**貼り付けます。

    c. **Microsoft Entra SAML エンティティ ID** ボックスに、**Microsoft Entra Identifier** を貼り付けます。

    d. ダウンロードした **証明書 (Base64)** ファイルをメモ帳で開き、その内容をクリップボードにコピーして、 **Microsoft Entra ID 署名証明書 (Base64 エンコード)** ボックスに貼り付けます。

    e. ビジネス電子メール ドメイン名を追加します。つまり、[ **ドメイン名** ] ボックスに mycompany.com して、この電子メール ドメインを使用して社内のユーザーに SSO を割り当てます (必須ではありません)。

    f. **SSO をマーク**して BitaBIZ アカウントを有効にしました。

    g. [ **Microsoft Entra 構成の保存]** を選択して、SSO 構成を保存してアクティブ化します。

#### BitaBIZ テスト ユーザーの作成

Microsoft Entra ユーザーが BitaBIZ にログインできるようにするには、そのユーザーを BitaBIZ にプロビジョニングする必要があります。 BitaBIZ の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. BitaBIZ の企業サイトに管理者としてログインします。
2. [ **セットアップ管理者] を選択します**。

    [Image: [セットアップ管理者] が選択されているブラウザー ウィンドウの一部を示すスクリーンショット。]
3. [**組織**] セクションで [**ユーザーの追加]** を選択します。

    [Image: [ユーザーの追加] が選択されている [組織] セクションを示すスクリーンショット。]
4. [ **新しい従業員の追加] を選択します**。

    [Image: スクリーンショットでは「ユーザーの追加」で「新しい従業員の追加」が選択されています。]
5. [ **新しい従業員の追加** ] ダイアログ ページで、次の手順を実行します。

    [Image: この手順で説明する情報を入力するページを示すスクリーンショット。]

    a. **名** テキストボックスに、Britta などのユーザーの名前を入力します。

    b。 [ **姓]** ボックスに、ユーザーの姓 (Simon など) を入力します。

    c. [ **電子メール** ] ボックスに、ユーザーのメール アドレス ( Brittasimon@contoso.comなど) を入力します。

    d. [ **雇用日] で日付を選択します**。

    e. ユーザーに対して設定できる他の任意のユーザー属性があります。 詳細については、 [従業員設定に関するドキュメント](https://help.bitabiz.dk/manage-or-set-up-your-account/on-boarding-employees/new-employee) を参照してください。

    f. [ **従業員の保存] を選択します**。

    Note

    Microsoft Entra アカウント所有者が電子メールを受信し、リンクに従ってアカウントを確認すると、そのアカウントがアクティブになります。

Note

BitaBIZ では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bitabiz-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる BitaBIZ サインオン URL にリダイレクトされます。
- BitaBIZ のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した BitaBIZ に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [BitaBIZ] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した BitaBIZ に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bitbucket-tutorial"} -->
## Microsoft Entra ID のシングルサインオンのために resolution GmbH による Bitbucket 用の SAML SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bitbucket-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と SAML SSO for Bitbucket by resolution GmbH の間でシングル サインオンを構成する方法について説明します。

この記事では、SAML SSO for Bitbucket by resolution GmbH と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と SAML SSO for Bitbucket by resolution GmbH を統合すると、次のことができます。

- SAML SSO for Bitbucket by resolution GmbH にアクセスするユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで SAML SSO for Bitbucket by resolution GmbH に自動的にサインインするように設定する。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAML SSO for Bitbucket by resolution GmbH でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- resolution GmbH による Bitbucket 向け SAML SSO は、**SP**の開始によるSSOと**IDP**の開始によるSSOをサポートしています。
- SAML SSO for Bitbucket by resolution GmbH では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの SAML SSO for Bitbucket by resolution GmbH の追加

Microsoft Entra ID への SAML SSO for Bitbucket by resolution GmbH の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SAML SSO for Bitbucket by resolution GmbH を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**SAML SSO for Bitbucket by resolution GmbH**」と入力します。
4. 結果から **SAML SSO for Bitbucket by resolution GmbH** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### SAML SSO for Bitbucket by resolution GmbH に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SAML SSO for Bitbucket by resolution GmbH に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと SAML SSO for Bitbucket by resolution GmbH の関連ユーザーとの間にリンクされた関係を確立する必要があります。

SAML SSO for Bitbucket by resolution GmbH に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAML SSO for Bitbucket by resolution GmbH SSO**を構成して、アプリケーション側で単一 Sign-On 設定を構成します。
    1. **SAML SSO for Bitbucket by resolution GmbH テスト ユーザーの作成** - SAML SSO for Bitbucket by resolution GmbH で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

このセクションでは、Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SAML SSO for Bitbucket by resolution GmbH** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **単一の Sign-On 方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **IDP** 開始モードでアプリケーションを構成する場合は、[**基本的な SAML 構成**] セクションで次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/samlsso`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/samlsso`

    c. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/samlsso`

    Note

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [SAML SSO for Bitbucket by resolution GmbH クライアント サポート チーム](https://marketplace.atlassian.com/apps/1217045/saml-single-sign-on-sso-bitbucket?hosting=server&amp;tab=support) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAML SSO for Bitbucket by resolution GmbH の SSO の構成

1. SAML SSO for Bitbucket by resolution GmbH 企業サイトに管理者としてサインオンします。
2. メイン ツールバーの右側にある **[設定]** を選択します。
3. [ACCOUNTS] セクションに移動し、メニュー バーの **[SAML SingleSignOn** ] を選択します。

    [Image: The Samlsingle]
4. **[SAML SIngleSignOn プラグインの構成] ページ**で、[**IdP の追加]** を選択します。
5. [ **SAML ID プロバイダーの選択** ] ページで、指定されたフィールドに IdP の種類、名前、説明を入力します。

    a. [ **IdP の種類]** を **Microsoft Entra ID** として選択します。

    b。 [ **名前** ] ボックスに名前を入力します。

    c. [ **説明** ] ボックスに、説明を入力します。

    d. [ **次へ**] を選択します。
6. [ **ID プロバイダーの構成** ] ページで、[ **次へ**] を選択します。
7. [ **SAML IdP メタデータのインポート** ] ページで、[ **ファイルの読み込み** ] を選択して、以前にダウンロードした **メタデータ XML** ファイルをアップロードします。
8. [ **次へ**] を選択します。
9. [ **設定の保存] を選択します**。

    [Image: 保存]

### SAML SSO for Bitbucket by resolution GmbH のテスト ユーザーの作成

このセクションの目的は、SAML SSO for Bitbucket by resolution GmbH で Britta Simon というユーザーを作成することです。 SAML SSO for Bitbucket by resolution GmbH では Just-In-Time プロビジョニングがサポートされています。また、ユーザーを手動で作成することもできます。要件に従って [、SAML SSO for Bitbucket by resolution GmbH クライアント サポート チーム](https://marketplace.atlassian.com/plugins/com.resolution.atlasplugins.samlsso-bitbucket/server/support) にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる SAML SSO for Bitbucket by resolution GmbH サインオン URL にリダイレクトされます。
- SAML SSO for Bitbucket by resolution GmbH のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SAML SSO for Bitbucket by resolution GmbH に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SAML SSO for Bitbucket by resolution GmbH] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SAML SSO for Bitbucket by resolution GmbH に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bitly-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Bitly を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bitly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Bitly の間のシングル サインオンを構成する方法について説明します。

この記事では、Bitly と Microsoft Entra ID を統合する方法について説明します。 Bitly を Microsoft Entra ID と統合すると、次のことが可能になります。

- Bitly にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Bitly に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Bitly でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Bitly では、**SP と IDP** によって開始される SSO がサポートされます。
- Bitly では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Bitly の追加

Bitly と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に Bitly をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Bitly**」と入力します。
4. 結果のパネルから **[Bitly]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Bitly の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Bitly の Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Bitly での関連ユーザーとの間にリンク関係を確立する必要があります。

Bitly の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Bitly の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Bitly テスト ユーザーの作成** - Bitly で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Bitly**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://bitly.com/sso/<subdomain>/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://bitly.com/sso/<subdomain>?acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://bitly.com/sso/<subdomain>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 この値を取得するには、[Bitly クライアント サポート チーム](mailto:sso@bit.ly)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Bitly SSO の構成

**Bitly** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Bitly サポート チーム](mailto:sso@bit.ly)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Bitly テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Bitly に作成します。 Bitly では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Bitly にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Bitly サインオン URL にリダイレクトされます。
- Bitly のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Bitly に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Bitly] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Bitly に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bizagi-studio-for-digital-process-automation-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Bizagi Studio for Digital Process Automation を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bizagi-studio-for-digital-process-automation-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra IDから Bizagi Studio for Digital Process Automation にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Bizagi Studio for Digital Process Automation と、自動ユーザー プロビジョニングを構成するためにMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 これを行うように構成すると、Microsoft Entra ID で Microsoft Entra プロビジョニング サービスを使用して、[Bizagi Studio for Digital Process Automation](https://www.bizagi.com/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされる機能

- Bizagi Studio for Digital Process Automation でユーザーを作成します。
- アクセスが不要になったら、Bizagi Studio for Digital Process Automation のユーザーを削除します。
- Microsoft Entra IDと Bizagi Studio for Digital Process Automation の間でユーザー属性の同期を維持します。
- Bizagi Studio for Digital Process Automation への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bizagi-studio-for-digital-process-automation-tutorial) (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次のものが既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Bizagi Studio for Digital Process Automation バージョン 11.2.4.2X 以降。

### プロビジョニングのデプロイを計画する

計画のために次の手順に従います。

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープに含まれるユーザーを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)。
3. Microsoft Entra IDと Bizagi Studio for Digital Process Automation の間で[マップするデータ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を決定します。

### Microsoft Entra IDでのプロビジョニングをサポートするように構成する

Microsoft Entra IDを使用したプロビジョニングをサポートするように Bizagi Studio for Digital Process Automation を構成するには、次の手順に従います。

1. **管理者アクセス許可**を持つユーザーとして作業ポータルにサインインします。
2. **管理者**&gt;**セキュリティ**&gt;**OAuth 2 アプリケーション**に移動します。

    [Image: OAuth 2 アプリケーションが強調表示されている Bizagi のスクリーンショット。]
3. [**] を選択し、[**] を追加します。
4. **「許可の種類」**で、**「ベアラートークン」**を選択します。 **[許可されたスコープ] で**、[**API**] と [**USER SYNC**] を選択します。 次に、 **[保存]** を選択します。

    [Image: [許可の種類] と [許可されたスコープ] が強調表示されている [アプリケーションの登録] のスクリーンショット。]
5. **クライアント シークレット**をコピーして保存します。 Azure portalで、Bizagi Studio for Digital Process Automation アプリケーションの **Provisioning** タブで、クライアント シークレット値が **Secret Token** フィールドに入力されます。

    [Image: [クライアント シークレット] が強調表示されている Oauth のスクリーンショット。]

### Microsoft Entra ギャラリーからアプリケーションを追加する

Bizagi Studio for Digital Process Automation へのプロビジョニングの管理を開始するには、Microsoft Entra アプリケーション ギャラリーからアプリを追加します。 シングル サインオンのために Bizagi Studio for Digital Process Automation を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストする場合は、別のアプリを作成してください。 詳細については、「 [クイック スタート: Microsoft Entra テナントにアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### プロビジョニングの対象ユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 自動ユーザー プロビジョニングの構成

このセクションでは、ユーザーとグループを作成、更新、無効にするように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。 これは、Microsoft Entra IDのユーザーとグループの割り当てに基づいて、テスト アプリで行います。

#### Microsoft Entra ID で Bizagi Studio for Digital Process Automation の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: エンタープライズ アプリケーションとすべてのアプリケーションが強調表示されているAzure portalのスクリーンショット。]
3. アプリケーションの一覧で、 **Bizagi Studio for Digital Process Automation** を選択します。
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] が強調表示されている [管理] オプションのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Bizagi Studio for Digital Process Automation テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Bizagi Studio for Digital Process Automation に接続できることを確認します。 接続に失敗した場合は、Bizagi Studio for Digital Process Automation アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]

    - **テナント URL:** Bizagi SCIM エンドポイントを次の構造で入力します: `<Your_Bizagi_Project>/scim/v2/`。 たとえば、 `https://my-company.bizagi.com/scim/v2/`と指定します。
    - **シークレット トークン:** この値は、この記事で前述した手順から取得されます。
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Bizagi Studio for Digital Process Automation に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、Bizagi Studio for Digital Process Automation のユーザー アカウントを更新操作に照合するために使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Bizagi Studio for Digital Process Automation API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 [ **保存] を** 選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "work"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |

    カスタム拡張属性を追加するには、[ **Bizagi の属性リストの編集] &gt; [詳細オプションの表示]** に移動します。 カスタム拡張属性には **、urn:ietf:params:scim:schemas:extension:bizagi:2.0:UserProperties:** というプレフィックスを付ける必要があります。 たとえば、カスタム拡張属性が **IdentificationNumber** の場合、属性を **urn:ietf:params:scim:schemas:extension:bizagi:2.0:UserProperties:IdentificationNumber** として追加する必要があります。 [ **保存] を** 選択して変更をコミットします。

    [Image: 属性リストを編集します。]

    カスタム属性を追加する方法の詳細については、「アプリケーション属性の [カスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)」を参照してください。

注

サポートされるのは、基本的な型のプロパティのみです (String、Integer、Boolean、DateTime など)。 パラメトリック テーブルまたは複数の型にリンクされているプロパティはまだサポートされていません。

1. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
2. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
3. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「 [自動ユーザー アカウント プロビジョニングに関するレポート」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。

### デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bizagi-studio-for-digital-process-automation-tutorial"} -->
## Bizagi for Digital Process Automation を Microsoft Entra ID でシングル サインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bizagi-studio-for-digital-process-automation-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Bizagi for Digital Process Automation の間でシングル サインオンを構成する方法について説明します。

この記事では、Bizagi for Digital Process Automation Services または Server を Microsoft Entra ID と統合する方法について説明します。 Microsoft Entra ID と Bizagi for Digital Process Automation を統合すると、次のことができます。

- Bizagi for Digital Process Automation サービスまたはサーバーにアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Bizagi for Digital Process Automation サービスまたはサーバーのプロジェクトに自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Automation サービスまたはサーバーを使用する Bizagi プロジェクト。
- SAML アサーション署名用に独自の証明書を用意します。 この証明書は、p12 または pfx 形式で生成する必要があります。
- XML 形式のメタデータ ファイルを Bizagi プロジェクトから生成します。

### シナリオの説明

この記事では、Automation サービスまたはサーバーを使用して、Bizagi プロジェクトで Microsoft Entra SSO を構成し、テストします。

- Bizagi for Digital Process Automation では、**SP** Initiated SSO がサポートされます。
- Bizagi for Digital Process Automation では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bizagi-studio-for-digital-process-automation-provisioning-tutorial)がサポートされます。

### ギャラリーからの Bizagi for Digital Process Automation の追加

Microsoft Entra ID への Bizagi for Digital Process Automation の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Bizagi for Digital Process Automation を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Bizagi for Digital Process Automation**」と入力します。
4. 結果のパネルから **[Bizagi for Digital Process Automation]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Bizagi for Digital Process Automation に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Bizagi for Digital Process Automation に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Bizagi プロジェクトの関連ユーザーとの間にリンク関係を確立する必要があります。

Bizagi for Digital Process Automation に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra のテスト ユーザーの作成** - B.Simon を使用して Microsoft Entra シングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Bizagi for Digital Process Automation SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Bizagi for Digital Process Automation のテスト ユーザーの作成** - Bizagi for Digital Process Automation で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Bizagi for Digital Process Automation**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.bizagi.com/<PROJECT_NAME>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.bizagi.com/<PROJECT_NAME>`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Bizagi for Digital Process Automation サポート チーム](mailto:jarvein.rivera@bizagi.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

    このメタデータ URL は、Bizagi プロジェクトの認証オプションに登録されている必要があります。
7. [ **SAML でシングル サインオンを設定**する] ページで、[ **ユーザー属性] と [要求** ] の鉛筆アイコンを選択して、一意のユーザー識別子を編集します。

    一意のユーザー ID を user.mail として設定します。

#### Microsoft Entra ID テストを作成する

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

このセクションでは、B.Simon に Bizagi for Digital Process Automation へのアクセスを許可することで、シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Bizagi デジタルプロセス自動化用** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Bizagi for Digital Process Automation SSO の構成

**Bizagi for Digital Process Automation** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Bizagi for Digital Process Automation サポート チーム](mailto:jarvein.rivera@bizagi.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Bizagi for Digital Process Automation テスト ユーザーの作成

このセクションでは、Bizagi for Digital Process Automation で Britta Simon というユーザーを作成します。 [Bizagi for Digital Process Automation サポート チーム](mailto:jarvein.rivera@bizagi.com)と連携して、Bizagi for Digital Process Automation プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Bizagi for Digital Process Automation では、自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bizagi-studio-for-digital-process-automation-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Bizagi for Digital Process Automation のサインオン URL にリダイレクトされます。
- Bizagi for Digital Process Automation のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Bizagi for Digital Process Automation] タイルを選択すると、このオプションは Bizagi for Digital Process Automation のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/blackboard-learn-shibboleth-tutorial"} -->
## Microsoft Entra ID でのシングル サインオン用に Blackboard Learn - Shibboleth を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blackboard-learn-shibboleth-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Blackboard Learn - Shibboleth の間でシングル サインオンを構成する方法について説明します。

この記事では、Blackboard Learn - Shibboleth と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Blackboard Learn - Shibboleth を統合すると、次のことができます。

- Blackboard Learn - Shibboleth にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Blackboard Learn - Shibboleth に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Blackboard Learn - Shibboleth でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Blackboard Learn - Shibboleth では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの Blackboard Learn - Shibboleth の追加

Microsoft Entra ID への Blackboard Learn - Shibboleth の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Blackboard Learn - Shibboleth を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Blackboard Learn - Shibboleth**」と入力します。
4. 結果パネルから **Blackboard Learn - Shibboleth** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Blackboard Learn - Shibboleth に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Blackboard Learn - Shibboleth に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Blackboard Learn - Shibboleth の関連ユーザーとの間にリンク関係を確立する必要があります。

Blackboard Learn - Shibboleth で Microsoft Entra SSO を構成およびテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Blackboard Learn - Shibboleth SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Blackboard Learn - Shibboleth のテスト ユーザーの作成** - Blackboard Learn - Shibboleth で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Blackboard Learn - Shibboleth で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Blackboard Learn - Shibboleth** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。
4. [ **SAML を使用して単一 Sign-On を設定** する] ページで、鉛筆アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<yourblackoardlearnserver>.blackboardlearn.com/Shibboleth.sso/Login`

    b。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<yourblackoardlearnserver>.blackboardlearn.com/shibboleth-sp`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<yourblackoardlearnserver>.blackboardlearn.com/Shibboleth.sso/SAML2/POST`

    注

    これらの値は実際の値ではありません。 実際の Sign-On URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには [、Blackboard Learn - Shibboleth クライアント サポート チーム](https://www.blackboard.com/contact-us) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Blackboard Learn - Shibboleth のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Blackboard Learn - Shibboleth SSO の構成

Blackboard Learn - Shibboleth シングル サインオンを構成するには、この [ドキュメント](https://help.blackboard.com/Learn/Administrator/SaaS/Authentication/Implement_Authentication/SAML_Authentication_Provider_Type)を参照してください。

#### Blackboard Learn - Shibboleth のテスト ユーザーの作成

このセクションでは、Blackboard Learn - Shibboleth で Britta Simon というユーザーを作成します。 [Blackboard Learn - Shibboleth サポート チーム](https://www.blackboard.com/contact-us)と協力して、Blackboard Learn - Shibboleth プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Blackboard Learn - Shibboleth のサインオン URL にリダイレクトされます。
- Blackboard Learn - Shibboleth のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Blackboard Learn - Shibboleth] タイルを選択すると、SSO を設定した Blackboard Learn - Shibboleth に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/blackboard-learn-tutorial"} -->
## Microsoft Entra ID で Blackboard Learn for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blackboard-learn-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra IDと Blackboard Learn の間でシングル サインオンを構成する方法について説明します。

この記事では、Blackboard Learn と Microsoft Entra ID を統合する方法について説明します。 Blackboard Learn と Microsoft Entra ID を統合すると、次のことができます。

- Blackboard Learn にアクセスできるユーザーをMicrosoft Entra IDで制御できます。
- ユーザーが自分のMicrosoft Entra アカウントを使用して Blackboard Learn に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Blackboard Learn でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で sso Microsoft Entraを構成し、テストします。

- Blackboard Learn では、**SP** によって開始される SSO がサポートされます
- Blackboard Learn では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Blackboard Learn の追加

Microsoft Entra IDへの Blackboard Learn の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Blackboard Learn を追加する必要があります。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Blackboard Learn**」と入力します。
4. 結果のパネルから **[Blackboard Learn]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細については](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)を参照してください。

### Blackboard Learn の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Blackboard Learn における Microsoft Entra SSO の構成とテストを行います。 SSO を機能させるには、Microsoft Entra ユーザーと Blackboard Learn の関連ユーザーとの間にリンク関係を確立する必要があります。

Blackboard Learn Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**を構成する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Microsoft Entra のシングル サインオンを B.Simon でテストします。
    2. **Microsoft Entra テストユーザーを割り当てる** - B.Simon が Microsoft Entra シングルサインオンを使用できるようにします。
2. **Blackboard Learn の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. Blackboard Learn のテストユーザーを作成し、このユーザーを B.Simon に対応させて、Microsoft Entra のユーザー表現に結びつけます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Blackboard Learn**&gt;**シングルサインオン**を開きます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.blackboard.com/`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.blackboard.com/auth-saml/saml/SSO/entity-id/SAML_AD`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Blackboard Learn クライアント サポート チーム](https://www.blackboard.com/support)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Blackboard Learn のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra のテスト ユーザーを作成して割り当てる

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Blackboard Learn の SSO の構成

**Blackboard Learn** 側でシングル サインオンを構成するには、こちらの[リンク](https://help.blackboard.com/Learn/Administrator/SaaS/Authentication/Implement_Authentication/SAML_Authentication_Provider_Type)をクリックしてください。 構成中に問題が発生した場合は、 [Blackboard Learn サポート チーム](https://www.blackboard.com/support)にお問い合わせください。

#### Blackboard Learn のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Blackboard Learn に作成します。 Blackboard Learn では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Blackboard Learn にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra シングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Blackboard Learn のサインオン URL にリダイレクトされます。
- Blackboard Learn のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft My Appsを使用できます。 My Appsで [Blackboard Learn] タイルを選択すると、このオプションは Blackboard Learn のサインオン URL にリダイレクトされます。 My Appsの詳細については、「[My Apps への紹介](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bldng-app-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に BLDNG アプリを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bldng-app-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-27
- Summary: Microsoft Entra IDから BLDNG APP にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために BLDNG APP とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成されたMicrosoft Entra IDは、Microsoft Entraプロビジョニングサービスを使用して、ユーザーとグループを[BLDNG APP](https://dashboard.bldng.ai/)に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- BLDNG.AI でユーザーを作成する
- BLDNG.AIでアクセスが不要になったユーザーを削除します。
- Microsoft Entra IDと BLDNG の間でユーザー属性の同期を維持します。Ai
- BLDNG.AI でグループとグループ メンバーシップをプロビジョニングする
- BLDNG.AIに[シングルサインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)することをお勧めします。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [BLDNG。AI](https://dashboard.bldng.ai/) 契約。
- ユーザー プロビジョニングを有効にし、BLDNG APP を使用するための BLDNG.AI からの招待

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとBLDNG APPの間でマッピングするデータを決定します。

### 手順 2: Microsoft Entra IDを使用したプロビジョニングをサポートするように BLDNG APP を構成する

- Azureからユーザー、ユーザー グループ、およびグループ メンバーシップのプロビジョニングを構成するには、BLDNG が必要です。AI 契約とテナント。
- 契約を締結するには、 [営業担当者](mailto:salg@bldng.ai) と連絡を取るために販売に連絡してください。 契約が存在しない場合、BLDNG APP を続行したり使用したりすることはできません。
- 既にアクティブな契約を結んでいるが、ユーザー プロビジョニングのみを有効にする必要がある場合は、 [直接サポート](mailto:support@bldng.ai) にお問い合わせください。

契約が確立されると、ユーザー プロビジョニングの設定方法に関する詳細な手順が記載された電子メールが届きます。 電子メールには、必要に応じて、BLDNG APP を使用することへの管理者の同意 (組織の代理として) に関する詳細も含まれます。

電子メールには、自動ユーザー プロビジョニングを構成するときに使用するテナントの URL とシークレット トークンも含まれます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから BLDNG APP を追加する

Microsoft Entra アプリケーション ギャラリーから BLDNG APP を追加して、BLDNG APP へのプロビジョニングの管理を開始します。 SSO のために BLDNG APP を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: BLDNG APP への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDにおけるユーザーまたはグループの割り当てを基にして、BLDNG APP内のユーザーおよびグループの作成、更新、無効化を行うためにMicrosoft Entraプロビジョニングサービスを構成する手順を説明します。

#### Microsoft Entra IDで BLDNG APP の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で [ **BLDNG アプリ**] を選択します。

    [Image: アプリケーションの一覧の BLDNG APP のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、BLDNG アプリのテナント URL とシークレット トークンを入力します。 **Test Connection** を選択してMicrosoft Entra ID BLDNG APP に接続できることを確認します。 接続に失敗した場合は、BLDNG APP アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから BLDNG APP に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作のために BLDNG APP のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、BLDNG APP API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

注

**externalId** のマッピングを変更した場合、テナント内のユーザーは BLDNG APP を使用してログインできないことに注意してください。

| 特性 | タイプ | フィルター処理でサポートされます |
| --- | --- | --- |
| ユーザー名 | 糸 | ✓ |
| 活動中 | ブール値 |  |
| displayName | 糸 |  |
| emails[type eq "work"].value | 糸 |  |
| name.givenName | 糸 |  |
| name.familyName | 糸 |  |
| phoneNumbers[type eq "mobile"].value | 糸 |  |
| externalId | 糸 |  |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |

1. **[グループ]** を選びます。
2. **Attribute-Mapping** セクションで、Microsoft Entra IDから BLDNG APP に同期されるグループ属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で BLDNG APP のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | members | リファレンス |  |
    | externalId | 糸 |  |
3. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
4. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
5. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/blink-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Blink を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blink-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: ユーザー アカウントを Blink に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Microsoft Entra ID を構成してユーザーを Blink に自動的にプロビジョニングおよびプロビジョニング解除する手順を、Blink と Microsoft Entra ID でどのように実行するかを示すことです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

Blink は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### サポートされている機能

- Blink でユーザーを作成します。
- アクセスが不要になった場合は、Blink のユーザーを削除します。
- Microsoft Entra IDと Blink の間でユーザー属性の同期を維持します。
- Blink に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blink-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Blink 入居者](https://joinblink.com/pricing)
- Admin アクセス許可がある Blink のユーザー アカウント。

### Blink へのユーザーの割り当て

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリにaccessを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーまたはグループ メンバーのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Blink にaccessする必要があるMicrosoft Entra IDのユーザーまたはグループ メンバーを決定する必要があります。 特定した後、次の手順に従い、これらのユーザー、グループ、またはその両方を Blink に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Blink に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを Blink に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Blink にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **Default Access** ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために Blink を設定する

1. サポートケースを記録するか、Blinkサポートにまでメールを送信して、SCIMトークンを要求してください。
2. **SCIM 認証トークン**をコピーします。 この値は、Blink アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。

### ギャラリーから Blink を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Blink を構成する前に、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Blink を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Blink を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションに「**Blink**」と入力し、検索ボックスで **[Blink**] を選択します。
4. 結果パネルから **[Blink** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧で点滅する]

### Blink への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDのユーザーやグループの割り当てに基づいてBlinkでユーザーを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順を説明します。

ヒント

Blink シングル サインオンに関する記事で説明されている手順に従って、Blink に対して SAML ベースの [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blink-tutorial)を有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは独立に構成できますが、これらの 2 つの機能は互いに補完しあいます。

#### Microsoft Entra IDで Blink の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で [ **点滅**] を選択します。

    [Image: アプリケーション一覧内の Blink リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Blink テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Blink に接続できることを確認します。 接続に失敗した場合は、Blink アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. Microsoft Entra IDから Blink に同期されるユーザー属性を、**Attribute Mapping** セクションで確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Blink のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | タイトル | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | エクスターナルID | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | リファレンス |  |
    | urn:ietf:params:scim:schemas:extension:blink:2.0:User:company | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:blink:2.0:User:description | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:blink:2.0:ユーザー:場所 | 糸 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

- [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、正常にプロビジョニングされたユーザーまたは失敗したユーザーを特定する
- [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
- プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、を参照してください。

### 変更履歴

- 2021 年 1 月 14 日 - カスタム拡張機能属性 **の会社**、 **説明**、 **場所** が追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/blink-tutorial"} -->
## Microsoft Entra ID で Blink for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blink-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Blink 間のシングル サインオンを構成する方法について説明します。

この記事では、Blink と Microsoft Entra ID を統合する方法について説明します。 Blink を Microsoft Entra ID と統合すると、次のことが可能になります。

- Blink へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Blink に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Blink でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Blink では、 **SP** Initiated SSO がサポートされます。
- Blink では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Blink では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blink-provisioning-tutorial)。

### ギャラリーからの Blink の追加

Microsoft Entra ID への Blink の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Blink を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Blink**」と入力します。
4. 結果パネルから **[Blink** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Blink に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、Blink に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Blink の関連ユーザーとの間にリンク関係を確立する必要があります。

Blink に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Blink SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Blink のテスト ユーザーの作成** - Blink で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Blink**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    1. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://app.joinblink.com` |
    | `https://<SUBDOMAIN>.joinblink.com` |

    1. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。

    `https://api.joinblink.com/saml/o-<TENANTID>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [Blink クライアント サポート チーム](https://help.joinblink.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Blink Meetings アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: 画像]
7. その他に、Blink Meetings アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [ユーザー属性] ダイアログの [ユーザー要求] セクションで、以下の手順を実行して、以下の表のように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | first\_name | User.givenname |
    | ミドルネーム | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
    |  |  |

    1. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。
    2. [ **名前** ] ボックスに、その行に表示される属性名を入力します。
    3. **名前空間**は空白のままにします。
    4. [ソース] を **[属性**] として選択します。
    5. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。
    6. **[保存] を選択します**。
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Blink のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Blink の SSO の構成

**Blink** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Blink サポート チーム](https://help.joinblink.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Blink のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Blink に作成します。 Blink では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Blink にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

Blink では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blink-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Blink のサインオン URL にリダイレクトされます。
- Blink のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [点滅] タイルを選択すると、このオプションは Blink のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/blinq-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Blinq を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blinq-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-02
- Summary: Microsoft Entra IDから Blinq にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Blinq と Microsoft Entra ID の両方で自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 Microsoft Entra IDを構成すると、Microsoft Entraプロビジョニングサービスを使用してユーザーとグループを[Blinq](https://blinq.me/)に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Blinq でユーザーを作成する。
- accessが不要になったら、Blinq のユーザーを削除します。
- Microsoft Entra IDと Blinq の間でユーザー属性の同期を維持します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者のアクセス許可がある Blinq のユーザー アカウント

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとBlinqの間でどのデータを[マップするかを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Blinq を構成する

1. 別のブラウザー タブで [Blinq 管理コンソール](https://dash.blinq.me) に移動します。
2. Blinq にログインしていない場合は、ログインする必要があります。
3. 画面の左上隅にあるワークスペースを選択し、ドロップダウン メニューの **[設定]** を選択します。

    [Image: Blinq 設定オプションのスクリーンショット。]
4. [ **統合** ] ページに、URL とトークンを含む **チーム カード プロビジョニング** が表示されます。 [生成] を選択してトークンを **生成**する必要があります。 **URL** と**トークン**をコピーします。 URL とトークンは、それぞれ Azure portal の **Tenant URL** および **Secret Token** フィールドに挿入されます。

    [Image: Blinq 統合ページのスクリーンショット。]

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Blinq を追加する

Microsoft Entra アプリケーション ギャラリーから Blinq を追加して、Blinq へのプロビジョニングの管理を開始します。 SSO のために Blinq を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Blinq への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーとグループの割り当てに基づいて、Blinqでユーザーとグループを作成、更新、無効化するためにMicrosoft Entraプロビジョニングサービスを構成する手順を案内します。

#### Microsoft Entra IDで Blinq の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Blinq**] を選択します。

    [Image: アプリケーションの一覧の [Blinq] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Blinq テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Blinq に接続できることを確認します。 接続に失敗した場合は、Blinq アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Blinq に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Blinq のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Blinq API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Blinq で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  |  |
    | ニックネーム | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | ロケール | 糸 |  |  |
    | タイムゾーン | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | name.formatted | 糸 |  |  |
    | name.middleName | 糸 |  |  |
    | name.honorificPrefix | 糸 |  |  |
    | name.honorificSuffix | 糸 |  |  |
    | externalId | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | emails[type eq "home"].value | 糸 |  |  |
    | emails[type eq "other"].value | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | phoneNumbers[type eq "fax"].value | 糸 |  |  |
    | phoneNumbers[type eq "home"].value | 糸 |  |  |
    | phoneNumbers[type eq "other"].value | 糸 |  |  |
    | phoneNumbers[type eq "pager"].value | 糸 |  |  |
    | addresses[type eq "work"].formatted | 糸 |  |  |
    | addresses[type eq "work"].streetAddress | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | addresses[type eq "work"].region | 糸 |  |  |
    | addresses[type eq "work"].postalCode | 糸 |  |  |
    | addresses[type eq "work"].country | 糸 |  |  |
    | addresses[type eq "home"].formatted | 糸 |  |  |
    | addresses[type eq "home"].streetAddress | 糸 |  |  |
    | addresses[type eq "home"].locality（住所[タイプ＝「自宅」].地域） | 糸 |  |  |
    | addresses[type eq "home"].region | 糸 |  |  |
    | addresses[type eq "home"].postalCode | 糸 |  |  |
    | addresses[type eq "home"].country | 糸 |  |  |
    | addresses[type eq "other"].formatted | 糸 |  |  |
    | addresses[type eq "other"].streetAddress | 糸 |  |  |
    | 住所[タイプ eq "その他"].市区町村 | 糸 |  |  |
    | addresses[type eq "other"].region | 糸 |  |  |
    | addresses[type eq "other"].postalCode | 糸 |  |  |
    | addresses[type eq "other"].country | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、組織内でより広範に展開する前に、少数のユーザーとの同期を検証します。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

- 2022 年 5 月 25 日 - このアプリで **有効になっているスキーマ検出** 機能。
- 12/22/2022 - **addresses[type eq "work"].formatted** のソース属性は、**Join("", [streetAddress], IIF(IsPresent([city]),", ",""), [city], IIF(IsPresent([state]),", ",""), [state], IIF(IsPresent([postalCode])," ",""), [postalCode]) --&gt; addresses[type eq "work"].formatted** に変更されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/blinq-tutorial"} -->
## Microsoft Entra ID で Blinq for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blinq-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Blinq の間でシングル サインオンを構成する方法について説明します。

この記事では、Blinq と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Blinq を統合すると、次のことができます。

- Blinq にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Blinq に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Blinq でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Blinq では、 **SP** によって開始される SSO のみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Blinq を追加する

Microsoft Entra ID への Blinq の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Blinq を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Blinq**」と入力します。
4. 結果パネルから **Blinq** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Blinq の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Blinq に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Blinq の関連ユーザーとの間にリンク関係を確立する必要があります。

Blinq に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Blinq SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Blinq テスト ユーザーの作成 - Blinq** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Blinq**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://auth.blinq.me/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.blinq.me/authorize/callback/<ID>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.blinq.me/authorize/callback/<ID>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには [、Blinq サポート チーム](mailto:support@blinq.me) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Blinq のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Blinq SSO の構成

1. Blinq 企業サイトに管理者としてログインします。
2. **[My Team**&gt;**Team Settings**&gt;**Integrations** に移動し、次の手順を実行します。

    [Image: [構成] を示すスクリーンショット。]

    1. **[Identity Provider Entity ID**] ボックスに、Microsoft Entra 管理センターからコピーした **Microsoft Entra 識別子**の値を貼り付けます。
    2. **[シングル サインオン URL**] テキスト ボックスに、Microsoft Entra 管理センターからコピーした**ログイン URL** の値を貼り付けます。
    3. ダウンロードした **証明書 (Base64)** をメモ帳に開き、[ **証明書** ] テキストボックスに内容を貼り付けます。
    4. **サービス プロバイダー エンティティ ID を**コピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの [**識別子 (エンティティ ID)]** ボックスに貼り付けます。
    5. **ACS URL を**コピーし、Microsoft Entra 管理センターの **[基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。
    6. **[保存] を選択する**

#### Blinq テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、Blinq Web サイトに管理者としてサインインします。
2. **[チーム メンバー**] に移動し、[**チーム メンバーの追加 +]** を選択します。

    [Image: スクリーンショットは、新しいユーザーを追加する方法を示しています。]
3. 次のページで以下の手順を実行します。

    [Image: ユーザー情報を入力する [新しいユーザー] セクションを示すスクリーンショット。]

    1. [ **電子メール** ] ボックスに、ユーザーの有効な emailaddress を入力します。
    2. 組織の要件に従って、ドロップダウンから **メンバー** または **管理者** として招待します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Blinq のサインオン URL にリダイレクトします。
- Blinq のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Blinq] タイルを選択すると、このオプションは Blinq のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/blockbax-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Blockbax を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blockbax-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Blockbax の間のシングル サインオンを構成する方法について説明します。

この記事では、Blockbax と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Blockbax を統合すると、次のことができます。

- Blockbax へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Blockbax に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Blockbax でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Blockbax では、**SP Initiated SSO** と **IDP Initiated SSO** がサポートされます
- Blockbax では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Blockbax の追加

Microsoft Entra ID への Blockbax の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Blockbax を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Blockbax**」と入力します。
4. 結果のパネルから **[Blockbax]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Blockbax の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Blockbax に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Blockbax の関連ユーザーとの間にリンク関係を確立する必要があります。

Blockbax に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Blockbax の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Blockbaxのテストユーザーを作成する - Blockbax** で B.Simonと同等のBlockbaxユーザーを作成し、Microsoft Entraでのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Blockbax**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.blockbax.com/saml2/service-provider-metadata/<CustomerName>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.blockbax.com/login/saml2/sso/<CustomerName>`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://login.blockbax.com/sso`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Blockbax サポート チーム](mailto:support@blockbax.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Blockbax の SSO の構成

1. Blockbax 企業サイトに管理者としてログインします。
2. **[設定]** に移動し、**[SSO 設定]** を展開します。

    [Image: SAML アカウントを示すスクリーンショット]
3. **[Identity provider metadata URL](ID プロバイダー メタデータ URL)** テキストボックスに、前の手順でコピーした**アプリのフェデレーション メタデータ URL** の値を貼り付けます。
4. **IDプロバイダーを追加**を選択します。

#### Blockbax のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Blockbax に作成します。 Blockbax では、Just-In-Time ユーザー プロビジョニングがサポートされていて、既定で有効になっています。 このセクションにはアクション項目はありません。 Blockbax にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Blockbax のサインオン URL にリダイレクトされます。
- Blockbax のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Blockbax に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Blockbax] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Blockbax に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/blogin-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に BlogIn を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blogin-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-02
- Summary: Microsoft Entra IDから BlogIn にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために BlogIn と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra IDが構成されると、Microsoft Entra プロビジョニング サービスを使用して、[BlogIn](https://blogin.co/)にユーザーとグループを自動的にプロビジョニングおよびプロビジョニングを解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- BlogIn でユーザーを作成する
- アクセスが不要になった場合にBlogInのユーザーを削除する
- Microsoft Entra IDと BlogIn の間でユーザー属性の同期を維持する
- BlogIn にグループとグループ メンバーシップをプロビジョニングする
- BlogIn に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blogin-tutorial)する (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 管理者ロールを持つ BlogIn 内のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとBlogInの間でデータを[マップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)ことを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように BlogIn を構成する

**BlogIn** でユーザー プロビジョニングを構成するには、BlogIn アカウントにログインし、次の手順を実行します。

1. &gt;  &gt;に移動します。
2. **[User provisioning](ユーザー プロビジョニング)** タブに切り替え、ユーザー プロビジョニングの状態を **[On](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/オン)** に変更します。
3. [ **変更の保存]** ボタンを選択します。 最初に保存すると、 **シークレット (ベアラー) トークン** が生成されます。
4. **ベース (テナント) URL** と**シークレット (ベアラー) トークン**の値をコピーします。 これらの値は、BlogIn アプリケーションの [プロビジョニング] タブの [テナント URL] フィールドと [シークレット トークン] フィールドに入力されます。

BlogIn でのユーザー プロビジョニングの設定の詳細については、[SCIM を使用したユーザー プロビジョニングのセットアップ](https://blogin.co/blog/set-up-user-provisioning-via-scim-254/)に関する記事を参照してください。 ご不明な点がある場合やサポートが必要な場合は、[BlogIn サポート チーム](mailto:support@blogin.co)にお問い合わせください。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから BlogIn を追加する

Microsoft Entra アプリケーション ギャラリーから BlogIn を追加して、BlogIn へのプロビジョニングの管理を開始します。 SSO のために BlogIn を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: BlogIn への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループの割り当てに基づき、TestApp においてユーザーやグループを作成、更新、または無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで BlogIn の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示すスクリーンショット。]
3. アプリケーションの一覧で **[BlogIn]** を選択します。

    [Image: スクリーンショットは、[アプリケーション] の一覧の [BlogIn] リンクを示しています。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブを示すスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、BlogIn テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが BlogIn に接続できることを確認します。 接続に失敗した場合は、BlogIn アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. Microsoft Entra IDから BlogIn に同期されるユーザー属性については、「**Attribute-Mapping**」セクションで確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で BlogIn のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、BlogIn API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | 活動中 | ブール値 |
    | タイトル | 糸 |
    | emails[type eq "work"].value | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | name.formatted | 糸 |
    | phoneNumbers[type eq "work"].value | 糸 |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから BlogIn に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で BlogIn のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | members | リファレンス |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/blogin-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に BlogIn を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blogin-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BlogIn の間のシングル サインオンを構成する方法について説明します。

この記事では、BlogIn と Microsoft Entra ID を統合する方法について説明します。 BlogIn を Microsoft Entra ID と統合すると、次のことが可能になります。

- BlogIn へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで BlogIn に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- BlogIn でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- BlogIn では、**SP と IDP** Initiated SSO がサポートされます。
- BlogIn では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- BlogIn では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blogin-provisioning-tutorial)がサポートされます。

### ギャラリーからの BlogIn の追加

Microsoft Entra ID への BlogIn の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に BlogIn を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**BlogIn**」と入力します。
4. 結果のパネルから **[BlogIn]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BlogIn に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、BlogIn に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと BlogIn の関連ユーザーとの間にリンク関係を確立する必要があります。

BlogIn に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BlogIn SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **BlogIn テストユーザーの作成** - BlogIn で B.Simon に対応するユーザーとして作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**BlogIn**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** Initiated モードで構成する場合は、次の手順を行います。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.blogin.co/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.blogin.co/sso/saml/callback`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.blogin.co/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらのフィールドの正確な値は、BlogIn の **[設定]** ページで取得できます ([**ユーザー認証**] タブ &gt;**SSO とユーザー プロビジョニングの構成**)。 または、[BlogIn クライアント サポート チーム](mailto:support@blogin.co)に連絡して、これらの値を取得することもできます。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. BlogIn アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、BlogIn アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | タイトル | ユーザー.職名 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BlogIn SSO の構成

**BlogIn** 側でシングル サインオンを構成するには、BlogIn アカウントにログインし、次の手順を実行します。

1. &gt;  &gt;に移動します。
2. 次の画面で、[シングル Sign-On の状態] を **[オン** ] に変更し、ログイン画面に表示される SSO ログイン ボタンのカスタム名を選択します。
3. 前のセクションの最終手順で**アプリのフェデレーション メタデータ URL** を保存した場合、構成方法 **[メタデータ URL]** を選択し、**アプリのフェデレーション メタデータ URL** を [メタデータ URL] フィールドに貼り付けます。 それ以外の場合は、構成方法を **[手動]** に変更し、 **[ID プロバイダーの SSO URL (ログイン URL)]** および **[ID プロバイダーの発行者 (エンティティ ID)]** に手動でデータを入力し、Microsoft Entra ID から取得した**証明書 (base64)** をアップロードします。
4. SSO を使用して BlogIn に参加する新規ユーザーの既定のユーザー ロールを選択します。
5. **[変更の保存]** を選択します。

BlogIn での SSO の設定について詳しくは、[BlogIn で Microsoft Entra ID の SSO を設定する方法](https://blogin.co/blog/how-to-set-up-single-sign-on-sso-for-microsoft-azure-active-directory-azure-ad-267/)に関するページを参照してください。 ご不明な点がある場合やサポートが必要な場合は、いつでも [BlogIn サポート チーム](mailto:support@blogin.co)にお問い合わせください。

#### BlogIn テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを BlogIn に作成します。 BlogIn では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 BlogIn にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

BlogIn では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blogin-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる BlogIn のサインオン URL にリダイレクトされます。
- BlogIn のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した BlogIn に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [BlogIn] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した BlogIn に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/blue-access-for-members-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Blue Access for Members (BAM) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blue-access-for-members-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Blue Access for Members (BAM) の間でシングル サインオンを構成する方法について説明します。

この記事では、Blue Access for Members (BAM) と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Blue Access for Members (BAM) を統合すると、次のことが可能になります。

- Blue Access for Members (BAM) にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Blue Access for Members (BAM) に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Blue Access for Members (BAM) でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Blue Access for Members (BAM) では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからの Blue Access for Members (BAM) の追加

Microsoft Entra ID への Blue Access for Members (BAM) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Blue Access for Members (BAM) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Blue Access for Members (BAM)」**と入力します。
4. 結果パネルから **[Blue Access for Members (BAM)]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Blue Access for Members (BAM) 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Blue Access for Members (BAM) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Blue Access for Members (BAM) の関連ユーザーとの間にリンク関係を確立する必要があります。

Blue Access for Members (BAM) に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Blue Access for Members (BAM) SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Blue Access for Members (BAM) テスト ユーザーの作成** - Blue Access for Members (BAM) に B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Blue Access for Members (BAM)**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<Custom Domain Value>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMURL>/affwebservices/public/saml2assertionconsumer`

    c. [ **追加の URL の設定] を選択します**。

    d. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMURL>/BAMSSOServlet/sso/BamInboundSsoServlet`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、およびリレー状態でこれらの値を更新します。 これらの値を取得するには、 [Blue Access for Members (BAM) クライアント サポート チーム](https://www.bcbstx.com/contact-us) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Blue Access for Members (BAM) アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性マッピングの画像を示すスクリーンショット。]
7. その他に、Blue Access for Members (BAM) アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ClientID | `<ClientID>` |
    | ユーザー識別子 | `<UID>` |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **メンバーの Blue Access (BAM) のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成を適切な URL にコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Blue Access for Members (BAM) SSO の構成

**Blue Access for Members (BAM)** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Blue Access for Members (BAM) サポート チーム](https://www.bcbstx.com/contact-us)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Blue Access for Members (BAM) テスト ユーザーの作成

このセクションでは、Blue Access for Members (BAM) で B.Simon というユーザーを作成します。 [Blue Access for Members (BAM) サポート チーム](https://www.bcbstx.com/contact-us)と協力して、Blue Access for Members (BAM) プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Blue Access for Members (BAM) に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Blue Access for Members (BAM)] タイルを選択すると、SSO を設定した Blue Access for Members (BAM) に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->
