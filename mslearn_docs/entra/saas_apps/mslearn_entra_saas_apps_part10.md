# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 10)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 69

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/funnel-leasing-provisioning-tutorial"} -->
## Microsoft Entra ID を使用してユーザー自動プロビジョニングを行うために、Funnel Leasing を設定します。 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/funnel-leasing-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDからファネル リースにユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Funnel LeasingとMicrosoft Entra IDの両方で実行し、自動ユーザープロビジョニングを構成するために必要な手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーを[Funnel リース](https://funnelleasing.com)に自動的にプロビジョニングおよびデプロビジョニングします。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Funnel Leasing でユーザーを作成します。
- ファンネルリースでアクセスが不要になったユーザーを削除します。
- Microsoft Entra IDとFunnel Leasingの間でユーザー属性の同期を保つ。
- Funnel Leasing への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Funnel のライブ コミュニティ、または少なくとも必要なすべての構成が稼働日に備えて Funnel 側で完了していることの確認。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとFunnel Leasingの間でマッピングするデータを決定します。

### 手順 2: Microsoft Entra IDを用いたプロビジョニングをサポートするように、Funnel Leasingを構成する

ファネル アカウント マネージャーに連絡し、Microsoft Entra ユーザー プロビジョニングを有効にしたいことを伝えてください。彼らは認証ベアラー トークンを提供します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーからFunnel Leasingを追加する

Microsoft Entra アプリケーション ギャラリーからファネル リースを追加して、ファネル リースへのプロビジョニングの管理を開始します。 SSO のために Funnel Leasing を以前に設定している場合は、同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Funnel Leasing への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDを Funnel のユーザー アカウント プロビジョニング API に接続し、Microsoft Entra IDのユーザー割り当てに基づいて、Funnel で割り当てられたユーザー アカウントを作成、更新、無効化するようにプロビジョニング サービスを構成する方法について説明します。

#### Microsoft Entra IDでファネル リースの自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Funnel]** を選択します。

    [Image: アプリケーション一覧内の Funnel Leasing リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Funnel テナントの URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra ID が Funnel に接続できることを確認します。 接続に失敗した場合は、じょうごアカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Funnel に同期されるユーザー属性を確認します。 **「照合」**プロパティとして選択されている属性は、更新操作でFunnelのユーザーアカウントを照合するために使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、じょうご API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Funnel Leasing に必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | 名前.名 | 糸 |  | ✓ |
    | 名前.姓 | 糸 |  | ✓ |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | エクスターナルID | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### ロールとグループのマッピング

ファネル ロールにAzureユーザーを関連付ける場合、またはファネルの従業員グループにAzureユーザーを関連付けるには、Funnel はカスタム マッピング機能を使用します。

- どのAzureフィールドが使用されますか?

    ロール マッピングの場合、Funnel は既定で SCIM `title` 属性を確認します。 この SCIM 属性は、既定で `jobTitle` Azure ユーザー属性にマップされます。

    グループ マッピングの場合、Funnel は既定で SCIM `userType` 属性を確認します。 この SCIM 属性は、既定で `department` Azure ユーザー属性にマップされます。

    使用するフィールドを変更する場合は、**[属性マッピング]** セクションを編集し、目的のフィールドを `title` と `userType` にマップできます。
- 使用される値はどれですか?

    初期セットアップで、ロールとグループのマッピングに使用するすべての値を決定します。 Funnel で構成を設定するために、Funnel アカウント マネージャーにこれらの値を提供します。

    たとえば、`jobTitle` フィールドに `agent` 値を設定する場合は、その値をマップするファネル ロールをファネル アカウント マネージャーに指定する必要があります。

    将来的に新しい値を更新または追加する必要が生じた場合は、ファネル アカウント マネージャーに通知する必要があります。
- ユーザーを複数のロールとグループに関連付けるにはどうすればよいですか?

    1 人のユーザーを複数のじょうごロールに関連付けることはできませんが、1 人のユーザーを複数のファネルの従業員グループに関連付けることができます。

    ユーザーを複数のファネル従業員グループに関連付けるには、 `department` ユーザー属性 (または `userType`にマップした属性) に複数の値を指定する必要があります。 各値は区切り記号で区切る必要があります。 既定では、`-` 文字が区切り記号として使用されます。 別の区切り記号を使用するには、ファネルアカウントマネージャーに通知する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fuse-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Fuse を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fuse-tutorial
- Service: entra-id / saas-apps
- Article date: 2023-03-10
- Summary: Microsoft Entra ID と Fuse の間のシングル サインオンを構成する方法について説明します。

この記事では、Fuse と Microsoft Entra ID を統合する方法について説明します。 Fuse は、組織内の学習者が職場でのスキルを向上させるために必要な知識と専門知識にアクセスできるようにする学習プラットフォームです。 Fuse を Microsoft Entra ID と統合すると、次のことが可能になります。

- Fuse にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Fuse に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Fuse に対する Microsoft Entra のシングル サインオンをテスト環境で構成・テストする。 Fuse では、**SP** initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Fuse と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Fuse でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Fuse アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Fuse を追加する

Microsoft Entra アプリケーション ギャラリーから Fuse を追加して、Fuse でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra シングル サインオンの構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Fuse]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションの **[サインオン URL]** テキスト ボックスで、次のパターンを使用して適切な URL を指定します: `https://{tenantname}.fuseuniversal.com/`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[Fuse クライアント サポート チーム](mailto:support@fusion-universal.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Fuse のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

### Fuse のシングル サインオンを構成する

**Fuse** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と Azure portal からコピーした URL を [Fuse サポート チーム](mailto:support@fusion-universal.com)に送信します。 サポート チームは、コピーした URL を使用して、アプリケーションでシングル サインオンを構成します。

#### Fuse のテスト ユーザーの作成

シングル サインオンをテストして使用できるようにするには、fuse アプリケーションでユーザーを作成してアクティブにする必要があります。

このセクションでは、前のセクションで既に作成した Microsoft Entra ユーザーに対応する Britta Simon というユーザーを Fuse に作成します。 [Fuse サポート チーム](mailto:support@fusion-universal.com)と連携して、Fuse プラットフォームにユーザーを追加してください。

### シングル サインオンのテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- **[SAML ベースのサインオン]** ウィンドウの **[Test single sign-on with Fuse](Fuse でシングル サインオンをテストする)** セクションで、Azure portal の **[Test this application](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/このアプリケーションをテストする)** を選択します。 Fuse のサインオン URL にリダイレクトされ、そこからサインイン フローを開始することができます。
- Fuse のサインオン URL に直接移動し、アプリケーション側からサインイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Fuse] タイルを選択すると、Fuse のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fuze-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Fuze を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fuze-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: ユーザー アカウントを Fuze に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、ユーザーやグループを [Fuze](https://www.fuze.com/) に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するために Fuze とMicrosoft Entra IDで実行する手順を示することです。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Fuze でユーザーを作成する
- アクセスが不要になったときに Fuze でユーザーを削除する
- Microsoft Entra IDと Fuze の間でユーザー属性の同期を維持する
- Fuze への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fuze-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- [プロビジョニングを構成する権限](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ([Application Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [Application Owner](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications) など)。
- [Fuzeのテナント](https://www.fuze.com/)。
- Admin アクセス許可がある Fuze のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとFuzeの間で[マッピングするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Fuze を構成する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Fuze を構成する前に、Fuze で SCIM プロビジョニングを有効にする必要があります。

1. 以下の必要な情報について、Fuze 担当者に問い合わせることから始めます。

    - 現在社内で使用されている Fuze 製品 SKU の一覧。
    - 会社の所在地に対応する場所コードの一覧。
    - 会社の部門コードの一覧。
2. そのような SKU およびコードは、Fuze の契約書および構成に関するドキュメントを調べるか、または Fuze の担当者に連絡することで確認することができます。
3. 要件を受け取ると、Fuze 担当者から、統合を有効にするために必要な Fuze 認証トークンが提供されます。 この値は、Fuze アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Fuze を追加する

Microsoft Entra アプリケーション ギャラリーから Fuze を追加して、Fuze へのプロビジョニングの管理を開始します。 SSO のために Fuze を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Fuze への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Fuze でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Fuze の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: エンタープライズアプリケーションブレード]
3. アプリケーションの一覧で [ **Fuze**] を選択します。

    [Image: アプリケーションの一覧の Fuze リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Fuze テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Fuze に接続できることを確認します。 接続に失敗した場合は、Fuze アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Fuze に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Fuze のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Fuze API でサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | 名前.名 | 糸 |
    | 名前.姓 | 糸 |
    | emails[type eq "仕事"].value | 糸 |
    | 活動中 | ブール値 |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### コネクタの制限事項

- Fuze では、 **エンタイトルメント**と呼ばれるカスタム SCIM 属性がサポートされています。 これらの属性は作成することはできますが、更新することはできません。
- Fuze SCIM API では、userName 属性のフィルター処理はサポートされていません。 その結果、userName 属性を持たないが、Microsoft Entra IDの userPrincipalName と一致する電子メールと存在する既存のユーザーを同期しようとすると、ログにエラーが表示されることがあります。

### 変更ログ

- 06/15/2020 - 統合のレート制限を、10 要求/秒に調整しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fuze-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Fuze を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fuze-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Fuze 間のシングル サインオンを構成する方法について説明します。

この記事では、Fuze と Microsoft Entra ID を統合する方法について説明します。 Fuze を Microsoft Entra ID と統合すると、次のことが可能になります。

- Fuze にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Fuze に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fuze でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Fuze では、**SP** Initiated SSO がサポートされます。
- Fuze では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Fuze では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fuze-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Fuze を追加する

Microsoft Entra ID への Fuze の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Fuze を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Fuze**」と入力します。
4. 結果パネルから **[Fuze]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fuze に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、Fuze に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Fuze の関連ユーザーとの間にリンク関係を確立する必要があります。

Fuze に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fuze SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Fuze テスト ユーザーの作成** - Fuze で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Fuze]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.thinkingphones.com/jetspeed/portal/`
6. [ **SAML を使用したシングル Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Fuze のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fuze SSO の構成

**Fuze** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Fuze サポート チーム](https://www.fuze.com/support)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Fuze のテスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Fuze に作成します。 Fuze では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Fuze にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

Fuze では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fuze-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Fuze サインオン URL にリダイレクトされます。
- Fuze のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Fuze] タイルを選択すると、このオプションは Fuze のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/g-suite-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Google Cloud/Google Workspace を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/g-suite-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-09
- Summary: Microsoft Entra ID から Google Cloud または Google Workspace にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Google (Google Cloud または Google Workspace) と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [Google Workspace](https://workspace.google.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

注

この記事では、Google G Suite の Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。これは、以前の Google Workspace の名前です。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- G Suite でユーザーを作成する
- アクセスが不要になったときに G Suite のユーザーを削除します (注: 同期スコープからユーザーを削除しても、G Suite 内のオブジェクトは削除されません)
- Microsoft Entra ID と G Suite の間でユーザー属性の同期を維持する
- G Suite でグループとグループ メンバーシップをプロビジョニングする
- G Suite への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/google-apps-tutorial) (推奨)

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [G Suite テナント](https://gsuite.google.com/pricing.html)
- 管理者アクセス許可を持つ G Suite 上のユーザー アカウント。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と G Suite 間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように G Suite を構成する

Microsoft Entra ID での自動ユーザー プロビジョニング用に G Suite を構成する前に、G Suite 上で SCIM プロビジョニングを有効にする必要があります。

1. 管理者アカウントで [G Suite 管理コンソール](https://admin.google.com/) にサインインし、 **メイン メニュー** を選択して [セキュリティ] を選択 **します**。 表示されない場合は、 [**詳細を表示**] メニューの下に隠れている可能性があります。

    [Image: G Suite のセキュリティ]

    [Image: G Suiteをさらに表示]
2. **[Security -&gt; Access and data control -&gt; API Controls**] に移動します。[**内部のドメイン所有アプリを信頼**する] チェック ボックスをオンにし、[保存] を選択**します**。

    [Image: G Suite の API]

    重要

    G Suite にプロビジョニングしようとしているすべてのユーザーについて、その Microsoft Entra ID でのユーザー名がカスタム ドメインに関連付けられている**必要があります**。 たとえば、 bob@contoso.onmicrosoft.com のようなユーザー名は、G Suite では受け入れられません。 bob@contoso.com のようなユーザー名は使用できます。 既存のユーザーのドメインは、[ここ](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)にある手順に従って変更できます。
3. Microsoft Entra ID で目的のカスタム ドメインを追加して検証したら、それらを G Suite で再度検証する必要があります。 G Suite でドメインを検証するには、次の手順を参照してください。

    1. [G Suite 管理コンソール](https://admin.google.com/)で、**[アカウント] -&gt; [ドメイン] -&gt;[ドメインの管理]** に移動します。

        [Image: G Suite のドメイン]
    2. [ドメインの管理] ページで、[ **ドメインの追加**] を選択します。

        [Image: G Suite のドメインの追加]
    3. [別のドメインを追加] を選択し、追加するドメインの名前を入力します。

        [Image: G Suite のドメイン確認]
    4. [ **ドメインの&の開始確認の追加 ** ] を選択します。 次に、手順に従って、ドメイン名を所有していることを確認します。 Google でドメインを検証する方法に関する包括的な手順については、「[サイトの所有権を確認する](https://support.google.com/webmasters/answer/35179)」を参照してください。
    5. G Suite に追加しようとしているすべてのその他のドメインについて、前の手順を繰り返します。
4. 次に、G Suite でユーザー プロビジョニングを管理するためにどの管理者アカウントを使用するかを決定します。 **[アカウント]-&gt;[管理者 ロール]** に移動します。

    [Image: G Suite の管理者]
5. そのアカウントの **[Admin role] (管理者ロール)** で、そのロールの **[特権]** を編集します。 このアカウントをプロビジョニングに使用できるように、 **[Admin API Privileges](管理 API の権限)** がすべて有効になっていることを確認します。

    [Image: G Suite の管理者権限]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから G Suite を追加する

Microsoft Entra アプリケーション ギャラリーから G Suite を追加して、G Suite へのプロビジョニングの管理を開始します。 SSO のために G Suite を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングの対象となるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: G Suite への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて TestApp 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

注

G Suite の Directory API エンドポイントの詳細については、[Directory API のリファレンス ドキュメント](https://developers.google.com/admin-sdk/directory)を参照してください。

#### Microsoft Entra ID で G Suite に対する自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: [エンタープライズ アプリケーション] ブレード]

    [Image: [すべてのアプリケーション] ブレード]
3. アプリケーションの一覧で **[G Suite]** を選択します。

    [Image: アプリケーションの一覧の G Suite のリンク]
4. [プロビジョニング] タブ **を** 選択します。[ **作業の開始] を選択します**。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、[ **承認**] を選択します。 新しいブラウザー ウィンドウで Google 承認ダイアログ ボックスにリダイレクトされます。

    [Image: G Suite の承認]
7. Microsoft Entra に G Suite テナントを変更するためのアクセス許可を付与することを確認します。 **[Accept](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/承認)** を選択します。

    [Image: G Suite のテナントの承認]
8. **[テスト接続]** を選択して、Microsoft Entra ID が G Suite に接続できることを確認します。 接続できない場合は、使用中の G Suite アカウントに管理者アクセス許可があることを確認してから、もう一度試します。 その後、**承認**手順を再び試します。
9. [ **作成]** を選択して構成を作成します。
10. [**概要**] ページで **[プロパティ**] を選択します。
11. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
12. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から G Suite に同期されるユーザー属性を確認します。 **[保存]** ボタンをクリックして変更をコミットします。

注

現在、G Suite プロビジョニングでは、一致する属性として primaryEmail の使用のみがサポートされています。

| 属性 | タイプ |
| --- | --- |
| 主要なメールアドレス | 糸 |
| 関係。[type eq "manager"].value | 糸 |
| name.familyName | 糸 |
| name.givenName | 糸 |
| suspended | 糸 |
| externalIds。[type eq "custom"].value | 糸 |
| externalIds。[type eq "organization"].value | 糸 |
| アドレス。[種類 eq "勤務"].国 | 糸 |
| アドレス。[type eq "work"].streetAddress | 糸 |
| アドレス。[type eq "work"].region | 糸 |
| アドレス。[type eq "work"].locality | 糸 |
| アドレス。[type eq "work"].postalCode | 糸 |
| 電子メール。[type eq "work"].アドレス | 糸 |
| 組織。[type eq "work"].部門 | 糸 |
| 組織。[type eq "work"].title | 糸 |
| phoneNumbers。[type eq "work"].value | 糸 |
| phoneNumbers。[type eq "mobile"].value | 糸 |
| phoneNumbers。[type eq "work\_fax"].value | 糸 |
| 電子メール。[type eq "work"].アドレス | 糸 |
| 組織。[type eq "work"].部門 | 糸 |
| 組織。[type eq "work"].title | 糸 |
| アドレス。[type eq "home"].国 | 糸 |
| アドレス。[type eq "home"].フォーマット済み | 糸 |
| アドレス。[type eq "home"].ローカリティ | 糸 |
| 住所。[type eq "home"].郵便番号 | 糸 |
| アドレス。[type eq "home"].region | 糸 |
| アドレス。[type eq "home"].streetAddress | 糸 |
| アドレス。[type eq "other"].country | 糸 |
| アドレス。[type eq "other"].フォーマット済み | 糸 |
| アドレス。「type eq "other"」のlocality | 糸 |
| アドレス。[type eq "other"].postalCode | 糸 |
| アドレス。[type eq "other"].region | 糸 |
| 住所。[タイプ eq "その他"].ストリートアドレス | 糸 |
| アドレス。[type eq "work"].formatted | 糸 |
| 次回ログイン時にパスワードを変更する | 糸 |
| 電子メール。[type eq "ホーム"].address | 糸 |
| 電子メール。[type eq "other"].address | 糸 |
| externalIds。[type eq "account"].value | 糸 |
| externalIds。[type eq "custom"].customType | 糸 |
| externalIds。［type eq "customer"］。value | 糸 |
| externalIds。[type eq "login\_id"].value | 糸 |
| 外部識別子。[タイプ eq "ネットワーク"].値 | 糸 |
| gender.type | 糸 |
| 生成された不変のID | 糸 |
| 識別子 | 糸 |
| ims。[type eq "home"].protocol | 糸 |
| Ims。[type eq "other"].protocol | 糸 |
| ims。[type eq "work"].protocol | 糸 |
| グローバルアドレスリストに含める | 糸 |
| ipWhitelisted | 糸 |
| 組織。[type eq "school"].costCenter | 糸 |
| 組織。[タイプ＝「学校」]。部門 | 糸 |
| 組織。[type eq "school"].domain | 糸 |
| 組織。[type eq "school"].fullTimeEquivalent | 糸 |
| 組織。[type eq "school"].場所 | 糸 |
| 組織。[type eq "school"].name | 糸 |
| 組織。[type eq "school"].symbol | 糸 |
| 組織。[type eq "school"].title | 糸 |
| 組織。[type eq "work"]. コストセンター | 糸 |
| 組織。[type eq "work"].domain | 糸 |
| 組織。[type eq "work"].フルタイム同等 | 糸 |
| 組織。[type eq "work"].location | 糸 |
| 組織。[type eq "work"].name | 糸 |
| 組織。[type eq "work"].symbol | 糸 |
| OrgUnitPath | 糸 |
| phoneNumbers。[type eq "home"].value | 糸 |
| phoneNumbers。[type eq "other"].value | 糸 |
| Web サイト。[type eq "home"].value | 糸 |
| Web サイト。[type eq "other"].value | 糸 |
| Web サイト。[type eq "work"].value | 糸 |

1. **[グループ]** を選びます。
2. **[属性マッピング]** セクションで、Microsoft Entra ID から G Suite に同期されるグループ属性を確認します。 **[Matching] (照合)** プロパティとして選択されている属性は、更新操作のために G Suite のグループを照合するために使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | メール | 糸 |
    | Members | 糸 |
    | 名前 | 糸 |
    | 説明 | 糸 |
3. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
4. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、組織内でより広範に展開する前に、少数のユーザーとの同期を検証します。
5. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

注

ユーザーが Microsoft Entra ユーザーのメール アドレスを使用して既存の個人/コンシューマー アカウントを既に持っている場合は、ディレクトリ同期を実行する前に Google 転送ツールを使用して解決できる問題が発生する可能性があります。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### トラブルシューティングのヒント

- 同期スコープからユーザーを削除すると、GSuite で無効になりますが、そのユーザーが G Suite から削除されることはありません

### グループの PIM を使用した Just-In-Time (JIT) アプリケーション アクセス

グループの PIM を使用すると、Google Cloud / Google Workspace のグループへの Just-In-Time アクセス権を提供し、Google Cloud / Google Workspace の特権グループに永続的にアクセスできるユーザーの数を減らすことができます。

**SSO とプロビジョニング用にエンタープライズ アプリケーションを構成する**

1. テナントに Google Cloud/Google Workspace を追加し、この記事の説明に従ってプロビジョニング用に構成し、プロビジョニングを開始します。
2. Google Cloud / Google Workspace の[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/google-apps-tutorial)を構成します。
3. すべてのユーザーがアプリケーションにアクセスできるようにする[グループ](https://learn.microsoft.com/ja-jp/azure/active-directory/fundamentals/how-to-manage-groups)を作成します。
4. グループを Google Cloud / Google Workspace アプリケーションに割り当てます。
5. 前の手順で作成したグループの直接メンバーとしてテスト ユーザーを割り当てるか、アクセス パッケージを使用してグループへのアクセス権を付与します。 このグループは、Google Cloud / Google Workspace での永続的な管理者以外のアクセスに使用できます。

**グループの PIM を有効にする**

1. Microsoft Entra ID で 2 つ目のグループを作成します。 このグループは、Google Cloud / Google Workspace での管理者権限へのアクセスを提供します。
2. グループを [Microsoft Entra PIM の管理](https://learn.microsoft.com/ja-jp/azure/active-directory/privileged-identity-management/groups-discover-groups)下に置きます。
3. ロールがメンバーに設定されている [PIM のグループの対象](https://learn.microsoft.com/ja-jp/azure/active-directory/privileged-identity-management/groups-assign-member-owner)としてテスト ユーザーを割り当てます。
4. 2 番目のグループを Google Cloud / Google Workspace アプリケーションに割り当てます。
5. オンデマンド プロビジョニングを使用して、Google Cloud / Google Workspace でグループを作成します。
6. Google Cloud / Google Workspace にサインインし、2 番目のグループに対して管理タスクを実行するために必要なアクセス許可を割り当てます。

PIM のグループの対象となったエンド ユーザーは、[グループ メンバーシップをアクティブ化する](https://learn.microsoft.com/ja-jp/azure/active-directory/privileged-identity-management/groups-activate-roles#activate-a-role)ことで、Google Cloud / Google Workspace のグループへの JIT アクセスを取得できるようになりました。 割り当ての有効期限が切れると、ユーザーは Google Cloud / Google Workspace のグループから削除されます。 次の増分サイクル中に、プロビジョニング サービスはグループからユーザーを再度削除しようとします。 これにより、プロビジョニング ログにエラーが発生する可能性があります。 このエラーは、グループ メンバーシップが既に削除されているために発生します。 このエラー メッセージは無視することができます。

- ユーザーがアプリケーションにプロビジョニングされるまでにかかる時間
    - Microsoft Entra ID Privileged Identity Management (PIM) を使用したグループ メンバーシップのアクティブ化以外で、ユーザーを Microsoft Entra ID のグループに追加するとき:
        - グループ メンバーシップは、次の同期サイクルの間に、アプリケーションでプロビジョニングされます。 同期サイクルは 40 分ごとに実行されます。
    - ユーザーが Microsoft Entra ID PIM でグループ メンバーシップをアクティブ化するとき:
        - グループ メンバーシップは 2 から 10 分でプロビジョニングされます。 一度に行われる要求の数が多い場合、10 秒あたり 5 つの要求に調整されます。
        - 特定のアプリケーションのグループ メンバーシップをアクティブ化しようとするユーザーのうち、10 秒の期間内の最初の 5 人については、2 分から 10 分以内にアプリケーションでグループ メンバーシップがプロビジョニングされます。
        - 特定のアプリケーションのグループ メンバーシップをアクティブ化する 10 秒以内の 6 番目のユーザーの場合、グループ メンバーシップは次の同期サイクルでアプリケーションにプロビジョニングされます。 同期サイクルは 40 分ごとに実行されます。 調整の制限は、エンタープライズ アプリケーションごとに行われます。
- ユーザーが Google Cloud / Google Workspace の必要なグループにアクセスできない場合は、PIM のログとプロビジョニングのログを調べて、グループ メンバーシップが正常に更新されたことを確認してください。 ターゲット アプリケーションの設計方法によっては、グループ メンバーシップがアプリケーションで有効になるのに時間がかかる場合があります。
- [Azure Monitor](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics) を使うと、お客様は障害に対するアラートを作成できます。

### ログの変更

- 10/17/2020 - G Suite のその他のユーザーおよびグループ属性に対するサポートが追加されました。
- 10/17/2020 - G Suite ターゲットの属性名が、[ここで](https://developers.google.com/admin-sdk/directory)定義されている内容に一致するように更新されました。
- 10/17/2020 - 既定の属性マッピングが更新されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/gaggleamp-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に GaggleAMP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/gaggleamp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GaggleAMP の間のシングル サインオンを構成する方法について説明します。

この記事では、GaggleAMP と Microsoft Entra ID を統合する方法について説明します。 GaggleAMP を Microsoft Entra ID と統合すると、次のことが可能になります。

- GaggleAMP にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで GaggleAMP に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GaggleAMP でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- GaggleAMP では、**SP** および **IDP** Initiated SSO がサポートされます。
- GaggleAMP では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの GaggleAMP の追加

Microsoft Entra ID への GaggleAMP の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に GaggleAMP を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**GaggleAMP**」と入力します。
4. 結果パネルから **[GaggleAMP]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GaggleAMP に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、GaggleAMP に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、GaggleAMP での関連ユーザーとの間にリンク関係を確立する必要があります。

GaggleAMP に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GaggleAMP の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GaggleAMPのテストユーザーを作成** - Microsoft EntraにおけるB.Simonのユーザー表現にリンクされた、GaggleAMP内のB.Simonの対応ユーザーを持たせるため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[GaggleAMP]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://accounts.gaggleamp.com/auth/saml/callback`
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[GaggleAMP のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GaggleAMP の SSO の構成

1. 別のブラウザー インスタンスで、Gaggle サポート チームによって作成された SAML SSO ページ (例: `https://accounts.gaggleamp.com/saml_configurations/oXH8sQcP79dOzgFPqrMTyw/edit` ) に移動します。
2. **[SAML SSO]** ページで、次の手順を実行します。

    [Image: GaggleAMP シングル サインオン]

    a. **[ID プロバイダー]** ドロップダウン メニューから **[その他]** を選択します。

    b。 **[ID プロバイダー発行者]** ボックスに、**[Microsoft Entra 識別子]** の値を貼り付けます。

    c. **[ID プロバイダーのシングル サインオン URL]** テキスト ボックスに、**[ログイン URL]** の値を貼り付けます。

    d. ダウンロードした**証明書 (Base64)** ファイルをメモ帳で開き、その内容をクリップボードにコピーして、**[X.509 Certificate](X.509 証明書)** ボックスに貼り付けます。

    e. **保存** を選択します。

#### GaggleAMP のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを GaggleAMP に作成します。 GaggleAMP では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 GaggleAMP にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Gaggle の招待ページにアクセスすると、マネージャー ビューのメニュー オプション **[メンバー] &gt; [招待]** に固有のリンクが表示されます。
- [ **SAML でサインイン** ] ボタンを選択して、そこからログイン フローを開始します。

##### IdP から開始:

- [ **このアプリケーションをテスト**する] を選択すると、GaggleAMP に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで GaggleAMP タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した GaggleAMP に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/gainsight-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Gainsight を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/gainsight-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Gainsight の間のシングル サインオンを構成する方法について説明します。

この記事では、Gainsight と Microsoft Entra ID を統合する方法について説明します。 ユーザー アクセスを管理し、Gainsight によるシングル サインオンを有効にするには、Microsoft Entra ID を使用します。 Gainsight のサブスクリプションが必要です。 Gainsight を Microsoft Entra ID と統合すると、次のことが可能になります。

- Gainsight にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Gainsight に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Gainsight 用の Microsoft Entra シングル サインオンを構成してテストします。 Gainsight では、 **SP** と **IDP** によって開始されるシングル サインオンの両方がサポートされます。

### [前提条件]

Gainsight を Microsoft Entra ID と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Gainsight でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Gainsight アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Gainsight SAML を追加する

Microsoft Entra アプリケーション ギャラリーから Gainsight SAML を追加して、Gainsight でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Gainsight**&gt;**シングルサインオン**にブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. `https://gainsight.com`の**識別子 (エンティティ ID**) と**応答 URL (Assertion Consumer Service URL**) に 、() のような任意のダミー URL を指定します。
5. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
6. [ **Gainsight SAML のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]
7. Gainsight 側で、[ **ユーザー管理** ] に移動し、[ **認証** ] タブを選択し、新しい **SAML** 認証を作成します。

### Gainsight で SAML 2.0 認証をセットアップする

注

SAML 2.0 認証を使用すると、ユーザーは Microsoft Entra ID 経由で Gainsight にログインできます。 SAML 2.0 を介して認証するように Gainsight を構成すると、Gainsight にアクセスするユーザーにユーザー名またはパスワードの入力を求めるメッセージは表示されなくなります。 代わりに、Gainsight と Microsoft Entra ID の間で情報のやり取りが行われ、これによりユーザーが Gainsight にアクセスできるようになります。

**SAML 2.0 認証を構成するには:**

1. **Gainsight** 企業サイトに管理者としてログインします。
2. 左側のメニューで **検索バー** を選択し、[ **ユーザー管理**] を選択します。

    [Image: Gainsight Left Nav の検索バーを示すスクリーンショット。]
3. [ **ユーザー管理** ] ページで、[ **認証** ] タブに移動し、[ **認証の追加**&gt;**SAML**] を選択します。

    [Image: [Gainsight User Management Authentication](Gainsight ユーザー管理認証) ページを示すスクリーンショット。]
4. **[SAML メカニズム**] ページで、次の手順を実行します。

    [Image: Gainsight で SAML 構成を編集する方法を示すスクリーンショット。]

    1. テキスト ボックスに一意の接続 **名** を入力します。
    2. テキスト ボックスに有効な **電子メール ドメイン** を入力します。
    3. [ **サインイン URL** ] ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。
    4. [ **サインアウト URL** ] ボックスに、前にコピーした **ログアウト URL** の値を貼り付けます。
    5. ダウンロードした**証明書 (Base64)** を開き、[**参照**] オプションを選択して**証明書**にアップロードします。
    6. **[保存] を選択します**。
    7. 新しい **SAML** 認証を再度開き、新しく作成した接続の編集を選択し、 **メタデータ**をダウンロードします。 お気に入りのエディターで **メタデータ** ファイルを開き、 **entityID** と **Assertion Consumer Service の場所の URL をコピーします**。

    注

    SAML の作成の詳細については、 [GAINSIGHT SAML](https://support.gainsight.com/Gainsight_NXT/01Onboarding_and_Implementation/Onboarding_for_Gainsight_NXT/Login_and_Permissions/03Gainsight_Authentication) を参照してください。
5. Azure portal に戻り、[SAML での **シングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
6. [ **基本的な SAML 構成]** セクションで、手順 4 で取得した値を使用して、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** ボックスに、次のいずれかのパターンを使用して値を入力します。

    | **識別子** |
    | --- |
    | `urn:auth0:gainsight:<ID>` |
    | `urn:auth0:gainsight-eu:<ID>` |

    b。 **[応答 URL (Assertion Consumer Service URL)]** ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://secured.gainsightcloud.com/login/callback connection=<ID>` |
    | `https://secured.eu.gainsightcloud.com/login/callback?connection=<ID>` |
7. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://secured.gainsightcloud.com/samlp/<ID>` |
    | `https://secured.eu.gainsightcloud.com/samlp/<ID>` |

### Gainsight のテスト ユーザーを作成する

1. 別の Web ブラウザー ウィンドウで、Gainsight の Web サイトに管理者としてサインインします。
2. [ **ユーザー管理** ] ページで、[ **ユーザー**&gt;**ユーザーの追加**] に移動します。

    [Image: Gainsight でユーザーを追加する方法を示すスクリーンショット。]
3. 必須フィールドに入力し、[ **保存]** を選択します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Gainsight のサインオン URL にリダイレクトされます。
- Gainsight のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Gainsight に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Gainsight] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Gainsight に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/gamba-tutorial"} -->
## gamba! を構成する Microsoft Entra ID を使用したシングル サインオン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/gamba-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と gamba! 間のシングル サインオンを構成する方法について説明します。

この記事では、gamba! を統合する方法について説明します。 と Microsoft Entra ID を統合します。 gamba! を Microsoft Entra ID を使用することで、次のことができます。

- gamba! にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Azure AD アカウントを使用して gamba! に自動的に と Microsoft Entra アカウントを統合します。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ガンバ！ でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ガンバ！ では、**SP Initiated SSO** と **IDP Initiated SSO** がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの gamba! ギャラリーから

gamba! の Microsoft Entra ID への統合を構成するには、gamba! をギャラリーからマネージド SaaS アプリ ギャラリーからマネージド SaaS アプリのリストに追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**gamba!**」と入力します。
4. 結果のパネルから **[gamba!]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### gamba! に対する Microsoft Entra SSO を構成してテストする

gamba! での Microsoft Entra SSO を構成してテストする **B.Simon** というテスト ユーザーを使用します。 SSO が機能するためには、Microsoft Entra ユーザーと gamba! の関連ユーザーとの間にリンク関係を確立する必要があります。

gamba! に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **gamba! SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **gamba! のテスト ユーザーの作成** - gamba! で B.Simon に対応するユーザーを作成します。 これは、Microsoft Entra のユーザー表現にリンクされています。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[gamba!]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.getgamba.com/n/#/login`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### gamba! を構成する SSO

**gamba!** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** とアプリケーション構成からコピーした適切な URL を [gamba! サポート チーム](mailto:customers@getgamba.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### gamba! テスト ユーザー テストユーザー

このセクションでは、gamba! で Britta Simon というユーザーを作成します。 gamba! にユーザーを追加するには、[gamba! サポート チーム](mailto:customers@getgamba.com)と連携します。 プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションが gamba にリダイレクトされます。 サインオン URL にリダイレクトされます。
- gamba! サインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、自動的に gamba にサインインします。 に自動的にサインインされます。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [gamba!] タイルをクリックすると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した gamba! に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/genius-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用にGenius を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/genius-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID とGenius の間でシングル サインオンを構成する方法について説明します。

この記事では、Genius と Microsoft Entra ID を統合する方法について説明します。 Genius と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Genius へのアクセスを管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して自動的にGeniusにサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Genius でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Genius は、SP  と IDP  の両方の初回起動 SSO をサポートします。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからGeniusを追加する

Microsoft Entra ID へのGenius の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧にGeniusを追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Genius**」と入力します。
4. 結果のパネルから **[Genius**] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Genius の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Genius に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーとGenius の関連ユーザーとの間にリンク関係を確立する必要があります。

Genius に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Genius SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Genius のテスト ユーザーの作成** -Genius で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Genius**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **基本的な SAML 構成** セクションでは、アプリケーションが事前に構成されており、必要な URL が既に Microsoft Entra に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. あなたが望む場合は、**SP**開始モードでアプリケーションを構成するために、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、URL: `https://genius.avoxi.com/` を入力します。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Genius SSO の構成

Genius **側** シングル サインオンを構成するには、**アプリフェデレーション メタデータ URL** を [のGenius サポート チーム](mailto:service@avoxi.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Genius のテスト ユーザーの作成

このセクションでは、Genius で B.Simon というユーザーを作成します。 [のGeniusサポートチームと協力して](mailto:service@avoxi.com)、Geniusプラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログインフローを開始できるGeniusのサインオンURLにリダイレクトします。
- Genius のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定したGeniusに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Genius] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したGeniusに自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/getabstract-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に getAbstract を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/getabstract-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID から getAbstract へのユーザー アカウントの自動プロビジョニングおよび自動プロビジョニング解除を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために getAbstract と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して [、getAbstract](https://www.getabstract.com) に対してユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用してサービスとしてのソフトウェア (SaaS) アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)する」を参照してください。

### サポートされている機能

- getAbstract でユーザーを作成する。
- アクセスが不要になった場合に getAbstract のユーザーを削除する。
- Microsoft Entra ID と getAbstract との間でユーザー属性の同期を維持する。
- getAbstract でグループとグループ メンバーシップをプロビジョニングする。
- [シングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/getabstract-tutorial) を getAbstract に対して有効にすることをお勧めします。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- getAbstract テナント (getAbstract コーポレート ライセンス)。
- Microsoft Entra テナントと getAbstract テナントで SSO を有効にする。
- getAbstract に対して承認およびクロスドメイン ID 管理システム (SCIM) を有効にする。 (b2b.itsupport@getabstract.com に電子メールを送信します。)

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と getAbstract の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように getAbstract を構成する

1. getAbstract にサインインします。
2. 右上隅にあるユーザー アイコンを選択し、[ **マイ セントラル管理者** ] オプションを選択します。

    [Image: getAbstract My Central Admin を示すスクリーンショット。]
3. 左側のメニューで、[ **ユーザー管理** ] を選択し、[ **scim の構成** ] ボタンを選択します。

    [Image: getAbstract SCIM 管理者を示すスクリーンショット。]
4. [ **移動**] を選択します。

    [Image: getAbstract SCIM クライアント ID を示すスクリーンショット。]
5. [ **新しいトークンの生成** ] ボタンを選択します。

    [Image: getAbstract SCIM トークン 1 を示すスクリーンショット。]
6. 確認できる場合は、[ **新しいトークンの生成** ] ボタンを選択します。 それ以外の場合は、[ **キャンセル]** を選択します。

    [Image: getAbstract SCIM トークン 2 を示すスクリーンショット。]
7. 最後に、クリップボードへのコピー アイコンを選択するか、トークン全体を選択してコピーします。 また、テナント URL (ベース URL) が `https://www.getabstract.com/api/scim/v2` であることも書き留めてください。 これらの値は、getAbstract アプリケーションの [**プロビジョニング**] タブの [**シークレット トークン**] ボックスと [**テナント URL**] ボックスに入力されます。

    [Image: getAbstract SCIM トークン 3 を示すスクリーンショット。]

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから getAbstract を追加する

Microsoft Entra アプリケーション ギャラリーから getAbstract を追加して、getAbstract へのプロビジョニングの管理を開始します。 SSO のために getAbstract を以前に設定している場合は、その同じアプリケーションを使用できます。 統合を最初にテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [このクイック スタート](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: getAbstract への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて TestApp 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で getAbstract に対する自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。

    [Image: [エンタープライズ アプリケーション] ウィンドウを示すスクリーンショット。]
3. アプリケーションの一覧で **getAbstract** を選択します。

    [Image: アプリケーションの一覧の getAbstract リンクを示すスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブを示すスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、getAbstract テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が getAbstract に接続できることを確認します。 接続に失敗した場合は、getAbstract アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から getAbstract に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で getAbstract のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、getAbstract API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存] を** 選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | externalId | 糸 |  |
    | 優先言語 | 糸 |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から getAbstract に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で getAbstract のグループとの照合に使用されます。 [ **保存] を** 選択して変更をコミットします。

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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/getabstract-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Getabstract を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/getabstract-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Getabstract 間のシングル サインオンを構成する方法について説明します。

この記事では、Getabstract と Microsoft Entra ID を統合する方法について説明します。 Getabstract を Microsoft Entra ID と統合すると、次のことが可能になります。

- Getabstract にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して Getabstract に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

Getabstract は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- Getabstract でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Getabstract では、**SP と IDP** Initiated SSO がサポートされます。
- Getabstract では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Getabstract では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/getabstract-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Getabstract を追加する

Microsoft Entra ID への Getabstract の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Getabstract を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Getabstract**」と入力します。
4. 結果パネルから **[Getabstract]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Getabstract 向けに Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Getabstract に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと Getabstract の関連ユーザーとの間にリンク関係を確立する必要があります。

Getabstract に対する Microsoft Entra の SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Getabstract の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Getabstract のテスト ユーザーの作成** - Getabstract で、Microsoft Entra のユーザー Britta Simon のリンク相手とするユーザーを用意し、これらのユーザーの間にリンクを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Getabstract**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを**IDP** イニシエートモードで構成する場合は、次の手順を実行します。

    a。 **[識別子]** ボックスに、 という URL を入力します。

    ステージ/pre\_production の場合: `https://int.getabstract.com`

    運用環境の場合: `https://www.getabstract.com`

    b。 **[応答 URL]** ボックスに、URL として「」と入力します。

    ステージ/pre\_production の場合: `https://int.getabstract.com/ACS.do`

    運用環境の場合: `https://www.getabstract.com/ACS.do`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、 のパターンを使用して URL を入力します。

    ステージ/pre\_production の場合: `https://int.getabstract.com/portal/<org_username>`

    運用環境の場合: `https://www.getabstract.com/portal/<org_username>`

    注

    この値は実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値を取得するには、[Getabstract クライアント サポート チーム](https://www.getabstract.com/en/contact)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用したシングル Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Getabstract のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Getabstract SSO を構成する

**Getabstract** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーションの構成からコピーした適切な URL を [Getabstract サポート チーム](https://www.getabstract.com/en/contact)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Getabstract のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Getabstract に作成します。 Getabstract では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Getabstract にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

Getabstract では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/getabstract-provisioning-tutorial)をご覧ください。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Getabstract のサインオン URL にリダイレクトされます。
- Getabstract のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Getabstract に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Getabstract] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Getabstract に自動的にサインインされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/getthere-tutorial"} -->
## Microsoft Entra ID で GetThere for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/getthere-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GetThere 間のシングル サインオンを構成する方法について説明します。

この記事では、GetThere と Microsoft Entra ID を統合する方法について説明します。 GetThere を Microsoft Entra ID と統合すると、次のことが可能になります。

- GetThere にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで GetThere に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GetThere でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- GetThere では、**IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの GetThere の追加

Microsoft Entra ID への GetThere の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に GetThere を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**GetThere**」と入力します。
4. 結果のパネルから **[GetThere]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GetThere に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、GetThere に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと GetThere の関連ユーザー間にリンク関係を確立する必要があります。

GetThere に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GetThere SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GetThere のテスト ユーザーの作成** - GetThere で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**GetThere**&gt;**シングルサインオン**にアクセスしてください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次の手順に従います。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **識別子** |
    | --- |
    | `getthere.com` |
    | `http://idp.getthere.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://wx1.getthere.net/login/saml/post.act` |
    | `https://gtx2-gcte2.getthere.net/login/saml/post.act` |
    | `https://gtx2-gcte2.getthere.net/login/saml/ssoaasvalidate.act` |
    | `https://wx1.getthere.net/login/saml/ssoaavalidate.act` |
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[GetThere のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GetThere SSO の構成

**GetThere** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [GetThere サポート チーム](mailto:dataintegration@serko.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### GetThere のテスト ユーザーの作成

このセクションでは、GetThere で B.Simon というユーザーを作成します。 [GetThere サポート チーム](mailto:dataintegration@serko.com)と連携し、GetThere プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した GetThere に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [GetThere] タイルを選択すると、SSO を設定した GetThere に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/getty-images-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Getty Images を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/getty-images-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Getty Images の間のシングル サインオンを構成する方法について説明します。

この記事では、Getty Images と Microsoft Entra ID を統合する方法について説明します。 Getty Images は、クリエイティブなストック写真、ベクター アート イラスト、ストック写真の世界最高峰の写真ライブラリから、次のプロジェクトに最適な画像を見つけます。 Getty Images と Microsoft Entra ID を統合すると、次のことができます。

- Getty Images にアクセスできるユーザー Microsoft Entra ID を制御する。
- ユーザーが Microsoft Entra アカウントで Getty Images に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Getty Images 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Getty Images では、**SP** と **IDP** によって開始されるシングル サインオンと、**Just In Time** ユーザー プロビジョニングの両方がサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Getty Images と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Getty Images でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Getty Images アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Getty Images を追加する

Microsoft Entra アプリケーション ギャラリーから Getty Images を追加して、Getty Images でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Getty Images**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、URL を入力します。 `https://gettyimages.com/`

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://www.gettyimages.com/sign-in/sso/acs` |
    | `https://www.gettyimages.<Environment>/sign-in/sso/acs` |
    | `https://www.gettyimages.com.<Environment>/sign-in/sso/acs` |
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://www.gettyimages.com/sign-in/sso`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、[Getty Images サポート チーム](mailto:support@gettyimages.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Getty Images アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. その他に、Getty Images アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | groupid | ユーザー.グループ |

    注

    groupid 要求は、 [Getty Images サポート チーム](mailto:support@gettyimages.com)によって提供される定数値です。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### Getty Images の SSO を構成する

**Getty Images** 側でシングル サインオンを構成するには、**アプリケーション フェデレーション メタデータ URL** を [Getty Images サポート チーム](mailto:support@gettyimages.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。 詳細については、 [この](https://developers.gettyimages.com/single-sign-on/) リンクを参照してください。

#### Getty Images テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Getty Images に作成します。 Getty Images では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Getty Images にユーザーがまだ存在していない場合、一般に認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Getty Images のサインオン URL にリダイレクトされます。
- Getty Images のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Getty Images に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Getty Images] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Getty Images に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/gigya-tutorial"} -->
## Microsoft Entra ID で Gigya for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/gigya-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Gigya の間にシングル サインオンを構成する方法について説明します。

この記事では、Gigya と Microsoft Entra ID を統合する方法について説明します。 Gigya を Microsoft Entra ID と統合すると、次のことができます。

- Gigya にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Gigya に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Gigya でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Gigya では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの Gigya の追加

Microsoft Entra ID への Gigya の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Gigya を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Gigya」**と入力します。
4. 結果パネルから **Gigya** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Gigya 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Gigya に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Gigya の関連ユーザーとの間にリンク関係を確立する必要があります。

Gigya に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Gigya の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Gigya のテスト ユーザーの作成** - Gigya で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Gigya**&gt;**シングル サインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ａ. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `http://<companyname>.gigya.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://fidm.gigya.com/saml/v2.0/<companyname>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、Gigya クライアント サポート チーム](https://developers.gigya.com/display/GD/Opening+A+Support+Incident) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Gigya のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Gigya の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Gigya 企業サイトに管理者としてログインします。
2. &gt;] に移動し、[**追加**] ボタンを選択します。

    [Image: SAML ログイン]
3. **[SAML Login]\(SAML ログイン**\) セクションで、次の手順を実行します。

    [Image: SAML 構成]

    ａ. [ **名前** ] ボックスに、構成の名前を入力します。

    b。 **[発行者**] ボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    c. **Single Sign-On Service URL** テキストボックスに、**ログイン URL** の値を貼り付けます。

    d. [ **名前 ID 形式** ] ボックスに、 **名前識別子の形式**の値を貼り付けます。

    え Azure portal からダウンロードした Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、 **X.509 証明書** ボックスに貼り付けます。

    f. [ **設定の保存] を選択します**。

### Gigya のテスト ユーザーの作成

Microsoft Entra ユーザーが Gigya にログインできるようにするには、そのユーザーを Gigya にプロビジョニングする必要があります。 Gigya の場合、プロビジョニングは手動で行います。

#### ユーザー アカウントをプロビジョニングするには、次の手順に従います。

1. **Gigya** 企業サイトに管理者としてログインします。
2. **[管理者**&gt;**管理ユーザー**] に移動し、[**ユーザーの招待**] を選択します。

    [Image: ユーザーの管理]
3. [ユーザーの招待] ダイアログで、次の手順を実行します。

    [Image: ユーザーの招待]

    ａ. [ **電子メール** ] ボックスに、プロビジョニングする有効な Microsoft Entra アカウントのメール エイリアスを入力します。

    b。 [ **ユーザーの招待]** を選択します。

    注

    アカウントがアクティブになる前に、Microsoft Entra アカウント所有者に、アカウント確認用のリンクを含む電子メールが送信されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Gigya のサインオン URL にリダイレクトされます。
- Gigya のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Gigya] タイルを選択すると、このオプションは Gigya のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/github-enterprise-cloud-enterprise-account-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に GitHub Enterprise Cloud - Enterprise アカウントを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-cloud-enterprise-account-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GitHub Enterprise Cloud - Enterprise アカウントの間にシングル サインオンを構成する方法について説明します。

この記事では、Microsoft Entra SAML と GitHub Enterprise Cloud - Enterprise アカウントの統合を設定する方法について説明します。 GitHub Enterprise Cloud - Enterprise アカウントを Microsoft Entra ID と統合すると、次のことができます。

- GitHub Enterprise アカウントと Enterprise アカウント内の任意の組織にアクセスできるユーザーを Microsoft Entra ID で制御する。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [GitHub Enterprise アカウント](https://docs.github.com/en/enterprise-cloud@latest/admin/overview/about-enterprise-accounts)。
- エンタープライズ アカウント所有者である GitHub ユーザー アカウント。

### シナリオの説明

この記事では、GitHub Enterprise アカウントの SAML 統合を構成し、エンタープライズ アカウント所有者とエンタープライズ/組織メンバーの認証とアクセスをテストします。

注

GitHub `Enterprise Cloud - Enterprise Account` アプリケーションでは、 [SCIM](https://learn.microsoft.com/ja-jp/entra/architecture/sync-scim) プロビジョニングの自動有効化はサポートされていません。 GitHub Enterprise Cloud 環境のプロビジョニングを設定する必要がある場合は、SAML を組織レベルで構成し、代わりに microsoft Entra アプリケーション `GitHub Enterprise Cloud - Organization` を使用する必要があります。 [Enterprise Managed Users (EMU)](https://docs.github.com/enterprise-cloud@latest/admin/identity-and-access-management/using-enterprise-managed-users-for-iam/about-enterprise-managed-users) が有効になっている企業に対して SAML と SCIM プロビジョニング統合を設定する場合は、SAML/プロビジョニング統合に microsoft Entra アプリケーションを`GitHub Enterprise Managed User`するか、OIDC/プロビジョニング統合用の `GitHub Enterprise Managed User (OIDC)` Microsoft Entra アプリケーションを使用する必要があります。

- GitHub Enterprise Cloud - Enterprise Account では、**SP**開始SSOと**IDP**開始SSOがサポートされています。

注

現在、GitHub `Enterprise Cloud - Enterprise Account` アプリケーションでは、政府機関向けクラウド プラットフォームはサポートされていません。

### ギャラリーからの GitHub Enterprise Cloud - Enterprise Account の追加

Microsoft Entra ID への GitHub Enterprise Cloud - Enterprise アカウントの統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に GitHub Enterprise Cloud - Enterprise アカウントを追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を開きます。
3. **[ギャラリーから追加する**] セクションで、検索ボックス**に「GitHub Enterprise Cloud - Enterprise Account**」と入力します。
4. 結果パネルから **GitHub Enterprise Cloud - Enterprise Account を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GitHub Enterprise Cloud - Enterprise アカウントの Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、GitHub Enterprise Cloud - Enterprise Account に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと GitHub Enterprise Cloud - Enterprise アカウントの関連ユーザーとの間にリンク関係を確立する必要があります。

GitHub Enterprise Cloud - Enterprise アカウントに対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra ユーザーとテスト ユーザー アカウントを GitHub アプリに割り当てます** 。ユーザー アカウントとテスト ユーザー `B.Simon` が Microsoft Entra シングル サインオンを使用できるようにします。
2. **エンタープライズ アカウントとその組織の SAML を有効にしてテスト**します。アプリケーション側でシングル サインオン設定を構成します。
    1. **別のエンタープライズ アカウント所有者または組織メンバー アカウントに対する SSO をテスト** して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**GitHub Enterprise Cloud - Enterprise Account**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://github.com/enterprises/<ENTERPRISE-SLUG>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://github.com/enterprises/<ENTERPRISE-SLUG>/saml/consume`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://github.com/enterprises/<ENTERPRISE-SLUG>/sso`

    注

    `<ENTERPRISE-SLUG>` は、GitHub Enterprise Account の実際の名前に置き換えます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **GitHub Enterprise Cloud - Enterprise Account のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、Azure portal で `B.Simon` というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [ **新しいユーザー**&gt;**新しいユーザー**の作成] を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. [ **ユーザー プリンシパル名** ] フィールドに、 username@companydomain.extensionを入力します。 たとえば、`B.Simon@contoso.com` のようにします。
    3. [ **パスワードの表示** ] チェック ボックスをオンにし、[ **パスワード** ] ボックスに表示される値を書き留めます。
    4. [ **確認と作成**] を選択します。
5. **作成**を選択します。

#### GitHub アプリへの Microsoft Entra ユーザーとテスト ユーザー アカウントの割り当て

このセクションでは、GitHub Enterprise Cloud - Enterprise アカウントへのアクセスを許可することで、 `B.Simon` とユーザー アカウントで Azure シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**GitHub Enterprise Cloud - Enterprise アカウント**に移動する。
3. アプリの概要ページで、[ **管理** ] セクションを見つけて、[ **ユーザーとグループ**] を選択します。
4. [**ユーザーの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] リストから **B.Simon** とユーザー アカウントを選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
7. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Enterprise Account とその組織の SAML の有効化とテスト

**GitHub Enterprise Cloud - Enterprise アカウント**側でシングル サインオンを構成するには、[この GitHub ドキュメント](https://docs.github.com/en/enterprise-cloud@latest/admin/policies/enforcing-policies-for-your-enterprise/enforcing-policies-for-security-settings-in-your-enterprise#enabling-saml-single-sign-on-for-organizations-in-your-enterprise-account)に記載されている手順に従います。

1. [エンタープライズ アカウント所有者](https://docs.github.com/en/enterprise-cloud@latest/admin/user-management/managing-users-in-your-enterprise/roles-in-an-enterprise#enterprise-owner)のユーザー アカウントで GitHub.com にサインインします。
2. アプリの `Login URL` フィールドの値をコピーし、GitHub Enterprise Account の SAML 設定の `Sign on URL` フィールドに貼り付けます。
3. アプリの `Microsoft Entra Identifier` フィールドの値をコピーし、GitHub Enterprise Account の SAML 設定の `Issuer` フィールドに貼り付けます。
4. 上記の手順で Azure portal からダウンロードした **証明書 (Base64)** ファイルの内容をコピーし、GitHub Enterprise Account SAML 設定の適切なフィールドに貼り付けます。
5. `Test SAML configuration`を選択し、GitHub Enterprise アカウントから Microsoft Entra ID に正常に認証できることを確認します。
6. テストが成功したら、設定を保存します。
7. GitHub エンタープライズ アカウントから初めて SAML を使用して認証した後、GitHub エンタープライズ アカウントに *リンクされた外部 ID が* 作成され、サインインした GitHub ユーザー アカウントが Microsoft Entra ユーザー アカウントに関連付けられます。

GitHub Enterprise Account に対して SAML SSO を有効にすると、その Enterprise Account によって所有されているすべての組織に対して SAML SSO が既定で有効になります。 すべてのメンバーは、メンバーである組織にアクセスするために SAML SSO を使用して認証する必要があります。エンタープライズ所有者は、エンタープライズ アカウントにアクセスするときに SAML SSO を使用して認証する必要があります。

### 別の Enterprise Account オーナーまたは組織メンバー アカウントを使用した SSO のテスト

GitHub Enterprise アカウントに対して SAML 統合を設定すると (これは Enterprise アカウント内の GitHub 組織にも適用されます)、Microsoft Entra ID 内でアプリに割り当てられている他の Enterprise アカウント所有者は、GitHub Enterprise アカウントの URL (`https://github.com/enterprises/<enterprise account>`) に移動し、SAML を介して認証し、GitHub Enterprise アカウントのポリシーと設定にアクセスできます。

エンタープライズ アカウント内の組織の組織所有者は、 [GitHub 組織に参加するようにユーザーを招待](https://docs.github.com/en/free-pro-team@latest/github/setting-up-and-managing-organizations-and-teams/inviting-users-to-join-your-organization)できる必要があります。 組織所有者アカウントを使用して GitHub.com にサインインし、記事内の手順に従って `B.Simon` を組織に招待します。 まだ存在しない場合は、 `B.Simon` 用に GitHub ユーザー アカウントを作成する必要があります。

Enterprise Account で `B.Simon` テスト ユーザー アカウントを使用して GitHub 組織アクセスをテストするには:

1. Enterprise Account 内の組織に `B.Simon` を組織所有者として招待します。
2. `B.Simon` の Microsoft Entra ユーザー アカウントにリンクするユーザー アカウントを使用して、GitHub.com にサインインします。
3. `B.Simon` ユーザー アカウントを使って Microsoft Entra ID にサインインします。
4. GitHub 組織に移動します。 SAML を介して認証するよう求めるメッセージがユーザーに表示されます。 SAML 認証が成功すると、`B.Simon` は組織のリソースにアクセスできるようになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/github-enterprise-managed-user-ghe-com-tutorial"} -->
## GitHub Enterprise Managed User の構成 - Microsoft Entra ID を使用したシングル サインオン用の GHE.com - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-managed-user-ghe-com-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-28
- Summary: Microsoft Entra ID と GitHub Enterprise Managed User - GHE.com の間のシングル サインオンを構成する方法について学習します。

この記事では、GitHub Enterprise Managed User と Microsoft Entra ID を統合する方法について説明します。 GitHub Enterprise Managed User と Microsoft Entra ID を統合すると、次のことができます。

- GitHub Enterprise Managed User にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して GitHub Enterprise Managed User に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GitHub Enterprise Managed User でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- GitHub Enterprise Managed User では、**SP開始 SSO** と **IDP開始 SSO** の両方がサポートされます。

注意

現在、GitHub `Enterprise Managed User - GHE.com` アプリケーションでは、政府機関向けクラウド プラットフォームはサポートされていません。

### ギャラリーからの GitHub Enterprise Managed User の追加

Microsoft Entra ID への GitHub Enterprise Managed User の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に GitHub Enterprise Managed User を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照してください。
3. 検索ボックス **に「GitHub Enterprise Managed User** 」と入力します。
4. 結果パネルから **GitHub Enterprise Managed User** を選択し、[ **作成** ] ボタンを選択します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GitHub Enterprise Managed User の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、GitHub Enterprise Managed User に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと GitHub Enterprise Managed User の関連ユーザーとの間にリンク関係を確立する必要があります。

GitHub Enterprise Managed User に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GitHub Enterprise Managed User SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GitHub Enterprise Managed User のテスト ユーザーの作成** - GitHub Enterprise Managed User で B.Simon に対応するユーザーを作成し、Microsoft Entra ID の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**GitHub Enterprise Managed User**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENTERPRISE>.ghe.com/enterprises/<ENTERPRISE>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENTERPRISE>.ghe.com/enterprises/<ENTERPRISE>/saml/consume`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENTERPRISE>.ghe.com/login`

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには [、GitHub Enterprise Managed User サポート チーム](https://support.github.com/early-access/data-residency) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **GitHub Enterprise Managed User のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GitHub Enterprise Managed User SSO の構成

**GitHub Enterprise Managed User** 側でシングル サインオンを構成するには、[GitHub ドキュメントの ID プロバイダーを構成するためのドキュメントに](https://docs.github.com/en/enterprise-cloud@latest/admin/managing-iam/configuring-authentication-for-enterprise-managed-users/configuring-saml-single-sign-on-for-enterprise-managed-users#configure-your-enterprise)従います。

#### GitHub Enterprise Managed User のテスト ユーザーの作成

このセクションでは、GitHub Enterprise Managed User に B.Simon というユーザーを作成します。 [GitHub Enterprise Managed User サポート チーム](https://support.github.com/early-access/data-residency)と協力して、GitHub Enterprise Managed User プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる GitHub Enterprise Managed User のサインオン URL にリダイレクトされます。
- GitHub Enterprise Managed User のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した GitHub Enterprise Managed User に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [GitHub Enterprise Managed User] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した GitHub Enterprise Managed User に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/github-enterprise-managed-user-oidc-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に GitHub Enterprise Managed User (OIDC) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-managed-user-oidc-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID から GitHub Enterprise Managed User (OIDC) に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を説明します。

この記事では、自動ユーザー プロビジョニングを構成するために GitHub Enterprise Managed User (OIDC) と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、GitHub Enterprise Managed User (OIDC) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

注意

[GitHub Enterprise Managed User (EMU)](https://docs.github.com/enterprise-cloud@latest/admin/authentication/managing-your-enterprise-users-with-your-identity-provider/about-enterprise-managed-users) は、別の種類の [GitHub Enterprise アカウント](https://docs.github.com/enterprise-cloud@latest/admin/overview/about-enterprise-accounts)です。 EMU インスタンスを特に要求していない限り、標準の GitHub Enterprise アカウントになります。 その場合は、EMU 以外の組織でユーザー プロビジョニングを構成するための [ドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-provisioning-tutorial) を参照してください。 ユーザー プロビジョニングは [、標準の GitHub Enterprise アカウント](https://docs.github.com/enterprise-cloud@latest/admin/overview/about-enterprise-accounts)ではサポートされていませんが、標準の GitHub Enterprise アカウントの下の組織ではサポートされています。

### サポートされる機能

- GitHub Enterprise Managed User でユーザーを作成する (OIDC)
- アクセスが不要になった場合に GitHub Enterprise Managed User (OIDC) のユーザーを削除する
- Microsoft Entra ID と GitHub Enterprise Managed User (OIDC) 間でユーザー属性の同期を維持する
- GitHub Enterprise Managed User (OIDC) でグループとグループ メンバーシップをプロビジョニングする
- GitHub Enterprise Managed User (OIDC) に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)する (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

GitHub Enterprise Managed User (OIDC) は、次の [一部のクラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- エンタープライズ管理ユーザーがOpenID Connect (OIDC) シングルサインオンを使用してMicrosoft Entraテナントを通じてログインできるように、GitHub Enterpriseが有効化および構成されています。

### 手順 1: プロビジョニングの配置を計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と GitHub Enterprise Managed User の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用してプロビジョニングを構成する準備を行う

1. テナントの URL を特定します。 これは、GitHub Enterprise Managed User アプリケーションの [プロビジョニング] タブにある [テナント URL] フィールドに入力する値です。

    - GitHub.com のエンタープライズの場合、テナント URL は `https://api.github.com/scim/v2/enterprises/{enterprise}` です。
    - GHE.com のエンタープライズの場合、テナント URL は `https://api.{subdomain}.ghe.com/scim/v2/enterprises/{subdomain}` です
2. エンタープライズのセットアップ ユーザー用に **scim:enterprise** スコープでトークンを作成していることを確かめます。 この値を、GitHub Enterprise Managed User アプリケーションの [プロビジョニング] タブにある [シークレット トークン] フィールドに入力します。 GitHub Docs の「[Enterprise Managed Users の概要](https://docs.github.com/en/enterprise-cloud@latest/admin/managing-iam/understanding-iam-for-enterprises/getting-started-with-enterprise-managed-users#create-a-personal-access-token)」を参照してください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから GitHub Enterprise Managed User (OIDC) を追加する

Microsoft Entra アプリケーション ギャラリーから GitHub Enterprise Managed User (OIDC) を追加して、GitHub Enterprise Managed User (OIDC) へのプロビジョニングの管理を開始します。 SSO のために GitHub Enterprise Managed User (OIDC) を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

注意

アプリケーションの別のインスタンスが必要な場合は、GitHub Enterprise Managed User (OIDC) - ghe.com に同意し、GitHubアカウント チームと協力して、インスタンスに対してこの機能を有効にしてください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: GitHub Enterprise Managed User (OIDC) への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、TestApp でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で GitHub Enterprise Managed User (OIDC) への自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で、**[GitHub Enterprise Managed User (OIDC)]** を選択します。

    [Image: アプリケーションの一覧の [GitHub Enterprise Managed User (OIDC)] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションで、GitHub Enterprise Managed User (OIDC) のテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が GitHub Enterprise Managed User (OIDC) に接続できることを確認します。 接続に失敗する場合は、GitHub Enterprise Managed User (OIDC) アカウントによってシークレット トークンがエンタープライズ所有者として作成されていることを確認し、やり直してください。

    - [テナント URL] に、先ほど特定したテナント URL を入力します。

        - GitHub.com の octo-corp という企業の場合、テナント URL は `https://api.github.com/scim/v2/enterprises/octo-corp`。
        - GHE.com の octo-corp という企業の場合、テナント URL は `https://api.octo-corp.ghe.com/scim/v2/enterprises/octo-corp`。
    - [シークレット トークン] には、先ほど作成した GitHub 個人用アクセス トークンを貼り付けます。

        [Image: トークン]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から GitHub Enterprise Managed User (OIDC) に同期されるユーザー属性を確認します。 **Matching** プロパティとして選択されている属性は、更新処理で GitHub Enterprise Managed User (OIDC) のユーザー アカウントとの照合に使用されます。 [一致する対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づいたユーザーのフィルター処理が確実に GitHub Enterprise Managed User API (OIDC) でサポートされているようにする必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | エクスターナルID | 文字列 | ✓ |
    | ユーザー名 | 文字列 |  |
    | 活動中 | ブール値 |  |
    | 役割 | 文字列 |  |
    | 表示名 | 文字列 |  |
    | 名前.名 | 文字列 |  |
    | 名前.姓 | 文字列 |  |
    | 名前.整形済み | 文字列 |  |
    | emails[type eq "仕事"].value | 文字列 |  |
    | メール[タイプ eq "自宅"].値 | 文字列 |  |
    | emails[タイプ eq "その他"].値 | 文字列 |  |

    注意

    **AppRoleAssignmentComplex** 構成は、[**割り当てられたユーザーとグループのみを同期**] オプションがスコープとして選択されている場合にのみ機能します。 このオプションが選択されていない場合、approleassignment は期待どおりに機能しません。

    [Image: AppRoleAssignmentComplex を示すスクリーンショット。]
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から GitHub Enterprise Managed User (OIDC) に同期されるグループ属性を確認します。 **Matching** プロパティとして選択されている属性は、更新処理で GitHub Enterprise Managed User (OIDC) のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | エクスターナルID | 文字列 | ✓ |
    | 表示名 | 文字列 |  |
    | メンバー | リファレンス |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/github-enterprise-managed-user-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に GitHub Enterprise Managed User を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-managed-user-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID から GitHub Enterprise Managed User に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を説明します。

この記事では、自動ユーザー プロビジョニングを構成するために GitHub Enterprise Managed User と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、GitHub Enterprise Managed User へのユーザーとグループのプロビジョニングとプロビジョニング解除を自動的に実行します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

注

[GitHub Enterprise Managed User (EMU)](https://docs.github.com/enterprise-cloud@latest/admin/authentication/managing-your-enterprise-users-with-your-identity-provider/about-enterprise-managed-users) は、異なる種類の [GitHub Enterprise アカウント](https://docs.github.com/enterprise-cloud@latest/admin/overview/about-enterprise-accounts)です。 EMU インスタンスを特別に要求していない限り、標準の GitHub Enterprise Account になります。 その場合は、EMU 以外の組織でユーザー プロビジョニングを構成するための [ドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-provisioning-tutorial) を参照してください。 ユーザー プロビジョニングは [標準の GitHub Enterprise アカウント](https://docs.github.com/enterprise-cloud@latest/admin/overview/about-enterprise-accounts)ではサポートされていませんが、GitHub Enterprise Managed User (EMU) の下の組織ではサポートされています。

### サポートされる機能

- GitHub Enterprise Managed User でユーザーを作成する
- アクセスが不要になった場合に GitHub Enterprise Managed User のユーザーを削除する
- Microsoft Entra ID と GitHub Enterprise Managed User 間でユーザー属性の同期を維持する
- GitHub Enterprise Managed User でグループとグループ メンバーシップをプロビジョニングする
- GitHub Enterprise Managed User へシングル サインオンする (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

GitHub Enterprise マネージド ユーザーは、次の [一次クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Enterprise Managed User で GitHub Enterprise が有効にされ、Microsoft Entra テナントを介して SAML SSO でログインするように構成済みであること。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と GitHub Enterprise Managed User の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングの構成を準備する

1. テナントの URL を特定します。 この値を、GitHub Enterprise Managed User アプリケーションの [プロビジョニング] タブにある [テナント URL] フィールドに入力します。

    - GitHub.com 上の企業の場合、テナント URL は`https://api.github.com/scim/v2/enterprises/{enterprise}`されます。ここで、`{enterprise}`はエンタープライズ スラッグ (アカウント名) です。
    - GHE.com のエンタープライズの場合、テナント URL は `https://api.{subdomain}.ghe.com/scim/v2/enterprises/{subdomain}` です
2. 企業のセットアップ ユーザーの **scim:enterprise** スコープでトークンを作成していることを確認します。 この値を、GitHub Enterprise Managed User アプリケーションの [プロビジョニング] タブにある [シークレット トークン] フィールドに入力します。 GitHub Docs の [エンタープライズ マネージド ユーザーの概要](https://docs.github.com/en/enterprise-cloud@latest/admin/managing-iam/understanding-iam-for-enterprises/getting-started-with-enterprise-managed-users#create-a-personal-access-token) を参照してください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから GitHub Enterprise Managed User を追加する

Microsoft Entra アプリケーション ギャラリーから GitHub Enterprise Enterprise Managed User を追加して、GitHub Enterprise Managed User へのプロビジョニングの管理を開始します。 SSO のために GitHub Enterprise Managed User を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: GitHub Enterprise Managed User への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて TestApp 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID: で GitHub Enterprise Managed User への自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で、 **GitHub Enterprise Managed User** を選択します。

    [Image: アプリケーションの一覧の GitHub Enterprise Managed User のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、GitHub Enterprise Managed User テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が GitHub Enterprise Managed User に接続できることを確認します。 接続に失敗した場合は、GitHub Enterprise Managed User アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から GitHub Enterprise Managed User に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために GitHub Enterprise Managed User のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が GitHub Enterprise Managed User API でサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | externalId | 糸 | ✓ |
    | ユーザー名 | 糸 |  |
    | 活動中 | ブール値 |  |
    | roles | 糸 |  |
    | displayName | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | emails[type eq "work"].value | 糸 |  |
    | emails[type eq "home"].value | 糸 |  |
    | emails[type eq "other"].value | 糸 |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から GitHub Enterprise Managed User に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で GitHub Enterprise Managed User のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | externalId | 糸 | ✓ |
    | displayName | 糸 |  |
    | members | 関連項目 |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/github-enterprise-managed-user-tutorial"} -->
## Microsoft Entra ID を使用して SAML シングル サインオン用のエンタープライズ マネージド ユーザーを使用して GitHub エンタープライズを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-managed-user-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-22
- Summary: Enterprise Managed Users を使用して Microsoft Entra ID と GitHub エンタープライズの間で SAML シングル サインオンを構成する方法について説明します。

この記事では、Microsoft Entra ID を持つエンタープライズ マネージド ユーザーと GitHub エンタープライズの SAML 統合を設定する方法について説明します。 エンタープライズ マネージド ユーザーを使用する GitHub 企業では、[SCIM プロビジョニング](https://docs.github.com/enterprise-cloud@latest/admin/managing-iam/configuring-authentication-for-enterprise-managed-users/configuring-oidc-for-enterprise-managed-users)の設定に加えて、SAML または [OIDC](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-managed-user-provisioning-tutorial) 認証の統合を設定する必要があります。 Enterprise Managed Users を使用して GitHub エンタープライズの認証と [SCIM プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-managed-user-provisioning-tutorial) 設定すると、管理者は次のことができます。

- Microsoft Entra ID で Enterprise Managed Users を使用する GitHub エンタープライズへのアクセスを制御します。
- ユーザーが SSO を使用して GitHub Enterprise Managed User アカウントにログインできるようにします。
- ユーザーとグループを企業にプロビジョニングします (認証と SCIM プロビジョニングの両方の統合が設定されたら)。 GitHub チームは、SCIM によってプロビジョニングされたグループにマップできます。
- 1 つの中央の場所 (Entra ID) でアカウントとグループを管理します。

注

エンタープライズ マネージド ユーザーを持つ GitHub.com エンタープライズ アカウントは、特定の種類の企業です。 これは、GitHub.com で [新しい GitHub エンタープライズ アカウントを](https://docs.github.com/en/enterprise-cloud@latest/admin/managing-your-enterprise-account/creating-an-enterprise-account) 要求または作成した場合に決定されます。 さまざまな種類の GitHub 企業の詳細については、 [この GitHub の記事](https://docs.github.com/en/enterprise-cloud@latest/admin/managing-iam/understanding-iam-for-enterprises/choosing-an-enterprise-type-for-github-enterprise-cloud)を参照してください。 Enterprise Managed Users 用に設定されている企業がない場合は、詳細とリンクについては、 [この GitHub の記事](https://docs.github.com/en/enterprise-cloud@latest/admin/managing-iam/understanding-iam-for-enterprises/about-identity-and-access-management#authentication-through-githubcom-with-additional-saml-access-restriction) を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GitHub Enterprise Managed User でのシングル サインオン (SSO) が有効なサブスクリプション。
- Enterprise Managed Users 用に設定されている GitHub エンタープライズ。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- GitHub Enterprise Managed User は、**SP と IDP** によって開始される SSO の両方をサポートしています。
- GitHub Enterprise Managed User には、[**自動化された**ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-managed-user-provisioning-tutorial)が必要です。

注

現在、GitHub `Enterprise Managed User` アプリケーションでは、政府機関向けクラウド プラットフォームはサポートされていません。

### ギャラリーからの GitHub Enterprise Managed User の追加

Microsoft Entra ID への GitHub Enterprise Managed User の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に GitHub Enterprise Managed User を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「**GitHub Enterprise Managed User**」と入力します。
4. 結果パネルから **GitHub Enterprise Managed User** を選択し、[ **作成** ] ボタンを選択します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Enterprise Managed Users を使用して GitHub エンタープライズの Microsoft Entra SAML SSO を構成してテストする

GitHub Enterprise Managed User に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する** - Microsoft Entra テナントで SAML シングル サインオンを有効にします。
2. **GitHub Enterprise Managed User の SSO の構成** - GitHub Enterprise でシングル サインオン設定を構成します。

### Microsoft Entra SAML SSO の構成

Microsoft Entra SAML SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**GitHub Enterprise Managed User**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集を示すスクリーンショット。]
5. 始める前に Enterprise URL があることを確認します。 下に示す ENTITY フィールドは、EMU 対応 Enterprise URL の Enterprise 名です。 たとえば、 https://github.com/enterprises/contoso - **contoso** が ENTITY です。 **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    ある。 **[識別子]** ボックスに、`https://github.com/enterprises/{enterprise}` の形式で URL を入力します。

    注

    識別子の形式は、アプリケーションの推奨形式とは異なります。上の形式に従ってください。 さらに、\*\*識別子に末尾のスラッシュが含まれていないことを確認してください。

    b。 **[応答 URL]** ボックスに、`https://github.com/enterprises/{enterprise}/saml/consume` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://github.com/enterprises/{enterprise}/sso` という形式で URL を入力します。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 証明書** ] セクションで、Base64 証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Set up GitHub Enterprise Managed User](GitHub Enterprise Managed User の設定)** セクションで、下の URL をコピーして、下で GitHub を構成するために保存します。

    [Image: 構成の URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、SSO のセットアップを完了するために、GitHub Enterprise Managed User にアカウントを割り当てます。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**GitHub Enterprise Managed User** に移動。
3. アプリの概要ページで、 **[管理]** セクションを見つけて、 **[ユーザーとグループ]** を選択します。
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] リストから自分のアカウントを選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. [ **ロールの選択** ] ダイアログで、 **エンタープライズ所有者** ロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。 次の記事でアカウントをプロビジョニングすると、GitHub インスタンスのエンタープライズ所有者としてアカウントが割り当てられます。
7. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### GitHub Enterprise Managed User SSO の構成

**GitHub Enterprise Managed User** 側でシングル サインオンを構成するには、Entra ID アプリから次の項目が必要です。

1. 上記の Microsoft Entra Enterprise マネージド ユーザー アプリケーションの URL: `Login URL` と `Microsoft Entra Identifier`。
2. ダウンロードした base64 証明書。
3. GitHub エンタープライズの[セットアップ ユーザー アカウント](https://docs.github.com/enterprise-cloud@latest/admin/managing-iam/understanding-iam-for-enterprises/getting-started-with-enterprise-managed-users#create-the-setup-user)のユーザー名とパスワード。

#### GitHub Enterprise Managed User SAML SSO の有効化

このセクションでは、上記の Microsoft Entra ID から提供された情報を取得し、それらをエンタープライズ設定に入力して SSO サポートを有効にします。

1. [この GitHub ドキュメント](https://docs.github.com/enterprise-cloud@latest/admin/managing-iam/configuring-authentication-for-enterprise-managed-users/configuring-saml-single-sign-on-for-enterprise-managed-users#configure-your-enterprise)の手順に従って、企業の SAML 認証を構成します。
2. `Sign-on URL`を入力するときは、上記の Microsoft Entra ID からコピーしたログイン URL であることに注意してください。
3. `Issuer`を入力するときは、上記の Microsoft Entra ID からコピーした`Microsoft Entra Identifier`であることに注意してください。
4. パブリック証明書を入力するときに、上記でダウンロードした base64 証明書を開き、そのファイルのテキストコンテンツをこのダイアログに貼り付けます。
5. エンタープライズの SAML 認証を構成するための GitHub ドキュメントの手順を完了すると、SCIM でプロビジョニングされたエンタープライズ マネージド ユーザーのみが企業にアクセスできるようになります (セットアップ ユーザー アカウントでのログインとエンタープライズ回復コードの使用を除く)。 Enterprise Managed Users とグループをプロビジョニングできるように、以下のプロビジョニング チュートリアルの手順を完了して、GitHub エンタープライズの SCIM プロビジョニングを構成します。 ユーザーは、これらの手順が完了し、ユーザー アカウントが企業で SCIM プロビジョニングされるまで、企業にログインしてアクセスすることはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/github-enterprise-server-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して自動ユーザー プロビジョニング用に GitHub Enterprise Server を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-server-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-09
- Summary: Microsoft Entra ID から GitHub Enterprise Server にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために GitHub Enterprise Server と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーやグループを GitHub Enterprise Server に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- GitHub Enterprise Server でユーザーを作成する
- アクセスが不要になった場合に GitHub Enterprise Server のユーザーを削除する
- Microsoft Entra ID と GitHub Enterprise Server の間でユーザー属性の同期を維持する
- GitHub Enterprise Server でグループとグループ メンバーシップをプロビジョニングする
- [GitHub Enterprise Server](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-server-tutorial) へのシングル サインオン (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GitHub Enterprise Server。完全 [に初期化](https://docs.github.com/enterprise-server/admin/overview/about-github-enterprise-server) され、Microsoft Entra テナントを介して [SAML SSO を](https://docs.github.com/enterprise-server/admin/managing-iam/using-saml-for-enterprise-iam/configuring-saml-single-sign-on-for-your-enterprise) 使用してログインするように構成されています。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と GitHub Enterprise Server の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように GitHub Enterprise Server を構成する

GitHub Enterprise Server のプロビジョニングを有効にする方法については [、こちらをご覧ください](https://docs.github.com/enterprise-server/admin/managing-iam/provisioning-user-accounts-with-scim/configuring-scim-provisioning-for-users)。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから GitHub Enterprise Server を追加する

Microsoft Entra アプリケーション ギャラリーから GitHub Enterprise Server を追加して、GitHub Enterprise Server へのプロビジョニングの管理を開始します。 SSO 用に GitHub Enterprise Server を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: GitHub Enterprise Server への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で GitHub Enterprise Server の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**を確認する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で 、 **GitHub Enterprise Server** を選択します。

    [Image: アプリケーションの一覧で強調表示されている GitHub Enterprise Server リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: アプリケーション メニューで選択されている [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、GitHub Enterprise Server **テナントの URL** と **シークレット トークン**を入力します。 フィールドの値の形式は次のとおりです。

    - **テナント URL**: `https://<your-github-server-domain>/api/v3/scim`
    - **シークレット トークン**: GitHub Enterprise Server インスタンスの[プロビジョニング アカウント用に](https://docs.github.com/enterprise-server/admin/managing-iam/provisioning-user-accounts-with-scim/configuring-scim-provisioning-for-users#2-create-a-personal-access-token)作成した[個人用アクセス トークン (PAT)。](https://docs.github.com/enterprise-server/admin/managing-iam/provisioning-user-accounts-with-scim/configuring-scim-provisioning-for-users#1-create-a-built-in-setup-user)

    [ **テスト接続]** を選択して、Microsoft Entra ID が GitHub Enterprise Server に接続できることを確認します。 接続に失敗した場合は、GitHub Enterprise Server アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から GitHub Enterprise Server に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために GitHub Enterprise Server のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が GitHub Enterprise Server API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | `userName` | String |
    | `externalId` | String |
    | `emails[type eq "work"].value` | String |
    | `active` | ブール値 |
    | `name.givenName` | String |
    | `name.familyName` | String |
    | N`ame.formatted` | String |
    | `displayName` | String |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から GitHub Enterprise Server に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために GitHub Enterprise Server のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | `displayName` | String |
    | `externalId` | String |
    | `members` | リファレンス |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

- 2021 年 2 月 18 日 - グループのプロビジョニングのサポートを追加しました。
- 2025 年 8 月 19 日 - 現在の製品名を反映するように、"GitHub AE" のリンクとメンションを "GitHub Enterprise Server" に更新しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/github-enterprise-server-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に GitHub Enterprise Server を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-server-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-13
- Summary: Microsoft Entra ID と GitHub Enterprise Server の間でシングル サインオンを構成する方法について説明します。

この記事では、GitHub Enterprise Server と Microsoft Entra ID を統合する方法について説明します。 GitHub Enterprise Server と Microsoft Entra ID を統合すると、次のことができます。

- GitHub Enterprise Server にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して GitHub Enterprise Server に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- [初期化](https://docs.github.com/enterprise-server/admin/overview/about-github-enterprise-server)の準備ができている GitHub Enterprise Server。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- GitHub Enterprise Server では、**SP**開始SSO および **IDP**開始SSO がサポートされます。
- GitHub Enterprise Server では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。
- GitHub Enterprise Server では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-server-provisioning-tutorial)。

注

現在、GitHub Enterprise Server アプリケーションでは、政府機関向けクラウド プラットフォームでの SCIM プロビジョニングはサポートされていません。 この制限は、GitHub Enterprise Server で `User-Agent` ヘッダーが必要であるためです。これは、政府機関向けクラウド環境から送信されるプロビジョニング要求には含まれません。

### ギャラリーから GitHub Enterprise Server を追加する

Microsoft Entra ID への GitHub Enterprise Server の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に GitHub Enterprise Server を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「GitHub Enterprise Server**」と入力します。
4. 結果パネルから **GitHub Enterprise Server を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GitHub Enterprise Server の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、GitHub Enterprise Server に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと GitHub Enterprise Server の関連ユーザーとの間にリンク関係を確立する必要があります。

GitHub Enterprise Server に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GitHub Enterprise Server の SSO を構成**する - アプリケーション側でシングル サインオン設定を構成します。
    1. **GitHub Enterprise Server のテスト ユーザーを作成する** - GitHub Enterprise Server で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**GitHub Enterprise Server**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR-GITHUB-ENTERPRISE-SERVER-HOSTNAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR-GITHUB-ENTERPRISE-SERVER-HOSTNAME>/saml/consume`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR-GITHUB-ENTERPRISE-SERVER-HOSTNAME>/sso`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、GitHub Enterprise Server クライアント サポート チーム](mailto:support@github.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. GitHub Enterprise Server アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: Enterprise Server アプリケーションの画像を示すスクリーンショット。]
8. **ユーザー属性と要求を編集します**。
9. [ **新しい要求の追加]** を選択し、テキスト ボックスに `administrator` として名前を入力します ( `administrator` の値では大文字と小文字が区別されます)。
10. [**要求条件**] を展開し、[**ユーザーの種類** **] から [メンバー]** を選択します。
11. [ **グループの選択] を選択** し、この要求を含める **グループ** を検索します。このグループのメンバーは GHES の管理者である必要があります。
12. [**ソース**] の **[属性**] を選択し、[値] に「`true` (引用符なし)」と入力**します**。
13. **保存** を選択します。

    [Image: 属性に関するクレームを管理するスクリーンショット。]
14. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
15. [ **GitHub Enterprise Server のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成 U R L をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GitHub Enterprise Server SSO の構成

GitHub Enterprise Server 側で SSO を構成するには、 [ここで](https://docs.github.com/enterprise-server/admin/managing-iam/using-saml-for-enterprise-iam/configuring-saml-single-sign-on-for-your-enterprise)説明する手順に従う必要があります。

#### GitHub Enterprise Server のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを GitHub Enterprise Server に作成します。 GitHub Enterprise Server では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 GitHub Enterprise Server にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

GitHub Enterprise Server では、自動ユーザー プロビジョニングもサポートされています。 自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-enterprise-server-provisioning-tutorial) 。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる GitHub Enterprise Server のサインオン URL にリダイレクトされます。
- GitHub Enterprise Server のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDPが開始されました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した GitHub Enterprise Server に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [GitHub Enterprise Server] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した GitHub Enterprise Server に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/github-provisioning-tutorial"} -->
## Microsoft Entra ID で自動ユーザー プロビジョニング用にGitHubを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: GitHub Enterprise Cloud でユーザー組織のメンバーシップを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、GitHub Enterprise Cloud組織のメンバーシップのプロビジョニングを自動化するために、GitHubとMicrosoft Entra IDで実行する必要がある手順を示すことです。

注

Microsoft Entra プロビジョニング統合は、GitHub SCIM API に依存しています。これは、GitHub Enterprise 課金プランのお客様GitHub Enterprise Cloud で利用できます。

Github は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GitHub Enterprise Cloud で作成されたGitHub組織は、GitHub Enterprise 課金プランが必要です。
- 組織に対する管理者アクセス許可を持つGitHubのユーザー アカウント
- [GitHub Enterprise Cloud 組織用に構成されたSAML](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-tutorial)
- [ここで](https://help.github.com/en/github/setting-up-and-managing-organizations-and-teams/approving-oauth-apps-for-your-organization)説明するように、組織に OAuth アクセスが提供されていることを確認します
- 1 つの組織に対する SCIM のプロビジョニングは、組織レベルで SSO が有効になっている場合のみサポートされます

### GitHubへのユーザーの割り当て

Microsoft Entra IDでは、"割り当て" という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに "割り当てられている" ユーザーとグループのみが同期されます。

プロビジョニング サービスを構成して有効にする前に、GitHub組織にアクセスする必要があるユーザーを表すMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 決定し終えたら、次の手順に従ってこれらのユーザーを割り当てることができます。

詳細については、「 [エンタープライズ アプリにユーザーまたはグループを割り当てる」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)参照してください。

#### ユーザーをGitHubに割り当てる際の重要なヒント

- プロビジョニング構成をテストするには、GitHubに 1 人のMicrosoft Entra ユーザーを割り当てることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- ユーザーをGitHubに割り当てるときは、割り当てダイアログで **User** ロール、または別の有効なアプリケーション固有のロール (使用可能な場合) を選択する必要があります。 **既定のアクセス** ロールはプロビジョニングには機能せず、これらのユーザーはスキップされます。

### GitHubへのユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDをGitHubのSCIMプロビジョニングAPIに接続して、GitHub組織メンバーシップのプロビジョニングを自動化する方法について説明します。 この統合は、[OAuth アプリ](https://docs.github.com/en/free-pro-team@latest/github/authenticating-to-github/authorizing-oauth-apps#oauth-apps-and-organizations)を活用し、Microsoft Entra IDでのユーザーとグループの割り当てに基づいて、GitHub Enterprise Cloud 組織へのメンバーのアクセスを自動的に追加、管理、および削除します。 ユーザーが SCIM を使用してGitHub組織にプロビジョニングされると、ユーザーの電子メール アドレスに招待メールが送信されます。

#### Microsoft Entra ID を使って GitHub に対して自動ユーザーアカウントプロビジョニングを設定する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
3. シングル サインオンのGitHubを既に構成している場合は、検索フィールドを使用してGitHubのインスタンスを検索します。
4. GitHubのインスタンスを選択し、**Provisioning** タブを選択します。
5. [ **+ 新しい構成**] を選択します。

    [Image: [自動] オプションが強調表示されている [プロビジョニング モード] ドロップダウン リストのスクリーンショット。]
6. **テナント URL** フィールドに、GitHubテナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDがGitHubに接続できることを確認します。 接続に失敗した場合は、GitHub アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. 新しいウィンドウで、管理者アカウントを使用してGitHubにサインインします。 結果の承認ダイアログで、プロビジョニングを有効にするGitHub組織を選択し、**Authorize** を選択します。 完了したら、Azure ポータルに戻り、プロビジョニング構成を完了します。

    [Image: GitHub のサインインページを示すスクリーンショットです。]
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
11. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
12. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
13. **Attribute Mappings** セクションで、Microsoft Entra ID から GitHub に同期されるユーザー属性を確認します。 **Matching** プロパティとして選択されている属性は、更新操作でGitHubのユーザー アカウントとの照合に使用されます。 エラーが発生する可能性があるため、[プロビジョニング] セクションの他の既定の属性に対して [ **照合の優先順位** ] 設定 **を** 有効にしないでください。 [ **保存] を** 選択して変更をコミットします。
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「[自動ユーザー アカウント プロビジョニングに関するレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/github-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に GitHub Enterprise Cloud Organization を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GitHub Enterprise Cloud Organization の間でシングル サインオンを構成する方法について説明します。

この記事では、GitHub Enterprise Cloud **Organization** と Microsoft Entra ID を統合する方法について説明します。 GitHub Enterprise Cloud Organization を Microsoft Entra ID と統合すると、次のことが可能になります。

- GitHub Enterprise Cloud Organization にアクセスできるユーザーを Microsoft Entra ID で管理する。
- GitHub Enterprise Cloud Organization へのアクセスを一元管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [GitHub Enterprise Cloud](https://help.github.com/articles/github-s-products/#github-enterprise) で作成された GitHub 組織。[GitHub Enterprise の課金プラン](https://help.github.com/articles/github-s-billing-plans/#billing-plans-for-organizations)が必要です。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- GitHub では、 **SP** Initiated SSO がサポートされます。
- GitHub では、 [**自動** ユーザー プロビジョニング (組織の招待) がサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-provisioning-tutorial)。

### ギャラリーからの GitHub の追加

Microsoft Entra ID への GitHub の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に GitHub を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「GitHub**」と入力します。
4. 結果パネルから **GitHub Enterprise Cloud - Organization** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GitHub 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、GitHub に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと GitHub の関連ユーザーとの間にリンク関係を確立する必要があります。

GitHub に対して Microsoft Entra の SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GitHub SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GitHub のテストユーザーを作成する** - GitHub で B.Simon の対応ユーザーを作成し、それを Microsoft Entra にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**GitHub**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://github.com/orgs/<Organization ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://github.com/orgs/<Organization ID>/saml/consume`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://github.com/orgs/<Organization ID>/sso`

    Note

    これらは実際の値ではありません。 実際の識別子、応答 URL、サインオン URL にこれらの値を置き換える必要があります。 ここでは、識別子に一意の文字列値を使用することをお勧めします。 これらの値を取得するには、GitHub 管理者セクションに移動します。
6. GitHub アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 **一意のユーザー識別子 (名前 ID)** は **user.userprincipalname** にマップされています。 GitHub アプリケーションでは、 **一意のユーザー識別子 (名前 ID)** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: [編集] アイコンが選択されている [ユーザー属性] セクションを示すスクリーンショット。]
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **GitHub のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GitHub の SSO の構成

1. 別の Web ブラウザー ウィンドウで、GitHub 組織サイトに管理者としてサインインします。
2. **[設定]** に移動し、[セキュリティ] を選択**します**。

    [Image: [セキュリティ] が選択されている GitHub の [組織の設定] メニューを示すスクリーンショット。]
3. [ **SAML 認証を有効にする** ] チェック ボックスをオンにして、[シングル サインオンの構成] フィールドを表示し、次の手順を実行します。

    [Image: [S A M L シングル サインオン] セクションには、「S A M L 認証を有効にする」のテキストボックスが強調表示されたスクリーンショットが示されています。]

    a. **シングル サインオン URL** の値をコピーし**、[基本的な SAML 構成**] の **[サインオン URL**] テキスト ボックスにこの値を貼り付けます。

    b。 **アサーション コンシューマー サービスの URL 値を**コピーし、この値を **[基本的な SAML 構成**] の **[応答 URL**] テキスト ボックスに貼り付けます。
4. 次のフィールドを構成します。

    [Image: [サインオン URL]、[発行者]、および [パブリック証明書] テキスト ボックスを示すスクリーンショット。]

    a. [ **サインオン URL** ] ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。

    b。 **[Issuer]\(発行者**\) テキストボックスに、前にコピーした **Microsoft Entra Identifier** の値を貼り付けます。

    c. Azure portal からダウンロードした証明書をメモ帳で開き、[ **パブリック証明書** ] ボックスに内容を貼り付けます。

    d. 次に示すように **、[編集]** アイコンを選択して、署名 **方法** と **ダイジェスト メソッド** を **RSA-SHA1** と **SHA1** から **RSA-SHA256** および **SHA256** に編集します。

    e. GitHub の URL が Azure アプリ登録の URL と一致するように、 **アサーション コンシューマー サービスの URL (応答 URL)** を既定の URL から更新します。

    [Image: 画像を示すスクリーンショット。]
5. [ **SAML 構成のテスト** ] を選択して、SSO 中に検証エラーまたはエラーがないことを確認します。

    [Image: [設定] を示すスクリーンショット。]
6. **[保存] を選択する**

Note

GitHub でのシングル サインオンは、GitHub の特定の組織に対して認証を行い、GitHub 自体の認証に代わることはありません。 つまり、ユーザーの github.com セッションの有効期限が切れた場合は、シングル サインオン プロセス中に GitHub の ID とパスワードで認証するように求められることがあります。

#### GitHub テスト ユーザーの作成

このセクションの目的は、GitHub で Britta Simon というユーザーを作成することです。 GitHub では、自動ユーザー プロビジョニングがサポートされています。この設定は、既定で有効になっています。 自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/github-provisioning-tutorial) 。

**ユーザーを手動で作成する必要がある場合は、次の手順を実行します。**

1. GitHub 企業サイトに管理者としてログインします。
2. **人々** を選択します。

    [Image: GitHub サイトで「People」が選択されているスクリーンショット。]
3. [ **メンバーの招待] を選択します**。

    [Image: [ユーザーの招待] を示すスクリーンショット。]
4. [ **メンバーの招待** ] ダイアログ ページで、次の手順を実行します。

    a. [ **電子メール** ] ボックスに、Britta Simon アカウントのメール アドレスを入力します。

    [Image: [招待するユーザー] を示すスクリーンショット。]

    b。 [ **招待の送信]** を選択します。

    [Image: [メンバー] が選択され、[招待の送信] ボタンが選択されている [メンバーの招待] ダイアログ ページを示すスクリーンショット。]

    Note

    Microsoft Entra アカウント所有者が電子メールを受信し、リンクに従ってアカウントを確認すると、そのアカウントがアクティブになります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる GitHub のサインオン URL にリダイレクトされます。
- GitHub のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [GitHub] タイルを選択すると、このオプションは GitHub のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。

Note

ユーザーが Microsoft Entra ID のグローバル管理者権限を持っている場合、エンタープライズ アプリケーションのメンバーとして追加されることなく、GitHub 組織の SSO エンドポイントを介してサインインできます。 また、招待なしで組織に自己参加することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/glassfrog-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に GlassFrog を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/glassfrog-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GlassFrog の間のシングル サインオンを構成する方法について説明します。

この記事では、GlassFrog と Microsoft Entra ID を統合する方法について説明します。 GlassFrog を Microsoft Entra ID と統合すると、次のことが可能になります。

- GlassFrog にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで GlassFrog に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GlassFrog でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- GlassFrog では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから GlassFrog を追加する

Microsoft Entra ID への GlassFrog の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に GlassFrog を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「GlassFrog**」と入力します。
4. 結果パネルから **GlassFrog** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GlassFrog に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、GlassFrog に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、GlassFrog での関連ユーザーとの間にリンク関係を確立する必要があります。

GlassFrog 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GlassFrog の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GlassFrog テスト ユーザーの作成** - GlassFrog で B.Simon に対応するユーザーを作成し、Microsoft Entra ID でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**GlassFrog**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.glassfrog.com/people/sso?org_id=<ORGANIZATIONID>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、GlassFrog クライアント サポート チーム](mailto:support@glassfrog.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **GlassFrog のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GlassFrog SSO を構成する

**GlassFrog** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [GlassFrog サポート チーム](mailto:support@glassfrog.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### GlassFrog テスト ユーザーの作成

このセクションでは、GlassFrog で Britta Simon というユーザーを作成します。 [GlassFrog サポート チーム](mailto:support@glassfrog.com)と協力して、GlassFrog プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる GlassFrog のサインオン URL にリダイレクトされます。
- GlassFrog のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで GlassFrog タイルを選択すると、このオプションは GlassFrog のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/glia-hub-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Glia Hub を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/glia-hub-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Glia Hub との間でシングル サインオンを構成する方法について説明します。

この記事では、Glia Hub と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID に Glia Hub を統合すると、次の利点が得られます。

- どのユーザーが Glia Hub にアクセスできるかを Microsoft Entra ID で制御できます。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して Glia Hub に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Glia Hub のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Glia Hub では、**SP と IDP** による SSO がサポートされます。

### ギャラリーからの Glia Hub の追加

Microsoft Entra ID への Glia Hub の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Glia Hub を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「Glia Hub」**と入力します。
4. 結果パネルから **Glia Hub** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Glia Hub 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Glia Hub に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Glia Hub の関連ユーザーとの間にリンク関係を確立する必要があります。

Glia Hub で Microsoft Entra の SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Glia Hub の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Glia Hub のテスト ユーザーの作成** - Glia Hub で、Microsoft Entra ID のユーザー B. Simon のリンク相手とするユーザーを用意し、これらのユーザーの間にリンクを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Glia Hub]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.app.glia.com`
    2. [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.app.glia.com/saml/acs`
    3. [ **リレー状態** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.app.glia.com`
    4. [ **ログアウト URL]** ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.app.glia.com/saml/logout`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    1. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.app.glia.com`

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL、リレー状態、ログアウト URL で更新してください。 これらの値を取得するには [、Glia Hub サポート チーム](mailto:support@glia.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Glia Hub アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. また、Glia Hub アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | idp\_name\_attribute | user.userprincipalname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Glia Hub の SSO の構成

**Glia Hub** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Glia Hub サポート チーム](mailto:support@glia.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Glia Hub のテスト ユーザーの作成

このセクションでは、Glia Hub で B.Simon というユーザーを作成します。 [Glia Hub サポート チーム](mailto:support@glia.com)と協力して、Glia Hub プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Glia Hub のサインオン URL にリダイレクトされます。
- Glia Hub のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Glia Hub に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Glia Hub] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Glia Hub に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/glint-inc-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Glint Inc を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/glint-inc-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Glint Inc との間でシングル サインオンを構成する方法について説明します。

この記事では、Glint Inc と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID に Glint Inc を統合すると、次の利点が得られます。

- どのユーザーが Glint Inc にアクセスできるかを Microsoft Entra ID で制御できます。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して Glint Inc に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Glint Inc でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Glint Inc では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーからの Glint Inc の追加

Microsoft Entra ID への Glint Inc の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Glint Inc を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Glint Inc」**と入力します。
4. 結果パネルから **Glint Inc** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Glint Inc 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Glint Inc に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Glint Inc の関連ユーザーとの間にリンク関係を確立する必要があります。

Glint Inc で Microsoft Entra の SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Glint Inc の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Glint Inc のテスト ユーザーの作成** - Glint Inc で、Microsoft Entra のユーザー B. Simon のリンク相手とするユーザーを用意し、これらのユーザーの間にリンクを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Glint Inc**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.<REGION>.glintinc.com/api/client/<CUSTOMER_NAME>/token/saml2/consume/includeDeskLink`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.<REGION>.glintinc.com/api/client/<CUSTOMER_NAME>/token/saml2/consume/includeDeskLink`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.<REGION>.glintinc.com/api/client/<CUSTOMER_NAME>/token/saml2/sso`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Glint Inc クライアント サポート チーム](mailto:glint-ssosupport@linkedin.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Glint Inc のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Glint Inc の SSO の構成

**Glint Inc** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Glint Inc サポート チーム](mailto:glint-ssosupport@linkedin.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Glint Inc のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを作成します。その後、[Glint Inc サポート チーム](mailto:glint-ssosupport@linkedin.com) と協力して、Glint Inc プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Glint Inc のサインオン URL にリダイレクトされます。
- Glint Inc のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Glint Inc に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Glint Inc] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Glint Inc に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/global-relay-identity-sync-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用にグローバル リレー ID 同期を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/global-relay-identity-sync-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: ユーザー アカウントをMicrosoft Entra IDからグローバル リレー ID 同期に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するためにグローバル リレー ID 同期とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID Microsoft Entra プロビジョニング サービスを使用して、グローバル リレー ID 同期に対するユーザーとグループのプロビジョニングとプロビジョニング解除を自動的に行います。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Global Relay Identity Sync でユーザーを作成する
- アクセスが不要になった場合にグローバル リレー ID 同期のユーザーを削除する
- Microsoft Entra IDとグローバル リレー ID 同期の間でユーザー属性の同期を維持する
- Global Relay Identity Sync でグループとグループ メンバーシップをプロビジョニングする
- クライアント資格情報認証がサポートされています。

注

Global Relay Identity Sync プロビジョニング コネクタでは、セキュリティ上の問題によりサポートされなくなった SCIM 承認方法が利用されます。 Global Relay により、さらに安全な承認方法に切り替える作業が進められています。

注

グローバル リレー ID 同期でクライアント資格情報の承認がサポートされるようになりました。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとGlobal Relay Identity Syncの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするようにグローバル リレー ID 同期を構成する

Global Relay Identity Sync の担当者に連絡して、テナントの URL を受け取ってください。 この値は、グローバル リレー ID 同期アプリケーションの [プロビジョニング] タブの **[テナント URL** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーからグローバル リレー ID 同期を追加する

Microsoft Entra アプリケーション ギャラリーからグローバル リレー ID 同期を追加して、グローバル リレー ID 同期へのプロビジョニングの管理を開始します。ギャラリー [here](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal) からアプリケーションを追加する方法について説明します。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Global Relay Identity Sync への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDでのユーザーやグループの割り当てに基づいてグローバル リレー ID 同期アプリでユーザーやグループを作成、更新、無効化するようにMicrosoft Entraプロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDでグローバル リレー ID 同期の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で、[ **グローバル リレー ID 同期] を選択します**。

    [Image: アプリケーションの一覧の [Global Relay Identity Sync] リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [**テナント URL**] フィールドに、グローバル リレー ID 同期**テナント URL、クライアント識別子、クライアント シークレット、** **OAuth トークン エンドポイント**を入力します。 [**テスト接続**] を選択して、Microsoft Entra IDがグローバル リレー ID 同期に接続できることを確認します。接続に失敗した場合は、グローバル リレー ID 同期アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDからグローバル リレー ID 同期に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作でグローバル リレー ID 同期のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Global Relay Identity Sync API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | 活動中 | ブール値 |
    | displayName | 糸 |
    | タイトル | 糸 |
    | 優先言語 | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | name.formatted | 糸 |
    | addresses[type eq "work"].フォーマット済み | 糸 |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |
    | emails[type eq "仕事"].value | 糸 |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |
    | アドレス[タイプ eq "other"].フォーマット済み | 糸 |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |
    | phoneNumbers[type eq "ファックス"].value | 糸 |
    | externalId | 糸 |
    | name.honorificPrefix | 糸 |
    | name.honorificSuffix | 糸 |
    | ニックネーム | 糸 |
    | ユーザータイプ | 糸 |
    | ロケール | 糸 |
    | タイムゾーン | 糸 |
    | メール[タイプ eq "自宅"].値 | 糸 |
    | emails[タイプ eq "その他"].値 | 糸 |
    | 電話番号[タイプ eq "home"].値 | 糸 |
    | phoneNumbers[種類 eq "その他"].value | 糸 |
    | 電話番号[タイプイコール "ポケベル"].値 | 糸 |
    | アドレス[タイプ eq "home"].ストリートアドレス | 糸 |
    | addresses[type eq "home"].locality（住所[タイプ＝「自宅」].地域） | 糸 |
    | アドレス[type eq "home"].リージョン | 糸 |
    | アドレス[タイプ eq "home"].郵便番号 | 糸 |
    | N/A | 糸 |
    | アドレス[type eq "ホーム"].フォーマット済み | 糸 |
    | addresses[type eq "その他"].streetAddress | 糸 |
    | 住所[タイプ eq "その他"].市区町村 | 糸 |
    | addresses[type eq "その他"].地域 | 糸 |
    | 住所[タイプ eq "その他"].郵便番号 | 糸 |
    | 住所[タイプ eq "その他"].国 | 糸 |
    | roles[primary eq "True"].display | 糸 |
    | roles[primary eq "主要"]。タイプ | 糸 |
    | 役割[主要 eq "True"].値 | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:proxyAddresses | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute1 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute2 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute3 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute4 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute5 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute6 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute7 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute8 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute9 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute10 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute11 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute12 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute13 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute14 | 糸 |
    | urn:ietf:params:scim:schemas:extension:GlobalRelay:2.0:User:extensionAttribute15 | 糸 |
12. **[グループ]** を選びます。
13. Microsoft Entra IDからグローバル リレー ID 同期に同期されるグループ属性を、**Attribute-Mapping** セクションで確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作のグローバル リレー ID 同期のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | members | リファレンス |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、組織内でより広範に展開する前に、少数のユーザーとの同期を検証します。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/globalone-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に EY GlobalOne を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/globalone-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と EY GlobalOne の間のシングル サインオンを構成する方法について説明します。

この記事では、EY GlobalOne と Microsoft Entra ID を統合する方法について説明します。 EY GlobalOne を Microsoft Entra ID と統合すると、次のことが可能になります。

- EY GlobalOne にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで EY GlobalOne に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- EY GlobalOne シングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- EY GlobalOne では、**SP および IDP** によって開始される SSO がサポートされます。
- EY GlobalOne では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの EY GlobalOne の追加

Microsoft Entra ID への EY GlobalOne の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に EY GlobalOne を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に進みます。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「EY GlobalOne**」と入力します。
4. 結果パネルから **EY GlobalOne** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### EY GlobalOne に対する Microsoft Entra SSO を構成してテストする

**B. Simon** というテスト ユーザーを使用して、EY GlobalOne に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、EY GlobalOne での関連ユーザーとの間にリンク関係を確立する必要があります。

EY GlobalOne 用に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. ユーザーがこの機能を使用できるように **Microsoft Entra SSO を構成**します。
    1. B. Simon で Microsoft Entra のシングル サインオンをテストする Microsoft **Entra テスト ユーザーを作成**します。
    2. **Microsoft Entra テスト ユーザーを割り当てて** 、B. Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **EY GlobalOne SSO を構成**して、アプリケーション側で SSO 設定を構成します。
    1. **EY GlobalOne のテスト ユーザーの作成** - EY GlobalOne で B. Simon に対応するユーザーを作成し、Microsoft Entra の B. Simon にリンクさせます。
3. **SSO をテスト** して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**EY GlobalOne** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. EY GlobalOne アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: [編集] アイコンが選択されている [ユーザー属性] セクションを示すスクリーンショット。]
7. その他に、EY GlobalOne アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの名字 |
    | Email | ユーザーのメールアドレス |
    | [会社] | `<YOUR COMPANY NAME>` |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [新しい要求の追加] と [保存] アクションが強調表示されている [ユーザー要求] セクションを示すスクリーンショット。]

    [Image: 画像]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. [ソース] を **[属性**] として選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. [ **OK] を選択する**

    g. **[保存] を選択します**。
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **EY GlobalOne のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### EY GlobalOne SSO の構成

**EY GlobalOne** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、アプリケーション構成からコピーした適切な URL を [EY GlobalOne サポート チーム](mailto:globalone.support@ey.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### EY GlobalOne のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを EY GlobalOne に作成します。 EY GlobalOne では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 EY GlobalOne にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる EY GlobalOne サインオン URL にリダイレクトされます。
- EY GlobalOne のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した EY GlobalOne に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [EY GlobalOne] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した EY GlobalOne に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/globesmart-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に GlobeSmart を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/globesmart-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GlobeSmart の間にシングル サインオンを構成する方法について説明します。

この記事では、GlobeSmart と Microsoft Entra ID を統合する方法について説明します。 GlobeSmart を Microsoft Entra ID と統合すると、次のことができます。

- GlobeSmart にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して GlobeSmart に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GlobeSmart でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- GlobeSmart では、**SP および IDP によって開始される SSO** がサポートされます。
- GlobeSmart では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの GlobeSmart の追加

Microsoft Entra ID への GlobeSmart の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに GlobeSmart を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「GlobeSmart**」と入力します。
4. 結果パネルから **GlobeSmart** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GlobeSmart 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、GlobeSmart に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと GlobeSmart の関連ユーザーとの間にリンク関係を確立する必要があります。

GlobeSmart で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GlobeSmart SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GlobeSmart のテストユーザーを作成 -**Microsoft Entra 上の B.Simon に対応するユーザーを GlobeSmart 内で作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**GlobeSmart**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。

    | 環境 | URL |
    | --- | --- |
    | サンドボックス | `urn:auth0:aperianglobal-staging:<INSTANCE_NAME>` |
    | 生産 | `urn:auth0:aperianglobal-production:<INSTANCE_NAME>` |
    |  |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | サンドボックス | `https://aperianglobal-staging.auth0.com/login/callback?connection=<INSTANCE_NAME>` |
    | 生産 | `https://auth.aperianglobal.com/login/callback?connection=<INSTANCE_NAME>` |
    |  |  |
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | サンドボックス | `https://staging.aperianglobal.com?sp=<INSTANCE_NAME>` |
    | 生産 | `https://globesmart.aperianglobal.com?sp=<INSTANCE_NAME>` |
    |  |  |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、GlobeSmart クライアント サポート チーム](mailto:support@aperianglobal.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. GlobeSmart アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、GlobeSmart アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | ユーザーID | ユーザー.ユーザープリンシパルネーム |
    | メール | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **GlobeSmart のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GlobeSmart SSO の構成

**GlobeSmart** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [GlobeSmart サポート チーム](mailto:support@aperianglobal.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### GlobeSmart のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを GlobeSmart に作成します。 GlobeSmart では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 GlobeSmart にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる GlobeSmart のサインオン URL にリダイレクトされます。
- GlobeSmart のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した GlobeSmart に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [GlobeSmart] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した GlobeSmart に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/goalquest-tutorial"} -->
## Microsoft Entra ID で GoalQuest for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/goalquest-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GoalQuest との間でシングル サインオンを構成する方法について説明します。

この記事では、GoalQuest と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID に GoalQuest を統合すると、次の利点が得られます。

- どのユーザーが GoalQuest にアクセスできるかを Microsoft Entra ID で制御できます。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して GoalQuest に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- GoalQuest でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- GoalQuest では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーから GoalQuest を追加する

Microsoft Entra ID への GoalQuest の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから GoalQuest を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「GoalQuest**」と入力します。
4. 結果パネルから **GoalQuest** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GoalQuest 向けに Microsoft Entra の SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、GoalQuest に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと GoalQuest の関連ユーザーとの間にリンク関係を確立する必要があります。

GoalQuest で Microsoft Entra の SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GoalQuest の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GoalQuest テスト ユーザーの作成** - GoalQuest で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**GoalQuest**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. GoalQuest アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成イメージを示すスクリーンショット。]
7. その他に、GoalQuest アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | employee ID (従業員 ID) | user.employeeid |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GoalQuest SSO の構成

**GoalQuest** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[GoalQuest サポート チーム](mailto:goalquest@biworldwide.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### GoalQuest テスト ユーザーの作成

このセクションでは、GoalQuest で Britta Simon というユーザーを作成します。 [GoalQuest サポート チーム](mailto:goalquest@biworldwide.com)と協力して、GoalQuest プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した GoalQuest に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [GoalQuest] タイルを選択すると、SSO を設定した GoalQuest に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/gofluent-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に goFLUENT を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/gofluent-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と goFLUENT との間でシングル サインオンを構成する方法について説明します。

この記事では、Microsoft Entra ID に goFLUENT を統合する方法について説明します。goFLUENT は世界をリードする言語トレーニング プロバイダーであり、高度にパーソナライズした学習体験を提供して、受講者の自信増進、キャリア成長の支援、包括的なグローバル文化の確立を促します。 Microsoft Entra ID に goFLUENT を統合すると、次の利点が得られます。

- どのユーザーが goFLUENT にアクセスできるかを Microsoft Entra ID で制御できます。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して goFLUENT に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

goFLUENT 用の Microsoft Entra シングル サインオンをテスト環境で構成してテストします。 goFLUENT では、 **SP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングがサポートされます。

### [前提条件]

Microsoft Entra ID に goFLUENT を統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- goFLUENT のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから goFLUENT アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから goFLUENT を追加する

Microsoft Entra アプリケーション ギャラリーから goFLUENT を追加して、goFLUENT でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[goFLUENT]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://CustomerName.gofluent.com/login/samlconnector?client=<CustomerName>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.gofluent.com/login/samlconnector?client=<CustomerName>`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.gofluent.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [goFLUENT クライアント サポート チーム](mailto:presales-team@gofluent.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **goFLUENT のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### goFLUENT の SSO を構成する

**goFLUENT** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [goFLUENT サポート チーム](mailto:presales-team@gofluent.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### goFLUENT のテスト ユーザーを作成する

このセクションでは、goFLUENT で B.Simon というユーザーを作成します。 goFLUENT は、Just-In-Time ユーザー プロビジョニングをサポートします。この設定は既定で有効になります。 このセクションにはアクション項目はありません。 goFLUENT にユーザーがまだ存在していない場合、一般に認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる goFLUENT サインオン URL にリダイレクトされます。
- goFLUENT のサインオン URL に直接アクセスし、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで goFLUENT タイルを選択すると、このオプションは goFLUENT のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/golinks-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に GoLinks を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/golinks-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: Microsoft Entra IDから GoLinks にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために GoLinks と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 設定されると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを自動的に [GoLinks](https://www.golinks.io) にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- GoLinks でユーザーを作成する
- アクセスが不要になった場合に GoLinks のユーザーを削除する
- Microsoft Entra IDと GoLinks の間でユーザー属性の同期を維持する
- GoLinks への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/golinks-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Enterprise プラン](https://www.golinks.io/pricing.php)の GoLinks テナント。
- 管理者アクセス権を持つ [GoLinks](https://www.golinks.io) のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとGoLinksの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように GoLinks を構成する

1. テナント URL は `https://api.golinks.io/scim/v2` です。 この値は、GoLinks アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドに入力されます。
2. **シークレット トークン**については、support@golinks.ioまたはカスタマー サクセス マネージャーの GoLinks サポート チームにお問い合わせください。 この値は、GoLinks アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから GoLinks を追加する

Microsoft Entra アプリケーション ギャラリーから GoLinks を追加して、GoLinks へのプロビジョニングの管理を開始します。 SSO のために GoLinks を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: GoLinks への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて GoLinks でユーザーやグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで GoLinks の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **GoLinks**] を選択します。

    [Image: アプリケーションの一覧の [GoLinks] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、GoLinks テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが GoLinks に接続できることを確認します。 接続に失敗した場合は、GoLinks アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. Microsoft Entra IDから GoLinks に同期されるユーザー属性を、**Attribute-Mapping** セクションで確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で GoLinks のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、GoLinks API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | externalId | 糸 |  |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/golinks-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に GoLinks を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/golinks-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GoLinks 間のシングル サインオンを構成する方法について説明します。

この記事では、GoLinks と Microsoft Entra ID を統合する方法について説明します。 GoLinks を Microsoft Entra ID と統合すると、次のことが可能になります。

- GoLinks にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで GoLinks に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GoLinks でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- GoLinks では、**SP 開始 SSO** および **IDP 開始 SSO** がサポートされます。
- GoLinks では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。
- GoLinks では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/golinks-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの GoLinks の追加

Microsoft Entra ID への GoLinks の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に GoLinks を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「GoLinks**」と入力します。
4. 結果パネルから **GoLinks** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GoLinks に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、GoLinks に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと GoLinks の関連ユーザー間にリンク関係を確立する必要があります。

GoLinks に対して Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GoLinks SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GoLinks テストユーザーの作成** - B.Simon に対応するユーザーを GoLinks 内に作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**GoLinks**&gt;**シングルサインオン**に進みます。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.golinks.io/saml.php`
7. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **GoLinks のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GoLinks SSO の構成

**GoLinks** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [GoLinks サポート チーム](mailto:support@golinks.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### GoLinks のテスト ユーザーを作成する

このセクションでは、GoLinks で Britta Simon というユーザーを作成します。 GoLinks では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 GoLinks にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

GoLinks では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/golinks-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる GoLinks のサインオン URL にリダイレクトされます。
- GoLinks のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した GoLinks に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [GoLinks] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した GoLinks に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/gong-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Gong を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/gong-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: Microsoft Entra IDから Gong にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Gong と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID Microsoft Entra プロビジョニング サービスを使用して、[Gong](https://www.gong.io/) にユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Gong でユーザーを作成します。
- アクセスが不要になった場合は、Gong のユーザーを削除します。
- Microsoft Entra IDと Gong の間でユーザー属性の同期を維持します。
- Gong でグループとグループ メンバーシップをプロビジョニングする。
- コード認証許可フロー認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- **技術管理者**特権を持つ Gong のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとGongの間でデータを対応付ける方法を決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Gong を構成する

1. &gt; 会社の設定ページ&gt;**PEOPLE**領域に移動します。
2. プロビジョニング ソースとして **Microsoft Entra ID** を選択します。
3. データ キャプチャ、ワークスペース、アクセス許可の設定をMicrosoft Entra グループに割り当てるには:

    1. [ **設定の割り当て]** 領域で、[ **割り当ての追加**] を選択します。
    2. 割り当てに名前を付けます。
    3. **Microsoft Entraグループ**領域で、設定を定義するMicrosoft Entraグループを選択します。
    4. [ **データ キャプチャ** ] 領域で、ホーム ワークスペースと、このグループに属するユーザーのデータ キャプチャ設定を選択します。
    5. [ **ワークスペースとアクセス許可]** 領域で、組織内の他のワークスペースのアクセス許可プロファイルを設定します。
    6. [ **更新設定]**領域で、この割り当ての設定を管理する方法を定義します。
        - Gong でこの割り当てのユーザーのデータ キャプチャとアクセス許可の設定を管理するには、[ **手動編集** ] を選択します。 割り当てを作成した後: Microsoft Entra IDでグループ設定に変更を加えた場合、その設定は Gong にプッシュされません。 ただし、Gong でグループ設定を手動で編集することはできます。
        - (推奨)[**自動更新** を選択すると、Gong のデータ キャプチャとアクセス許可の設定をMicrosoft Entra ID制御できます。 割り当てを作成する場合にのみ、Gong でデータ キャプチャとアクセス許可の設定を定義します。 その後、他の変更は、Microsoft Entra IDからプッシュされたときに、この割り当てを持つグループ内のユーザーにのみ適用されます。
    7. **割り当ての追加** を選択します。
4. 割り当てがない組織の場合 (手順 3) は、自動的にプロビジョニングされたユーザーに適用するアクセス許可プロファイルを選択します。

    [アクセス許可プロファイルの詳細。](https://help.gong.io/hc/en-us/articles/360028568911#UUID-34baef91-0aba-1295-4032-ff49102cb182)
5. マネージャーの **プロビジョニング設定** 領域で、次の手順を実行します。

    1. **[新しいチーム メンバーがインポートされたときに、記録されたチームを含むダイレクト マネージャーに通知**する] を選択して、チーム マネージャーをループ内に保持します。
    2. 一部 **のマネージャーは、チームのデータ キャプチャをオンまたはオフ** にして、チーム マネージャーに何らかの自律性を与えることができます。

    ヒント

    詳細については、 [チーム メンバー](https://help.gong.io/hc/en-us/articles/360042352912#UUID-0d3df83a-44d1-11b9-ddf5-3ec649c2f594) のプロビジョニングに関する FAQ の記事の「マネージャーのプロビジョニング設定とは」を参照してください。
6. [ **更新]** を選択して設定を保存します。

注

後でプロビジョニング ソースを Microsoft Entra ID から変更した後、Microsoft Entra ID プロビジョニングに戻る場合は、Microsoft Entra IDに再認証する必要があります。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Gong を追加する

Microsoft Entra アプリケーション ギャラリーから Gong を追加して、Gong へのプロビジョニングの管理を開始します。 SSO 用に Gong を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Gong への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Gong でユーザーやグループを作成、更新、無効化するように、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Gong の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Gong**] を選択します。

    [Image: アプリケーションの一覧の [Gong] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Gong テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Gong に接続できることを確認します。 接続に失敗した場合は、Gong アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから Gong に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Gong のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Gong API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Gong で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |  |
    | 活動中 | ブール値 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | externalId | 糸 |  |  |
    | ロケール | 糸 |  |  |
    | タイムゾーン | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Gong:2.0:User:stateOrProvince | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Gong:2.0:User:country | 糸 |  |  |
13. **[マッピング]** セクションで、**[Microsoft Entra グループを Gong に同期する]** を選択します。
14. **Attribute-Mapping** セクションで、Microsoft Entra IDから Gong に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Gong のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Gong で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
15. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
16. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
17. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### 変更履歴

- 2022 年 3 月 23 日 - **グループ プロビジョニング**のサポートを追加しました。
- 2022 年 4 月 21 日 - **emails[type eq "work"].value** が必須属性としてマークされました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/goodpractice-toolkit-tutorial"} -->
## Microsoft Entra ID で Mind Tools Toolkit for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/goodpractice-toolkit-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mind Tools Toolkit の間でシングル サインオンを構成する方法について確認します。

この記事では、Mind Tools Toolkit と Microsoft Entra ID を統合する方法について説明します。 Mind Tools Toolkit と Microsoft Entra ID を統合すると、次のことができます:

- Mind Tools Toolkit にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Mind Tools Toolkit に自動的にサインイン (シングル サインオン) するように設定できます。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Mind Tools Toolkit サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Mind Tools Toolkit では、SP Initiated SSO がサポートされます。
- Mind Tools Toolkit では、Just-In-Time ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Mind Tools Toolkit の追加

Microsoft Entra ID への Mind Tools Toolkit の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Mind Tools Toolkit を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Mind Tools Toolkit**」と入力します。
4. 検索結果から **[Mind Tools Toolkit]** を選択し、そのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Mind Tools Toolkit 用に Microsoft Entra SSO を構成してテストする

**B. Simon** というテスト ユーザーを使用して、Mind Tools Toolkit に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Mind Tools Toolkit の関連ユーザーとの間にリンク関係を確立する必要があります。

Mind Tools Toolkit に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mind Tools Toolkit の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mind Tools Toolkit のテスト ユーザーの作成** - Mind Tools Toolkit で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Mind Tools Toolkit**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションの **[サインオン URL]** ボックスに、`https://app.goodpractice.net/#/<subscriptionUrl>/s/<LOCATION_ID>` というパターンの URL を入力します。

    注

    **サインオン URL** は、実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[Mind Tools Toolkit クライアント サポート チーム](mailto:support@goodpractice.com)にお問い合わせください。
6. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[SAML 署名証明書]** セクションに移動します。 **[フェデレーション メタデータ XML]** の右側にある **[ダウンロード]** を選択して、XML テキストをダウンロードし、お使いのコンピューターに保存します。 XML コンテンツは、選択したオプションによって異なります。

    [Image: [フェデレーション メタデータ XML] の横にある [ダウンロード] が強調表示された [SAML 署名証明書] セクション]
7. **[Mind Tools Toolkit のセットアップ]** セクションで、次の URL のうち必要なものをコピーします。

    [Image: 構成 URL が強調表示された [Mind Tools Toolkit のセットアップ] セクション]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mind Tools Toolkit の SSO の構成

**Mind Tools Toolkit** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** テキストと以前にコピーした URL を [Mind Tools Toolkit サポート チーム](mailto:support@goodpractice.com)に送信します。 この設定が構成され、SAML SSO 接続が両側で正しく行われます。

#### Mind Tools Toolkit のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Mind Tools Toolkit に作成します。 Mind Tools Toolkit では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Mind Tools Toolkit にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Mind Tools Toolkit のサインオン URL にリダイレクトされます。
- Mind Tools Toolkit のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Mind Tools Toolkit] タイルを選択すると、このオプションは Mind Tools Toolkit のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/google-apps-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Google Cloud/G Suite Connector by Microsoft を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/google-apps-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-20
- Summary: Microsoft Entra ID と Google Cloud/G Suite Connector by Microsoft の間でシングル サインオンを構成する方法について説明します。

この記事では、Google Cloud / G Suite Connector by Microsoft と Microsoft Entra ID を統合する方法について説明します。 Google Cloud/G Suite Connector by Microsoft を Microsoft Entra ID を統合すると、次のことができます。

- Google Cloud/G Suite Connector by Microsoft にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Google Cloud/G Suite Connector by Microsoft に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Google Cloud / G Suite Connector by Microsoft でのシングル サインオン (SSO) が有効なサブスクリプション。
- Google Apps サブスクリプションまたは Google Cloud Platform サブスクリプション

注

この記事の手順をテストするために、運用環境を使用することはお勧めしません。 このドキュメントは、新しいユーザー シングル サインオン エクスペリエンスを使用して作成されました。 まだ古いものを使用している場合、セットアップは異なります。 G-Suite アプリケーションのシングル サインオン設定で、新しいエクスペリエンスを有効にすることができます。 **Microsoft Entra ID**&gt;**Enterprise アプリケーション**に移動し、**Google Cloud / G Suite Connector by Microsoft** を選択し、[**シングル サインオン**] を選択して、[**新しいエクスペリエンスを試す**] を選択します。

この記事の手順をテストするには、次の推奨事項に従う必要があります。

- 運用環境は、必要な場合を除き、使用しないでください。
- サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。

### 最近の変更

Google からの最近の更新により、ユーザー グループをサードパーティの SSO プロファイルに追加できるようになりました。 これにより、SSO 設定の割り当てをより細かく制御できます。 SSO プロファイルの割り当てを作成できるようになりました。これにより、会社全体を一度に移動するのではなく、段階的にユーザーを移行できます。 この領域では、エンティティ ID と ACS URL を含む SP の詳細が表示されます。ここでは、応答とエンティティのために Azure Apps に追加する必要があります。

### よく寄せられる質問

1. **Q: この統合は、Google Cloud Platform SSO と Microsoft Entra ID の統合をサポートしていますか?**

    A:はい。 Google Cloud Platform と Google Apps は同じ認証プラットフォームを共有します。 そのため、GCP の統合を実行するには、Google Apps で SSO を構成する必要があります。
2. **Q: Chromebook やその他の Chrome デバイスは Microsoft Entra シングル サインオンと互換性がありますか?**

    A: はい。ユーザーは Microsoft Entra の資格情報を使用して、Chromebook デバイスにサインインすることができます。 ユーザーが資格情報の入力を 2 回求められる理由については、 [この Google Cloud/G Suite Connector by Microsoft サポート記事](https://support.google.com/chrome/a/answer/6060880) を参照してください。
3. **Q: シングル サインオンを有効にした場合、ユーザーは Microsoft Entra の資格情報を使用して、Google Classroom、GMail、Google Drive、YouTube などの Google 製品にサインインできるようになりますか?**

    A: はい。組織で有効または無効にする Google Cloud/G Suite Connector by Microsoft の選択に応じて異なります。
4. **Q: Microsoft ユーザーが Google Cloud/G Suite Connector のサブセットに対してのみシングル サインオンを有効にすることはできますか?**

    A: はい。SSO プロファイルは、Google Workspace のユーザー、組織単位、またはグループごとに選択できます。

    [Image: SSO プロファイルの割り当てのスクリーンショット。]

    Google Workspace グループの SSO プロファイルを "none" として選択します。 これにより、この (Google Workspace グループ) のメンバーがサインインのために Microsoft Entra ID にリダイレクトされなくなります。
5. **Q: ユーザーが Windows 経由でサインインしている場合、ユーザーはパスワードの入力を求められることなく、Microsoft によって Google Cloud/G Suite Connector に対して自動的に認証されますか?**

    A:このシナリオを有効にするには、2 つのオプションがあります。 まず、ユーザーは [Microsoft Entra 参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)を使用して Windows 10 デバイスにサインインできます。 または、Active [Directory フェデレーション サービス (AD FS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-user-signin) 展開を介して Microsoft Entra ID へのシングル サインオンが有効になっているオンプレミスの Active Directory にドメイン参加している Windows デバイスにユーザーがサインインすることもできます。 どちらのオプションでも、Microsoft Entra ID と Google Cloud / G Suite Connector by Microsoft の間でシングル サインオンを有効にするには、次の記事の手順を実行する必要があります。
6. **Q: "無効な電子メール" エラー メッセージが表示された場合はどうすればよいですか?**

    A:このセットアップでは、ユーザーがサインインできるにはメール属性が必要です。 この属性は手動では設定できません。

    メール属性は、有効な Exchange ライセンスを持つユーザーに自動的に設定されます。 ユーザーが電子メールを有効にしていない場合は、アプリケーションがアクセス権を付与するためにこの属性を取得する必要がある場合に、このエラーを受け取ります。

    管理者アカウントで portal.office.com に移動し、管理センター、課金、サブスクリプションを選択し、Microsoft 365 サブスクリプションを選択し、[ユーザーへの割り当て] を選択し、サブスクリプションを確認するユーザーを選択し、右側のウィンドウで [ライセンスの編集] を選択します。

    Microsoft 365 ライセンスの割り当てが適用されるまでに数分かかる場合があります。 その後、user.mail 属性が自動的に設定され、問題を解決する必要があります。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Google Cloud / G Suite Connector by Microsoft では、 **SP** Initiated SSO がサポートされています。
- Google Cloud / G Suite Connector by Microsoft では、 [**自動** ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/g-suite-provisioning-tutorial)。

### ギャラリーから Google Cloud / G Suite Connector by Microsoft を追加する

Microsoft Entra ID への Google Cloud / G Suite Connector by Microsoft の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Google Cloud / G Suite Connector by Microsoft を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Google Cloud/ G Suite Connector by Microsoft**」と入力します。
4. 結果パネルから **Google Cloud/ G Suite Connector by Microsoft** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Google Cloud / G Suite Connector by Microsoft に対する Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Google Cloud/ G Suite Connector by Microsoft に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Google Cloud/G Suite Connector by Microsoft の関連ユーザーとの間にリンク関係を確立する必要があります。

Google Cloud/G Suite Connector by Microsoft に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Google Cloud/G Suite Connector by Microsoft SSO**を構成する - アプリケーション側でシングル サインオン設定を構成します。
    1. **Microsoft テストユーザー用 Google Cloud/G Suite Connector の作成 -** Microsoft の Google Cloud/G Suite Connector で B.Simon に対応するユーザーを作成し、それを Microsoft Entra の B.Simon にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Google Cloud / G Suite Connector by Microsoft**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **Gmail** 用に構成する場合は、[**基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `google.com/a/<customer-domain>` |
    | `google.com` |
    | `https://google.com` |
    | `https://google.com/a/<customer-domain>` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://www.google.com` |
    | `https://www.google.com/a/<customer-domain>` |

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.google.com/a/<customer-domain>/ServiceLogin?continue=https://mail.google.com`
6. [ **基本的な SAML 構成]** セクションで、 **Google Cloud Platform** 用に構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `google.com/a/<customer-domain>` |
    | `google.com` |
    | `https://google.com` |
    | `https://google.com/a/<customer-domain>` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://www.google.com/acs` |
    | `https://www.google.com/a/<customer-domain>/acs` |

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.google.com/a/<customer-domain>/ServiceLogin?continue=https://console.cloud.google.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 Google Cloud / G Suite Connector by Microsoft では、シングル サインオン構成ではエンティティ ID/識別子の値が提供されないため、 **ドメイン固有の発行者** オプションをオフにすると、[識別子] の値が `google.com`。 **ドメイン固有の発行者**オプションをオンにすると、`google.com/a/<yourdomainname.com>`。 **ドメイン固有の発行者**オプションをオンまたはオフにするには、記事の後半で説明する**「Google Cloud/ G Suite Connector by Microsoft SSO の構成**」セクションに移動する必要があります。 詳細については、 [Google Cloud/G Suite Connector by Microsoft クライアント サポート チーム](https://www.google.com/contact/)にお問い合わせください。
7. Google Cloud / G Suite Connector by Microsoft アプリケーションでは、特定の形式の SAML アサーションが求められます。そのため、カスタム属性マッピングを SAML トークン属性構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、Google Cloud / G Suite Connector by Microsoft では、これがユーザーのメール アドレスにマップされることを想定しています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]

    注

    SAML 応答に Surname 属性に標準以外の ASCII 文字が含まれていないことを確認します。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **Google Cloud/ G Suite Connector by Microsoft のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

    注

    アプリに一覧表示されている既定のログアウト URL が正しくありません。 正しい URL は `https://login.microsoftonline.com/common/wsfederation?wa=wsignout1.0` です

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Google Cloud / G Suite Connector by Microsoft SSO を構成する

1. ブラウザーで新しいタブを開き、管理者アカウントを使用して [Google Cloud/ G Suite Connector by Microsoft Admin Console](https://admin.google.com/) にサインインします。
2. **メニュー -&gt; セキュリティ -&gt; Authentication -&gt; SSO with third party IDP** に移動します。

    [Image: G Suite のセキュリティ ページ。]
3. 組織の [ **サード パーティの SSO プロファイル** ] タブで、次の構成変更を行います。

    [Image: SSO を構成します。]

    ある。 **組織の SSO プロファイルを**有効にします。

    b。 Google Cloud/ G Suite Connector by Microsoft の **[サインイン ページ URL** ] フィールドに、 **ログイン URL** の値を貼り付けます。

    c. Google Cloud/ G Suite Connector by Microsoft の **[サインアウト ページ URL** ] フィールドに、 **ログアウト URL** の値を貼り付けます。

    d. Google Cloud / G Suite Connector by Microsoft で、 **確認証明書**として、以前にダウンロードした証明書をアップロードします。

    え [Microsoft Entra ID] の上記の [**基本的な SAML 構成**] セクションに記載されている注意事項に従って、[**ドメイン固有の発行者を使用**する] オプションをオンまたはオフにします。

    f. Google Cloud/ G Suite Connector by Microsoft の [ **パスワード URL の変更** ] フィールドに、次のように値を入力します。 `https://mysignins.microsoft.com/security-info/password/change`

    ジー **[保存] を選択します**。

#### Google Cloud / G Suite Connector by Microsoft のテスト ユーザーを作成する

このセクションの目的は、 [Google Cloud / G Suite Connector by Microsoft で](https://support.google.com/a/answer/33310?hl=en) B.Simon というユーザーを作成することです。 Google Cloud / G Suite Connector by Microsoft で手動で作成されたユーザーは、Microsoft 365 のログイン資格情報を使用してサインインできるようになります。

Google Cloud / G Suite Connector by Microsoft は、自動ユーザー プロビジョニングもサポートします。 自動ユーザー プロビジョニングを構成するには、まず、 [自動ユーザー プロビジョニング用に Google Cloud/ G Suite Connector by Microsoft を構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/g-suite-provisioning-tutorial)必要があります。

注

シングル サインオンをテストする前に Microsoft Entra ID でのプロビジョニングが有効にされていない場合は、既に Google Cloud / G Suite Connector by Microsoft にユーザーが存在していることを確認してください。

注

ユーザーを手動で作成する必要がある場合は、 [Google サポート チーム](https://www.google.com/contact/)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Google Cloud/G Suite Connector by Microsoft のサインオン URL にリダイレクトされます。
- Google Cloud / G Suite Connector by Microsoft のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Google Cloud/ G Suite Connector by Microsoft] タイルを選択すると、このオプションは Google Cloud/ G Suite Connector by Microsoft のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/goprofiles-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に GoProfiles を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/goprofiles-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GoProfiles の間でシングル サインオンを構成する方法について説明します。

この記事では、GoProfiles と Microsoft Entra ID を統合する方法について説明します。 GoProfiles と Microsoft Entra ID を統合すると、次のことができます。

- GoProfiles にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して GoProfiles に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GoProfiles でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- GoProfilesは**SP initiated SSO**および**IDP initiated SSO**の両方をサポートします。
- GoProfiles では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから GoProfiles を追加する

Microsoft Entra ID への GoProfiles の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に GoProfiles を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「GoProfiles**」と入力します。
4. 結果パネルから **GoProfiles** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GoProfiles の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、GoProfiles に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと GoProfiles の関連ユーザーとの間にリンク関係を確立する必要があります。

GoProfiles に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GoProfiles SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GoProfiles のテストユーザーを作成し、Microsoft Entra ID のユーザーに対応する B.Simon を GoProfiles にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**GoProfiles**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **基本的な SAML 構成** セクションでは、アプリケーションが事前に構成されており、必要な URL が既に Microsoft Entra に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.goprofiles.io/signin`
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **GoProfiles のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GoProfiles SSO の構成

1. GoProfiles 企業サイトに管理者としてログインします。
2. **設定**&gt;**ワークスペース**&gt;**シングルサインオン**に移動し&gt;、認証方法として**Azure**を選択し、**構成**を選択します。

    [Image: 構成の設定を示すスクリーンショット。]
3. **Azure** セクションで、次の手順を実行します。

    [Image: スクリーンショットは、構成を示しています。]

    1. [ **チームに対して Azure を有効にする** ] チェック ボックスをオンにします。
    2. ダウンロードした **フェデレーション メタデータ XML を** メモ帳に開き、その内容を **[ID プロバイダー メタデータ** ] ボックスに貼り付けます。
    3. [ **変更の保存] を選択します**。

#### GoProfiles テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを GoProfiles に作成します。 GoProfiles では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 GoProfiles にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる GoProfiles のサインオン URL にリダイレクトします。
- GoProfiles のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した GoProfiles に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [GoProfiles] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した GoProfiles に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/gosearch-tutorial"} -->
## Microsoft Entra ID で GoSearch for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/gosearch-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GoSearch の間でシングル サインオンを構成する方法について説明します。

この記事では、GoSearch と Microsoft Entra ID を統合する方法について説明します。 GoSearch と Microsoft Entra ID を統合すると、次のことができます。

- GoSearch にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して GoSearch に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GoSearch でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- GoSearch では、**SP および IDP** によって開始される SSO の双方がサポートされます。
- GoSearch では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから GoSearch を追加する

Microsoft Entra ID への GoSearch の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に GoSearch を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「GoSearch**」と入力します。
4. 結果パネルから **GoSearch** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GoSearch の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、GoSearch に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと GoSearch の関連ユーザーとの間にリンク関係を確立する必要があります。

GoSearch で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GoSearch SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GoSearch テスト ユーザーの作成 - GoSearch** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**GoSearch**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **基本的な SAML 構成** セクションでは、アプリケーションが事前に構成されており、必要な URL が既に Microsoft Entra に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.gosearch.ai/signin/`
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **GoSearch のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GoSearch SSO の構成

1. GoSearch 企業サイトに管理者としてログインします。
2. **設定**&gt;**ワークスペース**&gt;**シングルサインオン**に移動し&gt;、認証方法として**Azure**を選択し、**構成**を選択します。

    [Image: 構成の設定を示すスクリーンショット。]
3. **Azure** セクションで、次の手順を実行します。

    [Image: スクリーンショットは、構成を示しています。]

    1. [ **チームに対して Azure を有効にする** ] チェック ボックスをオンにします。
    2. ダウンロードした **フェデレーション メタデータ XML を** メモ帳に開き、その内容を **[ID プロバイダー メタデータ** ] ボックスに貼り付けます。
    3. [ **変更の保存] を選択します**。

#### GoSearch テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを GoSearch に作成します。 GoSearch では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 GoSearch にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる GoSearch のサインオン URL にリダイレクトします。
- GoSearch のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した GoSearch に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [GoSearch] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した GoSearch に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/goskills-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に GoSkills を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/goskills-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: ユーザー アカウントを Microsoft Entra ID から GoSkills に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザーとグループのプロビジョニングを構成するために GoSkills と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを自動的に [GoSkills](https://www.goskills.com/) にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- GoSkills でユーザーを作成します。
- アクセスが不要になった場合は、GoSkills のユーザーを削除します。
- Microsoft Entra IDと GoSkills の間でユーザー属性の同期を維持します。
- GoSkillsでグループとそのメンバーを設定します。
- GoSkills に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ GoSkills のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- Microsoft Entra IDとGoSkillsの間でマップするデータを決定します。

### 手順 2: GoSkills API キーを取得する

1. [ここで](https://www.goskills.com/login) GoSkills 管理者アカウントにログインします。
2. **[Admin**&gt;] で **API キー**を参照します。

    [Image: GoSkills 管理者ナビゲーションのスクリーンショット。]
3. 以前に API キーを使用したことがない場合は、[API キーを **有効にする] を** 選択します。

    [Image: GoSkills API キーのスタート ページのスクリーンショット。]
4. API キーの説明を入力し、[ **生成**] を選択します。

    [Image: GoSkills API キーの生成のスクリーンショット。]
5. 後の手順で使用するために、生成された API キーをコピーします。 API キーのシークレットは必ず保持してください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから GoSkills を追加する

Microsoft Entra アプリケーション ギャラリーから GoSkills を追加して、GoSkills へのプロビジョニングの管理を開始します。 SSO 用に GoSkills を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: GoSkills への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて GoSkills でユーザーとグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで GoSkills の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **GoSkills**] を選択します。

    [Image: アプリケーションの一覧の [GoSkills] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、GoSkills テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが GoSkills に接続できることを確認します。 接続に失敗した場合は、GoSkills アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから GoSkills に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で GoSkills のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、GoSkills API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。 SSO の自動プロビジョニングについては、 **externalId** 属性が **objectId** にマップされていることを確認し、GoSkills アカウント マネージャーに問い合わせて SSO プロビジョニングを有効にします。

    | 特性 | タイプ | フィルター処理でサポートされます | GoSkills で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | externalId | 糸 |  | ✓ |
13. [**Mappings**] セクションで、[**Microsoft Entra ID グループを GoSkills に同期する**] を選択します。
14. **Attribute-Mapping** セクションで、Microsoft Entra IDから GoSkills に同期されるグループ属性を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作の GoSkills のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | GoSkills で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
15. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
16. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
17. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/goto-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に GoTo を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/goto-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: Microsoft Entra IDから GoTo にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために GoTo と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID Microsoft Entra プロビジョニング サービスを使用して、[GoTo](https://www.goto.com/) にユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされる機能

- GoTo でユーザーを作成する
- アクセスが不要になった場合に GoTo のユーザーを削除する
- Microsoft Entra IDと GoTo の間でユーザー属性の同期を維持する
- GoTo でグループとグループメンバーシップを設定する
- GoTo への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/goto-tutorial) (推奨)
- コード認証許可フロー認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 少なくとも 1 つの検証済みドメインを持つ GoTo 組織センターで作成された組織
- 手順 2 に示すような、プロビジョニングを構成する[アクセス許可](https://support.goto.com/meeting/help/manage-organization-users-g2m710102)を持つ GoTo 組織センターのユーザーアカウント (たとえば、読み取りと書き込みアクセス許可を持つ組織の管理者ロール)。

### 手順 1: プロビジョニング展開を計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとGoToの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように GoTo を構成する

1. [組織センター](https://organization.logmeininc.com)にログインします。
2. アカウントのメール アドレスで使用されるドメインは、10 日以内に確認を求めるメッセージが表示されるドメインです。
3. 次のいずれかの方法を使用して、ドメインの所有権を確認できます。

    **方法 1: ドメイン ゾーン ファイルに DNS レコードを追加する。** DNS の方法を使用するには、DNS ゾーン内のメール ドメインのレベルに DNS レコードを配置します。 ドメインとして "main.com" を使用する例は、`@ IN TXT "goto-verification-code=00aa00aa-bb11-cc22-dd33-44ee44ee44ee"` または `main.com. IN TXT “goto-verification-code=00aa00aa-bb11-cc22-dd33-44ee44ee44ee”` のようになります。

    以下に詳細な手順を示します。

    1. ドメイン ホストでドメインのアカウントにサインインします。
    2. ドメインの DNS レコードを更新するためのページに移動します。
    3. ドメインの TXT レコードを探し、ドメインとサブドメインごとに TXT レコードを追加します。
    4. すべての変更を保存します。
    5. 変更が行われたことを確認するには、コマンド ラインを開き、下のいずれかのコマンドを入力します (オペレーティング システムに基づき、ドメインの例として "main.com" を使用します)。
        - Unix および Linux システムの場合: `$ dig TXT main.com`
        - Windows システムの場合: `c:\ > nslookup -type=TXT main.com`
    6. 応答は、独自の行に表示されます。

    **方法 2: 特定の Web サイトに、Web サーバー ファイルをアップロードする。** 文字列以外に空白や特殊文字を含まない、検証文字列を含むプレーンテキスト ファイルを Web サーバー ルートにアップロードします。

    - 場所: `http://<yourdomain>/goto-verification-code.txt`
    - 内容: `goto-verification-code=00aa00aa-bb11-cc22-dd33-44ee44ee44ee`
4. DNS レコードまたは TXT ファイルを追加したら、 [組織センター](https://organization.logmeininc.com) に戻り、[ **確認**] を選択します。
5. これで、ドメインを確認し、組織センターで組織を作成しました。この確認プロセスで使用されたアカウントが組織管理者になります。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから GoTo を追加する

Microsoft Entra アプリケーション ギャラリーから GoTo を追加して、GoTo へのプロビジョニングの管理を開始します。 SSO のために GoTo を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: GoTo へのユーザーの自動プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで GoTo の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[GoTo]** を選択します。

    [Image: アプリケーションの一覧の GoTo のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、[ **承認**] を選択します。 **GoTo** の承認ページにリダイレクトされます。 GoTo ユーザー名を入力し、[ **次へ** ] ボタンを選択します。 GoTo パスワードを入力し、[ **サインイン** ] ボタンを選択します。 [**Test Connection** を選択して、Microsoft Entra IDが GoTo に接続できることを確認します。 接続できない場合は、使用中の GoTo アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: 承認]

    [Image: ログイン]

    [Image: 接続]
7. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
8. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
9. **Attribute-Mapping** セクションで、Microsoft Entra IDから GoTo に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で GoTo のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が GoTo API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | エクスターナルID | 糸 |
    | 活動中 | ブール値 |
    | 名前.名 | 糸 |
    | 名前.姓 | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:コストセンター | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |
10. **Attribute-Mapping** セクションで、Microsoft Entra IDから GoTo に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で GoTo のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | ディスプレイ名 | 糸 |
    | エクスターナルID | 糸 |
    | メンバー | リファレンス |
11. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
12. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
13. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/goto-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に GoTo を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/goto-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GoTo の間のシングル サインオンを構成する方法について説明します。

この記事では、GoTo と Microsoft Entra ID を統合する方法について説明します。 GoTo と Microsoft Entra ID を統合すると、次のことができます。

- GoTo にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して GoTo に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GoTo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- GoTo では、**SP および IDP** で開始する SSO がサポートされます。
- GoTo では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/goto-provisioning-tutorial)がサポートされます。

### ギャラリーから GoTo を追加する

Microsoft Entra ID への GoTo の統合を構成するには、マネージド SaaS アプリのリストに、ギャラリーから GoTo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに **GoTo** と入力します。
4. 検索結果パネルから **[GoTo]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GoTo 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、GoTo に対する Microsoft Entra SSO 構成してテストします。 SSO を機能させるにはには、Microsoft Entra ユーザーと GoTo の関連ユーザーとの間にリンク関係を確立する必要があります。

GoTo に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GoTo SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    - **GoTo テスト ユーザーの作成** - GoTo で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**GoTo**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    ある。 **[サインオン URL]** テキスト ボックスに、URL として「`https://authentication.gotoinc.com/login?service=https%3A%2F%2Fmyaccount.gotoinc.com`」と入力します。
7. GoTo アプリケーションは、特定の形式の SAML アサーションを想定するため、カスタム属性のマッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、 **[Unique User Identifier](一意のユーザー ID)** は **user.userprincipalname** にマップされています。 GoTo アプリケーションでは、 **一意のユーザー識別子** が **user.mail** にマップされることを想定しているため、[ **編集** ] アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[GoTo の設定]** セクションで、要件に従い、適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GoTo の SSO を構成する

1. 別の Web ブラウザー ウィンドウで、GoTo の企業サイトに管理者としてサインインする
2. **[ID プロバイダー]** タブに移動し、前にコピーした **[フェデレーション メタデータ URL]** を **[メタデータ URL]** テキスト ボックスに貼り付けます。

    [Image: フェデレーション メタデータ URL のスクリーンショット。]
3. **保存** を選択します。

#### GoTo のテスト ユーザーを作成する

1. 別のブラウザー ウィンドウで、GoTo の Web サイトに管理者としてログインします。
2. [ **ユーザー** ] タブに移動し、[ **ユーザーの追加**] を選択します。

    [Image: [Add a user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ボタンのスクリーンショット。]
3. 次のページの必須フィールドに入力し、[ **保存]** を選択します。

    [Image: ユーザー フィールドのスクリーンショット。]

注

GoTo では、自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/goto-provisioning-tutorial)を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる GoTo サインオン URL にリダイレクトされます。
- GoTo のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した GoTo に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [GoTo] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した GoTo に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/govwin-iq-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に GovWin IQ を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/govwin-iq-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GovWin IQ 間にシングル サインオンを構成する方法について説明します。

この記事では、GovWin IQ と Microsoft Entra ID を統合する方法について説明します。 Deltek の GovWin IQ は、米国連邦政府、州政府、地方政府、カナダ政府向けに最も包括的な市場インテリジェンスを提供する業界をリードするプラットフォームです。 GovWin IQ を Microsoft Entra ID と統合すると、次のことができます:

- GovWin IQ にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して GovWin IQ に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- アクティブな GovWin IQ サブスクリプション。 シングル サインオンは無料で有効にできます。 Customer Success Manager で、SAML SSO 管理として組織のユーザーが次の手順を実行できるようにしていることを確認します。
- すべてのユーザーは、GovWin IQ でプロビジョニングされたものと同じメール アドレスを Azure に持っている必要があります。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- GovWin IQ では、 **SP** によって開始される SSO のみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの GovWin IQ の追加

Microsoft Entra ID への GovWin IQ の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに GovWin IQ を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に進みます。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「GovWin IQ**」と入力します。
4. 結果パネルから **GovWin IQ** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GovWin IQ 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、GovWin IQ に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと GovWin IQ の関連ユーザーとの間にリンク関係を確立する必要があります。

GovWin IQ に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GovWin IQ SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GovWin IQ のテストユーザーを SSO に割り当てる** - GovWin IQ で B.Simon に対応するユーザーを割り当てて、そのユーザーを Microsoft Entra ID における B.Simon 表示にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**GovWin IQ**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、URL を入力します。 `https://iq.govwin.com/cas`

    b。 [ **応答 URL** ] ボックスに、GovWin IQ 応答 URL フィールドの値を入力します。

    応答 URL は次のパターンです。 `https://iq.govwin.com/cas/login?client_name=ORG_<ID>`

    c. [ **サインオン URL** ] ボックスに、GovWIn IQ の [サインオン URL] フィールドの値を入力します。

    サインオン URL は次のパターンです。 `https://iq.govwin.com/cas/clientredirect?client_name=ORG_<ID>`

    注

    これらの値を、指定された SAML SSO 管理者がアクセスできる GovWin SAML シングル Sign-On 構成ページにある実際の応答 URL とサインオン URL で更新します。 [カスタマー サクセス マネージャー](mailto:CustomerSuccess@iq.govwin.com) にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra ID テスト ユーザーを割り当てる

このセクションでは、GovWin IQ へのアクセスを許可することで、テスト ユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

注

テスト用に選択したユーザーには、既存のアクティブな GovWin IQ アカウントが必要です。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**GovWin IQ** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] リストからテスト ユーザーを選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### GovWin IQ SSO の構成

1. SAML SSO 管理 ユーザーとして GovWin IQ 企業サイトにログインします。
2. [**\[SAML Single Sign-On Configuration\]** ページ](https://iq.govwin.com/neo/authenticationConfiguration/viewSamlSSOConfig)に移動し、次の手順を実行します。

    [Image: 構成の設定を示すスクリーンショット。]

    1. ID プロバイダー (IdP) ドロップダウンから **Azure** を選択します。
    2. **識別子 (EntityID)** の値をコピーし、この値を Microsoft Entra 管理センターの [**基本的な SAML 構成]** セクションの [**識別子**] ボックスに貼り付けます。
    3. **[応答 URL]** の値をコピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの **[応答 URL**] ボックスにこの値を貼り付けます。
    4. **[サインオン URL]** の値をコピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの **[サインオン URL**] ボックスにこの値を貼り付けます。
3. [ **メタデータ URL** ] ボックスに、Microsoft Entra 管理センターからコピーした **アプリのフェデレーション メタデータ URL を**貼り付けます。

    [Image: 構成のメタデータを示すスクリーンショット。]
4. [ **IDP メタデータの送信] を選択します**。

#### GovWin IQ テスト ユーザーを SSO に割り当てる

1. [**\[SAML Single Sign-On Configuration\]** ページ](https://iq.govwin.com/neo/authenticationConfiguration/viewSamlSSOConfig)で、[**除外されたユーザー**] タブに移動し、[**SSO から除外するユーザーの選択**] を選択します。

    [Image: ページからユーザーを除外する方法を示すスクリーンショット。]

    注

    ここでは、SSO に含めるまたは SSO から除外するユーザーを選択できます。 webservices サブスクリプションがある場合、webservices ユーザーは常に SSO から除外する必要があります。
2. 次に、テスト目的 **で [すべてのユーザーを SSO から除外]** を選択します。 これは、SSO のテスト中にユーザーの既存のアクセスに影響を与えないようにするためです。
3. 次に、テスト ユーザーを選択し、[選択したユーザーを SSO に追加] を選択します。
4. テストが成功したら、SSO を有効にする残りのユーザーを追加します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

注

構成が同期されるまでに最大 10 分かかる場合があります。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる GovWin IQ のサインオン URL にリダイレクトします。
- GovWin IQ のサインオン URL に直接アクセスし、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [GovWin IQ] タイルを選択すると、このオプションは GovWin IQ のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/gr8-people-tutorial"} -->
## Microsoft Entra ID で gr8 People for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/gr8-people-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と gr8 People の間でシングル サインオンを構成する方法について説明します。

この記事では、gr8 People と Microsoft Entra ID を統合する方法について説明します。 gr8 People と Microsoft Entra ID を統合すると、次のことができます。

- gr8 People にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントで gr8 People に自動的にサインイン (シングル サインオン) するように設定できます。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- gr8 People でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- gr8 Peopleでは、**SPによるSSO**と**IDPによるSSO**がサポートされます
- gr8 People を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスを拡張したものです。 [Microsoft Defender for Cloud Apps でセッション制御を適用する方法について説明します](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)。

### ギャラリーからの gr8 People の追加

Microsoft Entra ID への gr8 People の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に gr8 People を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「gr8 People」**と入力します。
4. 結果パネルから **gr8 People** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### gr8 People の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、gr8 People に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと gr8 People の関連ユーザーとの間にリンク関係を確立する必要があります。

gr8 People に対して Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **gr8 People SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **gr8 People のテスト ユーザーの作成** - gr8 People で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**gr8 People**&gt;**シングルサインオン**に移動してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://apps.gr8people.com/sso/metadata/<CUSTOMID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.gr8people.com/sso/<CUSTOMID>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.gr8people.com/sso/<CUSTOMID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [gr8 People クライアント サポート チーム](mailto:support@gr8people.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **gr8 People のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### gr8 People の SSO の構成

**gr8 People** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [gr8 People サポート チーム](mailto:support@gr8people.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### gr8 People のテスト ユーザーの作成

このセクションでは、gr8 People で Britta Simon というユーザーを作成します。 [gr8 People サポート チーム](mailto:support@gr8people.com)と協力して、gr8 People プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [gr8 People] タイルを選択すると、SSO を設定した gr8 People に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/gradle-enterprise-tutorial"} -->
## Microsoft Entra ID で Gradle Enterprise を使ってシングルサインオンを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/gradle-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Gradle Enterprise の間のシングル サインオンを構成する方法について説明します。

この記事では、Gradle Enterprise と Microsoft Entra ID を統合する方法について説明します。 Gradle Enterprise と Microsoft Entra ID を統合すると、次のことが可能になります。

- Gradle Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Gradle Enterprise に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Gradle Enterprise でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Gradle Enterprise では、**SP** Initiated SSO がサポートされます

### ギャラリーからの Gradle Enterprise の追加

Microsoft Entra ID への Gradle Enterprise の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Gradle Enterprise を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Gradle Enterprise**」と入力します。
4. 結果パネルから **[Gradle Enterprise]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Gradle Enterprise 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Gradle Enterprise に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Gradle Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

Gradle Enterprise に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Gradle Enterprise の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Gradle Enterprise テストユーザーの作成** - Microsoft Entra のユーザー B.Simon にリンクされた対応ユーザーを Gradle Enterprise で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Gradle Enterprise]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CLIENT_DOMAIN>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CLIENT_DOMAIN>/keycloak/realms/gradle-enterprise`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CLIENT_DOMAIN>/keycloak/realms/gradle-enterprise/broker/saml/endpoint`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Gradle Enterprise クライアント サポート チーム](https://gradle.com/brand/)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Gradle Enterprise のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に Gradle Enterprise へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Gradle Enterprise** にアクセスします。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Gradle Enterprise の SSO の構成

**Gradle Enterprise** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Gradle Enterprise サポート チーム](https://gradle.com/brand/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Gradle Enterprise のテスト ユーザーの作成

このセクションでは、Gradle Enterprise で Britta Simon というユーザーを作成します。 [Gradle Enterprise サポート チーム](https://gradle.com/brand/)と協力して、Gradle Enterprise プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

1. [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Gradle Enterprise のサインオン URL にリダイレクトされます。
2. Gradle Enterprise のサインオン URL に直接移動し、そこからログイン フローを開始します。
3. Microsoft アクセス パネルを使用することができます。 アクセス パネルで [Gradle Enterprise] タイルを選択すると、このオプションは Gradle Enterprise のサインオン URL にリダイレクトされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/graebel-single-sign-on-with-globalconnect-tutorial"} -->
## GlobalCONNECT で Graebel シングル サインオンを構成し、Microsoft Entra ID でシングル サインオンを行う - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/graebel-single-sign-on-with-globalconnect-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-02
- Summary: globalCONNECT を利用した Graebel Single Sign On と Microsoft Entra ID の間のシングル サインオンを構成する方法について説明します。

この記事では、Graebel Single Sign On と globalCONNECT と Microsoft Entra ID を統合する方法について説明します。 globalCONNECT を利用した Graebel Single Sign On を Microsoft Entra ID と統合すると、次のことができます。

- globalCONNECT を利用した Graebel Single Sign On にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して、globalCONNECT を利用した Graebel Single Sign On に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- globalCONNECT を利用した Graebel Single Sign On でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- globalCONNECT を利用した Graebel Single Sign On では、**SP Initiated SSO と IDP Initiated SSO** の両方サポートされています。
- globalCONNECT を利用した Graebel Single Sign On では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから globalCONNECT による Graebel Single Sign On を追加する

Microsoft Entra ID への globalCONNECT による Single Sign On の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に globalCONNECT による Graebel Single Sign On を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**globalCONNECT による Graebel Single Sign On**」と入力します。
4. 結果のパネルから **[globalCONNECT による Graebel Single Sign On]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### globalCONNECT による Graebel Single Sign On 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、globalCONNECT による Graebel Single Sign On に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと globalCONNECT による Graebel Single Sign On の関連ユーザーとの間にリンク関係を確立する必要があります。

globalCONNECT による Graebel Single Sign On に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **globalCONNECT SSO による Graebel Single Sign On を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **globalCONNECT テスト ユーザーで Graebel Single Sign On を作成する - GlobalCONNECT** で Graebel Single Sign On で B.Simon に対応するユーザーを作成し、Microsoft Entra ID の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Graebel シングル サインオン**で globalCONNECT&gt;**シングル サインオン**を閲覧します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://<CUSTOMER_NAME>.graebel.com/custom/sso/so.aspx` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<CUSTOMER_NAME>.graebel.com/custom/sso/so.aspx` のパターンを使用して URL を入力します
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<CUSTOMER_NAME>.graebel.com/custom/sso/so.aspx`

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには、[globalCONNECT による Graebel Single Sign On サポート チーム](mailto:filetransfer@graebel.com)にお問い合わせください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. globalCONNECT による Graebel Single Sign On アプリケーションは特定の形式の SAML アサーションを予測しているため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の画像を示すスクリーンショット。]
8. その他に、globalCONNECT による Graebel Single Sign On アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | SAML\_SUBJECT | User.mail |
9. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[globalCONNECT による Graebel Single Sign On の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは構成 URL をコピーするように示しています。]

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

このセクションでは、B.Simon に globalCONNECT で Graebel Single Sign On へのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**globalCONNECT の Graebel シングル サインオン**に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### globalCONNECT SSO を使用して Graebel Single Sign On を構成する

**globalCONNECT による Graebel Single Sign On**側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [globalCONNECT による Graebel Single Sign On サポート チーム](mailto:filetransfer@graebel.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### globalCONNECT による Graebel Single Sign On のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを globalCONNECT による Graebel Single Sign On に作成します。 globalCONNECT を使用した Graebel シングル サインオン では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 globalCONNECT を使用した Graebel シングル サインオン にユーザーがまだ存在していない場合、認証後に新しいものが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる globalCONNECT サインオン URL を使用して Graebel シングル サインオンにリダイレクトします。
- globalCONNECT を利用した Graebel Single Sign On のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した globalCONNECT で Graebel シングル サインオンに自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [GlobalCONNECT で Graebel Single Sign On] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した globalCONNECT を使用して Graebel Single Sign On に自動的にサインインします。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/grammarly-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Grammarly を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/grammarly-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: Microsoft Entra IDから Grammarly にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Grammarly と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成されると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Grammarly](https://www.grammarly.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Grammarly でユーザーを作成する
- アクセスが不要になった場合に Grammarly でユーザーを削除する
- Microsoft Entra IDと Grammarly の間でユーザー属性の同期を維持する
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- [プロビジョニングを構成する権限](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ([Application Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [Application Owner](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications) など)。
- 管理者アクセス権を持つ Grammarly ビジネス アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra ID と Grammarly の間で  マップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Grammarly を構成する

Grammarly の担当者に連絡するか、support@grammarly.com に電子メールを送信して、プロビジョニング トークンを要求します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Grammarly を追加する

Microsoft Entra アプリケーション ギャラリーから Grammarly を追加して、Grammarly へのプロビジョニングの管理を開始します。 SSO 用に Grammarly を以前に設定している場合は、同じアプリケーションを使用できます。 統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、 [このクイック スタート](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Grammarly への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーまたはグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Grammarly の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動します。

    [Image: [エンタープライズ アプリケーション] ウィンドウを示すスクリーンショット。]
3. アプリケーションの一覧で [ **Grammarly**] を選択します。

    [Image: アプリケーションの一覧の [Grammarly] リンクを示すスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブを示すスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Grammarly テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Grammarly に接続できることを確認します。 接続に失敗した場合は、Grammarly アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Grammarly に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Grammarly のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Grammarly API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | externalId | 糸 |  |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/grammarly-tutorial"} -->
## Microsoft Entra ID で Grammarly for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/grammarly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Grammarly 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、Grammarly と Microsoft Entra ID を統合する方法について説明します。 Grammarly を Microsoft Entra ID を統合すると、次のことができます。

- Grammarly にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Grammarly に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Grammarly でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Grammarly では、**IDP** Initiated SSO がサポートされます。
- Grammarly では、[**自動化された**ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/grammarly-provisioning-tutorial) (推奨) がサポートされます。
- Grammarly では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Grammarly の追加

Microsoft Entra ID への Grammarly の統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに Grammarly を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Grammarly**」と入力します。
4. 結果のパネルから **[Grammarly]** を選択して、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Grammarly 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Grammarly に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Grammarly の関連ユーザーとの間にリンク関係を確立する必要があります。

Grammarly に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Grammarly の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Grammarlyでのテストユーザーの作成 - Grammarly** における B.Simon に対応するユーザーを作成し、それを Microsoft Entra の表象にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Grammarly**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Grammarly アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | メールアドレス | ユーザー.プリンシパル名 |
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Grammarly のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に Grammarly へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Grammarly**にアクセスします。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Grammarly SSO の構成

**Grammarly** でシングル サインオンを構成するには、**ログイン URL**、**Microsoft Entra 識別子**、ダウンロードした**証明書 (Base64)** を Grammarly の管理パネルにコピーする必要があります。 方法については、[こちら](https://support.grammarly.com/hc/en-us/articles/360048683092-How-do-I-set-up-SAML-single-sign-on-for-my-Grammarly-Business-account-)をご覧ください。

#### Grammarly テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Grammarly で作成します。 Grammarly では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Grammarly にユーザーがまだ存在していない場合は、認証後にそれが新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Grammarly に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Grammarly] タイルを選択すると、SSO を設定した Grammarly に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/granite-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Granite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/granite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Granite の間のシングル サインオンを構成する方法について説明します。

この記事では、Granite と Microsoft Entra ID を統合する方法について説明します。 Granite を Microsoft Entra ID と統合すると、次のことが可能になります。

- Granite にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Granite に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Granite でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Granite では、**SP** Initiated SSO がサポートされます。
- Granite では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Granite の追加

Microsoft Entra ID への Granite の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Granite を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Granite**」と入力します。
4. 結果のパネルから **[Granite]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Granite 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Granite に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Granite の関連ユーザーとの間にリンク関係を確立する必要があります。

Granite に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Granite の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Granite テスト ユーザーの作成** - Granite で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Granite]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`<Customer_Name>.granitegrc.com` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer_Name>.granitegrc.com/simplesaml/module.php/saml/sp/saml2-acs.php/default`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer_Name>.granitegrc.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Granite サポート チーム](mailto:support@granitegrc.com)に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Granite アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Granite アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ユーザー名 | user.userprincipalname |
    | groups | ユーザー.グループ |
    | company | user.companyname |
    | 部署 | user.department |
    | objectid | user.objectid (ユーザーのオブジェクトID) |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

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

このセクションでは、B.Simon に Granite へのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Granite** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Granite SSO の構成

**Granite** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Granite サポート チーム](mailto:support@granitegrc.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Granite のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Granite に作成します。 Granite では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Granite に存在しない場合は、Granite にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Granite のサインオン URL にリダイレクトします。
- Granite のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [花崗岩] タイルを選択すると、このオプションは Granite のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/grape-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Gra-Pe を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/grape-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Gra-Pe 間のシングル サインオンを構成する方法について説明します。

この記事では、Gra-Pe と Microsoft Entra ID を統合する方法について説明します。 Gra-Pe を Microsoft Entra ID と統合すると、次の利点があります。

- Gra-Pe にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが Microsoft Entra アカウントで Gra-Pe に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Gra-Pe でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Gra-Pe では、**SP** Initiated SSO がサポートされます

### ギャラリーからの Gra-Pe の追加

Microsoft Entra ID への Gra-Pe の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Gra-Pe を追加する必要があります。

**ギャラリーから Gra-Pe を追加するには、次の手順を実行します。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Gra-Pe**」と入力し、結果パネルで **Gra-Pe** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Gra-Pe]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Gra-Pe で Microsoft Entra シングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Gra-Pe の関連ユーザー間にリンク関係を確立する必要があります。

Gra-Pe で Microsoft Entra シングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Gra-Pe のシングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Gra-Pe のテストユーザーを作成** - Britta Simon に対応するユーザーを Gra-Pe で作成し、そのユーザーを Microsoft Entra での表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Gra-Pe で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Gra-Pe** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [Gra-Pe のドメインと URL] のシングル サインオン情報]

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://btm.tts.co.jp/portal/apl/SSOLogin.aspx`
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Gra-Pe のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Gra-Pe のシングル サインオンの構成

**Gra-Pe** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Gra-Pe サポート チーム](https://www.toppantravel.com/inquiry/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーを作成する

このセクションの目的は、Britta Simon というテスト ユーザーを作成することです。

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

このセクションでは、Britta Simon に Gra-Pe へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Gra-Pe]** に移動します。

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Gra-Pe]** を選択します。

    [Image: アプリケーションの一覧の Gra-Pe のリンク]
4. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
5. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。

    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

#### Gra-Pe のテスト ユーザーの作成

このセクションでは、Gra-Pe で Britta Simon というユーザーを作成します。 [Gra-Pe サポート チーム](https://www.toppantravel.com/inquiry/)と協力して、Gra-Pe プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Gra-Pe] タイルを選択すると、SSO を設定した Gra-Pe に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/greenhouse-tutorial"} -->
## Microsoft Entra ID で Greenhouse for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/greenhouse-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Greenhouse の間のシングル サインオンを構成する方法について説明します。

この記事では、Greenhouse と Microsoft Entra ID を統合する方法について説明します。 Greenhouse を Microsoft Entra ID と統合すると、次のことが可能になります。

- Greenhouse にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Greenhouse に自動的にサインインできるように設定する。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Greenhouse でのシングル サインオン (SSO) が有効なサブスクリプション。

注

この統合は、Microsoft Entra US Government Cloud 環境からも利用できます。 このアプリケーションは、Microsoft Entra 米国政府クラウドのアプリケーション ギャラリーにあります。パブリック クラウドの場合と同じように構成してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Greenhouse では、**SP および IDP** Initiated SSO がサポートされます。

### ギャラリーから Greenhouse を追加する

Microsoft Entra ID への Greenhouse の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Greenhouse を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Greenhouse**」と入力します。
4. 結果パネルから **Greenhouse** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Greenhouse 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Greenhouse に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Greenhouse の関連ユーザーとの間にリンク関係を確立する必要があります。

Greenhouse に対して Microsoft Entra の SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Greenhouse SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Greenhouse のテストユーザーを作成する** - Microsoft Entra におけるユーザーの表現とリンクされた Britta Simon の対応人物を Greenhouse で備えるため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップを実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Greenhouse]**&gt;**[シングル サインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]

注

[Greenhouse クライアント サポート チーム](https://www.greenhouse.io/contact)は、**IDP** 開始モード用に Entra ID 側のアプリケーション設定を構成することをお勧めします。 詳細情報および以下で言及されている正しい値については、Greenhouse クライアント サポート チームにお問い合わせください。

1. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、値を入力します。 `greenhouse.io`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANYNAME>.greenhouse.io/<ENTITY ID>/users/saml/consume`
2. **[サインオン URL**] テキスト ボックスは空のままにします。
3. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
4. [ **Greenhouse のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

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

このセクションでは、B.Simon に Greenhouse へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Greenhouse** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Greenhouse の SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として Greenhouse Web サイトにサインインします。
2. ** &gt; Dev Center &gt; Single Sign-On の設定画面**に移動します。

    [Image: SSO ページのスクリーンショット]
3. **[シングル サインオン]** ページで次の手順を実行します。

    [Image: SSO 構成ページのスクリーンショット]

    a. **SSO Assertion Consumer の URL 値を**コピーし、この値を [**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスに貼り付けます。

    b。 [ **エンティティ ID/発行者** ] ボックスに、前にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    c. [ **単一 Sign-On URL** ] ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。

    d. ダウンロードした **フェデレーション メタデータ XML** をメモ帳に開き、その内容を **IdP 証明書の指紋** テキスト ボックスに貼り付けます。

    e. ドロップダウンから **[名前識別子の形式]** の値を選択します。

    f. [ **テストの開始]** を選択します。

    注

    または、[**ファイルの選択**] オプションを選択して**、フェデレーション メタデータ XML** ファイルをアップロードすることもできます。

#### Greenhouse のテスト ユーザーの作成

Microsoft Entra ユーザーが Greenhouse にログインできるようにするには、ユーザーを Greenhouse にプロビジョニングする必要があります。 Greenhouse の場合、プロビジョニングは手動で行います。

注

他の Greenhouse ユーザー アカウント作成ツールや、Greenhouse から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. **Greenhouse** 企業サイトに管理者としてログインします。
2. **[&gt; ユーザーの構成] &gt; [新しいユーザー]** に移動します

    [Image: ユーザー]
3. [ **新しいユーザーの追加]** セクションで、次の手順を実行します。

    [Image: 新しいユーザーの追加 新しい]

    a. [ **Enter user emails]\(ユーザーの電子メールの入力** \) ボックスに、プロビジョニングする有効な Microsoft Entra アカウントのメール アドレスを入力します。

    b。 **[保存] を選択します**。

    注

    アカウントがアクティブになる前に、Microsoft Entra アカウント所有者に、アカウント確認用のリンクを記述した電子メールが送信されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Greenhouse のサインオン URL にリダイレクトされます。
- Greenhouse のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Greenhouse に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Greenhouse タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Greenhouse に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/greenlight-compliant-access-management-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Greenlight 準拠アクセス管理を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/greenlight-compliant-access-management-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Greenlight Compliant Access Management の間でシングル サインオンを構成する方法について説明します。

この記事では、Greenlight Compliant Access Management と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Greenlight Compliant Access Management を統合すると、次のことができます。

- Greenlight Compliant Access Management にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Greenlight Compliant Access Management に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Greenlight Compliant Access Management のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Greenlight Compliant Access Management では、**サービスプロバイダー (SP) とアイデンティティプロバイダー (IDP)** によって開始される SSO がサポートされます。
- Greenlight Compliant Access Management を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスを拡張したものです。 [Microsoft Defender for Cloud Apps でセッション制御を適用する方法について説明します](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)。

### ギャラリーからの Greenlight Compliant Access Management の追加

Microsoft Entra ID への Greenlight Compliant Access Management の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Greenlight Compliant Access Management を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動する。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Greenlight Compliant Access Management**」と入力します。
4. 結果パネルから **Greenlight Compliant Access Management** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Greenlight Compliant Access Management の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Greenlight Compliant Access Management に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Greenlight Compliant Access Management の関連ユーザーとの間にリンク関係を確立する必要があります。

Greenlight Compliant Access Management に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Greenlight Compliant Access Management SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Greenlight Compliant Access Management のテスト ユーザーの作成** - Greenlight Compliant Access Management で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Greenlight Compliant Access Management]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER>.greenlightcorp.com/ebcpresq/checkLoginSAML.do`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER>.greenlightcorp.com/ebcpresq/checkLoginSAML.do`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER>.greenlightcorp.com/ebcpresq/checkLoginSAML.do`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Greenlight 準拠アクセス管理クライアント サポート チーム](mailto:support@greenlightcorp.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Greenlight Compliant Access Management のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に Greenlight Compliant Access Management へのアクセスを許可することで、シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Greenlight Compliant Access Management**を参照してください。
3. アプリの概要ページで、[ **管理** ] セクションを見つけて、[ **ユーザーとグループ**] を選択します。

    [Image: [ユーザーとグループ] リンク]
4. [**ユーザーの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。

    [Image: [ユーザーの追加] リンク]
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Greenlight Compliant Access Management の SSO の構成

**Greenlight Compliant Access Management** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Greenlight 準拠アクセス管理サポート チーム](mailto:support@greenlightcorp.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Greenlight Compliant Access Management のテスト ユーザーの作成

このセクションでは、Greenlight Compliant Access Management で B.Simon というユーザーを作成します。 [Greenlight 準拠アクセス管理サポート チーム](mailto:support@greenlightcorp.com)と協力して、Greenlight 準拠アクセス管理プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Greenlight Compliant Access Management] タイルを選択すると、SSO を設定した Greenlight Compliant Access Management に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/greenlight-enterprise-business-controls-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Greenlight Enterprise Business Controls Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/greenlight-enterprise-business-controls-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Greenlight Enterprise Business Controls Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Greenlight Enterprise Business Controls Platform と Microsoft Entra ID を統合する方法について説明します。 Greenlight Enterprise Business Controls Platform と Microsoft Entra ID を統合すると、次のことができます。

- Greenlight Enterprise Business Controls Platform にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Greenlight Enterprise Business Controls Platform に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Greenlight Enterprise Business Controls Platform でのシングルサインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Greenlight Enterprise Business Controls Platform では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーから Greenlight Enterprise Business Controls Platform を追加する

Microsoft Entra ID への Greenlight Enterprise Business Controls Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Greenlight Enterprise Business Controls Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Greenlight Enterprise Business Controls Platform**」と入力します。
4. 結果のパネルから **[Greenlight Enterprise Business Controls Platform]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Greenlight Enterprise Business Controls Platform の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Enterprise Business Controls Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Greenlight エンタープライズ ビジネス コントロール プラットフォームの関連ユーザーとの間にリンク関係を確立する必要があります。

Greenlight Enterprise Business Controls Platform で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Greenlight Enterprise Business Controls Platform の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Greenlight Enterprise Business Controls Platform で B.Simon に対応するテストユーザーを作成し、Microsoft Entra のユーザーにリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**Greenlight Enterprise Business Controls Platform**&gt;**シングルサインオン**を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.gltcloud.com/ebcpplatform/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.gltcloud.com/ebcpplatform/saml`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.gltcloud.com/ebcpplatform/saml`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Greenlight Enterprise Business Controls Platform クライアント サポート チーム](mailto:support@greenlightcorp.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Greenlight Enterprise Business Controls Platform のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に Greenlight Enterprise Business Controls Platform へのアクセスを許可することで、シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Greenlight Enterprise Business Controls Platform** に移動します。
3. アプリの概要ページで、 **[管理]** セクションを見つけて、 **[ユーザーとグループ]** を選択します。
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Greenlight Enterprise Business Controls Platform の SSO の構成

**Greenlight Enterprise Business Controls Platform** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Greenlight Enterprise Business Controls Platform サポート チーム](mailto:support@greenlightcorp.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Greenlight Enterprise Business Controls Platform のテスト ユーザーの作成

このセクションでは、Greenlight Enterprise Business Controls Platform で B.Simon というユーザーを作成します。 [Greenlight Enterprise Business Controls Platform サポート チーム](mailto:support@greenlightcorp.com)と連携して、Greenlight Enterprise Business Controls Platform プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Greenlight Enterprise Business Controls Platform のサインオン URL にリダイレクトされます。
- Greenlight Enterprise Business Controls Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Greenlight Enterprise Business Controls Platform に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Greenlight Enterprise Business Controls Platform] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Greenlight Enterprise Business Controls Platform に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/greenlight-integration-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Greenlight Integration Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/greenlight-integration-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Greenlight Integration Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Greenlight Integration Platform と Microsoft Entra ID を統合する方法について説明します。 Greenlight Integration Platform を Microsoft Entra ID と統合すると、次のことができます。

- Greenlight Integration Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Greenlight Integration Platform に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Greenlight Integration Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Greenlight Integration Platform では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーからの Greenlight Integration Platform の追加

Microsoft Entra ID への Greenlight Integration Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Greenlight Integration Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Greenlight Integration Platform**」と入力します。
4. 結果のパネルから **[Greenlight Integration Platform]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Greenlight Integration Platform 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Greenlight Integration Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Greenlight Integration Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

Greenlight Integration Platform 用に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Greenlight Integration Platform の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Greenlight Integration Platform のテスト ユーザーの作成** - Greenlight Integration Platform で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Greenlight Integration Platform**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER>.greenlightcorp.com/ebcprtads/checkLoginSAML.do`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER>.greenlightcorp.com/ebcprtads/checkLoginSAML.do`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER>.greenlightcorp.com/ebcprtads/checkLoginSAML.do`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Greenlight Integration Platform クライアント サポート チーム](mailto:support@greenlightcorp.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Greenlight Integration Platform のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に Greenlight Integration Platform へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Greenlight Integration Platform]** に移動します。
3. アプリの概要ページで、 **[管理]** セクションを見つけて、 **[ユーザーとグループ]** を選択します。
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Greenlight Integration Platform の SSO の構成

**Greenlight Integration Platform** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Greenlight Integration Platform サポート チーム](mailto:support@greenlightcorp.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Greenlight Integration Platform のテスト ユーザーの作成

このセクションでは、Greenlight Integration Platform で B.Simon というユーザーを作成します。 [Greenlight Integration Platform サポート チーム](mailto:support@greenlightcorp.com)と連携し、Greenlight Integration Platform プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Greenlight Integration Platform のサインオン URL にリダイレクトされます。
- Greenlight Integration Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Greenlight Integration Platform に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Greenlight Integration Platform] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Greenlight Integration Platform に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/greenorbit-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に GreenOrbit を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/greenorbit-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GreenOrbit の間のシングル サインオンを構成する方法について説明します。

この記事では、GreenOrbit と Microsoft Entra ID を統合する方法について説明します。 GreenOrbit を Microsoft Entra ID と統合すると、次のことが可能になります。

- GreenOrbit にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで GreenOrbit に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GreenOrbit でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- GreenOrbit では、 **SP** Initiated SSO がサポートされます。
- GreenOrbit では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから GreenOrbit を追加する

Microsoft Entra ID への GreenOrbit の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に GreenOrbit を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「GreenOrbit**」と入力します。
4. 結果パネルから **GreenOrbit** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GreenOrbit 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、GreenOrbit に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと GreenOrbit の関連ユーザーとの間にリンク関係を確立する必要があります。

GreenOrbit に対して Microsoft Entra の SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GreenOrbit SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GreenOrbit テストユーザーの作成** - GreenOrbit 内で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 内のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**GreenOrbit**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.yourcompanydomain.extension`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.yourcompanydomain.extension`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 [GreenOrbit クライアント サポート チーム](mailto:support@greenorbit.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **GreenOrbit のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

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
5. **[作成]** を選択します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に GreenOrbit へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**GreenOrbit** を参照してください。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### GreenOrbit の SSO の構成

**GreenOrbit** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [GreenOrbit サポート チーム](mailto:support@greenorbit.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### GreenOrbit テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを GreenOrbit に作成します。 GreenOrbit では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 GreenOrbit にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる GreenOrbit のサインオン URL にリダイレクトされます。
- GreenOrbit のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [GreenOrbit] タイルを選択すると、このオプションは GreenOrbit のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/grok-learning-tutorial"} -->
## Microsoft Entra ID で Grok Learning for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/grok-learning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Grok Learning. の間のシングル サインオンを構成する方法について説明します。

この記事では、Grok Learning と Microsoft Entra ID を統合する方法について説明します。 Grok Learning を Microsoft Entra ID と統合すると、次のことが可能になります。

- Grok Learning にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Grok Learning に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Grok Learning でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Grok Learning では、**SP/IDP ** Initiated SSO がサポートされます。
- Grok Learning では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Grok Learning の追加

Microsoft Entra ID への Grok Learning の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Grok Learning を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Grok Learning**」と入力します。
4. 結果のパネルから **[Grok Learning]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Grok Learning 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Grok Learning に対する Microsoft Entra SSO を構成およびテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Grok Learning での関連ユーザーとの間にリンク関係を確立する必要があります。

Grok Learning に関する Microsoft Entra SSO を構成およびテストするには、次のステップを実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Grok Learning の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Grok Learning のテストユーザーを作成** - Grok Learning で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra における B.Simon の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Grok Learning**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.groklearning.com/sso/saml2/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.groklearning.com/sso/saml2/acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.groklearning.com/sso/saml2/login?idp=<IDP_NAME>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Grok Learning クライアント サポート チーム](mailto:sso-support@groklearning.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Grok Learning アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングをSAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の画像を示すスクリーンショット。]
8. その他に、Grok Learning アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | gn | User.givenname |
    | sn | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
    | cn | user.displayname |
    | メールアドレス | user.userprincipalname |
    | グループ | ユーザー.グループ |
    | 一意のユーザー ID | user.objectid (ユーザーのオブジェクトID) |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

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

このセクションでは、B.Simon に Grok Learning へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Grok Learning** に移動します。
3. アプリの概要ページで、 **[管理]** セクションを見つけて、 **[ユーザーとグループ]** を選択します。
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Grok Learning の SSO の構成

**Grok Learning** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Grok Learning サポート チーム](mailto:sso-support@groklearning.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Grok Learning のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Grok Learning に作成します。 Grok Learning では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Grok Learning にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Grok Learning Sign-On URL にリダイレクトされます。
- Grok Learning のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Grok Learning に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Grok Learning] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション Sign-On ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Grok Learning に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/grouptalk-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に GroupTalk を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/grouptalk-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: Microsoft Entra IDから GroupTalk にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために GroupTalk とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID Microsoft Entra プロビジョニング サービスを使用して、[GroupTalk](https://www.grouptalk.com/) にユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- GroupTalk でユーザーを作成する
- アクセスが不要になった場合に GroupTalk のユーザーを削除する
- Microsoft Entra IDと GroupTalk の間でユーザー属性の同期を維持する
- GroupTalk でグループとグループ メンバーシップをプロビジョニングする
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Admin アクセス許可がある GroupTalk のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとGroupTalkの間で[マップするデータ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように GroupTalk を構成する

1. support@grouptalk.com にある GroupTalkサポートに、Microsoft Entra IDと統合する **Tenant name** および **ID** について連絡を取ってください。
2. Microsoft Entra統合に必要なセットアップが完了したことを通知されたら、GroupTalk 管理者にログインし、組織ビューに移動します。
3. Microsoft Entra統合構成項目が表示されます。 これを選択して、**JWT (シークレット トークン)** を取得する**テナント名**と **ID を**確認します。
4. GroupTalk テナントの URL は `https://api.grouptalk.com/api/scim/` です。 前の手順で取得した **テナント URL** と **シークレット トークン** は、GroupTalk アプリケーションの [プロビジョニング] タブに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから GroupTalk を追加する

Microsoft Entra アプリケーション ギャラリーから **GroupTalk** を追加して、GroupTalk へのプロビジョニングの管理を開始します。

1. GroupTalk 管理アプリケーションにルーティングする [ **GroupTalk にサインアップ** ] ボタンを選択します。
2. 既に GroupTalk にログインしている場合は、ログアウトしてログイン画面に移動します。 [Microsoft Entra ID] タブを選択し、[**Sign in** ボタンを選択します。

    [Image: GroupTalk]
3. AD 管理者アカウントでログインし、GroupTalk アプリケーションのアクセス権を受け入れます。 この操作が完了すると、ユーザーが存在しないことを示すエラー メッセージが表示されます。 ユーザーがまだ GroupTalk にプロビジョニングされていない一方で、テナントに GroupTalk を追加したため、これは予期されるものです。
4. Azure ポータルに戻り、**GroupTalk** がエンタープライズ アプリケーションに追加されたことを確認します。

ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: GroupTalk への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで GroupTalk の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps** にアクセスします。

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **GroupTalk** を選択します。

    [Image: アプリケーションの一覧の GroupTalk リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、GroupTalk テナントの URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが GroupTalk に接続できることを確認します。 接続に失敗した場合は、GroupTalk アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから GroupTalk に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で GroupTalk のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、GroupTalk API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 電話番号[タイプ eq "携帯"].値 | 糸 | ✓ |
    | emails[type eq "仕事"].value | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:grouptalk:2.0:User:label1 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:grouptalk:2.0:User:label2 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:grouptalk:2.0:User:label3 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:grouptalk:2.0:User:label4 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:grouptalk:2.0:User:label5 | 糸 |  |
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから GroupTalk に同期されるグループ属性を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で GroupTalk のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | members | リファレンス |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:grouptalk:2.0:Group:description | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/grovo-tutorial"} -->
## Microsoft Entra ID で Grovo for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/grovo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Grovo 間にシングル サインオンを構成する方法について説明します。

この記事では、Grovo と Microsoft Entra ID を統合する方法について説明します。 Grovo を Microsoft Entra ID と統合すると、次のことができます。

- Grovo にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Grovo に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Grovo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Grovo では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Grovo では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Grovo の追加

Microsoft Entra ID へのGrovo の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Grovo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Grovo**」と入力します。
4. 結果のパネルから **[Grovo]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Grovo の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Grovo で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Grovo の関連ユーザーとの間にリンク関係を確立する必要があります。

Grovo で Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Grovo SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Grovo テスト ユーザーの作成** - B.Simon に対応するユーザーを Grovo に作成し、それを Microsoft Entra の表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Grovo**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.grovo.com/sso/saml2/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.grovo.com/sso/saml2/saml-assertion`

    c. [ **追加の URL の設定] を選択します**。

    d. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.grovo.com`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.grovo.com/sso/saml2/saml-assertion`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、リレー状態でこれらの値を更新します。 この値を取得するには、Grovo クライアント サポート チームにお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Grovo のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に Grovo へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Grovo**にアクセスする。
3. アプリの概要ページで、 **[管理]** セクションを見つけて、 **[ユーザーとグループ]** を選択します。

    [Image: [ユーザーとグループ] リンク]
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。

    [Image: [ユーザーの追加]リンク]
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Grovo SSO の構成

1. 別の Web ブラウザー ウィンドウで、Grovo に管理者としてサインインします。
2. **[管理**&gt;**Integrations]** に移動します。

    [Image: [Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) が選択されている [Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者) メニューを示すスクリーンショット。]
3. [**SP Initiated SAML 2.0**] セクションで **[SET UP]** を選択します。

    [Image: [Set up](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) ボタンが選択されている [SP Initiated SAML 2.0](SP によって開始された SAML 2.0) セクションを示すスクリーンショット。]
4. **[SP Initiated SAML 2.0](SP によって開始された SAML 2.0)** ポップアップ ウィンドウで、次の手順を実行します。

    [Image: Grovo 構成]

    ある。 **[Entity ID](エンティティ ID)** テキストボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    b。 **[シングル サインオン サービス エンドポイント]** テキストボックスに**ログイン URL** の値を貼り付けます。

    c. **[Single sign-on service endpoint binding](シングル サインオン サービス エンドポイント バインディング)** として `urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect` を選択します。

    d. Azure Portal からダウンロードした **Base64 でエンコードされた証明書**をメモ帳で開き、 **[公開キー]** ボックスに貼り付けます。

    え [**次へ**] を選択します。

#### Grovo テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Grovo に作成します。 Grovo では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Grovo にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Grovo] タイルを選択すると、SSO を設定した Grovo に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/gtmhub-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Gtmhub を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/gtmhub-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: Microsoft Entra IDから Gtmhub にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Gtmhub と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID によって、Microsoft Entra プロビジョニング サービスを使用して [Gtmhub](https://www.gtmhub.com/) にユーザーとグループが自動的にプロビジョニングおよびプロビジョニング解除されます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

注

現在、自動ユーザー プロビジョニングが構成されている場合、Microsoft Entraのみ、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループの Gtmhub へのプロビジョニングを自動的に解除し、ユーザーをそれぞれのチームにマップします。 しかし、2021 年に Gtmhub で SSO が有効になると、ユーザーは SSO を使用してログインしたときに自動的にプロビジョニングされ、それぞれのチームに割り当てられます。

### サポートされている機能

- アクセスが不要になった場合は、Gtmhub でユーザーを削除します。
- Microsoft Entra IDと Gtmhub の間でユーザー属性の同期を維持します。
- ユーザーをそれぞれのチームに自動的にマップして配置する。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [1 つの Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Enterprise Gtmhub アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとGtmhubの間でデータを対応付けることを決定します。

### 手順 2: Microsoft Entra IDを使用したチーム マッピングとユーザーのプロビジョニング解除をサポートするように Gtmhub を構成する

プロビジョニング アプリケーションを Gtmhub アカウントに接続するには、SCIM トークンを発行し、テナント URL をコンパイルする必要があります。

#### 新しい SCIM トークンを発行するには:

1. **Gtmhub アカウント**にサインインします。 **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) &gt; [Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) &gt; [API Tokens](API トークン)** に移動します。

    [Image: API トークンのタブ]
2. [ **問題トークン** ] を選択し、[ **SCIM**] を選択します。 トークンの名前を入力し、[ **API トークンの生成** ] ボタンを選択します。

    [Image: トークンの生成タブ]
3. トークンが生成されたら、Microsoft Entra プロビジョニング アプリケーションでコピーして使用できます。

    [Image: トークンのコピー]

#### テナントの URL を作成するには:

1. テナントの URL は次の形式にする必要があります。

    `https://app.gtmhub.com/api/v1/scim/azure/{account_id}`
2. Gtmhub アカウントが米国のデータ センターにある場合は、URL にデータ センターを追加する必要もあります。

    `https://app.us.gtmhub.com/api/v1/scim/azure/{account_id}`
3. アカウント ID を取得するには、**[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** に移動し、**[API Tokens](API トークン)** タブを選択して、アカウント ID をコピーします。[Image: Account ID]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから gtmhub を追加する

Microsoft Entra アプリケーション ギャラリーから gtmhub を追加して、Gtmhub へのプロビジョニングの管理を開始します。 SSO のために Gtmhub を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Gtmhub への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで gtmhub の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Gtmhub]** を選択します。

    [Image: アプリケーションの一覧の Gtmhub のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Gtmhub テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Gtmhub に接続できることを確認します。 接続に失敗した場合は、Gtmhub アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Gtmhub に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Gtmhub のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Gtmhub API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | エクスターナルID | 糸 | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | リファレンス |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/gtnexus-sso-module-tutorial"} -->
## Microsoft Entra ID で GTNexus SSO System for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/gtnexus-sso-module-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と GTNexus SSO System の間にシングル サインオンを構成する方法について説明します。

この記事では、GTNexus SSO System と Microsoft Entra ID を統合する方法について説明します。 GTNexus SSO System と Microsoft Entra ID を統合すると、次の利点が得られます。

- GTNexus SSO System にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで GTNexus SSO System に自動的にサインイン (シングル サインオン) するように設定できます。
- 1 つの場所でアカウントを管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GTNexus SSO System でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- GTNexus SSO System では、 **IDP** Initiated SSO がサポートされます

### ギャラリーからの GTNexus SSO System の追加

Microsoft Entra ID への GTNexus SSO System の統合を構成するには、管理対象の SaaS アプリの一覧にギャラリーから GTNexus SSO System を追加する必要があります。

**ギャラリーから GTNexus SSO System を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;、**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **GTNexus SSO System**」と入力し、結果パネルで **GTNexus SSO System** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: GTNexus SSO System の結果一覧]

### Microsoft Entra シングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、GTNexus SSO System で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと GTNexus SSO System 内の関連ユーザー間にリンク関係が確立されている必要があります。

GTNexus SSO System で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **GTNexus SSO System シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **GTNexus SSO System のテストユーザーを作成する** - GTNexus SSO System で Britta Simon に対応するユーザーを設定し、Microsoft Entra におけるユーザー表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

GTNexus SSO System で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**GTNexus SSO System** アプリケーション統合ページを参照し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: [メタデータ ファイルのアップロード] アクションが選択されている [基本的な S A M L 構成] ページを示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: [フォルダー] ロゴと [アップロード] ボタンが選択されている [ファイルの選択] フィールドを示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、 **識別子** と **応答 URL** の値が GTNexus SSO System セクションのテキスト ボックスに自動的に入力されます。

    [Image: 画像]

    注

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### GTNexus SSO System のシングル サインオンの構成

**GTNexus SSO System** 側でシングル サインオンを構成するには、**フェデレーション メタデータ XML** を [GTNexus SSO System サポート チーム](mailto:support@gtnexus.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Microsoft Entra テスト ユーザーを作成する

このセクションの目的は、Britta Simon というテスト ユーザーを作成することです。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [ **新しいユーザー**&gt;**新しいユーザー**の作成] を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. [ **ユーザー プリンシパル名** ] フィールドに、 username@companydomain.extensionを入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. [ **パスワードの表示** ] チェック ボックスをオンにし、[ **パスワード** ] ボックスに表示される値を書き留めます。
    4. [ **確認と作成**] を選択します。
5. **作成** を選択します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、Britta Simon に GTNexus SSO System へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**GTNexus SSO System** に移動します。

    [Image: エンタープライズアプリケーション ブレード]
3. アプリケーションの一覧で **GTNexus SSO System** を選択します。

    [Image: アプリケーションの一覧の GTNexus SSO System のリンク]
4. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
5. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。

    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

#### GTNexus SSO System のテスト ユーザーの作成

このセクションでは、GTNexus SSO System で Britta Simon というユーザーを作成します。 [GTNexus SSO System サポート チーム](mailto:support@gtnexus.com)と協力して、GTNexus SSO System プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra シングル サインオン構成をテストします。

アクセス パネルで [GTNexus SSO System] タイルを選択すると、SSO を設定した GTNexus SSO System に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/guardium-data-protection-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Guardium Data Protection を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/guardium-data-protection-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Guardium Data Protection の間でシングル サインオンを構成する方法について説明します。

この記事では、Guardium Data Protection と Microsoft Entra ID を統合する方法について説明します。 Guardium Data Protection を Microsoft Entra ID と統合すると、次のことが可能になります。

- Guardium Data Protection にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Guardium Data Protection に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Guardium Data Protection のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Guardium Data Protection では、**SP** および **IDP** によって開始される SSO がサポートされます。

### ギャラリーから Guardium Data Protection を追加する

Microsoft Entra ID への Guardium Data Protection の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Guardium Data Protection を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Guardium Data Protection**」と入力します。
4. 結果パネルから [**Guardium Data Protection**] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Guardium Data Protection 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Guardium Data Protection に対する Microsoft Entra SSO を構成およびテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Guardium Data Protection の関連ユーザーとの間にリンク関係を確立する必要があります。

Guardium Data Protection に対して Microsoft Entra SSO を構成およびテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Guardium Data Protection SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Guardium Data Protection のテスト ユーザーの作成** - Guardium Data Protection で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Guardium Data Protection**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<hostname>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<hostname>:8443/saml/sso`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<hostname>:8443`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Guardium Data Protection サポート チーム](mailto:NA@ibm.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Guardium Data Protection アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: Guardium Data Protection アプリケーション画像を示すスクリーンショット。]
8. その他に、Guardium Data Protection アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | jobtitle | ユーザー.職名 |
9. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Guardium Data Protection のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に Guardium Data Protection へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Guardium Data Protection]** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Guardium Data Protection SSO を構成する

**Guardium Data Protection** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Guardium Data Protection サポート チーム](mailto:NA@ibm.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Guardium Data Protection テスト ユーザーを作成する

このセクションでは、Guardium Data Protection で Britta Simon というユーザーを作成します。 [Guardium Data Protection サポート チーム](mailto:NA@ibm.com)と連携し、Guardium Data Protection プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Guardium Data Protection のサインオン URL にリダイレクトされます。
- Guardium Data Protection のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Guardium Data Protection に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Guardium Data Protection] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Guardium Data Protection に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/gulfhr-sso-tutorial"} -->
## Microsoft Entra ID でガルフHRのシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/gulfhr-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-04
- Summary: Microsoft Entra ID と gulfHR SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、gulfHR SSO と Microsoft Entra ID を統合する方法について説明します。 gulfHR SSO と Microsoft Entra ID を統合すると、次のことができます。

- gulfHR SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って gulfHR SSO に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な gulfHR SSO のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- gulfHR SSO では、**IDP**が開始するSSOのみがサポートされます。

### ギャラリーから gulfHR SSO を追加する

gulfHR SSO の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に gulfHR SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「gulfHR SSO**」と入力します。
4. 結果パネルから **gulfHR SSO を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### gulfHR 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、gulfHR SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと gulfHR の関連ユーザーとの間にリンク関係を確立する必要があります。

gulfHR SSO で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **gulfHR SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **gulfHR SSO テスト ユーザーの作成 - gulfHR SSO** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**gulfHR SSO**&gt;**シングルサインオンに移動します**。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<GULFHR_CUSTOMER_SPECIFIC_DOMAIN>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<GULFHR_CUSTOMER_SPECIFIC_DOMAIN>/Security/Logon2.aspx`

    注意

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [gulfHR SSO クライアント サポート チーム](mailto:helpdesk@gulfhr.ae) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書を編集するスクリーンショット。]
7. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする様子を示すスクリーンショット。]
8. [ **GulfHR SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーを作成する

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

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に gulfHR SSO へのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**gulfHR SSO** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### gulfHR SSO を構成する

**gulfHR SSO** 側でシングル サインオンを構成するには、**Thumbprint Value** と Microsoft Entra 管理センターからコピーした適切な URL を [gulfHR SSO サポート チーム](mailto:helpdesk@gulfhr.ae)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### gulfHR SSO テスト ユーザーを作成する

このセクションでは、gulfHR SSO で B.Simon というユーザーを作成します。 [gulfHR SSO サポート チーム](mailto:helpdesk@gulfhr.ae)と協力して、gulfHR SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した gulfHR SSO に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで gulfHR SSO タイルを選択すると、SSO を設定した gulfHR SSO に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/guru-tutorial"} -->
## Microsoft Entra ID で Guru for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/guru-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Guru 間のシングル サインオンを構成する方法について説明します。

この記事では、 [Guru](https://www.getguru.com/) と Microsoft Entra ID を統合する方法について説明します。 Guru を Microsoft Entra ID と統合すると、次のことが可能になります。

- Guru にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Guru に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Guru でのシングル サインオン (SSO) が有効なサブスクリプション - [ここでアカウントを作成](https://app.getguru.com/signin/new-user)します。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Guru では、 **IDP** Initiated SSO がサポートされます。
- Guru では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Guru の追加

Microsoft Entra ID への Guru の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Guru を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Guru**」と入力します。
4. 結果パネルから **[Guru** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Guru 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Guru に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Guru の関連ユーザーとの間にリンク関係を確立する必要があります。

Guru に対して Microsoft Entra SSO を構成してテストするには、以下の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Guru SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Guru テスト ユーザーの作成** - Guru で B.Simon に対応するユーザーを作成し、Microsoft Entra ID でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Guru]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `getguru.com/<TeamID>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://api.getguru.com/samlsso/<TeamID>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 `TeamID`は、**Configure Guru SSO** セクションから取得できます。 ご質問がある場合は、 [Guru サポート チーム](mailto:support@getguru.com)にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Guru アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Guru アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **Guru のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra ID テスト ユーザーの作成

このセクションでは、Microsoft Entra 管理センターで B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [ **新しいユーザー**&gt;**新しいユーザー**の作成] を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. [ **ユーザー プリンシパル名** ] フィールドに、 username@companydomain.extensionを入力します。 たとえば、`B.Simon@contoso.com` のようにします。
    3. [ **パスワードの表示** ] チェック ボックスをオンにし、[ **パスワード** ] ボックスに表示される値を書き留めます。
    4. [ **確認と作成**] を選択します。
5. **作成**を選択します。

#### Microsoft Entra ID テスト ユーザーを割り当てる

このセクションでは、B.Simon に Guru へのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Guru]** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Guru SSO の構成

1. Guru 企業サイトに管理者としてログインします。
2. **[設定]**&gt;**[アプリケーションと統合**]に移動し、**SSO/SCIM** を選択します。
3. **SSO/SCIM** セクションで、次の手順を実行します。

    [Image: 管理ポータルを示すスクリーンショット。]

    1. **チーム ID を**コピーし、コンピューターに保存します。
    2. **シングル サインオン URL を**コピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。
    3. **対象ユーザー URI を**コピーし、この値を Microsoft Entra 管理センターの **[基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスに貼り付けます。
    4. **[Identity Provider Single Sign-On Url**] ボックスに、Microsoft Entra 管理センターからコピーした**ログイン URL** の値を貼り付けます。
    5. [ **ID プロバイダー発行者** ] ボックスに、Microsoft Entra 管理センターからコピーした **Microsoft Entra ID 識別子** の値を貼り付けます。
    6. Microsoft Entra 管理センターからダウンロードした **証明書 (Base64)** をメモ帳に開き、内容を **[X.509 証明書** ] ボックスに貼り付けます。
    7. [ **SSO を有効にする] を選択します**。

#### Guru テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Guru に作成します。 Guru では、ジャストインタイム プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Guru に存在しない場合は、Guru にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した Guru に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Guru] タイルを選択すると、SSO を設定した Guru に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/h5mag-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に H5mag を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/h5mag-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: Microsoft Entra IDから H5mag にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために H5mag と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成された Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを[H5mag](https://www.h5mag.com) に自動でプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- H5mag でユーザーを作成する
- アクセスが不要になったときに H5mag のユーザーを削除する
- Microsoft Entra IDと H5mag の間でユーザー属性の同期を維持する
- H5mag へのシングル サインオン (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- エンタープライズ ライセンスを持つ、[H5mag](https://account.h5mag.com)のユーザー アカウント。 アカウントでエンタープライズ ライセンスへのアップグレードが必要な場合は、`support@h5mag.com` までお問い合わせください。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとH5magの間でどのデータを[マップするか決定](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように H5mag を構成する

1. [H5mag 環境](https://account.h5mag.com/login)にログインし、**[Account](https://account.h5mag.com/account)**&gt;**[Provisioning & SSO](https://account.h5mag.com/account/provisioning)** に移動します。
2. [ **トークンの生成** ] ボタンを選択します。 プロビジョニング URL と API トークンが表示されます。 これらの値は、H5mag アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。
3. [ **保存** ] ボタンを選択して、生成されたトークンを格納します。
4. ユーザーが H5mag 独自のシステムを使用してログインしようとしたときにMicrosoftログイン ページを使用するようにユーザーをリダイレクトする場合は、SSO プロバイダー オプションで **Microsoft 365/ Microsoft Entra ID** を選択して、このページで SSO リダイレクトを設定することもできます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから H5mag を追加する

Microsoft Entra アプリケーション ギャラリーから H5mag を追加して、H5mag へのプロビジョニングの管理を開始します。 SSO のために以前 H5mag を設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: H5mag への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて H5mag のユーザーやグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで H5mag の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[H5mag]** を選択します。

    [Image: アプリケーションの一覧の H5mag のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、H5mag テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが H5mag に接続できることを確認します。 接続に失敗した場合は、H5mag アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから H5mag に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で H5mag のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、H5mag API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | エクスターナルID | 糸 |  |
    | 活動中 | ブール値 |  |
    | ディスプレイ名 | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 優先言語 | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | 名前.整形済み | 糸 |  |
    | ロケール | 糸 |  |
    | タイムゾーン | 糸 |  |
    | ユーザータイプ | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hackerone-tutorial"} -->
## Microsoft Entra ID で HackerOne for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hackerone-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HackerOne との間でシングル サインオンを構成する方法について説明します。

この記事では、HackerOne と Microsoft Entra ID を統合する方法について説明します。 HackerOne を Microsoft Entra ID と統合すると、次のことができます。

- HackerOne にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで HackerOne に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な HackerOne サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- HackerOne では、 **SP** によって開始される SSO がサポートされます。
- HackerOne では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの HackerOne の追加

Microsoft Entra ID への HackerOne の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に HackerOne を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**HackerOne**」と入力します。
4. 結果パネルから **HackerOne** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HackerOne に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、HackerOne に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと HackerOne の関連ユーザーとの間にリンク関係を確立する必要があります。

HackerOne に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成** する - ユーザーがこの機能を使用できるようにします。
2. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
3. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
4. **HackerOne SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[HackerOne]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `hackerone.com`

    b。 **[応答 URL (Assertion Consumption Service URL)]** テキスト ボックスに、値 `https://hackerone.com/users/saml/auth` を入力します。

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://hackerone.com/users/saml/sign_in?email=<CONFIGURED_DOMAIN>`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL でこの値を更新します。「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **HackerOne のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

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

このセクションでは、B.Simon に HackerOne へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**HackerOne** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### HackerOne の SSO の構成

[HackerOne のドキュメント](https://docs.hackerone.com/en/articles/8487039-single-sign-on-sso-via-saml)で説明されている手順に従います
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/hacknotice-tutorial"} -->
## Microsoft Entra ID で HackNotice for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/hacknotice-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HackNotice の間でシングル サインオンを構成する方法について説明します。

この記事では、HackNotice と Microsoft Entra ID を統合する方法について説明します。 HackNotice と Microsoft Entra ID を統合すると、次のことができます。

- HackNotice にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して HackNotice に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- HackNotice でのシングル サインオン (SSO) が有効なサブスクリプション。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra ID でアプリケーションを追加または管理することもできます。 詳細については、Azure 組み込みロール に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- HackNotice では、 **IDP** によって開始される SSO がサポートされます。

### ギャラリーから HackNotice を追加する

Microsoft Entra ID への HackNotice の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に HackNotice を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「HackNotice**」と入力します。
4. 結果のパネルから HackNotice  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HackNotice の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、HackNotice に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと HackNotice の関連ユーザーとの間にリンク関係を確立する必要があります。

HackNotice で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HackNotice SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **HackNotice テストユーザーを作成** - B.Simon に対応するユーザーを HackNotice で作成し、それを Microsoft Entra 上の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[HackNotice]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **HackNotice のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [ **新しいユーザー**&gt;**新しいユーザー**の作成] を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. [ **ユーザー プリンシパル名** ] フィールドに、 username@companydomain.extensionを入力します。 たとえば、`B.Simon@contoso.com`します。
    3. [ **パスワードの表示** ] チェック ボックスをオンにし、[ **パスワード** ] ボックスに表示される値を書き留めます。
    4. [ **確認と作成**] を選択します。
5. **[作成]**を選択します。

#### Microsoft Entra テスト ユーザーの割り当て

このセクションでは、B.Simon に HackNotice へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**HackNotice** に移動します。
3. アプリの概要ページで、[ユーザーとグループ] 選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### HackNotice SSO の構成

**HackNotice** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [HackNotice サポート チーム](mailto:support@hacknotice.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### HackNotice テスト ユーザーの作成

このセクションでは、HackNotice で Britta Simon というユーザーを作成します。 HackNotice サポート チーム  と連携して、HackNotice プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した HackNotice に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [HackNotice] タイルを選択すると、SSO を設定した HackNotice に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/halogen-software-tutorial"} -->
## Microsoft Entra ID で Saba TalentSpace for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/halogen-software-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Saba TalentSpace の間のシングル サインオンを構成する方法について説明します。

この記事では、Saba TalentSpace と Microsoft Entra ID を統合する方法について説明します。 Saba TalentSpace を Microsoft Entra ID と統合すると、次のことが可能になります。

- Saba TalentSpace にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使って Saba TalentSpace に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Saba TalentSpace でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Saba TalentSpace では、**SP** Initiated SSO がサポートされます

### ギャラリーからの Saba TalentSpace の追加

Microsoft Entra ID への Saba TalentSpace の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Saba TalentSpace を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Saba TalentSpace**」と入力します。
4. 結果パネルで **[Saba TalentSpace]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Saba TalentSpace 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Saba TalentSpace に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Saba TalentSpace の関連ユーザーとの間にリンク関係を確立する必要があります。

Saba TalentSpace に対して Microsoft Entra SSO を構成およびテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Saba TalentSpace SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Saba TalentSpace のテストユーザーを作成し、B.Simon に対応するユーザーを Microsoft Entra にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Saba TalentSpace]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://global.hgncloud.com/<COMPANY_NAME>/saml/login`

    b。 **[識別子 (エンティティ ID)]** ボックスに、`https://global.hgncloud.com/<COMPANY_NAME>/saml/metadata` というパターンで URL を入力します。

    c. **[応答 URL (Assertion Consumer Service URL)]** ボックスに、`https://global.hgncloud.com/<COMPANY_NAME>/saml/SSO` というパターンで URL を入力します。

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Saba TalentSpace クライアント サポート チーム](https://support.saba.com/)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Saba TalentSpace のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に Saba TalentSpace へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Saba TalentSpace** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Saba TalentSpace の SSO の構成

1. 別のブラウザー ウィンドウで、管理者として **Saba TalentSpace** アプリケーションにサインオンします。
2. **[Options](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/オプション)** タブをクリックします。

    [Image: [Options](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/オプション) タブが選択された Saba TalentSpace ホーム ページを示すスクリーンショット。]
3. 左側のナビゲーション ウィンドウで、[ **SAML 構成]** を選択します。

    [Image: 左側の [User Interface](ユーザー インターフェイス) ナビゲーション ウィンドウで [SAML Configuration](SAML 構成) が選択されているスクリーンショット。]
4. **[SAML 構成]** ページで、次の手順を実行します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) オプションが強調表示されている [SAML Configuration](SAML 構成) ページを示すスクリーンショット。]

    a. **[Unique Identifier]** で **[NameID]** を選択します。

    b。 **[Unique Identifier Maps To]** で **[Username]**、**[Email Address]**、または **[Employee ID]** を選択します。 これは、Azure プライマリ属性と一致する必要があるフィールドです。

    c. ダウンロードしたメタデータ ファイルをアップロードするには、[ **参照** ] を選択してファイルを選択し、[ **ファイルのアップロード**] を選択します。

    d. 構成をテストするには、[ **テストの実行**] を選択します。

    注

    "*The SAML test is complete.Please close this window*" というメッセージが表示されるまで待機する必要があります。 次に、開いているブラウザー ウィンドウを閉じます。 **[Enable SAML]** チェック ボックスは、テストが完了した場合にのみ有効にします。

    e. [ **SAML を有効にする] を選択します**。

    f. [ **変更の保存] を選択します**。

#### Saba TalentSpace のテスト ユーザーの作成

このセクションの目的は、Saba TalentSpace で Britta Simon というユーザーを作成することです。

**Saba TalentSpace で Britta Simon というユーザーを作成するには、次の手順に従います。**

1. **Saba TalentSpace** アプリケーションに管理者としてサインオンします。
2. [ **ユーザー センター** ] タブを選択し、[ **ユーザーの作成**] を選択します。

    [Image: [User Center](ユーザー センター) タブと [Create User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成) が選択されているスクリーンショット。]
3. **[New User]** ダイアログ ページで、次の手順に従います。

    [Image: Microsoft Entra Connect とは]

    a. **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名を入力します (この例では **B**)。

    b。 [ **姓]** ボックスに、ユーザーの姓 ( **Simon** など) を入力します。

    c. **[User Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー名)** テキストボックスに、ユーザー名として「**B.Simon**」と入力します。

    d. **[Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード)** ボックスに、B.Simon のパスワードを入力します。

    e. **保存** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Saba TalentSpace のサインオン URL にリダイレクトされます。
- Saba TalentSpace のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Saba TalentSpace タイルを選択すると、SSO を設定した Saba TalentSpace に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/halosys-tutorial"} -->
## Microsoft Entra ID で Halosys for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/halosys-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Halosys の間のシングル サインオンを構成する方法について説明します。

この記事では、Halosys と Microsoft Entra ID を統合する方法について説明します。 Halosys を Microsoft Entra ID と統合すると、次のことが可能になります。

- Halosys にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Halosys に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Halosys のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Halosys では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからの Halosys の追加

Microsoft Entra ID への Halosys の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Halosys を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Halosys**」と入力します。
4. 結果パネルから **Halosys** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Halosys 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Halosys に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Halosys の関連ユーザー間にリンク関係を確立する必要があります。

Halosys に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Halosys SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Halosys テスト ユーザーの作成** - Halosys で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Halosys**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-name>.halosys.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-name>.halosys.com/<instance name>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Halosys クライアント サポート チーム](https://www.sonata-software.com/form/contact) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Halosys のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: スクリーンショットには、構成を適切な U R L にコピーする手順が表示されています。]

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

このセクションでは、B.Simon に Halosys へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Halosys** を参照してください。
3. アプリの概要ページで、[ **管理** ] セクションを見つけて、[ **ユーザーとグループ**] を選択します。
4. [**ユーザーの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Halosys SSO の構成

**Halosys** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Halosys サポート チーム](https://www.sonata-software.com/form/contact)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Halosys テスト ユーザーの作成

このセクションでは、Halosys で Britta Simon というユーザーを作成します。 [Halosys サポート チーム](https://www.sonata-software.com/form/contact)と協力して、Halosys プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Halosys に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで Halosys タイルを選択すると、SSO を設定した Halosys に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/happyfox-tutorial"} -->
## Microsoft Entra ID で HappyFox for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/happyfox-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と HappyFox 間にシングル サインオンを構成する方法について学習します。

この記事では、HappyFox と Microsoft Entra ID を統合する方法について説明します。 HappyFox と Microsoft Entra ID を統合すると、次のことができます。

- HappyFox にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って HappyFox に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- HappyFox でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- HappyFox では、 **SP** によって開始される SSO がサポートされます。
- HappyFox では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから HappyFox を追加する

Microsoft Entra ID への HappyFox の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に HappyFox を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「HappyFox**」と入力します。
4. 結果パネルから **HappyFox** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### HappyFox 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、HappyFox に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと HappyFox の関連ユーザーとの間にリンク関係を確立する必要があります。

HappyFox に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **HappyFox SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **HappyFox のテスト ユーザーの作成 - HappyFox** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**HappyFox**&gt;**シングルサインオンに移動します**。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.happyfox.com/`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.happyfox.com/saml/metadata/`

    注意

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、HappyFox クライアント サポート チーム](https://support.happyfox.com/home) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **HappyFox のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

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

このセクションでは、B.Simon に HappyFox へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**HappyFox に**移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### HappyFox SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として HappyFox テナントにサインオンします。
2. [ **管理**] に移動し、[統合] タブ **を** 選択します。

    [Image: [統合] タブが選択されている [管理] ページを示すスクリーンショット。]
3. [統合] タブで、[**SAML 統合**] で **[構成**] を選択して、シングル サインオン設定を開きます。

    [Image: [構成] アクションが選択された [S A M L 統合] 設定を示すスクリーンショット。]
4. **[SAML 構成]** セクションの **[SSO ターゲット URL**] ボックスに、[**HappyFox のセットアップ**] セクションの**ログイン URL** の値を貼り付けます。
5. Azure portal からダウンロードした証明書をメモ帳で開き、その内容を **[IdP Signature** ] セクションに貼り付けます。

    [Image: [I d P Signature] セクションが強調表示されているスクリーンショット。]
6. [ **設定の保存]** ボタンを選択します。

    [Image: シングル サインオンの構成]

#### HappyFox のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを HappyFox に作成します。 HappyFox では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 HappyFox にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、マイ アプリを使って Microsoft Entra のシングル サインオン構成をテストします。

1. マイ アプリで [HappyFox] タイルを選択すると、HappyFox アプリケーションのログイン ページが表示されます。 サインイン ページに **[SAML]** ボタンが表示されます。

    [Image: プラグイン]
2. **SAML** ボタンを選択して、Microsoft Entra アカウントを使用して HappyFox にログインします。

マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/harmony-tutorial"} -->
## Microsoft Entra ID で Harmony for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/harmony-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Harmony との間でシングル サインオンを構成する方法について説明します。

この記事では、Harmony と Microsoft Entra ID を統合する方法について説明します。 Harmony を Microsoft Entra ID と統合すると、次のことが可能になります。

- Harmony にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Harmony に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Harmony でのシングル サインオン (SSO) が有効なサブスクリプション。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Harmony では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Harmony の追加

Microsoft Entra ID への Harmony の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Harmony を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Harmony**」と入力します。
4. 結果のパネルから **[Harmony]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Harmony 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Harmony に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するには、Microsoft Entra ユーザーと Harmony の関連ユーザー間にリンク関係を確立する必要があります。

Harmony に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Harmony の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Harmony のテスト ユーザーの作成** - Harmony で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Harmony**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Harmony アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Harmony アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | user.userprincipalname |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Harmony のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

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

このセクションでは、B.Simon に Harmony へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Harmony** を参照してください。
3. アプリの概要ページで、 **[管理]** セクションを見つけて、 **[ユーザーとグループ]** を選択します。
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Harmony の SSO の構成

**Harmony** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Harmony サポート チーム](https://us.moodmedia.com/contact-us/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Harmony のテスト ユーザーの作成

このセクションでは、Harmony で Britta Simon というユーザーを作成します。 [Harmony サポート チーム](https://us.moodmedia.com/contact-us/)と連携して、Harmony プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Harmony に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Harmony] タイルを選択すると、SSO を設定した Harmony に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->
