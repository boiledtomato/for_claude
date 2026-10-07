# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 15)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 76

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lusid-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に LUSID を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lusid-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から LUSID に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を説明します。

この記事では、自動ユーザー プロビジョニングを構成するために LUSID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [LUSID](https://www.finbourne.com/lusid) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- LUSID でユーザーを作成する。
- アクセスが不要になった場合は、LUSID のユーザーを削除します。
- Microsoft Entra ID と LUSID の間でユーザー属性の同期を維持する
- LUSIDでグループとグループメンバーシップを設定する。
- LUSID への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lusid-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- SCIM の LUSID ライセンス (LUSID サポートにお問い合わせください)。
- **lusid-administrator** ロールを持つ LUSID ドメイン内のユーザー アカウント

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と LUSID の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように LUSID を構成する

[アクセス トークンを](https://support.lusid.com/knowledgebase/article/KA-01654/)生成した後、LUSID の [AddScim](https://www.lusid.com/identity/swagger/index.html) エンドポイントに要求を行います。

```
curl --request PUT 'https://<your-lusid-domain>.lusid.com/identity/api/identityprovider/scim' \
--header 'Authorization: Bearer <your-API-access-token>'
```

応答には、後で LUSID Microsoft Entra アプリに入力する `baseUrl` (Microsoft Entra ID の**テナント URL** ) と `apiToken` (Microsoft Entra ID の**シークレット トークン** ) が含まれます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから LUSID を追加する

Microsoft Entra アプリケーション ギャラリーから LUSID を追加して、LUSID へのプロビジョニングの管理を開始します。 SSO のために LUSID を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: LUSID への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で LUSID の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**をブラウズする

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **LUSID** を選択します。

    [Image: アプリケーションの一覧の LUSID リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、LUSID テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が LUSID に接続できることを確認します。 接続に失敗した場合は、LUSID アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. 「属性マッピング」セクションで、Microsoft Entra ID から LUSID に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で LUSID のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、LUSID API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | LUSID で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | externalId | 糸 |  | ✓ |
12. 「属性マッピング」セクションで、Microsoft Entra ID から LUSID に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で LUSID 内のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | LUSID で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  |  |
    | members | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lusid-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LUSID を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lusid-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と LUSID 間にシングル サインオンを構成する方法について説明します。

この記事では、LUSID と Microsoft Entra ID を統合する方法について説明します。 LUSID を Microsoft Entra ID と統合すると、次のことができます。

- LUSID にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って LUSID に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- LUSID でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- LUSID では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされています。
- LUSID では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの LUSID の追加

Microsoft Entra ID への LUSID の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに LUSID を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**LUSID**」と入力します。
4. 結果パネルから **[LUSID]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### LUSID 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、LUSID に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと LUSID の関連ユーザーとの間にリンク関係を確立する必要があります。

LUSID に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LUSID の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **LUSID テスト ユーザーを作成** - Microsoft Entra のユーザー表現にリンクする、B.Simon の対応ユーザーを LUSID で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**LUSID**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.okta.com/saml2/service-provider/<ID>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerDomain>.identity.lusid.com/sso/saml2/<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定**] を選択し、次のパターンを使用して [**リレー状態**] テキスト ボックスに URL を入力します。`https://<CustomerDomain>.lusid.com/app/home`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、およびリレー状態 URL でこれらの値を更新します。 これらの値を取得するには、[LUSID サポート チーム](mailto:support@finbourne.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. LUSID アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: LUSID アプリケーションの画像を示すスクリーンショット。]
8. その他に、LUSID アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | fbnグループ | user.assignedroles |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[LUSID のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LUSID SSO の構成

**LUSID** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を、[LUSID サポート チーム](mailto:support@finbourne.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### LUSID のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを LUSID に作成します。 LUSID では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 LUSID にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる LUSID サインオン URL にリダイレクトされます。
- LUSID のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した LUSID に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [LUSID] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した LUSID に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/luum-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Luum を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/luum-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Luum の間でシングル サインオンを構成する方法について説明します。

この記事では、Luum と Microsoft Entra ID を統合する方法について説明します。 Luum と Microsoft Entra ID を統合すると、次のことができます。

- Luum にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Luum に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Luum でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Luum は、**SP と IDP によって開始される** SSO をサポートします。

### ギャラリーからLuumを追加する

Microsoft Entra ID への Luum の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Luum を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Luum**」と入力します。
4. 結果パネル **Luum** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Luum の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Luum に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Luum の関連ユーザーとの間にリンク関係を確立する必要があります。

Luum に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Luum SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Luum のテスト ユーザーの作成** - Luum で、B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Luum**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<CustomerName>.luum.com`

    手記

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 値 [取得するには、Luum クライアント サポート チーム](mailto:support@luum.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. **保存** を選択します。
8. Luum アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    イメージ [Image: image]
9. 上記に加えて、Luum アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 従業員ID | user.employeeid |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Luum SSO の構成

Luum **側** シングル サインオンを構成するには、Luum サポート チーム [に **アプリフェデレーション メタデータ URL**](mailto:support@luum.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Luum テスト ユーザーの作成

このセクションでは、Luum で Britta Simon というユーザーを作成します。 Luum サポート チーム  と連携して、Luum プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Luum サインオン URL にリダイレクトされます。
- Luum のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Luum に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [Luum] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Luum に自動的にサインインされます。 アクセス パネルの詳細については、「[アクセス パネル](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)の概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lynda-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Lynda.com を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lynda-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Lynda.com の間にシングル サインオンを構成する方法について説明します。

この記事では、Lynda.com と Microsoft Entra ID を統合する方法について説明します。 Lynda.com と Microsoft Entra ID を統合すると、次のことができます:

- Lynda.com にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Lynda.com に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Lynda.com でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Lynda.com では、 **SP** Initiated SSO がサポートされます。
- Lynda.com では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Lynda.com を追加する

Microsoft Entra ID への Lynda.com の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Lynda.com を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックス **に「Lynda.com** 」と入力します。
4. 結果パネルから **Lynda.com** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Lynda.com 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Lynda.com に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Lynda.comの関連ユーザーの間にリンク関係を確立する必要があります。

Lynda.com に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Lynda.com SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Lynda.com テストユーザーを作成する** - Lynda.com で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDエンタープライズアプリLynda.comSingle サインオンへ移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.lynda.com/Shibboleth.sso/InCommon?providerId=<url>&target=<url>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値 Lynda.com 取得するには [、クライアント サポート チーム](https://www.linkedin.com/help/lynda/ask) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Lynda.com のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Lynda.com SSO を構成する

**Lynda.com** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を[サポート チーム Lynda.com](https://www.linkedin.com/help/lynda/ask) 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Lynda.com のテスト ユーザーの作成

Lynda.com へのユーザー プロビジョニングを構成するためのアクション項目はありません。 割り当て済みユーザーがアクセス パネルを使用して Lynda.com にログインしようとすると、そのユーザーが存在するかどうかが Lynda.com によって確認されます。

使用可能なユーザー アカウントがまだない場合は、Lynda.com によって自動的に作成されます。

注

他の Lynda.com ユーザー アカウント作成ツールや、Lynda.com から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Lynda.com サインオン URL にリダイレクトされます。
- Lynda.com のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Lynda.com] タイルを選択すると、このオプションは Lynda.com サインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/lyve-cloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Lyve Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/lyve-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Lyve Cloud 間のシングル サインオンを構成する方法について説明します。

この記事では、Lyve Cloud と Microsoft Entra ID を統合する方法について説明します。 Lyve Cloud を Microsoft Entra ID と統合すると、次のことが可能になります。

- Lyve Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Lyve Cloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Lyve Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Lyve Cloud では、**IDP** Initiated SSO がサポートされます。

### ギャラリーから Lyve Cloud を追加する

Microsoft Entra ID への Lyve Cloud の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Lyve Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Lyve Cloud**」と入力します。
4. 結果のパネルから **[Lyve Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Lyve Cloud に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Lyve Cloud に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと Lyve Cloud の関連ユーザー間にリンク関係を確立する必要があります。

Lyve Cloud に対して Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Lyve Cloud SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Lyve Cloud のテスト ユーザーの作成** - Lyve Cloud で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**Lyve Cloud**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<account_id>.console.lyvecloud.seagate.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<account_id>.console.lyvecloud.seagate.com`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Lyve Cloud クライアント サポート チーム](mailto:lyvecloud.support@seagate.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Lyve Cloud の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Lyve Cloud SSO を構成する

**Lyve Cloud** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Lyve Cloud サポート チーム](mailto:lyvecloud.support@seagate.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Lyve Cloud のテスト ユーザーを作成する

このセクションでは、Lyve Cloud で Britta Simon というユーザーを作成します。 [サポート チーム](mailto:lyvecloud.support@seagate.com)と連携して、Lyve Cloud プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Lyve Cloud に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Lyve Cloud] タイルを選択すると、SSO を設定した Lyve Cloud に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/m-files-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に M-Files を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/m-files-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-23
- Summary: Microsoft Entra ID から M-Files にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために M-Files と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [M-Files](https://www.m-files.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- M-Files でユーザーを作成します。
- アクセスが不要になった場合は、M-Files のユーザーを削除します。
- Microsoft Entra ID と M-Files の間でユーザー属性の同期を維持します。
- M-Files でグループとグループ メンバーシップをプロビジョニングします。
- [M-Files へのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/m-files-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- M-Files Cloud サブスクリプション (クラシック クラウドはサポートされていません)。
- M-Files Manageにサブスクリプション管理者アクセスまたはアクセス管理者アクセス権があるユーザーアカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と M-Files の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。
4. 必要なマッピングは事前に定義されていますが、2 つの追加データ フィールドを M-Files にマップできます。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように M-Files を構成する

M-Files でユーザー プロビジョニングを設定する前に、サブスクリプション内のすべてのコンテナーで、M-Files 管理者でユーザー同期が無効になっていることを確認してください。

独自の Entra ID アプリケーションを使用して M-Files Manage でユーザー プロビジョニングを構成したことがある場合、Microsoft Entra アプリケーション ギャラリーから M-Files アプリケーションを使用するように構成を変更することはできません。 代わりに M-Files アプリケーションを使用する場合は、M-Files Manage から既存の構成を削除し、関連する Entra ID アプリケーションを無効または削除します。 次に、次の手順に従って新しい構成を作成します。

1. https://manage.m-files.comで M-Files 管理にログインします。
2. 左側のページ ナビゲーションで、[ **プロビジョニング**] を選択します。
3. **[構成**] タブに移動します。
4. [ **ユーザー プロビジョニングの構成の作成**] で、 **Azure AD ギャラリー アプリ**を選択します。
5. 必要な情報を入力します。
    - **[構成名]** に、構成の一意の名前を入力します。
    - プロビジョニングされたユーザーの **既定のライセンスの種類** を選択します。 プロビジョニングされたすべてのユーザーは、最初にこのライセンスを取得します。 ユーザー グループのライセンスの種類は、ユーザー グループのプロビジョニング後に上位に変更できます。 サブスクリプションに既定のライセンスの種類の使用可能なライセンスが不足している場合、すべてのユーザーはライセンスを取得しません。
6. コピー アイコン ([Image: コピー アイコンのスクリーンショット] ) を選択し、M-Files 管理によって作成されたデータの各部分について、値をメモします。

注

ダイアログを閉じると、クライアント シークレットは他の場所には表示されません。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから M-Files を追加する

Microsoft Entra アプリケーション ギャラリーから M-Files を追加して、M-Files へのプロビジョニングの管理を開始します。 SSO 用に M-Files を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: M-Files への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで M-Files の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **M-Files**] を選択します。

    [Image: アプリケーションの一覧の [M-Files] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、M-Files テナント URL、トークン エンドポイント、クライアント識別子、クライアント シークレットを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が M-Files に接続できることを確認します。 接続に失敗した場合は、入力した値が正しいことを確認してから、やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. **省略可能**: 追加のユーザー情報を同期する場合は、M-Files Manage に同期する 2 つの追加フィールドを定義できます。 この情報は、[**ユーザー**情報] ページの**追加情報 1** と**追加情報 2** に表示されます。

    1. [マッピング] セクション **で** 、[ **Microsoft Entra ユーザーを M-Files に同期する**] を選択します。
    2. [ **属性マッピング** ] セクションで、[ **新しいマッピングの追加]** を選択します。
    3. 次の値を使用します。
        - **マッピングの種類**: **Direct**
        - **ソース属性**: Entra ID 属性を入力します
        - **ターゲット属性**: **urn:ietf:params:scim:schemas:extension:info:2.0:User:info1** または **urn:ietf:params:scim:schemas:extension:info:2.0:User:info2** を選択します
        - **この属性を使用してオブジェクトを照合**する: **いいえ**
        - **このマッピングを適用** **する: 常に**
    4. **OK** を選択します。
    5. **省略可能**: 2 つの追加のデータ フィールドを M-Files 管理に同期するには、他の使用可能なターゲット属性との 2 つ目のマッピングを追加します。

    注

    既定の属性マッピングを変更することはお勧めしません。
11. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
12. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
13. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/m-files-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に M-Files を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/m-files-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と M-Files の間でシングル サインオンを構成する方法について説明します。

この記事では、M-Files と Microsoft Entra ID を統合する方法について説明します。 M-Files を Microsoft Entra ID を統合すると、次のことができます。

- M-Files にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って M-Files に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- M-Files でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- M-Files では、**SP** Initiated SSO がサポートされます。
- M-Files では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/m-files-provisioning-tutorial)。

### ギャラリーからの M-Files の追加

Microsoft Entra ID への M-Files の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に M-Files を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**M-Files**」と入力します。
4. 結果のパネルから **[M-Files]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### M-Files 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、M-Files に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと M-Files の関連ユーザーとの間にリンク関係を確立する必要があります。

M-Files に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **M-Files の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **M-Files テスト ユーザーを作成し**、B.Simon に対応するユーザーを M-Files に設定し、Microsoft Entra の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**M-Files**&gt;**Single サインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantname>.cloudvault.m-files.com/authentication/MFiles.AuthenticationProviders.Core/sso`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantname>.cloudvault.m-files.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[M-Files クライアント サポート チーム](mailto:support@m-files.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[M-Files のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### M-Files の SSO の構成

1. アプリケーション用に構成された SSO を入手するには、[M-Files サポート チーム](mailto:support@m-files.com)に連絡して、ダウンロードしたメタデータを提供してください。

    注

    M-File デスクトップ アプリケーション用に SSO を構成する場合は、次の手順に従います。 M-Files の Web バージョン用に SSO を構成するだけの場合は、追加の手順は必要ありません。
2. 次の手順に従って M-File デスクトップ アプリケーションを構成し、Microsoft Entra ID の SSO を有効にします。 M-Files をダウンロードするには、[M-Files のダウンロード](https://www.m-files.com/customers/product-downloads/download-update-links/)ページに移動します。
3. **[M-Files デスクトップ設定]** ウィンドウを開きます。 その後、 **[追加]** を選択します。

    [Image: [Add](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加) を選択できる [M-Files Desktop Settings](M-Files デスクトップ設定) を示すスクリーンショット。]
4. **[Document Vault Connection Properties] \(資格情報コンテナーの接続プロパティのドキュメント化)** ウィンドウで、次の手順を実行します。

    [Image: 説明されている値を入力できる [Document Vault Connection Properties](ドキュメント コンテナーの接続のプロパティ) を示すスクリーンショット。]

    [サーバー] セクションで、次のように値を入力します。

    a。 **[名前]** は「`<tenant-name>.cloudvault.m-files.com`」と入力します。

    b。 **[ポート番号]** は「**4466**」と入力します。

    c. **[プロトコル]** は **[HTTPS]** を選択します。

    d. **[認証]** フィールドで、 **[特定の Windows ユーザー]** を選択します。 その後、署名ページが表示されます。 Microsoft Entra 資格情報を挿入します。

    え **[Vault on Server](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サーバーの資格情報コンテナー)** は、サーバー上の対応する資格情報コンテナーを選択します。

    f. **[OK] を選択**.

#### M-Files テスト ユーザーの作成

このセクションの目的は、M-Files で Britta Simon というユーザーを作成することです。 M-Files にユーザーを追加するには、[M-Files サポート チーム](mailto:support@m-files.com)と連携します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる M-Files のサインオン URL にリダイレクトされます。
- M-Files のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [M-Files] タイルを選択すると、このオプションは M-Files のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mail-luck-tutorial"} -->
## Mail Luck! Microsoft Entra ID を使用したシングル サインオン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mail-luck-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mail Luck! の間のシングル サインオンを構成する方法について説明します。

この記事では、Mail Luck を統合する方法について説明します。 と Microsoft Entra ID を統合します。 Mail Luck! を Microsoft Entra ID を使用することで、次のことができます。

- Mail Luck! にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Azure AD アカウントを使用して Mail Luck! に自動的に と Microsoft Entra アカウントを統合します。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- メールの運! でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- メールの運! では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Mail Luck! ギャラリーから

Mail Luck! の Azure AD への統合を構成するには、 Microsoft Entra ID で Mail Luck! を追加する必要があります ギャラリーからマネージド SaaS アプリのリストに追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**企業向けアプリ**&gt;、**新しいアプリケーション**へ移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Mail Luck!** 」と入力します。
4. 結果パネルから **[Mail Luck!** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mail Luck! に対する Microsoft Entra SSO を構成してテストする

Mail Luck! で Microsoft Entra SSO を構成してテストする **B.Simon** というテスト ユーザーを使用します。 SSO を機能させるには、Microsoft Entra ユーザーと Mail Luck! の関連ユーザーの間にリンク関係を確立する必要があります。

Mail Luck! で Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mail Luck! SSOを設定する**- アプリケーション側でシングルサインオンの設定を行います。
    1. **Mail Luck! テスト ユーザーの作成** - Mail Luck で B.Simon に対応するユーザーを作成しましょう! これは、Microsoft Entra のユーザー表現にリンクされています。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Mail Luck!**&gt;**シングル サインオン**に移動してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://manage<UNITID>.ml-sgw.jp/<TENANT_NAME>/saml/`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://manage<UNITID>.ml-sgw.jp/<TENANT_NAME>/saml/sign_in`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 [Mail Luck! クライアントサポートチーム](https://customer.nttpc.co.jp/cgi-bin/form/inquiry_index.cgi)に連絡して、これらの値を取得してください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mail Luck! SSO

**Mail Luck!** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Mail Luck! サポート チーム](https://customer.nttpc.co.jp/cgi-bin/form/inquiry_index.cgi)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Mail Luck! テスト ユーザー テストユーザー

このセクションでは、Mail Luck! で B.Simon というユーザーを作成します。 [Mail Luck! サポート チーム](https://customer.nttpc.co.jp/cgi-bin/form/inquiry_index.cgi)と協力して、Mail Luck にユーザーを追加してください。 プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションが Mail Luck にリダイレクトされます。 ログインフローを開始できるサインオンURL。
- Mail Luck! の サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 [Mail Luck!] を選択したら マイアプリのタイルでこのオプションを選択すると、Mail Luckにリダイレクトされます。 サインオン URL。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mailgates-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に MailGates を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mailgates-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MailGates 間にシングル サインオンを構成する方法について説明します。

この記事では、MailGates と Microsoft Entra ID を統合する方法について説明します。 MailGates と Microsoft Entra ID を統合すると、次のことができます。

- MailGates にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して MailGates に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MailGates でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MailGates では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの MailGates の追加

Microsoft Entra ID への MailGates の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に MailGates を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**MailGates**」と入力します。
4. 結果のパネルから **[MailGates]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MailGates 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、MailGates で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと MailGates の関連ユーザーとの間にリンク関係を確立する必要があります。

MailGates で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MailGates SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MailGates のテスト ユーザーの作成 - MailGates** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**MailGates**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] のスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    １。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.cybercloud.jp/mg-cgi/mg_login?saml_domain=<DOMAIN>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.cybercloud.jp/saml/module.php/saml/sp/metadata.php/mg_generic_sp`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.cybercloud.jp/mg-cgi/mg_login/saml2-acs/mg_generic_sp`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL で値を更新します。[MailGates クライアント サポート チーム](mailto:tech@cybersolutions.co.jp)に連絡してこれらの値を入手してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
7. **[MailGates のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: [構成 URL のコピー] のスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MailGates SSO の構成

**MailGates** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [MailGates サポート チーム](mailto:tech@cybersolutions.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MailGates テスト ユーザーの作成

このセクションでは、MailGates で Britta Simon というユーザーを作成します。 [MailGates サポート チーム](mailto:tech@cybersolutions.co.jp)と連携して、MailGates プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる MailGates のサインオン URL にリダイレクトされます。
- MailGates のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [MailGates] タイルを選択すると、このオプションは MailGates のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mailosaur-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Mailosaur を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mailosaur-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mailosaur の間でシングル サインオンを構成する方法について説明します。

この記事では、Mailosaur と Microsoft Entra ID を統合する方法について説明します。 Mailosaur と Microsoft Entra ID を統合すると、次のことができます。

- Mailosaur にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Mailosaur に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mailosaur でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Mailosaur では、 **SP** によって開始される SSO のみがサポートされます。
- Mailosaur では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Mailosaur を追加する

Microsoft Entra ID への Mailosaur の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Mailosaur を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Mailosaur**」と入力します。
4. 結果パネルから **Mailosaur** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mailosaur の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Mailosaur に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Mailosaur の関連ユーザーとの間にリンク関係を確立する必要があります。

Mailosaur に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mailosaur SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mailosaur のテストユーザーを作成し、Microsoft Entra ID の B.Simon にリンクさせるために、B.Simon に対応するユーザーを作成してください。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Mailosaur**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://id.mailosaur.com/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://mailosaur.com/__/auth/handler`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://mailosaur.com/sso/<ID>`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 値を取得するには [、Mailosaur サポート チーム](mailto:support@mailosaur.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mailosaur SSO の構成

**Mailosaur** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Mailosaur サポート チーム](mailto:support@mailosaur.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Mailosaur テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Mailosaur に作成します。 Mailosaur では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Mailosaur にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Mailosaur のサインオン URL にリダイレクトします。
- Mailosaur のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Mailosaur] タイルを選択すると、このオプションは Mailosaur のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mamorio-biz-tutorial"} -->
## Microsoft Entra ID で MAMORIO Biz for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mamorio-biz-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-02
- Summary: Microsoft Entra ID と MAMORIO Biz の間のシングル サインオンを構成する方法について説明します。

この記事では、MAMORIO Biz と Microsoft Entra ID を統合する方法について説明します。 MAMORIO Biz と Microsoft Entra ID を統合すると、次のことができます。

- MAMORIO Biz にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して MAMORIO Biz に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な MAMORIO Biz のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MAMORIO Biz では、**IDP** initiated SSO のみがサポートされます。

### ギャラリーから MAMORIO Biz を追加する

Microsoft Entra ID への MAMORIO Biz の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に MAMORIO Biz を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**MAMORIO Biz**」と入力します。
4. 結果のパネルから **[MAMORIO Biz]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MAMORIO Biz 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、MAMORIO Biz に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと MAMORIO Biz の関連ユーザーとの間にリンク関係を確立する必要があります。

MAMORIO Biz に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MAMORIO Biz SSO の構成**: アプリケーション側でシングル サインオン設定を構成します。
    1. **MAMORIO Biz のテストユーザーを作成する - MAMORIO Biz** において、B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra ID 上の B.Simon の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**MAMORIO Biz**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Microsoft Entra で事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. MAMORIO Biz アプリケーションは、特定の形式の SAML アサーションを使用するため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の画像を示すスクリーンショット。]

    注

    アプリケーションの要件に従って、Microsoft Entra 管理センターの上記の既定の属性から **Name**、**Givenname**、**Surname** を手動で削除してください。
7. その他に、MAMORIO Biz アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループドメイン | trail-m |
8. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[MAMORIO Biz の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは構成 URL をコピーするように示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MAMORIO Biz SSO を構成する

**MAMORIO Biz** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [MAMORIO Biz サポート チーム](mailto:support@mamorio.jp)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### MAMORIO Biz テスト ユーザーを作成する

このセクションでは、MAMORIO Biz で B.Simon というユーザーを作成します。 [MAMORIO Biz サポート チーム](mailto:support@mamorio.jp)と協力して、MAMORIO Biz プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した MAMORIO Biz に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [MAMORIO Biz] タイルを選択すると、SSO を設定した MAMORIO Biz に自動的にサインインします。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/manabipocket-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Manabi Pocket を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/manabipocket-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Manabi Pocket の間でシングル サインオンを構成する方法について説明します。

この記事では、Manabi Pocket と Microsoft Entra ID を統合する方法について説明します。 Manabi Pocket を Microsoft Entra ID を統合すると、次のことができます。

- Manabi Pocket にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Manabi Pocket に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

Manabi Pocket と Microsoft Entra の統合を構成するには、次の項目が必要です:

- Microsoft Entra サブスクリプション。 Microsoft Entra の環境がない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Manabi Pocket でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Manabi Pocket では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Manabi Pocket の追加

Microsoft Entra ID への Manabi Pocket の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Manabi Pocket を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Manabi Pocket**」と入力します。
4. 結果パネルから **[Manabi Pocket]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Manabi Pocket 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Manabi Pocket に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Manabi Pocket の関連ユーザーとの間にリンク関係を確立する必要があります。

Manabi Pocket で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Manabi Pocket の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Manabi Pocket のテストユーザーを作成し**、Microsoft Entra でのユーザー表現にリンクした B.Simon に対応するユーザーを Manabi Pocket で設定します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリケーション]**&gt;**[Manabi Pocket]**&gt;**[シングル サインオン]** を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<SERVER-NAME>.ed-cl.com/<TENANT-ID>/idp/provider`

    b。 [**サインオン URL** テキスト ボックスに、URL: `https://ed-cl.com/` を入力します。

    注

    識別子の値は実際の値ではありません。 この値を実際の識別子で更新します。 この値を取得するには、[Manabi Pocket クライアント サポート チーム](mailto:info-ed-cl@ntt.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Manabi Pocket のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Manabi Pocket SSO の構成

**Manabi Pocket** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Manabi Pocket サポート チーム](mailto:info-ed-cl@ntt.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Manabi Pocket のテスト ユーザーを作成する

このセクションでは、Manabi Pocket で Britta Simon というユーザーを作成します。 [Manabi Pocket サポート チーム](mailto:info-ed-cl@ntt.com)と協力して、Manabi Pocket プラットフォームでユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Manabi Pocket のサインオン URL にリダイレクトされます。
- Manabi Pocket のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Manabi Pocket] タイルを選択すると、このオプションは Manabi Pocket のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/manifestly-checklists-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオンの Manifestly チェックリストを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/manifestly-checklists-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Manifestly Checklists の間でシングル サインオンを構成する方法について説明します。

この記事では、Manifestly Checklists と Microsoft Entra ID を統合する方法について説明します。 Manifestly Checklists と Microsoft Entra ID を統合すると、次のことができます。

- Manifestly Checklists にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Manifestly Checklists に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Manifestly Checklists でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Manifestly Checklists では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Manifestly Checklists の追加

Microsoft Entra ID への Manifestly Checklists の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Manifestly Checklists を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Manifestly Checklists**」と入力します。
4. 結果パネルから **[Manifestly Checklists]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Manifestly Checklists に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Microsoft Entra SSO と Manifestly Checklists を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Manifestly Checklists の関連ユーザーとの間にリンク関係を確立する必要があります。

Manifestly Checklists で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Manifestly Checklists SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Manifestly Checklists のテストユーザーを作成 - Microsoft Entra における B.Simon の表現とリンクさせるために、Manifestly Checklists で B.Simon に対応するユーザーを作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Manifestly Checklists]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://app.manifest.ly/users/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://app.manifest.ly/users/saml/auth`

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://app.manifest.ly/users/sign_in` |
    | `https://app.manifest.ly/a/<CustomerName>` |

    注

    この値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには、[Manifestly Checklists クライアント サポート チーム](mailto:support@manifest.ly)に問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Manifestly Checklists アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Manifestly Checklists アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | first\_name | User.givenname |
    | last\_name | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Manifestly Checklists のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Manifestly Checklists SSO の構成

1. Manifestly Checklists 企業サイトに管理者としてログインします。
2. **[設定]**&gt;**SSO** に移動し、[**SAML サインオンの設定**] ボタンを選択します。

    [Image: SSO の設定を示すスクリーンショット。]
3. **[Edit SAML Single Sign](SAML シングル サインオンの編集)** ページで、次の手順に従います。

    [Image: SSO 構成を示すスクリーンショット。]

    1. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[SAML 証明書]** テキストボックスに貼り付けます。
    2. **[SAML エンティティ]** ボックスに、前にコピーした **Microsoft Entra 識別子**の値を貼り付けます。
    3. **[SAML URL]** テキスト ボックスに、前にコピーした **[ログイン URL]** の値を貼り付けます。
    4. **保存** を選択します。

#### Manifestly Checklists テスト ユーザーの作成

1. 別の Web ブラウザーのウィンドウで、管理者として Manifestly Checklists 企業サイトにサインインします。
2. **Teams**&gt;**Users** に移動し、[**ユーザーの追加]** を選択します。

    [Image: [チーム メンバー] を示すスクリーンショット。]
3. テキスト ボックスに **[名前** ] と [ **電子メール** ] を入力し、[ **招待の送信**] を選択します。

    [Image: ユーザーの追加を示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Manifestly Checklists のサインオン URL にリダイレクトされます。
- Manifestly Checklists のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Manifestly チェックリストに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [マニフェスト チェックリスト] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Manifestly Checklists に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mapbox-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Mapbox を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mapbox-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mapbox の間のシングル サインオンを構成する方法について説明します。

この記事では、Mapbox と Microsoft Entra ID を統合する方法について説明します。 Mapbox を Microsoft Entra ID と統合すると、次のことが可能になります。

- Mapbox にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Mapbox に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mapbox のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Mapbox で **IDP** Initiated SSO がサポートされています

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Mapbox の追加

Microsoft Entra ID に Mapbox を統合するには、ギャラリーからご自分の管理対象 SaaS アプリの一覧に Mapbox を追加構成する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Mapbox**」と入力します。
4. 結果ウィンドウで **[Mapbox]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mapbox に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、Mapbox に Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Mapbox の関連ユーザーとの間にリンク関係を確立する必要があります。

Mapbox に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mapbox SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mapbox テストユーザーの作成** - B.Simon に対応するユーザーを Mapbox で作成し、このユーザーを Microsoft Entra の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Mapbox**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Mapbox アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングをご自分の SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Mapbox アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ロール | user.assignedroles |
    |  |  |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Mapbox のセットアップ]** セクションで、ご自分の要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mapbox SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として Mapbox にサインインします。
2. **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** タブを選択します。

    [Image: Mapbox の [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) タブ]
3. 左側のナビゲーション ウィンドウから [ **セキュリティ** ] タブを選択します。

    [Image: Mapbox の [Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ) タブ]
4. [ **シングル サインオンの編集]** を選択します。

    [Image: Mapbox の [Edit single sign-on](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの編集)]
5. **[手順 3:Setup SAML single sign-on for Mapbox](Mapbox に SAML シングル サインオンを設定する)** までスクロールダウンし、次の手順を実行します。

    [Image: Mapbox の構成]

    1. **[Idp サインオン URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。
    2. **[発行者 ID]** ボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。
    3. ダウンロードした**証明書 (Raw)** ファイルをメモ帳で開き、その証明書の内容をコピーして **[X.509 証明書]** テキスト ボックスに貼り付けます。
    4. [ **シングル サインオン設定の保存]** を選択します。

#### Mapbox テスト ユーザーの作成

このセクションでは、Mapbox に Britta Simon というユーザーを作成します。 [Mapbox サポート チーム](mailto:help@mapbox.com)と連携して、Mapbox プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Mapbox に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [Mapbox] タイルを選択すると、SSO を設定した Mapbox に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mapiq-essentials-tutorial"} -->
## Microsoft Entra ID でシングルサインオン用に Mapiq Essentials を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mapiq-essentials-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mapiq Essentials 間のシングル サインオンを構成する方法について説明します。

この記事では、Mapiq Essentials と Microsoft Entra ID を統合する方法について説明します。 Mapiq Essentials を Microsoft Entra ID と統合すると、次のことが可能になります。

- Microsoft Entra ID で Mapiq Essentials にアクセスできるユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Mapiq Essentials に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mapiq Essentials でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Mapiq Essentials では、**SP** Initiated SSO がサポートされます。

### ギャラリーから Mapiq Essentials を追加する

Microsoft Entra ID への Mapiq Essentials の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Mapiq Essentials を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Mapiq Essentials**」と入力します。
4. 結果のパネルから **[Mapiq Essentials]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mapiq Essentials 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Microsoft Entra SSO と Mapiq Essentials を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Mapiq Essentials の関連ユーザーの間にリンク関係を確立する必要があります。

Microsoft Entra SSO と Mapiq Essentials を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mapiq Essentials SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mapiq Essentials テスト ユーザーの作成** - Mapiq Essentials で B.Simon に対応するユーザーを作成し、Microsoft Entra のこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Mapiq Essentials**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`https://<customername>.mapiq.net` という形式で URL を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customername>.mapiq.net/federation/saml/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customername>.mapiq.net`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Mapiq Essentials クライアント サポート チーム](mailto:support@mapiq.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. SURFsecureID - Azure MFA アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、SURFsecureID - Azure MFA アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名前 | user.displayname |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mapiq Essentials SSO の構成

**Mapiq Essentials** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Mapiq Essentials サポート チーム](mailto:support@mapiq.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Mapiq Essentials のテスト ユーザーの作成

このセクションでは、Mapiq Essentials で Britta Simon というユーザーを作成します。 [Mapiq Essentials サポート チーム](mailto:support@mapiq.com)と連携し、Mapiq Essentials プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Mapiq Essentials のサインオン URL にリダイレクトされます。
- Mapiq Essentials のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Mapiq Essentials] タイルを選択すると、このオプションは Mapiq Essentials のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mapiq-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Mapiq を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mapiq-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mapiq の間でシングル サインオンを構成する方法について説明します。

この記事では、Mapiq と Microsoft Entra ID を統合する方法について説明します。 Mapiq を Microsoft Entra ID を統合すると、次のことができます。

- Mapiq にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Mapiq に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Mapiq でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Mapiq では、**SP** Initiated SSO がサポートされます。
- Mapiq では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Mapiq を追加する

Microsoft Entra ID への Mapiq の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Mapiq を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Mapiq**」と入力します。
4. 結果パネルから **[Mapiq]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mapiq 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Mapiq に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Mapiq の関連ユーザーとの間にリンク関係を確立する必要があります。

Mapiq に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mapiq の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mapiqのテストユーザーの作成** - MapiqでB.Simonに対応するユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Mapiq**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://mapiqprod.b2clogin.com/mapiqprod.onmicrosoft.com/B2C_1A_TrustFrameworkBase/samlp/sso/assertionconsumer`

    b。 [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.mapiq.com`
6. Mapiq アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Mapiq アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 表示名 | ユーザー.表示名 |
    | 部署 | ユーザーの部署 |
    | 事業部 | ユーザー.companyname |
    | オフィス | ユーザー.オフィスロケーション |
    | 職務タイトル | ユーザー.職名 |
    | 国 | ユーザーの国 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mapiq SSO の構成

**Mapiq SSO の設定** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Mapiq SSO の設定 サポート チーム](mailto:support@mapiq.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Mapiq のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Mapiq に作成します。 Mapiq では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Mapiq にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Mapiq のサインオン URL にリダイレクトされます。
- Mapiq のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Mapiq] タイルを選択すると、このオプションは Mapiq のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/maptician-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Maptician を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/maptician-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: ユーザー アカウントを Maptician に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Maptician ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Maptician](https://www.maptician.com) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Maptician でユーザーを作成する
- アクセスが不要になったときに Maptician のユーザーを削除する
- Microsoft Entra ID と Maptician の間でユーザー属性の同期を維持する
- Maptician に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/maptician-tutorial)する (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Maptician](https://www.maptician.com) テナント。
- 管理者アクセス許可がある Maptician のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Maptician 間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように Maptician を構成する

Maptician 環境を Microsoft Entra プロビジョニングおよびシングル サインオン (SSO) に接続するプロセスは、Maptician サポート チーム (support@maptician.com) に問い合わせるか、Maptician アカウント マネージャーに直接連絡して始めることができます。 **テナント URL** と**シークレット トークン**を含むドキュメントが提供されます。 Maptician サポート チームのメンバーから、この統合の設定の支援や、その構成や使用に関する質問への回答を得ることができます。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Maptician を追加する

Microsoft Entra アプリケーション ギャラリーから Maptician を追加して、Maptician へのプロビジョニングの管理を開始します。 SSO のために Maptician を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Maptician への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて Maptician 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Maptician の自動ユーザー プロビジョニングを構成するには、以下の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Maptician]** を選択します。

    [Image: アプリケーションの一覧の [Maptician] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Maptician テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Maptician に接続できることを確認します。 接続に失敗した場合は、Maptician アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Maptician に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Maptician のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Maptician API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | emails[type eq "work"].value | 糸 |  |
    | 活動中 | ブール値 |  |
    | タイトル | 糸 |  |
    | ユーザータイプ | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | addresses[type eq "work"].region | 糸 |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/maptician-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Maptician を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/maptician-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Maptician の間のシングル サインオンを構成する方法について説明します。

この記事では、Maptician と Microsoft Entra ID を統合する方法について説明します。 Maptician を Microsoft Entra ID と統合すると、次のことが可能になります。

- Maptician にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Maptician に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Maptician でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Maptician では、**SP Initiated と IDP Initiated** SSO がサポートされます

### ギャラリーからの Maptician の追加

Maptician と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Maptician をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Maptician**」と入力します。
4. 結果のパネルから **[Maptician]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Maptician 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Maptician に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Maptician での関連ユーザーとの間にリンク関係を確立する必要があります。

Maptician に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Maptician の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Maptician のテスト ユーザーを作成する** - Maptician で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID] **&gt;** [エンタープライズ アプリ] **&gt;** [Maptician] **&gt;** [シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.maptician.com/saml/acs_msft`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.maptician.com/saml/acs_msft`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.maptician.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Maptician クライアント サポート チーム](mailto:support@maptician.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Maptician アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. 上記に加えて、Maptician アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 従業員ID | user.employeeid |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Maptician の SSO の構成

**Maptician** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Maptician サポート チーム](mailto:support@maptician.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Maptician のテスト ユーザーの作成

このセクションでは、Maptician で Britta Simon というユーザーを作成します。 [Maptician サポート チーム](mailto:support@maptician.com)と連携して、Maptician プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Maptician のサインオン URL にリダイレクトされます。
- Maptician のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Maptician に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Maptician タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Maptician に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/marker-io-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Marker.io を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/marker-io-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-07-24
- Summary: Microsoft Entra ID と Marker.io の間にシングル サインオンを構成する方法について説明します。

この記事では、Marker.io と Microsoft Entra ID を統合する方法について説明します。 Marker.io と Microsoft Entra ID を統合すると、次のことができます。

- Marker.io にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Marker.io に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Marker.io でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Marker.io では、**SP および IDP** Initiated SSO がサポートされています。
- Marker.io では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Marker.io の追加

Microsoft Entra ID への Marker.io の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Marker.io を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Marker.io**」と入力します。
4. 結果のパネルから **[Marker.io]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Marker.io 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、 Marker.io に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Marker.io の関連ユーザーとの間にリンク関係を確立する必要があります。

Marker.io に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Marker.io SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Marker.io のテストユーザーを作成する** - Marker.ioでB.Simonに対応するユーザーを作成し、Microsoft Entra IDのユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Marker.io**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    A. **[識別子]** ボックスに、`https://api.marker.io/auth/sso/saml` という URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://api.marker.io/auth/sso/saml/<ID>` のパターンを使用して URL を入力します
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** テキスト ボックスに、URL として「`https://api.marker.io/auth/sso/saml`」と入力します。

    注

    応答 URL は実際のものではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、[Marker.io サポート チーム](mailto:info@marker.io)にお問い合わせください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Marker.io アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の画像を示すスクリーンショット。]
8. その他に、Marker.io アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | 苗字 | User.surname |
9. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書の [ダウンロード] リンクを示すスクリーンショット。]
10. **[Set up Marker.io] (Marker.io のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成のURL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Marker.io SSO の構成

**Marker.io** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [Marker.io サポート チーム](mailto:info@marker.io)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Marker.io テスト ユーザーを作成する

このセクションでは、Britta Simon という名前のユーザーを Marker.io に作成します。 Marker.io では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Marker.io にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Marker.io サインオン URL にリダイレクトします。
- Marker.io のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Marker.io に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Marker.io] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Marker.io に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/marketo-tutorial"} -->
## Microsoft Entra ID で Marketo for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/marketo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Marketo の間のシングル サインオンを構成する方法について説明します。

この記事では、Marketo と Microsoft Entra ID を統合する方法について説明します。 Marketo を Microsoft Entra ID と統合すると、次の利点があります。

- Marketo にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが Microsoft Entra アカウントで Marketo に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Marketo でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Marketo では、 **ID プロバイダー (IdP) によって**開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Marketo の追加

Microsoft Entra ID への Marketo の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Marketo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Marketo**」と入力します。
4. 結果のパネルから **[Marketo]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Marketo に対する Microsoft Entra SSO の構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Marketo で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Marketo の関連ユーザー間にリンク関係を確立する必要があります。

Marketo で Microsoft Entra シングル サインオンを構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成** する - Britta Simon で Microsoft Entra SSO をテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てて** 、Britta Simon が Microsoft Entra SSO を使用できるようにします。
2. **Marketo の SSO の構成**- アプリケーション側で SSO 設定を構成します。
    1. **Marketo のテストユーザーの作成** - Britta Simon の Marketo における対応ユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Marketo]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://saml.marketo.com/sp`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.marketo.com/saml/assertion/<munchkinid>`

    c. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<munchkinid>.marketo.com/`

    注

    これらの値は実際の値ではありません。 これらの値を実際の応答 URL とリレー状態で更新してください。 これらの値を取得するには、[Marketo クライアント サポート チーム](https://investors.marketo.com/contactus.cfm)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Marketo アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Marketo ではこれをユーザーのメール アドレスにマップすることが求められます。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: トークン属性構成の画像を示すスクリーンショット。]
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Marketo のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Marketo の SSO の構成

Marketo で SSO 設定を構成し、Microsoft Entra IDに必要な値を収集するには、次の手順に従います。

1. 別の Web ブラウザー ウィンドウで、Marketo 企業サイトに管理者としてサインインします。
2. アプリケーションの Munchkin ID を取得するには、次のアクションを実行します。

    a. 管理者の資格情報を使用して Marketo アプリにログインします。

    b。 上部のナビゲーション ウィンドウで [ **管理者** ] ボタンを選択します。

    [Image: シングル サインオンの構成 1]

    c. [統合] メニューに移動し、[ **Munchkin] リンク**を選択します。

    [Image: シングル サインオンの構成 2]

    d. 画面に表示される Munchkin ID をコピーし、Microsoft Entra の構成ウィザードで、応答 URL を完了します。

    [Image: シングル サインオンの構成 3]
3. アプリケーションで SSO を構成するには、次の手順に従います。

    a. 管理者の資格情報を使用して Marketo アプリにログインします。

    b。 上部のナビゲーション ウィンドウで [ **管理者** ] ボタンを選択します。

    [Image: シングル サインオンの構成 4]

    c. [統合] メニューに移動し、[ **シングル サインオン**] を選択します。

    [Image: シングル サインオンの構成 5]

    d. SAML 設定を有効にするには、[ **編集** ] ボタンを選択します。

    [Image: シングル サインオンの構成6]

    e. シングル サインオン設定を**有効**にします。

    f. **Microsoft Entra 識別子**を **[発行者 ID]** ボックスに貼り付けます。

    g. **[エンティティ ID]** ボックスに、URL「`http://saml.marketo.com/sp`」を入力します。

    h. **[Name Identifier element](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前識別子要素)** としてユーザー ID の場所を選択します。

    [Image: シングル サインオンの構成 7]

    注

    ユーザー識別子が UPN 値でない場合は、Marketo Single Sign-On 設定の [ **属性** ] タブで変更します。

    一. Microsoft Entra の構成ウィザードからダウンロードした証明書をアップロードします。 設定を**保存**します。

    j. ページのリダイレクト設定を編集します。

    k. **[ログイン URL]** ボックスに**ログイン URL** を貼り付けます。

    l. **[ログアウト URL]** ボックスに**ログアウト URL** を貼り付けます。

    m. **[エラー URL] で** **Marketo インスタンスの URL を**コピーし、[**保存]** ボタンを選択して設定を保存します。

    [Image: シングル サインオンの構成 8]
4. ユーザーの SSO を有効にするには、次の操作を行います。

    a. 管理者の資格情報を使用して Marketo アプリにログインします。

    b。 上部のナビゲーション ウィンドウで [ **管理者** ] ボタンを選択します。

    [Image: シングル サインオンの構成 9]

    c. **[セキュリティ**] メニューに移動し**、[ログイン設定]** を選択します。

    [Image: シングル サインオンの構成 10]

    d. **[SSO 必須]** オプションをオンにして、設定を**保存**します。

    [Image: シングル サインオンの構成 11]

#### Marketo のテスト ユーザーの作成

このセクションでは、Marketo で Britta Simon というユーザーを作成します。 Marketo プラットフォームでユーザーを作成するには、次の手順に従ってください。

1. 管理者の資格情報を使用して Marketo アプリにログインします。
2. 上部のナビゲーション ウィンドウで [ **管理者** ] ボタンを選択します。

    [Image: ユーザーのテスト 1]
3. **[セキュリティ**] メニューに移動し、[**ユーザー] と [ロール**] を選択します。

    [Image: ユーザーのテスト 2]
4. [ユーザー] タブの [ **新しいユーザーの招待** ] リンクを選択します。

    [Image: ユーザーのテスト 3]
5. [新しいユーザーの追加] ウィザードで、次の情報を入力します。

    a. テキスト ボックスにユーザーの **[メール]** アドレスを入力します。

    [Image: ユーザーのテスト 4]

    b。 テキスト ボックスに **[名]** を入力します。

    c. テキスト ボックスに **[姓]** を入力します。

    d. [**次へ**] を選択します。
6. [ **アクセス許可** ] タブで、 **userRoles** を選択し、[ **次へ**] を選択します。

    [Image: ユーザーのテスト 5]
7. [ **送信** ] ボタンを選択してユーザーへの招待を送信する

    [Image: ユーザーのテスト 6]
8. ユーザーは電子メール通知を受け取り、リンクを選択し、パスワードを変更してアカウントをアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Marketo に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [Marketo] タイルを選択すると、SSO を設定した Marketo に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/markit-procurement-service-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Markit Procurement Service を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/markit-procurement-service-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から Markit Procurement Service に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Markit Procurement Service と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使って、[Markit Procurement Service](https://www.markit.eu) に対するユーザーのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Markit Procurement Service でユーザーを作成します。
- アクセスが不要になったら、Markit Procurement Service のユーザーを削除します。
- Microsoft Entra ID と Markit Procurement Service の間でユーザー属性の同期を維持します。
- Markit Procurement Service への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者権限を持つ Markit Procurement Service のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Markit Procurement Service の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Markit Procurement Service を構成する

Maptician 環境を Microsoft Entra プロビジョニングに接続するプロセスは、[Markit サポート チーム](mailto:support@markit.eu)に問い合わせるか、Markit アカウント マネージャーに直接連絡して始めることができます。 **テナントの URL** と**シークレット トークン**が記載されたドキュメントが提供されます。 Markit アカウント マネージャーから、この統合の設定の支援や、その構成や使用に関する質問への回答を得ることができます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Markit Procurement Service を追加する

Microsoft Entra アプリケーション ギャラリーから Markit Procurement Service を追加して、Markit Procurement Service へのプロビジョニングの管理を開始します。 既に SSO のために Markit Procurement Service を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Markit Procurement Service への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てに基づいて、Markit Procurement Service でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Markit Procurement Service の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Markit Procurement Service** を選択します。

    [Image: アプリケーションの一覧中の Markit Procurement Service リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Markit Procurement Service のテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Markit Procurement Service に接続できることを確認します。 接続に失敗した場合は、Markit Procurement Service アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Markit Procurement Service に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Markit Procurement Service のユーザー アカウントとの照合に使用されます。 [照合対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)の変更を選択する場合は、その属性に基づいたユーザーのフィルター処理を Markit Procurement Service API がサポートしているか確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Markit調達サービスに必要です |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | externalId | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/maverics-identity-orchestrator-saml-connector-tutorial"} -->
## シングル サインオン用に Maverics Identity Orchestrator SAML Connector を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/maverics-identity-orchestrator-saml-connector-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-05
- Summary: Microsoft Entra ID と Maverics Identity Orchestrator SAML Connector 間にシングル サインオンを構成する方法について説明します。

Strata の Maverics Orchestrator には、認証とアクセス制御のために、オンプレミス アプリケーションを Microsoft Entra ID と統合する簡単な方法が用意されています。 Maverics Orchestrator を使用すると、現在、ヘッダー、Cookie、およびその他の独自の認証方法に依存しているアプリの認証と承認を最新化できます。 Maverics Orchestrator のインスタンスは、オンプレミスまたはクラウドにデプロイできます。

このハイブリッド アクセスの記事では、従来の Web アクセス管理製品によって現在保護されているオンプレミスの Web アプリケーションを移行し、認証とアクセス制御に Microsoft Entra ID を使用する方法について説明します。 基本的な手順は次のとおりです。

1. Maverics Orchestrator をセットアップする
2. アプリケーションをプロキシ経由にする
3. Microsoft Entra ID でエンタープライズ アプリケーションを登録する
4. Microsoft Entra ID を使用した認証およびアプリケーションへのアクセスの承認を行う
5. シームレスなアプリケーション アクセスのためにヘッダーを追加する
6. 複数のアプリケーションを操作する

### 前提条件

- Microsoft Entra ID サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Maverics Identity Orchestrator プラットフォームのアカウント。 https://maverics.strata.io でサインアップします。
- ヘッダー ベースの認証が使用されている少なくとも 1 つのアプリケーション。 この例では、 `https://localhost:8443`で到達可能な Sonar というアプリケーションに対して作業します。

### 手順 1: Maverics Orchestrator をセットアップする

https://maverics.strata.io で Maverics アカウントにサインアップした後、ラーニング センターの記事「[**Getting Started: Evaluation Environment**](https://maverics.strata.io/learn/redirect?context=environments-create-evaluation)」を使用します。 この記事では、評価環境の作成、オーケストレーターのダウンロード、マシンへのオーケストレーターのインストールの手順について説明します。

### 手順 2: レシピを使用して Microsoft Entra ID をアプリに拡張する

次に、ラーニング センターの記事「 [**Microsoft Entra ID を従来の標準以外のアプリに拡張する**](https://maverics.strata.io/learn/redirect?context=microsoft-entra-id-recipe)」を使用します。 この記事では、ID ファブリック、ヘッダー ベースのアプリケーション、部分的に完全なユーザー フローを自動的に構成する .json レシピについて説明します。

### 手順 3: Microsoft Entra ID でエンタープライズ アプリケーションを登録する

次に、エンド ユーザーの認証に使用される新しいエンタープライズ アプリケーションを Microsoft Entra ID で作成します。

注

条件付きアクセスなどの Microsoft Entra ID 機能を活用する場合は、オンプレミスのアプリケーションごとにエンタープライズ アプリケーションを作成することが重要です。 これにより、アプリごとの条件付きアクセス、アプリごとのリスク評価、アプリごとの割り当てアクセス許可などが許可されます。一般に、Microsoft Entra ID のエンタープライズ アプリケーションは、Maverics の Azure コネクタにマップされます。

1. Microsoft Entra ID テナントで、 **エンタープライズ アプリケーション**に移動し、[ **新しいアプリケーション** ] を選択し、Microsoft Entra ID ギャラリーで **Maverics Identity Orchestrator SAML Connector** を検索して選択します。
2. Maverics Identity Orchestrator SAML Connector の **[プロパティ]** ペインで、 **[ユーザーの割り当てが必要ですか?]** を **[いいえ]** に設定して、ディレクトリ内のすべてのユーザーがアプリケーションを使用できるようにします。
3. Maverics Identity Orchestrator SAML Connector の **[概要]** ペインで、 **[シングル サインオンを設定する]** を選択してから、 **[SAML]** を選択します。
4. Maverics Identity Orchestrator SAML Connector の **[SAML ベースのサインオン]** ペインで、 **[編集]** (鉛筆アイコン) ボタンを選択して **[基本的な SAML 構成]** を編集します。

5. **エンティティ ID**`https://sonar.maverics.com` を入力します。 このエンティティ ID はテナント内のアプリ間で一意である必要があり、任意の値を指定できます。 この値は、次のセクションで Azure コネクタの `samlEntityID` フィールドを定義するときに使用します。
6. **応答 URL**`https://sonar.maverics.com/acs` を入力します。 この値は、次のセクションで Azure コネクタの `samlConsumerServiceURL` フィールドを定義するときに使用します。
7. **サインオン URL**`https://sonar.maverics.com/` を入力します。 このフィールドは Maverics では使用されませんが、ユーザーが Microsoft Entra ID My Apps ポータルを使用してアプリケーションにアクセスできるようにするには、Microsoft Entra ID で必要です。
8. **[保存]** を選択します。
9. **[SAML 署名証明書]** セクションで、**[コピー]** ボタンを選択して **[アプリのフェデレーション メタデータ URL]** をコピーし、お使いのコンピューターに保存します。

    [Image: [SAML 署名証明書] コピー ボタンのスクリーンショット。]

### 手順 4: Microsoft Entra ID を使用した認証およびアプリケーションへのアクセスの承認を行う

引き続き、ラーニング センター トピック「**Microsoft Entra ID を標準以外のレガシ アプリに拡張する**」の手順 4 に進み、Maverics でユーザー フローを編集します。 これらの手順では、アップストリーム アプリケーションにヘッダーを追加し、ユーザー フローを配置するプロセスについて順に説明します。

ユーザー フローを配置したら、認証が想定どおりに動作していることを確認するために、Maverics プロキシ経由でアプリケーション リソースに対して要求を行います。 これで、保護されたアプリケーションでは要求でヘッダーを受信するようになりました。

アプリケーションで異なるヘッダーが想定されている場合は、ヘッダー キーを自由に編集してください。 SAML フローの一部として Microsoft Entra ID から返されるすべての要求は、ヘッダーで使用できます。 たとえば、別のヘッダー `secondary_email: azureSonarApp.email` を含めることができます。ここで、`azureSonarApp` はコネクタ名で、`email` は Microsoft Entra ID から返される要求です。

### 高度なシナリオ

#### ID の移行

有効期間が終了した Web アクセス管理ツールには満足できないが、パスワードの一括リセットを行わずにユーザーを移行する方法はないとお考えですか。 Maverics Orchestrator では、`migrationgateways` を使用して ID の移行をサポートします。

#### Web サーバー モジュール

Maverics Orchestrator を使用してネットワークとプロキシのトラフィックをやり直したくないとお考えですか。 問題ありません。Maverics Orchestrator では、Web サーバー モジュールと組み合わせて、プロキシ経由にせずに同じソリューションを提供できます。

### まとめ

この時点で、Maverics Orchestrator をインストールし、Microsoft Entra ID 内にエンタープライズ アプリケーションを作成して構成し、保護されたアプリケーションに対してプロキシ経由にするように Orchestrator を構成し、一方で認証を要求してポリシーを適用しました。 Maverics Orchestrator を分散 ID 管理のユースケースに使用する方法の詳細については、[Strata にお問い合わせ](mailto:sales@strata.io)ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/maxient-conduct-manager-software-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Maxient Conduct Manager Software を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/maxient-conduct-manager-software-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Maxient Conduct Manager Software の間でシングル サインオンを構成する方法について説明します。

この記事では、Maxient Conduct Manager Software と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Maxient Conduct Manager Software を統合すると、次のことができます。

- Microsoft Entra ID を利用して、Maxient Conduct Manager Software のユーザーを認証します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Maxient Conduct Manager Software に自動的にサインインできるようになります。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) 対応の Maxient Conduct Manager Software サブスクリプション

### シナリオの説明

この記事では、Maxient Conduct Manager Software で使用する Microsoft Entra ID を構成します。

- Maxient Conduct Manager Software では、**SP開始型SSO**と**IDP開始型SSO**がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Maxient Conduct Manager Software を追加する

Microsoft Entra ID への Maxient Conduct Manager Software の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Maxient Conduct Manager Software を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Maxient Conduct Manager Software**」と入力します。
4. 結果パネルから **Maxient Conduct Manager Software** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Maxient Conduct Manager Software のために Microsoft Entra SSO を構成してテストする

Maxient Conduct Manager Software に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ID と Maxient Conduct Manager Software 間の接続を確立する必要があります。

Maxient Conduct Manager Software を使用して Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーが Maxient Conduct Manager Software で使用するための認証を行えるようにします。
    1. **[ユーザーの割り当てが必要です] を [いいえ] に設定** すると、教育機関のすべてのユーザーが認証できるようになります。
2. **Maxient を使用して Microsoft Entra セットアップをテスト** する - 構成が機能し、正しい属性がリリースされているかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Maxient Conduct Manager Software]**&gt;**[シングル サインオン]** にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cm.maxient.com/<SCHOOLCODE>`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 Maxient の実装およびサポート担当者と協力して値を取得します。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。 この URL を Maxient の実装/サポート担当者に提供する必要があります。

    [Image: 証明書のダウンロード リンク]

#### [ユーザーの割り当てが必要] を [いいえ] に設定する

Maxient が正常に機能するには、この手順が **必要** であることに注意してください。 Maxient は、Microsoft Entra システムを利用してユーザーを *認証* します。 ユーザーの *承認* は、実行しようとしている特定の関数に対して Maxient システム内で実行されます。 Maxient では、ディレクトリの属性を使用してこれらの決定を行いません。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Maxient Conduct Manager Software** に移動します。
3. アプリの概要ページで、[ユーザーの割り当てが必要] の設定を [いいえ] に切り替えます。

### Maxient でテストする

Maxient の実装およびサポート担当者がまだサポート チケットを開いていない場合は、"キャンパス ベースの認証および Azure セットアップ - support@maxient.com学校名&lt;&lt;" という件名の電子メールを &gt;&gt; に送信してください。 電子メールの本文で、 **アプリのフェデレーション メタデータ URL を指定します**。 Maxient のスタッフから、適切な属性がリリースされていることを確認できるテスト リンクを記載した応答を受け取ります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/maximo-application-suite-tutorial"} -->
## Microsoft Entra ID で Maximo Application Suite for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/maximo-application-suite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Maximo Application Suite 間のシングル サインオンを構成する方法について説明します。

この記事では、Maximo Application Suite を Microsoft Entra ID と統合する方法について説明します。 カスタマー マネージド - IBM Maximo Application Suite は CMMS EAM プラットフォームであり、インテリジェントな資産管理、監視、予測メンテナンス、信頼性を単一のプラットフォームで提供します。 Maximo Application Suite を Microsoft Entra ID と統合すると、次のことが可能になります。

- Maximo Application Suite にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して Maximo Application Suite に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Maximo Application Suite に対する Microsoft Entra シングル サインオンをテスト環境で構成・テストする。 Maximo Application Suite では、 **SP** と **IDP** によって開始されるシングル サインオンがサポートされます。

### [前提条件]

Microsoft Entra ID を Maximo Application Suite と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Maximo Application Suite でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Maximo Application Suite アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Maximo Application Suite を追加する

Microsoft Entra アプリケーション ギャラリーから Maximo Application Suite を追加して、Maximo Application Suite でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Maximo Application Suite**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル** がある場合は、次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: スクリーンショットはメタデータ ファイルのアップロードを示します。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルを選択する方法を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    d. **SP** 開始モードを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、 `</path>`せずに次のパターンを使用して URL を入力します。 `https://<workspace_id>.<mas_application>.<mas_domain>`

    注

    **Service Provider メタデータ ファイル**は、この記事で後述する「**Maximo Application Suite SSO の構成**」セクションから取得します。 **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。 これらの値を取得するには [、Maximo Application Suite クライアント サポート](https://www.ibm.com/mysupport/) に問い合わせてください。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Maximo Application Suite のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Maximo Application Suite SSO を構成する

1. Maximo Application Suite 企業サイトに管理者としてログインします。
2. スイートの管理に移動し、[ **SAML の構成**] を選択します。

    [Image: Maximo 管理ポータルを示すスクリーンショット。]
3. [SAML 認証] ページで、次の手順を実行します。

    [Image: [認証] ページを示すスクリーンショット。]

    1. [名前 ID 形式](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)として emailAddress を選択します。
    2. [ **ファイルの生成**]、[待機]、[ **ファイルのダウンロード**] の順に選択します。 このメタデータ ファイルを保存し、Microsoft Entra ID 側にアップロードします。
4. **フェデレーション メタデータ XML ファイル**をダウンロードし、Microsoft Entra フェデレーション メタデータ XML ドキュメントを Maximo の SAML 構成パネルにアップロードして保存します。

    [Image: フェデレーション メタデータ ファイルをアップロードするスクリーンショット。]

#### Maximo Application Suite ののテスト ユーザーを作成する

1. 別の Web ブラウザー ウィンドウで、管理者として Maximo Application Suite 企業サイトにサインインします。
2. [ユーザー] の [Suite Administration] で新しい **ユーザー** を作成し、次の手順を実行します。

    [Image: スクリーンショットは、Suite Administration の新しいユーザーを示しています。]

    1. [認証の種類] を **[SAML**] として選択します。
    2. **[表示名**] ボックスに、Microsoft Entra ID で使用される UPN を入力します。一致する必要があります。
    3. [ **プライマリ メール** ] ボックスに、Microsoft Entra ID で使用される UPN を入力します。

        注

        残りのフィールドには、必要に応じて、すべての必要なアクセス許可を入力できます。
    4. そのユーザーに必要な**権利**を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Maximo Application Suite のサインオン URL にリダイレクトされます。
- Maximo Application Suite のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Azure portal で [ **このアプリケーションをテスト**する] を選択して Maximo ログイン ページに移動します。このページでは、完全修飾メール アドレスとして SAML ID を入力する必要があります。 ユーザーが既に IDP で認証されている場合、Maximo Application Suite は再びログインする必要はありません。ブラウザーはホーム ページにリダイレクトされます。
- Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Maximo Application Suite タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Maximo Application Suite に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。

注

スクリーンショットは MAS 継続的デリバリー 8.9 のものであり、今後のバージョンでは異なる可能性があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/maxxpoint-tutorial"} -->
## Microsoft Entra ID で MaxxPoint のシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/maxxpoint-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MaxxPoint の間にシングル サインオンを構成する方法について説明します。

この記事では、MaxxPoint と Microsoft Entra ID を統合する方法について説明します。 MaxxPoint と Microsoft Entra ID を統合すると、次のことができます。

- MaxxPoint にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って MaxxPoint に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- MaxxPoint でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- MaxxPoint は、**SP**主導のSSOと**IDP**主導のSSOをサポートしています。

### ギャラリーからの MaxxPoint の追加

Microsoft Entra ID への MaxxPoint の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MaxxPoint を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「MaxxPoint**」と入力します。
4. 結果パネルから **MaxxPoint** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MaxxPoint 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、MaxxPoint に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと MaxxPoint の関連ユーザーとの間にリンク関係を確立する必要があります。

MaxxPoint に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MaxxPoint SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MaxxPoint テスト ユーザーを作成** - MaxxPointにおけるB.Simonの対応ユーザーを作成し、そのユーザーをMicrosoft Entra上のB.Simonにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**MaxxPoint**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、アプリが既に Azure に事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://maxxpoint.westipc.com/default/sso/login/entity/<customer-id>-azure`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値については、MaxxPoint チームに電話 (888-728-0950) でお問い合わせください。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **MaxxPoint のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成を適切なURLにコピーするためのスクリーンショットです。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MaxxPoint の SSO の構成

アプリケーション用に構成された SSO を取得するには、 **888-728-0950** で MaxxPoint サポート チームに問い合わせてください。ダウンロードした **フェデレーション メタデータ XML** ファイルを提供する方法をさらに支援します。

#### MaxxPoint のテスト ユーザーの作成

このセクションでは、MaxxPoint で Britta Simon というユーザーを作成します。 MaxxPoint アプリケーションにユーザーを追加するには、 **888-728-0950** の MaxxPoint サポート チームにお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる MaxxPoint のサインオン URL にリダイレクトされます。
- MaxxPoint のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した MaxxPoint に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [MaxxPoint] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した MaxxPoint に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mcm-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に MCM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mcm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MCM 間にシングル サインオンを構成する方法について説明します。

この記事では、MCM と Microsoft Entra ID を統合する方法について説明します。 MCM を Microsoft Entra ID と統合すると、次のことができます。

- MCM にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って MCM に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- MCM でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- MCM では、**SP** Initiated SSO がサポートされます。

### ギャラリーから MCM を追加

Microsoft Entra ID への MCM の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MCM を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**MCM**」と入力します。
4. 結果パネルから **[MCM]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MCM 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、MCM に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと MCM の関連ユーザーとの間にリンク関係を確立する必要があります。

MCM に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MCM の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MCM テスト ユーザーの作成** - Microsoft Entra でユーザーの B.Simon にリンクされた MCM 内の対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;、**Enterprise アプリ**&gt;、**MCM**&gt;、**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://myaba.co.uk/<companyname>`

    b。 [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://myaba.co.uk/client-access/<companyname>/saml.php`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 この値を取得するには、[MCM クライアント サポート チーム](https://mcmtechnology.com/support)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[MCM の設定]** セクションで、要件に従って適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### MCM SSO の構成

**MCM** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [MCM サポート チーム](https://mcmtechnology.com/support)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MCM のテスト ユーザーの作成

このセクションでは、MCM で Britta Simon というユーザーを作成します。 [MCM サポート チーム](https://mcmtechnology.com/support)と連携し、MCM プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

注

MCM の他のユーザー アカウント作成ツールや、MCM から提供されている API を使って、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる MCM サインオン URL にリダイレクトされます。
- MCM のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [MCM] タイルを選択すると、このオプションは MCM のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mdcomune-business-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に MDComune Business を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mdcomune-business-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MDComune Business の間でシングル サインオンを構成する方法について説明します。

この記事では、MDComune Business と Microsoft Entra ID を統合する方法について説明します。 MDComune Business と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で MDComune Business へのアクセスを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して MDComune Business に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MDComune Business でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MDComune Business では、 **IDP** Initiated SSO がサポートされます。
- MDComune Business では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから MDComune Business を追加する

Microsoft Entra ID への MDComune Business の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MDComune Business を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を開きます。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「MDComune Business」**と入力します。
4. 結果パネルから **MDComune Business** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MDComune Business の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、MDComune Business に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと MDComune Business の関連ユーザーとの間にリンク関係を確立する必要があります。

MDComune Business に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MDComune Business の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MDComune Business のテスト ユーザーを作成する** - MDComune Business で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra ID の表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**MDComune Business**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかの値/パターンを入力します。

    | **識別子** |
    | --- |
    | `MDComuneBusiness` |
    | `<MDComuneBusiness_ENTITY_ID>` |

    注

     &lt;MDComuneBusiness\_ENTITY\_ID&gt; は本物ではありません。 これを実際の値で更新します。

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://www.mdcomune.com.br/Madis/Account/SamlLogon`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **MDComune Business のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MDComune Business SSO の構成

**MDComune Business** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、Microsoft Entra 管理センターからコピーした適切な URL を [MDComune Business サポート チーム](mailto:madis@madis.com.br)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MDComune Business のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを MDComune Business に作成します。 MDComune Business では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 MDComune Business にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した MDComune Business に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [MDComune Business] タイルを選択すると、SSO を設定した MDComune Business に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/media/fortigate-ssl-vpn-tutorial/fortigate-deployment-guide-converted"} -->
## FortiGate デプロイ ガイド - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/media/fortigate-ssl-vpn-tutorial/fortigate-deployment-guide-converted
- Service: entra-id / saas-apps
- Article date: 2022-11-21
- Summary: 次世代のファイアウォール製品である Fortinet FortiGate を設定して使用します。

このデプロイ ガイドを使用して、Azure 仮想マシンとしてデプロイされた次世代ファイアウォール製品 Fortinet FortiGate を設定して操作する方法を学習します。 さらに、FortiGate SSL VPN Microsoft Entra ギャラリー アプリを構成して、Microsoft Entra ID を通じた VPN 認証を実現します。

### FortiGate ライセンスを引き換える

Fortinet FortiGate は次世代のファイアウォール製品であり、Azure IaaS (サービスとしてのインフラストラクチャ) の仮想マシンとして使用できます。 この仮想マシンには、従量課金制とライセンス持ち込み (BYOL) の 2 種類のライセンス モードがあります。

BYOL 仮想マシン デプロイ オプションで使用するために Fortinet から FortiGate ライセンスを購入した場合は、Fortinet の製品アクティブ化ページ (https://support.fortinet.com ) でそれを引き換えてください。 結果として得られるライセンス ファイルのファイル拡張子は .lic になります。

### ファームウェアをダウンロードする

このドキュメントの執筆時点では、Fortinet FortiGate Azure VM には、SAML 認証に必要なファームウェア バージョンが付属していません。 最新バージョンを Fortinet から取得する必要があります。

1. https://support.fortinet.com/ でサインインします。
2. **[Download]\(ダウンロード\)**&gt;**[Firmware Images]\(ファームウェア イメージ\)** に移動します。
3. **[Release Notes]\(リリース ノート\)** の右側にある **[Download]\(ダウンロード\)** を選択します。
4. **[v6.00]**&gt;**[6.4]**&gt;**[6.4.2]** の順に選択します。
5. 同じ行の **[HTTPS]** リンクを選択して、**FGT\_VM64\_AZURE-v6-build1723-FORTINET.out** をダウンロードします。
6. 後で使用するためにファイルを保存します。

### FortiGate VM をデプロイする

1. Azure portal に移動し、FortiGate 仮想マシンをデプロイするサブスクリプションにサインインします。
2. 新しいリソース グループを作成するか、FortiGate 仮想マシンをデプロイするリソース グループを開きます。
3. **[追加]** を選択します。
4. **[Marketplace を検索]** に「*Forti*」と入力します。 **[Fortinet FortiGate Next-Generation Firewall]\(Fortinet FortiGate 次世代ファイアウォール\)** を選択します。
5. ソフトウェア プランを選択します (ライセンスを持っている場合はライセンス持ち込み、それ以外の場合は従量課金制)。 **［作成］** を選択します
6. VM の構成を設定します。

    [Image: [仮想マシンの作成] のスクリーンショット。]
7. **[認証の種類]** を **[パスワード]** に設定し、VM の管理者資格情報を指定します。
8. **[確認および作成]**&gt;**[作成]** の順に選択します。
9. VM のデプロイが完了するまで待ちます。

#### 静的パブリック IP アドレスを設定して完全修飾ドメイン名を割り当てる

一貫したユーザー エクスペリエンスにするには、FortiGate VM に割り当てられるパブリック IP アドレスを静的に割り当てるように設定します。 さらに、それを完全修飾ドメイン名 (FQDN) にマップします。

1. Azure portal に移動し、FortiGate VM の設定を開きます。
2. **[概要]** 画面で、パブリック IP アドレスを選択します。

    [Image: FortiGate SSL VPN のスクリーンショット。]
3. **[静的]**&gt;**[保存]** を選択します。

FortiGate VM がデプロイされる環境に対してパブリックにルーティング可能なドメイン名を所有している場合は、VM のホスト (A) レコードを作成します。 このレコードは、静的に割り当てられた前のパブリック IP アドレスにマップされます。

#### TCP ポート 8443 の新しい受信ネットワーク セキュリティ グループ規則を作成する

1. Azure portal に移動し、FortiGate VM の設定を開きます。
2. 左側のメニューで **[ネットワーク]** を選択します。 ネットワーク インターフェイスの一覧が表示され、受信ポート規則が示されます。
3. **[受信ポートの規則を追加する]** を選択します。
4. TCP 8443 に対する新しい受信ポート規則を作成します。

    [Image: [受信セキュリティ規則の追加] のスクリーンショット。]
5. **[追加]** を選択します。

### VM の 2 つ目の仮想 NIC を作成する

内部リソースをユーザーが使用できるようにするには、FortiGate VM に 2 つ目の仮想 NIC を追加する必要があります。 仮想 NIC が存在する Azure の仮想ネットワークには、内部リソースへのルーティング可能な接続が必要です。

1. Azure portal に移動し、FortiGate VM の設定を開きます。
2. FortiGate VM がまだ停止されていない場合は、 **[停止]** を選択し、VM がシャットダウンするまで待ちます。
3. 左側のメニューで **[ネットワーク]** を選択します。
4. **[ネットワーク インターフェイスの接続]** を選択します。
5. **[Create and attach network interface]\(ネットワーク インターフェイスの作成と接続\)** を選択します。
6. 新しいネットワーク インターフェイスのプロパティを構成し、 **[作成]** を選択します。

    [Image: [ネットワーク インターフェイスの作成] のスクリーンショット。]
7. FortiGate VM を起動します。

### FortiGate VM を構成する

以下のセクションでは、FortiGate VM を設定する方法について説明します。

#### ライセンスをインストールする

1. `https://<address>` にアクセスします。 `<address>` は、FortiGate VM に割り当てられている FQDN またはパブリック IP アドレスです。
2. 証明書エラーを無視して続行します。
3. FortiGate VM のデプロイ中に指定した管理者資格情報を使用してサインインします。
4. デプロイでライセンス持ち込みモデルが使用されている場合は、ライセンスのアップロードを求めるメッセージが表示されます。 前に作成したライセンス ファイルを選択してアップロードします。 **[OK]** を選択し、FortiGate VM を再起動します。

    [Image: FortiGate VM ライセンスのスクリーンショット。]
5. 再起動後に、ライセンスを検証するために管理者の資格情報を使用してもう一度サインインします。

#### ファームウェアを更新する

1. `https://<address>` にアクセスします。 `<address>` は、FortiGate VM に割り当てられている FQDN またはパブリック IP アドレスです。
2. 証明書エラーを無視して続行します。
3. FortiGate VM のデプロイ中に指定した管理者資格情報を使用してサインインします。
4. 左側のメニューで、 **[System]\(システム\)**&gt;**[Firmware]\(ファームウェア\)** を選択します。
5. **[Firmware Management]\(ファームウェアの管理\)** で **[Browse]\(参照\)** を選択して、先ほどダウンロードしたファームウェア ファイルを選択します。
6. 警告を無視し、 **[Backup config and upgrade]\(構成をバックアップしてアップグレード\)** を選択します。

    [Image: ファームウェアの管理のスクリーンショット。]
7. **[続行]** をクリックします。
8. FortiGate の構成を (.conf ファイルとして) 保存するように求められたら、 **[Save]\(保存\)** を選択します。
9. ファームウェアがアップロードされて適用されるまで待ちます。 FortiGate VM が再起動されるまで待ちます。
10. FortiGate VM が再起動した後、管理者の資格情報を使用してもう一度サインインします。
11. ダッシュボードの設定を求めるメッセージが表示されたら、 **[Later]\(後で\)** を選択します。
12. チュートリアル ビデオが開始したら、 **[OK]** を選択します。

#### 管理ポートを TCP 8443 に変更する

1. `https://<address>` にアクセスします。 `<address>` は、FortiGate VM に割り当てられている FQDN またはパブリック IP アドレスです。
2. 証明書エラーを無視して続行します。
3. FortiGate VM のデプロイ中に指定した管理者資格情報を使用してサインインします。
4. 左側のメニューで、 **[System]\(システム\)** を選択します。
5. **[Administration Settings]\(管理設定\)** で HTTPS ポートを **8443** に変更し、 **[Apply]\(適用\)** を選択します。
6. 変更が適用されると、ブラウザーで管理ページの再読み込みが試みられますが、失敗します。 これ以降、管理ページのアドレスは `https://<address>:8443` になります。

    [Image: リモート証明書アップロードのスクリーンショット。]

#### Microsoft Entra SAML 署名証明書をアップロードする

1. 「 `https://<address>:8443` 」を参照してください。 `<address>` は、FortiGate VM に割り当てられている FQDN またはパブリック IP アドレスです。
2. 証明書エラーを無視して続行します。
3. FortiGate VM のデプロイ中に指定した管理者資格情報を使用してサインインします。
4. 左側のメニューで、 **[System]\(システム\)**&gt;**[Certificates]\(証明書\)** を選択します。
5. **[Import]\(インポート\)**&gt;**[Remote Certificate]\(リモート証明書\)** の順に選択します。
6. Azure テナントの FortiGate カスタム アプリのデプロイからダウンロードした証明書を参照します。 それを選択して、 **[OK]** を選択します。

#### カスタム SSL 証明書をアップロードして構成する

使用している FQDN をサポートする独自の SSL 証明書を使用して FortiGate VM を構成することもできます。 .PFX 形式の秘密キーでパッケージ化された SSL 証明書にアクセスできる場合、この目的でこれを使用できます。

1. `https://<address>:8443` にアクセスします。 `<address>` は、FortiGate VM に割り当てられている FQDN またはパブリック IP アドレスです。
2. 証明書エラーを無視して続行します。
3. FortiGate VM のデプロイ中に指定した管理者資格情報を使用してサインインします。
4. 左側のメニューで、 **[System]\(システム\)**&gt;**[Certificates]\(証明書\)** を選択します。
5. **[Import]\(インポート\)**&gt;**[Local Certificate]\(ローカル証明書\)**&gt;**[PKCS #12 Certificate]\(PKCS #12 証明書\)** を選択します。
6. SSL 証明書と秘密キーが含まれる .PFX ファイルを参照します。
7. .PFX パスワードと、証明書のわかりやすい名前を指定します。 **[OK]** をクリックします。
8. 左側のメニューで、 **[System]\(システム\)**&gt;**[Settings]\(設定\)** を選択します。
9. **[Administration Settings]\(管理設定\)** で、 **[HTTPS server certificate]\(HTTPS サーバー証明書\)** の隣にある一覧を展開し、前にインポートした SSL 証明書を選択します。
10. **[適用]** を選択します。
11. ブラウザー ウィンドウを閉じて、`https://<address>:8443` に移動します。
12. FortiGate 管理者の資格情報でサインインします。 正しい SSL 証明書が使用されていることがわかります。

#### 認証タイムアウトを構成する

1. Azure portal に移動し、FortiGate VM の設定を開きます。
2. 左側のメニューで **[シリアル コンソール]** を選択します。
3. FortiGate VM 管理者の資格情報を使用してシリアル コンソールにサインインします。
4. シリアル コンソールで、次のコマンドを実行します。

    ```
    config system global
    set remoteauthtimeout 60
    end
    ```

#### ネットワーク インターフェイスで IP アドレスが取得されていることを確認する

1. `https://<address>:8443` にアクセスします。 `<address>` は、FortiGate VM に割り当てられている FQDN またはパブリック IP アドレスです。
2. FortiGate VM のデプロイ中に指定した管理者資格情報を使用してサインインします。
3. 左側のメニューで **[ネットワーク]** を選択します。
4. [ネットワーク] で **[インターフェイス]** を選択します。
5. port1 (外部インターフェイス) と port2 (内部インターフェイス) を調べて、正しい Azure サブネットから IP アドレスが取得されていることを確認します。 a. いずれかのポートで (DHCP 経由で) サブネットから IP アドレスが取得されていない場合は、そのポートを右クリックし、 **[編集]** を選択します。 b. [Addressing Mode]\(アドレス指定モード\) の横で、 **[DHCP]** が選択されていることを確認します。 c. **[OK]** を選択します。

    [Image: ネットワーク インターフェイスのアドレス指定のスクリーンショット。]

#### FortiGate VM でオンプレミスの企業リソースへの正しいルートが設定されていることを確認する

マルチホームの Azure VM では、すべてのネットワーク インターフェイスが同じ仮想ネットワーク上にあります (ただし、サブネットは異なる場合があります)。 これは多くの場合、FortiGate を介して公開されているオンプレミスの企業リソースに両方のネットワーク インターフェイスが接続していることを意味します。 そのため、オンプレミスの企業リソースが要求されたときにトラフィックが正しいインターフェイスから送出されるように、カスタム ルート エントリを作成する必要があります。

1. `https://<address>:8443` にアクセスします。 `<address>` は、FortiGate VM に割り当てられている FQDN またはパブリック IP アドレスです。
2. FortiGate VM のデプロイ中に指定された管理者資格情報を使用してサインインします。
3. 左側のメニューで **[ネットワーク]** を選択します。
4. [ネットワーク] で **[静的ルート]** を選択します。
5. **[新規作成]** を選択します。
6. [宛先] の横で、 **[サブネット]** を選択します。
7. [サブネット] で、オンプレミスの企業リソースが存在するサブネットの情報を指定します (例: `10.1.0.0/255.255.255.0`)。
8. [ゲートウェイ アドレス] の横で、port2 が接続されている Azure サブネット上のゲートウェイを指定します (これは `10.6.1.1` のように、通常 `1` で終わります)。
9. [インターフェイス] の横で、内部ネットワーク インターフェイスである `port2` を選択します。
10. **[OK]** を選択します。

    [Image: ルートの構成のスクリーンショット。]

### FortiGate SSL VPN を構成する

「[チュートリアル: Microsoft Entra シングル サインオン (SSO) と FortiGate SSL VPN の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fortigate-ssl-vpn-tutorial)」にある手順に従います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mediusflow-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に MediusFlow を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mediusflow-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-16
- Summary: Microsoft Entra ID から MediusFlow にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために MediusFlow と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [MediusFlow](https://www.mediusflow.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- MediusFlow でユーザーを作成する
- アクセスが不要になった場合に MediusFlow のユーザーを削除する
- Microsoft Entra ID と MediusFlow の間でユーザー属性の同期を維持する
- MediusFlow でグループとグループ メンバーシップをプロビジョニングする
- MediusFlow へのシングル サインオン (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 品質保証または運用テナントを持つアクティブな MediusFlow サブスクリプション。
- MediusFlow 内で構成を実行できる管理者権限を持つ MediusFlow のユーザー アカウント。
- ユーザーをプロビジョニングする必要がある MediusFlow テナントに会社が追加されていること。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. ■[プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と MediusFlow の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように MediusFlow を構成する

#### MediusFlow 内で Microsoft 365 アプリをアクティブ化する

まず、次の手順を実行して、MediusFlow 内の Microsoft Entra ログインと Microsoft Entra 構成機能へのアクセスを有効にします。

##### ユーザー ログイン

Microsoft 365/Microsoft Entra ID へのログイン フローを有効にするには、 [この](https://success.medius.com/documentation/administration_guide/user_login_and_transfer/end_to_end_support_office365/office365userintegration/#gatsby-focus-wrapper) 記事を参照してください。

##### ユーザー転送の構成

Microsoft Entra ID からプロビジョニングするためにユーザーの構成ポータルを有効にするには、 [この](https://success.medius.com/documentation/administration_guide/manage_your_integration/#company-onboarding) 記事を参照してください。

##### ユーザー プロビジョニングの構成

1. テナント ID を指定して [MediusFlow 管理コンソール](https://office365.cloudapp.mediusflow.com/) にログインします。

    [Image: MediusFlow 管理コンソールのスクリーンショット。最初の統合手順では、[MediusFlow テナント名] ボックスと [認証] ボタンが強調表示されています。]
2. MediusFlow との接続を確認します。

    [Image: 確かめる]
3. Microsoft Entra テナント ID を指定します。

    [Image: テナント ID を指定する]

    詳細については、FAQ の検索方法に関する [FAQ](https://success.medius.com/documentation/administration_guide/user_login_and_transfer/end_to_end_support_office365/office365userintegration/#getting-azure-tenantid) を参照してください。
4. 構成を保存します。

    [Image: 4 番目の統合手順を示す MediusFlow 管理コンソールのスクリーンショット。[構成の保存] ボタンが強調表示されています。]
5. ユーザー プロビジョニングを選択し、[ **OK] を選択します**。

    [Image: 5 番目の統合手順を示す MediusFlow 管理コンソールのスクリーンショット。[ユーザー プロビジョニングの使用] ボタンと [OK] ボタンが強調表示されています。]
6. [ **秘密鍵の生成]** を選択します。 この値をコピーして保存します。 この値は、MediusFLow アプリケーションの [**プロビジョニング**] タブの [**シークレット トークン**] フィールドに入力されます。

    [Image: MediusFlow 管理コンソールの [ユーザー プロビジョニング構成] タブのスクリーンショット。[シークレット キーの生成] ボタンと [コピー] ボタンが強調表示されています。]
7. **[OK] を選択**.

    [Image: MediusFlow 管理コンソールのスクリーンショット。新しい秘密鍵を生成するために [OK] を選択するようユーザーに通知します。[OK] ボタンが強調表示されています。]
8. MediusFlow で定義済みのロール、企業、およびその他の一般的な構成のセットを使用してユーザーをインポートするには、まずユーザーを構成する必要があります。 まず、[ **新しい**構成の追加] を選択して構成を追加します。

    [Image: MediusFlow 管理コンソールの [ユーザー プロビジョニング構成] タブのスクリーンショット。[新しい構成の追加] ボタンが強調表示されています。]
9. ユーザーの既定の設定を指定します。 このビューでは、既定の属性を設定できます。 標準設定で問題ない場合は、有効な会社名のみを指定するだけで十分です。 これらの構成設定は Mediusflow からフェッチされるため、最初に構成する必要があります。 詳細については、この記事の **「前提条件」** セクションを参照してください。

    [Image: MediusFlow の [新しい構成の追加] ウィンドウのスクリーンショット。ロケール設定、フィルター、ユーザー ロールなど、多くの設定が表示されます。]
10. [ **保存] を** 選択してユーザー構成を保存します。

    [Image: MediusFlow 管理コンソールの [ユーザー プロビジョニング構成] タブのスクリーンショット。[保存] ボタンが強調表示されています。]
11. ユーザー プロビジョニング リンクを取得するには、[ **COPY SCIM Link]\(SCIM リンクのコピー**\) を選択します。 この値をコピーして保存します。 この値は、MediusFLow アプリケーションの [**プロビジョニング**] タブの [**テナント URL**] フィールドに入力されます。

    [Image: MediusFlow 管理コンソールの [ユーザー プロビジョニング構成] タブのスクリーンショット。[COPY S C I M](S C I M のコピー) リンク ボタンが強調表示されています。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから MediusFlow を追加する

Microsoft Entra アプリケーション ギャラリーから MediusFlow を追加して、MediusFlow へのプロビジョニングの管理を開始します。 SSO 用に MediusFlow を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: MediusFlow への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で MediusFlow の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で [ **MediusFlow**] を選択します。

    [Image: アプリケーションの一覧の MediusFlow のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションで、先ほど取得したテナント URL 値を **[テナント URL]** に入力します。 先ほど取得したシークレット トークン値を **シークレット トークン**に入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が MediusFlow に接続できることを確認します。 接続に失敗した場合は、MediusFlow アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: スクリーンショットには、[管理者資格情報] ダイアログ ボックスが表示され、テナント U R L とシークレット トークンを入力できます。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から MediusFlow に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で MediusFlow のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、MediusFlow API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.displayName | 糸 |  |
    | 活動中 | ブール値 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |  |
    | urn:ietf:params:scim:schemas:extension:medius:2.0:User:configurationFilter | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:medius:2.0:User:identityProvider | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:medius:2.0:User:nameIdentifier | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:medius:2.0:User:customFieldText1 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:medius:2.0:User:customFieldText2 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:medius:2.0:User:customFieldText3 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:medius:2.0:User:customFieldText4 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:medius:2.0:User:customFieldText5 | 糸 |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から MediusFlow に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で MediusFlow のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | externalID | 糸 |
    | members | リファレンス |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

- 2021 年 1 月 21 日 - カスタム拡張属性 **configurationFilter**、 **identityProvider**、 **nameIdentifier**、 **customFieldText1**、 **customFieldText2**、 **customFieldText3**、 **customFieldText3** 、 **customFieldText5** が追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/memomeister-tutorial"} -->
## Microsoft Entra ID で MemoMeister for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/memomeister-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MemoMeister の間でシングル サインオンを構成する方法について説明します。

この記事では、MemoMeister と Microsoft Entra ID を統合する方法について説明します。 MemoMeister と Microsoft Entra ID を統合すると、次のことができます。

- MemoMeister にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して MemoMeister に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MemoMeister でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MemoMeister では、 **SP** によって開始される SSO のみがサポートされます。

### ギャラリーから MemoMeister を追加する

Microsoft Entra ID への MemoMeister の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MemoMeister を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「MemoMeister**」と入力します。
4. 結果パネルから **MemoMeister** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MemoMeister の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、MemoMeister に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと MemoMeister の関連ユーザーとの間にリンク関係を確立する必要があります。

MemoMeister に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MemoMeister SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MemoMeister テストユーザーを作成 - B.Simon に対応する MemoMeister ユーザーを作成し、それを Microsoft Entra ID のユーザー表現にリンクするために。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**MemoMeister**&gt;**シングルサインオン**まで参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `urn:amazon:cognito:sp:eu-central-1_<ID>`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://memomeister-production.auth.eu-central-1.amazoncognito.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://web.memomeister.com/login`

    注

    識別子の値は実際の値ではありません。 実際の識別子で値を更新します。 この値を取得するには [、MemoMeister サポート チーム](mailto:support@memomeister.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. MemoMeister アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、MemoMeister アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 住所\_市 | ユーザーの都市 |
    | 住所の国名 | ユーザーの国 |
    | 携帯電話 | ユーザーの携帯電話 |
    | 電話番号 | ユーザー.電話番号 |
    | 住所\_町域丁目番地 | ユーザーの住所 |
    | 希望する言語 | ユーザーの優先言語 |
    | 住所\_郵便番号 | ユーザー.郵便番号 |
    | 会社名 | ユーザー.companyname |
    | グループ | ユーザー.グループ |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MemoMeister SSO の構成

**MemoMeister** 側でシングル サインオンを構成するには、**MemoMeister サポート チーム**に[アプリのフェデレーション メタデータ URL を](mailto:support@memomeister.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MemoMeister テスト ユーザーの作成

このセクションでは、MemoMeister で B.Simon というユーザーを作成します。 [MemoMeister サポート チーム](mailto:support@memomeister.com)と協力して、MemoMeister プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる MemoMeister のサインオン URL にリダイレクトされます。
- MemoMeister のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [MemoMeister] タイルを選択すると、このオプションは MemoMeister のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mend-io-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Mend.io を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mend-io-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-07-16
- Summary: Microsoft Entra ID と Mend.io の間でシングル サインオンを構成する方法について説明します。

この記事では、Mend.io と Microsoft Entra ID を統合する方法について説明します。 Mend.io を Microsoft Entra ID と統合すると、次のことができます。

- Mend.io にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Mend.io に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mend.ioのシングルサインオン(SSO)が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Mend.io では、 **SP** Initiated SSO がサポートされます。
- Mend.io では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Mend.io を追加する

Microsoft Entra ID への Mend.io の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Mend.io を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックス **に「Mend.io** 」と入力します。
4. 結果のパネルから **Mend.io** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mend.io の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Mend.io に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Mend.io の関連ユーザーとの間にリンク関係を確立する必要があります。

Mend.io で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mend.io SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mend.io テストユーザーを作成** - Microsoft Entra の B.Simon にリンクされている Mend.io の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Mend.io**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:<Environment>:<ID>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Environment>.mend.io/login/callback?connection=<ID>`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値 Mend.io 取得するには [、サポート チーム](https://www.mend.io/contact-us/) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Mend.io のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mend.io SSO の構成

Mend.io 側でシングル サインオンを構成するには、次**の**[手順](https://docs.mend.io/bundle/platform/page/configure_single_sign-on__sso__for_the_mend_platform.html)に従ってください。

#### テスト ユーザー Mend.io 作成する

このセクションでは、B.Simon というユーザーを Mend.io に作成します。 Mend.io では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Mend.io にユーザーがまだ存在していない場合は、Mend.io にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Mend.io サインオン URL にリダイレクトされます。
- Mend.io サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Mend.io] タイルを選択すると、このオプションは Mend.io サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/menlosecurity-tutorial"} -->
## Microsoft Entra ID で Menlo Security for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/menlosecurity-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Menlo Security 間にシングル サインオンを構成する方法について学習します。

この記事では、Menlo Security と Microsoft Entra ID を統合する方法について説明します。 Menlo Security を Microsoft Entra ID と統合すると、次のことができます。

- Menlo Security にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Menlo Security に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Menlo Security でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Menlo Security では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Menlo Security の追加

Microsoft Entra ID への Menlo Security の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Menlo Security を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Menlo Security**」と入力します。
4. 結果のパネルから **[Menlo Security]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Menlo Security 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Menlo Security に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Menlo Security の関連ユーザーとの間にリンク関係を確立する必要があります。

Menlo Security に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Menlo Security の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Menlo Security のテストユーザーを作成し**、Microsoft Entra のユーザーである B.Simon に対応するものをリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Menlo Security**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.menlosecurity.com/account/login`
    2. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.menlosecurity.com/safeview-auth-server/saml/metadata`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Menlo Security クライアント サポート チーム](https://www.menlosecurity.com/menlo-contact)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Menlo Security のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Menlo Security SSO の構成

1. **Menlo Security 側**でシングル サインオンを構成するために、**Menlo Security**の Web サイトに管理者としてログインします。
2. **[Settings (設定)]** の **[Authentication (認証)]** に移動し、次の操作を行います。

    [Image: シングルサインオンの設定]

    1. **[Enable user authentication using SAML (SAML を使用してユーザー認証を有効にする)]** チェックボックスをオンにします。
    2. **[Allow External Access (外部アクセスを許可する)]** で **[Yes (はい)]** を選択します。
    3. **[SAML プロバイダー]** で **[Microsoft Entra ID]** を選択します。
    4. **SAML 2.0 エンドポイント** : **ログイン URL** を貼り付けます。
    5. **サービス 識別子 (発行者)**: **[Microsoft Entra 識別子]** を貼り付けます。
    6. **[X.509 Certificate (X.509 証明書)]**: ダウンロードした**証明書 (Bas64)** をメモ帳で開いてこのボックスにコピーします。
    7. **[保存]** を選択して設定を保存します。

#### Menlo Security のテスト ユーザーの作成

このセクションでは、Menlo Security で Britta Simon というユーザーを作成します。 [Menlo Security クライアント サポート チーム](https://www.menlosecurity.com/menlo-contact)と連携して、Menlo Security プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Menlo Security のサインオン URL にリダイレクトされます。
- Menlo Security のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Menlo Security] タイルを選択すると、このオプションは Menlo Security のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/meraki-dashboard-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Meraki Dashboard を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/meraki-dashboard-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Meraki Dashboard の間にシングル サインオンを構成する方法について説明します。

この記事では、Meraki Dashboard と Microsoft Entra ID を統合する方法について説明します。 Meraki Dashboard を Microsoft Entra ID と統合すると、次のことができます。

- Meraki Dashboard にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Meraki Dashboard に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Meraki Dashboard でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Meraki Dashboard では、 **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Meraki Dashboard の追加

Microsoft Entra ID への Meraki Dashboard の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Meraki Dashboard を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Meraki Dashboard**」と入力します。
4. 結果パネルから **Meraki Dashboard** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Meraki Dashboard 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Meraki Dashboard に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Meraki Dashboard の関連ユーザーの間にリンク関係を確立する必要があります。

Meraki Dashboard に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Meraki Dashboard の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Meraki Dashboard の管理者ロールを作成 - B.Simon に対応するロールを Meraki Dashboard 上で設定し、Microsoft Entra での B.Simon の表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Meraki Dashboard**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://n27.meraki.com/saml/login/m9ZEgb/< UNIQUE ID >`

    注

    応答 URL は、実際の値ではありません。 この値を実際の応答 URL 値で更新します。これについては、この記事の後半で説明します。
6. **[保存**] ボタンを選択します。
7. Meraki Dashboard アプリケーションは特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Meraki Dashboard アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | `https://dashboard.meraki.com/saml/attributes/username` | ユーザー.ユーザープリンシパルネーム |
    | `https://dashboard.meraki.com/saml/attributes/role` | user.assignedroles |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)。
9. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
10. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。 この値にはコロンを含めることで、Meraki Dashboard で認識されるように変換する必要があります。 たとえば、Azure の拇印が `C2569F50A4AAEDBB8E` の場合、後で Meraki Dashboard で使用できるようにするには `C2:56:9F:50:A4:AA:ED:BB:8E` に変更する必要があります。

    [Image: 拇印の値をコピーする]
11. [ **Meraki Dashboard のセットアップ** ] セクションで、ログアウト URL の値をコピーしてコンピューターに保存します。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Meraki Dashboard SSO の構成

1. 別の Web ブラウザー ウィンドウで、Meraki Dashboard 企業サイトに管理者としてサインインします
2. **組織**&gt;**Settings** に移動します。

    [Image: [Meraki Dashboard Settings](Meraki ダッシュボードの設定) タブ]
3. [認証] で、[ **SAML SSO]** を [ **SAML SSO が有効]** に変更します。

    [Image: Meraki Dashboard Authentication]
4. [ **SAML IdP の追加] を**選択します。

    [Image: Meraki ダッシュボード SAML IdP を追加]
5. 前のセクションの手順 9 で説明したように、コピーして指定した形式で変換した変換された **拇印** の値を **X.590 証明書 SHA1 指紋** テキスト ボックスに貼り付けます。 次に、[ **保存]** を選択します。 保存すると、コンシューマー URL が表示されます。 コンシューマー URL の値をコピーし、[**基本的な SAML 構成] セクション**の **[応答 URL**] ボックスに貼り付けます。

    [Image: Meraki ダッシュボードの構成]

#### Meraki Dashboard の管理者ロールを作成する

1. 別の Web ブラウザー ウィンドウで、管理者として Meraki Dashboard にサインインします。
2. **Organization**&gt;**Administrators** に移動します。

    [Image: Meraki ダッシュボード管理者]
3. [SAML 管理者ロール] セクションで、[ **SAML ロールの追加** ] ボタンを選択します。

    [Image: Meraki ダッシュボードの [SAML ロールの追加] ボタン]
4. [ロール **] meraki\_full\_admin**を入力し、[ **組織のアクセス権** ] を **[完全** ] としてマークし、[ **ロールの作成**] を選択します。 **meraki\_readonly\_admin**のプロセスを繰り返します。今回は、[**組織のアクセス** **] を [読み取り専用**] ボックスに設定します。

    [Image: Meraki ダッシュボードでユーザーを作成する]
5. 次の手順に従って、Meraki Dashboard のロールを Microsoft Entra SAML のロールにマップします。

    [Image: アプリ ロールのスクリーンショット。]

    ア. Azure portal で、[ **アプリの登録**] を選択します。

    b。 [すべてのアプリケーション] を選択し、[ **Meraki Dashboard**] を選択します。

    c. [ **アプリ ロール]** を選択し、[ **アプリ ロールの作成**] を選択します。

    d. [表示名] を `Meraki Full Admin` として入力します。

    え [許可されたメンバー] を `Users/Groups` として選択します。

    f. [値] を `meraki_full_admin` として入力します。

    ジー [説明] を `Meraki Full Admin` として入力します。

    h. **[保存] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Meraki ダッシュボードに自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [Meraki Dashboard] タイルを選択すると、SSO を設定した Meraki Dashboard に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mercado-eletronico-saml-tutorial"} -->
## Microsoft Entra ID で Mercado Eletronico SAML for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mercado-eletronico-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mercado Eletronico SAML の間でシングル サインオンを構成する方法について説明します。

この記事では、Mercado Eletronico SAML と Microsoft Entra ID を統合する方法について説明します。 Mercado Eletronico SAML と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Mercado Eletronico SAML へのアクセス権を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Mercado Eletronico SAML に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mercado Eletronico SAML でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Mercado Eletronico SAML では、 **SP** Initiated SSO のみがサポートされます。

### ギャラリーから Mercado Eletronico SAML を追加する

Microsoft Entra ID への Mercado Eletronico SAML の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Mercado Eletronico SAML を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックス**に「Mercado Eletronico SAML**」と入力します。
4. 結果パネルから **Mercado Eletronico SAML を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mercado Eletronico SAML の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Mercado Eletronico SAML に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Mercado Eletronico SAML の関連ユーザーとの間にリンク関係を確立する必要があります。

Mercado Eletronico SAML に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mercado Eletronico SAML SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mercado Eletronico SAML テスト ユーザーを作成する** - Microsoft Entra ID にリンクされた B.Simon に対応する Mercado Eletronico SAML のユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Mercado Eletronico SAML**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子 (エンティティ ID)** |
    | --- |
    | `https://<SUBDOMAIN>.mercadoeletronico.com` |
    | `https://<SUBDOMAIN>.me.com.br` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<SUBDOMAIN>.me.com.br/<ID>` |
    | `https://<SUBDOMAIN>.miisy.me/<ID>` |
    | `https://<SUBDOMAIN>.miisy.com/<ID>` |
    | `https://<SUBDOMAIN>.miisy.eu/<ID>` |

    c. **[サインオン URL]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<SUBDOMAIN>.me.com.br/<ID>` |
    | `https://<SUBDOMAIN>.miisy.me/<ID>` |
    | `https://<SUBDOMAIN>.miisy.com/<ID>` |
    | `https://<SUBDOMAIN>.miisy.eu/<ID>` |

    d. [ **リレー状態** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **リレー状態** |
    | --- |
    | `https://<SUBDOMAIN>.me.com.br/<ID>` |
    | `https://<SUBDOMAIN>.miisy.me/<ID>` |
    | `https://<SUBDOMAIN>.miisy.com/<ID>` |
    | `https://<SUBDOMAIN>.miisy.eu/<ID>` |
    | `https://contracts.jbssa.com/<ID>` |

    え [ **ログアウト URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.mercadoeletronico.com/auth/realms/me-trunk/broker/<ID>/endpoint`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、リレー状態、ログアウト URL でこれらの値を更新します。 これらの値を取得するには、 [Mercado Eletronico SAML サポート チーム](mailto:suporte@me.com.br) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mercado Eletronico SAML SSO の構成

**Mercado Eletronico SAML** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Mercado Eletronico SAML サポート チーム](mailto:suporte@me.com.br)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Mercado Eletronico SAML テスト ユーザーの作成

このセクションでは、Mercado Eletronico SAML で B.Simon というユーザーを作成します。 [Mercado Eletronico SAML サポート チーム](mailto:suporte@me.com.br)と協力して、Mercado Eletronico SAML プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Mercado Eletronico SAML サインオン URL にリダイレクトします。
- Mercado Eletronico SAML サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Mercado Eletronico SAML] タイルを選択すると、このオプションは Mercado Eletronico SAML サインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mercell-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Mercell を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mercell-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mercell の間でシングル サインオンを構成する方法について説明します。

この記事では、Mercell と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Mercell を統合すると、次のことができます。

- Mercell にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Mercell に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mercell でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Mercell では、**IDP** イニシエート SSO をサポートしています。
- Mercell では、Just-In-Time **ユーザー プロビジョニング** がサポートされています。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Mercell を追加する

Microsoft Entra ID への Mercell の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Mercell を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Mercell**」と入力します。
4. 結果パネル **Mercell** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mercell の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Mercell に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Mercell の関連ユーザーとの間にリンク関係を確立する必要があります。

Mercell に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mercell SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Mercell テストユーザーの作成** - Mercell で Microsoft Entra にリンクする B.Simon に対応するユーザーを作成します。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Mercell**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [**基本的な SAML 構成**] セクションで、次の手順を実行します。

    [**識別子** テキスト ボックスに、URL: `https://my.mercell.com/` を入力します。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mercell SSO の構成

**Mercell** 側でシングル サインオンを構成するには、**アプリ フェデレーション メタデータ URL** を [Mercell サポート チーム](mailto:webmaster@mercell.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Mercell テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Mercell に作成します。 Mercell では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Mercell にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

手記

ユーザーを手動で作成する必要がある場合は、Mercell サポート チーム にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Mercell に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Mercell] タイルを選択すると、SSO を設定した Mercell に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mercerhrs-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Mercer BenefitsCentral (MBC) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mercerhrs-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mercer BenefitsCentral (MBC) 間にシングル サインオンを構成する方法について学習します。

この記事では、Mercer BenefitsCentral (MBC) と Microsoft Entra ID を統合する方法について説明します。 Mercer BenefitsCentral (MBC) と Microsoft Entra ID を統合すると、次のようなベネフィットが得られます。

- Mercer BenefitsCentral (MBC) にアクセスするユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Mercer BenefitsCentral (MBC) に自動的にサインイン (シングル サインオン) できるようにすることが可能です。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mercer BenefitsCentral (MBC) でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Mercer BenefitsCentral (MBC) では、 **IDP** Initiated SSO がサポートされます

### ギャラリーから Mercer BenefitsCentral (MBC) を追加する

Microsoft Entra ID への Mercer BenefitsCentral (MBC) の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Mercer BenefitsCentral (MBC) を追加する必要があります。

**ギャラリーから Mercer BenefitsCentral (MBC) を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新規アプリケーション**に移動します。
3. 検索ボックスに「 **Mercer BenefitsCentral (MBC)」**と入力し、結果パネルで **Mercer BenefitsCentral (MBC)** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Mercer BenefitsCentral (MBC)]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Mercer BenefitsCentral (MBC) で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Mercer BenefitsCentral (MBC) 内の関連ユーザー間にリンク関係が確立されている必要があります。

Mercer BenefitsCentral (MBC) で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Mercer BenefitsCentral (MBC) シングル サインオン** の構成 - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Mercer BenefitsCentral (MBC) テスト ユーザーの作成** - Mercer BenefitsCentral (MBC) で Britta Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Mercer BenefitsCentral (MBC) で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Mercer BenefitsCentral (MBC)** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    [Image: Mercer BenefitsCentral (MBC) ドメインおよびURLのシングルサインオン情報]

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `stg.mercerhrs.com/saml2.0`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://ssous-stg.mercerhrs.com/SP2/Saml2AssertionConsumer.aspx`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには [、Mercer BenefitsCentral (MBC) クライアント サポート チーム](https://www.mercer.com/en-gb/about/contact/contact-us/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Mercer BenefitsCentral (MBC) のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    ある。 ログイン URL

    b。 Microsoft Entra アイデンティファイヤー

    c. ログアウト URL

#### Mercer BenefitsCentral (MBC) シングル サインオンの構成

**Mercer BenefitsCentral (MBC)** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Mercer BenefitsCentral (MBC) サポート チーム](https://www.mercer.com/en-gb/about/contact/contact-us/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Mercer BenefitsCentral (MBC) テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Mercer BenefitsCentral (MBC) 内に作成します。 [Mercer BenefitsCentral (MBC) サポート チーム](https://www.mercer.com/en-gb/about/contact/contact-us/)と協力して、Mercer BenefitsCentral (MBC) プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Mercer BenefitsCentral (MBC)] タイルを選択すると、SSO を設定した Mercer BenefitsCentral (MBC) に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/merchlogix-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に MerchLogix を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/merchlogix-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-16
- Summary: MerchLogix に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、MerchLogix と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを MerchLogix に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MerchLogix テナント
- ユーザー プロビジョニングに必要な SCIM エンドポイント URL とシークレット トークンを提供できる MerchLogix の技術担当者

### ギャラリーからの Merchlogix の追加

Microsoft Entra ID で自動ユーザー プロビジョニング用に MerchLogix を構成する前に、MerchLogix を Microsoft Entra アプリケーション ギャラリーから管理対象の SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから MerchLogix を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「MerchLogix**」と入力します。
4. 結果パネルから **MerchLogix** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### MerchLogix へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスを付与するユーザーを決定する際に "割り当て" という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに "割り当て済み" のユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、MerchLogix へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定したら、次の手順に従って、これらのユーザーやグループを MerchLogix に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを MerchLogix に割り当てる際の重要なヒント

- 最初の自動ユーザー プロビジョニング構成をテストするには、1 人の Microsoft Entra ユーザーを MerchLogix に割り当てることをお勧めします。 テストが成功すれば、後でユーザーやグループを追加で割り当てられます。
- MerchLogix にユーザーを割り当てるときは、アプリケーション固有の有効なロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### MerchLogix への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、MerchLogix でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

MerchLogix のシングル サインオンに関する記事で説明されている手順に従って、 [MerchLogix](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/merchlogix-tutorial) で SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で MerchLogix に対する自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**を参照する
3. アプリケーションの一覧から **MerchLogix** を選択します。
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、MerchLogix テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が MerchLogix に接続できることを確認します。 接続に失敗した場合は、MerchLogix アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から MerchLogix に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で MerchLogix のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から MerchLogix に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で MerchLogix のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/merchlogix-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Merchlogix を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/merchlogix-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Merchlogix の間にシングル サインオンを構成する方法について説明します。

この記事では、Merchlogix と Microsoft Entra ID を統合する方法について説明します。 Merchlogix を Microsoft Entra ID と統合すると、次のことができます。

- Merchlogix にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Merchlogix に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Merchlogix でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Merchlogix では、**SP** Initiated SSO がサポートされます。
- Merchlogix では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/merchlogix-provisioning-tutorial)がサポートされます。

### ギャラリーからの Merchlogix の追加

Microsoft Entra ID への Merchlogix の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Merchlogix を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Merchlogix**」と入力します。
4. 結果のパネルから **[Merchlogix]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Merchlogix 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Merchlogix に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Merchlogix の関連ユーザーとの間にリンク関係を確立する必要があります。

Merchlogix に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Merchlogix SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Merchlogix テストユーザーの作成** - Microsoft Entra 上のユーザーの表現とリンクする B.Simon の対応ユーザーとして Merchlogix に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Merchlogix**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<DOMAIN>/simplesaml/module.php/saml/sp/metadata.php/<SAML_NAME>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<DOMAIN>/login.php?saml=true`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Merchlogix クライアント サポート チーム](https://www.merchlogix.com/contact/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Merchlogix のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Merchlogix SSO の構成

**Merchlogix** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を、[Merchlogix サポート チーム](https://www.merchlogix.com/contact/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Merchlogix テスト ユーザーの作成

このセクションでは、Merchlogix で Britta Simon というユーザーを作成します。 [Merchlogix サポート チーム](https://www.merchlogix.com/contact/)と連携し、Merchlogix プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Merchlogix は、自動ユーザー プロビジョニングもサポートしています。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/merchlogix-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Merchlogix のサインオン URL にリダイレクトされます。
- Merchlogix のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Merchlogix] タイルを選択すると、このオプションは Merchlogix のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/meta-networks-connector-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Meta Networks Connector を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/meta-networks-connector-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-16
- Summary: Microsoft Entra ID を構成して、ユーザー アカウントを Meta Networks Connector に自動的にプロビジョニング/プロビジョニング解除する方法を説明します。

この記事の目的は、Meta Networks Connector と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Meta Networks Connector に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Meta Networks Connector テナント](https://www.metanetworks.com/)
- 管理者のアクセス許可を持つ Meta Networks Connector のユーザー アカウント。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Meta Networks Connector の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### Meta Networks Connector へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に*割り当て*という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーとグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Meta Networks Connector へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Meta Networks Connector に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Meta Networks Connector に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを Meta Networks Connector に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Meta Networks Connector にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### ステップ 2: プロビジョニング用に Meta Networks Connector を構成する

1. ご自身の組織名を使用して [Meta Networks Connector 管理コンソール](https://login.metanetworks.com/login/)にサインインします。 **[認証] &gt; [API キー]** に移動します。

    [Image: Meta Networks Connector 管理コンソール]
2. 画面の右上にあるプラス記号を選択して、新しい **API キー**を作成します。
3. **[API キー名]** と **[API キーの説明]** を設定します。

    [Image: Meta Networks Connector 管理コンソールのスクリーンショット。Microsoft Entra ID と API キーの [API キー名] 値と [API キーの説明] 値が強調表示されています。]
4. **[グループ]** と **[ユーザー]** の **[書き込み]** をオンにします。

    [Image: Meta Networks Connector の権限]
5. [**] を選択し、[**] を追加します。 **SECRET** をコピーし、表示できる唯一の時間として保存します。 この値は、Meta Networks Connector アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。

    [Image: API キーが追加されたことをユーザーに伝えるウィンドウのスクリーンショット。[シークレット] ボックスには解読できない値が含まれており、強調表示されています。]
6. **[管理] &gt; [設定] &gt; [IdP] &gt; [新規作成]** に移動して IdP を追加します。

    [Image: Meta Networks Connector の IdP の追加]
7. **[IdP Configuration](IdP 構成)** ページで、IdP 構成の**名前**を指定し、**アイコン**を選択できます。

    [Image: Meta Networks Connector の IdP 名]

    [Image: Meta Networks Connector の IdP アイコン]
8. **[Configure SCIM](SCIM の構成)** で、前述のステップで作成した API キー名を選択します。 **保存** を選択します。

    [Image: Meta Networks Connector の SCIM の構成]
9. [ **管理] &gt; [設定] &gt; [IdP] タブに移動します**。IdP ID を表示するには、前の手順で作成した **IdP** 構成の名前を選択します。 この **ID** は、Meta Networks Connector アプリケーションの [プロビジョニング] タブにある **[テナント URL]** フィールドに値を入力するときに、**テナント URL** の末尾に追加されます。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Meta Networks Connector を追加する

Meta Networks Connector へのプロビジョニングの管理を開始するために、Microsoft Entra アプリケーション ギャラリーから Meta Networks Connector を追加します。 以前に Meta Networks Connector を SSO 用に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ5: Meta Networks Connector への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて Meta Networks Connector のユーザーやグループを作成、更新、無効化するよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Meta Networks Connector のシングル サインオンに関する記事で説明されている手順に従って、Meta Networks Connector で SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/metanetworksconnector-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは独立に構成できますが、これらの 2 つの機能は互いに補完しあいます。

#### Microsoft Entra ID で Meta Networks Connector の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **[Meta Networks Connector]** を選択します。

    [Image: アプリケーションの一覧の Meta Networks Connector リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションの `https://api.metanetworks.com/v1/scim/<IdP ID>` に「」と入力します。 **[シークレット トークン]** に先ほど取得した**SCIM 認証トークン**の値を入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Meta Networks Connector に接続できることを確認します。 接続できない場合は、使用中の Meta Networks Connector アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: テナント URL + トークン]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Meta Networks Connector に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Meta Networks Connector のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Meta Networks Connector API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Meta Networks Connector で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | phonenumbers[type eq "work"].value | 糸 |  |  |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |

    注

    phonenumbers 値は E164 形式である必要があります。 (例: +16175551212)。
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Meta Networks Connector に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Meta Networks Connector のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Meta Networks Connector API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Meta Networks Connector で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | 関連項目 |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更履歴

2022 年 4 月 6 日 - **phoneNumbers[type eq "work"].value** のサポートを追加しました。 **emails[type eq "work"].value** と **manager** のサポートを削除しました。 **name.givenName** と **name.familyName** が必須属性になりました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/meta-work-accounts-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Meta Work Accounts を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/meta-work-accounts-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Meta Work Accounts の間にシングル サインオンを構成する方法について説明します。

この記事では、Meta Work アカウントと Microsoft Entra ID を統合する方法について説明します。 Meta Work Accounts を Microsoft Entra ID を統合すると、次のことができます:

- Meta Work Accounts にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Meta Work Accounts に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Meta Work Accounts のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Meta Work Accounts では、**SP と IDP** によって開始される SSO がサポートされています。

### ギャラリーから Meta Work Accounts を追加する

Microsoft Entra ID への Meta Work Accounts の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Meta Work Accounts を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Meta Work Accounts**」と入力します。
4. 結果パネルから **[Meta Work Accounts]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Meta Work Accounts 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Meta Work Accounts で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Meta Work Accounts の関連ユーザーの間にリンク関係を確立する必要があります。

Meta Work Accounts に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Meta Work Accounts の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Meta Work Accounts のテスト ユーザーの作成 - Meta Work Accounts** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Meta Work Accounts**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://work.facebook.com/company/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 ` https://work.facebook.com/work/saml.php?__cid=<ID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://work.facebook.com`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Work Accounts チーム](https://www.workplace.com/help/work)に依頼してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Meta Work Accounts のセットアップ]** セクションで、実際の要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Meta Work Accounts の SSO を構成する

1. ご自分の Meta Work Accounts の企業サイトに管理者としてログインします。
2. **[セキュリティ]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン (SSO)** を有効にする] チェック ボックスをオンにして、[**+新しい SSO プロバイダーの追加]** を選択します。

1. **[シングル サインオン(SSO) の設定]** ページで、次の手順を行います。

1. 有効な **SSO プロバイダーの名前**を入力します。
2. **[SAML URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。
3. **[SAML Issuer URL](SAML 発行者 URL)** テキストボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。
4. **[SAML Logout URL](SAML ログアウト URL)** テキストボックスの **[Enable SAML logout redirection](SAML ログアウト リダイレクトを有効にする)** チェックボックスをオンにして、以前コピーした**ログアウト URL** の値を貼り付けます。
5. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[SAML 証明書]** テキストボックスに貼り付けます。
6. **[対象ユーザー URL]** の値をコピーし、その値を **[基本的な SAML 構成]** セクションの **[識別子]** テキストボックスに貼り付けます。
7. **[ACS (Assertion Consumer Service) URL]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[応答 URL]** テキスト ボックスに貼り付けます。
8. [ **Test SSO Setup]\(SSO セットアップのテスト** \) セクションで、テキスト ボックスに有効な電子メールを入力し、[ **Test SSO**]\(SSO のテスト\) を選択します。
9. [ **変更の保存] を選択します**。

#### Meta Work Accounts テスト ユーザーを作成する

このセクションでは、Meta Work Accounts で Britta Simon というユーザーを作成します。 [Work Accounts チーム](https://www.workplace.com/help/work)と連携して、Meta Work Accounts プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Meta Work Accounts のサインオン URL にリダイレクトされます。
- Meta Work Accounts のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Meta Work Accounts に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Meta Work Accounts] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Meta Work Accounts に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/meta4-global-hr-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Meta4 Global HR を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/meta4-global-hr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Meta4 Global HR の間にシングル サインオンを構成する方法について説明します。

この記事では、Meta4 Global HR と Microsoft Entra ID を統合する方法について説明します。 Meta4 Global HR を Microsoft Entra ID と統合すると、次のことができます。

- Meta4 Global HR にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Meta4 Global HR に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Meta4 Global HR でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Meta4 Global HR では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Meta4 Global HR の追加

Microsoft Entra ID への Meta4 Global HR の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Meta4 Global HR を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Meta4 Global HR**」と入力します。
4. 結果パネルから **[Meta4 Global HR]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Meta4 Global HR 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Meta4 Global HR に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Meta4 Global HR の関連ユーザーの間にリンク関係を確立する必要があります。

Meta4 Global HR に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Meta4 Global HR SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Meta4 Global HR テストユーザーを作成して、Meta4 Global HR で B.Simon に対応するユーザーを設定し、それを Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Meta4 Global HR**&gt;**シングルサインオンに移動します。**
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.meta4globalhr.com/saml.sso/SAML2/POST`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.meta4globalhr.com`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには、[Meta4 Global HR クライアント サポート チーム](mailto:victors@meta4.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Set up Meta4 Global HR]** (Meta4 Global HR のセットアップ) セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Meta4 Global HR SSO の構成

**Meta4 Global HR** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーションの構成からコピーした適切な URL を [Meta4 Global HR サポート チーム](mailto:victors@meta4.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Meta4 Global HR テスト ユーザーの作成

このセクションでは、Meta4 Global HR で Britta Simon というユーザーを作成します。 [Meta4 Global HR サポート チーム](mailto:victors@meta4.com)と連携して、Meta4 Global HR プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Meta4 Global HR サインオン URL にリダイレクトされます。
- Meta4 Global HR のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Meta4 Global HR に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Meta4 Global HR] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Meta4 Global HR に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/metanetworksconnector-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Meta Networks Connector を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/metanetworksconnector-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Meta Networks Connector の間でシングル サインオンを構成する方法について説明します。

この記事では、Meta Networks Connector と Microsoft Entra ID を統合する方法について説明します。 Meta Networks Connector と Microsoft Entra ID を統合すると、次のことができるようになります。

- Meta Networks Connector にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Meta Networks Connector に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Meta Networks Connector でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Meta Networks Connector では、**SP Initiated SSO** と **IDP Initiated SSO** がサポートされます。
- Meta Networks Connector では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Meta Networks Connector では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/meta-networks-connector-provisioning-tutorial)がサポートされます。

### ギャラリーからの Meta Networks Connector の追加

Microsoft Entra ID への Meta Networks Connector の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Meta Networks Connector を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Meta Networks Connector**」と入力します。
4. 結果のパネルから **[Meta Networks Connector]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Meta Networks Connector 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Meta Networks Connector に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Meta Networks Connector の関連ユーザーとの間にリンク関係を確立する必要があります。

Meta Networks Connector に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Meta Networks Connector SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Meta Networks Connector のテスト ユーザーの作成** - Meta Networks Connector において Microsoft Entra のユーザー表現の B.Simon にリンクされた B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Meta Networks Connector**&gt;**シングルサインオン**を開きます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを**IDP** イニシエートモードで構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.nsof.io/v1/<ORGANIZATION-SHORT-NAME>/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.nsof.io/v1/<ORGANIZATION-SHORT-NAME>/sso/saml`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ORGANIZATION-SHORT-NAME>.metanetworks.com/login`

    b。 **[リレー状態]** ボックスに、`https://<ORGANIZATION-SHORT-NAME>.metanetworks.com/#/` のパターンで URL を入力します。

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子、応答 URL、Sign-On URL で更新する方法については、後で説明します。
7. Meta Networks Connector アプリケーションでは、特定の形式の SAML アサーションが求められます。そのため、カスタム属性マッピングを SAML トークン属性構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ **ユーザー属性] ダイアログを** 開きます。

    [Image: スクリーンショットには、[編集] アイコンが選択された [ユーザー属性] が表示されます。]
8. その他に、Meta Networks Connector アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 | Namespace |
    | --- | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |  |
    | 名字 | ユーザーの名字 |  |
    | メールアドレス | ユーザーのメールアドレス | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
    | 名前 | ユーザー.ユーザープリンシパルネーム | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
    | 電話 | ユーザー.電話番号 |  |

    ある。 [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: スクリーンショットには、[新しい要求の追加] オプションを含むユーザー要求が表示されます。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    え **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **[OK]** を選択します。

    ジー **保存** を選択します。
9. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Meta Networks Connector のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Meta Networks Connector SSO の構成

1. ブラウザーで新しいタブを開き、Meta Networks Connector の管理者アカウントにログインします。

    注

    Meta Networks Connector は、セキュリティで保護されたシステムです。 したがって、ポータルにアクセスする前に、接続先側でパブリック IP アドレスを許可リストに登録する必要があります。 パブリック IP アドレスを取得するには、[ここ](https://whatismyipaddress.com/)で指定されているリンクに従います。 IP アドレスを [Meta Networks Connector クライアント サポート チーム](mailto:support@metanetworks.com)に送信して、IP アドレスを許可リストに登録してもらいます。
2. **[管理者]** に移動して **[設定]** を選択します。

    [Image: [Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) メニューの [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) が選択されているスクリーンショット。]
3. **[Log Internet Traffic](インターネット トラフィックのログ記録)** と **[Force VPN MFA](VPN MFA の強制)** がオフに設定されていることを確認します。

    [Image: これらの設定がオフにされているスクリーンショット。]
4. **[管理者]** に移動して **[SAML]** を選択します。

    [Image: [Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) メニューの [SAML] が選択されているスクリーンショット。]
5. **[DETAILS](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細)** タブで次の手順を実行します。

    [Image: 説明されている値を入力できる [DETAILS](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細) ページを示すスクリーンショット。]

    ある。 **[SSO URL]** の値をコピーし、 **[Meta Networks Connector ドメインと URL]** セクションの **[サインイン URL]** テキスト ボックスに貼り付けます。

    b。 **[Recipient URL](受信者 URL)** の値をコピーし、 **[Meta Networks Connector ドメインと URL]** セクションの **[応答 URL]** テキスト ボックスに貼り付けます。

    c. **[Audience URI (SP Entity ID)](オーディエンス URI (SP エンティティ ID))** の値をコピーし、 **[Meta Networks Connector ドメインと URL]** セクションの **[識別子 (エンティティ ID)]** テキスト ボックスに貼り付けます。

    d. SAML を有効にします。
6. **[GENERAL](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般)** タブで、次の手順を実行します。

    [Image: 説明されている値を入力できる [GENERAL](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般) ページを示すスクリーンショット。]

    ある。 **[Identity Provider Single Sign-On URL] (ID プロバイダーのシングル サインオン URL)** に、先ほどコピーした **ログイン URL** の値を貼り付けます。

    b。 **[ID プロバイダーの発行者]** に、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    c. Azure Portal からダウンロードした証明書をメモ帳で開き、 **[X.509 Certificate](X.509 証明書)** ボックスに貼り付けます。

    d. **[Just-in-Time Provisioning](ジャストイン タイム プロビジョニング)** を有効にします。

#### Meta Networks Connector のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Meta Networks Connector に作成します。 Meta Networks Connector では、Just-In-Time プロビジョニングがサポートされています。この設定は、既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Meta Networks Connector に存在しない場合は、Meta Networks Connector にアクセスしようとしたときに新しいユーザーが作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Meta Networks Connector クライアント サポート チーム](mailto:support@metanetworks.com)にお問い合わせください。

Meta Networks は、自動ユーザー プロビジョニングもサポートしています。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/meta-networks-connector-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Meta Networks Connector のサインオン URL にリダイレクトされます。
- Meta Networks Connector のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Meta Networks Connector に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Meta Networks Connector] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Meta Networks Connector に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/metatask-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Metatask を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/metatask-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Metatask の間にシングル サインオンを構成する方法について説明します。

この記事では、Metatask と Microsoft Entra ID を統合する方法について説明します。 Metatask を Microsoft Entra ID を統合すると、次のことができます。

- Metatask にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Metatask に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Metatask でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Metatask では、**SP開始 SSO** と **IDP開始 SSO** がサポートされます。
- Metatask では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Metatask を追加する

Microsoft Entra ID への Metatask の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Metatask を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Metatask**」と入力します。
4. 結果パネルから **[Metatask** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Metatask 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Metatask に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Metatask の関連ユーザーとの間にリンク関係を確立する必要があります。

Metatask に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Metatask の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Metatask テストユーザーを作成** - Microsoft Entra のユーザー表現とリンクされた Metatask の B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Metatask**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<DOMAIN_NAME>.metatask.io/api/authenticate/saml`

    b。 [ **リレー状態** ] ボックスに、次のパターンを使用して値を入力します。 `<DOMAIN_NAME>`

    注

    これらの値は実際の値ではありません。 これらの値を、実際のサインオン URL およびリレー状態で更新してください。 これらの値を取得するには、 [Metatask クライアント サポート チーム](mailto:support@metatask.io) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Metatask アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Metatask アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | display\_name | ユーザー表示名 |
    | メール | ユーザーのメールアドレス |
    | 苗字 | ユーザーの名字 |
    | 名（ファーストネーム） | ユーザー.ファーストネーム |
    | 位置 | ユーザー.ユーザープリンシパルネーム |
    | ユーザー名 | ユーザー.オブジェクトID |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Metatask SSO を構成する

Metatask 側でシングル サインオンを構成するには、**Metatask** **サポート チーム**に[アプリのフェデレーション メタデータ URL を](mailto:support@metatask.io)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Metatask テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Metatask に作成します。 Metatask では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Metatask にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Metatask のサインオン URL にリダイレクトされます。
- Metatask のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Metatask に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Metatask] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Metatask に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/metlife-legal-plans-member-app-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に MetLife Legal Plans メンバー アプリを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/metlife-legal-plans-member-app-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MetLife Legal Plans メンバー アプリの間でシングル サインオンを構成する方法について説明します。

この記事では、MetLife Legal Plans メンバー アプリと Microsoft Entra ID を統合する方法について説明します。 MetLife Legal Plans メンバー アプリを Microsoft Entra ID と統合すると、次のことができます。

- MetLife Legal Plans メンバー アプリにアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して MetLife Legal Plans メンバー アプリに自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MetLife Legal Plans メンバー アプリでのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MetLife Legal Plans メンバー アプリでは、 **IDP** によって開始される SSO のみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから MetLife Legal Plans メンバー アプリを追加する

Microsoft Entra ID への MetLife Legal Plans メンバー アプリの統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MetLife Legal Plans メンバー アプリを追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「MetLife Legal Plans Member App**」と入力します。
4. 結果パネルから **MetLife Legal Plans メンバー アプリ** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MetLife Legal Plans メンバー アプリの Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、MetLife Legal Plans メンバー アプリに対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと MetLife Legal Plans Member App の関連ユーザーとの間にリンク関係を確立する必要があります。

MetLife Legal Plans メンバー アプリに対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MetLife Legal Plans メンバー アプリの SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **MetLife Legal Plans Member App の B.Simon に対応するユーザーを作成し、Microsoft Entra ID にリンクする** - MetLife Legal Plans Member App における Microsoft Entra ID 表現のユーザーにリンクされた B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**MetLife Legal Plans メンバー アプリ**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリは既に Microsoft Entra と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. MetLife Legal Plans Member App アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]

    注

    アプリケーション側の要件に従って、両方の側で SSO 接続を正しく動作させるには、上記のスクリーンショットに示されている **[追加の要求**] セクションで、**emailaddress** の名前を **EmailAddress** に変更し、この属性の名前空間を手動で削除してください。
7. 上記に加えて、MetLife Legal Plans メンバー アプリ アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 社員ID | ユーザー.社員ID |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **MetLife Legal Plans Member App のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MetLife Legal Plans メンバー アプリの SSO の構成

**MetLife Legal Plans メンバー アプリ**側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** と、Microsoft Entra 管理センターからコピーした適切な URL を [MetLife Legal Plans メンバー アプリ サポート チーム](mailto:microsoftsupport@legalplans.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MetLife Legal Plans メンバー アプリのテストユーザーを作成する

このセクションでは、MetLife Legal Plans メンバー アプリで B.Simon というユーザーを作成します。 [MetLife Legal Plans メンバー アプリ サポート チーム](mailto:microsoftsupport@legalplans.com)と協力して、MetLife Legal Plans メンバー アプリ プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した MetLife Legal Plans メンバー アプリに自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [MetLife Legal Plans Member App] タイルを選択すると、SSO を設定した MetLife Legal Plans メンバー アプリに自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mevisio-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Mevisio を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mevisio-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mevisio 間にシングル サインオンを構成する方法について説明します。

この記事では、Mevisio と Microsoft Entra ID を統合する方法について説明します。 Mevisio と Microsoft Entra ID を統合すると、次のことができます。

- Mevisio にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Mevisio に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mevisio でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Mevisio では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Mevisio では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Mevisio の追加

Microsoft Entra ID への Mevisio の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Mevisio を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Mevisio**」と入力します。
4. 結果のパネルから **[Mevisio]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mevisio 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Mevisio で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Mevisio の関連ユーザーとの間にリンク関係を確立する必要があります。

Mevisio に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mevisio の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mevisio テスト ユーザーの作成 - Mevisio** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Mevisio**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.mevisio.com/identity/saml2/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.mevisio.com/identity/saml2/login`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.mevisio.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 この値を取得するには、[Mevisio クライアント サポート チーム](mailto:support@mevisio.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Mevisio アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Mevisio アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mevisio の SSO の構成

**Mevisio** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Mevisio サポート チーム](mailto:support@mevisio.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Mevisio のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Mevisio 内に作成します。 Mevisio では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Mevisio にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Mevisio のサインオン URL にリダイレクトされます。
- Mevisio のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Mevisio に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [Mevisio] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Mevisio に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mic-saas-portal-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に MIC SAAS ポータルを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mic-saas-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MIC SAAS Portal 間にシングル サインオンを構成する方法について説明します。

この記事では、MIC SAAS ポータルと Microsoft Entra ID を統合する方法について説明します。 MIC SAAS Portal を Microsoft Entra ID と統合すると、次のことができます。

- MIC SAAS Portal にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って MIC SAAS Portal に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な MIC SAAS Portal のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MIC SAAS Portal では、**SP** Initiated SSO がサポートされます。
- MIC SAAS Portal では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの MIC SAAS Portal の追加

Microsoft Entra ID への MIC SAAS Portal の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MIC SAAS Portal を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**MIC SAAS Portal**」と入力します。
4. 結果パネルから **[MIC SAAS Portal]** を選び、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MIC SAAS Portal 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、MIC SAAS Portal に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと MIC SAAS Portal の関連ユーザーとの間にリンク関係を確立する必要があります。

MIC SAAS Portal に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MIC SAAS Portal の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MIC SAASポータルでテストユーザーを作成する** - B.Simon に対応するユーザーを作成し、Microsoft Entra ID にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**MIC SAAS Portal**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://sso.eu.micgtm.com/auth/realms/<INSTANCE>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://sso.eu.micgtm.com/auth/realms/<INSTANCE>/broker/<PROVIDER>/endpoint`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://gtmportal.eu.micgtm.com/?idp=<INSTANCE>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[MIC SAAS Portal サポート チーム](mailto:support@mic-cust.com)にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[MIC SAAS Portal の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MIC SAAS Portal SSO を構成する

**MIC SAAS Portal** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と Microsoft Entra 管理センターからコピーした適切な URL を [MIC SAAS Portal サポート チーム](mailto:support@mic-cust.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MIC SAAS Portal テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを MIC SAAS Portal に作成します。 MIC SAAS Portal では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 MIC SAAS Portal にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる MIC SAAS ポータルのサインオン URL にリダイレクトします。
- MIC SAAS Portal のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [MIC SAAS ポータル] タイルを選択すると、このオプションは MIC SAAS ポータルのサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/michigan-data-hub-single-sign-on-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用にミシガン Data Hub シングル Sign-On を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/michigan-data-hub-single-sign-on-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Michigan Data Hub Single Sign-On の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、Michigan Data Hub Single Sign-On と Microsoft Entra ID を統合する方法について説明します。 Michigan Data Hub Single Sign-On を Microsoft Entra ID と統合すると、次のことができます。

- Michigan Data Hub Single Sign-On にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Michigan Data Hub Single Sign-On に自動的にサインインするように設定できます。
- 1 つの中央の場所でアカウントを管理します。

ミシガン データ ハブの単一 Sign-On は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Michigan Data Hub Single Sign-On でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Michigan Data Hub Single Sign-On では、**SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Michigan Data Hub Single Sign-On の追加

Microsoft Entra ID への Michigan Data Hub Single Sign-On の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Michigan Data Hub Single Sign-On を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Michigan Data Hub Single Sign-On**」と入力します。
4. 結果のパネルから **Michigan Data Hub Single Sign-On** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Michigan Data Hub Single Sign-On の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Michigan Data Hub Single Sign-On で Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Michigan Data Hub Single Sign-On の関連ユーザーとの間にリンク関係を確立する必要があります。

Michigan Data Hub Single Sign-On で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Michigan Data Hub Single Sign-On の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Michigan Data Hub Single Sign-On テスト ユーザーの作成** - ミシガンData Hub Single Sign-OnでB.Simonに対応するユーザーを作成し、このユーザーをMicrosoft Entraのユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Michigan Data Hub シングル サインオン**&gt;**シングル サインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://launchpad.midatahub.org`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Michigan Data Hub Single Sign-On の SSO の構成

**Michigan Data Hub Single Sign-On** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Michigan Data Hub Single Sign-On のサポート チーム](mailto:support@midatahub.org)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Michigan Data Hub Single Sign-On のテスト ユーザーの作成

このセクションでは、Michigan Data Hub Single Sign-On で B.Simon というユーザーを作成します。 [Michigan Data Hub Single Sign-On のサポート チーム](mailto:support@midatahub.org)と連携して、Michigan Data Hub Single Sign-On プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる、Michigan Data Hub Single Sign-On サインオン URL にリダイレクトされます。
- Michigan Data Hub Single Sign-On のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Michigan Data Hub Single Sign-On] タイルを選択すると、このオプションは、Michigan Data Hub Single Sign-On のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mihcm-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に MiHCM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mihcm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MiHCM の間でシングル サインオンを構成する方法について説明します。

この記事では、MiHCM と Microsoft Entra ID を統合する方法について説明します。 MiHCM と Microsoft Entra ID を統合すると、次のことができます。

- MiHCM にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して MiHCM に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MiHCM でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MiHCM では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから MiHCM を追加する

Microsoft Entra ID への MiHCM の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MiHCM を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「MiHCM**」と入力します。
4. 結果パネルから **MiHCM** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MiHCM の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、MiHCM に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと MiHCM の関連ユーザーとの間にリンク関係を確立する必要があります。

MiHCM に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MiHCM SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MiHCM テストユーザーを作成する** - Microsoft Entra の B.Simon にリンクされた、MiHCM で B.Simon に対応するユーザーを作ります。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**MiHCM**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    エイ。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.mihcm.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.mihcm.com/<subdomain>/Acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.mihcm.com`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [MiHCM クライアント サポート チーム](mailto:support@mihcm.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MiHCM SSO の構成

**MiHCM** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[MiHCM サポート チーム](mailto:support@mihcm.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MiHCM テスト ユーザーの作成

このセクションでは、MiHCM で Britta Simon というユーザーを作成します。 MiHCM サポート チーム  と連携して、MiHCM プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる MiHCM サインオン URL にリダイレクトされます。
- MiHCM のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [MiHCM] タイルを選択すると、このオプションは MiHCM のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mimecast-personal-portal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Mimecast を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mimecast-personal-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mimecast の間のシングル サインオンを構成する方法について説明します。

この記事では、Mimecast と Microsoft Entra ID を統合する方法について説明します。 Mimecast を Microsoft Entra ID と統合すると、次のことが可能になります。

- Mimecast にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Mimecast に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Mimecast のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Mimecast では、**SP開始SSO**と**IDP開始SSO**がサポートされます。

### ギャラリーからの Mimecast の追加

Microsoft Entra ID への Mimecast の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Mimecast を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Mimecast**」と入力します。
4. 結果パネルから **Mimecast** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mimecast に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Mimecast に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Mimecast の関連ユーザー間にリンク関係を確立する必要があります。

Mimecast に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mimecast SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mimecast テスト ユーザーの作成** - Mimecast で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Mimecast**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、IDP 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | リージョン | 価値 |
    | --- | --- |
    | ヨーロッパ | `https://eu-api.mimecast.com/sso/<accountcode>` |
    | 米国 | `https://us-api.mimecast.com/sso/<accountcode>` |
    | 南アフリカ | `https://za-api.mimecast.com/sso/<accountcode>` |
    | オーストラリア | `https://au-api.mimecast.com/sso/<accountcode>` |
    | オフショア | `https://jer-api.mimecast.com/sso/<accountcode>` |

    注

    `accountcode`値は Mimecast の [**Account**&gt;**Settings**&gt;] で [**Account Code**] の下にあります。 `accountcode` を識別子に追加します。

    b。 [ **応答 URL** ] ボックスに、次のいずれかの URL を入力します。

    | リージョン | 価値 |
    | --- | --- |
    | ヨーロッパ | `https://eu-api.mimecast.com/login/saml` |
    | 米国 | `https://us-api.mimecast.com/login/saml` |
    | 南アフリカ | `https://za-api.mimecast.com/login/saml` |
    | オーストラリア | `https://au-api.mimecast.com/login/saml` |
    | オフショア | `https://jer-api.mimecast.com/login/saml` |
6. **SP** 開始モードでアプリケーションを構成する場合:

    [ **サインオン URL** ] ボックスに、次のいずれかの URL を入力します。

    | リージョン | 価値 |
    | --- | --- |
    | ヨーロッパ | `https://eu-api.mimecast.com/login/saml` |
    | 米国 | `https://us-api.mimecast.com/login/saml` |
    | 南アフリカ | `https://za-api.mimecast.com/login/saml` |
    | オーストラリア | `https://au-api.mimecast.com/login/saml` |
    | オフショア | `https://jer-api.mimecast.com/login/saml` |
7. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mimecast SSO の構成

1. 別の Web ブラウザー ウィンドウで、Mimecast Administration Console にサインインします。
2. **Administration**&gt;**Services**&gt;**Applications** に移動します。

    [Image: [アプリケーション] が選択された [Mimecast] ウィンドウを示すスクリーンショット。]
3. [ **認証プロファイル** ] タブを選択します。

    [Image: [認証プロファイル] が選択されている [アプリケーション] タブを示すスクリーンショット。]
4. [ **新しい認証プロファイル] タブを** 選択します。

    [Image: 選択された新しい認証プロファイルを示すスクリーンショット。]
5. [ **説明** ] ボックスに有効な説明を入力し、[ **Mimecast に SAML 認証を適用** する] チェック ボックスをオンにします。

    [Image: [新しい認証プロファイル] が選択されているスクリーンショット。]
6. [ **SAML Configuration for Mimecast]** ページで、次の手順を実行します。

    [Image: [管理コンソールに SAML 認証を適用する] を選択する場所を示すスクリーンショット。]

    a. **プロバイダー**の場合は、ドロップダウンから **Microsoft Entra ID を**選択します。

    b。 [ **メタデータ URL** ] ボックスに、前にコピーした **アプリのフェデレーション メタデータ URL** の値を貼り付けます。

    c. [ **インポート] を選択します**。 メタデータ URL をインポートすると、フィールドは自動的に設定され、これらのフィールドに対してアクションを実行する必要はありません。

    d. [ **パスワードで保護されたコンテキストを使用** する] チェック ボックスと [ **統合認証コンテキストを使用する** ] チェック ボックスをオフにしてください。

    e. **[保存] を選択します**。

#### Mimecast テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、Mimecast Administration Console にサインインします。
2. **管理**&gt;**ディレクトリ**&gt;**内部ディレクトリ**に移動します。

    [Image: Mimecast の SAML 構成を示すスクリーンショット。ここで、説明されている値を入力できます。]
3. ドメインが以下に記載されている場合は、ドメインを選択します。それ以外の場合は、[新しいドメイン] を選択して **新しいドメイン**を作成してください。

    [Image: [内部ディレクトリ] が選択された [Mimecast] ウィンドウを示すスクリーンショット。]
4. [ **新しいアドレス] タブを** 選択します。

    [Image: 選択されたドメインを示すスクリーンショット。]
5. 次のページで、必要なユーザー情報を入力します。

    [Image: スクリーンショットは、説明されている値を入力できるページを示しています。]

    a. [ **電子メール アドレス]** ボックスに、ユーザーの電子メール アドレス ( `B.Simon@yourdomainname.com`など) を入力します。

    b。 [ **グローバル名]** ボックスに、ユーザーの **フル ネーム** を入力します。

    c. [ **パスワード** ] ボックスと [ **パスワードの確認** ] ボックスに、ユーザーのパスワードを入力します。

    d. [ **ログイン時に強制的に変更** する] チェック ボックスをオンにします。

    e. **[保存] を選択します**。

    f. ユーザーにロールを割り当てるには、[ **ロールの編集]** を選択し、組織の要件に従って必要なロールをユーザーに割り当てます。

    [Image: [ロールの編集] を選択できる [アドレス設定] を示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Mimecast のサインオン URL にリダイレクトされます。
- Mimecast のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Mimecast に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Mimecast] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Mimecast に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mindflash-tutorial"} -->
## Microsoft Entra ID で Trakstar Learn for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mindflash-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Trakstar Learn (Mindflash) の間にシングル サインオンを構成する方法について説明します。

この記事では、Trakstar Learn (Mindflash) と Microsoft Entra ID を統合する方法について説明します。 Learn と Microsoft Entra ID を統合すると、次のことができます:

- Learn にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Learn に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Trakstar Learn のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Learn では、**SP** Initiated SSO がサポートされます。

### ギャラリーから Learn を追加する

Microsoft Entra ID への Learn の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Learn を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Trakstar Learn**」と入力します。 Trakstar Learn は以前は Mindlfash でした。
4. 結果のパネルから **[Trakstar Learn]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Learn 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Learn に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Learn の関連ユーザーとの間にリンク関係を確立する必要があります。

Learn に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Trakstar Learn SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Trakstar Learn のテストユーザーを作成 - B.Simon に対応するユーザーを Trakstar Learn に作成し、Microsoft Entra のユーザー表現にリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Trakstar Learn**&gt;**シングルサインオン**へ進みます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.mindflash.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.mindflash.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Trakstar Learn クライアント サポート チーム](mailto:learn@trakstar.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Trakstar Learn のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Trakstar Learn の SSO を構成する

**Trakstar Learn** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Trakstar Learn サポート チーム](mailto:learn@trakstar.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Trakstar Learn のテスト ユーザーの作成

Microsoft Entra ユーザーが Learn にログインできるようにするには、そのユーザーを Learn にプロビジョニングする必要があります。 Learn の場合、プロビジョニングは手動で行います。

#### ユーザー アカウントをプロビジョニングするには、次の手順を実行します。

1. **Trakstar Learn** 社のサイトに管理者としてログインします。
2. **[ユーザーの管理]** に移動します。

    [Image: スクリーンショットは、アカウントの [ユーザーの管理] を示しています。]
3. [ **ユーザーの追加]** を選択し、[ **新規**] を選択します。
4. **[新規ユーザーを追加する]** セクションで、プロビジョニングする有効な Microsoft Entra アカウントについて次の手順を実行します。

    [Image: スクリーンショットは、アカウントの [新規ユーザーを追加する] を示しています。]

    ある。 **[名]** ボックスに、ユーザーの**名**を、「**Britta**」と入力します。

    b。 **[姓]** ボックスに、ユーザーの**姓**を、「**Simon**」と入力します。

    c. **[電子メール]** のボックスに、ユーザーの**電子メール アドレス**を「」**BrittaSimon@contoso.com**と入力します。

    b。 [**] を選択し、[**] を追加します。

注

Learn の他のユーザー アカウント作成ツールや、Learn から提供されている API を使って、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Learn サインオン URL にリダイレクトされます。
- Learn のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Trakstar Learn] タイルを選択すると、このオプションは Learn のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mindtickle-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に MindTickle を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mindtickle-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-16
- Summary: MindTickle に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、MindTickle と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを MindTickle に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MindTickle テナント。
- Admin アクセス許可がある MindTickle のユーザー アカウント。

### MindTickle へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に*割り当て*という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、MindTickle へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定したら、次の手順に従って、これらのユーザーやグループを MindTickle に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを MindTickle に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを MindTickle に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- MindTickle にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### プロビジョニング用に MindTickle を設定する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に MindTickle を構成する前に、MindTickle で SCIM プロビジョニングを有効にする必要があります。

[MindTickle のサポート チーム](mailto:help@mindtickle.com)に連絡して、SCIM プロビジョニングを構成するために必要な JWT トークンを入手します。

### ギャラリーから MindTickle を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に MindTickle を構成するには、MindTickle を Microsoft Entra アプリケーション ギャラリーから管理対象の SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから MindTickle を追加するには、以下の手順を行います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**MindTickle**」と入力し、検索ボックス **[MindTickle]** を選択します。
4. 結果のパネルから **[MindTickle]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の MindTickle]

### MindTickle への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、MindTickle でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

MindTickle のシングル サインオンに関する記事で説明されている手順に従って、 [MindTickle](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mindtickle-tutorial) で SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは独立に構成できますが、これらの 2 つの機能は互いに補完しあいます。

#### Microsoft Entra ID で MindTickle の自動ユーザー プロビジョニングを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] 画面]
3. アプリケーションの一覧で **[MindTickle]** を選択します。

    [Image: アプリケーションの一覧の MindTickle のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションの `https://admin.mindtickle.com/scim` に  を入力します。 以前に [シークレット トークン] テキスト ボックスで取得した **JWT トークン**の値を入力し、MindTickle サポート チームから教えられた **JWT トークン**の値を入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が myPolicies に接続できることを確認します。 接続できない場合は、使用中の MindTickle アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: テナント URL + トークン]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から MindTickle に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で MindTickle のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: [属性マッピング] ページのスクリーンショット。Microsoft Entra ID と MindTickle の属性と、一致する優先順位が一覧表示されています。]
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

これにより、 **[設定]** セクションの **[スコープ]** で 定義したユーザーやグループの初期同期が開始されます。 初期同期は、後続の同期よりも実行に時間がかかります。 ユーザーやグループのプロビジョニングにかかる時間の詳細については、「[ユーザーをプロビジョニングするにはどのくらいの時間がかかりますか](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user#how-long-will-it-take-to-provision-users)」を参照してください。

**[現在の状態]** セクションを使用すると、進行状況を監視できるほか、リンクをクリックしてプロビジョニング アクティビティ レポートを取得できます。このレポートには、Microsoft Entra プロビジョニング サービスによって MindTickle に対して実行されたすべてのアクションが記載されています。 詳細については、「[ユーザー プロビジョニングの状態を確認する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)」を参照してください。 Microsoft Entra プロビジョニング ログを読むには、「[自動ユーザー アカウント プロビジョニングについてのレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)」を参照してください。

### デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mindtickle-tutorial"} -->
## Microsoft Entra ID で MindTickle for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mindtickle-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MindTickle 間にシングル サインオンを構成する方法について説明します。

この記事では、MindTickle と Microsoft Entra ID を統合する方法について説明します。 MindTickle を Microsoft Entra ID と統合すると、次のことができます。

- MindTickle にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って MindTickle に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MindTickle でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- MindTickle では、**SP** Initiated SSO がサポートされます。
- MindTickle では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- MindTickle では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mindtickle-provisioning-tutorial)がサポートされます。

### ギャラリーから MindTickle を追加する

Microsoft Entra ID への MindTickle の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に MindTickle を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**MindTickle**」と入力します。
4. 結果のパネルから **[MindTickle]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MindTickle 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、MindTickle に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと MindTickle の関連ユーザーとの間にリンク関係を確立する必要があります。

MindTickle に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MindTickle SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MindTickle テストユーザーを作成** - Microsoft Entra のユーザーである B.Simon に対応し、MindTickle にリンクされたユーザーを作るため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**MindTickle**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**の値が、 **[基本的な SAML 構成]** セクションに自動的に設定されます。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.mindtickle.com`

    注

    **識別子**の値が自動的に設定されない場合は、要件に従って値を手動で入力してください。 サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[MindTickle サポート チーム](mailto:support@mindtickle.com)に問い合わせてください。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[MindTickle のセットアップ]** セクションで、要件のとおりに適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MindTickle SSO の構成

**MindTickle** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [MindTickle サポート チーム](mailto:support@mindtickle.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MindTickle のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを MindTickle に作成します。 MindTickle では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 MindTickle にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

MindTickle は、自動ユーザー プロビジョニングもサポートしています。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mindtickle-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる MindTickle のサインオン URL にリダイレクトされます。
- MindTickle のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [MindTickle] タイルを選択すると、このオプションは MindTickle のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mindwireless-tutorial"} -->
## Microsoft Entra ID で mindWireless for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mindwireless-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と mindWireless の間のシングル サインオンを構成する方法について説明します。

この記事では、mindWireless と Microsoft Entra ID を統合する方法について説明します。 mindWireless を Microsoft Entra ID と統合すると、次のことが可能になります。

- mindWireless にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで mindWireless に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- mindWireless でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- mindWireless は、**IDP** によって開始される SSO をサポートしています。

### ギャラリーからの mindWireless の追加

Microsoft Entra ID への mindWireless の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に mindWireless を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**mindWireless**」と入力します。
4. 結果のパネルから **[mindWireless]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### mindWireless 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、mindWireless に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと mindWireless の関連ユーザーの間にリンク関係を確立する必要があります。

mindWireless 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **mindWireless SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **mindWireless テスト ユーザーの作成**ユーザー - mindWireless で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**mindWireless**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次の手順に従います。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.mwsmart.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.mwsmart.com/SAML/AssertionConsumerService.aspx`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[mindWireless クライアント サポート チーム](mailto:sdulloor@mindwireless.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. mindWireless アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、mindWireless アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | Namespace | ソース属性 |
    | --- | --- | --- |
    | 従業員 ID | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` | user.employeeid |

    注

    要求の名前は常に **Employee ID** であり、その値はユーザーの Employee ID を含む **user.employeeid** にマップされています。 ここで、Microsoft Entra ID から mindWireless へのユーザー マッピングは EmployeeID で完了しますが、それをアプリケーションの設定に基づいて別の値にもマップできます。 最初に [mindWireless サポート チーム](mailto:sdulloor@mindwireless.com)と協力してユーザーの正しい ID を使用し、その値を **Employee ID** 要求でマップすることができます。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[mindWireless のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### mindWireless の SSO の構成

**mindWireless** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [mindWireless サポート チーム](mailto:sdulloor@mindwireless.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### mindWireless のテスト ユーザーの作成

このセクションでは、mindWireless で B.Simon というユーザーを作成します。 [mindWireless サポート チーム](mailto:sdulloor@mindwireless.com)と協力して、mindWireless プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した mindWireless に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで mindWireless タイルを選択すると、SSO を設定した mindWireless に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mint-tms-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に MINT TMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mint-tms-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MINT TMS 間にシングル サインオンを構成する方法について説明します。

この記事では、MINT TMS と Microsoft Entra ID を統合する方法について説明します。 MINT TMS は、トレーニングとキャリアの進捗状況と従業員の実績を計画、最適化、測定するための信頼性の高いツールとして使用されるトレーニング、リソース、資格の管理システムです。 MINT TMS を Microsoft Entra ID と統合すると、次のことができます。

- MINT TMS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って MINT TMS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で MINT TMS 向けの Microsoft Entra のシングル サインオンを構成してテストします。 MINT TMS は、**IDP** initiated シングル サインオンをサポートしています。

### [前提条件]

Microsoft Entra ID を MINT TMS と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- MINT TMS のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから MINT TMS アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから MINT TMS を追加する

Microsoft Entra アプリケーション ギャラリーから MINT TMS を追加して、MINT TMS とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**MINT TMS**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`<environment-name>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<environment-name>.mint-online.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[MINT TMS クライアント サポート チーム](mailto:support@media-interactive.de)にお問い合わせください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[MINT TMS のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### MINT TMS SSO を構成する

**MINT TMS** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[MINT TMS サポート チーム](mailto:support@media-interactive.de)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MINT TMS テスト ユーザーを作成する

このセクションでは、MINT TMS で Britta Simon というユーザーを作成します。 [MINT TMS サポート チーム](mailto:support@media-interactive.de)と連携して MINT TMS プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した MINT TMS に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [MINT TMS] タイルを選択すると、SSO を設定した MINT TMS に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/miro-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Miro を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/miro-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-16
- Summary: Miro に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、Miro と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Miro に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Miro テナント](https://miro.com/pricing/)
- Miro の管理者権限を持つユーザー アカウント。

### Miro へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に*割り当て*という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Miro へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Miro に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Miro に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを Miro に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Miro にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### プロビジョニングのための Miro の設定

必要な **[シークレット トークン]** を取得するには、[Miro のサポート チーム](mailto:support@miro.com)に問い合わせてください。 この値は、Miro アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。

### ギャラリーから Miro を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に Miro を構成する前に、Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に Miro を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Miro を追加するには、以下の手順を行います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Miro**」と入力します。
4. 結果のパネルから **[Miro]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Miroへのユーザー自動プロビジョニングの構成

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Miro でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Miro のシングル サインオンに関する記事で説明されている手順に従って、Miro に対して SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/miro-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

注

Miro の SCIM エンドポイントの詳細については、[こちら](https://help.miro.com/hc/en-us/articles/360036777814)を参照してください。

#### Microsoft Entra ID で Miro の自動ユーザー プロビジョニングを構成するには

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **[Miro]** を選択します。

    [Image: アプリケーションの一覧の [Miro] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションの **「テナント URL」** に `https://miro.com/api/v1/scim` を入力します。 **[シークレット トークン]** に先ほど取得した**SCIM 認証トークン**の値を入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Miro に接続できることを確認します。 接続できない場合は、使用中の Miro アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: テナント URL + トークン]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Miro に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Miro のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Miro のユーザー属性]
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Miro に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Miro のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。 Miro SCIM API ではグループの作成と**削除**がサポートされていないため、[**ターゲット オブジェクト アクション]** の [**作成**] と [削除] をオフにします。

    [Image: Miro のグループ属性]
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

- Miro の SCIM エンドポイントでは、グループに対する **作成** 操作と **削除** 操作は許可されません。 グループの **[更新]** 操作のみがサポートされます。

### トラブルシューティングのヒント

- グループの作成でエラーが発生した場合は、Miro SCIM API がグループの **作成** と **削除** をサポートしていないため、[ **ターゲット オブジェクト アクション** ] の [作成と削除] をオフにして無効にする必要があります。

    [Image: Miro グループのヒント]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/miro-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Miro を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/miro-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Miro 間にシングル サインオンを構成する方法について説明します。

この記事では、Miro と Microsoft Entra ID を統合する方法について説明します。 この記事の別のバージョンについては、help.miro.com を参照してください。 Miro を Microsoft Entra ID と統合すると、次のことができます:

- Miro にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Miro に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Miro でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Miro では、 **SP Initiated SSO と IDP** Initiated SSO がサポートされ、 **Just In Time** ユーザー プロビジョニングがサポートされます。
- Miro では、 [**自動** ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/miro-provisioning-tutorial) がサポートされています (推奨)。

### ギャラリーから Miro を追加する

Microsoft Entra ID への Miro の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Miro を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Miro**」と入力します。
4. 結果パネルから **[Miro** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Miro 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Miro に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Miro の関連ユーザーとの間にリンク関係を確立する必要があります。

Miro に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Miro SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Miroテストユーザーの作成** - Microsoft Entra のユーザー B.Simon にリンクする Miro 内の対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Miro** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかの URL/パターンを入力します。

    | **識別子** |
    | --- |
    | `https://miro.com/` |
    | `https://<SUBDOMAIN>.miro.com/<ORG_ID>/<SAMLSETTINGS_ID>` |
    | `https://miro.com/<ORG_ID>/<SAMLSETTINGS_ID>` |
    | `https://<SUBDOMAIN>.miro.com/<ORG_ID>` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL/パターンを入力します。

    | **応答 URL** |
    | --- |
    | `https://miro.com/sso/saml` |
    | `https://<SUBDOMAIN>.miro.com/sso/saml/<ORG_ID>` |
    | `https://miro.com/sso/saml/<ORG_ID>/<SAMLSETTINGS_ID>` |
    | `https://<SUBDOMAIN>.miro.com/sso/saml/<ORG_ID>/<SAMLSETTINGS_ID>` |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://miro.com/sso/login/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Miro サポート チーム](mailto:support@miro.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。 Miro 側で SSO を構成するために必要です。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Miro のセットアップ** ] セクションで、ログイン URL をコピーします。 Miro 側で SSO を構成するために必要です。

    [Image: ログイン URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Miro の SSO の構成

Miro 側にシングル サインオンを構成するには、以前にダウンロードした証明書と、以前にコピーしたログイン URL を使用します。 Miro アカウントの設定で、[ **セキュリティ** ] セクションに移動し **、[SSO/SAML を有効にする]** をオンにします。

1. **[SAML サインイン** URL] フィールドにログイン URL を貼り付けます。
2. 証明書ファイルをテキスト エディターで開き、証明書のシーケンスをコピーします。 [ **キー x509 証明書** ] フィールドにシーケンスを貼り付けます。 [Image: Miro の設定]
3. [ **ドメイン** ] フィールドにドメイン アドレスを入力し、[ **追加** ] を選択し、確認手順に従います。 他にもドメイン アドレスがある場合は、これを繰り返します。 Miro SSO 機能は、リストにあるドメインのエンドユーザーに対して正常に動作しています。 [Image: ドメイン]
4. Just in Time プロビジョニング (Miro での登録時にユーザーをサブスクリプションにプルする) を使用しているかどうかを判断し、[ **保存]** を選択して Miro 側で SSO 構成を完了します。 [Image: ジャストインタイム プロビジョニング]

#### Miro テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Miro に作成します。 Miro では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Miro にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、B.Simon というテスト ユーザーを使用し、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Miro のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Azure portal で [ **このアプリケーションをテスト**する] を選択し、B.Simon としてログインすることを選択します。 SSO を設定した Miro サブスクリプションに自動的にサインインされます。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Miro] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Miro に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mist-cloud-admin-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Mist Cloud Admin SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mist-cloud-admin-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mist Cloud Admin SSO の間にシングル サインオンを構成する方法について説明します。

この記事では、Mist Cloud Admin SSO と Microsoft Entra ID を統合する方法について説明します。 Mist Cloud Admin SSO を Microsoft Entra ID と統合すると、次のことが可能になります。

- Mist ダッシュボードにアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Mist ダッシュボードに自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Mist Cloud アカウントでは、 [ここで](https://manage.mist.com/)アカウントを作成できます。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Mist Cloud Admin SSO では、**SP**および**IDP**によって開始されたSSOがサポートされます。

### ギャラリーからの Mist Cloud Admin SSO の追加

Microsoft Entra ID への Mist Cloud Admin SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Mist Cloud Admin SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「Mist Cloud Admin SSO**」と入力します。
4. 結果パネルから **Mist Cloud Admin SSO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mist Cloud Admin SSO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Mist Cloud Admin SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra アプリと Mist 組織 SSO の間にリンクを確立する必要があります。

Mist Cloud Admin SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Mist Cloud SSO の初期構成を実行** して、アプリケーション側で ACS URL を生成します。
2. **Microsoft Entra SSO を構成** する - ユーザーがこの機能を使用できるようにします。

    1. **SSO アプリケーションのロールの作成**
    2. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    3. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
3. **Mist Cloud の完全な構成**
4. **Microsoft Entra ID によって送信されたロールをリンクするロールを作成する**
5. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Mist Cloud SSO の初期構成を実行する

1. ローカル アカウントを使って Mist ダッシュボードにサインインします。
2. **[組織&gt;設定&gt;シングル Sign-On&gt; IdP追加]** に移動します。
3. [ **シングル サインオン** ] セクションで、[ **IDP の追加]** を選択します。
4. [ **名前** ] フィールドに「 `Azure AD` 」と入力し、[ **追加**] を選択します。

    [Image: ID プロバイダーを追加するスクリーンショット。]
5. **[応答 URL]** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。

    [Image: 返信 URL の値を示すスクリーンショット。]

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ・アプリ**&gt;**Mist Cloud Admin SSO**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    n/a [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `https://api.<MISTCLOUDREGION>.mist.com/api/v1/saml/<SSOUNIQUEID>/login`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://api.<MISTCLOUDREGION>.mist.com/api/v1/saml/<SSOUNIQUEID>/login`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://manage.mist.com`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Mist Cloud Admin SSO サポート チーム](mailto:support@mist.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Mist Cloud Admin SSO アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性マッピングの画像を示すスクリーンショット。]
8. その他に、Mist Cloud Admin SSO アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | 役割 | user.assignedroles |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。 Mist Cloud では、ユーザーに正しい管理者特権を割り当てるためにロール属性が必要です。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. 1. [ **Mist Cloud Admin SSO の設定** ] セクションで、適切な **ログイン URL** と **Microsoft Entra 識別子**をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

#### SSO アプリケーション用のロールを作成する

このセクションでは、後でテスト ユーザー B.Simon に割り当てるスーパーユーザー ロールを作成します。

1. Azure portal で [ **アプリの登録**] を選択し、[ **すべてのアプリケーション**] を選択します。
2. アプリケーションの一覧で、[ **Mist Cloud Admin SSO**] を選択します。
3. アプリの概要ページで、[ **管理** ] セクションを見つけて、[ **アプリ ロール**] を選択します。
4. [**アプリ ロールの作成**] を選択し、[**表示名]** フィールドに**「Mist Superuser**」と入力します。
5. **[値**] フィールドに「**スーパーユーザー**」と入力し、[**説明**] フィールドに**「Mist Superuser Role**」と入力し、[**適用**] を選択します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mist Cloud を完全に構成する

1. [ **ID プロバイダーの作成** ] セクションで、次の手順を実行します。

    [Image: 組織アルゴリズムを示すスクリーンショット。]

    1. **[発行者**] ボックスに、前にコピーした **Microsoft Entra 識別子**の値を貼り付けます。
    2. ダウンロードした **証明書 (Base64)** をメモ帳に開き、[ **証明書** ] テキストボックスに内容を貼り付けます。
    3. **[SSO URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。
    4. **[保存] を選択します**。

### Microsoft Entra ID によって送信されたロールをリンクするためのロールを作成する

1. [ミスト] ダッシュボードで、[ **組織の &gt; 設定]** に移動します。 [ **シングル サインオン] セクションで** 、[ **ロールの作成**] を選択します。

    [Image: [ロールの作成] セクションを示すスクリーンショット。]
2. ロール名は、Microsoft Entra ID によって送信されるロール要求値と一致する必要があります。たとえば、[`Superuser`] フィールドに「」と入力し、ロールに必要な管理者特権を指定して、[**作成**] を選択します。

    [Image: [ロールの作成] ボタンを示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Mist Cloud Admin SSO のサインオン URL にリダイレクトされます。
- Mist Cloud Admin SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。

    注

    各ユーザーの最初のログインは、SP で開始されるフローを使う前に、IdP から実行する必要があります。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Mist Cloud Admin SSO に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Mist Cloud Admin SSO] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Mist Cloud Admin SSO に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mitel-connect-tutorial"} -->
## Microsoft Entra ID で Mitel Connect for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mitel-connect-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mitel Connect の間でシングル サインオンを構成する方法について説明します。

この記事では、Mitel Connect アプリを使用して Microsoft Entra ID を Mitel MiCloud Connect または CloudLink Platform と統合する方法について説明します。 Mitel Connect アプリは Azure ギャラリーで入手できます。 MiCloud Connect または CloudLink Platform と Microsoft Entra ID の統合には、次の利点があります。

- Microsoft Entra ID で、エンタープライズ資格情報を使用して、MiCloud Connect アプリまたは CloudLink アプリにアクセスできるユーザーを制御できます。
- お使いのアカウントで、ユーザーが自身の Microsoft Entra アカウントを使用して MiCloud Connect または CloudLink に自動的にサインイン (シングル サインオン) するように設定できます。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 構成するアプリケーションに応じて、Mitel MiCloud Connect アカウントまたは Mitel CloudLink アカウント。

### シナリオの説明

この記事では、Microsoft Entra シングル サインオン (SSO) を構成してテストします。

- Mitel Connect では、**SP** initiated SSO をサポートしています。

### ギャラリーからの Mitel Connect の追加

Microsoft Entra ID への Mitel Connect の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Mitel Connect を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Mitel Connect**」と入力します。
4. 結果のパネルから **[Mitel Connect]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO の構成とテスト

このセクションでは、 ***Britta Simon*** というテスト ユーザーに基づいて、MiCloud Connect または CloudLink Platform に対する Microsoft Entra SSO を構成し、テストします。 シングル サインオンが機能するためには、Azure portal のユーザーと Mitel プラットフォームの対応するユーザーの間にリンクが確立されている必要があります。 MiCloud Connect または CloudLink Platform に対する Microsoft Entra SSO を構成してテストする方法については、以下のセクションを参照してください。

- MiCloud Connect に対する Microsoft Entra SSO の構成とテスト
- CloudLink Platform に対する Microsoft Entra SSO の構成とテスト

### MiCloud Connect に対する Microsoft Entra SSO の構成とテスト

MiCloud Connect に対する Microsoft Entra シングル サインオンを構成およびテストするには、以下の手順を完了する必要があります。

1. **Microsoft Entra ID を使った SSO のための MiCloud Connect の構成** - ユーザーがこの機能を使用できるようにして、アプリケーション側で SSO 設定を構成します。
2. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
3. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
4. **Mitel MiCloud Connect テスト ユーザーを作成する - Mitel MiCloud Connect アカウントに、Microsoft Entra での Britta Simon にリンクした対応ユーザーを作成します。**
5. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra ID を使った SSO のための MiCloud Connect の構成

このセクションでは、Azure portal で MiCloud Connect に対して Microsoft Entra のシングル サインオンを有効にし、Microsoft Entra ID を使用した SSO を許可するように MiCloud Connect アカウントを構成します。

Microsoft Entra ID の SSO を使用して MiCloud Connect を構成するには、Azure portal と Mitel アカウント ポータルをサイド バイ サイドで開くのが最も簡単です。 一部の情報は Mitel アカウント ポータルに、一部は Mitel アカウント ポータルから Azure portal にコピーする必要があります。

1. Azure portal で構成ページを開くには、次のようにします。

    1. **Mitel Connect** アプリケーション統合ページで、 **[シングル サインオン]** を選択します。
    2. **[シングル サインオン方式の選択]** ダイアログ ボックスで、 **[SAML]** を選択します。 SAML ベースのサインオン ページが表示されます。
2. Mitel アカウント ポータルで構成ダイアログ ボックスを開くには、次の手順を実行します。

    1. **[Phone System](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電話システム)** メニューで、 **[Add-On Features](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アドオン機能)** を選択します。
    2. **[Single Sign-On](シングル サインオン)** の右側で、 **[Activate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクティブ化)** または **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** を選択します。

    [Connect Single Sign-On 設定] ダイアログボックスが表示されます。
3. **[Enable Single Sign-On](シングル サインオンを有効にする)** チェック ボックスをオンにします。

    [Image: Mitel の [Connect Single Sign-On Settings](Connect シングル サインオン設定) ページのスクリーンショット。[Enable Single Sign-On](シングル サインオンを有効にする) チェック ボックスがオンになっています。]
4. Azure portal で、 **[基本的な SAML 構成]** セクションの **[編集]** アイコンを選択します。

    [Image: [SAML でのシングル サインオンの設定] ページを示すスクリーンショット。[編集] アイコンが選択されています。]

    [基本的な SAML 構成] ダイアログ ボックスが開きます。
5. Mitel アカウント ポータル内の **[Mitel Identifier (Entity ID)] (Mitel 識別子 (エンティティ ID))** フィールドの URL をコピーし、**[識別子 (エンティティ ID)]** フィールドに貼り付けます。
6. Mitel アカウントポータルの**応答 URL (Assertion Consumer Service URL)** フィールドから URL をコピーし、**応答 URL (Assertion Consumer Service URL)** フィールドに貼り付けます。

    [Image: Azure portal の [基本的な SAML 構成] および Mitel アカウント ポータルの [ID プロバイダーの設定] セクションを示すスクリーンショット。それらの関係が線で示されています。]
7. **[サインオン URL]** ボックスに、次のいずれかの URL を入力します。

    1. **https://portal.shoretelsky.com** - 既定の Mitel アプリケーションとして Mitel アカウント ポータルを使用する場合
    2. **https://teamwork.shoretel.com** - 既定の Mitel アプリケーションとして Teamwork を使用する場合

    注意

    既定の Mitel アプリケーションは、ユーザーがアクセス パネルで [Mitel Connect] タイルを選択したときにアクセスされるアプリケーションです。 また、Microsoft Entra ID からテスト セットアップを実行した場合にアクセスされるアプリケーションでもあります。
8. **[基本的な SAML 構成]** ダイアログ ボックスで **[保存]** を選択します。
9. Azure portal 内の **[SAML ベースのサインオン]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** の横にある **[ダウンロード]** を選択して**署名証明書**をダウンロードし、コンピューターに保存します。

    [Image: [SAML 署名証明書] ウィンドウを示すスクリーンショット。ここでは、証明書をダウンロードできます。]
10. テキスト エディターで署名証明書ファイルを開き、ファイル内のデータをすべてコピーして、Mitel アカウント ポータルの **[Signing Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/署名証明書)** フィールドに貼り付けます。

    [Image: [署名証明書] フィールドを示すスクリーンショット。]
11. Azure portal の **[SAML ベースのサインオン]** ページの **[Setup Mitel Connect](Mitel Connect の設定)** セクションで、次の手順を実行します。

    1. **[ログイン URL]** フィールドから URL をコピーして、Mitel アカウント ポータルの **[Sign-in URL](サインイン URL)** フィールドに貼り付けます。
    2. **[Microsoft Entra 識別子]** フィールドから URL をコピーして、Mitel アカウント ポータルの **[エンティティ ID]** フィールドに貼り付けます。
12. Mitel アカウント ポータル内の **[Connect Single Sign-On Settings](Connect シングル サインオンの設定)** ダイアログ ボックスで、 **[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存)** を選択します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Mitel MiCloud Connect のテスト ユーザーの作成

このセクションでは、お使いの MiCloud Connect アカウントで Britta Simon というユーザーを作成します。 シングル サインオンを使用する前に、ユーザーを作成し、アクティブにする必要があります。

Mitel アカウント ポータルでのユーザーの追加の詳細については、Mitel ナレッジ ベースのユーザーの追加に関する記事を参照してください。

次の詳細情報を使用して、MiCloud Connect アカウントにユーザーを作成します。

- **[名前]:** Britta Simon
- **勤務先のメール アドレス:**`brittasimon@<yourcompanydomain>.<extension>` (例: brittasimon@contoso.com)
- **ユーザー名**: `brittasimon@<yourcompanydomain>.<extension>` (例: brittasimon@contoso.com。ユーザー名は通常、ユーザーの勤務先のメール アドレスと同じ)

注意

MiCloud Connect のユーザー名は、Azure 内のユーザーのメール アドレスと同じである必要があります。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、サインイン フローを開始できる Mitel Connect のサインオン URL にリダイレクトされます。
- Mitel Connect のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Mitel Connect] タイルを選択すると、このオプションは MiCloud Connect のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。

### CloudLink Platform に対する Microsoft Entra SSO の構成とテスト

このセクションでは、Azure portal で CloudLink Platform に対して Microsoft Entra SSO を有効にする方法と、Microsoft Entra ID を使用したシングル サインオンを許可するように CloudLink Platform アカウントを構成する方法について説明します。

Microsoft Entra ID のシングル サインオンを使用して CloudLink プラットフォームを構成するには、CloudLink アカウント ポータルに情報をコピーする必要があるため、Azure portal と CloudLink アカウント ポータルをサイド バイ サイドで開くことをお勧めします。その逆も同様です。

1. Azure portal で構成ページを開くには、次のようにします。

    1. **Mitel Connect** アプリケーション統合ページで、 **[シングル サインオン]** を選択します。
    2. **[シングル サインオン方式の選択]** ダイアログ ボックスで、 **[SAML]** を選択します。 **[SAML ベースのサインオン]** ページが開き、 **[基本的な SAML 構成]** セクションが表示されます。

        [Image: [SAML ベースのサインオン] ページを示すスクリーンショット。[基本的な SAML 構成] が表示されています。]
2. CloudLink アカウント ポータルで **[Microsoft Entra シングル サインオン]** 構成パネルにアクセスするには、次のようにします。

    1. 統合を有効にする顧客アカウントの **[Account Information](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント情報)** ページに移動します。
    2. **[Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合)** セクションで、 **[+ Add new](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新規追加)** を選択します。 ポップアップ画面に **[Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合)** パネルが表示されます。
    3. **[3rd party](サード パーティ)** タブを選択します。サポートされるサード パーティ アプリケーションの一覧が表示されます。 **[Microsoft Entra シングル サインオン]** に関連付けられている **[追加]** ボタンを選択し、**[完了]** を選択します。

        顧客アカウントの **Microsoft Entra シングル サインオン**が有効になり、**[アカウント情報]** ページの **[統合]** セクションに追加されます。
    4. **[Complete Setup](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セットアップの完了)** を選択します。 **[Microsoft Entra シングル サインオン]** 構成パネルが開きます。

        [Image: Microsoft Entra シングル サインオンの構成を示すスクリーンショット。]

        Mitel では、 **[Optional Mitel credentials](オプションの Mitel 資格情報)** セクションの **[Enable Mitel Credentials (Optional)](Mitel の資格情報を有効にする (オプション))** チェック ボックスをオフにすることを勧めします。 このチェック ボックスをオンにするのは、シングル サインオン オプションに加え、Mitel 資格情報を使用して、ユーザーが CloudLink アプリケーションにサインインできるようにする場合のみです。
3. Azure portal の **[SAML ベースのサインオン]** ページで、**[基本的な SAML 構成]** セクションの **[編集]** アイコンを選択します。 **[基本的な SAML 構成]** パネルが開きます。

    [Image: [基本的な SAML 構成] ウィンドウを示すスクリーンショット。[編集] アイコンが選択されています。]
4. CloudLink アカウント ポータル内の **[Mitel Identifier (Entity ID)] (Mitel 識別子 (エンティティ ID))** フィールドから URL をコピーし、**[識別子 (エンティティ ID)]** フィールドに貼り付けます。
5. CloudLink アカウント ポータル内の **返信 URL (Assertion Consumer Service URL)** フィールドから URL をコピーし、**返信 URL (Assertion Consumer Service URL)** フィールドに貼り付けます。

    [Image: CloudLink アカウント ポータルと Azure portal のページ間の関係を示すスクリーンショット。]
6. **[サインオン URL]** テキスト ボックスに、CloudLink アカウント ポータルを既定の Mitel アプリケーションとして使用するための URL である「`https://accounts.mitel.io`」を入力します。

    [Image: [サインオン URL] テキスト ボックスを示すスクリーンショット。]

    注意

    既定の Mitel アプリケーションとは、ユーザーがアクセス パネルの [Mitel Connect] タイルを選択したときに開くアプリケーションです。 また、ユーザーが Microsoft Entra ID からテスト セットアップを構成するときにアクセス先となるアプリケーションでもあります。
7. **[基本的な SAML 構成]** ダイアログ ボックスで **[保存]** を選択します。
8. Azure portal 内の **[SAML ベースのサインオン]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** の横にある **[ダウンロード]** を選択して**署名証明書**をダウンロードします。 お使いのコンピューターに証明書ファイルを保存します。

    [Image: [SAML 署名証明書] セクションを示すスクリーンショット。ここでは、Base64 の証明書をダウンロードできます。]
9. テキスト エディターで署名証明書ファイルを開き、ファイル内のデータをすべてコピーして、CloudLink アカウント ポータルの **[Signing Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/署名証明書)** フィールドに貼り付けます。

    注意

    複数の証明書がある場合は、それらを 1 つずつ貼り付けることをお勧めします。
10. Azure portal の **[SAML ベースのサインオン]** ページの **[Set up Mitel Connect](Mitel Connect の設定)** セクションで、次の手順を実行します。

    1. **[ログイン URL]** フィールドから URL をコピーして、CloudLink アカウント ポータルの **[Sign-in URL](サインイン URL)** フィールドに貼り付けます。
    2. **[Microsoft Entra 識別子]** フィールドから URL をコピーして、CloudLink アカウント ポータルの **[IDP 識別子 (エンティティ ID)]** フィールドに貼り付けます。

        [Image: Mintel Connect で説明されている値のソースを示すスクリーンショット。]
11. CloudLink アカウント ポータルの **[Microsoft Entra シングル サインオン]** パネルで、**[保存]** を選択します。

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部で **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. "**表示名**" フィールドに「`B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **[作成]** を選択します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、Mitel Connect へのアクセスを B.Simon に許可し、シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Mitel Connect** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、**[追加された割り当て]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. **[ユーザーとグループ]** ダイアログ ボックスの [ユーザー] の一覧で **[B.Simon]** を選択し、画面の下部にある **[選択]** ボタンを選択します。
    2. ユーザーにロールが割り当てられることが想定される場合は、**[ロールの選択]** ドロップダウンからそれを選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. **[割り当ての追加]** ダイアログで、**[割り当て]** ボタンを選択します。

#### CloudLink テスト ユーザーの作成

このセクションでは、CloudLink プラットフォームに ***Britta Simon*** という名前のテスト ユーザーを作成する方法について説明します。 ユーザーがシングル サインオンを使用できるようにするには、事前にユーザーを作成し、アクティブにする必要があります。

CloudLink アカウント ポータルでユーザーを追加する方法の詳細については、*CloudLink アカウント ドキュメント*の「[**ユーザーの管理**](https://www.mitel.com/document-center/technology/cloudlink)」を参照してください。

次の詳細情報を使用して、CloudLink アカウント ポータルでユーザーを作成します。

- 名前:Britta Simon
- 名: Britta
- 姓: Simon
- 電子メール: BrittaSimon@contoso.com

注意

ユーザーの CloudLink メール アドレスは、**ユーザー プリンシパル名**と同じにする必要があります。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは CloudLink のサインオン URL にリダイレクトされ、そこでサインイン フローを開始できます。
- CloudLink のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Mitel Connect] タイルを選択すると、このオプションは CloudLink のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mixpanel-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Mixpanel を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mixpanel-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から Mixpanel に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Mixpanel と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Mixpanel](https://mixpanel.com/pricing/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Mixpanel でユーザーを作成する
- アクセスが不要になった場合に Mixpanel のユーザーを削除する
- Microsoft Entra ID と Mixpanel の間でユーザー属性の同期を維持する
- Mixpanelでグループやグループメンバーシップのプロビジョニングを行う
- Mixpanel への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mixpanel-tutorial) (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- エンタープライズ レベルの Mixpanel 組織
- この組織に対する管理者特権を持つ Mixpanel アカウント
- ドメインが確認された Mixpanel での SSO の有効化

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Mixpanel の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Mixpanel を構成する

1. SSO の設定およびドメインの要求については、[こちら](https://docs.mixpanel.com/docs/admin/sso)を参照してください。
2. その後、組織設定のアクセス セキュリティ セクションの [SCIM] タブで SCIM トークンを生成する必要があります。 [Image: Mixpanel トークン]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Mixpanel を追加する

Microsoft Entra アプリケーション ギャラリーから Mixpanel を追加して、Mixpanel へのプロビジョニングの管理を開始します。 SSO で Mixpanel を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Mixpanel への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Mixpanel の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Mixpanel]** を選択します。

    [Image: アプリケーションの一覧の [Mixpanel] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Mixpanel テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Mixpanel に接続できることを確認します。 接続に失敗した場合は、Mixpanel アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Mixpanel に同期されるユーザー属性を確認します。 **[Matching]** プロパティとして選択されている属性は、更新処理で Mixpanel のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Mixpanel API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | displayName | 糸 |
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Mixpanel に同期されるグループ属性を確認します。 **[Matching]** プロパティとして選択されている属性は、更新処理で Mixpanel のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | members | リファレンス |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mixpanel-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Mixpanel を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mixpanel-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mixpanel 間にシングル サインオンを構成する方法について説明します。

この記事では、Mixpanel と Microsoft Entra ID を統合する方法について説明します。 Mixpanel を Microsoft Entra ID と統合すると、次のことができます。

- Mixpanel にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Mixpanel に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mixpanel でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Mixpanel では、 **SP** Initiated SSO がサポートされます。
- Mixpanel では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mixpanel-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Mixpanel の追加

Microsoft Entra ID への Mixpanel の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Mixpanel を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Mixpanel**」と入力します。
4. 結果パネルから **Mixpanel** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mixpanel 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Mixpanel に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Mixpanel の関連ユーザーとの間にリンク関係を確立する必要があります。

Mixpanel に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mixpanel の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mixpanel テスト ユーザーを作成** - Mixpanel で B.Simon の対応ユーザーを作成し、それを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Mixpanel**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://mixpanel.com/login/`

    注

    ログイン資格情報を設定するには、 https://mixpanel.com/register/ に登録し、 [Mixpanel サポート チーム](mailto:support@mixpanel.com) に連絡してテナントの SSO 設定を有効にしてください。 必要であれば、Mixpanel サポート チームはサインオン URL 値も提供します。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Mixpanel のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mixpanel SSO の構成

1. 別のブラウザー ウィンドウで、管理者として Mixpanel アプリケーションにサインオンします。
2. ページの下部で、左側の小さな **歯車** アイコンを選択します。

    [Image: Mixpanel SSO]
3. [ **アクセス セキュリティ** ] タブを選択し、[ **設定の変更**] を選択します。

    [Image: 設定を変更できる [アクセス セキュリティ] タブを示すスクリーンショット。]
4. [ **証明書の変更** ] ダイアログ ページで、[ **ダウンロード** した証明書をアップロードするファイルの選択] を選択し、[ **次へ**] を選択します。

    [Image: 証明書ファイルを選択できる [証明書の変更] ダイアログ ボックスを示すスクリーンショット。]
5. [認証 URL の変更] ダイアログ ページの [ **認証 URL** ] ボックスに、 **ログイン URL** の値を貼り付けて、[ **次へ**] を選択します。

    [Image: スクリーンショットは、ログイン URL をコピーできる [認証 URL の変更] ウィンドウを示しています。]
6. [ **完了] を選択します**。

#### Mixpanel のテスト ユーザーの作成

このセクションの目的は、Mixpanel で Britta Simon というユーザーを作成することです。

1. Mixpanel 企業サイトに管理者としてサインオンします。
2. ページの下部で、左側の小さな歯車ボタンを選択して **[設定]** ウィンドウを開きます。
3. [ **チーム** ] タブを選択します。
4. **チーム メンバー**のテキスト ボックスに、Azure で Britta のメール アドレスを入力します。

    [Image: スクリーンショットは、[招待] にアドレスを追加する [チーム] タブを示しています。]
5. [ **招待**] を選択します。

注

ユーザーは、プロファイルを設定するための電子メールを受け取ります。

注

Mixpanel では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mixpanel-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Mixpanel のサインオン URL にリダイレクトされます。
- Mixpanel のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Mixpanel] タイルを選択すると、このオプションは Mixpanel のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mobi-tutorial"} -->
## Microsoft Entra ID で MOBI for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mobi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MOBI 間にシングル サインオンを構成する方法について説明します。

この記事では、MOBI と Microsoft Entra ID を統合する方法について説明します。 MOBI を Microsoft Entra ID と統合すると、次のことができます。

- MOBI にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って MOBI に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MOBI のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- MOBI は、**SP** を起点とした SSO と **IDP** を起点とした SSO をサポートします。

### ギャラリーから MOBI を追加

Microsoft Entra ID への MOBI の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MOBI を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックスに **「MOBI** 」と入力します。
4. 結果パネルから **MOBI** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MOBI 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、MOBI に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと MOBI の関連ユーザーとの間にリンク関係を確立する必要があります。

MOBI に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MOBI SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MOBI テスト ユーザーの作成** - MOBI で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**MOBI**&gt;**シングル サインオン**に移ります。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.thefutureis.mobi`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.thefutureis.mobi/saml_consume`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.thefutureis.mobi/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [MOBI クライアント サポート チーム](mailto:sso@mobiwm.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **MOBI のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MOBI SSO の構成

**MOBI** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [MOBI サポート チーム](mailto:sso@mobiwm.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MOBI テスト ユーザーの作成

このセクションでは、MOBI で Britta Simon というユーザーを作成します。 [MOBI サポート チーム](mailto:sso@mobiwm.com)と協力して、MOBI プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる MOBI サインオン URL にリダイレクトされます。
- MOBI のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した MOBI に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで MOBI タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した MOBI に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mobicontrol-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に MobiControl を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mobicontrol-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MobiControl の間のシングル サインオンを構成する方法について説明します。

この記事では、MobiControl と Microsoft Entra ID を統合する方法について説明します。 MobiControl と Microsoft Entra ID を統合すると、次のことができます。

- MobiControl にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って MobiControl に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MobiControl でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- MobiControl では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの MobiControl の追加

Microsoft Entra ID への MobiControl の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に MobiControl を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**MobiControl**」と入力します。
4. 結果のパネルから **[MobiControl]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MobiControl 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、MobiControl に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと MobiControl の関連ユーザーとの間にリンク関係を確立する必要があります。

MobiControl に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MobiControl の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **MobiControl テスト ユーザーの作成** - B.Simon の Microsoft Entra での表現にリンクされた MobiControl 内の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**MobiControl**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.corp.soti.net/mobicontrol`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.mobicontrolcloud.com/mobicontrol`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[MobiControl クライアント サポート チーム](https://www.soti.net/about/contact-us/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MobiControl の SSO の構成

**MobiControl** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [MobiControl サポート チーム](https://www.soti.net/about/contact-us/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MobiControl テスト ユーザーの作成

このセクションでは、MobiControl で Britta Simon というユーザーを作成します。 [MobiControl サポート チーム](https://www.soti.net/about/contact-us/)と連携して、MobiControl プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる MobiControl のサインオン URL にリダイレクトされます。
- MobiControl のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [MobiControl] タイルを選択すると、このオプションは MobiControl のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mobile-locker-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Mobile Locker を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mobile-locker-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mobile Locker の間にシングル サインオンを構成する方法について説明します。

この記事では、Mobile Locker と Microsoft Entra ID を統合する方法について説明します。 Mobile Locker を Microsoft Entra ID と統合すると、次のことができます。

- Mobile Locker にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Mobile Locker に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mobile Locker でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Mobile Locker では、**SP Initiated SSO と IDP Initiated SSO** がサポートされています
- Mobile Locker を構成すると、組織の機密データの流出と侵入をリアルタイムで保護するセッション制御を適用することができます。 セッション制御は条件付きアクセスから拡張されます。 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)でセッション制御を適用する方法について説明します。

### ギャラリーからの Mobile Locker の追加

Microsoft Entra ID への Mobile Locker の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Mobile Locker を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Mobile Locker**」と入力します。
4. 結果のパネルから **Mobile Locker** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mobile Locker 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Mobile Locker に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Mobile Locker の関連ユーザーの間にリンク関係を確立する必要があります。

Mobile Locker に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mobile Locker の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mobile Locker のテストユーザーを作成します** - Mobile Locker 内に B.Simon に相当するテストユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**Mobile Locker**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.mobilelocker.com/saml2/metadata?service=[UUID]`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.mobilelocker.com/saml2/acs?service=[UUID]`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.mobilelocker.com/saml2/login?service=[UUID]`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Mobile Locker クライアント サポート チーム](mailto:support@mobilelocker.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Mobile Locker のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mobile Locker の SSO を構成する

**Mobile Locker** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Mobile Locker サポート チーム](mailto:support@mobilelocker.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Mobile Locker のテスト ユーザーの作成

このセクションでは、Mobile Locker で B.Simon というユーザーを作成します。 [Mobile Locker サポート チーム](mailto:support@mobilelocker.com)と連携して、Mobile Locker プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Mobile Locker] タイルを選択すると、SSO を設定した Mobile Locker に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mobileiron-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に MobileIron を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mobileiron-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から MobileIron にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、MobileIron と Microsoft Entra ID の両方で実行して、ユーザーとグループの自動プロビジョニングを構成するために必要な手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して [MobileIron](https://www.mobileiron.com/) にユーザーを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- MobileIron でユーザーを作成します。
- アクセスが不要になった MobileIron のユーザーを削除します。
- Microsoft Entra ID と MobileIron の間でユーザー属性の同期を維持します。
- MobileIronでグループとグループメンバーシップを設定します。
- MobileIron に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mobileiron-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ MobileIron のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- [Microsoft Entra ID と MobileIron の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように MobileIron を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように MobileIron を構成するには、MobileIron サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから MobileIron を追加する

Microsoft Entra アプリケーション ギャラリーから MobileIron を追加して、MobileIron へのプロビジョニングの管理を開始します。 SSO 用に MobileIron を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: MobileIron への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて MobileIron のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で MobileIron の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[MobileIron]**を選択します。

    [Image: アプリケーションの一覧の MobileIron リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、MobileIron テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が MobileIron に接続できることを確認します。 接続に失敗した場合は、MobileIron アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から MobileIron に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で MobileIron のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、MobileIron API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | MobileIron で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | externalId | 糸 |  |  |
12. [属性マッピング] セクションで、Microsoft Entra ID から MobileIron に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で MobileIron のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | MobileIron で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mobileiron-tutorial"} -->
## Microsoft Entra ID で MobileIron for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mobileiron-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MobileIron 間にシングル サインオンを構成する方法について説明します。

この記事では、MobileIron と Microsoft Entra ID を統合する方法について説明します。 MobileIron を Microsoft Entra ID と統合すると、次のことができます。

- MobileIron にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Mobile MobileIron に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MobileIron でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- MobileIron は、**SP および IDP による SSO の開始**をサポートします。

### ギャラリーからの MobileIron の追加

Microsoft Entra ID への MobileIron の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MobileIron を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**、&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「MobileIron**」と入力します。
4. 結果から **MobileIron** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MobileIron 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、MobileIron に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと MobileIron の関連ユーザーとの間にリンク関係を確立する必要があります。

MobileIron に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MobileIron SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **MobileIron テストユーザーを作成** - MobileIron で Britta Simon に対応するユーザーを作成し、Microsoft Entra 上のユーザープロフィールとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

このセクションでは、Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**MobileIron** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **単一の Sign-On 方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **IDP** 開始モードでアプリケーションを構成する場合は、[**基本的な SAML 構成**] セクションで次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.MobileIron.com/<key>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<host>.MobileIron.com/saml/SSO/alias/<key>`

    c. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<host>.MobileIron.com/user/login.html`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 キーとホストの値は、MobileIron の管理ポータルから取得します。これについては、この記事の後半で説明します。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MobileIron の SSO の構成

1. 別の Web ブラウザー ウィンドウで、MobileIron 企業サイトに管理者としてログインします。
2. **[Admin**&gt;**Identity**] に移動し、[**Info on Cloud IDP Setup]\(クラウド IDP セットアップの情報**\) フィールドで **Microsoft Entra ID** オプションを選択します。

    [Image: [ID] が選択されている MobileIron サイトの [Admin] タブを示すスクリーンショット。]
3. **キー**と**ホスト**の値をコピーして貼り付けて、Azure portal の [**基本的な SAML 構成]** セクションの URL を完成させます。

    [Image: スクリーンショットは、キーとホスト値を含む [SAML の設定] オプションを示しています。]
4. **AAD からメタデータ ファイルをエクスポートし、MobileIron Cloud フィールドにインポートします**。[**ファイルの選択**] を選択して、Azure portal からダウンロードしたメタデータをアップロードします。 アップロードしたら **完了** を選択します。

    [Image: 単一の管理メタデータを設定するボタン Sign-On]

#### MobileIron のテスト ユーザーの作成

Microsoft Entra ユーザーが MobileIron にログインできるようにするには、ユーザーを MobileIron にプロビジョニングする必要があります。 MobileIron の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. MobileIron 企業サイトに管理者としてログインします。
2. **[ユーザー**] に移動し、[**追加**&gt;**単一ユーザー] を選択します**。

    [Image: シングル Sign-On ユーザー ボタンを構成する]
3. **[シングル ユーザー]** ダイアログ ページで、次の手順を実行します。

    [Image: シングル Sign-On ユーザー追加ボタンを設定する]

    ある。 [ **電子メール アドレス** ] テキスト ボックスに、ユーザーの電子メール ( brittasimon@contoso.comなど) を入力します。

    b。 **名**テキストボックスに、ユーザーの名を入力します（例: Britta）。

    c. [ **姓]** テキスト ボックスに、ユーザーの姓 (Simon など) を入力します。

    d. [ **完了] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

#### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる MobileIron のサインオン URL にリダイレクトされます。
- MobileIron のサインオン URL に直接移動し、そこからログイン フローを開始します。

#### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した MobileIron に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [MobileIron] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した MobileIron に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mobilexpense-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Mobile Xpense を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mobilexpense-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mobile Xpense の間にシングル サインオンを構成する方法について説明します。

この記事では、Mobile Xpense と Microsoft Entra ID を統合する方法について説明します。 Mobile Xpense を Microsoft Entra ID と統合すると、次のことができます。

- Mobile Xpense にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Mobile Xpense に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Mobile Xpense でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Mobile Xpense では、**SP** と **IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Mobile Xpense の追加

Microsoft Entra ID への Mobile Xpense の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Mobile Xpense を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Mobile Xpense**」と入力します。
4. 結果のパネルから **[Mobile Xpense]** を選び、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mobile Xpense 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Mobile Xpense に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Mobile Xpense の関連ユーザーの間にリンク関係を確立する必要があります。

Mobile Xpense に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mobile Xpense SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Mobile Xpense のテストユーザーを作成** - Mobile Xpense で B.Simon に対応するユーザーを作成し、それを Microsoft Entra 上のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Mobile Xpense**&gt;**シングルサインオンに移動します。**
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://mobilexpense.com/ServiceProvider`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.mobilexpense.com/NET/SSO/SAML20/SAML/AssertionConsumerService.aspx`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<sub-domain>.mobilexpense.com/<customername>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、[Mobile Xpense クライアント サポート チーム](https://www.mobilexpense.com/contact)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Mobile Xpense のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mobile Xpense SSO の構成

**Mobile Xpense** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Mobile Xpense サポート チーム](https://www.mobilexpense.com/contact)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Mobile Xpense のテスト ユーザーの作成

このセクションでは、Mobile Xpense で Britta Simon というユーザーを作成します。 [Mobile Xpense サポート チーム](https://www.mobilexpense.com/contact)と連携して、Mobile Xpense プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Mobile Xpense Sign-On URL にリダイレクトされます。
- Mobile Xpense のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Mobile Xpense に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Mobile Xpense] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション Sign-On ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Mobile Xpense に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/moconavi-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に moconavi を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/moconavi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と moconavi 間にシングル サインオンを構成する方法について説明します。

この記事では、moconavi と Microsoft Entra ID を統合する方法について説明します。 moconavi を Microsoft Entra ID と統合すると、次のことができます。

- moconavi にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して moconavi に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- moconavi のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- moconavi では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの moconavi の追加

Microsoft Entra ID への moconavi の統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに moconavi を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「moconavi**」と入力します。
4. 結果パネルから **moconavi** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### moconavi 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、moconavi に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと moconavi の関連ユーザーとの間にリンク関係を確立する必要があります。

moconavi に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **moconavi SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **moconavi テストユーザーの作成** - moconaviにおいてB.Simonに対応するユーザーを作成し、そのユーザーをMicrosoft EntraのB.Simonにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**moconavi**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<yourserverurl>/moconavi-saml2`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<yourserverurl>/moconavi-saml2/saml/SSO`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<yourserverurl>/moconavi-saml2/saml/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、moconavi クライアント サポート チーム](mailto:support@recomot.co.jp) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **moconavi のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### moconavi の SSO の構成

**moconavi** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [moconavi サポート チーム](mailto:support@recomot.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### moconavi テスト ユーザーの作成

このセクションでは、moconavi で Britta Simon というユーザーを作成します。 [moconavi サポート チーム](mailto:support@recomot.co.jp)と協力して、moconavi プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

1. Microsoft Store から moconavi をインストールします。
2. moconavi を起動します。
3. [ **接続設定** ] ボタンを選択します。

    [Image: [接続設定] ボタンが表示された moconavi を示すスクリーンショット。]
4. [`https://mcs-admin.moconavi.biz/gateway`] ボックスに「」と入力し、[**完了]** ボタンを選択します。

    [Image: スクリーンショットは、[U R L に接続] ボックスと [完了] ボタンを示しています。]
5. 次のスクリーンショットで、次の手順を実行します。

    [Image: 説明されている値を入力できる moconavi ページを示すスクリーンショット。]

    ある。 **入力認証キー**:`azureAD`**入力認証キー**ボックスに入力します。

    b。 **入力ユーザー ID**: `your ad account` を**入力ユーザー ID**テキストボックスに入力します。

    c. [ **ログイン] を選択します**。
6. [ **パスワード** ] テキストボックスに Microsoft Entra パスワードを入力し、[ **ログイン** ] ボタンを選択します。

    [Image: Microsoft Entra パスワードを入力する場所を示すスクリーンショット。]
7. メニューが表示されたら、Microsoft Entra 認証は成功です。

    [Image: moconavi の [電話] アイコンを示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/momenta-tutorial"} -->
## Microsoft Entra ID で Momenta for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/momenta-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Momenta の間のシングル サインオンを構成する方法について説明します。

この記事では、Momenta と Microsoft Entra ID を統合する方法について説明します。 Momenta を Microsoft Entra ID と統合すると、次のことが可能になります。

- Momenta へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Momenta に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Momenta でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Momenta では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーからの Momenta の追加

Microsoft Entra ID への Momenta の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Momenta を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Momenta**」と入力します。
4. 結果のパネルから **[Momenta]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Momenta に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Momenta で Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Momenta の関連ユーザー間にリンク関係を確立する必要があります。

Momenta に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Momenta の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Momenta テスト ユーザーの作成** - Momenta で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Momenta**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.btsmomenta.com/sso/<CUSTOMID>-federationmetadata.xml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.btspulse.com/auth/api/v1/basic/consumeSSO`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.btsmomenta.com/#/auth/sso/microsoft/AUTOCO,ENERGYCO,HEALTHCO`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Momenta のクライアント サポート チーム](mailto:microsoftsupport@bts.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Momenta SSO の構成

**Momenta** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Momenta のサポート チーム](mailto:microsoftsupport@bts.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Momenta テスト ユーザーの作成

このセクションでは、Momenta で B.Simon というユーザーを作成します。 [Momenta サポート チーム](mailto:microsoftsupport@bts.com)と連携して、Momenta プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Momenta のサインオン URL にリダイレクトされます。
- Momenta のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Momenta に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Momenta] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Momenta に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mondaycom-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して自動ユーザー プロビジョニング用に monday.com を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mondaycom-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から monday.com に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために、monday.com ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[monday.com](https://www.monday.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- monday.com でユーザーを作成する
- アクセスが不要になったときに monday.com のユーザーを削除する
- Microsoft Entra ID と monday.com の間でユーザー属性の同期を維持する。
- monday.com でグループとグループ メンバーシップをプロビジョニングする
- monday.com への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mondaycom-tutorial) (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- **Enterprise** monday.com アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と monday.com の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように monday.com を構成する

1. [monday.com](https://www.monday.com/) にサインインします。 左側のナビゲーション ウィンドウで、プロファイル画像を選択します。
2. **[Admin &gt; Security] に移動します**。
3. [**ログイン**] タブの [**SCIM**] セクションで [**開く**] を選択します。

[Image: SCIM の [Provisioning] タブ]

1. **[Generate] \(生成)** を選択します。 これらは、手順 5 で必要な **テナント URL** と **シークレット トークン** です。

注

このシークレット トークンを共有したり保存したりしないでください。 必要に応じて、いつでも新しいトークンを生成できます。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから monday.com を追加する

Microsoft Entra アプリケーション ギャラリーから monday.com を追加して、monday.com へのプロビジョニングの管理を開始します。 SSO のために monday.com を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: monday.com への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で monday.com に対する自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[monday.com]** を選択します。

    [Image: アプリケーションの一覧の monday.com のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、monday.com テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が monday.com に接続できることを確認します。 接続に失敗した場合は、monday.com アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から monday.com に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で monday.com のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、monday.com API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | monday.com で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | ユーザータイプ | 糸 |  |  |
    | displayName | 糸 |  | ✓ |
    | タイトル | 糸 |  |  |
    | ロケール | 糸 |  |  |
    | タイムゾーン | 糸 |  |  |
    | roles | 糸 |  |  |
12. **[属性マッピング]** セクションで、Microsoft Entra ID から monday.com に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で monday.com のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | monday.com で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

- monday.com でサポートされるのは、userType "admin"、"guest"、"member"、"viewer" だけです。 userType "User" はサポートされておらず、今後削除されます。

### 変更ログ

- 2021 年 1 月 21 日 - ユーザーの主要な属性 "userType" のサポートを追加しました。
- 2022 年 12 月 8 日 - コア ユーザー属性 **emails[type eq "work"].value** を削除しました。**userName** が **"オブジェクトの作成時にのみ"** ではなく **"常に"** プロビジョニングされるように更新してください。**userType** のマッピングを削除しました。コア ユーザー属性 **roles** とそれに対応するマッピングを追加しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mondaycom-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの monday.com を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mondaycom-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と monday.com の間でシングル サインオンを構成する方法について説明します。

この記事では、monday.com と Microsoft Entra ID を統合する方法について説明します。 monday.com を Microsoft Entra ID と統合すると、次のことができます。

- monday.com にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して monday.com に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

monday.com は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- monday.com のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- monday.com は、**SP 起動の SSO と IDP 起動の SSO** をサポートします。
- monday.com では、 [**自動** ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mondaycom-provisioning-tutorial) がサポートされます (推奨)。
- monday.com では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから monday.com を追加する

Microsoft Entra ID への monday.com の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に monday.com を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックス **に「monday.com** 」と入力します。
4. 結果パネルから **monday.com** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### monday.com の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、monday.com に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと monday.com の関連ユーザーとの間にリンク関係を確立する必要があります。

monday.com に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **monday.com SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **monday.com テストユーザーを作成する** - Microsoft Entra のユーザー表現にリンクされた、monday.com における B.Simon のカウンターパートを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**monday.com**&gt;**Single sign-on** へアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル** があり、 **IDP** 開始モードで構成する場合は、次の手順を実行します。

    ａ。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    手記

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、値を手動で入力します。 **識別子**と**応答 URL** は同じで、値は次のパターンです。`https://<YOUR_DOMAIN>.monday.com/saml/saml_callback`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_DOMAIN>.monday.com`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、Sign-On URL でこれらの値を更新します。 [monday.com](https://monday.com/contact-us/) のクライアントサポートチームに連絡して、これらの値を取得してください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. monday.com アプリケーションは特定の形式の SAML アサーションを期待しており、そのためにはカスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットは、Givenname user.givenname や Emailaddress User.mail などの既定値を持つユーザー属性と要求を示しています。]
8. 上記に加えて、monday.com アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザー・メール |
    | ファーストネーム | ユーザー.名 |
    | 姓 | ユーザーの姓 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **monday.com のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### monday.com SSO の構成

1. monday.com の会社サイトに、別の Web ブラウザー ウィンドウで管理者としてサインインします。
2. ページの右上隅にある **プロファイル** に移動し、[管理者] を選択 **します**。

    [Image: [管理者プロファイル] が選択されているスクリーンショット。]
3. [ **セキュリティ** ] を選択し、[SAML] の横にある [ **開く** ] を選択します。

    [Image: [セキュリティ] タブを示すスクリーンショット。[SAML] の横に [開く] オプションが表示されています。]
4. IDP から以下の詳細を入力します。

    [Image: スクリーンショットは、I D P から情報を入力できる SAML プロバイダーを示しています。]

    手記

    詳細については、 [この](https://support.monday.com/hc/articles/360000460605-SAML-Single-Sign-on?abcb=34642) 記事を参照してください。

#### monday.comのテストユーザーを作成する

このセクションでは、B.Simon というユーザーを monday.com に作成します。 monday.com では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 monday.com にユーザーがまだ存在していない場合は、monday.com にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる monday.com サインオン URL にリダイレクトされます。
- monday.com サインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDPが開始されました:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した monday.com に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [monday.com] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した monday.com に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mongodb-cloud-tutorial"} -->
## Microsoft Entra ID のシングルサインオン（SSO）のためのMongoDB Atlasの構成 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mongodb-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と MongoDB Atlas - SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、MongoDB Atlas - SSO と Microsoft Entra ID を統合する方法について説明します。 MongoDB Atlas - SSO と Microsoft Entra ID を統合すると、次のことができます。

- MongoDB Atlas、MongoDB コミュニティ、MongoDB University、MongoDB サポートにアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って MongoDB Atlas - SSO に自動的にサインインできるようにする。
- Microsoft Entra のグループ メンバーシップに基づいて、ユーザーに MongoDB Atlas のロールを割り当てる。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な MongoDB Atlas - SSO のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- MongoDB Atlas - SSO では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。
- MongoDB Atlas - SSO では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの MongoDB Atlas - SSO の追加

Microsoft Entra ID への MongoDB Atlas - SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に MongoDB Atlas - SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**MongoDB Atlas - SSO**」と入力します。
4. 結果のパネルから **[MongoDB Atlas - SSO]** を選択して、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MongoDB Atlas - SSO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、MongoDB Atlas - SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと MongoDB Atlas - SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

MongoDB Atlas - SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. ユーザーがこの機能を使用できるように Microsoft Entra SSO を構成します。
    1. Microsoft Entra のテスト ユーザーとテスト グループを作成し、B.Simon を使って Microsoft Entra のシングル サインオンをテストします。
    2. Microsoft Entra のテスト ユーザーまたはテスト グループを割り当てて、B.Simon が Microsoft Entra のシングル サインオンを使用できるようにします。
2. MongoDB Atlas SSO を構成して、アプリケーション側でシングル サインオン設定を構成します。
    1. MongoDB Atlas SSO のテスト ユーザーを作成し、MongoDB Atlas - SSO で B.Simon に対応するユーザーを作成して、Microsoft Entra でのユーザー表現にリンクさせます。
3. SSO をテスト して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**MongoDB Atlas - SSO** アプリケーション統合ページを参照し、[**管理**] セクションを見つけます。 **[シングル サインオン]** を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 鉛筆アイコンが強調表示された [SAML を使用して単一 Sign-On を設定する] ページのスクリーンショット]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用する URL を入力します。 `https://www.okta.com/saml2/service-provider/<Customer_Unique>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用する URL を入力します。 `https://auth.mongodb.com/sso/saml2/<Customer_Unique>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用する URL を入力します。 `https://cloud.mongodb.com/sso/<Customer_Unique>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[MongoDB Atlas - SSO クライアント サポート チーム](https://support.mongodb.com/)にご連絡ください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. MongoDB Atlas - SSO アプリケーションは特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 既定の属性のスクリーンショット]
8. 上記の属性に加えて、MongoDB Atlas - SSO アプリケーションでは、その他にいくつかの属性が SAML 応答で返されることが想定されています。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザー.ユーザープリンシパルネーム |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
9. MongoDB Atlas [ロール マッピング](https://docs.atlas.mongodb.com/security/manage-role-mapping/)を使用してユーザーを承認する場合は、次のグループ要求を追加して、SAML アサーション内でユーザーのグループ情報を送信します。

    | 名前 | ソース属性 |
    | --- | --- |
    | 所属 | グループ識別子 |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **フェデレーション メタデータ XML**] を見つけます。 [ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [ダウンロード] リンクが強調表示された [SAML 署名証明書] セクションのスクリーンショット]
11. **[MongoDB Atlas - SSO のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: URL が強調表示された [MongoDB Cloud のセットアップ] セクションのスクリーンショット]

#### Microsoft Entra のテスト ユーザーとテスト グループを作成する

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

Microsoft Entra グループに基づいてユーザーにロールを割り当てるために MongoDB Atlas ロール マッピング機能を使用している場合は、テスト グループと B.Simon をメンバーとして作成します。

1. **Entra ID**&gt;**Groups** に移動します。
2. 画面の上部にある **[新しいグループ]** を選択します。
3. **[グループ]**プロパティで、以下の手順を実行します。
    1. **[グループの種類]** ドロップダウンで [セキュリティ] を選択します。
    2. **[グループ名]** フィールドに「Group 1」と入力します
    3. **を選択して**を作成します。

#### Microsoft Entra のテスト ユーザーまたはテスト グループを割り当てる

このセクションでは、B.Simon またはグループ 1 に MongoDB Atlas - SSO へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**MongoDB Atlas - SSO** に移動します。
3. アプリの概要ページで、 **[管理]** セクションを見つけて、 **[ユーザーとグループ]** を選択します。
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]** を選択します。
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択するか、MongoDB Atlas ロール マッピングを使用している場合は、[グループ] の一覧から **[グループ 1** ] を選択します。次に、画面の下部にある **[選択** ] ボタンを選択します。
6. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### MongoDB Atlas SSO の構成

MongoDB Atlas 側でシングル サインオンを構成するには、適切な URL をコピーする必要があります。 さらに、MongoDB Atlas 組織のフェデレーション アプリケーションを構成する必要があります。 [MongoDB Atlas のドキュメント](https://docs.atlas.mongodb.com/security/federated-auth-azure-ad/)に記載の手順に従います。 問題が発生した場合は、[MongoDB サポート チーム](https://support.mongodb.com/)にお問い合わせください。

#### MongoDB Atlas ロール マッピングの構成

Microsoft Entra グループ メンバーシップに基づいて MongoDB Atlas でユーザーを承認するには、MongoDB Atlas ロール マッピングを使用して、Microsoft Entra グループの Object-ID を MongoDB Atlas の組織とプロジェクトのロールにマップできます。 [MongoDB Atlas のドキュメント](https://docs.atlas.mongodb.com/security/manage-role-mapping/#add-role-mappings-in-your-organization-and-its-projects)に記載の手順に従います。 問題が発生した場合は、[MongoDB サポート チーム](https://support.mongodb.com/)にお問い合わせください。

#### MongoDB Atlas SSO テスト ユーザーの作成

MongoDB Atlas では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 追加のアクションはありません。 MongoDB Atlas にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる MongoDB Atlas のサインオン URL にリダイレクトされます。
- MongoDB Atlas のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した MongoDB Atlas に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで MongoDB Atlas - SSO タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した MongoDB Atlas - SSO に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/montageonline-tutorial"} -->
## Microsoft Entra ID で Montage Online for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/montageonline-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Montage Online 間にシングル サインオンを構成する方法について説明します。

この記事では、Montage Online と Microsoft Entra ID を統合する方法について説明します。 Montage Online と Microsoft Entra ID を統合すると、次のような利点があります。

- Montage Online にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで Montage Online に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Montage Online でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Montage Online では、**SP** によって開始される SSO がサポートされます

### ギャラリーからの Montage Online の追加

Microsoft Entra ID への Montage Online の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Montage Online を追加する必要があります。

**ギャラリーから Montage Online を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Montage Online**」と入力し、結果パネルで **Montage Online** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Montage Online]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Montage Online で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Montage Online 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Montage Online で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Montage Online のシングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Montage Online のテスト ユーザーを作成** - Montage Online で Britta Simon に相当するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Montage Online で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Montage Online** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [Montage Online のドメインと URL] のシングル サインオン情報]

    ある。 **[サインオン URL]** ボックスに、次の形式で URL を入力します。

    運用環境: `https://<subdomain>.montageonline.co.nz/`

    テスト環境の場合: `https://build-<subdomain>.montageonline.co.nz/`

    b。 **[識別子]** ボックスに次の URL を入力します。

    運用環境: `MOL_Azure`

    テスト環境の場合: `MOL_Azure_Build`

    注

    サインオン URL の値は実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[Montage Online クライアント サポート チーム](https://www.montage.co.nz/contact-us/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Montage Online のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    ある。 ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Montage Online のシングル サインオンの構成

**Montage Online** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [Montage Online サポート チーム](https://www.montage.co.nz/contact-us/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Montage Online のテスト ユーザーの作成

このセクションでは、Montage Online で Britta Simon というユーザーを作成します。 [Montage Online サポート チーム](https://www.montage.co.nz/contact-us/)と連携し、Montage Online プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Montage Online] タイルを選択すると、SSO を設定した Montage Online に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/moqups-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Moqups を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/moqups-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から Moqups に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Moqups と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Moqups](https://www.moqups.com) に対するユーザーのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Moqups でユーザーを作成する。
- アクセスが不要になったら、Moqups のユーザーを削除します。
- Microsoft Entra ID と Moqups の間でユーザー属性の同期を維持する。
- Moqups への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/moqups-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Moqups の管理者アカウント。
- SCIM ベースのユーザー プロビジョニングを、[無制限プラン](https://moqups.com/pricing)の Moqups のお客様が利用できます。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Moqups の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Moqups を構成する

**Azure** 用**に SCIM** を設定するには、まず Moqups で **API トークン**を生成してから、Azure 自体で**自動プロビジョニングを構成する**必要があります。

API トークンの生成:

1. Moqups **ダッシュボードの [Account] **ページの **[Integrations]** タブに移動します。
2. **[統合] タブ**の **[SCIM プロビジョニング**] セクションで、[**トークンの生成**] ボタンを選択します。

    [Image: [Generate token] のスクリーンショット。]
3. **API トークン**をクリップボードにコピーします。 **Azure** でプロセスを完了するために、これが必要です。

    [Image: API トークンのスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Moqups を追加する

Microsoft Entra アプリケーション ギャラリーから Moqups を追加して、Moqups へのプロビジョニングの管理を開始します。 SSO のために Moqups を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Moqups への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Moqups の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Moqups]** を選択します。

    [Image: アプリケーション リストの Moqups リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Moqups テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Moqups に接続できることを確認します。 接続に失敗した場合は、Moqups アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    Note

    `https://api.moqups.com/scim/v2` に「」と入力します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Moqups に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Moqups のユーザー アカウントの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Moqups API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Moqupsに必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | name.formatted | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/moqups-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Moqups を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/moqups-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Moqups の間のシングル サインオンを構成する方法について説明します。

この記事では、Moqups と Microsoft Entra ID を統合する方法について説明します。 Moqups を Microsoft Entra ID と統合すると、次のことが可能になります。

- Moqups にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Moqups に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Moqups でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Moqups では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Moqups では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Moqups では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/moqups-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Moqups の追加

Microsoft Entra ID への Moqups の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Moqups を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Moqups**」と入力します。
4. 結果のパネルから **[Moqups]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Moqups に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Moqups に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Moqups の関連ユーザーとの間にリンク関係を確立する必要があります。

Moqups に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Moqups SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Moqups テストユーザーの作成** - B.Simon に対応するユーザーを Moqups に作成し、Microsoft Entra のユーザーアカウントとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Moqups]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.moqups.com/saml-login`
7. **保存** を選択します。
8. Moqups アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Moqups アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの名字 |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Moqups SSO の構成

1. Moqups の Web サイトに管理者としてサインインします。
2. **[Account](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント)** に移動し、 **[Integration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合)** タブを選択します。
3. **[SAML 認証]** セクションに、コピーしておいた **[アプリのフェデレーション メタデータ URL]** の値を貼り付けます。

    [Image: 構成セクションのスクリーンショット。]
4. **[構成]** ボタンを選択します。

#### Moqups のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Moqups に作成します。 Moqups では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Moqups にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Moqups のサインオン URL にリダイレクトされます。
- Moqups のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Moqups に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Moqups] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Moqups に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/mosaic-project-operations-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Mosaic Project Operations を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/mosaic-project-operations-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Mosaic Project Operations の間のシングル サインオンを構成する方法について説明します。

この記事では、Mosaic Project Operations と Microsoft Entra ID を統合する方法について説明します。 Mosaic Project Operations と Microsoft Entra ID を統合すると、次のことができます:

- Mosaic Project Operations にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Mosaic Project Operations に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Mosaic Project Operations でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Mosaic Project Operations では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの Mosaic Project Operations の追加

Microsoft Entra ID への Mosaic Project Operations の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Mosaic Project Operations を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「モザイク プロジェクト操作**」と入力します。
4. 結果パネルから **[モザイク プロジェクト操作]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Mosaic Project Operations 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Mosaic Project Operations に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Mosaic Project Operations の関連ユーザーとの間にリンク関係を確立する必要があります。

Mosaic Project Operations に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Mosaic Project Operations の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Mosaic Project Operations のテスト ユーザーの作成 - Mosaic Project Operations** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Mosaic Project Operations**&gt;**シングルサインオン**へ移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア. [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://auth.us-east-1.party.mosaicapp.com/<UUID>` |
    | `https://auth.us-east-1.<ENVIRONMENT>.mosaicapp.com/<UUID>` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://auth.us-east-1.party.mosaicapp.com/auth/saml/callback/<UUID>` |
    | `https://auth.us-east-1.<ENVIRONMENT>.mosaicapp.com/auth/saml/callback/<UUID>` |

    c. [ **サインオン URL** ] ボックスに、URL を入力します。 `https://login.mosaicapp.com/login`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Mosaic Project Operations サポート チーム](mailto:support@mosaicapp.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **モザイク プロジェクト操作の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Mosaic Project Operations の SSO を構成する

**Mosaic Project Operations** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、Microsoft Entra 管理センターからコピーした適切な URL を [Mosaic Project Operations サポート チーム](mailto:support@mosaicapp.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。 詳細については、 [この](https://readme.mosaicapp.com/docs/microsoft-azure-saml) リンクを参照してください。

#### Mosaic Project Operations のテスト ユーザーを作成する

このセクションでは、Mosaic Project Operations で B.Simon というユーザーを作成します。 [Mosaic Project Operations サポート チーム](mailto:support@mosaicapp.com)と協力して、Mosaic Project Operations プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できるモザイク プロジェクト操作のサインオン URL にリダイレクトされます。
- Mosaic Project Operations のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [モザイク プロジェクト操作] タイルを選択すると、このオプションはモザイク プロジェクト操作のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/motus-tutorial"} -->
## Microsoft Entra ID で Motus for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/motus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Motus の間でシングル サインオンを構成する方法について説明します。

この記事では、Motus と Microsoft Entra ID を統合する方法について説明します。 Motus を Microsoft Entra ID を統合すると、次のことができます。

- Motus にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Motus に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Motus サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Motus では、**SP と IDP** initiated の SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Motus の追加

Microsoft Entra ID への Motus の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Motus を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Motus**」と入力します。
4. 結果のパネルから **[Motus]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Motus 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使って、Motus に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Motus の関連ユーザーとの間にリンク関係を確立する必要があります。

Motus に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Motus の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Motusのテストユーザーを作成 - B.Simonに対応するユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Motus**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure で事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.motus.com/`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Set up Motus](Motus の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Motus の SSO の構成

**Motus** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Motus サポート チーム](mailto:customercare@motus.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Motus のテスト ユーザーの作成

このセクションでは、Motus で B.Simon というユーザーを作成します。 [Motus サポート チーム](mailto:customercare@motus.com)と連携して、Motus プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Motus のサインオン URL にリダイレクトされます。
- Motus のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Motus に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Motus] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Motus に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->
