# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 14)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 75

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/korn-ferry-alp-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Korn Ferry ALP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/korn-ferry-alp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Korn Ferry ALP 間にシングル サインオンを構成する方法について説明します。

この記事では、Korn Ferry ALP と Microsoft Entra ID を統合する方法について説明します。 Korn Ferry ALP を Microsoft Entra ID と統合すると、次のことが可能になります。

- Korn Ferry ALP にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Korn Ferry ALP に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Korn Ferry ALP でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Korn Ferry ALP では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの Korn Ferry ALP の追加

Microsoft Entra ID への Korn Ferry ALP の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Korn Ferry ALP を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Korn Ferry ALP**」と入力します。
4. 結果パネルから **Korn Ferry ALP** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Korn Ferry ALP 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Korn Ferry ALP に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Korn Ferry ALP の関連ユーザーとの間にリンク関係を確立する必要があります。

Korn Ferry ALP に対する Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Korn Ferry ALP の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Korn Ferry ALP テスト ユーザーの作成** - Korn Ferry ALP で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID] **&gt;** [エンタープライズ アプリ] **&gt;** [Korn Ferry ALP] **&gt;** [シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://intappextin01/portalweb/sso/client/audience?guid=<customerguid>` |
    | `https://qaassessment.kfnaqa.com/portalweb/sso/client/audience?guid=<customerguid>` |
    | `https://assessments.kornferry.com/portalweb/sso/client/audience?guid=<customerguid>` |

    b。 [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://intappextin01/portalweb/sso/client/audience?guid=<customerguid>` |
    | `https://qaassessment.kfnaqa.com/portalweb/sso/client/audience?guid=<customerguid>` |
    | `https://assessments.kornferry.com/portalweb/sso/client/audience?guid=<customerguid>` |

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、Korn Ferry ALP クライアント サポート チーム](mailto:noreply@kornferry.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Korn Ferry ALP の SSO の構成

**Korn Ferry ALP** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Korn Ferry ALP サポート チーム](mailto:noreply@kornferry.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Korn Ferry ALP のテスト ユーザーの作成

このセクションでは、Korn Ferry ALP で Britta Simon というユーザーを作成します。 [Korn Ferry ALP サポート チーム](mailto:noreply@kornferry.com)と協力して、Korn Ferry ALP プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Korn Ferry ALP のサインオン URL にリダイレクトされます。
- Korn Ferry ALP のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Korn Ferry ALP] タイルを選択すると、このオプションは Korn Ferry ALP のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kpifire-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に kpifire を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kpifire-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: ユーザー アカウントを kpifire に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために kpifire と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [kpifire](https://www.kpifire.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- kpifire でユーザーを作成する
- アクセスが不要になった場合に kpifire のユーザーを削除する
- Microsoft Entra ID と kpifire の間でユーザー属性の同期を維持する。
- kpifire でグループとグループ メンバーシップをプロビジョニングする
- kpifire への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kpifire-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [kpifire テナント](https://www.kpifire.com/)。
- 管理者アクセス許可がある kpifire のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と kpifire の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように kpifire を構成する

1. https://app.kpifire.com に管理者権限でサインインします。
2. **Settings-&gt;API Settings-&gt;Add New Token** に移動して SCIM トークンを生成します。

    [Image: kpifire トークン生成ページのスクリーンショット。]
3. SCIM トークンをコピーし、保存します。 この値は、kpifire アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから kpifire を追加する

Microsoft Entra アプリケーション ギャラリーから kpifire を追加して、kpifire へのプロビジョニングの管理を開始します。 以前に、SSO 用に kpifire を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: kpifire への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて kpifire アプリ内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で kpifire に対する自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**にアクセスする

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **kpifire** を選択します。

    [Image: アプリケーションの一覧の kpifire リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: アプリケーション設定の [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、kpifire テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが kpifire に接続できることを確認します。 接続に失敗した場合は、kpifire アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から kpifire に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で kpifire のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、kpifire API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存] を** 選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
13. 左側のパネルで **[属性マッピング** ] を選択し、[グループ] を選択 **します**。
14. [属性マッピング] セクションで、Microsoft Entra ID から kpifire に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で kpifire 内のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | members | リファレンス |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kpifire-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に kpifire を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kpifire-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と kpifire の間のシングル サインオンを構成する方法について説明します。

この記事では、kpifire と Microsoft Entra ID を統合する方法について説明します。 kpifire を Microsoft Entra ID と統合すると、次のことが可能になります。

- kpifire へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで kpifire に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な kpifire サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- kpifire では、**IDP** によって開始される SSO がサポートされます。
- kpifire では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kpifire-provisioning-tutorial)がサポートされます。

### ギャラリーからの kpifire の追加

Microsoft Entra ID への kpifire の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に kpifire を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**kpifire**」と入力します。
4. 結果のパネルから **[kpifire]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### kpifire 用に Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、kpifire に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと kpifire の関連ユーザー間にリンク関係を確立する必要があります。

kpifire に対して Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **kpifire の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **kpifire のテスト ユーザーの作成** - kpifire で B.Simon に対応するユーザーを作成し、Microsoft Entra 内の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**kpifire**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.kpifire.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.kpifire.com/api/auth/saml/<UNIQUE_IDENTIFIER>/login`

    c. [ **追加の URL の設定] を選択します**。

    d. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.kpifire.com/#/metrics`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、およびリレー状態でこれらの値を更新します。 この値を取得するには、[kpifire クライアント サポート チーム](mailto:support@kpifire.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[kpifire のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### kpifire の SSO の構成

**kpifire** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [kpifire サポート チーム](mailto:support@kpifire.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### kpifire のテスト ユーザーの作成

このセクションでは、kpifire で B.Simon というユーザーを作成します。 [kpifire サポート チーム](mailto:support@kpifire.com)と連携して、kpifire プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

kpifire では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kpifire-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した kpifire に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで kpifire タイルを選択すると、SSO を設定した kpifire に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kpmg-tool-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に KPMG リース ツールを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kpmg-tool-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と KPMG Leasing Tool の間のシングル サインオンを構成する方法について説明します。

この記事では、KPMG リース ツールと Microsoft Entra ID を統合する方法について説明します。 KPMG Leasing Tool と Microsoft Entra ID を統合すると、次のことができます。

- KPMG Leasing Tool にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って KPMG Leasing Tool に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- KPMG リース ツールでのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- KPMG リース ツールでは、**IDP** によって開始される SSO がサポートされます。

### ギャラリーからの KPMG リース ツールの追加

Microsoft Entra ID への KPMG Leasing Tool の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に KPMG Leasing Tool を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**KPMG リース ツール**」と入力します。
4. 結果のパネルから **[KPMG リース ツール]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### KPMG Leasing Tool 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、KPMG Leasing Tool に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと KPMG Leasing Tool の関連ユーザーとの間にリンク関係を確立する必要があります。

KPMG Leasing Tool に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **KPMG リース ツール SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **KPMG リース ツールのテスト ユーザーの作成** - KPMG リース ツールで B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[KPMG リースツール]**&gt;**[シングル サインオン]**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML  検索し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[KPMG リース ツールのセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### KPMG リース ツール SSO の構成

**KPMG リース ツール**側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [KPMG リース ツール サポート チーム](mailto:wsnyder@KPMG.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### KPMG リース ツールのテスト ユーザーの作成

このセクションでは、KPMG リース ツールで Britta Simon というユーザーを作成します。 [KPMG リース ツール サポート チーム](mailto:wsnyder@KPMG.com)と協力して、KPMG リース ツール プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した KPMG リース ツールに自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [KPMG リース ツール] タイルを選択すると、SSO を設定した KPMG リース ツールに自動的にサインインします。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kpn-grip-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に KPN Grip を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kpn-grip-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra ID から KPN Grip に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために KPN Grip と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[KPN Grip](https://grip.kpn.com) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- KPN Grip でユーザーを作成する。
- アクセスが不要になった場合は、KPN グリップのユーザーを削除します。
- Microsoft Entra ID と KPN Grip の間でユーザー属性の同期を維持する。
- KPN Grip への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロール、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)ロール、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)ロールのいずれか。
- 管理者のアクセス許可がある KPN Grip のユーザー アカウント。

### 手順1: プロビジョニング デプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの対象](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を決めます。
3. [Microsoft Entra ID と KPN Grip の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように KPN Grip を構成する

Microsoft Entra ID を使用したプロビジョニングをサポートするように KPN Grip を構成するには、`https://grip.kpn.com/en/documentation/article/connectentraid#` の Microsoft Entra 設定を参照してください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから KPN Grip を追加する

KPN Grip へのプロビジョニングの管理を開始するために、Microsoft Entra アプリケーション ギャラリーから KPN Grip を追加します。 以前に KPN Grip を SSO 用に設定している場合は、同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニング対象のユーザーのスコープを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: KPN Grip への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、KPN Grip でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で KPN Grip 用に自動ユーザー プロビジョニングを構成するには、次のようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で、**[KPN Grip]** を選択します。

    [Image: アプリケーションの一覧の [KPN グリップ] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: アプリケーション設定の [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、KPN Grip テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が KPN グリップに接続できることを確認します。 接続に失敗した場合は、KPN Grip アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から KPN Grip に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作のために KPN Grip でユーザー アカウントを照合するために使用されます。 [照合するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更することを選択する場合は、その属性に基づいたユーザーのフィルター処理が KPN Grip API で確実にサポートされるようにする必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | KPN Grip が要求する |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | displayName | 糸 |  | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | emails[type eq "alternate"].value | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | externalId | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)を使用して、正常にプロビジョニングされたユーザーと失敗したユーザーを特定します
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/krisp-technologies-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Krisp Technologies を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/krisp-technologies-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Krisp Technologies の間でシングル サインオンを構成する方法について説明します。

この記事では、Krisp Technologies と Microsoft Entra ID を統合する方法について説明します。 Krisp の音声生成 AI では、バックグラウンド ノイズの除去、アクセントの明確化、通話のトランスクリプトにより音声通信が向上します。 Krisp Technologies を Microsoft Entra ID を統合すると、次のことができます。

- Krisp Technologies にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Krisp Technologies に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Krisp Technologies 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Krisp Technologies では、**SP** によって開始されるシングル サインオンと **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### [前提条件]

Microsoft Entra ID を Krisp Technologies と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) 対応の Krisp Technologies サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Krisp Technologies アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Krisp Technologies を追加する

Microsoft Entra アプリケーション ギャラリーから Krisp Technologies を追加して、Krisp Technologies でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Krisp Technologies**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`<TEAM_SLUG_ID>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://api.krisp.ai/v2/auth/sso/saml/<ID>`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://account.krisp.ai/sso/<ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Krisp Technologies のサポート チーム](mailto:support@krisp.ai)までお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Krisp Technologies アプリケーションでは特定の形式の SAML アサーションが想定されるため、カスタム属性のマッピングを SAML トークンの属性構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Krisp Technologies アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Krisp Technologies のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Krisp Technologies SSO を構成する

**Krisp Technologies** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** とアプリケーションの構成からコピーした適切な URL を [Krisp Technologies サポート チーム](mailto:support@krisp.ai)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Krisp Technologies のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーが Krisp Technologies で作成されます。 Krisp Technologies では、Just-In-Time ユーザー プロビジョニングがサポートされます。この設定は既定で有効です。 このセクションにはアクション項目はありません。 Krisp Technologies にユーザーがまだ存在していない場合、通常は認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Krisp Technologies のサインオン URL にリダイレクトされます。
- Krisp Technologies のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Krisp Technologies] タイルを選択すると、このオプションは Krisp Technologies のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kronos-tutorial"} -->
## Microsoft Entra ID で Kronos for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kronos-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kronos の間でシングル サインオンを構成する方法について説明します。

この記事では、Kronos と Microsoft Entra ID を統合する方法について説明します。 Kronos と Microsoft Entra ID を統合すると、次のことができます。

- Kronos にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Kronos に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kronos でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Kronos では、IDP **による開始のSSO** がサポートされています。

### ギャラリーからの Kronos の追加

Microsoft Entra ID への Kronos の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Kronos を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリー**から追加**] セクションで、検索ボックス**に「Kronos**」と入力します。
4. 結果のパネルから Kronos  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kronos の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Kronos に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Kronos の関連ユーザーとの間にリンク関係を確立する必要があります。

Kronos に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kronos SSO**の構成 - アプリケーション側で単一 Sign-On 設定を構成します。
    1. **Kronos テストユーザーを作成する - Microsoft Entra のユーザー表現としての B.Simon にリンクされた Kronos 内の B.Simon に対応するユーザーを作成します。**
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Kronos** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方法の選択]** ページで、SAML を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [SAML **を使用して単一 Sign-On を設定する**] ページで、次のフィールドの値を入力します。

    ある。 [**識別子**] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<company name>.kronos.net/`

    b。 [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<company name>.kronos.net/wfc/navigator/logonWithUID`

    手記

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、Kronos クライアント サポート チーム  にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. Kronos アプリケーションは、特定の形式の SAML アサーションを受け入れます。 このアプリケーションに対して次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの **ユーザー属性** セクションから管理できます。 [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** ボタンを選択して [ **ユーザー属性] ダイアログを** 開きます。

    [Image: スクリーンショットには、[編集] アイコンが選択された [ユーザー属性] が表示されます。]
7. [**ユーザー属性の**] ダイアログの [**ユーザー要求**] セクションで、上の図に示すように SAML トークン属性を構成し、次の手順を実行します。

    ある。 [ **編集] アイコン** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: スクリーンショットには、[編集] アイコンが選択された [ユーザー属性] & 要求が表示されます。]

    [Image: スクリーンショットには、[ユーザー要求の管理] ダイアログ ボックスが表示され、ここで説明されている値を入力できます。]

    b。 **変換** の一覧から、ExtractMailPrefix() 選択します。

    c. **パラメーター 1** の一覧で、**user.userprincipalname**を選択します。

    d. **保存** を選択します。
8. [SAML **を使用して単一 Sign-On を設定する**] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML **]** を見つけ、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [**Kronos** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kronos SSO の構成

Kronos **側** シングル サインオンを構成するには、ダウンロードした **フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Kronos サポート チーム](https://www.kronos.in/contact/en-in/form)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Kronos テスト ユーザーの作成

このセクションでは、Kronos で Britta Simon というユーザーを作成します。 Kronos サポート チーム  と連携して、Kronos プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Kronos に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [Kronos] タイルを選択すると、SSO を設定した Kronos に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kronos-workforce-dimensions-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Kronos Workforce Dimensions を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kronos-workforce-dimensions-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-04
- Summary: Microsoft Entra ID と Kronos Workforce Dimensions の間でシングル サインオンを構成する方法について説明します。

この記事では、Kronos Workforce Dimensions と Microsoft Entra ID を統合する方法について説明します。 Kronos Workforce Dimensions と Microsoft Entra ID を統合すると、次のことができます。

- Kronos Workforce Dimensions にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Kronos Workforce Dimensions に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

Kronos Workforce Dimensions は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Kronos Workforce Dimensions でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Kronos Workforce Dimensions では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから Kronos Workforce Dimensions を追加する

Microsoft Entra ID への Kronos Workforce Dimensions の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Kronos Workforce Dimensions を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Kronos Workforce Dimensions**」と入力します。
4. 結果パネルから **Kronos Workforce Dimensions** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kronos Workforce Dimensions への Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Kronos Workforce Dimensions に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Kronos Workforce Dimensions の関連ユーザーとの間にリンク関係を確立する必要があります。

Kronos Workforce Dimensions に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kronos Workforce Dimensions の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Kronos Workforce Dimensions のテスト ユーザーの作成 - Kronos Workforce Dimensions で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。** - Kronos Workforce Dimensions で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Kronos Workforce Dimensions**&gt;**シングル サインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.<ENVIRONMENT>.mykronos.com/authn/<TENANT_ID/hsp/<TENANT_NUMBER>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CUSTOMER>-<ENVIRONMENT>-sso.<ENVIRONMENT>.mykronos.com/` |
    | `https://<CUSTOMER>-sso.<ENVIRONMENT>.mykronos.com/` |

    注記

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、Kronos Workforce Dimensions クライアント サポート チーム](mailto:support@kronos.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kronos Workforce Dimensions の SSO を構成する

**Kronos Workforce Dimensions** 側でシングル サインオンを構成するには、**Kronos Workforce Dimensions サポート チーム**に[アプリフェデレーション メタデータ URL を](mailto:support@kronos.com)送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

### Kronos Workforce Dimensions のテスト ユーザーの作成

このセクションでは、Kronos Workforce Dimensions で Britta Simon というユーザーを作成します。 [Kronos Workforce Dimensions サポート チーム](mailto:support@kronos.com)と協力して、Kronos Workforce Dimensions プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

注記

元の Microsoft ドキュメントでは、Microsoft Entra ユーザーを作成するために、UKG サポートにメールで連絡することをお勧めしています。 このオプションも使用できますが、次のセルフサービス オプションを検討してください。

#### 手動プロセス

WFD 内で Microsoft Entra ユーザーを手動で作成するには、2 つの方法があります。 既存のユーザーを選択し、それらを複製してから、必要なフィールドを更新して、そのユーザーを一意にすることができます。 このプロセスには時間がかかる場合があり、WFD ユーザー インターフェイスに関する知識が必要です。 WFD API を使用してユーザーを作成することもでき、はるかに高速です。 このオプションでは、代わりに API に要求を送信するために、Postman などの API ツールの使用に関する知識が必要です。 次の手順は、事前構築済みの例を Postman API ツールにインポートする場合に役立ちます。

##### セットアップ

1. Postman ツールを開き、次のファイルをインポートします。

    a. ワークフォース ディメンション - User.postman\_collection.json の作成

    b。 Microsoft Entra ID から WFD Env Variables.json への変換
2. 左側のウィンドウで、[ **環境** ] ボタンを選択します。
3. **AAD\_to\_WFD\_Env\_Variables**選択し、WFD インスタンスに関連する UKG サポートによって提供される値を追加します。

    注記

    access\_token と refresh\_token は、アクセス トークンの取得 HTTP 要求の結果として自動的に設定されるため、空にする必要があります。
4. **WFD HTTP 要求で Microsoft Entra ユーザーの作成を**開き、JSON ペイロード内の強調表示されたプロパティを更新します。

    ```
    { 
    
    "personInformation": { 
    
       "accessAssignment": { 
    
          "accessProfileName": "accessProfileName", 
    
          "notificationProfileName": "All" 
    
        }, 
    
        "emailAddresses": [ 
    
          { 
    
            "address": "address” 
    
            "contactTypeName": "Work" 
    
          } 
    
        ], 
    
        "employmentStatusList": [ 
    
          { 
    
            "effectiveDate": "2019-08-15", 
    
            "employmentStatusName": "Active", 
    
            "expirationDate": "3000-01-01" 
    
          } 
    
        ], 
    
        "person": { 
    
          "personNumber": "personNumber", 
    
          "firstName": "firstName", 
    
          "lastName": "lastName", 
    
          "fullName": "fullName", 
    
          "hireDate": "2019-08-15", 
    
          "shortName": "shortName" 
    
        }, 
    
        "personAuthenticationTypes": [ 
    
          { 
    
            "activeFlag": true, 
    
            "authenticationTypeName": "Federated" 
    
          } 
    
        ], 
    
        "personLicenseTypes": [ 
    
          { 
    
            "activeFlag": true, 
    
            "licenseTypeName": "Employee" 
    
          }, 
    
          { 
    
            "activeFlag": true, 
    
            "licenseTypeName": "Absence" 
    
          }, 
    
          { 
    
            "activeFlag": true, 
    
            "licenseTypeName": "Hourly Timekeeping" 
    
          }, 
    
          { 
    
            "activeFlag": true, 
    
            "licenseTypeName": "Scheduling" 
    
          } 
    
        ], 
    
        "userAccountStatusList": [ 
    
          { 
    
            "effectiveDate": "2019-08-15", 
    
            "expirationDate": "3000-01-01", 
    
            "userAccountStatusName": "Active" 
    
          } 
    
        ] 
    
      }, 
    
      "jobAssignment": { 
    
        "baseWageRates": [ 
    
          { 
    
            "effectiveDate": "2019-01-01", 
    
            "expirationDate": "3000-01-01", 
    
            "hourlyRate": 20.15 
    
          } 
    
        ], 
    
        "jobAssignmentDetails": { 
    
          "payRuleName": "payRuleName", 
    
          "timeZoneName": "timeZoneName" 
    
        }, 
    
        "primaryLaborAccounts": [ 
    
          { 
    
            "effectiveDate": "2019-08-15", 
    
            "expirationDate": "3000-01-01", 
    
            "organizationPath": "organizationPath" 
    
          } 
    
        ] 
    
      }, 
    
      "user": { 
    
        "userAccount": { 
    
          "logonProfileName": "Default", 
    
          "userName": "userName" 
    
        } 
    
      } 
    
    }
    ```

    注記

    personInformation.emailAddress.address と user.userAccount.userName はどちらも、WFD で作成しようとしている対象の Microsoft Entra ユーザーと一致する必要があります。
5. 右上隅の [ **環境** ] ドロップダウン ボックスを選択し、 **AAD\_to\_WFD\_Env\_Variables**を選択します。
6. JSON ペイロードが更新され、正しい環境変数が選択されたら、[ **アクセス トークン** HTTP 要求の取得] を選択し、[ **送信** ] ボタンを選択します。 これにより、更新された環境変数を利用して WFD インスタンスに対する認証を行い、ユーザーの作成メソッドを呼び出すときに使用する環境変数にアクセス トークンをキャッシュします。
7. 認証呼び出しが成功した場合は、200 応答が表示されてアクセス トークンが返されます。 このアクセス トークンは、**access\_token** エントリの環境変数の **CURRENT VALUE** 列にも表示されるようになります。

    注記

    access\_tokenを受け取らない場合は、環境変数内のすべての変数が正しいことを確認します。 ユーザーの資格情報は、スーパー ユーザー アカウントである必要があります。
8. **access\_token**が取得されたら、**AAD\_to\_WFD\_Env\_Variables** HTTP 要求を選択し、[**送信**] ボタンを選択します。 要求が成功すると、200 HTTP 状態が返されます。
9. **スーパー ユーザー** アカウントを使用して WFD にログインし、新しい Microsoft Entra ユーザーが WFD インスタンス内に作成されたことを確認します。

#### 自動化されたプロセス

自動化されたプロセスは、CSV 形式のフラット ファイルで構成されています。これにより、ユーザーは上記の手動 API プロセスのペイロードで強調表示されている値を事前に指定できます。 フラット ファイルは、新しい WFD ユーザーを一括で作成する付属の PowerShell スクリプトによって使用されます。 スクリプトは、最適なパフォーマンスを得られるように構成可能な 70 (既定値) のバッチで新しいユーザーの作成を処理します。 次の手順では、スクリプトのセットアップと実行について説明します。

1. **AAD\_To\_WFD.csv** ファイルと **AAD\_To\_WFD.ps1** ファイルの両方をコンピューターにローカルに保存します。
2. **AAD\_To\_WFD.csv** ファイルを開き、列を入力します。

    - **personInformation.accessAssignment.accessProfileName**: WFD インスタンスからの特定のアクセス プロファイル名。
    - **personInformation.emailAddresses.address**: Microsoft Entra ID のユーザー プリンシパル名と一致する必要があります。
    - **personInformation.personNumber**: WFD インスタンス全体で一意である必要があります。
    - **personInformation.firstName**: ユーザーの名。
    - **personInformation.lastName**: ユーザーの姓。
    - **jobAssignment.jobAssignmentDetails.payRuleName**: WFD からの特定の支払いルール名。
    - **jobAssignment.jobAssignmentDetails.timeZoneName**: タイムゾーン形式は WFD インスタンス (つまり、 `(GMT -08:00) Pacific Time`) と一致する必要があります。
    - **jobAssignment.primaryLaborAccounts.organizationPath**: WFD インスタンス内の特定のビジネス構造の組織パス。
3. .csv ファイルを保存します。
4. 変更するには、**AAD\_To\_WFD.ps1** スクリプトを右ボタンで選択し、**[編集]** を選択して変更します。
5. 15 行目で指定したパスが、 **AAD\_To\_WFD.csv** ファイルへの正しい名前/パスであることを確認します。
6. WFD インスタンスに関連する UKG サポートによって提供される値を使用して、次の行を更新します。

    - 行 33: バニティURL (vanityUrl)
    - 行 43: appKey
    - 行 48: client\_id
    - 行 49: client\_secret
7. スクリプトを保存して実行します。
8. プロンプトが表示されたら、WFD **スーパー ユーザー** の資格情報を指定します。
9. 完了すると、作成に失敗したすべてのユーザーの一覧がスクリプトから返されます。

注記

WFD インスタンスの入力ミスまたはフィールドの不一致の結果として返される場合は、AAD\_To\_WFD.csv ファイルに指定されている値を確認してください。 バッチ内のすべてのユーザーが既にインスタンスに存在する場合は、WFD API インスタンスからもエラーが返される可能性があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Kronos Workforce Dimensions のサインオン URL にリダイレクトされます。
- Kronos Workforce Dimensions のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Kronos Workforce Dimensions] タイルを選択すると、Kronos Workforce Dimensions のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kudos-tutorial"} -->
## Microsoft Entra ID で Kudos for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kudos-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kudos 間のシングル サインオンを構成する方法について説明します。

この記事では、Kudos と Microsoft Entra ID を統合する方法について説明します。 Kudos を Microsoft Entra ID と統合すると、次のことが可能になります。

- Kudos にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Kudos に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kudos でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Kudos では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Kudos の追加

Microsoft Entra ID への Kudos の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Kudos を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに **[Kudos]** と入力します。
4. 結果のパネルから **[Kudos]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kudos 用に Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Kudos に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Kudos の関連ユーザーとの間にリンク関係を確立する必要があります。

Kudos に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kudos SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Kudos テスト ユーザーの作成** - Kudos において B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Kudos**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY>.kudosnow.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[Kudos クライアント サポート チーム](http://success.kudosnow.com/home)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Set up Kudos](Kudos の設定)** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kudos SSO の構成

1. 別の Web ブラウザーのウィンドウで、Kudos 企業サイトに管理者としてサインインします。
2. 上部のメニューで、[ **設定] アイコン**を選択します。

    [Image: 設定]
3. **[Integrations &gt; SSO**] を選択し、次の手順を実行します。

    [Image: SSO]

    a. **[サインオン URL]** テキストボックスに、**[ログイン URL]** の値を貼り付けます。

    b。 base-64 でエンコードされた証明書をメモ帳で開き、内容をクリップボードにコピーし、[**X.509 証明書**] ボックスに貼り付けます。

    c. **[ログアウト URL]** テキストボックスに、**[ログアウト URL]** の値を貼り付けます。

    d. [**Your Kudos URL**] ボックスに、会社名を入力します。

    e. **保存** を選択します。

#### Kudos のテスト ユーザーの作成

Microsoft Entra ユーザーが Kudos にサインインできるようにするには、そのユーザーを Kudos にプロビジョニングする必要があります。 Kudos の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. **Kudos** の企業サイトに管理者としてサインインします。
2. 上部のメニューで、[ **設定] アイコン**を選択します。

    [Image: 設定]
3. [ **ユーザー管理者] を選択します**。
4. [ **ユーザー** ] タブを選択し、[ **ユーザーの追加]** を選択します。

    [Image: ユーザー管理者]
5. [**ユーザーの追加**] セクションで、次の手順を実行します。

    [Image: ユーザーを追加する]

    a. 関連テキスト ボックスに、プロビジョニングする有効な Microsoft Entra アカウントの [**名**]、[**姓**]、[**電子メール**]、およびその他の詳細情報を入力します。

    b。 [ **ユーザーの作成] を選択します**。

注

他の Kudos ユーザー アカウント作成ツールや、Kudos によって提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Kudos のサインオン URL にリダイレクトされます。
- Kudos のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Kudos] タイルを選択すると、このオプションは Kudos のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kumolus-tutorial"} -->
## Microsoft Entra ID で Kumolus for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kumolus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kumolus の間にシングル サインオンを構成する方法について説明します。

この記事では、Kumolus と Microsoft Entra ID を統合する方法について説明します。 Kumolus を Microsoft Entra ID と統合すると、次のことができるようになります。

- Kumolus にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Kumolus に自動的にサインインできるようにすることができます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kumolus でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Kumolus では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Kumolus では、**Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Kumolus の追加

Microsoft Entra ID への Kumolus の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Kumolus を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Kumolus**」と入力します。
4. 結果のパネルから **[Kumolus]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kumolus 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Kumolus で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Kumolus の関連ユーザーとの間にリンク関係を確立する必要があります。

Kumolus で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kumolus の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Kumolus のテスト ユーザーの作成 - Kumolus** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Kumolus**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    エイ。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.kumolus.net/sso/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.kumolus.net/sso/acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.kumolus.net/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Kumolus クライアント サポート チーム](mailto:kumoas@kumolus.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Kumolus アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Kumolus アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メルアド | ユーザーのメールアドレス |
    | ロール | user.assignedroles |

    注

    Kumolus では、アプリケーションに対してユーザーのロールが割り当てられていることを想定しています。 ユーザーに適切なロールを割り当てることができるように、Microsoft Entra ID でこれらのロールを設定してください。 Microsoft Entra ID でロールを構成する方法については、 [こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Kumolus のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kumolus の SSO の構成

**Kumolus** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Kumolus サポート チーム](mailto:kumoas@kumolus.com) に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Kumolus のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Kumolus に作成します。 Kumolus では、Just-In-Time プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Kumolus に存在しない場合は、Kumolus にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Kumolus のサインオン URL にリダイレクトされます。
- Kumolus のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Kumolus に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [Kumolus] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Kumolus に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/labelbox-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Labelbox を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/labelbox-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-28
- Summary: Microsoft Entra と Labelbox の間のシングル サインオンを構成する方法について説明します。

この記事では、Labelbox と Microsoft Entra ID を統合する方法について説明します。 Labelbox と Microsoft Entra ID を統合すると、次のことができます。

Labelbox にアクセスできるユーザーを、Microsoft Entra ID を使って制御する。 ユーザーが自分の Microsoft Entra アカウントを使用して Labelbox に自動的にサインインできるようにする。 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Labelbox でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから Labelbox を追加する

Microsoft Entra ID への Labelbox の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Labelbox を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Labelbox**」と入力します。
4. 結果のパネルから **[Labelbox]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Labelbox**&gt;**シングルサインオン**にブラウズします。
3. 次のセクションで以下の手順を実行します。

    ある。 **[アプリケーションに移動]**を選択します。

    [Image: ID 構成を示すスクリーンショット。]

    b。 **[アプリケーション (クライアント) ID]** をコピーして、後で Labelbox 側の構成で使います。

    [Image: アプリケーション クライアント値のスクリーンショット。]

    c. **[エンドポイント]** タブで、**[OpenID Connect メタデータ ドキュメント]** のリンクをコピーし、後で Labelbox 側の構成で使用します。

    [Image: タブのエンドポイントを示すスクリーンショット。]
4. 左側のメニューの **[認証]** タブに移動し、次の手順を実行します。

    ある。 **[ID トークン (暗黙的およびハイブリッド フローに使用)]** チェックボックスを有効にします。

    [Image: アクセス トークンを示すスクリーンショット。]

    b。 **[保存] を選択します**。

    注

    **[リダイレクト URI]** の値が自動的に入力されるため、ここでは手動で構成する必要はありません。
5. 左側のメニューの **[証明書とシークレット]** に移動し、次の手順を実行します。

    1. **[クライアント シークレット]** タブに移動し、**[+ 新しいクライアント シークレット]** を選択します。
    2. テキストボックスに有効な **[説明]** を入力し、要件に応じてドロップダウンから **[有効期限]** 日数を選択し **[追加]** を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、**[値]** が生成されます。 この値をコピーして、後で Labelbox 側の構成で使用します。

        [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Labelbox SSO の構成

**Labelbox** 側で OAuth/OIDC フェデレーションのセットアップを完了するには、Entra からコピーした値 (クライアント ID、クライアント シークレット、OIDC メタデータ ファイルなど) を [Labelbox サポート チーム](mailto:support@labelbox.com)に送信する必要があります。 サポート チームはこれを設定して、OIDC 接続が両方の側で正しく設定されるようにします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lablog-tutorial"} -->
## Microsoft Entra ID で LabLog for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lablog-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LabLog 間にシングル サインオンを構成する方法について説明します。

この記事では、LabLog と Microsoft Entra ID を統合する方法について説明します。 LabLog と Microsoft Entra ID を統合すると、次のことができます:

- LabLog にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して LabLog に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LabLog でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- LabLog では、**SP** Initiated SSO がサポートされます
- LabLog では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの LabLog の追加

Microsoft Entra ID への LabLog の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に LabLog を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**LabLog**」と入力します。
4. 結果のパネルから **[LabLog]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LabLog 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、LabLog で Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと LabLog の関連ユーザーとの間にリンク関係を確立する必要があります。

LabLog に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LabLog の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LabLog のテストユーザーを作成 - B.Simon に対応するユーザーを作成して、Microsoft Entra のユーザー表現とリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**LabLog**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_SUBDOMAIN>.labnotebook.app/lablog/login/sso/`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[LabLog クライアント サポート チーム](mailto:support@labnotebook.app)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[LabLog のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LabLog の SSO の構成

1. LabLog の Web サイトに管理者としてログインします。
2. 左側 **のメニューで [シングル サインオン]** アイコンを選択します。
3. 次のページで以下の手順を実行します。

    [Image: LabLog の構成]

    ある。 [ **エンティティ ID** ] ボックスに、前にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    b。 **[SAML SSO ログイン URL]** テキスト ボックスに、前にコピーした **[ログイン URL]** の値を貼り付けます。

    c. ダウンロードした **証明書 (Base64)** をメモ帳に開き、[ **パブリック証明書** ] ボックスに内容を貼り付けます。

    d. **[保存] を選択します**。

#### LabLog のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを LabLog に作成します。 LabLog では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 LabLog にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる LabLog のサインオン URL にリダイレクトされます。
- LabLog のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [LabLog] タイルを選択すると、このオプションは LabLog のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lambda-test-single-sign-on-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LambdaTest シングル サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lambda-test-single-sign-on-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LambdaTest シングル サインオンの間でシングル サインオンを構成する方法についてご確認ください。

この記事では、LambdaTest シングル サインオン と Microsoft Entra ID を統合する方法について説明します。 LambdaTest のシングル サインオン アプリケーションを使用すると、Microsoft Entra インスタンスに対する SSO を自己構成できます。 LambdaTest シングル サインオン を Microsoft Entra ID と統合すると、次のことができます:

- LambdaTest シングル サインオン にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して LambdaTest シングル サインオン に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で LambdaTest シングル サインオン 向けの Microsoft Entra のシングル サインオンを構成してテストします。 LambdaTest シングル サインオンでは、 **SP** と **IDP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングの両方がサポートされます。

### [前提条件]

Microsoft Entra ID と LambdaTest シングル サインオン を統合するには、次のものが必要です:

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- LambdaTest Single Sign on でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから LambdaTest シングル サインオン アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから LambdaTest シングル サインオン を追加する

Microsoft Entra アプリケーション ギャラリーから LambdaTest シングル サインオン を追加して、LambdaTest シングル サインオン とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**LambdaTest シングルサインオン**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:lambdatest:<CustomerName>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://lambdatest.auth0.com/login/callback?connection=<CustomerName>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://accounts.lambdatest.com/auth0/login`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、LambdaTest シングル サインオン クライアント サポート チーム](mailto:support@lambdatest.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **LambdaTest シングル サインオンの設定** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### LambdaTest Single Sign on SSO を構成する

**LambdaTest シングル** サインオン側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [LambdaTest シングル サインオン サポート チームに](mailto:support@lambdatest.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### LambdaTest Single Sign on テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを LambdaTest Single Sign on に作成します。 LambdaTest Single Sign on では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 LambdaTest Single Sign on にユーザーがまだ存在していない場合、一般的には認証後に新しいものが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる LambdaTest シングル サインオンのサインオン URL にリダイレクトされます。
- LambdaTest Single Sign on のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した LambdaTest シングル サインオンに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [LambdaTest Single Sign on] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した LambdaTest シングル サインオンに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/landgorilla-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Land Gorilla を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/landgorilla-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Land Gorilla の間のシングル サインオンを構成する方法について説明します。

この記事では、Land Gorilla と Microsoft Entra ID を統合する方法について説明します。 Land Gorilla と Microsoft Entra ID を統合すると、次のことができます。

- Land Gorilla にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Land Gorilla に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Land Gorilla でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Land Gorilla では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Land Gorilla の追加

Microsoft Entra ID への Land Gorilla の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Land Gorilla を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Land Gorilla**」と入力します。
4. 結果のパネルから **[Land Gorilla]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Land Gorilla 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Land Gorilla に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Land Gorilla の関連ユーザーとの間にリンク関係を確立する必要があります。

Land Gorilla に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Land Gorilla SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Land Gorilla のテストユーザーを作成する** - B.Simon に対応するユーザーを Land Gorilla に作成し、それを Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Land Gorilla** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<customer domain>.landgorilla.com/` |
    | `https://www.<customer domain>.landgorilla.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<customer domain>.landgorilla.com/simplesaml/module.php/core/authenticate.php` |
    | `https://www.<customer domain>.landgorilla.com/simplesaml/module.php/core/authenticate.php` |
    | `https://<customer domain>.landgorilla.com/simplesaml/module.php/saml/sp/saml2-acs.php/default-sp` |
    | `https://www.<customer domain>.landgorilla.com/simplesaml/module.php/saml/sp/saml2-acs.php/default-sp` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 ここでは、識別子に一意の文字列値を使用することをお勧めします。 これらの値の取得については、[Land Gorilla Client サポート チーム](https://www.landgorilla.com/support/)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Land Gorilla のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Land Gorilla SSO の構成

**Land Gorilla** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[Land Gorillaサポート チーム](https://www.landgorilla.com/support/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Land Gorilla テスト ユーザーの作成

このセクションでは、Land Gorilla で Britta Simon というユーザーを作成します。 [Land Gorilla サポート チーム](https://www.landgorilla.com/support/)と連携して、Land Gorilla プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Land Gorilla に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Land Gorilla] タイルを選択すると、SSO を設定した Land Gorilla に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lanschool-air-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に LanSchool Air を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lanschool-air-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra ID から LanSchool Air に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために LanSchool Air と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [LanSchool Air](https://lanschoolair.lenovosoftware.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- ユーザーを LanSchool Air で作成します。
- アクセスが不要になったら、LanSchool Air のユーザーを削除します。
- Microsoft Entra ID と LanSchool Air の間でユーザー属性の同期を維持する。
- LanSchool Air に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lanschool-air-tutorial)します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者アクセス許可がある LanSchool Air のユーザーアカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と LanSchool Air の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように LanSchool Air を構成する

1. サイト管理者として、LanSchool Air にログインします。
2. 左上のメニューを選択し、[ **設定]** を選択します。

    [Image: LanSchool Air 管理者の [設定] メニューのスクリーンショット。]
3. **[SSO 構成] を選択します**。

    [Image: [SSO 構成設定] ページのスクリーンショット。]
4. [ **新規作成] を選択します**。 システムによってランダムな秘密トークンが生成されます。 **[コピー] を選択します**。

    [Image: SCIM トークン生成ダイアログのスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから LanSchool Air を追加する

Microsoft Entra アプリケーション ギャラリーから LanSchool Air を追加して、LanSchool Air へのプロビジョニングの管理を開始します。 以前に LanSchool Air を SSO 向けに設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: LanSchool Air への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、LanSchool Air でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で LanSchool Air の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **LanSchool Air**] を選択します。

    [Image: アプリケーションの一覧の [LanSchool Air] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: アプリケーション設定の [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、LanSchool Air テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が LanSchool Air に接続できることを確認します。 接続に失敗した場合は、LanSchool Air アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から LanSchool Air に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために LanSchool Air のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、LanSchool Air API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | LanSchool Air で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | externalId | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lanschool-air-tutorial"} -->
## Microsoft Entra ID でのシングルサインオンに向けて LanSchool Air を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lanschool-air-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LanSchool Air の間のシングル サインオンを構成する方法について説明します。

この記事では、LanSchool Air と Microsoft Entra ID を統合する方法について説明します。 LanSchool Air を Microsoft Entra ID と統合すると、次のことが可能になります。

- LanSchool Air にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して LanSchool Air に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LanSchool Air でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- LanSchool Air では、**SP開始SSO**とIDP開始SSOがサポートされます。
- LanSchool Air では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lanschool-air-provisioning-tutorial)。

### ギャラリーから LanSchool Air を追加する

Microsoft Entra ID への LanSchool Air の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に LanSchool Air を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリ**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「LanSchool Air**」と入力します。
4. 結果パネルから **LanSchool Air** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LanSchool Air 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、LanSchool Air に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと LanSchool Air の関連ユーザーとの間にリンク関係を確立する必要があります。

LanSchool Air で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LanSchool Air の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **LanSchool Air のテストユーザーを作成** - Microsoft Entra のユーザー表現にリンクさせた B.Simon に対応するユーザーを LanSchool Air で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**LanSchool Air**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://lanschoolair.lenovosoftware.com`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LanSchool Air の SSO の構成

**LanSchool Air** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[LanSchool Air サポート チーム](mailto:support@lanschool.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### LanSchool Air テスト ユーザーの作成

このセクションでは、LanSchool Air で Britta Simon というユーザーを作成します。 [LanSchool Air サポート チーム](mailto:support@lanschool.com)と協力して、LanSchool Air プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる LanSchool Air Sign on URL にリダイレクトされます。
- LanSchool Air のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した LanSchool Air に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [LanSchool Air] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した LanSchool Air に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lattice-tutorial"} -->
## Microsoft Entra ID で Lattice for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lattice-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Lattice の間でシングル サインオンを構成する方法について説明します。

この記事では、Lattice と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Lattice を統合すると、次のことができます。

- Microsoft Entra ID で Lattice へのアクセスを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して自動的に Lattice にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Lattice でのシングル サインオン (SSO) が有効なサブスクリプション。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra ID でアプリケーションを追加または管理することもできます。 詳細については、Azure 組み込みロール に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Lattice では、**SP** および **IDP** によって開始される SSO がサポートされます。

### ギャラリーから Lattice を追加する

Microsoft Entra ID への Lattice の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Lattice を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Lattice**」と入力します。
4. 結果パネルから **[Lattice** ] を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Lattice の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Lattice に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Lattice の関連ユーザーとの間にリンク関係を確立する必要があります。

Lattice で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Lattice SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Lattice のテストユーザーを作成** - Microsoft Entra でのユーザーとして B.Simon に対応するユーザーを Lattice に作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Lattice**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://router.latticehq.com/sso/<subdomain>/metadata`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://router.latticehq.com/sso/<subdomain>/acs`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://router.latticehq.com/sso/lattice/sp-login-redirect`

    手記

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Lattice サポート チーム](mailto:customercare@lattice.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Lattice のセットアップ** ]セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Lattice SSO の構成

1. Lattice 企業サイトに管理者としてログインします。
2. **Admin**&gt;**Platform**&gt;**Settings**&gt;**シングルサインオン設定**に移動し、次の手順を実行します。

    [Image: [構成設定] を示すスクリーンショット。]

    a. [ **XML メタデータ** ] ボックスに、前にコピーした **フェデレーション メタデータ XML** ファイルを貼り付けます。

    b。 **[保存] を選択します**。

#### Lattice テスト ユーザーの作成

このセクションでは、Lattice で Britta Simon というユーザーを作成します。 [Lattice サポート チーム](mailto:customercare@lattice.com)と協力して、Lattice プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Lattice のサインオン URL にリダイレクトされます。
- Lattice のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Lattice に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Lattice] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Lattice に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/launchdarkly-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LaunchDarkly を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/launchdarkly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LaunchDarkly 間にシングル サインオンを構成する方法について学習します。

この記事では、LaunchDarkly と Microsoft Entra ID を統合する方法について説明します。 LaunchDarkly を Microsoft Entra ID と統合すると、次のことができます:

- LaunchDarkly にアクセスできるユーザーをMicrosoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って LaunchDarkly に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

    注

    LaunchDarkly の Microsoft Entra との統合は一方向です。 統合を構成したら、Microsoft Entra ID を使用して LaunchDarkly でユーザー、SSO、アカウントを管理できますが、LaunchDarkly を使用して Azure でユーザー、SSO、アカウントを管理 **することはできません** 。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LaunchDarkly でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- LaunchDarkly では、**IDP** Initiated SSO がサポートされます。
- LaunchDarkly では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの LaunchDarkly の追加

Microsoft Entra ID への LaunchDarkly の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に LaunchDarkly を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**LaunchDarkly**」と入力します。
4. 結果のパネルから **[LaunchDarkly]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LaunchDarkly 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、LaunchDarkly に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと LaunchDarkly の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を LaunchDarkly と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LaunchDarkly の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LaunchDarkly テスト ユーザーの作成 - LaunchDarkly** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**LaunchDarkly**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `app.launchdarkly.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.launchdarkly.com/trust/saml2/acs/<customers-unique-id>`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL を使用して値を更新します。これについては、この記事の後半で説明します。 LaunchDarkly では現在、**IDP** Initiated SSO がサポートされます。 このアプリケーションを **IDP** モードで使用するには、[ **サインオン URL** ] フィールドを空白のままにする必要があります。それ以外の場合は **、IDP** からログインを開始できません。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[LaunchDarkly のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LaunchDarkly の SSO の構成

1. 別の Web ブラウザーのウィンドウで、LaunchDarkly 企業サイトに管理者としてログインします。
2. 左側のナビゲーション パネルから **[アカウント設定]** を選択します。

    [Image: [Production](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/運用) の下で [Account Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント設定) 項目が選択されているスクリーンショット。]
3. **[セキュリティ]** タブを選びます。

    [Image: [Account settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント設定) の [Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ) タブを示すスクリーンショット。]
4. [ **SSO の有効化]** を選択し、[ **SAML 構成] を編集**します。

    [Image: [ENABLE S S O](S S O を有効にする) および [EDIT SAML CONFIGURATION](SAML 構成の編集) を操作できる [Single sign-on](シングル サインオン) ページを示すスクリーンショット。]
5. **[Edit your SAML configuration]\(SAML の構成の編集)** セクションで、次の手順を実行します。

    [Image: ここで説明されている変更を行うことができる [Edit your SAML configuration](SAML の構成の編集) セクションを示すスクリーンショット。]

    a。 インスタンスの **SAML コンシューマー サービス URL** をコピーし、Azure Portal で、 **[LaunchDarkly ドメインと URL]** セクションの [応答 URL] ボックスに貼り付けます。

    b。 **[サインオン URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    c. ダウンロードした証明書をメモ帳に開き、内容をコピーして **X.509 証明書** ボックスに貼り付けます。または、アップロードを選択して証明書を直接 **アップロード**できます。

    d. **保存** を選択します。

#### LaunchDarkly テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを LaunchDarkly に作成します。 LaunchDarkly では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 LaunchDarkly にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した LaunchDarkly に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [LaunchDarkly] タイルを選択すると、SSO を設定した LaunchDarkly に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lawvu-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に LawVu を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lawvu-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: Microsoft Entra IDから LawVu にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために LawVu と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra ID が構成されると、Microsoft Entra プロビジョニング サービスを使用してユーザーを [LawVu](https://lawvu.com/) に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- LawVu でユーザーを作成します。
- アクセスが不要になった場合は、LawVu のユーザーを削除します。
- Microsoft Entra IDと LawVu の間でユーザー属性の同期を維持します。
- LawVu に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lawvu-tutorial) します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- テナント URL とシークレット トークン。
- アクティブな LawVu アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. どのデータを[Microsoft Entra IDとLawVuの間でマッピングするか決定する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように LawVu を構成する

LawVu の連絡先から、LawVu テナントの URL と対応するシークレット トークンが送られてきます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから LawVu を追加する

Microsoft Entra アプリケーション ギャラリーから LawVu を追加して、LawVu へのプロビジョニングの管理を開始します。 SSO のために LawVu を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: LawVu への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて LawVu のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで LawVu の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **LawVu** を選択します。

    [Image: アプリケーションリストの LawVu のリンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、LawVu テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが LawVu に接続できることを確認します。 接続に失敗した場合は、LawVu アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから LawVu に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で LawVu のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、LawVu API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | LawVu で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | タイトル | 糸 |  |  |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |

    注

    LawVu アプリは**スキーマ検出**をサポートしています。 `/schemas` 要求は、Azure ポータルでプロビジョニング構成を保存するたびに、またはユーザーがプロビジョニングの編集ページに移動するたびに、Microsoft Entra プロビジョニング サービスによって行われます。 検出されたその他の属性は、ターゲット属性リストの属性マッピングで顧客に表示されます。 スキーマ検出では、対象の属性が新たに追加されるだけです。 属性が削除されることはありません。
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lawvu-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LawVu を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lawvu-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LawVu 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、LawVu と Microsoft Entra ID を統合する方法について説明します。 LawVu を Microsoft Entra ID と統合すると、次のことができます:

- LawVu にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って LawVu に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LawVu でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- LawVu では、**SPおよびIDP**によって開始されるSSOがサポートされます。
- LawVu では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから LawVu を追加する

Microsoft Entra ID への LawVu の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに LawVu を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;の**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「LawVu**」と入力します。
4. 結果パネルから **LawVu** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LawVu 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、LawVu に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと LawVu の関連ユーザーとの間にリンク関係を確立する必要があります。

LawVu に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LawVu SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LawVuでテストユーザーを作成** - LawVuでB.Simonに対応するユーザーを作り、Microsoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**LawVu**&gt;** シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] ページのスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    - [ **応答 URL** ] テキスト ボックスに、「 `https://api-<REGION>.lawvu.com/sso/validate/<GUID>`」というパターンを使用して URL を入力します。

    注

    この値は実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには [、LawVu クライアント サポート チーム](mailto:support@lawvu.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    - [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://go.lawvu.com`
7. LawVu アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、LawVu アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | uniqueId | user.objectid (ユーザーのオブジェクトID) |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
10. [ **LawVu のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: コピー構成 URL のスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LawVu の SSO を構成する

**LawVu** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [LawVu サポート チーム](mailto:support@lawvu.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### LawVu のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを LawVu に作成します。 LawVu では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 LawVu にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

SP Initiated を使用してテストするには、2 つのオプションがあります。

- Azure portal で、[ **このアプリケーションをテスト**する] を選択します。 LawVu のサインオン URL にリダイレクトされ、ログイン フローを開始することができます。
- LawVu のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Azure portal で、[ **このアプリケーションをテスト**する] を選択します。 SSO を設定した LawVu に自動的にサインインされます。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで LawVu タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した LawVu に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lcvista-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LCVista を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lcvista-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LCVista の間にシングル サインオンを構成する方法について説明します。

この記事では、LCVista と Microsoft Entra ID を統合する方法について説明します。 LCVista と Microsoft Entra ID を統合すると、次のことができます。

- LCVista にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って LCVista に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

LCVista と Microsoft Entra の統合を構成するには、次の項目が必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- LCVista でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- LCVista では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの LCVista の追加

Microsoft Entra ID への LCVista の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に LCVista を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「LCVista**」と入力します。
4. 結果パネルから **LCVista** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LCVista 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、LCVista に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと LCVista の関連ユーザーとの間にリンク関係を確立する必要があります。

LCVista に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LCVista SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LCVista テストユーザーの作成** - Microsoft Entra 上のユーザーの表現にリンクされた B.Simon の対応ユーザーを LCVista に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**LCVista**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.lcvista.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.lcvista.com/rainier/login`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 [LCVista クライアント サポート チーム](https://lcvista.com/contact) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **LCVista のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LCVista SSO の構成

1. 管理者として LCVista アプリケーションにログインします。
2. **[SAML Config]\(SAML 構成\**) セクションで、[**Enable SAML login]\(SAML ログインを有効にする\)** を確認し、次の図に示すように詳細を入力します。

    [Image: シングル サインオンの構成]

    a [ **エンティティ ID** ] ボックスに、前にコピーした **Microsoft Entra Identifier** 値を貼り付けます。

    b。 **URL** テキスト ボックスに、前にコピーした**ログイン URL** 値を貼り付けます。

    c. Azure portal からダウンロードしたメタデータ XML ファイルをメモ帳に開き、 **値 X509Certificate** をコピーして **、x509 証明書** セクションに貼り付けます。

    d. [**First name attribute（名属性）**] ボックスに、値を `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname` 貼り付けます。

    え [ **姓属性** ] テキストボックスに、値を `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname` 貼り付けます。

    f. [ **Email 属性** ] ボックスに、値を `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`貼り付けます。

    ジー [ **Username attribute]\(ユーザー名属性** \) ボックスに、値 `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name`貼り付けます。

    え [ **保存] を** 選択して設定を保存します。

#### LCVista のテスト ユーザーを作成する

このセクションでは、LCVista で Britta Simon というユーザーを作成します。 [LCVista クライアント サポート チーム](https://lcvista.com/contact)と協力して、LCVista プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる LCVista のサインオン URL にリダイレクトされます。
- LCVista のサインオン URL に直接移動し、そこからログインフローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [LCVista] タイルを選択すると、このオプションは LCVista のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/leadfamly-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Leadfamly を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/leadfamly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Leadfamly の間のシングル サインオンを構成する方法について説明します。

この記事では、Leadfamly と Microsoft Entra ID を統合する方法について説明します。 Leadfamly を Microsoft Entra ID と統合すると、次のことが可能になります。

- Leadfamly にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Leadfamly に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Leadfamly でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Leadfamly では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Leadfamly の追加

Microsoft Entra ID への Leadfamly の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Leadfamly を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Leadfamly**」と入力します。
4. 結果パネルから **Leadfamly** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Leadfamly に対する Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Leadfamly に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Leadfamly の関連ユーザーとの間にリンク関係を確立する必要があります。

Leadfamly に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Leadfamly SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Leadfamly のテスト ユーザーの作成** - Leadfamly で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Leadfamly]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://appv2.leadfamly.com/saml-sso/<INSTANCE ID>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL でこれらの値を更新します。 これらの値を取得するには [、Leadfamly クライアント サポート チーム](mailto:support@leadfamly.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Leadfamly のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Leadfamly SSO の構成

1. Leadfamly 企業サイトに管理者としてログインします。
2. **アカウント** -&gt;**Customer 情報** -&gt;**SAML SSO** に移動します。

[Image: アカウント]

1. **SAML SSO を**有効にし、ドロップダウン リストから **Microsoft Entra ID** Provider を選択し、次の手順を実行します。

[Image: 情報]

a. 識別子の値**を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子** URL] テキスト ボックスにこの値を貼り付けます。

b。 **[応答 URL]** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。

c. **[サインオン URL**] の値をコピーし、[**基本的な SAML 構成**] セクションの **[サインオン URL**] テキスト ボックスにこの値を貼り付けます。

d. ダウンロードした **フェデレーション メタデータ XML** ファイルをメモ帳に開き、その内容を **フェデレーション メタデータ XML** にアップロードします。

e. **[保存] を選択します**。

#### Leadfamly のテスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、管理者として Leadfamly Web サイトにサインインします。
2. **Account**&gt;**Users**&gt;**ユーザー招待**に進みます。

[Image: ユーザーセクション]

1. 次のフィールドに必要な値を入力し、[ **保存]** を選択します。

[Image: ユーザーを変更]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Leadfamly のサインオン URL にリダイレクトされます。
- Leadfamly のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Leadfamly] タイルを選択すると、このオプションは Leadfamly のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lean-tutorial"} -->
## Microsoft Entra ID で Lean for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lean-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Lean の間のシングル サインオンを構成する方法について説明します。

この記事では、Lean と Microsoft Entra ID を統合する方法について説明します。 Lean を Microsoft Entra ID と統合すると、次の利点があります。

- Lean にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが Microsoft Entra アカウントで Lean に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Lean でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Lean では、**SP** によって開始される SSO がサポートされます
- Lean では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Lean の追加

Microsoft Entra ID への Lean の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Lean を追加する必要があります。

**ギャラリーから Lean を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Lean**」と入力し、結果パネルで **Lean** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Lean]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Lean で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Lean の関連ユーザー間にリンク関係を確立する必要があります。

Lean に対する Microsoft Entra シングル サインオンを構成およびテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Lean シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Lean のテストユーザーを作成する** - Lean 内で Britta Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の Britta Simon にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Lean に対する Microsoft Entra シングル サインオンを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Lean** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [Lean のドメインと URL] のシングル サインオン情報]

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.goodpractice.net/api/gpsso`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `bloom-goodpractice-<SUBDOMAIN>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 この値を取得するには、[Lean クライアント サポート チーム](mailto:support@goodpractice.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Lean の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Lean のシングル サインオンを構成する

**Lean** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Lean サポート チーム](mailto:support@goodpractice.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Lean テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Lean に作成します。 Lean では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Lean にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Lean] タイルを選択すると、SSO を設定した Lean に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/leandna-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LeanDNA を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/leandna-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LeanDNA 間のシングル サインオンを構成する方法について説明します。

この記事では、LeanDNA を Microsoft Entra ID と統合する方法について説明します。 Azure を使用し、SAML 2.0 SSO を介して LeanDNA アプリに接続します。 LeanDNA を Microsoft Entra ID と統合すると、次のことができます。

- LeanDNA にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで LeanDNA に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

LeanDNA に対する Microsoft Entra のシングル サインオンをテスト環境で構成してテストする。 LeanDNA では、 **SP** によって開始されるシングル サインオンのみがサポートされます。

### [前提条件]

LeanDNA を Microsoft Entra ID と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- LeanDNA でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから LeanDNA アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから LeanDNA を追加する

Microsoft Entra アプリケーション ギャラリーから LeanDNA を追加して、LeanDNA とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**LeanDNA**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://www.leandna.com/auth/1/saml2/metadata/customer/<ID>` |
    | `https://app.leandna.com/auth/1/saml2/metadata/customer/<ID>` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://www.leandna.com/auth/1/saml2/login/customer/<ID>` |
    | `https://app.leandna.com/auth/1/saml2/login/customer/<ID>` |

    c. [ **サインオン URL** ] ボックスに、URL を入力します。 `https://www.leandna.com/application/sso.html`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [LeanDNA クライアント サポート チーム](mailto:support@leandna.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **LeanDNA のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### LeanDNA SSO の構成

**LeanDNA** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [LeanDNA サポート チーム](mailto:support@leandna.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### LeanDNA のテスト ユーザーの作成

このセクションでは、LeanDNA で Britta Simon というユーザーを作成します。 [LeanDNA サポート チーム](mailto:support@leandna.com)と協力して、LeanDNA プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる LeanDNA サインオン URL にリダイレクトされます。
- LeanDNA のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで LeanDNA タイルを選択すると、このオプションは LeanDNA のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/leapsome-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Leapsome を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/leapsome-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: ユーザー アカウントを Leapsome に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Leapsome に対してユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するために Leapsome とMicrosoft Entra IDで実行する手順を示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

このコネクタは、現在プレビューの段階です。 プレビューの詳細については、[オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)に関するページを参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entraユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.
- [Leapsome](https://www.Leapsome.com/pricing) テナントアカウント。
- Admin アクセス許可がある Leapsome のユーザー アカウント。

### Leapsome へのユーザーの割り当て

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Leapsome へのアクセスが必要なMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Leapsome に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Leapsome に割り当てるときの重要なヒント

- 1 人のMicrosoft Entra ユーザーを Leapsome に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Leapsome にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### プロビジョニング用に Leapsome を設定する

1. [Leapsome 管理コンソール](https://www.Leapsome.com/app/#/login)にサインインします。 **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) &gt; [Admin Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者設定)** に移動します。

    [Image: Leapsome 管理コンソールのスクリーンショット。]
2. **[Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) &gt; [SCIM User provisioning](SCIM ユーザー プロビジョニング)** に移動します。

    [Image: Leapsome Add SCIM のスクリーンショット。]
3. **SCIM 認証トークン**をコピーします。 この値は、Leapsome アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。

    [Image: Leapsome Create Token のスクリーンショット。]

### ギャラリーから Leapsome を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Leapsome を構成する前に、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Leapsome を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Leapsome を追加するには、次の手順を実行します:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Leapsome**」と入力し、**[Leapsome]** を選びます。
4. 結果のパネルから **[Leapsome]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の Leapsome のスクリーンショット。]

### Leapsome への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDのユーザーやグループの割り当てに基づいて Leapsome でユーザーやグループを作成、更新、無効化するように、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Leapsome での SAML ベースのシングル サインオンを有効にすることもできます。これは、 [Leapsome シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/leapsome-tutorial)に関する記事に記載されている手順に従って行うこともできます。 シングル サインオンは自動ユーザー プロビジョニングとは独立に構成できますが、これらの 2 つの機能は互いに補完しあいます。

#### Microsoft Entra IDで Leapsome の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Leapsome]** を選択します。

    [Image: アプリケーションの一覧の Leapsome リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Leapsome テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Leapsome に接続できることを確認します。 接続に失敗した場合は、Leapsome アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    注

    `https://www.leapsome.com/api/scim` に「」と入力します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Leapsome に同期されるユーザー属性を確認します。 **[Matching] (照合)** プロパティとして選択されている属性は、更新処理で Leapsome のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Leapsome API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Leapsome ユーザー属性のスクリーンショット。]
12. **Attribute Mapping** セクションで、Microsoft Entra IDから Leapsome に同期されるグループ属性を確認します。 **[Matching] (照合)** プロパティとして選択されている属性は、更新処理で Leapsome のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Leapsome グループ属性のスクリーンショット。]
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### コネクタの制限事項

- Leapsome には、一意な **userName** が必要です。
- Leapsome では、勤務先電子メール アドレスのみ保存できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/leapsome-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Leapsome を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/leapsome-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Leapsome 間のシングル サインオンを構成する方法について説明します。

この記事では、Leapsome と Microsoft Entra ID を統合する方法について説明します。 Leapsome を Microsoft Entra ID と統合すると、次のことが可能になります。

- Leapsome にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Leapsome に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Leapsome のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Leapsome では、**SP と IDP** によって開始される SSO がサポートされます。
- Leapsome では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/leapsome-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Leapsome を追加する

Microsoft Entra ID への Leapsome の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Leapsome を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Leapsome**」と入力します。
4. 結果のパネルから **[Leapsome]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Leapsome 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Leapsome に対する Microsoft Entra SSO を構成およびテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Leapsome の関連ユーザーとの間にリンク関係を確立する必要があります。

Leapsome に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Leapsome の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Leapsome のテストユーザーを作成** - B.Simon のLeapsomeでの対応ユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクする。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Leapsome**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://www.leapsome.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.leapsome.com/api/users/auth/saml/<CLIENTID>/assert`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.leapsome.com/api/users/auth/saml/<CLIENTID>/login`

    注

    上記の応答 URL とサインオン URL の値は実際の値ではありません。 これらは実際の値で更新します。これについては、この記事の後半で説明します。
7. Leapsome アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Leapsome アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 | Namespace |
    | --- | --- | --- |
    | ファーストネーム | User.givenname | https://schemas.xmlsoap.org/ws/2005/05/identity/claims |
    | lastname | ユーザーの名字 | https://schemas.xmlsoap.org/ws/2005/05/identity/claims |
    | タイトル | ユーザー.職名 | https://schemas.xmlsoap.org/ws/2005/05/identity/claims |
    | picture | 社員の画像への URL | https://schemas.xmlsoap.org/ws/2005/05/identity/claims |
    |  |  |  |

    注

    属性 attribute の値は、実際のものではありません。 実際の画像 URL でこの値を更新してください。 この値を取得するには、[Leapsome クライアント サポート チーム](mailto:support@leapsome.com)にお問い合わせください。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Leapsome のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Leapsome の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Leapsome にセキュリティ管理者としてサインインします。
2. 右上の [設定] ロゴを選択し **、[管理者設定]** を選択します。

    [Image: Leapsome セット]
3. 左側のメニュー バーで [ **シングル サインオン (SSO)]** を選択し、 **SAML ベースのシングル サインオン (SSO) ページで** 次の手順を実行します。

    [Image: Leapsome SAML]

    a. **[Enable SAML-based single sign-on](SAML ベースのシングル サインオンを有効にする)** を選択します。

    b。 **[Login URL (point your users here to start login)](ログイン URL (ログインをスタートする場所としてユーザーにここを案内する))** の値をコピーし、**[基本的な SAML 構成]** セクションの **[サインオン URL]** ボックスに貼り付けます。

    c. **[Reply URL (receives response from your identity provider)](応答 URL (ID プロバイダーからの応答をここで受け取る))** の値をコピーし、**[基本的な SAML 構成]** セクションの **[応答 URL]** ボックスに貼り付けます。

    d. **[SSO Login URL (provided by identity provider)](SSO ログイン URL (ID プロバイダーから提供されたもの))** ボックスに、コピーした**ログイン URL** の値を貼り付けます。

    e. Azure portal からダウンロードした証明書を `--BEGIN CERTIFICATE and END CERTIFICATE--` コメントなしでコピーして、**[証明書] (ID プロバイダーによって提供されたもの)** テキストボックスに貼り付けます。

    f. [ **SSO 設定の更新] を選択します**。

#### Leapsome テスト ユーザーの作成

このセクションでは、Leapsome で Britta Simon というユーザーを作成します。 [Leapsome クライアント サポート チーム](mailto:support@leapsome.com)と協力して、Leapsome プラットフォームの許可リストに追加する必要があるユーザーまたはドメインを追加します。 ドメインがチームによって追加されると、ユーザーは Leapsome プラットフォームに自動的にプロビジョニングされます。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Leapsome では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/leapsome-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Leapsome のサインオン URL にリダイレクトされます。
- Leapsome のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Leapsome に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Leapsome] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Leapsome に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/learning-at-work-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Learning at Work を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/learning-at-work-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Learning at Work 間のシングル サインオン (SSO) を構成する方法について説明します。

この記事では、Learning at Work と Microsoft Entra ID を統合する方法について説明します。 Learning at Work を Microsoft Entra ID と統合すると、次のことが可能になります。

- Learning at Work にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Learning at Work に自動的にサインインするように設定できます。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Learning at Work でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Learning at Work では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Learning at Work の追加

Microsoft Entra ID への Learning at Work の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Learning at Work を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Learning at Work**」と入力します。
4. 結果ウィンドウで **[Learning at Work]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Learning at Work 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Learning at Work に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Learning at Work での関連ユーザーとの間にリンク関係を確立する必要があります。

Learning at Work に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Learning at Work SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Learning at Work テスト ユーザーの作成** - Learning at Work で B.Simon に対応するユーザーを作成し、Microsoft Entra のこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Learning at Work** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<subdomain>.sabacloud.com/Saba/Web/<company code>`

    b。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<subdomain>.sabacloud.com/Saba/saml/SSO/alias/<company name>`

    Note

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Learning at Work クライアント サポート チーム](https://www.learninga-z.com/site/contact/support)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Learning at Work アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。

    組織のセットアップに基づいて Microsoft Entra ID の **nameidentifier** 値を更新できます。この値は SABA クラウドの **ユーザー ID** と一致する必要があります。そのため、 **鉛筆** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[Learning at Work のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Learning at Work SSO の構成

**Learning at Work** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Learning at Work サポート チーム](https://www.learninga-z.com/site/contact/support)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Learning at Work テスト ユーザーの作成

このセクションでは、Learning at Work で B.Simon というユーザーを作成します。 [Learning at Work サポート チーム](https://www.learninga-z.com/site/contact/support)と連携し、Learning at Work プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Learning at Work のサインオン URL にリダイレクトされます。
- Learning at Work のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Learning at Work] タイルを選択すると、このオプションは Learning at Work のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/learningpool-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に学習プール LMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/learningpool-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Learning Pool LMS 間のシングル サインオンを構成する方法について説明します。

この記事では、Learning Pool LMS と Microsoft Entra ID を統合する方法について説明します。 Learning Pool LMS を Microsoft Entra ID と統合すると、次のことが可能になります。

- Learning Pool LMS にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Learning Pool LMS に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオンを使用した Learning Pool LMS に対するアクティブなサブスクリプション。

Note

シングル サインオン プロジェクトを開始すると、Learning Pool LMS Delivery チームのメンバーが、このプロセスについて説明します。 Learning Pool LMS 配信チームのメンバーと連絡を取っていない場合は、Learning Pool LMS アカウント マネージャーにお問い合わせください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Learning Pool LMS では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの Learning Pool LMS の追加

Microsoft Entra ID への Learning Pool LMS の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Learning Pool LMS を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに「**Learning Pool LMS**」と入力します。
4. 結果パネルから **[Learning Pool LMS** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Learning Pool LMS 用に Microsoft Entra SSO を構成・テストする

既存の Azure ユーザーを使用して、Learning Pool LMS に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Learning Pool LMS の関連ユーザー間にリンク関係を確立する必要があります。

Learning Pool LMS に対して Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成** する - ユーザーがこの機能を使用できるようにします。
2. **Microsoft Entra ユーザーを割り当てる** - そのユーザーが Microsoft Entra シングル サインオンを使用できるようにします。
3. **Learning Pool LMS SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
4. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Learning Pool LMS**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションで **識別子** の値が自動的に設定されます。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://parliament.preview.Learningpool.com/auth/shibboleth/index.php`

    Note

    **識別子**の値が自動的に設定されない場合は、要件に従って値を手動で入力してください。
6. Azure ユーザーと Learning Pool LMS のユーザーの照合に使用される、少なくとも 1 つの属性を送信する必要があります。 通常は既定の属性で十分ですが、いくつかのカスタム属性を使用して送信する必要がある場合があります。 次のスクリーンショットには、既定の属性一覧が示されています。 [ **編集** ] アイコンを選択して [ユーザー属性] ダイアログを開き、必要に応じてさらに属性を追加します。

    [Image: [編集] アイコンが選択されているユーザー属性を示すスクリーンショット。]
7. [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、[**編集] アイコン**を使用して要求を編集するか、[**新しい要求の追加]** を使用して要求を追加し、上の図に示すように SAML トークン属性を構成し、次の手順を実行します。

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [新しい要求の追加] オプションを含むユーザー要求を示すスクリーンショット。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. [ソース] を **[属性**] として選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. [ **OK] を選択する**

    g. **[保存] を選択します**。
8. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **アプリのフェデレーション メタデータ URL** の [コピー] ボタンを選択し、その URL を学習プール配信チームに渡します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra ユーザーを割り当てる

このセクションでは、既存の Microsoft Entra ユーザーに Learning Pool LMS へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Learning Pool LMS]** に移動します。
3. アプリの概要ページで、[ **管理** ] セクションを見つけて、[ **ユーザーとグループ**] を選択します。
4. [**ユーザーの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] リストから適切なユーザーを選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
7. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Learning Pool LMS SSO の構成

学習プール配信チームは、 **アプリのフェデレーション メタデータ URL を** 使用して、SAML2 接続を受け入れるように LMS を構成します。 接続が正しく構成されていることを確認するためにいくつかのテスト手順を実行するように求められ、学習プール配信チームがこのプロセスを案内します。

#### SSO のテスト

学習プール配信チームによってテスト プロセスがガイドされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/learningseatlms-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Learning Seat LMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/learningseatlms-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Learning Seat LMS 間のシングル サインオン (SSO) を構成する方法について説明します。

この記事では、Learning Seat LMS と Microsoft Entra ID を統合する方法について説明します。 Learning Seat LMS を Microsoft Entra ID と統合すると、次のことが可能になります。

- Learning Seat LMS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Learning Seat LMS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Learning Seat LMS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Learning Seat LMS では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーから Learning Seat LMS を追加する

Microsoft Entra ID への Learning Seat LMS の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Learning Seat LMS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Learning Seat LMS**」と入力します。
4. 結果のパネルから **[Learning Seat LMS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Learning Seat LMS に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Learning Seat LMS に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと Learning Seat LMS の関連ユーザー間にリンク関係を確立する必要があります。

Learning Seat LMS に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Learning Seat LMS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Learning Seat LMS のテスト ユーザーの作成** - Learning Seat LMS で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Learning Seat LMS]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを**IDP** イニシエートモードで構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.learningseatlms.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.learningseatlms.com/Account/AssertionConsumerService`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.learningseatlms.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Learning Seat LMS クライアント サポート チーム](https://azuremarketplace.microsoft.com/marketplace/apps/aad.learnconnect?tab=Overview)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Learning Seat LMS のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Learning Seat LMS SSO の構成

**Learning Seat LMS** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Learning Seat LMS サポート チーム](https://azuremarketplace.microsoft.com/marketplace/apps/aad.learnconnect?tab=Overview)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Learning Seat LMS のテスト ユーザーを作成する

このセクションでは、Learning Seat LMS で Britta Simon というユーザーを作成します。 [Learning Seat LMS サポート チーム](https://azuremarketplace.microsoft.com/marketplace/apps/aad.learnconnect?tab=Overview)と連携して、Learning Seat LMS プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる Learning Seat LMS のサインオン URL にリダイレクトされます。
- Learning Seat LMS のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Learning Seat LMS に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Learning Seat LMS] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Learning Seat LMS に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/learnster-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Learnster を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/learnster-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Learnster の間のシングル サインオンを構成する方法について説明します。

この記事では、Learnster と Microsoft Entra ID を統合する方法について説明します。 Learnster を Microsoft Entra ID と統合すると、次のことが可能になります。

- Learnster にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Learnster に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Learnster でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Learnster では、**SP** Initiated SSO がサポートされます

### ギャラリーからの Learnster の追加

Microsoft Entra ID への Learnster の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Learnster を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Learnster**」と入力します。
4. 結果のパネルから **[Learnster]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Learnster 向けに Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、Learnster に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Learnster での関連ユーザーとの間にリンク関係を確立する必要があります。

Learnster に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Learnster SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **DLearnster テスト ユーザーの作成** - Learnster で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Azure portal でこれらの手順を実行して、Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Learnster]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    1. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.learnster.com/auth/login/force`
    2. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.learnster.com/`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Learnster クライアント サポート チーム](mailto:support@learnster.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Learnster のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Learnster SSO の構成

**Learnster** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Learnster サポート チーム](mailto:support@learnster.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Learnster テスト ユーザーの作成

このセクションでは、Learnster で B.Simon というユーザーを作成します。 [Learnster サポート チーム](mailto:support@learnster.com)と連携して、Learnster プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Learnster] タイルを選択すると、SSO を設定した Learnster に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/learnupon-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LearnUpon を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/learnupon-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LearnUpon の間でシングル サインオンを構成する方法について説明します。

この記事では、LearnUpon と Microsoft Entra ID を統合する方法について説明します。 LearnUpon と Microsoft Entra ID を統合すると、次のことができます。

- LearnUpon にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して LearnUpon に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LearnUpon でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- LearnUpon では、**IDP** Initiated SSO がサポートされます。
- LearnUpon は **Just In Time** ユーザー プロビジョニングをサポートしています。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから LearnUpon を追加する

Microsoft Entra ID への LearnUpon の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に LearnUpon を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**LearnUpon**」と入力します。
4. 結果パネルから**LearnUpon** を選択して、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LearnUpon の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、LearnUpon に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと LearnUpon の関連ユーザーとの間にリンク関係を確立する必要があります。

LearnUpon に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LearnUpon SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **LearnUpon テスト ユーザーの作成** - LearnUpon で B.Simon に対応するユーザーを作成し、Microsoft Entra のこのユーザーにリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[LearnUpon]**&gt;**[シングル サインオン]** を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<companyname>.learnupon.com/saml/consumer`

    手記

    これは実際の値ではありません。 実際の応答 URL で値を更新します。 この値を取得するには、[LearnUpon クライアント サポート チーム](https://www.learnupon.com/contact/) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページで、**[拇印]** を確認します。これが LearnUpon SAML 設定に追加されます。

    [Image: 証明書のダウンロード リンク]
7. **[LearnUpon のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LearnUpon SSO の構成

1. 別のブラウザー インスタンスを開き、管理者アカウントで LearnUpon にサインインします。
2. **[設定**] タブを選択します。

    [設定] タブを示すスクリーンショット [Image: 。]
3. [ **シングル サインオン - SAML**] を選択し、[ **全般設定]** を選択して SAML 設定を構成します。

    [Image: スクリーンショットには、[全般設定] が選択された状態で [シングル サインオン - SAML] が選択されています。]
4. [**全般設定]** セクションで、次の手順を実行します。

    [Image: スクリーンショットは、説明されている値を入力できる [全般設定] セクションを示しています。]

    a. **[Enabled]** を選択します。

    b。 **2.0**として **バージョン** を選択します。

    c. **[Skip conditions](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/条件をスキップする)** で **[いいえ]** を選択します。

    d. **[SAML Token POST param name (SAML トークン POST パラメーター名)]** ボックスに、前述の SAML コンシューマー URL に対する POST 要求パラメーターの名前を入力します。ここには、確認と認証の対象である SAML アサーションが含まれます (**SAMLResponse** など)。

    e. 以下の[**名識別子形式** テキストボックス]に、SAML アサーション内でユーザー識別子 (メールアドレス) がどこにあるかを示す値を入力します。たとえば、[`urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress`] です。

    f. [ **プロバイダーの場所の識別** ] ボックスに、Azure portal ログイン画面からアップロードしたアイコンを選択した場合に、ユーザーの送信先を示す値を入力します。

    g. [**サインアウト URL** テキストボックスに、以前コピーした **ログアウト URL** の値を貼り付けます。

    h. [ **指紋の管理**] を選択し、ダウンロードした証明書の指紋をアップロードします。
5. [ **ユーザー設定] を**選択し、次の手順を実行します。

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー設定] セクションを示しています。]

    a. **[First Name Identifier Format](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名識別子形式)** テキストボックスに、SAML アサーション内のユーザーの名の場所を示す値を入力します (例: `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`)。

    b。 **[Last Name Identifier Format](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓識別子形式)** テキストボックスに、SAML アサーション内のユーザーの姓の場所を示す値を入力します (例: `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname`)。

#### LearnUpon テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを LearnUpon に作成します。 LearnUpon では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 LearnUpon にユーザーがまだ存在していない場合は、認証後に新しく作成されます。 ユーザーを手動で作成する必要がある場合は、[LearnUpon サポート チーム](https://www.learnupon.com/contact/)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した LearnUpon に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [LearnUpon] タイルを選択すると、SSO を設定した LearnUpon に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lecorpio-tutorial"} -->
## Microsoft Entra ID でシングルサインオン用にLecorpioを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lecorpio-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Lecorpio の間のシングル サインオンを構成する方法について説明します。

この記事では、Lecorpio と Microsoft Entra ID を統合する方法について説明します。 Lecorpio を Microsoft Entra ID と統合すると、次の利点があります。

- Lecorpio にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが Microsoft Entra アカウントで Lecorpio に自動的にサインイン (シングル サインオン) できるようにします。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Lecorpio でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Lecorpio では、 **SP** Initiated SSO がサポートされます

### ギャラリーからの Lecorpio の追加

Microsoft Entra ID への Lecorpio の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Lecorpio を追加する必要があります。

**ギャラリーから Lecorpio を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **Lecorpio」**と入力し、結果パネルで **Lecorpio** を選択し、[ **追加** ] ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Lecorpio]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Lecorpio で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Lecorpio の関連ユーザー間にリンク関係を確立する必要があります。

Lecorpio に対する Microsoft Entra シングル サインオンを構成およびテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Lecorpio シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Lecorpio のテスト ユーザーの作成** - Lecorpio で Britta Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクされるようにします。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Lecorpio に対する Microsoft Entra シングル サインオンを構成するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Lecorpio** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: Lecorpio のドメインおよびURLのシングルサインオン情報]

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<instance name>.lecorpio.com/<customer name>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<instance name>.lecorpio.com/<customer name>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [Lecorpio クライアント サポート チーム](mailto:info@lecorpio.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Lecorpio のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Lecorpio のシングル サインオンの構成

**Lecorpio** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Lecorpio サポート チーム](mailto:info@lecorpio.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Lecorpio のテスト ユーザーの作成

このセクションでは、Lecorpio で "Britta Simon" というユーザーを作成します。 [Lecorpio サポート チーム](mailto:info@lecorpio.com)と協力して、Lecorpio プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Lecorpio] タイルを選択すると、SSO を設定した Lecorpio に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ledgy-tutorial"} -->
## Microsoft Entra ID で Ledgy for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ledgy-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Ledgy の間のシングル サインオンを構成する方法について説明します。

この記事では、Ledgy を Microsoft Entra ID と統合する方法について説明します。 株式を自動化します。 世界中の従業員に株とオプションを付与し、すべての主要システムに株式を統合し、チームが持ち株について理解できるように支援します。 Ledgy を Microsoft Entra ID と統合すると、次のことが可能になります。

- Ledgy にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Ledgy に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

Ledgy に対する Microsoft Entra シングル サインオンをテスト環境で構成およびテストします。 Ledgy では、 **SP** と **IDP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングの両方がサポートされます。

### [前提条件]

Microsoft Entra ID を Ledgy と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Ledgy でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Ledgy アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Ledgy を追加する

Microsoft Entra アプリケーション ギャラリーから Ledgy を追加して、Ledgy とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Ledgy**&gt;**シングルサインオン**にアクセスしてください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://app.ledgy.com/auth/saml/<orgSlug>/metadata.xml`

    b。 [応答 URL] ボックスに、`https://app.ledgy.com/auth/saml/<orgSlug>/acs` のパターンを使用して URL を入力します。
6. SP Initiated モードでアプリケーションを構成する場合は、続けて次の手順を実行します。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://app.ledgy.com/login`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Ledgy クライアント サポート チーム](mailto:support@ledgy.com) に問い合わせてください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
7. Ledgy アプリケーションでは特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. その他に、Ledgy アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらを以下に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ID | user.userprincipalname |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### Ledgy SSO を構成する

**Ledgy** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Ledgy サポート チーム](mailto:support@ledgy.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Ledgy テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Ledgy に作成します。 Ledgy では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Ledgy にユーザーがまだ存在していない場合、一般的には認証後に新しいものが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Ledgy のサインオン URL にリダイレクトされます。
- Ledgy のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Ledgy に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Ledgy] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Ledgy に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/legalforce-tutorial"} -->
## Microsoft Entra ID で LegalForce for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/legalforce-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LegalForce の間のシングル サインオンを構成する方法について説明します。

この記事では、LegalForce を Microsoft Entra ID と統合する方法について説明します。 LegalForce は、自然言語処理などのテクノロジを使用して、それぞれの契約の種類のチェックリストと契約を自動的にチェックし、用語の欠落や句の超過を即座に提示し、欠落を防ぎます。 契約作業の品質と効率を同時に向上させる機能を備えています。 LegalForce を Microsoft Entra ID と統合すると、次のことが可能になります。

- LegalForce にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで LegalForce に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

テスト環境で LegalForce 用の Microsoft Entra シングル サインオンを構成してテストします。 LegalForce では、**SP** によって開始されたシングル サインオンのみがサポートされます。

### 前提条件

Microsoft Entra ID を LegalForce と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な LegalForce のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから LegalForce アプリケーションを追加する必要があります。 アプリケーションに割り当て、シングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから LegalForce を追加する

Microsoft Entra アプリケーション ギャラリーから LegalForce を追加して、LegalForce とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、[クイック スタート: ギャラリーからのアプリケーションの追加](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)に関する記事を参照してください。

#### Microsoft Entra テスト ユーザーを作成して割り当てる

[ユーザー アカウントの作成と割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができます。 ウィザードは、シングル サインオン構成ウィンドウへのリンクも提供します。 [Microsoft 365 ウィザードの詳細をご覧ください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO を構成する

Microsoft Entra のシングル サインオンを有効にするには、以下の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**LegalForce**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML でシングル サインオンをセットアップします]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`urn:auth0:legalforce:saml-<ORG.CUSTOMERID>` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://auth.legalforce-cloud.com/login/callback?connection=saml-$<ORG.CUSTOMERID>` のパターンを使用して URL を入力します。

    c. **[サインオン URL]** テキストボックスに、URL として「`https://app.legalforce-cloud.com/`」と入力します。

    Note

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[LegalForce サポート チーム](mailto:support@legalforce.co.jp)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でのシングル サインオンの設定]** ページの **[SAML 署名証明書]** セクションで、**[...]** コンテキスト メニューを選択し、**[PEM 証明書のダウンロード]** を選択します。

    [Image: 証明書のダウンロード リンクのスクリーンショット]
7. **[LegalForce のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーすることを示すスクリーンショット。]

### LegalForce SSO の構成

**LegalForce** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** とアプリケーションの構成からコピーした適切な URL を [LegalForce サポート チーム](mailto:support@legalforce.co.jp)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### LegalForce テスト ユーザーの作成

このセクションでは、LegalForce で Britta Simon というユーザーを作成します。 [LegalForce サポート チーム](mailto:support@legalforce.co.jp)と協力して、LegalForce プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる LegalForce サインオン URL にリダイレクトされます。
- LegalForce のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [LegalForce] タイルを選択すると、このオプションは LegalForce のサインオン URL にリダイレクトされます。 詳細については、[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lensesio-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Lenses.io を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lensesio-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-04
- Summary: この記事では、Microsoft Entra ID と Lenses.io の間でシングル サインオンを構成する方法について説明します。

この記事では、 [Lenses.io](https://lenses.io/) DataOps ポータルを Microsoft Entra ID と統合する方法について説明します。 Microsoft Entra ID と Lenses.io を統合すると、次のことができます。

- Lenses.io Portal にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Lenses に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

Lenses.io は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- Lenses ポータルのインスタンス。 さまざまな [デプロイ オプション](https://lenses.io/product/deployment/)から選択できます。
- シングル サインオン (SSO) をサポートする Lenses.io [ライセンス](https://lenses.io/product/pricing/) 。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Lenses.io では、サービスプロバイダー (SP) 開始のSSOがサポートされます。

### ギャラリーからの Lenses.io の追加

Microsoft Entra ID への Lenses.io の統合を構成するには、マネージド SaaS アプリの一覧に Lenses.io を追加します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックス**に「Lenses.io**」と入力します。
4. 結果ウィンドウで **Lenses.io** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Lenses.io 用に Microsoft Entra SSO を構成してテストする

*B.Simon* というテスト ユーザーを作成して、Lenses.io ポータルで Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Lenses.io の関連ユーザーとの間にリンク関係を確立する必要があります。

次の手順を実行します:

1. ユーザーがこの機能を使用できるように Microsoft Entra SSO を構成します。
    1. Microsoft Entra のテスト ユーザーとグループを作成し、B.Simon で Microsoft Entra SSO をテストします。
    2. B.Simon が Microsoft Entra SSO を使用できるように、Microsoft Entra テスト ユーザーを割り当てます。
2. Lenses.io SSO を構成して、アプリケーション側で SSO 設定を構成します。
    1. Lenses.io テスト グループのアクセス許可を作成 して、Lenses.io (承認) で B.Simon がアクセスできる内容を制御します。
3. SSO をテスト して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Azure portal で、次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Lenses.io** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、**シングル サインオン**を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのアイコンを示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **識別子 (エンティティ ID):**次のパターンを持つ URL を入力します: `https://<CUSTOMER_LENSES_BASE_URL>`。 たとえば `https://lenses.my.company.com` です。

    b。 **応答 URL**: 次のパターンを持つ URL を入力します: `https://<CUSTOMER_LENSES_BASE_URL>/api/v2/auth/saml/callback?client_name=SAML2Client`。 たとえば `https://lenses.my.company.com/api/v2/auth/saml/callback?client_name=SAML2Client` です。

    c. **サインオン URL**: `https://<CUSTOMER_LENSES_BASE_URL>`というパターンの URL を入力します。 たとえば `https://lenses.my.company.com` です。

    注

    これらの値は実際の値ではありません。 これらは、お使いの Lenses ポータル インスタンスのベース URL を使用した実際の識別子、応答 URL、サインオン URL で更新します。 詳細については、Lenses.io SSO のドキュメントを参照してください。
6. [ **SAML でのシングル サインオンの設定** ] ページで、[ **SAML 署名証明書** ] セクションに移動します。 **フェデレーション メタデータ XML** を検索し、[**ダウンロード**] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Lenses.io の設定** ] セクションで、ダウンロードした XML ファイルを使用して、Azure SSO に対して Lenses を構成します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Lenses.io の SSO の構成

**Lenses.io** ポータルで SSO を構成するには、ダウンロードした**フェデレーション メタデータ XML を** Lenses インスタンスにインストールし、SSO を有効にするように Lenses を構成します。

#### Lenses.io テスト グループのアクセス許可を作成する

1. レンズでグループを作成するには、**LensesUsers** グループの**オブジェクト ID を**使用します。 これは、ユーザー 作成セクションでコピーした ID です。
2. B.Simon に必要なアクセス許可を割り当てます。

詳細については、「Azure - レンズ グループマッピング」を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Lenses.io サインオン URL にリダイレクトされます。
- Lenses.io のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Lenses.io] タイルを選択すると、このオプションは Lenses.io サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lessonly-tutorial"} -->
## Microsoft Entra ID で Lessonly for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lessonly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Lessonly の間のシングル サインオンを構成する方法について説明します。

この記事では、Lessonly と Microsoft Entra ID を統合する方法について説明します。 Lessonly を Microsoft Entra ID と統合すると、次のことが可能になります。

- Lessonly にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Lessonly に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Lessonly でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Lessonly では、**SP** によって開始される SSO がサポートされます。
- Lessonly では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Lessonly の追加

Microsoft Entra ID への Lessonly の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Lessonly を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Lessonly**」と入力します。
4. 結果のパネルから **[Lessonly]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Lessonly に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Microsoft Entra SSO と Lessonly を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Lessonly での関連ユーザーとの間にリンク関係を確立する必要があります。

Lessonly に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Lessonly の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Lessonly のテスト ユーザーの作成** - Lessonly で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Lessonly**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.lessonly.com/auth/saml`

    注

    一般名を参照するときは、この **companyname** を実際の名前に置き換える必要があります。

    b。 **[応答 URL (Assertion Customer Service URL)]** ボックスに、次のパターンを使用して URL を入力します: `https://<companyname>.lessonly.com/auth/saml/callback`

    c. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.lessonly.com/auth/saml/metadata`

    注

    これらの値は実際の値ではありません。 これらの値を実際のサインオン URL、応答 URL、識別子で更新してください。 これらの値を取得するには、[Lessonly.com クライアント サポート チーム](mailto:support@lessonly.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Lessonly アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Lessonly アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:2.5.4.42 | User.givenname |
    | urn:oid:2.5.4.4 | ユーザーの名字 |
    | urn:oid:0.9.2342.19200300.100.1.3 | ユーザーのメールアドレス |
    | urn:oid:1.3.6.1.4.1.5923.1.1.1.10 | user.objectid (ユーザーのオブジェクトID) |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Lessonly のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Lessonly の SSO の構成

**Lessonly** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [Lessonly サポート チーム](mailto:support@lessonly.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Lessonly のテスト ユーザーの作成

このセクションの目的は、Lessonly.com で B.Simon というユーザーを作成することです。 Lessonly.com では、Just-In-Time プロビジョニングがサポートされています。この設定は、既定で有効になっています。

このセクションにはアクション項目はありません。 ユーザーがまだ存在しない場合は、Lessonly.com にアクセスしようとすると、新しいユーザーが作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Lessonly.com のサポート チーム](mailto:support@lessonly.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Lessonly のサインオン URL にリダイレクトされます。
- Lessonly のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Lessonly] タイルを選択すると、このオプションは Lessonly のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lexion-tutorial"} -->
## Microsoft Entra ID で Lexion for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lexion-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Lexion 間のシングル サインオンを構成する方法について説明します。

この記事では、Lexion と Microsoft Entra ID を統合する方法について説明します。 Lexion を Microsoft Entra ID と統合すると、次のことが可能になります。

- Lexion にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Lexion に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Lexion のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Lexion では、**SP Initiated と IDP Initiated** SSO がサポートされます

### ギャラリーからの Lexion の追加

Microsoft Entra ID への Lexion の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Lexion を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Lexion**」と入力します。
4. 結果のパネルから **[Lexion]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Lexion に対する Microsoft Entra SSO を構成および検証する

**B.Simon** というテスト ユーザーを使用して、Lexion に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するには、Microsoft Entra ユーザーと Lexion の関連ユーザー間にリンク関係を確立する必要があります。

Lexion に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Lexion の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Lexion テスト ユーザーの作成** - Microsoft Entra にリンクされた Lexion で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Lexion**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.lexion.ai/login`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[Lexion クライアント サポート チーム](mailto:support@lexion.ai)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **保存** を選択します。
8. Lexion アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Lexion アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[Lexion のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Lexion SSO の構成

**Lexion** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Lexion サポート チーム](mailto:support@lexion.ai)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Lexion テスト ユーザーの作成

このセクションでは、Lexion で Britta Simon というユーザーを作成します。 [Lexion サポート チーム](mailto:support@lexion.ai)と連携して、Lexion プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Lexion サインオン URL にリダイレクトされます。
- Lexion のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Lexion に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [Lexion] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Lexion に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lexmark-cloud-services-oidc-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Lexmark Cloud Services (OIDC) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lexmark-cloud-services-oidc-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-10-03
- Summary: Microsoft Entra と Lexmark Cloud Services (OIDC) の間でシングル サインオンを構成する方法について説明します。

この記事では、Lexmark Cloud Services (OIDC) と Microsoft Entra ID を統合する方法について説明します。 Lexmark Cloud Services (OIDC) と Microsoft Entra ID を統合すると、次のことができます。

Microsoft Entra ID を使用して、Lexmark Cloud Services (OIDC) にアクセスできるユーザーを制御します。 ユーザーが自分の Microsoft Entra アカウントを使用して Lexmark Cloud Services (OIDC) に自動的にサインインできるようにします。 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Lexmark Cloud Services (OIDC) でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから Lexmark Cloud Services (OIDC) を追加する

Microsoft Entra ID への Lexmark Cloud Services (OIDC) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Lexmark Cloud Services (OIDC) を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ **Microsoft Entra アプリ ギャラリーの参照** ] セクションで、検索ボックスに **「Lexmark Cloud Services (OIDC)」** と入力します。
4. 結果パネルで **Lexmark Cloud Services (OIDC)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Lexmark Cloud Services (OIDC)]**&gt;**[シングル サインオン]** の順に選択します。
3. 次の手順を実行します。

    1. **[アプリケーションに移動]**を選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID をコピーします**。 これは、後で Lexmark Cloud Services (OIDC) SSO 構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
    3. [ **エンドポイント** ] タブで、 **OpenID Connect メタデータ ドキュメント** リンクをコピーします。 これは、後で Lexmark Cloud Services (OIDC) SSO 構成で使用します。

        [Image: タブにエンドポイントが表示されているスクリーンショット。]
4. 左側のメニューの **[証明書とシークレット** ] に移動し、次の手順を実行します。

    1. [ **クライアント シークレット** ] タブに移動し、[ **+新しいクライアント シークレット**] を選択します。
    2. テキストボックスに有効な **[説明]** を入力し、要件に応じてドロップダウンから **[有効期限]** 日数を選択し **[追加]** を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、**[値]** が生成されます。 値をコピーします。 これは、後で Lexmark Cloud Services (OIDC) SSO 構成で使用します。

        [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部で **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **を選択して**を作成します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に Lexmark Cloud Services (OIDC) へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Lexmark Cloud Services (OIDC)** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Lexmark Cloud Services (OIDC) SSO の構成

このセクションの手順を完了するには、Lexmark Cloud Services で組織の組織管理者ロールがあることを確認します。 OIDC フェデレーションを使用した Microsoft Entra ID の構成に関する [Lexmark ドキュメント](https://support.lexmark.com/en_us/manuals-guides/online/Lexmark-Cloud-Platform/configuring-azure-ad-federation-for-oidc-overview-.html) も確認してください

### OIDC に対する SSO のために組織を構成する

1. Lexmark Cloud Services に組織管理者としてログインします。
2. Lexmark Cloud Services ダッシュボードまたは画面右側のナビゲーション メニューから、[ **アカウント管理**] を選択します。 [Image: [アカウント管理] の選択のスクリーンショット。]
3. 必要に応じて、組織を選択し、[ **次へ**] を選択します。 [Image: 組織の選択のスクリーンショット。]
4. [組織] セクションで、[ **認証プロバイダー**] を選択します。 [Image: [認証プロバイダー] の選択のスクリーンショット。]
5. [**認証プロバイダー**] ウィンドウで **[構成] を**選択します。 [Image: 認証プロバイダーの構成のスクリーンショット。]
6. [ **認証プロバイダーの種類** ] メニューの **[OIDC**] を選択します。
7. Microsoft Entra ID からコピーした必要な情報を入力します。

    - クライアント ID (アプリケーション クライアント ID)
    - クライアント シークレット (クライアント シークレット値)
    - 既知の URL (OpenID Connect メタデータ ドキュメント URL)

注

[ドメイン] フィールドを使用すると、ユーザーのログイン後に Lexmark Cloud Services で新しいユーザー アカウントを自動的に確立できます。 各組織のドメインを一覧表示する必要はありません。 ドメインが設定されていない場合は、ログインする前に新しいユーザーを手動で組織に追加する必要があります。

1. [ **認証プロバイダーの構成] を選択します**。 [Image: 認証プロバイダーの構成のスクリーンショット。]

注

認証の構成が完了すると、構成の状態に関する電子メールが届きます。 構成エラーが発生した場合は、Lexmark の担当者にお問い合わせください。

1. 米国およびヨーロッパ地域の依存パーティのリダイレクト URI。 (Microsoft Entra 認証構成で使用されます)
    - 米国リージョン: `https://lexmarkb2c.b2clogin.com/lexmarkb2c.onmicrosoft.com/oauth2/authresp`
    - EU リージョン: `https://lexmarkb2ceu.b2clogin.com/lexmarkb2ceu.onmicrosoft.com/oauth2/authresp`

注

[Entra Authentication Configuration]\(Entra 認証の構成\) の下の [暗黙的な許可とハイブリッド フロー] で ID トークンを選択する

#### SSO 構成をテストする

SSO が正常に設定されていることをテストする方法については、[フェデレーションのテスト] (https://support.lexmark.com/en_us/manuals-guides/online/Lexmark-Cloud-Platform/testing-a-federation-v58742261.html?toc=2.5.4.10) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lexmark-cloud-services-provisioning-oidc-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Lexmark Cloud Services (OIDC) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lexmark-cloud-services-provisioning-oidc-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID から Lexmark Cloud Services (OIDC) にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Lexmark Cloud Services (OIDC) と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーを Lexmark Cloud Services (OIDC) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Lexmark Cloud Services (OIDC) でユーザーを作成する
- アクセスが不要になったときに Lexmark Cloud Services (OIDC) のユーザーを無効にする
- Microsoft Entra ID と Lexmark Cloud Services (OIDC) の間でユーザー属性の同期を維持します。Lexmark Cloud Services (OIDC) への [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

注

現在、Lexmark Cloud Services (OIDC) アプリケーションではユーザー プロビジョニングのみがサポートされています。 グループ のプロビジョニングはサポートされておらず、今後のリリースに向けて計画されています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 組織管理者ロールを持つ Lexmark Cloud Services の OIDC フェデレーション組織。
- ユーザー プロビジョニングに関する [lexmark ドキュメント](https://support.lexmark.com/en_us/manuals-guides/online/Lexmark-Cloud-Platform/overview-v54808648.html?toc=2.5.0) を確認します。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Adobe Identity Management (OIDC) の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Lexmark Cloud Services (OIDC) を構成する

1. Lexmark Cloud Services にログインします。
2. ダッシュボード カードまたはナビゲーション ワッフル メニューから、[ **アカウント管理**] を選択します。

    [Image: OIDC アカウント管理を示すスクリーンショット。]
3. 必要に応じて、組織を選択し、[ **次へ**] を選択します。

    [Image: [組織] メニューを示すスクリーンショット。]
4. [組織が Lexmark Cloud Services (OIDC) アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lexmark-cloud-services-oidc-tutorial)に対する SSO 用に構成されていることを確認します。
5. [ **組織の選択** ] ウィンドウで、[ **ユーザー プロビジョニング**] を選択します。

    [Image: [ユーザー プロビジョニング] メニューを示すスクリーンショット。]
6. [ **ユーザー プロビジョニングを有効にする] を選択**します。

    [Image: [ユーザー プロビジョニングの有効化] を示すスクリーンショット。]
7. プロビジョニングの詳細は、有効にすると自動的に入力されます。

    [Image: スクリーンショットには、[プロビジョニングの詳細が自動的に設定されます] が示されています。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Lexmark Cloud Services (OIDC) を追加する

Microsoft Entra アプリケーション ギャラリーから Lexmark Cloud Services (OIDC) を追加して、Lexmark Cloud Services (OIDC) へのプロビジョニングの管理を開始します。 SSO 用に Lexmark Cloud Services (OIDC) を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: Lexmark Cloud Services (OIDC) への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーまたはグループの割り当てに基づいて Lexmark Cloud Services (OIDC) でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します

#### Microsoft Entra ID で Lexmark Cloud Services (OIDC) の自動ユーザー プロビジョニングを構成するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくともアプリ所有者または[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示すスクリーンショット。]
3. アプリケーションの一覧で **Lexmark Cloud Services (OIDC)** を選択します。

    [Image: スクリーンショットは、アプリケーションの一覧の Lexmark Cloud Services (OIDC) リンクを示しています。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブを示すスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [テナント URL] フィールドに、Lexmark Cloud Services (OIDC) テナント URL を入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Lexmark Cloud Services (OIDC) に接続できることを確認します。 接続に失敗した場合は、Lexmark Cloud Services (OIDC) アカウントに必要なアクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [接続] タブで、Lexmark Cloud Services (OIDC) プロビジョニング ページからコピーしたテナント URL、トークン エンドポイント、クライアント識別子、クライアント シークレットを貼り付け、[ **テスト接続**] を選択します。

    [Image: 接続テスト接続のスクリーンショット。]
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. 鉛筆を選択してプロパティを編集します。

    1. 通知メールを有効にし、検疫メールを受信する電子メールを提供します。
    2. 誤削除防止を有効にします。
    3. **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
12. [ **属性マッピング** ] セクションで、Microsoft Entra ID から Lexmark Cloud Services (OIDC) に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Lexmark Cloud Services (OIDC) のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Lexmark Cloud Services (OIDC) API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Lexmark Cloud Services (OIDC) で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | 名前.名 | 糸 |  |  |
    | 名前.姓 | 糸 |  |  |
    | ディスプレイ名 | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:コストセンター | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |  |

    注

    バッジやピンなどの機密性の高い属性は、マッピングではサポートされていません。

    注

    カスタム属性を作成してマップするには、 [この記事](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes)の手順に従ってください。
13. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lexmark-cloud-services-provisioning-saml-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Lexmark Cloud Services (SAML) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lexmark-cloud-services-provisioning-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID から Lexmark Cloud Services (SAML) にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Lexmark Cloud Services (SAML) と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーを Lexmark Cloud Services (SAML) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Lexmark Cloud Services (SAML) でユーザーを作成する
- アクセスが不要になった場合に Lexmark Cloud Services (SAML) のユーザーを無効にする
- Microsoft Entra ID と Lexmark Cloud Services (SAML) の間でユーザー属性の同期を維持します。Lexmark Cloud Services (SAML) への [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

注

現在、Lexmark Cloud Services (SAML) アプリケーションではユーザー プロビジョニングのみがサポートされています。 グループ のプロビジョニングはサポートされておらず、今後のリリースに向けて計画されています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Lexmark Cloud Services の SAML フェデレーション組織内で「組織管理者」の役割を持つ場合。
- ユーザー プロビジョニングに関する [lexmark ドキュメント](https://support.lexmark.com/en_us/manuals-guides/online/Lexmark-Cloud-Platform/overview-v54808648.html?toc=2.5.0) を確認します。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Adobe Identity Management (SAML) の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Lexmark Cloud Services (SAML) を構成する

1. Lexmark Cloud Services にログインします。
2. ダッシュボード カードまたはナビゲーション ワッフル メニューから、[ **アカウント管理**] を選択します。

    [Image: [アカウント管理] を示すスクリーンショット。]
3. 必要に応じて、組織を選択し、[ **次へ**] を選択します。

    [Image: [組織] メニューを示すスクリーンショット。]
4. [組織が Lexmark Cloud Services (SAML) アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lexmark-cloud-services-tutorial)に対する SSO 用に構成されていることを確認します。
5. [ **組織の選択** ] ウィンドウで、[ **ユーザー プロビジョニング**] を選択します。

    [Image: [ユーザー プロビジョニング] メニューを示すスクリーンショット。]
6. [ **ユーザー プロビジョニングを有効にする] を選択**します。

    [Image: [ユーザー プロビジョニングの有効化] を示すスクリーンショット。]
7. プロビジョニングの詳細は、有効にすると自動的に入力されます。

    [Image: [User Provisioning Details](ユーザー プロビジョニングの詳細) を示すスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Lexmark Cloud Services (SAML) を追加する

Microsoft Entra アプリケーション ギャラリーから Lexmark Cloud Services (SAML) を追加して、Lexmark Cloud Services (SAML) へのプロビジョニングの管理を開始します。 SSO 用に Lexmark Cloud Services (SAML) を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: Lexmark Cloud Services (SAML) への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーまたはグループの割り当てに基づいて Lexmark Cloud Services (SAML) のユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Lexmark Cloud Services (SAML) の自動ユーザー プロビジョニングを構成するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくともアプリ所有者または[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示すスクリーンショット。]
3. アプリケーションの一覧で **Lexmark Cloud Services (SAML)** を選択します。

    [Image: スクリーンショットは、アプリケーションの一覧の Lexmark Cloud Services (SAML) リンクを示しています。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブを示すスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [テナント URL] フィールドに、Lexmark Cloud Services (SAML) テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Lexmark Cloud Services (SAML) に接続できることを確認します。 接続に失敗した場合は、Lexmark Cloud Services (SAML) アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。

    1. 通知メールを有効にし、検疫メールを受信する電子メールを提供します。
    2. 誤削除防止を有効にします。
    3. **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. [ **属性マッピング** ] セクションで、Microsoft Entra ID から Lexmark Cloud Services (SAML) に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Lexmark Cloud Services (SAML) のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Lexmark Cloud Services (SAML) API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Lexmark Cloud Services (SAML) で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | 名前.名 | 糸 |  |  |
    | 名前.姓 | 糸 |  |  |
    | ディスプレイ名 | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:コストセンター | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |  |
12. グループを選択 **します**。
13. [属性マッピング] セクションで、Microsoft Entra ID から Lexmark Cloud Services (SAML) に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Lexmark Cloud Services (SAML) のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。
14. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lexmark-cloud-services-tutorial"} -->
## SSO 用 Lexmark Cloud Services (SAML) と Microsoft Entra ID の統合 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lexmark-cloud-services-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-08-06
- Summary: Microsoft Entra ID と Lexmark Cloud Services (SAML) の間でシングル サインオンを構成する方法について説明します。

この記事では、Lexmark Cloud Services (SAML) と Microsoft Entra ID を統合する方法について説明します。 Lexmark Cloud Services (SAML) と Microsoft Entra ID を統合すると、次のことができます。

- Lexmark Cloud Services (SAML) にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Lexmark Cloud Services (SAML) に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Lexmark Cloud Services (SAML) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

### ギャラリーから Lexmark Cloud Services (SAML) を追加する

Microsoft Entra ID への Lexmark Cloud Services (SAML) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Lexmark Cloud Services (SAML) を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**新規アプリケーション**を参照します。
3. [ **Microsoft Entra アプリ ギャラリーの参照** ] セクションで、検索ボックスに **「Lexmark Cloud Services (SAML)」** と入力します。
4. 結果パネルから **Lexmark Cloud Services (SAML)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Lexmark Cloud Services (SAML)**&gt;**シングルサインオン**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://lexmarkb2c.b2clogin.com/LexmarkB2C.onmicrosoft.com/B2C_1A_TrustFrameworkBase_ciam` |
    | `https://lexmarkb2ceu.b2clogin.com/LexmarkB2CEU.onmicrosoft.com/B2C_1A_TrustFrameworkBase_ciam` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://lexmarkb2c.b2clogin.com/LexmarkB2C.onmicrosoft.com/B2C_1A_TrustFrameworkBase_ciam/samlp/sso/assertionconsumer` |
    | `https://lexmarkb2ceu.b2clogin.com/LexmarkB2CEU.onmicrosoft.com/B2C_1A_TrustFrameworkBase_ciam/samlp/sso/assertionconsumer` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、Lexmark SSO クライアント サポート チームに問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 証明書** ] セクションで、コピー ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Lexmark Cloud Services (SAML) SSO の構成

**Lexmark Cloud Services (SAML)** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を** Lexmark Cloud Services (SAML) サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。 SAML フェデレーションを使用した Microsoft Entra ID の構成に関する [Lexmark ドキュメント](https://support.lexmark.com/en_us/manuals-guides/online/Lexmark-Cloud-Platform/configuring-microsoft-entra-id-federation-for-saml.html) も確認してください

### SAML に対する SSO のために組織を構成する

1. Lexmark Cloud Services にログインします。 [Image: lexmark ログイン ページのスクリーンショット。]
2. 画面の右側にあるナビゲーション メニューで、[ **アカウント管理**] を選択します。 [Image: アカウント管理ページのスクリーンショット。]
3. 必要に応じて、組織を選択し、[ **次へ**] を選択します。 [Image: 組織のスクリーンショット。]
4. [組織] メニューの [ **認証プロバイダー**] を選択します。 [Image: 認証プロバイダーのスクリーンショット。]
5. **認証プロバイダーで「構成」を選択してください**。 [Image: 認証プロバイダーの構成のスクリーンショット。]
6. **[認証プロバイダーの種類]** メニューから [SAML] を選択**します**。 [Image: 認証プロバイダーの種類のスクリーンショット。]

    注

    [ドメイン] フィールドでは、ユーザーのログイン後に Lexmark Cloud Services で新しいユーザー アカウントを確立できます。 各組織ドメインを一覧表示する必要はありません。 ドメインが設定されていない場合は、ログインする前に新しいユーザーを手動で組織に追加する必要があります。
7. **[SAML 認証プロバイダー**] セクション**で、[メタデータ URL** あり] または [**メタデータ URL なし**] を選択します。

    注

    より短いプロセスでは、[メタデータ URL を使用する] を選択することをお勧めします。

#### メタデータ URL を使用する

メタデータ URL を使用して **SAML 認証プロバイダー** セクションを構成する場合は、次の手順を実行します。

1. **[SAML Authentication Provider]\(SAML 認証プロバイダー**\) セクション**で、[With Metadata URL]\(メタデータ URL を使用\**) を選択します。 [Image: メタデータ URL オプションを含む認証プロバイダーのスクリーンショット。]
2. [ **SAML メタデータ URL (必須)]** フィールドに、以前にコピーして保持したアプリのフェデレーション メタデータ URL を貼り付けます。

    注

    アプリのフェデレーション メタデータ URL の詳細については、「 [証明書のダウンロードと URL のコピー」を参照してください](https://support.lexmark.com/en_us/manuals-guides/online/Lexmark-Cloud-Platform/downloading-certificates-and-copying-urls-v5921516.html)。
3. [ **認証プロバイダーの構成] を選択します**。

#### メタデータ URL なし

メタデータ URL なしで SAML 認証プロバイダー セクションを構成する場合は、次の手順を実行します。

1. **[SAML 認証プロバイダー**] セクションで、[**メタデータ URL なし**] を選択します。 [Image: メタデータ URL のないシングル サインオン設定のスクリーンショット。]
2. [ **ID プロバイダー エンティティ ID (必須)]** フィールドに、場所に応じて、次のいずれかを入力します。

    - EU の場合: **`https://lexmarkb2ceu.b2clogin.com/LexmarkB2CEU.onmicrosoft.com/B2C_1A_TrustFrameworkBase_ciam`**
    - アメリカ合衆国の場合： **`https://lexmarkb2c.b2clogin.com/LexmarkB2C.onmicrosoft.com/B2C_1A_TrustFrameworkBase_ciam`**

    注

    URL は、Microsoft Entra ID に入力された URL と同じである必要があります。
3. Microsoft Entra ID からコピーした必要な情報を入力します。

    - SSO ターゲット URL (必須)
    - SSO ログアウト URL (必須)
    - 証明書 (必須)

    注

    証明書のヘッダーとフッターを必ず含めます。
4. [ **認証プロバイダーの構成] を選択します**。

    注

    認証の構成が完了すると、構成の状態に関する電子メールが届きます。 構成エラーが発生した場合は、Lexmark の担当者にお問い合わせください。

注

Lexmark Cloud Services ポータルを終了したり、ポータルのタイムアウトを許可したりしていないことを確認します。次に SAML 接続をテストします。テスト中に検出された問題を修正するためにログインできない場合があります。 フェデレーションのテストの詳細については、「フェデレーションの [テスト」を](https://support.lexmark.com/en_us/manuals-guides/online/Lexmark-Cloud-Platform/testing-a-federation-v59215222.html)参照してください。

#### Lexmark Cloud Services (SAML) テスト ユーザーの作成

このセクションでは、Lexmark Cloud Services (SAML) で B.Simon というユーザーを作成します。 Lexmark Cloud Services (SAML) サポート チームと協力して、Lexmark Cloud Services (SAML) プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、 [マイ アプリ](https://myapps.microsoft.com) ポータルを使用して Microsoft Entra のシングル サインオン構成をテストします。

マイ アプリで [Lexmark Cloud Services (SAML)] タイルを選択すると、SSO を設定した Lexmark Cloud Services (SAML) に自動的にサインインします。 マイ アプリ ポータルの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lexonis-talentscape-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Lexonis TalentScape を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lexonis-talentscape-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: Microsoft Entra IDから Lexonis TalentScape にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Lexonis TalentScape と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーが [Lexonis TalentScape](https://www.lexonis.com) に自動的にプロビジョニングおよび解除されます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされる機能

- Lexonis TalentScape でユーザーを作成します。
- アクセスが不要になったら、Lexonis TalentScape のユーザーを削除します。
- Microsoft Entra IDと Lexonis TalentScape の間でユーザー属性の同期を維持します。
- Lexonis TalentScape に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lexonis-talentscape-tutorial)します (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者アクセス許可がある Lexonis TalentScape のユーザー アカウント。

### 手順 1: プロビジョニング展開を計画する

- [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- Microsoft Entra IDとLexonis TalentScapeの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Lexonis TalentScape を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように Lexonis TalentScape を構成するには、Lexonis TalentScape サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Lexonis TalentScape を追加する

Microsoft Entra アプリケーション ギャラリーから Lexonis TalentScape を追加して、Lexonis TalentScape へのプロビジョニングの管理を開始します。 SSO 用に Lexonis TalentScape を既にセットアップしてある場合は、同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Lexonis TalentScape への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて Lexonis TalentScape でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Lexonis TalentScape の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧 **で [Lexonis TalentScape**] を選択します。

    [Image: アプリケーションの一覧の Lexonis TalentScape リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Lexonis TalentScape テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Lexonis TalentScape に接続できることを確認します。 接続に失敗した場合は、Lexonis TalentScape アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Lexonis TalentScape に同期されるユーザー属性を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Lexonis TalentScape のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Lexonis TalentScape API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Lexonis TalentScape で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | addresses[type eq "work"].formatted | 糸 |  |  |
    | addresses[type eq "work"].streetAddress | 糸 |  |  |
    | addresses[type eq "work"].locality | 糸 |  |  |
    | addresses[type eq "work"].region | 糸 |  |  |
    | addresses[type eq "work"].postalCode | 糸 |  |  |
    | addresses[type eq "work"].country | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | phoneNumbers[type eq "fax"].value | 糸 |  |  |
    | externalId | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |  |
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから Lexonis TalentScape に同期されるグループ属性を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Lexonis TalentScape のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Lexonis TalentScape で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  |  |
    | members | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lexonis-talentscape-tutorial"} -->
## Microsoft Entra ID でシングルサインオンのためにLexonis TalentScapeを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lexonis-talentscape-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-04
- Summary: Microsoft Entra ID と Lexonis TalentScape の間のシングル サインオンを構成する方法について説明します。

この記事では、Lexonis TalentScape と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Lexonis TalentScape を統合すると、次のことができます。

- Lexonis TalentScape にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Lexonis TalentScape に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Lexonis TalentScape は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- シングル サインオン (SSO) が有効な Lexonis TalentScape サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Lexonis TalentScape では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Lexonis TalentScape では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Lexonis TalentScape の追加

Microsoft Entra ID への Lexonis TalentScape の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Lexonis TalentScape を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Lexonis TalentScape**」と入力します。
4. 結果のパネルから **[Lexonis TalentScape]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Lexonis TalentScape に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Lexonis TalentScape に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Lexonis TalentScape での関連ユーザーとの間にリンク関係を確立する必要があります。

Lexonis TalentScape に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Lexonis TalentScape SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Lexonis TalentScape テストユーザーの作成** - Microsoft Entra のユーザー B.Simon にリンクする Lexonis TalentScape 内の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリ**&gt;**Lexonis TalentScape**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.lexonis.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.lexonis.com/saml2/acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.lexonis.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Lexonis TalentScape クライアント サポート チーム](mailto:support@lexonis.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Lexonis TalentScape アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Lexonis TalentScape アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを下に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | jobtitle | ユーザー.職名 |
    | roles | user.assignedroles |

    注

    Lexonis TalentScape では、アプリケーションに対してユーザーのロールが割り当てられていることを想定しています。 ユーザーに適切なロールを割り当てることができるように、Microsoft Entra ID でこれらのロールを設定してください。 Microsoft Entra ID でロールを構成する方法については、 [こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Lexonis TalentScape の SSO の構成

**Lexonis TalentScape** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Lexonis TalentScape サポート チーム](mailto:support@lexonis.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Lexonis TalentScape テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Lexonis TalentScape に作成します。 Lexonis TalentScape では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Lexonis TalentScape にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Lexonis TalentScape のサインオン URL にリダイレクトされます。
- Lexonis TalentScape のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Lexonis TalentScape に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Lexonis TalentScape] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Lexonis TalentScape に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lifebalance-program-oidc-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LifeBalance Program を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lifebalance-program-oidc-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-02
- Summary: Microsoft Entra と LifeBalance Program の間のシングル サインオンを構成する方法について説明します。

この記事では、LifeBalance Program と Microsoft Entra ID を統合する方法について説明します。 LifeBalance Program と Microsoft Entra ID を統合すると、次のことができます。

Microsoft Entra ID を使用して、LifeBalance Program にアクセスできるユーザーを制御する。 ユーザーが自分の Microsoft Entra アカウントを使用して LifeBalance Program に自動的にサインインできるようにする。 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- LifeBalance Program では、カスタムドメイン付きのサブスクリプションにシングルサインオン (SSO) が有効です。 LifeBalance Program サブスクリプションをお持ちでない場合は、 [LifeBalance の販売ページ](https://sales.lifebalanceprogram.com/)にアクセスできます。

### ギャラリーから LifeBalance Program を追加する

Microsoft Entra ID への LifeBalance Program の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に LifeBalance Program を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「LifeBalance Program**」と入力します。
4. 結果パネルで **LifeBalance Program** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**LifeBalance Program**&gt;**シングルサインオン**に移動します。
3. 次のセクションで以下の手順を実行します。

    1. [ **アプリケーションに移動] を**選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID**、**ディレクトリ (テナント) ID を**コピーし、後で LifeBalance Program 側の構成で使用します。

        [Image: 構成の設定を示すスクリーンショット。]
4. 左側のメニューの [ **認証** ] タブに移動し、次の手順を実行します。

    1. [ **リダイレクト URI** ] ボックスで、LifeBalance Program サブスクリプションに関連付けられている URL をこの形式で使用します。 これらは通常、次のパターンを持っています。 `https://<LifeBalance Domain>/api/azure_token`

        - カスタム ドメインをお持ちでない場合は、 [LifeBalance Program サポート チーム](mailto:info@lifebalanceprogram.com)にお問い合わせください。

        [Image: リダイレクト値を示すスクリーンショット。]
    2. [ **構成] ボタンを** 選択します。
5. 左側のメニューの **[証明書とシークレット** ] に移動し、次の手順を実行します。

    1. [ **クライアント シークレット** ] タブに移動し、[ **+新しいクライアント シークレット**] を選択します。
    2. テキストボックスに有効な **説明** を入力し、要件に従ってドロップダウンから **[有効期限** 日] を選択し、[ **追加**] を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、 **値** が生成されます。 この値をコピーして、後で LifeBalance Program 側の構成で使用します。

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

このセクションでは、B.Simon に LifeBalance Program へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**ライフバランスプログラム** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### LifeBalance Program SSO を構成する

**LifeBalance Program** 側で OAuth/OIDC フェデレーションのセットアップを完了するには、Entra からテナント ID、アプリケーション ID、およびクライアント シークレットのコピーされた値を、セキュリティで保護された電子メール経由[で LifeBalance Program SSO サポート チーム](mailto:sso@lifebalanceprogram.com)に送信する必要があります。 LifeBalance SSO チームは、OIDC 接続が両側で正しく設定されるようにこれらの値を設定します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lifesight-tutorial"} -->
## Microsoft Entra ID で LifeSight for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lifesight-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LifeSight の間でシングル サインオンを構成する方法について説明します。

この記事では、LifeSight と Microsoft Entra ID を統合する方法について説明します。 LifeSight と Microsoft Entra ID を統合すると、次のことができます。

- LifeSight にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して LifeSight に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LifeSight でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- LifeSight では、 **IDP** によって開始される SSO のみがサポートされます。

### ギャラリーから LifeSight を追加する

Microsoft Entra ID への LifeSight の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に LifeSight を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「LifeSight**」と入力します。
4. 結果パネルから **LifeSight** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LifeSight の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、LifeSight に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと LifeSight の関連ユーザーとの間にリンク関係を確立する必要があります。

LifeSight に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LifeSight の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LifeSight のテスト ユーザーの作成 - LifeSight** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**LifeSight**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかの値を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `epav2asrsg_saml` |
    | ステージング | `epav2asrsgtest_saml` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://epa.towerswatson.com:443/twacm/Consumer/metaAlias/epa/sp1` |
    | ステージング | `https://epatest.towerswatson.com:443/twacm/Consumer/metaAlias/epa/sp2` |
6. LifeSight アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、LifeSight アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | クライアントID | WTW/LifeSightチームと合意を取るために |
    | uid (ユーザー識別子) | WTW/LifeSightチームと合意を取るために |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. [ **LifeSight のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LifeSight SSO の構成

**LifeSight** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [LifeSight サポート チーム](mailto:Outsourcing.NNA.Tech.Service.Management.and.Support_Tier.2_3@willistowerswatson.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### LifeSight テスト ユーザーの作成

このセクションでは、LifeSight で B.Simon というユーザーを作成します。 [LifeSight サポート チーム](mailto:Outsourcing.NNA.Tech.Service.Management.and.Support_Tier.2_3@willistowerswatson.com)と協力して、LifeSight プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した LifeSight に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [LifeSight] タイルを選択すると、SSO を設定した LifeSight に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lifesize-cloud-tutorial"} -->
## Microsoft Entra ID で Lifesize Cloud のシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lifesize-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-04
- Summary: Microsoft Entra ID と Lifesize Cloud の間のシングル サインオンを構成する方法について説明します。

この記事では、Lifesize Cloud と Microsoft Entra ID を統合する方法について説明します。 Lifesize Cloud を Microsoft Entra ID と統合すると、次のことが可能になります。

- Lifesize Cloud にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Lifesize Cloud に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

Lifesize Cloud は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Lifesize Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Lifesize Cloud では、**SP** Initiated SSO がサポートされます。
- Lifesize Cloud では、**自動化された**ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Lifesize Cloud の追加

Microsoft Entra ID への Lifesize Cloud の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Lifesize Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Lifesize Cloud**」と入力します。
4. 結果のパネルから **[Lifesize Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Lifesize Cloud 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Lifesize Cloud に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するには、Microsoft Entra ユーザーとそれに対応する Lifesize Cloud ユーザーをリンクする必要があります。

Lifesize Cloud で Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Lifesize Cloud SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Lifesize Cloud のテスト ユーザーを作成する** - Lifesize Cloud で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 上の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Lifesize Cloud]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.lifesizecloud.com/ls/?acs`

    b。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.lifesizecloud.com/<COMPANY_NAME>`

    c. [ **追加の URL の設定] を選択します**。

    d. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://webapp.lifesizecloud.com/?ent=<IDENTIFIER>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、リレー状態でこれらの値を更新します。 [Lifesize Cloud クライアント サポート チーム](https://support.lifesize.com/)に問い合わせて、Sign-On URL と識別子の値を取得してください。この記事で後述する SSO 構成から Relay State の値を取得できます。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Lifesize Cloud のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Lifesize Cloud SSO の構成

1. アプリケーション用に構成された SSO を取得するには、管理者権限で Lifesize Cloud アプリケーションにログインします。
2. 右上隅で自分の名前を選択し、[ **詳細設定]** を選択します。

    [Image: [Advanced Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定) メニュー項目を示すスクリーンショット。]
3. [詳細設定] で、[ **SSO 構成]** リンクを選択します。 インスタンスの [SSO 構成] ページが開きます。

    [Image: [S S O Configuration](S S O 構成) を選択できる [Advanced Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定) を示すスクリーンショット。]
4. SSO 構成 UI で、次の値を構成します。

    [Image: 説明されている値を入力できる [S S O Configuration](S S O 構成) ページを示すスクリーンショット。]

    a. **[ID プロバイダー発行者]** ボックスに、**[Microsoft Entra 識別子]** の値を貼り付けます。

    b。 [ **ログイン URL** ] ボックスに、 **ログイン URL** の値を貼り付けます。

    c. Azure portal からダウンロードした Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、 **X.509 証明書** ボックスに貼り付けます。

    d. [名] ボックスの SAML 属性マッピングに、値を「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`」と入力します。

    e. **[姓]** ボックスの SAML 属性マッピングに、値を「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname`」と入力します。

    f. **[電子メール]** ボックスの SAML 属性マッピングに、値を「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`」と入力します。
5. 構成を確認するには、[ **テスト** ] ボタンを選択します。

    注

    テストを成功させるには、Microsoft Entra ID の構成ウィザードを完了し、テストを実行するユーザーやグループにもアクセスを提供する必要があります。
6. **[SSO を有効にする]** ボタンをクリックして、SSO を有効にします。
7. すべての設定が保存されるように、[ **更新** ] ボタンを選択します。 これにより、RelayState 値が生成されます。 テキスト ボックスに生成された RelayState の値をコピーし、**[Lifesize Cloud のドメインと URL]** セクションの **[リレー状態]** ボックスに貼り付けます。

#### Lifesize Cloud のテスト ユーザーの作成

このセクションでは、Lifesize Cloud で Britta Simon というユーザーを作成します。 Lifesize Cloud では、自動ユーザー プロビジョニングがサポートされています。 Microsoft Entra ID での認証が成功すると、ユーザーはアプリケーションに自動的にプロビジョニングされます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Lifesize Cloud のサインオン URL にリダイレクトされます。
- Lifesize Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Lifesize Cloud] タイルを選択すると、このオプションは Lifesize Cloud のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lift-tutorial"} -->
## Microsoft Entra ID で LIFT for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lift-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LIFT の間のシングル サインオンを構成する方法について説明します。

この記事では、LIFT と Microsoft Entra ID を統合する方法について説明します。 LIFT を Microsoft Entra ID と統合すると、次のことができます。

- LIFT にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して LIFT に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- LIFT でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- LIFT では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの LIFT の追加

Microsoft Entra ID への LIFT の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に LIFT を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**LIFT**」と入力します。
4. 結果パネルから **LIFT** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LIFT に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、LIFT に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと LIFT の関連ユーザーとの間にリンク関係を確立する必要があります。

LIFT に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LIFT SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LIFT テスト ユーザーの作成** - LIFT で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**LIFT**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.portal.liftsoftware.nl/saml-metadata/<identifier>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.portal.liftsoftware.nl/lift/secure`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 [LIFT クライアント サポート チーム](mailto:support@liftsoftware.nl) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LIFT の SSO の構成

**LIFT** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[LIFT サポート チーム](mailto:support@liftsoftware.nl)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### LIFT テスト ユーザーの作成

このセクションでは、LIFT で B.Simon というユーザーを作成します。 [LIFT サポート チーム](mailto:support@liftsoftware.nl)と協力して、LIFT プラットフォームにユーザーを追加します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる LIFT サインオン URL にリダイレクトされます。
- LIFT のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [LIFT] タイルを選択すると、このオプションは LIFT のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/limblecmms-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に LimbleCMMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/limblecmms-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: Microsoft Entra IDから LimbleCMMS にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために LimbleCMMS とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成時に、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [LimbleCMMS](https://limblecmms.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- LimbleCMMS でユーザーを作成する。
- アクセスが不要になった場合は、LimbleCMMS のユーザーを削除します。
- LimbleCMMS にグループを作成する。
- LimbleCMMS のグループでユーザーを追加/削除する
- LimbleCMMS のグループを削除する
- Microsoft Entra IDと LimbleCMMS の間でユーザー属性の同期を維持します。
- LimbleCMMS でグループとグループ メンバーシップをプロビジョニングする。
- LimbleCMMS への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Business Plus 以上のライセンスを持つ [LimbleCMMS](https://limblecmms.com/signup/?plan=business-yearly) テナント。
- Super Admin アクセス許可がある LimbleCMMS のユーザー アカウント。
- LimbleCMMS テナントでシングル サインオンが有効になっていること (カスタマー サクセス マネージャーにお問い合わせください)。
- LimbleCMMS へのプロビジョニングを計画している少なくとも 1 つのグループ (LimbleCMMS のアクセス許可はグループに基づいています。グループをプロビジョニングしない場合、プロビジョニングされたユーザーにはアクセス許可が関連付けられません)。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとLimbleCMMSの間でどのデータを[マッピングするかを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように LimbleCMMS を構成する

1. **Super Admin** として LimbleCMMS にログインします。
2. **[詳細設定] &gt; [SSO の管理]** の順に移動します。 [Image: [SSO の管理]]
3. SSO プロバイダーとして **Microsoft Entra ID** を選択します。
4. シングル サインオンをサポートするように [OIDC をセットアップ](https://help.limblecmms.com/en/articles/4446986-active-directory-oidc-sso-setup-guide)します
5. [ **SCIM トークンの生成** ] ボタンを選択して SCIM トークンを取得し、後の手順でこれを保存します。
6. **[ENABLE SSO]\(SSO を有効にする\) を選択します**。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから LimbleCMMS を追加する

Microsoft Entra アプリケーション ギャラリーから LimbleCMMS を追加して、LimbleCMMS へのプロビジョニングの管理を開始します。 SSO のために LimbleCMMS を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: LimbleCMMS への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて LimbleCMMS でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで LimbleCMMS の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[LimbleCMMS]** を選択します。

    [Image: アプリケーションの一覧の LimbleCMMS のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、LimbleCMMS テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが LimbleCMMS に接続できることを確認します。 接続に失敗した場合は、LimbleCMMS アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから LimbleCMMS に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で LimbleCMMS のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が LimbleCMMS API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | externalId | 糸 |  |
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから LimbleCMMS に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で LimbleCMMS のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | members | リファレンス |  |
    | externalId | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/linkedin-talent-solutions-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LinkedIn Talent Solutions を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/linkedin-talent-solutions-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LinkedIn Talent Solutions の間にシングル サインオンを構成する方法について説明します。

この記事では、LinkedIn Talent Solutions と Microsoft Entra ID を統合する方法について説明します。 LinkedIn Talent Solutions をMicrosoft Entra ID と統合すると、次のことができます。

- LinkedIn Talent Solutions にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って LinkedIn Talent Solutions に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LinkedIn Talent Solutions ダッシュボードでのアカウント センターへのアクセス

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- LinkedIn Talent Solutions では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- LinkedIn Talent Solutions では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの LinkedIn Talent Solutions の追加

Microsoft Entra ID への LinkedIn Talent Solutions の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に LinkedIn Talent Solutions を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**LinkedIn Talent Solutions**」と入力します。
4. 結果のパネルから **LinkedIn Talent Solutions** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LinkedIn Talent Solutions 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、LinkedIn Talent Solutions に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと LinkedIn Talent Solutions の関連ユーザーの間にリンク関係を確立する必要があります。

LinkedIn Talent Solutions に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LinkedIn Talent Solutions の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LinkedIn Talent Solutions のテスト ユーザーの作成 - LinkedIn Talent Solutions** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**LinkedIn Talent Solutions**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、**サービス プロバイダー メタデータ ファイル**がある場合は、次の手順に従います。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: image1]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: Image2]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**と**応答 URL** の値が、[基本的な SAML 構成] セクションに自動的に設定されます。

    [Image: image3]

    注意

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** テキスト ボックスに、URL として「`https://www.linkedin.com/talent/`」と入力します。
7. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[LinkedIn Talent Solutions のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LinkedIn Talent Solutions の SSO の構成

1. LinkedIn Talent Solutions の Web サイトに管理者としてサインインします。
2. **アカウント センター**に移動します。
3. ナビゲーション バーから **[設定]** タブを選択します。

    [Image: 設定ページ]
4. **[Single Sign-On (SSO)](シングル サインオン (SSO))** セクションを展開します。
5. [ **ダウンロード** ] ボタンを選択して **メタデータ ファイル** をダウンロードするか **、ここを選択してフォーム リンクから個々のフィールドを読み込んでコピー** し、構成データを表示します。

    [Image: 構成データ]
6. 次の手順に従って、フォームから個々のフィールドをコピーします。

    [Image: 入力データでの構成]

    ある。 **[エンティティ ID]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[Microsoft Entra 識別子]** テキスト ボックスに貼り付けます。

    b。 **ACS URL** の値をコピーし、この値を **[基本的な SAML 構成]** セクションの **[Reply URL]** テキスト ボックスに貼り付けます。

    c. **[SP X.509 Certificate(signing)](SP X.509 証明書 (署名))** ボックスの内容をメモ帳にコピーして、お使いのコンピューターに保存します。
7. [ **XML ファイルのアップロード** ] を選択して、前にコピーした **フェデレーション メタデータ XML** ファイルをアップロードします。

#### LinkedIn Talent Solutions のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを LinkedIn Talent Solutions に作成します。 LinkedIn Talent Solutions では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 LinkedIn Talent Solutions にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる LinkedIn Talent Solutions のサインオン URL にリダイレクトされます。
- LinkedIn Talent Solutions のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した LinkedIn Talent Solutions に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [LinkedIn Talent Solutions] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した LinkedIn Talent Solutions に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/linkedinelevate-provisioning-tutorial"} -->
## Microsoft Entra ID で自動ユーザー プロビジョニング用に LinkedIn Elevate を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/linkedinelevate-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: LinkedIn Elevate に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、Microsoft Entra ID から LinkedIn Elevate にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するために LinkedIn Elevate と Microsoft Entra ID で実行する必要がある手順について説明することです。

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LinkedIn Elevate テナント
- LinkedIn Account Center へのアクセス許可がある LinkedIn Elevate の管理者アカウント

注

Microsoft Entra ID と LinkedIn Elevate の統合には、SCIM プロトコルが使用されます。

### 手順 1: LinkedIn Elevate にユーザーを割り当てる

Microsoft Entra ID では、選択されたアプリへのアクセスを付与するユーザーを決定する際に "割り当て" という概念が使用されます。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに "割り当て済み" のユーザーとグループのみが同期されます。

プロビジョニング サービスを構成して有効にする前に、LinkedIn Elevate へのアクセスが必要なユーザーを表す Microsoft Entra ID 内のユーザーやグループを決定しておく必要があります。 決定し終えたら、次の手順でこれらのユーザーを LinkedIn Elevate に割り当てることができます。

[エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを LinkedIn Elevate に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを LinkedIn Elevate に割り当てて、プロビジョニングの構成をテストすることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- ユーザーを LinkedIn Elevate に割り当てるときに、割り当てのダイアログで**ユーザー** ロールを選択する必要があります。 "既定のアクセス" ロールはプロビジョニングでは機能しません。

### 手順 2: LinkedIn Elevate にユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID を LinkedIn Elevate の SCIM のユーザー アカウント プロビジョニング API に接続する手順のほか、プロビジョニング サービスを構成して、Microsoft Entra ID のユーザーとグループの割り当てに基づいて割り当て済みのユーザー アカウントを LinkedIn Elevate で作成、更新、無効化する手順を説明します。

**ヒント:** LinkedIn Elevate では SAML ベースのシングル サインオンを有効にすることもできます。これを行うには、[Azure portal](https://portal.azure.com) で提供される手順に従ってください。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID において、LinkedIn Elevate への自動ユーザーアカウントプロビジョニングを構成する

まず最初に、LinkedIn アクセス トークンを取得します。 エンタープライズ管理者の場合は、アクセス トークンをセルフ プロビジョニングできます。 アカウント センターで、 **[設定] &gt; [グローバル設定]** をクリックすると、 **[SCIM セットアップ]** パネルが開きます。

注

リンクからではなく、アカウント センターに直接アクセスしている場合は、次の手順を使用してアクセスできます。

1. アカウント センターにサインインします。
2. **[管理者] &gt; [管理者設定]** を選択します。
3. 左側のサイドバーで [ **Advanced Integrations]\(高度な統合** \) を選択します。 アカウント センターにリダイレクトされます。
4. [ **+ 新しい SCIM 構成の追加]** を選択し、各フィールドに入力して手順に従います。

    注

    自動割り当てライセンスが有効になっていない場合は、ユーザー データのみが同期されることを意味します。

    [Image: スクリーンショットには、LinkedIn アカウント センター グローバル設定が示されています。]

    注

    ライセンスの自動割り当てを有効にする場合、アプリケーション インスタンスとライセンスの種類に注意する必要があります。 ライセンスは、すべてのライセンスが取得されるまで、先着順で割り当てられます。

    [Image: スクリーンショットには、S C I M セットアップ ページが示されています。]
5. [ **トークンの生成]** を選択します。 **[アクセス トークン]** フィールドの下に、アクセス トークンが表示されます。
6. ページを離れる前に、クリップボードまたはコンピューターにアクセス トークンを保存します。
7. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
8. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
9. シングル サインオンのために LinkedIn Elevate を既に構成している場合は、検索フィールドで LinkedIn Elevate のインスタンスを検索します。 それ以外の場合は、**[追加]** を選択し、アプリケーションギャラリーで **LinkedIn Elevate** を検索します。 検索結果から LinkedIn Elevate を選択してアプリケーションの一覧に追加します。
10. LinkedIn Elevate のインスタンスを選択してから、 **[プロビジョニング]** タブを選択します。
11. [ **+ 新しい構成**] を選択します。
12. **[管理者資格情報]** の下で、以下のフィールドを入力します。

    - **[テナント URL]** フィールドに、「 `https://api.linkedin.com` 」と入力します。
    - [ **シークレット トークン** ] フィールドに、手順 1 で生成したアクセス トークンを入力し、[ **テスト接続** ] を選択します。
    - ポータルの右上に成功通知が表示されます。
13. [ **作成]** を選択して構成を作成します。
14. [**概要**] ページで **[プロパティ**] を選択します。
15. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
16. **[属性マッピング]** セクションで、Microsoft Entra ID から LinkedIn Elevate に同期するユーザーおよびグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために LinkedIn Elevate のユーザー アカウントとグループとの照合に使用されます。 [保存] ボタンをクリックして変更をコミットします。

    [Image: スクリーンショットには、属性マッピングを含むマッピングが示されています。]
17. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
18. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
19. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 3: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/linkedinelevate-tutorial"} -->
## Microsoft Entra ID で LinkedIn Elevate for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/linkedinelevate-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LinkedIn Elevate の間のシングル サインオンを構成する方法について説明します。

この記事では、LinkedIn Elevate と Microsoft Entra ID を統合する方法について説明します。 LinkedIn Elevate を Microsoft Entra ID と統合すると、次のことが可能になります。

- LinkedIn Elevate にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して LinkedIn Elevate に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LinkedIn Elevate でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- LinkedIn Elevate では、**SP と IDP** Initiated SSO がサポートされます。
- LinkedIn Elevate では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- LinkedIn Elevate では、[**自動化された**ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/linkedinelevate-provisioning-tutorial)がサポートされます。

### ギャラリーからの LinkedIn Elevate の追加

Microsoft Entra IDへの LinkedIn Elevate の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に LinkedIn Elevate を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**LinkedIn Elevate**」と入力します。
4. 結果のパネルから **[LinkedIn Elevate]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LinkedIn Elevate 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、LinkedIn Elevate に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するためには、Microsoft Entra ユーザーと LinkedIn Elevate の関連ユーザーとの間にリンク関係を確立する必要があります。

LinkedIn Elevate に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LinkedIn Elevate の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LinkedIn Elevate のテスト ユーザーの作成** - LinkedIn Elevate で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[LinkedIn Elevate]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** Initiated モードで構成する場合は、次の手順を行います。

    a. [ **識別子** ] テキスト ボックスに **エンティティ ID** の値を入力し、この記事で後述する Linkedin ポータルからエンティティ ID 値をコピーします。

    b。 **応答 URL** テキスト ボックスにアサーション **コンシューマー アクセス (ACS) URL 値を**入力し、この記事で後述する Linkedin ポータルからアサーション コンシューマー アクセス (ACS) URL 値をコピーします。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://www.linkedin.com/checkpoint/enterprise/login/<ACCOUNT_ID>?application=elevate&applicationInstanceId=<INSTANCE_ID>` という形式で URL を入力します。
7. LinkedIn Elevate アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングをSAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 LinkedIn Elevate アプリケーションでは、nameidentifier が **user.mail** にマップされることを想定しているため、[編集] アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
8. その他に、LinkedIn Elevate アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 部署 | user.department |
9. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
10. **[LinkedIn Elevate のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LinkedIn Elevate の SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として LinkedIn Elevate テナントにサインオンします。
2. **アカウント センター**で、[**設定] で [グローバル設定]** を選択**します**。 また、ドロップダウン リストから **[Elevate - Elevate Microsoft Entra ID Test (Elevate - Elevate Microsoft Entra ID テスト)]** を選択します。

    [Image: [Elevate A A D Test](Elevate A A D テスト) を選択できる [Global Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/グローバル設定) を示すスクリーンショット。]
3. **フォームから個々のフィールドを読み込んでコピーし、次の手順を実行するには、[またはここを選択] を選択**します。

    [Image: 説明されている値を入力できる [Single Sign-On](シングル サインオン) を示すスクリーンショット。]

    a. **[Entity ID](エンティティ ID)** をコピーして、**[基本的な SAML 構成]** にある **[識別子]** ボックスに貼り付けます。

    b。 **[Assertion Consumer Access (ACS) URL]** をコピーし、**[基本的な SAML 構成]** の **[応答 URL]** ボックスに貼り付けます。
4. **[LinkedIn Admin Settings (LinkedIn 管理者設定)]** セクションに移動します。 [XML ファイルのアップロード] オプションを選択して、ダウンロードした XML ファイルをアップロードします。

    [Image: X M L ファイルをアップロードできる [Configure the LinkedIn service provider S S O settings](LinkedIn サービス プロバイダーの S S O 設定の構成) を示すスクリーンショット。]
5. **[オン] を**選択して SSO を有効にします。 SSO の状態が **[未接続]** から **[接続済み]** に変わります。

    [Image: [Automatically assign licenses](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ライセンスを自動的に割り当てる) を選択できる [Single Sign-On](シングル サインオン) を示すスクリーンショット。]

#### LinkedIn Elevate のテスト ユーザーの作成

LinkedIn Elevate Application では、Just-In-Time ユーザー プロビジョニングがサポートされ、認証後にユーザーがアプリケーションに自動的に作成されます。 LinkedIn Elevate ポータルの管理者設定ページで、スイッチ **[Automatically Assign licenses (ライセンスを自動的に割り当てる)]** を切り替えて、ジャストインタイム プロビジョニングを有効にします。これにより、ユーザーにライセンスも割り当てられます。 LinkedIn Elevate は、 自動ユーザー プロビジョニングもまた、サポートしています。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/linkedinelevate-provisioning-tutorial)をご覧ください。

[Image: Microsoft Entra テスト ユーザーの作成]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる LinkedIn Elevate のサインオン URL にリダイレクトされます。
- LinkedIn Elevate のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した LinkedIn Elevate に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで LinkedIn Elevate タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した LinkedIn Elevate に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/linkedinlearning-tutorial"} -->
## Microsoft Entra ID で LinkedIn Learning for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/linkedinlearning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LinkedIn Learning の間でシングル サインオンを構成する方法について説明します。

この記事では、LinkedIn Learning と Microsoft Entra ID を統合する方法について説明します。 LinkedIn Learning と Microsoft Entra ID を統合すると、次のことができます。

- LinkedIn Learning にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して LinkedIn Learning に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LinkedIn Learning でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- LinkedIn Learning では、**SP および IDP** による SSO がサポートされます。
- LinkedIn Learning では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから LinkedIn Learning を追加する

Microsoft Entra ID への LinkedIn Learning の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に LinkedIn Learning を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「LinkedIn Learning**」と入力します。
4. 結果パネルから **LinkedIn Learning** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LinkedIn Learning の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、LinkedIn Learning に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと LinkedIn Learning の関連ユーザーとの間にリンク関係を確立する必要があります。

LinkedIn Learning に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LinkedIn Learning の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ライセンスの割り当て** - LinkedIn Learning で B.Simon に対応するユーザーを割り当て、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**LinkedIn Learning**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a [ **識別子** ] ボックスに、LinkedIn ポータルからコピーした **エンティティ ID を** 入力します。

    b。 **[応答 URL**] ボックスに、LinkedIn ポータルからコピーした**アサーション コンシューマー サービス (ACS) URL を**入力します。

    c. **SP 開始**モードでアプリケーションを構成する場合は、サインオン URL を指定する **[基本的な SAML 構成**] セクションで [**追加の URL の設定**] オプションを選択します。 ログイン URL を作成するには、 **Assertion Consumer Service (ACS) URL を** コピーし、/saml/ を /login/ に置き換えます。 完了すると、サインオン URL には次のパターンが必要です。

    `https://www.linkedin.com/checkpoint/enterprise/login/<AccountId>?application=learning&applicationInstanceId=<InstanceId>`

    手記

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新します。これについては、記事の **「LinkedIn Learning SSO の構成」** セクションで後述します。
6. LinkedIn Learning アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **nameidentifier** は **user.userprincipalname** にマップされています。 LinkedIn Learning アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、 **編集** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **LinkedIn Learning のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LinkedIn Learning SSO の構成

1. LinkedIn Learning 企業サイトに管理者としてログインします。
2. [&gt;&gt; に移動] を選択します。

    [Image: アカウント]
3. [**認証**] で [**シングル サインオンの構成**] を選択し、[**新しい SSO の追加]** を選択します。

    [Image: シングル サインオンの構成]
4. [**新しい SSO の追加]** ドロップダウンから **[SAML**] を選択します。

    [Image: SAML 認証]
5. [ **基本** ] タブで、「 **SAML 接続名」と** 入力し、[ **次へ**] を選択します。

    [Image: SSO 接続]
6. **[ID プロバイダーの設定**] タブに移動し、[**ファイルのダウンロード**] を選択してメタデータ ファイルをダウンロードし、コンピューターに保存し、[**次へ**] を選択します。

    [Image: ID プロバイダーの設定]

    手記

    このファイルを ID プロバイダーにインポートできない場合があります。 たとえば、Okta にはこの機能がありません。 このケースが構成要件と一致する場合は、「個々のフィールドの操作」に進んでください。
7. [ **ID プロバイダーの設定** ] タブで、[ **フィールドの読み込みと情報のコピー** ] を選択して必要なフィールドをコピーし **、[基本的な SAML 構成]** セクションに貼り付けて、[ **次へ**] を選択します。

    [Image: 設定]
8. **[SSO 設定]** タブに移動し、[**XML ファイルのアップロード**] を選択して、ダウンロードした**フェデレーション メタデータ XML** ファイルをアップロードします。

    [Image: 証明書ファイル]
9. **[SSO 設定**] タブでコピーした必須フィールドを手動で入力します。

    [Image: 値の入力]
10. [ **SSO 設定]** で、要件に従って SSO オプションを選択し、[ **保存]** を選択します。

    [Image: SSO 設定]

##### 単一 Sign-On の有効化

構成が完了したら、[SSO 状態] ドロップダウンから [アクティブな  選択して SSO を有効にします。

[Image: シングル サインオンの有効化]

#### ライセンスの割り当て

SSO を有効にしたら、[ライセンスの自動プロビジョニング] を [**オン**] に切り替えて **[保存]** を選択することで、従業員に**ライセンスを自動的**に割り当てることができます。 このオプションを有効にすると、ユーザーは初めて認証されたときに自動的にライセンスが付与されます。

[Image: ライセンスの割り当て]

手記

このオプションを有効にしない場合、管理者は [ユーザー] タブで手動でユーザーを追加する必要があります。LinkedIn Learning は、ユーザーをメール アドレスで識別します。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる LinkedIn Learning のサインオン URL にリダイレクトされます。
- LinkedIn Learning のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した LinkedIn Learning に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで LinkedIn Learning タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した LinkedIn Learning に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/linkedinsalesnavigator-provisioning-tutorial"} -->
## LinkedIn Sales Navigator を構成して自動ユーザー プロビジョニングを行う - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/linkedinsalesnavigator-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-12
- Summary: LinkedIn Sales Navigator に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事の目的は、Microsoft Entra ID から LinkedIn Sales Navigator にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するために LinkedIn Sales Navigator と Microsoft Entra ID で実行する必要がある手順について説明することです。

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LinkedIn Sales Navigator テナント
- LinkedIn Account Center へのアクセス許可がある LinkedIn Sales Navigator の管理者アカウント

注

Microsoft Entra ID と LinkedIn Sales Navigator の統合には、SCIM プロトコルが使用されます。

### LinkedIn Sales Navigator へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスを付与するユーザーを決定する際に "割り当て" という概念が使用されます。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに "割り当て済み" のユーザーとグループのみが同期されます。

プロビジョニング サービスを構成して有効にする前に、LinkedIn Sales Navigator にアクセスする必要があるユーザーを表す Microsoft Entra ID のユーザーやグループを決定する必要があります。 決定し終えたら、次の手順でこれらのユーザーを LinkedIn Sales Navigator に割り当てることができます。

[エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを LinkedIn Sales Navigator に割り当てる際の重要なヒント

- プロビジョニング構成をテストするには、1 人の Microsoft Entra ユーザーを LinkedIn Sales Navigator に割り当てることをお勧めします。 後で追加のユーザーやグループが割り当てられる場合があります。
- LinkedIn Sales Navigator にユーザーを割り当てるときは、割り当てダイアログで **ユーザー** ロールを選択する必要があります。 "既定のアクセス" ロールはプロビジョニングでは機能しません。

### LinkedIn Sales Navigator へのユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra ID を LinkedIn Sales Navigator の SCIM ユーザー アカウント プロビジョニング API に接続し、Microsoft Entra ID のユーザーとグループの割り当てに基づいて LinkedIn Sales Navigator で割り当てられたユーザー アカウントを作成、更新、無効化するようにプロビジョニング サービスを構成する手順について説明します。

ヒント

[Azure portal](https://portal.azure.com) で提供されている手順に従って、LinkedIn Sales Navigator で SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で LinkedIn Sales Navigator への自動ユーザー アカウント プロビジョニングを構成するには

まず最初に、LinkedIn アクセス トークンを取得します。 エンタープライズ管理者の場合は、アクセス トークンをセルフ プロビジョニングできます。 アカウント センターで、[ **設定] &gt; [グローバル設定]** に移動し、[ **SCIM セットアップ]** パネルを開きます。

注

リンクからではなく、アカウント センターに直接アクセスしている場合は、次の手順を使用してアクセスできます。

1. アカウント センターにサインインします。
2. [ **Admin**&gt;**Admin Settings] を** 選択します。
3. 左側のサイドバーで [ **Advanced Integrations]\(高度な統合** \) を選択します。 アカウント センターにリダイレクトされます。
4. [ **+ 新しい SCIM 構成の追加]** を選択し、各フィールドに入力して手順に従います。

    注

    ライセンスの自動割り当てオプションが有効になっていない場合は、ユーザー データのみが同期されることを意味します。

    [Image: LinkedIn アカウント センターのグローバル設定を示すスクリーンショット。]

    注

    ライセンスの自動割り当てを有効にする場合、アプリケーション インスタンスとライセンスの種類に注意する必要があります。 ライセンスは、すべてのライセンスが取得されるまで、先着順で割り当てられます。

    [Image: スクリーンショットは、[S C I M セットアップ] ページを示しています。]
5. [ **トークンの生成]** を選択します。 **アクセス トークン** フィールドの下に、アクセストークンが表示されるはずです。
6. ページを離れる前に、クリップボードまたはコンピューターにアクセス トークンを保存します。
7. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
8. **Entra ID**&gt;**Enterprise アプリ**に移動する
9. シングル サインオンのために LinkedIn Sales Navigator を既に構成している場合は、検索フィールドで LinkedIn Sales Navigator のインスタンスを検索します。 それ以外の場合は、[ **追加]** を選択し、アプリケーション ギャラリーで **LinkedIn Sales Navigator** を検索します。 検索結果から LinkedIn Sales Navigator を選択してアプリケーションの一覧に追加します。
10. LinkedIn Sales Navigator のインスタンスを選択し、[プロビジョニング] タブ **を** 選択します。

    [Image: プロビジョニングタブ]
11. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
12. **[管理者資格情報**] の下の次のフィールドに入力します。

    - [ **テナント URL** ] フィールドに「 https://developer.linkedin.com」と入力します。
    - [ **シークレット トークン** ] フィールドに、手順 1 で生成したアクセス トークンを入力し、[ **テスト接続** ] を選択します。
    - ポータルの右上に成功通知が表示されます。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
13. [ **作成]** を選択して構成を作成します。
14. [**概要**] ページで **[プロパティ**] を選択します。
15. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
16. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
17. Microsoft Entra ID から LinkedIn Sales Navigator に同期されるユーザー属性とグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で LinkedIn Sales Navigator のユーザー アカウントとグループとの照合に使用されます。 [保存] ボタンをクリックして変更をコミットします。

    [Image: [属性マッピング] を含むマッピングを示すスクリーンショット。]
18. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
19. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
20. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/linkedinsalesnavigator-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に LinkedIn Sales Navigator を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/linkedinsalesnavigator-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LinkedIn Sales Navigator の間でシングル サインオンを構成する方法について説明します。

この記事では、LinkedIn Sales Navigator と Microsoft Entra ID を統合する方法について説明します。 LinkedIn Sales Navigator を Microsoft Entra ID と統合すると、次のことが可能になります。

- LinkedIn Sales Navigator にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entraアカウントを使用して LinkedIn Sales Navigator に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LinkedIn Sales Navigator でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- LinkedIn Sales Navigator では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- LinkedIn Sales Navigator では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- LinkedIn Sales Navigator では、**自動化された**ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの LinkedIn Sales Navigator ナビゲーターの追加

Microsoft Entra ID への LinkedIn Sales Navigator の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に LinkedIn Sales Navigator を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**LinkedIn Sales Navigator**」と入力します。
4. 結果のパネルから **[LinkedIn Sales Navigator]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LinkedIn Sales Navigator 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、LinkedIn Sales Navigator に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するには、Microsoft Entra ユーザーとそれに対応する LinkedIn Sales Navigator ユーザーをリンクする必要があります。

LinkedIn Sales Navigator に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LinkedIn Sales Navigator の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LinkedIn Sales Navigator のテストユーザーを作成** - Microsoft Entra のユーザーである B.Simon に連携する LinkedIn Sales Navigator 上の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[LinkedIn Sales Navigator]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに **エンティティ ID** の値を入力し、この記事で後述する Linkedin ポータルからエンティティ ID 値をコピーします。

    b。 **応答 URL** テキスト ボックスにアサーション **コンシューマー アクセス (ACS) URL 値を**入力し、この記事で後述する Linkedin ポータルからアサーション コンシューマー アクセス (ACS) URL 値をコピーします。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.linkedin.com/checkpoint/enterprise/login/<account id>?application=salesNavigator`
7. LinkedIn Sales Navigator アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングをSAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、LinkedIn Sales Navigator アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | 部署 | user.department |
    | ファーストネーム | User.givenname |
    | lastname | ユーザーの名字 |
    | 一意のユーザー ID | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[LinkedIn Sales Navigator のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LinkedIn Sales Navigator の SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として **LinkedIn Sales Navigator** テナントにサインオンします。
2. **アカウント センター**で、[**設定] で [グローバル設定]** を選択**します**。 さらに、ドロップダウン リストから **[Sales Navigator]** を選択します。

    [Image: [Sales Navigator] を選択できる [Application Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーションの設定) を示すスクリーンショット。]
3. **フォームから個々のフィールドを読み込んでコピーし、次の手順を実行するには、[またはここを選択] を選択**します。

    [Image: 説明されている値を入力できる [Single Sign-On](シングル サインオン) を示すスクリーンショット。]

    a. **[エンティティ ID]** をコピーして、**[基本的な SAML 構成]** の **[識別子]** ボックスに貼り付けます。

    b。 **[Assertion Consumer Access (ACS) URL]** をコピーし、**[基本的な SAML 構成]** の **[応答 URL]** ボックスに貼り付けます。
4. **[LinkedIn Admin Settings (LinkedIn 管理者設定)]** セクションに移動します。 [XML ファイルのアップロード] オプションを選択して、ダウンロードした **XML ファイルをアップロード** します。

    [Image: X M L ファイルをアップロードできる [Configure the LinkedIn service provider S S O settings](LinkedIn サービス プロバイダーの S S O 設定の構成) を示すスクリーンショット。]
5. **[オン] を**選択して SSO を有効にします。 SSO の状態が **[Not Connected (未接続)]** から **[Connected (接続済み)]** に変更されます

    [Image: [Authenticate users with S S O](S S O を使用してユーザーを認証する) を有効にできる [Single Sign-On](シングル サインオン) を示すスクリーンショット。]

#### LinkedIn Sales Navigator のテスト ユーザーの作成

Linked Sales Navigator アプリケーションでは、ジャストインタイム (JIT) ユーザー プロビジョニングがサポートされ、認証後にユーザーがアプリケーション内に自動的に作成されます。 **[Automatically assign licenses (ライセンスを自動的に割り当てる)]** をアクティブ化して、ユーザーにライセンスを割り当てます。

[Image: Microsoft Entra テスト ユーザーの作成]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる LinkedIn Sales Navigator のサインオン URL にリダイレクトされます。
- LinkedIn Sales Navigator のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した LinkedIn Sales Navigator に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [LinkedIn Sales Navigator] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した LinkedIn Sales Navigator に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/liquidfiles-tutorial"} -->
## Microsoft Entra ID で LiquidFiles for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/liquidfiles-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-04
- Summary: Microsoft Entra ID と LiquidFiles の間にシングル サインオンを構成する方法について説明します。

この記事では、LiquidFiles と Microsoft Entra ID を統合する方法について説明します。 LiquidFiles と Microsoft Entra ID を統合すると、次のことができます。

- LiquidFiles にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って LiquidFiles に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

LiquidFiles は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- LiquidFiles でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- LiquidFiles では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの LiquidFiles の追加

Microsoft Entra ID への LiquidFiles の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に LiquidFiles を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**LiquidFiles**」と入力します。
4. 結果のパネルから **[LiquidFiles]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LiquidFiles 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、LiquidFiles に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと LiquidFiles の関連ユーザーとの間にリンク関係を確立する必要があります。

LiquidFiles に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LiquidFiles SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LiquidFilesのテストユーザーを作成 - LiquidFiles上でB.Simonに対応するユーザーを作成し、Microsoft Entraのユーザー表現とリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**LiquidFiles**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_SERVER_URL>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_SERVER_URL>/saml/consume`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_SERVER_URL>/saml/init`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[LiquidFiles クライアント サポート チーム](https://www.liquidfiles.com/support.html)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. **[Set up LiquidFiles](LiquidFiles の設定)** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LiquidFiles SSO の構成

1. LiquidFiles 企業サイトに管理者としてサインオンします。
2. メニューから [] で [&gt;] を選択します。
3. **[Single Sign-On Configuration](シングル サインオンの構成)** ページで、次の手順を実行します。

    [Image: シングルサインオンの設定]

    a. **[Single Sign On Method](シングル サインオンの方法)** として、**[SAML 2]** を選びます。

    b。 **[IDP ログイン URL]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    c. **[IDP ログアウト URL]** テキストボックスに **[ログアウト URL]** の値を貼り付けます。

    d. **[IDP Cert Fingerprint] (IDP 証明書のフィンガープリント)** テキストボックスに**拇印**の値を貼り付けます。

    e. [Name identifier Format](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前識別子の形式) テキスト ボックスに、次の値を入力します。`urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress`

    f. [Authn Context](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証コンテキスト) テキスト ボックスに、次の値を入力します。`urn:oasis:names:tc:SAML:2.0:ac:classes:PasswordProtectedTransport`

    g. **保存** を選択します。

#### LiquidFiles のテスト ユーザーの作成

このセクションの目的は、LiquidFiles で Britta Simon というユーザーを作成することです。 LiquidFiles アプリケーションにログインする前に、LiquidFiles サーバー管理者と協力して自分自身をユーザーとして追加します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる LiquidFiles のサインオン URL にリダイレクトされます。
- LiquidFiles のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで LiquidFiles タイルを選択すると、このオプションは LiquidFiles のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/litmos-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に SAP Litmos を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/litmos-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-12
- Summary: Microsoft Entra ID から SAP Litmos へのユーザー アカウントの自動プロビジョニングおよび自動プロビジョニング解除を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために SAP Litmos と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [SAP Litmos](http://www.litmos.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- SAP Litmos でユーザーを作成する。
- アクセスが不要になったら、SAP Litmos のユーザーを削除します。
- Microsoft Entra ID と SAP Litmos の間でユーザー属性の同期を維持する。
- SAP Litmos でグループとグループ メンバーシップをプロビジョニングする。
- SAP Litmos への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/litmos-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- SAP Litmos テナント。
- 管理者アクセス許可がある SAP Litmos のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と SAP Litmos の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように SAP Litmos を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように SAP Litmos を構成するには、SAP Litmos サポートにお問い合わせください。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから SAP Litmos を追加する

Microsoft Entra アプリケーション ギャラリーから SAP Litmos を追加して、SAP Litmos へのプロビジョニングの管理を開始します。 以前に SAP Litmos を SSO 用に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザー/グループの属性に基づいて、プロビジョニングされたユーザーのスコープを設定できます。 割り当てに基づいてアプリにプロビジョニングされたユーザーのスコープを設定する場合は、次の [手順](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) を使用して、ユーザーとグループをアプリケーションに割り当てることができます。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされたユーザーのスコープを設定する場合は、 [ここで](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)説明するようにスコープ フィルターを使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- さらにロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: SAP Litmos への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で SAP Litmos の自動ユーザー プロビジョニングを構成するには、以下の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧 **で SAP Litmos** を選択します。

    [Image: アプリケーションの一覧の SAP Litmos リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、SAP Litmos テナント URL とシークレット トークンを入力します。 Microsoft Entra ID が SAP Litmos に接続できることを確認するには、[ **テスト接続** ] を選択します。 接続に失敗した場合は、SAP Litmos アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から SAP Litmos に同期されるユーザー **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で SAP Litmos のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が SAP Litmos API でサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | SAP Litmos で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | タイムゾーン | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |  |  |
    | urn:ietf:params:scim:schemas:extension:Litmos:2.0:User:CustomField:CustomField1 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Litmos:2.0:User:CustomField:CustomField2 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Litmos:2.0:User:CustomField:CustomField3 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Litmos:2.0:User:CustomField:CustomField4 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Litmos:2.0:User:CustomField:CustomField5 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Litmos:2.0:User:CustomField:CustomField6 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Litmos:2.0:User:CustomField:CustomField7 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Litmos:2.0:User:CustomField:CustomField8 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Litmos:2.0:User:CustomField:CustomField9 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Litmos:2.0:User:CustomField:CustomField10 | 糸 |  |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から SAP Litmos に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で SAP Litmos のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | SAP Litmos で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

- [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、正常にプロビジョニングされたユーザーまたは失敗したユーザーを特定する
- [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
- プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/litmos-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SAP Litmos を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/litmos-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と SAP Litmos の間にシングル サインオンを構成する方法について説明します。

この記事では、SAP Litmos と Microsoft Entra ID を統合する方法について説明します。 SAP Litmos と Microsoft Entra ID を統合すると、次のことができます。

- SAP Litmos にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SAP Litmos に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP Litmos でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SAP Litmos では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。
- SAP Litmos では、**Just In Time** ユーザー プロビジョニングがサポートされています。
- SAP Litmos では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/litmos-provisioning-tutorial)。

### ギャラリーからの SAP Litmos の追加

Microsoft Entra ID への SAP Litmos の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SAP Litmos を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SAP Litmos**」と入力します。
4. 結果のパネルから **[SAP Litmos]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SAP Litmos 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SAP Litmos に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SAP Litmos の関連ユーザーとの間にリンク関係を確立する必要があります。

SAP Litmos に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAP Litmos SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SAP Litmos テストユーザーの作成 - B.Simon に対応するユーザーを SAP Litmos で作成して、それを Microsoft Entra のユーザー表現にリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**SAP Litmos**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **識別子** |
    | --- |
    | `https://<CustomerName>.litmos.com` |
    | `https://<CustomerName>.litmos.com.au` |
    | `https://<CustomerName>.litmoseu.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CompanyName>.litmos.com/integration/splogin` |
    | `https://<CompanyName>.litmos.com/integration/splogin?IdP=1` |
    | `https://<CompanyName>.litmos.com/integration/splogin?IdP=2` |
    | `https://<CompanyName>.litmos.com/integration/splogin?IdP=3` |
    | `https://<CompanyName>.litmos.com/integration/splogin?IdP=14` |
    | `https://<CompanyName>.litmos.com.au/integration/splogin` |
    | `https://<CompanyName>.litmos.com.au/integration/splogin?IdP=1` |
    | `https://<CompanyName>.litmos.com.au/integration/splogin?IdP=2` |
    | `https://<CompanyName>.litmos.com.au/integration/splogin?IdP=3` |
    | `https://<CompanyName>.litmos.com.au/integration/splogin?IdP=14` |
    | `https://<CompanyName>.litmoseu.com/integration/splogin` |
    | `https://<CompanyName>.litmoseu.com/integration/splogin?IdP=1` |
    | `https://<CompanyName>.litmoseu.com/integration/splogin?IdP=2` |
    | `https://<CompanyName>.litmoseu.com/integration/splogin?IdP=3` |
    | `https://<CompanyName>.litmoseu.com/integration/splogin?IdP=14` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CompanyName>.litmos.com/integration/splogin` |
    | `https://<CompanyName>.litmos.com/integration/splogin?IdP=1` |
    | `https://<CompanyName>.litmos.com/integration/splogin?IdP=2` |
    | `https://<CompanyName>.litmos.com/integration/splogin?IdP=3` |
    | `https://<CompanyName>.litmos.com/integration/splogin?IdP=14` |
    | `https://<CompanyName>.litmos.com.au/integration/splogin` |
    | `https://<CompanyName>.litmos.com.au/integration/splogin?IdP=1` |
    | `https://<CompanyName>.litmos.com.au/integration/splogin?IdP=2` |
    | `https://<CompanyName>.litmos.com.au/integration/splogin?IdP=3` |
    | `https://<CompanyName>.litmos.com.au/integration/splogin?IdP=14` |
    | `https://<CompanyName>.litmoseu.com/integration/splogin` |
    | `https://<CompanyName>.litmoseu.com/integration/splogin?IdP=1` |
    | `https://<CompanyName>.litmoseu.com/integration/splogin?IdP=2` |
    | `https://<CompanyName>.litmoseu.com/integration/splogin?IdP=3` |
    | `https://<CompanyName>.litmoseu.com/integration/splogin?IdP=14` |

    d. **[Relay State URL] (リレー状態 URL)** テキスト ボックスに、次のいずれかの URL を入力します。

    | **Relay State URL (リレー状態 URL)** |
    | --- |
    | `https://<CompanyName>.litmos.com/integration/splogin?RelayState=https://<CustomerName>.litmos.com/Course/12345` |
    | `https://<CompanyName>.litmos.com/integration/splogin?RelayState=https://<CustomerName>.litmos.com/LearningPath/12345` |

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子、応答 URL、サインオン URL、リレー状態 URL で更新します。これについては、後の記事で説明します。これらの値を取得するには [、SAP Litmos クライアント サポート チーム](https://www.litmos.com/contact-us) にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Set up SAP Litmos] (SAP Litmos のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAP Litmos SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として SAP Litmos 企業サイトにサインオンします。
2. 左側のナビゲーション バーで、[アカウント] を選択 **します**。

    [Image: アプリ側の [Accounts](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント) セクション]
3. **統合** タブを選択します。

    [Image: [Integration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) タブ]
4. [ **統合** ] タブで、[ **サード パーティ統合**] まで下にスクロールし、[ **SAML 2.0** ] タブを選択します。

    [Image: [SAML 2.0] セクション]
5. **[The SAML endpoint for litmos is:](Litmos の SAML エンドポイント:)** の値をコピーし、Azure Portal の **[Litmos のドメインと URL]** セクションの **[応答 URL]** テキストボックスに貼り付けます。

    [Image: SAML エンドポイント]
6. **SAP Litmos** アプリケーションで、次の手順を実行します。

    [Image: Litmos アプリケーション]

    ある。 [ **SAML を有効にする] を選択します**。

    b。 Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、**[SAML X.509 Certificate]** ボックスに貼り付けます。

    c. [ **変更の保存] を選択します**。

#### SAP Litmos のテストユーザーの作成

このセクションでは、B.Simon というユーザーを SAP Litmos に作成します。 SAP Litmos では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 SAP Litmos にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

**SAP Litmos で Britta Simon というユーザーを作成するには、次の手順に従います。**

1. 別の Web ブラウザーのウィンドウで、管理者として SAP Litmos 企業サイトにサインオンします。
2. 左側のナビゲーション バーで、[アカウント] を選択 **します**。

    [Image: アプリ側の [アカウント] セクション]
3. **統合** タブを選択します。

    [Image: [Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) タブ]
4. [ **統合** ] タブで、[ **サード パーティ統合**] まで下にスクロールし、[ **SAML 2.0** ] タブを選択します。

    [Image: SAML 2.0]
5. **[Autogenerate Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーを自動生成する)** をオンにします

    [Image: [Autogenerate Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーを自動生成する)]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SAP Litmos サインオン URL にリダイレクトされます。
- SAP Litmos のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SAP Litmos に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SAP Litmos] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SAP Litmos に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/litmus-tutorial"} -->
## Microsoft Entra ID で Litmus for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/litmus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Litmus 間のシングル サインオンを構成する方法について説明します。

この記事では、Litmus と Microsoft Entra ID を統合する方法について説明します。 Litmus を Microsoft Entra ID と統合すると、次のことが可能になります。

- Litmus にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Litmus に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Litmus でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Litmus では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

### ギャラリーからの Litmus の追加

Microsoft Entra ID への Litmus の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Litmus を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Litmus**」と入力します。
4. 結果のパネルから **[Litmus]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Litmus に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Litmus に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと Litmus の関連ユーザー間にリンク関係を確立する必要があります。

Litmus に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Litmus SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Litmus のテスト ユーザーの作成** - Litmus で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Litmus**&gt;**シングルサインオン**にアクセスしてください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://litmus.com/sessions/new`
7. **保存** を選択します。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Litmus のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Litmus SSO を構成する

1. 別の Web ブラウザー ウィンドウで、Litmus 企業サイトに管理者としてサインインします。
2. 左側のナビゲーション パネルから **[セキュリティ** ] を選択します。

    [Image: [セキュリティ] 項目が選択されている画面のスクリーンショット。]
3. **[Configure SAML Authentication](SAML 認証の構成)** セクションで、次の手順に従います。

    [Image: 説明されている値を入力できる [Configure SAML Authentication](SAML 認証の構成) セクションを示すスクリーンショット。]

    a. **[Enable SAML](SAML を有効にする)** トグルをオンに切り替えます。

    b。 プロバイダーに **[Generic](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/汎用)** を選択します。

    c. **[Identity Provider Name](ID プロバイダー名)** の名前を入力します。例: 例: `Azure AD`
4. 次の手順を実行します。

    [Image: スクリーンショットは、説明されている値を入力できるセクションを示しています。]

    a. **[SAML 2.0 エンドポイント (HTTP)]** テキスト ボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    b。 Azure portal からダウンロードした**証明書**ファイルをメモ帳で開き、その内容を **[X.509 Certificate](X.509 証明書)** ボックスに貼り付けます。

    c. [ **SAML 設定の保存] を選択します**。

#### Litmus のテスト ユーザーを作成する

1. 別の Web ブラウザー ウィンドウで、管理者として Litmus アプリケーションにサインインします。
2. 左側のナビゲーション パネルから **[アカウント]** を選択します。

    [Image: [Accounts](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント) 項目が選択されているスクリーンショット。]
3. [ **新しいユーザーの追加] タブを** 選択します。

    [Image: [Add New User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザーの追加) 項目が選択されているスクリーンショット。]
4. **[ユーザーの追加]** セクションで、次の手順を実行します。

    [Image: 説明されている値を入力できる [Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) セクションを示すスクリーンショット。]

    a. **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** テキスト ボックスに、ユーザーのメール アドレスを入力します (**B.Simon@contoso.com** など)。

    b。 **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名前を入力します (この例では **B**)。

    c. **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (この例では **Simon**)。

    d. [ **ユーザーの作成] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Litmus サインオン URL にリダイレクトされます。
- Litmus のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Litmus に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Litmus] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Litmus に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lktransfer-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LKTransfer を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lktransfer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LKTransfer の間でシングル サインオンを構成する方法について説明します。

この記事では、LKTransfer と Microsoft Entra ID を統合する方法について説明します。 LKTransfer と Microsoft Entra ID を統合すると、次のことができます。

- LKTransfer にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して LKTransfer に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LKTransfer でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- LKTransfer では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから LKTransfer を追加する

Microsoft Entra ID への LKTransfer の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に LKTransfer を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「LKTransfer**」と入力します。
4. 結果パネルから **LKTransfer** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LKTransfer の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、LKTransfer に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと LKTransfer の関連ユーザーとの間にリンク関係を確立する必要があります。

LKTransfer に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LKTransfer SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LKTransfer テストユーザーを作成** - Microsoft Entra ID の B.Simon にリンクするために、LKTransfer 内で B.Simon の対応ユーザーを設定します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**LKTransfer**&gt;**シングル サインオン**に参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://auth.lkidentity.com`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://auth.lkidentity.com/sp/ACS.saml2`

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://www.lktransfer.com` |
    | `https://nuance.lktransfer.com` |
    | `https://beta.lktransfer.com` |
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. [ **LKTransfer のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LKTransfer SSO の構成

**LKTransfer** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [LKTransfer サポート チーム](mailto:supportcenter@ellkay.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### LKTransfer テスト ユーザーの作成

このセクションでは、LKTransfer で B.Simon というユーザーを作成します。 [LKTransfer サポート チーム](mailto:supportcenter@ellkay.com)と協力して、LKTransfer プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる LKTransfer のサインオン URL にリダイレクトします。
- LKTransfer のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [LKTransfer] タイルを選択すると、このオプションは LKTransfer のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lms-and-education-management-system-leaf-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に LMS と Education Management System Leaf を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lms-and-education-management-system-leaf-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LMS and Education Management System Leaf の間でシングル サインオンを構成する方法について説明します。

この記事では、LMS と Education Management System Leaf と Microsoft Entra ID を統合する方法について説明します。 LMS and Education Management System Leaf と Microsoft Entra ID を統合すると、次のことができます。

- LMS and Education Management System Leaf にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して LMS and Education Management System Leaf に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- LMS and Education Management System Leaf でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- LMS and Education Management System Leaf では、**SP** initiated SSO がサポートされます。

### ギャラリーからの LMS and Education Management System Leaf の追加

Microsoft Entra ID への LMS and Education Management System Leaf の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に LMS and Education Management System Leaf を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**LMS and Education Management System Leaf**」と入力します。
4. 結果パネルから **[LMS and Education Management System Leaf]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LMS and Education Management System Leaf 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、LMS and Education Management System Leaf に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと LMS and Education Management System Leaf の関連ユーザーの間にリンク関係を確立する必要があります。

LMS and Education Management System Leaf での Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LMS and Education Management System Leaf の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LMS and Education Management System Leaf のテスト ユーザーの作成** - LMS and Education Management System Leaf で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[LMS and Education Management System Leaf]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.leaf-hrm.jp/`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.leaf-hrm.jp/loginusers/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.leaf-hrm.jp/loginusers/sso/1`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[LMS and Education Management System Leaf サポート チーム](mailto:leaf-jimukyoku@insource.co.jp)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. LMS and Education Management System Leaf アプリケーションでは、特定の形式の SAML アサーションが想定されているため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、LMS and Education Management System Leaf では、これがユーザーのメール アドレスにマップされるものと想定します。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Set up LMS and Education Management System Leaf] (LMS and Education Management System Leaf のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LMS and Education Management System Leaf の SSO の構成

**LMS and Education Management System Leaf** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーションの構成からコピーした適切な URL を [LMS and Education Management System Leaf サポート チーム](mailto:leaf-jimukyoku@insource.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### LMS and Education Management System Leaf のテスト ユーザーの作成

1. Leaf システム管理者ユーザーとしてログインします。 **マスター メンテナンス**の **[ユーザー]** タブで、ログイン ID が `leaftest` のユーザーを作成します。
2. マスター メンテナンスの [ユーザー] タブで、[ **SSO 情報の一括登録** ] ボタンを選択します。
3. **登録 CSV** ボタンを選択して登録 CSV をダウンロードします。
4. ダウンロードした CSV を開き、(Leaf) ログイン ID、NameID の形式、認証サーバーを入力して保存します。

    [Image: 登録 CSV のスクリーンショット。]

    [Image: 名前 ID のスクリーンショット。]

    a. `leaftest` 列に  を入力してください。

    b。 [Authentication Server]\(認証サーバー\) 列に、上の図の認証サーバーに対応する値を入力します。

    c. [NameID の形式] 列に、**NameID の形式**に対応する値を入力します。

    d.[NameID] 列に「**leaftest@company。.extension**」と入力します。
5. [ **ファイルの選択** ] ボタンを選択し、先ほど編集した CSV を選択します。
6. **[アップロード]** ボタンを選択します。

注

Leaf に関連付ける方法として、Leaf がリンクされているログイン ID (ユーザー) と、IdP (認証サーバー) が指定されている NameID (ユーザー) および NameID 形式 (形式) を指定します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる LMS および Education Management System Leaf のサインオン URL にリダイレクトされます。
- LMS and Education Management System Leaf のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [LMS および Education Management System Leaf] タイルを選択すると、このオプションは LMS および Education Management System Leaf のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/locus-tutorial"} -->
## Microsoft Entra ID を使用して Locus をシングル サインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/locus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Locus の間のシングル サインオンを構成する方法について説明します。

この記事では、Locus を Microsoft Entra ID と統合する方法について説明します。 Locus は、実世界に対応した、ラスト マイル エクセレンスのための発送管理プラットフォームです。 Locus を Microsoft Entra ID と統合すると、次のことが可能になります。

- Locus へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Locus に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Locus に対する Microsoft Entra シングル サインオンをテスト環境で構成およびテストする。 Locusは**SP**によって開始されるシングルサインオンをサポートします。

### [前提条件]

Microsoft Entra ID を Locus と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Locus のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Locus アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Locus を追加する

Microsoft Entra アプリケーション ギャラリーから Locus を追加して、Locus とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Locus**&gt;**シングルサインオン**に進みます。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    あ． [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:locus-aws-us-east-1:<ConnectionName>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://accounts.locus-dashboard.com/login/callback?connection=<ConnectionName>`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<ClientId>.locus-dashboard.com/#/login/sso?clientId=<ClientId>&connection=<ConnectionName>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [Locus クライアントサポートチーム](mailto:platform-oncall@locus.sh) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### Locus の SSO を構成する

**子**側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[、子のサポート チーム](mailto:platform-oncall@locus.sh)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Locus テスト ユーザーを作成する

このセクションでは、Locus で Britta Simon というユーザーを作成します。 [Locusサポートチーム](mailto:platform-oncall@locus.sh)と協力して、Locusプラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションが、ログイン フローを開始できる場所の、子サインオン URL にリダイレクトされます。
- Locus のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [スゴ] タイルを選択すると、このオプションは、スゴ サインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/logicgate-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に LogicGate を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/logicgate-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-12
- Summary: ユーザー アカウントを LogicGate に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために LogicGate と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[LogicGate](https://www.logicgate.com) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリケ―ションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- LogicGate でユーザーを作成する
- アクセスが不要になったときに LogicGate のユーザーを削除する
- Microsoft Entra ID と LogicGate の間でユーザー属性の同期を維持する
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための[アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を持つ Microsoft Entra ID のユーザー アカウント ([アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- Enterprise プラン以上の有効な LogicGate テナント。
- 管理者アクセス許可がある LogicGate のユーザー アカウント。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と LogicGate 間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように LogicGate を構成する

1. **LogicGate** 管理コンソールにログインします。 右上隅にある **[ホーム** ] タブと [ **プロファイル** の選択] アイコンに移動します。
2. **[Profile](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロファイル)**&gt;**[Access Key](アクセス キー)** に移動します。

    [Image: [Profile](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロファイル) タブ]
3. [ **アクセス キーの生成] を選択します**。

    [Image: [Access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス) タブ]
4. **アクセス キー**をコピーして保存します。この値は、LogicGate アプリケーションの [プロビジョニング] タブの [**シークレット トークン** \*] フィールドに入力されます。

    [Image: [キー] タブ]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから LogicGate を追加する

Microsoft Entra アプリケーション ギャラリーから LogicGate を追加して、LogicGate へのプロビジョニングの管理を開始します。 SSO のために LogicGate を以前に設定していた場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: LogicGate への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて TestApp 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で LogicGate に対する自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **[LogicGate]** を選択します。

    [Image: アプリケーションの一覧の LogicGate のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、LogicGate テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が LogicGate に接続できることを確認します。 接続に失敗した場合は、LogicGate アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から LogicGate に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で LogicGate のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、LogicGate API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | emails[type eq "仕事"].value | 糸 |  |
    | 活動中 | ブール値 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/logicmonitor-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LogicMonitor を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/logicmonitor-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LogicMonitor の間のシングル サインオンを構成する方法について説明します。

この記事では、LogicMonitor と Microsoft Entra ID を統合する方法について説明します。 LogicMonitor を Microsoft Entra ID と統合すると、次のことが可能になります。

- LogicMonitor へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで LogicMonitor に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LogicMonitor でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- LogicMonitor では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの LogicMonitor の追加

Microsoft Entra ID への LogicMonitor の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に LogicMonitor を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「LogicMonitor**」と入力します。
4. 結果パネルから **LogicMonitor** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LogicMonitor に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、LogicMonitor に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと LogicMonitor の関連ユーザーの間にリンク関係を確立する必要があります。

LogicMonitor に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LogicMonitor の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LogicMonitor のテストユーザーを作成し**、Microsoft Entra における B.Simon の対応ユーザーを LogicMonitor にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[LogicMonitor]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.logicmonitor.com`

    b。 **[応答 URL] (Assertion Consumer Service URL)** ボックスに、URL を入力します。`https://companyname.logicmonitor.com/santaba/saml/SSO/`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.logicmonitor.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、LogicMonitor クライアント サポート チーム](https://www.logicmonitor.com/contact/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **LogicMonitor のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LogicMonitor の SSO の構成

1. **LogicMonitor** 企業サイトに管理者としてログインします。
2. 上部のメニューで、[ **設定]** を選択します。

    [Image: 設定]
3. 左側のナビゲーション バーで、[ **シングル サインオン**] を選択します。

    [Image: シングル サインオン]
4. [ **シングル サインオン (SSO) 設定** ] セクションで、次の手順に従います。

    [Image: 単一 Sign-On 設定]

    a. [ **シングル サインオンを有効にする] を選択します**。

    b。 [**既定の役割の割り当て**] には [**読み取り専用**] を選択します。

    c. ダウンロードしたメタデータ ファイルをメモ帳で開き、そのファイルの内容を **[ID プロバイダー メタデータ** ] ボックスに貼り付けます。

    d. [ **変更の保存] を選択します**。

#### LogicMonitor のテスト ユーザーの作成

Microsoft Entra ユーザーがサインインできるようにするには、Azure Active Directory ユーザー名を使用して、Microsoft Entra ユーザーを LogicMonitor アプリケーションにプロビジョニングする必要があります。

**ユーザー プロビジョニングを構成するには、次の手順を実行します。**

1. LogicMonitor の企業サイトに管理者としてログインします。
2. 上部のメニューで、[ **設定]** を選択し、[ **ロールとユーザー**] を選択します。

    [Image: [Roles and Users (ロールとユーザー)]]
3. **追加**を選択します。
4. [ **アカウントの追加** ] セクションで、次の手順を実行します。

    [Image: アカウントを追加する]

    a. プロビジョニングする Microsoft Entra ユーザーの **ユーザー名**、 **電子メール**、 **パスワード**、 **および再入力パスワード** の値を関連するテキスト ボックスに入力します。

    b。 **[ロール]**、[**アクセス許可の表示**]、および **[状態]** を選択します。

    c. [ **送信] を選択します**。

注

LogicMonitor から提供されている他の LogicMonitor ユーザー アカウント作成ツールや API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる LogicMonitor のサインオン URL にリダイレクトされます。
- LogicMonitor のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [LogicMonitor] タイルを選択すると、SSO を設定した LogicMonitor に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/logzio-cloud-observability-for-engineers-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Logz.io を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/logzio-cloud-observability-for-engineers-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Logz.io - Microsoft Entra 統合の間のシングル サインオンを構成する方法について説明します。

### Logz.io - Azure portal 統合のシングル サインオン (SSO)

Logz.io は Azure Marketplace との統合を提供します。 このトピックは、管理者が Logz.io - Azure portal 統合の SSO を設定するためのガイダンスとなります。これにより、Microsoft Azure Marketplace 経由で Logz.io リソースにアクセスするユーザーのための SSO リンクが有効になります。

#### メリット

SSO を介した Logz.io Azure リソースへのアクセスをユーザーに提供する利点は次の通りです。

- ユーザーごとに一意のユーザー名とパスワードを事前に定義する必要はありません。SSO リンクを持つすべてのユーザーがアプリケーションにサインインできます。
- ユーザー制御の向上: ユーザーを Azure アカウントで定義して、SSO リンクを使用できるようにする必要があります。

Logz.io の Azure リソースを設定する前に、SSO 接続を準備してください。 リソースを設定するには、このプロセスで作成した資格情報が必要です。

#### Microsoft Entra ID で Logz.io リソースの SSO 接続を作成する

SSO を使用して Azure リソースから Logz.io アカウントに接続できるようにするために、Microsoft Entra エンタープライズ アプリケーションを作成します。

#### 前提条件:

開始するには、以下の特権が必要です。

- Microsoft Entra ID へのアクセス
- 新しいエンタープライズ アプリケーションを作成するためのアクセス許可
- Logz.io リソースを作成する Azure サブスクリプションの所有者ロールのアクセス許可

Logz.io-Azure 統合リソース用に作成された SSO リンクにアクセスして使用できるようにするには、関連付けられている Azure アカウントでユーザーを定義する必要があります。

##### Logz.io - Azure portal リソースの SSO リンクを設定する

###### ギャラリーから Logz.io - Microsoft Entra 統合を追加する

Azure portal で Logz.io リソースの SSO を構成するには、Logz.io - Microsoft Entra 統合をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Logz.io - Microsoft Entra Integration**」と入力します。
4. 結果のパネルから **[Logz.io - Microsoft Entra Integration** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。
5. 統合の名前を関連する名前に変更し、[ **作成**] を選択します。 (次の手順では、 **logz.io リソースに App** という名前を使用しました)

    [Image: 統合の名前を変更する]

###### アプリケーション ID をコピーする

**logz.io リソースのアプリで |概要**&gt;**Properties**、**アプリケーション ID** プロパティをコピーします。

[Image: アプリケーション ID のコピー]

###### Microsoft Entra SSO を構成する

1. **logz.io リソースのアプリ | オーバービュー &gt; はじめに**、**2. シングルサインオンを設定**して、**スタート**を選択して**シングルサインオン**を開きます。

    [Image: SSO の設定]
2. **logz.ioリソースのアプリでシングルサインオンを行い**、**SAML**メソッドを選択します。

    [Image: SAML SSO の方法を選択する]

###### 基本的な SAML 構成

1. **logz.io リソースのアプリで |SAML ベースのサインオンで**、[**編集**] を選択して [**基本的な SAML 構成]** パネルを開きます。

    [Image: 基本的な SAML の編集]
2. [ **識別子 (エンティティ ID)]** テキスト ボックスに、パターン `urn:auth0:logzio:*`を使用して値を入力します。 `*` を手順 2. でコピーした **アプリケーション ID** に置き換え、[ **既定** ] オプションを選択します。
3. **応答 URL (Assertion Consumer Service URL)**ボックスに、次の形式で URL を入力します。`https://logzio.auth0.com/login/callback?connection=`を手順 2 でコピーした`CONNECTION_NAME`に置き換えてください。
4. パネルの上部にある **[保存]** を選択します。

    [Image: SML の設定]

###### ユーザー割り当てオプションを構成する

**logz.io リソースのアプリで|[プロパティ] ([&gt;プロパティの管理)]**、[**ユーザーの割り当てが必要ですか?**] を **[いいえ**] に設定し、[**保存]** を選択します。 この手順により、SSO リンクにアクセスできるユーザーが Microsoft Azure portal を介して Logz.io にサインインできるようになり、Active Directory で各ユーザーを事前に定義する必要はありません。

このオプションにより、Active Directory で定義されているユーザーが SSO リンクを使用できるようになり、先ほど作成した AD アプリから各ユーザーに対して特定のアクセス権を定義する必要はありません。

このオプションを構成したくない場合、組織は Logz.io に対する特定のアクセス権を各ユーザーに割り当てる必要があります。

[Image: ユーザーの割り当てが不要]

#### Microsoft Entra ID を介して Logz.io リソースの SSO を有効にする

Logz.io アカウントを作成するときに、Logz.io リソース用に作成したアプリを使用して、Microsoft Entra ID によるシングル サインオンを有効にします。

Azure portal の Logz.io アカウントで、[ **Sigle のサインオン** ] タブを選択します。 **[選択済み]** の Logz.io Microsoft Entra アプリ のリソース名は、入力時に自動的に設定されます。

SSO リンクは、Logz.io リソースにサインインすると表示されます。  Logz.io でアカウントにアクセスするリンクを選択します。

Logz.io リソースの作成時に SSO を構成しない場合は、後で [シングル サインオン] ブレードで構成できます。

ログが Logz.io に送信されるように、Azure でログを構成する必要があります。

[Image: Logz.io へのワンクリック SSO]

### 既存の Logz.io アカウントに対する Microsoft Entra シングル サインオン

このセクションでは、Logz.io - Microsoft Entra Integration と Microsoft Entra ID を統合する方法について説明します。 Logz.io - Microsoft Entra 統合と Microsoft Entra ID をすると、次のことができます。

- Logz.io - Microsoft Entra 統合にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って、Logz.io - Microsoft Entra 統合に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

#### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Logz.io - Microsoft Entra 統合でのシングル サインオンが有効なサブスクリプション。

#### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Logz.io - Microsoft Entra Integration では、 **IDP** Initiated SSO がサポートされます。

#### ギャラリーから Logz.io - Microsoft Entra 統合を追加する

Microsoft Entra ID への Logz.io - Microsoft Entra 統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Logz.io - Microsoft Entra 統合を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Logz.io - Microsoft Entra Integration**」と入力します。
4. 結果のパネルから **[Logz.io - Microsoft Entra Integration** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

#### Logz.io - Microsoft Entra 統合 に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Logz.io - Microsoft Entra Integration に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Logz.io - Microsoft Entra 統合の関連ユーザーとの間にリンク関係を確立する必要があります。

Logz.io - Microsoft Entra 統合に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Logz.io の構成 - Microsoft Entra Integration SSO**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Logz.io - Microsoft Entra Integration テスト ユーザーの作成** - Logz.io - Microsoft Entra Integration で B.Simon に対応するユーザーを作成し、Microsoft Entra におけるB.Simon のユーザー表現と関連付けます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップを実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Logz.io - Microsoft Entra 統合]**&gt;**[シングル サインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次の手順に従います。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:logzio:CONNECTION-NAME`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://logzio.auth0.com/login/callback?connection=CONNECTION-NAME`

    Note

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Logz.io - Microsoft Entra Integration クライアント サポート チーム](mailto:help@logz.io) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Logz.io - Microsoft Entra 統合アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
7. その他に、Logz.io - Microsoft Entra 統合アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | セッションの有効期限 | ユーザーセッションの有効期限切れ |
    | メール | User.mail |
    | グループ | ユーザー.グループ |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Logz.io - Microsoft Entra Integration のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

##### Microsoft Entra テスト ユーザーを作成する

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

##### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に Logz.io - Microsoft Entra Integration へのアクセスを許可することで、シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Logz.io - Microsoft Entra 統合]** を参照します。
3. アプリの概要ページで、[ **管理** ] セクションを見つけて、[ **ユーザーとグループ**] を選択します。
4. [**ユーザーの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

#### Logz.io - Microsoft Entra 統合の SSO を構成する

**Logz.io - Microsoft Entra Integration** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Logz.io - Microsoft Entra Integration サポート チーム](mailto:help@logz.io)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

##### Logz.io Microsoft Entra 統合のテスト ユーザーを作成する

このセクションでは、Logz.io - Microsoft Entra 統合で Britta Simon というユーザーを作成します。 [Logz.io - Microsoft Entra Integration サポート チーム](mailto:help@logz.io)と連携して、Logz.io - Microsoft Entra Integration プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Logz.io Microsoft Entra Integration に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Logz.io Microsoft Entra Integration] タイルを選択すると、SSO を設定した Logz.io Microsoft Entra Integration に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。

#### 関連コンテンツ

Logz.io Microsoft Entra 統合を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスを拡張したものです。 [Microsoft Defender for Cloud Apps でセッション制御を適用する方法について説明します](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-aad)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/looker-analytics-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Looker Analytics Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/looker-analytics-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Looker Analytics Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Looker Analytics Platform と Microsoft Entra ID を統合する方法について説明します。 Looker Analytics Platform を Microsoft Entra ID を統合すると、次のことができます。

- Looker Analytics Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Looker Analytics Platform に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

Looker Analytics Platform は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Looker Analytics Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Looker Analytics Platform では、**SP 開始の SSO と IDP 開始の SSO** がサポートされます。
- Looker Analytics Platform では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Looker Analytics Platform の追加

Microsoft Entra ID への Looker Analytics Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Looker Analytics Platform を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに「**Looker Analytics Platform**」と入力します。
4. 結果パネルから **Looker Analytics Platform** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Looker Analytics Platform 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Looker Analytics Platform に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Looker Analytics Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

Looker Analytics Platform に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Looker Analytics Platform の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Looker Analytics Platform のテスト ユーザーの作成** - Microsoft Entra のユーザーの表現にリンクされた、Looker Analytics Platform 内の B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Looker Analytics Platform**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 **[SP Entity/IdP Audience]\(SP エンティティ/IdP 対象ユーザー**\) テキスト ボックスに、次のパターンを使用して URL を入力します。`<SPN>_looker`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.looker.com/samlcallback`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.looker.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Looker Analytics Platform クライアント サポート チーム](mailto:support@looker.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Looker Analytics Platform のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Looker Analytics Platform の SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として Looker Analytics Platform Web サイトにサインインします。
2. **Admin**&gt;**Authentication**&gt;**SAML** に移動します

    [Image: SAML オプションのスクリーンショット]
3. コピーした **フェデレーション メタデータ** 情報を **IDP メタデータ** テキスト ボックスに貼り付け、[ **読み込み**] を選択します。

    [Image: メタデータアップロードのスクリーンショット]
4. **[ユーザー属性の設定]** セクションで、次の手順を実行します。

    [Image: ユーザー属性の設定のスクリーンショット]

    ある。 [Email Attr](Email 属性) フィールドに次の値を追加します: `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`

    b。 [Fname Attr](Fname 属性) フィールドに次の値を追加します: `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`

    c. [Lname Attr](Lname 属性) フィールドに次の値を追加します: `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname`

    d. **[Test User Authentication]\(ユーザー認証のテスト**\) で、[**Test SAML Authentication**]\(SAML 認証のテスト\) を選択します。 読み込まれたページに "Server Response Successfully Validated (サーバーの応答が正しく検証されました)" と表示されている場合、SAML 統合のインスタンスは正しく設定されています。

    え [ **設定の保存と適用]** で、 **上記の構成を確認し、グローバルに適用できるようにするボックスをオンにします**。

    f. [ **設定の更新]** ボタンを選択します。

#### Looker Analytics Platform のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Looker Analytics Platform に作成します。 Looker Analytics Platform では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Looker Analytics Platform にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Looker Analytics Platform のサインオン URL にリダイレクトされます。
- Looker Analytics Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Looker Analytics Platform に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [Looker Analytics Platform] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Looker Analytics Platform に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lookout-secure-access-tutorial"} -->
## Microsoft Entra ID を使用して Lookout Secure Access for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lookout-secure-access-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Lookout Secure Access の間でシングル サインオンを構成する方法について説明します。

この記事では、Lookout Secure Access と Microsoft Entra ID を統合する方法について説明します。 Lookout Secure Access と Microsoft Entra ID を統合すると、次のことができます。

- Lookout Secure Access にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Lookout Secure Access に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 概要

Lookout Cloud Security Platform は、インターネットベースの脅威からユーザーを保護し、クラウド アプリケーション、プライベート アプリケーション、Web サイトに格納されているデータを保護する、データ中心のクラウド セキュリティ ソリューションです。

このソリューションでは、クラウド セキュリティの次の重要なコンポーネントがサポートされています。

- Lookout のセキュリティで保護されたインターネット アクセス: Web または Web 以外のインターネット ベースのトラフィックの保護。
- Lookout Secure Private Access: プライベート アプリケーション トラフィックの保護。
- Lookout Secure Cloud Access: クラウド アプリケーション トラフィックの保護。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Lookout SSE サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Lookout Secure Access は、**SP および IDP** により開始される SSO の両方をサポートしています。

### ギャラリーから Lookout Secure Access を追加する

Microsoft Entra ID への Lookout Secure Access の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Lookout Secure Access を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Lookout Secure Access**」と入力します。
4. 結果のパネルから **[TOPdesk - Secure]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Lookout Secure Access の Microsoft Entra SSO の構成とテスト

"**B.Simon**" というテスト ユーザーを使用して、Lookout Secure Access で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Lookout Secure Access の関連ユーザーとの間にリンク関係を確立する必要があります。

Lookout Secure Access で Microsoft Entra SSO を構成してテストするには、次のステップに従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Lookout Secure Access SSO の構成**- アプリケーション側でシングル サインオン設定を構成するため。
    1. **Lookout Secure Access テスト ユーザーの作成** - Lookout Secure Access で B.Simon に対応するユーザーを作成し、そのユーザーを B.Simon の Microsoft Entra ID 表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Lookout Secure Access**&gt;**シングルサインオン** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、**サービス プロバイダー メタデータ ファイル**がある場合は、次の手順に従います。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルのアップロードを示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**と**応答 URL** の値が、[基本的な SAML 構成] セクションに自動的に設定されます。

    注

    識別子と応答 URL の値が自動的に設定されない場合は、要件に従って値を手動で入力します。 **サービス プロバイダー メタデータ ファイル**は、**[Lookout Secure Accessの構成]** セクションから取得できます。

    d. [ **リレー状態** ] ボックスに、Lookout 管理コンソールからコピーした値を貼り付け、[ **保存]** を選択します。
6. Lookout Secure Access アプリケーションでは、特定の形式の SAML アサーションが使用されるため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 **[一意のユーザー識別子 (名前 ID)]** 属性については、名前識別子の形式を **[指定なし]** に手動で設定してください。

    [Image: 要求の管理を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Lookout Secure Access SSO の構成

1. Lookout Secure Access 企業サイトに管理者としてログインします。
2. **管理**&gt;**Enterprise Integration** に移動し、左側のウィンドウから **[シングル サインオン**] を選択します。
3. [ **SSO グループ** ] タブで、既定のグループから **SP メタデータ** のダウンロード アイコンを選択します。 SP メタデータの詳細が表示されたポップアップが表示されます。 [SP メタデータ ファイル] ボタンを選択してファイルをダウンロードし、Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションでアップロードします。

    [Image: メタデータ ファイルのダウンロードを示すスクリーンショット。]
4. **[SSO Providers]** タブに移動し、次のステップを実行します。

    [Image: 構成の設定を示すスクリーンショット。]

    1. **+ 新規** を選択します。
    2. **[名前]** フィールドに有効な名前を入力し、**[ID プロバイダー]** として [種類] を選択します。
    3. Microsoft Entra 管理センターからコピーした **[アプリのフェデレーション メタデータ URL]** を **[メタデータ リンク]** テキスト ボックスに貼り付けます。
    4. **[検証]** を選択します。
    5. **保存** を選択します。
5. **[管理]**&gt;**[システム設定]**&gt;**[エンタープライズ認証]** に戻り、次のステップを実行します。

    [Image: エンタープライズ認証のシステム設定を示すスクリーンショット。]

    1. **[ID プロバイダー]** ドロップダウンから、作成した ID プロバイダーを選択します。
    2. トグルをオンにして、**[管理コンソール]** と**[エンドポイント]** を有効にします。
    3. コピー ボタンを選択して**リレー状態**の値をコピーし、Entra 側の **[基本的な SAML 構成**] セクションの **[リレー状態**] ボックスに貼り付けます。
    4. **保存** を選択します。

#### Lookout Secure Access テスト ユーザーの作成

このセクションでは、Lookout Secure Access に "Britta Simon" というユーザーを作成します。 Lookout Secure Access では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Lookout Secure Access にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始

- Lookout SSE 管理コンソールの URL に直接移動し、そこから IDP フローを使用してログインを開始します。

##### IDP Initiated

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Lookout Secure Access に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Lookout Secure Access タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Lookout Secure Access に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/looop-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Looop を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/looop-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-12
- Summary: Looop に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、Looop と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Looop に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Looop テナント](https://www.looop.co/pricing/)
- 管理者アクセス許可を持つ Looop のユーザー アカウント。

### ユーザーを Looop に割り当てる

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に割り当てという概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Looop へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 特定した後、次の手順に従い、これらのユーザー、グループ、またはその両方を Looop に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Looop に割り当てる際の重要なヒント

- 自動ユーザー プロビジョニング構成をテストするには、1 人の Microsoft Entra ユーザーを Looop に割り当てることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- Looop にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために Looop を設定する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Looop を構成する前に、Looop からプロビジョニング情報を取得する必要があります。

1. Looop 管理コンソールにサインインし、[アカウント] を選択 **します**。 **[アカウントの設定]** で **[認証]** を選択します。

    [Image: Looop 管理者]
2. **SCIM 統合**で [トークンの**リセット**] を選択して、新しいトークンを生成します。

    [Image: Looop トークン]
3. **SCIM エンドポイント**と**トークン**をコピーします。 これらの値は、Looop アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

    [Image: Looop でのトークンの作成]

### ギャラリーからLooopを追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に Looop を構成するには、Looop を Microsoft Entra アプリケーション ギャラリーから管理対象の SaaS アプリケーションの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションに「**Looop**」と入力し、結果パネルの **[Looop]** を選びます。

    [Image: 結果一覧にある Looop]
4. **[Sign-up for Looop](Looop にサインアップ)** ボタンを選択します。Looop のログイン ページにリダイレクトされます。

    [Image: Looop OIDC の追加]
5. Looop は OpenIDConnect アプリであるため、Microsoft の職場アカウントを使用して Looop にログインすることを選択します。

    [Image: Looop OIDC ログイン]
6. 認証に成功した後、同意ページの同意プロンプトを受け入れます。 その後、アプリケーションがテナントに自動的に追加され、Looop アカウントにリダイレクトされます。

### Looop への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Looop でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Looop の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] 画面]
3. アプリケーションの一覧で **[Looop]** を選択します。

    [Image: アプリケーションの一覧の [Looop] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Looop テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Looop に接続できることを確認します。 接続に失敗した場合は、Looop アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Looop に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Looop のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | エクスターナルID | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:Looop:2.0:User:area | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:Looop:2.0:User:custom\_1 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:Looop:2.0:User:custom\_2 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:Looop:2.0:User:custom\_3 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:Looop:2.0:User:location | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:Looop:2.0:User:position | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:Looop:2.0:User:startAt | 糸 |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Meta Networks Connector に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Meta Networks Connector のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ディスプレイ名 | 糸 | ✓ |
    | メンバー | リファレンス |  |
    | エクスターナルID | 糸 |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更履歴

- 07/15/2021 - Enterprise 拡張機能のユーザー属性**urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department**、**urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber** 、および**urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager**が追加されました。
- 07/15/2021-カスタム拡張機能のユーザー属性**urn:ietf:params:scim:schemas:extension:Looop:2.0:User:department**および**urn:ietf:params:scim:schemas:extension:Looop:2.0:User:employee\_id**が削除されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/loop-flow-crm-tutorial"} -->
## Microsoft Entra ID を使用して Loop Flow CRM for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/loop-flow-crm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Loop Flow CRM の間のシングル サインオンを構成する方法について説明します。

この記事では、Loop Flow CRM と Microsoft Entra ID を統合する方法について説明します。 Loop Flow CRM を Microsoft Entra ID と統合すると、次のことが可能になります。

- Loop Flow CRM にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Loop Flow CRM に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Loop Flow CRM でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Loop Flow CRM では、SP 起動型 SSO と IDP 起動型 SSO がサポートされています。

### ギャラリーから Loop Flow CRM を追加する

Microsoft Entra ID への Loop Flow CRM の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Loop Flow CRM を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Loop Flow CRM**」と入力します。
4. 結果パネルから **[Loop Flow CRM** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Loop Flow CRM 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Loop Flow CRM に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Loop Flow CRM の関連ユーザーとの間にリンク関係を確立する必要があります。

Loop Flow CRM に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Loop Flow CRM SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Loop Flow CRM テストユーザーを作成** - Loop Flow CRM で B.Simon に対応するユーザーを作成し、Microsoft Entra内のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Loop Flow CRM**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.loopworks.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.loopworks.com/sso/consume/<CUSTOMER_NAME>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.loopworks.com/sso/<CUSTOMER_NAME>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Loop Flow CRM クライアント サポート チーム](mailto:support@loopworks.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Loop Flow CRM の SSO の構成

**Loop Flow CRM** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Loop Flow CRM サポート チーム](mailto:support@loopworks.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Loop Flow CRM テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Loop Flow CRM に作成します。 [Loop Flow CRM サポート チーム](mailto:support@loopworks.com)と協力して、Loop Flow CRM プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できるループ フロー CRM サインオン URL にリダイレクトされます。
- Loop Flow CRM のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Loop Flow CRM に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Loop Flow CRM] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Loop Flow CRM に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lovable-oidc-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Lovable (OIDC) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lovable-oidc-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-17
- Summary: Microsoft Entra ID と Lovable の間で OIDC ベースのシングル サインオンを構成する方法について説明します。

この記事では、OpenID Connect (OIDC) を使用して Lovable と Microsoft Entra ID を統合する方法について説明します。 Lovable と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra IDを使用して、Lovable にアクセスできるユーザーを制御します。
- ユーザーが自分のMicrosoft Entra アカウントを使用して Lovable にサインインできるようにします。
- Lovable へのアクセスを 1 つの中央の場所で管理します。

Lovable では、Business ワークスペースと Enterprise ワークスペースの OIDC ベースのワークスペース シングル サインオン (SSO) がサポートされています。 Lovable では、サービス プロバイダー (SP) によって開始されるサインオンのみがサポートされます。 ユーザーは Lovable からサインインを開始する必要があります。

### Prerequisites

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Lovable Business または Enterprise のワークスペース。
- Lovable ワークスペースの所有者または管理者のアクセス許可。
- Lovable の検証済みドメイン。

### ギャラリーから Lovable (OIDC) を追加する

Microsoft Entra IDへの Lovable の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Lovable を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Lovable**」と入力します。
4. 結果パネルで **[Lovable** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO を構成する

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Lovable**&gt;**シングル サインオン** に移動します。
3. 次のセクションで以下の手順を実行します。

    1. **[アプリケーションに移動]**を選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID** と**ディレクトリ (テナント) ID をコピーします**。 これらの値は、後で Lovable 構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
    3. アプリケーションで使用されるメタデータ エンドポイントを確認する場合は、[ **エンドポイント**] で **OpenID Connect メタデータ ドキュメント** リンクをコピーします。

        [Image: タブにエンドポイントが表示されているスクリーンショット。]
4. 左側のメニューの **[認証** ] に移動し、次の手順を実行します。

    1. [ **リダイレクト URI** ] ボックスに、Lovable リダイレクト URI を入力します。 `https://auth.lovable.dev/__/auth/handler`

        [Image: リダイレクト値を示すスクリーンショット。]
    2. **設定**を選択します。
5. 左側のメニューで **API のアクセス許可** に移動します。
6. Lovable に必要な Microsoft Graph の委任されたアクセス許可が構成されていることを確認してください。

    - `email`
    - `openid`
    - `profile`
7. 構成されたアクセス許可に対して **[管理者の同意を付与** する] を選択します。
8. 左側のメニューの **[証明書とシークレット** ] に移動し、次の手順を実行します。

    1. [ **クライアント シークレット** ] タブに移動し、[ **新しいクライアント シークレット**] を選択します。
    2. 説明を入力し、有効期限を選択して、[ **追加**] を選択します。
    3. クライアント シークレットの値をコピー **します**。 この値は、後で Lovable 構成で使用します。

Important

クライアント シークレットの値をすぐにコピーします。 ページを離れると、再び表示されません。

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。

    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **を選択して**を作成します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に Lovable へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. アプリケーションの一覧で **[Lovable**] を選択します。
4. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
5. [ **ユーザー/グループの追加]** を選択し、[ **ユーザーとグループ**] を選択します。
6. [ **ユーザーとグループ** ] ダイアログで、ユーザーの一覧から **B.Simon** を選択し、[選択] を **選択**します。
7. [ **割り当ての追加** ] ダイアログで、[ **割り当て**] を選択します。

### Lovable OIDC SSO を設定する

Lovable で OIDC SSO のセットアップを完了するための構成手順を次に示します。

1. ワークスペースの所有者または管理者として [Lovable](https://lovable.dev) にサインインします。
2. ワークスペースの **[設定]** に移動します。 サイドバーの [ **メンバーとアクセス**] で、[ **ID**] を選択します。
3. ドメインがまだ検証されていない場合は、最初に確認します。

    1. [ **確認済みドメイン** ] セクションで、[ **ドメインの追加**] を選択します。
    2. [ **ドメイン** ] ボックスに、ドメイン (たとえば、 `contoso.com`) を入力します。
    3. ページに表示されている TXT レコードを DNS プロバイダーに追加します。 DNS の変更が反映されるまでに数分から最大 72 時間かかる場合があります。
    4. [ **ドメインの確認**] を選択し、ドメインの検証後に [続行] を選択 **します**。
4. [ **SSO プロバイダー** ] セクションで、[ **プロバイダーの追加]** を選択します。

    [Image: プロバイダーの追加を示すスクリーンショット。]
5. [**SSO プロトコルの選択**] ステップで、[**OpenID Connect (OIDC)]** で [**OIDC の構成**] を選択します。

    [Image: [SSO プロトコルの選択] を示すスクリーンショット。]
6. Lovable には、Microsoft Entra IDで既に完了した ID プロバイダーのセットアップ (アプリケーション設定、リダイレクト URI、OAuth スコープ) を要約した準備手順が示されています。 [ **次へ** ] を選択して移動し、[ **IdP を構成しました**] を選択します。
7. [ **OIDC プロバイダーの構成]** ステップで、次の手順を実行します。

    [Image: OIDC プロバイダーの構成を示すスクリーンショット。]

    1. **OIDC 発行者 URL/探索エンドポイント**に、次の URL を入力します。 `{TENANT_ID}`を、Microsoft Entra IDからコピーした**ディレクトリ (テナント) ID** の値に置き換えます。

        `https://login.microsoftonline.com/{TENANT_ID}/v2.0`
    2. **OAuth クライアント ID/アプリケーション ID** に、Microsoft Entra IDからコピーした**アプリケーション (クライアント) ID の**値を貼り付けます。
    3. **OAuth クライアント シークレット**で、Microsoft Entra IDからコピーしたクライアント シークレットの値を貼り付けます。
    4. [ **表示名]** に、認証時にユーザーに表示される名前を入力します。
    5. **[検証済みドメイン**] で、SSO に使用する検証済みドメインを選択します。 SSO ログイン URL はこのドメインに基づいています。
    6. 必要に応じて、[ **ログイン URL サフィックス**] に、SSO ログイン識別子のサフィックスを入力します。 結果の SSO ログイン URL がフィールドの下に表示されます。ユーザーはこの URL を使用して SSO で直接サインインできます。
8. [ **テスト構成]** を選択して OIDC 構成を検証します。
9. **[テスト結果**] ステップで、検証結果を確認し、[**プロバイダーの構成]** を選択します。
10. 確認ページを確認し、 **確認を選択して SSO を有効に** して、Lovable OIDC SSO の構成を完了します。

これで、プロバイダーが **SSO providers** セクションに SSO ログイン URL と共に表示され、ユーザーとコピーして共有できます。

Note

ワークスペースの SSO を適用する前に、6 時間待ってから SSO ログインをテストします。 Lovable では、新しく作成されたプロバイダーに対して、この期間中に **[SSO の適用** ] トグルが無効になります。

### SSO のテスト

Lovable では、SP によって開始されるサインオンのみがサポートされます。 Microsoft Entra アプリケーション タイルからの IdP によって開始されるサインオンはサポートされていません。

SSO をテストするには、Lovable に移動し、SSO サインイン フローを開始します。 Lovable で SSO ログイン識別子を構成した場合、ユーザーは次の URL を使用してサインインを開始することもできます。

`https://lovable.dev/sso-login/{tenantId}`

`{tenantId}`を、Lovable で構成された SSO ログイン識別子に置き換えます。

### Lovable SSO の追加設定

OIDC プロバイダーを構成した後、Lovable ワークスペースの所有者または管理者は **、Settings**&gt;**Workspace**&gt;**Identity** でワークスペースに SSO を適用できます。 SSO が適用されると、ワークスペース メンバーは SSO で認証する必要があります。

Lovable では、SSO による Just-In-Time (JIT) プロビジョニングがサポートされます。 ユーザー アカウントは、ユーザーが SSO で初めてサインインすると自動的に作成され、会社のワークスペースに追加されます。 Lovable では、Enterprise プランでの SCIM プロビジョニングもサポートされています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lr-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LoginRadius を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LoginRadius の間のシングル サインオンを構成する方法について説明します。

この記事では、LoginRadius と Microsoft Entra ID を統合する方法について説明します。 LoginRadius を Microsoft Entra ID と統合すると、以下のことが可能になります。

- 誰が LoginRadius にアクセスできるかを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して LoginRadius に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LoginRadius でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- LoginRadius では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの LoginRadius の追加

LoginRadius の Microsoft Entra ID への統合を構成するには、LoginRadius をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**LoginRadius**」と入力します。
4. 結果のパネルから **[LoginRadius]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LoginRadius の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、LoginRadius での Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと LoginRadius の関連ユーザーとの間にリンク関係を確立する必要があります。

LoginRadius で Microsoft Entra SSO を構成してテストするには、以下の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LoginRadius SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LoginRadius テストユーザーの作成** - Microsoft Entra ユーザーである B.Simon に対応するユーザーを LoginRadius に作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**LoginRadius**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. **[識別子 (エンティティ ID)]** ボックスに URL (`https://lr.hub.loginradius.com/`) を入力します。
    2. **[応答 URL (Assertion Consumer Service URL)]** ボックスに、LoginRadius の ACS URL (`https://lr.hub.loginradius.com/saml/serviceprovider/AdfsACS.aspx`) を入力します。
    3. **[サインオン URL]** ボックスに URL (`https://secure.loginradius.com/login`) を入力します。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[LoginRadius のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LoginRadius SSO の構成

このセクションでは、LoginRadius 管理コンソールで Microsoft Entra シングル サインオンを有効にします。

1. LoginRadius [管理コンソール](https://adminconsole.loginradius.com/login) アカウントにログインします。
2. **LoginRadius 管理コンソール**の [\[Team Management\](チーム管理)](https://www.loginradius.com/legacy/docs/api/v2/admin-console/overview/) セクションに移動します。
3. **[シングル サインオン]** タブを選択してから、**[Microsoft Entra ID]** を選択します。

    [Image: LoginRadius の [Team Management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/チーム管理) コンソールにあるシングル サインオン メニューを示すスクリーンショット]
4. Microsoft Entra の設定ページで、以下の手順を実行します。

    [Image: LoginRadius チーム管理コンソールでの Microsoft Entra の構成を示すスクリーンショット]

    1. **[ID プロバイダーの場所]** で、Microsoft Entra アカウントから取得したサインオン エンドポイントを入力します。
    2. **[ID プロバイダー ログアウト URL]** に、Microsoft Entra アカウントから取得したサインアウト エンドポイントを入力します。
    3. **[ID プロバイダー証明書]** で、Microsoft Entra アカウントから取得した Microsoft Entra 証明書を入力します。 ヘッダーとフッターを付けて証明書の値を入力します。 例: `-----BEGIN CERTIFICATE-----<certificate value>-----END CERTIFICATE-----`
    4. **[Service Provider Certificate](サービス プロバイダー証明書)** と **[Server Provider Certificate Key](サーバー プロバイダー証明書キー)** に、自分の証明書とキーを入力します。

        自己署名証明書は、コマンド ラインから次のコマンドを実行して作成できます (Linux または Mac)。

        - SP の証明書キーを取得するコマンド: `openssl genrsa -out lr.hub.loginradius.com.key 2048`
        - SP の証明書を取得するコマンド: `openssl req -new -x509 -key lr.hub.loginradius.com.key -out lr.hub.loginradius.com.cert -days 3650 -subj /CN=lr.hub.loginradius.com`

        注

        証明書と証明書キーの値は、必ずヘッダーとフッターを付けて入力してください。

        - 証明書の値の形式 (入力例): `-----BEGIN CERTIFICATE-----<certificate value>-----END CERTIFICATE-----`
        - 証明書キーの値の形式 (入力例): `-----BEGIN RSA PRIVATE KEY-----<certificate key value>-----END RSA PRIVATE KEY-----`
5. **[データ マッピング]** セクションで、フィールド (SP フィールド) を選択し、対応する Microsoft Entra ID フィールド (IdP フィールド) を入力します。

    Microsoft Entra ID のフィールド名一覧の一部を次に示します。

    | 田畑 | プロファイル キー |
    | --- | --- |
    | Email | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress` |
    | ファーストネーム | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname` |
    | 苗字 | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname` |

    注

    **Email** フィールドのマッピングは必須です。 **FirstName** フィールドと **LastName** フィールドのマッピングは省略可能です。

#### LoginRadius のテスト ユーザーの作成

1. LoginRadius [管理コンソール](https://adminconsole.loginradius.com/login) アカウントにログインします。
2. LoginRadius 管理コンソールのチーム管理セクションに移動します。

    [Image: LoginRadius 管理コンソールを示すスクリーンショット]
3. サイド メニューの **[Add Team Member](チーム メンバーの追加)** を選択してフォームを開きます。
4. **[Add Team Member](チーム メンバーの追加)** フォームで、LoginRadius サイトに Britta Simon というユーザーを作成します。このユーザーの詳細を入力し、必要なアクセス許可を割り当ててください。 ロールに基づくアクセス許可の詳細については、LoginRadius の「[チーム メンバーの管理](https://www.loginradius.com/docs/)」ドキュメントの「[ロールのアクセス権](https://www.loginradius.com/docs/)」セクションを参照してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、MyApps を使用して Microsoft Entra シングル サインオン構成をテストします。

1. ブラウザーで https://accounts.loginradius.com/auth.aspx に移動し、 **[Fed SSO log in](フェデレーション SSO ログイン)** を選択します。
2. ご利用の LoginRadius アプリの名前を入力し、 **[Login](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ログイン)** を選択します。
3. これによって Microsoft Entra アカウントへのサインインを求めるポップアップが表示されるはずです。
4. 認証が完了すると、ポップアップが閉じられ、LoginRadius 管理コンソールにログインします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lucid-all-products-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Lucid (すべての製品) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lucid-all-products-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-12
- Summary: Microsoft Entra ID から Lucid (すべての製品) に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Lucid (すべての製品) と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Lucid (すべての製品)](https://lucid.co/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Lucid (すべての製品) でユーザーを作成する。
- アクセスが不要になった場合は、Lucid (すべての製品) のユーザーを削除します。
- Microsoft Entra ID と Lucid (すべての製品) の間でユーザー属性の同期を維持します。
- Lucid (すべての製品) でグループとグループ メンバーシップをプロビジョニングする。
- Lucid (すべての製品) への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lucid-tutorial) (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者権限を持つ Lucid (すべての製品) のユーザー アカウント。
- 最新の料金プランのエンタープライズ アカウントを利用していることを確認します。 アップグレードするには、営業チームにお問い合わせください。
- アカウントの SCIM を有効にできるように、Lucidchart Customer Success Manager に連絡してください。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Lucid (すべての製品) の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID でプロビジョニングをサポートするように Lucid (すべての製品) を構成する

1. [Lucid 管理コンソール](https://lucid.app/)にログインします。 **[管理者]** に移動します。
2. 左側のメニューで [ **アプリ統合** ] を選択します。
3. **SCIM** タイルを選択します。
4. [ **トークンの生成]** を選択します。 Lucid は、Azure.Copy と共有し、 **ベアラー トークン** を保存するための一意のコードを **Bearer Token** テキスト フィールドに設定します。 この値は、Lucid(All Products) アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** \*] フィールドに入力されます。

    [Image: トークン生成のスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Lucid (すべての製品) を追加する

Microsoft Entra アプリケーション ギャラリーから Lucid (すべての製品) を追加し、Lucid (すべての製品) へのプロビジョニングの管理を開始します。 SSO のために Lucid (すべての製品) を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Lucid (すべての製品) への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて Lucid (すべての製品) のユーザーやグループを作成、更新、無効化するよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Lucid (すべての製品) の自動ユーザー プロビジョニングを構成するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Lucid (すべての製品)** を選択します。

    [Image: アプリケーションの一覧の Lucid (すべての製品) リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Lucid (すべての製品) テナント URL とシークレット トークンを入力します。 [ **テスト接続** ] を選択して、Microsoft Entra ID が Lucid (すべての製品) に接続できることを確認します。 接続に失敗した場合は、Lucid (すべての製品) アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Lucid (すべての製品) に同期されるユーザー **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Lucid (すべての製品) のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Lucid (すべての製品) API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Lucid (すべての製品) で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:lucid:2.0:User:billingCode | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:lucid:2.0:User:productLicenses.Lucidchart | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:lucid:2.0:User:productLicenses.Lucidspark | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:lucid:2.0:User:productLicenses.LucidscaleExplorer | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:lucid:2.0:User:productLicenses.LucidscaleCreator | 糸 |  |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から Lucid (すべての製品) に同期されるグループ **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作の Lucid (すべての製品) のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Lucid (すべての製品) で必須 |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lucid-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Lucid (すべての製品) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lucid-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Lucid (All Products) の間でシングル サインオンを構成する方法について説明します。

この記事では、Lucid (すべての製品) と Microsoft Entra ID を統合する方法について説明します。 Lucid (All Products) を Microsoft Entra ID と統合すると、次のことが可能になります。

- Lucid (All Products) にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Lucid (All Products) に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

Lucid は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Lucid (All Products) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Lucid (All Products) では、**SP および IDP** Initiated SSO がサポートされます。
- Lucid (All Products) では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Lucid (All Products) の追加

Microsoft Entra ID への Lucid (All Products) の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Lucid (All Products) を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Lucid (All Products)** 」と入力します。
4. 結果のパネルから **[Lucid (All Products)]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Lucid (All Products) 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Lucid (All Products) に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するには、Microsoft Entra ユーザーとそれに対応する Lucid (All Products) ユーザーをリンクする必要があります。

Lucid (All Products) に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Lucid (All Products) の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Lucid (All Products) のテスト ユーザーの作成** - Lucid (All Products) で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Lucid (All Products)]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://lucid.app/saml/sso/<TENANT_NAME>?idpHash=<HASH_ID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://lucid.app/saml/sso/<TENANT_NAME>?idpHash=<HASH_ID>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには、[Lucid (All Products) クライアント サポート チーム](mailto:support@lucidchart.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Lucid (All Products) のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Lucid (All Products) の SSO の構成

**Lucid (All Products)** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Lucid (All Products) サポート チーム](mailto:support@lucidchart.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Lucid (All Products) のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Lucid (All Products) に作成します。 Lucid (All Products) では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Lucid (All Products) にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Lucid (すべての製品) のサインオン URL にリダイレクトされます。
- Lucid (All Products) のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Lucid (すべての製品) に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Lucid (すべての製品)] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Lucid (All Products) に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lucidchart-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Lucidchart を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lucidchart-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: ユーザー アカウントを Lucidchart に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Lucidchart と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Lucidchart](https://www.lucidchart.com/user/117598685#/subscriptionLevel) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリケ―ションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- Lucidchart でユーザーを作成する
- アクセスが不要になった場合に Lucidchart のユーザーを削除する
- Microsoft Entra ID と Lucidchart の間でユーザー属性の同期を維持する
- Lucidchart でグループとグループ メンバーシップをプロビジョニングする
- Lucidchart への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lucidchart-tutorial) (推奨)

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための[アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を持つ Microsoft Entra ID のユーザー アカウント ([アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- [Enterprise プラン](https://www.lucidchart.com/user/117598685#/subscriptionLevel)以上の有効な LucidChart テナント。
- Admin アクセス許可がある LucidChart のユーザー アカウント。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Lucidchart 間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように Lucidchart を構成する

1. [Lucidchart 管理コンソール](https://lucid.app/)にログインします。 **[Team] (チーム) &gt; [App Integration] (アプリの統合)** に移動します。

    [Image: Lucidchart 管理コンソールのスクリーンショット。[Team](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/チーム) メニューが強調表示され、開かれています。[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) の下の [App Integration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリの統合) が強調表示されています。]
2. **[SCIM]** に移動します。

    [Image: Lucidchart 管理コンソールのスクリーンショット。大きな [SCIM] ボタンの中でテキスト [SCIM] が強調表示されており、[enabled](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/有効) バナーが表示されています。]
3. 下にスクロールして **[Bearer token] (ベアラー トークン)** と **[Lucidchart Base URL] (Lucidchart ベース URL)** を表示します。 **[Bearer token] (ベアラー トークン)** をコピーして保存します。 この値は、LucidChart アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: Lucidchart トークン]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Lucidchart を追加する

Microsoft Entra アプリケーション ギャラリーから Lucidchart を追加して、Lucidchart へのプロビジョニングの管理を開始します。 SSO のために Lucidchart を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Lucidchart への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて TestApp 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Lucidchart に対する自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **[Lucidchart]** を選択します。

    [Image: アプリケーションの一覧の [Lucidchart] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Lucidchart テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Lucidchart に接続できることを確認します。 接続に失敗した場合は、Lucidchart アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Lucidchart に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Lucidchart のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Lucidchart API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | emails[type eq "work"].value | 糸 |
    | 活動中 | ブール値 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 関連項目 |
    | urn:ietf:params:scim:schemas:extension:lucidchart:1.0:User:canEdit | ブール値 |
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Lucidchart に同期されるグループ属性を確認します。 **[Matching] (照合)** プロパティとして選択されている属性は、更新処理で Lucidchart のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | members | 関連項目 |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### ログの変更

- 2020 年 4 月 30 日 - エンタープライズ拡張属性とユーザーのカスタム属性 "CanEdit" のサポートを追加しました。
- 2020/06/15 - ユーザーの論理的な削除が有効になりました ([active](https://tools.ietf.org/html/rfc7643) 属性のサポート)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lucidchart-tutorial"} -->
## Microsoft Entra ID で Lucidchart for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lucidchart-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Lucidchart の間でシングル サインオンを構成する方法について説明します。

この記事では、Lucidchart と Microsoft Entra ID を統合する方法について説明します。 Lucidchart と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Lucidchart へのアクセス権を管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Lucidchart に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Lucidchart でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Lucidchart は、SP **によって開始された SSO** をサポートしています。
- Lucidchart では、[**自動** ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lucidchart-provisioning-tutorial) がサポートされます (推奨)。
- Lucidchart では、**Just In Time** ユーザー プロビジョニングをサポートしています。

### ギャラリーから Lucidchart を追加する

Microsoft Entra ID への Lucidchart の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Lucidchart を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリーから **追加する**] セクションで、検索ボックスに「**Lucidchart**」と入力します。
4. 結果のパネルから Lucidchart  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Lucidchart の Microsoft Entra SSO の構成とテスト

B.Simon **というテスト ユーザーを使用して、Lucidchart に対する Microsoft Entra SSO**構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Lucidchart の関連ユーザーとの間にリンク関係を確立する必要があります。

Lucidchart で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Lucidchart SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    - **Lucidchart テストユーザーを作成** - Microsoft Entra の B.Simon に対応するユーザーを Lucidchart で作成し、リンクする予定です。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Lucidchart**&gt;**シングルサインオン**に移動します。
3. [**シングル サインオン方法の選択]** ページで、[**SAML**] を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集]
5. [**基本的な SAML 構成**] セクションで、次のフィールドの値を入力します。

    [**サインオン URL** テキスト ボックスに、URL を次のように入力 `https://chart2.office.lucidchart.com/saml/sso/azure`
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [**Lucidchart** のセットアップ] セクションで、必要に応じて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Lucidchart SSO の構成

1. 別の Web ブラウザー ウィンドウで、Lucidchart 企業サイトに管理者としてログインします。
2. 上部のメニューで、[ **チーム**] を選択します。

    [Image: チーム]
3. **アプリケーション**&gt;を選択し、**SAMLを管理**します。

    [Image: SAML] の管理
4. [**SAML 認証設定**] ダイアログ ページで、次の手順を実行します。

    ある。 [ **SAML 認証を有効にする**] を選択し、[ **省略可能**] を選択します。

    SAML 認証設定 [Image: SAML 認証設定]

    b。 [ **ドメイン** ] ボックスにドメインを入力し、[ **証明書の変更**] を選択します。

    証明書 [Image: 変更証明書]

    c. ダウンロードしたメタデータ ファイルを開き、コンテンツをコピーして、**[Upload Metadata**] ボックスに貼り付けます。

    [Image: メタデータのアップロード]

    d. [ **新しいユーザーをチームに自動的に追加する**] を選択し、[ **変更の保存]** を選択します。

    [Image: 変更を保存]

#### Lucidchart テスト ユーザーの作成

Lucidchart へのユーザー プロビジョニングを構成するためのアクション項目はありません。 割り当てられたユーザーがアクセス パネルを使用して Lucidchart にログインしようとすると、Lucidchart はユーザーが存在するかどうかを確認します。

使用可能なユーザー アカウントがまだない場合は、Lucidchart によって自動的に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Lucidchart のサインオン URL にリダイレクトされます。
- Lucidchart のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Lucidchart] タイルを選択すると、SSO を設定した Lucidchart に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lusha-tutorial"} -->
## Microsoft Entra ID で Lusha for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lusha-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Lusha の間のシングル サインオンを構成する方法について説明します。

この記事では、Lusha と Microsoft Entra ID を統合する方法について説明します。 Lusha は、営業・マーケティング・採用チームがより少ない工数で営業を加速できるよう、即時かつ正確な連絡先や企業データを提供する、営業インテリジェンス ソリューションです。 Lusha を Microsoft Entra ID と統合すると、次のことが可能になります。

- Lusha にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Lusha に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Lusha に対する Microsoft Entra のシングル サインオンをテスト環境で構成・テストする。 Lusha では、 **SP** と **IDP** によって開始されるシングル サインオンの両方がサポートされ、 **Just In Time** ユーザー プロビジョニングもサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Lusha と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Lusha でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Lusha アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Lusha を追加する

Microsoft Entra アプリケーション ギャラリーから Lusha を追加し、Lusha に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Lusha**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** Initiated SSO を構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://auth.lusha.com/sso-login`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### Lusha SSO を構成する

**Lusha** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Lusha サポート チーム](mailto:support@lusha.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Lusha のテスト ユーザーを作成する

このセクションでは、Lusha で B.Simon というユーザーを 作成します。 Lusha では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Lusha にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる Lusha のサインオン URL にリダイレクトされます。
- Lusha のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Lusha に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Lusha] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Lusha に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->
