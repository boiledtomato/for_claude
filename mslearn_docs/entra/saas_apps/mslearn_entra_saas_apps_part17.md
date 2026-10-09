# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 17)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 72

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oktopost-saml-tutorial"} -->
## Microsoft Entra ID で Oktopost SAML for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oktopost-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Oktopost SAML 間にシングル サインオンを構成する方法について学習します。

この記事では、Oktopost SAML と Microsoft Entra ID を統合する方法について説明します。 Oktopost SAML を Microsoft Entra ID を統合すると、次のことができます。

- Oktopost SAML にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Oktopost SAML に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Oktopost SAML でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Oktopost SAML では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Oktopost SAML の追加

Microsoft Entra ID への Oktopost SAML の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Oktopost SAML を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Oktopost SAML**」と入力します。
4. 結果のパネルから **Oktopost SAML** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Oktopost SAML 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Oktopost SAML に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Oktopost SAML の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Oktopost SAML と組み合わせて構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Oktopost SAML の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Oktopost SAML テストユーザーの作成** - Oktopost SAML で B.Simon に対応するテストユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Oktopost SAML**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.oktopost.com/auth/login`
7. **保存** を選択します。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Oktopost SAML のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Oktopost SAML の SSO の構成

1. 管理者として Oktopost SAML にログインします。
2. **[ユーザー アイコン] &gt; [設定]** を選択します。

    [Image: Oktopost SAML の [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)]
3. **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** の **[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ) &gt; [Single Sign-on](シングル サインオン)** ページに移動し、次の手順を実行します。

    [Image: Oktopost SAML の構成]

    a **[Enable Single Sign-on](シングル サインオンを有効にする)** で **[Yes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/はい)** を選択します。

    b。 **[SAML エンドポイント]** テキストボックスに、先ほどコピーした **[ログイン URL]** の値を貼り付けます。

    c. **[発行者]** テキスト ボックスに、先ほどコピーした **[Microsoft Entra 識別子]** の値を貼り付けます。

    d. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[X.509 証明書]** テキストボックスに貼り付けます。

    え **保存** を選択します。

#### Oktopost SAML のテスト ユーザーの作成

1. 管理者として Oktopost SAML にログインします。
2. **[ユーザー アイコン] &gt; [設定]** を選択します。

    [Image: Oktopost SAML のテスト ユーザー 1]
3. **[User Management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー管理) &gt; [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) &gt; [Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加)** に移動します。

    [Image: Oktopost SAML のテスト ユーザー 2]
4. ポップアップに必要なフィールドを入力し、[送信] を選択 **します**。

    [Image: Oktopost SAML のテスト ユーザー 3]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは Oktopost SAML サインオン URL にリダイレクトされ、ログイン フローを開始できます。
- Oktopost SAML のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Oktopost SAML に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Oktopost SAML] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Oktopost SAML に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/olfeo-saas-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Olfeo SAAS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/olfeo-saas-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-21
- Summary: Microsoft Entra ID から Olfeo SAAS に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Olfeo SAAS と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Olfeo SAAS](https://www.olfeo.com) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Olfeo SAAS でユーザーを作成する
- アクセスが不要になったときに Olfeo SAAS のユーザーを削除する
- Microsoft Entra ID と Olfeo SAAS の間でユーザー属性の同期を維持する
- Olfeo SAAS にグループとグループ メンバーシップをプロビジョニングする
- Olfeo SAAS への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/olfeo-saas-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Olfeo SAAS テナント](https://www.olfeo.com/)。
- Admin アクセス許可がある Olfeo SAAAS のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Olfeo SAAS の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Olfeo SAAS を構成する

1. Olfeo SAAS 管理コンソールにログインします。
2. **[設定] &gt; [Annuaires]** にアクセスしてください。
3. 新しいディレクトリを作成し、名前を付けます。
4. **Azure** プロバイダーを選択し、**Créer** を選択して新しいディレクトリを保存します。
5. **[同期]** タブに移動し、 **[テナント URL]** と **[Jeton シークレット]** を確認します。 これらの値はコピーされ、Olfeo SAAS アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに貼り付けられます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Olfeo SAAS を追加する

Microsoft Entra アプリケーション ギャラリーから Olfeo SAAS を追加して、Olfeo SAAS へのプロビジョニングの管理を開始します。 SSO のために Olfeo SAAS を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Olfeo SAAS への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーとグループの割り当てに基づいて、Olfeo SAAS アプリでユーザーとグループが作成、更新、無効化されるように、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Olfeo SAAS の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Olfeo SAAS]** を選択します。

    [Image: アプリケーションの一覧の Olfeo SAAS のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: 自動プロビジョニングを構成するための [プロビジョニング] タブのスクリーンショット]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Olfeo SAAS テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Olfeo SAAS に接続できることを確認します。 接続に失敗した場合は、Olfeo SAAS アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Olfeo SAAS に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Olfeo SAAS のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Olfeo SAAS API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | displayName | 糸 |  |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 優先言語 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | externalId | 糸 |  |
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Olfeo SAAS に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Olfeo SAAS のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | externalId | 糸 |  |
    | members | リファレンス |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/olfeo-saas-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Olfeo SAAS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/olfeo-saas-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Olfeo SAAS の間でシングル サインオンを構成する方法について説明します。

この記事では、Olfeo SAAS と Microsoft Entra ID を統合する方法について説明します。 Olfeo SAAS と Microsoft Entra ID を統合すると、次のことができます。

- Olfeo SAAS にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Olfeo SAAS に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Olfeo SAAS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Olfeo SAAS では、 **SP** によって開始される SSO がサポートされます。
- Olfeo SAAS では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/olfeo-saas-provisioning-tutorial)。

### ギャラリーから Olfeo SAAS を追加

Microsoft Entra ID への Olfeo SAAS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Olfeo SAAS を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Olfeo SAAS**」と入力します。
4. 結果パネルから **Olfeo SAAS** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Olfeo SAAS の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Olfeo SAAS に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Olfeo SAAS の関連ユーザーとの間にリンク関係を確立する必要があります。

Olfeo SAAS に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Olfeo SAAS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Olfeo SAAS テスト ユーザーの作成 - Olfeo SAAS** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Olfeo SAAS**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    あ。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.olfeo.com/api/sso/saml/<ID>/login`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.olfeo.com/api/sso/saml/<ID>/login`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.olfeo.com/api/sso/saml/<ID>/acs`

    手記

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには、olfeo SAAS クライアント サポート チーム  にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Olfeo SAAS SSO の構成

**Olfeo SAAS** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Olfeo SAAS サポート チーム](mailto:equipe-rd@olfeo.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Olfeo SAAS テスト ユーザーの作成

このセクションでは、Olfeo SAAS で Britta Simon というユーザーを作成します。 Olfeo SAAS サポート チーム  と連携して、Olfeo SAAS プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Olfeo SAAS では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Olfeo SAAS サインオン URL にリダイレクトされます。
- Olfeo SAAS のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Olfeo SAAS] タイルを選択すると、このオプションは Olfeo SAAS のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/on24-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ON24 Virtual Environment SAML Connection を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/on24-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ON24 Virtual Environment SAML Connection の間でシングル サインオンを構成する方法について説明します。

この記事では、ON24 Virtual Environment SAML Connection と Microsoft Entra ID を統合する方法について説明します。 ON24 Virtual Environment SAML Connection を Microsoft Entra ID と統合すると、次のことができます。

- ON24 Virtual Environment SAML Connection にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ON24 Virtual Environment SAML Connection に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ON24 Virtual Environment SAML Connection シングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ON24 Virtual Environment SAML Connection では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから ON24 Virtual Environment SAML Connection を追加

ON24 Virtual Environment SAML Connection の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ON24 Virtual Environment SAML Connection を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ON24 Virtual Environment SAML Connection**」と入力します。
4. 結果のパネルから **[ON24 Virtual Environment SAML Connection]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ON24 Virtual Environment SAML Connection 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ON24 Virtual Environment SAML Connection で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ON24 Virtual Environment SAML Connection の関連ユーザーとの間にリンク関係を確立する必要があります。

ON24 Virtual Environment SAML Connection で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ON24 Virtual Environment SAML Connection の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ON24 Virtual Environment SAML Connection でテストユーザーを作成** - Microsoft Entra のユーザー表現と関連付けられる ON24 Virtual Environment SAML Connection において、B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**ON24 Virtual Environment SAML Connection**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを**IDP** イニシエートモードで構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかの値を入力します。

    | **運用環境 URL** |
    | --- |
    | `SAML-VSHOW.on24.com` |
    | `SAML-Gateway.on24.com` |
    | `SAP PROD SAML-EliteAudience.on24.com` |
    |  |

    | **QA 環境 URL** |
    | --- |
    | `SAMLQA-VSHOW.on24.com` |
    | `SAMLQA-Gateway.on24.com` |
    | `SAMLQA-EliteAudience.on24.com` |
    |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **運用環境 URL** |
    | --- |
    | `https://federation.on24.com/sp/ACS.saml2` |
    | `https://federation.on24.com/sp/eyJ2c2lkIjoiU0FNTC1WU2hvdy5vbjI0LmNvbSJ9/ACS.saml2` |
    | `https://federation.on24.com/sp/eyJ2c2lkIjoiU0FNTC1HYXRld2F5Lm9uMjQuY29tIn0/ACS.saml2` |
    | `https://federation.on24.com/sp/eyJ2c2lkIjoiU0FNTC1FbGl0ZUF1ZGllbmNlLm9uMjQuY29tIn0/ACS.saml2` |
    |  |

    | **QA 環境 URL** |
    | --- |
    | `https://qafederation.on24.com/sp/ACS.saml2` |
    | `https://qafederation.on24.com/sp/eyJ2c2lkIjoiU0FNTFFBLVZzaG93Lm9uMjQuY29tIn0/ACS.saml2` |
    | `https://qafederation.on24.com/sp/eyJ2c2lkIjoiU0FNTFFBLUdhdGV3YXkub24yNC5jb20ifQ/ACS.saml2` |
    | `https://qafederation.on24.com/sp/eyJ2c2lkIjoiU0FNTFFBLUVsaXRlQXVkaWVuY2Uub24yNC5jb20ifQ/ACS.saml2` |
    |  |

    c. [ **追加の URL の設定] を選択します**。

    d. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://vshow.on24.com/vshow/ms_azure_saml_test?r=<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://vshow.on24.com/vshow/<INSTANCE_NAME>`

    注

    これらの値は実際の値ではありません。 これらの値を実際のリレー状態とサインオン URL で更新してください。 これらの値を取得するには、[ON24 Virtual Environment SAML Connection クライアント サポート チーム](https://www.on24.com/contact-us/)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[ON24 Virtual Environment SAML Connection の設定]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ON24 Virtual Environment SAML Connection SSO の構成

**ON24 Virtual Environment SAML Connection** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を ON24 Virtual Environment SAML Connection サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ON24 Virtual Environment SAML Connection テスト ユーザーの作成

このセクションでは、ON24 Virtual Environment SAML Connection に Britta Simon というユーザーを作成します。 ON24 Virtual Environment SAML Connection サポート チームと協力して、ON24 Virtual Environment SAML Connection プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる ON24 Virtual Environment SAML Connection Sign on URL にリダイレクトされます。
- ON24 Virtual Environment SAML Connection のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ON24 Virtual Environment SAML Connection に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ON24 Virtual Environment SAML Connection] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ON24 Virtual Environment SAML Connection に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/onedesk-tutorial"} -->
## Microsoft Entra ID で OneDesk for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/onedesk-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と OneDesk 間にシングル サインオンを構成する方法について説明します。

この記事では、OneDesk と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と OneDesk を統合すると、次のことができます。

- OneDesk にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して OneDesk に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- OneDesk でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- OneDesk では、**SP と IDP が開始する SSO** がサポートされます。
- OneDesk では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから OneDesk を追加する

Microsoft Entra ID への OneDesk の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に OneDesk を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「OneDesk**」と入力します。
4. 結果パネルから **OneDesk** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### OneDesk 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、OneDesk に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと OneDesk の関連ユーザーとの間にリンク関係を確立する必要があります。

OneDesk に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **OneDesk SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **OneDesk のテスト ユーザーの作成 - OneDesk** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**OneDesk**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `onedesk.com_<specific_tenant_string>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.onedesk.com/sso/saml/SSO/alias/onedesk.com_<specific_tenant_string>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.onedesk.com/sso/saml/login/alias/onedesk.com_<specific_tenant_string>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [OneDesk クライアント サポート チーム](mailto:hello@onedesk.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **OneDesk のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### OneDesk SSO の構成

1. 別の Web ブラウザー ウィンドウで、OneDesk 企業サイトに管理者としてサインインします
2. [統合] タブ **を** 選択します。

    [Image: [Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) タブが選択されていることを示すスクリーンショット。]
3. **シングル サインオン**を選択し、[**メタデータ ファイルのアップロード**] を選択し、[**ファイルの選択] を選択**して、ダウンロードしたメタデータ ファイルをアップロードします。

    [Image: [設定] タブ]

#### OneDesk テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを OneDesk に作成します。 OneDesk では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 OneDesk にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる OneDesk Sign on URL にリダイレクトされます。
- OneDesk のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した OneDesk に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで OneDesk タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した OneDesk に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oneflow-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Oneflow を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oneflow-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-21
- Summary: Microsoft Entra ID から Oneflow に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Oneflow ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [Oneflow](https://oneflow.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Oneflow でユーザーを作成します。
- アクセスが不要になった場合は、Oneflow のユーザーを削除します。
- Microsoft Entra ID と Oneflow の間でユーザー属性の同期を維持します。
- Oneflow でグループとグループ メンバーシップをプロビジョニングする。
- Oneflow に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oneflow-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Oneflow テナント。
- 管理者アクセス許可がある Oneflow のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Oneflow の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Oneflow を構成する

手順 5- 6 では、次の情報を使用します。

- テナント URL: `https://api.oneflow.com/scim/v1/`
- シークレット トークン: oneflow SCIM トークンは、プロビジョニングのシークレット トークンとして機能します。 Oneflow SCIM トークンを生成するには、この [記事](https://developer.oneflow.com/docs/enable-scim-api-extension) に記載されている手順に従ってください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Oneflow を追加する

Microsoft Entra アプリケーション ギャラリーから Oneflow を追加して、Oneflow へのプロビジョニングの管理を開始します。 SSO のために Oneflow を以前に設定した場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5:Oneflow への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Oneflow の自動ユーザー プロビジョニングを構成するには、次のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Oneflow**] を選択します。

    [Image: アプリケーションの一覧の [Oneflow] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Oneflow テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Oneflow に接続できることを確認します。 接続に失敗した場合は、Oneflow アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Oneflow に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Oneflow のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Oneflow API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Oneflow で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | externalId | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
    | ニックネーム | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | プロフィールURL | 糸 |  |  |
    | displayName | 糸 |  |  |
    | addresses[type eq "work"].streetAddress | 糸 |  |  |
    | addresses[type eq "work"].locality | 糸 |  |  |
    | addresses[type eq "work"].region | 糸 |  |  |
    | addresses[type eq "work"].postalCode | 糸 |  |  |
    | addresses[type eq "work"].country | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:ws1b:2.0:User:adSourceAnchor | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:ws1b:2.0:User:customAttribute1 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:ws1b:2.0:User:customAttribute2 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:ws1b:2.0:User:customAttribute3 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:ws1b:2.0:User:customAttribute4 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:ws1b:2.0:User:customAttribute5 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:ws1b:2.0:User:distinguishedName | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:ws1b:2.0:User:domain | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:ws1b:2.0:User:userPrincipalName | 糸 |  |  |
12. [属性マッピング] セクションで、Microsoft Entra ID から Oneflow に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Oneflow のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Oneflow で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 | ✓ | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oneflow-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Oneflow を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oneflow-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Oneflow の間にシングル サインオンを構成する方法について説明します。

この記事では、Oneflow を Microsoft Entra ID を統合する方法について説明します。 Oneflow コネクタでは、ユーザー プロビジョニングと SSO の両方がサポートされます。 Oneflow と Microsoft Entra ID を統合すると、次のことができます。

- Oneflow にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Oneflow に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Oneflow 向けの Microsoft Entra シングル サインオンを構成してテストします。 Oneflow では、**SP** Initiated と **IDP** Initiated のシングル サインオンがサポートされています。 Oneflow では、 [自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oneflow-provisioning-tutorial)もサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Oneflow と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Oneflow のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Oneflow アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Oneflow を追加する

Microsoft Entra アプリケーション ギャラリーから Oneflow を追加して、Oneflow とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Oneflow**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://app.oneflow.com/api/ext/ssosaml/metadata/<INSTANCE>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://app.oneflow.com/api/ext/ssosaml/acs/<INSTANCE>`
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.oneflow.com/<INSTANCE>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Oneflow サポート チーム](mailto:support@oneflow.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Oneflow アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. その他に、Oneflow アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | ユーザー.グループ |
9. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Oneflow の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Oneflow SSO の構成

**Oneflow** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Oneflow サポート チーム](mailto:support@oneflow.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Oneflow テスト ユーザーの作成

このセクションでは、Oneflow で Britta Simon というユーザーを作成します。 [Oneflow サポート チーム](mailto:support@oneflow.com)と連携して、Oneflow プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Oneflow のサインオン URL にリダイレクトされます。
- Oneflow のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Oneflow に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Oneflow] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Oneflow に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/onestream-tutorial"} -->
## Microsoft Entra ID で OneStream for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/onestream-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-04-19
- Summary: Microsoft Entra ID と OneStream の間のシングル サインオンを構成する方法について説明します。

この記事では、OneStream と Microsoft Entra ID を統合する方法について説明します。 OneStream と Microsoft Entra ID を統合すると、次のことができます。

- OneStream にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して OneStream に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- OneStream でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- OneStream では、**SP** によって開始される SSO のみがサポートされます。

### ギャラリーから OneStream を追加する

Microsoft Entra ID への OneStream の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に OneStream を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**OneStream**」と入力します。
4. 結果のパネルから **[OneStream]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### OneStream 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、OneStream に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと OneStream の関連ユーザーとの間にリンク関係を確立する必要があります。

OneStream に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **OneStream SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **OneStream テスト ユーザーの作成 - OneStream** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**OneStream**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<CustomerDomain>.onestreamcloud.com/OneStreamIS/federation/<Scheme>/saml`

    b。 **[応答 URL]** ボックスに、`https://<CustomerDomain>.onestreamcloud.com/OneStreamIS/federation/<Scheme>/signin-saml` のパターンを使用して URL を入力します

    c. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<CustomerDomain>.onestreamcloud.com/OneStreamIS/federation/<Scheme>/signin-saml`

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 **OneStream Identity and Access Management Portal** で新しい SAML プロバイダーを作成して、これらの値を取得します。この値については、後の「OneStream SSO の構成」セクションで説明します。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. OneStream アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性構成の画像を示すスクリーンショット。]
7. その他に、OneStream アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | 名字 | User.surname |
    | メール | User.mail |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### OneStream SSO を構成する

1. OneStream 企業サイトに管理者としてログインします。
2. **[Identity and Access Management]** に移動し、**[ID プロバイダーの管理]** タイルを選択します。

    [Image: 構成の設定を示すスクリーンショット。]
3. [ **ID プロバイダーの管理** ] ページで、[ **+SAML プロバイダーの追加]** を選択します。

    [Image: スクリーンショットには ID プロバイダーを管理する方法が示されています。]
4. 次のページで以下の手順を実行します。

    [Image: スクリーンショットには構成ページが示されています。]

    1. **[名前]** テキストボックスに、ID プロバイダーの一意の名前を入力します。
    2. SAML 構成モードとして **[メタデータ URL]** を選択します。
    3. **[メタデータ URL]** テキスト ボックスに、Microsoft Entra 管理センター からコピーした **[アプリのフェデレーション メタデータ URL]** を貼り付けます。
    4. **[保存] ボタンを**選択します。

注意

詳細については、「[システム ガイド](https://docs.onestream.com/)」「&gt;」の **OneStream ドキュメント サイト**を参照してください。

#### OneStream テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、OneStream 企業サイトに管理者としてサインインします。
2. **System**&gt;**Security** に移動し&gt;**ユーザーの作成**を選択し、次の手順を実行します。

    [Image: スクリーンショットにはユーザー ページが示されています。]

    1. **[名前]** フィールドに有効なユーザー名を入力します。
    2. 要件に基づいて、**[ユーザーの種類]** を入力します。
    3. ドロップダウンから **[外部認証プロバイダー]** を選択します。
    4. **[外部プロバイダー ユーザー名]** フィールドに、Microsoft Entra ID の**ユーザー プリンシパル名**を入力します。

注意

詳細については、[OneStream ドキュメント サイト](https://docs.onestream.com/)を参照し、「**デザインとリファレンス**」&gt;「**ユーザーとグループの管理について**」&gt;「**ユーザーの作成と管理**」に移動してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる OneStream のサインオン URL にリダイレクトします。
- OneStream のサインオン URL に直接移動し、そこからログイン フローを開始します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oneteam-tutorial"} -->
## Microsoft Entra ID で Oneteam for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oneteam-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Oneteam の間でシングル サインオンを構成する方法について説明します。

この記事では、Oneteam と Microsoft Entra ID を統合する方法について説明します。 Oneteam と Microsoft Entra ID を統合すると、次のことができます。

- Oneteam にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Oneteam に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成することができます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Oneteam でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Oneteam では、**SP** と **IDP** によって開始される SSO がサポートされています。
- Oneteam では、ユーザープロビジョニングで **Just-In-Time** がサポートされています。

### ギャラリーから Oneteam を追加する

Microsoft Entra ID への Oneteam の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Oneteam を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Oneteam**」と入力します。
4. 結果パネル **Oneteam** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Oneteam の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Oneteam に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Oneteam の関連ユーザーとの間にリンク関係を確立する必要があります。

Oneteam に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Oneteam SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Oneteam テスト ユーザーの作成** - Oneteam で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Oneteam**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [**基本的な SAML 構成**] セクションで、**IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [**識別子**] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://api.one-team.io/teams/<team name>`

    b。 [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://api.one-team.io/teams/<team name>/auth/saml/callback`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<team name>.one-team.io/`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、Oneteam クライアント サポート チームに問い合わせてください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [**Oneteam** のセットアップ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Oneteam SSO の構成

Oneteam **側** シングル サインオンを構成するには、ダウンロードした **フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を Oneteam サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Oneteam テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Oneteam に作成します。 Oneteam では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Oneteam にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

手記

ユーザーを手動で作成する必要がある場合は、Oneteam サポート チームでサポート チケットを発行できます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Oneteam サインオン URL にリダイレクトされます。
- Oneteam のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP による開始:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Oneteam に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Oneteam タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Oneteam に自動的にサインインされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/onetrust-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に OneTrust Privacy Management Software を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/onetrust-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と OneTrust Privacy Management Software の間のシングル サインオンを構成する方法について説明します。

この記事では、OneTrust Privacy Management Software と Microsoft Entra ID を統合する方法について説明します。 OneTrust Privacy Management Software を Microsoft Entra ID と統合すると、次のことが可能になります。

- OneTrust Privacy Management Software にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで OneTrust Privacy Management Software に自動的にサインインされるように設定する。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

OneTrust Privacy Management Software と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra の環境がない場合は、[こちら](https://azure.microsoft.com/pricing/free-trial/)から 1 か月の試用版を入手できます。
- OneTrust Privacy Management Software でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- OneTrust Privacy Management Software では、**SP** と **IDP** initiated SSO がサポートされます。
- OneTrust Privacy Management Software では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの OneTrust Privacy Management Software の追加

Microsoft Entra ID への OneTrust Privacy Management Software の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に OneTrust Privacy Management Software を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**OneTrust Privacy Management Software**」と入力します。
4. 結果のパネルから **OneTrust Privacy Management Software** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### OneTrust Privacy Management Software の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、OneTrust Privacy Management Software に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと OneTrust Privacy Management Software の関連ユーザーとの間にリンク関係を確立する必要があります。

OneTrust Privacy Management Software で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **OneTrust Privacy Management Software の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **OneTrust Privacy Management Software のテスト ユーザーを作成する** - ユーザーを Microsoft Entra の表現にリンクするために、B.Simon に対応するユーザーを OneTrust Privacy Management Software 内に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

このセクションでは、Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**OneTrust Privacy Management Software** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **単一の Sign-On 方法の選択** ] ページで、[SAML] を選択 **します**。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを**IDP** イニシエートモードで構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://www.onetrust.com/saml2`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://<subdomain>.onetrust.com/auth/consumerservice` |
    | `https://app.onetrust.com/access/v1/saml/SSO` |
    |  |
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.onetrust.com/auth/login`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、[OneTrust Privacy Management Software クライアント サポート チーム](mailto:support@onetrust.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[OneTrust Privacy Management Software のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

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

このセクションでは、B.Simon に OneTrust Privacy Management Software へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
2. アプリケーションの一覧で **[OneTrust Privacy Management Software]** を選択します。
3. アプリの概要ページで、[ **管理** ] セクションを見つけて、[ **ユーザーとグループ**] を選択します。
4. [ **ユーザーの追加] を選択します**。 次に、[ **割り当ての追加** ] ダイアログ ボックスで、[ **ユーザーとグループ**] を選択します。
5. [ **ユーザーとグループ** ] ダイアログ ボックスで、ユーザーの一覧から **B.Simon** を選択します。 次に、画面の下部にある **[選択** ] を選択します。
6. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
7. **[割り当ての追加]** ダイアログ ボックスで **[割り当て]** を選びます。

#### OneTrust Privacy Management Software の SSO の構成

**OneTrust Privacy Management Software** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [OneTrust Privacy Management Software サポート チーム](mailto:support@onetrust.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### OneTrust Privacy Management Software のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを OneTrust Privacy Management Software に作成します。 OneTrust Privacy Management Software では、Just-In-Time ユーザー プロビジョニングがサポートされます。この設定は既定で有効です。 このセクションにはアクション項目はありません。 OneTrust Privacy Management Software にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[OneTrust Privacy Management Software サポート チーム](mailto:support@onetrust.com)にお問い合わせください。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる OneTrust Privacy Management Software のサインオン URL にリダイレクトされます。
- OneTrust Privacy Management Software のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した OneTrust Privacy Management Software に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [OneTrust Privacy Management Software] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した OneTrust Privacy Management Software に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/onit-tutorial"} -->
## Microsoft Entra ID で Onit for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/onit-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Onit 間にシングル サインオンを構成する方法について学習します。

この記事では、Onit と Microsoft Entra ID を統合する方法について説明します。 Onit を Microsoft Entra ID を統合すると、次のことができます。

- Onit にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Onit に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Onit でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Onit では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの Onit の追加

Microsoft Entra ID への Onit の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Onit を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Onit**」と入力します。
4. 結果パネルから **[Onit]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Onit 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Onit に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Onit の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Onit と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Onit SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Onit テスト ユーザーの作成** - B.Simon の対応を Onit に持たせ、Microsoft Entra におけるユーザー表現にリンクさせるため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Onit**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.onit.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.onit.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、Onit クライアント サポート チーム](https://www.onit.com/support-portal) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. [ **Onit のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Onit SSO の構成

1. 別の Web ブラウザー ウィンドウで、Onit 企業サイトに管理者としてログインします。
2. 上部のメニューで、[管理] を選択 **します**。

    [Image: [管理] アクションが選択されている [M S S S S O テスト] ページの上部にあるメニューを示すスクリーンショット。]
3. **会社の編集を選択します**

    [Image: Edit Corporation]
4. [ **セキュリティ** ] タブを選択します。

    [Image: 会社情報の編集 会社情報]
5. [ **セキュリティ** ] タブで、次の手順を実行します。

    [Image: シングル サインオン]

    ある。 **認証戦略**として、[**シングル サインオンとパスワード**] を選択します。

    b。 **[Idp ターゲット URL**] ボックスに、**ログイン URL** の値を貼り付けます。

    c. **[Idp ログアウト URL**] ボックスに、**ログアウト URL** の値を貼り付けます。

    d. **[Idp Cert Fingerprint (SHA1)] ボックスに**、証明書の**拇印**の値を貼り付けます。

#### Onit テスト ユーザーの作成

Microsoft Entra ユーザーが Onit にログインできるようにするには、そのユーザーを Onit にプロビジョニングする必要があります。 Onit の場合、プロビジョニングは手動で行います。

**ユーザー プロビジョニングを構成するには、次の手順を実行します。**

1. **Onit** 企業サイトに管理者としてサインオンします。
2. [ **ユーザーの追加] を選択します**。

    [Image: 管理]
3. [ **ユーザーの追加** ] ダイアログ ページで、次の手順を実行します。

    [Image: ユーザーを追加]

    ある。 プロビジョニングする有効な Microsoft Entra アカウントの **名前** と **電子メール アドレス** を関連するテキスト ボックスに入力します。

    b。 **[作成]**を選択します。

    注

    Microsoft Entra アカウント所有者がメールを受け取り、リンクに従ってアカウントを確認すると、そのアカウントがアクティブになります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Onit のサインオン URL にリダイレクトされます。
- Onit のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Onit] タイルを選択すると、このオプションは Onit のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/onpage-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に OnPage (SSO) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/onpage-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-27
- Summary: Microsoft Entra ID と OnPage (SSO) の間のシングル サインオンを構成する方法について説明します。

この記事では、OnPage (SSO) と Microsoft Entra ID を統合する方法について説明します。 OnPage (SSO) を Microsoft Entra ID と統合すると、次のことができます。

- OnPage (SSO) にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って OnPage (SSO) に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- OnPage (SSO) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- OnPage (SSO) では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから OnPage (SSO) を追加する

OnPage (SSO) の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に OnPage (SSO) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーから追加**する] セクションで、検索ボックス**に「OnPage (SSO)」**と入力します。
4. 結果パネルから **OnPage (SSO)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### OnPage (SSO) 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、OnPage (SSO) に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと OnPage (SSO) の関連ユーザーとの間にリンク関係を確立する必要があります。

OnPage (SSO) に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **OnPage (SSO) の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **OnPage (SSO) テスト ユーザーを作成** - Microsoft Entra ID のユーザーである B.Simon にリンクされたオンページ (SSO) の対になるユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**OnPage (SSO)**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.onpagecorp.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://sso.onsetmobile.com/dc/v1/callback` |
    | `https://sso.onsetmobile.com/md/v1/callback` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | サインオン URL |
    | --- |
    | `https://sso.onpage.com/dc/v1` |
    | `https://sso.onpage.com/md/v1` |

    注

    識別子の値は実際の値ではありません。 この値を実際の識別子で更新してください。 この値を取得するには、 [OnPage (SSO) サポート チーム](mailto:support@onpagecorp.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **OnPage (SSO) のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### OnPage (SSO) の構成

**OnPage (SSO)** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、Microsoft Entra 管理センターからコピーした適切な URL を [OnPage (SSO) サポート チーム](mailto:support@onpagecorp.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### OnPage (SSO) テスト ユーザーの作成

このセクションでは、OnPage (SSO) で B.Simon というユーザーを作成します。 [OnPage (SSO) サポート チーム](mailto:support@onpagecorp.com)と協力して、OnPage (SSO) プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる OnPage (SSO) のサインオン URL にリダイレクトされます。
- OnPage (SSO) のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで OnPage (SSO) タイルを選択すると、このオプションは OnPage (SSO) のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/onshape-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Onshape を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/onshape-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Onshape 間にシングル サインオンを構成する方法について説明します。

この記事では、Onshape と Microsoft Entra ID を統合する方法について説明します。 Onshape を Microsoft Entra ID を統合すると、次のことができます:

- Onshape にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Onshape に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Onshape サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Onshape では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Onshape では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Onshape の追加

Microsoft Entra ID への Onshape の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Onshape を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Onshape**」と入力します。
4. 結果のパネルから **Onshape** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Onshape 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Onshape に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Onshape の関連ユーザーとの間にリンク関係を確立する必要があります。

Onshape に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Onshape の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Onshape テストユーザーの作成 - Microsoft Entra の B.Simon に対応するユーザーを Onshape に設定し、リンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Onshape**&gt;**シングルサインオン**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. シングル サインオン設定を保存するかどうかを確認するメッセージが表示されたら、 **[はい]** を選択します。
5. Onshape アプリケーションでは、特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
6. その他に、Onshape アプリケーションでは、下に示すいくつかの属性が SAML 応答で返されることが想定されています。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | 会社名 | &lt;COMPANY\_NAME&gt; |

    注

    *companyName* 属性の値を Onshape エンタープライズの "**ドメイン プレフィックス**" に変更する "*必要があります*"。 たとえば、`https://acme.onshape.com` のような URL を使用して Onshape アプリケーションにアクセスしている場合、ドメイン プレフィックスは *acme* です。 この属性値は、DNS 名全体ではなくプレフィックスのみとする必要があります。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Onshape のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Onshape の SSO の構成

**Onshape** 側でシングル サインオンを構成する方法の詳細については、「[Microsoft Entra ID との統合](https://cad.onshape.com/help/Content/MS_AzureAD.htm)」を参照してください。

#### Onshape のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Onshape に作成します。 Onshape では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Onshape にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Onshape のサインオン URL にリダイレクトされます。
- Onshape のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Onshape に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Onshape] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Onshape に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ontrack-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に OnTrack を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ontrack-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と OnTrack の間にシングル サインオンを構成する方法について説明します。

この記事では、OnTrack と Microsoft Entra ID を統合する方法について説明します。 OnTrack と Microsoft Entra ID の統合には、次の利点があります。

- OnTrack にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して OnTrack に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- OnTrack でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- OnTrack では、**IDP** Initiated SSO がサポートされます

### ギャラリーからの OnTrack の追加

Microsoft Entra ID への OnTrack の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に OnTrack を追加する必要があります。

**ギャラリーから OnTrack を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **OnTrack**」と入力し、結果パネルで **[OnTrack]** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の OnTrack]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、OnTrack で Microsoft Entra シングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと OnTrack の関連ユーザーの間にリンク関係を確立する必要があります。

OnTrack で Microsoft Entra シングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **OnTrack のシングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **OnTrack テストユーザーの作成** - Microsoft Entra の Britta Simon にリンクする OnTrack 内の対応ユーザーを作成します。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

OnTrack で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**OnTrack** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    [Image: [OnTrack のドメインと URL] のシングル サインオン情報]

    ある。 **[識別子]** テキスト ボックスに次のように入力します。

    テスト環境の場合は、次の URL を入力します。`https://staging.insigniagroup.com/sso`

    運用環境の場合は、次の URL を入力します。`https://oeaccessories.com/sso`

    b。 **[応答 URL]** ボックスに次のように入力します。

    テスト環境の場合は、次の URL を入力します。`https://indie.staging.insigniagroup.com/sso/autonation.aspx`

    運用環境の場合は、次の URL を入力します。`https://igaccessories.com/sso/autonation.aspx`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[OnTrack クライアント サポート チーム](mailto:CustomerService@insigniagroup.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. OnTrack アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ **ユーザー属性] ダイアログを** 開きます。

    [Image: [ユーザー属性] ダイアログのスクリーンショット。右上で [編集] アイコンが選択されています。]
7. その他に、OnTrack アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | User-Role | "42F432" |
    | Hyperion-Code | "12345" |

    注

    **User-Role** 属性と **Hyperion-Code** 属性は、それぞれ Autonation ユーザー ロールと Dealer コードにマップされます。 これらの値は例なので、統合するための正しいコードを使用してください。 これらの値は、[Autonation サポート](mailto:CustomerService@insigniagroup.com)に問い合わせることができます。

    ある。 [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [ユーザーの要求] ダイアログのスクリーンショット。[新しい要求の追加] および [保存] アクションが選択されています。]

    [Image: 画像]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    え **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **[OK]** を選択します。

    ジー **保存** を選択します。
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[OnTrack のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    ある。 ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### OnTrack のシングル サインオンの構成

**OnTrack** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [OnTrack サポート チーム](mailto:CustomerService@insigniagroup.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### OnTrack のテスト ユーザーの作成

このセクションでは、OnTrack で Britta Simon というユーザーを作成します。 [OnTrack サポート チーム](mailto:CustomerService@insigniagroup.com)と連携し、OnTrack プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [OnTrack] タイルを選択すると、SSO を設定した OnTrack に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/onyxia-tutorial"} -->
## Microsoft Entra ID で Onyxia for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/onyxia-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-03-27
- Summary: Microsoft Entra ID と Onyxia の間でシングル サインオンを構成する方法について説明します。

この記事では、Onyxia と Microsoft Entra ID を統合する方法について説明します。 Onyxia と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Onyxia へのアクセス権を持つユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Onyxia に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Onyxia でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Onyxia は、**SP および IDP** による SSO の両方をサポートします。
- Onyxia では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Onyxia を追加する

Microsoft Entra ID への Onyxia の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Onyxia を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Onyxia」**と入力します。
4. 結果パネルから **Onyxia** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Onyxia の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Onyxia に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Onyxia の関連ユーザーとの間にリンク関係を確立する必要があります。

Onyxia に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Onyxia SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Onyxia テストユーザーの作成** - Microsoft Entra におけるユーザーとしての B.Simon に対応する Onyxia のユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Onyxia**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL には既に Microsoft Entra が事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://auth.onyxia.io/auth/saml/callback`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Onyxia のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Onyxia SSO の構成

1. Onyxia 企業サイトに管理者としてログインします。
2. **[設定]** に移動し、[**アカウント設定]** を選択します。

    [Image: 設定へのナビゲーションを示すスクリーンショット。]
3. **[SSO**] セクションに移動し、[**+ 新しい接続の追加]** を選択します。

    [Image: 新しい接続を追加する方法を示すスクリーンショット。]
4. [ **SSO 構成]** セクションで、次の手順を実行します。

    [Image: 構成を示すスクリーンショット。]

    1. [ **ドメイン名** ] テキスト ボックスに、有効なドメイン名を入力します。
    2. **[SSO URL**] ボックスに、Microsoft Entra 管理センターからコピーした**ログイン URL を**貼り付けます。
    3. ダウンロードした **証明書 (Base64)** をメモ帳に開き、[ **パブリック証明書** ] ボックスに内容を貼り付けます。
    4. **ACS URL を**コピーし、Microsoft Entra 管理センターの **[基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。
    5. **SP エンティティ ID を**コピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの [**識別子 (エンティティ ID)]** ボックスに貼り付けます。
    6. [ **+ 作成]** を選択します。

#### Onyxia テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Onyxia に作成します。 Onyxia では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Onyxia にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Onyxia のサインオン URL にリダイレクトします。
- Onyxia のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Onyxia に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Onyxia] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Onyxia に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/opal-tutorial"} -->
## Microsoft Entra ID で Opal をシングル サインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/opal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Opal の間にシングル サインオンを構成する方法について説明します。

この記事では、Opal と Microsoft Entra ID を統合する方法について説明します。 Opal と Microsoft Entra ID を統合すると、次のことができます。

- Opal にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Opal に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Opal でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Opal では、 **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Opal の追加

Microsoft Entra ID への Opal の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Opal を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Opal**」と入力します。
4. 結果パネルから **Opal** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Opal 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Opal に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Opal の関連ユーザーとの間にリンク関係を確立する必要があります。

Opal に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Opal SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Opal テスト ユーザーの作成** - Microsoft Entra の B.Simon にリンクされた Opal での対になるユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Opal**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、値を入力します。 `Opal`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.ouropal.com/auth/saml/callback`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL で値を更新します。 この値を取得するには [、Opal クライアント サポート チーム](mailto:support@workwithopal.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Opal アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: Opal アプリケーションの画像を示すスクリーンショット。]
7. その他に、Opal アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 名字 | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **Opal のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な URL への構成コピー方法を示しているスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Opal の SSO の構成

**Opal** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Opal サポート チーム](mailto:support@workwithopal.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Opal のテスト ユーザーの作成

このセクションでは、Opal で Britta Simon というユーザーを作成します。 [Opal サポート チーム](mailto:support@workwithopal.com)と協力して、Opal プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Opal に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Opal] タイルを選択すると、SSO を設定した Opal に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/open-text-directory-services-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に OpenText Directory Services を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/open-text-directory-services-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID から OpenText Directory Services に対してユーザー アカウントを自動的にプロビジョニング/プロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために OpenText Directory Services と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使って、OpenText Directory Services に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- OpenText Directory Services でユーザーを作成する
- アクセスが不要になった場合に OpenText Directory Services のユーザーを削除する
- Microsoft Entra ID と OpenText Directory Services の間でユーザー属性の同期を維持する
- OpenText Directory Services でグループとグループ メンバーシップをプロビジョニングする
- OpenText Directory Services への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/directory-services-tutorial) (推奨)
- クライアント資格情報認証がサポートされています。
- 有効期間が長いベアラー トークン認証がサポートされています。

OpenText Directory Services は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Microsoft Entra ID によってアクセス可能な OTDS インストール。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と OpenText Directory Services の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように OpenText Directory Services を構成する

注

以下の手順は、OpenText Directory Services のインストールに適用されます。 OpenText CoreShare テナントまたは OpenText OT2 テナントには適用されません。

1. 専用の機密 **OAuth クライアント**を作成します。
2. リダイレクト URL は指定しないでください。 これらは必須ではありません。
3. OTDS は **クライアント シークレット**を生成して表示します。 **クライアント ID** と**クライアント シークレット**を安全な場所に保存します。

    [Image: クライアント シークレット]
4. Microsoft Entra ID から同期するユーザーとグループのパーティションを作成します。

    [Image: パーティション ページ]
5. 同期する Microsoft Entra ユーザーとグループに使用するパーティションで作成した OAuth クライアントに管理者権限を付与します。

    - [パーティション] -&gt; [アクション] -&gt; [管理者の編集]

    [Image: [管理者] ページ]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから OpenText Directory Services を追加する

Microsoft Entra アプリケーション ギャラリーから OpenText Directory Services を追加して、OpenText Directory Services へのプロビジョニングの管理を開始します。 SSO 用に OpenText Directory Services を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: OpenText Directory Services への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で OpenText Directory Services の自動ユーザー プロビジョニングを構成するには、次のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で [ **OpenText Directory Services**] を選択します。

    [Image: アプリケーションの一覧の [OpenText Directory Services] リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、OpenText Directory Services テナント URL を入力します。

    - 特定以外のテナント URL: `{OTDS URL}/scim/{partitionName}`
    - 特定のテナントのURL: `{OTDS URL}/otdstenant/{tenantID}/scim/{partitionName}`
7. 認証方法として **OAuth2 クライアント資格情報の付与** を選択します。

    1. 手順 2 で取得した **クライアント ID** と **クライアント シークレット** を入力します。
    2. [ **テスト接続]** を選択して、Microsoft Entra ID が OpenText Directory Services に接続できることを確認します。
    3. 接続できない場合は、OpenText Directory Services アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

        [Image: トークンのスクリーンショット。]
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から OpenText Directory Services に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で OpenText Directory Services のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、OpenText Directory Services API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

| 特性 | タイプ |
| --- | --- |
| ユーザー名 | 糸 |
| 活動中 | ブール値 |
| 表示名 | 糸 |
| タイトル | 糸 |
| emails[type eq "仕事"].value | 糸 |
| 優先言語 | 糸 |
| 名前.名 | 糸 |
| 名前.姓 | 糸 |
| 名前.整形済み | 糸 |
| addresses[type eq "work"].フォーマット済み | 糸 |
| アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |
| アドレス[タイプ eq "職場"].ローカリティ | 糸 |
| アドレス[タイプが"仕事"に等しい].地域 | 糸 |
| addresses[タイプ eq "work"].郵便番号 | 糸 |
| アドレス[タイプ Eq "仕事"].国 | 糸 |
| phoneNumbers[タイプが "職場" の場合].値 | 糸 |
| 電話番号[タイプ eq "携帯"].値 | 糸 |
| phoneNumbers[type eq "ファックス"].value | 糸 |
| エクスターナルID | 糸 |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | リファレンス |

1. **[グループ]** を選びます。
2. [属性マッピング] セクションで、Microsoft Entra ID から OpenText Directory Services に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で OpenText Directory Services のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | 表示名 | 糸 |
    | エクスターナルID | 糸 |
    | メンバー | リファレンス |
3. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
4. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
5. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/openathens-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に OpenAthens を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/openathens-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と OpenAthens 間にシングル サインオンを構成する方法について学習します。

この記事では、OpenAthens と Microsoft Entra ID を統合する方法について説明します。 OpenAthens を Microsoft Entra ID と統合すると、次のことができます。

- OpenAthens にアクセスできるユーザーを Microsoft Entra ID を制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って OpenAthens に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- OpenAthens でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- OpenAthens では、**IDP** によって開始される SSO がサポートされます
- OpenAthens では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの OpenAthens の追加

Microsoft Entra ID への OpenAthens の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に OpenAthens を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**OpenAthens**」と入力します。
4. 結果のパネルから **[OpenAthens]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### OpenAthens 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、OpenAthens に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと OpenAthens の関連ユーザーとの間にリンク関係を確立する必要があります。

OpenAthens に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **OpenAthens SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **OpenAthens のテスト ユーザーの作成** - OpenAthens で B.Simon の対応ユーザーを作成し、それを Microsoft Entra におけるユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**OpenAthens**&gt;**シングルサインオンに**アクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダーメタデータファイル**をアップロードします。この手順については、この記事の後半で説明します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: OpenAthens (メタデータのアップロード)]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: OpenAthens (アップロードするメタデータの参照)]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**の値が、 **[基本的な SAML 構成]** セクションのテキスト ボックスに自動的に設定されます。

    [Image: [OpenAthens のドメインと URL] のシングル サインオン情報]
6. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[OpenAthens のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### OpenAthens の SSO の構成

1. 別の Web ブラウザー ウィンドウで、OpenAthens 企業サイトに管理者としてサインインします。
2. **[管理]** タブの一覧から **[接続]** を選択します。
3. **[SAML 1.1/2.0]** を選択し、 **[構成]** を選択します。

    [Image: [Select local authentication system type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ローカル認証システムの種類の選択) ダイアログを示すスクリーンショット。[S A M L 1.1/2.0] と [Configure](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) ボタンが選択されています。]
4. 構成を追加するには、**[参照]** を選択して、ダウンロードしたメタデータ .xml ファイルをアップロードし、**[追加]** を選択します。

    [Image: [Add S A M L authentication system](SAML 認証システムの追加) ダイアログを示すスクリーンショット。[Browse](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/参照) アクションと [Add](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加) ボタンが選択されています。]
5. **[詳細]** タブで、次の手順を実行します。

    [Image: シングル サインオンの構成]

    ある。 **[Display name mapping](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/表示名マッピング)** で、 **[Use attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/属性の使用)** を選択します。

    b。 **[Display name attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/表示名属性)** ボックスに、値「`http://schemas.microsoft.com/identity/claims/displayname`」を入力します。

    c. **[Unique user mapping](一意のユーザー マッピング)** で、 **[Use attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/属性の使用)** を選択します。

    d. **[Display name attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/一意のユーザ属性)** ボックスに、値「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name`」を入力します。

    え **[状態]** で、3 つのチェック ボックスすべてをオンにします。

    f. **[Create local accounts](ローカル アカウントの作成)** で、 **[automatically](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/自動)** を選択します。

    ジー **[変更の保存]** を選択します。

    h. **[&lt;/&gt; Relying Party]( 証明書利用者)** タブで、**[Metadata URL](メタデータ URL)** をコピーし、その URL をブラウザーで開いて **SP メタデータ XML** ファイルをダウンロードします。 Microsoft Entra ID の **[基本的な SAML 構成]** セクションで、この SP メタデータ ファイルをアップロードします。

    [Image: [Relying Party](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書利用者) タブが選択されている画面のスクリーンショット。[メタデータ URL] が強調表示されています。]

#### OpenAthens のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを OpenAthens に作成します。 OpenAthens では、**Just-In-Time ユーザー プロビジョニング**がサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 OpenAthens にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した OpenAthens に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [OpenAthens] タイルを選択すると、SSO を設定した OpenAthens に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/openidoauth-tutorial"} -->
## Microsoft Entra アプリ ギャラリーから OpenID Connect OAuth アプリケーションを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/openidoauth-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra アプリ ギャラリーから OpenID Connect OAuth アプリケーションを構成する手順。

この記事では、OpenID Connect を実装するアプリケーション ギャラリー内のアプリケーションについて説明します。 社内で開発されたアプリケーションを含む他のアプリケーションに対して OpenID Connect を有効にする方法の詳細については、 [Microsoft ID プラットフォームでの OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc) と [、カスタム (ギャラリー以外) アプリケーションの OIDC SSO の構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso#configure-oidc-sso-for-custom-non-gallery-applications)に関するページを参照してください。

### ギャラリーから OpenID アプリケーションを追加する手順

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**に移動します。

    [Image: [エンタープライズ アプリケーション] ブレード]
3. ダイアログ ボックスの上部にある [ **新しいアプリケーション** ] を選択します。

    [Image: [新しいアプリケーション] ボタン]
4. 検索ボックスに、アプリケーション名を入力します。 検索パネルで目的のアプリケーションを選択して、アプリケーションにサインアップします。

    [Image: 結果一覧のOpenID]
5. [アプリケーション名] ページで、[ **サインアップ** ] ボタンを選択します。

    [Image: [追加] ボタン]

    注意

    ここでテナント管理者は、サインアップ ボタンを選択して、アプリケーションに同意する必要があります。 これによりアプリケーションが外部テナントに追加されて、構成を行うことができます。 アプリケーションを明示的に追加する必要はありません。
6. サインイン資格情報については、アプリケーションのログイン ページまたは Microsoft Entra ID ページにリダイレクトされます。
7. 認証に成功したら、同意ページで同意を受け入れます。 その後、アプリケーションのホーム ページが表示されます。

    注意

    追加できるアプリケーションのインスタンスは 1 つだけです。 既に追加済みで、再度同意の提供を試みた場合は、テナントに再度追加されることはありません。 そのため論理的には、テナント内で使用できるのは 1 つのアプリ インスタンスのみです。
8. 下のビデオに従って、ギャラリーから OpenID アプリケーションを追加します。

### OpenID Connect を使用する認証フロー

最も基本的なサインイン フローには次の手順が含まれています。

[Image: OpenID Connect を使用した認証フロー]

#### マルチテナント アプリケーション

マルチテナント アプリケーションは、1 つの組織ではなく、多数の組織で使用することを目的としています。 通常、これらは独立系ソフトウェア ベンダー (ISV) によって作成された SaaS (サービスとしてのソフトウェア) アプリケーションです。

マルチテナント アプリケーションは、それらが使用される各ディレクトリにプロビジョニングする必要があります。 これらを登録するには、ユーザーまたは管理者の同意が必要です。 この同意プロセスは、アプリケーションをディレクトリに登録し、Graph API または別の Web API へのアクセス権を付与するときに開始されます。 別の組織のユーザーまたは管理者がアプリケーションを使用するためにサインアップすると、アプリケーションに必要なアクセス許可がダイアログ ボックスに表示されます。

ユーザーまたは管理者は、そこでアプリケーションに同意することができます。 この同意によって、アプリケーションは定められたデータへのアクセスが許可され、最終的にディレクトリに登録されます。

注意

複数のディレクトリ内のユーザーがアプリケーションを使用できるようにする場合、ユーザーが属するテナントを確認するためのメカニズムが必要です。 シングルテナント アプリケーションでは、それ自体のディレクトリでユーザーを探すだけで済みます。 マルチテナント アプリケーションでは、Microsoft Entra ID のすべてのディレクトリから特定のユーザーを識別する必要があります。

このタスクを実行するために、Microsoft Entra ID には、テナント固有のエンドポイントの代わりに、マルチテナント アプリケーションがサインイン要求を送信できる共通の認証エンドポイントが用意されています。 このエンドポイントは、Microsoft Entra ID 内にあるすべてのディレクトリの `https://login.microsoftonline.com/common` です。 テナント固有のエンドポイントであれば `https://login.microsoftonline.com/contoso.onmicrosoft.com` のようになります。

アプリケーションを開発するときは、共通のエンドポイントを考慮することが重要です。 サインイン、サインアウト、トークンの検証時に複数のテナントに対応するためのロジックが必要となります。

既定では、Microsoft Entra ID を使うと、マルチテナント アプリケーションが昇格します。 これらは、複数の組織全体でアクセスが容易です。また、お客様が同意を受け入れた後の使用が容易です。

### 同意フレームワーク

Microsoft Entra の同意フレームワークを使って、マルチテナントの Web クライアント アプリケーションとネイティブ クライアント アプリケーションを開発できます。 これらのアプリケーションには、そのアプリケーションが登録されている Microsoft Entra テナントとは異なるテナントのユーザー アカウントを使ってサインインできます。 また、次のような Web API へのアクセスが必要になることもあります。

- Microsoft Graph API (Microsoft Entra ID、Intune、Microsoft 365 のサービスにアクセスするため)。
- その他の Microsoft サービスの API。
- お客様独自の Web API。

このフレームワークは、ディレクトリへの登録を要求するアプリケーションに対して同意を与えるユーザーまたは管理者の存在が前提となっています。 登録には、ディレクトリ データへのアクセスが伴う場合があります。 同意が与えられると、クライアント アプリケーションがユーザーに代わって Microsoft Graph API を呼び出し、必要に応じて情報を利用できるようになります。

[Microsoft Graph API](https://developer.microsoft.com/graph/) は、次のような Microsoft 365 のデータへのアクセスを提供します。

- Exchange の予定表とメッセージ。
- SharePoint のサイトとリスト。
- OneDrive のドキュメント。
- OneNote のノートブック。
- Planner のタスク。
- Excel のブック。

Microsoft Entra ID のユーザーとグループや、Microsoft クラウド サービスの他のデータ オブジェクトにも、Graph API を使用してアクセスすることができます。

以下の手順は、アプリケーションの開発者とユーザーにとっての同意エクスペリエンスがどのようなものになるかを示しています。

1. リソースまたは API にアクセスするために一定のアクセス許可を要求する必要がある Web クライアント アプリケーションがあると仮定しましょう。 Azure Portal は、構成時にアクセス許可要求を宣言するために使用されます。 これらは他の構成設定と同様、アプリケーションを Microsoft Entra に登録する一環として行います。 必要なアクセス許可の要求パスについては、以下の手順に従ってください。

    ある。 メニューの左側から **[アプリの登録** ] を選択し、検索ボックスにアプリケーション名を入力してアプリケーションを開きます。

    [Image: 左側のメニューから [アプリの登録] が選択され、[Application I D] 検索ボックスが強調表示されているスクリーンショット。]

    b。 [ **API のアクセス許可の表示] を選択します**。

    [Image: [View A P I Permissions](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス許可の表示) ボタンが選択されている [Call A P I](P I の呼び出し) ページを示すスクリーンショット。]

    c. [ **アクセス許可の追加] を選択します**。

    [Image: [アクセス許可の追加] ボタンが選択されている [A P I のアクセス許可] セクションを示すスクリーンショット。]

    d. **Microsoft Graph** を選択します。

    え **[委任されたアクセス許可**] と [**アプリケーション**のアクセス許可] から必要なオプションを選択します。

    [Image: グラフAPI]
2. アプリケーションのアクセス許可が更新された状況を考えてみましょう。 アプリケーションは実行中であり、ユーザーが初めてアプリケーションを使うところです。 アプリケーションではまず、Microsoft Entra ID の /authorize エンドポイントから認証コードを取得する必要があります。 取得した認証コードは、後でアクセス トークンと更新トークンの取得に使用します。
3. ユーザーがまだ認証されていない場合、Microsoft Entra ID /authorize エンドポイントはサインインを求めます。

    [Image: アカウントのサインイン プロンプトのスクリーンショット]
4. ユーザーのサインインが終わると、そのユーザーに対して同意ページを表示する必要があるかどうかが Microsoft Entra ID によって判定されます。 表示の要否の判定基準は、ユーザー (またはそのユーザーが所属する組織の管理者) がアプリケーションに既に同意を与えているかどうかです。

    同意がまだであれば、Microsoft Entra からユーザーに対して同意を求めるメッセージと、アプリケーションが機能するうえで必要なアクセス許可が表示されます。 同意ダイアログ ボックスに表示されるアクセス許可は、[委任されたアクセス許可] で選択されているものと同じになります。

    [Image: 同意ページ]

通常のユーザーはいくつかのアクセス許可に同意できます。 その他のアクセス許可では、テナント管理者の同意が必要になります。

### 管理者の同意とユーザーの同意の違い

管理者であれば、テナント内のユーザー全員に代わってアプリケーションの委任されたアクセス許可に同意することもできます。 管理者が同意すると、テナントの各ユーザーには同意ダイアログ ボックスが表示されなくなります。 管理者ロールを持つユーザーは同意を付与することができます。 [ **管理**&gt;**API アクセス許可**] を選択します。 **同意を付与** の下で、**管理者の同意を付与** を選択します。

注意

MSAL.jsを使用するシングルページ アプリケーション (SPA) では、[ **管理者の同意の付与** ] ボタンを使用して明示的な同意を付与する必要があります。 そうしないと、アクセス トークンが要求されたときにアプリケーションでエラーが発生します。

アプリケーション専用アクセス許可では、常にテナント管理者の同意が必要になります。 アプリケーションがアプリ専用アクセス許可を要求する場合に、ユーザーがそのアプリケーションにサインインしようとすると、エラー メッセージが表示されます。 メッセージによって、そのユーザーは同意できないことが伝えられます。

アプリケーションで管理者の同意が必要なアクセス許可を使用する場合、ジェスチャ (管理者がアクションを開始できるボタンやリンク) を設定する必要があります。 通常、このアクションに対してアプリケーションから送信される要求は OAuth2 または OpenID Connect 承認要求です。 この要求には、 *prompt=admin\_consent* クエリ文字列パラメーターが含まれます。

管理者が同意し、サービス プリンシパルが顧客のテナントに作成された後、後のサインイン要求には *prompt=admin\_consent* パラメーターは必要ありません。 管理者は要求されたアクセス許可を許容可能と判断しているため、その時点からは、テナント内の他のユーザーが同意を求められることはありません。

テナント管理者は、通常ユーザーによるアプリケーションへの同意を無効にすることができます。 通常ユーザーによる同意が無効化された場合、テナントでアプリケーションを使用するには常に管理者の同意が必要になります。 エンド ユーザーの同意を無効にしてアプリケーションをテストする場合は、 [Azure portal](https://portal.azure.com/) で構成スイッチを見つけることができます。 [[エンタープライズ アプリケーション](https://portal.azure.com/#blade/Microsoft_AAD_IAM/StartboardApplicationsMenuBlade/UserSettings/menuId/)] の [**ユーザー設定**] セクションにあります。

*prompt=admin\_consent* パラメーターは、管理者の同意を必要としないアクセス許可を要求するアプリケーションでも使用できます。 たとえば、テナント管理者が 1 回 "サインアップする" と、その時点からは他のユーザーが同意を求められないエクスペリエンスを必要とするアプリケーションが該当します。

アプリケーションに管理者の同意が必要であり、 *prompt=admin\_consent* パラメーターを送信せずに管理者がサインインするとします。 管理者がアプリケーションへの同意に成功したとき、それが適用されるのは、自分のユーザー アカウントだけです。 通常のユーザーは、アプリケーションへのサインインも同意も実行できないままです。 この機能は、他のユーザーのアクセスを許可する前に、テナント管理者がアプリケーションを確認できるようにしたい場合に役立ちます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/openlearning-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に OpenLearning を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/openlearning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と OpenLearning の間でシングル サインオンを構成する方法について説明します。

この記事では、OpenLearning と Microsoft Entra ID を統合する方法について説明します。 OpenLearning と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で OpenLearning にアクセスできるユーザーを管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して OpenLearning に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

OpenLearning は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- OpenLearning でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- OpenLearning では、**SP** Initiated SSO がサポートされます。

### ギャラリーから OpenLearning を追加する

Microsoft Entra ID への OpenLearning の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に OpenLearning を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**OpenLearning**」と入力します。
4. 結果パネルから **[OpenLearning]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### OpenLearning 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、OpenLearning で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと OpenLearning の関連ユーザーとの間にリンク関係を確立する必要があります。

OpenLearning で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **OpenLearning の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **OpenLearning のテストユーザーを作成 - B.Simon に対応するユーザーを作成し、Microsoft Entra にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**OpenLearning**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、**サービス プロバイダー メタデータ ファイル**がある場合は、次の手順に従います。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルのアップロードを示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**の値が、[基本的な SAML 構成] セクションに自動的に設定されます。

    d. **[サインオン URL]** ボックスに、`https://www.openlearning.com/saml-redirect/<institution_id>/<idp_name>/` という形式で URL を入力します。

    注

    **識別子**の値が自動的に設定されない場合は、要件に従って値を手動で入力してください。 サインオン URL の値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには、[OpenLearning クライアント サポート チーム](mailto:dev@openlearning.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. OpenLearning Identity Authentication アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングをご自分の SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
7. 上記に加えて、OpenLearning Identity Authentication アプリケーションでは、以下に示す SAML 応答で返される属性がほとんどないことが想定されます。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:0.9.2342.19200300.100.1.3 | user.mail |
    | urn:oid:2.16.840.1.113730.3.1.241 | ユーザー表示名 |
    | urn:oid:1.3.6.1.4.1.5923.1.1.1.9 | user.extensionattribute1 |
    | urn:oid:1.3.6.1.4.1.5923.1.1.1.6 | user.objectid (ユーザーのオブジェクトID) |
    | urn:oid:2.5.4.10 | ユーザー.companyname |
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[OpenLearning の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成 URL をコピーする画面のスクリーンショット。]
10. OpenLearning アプリケーションでは、SSO を機能させるためにトークン暗号化を有効にすることが予測されます。 トークン暗号化をアクティブにするには、**Entra ID**&gt;**Enterprise アプリ**を参照&gt;アプリケーション &gt;**トークン暗号化**を選択します。 詳細については、「[Microsoft Entra の SAML トークン暗号化を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption)」を参照してください。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### OpenLearning の SSO の構成

1. OpenLearning の企業サイトに管理者としてログインします。
2. &gt;] に移動し、[SAML ID プロバイダー (IDP) の構成] で **[追加**] を選択します。
3. **[SAML ID プロバイダー]** ページで、次の手順を実行します。

    [Image: SAML 設定を示すスクリーンショット]

    1. **[名前 (必須)]** テキスト ボックスに、短い構成名を入力します。
    2. **Reply(ACS) URL** の値をコピーし、この値を **[基本的な SAML 構成]** セクションの **[Reply URL]** テキスト ボックスに貼り付けます。
    3. **[エンティティ ID/発行者 URL (必須)]** テキスト ボックスに、先ほどコピーした **[Microsoft Entra 識別子]** の値を貼り付けます。
    4. **[サインイン URL (必須)]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。
    5. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[証明書 (必須)]** テキストボックスに貼り付けます。
    6. **メタデータ XML** をメモ帳にダウンロードし、そのファイルを **[基本的な SAML 構成]** セクションにアップロードします。
    7. **保存** を選択します。

#### OpenLearning のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを OpenLearning に作成します。 OpenLearning では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 OpenLearning にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる OpenLearning のサインオン URL にリダイレクトされます。
- OpenLearning のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [OpenLearning] タイルを選択すると、このオプションは OpenLearning のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/opsgenie-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に OpsGenie を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/opsgenie-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と OpsGenie の間にシングル サインオンを構成する方法についてご確認ください。

この記事では、OpsGenie と Microsoft Entra ID を統合する方法について説明します。 OpsGenie を Microsoft Entra ID と統合すると、次のことができるようになります。

- OpsGenie にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して OpsGenie に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- OpsGenie でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- OpsGenie では、 **IDP** Initiated SSO がサポートされます

### ギャラリーからの OpsGenie の追加

Microsoft Entra ID への OpsGenie の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに OpsGenie を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を開きます。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「OpsGenie**」と入力します。
4. 結果パネルから **OpsGenie** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### OpsGenie 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、OpsGenie に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと OpsGenie の関連ユーザーとの間にリンク関係を確立する必要があります。

OpsGenie に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **OpsGenie SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **OpsGenie のテストユーザーを作成する** - OpsGenie に B.Simon に相当するユーザーを作成し、Microsoft Entra のユーザーとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**OpsGenie**&gt;**シングルサインオン**のページに移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.opsginie.com/auth/saml/<UNIQUEID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.opsginie.com/auth/saml?id=<UNIQUEID>`

    注意

    これらの値は実際の値ではありません。 これらの値を実際の識別子と応答 URL で更新します。これについては、この記事の後半で説明します。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **OpsGenie のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### OpsGenie の SSO の構成

1. 別の Web ブラウザー ウィンドウで、OpsGenie 企業サイトに管理者としてサインインします
2. [ **設定]** を選択し、[ **シングル サインオン** ] タブを選択します。

    [Image: OpsGenie シングルサインオン]
3. SSO を有効にするには、[ **有効]** を選択します。

    [Image: [有効] チェックボックスが選択されていることを示すスクリーンショット。]
4. [ **プロバイダー** ] セクションで、[ **Microsoft Entra ID** ] タブを選択します。
5. [Microsoft Entra] ダイアログ ページで、次の手順を実行します。

    [Image: [シングル サインオンを有効にする] トグル、]

    a。 **[アプリ ID URI**] の値をコピーし、[**基本的な SAML 構成**] セクションの [**識別子 (エンティティ ID)]** ボックスに貼り付けます。

    a。 **[応答 URL**] の値をコピーし**、[基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。

    a。 **[SAML 2.0 Endpoint]\(SAML 2.0 エンドポイント**\) ボックスに、前にコピーした**ログイン URL**値を貼り付けます。

    b。 [ **メタデータ URL:** ] ボックスに、前にコピーした **アプリのフェデレーション メタデータ URL の値を** 貼り付けます。

    c. SSO を有効にするには、[ **シングル サインオンを有効にする] トグルを** オンにします。

    d. [ **SSO 設定の適用] を選択します**。

#### OpsGenie テスト ユーザーの作成

このセクションの目的は、OpsGenie で B. Simon というユーザーを作成することです。

1. Web ブラウザー ウィンドウで、OpsGenie テナントに管理者としてサインインします。
2. 左側のパネルで [ユーザー] を選択して、[ **ユーザー** ] リストに移動します。

    [Image: OpsGenie の設定]
3. [ **ユーザーの追加] を選択します**。
4. [ **ユーザーの追加** ] ダイアログで、次の手順を実行します。

    [Image: [電子メール] テキスト ボックスと [フル ネーム] テキスト ボックスが強調表示され、[保存] ボタンが選択されている [ユーザーの追加] ダイアログを示すスクリーンショット。]

    a。 [ **電子メール** ] ボックスに、Microsoft Entra ID でアドレス指定された B.Simon のメール アドレスを入力します。

    b。 [Full Name]\( **フル ネーム\)** ボックスに「 **B.Simon」と入力します**。

    c. **[保存] を選択します**。

注意

B.Simon にプロファイルの設定方法が記載されたメールが届きます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した OpsGenie に自動的にサインインします
- Microsoft マイ アプリを使用することができます。 マイ アプリで [OpsGenie] タイルを選択すると、SSO を設定した OpsGenie に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/optimizely-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Optimizely を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/optimizely-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Optimizely の間にシングル サインオンを構成する方法についてご確認ください。

Microsoft Entra ID と Optimizely の間の SSO 統合は、Optimizely のOpti ID 管理センターで構成できます。 Optimizely ナレッジ ベースの「[Opt ID の概要](https://support.optimizely.com/hc/en-us/articles/12613241464461-Get-started-with-Opti-ID)」に記載されている手順に従ってください

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Optimizely のサインオン URL にリダイレクトされます。
- Optimizely のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Optimizely] タイルを選択すると、このオプションは Optimizely のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/optiturn-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用にOptiTurn を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/optiturn-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と OptiTurn 間にシングル サインオンを構成する方法について説明します。

この記事では、OptiTurn と Microsoft Entra ID を統合する方法について説明します。 OptiTurn は、小売業者が返品商品をルーティングし、倉庫の運用を改善し、在庫バックログを管理するのに役立つ返品管理プラットフォームです。 OptiTurn を Microsoft Entra ID と統合すると、次のことができます。

- OptiTurn にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して OptiTurn に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で OptiTurn 向けの Microsoft Entra のシングル サインオンを構成してテストします。 OptiTurn は、**SP** Initiated シングル サインオンと **Just-In-Time** ユーザー プロビジョニングをサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を OptiTurn と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な OptiTurn のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから OptiTurn アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから OptiTurn を追加する

Microsoft Entra アプリケーション ギャラリーから OptiTurn を追加して、OptiTurn とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**OptiTurn**&gt;**シングルサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** テキストボックスに、次を使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://optiturn.com/sp` - 運用 |
    | `https://sandbox.optiturn.com/sp` - テスト |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://optiturn.com/auth/saml/<Customer_Name>_azure_saml/callback` - 運用 |
    | `https://sandbox.optiturn.com/auth/saml/<Customer_Name>_azure_saml/callback` - テスト |

    c. **[サインオン URL]** テキストボックスに、次のいずれかを入力します。

    | **サインオン URL** |
    | --- |
    | `https://optiturn.com/session/new` - 運用 |
    | `https://sandbox.optiturn.com/session/new` - テスト |

    注

    `<Customer_Name>` は、小文字とアンダースコア バージョンの会社名に置き換える必要があります。 たとえば、Fake Corp. は fake\_corp になります。 [OptiTurn サポート チーム](mailto:support@optoro.com)が、この値を選択するのをお手伝いします。
6. OptiTurn アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、OptiTurn アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | 名（ファーストネーム） | ユーザー.ファーストネーム |
    | last\_name | ユーザーの名字 |

    注

    warehouse\_identifier アサーション属性は使いやすくするために推奨されますが、必須ではありません。 warehouse\_identifier は、特定の従業員が物理的に配置されている倉庫の ID です。 識別子を、OptiTurn で構成されている倉庫と照合します。 これで、ユーザーのアクティビティとデータは、そのウェアハウスの "範囲に設定" されます。
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (Base64)]** を見つけます。**[ダウンロード]** を選択して証明書をダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[OptiTurn のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### OptiTurn SSO を構成する

**OptiTurn** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [OptiTurn サポート チーム](mailto:support@optoro.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### OptiTurn テスト ユーザーを作成する

このセクションでは、B. Simon というユーザーを OptiTurn に作成します。 OptiTurn は、Just-In-Time ユーザー プロビジョニングをサポートしています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 OptiTurn にユーザーがまだ存在していない場合、通常は認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できるOptiTurn のサインオン URL にリダイレクトされます。
- OptiTurn のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [OptiTurn] タイルを選択すると、このオプションは、OptiTurn のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oracle-access-manager-for-oracle-ebs-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Oracle Access Manager for Oracle E-Business Suite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-access-manager-for-oracle-ebs-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Oracle Access Manager for Oracle E-Business Suite の間でシングル サインオンを構成する方法について説明します。

この記事では、Oracle Access Manager for Oracle E-Business Suite と Microsoft Entra ID を統合する方法について説明します。 Oracle Access Manager for Oracle E-Business Suite と Microsoft Entra ID を統合すると、次のことができます。

- Oracle Access Manager for Oracle E-Business Suite にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して Oracle Access Manager for Oracle E-Business Suite に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Oracle Access Manager for Oracle E-Business Suite 用に Microsoft Entra シングル サインオンを構成してテストします。 Oracle Access Manager for Oracle E-Business Suite では、**SP** によって開始されるシングル サインオンのみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID と Oracle Access Manager for Oracle E-Business Suite を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Oracle Access Manager for Oracle E-Business Suite のシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Oracle Access Manager for Oracle E-Business Suite アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Oracle Access Manager for Oracle E-Business Suite を追加する

Oracle Access Manager for Oracle E-Business Suite とのシングル サインオンを構成するには、Microsoft Entra アプリケーション ギャラリーから Oracle Access Manager for Oracle E-Business Suite を追加します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Oracle Access Manager for Oracle E-Business Suite**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 ` https://<SUBDOMAIN>.oraclecloud.com/`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.oraclecloud.com/v1/saml/<UNIQUEID>>`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 ` https://<SUBDOMAIN>.oraclecloud.com/`
6. Oracle Access Manager for Oracle E-Business Suite アプリケーションでは、特定の形式の SAML アサーションが想定されているため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Oracle Access Manager for Oracle E-Business Suite では、これがユーザーのメール アドレスにマップされると想定します。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

### Oracle Access Manager for Oracle E-Business Suite の SSO を構成する

1. Oracle Access Manager コンソールに管理者としてサインインします。
2. コンソールの上部にある [ **フェデレーション** ] タブを選択します。
3. [**Launch Pad**] タブの [**フェデレーション**] 領域で、[**サービス プロバイダーの管理**] を選択します。
4. [サービス プロバイダーの管理] タブで、[ **ID プロバイダー パートナーの作成**] を選択します。
5. **[一般]** 領域で、**アイデンティティ・プロバイダ・パートナ**の名前を入力し、**[パートナの有効化] と [デフォルトのアイデンティティ・プロバイダ・パートナ]** の両方を選択します。 保存する前に、次の手順に進みます。
6. **[サービス情報]** 領域で、次のようにします。

    ある。 プロトコルとして **[SAML2.0]** を選択します。

    b。 **[プロバイダ・メタデータからロード]** を選択します。

    c. [ **参照** ] (Windows の場合) または **[ファイルの選択** ] (Mac の場合) を選択し、前にダウンロードした **フェデレーション メタデータ XML** ファイルを選択します。

    d. 保存する前に、次の手順に進みます。
7. **[マッピング・オプション]** 領域で、次のようにします。

    ある。 E-Business Suite ユーザーに対 **して** チェックされる Oracle Access Manager LDAP ID ストアとして使用されるユーザー ID ストア オプションを選択します。 通常、これは Oracle Access Manager ID ストアとして既に構成されています。

    b。 **[ユーザー検索ベース DN]** は空白のままにします。 検索ベースは、ID ストア構成から自動的に選択されます。

    c. **[ユーザー ID ストア属性にアサーション名 ID をマップ]** を選択し、テキスト ボックスに「mail」と入力します。
8. [ **保存] を** 選択して ID プロバイダー パートナーを保存します。
9. パートナーが保存されたら、タブの下部にある **[Advanced] (詳細設定)** 領域に戻ります。オプションが次のように構成されていることを確認します。

    ある。 **[グローバル・ログアウトの有効化]** が選択されていること。

    b。 **[HTTP POST SSO レスポンス・バインディング]** が選択されていること。

#### Oracle Access Manager for Oracle E-Business Suite のテスト ユーザーを作成する

このセクションでは、Oracle Access Manager for Oracle E-Business Suite で Britta Simon というユーザーを作成します。 [Oracle Access Manager for Oracle E-Business Suite サポート チーム](https://www.oracle.com/support/advanced-customer-services/cloud/)と協力して、Oracle Access Manager for Oracle E-Business Suite プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションを選択すると、ログイン フローを開始できる Oracle Access Manager for Oracle E-Business Suite のサインオン URL にリダイレクトされます。
- Oracle Access Manager for Oracle E-Business Suite のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Oracle Access Manager for Oracle E-Business Suite タイルを選択すると、このオプションは Oracle Access Manager for Oracle E-Business Suite のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oracle-access-manager-for-oracle-retail-merchandising-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Oracle Access Manager for Oracle Retail Merchandising を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-access-manager-for-oracle-retail-merchandising-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Azure Active Directory と Oracle Access Manager for Oracle Retail Merchandising の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、Oracle Access Manager for Oracle Retail Merchandising と Microsoft Entra ID を統合する方法について説明します。 Oracle Access Manager for Oracle Retail Merchandising を Microsoft Entra ID と統合すると、以下が可能になります。

- Oracle Access Manager for Oracle Retail Merchandising にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Oracle Access Manager for Oracle Retail Merchandising に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Oracle Access Manager for Oracle Retail Merchandising 用に Microsoft Entra シングル サインオンを構成してテストします。 Oracle Access Manager for Oracle Retail Merchandising では、 **SP** によって開始されるシングル サインオンのみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID と Oracle Access Manager for Oracle Retail Merchandising を統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Oracle Access Manager for Oracle Retail Merchandising のシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Oracle Access Manager for Oracle Retail Merchandising アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーからの Oracle Access Manager for Oracle Retail Merchandising の追加

Oracle Access Manager for Oracle Retail Merchandising でのシングル サインオンを構成するには、Microsoft Entra アプリケーション ギャラリーから Oracle Access Manager for Oracle Retail Merchandising を追加します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Oracle Access Manager for Oracle Retail Merchandising**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 ` https://<SUBDOMAIN>.oraclecloud.com/`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.oraclecloud.com/v1/saml/<UNIQUEID>`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 ` https://<SUBDOMAIN>.oraclecloud.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を入手するには、[Oracle Access Manager for Oracle Retail Merchandising のサポート チーム](https://www.oracle.com/support/advanced-customer-services/cloud/)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Oracle Access Manager for Oracle Retail Merchandising アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Oracle Access Manager for Oracle Retail Merchandising では、これがユーザーのメール アドレスにマップされるものと想定します。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

### Oracle Access Manager for Oracle Retail Merchandising SSO の構成

Oracle Access Manager for Oracle Retail Merchandising 側でのシングル サインオンを構成するには、ダウンロードしたフェデレーション メタデータ XML ファイルを Azure portal から [Oracle Access Manager for Oracle Retail Merchandising のサポート チーム](https://www.oracle.com/support/advanced-customer-services/cloud/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Oracle Access Manager for Oracle Retail Merchandising テスト ユーザーの作成

このセクションでは、Oracle Access Manager for Oracle Retail Merchandising で Britta Simon というユーザーを作成します。 [Oracle Access Manager for Oracle Retail Merchandising のサポート チーム](https://www.oracle.com/support/advanced-customer-services/cloud/)と連携して、Oracle Access Manager for Oracle Retail Merchandising プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Oracle Access Manager for Oracle Retail Merchandising のサインオン URL にリダイレクトされます。
- Oracle Access Manager for Oracle Retail Merchandising のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Oracle Access Manager for Oracle Retail Merchandising タイルを選択すると、このオプションは Oracle Access Manager for Oracle Retail Merchandising のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oracle-cloud-infrastructure-console-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Oracle Cloud Infrastructure Console を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-cloud-infrastructure-console-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-21
- Summary: Microsoft Entra ID から Oracle Cloud Infrastructure Console に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を説明します。

注

Oracle Cloud Infrastructure Console または Oracle IDCS とカスタム/BYOA アプリケーションの統合はサポートされていません。 この記事の説明に従ってギャラリー アプリケーションを使用することがサポートされています。 ギャラリー アプリケーションは、Oracle SCIM サーバーと連携するようにカスタマイズされています。

この記事では、自動ユーザー プロビジョニングを構成するために Oracle Cloud Infrastructure Console と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID により、Microsoft Entra プロビジョニング サービスを使って、[Oracle Cloud Infrastructure Console](https://www.oracle.com/cloud/free/?source=:ow:o:p:nav:0916BCButton&amp;intcmp=:ow:o:p:nav:0916BCButton) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- Oracle Cloud Infrastructure Console でのユーザーの作成
- アクセスが不要になった場合に Oracle Cloud Infrastructure Console でユーザーを削除する
- Microsoft Entra ID と Oracle Cloud Infrastructure Console の間でユーザー属性の同期を維持する
- Oracle Cloud Infrastructure Console でのグループおよびグループメンバーシップのプロビジョニング
- Oracle Cloud Infrastructure Console への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-cloud-tutorial) (推奨)

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Oracle Cloud Infrastructure Console [テナント](https://www.oracle.com/cloud/sign-in.html?intcmp=OcomFreeTier&amp;source=:ow:o:p:nav:0916BCButton)。
- Oracle Cloud Infrastructure Console での管理者権限を備えたユーザー アカウント。

注

この統合は、Microsoft Entra 米国政府クラウド環境から利用することもできます。 このアプリケーションは、Microsoft Entra 米国政府クラウドのアプリケーション ギャラリーにあり、パブリック クラウドの場合と同じように構成できます。

### 手順 1: プロビジョニングの展開を計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Oracle Cloud Infrastructure Console の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID でのプロビジョニングをサポートするように Oracle Cloud Infrastructure Console を構成する

1. Oracle Cloud Infrastructure Console 管理ポータルにログオンします。 画面の左上隅で **[Identity](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ID) &gt; [Federation](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/フェデレーション)** に移動します。

    [Image: ID 管理用の Oracle 管理コンソールを示すスクリーンショット。]
2. Oracle Identity Cloud Service コンソールの横のページに表示される URL を選択します。
3. [ **ID プロバイダーの追加] を** 選択して、新しい ID プロバイダーを作成します。 テナント URL の一部として使用する IdP ID を保存します。 **[Applications](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーション)** タブの横にあるプラス記号アイコンを選んで OAuth クライアントを作成し、IDCS Identity Domain Administrator AppRole を付与します。

    [Image: アプリケーションを追加するための Oracle クラウド アイコンを示すスクリーンショット。]
4. 次のスクリーンショットに従って、アプリケーションを構成します。 構成が完了したら **[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存)** を選びます。

    [Image: Oracle の構成を示すスクリーンショット。]

    [Image: Oracle のトークン発行ポリシーを示すスクリーンショット。]
5. アプリケーションの構成タブで **[General Information](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般情報)** オプションを展開して、クライアント ID とクライアント シークレットを取得します。

    [Image: Oracle のトークンの生成を示すスクリーンショット。]
6. シークレット トークンを生成するには、クライアント ID とクライアント シークレットを **:** の形式で Base64 としてエンコードします。 注 - この値は、行の折り返しを無効にして生成する必要があります (base64 -w 0)。 シークレット トークンを保存します。 この値は、Oracle Cloud Infrastructure Console アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Oracle Cloud Infrastructure Console を追加する

Oracle Cloud Infrastructure Console へのプロビジョニングの管理を始めるには、Microsoft Entra アプリケーション ギャラリーから Oracle Cloud Infrastructure Console を追加します。 前に SSO 用に Oracle Cloud Infrastructure Console を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザー/グループの属性に基づいて、プロビジョニングされたユーザーのスコープを設定できます。 割り当てに基づいてアプリにプロビジョニングされたユーザーのスコープを設定する場合は、次の [手順](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) を使用して、ユーザーとグループをアプリケーションに割り当てることができます。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされたユーザーのスコープを設定する場合は、 [ここで](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)説明するようにスコープ フィルターを使用できます。

- 追加のロールが必要な場合は、[アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)して新しいロールを追加できます。
- 追加のロールが必要な場合は、[アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)して新しいロールを追加できます。

### 手順 5: Oracle Cloud Infrastructure Console への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、TestApp でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Oracle Cloud Infrastructure Console に対する自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示すスクリーンショット。]
3. アプリケーションの一覧で **[Oracle Cloud Infrastructure Console]** を選択します。

    [Image: アプリケーション一覧での Oracle Cloud Infrastructure Console のリンクを示すスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Oracle Cloud Infrastructure Console のテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Oracle Cloud Infrastructure Console に接続できることを確認します。 接続に失敗した場合は、Oracle Cloud Infrastructure Console アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    注

    `https://<IdP ID>.identity.oraclecloud.com/admin/v1` に「」と入力します。 例: IdP ID が`idcs-0bfd023ff2xx4a98a760fa2c31k92b1d`場合は、`https://idcs-0bfd023ff2xx4a98a760fa2c31k92b1d.identity.oraclecloud.com/admin/v1` に「」と入力します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Oracle Cloud Infrastructure Console に同期されるユーザーの属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/一致する)** プロパティとして選択されている属性は、更新処理で Oracle Cloud Infrastructure Console のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Oracle Cloud Infrastructure Console API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | ユーザー名 | 糸 |
    | 活動中 | ブール値 |
    | タイトル | 糸 |
    | emails[type eq "work"].value | 糸 |
    | 優先言語 | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | addresses[type eq "work"].formatted | 糸 |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |
    | addresses[type eq "work"].region | 糸 |
    | addresses[type eq "work"].postalCode | 糸 |
    | addresses[type eq "work"].country | 糸 |
    | addresses[type eq "work"].streetAddress | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |
    | urn:ietf:params:scim:schemas:oracle:idcs:extension:user:User:bypassNotification | ブール値 |
    | urn:ietf:params:scim:schemas:oracle:idcs:extension:user:User:isFederatedUser | ブール値 |

    注

    Oracle Cloud Infrastructure Console の SCIM エンドポイントでは、`addresses[type eq "work"].country` は ISO 3166-1 "alpha-2" コード形式であると想定されます (例: US、UK)。 プロビジョニングを開始する前に、すべてのユーザーがそれぞれの "国または地域" フィールドの値を想定される形式で設定していることを確認してください。そうでない場合、その特定のユーザー プロビジョニングが失敗します。 [Image: 連絡先情報を示すスクリーンショット。]

    注

    拡張属性 "urn:ietf:params:scim:schemas:oracle:idcs:extension:user:User:bypassNotification" と "urn:ietf:params:scim:schemas:oracle:idcs:extension:user:User:isFederatedUser" は、その形式でサポートされている唯一のカスタム拡張機能属性です。 追加の拡張属性は、urn:ietf:params:scim:schemas:extension:CustomExtensionName:2.0:User:CustomAttribute の形式に従う必要があります。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Oracle クラウド インフラストラクチャに同期されているグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/一致する)** プロパティとして選択されている属性は、更新処理で Oracle Cloud Infrastructure Console のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | externalId | 糸 |
    | members | リファレンス |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

- [プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)を使用して、正常にプロビジョニングされたユーザーと失敗したユーザーを特定します。
- [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
- プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)を参照してください。

### 更新履歴

2023 年 8 月 15 日 - アプリが Gov Cloud に追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oracle-cloud-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Oracle Cloud Infrastructure Console を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Oracle Cloud Infrastructure Console の間でシングル サインオンを構成する方法について学習します。

この記事では、Oracle Cloud Infrastructure Console と Microsoft Entra ID を統合する方法について説明します。 Oracle Cloud Infrastructure Console と Microsoft Entra ID を統合すると、次のことが可能になります。

- Oracle Cloud Infrastructure Console にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Oracle Cloud Infrastructure Console に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Oracle Cloud Infrastructure Console は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- Oracle Cloud Infrastructure Console でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Oracle Cloud Infrastructure Console では、**SP** Initiated SSO がサポートされます。
- Oracle Cloud Infrastructure Console では、[**自動化された**ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-cloud-infrastructure-console-provisioning-tutorial) (推奨) がサポートされます。

### ギャラリーからの Oracle Cloud Infrastructure Console の追加

Microsoft Entra ID への Oracle Cloud Infrastructure Console の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Oracle Cloud Infrastructure Console を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Oracle Cloud Infrastructure Console**」と入力します。
4. 結果のパネルから **[Oracle Cloud Infrastructure Console]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO の構成とテスト

**B. Simon** というテスト ユーザーを使用して、Oracle Cloud Infrastructure Console に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Oracle Cloud Infrastructure Console の関連ユーザーとの間にリンク関係を確立する必要があります。

Oracle Cloud Infrastructure Console に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**して、ユーザーがこの機能を使用できるようにします。
    1. B. Simon で Microsoft Entra のシングル サインオンをテストする Microsoft **Entra テスト ユーザーを作成**します。
    2. **Microsoft Entra テスト ユーザーを割り当てて** 、B. Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Oracle Cloud Infrastructure Console SSO の構成**- アプリケーション側で SSO 設定を構成します。
    1. **Oracle Cloud Infrastructure Console のテスト ユーザーを作成**して、Oracle Cloud Infrastructure Console 上で B. Simon の対応ユーザーとして、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Oracle Cloud Infrastructure Console** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    注

    サービス プロバイダー メタデータ ファイルは、記事の **「Oracle Cloud Infrastructure Console のシングル サインオンの構成** 」セクションから取得します。

    1. [ **メタデータ ファイルのアップロード]** を選択します。
    2. **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。
    3. メタデータ ファイルが正常にアップロードされると、**識別子**と**応答 URL** の値が、 **[基本的な SAML 構成]** セクションのテキストボックスに自動的に設定されます。

        注

        **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。

        [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cloud.oracle.com/?region=<REGIONNAME>`

        注

        これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[Oracle Cloud Infrastructure Console のクライアント サポート チーム](https://www.oracle.com/support/advanced-customer-services/cloud/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. Oracle Cloud Infrastructure Console アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 **[編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: image1]
8. その他に、Oracle Cloud Infrastructure Console アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [**グループ要求 (プレビュー)]** ダイアログの [**ユーザー属性と要求**] セクションで、次の手順を実行します。

    1. **[名前識別子の値**] の横にある**ペン**を選択します。
    2. **[名前識別子の形式の選択 ]** として **[Persistent]** を選択します。
    3. **保存** を選択します。

        [Image: image2 を示すスクリーンショット]

        [Image: image3 を示すスクリーンショット]
    4. **要求で返されるグループ**の横にある**ペン**を選択します。
    5. ラジオ ボタンのリストから **[セキュリティ グループ]** を選択します。
    6. **[グループ ID]** の **[ソース属性]** を選択します。
    7. **グループ クレームの名前のカスタマイズ** を選択します。
    8. **[名前]** ボックスに「**groupName**」と入力します。
    9. **[名前空間 (省略可能)]** ボックスに「`https://auth.oraclecloud.com/saml/claims`」と入力します。
    10. **保存** を選択します。

        [Image: image4 を示すスクリーンショット]
9. **[Set up Oracle Cloud Infrastructure Console](Oracle Cloud Infrastructure Console の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Oracle Cloud Infrastructure Console SSO の構成

1. 別の Web ブラウザー ウィンドウで、Oracle Cloud Infrastructure Console に管理者としてサインインします。
2. メニューの左側を選択し、[ **ID** ] を選択し、[ **フェデレーション**] に移動します。

    [Image: Configuration1 を示すスクリーンショット]
3. [**このドキュメントのダウンロード**] リンクを選択して**サービス プロバイダーのメタデータ ファイル**を保存し、Azure portal の **[基本的な SAML 構成]** セクションにアップロードし、[**Id プロバイダーの追加**] を選択します。

    [Image: Configuration2 を示すスクリーンショット]
4. **[ID プロバイダーの追加]** ポップアップで、次の手順に従います。

    [Image: Configuration3 を示すスクリーンショット]

    1. **[NAME](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前)** ボックスに自分の名前を入力します。
    2. **[DESCRIPTION](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/説明)** ボックスに説明を入力します。
    3. **[MICROSOFT ACTIVE DIRECTORY FEDERATION SERVICE (ADFS) または SAML 2.0 に準拠している ID プロバイダー](MICROSOFT ACTIVE DIRECTORY FEDERATION SERVICE (ADFS) または SAML 2.0 に準拠している ID プロバイダー)** を **[TYPE](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/種類)** として選択します。
    4. [ **参照** ] を選択して、以前にダウンロードしたフェデレーション メタデータ XML をアップロードします。
    5. [ **続行]** を選択し、[ **ID プロバイダーの編集** ] セクションで次の手順を実行します。

        [Image: Configuration4 を示すスクリーンショット]
    6. **ID プロバイダー グループ**は、Microsoft Entra グループのオブジェクト ID として選択する必要があります。 [グループ ID] は Microsoft Entra ID のグループの GUID である必要があります。 このグループを、 **[OCI GROUP](OCI グループ)** フィールド内の対応するグループとマップする必要があります。
    7. Azure portal での設定と組織のニーズに応じて複数のグループをマップできます。 **[+ マッピングの追加]** を選択して、必要な数のグループを追加します。
    8. **送信**を選択します。

#### Oracle Cloud Infrastructure Console のテスト ユーザーの作成

Oracle Cloud Infrastructure Console では、Just-In-Time プロビジョニングがサポートされています。この設定は、既定で有効になっています。 このセクションにはアクション項目はありません。 アクセスの試行中に新しいユーザーが作成されることはありません。また、ユーザーを作成する必要もありません。

### SSO のテスト

マイ アプリで [Oracle Cloud Infrastructure Console] タイルを選択すると、Oracle Cloud Infrastructure Console のサインイン ページにリダイレクトされます。 ドロップダウン メニューから **[IDENTITY PROVIDER** ] を選択し、次に示すように **[続行** ] を選択してサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。

[Image: 構成を示すスクリーンショット]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oracle-fusion-erp-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Oracle Fusion ERP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-fusion-erp-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Oracle Fusion ERP に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について学習します。

この記事の目的は、Oracle Fusion ERP と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Oracle Fusion ERP に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Oracle Fusion ERP テナント](https://www.oracle.com/applications/erp/)。
- Admin アクセス許可がある Oracle Fusion ERP のユーザー アカウント。

### Oracle Fusion ERP にユーザーを割り当てる

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に割り当てという概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Oracle Fusion ERP へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 特定した後、次の手順に従い、これらのユーザー、グループ、またはその両方を Oracle Fusion ERP に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Oracle Fusion ERP に割り当てるときの重要なヒント

- 1 人の Microsoft Entra ユーザーを Oracle Fusion ERP に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Oracle Fusion ERP にユーザーを割り当てるときは、割り当てダイアログで、有効なアプリケーション固有ロール (使用可能な場合) を選択する必要があります。 既定のアクセス ロールのユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために Oracle Fusion ERP を設定する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Oracle Fusion ERP を構成する前に、Oracle Fusion ERP で SCIM プロビジョニングを有効にする必要があります。

1. [Oracle Fusion ERP 管理コンソール](https://cloud.oracle.com/sign-in)にサインインします。
2. 左上隅にあるナビゲーターを選択します。 **[Tools](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ツール)** で **[Security Console](セキュリティ コンソール)** を選択します。

    [Image: Oracle Fusion E R P 管理コンソールの [Navigator](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ナビゲーター) ページのスクリーンショット。[Tools](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ツール) と [Security Console](セキュリティ コンソール) が強調表示されています。]
3. **[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** に移動します。

    [Image: Oracle Fusion E R P 管理コンソールのパネルのスクリーンショット。[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) 項目が強調表示されています。]
4. Oracle Fusion ERP 管理コンソールへのログインに使用する管理者ユーザー アカウントのユーザー名とパスワードを保存します。 これらの値を Oracle Fusion ERP アプリケーションの [プロビジョニング] タブの **[管理ユーザー名]** フィールドと **[パスワード]** フィールドに入力する必要があります。

### ギャラリーからの Oracle Fusion ERP の追加

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Oracle Fusion ERP を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Oracle Fusion ERP を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Oracle Fusion ERP を追加するには、次の手順を行います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、「**Oracle Fusion ERP**」と入力して結果パネルから **[Oracle Fusion ERP]** を選びます。

    [Image: 結果リストの Oracle Fusion ERP]

### Oracle Fusion ERP への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループ割り当てに基づいて、Oracle Fusion ERP でユーザーまたはグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Oracle Fusion ERP のシングル サインオンに関する記事に記載されている手順に従って、Oracle Fusion ERP に対して SAML ベース [のシングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-fusion-erp-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

注

Oracle Fusion ERP の SCIM エンドポイントについて詳しくは、「[Oracle アプリケーション クラウドの一般的な機能の REST API](https://docs.oracle.com/en/cloud/saas/applications-common/23b/farca/index.html)」をご覧ください。

#### Microsoft Entra ID で Fuze の自動ユーザー プロビジョニングを構成するには、次の操作を行います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **[Oracle Fusion ERP]** を選択します。

    [Image: アプリケーションの一覧の [Oracle Fusion ERP] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Oracle Fusion ERP テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Oracle Fusion ERP に接続できることを確認します。 接続に失敗した場合は、Oracle Fusion ERP アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    注

    `https://ejlv.fa.em2.oraclecloud.com/hcmRestApi/scim/` に「」と入力します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]

    [Image: [管理者資格情報] セクションのスクリーンショット。[テスト接続] ボタンのほか、[テナントの U R L]、[管理者ユーザー名]、[管理者パスワード] の各フィールドが表示されています。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Oracle Fusion ERP に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Oracle Fusion ERP のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Oracle Fusion ERP API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Oracle Fusion ERP で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 |  |  |
    | displayName | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | 活動中 | ブール値 |  |  |
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Oracle Fusion ERP に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新操作で Oracle Fusion ERP のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Oracle Fusion ERP で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### コネクタの制限事項

- Oracle Fusion ERP では、SCIM エンドポイントに対して基本認証のみがサポートされています。
- Oracle Fusion ERP では、グループ のプロビジョニングはサポートされていません。
- Oracle Fusion ERP のロールは、Microsoft Entra ID のグループにマップされます。 Microsoft Entra ID から Oracle Fusion ERP のユーザーにロールを割り当てるには、Oracle Fusion ERP のロールに名前が付けられた目的の Microsoft Entra グループにユーザーを割り当てる必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oracle-fusion-erp-tutorial"} -->
## Microsoft Entra ID で Oracle Fusion ERP for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-fusion-erp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Oracle Fusion ERP の間のシングル サインオンを構成する方法について説明します。

この記事では、Oracle Fusion ERP と Microsoft Entra ID を統合する方法について説明します。 Oracle Fusion ERP を Microsoft Entra ID と統合すると、次のことができます。

- Oracle Fusion ERP にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Oracle Fusion ERP に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Oracle Fusion ERP でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Oracle Fusion ERP では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Oracle Fusion ERP では、[**自動化された**ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-fusion-erp-provisioning-tutorial) (推奨) がサポートされます。

### ギャラリーからの Oracle Fusion ERP の追加

Microsoft Entra ID への Oracle Fusion ERP の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Oracle Fusion ERP を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Oracle Fusion ERP**」と入力します。
4. 結果パネルで **[Oracle Fusion ERP]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Oracle Fusion ERP 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Oracle Fusion ERP に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Oracle Fusion ERP の関連ユーザーとの間にリンク関係を確立する必要があります。

Oracle Fusion ERP に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO の構成**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Oracle Fusion ERP SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Oracle Fusion ERP テストユーザーを作成する** - Microsoft Entra での B.Simon の表現にリンクするために、Oracle Fusion ERP 内で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Oracle Fusion ERP** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    a. **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<SUBDOMAIN>.login.em2.oraclecloud.com:443/oam/fed`

    b。 **[応答 URL]** ボックスに、`https://<SUBDOMAIN>.login.em2.oraclecloud.com:443/oam/fed` のパターンを使用して URL を入力します
6. アプリケーションを **SP** 開始モードで構成する場合は、**[追加の URL を設定します]** を選択して次の手順を実行します。 これは省略可能です。

    **[サインオン URL]** ボックスに、`https://<SUBDOMAIN>.fa.em2.oraclecloud.com/fscmUI/faces/AtkHomePageWelcome` という形式で URL を入力します。

    Note

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには、[Oracle Fusion ERP クライアント サポート チーム](https://www.oracle.com/applications/erp/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[Oracle Fusion ERP のセットアップ]** セクションで、要件に基づいて 1 つ以上の適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Oracle Fusion ERP SSO の構成

**Oracle Fusion ERP** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Oracle Fusion ERP サポート チーム](https://www.oracle.com/applications/erp/)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Oracle Fusion ERP のテスト ユーザーの作成

このセクションでは、Oracle Fusion ERP で Britta Simon というユーザーを作成します。 [Oracle Fusion ERP サポート チーム](https://www.oracle.com/applications/erp/)と連携して、Oracle Fusion ERP プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは Oracle Fusion ERP のサインオン URL にリダイレクトされ、そこでサインイン フローを開始できます。
- Oracle Fusion ERP のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Oracle Fusion ERP に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Oracle Fusion ERP] タイルを線t買うすると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Oracle Fusion ERP に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oracle-hcm-provisioning-tutorial"} -->
## 自動ユーザー プロビジョニング用に Oracle Human Capital Management (HCM) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-hcm-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-09-13
- Summary: 受信プロビジョニング API を使用した Oracle Fusion Cloud Human Capital Management (HCM) と Microsoft Entra ID およびオンプレミス Active Directory の統合。

Inbound Provisioning API は、Oracle Fusion Cloud Human Capital Management (HCM) などの外部ソースから、Microsoft Entra ID とオンプレミス Active Directory のユーザーを作成、更新、削除できる機能です。 この機能により、組織は生産性を向上し、セキュリティを強化し、コンプライアンスと規制の要件をより簡単に満たすことができます。

[Microsoft Entra ID Governance](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview) を使用すると、適切なリソースへの適切なアクセス権を適切なユーザーに自動的に付与することができます。 このアクセスには、ID およびアクセス管理プロセスの自動化、ビジネス グループへの委任、可視性の向上が含まれます。

この記事では、API 駆動型プロビジョニングを使用して Oracle HCM と Microsoft Entra ID を統合するための手順とベスト プラクティスについて説明します。次の方法について説明します。

- 環境を準備し、API 設定を構成する
- Oracle HCM からワーカー データを CSV 形式でエクスポートし、Microsoft スクリプトを使用してクロスドメイン ID 管理システム (SCIM) 形式に変換する
- PowerShell または Azure Logic Apps を使用してワーカー データをインバウンド プロビジョニング API に送信する
- Oracle Atom フィード API または HCM 抽出を使用して、差分同期を実行してワーカー データを最新の状態に維持する
- Oracle HCM SCIM API を使用して Microsoft Entra ID から Oracle HCM への書き戻しを構成する (必要な場合)

### 用語

- [Oracle Cloud HCM (oracle.com)](https://go.oracle.com/LP=139597?src1=:ad:pas:bi:dg:a_nas:l5:RC_MSFT220512P00060C01584:MainAd&amp;gclid=9c09cb5c768b188a186aaea4b3735c3e&amp;gclsrc=3p.ds&amp;msclkid=9c09cb5c768b188a186aaea4b3735c3e): このガイドでは、Oracle Fusion Cloud HCM から Microsoft Entra ID に統合する方法に特に焦点を当てています。 PeopleSoft や Taleo などの他の Oracle オファリングは、この記事の対象ではありません。

### 前提条件

インバウンド プロビジョニング API を使用して Oracle HCM と Microsoft Entra ID の統合を開始する前に、次の前提条件が用意されていることを確認する必要があります。

- 次の特権を持つ Oracle HCM (oracle.com) アカウント:

    - HCM データの表示とエクスポート。
    - Oracle HCM REST API へのアクセス。 この記事では、 [人事 24A (oracle.com)](https://docs.oracle.com/en/cloud/saas/human-resources/24a/farws/rest-endpoints.html) を参照しました。 と [Applications Common 24A (oracle.com)](https://docs.oracle.com/en/cloud/saas/applications-common/24a/farca/rest-endpoints.html) を参照しています。
- Microsoft Entra ID P1 /  / 以上の [Microsoft Entra ID](https://www.microsoft.com/microsoft-365/enterprise/e3) テナント。 これらのライセンスを使用すると、新しい API 駆動型プロビジョニング機能を使用できます。

    - プロビジョニング エージェントをインストールするには (ハイブリッド ユーザーのみ)、AD ドメインに接続されている Microsoft Windows サーバーにアクセスする必要があります。
    - ギャラリー アプリおよびプロビジョニング ジョブを作成するには、[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)と[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)のロールを持つ Microsoft Entra が必要です。
    - [Microsoft Entra ID Governance ライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals): ライフサイクル ワークフローを構成するには、このアドオン ライセンスが必要です。
    - [Azure サブスクリプション: Azure](https://azure.microsoft.com) Logic Apps を使用する予定の場合は、サブスクリプションが必要です。

### 統合の概要

HR 統合の設定に使用できる主な同期シナリオには、次の 3 つがあります。 このセクションの図は、これらの同期シナリオを示しています。 このガイドは、3 つのセクションに分けて編成されています。 ワークフローごとに、ワークフローの構成方法に関する推奨事項が提供されています。

- **初期または完全同期**は、2 つのシステム間ですべてのワーカー データを同期するプロセスです。この場合は、Oracle HCM と Microsoft Entra ID 間で直接同期するか、Oracle HCM をオンプレミスの Active Directory (オンプレミスの AD) に同期します。 このプロセスには、すべてのワーカーの ID と属性 (個人情報、連絡先情報、雇用情報など) が含まれます。 完全同期は通常、両方のシステムですべてのワーカー データの一貫性と最新状態を確保するために、最初の統合セットアップで実行されます。
- **差分同期**は、Oracle HCM との最後の同期以降に発生した変更または更新のみを同期するプロセスです。 差分同期は通常、ソース システムで発生する変更に対応してワーカー データの最新状態を維持するために、最初の完全同期後に実行されます。 このプロセスには、新しい従業員、更新された従業員データ、または削除された従業員が含まれます。 差分同期は増分更新であり、ワーカー データが変更されるたびに、完全同期よりも高速かつ効率的に実行されます。
- **書き戻し**は、Microsoft Entra ID で発生したユーザー属性 (ユーザー名、メール アドレス、電話番号など) の変更を Oracle HCM に返送するオプションのプロセスです。

[Image: Oracle HCM 駆動型プロビジョニングと書き戻しの図。]

### 統合手順

| **#** | **ステップ** | **操作内容** | **関与するユーザー** |
| --- | --- | --- | --- |
| 1. | HCM からプロビジョニングする属性セットを決定する | • この HCM 属性の一覧を参照して、Microsoft Entra ID にエクスポートする属性を決定する • Oracle HCM 属性を SCIM 属性にマップする • 一意の ID の生成規則と変換規則を定義する | Oracle HCM 管理者および IT 管理者 |
| 2. | インバウンド プロビジョニング API にアクセス許可を付与する | [API クライアントを表すアプリケーションを作成し、インバウンド プロビジョニング エンドポイントにデータを送信するアクセス許可を付与する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-grant-access) | IT 管理者 - 特権ロール管理者 |
| 3. | プロビジョニングのターゲットを決定する: クラウド専用 ID を Microsoft Entra ID にプロビジョニングするか、またはハイブリッド ID をオンプレミス AD にプロビジョニングするか | ターゲットが Microsoft Entra ID の場合 (クラウド専用 ID プロビジョニング):  • ギャラリー アプリを構成する • SCIM を Microsoft Entra の属性にマップする ターゲットがオンプレミスの AD の場合 (ハイブリッド ID プロビジョニング):  • プロビジョニング エージェントをダウンロードして構成する • ギャラリー アプリを構成する • SCIM 属性をオンプレミスの Active Directory にマップする • Microsoft Entra Connect 同期とクラウド同期のマッピングを更新して、新しい HR 属性を Entra ID にフローする | プロビジョニング エージェントのインストールには、Windows 管理者が関与する  ギャラリー アプリの構成には、アプリケーション管理者特権を持つ管理者が参加する |
| 4. | 初期同期を実行してデータの全範囲をプロビジョニング エンドポイントに送信する | • 初期同期の準備を行う • CSV エクスポートを実行し、データを API に送信する • 適切なワーカーが一致しており、Microsoft Entra/AD に存在することを検証する | IT 管理者 |
| 5 | 差分同期を実行して Microsoft Entra ID 内のデータを最新の状態に保つ | 次のいずれかの方法を使用します。 • CSV 抽出を使用する • Atom フィード API を使用する | IT 管理者 |
| 6. | データを Oracle HCM に書き戻す | • 書き戻しプロビジョニング ジョブを構成して実行する | IT 管理者 |
| 7. | *推奨事項*: Microsoft Entra のライフサイクル ワークフローを構成する | • [Microsoft Entra を使用して、就職者、異動者、退職者の各プロセスを自動化する](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) • [ガバナンス ライセンスが必要](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview) | IT 管理者 |

### ギャラリー アプリケーションを構成する

Microsoft Entra でプロビジョニング ジョブを構成する前に、プロビジョニングのターゲットがオンプレミスの AD または Microsoft Entra ID のいずれであるかを判断する必要があります。 オンプレミスの依存関係を使用してハイブリッド ユーザーをプロビジョニングする場合、AD がターゲットになります。 ユーザーがクラウド専用の場合、Microsoft Entra ID に直接プロビジョニングできます。

#### クラウド専用ユーザーの場合

次の手順に従って、**Microsoft Entra ID への API 駆動型プロビジョニング** ギャラリー アプリケーションを作成して構成します。

1. [ギャラリー アプリケーションを作成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app#create-your-api-driven-provisioning-app)し、アプリケーションに **Oracle HCM Cloud から Entra ID へのプロビジョニング**という名前を付けます。

    [Image: Microsoft Entra ID への API 駆動型プロビジョニングの図。]
2. [アプリケーションを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app#configure-api-driven-inbound-provisioning-to-microsoft-entra-id)。

#### ハイブリッド ユーザーの場合

Windows 管理者と連携して、ドメイン参加済みの Windows サーバーにプロビジョニング エージェントをインストールし、次の手順に従います。

1. [プロビジョニング エージェントをインストールします](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-install)。
2. [ギャラリー アプリケーションを作成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app#create-your-api-driven-provisioning-app)し、アプリケーションに **Oracle HCM Cloud からオンプレミスの Active Directory へのプロビジョニング**という名前を付けます。

    [Image: オンプレミスの Active Directory への API 駆動型プロビジョニングの図。]
3. [アプリケーションを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app#configure-api-driven-inbound-provisioning-to-on-premises-ad)。

### 初期同期の準備を行う

初期同期ペイロードを送信する前に、データが Microsoft Entra と適切に同期できるように準備されていることを確認する必要があります。 次の手順は、スムーズな統合を確保するために役立ちます。

1. [一致する識別子](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#matching-users-in-the-source-and-target--systems)の存在と一意性: プロビジョニング サービスでは、一致する属性を使用して、Oracle システム内のワーカー レコードを一意に識別し、AD または Microsoft Entra ID 内の対応するユーザー アカウントとリンクします。 既定の一致する属性ペアは、Microsoft Entra ID またはオンプレミスの AD 内の従業員 ID 属性にマップされた Oracle HCM 内の個人番号です。 従業員 ID はユーザーを一意に識別するため、完全同期を開始する前に、その値が Microsoft Entra ID (クラウド専用ユーザーの場合) およびオンプレミスの AD (ハイブリッド ユーザーの場合) に設定されていることを確認します。
2. [スコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用して、関連性がなくなった HR レコードをスキップする: HR システムには、おそらく 1970 年代まで遡った数年間分の雇用データが保存されています。 一方、IT チームが関心を持つのは、現在アクティブな従業員の一覧と、稼働後に発生する退職レコードのみである可能性があります。 IT チームの観点から関連性がなくなった HR レコードを除外するには、Microsoft Entra で構成できるスコープ フィルター規則を特定します。

### 初期同期のための CSV エクスポート

この手順では、ワーカー データを Oracle HCM から CSV 形式でエクスポートし、Microsoft CSV から SCIM スクリプトを使用して、データを SCIM 形式に変換します。 この手順を使用すると、インバウンド プロビジョニング API で理解して処理できる標準ベースのペイロードで、ワーカー データを API に送信できます。

エクスポートする Oracle HCM ワーカー属性の一覧を Oracle HCM 管理者と共有します。 ワーカー データを Oracle HCM から CSV 形式でエクスポートするために、Oracle では複数のオプションを提供しています。

- **HCM 抽出ツール**: Oracle HCM Cloud からデータを一括で取得するための主要な方法は、HCM 抽出を使用することです。これは、データ ファイルとレポートを生成するためのツールです。 HCM 抽出には、抽出するレコードと属性を指定するための専用のインターフェイスがあります。 このツールを使用すると、次のことができます。

    - 複雑な選択基準を使用して抽出するレコードを識別する
    - 高速の数式データベース項目と規則を使用して、HCM 抽出のデータ要素を定義する

注

HCM 抽出の作成を開始するには、「[抽出の定義](https://www.oracle.com/webfolder/technetwork/tutorials/obe/hcm_extract/extract_obe_ptrtrn/extract_index.html)」 (oracle.com) を参照してください。

- **Oracle BI Publisher**: 事前定義済みの Oracle Transactional Business Intelligence 分析構造またはユーザー独自のデータ モデルに基づいて、スケジュール済みのレポートと計画外のレポートの両方がサポートされています。 さまざまな形式でレポートを生成できます。 アウトバウンド統合に Oracle BI Publisher を使用するには、XML や CSV などの自動ダウンストリーム処理に適した形式でレポートを生成します。 BI Publisher レポートの作成を開始するには、「[HCM 抽出での BI パブリッシャ テンプレートの定義](https://docs.oracle.com/en/cloud/saas/human-resources/24a/fahex/define-the-bi-publisher-template-in-hcm-extracts.html#s20043805)」 (oracle.com) を参照してください。
- **Oracle Integration Cloud (OIC) サービス**: OIC のサブスクリプションがある場合、[Oracle HCM アダプタ (oracle.com)](https://docs.oracle.com/en/cloud/paas/integration-cloud/hcm-adapter/understand-oracle-hcm-cloud-adapter.html#GUID-40A15882-F8D1-452E-9E9C-1B184616E1A8) との統合を構成して、必要なデータを Oracle HCM から抽出できます。 Oracle では、作業を開始するために使用できる[ガイド (oracle.com)](https://docs.oracle.com/en/cloud/paas/integration-cloud/int-get-started/export-employee-data-oracle-hcm-cloud-identity-management-system.html#GUID-DE0A58BC-25F1-4013-A87C-E4A0123A94EE) を提供しています。

注

Oracle HCM 管理者と連携して、必要な属性を CSV ファイルにエクスポートしてください。

ワーカー データを CSV ファイルにエクスポートしたら、ペイロードを受け入れ可能な形式にするために、CSV を SCIM 形式に変換する必要があります。 PowerShell と Azure Logic Apps の 2 つの方法で CSV を SCIM ペイロードに変換する方法に関するドキュメントとサンプル コードを提供しています。

それぞれの方法でこの変換を実行するためのリンクを次に示します。

- **PowerShell**: [PowerShell スクリプトを使用した API 駆動型インバウンド プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-powershell)
- **Azure Logic Apps**: [Azure Logic Apps を使用した API 駆動型インバウンド プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-logic-apps)

インバウンド プロビジョニング プロセスには、プロビジョニング ペイロードの送信が含まれます。 ペイロードを送信する前に、Microsoft Entra 管理センターで **[プロビジョニングの開始]** を選択して、プロビジョニング ジョブが新しい要求をリッスンしていることを確認します。 処理のためにファイル全体を送信する前に、5 から 10 件のレコードを送信して、ワーカーと属性が正しく一致することを検証します。 ペイロードが送信されると、Microsoft Entra テナントまたはオンプレミスの AD にユーザーが短時間表示されます。

### 差分同期

初期同期のためにワーカー データをインバウンド プロビジョニング API に送信した後、ワーカー データを最新の状態に保つために差分同期を実行する必要があります。 差分同期は増分更新であり、新しいワーカー、更新されたワーカー、削除されたワーカーなど、最後の同期以降に発生した変更のみが送信されます。

差分同期を実行するには、次の 3 つのオプションがあります。

**オプション 1**: Oracle Atom フィード API を使用して、Oracle HCM でのワーカーの変更に関するリアルタイム通知を取得し、それをインバウンド プロビジョニング API に送信します。

**オプション 2**: CSV 抽出を使用して、Oracle HCM でのワーカーの変更に関する定期レポートを生成し、独自の自動化ツールまたは Logic Apps を使用して抽出をインバウンド プロビジョニング API に送信します。

**オプション 3**: [Oracle Integration Cloud サービス (oracle.com)](https://docs.oracle.com/en/cloud/paas/application-integration/) を使用します。 OIC のサブスクリプションがある場合、[Oracle HCM アダプタ (oracle.com)](https://docs.oracle.com/en/cloud/paas/integration-cloud/hcm-adapter/understand-oracle-hcm-cloud-adapter.html#GUID-40A15882-F8D1-452E-9E9C-1B184616E1A8) との統合を構成して、必要なデータを Oracle HCM から抽出できます。 Oracle では、作業を開始するために使用できる[ガイド (oracle.com)](https://docs.oracle.com/en/cloud/paas/integration-cloud/int-get-started/export-employee-data-oracle-hcm-cloud-identity-management-system.html#GUID-DE0A58BC-25F1-4013-A87C-E4A0123A94EE) を提供しています。

#### オプション 1: Oracle Atom フィード API を使用する

Oracle Atom フィード API は、Oracle HCM でのワーカーの変更をリアルタイムで通知します。 Atom フィード API をサブスクライブし、変更されたワーカー データを含む属性の JSON 表現を受け取ることができます。 その後、サンプル PowerShell スクリプトまたは Logic Apps 統合を使用して、JSON を SCIM 形式に変換し、インバウンド プロビジョニング API に送信できます。

Atom フィード統合を使用する場合は、必ず初期同期の直後に Atom フィードを有効にしてください。この手順が遅れると、変更が失われる可能性があります。

Oracle の ATOM フィードの使用を開始するには、 [Oracle のドキュメント (oracle.com)](https://docs.oracle.com/en/cloud/saas/human-resources/24a/farws/Working_with_Atom.html) と [記事 (oracle.com)](https://docs.oracle.com/en/applications/fusion-apps/fusion-human-capital-management/hcmintegration/index.html#background) を参照してください。 [従業員ワークスペース (oracle.com)](https://docs.oracle.com/en/cloud/saas/human-resources/24a/farws/Employee_Atom_Feeds.html) にサブスクライブし、Atom フィード コレクション (newhire、empassignment、empupdate、termination、cancelworkrelship、workrelshipupdate) を適用することをお勧めします。

HCM テナントで Atom フィードを構成したら、Atom フィード API の出力を読み取り、インバウンド プロビジョニング API を使用してデータを SCIM ペイロード形式で Microsoft Entra ID に送信するカスタム モジュールを作成する必要があります。

カスタム モジュールのロジックでは、次のシナリオが処理されます。

- データ検証
- 一意の ID の生成
- Atom フィードのシーケンス処理
- SCIM ペイロードへの Atom フィードの変換
- エラー処理

このカスタム モジュールを構築するには、Oracle HCM パートナーまたは Microsoft システム インテグレーターを利用することをお勧めします。 このカスタム モジュールは、Oracle Integration Cloud などの Oracle ミドルウェアでホストするか、Azure 関数、Azure Logic Apps、または Azure Data Factory パイプラインとして Azure クラウドでホストすることができます。

**就職者シナリオを実装する**

就職者シナリオでは、特に新入社員のオンボード プロセスに対処します。 「[Fusion Cloud HCM と外部エンタイトルメント管理システムとの融合](https://docs.oracle.com/en/applications/fusion-apps/fusion-human-capital-management/hcmintegration/index.html#joiner)」 (oracle.com) で説明されているように、Oracle HCM Atom フィードは、就職者のデータを返します。

新入社員の Atom フィードからデータを読み取り、カスタム モジュールにロジックを実装して、個人データ、連絡先データ、雇用情報、業務情報などのデータ要素が SCIM ペイロードに確実に存在するようにします。

就職者シナリオ用の Atom フィードを取得した後に必要になった場合は、[ワーカー](https://docs.oracle.com/en/cloud/saas/human-resources/24a/farws/op-workers-workersuniqid-get.html)または[従業員](https://docs.oracle.com/en/cloud/saas/human-resources/24a/farws/api-employees.html)エンドポイントのクエリを実行して、追加のワーカー属性を取得します。

新入社員に対して Microsoft Entra ライフサイクル ワークフローをトリガーするには、その従業員の入社日のカスタム SCIM 属性 (`urn:ietf:params:scim:schemas:extension:COMPANYNAME:1.0:User:HireDate`) を必ず含めます。

Oracle HCM の *EffectiveStartDate* フィールドを使用して、入社日の値を設定します。 「SCIM ペイロードの例」を参照してください。

**異動者シナリオを実装する**

異動者シナリオは、従業員がフルタイムから契約社員に、またはその逆に転換されたとき、職務の変更が発生したとき、仕事上の関係の変更が発生したとき、異動があったとき、または昇進したときに、Oracle HCM でトリガーされます。 「[Fusion Cloud HCM と外部エンタイトルメント管理システムとの融合](https://docs.oracle.com/en/applications/fusion-apps/fusion-human-capital-management/hcmintegration/index.html#mover)」 (oracle.com) で説明されているように、Oracle HCM Atom フィードは、異動者のデータを返します。

Oracle HCM で変更された属性の新しい値を必ずフェッチします。 多くの場合、これらの値は、Atom フィード応答の **Changed Attributes** セクションからフェッチできます。 必要に応じて、[ワーカー](https://docs.oracle.com/en/cloud/saas/human-resources/24a/farws/op-workers-workersuniqid-get.html)または[従業員](https://docs.oracle.com/en/cloud/saas/human-resources/24a/farws/api-employees.html)エンドポイントのクエリを直接実行して、追加のワーカー属性を取得します。

取得したデータを使用して、SCIM ペイロードを構築します。 「SCIM ペイロードの例」を参照してください。

**退職者シナリオを実装する**

退職者シナリオは、従業員が自発的または非自発的に組織での就労を終了したときに発生します。 「[Fusion Cloud HCM と外部エンタイトルメント管理システムとの融合](https://docs.oracle.com/en/applications/fusion-apps/fusion-human-capital-management/hcmintegration/index.html#leaver)」 (oracle.com) で説明されているように、Oracle HCM Atom フィードは、退職者のデータを返します。 Atom フィードからデータを読み取り、SCIM ペイロードを構築します。

退職者に対してライフサイクル ワークフローをトリガーするには、その従業員の退職日のカスタム SCIM 属性 (`urn:ietf:params:scim:schemas:extension:COMPANYAME:1.0:User:TermDate`) を必ず含めます。

Oracle HCM の *EffectiveDate* フィールドを使用して、終了日の値を設定します。 「SCIM ペイロードの例」を参照してください。

#### SCIM ペイロードの例

就職者、異動者、退職者の各シナリオに関連付けられた JSON ペイロードを変換して、Microsoft API 駆動型プロビジョニング エンドポイントに送信する SCIM ペイロードを作成します。

"Oracle HCM から SCIM" ワークシートに基づいて、Oracle HCM 属性が SCIM ペイロードの属性にどのようにマップされるかを示す一般的な例を次に示します。

```
{
"schemas": ["urn:ietf:params:scim:api:messages:2.0:BulkRequest"],  
"Operations": [  

{  

    "method": "POST",  
    "bulkId": "897401c2-2de4-4b87-a97f-c02de3bcfc61",  
    "path": "/Users",  
    "data": {  
        "schemas": ["urn:ietf:params:scim:schemas:core:2.0:User",  
        "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User"],  
        "externalId": "<Oracle HCM workers.PersonNumber>",  
        "userName": "<Oracle HCM employee.UserName>",  
        "name": {  
            "familyName": "<Oracle HCM workers.names.LastName>",  
            "givenName": "<Oracle HCM workers.names.FirstName> ",  
            "middleName": "<Oracle HCM workers.names.MiddleName>",  
               },  
        "displayName": "<Oracle HCM workers.DisplayName>",  
        "emails": [  
        {  
          "value": "<Oracle HCM workers.emails.EmailAddress> ",  
          "type": "work",  
          "primary": true  
        }  
        ],  
        "addresses": [  
        {  
          "type": "work",  
          "streetAddress": "<Oracle HCM workers.addresses.AddressLine1>",  
          "locality": "<Oracle HCM workers.addresses.TownorCity>",  
          "region": "<Oracle HCM workers.addresses.Region1>",  
          "postalCode": "<Oracle HCM workers addresses.PostalCode> ",  
          "country": "<Oracle HCM workers addresses.Country> ",  
          "primary": true  
        }  
        ],  
        "phoneNumbers": [  
        {  
          "value": "<Oracle HCM workers. phones.PhoneNumber ",  
          "type": "work"  
        }  
        ],  
        "userType": "<Oracle HCM workers.workRelationships.WorkerType ",  
        "title": " <Oracle HCM worker.workRelationships.assignments.JobName",  
         "active":true,  
        "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User": {  
             "employeeNumber": "<Oracle HCM workers.PersonNumber> ",  
             "division": "<Oracle HCM worker.workRelationships.assignments.BusinessUnitId> ",  
             "department": "<Oracle HCM worker.workRelationships.assignments.DepartmentId >",  
             "manager": {  
               "value": "<Oracle HCM worker.workRelationships.assignments.allReports.ManagerPersonNumber> ",  
                 "displayName": "<Oracle HCM worker.workRelationships.assignments.allReports.ManagerDisplayName"  
             }  
        }  
    }  
} 

],
"failOnErrors": null
}
```

[SCIM 一括要求](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-graph-explorer#bulk-request-with-scim-enterprise-user-schema)を書式設定したら、API 駆動型プロビジョニングを使用してデータを [bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API エンドポイントに送信できます。

統合を有効にする前に、手動テストと検証を実行し、SCIM 一括要求ペイロードの構造を検証します。 [cURL](https://go.microsoft.com/fwlink/?linkid=2281068) や [Graph エクスプローラー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-graph-explorer)などのツールを使用すると、一括要求のペイロードが想定どおりに処理されることを確認できます。

注

パートナーと連携したり、独自のカスタム モジュールを構築したりする必要がない場合は、次のセクションで説明する **HCM 抽出ツール**を使用することをお勧めします。

#### オプション 2: CSV 抽出を使用する

初期同期で使用した方法と同様に、CSV 抽出を使用して差分同期を処理することもできます。 前回の同期以降の新しい変更のみを実行するように抽出を構成できます。または、ワーカー データの全範囲を送信すると、Microsoft Entra ID プロビジョニング サービスで、新入社員、属性の変更、退職などの変更を管理および更新できます。

初期同期と同様に、CSV 抽出を取得するために使用できるオプションが複数あります。

- **HCM 抽出ツール**: Oracle HCM Cloud からデータを一括で取得するための主要な方法は、HCM 抽出を使用することです。これは、データ ファイルとレポートを生成するためのツールです。 HCM 抽出には、抽出するレコードと属性を指定するための専用のインターフェイスがあります。 このツールを使用すると、次のことができます。

    - 複雑な選択基準を使用して抽出するレコードを識別する。
    - 高速の数式データベース項目と規則を使用して、HCM 抽出のデータ要素を定義する。

        注

        HCM 抽出の作成を開始するには、「[抽出の定義](https://docs.oracle.com/en/cloud/saas/human-resources/24a/fahex/define-extracts.html#s20034537)」 (oracle.com) を参照してください。
- **Oracle BI Publisher**: Oracle BI Publisher では、事前定義済みの Oracle Transactional Business Intelligence 分析構造またはユーザー独自のデータ モデルに基づいて、スケジュール済みのレポートと計画外のレポートの両方がサポートされています。 さまざまな形式でレポートを生成できます。 アウトバウンド統合に Oracle BI Publisher を使用するには、XML や CSV などの自動ダウンストリーム処理に適した形式でレポートを生成します。 BI Publisher レポートの作成を開始するには、「[HCM 抽出での BI パブリッシャ テンプレートの定義](https://docs.oracle.com/en/cloud/saas/human-resources/24a/fahex/define-the-bi-publisher-template-in-hcm-extracts.html#s20043805)」 (oracle.com) を参照してください。
- **Oracle Integration Cloud (OIC) サービス**: OIC のサブスクリプションがある場合、[Oracle HCM アダプタ (oracle.com)](https://docs.oracle.com/en/cloud/paas/integration-cloud/hcm-adapter/understand-oracle-hcm-cloud-adapter.html#GUID-40A15882-F8D1-452E-9E9C-1B184616E1A8) との統合を構成して、必要なデータを Oracle HCM から抽出できます。 Oracle では、作業を開始するために使用できる[ガイド (oracle.com)](https://docs.oracle.com/en/cloud/paas/integration-cloud/int-get-started/export-employee-data-oracle-hcm-cloud-identity-management-system.html#GUID-DE0A58BC-25F1-4013-A87C-E4A0123A94EE) を提供しています。

    ワーカー データを CSV 形式で取得したら、次の 2 つの方法のいずれかを使用して、データを SCIM ペイロードに変換し、プロビジョニング サービスに送信します。
- **PowerShell**: [PowerShell スクリプトを使用した API 駆動型インバウンド プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-powershell)
- **Logic Apps**: [Azure Logic Apps を使用した API 駆動型インバウンド プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-logic-apps)

### Microsoft Entra ID から Oracle HCM に書き戻す

インバウンド プロビジョニング API を使用して Oracle HCM から Microsoft Entra ID/オンプレミスの Active Directory を同期した後、Microsoft Entra プロビジョニング サービスから Oracle HCM への書き戻しを構成できます。 書き戻しは、Entra ID で発生したユーザーの変更 (ユーザー名、メール アドレス、パスワードなどの変更) を Oracle HCM に送信するプロセスです。 このプロセスにより、両方のシステムでデータの一貫性と正確性が保証されます。

書き戻しを構成するには、Oracle HCM SCIM API を使用する必要があります。 [Oracle HCM SCIM API (oracle.com)](https://docs.oracle.com/en/cloud/saas/applications-common/24a/farca/Quick_Start.html) は、RESTful Web サービスであり、Entra などの外部ソースから Oracle HCM 内のユーザーの作成、更新、削除を行うことができます。 Microsoft Entra App Gallery の既存の Oracle Fusion ERP プロビジョニング コネクタを使用して、Oracle HCM SCIM API に接続し、書き戻すユーザー属性をマップできます。

書き戻しを設定するには、Oracle HCM テナントへのアウトバウンド プロビジョニング ジョブを構成する必要があります。 書き戻しを構成するには、次の情報が必要です。

- **管理者ユーザー名とパスワード:** Oracle HCM にアクセスでき、HCM [ユーザー更新 API](https://docs.oracle.com/en/cloud/saas/applications-common/25a/farca/index.html) を呼び出すことができる管理者アカウントの詳細が必要です。

Oracle Fusion ERP コネクタを使用して Oracle HCM への書き戻しジョブを構成するには、次の手順を実行します。

1. Microsoft Entra Enterprise アプリ ギャラリーで、Oracle Fusion ERP アプリを検索します。
2. Oracle Fusion ERP アプリを使用して書き戻しを構成するには、[Oracle Fusion ERP](https://go.microsoft.com/fwlink/?linkid=2286440) を参照してください。
3. URL と管理者のユーザー名およびパスワードの入力を求められたら、手順で指定された URL を使用し、Oracle HCM にアクセスでき、HCM [ユーザー更新 API](https://docs.oracle.com/en/cloud/saas/applications-common/25a/farca/index.html) を呼び出すことができるアカウントの管理者のユーザー名およびパスワードを入力します。
4. Oracle Fusion ERP に関する記事のガイダンスに従って属性マッピングを編集し、特定のユーザーを Oracle HCM にプロビジョニングし直します。
5. [属性マッピング] セクションの編集で、**[ターゲット オブジェクトのアクション]** の下にある **[更新]** 操作のみを選択します。
6. HCM 属性が属性マッピングセクションに自動的に入力されていることが分かります。 書き戻しを行わない属性を削除します。
7. 設定を保存し、プロビジョニングの状態を有効にします。
8. Entra のオンデマンド プロビジョニング機能を使用して、書き戻しの統合をテストおよび検証します。
9. ワークフローを検証したら、ジョブを開始し、Entra が継続的にデータを Oracle HCM に同期できるようにジョブを実行し続けます。

### 付録

#### ワークシート 1: Oracle HCM 属性

このセクションの表は、Oracle HCM からエクスポートできる属性を表しています。 これらの属性の名前は、使用する HCM システムによって異なる場合がありますが、この一覧は、HR 統合の一般的な属性の一覧を示しています。 統合用にエクスポートする属性を決定します。

| Oracle HCM 属性 (CSV ファイルから) | 必要または必須 |
| --- | --- |
| 個人番号 | 必須 |
| アカウントの状態 | 必須 -&gt;解雇されていないワーカーの場合は、値を *True* に設定します。 |
| 番地 |  |
| 都市 |  |
| 状態 |  |
| 郵便番号 |  |
| 国 |  |
| 部署名 |  |
| 区分 |  |
| 会社 |  |
| ユーザー名 |  |
| 名前 | 必須 |
| 姓 | 必須 |
| ジョブ コード |  |
| ジョブ名 |  |
| メール アドレス |  |
| 管理者 |  |
| 携帯電話番号 |  |
| 電話番号 |  |
| 勤務先住所 |  |
| 電話番号 |  |
| 採用日 | ライフサイクル ワークフローで必要 |
| 退職日 | ライフサイクル ワークフローで必要 |
|  |  |
|  |  |
|  |  |

注

この一覧にない他の属性を追加してプロビジョニング ジョブに含めることができるように、上記のワークシートに空白行を含めました。

#### ワークシート 2: Oracle HCM から SCIM への属性マッピング

このセクションの表は、Oracle HCM 属性から API でサポートされる汎用 SCIM 属性へのサンプル マッピングを示しています。

| Oracle HCM 属性 (CSV ファイルから) | SCIM 属性 |
| --- | --- |
| 個人番号 | 外部ID |
| アカウントの状態 | アクティブです |
| 番地 | アドレス[タイプ eq "作業"].ストリートアドレス |
| 都市 | アドレス[タイプ eq "職場"].ローカリティ |
| 状態 | アドレス[タイプが"仕事"に等しい].地域 |
| 郵便番号 | addresses[タイプ eq "work"].郵便番号 |
| 国 | アドレス[タイプ Eq "仕事"].国 |
| 部署名 | urn:ietf:params:scim:schemas: extension:enterprise:2.0:User:department |
| 区分 | urn:ietf:params:scim:schemas: extension:enterprise:2.0:User:division |
| 会社 | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:組織 |
| ユーザー名 | ディスプレイ名 |
| 名前 | 名前.名 |
| 姓 | 名前.姓 |
| ジョブ コード | urn:ietf:params:scim:schemas:extension:COMPANYNAME:1.0:User:JobCode |
| ジョブ名 | タイトル |
| メール アドレス | emails[type eq "仕事"].value |
| 管理者 | urn:ietf:params:scim:schemas:extension: enterprise:2.0:User:manager |
| 携帯電話番号 | 電話番号[タイプ eq "携帯"].値 |
| 電話番号 | phoneNumbers[タイプが "職場" の場合].値 |
| 勤務先住所 | addresses[type eq "work"].フォーマット済み |
| 採用日 | urn:ietf:params:scim:schemas:extension:COMPANYNAME:1.0:User:HireDate |
| 退職日 | urn:ietf:params:scim:schemas:extension:COMPANYAME:1.0:User:TermDate |

#### ワークシート 3: 一意の ID の生成規則と変換規則を定義する

このセクションの表は、一意の生成規則または特定の変換規則を必要とする特定の属性について説明しています。 これらには、値を設定するための追加規則を持つ 3 つの一般的に使用される属性が含まれます。 これらの属性を適切に設定するには、リンクを参照してください。

| 特性 | 属性値の設定方法 |
| --- | --- |
| userPrincipalName (必須) | [Microsoft Entra のユーザー プロビジョニングにクラウド HR アプリケーションを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision#generate-a-unique-attribute-value) |
| SamAccountName (オンプレミスの AD のみ) | [Microsoft Entra のユーザー プロビジョニングにクラウド HR アプリケーションを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision#generate-a-unique-attribute-value) |
| parentDistinguishedName (オンプレミスの AD のみ) | [Microsoft Entra のユーザー プロビジョニングにクラウド HR アプリケーションを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision#configure-active-directory-ou-container-assignment) |

#### ワークシート 4: SCIM 属性とオンプレミスの AD 属性のマッピング

このセクションの表は、Active Directory でサポートされているオンプレミス属性のセットを表しています。 プロビジョニング ターゲットが Active Directory の場合、この表の属性に SCIM 属性をマップします。

| SCIM 属性 | オンプレミスの AD の属性 |
| --- | --- |
| 外部ID | 従業員ID |
| アクティブです | アカウントが無効化されました |
| アドレス[タイプ eq "作業"].ストリートアドレス | 住所 |
| アドレス[タイプ eq "職場"].ローカリティ | l |
| アドレス[タイプが"仕事"に等しい].地域 | 聖 |
| addresses[タイプ eq "work"].郵便番号 | 郵便番号 |
| アドレス[タイプ Eq "仕事"].国 | 会社 |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 部署 |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 部署 |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:組織 | 会社 |
| ディスプレイ名 | cn |
| 名前.名 | givenName |
| 名前.姓 | エスエヌ |
| urn:ietf:params:scim:schemas:extension:COMPANYNAME:1.0:User:JobCode | extensionAttribute1 |
| タイトル | タイトル |
| emails[type eq "仕事"].value | &lt;AD により生成&gt; |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | マネージャー |
| 電話番号[タイプ eq "携帯"].値 | モバイル |
| phoneNumbers[タイプが "職場" の場合].値 | 電話番号 |
| addresses[type eq "work"].フォーマット済み | 物理配送オフィス名 |
| urn:ietf:params:scim:schemas:extension:COMPANYNAME:1.0:User:HireDate | extensionAttribute2 |
| urn:ietf:params:scim:schemas:extension:COMPANYAME:1.0:User:TermDate | extensionAttribute3 |

注

対応するオンプレミスの AD 属性がない SCIM スキーマ拡張属性を定義している場合は、それらを *extensionAttributes* 1 から 15 にマップするか、[AD スキーマを拡張](https://learn.microsoft.com/ja-jp/windows/win32/ad/how-to-extend-the-schema)して、必要な属性を持つ新しい補助オブジェクト クラスを追加できます。

#### ワークシート 5: オンプレミスの AD から Microsoft Entra ID へのマッピング

ID をオンプレミスの AD に同期したら、クラウド同期または Microsoft Entra ID 接続を介して ID を Microsoft Entra ID に送信できます。 これらのツールの使用方法については、リンクされたドキュメントを参照してください。

このセクションの表は、ワークシート 4 に含まれる AD 属性から Microsoft Entra 属性への属性マッピングの例です。

注

カスタム属性 *extensionAttribute1* は、ワーカーのジョブ コードです。 前の表では、これは、AD の *extensionAttribute1* にマップされていました。 ただし、Microsoft Entra には対応する属性がないため、ここでは Microsoft Entra の *extensionAttribute1* にマッピングしています。 *extensionAttribute2* と *extensionAttribute3* (入社日と退職日) は、それに応じてマップされます。

| オンプレミスの AD の属性 | Microsoft Entra 属性 |
| --- | --- |
| 住所 | 住所 |
| l | 都市 |
| 聖 | 状態 |
| 郵便番号 | 郵便番号 |
| 会社 | 国 |
| 部署 | 部署 |
| 部署 | EmployeeOrgData.division |
| 会社 | 会社名 |
| cn | ディスプレイ名 |
| givenName | givenName |
| エスエヌ | 姓 |
| extensionAttribute1 | extensionAttribute1 |
| タイトル | 職務タイトル |
| &lt;AD により生成&gt; | メール |
| マネージャー | マネージャー |
| モバイル | モバイル |
| 電話番号 | 電話番号 |
| 物理配送オフィス名 | 物理配送オフィス名 |
| extensionAttribute2 | 従業員採用日 |
| extensionAttribute3 | 従業員退勤日時 |

#### ワークシート 6: SCIM 属性から Microsoft Entra 属性へのマッピング

このセクションの表は、Microsoft Entra ID でサポートされている属性のセットを表しています。 プロビジョニング ターゲットが Microsoft Entra ID の場合、この表の属性に SCIM 属性をマップします。 カスタム SCIM 属性をギャラリー アプリケーションに追加するには、「[API 駆動型プロビジョニングを拡張してカスタム属性を同期する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-custom-attributes)」を参照してください。

| SCIM 属性 | Microsoft Entra 属性 |
| --- | --- |
| 外部ID | 従業員ID |
| アクティブです | アカウント有効化 |
| アドレス[タイプ eq "作業"].ストリートアドレス | 住所 |
| アドレス[タイプ eq "職場"].ローカリティ | 都市 |
| アドレス[タイプが"仕事"に等しい].地域 | 状態 |
| addresses[タイプ eq "work"].郵便番号 | 郵便番号 |
| アドレス[タイプ Eq "仕事"].国 | 国 |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 部署 |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | EmployeeOrgData.division |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:組織 | 会社名 |
| ディスプレイ名 | ディスプレイ名 |
| 名前.名 | givenName |
| 名前.姓 | 姓 |
| urn:ietf:params:scim:schemas:extension:COMPANYNAME:1.0:User:JobCode | extensionAttribute1 |
| タイトル | 職務タイトル |
| emails[type eq "仕事"].value | メール |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | マネージャー |
| 電話番号[タイプ eq "携帯"].値 | モバイル |
| phoneNumbers[タイプが "職場" の場合].値 | 電話番号 |
| addresses[type eq "work"].フォーマット済み | 物理配送オフィス名 |
| urn:ietf:params:scim:schemas:extension:COMPANYNAME:1.0:User:HireDate | 従業員採用日 |
| urn:ietf:params:scim:schemas:extension:COMPANYAME:1.0:User:TermDate | 従業員退勤日時 |

### 確認

この記事のレビューと投稿に関して、次のパートナーに感謝します。

- Michael Starkweather (PwC ディレクター)
- Rob Allen (ActiveIdM アーキテクチャおよびテクノロジ ディレクター)
- Ray Nalette (ActiveIdM テクニカル デリバー マネージャー)
- Randy Robb (Oxford Computer Group 主席コンサルタント)
- Frank Urena (Oxford Computer Group 主席アーキテクト)
- Nick Herbert (Oxford Computer Group セールス担当副社長)
- Steve Brugger (Oxford Computer Group CEO)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oracle-idcs-for-ebs-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Oracle IDCS for E-Business Suite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-idcs-for-ebs-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Oracle IDCS for E-Business Suite の間にシングル サインオンを構成する方法について説明します。

この記事では、Oracle IDCS for E-Business Suite と Microsoft Entra ID を統合する方法について説明します。 Oracle IDCS for E-Business Suite を Microsoft Entra ID を統合すると、次のことができます。

- Oracle IDCS for E-Business Suite にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Oracle IDCS for E-Business Suite に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Oracle IDCS for E-Business Suite 用の Microsoft Entra シングル サインオンを構成してテストします。 Oracle IDCS for E-Business Suite は **SP** Initiated シングル サインオンのみをサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID と Oracle IDCS for E-Business Suite を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Oracle IDCS for E-Business Suite のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Oracle IDCS for E-Business Suite アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Oracle IDCS for E-Business Suite を追加する

Microsoft Entra アプリケーション ギャラリーから Oracle IDCS for E-Business Suite を追加して、Oracle IDCS for E-Business Suite を使ってシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Oracle IDCS for E-Business Suite**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 ` https://<SUBDOMAIN>.oraclecloud.com/`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.oraclecloud.com/v1/saml/<UNIQUEID>`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 ` https://<SUBDOMAIN>.oraclecloud.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値は、[Oracle IDCS for E-Business Suite サポート チーム](https://www.oracle.com/support/advanced-customer-services/)に問い合わせて入手してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Oracle IDCS for E-Business Suite アプリケーションでは、特定の形式の SAML アサーションを使うため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Oracle IDCS for E-Business Suite ではこれをユーザーのメール アドレスにマップすることが求められます。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

### Oracle IDCS for E-Business Suite SSO の構成

Oracle IDCS for E-Business Suite 上でシングル サインオンを構成するには、Azure portal からダウンロードしたフェデレーション メタデータ XML ファイルを [Oracle IDCS for E-Business Suite サポート チーム](https://www.oracle.com/support/advanced-customer-services/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Oracle IDCS for E-Business Suite テスト ユーザーの作成

このセクションでは、Oracle IDCS for E-Business Suite で Britta Simon というユーザーを作成します。 [Oracle IDCS for E-Business Suite サポート チーム](https://www.oracle.com/support/advanced-customer-services/)と連携して、Oracle IDCS for E-Business Suite プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Oracle IDCS for E-Business Suite のサインオン URL にリダイレクトされます。
- Oracle IDCS for E-Business Suite のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Oracle IDCS for E-Business Suite] タイルを選択すると、このオプションは Oracle IDCS for E-Business Suite のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oracle-idcs-for-jd-edwards-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Oracle IDCS for JD Edwards を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-idcs-for-jd-edwards-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Oracle IDCS for JD Edwards の間でシングル サインオンを構成する方法について説明します。

この記事では、Oracle IDCS for JD Edwards と Microsoft Entra ID を統合する方法について説明します。 Oracle IDCS for JD Edwards を Microsoft Entra ID を統合すると、次のことが可能になります。

- Oracle IDCS for JD Edwards にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Oracle IDCS for JD Edwards に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Oracle IDCS for JD Edwards 用の Microsoft Entra シングル サインオンを構成してテストします。 Oracle IDCS for JD Edwards は **SP** によって開始されたシングル サインオンのみをサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Oracle IDCS for JD Edwards と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Oracle IDCS for JD Edwards のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Oracle IDCS for JD Edwards アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Oracle IDCS for JD Edwards を追加する

Microsoft Entra アプリケーション ギャラリーから Oracle IDCS for JD Edwards を追加して、Oracle IDCS for JD Edwards でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Oracle IDCS for JD Edwards]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 ` https://<SUBDOMAIN>.oraclecloud.com/`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.oraclecloud.com/v1/saml/<UNIQUEID>`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 ` https://<SUBDOMAIN>.oraclecloud.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値は、[Oracle IDCS for JD Edwards サポート チーム](https://www.oracle.com/support/advanced-customer-services/)に問い合わせて入手してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Oracle IDCS for JD Edwards アプリケーションでは、特定の形式の SAML アサーションを使うため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Oracle IDCS for JD Edwards ではこれをユーザーのメール アドレスにマップすることが求められます。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 既定の属性の画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書の [ダウンロード] リンクを示すスクリーンショット。]

### Oracle IDCS for JD Edwards の SSO を構成する

Oracle IDCS for JD Edwards 側でシングル サインオンを構成するには、Azure portal からダウンロードしたフェデレーション メタデータ XML ファイルを [Oracle IDCS for JD Edwards サポート チーム](https://www.oracle.com/support/advanced-customer-services/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Oracle IDCS for JD Edwards のテスト ユーザーを作成する

このセクションでは、Oracle IDCS for JD Edwards で Britta Simon というユーザーを作成します。 [Oracle IDCS for JD Edwards サポート チーム](https://www.oracle.com/support/advanced-customer-services/)と連携して、Oracle IDCS for JD Edwards プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Oracle IDCS for JD Edwards のサインオン URL にリダイレクトされます。
- Oracle IDCS for JD Edwards のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Oracle IDCS for JD Edwards] タイルを選択すると、このオプションは Oracle IDCS for JD Edwards のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oracle-idcs-for-peoplesoft-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Oracle IDCS for PeopleSoft を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oracle-idcs-for-peoplesoft-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Oracle IDCS for PeopleSoft の間にシングル サインオンを構成する方法について説明します。

この記事では、Oracle IDCS for PeopleSoft と Microsoft Entra ID を統合する方法について説明します。 Oracle IDCS for PeopleSoft を Microsoft Entra ID を統合すると、次のことができます。

- Oracle IDCS for PeopleSoft にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Oracle IDCS for PeopleSoft に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Oracle IDCS for PeopleSoft 用の Microsoft Entra シングル サインオンを構成してテストします。 Oracle IDCS for PeopleSoft は **SP** Initiated シングル サインオンのみをサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID と Oracle IDCS for PeopleSoft を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Oracle IDCS for PeopleSoft のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Oracle IDCS for PeopleSoft アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Oracle IDCS for PeopleSoft を追加する

Microsoft Entra アプリケーション ギャラリーから Oracle IDCS for PeopleSoft を追加して、Oracle IDCS for PeopleSoft を使ってシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Oracle IDCS for PeopleSoft**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 ` https://<SUBDOMAIN>.oraclecloud.com/`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.oraclecloud.com/v1/saml/<UNIQUEID>`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 ` https://<SUBDOMAIN>.oraclecloud.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値は、[Oracle IDCS for PeopleSoft サポート チーム](https://www.oracle.com/support/advanced-customer-services/cloud/)に問い合わせて入手してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Oracle IDCS for PeopleSoft アプリケーションでは、特定の形式の SAML アサーションを使うため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Oracle IDCS for PeopleSoft ではこれをユーザーのメール アドレスにマップすることが求められます。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

### Oracle IDCS for PeopleSoft の SSO を構成する

Oracle IDCS for PeopleSoft 上でシングル サインオンを構成するには、Azure portal からダウンロードしたフェデレーション メタデータ XML ファイルを [Oracle IDCS for PeopleSoft サポート チーム](https://www.oracle.com/support/advanced-customer-services/cloud/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Oracle IDCS for PeopleSoft テスト ユーザーを作成する

このセクションでは、Oracle IDCS for PeopleSoft で Britta Simon というユーザーを作成します。 [Oracle IDCS for PeopleSoft サポート チーム](https://www.oracle.com/support/advanced-customer-services/cloud/)と連携し、Oracle IDCS for PeopleSoft プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Oracle IDCS for PeopleSoft のサインオン URL にリダイレクトされます。
- Oracle IDCS for PeopleSoft のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Oracle IDCS for PeopleSoft] タイルを選択すると、このオプションは、Oracle IDCS for PeopleSoft のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oreilly-learning-platform-provisioning-tutorial"} -->
## O'Reilly 学習プラットフォームを構成し、Microsoft Entra ID を使った自動ユーザー プロビジョニングに対応させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oreilly-learning-platform-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra ID から O'Reilly 学習プラットフォームにユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を説明します。

この記事では、自動ユーザー プロビジョニングを構成するために O'Reilly 学習プラットフォームと Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して [ユーザーを O'Reilly ラーニング プラットフォームに自動的に](https://www.oreilly.com/) プロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- O'Reilly 学習プラットフォームでユーザーを作成します。
- アクセスが不要になったら、O'Reilly 学習プラットフォームのユーザーを削除します。
- Microsoft Entra ID と O'Reilly 学習プラットフォームの間でユーザー属性の同期を維持します。
- O'Reilly 学習プラットフォームに[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oreilly-learning-platform-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者権限を持つ O'Reilly 学習プラットフォームのユーザー アカウント。
- O'Reilly 学習プラットフォームのシングル サインオン (SSO) 対応サブスクリプション。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- [Microsoft Entra ID と O'Reilly 学習プラットフォームの間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID でのプロビジョニングをサポートするように O'Reilly 学習プラットフォームを構成する

Microsoft Entra ID でのプロビジョニングをサポートするように O'Reilly 学習プラットフォームの構成を開始する前に、O'Reilly 管理コンソール内で SCIM API トークンを生成する必要があります。

1. O'Reilly アカウントにログインして、O'Reilly 管理コンソールに移動します。
2. ログインしたら、上部のナビゲーションで **[管理者** ] を選択し、[ **統合**] を選択します。
3. **[API トークン**] セクションまで下にスクロールします。 [API トークン] で [ **トークンの作成** ] を選択し、 **SCIM API** を選択します。 次に、トークンに名前と有効期限を指定し、[続行] を選択します。 API キーがポップアップ メッセージで通知され、そのコピーを安全な場所に保存するように求められます。 キーのコピーを保存したら、チェックボックスをオンにして[続行]を選択します。
4. 手順 5 で O'Reilly SCIM API トークンを使用します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから O'Reilly 学習プラットフォームを追加する

Microsoft Entra アプリケーション ギャラリーから O'Reilly 学習プラットフォームを追加して、O'Reilly 学習プラットフォームへのプロビジョニングの管理を開始します。 [SSO 用に O'Reilly ラーニング プラットフォームを以前に設定](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oreilly-learning-platform-tutorial)している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: O'Reilly 学習プラットフォームへの自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra プロビジョニング サービスが、Microsoft Entra ID でのユーザー割り当てに基づいて、O'Reilly 学習プラットフォームでのユーザーの作成、更新、無効化を行うように構成する手順について説明します。

#### Microsoft Entra ID で O'Reilly 学習プラットフォームの自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で[ **O'Reilly learning platform]\(O'Reilly 学習プラットフォーム**\) を選択します。

    [Image: アプリケーションの一覧の [O'Reilly learning platform](O'Reilly 学習プラットフォーム) リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、O'Reilly ラーニング プラットフォームのテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が O'Reilly 学習プラットフォームに接続できることを確認します。 接続に失敗した場合は、O'Reilly ラーニング プラットフォーム アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    Note

    `https://api.oreilly.com/api/scim/v2` に「」と入力します。 接続に失敗した場合は、トークンが正しいことを再確認するか、 [O'Reilly プラットフォーム統合チームに問い合わせてください](mailto:platform-integration@oreilly.com) 。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から O'Reilly 学習プラットフォームに同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために O'Reilly 学習プラットフォームのユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、O'Reilly 学習プラットフォーム API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | O'Reilly 学習プラットフォームで必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | emails[type eq "work"].value | 糸 |  | ✓ |
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
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/oreilly-learning-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に O'Reilly ラーニング プラットフォームを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oreilly-learning-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と O'Reilly Learning Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、O'Reilly Learning Platform を Microsoft Entra ID と統合する方法について学習します。 Microsoft Entra ID と O'Reilly Learning Platform を統合すると、SAML でシングル サインオン (SSO) を有効にできます。 これにより、エンド ユーザーにシームレスなログイン エクスペリエンスを提供します。 O'Reilly Learning Platform を Microsoft Entra ID を統合すると、次のことができます。

- O'Reilly Learning Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して O'Reilly Learning Platform に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で O'Reilly Learning Platform 向けの Microsoft Entra シングル サインオンを構成してテストする。 O'Reilly Learning Platform は、**SP** と **IDP** Initiated の両方のシングル サインオンと **Just In Time** ユーザー プロビジョニングをサポートします。 O'Reilly 学習プラットフォームでは、 [自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/oreilly-learning-platform-provisioning-tutorial)もサポートされています。

### [前提条件]

Microsoft Entra ID を O'Reilly Learning Platform と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- O'Reilly Learning Platform のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから O'Reilly Learning Platform アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから O'Reilly Learning Platform を追加する

Microsoft Entra アプリケーション ギャラリーから O'Reilly Learning Platform を追加して、O'Reilly Learning Platform でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**&gt;**O'Reilly ラーニングプラットフォーム**&gt;**シングルサインオン**を閲覧します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア **[識別子]** ボックスに、`urn:auth0:learning:<CONNECTION-NAME>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://sso.oreilly.com/login/callback?connection=<CONNECTION-NAME>`
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://go.oreilly.com/<CONNECTION-NAME>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [O'Reilly Learning Platform のクライアント サポート チーム](mailto:platform-integration@oreilly.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### O'Reilly Learning Platform の SSO を構成する

**O'Reilly Learning Platform** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [O'Reilly Learning Platform のサポート チーム](mailto:platform-integration@oreilly.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### O'Reilly Learning Platform のテスト ユーザーを作成する

このセクションでは、O'Reilly Learning Platform で B.Simon というユーザーを作成します。 O'Reilly Learning Platform は、Just-In-Time ユーザー プロビジョニングをサポートします。これは既定で有効になります。 このセクションにはアクション項目はありません。 O'Reilly Learning Platform にユーザーがまだ存在していない場合、一般に認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる O'Reilly 学習プラットフォームのサインオン URL にリダイレクトされます。
- O'Reilly Learning Platform のサインオン URL に直接アクセスし、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した O'Reilly ラーニング プラットフォームに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [O'Reilly 学習プラットフォーム] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した O'Reilly ラーニング プラットフォームに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/orgchartnow-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に OrgChart Now を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/orgchartnow-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と OrgChart Now 間にシングル サインオンを構成する方法について学習します。

この記事では、OrgChart Now と Microsoft Entra ID を統合する方法について説明します。 OrgChart Now を Microsoft Entra ID を統合すると、次のことができます。

- OrgChart Now にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って OrgChart Now に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- OrgChart Now のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- OrgChart Now により、**SP** および **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから OrgChart Now を追加する

Microsoft Entra ID への OrgChart Now の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に OrgChart Now を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**OrgChart Now**」と入力します。
4. 結果パネルから **OrgChart Now** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### OrgChart Now 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、OrgChart Now に Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと OrgChart Now の関連ユーザーとの間にリンク関係を確立する必要があります。

OrgChart Now に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **OrgChart Now の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **OrgChart Now のテストユーザーを作成** - OrgChart Now において B.Simon に相当するユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**OrgChart Now**&gt;**シングルサインオン**にアクセスしてください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://<OrgChartNowServer>.orgchartnow.com/saml/sso_metadata?entityID=<Your_Azure_AD_Entity_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<OrgChartServer>.orgchartnow.com/saml/sso_acs?entityID=<Your_Azure_AD_Entity_ID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<OrgChartServer>.orgchartnow.com/saml/sso_acs?entityID=<Your_Azure_AD_Entity_ID>`

    注

    `<YourEntityID>`は、記事で後述する「**OrgChart Now のセットアップ**」セクションからコピーした **Microsoft Entra 識別子**です。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[OrgChart Now のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### OrgChart Now の SSO の構成

OrgChart Now でシングル サインオンを構成するには、OrgChart Now のヘルプ サイトにある [SSO の構成に関する記事](https://help.orgchartnow.com/en/topics/sso-configuration.html#configuring-sso-41334)に列挙されている手順に従います。

#### OrgChart Now のテスト ユーザーの作成

Microsoft Entra ユーザーが OrgChart Now にログインできるようにするには、そのユーザーを OrgChart Now のユーザーとして設定するか、または **[SSO 構成]** パネルで [\[自動プロビジョニング\]](https://help.orgchartnow.com/en/topics/sso-configuration.html#configuring-sso-41334) を有効にする必要があります。

現時点で自動プロビジョニングを有効にしない場合は、SSO テストのためにユーザーを OrgChart Now に手動で追加できます。 それを行うには、[アカウント設定: ユーザーの管理](https://help.orgchartnow.com/en/account-settings/manage-users.html#UUID-a921b00b-a5a2-3099-8fe5-d0f28f5a50b9_bridgehead-idm4532421481724832584395125038)に関する記事の「[新しいユーザーの作成](https://help.orgchartnow.com/en/account-settings/manage-users.html)」セクションに列挙されている手順に従います。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる OrgChart Now のサインオン URL にリダイレクトされます。
- OrgChart Now のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した OrgChart Now に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [OrgChart Now] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した OrgChart Now に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/orgvitality-sso-tutorial"} -->
## Microsoft Entra ID で OrgVitality SSO for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/orgvitality-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と OrgVitality SSO の間にシングル サインオンを構成する方法についてご確認ください。

この記事では、OrgVitality SSO と Microsoft Entra ID を統合する方法について説明します。 OrgVitality SSO と Microsoft Entra ID を統合すると、次のことができます。

- OrgVitality SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って OrgVitality SSO に自動的にサインインできるように設定できます。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- OrgVitality SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- OrgVitality SSO では、**IDP** Initiated SSO がサポートされています。

### ギャラリーから OrgVitality SSO を追加する

Microsoft Entra ID への OrgVitality SSO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に OrgVitality SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**OrgVitality SSO**」と入力します。
4. 結果 パネルで **OrgVitality SSO** を選択して、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### OrgVitality SSO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、OrgVitality SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと OrgVitality SSO の関連するユーザーの間のリンク関係を確立する必要があります。

OrgVitality SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **OrgVitality SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **OrgVitality SSO テスト ユーザーの作成** - OrgVitality SSO で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**OrgVitality SSO**&gt;**シングル サインオンに移動します**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://rpt.orgvitality.com/<COMPANY_NAME>/` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://rpt.orgvitality.com/<COMPANY_NAME>Auth/default.aspx` のパターンを使用して URL を入力します

    注意

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[OrgVitality SSO サポート チーム](https://orgvitality.com/contact-us/)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. OrgVitality SSO アプリケーションでは、特定の形式の SAML アサーションを予測しているため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 OrgVitality SSO アプリケーションでは **、nameidentifier** が **user.employeeid** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[OrgVitality SSO のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### OrgVitality SSO の構成

**OrgVitality SSO** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [OrgVitality SSO サポート チーム](https://orgvitality.com/contact-us/)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### OrgVitality SSO テスト ユーザーの作成

このセクションでは、OrgVitality SSO で Britta Simon というユーザーを作成します。 [OrgVitality SSO サポート チーム](https://orgvitality.com/contact-us/)と連携して、OrgVitality SSO プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した OrgVitality SSO に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [OrgVitality SSO] タイルを選択すると、SSO を設定した OrgVitality SSO に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/orgvue-tutorial"} -->
## Microsoft Entra ID で Orgvue for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/orgvue-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-07-24
- Summary: Microsoft Entra IDと Orgvue の間でシングル サインオンを構成する方法について説明します。

この記事では、Orgvue と Microsoft Entra ID を統合する方法について説明します。 Orgvue を Microsoft Entra ID と統合すると、次のことができます。

- Orgvue にアクセスできるユーザーをMicrosoft Entra IDで制御できます。
- ユーザーが自分のMicrosoft Entra アカウントを使用して Orgvue に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### Prerequisites

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Orgvue でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Orgvue では、 **SP** によって開始される SSO のみがサポートされます。

### ギャラリーから Orgvue を追加する

Microsoft Entra IDへの Orgvue の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Orgvue を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ **ギャラリーからの追加] セクションで** 、検索ボックスに **「Orgvue** 」と入力します。
4. 結果パネルから **Orgvue** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細については](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)を参照してください。

### Orgvue の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Orgvue Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Orgvue の関連ユーザーとの間にリンク関係を確立する必要があります。

Orgvue Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra のテストユーザーを作成** - B.Simon を使用して Microsoft Entra のシングルサインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Orgvue の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Orgvue のテスト ユーザーを作成** - Orgvue で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 上のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Orgvue**&gt;**シングル サインオン** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://orgvue-staging.us-east-1.concentra.io/auth`

    b. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://orgvue-staging.us-east-1.concentra.io/saml-callback/<YOUR_DOMAIN>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://orgvue-staging.us-east-1.concentra.io/app/login-gateway/?domain=<YOUR_DOMAIN>`

    Note

    応答 URL とサインオン URL の値は実際の値ではありません。 これらの値を実際の URL で更新します。 `<YOUR_DOMAIN>`の値を実際のドメイン (例: `microsoft.com`) に置き換えます。追加のサポートについては、[Orgvue クライアント サポート チーム](mailto:support@orgvue.com)にお問い合わせください。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra のテストユーザーを作成して割り当てる。

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Orgvue SSO の構成

**Orgvue** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Orgvue サポート チーム](mailto:support@orgvue.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Orgvue テスト ユーザーの作成

このセクションでは、Orgvue で B.Simon というユーザーを作成します。 [Orgvue サポート チーム](mailto:support@orgvue.com)と協力して、Orgvue プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Orgvue のサインオン URL にリダイレクトします。
- Orgvue のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Orgvue] タイルを選択すると、このオプションは Orgvue のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/origami-tutorial"} -->
## Microsoft Entra ID で Origami をシングル サインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/origami-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Origami の間のシングル サインオンを構成する方法について説明します。

この記事では、Origami と Microsoft Entra ID を統合する方法について説明します。 Origami を Microsoft Entra ID と統合すると、次のことが可能になります。

- Origami にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Origami に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Origami でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Origami では、**SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Origami の追加

Microsoft Entra ID への Origami の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Origami を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Origami**」と入力します。
4. 結果のパネルから **[Origami]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Origami 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Origami に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Origami の関連ユーザーとの間にリンク関係を確立する必要があります。

Origami に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Origami SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Origami のテスト ユーザーの作成** - Origami で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Origami]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://live.origamirisk.com/origami/account/login?account=<COMPANY_NAME>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[Origami クライアント サポート チーム](https://wordpress.org/support/theme/origami)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Origami の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Origami SSO の構成

1. 管理者権限で Origami アカウントにログインします。
2. 上部のメニューで、[管理者] を選択 **します**。

    [Image: Origami ホーム ページのスクリーンショット。[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者) が選択されています。]
3. [Single Sign On Setup] ダイアログ ページで、次の手順に従います。

    [Image: [Single Sign On Setup](シングル サインオンの設定) ページのスクリーンショット。[Enable Single Sign-on](シングル サインオンを有効にする) が選択され、テキスト ボックスが強調表示されています。]

    a. **[シングル サインオンを有効にする]** を選択します。

    b。 **[Identity Provider's Sign-in Page URL] (ID プロバイダー サインイン ページ URL)** テキスト ボックスに、**ログイン URL** の値を貼り付けます。

    c. **[Identity Provider's Sign-out Page URL] (ID プロバイダー シングル サインアウト ページ URL)** テキスト ボックスに、**ログアウト URL** の値を貼り付けます。

    d. **[参照]**を選択して、ダウンロードした証明書をアップロードします。

    e. [ **変更の保存] を選択します**。

#### Origami テスト ユーザーの作成

このセクションでは、Origami で Britta Simon というユーザーを作成します。

1. 管理者権限で Origami アカウントにログインします。
2. 上部のメニューで、[管理者] を選択 **します**。

    [Image: Origami アカウント ホーム ページのスクリーンショット。[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者) が選択されています。]
3. [ **ユーザーとセキュリティ** ] ダイアログで、[ **ユーザー**] を選択します。

    [Image: [Users and Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーとセキュリティ) ダイアログのスクリーンショット。[ユーザー] が選択されています。]
4. [ **新しいユーザーの追加] を選択します**。

    [Image: [新しいユーザーの追加] ボタンが選択されていることを示すスクリーンショット。]
5. [新規ユーザーの追加] ダイアログで、次の手順を実行します。

    [Image: [新規ユーザーの追加] ダイアログのスクリーンショット。[ユーザー名]、[名]、[姓] テキスト ボックスが強調表示されています。]

    a. **[ユーザー名]** ボックスに、ユーザーの電子メール (**brittasimon@contoso.com** など) を入力します。

    b。 **[パスワード]** ボックスに、パスワードを入力します。

    c. **[パスワードの確認]** ボックスに、パスワードを再度入力します。

    d. **名** テキストボックスに、ユーザーの名を「**Britta**」のように入力します。

    e. [ **姓]** ボックスに、ユーザーの姓 ( **Simon** など) を入力します。

    f. **保存** を選択します。

    [Image: [保存] ボタンが選択されていることを示すスクリーンショット。]
6. **ユーザー ロール**と**クライアント アクセス**をユーザーに割り当てます。

    [Image: シングルサインオンの設定]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Origami のサインオン URL にリダイレクトされます。
- Origami のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Origami] タイルを選択すると、このオプションは Origami のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/othership-workplace-scheduler-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Othership Workplace Scheduler を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/othership-workplace-scheduler-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Othership Workplace Scheduler の間でシングル サインオンを構成する方法について説明します。

この記事では、Othership Workplace Scheduler と Microsoft Entra ID を統合する方法について説明します。 Othership Workplace Scheduler と Microsoft Entra ID を統合すると、次のことができます。

- Othership Workplace Scheduler のアクセス権を持つユーザーを、Microsoft Entra ID で管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Othership Workplace Scheduler に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Othership Workplace Scheduler でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Othership Workplace Scheduler では、 **IDP** によって開始される SSO のみがサポートされます。
- Othership Workplace Scheduler では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからOthership Workplace Schedulerを追加する

Microsoft Entra ID への Othership Workplace Scheduler の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Othership Workplace Scheduler を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Othership Workplace Scheduler**」と入力します。
4. 結果パネルから **Othership Workplace Scheduler** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Othership Workplace Scheduler の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Othership Workplace Scheduler に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Othership Workplace Scheduler の関連ユーザーとの間にリンク関係を確立する必要があります。

Othership Workplace Scheduler に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Othership Workplace Scheduler の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Othership Workplace Scheduler テスト ユーザーの作成** - Microsoft Entra に表現されているユーザーとリンクされている、B.Simon に対応する Othership Workplace Scheduler でのユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Othership Workplace Scheduler**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **基本的な SAML 構成** セクションでは、アプリケーションが事前に構成されており、必要な URL が既に Microsoft Entra に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Othership Workplace Scheduler アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、Othership Workplace Scheduler アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名（ファーストネーム） | ユーザー.ファーストネーム |
    | last\_name | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. [ **Othership Workplace Scheduler のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Othership Workplace Scheduler の SSO の構成

1. Othership Workplace Scheduler 企業サイトに管理者としてログインします。
2. **[設定**&gt;**組織設定**&gt;**組織統合**に移動し、**+ 追加** SAML 2.0 を選択します。

    [Image: [構成] のパスを示すスクリーンショット。]
3. [ **SAML 構成の追加]** ページで、次の手順を実行します。

    [Image: スクリーンショットは、構成を示しています。]

    1. ドロップダウン**から** **プロバイダーとして Microsoft Entra ID を**選択します。
    2. **[SAML SSO (サインオン URL)]** テキスト ボックスに、Microsoft Entra 管理センターからコピーした**ログイン URL** の値を貼り付けます。
    3. [ **ID プロバイダー発行者** ] ボックスに、Microsoft Entra 管理センターからコピーした **Microsoft Entra 識別子** の値を貼り付けます。
    4. [IDP メタデータのインポート] を選択して、以前にダウンロードした **フェデレーション メタデータ XML** ファイルをインポートします。その後、証明書が **[パブリック証明書** ] ボックスに表示されます。
    5. [ **構成の保存] を選択します**。

#### Othership Workplace Scheduler のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Othership Workplace Scheduler に作成します。 Othership Workplace Scheduler では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Othership Workplace Scheduler にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した Othership Workplace Scheduler に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Othership Workplace Scheduler] タイルを選択すると、SSO を設定した Othership Workplace Scheduler に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ou-campus-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に OU Campus を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ou-campus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と OU Campus 間にシングル サインオンを構成する方法について説明します。

この記事では、OU Campus と Microsoft Entra ID を統合する方法について説明します。 OU Campus と Microsoft Entra ID の統合には、次の利点があります:

- OU Campus にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントで OU Campus に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- OU Campus でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- OU Campus では、**SP** によって開始される SSO がサポートされます

### ギャラリーからの OU Campus の追加

Microsoft Entra ID への OU Campus の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に OU Campus を追加する必要があります。

**ギャラリーから OU Campus を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **OU Campus**」と入力し、結果パネルから **OU Campus** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の OU Campus]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、OU Campus で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと OU Campus 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

OU Campus で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります:

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **OU Campus シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **OU Campus のテスト ユーザーの作成** - Britta Simon に対応する OU Campus のユーザーを作成し、そのユーザーを Microsoft Entra の表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

OU Campus で Microsoft Entra シングル サインオンを構成するには、次の手順に従います:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**OU Campus** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: OU Campus のドメインと URL のシングル サインオン情報]

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://a.cms.omniupdate.com/<Instance Name>`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[OU Campus クライアント サポート チーム](mailto:support@omniupdate.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[OU Campus のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    ある。 ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### OU Campus シングルサインオンの構成

**OU Campus** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [OU Campus サポート チーム](mailto:support@omniupdate.com) に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### OU Campus のテスト ユーザーの作成

このセクションでは、OU Campus で Britta Simon というユーザーを作成します。 [OU Campus サポート チーム](mailto:support@omniupdate.com)と連携し、OU Campus プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [OU Campus] タイルを選択すると、SSO を設定した OU Campus に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/outsystems-tutorial"} -->
## Microsoft Entra ID によるシングルサインオンのために、OutSystems を Microsoft Entra ID に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/outsystems-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と OutSystems Microsoft Entra ID 間にシングル サインオンを構成する方法について学習します。

この記事では、OutSystems Microsoft Entra ID と Microsoft Entra ID を統合する方法について説明します。 OutSystems Microsoft Entra ID を Microsoft Entra ID と統合すると、以下のことができます。

- OutSystems にアクセスする権限を持つユーザーを Microsoft Entra ID で管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して OutSystems Microsoft Entra ID に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- OutSystems Microsoft Entra でのシングル サインオン (SSO) が有効になったサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- OutSystems Microsoft Entra ID では、 **SP Initiated SSO と IDP** Initiated SSO がサポートされ、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから OutSystems Microsoft Entra ID を追加する

Microsoft Entra ID への OutSystems Microsoft Entra ID の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に OutSystems Microsoft Entra ID を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「OutSystems Microsoft Entra ID**」と入力します。
4. 結果パネルから **OutSystems Microsoft Entra ID を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### OutSystems Microsoft Entra ID に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、OutSystems Microsoft Entra ID に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと OutSystems Microsoft Entra ID の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を OutSystems Microsoft Entra ID と組み合わせて構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **OutSystems Microsoft Entra SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **OutSystems Microsoft Entra テストユーザーを作成** - Microsoft Entra ID のユーザーとして OutSystems における B.Simon に対応するユーザーを作成し、このユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**OutSystems Microsoft Entra ID** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `http://<YOURBASEURL>/IdP`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOURBASEURL>/IdP/SSO.aspx`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOURBASEURL>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、OutSystems クライアント サポート チーム](mailto:support@outsystems.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **OutSystems Microsoft Entra ID のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### OutSystems Microsoft Entra SSO を構成する

OutSystems 側でシングル サインオンを構成するには、 [IdP](https://success.outsystems.com/Documentation/Development_FAQs/How_to_configure_OutSystems_to_use_identity_providers_using_SAML#Configure_your_application_to_use_IdP_connector) forge コンポーネントをダウンロードし、手順に従って構成する必要があります。 コンポーネントをインストールし、必要なコード変更を行った後、次の手順に従って、Azure portal からフェデレーション メタデータ XML をダウンロードして Microsoft Entra ID を構成し、OutSystems IdP コンポーネントにアップロード [します](https://success.outsystems.com/Documentation/Development_FAQs/How_to_configure_OutSystems_to_use_identity_providers_using_SAML#Azure_AD_.2F_ADFS)。

#### OutSystems Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを OutSystems に作成します。 OutSystems では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 OutSystems にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる OutSystems Microsoft Entra ID のサインオン URL にリダイレクトされます。
- OutSystems Microsoft Entra のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した OutSystems Microsoft Entra ID に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [OutSystems Microsoft Entra ID] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した OutSystems Microsoft Entra ID に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/overdrive-books-tutorial"} -->
## Microsoft Entra ID で Overdrive for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/overdrive-books-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Overdrive の間でシングル サインオンを構成する方法について説明します。

この記事では、Overdrive と Microsoft Entra ID を統合する方法について説明します。 Overdrive と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Overdrive にアクセスできるユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Overdrive に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Overdrive でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Overdrive では、**SP** Initiated SSO がサポートされます。
- Overdrive では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Overdrive を追加する

Microsoft Entra ID への Overdrive の統合を構成するには、次の手順を実行して、ギャラリーから管理対象 SaaS アプリの一覧に Overdrive を追加します。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Overdrive**」と入力します。
4. 結果ウィンドウで[**Overdrive**]を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Overdrive の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Overdrive に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Overdrive の関連ユーザーとの間にリンク関係を確立する必要があります。

Overdrive に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Overdrive SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Overdrive のテストユーザーを作成 - Microsoft Entra 上の B.Simon にリンクするために、Overdrive で B.Simon に対応するユーザーを作成します。**
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Overdrive**&gt;**シングル サインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本 SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `http://<subdomain>.libraryreserve.com`

    手記

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 値を取得するには、[Overdrive クライアント サポート チーム](https://help.overdrive.com/) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用したシングル Sign-On の設定** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **アプリフェデレーション メタデータ URL を** ダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [**Overdrive** のセットアップ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Overdrive SSO の構成

シングル サインオンを Overdrive **側** で構成するには、アプリフェデレーション メタデータ URL  を Overdrive サポート チーム に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Overdrive テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Overdrive に作成します。 Overdrive では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Overdrive にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

手記

OverDrive によって提供される他の OverDrive ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Overdrive のサインオン URL にリダイレクトされます。
- Overdrive のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Overdrive] タイルを選択すると、このオプションは Overdrive のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pacific-timesheet-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Pacific Timesheet を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pacific-timesheet-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Pacific Timesheet の間のシングル サインオンを構成する方法について説明します。

この記事では、Pacific Timesheet と Microsoft Entra ID を統合する方法について説明します。 Pacific Timesheet と Microsoft Entra ID の統合には、次の利点があります。

- Pacific Timesheet にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Pacific Timesheet に自動的にサインイン (シングル サインオン) できるようにすることができます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pacific Timesheet でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Pacific Timesheet では、 **IDP** Initiated SSO がサポートされます

### ギャラリーからの Pacific Timesheet の追加

Microsoft Entra ID への Pacific Timesheet の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Pacific Timesheet を追加する必要があります。

**ギャラリーから Pacific Timesheet を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **Pacific Timesheet**」と入力し、結果パネルで **Pacific Timesheet** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Pacific Timesheet]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Pacific Timesheet で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Pacific Timesheet 内の関連ユーザーの間にリンク関係が確立されている必要があります。

Pacific Timesheet で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Pacific Timesheet シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Pacific Timesheet のテストユーザーを作成 - Britta Simon に対応するユーザーを作成し、Microsoft Entra の表現とリンクさせます。**
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Pacific Timesheet で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Pacific Timesheet** アプリケーション統合ページを参照し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    [Image: Pacific Timesheet のドメインおよび URL のシングルサインオン情報]

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<InstanceID>.pacifictimesheet.com/timesheet/home.do`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<InstanceID>.pacifictimesheet.com/timesheet/home.do`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Pacific Timesheet クライアント サポート チーム](https://www.pacifictimesheet.com/support) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Pacific Timesheet のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Pacific Timesheet のシングル サインオンの構成

**Pacific Timesheet** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Pacific Timesheet サポート チーム](https://www.pacifictimesheet.com/support)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Pacific Timesheet テスト ユーザーの作成

このセクションでは、Pacific Timesheet で Britta Simon というユーザーを作成します。 [Pacific Timesheet サポート チーム](https://www.pacifictimesheet.com/support)と協力して、Pacific Timesheet プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Pacific Timesheet] タイルを選択すると、SSO を設定した Pacific Timesheet に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pagedna-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に PageDNA を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pagedna-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と PageDNA 間にシングル サインオンを構成する方法について説明します。

この記事では、PageDNA と Microsoft Entra ID を統合する方法について説明します。 PageDNA を Microsoft Entra ID と統合すると、次のことができます。

- PageDNA にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して PageDNA に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

PageDNA と Microsoft Entra の統合を構成するには、次の項目が必要です。

- Microsoft Entra サブスクリプション。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- シングル サインオンが有効な PageDNA のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成してテストし、PageDNA と Microsoft Entra ID を統合します。

PageDNA では、次の機能をサポートしています。

- SP によって開始されるシングル サインオン (SSO)。
- Just-In-Time のユーザー プロビジョニング。

### Azure Marketplace からの PageDNA の追加

Microsoft Entra ID への PageDNA の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に PageDNA を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**PageDNA**」と入力します。
4. 結果パネルから **[PageDNA]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### PageDNA 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、PageDNA に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと PageDNA の関連ユーザーとの間にリンク関係を確立する必要があります。

PageDNA に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PageDNA SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PageDNA テストユーザーを作成** - PageDNA で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra における B.Simon の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**PageDNA**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. **[識別子 (エンティティ ID)]** ボックスに、次のいずれかのパターンを使用して URL を入力します。

        | **識別子** |
        | --- |
        | `https://stores.pagedna.com/<your site>/saml2ep.cgi` |
        | `https://www.nationsprint.com/clients/<your site>/saml2ep.cgi` |
    2. [ **応答 URL (Assertion Consumer Service URL)]** ボックスに、次のいずれかのパターンを使用して URL を入力します。

        | **応答 URL** |
        | --- |
        | `https://stores.pagedna.com/<your site>/saml2ep.cgi` |
        | `https://www.nationsprint.com/clients/<your site>/saml2ep.cgi` |
    3. **[サインオン URL]** ボックスに、以下のいずれかの形式で URL を入力します。

        | **サインオン URL** |
        | --- |
        | `https://stores.pagedna.com/<your site>` |
        | `https://<your domain>` |
        | `https://<your domain>/<your site>` |
        | `https://www.nationsprint.com/clients/<your site>` |

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[PageDNA サポート チーム](mailto:success@pagedna.com)に問い合わせてください。 **[Basic SAML Configuration] (基本的な SAML 構成)** ペインに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ウィンドウの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して特定のオプションの**証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書 (未加工) のダウンロード オプションを示すスクリーンショット。]
7. **[PageDNA のセットアップ]** セクションで、必要な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PageDNA SSO の構成

PageDNA 側でシングル サインオンを構成するには、ダウンロードした証明書 (未加工) とコピーした適切な URL を、[PageDNA サポート チーム](mailto:success@pagedna.com)に送信する必要があります。 PageDNA チームは、SAML SSO 接続が両方の側で正しく設定されていることを確認します。

#### PageDNA のテスト ユーザーの作成

これで、Britta Simon というユーザーが PageDNA に作成されました。 このユーザーを作成するために、何かをする必要はありません。 PageDNA では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 PageDNA に Britta Simon というユーザーがまだ存在しない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる PageDNA サインオン URL にリダイレクトされます。
- PageDNA のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [PageDNA] タイルを選択すると、このオプションは PageDNA のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pagerduty-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に PagerDuty を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pagerduty-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と PagerDuty 間にシングル サインオンを構成する方法について学習します。

この記事では、PagerDuty と Microsoft Entra ID を統合する方法について説明します。 PagerDuty を Microsoft Entra ID と統合すると、次のことができます。

- PagerDuty にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って PagerDuty に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PagerDuty でのシングル サインオン (SSO) が有効なサブスクリプション。

注

Microsoft Entra ID で MFA またはパスワードレス認証を使用している場合は、SAML 要求の AuthnContext 値をオフにします。 それ以外の場合、Microsoft Entra ID は AuthnContext の不一致でエラーをスローし、トークンをアプリケーションに送り返しません。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PagerDuty では、**SP** によって開始される SSO がサポートされます

### ギャラリーからの PagerDuty の追加

Microsoft Entra ID への PagerDuty の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに PagerDuty を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**PagerDuty**」と入力します。
4. 結果のパネルから **[PagerDuty]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### PagerDuty 用の Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、PagerDuty で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと PagerDuty の関連ユーザーとの間にリンク関係を確立する必要があります。

PagerDuty で Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PagerDuty の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PagerDuty のテストユーザーを作成 - B.Simon に対応するユーザーを PagerDuty で作成し、それを Microsoft Entra の B.Simon にリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**PagerDuty**&gt;**シングルサインオンに**アクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-name>.pagerduty.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-name>.pagerduty.com`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-name>.pagerduty.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには、[PagerDuty クライアント サポート チーム](https://www.pagerduty.com/support/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[PagerDuty のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PagerDuty の SSO の構成

1. 別の Web ブラウザーのウィンドウで、PagerDuty 企業サイトに管理者としてサインインします。
2. 上部のメニューで、[ **アカウント設定]** を選択します。

    [Image: アカウント設定]
3. [ **シングル サインオン] を選択します**。

    [Image: シングル サインオン]
4. **[シングル サインオンの有効化 (SSO)]** ページで、次の手順に従います。

    [Image: シングル サインオンの有効化]

    ある。 Azure Portal からダウンロードされた Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーしてから、それを **[X.509 Certificate]** ボックスに貼り付けます

    b。 [ **ログイン URL** ] ボックスに、 **ログイン URL を**貼り付けます。

    c. [ **ログアウト URL** ] ボックスに、 **ログアウト URL を**貼り付けます。

    d. **[Allow username/password login]** (ユーザー名/パスワードによるログインを許可) を選択します。

    え **[Require EXACT authentication context comparison]** (認証コンテキストの正確な比較を要求する) チェック ボックスを選択します。

    f. [ **変更の保存] を選択します**。

#### PagerDuty のテスト ユーザーの作成

Microsoft Entra ユーザーが PagerDuty にサインインできるようにするには、そのユーザーを PagerDuty にプロビジョニングする必要があります。 PagerDuty の場合、プロビジョニングは手動で行います。

注

PagerDuty から提供されている他の PagerDuty ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. **PagerDuty** テナントにサインインします。
2. 上部のメニューで、[ユーザー] を選択 **します**。
3. **[ユーザーの追加]** を選択します。

    [Image: ユーザー追加]
4. **[Invite your team]** ダイアログ ボックスで、次の手順を実行します。

    [Image: チームの招待]

    ある。 ユーザーの**氏名** (**B.Simon** など) を入力します。

    b。 ユーザーの**電子メール** アドレス (**b.simon@contoso.com** など) を入力します。

    c. [ **追加]** を選択し、[ **招待の送信**] を選択します。

    注

    PagerDuty アカウントを作成すると、追加したすべてのユーザーが招待を受信します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる PagerDuty のサインオン URL にリダイレクトされます。
- PagerDuty のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [PagerDuty] タイルを選択すると、このオプションは PagerDuty のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/palantir-foundry-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Palantir Foundry を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/palantir-foundry-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Palantir Foundry の間のシングル サインオンを構成する方法について説明します。

この記事では、Palantir Foundry と Microsoft Entra ID を統合する方法について説明します。 Palantir Foundry を Microsoft Entra ID と統合すると、次のことができます。

- Palantir Foundry にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Palantir Foundry に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理する。

Palantir Foundry は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Palantir Foundry でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者に加え、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができる。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Palantir Foundry では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Palantir Foundry では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーから Palantir Foundry を追加する

Microsoft Entra ID への Palantir Foundry の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Palantir Foundry を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Palantir Foundry**」と入力します。
4. 結果パネルから **[Palantir Foundry]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO を Palantir Foundry 向けに構成してテストする

**B.Simon** というテスト ユーザーを使用して、Palantir Foundry に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Palantir Foundry の関連ユーザーとの間にリンク関係を確立する必要があります。

Palantir Foundry に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO の構成**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Palantir Foundry SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Palantir Foundry のテスト ユーザーの作成** - Palantir Foundry で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Palantir Foundry**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[メタデータ ファイルをアップロードする]** を選択して **[Palantir Foundry SSO を構成する]** セクションでダウンロードしたメタデータ ファイルを選択し、**[追加]** を選択します。

    [Image: アップロードのメタデータを閲覧するスクリーンショット。]
6. メタデータ ファイルが正常にアップロードされると、**[識別子]**、**[応答 URL]**、および **[ログアウト URL]** の値が、Palantir Foundry セクションのテキスト ボックスに自動的に設定されます。

    注

    **[識別子]**、**[応答 URL]**、および **[ログアウト URL]** の値が自動的に表示されない場合は、Foundry コントロール パネルにある値を手動で入力してください。
7. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Palantir Foundry SSO を構成する

1. Foundry コントロール パネルで、[ **認証** ] タブに移動し、[ **SAML プロバイダーの追加]** を選択します。

    [Image: S A M L プロバイダーを追加するスクリーンショット。]
2. 有効な SAML プロバイダー名を指定し、[ **作成**] を選択します。
3. **[SAML**] セクションで [**管理**] を選択します。
4. **[SAML]** セクションで、次の手順を実行します。

    [Image: SAM 構成を追加するスクリーンショット。]

    a. [SAML 統合メタデータ] セクションで、**SAML 統合メタデータ XML** をダウンロードし、コンピューターにファイルとして保存します。

    b。 [ID プロバイダーのメタデータ] セクションで、[ **参照** ] を選択して、ダウンロードした **フェデレーション メタデータ XML** ファイルをアップロードします。

    c. **保存** を選択します。

#### Palantir Foundry のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Palantir Foundry に作成します。 Palantir Foundry では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Palantir Foundry にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Palantir Foundry のサインオン URL にリダイレクトされます。
- Palantir Foundry のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Palantir Foundry に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Palantir Foundry] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Palantir Foundry に自動的にサインインされます。 詳細については、[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/palo-alto-networks-cloud-identity-engine---cloud-authentication-service-tutorial"} -->
## Microsoft Entra ID とのシングルサインオンのための Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/palo-alto-networks-cloud-identity-engine---cloud-authentication-service-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service の間でシングル サインオンを構成する方法について説明します。

この記事では、Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service と Microsoft Entra ID を統合する方法について説明します。 Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service を Microsoft Entra ID と統合すると、次のことができるようになります。

- Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service のシングルサインオン (SSO) に対応しているサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service では、**SP** によって開始される SSO がサポートされています。
- Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service では、**Just In Time** ユーザー プロビジョニングがサポートされています。

### Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service をギャラリーから追加する

Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service の Microsoft Entra ID の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Palo Alto Networks cloud Identity Engine - cloud Authentication Service**」と入力します。
4. 結果パネルで **Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service** を選択し、このアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service で Microsoft Entra SSO を構成し、テストします。 SSO を機能させるために、Microsoft Entra ユーザーと Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service の関連ユーザーの間にリンク関係を確立する必要があります。

Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service で Microsoft Entra SSO を構成し、テストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service の SSO を構成する**- アプリケーション側のシングル サインオン設定を構成します。
    1. **Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service テスト ユーザーの作成** - Microsoft Entra のユーザー表示にリンクさせるために、Palo Alto Networks Cloud Identity Engine で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    エー。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションで **識別子** の値が自動的に設定されます。

    d. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<RegionUrl>.paloaltonetworks.com/sp/acs`

    注

    **識別子**の値が自動的に設定されない場合は、要件に従って値を手動で入力してください。 サインオン URL の値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには、[Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service クライアント サポート チーム](mailto:support@paloaltonetworks.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service アプリケーションでは、特定の形式の SAML アサーションを受け取ることが想定されるため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. 上記に加えて、Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service アプリケーションでは、以下に示すように、SAML 応答で返される属性がさらにいくつかあることが想定されます。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | ユーザー.グループ |
    | ユーザー名 | ユーザー.ユーザープリンシパルネーム |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service SSO を構成する

1. ご自分の Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service 企業サイトに管理者としてログインします。
2. **[認証**&gt;**識別子プロバイダー**] に移動し、[**ID プロバイダーの追加] を選択します**。

    [Image: アカウント]
3. **[Set Up SAML Authentication](SAML 認証の設定)** ページで、次の手順を行います。

    [Image: 認証]

    エー。 手順 1 で 、[ **SP メタデータのダウンロード** ] を選択してメタデータ ファイルをダウンロードし、コンピューターに保存します。

    b。 ステップ 2 で、必須フィールドに入力して、以前にコピーしておいた **ID プロバイダー プロファイルを構成**します。

    c. 手順 3 で、[ **SAML セットアップのテスト** ] を選択してプロファイルの構成を確認し、 **IDP で MFA が有効になっていることを**選択します。

    [Image: SAML のテスト]

    注

    **Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service SSO を**テストするには、**Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service コンソールを**開き、[**テスト接続**] ボタンを選択し、「**Microsoft Entra テスト ユーザーの作成**」セクションで作成したテスト アカウントを使用して認証します。

    d. 手順 4 でユーザー **名属性** を入力し、[ **送信]** を選択します。

    [Image: SAML 属性]

#### Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service テスト ユーザーを作成する

このセクションでは、**Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service** で Britta Simon というユーザーが作成されます。 **Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service** では、Just-In-Time ユーザープロビジョニングがサポートされています。この設定は、既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーが **Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service** にまだ存在しない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

**Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service SSO を**テストするには、**Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service コンソールを**開き、[**テスト接続**] ボタンを選択し、「**Microsoft Entra テスト ユーザーの作成**」セクションで作成したテスト アカウントを使用して認証します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/palo-alto-networks-cloud-identity-engine-provisioning-tutorial"} -->
## Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service を構成して、Microsoft Entra ID を使用した自動ユーザー プロビジョニングを行う - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/palo-alto-networks-cloud-identity-engine-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra ID から Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service と Microsoft Entra ID の両方で実行して、自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成すると、Microsoft Entra ID では、Microsoft Entra プロビジョニング サービスを使用して、[Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service](https://www.paloaltonetworks.com/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service でユーザーを作成する。
- アクセスが不要になった場合は、Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service のユーザーを削除します。
- Microsoft Entra ID と Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service の間でユーザー属性の同期を維持する。
- Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service でグループとグループ メンバーシップをプロビジョニングする。
- Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/palo-alto-networks-cloud-identity-engine---cloud-authentication-service-tutorial)する (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者権限を持つ Palo Alto Networks のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service の間でマッピングする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID でのプロビジョニングをサポートするために Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service を構成する

[SCIM URL](https://support.paloaltonetworks.com/support) と対応する**トークン**を取得するには、**Palo Alto Networks カスタマー サポート**にお問い合わせください。

### 手順 3: Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service を Microsoft Entra アプリケーション ギャラリーから追加する

Microsoft Entra アプリケーション ギャラリーから Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service を追加して、Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service へのプロビジョニングの管理を開始します。 過去に Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service で SSO を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service に自動的なユーザーのプロビジョニングを構成する

このセクションでは、Microsoft Entra プロビジョニング サービスを構成し、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service のユーザーやグループを作成、更新、無効化する手順について説明します。

#### Microsoft Entra ID で Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service に自動的なユーザーのプロビジョニングを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で、**Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service** を選択します。

    [Image: アプリケーションの一覧の Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service のテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service に接続できることを確認します。 接続に失敗した場合は、Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service による要件 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | emails[タイプ eq "その他"].値 | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | name.formatted | 糸 |  | ✓ |
    | name.honorificSuffix | 糸 |  |  |
    | name.honorificPrefix | 糸 |  |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | アドレス[タイプ eq "other"].フォーマット済み | 糸 |  |  |
    | addresses[type eq "その他"].streetAddress | 糸 |  |  |
    | 住所[タイプ eq "その他"].市区町村 | 糸 |  |  |
    | addresses[type eq "その他"].地域 | 糸 |  |  |
    | 住所[タイプ eq "その他"].郵便番号 | 糸 |  |  |
    | 住所[タイプ eq "その他"].国 | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | phoneNumbers[type eq "ファックス"].value | 糸 |  |  |
    | externalId | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |  |

    注

    このアプリでは**、スキーマ検出**が有効になっています。 そのため、上記の表で説明したよりも多くの属性がアプリケーションに表示される場合があります。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Palo Alto Networks Cloud Identity Engine - Cloud Authentication Service による要件 |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/palo-alto-networks-globalprotect-tutorial"} -->
## Palo Alto Networks - GlobalProtect for Single sign-on を Microsoft Entra ID で構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/palo-alto-networks-globalprotect-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Palo Alto Networks - GlobalProtect 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、Palo Alto Networks - GlobalProtect と Microsoft Entra ID を統合する方法について説明します。 Palo Alto Networks - GlobalProtect を Microsoft Entra ID と統合すると、次のことができます。

- Palo Alto Networks - GlobalProtect にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Palo Alto Networks - GlobalProtect に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Palo Alto Networks - GlobalProtect でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Palo Alto Networks - GlobalProtect では、**SP** によって開始される SSO がサポートされます
- Palo Alto Networks - GlobalProtect では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Palo Alto Networks - GlobalProtect の追加

Microsoft Entra ID への Palo Alto Networks - GlobalProtect の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Palo Alto Networks - GlobalProtect を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Palo Alto Networks - GlobalProtect**」と入力します。
4. 結果パネルで **[Palo Alto Networks - GlobalProtect]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Palo Alto Networks - GlobalProtect の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Palo Alto Networks - GlobalProtect に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Palo Alto Networks - GlobalProtect の関連ユーザーとの間にリンク関係を確立する必要があります。

Palo Alto Networks - GlobalProtect に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Palo Alto Networks - GlobalProtect SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Palo Alto Networks - GlobalProtect テストユーザーを作成** - Microsoft Entra ID で表現された B.Simon に対応する Palo Alto Networks - GlobalProtect のユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Palo Alto Networks - GlobalProtect**&gt;**シングルサインオン**操作する。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<Customer Firewall URL>:443/SAML20/SP`

    b。 **[応答 URL (Assertion Consumer Service URL)]** テキスト ボックスに、`https://<Customer Firewall URL>/SAML20/SP/ACS` というパターンを使用して URL を入力します。

    c. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<Customer Firewall URL>`

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには、[Palo Alto Networks - GlobalProtect クライアント サポート チーム](https://support.paloaltonetworks.com/support)に連絡してください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、**[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、XML ファイル (これにも SAML 証明書が含まれています) をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Palo Alto Networks - GlobalProtect のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Palo Alto Networks - GlobalProtect の SSO の構成

1. 別のブラウザー ウィンドウで Palo Alto Networks - GlobalProtect を管理者として開きます。
2. [ **デバイス] を選択します**。

    [Image: Palo Alto シングル サインオンの構成 1]
3. 左側のナビゲーション バーから **SAML ID プロバイダー** を選択し、[インポート] を選択してメタデータ ファイルをインポートします。

    [Image: Palo Alto シングル サインオンの構成 2]
4. [Import](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インポート) ウィンドウで次の操作を実行します。

    [Image: Palo Alto シングル サインオンの構成 3]

    ある。 **[プロファイル名]** ボックスに名前 (「Microsoft Entra GlobalProtect」など) を入力します。

    b。 **[ID プロバイダー メタデータ**] で、[**参照**] を選択し、Microsoft Entra 管理センターからダウンロードしたフェデレーション メタデータ XML ファイルを選択します。

    c. **省略可能:** ID プロバイダー証明書の検証をオフにします。 チェックした場合、Microsoft Entra の証明書もファイアウォールにアップロードする必要があります。

    d. **[OK]** を選択します。

    注

    エラー メッセージ `Failed to parse IDP Metadata` でアップロードが失敗した場合は、システムに必要な管理者権限があり、プロファイル名が 31 文字を超えていないことを確認します。

#### Palo Alto Networks - GlobalProtect テスト ユーザーの作成

このセクションでは、Palo Alto Networks - GlobalProtect で B.Simon というユーザーを作成します。 Palo Alto Networks - GlobalProtect では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Palo Alto Networks - GlobalProtect にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Palo Alto Networks - GlobalProtect のサインオン URL にリダイレクトされます。
- Palo Alto Networks - GlobalProtect のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Palo Alto Networks - GlobalProtect] タイルを選択すると、SSO を設定した Palo Alto Networks - GlobalProtect に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/palo-alto-networks-scim-connector-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Palo Alto Networks SCIM Connector を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/palo-alto-networks-scim-connector-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra ID から Palo Alto Networks SCIM Connector に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Palo Alto Networks SCIM Connector と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Palo Alto Networks SCIM Connector](https://www.paloaltonetworks.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Palo Alto Networks SCIM Connector でユーザーを作成する。
- アクセスが不要になったら、Palo Alto Networks SCIM Connector のユーザーを削除します。
- Microsoft Entra ID と Palo Alto Networks SCIM Connector の間でユーザー属性の同期を維持する。
- Palo Alto Networks SCIM Connector でグループとグループ メンバーシップをプロビジョニングする。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者権限を持つ Palo Alto Networks のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Palo Alto Networks SCIM Connector の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID でのプロビジョニングをサポートするように Palo Alto Networks SCIM Connector を構成する

[SCIM URL](https://support.paloaltonetworks.com/support) と対応する**トークン**を取得するには、**Palo Alto Networks カスタマー サポート**にお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Palo Alto Networks SCIM Connector を追加する

Microsoft Entra アプリケーション ギャラリーから Palo Alto Networks SCIM Connector を追加して、Palo Alto Networks SCIM Connector へのプロビジョニングの管理を開始します。 以前に Palo Alto Networks SCIM Connector を SSO 用に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Palo Alto Networks SCIM Connector への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Palo Alto Networks SCIM Connector でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Palo Alto Networks SCIM Connector に対して自動ユーザー プロビジョニングを構成するには、次のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。

    [Image: エンタープライズアプリケーションブレード]
3. アプリケーションの一覧で Palo **Alto Networks SCIM Connector** を選択します。

    [Image: アプリケーションの一覧の Palo Alto Networks SCIM Connector のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Palo Alto Networks SCIM コネクタのテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Palo Alto Networks SCIM Connector に接続できることを確認します。 接続に失敗した場合は、Palo Alto Networks SCIM Connector アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Palo Alto Networks SCIM Connector に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Palo Alto Networks SCIM Connector のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Palo Alto Networks SCIM Connector API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Palo Alto Networks SCIM Connector で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | タイトル | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | emails[type eq "other"].value | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | name.formatted | 糸 |  | ✓ |
    | name.honorificSuffix | 糸 |  |  |
    | name.honorificPrefix | 糸 |  |  |
    | addresses[type eq "work"].formatted | 糸 |  |  |
    | addresses[type eq "work"].streetAddress | 糸 |  |  |
    | addresses[type eq "work"].locality | 糸 |  |  |
    | addresses[type eq "work"].region | 糸 |  |  |
    | addresses[type eq "work"].postalCode | 糸 |  |  |
    | addresses[type eq "work"].country | 糸 |  |  |
    | addresses[type eq "other"].formatted | 糸 |  |  |
    | addresses[type eq "other"].streetAddress | 糸 |  |  |
    | 住所[タイプ eq "その他"].市区町村 | 糸 |  |  |
    | addresses[type eq "other"].region | 糸 |  |  |
    | addresses[type eq "other"].postalCode | 糸 |  |  |
    | addresses[type eq "other"].country | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | phoneNumbers[type eq "fax"].value | 糸 |  |  |
    | externalId | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |  |

    注

    このアプリでは**、スキーマ検出**が有効になっています。 そのため、上記の表で説明したよりも多くの属性がアプリケーションに表示される場合があります。
12. [属性マッピング] セクションで、Microsoft Entra ID から Palo Alto Networks SCIM Connector に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Palo Alto Networks SCIM Connector のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Palo Alto Networks SCIM Connector に必要な項目 |
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
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/paloaltoadmin-tutorial"} -->
## Palo Alto Networks - Admin UI を Microsoft Entra ID でシングル サインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/paloaltoadmin-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Palo Alto Networks - Admin UI 間にシングル サインオンを構成する方法について学習します。

この記事では、Palo Alto Networks - Admin UI と Microsoft Entra ID を統合する方法について説明します。 Palo Alto Networks - Admin UI を Microsoft Entra ID と統合すると、次のことができます:

- Palo Alto Networks - Admin UI にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Palo Alto Networks - Admin UI に自動的にサインインできる。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Palo Alto Networks - Admin UI でのシングル サインオン (SSO) が有効なサブスクリプション。
- これは、サービスをパブリックに利用できるようにする必要がある要件です。 詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)のページをご覧ください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Palo Alto Networks - Admin UI では、**SP** Initiated SSO がサポートされます。
- Palo Alto Networks - Admin UI では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Palo Alto Networks - Admin UI の追加

Microsoft Entra ID への Palo Alto Networks - Admin UI の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Palo Alto Networks - Admin UI を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Palo Alto Networks - Admin UI**」と入力します。
4. 結果パネルで **[Palo Alto Networks - Admin UI]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Palo Alto Networks - Admin UI の Microsoft Entra SSO の構成とテスト

このセクションでは、**B.Simon** というテスト ユーザーに基づいて、Palo Alto Networks - Admin UI で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Palo Alto Networks - Admin UI 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Palo Alto Networks - Admin UI で Microsoft Entra シングル サインオンを構成してテストするには、次の手順を行います:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Palo Alto Networks - Admin UI の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Palo Alto Networks - Admin UI テスト ユーザーの作成** - Palo Alto Networks - Admin UI で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Palo Alto Networks - Admin UI**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://<Customer Firewall FQDN>:443/SAML20/SP` という形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<Customer Firewall FQDN>:443/SAML20/SP/ACS` という形式を使用して、Assertion Consumer Service (ACS) URL を入力します。

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer Firewall FQDN>/php/login.php`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Palo Alto Networks - Admin UI クライアント サポート チーム](https://support.paloaltonetworks.com/support)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。

    **[識別子]** と **[応答 URL]** では、ポート 443 が必須となります。これらの値は Palo Alto Firewall にハードコーディングされています。 ポート番号を削除すると、削除された場合、ログイン中にエラーが発生します。

>
> **[識別子]** と **[応答 URL]** では、ポート 443 が必須となります。これらの値は Palo Alto Firewall にハードコーディングされています。 ポート番号を削除すると、削除された場合、ログイン中にエラーが発生します。
6. Palo Alto Networks - Admin UI アプリケーションでは、特定の形式の SAML アサーションが求められます。そのため、カスタム属性マッピングを SAML トークン属性構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]

    注

    属性の値はサンプルです。*username* と *adminrole* には適切な値をマップしてください。 もう 1 つの省略可能な属性 *accessdomain* は、ファイアウォール上の特定の仮想システムへの管理者アクセスを制限するために使用されます。
7. その他に、Palo Alto Networks - Admin UI アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユーザー名 | ユーザー.ユーザープリンシパルネーム |
    | 管理者ロール | カスタムアドミン |
    |  |  |

    注

    上記で **adminrole** として示されている*名前*の値は、「 *Palo Alto Networks - Admin UI の SSO の構成* 」セクションの手順 12 で構成される "**管理者ロールの属性**" と同じ値である必要があります。 上記**の customadmin** として示されている*ソース属性値*は、*管理者ロール プロファイル名*と同じ値にする必要があります。この値は、「**Palo Alto Networks - Admin UI SSO の構成**」セクションの手順 9 で構成されています。

    注

    これらの属性の詳細については、次の記事を参照してください。

    - [Admin UI の 管理ロール プロファイル (adminrole)](https://docs.paloaltonetworks.com/pan-os/8-1/pan-os-admin/firewall-administration/manage-firewall-administrators/configure-an-admin-role-profile)
    - [Admin UI の デバイス アクセス ドメイン (accessdomain)](https://docs.paloaltonetworks.com/pan-os/8-0/pan-os-web-interface-help/device/device-access-domain.html)
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Palo Alto Networks - Admin UI のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Palo Alto Networks - Admin UI の SSO の構成

1. 新しいウィンドウで Palo Alto Networks Firewall Admin UI を管理者として開きます。
2. [ **デバイス** ] タブを選択します。

    [Image: [Device](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/デバイス) タブを示すスクリーンショット。]
3. 左側のウィンドウで **[SAML Identity Provider](SAML ID プロバイダー)** を選択し、 **[Import](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インポート)** を選択してメタデータ ファイルをインポートします。

    [Image: メタデータ ファイルの [Import](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インポート) ボタンを示すスクリーンショット。]
4. **[SAML Identify Provider Server Profile Import](SAML ID プロバイダー サーバー プロファイル インポート)** ウィンドウで、次の手順を実行します。

    [Image: [SAML Identify Provider Server Profile Import](SAML ID プロバイダー サーバー プロファイル インポート) ウィンドウを示すスクリーンショット。]

    ある。 **[プロファイル名]** ボックスに名前 (「**AzureAD Admin UI**」など) を入力します。

    b。 **[Identity Provider Metadata] (ID プロバイダー メタデータ)** の下で **[参照]** を選択して、前の手順でダウンロードした metadata.xml ファイルを選択します。

    c. **[Validate Identity Provider Certificate](ID プロバイダー証明書の検証)** チェック ボックスをオフにします。

    d. **[OK] を選択**.

    え ファイアウォールの構成を確定するには **[Commit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/コミット)** を選択します。
5. 左側のウィンドウで、**[SAML ID プロバイダー]** を選択し、前の手順で作成した SAML ID プロバイダー プロファイル (**[AzureAD Admin UI]** など) を選択します。

    [Image: SAML ID プロバイダー プロファイルを示すスクリーンショット]
6. **[SAML Identify Provider Server Profile](SAML ID プロバイダー サーバー プロファイル)** ウィンドウで、次の手順を実行します。

    [Image: [SAML Identity Provider Server Profile](SAML ID プロバイダー サーバー プロファイル) ウィンドウを示すスクリーンショット。]

    ある。 **[Identity Provider SLO URL](ID プロバイダー SLO URL)** ボックスで、前の手順でインポートした SLO URL を `https://login.microsoftonline.com/common/wsfederation?wa=wsignout1.0` で置き換えます。

    b。 **[OK] を選択**.
7. Palo Alto Networks Firewall の Admin UI で、 **[Device](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/デバイス)** をクリックし、 **[Admin Roles](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者ロール)** を選択します
8. **[追加]** ボタンを選びます。
9. **[Admin Role Profile](管理者ロール プロファイル)** ウィンドウの **[名前]** ボックスに管理者ロールの名前 (たとえば **fwadmin**) を入力します。 この管理者ロール名は、ID プロバイダーから送信された SAML 管理者ロール属性名と一致する必要があります。 管理者ロールの名前と値は、**[ユーザー属性]** セクションで作成されています。

    [Image: Palo Alto Networks の管理者ロールの構成。]
10. Firewall の Admin UI で、 **[Device](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/デバイス)** をクリックし、 **[Authentication Profile](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証プロファイル)** を選択します。
11. **[追加]** ボタンを選びます。
12. **[Authentication Profile](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証プロファイル)** ウィンドウで、次の手順を実行します。

    [Image: [Authentication Profile](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証プロファイル) ウィンドウを示すスクリーンショット。]

    ある。 **[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前)** ボックスに名前 (「**AzureSAML\_Admin\_AuthProfile**」など) を入力します。

    b。 **[Type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/種類)** ドロップダウン リストで、 **[SAML]** を選択します。

    c. **[IdP サーバー プロファイル]** ドロップダウン リストで適切な SAML ID プロバイダー サーバーのプロファイル (**[AzureAD Admin UI]** など) を選択します。

    d. **[Enable Single Logout](シングル ログアウトを有効にする)** チェック ボックスをオンにします

    え **[Admin Role Attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者ロール属性)** ボックスに属性名 (「**adminrole**」など) を入力します。

    f. **[Advanced](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定)** タブを選択してから、 **[Allow List](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/許可リスト)** で **[Add](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加)** を選択します。

    [Image: [Advanced](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定) タブの [Add](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加) ボタンを示すスクリーンショット。]

    ジー **[All](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/すべて)** チェック ボックスをオンにするか、このプロファイルで認証できるユーザーとグループを選択します。 ユーザーが認証すると、ファイアウォールは関連するユーザー名またはグループをこの一覧のエントリと照合します。 エントリを追加しないと、ユーザーは認証できません。

    h. **[OK] を選択**.
13. 管理者が Azure を使用して SAML SSO を使用できるようにするには、 **[Device](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/デバイス)**&gt;**[Setup](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セットアップ)** を選択します。 **[Setup](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セットアップ)** ウィンドウで **[Management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理)** タブを選択してから、 **[Authentication Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の設定)** で**設定** ("歯車") ボタンを選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) ボタンを示すスクリーンショット。]
14. [Authentication Profile](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証プロファイル) ウィンドウで作成した SAML 認証プロファイル (たとえば、**AzureSAML\_Admin\_AuthProfile**) を選択します。

    [Image: [Authentication Profile](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証プロファイル) フィールドを示すスクリーンショット。]
15. **[OK] を選択**.
16. 構成をコミットするには **[Commit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/コミット)** を選択します。

#### Palo Alto Networks - Admin UI のテスト ユーザーの作成

Palo Alto Networks - Admin UI では、Just-In-Time ユーザー プロビジョニングがサポートされます。 ユーザーがまだ存在しない場合は、認証が成功した後にシステムに自動的に作成されます。 ユーザーを作成する操作は不要です。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Palo Alto Networks - Admin UI のサインオン URL にリダイレクトされます。
- Palo Alto Networks - Admin UI のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Palo Alto Networks - Admin UI] タイルを選択すると、SSO を設定した Palo Alto Networks - Admin UI に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/paloaltonetworks-aperture-tutorial"} -->
## Palo Alto Networks - Aperture for Single sign-on を Microsoft Entra ID で構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/paloaltonetworks-aperture-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Palo Alto Networks - Aperture 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、Palo Alto Networks - Aperture と Microsoft Entra ID を統合する方法について説明します。 Palo Alto Networks - Aperture を Microsoft Entra ID と統合すると、次のことができます。

- Palo Alto Networks - Aperture にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Palo Alto Networks - Aperture に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Palo Alto Networks - Aperture でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Palo Alto Networks - Aperture では、**SP** および **IDP** Initiated SSO がサポートされます。

### ギャラリーからの Palo Alto Networks - Aperture の追加

Microsoft Entra ID への Palo Alto Networks - Aperture の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Palo Alto Networks - Aperture を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Palo Alto Networks - Aperture**」と入力します。
4. 結果パネルで **[Palo Alto Networks - Aperture]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO の構成とテスト

このセクションでは、**B.Simon** というテスト ユーザーに基づいて、Palo Alto Networks - Aperture で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Palo Alto Networks - Aperture 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Palo Alto Networks - Aperture で Microsoft Entra シングル サインオンを構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Configure Palo Alto Networks - Aperture の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Palo Alto Networks - Aperture テストユーザーの作成** - Microsoft Entra のユーザーである Britta Simon に対応するユーザーを Palo Alto Networks - Aperture に作成し、リンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Palo Alto Networks - Aperture**&gt;**シングルサインオン**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    えー。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.aperture.paloaltonetworks.com/d/users/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.aperture.paloaltonetworks.com/d/users/saml/auth`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.aperture.paloaltonetworks.com/d/users/saml/sign_in`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Palo Alto Networks - Aperture クライアント サポート チーム](https://live.paloaltonetworks.com/t5/custom/page/page-id/Support)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Palo Alto Networks - Aperture の設定]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Palo Alto Networks - Aperture の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Palo Alto Networks - Aperture に管理者としてログインします。
2. 上部のメニュー バーで、[ **設定]** を選択します。

    [Image: [設定] タブ]
3. **[アプリケーション**] セクションに移動し、メニューの左側にある **[認証**] を選択します。

    [Image: [認証] タブ]
4. **[認証]** ページで、次の手順を実行します。

    [Image: [認証] タブ]

    えー。 **[Single Sign-On](シングル サインオン)** フィールドの **[Enable Single Sign-On(Supported SSP Providers are Okta, Onelogin)](シングル サインオンを有効にする (サポートされている SSP プロバイダーは Okta、Onelogin))** をオンにします。

    b。 **[ID プロバイダー ID]** テキストボックスに **Microsoft Entra 識別子** の値を貼り付けます。

    c. [ **ファイルの選択] を選択** して、[ **ID プロバイダー** 証明書] フィールドに Microsoft Entra ID からダウンロードした証明書をアップロードします。

    d. **[Identity Provider SSO URL] (ID プロバイダーの SSO URL)** テキスト ボックスに、**ログイン URL** の値を貼り付けます。

    え **[Aperture 情報]** セクションで IdP 情報を確認し、**[Aperture キー]** フィールドから証明書をダウンロードします。

    f. **保存** を選択します。

#### Palo Alto Networks - Aperture テスト ユーザーの作成

このセクションでは、Palo Alto Networks - Aperture で Britta Simon というユーザーを作成します。 [Palo Alto Networks - Aperture Client サポート チーム](https://live.paloaltonetworks.com/t5/custom/page/page-id/Support)と協力し合い、Palo Alto Networks - Aperture プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Palo Alto Networks - Aperture のサインオン URL にリダイレクトされます。
- Palo Alto Networks - Aperture のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Palo Alto Networks - Aperture に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Palo Alto Networks - Aperture タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Palo Alto Networks - Aperture に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/paloaltonetworks-captiveportal-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Palo Alto Networks Captive Portal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/paloaltonetworks-captiveportal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-09
- Summary: Microsoft Entra ID と Palo Alto Networks Captive Portal 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、Palo Alto Networks Captive Portal と Microsoft Entra ID を統合する方法について説明します。 Palo Alto Networks Captive Portal と Microsoft Entra ID の統合には、次のメリットがあります。

- Palo Alto Networks Captive Portal にアクセスするユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで Palo Alto Networks Captive Portal に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) に対応した Palo Alto Networks Captive Portal のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Palo Alto Networks Captive Portal では、 **IDP** Initiated SSO がサポートされます
- Palo Alto Networks Captive Portal では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Palo Alto Networks Captive Portal の追加

Microsoft Entra ID への Palo Alto Networks Captive Portal の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Palo Alto Networks Captive Portal を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Palo Alto Networks Captive Portal**」と入力します。
4. 結果パネルから **Palo Alto Networks Captive Portal** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO の構成とテスト

このセクションでは、 **B.Simon** というテスト ユーザーに基づいて、Palo Alto Networks Captive Portal で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Palo Alto Networks Captive Portal 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Palo Alto Networks Captive Portal で Microsoft Entra シングル サインオンを構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO の構成**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - ユーザー B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - Microsoft Entra シングル サインオンを使用するように B.Simon を設定します。
2. **Palo Alto Networks Captive Portal の SSO**の構成 - アプリケーションでシングル サインオン設定を構成します。
    - **Palo Alto Networks Captive Portal のテスト ユーザーの作成 - Palo Alto Networks Captive Portal** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能することを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Palo Alto Networks Captive Portal**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** ウィンドウで、次の手順を実行します。

    1. **[識別子]** には、パターンが`https://<customer_firewall_host_name>:6082/SAML20/SP` URL を入力します。
    2. [ **応答 URL]** に、パターンが `https://<customer_firewall_host_name>:6082/SAML20/SP/ACS` URL を入力します。

        注

        この手順に示したプレースホルダーの値は、実際の識別子と応答 URL に置き換えてください。 実際の値を取得するには、 [Palo Alto Networks Captive Portal クライアント サポート チーム](https://support.paloaltonetworks.com/support)にお問い合わせください。
6. **[SAML 署名証明書**] セクションで、[**フェデレーション メタデータ XML**] の横にある [**ダウンロード**] を選択します。 ダウンロードしたファイルを、お使いのコンピューターに保存します。

    [Image: フェデレーション メタデータ XML ダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Palo Alto Networks Captive Portal の SSO の構成

次に、Palo Alto Networks Captive Portal 内でシングル サインオンを設定していきます。

1. 別の Web ブラウザー ウィンドウで、Palo Alto Networks の Web サイトに管理者としてログインします。
2. [ **デバイス** ] タブを選択します。

    [Image: Palo Alto Networks Web サイトの [デバイス] タブ]
3. メニューで 、[ **SAML ID プロバイダー] を**選択し、[ **インポート**] を選択します。

    [Image: [インポート] ボタン]
4. **[SAML Identity Provider Server Profile Import]\(SAML ID プロバイダー サーバー プロファイルのインポート**\) ダイアログ ボックスで、次の手順を実行します。

    [Image: Palo Alto Networks シングル サインオンの構成]

    1. [ **プロファイル名]** に、 `AzureAD-CaptivePortal`などの名前を入力します。
    2. **[ID プロバイダー メタデータ]**の横にある**[ブラウズ]**を選択します。 ダウンロードした metadata.xml ファイルを選択します。
    3. [ **OK] を選択します**。

#### Palo Alto Networks Captive Portal のテスト ユーザーの作成

次に、Palo Alto Networks Captive Portal で *Britta Simon* という名前のユーザーを作成します。 Palo Alto Networks Captive Portal では、Just-In-Time のユーザー プロビジョニングがサポートされています。この設定は、既定で有効になっています。 このセクションでは、何も行う必要はありません。 Palo Alto Networks Captive Portal にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

注

ユーザーを手動で作成する場合は、 [Palo Alto Networks Captive Portal クライアント サポート チーム](https://support.paloaltonetworks.com/support)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Palo Alto Networks Captive Portal に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [Palo Alto Networks Captive Portal] タイルを選択すると、SSO を設定した Palo Alto Networks Captive Portal に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pandadoc-tutorial"} -->
## Microsoft Entra ID で PandaDoc for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pandadoc-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と PandaDoc 間にシングル サインオンを構成する方法について学習します。

この記事では、PandaDoc と Microsoft Entra ID を統合する方法について説明します。 PandaDoc と Microsoft Entra ID を統合すると、次のことができます:

- PandaDoc にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って PandaDoc に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- PandaDoc でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PandaDoc は、**SP 開始の SSO および IDP 開始の SSO** をサポートしています。
- PandaDoc では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの PandaDoc の追加

Microsoft Entra ID への PandaDoc の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに PandaDoc を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスしてください。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「PandaDoc**」と入力します。
4. 結果パネルから **PandaDoc** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PandaDoc 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、PandaDoc に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと PandaDoc の関連ユーザーとの間にリンク関係を確立する必要があります。

PandaDoc で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PandaDoc の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PandaDoc のテスト ユーザーの作成 - PandaDoc** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**PandaDoc**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.pandadoc.com/sso-login/`
7. PandaDoc アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
8. その他に、PandaDoc アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | Namespace |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **PandaDoc のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PandaDoc SSO の構成

**PandaDoc** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [PandaDoc サポート チーム](mailto:support@pandadoc.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PandaDoc テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを PandaDoc に作成します。 PandaDoc では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 PandaDoc にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる PandaDoc サインオン URL にリダイレクトされます。
- PandaDoc のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した PandaDoc に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [PandaDoc] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した PandaDoc に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/panopto-tutorial"} -->
## Microsoft Entra ID で Panopto for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/panopto-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Panopto 間にシングル サインオンを構成する方法について学習します。

この記事では、Panopto と Microsoft Entra ID を統合する方法について説明します。 Panopto を Microsoft Entra ID と統合すると、次のことができます。

- Panopto にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Panopto に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Panopto でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Panopto では、 **SP** によって開始される SSO がサポートされます。
- Panopto では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Panopto の追加

Microsoft Entra ID への Panopto の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Panopto を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Panopto**」と入力します。
4. 結果パネルから **Panopto** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Panopto 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Panopto に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Panopto の関連ユーザーとの間にリンク関係を確立する必要があります。

Panopto で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Panopto SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Panopto テストユーザーを作成** - Microsoft Entra における B.Simon の表現にリンクされた Panopto 内の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Panopto**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TENANT_NAME>.panopto.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、Panopto クライアント サポート チーム](mailto:support@panopto.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Panopto のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Panopto の SSO の構成

1. 別の Web ブラウザーのウィンドウで、Panopto 企業サイトに管理者としてログインします。
2. 左側のツール バーで、[ **システム**] を選択し、[ **ID プロバイダー] を選択します**。

    [Image: システム]
3. [ **プロバイダーの追加] を選択します**。

    [Image: アイデンティティプロバイダー]
4. [SAML プロバイダー] セクションで、次の手順に従います。

    [Image: SaaS 構成]

    ある。 [ **プロバイダーの種類]** ボックスの一覧から [ **SAML20**] を選択します。

    b。 [ **インスタンス名]** ボックスに、インスタンスの名前を入力します。

    c. [ **わかりやすい説明** ] ボックスに、わかりやすい説明を入力します。

    d. [ **バウンス ページ URL]** ボックスに、 **ログイン URL** の値を貼り付けます。

    え **[発行者**] ボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    f. Azure portal からダウンロードした base-64 でエンコードされた証明書を開き、その内容をクリップボードにコピーして、 **公開キー** のテキスト ボックスに貼り付けます。
5. **[保存] を選択します**。

#### Panopto のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Panopto に作成します。 Panopto では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Panopto にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

Panopto から提供されている他の Panopto ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Panopto のサインオン URL にリダイレクトされます。
- Panopto のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Panopto] タイルを選択すると、このオプションは Panopto のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/panorama9-tutorial"} -->
## Microsoft Entra ID で Panorama9 for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/panorama9-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Panorama9 間にシングル サインオンを構成する方法について説明します。

この記事では、Panorama9 と Microsoft Entra ID を統合する方法について説明します。 Panorama9 と Microsoft Entra ID を統合すると、次のことができます:

- Panorama9 にアクセスできるユーザー Microsoft Entra ID を制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Panorama9 に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Panorama9 でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Panorama9 では、**SP** Initiated SSO がサポートされます。

### ギャラリーから Panorama9 を追加する

Microsoft Entra ID への Panorama9 の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Panorama9 を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Panorama9**」と入力します。
4. 結果のパネルから **[Panorama9]** を選択してアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Panorama9 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Panorama9 に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Panorama9 の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Panorama9 と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Panorama9 SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Panorama9 テストユーザーを作成** - Microsoft Entra の B.Simon とリンクされた B.Simon の対応ユーザーを Panorama9 に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Panorama9**&gt;**シングルサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[サインオン URL]** ボックスに、URL として「`https://dashboard.panorama9.com/saml/access/3262`」と入力します。

    b。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://www.panorama9.com/saml20/<TENANT_NAME>`

    注意

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、Panorama9 クライアント サポート チーム `https://support.panorama9.com` に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. **[SAML 署名証明書]** セクションで **[Thumbprint](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/拇印)** をコピーし、お使いのコンピューターに保存します。

    [Image: [Thumbprint](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/拇印) の値をコピーする]
8. **[Panorama9 のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Panorama9 SSO の構成

1. 別の Web ブラウザーのウィンドウで、Panorama9 企業サイトに管理者としてサインインします。
2. [ **管理**&gt;**拡張機能**&gt;**シングルサインオン**に移動します。
3. **[設定]** セクションで、次の手順に従います。

    [Image: [設定]]

    ある。 シングル サインオンを有効にします。

    b。 **[Identity URL]** テキストボックスに、**識別子 (エンティティ ID)** の値を貼り付けます。

    c. **[Certificate fingerprint]** テキストボックスに、証明書の**拇印**の値を貼り付けます。
4. [ **変更の保存] を選択します**。

#### Panorama9 テスト ユーザーの作成

Microsoft Entra ユーザーが Panorama9 にサインインできるようにするには、ユーザーを Panorama9 にプロビジョニングする必要があります。

Panorama9 の場合、プロビジョニングは手動で行います。

**ユーザー プロビジョニングを構成するには、次の手順に従います。**

1. **Panorama9** 企業サイトに管理者としてサインインします。
2. [ユーザー] セクションで、**[メール]** テキストボックスにプロビジョニングする有効な Microsoft Entra ユーザーのメール アドレスを入力し、有効な **[名前]** を付けます。

    [Image: Users]
3. **[Create user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成)** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Panorama9 のサインオン URL にリダイレクトされます。
- Panorama9 のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Panorama9] タイルを選択すると、このオプションは Panorama9 のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/panorays-tutorial"} -->
## Microsoft Entra ID で Panorays for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/panorays-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Panorays 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、Panorays と Microsoft Entra ID を統合する方法について説明します。 Panorays と Microsoft Entra ID を統合すると、次のことができます:

- Panorays にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Panorays に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Panorays は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Panorays でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Panorays では、**SP および IDP** によって開始される SSO がサポートされます。
- Panorays では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからパノラマX線画像を追加

Azure AD への Panorays の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Panorays を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Panorays**」と入力します。
4. 結果のパネルから **[Panorays]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Panorays 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Panorays で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Panorays の関連ユーザーとの間にリンク関係を確立する必要があります。

Panorays に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Panorays の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Panorays テストユーザーの作成** - B.Simon に対応するユーザーを Panorays で作成し、それを Microsoft Entra ユーザーとリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Panorays**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を保存する必要があります。
6. Panorays アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、 **[Unique User Identifier](一意のユーザー ID)** は **user.userprincipalname** にマップされています。 Panorays アプリケーションでは、 **一意のユーザー識別子** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Panorays SSO を設定

**Panorays** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Panorays サポート チーム](mailto:support@panorays.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Panorays のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Panorays に作成します。 Panorays では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Panorays にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Panorays のサインオン URL にリダイレクトされます。
- Panorays のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Panorays に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Panorays] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Panorays に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pantheon-tutorial"} -->
## Microsoft Entra ID で Pantheon for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pantheon-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Pantheon 間にシングル サインオンを構成する方法について学習します。

この記事では、Pantheon と Microsoft Entra ID を統合する方法について説明します。 Pantheon を Microsoft Entra ID と統合すると、次のことができます。

- Pantheon にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Pantheon に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pantheon でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Pantheon では、**IDP** によって開始される SSO がサポートされます。

### ギャラリーからの Pantheon の追加

Microsoft Entra ID への Pantheon の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Pantheon を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Pantheon**」と入力します。
4. 結果のパネルから **[Pantheon]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pantheon 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Pantheon で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Pantheon の関連ユーザーとの間にリンク関係を確立する必要があります。

Pantheon で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pantheon SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Pantheon のテストユーザーを作成し、** B.Simon に対応するユーザーとして Pantheon に作成し、Microsoft Entra ユーザーとリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Pantheon**&gt;**シングル サインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[SAML でシングル サインオンをセットアップします]** ページで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`urn:auth0:pantheon:<orgname>-SSO` の形式で値を入力します。

    b。 **[応答 URL]** ボックスに、`https://pantheon.auth0.com/login/callback?connection=<orgname>-SSO` のパターンを使用して URL を入力します

    注意

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Pantheon クライアント サポート チーム](https://pantheon.io/docs/getting-support/)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Pantheon アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 Pantheon アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[Pantheon のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pantheon の SSO の構成

**Pantheon** 側にシングルサインオンを構成するには、ダウンロードされた**証明書 (Base64)** およびコピーされた適切な URL を [Pantheon サポート チーム](https://pantheon.io/docs/getting-support/)に送信する必要があります。

注意

この接続を有効にするには、電子メール ドメイン情報と日時も提供する必要があります。 詳細については、[こちら](https://pantheon.io/docs/sso-organizations/)を参照してください。

#### Pantheon のテスト ユーザーの作成

このセクションでは、Pantheon で B.Simon というユーザーを作成します。 Pantheon でユーザーを追加するには、次の手順に従ってください。

注意

SSO が機能するには、まず Pantheon でユーザーを作成する必要があります。

1. 管理者の資格情報で Pantheon にサインインします。
2. **[組織]** ダッシュボード ページに移動します。
3. **[People]** を選びます。
4. [ **ユーザーの追加] を選択します**。
5. ユーザーの電子メール アドレスを入力します。
6. ユーザーの役割を選択します。
7. [ **ユーザーの追加] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Pantheon に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Pantheon] タイルを選択すると、SSO を設定した Pantheon に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/papercut-cloud-print-management-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に PaperCut Cloud Print Management を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/papercut-cloud-print-management-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-20
- Summary: Microsoft Entra ID から PaperCut Cloud Print Management に対するユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に行う方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために PaperCut Cloud Print Management と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [PaperCut Cloud Print Management](https://www.papercut.com/products/papercut-pocket/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされる機能

- PaperCut Cloud Print Management でユーザーを作成する
- アクセスが不要になった場合に PaperCut Cloud Print Management のユーザーを削除する
- Microsoft Entra ID と PaperCut Cloud Print Management の間でユーザー属性の同期を維持する
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- PaperCut Cloud Print Management 管理者アカウント。

### 手順 1: プロビジョニングデプロイメントの計画を立てる

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と PaperCut Cloud Print Management の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように PaperCut Cloud Print Management を構成する

1. [PaperCut Pocket 管理コンソール](https://pocket.papercut.com/)または [PaperCut Hive 管理コンソール](https://hive.papercut.com/)にサインインします。
2. **アドオン**&gt;**すべてのアドオン**に移動し、**Microsoft Entra ユーザー同期アドオン**を見つけます。
3. [ **詳細情報** ] ボタンを選択し、[ **追加]** を選択してインストールします。
4. インストールすると、アドオンの詳細ページが **テナント URL** と **シークレット トークン**と共に表示されます。 これらの値は、PaperCut Cloud Print Management アプリケーションの [プロビジョニング] タブの [テナント URL] \* フィールドと [シークレット トークン] \* フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから PaperCut Cloud Print Management を追加する

Microsoft Entra アプリケーション ギャラリーから PaperCut Cloud Print Management を追加して、PaperCut Cloud Print Management へのプロビジョニングの管理を開始します。 前に SSO 用に PaperCut Cloud Print Management を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: PaperCut Cloud Print Management への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、PaperCut Cloud Print Management でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で PaperCut Cloud Print Management の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照する

    [Image: エンタープライズ アプリケーションブレード]
3. アプリケーションの一覧で [ **PaperCut Cloud Print Management**] を選択します。

    [Image: アプリケーションの一覧の [PaperCut Cloud Print Management] リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、PaperCut Cloud Print Management テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が PaperCut Cloud Print Management に接続できることを確認します。 接続に失敗した場合は、PaperCut Cloud Print Management アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から PaperCut Cloud Print Management に同期されるユーザー **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で PaperCut Cloud Print Management のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、PaperCut Cloud Print Management API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 表示名 | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/papirfly-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Papirfly SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/papirfly-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Papirfly SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、Papirfly SSO と Microsoft Entra ID を統合する方法について説明します。 Papirfly SSO と Microsoft Entra ID を統合すると、次のことができます。

- Papirfly SSO にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントで Papirfly SSO に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Papirfly SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Papirfly SSO では、 **SP** によって開始される SSO のみがサポートされます。
- Papirfly SSO では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### Papirfly SSO をギャラリーから追加する

Microsoft Entra ID への Papirfly SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Papirfly SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Papirfly SSO**」と入力します。
4. 結果パネルから **Papirfly SSO を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Papirfly SSO の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Papirfly SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Papirfly SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Papirfly SSO に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Papirfly SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Papirfly SSO テスト ユーザーの作成** - Papirfly SSO において B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Papirfly SSO**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Portal_Domain>/AuthServices`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Portal_Domain>/AuthServices/Acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Portal_Domain>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Papirfly SSO サポート チーム](mailto:support@papirfly.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Papirfly SSO の構成

**Papirfly SSO** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Papirfly SSO サポート チームに](mailto:support@papirfly.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Papirfly SSO テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Papirfly SSO に作成します。 Papirfly SSO では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Papirfly SSO にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Papirfly SSO サインオン URL にリダイレクトします。
- Papirfly SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Papirfly SSO] タイルを選択すると、このオプションは Papirfly SSO のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/parallels-desktop-tutorial"} -->
## Microsoft Entra ID で Parallels Desktop for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/parallels-desktop-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Parallels Desktop の間にシングル サインオンを構成する方法について学習します。

この記事では、Parallels Desktop と Microsoft Entra ID を統合する方法について説明します。 従業員が Parallels Desktop を使用するための SSO/SAML 認証。 従業員が会社アカウントで Parallels Desktop にサインインしてアクティブ化できるようにします。 Parallels Desktop と Microsoft Entra ID を統合すると、次のことができます:

- Parallels Desktop にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Parallels Desktop に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Parallels Desktop 用の Microsoft Entra のシングル サインオンを構成してテストします。 Parallels Desktop では、 **SP** によって開始されるシングル サインオンのみがサポートされます。

### [前提条件]

Microsoft Entra ID を Parallels Desktop と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Parallels Desktop でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Parallels Desktop アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Parallels Desktop を追加する

Microsoft Entra アプリケーション ギャラリーから Parallels Desktop を追加して、Parallels Desktop でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Parallels Desktop**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    エー。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://account.parallels.com/<ID>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://account.parallels.com/webapp/sso/acs/<ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 識別子と応答 URL の値はお客様固有であり、Parallels の My Account から ID プロバイダー Azure にコピーして手動で指定できる必要があることに注意してください。 サポートについては [、Parallels Desktop サポート チーム](https://www.parallels.com/support/) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。

    c. [ **サインオン URL** ] ボックスに、URL:- を入力します。 `https://my.parallels.com/login?sso=1`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
7. [ **Parallels Desktop のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Parallels Desktop SSO を構成する

**Parallels Desktop** 側でシングル サインオンを構成するには、[このページ](https://kb.parallels.com/en/129240)の Parallels の Azure SSO セットアップ ガイドの最新バージョンに従ってください。 セットアップ プロセス全体で問題が発生した場合は、 [Parallels Desktop サポート チーム](https://www.parallels.com/support/)にお問い合わせください。

#### Parallels Desktop テスト ユーザーを作成する

[このページ](https://kb.parallels.com/en/129240)にある Parallels の Azure SSO セットアップ ガイドに従って、Microsoft Entra ID 側の管理者またはユーザー グループに既存のユーザー アカウントを追加します。 組織から離れた後にユーザー アカウントが非アクティブ化されると、Parallels の製品ライセンスのユーザー数にすぐに反映されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Parallels Desktop のサインオン URL にリダイレクトされます。
- Parallels Desktop のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Parallels Desktop] タイルを選択すると、このオプションは Parallels Desktop のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/parkable-tutorial"} -->
## Microsoft Entra ID で Parkable for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/parkable-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-13
- Summary: Microsoft Entra IDと Parkable の間でシングル サインオンを構成する方法について説明します。

この記事では、Parkable と Microsoft Entra ID を統合する方法について説明します。 Parkable は、利用率の向上と収益の増加を支援しながら、スタッフ、テナント、利用者のすべてにメリットがもたらされるようにする駐車場管理プラットフォームです。 Parkable と Microsoft Entra ID を統合すると、次のことができます。

- ParkableへのアクセスをMicrosoft Entra IDで制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Parkable に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

テスト環境で Parkable 用の Microsoft Entra のシングル サインオンを構成してテストします。 Parkable では、 **SP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングのみがサポートされます。

### 前提条件

Microsoft Entra IDを Parkable と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Parkable のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Parkable アプリケーションを追加する必要があります。 アプリケーションに割り当て、シングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Parkable を追加する

Microsoft Entra アプリケーション ギャラリーから Parkable を追加して、Parkable でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーを作成して割り当てる

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができます。 このウィザードには、シングル サインオン構成ウィンドウへのリンクも表示されます。 [Microsoft 365 wizards.](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides) の詳細を参照してください。

### Microsoft Entra SSO の構成

Microsoft Entra シングル サインオンを有効にするには、次の手順を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Parkable**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `parkable.com/<ID>`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://parkable-app.firebaseapp.com/__/auth/handler`

    c. [ **サインオン URL** ] ボックスに、URL を入力します。 `https://account.parkable.com`
6. Parkable アプリケーションでは、特定の形式の SAML アサーションが想定されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: トークン属性の画像を示すスクリーンショット。]
7. その他に、Parkable アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を下に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 苗字 | user.姓 |
    | ファーストネーム | user.givenname |
    | メール | user.mail |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### Parkable SSO を構成する

Parkable でシングル サインオンを構成するには、 **Parkable** 管理パネルで説明されている手順に従って、SSO (SAML) のセットアップを続行する必要があります。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Parkable のサインオン URL にリダイレクトされます。
- Parkable のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft My Appsを使用できます。 My Appsで [パーク可能] タイルを選択すると、このオプションは Parkable のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/parkalot-car-park-management-tutorial"} -->
## Microsoft Entra IDのシングルサインオンのためにParkalotの駐車場管理を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/parkalot-car-park-management-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Parkalot - Car park management の間でシングル サインオンを構成する方法について説明します。

この記事では、Parkalot - Car park management と Microsoft Entra ID を統合する方法について説明します。 Parkalot - Car park management と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Parkalot - 駐車場管理へのアクセスを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Parkalot - Car park management に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Parkalot - Car park management でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Parkalot - 駐車場管理は、SP **に対するSSO**のサポートを提供します。
- Parkalot - 駐車場管理は **Just In Time** ユーザープロビジョニングをサポートします

### ギャラリーからParkalotを追加して駐車場を管理する

Microsoft Entra ID への Parkalot - Car park management の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Parkalot - Car park management を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **ギャラリーから追加** セクションで、検索ボックスに **Parkalot - Car park management** を入力します。
4. 結果のパネルから **Parkalot - Car park management** を選択し、アプリを追加してください。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Parkalot の Microsoft Entra SSO の構成とテスト - 駐車場管理

**B.Simon**というテスト ユーザーを使用して、Parkalot - Car park management に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Parkalot - Car park management の関連ユーザーとの間にリンク関係を確立する必要があります。

Parkalot - Car park management に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Parkalot-Car パーク管理 SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **パーク管理テストユーザー Parkalot-Car を作成する** - Parkalot - Car park management で B.Simon に対応するユーザーを作成し、Microsoft Entra でのユーザー表現にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Parkalot - Car park management**&gt;**シングルサインオン**に移動します。
3. [**シングル サインオン方法の選択]** ページで、[SAML 選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集]
5. [**基本的な SAML 構成**] セクションで、次のフィールドの値を入力します。

    エー。 **識別子 (エンティティ ID)** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 (エンティティ ID) |
    | --- |
    | `https://parkalot.io` |
    | `https://<CUSTOMERNAME>.parkalot.io` |
    |  |

    b。 [**応答 URL** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 応答 URL |
    | --- |
    | `https://<CUSTOMERNAME>.parkalot.io` |
    | `https://parkalot-saml.firebaseapp.com/__/auth/handler` |
    | `https://parkalot-saml.web.app/__/auth/handler` |
    | `https://<CustomerName>.parkalot.io/__/auth/handler` |
    |  |

    c. [**サインオン URL** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://<CUSTOMERNAME>.parkalot.io/#/login` |
    | `https://parkalot-saml.firebaseapp.com/#/login` |
    | `https://parkalot-saml.web.app/#/login` |
    |  |

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Parkalot - Car park management クライアント サポート チーム](mailto:contact-us@parkalot.io) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **Parkalot - Car park management** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Parkalot-Car 公園管理 SSO の構成

1. 別の Web ブラウザー ウィンドウで、Parkalot - Car park 管理企業サイトに管理者としてサインインします。
2. **[SAML のセットアップ]** を選択し、[**新しい追加**] カードで **[編集]** アイコンを選択します。

    新しい編集アイコンを追加します。
3. 次のページで、以下の手順を実行します。

    [Image: Parkalot - Car park management SSO を構成します。]

    エー。 [**表示名**] テキストボックスに、有効な名前を入力してください。

    b。 **IdP エンティティ ID テキストボックス** に、前にコピーした Microsoft Entra Identifier 値 **を** 貼り付けます。

    c. **SSO URL** ボックスに、前にコピーした **ログイン URL** 値を貼り付けます。

    d. ダウンロードした **証明書 (Base64)** をメモ帳に開き、**証明書** ボックスに内容を貼り付けます。

    え **[保存] を選択します**。

#### パーク管理テスト ユーザー Parkalot-Car 作成する

このセクションでは、Britta Simon というユーザーを Parkalot - Car park management に作成します。 Parkalot - 駐車場管理では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Parkalot - 駐車場管理にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる Parkalot - Car park management のサインオン URL にリダイレクトされます。
- Parkalot - Car park management のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Parkalot - Car park management] タイルを選択すると、このオプションは Parkalot - Car park management のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/parkhere-corporate-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ParkHere Corporate を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/parkhere-corporate-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ParkHere Corporate 間にシングル サインオンを構成する方法について学習します。

この記事では、ParkHere Corporate と Microsoft Entra ID を統合する方法について説明します。 ParkHere Corporate を Microsoft Entra ID と統合すると、次のことができます。

- ParkHere Corporate にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って ParkHere Corporate に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ParkHere Corporate でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ParkHere Corporate では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーから ParkHere Corporate を追加する

Microsoft Entra ID への ParkHere Corporate の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに ParkHere Corporate を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「ParkHere Corporate」**と入力します。
4. 結果パネルから **ParkHere Corporate** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ParkHere Corporate 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ParkHere Corporate に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ParkHere Corporate の関連ユーザーとの間にリンク関係を確立する必要があります。

ParkHere Corporate で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ParkHere Corporate SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ParkHere Corporate のテストユーザーを作成** - ParkHere Corporate で B.Simon の対応ユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**ParkHere Corporate**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ParkHere Corporate SSO の構成

**ParkHere 企業**側でシングル サインオンを構成するには、**App Federation Metadata URL を**[ParkHere 企業サポート チーム](mailto:support@park-here.eu)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ParkHere Corporate のテスト ユーザーの作成

このセクションでは、ParkHere Corporate で Britta Simon というユーザーを作成します。 [ParkHere 企業サポート チーム](mailto:support@park-here.eu)と協力して、ParkHere 企業プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ParkHere Corporate に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ParkHere Corporate] タイルを選択すると、SSO を設定した ParkHere Corporate に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/parsable-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して自動ユーザー プロビジョニング用に Parsable を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/parsable-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-20
- Summary: Microsoft Entra ID から Parsable に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Parsable ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Parsable](https://www.parsable.com/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Parsable でユーザーを作成する
- アクセスが不要になった場合に Parsable のユーザーを削除する
- Microsoft Entra ID と Parsable の間でユーザー属性の同期を維持する。
- Parsable でグループとグループメンバーシップを設定する
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- パーサブル テナント（チーム）
- 管理者アクセス許可がある Parsable のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Parsable の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Parsable を構成する

1. このプレリリース機能をオプトインするには、Parsable Customer Success の担当者にお問い合わせください。
2. 担当者は、サポート チケットを作成して必要な **ベアラー トークン** (シークレット トークン) を取得するお手伝いをいたします。
3. **[Bearer token] (ベアラー トークン)** をコピーして保存します。 この値は、解析可能アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Parsable を追加する

Microsoft Entra アプリケーション ギャラリーから Parsable を追加して、Parsable へのプロビジョニングの管理を開始します。 SSO のために Parsable を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Parsable への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Parsable の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Parsable]** を選択します。

    [Image: アプリケーションの一覧の Parsable のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、解析可能なテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Parsable に接続できることを確認します。 接続に失敗した場合は、Parsable アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Parsable に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Parsable のユーザー アカウントとの照合に使用されます。 [照合する対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づいたユーザーのフィルター処理が Parsable API で確実にサポートされている必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | ディスプレイ名 | 糸 |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Parsable に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Parsable のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ディスプレイ名 | 糸 | ✓ |
    | メンバー | リファレンス |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

- 2021 年 2 月 15 日 - グループ プロビジョニングが有効になりました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/patentsquare-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に PatentSQUARE を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/patentsquare-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と PatentSQUARE の間にシングル サインオンを構成する方法について説明します。

この記事では、PatentSQUARE と Microsoft Entra ID を統合する方法について説明します。 PatentSQUARE を Microsoft Entra ID と統合すると、以下のことができます。

- PatentSQUARE にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って PatentSQUARE に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- PatentSQUARE でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- PatentSQUARE では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの PatentSQUAR の追加

Microsoft Entra ID への PatentSQUAR の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に PatentSQUAR を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**PatentSQUARE**」と入力します。
4. 結果のパネルから **[PatentSQUARE]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PatentSQUARE 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、PatentSQUARE に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと PatentSQUARE の関連ユーザーとの間にリンク関係を確立する必要があります。

PatentSQUARE との Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PatentSQUARE の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PatentSQUARE のテストユーザーを作成する** - PatentSQUARE において B.Simon に相当するユーザーを作成し、それを Microsoft Entra のユーザー表現とリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**PatentSQUARE**&gt;**シングルサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    エー。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companysubdomain>.pat-dss.com:443/patlics`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companysubdomain>.pat-dss.com:443/patlics/secure/aad`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[PatentSQUARE クライアント サポート チーム](https://www.panasonic.com/jp/business/its/patentsquare.html)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[PatentSQUARE のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PatentSQUARE の SSO の構成

**PatentSQUARE** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [PatentSQUARE サポート チーム](https://www.panasonic.com/jp/business/its/patentsquare.html)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### PatentSQUARE のテスト ユーザーの作成

このセクションでは、PatentSQUAR で Britta Simon というユーザーを作成します。 [PatentSQUARE サポート チーム](https://www.panasonic.com/jp/business/its/patentsquare.html)と連携し、PatentSQUARE プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる PatentSQUARE のサインオン URL にリダイレクトされます。
- PatentSQUARE のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで PatentSQUARE タイルを選択すると、このオプションは PatentSQUARE のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/pavaso-digital-close-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Pavaso Digital Close を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pavaso-digital-close-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Pavaso Digital Close 間にシングル サインオンを構成する方法について説明します。

この記事では、Pavaso Digital Close と Microsoft Entra ID を統合する方法について説明します。 Pavaso Digital Close と Microsoft Entra ID を統合すると、次のことができます:

- Pavaso Digital Close にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Pavaso Digital Close に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Pavaso Digital Close でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Pavaso Digital Close では、**SP および IDP によって開始される SSO** をサポートします。

### ギャラリーから Pavaso Digital Close を追加する

Microsoft Entra ID への Pavaso Digital Close の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Pavaso Digital Close を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Pavaso Digital Close」**と入力します。
4. 結果パネルから **Pavaso Digital Close** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Pavaso Digital Close 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Pavaso Digital Close に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Pavaso Digital Close の関連ユーザーとの間にリンク関係を確立する必要があります。

Pavaso Digital Close に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Pavaso Digital Close SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Pavaso Digital Close テストユーザーの作成** - Microsoft Entra のユーザー表現にリンクされた Pavaso Digital Close 内の B.Simon に対応するテストユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Pavaso Digital Close**&gt;**シングル サインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.pavaso.com/AuthServices`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.pavaso.com/AuthServices/Acs`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.pavaso.com`。

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Pavaso Digital Close クライアント サポート チーム](mailto:support@pavaso.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Pavaso Digital Close のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Pavaso Digital Close SSO を構成する

**Pavaso Digital Close** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Pavaso Digital Close サポート チーム](mailto:support@pavaso.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Pavaso Digital Close テスト ユーザーの作成

このセクションでは、Pavaso Digital Close で Britta Simon というユーザーを作成します。 [Pavaso Digital Close サポート チーム](mailto:support@pavaso.com)と協力して、Pavaso Digital Close プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Pavaso Digital Close Sign on URL にリダイレクトされます。
- Pavaso Digital Close のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Pavaso Digital Close に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Pavaso Digital Close タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Pavaso Digital Close に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/paycargo-classic-tutorial"} -->
## Microsoft Entra ID で PayCargo クラシックのシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/paycargo-classic-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と PayCargo Classic の間にシングル サインオンを構成する方法について説明します。

この記事では、PayCargo Classic と Microsoft Entra ID を統合する方法について説明します。 PayCargo Classic と Microsoft Entra ID を統合すると、次のことができます。

- PayCargo Classic にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して PayCargo Classic に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な PayCargo Classic サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- PayCargo Classic では、**SP** Initiated SSO がサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから PayCargo Classic を追加する

Microsoft Entra ID への PayCargo Classic の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に PayCargo Classic を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**PayCargo Classic**」と入力します。
4. 結果パネルから **[PayCargo Classic]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### PayCargo Classic での Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、PayCargo Classic による Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと PayCargo Classic の関連ユーザーとの間にリンク関係を確立する必要があります。

PayCargo Classic による Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **PayCargo Classic SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **PayCargo Classicのテストユーザーを作成する** - Microsoft Entra IDでのB.Simonにリンクされた、PayCargo ClassicでのB.Simonに対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**PayCargo Classic**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに `https://prod.paycargo.com/` という URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://api.paycargo.com/<CLIENT_NAME>/login/callback` のパターンを使用して URL を入力します

    c. **[サインオン URL]** ボックスに、URL として「`https://app.paycargo.com`」と入力します。

    注

    応答 URL は実際のものではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、[PayCargo Classic サポート チーム](mailto:support@paycargo.com)にお問い合わせください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[PayCargo Classic の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは構成 URL をコピーするように示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### PayCargo Classic SSO の構成

**PayCargo Classic** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と Microsoft Entra 管理センターからコピーした適切な URL を [PayCargo Classic サポート チーム](mailto:support@paycargo.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### PayCargo Classic テスト ユーザーの作成

このセクションでは、PayCargo Classic で B.Simon というユーザーを作成します。 [PayCargo Classic サポート チーム](mailto:support@paycargo.com)と連携して、PayCargo Classic プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる PayCargo クラシック サインオン URL にリダイレクトします。
- PayCargo Classic のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [PayCargo クラシック] タイルを選択すると、このオプションは PayCargo クラシック サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/paylocity-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Paylocity を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/paylocity-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Paylocity の間にシングル サインオンを構成する方法について説明します。

この記事では、Paylocity と Microsoft Entra ID を統合する方法について説明します。 Paylocity と Microsoft Entra ID を統合すると、次のことができます:

- Paylocity にアクセスできるユーザー Microsoft Entra ID を制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Paylocity に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Paylocity でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Paylocity では、**SP と IDP** によって開始される SSO がサポートされます

### ギャラリーからの Paylocity の追加

Microsoft Entra ID への Paylocity の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Paylocity を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Paylocity**」と入力します。
4. 結果ウィンドウで **[Paylocity]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Paylocity 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Paylocity に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Paylocity の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Paylocity と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Paylocity の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Paylocity テスト ユーザーの作成 - Paylocity** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Paylocity**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://access.paylocity.com/`
7. **保存** を選択します。
8. Paylocity アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Paylocity アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性にも事前に値が設定されていますが、実際の値でこれらの属性を更新する必要があります。

    | 名前 | ソース属性 |
    | --- | --- |
    | パートナーID | `P8000010` |
    | PaylocityUser | `user.mail` |
    | PaylocityEntity | &lt; `PaylocityEntity` &gt; |

    注

    PaylocityEntity は Paylocity の会社 ID です。
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[アイコンの **編集]** を選択します。

    [Image: [SAML 署名証明書] のスクリーンショット。[フェデレーション メタデータ XML] の [ダウンロード] アクションが選択されています。]
12. [ **署名オプション** ] として [ **SAML 応答とアサーションに署名** ] を選択し、[ **保存]** を選択します。

    [Image: SAML 署名証明書の編集]
13. **[Paylocity の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Paylocity SSO の構成

**Paylocity** 側でシングル サインオンを構成するには、次の手順を実行します。

1. **フェデレーション メタデータ XML** をダウンロードします。
2. [Paylocity] で、[&gt;&gt; に移動します。
3. **[SSO Integrations](SSO 統合)** の下にある **[Add SSO Integration](SSO 統合の追加)** を選択します。 新しいドロアーが開きます。
4. ドロップダウンから、SSO プロバイダーとして **[Microsoft Azure]** を選択します。
5. ドロップダウンから **[Status](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/状態)** を選択します。
6. メタデータ ファイルをドロップ領域にドラッグ アンド ドロップします。 Paylocity は、発行者 URL、POST URL、リダイレクト URL、バインディング URL、およびセキュリティ証明書の解析を試みます。
7. 変更を確認するには **保存** を選択します。 統合が **[SSO Integrations](SSO 統合)** の下に表示されます。

#### Paylocity テスト ユーザーの作成

このセクションでは、Paylocity で B.Simon というユーザーを作成します。 [Paylocity サポート チーム](mailto:service@paylocity.com)と連携して、Paylocity プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Paylocity サインオン URL にリダイレクトされます。
- Paylocity のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Paylocity に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Paylocity] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Paylocity に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/peakon-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Peakon を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/peakon-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: ユーザー アカウントを Peakon に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事の目的は、Peakon と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Peakon に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

このコネクタは、現在プレビューの段階です。 プレビューの詳細については、「 [オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)」を参照してください。

Peakon は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.
- [Peakon テナント](https://www.workday.com/en-us/products/employee-voice/overview.html)。
- 管理者アクセス許可がある Peakon のユーザー アカウント。

### 手順 1: Peakon にユーザーを割り当てる

Microsoft Entra ID では、 *割り当て* と呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーとグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Peakon へのアクセスが必要な Microsoft Entra ID 内のユーザー/グループを決定しておく必要があります。 特定した後、次の手順に従い、これらのユーザー、グループ、またはその両方を Peakon に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### 手順 2: Peakon にユーザーを割り当てるための重要なヒント

- 1 人の Microsoft Entra ユーザーを Peakon に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Peakon にユーザーを割り当てるときは、割り当てダイアログで、有効なアプリケーション固有ロール (使用可能な場合) を選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### 手順 3: プロビジョニング用に Peakon を設定する

1. [Peakon 管理コンソール](https://app.Peakon.com/login)にサインインします。 **[構成] を選択します**。

    [Image: Peakon 管理コンソール]
2. **統合**を選択します。

    [Image: [統合] オプションが強調表示されている [構成] オプションのスクリーンショット。]
3. **Employee Provisioning を**有効にします。

    [Image: [有効] オプションが強調表示されている [Employee Provisioning](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/従業員のプロビジョニング) セクションのスクリーンショット。]
4. **SCIM 2.0 URL** と **OAuth ベアラー トークン**の値をコピーします。 これらの値は、Peakon アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

    [Image: Peakon のトークンの作成]

### 手順 4: ギャラリーから Peakon を追加する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Peakon を構成するには、Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に Peakon を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** に移動します。
3. **[ギャラリーから追加**] セクションに「**Peakon**」と入力し、検索ボックスで **[Peakon**] を選択します。
4. 結果パネルから **Peakon** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: Peakon の結果一覧]

### 手順 5: Peakon への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて Peakon 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Peakon のシングル サインオンに関する記事で説明されている手順に従って、Peakon で SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/peakon-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Peakon の自動ユーザー プロビジョニングを構成するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリ**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **Peakon** を選択します。

    [Image: アプリケーションの一覧の Peakon リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Peakon テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Peakon に接続できることを確認します。 接続に失敗した場合は、Peakon アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Peakon に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Peakon のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: Peakon ユーザー属性]
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

- Peakon のすべてのカスタム ユーザー属性は、Peakon の `urn:ietf:params:scim:schemas:extension:peakon:2.0:User` のカスタム SCIM ユーザー拡張から拡張する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/peakon-tutorial"} -->
## Microsoft Entra ID で Peakon for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/peakon-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Peakon の間にシングル サインオンを構成する方法について説明します。

この記事では、Peakon と Microsoft Entra ID を統合する方法について説明します。 Peakon を Microsoft Entra ID と統合すると、次のことができます。

- Peakon にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Peakon に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Peakon は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- シングル サインオン (SSO) が有効な Peakon のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Peakon では、**SP** 開始の SSO と **IDP** 開始の SSO がサポートされます。
- Peakon では、 [**自動** ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/peakon-provisioning-tutorial) がサポートされています (推奨)。

### ギャラリーから Peakon を追加する

Microsoft Entra ID への Peakon の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Peakon を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Peakon**」と入力します。
4. 結果パネルから **Peakon** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Peakon 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Peakon に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Peakon の関連ユーザーとの間にリンク関係を確立する必要があります。

Peakon に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Peakon SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Peakon テスト ユーザーの作成** - Microsoft Entra の B.Simon にリンクされる Peakon 上の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Peakon**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.peakon.com/saml/<companyid>/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.peakon.com/saml/<companyid>/assert`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.peakon.com/login`

    注

    これらの値は実際の値ではありません。 これらの値は、記事の後半で説明する実際の識別子と応答 URL で更新します。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Peakon のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Peakon SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として Peakon にサインインします。
2. ページの左側にあるメニュー バーで、[ **構成**] を選択し、[統合] に移動 **します**。

    [Image: [構成] を示すスクリーンショット]
3. [ **統合** ] ページで、[ **シングル サインオン**] を選択します。

    [Image: Single を示すスクリーンショット]
4. [ **シングル サインオン** ] セクションで、[ **有効にする**] を選択します。

    [Image: シングル サインオンを有効にするスクリーンショット]
5. [ **SAML を使用した従業員のシングル サインオン** ] セクションで、次の手順を実行します。

    [Image: SAML シングル サインオンを示すスクリーンショット]

    ある。 **[SSO ログイン URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    b。 **[SSO ログアウト URL**] ボックスに、前にコピーした**ログアウト URL** の値を貼り付けます。

    c. [ **ファイルの選択] を選択** して、ダウンロードした証明書を [証明書] ボックスにアップロードします。

    d. **アイコン**を選択して**エンティティ ID を**コピーし、[**基本的な SAML 構成]** セクションの **[識別子**] ボックスに貼り付けます。

    え **アイコン**を選択して**応答 URL (ACS) を**コピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。

    f. **[保存] を選択します**。

#### Peakon テスト ユーザーの作成

Microsoft Entra ユーザーが Peakon にサインインできるようにするには、ユーザーを Peakon にプロビジョニングする必要があります。 Peakon の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. Peakon 企業サイトに管理者としてサインインします。
2. ページの左側にあるメニュー バーで、[ **構成**] を選択し、[ **従業員]** に移動します。

    [Image: 従業員を示すスクリーンショット]
3. ページの右上にある [ **従業員の追加**] を選択します。

    [Image: 従業員追加の方法を示すスクリーンショット]
4. [ **新しい従業員** ] ダイアログ ページで、次の手順を実行します。

    [Image: 新しい従業員を示すスクリーンショット]

    1. [c0] 名前 [/c0] ボックスに、名は [c1] Britta [/c1]、姓は [c2] simon [/c2] と入力します。
    2. [ **電子メール** ] ボックスに、 **Brittasimon@contoso.com**などのメール アドレスを入力します。
    3. **従業員の作成**を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Peakon のサインオン URL にリダイレクトされます。
- Peakon のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Peakon に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Peakon] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Peakon に自動的にサインインされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->
