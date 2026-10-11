# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 27)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 65

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/xm-discover-tutorial"} -->
## Microsoft Entra ID を使用して XM Discover for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/xm-discover-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と XM Discover の間でシングル サインオンを構成する方法について説明します。

この記事では、XM Discover と Microsoft Entra ID を統合する方法について説明します。 XM Discover と Microsoft Entra ID を統合すると、次のことができます。

- XM Discover にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して XM Discover に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- XM Discover でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- XM Discover では、 **IDP** によって開始される SSO のみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから XM Discover を追加する

Microsoft Entra ID への XM Discover の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に XM Discover を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「XM Discover**」と入力します。
4. 結果パネルから **XM Discover** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### XM Discover の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、XM Discover に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと XM Discover の関連ユーザーとの間にリンク関係を確立する必要があります。

XM Discover に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **XM Discover SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **XM Discover テストユーザーの作成** - XM Discover 内に B.Simon に対応するユーザーを作成し、そのユーザーは Microsoft Entra の B.Simon の表現にリンクされます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**XM Discover**&gt;**シングルサインオン**に参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL には既に Microsoft Entra が事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### XM Discover SSO の構成

**XM Discover** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[XM Discover サポート チーム](mailto:support@qualtrics.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### XM Discover テスト ユーザーの作成

このセクションでは、XM Discover で B.Simon というユーザーを作成します。 [XM Discover サポート チーム](mailto:support@qualtrics.com)と協力して、XM Discover プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した XM Discover に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [XM Discover] タイルを選択すると、SSO を設定した XM Discover に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/xm-fax-and-xm-send-secure-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に XM FAX と XM SendSecure を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/xm-fax-and-xm-send-secure-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-05
- Summary: ユーザー アカウントをMicrosoft Entra IDから XM FAX および XM SendSecure に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために XM FAX と XM SendSecure とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成済みの Microsoft Entra ID を使用すると、Microsoft Entra プロビジョニング サービスによって、[XM FAX および XM SendSecure](https://www.opentext.com/products/xm-fax) にユーザーを自動的にプロビジョニングおよび削除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- XM Fax and XM SendSecure でユーザーを作成する。
- アクセスが不要になったら、XM FAX と XM SendSecure のユーザーを削除します。
- Microsoft Entra IDと XM FAX と XM SendSecure の間でユーザー属性の同期を維持します。
- XM Fax and XM SendSecure に対する[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/xm-fax-and-xm-send-secure-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある XM Fax and XM SendSecure のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
- Microsoft Entra IDとXM FAXとXM SendSecureの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように XM FAX と XM SendSecure を構成する

#### アクセス トークンを作成する

1. XM Cloud エンタープライズ アカウントで、 **エンタープライズ名**&gt;**Access トークンを選択します**。
2. **ユーザー プロビジョニング (SCIM を使用**) アクセス許可を持つ新しいアクセス トークンを作成します。
3. アクセス トークンをコピーします。 これは、Microsoft Entra IDの **Secret Token** として必要です。

#### テナントの URL

- Microsoft Entra プロビジョニング サービスを構成するには、テナントの URL が必要です。
- テナント URL は、リージョン、エンタープライズ アカウントの名前によって異なり、次のスキームがあります。 `https://<domain>/api/scim/v2/enterprises/<enterprise_name>/`

例：

- `https://portal.xmedius.com/api/scim/v2/enterprises/acme/`
- `https://portal.xmedius.eu/api/scim/v2/enterprises/my_corporation/`
- `https://portal.xmedius.ca/api/scim/v2/enterprises/another_company/`

### 手順 3: Microsoft Entra アプリケーション ギャラリーから XM FAX と XM SendSecure を追加する

Microsoft Entra アプリケーション ギャラリーから XM FAX と XM SendSecure を追加して、XM FAX と XM SendSecure へのプロビジョニングの管理を開始します。 SSO 用に XM Fax and XM SendSecure を既に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: XM Fax and XM SendSecure に対する自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて TestApp でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで XM FAX と XM SendSecure の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で、**[XM Fax and XM SendSecure]** を選択します。

    [Image: アプリケーションの一覧に表示された XM Fax and XM SendSecure リンクのスクリーン ショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、XM FAX と XM SendSecure テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、MICROSOFT ENTRA IDが XM FAX と XM SendSecure に接続できることを確認します。 接続に失敗した場合は、XM FAX と XM SendSecure アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから XM FAX および XM SendSecure に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、XM Fax and XM SendSecure での更新処理時にユーザー アカウントを照合するために使用されます。 [照合対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合、その属性に基づいたユーザーのフィルター処理を XM Fax and XM SendSecure API がサポートしているか確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | XM Fax and XM SendSecure で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | 活動中 | ブール値 |  |  |
    | タイトル | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | addresses[type eq "work"].streetAddress | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | addresses[type eq "work"].region | 糸 |  |  |
    | addresses[type eq "work"].postalCode | 糸 |  |  |
    | addresses[type eq "work"].country | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | phoneNumbers[type eq "fax"].value | 糸 |  |  |
    | externalId | 糸 |  | ✓ |
    | roles[primary eq "True"].value | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/xm-fax-and-xm-send-secure-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に XM FAX と XM SendSecure を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/xm-fax-and-xm-send-secure-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と、XM Fax と XM SendSecure の間でシングル サインオンを構成する方法について説明します。

この記事では、XM FAX と XM SendSecure を Microsoft Entra ID と統合する方法について説明します。 XM Fax と XM SendSecure を Microsoft Entra ID と統合すると、次のことができます。

- XM Fax と XM SendSecure にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して自動的に XM Fax と XM SendSecure にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Microsoft Entra クラウド アプリケーション管理者またはアプリケーション管理者ロール。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。
- XM Fax と XM SendSecure サブスクリプション。
- XM Fax と XM SendSecure 管理者アカウント。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- XM FAX と XM SendSecure では、 **SP によって開始される SSO がサポートされます** 。
- XM FAX と XM SendSecure では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/xm-fax-and-xm-send-secure-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから XM Fax と XM SendSecure を追加する

Microsoft Entra ID への XM Fax と XM SendSecure の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に XM Fax と XM SendSecure を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「XM FAX」と「XM SendSecure」と**入力します。
4. 結果パネルから **XM FAX と XM SendSecure** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### XM Fax と XM SendSecure 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、XM FAX と XM SendSecure に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、XM Fax と XM SendSecure の関連ユーザーとの間にリンク関係を確立する必要があります。

XM Fax と XM SendSecure に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **XM FAX と XM SendSecure の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **XM Fax および XM SendSecure のテストユーザーとして、Microsoft Entra 上の B.Simon に対応するユーザーを設定** - これは、XM Fax と XM SendSecure で Microsoft Entra 表記のユーザーとリンクされる B.Simon の同等のユーザーを持つためです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**XM FAX と XM SendSecure**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかの URL を入力します。

    | **識別子** |
    | --- |
    | `https://login.xmedius.com/` |
    | `https://login.xmedius.eu/` |
    | `https://login.xmedius.ca/` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://login.xmedius.com/auth/saml/callback` |
    | `https://login.xmedius.eu/auth/saml/callback` |
    | `https://login.xmedius.ca/auth/saml/callback` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://login.xmedius.com/{account}` |
    | `https://login.xmedius.eu/{account}` |
    | `https://login.xmedius.ca/{account}` |
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **XM FAX と XM SendSecure のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### XM Fax と XM SendSecure SSO の構成

1. Web ブラウザーを使用して XM Cloud アカウントにログインします。
2. Web ポータルのメイン メニューから、[ **エンタープライズ設定] enterprise\_account&gt; 選択します**。
3. **[シングル サインオン] セクションに**移動し、[**SAML 2.0**] を選択します。
4. 次の必須情報を指定します。

    ある。 **[発行者 (ID プロバイダー)] ボックスに**、前にコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    b。 [ **サインイン URL** ] ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。

    c. ダウンロードした **証明書 (Base64)** をメモ帳に開き、内容を **[X.509 署名証明書** ] ボックスに貼り付けます。

    d. **[保存] を選択します**。

注

SSO 構成セクションの下部にあるフェールセーフ URL (`https://login.[domain]/[account]/no-sso`) を保持します。SSO のアクティブ化後に自分自身をロックした場合は、XM Cloud アカウントの資格情報を使用してログインできます。

#### XM Fax と XM SendSecure テスト ユーザーの作成

XM Fax と XM SendSecure で Britta Simon というユーザーを作成します。 メールアドレスが "B.Simon@contoso.com" に設定されていることを確認します。

注

シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる XM FAX と XM SendSecure のサインオン URL にリダイレクトされます。
- XM Fax と XM SendSecure サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリ ポータルで [XM FAX] タイルと [XM SendSecure] タイルを選択すると、XM FAX と XM SendSecure のサインオン URL にリダイレクトされます。 マイ アプリ ポータルの詳細については、「マイ アプリ [ポータルの概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/xmatters-ondemand-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に xMatters OnDemand を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/xmatters-ondemand-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と xMatters OnDemand の間にシングル サインオンを構成する方法について説明します。

この記事では、xMatters OnDemand と Microsoft Entra ID を統合する方法について説明します。 xMatters OnDemand と Microsoft Entra ID を統合すると、次のことができます。

- xMatters OnDemand にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って xMatters OnDemand に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- xMatters OnDemand でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- xMatters OnDemand では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの xMatters OnDemand の追加

Microsoft Entra ID への xMatters OnDemand の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに xMatters OnDemand を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**xMatters OnDemand**」と入力します。
4. 結果パネルで **[xMatters OnDemand]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### xMatters OnDemand 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、xMatters OnDemand に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと xMatters OnDemand の関連ユーザーとの間にリンク関係を確立する必要があります。

xMatters OnDemand に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **xMatters OnDemand の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **xMatters OnDemand の Britta Simon 対応テストユーザーの作成** - Microsoft Entra のユーザーとリンクされる xMatters OnDemand で Britta Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**xMatters OnDemand**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://<COMPANY_NAME>.au1.xmatters.com.au/` |
    | `https://<COMPANY_NAME>.cs1.xmatters.com/` |
    | `https://<COMPANY_NAME>.xmatters.com/` |
    | `https://www.xmatters.com` |
    | `https://<COMPANY_NAME>.xmatters.com.au/` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 応答 URL |
    | --- |
    | `https://<COMPANY_NAME>.au1.xmatters.com.au` |
    | `https://<COMPANY_NAME>.xmatters.com/sp/<INSTANCE_NAME>` |
    | `https://<COMPANY_NAME>.cs1.xmatters.com/sp/<INSTANCE_NAME>` |
    | `https://<COMPANY_NAME>.au1.xmatters.com.au/<INSTANCE_NAME>` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[xMatters OnDemand クライアント サポート チーム](https://www.xmatters.com/company/contact-us/)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

    重要

    [xMatters OnDemand サポート チーム](https://www.xmatters.com/company/contact-us/)に証明書を転送する必要があります。 シングル サインオンの構成を確定するには、その前に、xMatters サポート チームによって証明書がアップロードされる必要があります。
7. **[Set up xMatters OnDemand](xMatters OnDemand の設定)** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### xMatters OnDemand の SSO の構成

1. 別の Web ブラウザー ウィンドウで、xMatters OnDemand の企業サイトに管理者としてサインインします。
2. [ **管理者**] を選択し、[ **会社の詳細**] を選択します。

    [Image: [Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) ページ]
3. **[SAML 構成]** ページで、次の手順を実行します。

    [Image: SAML の構成セクション]

    ある。 [ **SAML を有効にする] を選択します**。

    b。 **[Identity Provider ID] (ID プロバイダーの ID)** テキストボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    c. **[Single Sign-On URL](シングル サインオン URL)** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    d. **[Logout URL Redirect](ログアウト URL リダイレクト)** テキストボックスに、先ほどコピーした**ログアウト URL** を貼り付けます。

    え [ **ファイルの選択] を選択** して、ダウンロードした **証明書 (Base64)** をアップロードします。

    f. [会社の詳細] ページの上部にある [ **変更の保存**] を選択します。

    [Image: 会社の詳細]

#### xMatters OnDemand のテスト ユーザーの作成

1. **xMatters OnDemand** テナントにサインインします。
2. **[ユーザー] アイコン**&gt;**[ユーザー]** に移動し、[ユーザーの**追加]** を選択します。

    [Image: ユーザー]
3. [ **ユーザーの追加** ] セクションで、必要なフィールドに入力し、[ **ユーザーの追加]** ボタンを選択します。

    [Image: ユーザーの追加]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した xMatters OnDemand に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [xMatters OnDemand] タイルを選択すると、SSO を設定した xMatters OnDemand に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/yardielearning-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Yardi eLearning を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/yardielearning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Yardi eLearning の間でシングル サインオンを構成する方法について説明します。

この記事では、Yardi eLearning と Microsoft Entra ID を統合する方法について説明します。 Yardi eLearning を Microsoft Entra ID を統合すると、次のことができます。

- Yardi eLearning にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Yardi eLearning に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Yardi eLearning でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Yardi eLearning では、**SP** Initiated SSO がサポートされます。
- Yardi eLearning では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Yardi eLearning の追加

Microsoft Entra ID への Yardi eLearning の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Yardi eLearning を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Yardi eLearning**」と入力します。
4. 結果のパネルから **[Yardi eLearning]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Yardi eLearning 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Yardi eLearning に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Yardi eLearning の関連ユーザーとの間にリンク関係を確立する必要があります。

Yardi eLearning に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Yardi eLearning SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Yardi eLearning テスト ユーザーの作成 - Yardi eLearning** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Yardi eLearning**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.yardielearning.com/login`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.yardielearning.com/trust`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Yardi eLearning クライアント サポート チーム](mailto:elearning@yardi.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Yardi eLearning のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Yardi eLearning SSO の構成

**Yardi eLearning** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Yardi eLearning サポート チーム](mailto:elearning@yardi.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Yardi eLearning テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Yardi eLearning に作成します。 Yardi eLearning では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Yardi eLearning にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Yardi eLearning のサポート チーム](mailto:elearning@yardi.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Yardi eLearning のサインオン URL にリダイレクトされます。
- Yardi eLearning のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Yardi eLearning] タイルを選択すると、このオプションは Yardi eLearning のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/yardione-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に YardiOne を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/yardione-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-05
- Summary: Microsoft Entra IDから YardiOne にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、YardiOne と Microsoft Entra ID の両方で実行して自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成すると、Microsoft Entra ID Microsoft Entra プロビジョニング サービスを使用してユーザーを [YardiOne](https://www.yardi.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- YardiOne でユーザーを作成します。
- アクセスが不要になった場合は、YardiOne のユーザーを削除します。
- Microsoft Entra IDと YardiOne の間でユーザー属性の同期を維持します。
- YardiOne に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/yardione-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ YardiOne のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- Microsoft Entra IDとYardiOneの間でマッピングするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように YardiOne を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように YardiOne を構成するには、YardiOne サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから YardiOne を追加する

Microsoft Entra アプリケーション ギャラリーから YardiOne を追加して、YardiOne へのプロビジョニングの管理を開始します。 SSO 用に YardiOne を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: YardiOne への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて YardiOne でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で YardiOne の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **YardiOne** を選択します。

    [Image: アプリケーションの一覧の YardiOne リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、YardiOne テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが YardiOne に接続できることを確認します。 接続に失敗した場合は、YardiOne アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから YardiOne に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で YardiOne のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、YardiOne API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | YardiOne で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから YardiOne に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で YardiOne のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | YardiOne で必要 |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/yardione-tutorial"} -->
## Microsoft Entra ID で YardiOne for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/yardione-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と YardiOne の間でシングル サインオンを構成する方法について説明します。

この記事では、YardiOne と Microsoft Entra ID を統合する方法について説明します。 YardiOne と Microsoft Entra ID を統合すると、次のことができます。

- YardiOne にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して YardiOne に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- YardiOne でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- YardiOne では、 **SP** によって開始される SSO がサポートされます。
- YardiOne では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。
- YardiOne では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/yardione-provisioning-tutorial)。

### ギャラリーから YardiOne を追加する

Microsoft Entra ID への YardiOne の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に YardiOne を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「YardiOne**」と入力します。
4. 結果パネルから **YardiOne** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### YardiOne の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、YardiOne に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと YardiOne の関連ユーザーとの間にリンク関係を確立する必要があります。

YardiOne で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **YardiOne SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **YardiOne のテスト ユーザーの作成** - YardiOne で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**YardiOne**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<y1-subdomain>.yardione.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `http://<y1-subdomain>.yardione.com/yAuth2/trust`

    手記

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、YardiOne クライアント サポート チーム](https://clientcentral.yardi.com/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### YardiOne SSO の構成

**YardiOne** 側でシングル サインオンを構成するには、**YardiOne サポート チーム**に[アプリのフェデレーション メタデータ URL を](https://clientcentral.yardi.com/)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### YardiOne テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを YardiOne に作成します。 YardiOne では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 YardiOne にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

手記

ユーザーを手動で作成する必要がある場合は、YardiOne サポート チーム にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる YardiOne のサインオン URL にリダイレクトされます。
- YardiOne のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [YardiOne] タイルを選択すると、このオプションは YardiOne のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/yello-enterprise-tutorial"} -->
## Microsoft Entra ID でシングルサインオン用Yello Enterpriseを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/yello-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Yello Enterprise 間にシングル サインオンを構成する方法について説明します。

この記事では、Yello Enterprise と Microsoft Entra ID を統合する方法について説明します。 Yello Enterprise と Microsoft Entra ID を統合すると、次のことができます:

- Yello Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Yello Enterprise に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Yello Enterprise でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Yello Enterprise では、 **IDP** Initiated SSO がサポートされます

### ギャラリーからの Yello Enterprise の追加

Microsoft Entra ID への Yello Enterprise の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Yello Enterprise を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Yello Enterprise」**と入力します。
4. 結果のパネルから **[Yello Enterprise]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Yello Enterprise 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Yello Enterprise に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Yello Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

Yello Enterprise に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Yello Enterprise SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Yello Enterprise のテストユーザーを作成 - B.Simon に対応するユーザーとして Yello Enterprise に作成し、Microsoft Entra のユーザー表現とリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Yello Enterprise**&gt;**シングルサインオン**に進みます。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.yello.co/<IDP_NAME>`

    注

    この値は実際の値ではありません。 実際の識別子で値を更新します。 この値を取得するには [、Yello Enterprise クライアント サポート チーム](mailto:support@yello.co) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Yello Enterprise アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Yello Enterprise アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 従業員 ID | ユーザー.社員ID |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Yello Enterprise のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Yello Enterprise の SSO の構成

**Yello Enterprise** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Yello Enterprise サポート チーム](mailto:support@yello.co)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Yello Enterprise のテスト ユーザーの作成

このセクションでは、Yello Enterprise で Britta Simon というユーザーを作成します。 [Yello Enterprise サポート チーム](mailto:support@yello.co)と協力して、Yello Enterprise プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Yello Enterprise に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [Yello Enterprise] タイルを選択すると、SSO を設定した Yello Enterprise に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/yellowbox-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Yellowbox を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/yellowbox-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから Yellowbox にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Yellowbox とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 Microsoft Entra ID が構成されると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを[Yellowbox](https://yellowbox.app/)に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Yellowbox でユーザーを作成する
- アクセスが不要になった場合に Yellowbox のユーザーを削除する
- Microsoft Entra IDと Yellowbox の間でユーザー属性の同期を維持する
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- SCIM プロビジョニング エンドポイントに対する認可用に Yellowbox から発行された JSON Web トークン

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとYellowboxの間でマッピングするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Yellowbox を構成する

- [テナントの URL] として `https://australia-southeast1-yellowbox-f4c6e.cloudfunctions.net/scim` を使用します。
- トークンをまだ発行していない場合は、 [Yellowbox サポート](mailto:contact@yellowbox.app)に連絡して、yellowbox から JWT 承認トークンを取得します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Yellowbox を追加する

Microsoft Entra アプリケーション ギャラリーから Yellowbox を追加して、Yellowbox へのプロビジョニングの管理を開始します。 以前に SSO 用に Yellowbox を設定したことがある場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Yellowbox への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Yellowbox でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Yellowbox の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Yellowbox**] を選択します。

    [Image: アプリケーションの一覧の [Yellowbox] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Yellowbox テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Yellowbox に接続できることを確認します。 接続に失敗した場合は、Yellowbox アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Yellowbox に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Yellowbox のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Yellowbox API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Yellowbox で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 役割[主要 eq "True"].値 | 糸 |  | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | externalId | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/yodeck-tutorial"} -->
## Microsoft Entra ID で Yodeck for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/yodeck-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Yodeck 間にシングル サインオンを構成する方法について学習します。

この記事では、Yodeck と Microsoft Entra ID を統合する方法について説明します。 Yodeck と Microsoft Entra ID を統合すると、次のことができます。

- Yodeck にアクセスできるユーザー Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Yodeck に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Yodeck でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Yodeck では、**SP** と **IDP** Initiated SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Yodeck の追加

Microsoft Entra ID への Yodeck の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Yodeck を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、 **[Yodeck]** と入力します。
4. 結果のパネルから **[Yodeck]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Yodeck 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Yodeck に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Yodeck の関連ユーザーとの間にリンク関係を確立する必要があります。

Yodeck に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Yodeck SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Yodeck のテストユーザーの作成** - Yodeck において B.Simon に対応するユーザーを作成し、Microsoft Entra におけるユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Yodeck**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    **[識別子]** ボックスに、`https://app.yodeck.com/api/v1/account/metadata/` という URL を入力します。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. Yodeck アプリケーションでは、特定の形式の SAML アサーションが必要となるため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Yodeck ではこれをユーザーのメール アドレスにマップする必要があります。 そのため、一覧の **user.mail** 属性を使用するか、組織構成に基づいて適切な属性値を使用できます。

[Image: 属性の画像を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Yodeck SSO の構成

1. 別の Web ブラウザー ウィンドウで、Yodeck 企業サイトに管理者としてサインインします。
2. ページの右上隅にある [ **ユーザー設定]** オプションを選択し **、[アカウント設定]** を選択します。

    [Image: ユーザーに対して [アカウント設定] が選択されていることを示すスクリーンショット。]
3. **[SAML]** をクリックし、次の手順を実行します。

    [Image: [SAML] タブを示すスクリーンショット。ここで以下の手順を行います。]

    ある。 **[URL からインポートする]** を選択します。

    b。 **[URL**] ボックスに、コピーした**アプリのフェデレーション メタデータ URL** の値を貼り付け、[インポート] を選択**します**。

    c. **[アプリのフェデレーション メタデータ URL]** をインポートすると、残りのフィールドが自動的に設定されます。

    d. **保存** を選択します。

#### Yodeck のテスト ユーザーの作成

Microsoft Entra ユーザーが Yodeck にサインインできるようにするには、ユーザーを Yodeck にプロビジョニングする必要があります。 Yodeck の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. Yodeck 企業サイトに管理者としてサインインします。
2. ページの右上隅にある [ **ユーザー設定]** オプションを選択し、[ユーザー] を選択 **します**。

    [Image: ユーザーに対して選択された [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) を示すスクリーンショット。]
3. **[+ユーザー]** を選択して [**ユーザーの詳細**] タブを開きます。

    [Image: [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) ボタンを示すスクリーンショット。]
4. [**User Details**] ダイアログ ページで、以下の手順を実行します。

    [Image: [User Details](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの詳細) タブを示すスクリーンショット。ここで以下の手順を行います。]

    ある。 **[名]** ボックスに、ユーザーの名を入力します (この例では **Britta**)。

    b。 **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (この例では **Simon**)。

    c. **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メール)** ボックスに、ユーザーのメール アドレス (brittasimon@contoso.com など) を入力します。

    d. 組織の要件に従って、適切な **[アカウントのアクセス許可]** オプションを選択します。

    え **保存** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Yodeck のサインオン URL にリダイレクトされます。
- Yodeck のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Yodeck に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Yodeck タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Yodeck に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/yokoy-sso-tutorial"} -->
## Microsoft Entra ID で Yokoy for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/yokoy-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-01-22
- Summary: Microsoft Entra ID と Yokoy の間でシングル サインオンを構成する方法について説明します。

この記事では、Yokoy と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Yokoy を統合すると、次のことができます。

- Microsoft Entra ID で、Yokoy へのアクセス権を管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して自動的に Yokoy にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Yokoy でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Yokoy では、 **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Yokoy を追加する

Microsoft Entra ID への Yokoy の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Yokoy を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックス**に「Yokoy**」と入力します。
4. 結果パネルから **[Yokoy** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Yokoy の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Yokoy に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Yokoy の関連ユーザーとの間にリンク関係を確立する必要があります。

Yokoy に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Yokoy SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Yokoy テスト ユーザーを作成する** - Microsoft Entra の B.Simon にリンクした Yokoy における B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Yokoy**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. **[基本的な SAML 構成]** ページで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、値を入力します。 `VERSAL`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://Yokoy.com/sso/saml/orgs/<organization_id>`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 これらの値を取得するには、Yokoy クライアント サポート チームに問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Yokoy アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 Yokoy アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集** ] アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: Yokoyアプリケーションの画像を示したスクリーンショット。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **Yokoy のセットアップ** ] セクションで、要件に基づいて 1 つ以上の適切な URL をコピーします。

    [Image: 適切な構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Yokoy SSO の構成

**Yokoy** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を Yokoy サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Yokoy テスト ユーザーの作成

このセクションでは、Yokoy で B.Simon というユーザーを作成します。 「SAML テスト ユーザーの作成」サポート ガイドに従って、組織内にユーザー B.Simon を作成します。 シングル サインオンを使用する前に、ユーザーを作成して Yokoy でアクティブ化する必要があります。

### SSO のテスト

このセクションでは、Web サイトに埋め込まれた Yokoy コースを使用して、Microsoft Entra のシングル サインオン構成をテストします。 Microsoft Entra シングル サインオンをサポートするYokoyコースを埋め込む方法については、「Embedding Organizational Courses SAML シングル サインオンサポートガイド」を参照してください。

コースの埋め込みをテストするには、コースを作成し、それを組織と共有し、発行する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/yonyx-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Yonyx Interactive Guides を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/yonyx-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Yonyx Interactive Guides の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、Yonyx Interactive Guides と Microsoft Entra ID を統合する方法について説明します。 Yonyx Interactive Guides と Microsoft Entra ID を統合すると、次のことを行えます。

- Microsoft Entra ID で、Yonyx Interactive Guides にアクセスできるユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントで自動的に Yonyx Interactive Guides にサインオンできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Yonyx Interactive Guides でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Yonyx Interactive Guides では、 **SP** Initiated SSO がサポートされます。
- Yonyx Interactive Guides では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Yonyx Interactive Guides を追加する

Microsoft Entra ID への Yonyx Interactive Guides の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Yonyx Interactive Guides を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Yonyx Interactive Guides」と**入力します。
4. 結果パネルから **Yonyx Interactive Guides** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Yonyx Interactive Guides の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Yonyx Interactive Guides に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Yonyx Interactive Guides の関連するユーザーの間のリンク関係を確立する必要があります。

Yonyx Interactive Guides で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Yonyx Interactive Guides の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Yonyx Interactive Guides のテストユーザーを作成する** - Yonyx Interactive Guides で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Yonyx Interactive Guides**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] のスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    「a.」 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.yonyx.com/y/conversation/?id=<guid number>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.yonyx.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、Yonyx Interactive Guides クライアント サポート チーム](mailto:support@yonyx.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
7. [ **Yonyx Interactive Guides のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: [構成 URL のコピー] のスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Yonyx Interactive Guides SSO を構成する

**Yonyx Interactive Guides** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Yonyx Interactive Guides サポート チーム](mailto:support@yonyx.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Yonyx Interactive Guides テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Yonyx Interactive Guides に作成します。 Yonyx Interactive Guides では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Yonyx Interactive Guides にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Yonyx Interactive Guides サポート チーム](mailto:support@yonyx.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Yonyx Interactive Guides のサインオン URL にリダイレクトされます。
- Yonyx Interactive Guides のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Yonyx Interactive Guides] タイルを選択すると、このオプションは Yonyx Interactive Guides のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/you-at-college-tutorial"} -->
## Microsoft Entra ID を使用して YOU at College にシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/you-at-college-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と YOU at College の間でシングル サインオンを構成する方法について説明します。

この記事では、YOU at College を Microsoft Entra ID と統合する方法について説明します。 YOU at College は、高等教育機関が学生やスタッフにライセンス供与し、プロモートすることができる Web ベースのオプトイン福利アプリケーションです。 YOU at College を Microsoft Entra ID を統合すると、以下のことができます。

- YOU at College にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って YOU at College に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で YOU at College 用の Microsoft Entra シングル サインオンを構成してテストします。 YOU at College では、 **SP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングのみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を YOU at College と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- YOU at College でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから YOU at College アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから YOU at College を追加する

Microsoft Entra アプリケーション ギャラリーから YOU at College を追加して、YOU at College とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**YOU at College**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、URL を入力します。 `http://sso.youatcollege.com/shibboleth`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://sso.youatcollege.com/Shibboleth.sso/SAML2/POST`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://sso.youatcollege.com/idp-<domain>.php`

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには、 [College クライアント サポート チーム](mailto:technology@gritdigitalhealth.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. **大学での YOU セットアップ** セクションで、目的に応じて適切な URL をコピーしてください。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### YOU at College SSO を構成する

**カレッジ側でユーザー**にシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [College サポート チームのユーザー](mailto:technology@gritdigitalhealth.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### YOU at College テスト ユーザーを作成する

このセクションでは、YOU at College で B.Simon というユーザーを作成します。 YOU at College では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 YOU at College にユーザーがまだ存在していない場合、一般的には認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる大学のサインオン URL でユーザーにリダイレクトされます。
- YOU at College のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで「YOU at College」タイルを選択すると、このオプションは「YOU at College」のサインオンURLにリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/youearnedit-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に YouEarnedIt を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/youearnedit-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と YouEarnedIt 間にシングル サインオンを構成する方法について学習します。

この記事では、YouEarnedIt と Microsoft Entra ID を統合する方法について説明します。 YouEarnedIt を Microsoft Entra ID と統合すると、次のことができます。

- YouEarnedIt にアクセスできるユーザー Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って YouEarnedIt に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- YouEarnedIt でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- YouEarnedIt では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの YouEarnedIt の追加

Microsoft Entra ID への YouEarnedIt の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に YouEarnedIt を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、 **[YouEarnedIt]** と入力します。
4. 結果パネルから **[YouEarnedIt]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### YouEarnedIt 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、YouEarnedIt に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと YouEarnedIt の関連ユーザーとの間にリンク関係を確立する必要があります。

YouEarnedIt に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **YouEarnedIt SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **YouEarnedIt テストユーザーの作成** - Microsoft Entra におけるユーザーの表現にリンクされた YouEarnedIt で B.Simon の対応ユーザーを持つためのものです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**YouEarnedIt**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** テキストボックスに、次のいずれかのパターンで値を入力します。

    | 環境 | パターン |
    | --- | --- |
    | 生産 | `<company name>.youearnedit.com` |
    | サンドボックス | `<company name>.sandbox.youearnedit.com` |

    b。 [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 環境 | パターン |
    | --- | --- |
    | 生産 | `https://<company name>.youearnedit.com/users/sign_in` |
    | サンドボックス | `https://<company name>.sandbox.youearnedit.com/users/sign_in` |

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値は、担当の YouEarnedIt 顧客対応マネージャーにお問い合わせください。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Set up YouEarnedIt](YouEarnedIt のセットアップ)** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### YouEarnedIt SSO の構成

**YouEarnedIt** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を、担当の YouEarnedIt 顧客対応マネージャーに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### YouEarnedIt テスト ユーザーの作成

このセクションでは、YouEarnedIt で Britta Simon というユーザーを作成します。 担当の YouEarnedIt 顧客対応マネージャーと連携して、YouEarnedIt プラットフォームにユーザーを追加してください。

注

YouEarnedIt は、ID プロバイダーが NameID 属性の EmailAddress または UserName を提供することを求めています。 対応する UserName または EmailAddress がデータベース内に見つからないか、完全に一致しない場合、認証は失敗します。 その場合は、SSO 統合前に、YouEarnedIt システムにアカウントをインポートする必要があります (通常、API または CSV インポートを使用します)。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる YouEarnedIt サインオン URL にリダイレクトされます。
- YouEarnedIt のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [YouEarnedIt] タイルを選択すると、このオプションは YouEarnedIt のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/yuhu-property-management-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Yuhu Property Management Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/yuhu-property-management-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Yuhu Property Management Platform の間にシングル サインオンを構成する方法について説明します。

この記事では、Yuhu Property Management Platform と Microsoft Entra ID を統合する方法について説明します。 Yuhu Property Management Platform を Microsoft Entra ID と統合すると、次のことができるようになります。

- Yuhu Property Management Platform にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Yuhu Property Management Platform に自動的にサインインできるようにすることができます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Yuhu Property Management Platform でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Yuhu Property Management Platform では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの Yuhu Property Management Platform を追加する

Microsoft Entra ID への Yuhu Property Management Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Yuhu Property Management Platform を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Yuhu Property Management Platform**」と入力します。
4. 結果パネルから **Yuhu Property Management Platform** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Yuhu Property Management Platform 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Yuhu Property Management Platform に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Yuhu Property Management Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

Yuhu Property Management Platform で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Yuhu Property Management Platform の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Yuhu Property Management Platform のテストユーザー作成** - Microsoft Entra における B.Simon のユーザー表現にリンクするように、Yuhu Property Management Platform で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Yuhu Property Management Platform**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `yuhu-<ID>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.yuhu.io/companies`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、Yuhu Property Management Platform クライアント サポート チーム](mailto:hello@yuhu.io) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Yuhu Property Management Platform アプリケーションでは、特定の形式の SAML アサーションが求められます。そのため、カスタム属性マッピングを SAML トークン属性構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、Yuhu Property Management Platform アプリケーションでは、以下に示す SAML 応答で返される属性がほとんどないことが想定されます。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **Yuhu Property Management Platform のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、適切な U R L に構成をコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Yuhu Property Management Platform の SSO の構成

**Yuhu Property Management Platform** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、アプリケーション構成からコピーした適切な URL を [Yuhu Property Management Platform サポート チーム](mailto:hello@yuhu.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Yuhu Property Management Platform のテスト ユーザーの作成

このセクションでは、Yuhu Property Management Platform で B.Simon というユーザーを作成します。 [Yuhu Property Management Platform サポート チーム](mailto:hello@yuhu.io)と協力して、Yuhu Property Management Platform プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Yuhu Property Management Platform のサインオン URL にリダイレクトされます。
- Yuhu Property Management Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Yuhu Property Management Platform] タイルを選択すると、このオプションは Yuhu Property Management Platform のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zapier-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Zapier を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zapier-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから Zapier にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Zapier と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成されると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Zapier](https://zapier.com/pricing) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Zapier でユーザーを作成する
- アクセスが不要になった場合に Zapier のユーザーを削除する
- Microsoft Entra IDと Zapier の間でユーザー属性の同期を維持する
- Zapier でグループとグループ メンバーシップを設定する
- Zapier へのシングル サインオン (推奨)

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- [プロビジョニングを構成する権限](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ([Application Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [Application Owner](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications) など)。
- 管理者アクセス許可を持つ Zapier のユーザー アカウント。

### 手順 1: プロビジョニングの展開を準備する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Zapier の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Zapier を構成する

1. [Zapier 管理コンソール](https://zapier.com/app/login/)にサインインします。 テナント ID の下の **[設定]** に移動します。

    [Image: Zapier 管理コンソールのスクリーンショット。]
2. [ **会社の設定]** で、[ **ユーザー プロビジョニング**] を選択します。

    [Image: Zapier Add SCIM のスクリーンショット。]
3. **SCIM ベース URL** と **SCIM ベアラー トークン**をコピーします。 これらの値は、Zapier アプリケーションの [プロビジョニング] タブの [テナント URL] フィールドと [シークレット トークン] フィールドにそれぞれ入力されます。

    [Image: Zapier のトークンの作成のスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Zapier を追加する

Microsoft Entra アプリケーション ギャラリーから Zapier を追加して、Zapier へのプロビジョニングの管理を開始します。 SSO 用に Zapier を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Zapier への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Zapier の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [Zapier 選択します。

    [Image: アプリケーションの一覧の [Zapier] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Zapier テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Zapier に接続できることを確認します。 接続に失敗した場合は、Zapier アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Zapier に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Zapier のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Zapier API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 変数 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | アクティブ | ブール値 |
    | externalId | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | emails[type eq "work"].value | 糸 |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Zapier に同期されるグループ属性を確認します。 Zapierのグループと照合するための属性として選択されているプロパティは、更新操作で使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 変数 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | members | リファレンス |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zdiscovery-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に ZDiscovery を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zdiscovery-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ZDiscovery 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、ZDiscovery と Microsoft Entra ID を統合する方法について説明します。 ZDiscovery を Microsoft Entra ID と統合すると、次のことができます:

- ZDiscovery にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して ZDiscovery に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ZDiscovery でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ZDiscovery では、**SP** が開始する SSO と **IDP** が開始する SSO に対応しています。

### ギャラリーからの ZDiscovery の追加

Microsoft Entra ID への ZDiscovery の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに ZDiscovery を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「ZDiscovery**」と入力します。
4. 結果パネルから **ZDiscovery** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ZDiscovery 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ZDiscovery に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ZDiscovery の関連ユーザーとの間にリンク関係を確立する必要があります。

ZDiscovery に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ZDiscovery SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ZDiscovery テスト ユーザーを作成** - Microsoft Entra のユーザーとして表現されている B.Simon に対応する新しいユーザーを ZDiscovery で作成し、それにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**ZDiscovery**&gt;**シングルサインオンに**移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:<AUTH0_TENANT>:<CONNECTION_NAME>`

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://zapproved.auth0.com/login/callback?connection=<YOUR_AUTH0_CONNECTION_NAME>` |
    | `https://zapproved-sandbox.auth0.com/login/callback?connection=<YOUR_AUTH0_CONNECTION_NAME>` |
    | `https://zapproved-preview.us.auth0.com/login/callback?connection=<YOUR_AUTH0_CONNECTION_NAME>` |
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://zdiscovery.io/<CustomerName>/` |
    | `https://zdiscovery-sandbox.io/<CustomerName>` |
    | `https://zdiscovery-preview.io/<CustomerName>` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値 Rise.com 取得するには [、サポート チーム](mailto:support@zapproved.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **ZDiscovery のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成を適切な URL にコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ZDiscovery SSO の構成

**ZDiscovery** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** と、アプリケーション構成からコピーした適切な URL を [ZDiscovery サポート チーム](mailto:support@zapproved.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ZDiscovery テスト ユーザーの作成

このセクションでは、ZDiscovery で Britta Simon というユーザーを作成します。 [ZDiscovery サポート チーム](mailto:support@zapproved.com)と協力して、ZDiscovery プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる ZDiscovery Sign-On URL にリダイレクトされます。
- ZDiscovery のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ZDiscovery に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ZDiscovery] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション Sign-On ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ZDiscovery に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zello-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Zello を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zello-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから Zello にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Zello と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra IDを構成すると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーを [Zello](https://zello.com/) に自動的にプロビジョニングおよびプロビジョニングの解除を行います。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされる機能

- Zello でユーザーを作成する
- アクセスが不要になった場合は、Zello のユーザーを削除します。
- Microsoft Entra IDと Zello の間でユーザー属性の同期を維持します。
- Zello に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者アクセス許可がある Zello のユーザー アカウント。

### 手順 1:プロビジョニングのデプロイを計画する

- [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
- [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
- Microsoft Entra IDとZelloの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Zello を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように Zello を構成するには、Zello サポートに問い合わせてください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Zello を追加する

Microsoft Entra アプリケーション ギャラリーから Zello を追加して、Zello へのプロビジョニングの管理を開始します。 シングル サインオン (SSO) 用に Zello を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Zello への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて TestApp でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Zello の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Zello** を選択します。

    [Image: アプリケーションの一覧の [Zello] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Zello テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Zello に接続できることを確認します。 接続に失敗した場合は、Zello アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Zello に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Zello のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Zello API でサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Zello で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | externalId | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、組織内でより広範に展開する前に、少数のユーザーとの同期を検証します。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zendesk-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Zendesk を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zendesk-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から Zendesk に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Zendesk ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Zendesk](http://www.zendesk.com/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- Zendesk でユーザーを作成する。
- アクセスが不要になった場合に Zendesk のユーザーを削除します。
- Microsoft Entra ID と Zendesk の間でユーザー属性の同期を維持する。
- Zendesk でグループおよびグループメンバーシップを設定する。
- Zendesk に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zendesk-tutorial) (推奨)

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロール、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)ロール、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)ロールのいずれか。
- 管理者権限がある Zendesk のユーザー アカウント。
- Professional プラン以上が有効になっている Zendesk テナント。

### 手順 1: プロビジョニングの展開を計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Zendesk の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Zendesk を構成する

1. [Zendesk 管理センター](https://support.zendesk.com/hc/en-us/articles/4581766374554#topic_hfg_dyz_1hb)にサインインします。
2. **[アプリと統合]**&gt;**[API]**&gt;**[Zendesk API]** に移動します。
3. [ **設定]** タブを選択し、トークン アクセスが **有効**になっていることを確認します。
4. **アクティブな** API トークンの右側にある [**API トークンの追加]** ボタンを選択します。 トークンが生成されて表示されます。
5. **API トークンの説明**を入力します。
6. トークンを**コピー**し、安全な場所に貼り付けます。 このウィンドウを閉じると、完全なトークンは再び表示されなくなります。
7. [ **保存] を** 選択して API ページに戻ります。 トークンを選択して再度開くと、トークンの切り捨てられたバージョンが表示されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Zendesk を追加する

Microsoft Entra アプリケーション ギャラリーから Zendesk を追加して、Zendesk へのプロビジョニングの管理を開始します。 SSO のために Zendesk を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Zendesk への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループ割り当てに基づいて、Zendesk でユーザーまたはグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### ユーザーを Zendesk に割り当てる際の重要なヒント

- 現在、Zendesk のロールは、Azure portal UI で自動的かつ動的に設定されます。 Zendesk のロールをユーザーに割り当てる前に、必ず Zendesk との初期同期を完了して、お使いの Zendesk テナントの最新ロールを取得してください。
- 単一の Microsoft Entra ユーザーを Zendesk に割り当てて、初期の自動ユーザー プロビジョニングの構成をテストすることをお勧めします。 テストが成功した後で、追加のユーザーまたはグループを割り当てることができます。
- Zendesk にユーザーを割り当てるとき、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログ ボックスで選択します。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

#### Microsoft Entra ID で Zendesk の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Zendesk]** を選択します。

    [Image: アプリケーションリストの Zendesk のリンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションに、Zendesk アカウントの管理ユーザー名、シークレット トークン、ドメインを入力します。 これらの値の例を次に示します。

    - **[管理ユーザー名]** ボックスに、Zendesk テナントの管理者アカウントのユーザー名を入力します。 たとえば admin@contoso.com です。
    - **[シークレット トークン]** ボックスに、手順 6 で説明されているシークレット トークンを入力します。
    - **[ドメイン]** ボックスに、Zendesk テナントのサブドメインを入力します。 たとえば、テナント URL が `https://my-tenant.zendesk.com` のアカウントの場合、サブドメインは **my-tenant** になります。
7. Zendesk アカウントのシークレット トークンは、上記の **ステップ 2** で説明したステップに従って生成できます。
8. 手順 5 に示されているボックスに入力したら、**[テスト接続]** を選択して、Microsoft Entra ID が Zendesk に接続できることを確認します。 接続できない場合は、使用中の Zendesk アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: Zendesk テスト接続のスクリーンショット]
9. [ **作成]** を選択して構成を作成します。
10. [**概要**] ページで **[プロパティ**] を選択します。
11. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
12. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Zendesk に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Zendesk のユーザー アカウントとの照合に使用されます。 すべての変更を保存するために、 **[保存]** を選択します。

    [Image: Zendesk の一致するユーザー属性のスクリーンショット]
14. **[グループ]** を選びます。
15. **[属性マッピング]** セクションで、Microsoft Entra ID から Zendesk に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Zendesk のグループとの照合に使用されます。 すべての変更を保存するために、 **[保存]** を選択します。

    [Image: Zendesk が一致するグループ属性のスクリーンショット]
16. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
17. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
18. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

**[同期の詳細]** セクションを使用して進行状況を監視し、リンクをクリックしてプロビジョニング アクティビティ レポートを取得できます。 このレポートには、Microsoft Entra プロビジョニング サービスによって Zendesk に対して実行されたすべてのアクションが記述されます。

### コネクタの制限事項

- Zendesk は、**エージェント**の役割のみを持つユーザーのグループの使用をサポートしています。 詳細については、[Zendesk のドキュメント](https://support.zendesk.com/hc/en-us/articles/203661966-Creating-managing-and-using-groups)を参照してください。
- カスタム役割がユーザーやグループに割り当てられると、Microsoft Entra の自動ユーザー プロビジョニング サービスも既定のロールを**エージェント**に割り当てます。 エージェントのみにカスタム ロールを割り当てることができます。 詳細については、[Zendesk API のドキュメント](https://developer.zendesk.com/rest_api/docs/support/users#json-format-for-agent-or-admin-requests)を参照してください。
- カスタム ロールの中に組み込みロールの "エージェント" または "エンド ユーザー" と同様の表示名がある場合、すべてのロールのインポートに失敗します。 これを回避するには、インポートするカスタム ロールに上記の表示名が含まれないことを確実にします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zendesk-tutorial"} -->
## Microsoft Entra ID で Zendesk for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zendesk-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-17
- Summary: Microsoft Entra ID と Zendesk 間にシングル サインオンを構成する方法について説明します。

この記事では、Zendesk と Microsoft Entra ID を統合する方法について説明します。 Zendesk を Microsoft Entra ID と統合すると、次のことができます。

- Zendesk にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zendesk に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Zendesk サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zendesk では、**SP** Initiated SSO がサポートされます。
- Zendesk では、[**自動化された**ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zendesk-provisioning-tutorial)がサポートされます。

### ギャラリーからの Zendesk の追加

Microsoft Entra ID への Zendesk の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zendesk を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zendesk**」と入力します。
4. 結果のパネルから **[Zendesk]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zendesk 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zendesk に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Zendesk の関連ユーザーとの間にリンク関係を確立する必要があります。

Zendesk に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zendesk の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zendesk のテストユーザーを作成** - Zendesk で B.Simon の対応するユーザーを作成し、そのユーザーを Microsoft Entra 上のユーザーの表現とリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zendesk**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<subdomain>.zendesk.com`

    b。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<subdomain>.zendesk.com`

    c. **[応答 URL]** ボックスに、`https://<subdomain>.zendesk.com/access/saml` のパターンを使用して URL を入力します

    注意

    これらの値は実際の値ではありません。 これらの値を実際のサインオン URL、識別子、および応答 URL で更新してください。 これらの値を取得するには、[Zendesk クライアント サポート チーム](https://support.zendesk.com/hc/en-us/articles/203663676-Using-SAML-for-single-sign-on-Professional-and-Enterprise)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Zendesk アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 必須の SAML 属性はありませんが、必要に応じて、アプリケーション統合ページの **[ユーザー属性]** セクションから管理できます。 [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** ボタンを選択して [ **ユーザー属性] ダイアログを** 開きます。

    [Image: このスクリーンショットは、[編集] アイコンが選択された状態の [User Attributes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー属性) を示しています。]

    注意

    拡張機能属性を使用して、Microsoft Entra ID に含まれていない属性を既定で追加します。 [SAML で設定できるユーザー属性](https://support.zendesk.com/hc/articles/203663676-Using-SAML-for-single-sign-on-Professional-and-Enterprise-)を選択して、**Zendesk** が受け入れる SAML 属性の完全な一覧を取得します。
7. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集を示すスクリーンショット。]
8. **[SAML 署名証明書]** セクションで **[Thumbprint Value](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/拇印の値)** をコピーし、お使いのコンピューターに保存します。

    [Image: サムプリントの値をコピーすることを示すスクリーンショット。]
9. **[Zendesk のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zendesk の SSO の構成

チーム メンバー用に 1 つの SAML 構成を設定し、エンド ユーザー用に 2 つめの SAML 構成を設定できます。

1. 別の Web ブラウザー ウィンドウで、Zendesk 企業サイトに管理者としてサインインします
2. **Zendesk 管理センター**で、[**アカウント] -&gt; [セキュリティ] -&gt; [シングル サインオン**] に移動し、[**SSO 構成の作成**] を選択し、[**SAML**] を選択します。

    [Image: [Security settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ設定) が選択されている Zendesk 管理センターを示すスクリーンショット。]
3. **[シングル サインオン]** ページで次の手順を実行します。

    [Image: シングル サインオンを示すスクリーンショット。]

    ある。 **[Configuration name] (構成名)** に、自分の構成の名前を入力します。 最大 2 つの SAML と 2 つの JWT の構成が可能です。

    b。 **[SAML SSO URL]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    c. **[Certificate fingerprint]** テキストボックスに、証明書の**拇印**の値を貼り付けます。

    d. **[リモート ログアウト URL]** テキストボックスに **[ログアウト URL]** の値を貼り付けます。

    え **保存** を選択します。

SAML 構成を作成したら、エンド ユーザーまたはチーム メンバーに割り当ててアクティブ化する必要があります。

1. **[Zendesk 管理 センター]** で、**[アカウント] -&gt;[セキュリティ]** に移動し、**[チームメンバーの認証]** または **[エンドユーザー認証]** を選択します。
2. 構成をチーム メンバーに割り当てる場合は、**[外部認証]** を選択して認証オプションを表示します。 これらのオプションは、エンド ユーザーについては既に表示されています。
3. [**外部認証**] セクションで [**シングル サインオン (SSO)]** オプションを選択し、使用する SSO 構成の名前を選択します。
4. グループに複数の認証方法が割り当てられている場合は、このユーザー グループのプライマリ SSO 方法を選択します。 このオプションは、ユーザーが認証を必要とするページに移動するときに使用される既定の方法を設定します。
5. **保存** を選択します。

#### Zendesk のテスト ユーザーの作成

このセクションの目的は、ZendeskにBritta Simon というユーザーを作成することです。 Zendesk では、自動ユーザー プロビジョニングがサポートされています。この設定は、既定で有効になっています。 自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zendesk-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zendesk のサインオン URL にリダイレクトされます。
- Zendesk のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Zendesk] タイルを選択すると、このオプションは Zendesk のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zengine-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zengine を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zengine-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zengine の間にシングル サインオンを構成する方法について説明します。

この記事では、Zengine と Microsoft Entra ID を統合する方法について説明します。 Zengine を Microsoft Entra ID と統合すると、次のことができます。

- Zengine にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Zengine に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Zengine サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zengine では、**SP** initiated SSO がサポートされます。

### ギャラリーからの Zengine の追加

Microsoft Entra ID への Zengine の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zengine を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zengine**」と入力します。
4. 結果のパネルから **Zengine** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zengine 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Zengine に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Zengine の関連ユーザーとの間にリンク関係を確立する必要があります。

Zengine に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zengine の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zengine テストユーザーを作成** - Microsoft Entra における B.Simon の表現にリンクした、Zengine 内での B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Zengine**&gt;**シングル サインオンにアクセスします**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.zenginehq.com/saml2/v1/metadata/<ENVIRONMENT_NAME>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.zenginehq.com/saml2/v1/sls/<ENVIRONMENT_NAME>`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Zengine クライアント サポート チーム](mailto:support@wizehive.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Zengine のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zengine の SSO を構成する

**Zengine** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を、[Zengine サポート チーム](mailto:support@wizehive.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Zengine のテスト ユーザーを作成する

このセクションでは、Zengine で Britta Simon というユーザーを作成します。 [Zengine サポート チーム](mailto:support@wizehive.com)と連携して、Zengine プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zengine のサインオン URL にリダイレクトされます。
- Zengine のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Zengine] タイルを選択すると、このオプションは Zengine のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zenqms-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ZenQMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zenqms-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ZenQMS の間でシングル サインオンを構成する方法について説明します。

この記事では、ZenQMS と Microsoft Entra ID を統合する方法について説明します。 ZenQMS と Microsoft Entra ID を統合すると、次のことができます。

- ZenQMS にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して ZenQMS に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ZenQMS でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ZenQMS では、SP が開始した SSO  と IDP が開始した SSO  がサポートされます。

### ギャラリーから ZenQMS を追加する

Microsoft Entra ID への ZenQMS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ZenQMS を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [**ギャラリーからの追加**] セクションで、検索ボックスに「ZenQMS 」を入力します。
4. 結果パネル **ZenQMS** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ZenQMS の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、ZenQMS に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ZenQMS の関連ユーザーとの間にリンク関係を確立する必要があります。

ZenQMS に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ZenQMS SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **ZenQMSテストユーザーを作成 - ZenQMS内でMicrosoft EntraのユーザーであるB.Simonに対応するユーザーをリンクする。**
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**ZenQMS**&gt;**シングルサインオン**に移動します。
3. [**シングル サインオン方法を選択]** ページで、[**SAML**] を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成] を編集する
5. **基本的な SAML 構成** セクションで、アプリケーション **を IDP** 開始モードで構成する場合は、次の手順を行ってください。

    ある。 [**識別子** テキスト ボックスに、次のパターンを使用して値を入力します: `urn:zenqms:<INSTANCE>`

    b。 [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<INSTANCE>.zenqms.com/SAML/AssertionConsumerService`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **ログイン URL** |
    | --- |
    | `https://<INSTANCE>.zenqms.com/<ID>` |
    | `https://<INSTANCE>.zenqms.com/<EMAIL DOMAIN>/` |

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値 [取得するには、ZenQMS クライアント サポート チーム](mailto:help@zenqms.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ZenQMS SSO の構成

ZenQMS **側** シングル サインオンを構成するには、**アプリフェデレーション メタデータ URL** を ZenQMS サポート チーム 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ZenQMS テスト ユーザーの作成

このセクションでは、ZenQMS で Britta Simon というユーザーを作成します。 ZenQMS サポート チーム  と連携して、ZenQMS プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP開始済み

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ZenQMS サインオン URL にリダイレクトされます。
- ZenQMS のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDPが開始されました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ZenQMS に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで ZenQMS タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ZenQMS に自動的にサインインされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zenvoices-imap-tutorial"} -->
## Microsoft Entra ID を使用して Zenvoices IMAP for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zenvoices-imap-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-09
- Summary: Microsoft Entra と Zenvoices IMAP 間にシングル サインオンを構成する方法について説明します。

この記事では、Zenvoices IMAP と Microsoft Entra ID を統合する方法について説明します。 Zenvoices IMAP を Microsoft Entra ID と統合すると、次のことができます。

Microsoft Entra ID を使用して、Zenvoices IMAP にアクセスできるユーザーを制御する。 ユーザーが自分の Microsoft Entra アカウントを使用して Zenvoices IMAP に自動的にサインインできるようにする。 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Zenvoices IMAP でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから Zenvoices IMAP を追加する

Microsoft Entra ID への Zenvoices IMAP の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Zenvoices IMAP を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Zenvoices IMAP**」と入力します。
4. 結果パネルで **Zenvoices IMAP** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zenvoices IMAP**&gt;**シングルサインオン**に移動します。
3. 次のセクションで、次の手順を実行します。

    1. [ **アプリケーションに移動] を**選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID を**コピーし、後で Zenvoices IMAP 側の構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
    3. [ **エンドポイント** ] タブで、 **OpenID Connect メタデータ ドキュメント** リンクをコピーし、後で Zenvoices IMAP 側の構成で使用します。

        [Image: タブにエンドポイントが表示されているスクリーンショット。]
4. 左側のメニューの [ **認証** ] タブに移動し、次の手順を実行します。

    1. [ **リダイレクト URI** ] ボックスに、URL を入力します。 `https://app.zenvoices.com/ChannelConnector/ProcessEntraIMAPAuthorizationResult`

        [Image: リダイレクト値を示すスクリーンショット。]
    2. [ **構成] ボタンを** 選択します。
5. 左側のメニューの **[証明書とシークレット** ] に移動し、次の手順を実行します。

    1. [ **クライアント シークレット** ] タブに移動し、[ **+新しいクライアント シークレット**] を選択します。
    2. テキストボックスに有効な **説明** を入力し、要件に従ってドロップダウンから **[有効期限** 日] を選択し、[ **追加**] を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、 **値** が生成されます。 この値をコピーして、後で Zenvoices IMAP 側の構成で使用します。

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

このセクションでは、Zenvoices IMAP へのアクセスを許可することで、B.Simon がシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Zenvoices IMAP** にアクセスします。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Zenvoices IMAP SSO を構成する

**Zenvoices IMAP** 側で OAuth/OIDC フェデレーションのセットアップを完了するには、テナント ID、アプリケーション ID、クライアント シークレットなどのコピーされた値を Entra から [Zenvoices IMAP サポート チーム](mailto:info@zenvoices.com)に送信する必要があります。 サポート チームはこれを設定して、OIDC 接続が両方の側で正しく設定されるようにします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zenya-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Zenya を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zenya-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Zenya に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、Zenya と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを [Zenya](https://www.infoland.nl/) に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。 この記事を使用する前に、すべての要件を把握し、満たしていることを確認してください。 質問がある場合は、Infoland にお問い合わせください。

### サポートされる機能

>
> - Zenya でユーザーを作成する
> - アクセスが不要になったときに Zenya のユーザーを削除または無効化する
> - Microsoft Entra ID と Zenya の間でユーザー属性の同期を維持する
> - Zenya でグループとグループメンバーシップを設定する
> - Zenya に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zenya-tutorial) (推奨)
>

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Zenya テナント](https://www.infoland.nl/)。
- 管理者アクセス許可がある Zenya のユーザー アカウント。

### 手順 1: プロビジョニングの展開を計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Zenya の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Zenya を構成する

1. [Zenya 管理コンソール](https://www.infoland.nl/)にサインインします。 アプリケーション管理 に移動します。

    [Image: [Zenya 管理コンソール] を示すスクリーンショット。]
2. **[外部ユーザーの管理]** を選択します。

    [Image: [外部ユーザーの管理] リンクが強調表示されている Zenya の [ユーザーとグループ] ページを示すスクリーンショット。]
3. 新しいプロバイダーを追加するには、**プラス** アイコンをクリックします。 新しい **[Add provider](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロバイダーの追加)** ダイアログ ボックスに **[Title](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/タイトル)** を入力します。 **[IP-based access restriction](IP ベースのアクセス制限)** の追加を選択することもできます。 **[OK]** を選択します。

    [Image: Zenya の [新規追加] ボタンを示すスクリーンショット。]

    [Image: Zenya の [プロバイダーの追加] ページを示すスクリーンショット。]
4. **[永続的なトークン]** ボタンを選択します。 **[永続的なトークン]** をコピーして保存します。 これは後で表示できません。 この値を、Zenya アプリケーションの [プロビジョニング] タブ内の [シークレット トークン] フィールドに入力します。

    [Image: トークンを作成するための Zenya の [ユーザー プロビジョニング] ページを示すスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Zenya を追加する

Microsoft Entra アプリケーション ギャラリーから Zenya を追加して、Zenya へのプロビジョニングの管理を開始します。 SSO のために Zenya を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Zenya への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループ割り当てに基づいて、Zenya でユーザーまたはグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

詳細 (オランダ語) については、[`Implementatie SCIM koppeling`](https://webshare.iprova.nl/8my7yg8c1ofsmdj9/Document.aspx) も参照してください。

#### Microsoft Entra ID で Zenya の自動ユーザー プロビジョニングを構成するには、次の操作を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示すスクリーンショット。]
3. アプリケーションの一覧で **[Zenya]** を選択します。

    [Image: アプリケーションのリストから、アプリケーションを選択するスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションの **[テナント URL]** に、先ほど取得した **SCIM 2.0 ベース URL と永続的なトークン**の値を入力し、それに /scim/ を追加します。 また、**シークレット トークン**も追加します。 **[永続的なトークン]** ボタンを使用することで、Zenya でシークレット トークンを生成できます。 **[接続テスト]** を選択して、Microsoft Entra ID が Zenya に接続できることを確認します。 接続できない場合は、使用中の Zenya アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: [テスト接続] ページと、[テナント URL] および [トークン] のフィールドを示すスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Zenya に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Zenya のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | 活動中 | ブール値 |
    | ディスプレイ名 | 糸 |
    | emails[type eq "仕事"].value | 糸 |
    | 優先言語 | 糸 |
    | ユーザー名 | 糸 |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |
    | エクスターナルID | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |
    | タイトル | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | 糸 |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Zenya に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Zenya のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | ディスプレイ名 | 糸 |
    | メンバー | リファレンス |
    | 外部 ID | 糸 |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更履歴

- 2020 年 6 月 17 日 - エンタープライズ拡張属性 **urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager** のサポートが削除されました。
- 2023 年 10 月 11 日 - コア属性 **title** のサポートが追加され、エンタープライズ拡張属性 **urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department** と **urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager** のサポートが追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zenya-tutorial"} -->
## Microsoft Entra ID で Zenya for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zenya-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zenya の間にシングル サインオンを構成する方法について説明します。

この記事では、Zenya と Microsoft Entra ID を統合する方法について説明します。 Zenya と Microsoft Entra ID を統合すると、次のことができます。

- Zenya にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Zenya に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zenya でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zenya では、**SP** Initiated SSO がサポートされます
- Zenya では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zenya-provisioning-tutorial)がサポートされます。

### ギャラリーから Zenya を追加する

Microsoft Entra ID への Zenya の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Zenya を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zenya**」と入力します。
4. 結果のパネルから **[Zenya]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zenya 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Zenya に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Zenya の関連ユーザーとの間にリンク関係を確立する必要があります。

Zenya に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zenya の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zenya テストユーザーの作成** - Zenya で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Zenya からの構成情報の取得

このセクションでは、Microsoft Entra のシングル サインオンを構成するための情報を Zenya から取得します。

1. Web ブラウザーを開き、次の URL パターンを使用して Zenya の **[SAML2 info](SAML2 の情報)** ページに移動します。

    `https://<SUBDOMAIN>.zenya.work/saml2info``https://<SUBDOMAIN>.iprova.nl/saml2info``https://<SUBDOMAIN>.iprova.be/saml2info``https://<SUBDOMAIN>.iprova.eu/saml2info`

    [Image: Zenya の [SAML2 の情報] ページのスクリーンショット。]
2. そのブラウザー タブを開いたまま、別のブラウザー タブで次の手順に進みます。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zenya**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するページのスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **Zenya の [SAML2 info](SAML2 の情報)** ページで、 **[Sign-on URL](サインオン URL)** というラベルの後にある **[Sign-on URL](サインオン URL)** ボックスに値を入力します。 このページは他のブラウザー タブで開いたままです。

    b。 Zenya の **[SAML2 info](SAML2 の情報)** ページで、 **[EntityID]** というラベルの後にある **[Identifier](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/識別子)** ボックスに値を入力します。 このページは他のブラウザー タブで開いたままです。

    c. Zenya の **[SAML2 info](SAML2 の情報)** ページで、 **[Reply URL](応答 URL)** というラベルの後にある **[Reply-URL]** ボックスに値を入力します。 このページは他のブラウザー タブで開いたままです。

    d. **[ログアウト URL]** ボックスに、**[Zenya SAML2 の情報]** ページの **[ログアウト URL]** というラベルの背後に表示されている値を入力します。 このページは他のブラウザー タブで開いたままです。
6. Zenya アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 既定の属性の一覧を示すスクリーンショット。]
7. その他に、Zenya アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 | Namespace |
    | --- | --- | --- |
    | `samaccountname` | `user.onpremisessamaccountname` | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [SAML 署名証明書] のダウンロード リンク含む情報を示すスクリーンショット。]

### Microsoft Entra テスト ユーザーを作成する

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

### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に Zenya へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Zenya** に移動してください。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Zenya SSO の構成

1. **Administrator** アカウントを使用して Zenya にサインインします。
2. **[Go to](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/移動)** メニューを開きます。
3. **[Application management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーション管理)** を選択します。
4. **[System settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/システム設定)** パネルの **[General](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般)** を選択します。
5. **[編集]** を選択します。
6. **[Access control](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス制御)** まで下にスクロールします。

    [Image: Zenya の [アクセス制御] の設定を示すスクリーンショット。]
7. **[Users are automatically logged on with their network accounts](ユーザーのネットワーク アカウントを使用して自動的にログオンする)** 設定を探し、**[Yes, authentication via SAML](はい、SAML 経由で認証します)** に変更します。 これで、追加のオプションが表示されます。
8. **[セットアップ]** を選びます
9. [**次へ**] を選択します。
10. Zenya によって、URL からフェデレーション データをダウンロードするか、それともファイルからアップロードするかを問われます。 **[from URL](URL から)** オプションを選択します。

    [Image: Microsoft Entra のメタデータをダウンロードするための URL を入力するページを示すスクリーンショット。]
11. 「Microsoft Entra シングル サインオンを構成する」セクションの最後のステップで保存したメタデータ URL を貼り付けます。
12. 矢印形のボタンを選んで、Microsoft Entra ID からメタデータをダウンロードします。
13. ダウンロードが完了したら、**Valid Federation Data file downloaded (有効なフェデレーション データ ファイルがダウンロードされました)** という確認メッセージが表示されます。
14. [**次へ**] を選択します。
15. ここでは **[Test login](テスト ログイン)** オプションをスキップし、**[Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ)** を選択します。
16. **[Claim to use](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/使用する要求)** ドロップダウン ボックスで **[windowsaccountname]** を選択します。
17. **完了** を選択します。
18. これで、**[Edit general settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般設定の編集)** 画面に戻ります。 ページの下部までスクロールし、**[OK]** を選択して自分の構成を保存します。

### Zenya テスト ユーザーの作成

1. **Administrator** アカウントを使用して Zenya にサインインします。
2. **[Go to](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/移動)** メニューを開きます。
3. **[Application management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーション管理)** を選択します。
4. **[Users and user groups](ユーザーとユーザー グループ)** パネルの **[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** を選択します。
5. [**] を選択し、[**] を追加します。
6. **[ユーザー名]** ボックスにユーザー名 (`B.Simon@contoso.com` など) を入力します。
7. **[Full name](フル ネーム)** ボックスにユーザーのフル ネームを入力します (「**B.Simon**」など)。
8. **[No password (use single sign-on)](パスワードなし (シングル サインオンを使用する))** オプションを選択します。
9. **[E-mail address](メール アドレス)** ボックスに、ユーザーのメール アドレス (`B.Simon@contoso.com` など) を入力します。
10. ページの一番下までスクロールし、**[Finish](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/完了)** を選択します。

注

Zenya では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zenya-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zenya のサインオン URL にリダイレクトされます。
- Zenya のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Zenya] タイルを選択すると、このオプションは Zenya のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zephyrsso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ZephyrSSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zephyrsso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ZephyrSSO 間のシングル サインオンを構成する方法について説明します。

この記事では、ZephyrSSO と Microsoft Entra ID を統合する方法について説明します。 ZephyrSSO を Microsoft Entra ID と統合すると、次の利点があります。

- ZephyrSSO にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが Microsoft Entra アカウントで ZephyrSSO に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ZephyrSSO でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ZephyrSSO では、 **IDP** Initiated SSO がサポートされます

### ギャラリーからの ZephyrSSO の追加

Microsoft Entra ID への ZephyrSSO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ZephyrSSO を追加する必要があります。

**ギャラリーから ZephyrSSO を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **ZephyrSSO**」と入力し、結果パネルで **ZephyrSSO** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の ZephyrSSO]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、ZephyrSSO で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと ZephyrSSO の関連ユーザー間にリンク関係を確立する必要があります。

ZephyrSSO に対する Microsoft Entra シングル サインオンを構成・テストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **ZephyrSSO シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **ZephyrSSO のテスト ユーザーの作成** - ZephyrSSO で、Microsoft Entra 内のユーザー Britta Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

ZephyrSSO に対する Microsoft Entra シングル サインオンを構成するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ZephyrSSO** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    [Image: ZephyrSSO のドメインと URL のシングル サインオン情報]

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.yourzephyr.com/Zephyrsso`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.yourzephyr.com/flex/saml/sso`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、ZephyrSSO クライアント サポート チーム](https://support.getzephyr.com/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **ZephyrSSO のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### ZephyrSSO シングル サインオンの構成

**ZephyrSSO** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [ZephyrSSO サポート チーム](https://support.getzephyr.com/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### ZephyrSSO のテスト ユーザーの作成

このセクションでは、ZephyrSSO で Britta Simon というユーザーを作成します。 [ZephyrSSO サポート チーム](https://support.getzephyr.com/)と協力して、ZephyrSSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [ZephyrSSO] タイルを選択すると、SSO を設定した ZephyrSSO に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zero-networks-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zero Networks を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zero-networks-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zero Networks の間でシングル サインオンを構成する方法について説明します。

この記事では、Zero Networks と Microsoft Entra ID を統合する方法について説明します。 Zero Networks と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID を使用して、Zero Networks へのアクセス権を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zero Networks に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zero Networks でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、Zero Networks 管理ポータルとアクセス ポータルに対して Microsoft Entra SSO を構成します。

- Zero Networks では、**SP** と **IDP** Initiated SSO がサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからゼロネットワークを追加

Microsoft Entra ID への Zero Networks の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zero Networks を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zero Networks**」と入力します。
4. 結果のパネルから **[ゼロ ネットワーク]** を選択し、**[作成]** を選択してアプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Zero Networks** に移動します。
3. **[シングル サインオン]** を選択します。
4. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
5. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成] の編集
6. [**基本的な SAML 構成**] セクションで、次の手順を実行します。

    エー。 **識別子 (エンティティ ID)** テキスト ボックスに、URL を入力します: `https://<customerUrl>.zeronetworks.com/api/v1/sso/azure/metadata`

    b。 **[応答 URL (Assertion Consumer Service URL)]** テキスト ボックスに、URL `https://<customerUrl>.zeronetworks.com/api/v1/sso/azure/acs` を入力します。

    c. [**サインオン URL** テキスト ボックスに、URL: `https://<customerUrl>.zeronetworks.com/#/login` を入力します。
7. [**SAML** でのシングル サインオンの設定] ページの [**SAML 証明書**] セクションで、[**証明書 (Base64)** を探し、[**のダウンロード** 選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **ゼロ ネットワークのセットアップ** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

### Zero Networks の SSO の構成

1. Zero Networks 管理ポータルに管理者としてログインします。
2. **設定**&gt;**IDプロバイダー**に移動します。
3. **Microsoft Azure** を選択し、次の手順を実行します。

    [Image: SSO 構成の設定を示すスクリーンショット。]

    1. **のログイン URL** テキストボックスに、前にコピーした **のログイン URL** 値を貼り付けます。
    2. **ログアウト URL** ボックスに、前にコピーした **ログアウト URL** 値を貼り付けます。
    3. ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容を **Certificate (Base64)** ボックスに貼り付けます。
    4. **保存** を選択します。

### ユーザー割り当ての要件を構成する

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zero Networks** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて**、[プロパティ**] を選択します。
3. **[ユーザーの割り当てが必要]** を **[いいえ]** に変更します。

[Image: ユーザー割り当てが必要なスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zero Networks のサインオン URL にリダイレクトされます。
- Zero Networks のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ゼロ ネットワーク] タイルを選択すると、このオプションは Zero Networks のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zero-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Zero を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zero-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から Zero に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Zero ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Zero](https://teamzero.com/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Zero でユーザーを作成する。
- アクセスが不要になった場合は、Zero のユーザーを削除します。
- Microsoft Entra ID と Zero の間でユーザー属性の同期を維持する。
- ゼロでグループとグループメンバーシップを設定する。
- Zero に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)する (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- SCIM ユーザー プロビジョニング サービスでの [Zero アカウント](https://www.teamzero.com)。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Zero の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Zero を構成する

1. ZERO アカウント管理者に SCIM シークレット トークンを取得するには [、Zero サポート](https://help.teamzero.com/) に問い合わせてください。この値は、Zero アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。
2. テナント URL は `https://api.teamzero.com/scim/v2/` です。 この値は、ゼロ アプリケーションの [プロビジョニング] タブの [テナント URL] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Zero を追加する

Microsoft Entra アプリケーション ギャラリーから Zero を追加して、Zero へのプロビジョニングの管理を開始します。 SSO のために Zero を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Zero への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループ割り当てに基づいて、Zero でユーザーまたはグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Zero の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Zero]** を選択します。

    [Image: アプリケーション一覧の Zero のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、ゼロ テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID がゼロに接続できることを確認します。 接続に失敗した場合は、Zero アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Zero に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Zero のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Zero API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | タイトル | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | エクスターナルID | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:組織 | 糸 |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Zero に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Zero のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ディスプレイ名 | 糸 | ✓ |
    | エクスターナルID | 糸 |  |
    | メンバー | リファレンス |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zeroheight-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に zeroheight を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zeroheight-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と zeroheight の間にシングル サインオンを構成する方法について説明します。

この記事では、zeroheight と Microsoft Entra ID を統合する方法について説明します。 zeroheight を Microsoft Entra ID と統合すると、次のことができます。

- zeroheight にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って zeroheight に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な zeroheight サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- zeroheight では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの zeroheight の追加

Microsoft Entra ID への zeroheight の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に zeroheight を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**zeroheight**」と入力します。
4. 結果のパネルから **[zeroheight]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### zeroheight 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、zeroheight に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと zeroheight の関連ユーザーとの間にリンク関係を確立する必要があります。

zeroheight に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **zeroheight の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **zeroheight テスト ユーザーの作成** - Microsoft Entra の B.Simon にリンクさせるために、対応するユーザーを zeroheight で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**zeroheight**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `zeroheight:<CUSTOM_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://zeroheight.com/sso/acs/<CUSTOM_ID>`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://zeroheight.com/sso`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値は、アカウントで自動的に生成されます。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. zeroheight アプリケーションでは、SAML アサーションを特定の形式にする必要があり、ご自分の SAML トークンの属性の構成にカスタム属性のマッピングを追加する必要があります。 次のセクションで既定の属性をご確認ください。

    [Image: 画像]
7. zeroheight では、いずれの既定の属性も使用されません。 代わりに、SAML 応答で返されるように次の属性を追加してください。 これらの属性も事前設定する必要がありますが、要件に従って確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ファーストネーム | ユーザー.ファーストネーム |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### zeroheight の SSO の構成

**zeroheight** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** をブラウザーに貼り付け、XML ファイルをダウンロードする必要があります。 次に、ID プロバイダーの単一 Sign-On URL と X.509 証明書を抽出する必要があります。 わからない場合は、IT チームに問い合わせてください。

#### zeroheight のテスト ユーザーの作成

このセクションでは、zeroheight で Britta Simon というユーザーを作成します。 [zeroheight サポート チーム](mailto:support@zeroheight.com)と連携し、zeroheight プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる zeroheight のサインオン URL にリダイレクトされます。
- zeroheight のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで zeroheight タイルを選択すると、このオプションは zeroheight のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zest-tutorial"} -->
## Microsoft Entra ID で Zest for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zest-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zest 間にシングル サインオンを構成する方法について説明します。

この記事では、Zest と Microsoft Entra ID を統合する方法について説明します。 Zest を Microsoft Entra ID を統合すると、次のことができます。

- Zest にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Zest に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zest でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zest では、**IDP** によって開始される SSO がサポートされています。

### ギャラリーから Zest を追加する

Microsoft Entra ID への Zest の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Zest を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zest**」と入力します。
4. 結果パネルから **[Zest]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zest 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zest に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Zest の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Zest と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zest SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zestでのテストユーザーの作成** - Microsoft EntraのユーザーとしてB.Simonに対応するZestのユーザーを作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Zest**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `http://my.zestbenefits.com/idp/identity/AuthServices` |
    | `http://my.zestbenefits.com/idp/identity/AuthServices?<SSOPortalId>` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://my.zestbenefits.com/idp/identity/AuthServices/Acs` |
    | `https://<CustomDomain>/idp/identity/AuthServices/Acs` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Zest クライアント サポート チーム](mailto:help@zestbenefits.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Zest のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zest SSO の構成

**Zest** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [Zest サポート チーム](mailto:help@zestbenefits.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Zest のテスト ユーザーの作成

このセクションでは、Zest で Britta Simon というユーザーを作成します。 [Zest サポート チーム](mailto:help@zestbenefits.com)と連携して、Zest プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Zest に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Zest] タイルを選択すると、SSO を設定した Zest に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ziflow-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Ziflow を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ziflow-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Ziflow の間のシングル サインオンを構成する方法について説明します。

この記事では、Ziflow と Microsoft Entra ID を統合する方法について説明します。 Ziflow を Microsoft Entra ID と統合すると、次のことが可能になります。

- Ziflow にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Ziflow に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Ziflow シングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Ziflow では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Ziflow の追加

Microsoft Entra ID への Ziflow の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Ziflow を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Ziflow**」と入力します。
4. 結果のパネルから **[Ziflow]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Ziflow に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、Ziflow に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Ziflow の関連ユーザーとの間にリンク関係を確立する必要があります。

Ziflow に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Ziflow SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Ziflow テストユーザーの作成** - Microsoft Entra のユーザーである B.Simon にリンクされた、Ziflow 上の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Ziflow**&gt;**シングルサインオン**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:ziflow-production:<UNIQUE_ID>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://ziflow-production.auth0.com/login/callback?connection=<UNIQUE_ID>`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://ziflow-production.auth0.com/login/callback?connection=<UNIQUE_ID>`

    注

    上記の値は実際の値ではありません。 識別子、サインオン URL、応答 URL の一意の ID 値を実際の値で更新します。これについては、後で説明します。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Ziflow の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Ziflow SSO の構成

1. 別の Web ブラウザー ウィンドウで、セキュリティ管理者として Ziflow にサインインします。
2. 右上隅にある [アバター] を選択し、[アカウントの **管理**] を選択します。

    [Image: [Ziflow の構成の管理] のスクリーンショット]
3. 左上の [ **シングル サインオン**] を選択します。

    [Image: [Ziflow の構成 - サイン] のスクリーンショット]
4. [ **シングル サインオン** ] ページで、次の手順に従います。

    [Image: [Ziflow の構成 - シングル] のスクリーンショット]

    a. **[SAML2.0]** として **[種類]** を選択します。

    b。 **[サインオン URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    c. ダウンロードした base 64 でエンコードされた証明書を **X509 署名証明書**にアップロードします。

    d. **[サインアウト URL]** テキストボックスに、先ほどコピーした**ログアウト URL** の値を貼り付けます。

    e. **[Configuration Settings for your Identifier Provider (ID プロバイダーの構成設定)]** セクションで、強調表示されている一意の ID 値をコピーし、Azure portal の **[基本的な SAML 構成]** で ID とサインオン URL に追加します。

#### Ziflow のテスト ユーザーの作成

Microsoft Entra ユーザーが Ziflow にサインインできるようにするには、そのユーザーを Ziflow にプロビジョニングする必要があります。 Ziflow では、プロビジョニングは手動で行います。

ユーザー アカウントをプロビジョニングするには、次の手順を実行します。

1. セキュリティ管理者として Ziflow にサインインします。
2. 上部の **[ユーザー]** に移動します。

    [Image: [Ziflow の構成 - ユーザー] のスクリーンショット]
3. [ **追加]** を選択し、[ **ユーザーの追加]** を選択します。

    [Image: [ユーザーの追加] オプションが選択されていることを示すスクリーンショット。]
4. **[Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加)** ポップアップで、次の手順を実行します。

    [Image: [ユーザーの追加] ダイアログ ボックスを示すスクリーンショット。ここで、説明されている値を入力できます。]

    a. [ **電子メール** ] テキスト ボックスに、ユーザーの電子メール ( brittasimon@contoso.comなど) を入力します。

    b。 **[名]** ボックスに、ユーザーの名を入力します (例: Britta)。

    c. **[姓]** ボックスに、ユーザーの姓を入力します (例: Simon)。

    d. Ziflow のロールを選択します。

    e. [ **Add 1 user]\(1 ユーザーの追加\) を選択します**。

    注

    Microsoft Entra アカウント所有者が電子メールを受信し、リンクに従ってアカウントを確認すると、そのアカウントがアクティブになります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Ziflow のサインオン URL にリダイレクトされます。
- Ziflow のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Ziflow] タイルを選択すると、このオプションは Ziflow のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zip-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Zip を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zip-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から Zip に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Zip ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [Zip](https://ziphq.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Zip でユーザーを作成する
- アクセスが不要になった場合に Zip でユーザーを削除する
- Microsoft Entra ID と Zip の間でユーザー属性の同期を維持する
- Zip でグループとグループ メンバーシップをプロビジョニングする
- Zip に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zip-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [マイクロソフト Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- [Zip](https://ziphq.com/) テナント。
- 管理者のアクセス許可がある Zip のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Zip の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Zip を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように Zip を構成するには、[`support@ziphq.com`](mailto:support@ziphq.com) の Zip サポート チームにお問い合わせください。 手順 5. に記載されている、Zip への自動ユーザー プロビジョニングを設定するために必要なテナント URL とシークレット トークンが提供されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Zip を追加する

Microsoft Entra アプリケーション ギャラリーから Zip を追加して、Zip へのプロビジョニングの管理を開始します。 SSO のために Zip を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Zip への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループ割り当てに基づいて、Zip でユーザーまたはグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Zip の自動ユーザー プロビジョニングを構成するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**にアクセスする

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で [ **Zip**] を選択します。

    [Image: アプリケーションの一覧の Zip リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Zip テナント URL とシークレット トークンを入力します。 Microsoft Entra ID が Zip に接続できることを確認するには、[ **テスト接続** ] を選択します。 接続に失敗した場合は、Zip アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Zip に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Zip のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Zip API でサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 優先言語 | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | エクスターナルID | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | リファレンス |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から Zip に同期されるグループ **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Zip のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | 表示名 | 糸 | ✓ |
    | メンバー | リファレンス |  |
    | エクスターナルID | 糸 |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### 変更ログ

- 2022/02/14 - エンタープライズ拡張属性マッピングを追加しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zip-tutorial"} -->
## Microsoft Entra ID で Zip for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zip-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zip 間にシングル サインオンを構成する方法について説明します。

この記事では、Zip と Microsoft Entra ID を統合する方法について説明します。 Zip と Microsoft Entra ID を統合すると、次のことができます。

- Zip にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zip に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zip でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zip では、**SP開始SSO**および**IDP開始SSO**がサポートされています。
- Zip では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zip-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Zip の追加

Microsoft Entra ID への Zip の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Zip を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「Zip**」と入力します。
4. 結果パネルから **[Zip** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zip 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Zip に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Zip の関連ユーザーとの間にリンク関係を確立する必要があります。

Zip に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zip SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zip テスト ユーザーの作成** - Zip で B.Simon に対応するユーザーが、Microsoft Entra のユーザー表現にリンクされるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zip**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 応答 URL |
    | --- |
    | `https://ziphq.com/saml/acs` |
    | `https://<CUSTOMER_NAME>.ziphq.com/saml/acs` |
    |  |
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.ziphq.com`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Zip クライアント サポート チーム](mailto:support@tryevergreen.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Zip のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zip の SSO の構成

**Zip** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Zip サポート チーム](mailto:support@tryevergreen.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Zip のテスト ユーザーの作成

このセクションでは、Zip で Britta Simon というユーザーを作成します。 [Zip サポート チーム](mailto:support@tryevergreen.com)と協力して、Zip プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Zip では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zip-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zip サインオン URL にリダイレクトされます。
- Zip のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Zip に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Zip] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Zip に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zivver-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zivver を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zivver-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zivver の間にシングル サインオンを構成する方法について説明します。

この記事では、Zivver と Microsoft Entra ID を統合する方法について説明します。 Zivver と Microsoft Entra ID を統合すると、次のことができます。

- Zivver にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zivver に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zivver のシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Zivver では、**IDP** によって開始される SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Zivver を追加する

Microsoft Entra ID への Zivver の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Zivver を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Zivver**」と入力します。
4. 結果のパネルから **[Zivver]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zivver 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Zivver に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Zivver の関連ユーザーとの間にリンク関係を確立する必要があります。

Zivver に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zivver SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zivverのテストユーザーを作成** - B.SimonのZivverでのカウンターパートを作成し、それをMicrosoft Entraのユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Zivver**&gt;**シングル サインオン**に移動する。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    **[識別子]** ボックスに、`https://app.zivver.com/SAML/Zivver` という URL を入力します。
6. Zivver アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 Zivver アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: このスクリーンショットは、[編集] アイコンが選択された状態の [User Attributes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー属性) を示しています。]
7. その他に、Zivver アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 **[ユーザー属性]** ダイアログの **[ユーザー要求]** セクションで、以下の手順を実行して、以下の表のように SAML トークン属性を追加します。

    | 名前 | 名前空間 | ソース属性 |
    | --- | --- | --- |
    | ZivverAccountKey | https://zivver.com/SAML/Attributes | user.objectid (ユーザーのオブジェクトID) |

    注意

    オンプレミスの Active Directory と Microsoft Entra Connect ツールでハイブリッド セットアップを使用している場合は、VALUE を `user.objectGUID`

    ある。 [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [新しい要求の追加] オプションが備わっている [ユーザー要求] のスクリーンショット。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 **[名前]** ボックスに、その行に対して表示される属性名を入力します。

    c. **[名前空間]** テキストボックスに、「`https://zivver.com/SAML/Attributes`」と入力します。

    d. [ソース] として **[属性]** を選択します。

    え **[ソース属性]** の一覧から、その行に表示される属性値を入力します。

    f. **保存** を選択します。
8. [ **SAML での単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して **フェデレーション メタデータ XML** をダウンロードし、[ **コピー** ] アイコンを選択して、要件に従って指定されたオプションから **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書 URL のダウンロードのリンク]
9. **[Zivver の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zivver SSO を構成する

1. 別の Web ブラウザー ウィンドウで、Zivver 企業[サイト](https://app.zivver.com/login)に管理者としてサインインします。
2. ブラウザー ウィンドウの左下にある **[組織の設定]** アイコンを選択します。
3. **[Single sign-on](シングル サインオン)** に移動します。
4. 以前にダウンロードしたフェデレーション メタデータ XML ファイルを開きます。
5. **[Identity Provider metadata URL](ID プロバイダー メタデータ URL)** ボックスに、以前に保存した**アプリ フェデレーション メタデータ URL** を貼り付けます。
6. チェックボックス **[Turn on SSO](SSO をオンにする)** をオンにします。
7. **[保存] を選択します**。

#### Zivver テスト ユーザーの作成

このセクションでは、Zivver で Britta Simon というユーザーを作成します。 [Zivver サポート チーム](https://support.zivver.com/)と連携し、Zivver プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Zivver に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Zivver] タイルを選択すると、SSO を設定した Zivver に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zoho-mail-tutorial"} -->
## Microsoft Entra ID で Zoho for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zoho-mail-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zoho の間にシングル サインオンを構成する方法について説明します。

この記事では、Zoho と Microsoft Entra ID を統合する方法について説明します。 Zoho を Microsoft Entra ID と統合すると、次のことができます。

- Zoho にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zoho に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

Microsoft Entra と Zoho One の統合を構成するには、次の項目が必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Zoho でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Zoho では、 **SP** Initiated SSO がサポートされます

### ギャラリーからの Zoho の追加

Microsoft Entra ID への Zoho の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zoho を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Zoho**」と入力します。
4. 結果パネルから **Zoho** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zoho 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Zoho に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Zoho の関連ユーザーとの間にリンク関係を確立する必要があります。

Zoho に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zoho SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zoho テスト ユーザーの作成** - Zoho で B.Simon に対応するユーザーを作成し、それを Microsoft Entra での B.Simon とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zoho**&gt;**シングルサインオン**に移動してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.zohomail.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、 [Zoho クライアント サポート チーム](https://www.zoho.com/mail/contact.html) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Zoho のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

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
5. **「作成」を選択します。**

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に Zoho へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Zoho** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

#### Zoho SSO の構成

1. 別の Web ブラウザー ウィンドウで、Zoho Mail 企業サイトに管理者としてログインします。
2. **コントロール パネル**に移動します。

    [Image: コントロール パネル]
3. [ **SAML 認証** ] タブを選択します。

    [Image: SAML 認証]
4. [ **SAML Authentication Details]\(SAML 認証の詳細\)** セクションで、次の手順を実行します。

    [Image: SAML 認証の詳細]

    ある。 [ **ログイン URL** ] ボックスに、 **ログイン URL を**貼り付けます。

    b。 [ **ログアウト URL** ] ボックスに、 **ログアウト URL を**貼り付けます。

    c. [ **パスワードの変更 URL** ] ボックスに、 **パスワードの変更 URL を**貼り付けます。

    d. Azure portal からダウンロードした base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、 **PublicKey** テキストボックスに貼り付けます。

    え **アルゴリズム**として、**RSA** を選択します。

    f. [ **OK] を選択します**。

#### Zoho テスト ユーザーの作成

Microsoft Entra ユーザーが Zoho Mail にログインできるようにするには、そのユーザーを Zoho Mail にプロビジョニングする必要があります。 Zoho Mail の場合、プロビジョニングは手動で行います。

注

他の Zoho Mail ユーザー アカウント作成ツールや、Zoho Mail から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

#### ユーザー アカウントをプロビジョニングするには、次の手順を実行します。

1. **Zoho Mail** 企業サイトに管理者としてログインします。
2. **[コントロール パネル**] &gt;**[メール] > [ドキュメント]** に移動します。
3. **[ユーザーの詳細]**&gt;**[ユーザーの追加]**に移動します。

    [Image: スクリーンショットは、[ユーザーの詳細] と [ユーザーの追加] が選択されている Zoho メール サイトを示しています。]
4. [ **ユーザーの追加** ] ダイアログで、次の手順を実行します。

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザーの追加] ダイアログ ボックスを示しています。]

    ある。 **名** ボックスに、**Britta**のようなユーザーの名を入力します。

    b。 [ **姓]** ボックスに、ユーザーの姓 ( **Simon** など) を入力します。

    c. [ **電子メール ID** ] ボックスに、ユーザーの電子メール ID ( **brittasimon@contoso.com**など) を入力します。

    d. [ **パスワード** ] ボックスに、ユーザーのパスワードを入力します。

    え [ **OK] を選択します**。

    注

    アカウントがアクティブになる前に、Microsoft Entra アカウント所有者は、アカウント確認用のリンクを含むメールを受け取ります。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zoho のサインオン URL にリダイレクトされます。
- Zoho のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Zoho] タイルを選択すると、SSO を設定した Zoho に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zoho-one-china-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zoho One China を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zoho-one-china-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zoho One China の間にシングル サインオンを構成する方法について説明します。

この記事では、Zoho One China と Microsoft Entra ID を統合する方法について説明します。 Zoho One China を Microsoft Entra ID と統合すると、次のことができます。

- Zoho One China にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Zoho One China に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zoho One China でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zoho One China では、**SP と IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Zoho One China の追加

Microsoft Entra ID への Zoho One China の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zoho One China を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zoho One China**」と入力します。
4. 結果のパネルから **[Zoho One China]** を選択し、そのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zoho One China 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Zoho One China に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Zoho One China の関連ユーザーとの間にリンク関係を確立する必要があります。

Zoho One China に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zoho One China SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zoho One China のテストユーザーを作成する** - Microsoft Entra の B.Simon に対応するユーザーを Zoho One China で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Zoho One China**&gt;**シングル サインオン**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://accounts.zoho.com.cn/signin/samlsp/<zoid>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://accounts.zoho.com.cn/samlauthrequest/<zoid>?serviceurl=https://one.zoho.com.cn`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには、[Zoho One China クライアント サポート チーム](mailto:support@zohocorp.com.cn)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Zoho One China のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zoho One China SSO の構成

**Zoho One China** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Zoho One China サポート チーム](mailto:support@zohocorp.com.cn)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Zoho One China テスト ユーザーの作成

このセクションでは、Zoho One China で Britta Simon というユーザーを作成します。 [Zoho One China サポート チーム](mailto:support@zohocorp.com.cn)と連携して、Zoho One China プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Zoho One China のサインオン URL にリダイレクトされます。
- Zoho One China のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Zoho One China に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Zoho One China] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Zoho One China に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zoho-one-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Zoho One を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zoho-one-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から Zoho One にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Zoho One と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、[Zoho One](https://www.zoho.com) にユーザーとグループを自動的にプロビジョニングし、解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Zoho One でユーザーを作成します。
- アクセスが不要になった Zoho One のユーザーを削除します。
- Microsoft Entra ID と Zoho One の間でユーザー属性の同期を維持します。
- Zoho One でグループとグループ メンバーシップを設定する
- [Zoho Oneでのシングルサインオンの使用](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zohoone-tutorial)（推奨）。
- コード認証許可フロー認証がサポートされています。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Zoho One の管理者アカウント。

### 手順 1: プロビジョニングのデプロイを計画する

1. プロビジョニング サービスの [のしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)について説明します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra ID と Zoho One [の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決めます。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Zoho One を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように Zoho One を構成するには、Zoho One サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Zoho One を追加する

Microsoft Entra アプリケーション ギャラリーから Zoho One を追加して、Zoho One へのプロビジョニングの管理を開始します。 SSO 用に Zoho One を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Zoho One への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Zoho One の自動ユーザー プロビジョニングを構成するには:

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [Zoho One 選択します。

    [Image: アプリケーションの一覧の Zoho One リンクのスクリーンショット。]
4. **[プロビジョニング] タブ** を選択します。

    [プロビジョニング] タブの [Image: スクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 自動プロビジョニング タブのスクリーンショット。]
6. [**管理者資格情報の**] セクションで、Zoho One テナント URL、承認エンドポイント、トークンエンドポイントを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Zoho One に接続できることを確認します。 接続に失敗した場合は、Zoho One アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: トークンのスクリーンショット。]

    手記

    - Zoho One のテナント URL、承認エンドポイント、トークン エンドポイントはすべてリージョン固有です。 入力する際は注意してください。
    - **承認エンドポイント** には、常に値を入力するときに `?access_type=offline&prompt=consent&response_type=code&state=&client_id=1000.T3YYZHB8J5Y2BQ185U2FWOIKREUWAH&scope=ZohoOne.SCIM.ALL&redirect_uri=https%3A%2f%2fportal.azure.com%2fTokenAuthorize` を追加する必要があります (たとえば、リージョンが米国の場合は、入力する承認エンドポイントを `https://accounts.zoho.com/oauth/v2/auth?access_type=offline&prompt=consent&response_type=code&state=&client_id=1000.T3YYZHB8J5Y2BQ185U2FWOIKREUWAH&scope=ZohoOne.SCIM.ALL&redirect_uri=https%3A%2f%2fportal.azure.com%2fTokenAuthorize`する必要があります)。
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **属性マッピング** セクションで、Microsoft Entra ID から Zoho One に同期されるユーザー属性を確認します。 Zoho One のユーザー アカウントを更新操作で照合するために、"一致する属性**" として選択された** プロパティが使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Zoho One API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[** 保存] ボタンを選択して、変更をコミットします。

    | 属性 | タイプ | フィルター処理でサポートされます | Zoho One で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | アクティブ | ブール値 |  |  |
    | 表示名 | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | 名前.名 | 糸 |  |  |
    | 名前.姓 | 糸 |  |  |
    | 名前.整形済み | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | phoneNumbers[type eq "ファックス"].value | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | エクスターナルID | 糸 |  |  |
12. **[グループ]** を選びます。
13. **属性マッピング** セクションで、Microsoft Entra ID から Zoho One に同期されるグループ属性を確認します。 **照合のために選択された** プロパティとしての属性は、更新操作において Zoho One のグループを照合するために使用されます。 **[** 保存] ボタンを選択して、変更をコミットします。

    | 属性 | タイプ | フィルター処理でサポートされます | Zoho One で必須 |
    | --- | --- | --- | --- |
    | 表示名 | 糸 | ✓ | ✓ |
    | メンバー | 参考 |  |  |
    | エクスターナルID | 糸 |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バーの](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zohoone-tutorial"} -->
## Microsoft Entra ID で Zoho One をシングルサインオンに設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zohoone-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zoho One の間にシングル サインオンを構成する方法について説明します。

この記事では、Zoho One と Microsoft Entra ID を統合する方法について説明します。 Zoho One を Microsoft Entra ID と統合すると、次のことができます。

- Zoho One にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zoho One に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zoho One でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Zoho One では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Zoho One の追加

Microsoft Entra ID への Zoho One の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Zoho One を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zoho One**」と入力します。
4. 結果のパネルから **[Zoho One]** を選択し、そのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zoho One 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Zoho One に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Zoho One の関連ユーザーとの間にリンク関係を確立する必要があります。

Zoho One に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zoho One SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zoho One テストユーザーを作成** - Microsoft Entra の B.Simon にリンクされた、Zoho One での B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**Zoho One**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、値を入力します。 `one.zoho.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://accounts.zoho.com/samlresponse/<saml-identifier>`

    注

    上記の **応答 URL** 値は実際の値ではありません。 `<saml-identifier>`値は、「**Zoho One シングル サインオンの構成**」セクションの #step4 から取得します。これについては、後で説明します。

    c. [ **追加の URL の設定] を選択します**。

    d. [ **リレー状態** ] テキスト ボックスに、URL を入力します。 `https://one.zoho.com`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://accounts.zoho.com/samlauthrequest/<domain_name>?serviceurl=https://one.zoho.com`

    注

    上記の **サインオン URL** 値は実際の値ではありません。 この値は、「 **Zoho One シングル サインオンの構成** 」セクションの実際の Sign-On URL で更新します。これについては、この記事の後半で説明します。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Set up Zoho One](Zoho One のセットアップ)** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zoho One SSO の構成

1. 別の Web ブラウザー ウィンドウで、Zoho One 企業サイトに管理者としてサインインします。
2. [**組織**] タブの [**SAML 認証**] で [**セットアップ**] を選択します。

    [Image: Zoho One、組織]
3. ポップアップ ページで、次の手順に従います。

    [Image: Zoho One、sig]

    ある。 **[サインイン URL]** ボックスに **[ログイン URL]** の値を貼り付けます。

    b。 **[サインアウト URL]** ボックスに **[ログアウト URL]** の値を貼り付けます。

    c. [ **参照] を** 選択して、以前にダウンロードした **証明書 (Base64)** をアップロードします。

    d. **保存** を選択します。
4. SAML 認証設定を保存した後、**[SAML-Identifier](SAML 識別子)** の値をコピーし、 を置き換える形で`<saml-identifier>` に追加します (例: `https://accounts.zoho.com/samlresponse/one.zoho.com`)。こうして得られた値を **[基本的な SAML 構成]** セクションの **[応答 URL]** ボックスに貼り付けてください。

    [Image: Zoho One、SAML]
5. [ **ドメイン** ] タブに移動し、[ **ドメインの追加**] を選択します。

    [Image: Zoho One、ドメイン]
6. **[Add Domain](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ドメインの追加)** ページで、次の手順に従います。

    [Image: Zoho One、ドメインの追加]

    ある。 **[Domain Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ドメイン名)** ボックスに、ドメインを入力します (例: contoso.com)。

    b。 [**] を選択し、[**] を追加します。

    注

    ドメインに追加した後、[これら](https://www.zoho.com/one/help/admin-guide/domain-verification.html)の手順に従って、ドメインを確認します。 ドメインを確認したら、Azure portal の **[基本的な SAML 構成]** セクションの **[サインオン URL]** でこのドメイン名を使用します。

#### Zoho One テスト ユーザーの作成

Microsoft Entra ユーザーが Zoho One にサインインできるようにするには、そのユーザーを Zoho One にプロビジョニングする必要があります。 Zoho One では、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. セキュリティ管理者として Zoho One にサインインします。
2. [ **ユーザー** ] タブで、 **ユーザー ロゴを**選択します。

    [Image: Zoho One、ユーザー]
3. **[Add User]** ページで、次の手順に従います。

    [Image: Zoho One、ユーザーの追加]

    ある。 **[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前)** ボックスに、ユーザーの氏名を入力します (例: **Britta Simon**)。

    b。 **[Email Address](メール アドレス)** ボックスに、ユーザーのメール アドレスを入力します (例: brittasimon@contoso.com)。

    注

    ドメインの一覧から、確認済みドメインを選択します。

    c. [**] を選択し、[**] を追加します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Zoho One のサインオン URL にリダイレクトされます。
- Zoho One のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Zoho One に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Zoho One タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Zoho One に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zola-tutorial"} -->
## Microsoft Entra ID で Zola for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zola-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zola 間にシングル サインオンを構成する方法について説明します。

この記事では、Zola と Microsoft Entra ID を統合する方法について説明します。 Zola と Microsoft Entra ID を統合すると、次のことができます:

- Zola にアクセスできるユーザー Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Zola に自動的にサインインできるように設定できます。
- 1 つの場所でアカウントを管理します。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Zola でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zola では、**SP** Initiated SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Zola を追加する

Microsoft Entra ID への Zola の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Zola を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zola**」と入力します。
4. 結果のパネルから **[Zola]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Zola 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zola に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Zola の関連ユーザーとの間にリンク関係を確立する必要があります。

Zola に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zola SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zola テストユーザーを作成** - B.Simon に対応するユーザーを Zola で作成し、Microsoft Entra にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Zola**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[応答 URL]** ボックスに、URL として「`https://zola-prod.auth.eu-west-3.amazoncognito.com/saml2/idpresponse`」と入力します。

    b。 **[サインオン URL]** ボックスに、Zola から提供された URL (`https://app.zola.fr/?company=<MYCOMPANYID>`) を入力します。

    c. **[リレー状態]** ボックスに、URL `https://app.zola.fr/dashboard` を入力します。

    注意

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新する必要があります。 この値を取得するには、[Zola サポート チーム](mailto:tech@zola.fr)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Zola のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成 URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zola SSO の構成

**Zola** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Zola サポート チーム](mailto:tech@zola.fr)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Zola テスト ユーザーの作成

このセクションでは、Zola で Britta Simon というユーザーを作成します。 [Zola サポート チーム](mailto:tech@zola.fr)と連携して、Zola プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Zola サインオン URL にリダイレクトされます。
- Zola のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Zola] タイルを選択すると、このオプションは Zola のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zonka-feedback-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zonka Feedback を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zonka-feedback-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zonka Feedback の間でシングル サインオンを構成する方法について説明します。

この記事では、Zonka Feedback と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Zonka Feedback を統合すると、次のことができます。

- Zonka フィードバックにアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zonka Feedback に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zonka Feedback でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zonka Feedback では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Zonka フィードバックの追加

Microsoft Entra ID への Zonka Feedback の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zonka Feedback を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Zonka Feedback**」と入力します。
4. 結果パネルから **Zonka フィードバック** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zonka フィードバックの Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zonka Feedback に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Zonka Feedback の関連ユーザーとの間にリンク関係を確立する必要があります。

Zonka Feedback に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zonka Feedback の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zonka Feedback のテストユーザーを作成します** - Zonka Feedback で B.Simon に対応するユーザーを設定し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zonka Feedback**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `zonkafeedback`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://us1.zonkafeedback.com/api/v1/sso/saml` |
    | `https://e.zonkafeedback.com/api/v1/sso/saml` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://us1.zonkafeedback.com/api/v1/sso/saml` |
    | `https://e.zonkafeedback.com/api/v1/sso/saml` |
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. [ **Zonka フィードバックのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zonka Feedback SSO の構成

1. Zonka フィードバック企業サイトに管理者としてログインします。
2. **[設定] (歯車アイコン)**&gt;**Account**&gt;に移動し、[**SSO**] を選択します。
3. [ **シングル サインオン** ] ページで、次の手順を実行します。

    [Image: 構成の設定を示すスクリーンショット。]

    1. [ **SSO プロバイダー** ] セクションで、[ **Microsoft Entra ID** ] ラジオ ボタンを選択します。
    2. **[SAML SSO URL**] ボックスに、Microsoft Entra 管理センターからコピーした**ログイン URL を**貼り付けます。
    3. [**Identity Provider Issuer]\(ID プロバイダー発行者**\) ボックスに、Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションからコピーした**識別子 (エンティティ ID)** の値を貼り付けます。
    4. ダウンロードした **証明書 (Base64)** をメモ帳に開き、[ **パブリック証明書** ] ボックスに内容を貼り付けます。
    5. **保存** を選択します。

#### Zonka Feedback テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、管理者として Zonka フィードバック Web サイトにサインインします。
2. **[設定]**&gt;**[ユーザー**&gt;**すべてのユーザー**] に移動し、[**ユーザーの追加]** を選択します。

    [Image: スクリーンショットは、アプリケーションでユーザーを作成する方法を示しています。]
3. [ **新しいユーザーの招待** ] セクションで、次の手順を実行します。

    [Image: ページで新しいユーザーを作成する方法を示すスクリーンショット。]

    1. テキスト ボックスに有効なメール アドレスを入力します。
    2. [ **招待**] を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Zonka Feedback のサインオン URL にリダイレクトします。
- Zonka Feedback のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Zonka フィードバック] タイルを選択すると、このオプションは Zonka Feedback のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zoom-for-government-tutorial"} -->
## Microsoft Entra ID で Zoom for Government for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zoom-for-government-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-17
- Summary: Microsoft Entra ID と Zoom for Government の間にシングル サインオンを構成する方法について説明します。

この記事では、Zoom for Government と Microsoft Entra ID を統合する方法について説明します。 Zoom for Government と Microsoft Entra ID を統合すると、次のことができます。

- Zoom for Government にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zoom for Government に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Zoom for Government のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zoom for Government では、**SP** initiated SSO のみがサポートされます。
- Zoom for Government では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Zoom for Government を追加する

Microsoft Entra ID への Zoom for Government の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Zoom for Government を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zoom for Government**」と入力します。
4. 結果パネルから **[Zoom for Government]** を選び、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zoom for Government 用に Microsoft Entra SSO を構成してテストする

**B.Simon** という名前のテスト ユーザーを使用して、Zoom for Government に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Zoom for Government の関連ユーザーとの間にリンク関係を確立する必要があります。

Zoom for Government に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zoom for Government SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zoom for Government のテスト ユーザーの作成** - Zoom for Government で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Zoom for Government**&gt;**シングル サインオン** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<CustomerName>.zoomgov.com`

    b。 **[応答 URL]** ボックスに、`https://<CustomerName>.zoomgov.com` のパターンを使用して URL を入力します

    c. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<CustomerName>.zoomgov.com`

    d. **[ログアウト URL]** テキスト ボックスに、`https://<CustomerName>.zoomgov.com` のパターンを使用して URL を入力します。

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL、およびログアウト URL で更新してください。 これらの値を取得するには、[Zoom for Government のサポート チーム](mailto:support@zoomgov.com)にお問い合わせください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Zoom for Government アプリケーションでは、特定の形式の SAML アサーションが使用されるため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性構成の画像を示すスクリーンショット。]
7. その他に、Zoom for Government アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | User.mail |
    | 電話 | ユーザー.電話番号 |
    | 部署 | ユーザーの部署 |
    | ロール | user.assignedroles |
    | グループ | ユーザー.グループ |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (未加工)]** を見つけます。**[ダウンロード]** を選択して証明書をダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Zoom for Government のセットアップ]** セクションで、要件に基づいて該当の URL をコピーします。

    [Image: 構成の URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zoom for Government SSO を構成する

**Zoom for Government** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** と、Microsoft Entra 管理センターからコピーした適切な URL を [Zoom for Government サポート チーム](mailto:support@zoomgov.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Zoom for Government のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Zoom for Government に作成します。 Zoom for Government では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Zoom for Government にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Zoom for Government のサインオン URL にリダイレクトされます。
- Zoom for Government のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [政府向けズーム] タイルを選択すると、このオプションは Zoom for Government のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zoom-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して自動ユーザー プロビジョニング用に Zoom を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zoom-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID から Zoom に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Zoom ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成の済んだ Microsoft Entra ID では、[Zoom](https://zoom.us/pricing/) に対するユーザーのプロビジョニングとプロビジョニング解除が、Microsoft Entra プロビジョニング サービスによって自動的に行われます。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- Zoom でユーザーを作成する
- アクセスが不要になった場合に Zoom でユーザーを削除する
- Microsoft Entra ID と Zoom の間でユーザー属性の同期を維持する
- Zoom への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zoom-tutorial) (推奨)

Zoom は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ |  | ✅ |

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- プロビジョニングを構成するための[アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を持つ Microsoft Entra ID のユーザー アカウント ([アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- [Zoom のテナント](https://zoom.us/pricing)。
- 管理者のアクセス許可がある Zoom のユーザー アカウント。

### 手順1: プロビジョニング デプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Zoom の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra アプリケーション ギャラリーから Zoom を追加する

Microsoft Entra アプリケーション ギャラリーから Zoom を追加して、Zoom へのプロビジョニングの管理を開始します。 SSO のために Zoom を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 3: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 4: Zoom への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーの割り当てに基づいて、TestApp でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Zoom in Microsoft Entra ID の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。

    [Image: エンタープライズ アプリケーション ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Zoom]** を選択します。

    [Image: アプリケーション リストの Zoom リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. **[管理者資格情報]** セクションにある **[OAuth2 Authorization Code Grant] (OAuth2 承認コードの付与)** を選択します。 `https://api.zoom.us/scim` に「」と入力し、[**承認]** を選択し、Zoom アカウントの管理者資格情報を入力していることを確認します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Zoom に接続できることを確認します。 接続できない場合は、使用中の Zoom アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: Zoom プロビジョニング トークンのスクリーンショット。]

    注意

    認証方法には、 **ベアラー認証** と **OAuth2 承認コード付与の 2** つのオプションがあります。 [OAuth2 認可コードの付与] を選択していることを確認します。 Zoom は **ベアラー認証**方法をサポートしなくなりました
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Zoom に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Zoom のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Zoom API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Zoom で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | emails[type eq "work"] | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | ユーザータイプ | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

- 現在、Zoom で許可されているベーシック ユーザーの数は 9,999 人までです。

### 更新履歴

- 2020 年 5 月 14日 - emails[type eq "work"] 属性に UPDATE 操作のサポートが追加されました。
- 2020 年 10 月 20 日 - 既存の**ロール Pro** と **Corp** を置き換えるために、**ライセンスと** **オンプレミス**の 2 つの新しいロールのサポートを追加しました。**Pro** と **Corp の**役割のサポートは今後削除されます。
- 2023 年 5 月 30 日 - 新しい認証方法 (**OAuth 2.0**) のサポートが追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zoom-tutorial"} -->
## Microsoft Entra ID で Zoom for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zoom-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Zoom の間でシングル サインオンを構成する方法について説明します。

この記事では、Zoom と Microsoft Entra ID を統合する方法について説明します。 Zoom と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Zoom へのアクセス権を持つ人を制御します。
- ユーザーが Microsoft Entra アカウントを使用して Zoom に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

Zoom は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ |  | ✅ |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zoom でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zoom では、**SP** によって開始される SSO をサポートしています。
- Zoom では、 [**自動** ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zoom-provisioning-tutorial)。

### ギャラリーからの Zoom の追加

Microsoft Entra ID への Zoom の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zoom を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Zoom**」と入力します。
4. 結果パネルから **[ズーム]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zoom の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zoom に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Zoom の関連ユーザーとの間にリンク関係を確立する必要があります。

Zoom で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zoom SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Zoom テスト ユーザーの作成** - Microsoft Entra の B.Simon にリンクさせるために、対応するユーザーを Zoom で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zoom** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集のスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `<companyname>.zoom.us`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.zoom.us/saml/SSO`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.zoom.us`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [Zoom クライアント サポート チーム](https://support.zoom.us/hc/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
7. [ **Zoom のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: [構成 URL のコピー] のスクリーンショット。]

注

Microsoft Entra ID でロールを構成する方法については、「 [エンタープライズ アプリケーションの SAML トークンで発行されたロール要求を構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/enterprise-app-role-management)」を参照してください。

注

Zoom では、SAML ペイロードにグループ要求が必要な場合があります。 グループを作成した場合は、グループ情報を [Zoom クライアント サポート チーム](https://support.zoom.us/hc/) に連絡して、グループ情報を構成できるようにします。 また、オブジェクト ID を [Zoom クライアント サポート チーム](https://support.zoom.us/hc/) に提供して、最後にオブジェクト ID を構成できるようにする必要もあります。 オブジェクト ID を取得するには、「 [Azure での Zoom の構成」を](https://support.zoom.us/hc/articles/115005887566)参照してください。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zoom SSO の構成

1. Zoom 内での構成を自動化するには、[拡張機能のインストール] を選択して **マイ アプリ Secure Sign-in ブラウザー拡張機能** を **インストールする必要があります**。

    [Image: マイ アプリ拡張機能のスクリーンショット。]
2. 拡張機能をブラウザーに追加した後、[ **Zoom のセットアップ** ] を選択すると、Zoom アプリケーションに移動します。 そこから、Zoom にサインインするための管理者資格情報を指定します。 ブラウザー拡張機能によってアプリケーションが自動的に構成され、手順 3 から 6 が自動化されます。

    [Image: セットアップ構成のスクリーンショット。]
3. Zoom を手動で設定する場合は、別の Web ブラウザー ウィンドウで、Zoom 企業サイトに管理者としてサインインします。
4. [ **シングル サインオン** ] タブを選択します。

    [Image: [シングル サインオン] タブのスクリーンショット。]
5. [ **セキュリティ制御** ] タブを選択し、[ **シングル サインオンの設定] に** 移動します。
6. [Single Sign-On] セクションで、次の手順に従います。

    [Image: [シングル サインオン] セクションのスクリーンショット。]

    a. [ **サインイン ページ URL** ] ボックスに、 **ログイン URL** の値を貼り付けます。

    b。 **サインアウト ページの URL** 値については、Microsoft Entra 管理センターで、**Entra ID**&gt;**アプリの登録**&gt;**Endpoints** に移動します。

    [Image: [エンドポイント] ボタンのスクリーンショット。]

    d. **SAML-P SIGN-OUT ENDPOINT** をコピーし、[**サインアウト ページの URL**] ボックスに貼り付けます。

    [Image: [終了ポイントのコピー] ボタンのスクリーンショット。]

    e. base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、 **ID プロバイダー証明書** のテキスト ボックスに貼り付けます。

    f. **[発行者**] ボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    g. **バインド**として **HTTP リダイレクト**を選択し、**署名ハッシュ アルゴリズム**として **SHA-256** を選択します。

    h. [ **変更の保存] を選択します**。

    注

    詳細については、ズームのドキュメントを [参照してください](https://zoomus.zendesk.com/hc/articles/115005887566)。

#### Zoom テスト ユーザーの作成

このセクションの目的は、Zoom で B.Simon というユーザーを作成することです。 Zoom では、既定で有効になっている自動ユーザー プロビジョニングがサポートされています。 自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zoom-provisioning-tutorial) 。

注

ユーザーを手動で作成する必要がある場合は、[Zoom クライアント サポート チーム](https://support.zoom.us/hc/)にお問い合わせください

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Zoom サインオン URL にリダイレクトされます。
- Zoom のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ズーム] タイルを選択すると、このオプションは Zoom のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-b2b-user-portal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zscaler B2B ユーザー ポータルを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-b2b-user-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Zscaler B2B User Portal 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、Zscaler B2B ユーザー ポータルと Microsoft Entra ID を統合する方法について説明します。 Zscaler B2B User Portal を Microsoft Entra ID を統合すると、次のことができます:

- Zscaler B2B User Portal にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zscaler B2B User Portal に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Zscaler B2B ユーザー ポータルは、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- Zscaler B2B ユーザー ポータルでのシングル サインオン (SSO) が有効なサブスクリプション。

注

この統合は、Microsoft Entra 米国政府クラウド環境から利用することもできます。 このアプリケーションは、Microsoft Entra 米国政府クラウドのアプリケーション ギャラリーにあり、パブリック クラウドの場合と同じように構成できます。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zscaler B2B ユーザー ポータルでは、**IDP** Initiated SSO がサポートされます。
- Zscaler B2B ユーザー ポータルでは、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Zscaler B2B ユーザー ポータルの追加

Microsoft Entra ID への Zscaler B2B ユーザー ポータルの統合を構成するには、管理対象 SaaS アプリのリストに Zscaler B2B ユーザー ポータルをギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zscaler B2B ユーザー ポータル**」と入力します。
4. 結果のパネルから **[Zscaler B2B ユーザー ポータル]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zscaler B2B User Portal 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zscaler B2B ユーザー ポータルに対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Zscaler B2B User Portal の関連ユーザーとの間にリンク関係を確立する必要があります。

Zscaler B2B ユーザー ポータルに対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zscaler B2B ユーザー ポータル SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zscaler B2B ユーザー ポータルのテストユーザーを作成** - Zscaler B2B ユーザー ポータルで B.Simon に対応するユーザーを作成し、これを Microsoft Entra におけるユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Zscaler B2B ユーザー ポータル]**&gt;**[シングルサインオン]**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次の手順に従います。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://samlsp.private.zscaler.com/auth/metadata/<UniqueID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://samlsp.private.zscaler.com/auth/login?domain=EXAMPLE`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Zscaler B2B ユーザー ポータル クライアント サポート チーム](https://help.zscaler.com/)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Zscaler B2B ユーザー ポータルのセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zscaler B2B ユーザー ポータル SSO の構成

1. 新しい Web ブラウザー ウィンドウを開き、Zscaler B2B ユーザー ポータルの企業サイトに管理者としてサインインして、次の手順を実行します。
2. メニューの左側から[ **管理** ]を選択し、[ **認証** ]セクションに移動して **[IdP 構成]**を選択します。

    [Image: Zscaler Private Access Administrator の管理]
3. 右上隅にある [ **IdP 構成の追加]** を選択します。

    [Image: Zscaler Private Access Administrator の IdP]
4. **[Add IdP Configuration]** ページで、次の手順に従います。

    [Image: Zscaler Private Access Administrator の選択]

    a. [ **ファイルの選択] を選択** して、[IdP メタデータ ファイルのアップロード] フィールドに Microsoft Entra ID からダウンロードした **メタデータ ファイルをアップロード** します。

    b。 Microsoft Entra ID から **IdP メタデータ** が読み取られ、以下に示すようにすべてのフィールド情報が設定されます。

    [Image: Zscaler Private Access Administrator での構成]

    c. **[Domains]** フィールドから自分のドメインを選択します。

    d. **保存** を選択します。

#### Zscaler B2B ユーザー ポータルのテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Zscaler B2B ユーザー ポータルに作成します。 Zscaler B2B ユーザー ポータルでは、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Zscaler B2B ユーザー ポータルにユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Zscaler B2B ユーザー ポータルに自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで Zscaler B2B ユーザー ポータル タイルを選択すると、SSO を設定した Zscaler B2B ユーザー ポータルに自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-beta-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Zscaler Beta を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-beta-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: FZscaler Beta に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、Zscaler Beta と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Zscaler Beta に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

Zscaler Beta は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ |  | ✅ |

### 前提条件

この記事で説明するシナリオでは、次のものが既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zscaler Beta テナント
- 管理者アクセス許可がある Zscaler Beta のユーザー アカウント

注

Microsoft Entra プロビジョニング統合では、Enterprise パッケージを含むアカウントについて Zscaler Beta 開発者が使用できる Zscaler Beta SCIM API が必要です。

### 手順 1: ギャラリーから Zscaler Beta を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に Zscaler Beta を構成する前に、Zscaler Beta を Microsoft Entra アプリケーション ギャラリーから管理対象の SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Zscaler Beta を追加するには、次の手順を行います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Zscaler Beta」**と入力し、結果パネルで **Zscaler Beta** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Zscaler Beta のスクリーンショット。]

### 手順 2: Zscaler Beta にユーザーを割り当てる

Microsoft Entra ID では、選択されたアプリへのアクセスを付与するユーザーを決定する際に "割り当て" という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに "割り当て済み" のユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Zscaler Beta へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Zscaler Beta に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Zscaler Beta に割り当てるときの重要なヒント

- 1 人の Microsoft Entra ユーザーを Zscaler Beta に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Zscaler Beta にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### 手順 3: Zscaler Beta への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Zscaler Beta でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Zscaler Beta のシングル サインオンに関する記事で説明されている手順に従って、 [Zscaler Beta](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-beta-tutorial) に対して SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

注

ユーザーとグループをプロビジョニングしたりプロビジョニング解除したりする際は、グループ メンバーシップが適切に更新されるよう、定期的にプロビジョニングをやり直すことをお勧めします。 再起動を行うことで、当サービスはすべてのグループを再評価し、メンバーシップを更新します。

#### Microsoft Entra ID で Zscaler Beta の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Zscaler Beta** にアクセスします。

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で、 **[Zscaler Beta]** を選択します。

    [Image: アプリケーションの一覧の Zscaler Beta リンクを示すスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [管理] カテゴリの [プロビジョニング] タブが選択されていることを示すスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、この記事で後述するように、Zscaler Beta アカウントの **テナント URL** と **シークレット トークン** を入力します。
7. **テナント URL** と**シークレット トークン**を取得するには、Zscaler Beta ポータルのユーザー インターフェイスで**管理&gt;認証設定**に移動し、[**認証の種類**] で **[SAML**] を選択します。

    [Image: [認証設定] を示すスクリーンショット。]

    **SAML を構成する** を選択して **SAML 構成** オプションを開きます。

    [Image: [SAML の構成時] を示すスクリーンショット。]

    **[Enable SCIM-Based Provisioning](SCIM ベースのプロビジョニングを有効にする)** を選択して、**ベース URL** と**ベアラー トークン**を取得し、設定を保存します。 **ベース URL** を**テナント URL** にコピーし、**ベアラー トークン**を**シークレット トークン**にコピーします。
8. 手順 5 に示すフィールドに入力したら、[ **テスト接続** ] を選択して、Microsoft Entra ID が Zscaler Beta に接続できることを確認します。 接続できない場合は、使用中の Zscaler Beta アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: トークンのスクリーンショット。]
9. [ **作成]** を選択して構成を作成します。
10. [**概要**] ページで **[プロパティ**] を選択します。
11. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
12. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Zscaler Beta に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Zscaler Beta のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler Beta で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | displayName | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  | ✓ |
14. **[グループ] を選択します**。
15. **[属性マッピング]** セクションで、Microsoft Entra ID から Zscaler Beta に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Zscaler Beta のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler Beta で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
    | externalId | 糸 |  | ✓ |
16. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
17. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
18. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 4: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-beta-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zscaler Beta を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-beta-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Zscaler Beta 間にシングル サインオンを構成する方法について説明します。

この記事では、Zscaler Beta と Microsoft Entra ID を統合する方法について説明します。 Zscaler Beta を Microsoft Entra ID を統合すると、次のことができます:

- Zscaler Beta にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Azure AD アカウントを使用して Zscaler Beta に自動的にサインインできるようにする。 このアクセス制御はシングル サインオン (SSO) と呼ばれます。
- Azure portal を使用して 1 つの中央サイトでアカウントを管理する。

Zscaler Beta は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- シングル サインオンを使用する Zscaler Beta サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Zscaler Beta では、**SP** Initiated SSO がサポートされます。
- Zscaler Beta では、**Just In Time** ユーザー プロビジョニングがサポートされます。
- Zscaler Beta では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-beta-provisioning-tutorial)がサポートされます。

### ギャラリーからの Zscaler Beta の追加

Microsoft Entra への Zscaler Beta の統合を構成するには、管理対象の SaaS アプリ一覧に Zscaler Beta をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zscaler Beta**」と入力します。
4. 結果パネルから **[Zscaler Beta]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zscaler Beta 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zscaler Beta に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Zscaler Beta の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Zscaler Beta と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zscaler Beta の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zscaler Beta のテスト ユーザーの作成** - Zscaler Beta で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zscaler Beta** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    **[サインオン URL]** ボックスに、ユーザーが Zscaler Beta アプリケーションへのサインインに使用する URL を入力します。

    注

    これは実際の値ではありません。 実際のサインオン URL 値でこの値を更新します。 この値を取得するには、[Zscaler Beta クライアント サポート チーム](https://www.zscaler.com/company/contact)に問い合わせてください。
6. Zscaler Beta アプリケーションでは、特定の形式の SAML アサーションを予期しています。 カスタム属性マッピングを SAML トークン属性構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 **[編集]** を選択して **[ユーザー属性]** ダイアログ ボックスを開きます。

    [Image: [ユーザー属性] ダイアログ ボックス]
7. Zscaler Beta アプリケーションでは、いくつかの追加の属性が SAML 応答で返されることを予期しています。 **[ユーザー属性]** ダイアログの **[ユーザー要求]** セクションで、次の手順を実行して、以下の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | memberOf | user.assignedroles |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログ ボックスを開きます。

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **[名前空間**] ボックスは空白のままにします。

    d. **[ソース]** で **[属性]** を選択します。

    e. **[ソース属性]** の一覧から、その行に表示される属性値を入力します。

    f. **[OK] を選択**.

    g. **保存** を選択します。

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[ダウンロード]** を選択して**証明書 (Base64)** をダウンロードします。 それを自分のコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Set up Zscaler Beta](Zscaler Beta の設定)** セクションで、要件に従って必要な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zscaler Beta の SSO の構成

1. Zscaler Beta 内での構成を自動化するには、**[拡張機能のインストール]** を選択して **[アプリによるセキュリティで保護されたサインイン拡張機能]** をインストールします。

    [Image: マイ アプリの拡張機能]
2. ブラウザーに拡張機能を追加した後は、**[Zscaler Beta の設定]** を選択すると Zscaler Beta アプリケーションが表示されます。 そこから、管理者資格情報を入力して Zscaler Beta にサインインします。 ブラウザー拡張機能によりアプリケーションが自動的に構成され、手順 3 から 6 が自動化されます。

    [Image: セットアップの構成]
3. Zscaler Beta を手動で設定するには、新しい Web ブラウザー ウィンドウを開きます。 Zscaler Beta の会社サイトに管理者としてサインインし、次の手順を実行します。
4. **[Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理)**&gt;**[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)**&gt;**[Authentication Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証設定)** の順に選択し、次の手順を実行します。

    [Image: 管理]

    a. [ **認証の種類] で** 、[SAML] を選択 **します**。

    b。 [ **SAML の構成] を選択します**。
5. [c0]SAML の編集[/c0] ウィンドウで、次の手順に従います: [c1][sb1]ユーザーと認証の管理[/sb1][sb1]ユーザーと認証の管理[/sb1][/c1]

    a. **[SAML ポータル URL]** ボックスに、コピーした**ログイン URL** を貼り付けます。

    b。 **[Login Name Attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ログイン名属性)** ボックスに **NameID** の値を入力します。

    c. **[パブリック SSL 証明書]** ボックスで、**[アップロード]** を選択して、ダウンロードした Azure SAML 署名証明書をアップロードします。

    d. **[SAML 自動プロビジョニングを有効にする]** を切り替えます。

    e. displayName 属性で SAML 自動プロビジョニングを有効にするには、**[User Display Name Attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー表示名属性)** ボックスに **displayName** の値を入力します。

    f. memberOf 属性で SAML 自動プロビジョニングを有効にするには、**[Group Name Attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/グループ名属性)** ボックスに **memberOf** の値を入力します。

    g. department 属性で SAML 自動プロビジョニングを有効にするには、**[Department Name Attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/部署名属性)** ボックスに **department** の値を入力します。

    h. **保存** を選択します。
6. **[ユーザー認証の構成]** ダイアログ ページで、次の手順に従います。

    [Image: [アクティブ化] メニューと [アクティブ化] ボタン]

    a. 左下の **[Activation](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクティブ化)** メニューにポインターを置きます。

    b。 [**を選択し、**をアクティブ化します。]

### プロキシ設定の構成

Internet Explorer でプロキシ設定を構成するには、次の手順に従ってください。

1. **Internet Explorer を起動します**。
2. **[ツール]** メニューの **[インターネット オプション]** を選択し、**[インターネット オプション]** ダイアログ ボックスを開きます。

    [Image: [インターネット オプション] ダイアログ ボックス]
3. [ **接続** ] タブを選択します。

    [Image: [接続] タブ]
4. **[LAN の設定]** を選択して **[ローカル エリア ネットワーク (LAN) の設定]** ダイアログ ボックスを開きます。
5. **[プロキシ サーバー]** セクションで、次の手順に従います。

    [Image: [プロキシ サーバー] セクション]

    a. **[LAN にプロキシ サーバーを使用する]** チェック ボックスをオンにします。

    b。 **[アドレス]** ボックスに「**gateway.Zscaler Beta.net**」と入力します。

    c. **[ポート]** ボックスに「**80**」と入力します。

    d. [ **ローカル アドレスにはプロキシ サーバーを使用しない**] チェック ボックスをオンにします。

    e. **[OK]** を選択して **[ローカル エリア ネットワーク (LAN) の設定]** ダイアログ ボックスを閉じます。
6. **[OK]** を選択して **[インターネット オプション]** ダイアログ ボックスを閉じます。

#### Zscaler Beta テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Zscaler Beta に作成します。 Zscaler Beta では、**Just-In-Time ユーザー プロビジョニング**がサポートされています。この設定は既定で有効になっています。 このセクションでは行うことはありません。 Zscaler Beta にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成するには、[Zscaler Beta サポート チーム](https://www.zscaler.com/company/contact)にお問い合わせください。

注

Zscaler Beta では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-beta-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zscaler Beta のサインオン URL にリダイレクトされます。
- Zscaler Beta のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Zscaler Beta] タイルを選択すると、このオプションは Zscaler Beta のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-internet-access-administrator-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zscaler Internet Access Administrator を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-internet-access-administrator-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Zscaler Internet Access Administrator の間でシングル サインオンを構成する方法について説明します。

この記事では、Zscaler Internet Access Administrator と Microsoft Entra ID を統合する方法について説明します。 Zscaler Internet Access Administrator を Microsoft Entra ID と統合すると、次のことが可能になります。

- Zscaler Internet Access Administrator にアクセスするユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して、Zscaler Internet Access Administrator に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

Zscaler Internet Access Administrator は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- Zscaler Internet Access Administrator でのシングル サインオン (SSO) が有効なサブスクリプション。

注

この統合は、Microsoft Entra 米国政府クラウド環境から利用することもできます。 このアプリケーションは、Microsoft Entra 米国政府クラウドのアプリケーション ギャラリーにあり、パブリック クラウドの場合と同じように構成できます。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Zscaler Internet Access Administrator では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Zscaler Internet Access Administrator の追加

Microsoft Entra ID への Zscaler Internet Access Administrator の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zscaler Internet Access Administrator を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zscaler Internet Access Administrator**」と入力します。
4. 結果パネルから **[Zscaler Internet Access Administrator]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zscaler Internet Access Administrator の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zscaler Internet Access Administrator に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Zscaler Internet Access Administrator の関連ユーザーとの間にリンク関係を確立する必要があります。

Zscaler Internet Access Administrator に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zscaler Internet Access Administrator の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zscaler Internet Access Administrator のテスト ユーザーを作成** - Zscaler Internet Access Administrator で Britta Simon に対応するテストユーザーを作成し、このユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zscaler Internet Access Administrator** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. **[識別子]** テキスト ボックスに、要件に応じて次のいずれかの URL を入力します。

    | 識別子 |
    | --- |
    | `https://admin.zscaler.net` |
    | `https://admin.zscalerone.net` |
    | `https://admin.zscalertwo.net` |
    | `https://admin.zscalerthree.net` |
    | `https://admin.zscloud.net` |
    | `https://admin.zscalerbeta.net` |

    b。 **[応答 URL]** テキスト ボックスに、要件に応じて次のいずれかの URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://admin.zscaler.net/adminsso.do` |
    | `https://admin.zscalerone.net/adminsso.do` |
    | `https://admin.zscalertwo.net/adminsso.do` |
    | `https://admin.zscalerthree.net/adminsso.do` |
    | `https://admin.zscloud.net/adminsso.do` |
    | `https://admin.zscalerbeta.net/adminsso.do` |
6. Zscaler Internet Access Administrator アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 このアプリケーションに対して次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの **「ユーザー属性と要求** 」セクションから管理できます。 [ **SAML を使用した単一 Sign-On の設定] ページで**、[ **編集** ] ボタンを選択して [ **ユーザー属性と要求** ] ダイアログを開きます。

    [Image: 属性リンク]
7. **[ユーザー属性]** ダイアログの **[ユーザーの要求]** セクションで、上の図のように SAML トークン属性を構成し、次の手順を実行します。

    | 名前 | ソース属性 |
    | --- | --- |
    | 役割 | user.assignedroles |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    b。 **[ソース属性]** の一覧から、属性値を選択します。

    c. **OK** を選択します。

    d. **保存** を選択します。

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Zscaler Internet Access Administrator のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zscaler Internet Access Administrator の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Zscaler Internet Access の管理 UI にログインします。
2. **管理&gt;管理者管理**に移動し、次の手順に従って [保存] を選択します。

    [Image: [Administrator Management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者の管理) を示すスクリーンショット。S A M L 認証を有効にするオプション、S S L 証明書をアップロードするオプション、および発行者を指定するオプションが表示されています。]

    a. **[Enable SAML Authentication](SAML 認証を有効にする)** をオンにします。

    b。 [ **アップロード]** を選択して、Azure portal からダウンロードした Azure SAML 署名 **証明書をパブリック SSL 証明書**にアップロードします。

    c. セキュリティを強化するために、必要に応じて **[Issuer](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/発行者)** の詳細を追加して、SAML 応答の発行者を確認します。
3. 管理 UI で次の手順を実行します。

    [Image: 管理 U I を示すスクリーンショット。ここでは、次の手順を実行できます。]

    a. 左下の [ **アクティブ化** ] メニューにカーソルを合わせます。

    b。 [**を選択し、**をアクティブ化します。]

#### Zscaler Internet Access Administrator のテスト ユーザーの作成

このセクションの目的は、Zscaler Internet Access Administrator で Britta Simon というユーザーを作成することです。 Zscaler Internet Access では、Administrator SSO の Just-In-Time プロビジョニングはサポートされていません。 管理者アカウントを手動で作成する必要があります。 管理者アカウントを作成する手順については、Zscaler のドキュメントを参照してください。

https://help.zscaler.com/zia/adding-admins

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Zscaler Internet Access Administrator に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [Zscaler Internet Access Administrator] タイルを選択すると、SSO を設定した Zscaler Internet Access Administrator に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-internet-access-zscloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zscaler Internet Access ZSCloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-internet-access-zscloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Zscaler Internet Access ZSCloud の間でシングル サインオンを構成する方法について説明します。

この記事では、Zscaler Internet Access ZSCloud と Microsoft Entra ID を統合する方法について説明します。 Zscaler Internet Access ZSCloud と Microsoft Entra ID を統合すると、次のことができます。

- Zscaler Internet Access ZSCloud にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zscaler Internet Access ZSCloud に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

Zscaler Internet Access ZSCloud は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ |  | ✅ |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zscaler Internet Access ZSCloud でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Zscaler Internet Access ZSCloud では、 **SP** Initiated SSO がサポートされます。
- Zscaler Internet Access ZSCloud では、 **Just In Time** ユーザー プロビジョニングがサポートされます。
- Zscaler Internet Access ZSCloud では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-zscloud-provisioning-tutorial)。

### ギャラリーからの Zscaler Internet Access ZSCloud の追加

Microsoft Entra ID への Zscaler Internet Access ZSCloud の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zscaler Internet Access ZSCloud を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Zscaler Internet Access ZSCloud**」と入力します。
4. 結果パネルから **Zscaler Internet Access ZSCloud** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zscaler Internet Access ZSCloud の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zscaler Internet Access ZSCloud に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Zscaler Internet Access ZSCloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Zscaler Internet Access ZSCloud に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zscaler Internet Access ZSCloud SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zscaler Internet Access ZSCloud のテストユーザーを作成します** - Zscaler Internet Access ZSCloud に、Microsoft Entra 上のユーザーの表現にリンクされた B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Zscaler Internet Access ZSCloud]**&gt;**[シングル サインオン]**に移動してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] ボックスに、Zscaler Internet Access ZSCloud アプリケーションへのサインオンにユーザーが使用する URL を入力します。

    注

    この値は実際のサインオン URL で更新する必要があります。 この値を取得するには、 [Zscaler Internet Access ZSCloud クライアント サポート チーム](https://help.zscaler.com/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Zscaler Internet Access ZSCloud アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ **ユーザー属性] ダイアログを** 開きます。

    [Image: [編集] アイコンが選択されているユーザー属性を示すスクリーンショット。]
7. 上記に加えて、Zscaler Internet Access ZSCloud アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | memberOf | user.assignedroles |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [新しい要求の追加] オプションを含むユーザー要求を示すスクリーンショット。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. [ソース] を **[属性**] として選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **[保存] を選択します**。

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Zscaler Internet Access ZSCloud のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zscaler Internet Access ZSCloud SSO の構成

1. 別の Web ブラウザー ウィンドウで、Zscaler Internet Access ZSCloud 企業サイトに管理者としてサインインします
2. **管理&gt;認証&gt;認証設定**に移動し、次の手順を実行します。

    [Image: 指示された手順を示しているZscalerサイトのスクリーンショットです。]

    a. [認証の種類] で、[SAML] を選択 **します**。

    b。 [ **SAML の構成] を選択します**。
3. [ **SAML の編集]** ウィンドウで、次の手順を実行し、[保存] を選択します。

    [Image: ユーザーの管理 & 認証]

    a. **[SAML Portal URL**] ボックスに、**ログイン URL を**貼り付けます。

    b。 [ **ログイン名属性** ] ボックスに「 **NameID」と**入力します。

    c. [ **アップロード]** を選択して、Azure portal からダウンロードした Azure SAML 署名 **証明書をパブリック SSL 証明書**にアップロードします。

    d. **[SAML 自動プロビジョニングを有効にする] を**切り替えます。

    e. displayName 属性の SAML 自動プロビジョニングを有効にする場合は、[ **ユーザー表示名属性** ] ボックスに **「displayName** 」と入力します。

    f. memberOf 属性の SAML 自動プロビジョニングを有効にする場合は、[ **グループ名属性** ] ボックスに「 **memberOf** 」と入力します。

    g. SAML 自動プロビジョニングを部門属性に対して有効にする場合は、**部門名属性**に「**department**」と入力します。

    h. **[保存] を選択します**。
4. [ **ユーザー認証の構成** ] ダイアログ ページで、次の手順を実行します。

    [Image: スクリーンショットは、[アクティブ化] が選択された [ユーザー認証の構成] ダイアログ ボックスを示しています。]

    a. 左下の [ **アクティブ化** ] メニューにカーソルを合わせます。

    b。 **[アクティブ化] を選択します**。

### プロキシ設定の構成

#### Internet Explorer でプロキシ設定を構成するには

1. **Internet Explorer を起動します**。
2. [**ツール**] メニューから **[インターネット オプション**] を選択し**、[インターネット オプション]** ダイアログを開きます。

    [Image: インターネット オプション]
3. [ **接続** ] タブを選択します。

    [Image: つながり]
4. **[LAN 設定]** を選択して、[**LAN 設定]** ダイアログを開きます。
5. [プロキシ サーバー] セクションで、次の手順を実行します。

    [Image: プロキシ サーバー]

    a. [ **LAN にプロキシ サーバーを使用する] を選択します**。

    b。 [アドレス] ボックスに「ゲートウェイ」と入力します **。Zscaler ZSCloud.net**。

    c. [ポート] ボックスに「 **80**」と入力します。

    d. **[ローカル アドレスのプロキシ サーバーをバイパスする] を選択します**。

    e. [ **OK] を** 選択して、[ **ローカル エリア ネットワーク (LAN) の設定]** ダイアログを閉じます。
6. [ **OK] を** 選択して [ **インターネット オプション] ダイアログを** 閉じます。

#### Zscaler Internet Access ZSCloud テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Zscaler Internet Access ZSCloud に作成します。 Zscaler Internet Access ZSCloud では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Zscaler Internet Access ZSCloud にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Zscaler Internet Access ZSCloud サポート チーム](https://help.zscaler.com/)にお問い合わせください。

注

Zscaler Internet Access ZSCloud では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-zscloud-provisioning-tutorial) ください。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zscaler Internet Access ZSCloud のサインオン URL にリダイレクトされます。
- Zscaler Internet Access ZSCloud のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Zscaler Internet Access ZSCloud タイルを選択すると、このオプションは Zscaler Internet Access ZSCloud のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-internet-access-zsnet-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zscaler Internet Access ZSNet を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-internet-access-zsnet-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Zscaler Internet Access ZSNet の間でシングル サインオンを構成する方法について説明します。

この記事では、Zscaler Internet Access ZSNet と Microsoft Entra ID を統合する方法について説明します。 Zscaler Internet Access ZSNet と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で、Zscaler Internet Access ZSNet へのアクセス権を持つユーザーを管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zscaler Internet Access ZSNet に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

Zscaler Internet Access ZSNet は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Zscaler Internet Access ZSNet でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zscaler Internet Access ZSNet では、 **SP** Initiated SSO がサポートされます。
- Zscaler Internet Access ZSNet では、 **Just In Time** ユーザー プロビジョニングがサポートされます。
- Zscaler Internet Access ZSNet では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-provisioning-tutorial)。

### ギャラリーからの Zscaler Internet Access ZSNet の追加

Microsoft Entra ID への Zscaler Internet Access ZSNet の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zscaler Internet Access ZSNet を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Zscaler Internet Access ZSNet**」と入力します。
4. 結果パネルから **Zscaler Internet Access ZSNet** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zscaler Internet Access ZSNet の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zscaler Internet Access ZSNet に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Zscaler Internet Access ZSNet の関連ユーザーとの間にリンク関係を確立する必要があります。

Zscaler Internet Access ZSNet に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zscaler Internet Access ZSNet SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Zscaler Internet Access ZSNet のテストユーザーの作成** - Zscaler Internet Access ZSNet で B.Simon に対応するユーザーを作成し、それを Microsoft Entra ユーザーの表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zscaler Internet Access ZSNet** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.zscaler.net`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、 [Zscaler Internet Access ZSNet クライアント サポート チーム](https://www.zscaler.com/company/contact) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Zscaler Internet Access ZSNet アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ **ユーザー属性] ダイアログを** 開きます。

    [Image: スクリーンショットには、[編集] アイコンが選択された [ユーザー属性] が表示されます。]
7. さらに、Zscaler Internet Access ZSNet アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | memberOf | user.assignedroles |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **保存** を選択します。

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
8. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Zscaler Internet Access ZSNet のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zscaler Internet Access ZSNet SSO の構成

1. 別の Web ブラウザー ウィンドウで、Zscaler Internet Access ZSNet 企業サイトに管理者としてサインインします
2. **管理&gt;認証&gt;認証設定**に移動し、次の手順を実行します。

    [Image: Zscaler One サイトのスクリーンショット。説明されている手順が示されています。]

    a. [認証の種類] で、[SAML] を選択 **します**。

    b。 [ **SAML の構成] を選択します**。
3. [ **SAML の編集]** ウィンドウで、次の手順を実行し、[保存] を選択します。

    [Image: ユーザーの管理 & 認証]

    a. **[SAML Portal URL**] ボックスに、**ログイン URL を**貼り付けます。

    b。 [ **ログイン名属性** ] ボックスに「 **NameID」と**入力します。

    c. [ **アップロード]** を選択して、Azure portal からダウンロードした Azure SAML 署名 **証明書をパブリック SSL 証明書**にアップロードします。

    d. **[SAML 自動プロビジョニングを有効にする] を**切り替えます。

    e. displayName 属性の SAML 自動プロビジョニングを有効にする場合は、[ **ユーザー表示名属性** ] ボックスに **「displayName** 」と入力します。

    f. memberOf 属性の SAML 自動プロビジョニングを有効にする場合は、[ **グループ名属性** ] ボックスに「 **memberOf** 」と入力します。

    g. SAML 自動プロビジョニングを部門属性に対して有効にする場合は、**部門名属性**に「**department**」と入力します。

    h. **保存** を選択します。
4. [ **ユーザー認証の構成** ] ダイアログ ページで、次の手順を実行します。

    [Image: スクリーンショットは、[アクティブ化] が選択された [ユーザー認証の構成] ダイアログ ボックスを示しています。]

    a. 左下の [ **アクティブ化** ] メニューにカーソルを合わせます。

    b。 [**を選択し、**をアクティブ化します。]

### プロキシ設定の構成

#### Internet Explorer でプロキシ設定を構成するには

1. **Internet Explorer を起動します**。
2. [**ツール**] メニューから **[インターネット オプション**] を選択し**、[インターネット オプション]** ダイアログを開きます。

    [Image: インターネット オプション]
3. [ **接続** ] タブを選択します。

    [Image: つながり]
4. **[LAN 設定]** を選択して、[**LAN 設定]** ダイアログを開きます。
5. [プロキシ サーバー] セクションで、次の手順を実行します。

    [Image: プロキシ サーバー]

    a. [ **LAN にプロキシ サーバーを使用する] を選択します**。

    b。 [アドレス] ボックスに「**gateway.zscaler.net**」と入力します。

    c. [ポート] ボックスに「 **80**」と入力します。

    d. **[ローカル アドレスのプロキシ サーバーをバイパスする] を選択します**。

    e. [ **OK] を** 選択して、[ **ローカル エリア ネットワーク (LAN) の設定]** ダイアログを閉じます。
6. [ **OK] を** 選択して [ **インターネット オプション] ダイアログを** 閉じます。

#### Zscaler Internet Access ZSNet テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Zscaler Internet Access ZSNet に作成します。 Zscaler Internet Access ZSNet では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Zscaler Internet Access ZSNet にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Zscaler Internet Access ZSNet サポート チーム](https://www.zscaler.com/company/contact)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zscaler Internet Access ZSNet のサインオン URL にリダイレクトされます。
- Zscaler Internet Access ZSNet のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Zscaler Internet Access ZSNet タイルを選択すると、このオプションは Zscaler Internet Access ZSNet のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-internet-access-zsone-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zscaler Internet Access ZSOne を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-internet-access-zsone-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Zscaler Internet Access ZSOne の間でシングル サインオンを構成する方法について説明します。

この記事では、Zscaler Internet Access ZSOne と Microsoft Entra ID を統合する方法について説明します。 Zscaler Internet Access ZSOne と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で、Zscaler Internet Access ZSOne へのアクセス権を持つユーザーを管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zscaler Internet Access ZSOne に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

Zscaler Internet Access ZSOne は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ |  | ✅ |

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Zscaler Internet Access ZSOne でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Zscaler Internet Access ZSOne では、 **SP** Initiated SSO がサポートされます。
- Zscaler Internet Access ZSOne では、 **Just In Time** ユーザー プロビジョニングがサポートされます。
- Zscaler Internet Access ZSOne では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-one-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Zscaler Internet Access ZSOne を追加する

Microsoft Entra ID への Zscaler Internet Access ZSOne の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zscaler Internet Access ZSOne を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Zscaler Internet Access ZSOne**」と入力します。
4. 結果パネルから **Zscaler Internet Access ZSOne** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zscaler Internet Access ZSOne の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zscaler Internet Access ZSOne に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Zscaler Internet Access ZSOne の関連ユーザーとの間にリンク関係を確立する必要があります。

Zscaler Internet Access ZSOne に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zscaler Internet Access ZSOne SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Zscaler Internet Access ZSOne テスト ユーザーの作成** - Zscaler Internet Access ZSOne で Britta Simon に対応するユーザーを作成し、それを Microsoft Entra 上のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Zscaler Internet Access ZSOne]**&gt;**[シングル サインオン]**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] ボックスに、Zscaler Internet Access ZSOne アプリケーションへのサインオンにユーザーが使用する URL を入力します。

    注

    実際のサインオン URL でこの値を更新してください。 この値を取得するには、 [Zscaler Internet Access ZSOne クライアント サポート チーム](https://www.zscaler.com/company/contact) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Zscaler Internet Access ZSOne アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ **ユーザー属性] ダイアログを** 開きます。

    [Image: スクリーンショットには、[編集] アイコンが選択された [ユーザー属性] が表示されます。]
7. さらに、Zscaler Internet Access ZSOne アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | memberOf | user.assignedroles |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **保存** を選択します。

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Zscaler Internet Access ZSOne のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

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

このセクションでは、B.Simon に Zscaler Internet Access ZSOne へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Zscaler Internet Access ZSOne]** に移動します。
3. アプリの概要ページで、 **[管理]** セクションを見つけて、 **[ユーザーとグループ]** を選択します。
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. 上記で説明したようにロールを設定している場合は、**[ロールの選択]** ドロップダウンから選択できます。
7. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

#### Zscaler Internet Access ZSOne SSO の構成

1. 別の Web ブラウザー ウィンドウで、Zscaler Internet Access ZSOne 企業サイトに管理者としてサインインします
2. **管理&gt;認証&gt;認証設定**に移動し、次の手順を実行します。

    [Image: Zscaler One サイトのスクリーンショット。説明されている手順が示されています。]

    a. [認証の種類] で、[SAML] を選択 **します**。

    b。 [ **SAML の構成] を選択します**。
3. [ **SAML の編集]** ウィンドウで、次の手順を実行し、[保存] を選択します。

    [Image: ユーザーの管理 & 認証]

    a. **[SAML Portal URL**] ボックスに、**ログイン URL を**貼り付けます。

    b。 [ **ログイン名属性** ] ボックスに「 **NameID」と**入力します。

    c. [ **アップロード]** を選択して、Azure portal からダウンロードした Azure SAML 署名 **証明書をパブリック SSL 証明書**にアップロードします。

    d. **[SAML 自動プロビジョニングを有効にする] を**切り替えます。

    e. displayName 属性の SAML 自動プロビジョニングを有効にする場合は、[ **ユーザー表示名属性** ] ボックスに **「displayName** 」と入力します。

    f. memberOf 属性の SAML 自動プロビジョニングを有効にする場合は、[ **グループ名属性** ] ボックスに「 **memberOf** 」と入力します。

    g. SAML 自動プロビジョニングを部門属性に対して有効にする場合は、**部門名属性**に「**department**」と入力します。

    h. **保存** を選択します。
4. [ **ユーザー認証の構成** ] ダイアログ ページで、次の手順を実行します。

    [Image: スクリーンショットは、[アクティブ化] が選択された [ユーザー認証の構成] ダイアログ ボックスを示しています。]

    a. 左下付近の **[Activation](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクティブ化)** メニューにポインターを置きます。

    b。 [**を選択し、**をアクティブ化します。]

### プロキシ設定の構成

#### Internet Explorer でプロキシ設定を構成するには

1. **Internet Explorer を起動します**。
2. [**ツール**] メニューから **[インターネット オプション**] を選択し**、[インターネット オプション]** ダイアログを開きます。

    [Image: インターネット オプション]
3. [ **接続** ] タブを選択します。

    [Image: つながり]
4. **[LAN 設定]** を選択して、[**LAN 設定]** ダイアログを開きます。
5. [プロキシ サーバー] セクションで、次の手順を実行します。

    [Image: プロキシ サーバー]

    a. [ **LAN にプロキシ サーバーを使用する] を選択します**。

    b。 [アドレス] ボックスに「**gateway.Zscaler One.net**」と入力します。

    c. [ポート] ボックスに「 **80**」と入力します。

    d. **[ローカル アドレスのプロキシ サーバーをバイパスする] を選択します**。

    e. [ **OK] を** 選択して、[ **ローカル エリア ネットワーク (LAN) の設定]** ダイアログを閉じます。
6. [ **OK] を** 選択して [ **インターネット オプション] ダイアログを** 閉じます。

#### Zscaler Internet Access ZSOne テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Zscaler Internet Access ZSOne に作成します。 Zscaler Internet Access ZSOne では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Zscaler Internet Access ZSOne にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Zscaler Internet Access ZSOne サポート チーム](https://www.zscaler.com/company/contact)にお問い合わせください。

注

Zscaler Internet Access ZSOne では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-one-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zscaler Internet Access ZSOne サインオン URL にリダイレクトされます。
- Zscaler Internet Access ZSOne のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Zscaler Internet Access ZSOne] タイルを選択すると、このオプションは Zscaler Internet Access ZSOne のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-internet-access-zsthree-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zscaler Internet Access ZSThree を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-internet-access-zsthree-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Zscaler Internet Access ZSThree の間でシングル サインオンを構成する方法について説明します。

この記事では、Zscaler Internet Access ZSThree と Microsoft Entra ID を統合する方法について説明します。 Zscaler Internet Access ZSThree と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で、Zscaler Internet Access ZSThree へのアクセス権を持つユーザーを管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zscaler Internet Access ZSThree に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

Zscaler Internet Access ZSThree は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Zscaler Internet Access ZSThree でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zscaler Internet Access ZSThree では、 **SP** Initiated SSO がサポートされます。
- Zscaler Internet Access ZSThree では、 **Just In Time** ユーザー プロビジョニングがサポートされます。
- Zscaler Internet Access ZSThree では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-three-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Zscaler Internet Access ZSThree を追加する

Microsoft Entra ID への Zscaler Internet Access ZSThree の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zscaler Internet Access ZSThree を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Zscaler Internet Access ZSThree**」と入力します。
4. 結果パネルから **Zscaler Internet Access ZSThree** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zscaler Internet Access ZSThree の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zscaler Internet Access ZSThree に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Zscaler Internet Access ZSThree の関連ユーザーとの間にリンク関係を確立する必要があります。

Zscaler Internet Access ZSThree に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zscaler Internet Access ZSThree SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Zscaler Internet Access ZSThree テスト ユーザーの作成** - Zscaler Internet Access ZSThree において、Microsoft Entra のユーザー表明とリンクされた、B.Simon に対応する項目を持ちます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Zscaler Internet Access ZSThree]**&gt;**[シングル サインオン]**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://login.zscalerthree.net/sfc_sso`
6. Zscaler Internet Access ZSThree アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットには、[編集] アイコンが選択された [ユーザー属性] が表示されます。]
7. 上記に加えて、Zscaler Internet Access ZSThree アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | memberOf | user.assignedroles |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Zscaler Internet Access ZSThree のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zscaler Internet Access ZSThree SSO の構成

1. 別の Web ブラウザー ウィンドウで、Zscaler Internet Access ZSThree 企業サイトに管理者としてサインインします
2. **管理&gt;認証&gt;認証設定**に移動し、次の手順を実行します。

    [Image: Zscaler One サイトのスクリーンショット。説明されている手順が示されています。]

    a. [認証の種類] で、[SAML] を選択 **します**。

    b。 [ **SAML の構成] を選択します**。
3. [ **SAML の編集]** ウィンドウで、次の手順を実行し、[保存] を選択します。

    [Image: ユーザーの管理 & 認証]

    a. **[SAML Portal URL**] ボックスに、**ログイン URL を**貼り付けます。

    b。 [ **ログイン名属性** ] ボックスに「 **NameID」と**入力します。

    c. [ **アップロード]** を選択して、Azure portal からダウンロードした Azure SAML 署名 **証明書をパブリック SSL 証明書**にアップロードします。

    d. **[SAML 自動プロビジョニングを有効にする] を**切り替えます。

    e. displayName 属性の SAML 自動プロビジョニングを有効にする場合は、[ **ユーザー表示名属性** ] ボックスに **「displayName** 」と入力します。

    f. memberOf 属性の SAML 自動プロビジョニングを有効にする場合は、[ **グループ名属性** ] ボックスに「 **memberOf** 」と入力します。

    g. SAML 自動プロビジョニングを部門属性に対して有効にする場合は、**部門名属性**に「**department**」と入力します。

    h. **保存** を選択します。
4. [ **ユーザー認証の構成** ] ダイアログ ページで、次の手順を実行します。

    [Image: スクリーンショットは、[アクティブ化] が選択された [ユーザー認証の構成] ダイアログ ボックスを示しています。]

    a. 左下付近の **[Activation](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクティブ化)** メニューにポインターを置きます。

    b。 [**を選択し、**をアクティブ化します。]

### プロキシ設定の構成

#### Internet Explorer でプロキシ設定を構成するには

1. **Internet Explorer を起動します**。
2. [**ツール**] メニューから **[インターネット オプション**] を選択し**、[インターネット オプション]** ダイアログを開きます。

    [Image: インターネット オプション]
3. [ **接続** ] タブを選択します。

    [Image: つながり]
4. **[LAN 設定]** を選択して、[**LAN 設定]** ダイアログを開きます。
5. [プロキシ サーバー] セクションで、次の手順を実行します。

    [Image: プロキシ サーバー]

    a. [ **LAN にプロキシ サーバーを使用する] を選択します**。

    b。 [アドレス] テキスト ボックスに「**gateway.Zscaler Three.net**」と入力します。

    c. [ポート] ボックスに「 **80**」と入力します。

    d. **[ローカル アドレスのプロキシ サーバーをバイパスする] を選択します**。

    e. [ **OK] を** 選択して、[ **ローカル エリア ネットワーク (LAN) の設定]** ダイアログを閉じます。
6. [ **OK] を** 選択して [ **インターネット オプション] ダイアログを** 閉じます。

#### Zscaler Internet Access ZSThree テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Zscaler Internet Access ZSThree に作成します。 Zscaler Internet Access ZSThree では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Zscaler Internet Access ZSThree にユーザーがまだ存在していない場合は、Zscaler Internet Access ZSThree にアクセスしようとしたときに新しいユーザーが作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Zscaler Internet Access ZSThree サポート チーム](https://www.zscaler.com/company/contact)にお問い合わせください。

注

Zscaler Internet Access ZSThree では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-three-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zscaler Internet Access ZSThree のサインオン URL にリダイレクトされます。
- Zscaler Internet Access ZSThree のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Zscaler Internet Access ZSThree タイルを選択すると、このオプションは Zscaler Internet Access ZSThree のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-internet-access-zstwo-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zscaler Internet Access ZSTwo を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-internet-access-zstwo-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Zscaler Internet Access ZSTwo の間でシングル サインオンを構成する方法について説明します。

この記事では、Zscaler Internet Access ZSTwo と Microsoft Entra ID を統合する方法について説明します。 Zscaler Internet Access ZSTwo と Microsoft Entra ID を統合すると、次のことができます。

- Zscaler Internet Access ZSTwo にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zscaler Internet Access ZSTwo に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

Zscaler Internet Access ZSTwo は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Zscaler Internet Access ZSTwo でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Zscaler Internet Access ZSTwo では、 **SP** Initiated SSO がサポートされます。
- Zscaler Internet Access ZSTwo では、 **Just In Time** ユーザー プロビジョニングがサポートされます。
- Zscaler Internet Access ZSTwo では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-two-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Zscaler Internet Access ZSTwo を追加する

Microsoft Entra ID への Zscaler Internet Access ZSTwo の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zscaler Internet Access ZSTwo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Zscaler Internet Access ZSTwo**」と入力します。
4. 結果パネルから **Zscaler Internet Access ZSTwo** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zscaler Internet Access ZSTwo の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zscaler Internet Access ZSTwo に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Zscaler Internet Access ZSTwo の関連ユーザーとの間にリンク関係を確立する必要があります。

Zscaler Internet Access ZSTwo に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zscaler Internet Access ZSTwo SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Zscaler Internet Access ZSTwo テストユーザーを作成** - Zscaler Internet Access ZSTwo で B.Simon の対応ユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Zscaler Internet Access ZSTwo]**&gt;**[シングル サインオン]**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] ボックスに、Zscaler Internet Access ZSTwo アプリケーションへのサインオンにユーザーが使用する URL を入力します。

    注

    実際のサインオン URL でこの値を更新してください。 この値を取得するには、 [Zscaler Internet Access ZSTwo クライアント サポート チーム](https://www.zscaler.com/company/contact) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Zscaler Internet Access ZSTwo アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ **ユーザー属性] ダイアログを** 開きます。

    [Image: スクリーンショットには、[編集] アイコンが選択された [ユーザー属性] が表示されます。]
7. さらに、Zscaler Internet Access ZSTwo アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | memberOf | user.assignedroles |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: スクリーンショットには、[新しい要求の追加] オプションを含むユーザー要求が表示されます。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **保存** を選択します。

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Zscaler Internet Access ZSTwo のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zscaler Internet Access ZSTwo SSO の構成

1. 別の Web ブラウザー ウィンドウで、Zscaler Internet Access ZSTwo 企業サイトに管理者としてサインインします
2. **管理&gt;認証&gt;認証設定**に移動し、次の手順を実行します。

    [Image: Zscaler One サイトのスクリーンショット。説明されている手順が示されています。]

    a. [認証の種類] で、[SAML] を選択 **します**。

    b。 [ **SAML の構成] を選択します**。
3. [ **SAML の編集]** ウィンドウで、次の手順を実行し、[保存] を選択します。

    [Image: ユーザーの管理 & 認証]

    a. **[SAML Portal URL**] ボックスに、**ログイン URL を**貼り付けます。

    b。 [ **ログイン名属性** ] ボックスに「 **NameID」と**入力します。

    c. [ **アップロード]** を選択して、Azure portal からダウンロードした Azure SAML 署名 **証明書をパブリック SSL 証明書**にアップロードします。

    d. **[SAML 自動プロビジョニングを有効にする] を**切り替えます。

    e. displayName 属性の SAML 自動プロビジョニングを有効にする場合は、[ **ユーザー表示名属性** ] ボックスに **「displayName** 」と入力します。

    f. memberOf 属性の SAML 自動プロビジョニングを有効にする場合は、[ **グループ名属性** ] ボックスに「 **memberOf** 」と入力します。

    g. SAML 自動プロビジョニングを部門属性に対して有効にする場合は、**部門名属性**に「**department**」と入力します。

    h. **保存** を選択します。
4. [ **ユーザー認証の構成** ] ダイアログ ページで、次の手順を実行します。

    [Image: スクリーンショットは、[アクティブ化] が選択された [ユーザー認証の構成] ダイアログ ボックスを示しています。]

    a. 左下の [ **アクティブ化** ] メニューにカーソルを合わせます。

    b。 [**を選択し、**をアクティブ化します。]

### プロキシ設定の構成

#### Internet Explorer でプロキシ設定を構成するには

1. **Internet Explorer を起動します**。
2. [**ツール**] メニューから **[インターネット オプション**] を選択し**、[インターネット オプション]** ダイアログを開きます。

    [Image: インターネット オプション]
3. [ **接続** ] タブを選択します。

    [Image: つながり]
4. **[LAN 設定]** を選択して、[**LAN 設定]** ダイアログを開きます。
5. [プロキシ サーバー] セクションで、次の手順を実行します。

    [Image: プロキシ サーバー]

    a. [ **LAN にプロキシ サーバーを使用する] を選択します**。

    b。 [アドレス] テキスト ボックスに「**gateway.Zscaler Two.net**」と入力します。

    c. [ポート] ボックスに「 **80**」と入力します。

    d. **[ローカル アドレスのプロキシ サーバーをバイパスする] を選択します**。

    e. [ **OK] を** 選択して、[ **ローカル エリア ネットワーク (LAN) の設定]** ダイアログを閉じます。
6. [ **OK] を** 選択して [ **インターネット オプション] ダイアログを** 閉じます。

#### Zscaler Internet Access ZSTwo テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Zscaler Internet Access ZSTwo に作成します。 Zscaler Internet Access ZSTwo では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Zscaler Internet Access ZSTwo にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Zscaler Internet Access ZSTwo サポート チーム](https://www.zscaler.com/company/contact)にお問い合わせください。

注

Zscaler Internet Access ZSTwo では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-two-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zscaler Internet Access ZSTwo のサインオン URL にリダイレクトされます。
- Zscaler Internet Access ZSTwo のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Zscaler Internet Access ZSTwo タイルを選択すると、このオプションは Zscaler Internet Access ZSTwo のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-oidc-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zscaler を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-oidc-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-06
- Summary: Microsoft Entra と Zscaler の間でシングル サインオンを構成する方法について説明します。

この記事では、Zscaler と Microsoft Entra ID を統合する方法について説明します。 Zscaler と Microsoft Entra ID を統合すると、次のことができます:

Microsoft Entra ID を使用して、Zscaler にアクセスできるユーザーを制御します。 ユーザーが自分の Microsoft Entra アカウントを使用して Zscaler に自動的にサインインできるようにします。 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Zscaler でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから Zscaler を追加する

Microsoft Entra ID への Zscaler の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Zscaler を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Zscaler**」と入力します。
4. 結果パネルで **Zscaler** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Zscaler**&gt;**シングルサインオン**をブラウズしてください。
3. 次のセクションで以下の手順を実行します。

    1. **[アプリケーションに移動]**を選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID を**コピーし、後で Zscaler 側の構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
    3. [ **エンドポイント** ] タブで、 **OpenID Connect メタデータ ドキュメント** リンクをコピーし、後で Zscaler 側の構成で使用します。

        [Image: タブのエンドポイントを示すスクリーンショット。]
4. 左側のメニューの **[認証]** タブに移動し、次の手順を実行します。

    1. [ **リダイレクト URI** ] ボックスに、Zscaler 側からコピーした **リダイレクト URI** 値を貼り付けます。

        [Image: リダイレクト値を示すスクリーンショット。]
5. 左側のメニューの **[証明書とシークレット]** に移動し、次の手順を実行します。

    1. **[クライアント シークレット]** タブに移動し、**[+ 新しいクライアント シークレット]** を選択します。
    2. テキストボックスに有効な **[説明]** を入力し、要件に応じてドロップダウンから **[有効期限]** 日数を選択し **[追加]** を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、**[値]** が生成されます。 値をコピーし、後で Zscaler 側の構成で使用します。

        [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

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

このセクションでは、B.Simon に Zscaler へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Zscaler** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Zscaler の SSO の構成

OIDC フェデレーションのセットアップを完了するための構成手順を次に示します。

1. Zscaler サイトにサインインします。
2. Microsoft パートナー テナントとして **ZSLogin Administration** を選択します。

    [Image: フェデレーションのセットアップを示すスクリーンショット。]
3. **[管理**] **の [外部 ID]** に移動します。

    [Image: 外部 ID を示すスクリーンショット。]
4. [外部 ID] で、[ **セカンダリ ID プロバイダー** ] に移動し、[ **+ セカンダリ IdP の追加]** を選択します。

    [Image: セカンダリIDの追加が表示されるスクリーンショット。]
5. [ **BASIC** ] セクションで、[ **全般** ] タブで次の手順を実行します。

    [Image: [全般] タブを示すスクリーンショット。]

    ある。 [ **名前** ] フィールドに、ID の名前を入力します。

    b。 [ **ID ベンダー** ] フィールドで、ドロップダウンから Microsoft Entra ID を選択します。

    c. 一覧から **ドメイン** を選択します。

    d. **プロトコルとして SAML** を選択し、**状態** を有効にします。
6. **[BASIC**] セクションで、[**OIDC 構成**] タブで次の手順を実行します。

    [Image: oidc 構成タブを示すスクリーンショット。]

    ある。 [Entra] ページからコピーした [**メタデータ URL**] フィールドに **Open ID Connect メタデータ ドキュメント**の値を貼り付け、[**FETCH**] を選択します。 値は自動的に設定されます。

    b。 FETCH ボタンを選択し、後で Entra 側の構成で使用すると生成される **リダイレクト URI** 値をコピーします。

    c. [ **クライアント ID** ] フィールドに、Entra ページからコピーした **アプリケーション ID** の値を貼り付けます。

    d. [ **クライアント シークレット** ] フィールドに、Entra 側の **[証明書とシークレット** ] セクションからコピーした値を貼り付けます。

    え **要求されたスコープで**、電子メールとプロファイルを追加します。

    f. **[更新]** を選択します。
7. [ **プロビジョニング** ] セクションで、JIT プロビジョニングを有効にして、[ **更新**] を選択します。

    [Image: プロビジョニングが表示されたスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-one-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Zscaler One を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-one-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: ユーザー アカウントを Zscaler One に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、Zscaler One と Microsoft Entra ID で実行して、ユーザーとグループを Zscaler One に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する手順について説明します。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービス上に構築されるコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問については、「 [Microsoft Entra ID を使用してサービスとしてのソフトウェア (SaaS) アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)する」を参照してください。

Zscaler One は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ |  | ✅ |

### 前提条件

この記事で説明するシナリオでは、次のことを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.

- Zscaler One テナント。
- 管理者アクセス許可がある Zscaler One のユーザー アカウント。

注

Microsoft Entra プロビジョニング統合は、Zscaler One SCIM API に依存しています。 この API は、Zscaler One の開発者が Enterprise パッケージを含むアカウントで使用できます。

### 手順 1: Azure Marketplace から Zscaler One を追加する

Microsoft Entra ID での自動ユーザー プロビジョニング用に Zscaler One を構成する前に、Zscaler One を Azure Marketplace から管理対象の SaaS アプリケーションの一覧に追加する必要があります。

Marketplace から Zscaler One を追加するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にナビゲートします。
3. **[ギャラリーからの追加**] セクションで、「**Zscaler One**」と入力し、結果パネルから **Zscaler One** を選択します。 アプリケーションを追加するには、[追加] を選択 **します**。

    [Image: 結果一覧の Zscaler One のスクリーンショット。]

### 手順 2: Zscaler One にユーザーを割り当てる

Microsoft Entra ID では、 *割り当て* と呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID でアプリケーションに割り当てられたユーザーまたはグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Zscaler One へのアクセスが必要な Microsoft Entra ID 内のユーザーまたはグループを決定します。 これらのユーザーまたはグループを Zscaler One に割 [り当てるには、「エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)」の手順に従います。

#### ユーザーを Zscaler One に割り当てるときの重要なヒント

- 単一の Microsoft Entra ユーザーを Zscaler One に割り当てて、自動ユーザー プロビジョニングの構成をテストすることをお勧めします。 後で、追加のユーザーまたはグループを割り当てることができます。
- Zscaler One にユーザーを割り当てるとき、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログ ボックスで選択します。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### 手順 3: Zscaler One への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra プロビジョニング サービスを構成する手順を説明します。 これを使用して、Microsoft Entra ID でのユーザーまたはグループの割り当てに基づいて、Zscaler One でのユーザーまたはグループの作成、更新、および無効化を行います。

ヒント

Zscaler One に対する SAML ベースのシングル サインオンを有効にすることもできます。 [Zscaler One のシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-one-tutorial)に関する記事の手順に従います。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

注

ユーザーとグループをプロビジョニングしたりプロビジョニング解除したりする際は、グループ メンバーシップが適切に更新されるよう、定期的にプロビジョニングをやり直すことをお勧めします。 そうすることによって、サービスによって強制的にすべてのグループが再評価され、メンバーシップが更新されます。

#### Microsoft Entra ID で Zscaler One に対する自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Zscaler One** を参照します。

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Zscaler One** を選択します。

    [Image: アプリケーションの一覧の [Zscaler One] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: Zscaler One Provisioning のスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、この記事で後述するように、Zscaler Beta アカウントの **テナント URL** と **シークレット トークン** を入力します。
7. テナント URL とシークレット トークンを取得するには、Zscaler One ポータル UI の **管理**&gt;**認証設定** に移動します。 [ **認証の種類] で** 、[SAML] を選択 **します**。

    [Image: Zscaler One 認証設定のスクリーンショット。]
8. [ **SAML の構成]** を選択して、[ **SAML の構成] オプションを** 開きます。

    [Image: Zscaler One Configure SAML のスクリーンショット。]
9. [ **SCIM-Based プロビジョニングを有効にする** ] を選択して、[ **ベース URL** ] と [ **ベアラー トークン**] の設定を取得します。 それらの設定を保存します。 **[ベース URL**] 設定を **[テナント URL]** にコピーします。 **ベアラー トークン**設定を**シークレット トークン**にコピーします。
10. 手順 5 で示されているボックスに入力した後、[ **テスト接続** ] を選択して、Microsoft Entra ID が Zscaler One に接続できることを確認します。 接続できない場合は、使用中の Zscaler One アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: Zscaler One テスト接続のスクリーンショット。]
11. [ **作成]** を選択して構成を作成します。
12. [**概要**] ページで **[プロパティ**] を選択します。
13. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
14. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
15. [属性マッピング] セクションで、Microsoft Entra ID から Zscaler One に同期されるユーザー **属性を** 確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Zscaler One のユーザー アカウントとの照合に使用されます。 変更を保存するには、[ **保存]** を選択します。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler One で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | externalId | 糸 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  | ✓ |
16. **[グループ] を選択します**。
17. [属性マッピング] セクションで、Microsoft Entra ID から Zscaler One に同期されるグループ **属性を** 確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Zscaler One のグループとの照合に使用されます。 変更を保存するには、[ **保存]** を選択します。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler One で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | members | 関連項目 |  |  |
18. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
19. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
20. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 4: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

- 2022 年 5 月 16 日 - このアプリで **有効になっているスキーマ検出** 機能。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-private-access-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Zscaler Private Access (ZPA) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-private-access-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: ユーザー アカウントを Zscaler Private Access (ZPA) に自動的にプロビジョニング/プロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事の目的は、Zscaler Private Access (ZPA) と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Zscaler Private Access (ZPA) に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

Zscaler Private Access (ZPA) は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- [Zscaler Private Access (ZPA) テナント](https://www.zscaler.com/pricing-and-plans#contact-us)
- 管理者アクセス許可を持つ Zscaler Private Access (ZPA) のユーザー アカウント。

### 手順 1: Zscaler Private Access (ZPA) にユーザーを割り当てる

Microsoft Entra ID では、 *割り当て* と呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内でアプリケーションに割り当て済みのユーザーとグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Microsoft Entra ID のどのユーザーまたはグループに Zscaler Private Access (ZPA) へのアクセスが必要かを決定する必要があります。 決定したら、次の手順に従って、これらのユーザーまたはグループを Zscaler Private Access (ZPA) に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### Zscaler Private Access (ZPA) にユーザーを割り当てる場合の重要なヒント

- 自動ユーザー プロビジョニング構成をテストするには、1 人の Microsoft Entra ユーザーを Zscaler Private Access (ZPA) に割り当てることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Zscaler Private Access (ZPA) にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### 手順 2: プロビジョニング用に Zscaler Private Access (ZPA) を設定する

1. [Zscaler Private Access (ZPA) 管理コンソール](https://admin.private.zscaler.com/)にサインインします。 **[管理&gt; IdP 構成]** に移動します。

    [Image: Zscaler Private Access (ZPA) 管理コンソールのスクリーンショット。]
2. **シングル サインオン**用の IdP が構成されていることを確認します。 IdP が設定されていない場合は、画面の右上隅にあるプラス アイコンを選択して追加します。

    [Image: Zscaler Private Access (ZPA) Add SCIM のスクリーンショット。]
3. **IdP 構成の追加**ウィザードに従って、IdP を追加します。 **[シングル サインオン**] フィールドは **[ユーザー**] のままにします。 **[名前]** を指定し、ドロップダウン リストから **[ドメイン**] を選択します。 **[次へ**] を選択して、次のウィンドウに移動します。

    [Image: Zscaler Private Access (ZPA) Add IdP のスクリーンショット。]
4. **サービス プロバイダー証明書**をダウンロードします。 **[次へ**] を選択して、次のウィンドウに移動します。

    [Image: Zscaler Private Access (ZPA) SP 証明書のスクリーンショット。]
5. 次のウィンドウで、前にダウンロードした **サービス プロバイダー証明書** をアップロードします。

    [Image: Zscaler Private Access (ZPA) アップロード証明書のスクリーンショット。]
6. 下にスクロールして、 **シングル サインオン URL** と **IdP エンティティ ID を**指定します。

    [Image: Zscaler Private Access (ZPA) IdP ID のスクリーンショット。]
7. 下にスクロールして **SCIM 同期を有効にします**。[ **新しいトークンの生成** ] ボタンを選択します。 **ベアラー トークン**をコピーします。 この値は、Zscaler Private Access (ZPA) アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。

    [Image: Zscaler Private Access (ZPA) Create Token のスクリーンショット。]
8. **テナント URL を**見つけるには、[**管理&gt; IdP 構成]** に移動します。 ページに一覧表示されている新しく追加された IdP 構成の名前を選択します。

    [Image: Zscaler Private Access (ZPA) Idp Name のスクリーンショット。]
9. 下にスクロールして、ページの最後にある **SCIM サービス プロバイダー エンドポイント** を表示します。 **SCIM サービス プロバイダー エンドポイント**をコピーします。 この値は、Zscaler Private Access (ZPA) アプリケーションの [プロビジョニング] タブの [テナント URL] フィールドに入力されます。

    [Image: Zscaler Private Access (ZPA) SCIM URL のスクリーンショット。]

### 手順 3: ギャラリーから Zscaler Private Access (ZPA) を追加する

Microsoft Entra ID での自動ユーザー プロビジョニング用に Zscaler Private Access (ZPA) を構成する前に、Zscaler Private Access (ZPA) を Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Zscaler Private Access (ZPA) を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、「**Zscaler Private Access (ZPA)」**と入力し、検索ボックスで **Zscaler Private Access (ZPA)** を選択します。
4. 結果パネルから **Zscaler Private Access (ZPA)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

    [Image: 結果一覧の Zscaler Private Access (ZPA) のスクリーンショット。]

### 手順 4: Zscaler Private Access (ZPA) への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて Zscaler Private Access (ZPA) 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Zscaler Private Access (ZPA) のシングル サインオンに関する記事に記載されている手順に従って、 [Zscaler Private Access (ZPA)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscalerprivateaccess-tutorial) に対して SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

注

ユーザーとグループをプロビジョニングしたりプロビジョニング解除したりする際は、グループ メンバーシップが適切に更新されるよう、定期的にプロビジョニングをやり直すことをお勧めします。 そうすることによって、サービスによって強制的にすべてのグループが再評価され、メンバーシップが更新されます。

注

Zscaler Private Access の SCIM エンドポイントの詳細については、 [こちらを](https://www.zscaler.com/partners/microsoft)参照してください。

#### Microsoft Entra ID で Zscaler Private Access (ZPA) の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[Enterprise アプリ]**&gt;**[Zscaler Private Access (ZPA)]** を参照します。

    [Image: アプリケーションの一覧の Zscaler Private Access (ZPA) リンクのスクリーンショット。]
3. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
4. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
5. [ **管理者資格情報** ] セクションで、先ほど取得した **SCIM サービス プロバイダー エンドポイント** の値を **テナント URL** に入力します。 先ほど取得した **ベアラー トークン** 値を **シークレット トークン**に入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Zscaler Private Access (ZPA) に接続できることを確認します。 接続に失敗する場合は、Zscaler Private Access (ZPA) アカウントに管理者アクセス許可があることを確認し、再試行します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
6. [ **作成]** を選択して構成を作成します。
7. [**概要**] ページで **[プロパティ**] を選択します。
8. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
9. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
10. [属性マッピング] セクションで、Microsoft Entra ID から Zscaler Private Access (ZPA) に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Zscaler Private Access (ZPA) のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler Private Access で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 |  |  |
    | 活動中 | ブール値 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | displayName | 糸 |  |  |
    | ユーザータイプ | 糸 |  |  |
    | ニックネーム | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 文字列 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
11. **[グループ] を選択します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から Zscaler Private Access (ZPA) に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Zscaler Private Access (ZPA) のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler Private Access で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | 関連項目 |  |  |
    | externalId | 糸 |  |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Zscaler ZSNet を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-30
- Summary: Zscaler ZSNet に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Zscaler ZSNet と Microsoft Entra ID で実行する手順と、Microsoft Entra ID を構成して、Zscaler ZSNet に対してユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除する方法を示すことです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次のものが既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zscaler ZSNet テナント
- 管理者アクセス許可を持つ Zscaler ZSNet のユーザー アカウント

注

Microsoft Entra プロビジョニング統合は、Zscaler ZSNet SCIM API に依存しています。これは、Enterprise パッケージのアカウントの Zscaler ZSNet 開発者が利用できます。

### 手順 1: ギャラリーから Zscaler ZSNet を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Zscaler ZSNet を構成する前に、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Zscaler ZSNet を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Zscaler ZSNet を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Zscaler ZSNet**」と入力し、結果パネルで **Zscaler ZSNet** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Zscaler ZSNet のスクリーンショット。]

### 手順 2: Zscaler ZSNet にユーザーを割り当てる

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に「割り当て」という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内でアプリケーションに「割り当て済み」のユーザーとグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Zscaler ZSNet へのアクセスが必要なMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 決定したら、こちらの手順に従って、これらのユーザーやグループを Zscaler ZSNet に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### Zscaler ZSNet にユーザーを割り当てる際の重要なヒント

- 1 人のMicrosoft Entra ユーザーを Zscaler ZSNet に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Zscaler ZSNet にユーザーを割り当てるときは、有効なアプリケーション固有のロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### 手順 3: Zscaler ZSNet への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDでのユーザーまたはグループの割り当てに基づいて Zscaler ZSNet でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

注

[サポート チケット](https://help.zscaler.com/)を開いて、Zscaler ZSNet にドメインを作成してください。

ヒント

[Zscaler ZSNet のシングル サインオンに関する記事](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-tutorial)で説明されている手順に従って、Zscaler ZSNet に対して SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

注

ユーザーとグループをプロビジョニングしたりプロビジョニング解除したりする際は、グループ メンバーシップが適切に更新されるよう、定期的にプロビジョニングをやり直すことをお勧めします。 そうすることによって、サービスによって強制的にすべてのグループが再評価され、メンバーシップが更新されます。 テナント内のすべてのユーザーとグループを同期している場合、または 50K 以上のメンバーを持つ大規模なグループを割り当てた場合、再起動に時間がかかる可能性があることに注意してください。

#### Microsoft Entra ID で Zscaler ZSNet の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Zscaler ZSNet** に移動します。
3. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている Zscaler ZSNet - [Provisioning Enterprise Application](エンタープライズ アプリケーションのプロビジョニング) サイドバーのスクリーンショット。]
4. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
5. **管理者資格情報** セクションの下で、この記事で後述するように、Zscaler ZSNet Beta アカウントの **テナント URL** と **シークレット トークン** を入力します。
6. **テナント URL** と**シークレット トークン**を取得するには、Zscaler ZSNet ポータルのユーザー インターフェイスで**管理&gt;認証設定**に移動し、[**認証の種類**] で [**SAML**] を選択します。

    [Image: [認証設定] ページのスクリーンショット。]
7. [ **SAML の構成] を** 選択して **構成 SAML オプションを** 開きます。

    [Image: [SAML の構成] ダイアログ ボックスのスクリーンショット。[ベース URL] と [ベアラー トークン] のテキスト ボックスが選択されています。]
8. **[Enable SCIM-Based Provisioning](SCIM ベースのプロビジョニングを有効にする)** を選択して、**ベース URL** と**ベアラー トークン**を取得し、設定を保存します。 **ベース URL** を**テナント URL** にコピーし、**ベアラー トークン**を**シークレット トークン**にコピーします。
9. 手順 5 に示すフィールドに値を入力したら、[**テスト接続**] を選択して、Microsoft Entra IDが Zscaler ZSNet に接続できることを確認します。 接続に失敗した場合は、Zscaler ZSNet アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: トークンのスクリーンショット。]
10. [ **作成]** を選択して構成を作成します。
11. [**概要**] ページで **[プロパティ**] を選択します。
12. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
13. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
14. **[属性マッピング]** セクションで、Microsoft Entra IDから Zscaler ZSNet に同期されるユーザー属性を確認します。 [**照合**プロパティ] として選択されている属性は、更新操作で Zscaler ZSNet のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler ZSNet に必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | displayName | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  | ✓ |
15. **[グループ] を選択します**。
16. **属性マッピング** セクションで、Microsoft Entra ID から Zscaler ZSNet に同期されるグループ属性を確認します。 **照合**プロパティとして選択されている属性は、更新操作で Zscaler ZSNet のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler ZSNet に必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | 関連項目 |  |  |
    | externalId | 糸 |  | ✓ |
17. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
18. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
19. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 4: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-three-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Zscaler Three を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-three-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: この記事では、ユーザー アカウントを Zscaler Three に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、ユーザーまたはグループを Zscaler Three に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービス上に構築されたコネクタについて説明します。 このサービスが実行する内容および動作方法についての重要な情報と、よく寄せられる質問への回答については、[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)に関する記事を参照してください。

Zscaler Three は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ |  | ✅ |

### 前提条件

この記事で説明されている手順を完了するには、次のものが必要です。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.

- Zscaler Three テナント。
- 管理者アクセス許可がある Zscaler Three のユーザー アカウント。

注

Microsoft Entra プロビジョニング統合は、エンタープライズ アカウントで利用できる Zscaler ZSCloud SCIM API に依存しています。

### 手順 1: ギャラリーから Zscaler Three を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング向けに Zscaler Three を構成する前に、Zscaler Three を Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**[エンタープライズ アプリ]**&gt;**[Zscaler Three]** を参照します。
3. 結果から **[Zscaler Three]** を選択して **[追加]** を選択します。

[Image: 結果リスト]

### 手順 2: Zscaler Three にユーザーを割り当てる

Microsoft Entra ユーザーが特定のアプリを使用するには、アプリへのアクセス権が割り当てられている必要があります。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーまたはグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Zscaler Three へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 それが決まれば、[エンタープライズ アプリへのユーザーまたはグループの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)に関するページの手順に従って、これらのユーザーとグループを Zscaler Three に割り当てることができます。

#### ユーザーを Zscaler Three に割り当てるときの重要なヒント

- まず、単一の Microsoft Entra ユーザーを Zscaler Three に割り当てて、自動ユーザー プロビジョニングの構成をテストすることをお勧めします。 後で、他のユーザーとグループを割り当てることができます。
- Zscaler Three にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログ ボックスで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### 手順 3: 自動ユーザー プロビジョニングを設定する

このセクションでは、Microsoft Entra ID でのユーザーとグループの割り当てに基づいて、Zscaler Three でユーザーとグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Zscaler Three では、SAML ベースのシングル サインオンを有効にすることもできます。 その場合は、 [Zscaler Three シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-three-tutorial)に関する記事の手順に従います。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

注

ユーザーとグループをプロビジョニングしたりプロビジョニング解除したりする際は、グループ メンバーシップが適切に更新されるよう、定期的にプロビジョニングをやり直すことをお勧めします。 再起動を行うことで、当サービスはすべてのグループを再評価し、メンバーシップを更新します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**[エンタープライズ アプリ]**&gt;**[Zscaler Three]** を参照します。

    [Image: エンタープライズ アプリケーションのスクリーンショット。]
3. アプリケーションの一覧で、 **[Zscaler Three]** を選択します。

    [Image: アプリケーションの一覧のスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: Zscaler Three Provisioning のスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. **[管理者資格情報]** セクションで、次の手順で説明する Zscaler Three アカウントの **[テナント URL]** と **[シークレット トークン]** を入力します。
7. **[テナント URL]** と **[シークレット トークン]** を取得するには、Zscaler Three ポータルで **[管理]**&gt;**[認証の設定]** の順に移動し、 **[認証の種類]** で **[SAML]** を選択します。

    [Image: Zscaler Three 認証設定のスクリーンショット。]
8. **[Configure SAML](SAML の構成)** を選択して **[Configure SAML](SAML の構成)** ウィンドウを開きます。

    [Image: [SAML の構成] ウィンドウのスクリーンショット。]
9. **[Enable SCIM-Based Provisioning](SCIM ベースのプロビジョニングを有効にする)** を選択して、**ベース URL** と**ベアラー トークン**をコピーし、設定を保存します。 Azure portal で、**ベース URL** を **[テナント URL]** ボックスに、**ベアラー トークン**を **[シークレット トークン]** ボックスに貼り付けます。
10. **[テナントの URL]** と **[シークレット トークン]** のボックスに値を入力したら、**[接続のテスト]** を選択して Microsoft Entra ID が Zscaler Three に接続できることを確認します。 接続できない場合は、使用中の Zscaler Three アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: トークンのスクリーンショット。]
11. [ **作成]** を選択して構成を作成します。
12. [**概要**] ページで **[プロパティ**] を選択します。
13. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
14. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
15. **[属性マッピング]** セクションで、Microsoft Entra ID から Zscaler Three に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Zscaler Three のユーザー アカウントとの照合に使用されます。 すべての変更をコミットするには、 **[保存]** を選択します。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler Three で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | displayName | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  | ✓ |
16. **[グループ] を選択します**。
17. **[属性マッピング]** セクションで、Microsoft Entra ID から Zscaler Three に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Zscaler Three のグループとの照合に使用されます。 すべての変更をコミットするには、 **[保存]** を選択します。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler Three で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
    | externalId | 糸 |  | ✓ |
18. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
19. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
20. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 4: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-two-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Zscaler Two を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-two-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: この記事では、ユーザー アカウントを Zscaler Two に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、ユーザーまたはグループを Zscaler Two に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービス上に構築されたコネクタについて説明します。 このサービスが実行する内容および動作方法についての重要な情報と、よく寄せられる質問への回答については、[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)に関する記事を参照してください。

Zscaler Two は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ |  | ✅ |

### 前提条件

この記事で説明されている手順を完了するには、次のものが必要です。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.

- Zscaler Two テナント。
- 管理者アクセス許可がある Zscaler Two のユーザー アカウント。

注

Microsoft Entra プロビジョニング統合は、Enterprise アカウントで使用できる Zscaler Two SCIM API に依存しています。

### 手順 1: ギャラリーから Zscaler Two を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング向けに Zscaler Two を構成する前に、Zscaler Two を Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zscaler Two**」と入力します。
4. 結果のパネルから **[Zscaler Two]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### 手順 2: Zscaler Two にユーザーを割り当てる

Microsoft Entra ユーザーが特定のアプリを使用するには、アプリへのアクセス権が割り当てられている必要があります。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーまたはグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Zscaler Two へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 それが決まれば、[エンタープライズ アプリへのユーザーまたはグループの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)に関するページの手順に従って、これらのユーザーとグループを Zscaler Two に割り当てることができます。

#### ユーザーを Zscaler Two に割り当てるときの重要なヒント

- まず、単一の Microsoft Entra ユーザーを Zscaler Two に割り当てて、自動ユーザー プロビジョニングの構成をテストすることをお勧めします。 後で、他のユーザーとグループを割り当てることができます。
- Zscaler Two にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログ ボックスで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### 手順 3: 自動ユーザー プロビジョニングを設定する

このセクションでは、Microsoft Entra ID でのユーザーとグループの割り当てに基づいて、Zscaler Two でユーザーとグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Zscaler Two では、SAML ベースのシングル サインオンを有効にすることもできます。 その場合は、 [Zscaler Two のシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-two-tutorial)に関する記事の手順に従います。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

注

ユーザーとグループをプロビジョニングしたりプロビジョニング解除したりする際は、グループ メンバーシップが適切に更新されるよう、定期的にプロビジョニングをやり直すことをお勧めします。 再起動を行うことで、当サービスはすべてのグループを再評価し、メンバーシップを更新します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**Zscaler Two** を参照します。
3. **[プロビジョニング]** タブを選択します。

    [Image: Zscaler Two Provisioning のスクリーンショット。]
4. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
5. **[管理者資格情報]** セクションで、次の手順で説明する Zscaler Two アカウントの **[テナント URL]** と **[シークレット トークン]** を入力します。
6. **[テナント URL]** と **[シークレット トークン]** を取得するには、Zscaler Two ポータルで **[管理]**&gt;**[認証の設定]** の順に移動し、 **[認証の種類]** で **[SAML]** を選択します。

    [Image: Zscaler Two 認証設定のスクリーンショット。]
7. **[Configure SAML](SAML の構成)** を選択して **[Configure SAML](SAML の構成)** ウィンドウを開きます。

    [Image: [SAML の構成] ウィンドウのスクリーンショット。]
8. **[Enable SCIM-Based Provisioning](SCIM ベースのプロビジョニングを有効にする)** を選択して、**ベース URL** と**ベアラー トークン**をコピーし、設定を保存します。 Azure portal で、**ベース URL** を **[テナント URL]** ボックスに、**ベアラー トークン**を **[シークレット トークン]** ボックスに貼り付けます。
9. **[テナントの URL]** と **[シークレット トークン]** のボックスに値を入力したら、**[接続のテスト]** を選択して Microsoft Entra ID が Zscaler Two に接続できることを確認します。 接続できない場合は、使用中の Zscaler Two アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: トークンのスクリーンショット。]
10. [ **作成]** を選択して構成を作成します。
11. [**概要**] ページで **[プロパティ**] を選択します。
12. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
13. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
14. **[属性マッピング]** セクションで、Microsoft Entra ID から Zscaler Two に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Zscaler Two のユーザー アカウントとの照合に使用されます。 すべての変更をコミットするには、 **[保存]** を選択します。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler Two で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | displayName | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  | ✓ |
15. **[グループ] を選択します**。
16. **[属性マッピング]** セクションで、Microsoft Entra ID から Zscaler Two に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Zscaler Two のグループとの照合に使用されます。 すべての変更をコミットするには、 **[保存]** を選択します。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler Two で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
    | externalId | 糸 |  | ✓ |
17. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
18. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
19. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 4: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-zidentity-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Zscaler を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-zidentity-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-09-30
- Summary: Microsoft Entra IDから Zscaler にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Zscaler と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーを [Zscaler](https://www.zscaler.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Zscaler でユーザーを作成する
- アクセスが不要になった場合に Zscaler のユーザーを削除する
- Microsoft Entra IDと Zscaler の間でユーザー属性の同期を維持する
- Zscaler でグループとグループ メンバーシップをプロビジョニングします。
- Zscaler への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。
- クライアント資格情報認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある Zscaler のユーザー アカウント。
- Zscaler のユーザーとグループにアプリ ロールを作成して割り当てる必要があります。これについては、このチュートリアルの後半で説明します。

Note

Zscaler が既にインストールされ、アプリの登録によって構成されている場合は、SCIM プロビジョニングを有効にする前に [、この前提条件の手順](https://github.com/microsoftgraph/msgraph-sdk-powershell/blob/main/samples/Scripts/AppRoleMove.ps1) を完了してください。 認証と SCIM の両方の統合を初めて実行するお客様 [は、このスクリプト](https://github.com/microsoftgraph/msgraph-sdk-powershell/blob/main/samples/Scripts/AppRoleMove.ps1) を実行する必要はありません。手順 3 に直接進むことができます。

### 手順 1: プロビジョニングデプロイメントの計画を立てる

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- [Microsoft Entra IDと Zscaler の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Zscaler を構成する

1. 管理者の資格情報を使用して Zscaler にサインインします。 次に示すように、 **管理 -&gt; ID -&gt; IDP 構成 -&gt; 外部 ID** に移動します。

    [Image: 外部 ID のスクリーンショット。]
2. [ **プロビジョニング** ] タブに移動し、次の手順を実行します。

    [Image: [基本] セクションのスクリーンショット。]

    a. **SCIM プロビジョニング**トグルをオンにします。

    b. ドロップダウンから [Oauth2 クライアント資格情報] として **[認証方法** ] を選択します。

    c. **クライアント ID** と**クライアント シークレット**をコピーして、後で使用します。

    d. ドロップダウンから **[Expires On]** を選択します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Zscaler を追加する

Microsoft Entra アプリケーション ギャラリーから Zscaler を追加して、Zscaler へのプロビジョニングの管理を開始します。 SSO 用に Zscaler を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 [ギャラリーからのアプリケーションの追加の](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)詳細について説明します。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Zscaler への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて Zscaler でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Zscaler の自動ユーザー プロビジョニングを構成するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくともアプリ所有者または[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示すスクリーンショット。]
3. アプリケーションの一覧で **Zscaler** を選択します。

    [Image: スクリーンショットは、アプリケーションの一覧の Zscaler リンクを示しています。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブを示すスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [**テナント URL**] フィールドに、Zscaler **テナント URL、クライアント識別子、クライアント シークレット**および**OAuth トークン エンドポイント**を入力します。 [**接続のテスト**] を選択して、Microsoft Entra IDが Zscaler に接続できることを確認します。 接続に失敗した場合は、Zscaler アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra IDから Zscaler に同期されるユーザー**属性**を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Zscaler のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Zscaler API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート | Zscaler で必要 |
    | --- | --- | --- | --- |
    | displayName | String | ✓ | ✓ |
    | 主要なメールアドレス | String |  | ✓ |
    | active | ブール値 |  |  |
    | title | String |  |  |
    | emails[type eq "仕事"].value | String |  |  |
    | preferredLanguage | String |  |  |
    | userName | String |  |  |
    | name.givenName | String |  |  |
    | name.familyName | String |  |  |
    | name.formatted | String |  |  |
    | addresses[type eq "work"].フォーマット済み | String |  |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | String |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | String |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | String |  |  |
    | addresses[タイプ eq "work"].郵便番号 | String |  |  |
    | アドレス[タイプ Eq "仕事"].国 | String |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | String |  |  |
    | 電話番号[タイプ eq "携帯"].値 | String |  |  |
    | 外部ID | String |  |  |
    | nickName | String |  |  |
    | userType | String |  |  |
    | timezone | String |  |  |
    | メール[タイプ eq "自宅"].値 | String |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | String |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | String |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | String |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | String |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | Reference |  |  |
12. グループを選択 **します**。
13. [属性マッピング] セクションで、Microsoft Entra IDから Zscaler に同期されるグループ**属性**を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Zscaler のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート | Zscaler で必要 |
    | --- | --- | --- | --- |
    | displayName | String | ✓ | ✓ |
    | members | Reference |  |  |
    | 外部ID | String |  | ✓ |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscaler-zscloud-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Zscaler ZSCloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-zscloud-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: この記事では、ユーザー アカウントを Zscaler ZSCloud に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、ユーザーやグループを Zscaler ZSCloud に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービス上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみについての重要な情報と、よく寄せられる質問への回答については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

Zscaler ZSCloud は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ |  | ✅ |

### 前提条件

この記事で説明されている手順を完了するには、次のものが必要です。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.

- Zscaler ZSCloud テナント。
- Admin アクセス許可がある Zscaler ZSCloud のユーザー アカウント。

注

Microsoft Entra プロビジョニング統合では、Enterprise アカウントで利用できる Zscaler ZSCloud SCIM API が必要です。

### 手順 1: ギャラリーから Zscaler ZSCloud を追加する

Microsoft Entra ID との自動ユーザー プロビジョニング対象として Zscaler ZSCloud を構成する前に、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Zscaler ZSCloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。

    [Image: エンタープライズ アプリケーションのスクリーンショット。]
3. 検索ボックスに、「**Zscaler ZSCloud**」と入力します。
4. 結果から **[Zscaler ZSCloud]** を選択して **[追加]** を選択します。

    [Image: 結果リストのスクリーンショット。]

### 手順 2: Zscaler ZSCloud にユーザーを割り当てる

Microsoft Entra ユーザーが特定のアプリを使用するためには、そのユーザーにアプリへのアクセス権が割り当てられている必要があります。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID でアプリケーションに割り当てられているユーザーまたはグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Zscaler ZSCloud へのアクセスが必要な Microsoft Entra ID のユーザー/グループを決定しておく必要があります。 それが決まれば、[エンタープライズ アプリへのユーザーまたはグループの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)に関するページの手順に従って、これらのユーザーとグループを Zscaler ZSCloud に割り当てることができます。

#### ユーザーを Zscaler ZSCloud に割り当てる際の重要なヒント

- まず、単一の Microsoft Entra ユーザーを Zscaler ZSCloud に割り当て、自動ユーザー プロビジョニングの構成をテストすることをお勧めします。 後で、他のユーザーとグループを割り当てることができます。
- Zscaler ZSCloud にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログ ボックスで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### 手順 3: 自動ユーザー プロビジョニングを設定する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて Zscaler ZSCloud 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Zscaler ZSCloud では、SAML ベースのシングル サインオンを有効にすることもできます。 その場合は、 [Zscaler ZSCloud のシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-zscloud-tutorial)に関する記事の手順に従います。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

注

ユーザーとグループをプロビジョニングしたりプロビジョニング解除したりする際は、グループ メンバーシップが適切に更新されるよう、定期的にプロビジョニングをやり直すことをお勧めします。 そうすることによって、サービスによって強制的にすべてのグループが再評価され、メンバーシップが更新されます。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**[エンタープライズ アプリ]**&gt;**[Zscaler ZSCloud]** に移動します。
3. **[プロビジョニング]** タブを選択します。

    [Image: Zscaler ZSCloud プロビジョニングのスクリーンショット。]
4. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
5. **[管理者資格情報]** セクションで、次の手順で説明する Zscaler ZSCloud アカウントの **[テナント URL]** と **[シークレット トークン]** を入力します。
6. **テナント URL** と**シークレット トークン**を取得するには、Zscaler ZSCloud ポータルで **[管理]**&gt;**[認証設定]** に移動し、**[認証タイプ]** で **[SAML]** を選択します。

    [Image: Zscaler ZSCloud 認証設定のスクリーンショット。]
7. **[Configure SAML](SAML の構成)** を選択して **[Configure SAML](SAML の構成)** ウィンドウを開きます。

    [Image: [SAML の構成] ウィンドウのスクリーンショット。]
8. **[Enable SCIM-Based Provisioning](SCIM ベースのプロビジョニングを有効にする)** を選択して、**ベース URL** と**ベアラー トークン**をコピーし、設定を保存します。 Azure portal で、**ベース URL** を **[テナント URL]** ボックスに、**ベアラー トークン**を **[シークレット トークン]** ボックスに貼り付けます。
9. **[テナント URL]** ボックスと **[シークレット トークン]** ボックスに値を入力したら、**[テスト接続]** を選択して Microsoft Entra ID が Zscaler ZSCloud に接続できることを確認します。 接続できない場合は、使用中の Zscaler ZSCloud アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: トークンのスクリーンショット。]
10. [ **作成]** を選択して構成を作成します。
11. [**概要**] ページで **[プロパティ**] を選択します。
12. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
13. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
14. **[属性マッピング]** セクションで、Microsoft Entra ID から Zscaler ZSCloud に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Zscaler ZSCloud のユーザー アカウントとの照合に使用されます。 すべての変更をコミットするには、**[保存]** を選択します。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler ZSCloud で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | displayName | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  | ✓ |
15. **[グループ] を選択します**。
16. **[属性マッピング]** セクションで、Microsoft Entra ID から Zscaler ZSCloud に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Zscaler ZSCloud のグループとの照合に使用されます。 すべての変更をコミットするには、**[保存]** を選択します。

    | 属性 | タイプ | フィルター処理のサポート | Zscaler ZSCloud で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | 関連項目 |  |  |
    | externalId | 糸 |  | ✓) |
17. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
18. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
19. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 4: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscalerprivateaccess-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zscaler Private Access (ZPA) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscalerprivateaccess-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Zscaler Private Access (ZPA) の間のシングル サインオンを構成する方法について説明します。

この記事では、Zscaler Private Access (ZPA) と Microsoft Entra ID を統合する方法について説明します。 Zscaler Private Access (ZPA) を Microsoft Entra ID と統合すると、以下のことが可能になります。

- 誰が Zscaler Private Access (ZPA) にアクセスできるかを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して、Zscaler Private Access (ZPA) に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

Zscaler Private Access (ZPA) は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- シングル サインオン (SSO) が有効な Zscaler Private Access (ZPA) サブスクリプション。

注

この統合は、Microsoft Entra 米国政府クラウド環境から利用することもできます。 このアプリケーションは、Microsoft Entra 米国政府クラウドのアプリケーション ギャラリーにあり、パブリック クラウドの場合と同じように構成できます。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zscaler Private Access (ZPA) では **SP** Initiated SSO がサポートされます。
- Zscaler Private Access (ZPA) では、[**自動化**されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-private-access-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Zscaler Private Access (ZPA) を追加する

Zscaler Private Access (ZPA) の Microsoft Entra ID への統合を構成するには、Zscaler Private Access (ZPA) をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zscaler Private Access (ZPA)** 」と入力します。
4. 結果パネルから **Zscaler Private Access (ZPA)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zscaler Private Access (ZPA) の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Zscaler Private Access (ZPA) での Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Zscaler Private Access (ZPA) の関連ユーザーとの間にリンク関係を確立する必要があります。

Zscaler Private Access (ZPA) での Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zscaler Private Access (ZPA) SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zscaler Private Access (ZPA) テストユーザーの作成** - Zscaler Private Access (ZPA) 内で、Microsoft Entraユーザーの代表として、B.Simonに対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zscaler Private Access (ZPA)** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** ページで、次の手順を実行します。

    1. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://samlsp.private.zscaler.com/auth/metadata`
    2. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://samlsp.private.zscaler.com/auth/login?domain=<DOMAIN_NAME>`

    注

    **サインオン URL** の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 値を取得するには、[Zscaler Private Access (ZPA) サポート チーム](https://help.zscaler.com/zpa-submit-ticket)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Set up Zscaler Private Access (ZPA)](Zscaler Private Access (ZPA) の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zscaler Private Access (ZPA) の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Zscaler Private Access (ZPA) 企業サイトに管理者としてサインインします
2. メニューの左側から[ **管理** ]を選択し、[ **認証** ]セクションに移動して **[IdP 構成]**を選択します。

    [Image: Zscaler Private Access Administrator の管理]
3. 右上隅にある [ **IdP 構成の追加]** を選択します。

    [Image: Zscaler Private Access Administrator の IdP]
4. **[Add IdP Configuration]** ページで、次の手順に従います。

    [Image: Zscaler Private Access Administrator での選択]

    a. [ **ファイルの選択] を選択** して、[IdP メタデータ ファイルのアップロード] フィールドに Microsoft Entra ID からダウンロードした **メタデータ ファイルをアップロード** します。

    b。 Microsoft Entra ID から **IdP メタデータ** が読み取られ、以下に示すようにすべてのフィールド情報が設定されます。

    [Image: Zscaler Private Access Administrator での構成]

    c. **[Domains]** フィールドから自分のドメインを選択します。

    d. **保存** を選択します。

#### Zscaler Private Access (ZPA) テスト ユーザーの作成

このセクションでは、Zscaler Private Access (ZPA) で Britta Simon というユーザーを作成します。 [Zscaler Private Access (ZPA) のサポート チーム](https://help.zscaler.com/zpa-submit-ticket)に問い合わせて、Zscaler Private Access (ZPA) プラットフォームでユーザーを追加します。

Zscaler Private Access (ZPA) は、自動ユーザー プロビジョニングもまた、サポートしています。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscaler-private-access-provisioning-tutorial)を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zscaler Private Access (ZPA) のサインオン URL にリダイレクトされます。
- Zscaler Private Access (ZPA) のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Zscaler Private Access (ZPA) タイルを選択すると、このオプションは Zscaler Private Access (ZPA) のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zscalerprivateaccessadministrator-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zscaler Private Access Administrator を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscalerprivateaccessadministrator-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Zscaler Private Access Administrator の間でシングル サインオンを構成する方法について説明します。

この記事では、Zscaler Private Access Administrator と Microsoft Entra ID を統合する方法について説明します。 Zscaler Private Access Administrator を Microsoft Entra ID と統合すると、次のことが可能になります。

- Zscaler Private Access Administrator にアクセスするユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して、Zscaler Private Access Administrator に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Zscaler Private Access Administrator は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- Zscaler Private Access Administrator でのシングル サインオンが有効なサブスクリプション。

注

この統合は、Microsoft Entra 米国政府クラウド環境から利用することもできます。 このアプリケーションは、Microsoft Entra 米国政府クラウドのアプリケーション ギャラリーにあり、パブリック クラウドの場合と同じように構成できます。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Zscaler Private Access Administrator は、**SP** 開始の SSO と **IDP** 開始の SSO をサポートしています。

### ギャラリーからの Zscaler Private Access Administrator の追加

Microsoft Entra ID への Zscaler Private Access Administrator の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Zscaler Private Access Administrator を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zscaler Private Access Administrator**」と入力します。
4. 結果パネルから **[Zscaler Private Access Administrator]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zscaler Private Access Administrator の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zscaler Private Access Administrator に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Zscaler Private Access Administrator の関連ユーザーとの間にリンク関係を確立する必要があります。

Zscaler Private Access Administrator に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zscaler Private Access Administrator の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zscaler Private Access Administrator のテスト ユーザーの作成** - Zscaler Private Access Administrator で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Zscaler Private Access Administrator**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.private.zscaler.com/auth/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.private.zscaler.com/auth/sso`

    c. [ **追加の URL の設定] を選択します**。

    d. **[リレー状態]** テキスト ボックスに、値 `idpadminsso` を入力します。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.private.zscaler.com/auth/sso`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Zscaler Private Access Administrator クライアント サポート チーム](https://help.zscaler.com/zpa-submit-ticket)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Zscaler Private Access Administrator のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zscaler Private Access Administrator の SSO の構成

1. 別のWebブラウザーウィンドウで、管理者としてZscaler Private Access Administratorにサインインします。
2. 上部の [ **管理** ] を選択し、[ **認証** ] セクションに移動して **[IdP 構成]** を選択します。

    [Image: Zscaler Private Access Administrator 管理者]
3. 右上隅にある [ **IdP 構成の追加]** を選択します。

    [Image: Zscaler Private Access Administrator addidp]
4. **[Add IdP Configuration]** ページで、次の手順に従います。

    [Image: Zscaler Private Access Administrator の idpselect]

    a. [ **ファイルの選択] を選択** して、[IdP メタデータ ファイルのアップロード] フィールドに Microsoft Entra ID からダウンロードした **メタデータ ファイルをアップロード** します。

    b。 Microsoft Entra ID から **IdP メタデータ** が読み取られ、以下に示すようにすべてのフィールド情報が設定されます。

    [Image: Zscaler Private Access Administrator の idpconfig]

    c. **[Administrator]** として **[Single Sign On]** を選択します。

    d. **[Domains]** フィールドから自分のドメインを選択します。

    e. **保存** を選択します。

#### Zscaler Private Access Administrator のテスト ユーザーの作成

Microsoft Entra ユーザーが Zscaler Private Access Administrator にサインインできるようにするには、ユーザーを Zscaler Private Access Administrator にプロビジョニングする必要があります。 Zscaler Private Access Administrator の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. 管理者として Zscaler Private Access Administrator の会社サイトにサインインします。
2. 上部の [ **管理** ] を選択し、[ **認証** ] セクションに移動して **[IdP 構成]** を選択します。

    [Image: Zscaler Private Access Administrator 管理者]
3. メニューの左側から **[管理者** ] を選択します。

    [Image: Zscaler Private Access Administrator の管理者]
4. 右上隅の [ **管理者の追加**] を選択します。

    [Image: Zscaler Private Access Administrator の管理者の追加]
5. **[Add Administrator]** ページで、次の手順に従います。

    [Image: Zscaler Private Access Administrator のユーザー管理]

    a. **[Username]** ボックスに、ユーザーのメール (BrittaSimon@contoso.com など) を入力します。

    b。 **[Password]** ボックスに、ユーザーのパスワードを入力します。

    c. **[Confirm Password]** ボックスに、パスワードを入力します。

    d. **[Role]** に **[Zscaler Private Access Administrator]** を選択します。

    e. [ **電子メール** ] ボックスに、ユーザーの電子メール ( BrittaSimon@contoso.comなど) を入力します。

    f. **[Phone]** ボックスに、電話番号を入力します。

    g. **[Timezone]** ボックスで、タイム ゾーンを選択します。

    h. **保存** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zscaler Private Access Administrator のサインオン URL にリダイレクトされます。
- Zscaler Private Access Administrator のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Zscaler Private Access Administrator に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Zscaler Private Access Administrator] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Zscaler Private Access Administrator に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zuddl-tutorial"} -->
## Microsoft Entra ID で Zuddl for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zuddl-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zuddl 間にシングル サインオンを構成する方法について学習します。

この記事では、Zuddl と Microsoft Entra ID を統合する方法について説明します。 Zuddl を Microsoft Entra ID と統合すると、次のことができます。

- Zuddl にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Zuddl に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zuddl でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zuddl では、**SP** Initiated SSO がサポートされます

### ギャラリーからの Zuddl の追加

Microsoft Entra ID への Zuddl の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Zuddl を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zuddl**」と入力します。
4. 結果のパネルから **[Zuddl]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zuddl 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Zuddl で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Zuddl の関連ユーザーとの間にリンク関係を確立する必要があります。

Zuddl で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zuddl の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zuddl テスト ユーザーの作成** - Zuddlにおいて B.Simonの対応ユーザーを作成し、それを Microsoft Entra におけるB.Simonの表現とリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zuddl**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.zuddl.com/<CUSTOM_URL>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.zuddl.com/<CUSTOM_ID>`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.workos.com/sso/saml/acs/<CUSTOM_ID>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、応答 URL でこれらの値を更新します。 この値を取得するには、[Zuddl クライアント サポート チーム](mailto:support@zuddl.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Zuddl のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zuddl の SSO の構成

**Zuddl** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Zuddl サポート チーム](mailto:support@zuddl.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Zuddl のテスト ユーザーの作成

このセクションでは、Zuddl で Britta Simon というユーザーを作成します。 [Zuddl サポート チーム](mailto:support@zuddl.com)と連携し、Zuddl プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zuddl のサインオン URL にリダイレクトされます。
- Zuddl のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Zuddl] タイルを選択すると、このオプションは Zuddl のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zwayam-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Zwayam を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zwayam-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zwayam 間にシングル サインオンを構成する方法について説明します。

この記事では、Zwayam と Microsoft Entra ID を統合する方法について説明します。 Zwayam と Microsoft Entra ID を統合すると、次のことができます。

- Zwayam にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Zwayam に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zwayam でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Zwayam では、**SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Zwayam を追加する

Microsoft Entra ID への Zwayam の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Zwayam を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zwayam**」と入力します。
4. 結果パネルから **[Zwayam]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zwayam 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Zwayam で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Zwayam の関連ユーザーとの間にリンク関係を確立する必要があります。

Zwayam に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zwayam SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zwayam テストユーザーの作成** - Microsoft Entra のユーザーとしての B.Simon に対応するものとして Zwayam で作成されます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Zwayam**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://sso.zwayam.com/zwayam-saml/saml/metadata`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sso.zwayam.com/zwayam-saml/zwayam-saml/saml/login?idp=<SAML Entity ID>`

    注

    **サインオン URL** の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 `<SAML Entity ID>` は、この記事の後半で説明する Microsoft Entra 識別子の値です。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Zwayam のセットアップ]** セクションで、要件のとおりに適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zwayam SSO を構成する

**Zwayam** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[Zwayam サポート チーム](mailto:opendoors@zwayam.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Zwayam テスト ユーザーの作成

このセクションでは、Zwayam で Britta Simon というユーザーを作成します。 [Zwayam サポート チーム](mailto:opendoors@zwayam.com)と連携し、Zwayam プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zwayam のサインオン URL にリダイレクトされます。
- Zwayam のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Zwayam] タイルを選択すると、このオプションは Zwayam のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

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
