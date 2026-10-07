# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 7)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 79

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/darwinbox-entra-integration-tutorial"} -->
## Darwinbox HR と Microsoft Entra ID の統合 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/darwinbox-entra-integration-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-06-19
- Summary: Darwinbox HR と Microsoft Entra ID を統合して、ユーザー プロビジョニングを自動化し、ライフサイクル ワークフローを管理し、人事主導のプロセスを合理化する方法について説明します。

このドキュメントでは、Darwinbox と Microsoft Entra ID を統合するための詳細なガイドを提供します。 この手順には、接続の確立、属性マッピングの構成、アカウント プロビジョニングのテスト、アカウント アクセス規則の構成、プロビジョニングの監視が含まれます。 この統合を使用して、Microsoft Entra ID で直接クラウド ネイティブ ユーザーを構成します。 この統合により、IT 管理者は Microsoft Entra ID ガバナンス ライフサイクル ワークフローを使用してビジネス プロセスを自動化できます。

お使いの Darwinbox 環境を統合する方法の詳細なガイダンスについては、 [この](https://help.darwinbox.com/r/Integration-Templates/Darwinbox-Microsoft-Entra-ID-Connector)記事の「Darwinbox ガイド」を参照してください。

次の概要手順に従って、Darwinbox ポータルで Microsoft Entra ID とアプリの統合を構成します。

### シングルテナント アプリの登録を作成する

この手順では、Microsoft Entra ID でシングルテナント アプリケーションを作成し、必要なアクセス許可を割り当てます。 これにより、Darwinbox はアプリケーションのクライアント資格情報を使用してプロビジョニング ジョブを作成し、ユーザー データを Microsoft Entra ID テナントに安全に送信できます。

Microsoft Entra 管理センターに移動し、[ **アプリの登録**] を選択し、[ **新しい登録**] を選択します。 次に示すように、シングルテナント アプリを作成します。

[Image: Microsoft Entra ID の [アプリケーションの登録] ページのスクリーンショット。]

次の 3 つの Microsoft Graph アプリケーションのアクセス許可を追加して、Darwinbox がプロビジョニング ジョブを作成できるようにします。 `Application.ReadWrite.OwnedBy`、ユーザー データを `SyncrhonizationData-User.Upload.OwnedBy`送信し、プロビジョニング ログ `ProvisioningLog.Read.All`確認します。

[Image: API アクセス許可の Microsoft Entra ID の [Darwinbox Sync] ページのスクリーンショット。]

クライアント シークレットを作成し、そのガイドで指定されているように、その資格情報を Darwinbox に提供します。

### Darwinbox Studio でコネクタを構成する

1. Darwinbox Studio を開き、 **コネクタ ライブラリ**に移動します。
2. Microsoft を検索 **します**。 **Microsoft** 親アプリ コネクタと **Microsoft Entra** 子アプリ コネクタをインストールします。

[Image: Darwinbox Studio のスクリーンショット。]

1. **Microsoft** アプリを開き、手順 1 で取得した接続パラメーターを構成します。 **クライアント ID**、**クライアント シークレット**、**OAuth トークン エンドポイント**の詳細を指定します。 ここで指定した接続情報は、Microsoft Entra ID テナントにプロビジョニング アプリを作成するために、Darwinbox によって使用されます。[Image: Microsoft の接続の作成のスクリーンショット。]
2. レシピ タスクを手動でトリガー **します。Microsoft Entra SCIM でアプリケーションとジョブを構成します**。 これにより、ユーザー情報の送信に使用する API 駆動型プロビジョニング ジョブが作成されます。[Image: Entra でアプリケーションとジョブを構成するスクリーンショット。]
3. Microsoft Entra 管理センターで、 **Enterprise Applications**を参照し、Darwinbox によって作成されたプロビジョニング アプリを開きます。
    1. [概要] ブレードから **サービス プリンシパル ID/オブジェクト ID を** コピーします。
    2. このアプリの [プロビジョニング] ブレードを開き、[概要] セクションの **[技術情報の表示]** に移動します。
    3. **プロビジョニング ジョブ ID を**コピーします。
4. Darwinbox Studio で **Microsoft Entra** アプリを開き、接続の詳細を構成します。具体的には、 **ServicePrincipalID** と **プロビジョニング ジョブ ID を入力します**。[Image: Microsoft Entra の接続の編集のスクリーンショット。]

### Darwinbox と Entra ID で属性マッピングを構成する

#### Darwinbox 属性を Entra ID SCIM 属性にマップする

Darwinbox 統合ガイドを参照し、次の 3 つの CSV ファイルを作成します。このファイルは、このファイルを、Darwinbox レシピの入力として使用します。

- Darwinbox 属性を Entra ID SCIM 属性にマップする CSV ファイル。 このファイルは、Darwinbox レシピの入力として使用されます。

    [Image: Darwinbox から Entra キーへの CSV ファイルの例のスクリーンショット。]
- グループの会社名または部署に基づいて電子メール ID の作成に使用するドメインを指示する CSV ファイル。
- グループとライセンスを割り当てる方法を指示する CSV ファイル (省略可能)。

#### Entra プロビジョニング ジョブに Darwinbox カスタム属性を追加する

Entra プロビジョニング ジョブで次のカスタム の Darwinbox SCIM 属性を導入するには、 [ここに](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-custom-attributes#step-1---extend-the-provisioning-app-schema) 記載されている手順を参照してください。

- urn:ietf:params:scim:schemas:extension:Darwinbox:1.0:User:UsageLocation
- urn:ietf:params:scim:schemas:extension:Darwinbox:1.0:User:EmployeeType
- urn:ietf:params:scim:schemas:extension:Darwinbox:1.0:User:HireDate
- urn:ietf:params:scim:schemas:extension:Darwinbox:1.0:User:TerminationDate

Microsoft Entra ID API ベースのプロビジョニング ジョブ属性マッピングを確認して更新します。 Joiner-Mover-Leaver ライフサイクル ワークフローを構成できるように、マッピングに `employeeHireDate` 属性と `employeeLeaveDateTime` 属性が含まれていることを確認します。[Image: [属性マッピング] 画面のスクリーンショット。]

#### ユーザー プロビジョニング用に Darwinbox の自動化を設定する

コネクタを構成すると、従業員の Joiner-Mover-Leaver ライフサイクルを管理するための複数のレシピが作成されます。

[Image: Microsoft Entra に関する、Darwinbox のおすすめレシピのスクリーンショット。]

Microsoft Entra ID テナントでユーザー アカウントの作成、更新、削除を有効にするニーズに基づいてレシピを構成します。

#### プロビジョニングを監視する

プロビジョニング イベントの状態を監視するには、プロビジョニング ログに移動するか、プロビジョニング ブックを使用します。

- [Microsoft Entra ID のユーザー プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)
- [Microsoft Entra プロビジョニング ログを分析する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-provisioning-logs)

#### Joiner-Mover-Leaver ライフサイクル ワークフローの管理

人事主導のプロビジョニング プロセスを拡張して、新入社員、雇用の変更、退職に対するビジネス プロセスとセキュリティ制御を自動化します。 [Microsoft Entra ID ガバナンス ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)を使用して、次のような JoinerMover-Leaver ワークフローを構成します。

- 新しい採用者が参加する日の数日前に、マネージャーに電子メールを送信し、ユーザーをグループに追加し、初回ログインの一時的なアクセス パスを生成します。
- ユーザーの部署、役職、またはグループ メンバーシップに変更がある場合は、カスタム タスクを起動します。
- 作業の最終日に、マネージャーにメールを送信し、グループとライセンスの割り当てからユーザーを削除します。
- 退職の "X" 日後、Microsoft Entra ID からユーザーを削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/darwinbox-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Darwinbox を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/darwinbox-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Darwinbox 間にシングル サインオンを構成する方法について説明します。

この記事では、Darwinbox と Microsoft Entra ID を統合する方法について説明します。 Darwinbox を Microsoft Entra ID と統合すると、次のことができます。

- Darwinbox にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Darwinbox に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Darwinbox は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Darwinbox でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Darwinbox では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Darwinbox の追加

Microsoft Entra ID への Darwinbox の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Darwinbox を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Darwinbox**」と入力します。
4. 結果パネルで **[Darwinbox]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Darwinbox 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Darwinbox で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Darwinbox の関連ユーザーとの間にリンク関係を確立する必要があります。

Darwinbox に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Darwinbox SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Darwinbox テストユーザーの作成** - Darwinbox で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーの表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Darwinbox**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.darwinbox.in/adfs/module.php/saml/sp/metadata.php/<CUSTOM_ID>`
    2. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.darwinbox.in/`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Darwinbox クライアント サポート チーム](https://darwinbox.com/contact-us.php)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Darwinbox のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Darwinbox SSO の構成

**Darwinbox** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Darwinbox サポート チーム](https://darwinbox.com/contact-us.php)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Darwinbox のテスト ユーザーの作成

このセクションでは、Darwinbox で B.Simon というユーザーを作成します。 [Darwinbox サポート チーム](https://darwinbox.com/contact-us.php)と連携し、Darwinbox プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Darwinbox のサインオン URL にリダイレクトされます。
- Darwinbox のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Darwinbox] タイルを選択すると、このオプションは、Darwinbox のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。

### Darwinbox (モバイル) の SSO のテスト

1. Darwinbox モバイル アプリケーションを開きます。 **Enter Organization URL** を選択し、テキストボックスに組織のURLを入力して、矢印ボタンを選択します。

    [Image: [Enter Organization U R L](組織の U R L の入力) が選択されている]
2. 複数のドメインがある場合は、ドメインを選択します。

    [Image: サンプル ドメインが選択されている [Choose your domain](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ドメインの選択) 画面を示すスクリーンショット。]
3. Darwinbox アプリケーションに Microsoft Entra ID メールを入力し、[ **次へ**] を選択します。

    [Image: [Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ) ボタンが強調表示されている [Sign in](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サインイン) 画面を示すスクリーンショット。]
4. Darwinbox アプリケーションに Microsoft Entra パスワードを入力し、[ **サインイン**] を選択します。

    [Image: [Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ) ボタンが強調表示されている [Sign into options](サインイン オプション) 画面を示すスクリーンショット。]
5. 最後に、サインインに成功すると、アプリケーションのホームページが表示されます。

    [Image: Darwinbox モバイル アプリ]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/databasics-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に DATABASICS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/databasics-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と DATABASICS の間でシングル サインオンを構成する方法について説明します。

この記事では、DATABASICS と Microsoft Entra ID を統合する方法について説明します。 DATABASICS と Microsoft Entra ID を統合すると、次のことができます。

- DATABASICS にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して DATABASICS に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/free/?WT.mc_id=A261C142F)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- DATABASICS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- DATABASICS では、**SP** 起動型 SSO をサポートしています。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから DATABASICS を追加する

Microsoft Entra ID への DATABASICS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に DATABASICS を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**DATABASICS**」と入力します。
4. 結果パネル **DATABASICS** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### DATABASICS の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、DATABASICS に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと DATABASICS の関連ユーザーとの間にリンク関係を確立する必要があります。

DATABASICS に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **DATABASICS SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **DATABASICS テスト ユーザーの作成** - DATABASICS で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**DATABASICS**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    a. **識別子 (エンティティ ID)** テキスト ボックスに、値を入力します: `DATA-BASICS_SP`

    b。 [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<sitenumber>.data-basics.net/<clientname>/saml_sso.jsp`

    手記

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 これらの値を取得するには、[DATABASICS クライアント サポート チーム](https://www.data-basics.com/support/)に連絡してください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **DATABASICS** のセットアップセクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### DATABASICS SSO の構成

DATABASICS **側** シングル サインオンを構成するには、ダウンロードした **フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL [DATABASICS サポート チーム](https://www.data-basics.com/support/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### DATABASICS テスト ユーザーの作成

このセクションでは、DATABASICS で Britta Simon というユーザーを作成します。 [DATABASICS サポート チームの](https://www.data-basics.com/support/) と連携して、DATABASICS プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる DATABASICS サインオン URL にリダイレクトされます。
- DATABASICS のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [DATABASICS] タイルを選択すると、このオプションは DATABASICS のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/databook-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Databook を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/databook-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Databook の間のシングル サインオンを構成する方法について説明します。

この記事では、Databook と Microsoft Entra ID を統合する方法について説明します。 データブックは、会社の財務と戦略的な優先順位に関する分析情報を提供し、影響の大きい推奨事項を提供するために最適な Microsoft ソリューションをマップするカスタマー インテリジェンス プラットフォームです。 Databook を Microsoft Entra ID と統合すると、次のことが可能になります。

- Databook にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Databook に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Databook の Microsoft Entra シングル サインオンを構成してテストします。 Databook は、**SP** と **IDP** によって開始されるシングル サインオンをサポートし、**Just In Time** ユーザー プロビジョニングもサポートします。

### [前提条件]

Databook を Microsoft Entra ID と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Databook でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Databook アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Databook を追加する

Microsoft Entra アプリケーション ギャラリーから Databook を追加して、Databook でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Databook]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ａ． **[識別子]** ボックスに、`urn:auth0:databook:<CustomerID>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://databook.auth0.com/login/callback?connection=<CustomerID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://databook.auth0.com/login?client=<ID>&connection=<CustomerID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 [Databook クライアント サポート チーム](mailto:info@trydatabook.com)に問い合わせて、これらの値を取得します。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Databook アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. その他に、Databook アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | グループタグ | ユーザー.グループ |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### Databook の SSO を構成する

**Databook** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Databook サポート チーム](mailto:info@trydatabook.com)に送信する必要があります。 サポート チームは、コピーした URL を使用して、アプリケーションでシングル サインオンを構成します。

#### Databook のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Databook に作成します。 Databook では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Databook にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Databook のサインオン URL にリダイレクトされます。
- Databook のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Databook に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [データブック] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Databook に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/datacamp-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に DataCamp を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/datacamp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と DataCamp の間でシングル サインオンを構成する方法について説明します。

この記事では、DataCamp と Microsoft Entra ID を統合する方法について説明します。 DataCamp と Microsoft Entra ID を統合すると、次のことができます。

- DataCamp にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して DataCamp に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- DataCamp でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- DataCamp では、**SP および IDP** によって開始される SSO がサポートされます。
- DataCamp では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから DataCamp を追加する

Microsoft Entra ID への DataCamp の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に DataCamp を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**DataCamp**」と入力します。
4. 結果パネル **DataCamp** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### DataCamp の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、DataCamp に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと DataCamp の関連ユーザーとの間にリンク関係を確立する必要があります。

DataCamp に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **DataCamp SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **DataCamp のテストユーザーを作成 - DataCamp** で B.Simon に相当するユーザーを作成し、そのユーザーを Microsoft Entra 内の B.Simon の表現にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[DataCamp]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、アプリケーションを IDP **によって開始されるモードで** 構成する場合は、次の手順を実行します。

    a. [**識別子**] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://www.datacamp.com/groups/<group-slug>/sso/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://auth.datacamp.com/realms/datacamp-users/broker/b2b-sso-group-<group-identifier>/endpoint/clients/datacamp-saml-login`。 チェック ボックスをオンにして、この URL を既定値としてマークします。

    c. [ **応答 URL の追加** ] をクリックして[ **応答 URL** ] フィールドに新しいテキスト ボックスを追加し、次のパターンを使用して URL を入力します。 `https://auth.datacamp.com/realms/datacamp-users/broker/b2b-sso-group-<group-identifier>/endpoint`

    手記

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 [これらの値を取得するためには、DataCamp クライアントサポートチーム](https://support.datacamp.com/hc/en-us) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. DataCamp アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    イメージ [Image: image]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [**DataCamp** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### DataCamp の SSO の構成

**DataCamp** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [DataCamp サポート チーム](https://support.datacamp.com/hc/en-us)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### DataCamp テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを DataCamp に作成します。 DataCamp では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 DataCamp にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる DataCamp のサインオン URL にリダイレクトされます。
- DataCamp のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した DataCamp に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [DataCamp] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した DataCamp に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/datadog-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Datadog を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/datadog-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-05
- Summary: Microsoft Entra IDから Datadog にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Datadog と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID では自動的に、[Datadog](https://www.datadog.com/) に対し、Microsoft Entra プロビジョニング サービスを使用して、ユーザーのプロビジョニングとデプロビジョニングを行います。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

Datadog では、次のプロビジョニングとアクセスの機能がサポートされています。

- Datadog でユーザーを作成します。
- accessが不要になった場合は、Datadog のユーザーを削除します。
- Microsoft Entra IDと Datadog の間でユーザー属性の同期を維持します。
- Datadog に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/datadog-tutorial)します (推奨)。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Datadog のユーザー アカウント。

### 手順 1: プロビジョニングの展開を計画する

プロビジョニングを構成する前に、次の計画タスクを完了します。

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
- Microsoft Entra ID と Datadog 間でマッピングするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Datadog を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように Datadog を構成するには、Datadog サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Datadog を追加する

Microsoft Entra アプリケーション ギャラリーから Datadog を追加して、Datadog へのプロビジョニングの管理を開始します。 SSO 用に Datadog を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 [ギャラリーからアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)方法の詳細を確認します。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

プロビジョニングに含めるユーザーとグループを定義するには、次の手順に従います。

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Datadog への自動ユーザー プロビジョニングを構成する

次の手順では、Microsoft Entra IDのユーザー割り当てに基づいて Datadog のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する方法について説明します。

#### Microsoft Entra IDで Datadog の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Datadog** を選択します。

    [Image: アプリケーションの一覧の Datadog リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Datadog テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Datadog に接続できることを確認します。 接続に失敗した場合は、Datadog アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Datadog に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Datadog のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Datadog API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理でサポートされます | Datadog によって求められている |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | アクティブ | ブール値 |  |  |
    | タイトル | 文字列 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | name.formatted | 糸 |  |  |
12. スコープ フィルターを構成するには、 [ユーザー アカウントをプロビジョニングするためのスコープ フィルターの定義に関するページを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを有効にした後、次のガイダンスを使用して同期アクティビティを監視し、問題のトラブルシューティングを行います。

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/datadog-tutorial"} -->
## Microsoft Entra ID で Datadog for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/datadog-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Datadog の間のシングル サインオンを構成する方法について説明します。

この記事では、Datadog と Microsoft Entra ID を統合する方法について説明します。 Datadog を Microsoft Entra ID と統合すると、次のことが可能になります。

- Datadog にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Datadog に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Datadog でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Datadog では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Datadog では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/datadog-provisioning-tutorial)。

### ギャラリーからの Datadog の追加

Microsoft Entra ID への Datadog の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Datadog を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Datadog**」と入力します。
4. 結果のパネルから **[Datadog]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Datadog 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Datadog に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Datadog での関連ユーザーとの間にリンク関係を確立する必要があります。

Datadog 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Datadog SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. Datadogのテストユーザーを作成 - DatadogでB.Simonに対応するユーザーを作成し、Microsoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Datadog** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて**、シングル サインオン**を選択します。
3. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。
4. アプリケーションは Azure と事前に統合済みのため、 **[基本的な SAML 構成]** セクションでは、ユーザーは何のアクションも実行しません。
5. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.datadoghq.com/account/login/id/<CUSTOM_IDENTIFIER>`

    注

    これは実際の値ではありません。 この値は、[Datadog の SAML 設定](https://app.datadoghq.com/organization-settings/login-methods/saml)にある実際のサインオン URL で更新します。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。 IdP Initiated ログインと SP Initiated ログインを一緒に使用するには、Azure 内に構成されている ACS URL の両方のバージョンが必要です。
6. **保存** を選択します。
7. [ **SAML を使用したシングル Sign-On の設定** ] ページの [ **ユーザー属性と要求**] で、鉛筆アイコンを選択して設定を編集します。
8. [グループ要求の **追加] ボタンを** 選択します。 Microsoft Entra ID では既定で、グループ要求名は URL です。 例: `http://schemas.microsoft.com/ws/2008/06/identity/claims/groups`。 これを **groups** などの表示名の値に変更する場合は、**[詳細オプション]** を選択し、グループ要求の名前を **groups** に変更します。

    注

    ソース属性は `Group ID` に設定されています。 これは、Microsoft Entra ID におけるグループの UUID です。 つまり、グループ ID は、グループ名としてではなく、グループ要求属性値として Microsoft Entra IDによって送信されます。 グループ名ではなくグループ ID にマップするように Datadog のマッピングを変更する必要があります。 詳細については、[Datadog の SAML マッピング](https://docs.datadoghq.com/account_management/saml/#mapping-saml-attributes-to-datadog-roles)に関するページを参照してください。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。
10. **[Datadog のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Datadog SSO の構成

**Datadog** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** を [Datadog の SAML 設定](https://app.datadoghq.com/organization-settings/login-methods/saml)でアップロードする必要があります。

### SSO のテスト

次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Datadog のサインオン URL にリダイレクトされます。
- Datadog のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Datadog に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリ ポータルで [Datadog] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Datadog に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリ ポータルの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関する記事を参照してください。

#### テナントのすべてのユーザーがアプリで認証できるようにする

このセクションでは、1 人のユーザーに Datadog 側でアカウントが与えられている場合、テナントの誰もが Datadog にアクセスできるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Datadog]** を開いてください。
3. アプリの概要ページの **[管理]** の下で **[プロパティ]** を選択します。

    [Image: [プロパティ] リンク]
4. **[ユーザーの割り当てが必要ですか?]** で **[いいえ]** を選択します。

    [Image: ユーザーの割り当てが不要]
5. **保存** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/datahug-tutorial"} -->
## Microsoft Entra ID で Datahug for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/datahug-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Datahug の間のシングル サインオンを構成する方法について説明します。

この記事では、Datahug と Microsoft Entra ID を統合する方法について説明します。 Datahug を Microsoft Entra ID と統合すると、次のことが可能になります。

- Datahug にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Datahug に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Datahug でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Datahug では、**SP** と **IDP** によって開始される SSO がサポートされます。

### ギャラリーからの Datahug の追加

Microsoft Entra ID への Datahug の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Datahug を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Datahug**」と入力します。
4. 結果のパネルから **[Datahug]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Datahug 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Datahug に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Datahug での関連ユーザーとの間にリンク関係を確立する必要があります。

Datahug 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Datahug の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Datahug テスト ユーザーの作成** - Datahug における B.Simon の対となるユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Datahug]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを**IDP** イニシエートモードで構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://apps.datahug.com/identity/<uniqueID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://apps.datahug.com/identity/<uniqueID>/acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://apps.datahug.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Datahug クライアント サポート チーム](https://www.sap.com/corporate/en/company/office-locations.html)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用したシングル Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して **[SAML 署名証明書** ] ダイアログを開き、次の手順を実行します。

    [Image: SAML 署名証明書の編集]

    a. **[署名オプション]** で **[SAML アサーションへの署名]** を選択します。

    b。 **[署名アルゴリズム]** で **[SHA-1]** を選択します。

    c. **保存** を選択します。
9. **[Datahug のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Datahug SSO の構成

**Datahug** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Datahug サポート チーム](https://www.sap.com/corporate/en/company/office-locations.html)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Datahug テスト ユーザーの作成

Microsoft Entra ユーザーが Datahug にサインインできるようにするには、ユーザーを Datahug にプロビジョニングする必要があります。 Datahug の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. Datahug 企業サイトに管理者としてサインインします。
2. 右上隅の **歯車** アイコンをポイントし、[ **設定]** を選択します。

3. [ **ユーザー** ] を選択し、[ **ユーザーの追加]** タブを選択します。

    [Image: [People](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) タブと [Add Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) が選択されている [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) ページを示すスクリーンショット。]
4. アカウントを作成するユーザーの電子メールを入力し、[ **追加**] を選択します。

    [Image: 従業員の追加を示すスクリーンショット。]

    注

    **[Send welcome email]** チェック ボックスをオンにすると、ユーザーに登録メールを送信できます。 Salesforce のアカウントを作成している場合、ウェルカム メールは送信されません。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Datahug のサインオン URL にリダイレクトされます。
- Datahug のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Datahug に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Datahug] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Datahug に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/datasite-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Datasite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/datasite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Datasite の間でシングル サインオンを構成する方法について説明します。

この記事では、Datasite と Microsoft Entra ID を統合する方法について説明します。 Datasite と Microsoft Entra ID を統合すると、次のことができます。

- Datasite にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Datasite に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Datasite でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Datasite では、**SP** によって開始される SSO がサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Datasite を追加する

Microsoft Entra ID への Datasite の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Datasite を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Datasite**」と入力します。
4. 結果パネルから**Datasite**を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Datasite の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Datasite に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Datasite の関連ユーザーとの間にリンク関係を確立する必要があります。

Datasite に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Datasite SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Datasite テストユーザーの作成** - Microsoft Entra のユーザー表現である B.Simon にリンクされた、Datasite 内での B.Simon に対応するユーザーを作成します。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Datasite]**&gt;**[シングル サインオン]** の順にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    基本的な SAML 構成の編集
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    a. [**応答 URL** テキスト ボックスに、URL: `https://auth.datasite.com/sp/ACS.saml2` を入力します。

    b。 [**サインオン URL** テキスト ボックスに、URL: `https://auth.datasite.com/sp/ACS.saml2` を入力します。
6. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [**Datasite** のセットアップ] セクションで、必要に応じて適切な URL をコピーしてください。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Datasite SSO の構成

Datasite **側** シングル サインオンを構成するには、ダウンロードした **証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を Datasite サポート チーム 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Datasite テスト ユーザーの作成

このセクションでは、Datasite で B.Simon というユーザーを作成します。 [Datasite サポート チームの](mailto:service@datasite.com) と連携して、Datasite プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Datasite のサインオン URL にリダイレクトされます。
- Datasite のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [データサイト] タイルを選択すると、このオプションは Datasite のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/datava-enterprise-service-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Datava Enterprise Service Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/datava-enterprise-service-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Datava Enterprise Service Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Datava Enterprise Service Platform と Microsoft Entra ID を統合する方法について説明します。 Datava Enterprise Service Platform と Microsoft Entra ID を統合すると、次のことが可能になります。

- Datava Enterprise Service Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Datava Enterprise Service Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Datava Enterprise Service Platform は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Datava Enterprise Service Platform でのシングルサインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Datava Enterprise Service Platform では、 **SP** によって開始される SSO がサポートされます。
- Datava Enterprise Service Platform では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Datava Enterprise Service Platform を追加する

Datava Enterprise Service Platform と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Datava Enterprise Service Platform をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Datava Enterprise Service Platform**」と入力します。
4. 結果パネルから **Datava Enterprise Service Platform** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Datava Enterprise Service Platform の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Datava Enterprise Service Platform に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Datava Enterprise Service Platform での関連ユーザーとの間にリンク関係を確立する必要があります。

Datava Enterprise Service Platform の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Datava Enterprise Service Platform の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Datava Enterprise Service Platform のテストユーザーを作成** - Microsoft Entra でのユーザー表現にリンクされた、Datava Enterprise Service Platform の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Datava Enterprise Service Platform**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次の値を入力します。 `https://samlsp.datava.com`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://go.datava.com/saml/module.php/saml/sp/saml2-acs.php/<TENANT_NAME>-sp`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://go.datava.com/<TENANT_NAME>`

    注

    TENANT\_NAME値を取得するには、 [Datava Enterprise Service Platform クライアント サポート チーム](mailto:support@datava.com) に問い合わせてください。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Datava Enterprise Service Platform の SSO の構成

**Datava Enterprise Service Platform** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Datava Enterprise Service Platform サポート チーム](mailto:support@datava.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Datava Enterprise Service Platform にテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Datava Enterprise Service Platform に作成します。 Datava Enterprise Service Platform では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Datava Enterprise Service Platform にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Datava Enterprise Service Platform のサインオン URL にリダイレクトされます。
- Datava Enterprise Service Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Datava Enterprise Service Platform] タイルを選択すると、このオプションは Datava Enterprise Service Platform のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/datto-file-protection-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Datto File Protection シングル サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/datto-file-protection-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Datto File Protection Single Sign On の間でシングル サインオンを構成する方法について説明します。

この記事では、Datto File Protection シングル サインオンと Microsoft Entra ID を統合する方法について説明します。 Datto File Protection Single Sign On を Microsoft Entra ID と統合すると、次のことができます。

- Datto File Protection Single Sign On にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Datto File Protection Single Sign On に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Datto File Protection Single Sign On が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Datto File Protection Sign On では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。

### ギャラリーから Datto File Protection Single Sign On を追加する

Datto File Protection Single Sign On と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Datto File Protection Single Sign On をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Datto File Protection Single Sign On**」と入力します。
4. 結果パネルから **[Datto File Protection Single Sign On]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Datto File Protection Single Sign On 用の Microsoft Entra SSO を構成してテストする

**B.Simon** という名前のテスト ユーザーを使用して、Datto File Protection Single Sign On での Microsoft Entra SSO を構成し、テストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Datto File Protection Single Sign On での関連ユーザーとの間にリンク関係を確立する必要があります。

Datto File Protection Single Sign On での Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Datto File Protection Single Sign On の SSO を構成する**- アプリケーション側のシングル サインオン設定を構成します。
    1. **Datto File Protection Single Sign On のテスト ユーザーを作成する** - Datto File Protection Single Sign On で B.Simon に対応するユーザーを作成し、Microsoft Entra での当該ユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Datto File Protection Single Sign On]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **[基本的な SAML 構成]** セクションで、アプリケーションを **SP** 開始モードで構成する場合は、次の手順を実行します。

    a. [ **識別子** ] ボックスに、URL を入力します。 `https://saml.fileprotection.datto.com/singlesignon/saml/metadata`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://saml.fileprotection.datto.com/singlesignon/saml/SSO`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.fileprotection.datto.com`

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには、[Datto File Protection Single Sign On クライアント サポート チーム](mailto:ms-sso-support@ot.soonr.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Datto File Protection Single Sign On の SSO を構成する

**Datto File Protection Single Sign On** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Datto File Protection Single Sign On サポート チーム](mailto:ms-sso-support@ot.soonr.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Datto File Protection Single Sign On のテスト ユーザーを作成する

このセクションでは、Datto File Protection Single Sign On で Britta Simon というユーザーを作成します。 [Datto File Protection Single Sign On サポート チーム](mailto:ms-sso-support@ot.soonr.com)と協力して、このユーザーを Datto File Protection Single Sign On プラットフォームに追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Datto File Protection のシングル サインオン URL にリダイレクトされます。
- Datto File Protection Single Sign On のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Datto File Protection シングル サインオンに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Datto File Protection Single Sign On] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Datto File Protection シングル サインオンに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/datto-workplace-tutorial"} -->
## Microsoft Entra ID とシングルサインオンするために、Datto Workplace のシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/datto-workplace-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Datto Workplace Single Sign On 間のシングル サインオンを構成する方法について説明します。

この記事では、Datto Workplace Single Sign On と Microsoft Entra ID を統合する方法について説明します。 Datto Workplace Single Sign On を Microsoft Entra ID と統合すると、次のことが可能になります。

- Datto Workplace Single Sign On にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Datto Workplace Single Sign On に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Datto Workplace Single Sign On でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Datto Workplace Single Sign On では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Datto Workplace Single Sign On の追加

Microsoft Entra ID への Datto Workplace Single Sign On の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Datto Workplace Single Sign On を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Datto Workplace Single Sign On**」と入力します。
4. 結果パネルから **Datto Workplace Single Sign On** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Datto Workplace Single Sign On に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Datto Workplace のシングル サインオンに対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Datto Workplace Single Sign On の関連ユーザー間にリンク関係を確立する必要があります。

Datto Workplace Single Sign On に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Datto Workplace シングル サインオン SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Datto Workplace Single Sign On テストユーザーの作成** - Microsoft Entra における B.Simon のユーザー表現にリンクされた、Datto Workplace Single Sign On でのB.Simonに対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Datto Workplace Single Sign On]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **[基本的な SAML 構成]** セクションで、**SP** 開始モードで構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://saml.workplace.datto.com/singlesignon/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://saml.workplace.datto.com/singlesignon/saml/SSO`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.workplace.datto.com/login`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには [、Datto Workplace シングル サインオン クライアント サポート チーム](mailto:ms-sso-support@ot.soonr.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Datto Workplace Single Sign On SSO の構成

**Datto Workplace シングル サインオン**側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Datto Workplace シングル サインオン サポート チームに送信する](mailto:ms-sso-support@ot.soonr.com)必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Datto Workplace Single Sign On テスト ユーザーの作成

このセクションでは、Datto Workplace Single Sign On で Britta Simon というユーザーを作成します。 [Datto Workplace Single Sign On サポート チーム](mailto:ms-sso-support@ot.soonr.com)と協力して、Datto Workplace Single Sign On プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Datto Workplace のシングル サインオン URL にリダイレクトされます。
- Datto Workplace Single Sign On のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Datto Workplace シングル サインオンに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Datto Workplace Single Sign On] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Datto Workplace Single Sign On に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/db-education-portal-for-schools-tutorial"} -->
## Microsoft Entra ID を使用して DB Education Portal for Schools for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/db-education-portal-for-schools-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と DB Education Portal の間のシングル サインオンを構成する方法について説明します。

この記事では、DB Education Portal for Schools と Microsoft Entra ID を統合する方法について説明します。 英国の学校とマルチ アカデミー トラストで利用できる DB Education Portal に、Microsoft Entra ID 経由でシングル サインオン アクセスを提供します。 DB Education Portal for Schools を Microsoft Entra ID と統合すると、次のことができます。

- DB Education Portal for Schools にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで DB Education Portal for Schools に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で DB Education Portal for Schools 向けの Microsoft Entra のシングル サインオンを構成してテストします。 DB Education Portal for Schools では、 **SP** によって開始されるシングル サインオンがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を DB Education Portal for Schools, と統合するには、次が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な DB Education Portal for Schools のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから DB Education Portal for Schools アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーからの DB Education Portal for Schools の追加

Microsoft Entra アプリケーション ギャラリーから DB Education Portal for Schools を追加して、DB Education Portal for Schools でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**学校向けDB教育ポータル**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、値を入力します。 `DBEducation`

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://intranet.<CustomerName>.domain.extension/governorintranet/wp-login.php?saml_acs` |
    | `https://portal.<CustomerName>.domain.extension/governorintranet/wp-login.php?saml_acs` |
    | `https://intranet.<CustomerName>.domain.extension/studentportal/wp-login.php?saml_acs` |
    | `https://portal.<CustomerName>.domain.extension/studentportal/wp-login.php?saml_acs` |
    | `https://intranet.<CustomerName>.domain.extension/staffportal/wp-login.php?saml_acs` |
    | `https://portal.<CustomerName>.domain.extension/staffportal/wp-login.php?saml_acs` |
    | `https://intranet.<CustomerName>.domain.extension/parentportal/wp-login.php?saml_acs` |
    | `https://portal.<CustomerName>.domain.extension/parentportal/wp-login.php?saml_acs` |
    | `https://intranet.<CustomerName>.domain.extension/familyportal/wp-login.php?saml_acs` |
    | `https://portal.<CustomerName>.domain.extension/familyportal/wp-login.php?saml_acs` |

    c. [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://portal.<CustomerName>.domain.extension` |
    | `https://intranet.<CustomerName>.domain.extension` |

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、 [DB Education Portal for Schools サポート チーム](mailto:contact@dbeducation.org.uk) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. DB Education Portal for Schools アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、DB Education Portal for Schools アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | groups | ユーザー.グループ |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### DB Education Portal for Schools SSO を構成する

**DB Education Portal for Schools** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[DB Education Portal for Schools サポート チーム](mailto:contact@dbeducation.org.uk)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### DB Education Portal for Schools テスト ユーザーの作成

このセクションでは、DB Education Portal for Schools SSO で Britta Simon というユーザーを作成します。 [DB Education Portal for Schools SSO サポート チーム](mailto:contact@dbeducation.org.uk)と協力して、DB Education Portal for Schools SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる DB Education Portal for Schools のサインオン URL にリダイレクトされます。
- DB Education Portal for Schools のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [DB Education Portal for Schools] タイルを選択すると、このオプションは DB Education Portal for Schools のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ddc-web-tutorial"} -->
## Microsoft Entra ID で DDC Web for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ddc-web-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と DDC Web の間のシングル サインオンを構成する方法について説明します。

この記事では、DDC Web を Microsoft Entra ID と統合する方法について説明します。 パーソナライズされたコンテンツ、シンプルなアクティブ化、PAC 資金調達ツールを備えた柔軟な DDC Webプラットフォームを使用して、支援者と PAC 対象集団を簡単に引き込み、動員します。 DDC Web を Microsoft Entra ID と統合すると、次のことが可能になります。

- DDC Web へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して DDC Web に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

DDC Web に対する Microsoft Entra シングル サインオンをテスト環境で構成・テストする。 DDC Web では、 **SP** と **IDP** によって開始されるシングル サインオンがサポートされます。

### [前提条件]

Microsoft Entra ID を DDC Web と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- DDC Web のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから DDC Web アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから DDC Web を追加する

Microsoft Entra アプリケーション ギャラリーから DDC Web を追加して、DDC Web とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**DDC Web**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<yourwebsite>.com`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<yourwebsite>.com/sso/`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<yourwebsite>.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [DDC Web クライアント サポート チーム](mailto:ondemand@ddcpublicaffairs.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **DDC Web のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### DDC Web の SSO を構成する

**DDC Web** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [DDC Web サポート チーム](mailto:ondemand@ddcpublicaffairs.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### DDC Web のテスト ユーザーを作成する

このセクションでは、DDC Web で Britta Simon というユーザーを作成します。 [DDC Web サポート チーム](mailto:ondemand@ddcpublicaffairs.com)と協力して、DDC Web プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる DDC Web サインオン URL にリダイレクトされます。
- DDC Web のサインオン URL に直接アクセスし、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した DDC Web に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで DDC Web タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した DDC Web に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dealpath-tutorial"} -->
## Microsoft Entra ID で Dealpath for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dealpath-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Dealpath の間のシングル サインオンを構成する方法について説明します。

この記事では、Dealpath と Microsoft Entra ID を統合する方法について説明します。 Dealpath を Microsoft Entra ID と統合すると、次のことが可能になります。

- Dealpath にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Dealpath に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Dealpath でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Dealpath では、 **SP** Initiated SSO がサポートされます。

### ギャラリーから Dealpath を追加する

Microsoft Entra ID への Dealpath の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Dealpath を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Dealpath**」と入力します。
4. 結果パネルから **Dealpath** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Dealpath の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Dealpath に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Dealpath の関連ユーザー間にリンク関係を確立する必要があります。

Dealpath に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Dealpath SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Dealpath テスト ユーザーの作成** - Dealpath で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Dealpath**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.dealpath.com/account/login`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.dealpath.com/saml/metadata/<ID>`

    注

    識別子の値は実際の値ではありません。 実際の識別子で値を更新します。 これらの値を取得するには、 [Dealpath クライアント サポート チーム](mailto:kenter@dealpath.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用したシングル Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Dealpath のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Dealpath SSO の構成

1. 別の Web ブラウザー ウィンドウで、Dealpath に管理者としてサインインします。
2. 右上の [ **管理ツール** ] を選択し、[ **統合**] に移動し、[ **SAML 2.0 認証** ] セクションで **[設定の更新]** を選択します。

    [Image: [Admin Tools - Integrations](管理ツール - 統合) ページを示すスクリーンショット。[S A M L 2.0 Authentication](S A M L 2.0 認証) セクションと [Update Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定の更新) が選択されています。]
3. [ **SAML 2.0 認証の設定** ] ページで、次の手順を実行します。

    [Image: Dealpath 構成を示すスクリーンショット。]

    a. **[SAML SSO URL**] ボックスに、**ログイン URL** の値を貼り付けます。

    b。 **Identity Provider Issuer** テキストボックスに、**Microsoft Entra Identifier** の値を貼り付けます。

    c. ダウンロードした **証明書 (Base64)** ファイルの内容をメモ帳にコピーし、[ **パブリック証明書** ] ボックスに貼り付けます。

    d. [ **設定の更新] を選択します**。

#### Dealpath テスト ユーザーの作成

このセクションでは、Dealpath で Britta Simon というユーザーを作成します。 [Dealpath クライアント サポート チーム](mailto:kenter@dealpath.com)と協力して、Dealpath プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Dealpath のサインオン URL にリダイレクトされます。
- Dealpath のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Dealpath] タイルを選択すると、このオプションは Dealpath のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/debroome-brand-portal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に deBroome ブランド ポータルを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/debroome-brand-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と deBroome Brand Portal の間でシングル サインオンを構成する方法について説明します。

この記事では、deBroome Brand Portal と Microsoft Entra ID を統合する方法について説明します。 deBroome Brand Portal を Microsoft Entra ID と統合すると、次のことが可能になります。

- deBroome Brand Portal にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して deBroome Brand Portal に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- deBroome Brand Portal でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- deBroome Brand Portal では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- deBroome Brand Portal では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの deBroome Brand Portal の追加

deBroome Brand Portal と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に deBroome Brand Portal をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**deBroome Brand Portal**」と入力します。
4. 結果のパネルから **[deBroome Brand Portal]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### deBroome Brand Portal 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、deBroome Brand Portal 用の Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、deBroome Brand Portal での関連ユーザーとの間にリンク関係を確立する必要があります。

deBroome Brand Portal で Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **deBroome Brand Portal の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **deBroome Brand Portal のテスト ユーザーを作成する** - deBroome Brand Portal で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**deBroome Brand Portal**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

[Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]

1. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerBrandPortalUrl>/rv2/saml2/metadata`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerBrandPortalUrl>/rv2/saml2/acs`
2. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerBrandPortalUrl>/sso`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[deBroome Brand Portal クライアント サポート チーム](mailto:support@debroome.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
3. deBroome Brand Portal アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
4. その他に、deBroome Brand Portal アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
5. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### deBroome Brand Portal の SSO の構成

**deBroome Brand Portal** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [deBroome Brand Portal サポート チーム](mailto:support@debroome.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### deBroome Brand Portal のテスト ユーザーの作成

このセクションでは、B. Simon というユーザーを deBroome Brand Portal に作成します。 deBroome Brand Portal では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 deBroome Brand Portal にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる deBroome ブランド ポータルのサインオン URL にリダイレクトされます。
- deBroome Brand Portal サインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した deBroome Brand Portal に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [deBroome Brand Portal] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した deBroome Brand Portal に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/deem-mobile-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Deem Mobile を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/deem-mobile-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Deem Mobile 間のシングル サインオンを構成する方法について説明します。

この記事では、Deem Mobile と Microsoft Entra ID を統合する方法について説明します。 Deem Mobile は、ビジネス出張を迅速かつ簡単に手配したい方に向けて設計されています。 航空券、ホテル、レンタカー、さらには Uber for Business まで予約できる充実した機能を備えています。 Deem Mobile を Microsoft Entra ID と統合すると、次のことが可能になります。

- Deem Mobile にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Deem Mobile に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Deem Mobile に対する Microsoft Entra シングル サインオンをテスト環境で構成・テストする。 Deem Mobile では、**SP**開始シングルサインオンと**IDP**開始シングルサインオンの両方がサポートされています。

### [前提条件]

Microsoft Entra ID を Deem Mobile と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Deem Mobile でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Deem Mobile アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Deem Mobile を追加する

Microsoft Entra アプリケーション ギャラリーから Deem Mobile を追加し、Deem Mobile に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Deem Mobile]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. **識別子** textDeem Mobile に、次のいずれかのパターンを使用して値を入力します。

        | **識別子** |
        | --- |
        | `<Deem_CustomerDomainName>-mobile` |
        | `<Deem_CustomerDomainName>:mobile` |
    2. [ **応答 URL** ] ボックスに、URL を入力します。 `https://go.deem.com/idp/ACS.saml2`

    注

    識別子の値は実際の値ではありません。 この値を実際の識別子で更新します。 この値を取得するには、 [Deem Mobile サポート チーム](mailto:customer.success@deem.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Deem Mobile アプリケーションでは、特定の形式の SAML アサーションを使用するため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、Deem Mobile では、これがユーザーの電子メール アドレスにマップされることを想定しています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: トークン属性の構成の画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### Deem Mobile SSO を構成する

**Deem Mobile** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Deem Mobile サポート チーム](mailto:customer.success@deem.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Deem Mobile のテスト ユーザーを作成する

このセクションでは、Deem Mobile で Britta Simon というユーザーを作成します。 [Deem Mobile サポート チーム](mailto:customer.success@deem.com)と協力して、Deem Mobile プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Deem Mobile に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Deem Mobile] タイルを選択すると、SSO を設定した Deem Mobile に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/degreed-tutorial"} -->
## Microsoft Entra ID で Degreed for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/degreed-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Degreed 間のシングル サインオンを構成する方法について説明します。

この記事では、Degreed と Microsoft Entra ID を統合する方法について説明します。 Degreed を Microsoft Entra ID と統合すると、次のことが可能になります。

- Degreed にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Degreed に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Degreed は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Degreed でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Degreed では、 **SP** Initiated SSO がサポートされます。
- Degreed では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Degreed を追加する

Microsoft Entra ID への Degreed の統合を構成するに、ギャラリーから管理対象 SaaS アプリの一覧に Degreed を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスしてください。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Degreed**」と入力します。
4. 結果パネルから **Degreed** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Degreed に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Degreed での関連ユーザーとの間にリンク関係を確立する必要があります。

Degreed に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Degreed SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Degreedのテストユーザーを作成 -** DegreedでB.Simonに対応するユーザーを作成し、そのユーザーをMicrosoft EntraでのB.Simonにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Degreed]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://degreed.com/?orgsso=<company code>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://degreed.com/<instancename>`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://degreed.com/SAML/<instancename>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、および応答 URL で値を更新します。 これらの値を取得するには [、Degreed クライアント サポート チーム](mailto:admin@degreed.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Degreed のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Degreed の SSO の構成

**Degreed** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Degreed サポート チーム](mailto:sso@degreed.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Degreed のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Degreed に作成します。 Degreed では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Degreed にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Degreed サポート チーム](mailto:sso@degreed.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Degreed サインオン URL にリダイレクトされます。
- Degreed のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Degreed] タイルを選択すると、このオプションは Degreed のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/delivery-scheduling-tool-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に配信スケジュール ツールを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/delivery-scheduling-tool-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と配信スケジュール ツールの間でシングル サインオンを構成する方法について説明します。

この記事では、配信スケジュール ツールと Microsoft Entra ID を統合する方法について説明します。 配信スケジュール ツールと Microsoft Entra ID を統合すると、次のことができます。

- 配信スケジュール ツールにアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して配信スケジュール ツールに自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 配信スケジュール ツールでのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- 配信スケジュール ツールでは、 **SP Initiated SSO と IDP** Initiated SSO の両方がサポートされます。

### ギャラリーからの配信スケジュール ツールの追加

Microsoft Entra ID への配信スケジュール ツールの統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に配信スケジュール ツールを追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**配信スケジュール ツール**」と入力します。
4. 結果パネルから **配信スケジュール ツールを** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### 配信スケジュール ツールの Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、配信スケジュール ツールに対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと配信スケジュール ツールの関連ユーザーとの間にリンク関係を確立する必要があります。

配信スケジュール ツールで Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **配信スケジュール ツールの SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **配信スケジュールツールでテストユーザーを作成し**、Microsoft Entra のユーザー表現にリンクされた B.Simon に対応するユーザーを持つようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Delivery Scheduling Tool**&gt;**シングルサインオンに移動します。**
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **識別子** |
    | --- |
    | `https://saml.quickbase.com` |
    | `https://microsoftlearning.quickbase.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://saml.quickbase.com/saml/SSOAssert.aspx` |
    | `https://microsoftlearning.quickbase.com/saml/SSOAssert.aspx` |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://saml.quickbase.com`
7. 配信スケジュール ツール アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]

    注

    上のスクリーンショットに示す **[追加の要求**] セクションに示されているすべての既定の属性の **emailaddress** の名前を **EmailAddress** に変更し、名前空間を削除して、アプリケーション側の要件に従って両方の側で SSO 接続を正しく動作させ、ポータルで**名前**と**一意のユーザー識別子 (名前 ID) 属性を** **user.onpremisessamaccountname に**マップしてください。
8. 上記に加えて、配信スケジュール ツール アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. [ **配信スケジュール ツールの設定** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### 配信スケジュール ツールの SSO の構成

**配信スケジュール ツール**側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を[配信スケジュール ツール サポート チーム](mailto:LP.Tier2@accenture.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### 配送スケジューリングツールのテストユーザーを作成する

このセクションでは、配信スケジュール ツールで B.Simon というユーザーを作成します。 [配信スケジュール ツール サポート チーム](mailto:LP.Tier2@accenture.com)と協力して、配信スケジュール ツール プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる配信スケジュール ツールのサインオン URL にリダイレクトされます。
- 配信スケジュール ツールのサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した配信スケジュール ツールに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [配信スケジュール ツール] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した配信スケジュール ツールに自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/delivery-solutions-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に配信ソリューションを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/delivery-solutions-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Delivery Solutions の間にシングル サインオンを構成する方法について説明します。

この記事では、配信ソリューションと Microsoft Entra ID を統合する方法について説明します。 配信ソリューションは、即日配送、カーブサイド、店舗内受け取り、発送、購入後のサポートを通じてオムニチャネル戦略を可能にするOXMプラットフォームです。 Microsoft Entra ID と Delivery Solutions を統合すると、次のことができます。

- Delivery Solutions にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Delivery Solutions に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Delivery Solutions 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Delivery Solutions は、**SP** initiated と **IDP** initiated の両方のシングル サインオンと **Just In Time** ユーザー プロビジョニングをサポートします。

### [前提条件]

Microsoft Entra ID と Delivery Solutions を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Delivery Solutions でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Delivery Solutions アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Delivery Solutions を追加する

Microsoft Entra アプリケーション ギャラリーから Delivery Solutions を追加して、Delivery Solutions でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Delivery Solutions**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`<ENVIRONMENT>.portal.deliverysolutions.co` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT>.api.deliverysolutions.co/authentications/saml/response/<Base64_Tenant_ID>`
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT>.portal.deliverysolutions.co/#/login/saml/<Tenant_ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Delivery Solutions のサポート チーム](mailto:support@deliverysolutions.co)までお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Delivery Solutions アプリケーションでは特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. さらに、Delivery Solutions アプリケーションでは、いくつかの追加の属性が SAML 応答で返されれます。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ブランドID | ユーザー.職名 |
    | 店舗ID | ユーザーの部署 |
    | ロール | user.assignedroles |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
9. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (Base64)]** を見つけます。**[ダウンロード]** を選択して証明書をダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Delivery Solutions の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Delivery Solutions の SSO を構成する

1. Delivery Solutions 企業サイトに管理者としてログインします。
2. **[ビジネス]**&gt;**[設定]**&gt;**[認証]** の順にアクセスし、**[構成]** ボタンを有効化します。

    [Image: [設定] と [ビジネス] のページを示すスクリーンショット。]
3. **[SSO 構成]** ページで次の手順を実行します。

    [Image: 構成設定を示すスクリーンショット。]

    1. ドロップダウンから SSO の **[SAML]** の種類を選択します。
    2. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[Idp Certificate] (IdP 証明書)** テキスト ボックスに貼り付けます。
    3. **[エンティティ ID/発行者 URL]** テキスト ボックスに、先ほどコピーした **[Microsoft Entra 識別子]** の値を貼り付けます。
    4. **[ログイン URL/SSO エンドポイント]** テキスト ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。
    5. **[ログアウト URL/SSO エンドポイント]** テキスト ボックスに、前にコピーした**ログアウト URL** の値を貼り付けます。
    6. ドロップダウンから **[ユーザー ロール]** を選択し、構成を保存します。

#### Delivery Solutions のテスト ユーザーを作成する

このセクションでは、Delivery Solutions に B.Simon というユーザーを作成します。 Delivery Solutions は Just In Time ユーザー プロビジョニングをサポートします。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Delivery Solutions にユーザーがまだ存在していない場合、通常認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる配信ソリューションのサインオン URL にリダイレクトされます。
- Delivery Solutions のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Delivery Solutions に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [配信ソリューション] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した配信ソリューションに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/deputy-tutorial"} -->
## Microsoft Entra ID で Deputy for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/deputy-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Deputy の間のシングル サインオンを構成する方法について説明します。

この記事では、Deputy と Microsoft Entra ID を統合する方法について説明します。 Deputy を Microsoft Entra ID と統合すると、次のことが可能になります。

- Deputy にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Deputy に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Deputy のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Deputy は、**SPおよびIDPによって開始されるSSO**をサポートします。
- Deputy では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Deputy を追加する

Microsoft Entra ID への Deputy の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Deputy を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Deputy**」と入力します。
4. 結果パネルから **Deputy** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Deputy 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Deputy に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Deputy の関連ユーザー間にリンク関係を確立する必要があります。

Deputy に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Deputy SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Deputy のテスト ユーザーの作成** - Deputy で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザーをリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Deputy]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://<subdomain>.<region>.au.deputy.com
    https://<subdomain>.<region>.ent-au.deputy.com
    https://<subdomain>.<region>.na.deputy.com
    https://<subdomain>.<region>.ent-na.deputy.com
    https://<subdomain>.<region>.eu.deputy.com
    https://<subdomain>.<region>.ent-eu.deputy.com
    https://<subdomain>.<region>.as.deputy.com
    https://<subdomain>.<region>.ent-as.deputy.com
    https://<subdomain>.<region>.la.deputy.com
    https://<subdomain>.<region>.ent-la.deputy.com
    https://<subdomain>.<region>.af.deputy.com
    https://<subdomain>.<region>.ent-af.deputy.com
    https://<subdomain>.<region>.an.deputy.com
    https://<subdomain>.<region>.ent-an.deputy.com
    https://<subdomain>.<region>.deputy.com
    ```

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://<subdomain>.<region>.au.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.ent-au.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.na.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.ent-na.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.eu.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.ent-eu.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.as.deputy.com/exec/devapp/samlacs.
    https://<subdomain>.<region>.ent-as.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.la.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.ent-la.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.af.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.ent-af.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.an.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.ent-an.deputy.com/exec/devapp/samlacs
    https://<subdomain>.<region>.deputy.com/exec/devapp/samlacs
    ```
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<your-subdomain>.<region>.deputy.com`

    注

    Deputy リージョン サフィックスはオプションです。または次のいずれかを使用する必要があります。au | na | eu |as |la |af |an |ent-au |ent-na |ent-eu |ent-as | ent-la | ent-af | ent-an

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Deputy クライアント サポート チーム](https://www.deputy.com/call-centers-customer-support-scheduling-software) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Deputy アプリケーションは特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Deputy アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名（ファーストネーム） | User.givenname |
    | 姓 | ユーザーの名字 |
9. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Deputy のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Deputy SSO の構成

1. Deputy アカウントに管理者としてログインします。
2. 右上隅でアカウントを選択し、[ **ビジネス設定**] を選択します。

    [Image: ビジネス設定のスクリーンショット]
3. 次に、[ **全般** ] タブの [ **単一 Sign-On 設定**] を選択します。

    [Image: [Single Sign-On settings](単一 Sign-On 設定) のスクリーンショット]
4. この **単一 Sign-On 設定** ページで、次の手順を実行します。

    [Image: シングル サインオンの構成]

    a. [ **シングル サインオンを有効にする] を選択します**。

    b。 **ID プロバイダーのログイン URL** ボックスに、前にコピーした**ログイン URL を**貼り付けます。

    c. ID **プロバイダーの発行者** テキスト ボックスに、前にコピーした **識別子 (エンティティ ID) を** 貼り付けます。

    d. ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容を **X.509 証明書** ボックスに貼り付けます。

    e. SSO でログインする場合は、 **必要なシングル サインオン** ログインを有効にします。

    f. **Just-In-Time プロビジョニングを**有効にし、[**名**] フィールドと [**姓**] フィールドで、[**ユーザー属性] および [要求**] セクションで設定した属性の名前 (`First name`や`Last name`など) を指定します。

    g. [ **変更の適用]** を選択します。

#### Deputy のテスト ユーザーの作成

このセクションでは、Deputy で Britta Simon というユーザーを作成します。 Deputy では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Deputy にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

##### ユーザーを手動で追加するには、次の手順を行います。

1. Deputy 企業サイトに管理者としてログインします。
2. 上部のナビゲーション ウィンドウで、[ **ユーザー**] を選択します。
3. [ **ユーザーの追加** ] ボタンを選択し、[ **単一ユーザーの追加]** を選択します。

    [Image: 人を追加]
4. ユーザーを追加するには、[ **全般** ] タブで次の手順を実行します。

    [Image: 新しいユーザー]

    a. [ **名** ] ボックスと [ **姓** ] ボックスに、 **Britta** や Simon などのフィールドを入力 **します**。

    b。 **[勤務先] 欄**に会社名を入力します。

    c. [ **保存] ボタンを** 選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TeamzSkill のサインオン URL にリダイレクトされます。
- TeamzSkill のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TeamzSkill に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [TeamzSkill] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TeamzSkill に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/descartes-tutorial"} -->
## Microsoft Entra ID で Descartes for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/descartes-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Descartes の間のシングル サインオンを構成する方法について説明します。

この記事では、Descartes と Microsoft Entra ID を統合する方法について説明します。 Descartes アプリケーションでは、世界中の機密性の高い企業にロジスティック情報サービスが提供されます。 統合スイートとして、さまざまなロジスティック ビジネス ロールのモジュールが提供されます。 Descartes を Microsoft Entra ID と統合すると、次のことが可能になります。

- Descartes にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Descartes に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

テスト環境で Descartes 用の Microsoft Entra シングル サインオンを構成してテストします。 Descartes では、 **SP** と **IDP** によって開始されるシングル サインオンの両方がサポートされ、 **Just In Time** ユーザー プロビジョニングもサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### 前提条件

Descartes を Microsoft Entra ID と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Descartes のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Descartes アプリケーションを追加する必要があります。 アプリケーションに割り当て、シングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Descartes を追加する

Microsoft Entra アプリケーション ギャラリーから Descartes を追加して、Descartes でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーを作成して割り当てる

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができます。 ウィザードは、シングル サインオン構成ウィンドウへのリンクも提供します。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO を構成する

Microsoft Entra のシングル サインオンを有効にするには、以下の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Descartes**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **IdP** Initiated SSO を構成する場合は、次の手順を実行します。

    [ **リレー状態** ] ボックスに、URL を入力します。 `https://auth.gln.com/Welcome`
7. Descartes アプリケーションでは特定の形式の SAML アサーションが想定されるため、カスタム属性のマッピングを SAML トークンの属性構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. その他に、Descartes アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 電話 | ユーザー.電話番号 |
    | ファクシミリ電話番号 | user.facsimiletelephonenumber |
    | ou | user.department |
    | assignedRoles | user.assignedroles |
    | グループ | ユーザー.グループ |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. ロールベースの構成に Descartes アプリケーションで使用する Microsoft Entra グループのリストを作成します。 Descartes アプリケーションのユーザー ロール モジュールのリストは、https://www.gln.com/docs/Descartes_Application_User_Roles.pdf にあります。 Azure グループ GUID が見つかります。 Microsoft Entra 管理センターで **[グループ**から**グループをダウンロード**] を選択します。

この CSV ファイルは Excel で読み込むことができます。 最初の列の ID を一覧表示し、それを Descartes アプリケーションのユーザー ロールに関連付けることで、Descartes アプリケーション ロールにマップするグループを選択してください。

### Descartes SSO を構成する

**Descartes** 側でシングル サインオンを構成するには、[Descartes サポート チーム](mailto:servicedesk@descartes.com)に次の値を電子メールで送信する必要があります。 件名として、Microsoft Entra SSO セットアップ要求を使用してください。

1. 優先される ID ドメイン サフィックス (多くの場合、電子メール ドメイン サフィックスと同じです)。
2. アプリのフェデレーション メタデータ URL。
3. Descartes アプリケーションを使用する権利があるユーザー向けの Microsoft Entra グループ GUID を含むリスト。

Descartes では電子メールの情報を使用して、SAML SSO 接続をアプリケーション側で正しく設定します。

このような要求の例を以下に示します。

[Image: 要求の例を示すスクリーンショット。]

#### Descartes テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Descartes に作成します。 Descartes では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションでは、ユーザー側で必要な操作はありません。 Descartes にユーザーがまだ存在していない場合、一般的には認証後に新しいものが作成されます。

Descartes アプリケーションでは、Microsoft Entra 統合ユーザーのドメイン修飾ユーザー名が使用されます。 ドメイン修飾ユーザー名は SAML 要求の件名で構成され、常にドメイン サフィックスで終わります。 Descartes では、ドメイン内のすべてのユーザーに共通する会社の電子メール ドメイン サフィックスを ID ドメイン サフィックスとして選択することが推奨されます (例: B.Simon@contoso.com)。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Descartes のサインオン URL にリダイレクトされます。 または、Descartes アプリケーションの特定のモジュールに "ディープ リンク" URL を使用できます。ページにリダイレクトされ、ドメインで修飾されたユーザー名を指定して、Microsoft Entra ログイン ダイアログに移動します。
- 提供された Descartes アプリケーションの直接アクセス URL に移動し、アプリケーション ログイン ウィンドウでドメイン修飾ユーザー名 (B.Simon@contoso.com) を指定してログイン フローを開始します。 これにより、ユーザーが自動的に Microsoft Entra ID にリダイレクトされます。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Descartes アプリケーション メニューに自動的にサインインします。
- また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Descartes] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Descartes に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/desknets-neo-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に desknets NEO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/desknets-neo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と desknet's NEO 間のシングル サインオンを構成する方法について説明します。

この記事では、desknet の NEO と Microsoft Entra ID を統合する方法について説明します。 desknet's NEO を Microsoft Entra ID と統合すると、次のことが可能になります。

- desknet's NEO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで desknet's NEO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- desknet's NEO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- desknet's NEO では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの desknet's NEO の追加

Microsoft Entra ID への desknet's NEO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に desknet's NEO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**desknet's NEO**」と入力します。
4. 結果パネルから **[desknet's NEO]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### desknet's NEO に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、desknet's NEO に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと desknet's NEO の関連ユーザー間にリンク関係を確立する必要があります。

desknet's NEO に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **desknet's NEO の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **desknet's NEO のテスト ユーザーの作成** - desknet's NEO で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**desknet's NEO**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.dn-cloud.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.dn-cloud.com/cgi-bin/dneo/zsaml.cgi`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.dn-cloud.com/cgi-bin/dneo/dneo.cgi`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[desknet's NEO クライアント サポート チーム](mailto:cloudsupport@desknets.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[desknet's NEO のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### desknet's NEO の SSO の構成

1. desknet's NEO の企業サイトに管理者としてサインインします。
2. メニューで、[ **SAML 認証リンクの設定]** アイコンを選択します。

    [Image: [SAML authentication link settings](SAML 認証リンクの設定) のスクリーンショット]
3. **[共通設定**] で、[SAML 認証コラボレーション] から **[使用**] を選択します。

    [Image: SAML 認証の使用のスクリーンショット。]
4. **[SAML authentication link settings](SAML 認証リンクの設定)** セクションで次の手順を実行します。

    [Image: [SAML authentication link settings](SAML 認証リンクの設定) セクションのスクリーンショット。]

    a. **[アクセス URL]** ボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    b。 **[SP エンティティ ID]** ボックスに、先ほどコピーした**識別子**の値を貼り付けます。

    c. [ **ファイルの選択] を選択** して、ダウンロードした **証明書 (Base64)** ファイルを **x.509 証明書** テキストボックスにアップロードします。

    d. **[変更**] を選択します。

#### desknet's NEO のテスト ユーザーの作成

1. desknet's NEO の企業サイトに管理者としてサインインします。
2. **メニュー**の [**管理者設定**] アイコンを選択します。

    [Image: [Administrator settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者の設定) のスクリーンショット。]
3. **[設定]** アイコンを選択し、[**カスタム設定**] で [**ユーザー管理**] を選択します。

    [Image: [User management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー管理) の設定のスクリーンショット。]
4. [ **ユーザー情報の作成] を選択します**。

    [Image: ユーザー情報ボタンのスクリーンショット。]
5. 次のページの必須フィールドに入力し、[ **作成**] を選択します。

    [Image: ユーザー作成セクションのスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる desknet の NEO サインオン URL にリダイレクトされます。
- desknet's NEO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで desknet の NEO タイルを選択すると、このオプションは desknet の NEO サインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/deskradar-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Deskradar を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/deskradar-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Deskradar の間でシングル サインオンを構成する方法について説明します。

この記事では、Deskradar と Microsoft Entra ID を統合する方法について説明します。 Deskradar と Microsoft Entra ID を統合すると、次のことができます。

- Deskradar にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Deskradar に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Deskradar でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Deskradar では、**SP と** IDP によって開始される SSO がサポートされます。

### ギャラリーから Deskradar を追加する

Microsoft Entra ID への Deskradar の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Deskradar を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Deskradar**」と入力します。
4. 結果パネルで **Deskradar** を選択して、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Deskradar の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Deskradar に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Deskradar の関連ユーザーとの間にリンク関係を確立する必要があります。

Deskradar で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Deskradar SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Deskradar テストユーザーの作成** - Deskradar で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表示にリンクします。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Deskradar]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    a. [**識別子**] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<YOURDOMAIN>.deskradar.cloud`

    b。 [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<YOURDOMAIN>.deskradar.cloud/auth/sso/saml/consume`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<YOURDOMAIN>.deskradar.cloud/auth/sso/saml/login`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 **YOURDOMAIN** をあなたの Deskradar インスタンス ドメインで置き換えてください。 これらの値 [取得するには、Deskradar クライアント サポート チーム](mailto:support@deskradar.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. Deskradar アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: image]
8. 上記に加えて、Deskradar アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | LastName | ユーザー.姓 |
    | Email | user.userprincipalname |
    |  |  |
9. [SAML でシングル サインオンを設定する ] ページの [SAML 署名証明書の] セクションで、[証明書 (Base64) を探し、ダウンロード を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **Deskradar** のセットアップセクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Deskradar SSO の構成

1. 別の Web ブラウザー ウィンドウで、Deskradar 企業サイトに管理者としてサインインします
2. サイドバーのアイコンを選択して[ **チーム** ]パネルを開きます。
3. **[認証]** アブに切り替えます。
4. [**SAML 2.0**] タブで、以前コピーした **ログイン URL** と **Microsoft Entra Identifier** の値を次のフィールドに入力します。

    - **SAML SSO URL**: 前にコピーした **ログイン URL** 値。
    - **[Identity Provider Issuer] (ID プロバイダーの発行者)**: 前にコピーした **Microsoft Entra 識別子** の値。

    a. **SAML** 認証方法を有効にします。

    b。 **SAML SSO URL** ボックスに、前にコピーした **ログイン URL** 値を入力します。

    c. **[Identity Provider Issuer] (ID プロバイダーの発行者)** テキストボックスに、前にコピーした **Microsoft Entra 識別子**の値を入力します。
5. ダウンロードした**証明書 (Base64)** ファイルをテキスト エディターで開き、その内容をコピーして、Deskradar の **[公開証明書]** フィールドに貼り付けます。

#### Deskradar テスト ユーザーの作成

このセクションでは、Deskradar で B.Simon というユーザーを作成します。 Deskradar クライアント サポート チーム  と連携して、Deskradar プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Deskradar のサインオン URL にリダイレクトされます。
- Deskradar のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Deskradar に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Deskradar] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Deskradar に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dialpad-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Dialpad を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dialpad-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-05
- Summary: ユーザー アカウントを Dialpad に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Dialpad で実行する手順と、ユーザーやグループを Dialpad に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するMicrosoft Entra IDを示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

>
> このコネクタは、現在プレビューの段階です。 プレビューの詳細については、「[Universal License Terms For Online Services](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)」を参照してください。

### サポートされている機能

- Dialpad でユーザーを作成します。
- アクセスが不要になった場合は、Dialpad でユーザーを削除します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)..

- [Dialpad テナント](https://www.dialpad.com/pricing/)。
- 管理者アクセス許可がある Dialpad のユーザー アカウント。

### ユーザーを Dialpad に割り当てる

Microsoft Entra IDでは、割り当てと呼ばれる概念を使用して、選択したアプリにaccessを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザープロビジョニングを設定および有効化する前に、Microsoft Entra ID のどのユーザーやグループが Dialpad にアクセスする必要があるかを決定する必要があります。 特定した後、次の手順に従い、これらのユーザー、グループ、またはその両方を Dialpad に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Dialpad に割り当てる際の重要なヒント

- 自動ユーザー プロビジョニング構成をテストするには、1 人の Microsoft Entra ユーザーを Dialpad に割り当てることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Dialpad にユーザーを割り当てるときは、割り当てダイアログで、有効なアプリケーション固有ロール (使用可能な場合) を選択する必要があります。 既定のAccess ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために Dialpad を設定する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Dialpad を構成する前に、Dialpad からプロビジョニング情報を取得する必要があります。

1. [Dialpad 管理コンソール](https://dialpadbeta.com/login)にサインインし、 **[Admin settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者設定)** を選択します。 ドロップダウンから **[My Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/自分の会社)** が選択されていることを確認します。 **[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) &gt; [API Keys](API キー)** に移動します。

    [Image: Dialpad 管理コンソールのスクリーンショット。設定アイコン、[My Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/自分の会社)、[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)、[A P I keys](A P I キー) が強調表示され、[My Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/自分の会社) が選択されています。]
2. [キーの追加] を選択し、シークレット トークンのプロパティを構成して、新しい **キー** を生成します。

    [Image: Dialpad 管理コンソールの [A P I keys](A P I キー) ページのスクリーンショット。[Add a key](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/キーの追加) が強調表示されています。]

    [Image: Dialpad 管理コンソールの [Edit A P I key](A P I キーの編集) ページのスクリーンショット。[保存] ボタンが強調表示されています。]
3. 最近作成した API キーの **[値を表示する** ] ボタンを選択し、表示された値をコピーします。 この値は、Dialpad アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: Dialpad でトークンを作成する]

### ギャラリーから Dialpad を追加する

Microsoft Entra IDを使用して自動ユーザー プロビジョニング用に Dialpad を構成するには、Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に Dialpad を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Dialpad を追加するには、以下の手順を行います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、「**Dialpad**」と入力し、結果パネルで **[Dialpad]** を選択します。 [Image: 結果一覧の Dialpad]
4. 個別のブラウザーで、以下で強調表示されている **URL** に移動します。

    [Image: Dialpad アプリについての情報を表示するページのスクリーンショット。[U R L] にアドレスがリストされて強調表示されています。]
5. 右上隅で、**[Log In](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ログイン)\ &gt; [Use Dialpad online](Dialpad オンラインを使用する)** を選択します。

    [Image: Dialpad Web サイトのスクリーンショット。[Log in](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ログイン) が強調表示され、[Log in](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ログイン) タブが開いています。[Use Dialpad online](Dialpad オンラインを使用する) も強調表示されています。]
6. Dialpad は OpenIDConnect アプリであるため、Microsoft の職場アカウントを使用して Dialpad にログインすることを選択します。

    [Image: ダイヤルパッド Web サイトの [通話の開始] ページのスクリーンショット。[Office 365でログイン] ボタンが強調表示されています。]
7. 認証に成功した後、同意ページの同意プロンプトを受け入れます。 その後、アプリケーションがテナントに自動的に追加され、Dialpad アカウントにリダイレクトされます。

### Dialpad への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて、Dialpad でユーザーやグループを作成、更新、または無効にするために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで Dialpad の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Dialpad]** を選択します。

    [Image: アプリケーションの一覧の Dialpad のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションの `https://dialpad.com/scim` に  を入力します。 前の手順で Dialpad から取得して保存した値を **[シークレット トークン]** に入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Dialpad に接続できることを確認します。 接続できない場合は、使用中の Dialpad アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. Microsoft Entra IDから Dialpad に同期されるユーザー属性を、**Attribute Mapping** セクションで確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Dialpad のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Dialpad ユーザーの属性]
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

- 現在、ダイヤルパッドではグループ名の変更はサポートされていません。 つまり、Microsoft Entra ID内のグループの **displayName** に対する変更は、Dialpad で更新および反映されません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/diffchecker-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Diffchecker を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/diffchecker-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-05
- Summary: ユーザー アカウントをMicrosoft Entra IDから Diffchecker に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Diffchecker と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成されたMicrosoft Entra IDは、Microsoft Entraプロビジョニングサービスを使用して、[Diffchecker](https://www.diffchecker.com)に対してユーザーのプロビジョニングと解除を自動的に行います。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Diffchecker でユーザーを作成します。
- accessが不要になった場合は、Diffchecker のユーザーを削除します。
- Microsoft Entra IDと Diffchecker の間でユーザー属性の同期を維持します。
- Diffchecker への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/diffchecker-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Diffchecker のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- Microsoft Entra IDとDiffcheckerの間で[マッピングするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Diffchecker を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように Diffchecker を構成するには、Diffchecker サポートに問い合わせてください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Diffchecker を追加する

Microsoft Entra アプリケーション ギャラリーから Diffchecker を追加して、Diffchecker へのプロビジョニングの管理を開始します。 SSO 用に Diffchecker を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Diffchecker への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて、Diffchecker でユーザーを作成、更新、無効化できるように Microsoft Entra プロビジョニング サービスを設定するための手順を案内します。

#### Microsoft Entra IDで Diffchecker の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Diffchecker**] を選択します。

    [Image: アプリケーションの一覧の Diffchecker リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Diffchecker テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Diffchecker に接続できることを確認します。 接続に失敗した場合は、Diffchecker アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Diffchecker に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Diffchecker のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Diffchecker API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Diffchecker で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/diffchecker-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Diffchecker を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/diffchecker-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Diffchecker 間のシングル サインオンを構成する方法について説明します。

この記事では、Diffchecker と Microsoft Entra ID を統合する方法について説明します。 Diffchecker を Microsoft Entra ID と統合すると、次のことができます。

- Diffchecker にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Diffchecker に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Diffchecker でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Diffcheckerでは、**SPが開始するSSO**と**IDPが開始するSSO**に対応しています。
- Diffchecker では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/diffchecker-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Diffchecker の追加

Microsoft Entra ID への Diffchecker の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Diffchecker を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Diffchecker**」と入力します。
4. 結果パネルから **Diffchecker** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Diffchecker に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、Diffchecker に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Diffchecker の関連ユーザーとの間にリンク関係を確立する必要があります。

Diffchecker に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Diffchecker の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Diffchecker テスト ユーザーの作成** - Diffchecker で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Diffchecker**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `http://www.diffchecker.com/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.<ENVIRONMENT>.diffchecker.com/auth/saml/acs/orgs/<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.<ENVIRONMENT>.diffchecker.com/auth/saml/<ID>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Diffchecker サポート チーム](mailto:azure@diffchecker.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Diffchecker のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Diffchecker SSO の構成

**Diffchecker** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と Microsoft Entra 管理センターからコピーした適切な URL を [Diffchecker サポート チーム](mailto:azure@diffchecker.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Diffchecker テスト ユーザーの作成

このセクションでは、Diffchecker で B.Simon というユーザーを作成します。 [Diffchecker サポート チーム](mailto:azure@diffchecker.com)と協力して、Diffchecker プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Diffchecker のサインオン URL にリダイレクトします。
- Diffchecker のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Diffchecker に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Diffchecker] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Diffchecker に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/digicert-tutorial"} -->
## Microsoft Entra ID で DigiCert for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/digicert-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と DigiCert の間のシングル サインオンを構成する方法について説明します。

この記事では、DigiCert と Microsoft Entra ID を統合する方法について説明します。 DigiCert を Microsoft Entra ID と統合すると、次のことが可能になります。

- DigiCert にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで DigiCert に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な DigiCert サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- DigiCert では、**IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの DigiCert の追加

Microsoft Entra ID への DigiCert の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に DigiCert を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **アイデンティティ**&gt;**アプリケーション**&gt;**エンタープライズ アプリケーション**&gt;**新しいアプリケーション**の順に移動します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**DigiCert**」と入力します。
4. 結果パネルから **[DigiCert]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### DigiCert の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、DigiCert に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと DigiCert の関連ユーザー間にリンク関係を確立する必要があります。

DigiCert に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **DigiCert の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **DigiCertテストユーザーを作成 - DigiCert**においてB.Simonに対応するユーザーを設定し、それをMicrosoft Entra内のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**DigiCert**&gt;**シングルサインオン**にブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://www.digicert.com/account/sso/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://www.digicert.com/account/sso/`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.digicert.com/account/sso/<FEDERATION_NAME>/login`

    注

    サインオン URL の値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 値を取得するには、[DigiCert サポート チーム](mailto:support@digicert.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. DigiCert アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 このアプリケーションに対して次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの **ユーザー属性** セクションから管理できます。 [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** ボタンを選択して [ **ユーザー属性] ダイアログを** 開きます。

    [Image: [編集] ボタンが選択されている [ユーザー属性] セクションを示すスクリーンショット。]
7. [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、[**編集] アイコン**を使用して要求を編集するか、[**新しい要求の追加]** を使用して要求を追加し、上の図に示すように SAML トークン属性を構成し、次の手順を実行します。

    | 名前 | ソース属性 |
    | --- | --- |
    | ネームアイデンティファイア | user.userprincipalname |
    | company | &lt; 会社コード &gt; |
    | digicertrole | 証明書センターにアクセス可能 |

    注

    **会社**の属性の値は実際のものではありません。 この値を実際の企業コードで更新します。 **company** 属性の値を取得するには、[DigiCert サポート チーム](mailto:support@digicert.com)に問い合わせてください。

    a。 [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [新しい要求の追加] および [保存] ボタンが強調表示されている [ユーザーの要求] セクションを示すスクリーンショット。]

    [Image: 画像]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **[OK]** を選択します。

    g. **保存** を選択します。
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[DigiCert のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### DigiCert の SSO の構成

**DigiCert** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [DigiCert サポート チーム](mailto:support@digicert.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### DigiCert のテスト ユーザーの作成

このセクションでは、DigiCert で Britta Simon というユーザーを作成します。 [DigiCert サポート チーム](mailto:support@digicert.com)と連携して、DigiCert プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した DigiCert に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [DigiCert] タイルを選択すると、SSO を設定した DigiCert に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/digital-pigeon-tutorial"} -->
## Microsoft Entra ID でシングルサインオンのために Digital Pigeon を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/digital-pigeon-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Digital Pigeon 間のシングル サインオンを構成する方法について説明します。

この記事では、Digital Pigeon と Microsoft Entra ID を統合する方法について説明します。 Digital Pigeon は、クリエイティブな人々が作品を美しく、迅速に提供できるよう支援します。 Digital Pigeon は、どのようなニーズにも対応し、大容量ファイルの送受信をシームレスに行うことができます。 Digital Pigeon を Microsoft Entra ID と統合すると、以下のことが可能になります。

- Digital Pigeon にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Digital Pigeon に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

テスト環境で Digital Pigeon 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Digital Pigeon は、**SP** と **IDP** によって開始されるシングル サインオンと、**Just In Time** ユーザー プロビジョニングの両方をサポートしています。

### 前提条件

Microsoft Entra ID を Digital Pigeon と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Digital Pigeon の、シングル サインオン (SSO) が有効なサブスクリプション (つまり、Business または Enterprise プラン)
- 上記のサブスクリプションに対する Digital Pigeon アカウント所有者のアクセス権

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Digital Pigeon アプリケーションを追加する必要があります。 アプリケーションに割り当て、シングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Digital Pigeon を Microsoft Entra ギャラリーから追加する

Microsoft Entra アプリケーション ギャラリーから Digital Pigeon を追加し、Digital Pigeon でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、[クイック スタート: ギャラリーからのアプリケーションの追加](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)に関する記事を参照してください。

#### Microsoft Entra テスト ユーザーを作成して割り当てる

[ユーザー アカウントの作成と割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

注

Microsoft Entra ID でアプリ ロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。 ロールの値は、"Digital Pigeon ユーザー"、"Digital Pigeon パワー ユーザー"、"Digital Pigeon 管理者" のいずれかにする必要があります。 ロール要求が指定されていない場合、Digital Pigeonアプリ (`Account Settings > SSO > SAML Provisioning Settings`) 内でDigital Pigeonの所有者が既定のロールを構成できます。次に示すスクリーンショットは、SAMLプロビジョニングの既定のロールを構成する方法を示しています。[Image: SAMLの既定のロール]

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができます。 ウィザードは、シングル サインオン構成ウィンドウへのリンクも提供します。 [Microsoft 365 ウィザードの詳細をご覧ください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO を構成する

Microsoft Entra のシングル サインオンを有効にするには、以下の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Digital Pigeon**&gt;**シングルサインオン**にアクセスする。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML でシングル サインオンをセットアップします]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集を示すスクリーンショット。]
5. 別のブラウザー タブで、アカウント管理者として Digital Pigeon にログインします。
6. **[アカウント設定] &gt; [SSO]** に移動し、**SP エンティティ ID** と **SP ACS URL** の値をコピーします。

    [Image: Digital Pigeon SAML サービス プロバイダーの設定を示すスクリーンショット。]
7. Microsoft Entra ID の **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** テキスト ボックスに、*[Digital Pigeon] &gt; [アカウント設定] &gt; [SSO] &gt;**[SP エンティティ ID]*** の値を貼り付けます。 次のパターンに一致する必要があります。`https://digitalpigeon.com/saml2/service-provider-metadata/<CustomerID>`

    b。 **[応答 URL]** テキスト ボックスに、*[Digital Pigeon] &gt; [アカウント設定] &gt; [SSO] &gt;**[SP ACS URL]*** の値を貼り付けます。 次のパターンに一致する必要があります。`https://digitalpigeon.com/login/saml2/sso/<CustomerID>`
8. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** テキストボックスに、URL として「`https://digitalpigeon.com/login`」と入力します。
9. Digital Pigeon アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性構成の画像を示すスクリーンショット。]
10. その他に、Digital Pigeon アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユーザー名.ファーストネーム | User.givenname |
    | user.lastName | User.surname |
11. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、**[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
12. Digital Pigeon で、ダウンロードした**フェデレーション メタデータ XML** ファイルの内容を **[IDP メタデータ XML]** テキスト フィールドに貼り付けます。

    [Image: IDP メタデータ XML を示すスクリーンショット。]
13. Microsoft Entra ID の **[Digital Pigeon の設定]** セクションで、Microsoft Entra ID の URL をコピーします。

    [Image: 構成の適切な URL をコピーすることを示すスクリーンショット。]
14. Digital Pigeon で、この URL を **[IDP エンティティ ID]** テキスト フィールドに貼り付けます。

    [Image: IDP エンティティ ID を示すスクリーンショット。]
15. **Save** ボタンを選択して Digital Pigeon の SSO を有効にします。

#### Digital Pigeon のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Digital Pigeon に作成します。 Digital Pigeon では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Digital Pigeon にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Digital ピジョン サインオン URL にリダイレクトされます。
- Digital Pigeon のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Digital ピジョンに自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイアプリで[Digital ピジョン]タイルを選択すると、SPモードの場合は、ログインプロセスを開始するためのアプリケーションのサインオンページにリダイレクトされ、IDPモードの場合は、SSOを設定したDigital ピジョンに自動的にサインインします。 詳細については、[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dining-sidekick-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Dining Sidekick を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dining-sidekick-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Dining Sidekick 間のシングル サインオンを構成する方法について説明します。

この記事では、Dining Sidekick と Microsoft Entra ID を統合する方法について説明します。 Dining Sidekick を Microsoft Entra ID を統合すると、次のことができます。

- Dining Sidekick にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Dining Sidekick に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Dining Sidekick でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- バークレーサイドミックでは、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Gallery Sidekick の追加

Microsoft Entra ID への Dining Sidekick の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Dining Sidekick を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Dining Sidekick**」と入力します。
4. 結果 **のパネルから [Dining ** Sidekick] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Dining Sidekick 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Dining Sidekick で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Dining Sidekick の関連ユーザーとの間にリンク関係を確立する必要があります。

Dining Sidekick に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Dining Sidekick SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Dining Sidekick テスト ユーザーの作成** - Dining Sidekick で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Dining Sidekick**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://api.diningsidekick.com`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://api.diningsidekick.com/api_user/samlsuccess`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://api.diningsidekick.com/api_user/samllogin`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Dining Sidekick**のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Dining Sidekick の構成

**Dining Sidekick** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [UserTesting サポート チーム](mailto:support@gethangry.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Dining Sidekick のテスト ユーザーの作成

このセクションでは、Dining Sidekick で Britta Simon というユーザーを作成します。 [Dining Sidekick チームと連絡](mailto:support@gethangry.com) を取り、バークレート サイドミック プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Dining Sidekick のサインオン URL にリダイレクトされます。
- Dining Sidekick モバイル アプリを開き、[**Sidekick University] を** 選択して、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Dining Sidekick] タイルを選択すると、このオプションは Dining Sidekick のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/direct-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用にダイレクト構成を行う - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/direct-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と direct の間のシングル サインオンを構成する方法について説明します。

この記事では、Direct と Microsoft Entra ID を統合する方法について説明します。 direct を Microsoft Entra ID と統合すると、次のことが可能になります。

- direct にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで direct に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- direct でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- direct は、**SP**主導のSSOと**IDP**主導のSSOをサポートします。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの direct の追加

Microsoft Entra ID への direct の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に direct を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックスに **「direct** 」と入力します。
4. 結果パネルから **ダイレクト** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### direct に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、Direct に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと direct の関連ユーザーとの間にリンク関係を確立する必要があります。

direct に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ダイレクト SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **direct テスト ユーザーの作成** - direct で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[direct]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://direct4b.com/`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://direct4b.com/sso`
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **ダイレクトのセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### direct の SSO の構成

**ダイレクト**側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を[直接サポート チーム](https://direct4b.com/ja/support.html#inquiry)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### direct テスト ユーザーの作成

このセクションでは、direct で Britta Simon というユーザーを作成します。 [直接サポート チーム](https://direct4b.com/ja/support.html#inquiry)と協力して、ダイレクト プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できるダイレクト サインオン URL にリダイレクトされます。
- direct のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定したダイレクトに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで直接タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したダイレクトに自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/directory-services-protector-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Directory Services 保護機能を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/directory-services-protector-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Directory Services Protector の間のシングル サインオンを構成する方法について説明します。

この記事では、Directory Services 保護機能と Microsoft Entra ID を統合する方法について説明します。 Directory Services Protector と Microsoft Entra ID を統合すると、次のことができます。

- Directory Services Protector にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して自動的に Directory Services Protector にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Directory Services Protector でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Directory Services Protector は、**SP と IDP** によって開始される SSO をサポートしています。
- Directory Services Protector では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Directory Services Protector の追加

Microsoft Entra ID への Directory Services Protector の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Directory Services Protector を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Directory Services Protecto**」と入力します。
4. 結果のパネルから **[Directory Services Protecto]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Directory Services Protector に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Directory Services Protector に対する Microsoft Entra SSO を構成してテストします。 SSO を使用するには、Microsoft Entra ユーザーと Directory Services Protector の関連ユーザーとの間にリンク関係を確立する必要があります。

Directory Services Protector に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Directory Services Protector の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Directory Services Protector のテストユーザーの作成 - Directory Services Protector において B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーにリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Directory Services Protector]**&gt;**[シングル サイン オン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、**サービス プロバイダー メタデータ ファイル**がある場合は、次の手順に従います。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルのアップロード方法を示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択方法を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**と**応答 URL** の値が、[基本的な SAML 構成] セクションに自動的に設定されます。

    [Image: メタデータ ファイルの画像を示すスクリーンショット。]

    注

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. **SP** 開始モードでアプリケーションを構成するには、次の手順を実行します。

    **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<HOSTNAME>.<DOMAIN>.<EXTENSION>/DSP/Login/SsoLogin`

    注

    サインオン URL の値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を入手するには、[Directory Services Protector のサポート チーム](mailto:support@semperis.com)に問い合わせてください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Directory Services Protector アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性構成の画像を示すスクリーンショット。]
8. その他に、Directory Services Protector アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ロール | user.assignedroles |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
9. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Directory Services Protector のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Directory Services Protector の SSO を構成する

1. Directory Services Protector の企業サイトに管理者としてログインします。
2. **[設定] (歯車アイコン)**&gt;**[データ接続]**&gt;[SAML 認証]**] ** に移動し、**[有効]** スイッチをオンに切り替えます。
3. 手順 **1 - ID プロバイダー**で、ドロップダウン メニューから **Microsoft Entra ID を** 選択し、[保存] を選択 **します**。
4. 手順 **2 - SAML ID プロバイダーに必要なデータ**で、[**CONFIRM**] ボタンと **DOWNLOAD METADATA XML** を選択して、Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションで**メタデータ ファイルをアップロード**し、[保存] を選択**します**。

    [Image: ID プロバイダーの設定を示すスクリーンショット。]
5. 手順 **3 - ユーザー属性と要求** では、この情報は必要ないので、手順 4 にスキップしてかまいません。
6. 手順 **4 – SAML ID プロバイダーから受信するデータ**では、メタデータ URL からのインポートと Microsoft Entra ID によって提供されるメタデータ XML のインポートの両方が DSP によってサポートされます。

    1. **[アプリのフェデレーション メタデータ URL]** ラジオ ボタンを選択し、Microsoft Entra ID の **[メタデータ URL]** をフィールドに貼り付けて、**[インポート]** を選択します。
    2. [ **フェデレーション メタデータ XML のインポート** ] を使用するラジオ ボタンを選択し、[ **IMPORT XML** ] を選択して Microsoft Entra 管理センターから **フェデレーション メタデータ XML** ファイルをアップロードします。
    3. **[保存] を選択します**。
7. DSP の **[SAML 認証]** ブレードの上部で、**[状態]** が **[構成済み]** と表示されるようになるはずです。

#### Directory Services Protector のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Directory Services Protector に作成します。 Directory Services Protector では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Directory Services Protector にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Directory Services 保護機能のサインオン URL にリダイレクトされます。
- Directory Services Protector のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Directory Services 保護機能に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Directory Services 保護機能] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Directory Services 保護機能に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/directory-services-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Directory Services を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/directory-services-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Directory Services の間でシングル サインオンを構成する方法について説明します。

この記事では、ディレクトリ サービスと Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Directory Services を統合すると、次のことができます。

- ディレクトリ サービスにアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Directory Services に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Directory Services でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Directory Services は、**SP Initiated SSO** と **IDP Initiated SSO** をサポートします。
- Directory Services では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Directory Services では、自動ユーザー プロビジョニング サポートされています。

### ギャラリーからディレクトリ サービスを追加する

Microsoft Entra ID へのディレクトリ サービスの統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧にディレクトリ サービスを追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Directory Services**」と入力します。
4. 結果パネルから **[ディレクトリ サービス** ] を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Directory Services の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Directory Services に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Directory Services の関連ユーザーとの間にリンク関係を確立する必要があります。

Directory Services に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Directory Services の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Directory Services のテスト ユーザーの作成** - Directory Services で B.Simon に対応するテスト ユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Directory Services**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://<HOSTNAME.DOMAIN.com>/otdsws/login` |
    | `https://<HOSTNAME.DOMAIN.com>/<OTDS_TENANT>/<TENANTID>/otdsws/login` |
    | `https://<HOSTNAME.DOMAIN.com>/otdsws/<OTDS_TENANT>/<TENANTID>/login` |
    | `https://<HOSTNAME.DOMAIN.com>/<OTDS_TENANT>/<TENANTID>/login` |
    |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 応答 URL |
    | --- |
    | `https://<HOSTNAME.DOMAIN.com>/otdsws/login` |
    | `https://<HOSTNAME.DOMAIN.com>/<OTDS_TENANT>/<TENANTID>/otdsws/login` |
    | `https://<HOSTNAME.DOMAIN.com>/otdsws/<OTDS_TENANT>/<TENANTID>/login` |
    | `https://<HOSTNAME.DOMAIN.com>/<OTDS_TENANT>/<TENANTID>/login` |
    |  |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://<HOSTNAME.DOMAIN.com>/otdsws/login` |
    | `https://<HOSTNAME.DOMAIN.com>/<OTDS_TENANT>/<TENANTID>/otdsws/login` |
    | `https://<HOSTNAME.DOMAIN.com>/otdsws/<OTDS_TENANT>/<TENANTID>/login` |
    | `https://<HOSTNAME.DOMAIN.com>/<OTDS_TENANT>/<TENANTID>/login` |
    |  |

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Directory Services サポート チーム](mailto:support@opentext.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Directory Services の SSO の構成

**Directory Services** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Directory Services サポート チーム](mailto:support@opentext.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Directory Services のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Directory Services に作成します。 Directory Services では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ディレクトリ サービスにユーザーがまだ存在していない場合は、認証後に新しく作成されます。

手記

Directory Services では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できるディレクトリ サービスのサインオン URL にリダイレクトされます。
- Directory Services のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定したディレクトリ サービスに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ディレクトリ サービス] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したディレクトリ サービスに自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/directprint-io-cloud-print-administration-tutorial"} -->
## Microsoft Entra ID とシングル サインオンで連携するために、directprint.io Cloud Print 管理を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/directprint-io-cloud-print-administration-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と directprint.io Cloud Print Administration の間でシングル サインオンを構成する方法について説明します。

この記事では、directprint.io Cloud Print Administration と Microsoft Entra ID を統合する方法について説明します。 directprint.io Cloud Print Administration を Microsoft Entra ID と統合すると、次のことを行なえるようになります。

- directprint.io Cloud Print Administration にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで directprint.io Cloud Print Administration に自動的にサインインされるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- directprint.io Cloud Print Administration のシングル サインオン (SSO) が有効になっているサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- directprint.io Cloud Print Administration では、 **IDP** Initiated SSO がサポートされます。
- directprint.io Cloud Print Administration では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの directprint.io Cloud Print Administration の追加

Microsoft Entra ID への directprint.io Cloud Print Administration の統合を構成するには、directprint.io Cloud Print Administration をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス **directprint.io「Cloud Print Administration**」と入力します。
4. 結果パネルから **directprint.io Cloud Print Administration** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### directprint.io Cloud Print Administration に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、directprint.io Cloud Print Administration に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと directprint.io Cloud Print Administration での関連ユーザーとの間にリンク関係を確立する必要があります。

directprint.io Cloud Print Administration に対して Microsoft Entra SSO を構成してテストするため、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **directprint.io Cloud Print Administration の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **directprint.io Cloud Print Administration テスト ユーザーの作成** - directprint.io Cloud Print Administration で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[Enterprise アプリ]**&gt;**[directprint.io Cloud Print Administration]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは IDP 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **directprint.io クラウド印刷管理のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### directprint.io Cloud Print Administration SSO の構成

**directprint.io Cloud Print Administration 側**でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL**を[directprint.io Cloud Print Administration サポート チーム](mailto:support@directprint.io)に送信してください。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### directprint.io Cloud Print Administration テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを directprint.io Cloud Print Administration に作成します。 directprint.io Cloud Print Administration では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 directprint.io Cloud Print Administration にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した directprint.io Cloud Print Administration に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [directprint.io Cloud Print Administration] タイルを選択すると、SSO を設定した directprint.io Cloud Print Administration に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/directprint-io-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して自動ユーザー プロビジョニング用に directprint.io を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/directprint-io-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: ユーザー アカウントを directprint.io に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために、directprint.io ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID によって、Microsoft Entra プロビジョニング サービスを使用し、ユーザーとグループが https://directprint.io に自動的にプロビジョニングおよび解除されます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされる機能

- directprint.io でユーザーを作成する。
- アクセスが不要になったら、directprint.io のユーザーを削除します。
- Microsoft Entra ID と directprint.io 間でユーザー属性の同期を維持する。
- directprint.io でグループとグループ メンバーシップをプロビジョニングする。
- directprint.io に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/directprint-io-cloud-print-administration-tutorial)します (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra ID でのシングル サインオンが完了している。
- directprint.io でのライセンスまたは 30 日間無料試用アカウント。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と directprint.io の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように directprint.io を構成する

1. [directprint.io アカウント](https://directprint.io/login/)にログインします。
2. Microsoft Entra の SSO とプロビジョニングの画面に移動します。
3. 今後参照するために、テナント URL とシークレット トークンを保存します。 **手順 5** で必要になります。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから directprint.io を追加する

Microsoft Entra アプリケーション ギャラリーから directprint.io を追加して、directprint.io へのプロビジョニングの管理を開始します。 SSO のために directprint.io を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: directprint.io への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて directprint.io 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で directprint.io の自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **directprint.io** を選択します。

    [Image: アプリケーションの一覧の [directprint.io] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、直接印刷テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが directprint に接続できることを確認します。 接続に失敗した場合は、Directprint アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、[グループ] を選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から directprint.io に同期されるグループ **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作の directprint.io のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | externalId | 糸 |  |
    | members | 関連項目 |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/discovery-benefits-sso-tutorial"} -->
## Microsoft Entra ID で Discovery Benefits SSO のシングル サインオンを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/discovery-benefits-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Discovery Benefits SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、Discovery Benefits SSO と Microsoft Entra ID を統合する方法について説明します。 Discovery Benefits SSO と Microsoft Entra ID を統合すると、次のことができます。

- Discovery Benefits SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Discovery Benefits SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Discovery Benefits SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Discovery Benefits SSO では、**IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Discovery Benefits SSO の追加

Microsoft Entra ID への Discovery Benefits SSO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Discovery Benefits SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Discovery Benefits SSO**」と入力します。
4. 結果のパネルから **[Discovery Benefits SSO]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Discovery Benefits SSO に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Discovery Benefits SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Discovery Benefits SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Discovery Benefits SSO で Microsoft Entra SSO を構成してテストするには、次の項目を完了する必要があります。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Discovery Benefits SSO SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Discovery Benefits SSO テスト ユーザーの作成r** - Discovery Benefits SSO で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Discovery Benefits SSO]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Discovery Benefits SSO アプリケーションは特定の形式の SAML アサーションを予測しているため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 **[編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: 画像]

    a. [ **編集]** アイコンを選択して、[ **一意のユーザー識別子 (名前 ID)]** ダイアログを開きます。

    [Image: 右側に [必須の要求] 省略記号が選択されている [ユーザー属性と要求] セクションを示すスクリーンショット。]

    [Image: Discovery Benefits SSO の構成]

    b。 [ **編集]** アイコンを選択して、[ **変換の管理** ] ダイアログを開きます。

    c. **[変換]** ボックスに、その行に対して表示される「**ToUppercase()** 」を入力します。

    d. **[パラメーター 1]** ボックスに、`<Name Identifier value>`のようなパラメーターを入力します。

    e. [**] を選択し、[**] を追加します。

    注

    Discovery Benefits SSO では、この統合を機能させるために、固定文字列値を **[一意のユーザー ID (名前 ID)]** フィールドに渡す必要があります。 現在、Microsoft Entra ID ではこの機能がサポートされていないため、回避策として、NameID の **ToUpper** または **ToLower** 変換を使用して、固定文字列値を設定できます (上のスクリーンショットを参照)。

    f. SSO の構成に必要な追加の要求 (`SSOInstance` および `SSOID`) が自動的に設定されました。 **鉛筆**アイコンを使用して、組織に応じて値をマップします。

    [Image: [S S O インスタンス] と [S S O I D] の値が強調表示されている [ユーザー属性と要求] を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Discovery Benefits SSO のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Discovery Benefits SSO の構成

**Discovery Benefits SSO** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [Discovery Benefits SSO のサポート チーム](mailto:Jsimpson@DiscoveryBenefits.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Discovery Benefits SSO テスト ユーザーの作成

このセクションでは、Discovery Benefits SSO で Britta Simon というユーザーを作成します。 [Discovery Benefits SSO のサポート チーム](mailto:Jsimpson@DiscoveryBenefits.com)と連携して、Discovery Benefits SSO プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Discovery Benefits SSO に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Discovery Benefits SSO] タイルを選択すると、SSO を設定した Discovery Benefits SSO に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/displayr-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Displayr を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/displayr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Displayr の間のシングル サインオンを構成する方法について説明します。

この記事では、Displayr と Microsoft Entra ID を統合する方法について説明します。 Displayr を Microsoft Entra ID と統合すると、次のことが可能になります。

- Displayr にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Displayr に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Displayr でのシングル サインオン (SSO) が有効な会社。

### シナリオの説明

この記事では、Displayr 企業で Microsoft Entra SSO を構成する方法について説明します。

- Displayr では、**SP** によって開始される SSO がサポートされます

### ギャラリーからの Displayr の追加

Microsoft Entra ID への Displayr の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Displayr を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに "**Displayr**" と入力します。
4. 結果のパネルから **[Displayr]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Displayr に Microsoft Entra SSO を構成する

Displayr で Microsoft Entra SSO を構成するには、次の手順を実行します。

1. **Microsoft Entra SSO を構成して**、ユーザーがこの機能を使用できるようにします。
2. **Displayr SSO を構成**し、アプリケーション側で SSO 設定を構成します。
3. **特定のユーザーへのアクセスを制限**し、Displayr にサインインできる Microsoft Entra ユーザーを制限します。
4. **SSO をテスト**して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Displayr** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **SAML によるシングル サインオンのセットアップ** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して値を入力します。`<EntityID>`

    b。 **[応答 URL]** テキスト ボックスに、URL `<ACS URL>` を入力します。

    c. **[サインオン URL]** ボックスに、`https://<YOURDOMAIN>.displayr.com` という形式で URL を入力します。

    d. **保存**を選択します。

    Note

    EntityID と ACS URL は、Displayr アカウント設定ページにあります。 [**アカウント設定]**&gt;**[設定]** タブに移動 &gt;**シングル サインオン (SAML)**を構成します。詳細は[**サービス プロバイダー情報]** 見出しにあります。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** をクリックして**証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. Displayr アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。 **[編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: [編集] アイコンが強調表示されている [ユーザー属性] セクションを示すスクリーンショット。]
8. その他に、Displayr アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 **[グループ要求 (プレビュー)]** ダイアログの **[ユーザー属性と要求]** セクションで、次の手順を実行します。

    1. **[グループ要求**の追加] を選択します。
    2. ラジオ ボタンのリストから **[すべてのグループ]** を選択します。
    3. **[グループ ID]** の **[ソース属性]** を選択します。
    4. **保存**を選択します。
9. **セットアップ画面** セクションで、要件に応じて 1 つ以上の適切な URL をコピーします。

    [Image: 構成 URL のコピー]

### Displayr SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として Displayr 企業サイトにサインインします。
2. **[ユーザー**] アイコンを選択し、[**アカウント設定]** に移動します。

    [Image: [設定] アイコンと [アカウント] が選択されていることを示すスクリーンショット。]
3. 上部のメニューから **[設定]** に切り替え、ページを下にスクロールして [ **シングル サインオンの構成 (SAML)]** を選択します。

    [Image: [設定] タブが選択され、[Configure Single Sign On (S A M L)](シングル サインオンの構成 (S A M L)) アクションが選択されていることを示すスクリーンショット。]
4. **[Single sign-on (SAML)](シングル サインオン (SAML))** ページで、次の手順に従います。

    [Image: 構成を示すスクリーンショット。]

    a. **[Enable Single Sign On (SAML)](シングル サインオン (SAML) を有効にする)** ボックスをオンにします。

    b。 Microsoft Entra ID の **[基本的な SAML 構成]** セクションから実際の **ID** 値をコピーして、 **[発行者]** テキスト ボックスに貼り付けます。

    c. **[ログイン URL]** テキスト ボックスに **[ログイン URL]** の値を貼り付けます。

    d. **[ログアウト URL]** テキスト ボックスに **[ログアウト URL]** の値を貼り付けます。

    e. 証明書 (Base64) をメモ帳で開き、その内容をコピーして **[証明書]** テキスト ボックスに貼り付けます。

    f. **グループ マッピング**は省略可能です。

    g. **保存**を選択します。

#### 特定のユーザーにアクセスを制限する

既定では、Displayr アプリケーションを追加したテナント内のすべてのユーザーは、SSO を使用して Displayr にサインインできます。 特定のユーザーまたはグループにアクセスを制限する場合、「[Microsoft Entra アプリを Microsoft Entra テナントの一連のユーザーに制限する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restrict-your-app-to-a-set-of-users)」を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる Displayr のサインオン URL にリダイレクトされます。
- Displayr のサインオン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Displayr] タイルを選択すると、このオプションは Displayr のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dlg-learning-center-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に DLG Learning Center を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dlg-learning-center-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と DLG Learning Center の間でシングル サインオンを構成する方法について説明します。

この記事では、DLG ラーニング センターと Microsoft Entra ID を統合する方法について説明します。 DLG ラーニング センターを Microsoft Entra ID と統合すると、次のことができます。

- DLG ラーニング センターにアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して DLG Learning Center に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- DLG Learning Center でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- DLG Learning Center では、 **IDP** によって開始される SSO のみがサポートされます。

### ギャラリーから DLG ラーニング センターを追加する

Microsoft Entra ID への DLG Learning Center の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に DLG ラーニング センターを追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「DLG Learning Center**」と入力します。
4. 結果パネルから **DLG Learning Center** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### DLG Learning Center の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、DLG Learning Center に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと DLG Learning Center の関連ユーザーとの間にリンク関係を確立する必要があります。

DLG Learning Center で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **DLG Learning Center の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **DLG Learning Center のテスト ユーザーを作成する** - B.Simon に相当するユーザーを DLG Learning Center で作成し、そのユーザーを Microsoft Entra の表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**DLG ラーニング センター**&gt;**シングル サインオン**を開きます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. [ **基本的な SAML 構成]** セクションでは、アプリは既に Microsoft Entra と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. [ **DLG ラーニング センターのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### DLG Learning Center の SSO の構成

**DLG Learning Center** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と Microsoft Entra 管理センターからコピーした適切な URL を [DLG Learning Center サポート チーム](mailto:help@dlglearningcenter.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### DLG Learning Center のテスト ユーザーの作成

このセクションでは、DLG ラーニング センターで B.Simon というユーザーを作成します。 [DLG ラーニング センター サポート チーム](mailto:help@dlglearningcenter.com)と協力して、DLG ラーニング センター プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した DLG ラーニング センターに自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [DLG Learning Center] タイルを選択すると、SSO を設定した DLG ラーニング センターに自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dmarcian-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に dmarcian を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dmarcian-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-07-14
- Summary: Microsoft Entra ID と dmarcian の間のシングル サインオンを構成する方法について説明します。

この記事では、dmarcian と Microsoft Entra ID を統合する方法について説明します。 dmarcian を Microsoft Entra ID と統合すると、次のことができます。

- dmarcian にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで dmarcian に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- dmarcian のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- dmarcian では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーからの dmarcian の追加

Microsoft Entra ID への dmarcian の統合を構成するに、ギャラリーから管理対象 SaaS アプリの一覧に dmarcian を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「dmarcian**」と入力します。
4. 結果パネルから **dmarcian** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### dmarcian の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、dmarcian に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと dmarcian の関連ユーザー間にリンク関係を確立する必要があります。

dmarcian に対する Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **dmarcian SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **dmarcian テスト ユーザーの作成** - dmarcian で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**dmarcian** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。 [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    1. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

        | **識別子** |
        | --- |
        | `https://us.dmarcian.com/sso/saml/<ACCOUNT_ID>/sp.xml` |
        | `https://eu.dmarcian.com/sso/saml/<ACCOUNT_ID>/sp.xml` |
        | `https://ca.dmarcian.com/sso/saml/<ACCOUNT_ID>/sp.xml` |
        | `https://ap.dmarcian.com/sso/saml/<ACCOUNT_ID>/sp.xml` |
        | `https://au.dmarcian.com/sso/saml/<ACCOUNT_ID>/sp.xml` |
        | `https://jp.dmarcian.com/sso/saml/<ACCOUNT_ID>/sp.xml` |
    2. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

        | **応答 URL** |
        | --- |
        | `https://us.dmarcian.com/login/<ACCOUNT_ID>/handle/` |
        | `https://eu.dmarcian.com/login/<ACCOUNT_ID>/handle/` |
        | `https://ca.dmarcian.com/login/<ACCOUNT_ID>/handle/` |
        | `https://ap.dmarcian.com/login/<ACCOUNT_ID>/handle/` |
        | `https://au.dmarcian.com/login/<ACCOUNT_ID>/handle/` |
        | `https://jp.dmarcian.com/login/<ACCOUNT_ID>/handle/` |
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://us.dmarcian.com/login/<ACCOUNT_ID>` |
    | `https://eu.dmarcian.com/login/<ACCOUNT_ID>` |
    | `https://ca.dmarcian.com/login/<ACCOUNT_ID>` |
    | `https://ap.dmarciam.com/login/<ACCOUNT_ID>` |
    | `https://au.dmarciam.com/login/<ACCOUNT_ID>` |
    | `https://jp.dmarciam.com/login/<ACCOUNT_ID>` |

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、Sign-On URL で更新します。これについては、後で説明します。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を**コピーし、新しいブラウザー タブで開き、ページの内容を XML ファイルとしてダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### dmarcian SSO の構成

1. 別の Web ブラウザー ウィンドウで、dmarcian 企業サイトに管理者としてサインインします。
2. 右上隅にある⚙️ **歯車アイコン** をクリックし、[ **環境設定]** を選択します。
[Image: [基本設定] メニューのスクリーンショット。]
3. [ **SSO** ] タブをクリックします。
[Image: [SSO] タブのスクリーンショット。]
4. まだ設定されていない場合は、状態を **[有効]** に設定し、次の手順を実行します。
[Image: 構成フォームのスクリーンショット。]
    1. [**ID プロバイダーへの dmarcian の追加**] セクションで、**コピー アイコン**をクリックしてインスタンスの **Assertion Consumer Service URL を**コピーし、Azure portal の **[基本的な SAML 構成] セクション**の **[応答 URL**] ボックスに貼り付けます。
    2. [**ID プロバイダーへの dmarcian の追加**] セクションで、**コピー アイコン**をクリックしてインスタンスの**エンティティ ID を**コピーし、Azure portal の **[基本的な SAML 構成] セクション**の **[識別子**] ボックスに貼り付けます。
    3. [ **認証の設定** ] セクションの [ **ID プロバイダー メタデータ**] で、[ファイルのアップロード] ボタンをクリックして、 **アプリのフェデレーション メタデータ URL** からダウンロードした XML ファイルをアップロードします。
    4. [ **認証の設定** ] セクションの **[属性ステートメント** ] ボックスに、Azure portal で指定した電子メール アドレスを貼り付けます。
    5. [**ログイン URL の設定**] セクションで、インスタンスの**ログイン URL を**コピーし、Azure portal の **[基本的な SAML 構成] セクション**の **[サインオン URL**] ボックスに貼り付けます。

        注

        組織に応じて **ログイン URL を** 変更できます。
    6. [オプションの構成] で、チェックボックスをオンにして、すべてのユーザーに SSO 認証を適用するかどうかを選択できます。
    7. **[保存] を選択します**。

#### dmarcian のテスト ユーザーの作成

Microsoft Entra ユーザーが dmarcian にサインインできるようにするには、ユーザーを dmarcian にプロビジョニングする必要があります。 dmarcian では、プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. セキュリティ管理者として dmarcian にサインインします。
2. 右上隅にある⚙️ **歯車アイコン** をクリックし、[ユーザーの管理] を選択 **します**。
[Image: [ユーザーの管理] メニューのスクリーンショット。]
3. [ **+ 新しいユーザーの追加] ボタンを** クリックします。
[Image: [ユーザーの追加] ボタンのスクリーンショット。]
4. [ **新しいユーザーの追加]** ポップアップで、次の手順を実行します。

    [Image: 新しいユーザーを示すスクリーンショット。]

    1. [ **新しいユーザー電子メール** ] ボックスに、ユーザーの電子メール ( `email@domain.com` など) を入力し、 **Enter キー**を押します。
    2. 必要に応じて、ユーザーのアクセス許可とドメイン グループアクセスを変更できます。
    3. ポップアップの右下にある [ **+ 新しいユーザーの追加** ] ボタンをクリックします。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる dmarcian のサインオン URL にリダイレクトされます。
- dmarcian のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した dmarcian に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで dmarcian タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した dmarcian に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/docker-tutorial"} -->
## Microsoft Entra ID で Docker Business for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/docker-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-13
- Summary: Microsoft Entra ID と Docker Business 間にシングル サインオンを構成する方法について説明します。

この記事では、Docker Business と Microsoft Entra ID を統合する方法について説明します。 Docker Business と Microsoft Entra ID を統合すると、次のことができます。

- Docker Business にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Docker Business に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Docker Business サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Docker Business では、 **SP** によって開始される SSO のみがサポートされます。
- Docker Business では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Docker Business を追加する

Microsoft Entra ID への Docker Business の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Docker Business を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Docker Business」**と入力します。
4. 結果パネルから **Docker Business** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Docker Business での Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Docker Business に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Docker Business の関連ユーザーとの間にリンク関係を確立する必要があります。

Docker Business による Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Docker Business の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Docker Business のテスト ユーザーの作成** - Docker Business で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Docker Business**&gt;**シングルサインオン**を参照する。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:docker-prod:<Docker_SsoID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.docker.com/login/callback?connection=<Docker_SsoID>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://hub.docker.com/auth/start?connection=<Docker_SsoID>`

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値は、Docker Business SSO の構成中に取得します。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Docker Business アプリケーションでは、user.userprincipalname ではなく電子メール アドレス (**user.mail**) にマップされた**一意**のユーザー識別子が必要です。また、ユーザーのフル ネームを Docker Business アプリに同期する**特定の名前**と**姓**もサポートしています。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の画像を示すスクリーンショット。]

    注意

    アプリケーションの要件に従って、Microsoft Entra 管理センターの上記の既定の属性から **名前** と **emailaddress** を手動で削除してください。
7. 上記に加えて、次に示すような、Docker Business アプリケーションでは、SAML 応答で返される省略可能な属性がサポートされています。 これらの属性を追加すると、特定のチーム内のユーザーのプロビジョニングと、その Docker Business 組織内でのロールを管理できます。

    | 要求名 | 名前空間 | ソース属性 |
    | --- | --- | --- |
    | dockerOrg | &lt;`empty`&gt; | Docker 組織名 |
    | dockerTeam | &lt;`empty`&gt; | Docker チーム名 |
    | ドッカーロール | &lt;`empty`&gt; | 組織のユーザー ロール。 使用できる値: "owner"、"editor"、"member"。 |

    注意

    組織がユーザーを複数のチームに分けて管理する必要がある場合は、グループ要求を有効にすることもできます。 Docker SSO グループ管理の詳細については、 [こちらを選択してください](https://docs.docker.com/security/for-admins/group-mapping/)。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。
9. [ **Docker Business のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Docker Business SSO の構成

1. Docker Business 企業サイトに管理者としてログインします。
2. 左側のドロップダウン メニューから組織または会社を選択し、 **SSO と SCIM** を選択します。
3. SSO 接続テーブルで、[接続の **作成** ] を選択し、接続の名前を作成します。

    注意

    接続を作成する前に、少なくとも 1 つのドメインを確認する必要があります。
4. 認証方法として **SAML** を選択し、次の手順を実行します。

    [Image: [構成] を示すスクリーンショット。]

    1. **エンティティ ID の値を**コピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの [**識別子 (エンティティ ID)]** テキスト ボックスにこの値を貼り付けます。
    2. **ACS URL** の値をコピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。
    3. **[SAML Sign-On URL**] フィールドに、Microsoft Entra 管理センターからコピーした**ログイン URL** 値を貼り付けます。
    4. ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容を **[キー x509 証明書** ] ボックスに貼り付けます。
    5. [ **次へ** ] を選択し **、[接続の保存] を選択します**。

#### Docker Business テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Docker Business に作成します。 Docker Business では Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Docker Business にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Docker Business サインオン URL にリダイレクトされます。
- Docker Business のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Docker Business] タイルを選択すると、このオプションは Docker Business のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/document360-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Document360 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/document360-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Document360 の間のシングル サインオン (SSO) を構成する方法について説明します。

この記事では、Document360 と Microsoft Entra ID を統合する方法について説明します。 Document360 は、オンラインのセルフサービス ナレッジ ベース ソフトウェアです。 Document360 を Microsoft Entra ID と統合すると、次のことが可能になります。

- Document360 にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Document360 に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Document360 に対する Microsoft Entra シングル サインオンをテスト環境で構成およびテストします。 Document360 では、 **サービス プロバイダー (SP)** と **ID プロバイダー (IdP)** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID と Document360 を統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウントを取得](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- SSO に対応した Document360 サブスクリプション。 サブスクリプションをお持ちでない場合は、 [新しいアカウントにサインアップ](https://document360.com/signup/)できます。

### アプリケーションを追加してテスト ユーザーを割り当てる

SSO を構成する前に、Microsoft Entra ギャラリーから Document360 アプリケーションを追加してください。 テスト ユーザー アカウントを作成し、アプリケーションに割り当てた後に、SSO の構成をテストする必要があります。

#### Microsoft Entra ギャラリーから Document360 を追加する

Microsoft Entra アプリケーション ギャラリーから Document360 を追加して、Document360 で SSO を構成します。 ギャラリーからのアプリケーションの追加の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Document360** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて**、シングル サインオン**を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。 データ センターのリージョンに基づいて、いずれかの識別子、応答 URL、サインオン URL を選択します。

    a. [ **識別子** ] ボックスに、次のいずれかの URL を入力/コピーして貼り付けます。

    | **識別子** |
    | --- |
    | `https://identity.document360.io/saml` |
    | **(または)** |
    | `https://identity.us.document360.io/saml` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力/コピーして貼り付けます。

    | **応答 URL** |
    | --- |
    | `https://identity.document360.io/signin-saml-<ID>` |
    | **(または)** |
    | `https://identity.us.document360.io/signin-saml-<ID>` |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のいずれかの URL を入力/コピーして貼り付けます。

    | **サインオン URL** |
    | --- |
    | `https://identity.document360.io` |
    | **(または)** |
    | `https://identity.us.document360.io` |

    注

    応答 URL は実際のものではありません。 実際の応答 URL でこの値を更新します。 Azure portal の **[基本的な SAML 構成** ] セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Document360 のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Document360 の SSO を構成する

1. 別の Web ブラウザー ウィンドウで、Document360 ポータルに管理者としてログインします。
2. **Document360** ポータルで SSO を構成するには、[**設定**] → **[ユーザー] と [セキュリティ**] → **SAML/OpenID** → **SAML** に移動し、次の手順を実行する必要があります。

    [Image: Document360 の構成を示すスクリーンショット。]
3. Document360 ポータル側の **SAML 基本構成** で [編集] アイコンを選択し、以下のフィールドの関連付けに基づいて Microsoft Entra 管理センターの値を貼り付けます。

    | Document360 ポータルのフィールド | Microsoft Entra 管理センターの値 |
    | --- | --- |
    | メール ドメイン | [Active Directory] にあるメールのドメイン |
    | サインオン URL | ログイン URL |
    | エンティティ識別子 | Microsoft Entra ID |
    | サインアウト URL | ログアウト URL |
    | SAML 証明書 | Microsoft Entra ID 側から証明書 (Base64) をダウンロードし、Document360 でアップロードする |
4. 値が完了したら、[ **保存]** ボタンを選択します。

#### Document360 のテスト ユーザーを作成する

1. 別の Web ブラウザー ウィンドウで、Document360 ポータルに管理者としてログインします。
2. Document360 ポータルから、[ **設定] → [ユーザー] と [セキュリティ] → チーム アカウントとグループ→チーム アカウントに**移動します。 [ **新しいチーム アカウント** ] ボタンを選択し、必要な詳細を入力し、ロールを指定し、モジュールの手順に従ってユーザーを Document360 に追加します。

    [Image: Document360 テスト ユーザーを示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Document360 サインオン URL にリダイレクトされます。
- Document360 のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Azure portal で [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Document360 に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 SP モードで構成されている場合、[マイ アプリ] で [Document360] タイルを選択すると、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。 IDP モードで構成されている場合は、SSO を設定した Document360 に自動的にサインインされます。

詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/documo-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Documo を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/documo-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: ユーザー アカウントを Documo に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Documo ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Documo](https://www.documo.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Documo でユーザーを作成する
- アクセスが不要になった場合に Documo のユーザーを削除する
- Microsoft Entra ID と Documo の間でユーザー属性の同期を維持する。
- Documo に対して[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/documo-tutorial)を行う (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- API アクセスを備えた [Documo](https://www.documo.com/) アカウント。
- Documo の管理者権限を持つユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Documo の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように Documo を構成する

1. Microsoft Entra のプロビジョニングに使う [API キーを生成](https://help.documo.com/hc/en-us/articles/7789630698011-How-to-Enable-and-Retrieve-API-Keys)します。
2. ご自分の API URL を見つけて覚えておいてください。 既定の API URL は `https://api.documo.com` です。 カスタム Documo API ドメインがある場合は、Documo のブランド設定ページの [ドメイン] タブで参照できます。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Documo を追加する

Microsoft Entra アプリケーション ギャラリーから Documo を追加して、Documo へのプロビジョニングの管理を開始します。 SSO のために Documo を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Documo への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて Documo 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Documo に対する自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Documo]** を選択します。

    [Image: アプリケーションの一覧の [Documo] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Documo テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Documo に接続できることを確認します。 接続に失敗した場合は、Documo アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Documo に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Documo のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Documo API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | roles[primary eq "True"].value | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/documo-tutorial"} -->
## Microsoft Entra ID で Documo for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/documo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Documo の間のシングル サインオンを構成する方法について説明します。

この記事では、Documo と Microsoft Entra ID を統合する方法について説明します。 Documo を Microsoft Entra ID と統合すると、次のことが可能になります。

- Documo にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Documo に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Documo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Documo では、**SP による開始 SSO** および **IDP による開始 SSO** がサポートされます。
- Documo では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/documo-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Documo の追加

Microsoft Entra ID への Documo の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Documo を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Documo**」と入力します。
4. 結果パネルから **Documo** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Documo に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Documo に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Documo の関連ユーザーとの間にリンク関係を確立する必要があります。

Documo に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Documo SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Documoのテストユーザーを作成** - B.Simonに対応するDocumoのユーザーを作成し、Microsoft Entraのユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Documo]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。 Documo アカウントのドメインがカスタム ドメインである場合、SSO が機能するためにはカスタム API ドメインも必要です。 既定値をカスタム API ドメインに置き換えてください (`https://mycustomapidomain.com`、`https://mycustomapidomain.com/assert` など)。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。`https://app.documo.com/sso`
7. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Documo のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Documo SSO の構成

1. Documo の Web サイトに管理者としてログインします。
2. アカウント**設定**&gt;Security に移動**します**。

    [Image: セキュリティ ページのスクリーンショット。]
3. [セキュリティ] タブで、ページの下部にある [ **SSO の構成** ] ボタンを選択します。

    [Image: [構成] ボタンのスクリーンショット。]
4. **[SAML のセットアップ]** ページで次の手順を実行します。

    [Image: 構成ページのスクリーンショット。]

    a. [ **エンティティ ID** ] ボックスに、前にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    b。 **[SSO URL(リダイレクト URL)]** ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    c. テキスト ボックスに [ **電子メール ドメイン]** の値を指定します。

    d. **[ID 電子メールを含む SAML トークン] テキスト ボックスの [フィールド名]** に値を入力します。

    e. ダウンロードした **フェデレーション メタデータ XML** をメモ帳で開きます。 `<X509Certificate>` タグを見つけて、[**署名者証明書**] ボックスに内容を貼り付けます。

    f. [ **送信] を選択します**。

#### Documo のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Documo に作成します。

1. Documo アプリの [\[ユーザー\] ページ](https://app.documo.com?redirectTo=/users) に移動します。
2. [ **新しいユーザー** ] ボタンを選択します。
3. ユーザー フォームに名前、メール アドレス、電話番号、ユーザー ロール、パスワードの情報を入力します。 **電子メール** フィールドが **Microsoft Entra ID** の B.Simon のメールと一致していることを確認します。
4. **[作成]**を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Documo サインオン URL にリダイレクトされます。
- Documo のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Documo に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Documo] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Documo に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/docusign-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に DocuSign を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/docusign-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra ID と DocuSign の間のシングル サインオンを構成する方法について学習します。

この記事の目的は、Microsoft Entra ID から DocuSign にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するために DocuSign と Microsoft Entra ID で実行する必要がある手順を示することです。

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- Microsoft Entra テナント。
- DocuSign でのシングル サインオンが有効なサブスクリプション。
- Team Admin アクセス許可がある DocuSign のユーザー アカウント。

### DocuSign へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に "割り当て" という概念が使用されます。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra ID のアプリケーションに "割り当て済み" のユーザーとグループのみが同期されます。

プロビジョニング サービスを構成して有効にする前に、DocuSign アプリへのアクセスが必要なユーザーを表す Microsoft Entra ID 内のユーザーやグループを決定しておく必要があります。 決定し終えたら、次の手順でこれらのユーザーを DocuSign アプリに割り当てることができます。

[エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを DocuSign に割り当てる際の重要なヒント

- プロビジョニング構成をテストするには、1 人の Microsoft Entra ユーザーを DocuSign に割り当てることをお勧めします。 後で追加のユーザーを割り当てられます。
- DocuSign にユーザーを割り当てるときに、有効なユーザー ロールを選択する必要があります。 "既定のアクセス" ロールはプロビジョニングでは機能しません。

注

Microsoft Entra ID では、Docusign アプリケーションを使用したグループ プロビジョニングはサポートされていません。ユーザーのみをプロビジョニングできます。

### ユーザー プロビジョニングの有効化

このセクションでは、Microsoft Entra ID を DocuSign のユーザー アカウント プロビジョニング API に接続する手順と、Microsoft Entra ID のユーザーとグループの割り当てに基づいて、割り当て済みのユーザー アカウントを DocuSign で作成、更新、無効化するようにプロビジョニング サービスを構成する手順を説明します。

ヒント

DocuSign では SAML ベースのシングル サインオンを有効にすることもできます。これを行うには、[Azure portal](https://portal.azure.com) で説明されている手順に従ってください。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### ユーザー アカウント プロビジョニングを構成するには

このセクションでは、Active Directory のユーザー アカウントのプロビジョニングを DocuSign に対して有効にする方法を説明します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. シングル サインオンのために DocuSign を既に構成している場合は、検索フィールドで DocuSign のインスタンスを検索します。 そうでない場合は、**[追加]** を選択し、アプリケーションギャラリーで **DocuSign** を検索します。 検索結果から DocuSign を選択してアプリケーションの一覧に追加します。
4. DocuSign のインスタンスを選択してから、 **[プロビジョニング]** タブを選択します。
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、DocuSign テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが DocuSign に接続できることを確認します。 接続に失敗した場合は、DocuSign アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。
7. [**概要**] ページで **[プロパティ**] を選択します。
8. [ **作成]** を選択して構成を作成します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から DocuSign に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で DocuSign のユーザー アカウントとの照合に使用されます。 [保存] ボタンをクリックして変更をコミットします。
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログの読み方について詳しくは、「[自動ユーザー アカウント プロビジョニングについてのレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)」をご覧ください。

### トラブルシューティングのヒント

- Docusign でユーザーのロールまたはアクセス許可プロファイルをプロビジョニングするには、属性マッピングで [switch](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#switch) と [singleAppRoleAssignment](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#singleapproleassignment) 関数を使用する式を使用します。 たとえば、以下の式では、Microsoft Entra ID でユーザーに "DS Admin" ロールが割り当てられている場合、ID "8032066" がプロビジョニングされます。 ユーザーに Microsoft Entra ID 側のロールが割り当てられていない場合、アクセス許可プロファイルはプロビジョニングされません。 DocuSign [ポータル](https://support.docusign.com/)から ID を取得できます。

スイッチ(シングルアプリ役割の割り当て([アプリ役割割り当て]), " ", "DS 管理者", "8032066")
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/docusign-tutorial"} -->
## Microsoft Entra ID で DocuSign for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/docusign-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と DocuSign の間のシングル サインオン (SSO) を構成する方法について説明します。

この記事では、DocuSign と Microsoft Entra ID を統合する方法について説明します。 DocuSign を Microsoft Entra ID と統合すると、次のことが可能になります。

- DocuSign にアクセスできるユーザーを Microsoft Entra ID を使用して制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して DocuSign に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

DocuSign は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- シングル サインオン (SSO) が有効な DocuSign サブスクリプション。
- ドメイン DNS を制御します。 これは、DocuSign でドメインを要求するために必要です。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストし、次のことを確認します。

- DocuSign では、サービス プロバイダー **SP** によって開始される SSO がサポートされます。
- DocuSign では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。
- DocuSign では、 [自動ユーザー プロビジョニングが](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/docusign-provisioning-tutorial)サポートされています。

### ギャラリーからの DocuSign の追加

Microsoft Entra ID への DocuSign の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に DocuSign を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックスに **「DocuSign** 」と入力します。
4. 結果パネルから **DocuSign** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### DocuSign 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、DocuSign に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと DocuSign の対応するユーザーとの間にリンク関係を確立する必要があります。

DocuSign に対して Microsoft Entra SSO を構成してテストするには、以下の手順に従います。

1. ユーザーがこの機能を使用できるように、Microsoft Entra SSO を構成します。
    1. B.Simon で Microsoft Entra のシングル サインオンをテストする Microsoft Entra テスト ユーザーを作成します。
    2. B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます。
2. DocuSign SSO を構成して、アプリケーション側でシングル サインオン設定を構成します。
    1. DocuSign テスト ユーザーを作成 し、そのユーザーの Microsoft Entra 表現にリンクされた B.Simon に対応するユーザーを DocuSign で生成します。
3. SSO をテスト して、構成が機能することを確認します。

### Microsoft Entra SSO の構成

Azure portal で Microsoft Entra SSO を有効にするには、これらの手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**DocuSign** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、**シングル サインオン**を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。

    `https://<subdomain>.docusign.com/organizations/<OrganizationID>/saml2`

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | 生産： |
    | `https://<subdomain>.docusign.com/organizations/<OrganizationID>/saml2/login/<IDPID>` |
    | `https://<subdomain>.docusign.net/SAML/` |
    | QA インスタンス: |
    | `https://<SUBDOMAIN>.docusign.com/organizations/saml2` |

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。

    `https://<subdomain>.docusign.com/organizations/<OrganizationID>/saml2/login/sp/<IDPID>`

    注

    かっこで囲まれたこれらの値はプレースホルダーです。 実際の 識別子、Reply URL、Sign on URLの値に置き換えてください。 これらの値を Docusign サポート チームに連絡してください。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を見つけます。 [ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **DocuSign のセットアップ** ] セクションで、要件に基づいて適切な URL (または URL) をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### DocuSign の SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として DocuSign 企業サイトにサインインします。
2. ページの左上隅で、アプリ起動ツール (9 ドット) を選択し、[管理者] を選択 **します**。

    [Image: [プロファイル] の [管理者に移動] のスクリーンショット。]
3. ドメイン ソリューション ページで、[ドメイン] を選択 **します**。

    [Image: Select_Domainsのスクリーンショット。]
4. **[ドメイン]** セクションで、**[ドメインを要求する]** を選択します。

    [Image: Claim_domainのスクリーンショット。]
5. [ **ドメインの要求** ] ダイアログ ボックスの [ **ドメイン名** ] ボックスに会社のドメインを入力し、[ **要求**] を選択します。 ドメインを確認し、その状態がアクティブであることを確かめてください。

    [Image: [ドメイン/ドメイン名の要求] ダイアログのスクリーンショット。]
6. [ドメイン] セクション **で** 、要求の一覧に追加された新しいドメインの **検証トークンの取得** を選択します。

    [Image: pending_Identity_providerのスクリーンショット。]
7. **TXT トークン**をコピーする

    [Image: TXT_tokenのスクリーンショット。]
8. 次の手順に従って、 **TXT トークン** を使用して DNS プロバイダーを構成します。

    a. ドメインの DNS レコードの管理ページに移動します。

    b。 新しい TXT レコードを追加します。

    c. 名前: @ または \*。

    d. テキスト: 前の手順でコピーした **TXT トークン** の値を貼り付けます。

    e. TTL: 既定または 1 時間 /3,600 秒。
9. 左側のナビゲーションで、**ACCESS MANAGEMENT** で **[ID プロバイダー] を**選択します。

    [Image: ID プロバイダー オプションのスクリーンショット。]
10. [ **ID プロバイダー** ] セクションで、[ **ID プロバイダーの追加] を選択します**。

    [Image: [Add Identity Provider](ID プロバイダーの追加) オプションのスクリーンショット。]
11. [ **ID プロバイダーの設定]** ページで、次の手順に従います。

    a. [ **カスタム名** ] ボックスに、構成の一意の名前を入力します。 スペースは使用しないでください。

    [Image: name_Identity_providerのスクリーンショット。]

    b。 [ **ID プロバイダー発行者] ボックス**に、コピーした **Microsoft Entra 識別子** の値を貼り付けます。

    [Image: urls_Identity_providerのスクリーンショット。]

    c. [ **ID プロバイダーのログイン URL** ] ボックスに、Azure portal からコピーした **ログイン URL** の値を貼り付けます。

    d. [ **ID プロバイダーのログアウト URL** ] ボックスに、Azure portal からコピーした **ログアウト URL** の値を貼り付けます。

    [Image: settings_Identity_providerのスクリーンショット。]

    e. **AuthN 要求の送信方法**として**POST**を選択します。

    f. [ **ログアウト要求の送信方法] で**、[GET] を選択 **します**。

    g. [ **カスタム属性マッピング** ] セクションで、[ **新しいマッピングの追加**] を選択します。

    [Image: カスタム属性マッピング UI のスクリーンショット。]

    h. Microsoft Entra 要求にマッピングするフィールドを選択します。 この例では、 **emailaddress** 要求は `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress` の値にマップされます。 これは、Microsoft Entra ID の電子メール要求の既定の要求名です。 **[保存] を選択します**。

    [Image: カスタム属性マッピング フィールドのスクリーンショット。]

    注

    適切な **ユーザー識別子** を使用して、Microsoft Entra ID から DocuSign ユーザー マッピングにユーザーをマップします。 適切なフィールドを選択し、組織の設定に基づく適切な値を入力してください。 カスタム属性マッピング設定は必須ではありません。

    一. [ **ID プロバイダー証明書** ] セクションで、[ **証明書の追加**] を選択し、Azure portal からダウンロードした証明書をアップロードして、[保存] を選択 **します**。

    [Image: ID プロバイダー証明書/証明書の追加のスクリーンショット。]

    j. [ **ID プロバイダー** ] セクションで、[ **アクション]** を選択し、[エンドポイント] を選択 **します**。

    [Image: ID プロバイダー/エンドポイントのスクリーンショット。]

    k. DocuSign 管理ポータルの [ **View SAML 2.0 Endpoints]\(SAML 2.0 エンドポイントの表示** \) セクションで、次の手順に従います。

    [Image: SAML 2.0 エンドポイントの表示のスクリーンショット。]

    1. **サービス プロバイダー発行者 URL を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子**] ボックスに貼り付けます。
    2. **サービス プロバイダー アサーション コンシューマー サービス URL を**コピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。
    3. **サービス プロバイダーのログイン URL を**コピーし、[**基本的な SAML 構成**] セクションの **[サインオン URL**] ボックスに貼り付けます。 **サービス プロバイダーのログイン URL** の末尾に IDPID 値が表示されます。
    4. [ **閉じる]** を選択します。

#### DocuSign のテスト ユーザーの作成

このセクションでは、B. Simon というユーザーを DocuSign に作成します。 DocuSign では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 DocuSign にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [DocuSign サポート チーム](https://support.docusign.com/)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる DocuSign サインオン URL にリダイレクトされます。
- DocuSign のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [DocuSign] タイルを選択すると、SSO を設定した DocuSign に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dojonavi-tutorial"} -->
## Microsoft Entra ID で DojoNavi for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dojonavi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と DojoNavi 間のシングル サインオンを構成する方法について説明します。

この記事では、DojoNavi と Microsoft Entra ID を統合する方法について説明します。 「DojoNavi」は、企業内のさまざまなシステム運用に大きく貢献し、システム運用の大幅な効率化と運用効率の向上を実現する次世代のマニュアル ソリューションです。これまで存在しなかった、システム運用における「ナビゲーション機能」や「ブロッキング機能」を提供し、システム運用の大幅な効率化とシステム運用コストの大幅な削減を実現します。 DojoNavi を Microsoft Entra ID と統合すると、次のことが可能になります。

- DojoNavi にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで DojoNavi に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

DojoNavi に対する Microsoft Entra シングル サインオンをテスト環境で構成・テストします。 DojoNavi では、 **SP** と **IDP** によって開始されるシングル サインオンがサポートされます。

### [前提条件]

DojoNavi を Microsoft Entra ID と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- DojoNavi のシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから DojoNavi アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから DojoNavi を追加する

Microsoft Entra アプリケーション ギャラリーから DojoNavi を追加して、DojoNavi に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**DojoNavi**&gt;**シングルサインオン**に進みます。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<SUBDOMAIN>.dojo-navi.com/external_sso_service/metadata/` |
    | `https://<SUBDOMAIN>.dojo-sero.tepss.com/external_sso_service/metadata/` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<SUBDOMAIN>.dojo-navi.com/external_sso_service/acs/` |
    | `https://<SUBDOMAIN>.dojo-sero.tepss.com/external_sso_service/acs/` |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<SUBDOMAIN>.dojo-navi.com/external_sso_service/sso/` |
    | `https://<SUBDOMAIN>.dojo-sero.tepss.com/external_sso_service/sso/` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、DojoNavi クライアント サポート チーム](mailto:product_support@tenda.co.jp) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **DojoNavi のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### DojoNavi SSO の構成

**DojoNavi** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [DojoNavi サポート チーム](mailto:product_support@tenda.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### DojoNavi テスト ユーザーの作成

このセクションでは、DojoNavi で Britta Simon というユーザーを作成します。 [DojoNavi サポート チーム](mailto:product_support@tenda.co.jp)と協力して、DojoNavi プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる DojoNavi のサインオン URL にリダイレクトされます。
- DojoNavi のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した DojoNavi に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [DojoNavi] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した DojoNavi に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dojowm-tutorial"} -->
## Microsoft Entra ID で DojoWM for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dojowm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と DojoWM の間でシングル サインオンを構成する方法について説明します。

この記事では、DojoWM と Microsoft Entra ID を統合する方法について説明します。 DojoWM と Microsoft Entra ID を統合すると、次のことができます。

- DojoWM にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して DojoWM に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- DojoWM でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- DojoWM では、**SP 主導の SSO と IDP 主導の SSO** の両方をサポートしています。

### ギャラリーから DojoWM を追加する

Microsoft Entra ID への DojoWM の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に DojoWM を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加] セクションで** 、検索ボックスに **「DojoWM** 」と入力します。
4. 結果パネルから **DojoWM** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### DojoWM の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、DojoWM に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと DojoWM の関連ユーザーとの間にリンク関係を確立する必要があります。

DojoWM に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **DojoWM SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **DojoWM のテストユーザー作成 - Microsoft Entra ID の B.Simon に対応するユーザーを DojoWM に作成し、リンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**DojoWM**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.dojo-wm.tepss.com/external_sso_service/metadata/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.dojo-wm.tepss.com/external_sso_service/acs/`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.dojo-wm.tepss.com/external_sso_service/sso/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [DojoWM サポート チーム](mailto:support_dojowm@tenda.co.jp) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **DojoWM のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### DojoWM SSO の構成

**DojoWM** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と Microsoft Entra 管理センターからコピーした適切な URL を [DojoWM サポート チーム](mailto:support_dojowm@tenda.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### DojoWM テスト ユーザーの作成

このセクションでは、DojoWM で B.Simon というユーザーを作成します。 [DojoWM サポート チーム](mailto:support_dojowm@tenda.co.jp)と協力して、DojoWM プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる DojoWM サインオン URL にリダイレクトします。
- DojoWM のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した DojoWM に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [DojoWM] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した DojoWM に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dome9arc-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Check Point CloudGuard Posture Management を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dome9arc-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Check Point CloudGuard Posture Management の間でシングル サインオンを構成する方法について説明します。

この記事では、Check Point CloudGuard Posture Management と Microsoft Entra ID を統合する方法について説明します。 Check Point CloudGuard Posture Management と Microsoft Entra ID を統合すると、次のことが可能になります:

- Check Point CloudGuard Posture Management にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Check Point CloudGuard Posture Management に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Check Point CloudGuard Posture Management でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Check Point CloudGuard Posture Management では、**SPによるSSO**と**IDPによるSSO**がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Check Point CloudGuard Posture Management の追加

Microsoft Entra ID への Check Point CloudGuard Posture Management の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Check Point CloudGuard Posture Management を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Check Point CloudGuard Posture Management**」と入力します。
4. 結果パネルから **Check Point CloudGuard Posture Management** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Check Point CloudGuard Posture Management の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Check Point CloudGuard Posture Management に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Check Point CloudGuard Posture Management の関連ユーザーとの間にリンク関係を確立する必要があります。

Check Point CloudGuard Posture Management を使用して Microsoft Entra SSO を構成してテストするには、次のステップを実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Check Point CloudGuard Posture Management の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Check Point CloudGuard Posture Management のテストユーザーを作成 - Check Point CloudGuard Posture Management** で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Check Point CloudGuard Posture Management]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://secure.dome9.com/sso/saml/<YOURCOMPANYNAME>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://secure.dome9.com/sso/saml/<YOURCOMPANYNAME>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 `<company name>`値は、「**Check Point CloudGuard Posture Management SSO の構成**」セクションから取得します。これについては、後で説明します。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Check Point CloudGuard Posture Management アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
8. その他に、Check Point CloudGuard Posture Management アプリケーションでは、以下に示したいくつかの属性が SAML 応答で返されることが想定されています。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | memberof | user.assignedroles |

    注

    Microsoft Entra ID でロールを作成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Check Point CloudGuard Posture Management のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Check Point CloudGuard Posture Management の SSO を構成する

1. 別の Web ブラウザー ウィンドウで、Check Point CloudGuard Posture Management 企業サイトに管理者としてサインインします
2. 右上隅にある **プロファイル設定** を選択し、[ **アカウント設定]** を選択します。

    [Image: [アカウント設定] が選択されている [プロファイル設定] メニューを示すスクリーンショット。]
3. **SSO** に移動し、[**ENABLE]** を選択します。

    [Image: [S S O] タブと [有効] が選択されていることを示すスクリーンショット。]
4. [SSO 構成] セクションで、次の手順を実行します。

    [Image: Check Point CloudGuard Posture Management の構成]

    a. **[アカウント ID**] ボックスに会社名を入力します。 この値は、Azure portal の [**基本的な SAML 構成**] セクションに記載されている**応答**と**サインオン**の URL で使用されます。

    b。 **[発行者**] ボックスに、Azure portal からコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    c. **[Idp エンドポイント URL**] ボックスに、Azure portal からコピーした**ログイン URL** の値を貼り付けます。

    d. ダウンロードした Base64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、 **X.509 証明書** のテキスト ボックスに貼り付けます。

    e. **[保存] を選択します**。

#### Check Point CloudGuard Posture Management のテスト ユーザーを作成する

Microsoft Entra ユーザーが Check Point CloudGuard Posture Management にサインインできるようにするには、そのユーザーをアプリケーションにプロビジョニングする必要があります。 Check Point CloudGuard Posture Management では Just-In-Time プロビジョニングがサポートされていますが、正しく機能するためには、ユーザーが特定のロールを選択し、同じ **ロール** をユーザーに割り当てる必要があります。

注

**ロール**を作成する方法とその他の情報については、[CloudGuard 管理者ガイド](https://blog.checkpoint.com/securing-the-cloud/how-to-use-compliance-engine-pci-dome9)を参照してください。

24 時間 365 日サポートについては、 [Check Point サポート](https://www.checkpoint.com/support-services/contact-support/)にお問い合わせください。

**ユーザー アカウントを手動でプロビジョニングするには、次の手順に従います。**

1. Check Point CloudGuard Posture Management 企業サイトに管理者としてサインインします。
2. [ **ユーザーとロール** ] を選択し、**[ユーザー] を選択します**。

    [Image: [ユーザー] アクションが選択されている [Users & Roles](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーとロール) を示すスクリーンショット。]
3. [ **ユーザーの追加] を選択します**。

    [Image: [ユーザーの追加] ボタンが選択された [ユーザーとロール] を示すスクリーンショット。]
4. [ **ユーザーの作成** ] セクションで、次の手順を実行します。

    [Image: 従業員の追加]

    a. [ **電子メール** ] ボックスに、ユーザーの電子メール ( B.Simon@contoso.comなど) を入力します。

    b。 **名** テキストボックスにユーザーの名を入力します。例：B。

    c. [ **姓]** ボックスに、ユーザーの姓 (Simon など) を入力します。

    d. **SSO ユーザーを** **オンにします**。

    e. **作成**を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Check Point CloudGuard Posture Management のサインオン URL にリダイレクトされます。
- Check Point CloudGuard Posture Management のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Check Point CloudGuard Posture Management に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Check Point CloudGuard Posture Management] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Check Point CloudGuard Posture Management に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dominknowone-tutorial"} -->
## Microsoft Entra ID でのシングルサインオンのために dominKnow - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dominknowone-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と dominKnow|ONE の間のシングル サインオンを構成する方法について説明します。

この記事では、dominKnow|ONE を Microsoft Entra ID と統合する方法について説明します。 dominKnow|ONE を Microsoft Entra ID と統合すると、次のことが可能になります。

- dominKnow|ONE にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで dominKnow|ONE に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な dominKnow|ONE のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- dominKnow|ONE では、**SP** によって開始される SSO がサポートされます。

### ギャラリーから dominKnow|ONE を追加する

dominKnow|ONE と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に dominKnow|ONE をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**dominKnow|ONE**」と入力します。
4. 結果のパネルから **[dominKnow|ONE]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### dominKnow|ONE 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、dominKnow|ONE 用に Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと、dominKnow|ONE での関連ユーザーとの間にリンク関係を確立する必要があります。

dominKnow|ONE 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **dominKnowONE の SSO を構成する**- アプリケーション側のシングル サインオン設定を構成します。
    1. **dominKnowONE のテスト ユーザーを作成する** - dominKnow|ONE で B.Simon に対応するユーザーを作成し、Microsoft Entra での当該ユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[dominKnow|ONE]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<customer>.authr.it/SAML/dominKnowONE<customer>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<customer>.authr.it`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer>.authr.it`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[dominKnow|ONE サポート チーム](mailto:support@dominknow.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. dominKnow|ONE アプリケーションでは、特定の形式の SAML アサーションが使用されるため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、dominKnow|ONE では、これをユーザーのメール アドレスにマップすることが求められます。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[dominKnow|ONE のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### dominKnowONE SSO の構成

**dominKnow|ONE** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からからコピーした適切な URL を [dominKnow|ONE サポート チーム](mailto:support@dominknow.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### dominKnowONE のテスト ユーザーの作成

このセクションでは、dominKnow|ONE で Britta Simon というユーザーを作成します。 [dominKnow|ONE サポート チーム](mailto:support@dominknow.com)と協力して、dominKnow|ONE プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションは dominKnow にリダイレクトされます。ログイン フローを開始できる ONE サインオン URL。
- dominKnow|ONE のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイアプリで dominKnow|ONE のタイルを選択すると、このオプションは dominKnow|ONE のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/domo-tutorial"} -->
## Microsoft Entra ID で Domo for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/domo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Domo 間のシングル サインオンを構成する方法について説明します。

この記事では、Domo と Microsoft Entra ID を統合する方法について説明します。 Domo を Microsoft Entra ID と統合すると、次のことが可能になります。

- Domo にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Domo に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Domo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Domo では、**SP** によって開始される SSO がサポートされます。
- Domo では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Domo を追加する

Microsoft Entra ID への Domo の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Domo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Domo**」と入力します。
4. 結果のパネルから **[Domo]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Domo に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、Domo に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Domo の関連ユーザーとの間にリンク関係を確立する必要があります。

Domo に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Domo SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Domo テスト ユーザーの作成** - Domo で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Domo**&gt;**シングルサインオン**にアクセスしてください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.domo.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://<companyname>.domo.com
    https://<companyname>.beta.domo.com
    https://<companyname>.demo.domo.com
    https://<companyname>.dev.domo.com
    https://<companyname>.fastage1.domo.com
    https://<companyname>.frdev.domo.com
    https://<companyname>.gastage.domo.com
    https://<companyname>.load.domo.com
    https://<companyname>.local.domo.com
    https://<companyname>.qa.domo.com
    https://<companyname>.stage.domo.com
    ```

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 この値を取得するには、[Domo クライアント サポート チーム](mailto:support@domo.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Domo のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Domo SSO の構成

**デモ**側でシングル サインオンを構成するには、[こちら](https://knowledge.domo.com?cid=azuread)からデモのサポート技術情報の記事に移動し、指示に従います。

#### Domo テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Domo に作成します。 Domo では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Domo にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Domo のサインオン URL にリダイレクトされます。
- Domo のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Domo] タイルを選択すると、このオプションは Domo のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dossier-tutorial"} -->
## Microsoft Entra ID で Dossier for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dossier-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Dossier の間のシングル サインオンを構成する方法について説明します。

この記事では、Dossier と Microsoft Entra ID を統合する方法について説明します。 Dossier を Microsoft Entra ID と統合すると、次のことが可能になります。

- Dossier にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Dossier に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

Dossier と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Dossier でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Dossier では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの Dossier の追加

Dossier と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Dossier をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Dossier**」と入力します。
4. 結果のパネルから **[Dossier** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Dossier 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Dossier に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Dossier での関連ユーザーとの間にリンク関係を確立する必要があります。

Dossier 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Dossier SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Dossier テスト ユーザーの作成** - Dossier でユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせ、B.Simon に対応させます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Dossier]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `Dossier/<CLIENTNAME>`

    注

    識別子の値は、`Dossier/<CLIENTNAME>` の形式かユーザー個人用に設定された値である必要があります。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<SUBDOMAIN>.dossiersystems.com/azuresso` |
    | `https://dossier.<CLIENTDOMAINNAME>/azuresso` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<SUBDOMAIN>.dossiersystems.com/azuresso/account/SignIn` |
    | `https://dossier.<CLIENTDOMAINNAME>/azuresso/account/SignIn` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Dossier クライアント サポート チーム](mailto:support@intellimedia.ca) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、コピー ボタンを選択して、要件に従って指定されたオプションから **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Dossier のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 設定を適切な U R L にコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Dossier SSO の構成

**Dossier** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Dossier サポート チーム](mailto:support@intellimedia.ca)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Dossier のテスト ユーザーを作成する

このセクションでは、Dossier で Britta Simon というユーザーを作成します。 [Dossier サポート チーム](mailto:support@intellimedia.ca)と協力して、Dossier プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Dossier のサインオン URL にリダイレクトされます。
- Dossier のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Dossier] タイルを選択すると、このオプションは Dossier のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dotcom-monitor-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Dotcom-Monitor を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dotcom-monitor-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Dotcom-Monitor 間のシングル サインオンを構成する方法について説明します。

この記事では、Dotcom-Monitor と Microsoft Entra ID を統合する方法について説明します。 Dotcom-Monitor を Microsoft Entra ID と統合すると、次のことが可能になります。

- Dotcom-Monitor にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Dotcom-Monitor に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Dotcom-Monitor でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Dotcom-Monitor では、 **SP** Initiated SSO がサポートされます
- Dotcom-Monitor では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Dotcom-Monitor の追加

Dotcom-Monitor と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に Dotcom-Monitor をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Dotcom-Monitor**」と入力します。
4. 結果パネルから **Dotcom-Monitor** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Dotcom-Monitor 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Dotcom-Monitor に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Dotcom-Monitor での関連ユーザーとの間にリンク関係を確立する必要があります。

Dotcom-Monitor 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Dotcom Monitor の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Dotcom-Monitor のテスト ユーザーを作成する** - Dotcom-Monitor で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Dotcom-Monitor**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://userauth.dotcom-monitor.com/Login.ashx?cidp=<CUSTOM_GUID>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 値を取得するには、[Dotcom-Monitor クライアント サポート チーム](mailto:vadimm@dana-net.com)に連絡してください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Dotcom-Monitor アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Dotcom-Monitor アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 役割 | user.assignedroles |

    注

    Microsoft Entra ID でカスタム ロールを作成する方法の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Dotcom-Monitor のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Dotcom-Monitor の SSO の構成

**Dotcom-Monitor** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を[サポート チームDotcom-Monitor](mailto:vadimm@dana-net.com) 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Dotcom-Monitor のテスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Dotcom-Monitor に作成します。 Dotcom-Monitor では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Dotcom-Monitor にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプション Dotcom-Monitor ログイン フローを開始できるサインオン URL にリダイレクトされます。
- Dotcom-Monitor のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Dotcom-Monitor] タイルを選択すると、このオプションは Dotcom-Monitor サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dovetale-tutorial"} -->
## Microsoft Entra ID で Dovetale for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dovetale-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Dovetale の間のシングル サインオンを構成する方法について説明します。

この記事では、Dovetale と Microsoft Entra ID を統合する方法について説明します。 Dovetale を Microsoft Entra ID と統合すると、次のことが可能になります。

- Dovetale にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Dovetale に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Dovetale でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Dovetale では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Dovetale では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Dovetale の追加

Microsoft Entra ID への Dovetale の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Dovetale を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Dovetale**」と入力します。
4. 結果のパネルから **[Dovetale]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Dovetale に Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、Dovetale に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Dovetale の関連ユーザー間にリンク関係を確立する必要があります。

Dovetale との Microsoft Entra SSO を構成・テストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Dovetale の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Dovetale テスト ユーザーの作成 - B.Simon に対応するユーザーを Dovetale に作成し、Microsoft Entra にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Dovetale]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `<COMPANYNAME>.dovetale.com`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[Dovetale クライアント サポート チーム](mailto:support@dovetale.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Dovetale アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Dovetale アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | first\_name | User.givenname |
    | 名前 | user.userprincipalname |
    | last\_name | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Dovetale の SSO の構成

**Dovetale** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Dovetale サポート チーム](mailto:support@dovetale.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Dovetale テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Dovetale に作成します。 Dovetale では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Dovetale にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Dovetale サポート チーム](mailto:support@dovetale.com)にお問い合わせください。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Dovetale] タイルを選択すると、SSO を設定した Dovetale に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dowjones-factiva-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Dow Jones Factiva を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dowjones-factiva-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Dow Jones Factiva の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、Dow Jones Factiva と Microsoft Entra ID を統合する方法について説明します。 Dow Jones Factiva と Microsoft Entra ID の統合には、次の利点があります。

- Dow Jones Factiva にアクセスできる Microsoft Entra ID ユーザーを制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Dow Jones Factiva に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Dow Jones Factiva でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Dow Jones Factiva は、**IDP** Initiated SSO をサポートしています

### ギャラリーからの Dow Jones Factiva の追加

Microsoft Entra ID への Dow Jones Factiva の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Dow Jones Factiva を追加する必要があります。

**ギャラリーから Dow Jones Factiva を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **Dow Jones Factiva**」と入力し、結果パネルで **Dow Jones Factiva** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: Dow Jones Factiva が結果一覧に]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Dow Jones Factiva で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Dow Jones Factiva 内の関連ユーザー間にリンク関係が確立されている必要があります。

Dow Jones Factiva で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Dow Jones Factiva シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Dow Jones Factiva テストユーザーの作成** - Britta Simon に対応するユーザーを Dow Jones Factiva に作成し、Microsoft Entra におけるユーザーの表現とリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Dow Jones Factiva で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Dow Jones Factiva** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。

    [Image: Dow Jones Factiva ドメインとURLのシングルサインオン情報]
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Dow Jones Factiva のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    ある。 ログイン URL

    b。 Microsoft Entra アイデンティファイヤー

    c. ログアウト URL

#### Dow Jones Factiva シングル サインオンの構成

**Dow Jones Factiva** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Dow Jones Factiva サポート チーム](https://www.dowjones.com/contact/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Dow Jones Factiva のテスト ユーザーの作成

このセクションでは、Dow Jones Factiva で Britta Simon というユーザーを作成します。 [Dow Jones Factiva サポート チーム](https://www.dowjones.com/contact/)と協力して、Dow Jones Factiva プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Dow Jones Factiva] タイルを選択すると、SSO を設定した Dow Jones Factiva に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dozuki-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Dozuki を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dozuki-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Dozuki の間のシングル サインオンを構成する方法について説明します。

この記事では、Dozuki を Microsoft Entra ID と統合する方法について説明します。 Dozukiは、継続的な改善とトレーニングの取り組みをサポートするために標準化された手順を製造業者が実装できるようにする標準的な作業指示ソフトウェアです。 Dozuki を Microsoft Entra ID と統合すると、次のことが可能になります。

- Dozuki にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Dozuki に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Dozuki 向けの Microsoft Entra のシングル サインオンを構成してテストする。 Dozuki は、**SP** および **IDP** Initiated の両方のシングル サインオンと、**Just In Time** ユーザー プロビジョニングをサポートします。

### [前提条件]

Microsoft Entra ID を Dozuki と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Dozuki のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Dozuki アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Dozuki を追加する

Microsoft Entra アプリケーション ギャラリーから Dozuki を追加して、Dozuki でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Dozuki]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<dozukiSubdomain>.dozuki.com/`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<dozukiSubdomain>.dozuki.com/Guide/User/remote_login`
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<dozukiSubdomain>.dozuki.com/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Dozuki のクライアント サポート チーム](mailto:support@dozuki.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Dozuki アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. 上記に加えて、Dozuki アプリケーションは、さらにいくつかの属性が SAML 応答で返されると想定しています。それらを次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ユーザーID | user.objectid (ユーザーのオブジェクトID) |
    | ユーザー名 | user.displayname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Dozuki のセットアップ]** セクションで、要件に基づいて該当の URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Dozuki の SSO を構成する

**Dozuki** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした該当の URL を [Dozuki のサポート チーム](mailto:support@dozuki.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Dozuki のテスト ユーザーを作成する

このセクションでは、Dozuki で B. Simon というユーザーを作成します。 Dozuki は、Just-In-Time ユーザー プロビジョニングをサポートしています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Dozuki にユーザーがまだ存在していない場合、一般に認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Dozuki のサインオン URL にリダイレクトされます。
- Dozuki のサインオン URL に直接アクセスし、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Dozuki に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Dozuki タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Dozuki に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/draup-inc-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Draup, Inc を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/draup-inc-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Draup, Inc の間のシングル サインオンを構成する方法について説明します。

この記事では、Draup, Inc と Microsoft Entra ID を統合する方法について説明します。 Draup, Inc を Microsoft Entra ID と統合すると、次のことが可能になります。

- Draup, Inc にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Draup, Inc に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Draup, Inc でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Draup, Inc では、**SP** Initiated SSO がサポートされます。
- Draup, Inc では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Draup, Inc を追加する

Microsoft Entra ID への Draup, Inc の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Draup, Inc を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Draup, Inc**」と入力します。
4. 結果のパネルから **[Draup, Inc]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Draup, Inc に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、Draup, Inc に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Draup, Inc の関連ユーザーとの間にリンク関係を確立する必要があります。

Draup, Inc に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Draup, Inc の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Draup, Inc のテスト ユーザーの作成** - Draup, Inc で B.Simon に対応するユーザーを作成し、Microsoft Entra の B. Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Draup, Inc**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** ページで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://platform.draup.com/saml2/login/`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Draup, Inc の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Draup, Inc の SSO の構成

**Draup, Inc** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** とアプリケーション構成からコピーした適切な URL を [Draup, Inc サポート チーム](mailto:support@draup.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Draup, Inc テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Draup, Inc に作成します。Draup, Inc は、Just-In-Time プロビジョニング (既定で有効) をサポートしています。 このセクションにはアクション項目はありません。 ユーザーがまだ Draup, Inc に存在しない場合は、Draup, Inc にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Draup, Inc のサインオン URL にリダイレクトされます。
- Draup, Inc のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Draup, Inc] タイルを選択すると、このオプションは Draup, Inc のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/drawboard-projects-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Drawboard Projects を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/drawboard-projects-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Drawboard Projects 間のシングル サインオンを構成する方法について説明します。

この記事では、Drawboard Projects と Microsoft Entra ID を統合する方法について説明します。 Drawboard Projects のアーキテクチャ、エンジニアリング、建設チームは、設計レビューのライフサイクルで貴重なプロジェクト時間を大域的に節約します。 Drawboard Projects を Microsoft Entra ID と統合すると、次のことが可能になります。

- Drawboard Projects にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Drawboard Projects に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Drawboard Projects に対する Microsoft Entra シングル サインオンをテスト環境で構成・テストします。 Drawboard Projects は、**SP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングの両方をサポートしています。

### [前提条件]

Drawboard Projects を Microsoft Entra ID と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Drawboard Projects でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Drawboard Projects アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Drawboard Projects を追加する

Microsoft Entra アプリケーション ギャラリーから Drawboard Projects を追加して、Drawboard Projects に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Drawboard Projects]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`urn:auth0:bullclip:<CUSTOMERCONNECTIONNAME>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://id.drawboard.com/login/callback?connection=<CUSTOMERCONNECTIONNAME>`

    c. [ **サインオン URL** ] ボックスに、URL を入力します。 `https://projects.drawboard.com`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 [Drawboard Projects クライアント サポート チーム](mailto:support@drawboard.com)に問い合わせて、これらの値を取得します。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Drawboard Projects のセットアップ]** セクションで、実際の要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

### Drawboard Projects の SSO を構成する

**Drawboard Projects** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Drawboard Projects サポート チーム](mailto:support@drawboard.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Drawboard Projects のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Drawboard Projects に作成します。 Drawboard Projects では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Drawboard Projects にユーザーがまだ存在していない場合は、認証後に新規作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Drawboard Projects のサインオン URL にリダイレクトされます。
- Drawboard Projects のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Drawboard Projects] タイルを選択すると、このオプションは Drawboard Projects のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/drift-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Drift を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/drift-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Drift 間のシングル サインオンを構成する方法について説明します。

この記事では、Drift と Microsoft Entra ID を統合する方法について説明します。 Drift を Microsoft Entra ID と統合すると、次のことが可能になります。

- Drift にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Drift に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Drift サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Drift では、**SP および IDP** Initiated SSO がサポートされます。
- Drift では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Drift を追加する

Microsoft Entra ID への Drift の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Drift を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Drift**」と入力します。
4. 結果パネルから **[Drift** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Drift に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、Drift に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Drift の関連ユーザーとの間にリンク関係を確立する必要があります。

Drift に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Drift SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Drift テスト ユーザーの作成** - Drift で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Drift**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。

    a. [ **追加の URL の設定] を選択します**。

    b。 [ **リレー状態** ] テキスト ボックスに、URL を入力します。 `https://app.drift.com`
6. SP 開始モードでアプリケーションを構成する場合は、次の手順を実行し、[追加の URL の設定] を選択します。

    a. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://start.drift.com`
7. Drift アプリケーションでは、特定の形式の SAML アサーションを使用するため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Drift アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名前 | user.displayname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Drift のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Drift の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Drift 企業サイトに管理者としてサインインします。
2. メニュー バーの左側にある **[設定] アイコン**&gt;&gt;] を選択し、次の手順を実行します。

    [Image: 管理者リンク]

    a. ダウンロードした **フェデレーション メタデータ XML** **を [Upload Identity Provider metadata file]\(ID プロバイダー メタデータ ファイルのアップロード** \) テキスト ボックスにアップロードします。

    b。 メタデータをアップロードした後、残りの値は自動的にページへ取得されます。

    c. [ **SAML を有効にする] を選択します**。

#### Drift テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Drift に作成します。 Drift では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Drift にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Drift サポート チーム](mailto:integrations@drift.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Drift Sign on URL にリダイレクトされます。
- Drift のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Drift に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Drift] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Drift に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/drivelock-tutorial"} -->
## Microsoft Entra ID で DriveLock for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/drivelock-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-09-10
- Summary: Microsoft Entra ID と DriveLock の間のシングル サインオンを構成する方法について説明します。

この記事では、DriveLock と Microsoft Entra ID を統合する方法について説明します。 DriveLock と Microsoft Entra ID を統合すると、次のことができます。

- DriveLock にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って DriveLock に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

Microsoft Entra ID を DriveLock と統合するには、以下が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- DriveLock でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

### ギャラリーから DriveLock を追加する

Microsoft Entra ID への DriveLock の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに DriveLock を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**DriveLock**」と入力します。
4. 結果のパネルから **[DriveLock]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

1. DriveLock アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: セットアップ ページの既定のアプリケーション属性のスクリーンショット。]
2. その他に、DriveLock アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | 名前空間 | ソース属性 |
    | --- | --- | --- |
    | [Attb1] | [Namespace1] | [value1] |
    | [Attb2] | [Namespace2] | [value2] |
    | [Attb3] | [Namespace3] | [value3] |
    | [Attb4] | [Namespace4] | [value4] |
3. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、コピー ボタンを選択して **[アプリのフェデレーション メタデータ URL]** をコピーし、コンピューターに保存します。

    [Image: セットアップ ページの SAML 署名証明書のスクリーンショット。]

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

このセクションでは、B.Simon に DriveLock へのアクセスを許可して、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**DriveLock** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、**[追加された割り当て]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. **[ユーザーとグループ]** ダイアログ ボックスの [ユーザー] の一覧で **[B.Simon]** を選択し、画面の下部にある **[選択]** ボタンを選択します。
    2. ユーザーにロールが割り当てられることが想定される場合は、**[ロールの選択]** ドロップダウンからそれを選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. **[割り当ての追加]** ダイアログで、**[割り当て]** ボタンを選択します。

### DriveLock SSO の構成

**DriveLock** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [DriveLock サポート チーム](mailto:Cloud.opsmgmt@drivelock.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### DriveLock テスト ユーザーの作成

このセクションでは、DriveLock で B.Simon というユーザーを作成します。 [DriveLock サポートチーム](mailto:Cloud.opsmgmt@drivelock.com)と協力して、DriveLock プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、マイ アプリを使って Microsoft Entra のシングル サインオン構成をテストします。

[マイ アプリ] で [DriveLock] タイルをクリックすると、SSO を設定した DriveLock に自動的にサインインします。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dropboxforbusiness-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Dropbox for Business を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dropboxforbusiness-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: ユーザー アカウントを Dropbox for Business に自動的にプロビジョニングおよびプロビジョニング解除するよう Microsoft Entra ID を構成する方法について説明します。

この記事の目的は、Dropbox for Business と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Dropbox for Business に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

重要

今後、Microsoftと Dropbox は古い Dropbox 統合を非推奨にしています。 これは当初 2021 年 4 月 1 日に予定されていましたが、無期限に延期されました。 ただし、サービスの中断を回避するには、グループをサポートする新しい SCIM 2.0 Dropbox 統合に移行することをお勧めします。 新しい Dropbox 統合に移行するには、次の手順を使用して、Microsoft Entra テナントでのプロビジョニング用に Dropbox の新しいインスタンスを追加して構成します。 新しい Dropbox 統合を構成したら、以前の Dropbox 統合でのプロビジョニングを無効にして、プロビジョニングの競合を回避します。 新しい Dropbox 統合への移行の詳細な手順については、「 [Microsoft Entra ID を使用して最新の Dropbox for Business アプリケーションに更新する」および「Microsoft Entra ID を使用して](https://help.dropbox.com/installs-integrations/third-party/update-dropbox-azure-ad-connector)[Dropbox を接続](https://help.dropbox.com/integrations/microsoft-entra-id)する」を参照してください。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Dropbox for Business テナント](https://www.dropbox.com/business/pricing)
- Admin アクセス許可がある Dropbox for Business のユーザー アカウント。

### ギャラリーから Dropbox for Business を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に Dropbox for Business を構成する前に、Dropbox for Business を Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Dropbox for Business を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーから追加**] セクションで、「**Dropbox for Business」**と入力し、検索ボックスで **Dropbox for Business** を選択します。
4. 結果パネルから **Dropbox for Business** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の Dropbox for Business のスクリーンショット。]

### Dropbox for Business へのユーザーの割り当て

Microsoft Entra ID では、 *割り当て* と呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーとグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Dropbox for Business へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定したら、次の手順に従って、これらのユーザーやグループを Dropbox for Business に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Dropbox for Business に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを Dropbox for Business に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Dropbox for Business にユーザーを割り当てるときは、割り当てダイアログで有効なアプリケーション固有ロール (使用可能な場合) を選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### Dropbox for Business への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて Dropbox for Business のユーザーやグループを作成、更新、無効化するよう、 Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Dropbox for Business のシングル サインオンに関する記事に記載されている手順に従って、 [Dropbox for Business](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dropboxforbusiness-tutorial) で SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Dropbox for Business の自動ユーザー プロビジョニングを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**に移動する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Dropbox for Business** を選択します。

    [Image: アプリケーションの一覧の [Dropbox for Business] リンクのスクリーンショット]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Dropbox テナントの URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra ID Dropbox に接続できることを確認します。 接続に失敗した場合は、Dropbox アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. **[Dropbox for Business にサインインして Microsoft Entra ID にリンクする**] ダイアログで、Dropbox for Business テナントにサインインし、ID を確認します。

    [Image: Dropbox for Business サインインのスクリーンショット。]
8. Microsoft Entra ID が Dropbox for Business に接続できることを確認するには、**Test Connection** を選択してください。 接続できない場合は、使用中の Dropbox for Business アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: トークンのスクリーンショット。]
9. [ **作成]** を選択して構成を作成します。
10. [**概要**] ページで **[プロパティ**] を選択します。
11. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
12. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
13. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
14. [属性マッピング] セクションで、Microsoft Entra ID から Dropbox に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Dropbox のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: Dropbox ユーザー属性のスクリーンショット。]
15. 左側のパネルで **[属性マッピング** ] を選択し、[グループ] を選択 **します**。
16. [属性マッピング] セクションで、Microsoft Entra ID から Dropbox に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Dropbox のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: Dropbox グループ属性のスクリーンショット。]
17. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
18. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
19. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「 [自動ユーザー アカウント プロビジョニングに関するレポート」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。

### コネクタの制限事項

- Dropbox では、招待されたユーザーの一時停止はサポートされていません。 招待されたユーザーが一時停止されている場合、そのユーザーは削除されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dropboxforbusiness-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Dropbox Business を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dropboxforbusiness-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Dropbox Business 間にシングル サインオンを構成する方法について説明します。

この記事では、Dropbox Business と Microsoft Entra ID を統合する方法について説明します。 Dropbox Business と Microsoft Entra ID を統合すると、次のことができます。

- Dropbox Business にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Dropbox Business に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

Dropbox Business は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Dropbox Business でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

- この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。 Dropbox Business では、**SP** Initiated SSO がサポートされます。
- Dropbox Business では、[自動化されたユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dropboxforbusiness-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Dropbox Business の追加

Microsoft Entra ID への Dropbox Business の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Dropbox Business を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Dropbox Business**」と入力します。
4. 結果のパネルから **[Dropbox Business]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Dropbox Business に対する Microsoft Entra SSO の構成とテスト

**Britta Simon** というテスト ユーザーを使用して、Dropbox Business で Microsoft Entra の SSO を構成し、テストします。 SSO が機能するためには、Microsoft Entra ユーザーと Dropbox Business の関連ユーザーとの間にリンク関係を確立する必要があります。

Dropbox Business に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Dropbox Business の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Dropbox Business のテストユーザーを作成する** - Dropbox BusinessでBritta Simonに対応するユーザーを作成し、そのユーザーをMicrosoft Entraの表現するユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Dropbox Business** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** ページで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.dropbox.com/sso/<id>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `Dropbox`

    c. **[応答 URL]** フィールドに、「`https://www.dropbox.com/saml_login`」と入力します

    注

    **Dropbox サイン SSO ID** は、Dropbox サイトの [Dropbox] &gt; [Admin console](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理コンソール) &gt; [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) &gt; [Single sign-on](シングル サインオン) &gt; [SSO sign-in URL](SSO サインイン URL) で確認できます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Dropbox Business のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Dropbox Business の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Dropbox Business 企業サイトに管理者としてサインインします

    [Image: [Dropbox Business Sign in](Dropbox Business サインイン) ページを示すスクリーンショット。]
2. **ユーザー アイコン**を選択し、[**設定] タブを**選択します。

    [Image: [ユーザー アイコン] アクションと [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) が選択されていることを示すスクリーンショット。]
3. 左側のナビゲーション ウィンドウで、[ **管理コンソール**] を選択します。

    [Image: [Admin console](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理コンソール) が選択されていることを示すスクリーンショット。]
4. **管理コンソール**で、左側のナビゲーション ウィンドウで **[設定]** を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) が選択されていることを示すスクリーンショット。]
5. **[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** セクションの **[Single sign-on](シングル サインオン)** オプションを選択します。

    [Image: [Single sign-on](シングル サインオン) が選択されている [Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) セクションを示すスクリーンショット。]
6. **[Single sign-on](シングル サインオン)** セクションで、次の手順を実行します。

    [Image: [Single sign-on](シングル サインオン) の構成設定を示すスクリーンショット。]

    ある。 **[Single sign-on](シングル サインオン)** のドロップ ダウンからオプションとして **[Required](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/必須)** を選択します。

    b。 [ **サインイン URL の追加] を** 選択し、[ **ID プロバイダーのサインイン URL** ] ボックスに、コピーした **ログイン URL** の値を貼り付けて、[ **完了]** を選択します。

    [Image: シングル サインオンの構成]

    c. [ **証明書のアップロード**] を選択し、ダウンロード **した Base64 でエンコードされた証明書ファイル** を参照します。

    d. **[コピー] リンク**を選択し、コピーした値を Azure portal の **Dropbox Business の [ドメインと URL]** セクションの **[サインオン URL**] ボックスに貼り付けます。

    え **保存** を選択します。

#### Dropbox Business のテスト ユーザーの作成

1. Dropbox Business Web サイトに管理者としてログインします。
2. **管理コンソール**に移動し、左側のメニューで **[メンバー**] を選択します。

    [Image: 「メンバーを招待」のスクリーンショット]
3. 有効なユーザーメールを入力してユーザーを追加し、[招待] を選択 **します**。

    [Image: 招待のスクリーンショット]

このアプリケーションでは、自動ユーザー プロビジョニングもサポートされています。 [Dropbox Business](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dropboxforbusiness-provisioning-tutorial) の自動プロビジョニングを有効にする方法をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Dropbox Business のサインオン URL にリダイレクトされます。
- Dropbox Business のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Dropbox Business] タイルを選択すると、このオプションは Dropbox Business のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/drtrack-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に DRTrack を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/drtrack-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と DRTrack の間でシングル サインオンを構成する方法について説明します。

この記事では、DRTrack と Microsoft Entra ID を統合する方法について説明します。 DRTrack と Microsoft Entra ID を統合すると、次のことができます。

- DRTrack にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して DRTrack に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- DRTrack でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- DRTrackは、**SPおよびIDPによるSSO**をサポートしています。

### ギャラリーから DRTrack を追加する

Microsoft Entra ID への DRTrack の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に DRTrack を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**新規アプリケーション**に移動する。
3. [ **ギャラリーからの追加] セクションで** 、検索ボックスに「 **DRTrack** 」と入力します。
4. 結果パネルから **DRTrack** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### DRTrack の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、DRTrack に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと DRTrack の関連ユーザーとの間にリンク関係を確立する必要があります。

DRTrack に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **DRTrack SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **DRTrack テスト ユーザーの作成** - Microsoft Entra にある B.Simon の表現にリンクさせるために、DRTrack で B.Simon の対応ユーザーを設定します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[DRTrack]**&gt;**[シングル サインオン]**の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<CustomerName>.appiangps.com` |
    | `https://<CustomerName>.routetracking.com` |
    | `https://<CustomerName>.appiantracking.com` |
    | `https://<CustomerName>.drtrack.trimblemaps.com` |
    | `https://<CustomerName>.staging.appiantesting.com` |
    | `https://<CustomerName>.qa.appiantesting` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CustomerName>.appiangps.com/AssertionConsumer.aspx` |
    | `https://<CustomerName>.routetracking.com/AssertionConsumer.aspx` |
    | `https://<CustomerName>.appiantracking.com/AssertionConsumer.aspx` |
    | `https://<CustomerName>.drtrack.trimblemaps.com/AssertionConsumer.a` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CustomerName>.appiangps.com/Login.aspx` |
    | `https://<CustomerName>.routetracking.com/Login.aspx` |
    | `https://<CustomerName>.appiantracking.com/Login.aspx` |
    | `https://<CustomerName>.drtrack.trimblemaps.com/Login.aspx` |
    | `https://<CustomerName>.staging.appiantesting` |

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [DRTrack クライアント サポート チーム](mailto:support-appian@trimblemaps.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **DRTrack のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### DRTrack SSO の構成

**DRTrack** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [DRTrack サポート チーム](mailto:support-appian@trimblemaps.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### DRTrack テスト ユーザーの作成

このセクションでは、DRTrack で Britta Simon というユーザーを作成します。 DRTrack サポート チーム  と連携して、DRTrack プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる DRTrack サインオン URL にリダイレクトされます。
- DRTrack のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した DRTrack に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで DRTrack タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した DRTrack に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/druva-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Druva を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/druva-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: ユーザー アカウントを Druva に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Druva で実行する手順と、ユーザーやグループを Druva に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するMicrosoft Entra IDを示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Druva でユーザーを作成します。
- アクセスが不要になったら、Druva のユーザーを削除します。
- Druva に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/druva-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entraユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.

- [Druva テナント](https://www.druva.com/products/pricing-plans/).
- Admin アクセス許可がある Druva のユーザー アカウント。

### Druva へのユーザーの割り当て

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Druva へのアクセスが必要なMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 特定した後、次の手順に従い、これらのユーザー、グループ、またはその両方を Druva に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Druva に割り当てる際の重要なヒント

- 1 人のMicrosoft Entra ユーザーを Druva に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- Druva にユーザーを割り当てるときは、割り当てダイアログで、有効なアプリケーション固有ロール (使用可能な場合) を選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために Druva を設定する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Druva を構成する前に、Druva で SCIM プロビジョニングを有効にする必要があります。

1. [Druva 管理コンソール](https://console.druva.com)にサインインします。 **Druva**&gt;**inSync** に移動します。

    [Image: Druva 管理コンソールのスクリーンショット。]
2. **Manage**&gt;**Deployments**&gt;**Users** に移動します。

    [Image: Druva 管理コンソールのスクリーンショット。[管理] が強調表示され、[管理] メニューが表示されます。そのメニューの [デプロイ] で、[ユーザー] が強調表示されます。]
3. **[設定]** に移動します。 [ **トークンの生成]** を選択します。

    [Image: Druva 管理コンソールのページのスクリーンショット。設定が強調表示され、[設定] タブが開きます。[トークンの生成] ボタンが強調表示されています。]
4. **認証トークン**の値をコピーします。 この値は、Druva アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: Druva 管理コンソールの [トークンの作成] ページのスクリーンショット。[トークンのコピー] というラベルの付いたリンクを使用して、認証トークンの値をコピーできます。]

### ギャラリーからの Druva の追加

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Druva を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Druva を追加する必要があります。

** Microsoft Entra アプリケーション ギャラリーから Druva を追加するには、次の手順を実行します:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加**] セクションに「**Druva**」と入力し、検索ボックスで **[Druva**] を選択します。
4. 結果パネルから **Druva** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の Druva のスクリーンショット。]

### Druva への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーやグループの割り当てに基づいて Druva のユーザーやグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Druva のシングル サインオンに関する記事で説明されている手順に従って、Druva に対して SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/druva-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra IDで Druva の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Druva**] を選択します。

    [Image: アプリケーションの一覧の [Druva] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Druva テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Druva に接続できることを確認します。 接続に失敗した場合は、Druva アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から Druva に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Druva のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: Druva ユーザー属性のスクリーンショット。]
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

    Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「 [自動ユーザー アカウント プロビジョニングに関するレポート」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。

### コネクタの制限事項

- Druva では、必須属性として **電子メール** が必要です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/druva-tutorial"} -->
## Microsoft Entra ID で Druva for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/druva-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Druva 間のシングル サインオンを構成する方法について説明します。

この記事では、Druva と Microsoft Entra ID を統合する方法について説明します。 Druva を Microsoft Entra ID と統合すると、次のことが可能になります。

- Druva にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Druva に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Druva でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Druva では、**IDP** Initiated SSO がサポートされます。
- Druva では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/druva-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Druva の追加

Microsoft Entra ID への Druva の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Druva を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Druva**」と入力します。
4. 結果パネルから **Druva** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Druva に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Druva に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと Druva の関連ユーザー間にリンク関係を確立する必要があります。

Druva に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Druva SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Druva のテストユーザーを作成して、Druva 内で B.Simon の対応するユーザーを確立し、そのユーザーを Microsoft Entra の B.Simon にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Druva**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子 (エンティティ ID)]** ボックスに、「`DCP-login`」という文字列値を入力します。

    b。 **[応答 URL (Assertion Consumer Service URL)]** ボックスに、URL として「`https://cloud.druva.com/wrsaml/consume`」を入力します。
6. **保存** を選択します。
7. Druva アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Druva アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メールアドレス | ユーザー.メール |
    | druva\_auth\_token | DCP 管理コンソールから生成された SSO トークン (引用符を除く)。 次に例を示します。X-XXXXX-XXXX-S-A-M-P-L-E+TXOXKXEXNX=. Azure によって、認証トークンの周りに引用符が自動的に追加されます。 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Druva のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Druva SSO の構成

1. 別の Web ブラウザー ウィンドウで、Druva 企業サイトに管理者としてサインインします。
2. 左上隅にある Druva ロゴを選択し、[ **Druva Cloud Settings]\(Druva クラウド設定**\) を選択します。

    [Image: 設定]
3. [ **シングル サインオン** ] タブで、[編集] を選択 **します**。

    [Image: [Edit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/編集) ボタンが選択されている [Access Settings - Single Sign-On](アクセス設定 - シングル サインオン) タブを示すスクリーンショット。]
4. **[Edit Single Sign-On Settings](シングル サインオン設定の編集)** ページで、次の手順を実行します。

    [Image: 単一 Sign-On 設定]

    1. **[ID プロバイダーのログイン URL]** ボックスに **[ログイン URL]** の値を貼り付けます。
    2. base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、 **[ID プロバイダー証明書]** テキストボックスに貼り付けます。

        注

        管理者に対してシングル サインオンを有効にするには、 **[Administrators log into Druva Cloud through SSO provider](管理者は SSO プロバイダーを介して Druva Cloud にログインする)** および **[Allow failsafe access to Druva Cloud administrators(recommended)](Druva Cloud 管理者へのフェールセーフ アクセスを許可する)** チェック ボックスをオンにします。 IdP で障害が発生した場合に DCP コンソールにアクセスする必要があるため、Druva では、 **[Failsafe for Administrators](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者のフェールセーフ)** を有効にすることを推奨しています。 また、管理者は SSO と DCP の両方のパスワードを使用して、DCP コンソールにアクセスすることもできます。
    3. **保存** を選択します。 これにより、SSO を使用して Druva Cloud Platform にアクセスできるようになります。

#### Druva のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Druva に作成します。 Druva では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Druva にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

Druva では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/druva-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Druva に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Druva] タイルを選択すると、SSO を設定した Druva に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dx-netops-portal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に DX NetOps Portal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dx-netops-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と DX NetOps Portal 間のシングル サインオンを構成する方法について説明します。

この記事では、DX NetOps ポータルと Microsoft Entra ID を統合する方法について説明します。 DX NetOps ポータルでは、従来およびソフトウェア定義の (内部および外部) ネットワーク全体で、ネットワークの可観測性、障害の相関関係によるトポロジ、および通信キャリア レベルのスケールでの根本原因分析を提供します。 X NetOps Portal を Microsoft Entra ID と統合すると、次のことができます:

- DX NetOps Portal にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して DX NetOps ポータルに自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で DX NetOps ポータル向けの Microsoft Entra のシングル サインオンを構成してテストします。 DX NetOps ポータルでは、 **IDP** によって開始されるシングル サインオンがサポートされます。

### [前提条件]

Microsoft Entra ID を DX NetOps Portal と統合するには、次が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- DX NetOps ポータルでのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから DX NetOps ポータル アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから DX NetOps Portal を追加する

Microsoft Entra アプリケーション ギャラリーから DX NetOps ポータルを追加して、DX NetOps ポータルでシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**DX NetOps Portal**&gt;**シングルサインオン**にブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `<DX NetOps Portal hostname>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<DX NetOps Portal FQDN>:<SSO port>/sso/saml2/UserAssertionService`

    c. [ **リレー状態** ] ボックスに、次のパターンを使用して URL を入力します。 `SsoProductCode=pc&SsoRedirectUrl=https://<DX NetOps Portal FQDN>:<https port>/pc/desktop/page`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、およびリレー状態 URL でこれらの値を更新します。 これらの値を取得するには [、DX NetOps Portal クライアント サポート チーム](https://support.broadcom.com/web/ecx/contact-support) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. DX NetOps ポータル アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、DX NetOps ポータルでは、これがユーザーの電子メール アドレスにマップされることを想定しています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **DX NetOps Portal のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### DX NetOps ポータルの SSO を構成する

**DX NetOps ポータル**側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [DX NetOps Portal サポート チーム](https://support.broadcom.com/web/ecx/contact-support)に送信する必要があります。 サポート チームは、コピーした URL を使用して、アプリケーションでシングル サインオンを構成します。

#### DX NetOps ポータルのテスト ユーザーを作成する

シングル サインオンをテストして使用できるようにするには、DX NetOps ポータル アプリケーションでユーザーを作成してアクティブにする必要があります。

このセクションでは、前のセクションで既に作成した Microsoft Entra ユーザーに対応する Britta Simon というユーザーを DX NetOps ポータルに作成します。 [DX NetOps Portal サポート チーム](https://support.broadcom.com/web/ecx/contact-support)と協力して、DX NetOps Portal プラットフォームにユーザーを追加します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した DX NetOps ポータルに自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [DX NetOps Portal] タイルを選択すると、SSO を設定した DX NetOps ポータルに自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dynamic-signal-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Dynamic Signal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dynamic-signal-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-22
- Summary: Dynamic Signal に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、動的シグナルと Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Dynamic Signal に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Dynamic Signal テナント](https://dynamicsignal.com/)
- 管理者アクセス許可がある Dynamic Signal のユーザー アカウント

### 手順 1: ギャラリーから動的シグナルを追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に Dynamic Signal を構成する前に、Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に Dynamic Signal を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Dynamic Signal を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、「**Dynamic Signal**」と入力し、検索ボックスで **[Dynamic Signal**] を選択します。
4. 結果パネルから **[Dynamic Signal** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

    [Image: 結果一覧の Dynamic Signal のスクリーンショット。]

### 手順 2: Dynamic Signal にユーザーを割り当てる

Microsoft Entra ID では、 *割り当て* と呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Dynamic Signal へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定したら、「エンタープライズ アプリにユーザーまたはグループを割り当てる」の手順に従って、これらの [ユーザーやグループを Dynamic Signal に割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。

#### Dynamic Signal にユーザーを割り当てる際の重要なヒント

- 1 人の Microsoft Entra ID ユーザーを Dynamic Signal に割り当て、自動ユーザー プロビジョニングの構成をテストすることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- Dynamic Signal にユーザーを割り当てるときは、割り当てダイアログで有効なアプリケーション固有ロール (使用可能な場合) を選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### 手順 3: Dynamic Signal への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Dynamic Signal でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Dynamic Signal のシングル サインオンに関する記事で説明されている手順に従って、Dynamic Signal に対して SAML ベースの [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dynamicsignal-tutorial)を有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra IDで Dynamic Signal の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Dynamic Signal**] を選択します。

    [Image: アプリケーションの一覧の [ダイナミック シグナル] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、Dynamic Signal のアカウントの **テナント URL** と **シークレット トークン** を入力します。 これらの値を見つけるには、次の 2 つの手順に従います。
7. Dynamic Signal 管理コンソールで、[ **Admin &gt; Advanced &gt; API**] に移動します。

    [Image: Dynamic Signal 管理コンソールのスクリーンショット。[管理者] メニューの [詳細設定] が強調表示されています。[詳細設定] メニューも表示され、[A P I] が強調表示されています。]

    **SCIM API URL を** **テナント URL** にコピーします。 [ **新しいトークンの生成** ] を選択して **ベアラー トークン** を生成し、値を **シークレット トークン**にコピーします。

    [Image: [トークン] ページのスクリーンショット。[S C I M A P I U R L]、[新しいトークンの生成]、[ベアラー トークン] が強調表示され、[ベアラー トークン] ボックスにプレースホルダーが表示されています。]
8. テナント URL とシークレット トークンを入力したら、**Test Connection** を選択して、Microsoft Entra IDが Dynamic Signal に接続できることを確認します。 接続できない場合は、使用中の Dynamic Signal アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
9. [ **作成]** を選択して構成を作成します。
10. [**概要**] ページで **[プロパティ**] を選択します。
11. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
12. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
13. [属性マッピング] セクションで、Microsoft Entra ID から Dynamic Signal に同期されるユーザー **属性** を確認します。 [ **照合** ] プロパティとして選択されている属性は、更新操作で Dynamic Signal のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: 動的シグナル ユーザー属性のスクリーンショット。]
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 4: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

- Dynamic Signal では、Microsoft Entra ID からユーザーを完全に削除する操作はサポートされていません。 Dynamic Signal でユーザーを完全に削除するには、Dynamic Signal 管理コンソール UI で操作を行う必要があります。
- 現在、Dynamic Signal ではグループがサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dynamicsignal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Dynamic Signal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dynamicsignal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Dynamic Signal の間のシングル サインオンを構成する方法について説明します。

この記事では、Druva と Microsoft Entra ID を統合する方法について説明します。 Druva を Microsoft Entra ID と統合すると、次のことが可能になります。

- Druva にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Druva に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Dynamic Signal シングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Dynamic Signal では、**SP** Initiated SSO がサポートされます。
- Dynamic Signal では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Dynamic Signal では、[自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dynamic-signal-provisioning-tutorial)がサポートされます。

### ギャラリーからの Druva の追加

Microsoft Entra ID への Druva の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Druva を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Druva**」と入力します。
4. 結果パネルから **Druva** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Druva に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、Druva に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Druva の関連ユーザーとの間にリンク関係を確立する必要があります。

Druva に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Dynamic SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Dynamic Signal のテストユーザーを作成する** - Dynamic Signal で Britta Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現とリンクされます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Dynamic Signal**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.voicestorm.com`

    b。 **[識別子]** ボックスに、`https://<subdomain>.voicestorm.com` という形式で URL を入力します。

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.voicestorm.com/User/SsoResponse`

    注

    これらの値は実際の値ではありません。 実際の Sign-On URL、識別子、応答 URL でこれらの値を更新します。 この値を取得するには、[Dynamic Signal クライアント サポート チーム](mailto:support@dynamicsignal.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Dynamic Signal のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Dynamic Signal SSO の構成

**Dynamic Signal** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [Dynamic Signal サポート チーム](mailto:support@dynamicsignal.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Dynamic Signal のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Dynamic Signal に作成します。 Dynamic Signal では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Dynamic Signal にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

Dynamic Signal では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dynamic-signal-provisioning-tutorial)をご覧ください。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Dynamic Signal のサインオン URL にリダイレクトされます。
- Dynamic Signal のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Dynamic Signal] タイルを選択すると、このオプションは Dynamic Signal のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/dynatrace-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Dynatrace を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/dynatrace-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Dynatrace の間のシングル サインオンを構成する方法について説明します。

この記事では、Dynatrace と Microsoft Entra ID を統合する方法について説明します。 Dynatrace を Microsoft Entra ID と統合すると、次のことができます。

- Dynatrace にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Dynatrace に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Dynatrace でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Dynatrace では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Dynatrace では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

Note

このアプリケーションの識別子は固定文字列値です。 1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Dynatrace の追加

Microsoft Entra ID への Dynatrace の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Dynatrace を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Dynatrace**」と入力します。
4. 結果のパネルから **[Dynatrace]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Dynatrace に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Dynatrace に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Dynatrace の関連ユーザー間にリンク関係を確立する必要があります。

Dynatrace に対する Microsoft Entra SSO を構成およびテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Dynatrace SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Dynatrace テストユーザーを作成** - B.Simon に対応する Dynatrace ユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO をテストする** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**Dynatrace**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure で事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **追加の URL の設定] を** 選択し、次の手順を実行して、 **SP** 開始モードでアプリケーションを構成します。

    **[サインオン URL]** テキスト ボックスに、URL として「`https://sso.dynatrace.com/`」と入力します。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を見つけます。 **[ダウンロード]** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[SAML 署名証明書]** セクションで **[編集]** ボタンを選択して、 **[SAML 署名証明書]** ダイアログ ボックスを開きます。 次の手順のようにします。

    [Image: SAML 署名証明書の編集]

    a. **[署名オプション]** の設定はあらかじめ入力されています。 組織ごとの設定を確認してください。

    b。 **保存** を選択します。
9. **[Dynatrace のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Dynatrace の SSO の構成

**Dynatrace** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** ファイルとコピーした適切な URL を [Dynatrace](https://www.dynatrace.com/support/help/shortlink/users-sso-hub) に送信する必要があります。 Dynatrace Web サイトの指示に従って、両方の側で SAML SSO 接続を構成できます。

#### Dynatrace のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Dynatrace に作成します。 Dynatrace では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Dynatrace にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Dynatrace のサインオン URL にリダイレクトされます。
- Dynatrace のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Dynatrace に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Dynatrace] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Dynatrace に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/e-days-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に E-days を設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/e-days-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と E-days の間のシングル サインオンを構成する方法について説明します。

この記事では、E-days と Microsoft Entra ID を統合する方法について説明します。 E-days を Microsoft Entra ID と統合すると、次のことが可能になります。

- E-days へのアクセス権を持つユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで E-days に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- E-days でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- E-days では、**SP および IDP** による SSO がサポートされます。

### ギャラリーからの E-days の追加

E-days と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に E-days をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「E-days**」と入力します。
4. 結果パネルから **[E-days** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### E-days 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、E-days に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、E-days での関連ユーザーとの間にリンク関係を確立する必要があります。

E-days 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **E-days SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **E-days テストユーザーの作成** - E-days で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon の表現にリンクする。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**E-days**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    `https://<SUBDOMAIN>.e-days.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://<SUBDOMAIN>.e-days.com/SSO/SAML2/SP/AssertionConsumer.aspx` |
    | `https://<SUBDOMAIN>.signin.e-days.com/<CUSTOM_URL>` |
    | `https://<SUBDOMAIN>.signin.e-days.co.uk/<CUSTOM_URL>` |
    |  |
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    `https://<SUBDOMAIN>.e-days.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、E-days クライアント サポート チーム](https://support.e-days.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **E-days のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### E-days の SSO の構成

**E-days** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [E-days サポート チーム](https://support.e-days.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### E-days のテスト ユーザーの作成

このセクションでは、E-days で B.Simon というユーザーを作成します。 [E-days サポート チーム](https://support.e-days.com)と協力して、E-days プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる E-days サインオン URL にリダイレクトされます。
- E-days のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した E-days に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [E-days] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した E-days に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/e2open-cm-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に e2open CM-Global を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/e2open-cm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と e2open CM-Global の間でシングル サインオンを構成する方法について説明します。

この記事では、e2open CM-Global と Microsoft Entra ID を統合する方法について説明します。 e2open CM-Global を Microsoft Entra ID と統合すると、次のことができます。

- e2open CM-Global にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで e2open CM-Global に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- e2open CM-Global でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- e2open CM-Global では、 **SP** Initiated SSO がサポートされます。

### ギャラリーから e2open CM-Global を追加する

e2open CM-Global と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に e2open CM-Global をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション** に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「e2open CM-Global**」と入力します。
4. 結果パネルから **e2open CM-Global** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### e2open CM-Global 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、e2open CM-Global に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと、e2open CM-Global での関連ユーザーとの間にリンク関係を確立する必要があります。

e2open CM-Global 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **e2open CM-Global SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **e2open CM-Global テスト ユーザーの作成** - e2open CM-Global で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**e2open CM-Global**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `http://pingone.com/<cmglobalCustomGUID>`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://sso.connect.pingidentity.com/sso/sp/ACS.saml2`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sso.connect.pingidentity.com/sso/sp/initsso?saasid=<saasid>&idpid=<idpid>`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、e2open CM-Global サポート チーム](mailto:customersupport@e2open.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **e2open CM-Global のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### e2open CM-Global SSO を構成する

**e2open CM-Global** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [e2open CM-Global サポート チーム](mailto:customersupport@e2open.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### e2open CM-Global テスト ユーザーを作成する

このセクションでは、e2open CM-Global で Britta Simon というユーザーを作成します。 [e2open CM-Global サポート チーム](mailto:customersupport@e2open.com)と協力して、e2open CM-Global プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる e2open CM-Global サインオン URL にリダイレクトされます。
- e2open CM-Global のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [e2open CM-Global] タイルを選択すると、このオプションは e2open CM-Global サインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/e2open-lsp-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に E2open LSP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/e2open-lsp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と E2open LSP の間にシングル サインオンを構成する方法について説明します。

この記事では、E2open LSP と Microsoft Entra ID を統合する方法について説明します。 E2open LSP をMicrosoft Entra ID と統合すると、次のことができます。

- E2open LSP にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って E2open LSP に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- E2open LSP でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- E2open LSP では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから E2open LSP を追加する

Microsoft Entra ID への E2open LSP の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に E2open LSP を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「E2open LSP**」と入力します。
4. 結果パネルから **E2open LSP** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### E2open LSP 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、E2open LSP に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと E2open LSP の関連ユーザーとの間にリンク関係を確立する必要があります。

E2open LSP に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **E2open LSP SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **E2open LSP でテスト ユーザーを作成し、Microsoft Entra のユーザー表示にリンクさせて、B.Simon に対応するユーザーを作成します**。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**E2open LSP**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer name>-<Environment>.tms-lsp.blujaysolutions.net/navi/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer name>-<Environment>.tms-lsp.blujaysolutions.net/navi/sam`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer name>-<Environment>.tms-lsp.blujaysolutions.net/navi/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、E2open LSP クライアント サポート チーム](mailto:customersupport@e2open.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### E2open LSP SSO の構成

**E2open LSP** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[E2open LSP サポート チーム](mailto:customersupport@e2open.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### E2open LSP テスト ユーザーの作成

このセクションでは、E2open LSP テストユーザーの作成 で Britta Simon というユーザーを作成します。 [E2open LSP サポート チーム](mailto:customersupport@e2open.com)と協力して、E2open LSP プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる E2open LSP サインオン URL にリダイレクトされます。
- E2open LSP のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [E2open LSP] タイルを選択すると、このオプションは E2open LSP サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/eab-navigate-impl-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に EAB Implementation を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/eab-navigate-impl-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EAB Implementation の間のシングル サインオンを構成する方法について説明します。

この記事では、EAB Implementation と Microsoft Entra ID を統合する方法について説明します。 EAB Implementation を Microsoft Entra ID と統合すると、次のことが可能になります。

- EAB Implementation にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで EAB Implementation に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EAB Implementation でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EAB Implementation では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの EAB Implementation の追加

Microsoft Entra ID への EAB Implementation の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に EAB Implementation を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**EAB Implementation**」と入力します。
4. 結果パネルで **[EAB Implementation]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### EAB Implementation に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、EAB Implementation に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと EAB Implementation の関連ユーザーとの間にリンク関係を確立する必要があります。

EAB Implementation に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EAB Implementation の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EAB Implementation テストユーザーの作成** - B.Simon に対応するユーザーを EAB Implementation で作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[EAB Implementation]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を正確に入力します。 `https://impl.bouncer.eab.com`

    b。 **[応答 URL (Assertion Consumer Service URL)]** テキスト ボックスに、次の値の両方を別々の行として入力します。

    | [応答 URL] |
    | --- |
    | `https://impl.bouncer.eab.com/sso/saml2/acs` |
    | `https://impl.bouncer.eab.com/sso/saml2/acs/` |
    |  |

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.navigate.impl.eab.com/`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[EAB Implementation クライアント サポート チーム](mailto:EABTechSupport@eab.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EAB Implementation の SSO の構成

**EAB Implementation** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [EAB Implementation サポート チーム](mailto:EABTechSupport@eab.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EAB Implementation のテスト ユーザーの作成

このセクションでは、EAB Implementation で B.Simon というユーザーを作成します。 [EAB Implementation サポート チーム](mailto:EABTechSupport@eab.com)と連携して、EAB Implementation プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる EAB 実装のサインオン URL にリダイレクトされます。
- EAB Implementation のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [EAB 実装] タイルを選択すると、このオプションは EAB 実装のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/eab-navigate-strategic-care-tutorial"} -->
## Microsoft Entra ID を使用して EAB Navigate Strategic Care for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/eab-navigate-strategic-care-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EAB Navigate Strategic Care の間でシングル サインオンを構成する方法について説明します。

この記事では、EAB Navigate Strategic Care と Microsoft Entra ID を統合する方法について説明します。 EAB Navigate Strategic Care と Microsoft Entra ID を統合すると、次のことができます。

- EAB Navigate Strategic Care にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで EAB Navigate Strategic Care に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EAB Navigate Strategic Care でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EAB Navigate Strategic Care では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの EAB Navigate Strategic Care の追加

EAB Navigate Strategic Care と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に EAB Navigate Strategic Care をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「EAB Navigate Strategic Care**」と入力します。
4. 結果パネルから **EAB Navigate Strategic Care** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### EAB Navigate Strategic Care 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、EAB Navigate Strategic Care に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、EAB Navigate Strategic Care での関連ユーザーとの間にリンク関係を確立する必要があります。

EAB Navigate Strategic Care に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EAB Navigate Strategic Care SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EAB Navigate Strategic Care テスト ユーザーを作成する** - EAB Navigate Strategic Care で B.Simon に対応するユーザーを作成し、Microsoft Entra での当該ユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[EAB Navigate Strategic Care]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMERURL>.eab.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、 [EAB Navigate Strategic Care クライアント サポート チーム](mailto:tech@gradesfirst.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、コピー ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EAB Navigate Strategic Care SSO の構成

**EAB Navigate Strategic Care** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[EAB Navigate Strategic Care サポート チーム](mailto:tech@gradesfirst.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EAB Navigate Strategic Care のテスト ユーザーの作成

このセクションでは、EAB Navigate Strategic Care で B.Simon というユーザーを作成します。 [EAB Navigate Strategic Care サポート チーム](mailto:tech@gradesfirst.com)と協力して、EAB Navigate Strategic Care プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる EAB Navigate Strategic Care のサインオン URL にリダイレクトされます。
- EAB Navigate Strategic Care のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [EAB Navigate Strategic Care] タイルを選択すると、このオプションは EAB Navigate Strategic Care のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/eab-navigate-tutorial"} -->
## Microsoft Entra ID で EAB for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/eab-navigate-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EAB の間のシングル サインオンを構成する方法について説明します。

この記事では、EAB と Microsoft Entra ID を統合する方法について説明します。 EAB を Microsoft Entra ID と統合すると、次のことが可能になります。

- EAB にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで EAB に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EAB でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EAB では、**SP** によって開始された SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの EAB の追加

EAB と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に EAB をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「EAB**」と入力します。
4. 結果パネルから **EAB** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### EAB 用の Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、EAB に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、EAB での関連ユーザーとの間にリンク関係を確立する必要があります。

EAB 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EAB SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **EAB テストユーザーを作成** - Microsoft Entra のユーザーとして表現される B.Simon に対応する EAB 内のユーザーを作成し、それにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**EAB**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を正確に入力します。 `https://bouncer.eab.com`

    b。 **[応答 URL (Assertion Consumer Service URL)]** テキスト ボックスに、次の値の両方を別々の行として入力します。

    | [応答 URL] |
    | --- |
    | `https://bouncer.eab.com/sso/saml2/acs` |
    | `https://bouncer.eab.com/sso/saml2/acs/` |
    |  |

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.navigate.eab.com/`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、 [EAB クライアント サポート チーム](mailto:EABTechSupport@eab.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EAB SSO の構成

**EAB** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[EAB サポート チーム](mailto:EABTechSupport@eab.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EAB テスト ユーザーの作成

このセクションでは、EAB で B.Simon というユーザーを作成します。 [EAB サポート チーム](mailto:EABTechSupport@eab.com)と協力して、EAB プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる EAB サインオン URL にリダイレクトされます。
- EAB のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [EAB] タイルを選択すると、このオプションは EAB サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/eacomposer-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に EAComposer を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/eacomposer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EAComposer の間のシングル サインオンを構成する方法について説明します。

この記事では、EAComposer と Microsoft Entra ID を統合する方法について説明します。 EAComposer を Microsoft Entra ID と統合すると、次のことが可能になります。

- EAComposer にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで EAComposer に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EAComposer でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EAComposer では、**SP** Initiated SSO がサポートされます。
- EAComposer では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから EAComposer を追加する

Microsoft Entra ID への EAComposer の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に EAComposer を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**EAComposer**」と入力します。
4. 結果のパネルから **[EAComposer]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### EAComposer 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、EAComposer に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、EAComposer での関連ユーザーとの間にリンク関係を確立する必要があります。

EAComposer 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EAComposer の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EAComposer のテスト ユーザーの作成** - EAComposer で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[EAComposer]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.eacomposer.com/solution/login.aspx`
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. **[EAComposer のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EAComposer の SSO の構成

**EAComposer** 側でシングル サインオンを構成するには、**サムプリントの値**とアプリケーションの構成からコピーした適切な URL を [EAComposer サポート チーム](mailto:support@eacomposer.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EAComposer のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを EAComposer に作成します。 EAComposer では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 EAComposer にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる EAComposer のサインオン URL にリダイレクトされます。
- EAComposer のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [EAComposer] タイルを選択すると、このオプションは EAComposer のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/easy-metrics-connector-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Easy Metrics Connector を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/easy-metrics-connector-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Easy Metrics Connector の間でシングル サインオンを構成する方法について説明します。

この記事では、Easy Metrics Connector を Microsoft Entra ID と統合する方法について説明します。 このアプリケーションは、Microsoft Entra ID と Auth0 の間のブリッジであり、認証を顧客のMicrosoft Entra ID にフェデレーションします。 Easy Metrics Connector と Microsoft Entra ID を統合すると、次のことが可能になります。

- Easy Metrics Connector にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Easy Metrics Connector に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Easy Metrics Connector 用の Microsoft Entra のシングル サインオンを構成およびテストします。 Easy Metrics Connector では、**SP** Initiated シングル サインオンのみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Easy Metrics Connector と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Easy Metrics Connector でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Easy Metrics Connector アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Easy Metrics Connector を追加する

Microsoft Entra アプリケーション ギャラリーから Easy Metrics Connector を追加して、Easy Metrics Connector でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Easy Metrics Connector]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[ID]** テキストボックスに、[Easy Metrics Connector サポート チーム](mailto:support@easymetrics.com)から提供された値を入力します。

    b。 **[応答 URL]** テキストボックスに、[Easy Metrics Connector サポート チーム](mailto:support@easymetrics.com)から提供された値を入力します。

    c. **[サインオン URL]** テキストボックスに、[Easy Metrics Connector サポート チーム](mailto:support@easymetrics.com)から提供された値を入力します。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### Easy Metrics Connector SSO の構成

**Easy Metrics Connector** 側でシングル サインオンを構成するには、**証明書 (PEM)** を [Easy Metrics Connector サポート チーム](mailto:support@easymetrics.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Easy Metrics Connector テスト ユーザーの作成

このセクションでは、Easy Metrics Connector で Britta Simon というユーザーを作成します。 [Easy Metrics Connector サポート チーム](mailto:support@easymetrics.com) と連携して、Easy Metrics Connector プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Easy Metrics Connector のサインオン URL にリダイレクトされます。
- Easy Metrics Connector のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Easy Metrics Connector] タイルを選択すると、このオプションは Easy Metrics Connector のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/easysso-for-bamboo-tutorial"} -->
## Microsoft Entra ID を使用して EasySSO for Bamboo for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/easysso-for-bamboo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EasySSO for Bamboo の間でシングル サインオンを構成する方法について説明します。

この記事では、EasySSO for Bamboo と Microsoft Entra ID を統合する方法について説明します。 EasySSO for Bamboo を Microsoft Entra ID と統合すると、次のことができます。

- Bamboo にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Bamboo に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EasySSO for Bamboo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EasySSO for Bamboo では、**SP および IDP** Initiated SSO がサポートされます。
- EasySSO for Bamboo では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの EasySSO for Bamboo の追加

Microsoft Entra ID への EasySSO for Bamboo の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に EasySSO for Bamboo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**EasySSO for Bamboo**」と入力します。
4. 結果のパネルから **[EasySSO for Bamboo]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### EasySSO for Bamboo 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、EasySSO for Bamboo に対する Microsoft Entra SSO を構成およびテストするします。 SSO を機能させるためには、Microsoft Entra ユーザーと、EasySSO for Bamboo での関連ユーザーとの間にリンク関係を確立する必要があります。

EasySSO for Bamboo で Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EasySSO for Bamboo SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **EasySSO for Bamboo のテストユーザーを作成 - B.Simon に対応するユーザーを EasySSO for Bamboo に作成し、Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[EasySSO for Bamboo]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SERVER_BASE_URL>/plugins/servlet/easysso/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SERVER_BASE_URL>/plugins/servlet/easysso/saml`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SERVER_BASE_URL>/login.jsp`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値が不明な場合は、EasySSO サポート チーム  にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. EasySSO for Bamboo アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、EasySSO for Bamboo アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:0.9.2342.19200300.100.1.1 | user.userprincipalname |
    | urn:oid:0.9.2342.19200300.100.1.3 | ユーザーのメールアドレス |
    | urn:oid:2.16.840.1.113730.3.1.241 | user.displayname |
    | urn:oid:2.5.4.4 | ユーザーの名字 |
    | urn:oid:2.5.4.42 | User.givenname |

    Microsoft Entra ユーザーに **sAMAccountName** が構成されている場合は、 **urn:oid:0.9.2342.19200300.100.1.1** を **sAMAccountName** 属性にマップする必要があります。
9. [**SAML でのシングル サインオンの設定**] ページの [**SAML 署名証明書**] セクションで、[**証明書 (Base64)** または**フェデレーション メタデータ XML** オプションのリンクの**ダウンロード**] を選択し、いずれかまたはすべてをコンピューターに保存します。 Bamboo EasySSO を構成するには、後で必要になります。

    [Image: 証明書のダウンロード リンク]

    EasySSO for Bamboo の構成を証明書を使って手動で実施する予定の場合には、他にも以下のセクションから**ログイン URL** と **Microsoft Entra ID** をコピーし、コンピューターに保存しておく必要があります。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EasySSO for Bamboo SSO の構成

1. 別の Web ブラウザー ウィンドウで、Zoom 企業サイトに管理者としてサインインします
2. **[Manage Apps](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリの管理)** セクションに移動します。

    [Image: アプリの管理]
3. 左側で **EasySSO** を見つけて選択します。

    [Image: Easy SSO]
4. **SAML** オプションを選択します。 これにより、SAML 構成セクションが表示されます。

    [Image: SAML]
5. 上部の [ **証明書** ] タブを選択すると、次の画面が表示されます。

    [Image: メタデータ URL]
6. ここで、**Microsoft Entra SSO** 構成の前の手順で保存した**証明書 (Base64)** または**メタデータ ファイル**を見つけます。 続行する方法には、次のオプションがあります。

    a. ローカル ファイルとしてコンピューターにダウンロードしたアプリ フェデレーション **メタデータ ファイル** を使用します。 [ **アップロード]** ラジオ ボタンを選択し、オペレーティング システムに固有の [ファイルのアップロード] ダイアログに従います。

    **又は**

    b。 アプリ フェデレーション **メタデータ ファイル** を開き、ファイルの内容 (任意のプレーン テキスト エディター) を表示し、クリップボードにコピーします。 **[入力]** オプションを選択し、クリップボードの内容をテキスト フィールドに貼り付けます。

    **又は**

    c. 完全に手動で構成します。 アプリ フェデレーション **証明書 (Base64)** を開き、ファイルの内容 (任意のプレーン テキスト エディター) を表示し、クリップボードにコピーします。 **IdP トークン署名証明書**のテキスト フィールドに貼り付けます。 次に、[ **全般** ] タブに移動し、 **POST バインディング URL** フィールドと **エンティティ ID** フィールドに、前に保存した **ログイン URL** と **Microsoft Entra 識別子** のそれぞれの値を入力します。
7. ページの下部にある **[保存]** ボタンを選択します。 メタデータ ファイルまたは証明書ファイルの内容が構成フィールドに解析されていることがわかります。 これで、EasySSO for Bamboo の構成は完了しました。
8. 最適なテストエクスペリエンスを得るには、**Look & Feel** タブに移動し、**SAML ログイン ボタン** オプションをオンにします。 これにより、Microsoft Entra SAML 統合をエンドツーエンドでテストするための、独立した専用のボタンが Bamboo ログイン画面で有効になります。 このボタンをオンのままにすることで、運用モードでの配置、色、翻訳を構成することもできます。

    [Image: 外観と使い心地]

    注

    問題がある場合は、EasySSO サポート チーム にお問い合わせください。

#### EasySSO for Bamboo テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Bamboo に作成します。 EasySSO for Bamboo では Just-In-Time ユーザー プロビジョニングがサポートされており、既定では**無効**になっています。 ユーザー プロビジョニングを有効にするには、EasySSO プラグイン構成の [全般] セクションで、[ **ログインに成功したときにユーザーを作成** する] オプションを明示的にオンにする必要があります。 Bamboo にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

ただし、ユーザーの最初のログインで自動ユーザー プロビジョニングを有効にしない場合は、Bamboo インスタンスが使用するバックエンド ユーザー ディレクトリ (LDAP や Atlassian Crowd など) にユーザーが存在している必要があります。

[Image: ユーザー アカウント設定]

### SSO のテスト

#### IdP によって開始されるワークフロー

このセクションでは、マイ アプリを使用して Microsoft Entra のシングル サインオン構成をテストします。

マイ アプリで [EasySSO for Bamboo] タイルを選択すると、SSO を設定した Bamboo インスタンスに自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。

#### SP によって開始されるワークフロー

このセクションでは、Bamboo の **[SAML Login](SAML ログイン)** ボタンを使用して Microsoft Entra のシングル サインオン構成をテストします。

[Image: ユーザー SAML ログイン]

このシナリオでは、Bamboo EasySSO 構成ページの [**ルック アンド フィール**] タブで **SAML ログイン ボタン**が有効になっていると仮定します (上記を参照)。 既存のセッションとの干渉を避けるために、シークレット モードにしたブラウザーで Bamboo のログイン URL を開きます。 [ **SAML ログイン** ] ボタンを選択すると、Microsoft Entra ユーザー認証フローにリダイレクトされます。 正常に完了すると、SAML 経由で認証済みユーザーとして Bamboo インスタンスにリダイレクトされます。

Microsoft Entra ID からのリダイレクト後に、次の画面が表示される可能性があります。

[Image: EasySSO エラー画面]

このような場合には、[こちらのページの手順](https://techtime.co.nz/display/TECHTIME/EasySSO+How+to+get+the+logs#EasySSOHowtogetthelogs-RETRIEVINGTHELOGS)に従って **atlassian-bamboo.log** ファイルにアクセスする必要があります。 エラーの詳細は、EasySSO エラー ページで見つかった参照 ID で確認できます。

ログ メッセージのダイジェストに問題がある場合は、EasySSO サポート チーム にお問い合わせください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/easysso-for-bitbucket-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に EasySSO for BitBucket を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/easysso-for-bitbucket-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EasySSO for BitBucket の間でシングル サインオンを構成する方法について説明します。

この記事では、EasySSO for BitBucket と Microsoft Entra ID を統合する方法について説明します。 EasySSO for BitBucket を Microsoft Entra ID と統合すると、次のことができます。

- EasySSO for BitBucket にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して EasySSO for BitBucket に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な EasySSO for BitBucket のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EasySSO for BitBucket では、SP-initiated および IdP-initiated SSO がサポートされます。
- EasySSO for BitBucket では、"Just-In-Time" ユーザー プロビジョニングがサポートされます。

### ギャラリーから EasySSO for BitBucket を追加する

Microsoft Entra ID への EasySSO for BitBucket の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に EasySSO for BitBucket を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**EasySSO for BitBucket**」と入力します。
4. 結果から **EasySSO for BitBucket** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### EasySSO for BitBucket 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、EasySSO for BitBucket に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと EasySSO for BitBucket の関連ユーザーとの間にリンクされた関係を確立する必要があります。

EasySSO for BitBucket で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. ユーザーがこの機能を使用できるように Microsoft Entra SSO を構成します。
    1. B.Simon で Microsoft Entra のシングル サインオンをテストする Microsoft Entra テスト ユーザーを作成します。
    2. B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます。
2. EasySSO for BitBucket SSO を構成して、アプリケーション側でシングル サインオン設定を構成します。
    1. EasySSO for BitBucket のテストユーザーを作成 し、EasySSO for BitBucket で B.Simon の対応ユーザーを設定して、Microsoft Entra のユーザー表現にリンクさせます。
3. SSO をテスト して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**EasySSO for BitBucket** アプリケーション統合ページを参照し、[**管理**] セクションを見つけます。 **[シングル サインオン]** を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 鉛筆アイコンが強調表示された [SAML を使用して単一 Sign-On を設定する] ページのスクリーンショット]
5. **[基本的な SAML 構成]** セクションで、**IdP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用する URL を入力します。 `https://<server-base-url>/plugins/servlet/easysso/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用する URL を入力します。 `https://<server-base-url>/plugins/servlet/easysso/saml`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    - [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用する URL を入力します。 `https://<server-base-url>/login.jsp`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値が不明な場合は、 [EasySSO サポート チーム](mailto:support@techtime.co.nz) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. EasySSO for BitBucket アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 既定の属性のスクリーンショット]
8. EasySSO for BitBucket アプリケーションでは、さらにいくつかの属性も SAML 応答で返されることが想定されています。 それらを次の表に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:0.9.2342.19200300.100.1.1 | user.userprincipalname |
    | urn:oid:0.9.2342.19200300.100.1.3 | ユーザーのメールアドレス |
    | urn:oid:2.16.840.1.113730.3.1.241 | user.displayname |
    | urn:oid:2.5.4.4 | ユーザーの名字 |
    | urn:oid:2.5.4.42 | User.givenname |

    Microsoft Entra ユーザーが **sAMAccountName を** 構成している場合は、 **urn:oid:0.9.2342.19200300.100.1.1** を **sAMAccountName** 属性にマップする必要があります。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** または **フェデレーション メタデータ XML** オプションのダウンロード リンクを選択します。 そのいずれかまたは両方をコンピューターに保存します。 BitBucket EasySSO を構成するには、後で必要になります。

    [Image: [SAML 署名証明書] セクションのスクリーンショット。ダウンロード リンクが強調表示されています]

    証明書を使用して EasySSO for BitBucket を手動で構成する場合は、 **ログイン URL** と **Microsoft Entra 識別子**をコピーして、コンピューターに保存する必要もあります。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EasySSO for BitBucket SSO の構成

1. 別の Web ブラウザー ウィンドウで、Zoom 企業サイトに管理者としてサインインします
2. [ **管理** ] セクションに移動します。

    [Image: 歯車アイコンが強調表示されている BitBucket インスタンスのスクリーンショット]
3. **EasySSO** を見つけて選択します。

    [Image: Easy SSO オプションのスクリーンショット]
4. **[SAML**] を選択します。 これにより、SAML の構成セクションが表示されます。

    [Image: SAML が強調表示されている EasySSO 管理ページのスクリーンショット]
5. [ **証明書** ] タブを選択すると、次の画面が表示されます。

    [Image: さまざまなオプションが強調表示されている [証明書] タブのスクリーンショット]
6. この記事の前のセクションで保存した **証明書 (Base64)** または **メタデータ ファイル** を見つけます。 次のいずれの方法で続行できます。

    - コンピューター上のローカル **ファイル** にダウンロードしたアプリ フェデレーション メタデータ ファイルを使用します。 [ **アップロード** ] ラジオ ボタンを選択し、オペレーティング システムに固有のパスに従います。
    - アプリのフェデレーション **メタデータ ファイル** を開き、任意のプレーンテキスト エディターでファイルの内容を表示します。 それをクリップボードにコピーします。 [ **入力]** を選択し、クリップボードの内容をテキスト フィールドに貼り付けます。
    - すべて手動で構成します。 アプリフェデレーション **証明書 (Base64)** を開き、任意のプレーンテキスト エディターでファイルの内容を表示します。 それをクリップボードにコピーし、[ **IdP トークン署名証明書** ] テキスト フィールドに貼り付けます。 次に、[ **全般** ] タブに移動し、[ **POST バインディング URL** ] フィールドと **[エンティティ ID** ] フィールドに、前に保存した **ログイン URL** と **Microsoft Entra 識別子** のそれぞれの値を入力します。
7. ページの下部にある **[保存] を** 選択します。 メタデータ ファイルまたは証明書ファイルの内容が構成フィールドで解析されていることを確認できます。 これで、EasySSO for BitBucket の構成は完了しました。
8. 構成をテストするには、[ **外観** ] タブに移動し、[ **SAML ログイン ボタン**] を選択します。 これにより、BitBucket サインイン画面の独立したボタンが有効になり、Microsoft Entra SAML 統合をエンド ツー エンドでテストできるようになります。 このボタンをオンのままにすることで、運用モードでの配置、色、および翻訳を構成することもできます。

    [Image: SAML ログイン ボタンが強調表示されている [SAML] ページの [外観] タブのスクリーンショット]

    注

    問題がある場合は、 [EasySSO サポート チーム](mailto:support@techtime.co.nz)にお問い合わせください。

#### EasySSO for BitBucket のテスト ユーザーの作成

このセクションでは、BitBucket で Britta Simon というユーザーを作成します。 EasySSO for BitBucket では Just-In-Time ユーザー プロビジョニングがサポートされており、既定では無効になっています。 これを有効にするには、EasySSO プラグイン構成の **[全般**] セクションで、**ログインに成功したユーザーの作成**を明示的に確認する必要があります。 BitBucket にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

ただし、ユーザーが初めてサインインしたときに自動ユーザー プロビジョニングを有効にしない場合は、BitBucket のインスタンスが使用するユーザー ディレクトリにユーザーが存在している必要があります。 このディレクトリは、たとえば LDAP や Atlassian Crowd です。

[Image: [成功したログイン時にユーザーを作成する] が強調表示されている EasySSO プラグイン構成の [全般] セクションのスクリーンショット]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる EasySSO for BitBucket サインオン URL にリダイレクトされます。
- EasySSO for BitBucket のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した EasySSO for BitBucket に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [EasySSO for BitBucket] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した EasySSO for BitBucket に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/easysso-for-confluence-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に EasySSO for Confluence を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/easysso-for-confluence-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EasySSO for Confluence の間でシングル サインオンを構成する方法について説明します。

この記事では、EasySSO for Confluence と Microsoft Entra ID を統合する方法について説明します。 EasySSO for Confluence と Microsoft Entra ID を統合すると、次のことができます。

- Confluence にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Confluence に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EasySSO for Confluence でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EasySSO for Confluence では、**SP および IDP による** SSO がサポートされています。
- EasySSO for Confluence では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの EasySSO for Confluence の追加

Microsoft Entra ID への EasySSO for Confluence の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に EasySSO for Confluence を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**、&gt;**エンタープライズ アプリ**、&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**EasySSO for Confluence**」と入力します。
4. 結果パネルから **EasySSO for Confluence** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### EasySSO for Confluence の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、EasySSO for Confluence に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと EasySSO for Confluence の関連ユーザーとの間にリンク関係を確立する必要があります。

EasySSO for Confluence に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EasySSO for Confluence SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Confluence用 EasySSO テストユーザーを作成 - B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[EasySSO for Confluence]**&gt;**[シングル サインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/easysso/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/easysso/saml`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/login.jsp`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値が不明な場合は、EasySSO サポート チーム  にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. EasySSO for Confluence アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. 上記に加えて、EasySSO for Confluence アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:0.9.2342.19200300.100.1.1 | user.userprincipalname |
    | urn:oid:0.9.2342.19200300.100.1.3 | User.mail |
    | urn:oid:2.16.840.1.113730.3.1.241 | user.displayname |
    | urn:oid:2.5.4.4 | ユーザー.姓 |
    | urn:oid:2.5.4.42 | User.givenname |

    Microsoft Entra ユーザーに **sAMAccountName** が構成されている場合は、 **urn:oid:0.9.2342.19200300.100.1.1** を **sAMAccountName** 属性にマップする必要があります。
9. [**SAML でのシングル サインオンの設定**] ページの [**SAML 署名証明書**] セクションで、[**証明書 (Base64)** または**フェデレーション メタデータ XML** オプションのリンクの**ダウンロード**] を選択し、いずれかまたはすべてをコンピューターに保存します。 Confluence EasySSO を構成するには、後で必要になります。

    [Image: 証明書のダウンロード リンク]

    証明書を使用して EasySSO for Confluence 構成を手動で実行する場合は、以下のセクションから **ログイン URL** と **Microsoft Entra 識別子** をコピーし、コンピューターに保存する必要もあります。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Confluence SSO 用 EasySSO を設定する

1. 別の Web ブラウザー ウィンドウで、EasySSO for Confluence 企業サイトに管理者としてサインインし、[ **アプリの管理** ] セクションに移動します。

    [Image: アプリの管理]
2. 左側で **EasySSO** を見つけて選択します。 次に、[構成] ボタン **を** 選択します。

    [Image: Easy SSO]
3. **SAML** オプションを選択します。 これにより、SAML 構成セクションが表示されます。

    [Image: SAML]
4. 上部の [ **証明書** ] タブを選択すると、次の画面が表示されます。

    [Image: メタデータ URL]
5. ここで、**Microsoft Entra SSO** 構成の前の手順で保存した**証明書 (Base64)** または**メタデータ ファイル**を見つけます。 続行する方法には、次のオプションがあります。

    a. コンピューター上のローカル **ファイル** にダウンロードしたアプリ フェデレーション メタデータ ファイルを使用します。 [ **アップロード]** ラジオ ボタンを選択し、オペレーティング システムに固有の [ファイルのアップロード] ダイアログに従います

    **又は**

    b。 アプリのフェデレーション **メタデータ ファイル** を開き、ファイルのコンテンツ (任意のプレーン テキスト エディター) を表示し、クリップボードにコピーします。 **[入力]** オプションを選択し、クリップボードの内容をテキスト フィールドに貼り付けます。

    **又は**

    c. 完全に手動で構成します。 アプリのフェデレーション **証明書 (Base64)** を開き、ファイルの内容 (任意のプレーン テキスト エディター) を表示し、クリップボードにコピーします。 **IdP トークン署名証明書**のテキスト フィールドに貼り付けます。 次に、[ **全般** ] タブに移動し、 **POST バインディング URL** フィールドと **エンティティ ID** フィールドに、前に保存した **ログイン URL** と **Microsoft Entra 識別子** のそれぞれの値を入力します。
6. ページの下部にある **[保存]** ボタンを選択します。 メタデータ ファイルまたは証明書ファイルの内容が構成フィールドに解析されていることがわかります。 EasySSO for Confluence の構成が完了しました。
7. 最適なテスト エクスペリエンスを得るには、[ **ルック アンド フィール** ] タブに移動し、[ **SAML Login Button]\(SAML ログイン ボタン** \) オプションをオンにします。 これにより、Confluence ログイン画面で個別のボタンが有効になり、Microsoft Entra SAML 統合をエンド ツー エンドでテストできます。 このボタンをオンのままにして、運用モードの配置、色、翻訳を構成することもできます。

    [Image: 外観と使い心地]

    手記

    問題がある場合は、EasySSO サポート チーム にお問い合わせください。

#### EasySSO for Confluence のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Confluence に作成します。 EasySSO for Confluence では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で **無効になっています** 。 ユーザー プロビジョニングを有効にするには、EasySSO プラグイン構成の [全般] セクションで、[ **ログインに成功したときにユーザーを作成** する] オプションを明示的にオンにする必要があります。 Confluence にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

ただし、ユーザーの最初のログインで自動ユーザー プロビジョニングを有効にしない場合は、Confluence インスタンスが使用するバックエンド ユーザー ディレクトリ (LDAP や Atlassian Crowd など) にユーザーが存在している必要があります。

[Image: ユーザー アカウント設定]

### SSO のテスト

#### IdP によって開始されるワークフロー

このセクションでは、マイ アプリを使用して Microsoft Entra のシングル サインオン構成をテストします。

マイ アプリで [EasySSO for Confluence] タイルを選択すると、SSO を設定した Confluence インスタンスに自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。

#### SP によって開始されるワークフロー

このセクションでは、Confluence **SAML Login** ボタンを使用して Microsoft Entra のシングル サインオン構成をテストします。

[Image: ユーザー SAML ログイン]

このシナリオでは、Confluence EasySSO 構成ページの [**ルック アンド フィール**] タブで **SAML ログイン ボタン**が有効になっていると仮定します (上記を参照)。 ブラウザーのシークレット モードで Confluence ログイン URL を開き、既存のセッションとの干渉を回避します。 [ **SAML ログイン** ] ボタンを選択すると、Microsoft Entra ユーザー認証フローにリダイレクトされます。 正常に完了すると、SAML 経由で認証されたユーザーとして Confluence インスタンスにリダイレクトされます。

Microsoft Entra ID からリダイレクトされた後、次の画面が表示される可能性があります

[Image: EasySSO エラー画面]

この場合は、 [このページの指示に](https://techtime.co.nz/display/TECHTIME/EasySSO+How+to+get+the+logs#EasySSOHowtogetthelogs-RETRIEVINGTHELOGS) 従って、 **atlassian-confluence.log** ファイルにアクセスする必要があります。 エラーの詳細は、EasySSO エラー ページで見つかった参照 ID で確認できます。

ログ メッセージのダイジェストに問題がある場合は、EasySSO サポート チーム にお問い合わせください。
<!-- /MSL-PAGE -->
