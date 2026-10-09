# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 12)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 77

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ideagen-cloud-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Ideagen Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ideagen-cloud-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-09
- Summary: Microsoft Entra ID から Ideagen Cloud へのユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Ideagen Cloud と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Ideagen Cloud](https://www.ideagen.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Ideagen Cloud でユーザーを作成する。
- アクセスが不要になったら、Ideagen Cloud のユーザーを削除します。
- Microsoft Entra ID と Ideagen Cloud 間でユーザー属性の同期を維持します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- テナント URL とシークレット トークン。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Ideagen Cloud の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように Ideagen Cloud を構成する

1. Ideagen にログインします。 左側のメニューを表示するには、[ **管理** ] アイコンを選択します。

    [Image: 管理メニューのスクリーンショット。]
2. **[Manage tenant] (テナントの管理)** サブメニューの **[Authentication] (認証)** ページに移動します。

    [Image: [Authentication] (認証) ページのスクリーンショット。]
3. [編集] ボタンを選択し、[自動プロビジョニング] で **[有効]** チェック ボックスをオンにします。

    [Image: プロビジョニングの許可のスクリーンショット。]
4. [ **保存]** ボタンを選択して変更を保存します。
5. 認証ページの**クライアント トークン**セクションまでスクロールして**再生成**を選択します。

    [Image: トークン生成のスクリーンショット。]
6. **[Bearer token] (ベアラー トークン)** をコピーして保存します。 この値は、Ideagen Cloud アプリケーションの [プロビジョニング] タブの [シークレット トークン \*] フィールドに入力されます。

    [Image: トークンのコピーのスクリーンショット。]
7. **SCIM URL** を見つけて、後で使用できるように値を保持します。 この値は、Azure portal で自動ユーザー プロビジョニングを構成するときにテナント URL として使用されます。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Ideagen Cloud を追加する

Microsoft Entra アプリケーション ギャラリーから Ideagen Cloud を追加して、Ideagen Cloud へのプロビジョニングの管理を開始します。 SSO のために Ideagen Cloud を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Ideagen Cloud への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて Microsoft Entra 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Microsoft Entra の自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Ideagen Cloud]** を選択します。

    [Image: [アプリケーション] リストの [Ideagen Cloud] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Ideagen Cloud テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Ideagen Cloud に接続できることを確認します。 接続に失敗した場合は、Ideagen Cloud アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: [プロビジョニング プロパティの構成] ページのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Ideagen Cloud に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Ideagen Cloud のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Ideagen Cloud API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Ideagen Cloud で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | 優先言語 | 糸 |  |  |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | externalId | 糸 |  | ✓ |

    注

    自動プロビジョニング作業を問題なく取得するには、すべての必須フィールド (名、姓、電子メールなど) をMicrosoft Entra IDに入力する必要があります。
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ideascale-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に IdeaScale を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ideascale-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IdeaScale の間にシングル サインオンを構成する方法について説明します。

この記事では、IdeaScale と Microsoft Entra ID を統合する方法について説明します。 IdeaScale と Microsoft Entra ID を統合すると、次のような利点があります。

- IdeaScale にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで IdeaScale に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IdeaScale でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- IdeaScale では、**SP** Initiated SSO がサポートされます

### ギャラリーからの IdeaScale の追加

Microsoft Entra ID への IdeaScale の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に IdeaScale を追加する必要があります。

**ギャラリーから IdeaScale を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **IdeaScale」**と入力し、結果パネルで **IdeaScale** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の IdeaScale]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、IdeaScale で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと IdeaScale 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

IdeaScale で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **IdeaScale のシングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **IdeaScaleのテストユーザーを作成** - IdeaScaleでBritta Simonに対応するユーザーを作成し、Microsoft Entraのユーザー表現とリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

IdeaScale で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**IdeaScale** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [IdeaScale のドメインと URL] のシングル サインオン情報]

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.ideascale.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。

    ```http
    http://<companyname>.ideascale.com
    https://<companyname>.ideascale.com
    ```

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[IdeaScale クライアント サポート チーム](https://support.ideascale.com/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[IdeaScale のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    ある。 ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### IdeaScale のシングル サインオンの構成

1. 別の Web ブラウザーのウィンドウで、IdeaScale 企業サイトに管理者としてログインします。
2. **[コミュニティの設定]** に移動します。

    [Image: コミュニティの設定]
3. **セキュリティ**&gt;**シングルサインオン設定** に移動します。

    [Image: [Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ) メニューで選択されている [Single Signon Settings](シングル サインオン設定) を示すスクリーンショット。]
4. **[シングル サインオンのタイプ]** で **[SAML 2.0]** を選択します。

    [Image: シングル サインオンのタイプ]
5. **[シングル サインオンの設定]** ダイアログで、次の手順を実行します。

    [Image: [Single Signon Settings](シングル サインオンの設定) ダイアログ ボックスを示すスクリーンショット。]

    ある。 **[SAML IdP Entity ID] (SAML IdP エンティティ ID)** テキストボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    b。 Azure portal からダウンロードしたメタデータ ファイルをメモ帳で開き、内容をコピーして、 **[SAML IdP Metadata]\(SAML IdP メタデータ\)** テキストボックスに貼り付けます。

    c. **[Logout Success URL] (ログアウト成功 URL)** テキストボックスに **[ログアウト URL]** の値を貼り付けます。

    d. [ **変更の保存] を選択します**。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### IdeaScale のテスト ユーザーの作成

Microsoft Entra ユーザーが IdeaScale にログインできるようにするには、そのユーザーを IdeaScale にプロビジョニングする必要があります。 IdeaScale の場合、プロビジョニングは手動で行います。

**ユーザー プロビジョニングを構成するには、次の手順を実行します。**

1. **IdeaScale** 企業サイトに管理者としてログインします。
2. **[コミュニティの設定]** に移動します。

    [Image: コミュニティの設定]
3. [**基本設定]**&gt;**[メンバー管理]** に移動します。
4. [ **メンバーの追加] を選択します**。

    [Image: メンバー管理]
5. [新しいメンバーの追加] セクションで、次の手順を実行します。

    [Image: 新しいメンバーの追加]

    ある。 **[電子メール アドレス]** テキストボックスに、プロビジョニングする有効な Microsoft Entra アカウントのメール アドレスを入力します。

    b。 [ **変更の保存] を選択します**。

    注

    アカウントがアクティブになる前に、Microsoft Entra アカウント所有者は、アカウント確認用のリンクを含むメールを受け取ります。

注

他の IdeaScale ユーザー アカウント作成ツールや、IdeaScale から提供されている API を使って、Microsoft Entra のユーザー アカウントをプロビジョニングできます。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [IdeaScale] タイルを選択すると、SSO を設定した IdeaScale に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ideo-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に IDEO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ideo-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: ユーザー アカウントを IDEO に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、IDEO で実行する手順と、IDEO に対してユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するMicrosoft Entra IDを示することです。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- IDEO でユーザーを作成する
- アクセスが不要になった場合に IDEO のユーザーを削除する
- Microsoft Entra IDと IDEO の間でユーザー属性の同期を維持する
- IDEO でグループとグループ メンバーシップをプロビジョニングする
- IDEO へのシングル サインオン (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [1 つの Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- [プロビジョニングを構成する権限](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ([Application Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [Application Owner](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications) など)。
- [IDEO のテナント](https://www.saasworthy.com/product/shape-space/pricing)
- 管理者アクセス許可を持つ IDEO 上のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとIDEOの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように IDEO を構成する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に IDEO を構成する前に、IDEO からプロビジョニング情報を取得する必要があります。

- **シークレット トークン**については、productsupport@ideo.comの IDEO サポート チームにお問い合わせください。 この値は、IDEO アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから IDEO を追加する

Microsoft Entra アプリケーション ギャラリーから IDEO を追加して、IDEO へのプロビジョニングの管理を開始します。 SSO に対して IDEO を以前に設定した場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: IDEO に対する自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて IDEO でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで IDEO の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **IDEO** を選択します。

    [Image: アプリケーションの一覧の IDEO リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、IDEO テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、MICROSOFT ENTRA IDが IDEO に接続できることを確認します。 接続に失敗した場合は、IDEO アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから IDEO に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で IDEO のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が IDEO API でサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | emails[type eq "仕事"].value | 糸 |
    | 活動中 | ブール値 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから IDEO に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で IDEO のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

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
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### 変更ログ

- 06/15/2020 - グループに対して PUT 操作ではなく PATCH 操作を使用するためのサポートを追加しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/idid-manager-tutorial"} -->
## Microsoft Entra ID で iDiD Manager をシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/idid-manager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と iDiD Manager の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、iDiD Manager と Microsoft Entra ID を統合する方法について説明します。 iDiD Manager と Microsoft Entra ID の統合には、次の利点があります。

- iDiD Manager にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで iDiD Manager に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- iDiD Manager でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- iDiD Manager では、**SP と IDP** によって開始される SSO がサポートされます

### ギャラリーからの iDiD Manager の追加

Microsoft Entra ID への iDiD Manager の統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに iDiD Manager を追加する必要があります。

**ギャラリーから iDiD Manager を追加するには、次の手順を実行します。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **iDiD Manager」**と入力し、結果パネルで **iDiD Manager** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の iDiD Manager]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、iDiD Manager で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと iDiD Manager 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

iDiD Manager で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **iDiD Manager シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **iDiD Manager のテストユーザー作成** - iDiD Manager で、Britta Simon と同様のユーザーを作成し、Microsoft Entra でのユーザー表現に関連付けます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

iDiD Manager で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**iDiD Manager** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。

    [Image: [基本的な SAML 構成] を示すスクリーンショット。]
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [Image: スクリーンショットには、追加のURLを設定する画面が表示されており、ここでサインオンURLを入力することができます。]

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://idid2.fi/saml/login/<domain>`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[iDiD Manager クライアント サポート チーム](mailto:support@idid.fi)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### iDiD Manager のシングル サインオンの構成

**iDiD Manager** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [iDiD Manager サポート チーム](mailto:support@idid.fi)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### iDiD Manager のテスト ユーザーの作成

このセクションでは、iDiD Manager で Britta Simon というユーザーを作成します。 [iDiD Manager サポート チーム](mailto:support@idid.fi)と連携し、iDiD Manager プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [iDiD Manager] タイルを選択すると、SSO を設定した iDiD Manager に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/idrive-tutorial"} -->
## Microsoft Entra ID で IDrive for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/idrive-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IDrive の間のシングル サインオンを構成する方法について説明します。

この記事では、IDrive と Microsoft Entra ID を統合する方法について説明します。 IDrive を Microsoft Entra ID と統合すると、次のことが可能になります。

- IDrive にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで IDrive に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IDrive でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- IDrive では、**SP と IDP** Initiated SSO がサポートされます。

### ギャラリーからの IDrive の追加

Microsoft Entra ID への IDrive の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に IDrive を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**を参照してください。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックスに **「IDrive** 」と入力します。
4. 結果パネルから **[IDrive** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### IDrive 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、IDrive に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと IDrive の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を IDrive と一緒に構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IDrive SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IDriveのテストユーザーを作成** - Microsoft EntraのユーザーであるB.Simonに対応するユーザーをIDriveで作成しリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**IDrive**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.idrive.com/idrive/login/loginForm`
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **IDrive のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IDrive SSO の構成

**IDrive** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** と、アプリケーション構成からコピーした適切な URL を [IDrive サポート チーム](https://www.idrive.com/support)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IDrive のテスト ユーザーの作成

このセクションでは、IDrive で Britta Simon というユーザーを作成します。 [IDrive サポート チーム](https://www.idrive.com/support)と協力して、IDrive プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる IDrive サインオン URL にリダイレクトされます。
- IDrive のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IDrive に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [IDrive] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した IDrive に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/idrive360-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に IDrive360 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/idrive360-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IDrive360 間のシングル サインオンを構成する方法について説明します。

この記事では、IDrive360 と Microsoft Entra ID を統合する方法について説明します。 IDrive360 を Microsoft Entra ID と統合すると、次のことが可能になります。

- IDrive360 にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで IDrive360 に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IDrive360 でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- IDrive360 では、**SP と IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの IDrive360 の追加

Microsoft Entra ID への IDrive360 の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に IDrive360 を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**IDrive360**」と入力します。
4. 結果のパネルから **[IDrive360]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### IDrive360 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、IDrive360 と一緒に Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと IDrive360 の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を IDrive360 と一緒に構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IDrive360 SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IDrive360 テスト ユーザーの作成** - B.Simon に対応するユーザーを IDrive360 で作成し、Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[IDrive360]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.idrive360.com/enterprise/sso`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[IDrive360 の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IDrive360 SSO の構成

1. IDrive360 企業サイトに管理者としてログインします。
2. **[設定]**&gt;**[シングル サインオン (SSO)]** に移動し、次の手順を実行します。

    [Image: シングル サインオン]

    a. **[SSO name](SSO 名)** テキストボックスに、有効な名前を入力します。

    b。 **[発行者 URL]** ボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    c. **[SSO エンドポイント]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    d. [ **証明書のアップロード]** を選択して、以前にダウンロードした **証明書 (PEM)** をアップロードします。

    e. [ **シングル サインオンの構成] を選択します**。

#### IDrive360 のテスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、IDrive360 企業サイトに管理者としてサインインします
2. [ **ユーザー** ] タブに移動し、[ **ユーザーの追加]** を選択します。

    [Image: ユーザー]
3. **[新しいユーザーの作成]** セクションで、次の手順を実行します。

    [Image: ユーザーを作成]

    a. 有効な**メール アドレス**を **[メール アドレス]** テキストボックスに入力します。

    b。 **を選択して**を作成します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる IDrive360 サインオン URL にリダイレクトされます。
- IDrive360 のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IDrive360 に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [IDrive360] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した IDrive360 に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/igloo-software-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Igloo Software を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/igloo-software-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Igloo Software の間のシングル サインオンを構成する方法について説明します。

この記事では、Igloo Software と Microsoft Entra ID を統合する方法について説明します。 Igloo Software と Microsoft Entra ID を統合すると、次のことができます。

- Igloo Software にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Igloo Software に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) 対応の Igloo Software サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Igloo Software では、 **SP** Initiated SSO がサポートされます。
- Igloo Software では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Igloo Software の追加

Microsoft Entra ID への Igloo Software の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Igloo Software を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Igloo Software**」と入力します。
4. 結果パネルから **[Igloo Software** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Igloo Software 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Igloo Software に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Igloo Software での関連ユーザーとの間にリンク関係を確立する必要があります。

Igloo Software 用に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Igloo Software の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Igloo Software テスト ユーザーの作成** - Igloo Software で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Igloo Software**&gt;**シングルサインオン**を参照。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.igloocommmunities.com/saml.digest`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.igloocommmunities.com/saml.digest`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.igloocommmunities.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Igloo Software クライアント サポート チーム](https://customercare.igloosoftware.com/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Igloo Software のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Igloo Software SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として Igloo Software 企業サイトにログインします。
2. **コントロール パネル**に移動します。

    [Image: コントロール パネル]
3. [ **メンバーシップ** ] タブで、[ **サインイン設定]** を選択します。

    [Image: サインイン設定 サインイン設定]
4. [SAML 構成] セクションで、[ **SAML 認証の構成**] を選択します。

    [Image: SAML 構成]
5. [ **全般構成** ] セクションで、次の手順を実行します。

    [Image: 全般設定]

    a. [ **接続名]** ボックスに、構成のカスタム名を入力します。

    b。 **[IdP ログイン URL**] ボックスに、**ログイン URL** の値を貼り付けます。

    c. **[IdP ログアウト URL**] ボックスに、**ログアウト URL** の値を貼り付けます。

    d. [ **ログアウト応答] と [HTTP の種類を** POST として要求] を選択 **します**。

    e. Azure portal からダウンロードした **base-64** でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、[ **パブリック証明書** ] ボックスに貼り付けます。
6. **応答と認証の構成**で、次の手順を実行します。

    [Image: 応答と認証構成]

    a. **ID プロバイダー**として、**Microsoft ADFS** を選択します。

    b。 **識別子の種類**として、[**電子メール アドレス**] を選択します。

    c. [ **Email Attribute]\(電子メール属性** \) ボックスに、「 **emailaddress**」と入力します。

    d. **First Name Attribute**テキストボックスに**givenname**と入力します。

    e. [ **Last Name Attribute]\(姓属性** \) ボックスに「 **surname**」と入力します。
7. 次の手順を実行して、構成を完成させます。

    [Image: [サインイン時のユーザーの作成] サインイン]

    a. **[サインイン時のユーザーの作成] で**、[**サインイン時にサイトに新しいユーザーを作成**する] を選択します。

    b。 **[サインイン設定] で**、[**サインイン] 画面で [SAML の使用] ボタンを選択します**。

    c. **[保存] を選択します**。

#### Igloo Software のテスト ユーザーの作成

Igloo Software へのユーザー プロビジョニングを構成するためのアクション項目はありません。

割り当てられたユーザーがアクセス パネルを使用して Igloo Software にログインしようとすると、そのユーザーが存在するかどうかが Igloo Software によって確認されます。 使用可能なユーザー アカウントがまだない場合は、Igloo Software によって自動的に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Igloo Software のサインオン URL にリダイレクトされます。
- Igloo Software のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Igloo Software] タイルを選択すると、このオプションは Igloo Software のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/igrafx-platform-tutorial"} -->
## Microsoft Entra ID を使用して iGrafx Platform for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/igrafx-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と iGrafx Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、iGrafx Platform と Microsoft Entra ID を統合する方法について説明します。 iGrafx Platform と Microsoft Entra ID を統合すると、次のことができます。

- iGrafx Platform にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して iGrafx Platform に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- iGrafx Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- iGrafx Platform では、**SP** Initiated SSO がサポートされます。
- iGrafx Platform では、**ジャストインタイム** ユーザー プロビジョニングがサポートされています。

### ギャラリーから iGrafx Platform を追加する

Microsoft Entra ID への iGrafx Platform の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に iGrafx Platform を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**iGrafx Platform**」と入力します。
4. 結果パネル **iGrafx Platform** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### iGrafx Platform の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、iGrafx Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと iGrafx Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

iGrafx Platform に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **iGrafx Platform の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **iGrafx Platform のテスト ユーザーの作成** - iGrafx Platform で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**iGrafx Platform**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: スクリーンショットは、基本的な S A M L 構成を編集する方法を示しています。]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    a. **識別子 (エンティティ ID)** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **Identifier** |
    | --- |
    | `https://<SUBDOMAIN>.igrafxcloud.com/saml/metadata` |
    | `https://<SUBDOMAIN>.igrafxdemo.com/saml/metadata` |
    | `https://<SUBDOMAIN>.igrafxtraining.com/saml/metadata` |
    | `https://<SUBDOMAIN>.igrafx.com/saml/metadata` |

    b。 [**応答 URL** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<SUBDOMAIN>.igrafxcloud.com/` |
    | `https://<SUBDOMAIN>.igrafxdemo.com/` |
    | `https://<SUBDOMAIN>.igrafxtraining.com/` |
    | `https://<SUBDOMAIN>.igrafx.com/` |

    c. [**サインオン URL** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<SUBDOMAIN>.igrafxcloud.com/` |
    | `https://<SUBDOMAIN>.igrafxdemo.com/` |
    | `https://<SUBDOMAIN>.igrafxtraining.com/` |
    | `https://<SUBDOMAIN>.igrafx.com/` |

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、iGrafx Platform クライアント サポート チーム  にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: スクリーンショットには、[証明書のダウンロード] リンクが表示されます。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### iGrafx Platform SSO の構成

iGrafx Platform **側** でシングル サインオンを構成するには、**アプリフェデレーション メタデータ URL** を [iGrafx Platform サポート チーム](mailto:support@igrafx.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### iGrafx Platform テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを iGrafx Platform に作成します。 iGrafx Platform では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ iGrafx Platform に存在していない場合は、iGrafx Platform にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる iGrafx Platform のサインオン URL にリダイレクトされます。
- iGrafx Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [iGrafx Platform] タイルを選択すると、このオプションは iGrafx Platform のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ihasco-training-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に iHASCO Training を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ihasco-training-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と iHASCO Training の間でシングル サインオンを構成する方法について説明します。

この記事では、iHASCO Training と Microsoft Entra ID を統合する方法について説明します。 iHASCO Training を Microsoft Entra ID と統合すると、次のことができます。

- iHASCO Training にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して iHASCO Training に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- iHASCO Training でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- iHASCO Training では、**SP** によって開始される SSO がサポートされます。
- iHASCO Training では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの iHASCO Training の追加

Microsoft Entra ID への iHASCO Training の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に iHASCO Training を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**iHASCO Training**」と入力します。
4. 結果のパネルで **[iHASCO Training]** を選択し、このアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### iHASCO Training 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、iHASCO Training に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと、iHASCO Training での関連ユーザーとの間にリンク関係を確立する必要があります。

iHASCO Training 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **iHASCO Training の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **iHASCO Training のテストユーザーを作成する** - iHASCO Training 上で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 上のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**iHASCO Training**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. **[識別子]** ボックスに、`https://authentication.ihasco.co.uk/saml2/<ID>/metadata` という形式で URL を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://authentication.ihasco.co.uk/saml2/<ID>/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.ihasco.co.uk/<ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、Sign-On URL でこれらの値を更新します。 これらの値を取得するには、[iHASCO Training クライアント サポート チーム](mailto:support@ihasco.co.uk)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Set up iHASCO Training](iHASCO Training のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### iHASCO Training の SSO の構成

1. iHASCO Training の Web サイトに管理者としてログインします。
2. 右上のナビゲーションで **[設定] を** 選択し、[ **詳細設定** ] タイルまでスクロールし、[ **シングル サインオンの構成**] を選択します。

    [Image: iHASCO Training の SSO ボタンのスクリーンショット。]
3. [ **IDENTITY PROVIDERS** ] タブで、[ **プロバイダーの追加** ] を選択し、[ **SAML2**] を選択します。

    [Image: iHASCO Training の [IDENTITY PROVIDERS](ID プロバイダー) のスクリーンショット。]
4. **[Single Sign On / New SAML2](シングル サインオン/新規 SAML2)** ページで次の手順を実行します。

    [Image: iHASCO Training の [Single Sign On](シングル サインオン) のスクリーンショット。]

    a. **[GENERAL](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般)** の下で、この構成を識別するための **[Description](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/説明)** を入力します。

    b。 **[IDENTITY PROVIDER DETAILS](ID プロバイダーの詳細)** の下の **[Single Sign-on URL](シングル サインオン URL)** ボックスに、前の手順でコピーした**ログイン URL** の値を貼り付けます。

    c. **[シングル ログアウト URL]** テキストボックスに、先ほどコピーした**ログアウト URL** の値を貼り付けます。

    d. **[エンティティ ID]** テキストボックスに、先ほどコピーした**識別子**の値を貼り付けます。

    e. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[X509 (公開) 証明書]** テキストボックスに貼り付けます。

    f. **[USER ATTRIBUTE MAPPING](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー属性マッピング)** の下の **[Email address](メール アドレス)** に、`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress` のような値を入力します。

    g. **[First name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** に、`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname` のような値を入力します。

    h. **[Last name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** に、`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname` のような値を入力します。

    一. **保存** を選択します。

    j. ページの再読み込み後に **[今すぐ有効にする] を** 選択します。
5. 左側のナビゲーションで **[セキュリティ**] を選択し、[**登録**方法] として **[シングル サインオン プロバイダー**] を選択し、[**選択したプロバイダー**] として **Microsoft Entra 構成**を選択します。

    [Image: iHASCO Training の [Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ) のスクリーンショット。]
6. **[変更の保存]** を選択します。

#### iHASCO Training のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを iHASCO Training に作成します。 iHASCO Training では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 iHASCO Training にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる iHASCO Training のサインオン URL にリダイレクトされます。
- iHASCO Training のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [iHASCO Training] タイルを選択すると、このオプションは iHASCO Training のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/illusive-networks-tutorial"} -->
## Microsoft Entra ID で Illusive Networks for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/illusive-networks-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Illusive Networks の間のシングル サインオンを構成する方法について説明します。

この記事では、Illusive Networks と Microsoft Entra ID を統合する方法について説明します。 Illusive Networks を Microsoft Entra ID と統合すると、次のことが可能になります。

- Illusive Networks にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Illusive Networks に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Illusive Networks でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Illusive Networks では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

### ギャラリーからの Illusive Networks の追加

Microsoft Entra ID への Illusive Networks の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Illusive Networks を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Illusive Networks**」と入力します。
4. 結果のパネルから **Illusive Networks** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Illusive Networks 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Illusive Networks に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するために、Microsoft Entra ユーザーと Illusive Networks の関連ユーザーとの間にリンク関係を確立する必要があります。

Illusive Networks に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Illusive Networks の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Illusive Networks のテスト ユーザーの作成** - Illusive Networks で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID] **&gt;** [エンタープライズ アプリ] **&gt;** [Illusive Networks] **&gt;** [シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ILLUSIVE-MGMT-SERVER>.<DOMAIN>.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ILLUSIVE-MGMT-SERVER>.<DOMAIN>.com/saml2/splogin/<CUSTOM_ID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ILLUSIVE-MGMT-SERVER>.<DOMAIN>.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Illusive Networks クライアント サポート チーム](mailto:support@illusivenetworks.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Illusive Networks のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Illusive Networks の SSO の構成

**Illusive Networks** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Illusive Networks サポート チーム](mailto:support@illusivenetworks.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Illusive Networks のテスト ユーザーを作成する

このセクションでは、Illusive Networks で Britta Simon というユーザーを作成します。 [Illusive Networks サポート チーム](mailto:support@illusivenetworks.com)と連携して、Illusive Networks プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Illusive Networks のサインオン URL にリダイレクトされます。
- Illusive Networks のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Illusive Networks に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Illusive Networks] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Illusive Networks に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ilms-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に iLMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ilms-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と iLMS の間のシングル サインオンを構成する方法について説明します。

この記事では、iLMS と Microsoft Entra ID を統合する方法について説明します。 iLMS を Microsoft Entra ID と統合すると、次のことが可能になります。

- iLMS にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで iLMS に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- iLMS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- iLMS では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの iLMS の追加

Microsoft Entra ID への iLMS の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に iLMS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**iLMS**」と入力します。
4. 結果のパネルから **[iLMS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### iLMS 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、iLMS に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと iLMS の関連ユーザーとの間にリンク関係を確立する必要があります。

iLMS 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **iLMS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **iLMS テストユーザーを作成** - Microsoft Entra ユーザーの表現にリンクされた iLMS 内の B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**iLMS** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** ページで、アプリケーションを **IDP** Initiated モードで構成する場合は、次の手順を行います。

    a. **[識別子]** ボックスに、iLMS 管理ポータルで [SAML settings](SAML 設定) の **[Service Provider](サービス プロバイダー)** セクションからコピーした **[Identifier](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/識別子)** の値を貼り付けます。

    b。 **[応答 URL]** ボックスに、iLMS 管理ポータルで [SAML settings](SAML 設定) の **[Service Provider](サービス プロバイダー)** セクションからコピーした次の形式の **[Endpoint (URL)](エンドポイント (URL))** の値を貼り付けます`https://www.inspiredlms.com/Login/<INSTANCE_NAME>/consumer.aspx`。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、iLMS 管理ポータルで [SAML settings](SAML 設定) の **[Service Provider](サービス プロバイダー)** セクションからコピーした次の形式の **[Endpoint (URL)](エンドポイント (URL))** の値を貼り付けます`https://www.inspiredlms.com/Login/<INSTANCE_NAME>/consumer.aspx`。
7. iLMS アプリケーションは、JIT プロビジョニングを有効にするために特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 **[編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    注

    これらの属性をマップするには、iLMS で **[Create Un-recognized User Account (未認識のユーザー アカウントの作成)]** を有効にする必要があります。 属性の構成を理解するには、[こちら](https://support.inspiredelearning.com/help/adding-updating-and-managing-users#just-in-time-provisioning-with-saml-single-signon)の手順に従ってください。
8. その他に、iLMS アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | division | user.department |
    | リージョン | ユーザーの状態 |
    | 部署 | ユーザー.職名 |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **[OK]** を選択します。

    g. **保存** を選択します。
9. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[iLMS のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### iLMS の SSO の構成

1. 別の Web ブラウザー ウィンドウで、**iLMS 管理者ポータル**に管理者としてサインインします。
2. [**設定]** タブで **SSO:SAML** を選択して SAML 設定を開き、次の手順を実行します。

    [Image: i L M S 設定タブを示すスクリーンショット (S S O SAML を選択可能)。]
3. **[Service Provider (サービス プロバイダー)]** セクションを展開し、 **[Identifier (識別子)]** と **[Endpoint (URL) (エンドポイント (URL))]** の値をコピーします。

    [Image: 値を取得できる [SAML Settings](SAML 設定) を示すスクリーンショット。]
4. [ **ID プロバイダー** ] セクションで、[ **メタデータのインポート**] を選択します。
5. **[SAML 署名証明書]** セクションからダウンロードした**フェデレーション メタデータ** ファイルを選択します。

    [Image: メタデータ ファイルを選択できる [SAML Settings](SAML 設定) を示すスクリーンショット。]
6. JIT プロビジョニングを有効にして未認識のユーザーの iLMS アカウントを作成する場合は、次の手順に従います。

    a. **[Create Un-recognized User Account (未認識のユーザー アカウントの作成)]** をクリックします。

    [Image: [Create Un-recognized User Account](未認識のユーザー アカウントの作成) オプションを示すスクリーンショット。]

    b。 Microsoft Entra ID の属性を iLMS の属性とマップします。 属性欄に、属性名または既定値を指定します。

    c. **[Business Rules (ビジネス ルール)]** タブに移動し、次の手順を実行します。

    [Image: この手順での情報を入力できる [Business Rules](ビジネス ルール) 設定を示すスクリーンショット。]

    d. シングル サインオン時にまだ存在しない **リージョン、部門、および部門** を作成するには、[認識されていないリージョン、部門、部署の作成] をオンにします。

    e. シングル サインオンのたびにユーザー プロファイルが更新されるように指定するには、 **[Update User Profile During Sign-in (サインイン中にユーザー プロファイルを更新する)]** をオンにします。

    f. **[Update Blank Values for Non Mandatory Fields in User Profile](ユーザー プロファイルの必須フィールド以外の空白の値を更新する)** オプションをオンにした場合、サインイン時に空白であるオプションのプロファイル フィールドによって、ユーザーの iLMS プロファイルのオプションのフィールドが空白の値を含むように更新されます。

    g. エラー通知メールを受信する場合は、 **[Send Error Notification Email (エラー通知メールを送信する)]** をオンにし、ユーザーのメール アドレスを入力します。
7. [ **保存]** ボタンを選択して設定を保存します。

    [Image: [保存] ボタンを示すスクリーンショット。]

#### iLMS のテスト ユーザーの作成

アプリケーションでは、ジャストインタイムのユーザー プロビジョニングがサポートされ、認証後にユーザーがアプリケーションに自動的に作成されます。 iLMS 管理ポータルで SAML 構成設定中に [ **認識されないユーザー アカウントの作成** ] チェック ボックスをオンにした場合は、JIT が機能します。

ユーザーを手動で作成する必要がある場合は、以下の手順に従います。

1. iLMS 企業サイトに管理者としてサインインします。
2. [**ユーザー**] タブで [**ユーザーの登録**] を選択して、[ユーザーの登録] ページ**を**開きます。

    [Image: [Register User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの登録) を選択できる i L M S 設定タブを示すスクリーンショット。]
3. **[Register User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの登録)** ページで次の手順を実行します。

    [Image: 指定された情報を入力できる [Register User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの登録) ページを示すスクリーンショット。]

    a. **[First Name]** ボックスに、ユーザーの名を入力します (この例では Britta)。

    b。 **[Last Name]** ボックスに、ユーザーの姓を入力します (この例では Simon)。

    c. **[Email ID](電子メール ID)** ボックスに、ユーザーの電子メール アドレスを BrittaSimon@contoso.com のように入力します。

    d. **[Region (リージョン)]** ボックスの一覧からリージョンの値を選択します。

    e. **[Division (事業部)]** ボックスの一覧からリージョンの値を選択します。

    f. **[Department (部署)]** ボックスの一覧から部署の値を選択します。

    g. **保存** を選択します。

    注

    **[Send welcome email (ようこそメールの送信)]** チェックボックスをオンにすることで、ユーザーに登録メールを送信できます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる iLMS サインオン URL にリダイレクトされます。
- iLMS のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した iLMS に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [iLMS] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した iLMS に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ilogon-mha-tutorial"} -->
## Microsoft Entra ID でシングル サインオンのiLOGON_MHAを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ilogon-mha-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-22
- Summary: Microsoft Entra と iLOGON_MHA の間のシングル サインオンを構成する方法について説明します。

この記事では、iLOGON\_MHAと Microsoft Entra ID を統合する方法について説明します。 iLOGON\_MHA を Microsoft Entra ID と統合すると、次のことができます。

Microsoft Entra ID を使用して、iLOGON\_MHA にアクセスできるユーザーを制御します。 ユーザーが自分の Microsoft Entra アカウントを使用して iLOGON\_MHA に自動的にサインインできるようにします。 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- iLOGON\_MHA でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから iLOGON\_MHA を追加する

Microsoft Entra ID への iLOGON\_MHA の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに iLOGON\_MHA を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**iLOGON\_MHA**」と入力します。
4. 結果のパネルから **[iLOGON\_MHA]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**iLOGON\_MHA**&gt;**シングルサインオン**を参照します。
3. 次のセクションで以下の手順を実行します。

    ある。 **[アプリケーションに移動]**を選択します。

    [Image: ID 構成を示すスクリーンショット。]

    b。 **アプリケーション (クライアント) ID** と**ディレクトリ (テナント) ID** をコピーして、後で iLOGON\_MHA 側の構成で使用します。

    [Image: アプリケーション クライアント値のスクリーンショット。]
4. 左側のメニューの **[証明書とシークレット]** に移動し、次の手順を実行します。

    1. **[クライアント シークレット]** タブに移動し、**[+ 新しいクライアント シークレット]** を選択します。
    2. テキストボックスに有効な **[説明]** を入力し、要件に応じてドロップダウンから **[有効期限]** 日数を選択し **[追加]** を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、**[値]** が生成されます。 この値をコピーして、後で iLOGON\_MHA 側の構成で使用します。

        [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

注

認証セクションでは、**[リダイレクト URI]** の値が自動的に入力されるため、ここで手動で構成する必要はありません。

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

このセクションでは、B.Simon に iLOGON\_MHA へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**iLOGON\_MHA**をブラウズしてください。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### ILOGON\_MHA SSO を構成する

**iLOGON\_MHA** 側で OAuth/OIDC フェデレーションのセットアップを完了するには、Entra からコピーした値 (テナント ID、アプリケーション ID、クライアント シークレットなど) を [iLOGON_MHA サポート チーム](mailto:support@keyfields.com)に送信する必要があります。 サポート チームはこれを設定して、OIDC 接続が両方の側で正しく設定されるようにします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/imagen-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Imagen を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/imagen-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Imagen の間のシングル サインオンを構成する方法について説明します。

この記事では、Imagen と Microsoft Entra ID を統合する方法について説明します。 Imagen は、組織において大規模かつミッション クリティカルなビデオ ファイルを管理、エンリッチ、配布し、コンテンツの価値を高める目的で構築されたクラウド ネイティブのメディア資産管理プラットフォームです。 Imagen を Microsoft Entra ID と統合すると、次のことが可能になります。

- Microsoft Entra ID で Imagen にアクセス可能なユーザーを制御します。
- ユーザーが Microsoft Entra アカウントで Imagen に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Imagen 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Imagen は **SP** 開始のシングル サインオンと **Just In Time** ユーザー プロビジョニングをサポートしています。

### [前提条件]

Microsoft Entra ID を Imagen と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Imagen でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Imagen アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Imagen を追加する

Microsoft Entra アプリケーション ギャラリーから Imagen を追加して、Imagen とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Imagen]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.imagencloud.com/sp-entityid`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.imagencloud.com/saml/module.php/saml/sp/saml2-acs.php/imagenweb`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.imagencloud.com/site/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Imagen サポート チーム](mailto:support@imagen.io)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Imagen アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Imagen アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:0.9.2342.19200300.100.1.3 | ユーザーのメールアドレス |
    | urn:oid:1.3.6.1.4.1.5923.1.1.1.10 | user.userprincipalname |
    | グループ | ユーザー.グループ |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Imagen のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Imagen SSO を構成する

**Imagen** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Imagen サポート チーム](mailto:support@imagen.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Imagen テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Imagen に作成します。 Imagen では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Imagen にユーザーがまだ存在していない場合、一般に認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Imagen のサインオン URL にリダイレクトされます。
- Imagen のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Imagen] タイルを選択すると、このオプションは Imagen のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/imagerelay-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Image Relay を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/imagerelay-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Image Relay 間のシングル サインオンを構成する方法について説明します。

この記事では、Image Relay と Microsoft Entra ID を統合する方法について説明します。 Image Relay と Microsoft Entra ID を統合すると、次のことができます。

- Image Relay にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Image Relay に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Image Relay でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Image Relay では、**SP** によって開始される SSO がサポートされます。

### ギャラリーから Image Relay を追加する

Microsoft Entra ID への Image Relay の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Image Relay を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Image Relay**」と入力します。
4. 結果パネルから **[Image Relay]** を選択し、そのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Image Relay 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Image Relay に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Image Relay の関連ユーザー間にリンク関係を確立する必要があります。

Image Relay に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Image Relay の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Image Relay のテスト ユーザーの作成** - Image Relay で B.Simon に対応するユーザーを作成し、Microsoft Entra の Britta Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Image Relay**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANYNAME>.imagerelay.com/sso/metadata`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANYNAME>.imagerelay.com/`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Image Relay クライアント サポート チーム](http://support.imagerelay.com/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Image Relay のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Image Relay の SSO の構成

1. 別のブラウザー ウィンドウで、管理者として Image Relay 企業サイトにサインインします。
2. 上部のツール バーで、[ **ユーザー] と [アクセス許可]** ワークロードを選択します。

    [Image: ツール バーから選択された [ユーザーとアクセス許可] を示すスクリーンショット。]
3. [ **新しいアクセス許可の作成]** を選択します。

    [Image: アクセス許可のタイトルを入力するためのテキスト ボックスと、アクセス許可の種類を選択するためのオプションを示すスクリーンショット。]
4. **[シングル サインオン設定] ワークロードで**、[**このグループはシングル サインオン経由でのみサインインできます**] チェック ボックスをオンにし、[保存] を選択**します**。

    [Image: オプションを選択できる [Single Sign On Settings](シングル サイン オンの設定) を示すスクリーンショット。]
5. **[Account Settings (アカウントの設定)]** に移動します。

    [Image: [Account Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントの設定) ツール バー オプションを示すスクリーンショット。]
6. **[Single Sign On Settings (シングル サインオンの設定)]** ワークロードに移動します。

    [Image: [Single Sign On Settings](シングル サインオンの設定) メニュー オプションを示すスクリーンショット。]
7. **[SAML Settings (SAML の設定)]** ダイアログで、次の手順を実行します。

    [Image: 情報を入力できる [SAML Settings](SAML の設定) ダイアログ ボックスを示すスクリーンショット。]

    a. [ **ログイン URL** ] ボックスに、 **ログイン URL** の値を貼り付けます。

    b。 [ **ログアウト URL** ] ボックスに、 **ログアウト URL** の値を貼り付けます。

    c. **[Name Id Format]** として **[urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress]** を選択します。

    d. **[Binding Options for Requests from the Service Provider (Image Relay)]** で **[POST Binding]** を選択します。

    e. **[x.509 証明書] で**、[**証明書の更新**] を選択します。

    [Image: 証明書を更新するためのオプションを示すスクリーンショット。]

    f. ダウンロードした証明書をメモ帳で開き、その内容をコピーして、 **[x.509 Certificate](x.509 証明書)** ボックスに貼り付けます。

    [Image: x dot 509 証明書を示すスクリーンショット。]

    g. **[Just-In-Time User Provisioning]** で、 **[Enable Just-In-Time User Provisioning]** をオンにします。

    [Image: 有効にするコントロールが選択されている [Just-In-Time User Provisioning](Just-In-Time ユーザー プロビジョニング) セクションを示すスクリーンショット。]

    h. シングル サインオンによるサインインのみを許可するアクセス許可グループを選択します ( **[SSO Basic]** など)。

    [Image: [S S O Basic] が選択されている [Just-In-Time User Provisioning](Just-In-Time ユーザー プロビジョニング) セクションを示すスクリーンショット。]

    一. **保存** を選択します。

#### Image Relay の テスト ユーザーの作成

このセクションの目的は、Image Relay で Britta Simon というユーザーを作成することです。

**Image Relay で Britta Simon というユーザーを作成するには、次の手順に従います。**

1. Image Relay 企業サイトに管理者としてログインします。
2. **[ユーザーとアクセス許可]** に移動し、[**SSO ユーザーの作成**] を選択します。

    [Image: メニューで選択されている [Create S S O User](S S O ユーザーの作成) を示すスクリーンショット。]
3. プロビジョニングするユーザーの **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** 、 **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** 、 **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** 、 **[Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社)** を入力し、シングル サインオンのみでサインインできるアクセス許可グループ ([SSO Basic](SSO Basic) など) を選択します。

    [Image: 必要な情報を入力できる [Create a S S O User](S S O ユーザーの作成) ページを示すスクリーンショット。]
4. **を選択して**を作成します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Image Relay のサインオン URL にリダイレクトされます。
- Image Relay のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Image Relay] タイルを選択すると、このオプションは Image Relay のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/imageworks-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に IMAGE WORKS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/imageworks-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IMAGE WORKS 間のシングル サインオンを構成する方法について説明します。

この記事では、IMAGE WORKS と Microsoft Entra ID を統合する方法について説明します。 IMAGE WORKS を Microsoft Entra ID と統合すると、次の利点があります。

- IMAGE WORKS にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して IMAGE WORKS に自動的にサインイン (シングル サインオン) できるようにすることができます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IMAGE WORKS でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- IMAGE WORKS では、**SP** によって開始される SSO がサポートされます

### ギャラリーからの IMAGE WORKS の追加

Microsoft Entra ID への IMAGE WORKS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に IMAGE WORKS を追加する必要があります。

**ギャラリーから IMAGE WORKS を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **IMAGE WORKS」**と入力し、結果パネルから **IMAGE WORKS** を選択し、[ **追加** ] ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の IMAGE WORKS]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、IMAGE WORKS で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと IMAGE WORKS 内の関連ユーザー間にリンク関係が確立されている必要があります。

IMAGE WORKS で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **IMAGE WORKS シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **IMAGE WORKS テスト ユーザーの作成** - IMAGE WORKS で Britta Simon に対応するユーザーを作成し、Microsoft Entra の Britta Simon にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

IMAGE WORKS で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**IMAGE WORKS** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [IMAGE WORKS のドメインと URL] のシングル サインオン情報]

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://i-imageworks.jp/iw/<tenantName>/sso/Login.do`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sp.i-imageworks.jp/iw/<tenantName>/postResponse`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[IMAGE WORKS クライアント サポート チーム](mailto:iw-sd-support@fujifilm.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[IMAGE WORKS のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### IMAGE WORKS のシングル サインオンの構成

**IMAGE WORKS** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [IMAGE WORKS サポート チーム](mailto:iw-sd-support@fujifilm.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### IMAGE WORKS のテスト ユーザーの作成

このセクションでは、IMAGE WORKS で Britta Simon というユーザーを作成します。 [IMAGE WORKS サポート チーム](mailto:iw-sd-support@fujifilm.com)と連携し、IMAGE WORKS プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [IMAGE WORKS] タイルを選択すると、SSO を設定した IMAGE WORKS に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/imagineerwebvision-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Imagineer WebVision を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/imagineerwebvision-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AImagineer WebVision の間のシングル サインオンを構成する方法について説明します。

この記事では、Imagineer WebVision と Microsoft Entra ID を統合する方法について説明します。 Imagineer WebVision と Microsoft Entra ID の統合には、次の利点があります。

- Imagineer WebVision にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで Imagineer WebVision に自動的にサインイン (シングル サインオン) できるようにすることができます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Imagineer WebVision でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Imagineer WebVision では、**SP** によって開始される SSO がサポートされます

### ギャラリーからの Imagineer WebVision の追加

Microsoft Entra ID への Imagineer WebVision の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Imagineer WebVision を追加する必要があります。

**ギャラリーから Imagineer WebVision を追加するには:**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Imagineer WebVision**」と入力し、結果パネルで **Imagineer WebVision** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Imagineer WebVision]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** という名前のテスト ユーザーに基づいて、Imagineer WebVision で Microsoft Entra シングル サインオンを構成してテストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Imagineer WebVision 内の関連ユーザー間にリンク関係が確立されている必要があります。

Imagineer WebVision で Microsoft Entra シングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Imagineer WebVision シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Imagineer WebVision のテストユーザーを作成 - Britta Simon に対応する Imagineer WebVision ユーザーを作成し、Microsoft Entra のアカウントとリンクします。**
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Imagineer WebVision で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Imagineer WebVision** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [Imagineer WebVision Domain and URLs] (Imagineer WebVision のドメインと URL) のシングル サインオン情報]

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR SERVER URL>/<yourapplicationloginpage>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR SERVER URL>/<yourapplicationloginpage>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Imagineer WebVision クライアント サポート チーム](mailto:support@itgny.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Imagineer WebVision シングル サインオンの構成

**Imagineer WebVision** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Imagineer WebVision サポート チーム](mailto:support@itgny.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Imagineer WebVision テスト ユーザーの作成

このセクションでは、Imagineer WebVision で Britta Simon という名前のユーザーを作成します。 [Imagineer WebVision サポート チーム](mailto:support@itgny.com)と協力して、Imagineer WebVision プラットフォームでユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Imagineer WebVision] タイルを選択すると、SSO を設定した Imagineer WebVision に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/impacriskmanager-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に IMPAC Risk Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/impacriskmanager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IMPAC Risk Manager の間にシングル サインオンを構成する方法について説明します。

この記事では、IMPAC Risk Manager と Microsoft Entra ID を統合する方法について説明します。 IMPAC Risk Manager を Microsoft Entra ID と統合すると、次のことができるようになります。

- IMPAC Risk Manager にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って IMPAC Risk Manager に自動的にサインインできるようにすることができます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IMPAC Risk Manager でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- IMPAC Risk Manager では、**SP と IDP** Initiated SSO がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの IMPAC Risk Manager の追加

Microsoft Entra ID への IMPAC Risk Manager の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に IMPAC Risk Manager を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**IMPAC Risk Manager**」と入力します。
4. 結果のパネルから **[IMPAC Risk Manager]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### IMPAC Risk Manager 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、IMPAC Risk Manager で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと IMPAC Risk Manager の関連ユーザーとの間にリンク関係を確立する必要があります。

IMPAC Risk Manager で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IMPAC Risk Manager SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IMPAC Risk Manager のテストユーザーを作成する** - IMPAC Risk Manager で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**IMPAC Risk Manager**&gt;**シングル サインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 **[識別子]** テキスト ボックスに、IMPAC から提供された値を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 環境 | URL パターン |
    | --- | --- |
    | 実稼動用 | `https://www.riskmanager.co.nz/DotNet/SSOv2/AssertionConsumerService.aspx?client=<ClientSuffix>` |
    | ステージングおよびトレーニング用 | `https://staging.riskmanager.co.nz/DotNet/SSOv2/AssertionConsumerService.aspx?client=<ClientSuffix>` |
    | 開発用 | `https://dev.riskmanager.co.nz/DotNet/SSOv2/AssertionConsumerService.aspx?client=<ClientSuffix>` |
    | QA 用 | `https://QA.riskmanager.co.nz/DotNet/SSOv2/AssertionConsumerService.aspx?client=<ClientSuffix>` |
    | テスト用 | `https://test.riskmanager.co.nz/DotNet/SSOv2/AssertionConsumerService.aspx?client=<ClientSuffix>` |
    |  |  |
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 環境 | URL パターン |
    | --- | --- |
    | 実稼動用 | `https://www.riskmanager.co.nz/SSOv2/<ClientSuffix>` |
    | ステージングおよびトレーニング用 | `https://staging.riskmanager.co.nz/SSOv2/<ClientSuffix>` |
    | 開発用 | `https://dev.riskmanager.co.nz/SSOv2/<ClientSuffix>` |
    | QA 用 | `https://QA.riskmanager.co.nz/SSOv2/<ClientSuffix>` |
    | テスト用 | `https://test.riskmanager.co.nz/SSOv2/<ClientSuffix>` |
    |  |  |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[IMPAC Risk Manager クライアント サポート チーム](mailto:rmsupport@Impac.co.nz)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Set up IMPAC Risk Manager](IMPAC Risk Manager の設定)** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IMPAC Risk Manager での SSO の構成

**IMPAC Risk Manager** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [IMPAC Risk Manager サポート チーム](mailto:rmsupport@Impac.co.nz)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IMPAC Risk Manager のテスト ユーザーを作成する

このセクションでは、IMPAC Risk Manager で Britta Simon というユーザーを作成します。 [IMPAC Risk Manager サポート チーム](mailto:rmsupport@Impac.co.nz)と連携し、IMPAC Risk Manager プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる IMPAC Risk Manager のサインオン URL にリダイレクトされます。
- IMPAC Risk Manager のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IMPAC Risk Manager に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [IMPAC Risk Manager] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した IMPAC Risk Manager に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/imperva-data-security-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Imperva Data Security を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/imperva-data-security-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Imperva Data Security の間でシングル サインオンを構成する方法について説明します。

この記事では、Imperva Data Security と Microsoft Entra ID を統合する方法について説明します。 Imperva Data Security と Microsoft Entra ID を統合すると、次のことができます。

- Imperva データ セキュリティにアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Imperva Data Security に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Imperva Data Security でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Imperva Data Security では、 **SP** によって開始される SSO がサポートされます

### ギャラリーからの Imperva Data Security の追加

Microsoft Entra ID への Imperva Data Security の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Imperva Data Security を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Imperva Data Security**」と入力します。
4. 結果パネルから **Imperva Data Security** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Imperva Data Security の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Imperva Data Security に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Imperva Data Security の関連ユーザーとの間にリンク関係を確立する必要があります。

Imperva Data Security に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Imperva Data Security の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Imperva Data Security のテスト ユーザーの作成** - Imperva Data Security で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Imperva Data Security]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して識別子を入力します。 `application-name`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<IMPERVA_DNS_NAME>:8443`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<IMPERVA_DNS_NAME>:8443`

    d. [ **ログアウト URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<IMPERVA_DNS_NAME>:8443`

    手記

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Imperva Data Security クライアント サポート チーム](mailto:support@jsonar.imperva.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Imperva Data Security のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Imperva Data Security SSO の構成

**Imperva Data Security** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Imperva Data Security サポート チーム](mailto:support@jsonar.imperva.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Imperva Data Security テスト ユーザーの作成

このセクションでは、Imperva Data Security で Britta Simon というユーザーを作成します。 [Imperva Data Security サポート チーム](mailto:support@jsonar.imperva.com)と協力して、Imperva Data Security プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Imperva Data Security に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [Imperva Data Security] タイルを選択すると、SSO を設定した Imperva Data Security に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/in-case-of-crisis-mobile-tutorial"} -->
## Microsoft Entra ID でのシングル サインオン用に Case of Crisis - Mobile を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/in-case-of-crisis-mobile-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-04-15
- Summary: Microsoft Entra ID と In Case of Crisis - Mobile 間にシングル サインオンを構成する方法について説明します。

この記事では、In Case of Crisis - Mobile と Microsoft Entra ID を統合する方法について説明します。 In Case of Crisis - Mobile を Microsoft Entra ID と統合すると、次のことができます。

- In Case of Crisis - Mobile にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して In Case of Crisis - Mobile に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- In Case of Crisis - Mobile でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- In Case of Crisis - Mobile では、**IDP** によって開始される SSO のみがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから In Case of Crisis - Mobile を追加する

Microsoft Entra ID への In Case of Crisis - Mobile の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に In Case of Crisis - Mobile を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**In Case of Crisis - Mobile**」と入力します。
4. 結果のパネルから **[In Case of Crisis - Mobile]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### In Case of Crisis - Mobile の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、In Case of Crisis - Mobile で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと In Case of Crisis - Mobile の関連ユーザーとの間にリンク関係を確立する必要があります。

In Case of Crisis - Mobile 用に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **In Case of Crisis - Mobile SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **In Case of Crisis - Mobile テスト ユーザーの作成** - In Case of Crisis - Mobile に、Microsoft Entra のユーザー表現にリンクされた B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Enterprise アプリ]**&gt;**[In Case of Crisis - Mobile]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集のスクリーンショット。]
5. **[基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure で事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[In Case of Crisis - Mobile のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### In Case of Crisis - Mobile の SSO の構成

**In Case of Crisis - Mobile** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** とコピーした**ユーザー アクセス URL を** Azure portal から In Case of Crisis - Mobile サポート チームに送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### In Case of Crisis - Mobile テスト ユーザーの作成

このセクションでは、In Case of Crisis - Mobile で Britta Simon というユーザーを作成します。 In Case of Crisis - Mobile サポート チームと協力して、In Case of Crisis - Mobile プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した In Case of Crisis - Mobile に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [In Case of Crisis - Mobile] タイルを選択すると、SSO を設定した In Case of Crisis - Mobile に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/in-case-of-crisis-online-portal-tutorial"} -->
## In Case of Crisis の構成 - Microsoft Entra ID によるシングルサインオンのオンラインポータル - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/in-case-of-crisis-online-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と In Case of Crisis - Online Portal 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、In Case of Crisis - Online Portal と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と In Case of Crisis - Online Portal を統合すると、次のことができます。

- In Case of Crisis - Online Portal にアクセスする Microsoft Entra ID ユーザーを制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して In Case of Crisis - Online Portal に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- In Case of Crisis - Online Portal でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- In Case of Crisis - Online Portal では、**IDP** によって開始される SSO がサポートされます
- In Case of Crisis - Online Portal を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスから拡張されます。 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)でセッション制御を適用する方法について説明します。

### ギャラリーからの In Case of Crisis - Online Portal の追加

Microsoft Entra ID への In Case of Crisis - Online Portal の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに In Case of Crisis - Online Portal を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**In Case of Crisis - Online Portal**」と入力します。
4. 結果のパネルから **[In Case of Crisis - Online Portal]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### In Case of Crisis - Online Portal の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、In Case of Crisis - Online Portal に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと In Case of Crisis - Online Portal の関連ユーザーとの間にリンク関係を確立する必要があります。

In Case of Crisis - Online Portal で Microsoft Entra の SSO を構成してテストするには、次の項目を完了する必要があります。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **In Case of Crisis Online Portal SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **In Case of Crisis Online Portal のテストユーザーを作成する** - Microsoft Entra のユーザー表現にリンクされた In Case of Crisis のオンラインポータルにて、B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**In Case of Crisis - Online Portal**&gt;**Single のサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集を示すスクリーンショット。]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[In Case of Crisis - Online Portal のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    注

    [プロパティ] ページで、ユーザー アクセス URL を送信してください。 これは、In Case of Crisis ポータルで使用されます。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### In Case of Crisis Online Portal の SSO の構成

**In Case of Crisis - Online Portal** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [In Case of Crisis - Online Portal サポート チーム](mailto:support@rockdovesolutions.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### In Case of Crisis Online Portal のテスト ユーザーの作成

このセクションでは、In Case of Crisis - Online Portal で B.Simon というユーザーを作成します。 [In Case of Crisis - Online Portal サポート チーム](mailto:support@rockdovesolutions.com)と連携して、In Case of Crisis - Online Portal プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [In Case of Crisis - Online Portal] タイルを選択すると、SSO を設定した In Case of Crisis - Online Portal に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/infinitecampus-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Infinite Campus を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/infinitecampus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Infinite Campus 間のシングル サインオンを構成する方法について説明します。

この記事では、Infinite Campus と Microsoft Entra ID を統合する方法について説明します。 Infinite Campus を Microsoft Entra ID と統合すると、次のことができます。

- Infinite Campus にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Infinite Campus に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

Infinite Campus と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Infinite Campus シングル サインオンが有効なサブスクリプション。
- 構成を実行するには、少なくとも Microsoft Entra 管理者であり、「Student Information System (SIS)」のキャンパス製品セキュリティ ロールを持っている必要があります。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Infinite Campus では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの Infinite Campus の追加

Microsoft Entra への Infinite Campus の統合を構成するには、管理対象の SaaS アプリの一覧にギャラリーから Infinite Campus を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Infinite Campus**」と入力します。
4. 結果パネルで **[Infinite Campus]** を選択して、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Infinite Campus 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Infinite Campus に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Infinite Campus の関連ユーザーとの間にリンク関係を確立する必要があります。

Infinite Campus に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Infinite Campus SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Infinite Campus**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [基本的な SAML 構成] セクションで、次の手順を実行します (ドメインはホスティング モデルによって異なりますが、**FULLY-QUALIFIED-DOMAIN** の値は Infinite Campus のインストールと一致する必要があることに注意してください)。

    a. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<DOMAIN>.infinitecampus.com/campus/SSO/<DISTRICTNAME>/SIS`

    b。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<DOMAIN>.infinitecampus.com/campus/<DISTRICTNAME>`

    c. [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<DOMAIN>.infinitecampus.com/campus/SSO/<DISTRICTNAME>`
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Infinite Campus SSO の構成

Infinite Campus 内で SSO を構成する方法の詳細な手順については、[このドキュメントの手順に従ってください](https://kb.infinitecampus.com/help/sso-service-provider-configuration#SSOServiceProviderConfiguration-EnableandConfigureSAMLSSOFunctionality)。

Infinite Campus 内で SSO の構成が完了した後、ユーザーが Infinite Campus からログアウトするときに Azure SSO 接続からサインアウトされるようにする場合は、[この手順に従ってください](https://kb.infinitecampus.com/help/sso-service-provider-configuration#SSOServiceProviderConfiguration-AddtheInfiniteCampusLogoutURLtotheMicrosoftAzureSAMLSSOConfiguration)。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Infinite Campus のサインオン URL にリダイレクトされます。
- Infinite Campus のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Infinite Campus] タイルを選択すると、このオプションは Infinite Campus のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。

### 非運用 Infinite Campus 環境 (サンドボックス、ステージング) での Azure SSO の構成

お客様の地区に他の Infinite Campus 環境がある場合は、このセットアップ プロセス全体を環境ごとに繰り返す必要があります。 たとえば、この地区に Infinite Campus サンドボックス サイトがある場合は、ギャラリーから Infinite Campus アプリをもう一度追加し、Infinite Campus サンドボックス サイト内で [SSO Service Provider Configuration] 画面を参照しながらプロセスを完了します。 この地区にはまた、たとえば、Infinite Campus ステージング サイトもある場合は、このプロセスを 3 回目として完了する必要があります。

このプロセスの詳細については、Infinite Campus の[ドキュメント](https://kb.infinitecampus.com/help/sso-service-provider-configuration#sandbox/staging/non-production-environments)を参照してください。

### 期限切れ間近の SAML 証明書の置き換え

ユーザーがシングル サインオンを通じて Infinite Campus に引き続きログインできるようにするには、この統合の SAML 証明書をいずれ更新することが必要になります。 Campus Messenger メール設定が適切に確立されている地区では、証明書の期限切れが近づくと、Infinite Campus から警告メールが送信されます。 (件名: "アクションが必要: 証明書の有効期限が切れています。"

期限切れ間近の SAML 証明書を置き換えるには、次の手順を実行します。

1. お客様の地区の Microsoft Entra 管理者に、Azure portal にサインインしてもらいます。
2. 左のナビゲーション ペインで、Microsoft Entra サービスを選択します。
3. [エンタープライズ アプリケーション] に移動し、前に設定した Infinite Campus アプリケーションを選択します。 (サンドボックスまたはステージング サイトなどの複数の Infinite Campus 環境がある場合は、ここに複数の Infinite Campus アプリケーションが設定されています。証明書の期限切れが近づいているものについて、それぞれの Infinite Campus 環境ごとにこのプロセスを完了する必要があります。)
4. [シングル サインオン] を選択します。
5. [SAML 証明書] に移動し、アプリのフェデレーション メタデータ URL をコピーします。
6. Infinite Campus 内で、[SSO Service Provider Configuration] ツールに移動し、構成を選択し、直前の手順でコピーしたアプリのフェデレーション メタデータ URL を [Metadata URL] フィールドに貼り付けます。
7. 別のウィンドウで、Azure portal に戻ります。 [SAML 証明書] の [トークン署名証明書] 領域で、[編集] を選択します。
8. [新しい証明書] を選択します。 必要に応じて有効期限を変更します。
9. [保存] を選択します。 (署名オプションと署名アルゴリズムはそのままにします)
10. Infinite Campus ウィンドウに戻り、メタデータ URL の横にある [同期] ボタンを選択します。 "IDP Synchronization successful" と表示されます。 [OK]、[Save] を選択します。
11. Azure portal に戻り、引き続き [SAML 署名証明書] 編集画面で、新しい証明書の横にある 3 つのドット (...) を選択します。 [証明書をアクティブにする] を選択し、[保存] を選択します。
12. 古い証明書の横にある 3 つのドットを選択します。 [証明書の削除] を選択します。
13. Infinite Campus に戻り、[Metadata URL] の横にある [Sync] ボタンをもう一度クリックします。 "IDP Synchronization successful" と再び表示されます。 [OK]、[Save] を再度クリックします。

これで、期限切れ間近の証明書を置き換えるプロセスが完了しました。 詳細については、Infinite Campus の[ドキュメント](https://kb.infinitecampus.com/help/sso-service-provider-configuration#SSOServiceProviderConfiguration-CertificateExpirationWarnings)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/infinityqs-proficient-on-demand-tutorial"} -->
## InfinityQS ProFicient on Demand for Single sign-on を Microsoft Entra ID で構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/infinityqs-proficient-on-demand-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と InfinityQS ProFicient on Demand の間にシングル サインオンを構成する方法について説明します。

この記事では、InfinityQS ProFicient on Demand と Microsoft Entra ID を統合する方法について説明します。 InfinityQS ProFicient on Demand と Microsoft Entra ID を統合すると、以下のことができます。

- InfinityQS ProFicient on Demand にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して InfinityQS ProFicient on Demand に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- InfinityQS ProFicient on Demand でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- InfinityQS ProFicient on Demand では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーから InfinityQS ProFicient on Demand を追加する

Microsoft Entra ID への InfinityQS ProFicient on Demand の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に InfinityQS ProFicient on Demand を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「InfinityQS ProFicient on Demand**」と入力します。
4. 結果パネルから **InfinityQS ProFicient on Demand** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### InfinityQS ProFicient on Demand 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、InfinityQS ProFicient on Demand に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと InfinityQS ProFicient on Demand の関連ユーザーとの間にリンク関係を確立する必要があります。

InfinityQS ProFicient on Demand との Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **InfinityQS ProFicient on Demand SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **InfinityQS ProFicient on Demand テスト ユーザーの作成** - Microsoft Entra のユーザー表現にリンクした、InfinityQS ProFicient on Demand 内で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**InfinityQS ProFicient on Demand**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **InfinityQS ProFicient on Demand のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### InfinityQS ProFicient on Demand SSO の構成

**InfinityQS ProFicient on Demand** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [InfinityQS ProFicient on Demand サポート チームに](mailto:support@infinityqs.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### InfinityQS ProFicient on Demand のテスト ユーザーの作成

このセクションでは、InfinityQS ProFicient on Demand で Britta Simon というユーザーを作成します。 [InfinityQS ProFicient on Demand サポート チーム](mailto:support@infinityqs.com)と協力して、InfinityQS ProFicient on Demand プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した InfinityQS ProFicient on Demand に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで InfinityQS ProFicient on Demand タイルを選択すると、SSO を設定した InfinityQS ProFicient on Demand に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/infogix-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Infogix Data3Sixty Govern を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/infogix-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Infogix Data3Sixty Govern の間でシングル サインオンを構成する方法について説明します。

この記事では、Infogix Data3Sixty Govern と Microsoft Entra ID を統合する方法について説明します。 Infogix Data3Sixty Govern を Microsoft Entra ID と統合すると、次のことが可能になります。

- Infogix Data3Sixty Govern にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Infogix Data3Sixty Govern に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Infogix Data3Sixty Govern でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Infogix Data3Sixty Govern では、**SP および IDP** Initiated SSO がサポートされます。
- Infogix Data3Sixty Govern では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Infogix Data3Sixty Govern の追加

Microsoft Entra ID への Infogix Data3Sixty Govern の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Infogix Data3Sixty Govern を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Infogix Data3Sixty Govern**」と入力します。
4. 結果パネルから **[Infogix Data3Sixty Govern]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Infogix Data3Sixty Govern 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Infogix Data3Sixty Govern で Microsoft Entra SSO を構成およびテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Infogix Data3Sixty Govern の関連ユーザーとの間にリンク関係を確立する必要があります。

Infogix Data3Sixty Govern に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Infogix Data3Sixty Govern の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Infogix Data3Sixty Govern のテストユーザーを作成** - Infogix Data3Sixty Govern において、Microsoft Entra のユーザーである B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID] **&gt;** [エンタープライズ アプリ] **&gt;** [Infogix Data3Sixty Govern] **&gt;** [シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://data3sixty.com/ui`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.data3sixty.com/sso/acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.data3sixty.com`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには、[Infogix Data3Sixty Govern クライアント サポート チーム](mailto:data3sixtysupport@infogix.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Infogix Data3Sixty Govern アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 このアプリケーションに対して次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの **ユーザー属性** セクションから管理できます。 [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** ボタンを選択して [ **ユーザー属性] ダイアログを** 開きます。

    [Image: このスクリーンショットは、[編集] アイコンが選択された状態の [User Attributes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー属性) を示しています。]
8. [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、[**編集] アイコン**を使用して要求を編集するか、[**新しい要求の追加]** を使用して要求を追加し、上の図に示すように SAML トークン属性を構成し、次の手順を実行します。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastname | ユーザーの名字 |
    | ユーザー名 | ユーザーのメールアドレス |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [新しい要求の追加] オプションが備わっている [ユーザー要求] のスクリーンショット。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **[OK]** を選択します。

    g. **保存** を選択します。
9. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Infogix Data3Sixty Govern のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Infogix Data3Sixty Govern の SSO の構成

**Infogix Data3Sixty Govern** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** とアプリケーション構成からコピーした適切な URL を [Infogix Data3Sixty Govern サポート チーム](mailto:data3sixtysupport@infogix.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Infogix Data3Sixty 制御テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Infogix Data3Sixty Govern に作成します。 Infogix Data3Sixty Govern では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は、既定で有効になっています。 このセクションにはアクション項目はありません。 Infogix Data3Sixty Govern にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Infogix Data3Sixty Govern サポート チーム](mailto:data3sixtysupport@infogix.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Infogix Data3Sixty Govern のサインオン URL にリダイレクトされます。
- Infogix Data3Sixty Govern のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Infogix Data3Sixty Govern に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Infogix Data3Sixty Govern タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Infogix Data3Sixty Govern に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/infor-cloud-suite-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Infor CloudSuite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/infor-cloud-suite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID および Infor CloudSuite 間にシングル サインオンを構成する方法について説明します。

この記事では、Infor CloudSuite と Microsoft Entra ID を統合する方法について説明します。 Infor CloudSuite を Microsoft Entra ID と統合すると、次のことが可能になります。

- Infor CloudSuite にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Infor CloudSuite に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオンが有効な Infor CloudSuite サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Infor CloudSuite では、**SP と IDP** によって開始される SSO がサポートされます
- Infor CloudSuite では、[**自動化された**ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/infor-cloudsuite-provisioning-tutorial) (推奨) がサポートされます。
- Infor CloudSuite では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーから Infor CloudSuite を追加する

Microsoft Entra ID への Infor CloudSuite の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Infor CloudSuite を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Infor CloudSuite**」と入力します。
4. 結果パネルで **[Infor CloudSuite]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Infor CloudSuite 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Infor CloudSuite に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Infor CloudSuite の関連ユーザーとの間にリンク関係を確立する必要があります。

Infor CloudSuite に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Infor CloudSuite の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Infor CloudSuite のテスト ユーザーを作成する** - Infor CloudSuite で B.Simon に対応するテスト ユーザーを作成し、このユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Infor CloudSuite**&gt;**シングルサインオン**にアクセスする。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    http://mingle-sso.inforcloudsuite.com
    http://mingle-sso.se1.inforcloudsuite.com
    http://mingle-sso.eu1.inforcloudsuite.com
    http://mingle-sso.se2.inforcloudsuite.com
    ```

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://mingle-sso.inforcloudsuite.com:443/sp/ACS.saml2
    https://mingle-sso.se1.inforcloudsuite.com:443/sp/ACS.saml2
    https://mingle-sso.se2.inforcloudsuite.com:443/sp/ACS.saml2
    https://mingle-sso.eu1.inforcloudsuite.com:443/sp/ACS.saml2
    ```
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://mingle-portal.inforcloudsuite.com/Tenant-Name/
    https://mingle-portal.eu1.inforcloudsuite.com/Tenant-Name/
    https://mingle-portal.se1.inforcloudsuite.com/Tenant-Name/
    https://mingle-portal.se2.inforcloudsuite.com/Tenant-Name/
    ```

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Infor CloudSuite クライアント サポート チーム](mailto:support@infor.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Infor CloudSuite のセットアップ]** セクションで、要件のとおりに適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Infor CloudSuite の SSO の構成

**Infor CloudSuite** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Infor CloudSuite サポート チーム](mailto:support@infor.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Infor CloudSuite テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Infor CloudSuite に作成します。 Infor CloudSuite では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Infor CloudSuite にユーザーがまだ存在していない場合は、認証後に新規に作成されます。 ユーザーを手動で作成する必要がある場合は、[Infor CloudSuite サポート チーム](mailto:support@infor.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Infor CloudSuite のサインオン URL にリダイレクトされます。
- Infor CloudSuite のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Infor CloudSuite に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Infor CloudSuite] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Infor CloudSuite に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/infor-cloudsuite-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Infor CloudSuite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/infor-cloudsuite-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: ユーザー アカウントを Infor CloudSuite に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Infor CloudSuite で実行する手順と、ユーザーやグループを Infor CloudSuite に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するMicrosoft Entra IDを示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Infor CloudSuite テナント](https://www.infor.com/products)
- Admin アクセス許可がある Infor CloudSuite のユーザー アカウント。

### Infor CloudSuite へのユーザーの割り当て

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Infor CloudSuite へのアクセスが必要なMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Infor CloudSuite に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Infor CloudSuite に割り当てるときの重要なヒント

- 1 人のMicrosoft Entra ユーザーを Infor CloudSuite に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Infor CloudSuite にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために Infor CloudSuite を設定する

1. [Infor CloudSuite 管理コンソール](https://www.infor.com/customer-center)にサインインします。 ユーザー アイコンを選択し、 **ユーザー管理**に移動します。

    [Image: Infor CloudSuite 管理コンソール]
2. 画面の左上隅にあるメニュー アイコンを選択します。 **管理**を選択します。

    [Image: Infor CloudSuite SCIM の追加]
3. **[SCIM Accounts](SCIM アカウント)** に移動します。

    [Image: Infor CloudSuite SCIM アカウント]
4. プラス アイコンを選択して、管理者ユーザーを追加します。 **[SCIM Password](SCIM パスワード)** を入力し、 **[Confirm Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワードの確認)** に同じパスワードを入力します。 フォルダー アイコンを選択してパスワードを保存します。 その後、管理者 **ユーザー** に対して生成されたユーザー識別子が表示されます。

    [Image: Infor CloudSuite 管理者ユーザー]

    [Image: Infor CloudSuite パスワード]
5. ベアラー トークンを生成するには、 **[ユーザー識別子]** と **[SCIM パスワード]** をコピーします。 コロンで区切って notepad++ に貼り付けます。 **[Plugins] (プラグイン) &gt; MIME Tools (MIME ツール) &gt; Basic64 Encode (Basic64 エンコード)** の順に選択して文字列値をエンコードします。

    [Image: Notepad++ ドキュメントのスクリーンショット。[Plugins](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プラグイン) メニューの [MIME tools](MIME ツール) が強調表示されています。[MIME tools](MIME ツール) メニューの [Base64 encode](Base64 エンコード) が強調表示されています。]

    Notepad++ の代わりに PowerShell を使用してベアラー トークンを生成するには、次のコマンドを使用します。

    ```powershell
    $Identifier = "<User Identifier>"
     $SCIMPassword = "<SCIM Password>"
     $bytes = [System.Text.Encoding]::UTF8.GetBytes($($Identifier):$($SCIMPassword))
     [Convert]::ToBase64String($bytes)
    ```
6. ベアラー トークンをコピーします。 この値は、Infor CloudSuite アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。

### ギャラリーから Infor CloudSuite を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Infor CloudSuite を構成する前に、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Infor CloudSuite を追加する必要があります。

** Microsoft Entra アプリケーション ギャラリーから Infor CloudSuite を追加するには、次の手順を実行します:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Infor CloudSuite**」と入力し、検索ボックスで **Infor CloudSuite** を選択します。
4. 結果パネルで **[Infor CloudSuite]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果リストの Infor CloudSuite]

### Infor CloudSuite への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Infor CloudSuite でユーザーやグループを作成、更新、無効化するようにMicrosoft Entraプロビジョニング サービスを構成する手順について説明します。

ヒント

Infor CloudSuite のシングル サインオンに関する記事で説明されている手順に従って、 [Infor CloudSuite](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/infor-cloud-suite-tutorial) で SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra IDで Infor CloudSuite の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Infor CloudSuite]** を選択します。

    [Image: アプリケーション一覧の Infor CloudSuite のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Infor CloudSuite テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Infor CloudSuite に接続できることを確認します。 接続に失敗した場合は、Infor CloudSuite アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    注

    `https://mingle-t20b-scim.mingle.awsdev.infor.com/INFORSTS_TST/v2/scim` に「」と入力します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Infor CloudSuite に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Infor CloudSuite のユーザー アカウントとの照合に使用されることに注意してください。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Infor CloudSuite API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Infor CloudSuite で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | ディスプレイ名 | 糸 |  |  |
    | エクスターナルID | 糸 |  |  |
    | 名前.姓 | 糸 |  |  |
    | 名前.名 | 糸 |  |  |
    | ディスプレイ名 | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:infor:2.0:User:actorId | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:infor:2.0:User:federationId | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:infor:2.0:User:ifsPersonId | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:infor:2.0:User:lnUser | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:infor:2.0:User:userAlias | 糸 |  |  |
12. **Attribute Mapping** セクションで、Microsoft Entra IDから Infor CloudSuite に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Infor CloudSuite のグループとの照合に使用されることに注意してください。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Infor CloudSuite で必須 |
    | --- | --- | --- | --- |
    | ディスプレイ名 | 糸 | ✓ | ✓ |
    | メンバー | リファレンス |  |  |
    | エクスターナルID | 糸 |  |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

- [プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)を使用して、正常にプロビジョニングされたユーザーと失敗したユーザーを特定します。
- [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
- プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)を参照してください。

### 変更履歴

2023 年 2 月 15 日 - カスタム拡張機能ユーザー属性 **urn:ietf:params:scim:schemas:extension:infor:2.0:User:actorId**、**urn:ietf:params:scim:schemas:extension:infor:2.0:User:federationId**、**urn:ietf:params:scim:schemas:extension:infor:2.0:User:ifsPersonId**、**urn:ietf:params:scim:schemas:extension:infor:2.0:User:inUser**、**urn:ietf:params:scim:schemas:extension:infor:2.0:User:userAlias** のサポートを追加しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/informacast-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に InformaCast を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/informacast-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: Microsoft Entra IDから InformaCast にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために InformaCast と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra ID が構成されている場合、Microsoft Entra プロビジョニング サービスを使用し、ユーザーとグループを [InformaCast](https://www.singlewire.com/informacast) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- InformaCast でユーザーを作成します。
- アクセスが不要になったら、InformaCast のユーザーを削除します。
- Microsoft Entra IDと InformaCast の間でユーザー属性の同期を維持します。
- InformaCast でグループとグループ メンバーシップをプロビジョニングします。
- InformaCast に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/informacast-tutorial)する (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- InformaCast テナント。
- Admin アクセス許可がある InformaCast のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとInformaCastの間でどのデータをマッピングするかを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように InformaCast を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように InformaCast を構成するには、InformaCast サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから InformaCast を追加する

Microsoft Entra アプリケーション ギャラリーから InformaCast を追加して、InformaCast へのプロビジョニングの管理を開始します。 SSO のために InformaCast を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: InformaCast への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで InformaCast の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[InformaCast]** を選択します。

    [Image: アプリケーション リストの InformaCast リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、InformaCast テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが InformaCast に接続できることを確認します。 接続に失敗した場合は、InformaCast アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. Microsoft Entra IDから InformaCast に同期されるユーザー属性を、**Attribute-Mapping** セクションで確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で InformaCast のユーザー アカウントを照合するために使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、InformaCast API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | InformaCast で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 | ✓ |  |
    | displayName | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | name.formatted | 糸 |  | ✓ |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | externalId | 糸 |  |  |
    | メール[タイプ eq "自宅"].値 | 糸 |  |  |
    | emails[タイプ eq "その他"].値 | 糸 |  |  |
    | 電話番号[タイプ eq "home"].値 | 糸 |  |  |
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから InformaCast に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で InformaCast のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | InformaCast で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/informacast-tutorial"} -->
## Microsoft Entra ID で InformaCast for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/informacast-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と InformaCast の間にシングル サインオンを構成する方法についてご確認ください。

この記事では、InformaCast と Microsoft Entra ID を統合する方法について説明します。 InformaCast を Microsoft Entra ID と統合すると、次のことができます。

- InformaCast にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して InformaCast に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

InformaCast は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- InformaCast でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- InformaCast では、**SP と IDP** 開始の SSO をサポートしています。
- InformaCast では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/informacast-provisioning-tutorial)。

### ギャラリーからの InformaCast の追加

Microsoft Entra ID への InformaCast の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに InformaCast を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**InformaCast**」と入力します。
4. [結果] パネルから **[InformaCast]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### InformaCast 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、InformaCast に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと InformaCast の関連ユーザーとの間にリンク関係を確立する必要があります。

InformaCast に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **InformaCast の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **InformaCast のテストユーザーを作成** - Microsoft Entra にリンクされている B.Simon に対応するユーザーを InformaCast で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**InformaCast**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://admin.icmobile.singlewire.com`
7. **保存** を選択します。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### InformaCast の SSO の構成

**InformaCast** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [InformaCast サポート チーム](mailto:support@singlewire.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### InformaCast のテスト ユーザーの作成

このセクションでは、InformaCast で Britta Simon というユーザーを作成します。 [InformaCast サポート チーム](mailto:support@singlewire.com)と連携し、InformaCast プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

1. [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる InformaCast のサインオン URL にリダイレクトされます。
2. InformaCast のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した InformaCast に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで InformaCast タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した InformaCast に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/informatica-intelligent-data-management-cloud-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Informatica Intelligent Data Management Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/informatica-intelligent-data-management-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Informatica Intelligent Data Management Cloud の間でシングル サインオンを構成する方法について学習します。

この記事では、Informatica Intelligent Data Management Cloud と Microsoft Entra ID を統合する方法について説明します。 Azure Native Services で Informatica Intelligent Data Management Cloud を有効にする SAML SSO Auth アプリケーションです。 Informatica Intelligent Data Management Cloud と Microsoft Entra ID を統合すると、次のことができます。

- Informatica Intelligent Data Management Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して、Informatica Intelligent Data Management Cloud に自動的にサインイン可能にする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Informatica Intelligent Data Management Cloud 用に Microsoft Entra のシングル サインオンを構成およびテストします。 Intelligent Data Management Cloud では、**SP** および **IDP** Initiated の両方のシングル サインオンと、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

### [前提条件]

Microsoft Entra ID を Informatica Intelligent Data Management Cloud と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Informatica Intelligent Data Management Cloud シングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Informatica Intelligent Data Management Cloud アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Informatica Intelligent Data Management Cloud を追加する

Microsoft Entra アプリケーション ギャラリーから Informatica Intelligent Data Management Cloud を追加して、Informatica Intelligent Data Management Cloud でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**Informatica Intelligent Data Management Cloud**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<ORG_ID>.<REGION>.informaticacloud.com`
    2. [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<REGION>.informaticacloud.com/identity-service/acs/<ORG_ID>`
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<REGION>.informaticacloud.com/ma/sso/<ORG_ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Informatica Intelligent Data Management Cloud サポート チーム](mailto:support@informatica.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (Base64)]** を見つけます。**[ダウンロード]** を選択して証明書をダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Informatica Intelligent Data Management Cloud のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Informatica Intelligent Data Management Cloud SSO の構成

1. Informatica Intelligent Data Management Cloud 企業サイトに管理者としてログインします。
2. **[管理者]**&gt;**[SAML セットアップ]** に移動し、次の手順を実行します。

    [Image: Brainfuse の設定ページを示すスクリーンショット。]

    1. **[発行者]** テキストボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。
    2. **[シングル サインオン サービス URL]** テキストボックスに、先ほどコピーした**ログイン URL** を貼り付けます。
    3. **[シングル ログアウト サービス URL]** テキストボックスに、先ほどコピーした**ログアウト URL** を貼り付けます。
    4. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[署名証明書]** ボックスに貼り付けます。
    5. [ **保存] を** 選択して詳細を保存します。

#### Informatica Intelligent Data Management Cloud テスト ユーザーを作成する

このセクションでは、Informatica Intelligent Data Management Cloud で B.Simon というユーザーを作成します。 Informatica Intelligent Data Management Cloud では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Informatica Intelligent Data Management Cloud にユーザーがまだ存在していない場合、通常、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Informatica Intelligent Data Management Cloud のサインオン URL にリダイレクトされます。
- Informatica Intelligent Data Management Cloud サインオン URL に直接アクセスし、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Informatica Intelligent Data Management Cloud に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Informatica Intelligent Data Management Cloud タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Informatica Intelligent Data Management Cloud に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/informatica-platform-tutorial"} -->
## Microsoft Entra ID を使用して Informatica Platform for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/informatica-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Informatica の間のシングル サインオンを構成する方法について説明します。

この記事では、Informatica Platform と Microsoft Entra ID を統合する方法について説明します。 Informatica Platform を Microsoft Entra ID と統合すると、次のことが可能になります。

- Informatica Platform にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Informatica Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Informatica Platform でのシングル サインオン (SSO) 対応のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Informatica Platform は **SP** と **IDP** によって開始された SSO をサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Informatica Platform の追加

Microsoft Entra ID への Informatica Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Informatica Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Informatica Platform**」と入力します。
4. 結果パネルから **[Informatica Platform]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Informatica Platform 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Informatica Platform に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと、Informatica Platform での関連ユーザーとの間にリンク関係を確立する必要があります。

Informatica Platform に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Informatica Platform の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Informatica Platform のテストユーザーを作成** - Microsoft Entra のユーザーである B.Simon に対応したユーザーを Informatica Platform 上で作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Informatica Platform**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** テキスト ボックスに、次の値または URL パターンを入力します。

    | アプリ | URL |
    | --- | --- |
    | EDC の場合 | `Informatica` |
    | Axon の場合 | `https://<host name: port number>/saml/metadata` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<host name: port number>/administrator/Login.do`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<host name: port number>/administrator/saml/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Informatica Platform のクライアント サポート チーム](mailto:support@informatica.com) にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Informatica Platform アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Informatica Platform アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
    | 管理者 | User.givenname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Informatica Platform のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Informatica Platform SSO を構成する

**Informatica Platform** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Informatica Platform サポート チーム](mailto:support@informatica.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Informatica Platform テスト ユーザーを作成する

このセクションでは、Informatica Platform で Britta Simon というユーザーを作成します。 [Informatica Platform サポート チーム](mailto:support@informatica.com)と協力して、Informatica Platform にユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Informatica Platform のサインオン URL にリダイレクトされます。
- Informatica Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Informatica Platform に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Informatica Platform] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Informatica Platform に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/inforretailinformationmanagement-tutorial"} -->
## Infor Retail の情報管理を Microsoft Entra ID を使用してシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/inforretailinformationmanagement-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Infor Retail - Information Management の間でシングル サインオンを構成する方法について説明します。

この記事では、Infor Retail - Information Management と Microsoft Entra ID を統合する方法について説明します。 Infor Retail - Information Management を Microsoft Entra ID と統合すると、次のことができます。

- Infor Retail - Information Management にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra Iアカウントを使用して Infor Retail – Information Management に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Infor Retail – Information Management シングル サインオン対応のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Infor Retail – Information Management は、**SP および IDP** によって開始される SSO をサポートします。

### ギャラリーからの Infor Retail - Information Management の追加

Microsoft Entra ID への Infor Retail - Information Management の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Infor Retail – Information Management を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Infor Retail – Information Management**」と入力します。
4. 結果パネルから **Infor Retail – Information Management** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Infor Retail – Information Management の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Infor Retail – Information Management に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Infor Retail – Information Management の関連ユーザーの間にリンク関係を確立する必要があります。

Infor Retail – Information Management を使用した Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Infor Retail Information Management の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Infor Retail - Information Management のテスト ユーザーの作成** - Infor Retail - Information Management に、Microsoft Entra でのユーザー表現にリンクされた B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Infor Retail – Information Management**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://<COMPANY_NAME>.mingle.infor.com` |
    | `http://<COMPANY_NAME>.mingledev.infor.com` |
    |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.mingle.infor.com/sp/ACS.saml2`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.mingle.infor.com/<COMPANY_CODE>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Infor Retail – Information Management クライアント サポート チーム](mailto:innovate@infor.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Infor Retail – Information Management のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Infor Retail - Information Management SSO の構成

**Infor Retail – Information Management** 側でシングル サインオンを構成するには、ダウンロードした**メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Infor Retail - Information Management サポート チーム](mailto:innovate@infor.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Infor Retail - Information Management テスト ユーザーの作成

このセクションでは、Infor Retail - Information Management で Britta Simon というユーザーを作成します。 [Infor Retail - Information Management サポート チーム](mailto:innovate@infor.com)と協力して、Infor Retail – Information Management プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Infor Retail – Information Management のサインオン URL にリダイレクトされます。
- Infor Retail – Information Management のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Infor Retail – Information Management に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Infor Retail – Information Management] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Infor Retail – Information Management に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/infrascale-cloud-backup-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Infrascale Cloud Backup を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/infrascale-cloud-backup-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Infrascale Cloud Backup の間でシングル サインオンを構成する方法について説明します。

この記事では、Infrascale Cloud Backup と Microsoft Entra ID を統合する方法について説明します。 Infrascale Cloud Backup を Microsoft Entra ID と統合すると、次のことが可能になります。

- Infrascale Cloud Backup にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Infrascale Cloud Backup に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Infrascale Cloud Backup でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Infrascale Cloud Backup では、**SP** によって開始される SSO がサポートされます。

### ギャラリーから Infrascale Cloud Backup を追加する

Microsoft Entra ID への Infrascale Cloud Backup の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Infrascale Cloud Backup を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Infrascale Cloud Backup**」と入力します。
4. 結果のパネルから **[Infrascale Cloud Backup]** を選び、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Infrascale Cloud Backup に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Infrascale Cloud Backup で Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Infrascale Cloud Backup の関連ユーザーとの間にリンク関係を確立する必要があります。

Infrascale Cloud Backup で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cloud Backup SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Infrascale Cloud Backup テストユーザーの作成 - B.Simon に対応するユーザーを Infrascale Cloud Backup に作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Infrascale Cloud Backup**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://dashboard.sosonlinebackup.com/<ID>`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://dashboard.managedoffsitebackup.net/Account/AssertionConsumerService`

    c. **[サインオン URL]** テキスト ボックスには、会社の特定の URL が必要です。 URL の一般的なパターンは `https://[OptionalPrefix]dashboard[OptionalSuffix].CompanySpecificString.[com/net,etc]/Account/SingleSignOn` です。

    注

    [サインオン URL] テキスト ボックスに入力しないでください。 識別子の値は実際の URL ではなく、一般的なパターンにすぎません。 この値を、Infrascale から取得した実際の識別子 URL で更新します。 この値を取得するには [Infrascale Cloud Backup サポート チーム](mailto:support@infrascale.com)にお問い合わせください。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Infrascale Cloud Backup SSO の構成

1. ご自分の Infrascale Cloud Backup 企業サイトに管理者としてログインします。
2. **[設定]**&gt;**[シングル サインオン]** and select **[シングル サインオン (SSO) を有効にする]** に移動します。
3. **[シングル サインオン設定]** ページで、次の手順を実行します。

    [Image: 構成設定を示すスクリーンショット。]

    a. **[Service Provider EntityID] ** の値をコピーし、この値を **[基本的な SAML 構成]** セクションの **[識別子]** テキスト ボックスに貼り付けます。

    b。 **[応答 URL]** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。

    c. [ID プロバイダーの設定] セクションの **[メタデータ URL 経由]** ボタンを選びます。

    d. **[アプリのフェデレーション メタデータ URL]** をコピーし、**[メタデータ URL]** テキストボックスに貼り付けます。

    e. **保存** を選択します。

#### Infrascale Cloud Backup テスト ユーザーの作成

このセクションでは、Infrascale Cloud Backup で Britta Simon というユーザーを作成します。 [Infrascale Cloud Backup サポート チーム](mailto:support@infrascale.com)と連携し、Infrascale Cloud Backup プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Infrascale Cloud Backup Sign-On URL にリダイレクトされます。
- Infrascale Cloud Backup のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Infrascale Cloud Backup] タイルを選択すると、このオプションは Infrascale Cloud Backup Sign-On URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/inkling-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Inkling/EchoInk を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/inkling-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Inkling / EchoInk の間でシングル サインオンを構成する方法について説明します。

この記事では、Inkling/Echolink/Echolink と Microsoft Entra ID を統合する方法について説明します。 Inkling/Echolink/Echolink と Microsoft Entra ID を統合すると、次のことができます。

- Inkling/EchoInk にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Inkling/EchoInk に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Inkling/EchoInk でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Inkling/EchoInk では、 **IDP** によって開始される SSO がサポートされます。

### ギャラリーからの Inkling/EchoInk の追加

Microsoft Entra ID への Inkling/ EchoInk の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Inkling/ EchoInk を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Inkling/EchoInk」と**入力します。
4. 結果パネルから **Inkling/EchoInk** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Inkling/EchoInk の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Inkling/EchoInk に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Inkling / EchoInk の関連ユーザーとの間にリンク関係を確立する必要があります。

Inkling/EchoInk に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Inkling/EchoInk SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Inkling / EchoInk テスト ユーザーの作成** - Inkling / EchoInk で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Inkling/EchoInk**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] のスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.inkling.com/saml/v2/metadata/<user-id>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.inkling.com/saml/v2/acs/<user-id>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Inkling/EchoInk クライアント サポート チーム](mailto:support@echo360.com) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
7. [ **Inkling/ EchoInk のセットアップ** ] セクションで、要件に従って 1 つ以上の適切な URL をコピーします。

    [Image: [構成 URL のコピー] のスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Inkling /EchoInk SSO の構成

**Inkling/EchoInk** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Inkling/EchoInk サポート チーム](mailto:support@echo360.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Inkling/EchoInk テスト ユーザーの作成

このセクションでは、Inkling / EchoInk で Britta Simon というユーザーを作成します。 [Inkling/EchoInk サポート チーム](mailto:support@echo360.com)と協力して、Inkling/EchoInk プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Inkling/EchoInk に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Inkling/ EchoInk] タイルを選択すると、SSO を設定した Inkling/EchoInk に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/innotas-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Innotas を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/innotas-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Innotas の間のシングル サインオンを構成する方法について説明します。

この記事では、Innotas と Microsoft Entra ID を統合する方法について説明します。 Innotas と Microsoft Entra ID を統合すると、次のことができます。

- Innotas にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Innotas に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Innotas でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Innotas では、 **SP** によって開始される SSO がサポートされます。
- Innotas では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Innotas の追加

Microsoft Entra ID への Innotas の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Innotas を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Innotas**」と入力します。
4. 結果のパネルから **[Innotas** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Innotas 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Innotas に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Innotas の関連ユーザーとの間にリンク関係を確立する必要があります。

Innotas に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Innotas SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Innotas テスト ユーザーの作成** - Microsoft Entra における B.Simon の表現とリンクする Innotas 内の B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Innotas**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-name>.Innotas.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、Innotas クライアント サポート チーム](https://www.innotas.com/contact) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Innotas のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Innotas SSO の構成

**Innotas** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Innotas サポート チーム](https://www.innotas.com/contact)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Innotas のテスト ユーザーの作成

Innotas へのユーザー プロビジョニングを構成するためのアクション項目はありません。 割り当てられたユーザーがアクセス パネルを使用して Innotas にサインインしようとすると、そのユーザーが存在するかどうかが Innotas によって確認されます。 使用可能なユーザー アカウントがまだない場合は、Innotas によって自動的に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Innotas のサインオン URL にリダイレクトされます。
- Innotas のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Innotas] タイルを選択すると、このオプションは Innotas のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/innovationhub-tutorial"} -->
## Microsoft Entra ID で Innoverse for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/innovationhub-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Innoverse の間にシングル サインオンを構成する方法について説明します。

この記事では、Innoverse と Microsoft Entra ID を統合する方法について説明します。 Innoverse を Microsoft Entra ID を統合すると、次のことができます。

- Innoverse にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Innoverse に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Innoverse でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Innoverse では、**SP と IDP** によって開始される SSO がサポートされます
- Innoverse では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Innoverse の追加

Microsoft Entra ID への Innoverse の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Innoverse を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Innoverse**」と入力します。
4. 結果のパネルから **[Innoverse]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Innoverse 用に Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使って、Innoverse に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Innoverse の関連ユーザーとの間にリンク関係を確立する必要があります。

Innoverse に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Innoverse の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Innoverse のテストユーザーを作成** - InnoverseでB.Simonに対応するユーザーを用意し、そのユーザーがMicrosoft Entraに反映されるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Innoverse**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    イ. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domainname>.innover.se`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domainname>.innover.se/auth/saml2/login`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domainname>.innover.se/auth/saml2/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Innoverse クライアント サポート チーム](mailto:support@readify.net)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Innoverse アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Innoverse アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | Namespace | ソース属性 |
    | --- | --- | --- |
    | 表示名 | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` | `user.userprincipalname` |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Innoverse の SSO の構成

**Innoverse** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Innoverse サポート チーム](mailto:support@readify.net)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Innoverse のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Innoverse に作成します。 Innoverse では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Innoverse にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Innoverse] タイルを選択すると、SSO を設定した Innoverse に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/insider-tutorial"} -->
## Microsoft Entra ID で Insider for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insider-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Insider の間でシングル サインオンを構成する方法について説明します。

この記事では、Insider と Microsoft Entra ID を統合する方法について説明します。 Insider と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で、誰が Insider にアクセスできるかを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Insider に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Insider でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Insider は、**SP および IDP によって開始された SSO**をサポートしています。
- Insider では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Insider を追加する

Microsoft Entra ID への Insider の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Insider を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに「**Insider**」と入力します。
4. 結果パネルから **Insider** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Insider の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Insider に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Insider の関連ユーザーとの間にリンク関係を確立する必要があります。

Insider に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Insider SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Insider テスト ユーザーの作成** - Insider で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Insider**&gt;**シングルサインオン**をブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://inone.useinsider.com/sso/<Workplace_ID>/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://inone.useinsider.com/sso/<Workplace_ID>/acs`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://inone.useinsider.com/sso/<Workplace_ID>/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Insider サポート チーム](mailto:bytemasters@useinsider.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **Insider のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Insider SSO の構成

**Insider** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [Insider サポート チーム](mailto:bytemasters@useinsider.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Insider テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Insider に作成します。 Insider では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Insider にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Insider サインオン URL にリダイレクトします。
- Insider のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Insider に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Insider タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Insider に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/insidertrack-tutorial"} -->
## Microsoft Entra ID で Insider Track for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insidertrack-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Insider Track 間のシングル サインオンを構成する方法について説明します。

この記事では、Insider Track と Microsoft Entra ID を統合する方法について説明します。 Insider Track と Microsoft Entra ID の統合には、次のメリットがあります。

- Insider Track にアクセスできるユーザーを Microsoft Entra ID で管理できます。
- ユーザーが自分の Microsoft Entra アカウントで Insider Track に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Insider Track でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Insider Track では、 **SP** Initiated SSO がサポートされます

### ギャラリーからの Insider Track の追加

Microsoft Entra ID への Insider Track の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Insider Track を追加する必要があります。

**ギャラリーから Insider Track を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **Insider Track**」と入力し、結果パネルから **Insider Track** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: Insider Track が結果一覧にあります]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Insider Track で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Insider Track 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Insider Track で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Insider Track シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Insider Track のテストユーザーを作成** - Insider Track で Britta Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の Britta Simon にリンクします。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Insider Track で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Insider Track** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: Insider Track のドメインと URL のシングル サインオン情報]

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>/InsiderTrack.Portal.<companyname>/Sso/`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 値を取得するには、 [Insider Track クライアント サポート チーム](https://cytecsolutions.com/contact/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Insider Track のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Insider Track のシングル サインオンの構成

**Insider Track** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Insider Track サポート チーム](https://cytecsolutions.com/contact/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Insider Track のテスト ユーザーの作成

このセクションでは、Insider Track で Britta Simon というユーザーを作成します。 [Insider Track サポート チーム](https://cytecsolutions.com/contact/) と協力して、Insider Track プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Insider Track] タイルを選択すると、SSO を設定した Insider Track に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/insideview-tutorial"} -->
## Microsoft Entra ID で InsideView for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insideview-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: この記事では、Microsoft Entra ID と InsideView の間でシングル サインオンを構成する方法について説明します。

この記事では、InsideView と Microsoft Entra ID を統合する方法について説明します。 この統合には、次の利点があります。

- Microsoft Entra ID を使用して誰が InsideView にアクセスできるかを制御できます。
- ユーザーが自分の Microsoft Entra アカウントで InsideView に自動的にサインイン (シングル サインオン) されるようにすることができます。
- 1 つの中央サイト (Azure ポータル) でアカウントを管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「[Microsoft Entra ID でのアプリケーションへのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)」を参照してください。

Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### 前提条件

Microsoft Entra の InsideView との統合を構成するには、以下のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra の環境がない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオンが有効な InsideView サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- InsideView では、IdP Initiated SSO がサポートされます。

### ギャラリーからの InsideView の追加

Microsoft Entra ID への InsideView の統合を設定するには、InsideView をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションを追加するには、ウィンドウの上部の **[新しいアプリケーション]** を選択します。

    [Image: [新しいアプリケーション] を選択する]
4. 検索ボックスに「**InsideView**」と入力します。 検索結果で **[InsideView]** を選択し、**[追加]** を選択します。

    [Image: 検索結果]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、Britta Simon というテスト ユーザーを使用して、InsideView で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを有効にするには、Microsoft Entra ユーザーと InsideView の対応するユーザーの間の関係を確立する必要があります。

InsideView での Microsoft Entra シングル サインオンを構成してテストするには、以下の手順を完了する必要があります。

1. **Microsoft Entra のシングル サインオンを構成**して、この機能をユーザーに対して有効にします。
2. アプリケーション側で **InsideView シングル サインオンを構成**します。
3. **Microsoft Entra テスト ユーザーを作成** して、Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てて、ユーザー** の Microsoft Entra シングル サインオンを有効にします。
5. ユーザーの Microsoft Entra 表現にリンクされる **InsideView テスト ユーザーを作成します**。
6. **シングル サインオンをテスト** して、この構成が機能することを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

InsideView での Microsoft Entra シングル サインオンを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動します&gt;**InsideView**
3. **[シングル サインオン]** を選びます。

    [Image: [シングル サインオン] の選択]
4. **[シングル サインオン方式の選択]** ダイアログ ボックスで、 **[SAML/WS-Fed]** モードを選択して、シングル サインオンを有効にします。

    [Image: シングル サインオン方式の選択]
5. **[SAML でシングル サインオンをセットアップします]** ページで、**編集**アイコンを選択して **[基本的な SAML 構成]** ダイアログ ボックスを開きます。

    [Image: 編集アイコン]
6. **[基本的な SAML 構成]** ダイアログ ボックスで、次の手順を実行します。

    [Image: [基本的な SAML 構成] ダイアログ ボックス]

    **[応答 URL]** ボックスに、次のパターンで URL を入力します。

    `https://my.insideview.com/iv/<STS Name>/login.iv`

    注

    この値は、プレースホルダーです。 実際の応答 URL を使用する必要があります。 この値を取得するには、[InsideView サポート チーム](mailto:support@insideview.com)に連絡してください。 **[基本的な SAML 構成]** ダイアログ ボックスに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、要件に従って **[証明書 (未加工)]** の横にある **[ダウンロード]** リンクを選択し、証明書をコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Set up InsideView]\(InsideView の設定\)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピー]

    1. **ログイン URL**。
    2. **Microsoft Entra 識別子**。
    3. **[ログアウト URL]** 。

#### InsideView シングル サインオンの構成

1. 新しい Web ブラウザー ウィンドウで、InsideView 企業サイトに管理者としてサインインします。
2. ウィンドウ上部で **[Admin]\(管理\)**、**[SingleSignOn Settings]\(シングル サインオンの設定\)**、**[Add SAML]\(SAML の追加\)** の順に選択します。

    [Image: SAML シングル サインオンの設定]
3. **[Add a New SAML]\(新しい SAML の追加\)** セクションで、次の手順を実行します。

    [Image: 新しい SAML セクションを追加]

    1. **[STS Name]\(STS 名\)** ボックスに、構成の名前を入力します。
    2. **[SamlP/WS-Fed 未承諾エンドポイント]** ボックスに、コピーした **[ログイン URL]** の値を貼り付けます。
    3. ダウンロードした未加工の証明書を開きます。 証明書の内容をクリップボードにコピーしてから、その内容を **[STS Certificate]\(STS 証明書\)** ボックスに貼り付けます。
    4. **[Crm User Id Mapping]\(Crm ユーザー ID マッピング\)** ボックスに、「**`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`**」と入力します。
    5. **[Crm Email Mapping]\(Crm メール マッピング\)** ボックスに、「**`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`**」と入力します。
    6. **Crm First Name Mapping** ボックスに、**`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`**を入力します。
    7. **Crm lastName Mapping** ボックスに、「**`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname`**」と入力します。
    8. **保存** を選択します。

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、Britta Simon という名前のテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部で **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. "**表示名**" フィールドに「`B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、`B.Simon@contoso.com` のようにします。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **を選択して**を作成します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、Britta Simon に InsideView へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**InsideView** に移動します。

    [Image: アプリケーションの一覧]
3. 左側のウィンドウで **[ユーザーとグループ]** を選択します。

    [Image: [ユーザーとグループ] の選択]
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログ ボックスで **[ユーザーとグループ]** を選択します。

    [Image: [ユーザーの追加] を選択する]
5. [ **ユーザーとグループ** ] ダイアログ ボックスで、ユーザーの一覧で **Britta Simon** を選択し、ウィンドウの下部にある **[選択** ] ボタンを選択します。
6. SAML アサーション内にロール値が必要な場合、 **[ロールの選択]** ダイアログ ボックスで、一覧からユーザーに適したロールを選択します。 ウィンドウの下部にある **[選択** ] ボタンを選択します。
7. **[割り当ての追加]** ダイアログ ボックスで **[割り当て]** を選びます。

#### InsideView のテスト ユーザーの作成

Microsoft Entra ユーザーが InsideView にサインインできるようにするには、ユーザーを InsideView に追加する必要があります。 手動で追加する必要があります。

InsideView でユーザーまたは連絡先を作成するには、[InsideView サポート チーム](mailto:support@insideview.com)にお問い合わせください。

注

InsideView よって提供されているユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

#### シングル サインオンのテスト

次に、アクセス パネルを使って Microsoft Entra のシングル サインオン構成をテストする必要があります。

アクセス パネルで [InsideView] タイルを選択すると、SSO を設定した InsideView インスタンスに自動的にサインインします。 アクセス パネルの詳細については、「[マイ アプリ ポータルでアプリにアクセスして使用する](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/insight4grc-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Insight4GRC を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insight4grc-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: Microsoft Entra IDから Insight4GRC にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Insight4GRC とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 Microsoft Entra ID が構成されると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Insight4GRC](https://www.rsmuk.com/) に自動でプロビジョニングおよびプロビジョニング解除が行われます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Insight4GRC でユーザーを作成する
- アクセスが不要になった場合に Insight4GRC のユーザーを削除する
- Microsoft Entra IDと Insight4GRC の間でユーザー属性の同期を維持する
- Insight4GRC でグループとグループ メンバーシップをプロビジョニングする
- Insight4GRC に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insight4grc-tutorial) (推奨)

Insight4GRC は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- [プロビジョニングを構成する権限](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ([Application Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [Application Owner](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications) など)。
- 管理者アクセス許可がある Insight4GRC のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとInsight4GRCの間で[マップするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDを使用したプロビジョニングをサポートするように Insight4GRC を構成する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Insight4GRC を構成する前に、Insight4GRC で SCIM プロビジョニングを有効にする必要があります。

1. ベアラー トークンを取得するには、エンド ユーザーが [サポート チーム](mailto:support.ss@rsmuk.com)に連絡する必要があります。
2. SCIM エンドポイント URL を取得するには、SCIM エンドポイント URL の構築に使用される Insight4GRC ドメイン名を準備する必要があります。 Insight4GRC ドメイン名は、Insight4GRC を使用した最初のソフトウェア購入の一部として取得できます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Insight4GRC を追加する

Microsoft Entra アプリケーション ギャラリーから Insight4GRC を追加して、Insight4GRC へのプロビジョニングの管理を開始します。 SSO のために Insight4GRC を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Insight4GRC への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Insight4GRC の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps** にアクセスします。

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Insight4GRC]** を選択します。

    [Image: アプリケーションの一覧の [Insight4GRC] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションの **[テナント URL]** に、SCIM エンドポイントの URL を入力します。 エンドポイント URL は `https://<Insight4GRC Domain Name>.insight4grc.com/public/api/scim/v2` という形式にする必要があり、**Insight4GRC Domain Name** は前の手順で取得した値です。 先ほど取得したベアラー トークン値を **シークレット トークン**に入力します。 **Test Connection** を選択して、Microsoft Entra IDが Insight4GRC に接続できることを確認します。 接続できない場合は、使用中の Insight4GRC アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: スクリーンショットには、[管理者資格情報] ダイアログ ボックスが表示され、テナント U R L とシークレット トークンを入力できます。]
7. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
8. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
9. **Attribute-Mapping** セクションで、Microsoft Entra IDから Insight4GRC に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Insight4GRC のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Insight4GRC API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | externalId | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |
    | タイトル | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | emails[type eq "work"].value | 糸 |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |
10. **Attribute-Mapping** セクションで、Microsoft Entra IDから Insight4GRC に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Insight4GRC のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | externalId | 糸 |
    | members | リファレンス |
11. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
12. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
13. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

- 2021/08/19 - エンタープライズ拡張ユーザー属性 **manager** が追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/insight4grc-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Insight4GRC を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insight4grc-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Insight4GRC の間でシングル サインオンを構成する方法について説明します。

この記事では、Insight4GRC と Microsoft Entra ID を統合する方法について説明します。 Insight4GRC と Microsoft Entra ID を統合すると、次のことができます。

- Insight4GRC にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Insight4GRC に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

Insight4GRC は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Insight4GRC でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Insight4GRC では、**SP と IDP** によって開始される SSO がサポートされます。
- Insight4GRC は、**Just In Time ユーザープロビジョニング** をサポートします。
- Insight4GRC は [自動ユーザープロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insight4grc-provisioning-tutorial)をサポートしています。

### ギャラリーから Insight4GRC を追加する

Microsoft Entra ID への Insight4GRC の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Insight4GRC を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Insight4GRC**」と入力します。
4. 結果パネル **Insight4GRC** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Insight4GRC の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Insight4GRC に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Insight4GRC の関連ユーザーとの間にリンク関係を確立する必要があります。

Insight4GRC に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Insight4GRC SSO**の構成 - アプリケーション側で単一 Sign-On 設定を構成します。
    1. **Insight4GRC のテストユーザーを作成** - Britta Simon に対応し、Microsoft Entra 上のユーザー表現にリンクする Insight4GRC のユーザーを作成するため。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Insight4GRC**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを**IDP** イニシエートモードで構成する場合は、次の手順を実行します。

    a. [**識別子**] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.Insight4GRC.com/SAML`

    b。 [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.Insight4GRC.com/auth/saml/sp/assertion-consumer-service`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.Insight4GRC.com`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Insight4GRC クライアント サポート チーム](mailto:support.ss@rsmuk.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Insight4GRC SSO の構成

Insight4GRC **側** シングル サインオンを構成するには、**アプリフェデレーション メタデータ URL** を Insight4GRC サポート チーム 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Insight4GRC テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Insight4GRC に作成します。 Insight4GRC では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Insight4GRC にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

手記

Insight4GRC では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらを参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Insight4GRC サインオン URL にリダイレクトされます。
- Insight4GRC のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Insight4GRC に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Insight4GRC タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Insight4GRC に自動的にサインインされます。 詳細については、「[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/insightly-saml-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Insightly SAML を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insightly-saml-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-11
- Summary: Microsoft Entra ID から Insightly SAML にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Insightly SAML と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーを [Insightly SAML に自動的に](https://www.insightly.com/) プロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Insightly SAML でユーザーを作成します。
- アクセスが不要になった場合は、Insightly SAML のユーザーを削除します。
- Microsoft Entra ID と Insightly SAML の間でユーザー属性の同期を維持します。
- Insightly SAML に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insightly-saml-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Insightly SAML のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- [Microsoft Entra ID と Insightly SAML の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Insightly SAML を構成する

Microsoft Entra ID を使用したプロビジョニングをサポートするように Insightly SAML を構成するには、Insightly SAML サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Insightly SAML を追加する

Microsoft Entra アプリケーション ギャラリーから Insightly SAML を追加して、Insightly SAML へのプロビジョニングの管理を開始します。 SSO 用に Insightly SAML を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Insightly SAML への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて Insightly SAML のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Insightly SAML の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Insightly SAML**] を選択します。

    [Image: アプリケーションの一覧の [Insightly SAML] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Insightly SAML テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Insightly SAML に接続できることを確認します。 接続に失敗した場合は、Insightly SAML アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Insightly SAML に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために Insightly SAML のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Insightly SAML API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Insightly SAML で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | 名前.名 | 糸 |  | ✓ |
    | 名前.姓 | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/insightly-saml-tutorial"} -->
## Microsoft Entra ID で Insightly SAML for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insightly-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Insightly SAML の間のシングル サインオンを構成する方法について説明します。

この記事では、Insightly SAML と Microsoft Entra ID を統合する方法について説明します。 Insightly SAML を Microsoft Entra ID と統合すると、次のことが可能になります。

- Insightly SAML にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Insightly SAML に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Insightly SAML でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Insightly SAML では、**IDP** によって開始される SSO がサポートされています。

### ギャラリーからの Insightly SAML の追加

Microsoft Entra ID への Insightly SAML の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧にInsightly SAML を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Insightly SAML**」と入力します。
4. 結果のパネルから **[Insightly SAML]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Insightly SAML の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Insightly SAML に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Insightly SAML の関連ユーザーとの間にリンク関係を確立する必要があります。

Insightly SAML に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Insightly SAML SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Insightly SAML テストユーザーを作成する** - Insightly SAML で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra ID で表される B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Insightly SAML**&gt;**シングルサインオン**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://crm.na1.insightly.com/user/saml?instanceId=<ID>` |
    | `https://crm.au1.insightly.com/user/saml?instanceId=<ID>` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://crm.na1.insightly.com/user/saml?instanceId=<ID>` |
    | `https://crm.au1.insightly.com/user/saml?instanceId=<ID>` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Insightly SAML サポート チーム](mailto:support@insight.ly)に連絡してください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Insightly SAML の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Insightly SAML SSO の構成

**Insightly SAML** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と Microsoft Entra 管理センターからコピーした適切な URL を [Insightly SAML サポート チーム](mailto:support@insight.ly)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Insightly SAML テスト ユーザーの作成

このセクションでは、Insightly SAML で B.Simon というユーザーを作成します。 [Insightly SAML サポート チーム](mailto:support@insight.ly)と連携して、Insightly SAML プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した Insightly SAML に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Insightly SAML] タイルを選択すると、SSO を設定した Insightly SAML に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/insightsfirst-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Insightsfirst を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insightsfirst-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Insightsfirst 間にシングル サインオンを構成する方法について説明します。

この記事では、Insightsfirst と Microsoft Entra ID を統合する方法について説明します。 Insightsfirst を Microsoft Entra ID と統合すると、次のことができます。

- Insightsfirst にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Insightsfirst に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Insightsfirst のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Insightsfirst では、 **SP** によって開始される SSO がサポートされます。
- Insightsfirst では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Insightsfirst の追加

Microsoft Entra ID への Insightsfirst の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Insightsfirst を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Insightsfirst**」と入力します。
4. 結果パネルから **Insightsfirst** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Insightsfirst 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Insightsfirst に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Insightsfirst の関連ユーザーとの間にリンク関係を確立する必要があります。

Insightsfirst に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Insightsfirst SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Insightsfirst テスト ユーザーの作成** - Insightsfirst で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Insightsfirst**&gt;**シングル サインオン**にブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかの URL を入力します。

    | **識別子** |
    | --- |
    | `https://insightsfirst-implementation.evalueserve.com` |
    | `https://insightsfirst.evalueserve.com/` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://insightsfirst-implementation.evalueserve.com/InsightFirstSSO/api/Assertion/ConsumerService` |
    | `https://insightsfirst.evalueserve.com/InsightFirstSSO/api/Assertion/ConsumerService` |

    c. [ **サインオン URL** ] ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://insightsfirst.evalueserve.com/Microsoft` |
    | `https://insightsfirst-implementation.evalueserve.com/Microsoft` |
6. Insightsfirst アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Insightsfirst アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザーのメールアドレス |
8. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集を示すスクリーンショット。]
9. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: [フィンガープリント] の値をコピーするスクリーンショット。]
10. [ **Insightsfirst のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Insightsfirst SSO を構成する

**Insightsfirst** 側でシングル サインオンを構成するには、**拇印の値**と、Microsoft Entra 管理センターからコピーした適切な URL を [Insightsfirst サポート チーム](mailto:insightsfirst.support@evalueserve.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Insightsfirst テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Insightsfirst に作成します。 Insightsfirst では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Insightsfirst にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Insightsfirst のサインオン URL にリダイレクトします。
- Insightsfirst のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Insightsfirst] タイルを選択すると、このオプションは Insightsfirst のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/insigniasamlsso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Insignia SAML SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insigniasamlsso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Insignia SAML SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、Insignia SAML SSO と Microsoft Entra ID を統合する方法について説明します。 Insignia SAML SSO と Microsoft Entra ID を統合すると、次のことができます。

- Insignia SAML SSO にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Insignia SAML SSO に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Insignia SAML SSO でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Insignia SAML SSO では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからInsignia SAML SSOを追加する

Microsoft Entra ID への Insignia SAML SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Insignia SAML SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Insignia SAML SSO**」と入力します。
4. 結果パネルから **Insignia SAML SSO** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Insignia SAML SSO の Microsoft Entra SSO の構成とテスト

B.Simonというテストユーザーを使用して、Microsoft Entra SSO を構成し、Insignia SAML SSO に対してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Insignia SAML SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Insignia SAML SSO に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Insignia SAML SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Insignia SAML SSO テスト ユーザーの作成** - Insignia SAML SSO で B.Simon に対応するユーザーを作成し、Microsoft Entra の Britta Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Insignia SAML SSO**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] 画面を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://<customername>.insigniails.com/ils` |
    | `https://<customername>.insigniails.com/` |
    | `https://<customername>.insigniailsusa.com/` |
    |  |

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customername>.insigniailsusa.com/<uniqueid>`

    手記

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、Insignia SAML SSO クライアント サポート チーム](https://www.insigniasoftware.com/Support) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Insignia SAML SSO のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Insignia SAML SSO の構成

Insignia SAML SSO 側 シングル サインオンを構成するには、ダウンロードした 証明書 (Base64) と、アプリケーション構成からコピーした適切な URL を Insignia SAML SSO サポート チーム送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Insignia SAML SSO テスト ユーザーの作成

このセクションでは、Insignia SAML SSO で Britta Simon というユーザーを作成します。 [Insignia SAML SSO サポート チーム](https://www.insigniasoftware.com/Support)と協力して、Insignia SAML SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Insignia SAML SSO サインオン URL にリダイレクトされます。
- Insignia SAML SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Insignia SAML SSO] タイルを選択すると、このオプションは Insignia SAML SSO のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/insite-lms-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Insite LMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insite-lms-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-11
- Summary: Microsoft Entra ID から Insite LMS に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Insite LMS と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [Insite LMS](https://www.insite-it.net/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Insite LMS のユーザーを作成する
- アクセスが不要になった場合に Insite LMS のユーザーを削除する
- Microsoft Entra ID と Insite LMS の間でユーザー属性の同期を維持する
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Insite LMS テナント](https://www.insite-it.net/)。
- 管理者アクセス許可がある Insite LMS のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングのスコープに誰が含まれるかを決定します。
3. [Microsoft Entra ID と Insite LMS の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Insite LMS を構成する

シークレット トークンを生成するには

1. 管理者アカウントを使用して [Insite LMS コンソール](https://portal.insitelms.net) にログインします。
2. 左側のメニュー **の [アプリケーション** ] モジュールに移動します。
3. 「 **セルフホステッド・ジョブ**」セクションには、"SCIM" という名前のジョブがあります。 ジョブが見つからない場合は、Insite LMS サポート チームにお問い合わせください。

    [Image: API キーの生成のスクリーンショット。]
4. [ **Api キーの生成]** を選択します。 **API キー**をコピーして保存します。 この値は、Insite LMS アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

注

API キーは 1 年間のみ有効であり、有効期限が切れる前に手動で更新する必要があります。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Insite LMS を追加する

Microsoft Entra アプリケーション ギャラリーから Insite LMS を追加して、Insite LMS へのプロビジョニングの管理を開始します。 SSO のために Insite LMS を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Insite LMS への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Insite LMS アプリでユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Insite LMS の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧 **で [Insite LMS**] を選択します。

    [Image: アプリケーションの一覧の [Insite LMS] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Insite LMS テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Insite LMS に接続できることを確認します。 接続に失敗した場合は、Insite LMS アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Insite LMS に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Insite LMS のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Insite LMS API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存] を** 選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Insite LMS で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "仕事"].value | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | 名前.名 | 糸 |  |  |
    | 名前.姓 | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/insperityexpensable-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Insperity ExpensAble を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insperityexpensable-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Insperity ExpensAble の間でシングル サインオンを構成する方法について説明します。

この記事では、Insperity ExpensAble と Microsoft Entra ID を統合する方法について説明します。 Insperity ExpensAble と Microsoft Entra ID の統合には、次の利点があります。

- Microsoft Entra ID で、誰が Insperity ExpensAble にアクセスできるかを制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Insperity ExpensAble (単一 Sign-On) に自動的にサインインできるようにすることができます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Insperity ExpensAble でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Insperity ExpensAble では、 **SP** Initiated SSO がサポートされます

### ギャラリーから Insperity ExpensAble を追加する

Microsoft Entra ID への Insperity ExpensAble の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Insperity ExpensAble を追加する必要があります。

**ギャラリーから Insperity ExpensAble を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「InsperityExpensAble**」と入力します。
4. 結果パネルから **Insperity ExpensAble** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Insperity ExpensAble で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Insperity ExpensAble 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Insperity ExpensAble で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Insperity ExpensAble シングル サインオン** の構成 - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Insperity ExpensAble テストユーザーを作成 - Microsoft Entra のユーザーである Britta Simon に対応する Insperity ExpensAble 内のユーザーを作成します。**
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Insperity ExpensAble で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Insperity ExpensAble** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [Insperity ExpensAble のドメインと URL] のシングル サインオン情報]

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://server.expensable.com/esapp/Authenticate?companyId=<company ID>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、 [Insperity ExpensAble クライアント サポート チーム](https://www.insperity.com/products/expense-management/support/express/) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Insperity ExpensAble のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Insperity ExpensAble Single Sign-On の設定を行う

**Insperity ExpensAble** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Insperity ExpensAble サポート チーム](https://www.insperity.com/products/expense-management/support/express/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Insperity ExpensAble テスト ユーザーの作成

このセクションでは、Insperity ExpensAble で Britta Simon というユーザーを作成します。 [Insperity ExpensAble サポート チーム](https://www.insperity.com/products/expense-management/support/express/)と協力して、Insperity ExpensAble プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Insperity ExpensAble] タイルを選択すると、SSO を設定した Insperity ExpensAble に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/instavr-viewer-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に InstaVR Viewer を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/instavr-viewer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と InstaVR Viewer 間のシングル サインオンを構成する方法について説明します。

この記事では、InstaVR Viewer と Microsoft Entra ID を統合する方法について説明します。 InstaVR Viewer と Microsoft Entra ID の統合には、次のメリットがあります。

- InstaVR Viewer にアクセスできるユーザーを Microsoft Entra ID で管理できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して InstaVR Viewer に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- InstaVR Viewer でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- InstaVR Viewer では、**SP** によって開始される SSO がサポートされます
- InstaVR Viewer は、**Just-In-Time** ユーザー プロビジョニングをサポートしています

### ギャラリーから InstaVR Viewer を追加する

Microsoft Entra ID, への InstaVR Viewer の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に InstaVR Viewer を追加する必要があります。

**ギャラリーから InstaVR Viewer を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **InstaVR Viewer」**と入力し、結果パネルで **InstaVR Viewer** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の InstaVR Viewer]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーを使用して、InstaVR Viewer で Microsoft Entra のシングル サインオンを構成およびテストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと InstaVR Viewer 内の関連ユーザー間にリンク関係が確立されている必要があります。

InstaVR Viewer に対する Microsoft Entra のシングル サインオンを構成およびテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **InstaVR Viewer シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **InstaVR Viewerテストユーザーを作成** - InstaVR Viewerで、Microsoft EntraのBritta Simonに対応するユーザーを作成します。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

InstaVR Viewer で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**InstaVR Viewer** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [InstaVR Viewer のドメインと URL] のシングル サインオン情報]

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://console.instavr.co/auth/saml/login/<WEBPackagedURL>`

    注

    サインオン URL の固定パターンはありません。 これは、InstaVR Viewer のお客様が Web パッケージを行うときに生成されます。 これは、すべての顧客とパッケージに固有です。 正確なサインオン URL を取得するには、InstaVR Viewer インスタンスにログインして Web パッケージを実行する必要があります。

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://console.instavr.co/auth/saml/sp/<WEBPackagedURL>`

    注

    識別子の値は実際の値ではありません。 この値は、この記事で後述する実際の識別子の値で更新してください。
6. [ **SAML を使用したシングル Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** と **フェデレーション メタデータ ファイル** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[InstaVR Viewer のセットアップ]** セクションで、要件のとおりに適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### InstaVR Viewer シングル サインオンの構成

1. 新しい Web ブラウザー ウィンドウを開き、InstaVR Viewer 企業サイトに管理者としてログインします。
2. **[ユーザー アイコン**] を選択し、[アカウント] を選択**します**。

    [Image: ユーザーが選択されている InstaVR Viewer サイトを示すスクリーンショット。]
3. 下へスクロールして **[SAML Auth]\(SAML 認証\)** に移動し、次の手順に従います。

    [Image: この手順で説明されている値を入力できる [SAML Auth](SAML 認証) ページを示すスクリーンショット。]

    a. **[SSO URL]** ボックスに、あらかじめコピーした **[ログイン URL]** の値を貼り付けます。

    b。 [ **ログアウト URL** ] ボックスに、前にコピーした **ログアウト URL** の値を貼り付けます。

    c. [ **エンティティ ID** ] ボックスに、先にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    d. ダウンロードした証明書ファイルをアップロードするには、[ **更新**] を選択します。

    e. ダウンロードしたフェデレーション メタデータ ファイルをアップロードするには、[ **更新**] を選択します。

    f. **[Entity ID](エンティティ ID)** の値をコピーして、**[基本的な SAML 構成]** セクションの **[識別子 (エンティティ ID)]** ボックスに貼り付けます。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### InstaVR Viewer テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを InstaVR Viewer に作成します。 InstaVR Viewer では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 InstaVR Viewer にユーザーがまだ存在していない場合は、認証後に新規に作成されます。 問題が発生した場合には、[InstaVR Viewer のサポート チーム](mailto:contact@instavr.co)にお問い合わせください。

#### シングル サインオンのテスト

1. 新しい Web ブラウザー ウィンドウを開き、InstaVR Viewer 企業サイトに管理者としてログインします。
2. 左側のナビゲーション パネルから **[Package]\(パッケージ\)** を選択し、 **[Make package for Web]\(Web 用にパッケージ化\)** を選択します。

    [Image: [Select Package](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パッケージの選択) と [Make package for Web](Web 用にパッケージ化) が選択された InstaVR Viewer 会社サイトを示すスクリーンショット。]
3. [ **ダウンロード**] を選択します。

    [Image: [Download](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ダウンロード) アイコンが選択されているスクリーンショット。]
4. ログインのために Microsoft Entra ID にリダイレクトされた後、[ **ホストされたページを開く** ] を選択します。

    [Image: [Open Hosted Page](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ホストされているページを開く) が選択されているスクリーンショット。]
5. Microsoft Entra 資格情報を入力し、SSO を使用して Microsoft Entra ID にログインします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/insuite-tutorial"} -->
## Microsoft Entra ID で insuite for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/insuite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と insuite の間のシングル サインオンを構成する方法について説明します。

この記事では、insuite と Microsoft Entra ID を統合する方法について説明します。 insuite を Microsoft Entra ID と統合すると、次のことが可能になります。

- insuite にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで insuite に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な insuite サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- insuite では、**SP** Initiated SSO がサポートされます
- insuite を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスを拡張したものです。 [Microsoft Defender for Cloud Apps でセッション制御を適用する方法について説明します](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)。

### ギャラリーからの insuite の追加

Microsoft Entra ID への insuite の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に insuite を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「insuite**」と入力します。
4. 結果パネルから **insuite** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### insuite に Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、insuite に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと insuite の関連ユーザーとの間にリンク関係を確立する必要があります。

insuite に対する Microsoft Entra SSO を構成およびテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **insuite SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **insuite テストユーザーを作成します - B.Simon の insuite での対応ユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**insuite**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://<CUSTOMER_NAME>.m.diol.jp/cgi-bin/saml_sso.cgi
    https://<CUSTOMER_NAME>.dacl.jp/cgi-bin/saml_sso.cgi
    ```

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `DreamArts_insuite_TENANTNAME`

    c. [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://<CUSTOMER_NAME>.m.diol.jp/cgi-bin/saml_sso.cgi
    https://<CUSTOMER_NAME>.dacl.jp/cgi-bin/saml_sso.cgi
    ```

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには [、insuite クライアント サポート チーム](mailto:e-support@dreamarts.co.jp) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **insuite のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### insuite SSO の構成

**insuite** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [insuite サポート チーム](mailto:e-support@dreamarts.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### insuite テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを insuite に作成します。 insuite では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 insuite にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで insuite タイルを選択すると、SSO を設定した insuite に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/intacct-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Sage Intacct を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/intacct-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-20
- Summary: Microsoft Entra ID と Sage Intacct 間にシングル サインオンを構成する方法について説明します。

この記事では、Sage Intacct と Microsoft Entra ID を統合する方法について説明します。 Sage Intacct と Microsoft Entra ID を統合すると、次のことができます。

- Sage Intacct にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Sage Intacct に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Sage Intacct でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Sage Intacct では、**IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Sage Intacct の追加

Microsoft Entra ID への Sage Intacct の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Sage Intacct を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Sage Intacct**」と入力します。
4. 結果パネルから **[Sage Intacct]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sage Intacct 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Sage Intacct に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Sage Intacct の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Sage Intacct と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
    2. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
2. **Sage Intacct の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Intacct で個々のユーザーを設定** - Sage IntacctでB.Simonに対応するユーザーを設定し、そのユーザーをMicrosoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Sage Intacct** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    A. [ **識別子 (エンティティ ID)]** テキスト ボックスに、Sage Intacct 企業の一意の識別子を次の形式で入力します。

    `https://saml.intacct.com`。

    b。 **[応答 URL]** テキスト ボックスに、次の URL を追加します。

    | 応答 URL |
    | --- |
    | `https://www.intacct.com/ia/acct/sso_response.phtml` (既定値として選択します)。 |
    | `https://www-p01.intacct.com/ia/acct/sso_response.phtml` |
    | `https://www-p02.intacct.com/ia/acct/sso_response.phtml` |
    | `https://www-p03.intacct.com/ia/acct/sso_response.phtml` |
    | `https://www-p04.intacct.com/ia/acct/sso_response.phtml` |
    | `https://www-p05.intacct.com/ia/acct/sso_response.phtml` |
    | `https://www-p06.intacct.com/ia/acct/sso_response.phtml` |
6. Sage Intacct アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性 の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 **[編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: 画像]
7. [ **属性と要求** ] ダイアログで、次の手順を実行します。

    A. **一意のユーザー識別子 (名前 ID) を**編集し、ソース属性を user.mail に設定し、名前識別子の形式が電子メール アドレスに設定されていることを確認し、[**保存]** を選択します

    b。 [ ***...]*** と [削除] を選択して、既定の [追加の要求] 属性をすべて削除します。

    | 属性名 | ソース属性 |
    | --- | --- |
    | 会社名 | **Sage Intacct の会社 ID** |
    | 名前 | `<User ID>` |

    注

    `<User ID>`の値は、Sage Intacct **ユーザー ID** と同じである必要があります。この ID は、「**Intacct での個々のユーザーのセットアップ**」に入力します。これについては、この記事の後半で説明します。 通常、これはメール アドレスのプレフィックスです。 この場合は、ソースを変換として設定し、user.mail パラメーターの ExtractMailPrefix() を使用できます。

    c. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    d. [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    え **名前空間**は空白のままにします。

    f. **をソースとして属性**を選択します。

    ジー **[ソース属性]** の一覧から、その行に表示される属性値を入力または選択します。

    h. **[OK]** を選択します。

    一. **保存** を選択します。

>
> 両方のカスタム属性を追加するには、手順 c から i を繰り返します。
8. [ **SAML を使用したシングル Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **編集]** を選択してダイアログを開きます。 [アクティブな証明書] の横にある **[...** ] を選択し、[ **PEM 証明書のダウンロード** ] を選択して証明書をダウンロードし、ローカル ドライブに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Sage Intacct のセットアップ] セクションで、Sage Intacct** 構成内で使用するログイン URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sage Intacct SSO の構成

1. 別の Web ブラウザー ウィンドウで、Sage Intacct 企業サイトに管理者としてサインインします。
2. **[会社**] に移動し、[**セットアップ**] タブを選択し、[構成] セクションで **[会社**] を選択します。

    [Image: 会社]
3. **Security** タブを選択し、**Edit** を選択します。

    [Image: [セキュリティ] のスクリーンショット]
4. **[シングル サインオン (SSO)]** セクションで、次の手順を実行します。

    [Image: シングル サインオン]

    A. **[シングル サインオンを有効にする]** を選択します。

    b。 **[ID プロバイダーの種類]** として **[SAML 2.0]** を選択します。

    c. **[発行者 URL]** テキストボックスに、[基本的な SAML 構成] ダイアログで作成した**識別子 (エンティティ ID)** の値を貼り付けます。

    d. **[ログイン URL]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    え **PEM** でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、**[証明書]** ボックスに貼り付けます。

    f. **[Requested Authentication Context](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/要求された認証コンテキスト)** を **[Exact](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/完全)** に設定します。

    ジー **保存** を選択します。

#### Intacct で個々のユーザーを設定する

会社で SSO が有効になっている場合、ユーザーが会社にログインする際に SSO の使用を個別に要求できます。 ユーザーを SSO 用に設定すると、そのユーザーはパスワードを使用して会社に直接ログインできなくなります。 代わりに、そのユーザーはシングルサインオンを使用する必要があり、SSO ID プロバイダーによって許可されたユーザーとして認証されます。 SSO を設定していないユーザーは、基本的なサインイン ページを使用して会社にログインし続けることができます。

**ユーザーの SSO を有効にするには、次の手順に従います。**

1. **Sage Intacct** の会社にサインインします。
2. **[会社**] に移動し、[**管理者**] タブを選択し、[ユーザー] を選択**します**。

    [Image: [ユーザー] のスクリーンショット]
3. 目的のユーザーを見つけて、その横にある **[編集]** を選択します。

    [Image: ユーザーの編集のスクリーンショット]
4. [ **シングル サインオン** ] タブを選択し、 **Federated SSO ユーザー ID を**入力します。

注

この値は、Azure の [属性と要求] ダイアログにある一意のユーザー識別子にマップされます。

[Image: [Federated SSO user id](フェデレーション SSO のユーザー ID) を入力できる [User Information](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー情報) セクションを示すスクリーンショット。]

注

Microsoft Entra ユーザー アカウントをプロビジョニングするには、Sage Intacct から提供されているその他の Sage Intacct ユーザー アカウント作成ツールまたは API を使用できます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Sage Intacct に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Sage Intacct] タイルを選択すると、SSO を設定した Sage Intacct に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/intelligencebank-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に IntelligenceBank を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/intelligencebank-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IntelligenceBank 間のシングル サインオンを構成する方法について説明します。

この記事では、IntelligenceBank と Microsoft Entra ID を統合する方法について説明します。 IntelligenceBank を Microsoft Entra ID と統合すると、次のことが可能になります。

- IntelligenceBank にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して IntelligenceBank に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IntelligenceBank でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- IntelligenceBank では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの IntelligenceBank の追加

Microsoft Entra ID への IntelligenceBank の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に IntelligenceBank を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「IntelligenceBank**」と入力します。
4. 結果パネルから **IntelligenceBank** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### IntelligenceBank 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、IntelligenceBank に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと IntelligenceBank の関連ユーザーとの間にリンク関係を確立する必要があります。

IntelligenceBank で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IntelligenceBank の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IntelligenceBank のテストユーザーを作成** - IntelligenceBank における B.Simon の対応ユーザーを作成し、そのユーザーを Microsoft Entra のユーザーとして表す B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ IntelligenceBank]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `IB` |
    | `IntelligenceBank` |
    | `https://<SUBDOMAIN>.intelligencebank.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.intelligencebank.com/auth`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.intelligencebank.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、IntelligenceBank クライアント サポート チーム](mailto:helpdesk@intelligencebank.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **IntelligenceBank のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IntelligenceBank SSO の構成

1. 別の Web ブラウザー ウィンドウで、IntelligenceBank 企業サイトに管理者としてサインインします。
2. [ **Authenticator** ] を選択し、[ **新規追加]** を選択します。

    [Image: スクリーンショットには、[管理者] タブが選択され、[新しい追加] アイコンが表示されています。]
3. 次の手順を実行します。

    [Image: この手順で情報を入力するフィールドを示すスクリーンショット。]

    a. [ **名前** ] ボックスに、 `azureadsso`などの名前を入力します。

    b。 [ **説明** ] ボックスに、有効な説明を入力します。

    c. [**種類]** としてドロップダウンから **[SAML**] を選択します。

    d. [ **リモート URL** ] ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。

    e. [ **ホスト** ] ボックスに、先にコピーした **エンティティ ID** の値を貼り付けます。

    f. ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容を **CertData** テキストボックスに貼り付けます

    g. **SingleLogoutService** ボックスに、先にコピーした**ログアウト URL** 値を貼り付けます。

    h. [ **保存] ボタンを** 選択します。

#### IntelligenceBank テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、IntelligenceBank 企業サイトに管理者としてサインインします。
2. **[管理者**&gt;**ユーザー**] に移動し、[**新しいユーザーの追加] アイコン**を選択してユーザーを追加**します**。

    [Image: スクリーンショットは、[ユーザー] タブで選択されている [ユーザー] アイコンを示しています。]
3. 組織の要件に従って必要なフィールドに入力し、[ **保存]** を選択します。

    [Image: ユーザー情報を入力する [新しいユーザーの追加] ページを示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる IntelligenceBank のサインオン URL にリダイレクトされます。
- IntelligenceBank のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [IntelligenceBank] タイルを選択すると、このオプションは IntelligenceBank のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/international-sos-assistance-products-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に International SOS Assistance Products を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/international-sos-assistance-products-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と International SOS Assistance Products の間でシングル サインオンを構成する方法について説明します。

この記事では、International SOS Assistance Products と Microsoft Entra ID を統合する方法について説明します。 International SOS Assistance Products を Microsoft Entra ID と統合すると、次のことが可能になります。

- International SOS Assistance Products にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して、International SOS Assistance Products に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- International SOS Assistance Products でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- International SOS Assistance Products では、**SP** Initiated SSO がサポートされます
- International SOS Assistance Products では、**Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの International SOS Assistance Products の追加

Microsoft Entra ID への International SOS Assistance Products の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に International SOS Assistance Products を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**International SOS Assistance Products**」と入力します。
4. 結果パネルから **[International SOS Assistance Products]** を選択し、このアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### International SOS Assistance Products 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、International SOS Assistance Products に対する Microsoft Entra SSOを構成およびテストします。 SSO を機能させるには、Microsoft Entra ユーザーと International SOS Assistance Products の関連ユーザーとの間にリンク関係を確立する必要があります。

International SOS Assistance Products に対する Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **International SOS Assistance Products の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **International SOS Assistance Products のテストユーザーを作成する - International SOS Assistance Products で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID] **&gt;** [エンタープライズ アプリ] **&gt;** [International SOS Assistance Products] **&gt;** [シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.outsystemsenterprise.com/myassist`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.internationalsos.com/sso/saml2/<CUSTOM_ID>`

    c. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.okta.com/saml2/service-provider/<IN>`

    注

    これらの値は実際の値ではありません。 これらの値を実際のサインオン URL、応答 URL、識別子で更新してください。 これらの値を取得するには、[International SOS Assistance Products クライアント サポート チーム](mailto:onlinehelp@internationalsos.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### International SOS Assistance Products の SSO の構成

**International SOS Assistance Products** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [International SOS Assistance Products サポート チーム](mailto:onlinehelp@internationalsos.com)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### International SOS Assistance Products のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを International SOS Assistance Products に作成します。 International SOS Assistance Products では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 International SOS Assistance Products にユーザーがまだ存在しない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる International SOS Assistance Products のサインオン URL にリダイレクトされます。
- International SOS Assistance Products のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [International SOS Assistance Products] タイルを選択すると、このオプションは International SOS Assistance Products のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/intime-tutorial"} -->
## Microsoft Entra ID で InTime for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/intime-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と InTime の間でシングル サインオンを構成する方法について説明します。

この記事では、InTime と Microsoft Entra ID を統合する方法について説明します。 InTime を Microsoft Entra ID と統合すると、次のことができます。

- InTime にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して InTime に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な InTime のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- InTime では、**SP** Initiated SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの InTime の追加

InTime の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に InTime を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**InTime**」と入力します。
4. 結果パネルから **[InTime]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### InTime 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、InTime で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと InTime の関連ユーザーとの間にリンク関係を確立する必要があります。

InTime で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **InTime SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **InTime テストユーザーの作成** - InTime で B.Simon の対応ユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**InTime**&gt;**シングル サインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    **[サインオン URL]** ボックスに URL として「`https://intime6.intimesoft.com/mytime/login/login.xhtml`」と入力します。
6. InTime アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 InTime アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集** ] アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。 [名前識別子の形式の選択] で **[名前識別子形式]** が**[既定]** に設定されていることを確認します。

    [Image: 画像]
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[InTime のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### InTime SSO を構成する

**InTime** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [InTime サポート チーム](mailto:hdollard@intimesoft.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### InTime テスト ユーザーの作成

このセクションでは、InTime で Britta Simon というユーザーを作成します。 [InTime サポート チーム](mailto:hdollard@intimesoft.com)と連携し、InTime プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる InTime サインオン URL にリダイレクトされます。
- InTime のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [InTime] タイルを選択すると、このオプションは InTime サインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/intradiem-tutorial"} -->
## Microsoft Entra ID で Intradiem for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/intradiem-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Intradiem の間のシングル サインオンを構成する方法について説明します。

この記事では、Intradiem を Microsoft Entra ID と統合する方法について説明します。 コール センターやワークフォース マネージメント ソフトウェアと統合して、節約、生産性、エンゲージメントを向上させる、AI を利用した生産性ソリューションです。 Intradiem を Microsoft Entra ID と統合すると、次のことが可能になります。

- Intradiem にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Intradiem に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Intradiem に対する Microsoft Entra シングル サインオンをテスト環境で構成・テストする。 Intradiem では、**SP** によって開始されたシングル サインオンのみがサポートされます。

### [前提条件]

Intradiem を Microsoft Entra ID と統合するためには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Intradiem でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Intradiem アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Intradiem を追加する

Microsoft Entra アプリケーション ギャラリーから Intradiem を追加して、Intradiem とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Intradiem**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<CustomerName>.intradiem.com/auth/realms/<CustomerName>` |
    | `https://<CustomerName>auth.intradiem.com/auth/realms/<CustomerName>` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CustomerName>auth.intradiem.com/auth/realms/<CustomerName>/broker/<CustomerName>/endpoint` |
    | `https://<CustomerName>.intradiem.com/auth/realms/<CustomerName>/broker/<CustomerName>/endpoint` |

    c. [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CustomerName>auth.intradiem.com` |
    | `https://<CustomerName>.intradiem.com` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Intradiem サポート チーム](mailto:support@intradiem.com)にお問い合わせください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### Intradiem SSO の構成

**Intradiem** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Intradiem サポート チーム](mailto:support@intradiem.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Intradiem テスト ユーザーの作成

このセクションでは、Intradiem で Britta Simon というユーザーを作成します。 [Intradiem サポート チーム](mailto:support@intradiem.com)と連携して Intradiem プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Intradiem のサインオン URL にリダイレクトされます。
- Intradiem のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Intradiem] タイルを選択すると、このオプションは Intradiem のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/intralinks-tutorial"} -->
## Microsoft Entra ID で Intralinks for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/intralinks-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Intralinks の間にシングル サインオンを構成する方法について説明します。

この記事では、Intralinks と Microsoft Entra ID を統合する方法について説明します。 Intralinks を Microsoft Entra ID と統合すると、次のことができます。

- Intralinks にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Intralinks に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Intralinks でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Intralinks では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Intralinks の追加

Microsoft Entra ID への Intralinks の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Intralinks を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Intralinks**」と入力します。
4. 結果のパネルから **[Intralinks]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Intralinks 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Intralinks に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Intralinks の関連ユーザーとの間にリンク関係を確立する必要があります。

Intralinks に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Intralinks SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Intralinksテストユーザーを作成し、Microsoft EntraでB.Simonとリンクする対応ユーザーをIntralinks内で構築します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Intralinks**&gt;**シングルサインオン**のページを参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.Intralinks.com/?PartnerIdpId=https://sts.windows.net/<AzureADTenantID>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[Intralinks クライアント サポート チーム](https://www.intralinks.com/contact)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Intralinks のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Intralinks SSO の構成

**Intralinks** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Intralinks サポート チーム](https://www.intralinks.com/contact)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Intralinks テスト ユーザーの作成

このセクションでは、Intralinks で Britta Simon というユーザーを作成します。 [Intralinks サポート チーム](https://www.intralinks.com/contact)と連携し、Intralinks プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Intralinks のサインオン URL にリダイレクトされます。
- Intralinks のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Intralinks] タイルを選択すると、このオプションは Intralinks のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/introdus-pre-and-onboarding-platform-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に introDus Pre and Onboarding Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/introdus-pre-and-onboarding-platform-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-11
- Summary: Microsoft Entra ID から introDus Pre and Onboarding Platform に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために introDus Pre and Onboarding Platform と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを利用して、[IntroDus Pre and Onboarding Platform](https://introdus.dk/) にユーザーとグループを自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- introDus Pre and Onboarding Platform でユーザーを作成する
- アクセスが不要になったユーザーを introDus Pre and Onboarding Platform で削除する
- Microsoft Entra ID と introDus Pre and Onboarding Platform の間でユーザー属性の同期を維持する
- introDus Pre and Onboarding Platform への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- シングル サインオン (SSO) を含む introDus サブスクリプション
- 有効な introDus API トークン。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と introDus Pre と Onboarding Platform の間でマップするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra ID でのプロビジョニングをサポートするように introDus Pre and Onboarding Platform を構成する

SSO を許可するサブスクリプション。 introDus 側では他の構成は必要ありません。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから introDus Pre and Onboarding Platform を追加する

Microsoft Entra アプリケーション ギャラリーから introDus Pre and Onboarding Platform を追加して、introDus Pre and Onboarding Platform へのプロビジョニングの管理を開始します。 SSO のために introDus Pre and Onboarding Platform を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: introDus Pre and Onboarding Platform への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で introDus Pre and Onboarding Platform の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照する

    [Image: [企業アプリケーション] パネル]
3. アプリケーションの一覧 **で introDus Pre and Onboarding Platform** を選択します。

    [Image: アプリケーションの一覧の introDus Pre and Onboarding Platform のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョン] タブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、introDus Pre と Onboarding Platform のテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が introDus Pre and Onboarding Platform に接続できることを確認します。 接続に失敗した場合は、introDus Pre および Onboarding Platform アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から introDus Pre and Onboarding Platform に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために introDus Pre および Onboarding Platform のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、introDus Pre および Onboarding Platform API が、その属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | 名前.整形済み | 糸 |  |
    | エクスターナルID | 糸 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/intsights-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に IntSights を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/intsights-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IntSights の間のシングル サインオンを構成する方法について説明します。

この記事では、IntSights と Microsoft Entra ID を統合する方法について説明します。 IntSights を Microsoft Entra ID と統合すると、次のことが可能になります。

- IntSights へのアクセス許可を持つ Microsoft Entra ID を管理する。
- ユーザーが自分の Microsoft Entra アカウントで IntSights に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- IntSights でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- IntSights は、**SP initiated SSO と IDP initiated SSO** をサポートしています。
- IntSights は、**Just In Time** ユーザー プロビジョニングをサポートしています。

### ギャラリーからの IntSights の追加

Microsoft Entra ID への IntSights の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に IntSights を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**IntSights**」と入力します。
4. 結果のパネルから **[IntSights]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### IntSights に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、IntSights に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと IntSights の関連ユーザーとの間にリンク関係を確立する必要があります。

IntSights で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IntSights の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IntSights テストユーザーの作成 - Microsoft Entra のユーザー表現にリンクされている B.Simon に対応するユーザーを IntSights に作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**IntSights**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.ti.insight.rapid7.com/auth/saml-callback/azure`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.ti.insight.rapid7.com/auth/saml-callback/azure`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.ti.insight.rapid7.com/auth/saml-callback/azure`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[IntSights クライアント サポート チーム](mailto:supportteam@intsights.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. IntSights アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、IntSights アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | intsightsAccountId（インツァイツのアカウントID） | &lt; intsightsAccountId &gt; |
    | intsightsRole | &lt; intsightsRole &gt; |

    注

    **intsightsAccountId** と **intsightsRole** は省略可能な要求であり、既定では追加されず、 **Just In Time** ユーザー プロビジョニングが有効になっている場合にのみ手動で追加されます。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[IntSights のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IntSights の SSO の構成

**IntSights** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [IntSights サポート チーム](mailto:supportteam@intsights.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IntSights テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを IntSights に作成します。 IntSights では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 IntSights にユーザーがまだ存在していない場合は、認証後に新規作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる IntSights のサインオン URL にリダイレクトされます。
- IntSights のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IntSights に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [IntSights] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した IntSights に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/invision-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に InVision を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/invision-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-11
- Summary: ユーザー アカウントを InVision に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために InVision と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[InVision](https://www.invisionapp.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- InVision でユーザーを作成する
- アクセスが不要になった場合に InVision のユーザーを削除する
- Microsoft Entra ID と InVision の間でユーザー属性の同期を維持する。
- InVision への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/invision-tutorial) (必須)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- SSO が有効になっている [InVision Enterprise アカウント](https://www.invisionapp.com/)。
- Admin アクセス許可がある InVision のユーザー アカウント

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と InVision の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように InVision を構成する

1. 管理者または所有者として [InVision エンタープライズ アカウント](https://www.invisionapp.com/)にサインインします。 左下にある **[チーム設定]** ドロアーを開き、 **[設定]** を選択します。

    [Image: SCIM セットアップの構成]
2. **[SCIM でのユーザー プロビジョニング]** 設定で **[変更]** を選択します。

    [Image: SCIM プロビジョニングの設定]
3. トグルを選択して、SCIM プロビジョニングを有効にします。 SCIM を有効にするには、まず SSO を構成しておく必要があることにご注意ください。

    [Image: SCIM プロビジョニングを有効にする]
4. **SCIM API URL** をコピーし、URL に `/scim/v2` を追加します。 **認証トークン**をコピーします。 これらの値は、InVision アプリケーションの [プロビジョニング] タブの **[テナント URL]** および **[シークレット トークン]** フィールドで後で使用できるように保存しておきます。

    [Image: SCIM アクセス トークン]

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから InVision を追加する

Microsoft Entra アプリケーション ギャラリーから InVision を追加して、InVision へのプロビジョニングの管理を開始します。 SSO に対して InVision を以前にセットアップしたことがある場合は、同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: InVision への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で InVision に対する自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[InVision]** を選択します。

    [Image: アプリケーションの一覧の [InVision] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、InVision テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が InVision に接続できることを確認します。 接続に失敗した場合は、InVision アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から InVision に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で InVision のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、InVision API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/invision-tutorial"} -->
## Microsoft Entra ID で InVision for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/invision-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と InVision の間でシングル サインオンを構成する方法について説明します。

この記事では、InVision と Microsoft Entra ID を統合する方法について説明します。 InVision と Microsoft Entra ID を統合すると、次のことができます。

- InVision にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して InVision に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- InVision でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- InVision では、**SP と IDP** Initiated SSO がサポートされます。
- InVision は [自動ユーザープロビジョニングをサポートしています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/invision-provisioning-tutorial)。

### ギャラリーからの InVision の追加

Microsoft Entra ID への InVision の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に InVision を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**InVision**」と入力します。
4. 結果パネル **InVision** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### InVision の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、InVision に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと InVision の関連ユーザーとの間にリンク関係を確立する必要があります。

InVision に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **InVision SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **InVision テスト ユーザーの作成** - InVision で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 内の B.Simon の表現にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**InVision**&gt;**シングルサインオン**を参照する。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーション **を IDP** 開始モードで構成する場合は、次のフィールドの値を入力してください。

    a. [**識別子**] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.invisionapp.com`

    b。 [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.invisionapp.com//sso/auth`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.invisionapp.com`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[InVision クライアント サポート チーム](mailto:support@invisionapp.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [**InVision** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### InVision SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として InVision 企業サイトにサインインします
2. [ **チーム** ] を選択し **、[設定]** を選択します。

    [Image: スクリーンショットには、[設定] が選択された [チーム] タブが表示されます。]
3. [ **シングル サインオン** ] まで下にスクロールし、[ **変更**] を選択します。

    [Image: スクリーンショットは、シングル サインオンの [変更] ボタンを示しています。]
4. [**シングル サインオン**] ページで、次の手順に従います。

    [Image: スクリーンショットには、この手順の値を入力する [シングル サインオン] ページが表示されます。]

    a. **[Require SSO for every member of &lt; account name &gt;](アカウント名 のすべてのメンバーに対して SSO を要求する)** を**オン**に変更します。

    b。 **名前** テキストボックスに、例として `azureadsso`のような名前を入力します。

    c. **サインイン URL** ボックスにサインオン URL の値を入力します。

    d. **サインアウト URL** テキストボックスに、先にコピーした **ログアウト** URL の値を貼り付けます。

    e. **SAML 証明書** テキストボックスで、ダウンロードした **証明書 (Base64)** をメモ帳で開き、内容をコピーして、SAML 証明書テキストボックスに貼り付けます。

    f. **名前 ID 形式** テキストボックスでは、`urn:oasis:names:tc:SAML:1.1:nameid-format:Unspecified` を **名前 ID 形式**に使用します。

    g. **HASH アルゴリズム**のドロップダウンから SHA-256  選択します。

    h. **SSO ボタン ラベル**の適切な名前を入力します。

    一. **[Allow Just-in-Time provisioning](Just-in-Time プロビジョニングを許可する)** をオンにします。

    j. **[更新]** を選択します。

#### InVision テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、管理者として InVision サイトにサインインします。
2. [ **チーム** ] を選択し、[ **人** ] を選択します。

    [Image: スクリーンショットには、[チーム] タブが表示され、[ユーザー] が選択されています。]
3. **[+] アイコン**を選択して、新しいユーザーを追加します。

    [Image: スクリーンショットには、[+] アイコンが表示され、ユーザーを追加します。]
4. ユーザーのメール アドレスを入力し、[ **次へ**] を選択します。

    [Image: スクリーンショットは、[アドレスを入力できる招待] ダイアログ ボックスを示しています。]
5. メール アドレスを確認し、[ **招待**] を選択します。

    [Image: スクリーンショットには、[招待] ダイアログが表示され、[招待] を選択して続行できます。]

手記

InVision では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる InVision のサインオン URL にリダイレクトされます。
- InVision のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した InVision に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [InVision] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した InVision に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/invitedesk-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に InviteDesk を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/invitedesk-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-11
- Summary: ユーザー アカウントを InviteDesk に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために InviteDesk と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [InviteDesk](https://invitedesk.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- InviteDesk でユーザーを作成する
- アクセスが不要になった場合に InviteDesk のユーザーを削除する
- Microsoft Entra ID と InviteDesk の間でユーザー属性の同期を維持する。
- InviteDesk でグループとグループ メンバーシップをプロビジョニングする。
- InviteDesk に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [InviteDesk](https://invitedesk.com/) テナント。
- 管理者アクセス許可がある InviteDesk のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と InviteDesk の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように InviteDesk を構成する

1. [InviteDesk 管理コンソール](https://app.invitedesk.com/)にログインします。 **Active Directory &gt;設定**に移動します。

    [Image: InviteDesk の設定]
2. **Azure Tenant-Id を**入力し、トグル ボタンを選択して対応する**アクセス コード**を生成します。

    [Image: InviteDesk トークン ページ]
3. トグル ボタンを選択すると、**Azure Tenant-Id** に対応する**アクセス コード**が生成されます。この値は、LucidChart アプリケーションの [プロビジョニング] タブの [**シークレット トークン** \*] フィールドに入力されます。

    [Image: InviteDesk トークンの生成]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから InviteDesk を追加する

Microsoft Entra アプリケーション ギャラリーから InviteDesk を追加して、InviteDesk へのプロビジョニングの管理を開始します。 SSO に対して InviteDesk を以前にセットアップしたことがある場合は、同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: InviteDesk への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて InviteDesk 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で InviteDesk に対する自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照する

    [Image: [エンタープライズ アプリケーション ブレード]]
3. アプリケーションの一覧で **InviteDesk** を選択します。

    [Image: アプリケーションの一覧の InviteDesk のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、InviteDesk テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が InviteDesk に接続できることを確認します。 接続に失敗した場合は、InviteDesk アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から InviteDesk に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で InviteDesk のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、InviteDesk API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | externalId | 糸 |  |
    | 優先言語 | 糸 |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から InviteDesk に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で InviteDesk のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ip-platform-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用の IP プラットフォームを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ip-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IP Platform の間のシングル サインオンを構成する方法について説明します。

この記事では、IP Platform と Microsoft Entra ID を統合する方法について説明します。 IP Platform を Microsoft Entra ID と統合すると、次のことが可能になります。

- IP Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して IP Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IP Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- IP Platform では、**SP** Initiated SSO がサポートされます。
- IP Platform では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの IP Platform の追加

Microsoft Entra ID への IP Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に IP Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**IP Platform**」と入力します。
4. 結果パネルから **[IP Platform]** を選択し、そのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### IP Platform 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、IP Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと IP Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

IP Platform に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IP Platform の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IP Platform のテストユーザーを作成** - IP Platform で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra における B.Simon の既存表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[IP Platform]**&gt;**[シングル サインオン]** を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.ipplatform.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[IP Platform クライアント サポート チーム](mailto:helpdesk@cpaglobal.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[IP Platform のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IP Platform SSO の構成

**IP Platform** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [IP Platform サポート チーム](mailto:helpdesk@cpaglobal.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IP Platform のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを IP Platform に作成します。 IP Platform では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 IP Platform にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる IP プラットフォームのサインオン URL にリダイレクトされます。
- IP Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [IP プラットフォーム] タイルを選択すると、このオプションは IP Platform のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ipass-smartconnect-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に iPass SmartConnect を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ipass-smartconnect-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-10
- Summary: Microsoft Entra ID を構成して、ユーザー アカウントを iPass SmartConnect に自動的にプロビジョニング/プロビジョニング解除する方法を説明します。

この記事の目的は、iPass SmartConnect と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを iPass SmartConnect に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

iPass SmartConnect は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.

- [iPass SmartConnect テナント](https://www.ipass.com/)。
- Admin アクセス許可がある iPass SmartConnect のユーザー アカウント。

### ユーザーを iPass SmartConnect に割り当てる

Microsoft Entra ID では、 *割り当て* と呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーとグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、iPass SmartConnect へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを iPass SmartConnect に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを iPass SmartConnect に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを iPass SmartConnect に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- iPass SmartConnect にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために iPass SmartConnect を設定する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に iPass SmartConnect を構成する前に、iPass SmartConnect 管理コンソールから構成情報を取得する必要があります。

1. iPass SmartConnect SCIM エンドポイントに対する認証に必要なベアラー トークンを取得するには、iPass SmartConnect を初めて設定した時点を参照してください。この値は、その時点でのみ提供されるためです。
2. ベアラー トークンがない場合は、 [iPass SmartConnect のサポート チーム](mailto:help@ipass.com) に連絡して、新しいトークンを取得してください。

### ギャラリーから iPass SmartConnect を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に iPass SmartConnect を構成するには、Microsoft Entra アプリケーション ギャラリーから iPass SmartConnect をマネージド SaaS アプリケ―ションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから iPass SmartConnect を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーから追加** する] セクションで、「 **iPass SmartConnect」**と入力し、検索ボックスで **[iPass SmartConnect** ] を選択します。
4. 結果パネルから **iPass SmartConnect** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の iPass SmartConnect のスクリーンショット。]

### iPass SmartConnect への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて iPass SmartConnect のユーザーやグループを作成、更新、無効化するよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

iPass SmartConnect のシングル サインオンに関する記事で説明されている手順に従って、 [iPass SmartConnect](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ipasssmartconnect-tutorial) に対して SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で iPass SmartConnect の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[iPass SmartConnect**] を選択します。

    [Image: アプリケーションの一覧の [iPass SmartConnect] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの自動構成のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、iPass SmartConnect テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が iPass SmartConnect に接続できることを確認します。 接続に失敗した場合は、iPass SmartConnect アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から iPass SmartConnect に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で iPass SmartConnect のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: [属性マッピング] ページのスクリーンショット。テーブルには、Microsoft Entra ID 属性と iPass SmartConnect 属性と一致する優先順位が一覧表示されます。]
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「 [自動ユーザー アカウント プロビジョニングに関するレポート」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。

### コネクタの制限事項

- iPass SmartConnect では、ドメインが iPass SmartConnect 管理コンソールに登録されているユーザー名のみ受け入れられます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ipasssmartconnect-tutorial"} -->
## Microsoft Entra ID でシングルサインオンのために iPass SmartConnect を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ipasssmartconnect-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-04
- Summary: Microsoft Entra ID と iPass SmartConnect の間のシングル サインオンを構成する方法について説明します。

この記事では、iPass SmartConnect と Microsoft Entra ID を統合する方法について説明します。 iPass SmartConnect を Microsoft Entra ID と統合すると、次のことができます。

- iPass SmartConnect にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して iPass SmartConnect に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

iPass SmartConnect は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- iPass SmartConnect でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- iPass SmartConnect は、**SP と IDP による SSO の開始**をサポートします。
- iPass SmartConnect では、 **Just In Time** ユーザー プロビジョニングがサポートされます。
- iPass SmartConnect では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ipass-smartconnect-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから iPass SmartConnect を追加する

Microsoft Entra ID への iPass SmartConnect の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に iPass SmartConnect を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「iPass SmartConnect**」と入力します。
4. 結果パネルから **iPass SmartConnect** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### iPass SmartConnect 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、iPass SmartConnect に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと iPass SmartConnect の関連ユーザーとの間にリンク関係を確立する必要があります。

iPass SmartConnect に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **iPass SmartConnect SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **iPass SmartConnect テストユーザーの作成** - iPass SmartConnect の B.Simon に対応するユーザーを作成し、それを Microsoft Entra でのユーザー表現にリンクさせるためです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**iPass SmartConnect**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、アプリが既に Azure に事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://om-activation.ipass.com/ClientActivation/ssolanding.go`
7. **[保存] を選択します**。
8. iPass SmartConnect アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、iPass SmartConnect アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | メール | user.userprincipalname |
    | ユーザー名 | user.userprincipalname |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. [ **iPass SmartConnect のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### iPass SmartConnect の SSO の構成

**iPass SmartConnect** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [iPass SmartConnect サポート チーム](mailto:help@ipass.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### iPass SmartConnect のテスト ユーザーの作成

このセクションでは、iPass SmartConnect で Britta Simon というユーザーを作成します。 [iPass SmartConnect サポート チームと協力して、iPass SmartConnect](mailto:help@ipass.com) プラットフォームの許可リストに追加する必要があるユーザーまたはドメインを追加します。 ドメインがチームによって追加されると、ユーザーは自動的に iPass SmartConnect プラットフォームにプロビジョニングされます。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、ログイン フローを開始できる iPass SmartConnect サインオン URL にリダイレクトされます。
- iPass SmartConnect のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した iPass SmartConnect に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [iPass SmartConnect] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した iPass SmartConnect に自動的にサインインされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ipoint-service-provider-tutorial"} -->
## Microsoft Entra ID を使用して iPoint Service Provider for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ipoint-service-provider-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と iPoint Service Provider の間でシングル サインオンを構成する方法について説明します。

この記事では、iPoint Service Provider と Microsoft Entra ID を統合する方法について説明します。 iPoint Service Provider を Microsoft Entra ID と統合すると、次のことができます。

- iPoint Service Provider にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して iPoint Service Provider に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- iPoint Service Provider でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- iPoint Service Provider では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーからの iPoint Service Provider の追加

Microsoft Entra ID への iPoint Service Provider の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に iPoint Service Provider を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**iPoint Service Provider**」と入力します。
4. 結果のパネルから **[iPoint Service Provider]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### iPoint Service Provider 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、iPoint Service Provider で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと iPoint Service Provider の関連ユーザーとの間にリンク関係を確立する必要があります。

iPoint Service Provider で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **iPoint Service Provider SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **iPoint Service Provider のテストユーザーを作成** - iPoint Service Provider において B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra における B.Simon と関連付けます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**iPointサービスプロバイダー**&gt;**シングルサインオン**にブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CUSTOMERNAME>.ipoint-systems.com/dashboard/` |
    | `https://<CUSTOMERNAME>.ipoint-systems.com/ipca-web/` |
    | `https://<CUSTOMERNAME>.ipoint-systems.com/authserver/saml/ssoLogin` |
7. **保存** を選択します。
8. iPoint Service Provider アプリケーションは特定の形式の SAML アサーションを想定しているため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性マッピングの画像を示すスクリーンショット。]
9. その他に、iPoint Service Provider アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 一意のユーザー ID | user.objectid (ユーザーのオブジェクトID) |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
11. **[iPoint Service Provider のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### iPoint Service Provider SSO の構成

**iPoint Service Provider** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [iPoint Service Provider サポート チーム](mailto:support@ipoint-systems.de)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### iPoint Service Provider テスト ユーザーの作成

このセクションでは、iPoint Service Provider で B.Simon というユーザーを作成します。 [iPoint Service Provider サポート チーム](mailto:support@ipoint-systems.de)と連携し、iPoint Service Provider プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる iPoint Service Provider Sign-On URL にリダイレクトされます。
- iPoint Service Provider のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した iPoint サービス プロバイダーに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [iPoint サービス プロバイダー] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション Sign-On ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した iPoint Service Provider に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/iqnavigatorvms-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に IQNavigator VMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/iqnavigatorvms-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IQNavigator VMS の間のシングル サインオンを構成する方法について説明します。

この記事では、IQNavigator VMS と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と IQNavigator VMS を統合すると、次のことができます。

- IQNavigator VMS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで IQNavigator VMS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- IQNavigator VMS でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- IQNavigator VMS では、 **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの IQNavigator VMS の追加

Microsoft Entra ID への IQNavigator VMS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に IQNavigator VMS を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「IQNavigator VMS**」と入力します。
4. 結果パネルから **IQNavigator VMS** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### IQNavigator VMS の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、IQNavigator VMS に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと IQNavigator VMS の関連ユーザーとの間にリンク関係を確立する必要があります。

IQNavigator VMS に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IQNavigator VMS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IQNavigator VMS テストユーザーを作成 - Microsoft Entraで表現されるユーザーにリンクさせるために、IQNavigator VMSでB.Simonに対応するユーザーを作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**IQNavigator VMS**&gt;**シングルサインオン**へ移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、値を入力します。 `iqn.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.iqnavigator.com/security/login?client_name=https://sts.window.net/<instance name>`

    c. [ **追加の URL の設定] を選択します**。

    d. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.iqnavigator.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の応答 URL とリレー状態で更新してください。 これらの値を取得するには [、IQNavigator VMS クライアント サポート チーム](https://www.beeline.com/contact-support/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. IQNavigator アプリケーションでは、名前識別子の要求で一意のユーザー識別子の値が必要です。 顧客は、名前識別子要求の適切な値をマップできます。 ここでは、デモのために user.UserPrincipalName をマップしました。 ただし、組織の設定に従って、正しい値をマップする必要があります。

    [Image: IQNavigator アプリケーションの画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IQNavigator VMS の SSO の構成

**IQNavigator VMS** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[IQNavigator VMS サポート チーム](https://www.beeline.com/contact-support/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IQNavigator VMS テスト ユーザーの作成

このセクションでは、IQNavigator VMS で Britta Simon というユーザーを作成します。 [IQNavigator VMS サポート チーム](https://www.beeline.com/contact-support/)と協力して、IQNavigator VMS プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IQNavigator VMS に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで IQNavigator VMS タイルを選択すると、SSO を設定した IQNavigator VMS に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/iqualify-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に iQualify LMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/iqualify-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と iQualify LMS の間のシングル サインオンを構成する方法について説明します。

この記事では、iQualify LMS と Microsoft Entra ID を統合する方法について説明します。 iQualify LMS を Microsoft Entra ID と統合すると、次のことが可能になります。

- iQualify LMS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで iQualify LMS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- iQualify LMS でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- iQualify LMS では、**SP と IDP** によって開始される SSO がサポートされます。
- iQualify LMS では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの iQualify LMS の追加

Microsoft Entra ID への iQualify LMS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に iQualify LMS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**iQualify LMS**」と入力します。
4. 結果のパネルから **[iQualify LMS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### iQualify LMS の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、iQualify LMS に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと iQualify LMS の関連ユーザーとの間にリンク関係を確立する必要があります。

iQualify LMS に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **iQualify LMS の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **iQualify LMS のテスト ユーザーの作成** - iQualify LMS で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[iQualify LMS]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | 運用環境: `https://<yourorg>.iqualify.com/` |
    | テスト環境: `https://<yourorg>.iqualify.io` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | 運用環境: `https://<yourorg>.iqualify.com/auth/saml2/callback` |
    | テスト環境: `https://<yourorg>.iqualify.io/auth/saml2/callback` |
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | 運用環境: `https://<yourorg>.iqualify.com/login` |
    | テスト環境: `https://<yourorg>.iqualify.io/login` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[iQualify LMS クライアント サポート チーム](https://www.iqualify.com/)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. iQualify LMS アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ **ユーザー属性] ダイアログを** 開きます。

    [Image: このスクリーンショットは、[編集] アイコンが選択された状態の [User Attributes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー属性) を示しています。]
8. [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、[**編集] アイコン**を使用して要求を編集するか、[**新しい要求の追加]** を使用して要求を追加し、上の図に示すように SAML トークン属性を構成し、次の手順を実行します。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | first\_name | User.givenname |
    | last\_name | ユーザーの名字 |
    | 個人識別子 | "あなたの属性" |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [新しい要求の追加] オプションが備わっている [ユーザー要求] のスクリーンショット。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. **[OK]** を選択します。

    g. **保存** を選択します。

    注

    **person\_id** 属性は**省略可能**です。
9. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[iQualify LMS のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### iQualify LMS SSO の構成

1. 新しく Web ブラウザー ウィンドウを開き、iQualify LMS 環境に管理者としてサインインします。
2. ログインしたら、右上にあるアバターを選択し、[**アカウント設定]** を選択します

    [Image: [アカウント設定] を示すスクリーンショット。]
3. アカウント設定領域で、左側のリボン メニューを選択し、[**INTEGRATIONS**] を選択します。

    [Image: アプリケーションの [統合] エリアを示すスクリーンショット。]
4. [INTEGRATIONS] で、[ **SAML** ] アイコンを選択します。

    [Image: [統合] の [SAML] アイコンを示すスクリーンショット。]
5. **[SAML Authentication Settings (SAML 認証設定)]** ダイアログ ボックスで、次の手順を実行します。

    [Image: [SAML 認証設定] を示すスクリーンショット。]

    a. **[SAML SINGLE SIGN-ON SERVICE URL](SAML シングル サインオン サービス URL)** ボックスに、Microsoft Entra アプリケーション構成ウィンドウからコピーした**ログイン URL** の値を貼り付けます。

    b。 **[SAML LOGOUT URL](SAML ログアウト URL)** ボックスに、Microsoft Entra アプリケーション構成ウィンドウからコピーした**ログアウト URL** の値を貼り付けます。

    c. ダウンロードした証明書ファイルをメモ帳で開き、その内容をコピーして、**[Public Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パブリック証明書)** ボックスに貼り付けます。

    d. **[LOGIN BUTTON LABEL]( ログイン ボタン ラベル)** に、ログイン ページに表示するボタンの名前を入力します。

    e. **[保存] を選択します**。

    f. [ **更新] を**選択します。

#### iQualify LMS のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを iQualify LMS に作成します。 iQualify LMS では、Just-In-Time ユーザー プロビジョニングがサポートされます。この設定は既定で有効です。 このセクションにはアクション項目はありません。 iQualify LMS にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、マイ アプリを使用して Microsoft Entra のシングル サインオン構成をテストします。

マイ アプリで [iQualify LMS] タイルを選択すると、iQualify LMS アプリケーションのログイン ページが表示されます。

[Image: アプリケーションのログイン ページを示すスクリーンショット。]

[ **Microsoft Entra ID でサインイン** ] ボタンを選択すると、iQualify LMS アプリケーションに自動的にサインオンします。

マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/iris-intranet-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Iris Intranet を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/iris-intranet-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-10
- Summary: Microsoft Entra ID から Iris Intranet に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Iris Intranet と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [Iris Intranet](https://www.triptic.nl/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Iris Intranet でユーザーを作成する
- アクセスが不要になったときに Iris Intranet のユーザーを削除する
- Microsoft Entra ID と Iris Intranet の間でユーザー属性の同期を維持する。
- Iris Intranet への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/iris-intranet-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Iris Intranet テナント。
- Admin アクセス許可がある Iris Intranet のユーザー アカウント

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Iris Intranet の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように Iris Intranet を構成する

Microsoft Entra でのプロビジョニングをサポートするように Iris Intranet を構成するには、**Iris Intranet サポート チーム**にメールをドロップして**テナント URL** と[シークレット トークン](mailto:support@triptic.nl)を取得する必要があります。これらの値は、Iris Intranet のアプリケーションの [プロビジョニング] タブの [**シークレット トークン**と**テナント URL**] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Iris Intranet を追加する

Microsoft Entra アプリケーション ギャラリーから Iris Intranet を追加して、Iris Intranet へのプロビジョニングの管理を開始します。 SSO のために以前 Iris Intranet を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Iris Intranet への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Iris Intranet に対する自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Iris Intranet**] を選択します。

    [Image: アプリケーションの一覧の [Iris Intranet] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: アプリケーション設定の [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの自動構成のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Iris Intranet テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Iris Intranet に接続できることを確認します。 接続に失敗した場合は、Iris Intranet アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から Iris Intranet に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Iris Intranet のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Iris Intranet API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | externalId | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/iris-intranet-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Iris Intranet を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/iris-intranet-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Iris Intranet の間でシングル サインオンを構成する方法について説明します。

この記事では、Iris Intranet と Microsoft Entra ID を統合する方法について説明します。 Iris Intranet と Microsoft Entra ID を統合すると、次のことができます。

- Iris Intranet にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Iris Intranet に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Iris Intranet でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Iris Intranet では、SP  の SSO をサポートします。
- Iris Intranet では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Iris Intranet では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/iris-intranet-provisioning-tutorial)。

### ギャラリーから Iris Intranet を追加する

Microsoft Entra ID への Iris Intranet の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Iris Intranet を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Iris Intranet**」と入力します。
4. 結果パネルから **Iris Intranet** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Iris Intranet の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Iris Intranet に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Iris Intranet の関連ユーザーとの間にリンク関係を確立する必要があります。

Iris Intranet に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Iris Intranet の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Iris Intranet のテストユーザーを作成** - Microsoft Entra のユーザーの表現にリンクされた、Iris Intranet で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Iris Intranet**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.irisintranet.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.irisintranet.com`

    手記

    これらの値は実際の値ではありません。 実際の識別子とサインオン URL でこれらの値を更新します。 これらの値を取得するには [、Iris Intranet クライアント サポート チーム](mailto:support@triptic.nl) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Iris Intranet SSO の構成

**Iris Intranet** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Iris Intranet サポート チーム](mailto:support@triptic.nl)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Iris Intranet テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Iris Intranet に作成します。 Iris Intranet では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Iris Intranet にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

Iris Intranet では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Iris Intranet のサインオン URL にリダイレクトされます。
- Iris Intranet のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Iris Intranet] タイルを選択すると、このオプションは Iris Intranet のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/iriusrisk-tutorial"} -->
## Microsoft Entra ID で IriusRisk for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/iriusrisk-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IriusRisk 間のシングル サインオンを構成する方法について説明します。

この記事では、IriusRisk と Microsoft Entra ID を統合する方法について説明します。 IriusRisk を Microsoft Entra ID と統合すると、次のことが可能になります。

- IriusRisk にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Invicti に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IriusRisk でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- IriusRisk では、**SP** によって開始される SSO がサポートされます。
- IriusRisk では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの IriusRisk の追加

IriusRisk の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に IriusRisk を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**IriusRisk**」と入力します。
4. 結果のパネルから **[IriusRisk]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### IriusRisk 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、IriusRisk に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと IriusRisk の関連ユーザーとの間にリンク関係を確立する必要があります。

IriusRisk に対して Microsoft Entra SSO を構成してテストするには、以下の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IriusRisk SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IriusRisk のテストユーザーを作成します** - IriusRiskでB.Simonに対応するユーザーを作成し、そのユーザーをMicrosoft Entraのユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[IriusRisk]**&gt;**[シングル サインオン]** を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `iriusrisk-sp`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.iriusrisk.com/ui#!login`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値を取得するには、[IriusRisk クライアント サポート チーム](mailto:info@continuumsecurity.net)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[IriusRisk の設定]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IriusRisk SSO の構成

**IriusRisk** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [IriusRisk サポート チーム](mailto:info@continuumsecurity.net)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IriusRisk テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを IriusRisk に作成します。 IriusRisk では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 IriusRisk にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる IriusRisk のサインオン URL にリダイレクトされます。
- IriusRisk のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [IriusRisk] タイルを選択すると、このオプションは IriusRisk のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/isams-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に iSAMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/isams-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と iSAMS の間のシングル サインオンを構成する方法について説明します。

この記事では、iSAMS と Microsoft Entra ID を統合する方法について説明します。 iSAMS を Microsoft Entra ID と統合すると、次のことが可能になります。

- iSAMS にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで iSAMS に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- iSAMS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- iSAMS では、**SP および IDP** Initiated SSO がサポートされます。

### ギャラリーからの iSAMS の追加

Microsoft Entra ID への iSAMS の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に iSAMS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**iSAMS**」と入力します。
4. 結果のパネルから **[iSAMS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### iSAMS 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、iSAMS に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと iSAMS の関連ユーザーとの間にリンク関係を確立する必要があります。

iSAMS 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **iSAMS の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **iSAMS テスト ユーザーの作成** - iSAMS で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[iSAMS]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** Initiated モードで構成する場合は、次の手順を行います。

    a. **[識別子]** ボックスに、`https://<SUBDOMAIN>.isams.cloud/main/sso/saml2` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<SUBDOMAIN>.isams.cloud/main/sso/saml2/acs` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<SUBDOMAIN>.isams.cloud/` という形式で URL を入力します。

    Note

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[iSAMS クライアント サポート チーム](mailto:support@isams.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### iSAMS の SSO の構成

1. 管理者として iSAMS にログインします。
2. コントロール パネルに移動し、**[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** モジュールを開きます。
3. 右側のメニューで、**[Identity Providers](ID プロバイダー)** を選択します

    [Image: [Identity Providers](ID プロバイダー) が選択されている Active Directory 構成を示すスクリーンショット。]
4. **[Add Provider](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロバイダーの追加)** を選択します

    [Image: [Add Providers](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロバイダーの追加) が選択されている [Identity Providers](ID プロバイダー) を示すスクリーンショット。]
5. 次のページで、以下の手順を実行します。

    [Image: 説明されている手順を実行できる [Identity Providers](ID プロバイダー) ウィザードのスクリーンショット。]

    a. **[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前)** ボックスに、`Saml2 Azure` などの有効な名前を入力します。 これはログイン ページに表示される名前です。

    b。 [メタデータ URL] ボックスに、先ほどコピーした **[アプリのフェデレーション メタデータ URL]** の値を入力します。

    c. **[Import](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インポート)** を押します。

    d. **[Enabled Client Applications](有効なクライアント アプリケーション)** セクションの **[Applications](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーション)** ボックスで、プロバイダーによってログインページに表示されるすべての iSAMS アプリケーションを選択します。

    e. **保存して閉じる** を選択します。

#### iSAMS のテスト ユーザーの作成

1. 管理者として iSAMS にログインします。
2. **コントロール パネルのホーム**&gt;**セキュリティとアクセス許可**&gt;**ユーザー アカウント**&gt;**ユーザー オプションとタスク**&gt;**Modify ユーザー プロパティ**に移動します。
3. 表示されるポップアップ ウィンドウで、**[Account Details](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントの詳細)** タブを選択し、**[Authorization](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/承認)** を、新しく作成した ID プロバイダーのものに変更します。

    [Image: [Authorization](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/承認) の値が表示されている [Account Details](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントの詳細) を示すスクリーンショット。]
4. **保存して閉じる** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる iSAMS サインオン URL にリダイレクトされます。
- iSAMS のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した iSAMS に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [iSAMS] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した iSAMS に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/iserver-portal-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に iServer Portal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/iserver-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と iServer Portal 間にシングル サインオンを構成する方法について学習します。

この記事では、iServer Portal と Microsoft Entra ID を統合する方法について説明します。 iServer Portal を Microsoft Entra ID と統合すると、次のことができます:

- iServer Portal にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して iServer Portal に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- iServer Portal でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- iServer Portal では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

### ギャラリーからの iServer Portal の追加する

Microsoft Entra ID への iServer Portal の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに iServer Portal を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**iServer Portal**」と入力します。
4. 結果のパネルから **[iServer Portal]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### iServer Portal 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、iServer Portal で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと iServer Portal の関連ユーザーとの間にリンク関係を確立する必要があります。

iServer Portal で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **iServer Portal SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **iServer Portalでのテストユーザーの作成** - iServer Portal 上でB.Simonに対応するユーザーを作成し、それをMicrosoft Entraのユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**iServer ポータル**&gt;**シングル サインオン** をブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `iserver-portal-<myiserverportal>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<myiserverportal.com>/SAML/login`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<myiserverportal.com>/SAML/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[iServer Portal クライアント サポート チーム](mailto:support@orbussoftware.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集を示すスクリーンショット。]
8. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: サムプリントの値のコピーを示すスクリーンショット。]
9. **[iServer Portal のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### iServer Portal SSO の構成

**iServer Portal** 側でシングル サインオンを構成するには、**サムプリントの値**とアプリケーション構成からコピーした適切な URL を [iServer Portal サポート チーム](mailto:support@orbussoftware.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### iServer Portal テスト ユーザーの作成

このセクションでは、iServer Portal で B.Simon というユーザーを作成します。 [iServer Portal サポート チーム](mailto:support@orbussoftware.com)と連携して、iServer Portal プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる iServer Portal Sign-On URL にリダイレクトされます。
- iServer Portal Sign-On URL に直接移動し、そこからサインオン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した iServer ポータルに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [iServer Portal] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション Sign-On ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した iServer Portal に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/isg-governx-federation-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に ISG GovernX フェデレーションを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/isg-governx-federation-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ISG GovernX Federation 間のシングル サインオンを構成する方法について説明します。

この記事では、ISG GovernX フェデレーションと Microsoft Entra ID を統合する方法について説明します。 ISG とクライアント IDP 間のフェデレーション用テンプレート。 Microsoft Entra ID と ISG GovernX Federation を統合すると、次のことができます。

- ISG GovernX Federation にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで ISG GovernX Federation に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で ISG GovernX Federation 向けに Microsoft Entra のシングル サインオンを構成してテストします。 ISG GovernX フェデレーションでは、 **SP** と **IDP** によって開始されるシングル サインオンと **Just-In-Time** ユーザー プロビジョニングの両方がサポートされます。

### [前提条件]

Microsoft Entra ID を ISG GovernX Federation と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な ISG GovernX Federation サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから ISG GovernX Federation アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから ISG GovernX Federation を追加する

Microsoft Entra アプリケーション ギャラリーから ISG GovernX Federation を追加して、ISG GovernX Federation とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ISG GovernX Federation**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.okta.com/saml2/service-provider/<GovernX_UniqueID>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://isg-one.okta.com/sso/saml2/<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://isg-one.okta.com/sso/saml2/<ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [ISG GovernX フェデレーション サポート チーム](mailto:infrastructureteam@isg-one.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **ISG GovernX フェデレーションのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### ISG GovernX Federation SSO を構成する

**ISG GovernX フェデレーション**側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [ISG GovernX フェデレーション サポート チーム](mailto:infrastructureteam@isg-one.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ISG GovernX Federation のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーが ISG Cloud Control に作成されます。 ISG GovernX Federation では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 ISG GovernX Federation にユーザーがまだ存在していない場合、一般的には認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる ISG GovernX フェデレーション サインオン URL にリダイレクトされます。
- ISG GovernX Federation のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ISG GovernX フェデレーションに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ISG GovernX Federation] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ISG GovernX フェデレーションに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/isight-tutorial"} -->
## Microsoft Entra ID で i-Sight for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/isight-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と i-Sight の間のシングル サインオンを構成する方法について説明します。

この記事では、i-Sight と Microsoft Entra ID を統合する方法について説明します。 i-Sight を Microsoft Entra ID と統合すると、次のことが可能になります。

- i-Sight にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで i-Sight に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- i-Sight でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- i-Sight では、 **IDP** によって開始される SSO がサポートされます。

### ギャラリーから i-Sight を追加する

Microsoft Entra ID への i-Sight の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に i-Sight を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「i-Sight**」と入力します。
4. 結果パネルから **[i-Sight** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### i-Sight 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、i-Sight に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと i-Sight の関連ユーザーとの間にリンク関係を確立する必要があります。

i-Sight で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **i-Sight SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **i-Sight テストユーザーを作成** - B.Simon に対応するユーザーを i-Sight で作成し、それを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**i-Sight**&gt;**シングルサインオンにアクセスします。**
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<CustomerName>.i-sight.com` |
    | `https://<CustomerName>.i-sightuat.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CustomerName>.i-sight.com/auth/wsfed` |
    | `https://<CustomerName>.i-sightuat.com/auth/wsfed` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、i-Sight クライアント サポート チーム](mailto:it@i-sight.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **i-Sight のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成を適切な U R L にコピーするためのスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### i-Sight SSO の構成

**i-Sight** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [i-Sight サポート チーム](mailto:it@i-sight.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### i-Sight テスト ユーザーの作成

このセクションでは、i-Sight で Britta Simon というユーザーを作成します。 [i-Sight サポート チーム](mailto:it@i-sight.com)と協力して、i-Sight プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した i-Sight に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [i-Sight] タイルを選択すると、SSO を設定した i-Sight に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/island-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Island を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/island-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-10
- Summary: Microsoft Entra ID から Island にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザーとグループのプロビジョニングを構成するために Island ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して自動的にユーザーを [アイランド](https://www.island.io/) にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Island でユーザーを作成します。
- アクセスが不要になった場合は、Island のユーザーを削除します。
- Microsoft Entra ID と Island の間でユーザー属性の同期を維持します。
- Island にグループとグループ メンバーシップをプロビジョニングする
- Island に[シングル サインオンします](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/island-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者アクセス許可を持つ Island のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
- [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
- [Microsoft Entra ID と Island の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Island を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように Island を構成するには、Island サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Island を追加する

Microsoft Entra アプリケーション ギャラリーから Island を追加して、Island へのプロビジョニングの管理を開始します。 SSO 用に Island を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Island への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて Island のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Island の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Island**] を選択します。

    [Image: アプリケーションの一覧の [Island] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、アイランド テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Island に接続できることを確認します。 接続に失敗した場合は、Island アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から Island に同期されるユーザー **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Island のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Island API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Island で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | 優先言語 | 糸 |  |  |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | name.formatted | 糸 |  |  |
    | externalId | 糸 |  | ✓ |
13. [マッピング] セクション **で** 、[ **Microsoft Entra ID グループをアイランドに同期する**] を選択します。
14. [属性マッピング] セクションで、Microsoft Entra ID から Island に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Island のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Island で必須 |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/island-tutorial"} -->
## Microsoft Entra ID で Island for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/island-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Island の間のシングル サインオンを構成する方法について説明します。

この記事では、Island を Microsoft Entra ID と統合する方法について説明します。 Microsoft Entra シングル サインオンを使用すると、エンドユーザーは Microsoft Entra 認証により、Island の The Enterprise Browser に直接アクセスできます。 管理者は Microsoft Entra ID で、ユーザーの追加または削除と、属性の更新を行うこともできます。 Island を Microsoft Entra ID と統合すると、次のことができます。

- Island へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Island に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Island に対する Microsoft Entra のシングル サインオンをテスト環境で構成してテストする。 Island は、**SP** および **IDP** Initiated の両方のシングル サインオンと、**Just In Time** ユーザー プロビジョニングをサポートしています。

### [前提条件]

Microsoft Entra ID を Island と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Island のシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Island アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Island を追加する

Microsoft Entra アプリケーション ギャラリーから Island を追加して、Island とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Island**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `urn:auth0:za-production:<TENANTID>-saml-browser-prod`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.island.io/login/callback?connection=<TENANTID>-saml-browser-prod`
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://download.island.io`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Island クライアント サポート チーム](mailto:support@island.io)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Island アプリケーションでは、特定の形式の SAML アサーションが必要とされるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **"user.userprincipalname"** ですが、Island ではこれをユーザーのオブジェクト ID にマップし、名前識別子の形式の設定を **[永続的]** に変更する必要があります。 そのため、一覧の **user.objectid** 属性を使用するか、組織構成に基づいて適切な属性値を使用できます。

    [Image: 属性の構成の画像を示すスクリーンショット。]

    注

    既定の属性の一覧から、次のクレームを手動で編集してください。

    1. **givenname** (user.givenname) 要求を選択し、[名前] 設定を **given\_name** に変更して**、[保存]** を選択します。
    2. **名前** (user.userprincipalname) 要求を選択し、[ソース] 設定を **[変換**] に変更し、[変換の管理] 設定を次のように編集します。
    3. [変換] を選択し、"Join()" を選択します。 [パラメーター 1] として "user.givenname" を選択し、[区切り記号] として単一の空白文字を追加し、[パラメーター 2] として "user.surname" を選択します。
    4. [追加と保存] を選択して構成を完了します。
8. Island アプリケーションでは、上記のものに加えて、以下に示すさらにいくつかの属性が SAML 応答で返される必要があります。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | family\_name | ユーザーの名字 |
    | groups | user.groups [すべて] |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Island のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Island SSO の構成

**Island** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [Island サポート チーム](mailto:support@island.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Island テスト ユーザーの作成

このセクションでは、Island で B.Simon というユーザーを作成します。 Island は Just-In-Time ユーザー プロビジョニングをサポートしており、これは既定で有効になっています。 このセクションにはアクション項目はありません。 Island にユーザーがまだ存在していない場合、一般的には認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Island のサインオン URL にリダイレクトされます。
- Island のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Island に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Island] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Island に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/it-conductor-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの IT-Conductor を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/it-conductor-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IT-Conductor 間のシングル サインオンを構成する方法について説明します。

この記事では、IT-Conductor と Microsoft Entra ID を統合する方法について説明します。 IT-Conductor は、リモート エージェントレス監視、パフォーマンス管理、IT 運用のためのサービスとしてのソフトウェア自動化プラットフォームです。 IT-Conductor を Microsoft Entra ID と統合すると、次のことが可能になります。

- IT-Conductor にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して IT-Conductor に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で IT-Conductor 用の Microsoft Entra のシングル サインオンを構成およびテストする。 IT-Conductor では、**IDP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングがサポートされます。

### [前提条件]

Microsoft Entra ID を IT-Conductor と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- IT-Conductor でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから IT-Conductor アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから IT-Conductor を追加する

Microsoft Entra アプリケーション ギャラリーから IT-Conductor を追加して、IT-Conductor でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**IT-Conductor**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. IT-Conductor アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、IT-Conductor アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | PERSON\_Email | ユーザーのメールアドレス |
    | オブジェクト名 | user.userprincipalname |
    | PERSON\_FirstName | User.givenname |
    | PERSON\_LastName | ユーザーの名字 |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[IT-Conductor の設定]** セクションで、要件に基づいて該当する URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### IT-Conductor SSO の構成

**IT-Conductor** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [IT-Conductor サポート チーム](mailto:support@itconductor.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。 詳細については、 [この](https://docs.itconductor.com/start-here/sso-setup) リンクを参照してください。

#### IT-Conductor テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを IT-Conductor に作成します。 IT-Conductor では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 IT-Conductor にユーザーがまだ存在していない場合、一般的には認証後に新しいものが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IT-Conductor に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [IT-Conductor] タイルを選択すると、SSO を設定した IT-Conductor に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/itrp-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ITRP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/itrp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: この記事では、Microsoft Entra ID と ITRP の間でシングル サインオンを構成する方法について説明します。

この記事では、ITRP と Microsoft Entra ID を統合する方法について説明します。 ITRP を Microsoft Entra ID と統合すると、次のことができます。

- ITRP にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ITRP に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオンが有効な ITRP サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ITRP では、SP Initiated SSO がサポートされます。

### ギャラリーからの ITRP の追加

Microsoft Entra ID への ITRP の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ITRP を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックスに **「ITRP** 」と入力します。
4. 結果パネルから **ITRP** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ITRP 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ITRP に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ITRP の関連ユーザーとの間にリンク関係を確立する必要があります。

ITRP に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ITRP SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ITRP のテストユーザーを作成 - B.Simon に対応するユーザーを ITRP に作成し、Microsoft Entra のユーザーにリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ITRP**&gt;**シングルサインオンに移動します**。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成** ] ダイアログ ボックスで、次の手順を実行します。

    1. [ **識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。

        `https://<tenant-name>.itrp.com`
    2. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。

        `https://<tenant-name>.itrp.com`

    注意

    これらの値はプレースホルダーです。 実際の識別子とサインオン URL を使用する必要があります。 値を取得するには、 [ITRP サポート チーム](https://www.4me.com/support/) に問い合わせてください。 [ **基本的な SAML 構成** ] ダイアログ ボックスに表示されるパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] アイコンを選択して [ **SAML 署名証明書** ] ダイアログ ボックスを開きます。

    [Image: [編集] アイコンが選択されている [SAML 署名証明書] ページを示すスクリーンショット。]
7. [ **SAML 署名証明書** ] ダイアログ ボックスで、 **拇印** の値をコピーして保存します。

    [Image: 拇印の値をコピーする]
8. [ **ITRP のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ITRP SSO の構成

1. 新しい Web ブラウザー ウィンドウで、ITRP 企業サイトに管理者としてサインインします。
2. ウィンドウの上部にある **[設定]** アイコンを選択します。

    [Image: [設定] アイコン]
3. 左側のウィンドウで、[ **シングル サインオン**] を選択します。

    [Image: シングル サインオンを選択]
4. [ **シングル サインオン** の構成] セクションで、次の手順を実行します。

    [Image: [有効] が選択されている [Single Sign-On] セクションを示すスクリーンショット。]

    1. **[有効] を選択します**。
    2. [ **リモート ログアウト URL** ] ボックスに、コピーした **ログアウト URL** の値を貼り付けます。
    3. [ **SAML SSO URL** ] ボックスに、コピーした **ログイン URL** の値を貼り付けます。
    4. [ **証明書の指紋** ] ボックスに、コピーした証明書の **拇印** の値を貼り付けます。
    5. **[保存] を選択します**。

#### ITRP のテスト ユーザーの作成

Microsoft Entra ユーザーが ITRP にサインインできるようにするには、ユーザーを ITRP に追加する必要があります。 手動で追加する必要があります。

ユーザー アカウントを作成するには、以下の手順に従います。

1. ITRP テナントにサインインします。
2. ウィンドウの上部にある [ **レコード** ] アイコンを選択します。

    [Image: レコードアイコン]
3. メニューで、[ **ユーザー**] を選択します。

    [Image: ユーザーを選択]
4. プラス記号 ( **+** ) を選択して、新しいユーザーを追加します。

    [Image: プラス記号を選択してください]
5. [ **新しいユーザーの追加** ] ダイアログ ボックスで、次の手順を実行します。

    [Image: [新しい人物の追加] ダイアログ ボックス] [

    1. 追加する有効な Microsoft Entra アカウントの名前とメール アドレスを入力します。
    2. **[保存] を選択します**。

注意

ITRP よって提供されているユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ITRP サインオン URL にリダイレクトされます。
- ITRP のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで ITRP タイルを選択すると、このオプションは ITRP サインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/itslearning-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に itslearning を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/itslearning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と itslearning の間にシングル サインオンを構成する方法についてご確認ください。

この記事では、itslearning と Microsoft Entra ID を統合する方法について説明します。 itslearning と Microsoft Entra ID を統合すると、次のことができます。

- itslearning にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して itslearning に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- itslearning でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- itslearning では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの itslearning の追加

Microsoft Entra ID への itslearning の統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに itslearning を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**itslearning**」と入力します。
4. 結果のパネルから **[itslearning]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### itslearning 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、itslearning に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと itslearning の関連ユーザーとの間にリンク関係を確立する必要があります。

itslearning に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **itslearning の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **itslearningでテストユーザーを作成 -** B.Simonに対応するユーザーをitslearningで用意し、それをMicrosoft EntraのユーザーであるB.Simonにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[itslearning]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. **識別子 (エンティティ ID)** テキスト ボックスに、URL を入力します: `urn:mace:saml2v2.no:services:com.itslearning`
    2. **[応答 URL]** ボックスに、次のいずれかの URL を入力します。

        | 返信 URL |
        | --- |
        | `https://www.itsltest.com/elogin/AssertionConsumerService.aspx` |
        | `https://www.itslearning.com/elogin/AssertionConsumerService.aspx` |
    3. **[サインオン URL]** ボックスに、次のいずれかの URL を入力します。

        | サインオン用URL |
        | --- |
        | `https://www.itslearning.com/index.aspx` |
        | `https://us1.itslearning.com/index.aspx` |
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[itslearning の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### itslearning の SSO の構成

シングル サインオン (SSO) を設定するには、まず itslearning の営業担当者またはアカウント マネージャーに連絡して価格情報を確認し、技術チームに接続します。 彼らは、Microsoft Entra 構成のアプリ フェデレーション メタデータ URL が必要です。 誰に連絡すればよいかわからない場合は、 [サポート チーム](mailto:support@itslearning.com) にお問い合わせください。

#### itslearning テスト ユーザーの作成

このセクションでは、itslearning で Britta Simon というユーザーを作成します。 itslearning 技術チームと協力して、itslearning プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる itslearning のサインオン URL にリダイレクトされます。
- itslearning のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで itslearning タイルを選択すると、このオプションは itslearning のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ivanti-service-manager-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Ivanti Service Manager (ISM) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ivanti-service-manager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Ivanti Service Manager (ISM) の間でシングル サインオンを構成する方法について説明します。

この記事では、Ivanti Service Manager (ISM) と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Ivanti Service Manager (ISM) を統合すると、次のことができます。

- Ivanti Service Manager (ISM) にアクセスできるユーザーを Microsoft Entra ID でコントロールできます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Ivanti Service Manager (ISM) に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Ivanti Service Manager (ISM) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Ivanti Service Manager (ISM) では、**SP と IDP** によって開始される SSO がサポートされます
- Ivanti Service Manager (ISM) では、 **Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーから Ivanti Service Manager (ISM) を追加する

Microsoft Entra ID と Ivanti Service Manager (ISM) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Ivanti Service Manager (ISM) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Ivanti Service Manager (ISM)」**と入力します。
4. 結果パネルから **Ivanti Service Manager (ISM)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Ivanti Service Manager (ISM) 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Ivanti Service Manager (ISM) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Ivanti Service Manager (ISM) の関連ユーザーとの間にリンク関係を確立する必要があります。

Ivanti Service Manager (ISM) に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Ivanti Service Manager (ISM) SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Ivanti Service Manager (ISM) テストユーザーの作成** - Ivanti Service Manager (ISM) で B.Simon に対応するユーザーを作成し、Microsoft Entra 上のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Ivanti Service Manager (ISM)**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://<customer>.saasit.com/
    https://<customer>.saasiteu.com/
    https://<customer>.saasitau.com/
    ```

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer>/handlers/sso/SamlAssertionConsumerHandler.ashx`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer>.saasit.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Ivanti Service Manager (ISM) クライアント サポート チーム](https://www.ivanti.com/support/contact) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Ivanti Service Manager (ISM) のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Ivanti Service Manager (ISM) の SSO の構成

**Ivanti Service Manager (ISM)** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、アプリケーション構成からコピーした適切な URL を [Ivanti Service Manager (ISM) サポート チーム](https://www.ivanti.com/support/contact)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Ivanti Service Manager (ISM) のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Ivanti Service Manager (ISM) に作成します。 Ivanti Service Manager (ISM) では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Ivanti Service Manager (ISM) にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Ivanti Service Manager (ISM) サポート チーム](https://www.ivanti.com/support/contact)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Ivanti Service Manager (ISM) のサインオン URL にリダイレクトされます。
- Ivanti Service Manager (ISM) のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Ivanti Service Manager (ISM) に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Ivanti Service Manager (ISM)] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Ivanti Service Manager (ISM) に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->
