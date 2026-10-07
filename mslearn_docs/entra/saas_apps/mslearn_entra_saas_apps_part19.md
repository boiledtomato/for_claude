# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 19)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 76

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/proofpoint-security-awareness-training-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Proofpoint Security Awareness Training を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proofpoint-security-awareness-training-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Proofpoint Security Awareness Training の間でシングル サインオンを構成する方法について説明します。

この記事では、Proofpoint Security Awareness Training と Microsoft Entra ID を統合する方法について説明します。 このアプリケーションを使用すると、Microsoft Entra ID は、Proofpoint Security Awareness Training に対してユーザーを認証する SAML IdP として機能できます。 Proofpoint Security Awareness Training と Microsoft Entra ID を統合すると、次のことが可能になります。

- Proofpoint Security Awareness Training にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Proofpoint Security Awareness Training に自動でサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で、Proofpoint Security Awareness Training 用の Microsoft Entra シングル サインオンを構成してテストします。 Proofpoint Security Awareness Training は、**SP** Initiated と **IDP** Initiated 両方のシングル サインオンと、**Just In Time** ユーザー プロビジョニングをサポートしています。

### [前提条件]

Microsoft Entra ID と Proofpoint Security Awareness Training を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Proofpoint Security Awareness Training シングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Proofpoint Security Awareness Training アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Proofpoint Security Awareness Training を追加する

Microsoft Entra アプリケーション ギャラリーから Proofpoint Security Awareness Training を追加して、Proofpoint Security Awareness Training でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Proofpoint Security Awareness Training]**&gt;**[シングル サインオン] ** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.<ENVIRONMENT>/api/auth/saml/metadata`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.<ENVIRONMENT>/api/auth/saml/SSO`
6. **SP** Initiated モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.<ENVIRONMENT>`

    b。 **[リレー状態]** ボックスに、`https://<SUBDOMAIN>.<ENVIRONMENT>` のパターンで URL を入力します。

    c. [ **ログアウト URL]** ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.<ENVIRONMENT>/api/auth/saml/SingleLogout`

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL、リレー状態、ログアウト URL で更新してください。 これらの値を取得するには、[Proofpoint Security Awareness Training クライアント サポート チーム](mailto:wst-support@proofpoint.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### Proofpoint Security Awareness Training の SSO を構成する

**Proofpoint Security Awareness Training** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を[Proofpoint Security Awareness Training サポート チーム](mailto:wst-support@proofpoint.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Proofpoint Security Awareness Training のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Proofpoint Security Awareness Training に作成します。 Proofpoint Security Awareness Training では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Proofpoint Security Awareness Training にユーザーがまだ存在しない場合、一般的には認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Proofpoint Security Awareness Training のサインオン URL にリダイレクトされます。
- Proofpoint Security Awareness Training のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Proofpoint Security Awareness Training に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Proofpoint Security Awareness Training] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Proofpoint Security Awareness Training に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/proprofs-classroom-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ProProfs Training Maker を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proprofs-classroom-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ProProfs Training Maker の間にシングル サインオンを構成する方法について説明します。

この記事では、ProProfs Training Maker と Microsoft Entra ID を統合する方法について説明します。 ProProfs Training Maker を Microsoft Entra ID と統合すると、次のことができます。

- ProProfs Training Maker にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して ProProfs Training Maker に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ProProfs Training Maker でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ProProfs Training Maker では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの ProProfs Training Maker の追加

Microsoft Entra ID への ProProfs Training Maker の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ProProfs Training Maker を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ProProfs Training Maker**」と入力します。
4. 結果のパネルから **[ProProfs Training Maker]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ProProfs Training Maker 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ProProfs Training Maker での Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと ProProfs Training Maker の関連ユーザーとの間にリンク関係を確立する必要があります。

ProProfs Training Maker での Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ProProfs Training Maker SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ProProfs Training Maker のテストユーザーを作成する** - Microsoft Entra のユーザー表現にリンクされた B.Simon に対応する ProProfs Training Maker のユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**ProProfs Training Maker**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[ProProfs Training Maker の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ProProfs Training Maker SSO の構成

**ProProfs Training Maker** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [ProProfs Training Maker サポート チーム](mailto:support@proprofs.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ProProfs Training Maker のテスト ユーザーの作成

このセクションでは、ProProfs Training Maker で Britta Simon というユーザーを作成します。 [ProProfs Training Maker サポート チーム](mailto:support@proprofs.com)と連携し、ProProfs Training Maker プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ProProfs Training Maker に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ProProfs Training Maker] タイルを選択すると、SSO を設定した ProProfs Training Maker に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/proprofs-knowledge-base-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に ProProfs ナレッジ ベースを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proprofs-knowledge-base-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と ProProfs Knowledge Base の間にシングル サインオンを構成する方法について説明します。

この記事では、ProProfs ナレッジ ベースと Microsoft Entra ID を統合する方法について説明します。 ProProfs Knowledge Base を Microsoft Entra ID と統合すると、次のことができます。

- ProProfs Knowledge Base にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って ProProfs Knowledge Base に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

ProProfs ナレッジ ベースは、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- ProProfs Knowledge Base でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ProProfs ナレッジ ベースでは、 **IDP** によって開始される SSO がサポートされます。

### ギャラリーからの ProProfs Knowledge Base の追加

Microsoft Entra ID への ProProfs Knowledge Base の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ProProfs Knowledge Base を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「ProProfs Knowledge Base**」と入力します。
4. 結果パネルから **ProProfs ナレッジ ベース** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ProProfs Knowledge Base 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ProProfs ナレッジ ベースに対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと ProProfs Knowledge Base の関連ユーザーとの間にリンク関係を確立する必要があります。

ProProfs Knowledge Base に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ProProfs Knowledge Base の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ProProfs ナレッジ ベースのテストユーザーの作成** - ProProfs ナレッジ ベースで B.Simon に相当するユーザーを作成し、Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ProProfs Knowledge Base**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **ProProfs ナレッジ ベースのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ProProfs Knowledge Base の SSO の構成

**ProProfs ナレッジ ベース**側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [ProProfs サポート チーム](mailto:support@proprofs.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ProProfs Knowledge Base のテスト ユーザーの作成

このセクションでは、ProProfs Knowledge Base で Britta Simon というユーザーを作成します。 [ProProfs ナレッジ ベース サポート チーム](mailto:support@proprofs.com)と協力して、ProProfs ナレッジ ベース プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ProProfs ナレッジ ベースに自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [ProProfs Knowledge Base] タイルを選択すると、SSO を設定した ProProfs ナレッジ ベースに自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/prosci-portal-tutorial"} -->
## Microsoft Entra ID で Prosci Portal for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/prosci-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Prosci Portal の間でシングル サインオンを構成する方法について説明します。

この記事では、Prosci Portal と Microsoft Entra ID を統合する方法について説明します。 Prosci Portal と Microsoft Entra ID を統合すると、次のことができます。

- Prosci Portal にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Prosci Portal に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、無料でアカウントを作成できます 。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Prosci Portal でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Prosci Portal では、**SP** によって開始される SSO がサポートされています。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Prosci ポータルの追加

Microsoft Entra ID への Prosci Portal の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Prosci ポータルを追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Prosci Portal**」と入力します。
4. 結果のパネルから Prosci portal  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Prosci Portal の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Prosci Portal に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Prosci Portal の関連ユーザーとの間にリンク関係を確立する必要があります。

Prosci Portal で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Prosci Portal の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Prosci Portal のテストユーザーを作成 - Prosci Portal で B.Simon を対応するユーザーとして作成し、Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Prosci Portal**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    ある。 **識別子** ボックスに、次のいずれかの値を入力します。

    | **環境** | **URL** |
    | --- | --- |
    | 生産 | `urn:auth0:prosci-prod:microsoft` |
    | ステージング | `urn:auth0:prosci-staging:microsoft` |

    b。 [**応答 URL** ボックスに、次のいずれかの URL を入力します。

    | **環境** | **URL** |
    | --- | --- |
    | 生産 | `https://id.prosci.com/login/callback?connection=microsoft` |
    | ステージング | `https://id-staging.prosci.com/login/callback?connection=microsoft` |

    c. [**サインオン URL** ボックスに、次のいずれかの URL を入力します。

    | **環境** | **URL** |
    | --- | --- |
    | 生産 | `https://id.prosci.com` |
    | ステージング | `https://id-staging.prosci.com` |

    d. [**リレーステート** テキストボックスに、次のいずれかの URL を入力します。

    | **環境** | **URL** |
    | --- | --- |
    | 生産 | `https://portal.prosci.com` |
    | ステージング | `https://portal-staging.prosci.com` |

    え [**ログアウト URL** ボックスに、次のいずれかの URL を入力します。

    | **環境** | **URL** |
    | --- | --- |
    | 生産 | `https://id.prosci.com/logout` |
    | ステージング | `https://id-staging.prosci.com/logout` |
6. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML  検索し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: スクリーンショットには、証明書をダウンロードするリンクが表示されています。]
7. **[Prosci Portal** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: Screenshot shows to Copy configuration URLs.]構成 URL のコピーを示しているスクリーンショットです。メタデータ の

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Prosci Portal の SSO の構成

Prosci Portal **側** でシングル サインオンを構成するには、ダウンロードした **フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を Prosci Portal サポートチーム に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Prosci Portal のテスト ユーザーの作成

このセクションでは、Prosci Portal で B.Simon というユーザーを作成します。 [Prosci Portal サポート チーム](mailto:support@prosci.com) と連携して、Prosci Portal プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Prosci Portal のサインオン URL にリダイレクトします。
- Prosci Portal のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Prosci Portal] タイルを選択すると、このオプションは Prosci Portal のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/proto.io-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Proto.io を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proto.io-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Proto.io の間のシングル サインオンを構成する方法について説明します。

この記事では、Proto.io と Microsoft Entra ID を統合する方法について説明します。 Proto.io と Microsoft Entra ID を統合すると、次のことができます。

- Proto.io にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Proto.io に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Proto.io でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Proto.io では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Proto.io を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスを拡張したものです。 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)でセッション制御を適用する方法について説明します。

### ギャラリーからの Proto.io の追加

Microsoft Entra ID への Proto.io の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Proto.io を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Proto.io**」と入力します。
4. 結果のパネルから **[Proto.io]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Proto.io 用に Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使って、Proto.io に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Proto.io の関連ユーザーとの間にリンク関係を確立する必要があります。

Proto.io に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Proto.io の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Proto.io テストユーザーを作成します** - Proto.io で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Proto.io**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ア。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://proto.io/saml/sp/<PROTO-SAML-ACCOUNT-ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://saml.proto.io/login/callback?code=<PROTO-SAML-ACCOUNT-ID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<PROTO-SAML-ACCOUNT-ID>.proto.io`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Proto.io クライアント サポート チーム](mailto:support@proto.io)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Proto.io アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Proto.io アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | fname | ユーザー.ファーストネーム |
    | 姓名 | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Set up Proto.io](Proto.io の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Proto.io の SSO の構成

**Proto.io** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [Proto.io サポート チーム](mailto:support@proto.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Proto.io のテスト ユーザーの作成

このセクションでは、Proto.io で Britta Simon というユーザーを作成します。 [Proto.io サポート チーム](mailto:support@proto.io)と連携して、Proto.io プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Proto.io] タイルを選択すると、SSO を設定した Proto.io に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/proware-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して自動ユーザー プロビジョニング用に Proware を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proware-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: Microsoft Entra ID から Proware に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Proware と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、Proware に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされる機能

- Proware でユーザーを作成する
- アクセスが不要になった場合に Proware のユーザーを削除する
- Microsoft Entra ID と Proware の間でユーザー属性の同期を維持する
- Proware への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proware-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Proware のサブスクリプション。
- 管理者アクセス権を持つ Proware のユーザー アカウント。

### 手順 1: プロビジョニング展開を計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Proware の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Proware を構成する

1. `https://www.metaware.nl/Proware` に移動して Proware アプリケーションにサインインします。
2. **コントロール パネル**&gt;**Admin** に移動します。
3. **[コントロール パネルの設定**] を選択し、[**ユーザー プロビジョニング]** まで下にスクロールして、[ユーザー プロビジョニング] **を有効にします**。
4. [ **ベアラー トークンの作成** ] ボタンを選択し、 **トークン**をコピーします。 この値は、Proware アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。
5. **テナント URL を**コピーします。 この値は、Proware アプリケーションの [プロビジョニング] タブの [テナント URL] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Proware を追加する

Microsoft Entra アプリケーション ギャラリーから Proware を追加して、Proware へのプロビジョニングの管理を開始します。 以前に、SSO 用に Proware を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Proware への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、TestApp でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Proware の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で [ **Proware**] を選択します。

    [Image: アプリケーションの一覧の Proware のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Proware テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Proware に接続できることを確認します。 接続に失敗した場合は、Proware アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Proware に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Proware のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Proware API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | タイトル | 糸 |  |
    | externalId | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/proware-tutorial"} -->
## Microsoft Entra ID で Proware for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proware-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Proware の間にシングル サインオンを構成する方法について説明します。

この記事では、Proware と Microsoft Entra ID を統合する方法について説明します。 Proware と Microsoft Entra ID を統合すると、次のことができます。

- Proware にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Proware に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Proware でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Proware では、**SP および IDP** Initiated SSO がサポートされます。
- Proware では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proware-provisioning-tutorial)がサポートされます。

### ギャラリーからProwareを追加する

Microsoft Entra ID への Proware の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Proware を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Proware**」と入力します。
4. 結果のパネルから **Proware** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Proware 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Proware に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Proware の関連ユーザーとの間にリンク関係を確立する必要があります。

Proware に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Proware の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Proware のテスト ユーザーの作成** - Proware で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Proware**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    ある。 **[識別子]** ボックスに、`https://<CUSTOMER_NAME>.metaware.nl` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<CUSTOMER_NAME>.metaware.nl/names.nsf?SAMLLogin` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<CUSTOMER_NAME>.metaware.nl/` という形式で URL を入力します。

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Proware クライアント サポート チーム](mailto:helpdesk@metaware.nl)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML  検索し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Proware のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Proware の SSO の構成

**Proware** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Proware サポート チーム](mailto:helpdesk@metaware.nl)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Proware のテスト ユーザーの作成

このセクションでは、Proware で Britta Simon というユーザーを作成します。 [Proware サポート チーム](mailto:helpdesk@metaware.nl)と連携して、Proware プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Proware では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proware-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Proware のサインオン URL にリダイレクトされます。
- Proware のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Proware に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Proware] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Proware に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/proxyclick-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Proxyclick を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proxyclick-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-20
- Summary: Proxyclick に対するユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に行うように Microsoft Entra ID を構成する方法について説明します。

この記事の目的は、Proxyclick と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Proxyclick に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Proxyclick テナント](https://www.proxyclick.com/pricing)
- 管理者アクセス許可がある Proxyclick のユーザー アカウント

### ギャラリーから Proxyclick を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に Proxyclick を構成する前に、Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に Proxyclick を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Proxyclick を追加するには、次の手順を実行します。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Proxyclick**」と入力して、**[Proxyclick]** を選びます。
4. 結果のパネルから **[Proxyclick]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

    [Image: 結果一覧の Proxyclick を示す図。]

### Proxyclick へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に*割り当て*という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Proxyclick へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Proxyclick に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Proxyclick に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを Proxyclick に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Proxyclick にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### Proxyclick への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Proxyclick でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Proxyclick のシングル サインオンに関する記事で説明されている手順に従って、Proxyclick に対して SAML ベースの [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proxyclick-tutorial)を有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Proxyclick の自動ユーザー プロビジョニングを構成するには

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示す図。]
3. アプリケーションの一覧で **[Proxyclick]** を選択します。

    [Image: アプリケーションの一覧の Proxyclick リンクを示す図。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. Proxyclick アカウントの **テナント URL** と **シークレット トークン** を取得するには、ドキュメントの後半で説明する手順に従います。
7. [Proxyclick 管理コンソール](https://app.proxyclick.com/login//?destination=%2Fdefault)にサインインします。 **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)**&gt;**[Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合)**&gt;**[Browse Marketplace](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/マーケットプレースの参照)** に移動します。

    [Image: Proxyclick の設定を示す図。]

    [Image: Proxyclick 統合を示す図。]

    [Image: Proxyclick Marketplace を示す図。]

    **[Microsoft Entra ID]** を選びます。 **[今すぐインストール]** を選択します。

    [Image: Proxyclick Microsoft Entra ID を示す図。]

    [Image: Proxyclick Install を示す図。]

    [ **ユーザー プロビジョニング]** を選択し、[ **統合の開始]** を選択します。

    [Image: Proxyclick ユーザー プロビジョニングを示す図。]

    **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)**&gt;**[Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合)** で、適切な設定構成 UI が表示されるはずです。 **[Microsoft Entra ID (ユーザー プロビジョニング)]** の **[設定]** を選びます。

    [Image: Proxyclick Create を示す図。]

    ここで、**テナント URL** と**シークレット トークン**を見つけることができます。

    [Image: Proxyclick Create Token を示す図。]
8. 手順 5 に示すフィールドに値を入力したら、[ **テスト接続** ] を選択して、Microsoft Entra ID が Proxyclick に接続できることを確認します。 接続できない場合は、使用中の Proxyclick アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: トークンを示す図。]
9. [ **作成]** を選択して構成を作成します。
10. [**概要**] ページで **[プロパティ**] を選択します。
11. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
12. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Proxyclick に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性を使用して、更新処理で Proxyclick のユーザー アカウントとの照合が行われます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Proxyclick ユーザー属性を示す図。]
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

- Proxyclick では、**[emails]** と **[userName]** に同じソース値を設定する必要があります。 片方の属性が更新されると、他方の値が変更されます。
- Proxyclick では、グループのプロビジョニングはサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/proxyclick-tutorial"} -->
## Microsoft Entra ID で Proxyclick for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proxyclick-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: この記事では、Microsoft Entra ID と Proxyclick の間でシングル サインオンを構成する方法について説明します。

この記事では、Proxyclick と Microsoft Entra ID を統合する方法について説明します。 Proxyclick と Microsoft Entra ID を統合すると、次のことができます。

- Proxyclick にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Proxyclick に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Proxyclick でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Proxyclick では、SP によって開始される SSO と IdP によって開始される SSO がサポートされます。
- Proxyclick では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/proxyclick-provisioning-tutorial)。

### ギャラリーから Proxyclick を追加する

Microsoft Entra ID への Proxyclick の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Proxyclick を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Proxyclick**」と入力します。
4. 結果パネルから Proxyclick  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Proxyclick の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Proxyclick に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Proxyclick の関連ユーザーとの間にリンク関係を確立する必要があります。

Proxyclick で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Proxyclick SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Proxyclick のテストユーザーを作成** - B.Simon に対応するユーザーを Proxyclick に作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Proxyclick**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成** ] ダイアログ ボックスで、IdP 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://saml.proxyclick.com/init/<COMPANY_ID>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://saml.proxyclick.com/consume/<COMPANY_ID>`
6. SP 開始モードでアプリケーションを構成する場合は、[ **追加の URL の設定]** を選択します。 [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。

    `https://saml.proxyclick.com/init/<COMPANY_ID>`

    手記

    これらの値はプレースホルダーです。 実際の識別子、応答 URL、サインオン URL を使用する必要があります。 これらの値を取得する手順については、この記事の後半で説明します。
7. [**SAML を使用した単一 Sign-On の設定**] ページの [**SAML 署名証明書**] セクションで、要件に従って **[証明書 (Base64)] の**横にある **[ダウンロード**] リンクを選択し、証明書をコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Proxyclick のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Proxyclick SSO の構成

1. 新しい Web ブラウザー ウィンドウで、Proxyclick 企業サイトに管理者としてサインインします。
2. [ **アカウント] と [設定] を選択します**。

    [Image: [アカウント] と [設定] を選択します。]
3. [ **統合** ] セクションまで下にスクロールし、[SAML] を選択 **します**。

    [Image: [SAML] を選択します。]
4. **SAML** セクションで、次の手順を実行します。

    [Image: SAML セクション]

    1. **SAML コンシューマー URL** の値をコピーし、[**基本的な SAML 構成**] ダイアログ ボックスの **[応答 URL**] ボックスに貼り付けます。
    2. **SAML SSO リダイレクト URL** の値をコピーし、[**基本的な SAML 構成**] ダイアログ ボックスの **[サインオン URL**] ボックスと [**識別子**] ボックスに貼り付けます。
    3. **[SAML 要求方法**] ボックスの一覧で、[**HTTP リダイレクト**] を選択します。
    4. [ **発行者** ] ボックスに、コピーした **Microsoft Entra 識別子** の値を貼り付けます。
    5. **[SAML 2.0 エンドポイント URL**] ボックスに、コピーした**ログイン URL** の値を貼り付けます。
    6. メモ帳で、ダウンロードした証明書ファイルを開きます。 このファイルの内容を **[証明書** ] ボックスに貼り付けます。
    7. [ **変更の保存] を選択します**。

#### Proxyclick テスト ユーザーの作成

Microsoft Entra ユーザーが Proxyclick にサインインできるようにするには、ユーザーを Proxyclick に追加する必要があります。 手動で追加する必要があります。

ユーザー アカウントを作成するには、次の手順を実行します。

1. Proxyclick 企業サイトに管理者としてサインインします。
2. ウィンドウの上部にある **[仕事仲間** ] を選択します。

    [Image: [仕事仲間] を選択します。]
3. [ **仕事仲間の追加] を選択します**。

    [Image: [仕事仲間の追加] を選択します。]
4. [ **仕事仲間の追加] セクションで** 、次の手順を実行します。

    [Image: 仕事仲間セクションを追加します。]

    1. [ **電子メール** ] ボックスに、ユーザーのメール アドレスを入力します。 この場合、**brittasimon@contoso.com**。
    2. [ **名前** ] ボックスに、ユーザーの名前を入力します。 この場合、 **Britta**。
    3. [ **姓** ] ボックスに、ユーザーの姓を入力します。 この場合、 **Simon**。
    4. [ **ユーザーの追加] を選択します**。

手記

Proxyclick では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法については、 詳細を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP開始

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Proxyclick のサインオン URL にリダイレクトされます。
- Proxyclick のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDPが開始されました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Proxyclick に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Proxyclick] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Proxyclick に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pulse-secure-pcs-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Pulse Secure PCS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pulse-secure-pcs-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Pulse Secure PCS の間にシングル サインオンを構成する方法について説明します。

この記事では、Pulse Secure PCS と Microsoft Entra ID を統合する方法について説明します。 Pulse Secure PCS を Microsoft Entra ID を統合すると、次のことができます。

- Pulse Secure PCS にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Pulse Secure PCS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pulse Secure PCS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Pulse Secure PCS では、**SP** Initiated SSO がサポートされます

### ギャラリーからの Pulse Secure PCS の追加

Microsoft Entra ID への Pulse Secure PCS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Pulse Secure PCS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Pulse Secure PCS**」と入力します。
4. 結果のパネルから **[Pulse Secure PCS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pulse Secure PCS 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Pulse Secure PCS に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Pulse Secure PCS の関連ユーザーとの間にリンク関係を確立する必要があります。

Pulse Secure PCS に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pulse Secure PCS の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Pulse Secure PCS テストユーザーを作成** - Microsoft Entra におけるユーザー B.Simon にリンクされた、Pulse Secure PCS での B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Pulse Secure PCS**&gt;**シングル サインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<FQDN of PCS>/dana-na/auth/saml-consumer.cgi`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<FQDN of PCS>/dana-na/auth/saml-endpoint.cgi?p=sp1`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://[FQDN of PCS]/dana-na/auth/saml-consumer.cgi`

    注

    これらの値は実際の値ではありません。 これらの値を実際のサインオン URL、応答 URL、識別子で更新してください。 これらの値を取得するには、[Pulse Secure PCS クライアント サポート チーム](mailto:support@pulsesecure.net)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Pulse Secure PCS のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pulse Secure PCS の SSO の構成

このセクションでは、PCS を SAML SP として構成するために必要な SAML 構成について説明します。 領域やロールの作成などのその他の基本的な構成については説明しません。

**Pulse Connect Secure の構成は次のとおりです。**

- Microsoft Entra ID を SAML メタデータ プロバイダーとして構成する
- SAML 認証サーバーの構成
- それぞれの領域とロールへの割り当て

##### Microsoft Entra ID を SAML メタデータ プロバイダーとして構成する

次のページで、以下の手順を実行します。

[Image: Pulse Connect Secure の構成]

1. Pulse Connect Secure 管理コンソールにログインします。
2. **[System](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/システム) -&gt; [Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) -&gt; [SAML]** の順に移動します。
3. **新しいメタデータ プロバイダー**の選択
4. **[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前)** ボックスに有効な名前を入力します。
5. Azure portal からダウンロードしたメタデータ XML ファイルを **Microsoft Entra メタデータ ファイル**にアップロードします。
6. **[Accept Unsigned Metadata](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/署名されていないメタデータを受け入れる)** を選択します。
7. **[Identity Provider](ID プロバイダー)** ロールを選択します。
8. **[変更の保存]** を選択します。

##### SAML 認証サーバーの作成手順

1. **[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) -&gt; [Auth Servers](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証サーバー)** の順に移動します。
2. [**新規: SAML サーバー**] を選択し、[**新しいサーバー**] を選択します

    [Image: Pulse Connect Secure 認証サーバー]
3. [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) ページで、次の手順を実行します。

    [Image: Pulse Connect Secure 認証サーバーの設定]

    ある。 テキスト ボックスで**サーバー名**を指定します。

    b。 **SAML Version 2.0** を選択し、 **[構成モード]** で **[Metadata](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メタデータ)** を選択します。

    c. **[Connect Secure エンティティ ID]** の値をコピーし、**[基本的な SAML 構成]** ダイアログ ボックスにある **[識別子 URL]** ボックスに貼り付けます。

    d. **[Identity Provider Entity Id] (ID プロバイダー エンティティ ID) ボックスの一覧**で、Microsoft Entra エンティティ ID を値として選びます。

    え **[Identity Provider Single Sign-On Service URL] (ID プロバイダーのシングル サインオン サービス URL) ドロップダウン リスト**から Microsoft Entra ログイン URL の値を選びます。

    f. **シングル ログアウト**は省略可能な設定です。 このオプションが選択されていると、ログアウトした後に新しい認証が要求されます。 このオプションが選択されておらず、ブラウザーを閉じていない場合は、認証なしで再接続できます。

    ジー **要求される認証コンテキスト クラス**として **[Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード)** を、**比較方法**として **[exact](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/完全)** を選択します。

    h. **[Metadata Validity](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メタデータの有効性)** を日数の観点で設定します。

    一. [ **変更の保存] を選択します**

#### Pulse Secure PCS のテスト ユーザーの作成

このセクションでは、Pulse Secure PCS で Britta Simon というユーザーを作成します。 [Pulse Secure PCS サポート チーム](mailto:support@pulsesecure.net)と連携して、Pulse Secure PCS プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

1. [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Pulse Secure PCS のサインオン URL にリダイレクトされます。
2. Pulse Secure PCS のサインオン URL に直接移動し、そこからログイン フローを開始します。
3. Microsoft アクセス パネルを使用することができます。 アクセス パネルで [Pulse Secure PCS] タイルを選択すると、このオプションは Pulse Secure PCS のサインオン URL にリダイレクトされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pulse-secure-virtual-traffic-manager-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Pulse Secure Virtual Traffic Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pulse-secure-virtual-traffic-manager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Pulse Secure Virtual Traffic Manager の間でシングル サインオンを構成する方法について説明します。

この記事では、Pulse Secure Virtual Traffic Manager と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Pulse Secure Virtual Traffic Manager を統合すると、次のことができます。

- Pulse Secure Virtual Traffic Manager にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Pulse Secure Virtual Traffic Manager に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pulse Secure Virtual Traffic Manager でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Pulse Secure Virtual Traffic Manager では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Pulse Secure Virtual Traffic Manager の追加

Microsoft Entra ID への Pulse Secure Virtual Traffic Manager の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Pulse Secure Virtual Traffic Manager を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Pulse Secure Virtual Traffic Manager**」と入力します。
4. 結果のパネルから **[Pulse Secure Virtual Traffic Manager]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pulse Secure Virtual Traffic Manager の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Pulse Secure Virtual Traffic Manager に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Pulse Secure Virtual Traffic Manager の関連ユーザーとの間にリンク関係を確立する必要があります。

Pulse Secure Virtual Traffic Manager に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pulse Secure Virtual Traffic Manager の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Pulse Secure Virtual Traffic Manager のテスト ユーザーの作成 - Pulse Secure Virtual Traffic Manager** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Pulse Secure Virtual Traffic Manager**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<PUBLISHED VIRTUAL SERVER FQDN>/saml/consume`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<PUBLISHED VIRTUAL SERVER FQDN>/saml/metadata`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<PUBLISHED VIRTUAL SERVER FQDN>/saml/consume`

    注

    これらの値は実際の値ではありません。 これらの値を実際のサインオン URL、応答 URL、識別子で更新してください。 これらの値を取得するには、[Pulse Secure Virtual Traffic Manager クライアント サポート チーム](mailto:support@pulsesecure.net)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Pulse Secure Virtual Traffic Manager のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pulse Secure Virtual Traffic Manager で SSO を構成する

このセクションでは、Pulse Virtual Traffic Manager で Microsoft Entra SAML 認証を有効にするために必要な構成について説明します。 構成のすべての変更は、管理 Web UI を使用して、Pulse Virtual Traffic Manager に対して行われます。

#### 信頼できる SAML ID プロバイダーを作成する

ある。 **Pulse Virtual Traffic Manager Appliance Admin UI &gt; Catalog &gt; SAML &gt; Trusted Identity Providers Catalog** ページに移動し、**[編集]** を選択します。

[Image: SAML カタログ ページ]

b。 新しい SAML 信頼済み ID プロバイダーの詳細を追加し、[シングル サインオン設定] ページで Microsoft Entra Enterprise アプリケーションから情報をコピーし、[ **Create New Trusted Identity Provider]\(新しい信頼された ID プロバイダーの作成**\) を選択します。

[Image: 信頼できる ID プロバイダーを新しく作成する]

- **[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前)** ボックスに、信頼できる ID プロバイダーの名前を入力します。
- **[エンティティ ID]** テキストボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を入力します。
- **[URL]** テキスト ボックスに、先ほどコピーした**ログイン URL** の値を入力します。
- ダウンロードした**証明書**をメモ帳で開き、その内容を **[証明書]** テキストボックスに貼り付けます。

c. 新しい SAML ID プロバイダーが正常に作成されたことを確認します。

[Image: 信頼できる ID プロバイダーを確認する]

#### Microsoft Entra 認証を使用するよう仮想サーバーを構成する

ある。 **Pulse Virtual Traffic Manager Appliance Admin UI &gt; Services &gt; Virtual Servers** ページに移動し、前に作成した仮想サーバーの横にある **[編集]** を選択します。

[Image: 仮想サーバーの編集]

b。 [ **認証** ] セクションで、[ **編集**] を選択します。

[Image: 認証セクション]

c. 仮想サーバーに対して次の認証設定を構成します。

1. 認証 -

    [Image: 仮想サーバーの認証設定]

    ある。 **[Auth!type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の種類)** で、 **[SAML Service Provider](SAML サービス プロバイダー)** を選択します。

    b。 認証の問題のトラブルシューティングを行う場合は **[Auth!verbose](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証詳細)** を [Yes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/はい) に設定し、それ以外の場合は既定値の [No](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/いいえ) をそのまま使用します。
2. 認証セッションの管理 -

    [Image: 認証セッションの管理]

    ある。 **[Auth!session!cookie\_name](認証セッション Cookie 名)** は、既定値の "VS\_SamlSP\_Auth" をそのまま使用します。

    b。 **[auth!session!timeout](認証セッション タイムアウト)** は、既定値の "7200" をそのまま使用します。

    c. 認証の問題のトラブルシューティングを行う場合は **[auth!session!log\_external\_state](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証セッションで外部の状態をログに記録する)** を [Yes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/はい) に設定し、それ以外の場合は既定値の [No](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/いいえ) をそのまま使用します。

    d. **[auth!session!cookie\_attributes](認証セッションの Cookie の属性)** を、"HTTPOnly" に変更します。
3. SAML サービス プロバイダー -

    [Image: SAML サービス プロバイダー]

    ある。 **[auth!saml!sp\_entity\_id](認証 SAML SP エンティティ ID)** ボックスを、Microsoft Entra シングル サインオン構成の識別子 (エンティティ ID) として使用される URL に設定します。 `https://pulseweb.labb.info/saml/metadata` などです。

    b。 **[auth!saml!sp\_acs\_url](認証 SAML SP ACS URL)** を、Microsoft Entra シングル サインオン構成の応答 URL (Assertion Consumer Service URL) として使用されているものと同じ URL に設定します。 `https://pulseweb.labb.info/saml/consume` などです。

    c. **[auth!saml!idp](認証 SAML IDP)** で、前のステップで作成した**信頼できる ID プロバイダー**を選択します。

    d. [auth!saml!time\_tolerance](認証 SAML 許容時間) では、既定値の "5" 秒をそのまま使用します。

    え [auth!saml!nameid\_format](認証 SAML 名前 ID 形式) では、 **[unspecified](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/指定なし)** を選択します。

    f. ページの下部にある **[更新** ] を選択して、変更を適用します。

#### Pulse Secure Virtual Traffic Manager のテスト ユーザーを作成する

このセクションでは、Pulse Secure Virtual Traffic Manager で Britta Simon というユーザーを作成します。 [Pulse Secure Virtual Traffic Manager サポート チーム](mailto:support@pulsesecure.net)と連携し、Pulse Secure Virtual Traffic Manager プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Pulse Secure Virtual Traffic Manager のサインオン URL にリダイレクトされます。
- Pulse Secure Virtual Traffic Manager のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Pulse Secure Virtual Traffic Manager] タイルを選択すると、このオプションは Pulse Secure Virtual Traffic Manager のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pure-storage-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Pure Storage SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pure-storage-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-29
- Summary: Microsoft Entra ID と Pure Storage SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、Pure Storage SSO と Microsoft Entra ID を統合する方法について説明します。 Pure Storage SSO と Microsoft Entra ID を統合すると、次のことができます。

- Pure Storage SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Pure Storage SSO に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Pure Storage SSO のサブスクリプション。
- **[省略可能]** 署名証明書と暗号化アサーション機能を使用する場合は、 **precept create--certificate --key "cert-name"** を使用して、証明書と秘密キーを Pure Storage FlashArray にインポートする必要があります。 証明書の作成の優先モードを使用します。自己署名証明書の詳細については、 [Pure Storage SSO サポート チーム](mailto:security-solutions-support@purestorage.com) にお問い合わせください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Pure Storage SSO は、**SP と IDP** の両方から開始される SSO をサポートしています。

### ギャラリーから Pure Storage SSO を追加する

Microsoft Entra ID への Pure Storage SSO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Pure Storage SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「Pure Storage SSO**」と入力します。
4. 結果パネルから **Pure Storage SSO を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pure Storage SSO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Pure Storage SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Pure Storage SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Pure Storage SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pure Storage の SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Pure Storage SSO**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ARRAY-NAME>.purestorage.com/saml2/service-provider-metadata/<SSO_CONFIG_NAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ARRAY-NAME>.purestorage.com/login/saml2/sso/<SSO_CONFIG_NAME>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ARRAY-NAME>.purestorage.com/login/saml2/sso/<FQDN_of_Array_Name>`

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Pure Storage SSO アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングをご自分の SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の画像を示すスクリーンショット。]
8. その他に、Pure Storage SSO アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を以下に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 純度\_役割 | user.assignedroles |

    注意

    Microsoft Entra アプリで既定のロールを編集し、それぞれの値 **を "storage\_admin"、"ops\_admin"、"readonly"、"array\_admin"** として更新します。 詳細については、「**アプリ ロール UI - 値の更新に関するセクション」**および**「Microsoft Entra ロールへのユーザーとグループの割り当て - ロールの割り当てに関するセクション」[こちら](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)**を参照してください。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. [ **Pure Storage SSO のセットアップ** ] セクションで、要件に基づいて 1 つ以上の適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pure Storage SSO を構成する

1. Pure Storage SSO 企業サイトに管理者としてサインインします。
2. &gt;  &gt; セクションに移動し、ページの右上隅にある  ボタンを選択して SSO 構成を追加します。

    [Image: 構成のアカウント設定を示すスクリーンショット。]
3. [SAML2 SSO 構成] ページで次の手順を実行します。

    [Image: 構成ページを示すスクリーンショット。]

    1. **[名前**] ボックスに有効な名前を入力します。
    2. **エンティティ ID の値を**コピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスに貼り付けます。
    3. **アサーション コンシューマー URL を**コピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスに貼り付けます。
    4. **[エンティティ ID**] ボックスに、Microsoft Entra 管理センターからコピーした **Microsoft Entra 識別子**の値を貼り付けます。
    5. **[URL**] ボックスに、Microsoft Entra 管理センターからコピーした**ログイン URL を**貼り付けます。
    6. [ **メタデータ URL** ] ボックスに、Microsoft Entra 管理センターからコピーした **アプリのフェデレーション メタデータ URL** の値を貼り付けます。
    7. **[省略可能]** **署名資格情報**と**復号化資格情報**に、Pure Storage FlashArray にインポートされた**証明書名**を入力します。 **署名要求**と**暗号化アサーション**を切り替えて有効にします。
    8. Microsoft Entra アプリから検証証明書を取得し、FlashArray SSO Configuration for **Verification Certificate** フィールドで更新します。 [ドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on)に示されている手順に従って、関連する証明書を取得できます。
    9. **[保存] を選択します**。

        注意

        [アサーションを暗号化する] を有効にする場合にのみ、Microsoft Entra アプリ側でこれを実行します。 この**リンク**に従って、証明書をインポートし、[トークン暗号化](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption)を有効にします。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、サインイン フローを開始できる Pure Storage SSO のサインオン URL にリダイレクトします。
- Pure Storage SSO のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Pure Storage SSO に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Pure Storage SSO] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Pure Storage SSO に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/purecloud-by-genesys-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Genesys Cloud for Azure を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/purecloud-by-genesys-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID から Genesys Cloud for Azure に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Genesys Cloud for Azure と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使って、[Genesys Cloud for Azure](https://www.genesys.com) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Genesys Cloud for Azure でユーザーを作成する
- アクセスが不要になった場合に Genesys Cloud for Azure のユーザーを削除する
- Microsoft Entra ID と Genesys Cloud for Azure の間でユーザー属性の同期を維持します。
- Genesys Cloud for Azure でグループとグループ メンバーシップをプロビジョニングする
- Genesys Cloud for Azure への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/purecloud-by-genesys-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

Genesys Cloud for Azure は、次の[国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- PureCloud [組織](https://help.mypurecloud.com/?p=81984)。
- OAuth クライアントを作成する[アクセス許可](https://help.mypurecloud.com/?p=24360)があるユーザー。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Genesys Cloud for Azure の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Genesys Cloud for Azure を構成する

1. PureCloud 組織内に構成される [OAuth クライアント](https://help.mypurecloud.com/?p=188023)を作成します。
2. [OAuth クライアント](https://developer.mypurecloud.com/api/rest/authorization/use-client-credentials.html)を使用してトークンを生成します。
3. PureCloud 内でグループ メンバーシップを自動的にプロビジョニングする場合は、Microsoft Entra ID のグループと同じ名前で PureCloud にグループを [作成](https://help.mypurecloud.com/?p=52397) する必要があります。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Genesys Cloud for Azure を追加する

Microsoft Entra アプリケーション ギャラリーから Genesys Cloud for Azure を追加して、Genesys Cloud for Azure へのプロビジョニングの管理を開始します。 Genesys Cloud for Azure への SSO を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Genesys Cloud for Azure への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、TestApp でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Genesys Cloud for Azure の自動ユーザー プロビジョニングを構成するには、次のようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示す図。]
3. アプリケーションの一覧で **[Genesys Cloud for Azure]** を選択します。

    [Image: アプリケーションの一覧の Genesys Cloud for Azure のリンクを示す図。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションの **[テナントの URL]** と **[シークレット トークン]** のフィールドに、それぞれ Genesys Cloud for Azure API の URL と OAuth トークンを入力します。 API URL は、`{{API Url}}/api/v2/scim/v2`の PureCloud リージョンの API URL を使用して、として構成されます。 [ **テスト接続]** を選択して、Microsoft Entra ID が Genesys Cloud for Azure に接続できることを確認します。 接続できない場合は、この Genesys Cloud for Azure アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: スクリーンショットには、[管理者資格情報] ダイアログ ボックスが表示され、テナント U R L とシークレット トークンを入力できます。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Genesys Cloud for Azure に同期されるユーザー属性を確認します。 "**照合**" プロパティとして選択されている属性は、更新処理で Genesys Cloud for Azure のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Genesys Cloud for Azure API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Azure 向け Genesys Cloud に必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | ディスプレイ名 | 糸 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | phoneNumbers[type eq "work2"].value | 糸 |  |  |
    | phoneNumbers[type eq "work3"].value | 糸 |  |  |
    | フォンナンバー[タイプは "work4" に等しい].値 | 糸 |  |  |
    | 電話番号[タイプ eq "home"].値 | 糸 |  |  |
    | phoneNumbers[type eq "microsoftteams"].value | 糸 |  |  |
    | 役割 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | リファレンス |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:genesys:purecloud:2.0:User:externalIds[authority eq 'microsoftteams'].value | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:genesys:purecloud:2.0:User:externalIds[authority eq 'ringcentral'].value | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:genesys:purecloud:2.0:User:externalIds[authority eq 'zoomphone].value | 糸 |  |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Genesys Cloud for Azure に同期されるグループ属性を確認します。 "**照合**" プロパティとして選択されている属性は、更新処理で Genesys Cloud for Azure のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。 Genesys Cloud for Azure では、グループの作成や削除はサポートされておらず、グループの更新のみがサポートされています。

    | 特性 | タイプ | フィルター処理でサポートされます | Azure 向け Genesys Cloud に必要 |
    | --- | --- | --- | --- |
    | ディスプレイ名 | 糸 | ✓ | ✓ |
    | エクスターナルID | 糸 |  |  |
    | メンバー | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

- 09/10/2020 - エンタープライズ属性 **employeeNumber** のサポートが追加されました。
- 05/18/2021 - コア属性 **phoneNumbers[type eq "work2"]** 、**phoneNumbers[type eq "work3"]** 、**phoneNumbers[type eq "work4"]** 、**phoneNumbers[type eq "home"]** 、**phoneNumbers[type eq "microsoftteams"]** およびロールのサポートが追加されました。 また、カスタム拡張属性 **urn:ietf:params:scim:schemas:extension:genesys:purecloud:2.0:User:externalIds[authority eq ‘microsoftteams’]** 、**urn:ietf:params:scim:schemas:extension:genesys:purecloud:2.0:User:externalIds[authority eq ‘zoomphone]** 、**urn:ietf:params:scim:schemas:extension:genesys:purecloud:2.0:User:externalIds[authority eq ‘ringcentral’]** のサポートが追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/purecloud-by-genesys-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Genesys Cloud for Azure を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/purecloud-by-genesys-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Genesys Cloud for Azure の間でシングル サインオンを構成する方法について説明します。

この記事では、Genesys Cloud for Azure と Microsoft Entra ID を統合する方法について説明します。 Genesys Cloud for Azure と Microsoft Entra ID を統合すると、次のことができます。

- Genesys Cloud for Azure にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントで Genesys Cloud for Azure に自動的にサインイン (シングル サインオン) するように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Genesys Cloud for Azure でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Genesys Cloud for Azure では、**SP および IDP** Initiated SSO がサポートされます。
- Genesys Cloud for Azure では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/purecloud-by-genesys-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Genesys Cloud for Azure の追加

Microsoft Entra ID への Genesys Cloud for Azure の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Genesys Cloud for Azure を追加する必要があります。 この手順を実行するには、以下のステップに従ってください。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに **[Genesys Cloud for Azure]** と入力します。
4. 結果のパネルから **[Genesys Cloud for Azure]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Genesys Cloud for Azure 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使って、Genesys Cloud for Azure で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Genesys Cloud for Azure の関連ユーザーとの間にリンク関係を確立する必要があります。

Genesys Cloud for Azure に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**して、ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成**し、B.Simon を使用して Microsoft Entra シングル サインオンをテストします。
    2. **B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます** 。
2. **Genesys Cloud for Azure SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Genesys Cloud for Azure のテスト ユーザーの作成** - Genesys Cloud for Azure で B.Simon に対応するユーザーを作成し、Microsoft Entra のこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Azure portal で Microsoft Entra SSO を有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Genesys Cloud for Azure** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて**、シングル サインオン**を選択します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** Initiated モードで構成する場合は、次の手順を行います。

    ある。 **[識別子]** ボックスに、自分のリージョンに対応する URL を入力します。

    | 識別子 |
    | --- |
    | https://login.mypurecloud.com/saml |
    | https://login.mypurecloud.de/saml |
    | https://login.mypurecloud.jp/saml |
    | https://login.mypurecloud.ie/saml |
    | https://login.mypurecloud.com.au/saml |
    |  |

    b。 **[応答 URL]** ボックスに、自分のリージョンに対応する URL を入力します。

    | 応答 URL |
    | --- |
    | https://login.mypurecloud.com/saml |
    | https://login.mypurecloud.de/saml |
    | https://login.mypurecloud.jp/saml |
    | https://login.mypurecloud.ie/saml |
    | https://login.mypurecloud.com.au/saml |
    |  |
6. アプリケーションを **SP** 開始モードで構成する場合は、 **[追加の URL を設定します]** を選択して次の手順を実行します。

    **[サインオン URL]** ボックスに、自分のリージョンに対応する URL を入力します。

    | サインオン URL |
    | --- |
    | https://login.mypurecloud.com |
    | https://login.mypurecloud.de |
    | https://login.mypurecloud.jp |
    | https://login.mypurecloud.ie |
    | https://login.mypurecloud.com.au |
    |  |
7. Genesys Cloud for Azure アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
8. その他に、Genesys Cloud for Azure アプリケーションでは、次の表に示すとおり、いくつかの属性が SAML 応答で返されることが想定されています。 これらの属性も値が事前に設定されますが、必要に応じてそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザー.ユーザープリンシパルネーム |
    | 組織名 | `Your organization name` |
9. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Genesys Cloud for Azure のセットアップ]** セクションで、要件に基づいて適切な 1 つ以上の URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Genesys Cloud for Azure SSO の構成

1. 別の Web ブラウザー ウィンドウで、Genesys Cloud for Azure に管理者としてサインインします。
2. 上部の **[Admin] (管理)** を選択し、 **[Integrations] (統合)** の **[Single Sign-on] (シングル サインオン)** に移動します。

    [Image: [PureCloud Admin](PureCloud 管理) ウィンドウのスクリーンショット。ここで [Single Sign-on](シングル サインオン) を選択することができます。]
3. **[ADFS/Azure AD (Premium)]** タブに切り替えて、次の手順に従います。

    [Image: [Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) ページのスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[参照]** を選択して、ダウンロードした base 64 でエンコードされた証明書を **[ADFS 証明書]** にアップロードします。

    b。 **[ADFS 発行者の URI]** ボックスに、コピーした **Microsoft Entra 識別子**の値を貼り付けます。

    c. **[ターゲット URI]** ボックスに、コピーした**ログイン URL** の値を貼り付けます。

    d. **[証明書利用者 ID ]** の値については、Azure portal に移動し、**Genesys Cloud for Azure** アプリケーション統合ページで **[プロパティ]** タブを選択して、 **[アプリケーション ID]** の値をコピーします。 それを **[Relying Party Identifier] (証明書利用者識別子)** ボックスに貼り付けます。

    [Image: アプリケーション ID の値が表示される [プロパティ] ペインのスクリーンショット。]

    え **保存** を選択します。

#### Genesys Cloud for Azure のテスト ユーザーを作成する

Microsoft Entra ユーザーを Genesys Cloud for Azure にサインインできるようにするには、そのユーザーを Genesys Cloud for Azure にプロビジョニングする必要があります。 Genesys Cloud for Azure では、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順のようにします。**

1. 管理者として Genesys Cloud for Azure にログインします。
2. 上部にある **[管理者**] を選択し、[ユーザー**とアクセス許可**] の下の [**ユーザー**] に移動します。

    [Image: [PureCloud Admin](PureCloud 管理) ウィンドウのスクリーンショット。ここで [People](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) を選択することができます。]
3. **[People] (ユーザー)** ページで、 **[Add Person] (ユーザーの追加)** を選択します。

    [Image: ユーザーを追加できる [People](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) ページのスクリーンショット。]
4. **[Add People to the Organization] (組織へのユーザーの追加)** ダイアログボックスで、次の手順に従います。

    [Image: スクリーンショットは、説明されている値を入力できるページを示しています。]

    ある。 **[Full Name] (フル ネーム)** ボックスに、ユーザーの名前を入力します。 次に例を示します。**B.simon**。

    b。 **[Email] (メール)** ボックスに、ユーザーのメール アドレスを入力します。 たとえば、 **b.simon@contoso.com**と指定します。

    c. **を選択して**を作成します。

注

Genesys Cloud for Azure では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/purecloud-by-genesys-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Genesys Cloud for Azure サインオン URL にリダイレクトされます。
- Genesys Cloud for Azure のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Genesys Cloud for Azure に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Genesys Cloud for Azure] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Genesys Cloud for Azure に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/purelyhr-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に PurelyHR を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/purelyhr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PurelyHR の間にシングル サインオンを構成する方法について説明します。

この記事では、PurelyHR と Microsoft Entra ID を統合する方法について説明します。 PurelyHR をMicrosoft Entra ID と統合すると、次のことができます。

- PurelyHR にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って PurelyHR に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PurelyHR でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PurelyHR では、**SP と IDP** Initiated SSO がサポートされます。
- PurelyHR では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの PurelyHR の追加

Microsoft Entra ID への PurelyHR の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に PurelyHR を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**PurelyHR**」と入力します。
4. 結果のパネルから **[PurelyHR]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PurelyHR 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、PurelyHR に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと PurelyHR の関連ユーザーの間にリンク関係を確立する必要があります。

PurelyHR に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PurelyHR の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **B.Simon に対応する PurelyHR テストユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**PurelyHR**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_ID>.purelyhr.com/sso-consume`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_ID>.purelyhr.com/sso-initiate`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには、[PurelyHR クライアント サポート チーム](https://support.purelyhr.com/)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[PurelyHR のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PurelyHR の SSO の構成

1. 別の Web ブラウザー ウィンドウで、PurelyHR 企業サイトに管理者としてサインインします
2. ツール バーのオプションから **ダッシュボード** を開き **、[SSO 設定]** を選択します。
3. 以下の説明に従って、各ボックスに値を貼り付けます。

    [Image: シングルサインオンの設定]

    ある。 ダウンロードした **Certificate(Bas64)** をメモ帳で開き、証明書の値をコピーします。 コピーした値を **[X.509 Certificate]** ボックスに貼り付けます。

    b。 **[Idp Issuer URL] (Idp 発行者 URL)** ボックスに、コピーした **Microsoft Entra 識別子**を貼り付けます。

    c. **[Idp Endpoint URL] (IdP エンドポイント URL)** ボックスに、コピーした**ログイン URL** を貼り付けます。

    d. **[Auto-Create Users]** チェック ボックスをオンにして、PurelyHR への自動ユーザー プロビジョニングを有効にします。

    え [ **変更の保存]** を選択して設定を保存します。

#### PurelyHR のテスト ユーザーの作成

アプリケーションは、ジャスト イン タイムのユーザー プロビジョニングをサポートしているため、通常、この手順は必要ありません。 自動ユーザー プロビジョニングが有効になっていない場合は、以下の説明に従って手動でユーザーを作成できます。

Velpic SAML 企業サイトに管理者としてサインインし、次の手順に従います。

1. [管理] タブを選択し、[ユーザー] セクションに移動し、[新規] ボタンを選択してユーザーを追加します。

    [Image: ユーザーの追加]
2. **[Create New User] \(新しいユーザーの作成)** ダイアログ ページで、次の手順を実行します。

    [Image: ユーザー]

    ある。 **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名 B を入力します。

    b。 **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓 Simon を入力します。

    c. **[User Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー名)** テキストボックスに、B.Simon のユーザーを入力します。

    d. **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** ボックスに、B.Simon@contoso.com アカウントのメール アドレスを入力します。

    え その他の情報は省略可能です。必要に応じて入力してください。

    f. **[保存] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる PurelyHR サインオン URL にリダイレクトされます。
- PurelyHR のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した PurelyHR に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [PurelyHR] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した PurelyHR に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/puzzel-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Puzzel を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/puzzel-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-08-10
- Summary: Microsoft Entra ID から Puzzel にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Puzzel ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーを [Puzzel](https://www.puzzel.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされる機能

- Puzzel でユーザーを作成します。
- アクセスが不要になった場合に Puzzel のユーザーを削除します。
- Microsoft Entra ID と Puzzel の間でユーザー属性の同期を維持します。
- Puzzel に[シングル サインオンします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。
- OAuth2 クライアント資格情報付与認証がサポートされています。

### Prerequisites

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Puzzel のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- [Microsoft Entra ID と Puzzel の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra アプリケーション ギャラリーから Puzzel を追加する

Microsoft Entra アプリケーション ギャラリーから Puzzel を追加して、Puzzel へのプロビジョニングの管理を開始します。 SSO 用に Puzzel を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 3: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 4: Puzzel への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて Puzzel でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Puzzel の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Puzzel**] を選択します。

    [Image: アプリケーションの一覧の Puzzel リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. Microsoft Entra IDでプロビジョニングを構成する前に、Puzzel ポータルで OIDC クライアントを作成し、クライアント資格情報を生成します。 Microsoft Entra IDは、[プロビジョニング] タブで接続を設定するときにこれらの資格情報を使用します。クライアント資格情報を取得するには:

    1. `https://app.puzzel.com/settings` にサインインします。 **OIDC クライアント**を見つけて、[構成] を選択**します**。

        [Image: OIDC クライアントを示すスクリーンショット。]
    2. [ **OIDC クライアント構成]** ページで、右上隅にある **[+ 追加** ] を選択します。
    3. 次のように新しいクライアントを構成します。

        1. **[クライアント名]** に、わかりやすい名前 (例: `Microsoft Entra provisioning`) を入力します。
        2. 許可の種類として、[ **クライアント資格情報]** を選択し、[ **保存]** を選択します。
        3. [ **有効期間** ] タブを開き、[ **アクセス トークンの有効期間** ] と [ **更新トークンの有効期間** ] の両方を `3600`に設定します。 **保存**を選びます。

            [Image: [有効期間] タブを示すスクリーンショット。]
    4. クライアントの共有シークレットを生成します。

        1. [ **OIDC クライアント構成]** ページで、構成されているクライアント名を選択し、その行の最初のアイコン **(シークレット)** を選択します。

            [Image: OIDC クライアント構成ページを示すスクリーンショット。]
        2. [ **共有シークレット &gt; 追加]** を選択します。

            [Image: 共有シークレットを示すスクリーンショット。]
        3. [ **シークレットの生成]** を選択し、生成されたシークレット値をコピーして、セキュリティで保護された場所に格納します。 **保存**を選びます。
7. [承認方法] で、**認証方法**として **[OAuth2 クライアント資格情報の付与**] を選択します。 [ **テナント URL** ] フィールドに Puzzel **テナント URL を** 入力し、Puzzel ポータルから取得した **クライアント ID**、 **クライアント シークレット** 、 **およびトークン エンドポイント** を入力します。

    [Image: OAuth2 クライアント資格情報の付与のスクリーンショット。]
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から Puzzel に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Puzzel のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Puzzel API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | Attribute | タイプ | フィルター処理でサポートされます | Puzzel によって要求される |
    | --- | --- | --- | --- |
    | userName | String | ✓ | ✓ |
    | active | ブール値 |  |  |
    | displayName | String |  |  |
    | title | String |  |  |
    | emails[type eq "仕事"].value | String |  |  |
    | preferredLanguage | String |  |  |
    | name.givenName | String |  |  |
    | name.familyName | String |  |  |
    | name.formatted | String |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | String |  |  |
    | 電話番号[タイプ eq "携帯"].値 | String |  |  |
    | phoneNumbers[type eq "ファックス"].value | String |  |  |
    | externalId | String |  |  |
    | name.honorificPrefix | String |  |  |
    | name.honorificSuffix | String |  |  |
    | nickName | String |  |  |
    | userType | String |  |  |
    | ロケール | String |  |  |
    | timezone | String |  |  |
    | メール[タイプ eq "自宅"].値 | String |  |  |
    | emails[タイプ eq "その他"].値 | String |  |  |
    | 電話番号[タイプ eq "home"].値 | String |  |  |
    | phoneNumbers[種類 eq "その他"].value | String |  |  |
    | 電話番号[タイプイコール "ポケベル"].値 | String |  |  |
    | roles | String |  |  |
13. **[グループ]** を選びます。
14. [属性マッピング] セクションで、Microsoft Entra ID から Puzzel に同期されるグループ **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、Puzzel のグループを更新操作で照合するために使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | Attribute | タイプ | フィルター処理でサポートされます | Puzzel によって要求される |
    | --- | --- | --- | --- |
    | displayName | String | ✓ | ✓ |
    | externalId | String |  |  |
    | members | Reference |  |  |
15. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
16. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
17. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pwc-identity-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に PwC ID を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pwc-identity-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PwC Identity の間のシングル サインオンを構成する方法について説明します。

この記事では、PwC ID と Microsoft Entra ID を統合する方法について説明します。 PwC Identity と Microsoft Entra ID を統合すると、次のことができます。

- PwC Identity にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って PwC Identity に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PwC Identity でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PwC Identity では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから PwC Identity を追加する

Microsoft Entra ID への PwC Identity の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に PwC Identity を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**PwC Identity**」と入力します。
4. 結果パネルから **[PwC Identity]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PwC Identity 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、PwC Identity に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと PwC Identity の関連ユーザーとの間にリンク関係を確立する必要があります。

PwC Identity に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PwC Identity の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PwC Identity テストユーザーを作成 - PwC Identity 上で B.Simon に対応するユーザーを作成し、Microsoft Entra にてユーザーにリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**PwC Identity**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `urn:pwcid:saml:sp:p`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://login.pwc.com/openam/PWCIAuthConsumer/metaAlias/pwc/sp3`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://researchcreditsolution.pwc.com/`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[PwC Identity の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PwC Identity の SSO の構成

**PwC Identity** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [PwC Identity サポート チーム](https://www.pwc.com/us/en/services/tax/specialized-tax/research-development-credit.html)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PwC Identity のテスト ユーザーの作成

このセクションでは、PwC Identity で Britta Simon というユーザーを作成します。 [PwC Identity サポート チーム](https://www.pwc.com/us/en/services/tax/specialized-tax/research-development-credit.html)と連携し、PwC Identity プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる PwC ID サインオン URL にリダイレクトされます。
- PwC Identity のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [PwC ID] タイルを選択すると、このオプションは PwC ID のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pymetrics-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に pymetrics を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pymetrics-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と pymetrics 間にシングル サインオンを構成する方法について説明します。

この記事では、pymetrics と Microsoft Entra ID を統合する方法について説明します。 pymetrics と Microsoft Entra ID を統合すると、次のことができます:

- pymetrics にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って pymetrics に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- pymetrics でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- pymetrics は、**SP** によって開始される SSO をサポートします。 **IDP** によって開始されるフローを構成する必要がある場合は、[pymetrics のサポート](mailto:solutions-engineering@pymetrics.com)にお問い合わせください。
- pymetrics では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの pymetrics の追加

Microsoft Entra ID への pymetrics の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に pymetrics を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**pymetrics**」と入力します。
4. 結果のパネルから **[pymetrics]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### pymetrics 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、pymetrics に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと pymetrics の関連ユーザーとの間にリンク関係を確立する必要があります。

pymetrics に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **pymetrics の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **pymetrics テスト ユーザーを作成する** - pymetrics で B.Simon の対応ユーザーとして、Microsoft Entra のユーザー表現にリンクさせるため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**pymetrics**&gt;**シングル サインオン**を表示します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.pymetrics.com/saml2-sp/<CUSTOMERNAME>/<CUSTOMERNAME>/metadata/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.pymetrics.com/saml2-sp/<CUSTOMERNAME>/<CUSTOMERNAME>/?acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.pymetrics.com/saml2-sp/<CUSTOMERNAME>/<CUSTOMERNAME>/?sso`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[pymetrics クライアント サポート チーム](mailto:solutions-engineering@pymetrics.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. pymetrics アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次の表に、既定の属性の一覧を示します。 これらの属性は値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | UserFirstName | ユーザー.ファーストネーム |
    | ユーザー姓 | ユーザーの名字 |
    | ユーザーメールアドレス | ユーザー.ユーザープリンシパルネーム |
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[pymetrics のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### pymetrics の SSO の構成

**pymetrics** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [pymetrics サポート チーム](mailto:solutions-engineering@pymetrics.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### pymetrics のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを pymetrics に作成します。 pymetrics では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 pymetrics にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる pymetrics のサインオン URL にリダイレクトされます。
- pymetrics のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで pymetrics タイルを選択すると、このオプションは pymetrics のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/qiita-team-tutorial"} -->
## Microsoft Entra ID で Qiita Team for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/qiita-team-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Qiita Team の間でシングル サインオンを構成する方法について説明します。

この記事では、Qiita Team と Microsoft Entra ID を統合する方法について説明します。 Qiita Team を Microsoft Entra ID と統合すると、次のことができるようになります:

- Qiita Team にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Qiita Team に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Qiita Team のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Qiita Team では、**IDP** によって開始される SSO がサポートされます。
- Qiita Team では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Qiita Team を追加する

Microsoft Entra ID への Qiita Team の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Qiita Team を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Qiita Team**」と入力します。
4. 結果パネルから **[Qiita Team]** を選択し、そのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Qiita Team 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Qiita Team に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Qiita Team の関連ユーザーの間にリンク関係を確立する必要があります。

Qiita Team に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Qiita Team SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Qiita Team のテストユーザーを作成** - Microsoft Entra でのユーザー表現にリンクする、Qiita Team 上の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Qiita Team**&gt;**シングルサインオン**にアクセスしてください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.qiita.com/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.qiita.com/saml/consume`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Qiita Team クライアント サポート チーム](mailto:engineers+team@qiita.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Qiita Team アプリケーションでは、特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Qiita Team アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を下記に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | User.FirstName | ユーザーの名字 |
    | User.LastName | User.givenname |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Set up Qiita Team](Qiita Team のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Qiita Team SSO の構成

**Qiita Team** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Qiita Team サポート チーム](mailto:engineers+team@qiita.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Qiita Team テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Qiita Team に作成します。 Qiita Team では、Just-In-Time ユーザー プロビジョニングがサポートされます。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Qiita Team にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Qiita Team に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Qiita Team] タイルを選択すると、SSO を設定した Qiita Team に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/qliksense-enterprise-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Qlik Sense Enterprise Client-Managed を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/qliksense-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Qlik Sense Enterprise クライアントマネージドの間でシングル サインオンを構成する方法について説明します。

この記事では、Qlik Sense Enterprise Client-Managed と Microsoft Entra ID を統合する方法について説明します。 Qlik Sense Enterprise Client-Managed と Microsoft Entra ID を統合すると、次のことが可能になります。

- Qlik Sense Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Qlik Sense Enterprise に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

Qlik Sense Enterpriseには 2 つのバージョンがあります。 この記事では、クライアントで管理されるリリースとの統合について説明しますが、Qlik Sense Enterprise SaaS (Qlik Cloud バージョン) には別のプロセスが必要です。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Qlik Sense Enterprise でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者に加え、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができる。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Qlik Sense Enterprise では、**SP** によって開始される SSO がサポートされます。
- Qlik Sense Enterprise では、**ジャストインタイム プロビジョニング**がサポートされます。

### ギャラリーからの Qlik Sense Enterprise の追加

Microsoft Entra ID への Qlik Sense Enterprise の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Qlik Sense Enterprise を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Qlik Sense Enterprise**」と入力します。
4. 結果のパネルから **[Qlik Sense Enterprise]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Qlik Sense Enterprise に対する Microsoft Entra SSO を構成してテストする

**Britta Simon** というテスト ユーザーを使用して、Qlik Sense Enterprise に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Qlik Sense Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

Qlik Sense Enterprise に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Microsoft EntraQlik Sense Enterprise SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Qlik Sense Enterprise のテスト ユーザーの作成** - Qlik Sense Enterprise で Britta Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra でのユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Qlik Sense Enterprise** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、次のいずれかのパターンで URL を入力します。

    | 識別子 |
    | --- |
    | `https://<Fully Qualified Domain Name>.qlikpoc.com` |
    | `https://<Fully Qualified Domain Name>.qliksense.com` |

    b。 **[応答 URL]** ボックスに、 のパターンを使用して URL を入力します。

    `https://<Fully Qualified Domain Name>:443{/virtualproxyprefix}/samlauthn/`

    c. **[サインオン URL]** ボックスに、`https://<Fully Qualified Domain Name>:443{/virtualproxyprefix}/hub` のパターンを使用して URL を入力します。

    Note

    これらの値は実際の値ではありません。 これらの値は、この記事で後述する実際の識別子、応答 URL、サインオン URL で更新するか、 [Qlik Sense Enterprise クライアント サポート チーム](https://www.qlik.com/us/services/support) に連絡してこれらの値を取得してください。 URL の既定のポートは 443 ですが、組織のニーズに合わせてカスタマイズできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Qlik Sense Enterprise SSO の構成

1. Qlik Sense Qlik Management Console (QMC) に、仮想プロキシ構成を作成できるユーザーとして移動します。
2. QMC で、[ **仮想プロキシ** ] メニュー項目を選択します。

    [Image: [CONFIGURE SYSTEM](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成システム) から選択された仮想プロキシを示すスクリーンショット。]
3. 画面の下部にある **[新規作成]** ボタンを選択します。

    [Image: [Create new](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新規作成) オプションを示すスクリーンショット。]
4. [Virtual Proxies (仮想プロキシ)] 編集画面が表示されます。 画面の右側のメニューで、構成オプションを表示できます。

    [Image: [Properties](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロパティ) から選択された [Identification](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/識別) を示すスクリーンショット。]
5. [Identification (識別)] メニュー オプションを選択し、Azure 仮想プロキシ構成の識別情報を入力します。

    [Image: [Edit virtual proxy Identification](仮想プロキシ ID の編集) セクションのスクリーンショット。ここで、説明されている値を入力できます。]

    a. **[Description]** フィールドは、仮想プロキシ構成のフレンドリ名です。 値として説明を入力します。

    b。 **[Prefix]** フィールドでは、Microsoft Entra シングル サインオンで Qlik Sense に接続するための仮想プロキシ エンドポイントを指定します。 この仮想プロキシに一意のプレフィックス名を入力します。

    c. **[セッション非アクティブ タイムアウト (分)]** は、この仮想プロキシを経由する接続のタイムアウトです。

    d. **[Session cookie header name]** は、認証が成功した後にユーザーが受け取る Qlik Sense セッション用のセッション識別子を格納する Cookie 名です。 この名前は一意である必要があります。
6. [認証] メニュー オプションを選択して表示します。 [Authentication (認証)] 画面が表示されます。

    [Image: [Edit virtual proxy](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仮想プロキシの編集) の [Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) セクションのスクリーンショット。ここで、説明されている値を入力できます。]

    a. **[Anonymous access mode](匿名アクセス モード)** ドロップダウン リストでは、匿名ユーザーによる仮想プロキシを介した Qlik Sense へのアクセスを許可するかどうかを設定します。 既定のオプションは、 **[No anonymous user (匿名ユーザーを許可しない)]** です。

    b。 **[認証方法]** ドロップダウン リストでは、仮想プロキシで使用する認証スキームを設定します。 ドロップダウン リストから [SAML] を選択します。 その結果、さらにオプションが表示されます。

    c. **[SAML host URI]** フィールドに、ユーザーがこの SAML 仮想プロキシを介して Qlik Sense にアクセスする際に入力するホスト名を入力します。 ホスト名は、Qlik Sense サーバーの URI です。

    d. **[SAML entity ID]** に、[SAML host URI] フィールドに入力したのと同じ値を入力します。

    e. **[SAML IdP メタデータ]** に、**Microsoft Entra 構成からのフェデレーション メタデータの編集**に関するセクションで編集したファイルを指定します。 **IdP メタデータをアップロードする前に、このファイルを編集する必要があります**。Microsoft Entra ID と Qlik Sense サーバーの間で処理が正しく行われるように、ファイルの情報を削除してください。 **まだファイルを編集していない場合は、上記の手順に従ってください。** ファイルが編集されている場合は、[参照] ボタンを選択し、編集したメタデータ ファイルを選択して仮想プロキシ構成にアップロードします。

    f. これらは Microsoft Entra ID が Qlik Sense サーバーに送信する **UserID** を表します。 スキーマ リファレンス情報は、構成が終了した後に Azure アプリの画面から取得できます。 名前属性を使用するには、「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name`」と入力します。

    g. Microsoft Entra ID を使用して Qlik Sense サーバーに対して認証を行うときにユーザーにアタッチされるユーザー **ディレクトリ** の値を入力します。 ハードコーディングされた値は**角かっこ []** で囲む必要があります。 Microsoft Entra SAML アサーション内で送信される属性を使用するには、属性の名前をこのボックスに角かっこ**なし**で入力します。

    h. **[SAML signing algorithm]** で、仮想プロキシ構成用のサービス プロバイダー (この場合は Qlik Sense サーバー) 証明書の署名を設定します。 Microsoft Enhanced RSA and AES Cryptographic Provider を使用して生成された、信頼された証明書を Qlik Sense サーバーで使用する場合は、SAML 署名アルゴリズムを **[SHA-256]** に変更します。

    一. [SAML attribute mapping (SAML 属性マッピング)] セクションでは、セキュリティ規則での使用を目的とした、Qlik Sense への他の属性 (グループなど) の送信を許可します。
7. [負荷分散] メニュー オプション **を** 選択して表示します。 [Load balancing (負荷分散)] 画面が表示されます。

    [Image: [Virtual proxy edit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仮想プロキシの編集) 画面の [LOAD BALANCING](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/負荷分散) を示すスクリーンショット。ここで、[Add new server node](新しいサーバー ノードの追加) を選択できます。]
8. [ **新しいサーバー ノードの追加** ] ボタンを選択し、負荷分散のために Qlik Sense がセッションを送信するエンジン ノードまたはノードを選択し、[ **追加** ] ボタンを選択します。

    [Image: [Add server nodes to load balance on](負荷分散するサーバー ノードの追加) ダイアログのサーバーの追加ボタンを示すスクリーンショット。]
9. [詳細設定] メニュー オプションを選択して表示します。 [Advanced (詳細設定)] 画面が表示されます。

    [Image: [Edit virtual proxy](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仮想プロキシの編集) の [Advanced](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定) 画面のスクリーンショット。]

    ホストの許可リストでは、Qlik Sense サーバーへの接続時に受け入れられるホスト名を指定します。 **ユーザーが Qlik Sense サーバーへ接続する際に指定するホスト名を入力します。** ホスト名は、[SAML host URI (SAML ホスト URI)] の値から `https://` を除いたものです。
10. **[適用]** ボタンを選択します。

    [Image: [Apply](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/適用) ボタンのスクリーンショット。]
11. [OK] を選択して、仮想プロキシにリンクされているプロキシが再起動されたことを示す警告メッセージを受け入れます。

    [Image: [Apply changes to virtual proxy](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仮想プロキシに変更を適用する) 確認メッセージのスクリーンショット。]
12. 画面の右側に、[Associated items (関連項目)] メニューが表示されます。 [プロキシ] メニュー オプション **を** 選択します。

    [Image: [Associated items](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/関連項目) から選択された [Proxies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロキシ) のスクリーンショット。]
13. [Proxies (プロキシ)] 画面が表示されます。 下部にある **[リンク]** ボタンをクリックして、仮想プロキシにプロキシをリンクさせます。

    [Image: [Link](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/リンク) ボタンのスクリーンショット。]
14. この仮想プロキシ接続をサポートするプロキシ ノードを選択し、**[リンク]** ボタンを選択します。 リンク後、関連付けられているプロキシの下にプロキシが一覧表示されます。

    [Image: [Select proxy services](プロキシ サービスの選択) のスクリーンショット。]

    [Image: [Virtual proxy associated items](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仮想プロキシの関連項目) ダイアログ ボックスの [Associated proxies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/関連プロキシ) のスクリーンショット。]
15. 約 5 ~ 10 秒後に、更新された QMC メッセージが表示されます。 **[QMC の更新]** ボタンをクリックします。

16. QMC が更新されたら、[ **仮想プロキシ** ] メニュー項目を選択します。 新しい SAML 仮想プロキシのエントリが画面の表に表示されます。 仮想プロキシ エントリを 1 つ選択します。

    [Image: 1 件のエントリを含む [Virtual proxies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仮想プロキシ) のスクリーンショット。]
17. 画面の下部にある [SP メタデータのダウンロード] ボタンがアクティブになります。 **[SP メタデータのダウンロード]** ボタンを選択して、メタデータをファイルに保存します。

    [Image: [Download S P metadata](S P メタデータのダウンロード) ボタンのスクリーンショット。]
18. SP メタデータ ファイルを開きます。 **entityID** エントリと **AssertionConsumerService** エントリを確認します。 これらの値は、Microsoft Entra アプリケーション構成の**識別子**、**サインオン URL**、**応答 URL** に対応しています。 一致しない場合は Microsoft Entra アプリケーションの構成の **[Qlik Sense Enterprise のドメインと URL]** セクションにこれらの値を貼り付けて、Microsoft Entra アプリケーションの構成ウィザードで置換する必要があります。

    [Image: EntityDescriptor が表示されたプレーンテキスト エディターのスクリーンショット。entityID と AssertionConsumerService が強調表示されている。]

#### Qlik Sense Enterprise のテスト ユーザーの作成

Qlik Sense Enterprise は**ジャストインタイム プロビジョニング**をサポートしているため、ユーザーは SSO 機能を使用すると、Qlik Sense Enterprise の "USERS" リポジトリに自動的に追加されます。 加えて、クライアントは QMC を使用して UDC (User Directory Connector) を作成することにより、任意の LDAP (Active Directory など) から Qlik Sense Enterprise にユーザーを事前設定することができます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる Qlik Sense Enterprise のサインオン URL にリダイレクトされます。
- Qlik Sense Enterprise のサインオン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Qlik Sense Enterprise] タイルを選択すると、このオプションは Qlik Sense Enterprise のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/qmarkets-idea-innovation-management-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Qmarkets Idea & Innovation Management を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/qmarkets-idea-innovation-management-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Qmarkets Idea & Innovation Management の間のシングル サインオンを構成する方法について説明します。

この記事では、Qmarkets Idea & Innovation Management と Microsoft Entra ID を統合する方法について説明します。 Qmarkets Idea & Innovation Management と Microsoft Entra ID を統合すると、次のことができます。

- Qmarkets Idea & Innovation Management にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Qmarkets Idea & Innovation Management に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

Qmarkets Idea & Innovation Management は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Qmarkets Idea & Innovation Management でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Qmarkets Idea & Innovation Management では、**SP と IDP** によって開始されるSSOがサポートされます。
- Qmarkets Idea & Innovation Management では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Qmarkets Idea & Innovation Management の追加

Microsoft Entra ID への Qmarkets Idea & Innovation Management の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Qmarkets Idea & Innovation Management を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Qmarkets Idea & Innovation Management」と**入力します。
4. 結果パネルから **Qmarkets Idea & Innovation Management** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Qmarkets Idea & Innovation Management の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Qmarkets Idea & Innovation Management に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Qmarkets Idea & Innovation Management の関連ユーザーとの間にリンク関係を確立する必要があります。

Qmarkets Idea & Innovation Management に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Qmarkets Idea & Innovation Management の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. Qmarkets Idea & Innovation Management のテストユーザーを作成 - Qmarkets Idea & Innovation Management で B.Simon の役割に対応するユーザーを作り、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Qmarkets Idea & Innovation Management]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<app_url>/sso/saml2/metadata/qmarkets_sp_<endpoint_id>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<app_url>/sso/saml2/acs/qmarkets_sp_<endpoint_id>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<app_url>/sso/saml2/endpoint/qmarkets_sp_<endpoint_id>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Qmarkets Idea & Innovation Management クライアント サポート チーム](mailto:support@qmarkets.net) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Qmarkets Idea & Innovation Management SSO の構成

**Qmarkets Idea & Innovation Management** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Qmarkets Idea & Innovation Management サポート チーム](mailto:support@qmarkets.net)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Qmarkets Idea & Innovation Management のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Qmarkets Idea & Innovation Management に作成します。 Qmarkets Idea & Innovation Management では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Qmarkets Idea & Innovation Management にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Qmarkets Idea および Innovation Management のサインオン URL にリダイレクトされます。
- Qmarkets Idea & Innovation Management のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Qmarkets Idea & Innovation Management に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Qmarkets Idea & Innovation Management] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Qmarkets Idea & Innovation Management に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/qminder-tutorial"} -->
## Microsoft Entra ID で Qminder for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/qminder-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-28
- Summary: Microsoft Entra ID と Qminder の間にシングル サインオンを構成する方法について説明します。

この記事では、Qminder と Microsoft Entra ID を統合する方法について説明します。 Qminder を Microsoft Entra ID と統合すると、次のことができます。

- Qminder にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Qminder に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Qminder のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Qminder では、**SP Initiated SSO と IDP Initiated SSO** がサポートされています。

### ギャラリーから Qminder を追加する

Microsoft Entra ID への Qminder の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Qminder を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Qminder**」と入力します。
4. 結果パネルから **[Qminder]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Qminder 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Qminder に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Qminder の関連ユーザーとの間にリンク関係を確立する必要があります。

Qminder に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Qminder の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Qminderテストユーザーを作成** - Microsoft Entra ID の B.Simon にリンクされた Qminder 内の対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Qminder**&gt;**シングルサインオン**にアクセスしてください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクション上では、アプリケーションは事前に構成されており、必要な URL は Microsoft Entra によって既に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、URL として「`https://dashboard.qminder.com/login/`」と入力します。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Qminder の SSO を構成する

**Qminder** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Qminder サポート チーム](mailto:support@qminder.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Qminder のテスト ユーザーを作成する

このセクションでは、Qminder で B.Simon というユーザーを作成します。 [Qminder サポート チーム](mailto:support@qminder.com)と協力して、Qminder プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Qminder のサインオン URL にリダイレクトします。
- Qminder のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Qminder に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Qminder] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Qminder に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/qprism-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に QPrism を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/qprism-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と QPrism の間にシングル サインオンを構成する方法について説明します。

この記事では、QPrism と Microsoft Entra ID を統合する方法について説明します。 QPrism を Microsoft Entra ID を統合すると、次のことができます。

- QPrism にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って QPrism に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- QPrism でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- QPrism では、**SP** Initiated SSO がサポートされます。

### ギャラリーから QPrism を追加する

Microsoft Entra ID への QPrism の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に QPrism を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**QPrism**」と入力します。
4. 結果のパネルから **[QPrism]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### QPrism 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、QPrism に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと QPrism の関連ユーザーとの間にリンク関係を確立する必要があります。

QPrism に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **QPrism の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **QPrism テスト ユーザーの作成** - Microsoft Entra の B.Simon に対応するユーザーを QPrism に作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**QPrism**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集のスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer domain>.qmyzone.com/login`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer domain>.qmyzone.com/metadata.php`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[QPrism クライアント サポート チーム](mailto:qsupport-ce@quatrro.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### QPrism SSO の構成

**QPrism** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [QPrism サポート チーム](mailto:qsupport-ce@quatrro.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### QPrism のテスト ユーザーの作成

このセクションでは、QPrism で Britta Simon というユーザーを作成します。 [QPrism サポート チーム](mailto:qsupport-ce@quatrro.com)と連携して、QPrism プラットフォームにユーザーを追加してください。 SSO を使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる QPrism のサインオン URL にリダイレクトされます。
- QPrism のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [QPrism] タイルを選択すると、このオプションは QPrism のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/qradar-soar-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に QRadar SOAR を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/qradar-soar-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と QRadar SOAR の間にシングル サインオンを構成する方法について説明します。

この記事では、QRadar SOAR を Microsoft Entra ID と統合する方法について説明します。 QRadar SOAR は、シンプルな自動化、プロセス標準化、既存のセキュリティ ツールとの統合により、インシデント対応を高速化することでアナリスト エクスペリエンスを向上させます。 QRadar SOAR を Microsoft Entra ID と統合すると、次のことができます。

- QRadar SOAR にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して QRadar SOAR に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で QRadar SOAR 向けの Microsoft Entra シングル サインオンを構成してテストします。 QRadar SOAR では、**SP**開始のシングルサインオンと**IDP**開始のシングルサインオンの両方がサポートされます。

### [前提条件]

Microsoft Entra ID を QRadar SOAR と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- QRadar SOAR でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから QRadar SOAR アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから QRadar SOAR を追加する

Microsoft Entra アプリケーション ギャラリーから QRadar SOAR を追加して、QRadar SOAR でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**QRadar SOAR**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<CustomerName>.domain.extension/<ID>` |
    | `https://<CustomerName>.domain.extension` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CustomerName>.domain.extension/<ID>` |
    | `https://<CustomerName>.domain.extension` |
6. **SP** Initiated SSO を構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CustomerName>.domain.extension/<ID>` |
    | `https://<CustomerName>.domain.extension` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [QRadar SOAR クライアント サポート チーム](mailto:mysphelp@us.ibm.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **QRadar SOAR のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

### QRadar SOAR SSO を構成する

**QRadar SOAR** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [QRadar SOAR サポート チーム](mailto:mysphelp@us.ibm.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### QRadar SOAR テスト ユーザーを作成する

このセクションでは、QRadar SOAR で Britta Simon というユーザーを作成します。 [QRadar SOAR サポート チーム](mailto:mysphelp@us.ibm.com)と協力して、QRadar SOAR プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる QRadar SOAR のサインオン URL にリダイレクトされます。
- QRadar SOAR のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した QRadar SOAR に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [QRadar SOAR] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した QRadar SOAR に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/qreserve-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に QReserve を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/qreserve-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と QReserve の間にシングル サインオンを構成する方法についてご確認ください。

この記事では、QReserve と Microsoft Entra ID を統合する方法について説明します。 QReserve を Microsoft Entra ID と統合すると、次のことができます。

- QReserve にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して QReserve に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な QReserve のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- QReserve では、 **SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。
- QReserve では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの QReserve の追加

Microsoft Entra ID への QReserve の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに QReserve を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「QReserve**」と入力します。
4. 結果パネルから **QReserve** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### QReserve 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、QReserve に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと QReserve の関連ユーザーとの間にリンク関係を確立する必要があります。

QReserve に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **QReserve SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **QReserve テスト ユーザーの作成** - QReserve で B.Simon に相当するユーザーを作成し、Microsoft Entra におけるそのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**QReserve**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://my.qreserve.com`
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **QReserve のセットアップ** ]セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成をコピーするのに適切な U R L を示すスクリーンショットです。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### QReserve SSO の構成

**QReserve** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [QReserve サポート チーム](mailto:hello@qreserve.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### QReserve テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを QReserve に作成します。 QReserve では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 QReserve にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる QReserve サインオン URL にリダイレクトされます。
- QReserve のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した QReserve に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [QReserve] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した QReserve に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/qualaroo-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Qualaroo を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/qualaroo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Qualaroo の間にシングル サインオンを構成する方法について説明します。

この記事では、Qualaroo と Microsoft Entra ID を統合する方法について説明します。 Qualaroo を Microsoft Entra ID と統合すると、次のことができます:

- Qualaroo にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Qualaroo に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Qualaroo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Qualaroo では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Qualaroo の追加

Microsoft Entra ID への Qualaroo の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Qualaroo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Qualaroo**」と入力します。
4. 結果のパネルから **[Qualaroo]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Qualaroo 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Qualaroo に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Qualaroo の関連ユーザーとの間にリンク関係を確立する必要があります。

Qualaroo に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Qualaroo の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Qualaroo テストユーザーを作成** - Microsoft Entra のユーザーとして B.Simon に対応する人物を Qualaroo に作成しリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Qualaroo**&gt;**Single のサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Qualaroo のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Qualaroo の SSO の構成

**Qualaroo** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Qualaroo サポート チーム](mailto:support@proprofs.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Qualaroo のテスト ユーザーの作成

このセクションでは、Qualaroo で Britta Simon というユーザーを作成します。 [Qualaroo サポート チーム](mailto:support@proprofs.com)と連携して、Qualaroo プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Qualaroo に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Qualaroo] タイルを選択すると、SSO を設定した Qualaroo に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/qualtrics-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Qualtrics を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/qualtrics-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Qualtrics 間にシングル サインオンを構成する方法について説明します。

この記事では、Qualtrics と Microsoft Entra ID を統合する方法について説明します。 Qualtrics と Microsoft Entra ID を統合すると、次のことができます。

- 誰が Qualtrics にアクセスできるかを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Qualtrics に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Qualtrics サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Qualtrics では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。
- Qualtrics では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Qualtrics の追加

Microsoft Entra ID への Qualtrics の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Qualtrics を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Qualtrics**」と入力します。
4. 結果から **[Qualtrics]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Qualtrics の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Qualtrics に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Qualtrics の関連ユーザーとの間にリンク関係を確立する必要があります。

Qualtrics に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. Microsoft Entra SSO を構成して、ユーザーがこの機能を使用できるようにします。
    1. B.Simon で Microsoft Entra のシングル サインオンをテストする Microsoft Entra テスト ユーザーを作成します。
    2. B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます。
2. Qualtrics の SSO を構成して、アプリケーション側でシングル サインオン設定を構成します。
    1. Qualtrics のテスト ユーザーを作成し、B.Simon に対応するユーザーを Qualtrics に作成して、Microsoft Entra の B.Simon にリンクさせます。
3. SSO のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Qualtrics** アプリケーション統合ページに移動し、[**管理**] セクションを見つけます。 **[シングル サインオン]** を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML でシングル サインオンをセットアップします]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[SAML によるシングル サインオンのセットアップ]** ページで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    ある。 **[識別子]** ボックスに、次の形式で URL を入力します。

    `https://< DATACENTER >.qualtrics.com`

    b。 **[応答 URL]** ボックスに、次の形式で URL を入力します。

    `https://< DATACENTER >.qualtrics.com/login/v1/sso/saml2/default-sp`

    c. **[リレー状態]** ボックスに、次の形式で URL を入力します。

    `https://< brandID >.< DATACENTER >.qualtrics.com`
6. アプリケーションを **SP** 開始モードで構成する場合は、 **[追加の URL を設定します]** を選択して次の手順を実行します。

    **[サインオン URL]** ボックスに、次の形式で URL を入力します。

    `https://< brandID >.< DATACENTER >.qualtrics.com`

    注意

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、応答 URL、リレー状態でこれらの値を更新します。 これらの値を取得するには、[Qualtrics クライアント サポート チーム](https://www.qualtrics.com/support/)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、コピー アイコンを選択して **[アプリのフェデレーション メタデータ URL]** をコピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Qualtrics の SSO の構成

Qualtrics 側でシングル サインオンを構成するには、コピーした**アプリのフェデレーション メタデータ URL** を [Qualtrics サポート チーム](https://www.qualtrics.com/support/)に送信します。 サポート チームは、SAML SSO 接続が両方の側で正しく設定されていることを確認します。

#### Qualtrics のテスト ユーザーの作成

Qualtrics では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 追加のアクションはありません。 Qualtrics にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Qualtrics のサインオン URL にリダイレクトされます。
- Qualtrics のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Qualtrics に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Qualtrics] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Qualtrics に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/quantum-workplace-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Quantum Workplace を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/quantum-workplace-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Quantum Workplace の間のシングル サインオンを構成する方法について説明します。

この記事では、Quantum Workplace と Microsoft Entra ID を統合する方法について説明します。 Quantum Workplace を Microsoft Entra ID と統合すると、次のことができます。

- Quantum Workplace にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Quantum Workplace に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Quantum Workplace でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Quantum Workplace では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーから Quantum Workplace を追加する

Microsoft Entra ID への Quantum Workplace の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Quantum Workplace を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Quantum Workplace**」と入力します。
4. 結果のパネルから **[Quantum Workplace]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Quantum Workplace 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Quantum Workplace 用に Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Quantum Workplace の関連ユーザーとの間にリンク関係を確立する必要があります。

Quantum Workplace 用に Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Quantum Workplace の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Quantum Workplace のテスト ユーザーの作成** - Quantum Workplace で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Quantum Workplace**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://auth.quantumworkplace.com/Account/Login`
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Quantum Workplace の SSO の構成

**Quantum Workplace** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Quantum Workplace サポート チーム](mailto:support@quantumworkplace.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Quantum Workplace のテスト ユーザーの作成

このセクションでは、Quantum Workplace で Britta Simon というユーザーを作成します。 [Quantum Workplace サポート チーム](mailto:support@quantumworkplace.com)と協力して、Quantum Workplace プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Quantum Workplace のサインオン URL にリダイレクトされます。
- Quantum Workplace のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Quantum Workplace に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Quantum Workplace] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Quantum Workplace に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/quarem-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Quarem を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/quarem-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-20
- Summary: Microsoft Entra ID から Quarem に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Quarem ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーを [自動的に Quarem](https://www.quarem.com) にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされる機能

- Quarem でユーザーを作成します。
- アクセスが不要になったら、Quarem のユーザーを削除します。
- Microsoft Entra ID と Quarem の間でユーザー属性の同期を維持します。
- Quarem への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/quarem-tutorial) (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者アクセス許可がある Quarem のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントの計画を立てる

- [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- [Microsoft Entra ID と Quarem の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID でのプロビジョニングをサポートするように Quarem を構成する

Microsoft Entra ID でのプロビジョニングをサポートするための Quarem の構成については、Quarem のサポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Quarem を追加する

Quarem へのプロビジョニングの管理を始めるには、Microsoft Entra アプリケーション ギャラリーから Quarem を追加します。 SSO のために Quarem を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Quarem への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てに基づいて Quarem のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Quarem に対する自動ユーザー プロビジョニングを構成するには、次のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Quarem**] を選択します。

    [Image: アプリケーションの一覧の [Quarem] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Quarem テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Quarem に接続できることを確認します。 接続に失敗した場合は、Quarem アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Quarem に同期されるユーザー **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Quarem のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Quarem API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Quarem によって必要とされる |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | 名前.名 | 糸 |  | ✓ |
    | 名前.姓 | 糸 |  | ✓ |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | phoneNumbers[type eq "ファックス"].value | 糸 |  |  |
    | 役割 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |  |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から Quarem に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Quarem のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Quarem に必要とされる |
    | --- | --- | --- | --- |
    | 表示名 | 糸 | ✓ | ✓ |
    | エクスターナルID | 糸 |  |  |
    | メンバー | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/quarem-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Quarem を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/quarem-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Quarem の間でシングル サインオンを構成する方法について説明します。

この記事では、Quarem と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Quarem を統合すると、次のことができます。

- Quarem へのアクセスを持つユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Quarem に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Quarem でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Quaremは、**SP**および**IDP**によって開始されるSSOの両方をサポートします。

### ギャラリーから Quarem を追加する

Microsoft Entra ID への Quarem の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Quarem を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Quarem**」と入力します。
4. 結果パネルから **[Quarem** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Quarem の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Quarem に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Quarem の関連ユーザーとの間にリンク関係を確立する必要があります。

Quarem に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Quarem SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Quarem テストユーザーの作成** - Quarem で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーとして B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Quarem**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリは既に Microsoft Entra と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://na.quarem.net`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Quarem SSO の構成

**Quarem** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Quarem サポート チーム](mailto:clientservices@quarem.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Quarem テスト ユーザーの作成

このセクションでは、Quarem で B.Simon というユーザーを作成します。 [Quarem サポート チーム](mailto:clientservices@quarem.com)と協力して、Quarem プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Quarem のサインオン URL にリダイレクトします。
- Quarem のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Quarem に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Quarem] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Quarem に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/questetra-bpm-suite-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Questetra BPM Suite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/questetra-bpm-suite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Questetra BPM Suite の間にシングル サインオンを構成する方法について説明します。

この記事では、Questetra BPM Suite と Microsoft Entra ID を統合する方法について説明します。 Questetra BPM Suite と Microsoft Entra ID を統合すると、次のことができます。

- Questetra BPM Suite にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Questetra BPM Suite に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Questetra BPM Suite でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Questetra BPM Suite では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの Questetra BPM Suite の追加

Microsoft Entra ID への Questetra BPM Suite の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Questetra BPM Suite を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Questetra BPM Suite**」と入力します。
4. 結果パネルから **Questetra BPM Suite** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Questetra BPM Suite 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Questetra BPM Suite に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Questetra BPM Suite の関連ユーザーとの間にリンク関係を確立する必要があります。

Questetra BPM Suite に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Questetra BPM Suite の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Questetra BPM Suite のテストユーザーを作成 - Questetra BPM Suite 内で B.Simon に相当するユーザーを作成し、それを Microsoft Entra の B.Simon にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Questetra BPM Suite**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.questetra.net/`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.questetra.net/saml/SSO/alias/bpm`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値は**、Questetra BPM Suite** 企業サイトの **SP Information** セクションから取得できます。これについては、記事の後半で説明するか、[Questetra BPM Suite クライアント サポート チーム](https://support.questetra.com/support-service/)にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Questetra BPM Suite のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Questetra BPM Suite SSO を構成する

1. 別の Web ブラウザー ウィンドウで、 **Questetra BPM Suite** 企業サイトに管理者としてサインインします。
2. 上部のメニューで、[ **システム設定]** を選択します。

    [Image: Questetra BPM Suite 企業サイトから選択されたシステム設定を示すスクリーンショット。]
3. **SingleSignOnSAML** ページを開くには、**SSO (SAML)** を選択します。

    [Image: S S O (SAML) が選択されているスクリーンショット。]
4. **Questetra BPM Suite** 企業サイトの **SP 情報**セクションで、次の手順を実行します。

    ある。 **ACS URL を**コピーし、Azure portal の [**基本的な SAML 構成**] セクションの **[サインオン URL**] ボックスに貼り付けます。

    b。 **エンティティ ID を**コピーし、Azure portal の [**基本的な SAML 構成**] セクションの **[識別子**] ボックスに貼り付けます。
5. **Questetra BPM Suite** 企業サイトで、次の手順を実行します。

    [Image: シングル サインオンの構成]

    ある。 [ **シングル サインオンを有効にする] を選択します**。

    b。 **エンティティ ID** ボックスに、**Microsoft Entra Identifier** の値を貼り付けます。

    c. [ **サインイン ページ URL** ] ボックスに、 **ログイン URL** の値を貼り付けます。

    d. [ **サインアウト ページ URL** ] ボックスに、 **ログアウト URL** の値を貼り付けます。

    え **NameID 形式**のテキスト ボックスに、「`urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress`」と入力します。

    f. Azure portal からダウンロードした **Base-64** でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、[ **検証証明書** ] ボックスに貼り付けます。

    ジー **[保存] を選択します**。

#### Questetra BPM Suite テスト ユーザーの作成

このセクションの目的は、Questetra BPM Suite で Britta Simon というユーザーを作成することです。

**Questetra BPM Suite で Britta Simon というユーザーを作成するには、次の手順に従います。**

1. Questetra BPM Suite 企業サイトに管理者としてサインインします。
2. [ **システム設定] &gt; [ユーザー一覧] &gt; [新しいユーザー]** に移動します。
3. [新規ユーザー] ダイアログで、次の手順を実行します。

    [Image: テスト ユーザーの作成]

    ある。 [**名前**] ボックスに、ユーザー のbritta.simon@contoso.comを入力します。

    b。 [**電子メール**] ボックスに、ユーザー のbritta.simon@contoso.comを入力します。

    c. [ **パスワード** ] ボックスに、ユーザーの **パスワード** を入力します。

    d. [ **新しいユーザーの追加] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Questetra BPM Suite のサインオン URL にリダイレクトされます。
- Questetra BPM Suite のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Questetra BPM Suite] タイルを選択すると、このオプションは Questetra BPM Suite のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/quickhelp-tutorial"} -->
## Microsoft Entra ID で QuickHelp for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/quickhelp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と QuickHelp の間にシングル サインオンを構成する方法について説明します。

この記事では、QuickHelp と Microsoft Entra ID を統合する方法について説明します。 QuickHelp を Microsoft Entra ID と統合すると、次のことができます。

- QuickHelp にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して QuickHelp に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- QuickHelp でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- QuickHelp では、**SP** によって開始される SSO がサポートされます。
- QuickHelp では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの QuickHelp の追加

Microsoft Entra ID への QuickHelp の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に QuickHelp を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**QuickHelp**」と入力します。
4. 結果のパネルから **[QuickHelp]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### QuickHelp 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、QuickHelp に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと QuickHelp の関連ユーザーとの間にリンク関係を確立する必要があります。

QuickHelp に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **QuickHelp SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **QuickHelp テスト ユーザーの作成** - QuickHelpでのB.Simonに相当するユーザーを作成し、それをMicrosoft Entra上のユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**QuickHelp**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://auth.quickhelp.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://quickhelp.com/<ROUTE_URL>`

    注

    サインオン URL の値は実際の値ではありません。 実際の Sign-On URL で値を更新します。 組織の QuickHelp 管理者や BrainStorm Client Success マネージャーに値を問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[QuickHelp のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### QuickHelp SSO の構成

1. QuickHelp 企業サイトに管理者としてサインインします。
2. 上部のメニューで、[管理者] を選択 **します**。

    [Image: Brainstorm の [Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) メニュー項目のスクリーンショット]
3. **[QuickHelp 管理**] メニューの [**設定]** を選択します。

    [Image: [QuickHelp Admin](QuickHelp の管理) メニューの [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) が選択されている画面のスクリーンショット]
4. **[認証設定]** を選択します。
5. **[認証設定]** ページで、次の手順を実行します。

    [Image: 説明されている値を入力できる [Authentication Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証設定) ページのスクリーンショット]

    ある。 **SSO 型**として **WSFederation** を選びます。

    b。 ダウンロードした Azure メタデータ ファイルをアップロードするには、[ **参照**] を選択してファイルに移動し、最後に [ **メタデータのアップロード**] を選択します。

    c. **[電子メール]** ボックスに「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`」と入力します。

    d. **[名]** ボックスに「`type http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`」と入力します。

    え **[姓]** ボックスに「`type http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname`」と入力します。

    f. **アクション バー**で、[**保存]** を選択します。

#### QuickHelp のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを QuickHelp に作成します。 QuickHelp では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 QuickHelp にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる QuickHelp のサインオン URL にリダイレクトされます。
- QuickHelp のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [QuickHelp] タイルを選択すると、このオプションは QuickHelp のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/qumucloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Qumu Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/qumucloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Qumu Cloud の間にシングル サインオンを構成する方法について説明します。

この記事では、Qumu Cloud と Microsoft Entra ID を統合する方法について説明します。 Qumu Cloud を Microsoft Entra ID を統合すると、次のことができます。

- Qumu Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Qumu Cloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Qumu Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Qumu Cloud では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。
- Qumu Cloud では、**Just-In-Time** ユーザー プロビジョニングがサポーされます。

### ギャラリーからの Qumu Cloud の追加

Microsoft Entra ID への Qumu Cloud の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Qumu Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Qumu Cloud**」と入力します。
4. 結果のパネルから **[Qumu Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Qumu Cloud 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Qumu Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Qumu Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Qumu Cloud に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Qumu Cloud の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Qumu Cloud のテストユーザーの作成 - Microsoft Entra における B.Simon のユーザー表現にリンクされる、Qumu Cloud 上の対応するユーザーを作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Qumu Cloud**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.qumucloud.com/saml/SSO`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.qumucloud.com/saml/SSO`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.qumucloud.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Qumu Cloud クライアント サポート チーム](mailto:support@qumu.com)にご連絡ください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Qumu Cloud アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ **ユーザー属性] ダイアログを** 開きます。

    [Image: スクリーンショットには、[編集] アイコンが選択された [ユーザー属性] が表示されます。]
8. その他に、Qumu Cloud アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:2.5.4.42 | ユーザー.ファーストネーム |
    | urn:oid:2.5.4.4 | ユーザーの名字 |
    | urn:oid:0.9.2342.19200300.100.1.3 | ユーザーのメールアドレス |
    | urn:oid:0.9.2342.19200300.100.1.1 | ユーザー.ユーザープリンシパルネーム |

    ある。 [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: スクリーンショットには、[新しい要求の追加] オプションを含むユーザー要求が表示されます。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    え **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **保存** を選択します。
9. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Qumu Cloud のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Qumu Cloud の SSO の構成

**Qumu Cloud** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Qumu Cloud サポート チーム](mailto:support@qumu.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Qumu Cloud のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Qumu Cloud に作成します。 Qumu Cloud では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Qumu Cloud にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Qumu Cloud クライアント サポート チーム](mailto:support@qumu.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Qumu Cloud のサインオン URL にリダイレクトされます。
- Qumu Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Qumu Cloud に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Qumu Cloud] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Qumu Cloud に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rackspacesso-tutorial"} -->
## Microsoft Entra ID で Rackspace SSO for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rackspacesso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Rackspace SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、Rackspace SSO と Microsoft Entra ID を統合する方法について説明します。 Rackspace SSO と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra IDでRackspace SSOへのアクセス権を管理します。
- ユーザーが自分の Microsoft Entra アカウントを使って Rackspace SSO に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Rackspace SSO でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Rackspace SSO では、 **IDP** Initiated SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Rackspace SSO を追加する

Microsoft Entra IDへの Rackspace SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Rackspace SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「Rackspace SSO**」と入力します。
4. 結果パネルから **Rackspace SSO を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 Microsoft 365 wizards.

### Rackspace SSO 用に Microsoft Entra SSO を構成してテストする

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Rackspace SSO で Microsoft Entra のシングル サインオンを構成し、テストします。 Rackspace でシングル サインオンを使用する場合、Rackspace ユーザーは Rackspace ポータルに初めてサインインしたときに自動的に作成されます。

Rackspace SSO で Microsoft Entra のシングル サインオンを構成してテストするには、次の手順を実行する必要があります:

1. **Configure Microsoft Entra SSO**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Rackspace SSO の構成**- アプリケーション側で単一 Sign-On 設定を構成します。
    1. **Rackspace Control Panel** で属性マッピングを設定して、Rackspace ロールを Microsoft Entra ユーザーに割り当てます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDエンタープライズアプリRackspace SSOシングルサインオンにアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [**基本的な SAML 構成]** セクションで、**URL** からダウンロードできる[サービス プロバイダー メタデータ ファイル](https://login.rackspace.com/federate/sp.xml)をアップロードし、次の手順を実行します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: スクリーンショットは、[メタデータ ファイルのアップロード] リンクを含む基本的な S A M L 構成を示しています。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: スクリーンショットは、ファイルを選択してアップロードできるダイアログ ボックスを示しています。]

    c. メタデータ ファイルが正常にアップロードされると、必要な URL が自動的に設定されます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

このファイルは、必要な ID フェデレーション構成設定を設定するために Rackspace にアップロードされます。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Rackspace SSO の構成

**Rackspace SSO** 側でシングル サインオンを構成するには:

1. ID プロバイダーを Control Panel
2. その手順に従って以下を実行します。
    1. 新しい ID プロバイダーを作成します。
    2. ユーザーがサインイン時に会社を特定するために使用するメール ドメインを指定します。
    3. Azure control panelからダウンロードした **Federation Metadata XML** をアップロードします。

これにより、Azureと Rackspace が接続するために必要な基本的な SSO 設定が正しく構成されます。

#### Rackspace control panelで属性マッピングを設定する

Rackspace では、 **属性マッピング ポリシー** を使用して、Rackspace の役割とグループをシングル サインオン ユーザーに割り当てます。 **属性マッピング ポリシー**は、Microsoft Entra SAML 要求を Rackspace に必要なユーザー構成フィールドに変換します。 その他のドキュメントについては、Rackspace [属性マッピングの基本に関するドキュメントを参照してください](https://docs.rackspace.com/docs/id-federation-map-policies-permissions-cloud)。 いくつかの考慮事項があります。

- Microsoft Entra グループを使用してさまざまなレベルの Rackspace accessを割り当てる場合は、Azure **Rackspace SSO** シングル サインオン設定で Groups 要求を有効にする必要があります。 その後、 **属性マッピング ポリシー** を使用して、これらのグループを目的の Rackspace ロールとグループと照合します。

    [Image: [グループ] 要求の設定を示すスクリーンショット。]
- 既定では、Microsoft Entra IDは SAML 要求内の Microsoft Entra グループの UID とグループの名前を送信します。 ただし、on-premises Active DirectoryをMicrosoft Entra IDに同期する場合は、グループの実際の名前を送信するオプションがあります。

    [Image: グループのクレーム名設定を示すスクリーンショット。]

属性 **マッピング ポリシー** の例を次に示します。

1. Rackspace ユーザーの名前を `user.name` SAML 要求に設定します。 任意の要求を使用できますが、ユーザーの電子メール アドレスを含むフィールドに設定するのが最も一般的です。
2. グループ名またはグループ UID で Microsoft Entra グループを突き合わせることによって、Rackspace のロールである `admin` と `billing:admin` をユーザーに設定します。  フィールドの`"{0}"`の`roles`が使用され、`remote`ルール式の結果に置き換えられます。
3. `"{D}"`*default 置換*を使用して、Rackspace が SAML exchangeで標準および既知の SAML 要求を検索して追加の SAML フィールドを取得できるようにします。

```yaml
--- 
mapping:
    rules:
    - local:
        user:
          domain: "{D}"
          name: "{At(http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name)}"
          email: "{D}"
          roles:
              - "{0}"
          expire: "{D}"
      remote:
          - path: |
              (
                if (mapping:get-attributes('http://schemas.microsoft.com/ws/2008/06/identity/claims/groups')='7269f9a2-aabb-9393-8e6d-282e0f945985') then ('admin', 'billing:admin') else (),
                if (mapping:get-attributes('http://schemas.microsoft.com/ws/2008/06/identity/claims/groups')='MyAzureGroup') then ('admin', 'billing:admin') else ()
              )
            multiValue: true
  version: RAX-1
```

ヒント

ポリシー ファイルの編集する際は YAML 構文が検証されるテキスト エディターを必ず使用してください。

その他の例については、Rackspace [属性マッピングの基本ドキュメント](https://docs.rackspace.com/docs/id-federation-map-policies-permissions-cloud) を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Rackspace SSO に自動的にサインインします。
- Microsoft My Appsを使用できます。 My Appsで [Rackspace SSO] タイルを選択すると、SSO を設定した Rackspace SSO に自動的にサインインします。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。

**Rackspace SSO** シングル サインオン設定の **[検証**] ボタンを使用することもできます。

[Image: SSO 検証ボタンを示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/radancys-employee-referrals-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Radancy の従業員紹介を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/radancys-employee-referrals-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Radancy's Employee Referrals の間でシングル サインオンを構成する方法について説明します。

この記事では、Radancy の従業員紹介を Microsoft Entra ID と統合する方法について説明します。 Radancy's Employee Referrals を Microsoft Entra ID と統合すると、次のことができます。

- Radancy's Employee Referrals にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Radancy's Employee Referrals に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Radancy の従業員紹介でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Radancy の Employee Referrals では、**SPおよびIDPにより開始されたSSO**をサポートしています。
- Radancy の従業員紹介では、 **Just In Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Radancy の従業員紹介を追加する

Microsoft Entra ID への Radancy's Employee Referrals の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Radancy's Employee Referrals を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Radancy の従業員紹介**」と入力します。
4. 結果パネルから **Radancy の従業員紹介を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Radancy's Employee Referrals 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Radancy の Employee Referrals に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Radancy's Employee Referrals の関連ユーザーとの間にリンク関係を確立する必要があります。

Radancy's Employee Referrals に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Radancy の Employee Referrals SSO を構成**する - アプリケーション側でシングル サインオン設定を構成します。
    1. **Radancy の Employee Referrals テスト ユーザーの作成** - Radancy の Employee Referrals で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**Radancy の従業員紹介**&gt;**シングルサインオン**にブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-domain>.auth.1brd.com/saml/sp`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-domain>.auth.1brd.com/saml/callback`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-domain>.1brd.com/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Radancy の Employee Referrals クライアント サポート チーム](mailto:support@firstbird.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Radancy の従業員紹介アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: トークン属性の構成の画像を示すスクリーンショット。]
8. その他に、Radancy の従業員紹介アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名（ファーストネーム） | ユーザー.ファーストネーム |
    | last\_name | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. [ **Radancy の従業員紹介の設定** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Radancy の従業員紹介 SSO の構成

1. Radancy の従業員紹介 Web サイトに管理者としてログインします。
2. **アカウント設定**&gt;**認証**&gt;**シングルサインオン**に移動します。
3. **[SAML IdP Metadata Configuration]\(SAML IdP メタデータ構成\)** セクションで、次の手順を実行します。

    [Image: フェデレーション メタデータをアップロードする方法を示すスクリーンショット。]

    1. **[エンティティ ID**] ボックスに、コピーした **Microsoft Entra 識別子**の値を貼り付けます。
    2. **[SSO-service URL**] ボックスに、コピーした**ログイン URL** の値を貼り付けます。
    3. [ **署名証明書** ] ボックスに、ダウンロードした **フェデレーション メタデータ XML** ファイルを貼り付けます。
    4. **構成を保存** し、セットアップを確認します。

    注

    SSO オプションをコントラクトに含める必要があります。

#### Radancy の従業員紹介テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Radancy の従業員紹介に作成します。 Radancy の従業員紹介では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Radancy の従業員紹介にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Radancy の Employee Referrals のサインオン URL にリダイレクトされます。
- Radancy の従業員紹介のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Radancy の従業員紹介に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Radancy の従業員紹介] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Radancy の従業員紹介に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/radiant-iot-portal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用にラディアント IOT ポータルを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/radiant-iot-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Radiant IOT Portal の間にシングル サインオンを構成する方法について説明します。

この記事では、放射 IOT ポータルと Microsoft Entra ID を統合する方法について説明します。 Radiant IOT Portal は、IOT 追跡テクノロジに基づく資産追跡とアカウンタビリティのソリューションのために連邦政府と民間のお客様によって使用されます。 Radiant IOT Portal と Microsoft Entra ID を統合すると、次のことができます:

- Radiant IOT Portal にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Radiant IOT Portal に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Radiant IOT Portal 用に Microsoft Entra のシングル サインオンを構成してテストします。 Radiant IOT Portal は、**SP** Initiated シングル サインオンと **Just In Time** ユーザー プロビジョニングをサポートします。

### [前提条件]

Microsoft Entra ID と Radiant IOT Portal を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Radiant IOT Portal でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを始める前に、Microsoft Entra ギャラリーから Radiant IOT Portal アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Radiant IOT Portal を追加する

Microsoft Entra アプリケーション ギャラリーから Radiant IOT Portal を追加して、Radiant IOT Portal でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Radiant IOT Portal**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<SUBDOMAIN>.radiantrfid.com/VATServer/` |
    | `https://<SUBDOMAIN>.radiantrfid.com/VATPortal/` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<SUBDOMAIN>.radiantrfid.com/VATPortal/Saml2AuthenticationModule/acs` |
    | `https://<SUBDOMAIN>.radiantrfid.com/VATServer/Saml2AuthenticationModule/acs` |

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.radiantrfid.com/VATPortal/?cn=<CustomerName>&id=<ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Radiant IOT Portal のサポート チーム](mailto:support@radiantrfid.com)までご連絡ください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. Radiant IOT Portal アプリケーションでは特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. さらに、Radiant IOT Portal アプリケーションでは、いくつかの追加の属性が SAML 応答で返されます。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザーのメールアドレス |
    | ユーザーID | ユーザー.ユーザープリンシパルネーム |
    | グループ | ユーザー.グループ |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Radiant IOT Portal の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Radiant IOT Portal の SSO を構成する

**Radiant IOT Portal** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[Radiant IOT Portal サポート チーム](mailto:support@radiantrfid.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Radiant IOT Portal のテスト ユーザーを作成する

このセクションでは、Radiant IOT Portal に B.Simon というユーザーを作成します。 Radiant IOT Portal は Just In Time ユーザー プロビジョニングをサポートします。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Radiant IOT Portal にユーザーがまだ存在していない場合、通常認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できるラディアント IOT ポータルのサインオン URL にリダイレクトされます。
- Radiant IOT Portal のサインオン URL に直接移動し、そこからサインオン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [放射 IOT ポータル] タイルを選択すると、このオプションは、放射 IOT ポータルのサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/raketa-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Raketa を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/raketa-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Raketa の間のシングル サインオンを構成する方法について説明します。

この記事では、Raketa と Microsoft Entra ID を統合する方法について説明します。 Raketa を Microsoft Entra ID と統合すると、次のことが可能になります。

- Raketa にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Raketa に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Raketa でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Raketa では、**SP** によって開始される SSO がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Raketa を追加

Microsoft Entra ID への Raketa の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Raketa を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[Microsoft Entra ギャラリーを参照する]** セクションで、検索ボックスに「*Raketa*」と入力します。
4. **[Raketa]** を選択します。 **[Raketa]** ペインで、名前を指定して **[作成]** を選択します。

### Raketa に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Raketa に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Raketa の関連ユーザー間にリンク関係を確立する必要があります。

Raketa に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Raketa SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Raketa のテスト ユーザーの作成** - Raketa で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Raketa** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。

    [Image: rkt_5]
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. **[識別子 (エンティティ ID)]** および **[サインオン URL]** テキスト ボックスに、URL `https://raketa.travel/` を入力します。
    2. **[応答 URL]** ボックスに、`https://raketa.travel/sso/acs?clientId=<CLIENT_ID>` のパターンを使用して URL を入力します。

    [Image: rkt_6]

    Note

    応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、[Raketa クライアント サポート チーム](mailto:help@raketa.travel)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。
7. **[Raketa のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    1. **[ログイン URL]** – 認証システムにユーザーをリダイレクトするために使用される承認 Web ページの URL。
    2. **[Microsoft Entra 識別子]** – Microsoft Entra 識別子。
    3. **[ログアウト URL]** – ログアウト後にユーザーをリダイレクトするために使用される Web ページの URL。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Raketa SSO の構成

**Raketa** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Raketa サポート チーム](mailto:help@raketa.travel)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Raketa テスト ユーザーの作成

このセクションでは、Raketa で B.Simon というユーザーを作成します。 [Raketa サポート チーム](mailto:help@raketa.travel)と協力して、Raketa プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Raketa のサインオン URL にリダイレクトされます。
- Raketa Sign-on URLに直接アクセスし、そこからログインフローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Raketa] タイルを選択すると、このオプションは Raketa のサインオン URL にリダイレクトされます。 詳細については、[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rally-software-tutorial"} -->
## Microsoft Entra ID で Rally Software for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rally-software-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Rally Software の間にシングル サインオンを構成する方法について説明します。

この記事では、Rally Software と Microsoft Entra ID を統合する方法について説明します。 Rally Software と Microsoft Entra ID を統合すると、次のことができます。

- Rally Software にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Rally Software に自動的にサインインできるように設定します。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) 対応の Rally Software サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Rally Software では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Rally Software の追加

Microsoft Entra ID への Rally Software の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Rally Software を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに **[Rally Software]** と入力します。
4. 結果のパネルから **[Rally Software]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Rally Software 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Rally Software に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Rally Software の関連ユーザーとの間にリンク関係を確立する必要があります。

Rally Software に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Rally Software SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Rally Software のテスト ユーザーの作成** - Microsoft Entra での B.Simon の表現にリンクする「Rally Software」の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Rally Software**&gt;**シングルサインオン**を開きます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TENANT_NAME>.rally.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TENANT_NAME>.rally.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Rally Software クライアント サポート チーム](https://help.rallydev.com/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Rally Software のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Rally Software SSO の構成

1. **Rally Software** テナントにログインします。
2. 上部のツール バーで、[ **セットアップ**] を選択し、[サブスクリプション] を選択 **します**。

    [Image: サブスクリプション]
3. **[アクション**] ボタンを選択します。 ツールバーの上部の右側にある **[サブスクリプションの編集]** を選択します。
4. [ **サブスクリプション** ] ダイアログ ページで、次の手順を実行し、[ **保存して閉じる**] を選択します。

    [Image: 認証]

    a Authentication のドロップダウン リストから、**[Rally or SSO authentication]** を選択します。

    b。 **[Identity provider URL] (ID プロバイダー URL)** テキストボックスに **Microsoft Entra 識別子**の値を貼り付けます。

    c. **[SSO ログアウト]** テキストボックスに **[ログアウト URL]** の値を貼り付けます。

#### Rally Software テスト ユーザーの作成

Microsoft Entra ユーザーがサインインできるように、Microsoft Entra ユーザー名を使って、Rally Software アプリケーションにユーザーをプロビジョニングする必要があります。

**ユーザー プロビジョニングを構成するには、次の手順を実行します。**

1. Rally Software テナントにサインインします。
2. &gt;] に移動し、[**+ 新規追加]** を選択します。

    [Image: ユーザー]
3. [新しいユーザー] ボックスに名前を入力し、[ **詳細と共に追加]** を選択します。
4. **[Create User]** セクションで、次の手順に従います。

    [Image: ユーザーの作成]

    a **[User Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー名)** ボックスに、ユーザーの氏名 (**BrittSimon** など) を入力します。

    b。 **[E-mail address](電子メール アドレス)** ボックスに、ユーザーの電子メール アドレスを入力します (この例では brittasimon@contoso.com)。

    c. **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名を入力します (例: **Britta**)。

    d. **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (例: **Simon**)。

    え **保存して閉じる** を選択します。

    注

    他の Rally Software ユーザー アカウントの作成ツールまたは Rally Software から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Rally Software のサインオン URL にリダイレクトされます。
- Rally Software のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Rally Software] タイルを選択すると、Rally Software のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/raumfurraum-tutorial"} -->
## Microsoft Entra ID でのシングルサインオン用に raum]für[raum を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/raumfurraum-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と raum]für[raum 間にシングル サインオンを構成する方法について学習します。

この記事では、raum]für[raum と Microsoft Entra ID を統合する方法について説明します。 raum]für[raum を Microsoft Entra ID と統合すると、次のことができます。

- raum]für[raum にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って raum]für[raum に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- raum]für[raum でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- raum]für[raum では、**SP および IDP** Initiated SSO がサポートされています。
- raum]für[raum では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから raum]für[raum を追加する

Microsoft Entra ID への raum]für[raum の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに raum]für[raum を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**raum]für[raum**」と入力します。
4. 結果のパネルから **[raum]für[raum]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### raum]für[raum 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、raum]für[raum で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと raum]für[raum の関連ユーザーとの間にリンク関係を確立する必要があります。

raum]für[raum で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **raumfurraum の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **raumfurraum テスト ユーザーの作成** - raum]für[raum で B.Simon に対応するユーザーを作成し、Microsoft Entra ユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**raum]für[raum**&gt;**シングル サインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `<CUSTOMER_NAME>.raumfuerraum.de` |
    | `<CUSTOMER_NAME>.rfr.md.intra` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 応答 URL |
    | --- |
    | `https://<CUSTOMER_NAME>.raumfuerraum.de` |
    | `https://<CUSTOMER_NAME>.rfr.md.intra` |
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://<CUSTOMER_NAME>.raumfuerraum.de/saml.php` |
    | `https://<CUSTOMER_NAME>.rfr.md.intra/saml.php` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得する場合は、[raumfurraum クライアント サポート チーム](mailto:it@mediadialog.de)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[raum]für[raum CRM](raum]für[raum の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### raumfurraum の SSO を構成する

**raum]für[raum** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [raum\]für\[raum サポート チーム](mailto:it@mediadialog.de)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### raumfurraum のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを raum]für[raum に作成します。 raum]für[raum では、Just-In-Time ユーザー プロビジョニングがサポートされており、これは既定で有効になっています。 このセクションにはアクション項目はありません。 raum]für[raum にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- **このアプリケーションをテスト**を選択すると、Azure ポータルでこのオプションは raum]für[raum サインオン URL にリダイレクトされ、サインインフローを開始できます。
- raum]für[raum のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した raum]für[raum に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで raum]für[raum] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインイン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した raum]für[raum] に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/reach-360-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Reach 360 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/reach-360-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Reach 360 の間でシングル サインオンを構成する方法について説明します。

この記事では、Reach 360 と Microsoft Entra ID を統合する方法について説明します。 Reach 360 と Microsoft Entra ID を統合すると、次のことができます。

- Reach 360 にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Reach 360 に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Reach 360 でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Reach 360 は、**SP および IDP** による SSO の両方をサポートします。
- Reach 360 では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Reach 360 を追加する

Microsoft Entra ID への Reach 360 の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Reach 360 を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Reach 360**」と入力します。
4. 結果パネルから **Reach 360** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Reach 360 の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Reach 360 に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Reach 360 の関連ユーザーとの間にリンク関係を確立する必要があります。

Reach 360 で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Reach 360 SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Reach 360 テスト ユーザーの作成** - Reach 360 で B.Simon の代替ユーザーを作成し、そのユーザーを Microsoft Entra のユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Reach 360**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    Ａ。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://reach360.com/sso/saml2/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://reach360.com/sso/saml2`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer_TenantName>.reach360.com/`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 [Reach 360 サポート チーム](mailto:enterprise@articulate.com) にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Reach 360 アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、Reach 360 ではこれがユーザーの objectid にマップされると想定されています。 そのため、リストから **user.objectid** 属性を使用するか、適切な属性値を使用して、組織の構成に基づいて **名前識別子の形式** を **永続的** に更新し、[ **保存]** を選択します。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. 上記に加えて、Reach 360 アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | グループ | ユーザー.グループ |
    | メール | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. [ **Reach 360 のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Reach 360 SSO の構成

1. Reach 360 企業サイトに管理者としてログインします。
2. **管理**&gt;**Settings**&gt;**SSO** に移動します。
3. **SSO** セクションで、次の手順を実行します。

    [Image: スクリーンショットは、構成を示しています。]

    1. **[Idp SSO URL**] フィールドに、Microsoft Entra 管理センターからコピーした**ログイン URL を**貼り付けます。
    2. [ **Idp Issuer URI** ] フィールドに、 **Microsoft Entra** 管理センターからコピーした Microsoft Entra 識別子を貼り付けます。
    3. ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容を **IDP SIGNATURE CERTIFICATE** テキストボックスに貼り付けます。
    4. SSO 設定を保存した後に生成された Reach 360 テナントから**対象ユーザー URI** の値をコピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成]** セクションの **[識別子**] ボックスに貼り付けます。
    5. **保存** を選択します。

#### Reach 360 テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Reach 360 に作成します。 Reach 360 では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Reach 360 にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Reach 360 のサインオン URL にリダイレクトされます。
- Reach 360 のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Reach 360 に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Reach 360] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Reach 360 に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/readcube-papers-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ReadCube Papers を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/readcube-papers-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ReadCube Paper の間にシングル サインオンを構成する方法について説明します。

この記事では、ReadCube Papers と Microsoft Entra ID を統合する方法について説明します。 ReadCube Papers を Microsoft Entra ID と統合すると、次のことができます:

- ReadCube Papers にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ReadCube Papers に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ReadCube Papers でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ReadCube Papers では、**SP** Initiated SSO がサポートされます。
- ReadCube Papers では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ReadCube Papers の追加

Microsoft Entra ID への ReadCube Papers の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ReadCube Papers を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**ReadCube Papers**」と入力します。
4. 結果パネルで **[ReadCube Papers]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ReadCube Papers 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ReadCube Papers と一緒に Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ReadCube Papers の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を ReadCube Papers と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ReadCube Papers SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ReadCube Papers のテスト ユーザーを作成し、**Microsoft Entra のユーザー表現にリンクさせる B.Simon の対応者として設定します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**ReadCube Papers**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. **[Reply URL (ACS URL)](応答 URL (ACS URL))** テキスト ボックスに、URL として「`https://connect.liblynx.com/saml/module.php/saml/sp/saml2-acs.php/dsrsi`」と入力します。
    2. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.readcube.com`

        [Image: [SAML 構成] ウィンドウの設定例を示すスクリーンショット。]
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ReadCube Papers SSO の構成

**ReadCube Papers** 側でシングル サインオンを構成するには、 **[アプリのフェデレーション メタデータ URL]** を [ReadCube Papers サポート チーム](mailto:sso-support@readcube.com)に送る必要があります。 SAML SSO 接続が両方の側で正しく機能するように、この設定を変更します。

#### ReadCube Papers のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを ReadCube Papers に作成します。 ReadCube Papers では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 ReadCube Papers にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

注

テストする前に、[ReadCube Papers のサポートチーム](mailto:sso-support@readcube.com)に、SSO が ReadCube 側で設定されていることを確認してください。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ReadCube Papers のサインオン URL にリダイレクトされます。
- ReadCube Papers のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリ ポータルで [ReadCube Papers] タイルを選択すると、このオプションは ReadCube Papers のサインオン URL にリダイレクトされます。 マイ アプリ ポータルの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/real-links-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Real Links を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/real-links-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-20
- Summary: Microsoft Entra ID から Real Links に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Real Links と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Real Links](https://www.reallinks.io) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Real Links でユーザーを作成する
- アクセスが不要になった場合に Real Links のユーザーを削除する
- Microsoft Entra ID と Real Links の間でユーザー属性の同期を維持する
- Real Links への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/real-links-tutorial) (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Real Links](https://www.reallinks.io/) サブスクリプション - すべてのレベルに自動ユーザー プロビジョニングが含まれます。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Real Links の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Real Links を構成する

Real Links プラットフォームでプロビジョニングを構成するには、 [Real Links サポート チーム](mailto:support@reallinks.io) に連絡し、SCIM-v2 プロビジョニングの詳細を要求する必要があります。 これには以下が含まれます。

- お使いのプラットフォームのテナントの URL
- 一意のシークレットトークン

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Real Links を追加する

Microsoft Entra アプリケーション ギャラリーから Real Links を追加して、Real Links へのプロビジョニングの管理を開始します。 SSO のために Real Links を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Real Links への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて Real Links でユーザーやグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Real Links の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**にアクセスする

    [Image: [エンタープライズ アプリケーション] ブレードを示す図。]
3. アプリケーションの一覧で [ **リアル リンク**] を選択します。

    [Image: アプリケーションの一覧の [実際のリンク] リンクを示す図。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブを示す図。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、実際のリンクテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Real Links に接続できることを確認します。 接続に失敗した場合は、Real Links アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Real Links に同期されるユーザー **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Real Links のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Real Links API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Real Links で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:real-links:2.0:User:firstName | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:real-links:2.0:User:lastName | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:real-links:2.0:User:メールアドレス | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:real-links:2.0:User:phoneNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:real-links:2.0:User:title | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:real-links:2.0:User:preferredLanguage | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:real-links:2.0:User:organization | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:real-links:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:real-links:2.0:User:division | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:real-links:2.0:User:location | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:real-links:2.0:User:countryCode | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/real-links-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Real Links を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/real-links-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Real Links の間でシングル サインオンを構成する方法について説明します。

この記事では、Real Links と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Real Links を統合すると、次のことができます。

- Real Links にアクセスするユーザーを Microsoft Entra ID で管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Real Links に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Real Links でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Real Links は、SP **によって開始される SSO** をサポートします。
- Real Links は [自動ユーザープロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/real-links-provisioning-tutorial)をサポートしています。

### ギャラリーからの Real Links の追加

Microsoft Entra ID への Real Links の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Real Links を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 「**ギャラリーから追加**」セクションで、検索ボックスに「**Real Links**」と入力します。
4. 結果パネルから **Real Links** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Real Links の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Real Links に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Real Links の関連ユーザーとの間にリンク関係を確立する必要があります。

Real Links で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Real Links SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Real Links のテスト ユーザーの作成** - Real Links で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Real Links**&gt;**Single のサインオンに**移動します。
3. [**シングル サインオン方法の選択]** ページで、[**SAML**] を選択する。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    SAML の基本的な構成の編集
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    ある。 [**識別子 (エンティティ ID)** テキスト ボックスに、次のパターンを使用して値を入力します: `urn:amazon:cognito:sp:<SUBDOMAIN>`

    b。 [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.reallinks.io`

    手記

    これらの値は実際の値ではありません。 実際の識別子とサインオン URL でこれらの値を更新します。 これらの値 [取得するには、Real Links クライアント サポート チーム](mailto:support@reallinks.io) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Real Links の SSO の構成

Real Links **側** でシングル サインオンを構成するには、**アプリフェデレーション メタデータ URL** を Real Links サポート チーム に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Real Links のテスト ユーザーの作成

このセクションでは、Real Links で Britta Simon というユーザーを作成します。 [Real Links サポート チームの](mailto:support@reallinks.io) と連携して、Real Links プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Real Links のサインオン URL にリダイレクトされます。
- Real Links のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Real Links] タイルを選択すると、このオプションは Real Links のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/recnice-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Recnice を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/recnice-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から Recnice に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法についてご確認ください。

この記事では、自動ユーザー プロビジョニングを構成するために Recnice と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを利用して、[Recnice](https://recnice.com) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Recnice でユーザーを作成します。
- アクセスが不要になったら、Recnice のユーザーを削除します。
- Microsoft Entra ID と Recnice の間でユーザー属性の同期を維持します。
- Recnice でグループとグループ メンバーシップをプロビジョニングする。
- Recnice への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある Recnice のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Recnice の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Recnice を構成する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Recnice を構成する前に、シークレット トークンとテナント URL を知っている必要があります。

1. Recnice 管理コンソールにサインインします。 **アカウント** を選択します。

    [Image: Recnice アカウント ページのスクリーンショット。]
2. **SCIM キー**の値をコピーします。 この値は、Recnice アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: SCIM API キーのスクリーンショット。]
3. **テナント URL** の値: `https://scim.recnice.com/scim/`。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Recnice を追加する

Microsoft Entra アプリケーション ギャラリーから Recnice を追加して、Recnice へのプロビジョニングの管理を開始します。 既に SSO のために Recnice を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Recnice への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループ割り当てに基づいて、Recnice でユーザーまたはグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Recnice の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Recnice** を選択します。

    [Image: アプリケーション一覧の Recnice のリンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Recnice テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Recnice に接続できることを確認します。 接続に失敗した場合は、Recnice アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニング] プロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Recnice に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Recnice のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Recnice API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Recnice で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | タイトル | 糸 |  | ✓ |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | 優先言語 | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | name.formatted | 糸 |  | ✓ |
    | addresses[type eq "work"].formatted | 糸 |  | ✓ |
    | addresses[type eq "work"].streetAddress | 糸 |  | ✓ |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  | ✓ |
    | addresses[type eq "work"].region | 糸 |  | ✓ |
    | addresses[type eq "work"].postalCode | 糸 |  | ✓ |
    | addresses[type eq "work"].country | 糸 |  | ✓ |
    | externalId | 糸 |  | ✓ |
    | roles | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  | ✓ |
12. 左側のパネルで **[属性マッピング** ] を選択し、[グループ] を選択します。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Recnice に同期されるグループ属性を確認します。 **照合**プロパティとして選択されている属性は、更新処理で Recnice のグループの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Recnice で必須 |
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
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/recognize-tutorial"} -->
## Microsoft Entra ID で Recognize for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/recognize-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Recognize の間でシングル サインオンを構成する方法について説明します。

この記事では、Recognize と Microsoft Entra ID を統合する方法について説明します。 Recognize と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID 内で Recognize へのアクセス権を持つユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Recognize に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、無料でアカウントを作成 [できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効なサブスクリプションを認識します。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Recognize では、**SP** 開始 SSO がサポートされます。

### ギャラリーから Recognize を追加する

Microsoft Entra ID への Recognize の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Recognize を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Recognize**」と入力します。
4. 結果パネルから **Recognize** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Recognize の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Recognize に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Recognize の関連ユーザーとの間にリンク関係を確立する必要があります。

Recognize で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Recognize SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Recognize テストユーザーの作成** - Recognize で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の表現にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**認識する**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [**基本的な SAML 構成**] セクションで、サービス プロバイダー メタデータ ファイル がある場合は、次の手順を実行します。

    手記

    **サービス プロバイダーメタデータファイル**は、記事の「**Recognize Single Sign-On の構成**」セクションから取得します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイル] をアップロードする

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    メタデータ ファイルの[Image: choose metadata file]choose metadata fileを選択

    c. メタデータ ファイルが正常にアップロードされると、**識別子** 値が [基本的な SAML 構成] セクションに自動的に設定されます。

    [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://recognizeapp.com/<YOUR_DOMAIN>/saml/sso`

    手記

    **識別子**の値が自動的に設定されない場合は、記事の「**Recognize Single Sign-On の構成**」セクションで後述する SSO 設定セクションからサービス プロバイダー メタデータ URL を開いて識別子の値を取得します。 サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[Recognize クライアント サポート チーム](mailto:support@recognizeapp.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **Recognize** のセットアップ セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Recognize SSO の構成

1. 別の Web ブラウザー ウィンドウで、Recognize テナントに管理者としてサインインします。
2. 右上隅にある [メニュー] を選択 **します**。 **[Company Admin]** に移動します。

    [Image: スクリーンショットには、[設定] メニューから [会社の管理者] が選択されています。]
3. 左側のナビゲーション ウィンドウで、**[設定]** を選択します。

    [Image: スクリーンショットには、ナビゲーション ページから選択された [設定] が表示されます。]
4. **SSO設定** セクションで次の手順を実行します。

    [Image: スクリーンショットには、説明されている値を入力できる S S O 設定が示されています。]

    ある。 **[Enable SSO]** で **[ON]** を選択します。

    b。 **IDP エンティティ ID** テキストボックスに、**Microsoft Entra Identifier**の値を貼り付けます。

    c. **[Sso target url]** テキストボックスに、**ログイン URL** の値を貼り付けます。

    d. **Slo ターゲット URL** テキストボックスに、**ログアウト URL**の値を貼り付けます。

    え ダウンロードした **証明書 (Base64)** ファイルをメモ帳で開き、その内容をクリップボードにコピーして、**証明書** ボックスに貼り付けます。

    f. [ **設定の保存]** ボタンを選択します。
5. **SSO 設定** セクションの横に、[**サービス プロバイダー メタデータ URL**] の下の URL をコピーします。

    [Image: スクリーンショットには、サービス プロバイダー メタデータをコピーできるメモが示されています。]
6. 空のブラウザーの下  メタデータ URL リンクを開き、メタデータ ドキュメントをダウンロードします。 次に、ファイルから EntityDescriptor 値 (entityID) をコピーし、Azure portal の基本的な SAML 構成 **の 識別子 ボックス** 貼り付けます。

    [Image: スクリーンショットは、エンティティ ID を取得できるプレーン テキスト X M L のテキスト ボックスを示]

#### Recognize のテスト ユーザーの作成

Microsoft Entra ユーザーが Recognize にログインできるようにするには、ユーザーを Recognize にプロビジョニングする必要があります。 Recognize の場合、プロビジョニングは手動で行います。

このアプリは SCIM プロビジョニングをサポートしていませんが、ユーザーをプロビジョニングする代替ユーザー同期があります。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. Recognize 企業サイトに管理者としてサインインします。
2. 右上隅にある [メニュー] を選択 **します**。 **[Company Admin]** に移動します。
3. 左側のナビゲーション ウィンドウで、**[設定]** を選択します。
4. **ユーザー同期** セクションで、次の手順を実行します。

    [Image: 新しいユーザー]

    ある。 **[Sync Enabled ]** で **[ON]** を選択します。

    b。 **[Choose sync provider]** で **[Microsoft / Office 365]** を選択します。

    c. [ **ユーザー同期の実行] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Recognize サインオン URL にリダイレクトされます。
- [Recognize Sign-on URL]\(サインオン URL の認識\) に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Recognize]\(認識\) タイルを選択すると、このオプションは Recognize のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/recurly-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Recurly を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/recurly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Recurly の間にシングル サインオンを構成する方法について説明します。

この記事では、Recurly と Microsoft Entra ID を統合する方法について説明します。 Recurly を Microsoft Entra ID と統合すると、次のことができます。

- Recurly にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Recurly に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Recurly でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Recurly では、**SP および IdP** Initiated SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Recurly を追加する

Microsoft Entra ID への Recurly の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Recurly を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Recurly**」と入力します。
4. 結果パネルから **[frankly]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Recurly 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Recurly に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Recurly の関連ユーザーの間にリンク関係を確立する必要があります。

Recurly に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Recurly SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Recurly テスト ユーザーの作成** - Microsoft Entra のユーザーにリンクする Recurly 内の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Recurly** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて**、シングル サインオンを**選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、**[識別子]** と **[応答 URL]** の値はそれぞれ `https://app.recurly.com` と `https://app.recurly.com/login/sso` で事前構成されています。 次の手順を行い、構成を完成させます。

    ある。 **[サインオン URL]** テキスト ボックスに、URL として「`https://app.recurly.com/login/sso`」と入力します。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **編集]** を選択し、拇印の状態の横にある `...` を選択し、 **PEM 証明書のダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. Recurly アプリケーションは、特定の形式の SAML アサーションを想定しているため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、その例を示しています。 **[一意のユーザー識別子]** の既定値は **user.userprincipalname** ですが、Recurly ではこれをユーザーのメール アドレスにマップすることが想定されています。 そのため、一覧の **user.mail** 属性を使用するか、組織構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
8. SSO を機能させるには、Recurly アプリケーションでトークン暗号化を有効にする必要があります。 トークン暗号化をアクティブにするには、**Entra ID**&gt;**Enterprise アプリ**を参照&gt;アプリケーション &gt;**トークン暗号化**を選択します。 詳細については、「[Microsoft Entra の SAML トークン暗号化を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption)」を参照してください。

    1. インポートする証明書のコピーを取得するには、[Recurly サポート](mailto:support@recurly.com) にお問い合わせください。
    2. 証明書をインポートした後、拇印の状態の横にある `...` を選択し、 `Activate token encryption certificate`を選択します。
    3. トークン暗号化の構成の詳細については、こちらの[リンク](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption)を参照してください。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Recurly SSO の構成

**Recurly** サイトのシングル サインオンを構成するには、次の手順に従います。

1. Recurly 企業サイトに管理者としてログインします。
2. **[管理者] **&gt;** [ユーザー]** の順に移動します。

    [Image: [ユーザー] への移動メニューを示すスクリーンショット]
3. 右上にある [ **シングル サインオンの構成** ] ボタンを選択します。

    [Image: SSO 構成ページへの移動を示すスクリーンショット]
4. **[シングル サインオン]** セクションで、**[有効]** オプション ボタンを選択し、**[ID プロバイダー]** セクションで次の手順を行います。

    [Image: SSO 構成の完了を示すスクリーンショット]

    ある。 **[プロバイダー名]** で、**[Azure]** を選択します。

    b。 **[SAML 発行者 ID]** のテキストボックスに、**アプリケーション (クライアント ID)** の値を貼り付けます。

    c. **[ログイン URL]** のテキスト ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    d. ダウンロードした証明書 (PEM) をメモ帳で開き、その内容を **[証明書]** のテキストボックスに貼り付けます。

    え [ **変更の保存] を選択します**。

#### Recurly テスト ユーザーの作成

このセクションでは、サイトに参加するように新しいユーザーを招待し、SSO を使用して構成をテストするように要求します。

1. **Admin**&gt;**Users** に移動し、[**ユーザーの招待**] を選択し、以前に作成した Azure テスト ユーザーの電子メール アドレスを入力します。 招待は、既定で SSO の使用を要求するようになっています。

    [Image: [ユーザーの招待] ページへの移動メニューを示すスクリーンショット]

    [Image: [ユーザーの招待] ページを示すスクリーンショット]
2. テスト ユーザーは、Recurly からサイトに参加するように招待されるメールを受信します。
3. 招待を受け入れると、サイトの **[会社のユーザー** ] の下にテスト ユーザーが一覧表示され、SSO を使用してログインできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Recurly のサインオン URL にリダイレクトされます。
- Recurly のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Recurly に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [繰り返し] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Recurly に自動的にサインインされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/redbrick-health-tutorial"} -->
## Microsoft Entra ID で "RedBrick Health" をシングルサインオンに構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/redbrick-health-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RedBrick Health の間でシングル サインオンを構成する方法について説明します。

この記事では、RedBrick Health と Microsoft Entra ID を統合する方法について説明します。 RedBrick Health を Microsoft Entra ID を統合すると、次のことができます。

- RedBrick Health にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って RedBrick Health に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- RedBrick Health でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- RedBrick Health では、**IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの RedBrick Health の追加

Microsoft Entra ID への RedBrick Health の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に RedBrick Health を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**RedBrick Health**」と入力します。
4. 結果のパネルから **[RedBrick Health]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RedBrick Health 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、RedBrick Health に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと RedBrick Health の関連ユーザーとの間にリンク関係を確立する必要があります。

RedBrick Health に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RedBrick Health SSO の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RedBrick Health のテストユーザーを作成する - B.Simon に対応するユーザーを RedBrick Health で作成し、そのユーザーを Microsoft Entra にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**RedBrick Health**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://www.redbrickhealth.com`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://sso-intg.redbrickhealth.com/sp/ACS.saml2`

    運用環境: `https://sso.redbrickhealth.com/sp/ACS.saml2`

    c. [ **追加の URL の設定] を選択します**。

    d. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api-sso2.redbricktest.com/identity/sso/nbound?target=https://vanity9-sso2.redbrickdev.com/portal&connection=<companyname>conn1`

    注

    リレー状態の値は実際の値ではありません。 実際のリレー状態でこの値を更新します。 この値を取得するには、[RedBrick Health クライアント サポート チーム](https://home.redbrickhealth.com/contact/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. RedBrick Health アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 このアプリケーションに対して次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの **ユーザー属性** セクションから管理できます。 [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** ボタンを選択して [ **ユーザー属性] ダイアログを** 開きます。

    [Image: スクリーンショットには、[編集] アイコンが選択された [ユーザー属性] が表示されます。]
7. **[ユーザー属性]** ダイアログの **[ユーザーの要求]** セクションで、上の図のように SAML トークン属性を構成し、次の手順を実行します。

    | 名前 | ソース属性 |
    | --- | --- |
    | プリンシパル名 | \*\*\*\*\*\*\*\*\*\* |
    | [クライアント ID] | \*\*\*\*\*\*\*\*\*\* |
    | 参加者の ID | \*\*\*\*\*\*\*\*\*\* |

    注

    これらの値はあくまで参考のためのものです。 組織の要件に従って属性を定義する必要があります。 必要な要求について詳しくは、[RedBrick Health のサポート チーム](https://home.redbrickhealth.com/contact/)にお問い合わせください。

    ある。 [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: スクリーンショットには、[新しい要求の追加] オプションを含むユーザー要求が表示されます。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    え **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **[OK]** を選択します。

    ジー **保存** を選択します。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[RedBrick Health のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RedBrick Health の SSO の構成

**RedBrick Health** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [RedBrick Health サポート チーム](https://home.redbrickhealth.com/contact/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### RedBrick Health のテスト ユーザーの作成

このセクションでは、RedBrick Health で B.Simon というユーザーを作成します。 [RedBrick Health サポート チーム](https://home.redbrickhealth.com/contact/)と連携し、RedBrick Health プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した RedBrick Health に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [RedBrick Health] タイルを選択すると、SSO を設定した RedBrick Health に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/redocly-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Redocly を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/redocly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Redocly 間にシングル サインオンを構成する方法について説明します。

この記事では、Redocly と Microsoft Entra ID を統合する方法について説明します。 Redocly は、GitHub でドキュメントを保持できる最初の開発者向けドキュメント ツールであり、開発者のドキュメントを開発者の近くに保つことができます。 Microsoft Entra ID と Redocly を統合すると、次のことができます。

- Redocly にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Redocly に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Redocly 用の Microsoft Entra シングル サインオンを構成してテストします。 Redocly では、**SP** によって開始されるシングル サインオンと **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### [前提条件]

Microsoft Entra ID を Redocly と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Redocly のシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Redocly アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Redocly を追加する

Microsoft Entra アプリケーション ギャラリーから Redocly を追加して、Redocly とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Redocly**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://api.redocly.com/auth/sso?idpId=<CustomerId>` |
    | `https://api.<Region>.redocly.com/auth/sso?idpId=<CustomerId>` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://api.redocly.com/auth/sso` |
    | `https://api.<Region>.redocly.com/auth/sso` |
    | `https://<SiteName>.redoc.dev/_auth/saml2` |
    | `https://<SiteName>.<REGION>.redoc.dev/_auth/saml2` |

    c. [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://app.redocly.com/login-sso` |
    | `https://app.<Region>.redocly.com/login-sso` |
    | `https://<SiteName>.redoc.dev/_auth/idp-login` |
    | `https://<SiteName>.<REGION>.redoc.dev/_auth/idp-login` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Redocly サポート チーム](mailto:team@redocly.com)にお問い合わせください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (PEM)]** を見つけて **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Redocly のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Redocly の SSO を構成する

**Redocly** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** と、アプリケーションの構成からコピーした適切な URL を、[Redocly サポート チーム](mailto:team@redocly.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Redocly のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Redocly に作成します。 Redocly では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Redocly にユーザーがまだ存在していない場合、一般的には認証後に新しいものが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Redocly のサインオン URL にリダイレクトされます。
- Redocly のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Redocly] タイルを選択すると、このオプションは Redocly のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/redvector-tutorial"} -->
## Microsoft Entra ID で RedVector for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/redvector-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RedVector の間にシングル サインオンを構成する方法について説明します。

この記事では、RedVector と Microsoft Entra ID を統合する方法について説明します。 RedVector を Microsoft Entra ID と統合すると、次のことができます。

- RedVector にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って RedVector に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

Microsoft Entra と RedVector の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Azure サブスクリプションをお持ちでない場合は、開始する前に[無料のアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- シングル サインオンが有効な RedVector サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- RedVector では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの RedVector の追加

Microsoft Entra ID への RedVector の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に RedVector を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**RedVector**」と入力します。
4. 結果パネルから **[RedVector]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RedVector 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、RedVector に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと RedVector の関連ユーザーとの間にリンク関係を確立する必要があります。

RedVector に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RedVector SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RedVector テストユーザーを作成して**、B.Simon に対応するユーザーを RedVector に登録し、Microsoft Entra にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**RedVector**&gt;**Single サインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **識別子 (エンティティ ID)** テキスト ボックスに、URL を入力します: `https://sso2.redvector.com/saml2`

    b。 [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://sso2.redvector.com/adfs/<Companyname>`

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 値を取得するには、[RedVector クライアント サポート チーム](mailto:sso@redvector.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[RedVector のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### RedVector SSO を構成する

**RedVector** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [RedVector サポート チーム](mailto:sso@redvector.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### RedVector テスト ユーザーの作成

このセクションでは、RedVector で Britta Simon というユーザーを作成します。 [RedVector サポート チーム](mailto:sso@redvector.com)と連携して、RedVector プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる RedVector のサインオン URL にリダイレクトされます。
- RedVector のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [RedVector] タイルを選択すると、このオプションは RedVector のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/reflektive-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Reflektive を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/reflektive-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Reflektive の間にシングル サインオンを構成する方法について説明します。

この記事では、Reflektive と Microsoft Entra ID を統合する方法について説明します。 Reflektive と Microsoft Entra ID を統合すると、次のような利点があります。

- Reflektive にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで Reflektive に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Reflektive でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Reflektive では、**SP**開始のSSOと**IDP**開始のSSOがサポートされます。

### ギャラリーからの Reflektive の追加

Microsoft Entra ID への Reflektive の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Reflektive を追加する必要があります。

**ギャラリーから Reflektive を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **Reflektive**」と入力し、結果パネルで **Reflektive** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: Reflektive が結果一覧に表示される]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Reflektive で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Reflektive 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Reflektive で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Reflektive シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Reflektive のテストユーザーを作成** - Microsoft Entra における Britta Simon にリンクされた Reflektive 内での対応ユーザーを作成します。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Reflektive で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Reflektive** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [Image: ReflektiveのドメインとURLのシングルサインオン情報]

    [ **識別子** ] テキスト ボックスで、反射型サポート チームからの確認に従って、次のいずれかの URL を使用します。

    - `reflektive.com`
    - `https://www.reflektive.com/saml/metadata`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [Image: 画像]

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.reflektive.com/app`

    注

    SP モードの場合は、 [Reflektive サポート チーム](https://support@reflektive.com)に登録されている電子メール ID を取得する必要があります。 **[電子メール**] ボックスに ID を入力すると、シングル サインオン オプションが有効になります。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Reflektive のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    ある。 ログイン URL

    b。 Microsoft Entra アイデンティファイヤー

    c. ログアウト URL

#### Reflektive シングル サインオンの構成

**Reflektive** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Reflektive サポート チーム](mailto:support@reflektive.com/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Reflektive テスト ユーザーの作成

このセクションでは、Reflektive で Britta Simon というユーザーを作成します。 [Reflektive サポート チーム](mailto:support@reflektive.com/)と協力して、Reflektive プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Reflektive] タイルを選択すると、SSO を設定した Reflektive に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/remotepc-tutorial"} -->
## Microsoft Entra ID で RemotePC for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/remotepc-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RemotePC の間にシングル サインオンを構成する方法について説明します。

この記事では、RemotePC と Microsoft Entra ID を統合する方法について説明します。 RemotePC を Microsoft Entra ID と統合すると、次のことができます。

- RemotePC にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って RemotePC に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- RemotePC でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- RemotePC では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

### ギャラリーからの RemotePC の追加

Microsoft Entra ID への RemotePC の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に RemotePC を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**RemotePC**」と入力します。
4. 結果のパネルから **[RemotePC]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RemotePC 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、RemotePC に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと RemotePC の関連ユーザーとの間にリンク関係を確立する必要があります。

RemotePC に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RemotePC の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RemotePC テスト ユーザーを作成** - Microsoft Entra における B.Simon の表象にリンクされる、RemotePC における B.Simon の対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**RemotePC**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.remotepc.com/rpcnew/login/sso`
7. **保存** を選択します。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[RemotePC のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RemotePC の SSO の構成

**RemotePC** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [RemotePC サポート チーム](mailto:support@remotepc.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### RemotePC のテスト ユーザーの作成

このセクションでは、RemotePC で Britta Simon というユーザーを作成します。 [RemotePC サポート チーム](mailto:support@remotepc.com)と連携して、RemotePC プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる RemotePC サインオン URL にリダイレクトされます。
- RemotePC のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した RemotePC に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [RemotePC] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した RemotePC に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/renraku-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に PHONE APPLI PEOPLE を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/renraku-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と PHONE APPLI PEOPLE 間にシングル サインオンを構成する方法について学習します。

この記事では、PHONE APPLI PEOPLE と Microsoft Entra ID を統合する方法について説明します。 PHONE APPLI PEOPLE を Microsoft Entra ID と統合すると、次のことができます。

- PHONE APPLI PEOPLE にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って PHONE APPLI PEOPLE に自動的にサインインできるように設定します。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PHONE APPLI PEOPLE でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PHONE APPLI PEOPLE では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから PHONE APPLI PEOPLE を追加する

Microsoft Entra ID への PHONE APPLI PEOPLE の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に PHONE APPLI PEOPLE を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を開きます。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「PHONE APPLI PEOPLE**」と入力します。
4. 結果パネルから **[PHONE APPLI PEOPLE** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PHONE APPLI PEOPLE 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、PHONE APPLI PEOPLE に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと PHONE APPLI PEOPLE の関連ユーザーとの間にリンク関係を確立する必要があります。

PHONE APPLI PEOPLE に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PHONE APPLI PEOPLE の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PHONE APPLI PEOPLE テスト ユーザーの作成 - PHONE APPLI PEOPLE** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**PHONE APPLI PEOPLE**&gt;**シングルサインオン**に移動してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMURL>/front`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMURL>/front/login?sso`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、PHONE APPLI PEOPLE クライアント サポート チーム](https://phoneappli.net/product/contact/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **PHONE APPLI PEOPLE のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PHONE APPLI PEOPLE SSO の構成

**PHONE APPLI PEOPLE** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、アプリケーション構成からコピーした適切な URL を [PHONE APPLI PEOPLE サポート チーム](https://phoneappli.net/product/contact/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PHONE APPLI PEOPLE のテスト ユーザーの作成

このセクションでは、PHONE APPLI PEOPLE で B.Simon というユーザーを作成します。 [PHONE APPLI PEOPLE サポート チーム](https://phoneappli.net/product/contact/)と協力して、PHONE APPLI PEOPLE プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる PHONE APPLI PEOPLE のサインオン URL にリダイレクトされます。
- PHONE APPLI PEOPLE のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [PHONE APPLI PEOPLE] タイルを選択すると、このオプションは PHONE APPLI PEOPLE のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/replicon-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Replicon を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/replicon-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Replicon 間にシングル サインオンを構成する方法について説明します。

この記事では、Replicon と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Replicon を統合すると、次のことができます。

- Replicon にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Replicon に自動的にサインインするよう設定できます。
- 1 つの中央の場所でアカウントを管理します。

Replicon は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Replicon でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Replicon では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからRepliconを追加

Microsoft Entra ID への Replicon の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Replicon を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Replicon**」と入力します。
4. 結果パネルから **Replicon** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Replicon 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Replicon に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Replicon の関連ユーザーとの間にリンク関係を確立する必要があります。

Replicon に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Replicon の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Replicon テストユーザーの作成** - Microsoft Entra の B.Simon にリンクされている、Replicon 内の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Replicon** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** ページで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://global.replicon.com/!/saml2/<client name>/sp-sso/post`

    b。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://global.replicon.com/!/saml2/<client name>`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://global.replicon.com/!/saml2/<client name>/sso/post`

    注

    これらの値は実際の値ではありません。 実際の Sign-On URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Replicon クライアント サポート チーム](https://www.replicon.com/customerzone/contact-support) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. **SAML 署名証明書**の鉛筆アイコンを選択して設定を編集します。

    [Image: 署名アルゴリズム]

    1. 署名オプションとして **[SAML アサーション** に署名する] を **選択します**。
    2. **署名アルゴリズム**として **SHA-256** を選択します。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Replicon SSO の設定

1. 別の Web ブラウザー ウィンドウで、Replicon 企業サイトに管理者としてサインインします。
2. SAML 2.0 を構成するには、次の手順に従います。

    [Image: SAML 認証を有効にする]

    ある。 **EnableSAML Authentication2** ダイアログを表示するには、会社のキーの後に次のコードを URL に追加します。`/services/SecurityService1.svc/help/test/EnableSAMLAuthentication2`

    1. 完全な URL のスキーマを次に示します。`https://na2.replicon.com/<YourCompanyKey>/services/SecurityService1.svc/help/test/EnableSAMLAuthentication2`

    b。 **+**を選択して**、v20Configuration セクションを**展開します。

    c. **+**を選択して **metaDataConfiguration** セクションを展開します。

    d. xmlSignatureAlgorithm に **SHA256** を選択する

    え [ **ファイルの選択] を選択**して ID プロバイダーメタデータ XML ファイルを選択し、[ **送信]** を選択します。

#### Replicon テスト ユーザーの作成

このセクションの目的は、Replicon で B.Simon というユーザーを作成することです。

**ユーザーを手動で作成する必要がある場合は、次の手順を実行します。**

1. Web ブラウザー ウィンドウで、Replicon 企業サイトに管理者としてサインインします。
2. **[管理**] &gt;**[ユーザー]** に移動します。

    [Image: ユーザー]
3. **[+ ユーザーの追加] を選択します**。

    [Image: ユーザー追加]
4. [ **ユーザー プロファイル]** セクションで、次の手順を実行します。

    [Image: ユーザー プロファイル]

    ある。 **ログイン名** テキストボックスに、プロビジョニングする Microsoft Entra ユーザーの Microsoft Entra ID メールアドレスを入力します`B.Simon@contoso.com`。

    注

    [Login Name] (ログイン名) は Microsoft Entra ID のユーザーのメール アドレスと一致する必要があります。

    b。 **[認証の種類] で**、[**SSO**] を選択します。

    c. [Authentication ID] (認証 ID) を [Login Name] (ログイン名) (ユーザーの Microsoft Entra ID のメール アドレス) と同じ値に設定します

    d. [ **部署** ] ボックスに、ユーザーの部署を入力します。

    え **従業員の種類**として、[**管理者**] を選択します。

    f. [ **ユーザー プロファイルの保存]** を選択します。

注

Replicon から提供されている他の Replicon ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Replicon のサインオン URL にリダイレクトされます。
- Replicon のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Replicon] タイルを選択すると、このオプションは Replicon のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/reprints-desk-article-galaxy-tutorial"} -->
## Reprints Desk を構成する - Article Galaxy をシングルサインオンで Microsoft Entra ID と連携する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/reprints-desk-article-galaxy-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Reprints Desk - Article Galaxy の間でシングル サインオンを構成する方法について説明します。

この記事では、Reprints Desk - Article Galaxy と Microsoft Entra ID を統合する方法について説明します。 Reprints Desk - Article Galaxy と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Reprints Desk - Article Galaxy へのアクセス権を管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Reprints Desk - Article Galaxy に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Reprints Desk - Article Galaxy でのシングル サインオン (SSO) が有効なサブスクリプション。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra ID でアプリケーションを追加または管理することもできます。 詳細については、Azure 組み込みロール に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Reprints Desk - Article Galaxy では **IDP** を介して開始される SSO をサポートしています。
- Reprints Desk - Article Galaxy では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Reprints Desk - Article Galaxy を追加する

Microsoft Entra ID への Reprints Desk - Article Galaxy の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Reprints Desk - Article Galaxy を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリ**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Reprints Desk - Article Galaxy**」と入力します。
4. 結果パネルから [ **Reprints Desk - Article Galaxy** ] を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Reprints Desk の Microsoft Entra SSO の構成とテスト - Article Galaxy

**B.Simon** というテスト ユーザーを使用して、Reprints Desk - Article Galaxy に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Reprints Desk - Article Galaxy の関連ユーザーとの間にリンク関係を確立する必要があります。

Reprints Desk - Article Galaxy で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Reprints Desk - Article Galaxy SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Reprints Desk - Article Galaxy のテストユーザーの作成** - Microsoft Entra におけるユーザーの表現とリンクするために、Reprints Desk - Article Galaxy で B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Reprints Desk - Article Galaxy**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Reprints Desk - Article Galaxy アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットは、Reprints Desk アプリケーションの画像を示しています。]
7. 上記に加えて、Reprints Desk - Article Galaxy アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 名字 | ユーザーの姓 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **Reprints Desk - Article Galaxy のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成を適切な U R L にコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Reprints Desk - Article Galaxy の SSO を構成する

**Reprints Desk - Article Galaxy** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Reprints Desk - Article Galaxy サポート チーム](mailto:customersupport@reprintsdesk.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Reprints Desk - Article Galaxy のテストユーザー作成

このセクションでは、B.Simon というユーザーを Reprints Desk - Article Galaxy に作成します。 Reprints Desk - Article Galaxy では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Reprints Desk - Article Galaxy にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Reprints Desk - Article Galaxy に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Reprints Desk - Article Galaxy] タイルを選択すると、SSO を設定した Reprints Desk - Article Galaxy に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rescana-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Rescana を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rescana-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Rescana の間のシングル サインオンを構成する方法について説明します。

この記事では、Rescana と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Rescana を統合すると、次のことができます。

- Rescana へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Rescana に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Rescana でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Rescana では、**SP Initiated SSO** や **IDP Initiated SSO** がサポートされます。
- Rescana では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Rescana の追加

Microsoft Entra ID への Rescana の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Rescana を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Rescana**」と入力します。
4. 結果のパネルから **[Rescana** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Rescana の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Rescana に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Rescana の関連ユーザーとの間にリンク関係を確立する必要があります。

Rescana に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Rescana SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Rescana のテスト ユーザーの作成** - Rescana で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Rescana**&gt;**シングルサインオン** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 識別子 |
    | --- |
    | `https://pl.rescana.com/saml/metadata.xml` |
    | `https://portal.rescana.com/saml/metadata.xml` |
    | `https://qa.rescana.com/saml/metadata.xml` |
    |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://pl.rescana.com/authorization-code/callback` |
    | `https://portal.rescana.com/authorization-code/callback` |
    | `https://qa.rescana.com/authorization-code/callback` |
    |  |
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | サインオン URL |
    | --- |
    | `https://pl.rescana.com/authorization-code/callback` |
    | `https://portal.rescana.com/authorization-code/callback` |
    | `https://qa.rescana.com/authorization-code/callback` |
    |  |

    b。 [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<INSTANCE_ID>`

    注

    これは実際の値ではありません。 実際のリレー状態でこの値を更新します。 この値を取得するには、 [Rescana クライアント サポート チーム](mailto:ops@rescana.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Rescana のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Rescana の SSO の構成

**Rescana** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Rescana サポート チーム](mailto:ops@rescana.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Rescana のテスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Rescana に作成します。 Rescana では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Rescana に存在しない場合は、Rescana にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Rescana のサインオン URL にリダイレクトされます。
- Rescana のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Rescana に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Rescana] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Rescana に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/resource-central-tutorial"} -->
## Meeting Room Booking System 用の Resource Central を設定し、Microsoft Entra ID を使用したシングルサインオン (SAML SSO) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/resource-central-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Resource Central – SAML SSO for Meeting Room Booking System の間でシングル サインオンを構成する方法について学習します。

この記事では、Resource Central - SAML SSO for Meeting Room Booking System と Microsoft Entra ID を統合する方法について説明します。 Resource Central – SAML SSO for Meeting Room Booking System と Microsoft Entra ID 統合すると、次のことが可能になります。

- Resource Central – SAML SSO for Meeting Room Booking System にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントで Resource Central – SAML SSO for Meeting Room Booking System に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Resource Central – SAML SSO for Meeting Room Booking System でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Resource Central – SAML SSO for Meeting Room Booking System は **SP** 開始 SSO をサポート
- Resource Central – SAML SSO for Meeting Room Booking System は **Just In Time** ユーザー プロビジョニングをサポート

### Resource Central – SAML SSO for Meeting Room Booking System のギャラリーからの追加

Microsoft Entra ID への Resource Central – SAML SSO for Meeting Room Booking System の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Resource Central – SAML SSO for Meeting Room Booking System を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Resource Central – SAML SSO for Meeting Room Booking System**」と入力します。
4. 結果パネルから **[Resource Central – SAML SSO for Meeting Room Booking System]** を選択し、このアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Resource Central – SAML SSO for Meeting Room Booking System 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Microsoft Entra SSO を Resource Central – SAML SSO for Meeting Room Booking System 用に構成してテストします。 SSO が機能するために、Microsoft Entra ユーザーと Resource Central – SAML SSO for Meeting Room Booking System の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Resource Central – SAML SSO for Meeting Room Booking System と一緒に構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
    3. **Resource Central SAML SSO for Meeting Room Booking System のテスト ユーザーの作成** - Resource Central - SAML SSO for Meeting Room Booking System で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
2. **Resource Central SAML SSO for Meeting Room Booking System SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Resource Central - ミーティングルーム予約システム用 SAML SSO**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** で、次のフィールドの値を入力します。

    1. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<DOMAIN_NAME>/ResourceCentral`
    2. **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<DOMAIN_NAME>/ResourceCentral`
    3. **[応答 URL]** ボックスに、`https://<DOMAIN_NAME>/ResourceCentral/ExAuth/Saml2Authentication/Acs` のパターンを使用して URL を入力します

    注意

    これらの値はリテラル値ではありません。 これらの値は、実際のサインオン URL、識別子、応答 URL の値で更新してください。 これらの値を取得するには、[Resource Central – SAML SSO for Meeting Room Booking System クライアント サポート チーム](mailto:st@aod.vn)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** で、**[証明書 (Base64)]** を見つけて **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Resource Central – SAML SSO for Meeting Room Booking System の設定]** で、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Resource Central SAML SSO for Meeting Room Booking System のテスト ユーザーの作成

このセクションでは、**B.Simon** というユーザーが、**Resource Central – SAML SSO for Meeting Room Booking System** に作成されます。

1. Resource Central – SAML SSO for Meeting Room Booking System で、 **[セキュリティ]**&gt;**[Persons]**&gt;**[新規]** の順に選択します。

    [Image: [新規] ボタンが強調表示された、Resource Central の [Persons](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) ペインを示すスクリーンショット。]
2. **[Person Details](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの詳細)** で、 **[Display name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/表示名)** に、ユーザー「**B. Simon**」と入力します。 **[SMTP Address](SMTP アドレス)** には、ユーザーの Microsoft Entra ユーザー名を入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。

    [Image: Resource Central の [Person Details] (ユーザーの詳細 ) ペインを示すスクリーンショット。]

### Resource Central SAML SSO for Meeting Room Booking System SSO の構成

このセクションでは、 **Resource Central システム管理者**でシングル サインオンを構成します。

1. Resource Central – SAML SSO for Meeting Room Booking System システム管理者の **[External Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/外部認証)** を選択します。
2. **[Enable Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成を有効にする)** で、 **[Yes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/\はい)** を選択します。

    [Image: Resource Central – SAML SSO for Meeting Room Booking System の [External Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/外部認証) ペインで選択された [Enable Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成を有効にする) オプションを示すスクリーンショット。]
3. **[Authentication Protocol](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証プロトコル)** で **[SAML2]** を選択します。

    [Image: Resource Central の [Authentication Protocol](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証プロトコル) で選択された [SAML2] を示すスクリーンショット。]
4. **[SAML2 Configuration](SAML2 構成)** で、次のフィールドの値を入力します。

    1. **[Identifier (Entity ID)](識別子 (エンティティ ID))**、**[Login URL](ログイン URL)**、**[Logout URL](ログアウト URL)**、および **[Microsoft Entra Identifier](Microsoft Entra 識別子)** に、適切な URL を入力します。

        [Image: Resource Central の [SAML2 Configuration](SAML2 構成) ペインのスクリーンショット。]

        URL を **[Resource Central – SAML SSO for Meeting Room Booking System の設定]** ペインからコピーします。

        [Image: Resource Central の [Set up Resource Central](Resource Central のセットアップ) ペインのスクリーンショット。]
    2. **[Return URL](戻り先 URL)** には「`https://<DOMAIN_NAME>/ResourceCentral/ExAuth/Saml2Authentication/CallbackHandler`」と入力します。
5. **[Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書)** については、証明書をアップロードし、パスワードを入力します。

    [Image: Resource Central – SAML SSO for Meeting Room Booking System の証明書セクションのスクリーンショット。]
6. **[保存]** を選択します。
7. **Azure portal** に戻ります。 **[SAML 署名証明書]** で、証明書をアップロードし、パスワードを入力します。
8. **[追加]** を選択します。

### SSO のテスト

このセクションでは、Microsoft Entra のシングル サインオン構成をテストします。 シングル サインオンをテストするには、3 つのオプションがあります。

- Azure portal で、 **[このアプリケーションをテストします]** を選択します。 このリンクは、Resource Central – SAML SSO for Meeting Room Booking System サインオン URL にリダイレクトされます。この URL でログインを開始できます。
- Resource Central – SAML SSO for Meeting Room Booking System のサインオン URL に直接アクセスし、ログインを開始します。

    [Image: Resource Central シングル サインオンのテスト Web ページのスクリーンショット。]
- Microsoft のマイ アプリ ポータルを使用します。 マイ アプリ ポータルで、 **[Resource Central – SAML SSO for Meeting Room Booking System]** タイルを選択し、Resource Central – SAML SSO for Meeting Room Booking System サインオン URL にリダイレクトします。 詳細については、「[マイ アプリ ポータルからアプリにサインインして開始する](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/respondent-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用の Respondent を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/respondent-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Respondent の間にシングル サインオンを構成する方法について説明します。

この記事では、回答者と Microsoft Entra ID を統合する方法について説明します。 Respondent は、ビジネスのプロフェッショナルと消費者を研究者と結び付けるグローバル マーケットプレースです。 Respondent での研究参加者の採用、スケジュール、支払いを管理します。 Respondent を Microsoft Entra ID と統合すると、次のことができます。

- Respondent にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Respondent に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Respondent 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Respondent では、**SP** Initiated と **IDP** Initiated のシングル サインオンがサポートされています。

### [前提条件]

Microsoft Entra ID を Respondent と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Respondent でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Respondent アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Respondent を追加する

Microsoft Entra アプリケーション ギャラリーから Respondent を追加して、Respondent でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Respondent**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://app.respondent.io/auth/saml/sp/<ID>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://app.respondent.io/auth/saml/sp/<ID>`
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://app.respondent.io/auth/saml/login`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[回答者クライアント サポート チーム](mailto:enterprisesupport@respondent.io) にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Respondent アプリケーションでは、特定の形式の SAML アサーションが使用されるため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. その他に、Respondent アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユーザーID | user.objectid (ユーザーのオブジェクトID) |
    | メール | ユーザーのメールアドレス |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
9. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (Base64)]** を見つけます。**[ダウンロード]** を選択して証明書をダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Respondent のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Respondent SSO を構成する

**Respondent** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Respondent サポート チーム](mailto:enterprisesupport@respondent.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Respondent テスト ユーザーを作成する

このセクションでは、Respondent で Britta Simon というユーザーを作成します。 [Respondent サポート チーム](mailto:enterprisesupport@respondent.io)と連携して、Respondent プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる回答者のサインオン URL にリダイレクトされます。
- Respondent のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した回答者に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [回答者] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した回答者に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/retail-zipline-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に RetailZipline を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/retail-zipline-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Retail Zipline の間にシングル サインオンを構成する方法について説明します。

この記事では、RetailZipline と Microsoft Entra ID を統合する方法について説明します。 Retail Zipline と Microsoft Entra ID を統合すると、次のことができます:

- Retail Zipline にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Retail Zipline に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Retail Zipline サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Retail Zipline により、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Retail Zipline により、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Retail Zipline の追加

Microsoft Entra ID への Retail Zipline の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Retail Zipline を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Retail Zipline**」と入力します。
4. 結果パネルで **Retail Zipline** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Retail Zipline 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Retail Zipline に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Retail Zipline の関連ユーザーとの間にリンク関係を確立する必要があります。

Retail Zipline に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Retail Zipline の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Retail Zipline のテストユーザーを作成** - Retail Zipline で B.Simon に対応するユーザーを作成し、Microsoft Entra におけるユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**リテールジップライン**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.retailzipline.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.retailzipline.com/sso/saml`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.retailzipline.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Retail Zipline クライアント サポート チーム](mailto:support@retailzipline.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Retail Zipline アプリケーションによって、特定の形式の SAML アサーションが想定されているため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Retail Zipline アプリケーションにより、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 従業員番号 | ユーザー.社員ID |
    | メール | ユーザーのメールアドレス |
    | 苗字 | ユーザーの名字 |
    | given\_name | ユーザー.ファーストネーム |
    | タイトル | ユーザー.職名 |
    | コストセンター | ユーザーの部署 |
    | ロケール | ユーザーの優先言語 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Retail Zipline の SSO を構成する

**Retail Zipline** 側でシングル サインオンを構成するには、**アプリケーション フェデレーション メタデータ URL** を [Retail Zipline サポート チーム](mailto:support@retailzipline.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Retail Zipline のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Retail Zipline に作成します。 Retail Zipline によって、Just-In-Time ユーザー プロビジョニングがサポートされています。既定ではこれが有効になっています。 このセクションにはアクション項目はありません。 Retail Zipline にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる RetailZipline サインオン URL にリダイレクトされます。
- Retail Zipline のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した RetailZipline に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [RetailZipline] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した RetailZip に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/retrievermediadatabase-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に RetrieverMediaDatabase を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/retrievermediadatabase-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RetrieverMediaDatabase 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、RetrieverMediaDatabase と Microsoft Entra ID を統合する方法について説明します。 RetrieverMediaDatabase と Microsoft Entra ID を統合すると、次のことができます。

- RetrieverMediaDatabase にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Azure AD アカウントを使用して RetrieverMediaDatabase に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- RetrieverMediaDatabase でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- RetrieverMediaDatabase では、**IDP** Initiated SSO がサポートされます

### ギャラリーからの RetrieverMediaDatabase の追加

Azure AD への RetrieverMediaDatabase の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに RetrieverMediaDatabase を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**RetrieverMediaDatabase**」と入力します。
4. 結果パネルで **RetrieverMediaDatabase** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RetrieverMediaDatabase の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、RetrieverMediaDatabase に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと RetrieverMediaDatabase の関連ユーザーとの間にリンク関係を確立する必要があります。

RetrieverMediaDatabase に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RetrieverMediaDatabase の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RetrieverMediaDatabase テスト ユーザーを作成 - Microsoft Entra のユーザー表現にリンクするために、RetrieverMediaDatabase で B.Simon に対応するものを持たせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**RetrieverMediaDatabase**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RetrieverMediaDatabase の SSO の構成

**RetrieverMediaDatabase** 側でシングル サインオンを構成するには、**アプリケーション フェデレーション メタデータ URL** を [RetrieverMediaDatabase サポート チーム](mailto:support@retriever.nl)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### RetrieverMediaDatabase のテスト ユーザーの作成

このセクションでは、RetrieverMediaDatabase で Britta Simon というユーザーを作成します。 [RetrieverMediaDatabase サポート チーム](mailto:support@retriever.nl)と連携し、RetrieverMediaDatabase プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

1. [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した RetrieverMediaDatabase に自動的にサインインします
2. Microsoft マイ アプリを使用できます。 マイ アプリで [RetrieverMediaDatabase] タイルを選択すると、SSO を設定した RetrieverMediaDatabase に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/reviewsnap-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Reviewsnap を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/reviewsnap-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Reviewsnap の間にシングル サインオンを構成する方法について説明します。

この記事では、Reviewsnap と Microsoft Entra ID を統合する方法について説明します。 Reviewsnap を Microsoft Entra ID と統合すると、以下のことができます。

- Reviewsnap にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Reviewsnap に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Reviewsnap でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Reviewsnap では、**SP および IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Reviewsnap を追加する

Microsoft Entra ID への Reviewsnap の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Reviewsnap を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Reviewsnap**」と入力します。
4. 結果パネルから **[Reviewsnap]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Reviewsnap 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Reviewsnap に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Reviewsnap の関連ユーザーとの間にリンク関係を確立する必要があります。

Reviewsnap との Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Reviewsnap の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Reviewsnap テストユーザーの作成** - B.Simon に対応する Reviewsnap ユーザーを作成し、Microsoft Entra でのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Reviewsnap**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://app.reviewsnap.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.reviewsnap.com/auth/saml/callback?namespace=<CUSTOMER_NAMESPACE>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.reviewsnap.com/login`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL で値を更新します。 この値を取得するには、[Reviewsnap クライアント サポート チーム](mailto:support@reviewsnap.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Reviewsnap のセットアップ]** セクションで、要件西多賀って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Reviewsnap の SSO の構成

**Reviewsnap** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Reviewsnap サポート チーム](mailto:support@reviewsnap.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Reviewsnap テスト ユーザーの作成

このセクションでは、Reviewsnap で Britta Simon というユーザーを作成します。 [Reviewsnap サポート チーム](mailto:support@reviewsnap.com)と連携して、Reviewsnap プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Reviewsnap のサインオン URL にリダイレクトされます。
- Reviewsnap のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Reviewsnap に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Reviewsnap] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Reviewsnap に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/revspace-tutorial"} -->
## Microsoft Entra ID で RevSpace for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/revspace-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RevSpace の間にシングル サインオンを構成する方法について説明します。

この記事では、RevSpace と Microsoft Entra ID を統合する方法について説明します。 RevSpace を Microsoft Entra ID と統合すると、次のことができます:

- RevSpace にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って RevSpace に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な RevSpace のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- RevSpace では、**SP および IDP** Initiated SSO がサポートされます。
- RevSpace では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの RevSpace の追加

Microsoft Entra ID への RevSpace の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に RevSpace を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**RevSpace**」と入力します。
4. 結果のパネルから **[RevSpace]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RevSpace 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、RevSpace に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと RevSpace の関連ユーザーとの間にリンク関係を確立する必要があります。

RevSpace に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RevSpace SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RevSpaceでテストユーザーを作成し、B.SimonのRevSpaceでのカウンターパートとして、Microsoft Entraのユーザー表示とリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**RevSpace**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_SUBDOMAIN>.revspace.io/login/callback`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_SUBDOMAIN>.revspace.io/login/callback`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_SUBDOMAIN>.revspace.io/login/callback`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[RevSpace クライアント サポート チーム](mailto:support@revspace.io)にお問い合わせださい。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. RevSpace アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、RevSpace アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名前 | ユーザー.ファーストネーム |
    | 名字 | ユーザーの名字 |
    | 職種 | ユーザー.職名 |
    | 部署 | ユーザーの部署 |
    | 従業員ID | ユーザー.社員ID |
    | 郵便番号 | ユーザー.郵便番号 |
    | 国 | ユーザーの国 |
    | ロール | user.assignedroles |

    注

    RevSpace では、アプリケーションに対してユーザーのロールが割り当てられていることを想定しています。 ユーザーに適切なロールを割り当てることができるように、Microsoft Entra ID でこれらのロールを設定してください。 Microsoft Entra ID でロールを構成する方法については、 [こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Set up RevSpace](RevSpace のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RevSpace SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として RevSpace にサインインします。
2. [ユーザー プロファイル] アイコンを選択し、[ **会社の設定**] を選択します。

    [Image: RevSpace の [Company Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社の設定) のスクリーンショット。]
3. **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** ページで、次の手順を実行します。

    [Image: RevSpace の [設定] のスクリーンショット。]

    ある。 **[Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社) &gt; [Single Sign-On](シングル サインオン)** の順に移動し、**[Metadata Upload](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メタデータのアップロード)** タブを選択します。

    b。 **[XML Metadata] (XML メタデータ)** フィールドに、コピーした **[フェデレーション メタデータ XML]** の値を貼り付けます。

    c. 次に、 **[保存]** を選択します。

#### RevSpace テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを RevSpace に作成します。 RevSpace では、Just-In-Time プロビジョニングがサポートされており、これは既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ RevSpace に存在しない場合は、RevSpace にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる RevSpace のサインオン URL にリダイレクトされます。
- RevSpace のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した RevSpace に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [RevSpace] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した RevSpace に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/reward-gateway-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Reward Gateway を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/reward-gateway-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Reward Gateway に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、Reward Gateway に対してユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成するために Reward Gateway と Microsoft Entra ID で実行する手順を示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

現在、このコネクタはパブリック プレビュー段階にあります。 プレビューの詳細については、[オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)に関するページを参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.

- [Reward Gateway テナント](https://www.rewardgateway.com/)。
- Admin アクセス許可がある Reward Gateway のユーザー アカウント。

### Reward Gateway へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に*割り当て*という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Reward Gateway へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 それが決まれば、[エンタープライズ アプリへのユーザーまたはグループの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)に関するページの手順に従って、これらのユーザーやグループを Reward Gateway に割り当てることができます。

### ユーザーを Reward Gateway に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを Reward Gateway に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Reward Gateway にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### プロビジョニングのための Reward Gateway の設定

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Reward Gateway を構成する前に、Reward Gateway で SCIM プロビジョニングを有効にする必要があります。

1. [Reward Gateway 管理コンソール](https://rewardgateway.photoshelter.com/login/)にサインインします。 **統合**を選択します。

    [Image: Reward Gateway 管理コンソールのスクリーンショット。[統合] オプションが選択されています。]
2. **My Integration** を選択します。

    [Image: 2 つの [統合] オプションのスクリーンショット。[My Integration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/自分の統合) が選択されています。]
3. **[SCIM URL (v2)]** および **[OAuth Bearer Token](OAuth ベアラー トークン)** の値をコピーします。 これらの値は、Reward Gateway アプリケーションの [プロビジョニング] タブの [テナント URL] フィールドと [シークレット トークン] フィールドに入力されます。

    [Image: [My Integration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/自分の統合) パネルのスクリーンショット。[OAuth Bearer Token](OAuth ベアラー トークン) テキスト ボックスが選択されています。]

### ギャラリーからの Reward Gateway の追加

Microsoft Entra ID を使った自動ユーザー プロビジョニング用に Reward Gateway を構成するには、Reward Gateway を Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Reward Gateway を追加するには、次の手順を行います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Reward Gateway**」と入力して **[Reward Gateway]** を選びます。
4. 結果のパネルから **[Reward Gateway]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

    [Image: 結果一覧の Reward Gateway のスクリーンショット。]

### Reward Gateway への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて、Reward Gateway でユーザーとグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Reward Gateway のシングル サインオンに関する記事に記載されている手順に従って、Reward Gateway で SAML ベースの [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/reward-gateway-tutorial)を有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Reward Gateway の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Reward Gateway]** を選択します。

    [Image: アプリケーションの一覧の Reward Gateway リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Reward Gateway テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Reward Gateway に接続できることを確認します。 接続に失敗した場合は、Reward Gateway アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Reward Gateway に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Reward Gateway のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: 6 つのマッピングが表示されている [属性マッピング] セクションのスクリーンショット。]
12. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログの読み方について詳しくは、「[自動ユーザー アカウント プロビジョニングについてのレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)」をご覧ください。

### コネクタの制限事項

Reward Gateway では、現在、グループ のプロビジョニングはサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/reward-gateway-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Reward Gateway を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/reward-gateway-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Reward Gateway の間のシングル サインオンを構成する方法について説明します。

この記事では、Reward Gateway と Microsoft Entra ID を統合する方法について説明します。 Reward Gateway を Microsoft Entra ID と統合すると、次のことが可能になります。

- Reward Gateway にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Reward Gateway に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Reward Gateway でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Reward Gateway では、**IDP** Initiated SSO がサポートされます。
- Reward Gateway では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/reward-gateway-provisioning-tutorial)がサポートされます。

### ギャラリーからの Reward Gateway の追加

Reward Gateway と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に Reward Gateway をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Reward Gateway**」と入力します。
4. 結果のパネルから **[Reward Gateway]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Reward Gateway 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Reward Gateway 用の Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Reward Gateway での関連ユーザーとの間にリンク関係を確立する必要があります。

Reward Gateway 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Reward Gateway の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Reward Gateway のテストユーザーを作成** - Microsoft Entra のユーザー表現にリンクされる Reward Gateway の B.Simon の対になるユーザーを持つようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Reward Gateway**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://<COMPANY_NAME>.rewardgateway.com` |
    | `https://<COMPANY_NAME>.rewardgateway.co.uk/` |
    | `https://<COMPANY_NAME>.rewardgateway.co.nz/` |
    | `https://<COMPANY_NAME>.rewardgateway.com.au/` |
    |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://<COMPANY_NAME>.rewardgateway.com/Authentication/EndLogin?idp=<Unique Id>` |
    | `https://<COMPANY_NAME>.rewardgateway.co.uk/Authentication/EndLogin?idp=<Unique Id>` |
    | `https://<COMPANY_NAME>.rewardgateway.co.nz/Authentication/EndLogin?idp=<Unique Id>` |
    | `https://<COMPANY_NAME>.rewardgateway.com.au/Authentication/EndLogin?idp=<Unique Id>` |
    |  |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、Reward Manager Portal で統合のセットアップを開始します。 詳細については、 https://success.rewardgateway.com/hc/en-us/articles/360038650573-Microsoft-Azure-for-Authentication を参照してください。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Reward Gateway のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Reward Gateway SSO の構成

**Reward Gateway** 側でシングル サインオンを構成するには、Reward Manager Portal で統合のセットアップを開始します。 ダウンロードしたメタデータを使用して署名証明書を取得し、これを構成の際にアップロードします。 詳細については、 https://success.rewardgateway.com/hc/en-us/articles/360038650573-Microsoft-Azure-for-Authentication を参照してください。

#### Reward Gateway テスト ユーザーの作成

このセクションでは、Reward Gateway で Britta Simon というユーザーを作成します。 [Reward Gateway サポート チーム](mailto:clientsupport@rewardgateway.com)と連携して、Reward Gateway プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Reward Gateway では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/reward-gateway-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Reward Gateway に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Reward Gateway] タイルを選択すると、SSO を設定した Reward Gateway に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rewatch-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Rewatch を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rewatch-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Rewatch の間でシングル サインオンを構成する方法について説明します。

この記事では、Rewatch と Microsoft Entra ID を統合する方法について説明します。 Rewatch を Microsoft Entra ID を統合すると、次のことができます。

- Rewatch にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Rewatch に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Rewatch サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Rewatch では、**SP-initiated SSO** および **IDP-initiated SSO** がサポートされます。
- Rewatch では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Rewatch の追加

Microsoft Entra ID への Rewatch の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Rewatch を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに「**Rewatch**」と入力します。
4. 結果パネルから **[再ウォッチ** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Rewatch 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Rewatch に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Rewatch の関連ユーザーとの間にリンク関係を確立する必要があります。

Rewatch に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Rewatch SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Rewatch テスト ユーザーの作成** - B.Simonに対応するテストユーザーをMicrosoft Entraのユーザーとしてリンクさせるためです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Rewatch**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://rewatch.tv/login`
7. Rewatch アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. 上記に加えて、Rewatch アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | ユーザー.グループ |
9. **[保存] を選択します**。
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. [ **再ウォッチの設定** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Rewatch の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Rewatch 企業サイトに管理者としてサインインします。
2. 左側のメニューで **[管理コンソール** ] を選択します。

    [Image: ホーム ページで管理コンソールを再ウォッチします。]
3. **[セキュリティ**] に移動し、**SAML シングル サインオン** セクションで次の手順を実行します。

    [Image: saml シングル サインオン セクション。]

    ある。 **[IdP SSO ターゲット URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    b。 ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容を **IdP 証明書** のテキスト ボックスに貼り付けます。

    c. [ **このチャネルの SAML ログインを有効にする]** をオンにし、[ **保存]** を選択します。

#### Rewatch のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Rewatch に作成します。 Rewatch では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Rewatch にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Rewatch のサインオン URL にリダイレクトされます。
- Rewatch のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Rewatch に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [再ウォッチ] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Rewatch に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rfpio-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に RFPIO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rfpio-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: ユーザー アカウントを RFPIO に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事の目的は、RFPIO に対してユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成するために、RFPIO と Microsoft Entra ID で実行する手順を示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.

- [RFPIO テナント](https://www.rfpio.com/product/)。
- 管理者アクセス許可がある RFPIO のユーザー アカウント。

### RFPIO へのユーザーの割り当て

Microsoft Entra ID では、 *割り当て* と呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーとグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、RFPIO へのアクセスが必要な Microsoft Entra ID 内のユーザー/グループを決定しておく必要があります。 特定した後、次の手順に従い、これらのユーザー、グループ、またはその両方を RFPIO に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを RFPIO に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを RFPIO に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザー/グループを追加で割り当てられます。
- RFPIO にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニング対象の RFPIO を設定する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に RFPIO を構成する前に、RFPIO で SCIM プロビジョニングを有効にする必要があります。

1. RFPIO 管理コンソールにサインインします。 管理コンソールの左下にある [テナント] を選択 **します**。

    [Image: RFPIO 管理コンソールのスクリーンショット]
2. [ **組織の設定] を選択します**。

    [Image: RFPIO 管理者のスクリーンショット]
3. **USER MANAGEMENT**&gt;**SECURITY**&gt;**SCIM** に移動します。

    [Image: RFPIO Add SCIM のスクリーンショット]
4. **自動ユーザー プロビジョニング**が有効になっていることを確認します。 [ **SCIM API トークンの生成]** を選択します。

    [Image: [GENERATE S C I M A P I TOKEN] オプションが強調表示されている [S C I M] セクションのスクリーンショット。]
5. このトークンはセキュリティのために再び表示されないため、 **SCIM API トークン** を保存します。 この値は、RFPIO アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: [SUBMIT](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/送信) を選択した後に表示される [警告] ダイアログ ボックスが表示された [S C I M] セクションのスクリーンショット。]

### ギャラリーからの RFPIO の追加

Microsoft Entra ID との自動ユーザー プロビジョニング対象として RFPIO を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に RFPIO を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから RFPIO を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、「**RFPIO**」と入力し、結果パネルで **RFPIO** を選択し、[**追加**] ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の RFPIO のスクリーンショット]

### RFPIO に対する自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて RFPIO 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

RFPIO のシングル サインオンに関する記事で説明されている手順に従って、RFPIO に対して SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rfpio-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で RFPIO に対する自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**にアクセスする

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **RFPIO** を選択します。

    [Image: アプリケーションの一覧の RFPIO リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [自動] オプションが強調表示されている [プロビジョニング モード] ドロップダウン リストのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、RFPIO テナント URL とシークレット トークンを入力します。 Microsoft Entra ID が RFPIO に接続できることを確認するには、[ **テスト接続** ] を選択します。 接続に失敗した場合は、RFPIO アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. [属性マッピング] セクションで、Microsoft Entra ID から RFPIO に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で RFPIO のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: RFPIO ユーザー属性のスクリーンショット]
12. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「 [自動ユーザー アカウント プロビジョニングに関するレポート」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。

### コネクタの制限事項

- RFPIO では、現在、グループのプロビジョニングはサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rfpio-tutorial"} -->
## Microsoft Entra ID で RFPIO for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rfpio-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RFPIO の間にシングル サインオンを構成する方法について説明します。

この記事では、RFPIO と Microsoft Entra ID を統合する方法について説明します。 RFPIO を Microsoft Entra ID と統合すると、次のことができます:

- RFPIO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して RFPIO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- RFPIO でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- RFPIO では、**SP と IDP** によって開始される SSO がサポートされます。
- RFPIO では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rfpio-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの RFPIO の追加

Microsoft Entra ID への RFPIO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に RFPIO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**RFPIO**」と入力します。
4. 結果のパネルから **[RFPIO]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RFPIO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、RFPIO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと RFPIO の関連ユーザーとの間にリンク関係を確立する必要があります。

RFPIO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RFPIO SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RFPIO テストユーザーの作成** - Microsoft Entra にある B.Simon の表現とリンクされた B.Simon と対応するユーザーを RFPIO で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**RFPIO**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://www.rfpio.com`

    b。 [ **追加の URL の設定] を選択します**。

    c. **[リレー状態]** テキストボックスに文字列値を入力します。 この値を取得するには、[RFPIO サポート チーム](https://www.rfpio.com/contact/)に問い合わせてください。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.app.rfpio.com`
7. RFPIO アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、RFPIO アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名（ファーストネーム） | ユーザー.ファーストネーム |
    | last\_name | ユーザーの名字 |
9. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[RFPIO のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RFPIO SSO の構成

1. 別の Web ブラウザー ウィンドウで、**RFPIO** Web サイトに管理者としてサインインします。
2. 左下隅のドロップダウンを選択します。

    [Image: ペイン下部の下矢印を示すスクリーンショット。]
3. [ **組織の設定] を選択します**。

    [Image: [組織設定] が選択された画面のスクリーンショット。]
4. **機能と統合**を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) の [Features and Integration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/機能と統合) が選択されている画面のスクリーンショット。]
5. **SAML SSO 構成**で [**編集]** を選択します。

    [Image: [SAML S S O 構成] と [編集] ボタンが強調表示されている画面のスクリーンショット。]
6. このセクションでは、次のアクション実行します。

    [Image: SAML が有効になっている [SAML SSO 構成] を示すスクリーンショット。]

    ある。 **ダウンロードしたメタデータ XML** の内容をコピーし、 **[ID 構成]** フィールドに貼り付けます。

    注

    ダウンロードした**フェデレーション メタデータ XML** の内容をコピーするには、**Notepad++** または適切な **XML エディター**を使用します。

    b。 **[検証]** を選択します。

    c. **[検証**] を選択した後、**SAML (有効)** をオンにします。

    d. **送信**を選択します。

#### RFPIO のテスト ユーザーの作成

1. RFPIO 企業サイトに管理者としてサインインします。
2. 左下隅のドロップダウンを選択します。

    [Image: ペイン下部の下矢印を示すスクリーンショット。]
3. [ **組織の設定] を選択します**。

    [Image: [組織設定] が選択された画面のスクリーンショット。]
4. **[チーム メンバー]** を選択します。

    [Image: [設定] の [チーム メンバー] が選択されている画面のスクリーンショット。]
5. [ **メンバーの追加] を選択します**。

    [Image: [メンバーの追加] ボタンを示す画面のスクリーンショット。]
6. **[新しいメンバーの追加]** セクションで、 次の操作を実行します。

    [Image: [新しいメンバーの追加] を示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[Enter one email per line(1 行につき 1 つの電子メール アドレスを入力する)]** フィールドに**電子メール アドレス**を入力します。

    b。 要件に応じて **[ロール]** を選択してください。

    c. [ **メンバーの追加] を選択します**。

    注

    Microsoft Entra アカウント所有者がメールを受け取り、リンクに従ってアカウントを確認すると、そのアカウントがアクティブになります。

注

RFPIO では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rfpio-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる RFPIO サインオン URL にリダイレクトされます。
- RFPIO のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した RFPIO に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで RFPIO タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した RFPIO に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rhombus-systems-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Rhombus Systems を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rhombus-systems-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から Rhombus Systems に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために、Rhombus Systems と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Rhombus Systems](https://www.rhombussystems.com/) に対するユーザーのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Rhombus Systems でユーザーを作成する。
- アクセスが不要になったら、Rhombus Systems のユーザーを削除します。
- Microsoft Entra ID と Rhombus Systems の間でユーザー属性の同期を維持します。
- Rhombus Systems への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rhombus-systems-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある Rhombus Systems のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Rhombus Systems の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Rhombus Systems を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように Rhombus Systems を構成するには、Rhombus Systems サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Rhombus Systems を追加する

Microsoft Entra アプリケーション ギャラリーから Rhombus Systems を追加して、Rhombus Systems へのプロビジョニングの管理を開始します。 SSO のために Rhombus Systems を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Rhombus Systems への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて TestApp でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Rhombus Systems の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Rhombus Systems]** を選択します。

    [Image: アプリケーション一覧の Rhombus Systems リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Rhombus Systems のテナント URL とシークレット トークンを入力します。 Microsoft Entra ID が Rhombus Systems に接続できることを確認するには、[ **テスト接続** ] を選択します。 接続に失敗した場合は、お使いの Rhombus Systems アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Rhombus Systems に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Rhombus Systems のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Rhombus Systems API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Rhombus Systems で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rhombus-systems-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Rhombus Systems を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rhombus-systems-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Rhombus Systems の間にシングル サインオンを構成する方法について説明します。

この記事では、Rhombus Systems と Microsoft Entra ID を統合する方法について説明します。 Rhombus Systems を Microsoft Entra ID を統合すると、次のことができます。

- Rhombus Systems にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Rhombus Systems に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Rhombus Systems でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Rhombus Systems では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Rhombus Systems では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Rhombus Systems の追加

Microsoft Entra ID への Rhombus Systems の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Rhombus Systems を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Rhombus Systems**」と入力します。
4. 結果のパネルから **[Rhombus Systems]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Rhombus Systems 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Rhombus Systems に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Rhombus Systems の関連ユーザーとの間にリンク関係を確立する必要があります。

Rhombus Systems に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Rhombus Systems SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Rhombus Systems のテストユーザーを作成** - B.Simon に対応するユーザーとして、Microsoft Entra のユーザー表現にリンクする Rhombus Systems のユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Rhombus Systems**&gt;**シングルサインオン**に移動します。
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

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://console.rhombussystems.com/login/`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Set up Rhombus Systems](Rhombus Systems のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Rhombus Systems の SSO の構成

1. Rhombus Systems 企業サイトに管理者としてログインします。
2. **[設定]** アイコンに移動し、[**シングル サインオン**] を選択します。
3. **[Single Sign-on](シングル サインオン)** ページで、以下の手順を実行します。

    [Image: SSO 構成の設定を示すスクリーンショット。]

    1. **[Use Single Sign-On](シングル サインオンの使用)** ボタンを有効にします。
    2. **[Just-In-Time User Creation](Just-In-Time ユーザー作成)** ボタンを有効にします。
    3. テキスト ボックスに有効な**チーム名**を入力します。
    4. **[SSO Recovery Users](SSO 復旧ユーザー)** でドロップダウンから**ユーザーを選択**します。
    5. **SP メタデータ** ファイルをダウンロードして、そのメタデータ ファイルを **[Basic SAML Configuration](基本的な SAML 構成)** セクションにアップロードします。
    6. **フェデレーション メタデータ XML** をメモ帳にコピーし、その内容を **[IDP MetaData XML](IDP メタデータ XML)** テキストボックスに貼り付けます。
    7. **保存** を選択します。

#### Rhombus Systems テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Rhombus Systems に作成します。 Rhombus Systems では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Rhombus Systems にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Rhombus Systems のサインオン URL にリダイレクトされます。
- Rhombus Systems のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Rhombus Systems に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Rhombus Systems] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Rhombus Systems に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rightanswers-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に RightAnswers を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rightanswers-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RightAnswers の間にシングル サインオンを構成する方法について説明します。

この記事では、RightAnswers と Microsoft Entra ID を統合する方法について説明します。 RightAnswers を Microsoft Entra ID と統合すると、次のことができます。

- RightAnswers にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して RightAnswers に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

RightAnswers と Microsoft Entra の統合を構成するには、次の項目が必要です:

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- RightAnswers でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- RightAnswers では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの RightAnswers の追加

Microsoft Entra ID への RightAnswers の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に RightAnswers を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**RightAnswers**」と入力します。
4. 結果のパネルから **[RightAnswers]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RightAnswers 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、RightAnswers に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと RightAnswers の関連ユーザーの間にリンク関係を確立する必要があります。

RightAnswers に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RightAnswers SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RightAnswers テスト ユーザーの作成** - B.Simon に対応するユーザーを RightAnswers で作成し、Microsoft Entra との連携を行います。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**RightAnswers**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.rightanswers.com:<identifier>/portal`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.rightanswers.com/portal/ss/`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[RightAnswers クライアント サポート チーム](https://uplandsoftware.com/rightanswers/contact/)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[RightAnswers の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RightAnswers SSO の構成

**RightAnswers** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[RightAnswers サポート チーム](https://uplandsoftware.com/rightanswers/contact/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

注

RightAnswers サポート チームが、実際に SSO を構成する必要があります。 サブスクリプションに対して SSO が有効になっていると、通知が表示されます。

#### RightAnswers テスト ユーザーの作成

Microsoft Entra ユーザーが RightAnswers にサインインできるようにするには、ユーザーを RightAnswers にプロビジョニングする必要があります。 RightAnswers の場合、プロビジョニングは自動化されているため、ユーザー側で必要な操作はありません。

最初のシングル サインオンの試行中に、必要に応じてユーザーが自動的に作成されます。

注

他の RightAnswers ユーザー アカウント作成ツールや、RightAnswers から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる RightAnswers のサインオン URL にリダイレクトされます。
- RightAnswers のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [RightAnswers] タイルを選択すると、このオプションは RightAnswers のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rightcrowd-workforce-management-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に RightCrowd Workforce Management を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rightcrowd-workforce-management-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RightCrowd Workforce Management の間でシングル サインオンを構成する方法について説明します。

この記事では、RightCrowd Workforce Management と Microsoft Entra ID を統合する方法について説明します。 RightCrowd Workforce Management を Microsoft Entra ID と統合すると、次のことができます。

- RightCrowd Workforce Management にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って RightCrowd Workforce Management に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- RightCrowd Workforce Management のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- RightCrowd Workforce Management では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- RightCrowd Workforce Management では、**Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの RightCrowd Workforce Management の追加

Microsoft Entra ID への RightCrowd Workforce Management の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に RightCrowd Workforce Management を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**RightCrowd Workforce Management**」と入力します。
4. 結果パネルから **RightCrowd Workforce Management** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RightCrowd Workforce Management 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使って、RightCrowd Workforce Management に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと RightCrowd Workforce Management の関連ユーザーとの間にリンク関係を確立する必要があります。

RightCrowd Workforce Management に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RightCrowd Workforce Management の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RightCrowd Workforce Management のテスト ユーザーを作成** - RightCrowd Workforce Management で B.Simon の代替ユーザーとして作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**RightCrowd Workforce Management**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 **[識別子]** ボックスに、次のいずれかの URL を入力します。`http://<SUBDOMAIN>.rightcrowdcustomerdomain.com`

    b。 **[応答 URL]** ボックスに、次のいずれかの URL を入力します。`https://<SUBDOMAIN>.rightcrowdcustomerdomain.com/RightCrowd/Saml2/Auth/AssertionComsumerService`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、次のいずれかの URL を入力します: `http://<SUBDOMAIN>.rightcrowdcustomerdomain.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには、[RightCrowd Workforce Management サポート チーム](mailto:info@rightcrowd.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **保存** を選択します。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[RightCrowd Workforce Management のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RightCrowd Workforce Management の SSO の構成

**RightCrowd Workforce Management** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [RightCrowd Workforce Management サポート チーム](mailto:info@rightcrowd.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### RightCrowd Workforce Management のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを RightCrowd Workforce Management に作成します。 RightCrowd Workforce Management では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 RightCrowd Workforce Management にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる RightCrowd Workforce Management のサインオン URL にリダイレクトされます。
- RightCrowd Workforce Management のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した RightCrowd Workforce Management に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [RightCrowd Workforce Management] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した RightCrowd Workforce Management に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rightscale-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Rightscale を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rightscale-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Rightscale の間のシングル サインオンを構成する方法について説明します。

この記事では、Rightscale と Microsoft Entra ID を統合する方法について説明します。 Rightscale を Microsoft Entra ID と統合すると、次のことが可能になります。

- Rightscale へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Rightscale に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Rightscale でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Rightscale では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーからの Rightscale の追加

Microsoft Entra ID への Rightscale の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Rightscale を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Rightscale**」と入力します。
4. 結果パネルから [**Rightscale**] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Rightscale に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Rightscale に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Rightscale の関連ユーザーの間にリンク関係を確立する必要があります。

Rightscale に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Rightscale の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Rightscale で B.Simon に対応するテストユーザーを作成し、Microsoft Entra 表示のユーザーにリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Rightscale]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集のスクリーンショット。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://login.rightscale.com/`
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンク。]
8. **[Set up Rightscale] (Rightscale のセットアップ)** セクションで、要件に従った適切な URL をコピーします。

    [Image: 構成 URL のコピー。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Rightscale SSO の構成

1. アプリケーションに合わせて SSO を構成するには、管理者として RightScale テナントにサインオンする必要があります。
2. 上部のメニューで、[ **設定]** タブを選択し、[ **シングル サインオン**] を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) の [Single Sign-On](シングル サインオン) が選択されている画面のスクリーンショット。]
3. **新しい**ボタンを選択して**、SAML ID プロバイダーを追加します**。

    [Image: SAML ID プロバイダーを追加するための新しいボタンが選択されている画面のスクリーンショット。]
4. **[Display Name]** テキスト ボックスに会社名を入力します。

    [Image: 表示名の入力画面のスクリーンショット。]
5. **[Allow RightScale-initiated SSO using a discovery hint]** を選択して、下のテキストボックスに**ドメイン名**を入力します。

    [Image: ログイン方法を指定する画面のスクリーンショット。]
6. 入手した**ログイン URL** の値を RightScale の **[SAML SSO エンドポイント]** に貼り付けます。

    [Image: SAML S S O エンドポイントの入力画面のスクリーンショット。]
7. 入手した **Microsoft Entra 識別子**の値を RightScale の **[SAML EntityID]** に貼り付けます。

    [Image: SAML エンティティ I D の入力画面のスクリーンショット。]
8. [ **ブラウザー** ] ボタンを選択して、以前にダウンロードした証明書をアップロードします。

    [Image: SAML の署名証明書を指定する画面のスクリーンショット。]
9. **保存** を選択します。

#### Rightscale のテスト ユーザーを作成する

このセクションでは、Rightscale で Britta Simon というユーザーを作成します。 [Rightscale クライアント サポート チーム](mailto:support@rightscale.com)と連携して、Rightscale プラットフォームにユーザーを追加してください。 SSO を使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra の SSO 構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Rightscale のサインオン URL にリダイレクトされます。
- Rightscale のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Rightscale に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Rightscale] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Rightscale に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ringcentral-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に RingCentral を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ringcentral-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID から RingCentral に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために RingCentral ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使って、[RingCentral](https://www.ringcentral.com/office/plansandpricing.html) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- RingCentral でユーザーを作成する
- アクセスが不要になった場合に RingCentral のユーザーを削除する
- Microsoft Entra ID と RingCentral の間でユーザー属性の同期を維持します。
- RingCentral への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ringcentral-tutorial) (推奨)
- コード認証許可フロー認証がサポートされています。

RingCentral は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- [RingCentral テナント](https://www.ringcentral.com/office/plansandpricing.html)
- 管理者アクセス許可がある RingCentral のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と RingCentral の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように RingCentral を構成する

手順 5. の [管理者資格情報] セクションで承認を行うためには、[RingCentral](https://www.ringcentral.com/office/plansandpricing.html) 管理者アカウントが必要です。

RingCentral の管理ポータルの [Account Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント設定) -&gt; [Directory Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ディレクトリの統合) で、[Directory Provider](ディレクトリ プロバイダー) 設定を *[SCIM]* に設定します。[Image: 画像]

注

ユーザーにライセンスを割り当てる方法については、[こちら](https://support.ringcentral.com/s/article/5-10-Adding-Extensions-via-Web?language)のビデオ リンクをご覧ください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから RingCentral を追加する

Microsoft Entra アプリケーション ギャラリーから RingCentral を追加して、RingCentral へのプロビジョニングの管理を開始します。 SSO のために RingCentral を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: RingCentral への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で RingCentral の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[RingCentral]** を選択します。

    [Image: アプリケーションの一覧の [RingCentral] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、RingCentral テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が RingCentral に接続できることを確認します。 接続に失敗した場合は、RingCentral アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から RingCentral に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で RingCentral のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、RingCentral API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | externalId | 糸 |
    | 活動中 | ブール値 |
    | タイトル | 糸 |
    | emails[type eq "仕事"].value | 糸 |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |
12. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

- 2020 年 9 月 10 日 - "displayName" および "manager" 属性のサポートを削除しました。
- 03/15/2021 - 承認方法を永続的なベアラー トークンから OAuth コード付与フローに更新しました。
- 10/28/2021 - 既定のマッピングを **mail-&gt; emails[type eq "work"].value** に更新しました。
- 10/28/2021 - レート制限が読み取りで毎分 300、書き込みで毎分 1000 に更新されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ringcentral-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に RingCentral を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ringcentral-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と RingCentral の間にシングル サインオンを構成する方法について説明します。

この記事では、RingCentral と Microsoft Entra ID を統合する方法について説明します。 RingCentral と Microsoft Entra ID を統合すると、次のことができます。

- RingCentral にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って RingCentral に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

RingCentral は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- RingCentral でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- RingCentral では、 **IDP** Initiated SSO がサポートされます。
- RingCentral では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ringcentral-provisioning-tutorial)。

### ギャラリーから RingCentral を追加する

Microsoft Entra ID への RingCentral の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に RingCentral を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「RingCentral**」と入力します。
4. 結果パネルから **RingCentral** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RingCentral 用に Microsoft Entra SSO を構成してテストする

**Britta Simon** というテスト ユーザーを使用して、RingCentral に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと RingCentral の関連ユーザーとの間にリンク関係を確立する必要があります。

RingCentral に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RingCentral SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **RingCentral テストユーザーを作成** - B.Simon と対応するユーザーを RingCentral に作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**RingCentral** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    1. [ **メタデータ ファイルのアップロード]** を選択します。
    2. **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。
    3. メタデータ ファイルが正常にアップロードされると、[**基本的な SAML 構成]** セクションに**識別子**と**応答 URL** の値が自動的に設定されます。

    注

    **サービス プロバイダー メタデータ ファイル**は、この記事の後半で説明する RingCentral SSO 構成ページで取得します。
6. **サービス プロバイダー メタデータ ファイル**がない場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] ボックスに、次のいずれかの URL を入力します。

    | 識別子 |
    | --- |
    | `https://sso.ringcentral.com` |
    | `https://ssoeuro.ringcentral.com` |

    b。 [ **応答 URL** ] ボックスに、いずれかの URL を入力します。

    | 応答 URL |
    | --- |
    | `https://sso.ringcentral.com/sp/ACS.saml2` |
    | `https://ssoeuro.ringcentral.com/sp/ACS.saml2` |
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RingCentral SSO の構成

1. 別の Web ブラウザー ウィンドウで、RingCentral 企業サイトに管理者としてサインインします
2. 上部の [ツール] を選択 **します**。

    [Image: RingCentral 企業サイトから選択されたツールを示すスクリーンショット。]
3. **[シングル サインオン] に移動します**。

    [Image: [ツール] メニューから選択されている単一 Sign-On を示すスクリーンショット。]
4. [ **シングル サインオン** ] ページの [ **SSO 構成]** セクションで、 **手順 1 で** **[編集]** を選択し、次の手順を実行します。

    [Image: スクリーンショットは、[編集] を選択できる [S S O 構成] ページを示しています。]
5. [ **シングル サインオンの設定** ] ページで、次の手順に従います。

    [Image: スクリーンショットは、I D P メタデータをアップロードできる [単一 Sign-On のセットアップ] ページを示しています。]

    ある。 [ **参照] を** 選択して、以前にダウンロードしたメタデータ ファイルをアップロードします。

    b。 メタデータをアップロードすると、 **SSO の [全般情報** ] セクションに値が自動的に入力されます。

    c. [ **属性マッピング** ]セクションで、[ **電子メール属性を次のようにマップ]** を選択します。 `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`

    d. **[保存] を選択します**。

    え **手順 2 で**[**ダウンロード**]を選択して**サービス プロバイダーメタデータファイル**をダウンロードし**、[基本的な SAML 構成]**セクションでアップロードして、Azure portal で**識別子**と**応答 URL** の値を自動設定します。

    [Image: [S S O 構成] ページを示すスクリーンショット。[ダウンロード] を選択できます。]

    f. 同じページで、[ **SSO の有効化]** セクションに移動し、次の手順を実行します。

    [Image: スクリーンショットは、構成を完了できる [S S O の有効化] セクションを示しています。]

    - [ **SSO サービスの有効化] を選択します**。
    - [ **ユーザーが SSO または RingCentral 資格情報でログインできるようにする]** を選択します。
    - **[保存] を選択します**。

#### RingCentral のテスト ユーザーの作成

このセクションでは、RingCentral で Britta Simon というユーザーを作成します。 [RingCentral クライアント サポート チーム](https://success.ringcentral.com/RCContactSupp)と協力して、RingCentral プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

RingCentral では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ringcentral-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した RingCentral に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [RingCentral] タイルを選択すると、SSO を設定した RingCentral に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rippling-hcm-microsoft-entra-id-integration-tutorial"} -->
## Active Directory でのユーザー プロビジョニング用に Rippling Human Capital Management (HCM) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rippling-hcm-microsoft-entra-id-integration-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-06-18
- Summary: Rippling Human Capital Management (HCM) と Microsoft Entra ID/Active Directory の統合。

このドキュメントでは、Rippling HCM と Microsoft Entra ID/Active Directory を統合するための詳細なガイドを提供します。 この手順には、接続の確立、属性マッピングの構成、アカウント プロビジョニングのテスト、アカウント アクセス規則の構成、プロビジョニングの監視が含まれます。 この統合により、IT 管理者は Microsoft Entra ID ガバナンス ライフサイクル ワークフローを使用してビジネス プロセスを自動化できます。

Rippling HCM 環境を統合する方法の詳細なガイダンスについては、 [ここ](https://app.rippling.com/sign-in/id)の Rippling ガイドを参照してください。 アプリケーション名の横にある **[ヘルプ ドキュメント** ] リンクを選択します。

[Rippling App Shop](https://www.rippling.com/app-shop/app/microsoftactivedirectory) で Microsoft Entra ID/Active Directory とアプリの統合を構成する手順の概要を次に示します。

注

以下に示す手順とスクリーンショットは、Rippling アプリで構築されたエクスペリエンスを示し、統合の深さと柔軟性を強調しています。

### 手順 1 – 接続を確立する

この手順では、IT 管理者は Rippling に同意して、Microsoft Entra ID テナントに API 駆動型プロビジョニング アプリを作成します。 IT 管理者は、新しいユーザーの作成に使用する Active Directory ドメインと組織単位コンテナーの詳細も提供します。

### 手順 2 – 属性マッピングを構成する

アプリ統合には、Active Directory 属性への Rippling ユーザー フィールドの既定のマッピングがあります。 IT 管理者は、この属性マッピングをカスタマイズし、Rippling フローのダウンストリームからオンプレミス Active Directory へのユーザー フィールドを選択できます。 この統合で Microsoft Entra ID ガバナンス ライフサイクル ワークフローを使用するには、属性マッピングに **ユーザーの開始日** と **終了日** のフィールドが存在することを確認します。

[Image: Rippling 属性マッピングを示すスクリーンショット。]

### 手順 3 - アカウントのプロビジョニングをテストする

この手順では、IT 管理者は属性マッピングをテストし、テスト ユーザー プロファイルを使用してアカウントの作成または更新を確認できます。

[Image: アカウント作成ウィンドウの検証のスクリーンショット。]

### 手順 4 – アカウント アクセス規則を構成する

この手順では、IT 管理者が Active Directory のアカウント プロビジョニング規則を構成します。 IT 管理者は、この手順のオプションを使用して、アカウントの作成と失効に関するビジネス ポリシーを適用できます。

[Image: Rippling アカウントのアクセス規則と設定のスクリーンショット。]

### 手順 5 – プロビジョニングを監視する

この手順では、IT 管理者は Rippling によって実行されたアクションを監視し、[ **アクション履歴** ] タブから API 呼び出しを確認できます。ここに示すデータは、Microsoft Entra ID プロビジョニング ログから取得された情報に対応しています。

[Image: [API アクション履歴] タブのスクリーンショット。]

上記の手順を使用して、Rippling の従業員データを Microsoft Entra ID で使用できるようになったら、IT 管理者は、JoinerMover-Leaver ビジネス プロセスを自動化するように Microsoft Entra ID ガバナンス ライフサイクル ワークフローを構成できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/risecom-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Rise.com を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/risecom-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Rise.com の間でシングル サインオンを構成する方法について説明します。

この記事では、Rise.com と Microsoft Entra ID を統合する方法について説明します。 Rise.com を Microsoft Entra ID と統合すると、次のことができます。

- Rise.com にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Rise.com に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Rise.com のサブスクリプションはシングルサインオン (SSO) を有効にしています。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra ID でアプリケーションを追加または管理することもできます。 詳細については、Azure 組み込みロール に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Rise.com では、 **SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。
- Rise.com では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Rise.com を追加する

Microsoft Entra ID への Rise.com の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Rise.com を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリ**&gt;**新しいアプリケーション**にアクセスする。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックス **に「Rise.com** 」と入力します。
4. 結果パネルから **Rise.com** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Rise.com の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Rise.com に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Rise.com の関連ユーザーとの間にリンク関係を確立する必要があります。

Rise.com で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Rise.com SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Rise.com でテストユーザーを作成** - Microsoft Entra のユーザーの B.Simon に対応するユーザーを Rise.com に作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**Rise.com**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    a。 [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://id.rise.com/sso/saml2`

    b。 [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerDomainName>.rise.com`

    手記

    この値は実際の値ではありません。 実際のリレー状態 URL でこの値を更新します。 これらの値 Rise.com 取得するには [、サポート チーム](mailto:Enterprise@rise.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Rise.com アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の画像を示すスクリーンショット。]
8. Rise.com アプリケーションでは、次に示すように、既定の属性が特定の属性に置き換えられます。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. [ **Rise.com のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成を適切な U R L にコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Rise.com SSO の構成

**Rise.com** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を[サポート チーム Rise.com](mailto:Enterprise@rise.com) 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Rise.comのテストユーザーを作成する

このセクションでは、B.Simon というユーザーを Rise.com に作成します。 Rise.com では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Rise.com にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Rise.com Sign-On URL にリダイレクトされます。
- Rise.com Sign-On URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Rise.com に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Rise.com] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Rise.com に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/riskware-tutorial"} -->
## Microsoft Entra ID で Riskware for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/riskware-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Riskware の間にシングル サインオンを構成する方法について説明します。

この記事では、Riskware と Microsoft Entra ID を統合する方法について説明します。 Riskware を Microsoft Entra ID と統合すると、次のことができます。

- Riskware にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Riskware に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

Riskware と Microsoft Entra の統合を構成するには、次の項目が必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra の環境がない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Riskware でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Riskware では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの Riskware の追加

Microsoft Entra ID への Riskware の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Riskware を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Riskware**」と入力します。
4. 結果のパネルから **[Riskware]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Riskware 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Riskware に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Riskware の関連ユーザーとの間にリンク関係を確立する必要があります。

Riskware に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Riskware SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Riskware のテストユーザーを作成する** - RiskwareでB.Simonに対応するユーザーを作成し、それをMicrosoft Entraにおけるユーザーの表現にリンクさせるため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**リスクウェア**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | UAT | `https://riskcloud.net/uat` |
    | 製品 | `https://riskcloud.net/prod` |
    | デモ | `https://riskcloud.net/demo` |

    b。 **[サインオン URL]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 環境 | URL パターン |
    | --- | --- |
    | UAT | `https://riskcloud.net/uat?ccode=<COMPANYCODE>` |
    | 製品 | `https://riskcloud.net/prod?ccode=<COMPANYCODE>` |
    | デモ | `https://riskcloud.net/demo?ccode=<COMPANYCODE>` |

    注意

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値を取得するには、[Riskware クライアント サポート チーム](mailto:support@pansoftware.com.au)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Riskware のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Riskware SSO の構成

1. 別の Web ブラウザー ウィンドウで、Riskware 企業サイトに管理者としてサインインします。
2. 右上の [ **メンテナンス** ] を選択してメンテナンス ページを開きます。

    [Image: Riskware 構成、メンテナンスを示すスクリーンショット。]
3. メンテナンス ページで、[ **認証**] を選択します。
4. **[Authentication Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の構成)** ページで、次の手順に従います。

    [Image: Riskware 構成、認証構成を示すスクリーンショット。]

    ある。 認証の **[Type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/種類)**として **[SAML]** を選択します。

    b。 **[Code](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/コード)** ボックスにコードを入力します (例: AZURE\_UAT)。

    c. **[Description](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/説明)** ボックスに説明を入力します (例:SSO 用 AZURE 構成)。

    d. **[シングル サインオン ページ**] テキストボックスに、**[ログイン URL]** の値を貼り付けます。

    え **[サインアウト ページ]** テキストボックスに、 **[ログアウト URL]** の値を貼り付けます。

    f. **[Post フォーム フィールド]** ボックスに、SAML を含む Post 応答にあるフィールド名を入力します (例: SAMLResponse)。

    ジー **[XML Identity Tag Name](XML ID タグ名)** ボックスに、SAML 応答内の一意の識別子を含む属性を入力します (例: NameID)。

    h. Azure Portal からダウンロードした**メタデータ Xml** をメモ帳で開き、メタデータ ファイルから証明書をコピーして **[証明書]** ボックスに貼り付けます。

    一. **[Consumer URL](コンシューマー URL)** ボックスに、サポート チームから入手した**応答 URL** の値を貼り付けます。

    j. **[Issuer](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/発行者)** ボックスに、サポート チームから入手した**識別子**の値を貼り付けます。

    注意

    これらの値を取得するには、[Riskware クライアント サポート チーム](mailto:support@pansoftware.com.au)にお問い合わせください

    ケー **[Use POST](POST を使用する)** チェックボックスをオンにします。

    l. **[Use SAML Request](SAML 要求を使用する)** チェックボックスをオンにします。

    m. **保存** を選択します。

#### Riskware テスト ユーザーの作成

Microsoft Entra ユーザーが Riskware にサインインできるようにするには、Riskware にプロビジョニングする必要があります。 Riskware では、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. セキュリティ管理者として Riskware にサインインします。
2. 右上の [ **メンテナンス** ] を選択してメンテナンス ページを開きます。

    [Image: Riskware 構成、メンテナンスを示すスクリーンショット。]
3. メンテナンス ページで、**ピープル**を選択します。

    [Image: Riskware 構成、ユーザーを示すスクリーンショット。]
4. **[Details](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細)** タブを選択し、次の手順を実行します。

    [Image: Riskware 構成、詳細を示すスクリーンショット。]

    ある。 **[Person Type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの種類)** で、ユーザーの種類を選択します (例: [Employee](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/従業員))。

    b。 **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名前を入力します (例: **Britta**)。

    c. **[Surname](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (例: **Simon**)。
5. **[セキュリティ]** タブで、次の手順に従います。

    [Image: Riskware 構成、セキュリティを示すスクリーンショット。]

    ある。 **[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** セクションで、既に設定済みの**認証**モードを選択します (例:SSO 用 AZURE 構成)。

    b。 **[Logon Details](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ログオンの詳細)** セクションの **[User ID](ユーザー ID)** ボックスに、ユーザーのメール アドレスを入力します (例: `brittasimon@contoso.com`)。

    c. **[Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード)** ボックスに、ユーザーのパスワードを入力します。
6. **[Organization](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/組織)** タブで、次の手順に従います。

    [Image: Riskware 構成、組織を示すスクリーンショット。]

    ある。 **Level1** 組織としてオプションを選択します。

    b。 **[Person's Primary Workplace](ユーザーのプライマリ ワークプレース)** セクションで、**[Location](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/場所)** ボックスに場所を入力します。

    c. **[Employee](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/従業員)** セクションで、**[Employee Status](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/従業員のステータス)** を選択します (例: [Casual](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/カジュアル))。

    d. **保存** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Riskware Sign-On URL にリダイレクトされます。
- Riskware のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Riskware] タイルを選択すると、Riskware Sign-On URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->
