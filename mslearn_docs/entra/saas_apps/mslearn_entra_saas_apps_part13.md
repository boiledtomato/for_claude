# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 13)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 70

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ivm-smarthub-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に IVM Smarthub を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ivm-smarthub-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IVM Smarthub 間にシングル サインオンを構成する方法について説明します。

この記事では、IVM Smarthub と Microsoft Entra ID を統合する方法について説明します。 IVM Smarthub を Microsoft Entra ID と統合すると、次のことができます。

- IVM Smarthub にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って IVM Smarthub に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な IVM Smarthub のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- IVM Smarthub では、**SP** Initiated SSO がサポートされます。

### ギャラリーから IVM Smarthub を追加する

Microsoft Entra ID への IVM Smarthub の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に IVM Smarthub を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「** IVM Smarthub**」と入力します。
4. 詳細ウィンドウで **[ IVM Smarthub]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### IVM Smarthub 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、IVM Smarthub に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと IVM Smarthub の関連ユーザーとの間にリンク関係を確立する必要があります。

IVM Smarthub に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IVM Smarthub の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IVM Smarthub テスト ユーザーの作成 - IVM Smarthub** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**IVM Smarthub**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Environment>.ivminc.com/saml`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Environment>.ivminc.com/signin-saml-<CustomerName>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Environment>.ivmsmarthub.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[IVM Smarthub のサポート チーム](mailto:icssupport@ivminc.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[IVM Smarthub のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IVM Smarthub SSO の構成

**IVM Smarthub** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [IVM Smarthub サポート チーム](mailto:icssupport@ivminc.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IVM Smarthub テスト ユーザーの作成

このセクションでは、IVM Smarthub で Britta Simon というユーザーを作成します。 [IVM Smarthub サポート チーム](mailto:icssupport@ivminc.com)と協力して、IVM Smarthub プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる IVM Smarthub のサインオン URL にリダイレクトされます。
- IVM Smarthub のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [IVM Smarthub] タイルを選択すると、このオプションは IVM Smarthub のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/iwellnessnow-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に iWellnessNow を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/iwellnessnow-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と iWellnessNow の間でシングル サインオンを構成する方法について説明します。

この記事では、iWellnessNow と Microsoft Entra ID を統合する方法について説明します。 iWellnessNow と Microsoft Entra ID を統合すると、次のことができます。

- iWellnessNow にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して iWellnessNow に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- iWellnessNow でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- iWellnessNow は、**SP Initiated SSO と IDP Initiated SSO** をサポートしています。

### ギャラリーから iWellnessNow を追加する

Microsoft Entra ID への iWellnessNow の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に iWellnessNow を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**iWellnessNow**」と入力します。
4. 結果のパネルから **[iWellnessNow]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### iWellnessNow 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、iWellnessNow との Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、iWellnessNow の関連ユーザーとの間にリンク関係を確立する必要があります。

iWellnessNow との Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **iWellnessNow SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **iWellnessNow テストユーザーの作成** - iWellnessNow に B.Simon に対応するテストユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**iWellnessNow** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル** があり、 **IDP** 開始モードで構成する場合は、次の手順を実行します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルのアップロードを示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルを選択するスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    注

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. **サービス プロバイダー メタデータ ファイル**がないときに **IDP** 開始モードでアプリケーションを構成する場合は、次の手順に従います。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `http://<CustomerName>.iwellnessnow.com`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.iwellnessnow.com/ssologin`
7. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.iwellnessnow.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[iWellnessNow クライアント サポート チーム](mailto:info@iwellnessnow.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
8. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **メタデータ XML** を見つけて **[ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[iWellnessNow のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成用URLをコピーするスクリーンショットです。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### iWellnessNow の SSO の構成

**iWellnessNow** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[iWellnessNow サポート チーム](mailto:info@iwellnessnow.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### iWellnessNow のテスト ユーザーの作成

このセクションでは、iWellnessNow で Britta Simon というユーザーを作成します。 [iWellnessNow サポート チーム](mailto:info@iwellnessnow.com)と連携し、iWellnessNow プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる iWellnessNow のサインオン URL にリダイレクトされます。
- iWellnessNow のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した iWellnessNow に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [iWellnessNow] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した iWellnessNow に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/iwt-procurement-suite-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に IWT Procurement Suite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/iwt-procurement-suite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IWT Procurement Suite の間にシングル サインオンを構成する方法について説明します。

この記事では、IWT Procurement Suite と Microsoft Entra ID を統合する方法について説明します。 IWT Procurement Suite を Microsoft Entra ID と統合すると、次のことができます。

- IWT Procurement Suite にアクセスできるユーザーを Microsoft Entra ID 内で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して IWT Procurement Suite に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IWT Procurement Suite でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- IWT Procurement Suite では、**IDP** Initiated SSO がサポートされます。

### ギャラリーから IWT Procurement Suite を追加する

Microsoft Entra ID への IWT Procurement Suite の統合を構成するには、ギャラリーから、お使いの管理対象 SaaS アプリの一覧に IWT Procurement Suite を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**IWT Procurement Suite**」と入力します。
4. 結果のパネルから **[IWT Procurement Suite]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### IWT Procurement Suite 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、IWT Procurement Suite に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと IWT Procurement Suite 内の関連ユーザーとの間にリンク関係を確立する必要があります。

IWT Procurement Suite に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IWT Procurement Suite SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IWT Procurement Suite テスト ユーザーの作成** - IWT Procurement Suite で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**IWT Procurement Suite**&gt;**シングルサインオン**にアクセスする。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[SAML でシングル サインオンをセットアップします]** ページで、次の手順を実行します。

    a. **[識別子]** ボックスに、`https://[customersubdomain].ionwave.net/sso/[customerid]` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://[customersubdomain].ionwave.net/sso/[customerid]` のパターンを使用して URL を入力します

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[IWT Procurement Suite クライアント サポート チーム](mailto:support@ionwave.net)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. IWT Procurement Suite アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**Emailaddress** は **user.mail** にマップされています。 IWT Procurement Suite アプリケーションでは **、Emailaddress** が **user.userprincipalname** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IWT Procurement Suite SSO の構成

**IWT Procurement Suite** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [IWT Procurement Suite サポート チーム](mailto:support@ionwave.net)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### IWT Procurement Suite テスト ユーザーの作成

このセクションでは、IWT Procurement Suite で Britta Simon というユーザーを作成します。 [IWT Procurement Suite サポート チーム](mailto:support@ionwave.net)と連携して、IWT Procurement Suite プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IWT Procurement Suite に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [IWT Procurement Suite] タイルを選択すると、SSO を設定した IWT Procurement Suite に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jamfprosamlconnector-tutorial"} -->
## Microsoft Entra ID で Jamf Pro をシングルサインオンのために設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jamfprosamlconnector-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Jamf Pro の間でシングル サインオンを構成する方法について説明します。

この記事では、Jamf Pro と Microsoft Entra ID を統合する方法について説明します。 Jamf Pro と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID を使用して、Jamf Pro にアクセスできるユーザーを制御します。
- Microsoft Entra アカウントを使用して Jamf Pro にユーザーを自動的にサインインします。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効になっている Jamf Pro サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Jamf Pro では、 **SP Initiated** SSO と **IdP Initiated SSO がサポートされます** 。

### ギャラリーから Jamf Pro を追加する

Microsoft Entra ID への Jamf Pro の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Jamf Pro を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに*「Jamf Pro*」と入力します。
4. 結果パネルから **Jamf Pro** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Jamf Pro の Microsoft Entra ID での SSO の構成とテスト

B.Simon というテスト ユーザーを使用して、Jamf Pro に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Jamf Pro の関連ユーザーとの間にリンク関係を確立する必要があります。

このセクションでは、Jamf Pro で Microsoft Entra SSO を構成し、テストします。

1. ユーザーがこの機能を使用できるように、Microsoft Entra ID で SSO を構成します。
    1. B.Simon アカウントで Microsoft Entra SSO をテストする Microsoft Entra テスト ユーザーを作成します。
    2. B.Simon が Microsoft Entra ID で SSO を使用できるように、Microsoft Entra テスト ユーザーを割り当てます。
2. Jamf Pro で SSO を構成して、アプリケーション側で SSO 設定を構成します。
    1. Jamf Pro のテスト ユーザーを作成します。Jamf Pro で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、これらのユーザーをリンクします。
3. SSO 構成をテストして、構成 が機能することを確認します。

### Microsoft Entra ID で SSO を構成する

このセクションでは、Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Jamf Pro** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **単一の Sign-On 方法の選択** ] ページで、[SAML] を選択 **します**。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] ページを編集します。]
5. [ **基本的な SAML 構成]** セクションで、 **IdP 開始** モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次の式を使用する URL を入力します。 `https://<subdomain>.jamfcloud.com/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次の式を使用する URL を入力します。 `https://<subdomain>.jamfcloud.com/saml/SSO`
6. [ **追加の URL の設定] を選択します**。 **SP 開始**モードでアプリケーションを構成する場合は、[**サインオン URL**] テキスト ボックスに、次の式を使用する URL を入力します。`https://<subdomain>.jamfcloud.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 Jamf Pro ポータルの **[シングル サインオン** ] セクションから実際の識別子の値を取得します。これについては、この記事の後半で説明します。 識別子の値から実際のサブドメイン値を抽出し、そのサブドメイン情報をサインオン URL と応答 URL として使用できます。 「 **基本的な SAML 構成** 」セクションに示されている数式を参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **SAML 署名証明書** ] セクションに移動し、 **コピー** ボタンを選択して **アプリのフェデレーション メタデータ URL を**コピーし、コンピューターに保存します。

    [Image: SAML 署名証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Jamf Pro で SSO を構成する

1. Jamf Pro 内での構成を自動化するには、[拡張機能のインストール] を選択して **My Apps Secure Sign-in ブラウザー拡張機能** **をインストール**します。

    [Image: マイ アプリのセキュリティで保護されたサインイン ブラウザー拡張機能ページ]
2. 拡張機能をブラウザーに追加したら、[ **Jamf Pro のセットアップ**] を選択します。 Jamf Pro アプリケーションが開いたら、サインインするための管理者の資格情報を入力します。 ブラウザー拡張機能によってアプリケーションが自動的に構成され、手順 3 から 7 が自動化されます。

    [Image: Jamf Pro の [セットアップ構成] ページ]
3. Jamf Pro を手動で設定するには、新しい Web ブラウザー ウィンドウを開き、管理者として Jamf Pro 企業サイトにサインインします。 次に、次の手順を実行します。
4. ページの右上隅にある **[設定] アイコン** を選択します。

    [Image: Jamf Pro で設定アイコンを選択する]
5. [ **シングル サインオン] を選択します**。

    [Image: Jamf Pro でシングル Sign-On を選択する]
6. [ **シングル サインオン** ] ページで、次の手順を実行します。

    [Image: Jamf Pro の [Single Sign-On](シングル サインオン) ページ]

    a. **[編集]** を選択します。

    b。 [ **単一 Sign-On 認証を有効にする** ] チェック ボックスをオンにします。

    c. **[ID プロバイダー**] ドロップダウン メニューからオプションとして **Azure** を選択します。

    d. **ENTITY ID の値を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子 ] (エンティティ ID)** フィールドに貼り付けます。

    注

    [ `<SUBDOMAIN>` ] フィールドの値を使用して、[ **基本的な SAML 構成]** セクションのサインオン URL と応答 URL を入力します。

    e. **[ID プロバイダー メタデータ ソース**] ドロップダウン メニューから [メタデータ **URL] を**選択します。 表示されるフィールドに、コピーした **アプリのフェデレーション メタデータ URL** の値を貼り付けます。

    f. (省略可能)トークンの有効期限の値を編集するか、[SAML トークンの有効期限を無効にする] を選択します。
7. 同じページで、[ **ユーザー マッピング** ] セクションまで下にスクロールします。 次に、次の手順を実行します。

    [Image: Jamf Pro の [シングル Sign-On] ページの [ユーザー マッピング] セクション。]

    a. **ID プロバイダーのユーザー マッピング**の **NameID** オプションを選択します。 既定では、このオプションは **NameID** に設定されていますが、カスタム属性を定義できます。

    b。 **Jamf Pro ユーザー マッピング**の**電子メール**を選択します。 Jamf Pro は、IdP によって最初にユーザーによって送信され、次にグループによって送信される SAML 属性をマップします。 ユーザーが Jamf Pro にアクセスしようとすると、Jamf Pro は ID プロバイダーからユーザーに関する情報を取得し、すべての Jamf Pro ユーザー アカウントと照合します。 着信ユーザー アカウントが見つからない場合、Jamf Pro はグループ名で照合しようとします。

    c. `http://schemas.microsoft.com/ws/2008/06/identity/claims/groups`] フィールドに値を貼り付けます。

    d. 同じページで、[ **セキュリティ** ] セクションまで下にスクロールし、[ **単一 Sign-On 認証のバイパスをユーザーに許可**する] を選択します。 その結果、ユーザーは認証のために ID プロバイダーのサインイン ページにリダイレクトされません。代わりに Jamf Pro に直接サインインできます。 ユーザーが ID プロバイダー経由で Jamf Pro にアクセスしようとすると、IdP によって開始される SSO 認証と承認が行われます。

    e. **保存** を選択します。

#### Jamf Pro テスト ユーザーの作成

Microsoft Entra ユーザーが Jamf Pro にサインインするには、Jamf Pro にユーザーをプロビジョニングする必要があります。 Jamf Pro でのプロビジョニングは手動で行います。

ユーザー アカウントをプロビジョニングするには、次の手順を実行します。

1. Jamf Pro 企業サイトに管理者としてサインインします。
2. ページの右上隅にある **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** アイコンを選択します。

    [Image: Jamf Pro の設定アイコン]
3. **Jamf Pro ユーザー アカウントとグループを選択します**。

    [Image: Jamf Pro の設定の Jamf Pro ユーザー アカウントとグループ アイコン]
4. **新規**を選択します。

    [Image: Jamf Pro のユーザー アカウントとグループのシステム設定ページ]
5. [ **標準アカウントの作成]** を選択します。

    [Image: Jamf Pro の [ユーザー アカウントとグループ] ページの [標準アカウントの作成] オプション]
6. [ **新しいアカウント** ] ダイアログ ボックスで、次の手順を実行します。

    [Image: Jamf Pro システム設定の新しいアカウント設定オプション]

    a. [USERNAME]\( **ユーザー名** \) フィールドに、テスト ユーザーの完全な名前 `Britta Simon`入力します。

    b。 組織に応じて、 **アクセス レベル**、 **特権セット**、 **およびアクセス状態** のオプションを選択します。

    c. [ **FULL NAME]** フィールドに「 `Britta Simon`」と入力します。

    d. [EMAIL ADDRESS]\( **電子メール アドレス** \) フィールドに、Britta Simon のアカウントのメール アドレスを入力します。

    e. [ **パスワード** ] フィールドに、ユーザーのパスワードを入力します。

    f. [ **パスワードの確認** ] フィールドに、ユーザーのパスワードをもう一度入力します。

    g. **保存** を選択します。

### SSO 構成をテストする

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Jamf Pro のサインオン URL にリダイレクトされます。
- Jamf Pro のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Jamf Pro に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Jamf Pro タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Jamf Pro に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jasper-ai-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用にジャスパー AI を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jasper-ai-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID とジャスパー AI の間でシングル サインオンを構成する方法について説明します。

この記事では、ジャスパー AI と Microsoft Entra ID を統合する方法について説明します。 Jasper AI と Microsoft Entra ID を統合すると、次のことができます。

- ジャスパー AI にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用してジャスパー AI に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ジャスパー AI でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ジャスパー AI は、**SP開始方式のSSOとIDP開始方式のSSO**のどちらもサポートしています。

### ギャラリーからジャスパー AI を追加する

Microsoft Entra ID への Jasper AI の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧にジャスパー AI を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「ジャスパー AI**」と入力します。
4. 結果パネルから **[ジャスパー AI** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ジャスパー AI の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Jasper AI に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Jasper AI の関連ユーザーとの間にリンク関係を確立する必要があります。

Jasper AI に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Jasper AI SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ジャスパー AI テスト ユーザーの作成** - ジャスパー AI で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Jasper AI**&gt;**シングルサインオン**に移動。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.jasper.ai/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.workos.com/sso/saml/acs/<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.jasper.ai`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、ジャスパー AI サポート チーム](mailto:hey@jasper.ai) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Jasper AI SSO の構成

**ジャスパー AI** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Jasper AI サポート チーム](mailto:hey@jasper.ai)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。 詳細については、 [この](https://jasper7631.zendesk.com/hc/articles/20020759202843-Configuring-Azure-Single-Sign-On-SSO-for-Jasper) リンクを参照してください。

#### Jasper AI テスト ユーザーの作成

このセクションでは、Jasper AI で B.Simon というユーザーを作成します。 [ジャスパー AI サポート チーム](mailto:hey@jasper.ai)と協力して、ジャスパー AI プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる、ジャスパー AI のサインオン URL にリダイレクトされます。
- ジャスパー AI のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定したジャスパー AI に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ジャスパー AI] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したジャスパー AI に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/javelo-tutorial"} -->
## Microsoft Entra ID で Javelo for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/javelo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Javelo 間にシングル サインオンを構成する方法について説明します。

この記事では、Javelo と Microsoft Entra ID を統合する方法について説明します。 Javelo を Microsoft Entra ID と統合すると、次のことができます。

- Javelo にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Javelo に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Javelo でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Javelo では、 **SP** Initiated SSO がサポートされます。
- Javelo では、 **Just In Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Javelo を追加する

Microsoft Entra ID への Javelo の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Javelo を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Javelo**」と入力します。
4. 結果パネルから **Javelo** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Javelo 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Javelo に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Javelo の関連ユーザーとの間にリンク関係を確立する必要があります。

Javelo に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Javelo SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Javelo のテスト ユーザーの作成** - Javelo の B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Javelo**&gt;**Single サインオン**にブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [**基本的な SAML 構成]** セクションで、からダウンロードできる`https://api.javelo.io/omniauth/<CustomerSPIdentifier>_saml/metadata`をアップロードし、次の手順を実行します。

    A. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: スクリーンショットは、[メタデータ ファイルのアップロード] リンクを含む基本的な SAML 構成を示しています。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: スクリーンショットは、ファイルを選択してアップロードできるダイアログ ボックスを示しています。]

    c. メタデータ ファイルが正常にアップロードされると、必要な URL が自動的に設定されます。

    d. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerSubdomain>.javelo.io/auth/login`

    注

    この値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 これらの値を取得するには、 [Javelo クライアント サポート チーム](mailto:Support@javelo.io) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Javelo SSO の構成

1. Javelo 企業サイトに管理者としてログインします。
2. **管理者**ビューに移動し、[**SSO**] タブ&gt;**Microsoft Entra ID** に移動し、[**構成**] を選択します。
3. [ **Microsoft Entra ID での SSO の有効化] ページで** 、次の手順に従います。

    [Image: [構成設定] を示すスクリーンショット。]

    A. **[プロバイダー**] ボックスに有効な名前を入力します。

    b。 [ **エンティティ ID** ] ボックスに、前にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    c. [ **メタデータ URL** ] ボックスに、前にコピーした **アプリのフェデレーション メタデータ URL を** 貼り付けます。

    d. [ **テスト URL] を選択します**。

    え [ **Email Domains]\(電子メール ドメイン** \) ボックスに有効なドメインを入力します。

    f. [ **Enable SSO with Microsoft Entra ID]\(Microsoft Entra ID で SSO を有効にする\) を選択します**。

#### Javelo テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Javelo に作成します。 Javelo では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Javelo にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Javelo のサインオン URL にリダイレクトされます。
- Javelo のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Javelo] タイルを選択すると、このオプションは Javelo のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jdacloud-tutorial"} -->
## Microsoft Entra ID を使用して JDA Cloud for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jdacloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と JDA Cloud 間のシングル サインオンを構成する方法について説明します。

この記事では、JDA Cloud と Microsoft Entra ID を統合する方法について説明します。 JDA Cloud を Microsoft Entra ID と統合すると、次のことが可能になります。

- JDA Cloud にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで JDA Cloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- JDA Cloud でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- JDA Cloud では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーからの JDA Cloud の追加

Microsoft Entra ID への JDA Cloud の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に JDA Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**JDA Cloud**」と入力します。
4. 結果のパネルから **[JDA Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### JDA Cloud 用に Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、JDA Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと JDA Cloud の関連ユーザー間にリンク関係を確立する必要があります。

JDA Cloud に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **JDA Cloud の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **JDA Cloud のテスト ユーザーの作成** - Microsoft Entra のユーザーとして表される B.Simon の対応ユーザーを JDA Cloud に作成し、それにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**JDA Cloud**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.jdadelivers.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.jdadelivers.com/sp/ACS.saml2`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://ssonp-dl2.jdadelivers.com/sp/startSSO.ping?PartnerIdpId=<AZURE_AD_IDENTIFIER>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 **Microsoft Entra Identifier** の値は、[**JDA Cloud のセットアップ**] セクションから取得します。 これらの値を取得するには、[JDA Cloud クライアント サポート チーム](https://support.jda.com/)にご連絡ください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[JDA Cloud のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### JDA Cloud の SSO の構成

**JDA Cloud** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [JDA Cloud サポート チーム](https://support.jda.com/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### JDA Cloud テスト ユーザーを作成する

このセクションでは、JDA Cloud で Britta Simon というユーザーを作成します。 [JDA Cloud サポート チーム](https://support.jda.com/)と連携して、JDA Cloud プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる JDA Cloud サインオン URL にリダイレクトされます。
- JDA Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した JDA Cloud に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [JDA Cloud] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した JDA Cloud に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jedox-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Jedox を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jedox-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Jedox 間のシングル サインオンを構成する方法について説明します。

この記事では、Jedox と Microsoft Entra ID を統合する方法について説明します。 Jedox を Microsoft Entra ID と統合すると、次のことができます。

- Jedox にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Jedox に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Jedox でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Jedox では、**SP開始SSO** と **IDP開始SSO** の両方がサポートされます。

### ギャラリーからの Jedox の追加

Microsoft Entra ID への Jedox の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Jedox を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Jedox**」と入力します。
4. 結果のパネルから **[Jedox]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Jedox に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Jedox に Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Jedox の関連ユーザーとの間にリンク関係を確立する必要があります。

Jedox に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Jedox の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Jedox テスト ユーザーの作成** - Jedox で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Jedox**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.cloud.jedox.com/be/saml.php`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.cloud.jedox.com/ui/login/`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.cloud.jedox.com/ui/login/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Jedox サポート チーム](https://my.jedox.com/) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Jedox の SSO の構成

**Jedox** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Jedox サポート チーム](https://my.jedox.com/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Jedox のテスト ユーザーの作成

このセクションでは、Jedox で Britta Simon というユーザーを作成します。 [Jedox サポート チーム](https://my.jedox.com/)と協力して、Jedox プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

1. [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Jedox サインオン URL にリダイレクトされます。
2. Jedox のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Jedox に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [Jedox] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Jedox に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jellyfish-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Jellyfish を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jellyfish-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-10
- Summary: Microsoft Entra ID から Jellyfish にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Jellyfish と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーを [Jellyfish](https://training.cogitogroup.net/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Jellyfish でユーザーを作成します。
- アクセスが不要になったら、Jellyfish のユーザーを削除します。
- Microsoft Entra ID と Jellyfish の間でユーザー属性の同期を維持します。
- Jellyfish に[シングル サインオンします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Jellyfish のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- [Microsoft Entra ID と Jellyfish の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: プロビジョニング用の資格情報を生成する

1. **Jellyfish** ポータルにログインし、**キー管理 &gt; API キー**に移動します。
2. [**新規作成]** を選択する

    [Image: API キー管理ページのスクリーンショット。]
3. 前提条件の一部として作成された管理者アカウントを検索し、[ **作成**] を選択します。 (必要に応じて) 有効期限を設定し、有効期限が切れた後に資格情報を更新 *する必要* があることを示します。

    [Image: 新しい API キーの作成のスクリーンショット。]
4. API キーがダウンロードされ、生成されたユーザー アカウントへのアクセスが許可されるため、このキーが安全に保持されていることを確認します。 ユーザー プロビジョニングが構成されたら、ダウンロードした API キーを削除することをお勧めします。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Jellyfish を追加する

Microsoft Entra アプリケーション ギャラリーから Jellyfish を追加して、Jellyfish へのプロビジョニングの管理を開始します。 SSO 用に Jellyfish を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Jellyfish への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて Jellyfish のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Jellyfish の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Jellyfish**] を選択します。

    [Image: アプリケーションの一覧の [Jellyfish] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Jellyfish テナントの URL とシークレット トークンを入力します。 Microsoft Entra ID が Jellyfish に接続できることを確認するには、[ **テスト接続** ] を選択します。 接続に失敗した場合は、Jellyfish アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から Jellyfish に同期されるユーザー **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Jellyfish のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Jellyfish API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Jellyfish に必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | タイトル | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jfrog-artifactory-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に JFrog Artifactory を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jfrog-artifactory-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と JFrog Artifactory の間でシングル サインオンを構成する方法について説明します。

この記事では、JFrog Artifactory と Microsoft Entra ID を統合する方法について説明します。 JFrog Artifactory と Microsoft Entra ID を統合すると、次のことができます。

- JFrog Artifactory にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して JFrog Artifactory に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- JFrog Artifactory でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- JFrog Artifactory では、**サービス プロバイダ（SP）および ID プロバイダ（IDP）**が開始する SSO の両方がサポートされます。
- JFrog Artifactory では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの JFrog Artifactory の追加

Microsoft Entra ID への JFrog Artifactory の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に JFrog Artifactory を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**JFrog Artifactory**」と入力します。
4. 結果のパネルから **[JFrog Artifactory]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### JFrog Artifactory 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、JFrog Artifactory に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと JFrog Artifactory の関連ユーザーとの間にリンク関係を確立する必要があります。

JFrog Artifactory に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **JFrog Artifactory SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **JFrog Artifactory でテストユーザーを作成する - JFrog Artifactory において B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra ID 表示にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**JFrog Artifactory**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<SERVERNAME>.jfrog.io`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SERVERNAME>.jfrog.io/<SERVERNAME>/webapp/saml/loginResponse`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SERVERNAME>.jfrog.io/<SERVERNAME>/webapp/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [JFrog Artifactory サポート チーム](https://support.jfrog.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. JFrog Artifactory アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. 上記に加えて、JFrog Artifactory アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. [ **JFrog Artifactory のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### JFrog Artifactory SSO の構成

**JFrog Artifactory** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** と、Microsoft Entra 管理センターからコピーした適切な URL を [JFrog Artifactory サポート チーム](https://support.jfrog.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### JFrog Artifactory のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを JFrog Artifactory に作成します。 JFrog Artifactory では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 JFrog Artifactory にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる JFrog Artifactory のサインオン URL にリダイレクトします。
- JFrog Artifactory のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した JFrog Artifactory に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [JFrog Artifactory] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した JFrog Artifactory に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jira52microsoft-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に JIRA SAML SSO by Microsoft (V5.2) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jira52microsoft-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-05
- Summary: Microsoft Entra ID と JIRA SAML SSO by Microsoft (V5.2) の間でシングル サインオンを構成する方法について説明します。

Warnung

**非推奨の通知:** このプラグインは、2026 年 5 月 1 日に非推奨となりました。 JIRA のシングル サインオンを引き続き設定するには、 [ここで](https://support.atlassian.com/opsgenie/docs/configure-saml-based-sso/) サポートされている Atlassian SAML 統合を使用するか、Atlassian Cloud に移行します。 [ここから](https://www.atlassian.com/migration/assess/journey-to-cloud)始めることができます。

この記事では、JIRA SAML SSO by Microsoft (V5.2) とMicrosoft Entra IDを統合する方法について説明します。 JIRA SAML SSO by Microsoft (V5.2) と Microsoft Entra ID を統合すると、以下のことができます。

- JIRA SAML SSO by Microsoft (V5.2) にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して JIRA SAML SSO by Microsoft (V5.2) に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 説明

Microsoft Entra アカウントを Atlassian JIRA サーバーで使用して、シングル サインオンを有効にします。 これにより、組織のすべてのユーザーが、Microsoft Entra の資格情報を使用して JIRA アプリケーションにサインインできます。 このプラグインは、フェデレーションに SAML 2.0 を使用します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- JIRA CoreおよびJIRA Software 5.2は、Windows 64ビット版にインストールされ、構成される必要があります。
- JIRA サーバーで HTTPS が有効になっている。
- JIRA プラグインのサポートされているバージョンに注意してください。詳細は下記のセクションをご覧ください。
- JIRA サーバーが、認証のためにインターネット上で特に Microsoft Entra ログイン ページにアクセスでき、Microsoft Entra ID からトークンを受け取れること。
- 管理者の資格情報が JIRA で設定されている。
- WebSudo が JIRA で無効になっている。
- テスト ユーザーが JIRA サーバー アプリケーションで作成されている。

注意

この記事の手順をテストするために、JIRA の運用環境を使用することはお勧めしません。 最初にアプリケーションの開発環境またはステージング環境で統合をテストし、そのあとに運用環境を使用してください。

この記事の手順をテストするには、次の推奨事項に従う必要があります。

- 運用環境は、必要な場合を除き、使用しないでください。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。

### サポートされている JIRA のバージョン

- JIRA Core と Software: 5.2。
- JIRA では、6.0 から 7.12 もサポートされています。 詳細については、 [JIRA SAML SSO by Microsoft](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jiramicrosoft-tutorial) を選択してください。

注意

JIRA プラグインは、Ubuntu Version 16.04 でも動作することに注意してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- JIRA SAML SSO by Microsoft (V5.2) では、 **SP** Initiated SSO がサポートされています。

### Microsoft の JIRA SAML SSO (V5.2) をギャラリーから追加する

Microsoft Entra ID への JIRA SAML SSO by Microsoft (V5.2) の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に JIRA SAML SSO by Microsoft (V5.2) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「JIRA SAML SSO by Microsoft (V5.2)」**と入力します。
4. 結果のパネルから **JIRA SAML SSO by Microsoft (V5.2)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### JIRA SAML SSO by Microsoft (V5.2) 用の Microsoft Entra SSO の構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、JIRA SAML SSO by Microsoft (V5.2) で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと JIRA SAML SSO by Microsoft (V5.2) 内の関連ユーザーとの間にリンク関係を確立する必要があります。

JIRA SAML SSO by Microsoft (V5.2) との Microsoft Entra シングル サインオンを構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **JIRA SAML SSO by Microsoft (V5.2) SSO**の構成 - アプリケーション側で単一 Sign-On 設定を構成します。
    1. **JIRA SAML SSO by Microsoft (V5.2) テスト ユーザーの作成** - Microsoft Entra 上のユーザー情報と連携した JIRA SAML SSO by Microsoft (V5.2) で Britta Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**JIRA SAML SSO by Microsoft (V5.2)** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<domain:port>/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domain:port>/plugins/servlet/saml/auth`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domain:port>/plugins/servlet/saml/auth`

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 名前付き URL である場合は、ポートは省略できます。 これらの値は、Jira プラグインの構成中に受け取られます。これについては、この記事の後半で説明します。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### JIRA SAML SSO by Microsoft (V5.2) の SSO の構成

1. 別の Web ブラウザー ウィンドウで、JIRA インスタンスに管理者としてサインインします。
2. 歯車アイコンにマウスをポイントし、**アドオン**を選択します。

    [Image: [設定] メニューから選択されたアドオンを示すスクリーンショット。]
3. [アドオン] タブ セクションで、[アドオンの **管理**] を選択します。

    [Image: [アドオン] タブで選択されている [アドオンの管理] を示すスクリーンショット。]
4. [Microsoft ダウンロード センターからプラグインをダウンロードします](https://www.microsoft.com/download/details.aspx?id=56521)。 [アドオンのアップロード] メニューを使用して、Microsoft が提供するプラグインを手動 **でアップロード** します。 プラグインのダウンロードは、 [Microsoft サービス契約](https://www.microsoft.com/servicesagreement/)の対象となります。

    [Image: [アドオンのアップロード] リンクが強調表示されているアドオンの管理を示すスクリーンショット。]
5. プラグインがインストールされると、[User Installed add-ons]\ **(ユーザーインストール済み** アドオン\) セクションに表示されます。 [ **構成] を** 選択して新しいプラグインを構成します。
6. 構成ページで次の手順を実行します。

    [Image: Microsoft Jira S S O Connector の構成ページを示すスクリーンショット。]

    ヒント

    メタデータの解決でエラーが発生しないように、アプリに対してマップされている証明書が 1 つしかないようにします。 証明書が複数ある場合は、メタデータの解決の際に管理者に対してエラーが表示されます。

    a. [ **メタデータ URL** ] ボックスに、コピーした **アプリのフェデレーション メタデータ URL** の値を貼り付け、[ **解決** ] ボタンを選択します。 IdP メタデータ URL が読み取られ、すべてのフィールド情報が設定されます。

    b。 **[識別子]、[応答 URL]、[サインオン URL**] の各値をコピーし**、[基本的な SAML 構成]** セクションの **[識別子]、[応答 URL]、および [サインオン URL**] ボックスにそれぞれ貼り付けます。

    c. [ **ログイン ボタン名]** に、組織がユーザーにログイン画面で表示するボタンの名前を入力します。

    d. **SAML ユーザー ID の場所**で、**ユーザー ID が Subject ステートメントの NameIdentifier 要素**に含まれているか**、ユーザー ID が Attribute 要素に含まれている**かどうかを選択します。 この ID は JIRA ユーザー ID である必要があります。 ユーザー ID が一致しない場合、システムはユーザーのサインインを許可しません。

    注意

    既定の SAML ユーザー ID の場所は、名前識別子です。 属性オプションでこれを変更して、適切な属性名を入力できます。

    e. [ **属性要素] オプションで [ユーザー ID]** を選択した場合は、[ **属性名** ] ボックスに、ユーザー ID が必要な属性の名前を入力します。

    f. Microsoft Entra ID でフェデレーション ドメイン (ADFS など) を使用している場合は、[ **ホーム領域検出を有効にする** ] オプションを選択し **、ドメイン名**を構成します。

    g. [ **ドメイン名]** に、ADFS ベースのログインの場合は、ここでドメイン名を入力します。

    h. ユーザーが JIRA からサインアウトするときに Microsoft Entra ID からサインアウトする場合は、[ **シングル サインアウトを有効にする] をオンにします** 。

    一. [ **保存]** ボタンを選択して設定を保存します。

    注意

    インストールとトラブルシューティングの詳細については、 [MS JIRA SSO Connector 管理ガイド](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ms-confluence-jira-plugin-adminguide) を参照してください。また、サポートに関する [FAQ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ms-confluence-jira-plugin-adminguide) もあります。

#### Microsoft (V5.2) による JIRA SAML SSO テスト ユーザーの作成

Microsoft Entra ユーザーの JIRA オンプレミス サーバーへのサインインを有効にするには、そのユーザーを JIRA オンプレミス サーバーにプロビジョニングする必要があります。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. 管理者として、オンプレミス サーバーの JIRA にサインインします。
2. 歯車アイコンをポイントし、[ **ユーザー管理**] を選択します。

    [Image: [設定] メニューから選択されたユーザー管理を示すスクリーンショット。]
3. 管理者アクセス ページにリダイレクトされ、「 **パスワード** 」と入力し、[ **確認** ] ボタンを選択します。

    [Image: 資格情報を入力する管理者アクセス ページを示すスクリーンショット。]
4. [ **ユーザー管理** ] タブ セクションで、[ **ユーザーの作成**] を選択します。

    [Image: スクリーンショットは、ユーザーを作成できる [ユーザー管理] タブを示しています。]
5. **[新しいユーザーの作成]** ダイアログ ページで、次の手順を実行します。

    [Image: この手順の情報を入力できる [新しいユーザーの作成] ダイアログ ボックスを示すスクリーンショット。]

    a. [ **電子メール アドレス** ] ボックスに、ユーザーのメール アドレス ( Brittasimon@contoso.comなど) を入力します。

    b。 [ **Full Name]\(フル ネーム** \) ボックスに、Britta Simon のようなユーザーのフル ネームを入力します。

    c. [Username]\( **ユーザー名** \) テキストボックスに、ユーザーの電子メール ( Brittasimon@contoso.comなど) を入力します。

    d. [ **パスワード** ] ボックスに、ユーザーのパスワードを入力します。

    e. [ **ユーザーの作成] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる JIRA SAML SSO by Microsoft (V5.2) のサインオン URL にリダイレクトされます。
- JIRA SAML SSO by Microsoft (V5.2) のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで JIRA SAML SSO by Microsoft (V5.2) タイルを選択すると、このオプションは JIRA SAML SSO by Microsoft (V5.2) のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jiramicrosoft-tutorial"} -->
## Microsoft Entra ID で JIRA SAML SSO by Microsoft for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jiramicrosoft-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-12
- Summary: Microsoft Entra ID と JIRA SAML SSO by Microsoft の間でシングル サインオンを構成する方法について説明します。

Warnung

**非推奨の通知:** このプラグインは、2026 年 5 月 1 日に非推奨となりました。 JIRA のシングル サインオンを引き続き設定するには、 [ここで](https://support.atlassian.com/opsgenie/docs/configure-saml-based-sso/) サポートされている Atlassian SAML 統合を使用するか、Atlassian Cloud に移行します。 [ここから](https://www.atlassian.com/migration/assess/journey-to-cloud)始めることができます。

この記事では、JIRA SAML SSO by Microsoft と Microsoft Entra ID を統合する方法について説明します。 JIRA SAML SSO by Microsoft と Microsoft Entra ID を統合すると、次のことができます。

- JIRA SAML SSO by Microsoft にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して JIRA SAML SSO by Microsoft に自動的にサインインできるように設定できます。
- 1 つの場所でアカウントを管理します。

### 説明

Microsoft Entra アカウントを Atlassian JIRA サーバーで使用して、シングル サインオンを有効にします。 これにより、組織のすべてのユーザーが、Microsoft Entra の資格情報を使用して JIRA アプリケーションにサインインできます。 このプラグインは、フェデレーションに SAML 2.0 を使用します。

JIRA は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- JIRA Core および Software 7.0 から 10.5.1、または JIRA Service Desk 3.0 から 5.12.22 を Windows 64 ビット バージョンにインストールして構成する必要があります。
- JIRA サーバーで HTTPS が有効になっている。
- JIRA プラグインのサポートされているバージョンは下記のセクションに記されています。
- JIRA サーバーが認証のためにインターネット、特に Microsoft Entra ログイン ページにアクセスでき、Microsoft Entra ID からトークンを受け取れること。
- 管理者の資格情報が JIRA で設定されている。
- WebSudo が JIRA で無効になっている。
- テスト ユーザーが JIRA サーバー アプリケーションで作成されている。

注

この記事の手順をテストするために、JIRA の運用環境を使用することはお勧めしません。 最初にアプリケーションの開発環境またはステージング環境で統合をテストし、そのあとに運用環境を使用してください。

開始するには、次が必要です。

- 必要な場合を除き、運用環境を使用しないでください。
- Microsoft による JIRA SAML SSO シングル サインオン (SSO) が有効化されたサブスクリプション。

### サポートされている JIRA のバージョン

- JIRA Core とソフトウェア: 7.0 から 10.5.1。
- JIRA Service Desk 3.0 から 5.12.22。
- JIRA は 5.2 もサポートします。 詳細については、 [JIRA 5.2 の Microsoft Entra シングル サインオンを](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jira52microsoft-tutorial)選択してください。

注

JIRA プラグインは、Ubuntu Version 16.04 と Linux でも動作することに注意してください。

### Microsoft SSO プラグイン

- [JIRA データセンター アプリケーションの Microsoft Entra ID シングル サインオン](https://marketplace.atlassian.com/apps/1224430/microsoft-azure-active-directory-single-sign-on-for-jira?tab=overview&amp;hosting=datacenter)
- [JIRA サーバー側アプリケーションの Microsoft Entra ID シングル サインオン](https://www.microsoft.com/en-us/download/details.aspx?id=56506)

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- JIRA SAML SSO by Microsoft では、 **SP** Initiated SSO がサポートされています。

### Microsoft による JIRA SAML SSO をギャラリーから追加

Microsoft Entra ID への JIRA SAML SSO by Microsoft の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に JIRA SAML SSO by Microsoft を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「JIRA SAML SSO by Microsoft**」と入力します。
4. 結果パネルから **JIRA SAML SSO by Microsoft** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### JIRA SAML SSO by Microsoft の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、JIRA SAML SSO by Microsoft に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと JIRA SAML SSO by Microsoft の関連ユーザーの間で、リンク関係を確立する必要があります。

JIRA SAML SSO by Microsoft で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **JIRA SAML SSO by Microsoft SSO を構成**する - アプリケーション側でシングル サインオン設定を構成します。
    1. **JIRA SAML SSO by Microsoft テスト ユーザーの作成** - JIRA SAML SSO by Microsoft で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザーをリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[JIRA SAML SSO by Microsoft]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<domain:port>/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domain:port>/plugins/servlet/saml/auth`

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domain:port>/plugins/servlet/saml/auth`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 名前付き URL である場合は、ポートは省略できます。 これらの値は、Jira プラグインの構成中に受け取られます。これについては、この記事の後半で説明します。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. Microsoft Entra ID の名前 ID 属性は、[属性とクレーム] セクションを編集することで必要なあらゆるユーザー属性にマップできます。

    [Image: 属性と要求を編集する方法を示すスクリーンショット。]

    a. [編集] を選択した後、必要なユーザー属性をマップするには、[一意のユーザー識別子 (名前 ID)] を選択します。

    [Image: 属性と要求の NameID を示すスクリーンショット。]

    b。 次の画面では、[ソース属性] ドロップダウン メニューから user.userprincipalname などの必要な属性名をオプションとして選択できます。

    [Image: [属性と要求] を選択する方法を示すスクリーンショット。]

    c. その後、上部にある [保存] ボタンを選択して、選択内容を保存できます。

    [Image: 属性と要求を保存する方法を示すスクリーンショット。]

    d. これで、Microsoft Entra ID の user.userprincipalname 属性ソースが Microsoft Entra の名前 ID 属性名にマップされます。これは、SSO プラグインによって Atlassian のユーザー名属性と比較されます。

    [Image: 属性と要求を確認する方法を示すスクリーンショット。]

    注

    Microsoft Azure によって提供される SSO サービスでは、名、姓、電子メール (電子メール アドレス)、ユーザー プリンシパル名 (ユーザー名) などのさまざまな属性を使用してユーザー識別を実行できる SAML 認証がサポートされています。 電子メール アドレスは常に Microsoft Entra ID によって検証されるとは限らないので、認証属性として電子メールを使用しないことをお勧めします。 このプラグインは、有効なユーザー認証を決定するために、Atlassian のユーザー名属性の値と Microsoft Entra ID の NameID 属性を比較します。
8. Azure テナントに **ゲスト ユーザー** が存在する場合は、次の構成手順に従います。

    a. **鉛筆**アイコンを選択して、[属性と要求] セクションに移動します。

    [Image: 属性と要求を編集する方法を示すスクリーンショット。]

    b。 [属性] および [要求] セクションで **[NameID]** を選択します。

    [Image: 属性と要求の NameID を示すスクリーンショット。]

    c. ユーザーの種類に基づいてクレーム条件を設定します。

    [Image: 要求条件のスクリーンショット。]

    注

    メンバーには `user.userprincipalname`、外部ゲストには `user.mail` を NameID 値として指定します。

    d. 変更を**保存**し、外部ゲスト ユーザーの SSO を確認します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### JIRA SAML SSO by Microsoft の SSO の構成

1. 別の Web ブラウザー ウィンドウで、JIRA インスタンスに管理者としてサインインします。
2. 歯車アイコンにカーソルを合わせ、**アドオン**を選択します。

    [Image: [設定] メニューから選択されたアドオンを示すスクリーンショット。]
3. [Microsoft ダウンロード センターからプラグインをダウンロードします](https://www.microsoft.com/download/details.aspx?id=56506)。 [アドオンのアップロード] メニューを使用して、Microsoft が提供するプラグインを手動 **でアップロード** します。 プラグインのダウンロードは、 [Microsoft サービス契約](https://www.microsoft.com/servicesagreement/)の対象となります。

    [Image: [アドオンのアップロード] リンクが強調表示されているアドオンの管理を示すスクリーンショット。]
4. JIRA のリバース プロキシ シナリオまたはロード バランサー シナリオを実行するには、次の手順を実行します。

    注

    以下の手順に従って最初にサーバーを構成した後、プラグインをインストールする必要があります。

    a. JIRA サーバー アプリケーションのserver.xml** ファイルの ** **コネクタ** ポートに次の属性を追加します。

    `scheme="https" proxyName="<subdomain.domain.com>" proxyPort="<proxy_port>" secure="true"`

    [Image: スクリーンショットは、新しい行が追加されたエディターのサーバー ドット x m l ファイルを示しています。]

    b。 プロキシ/ロード バランサーに従って**、システム設定**の**ベース URL を**変更します。

    [Image: スクリーンショットは、Base U R L を変更できる [管理設定] を示しています。]
5. プラグインがインストールされると、[アドオンの管理] セクションの [ **ユーザーがインストールした** アドオン] セクション **に** 表示されます。 [ **構成] を** 選択して新しいプラグインを構成します。
6. 構成ページで次の手順を実行します。

    [Image: スクリーンショットは、Jira 構成ページの Microsoft Entra シングル サインオンを示しています。]

    ヒント

    メタデータの解決でエラーが発生しないように、アプリに対してマップされている証明書が 1 つしかないようにします。 証明書が複数ある場合は、メタデータの解決の際に管理者に対してエラーが表示されます。

    a. [ **メタデータ URL** ] ボックスに、コピーした **アプリのフェデレーション メタデータ URL** の値を貼り付け、[ **解決** ] ボタンを選択します。 IdP メタデータ URL が読み取られ、すべてのフィールド情報が設定されます。

    b。 **識別子、応答 URL、サインオン URL** の値をコピーし、Azure portal の **JIRA SAML SSO by Microsoft の [ドメインと URL**] セクションで、識別子**、応答 URL**、サインオン URL の各テキスト ボックスに貼り付けます。

    c. [ **ログイン ボタン名]** に、組織がユーザーにログイン画面で表示するボタンの名前を入力します。

    d. [ **ログイン ボタンの説明]** に、組織がユーザーにログイン画面で表示するボタンの説明を入力します。

    e. **[既定のグループ]** で、新しいユーザーに割り当てる組織の既定のグループを選択します。 既定のグループを使用すると、新しいユーザー アカウントに付与するアクセス権を容易に整理できます。

    f. **SAML ユーザー ID の場所**で、**ユーザー ID が Subject ステートメントの NameIdentifier 要素**に含まれているか**、ユーザー ID が Attribute 要素に含まれている**かどうかを選択します。 この ID は JIRA ユーザー ID である必要があります。 ユーザー ID が一致しない場合、システムはユーザーのサインインを許可しません。

    注

    既定の SAML ユーザー ID の場所は、名前識別子です。 属性オプションでこれを変更して、適切な属性名を入力できます。

    g. [ **ユーザー ID が属性要素内にある**] を選択した場合は、[ **属性名**] に、ユーザー ID が必要な属性の名前を入力します。

    h. **自動作成ユーザー**機能 (JIT ユーザー プロビジョニング): 承認された Web アプリケーションでのユーザー アカウントの作成を自動化します。手動プロビジョニングは必要ありません。 これにより、管理ワークロードが削減され、生産性が向上します。 JIT は Azure AD からのログイン応答に依存するため、ユーザーのメールアドレス、姓、名を含む SAML 応答属性値を入力します。

    一. Microsoft Entra ID でフェデレーション ドメイン (ADFS など) を使用している場合は、[ **ホーム領域検出を有効にする** ] オプションを選択し **、ドメイン名**を構成します。

    j. [ **ドメイン名]** に、ADFS ベースのログインの場合は、ここでドメイン名を入力します。

    k. ユーザーが JIRA からサインアウトするときに Microsoft Entra ID からサインアウトする場合は、[ **シングル サインアウトを有効にする] を** 選択します。

    l. Microsoft Entra ID 資格情報のみを使用してサインインする場合は、[ **Azure ログインの強制** ] を有効にします。

    注

    Azure ログインの強制を有効にしたときにログイン ページで管理ログインの既定のログイン フォームを有効にするには、ブラウザー URL にクエリ パラメーターを追加します。 `https://<domain:port>/login.jsp?force_azure_login=false`

    m. アプリケーション プロキシのセットアップでオンプレミスの Atlassian アプリケーションを構成した場合は、[アプリケーション **プロキシの使用を有効にする] を** 選択します。

    - アプリ プロキシのセットアップについては、 [Microsoft Entra アプリケーション プロキシのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)の手順に従います。

    n. [ **保存] を** 選択して設定を保存します。

    注

    インストールとトラブルシューティングの詳細については、 [MS JIRA SSO Connector 管理者ガイド](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ms-confluence-jira-plugin-adminguide)を参照してください。 サポートに関する [FAQ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ms-confluence-jira-plugin-adminguide) もあります。

#### JIRA SAML SSO by Microsoft テスト ユーザーの作成

オンプレミス サーバーで Microsoft Entra ユーザーの JIRA へのサインインを有効にするには、そのユーザーを JIRA SAML SSO by Microsoft にプロビジョニングする必要があります。 JIRA SAML SSO by Microsoft では、手動でプロビジョニングします。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. 管理者として、オンプレミス サーバーの JIRA にサインインします。
2. 歯車アイコンをポイントし、[ **ユーザー管理**] を選択します。

    [Image: [設定] メニューから選択されたユーザー管理を示すスクリーンショット。]
3. 管理者アクセス ページにリダイレクトされ、「 **パスワード** 」と入力し、[ **確認** ] ボタンを選択します。

    [Image: 資格情報を入力する管理者アクセス ページを示すスクリーンショット。]
4. [ **ユーザー管理** ] タブ セクションで、[ **ユーザーの作成**] を選択します。

    [Image: スクリーンショットは、ユーザーを作成できる [ユーザー管理] タブを示しています。]
5. **[新しいユーザーの作成]** ダイアログ ページで、次の手順を実行します。

    [Image: この手順の情報を入力できる [新しいユーザーの作成] ダイアログ ボックスを示すスクリーンショット。]

    a. [ **電子メール アドレス** ] ボックスに、ユーザーのメール アドレス ( B.simon@contoso.comなど) を入力します。

    b。 [Full Name]\( **フル ネーム** \) テキストボックスに、B.Simon のようなユーザーのフル ネームを入力します。

    c. [Username]\( **ユーザー名** \) テキストボックスに、ユーザーの電子メール ( B.simon@contoso.comなど) を入力します。

    d. [ **パスワード** ] ボックスに、ユーザーのパスワードを入力します。

    e. [ **ユーザーの作成] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる JIRA SAML SSO by Microsoft サインオン URL にリダイレクトされます。
- JIRA SAML SSO by Microsoft のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [JIRA SAML SSO by Microsoft] タイルを選択すると、このオプションは JIRA SAML SSO by Microsoft のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jitbit-helpdesk-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Jitbit Helpdesk を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jitbit-helpdesk-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-04
- Summary: Microsoft Entra ID と Jitbit Helpdesk の間のシングル サインオンを構成する方法について説明します。

この記事では、Jitbit Helpdesk と Microsoft Entra ID を統合する方法について説明します。 Jitbit Helpdesk を Microsoft Entra ID と統合すると、次のことが可能になります。

- Jitbit Helpdesk にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Jitbit Helpdesk に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Jitbit Helpdesk は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Jitbit Helpdesk でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Jitbit Helpdesk では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Jitbit Helpdesk の追加

Microsoft Entra ID への Jitbit Helpdesk の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Jitbit Helpdesk を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Jitbit Helpdesk**」と入力します。
4. 結果パネルから **Jitbit Helpdesk** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Jitbit Helpdesk 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Jitbit Helpdesk に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Jitbit Helpdesk の関連ユーザーとの間にリンク関係を確立する必要があります。

Jitbit Helpdesk に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Jitbit Helpdesk SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Jitbit Helpdesk のテスト ユーザーを作成する** - Jitbit Helpdesk で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**Jitbit Helpdesk**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用していずれかの URL を入力します。

    - `https://<hostname>/helpdesk/User/Login`
    - `https://<tenant-name>.Jitbit.com`

    注

    この値は実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値を取得するには [、Jitbit Helpdesk クライアント サポート チーム](https://www.jitbit.com/support/) に問い合わせてください。

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://www.jitbit.com/web-helpdesk/`
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Jitbit Helpdesk のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Jitbit Helpdesk SSO の構成

1. 別の Web ブラウザーのウィンドウで、Jitbit Helpdesk の企業サイトに管理者としてサインインします。
2. 上部のツール バーで、[ **管理**] を選択します。

    [Image: 管理]
3. [ **全般設定] を選択します**。

    [Image: [全般設定] リンクを示すスクリーンショット。]
4. [ **認証設定** の構成] セクションで、次の手順を実行します。

    [Image: 認証設定]

    a. **OneLogin** でシングル サインオン (SSO) を使用してサインインするには、[**SAML 2.0 シングル サインオンを有効にする]** を選択します。

    b。 **[EndPoint URL**] ボックスに、**ログイン URL** の値を貼り付けます。

    c. **Base-64** でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、**X.509 証明書**ボックスに貼り付けます。

    d. [ **変更の保存] を選択します**。

#### Jitbit Helpdesk テスト ユーザーの作成

Microsoft Entra ユーザーが Jitbit Helpdesk にサインインできるようにするには、そのユーザーを Jitbit Helpdesk にプロビジョニングする必要があります。 Jitbit Helpdesk の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. **Jitbit Helpdesk** テナントにサインインします。
2. 上部のメニューで、[管理] を選択 **します**。

    [Image: 管理]
3. **[ユーザー]、[会社]、[アクセス許可] を選択します**。

    [Image: ユーザー、会社、アクセス許可]
4. [ **ユーザーの追加] を選択します**。

    [Image: ユーザーを追加]
5. [作成] セクションで、プロビジョニングする Microsoft Entra アカウントのデータを次のように入力します。

    [Image: 作成​​]

    a. [ **ユーザー名** ] ボックスに、 **BrittaSimon** などのユーザーのユーザー名を入力します。

    b。 [ **電子メール** ] ボックスに、ユーザーの電子メール ( **BrittaSimon@contoso.com**など) を入力します。

    c. **名**テキストボックスに、ユーザーの名を**Britta**のように入力します。

    d. [ **姓]** ボックスに、ユーザーの家族名 ( **Simon** など) を入力します。

    e. **作成**を選択します。

注

Jitbit Helpdesk から提供されている他の Jitbit Helpdesk ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる Jitbit Helpdesk のサインオン URL にリダイレクトされます。
- Jitbit Helpdesk のサインオン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Jitbit Helpdesk] タイルを選択すると、このオプションは Jitbit Helpdesk のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jive-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Jive を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jive-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-10
- Summary: Microsoft Entra ID から Jive にユーザー アカウントを自動的にプロビジョニング/プロビジョニング解除するうえで Jive と Microsoft Entra ID で実行する必要がある手順について学習します。

この記事の目的は、Microsoft Entra ID から Jive にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するために Jive と Microsoft Entra ID で実行する必要がある手順を示することです。

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- Microsoft Entra テナント。
- シングルサインオンが有効になっている Jive のサブスクリプション
- Team Admin アクセス許可がある Jive のユーザー アカウント

### Jive へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に "割り当て" という概念が使用されます。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに "割り当て済み" のユーザーとグループのみが同期されます。

プロビジョニング サービスを構成して有効にする前に、Jive アプリへのアクセスが必要なユーザーを表す Microsoft Entra ID 内のユーザーやグループを決定しておく必要があります。 決定し終えたら、次の手順でこれらのユーザーを Jive アプリに割り当てることができます。

[エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Jive に割り当てる際の重要なヒント

- プロビジョニング構成をテストするには、1 人の Microsoft Entra ユーザーを Jive に割り当てることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Jive にユーザーを割り当てるときに、有効なユーザー ロールを選択する必要があります。 "既定のアクセス" ロールはプロビジョニングでは機能しません。

### ユーザー プロビジョニングの有効化

このセクションでは、Microsoft Entra ID を Jive のユーザー アカウント プロビジョニング API に接続する手順と、Microsoft Entra ID のユーザーとグループの割り当てに基づいて、割り当て済みのユーザー アカウントを Jive で作成、更新、無効化するようにプロビジョニング サービスを構成する手順を説明します。

ヒント

[Azure ポータル](https://portal.azure.com) に記載されている手順に従って、Jive に対して SAML ベースのシングル サインオンを有効にすることもできます>。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### ユーザー アカウント プロビジョニングを構成するには

このセクションでは、Active Directory のユーザー アカウントのプロビジョニングを Jive に対して有効にする方法を説明します。 この手順の一環として、Jive.com から要求する必要があるユーザー セキュリティ トークンを指定する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. シングル サインオンのために Jive を既に構成している場合は、検索フィールドで Jive のインスタンスを検索します。 それ以外の場合は、**[追加]** を選択してアプリケーション ギャラリーで **Jive** を検索します。 検索結果から Jive を選択してアプリケーションの一覧に追加します。
4. Jive のインスタンスを選択してから、 **[プロビジョニング]** タブを選択します。
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Jive テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Jive に接続できることを確認します。 接続に失敗した場合は、Jive アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Jive に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Jive のユーザー アカウントとの照合に使用されます。 [保存] ボタンをクリックして変更をコミットします。
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログの読み方の詳細については、「[自動ユーザー アカウント プロビジョニングについてのレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jive-tutorial"} -->
## Microsoft Entra ID で Jive for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jive-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Jive の間のシングル サインオンを構成する方法について説明します。

この記事では、Jive と Microsoft Entra ID を統合する方法について説明します。 Jive を Microsoft Entra ID と統合すると、次のことが可能になります。

- Jive にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Jive に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Jive でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Jive では、 **SP** Initiated SSO がサポートされます。
- Jive では、 [**自動** ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jive-provisioning-tutorial)。

### ギャラリーからの Jive の追加

Microsoft Entra ID への Jive の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Jive を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Jive**」と入力します。
4. 結果パネルから **Jive** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Jive に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Jive に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Jive の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Jive と一緒に構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Jive SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Jive テスト ユーザーの作成** - Jive で B.Simon に相当するユーザーを作成し、それを Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Jive**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<instance name>.jivecustom.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。

    ```http
    https://<instance name>.jiveon.com
    ```

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [Jive クライアント サポート チーム](https://www.jivesoftware.com/services-support/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Jive のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Jive の SSO の構成

1. **Jive** 側でシングル サインオンを構成するには、管理者として Jive テナントにサインオンします。
2. 上部のメニューで、[SAML] を選択 **します**。

    [Image: [有効] が選択された [SAML] タブを示すスクリーンショット。]

    a. [**全般**] タブで [**有効]** を選択します。

    b。 [ **SAVE ALL SAML SETTINGS]\(すべての SAML 設定を保存** \) ボタンを選択します。
3. **[IDP メタデータ**] タブに移動します。

    [Image: [SAML] タブの [I D P METADATA] が選択されているスクリーンショット。]

    a. ダウンロードしたメタデータ XML ファイルの内容をコピーし、 **ID プロバイダー (IDP) メタデータ** テキスト ボックスに貼り付けます。

    b。 [ **SAVE ALL SAML SETTINGS]\(すべての SAML 設定を保存** \) ボタンを選択します。
4. [ **USER ATTRIBUTE MAPPING]\(ユーザー属性マッピング** \) タブを選択します。

    [Image: [ユーザー属性マッピング] が選択されている [SAML] タブを示すスクリーンショット。]

    a. [ **電子メール** ] ボックスに、 **メール** 値の属性名をコピーして貼り付けます。

    b。 **First Name** テキストボックスに、**givenname** の属性名をコピーして貼り付けます。

    c. **姓**テキストボックスに、**surname**値の属性名をコピーして貼り付けます。

#### Jive のテスト ユーザーの作成

このセクションの目的は、Jive で Britta Simon というユーザーを作成することです。 Jive では、自動ユーザー プロビジョニングがサポートされています。この設定は、既定で有効になっています。 自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jive-provisioning-tutorial) 。

ユーザーを手動で作成する必要がある場合は、 [Jive クライアント サポート チーム](https://www.jivesoftware.com/services-support/) と協力して、Jive プラットフォームにユーザーを追加してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Jive のサインオン URL にリダイレクトされます。
- Jive のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Jive] タイルを選択すると、このオプションは Jive のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jll-tririga-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に JLL TRIRIGA を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jll-tririga-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と JLL TRIRIGA 間のシングル サインオンを構成する方法について説明します。

この記事では、JLL TRIRIGA と Microsoft Entra ID を統合する方法について説明します。 JLL TRIRIGA を Microsoft Entra ID と統合すると、次のことが可能になります。

- JLL TRIRIGA へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで JLL TRIRIGA に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な JLL TRIRIGA サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- JLL TRIRIGA では、**IDP** によって開始されたSSOがサポートされます

### ギャラリーからの JLL TRIRIGA の追加

Microsoft Entra ID への JLL TRIRIGA の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に JLL TRIRIGA を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「JLL TRIRIGA**」と入力します。
4. 結果パネルから **JLL TRIRIGA** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### JLL TRIRIGA に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、JLL TRIRIGA に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと JLL TRIRIGA の関連ユーザーとの間にリンク関係を確立する必要があります。

JLL TRIRIGA に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **JLL TRIRIGA SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **JLL TRIRIGA テスト ユーザーを作成する** - これは、Microsoft Entra のユーザー表現にリンクされる B.Simon の対応役を JLL TRIRIGA で持つためです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**JLL TRIRIGA**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://<SUBDOMAIN>.valudconsulting.com:PORT` |
    | `https://<SUBDOMAIN>.jll.com` |
    |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://<SUBDOMAIN>.valudconsulting.com:PORT/samlsps/trisaml` |
    | `https://<SUBDOMAIN>.jll.com/samlsps/trisaml` |
    |  |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [JLL TRIRIGA クライアント サポート チーム](https://www.us.jll.com/contact-us) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **JLL TRIRIGA のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### JLL TRIRIGA の SSO の構成

**JLL TRIRIGA** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [JLL TRIRIGA サポート チーム](https://www.us.jll.com/contact-us)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### JLL TRIRIGA のテスト ユーザーの作成

このセクションでは、JLL TRIRIGA で Britta Simon というユーザーを作成します。 [JLL TRIRIGA サポート チーム](https://www.us.jll.com/contact-us)と協力して、JLL TRIRIGA プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した JLL TRIRIGA に自動的にサインインします
- Microsoft アクセス パネルを使用することができます。 アクセス パネルで [JLL TRIRIGA] タイルを選択すると、SSO を設定した JLL TRIRIGA に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jobbadmin-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Jobbadmin を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jobbadmin-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Jobbadmin の間のシングル サインオンを構成する方法について説明します。

この記事では、Jobbadmin と Microsoft Entra ID を統合する方法について説明します。 Jobbadmin を Microsoft Entra ID と統合すると、次のことが可能になります。

- Jobbadmin にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Jobbadmin に自動的にサインインできるように設定する。
- アカウントを一元的に管理する。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Jobbadmin のサブスクリプション。
- クラウド アプリケーション管理者に加え、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができる。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Jobbadmin は、**SP** initiated SSO をサポートしています。

### ギャラリーから Jobbadmin を追加する

Microsoft Entra ID への Jobbadmin の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Jobbadmin を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Jobbadmin**」と入力します。
4. 結果のパネルから **[Jobbadmin]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Jobbadmin の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Jobbadmin で Microsoft Entra SSO を構成およびテストします。 SSO が機能するには、Microsoft Entra ユーザーとそれに対応する Jobbadmin ユーザーをリンクする必要があります。

Jobbadmin に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Jobbadmin SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Jobbadmin のテストユーザーを作成 - Jobbadmin** において Microsoft Entra のユーザー表現とリンクされた B.Simon に対応する存在を作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Jobbadmin** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて**、シングル サインオン**を選択します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<instancename>.jobnorge.no`

    b。 **[応答 URL]** ボックスに、`https://<instancename>.jobbnorge.no/auth/saml2/login.ashx` のパターンを使用して URL を入力します。

    c. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<instancename>.jobbnorge.no/auth/saml2/login.ashx`

    Note

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには、[Jobbadmin クライアント サポート チーム](https://grade.zammad.com/help)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Jobbadmin のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 適切な構成 URL のコピー操作を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Jobbadmin SSO の構成

**Jobbadmin** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Jobbadmin サポート チーム](https://grade.zammad.com/help)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Jobbadmin のテスト ユーザーの作成

このセクションでは、Jobbadmin で Britta Simon というユーザーを作成します。 [Jobbadmin サポート チーム](https://grade.zammad.com/help)と連携して、Jobbadmin プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Jobbadmin のサインオン URL にリダイレクトされます。
- Jobbadmin のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Jobbadmin] タイルを選択すると、このオプションは Jobbadmin のサインオン URL にリダイレクトされます。 詳細については、[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jobhub-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に JOBHUB を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jobhub-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と JOBHUB 間のシングル サインオンを構成する方法について説明します。

この記事では、JOBHUB と Microsoft Entra ID を統合する方法について説明します。 JOBHUB を Microsoft Entra ID と統合すると、次のことが可能になります。

- JOBHUB にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで JOBHUB に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- JOBHUB のシングル サインオン (SSO) が有効になったサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。 JOBHUB では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの JOBHUB の追加

Microsoft Entra ID への JOBHUB の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に JOBHUB を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックスに **「JOBHUB** 」と入力します。
4. 結果パネルから **JOBHUB** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra のシングル サインオンの構成とテスト

**Britta Simon** というテスト ユーザーを使用して、JOBHUB に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと JOBHUB の関連ユーザーとの間にリンク関係を確立する必要があります。

JOBHUB に対する Microsoft Entra SSO を構成およびテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成** する - ユーザーがこの機能を使用できるようにします。
2. **JOBHUB SSO の構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **JOBHUB のテスト ユーザーの作成** - JOBHUB で Britta Simon に対応するユーザーを作成し、Microsoft Entra の Britta Simon にリンクさせます。
6. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**JOBHUB** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成** ] セクションで、次のフィールドの値を入力します。 **[サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://pasona.jobhub.jp/saml/init`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、JOBHUB クライアント サポート チーム](mailto:platform@pasonagroup.co.jp) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. [ **JOBHUB のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### JOBHUB SSO の構成

**JOBHUB** 側でシングル サインオンを構成するには、**拇印の値**と、アプリケーション構成からコピーした適切な URL を [JOBHUB サポート チーム](mailto:platform@pasonagroup.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### JOBHUB テスト ユーザーの作成

このセクションでは、JOBHUB で Britta Simon というユーザーを作成します。 [JOBHUB サポート チーム](mailto:platform@pasonagroup.co.jp)と協力して、JOBHUB プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### SSO のテスト

アクセス パネルで [JOBHUB] タイルを選択すると、SSO を設定した JOBHUB に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jobscience-tutorial"} -->
## Microsoft Entra ID で Jobscience for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jobscience-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Jobscience の間のシングル サインオンを構成する方法について説明します。

この記事では、Jobscience と Microsoft Entra ID を統合する方法について説明します。

Jobscience を Microsoft Entra ID と統合すると、次の利点があります。

- Jobscience にアクセスできるユーザーを Microsoft Entra ID で制御できる
- ユーザーが自分の Microsoft Entra アカウントで自動的に Jobscience にサインオン (シングル サインオン) できるようになる
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます

SaaS アプリと Microsoft Entra ID の統合の詳細については、 [アプリケーション アクセスと Microsoft Entra ID でのシングル サインオンの概要](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Jobscience でのシングル サインオンが有効なサブスクリプション

Note

この記事の手順をテストするために、運用環境を使用することはお勧めしません。

この記事の手順をテストするには、次の推奨事項に従う必要があります。

- 必要な場合を除き、運用環境を使用しないでください。
- Microsoft Entra 試用版環境をお持ちでない場合は、1 か月間の試用版 ( [試用版](https://azure.microsoft.com/pricing/free-trial/)) を入手できます。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンをテストします。 この記事で説明するシナリオは、次の 2 つの主要な構成要素で構成されています。

1. ギャラリーからの Jobscience の追加
2. Microsoft Entra シングル サインオンの構成とテスト

### ギャラリーからの Jobscience の追加

Microsoft Entra ID への Jobscience の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Jobscience を追加する必要があります。

**ギャラリーから Jobscience を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Jobscience**」と入力します。
4. 結果パネルから **Jobscience** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra シングル サインオンの構成とテスト

このセクションでは、"Britta Simon" というテスト ユーザーに基づいて、Jobscience で Microsoft Entra のシングル サインオンを構成してテストします。

シングル サインオンを機能させるには、Microsoft Entra ID ユーザーに対応する Jobscience ユーザーが Microsoft Entra ID で認識されている必要があります。 言い換えると、Microsoft Entra ユーザーと、Jobscience での関連ユーザーとの間で、リンク関係が確立されている必要があります。

Jobscience で、Microsoft Entra ID の **ユーザー名** の値を **Username** の値として割り当ててリンク関係を確立します。

Jobscience に対する Microsoft Entra シングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. **Microsoft Entra シングル サインオンの構成** - ユーザーがこの機能を使用できるようにします。
2. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
3. **Jobscience テスト ユーザーの作成** - Jobscience で Britta Simon に対応するユーザーを作成し、Microsoft Entra の Britta Simon にリンクさせます。
4. **Microsoft Entra テスト ユーザーの割り当て** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra のシングル サインオンを構成する

このセクションでは、Azure Portal で Microsoft Entra のシングル サインオンを有効にして、Jobscience アプリケーションでシングル サインオンを構成します。

**Jobscience で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Jobscience** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: [管理] で [シングル サインオン] が選択されているスクリーンショット。]
3. [ **シングル サインオン** ] ダイアログで、[モード **] を** **[SAML ベースのサインオン** ] として選択して、シングル サインオンを有効にします。
4. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `http://<company name>.my.salesforce.com`

    Note

    この値は実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値は [、Jobscience クライアント サポート チーム](https://www.bullhorn.com/technical-support/) または作成した SSO プロファイルから取得します。これについては、この記事の後半で説明します。
5. [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を選択し、証明書ファイルをコンピューターに保存します。
6. **[保存**] ボタンを選択します。
7. [ **Jobscience の構成]** セクションで、[ **Jobscience の構成** ] を選択して **[サインオンの構成] ウィンドウを** 開きます。 **[クイック リファレンス] セクション**から**、Sign-Out URL、SAML エンティティ ID、SAML 単一 Sign-On サービス URL を**コピーします。
8. Jobscience の企業サイトに管理者としてログインします。
9. **[セットアップ]** に移動します。

    [Image: 会社の [セットアップ] 項目を示すスクリーンショット。]
10. 左側のナビゲーション ウィンドウの [ **管理** ] セクションで、[ **ドメイン管理** ] を選択して関連セクションを展開し、[ **マイ ドメイン]** を選択して [ **マイ ドメイン]** ページを開きます。

    [Image: マイドメイン]
11. ドメインが正しく設定されていることを確認するには、「**手順 4 でユーザーに展開された**」であることを確認し**、[マイ ドメインの設定]** を確認します。

    [Image: ユーザーに展開されたドメイン]
12. Jobscience 企業サイトで、[ **セキュリティコントロール**] を選択し、[ **単一の Sign-On 設定]** を選択します。

    [Image: [セキュリティ コントロール] で選択されている [Single Sign-On Settings](単一の Sign-On 設定) を示すスクリーンショット。]
13. [ **単一 Sign-On 設定]** セクションで、次の手順に従います。

    [Image: 単一 Sign-On 設定]

    a. [ **SAML Enabled] を選択します**。

    b。 [ **新規**] を選択します。
14. **[SAML Single Sign-On Setting Edit]\(SAML 単一 Sign-On 設定の編集\**) ダイアログで、次の手順を実行します。

    [Image: SAML 単一 Sign-On 設定]

    a. [ **名前** ] ボックスに、構成の名前を入力します。

    b。 **[発行者**] ボックスに、**SAML エンティティ ID** の値を貼り付けます。

    c. **Entity Id** ボックスに「 」と入力します。 `https://salesforce-jobscience.com`

    d. **参照** を選択して Microsoft Entra 証明書をアップロードします。

    e. **SAML ID の種類**として、[Assertion] を選択すると、**User オブジェクトのフェデレーション ID が含まれます**。

    f. **SAML ID の場所**として、[**Identity is in the NameIdentifier element of the Subject statement]\(ID は Subject ステートメントの NameIdentifier 要素にあります**\) を選択します。

    g. **Identity Provider Login URL** テキストボックスに、**SAML Single Sign-On Service URL** の値を貼り付けます。

    h. **ID プロバイダーのログアウト URL** ボックスに、URL の値 **Sign-Out** 貼り付けます。

    一. **[保存] を選択します**。
15. 左側のナビゲーション ウィンドウの [ **管理** ] セクションで、[ **ドメイン管理** ] を選択して関連セクションを展開し、[ **マイ ドメイン]** を選択して [ **マイ ドメイン]** ページを開きます。

    [Image: マイドメイン]
16. [ **マイ ドメイン]** ページの [ **ログイン ページのブランド化** ] セクションで、[編集] を選択 **します**。

    [Image: スクリーンショットは、[編集] ボタンを含む [Login Page Branding](ログイン ページのブランド化) セクションを示しています。]
17. [ **Login Page Branding]\(ログイン ページのブランド化** \) ページの **[Authentication Service** ]\(認証サービス\) セクションに、 **SAML SSO 設定** の名前が表示されます。 それを選択し、[ **保存]** を選択します。

    [Image: スクリーンショットは、PPE と [保存] が選択された [Login Page Branding](ログイン ページのブランド化) セクションを示しています。]
18. SP によって開始されるシングル サインオン ログイン URL を取得するには、[**セキュリティ コントロール**] メニュー セクションの **[シングル サインオン] 設定**を選択します。

    [Image: [Administer Security Controls](セキュリティ コントロールの管理) で [Single Sign-On Settings](シングル サインオンの設定) が選択されていることを示すスクリーンショット。]

    上記の手順で作成した SSO プロファイルを選択します。 このページには、会社のシングル サインオン URL が表示されます (例: `https://companyname.my.salesforce.com?so=companyid`)。

ヒント

これで、アプリのセットアップ中に、 [Azure portal](https://portal.azure.com) 内でこれらの手順の簡潔なバージョンを読むことができます。 **Active Directory &gt; Enterprise Applications** セクションからこのアプリを追加したら、[**シングル サインオン**] タブを選択し、下部にある **[構成**] セクションから埋め込みドキュメントにアクセスするだけです。 埋め込みドキュメント機能の詳細については、[Microsoft Entra ID の埋め込みドキュメント](https://go.microsoft.com/fwlink/?linkid=845985)を参照してください。

#### Microsoft Entra テスト ユーザーの作成

このセクションの目的は、Britta Simon というテスト ユーザーを作成することです。

[Image: Microsoft Entra ユーザーの作成]

**Microsoft Entra ID でテスト ユーザーを作成するには、次の手順に従います。**

1. Microsoft Entra 管理センターで、 **Entra ID**&gt;**Users** に移動します。

    [Image: [管理] メニューから選択された [ユーザーとグループ] を示すスクリーンショット。[すべてのユーザー] が選択されています。]
2. **[ユーザー**] ダイアログを開くには、ダイアログの上部にある [**追加**] を選択します。

    [Image: [ユーザー] ダイアログ ボックスを開く [追加] ボタンを示すスクリーンショット。]
3. [ **ユーザー** ] ダイアログ ページで、次の手順を実行します。

    [Image: この手順の値を入力できる [ユーザー] ダイアログ ボックスを示すスクリーンショット。]

    a. [ **名前** ] ボックスに「 **BrittaSimon**」と入力します。

    b。 [ **ユーザー名** ] ボックスに、BrittaSimon の **メール アドレス** を入力します。

    c. [ **パスワードの表示** ] を選択し、[パスワード] の値を書き留 **めます**。

    d. **作成**を選択します。

#### Jobscience テスト ユーザーの作成

Microsoft Entra ユーザーが Jobscience にログインできるようにするには、そのユーザーを Jobscience にプロビジョニングする必要があります。 Jobscience の場合、プロビジョニングは手動で行います。

Note

他の Jobscience ユーザー アカウント作成ツールや Jobscience から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

**ユーザー プロビジョニングを構成するには、次の手順を実行します。**

1. **Jobscience** 企業サイトに管理者としてログインします。
2. [セットアップ] に移動します。

    [Image: [セットアップ] 項目を示すスクリーンショット。]
3. **ユーザーの管理**&gt;に移動して**ユーザー**を管理します。

    [Image: ユーザー]
4. **新しいユーザー**を選択します。

    [Image: すべてのユーザー]
5. [ **ユーザーの編集]** ダイアログで、次の手順を実行します。

    [Image: ユーザー編集]

    a. **名**ボックスに、ユーザーの名前としてBrittaなどを入力します。

    b。 [ **姓]** ボックスに、ユーザーの姓 (Simon など) を入力します。

    c. [ **エイリアス** ] ボックスに、brittas などのユーザーのエイリアス名を入力します。

    d. [ **電子メール** ] ボックスに、ユーザーのメール アドレス ( Brittasimon@contoso.comなど) を入力します。

    e. [ **ユーザー名]** ボックスに、ユーザーのユーザー名 ( Brittasimon@contoso.comなど) を入力します。

    f. **[Nick Name]\(ニック名\)** テキストボックスに、Simon のようなユーザーのニック名を入力します。

    g. **[保存] を選択します**。

Note

Microsoft Entra アカウント所有者が電子メールを受信し、リンクに従ってアカウントを確認すると、そのアカウントがアクティブになります。

#### Microsoft Entra テスト ユーザーの割り当て

このセクションでは、Britta Simon に Jobscience へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

[Image: アカウントの表示名を示すスクリーンショット。]

**Jobscience に Britta Simon を割り当てるには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Jobscience** を参照します。

    [Image: [Jobscience] が選択されているスクリーンショット。]
3. 左側のメニューで、[ **ユーザーとグループ**] を選択します。

    [Image: [ユーザーとグループ] が選択されているメニューを示すスクリーンショット。]
4. [ **追加] ボタンを** 選択します。 次に、[**割り当ての追加**] ダイアログで [**ユーザーとグループ**] を選択します。

    [Image: 割り当ての追加に使用される [追加] ボタンを示すスクリーンショット。]
5. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧で **[Britta Simon** ] を選択します。
6. [**ユーザーとグループ**] ダイアログの [選択] ボタンを**選択**します。
7. [ **割り当ての追加]** ダイアログの [ **割り当て** ] ボタンを選択します。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Jobscience] タイルを選択すると、Jobscience アプリケーションに自動的にサインオンします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jobscore-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に JobScore を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jobscore-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と JobScore の間のシングル サインオンを構成する方法について説明します。

この記事では、JobScore と Microsoft Entra ID を統合する方法について説明します。 JobScore を Microsoft Entra ID と統合すると、以下のことが可能になります。

- 誰が JobScore にアクセスできるかを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して JobScore に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な JobScore のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- JobScore では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの JobScore の追加

JobScore の Microsoft Entra ID への統合を構成するには、JobScore をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「JobScore**」と入力します。
4. 結果パネルから **JobScore** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### JobScore の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、JobScore に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと JobScore の関連ユーザーとの間にリンク関係を確立する必要があります。

JobScore での Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **JobScore SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **JobScore のテスト ユーザーの作成** - B.Simon に対応する JobScore 上のユーザーを作成し、それを Microsoft Entra 内のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**JobScore** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて**、シングル サインオン**を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://hire.jobscore.com/auth/adfs/<company id>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、JobScore クライアント サポート チーム](mailto:support@jobscore.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **JobScore のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成URLをコピーしたスクリーンショットを表示します。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### JobScore SSO の構成

**JobScore** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [JobScore サポート チーム](mailto:support@jobscore.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### JobScore のテスト ユーザーの作成

このセクションでは、JobScore で Britta Simon というユーザーを作成します。 [JobScore サポート チーム](mailto:support@jobscore.com)と協力して、JobScore プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる JobScore のサインオン URL にリダイレクトされます。
- JobScore のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [JobScore] タイルを選択すると、このオプションは JobScore のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/joinedup-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に JoinedUp を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/joinedup-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と JoinedUp 間のシングル サインオンを構成する方法について説明します。

この記事では、JoinedUp と Microsoft Entra ID を統合する方法について説明します。 JoinedUp を Microsoft Entra ID と統合すると、次のことが可能になります。

- JoinedUp にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで JoinedUp に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- JoinedUp でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- JoinedUp では、**SP** によって開始される SSO がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから JoinedUp を追加する

Microsoft Entra ID への JoinedUp の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に JoinedUp を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**JoinedUp**」と入力します。
4. 結果のパネルから **[JoinedUp]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### JoinedUp 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、JoinedUp で Microsoft Entra SSO を構成およびテストします。 SSO を機能させるには、Microsoft Entra ユーザーと JoinedUp の関連ユーザーとの間にリンク関係を確立する必要があります。

JoinedUp に対する Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **JoinedUp SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **JoinedUp テストユーザーの作成 - B.Simon に対応する JoinedUp ユーザーを作成し、それを Microsoft Entra でのユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[JoinedUp]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.joinedup.com`

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには、[JoinedUp クライアント サポート チーム](mailto:support@joinedup.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### JoinedUp SSO を構成する

**JoinedUp** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [JoinedUp サポート チーム](mailto:support@joinedup.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### JoinedUp テスト ユーザーを作成する

このセクションでは、JoinedUp で Britta Simon というユーザーを作成します。 [JoinedUp サポート チーム](mailto:support@joinedup.com)と連携し、JoinedUp プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる JoinedUp のサインオン URL にリダイレクトされます。
- JoinedUp のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [JoinedUp] タイルを選択すると、このオプションは JoinedUp のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/joinme-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの join.me を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/joinme-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と join.me の間にシングル サインオンを構成する方法について説明します。

この記事では、join.me と Microsoft Entra ID を統合する方法について説明します。 join.me と Microsoft Entra ID の統合には、次の利点があります。

- join.me にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して join.me に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- join.me シングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- join.me では、 **IDP** Initiated SSO がサポートされます

### ギャラリーからの join.me の追加

Microsoft Entra ID への join.me の統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに join.me を追加する必要があります。

**ギャラリーから join.me を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. 検索ボックスに「join.me」 **と**入力し、結果パネルで **join.me** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧に join.me を表示する]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、join.me で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと join.me の関連ユーザーの間にリンク関係を確立する必要があります。

join.me で Microsoft Entra シングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **join.meのシングル サインオンを構成する** - アプリケーション側でシングル Sign-On 設定を行います。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **join.me テストユーザーを作成** - join.me で Britta Simon に対応するユーザーを作成して、Microsoft Entra のユーザー表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

join.me で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**join.me** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。

    [Image: join.me ドメインおよびURLのシングルサインオン情報]
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### join.me のシングル サインオンの構成

**join.me** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[サポート チーム join.me](https://help.join.me/s/?language) 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### join.me のテスト ユーザーの作成

このセクションでは、join.me で Britta Simon というユーザーを作成します。 [join.me サポート チーム](https://help.join.me/s/?language)と協力して、join.me プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [join.me] タイルを選択すると、SSO を設定した join.me に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jooto-tutorial"} -->
## Microsoft Entra ID で Jooto for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jooto-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Jooto 間のシングル サインオンを構成する方法について説明します。

この記事では、Jooto と Microsoft Entra ID を統合する方法について説明します。 Jooto を Microsoft Entra ID と統合すると、次のことが可能になります。

- Jooto にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Jooto に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Jooto でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Jooto では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Jooto では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Jooto を追加する

Microsoft Entra ID への Jooto の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Jooto を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Jooto**」と入力します。
4. 結果のパネルから **[Jooto]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Jooto に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Jooto に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Jooto の関連ユーザーとの間にリンク関係を確立する必要があります。

Jooto に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Jooto SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Jooto テストユーザーの作成** - Microsoft Entra における B.Simon の表現と連携する形で、Jooto 内に B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Jooto]**&gt;**[シングル サインオン]** を閲覧します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **[基本的な SAML 構成]** セクションで、アプリケーションを **SP** 開始モードで構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://app.jooto.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://app.jooto.com/auth/sso/callback`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.jooto.com/auth/sso/callback`

    d. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<ID>`
7. Jooto アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Jooto アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | first\_name | User.givenname |
    | last\_name | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
    | ユーザー名 | user.userprincipalname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Jooto! のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Jooto SSO の構成

**Jooto** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Jooto サポート チーム](mailto:jooto-success@prtimes.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Jooto テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Kion に作成します。 Jooto では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Jooto にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Jooto のサインオン URL にリダイレクトされます。
- Jooto のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Jooto に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Jooto] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Jooto に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/josa-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に JOSA を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/josa-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と JOSA の間のシングル サインオンを構成する方法について説明します。

この記事では、JOSA と Microsoft Entra ID を統合する方法について説明します。 JOSA を Microsoft Entra ID と統合すると、次のことが可能になります。

- JOSA にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して JOSA に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「[Microsoft Entra ID でのアプリケーション アクセスとシングル サインオンとは](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- JOSA でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- JOSA では、**SP** Initiated SSO がサポートされます

### ギャラリーからの JOSA の追加

Microsoft Entra ID への JOSA の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に JOSA を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**JOSA**」と入力します。
4. 結果パネルから **[JOSA]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### JOSA 向けに Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、JOSA に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと JOSA の関連ユーザーとの間にリンク関係を確立する必要があります。

JOSA に対して Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO の構成**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **JOSA の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **JOSA のテストユーザーを作成する - B.Simon に対応するユーザーを JOSA に作成し、Microsoft Entra のユーザー表現にリンクするためです。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

次の手順に従って、Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[JOSA]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、**サービス プロバイダー メタデータ ファイル**がある場合は、次の手順に従います。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルを選択する]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**の値が、[基本的な SAML 構成] セクションに自動的に設定されます。

    **[サインオン URL]** テキスト ボックスに、URL として「`https://www.jo-sa.dk/adfslogin.php`」と入力します。

    注

    **識別子**の値が自動的に設定されない場合は、要件に従って値を手動で入力してください。 サインオン URL の値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには、[JOSA クライアント サポート チーム](mailto:hr@alldialogue.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### JOSA SSO の構成

**JOSA** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [JOSA サポート チーム](mailto:hr@alldialogue.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### JOSA テスト ユーザーの作成

このセクションでは、JOSA で B.Simon というユーザーを作成します。 [JOSA サポート チーム](mailto:hr@alldialogue.com)と連携して JOSA プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra シングル サインオン構成をテストします。

アクセス パネルで [JOSA] タイルを選択すると、SSO を設定した JOSA に自動的にサインインします。 アクセス パネルの詳細については、[アクセス パネルの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jostle-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Jostle を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jostle-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: Microsoft Entra IDから Jostle にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Jostle と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 設定された場合、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Jostle](https://www.jostle.me/) に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされる機能

- Jostle でユーザーを作成する
- アクセスが不要になった場合に Jostle のユーザーを削除する
- Microsoft Entra IDと Jostle の間でユーザー属性の同期を維持する
- Jostle への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jostle-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Jostle テナント](https://www.jostle.me/)。
- 管理者アクセス許可がある Jostle のユーザー アカウント。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとJostleの間でマップするデータを決定する。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Jostle を構成する

#### Automation アカウント

開始する前に、Jostle イントラネットで **Automation ユーザー**を作成する必要があります。 これは、Azureで構成するために使用するアカウントです。 Automation ユーザーは、管理の **[設定] &gt; [ユーザー アカウントとデータ] &gt; [Automation ユーザーの管理]** で作成できます。

Automation ユーザーとその作成方法の詳細については、[こちらの記事](https://forum.jostle.us/hc/en-us/articles/360057364073)を参照してください。

作成後、Automation ユーザー アカウントは、(つまり、イントラネットに少なくとも 1 回ログインして) アクティブ化されてからでないと、Azure の構成に使用できません。

#### ユーザー プロビジョニングを管理する

開始する前に、アカウントのサブスクリプションに **SSO/ユーザープロビジョニング機能が含まれている**ことを確認してください。 含まれていない場合は、カスタマー サクセス マネージャーsuccess@jostle.me にご連絡いただければ、アカウントへの追加を支援いたします。

次のステップでは、Jostle から **API URL** と **API キー**を取得します。

1. メイン ナビゲーションに移動し **、[管理者設定]** を選択します。
2. [ **他のシステム間のユーザー データ** ] で、[ **ユーザー プロビジョニングの管理** ] を選択します。ここに [ユーザー プロビジョニングの管理] が表示されない場合に、アカウントに SSO/ユーザー プロビジョニングが含まれていることを確認した場合は、サポート support@jostle.me に問い合わせて、管理設定でこのページを有効にしてください)。
3. **[User Provisioning API details]\(ユーザー プロビジョニング API の詳細**\) セクションで、[**ベース URL**] フィールドに移動し、[コピー] ボタンを選択し、後で簡単にアクセスできる場所に URL を保存します。

    [Image: User Provisioning API の詳細のスクリーンショット。]
4. 次に、[ **新しいキーの追加**]を選択します。..ボタン
5. 次の画面で、 **[Automation User](Automation ユーザー)** フィールドに移動し、ドロップダウン メニューを使用して Automation ユーザー アカウントを選択します。

    [Image: 統合アカウントのスクリーンショット。]
6. **プロビジョニング API キーの説明** フィールドにキーに名前 (`Azure` など) を指定し、**Add** ボタンを選択します。
7. キーが生成されたら、 **すぐにコピー** し、URL を保存した場所に保存してください (キーが表示されるのは唯一の時間であるため)。
8. 次に、**API URL** と **API キー**を使用して、Azureで統合を構成します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Jostle を追加する

Microsoft Entra アプリケーション ギャラリーから Jostle を追加して、Jostle へのプロビジョニングの管理を開始します。 以前に SSO 用の Jostle を設定している場合は、同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Jostle への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーとグループの割り当てに基づいて Jostle アプリでユーザーとグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

注

Jostle への自動ユーザー プロビジョニングの詳細については、「[User-Provisioning-Azure-Integration](https://forum.jostle.us/hc/en-us/articles/360056368534-User-Provisioning-Azure-Integration)を参照してください。

#### Microsoft Entra IDで Jostle の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Jostle]** を選択します。

    [Image: アプリケーションの一覧の Jostle リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択し、[ **作業の開始**] を選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Jostle テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Jostle に接続できることを確認します。 接続に失敗した場合は、Jostle アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Jostle に同期されるユーザー属性を確認します。 **照合**プロパティとして選択された属性は、更新の操作を行う場合に Jostle のユーザー アカウントを照合する際に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Jostle API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | emails[type eq "work"].value | 糸 |  |
    | emails[type eq "personal"].value | 糸 |  |
    | emails[type eq "alternate1"].value | 糸 |  |
    | emails[type eq "alternate2"].value | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:alternateEmail1Label | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:alternateEmail2Label | 糸 |  |
    | DisplayName | 糸 |  |
    | 外部識別子 | 糸 |  |
    | Title | 糸 |  |
    | ニックネーム | 糸 |  |
    | ユーザー種別 | 糸 |  |
    | 生年月日 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:CustomBadge | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:CustomFilterCategory | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:CustomProfile | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:JoinDate | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:Locations | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:LoginType | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:PersonalPronouns | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:Address1Country | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:Address1Locality | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:Address1PostalCode | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:Address1StreetAddress | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:Address1Region | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:Address2Country | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:Address2Locality | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:Address2PostalCode | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:Address2StreetAddress | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:jostle:2.0:User:Address2Region | 糸 |  |
    | phoneNumbers[type eq "workofficephone"].value | 糸 |  |
    | phoneNumbers[type eq "homephone"].value | 糸 |  |
    | phoneNumbers[type eq "workmobilephone"].value | 糸 |  |
    | phoneNumbers[type eq "personalmobilephone"].value | 糸 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/jostle-tutorial"} -->
## Microsoft Entra ID で Jostle for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jostle-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Jostle 間にシングル サインオンを構成する方法について説明します。

この記事では、Jostle と Microsoft Entra ID を統合する方法について説明します。 Jostle を Microsoft Entra ID と統合すると、次のことができます。

- Jostle にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Jostle に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Jostle でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Jostle では、**SP** Initiated SSO がサポートされます。
- Jostle では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jostle-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Jostle の追加

Microsoft Entra ID への Jostle の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Jostle を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Jostle**」と入力します。
4. 結果のパネルから **[Jostle]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Jostle 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Jostle に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Jostle の関連ユーザーとの間にリンク関係を確立する必要があります。

Jostle に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Jostle の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Jostle テスト ユーザーを作成** - B.Simon に対応するユーザーを Jostle で作成し、Microsoft Entra での B.Simon の表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Jostle**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子** ] ボックスに、URL を入力します。 `https://jostle.us`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://login-prod.jostle.us/saml/SSO/alias/newjostle.us`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://login-prod.jostle.us`
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Jostle のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Jostle の SSO の構成

**Jostle** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[Jostle サポート チーム](mailto:support@jostle.me)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Jostle のテスト ユーザーの作成

このセクションでは、Jostle で Britta Simon というユーザーを作成します。 [Jostle サポート チーム](mailto:support@jostle.me)と連携して、Jostle プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Jostle では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/jostle-provisioning-tutorial)をご覧ください。

注

Microsoft Entra アカウント所有者がメールを受け取り、リンクに従ってアカウントを確認すると、そのアカウントがアクティブになります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Jostle のサインオン URL にリダイレクトされます。
- Jostle のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Jostle] タイルを選択すると、このオプションは Jostle のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/joyn-fsm-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Joyn FSM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/joyn-fsm-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: Microsoft Entra IDから Joyn FSM にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために、Joyn FSM とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを Joyn FSM に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされる機能

- Joyn FSM でユーザーを作成する
- アクセスが不要になった場合に Joyn FSM のユーザーを削除する
- Microsoft Entra ID と Joyn FSM の間でユーザー属性の同期を維持する
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Joyn FSM の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Joyn FSM を構成する

プロビジョニングの構成に必要なテナント URL とシークレット トークンを取得するには、 [SevenLakes カスタマー サクセス担当者](mailto:CustomerSuccessTeam@sevenlakes.com) にお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Joyn FSM を追加する

Microsoft Entra アプリケーション ギャラリーから Joyn FSM を追加して、Joyn FSM へのプロビジョニングの管理を開始します。 以前に Joyn FSM を SSO 用に設定している場合は、同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Joyn FSM への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて、Joyn FSM でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Joyn FSM の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **[Joyn FSM**] を選択します。

    [Image: アプリケーションの一覧の [Joyn FSM] リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Joyn FSM テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Joyn FSM に接続できることを確認します。 接続に失敗した場合は、Joyn FSM アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Joyn FSM に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Joyn FSM のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Joyn FSM API でサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Joyn FSM で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.formatted | 糸 |  |  |
    | displayName | 糸 |  |  |
    | externalId | 糸 |  |  |
    | name.givenName |  |  |  |
    | name.familyName | 糸 |  |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:joynfsm:2.0:User:xid | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:joynfsm:2.0:User:joynFieldId | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/juno-journey-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Juno Journey を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/juno-journey-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: Microsoft Entra IDから Juno Journey にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Juno Journey と Microsoft Entra ID の両方で自動ユーザー プロビジョニングを構成するために実行する必要がある手順について説明します。 構成したMicrosoft Entra IDは、Microsoft Entraプロビジョニングサービスを使用して、ユーザーとグループを[Juno Journey](https://www.junojourney.com/)に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Juno Journey でユーザーを作成する
- アクセスが不要になった場合に Juno Journey のユーザーを削除する
- Microsoft Entra IDと Juno Journey の間でユーザー属性の同期を維持する
- Juno Journey への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/juno-journey-tutorial) (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- [プロビジョニングを構成する権限](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ([Application Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [Application Owner](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications) など)。
- [Juno Journey テナント](https://app.junojourney.com/login)。
- 管理者アクセス許可がある Juno Journey のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとJuno Journeyの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Juno Journey を構成する

1. **シークレット トークン** と**テナント URL** について、Juno Journey のサポート チーム (support@the-juno.com) に問い合わせます。 この値は、Juno Journey アプリケーションの [プロビジョニング] タブの **[シークレット トークン** ] フィールドと [ **テナント URL** ] フィールドにそれぞれ入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Juno Journey を追加する

Microsoft Entra アプリケーション ギャラリーから Juno Journey を追加して、Juno Journey へのプロビジョニングの管理を開始します。 Juno Journey への SSO を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Juno Journey への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Juno Journey の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Juno Journey]** を選択します。

    [Image: アプリケーションの一覧の Juno Journey のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Juno Journey テナント URL とシークレット トークンを入力します。 **Test Connection** を選択してMicrosoft Entra IDが Juno Journey に接続できることを確認します。 接続に失敗した場合は、Juno Journey アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Juno Journey に同期されるユーザー属性を確認します。 **照合**用プロパティとして選択されている属性は、更新処理で Juno Journey のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Juno Journey API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 変数 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | externalId | 糸 |
    | displayName | 糸 |
    | タイトル | 糸 |
    | 活動中 | ブール値 |
    | 優先言語 | 糸 |
    | emails[type eq "work"].value | 糸 |
    | addresses[type eq "work"].country | 糸 |
    | addresses[type eq "work"].region | 糸 |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |
    | addresses[type eq "work"].postalCode | 糸 |
    | addresses[type eq "work"].formatted | 糸 |
    | addresses[type eq "work"].streetAddress | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | name.middleName | 糸 |
    | name.formatted | 糸 |
    | phoneNumbers[type eq "fax"].value | 糸 |
    | phoneNumbers[type eq "mobile"].value | 糸 |
    | phoneNumbers[type eq "work"].value | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/juno-journey-tutorial"} -->
## Microsoft Entra ID で Juno Journey for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/juno-journey-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Juno Journey の間でシングル サインオンを構成する方法について説明します。

この記事では、Juno Journey と Microsoft Entra ID を統合する方法について説明します。 Juno Journey と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Juno Journey へのアクセスを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Juno Journey に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Juno Journey でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Juno Journey では、**SP および IDP** によって開始されたSSOがサポートされます。
- Juno Journey では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Juno Journey では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/juno-journey-provisioning-tutorial)。

### ギャラリーからの Juno Journey の追加

Microsoft Entra ID への Juno Journey の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Juno Journey を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;「新しいアプリケーション」に移動してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Juno Journey**」と入力します。
4. 結果パネルから **Juno Journey** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Juno Journey の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Juno Journey に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Juno Journey の関連ユーザーとの間にリンク関係を確立する必要があります。

Juno Journey に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Juno Journey の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Juno Journey テストユーザーを作成** - Microsoft Entra のユーザー表現にリンクされた Juno Journey 内の B.Simon に相当するユーザーを持つために。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリケーション]**&gt;**[Juno Journey]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-subdomain>.the-juno.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-subdomain>.the-juno.com/sso/saml/login`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-subdomain>.the-juno.com/sso/saml/login`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、Juno Journey クライアント サポート チーム  にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Juno Journey のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Juno Journey SSO の構成

**Juno Journey** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、アプリケーション構成からコピーした適切な URL を [Juno Journey サポート チーム](mailto:support@the-juno.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Juno Journey のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Juno Journey に作成します。 Juno Journey では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Juno Journey にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

Juno Journey では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Juno Journey のサインオン URL にリダイレクトされます。
- Juno Journey のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Juno Journey に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Juno Journey] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Juno Journey に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/juriblox-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に JuriBlox を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/juriblox-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と JuriBlox の間のシングル サインオンを構成する方法について説明します。

この記事では、JuriBlox と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と JuriBlox を統合すると、次のことができます。

- JuriBlox へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで JuriBlox に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な JuriBlox サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- JuriBlox では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの JuriBlox の追加

Microsoft Entra ID への JuriBlox の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に JuriBlox を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**JuriBlox**」と入力します。
4. 結果パネルから **[JuriBlox]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### JuriBlox 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、JuriBlox に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、JuriBlox での関連ユーザーとの間にリンク関係を確立する必要があります。

JuriBlox 用の Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **JuriBlox の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **JuriBlox テスト ユーザーの作成** - JuriBlox で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**JuriBlox**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.juriblox.nl/auth/login`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### JuriBlox の SSO の構成

**JuriBlox** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [JuriBlox サポート チーム](mailto:support@juriblox.nl)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### JuriBlox のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを JuriBlox に作成します。 [JuriBlox サポート チーム](mailto:support@juriblox.nl)と協力して、JuriBlox プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる JuriBlox のサインオン URL にリダイレクトされます。
- JuriBlox のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [JuriBlox] タイルを選択すると、このオプションは JuriBlox のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/justlogin-tutorial"} -->
## Microsoft Entra ID で JustLogin for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/justlogin-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と JustLogin の間のシングル サインオンを構成する方法について説明します。

この記事では、JustLogin と Microsoft Entra ID を統合する方法について説明します。 JustLogin を Microsoft Entra ID と統合すると、次のことが可能になります。

- JustLogin にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで JustLogin に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- JustLogin のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- JustLogin により、**SP および IDP** Initiated SSO がサポートされます。

### ギャラリーから JustLogin を追加する

Microsoft Entra ID への JustLogin の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に JustLogin を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**JustLogin**」と入力します。
4. 結果のパネルから **JustLogin** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### JustLogin に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、JustLogin に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと JustLogin の関連ユーザーとの間にリンク関係を確立する必要があります。

JustLogin に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **JustLogin の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **JustLogin テスト ユーザーの作成** - JustLogin で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザーをリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[JustLogin]**&gt;**[シングル サインオン]** を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `JustLoginSAML/<CompanyID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://apis.justlogin.com/v1/auth/saml/AssertionConsumerService/<CompanyID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://apis.justlogin.com/v1/auth/saml/Login/<CompanyID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[JustLogin クライアント サポート チーム](mailto:support@justlogin.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Set up JustLogin](JustLogin の設定)** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### JustLogin SSO の構成

**JustLogin** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [JustLogin サポート チーム](mailto:support@justlogin.com)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### JustLogin のテスト ユーザーの作成

このセクションでは、JustLogin で Britta Simon というユーザーを作成します。 [JustLogin サポート チーム](mailto:support@justlogin.com)と連携し、JustLogin プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる JustLogin のサインオン URL にリダイレクトされます。
- JustLogin のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した JustLogin に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [JustLogin] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した JustLogin に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kairos-business-tutorial"} -->
## Microsoft Entra ID で Kairos Business for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kairos-business-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kairos Business の間でシングル サインオンを構成する方法について説明します。

この記事では、Kairos Business と Microsoft Entra ID を統合する方法について説明します。 Kairos Business と Microsoft Entra ID を統合すると、次のことができます。

- Kairos Business にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Kairos Business に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kairos Business でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Kairos Business では、 **IDP** Initiated SSO がサポートされます。
- Kairos Business では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Kairos Business を追加する

Microsoft Entra ID への Kairos Business の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Kairos Business を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Kairos Business」**と入力します。
4. 結果パネルから **Kairos Business** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kairos Business の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Kairos Business に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Kairos Business の関連ユーザーとの間にリンク関係を確立する必要があります。

Kairos Business に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kairos Business の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Kairos Business テストユーザーの作成** - Kairos Business で B.Simon の対応ユーザーを持ち、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Kairos Business**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかの値/パターンを入力します。

    | **識別子** |
    | --- |
    | `KairoBusiness` |
    | `<KairoBusiness_ENTITY_ID>` |

    注

     &lt;KairoBusiness\_ENTITY\_ID&gt; は本物ではありません。 これを実際の値で更新します。

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://www.dimepkairos.com.br/Dimep/Account/SamlLogon`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Kairos Business のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kairos Business SSO の構成

**Kairos Business** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、Microsoft Entra 管理センターからコピーした適切な URL を [Kairos Business サポート チーム](mailto:dimep@dimep.com.br)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Kairos Business のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Kairos Business に作成します。 Kairos Business では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Kairos Business にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した Kairos Business に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Kairos Business] タイルを選択すると、SSO を設定した Kairos Business に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kanbanbox-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に KanbanBOX を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kanbanbox-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と KanbanBOX の間のシングル サインオンを構成する方法について説明します。

この記事では、KanbanBOX と Microsoft Entra ID を統合する方法について説明します。 KanbanBOX は、サプライ チェーンに沿ったカンバン マテリアル フローをデジタル化します。 KanbanBOX は、社内の生産と物流の流れをサポートし、外部のサプライヤーや顧客とのコラボレーションをサポートします。 KanbanBOX を Microsoft Entra ID と統合すると、次のことが可能になります。

- KanbanBOX にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで KanbanBOX に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で KanbanBOX 用の Microsoft Entra シングル サインオンを構成してテストします。 KanbanBOX では、**SP**開始のシングルサインオンと**IDP**開始のシングルサインオンの両方がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

KanbanBOX を Microsoft Entra ID と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な KanbanBOX のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから KanbanBOX アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから KanbanBOX を追加する

KanbanBOX を使用したシングル サインオンを構成するには、Microsoft Entra アプリケーション ギャラリーから KanbanBOX を追加します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[KanbanBOX]**&gt;**[シングル サインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。

    [ **リレー状態** ] ボックスに、URL を入力します。 `https://app.kanbanbox.com/auth/idp_initiated_sso_login`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://app.kanbanbox.com/auth/login`
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **KanbanBOX のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成を適切な U R L にコピーするためのスクリーンショット。]

### KanbanBOX SSO の構成

**KanbanBOX** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [KanbanBOX サポート チーム](mailto:help@kanbanbox.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### KanbanBOX テスト ユーザーの作成

このセクションでは、KanbanBOX SSO で Britta Simon というユーザーを作成します。 [KanbanBOX サポート チーム](mailto:help@kanbanbox.com)と協力して、KanbanBOX SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる KanbanBOX のサインオン URL にリダイレクトされます。
- KanbanBOX のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した KanbanBOX に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [KanbanBOX] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した KanbanBOX に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kantegassoforbamboo-tutorial"} -->
## Microsoft Entra ID で Kantega SSO for Bamboo をシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kantegassoforbamboo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kantega SSO for Bamboo の間でシングル サインオンを構成する方法について説明します。

この記事では、Kantega SSO for Bamboo と Microsoft Entra ID を統合する方法について説明します。 Kantega SSO for Bamboo を Microsoft Entra ID と統合すると、次のことができます。

- Kantega SSO for Bamboo にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Kantega SSO for Bamboo に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kantega SSO for Bamboo でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Kantega SSO for Bamboo では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーからの Kantega SSO for Bamboo の追加

Microsoft Entra ID への Kantega SSO for Bamboo の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Kantega SSO for Bamboo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Kantega SSO for Bamboo**」と入力します。
4. 結果のパネルから **[Kantega SSO for Bamboo]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kantega SSO for Bamboo 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使って、Kantega SSO for Bamboo に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Kantega SSO for Bamboo の関連ユーザーとの間にリンク関係を確立する必要があります。

Kantega SSO for Bamboo に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kantega SSO for Bamboo SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Bamboo テストユーザー用の Kantega SSO を作成します** - Bamboo 用 Kantega SSO で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Kantega SSO for Bamboo**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<uniqueid>/login` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<uniqueid>/login` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<uniqueid>/login` という形式で URL を入力します。

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値は、Bamboo プラグインの構成中に受け取られます。これについては、この記事の後半で説明します。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[Set up Kantega SSO for Bamboo](Kantega SSO for Bamboo の設定)** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kantega SSO for Bamboo SSO の構成

1. 別の Web ブラウザー ウィンドウで、オンプレミス サーバーの Bamboo に管理者としてサインインします。
2. 歯車アイコンにカーソルを合わせ、**アドオン**を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) メニューの [Add-ons](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アドオン) が選択されているスクリーンショット。]
3. [アドオン] タブ セクションで、[ **新しいアドオンの検索**] を選択します。 **Kantega SSO for Bamboo (SAML & Kerberos)** を検索し、[**インストール**] ボタンを選択して新しい SAML プラグインをインストールします。

    [Image: [Kantega S S O for Bamboo] が選択されている [Bamboo Administration](Bamboo の管理) が示されているスクリーンショット。]
4. プラグインのインストールが開始されます。

    [Image: Kantega S S O for Bamboo のインストールの進行状況が示されているスクリーンショット。]
5. インストールが完了したら、**[閉じる]** を選択します。

    [Image: [Close](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/閉じる) ボタンを示すスクリーンショット。]
6. **Kantega SSO for Bamboo** ページで、**[管理]** を選択します。
7. [ **構成] を** 選択して新しいプラグインを構成します。

    [Image: [Configure](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) が選択された [User-installed add-ons](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーがインストールしたアドオン) を示すスクリーンショット。]
8. **[SAML]** セクションで、**[ID プロバイダーの追加]** ドロップダウンから **[Microsoft Entra ID]** を選択します。
9. **[Kantega シングル サインオン]** ページで、**[Basic]** を選択します。
10. **[App properties](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリのプロパティ)** セクションで、次の手順を実行します。

    [Image: この手順の情報を指定できる [App properties](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリのプロパティ) セクションを示すスクリーンショット。]

    ある。 **[アプリケーション ID/URI]** の値をコピーして、Azure portal の **[基本的な SAML 構成]** セクションで**識別子、応答 URL、サインオン URL** として使用します。

    b。 [**次へ**] を選択します。
11. **[Metadata import]** セクションで、**[Metadata file on my computer]** を選択します。
12. **[Browse file]** を選択して、以前にダウンロードしたメタデータ ファイルをアップロードし、**[Next]** を選択します。
13. **[Name and SSO location](名前と SSO の場所)** セクションで、次の手順を実行します。

    [Image: Microsoft Entra ID が ID プロバイダー名である [Name and SSO location] (名前と SSO の場所) を示すスクリーンショット。]

    ある。 **[ID プロバイダー名]** テキストボックスに、(Microsoft Entra ID などの) ID プロバイダーの名前を追加します。

    b。 [**次へ**] を選択します。
14. 署名証明書を確認し、[ **次へ**] を選択します。

    [Image: 署名の確認を示すスクリーンショット。]
15. **[Bamboo user accounts](Bamboo ユーザー アカウント)** セクションで、次の手順を実行します。

    [Image: ユーザーを作成するためのオプションがある [Bamboo user accounts](Bamboo ユーザー アカウント) を示すスクリーンショット。]

    ある。 **[Create users in Bamboo's internal Directory if needed](必要に応じて Bamboo の内部ディレクトリにユーザーを作成する)** を選択して、ユーザー グループの適切な名前を入力します (グループはコンマで区切られた複数の番号になる場合があります)。

    b。 [**次へ**] を選択します。
16. **完了** を選択します。
17. **[Microsoft Entra ID の既知のドメイン]** セクションで次の手順を実行します:

    ある。 ページの左側のパネルにある **[Known domains](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/既知のドメイン)** を選択します。

    b。 **[Known domains](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/既知のドメイン)** ボックスにドメイン名を入力します。

    c. **保存** を選択します。

#### Kantega SSO for Bamboo のテスト ユーザーの作成

Microsoft Entra ユーザーが Bamboo にサインインできるようにするには、Bamboo にプロビジョニングする必要があります。 Kantega SSO for Bamboo の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. 管理者として、オンプレミス サーバーの Bamboo にサインインします。
2. 歯車アイコンをポイントし、[ **ユーザー管理**] を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) メニューの [User Management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー管理) が選択されているスクリーンショット。]
3. **ユーザー**を選択します。 **[ユーザーの追加]** セクションで、次の手順を実行します。

    [Image: 以下の手順を実行できる [Add user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ペインを示すスクリーンショット。]

    ある。 **[Username](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー名)** ボックスに、ユーザーの電子メール (Brittasimon@contoso.com など) を入力します。

    b。 **[Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード)** ボックスに、ユーザーのパスワードを入力します。

    c. **[Confirm Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワードの確認)** ボックスに、ユーザーのパスワードを再入力します。

    d. **[Full Name](フル ネーム)** ボックスに、ユーザーの氏名 (Britta Simon など) を入力します。

    え **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メール)** ボックスに、ユーザーのメール アドレス (Brittasimon@contoso.com など) を入力します。

    f. **保存** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Kantega SSO for Bamboo Sign on URL にリダイレクトされます。
- Kantega SSO for Bamboo のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Kantega SSO for Bamboo に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Kantega SSO for Bamboo タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Kantega SSO for Bamboo に自動的にサインインされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kantegassoforbitbucket-tutorial"} -->
## Microsoft Entra ID で Kantega SSO for Bitbucket for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kantegassoforbitbucket-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kantega SSO for Bitbucket の間でシングル サインオンを構成する方法について説明します。

この記事では、Kantega SSO for Bitbucket と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Kantega SSO for Bitbucket を統合すると、次のことができます。

- Kantega SSO for Bitbucket にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Kantega SSO for Bitbucket に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kantega SSO for Bitbucket でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Kantega SSO for Bitbucket では、**SP および IDP** Initiated SSO がサポートされます

### ギャラリーからの Kantega SSO for Bitbucket の追加

Microsoft Entra ID への Kantega SSO for Bitbucket の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Kantega SSO for Bitbucket を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [**ギャラリーから追加する]** セクションで、検索ボックスに「**Kantega SSO for Bitbucket**」と入力します。
4. 結果パネルから **[Kantega SSO for Bitbucket]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kantega SSO for Bitbucket 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Kantega SSO for Bitbucket に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Kantega SSO for Bitbucket の関連ユーザーとの間にリンク関係を確立する必要があります。

Kantega SSO for Bitbucket に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kantega SSO for Bitbucket の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Kantega SSO for Bitbucket テスト ユーザーの作成** - Microsoft Entra のユーザー表現にリンクされている、Bitbucket の Kantega SSO で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Kantega SSO for Bitbucket**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<uniqueid>/login` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<uniqueid>/login` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<uniqueid>/login` という形式で URL を入力します。

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値は、記事の後半で説明する Bitbucket プラグインの構成中に受け取られます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[Kantega SSO for Bitbucket のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kantega SSO for Bitbucket の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Bitbucket 管理者ポータルに管理者としてサインインします。
2. 歯車を選択し、[ **新しいアドオンの検索**] を選択します。

    [Image: [Find new add-ons](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいアドオンの検出) が選択された [BitBucket Administration](BitBucket の管理) を示すスクリーンショット。]
3. **Kantega SSO for Bitbucket SAML & Kerberos** を検索し、[**インストール**] ボタンを選択して新しい SAML プラグインをインストールします。

    [Image: インストールするためのオプションがある [Kantega SSO for Bitbucket SAML & Kerberos] を示すスクリーンショット。]
4. プラグインのインストールが開始されます。

    [Image: インストールの進行状況を示すスクリーンショット。]
5. インストールが完了したら、 **を選択して**を閉じます。

    [Image: [Close](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/閉じる) ボタンを示すスクリーンショット。]
6. **[Kantega SSO for Bitbucket SAML Kerberos]** ページで、**[Manage]** を選択します。
7. [ **構成] を** 選択して新しいプラグインを構成します。

    [Image: [Configure](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) が選択された [User-installed add-ons](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーがインストールしたアドオン) を示すスクリーンショット。]
8. **[SAML]** セクションで、**[ID プロバイダーの追加]** ドロップダウンから **[Microsoft Entra ID]** を選択します。
9. **[Kantega シングル サインオン]** ページで、**[Basic]** を選択します。
10. **[App properties](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリのプロパティ)** セクションで、次の手順を実行します。

    [Image: この手順の情報を指定できる [App properties](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリのプロパティ) セクションを示すスクリーンショット。]

    ある。 **[アプリケーション ID/URI]** の値をコピーして、Azure portal の **[基本的な SAML 構成]** セクションで**識別子、応答 URL、サインオン URL** として使用します。

    b。 [**次へ**] を選択します。
11. **[Metadata import]** セクションで、**[Metadata file on my computer]** を選択します。
12. **[Browse file]** を選択して、以前にダウンロードしたメタデータ ファイルをアップロードし、**[Next]** を選択します。
13. **[Name and SSO location](名前と SSO の場所)** セクションで、次の手順を実行します。

    [Image: Microsoft Entra ID が ID プロバイダー名である [Name and SSO location] (名前と SSO の場所) を示すスクリーンショット。]

    ある。 **[ID プロバイダー名]** テキストボックスに、(Microsoft Entra ID などの) ID プロバイダーの名前を追加します。

    b。 [**次へ**] を選択します。
14. 署名証明書を確認し、[ **次へ**] を選択します。

    [Image: 署名の確認を示すスクリーンショット。]
15. **[Bitbucket user accounts](Bitbucket ユーザー アカウント)** セクションで、次の手順を実行します。

    [Image: ユーザーを作成するためのオプションがある [BitBucket user accounts](BitBucket ユーザー アカウント) を示すスクリーンショット。]

    ある。 **[Create users in Bitbucket's internal Directory if needed](必要に応じて Bitbucket の内部ディレクトリにユーザーを作成する)** を選択して、ユーザー グループの適切な名前を入力します (グループはコンマで区切られた複数の番号になる場合があります)。

    b。 [**次へ**] を選択します。
16. **完了** を選択します。
17. **[Microsoft Entra ID の既知のドメイン]** セクションで次の手順を実行します:

    ある。 ページの左側のパネルにある **[Known domains](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/既知のドメイン)** を選択します。

    b。 **[Known domains](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/既知のドメイン)** ボックスにドメイン名を入力します。

    c. **保存** を選択します。

#### Kantega SSO for Bitbucket のテスト ユーザーの作成

Microsoft Entra ユーザーが Bitbucket にサインインできるようにするには、ユーザーを Bitbucket にプロビジョニングする必要があります。 Kantega SSO for Bitbucket の場合、プロビジョニング作業は手動で行うことになります。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. Bitbucket 企業サイトに管理者としてサインインします。
2. [設定] アイコンを選択します。

    [Image: 設定アイコンを示すスクリーンショット。]
3. [ **管理** ] タブ セクションで、[ユーザー] を選択 **します**。

    [Image: [BitBucket Administration with Users] が選択されている状態を示すスクリーンショット。]
4. **[Create user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成)** を選択します。

    [Image: [Create user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成) が選択された [BitBucket Administration](BitBucket の管理) を示すスクリーンショット。]
5. **[Create User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成)** ダイアログ ページで、以下の手順を実行します。

    [Image: 以下の手順を実行できる [Create user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成) ダイアログ ボックスを示すスクリーンショット。]

    ある。 **[Username](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー名)** ボックスに、ユーザーの電子メール (Brittasimon@contoso.com など) を入力します。

    b。 **[Full Name](フル ネーム)** ボックスに、ユーザーの氏名 (Britta Simon など) を入力します。

    c. **[Email address](メール アドレス)** ボックスに、ユーザーのメール アドレス (Brittasimon@contoso.com など) を入力します。

    d. **[Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード)** ボックスに、ユーザーのパスワードを入力します。

    え **[Confirm Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワードの確認)** ボックスに、ユーザーのパスワードを再入力します。

    f. **[Create user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成)** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Kantega SSO for Bitbucket のサインオン URL にリダイレクトされます。
- Kantega SSO for Bitbucket のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Kantega SSO for Bitbucket に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Kantega SSO for Bitbucket タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Kantega SSO for Bitbucket に自動的にサインインされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kantegassoforconfluence-tutorial"} -->
## Microsoft Entra ID 用のシングルサインオンを設定するために Kantega SSO for Confluence を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kantegassoforconfluence-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kantega SSO for Confluence の間でシングル サインオンを構成する方法について説明します。

この記事では、Kantega SSO for Confluence と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Kantega SSO for Confluence を統合すると、次のことができます。

- Kantega SSO for Confluence にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Kantega SSO for Confluence に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kantega SSO for Confluence でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Kantega SSO for Confluence では **SP initiated SSO と IDP initiated SSO** がサポートされています。

### ギャラリーから Kantega SSO for Confluence を追加する

Microsoft Entra ID への Kantega SSO for Confluence の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Kantega SSO for Confluence を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Kantega SSO for Confluence」**と入力します。
4. 結果のパネルから **Kantega SSO for Confluence** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kantega SSO for Confluence 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Kantega SSO for Confluence に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Kantega SSO for Confluence の関連ユーザーとの間にリンク関係を確立する必要があります。

Kantega SSO for Confluence に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kantega SSO for Confluence SSO の**構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Kantega SSO for Confluence テストユーザーを作成** - Microsoft Entra のユーザーである B.Simon に対応する Kantega SSO for Confluence のユーザーを作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Kantega SSO for Confluence**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<uniqueid>/login`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<uniqueid>/login`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<uniqueid>/login`

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値は Confluence プラグインの構成中に受け取られます。これについては、この記事の後半で説明します。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Kantega SSO for Confluence のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kantega SSO for Confluence SSO を構成する

1. 別の Web ブラウザー ウィンドウで、 **Confluence 管理ポータル** に管理者としてサインインします。
2. 歯車アイコンにマウスを合わせ、**アドオン**を選択します。

    [Image: [歯車] メニュー アイコンと [アドオン] が選択されていることを示すスクリーンショット。]
3. **ATLASSIAN MARKETPLACE タブで**、[**新しいアドオンの検索**] を選択します。

    [Image: [新しいアドオンの検索] が選択されている [ATLASSIAN MARKETPLACE] タブを示すスクリーンショット。]
4. **Kantega SSO for Confluence SAML Kerberos** を検索し、[**インストール**] ボタンを選択して新しい SAML プラグインをインストールします。

    [Image: 検索ボックスに [Kantega S S O for Confluence S A M L Kerberos] が表示され、[インストール] ボタンが選択されている [新しいアドオンの検索] ページを示すスクリーンショット。]
5. プラグインのインストールが開始されます。

    [Image: プラグインの [インストール中] 画面を示すスクリーンショット。]
6. インストールが完了したら、 [ **閉じる]** を選択します。

    [Image: [閉じる] アクションが選択された [インストール済みで準備完了] 画面を示すスクリーンショット。]
7. **Kantega SSO for Confluence SAML Kerberos** ページで、[管理] を選択**します**。
8. [ **構成] を** 選択して新しいプラグインを構成します。

    [Image: [構成] ボタンが選択された [Kantega Single Sign-on with Kerberos and S A M L](Kerberos と S A M L での Kantega シングル サインオン) ページを示すスクリーンショット。]
9. この新しいプラグインは、[ **ユーザーとセキュリティ** ] タブにも表示されます。

    [Image: [Kantega Single Sign-on](Kantega シングル サインオン) アクションが選択されている [USERS & SECURITY](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーとセキュリティ) タブを示すスクリーンショット。]
10. **[SAML**] セクションで、[**ID プロバイダーの追加**] ドロップダウンから **Microsoft Entra ID** を選択します。
11. **[Kantega Single Sign-on]\(Kantega シングル サインオン**\) ページで、[Basic] を選択**します**。
12. [ **アプリのプロパティ** ] セクションで、次の手順を実行します。

    [Image: [App I D U R L] フィールドと [コピー] ボタンが強調表示され、[次へ] ボタンが選択されている [アプリのプロパティ] セクションを示すスクリーンショット。]

    ある。 Azure portal の [**基本的な SAML 構成]** セクションで、**アプリ ID URI** の値をコピー**し、識別子、応答 URL、Sign-On URL** として使用します。

    b。 [ **次へ**] を選択します。
13. [ **メタデータのインポート** ] セクション **で、コンピューター上の [メタデータ ファイル**] を選択します。
14. [ **ファイルの参照** ] を選択して、以前にダウンロードしたメタデータ ファイルをアップロードし、[ **次へ**] を選択します。
15. [ **名前と SSO の場所** ] セクションで、次の手順を実行します。

    [Image: [ID プロバイダー名] テキスト ボックスが強調表示され、[次へ] ボタンが選択されている [Name and S S O location](名前と S S O の場所) を示すスクリーンショット。]

    ある。 ID プロバイダー名テキスト ボックス (Microsoft Entra ID など) に **ID プロバイダーの名前** を追加します。

    b。 [ **次へ**] を選択します。
16. 署名証明書を確認し、[ **次へ**] を選択します。

    [Image: [次へ] ボタンが選択されている [署名の検証] セクションを示すスクリーンショット。]
17. [ **Confluence ユーザー アカウント** ] セクションで、次の手順を実行します。

    [Image: [必要に応じて Confluence の内部ディレクトリにユーザーを作成する] オプションと [次へ] ボタンが選択されている [Confluence ユーザー アカウント] セクションを示すスクリーンショット。]

    ある。 必要に応 **じて、[Confluence の内部ディレクトリにユーザーを作成** する] を選択し、ユーザーのグループの適切な名前を入力します (複数のグループをコンマで区切って指定できます)。

    b。 [ **次へ**] を選択します。
18. **[完了] を選択します**。
19. [ **Microsoft Entra ID の既知のドメイン** ] セクションで、次の手順を実行します。

    ある。 ページ **の左側の** パネルから [既知のドメイン] を選択します。

    b。 [既知のドメイン] ボックスに **ドメイン名を** 入力します。

    c. **[保存] を選択します**。

#### Kantega SSO for Confluence のテスト ユーザーの作成

Microsoft Entra ユーザーが Confluence にサインインできるようにするには、そのユーザーを Confluence にプロビジョニングする必要があります。 Kantega SSO for Confluence の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. Kantega SSO for Confluence 企業サイトに管理者としてサインインします。
2. 歯車アイコンをポイントし、[ **ユーザー管理**] を選択します。

    [Image: [歯車] アイコンと [ユーザー管理] が選択されていることを示すスクリーンショット。]
3. [ユーザー] セクションで、[ **ユーザーの追加** ] タブを選択します。[ **ユーザーの追加** ] ダイアログ ページで、次の手順を実行します。

    [Image: 従業員の追加]

    ある。 [Username]\( **ユーザー名** \) テキストボックスに、ユーザーの電子メール ( Brittasimon@contoso.comなど) を入力します。

    b。 [ **Full Name]\(フル ネーム\)** ボックスに、Britta Simon のようなユーザーのフル ネームを入力します。

    c. [ **電子メール** ] ボックスに、ユーザーのメール アドレス ( Brittasimon@contoso.comなど) を入力します。

    d. [ **パスワード** ] ボックスに、ユーザーのパスワードを入力します。

    え [ **パスワードの確認]** を選択して、パスワードを再入力します。

    f. [ **追加] ボタンを** 選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Kantega SSO for Confluence のサインオン URL にリダイレクトされます。
- Kantega SSO for Confluence のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Kantega SSO for Confluence に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Kantega SSO for Confluence タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Kantega SSO for Confluence に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kantegassoforfisheyecrucible-tutorial"} -->
## Microsoft Entra ID とシングルサインオンするための Kantega SSO for FishEye/Crucible の構成 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kantegassoforfisheyecrucible-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kantega SSO for FishEye/Crucible の間でシングル サインオンを構成する方法について説明します。

この記事では、Kantega SSO for FishEye/Crucible と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Kantega SSO for FishEye/Crucible を統合すると、次のことができます。

- Kantega SSO for FishEye/Crucible にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Kantega SSO for FishEye/Crucible に自動的にサインインできるように設定する。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kantega SSO for FishEye/Crucible でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Kantega SSO for FishEye/Crucible では **SP および IDP** のどちらかによる SSO がサポートされます。

### ギャラリーから Kantega SSO for FishEye/Crucible を追加する

Microsoft Entra ID への Kantega SSO for FishEye/Crucible の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Kantega SSO for FishEye/Crucible を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Kantega SSO for FishEye/Crucible**」と入力します。
4. 結果のパネルから **Kantega SSO for FishEye/Crucible** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kantega SSO for FishEye/Crucible 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Kantega SSO for FishEye/Crucible に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーとそれに対応する Kantega SSO for FishEye/Crucible ユーザーをリンクする必要があります。

Kantega SSO for FishEye/Crucible 用の Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kantega SSO for FishEye/Crucible SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Kantega SSO for FishEye/Crucible のテスト ユーザーの作成** - Kantega SSO for FishEye/Crucible で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Kantega SSO for FishEye/Crucible]**&gt;**[シングルサインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<uniqueid>/login`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<uniqueid>/login`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<uniqueid>/login`

    Note

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値は、記事の後半で説明する FishEye/Crucible プラグインの構成中に受け取られます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Kantega SSO for FishEye/Crucible の設定** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kantega SSO for FishEye/Crucible SSO を構成する

1. 別の Web ブラウザー ウィンドウで、オンプレミス サーバーの FishEye/Crucible に管理者としてサインインします。
2. 歯車アイコンをポイントし、**アドオン**を選択する。

    [Image: [歯車] アイコンと [アドオン] が選択されていることを示すスクリーンショット。]
3. [システム設定] セクションで、[ **新しいアドオンの検索**] を選択します。

    [Image: [新しいアドオンの検索] が選択されている [システム設定] セクションを示すスクリーンショット。]
4. **Kantega SSO for Crucible** を検索し、[**インストール**] ボタンを選択して新しい SAML プラグインをインストールします。

    [Image: 検索ボックスに]
5. プラグインのインストールが開始されます。

    [Image: プラグインの [インストール中] ダイアログを示すスクリーンショット。]
6. インストールが完了したら、 [ **閉じる]** を選択します。

    [Image: [インストール済みで準備完了] ダイアログと [閉じる] ボタンが選択されていることを示すスクリーンショット。]
7. **Kantega SSO for Crucible SAML & Kerberos** ページで、[管理] を選択**します**。
8. [ **構成] を** 選択して新しいプラグインを構成します。

    [Image: [ユーザーがインストールしたアドオン] ページと [構成] ボタンが選択されていることを示すスクリーンショット。]
9. **[SAML]** セクションに移動します。 [**ID プロバイダーの追加]** ドロップダウンから **Microsoft Entra ID** を選択します。
10. **[Kantega Single Sign-on]\(Kantega シングル サインオン**\) ページで、[Basic] を選択**します**。
11. [ **アプリのプロパティ** ] セクションで、次の手順を実行します。

    [Image: [App I D U R I] テキスト ボックスと [コピー] ボタンが選択されている [アプリのプロパティ] セクションを示すスクリーンショット。]

    a. Azure portal の [**基本的な SAML 構成]** セクションで、**アプリ ID URI** の値をコピー**し、識別子、応答 URL、Sign-On URL** として使用します。

    b。 [ **次へ**] を選択します。
12. [ **メタデータのインポート** ] セクション **で、コンピューター上の [メタデータ ファイル**] を選択します。
13. [ **ファイルの参照** ] を選択して、以前にダウンロードしたメタデータ ファイルをアップロードし、[ **次へ**] を選択します。
14. [ **名前と SSO の場所** ] セクションで、次の手順を実行します。

    [Image: [ID プロバイダー名] テキスト ボックスが強調表示され、[次へ] ボタンが選択されている [Name and S S O location](名前と S S O の場所) を示すスクリーンショット。]

    a. ID プロバイダー名テキスト ボックス (Microsoft Entra ID など) に **ID プロバイダーの名前** を追加します。

    b。 [ **次へ**] を選択します。
15. 署名証明書を確認し、[ **次へ**] を選択します。

    [Image: [Signature verification](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/署名の検証) セクションの情報と[次へ]ボタンが選択されていることを示すスクリーンショット。]
16. [ **FishEye ユーザー アカウント** ] セクションで、次の手順を実行します。

    [Image: [必要に応じて FishEye の内部ディレクトリにユーザーを作成する] オプションと [次へ] ボタンが選択されている [FishEye ユーザー アカウント] セクションを示すスクリーンショット。]

    a. 必要に応 **じて、[FishEye の内部ディレクトリにユーザーを作成** する] を選択し、ユーザーのグループの適切な名前を入力します (複数のグループをコンマで区切って指定できます)。

    b。 [ **次へ**] を選択します。
17. **[完了] を選択します**。
18. [ **Microsoft Entra ID の既知のドメイン** ] セクションで、次の手順を実行します。

    a. ページ **の左側の** パネルから [既知のドメイン] を選択します。

    b。 [既知のドメイン] ボックスに **ドメイン名を** 入力します。

    c. **[保存] を選択します**。

#### Kantega SSO for FishEye/Crucible のテスト ユーザーの作成

Microsoft Entra ユーザーが FishEye/Crucible にサインインするには、そのユーザーを FishEye/Crucible にプロビジョニングする必要があります。 Kantega SSO for FishEye/Crucible では、手動でプロビジョニングします。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. 管理者として、オンプレミス サーバーの Crucible にサインインします。
2. 歯車アイコンにカーソルを合わせ、[ユーザー] を選択 **します**。

    [Image: [歯車] アイコンが選択され、ドロップダウンから [ユーザー] が選択されていることを示すスクリーンショット。]
3. [ **ユーザー** ] タブ セクションで、[ **ユーザーの追加]** を選択します。

    [Image: [ユーザーの追加] ボタンが選択されている [ユーザー] セクションを示すスクリーンショット。]
4. [ **新しいユーザーの追加** ] ダイアログ ページで、次の手順を実行します。

    [Image: 従業員の追加]

    a. [Username]\( **ユーザー名** \) テキストボックスに、ユーザーの電子メール ( Brittasimon@contoso.comなど) を入力します。

    b。 [ **表示名]** ボックスに、Britta Simon などのユーザーの表示名を入力します。

    c. [ **電子メール アドレス** ] ボックスに、ユーザーのメール アドレス ( Brittasimon@contoso.comなど) を入力します。

    d. [ **パスワード** ] ボックスに、ユーザーのパスワードを入力します。

    e. [ **パスワードの確認** ] ボックスに、ユーザーのパスワードを再入力します。

    f. **追加**を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Kantega SSO for FishEye/Crucible のサインオン URL にリダイレクトされます。
- Kantega SSO for FishEye/Crucibe のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Kantega SSO for FishEye/Crucible に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Kantega SSO for FishEye/Crucible タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Kantega SSO for FishEye/Crucible に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kantegassoforjira-tutorial"} -->
## Kantego SSO を使用して Microsoft Entra ID で Jira for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kantegassoforjira-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Jira 間で Kantega SSO を使用してシングル サインオンを構成する方法について説明します。

この記事では、Jira で Microsoft Entra ユーザーのシングル サインオンを構成する手順について説明します。 これを実現するために、Kantega SSO アプリを使用しています。 この構成を使用すると、次のことが可能になります。

- Microsoft Entra ID から Jira へのアクセス権を持つユーザーを制御する。
- アクティブな Microsoft Entra セッションがある場合は、自動的に Jira にサインインします。
- 1 つの中央の場所でアカウントを管理します。

詳細については、 [Kantega の公式 SSO ドキュメントを参照してください](https://kantega-sso.atlassian.net/wiki/spaces/KSE/pages/895844483/Azure+AD)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Jira Data Center インスタンス。 [無料で試](https://www.atlassian.com/software/jira/download/data-center)すことができます。
- Atlassian Marketplace からの Jira 用 Kantega SSO アプリ。 [無料で試](https://marketplace.atlassian.com/apps/1211923/k-sso-saml-kerberos-openid-oidc-oauth-for-jira?tab=overview&amp;hosting=datacenter)すことができます。

### シナリオの説明

この記事では、Jira テスト環境で Microsoft Entra ID でシングル サインオンを構成し、テストします。

- Kantega SSO では、 **SAML と OIDC が**サポートされます。
- Kantega SSO では、**SP および IDP** による SSO がサポートされます。
- Kantega SSO では、自動化されたユーザー プロビジョニングとプロビジョニング解除 (推奨) がサポートされます。
- Kantega SSO では、Just-In-Time ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Kantega SSO for JIRA の追加

Microsoft Entra ID への Kantega SSO for JIRA の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Kantega SSO for JIRA を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Kantega SSO for JIRA**」と入力します。
4. 結果パネルから **Kantega SSO for JIRA** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kantega SSO for JIRA 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Kantega SSO for JIRA に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Kantega SSO for JIRA の関連ユーザーとの間にリンク関係を確立する必要があります。

Kantega SSO for JIRA に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kantega SSO for JIRA SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Kantega SSO for JIRA テストユーザーを作成する** - Microsoft Entra 上のユーザー表現にリンクするため、Kantega SSO for JIRA で B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;、**Kantega SSO for JIRA**&gt;、**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<UNIQUE_ID>/login`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<UNIQUE_ID>/login`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server-base-url>/plugins/servlet/no.kantega.saml/sp/<UNIQUE_ID>/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値は Jira プラグインの構成中に受け取ります。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Kantega SSO for JIRA のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kantega SSO for JIRA SSO の構成

Kantega SSO では、SAML または OIDC を SSO プロトコルとして使用するように構成できます。 次のいずれかのガイドを選択します。

- [SAML を使用した Microsoft Entra ID の Kantega SSO セットアップ ガイド](https://kantega-sso.atlassian.net/wiki/spaces/KSE/pages/896696394/Azure+AD+SAML)
- [OIDC を使用した Microsoft Entra ID の Kantega SSO セットアップ ガイド](https://kantega-sso.atlassian.net/wiki/spaces/KSE/pages/896598077/Azure+AD+OIDC)

#### Kantega SSO for JIRA のテスト ユーザーの作成

Kantega SSO for JIRA への Microsoft Entra ユーザーのサインインを有効にするには、ユーザーをプロビジョニングする必要があります。 このアプリケーションでは、Just-In-Time ユーザー プロビジョニングと、SCIM を使用した自動ユーザー プロビジョニングがサポートされていますが、ユーザーを手動で設定することもできます。 [さまざまなプロビジョニング オプション](https://kantega-sso.atlassian.net/wiki/spaces/KSE/pages/1769694/User+provisioning)の詳細をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択します。 このオプションは、ログイン フローを開始できる Kantega SSO for JIRA のサインオン URL にリダイレクトします。
- Kantega SSO for JIRA のサインオン URL に直接移動し、ログイン フローを開始します。

##### IDP 起動しました。

- Azure portal で [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Kantega SSO for JIRA に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Kantega SSO for JIRA タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。 IDP モードで構成されている場合は、SSO を設定した Kantega SSO for JIRA に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/karlsgate-identity-exchange-kie-sso-add-on-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Karlsgate Identity Exchange (KIE) SSO アドオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/karlsgate-identity-exchange-kie-sso-add-on-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-04-15
- Summary: Microsoft Entra ID と Karlsgate Identity Exchange (KIE) SSO アドオンの間でシングル サインオンを構成する方法について説明します。

この記事では、Karlsgate Identity Exchange (KIE) SSO アドオンと Microsoft Entra ID を統合する方法について説明します。 Karlsgate は、保存中、転送中、使用中のデータを保護するためのプライバシー強化テクノロジを提供します。 Karlsgate のゼロトラスト アプローチを使用すると、機密データの保護を維持しながら分析データのフリー フローを実現できます。 Karlsgate Identity Exchange (KIE) SSO アドオンと Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で、Karlsgate Identity Exchange (KIE) SSO アドオンにアクセスできるユーザーを制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Karlsgate Identity Exchange (KIE) SSO アドオンに自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

テスト環境で Karlsgate Identity Exchange (KIE) SSO アドオン向けの Microsoft Entra シングル サインオンを構成してテストします。 Karlsgate Identity Exchange (KIE) SSO アドオンでは、**SP** および **IDP** によって開始されるシングル サインオンがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### 前提条件

Microsoft Entra ID と Karlsgate Identity Exchange (KIE) SSO アドオンを統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Karlsgate Identity Exchange (KIE) SSO アドオンのシングル サインオン (SSO) の対象であるアカウントが既に存在していること。
- Karlsgate Identity Exchange (KIE) SSO アドオン アカウントで 1 人以上のユーザーが作成されていること。

注

お使いの Karlsgate Identity Exchange (KIE) SSO アドオン アカウントをシングル サインオン (SSO) アクセスの対象にするには、KIE アカウントに SSO の対象となるサブスクリプションが必要です。 ご不明な点がある場合は、[Karlsgate Identity Exchange (KIE) SSO アドオン サポート チーム](mailto:help@karlsgate.com)にお問い合わせください。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Karlsgate Identity Exchange (KIE) SSO アドオン アプリケーションを追加する必要があります。 アプリケーションに割り当て、シングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Karlsgate Identity Exchange (KIE) SSO アドオンを追加する

Karlsgate Identity Exchange (KIE) SSO アドオンでシングル サインオンを構成するには、Microsoft Entra アプリケーション ギャラリーから Karlsgate Identity Exchange (KIE) SSO アドオンを追加します。 ギャラリーからアプリケーションを追加する方法の詳細については、[クイック スタート: ギャラリーからのアプリケーションの追加](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)に関する記事を参照してください。

#### Microsoft Entra テスト ユーザーを作成して割り当てる

「[ユーザー アカウントを作成して割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)」の記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができます。 このウィザードには、シングル サインオン構成ウィンドウへのリンクも表示されます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO の構成

Microsoft Entra シングル サインオンを有効にするには、次の手順を行います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Karlsgate Identity Exchange (KIE) SSO アドオン**&gt;**Single サインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML でシングル サインオンをセットアップします]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **SP** Initiated モードでアプリケーションを構成する場合は、続けて次の手順を実行します。

    **[サインオン URL]** テキストボックスに、URL として「`https://portal.karlsgate.com/Identity/Account/Login`」と入力します。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### Karlsgate Identity Exchange (KIE) SSO アドオン SSO を構成する

**Karlsgate Identity Exchange (KIE) SSO アドオン**側でシングル サインオンを構成するには、次の情報を [Karlsgate Identity Exchange (KIE) SSO アドオン サポート チーム](mailto:help@karlsgate.com)に送信する必要があります。

1. KIE アカウントの構成済みで空白ではない **パブリック ネーム プレート** (KIE アカウントの二次確認として)。この値は `https://portal.karlsgate.com/Profile/Edit` で入手できます。
2. 構成済みの**識別子 (エンティティ ID)** (構成済みの値を確認するため)。
3. 構成済みの **アプリのフェデレーション メタデータ URL**。
4. 、`northwind.com`、`de.contoso.com`など、Karlsgate Identity Exchange (KIE) SSO アドオンにアクセスしているユーザーの 1 つ (または複数) の`fr.contoso.com` (最小 1) の一覧。 (下の注記を参照してください。)

    注

    多くの組織では、ユーザー用に 1 つのメール ドメインが構成されています。 たとえば、架空の Northwind 社では、ユーザー "jane.smith@northwind.com" のメール ドメインは "northwind.com" です。 一部の組織では、ユーザー用に複数 (2 つ以上) のメール ドメインが構成されています。 たとえば、架空の Contoso 社では、ユーザー "erika.mustermann@de.contoso.com" のメール ドメインは "de.contoso.com" であり、ユーザー "jean.dupont@fr.contoso.com" のメール ドメインは "fr.contoso.com" です。
5. Karlsgate Identity Exchange (KIE) SSO アドオン サポート チームは、これらの設定を使用して、SAML SSO アクセス用の Karlsgate Identity Exchange (KIE) SSO アドオン アプリケーションを構成します。

注

SAML SSO アクセスを構成するには、SSO の対象となるサブスクリプションを持つ既存の KIE アカウントが必要です。 SSO アクセスの場合、KIE ユーザーのメール アドレスが自分の Microsoft Entra ID メール アドレスと一致している必要があります。

ご不明な点がある場合は、[Karlsgate Identity Exchange (KIE) SSO アドオン サポート チーム](mailto:help@karlsgate.com)にお問い合わせください。

#### Karlsgate Identity Exchange (KIE) SSO アドオンのテスト ユーザーを作成する

[Karlsgate Identity Exchange (KIE) SSO アドオン サポート チーム](mailto:help@karlsgate.com)と連携して、KIE アカウントを作成し、ユーザーを KIE アカウントに追加します。

注

SSO アクセスの場合、KIE ユーザーのメール アドレスが自分の Microsoft Entra ID メール アドレスと一致している必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

1. [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Karlsgate Identity Exchange (KIE) SSO アドオン URL にリダイレクトされます。
2. Karlsgate Identity Exchange (KIE) SSO アドオンのサインオン URL に直接移動して、そこからログイン フローを開始します。

##### IDP Initiated:

1. [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Karlsgate Identity Exchange (KIE) SSO アドオンに自動的にサインインします。
2. また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Karlsgate Identity Exchange (KIE) SSO アドオン] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Karlsgate Identity Exchange (KIE) SSO アドオンに自動的にサインインされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/keepabl-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Keepabl を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/keepabl-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: Microsoft Entra IDから Keepabl にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Keepabl と Microsoft Entra ID の両方で実行して、自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成すると、Microsoft Entra IDは、Microsoft Entra プロビジョニング サービスを使用して、[Keepabl](https://keepabl.com/) にユーザーを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Keepabl でユーザーを作成します。
- アクセスが不要になった場合は、Keepabl のユーザーを削除します。
- Microsoft Entra IDと Keepabl の間でユーザー属性の同期を維持します。
- Keepabl に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/keepabl-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者アクセス許可を持つ Keepabl のユーザー アカウント。

### 手順 1: プロビジョニングの展開を計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとKeepablの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Keepabl を構成する

1. [Keepabl 管理ポータル](https://app.keepabl.com)にサインインし**、[組織&gt;アカウント設定]** に移動します。[**Single Sign-On (SSO)]** セクションが表示されます。
2. [ **ID プロバイダーの編集] ボタンを** 選択します。 [SSO セットアップ] ページが表示されます。プロバイダーとしてMicrosoft Azureを選択してから下にスクロールすると、**テナント URL** と **Secret Token** が表示されます。 これらの値は、Keepabl アプリケーションの [プロビジョニング] タブに入力されます。

    [Image: テナント URL とトークンの抽出のスクリーンショット。]

手記

ID プロバイダーまたは SSO を設定するには [、こちらを参照してください](https://keepabl.com/admin-guide-to-sso-keepabl)。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Keepabl を追加する

Microsoft Entra アプリケーション ギャラリーから Keepabl を追加して、Keepabl へのプロビジョニングの管理を開始します。 SSO 用に Keepabl を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Keepabl への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて Keepabl でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Keepabl の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Keepabl**] を選択します。

    [Image: アプリケーションの一覧の Keepabl リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Keepabl テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Keepabl に接続できることを確認します。 接続に失敗した場合は、Keepabl アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Keepabl に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Keepabl のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Keepabl API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理でサポートされます | Keepabl で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "仕事"].value | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/keepabl-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Keepabl を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/keepabl-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Keepabl 間のシングル サインオンを構成する方法について説明します。

この記事では、Keepabl と Microsoft Entra ID を統合する方法について説明します。 Keepabl を Microsoft Entra ID と統合すると、次のことが可能になります。

- Keepabl にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Keepabl に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Keepabl でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Keepabl では、**SP** イニシエータ SSO と **IDP** イニシエータ SSO がサポートされます。

### ギャラリーから Keepabl を追加する

Microsoft Entra ID への Keepabl の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Keepabl を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Keepabl**」と入力します。
4. 結果パネルから **Keepabl** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Keepabl に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Keepabl に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Keepabl の関連ユーザー間にリンク関係を確立する必要があります。

Keepabl に対して Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Keepabl SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Keepabl のテストユーザーを作成 - B.Simon の Microsoft Entra プロフィールにリンクされている対応するユーザーを Keepabl で作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Keepabl**&gt;**シングルサインオン**のページを閲覧してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `keepabl_microsoft_azure_<OrganizationID>`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://app.keepabl.com/users/saml/auth`
6. SP 開始モードでアプリケーションを構成する場合 **は、[追加の URL の設定] を** 選択し、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://app.keepabl.com/users/saml/sign_in?organization_id=<OrganizationID>` |
    | `https://keepabl.herokuapp.com/users/saml/sign_in?organization_id=<OrganizationID>` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Keepabl クライアント サポート チーム](mailto:support@keepabl.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Keepabl のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Keepabl SSO の構成

**Keepabl** 側でシングル サインオンを構成するには、**証明書 (Base64)** を [Keepabl サポート チーム](mailto:support@keepabl.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Keepabl のテスト ユーザーを作成する

このセクションでは、Keepabl で Britta Simon というユーザーを作成します。 [Keepabl サポート チーム](mailto:support@keepabl.com)と協力して、Keepabl プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Keepabl のサインオン URL にリダイレクトされます。
- Keepabl のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Keepabl に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Keepabl] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Keepabl に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/keeper-password-manager-digitalvault-provisioning-tutorial"} -->
## Keeper パスワード マネージャーとデジタル保管庫の構成を行い、Microsoft Entra ID を使用した自動ユーザー プロビジョニングを設定します。 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/keeper-password-manager-digitalvault-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-08
- Summary: Microsoft Entra IDを設定して、ユーザーアカウントをKeeper Password Manager & Digital Vaultに自動的にプロビジョニングおよびプロビジョニング解除する方法を学びます。

この記事の目的は、Microsoft Entra ID と Keeper Password Manager & Digital Vault において、Microsoft Entra ID を構成し、Keeper Password Manager & Digital Vault に対するユーザーやグループの自動プロビジョニングおよびプロビジョニング解除を行う手順を示すことです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Keeper Password Manager & Digital Vault テナント](https://keepersecurity.com/pricing.html?t=e)
- 管理者アクセス許可がある Keeper Password Manager & Digital Vault のユーザー アカウント

### ギャラリーから Keeper Password Manager & Digital Vault を追加する

Microsoft Entra IDを使用した自動ユーザープロビジョニングのためにKeeper Password Manager & Digital Vaultを構成する前に、Microsoft EntraアプリケーションギャラリーからKeeper Password Manager & Digital Vaultをあなたの管理対象SaaSアプリケーションのリストに追加する必要があります。

Microsoft Entra アプリケーションギャラリーから Keeper パスワードマネージャー & デジタルボールトを追加するには、次の手順を実行します:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Keeper Password Manager & Digital Vault**」と入力し、**[Keeper Password Manager & Digital Vault]** を選択します。
4. 結果のパネルから **[Keeper Password Manager & Digital Vault]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の Keeper Password Manager & Digital Vault]

### Keeper Password Manager & Digital Vault へのユーザーの割り当て

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Microsoft Entra IDのどのユーザーやグループがKeeper Password ManagerおよびDigital Vaultへのアクセスを必要とするか決定する必要があります。 決定したら、次の手順に従って、これらのユーザーやグループを Keeper Password Manager & Digital Vault に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### Keeper Password Manager & Digital Vault にユーザーを割り当てる際の重要なヒント

- 1 人のMicrosoft Entra ユーザーを Keeper パスワード マネージャーに割り当てることをお勧めします。Digital Vault を使用して、自動ユーザー プロビジョニング構成をテストします。 後でユーザーやグループを追加で割り当てられます。
- Keeper Password Manager & Digital Vault にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### Keeper Password Manager & Digital Vault への自動ユーザー プロビジョニングの構成

Microsoft Entra IDのユーザーおよび/またはグループの割り当てに基づき、Keeper Password Manager & Digital Vaultでユーザーとグループを作成、更新、無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順についてこのセクションで案内します。

ヒント

Keeper Password Manager と Digital Vault のシングル サインオンに関する記事で説明されている手順に従って、 [Keeper Password Manager と Digital Vault](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/keeperpasswordmanager-tutorial) に対して SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Keeper Password Manager & Digital Vault 用に自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Keeper Password Manager & Digital Vault]** を選択します。

    [Image: アプリケーションの一覧での Keeper Password Manager & Digital Vault リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Keeper Password Manager と Digital Vault テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Keeper Password Manager に接続できることを確認します。Digital Vault。 接続に失敗した場合は、Keeper Password Manager と Digital Vault アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。
7. [Keeper 管理コンソール](https://keepersecurity.com/console/#login)にサインインします。 [ **管理者]** を選択し、既存のノードを選択するか、新しいノードを作成します。 **[Provisioning](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロビジョニング)** タブに移動し、**[Add Method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メソッドの追加)** を選択します。

    [Image: Keeper 管理コンソール]

    **[SCIM (System for Cross-domain Identity Management)]** を選択します。

    [Image: Keeper における SCIM の追加]

    [ **プロビジョニング トークンの作成] を選択します**。

    [Image: Keeper でのエンドポイントの作成]

    **URL** および **Token** の値をコピーし、Microsoft Entra IDの **Tenant URL** および **Secret Token** に貼り付けます。 [ **保存] を** 選択して、Keeper のプロビジョニング設定を完了します。

    [Image: Keeper でのトークンの作成]
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. Microsoft Entra IDからKeeper Password Manager & Digital Vaultに同期されるユーザー属性をAttribute-Mappingセクションで確認してください。 **[Matching]\(照合\)** プロパティとして選択されている属性は、更新操作で Keeper Password Manager & Digital Vault のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Keeper Password Manager および Digital Vault API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Keeper ユーザーの属性]
13. Microsoft Entra IDからKeeper Password Manager & Digital Vaultに同期されるグループ属性をAttribute Mappingセクションで確認してください。 **[Matching]\(照合\)** プロパティとして選択されている属性は、更新操作で Keeper Password Manager & Digital Vault のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Keeper グループ属性]
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### コネクタの制限事項

- Keeper Password Manager & Digital Vault では、**電子メール**と**ユーザー名**のソース値が同じである必要があります。これは、どちらかの属性が更新されると、もう一方の値が変更されるためです。
- Keeper Password Manager と Digital Vault はユーザーの削除をサポートせず、無効にするだけです。 無効なユーザーは、Keeper 管理コンソール UI でロック済みとして表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/keeperpasswordmanager-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Keeper Password Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/keeperpasswordmanager-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-04
- Summary: Microsoft Entra ID と Keeper Password Manager の間のシングル サインオンを構成する方法について説明します。

この記事では、Keeper Password Manager と Microsoft Entra ID を統合する方法について説明します。 Keeper Password Manager と Microsoft Entra ID を統合すると、次のことができます。

- Keeper Password Manager にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Keeper Password Manager に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Keeper Password Manager は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- Keeper Password Manager でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Keeper Password Manager では、SP Initiated SSO がサポートされます。
- Keeper Password Manager では、[**自動化された**ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/keeper-password-manager-digitalvault-provisioning-tutorial) (推奨) がサポートされます。
- Keeper Password Manager では、Just In Time ユーザー プロビジョニングがサポートされます。

### ギャラリーから Keeper Password Manager を追加する

Microsoft Entra ID への Keeper Password Manager の統合を構成するには、ギャラリーからマネージド SaaS (サービスとしてのソフトウェア) アプリの一覧にアプリケーションを追加します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Keeper Password Manager**」と入力します。
4. 結果のパネルから **[Keeper Password Manager]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Keeper Password Manager 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Keeper Password Manager に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Keeper Password Manager の関連ユーザーとの間にリンク関係を確立する必要があります。

Keeper Password Manager に対して Microsoft Entra SSO を構成してテストするには:

1. ユーザーがこの機能を使用できるように Microsoft Entra SSO を構成します。

    1. Microsoft Entra テスト ユーザーを作成して、Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. Microsoft Entra テスト ユーザーを割り当てて、Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. Keeper Password Manager SSO の構成 - アプリケーション側で SSO 設定を構成します。

    1. Keeper Password Manager テスト ユーザーを作成して、Keeper Password Manager で Britta Simon に対応するユーザーを作成し、Microsoft Entra でのユーザー表現にリンクさせます。
3. SSO をテスト して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Keeper Password Manager** アプリケーション統合ページを参照して、[**管理**] セクションを見つけます。 **[シングル サインオン]** を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 鉛筆アイコンが強調表示された [SAML でシングル サインオンをセットアップします] のスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子 (エンティティ ID)]** に、次のいずれかのパターンを使用して URL を入力します。

    - クラウド SSO の場合: `https://keepersecurity.com/api/rest/sso/saml/<CLOUD_INSTANCE_ID>`
    - オンプレミス SSO の場合: `https://<KEEPER_FQDN>/sso-connect`

    b。 **[応答 URL]** に、次のいずれかのパターンを使用して URL を入力します。

    - クラウド SSO の場合: `https://keepersecurity.com/api/rest/sso/saml/sso/<CLOUD_INSTANCE_ID>`
    - オンプレミス SSO の場合: `https://<KEEPER_FQDN>/sso-connect/saml/sso`

    c. **[サインオン URL]** に、次のいずれかのパターンを使用して URL を入力します。

    - クラウド SSO の場合: `https://keepersecurity.com/api/rest/sso/ext_login/<CLOUD_INSTANCE_ID>`
    - オンプレミス SSO の場合: `https://<KEEPER_FQDN>/sso-connect/saml/login`

    d. **[サインアウト URL]** に、次のいずれかのパターンを使用して URL を入力します。

    - クラウド SSO の場合: `https://keepersecurity.com/api/rest/sso/saml/slo/<CLOUD_INSTANCE_ID>`
    - オンプレミスの SSO の構成はありません。

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Keeper Password Manager クライアント サポート チーム](https://keepersecurity.com/contact.html)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Keeper Password Manager アプリケーションでは、特定の形式の SAML アサーションを要求するため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: ユーザー属性と要求のスクリーンショット。]
7. さらに、Keeper Password Manager アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 それらを次の表に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | First | User.givenname |
    | Last (最後へ) | ユーザーの名字 |
    | Email | ユーザーのメールアドレス |
8. **[SAML によるシングル サインオンのセットアップ]** の **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択します。 これにより、要件に応じたオプションから**フェデレーション メタデータ XML** がダウンロードされ、コンピューターに保存されます。

    [Image: [ダウンロード] が強調表示された [SAML 署名証明書] のスクリーンショット。]
9. **[Keeper Password Manager のセットアップ]** で、要件に従って適切な URL をコピーします。

    [Image: URL が強調表示された [Keeper Password Manager のセットアップ] のスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Keeper Password Manager の SSO の構成

アプリの SSO を構成するには、[Keeper サポート ガイド](https://docs.keeper.io/sso-connect-cloud/identity-provider-setup/azure-o365-keeper)のガイドラインを参照してください。

#### Keeper Password Manager のテスト ユーザーの作成

Microsoft Entra ユーザーが Keeper Password Manager にサインインできるようにするには、ユーザーをプロビジョニングする必要があります。 このアプリケーションでは、Just-In-Time ユーザー プロビジョニングがサポートされているので、認証後にユーザーがアプリケーションに自動的に作成されます。 ユーザーを手動で設定する場合は、[Keeper サポート](https://keepersecurity.com/contact.html)にお問い合わせください。

注

Keeper Password Manager では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/keeper-password-manager-digitalvault-provisioning-tutorial)を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Keeper Password Manager のサインオン URL にリダイレクトされます。
- Keeper Password Manager のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Keeper Password Manager] タイルを選択すると、このオプションは Keeper Password Manager のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kemp-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Kemp LoadMaster Microsoft Entra 統合を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kemp-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-04
- Summary: Microsoft Entra ID と Kemp LoadMaster Microsoft Entra 統合の間のシングル サインオンを構成する方法について説明します。

この記事では、Kemp LoadMaster Microsoft Entra と Microsoft Entra ID の統合を統合する方法について説明します。 Kemp LoadMaster Microsoft Entra 統合と Microsoft Entra ID を統合すると、次のことができます。

- Kemp LoadMaster Microsoft Entra 統合にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って、Kemp LoadMaster Microsoft Entra 統合に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

Kemp LoadMaster は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Kemp LoadMaster Microsoft Entra 統合 でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Kemp LoadMaster Microsoft Entra 統合では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからの Kemp LoadMaster Microsoft Entra 統合の追加

Microsoft Entra ID への Kemp LoadMaster Microsoft Entra 統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Kemp LoadMaster Microsoft Entra 統合を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Kemp LoadMaster Microsoft Entra integration**」と入力します。
4. 結果パネルから **Kemp LoadMaster Microsoft Entra 統合** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kemp LoadMaster Microsoft Entra 統合用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Kemp LoadMaster Microsoft Entra 統合に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Kemp LoadMaster Microsoft Entra 統合の関連ユーザーとの間にリンク関係を確立する必要があります。

Kemp LoadMaster Microsoft Entra 統合に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成** する - ユーザーがこの機能を使用できるようにします。

    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kemp LoadMaster Microsoft Entra integration SSO** の構成 - アプリケーション側でシングル サインオン設定を構成します。
3. **Web サーバーの公開**

    1. **仮想サービスを作成する**
    2. **証明書とセキュリティ**
    3. **Kemp LoadMaster Microsoft Entra 統合 SAML プロファイル**
    4. **変更を確認する**
4. **Kerberos ベースの認証の構成**

    1. **Kemp LoadMaster Microsoft Entra 統合用の Kerberos 委任アカウントを作成する**
    2. **Kemp LoadMaster と Microsoft Entra の統合 KCD (Kerberos 委任アカウント)**
    3. **Kemp LoadMaster と Microsoft Entra の ESP 統合**
    4. **Kemp LoadMaster Microsoft Entra 統合テストユーザーの作成** - Kemp LoadMaster Microsoft Entra 統合の中で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 内の B.Simon の表現にリンクします。
5. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップを実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Kemp LoadMaster Microsoft Entra 統合]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://<KEMP-CUSTOMER-DOMAIN>.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://<KEMP-CUSTOMER-DOMAIN>.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Kemp LoadMaster Microsoft Entra 統合クライアント サポート チーム](mailto:support@kemp.ax) に問い合わせてください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** と **フェデレーション メタデータ XML** を検索し、[ **ダウンロード** ] を選択して証明書とフェデレーション メタデータ XML ファイルをダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Kemp LoadMaster Microsoft Entra 統合のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kemp LoadMaster Microsoft Entra 統合 の SSO を構成する

### Web サーバーを公開する

#### 仮想サーバーを作成する

1. Kemp LoadMaster Microsoft Entra 統合の LoadMaster Web UI で、&gt;[Virtual Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仮想サービス) &gt; [Add New](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新規追加) に移動します。
2. [新規追加] を選択します。
3. [Virtual Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仮想サービス) の [Parameters](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パラメーター) を指定します。

    [Image: ボックスの値の例を示す [仮想サービスのパラメーターを指定してください] ページを示すスクリーンショット。]

    a. Virtual Address (仮想アドレス)

    b。 港 / ポート

    c. Service Name (Optional) (サービス名 (省略可能))

    d. プロトコル
4. [Real Servers] セクションに移動します。
5. [新規追加] を選択します。
6. [Real Server] の [Parameters](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パラメーター) を指定します。

    [Image: ボックスの値の例を示す [実際のサーバーのパラメーターを指定してください] ページを示すスクリーンショット。]

    a. [Allow Remote Addresses](リモート アドレスを許可する) を選択します

    b。 [Real Server Address](Real Server のアドレス) を入力します

    c. 港 / ポート

    d. Forwarding method (転送方法)

    e. 重量

    f. Connection Limit (接続の制限)

    g. [この実サーバーの追加] を選択する

### 証明書とセキュリティ

#### Kemp LoadMaster Microsoft Entra 統合で証明書をインポートする

1. Kemp LoadMaster Microsoft Entra 統合の Web ポータルに移動して、&gt; [Certificates & Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書とセキュリティ) &gt; [SSL Certificates](SSL 証明書) の順で選択します。
2. [Manage Certificates](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書の管理) &gt; [Certificate Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書の構成) に移動します。
3. [証明書のインポート] を選択します。
4. 証明書を含むファイルの名前を指定します。 このファイルには秘密キーも保持できます。 ファイルに秘密キーが含まれていない場合は、秘密キーを含むファイルも指定する必要があります。 証明書は、.PEM または .PFX (IIS) のいずれかの形式を使用できます。
5. [証明書ファイル] で [ファイルの選択] を選択します。
6. キー ファイルを選択します (省略可能)。
7. [保存] を選択します。

#### SSL アクセラレーション

1. Kemp LoadMaster Web UI &gt; [Virtual Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仮想サービス) &gt; [View/Modify Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サービスの表示または変更) に移動します。
2. [操作] で [変更] を選択します。
3. SSL プロパティ (レイヤー 7 で動作) を選択します。

    [Image: [S S L Acceleration - Enabled] が選択され、証明書の例が選択されている [S S L のプロパティ] セクションを示すスクリーンショット。]

    a. SSL アクセラレーションで [有効] を選択します。

    b。 [使用可能な証明書] で、インポートした証明書を選択し、シンボル `>` 選択します。

    c. 必要な SSL 証明書が [割り当てられた証明書] に表示されたら、[ **証明書の設定**] を選択します。

    注

    [ **証明書の設定**] を選択していることを確認します。

### Kemp LoadMaster Microsoft Entra 統合 SAML プロファイル

#### IdP 証明書をインポートする

Kemp LoadMaster Microsoft Entra 統合の Web コンソールに移動します。

1. [証明書と機関] で [中間証明書] を選択します。

    [Image: [現在インストールされている中間証明書] セクションを示すスクリーンショット。証明書の例が選択されています。]

    a. [新しい中間証明書の追加] で [ファイルの選択] を選択します。

    b。 以前に Microsoft Entra エンタープライズ アプリケーションからダウンロードした証明書ファイルに移動します。

    c. [開く] を選択します。

    d. [Certificate Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書名) に [Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前) を指定します。

    e. [証明書の追加] を選択します。

#### 認証ポリシーの作成

[Virtual Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仮想サービス) の [Manage SSO](SSO の管理) にアクセスします。

[Image: [Manage S S O](S S O の管理) ページを示すスクリーンショット。]

a. 名前を指定した後、[新しいクライアント側構成の追加] で [追加] を選択します。

b。 [Authentication Protocol](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証プロトコル) で [SAML] を選択します。

c. [IdP Provisioning](IdP のプロビジョニング) で [MetaData File](メタデータ ファイル) を選択します。

d. [ファイルの選択] を選択します。

e. Azure portal から以前にダウンロードした XML に移動します。

f. [開く] を選択し、[IdP MetaData ファイルのインポート] を選択します。

g. [IdP Certificate](IdP 証明書) から中間証明書を選択します。

h. Azure portal で作成された ID と一致するように [SP Entity ID](SP エンティティ ID) を設定します。

一. [SP エンティティ ID の設定] を選択します。

#### 認証を設定する

Kemp LoadMaster Microsoft Entra 統合の Web コンソールで以下の操作を行います。

1. [仮想サービス] を選択します。
2. [サービスの表示/変更] を選択します。
3. [変更] を選択し、[ESP オプション] に移動します。

    [Image: [サービスの表示/変更] ページを示すスクリーンショット。[ESP オプション] セクションと [Real Servers] セクションが展開されています。]

    a. [ESP を有効にする] を選択します。

    b。 [Client Authentication Mode](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/クライアント認証モード) で [SAML] を選択します。

    c. [SSO Domain](SSO ドメイン) で以前に作成した [Client Side Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/クライアント側認証) を選択します。

    d. [許可された仮想ホスト] にホスト名を入力し、[許可された仮想ホストの設定] を選択します。

    e. 許可された仮想ディレクトリに 「/\* 」と入力し (アクセス要件に基づいて)、[許可されたディレクトリの設定] を選択します。

#### 変更を確認する

アプリケーションの URL にアクセスします。

以前の認証されていないアクセスではなく、テナントのログイン ページが表示されます。

[Image: テナント化された [サインイン] ページを示すスクリーンショット。]

### Kerberos ベースの認証の構成

#### Kemp LoadMaster Microsoft Entra 統合用の Kerberos 委任アカウントを作成する

1. ユーザー アカウントを作成します (この例では AppDelegation)。

    a. [属性エディター] タブを選択します。

    b。 servicePrincipalName に移動します。

    c. servicePrincipalName を選択し、[編集] を選択します。

    d. [値] フィールドに「http/kcduser」と入力し、[追加] を選択します。

    e. [適用] を選択して、[OK] を選択します。 ウィンドウを閉じてから再度開く必要があります (新しい [委任] タブを表示するため)。
2. [ユーザーのプロパティ] ウィンドウをもう一度開くと、[委任] タブを使用できるようになります。
3. [委任] タブを選択します。

    [Image: [委任] タブが選択されている [kcd user Properties](kcd ユーザーのプロパティ) ウィンドウを示すスクリーンショット。]

    a. [指定されたサービスへの委任でのみこのユーザーを信頼する] を選択します。

    b。 [任意の認証プロトコルを使う] を選択します。

    c. Real Server を追加し、サービスとして http を追加します。

    d. [展開済み] チェックボックスをオンにします。

    e. ホスト名と FQDN の両方を含むすべてのサーバーを表示できます。

    f. [OK] を選択します。

注

必要に応じて、[アプリケーション] と [Web サイト] の SPN を設定します。 アプリケーション プール ID が設定されている場合にアプリケーションにアクセスするには。 FQDN 名を使用して IIS アプリケーションにアクセスするには、Real Server コマンド プロンプトにアクセスし、必要なパラメーターを指定して SetSpn を入力します。 たとえば、「 `Setspn –S HTTP/sescoindc.sunehes.co.in suneshes\kdcuser` 」のように入力します。

#### Kemp LoadMaster Microsoft Entra 統合の KCD (Kerberos 委任アカウント)

Kemp LoadMaster Microsoft Entra 統合の Web コンソールに移動し、&gt;[Virtual Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仮想サービス) &gt; [Manage SSO](SSO の管理) の順に選択します。

[Image: [Manage S S O - Manage Domain](S S O の管理 - ドメインの管理) ページを示すスクリーンショット。]

a. [Server Side Single Sign On Configurations](サーバー側のシングル サインオンの構成) に移動します。

b。 [新しい Server-Side 構成の追加] に「名前」と入力し、[追加] を選択します。

c. [Authentication Protocol](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証プロトコル) で [Kerberos Constrained Delegation](Kerberos の制約付き委任) を選択します。

d. [Kerberos Realm](Kerberos 領域) にドメイン名を入力します。

e. [Kerberos 領域の設定] を選択します。

f. [Kerberos Key Distribution Center](Kerberos キー配布センター) にドメイン コントローラーの IP アドレスを入力します。

g. [Kerberos KDC の設定] を選択します。

h. [Kerberos Trusted User Name](Kerberos の信頼されたユーザー名)に KCD ユーザー名を入力します。

一. [KDC 信頼されたユーザー名の設定] を選択します。

j. [Kerberos Trusted User Password](Kerberos の信頼されたユーザー パスワード) にパスワードを入力します。

k. [KCD の信頼されたユーザー パスワードの設定] を選択します。

#### Kemp LoadMaster Microsoft Entra 統合 ESP

[Virtual Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仮想サービス) &gt; [View/Modify Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サービスの表示と変更) に移動します。

[Image: Kemp LoadMaster Microsoft Entra 統合 Web サーバー]

a. 仮想サービスのニック名で [変更] を選択します。

b。 [ESP オプション] を選択します。

c. [Server Authentication Mode](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サーバー認証モード) で、[KCD] を選択します。

d. [Server-Side configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サーバー側の構成) で、以前に作成したサーバー側のプロファイルを選択します。

#### Kemp LoadMaster Microsoft Entra 統合のテスト ユーザーを作成する

このセクションでは、Kemp LoadMaster Microsoft Entra 統合で B.Simon というユーザーを作成します。 [Kemp LoadMaster Microsoft Entra 統合クライアント サポート チーム](mailto:support@kemp.ax)と協力して、Kemp LoadMaster Microsoft Entra 統合プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Kemp LoadMaster Microsoft Entra 統合に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで Kemp LoadMaster Microsoft Entra 統合タイルを選択すると、SSO を設定した Kemp LoadMaster Microsoft Entra 統合に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kendis-scaling-agile-platform-tutorial"} -->
## Microsoft Entra ID で Kendis for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kendis-scaling-agile-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kendis - Microsoft Entra の統合の間でシングル サインオンを構成する方法について説明します。

この記事では、Kendis - Microsoft Entra Integration と Microsoft Entra ID を統合する方法について説明します。 Kendis - Microsoft Entra 統合を Microsoft Entra ID と統合する場合、次のことができます。

- Kendis - Microsoft Entra 統合にアクセスする Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Kendis - Microsoft Entra 統合に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kendis - Microsoft Entra 統合でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Kendis - Microsoft Entra 統合では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Kendis - Microsoft Entra 統合では、**Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Kendis - Microsoft Entra 統合の追加

Microsoft Entra ID への Kendis - Microsoft Entra の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Kendis - Microsoft Entra 統合を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Kendis - Microsoft Entra 統合**」と入力します。
4. 結果のパネルから **[Kendis - Microsoft Entra 統合]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kendis - Microsoft Entra 統合に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使って、Microsoft Entra SSO に対して Kendis - Microsoft Entra 統合を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Kendis - Microsoft Entra 統合の関連ユーザーとの間にリンク関係を確立する必要があります。

Kendis - Microsoft Entra 統合に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kendis - Azure AD Integration の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Kendis-Azure AD Integration テスト ユーザーの作成** - Kendis で B.Simon に対応する相手を用意し、Microsoft Entra のユーザー表現にリンクするための、Microsoft Entra Integration のユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Kendis - Microsoft Entra Integration**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.kendis.io`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.kendis.io/login/saml`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.kendis.io/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Kendis - Microsoft Entra 統合クライアント サポート チーム](mailto:support@kendis.io)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Kendis - Microsoft Entra 統合のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kendis - Azure AD Integration の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Kendis - Microsoft Entra 統合企業サイトに管理者としてサインインします。
2. **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)\ &gt; [SAML Configurations](SAML 構成)** に移動します。

    [Image: SAML 構成の設定]
3. ページの下部にある **[編集** ] ボタンを選択し、次の手順を実行します。

    [Image: SAML 構成]

    ある。 **[コールバック URL]** の値をコピーし、[基本的な SAML 構成] セクションの **[応答 URL]** テキスト ボックスにその値を貼り付けます。

    b。 **[ID プロバイダーのシングル サインオン URL]** テキストボックスに、先ほどコピーした **[ログイン URL]** の値を貼り付けます。

    c. **[Identity Provider Issuer] (ID プロバイダー発行者 (エンティティ ID))** テキストボックスに、先ほどコピーした **Microsoft Entra 識別子 (エンティティ ID)**の値を貼り付けます。

    d. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[X.509 証明書]** テキストボックスに貼り付けます。

    え オプションのリストから **[Default Group](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/既定のグループ) を選択**します。

    f. **保存** を選択します。

#### Kendis - Azure AD Integration のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Kendis - Microsoft Entra 統合に作成します。 Kendis - Microsoft Entra 統合では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Kendis - Microsoft Entra 統合にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Kendis - Microsoft Entra Integration のサインオン URL にリダイレクトされます。
- Kendis - Microsoft Entra 統合のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Kendis - Microsoft Entra Integration に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Kendis - Microsoft Entra Integration タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Kendis - Microsoft Entra Integration に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kenexasurvey-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に IBM Kenexa Survey Enterprise を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kenexasurvey-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と IBM Kenexa Survey Enterprise の間でシングル サインオンを構成する方法について説明します。

この記事では、IBM Kenexa Survey Enterprise と Microsoft Entra ID を統合する方法について説明します。 IBM Kenexa Survey Enterprise を Microsoft Entra ID と統合すると、次のことができます。

- IBM Kenexa Survey Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して IBM Kenexa Survey Enterprise に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- IBM Kenexa Survey Enterprise のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- IBM Kenexa Survey Enterprise では、**IDP** によって開始される SSO がサポートされます。

### ギャラリーからの IBM Kenexa Survey Enterprise の追加

Microsoft Entra ID への IBM Kenexa Survey Enterprise の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に IBM Kenexa Survey Enterprise を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**IBM Kenexa Survey Enterprise**」と入力します。
4. 結果のパネルから **[IBM Kenexa Survey Enterprise]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### IBM Kenexa Survey Enterprise に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、IBM Kenexa Survey Enterprise に Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと IBM Kenexa Survey Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

IBM Kenexa Survey Enterprise に Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **IBM Kenexa Survey Enterprise の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **IBM Kenexa Survey Enterprise のテストユーザー作成** - IBM Kenexa Survey Enterprise で B.Simon の対応ユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせるため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[IBM Kenexa Survey Enterprise]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://surveys.kenexa.com/<companycode>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://surveys.kenexa.com/<companycode>/tools/sso.asp`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を入手するには、[IBM Kenexa Survey Enterprise クライアント サポート チーム](https://www.ibm.com/support/home/?lnk=fcw)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. IBM Kenexa Survey Enterprise アプリケーションでは、特定の形式の Security Assertions Markup Language (SAML) アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 応答内のユーザー識別子要求の値は、Kenexa システムで構成された SSO ID に一致する必要があります。 組織内の適切なユーザー ID を SSO Internet Datagram Protocol (IDP) としてマッピングするには、[IBM Kenexa Survey Enterprise サポート チーム](https://www.ibm.com/support/home/?lnk=fcw)と連携してください。

    既定では、Microsoft Entra ID はユーザーの識別子を、ユーザー プリンシパル名 (UPN) の値として設定します。 この値は、以下のスクリーン ショットに示すように **[ユーザー属性]** タブから変更できます。 マッピングを正しく完了した後にのみ統合は機能します。

    [Image: 画像]
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[IBM Kenexa Survey Enterprise のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### IBM Kenexa Survey Enterprise SSO の構成

**IBM Kenexa Survey Enterprise** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [IBM Kenexa Survey Enterprise サポート チーム](https://www.ibm.com/support/home/?lnk=fcw)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### IBM Kenexa Survey Enterprise のテスト ユーザーの作成

このセクションでは、IBM Kenexa Survey Enterprise で Britta Simon というユーザーを作成します。

IBM Kenexa Survey Enterprise システムでユーザーを作成し、それに SSO ID をマッピングするには、[IBM Kenexa Survey Enterprise サポート チーム](https://www.ibm.com/support/home/?lnk=fcw)と連携してください。 また、この SSO ID 値を Microsoft Entra ID のユーザー ID の値にマップする必要があります。 **[属性]** タブでこの既定の設定を変更できます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した IBM Kenexa Survey Enterprise に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [IBM Kenexa Survey Enterprise] タイルを選択すると、SSO を設定した IBM Kenexa Survey Enterprise に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kerbf5-tutorial"} -->
## Single-Tier SaaS アプリの F5 Kerberos 制約付き委任を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kerbf5-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と F5 の間のシングル サインオン (SSO) を構成する方法について説明します。

この記事では、F5 と Microsoft Entra ID を統合する方法について説明します。 F5 を Microsoft Entra ID と統合すると、次のことが可能になります。

- F5 へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して F5 に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- F5 でのシングル サインオン (SSO) が有効なサブスクリプション。
- 共同ソリューションをデプロイするには次のライセンスが必要です。

    - F5 BIG-IP® Best バンドル (または)
    - F5 BIG-IP Access Policy Manager™ (APM) スタンドアロン ライセンス
    - 既存の BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP Access Policy Manager™ (APM) アドオン ライセンス
    - 上記のライセンスに加えて、F5 システムには次のライセンスが付与される場合があります。

        - URL カテゴリ データベースを使用するための URL フィルタリング サブスクリプション
        - 既知の攻撃者や悪意のあるトラフィックを検出してブロックするための F5 IP Intelligence サブスクリプション
        - 強力な認証用のデジタル キーを保護、管理するためのネットワーク ハードウェア セキュリティ モジュール (HSM)
- F5 BIG-IP システムは、APM モジュールと共にプロビジョニングされます (LTM はオプション)。
- オプションですが、高可用性 (HA) 用のフローティング IP アドレスを持つアクティブ スタンバイ ペアを含む [同期/フェールオーバー デバイス グループ](https://techdocs.f5.com/kb/en-us/products/big-ip_ltm/manuals/product/bigip-device-service-clustering-admin-11-6-0.html) (S/F DG) に F5 システムをデプロイすることを強くお勧めします。 Link Aggregation Control Protocol (LACP) を使用すれば、さらなるインターフェイスの冗長性を実現できます。 LACP は、接続されている物理インターフェイスを 1 つの仮想インターフェイス (集計グループ) として管理し、そのグループ内のインターフェイスに発生したエラーを検出します。
- Kerberos アプリケーションに関して、制約付き委任に使用するオンプレミス AD サービス アカウント。 AD 委任アカウントの作成については、 [F5 ドキュメント](https://support.f5.com/csp/article/K43063049) を参照してください。

### アクセス ガイド付き構成

- アクセス ガイド付き構成は、F5 TMOS バージョン 13.1.0.8 以降でサポートされます。 BIG-IP システムで 13.1.0.8 より前のバージョンが実行されている場合は、「 **詳細な構成** 」セクションを参照してください。
- アクセスガイド付き構成では、新しく合理化されたユーザー エクスペリエンスが提供されます。 このワークフローベースのアーキテクチャにより、選択したトポロジに合わせて調整された直感的で再入可能な構成ステップが提供されます。
- 構成に進む前に、downloads.f5.com から最新のユース ケース パックをダウンロードして、ガイド付き構成 [を](https://login.f5.com/resource/login.jsp?ctx=719748)アップグレードします。 アップグレードするには、以下の手順に従います。

    Note

    以下のスクリーンショットは、リリースされた最新バージョン (BIG-IP 15.0、AGC バージョン 5.0) のものです。 13.1.0.8 から最新の BIG-IP バージョンでは、このユース ケースに下記の構成手順が有効です。

1. F5 BIG-IP Web UI で **[アクセス] &gt;&gt; [ガイド付き構成]** を選択します。
2. [ **ガイド付き構成]** ページで、左上隅にある **[ガイド付き構成のアップグレード** ] を選択します。

    [Image: [ガイド付き構成のアップグレード] アクションが選択されている [ガイド付き構成] ページを示すスクリーンショット。]
3. [アップグレード ガイドの構成] ポップ画面で、[ **ファイルの選択** ] を選択してダウンロードしたユース ケース パックをアップロードし、[ **アップロードとインストール** ] ボタンを選択します。

    [Image: [ファイルの選択] と [アップロードとインストール] が選択された [ガイド付き構成のアップグレード] ポップアップ画面を示すスクリーンショット。]
4. アップグレードが完了したら、[ **続行** ] ボタンを選択します。

    [Image: [ガイド付き構成の更新が完了しました] ダイアログと [続行] ボタンが選択されていることを示すスクリーンショット。]

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- F5 では、**SP および IDP** による SSO がサポートされます
- F5 SSO は、次の 3 つの異なる方法で構成できます。

- Kerberos アプリケーションの F5 シングル サインオンを構成する
- [ヘッダー ベース アプリケーションの F5 シングル サインオンを構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/f5-big-ip-headers-easy-button)
- [Advanced Kerberos アプリケーションの F5 シングル サインオンを構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/advance-kerbf5-tutorial)

#### キー認証のシナリオ

先進認証プロトコル (OpenID Connect、SAML、WS-Fed など) に対する Microsoft Entra のネイティブ統合のサポートとは別に、F5 は、Microsoft Entra ID を使用することで、内部と外部の両方のアクセスに関してレガシベース認証アプリの安全なアクセスを拡張し、それらのアプリケーションへの最新のシナリオ (パスワードレス アクセスなど) を実現します。 これには、次のものが含まれます。

- ヘッダーベースの認証アプリ
- Kerberos 認証アプリ
- 匿名認証または非ビルトイン認証アプリ
- NTLM 認証アプリ (ユーザーに対する二重プロンプトでの保護)
- フォーム ベース アプリケーション (ユーザーに対する二重プロンプトでの保護)

### ギャラリーからの F5 の追加

Microsoft Entra ID への F5 の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に F5 を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「F5**」と入力します。
4. 結果パネルから **F5 キー** を押し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### F5 に対して Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、F5 に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと F5 の関連ユーザーとの間にリンク関係を確立する必要があります。

F5 で Microsoft Entra SSO を構成してテストするには、次の構成要素を順に実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **F5 SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **F5 テストユーザーの作成** - B.Simon に対応するユーザーを F5 で作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**F5**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YourCustomFQDN>.f5.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YourCustomFQDN>.f5.com/`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YourCustomFQDN>.f5.com/`

    Note

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [F5 クライアント サポート チーム](https://support.f5.com/csp/knowledge-center/software/BIG-IP?module=BIG-IP%20APM45) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** と **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **F5 のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### F5 SSO の構成

- [ヘッダー ベース アプリケーションの F5 シングル サインオンを構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/f5-big-ip-headers-easy-button)
- [Advanced Kerberos アプリケーションの F5 シングル サインオンを構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/advance-kerbf5-tutorial)

#### Kerberos アプリケーション用に F5 シングル サインオンを構成する

#### ガイド付き構成

1. 新しい Web ブラウザー ウィンドウを開き、F5 (Kerberos) 企業サイトに管理者としてサインインして、次の手順を実行します。
2. セットアップ プロセスの後半で使用される F5 にメタデータ証明書をインポートする必要があります。
3. **[System &gt; Certificate Management &gt; Traffic Certificate Management &gt; SSL Certificate List**] に移動します。 右上隅にある **[インポート]** を選択します。 **証明書名**を指定します (構成の後半で参照されます)。 **[証明書のソース]** で、[ファイルのアップロード] を選択し、SAML シングル サインオンの構成時に Azure からダウンロードした証明書を指定します。 [ **インポート] を選択します**。

    [Image: [証明書名] が強調表示され、[ファイルのアップロード] と [インポート] ボタンが選択されている [S S L Certificate/Key Source](S S L 証明書/キー ソース) ページを示すスクリーンショット。]
4. さらに、 **アプリケーション ホスト名には SSL 証明書が必要です。[System &gt; Certificate Management &gt; Traffic Certificate Management &gt; SSL Certificate List] に移動します**。 右上隅にある **[インポート]** を選択します。 **インポートの種類** は **PKCS 12 (IIS) です**。 **キー名** (構成の後半で参照) を指定し、PFX ファイルを指定します。 PFX の **パスワード** を指定します。 [ **インポート] を選択します**。

    Note

    この例では、アプリ名が `Kerbapp.superdemo.live`、キー名がワイルドカード証明書を使用しています `WildCard-SuperDemo.live`

    [Image: 値が入力され、[インポート] ボタンが選択されている [S S L Certificate/Key Source](S S L 証明書/キー ソース) ページを示すスクリーンショット。]
5. ガイド付きエクスペリエンスを使用して、Microsoft Entra フェデレーションとアプリケーション アクセスを設定します。 F5 BIG-IP の **[Main](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メイン)** に移動し、**[Access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス) &gt; [Guided Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ガイド付き構成) &gt; [Federation](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/フェデレーション) &gt; [SAML Service Provider](SAML サービス プロバイダー)** の順に選択します。 [ **次へ** ] を選択し、[ **次へ** ] を選択して構成を開始します。

    [Image: [フェデレーション] アイコンが強調表示され、[S A M L サービス プロバイダー] が選択されている [ガイド付き構成] ページを示すスクリーンショット。]

    [Image: [次へ] ボタンが選択されている [ガイド付き構成 - S A M L サービス プロバイダー] ページを示すスクリーンショット。]
6. **構成名を指定します**。 **エンティティ ID** (Microsoft Entra アプリケーション構成で構成したのと同じ) を指定します。 **ホスト名**を指定します。 参照用の **説明** を追加します。 残りの既定のエントリをそのまま使用し、[ **保存] & [次へ**] を選択します。

    [Image: [ホスト名] と [説明] テキスト ボックスが強調表示され、[保存して次へ] ボタンが選択されている [サービス プロバイダーのプロパティ] を示すスクリーンショット。]
7. この例では、ポート 443 で 192.168.30.200 として新しい仮想サーバーを作成しています。 宛先アドレスに仮想サーバーの IP アドレスを指定 **します**。 クライアント **SSL プロファイル**を選択し、[新規作成] を選択します。 以前にアップロードしたアプリケーション証明書 (この例ではワイルドカード証明書) と関連付けられているキーを指定し、[ **保存] & [次へ**] を選択します。

    Note

    この例では、内部 Web サーバーがポート 80 で稼動しており、それを 443 で公開したいと考えています。

    [Image: [宛先アドレス] テキスト ボックスが強調表示され、[保存して次へ] ボタンが選択されている [仮想サーバーのプロパティ] ページを示すスクリーンショット。]
8. **IdP コネクタ設定方法を選択**し、メタデータを指定し、**ファイルの選択**を選択して、Microsoft Entra ID から先ほどダウンロードしたメタデータ XML ファイルをアップロードします。 SAML IDP コネクタの一意の **名前** を指定します。 前にアップロードした **メタデータ署名証明書** を選択します。 **[保存] & [次へ] を選択します**。

    [Image: [名前] テキスト ボックスが強調表示され、[保存して次へ] ボタンが選択されている [External Identity Provider Connector Settings](外部 ID プロバイダー コネクタの設定) ページを示すスクリーンショット。]
9. [ **プールの選択**] で、[ **新規作成** ] を指定します (または、既に存在するプールを選択します)。 他の値は既定値のままにしてください。 [プール サーバー] で、[IP アドレス **/ノード名] に IP アドレスを入力します**。 ポートを指定 **します**。 **[保存] & [次へ] を選択します**。

    [Image: [IP アドレス/ノード名] と [ポート] テキスト ボックスが強調表示され、[保存して次へ] ボタンが選択されている [プールのプロパティ] ページを示すスクリーンショット。]
10. [シングル Sign-On 設定] 画面で、[ **シングル サインオンを有効にする**] を選択します。
11. **[Selected Single Sign-On Type](選択されたシングル サインオンの種類)** で **[Kerberos]** を選択します。 session.saml.last.Identity を、ユーザー名ソース の下の session.saml.last.attr.name.Identityに置き換えます (この変数は、Microsoft Entra ID の要求マッピングを使用して設定します)
12. [**詳細設定の表示] を**選択する
13. **[Kerberos 領域**] に「ドメイン名」と入力します。
14. [ **アカウント名/アカウント パスワード**] で、APM 委任アカウントとパスワードを指定します。
15. **[KDC**] フィールドにドメイン コントローラー IP を指定します。
16. **[保存] & [次へ] を選択します**。
17. このガイダンスでは、エンドポイントチェックをスキップします。 詳細については、F5 のドキュメントを参照してください。 画面で[ **保存]、[次へ]**の順に選択します。
18. 既定値をそのまま使用し、[ **保存] & [次へ]** を選択します。 SAML セッション管理設定の詳細については、F5 のドキュメントを参照してください。

    [Image: [保存して次へ] ボタンが選択されている [タイムアウト設定] ページを示すスクリーンショット。]
19. 概要画面を確認し、[ **デプロイ** ] を選択して BIG-IP を構成します。

    [Image: [概要] セクションが強調表示され、[デプロイ] ボタンが選択されている [アプリケーションをデプロイする準備ができました] ページを示すスクリーンショット。]
20. アプリケーションが構成されたら、[ **完了]** を選択します。

    [Image: [完了] ボタンが選択されている [アプリケーションがデプロイされています] ページを示すスクリーンショット。]

### 高度な構成

Note

リファレンス [については、こちらを選択してください](https://techdocs.f5.com/kb/en-us/products/big-ip_apm/manuals/product/apm-authentication-single-sign-on-12-1-0/2.html)

#### Active Directory AAA サーバーを構成する

Access Policy Manager (APM) がユーザーの認証に使用するドメイン コントローラーと資格情報を指定するには、APM で Active Directory AAA サーバーを構成します。

1. メイン タブで、**Active Directory &gt; AAA サーバ&gt;アクセス ポリシー**を選択します。 Active Directory サーバーのリスト画面が表示されます。
2. **作成**を選択します。 新しいサーバーのプロパティ画面が表示されます。
3. [ **名前** ] フィールドに、認証サーバーの一意の名前を入力します。
4. [ **ドメイン名]** フィールドに、Windows ドメインの名前を入力します。
5. **[サーバー接続**] 設定で、次のいずれかのオプションを選択します。

    - AAA サーバの高可用性を設定するには、[ **プールを使用** ]を選択します。
    - スタンドアロン機能用に AAA サーバを設定するには、[ **ダイレクト** ]を選択します。
6. **[ダイレクト**] を選択した場合は、[**ドメイン コントローラー**] フィールドに名前を入力します。
7. [ **プール**の使用] を選択した場合は、プールを構成します。

    - [ **ドメイン コントローラー プール名] フィールドに名前を入力** します。
    - プール内の **ドメイン コントローラー** を指定するには、それぞれに IP アドレスとホスト名を入力し、[ **追加** ] ボタンを選択します。
    - AAA サーバの正常性を監視するには、ヘルス モニタを選択するオプションがあります。この場合は **、gateway\_icmp** モニタのみが適切です。サーバー **プール モニター** の一覧から選択できます。
8. [ **管理者名]** フィールドに、Active Directory の管理アクセス許可を持つ管理者の大文字と小文字が区別される名前を入力します。 APM では、AD クエリの **[管理者名]** フィールドと **[管理者パスワード** ] フィールドの情報が使用されます。 Active Directory が匿名クエリ用に構成されている場合は、管理者名を指定する必要はありません。 それ以外の場合は、パスワード関連機能をサポートするために、Active Directory サーバーへのバインド、ユーザー グループ情報のフェッチ、Active Directory パスワード ポリシーのフェッチを行うのに十分な権限を持つアカウントが必要です (たとえば、AD クエリ アクションで [有効期限前にパスワードを変更するようにユーザーに求める] オプションを選択した場合など、APM はパスワード ポリシーをフェッチする必要があります)。この構成で管理者アカウント情報を指定しない場合、APM はユーザー アカウントを使用して情報をフェッチします。 これは、ユーザー アカウントに必要な権限がある場合に機能します。
9. [ **管理者パスワード** ] フィールドに、ドメイン名に関連付けられている管理者パスワードを入力します。
10. [ **管理者パスワードの確認** ] フィールドで、[ **ドメイン名** ] 設定に関連付けられている管理者パスワードを再入力します。
11. [ **グループ キャッシュの有効期間** ] フィールドに日数を入力します。 既定の有効期間は 30 日です。
12. [ **パスワード セキュリティ オブジェクト キャッシュの有効期間** ] フィールドに日数を入力します。 既定の有効期間は 30 日です。
13. **[Kerberos 事前認証の暗号化の種類]** ボックスの一覧から、暗号化の種類を選択します。 既定値は [なし] です。 暗号化の種類を指定した場合は、BIG-IP システムにより、最初の認証サービス要求 (AS-REQ) パケット内に Kerberos 事前認証データが追加されます。
14. [ **タイムアウト** ] フィールドに、AAA サーバのタイムアウト間隔 (秒単位) を入力します。 (この設定は省略可能です)。
15. [ **完了] を選択します**。 新しいサーバーがリストに表示されます。 これで、新しい Active Directory サーバーが Active Directory サーバー リストに追加されます。

    [Image: [全般プロパティ] セクションと [構成] セクションを示すスクリーンショット。]

#### SAML の構成

1. セットアップ プロセスの後半で使用される F5 にメタデータ証明書をインポートする必要があります。 **[System &gt; Certificate Management &gt; Traffic Certificate Management &gt; SSL Certificate List**] に移動します。 右上隅にある **[インポート]** を選択します。

    [Image: [インポート] ボタンが選択されている [Import S S L Certificate/Key Source](S S L 証明書/キー ソースのインポート) ページを示すスクリーンショット。]
2. SAML IDP を設定するには、[ **ACCESS &gt; Federation &gt; SAML: Service Provider &gt; External Idp Connectors**] に移動し、[ **メタデータから &gt; 作成**] を選択します。

    [Image: [作成] ドロップダウンから [メタデータから] が選択されている [S A M L サービス プロバイダー] ページを示すスクリーンショット。]

    [Image: [Create New S A M L I d P Connector](新しい S A M L I d P コネクタの作成) ダイアログを示すスクリーンショット。]

    [Image: [全般設定] が選択されている [EDIT S A M L I d P Connector](S A M L I d P コネクタの編集) ウィンドウを示すスクリーンショット。]

    [Image: [Single Sign On Service Settings](シングル サインオン サービス設定) が選択されている [Edit S A M L I d P Connector](S A M L I d P コネクタの編集) ウィンドウを示すスクリーンショット。]

    [Image: [セキュリティ設定] が選択された [EDIT S A M L I d P Connector](S A M L I d P コネクタの編集) ウィンドウを示すスクリーンショット。]

    [Image: [S L O サービス設定] が選択されている [EDIT S A M L I d P Connector](S A M L I d P コネクタの編集) ウィンドウを示すスクリーンショット。]
3. SAML SP を設定するには、[ **Access &gt; Federation &gt; SAML Service Provider &gt; Local SP Services** ] に移動し、[ **作成**] を選択します。 次の情報を入力し、[ **OK]** を選択します。

    - 種類名: KerbApp200SAML
    - [Entity ID\*](エンティティ ID\*): https://kerb-app.com.cutestat.com
    - [SP Name Settings](SP 名の設定)
    - [Scheme](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/スキーム): https
    - [Host](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ホスト): kerbapp200.superdemo.live
    - [Description](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/説明): kerbapp200.superdemo.live

    [Image: [全般設定] が選択された [Edit S A M L S P Service](S A M L P サービスの編集) ウィンドウを示すスクリーンショット。]

    b。 SP 構成である KerbApp200SAML を選択し、**[IdP コネクタのバインドまたはバインド解除]** を選択します。

    [Image: [Bind/Unbind I d P Connectors](I d P コネクタのバインド/バインド解除) ボタンが選択されていることを示すスクリーンショット。]

    c. [ **新しい行の追加]** を選択し、前の手順で作成した **外部 IdP コネクタ** を選択し、[ **更新**] を選択して、[ **OK] を選択します**。

    [Image: [Add New Row](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しい行の追加) ボタンが選択されている [Edit S A M L I d Ps that use this S P](この S P を使用する S A M L I d Ps の編集) ウィンドウを示すスクリーンショット。]
4. Kerberos SSO を構成するには、[ **Access &gt; Single Sign-on &gt; Kerberos** に移動し、情報を入力して **[完了]** を選択します。

    Note

    Kerberos 委任アカウントを作成して指定する必要があります。 KCD セクションを参照してください (変数リファレンスについては、付録を参照してください)

    - **ユーザー名のソース**: session.saml.last.attr.name。http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname
    - **[ユーザー領域のソース]** : session.logon.last.domain

        [Image: [ユーザー名のソース] テキスト ボックスと [ユーザー領域ソース] テキスト ボックスが強調表示されている [単一の Sign-On - プロパティ] ページを示すスクリーンショット。]
5. アクセス プロファイルを構成するには、 **アクセス &gt; プロファイル/ポリシー &gt; アクセス プロファイル (セッション ポリシーごと)** に移動し、[ **作成**] を選択し、次の情報を入力して **[完了]** を選択します。

    - 名前:KerbApp200
    - [Profile Type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロファイルの種類): All
    - [Profile Scope](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロファイルのスコープ): プロファイル
    - 言語:英語

        [Image: [名前]、[プロファイルの種類]、[言語] テキスト ボックスが強調表示されている [プロファイル/ポリシー - プロパティ] ページを示すスクリーンショット。]
6. KerbApp200 という名前を選択し、次の情報を入力して [ **更新**] を選択します。

    - [Domain Cookie](ドメイン Cookie): superdemo.live
    - [SSO Configuration](SSO 構成): KerAppSSO\_sso

        [Image: [ドメイン Cookie] テキスト ボックスと [S S O 構成] ドロップダウンが強調表示され、[更新] ボタンが選択されている [S S D/Auth Domains] ページを示すスクリーンショット。]
7. アクセス **ポリシー** を選択し、プロファイル "KerbApp200" の **アクセス ポリシーの編集** を選択します。

    [Image: [プロファイル KerbApp200 のアクセス ポリシーの編集] アクションが選択されている [アクセス ポリシー] ページを示すスクリーンショット。]

    [Image: [アクセス ポリシー] ページと [S A M L 認証 S P] ダイアログを示すスクリーンショット。]

    [Image: [アクセス ポリシー] ページと [変数の割り当て] ダイアログを示すスクリーンショット。[割り当て] テキスト ボックスが強調表示されています。]

    - **session.logon.last.usernameUPN expr {[mcget {session.saml.last.identity}]}**
    - **session.ad.lastactualdomain TEXT superdemo.live**

        [Image: [アクセス ポリシー] ページと [SearchFilter] テキスト ボックスが強調表示された [Active Directory] ダイアログを示すスクリーンショット。]
    - **(userPrincipalName=%{session.logon.last.usernameUPN})**

        [Image: [A D クエリ - 分岐ルール] ダイアログが表示された [アクセス ポリシー] ページを示すスクリーンショット。]

        [Image: [カスタム変数] テキスト ボックスと [カスタム式] テキスト ボックスが強調表示されているスクリーンショット。]
    - **session.logon.last.username expr { "[mcget {session.ad.last.attr.sAMAccountName}]" }**

        [Image: [ログオン ページからのユーザー名] テキスト ボックスが強調表示されているスクリーンショット。]
    - **mcget {session.logon.last.username}**
    - **mcget {session.logon.last.password**
8. 新しいノードを追加するには、 **ローカル トラフィック &gt; ノード &gt; ノード リストに移動し、[作成] を選択**し、次の情報を入力して、[ **完了]** を選択します。

    - 名前:KerbApp200
    - 説明:KerbApp200
    - Address:192.168.20.200

        [Image: [名前]、[説明]、および [アドレス] テキスト ボックスが強調表示され、[完了] ボタンが選択されている [新しいノード] ページを示すスクリーンショット。]
9. 新しいプールを作成するには、[ **Local Traffic &gt; Pools &gt; Pool List] に移動し、[作成] を選択**し、次の情報を入力して **[完了]** を選択します。

    - 名前:KerbApp200-Pool
    - 説明:KerbApp200-Pool
    - [Health Monitors](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/正常性モニター): http
    - Address:192.168.20.200
    - [Service Port](サービス ポート): 81

        [Image: 値が入力され、[完了] ボタンが選択されている [新しいプール] ページを示すスクリーンショット。]
10. 仮想サーバーを作成するには、ローカル **トラフィック &gt; 仮想サーバー &gt; 仮想サーバーの一覧 &gt; +** に移動し、次の情報を入力して [ **完了]** を選択します。

    - 名前:KerbApp200
    - [Destination Address/Mask](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/接続先のアドレス/マスク): [Host](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ホスト) 192.168.30.200
    - [Service Port](サービス ポート): [Port](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ポート) 443 HTTPS
    - [Access Profile](アクセス プロファイル): KerbApp200
    - 前の手順で作成したアクセス プロファイルを指定する

        [Image: [名前]、[宛先アドレス/マスク]、および [サービス ポート] テキスト ボックスが強調表示されている [仮想サーバーの一覧] ページを示すスクリーンショット。]

        [Image: [アクセス プロファイル] ドロップダウンが強調表示されている [仮想サーバーの一覧] ページを示すスクリーンショット。]

#### Kerberos 委任の設定

Note

参考までに、 [ここを選択してください](https://www.f5.com/pdf/deployment-guides/kerberos-constrained-delegation-dg.pdf)

- **手順 1:** 委任アカウントを作成する

    **例：**

    - ドメイン名: **superdemo.live**
    - Sam アカウント名: **big-ipuser**
    - New-ADUser -Name "APM 委任アカウント" -UserPrincipalName ホスト/big-ipuser.superdemo.live@superdemo.live -SamAccountName "big-ipuser" -PasswordNeverExpires $true -Enabled $true -AccountPassword ("Password!1234"Read-Host -AsSecureString)
- **手順 2:** SPN の設定 (APM 委任アカウント)

    **例：**

    - setspn –A **host/big-ipuser.superdemo.live** big-ipuser
- **手順 3:** SPN 委任 (App Service アカウントの場合)

    F5 委任アカウントに適切な委任を設定します。

    次の例では、FRP-App1.superdemo.live の KCD に対して APM 委任アカウントが構成されています。 live アプリの KCD に対して APM 委任アカウントが構成されています。

    [Image: F5 (Kerberos) の構成]
- 上記の参照ドキュメントの[ここ](https://techdocs.f5.com/kb/en-us/products/big-ip_apm/manuals/product/apm-authentication-single-sign-on-12-1-0/2.html)に記載されているように詳細を指定します。

#### F5 テスト ユーザーの作成

このセクションでは、F5 で B.Simon というユーザーを作成します。 [F5 クライアント サポート チーム](https://support.f5.com/csp/knowledge-center/software/BIG-IP?module=BIG-IP%20APM45)と協力して、F5 プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra シングル サインオン構成をテストします。

アクセス パネルで [F5] タイルを選択すると、SSO を設定した F5 に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/keystone-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Keystone を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/keystone-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-12
- Summary: Microsoft Entra ID から Keystone に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を説明します。

この記事では、Keystone と Microsoft Entra ID の両方で実行して、自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、Keystone に対するユーザーのプロビジョニングと解除を自動的に行います。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされる機能

- Keystone でユーザーを作成します。
- アクセスが不要になったら、Keystone のユーザーを削除します。
- Microsoft Entra ID と Keystone の間でユーザー属性の同期を維持します。
- Keystone に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/keystone-tutorial)します (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者アクセス許可がある Keystone のユーザー アカウント。

### 手順 1: プロビジョニングのデプロイを計画します

- [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- [Microsoft Entra ID と Keystone の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Keystone を構成する

Microsoft Entra ID を使ったプロビジョニングをサポートするように Keystone を構成するには、Keystone のサポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Keystone を追加する

Microsoft Entra アプリケーション ギャラリーから Keystone を追加して、Keystone へのプロビジョニングの管理を開始します。 以前に Keystone を SSO 用にセットアップしてある場合は、同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Keystone への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーの割り当てに基づいて、TestApp でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Keystone の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Keystone**] を選択します。

    [Image: アプリケーションの一覧の [Keystone] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Keystone テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Keystone に接続できることを確認します。 接続に失敗した場合は、Keystone アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Keystone に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Keystone のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Keystone API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Keystone が要求する |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 表示名 | 糸 |  | ✓ |
    | エクスターナルID | 糸 | ✓ |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/keystone-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Keystone を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/keystone-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Keystone の間のシングル サインオンを構成する方法について説明します。

この記事では、Keystone と Microsoft Entra ID を統合する方法について説明します。 Keystone を Microsoft Entra ID と統合すると、次のことができます。

- Kintone にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Keystone に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Keystone のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Keystone では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの Keystone の追加

Microsoft Entra ID への Keystone の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Keystone を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Keystone**」と入力します。
4. 結果のパネルから **[Keystone]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Keystone に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Keystone に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Keystone での関連ユーザーとの間にリンク関係を確立する必要があります。

Keystone に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Keystone SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Keystone のテスト ユーザーの作成** - Keystone で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Keystone]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`urn:amazon:cognito:sp:<UserPoolID>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<UserPoolName>.auth.<Region>.amazoncognito.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://irca.<Environment>.fm.ks.irdeto.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Keystone サポート チーム](mailto:soc@irdeto.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Keystone SSO の構成

**Keystone** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Keystone サポート チーム](mailto:soc@irdeto.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Keystone のテスト ユーザーの作成

このセクションでは、Keystone で Britta Simon というユーザーを作成します。 [Keystone サポート チーム](mailto:soc@irdeto.com)と協力して、Keystone プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Keystone Sign-On URL にリダイレクトされます。
- Keystone のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Keystone] タイルを選択すると、このオプションは Keystone Sign-On URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kfadvance-tutorial"} -->
## Microsoft Entra ID で KFAdvance for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kfadvance-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と KFAdvance の間のシングル サインオンを構成する方法について説明します。

この記事では、KFAdvance と Microsoft Entra ID を統合する方法について説明します。 KFAdvance を Microsoft Entra ID と統合すると、次のことが可能になります。

- KFAdvance にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで KFAdvance に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な KFAdvance サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- KFAdvance では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

### ギャラリーからの KFAdvance の追加

Microsoft Entra ID への KFAdvance の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に KFAdvance を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**KFAdvance**」と入力します。
4. 結果のパネルから **[KFAdvance]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### KFAdvance に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、KFAdvance に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと、KFAdvance での関連ユーザーとの間にリンク関係を確立する必要があります。

KFAdvance に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **KFAdvance の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **KFAdvance テストユーザーを作成** - KFAdvance における B.Simon の対応ユーザーで、Microsoft Entra のユーザー表現にリンクされています。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[KFAdvance]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.kfadvance.com/<PARTNER_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.kfadvance-<ENVIRONMENT>.com/v1/account/partnerssocallback?partnerKey=<PARTNER_ID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.kfadvance.com/v1/account/partnerssologin?partnerKey=<PARTNER_ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[KFAdvance クライアント サポート チーム](mailto:support@kornferry.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[KFAdvance のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### KFAdvance の SSO の構成

**KFAdvance** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [KFAdvance サポート チーム](mailto:support@kornferry.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### KFAdvance のテスト ユーザーの作成

このセクションでは、KFAdvance で Britta Simon というユーザーを作成します。 [KFAdvance サポート チーム](mailto:support@kornferry.com)と連携して、KFAdvance プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる KFAdvance サインオン URL にリダイレクトされます。
- KFAdvance のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した KFAdvance に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで KFAdvance タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した KFAdvance に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/khoros-care-tutorial"} -->
## Microsoft Entra ID で Khoros Care for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/khoros-care-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Khoros Care の間でシングル サインオンを構成する方法について説明します。

この記事では、Khoros Care と Microsoft Entra ID を統合する方法について説明します。 Khoros Care を Microsoft Entra ID と統合すると、次のことができます。

- Khoros Care にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Khoros Care に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Khoros Care でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Khoros Care では、**SP と IDP** によって開始される SSO がサポートされます。
- Khoros Care では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Khoros Care の追加

Microsoft Entra ID への Khoros Care の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Khoros Care を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Khoros Care**」と入力します。
4. 結果のパネルから **[Khoros Care]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Khoros Care 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Khoros Care に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Khoros Care の関連ユーザーとの間にリンク関係を確立する必要があります。

Khoros Care に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Khoros Care の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Khoros Care のテスト ユーザーを作成 - Microsoft Entra にリンクされた B.Simon に対応するユーザーを Khoros Care で作成します**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Khoros Care**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.response.lithium.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.response.lithium.com/sso/saml/v2/idp_response`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.response.lithium.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Khoros Care クライアント サポート チーム](mailto:support@khoros.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Khoros Care アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. その他に、Khoros Care アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Khoros Care のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Khoros Care の SSO の構成

**Khoros Care** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Khoros Care サポート チーム](mailto:support@khoros.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Khoros Care のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Khoros Care に作成します。 Khoros Care では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Khoros Care にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Khoros Care Sign-On URL にリダイレクトされます。
- Khoros Care のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Khoros Care に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Khoros Care] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション Sign-On ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Khoros Care に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kindling-tutorial"} -->
## Microsoft Entra ID で Kindling for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kindling-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kindling の間のシングル サインオンを構成する方法について説明します。

この記事では、Kindling と Microsoft Entra ID を統合する方法について説明します。 Kindling を Microsoft Entra ID と統合すると、次の利点があります。

- Kindling にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが Microsoft Entra アカウントで Kindling に自動的にサインイン (シングル サインオン) できるようにします。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kindling でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Kindling では、 **SP** Initiated SSO がサポートされます
- Kindling では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Kindling の追加

Microsoft Entra ID への Kindling の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Kindling を追加する必要があります。

**ギャラリーから Kindling を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **Kindling**」と入力し、結果パネルで **Kindling** を選択し、[ **追加** ] ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧内の Kindling]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Kindling で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Kindling の関連ユーザー間にリンク関係を確立する必要があります。

Kindling に対する Microsoft Entra シングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Kindling シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Kindling テストユーザーの作成** - Microsoft Entra におけるユーザーの表現とリンクする Kindling で Britta Simon に対応するユーザーを作成する。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Kindling に対する Microsoft Entra シングル サインオンを構成するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Kindling** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: Kindling のドメインと URLアドレスのシングルサインオンの詳細情報]

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.kindlingapp.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.kindlingapp.com/saml/module.php/saml/sp/metadata.php/clientIDP`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、Kindling クライアント サポート チーム](mailto:support@kindlingapp.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Kindling のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Kindling シングル サインオンの構成

**Kindling** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Kindling サポート チーム](mailto:support@kindlingapp.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Kindling テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Kindling 内に作成します。 Kindling では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Kindling にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Kindling] タイルを選択すると、SSO を設定した Kindling に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kintone-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Kintone を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kintone-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-12
- Summary: ユーザー アカウントを Kintone に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Kintone ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Kintone](https://www.kintone.com) に対してユーザーの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Kintone でユーザーを作成します。
- アクセスが不要になった場合は、Kintone のユーザーを削除します。
- Microsoft Entra ID と Kintone の間でユーザー属性の同期を維持する。
- Kintone に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kintone-tutorial)する (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある Kintone のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- [Microsoft Entra ID と Kintone 間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように Kintone を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように Kintone を構成するには、Kintone サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Kintone を追加する

Microsoft Entra アプリケーション ギャラリーから Kintone を追加して、Kintone へのプロビジョニングの管理を開始します。 以前に、SSO 用に Kintone を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Kintone への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて TestApp でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Kintone に対する自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Kintone]** を選択します。

    [Image: アプリケーション リストの Kintone リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Kintone テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Kintone に接続できることを確認します。 接続に失敗した場合は、Kintone アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Kintone に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Kintone のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Kintone API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Kintone で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | externalId | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kintone-tutorial"} -->
## Microsoft Entra ID で Kintone for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kintone-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kintone の間にシングル サインオンを構成する方法について説明します。

この記事では、Kintone と Microsoft Entra ID を統合する方法について説明します。 Kintone を Microsoft Entra ID と統合すると、次のことができます:

- Kintone にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Kintone に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kintone でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Kintone では、**SP** Initiated SSO がサポートされます。
- Kintone では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kintone-provisioning-tutorial)。

### ギャラリーから Kintone を追加する

Microsoft Entra ID への Kintone の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Kintone を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Kintone**」と入力します。
4. 結果のパネルから **[Kintone]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kintone 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Kintone に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Kintone の関連ユーザーとの間にリンク関係を確立する必要があります。

Kintone に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kintone SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Kintone テストユーザーの作成** - Microsoft Entra のユーザーとして表現される B.Simon に対応する Kintone ユーザーを作成し、これを Microsoft Entra ユーザーにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Kintone**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.kintone.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.kintone.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Kintone クライアント サポート チーム](https://www.kintone.com/contact/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Kintone のセットアップ]** セクションで、要件のとおりに適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kintone SSO を構成する

1. 別の Web ブラウザーのウィンドウで、 **Kintone** の企業サイトに管理者としてサインインします。
2. **[設定] アイコンを選択します**。

    [Image: 設定]
3. **[ユーザー] と [システム管理] を選択します**。

    [Image: ユーザーおよびシステム管理]
4. [**システム管理**]&gt;[**セキュリティ**][ログイン]**を選択します**。

    [Image: ログイン]
5. **[SAML 認証を有効にする]** を選択します。

    [Image: [ユーザーとシステム管理] が選択されていることを示すスクリーンショット。]
6. [SAML 承認] セクションで、次の手順に従います。

    [Image: SAML 認証]

    ア **[ログイン URL]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    b。 **[ログアウト URL]** ボックスに、値として `https://login.microsoftonline.com/common/wsfederation?wa=wsignout1.0` を貼り付けます。

    c. [ **参照] を** 選択して、Azure portal からダウンロードした証明書ファイルをアップロードします。

    d. **保存** を選択します。

#### Kintone テスト ユーザーの作成

Microsoft Entra ユーザーが Kintone にサインインできるようにするには、そのユーザーを Kintone にプロビジョニングする必要があります。 Kintone の場合、プロビジョニングは手動で行います。

#### ユーザー アカウントをプロビジョニングするには、次の手順を実行します。

1. **Kintone** の企業サイトに管理者としてサインインします。
2. **[設定] アイコンを選択します**。

    [Image: 設定]
3. **[ユーザー] と [システム管理] を選択します**。

    [Image: ユーザーおよびシステム管理]
4. [ **ユーザー管理**] で、[ **部門] と [ユーザー**] を選択します。

    [Image: 部署とユーザー]
5. **新しいユーザー**を選択します。

    [Image: [New User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザー) アクションが選択されている [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) セクションを示すスクリーンショット。]
6. **[新しいユーザー]** セクションで、次の手順に従います。

    [Image: 新しいユーザー]

    ア プロビジョニングする有効な Microsoft Entra アカウントの **表示名**、**ログイン名**、**新しいパスワード**、**パスワードの確認**、**メール アドレス**、その他の詳細を該当するボックスに入力します。

    b。 **保存** を選択します。

注

他の Kintone ユーザー アカウント作成ツールや、Kintone から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Kintone のサインオン URL にリダイレクトされます。
- Kintone のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Kintone] タイルを選択すると、このオプションは Kintone のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kisi-physical-security-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Kisi Physical Security を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kisi-physical-security-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-12
- Summary: Microsoft Entra ID から Kisi Physical Security に対してユーザー アカウントを自動的にプロビジョニングしたり、プロビジョニング解除したりする方法を説明します。

この記事では、Kisi Physical Security と Microsoft Entra ID の両方で、自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Kisi Physical Security](https://www.getkisi.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Kisi Physical Security でユーザーを作成する。
- アクセスが不要になったら、Kisi Physical Security のユーザーを削除します。
- Microsoft Entra ID と Kisi Physical Security の間でユーザー属性の同期を維持します。
- Kisi Physical Security でグループとグループ メンバーシップをプロビジョニングする。
- Kisi Physical Security に対して[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kisi-physical-security-tutorial)を行う (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Kisi Organization ライセンス](https://www.getkisi.com/enterprise)

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Kisi Physical Security の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Kisi Physical Security を構成する

#### Kisi でシークレット トークンを生成する

- Kisi Organization アカウントにサインインする
- [Organization Setup]\(組織のセットアップ\) で、SSO と SCIM を選択します
- [SCIM の有効化] をオンに切り替え、[トークンの生成] を選択する

    [Image: シークレット トークン]
- トークンをコピーする (このトークンは 1 回だけ表示されます)

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Kisi Physical Security を追加する

Microsoft Entra アプリケーション ギャラリーから Kisi Physical Security を追加して、Kisi Physical Security へのプロビジョニングの管理を開始します。 SSO のために Kisi Physical Security を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Kisi Physical Security への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて Kisi Physical Security 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Kisi Physical Security の自動ユーザー プロビジョニングを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Kisi Physical Security]** を選択します。

    [Image: アプリケーションの一覧の Kisi Physical Security のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Kisi 物理セキュリティ テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Kisi 物理セキュリティに接続できることを確認します。 接続に失敗した場合は、Kisi 物理セキュリティ アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Kisi Physical Security に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Kisi Physical Security のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Kisi Physical Security API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Kisi Physical Security で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | name.formatted | 糸 |  |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Kisi Physical Security に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Kisi Physical Security のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Kisi Physical Security で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
    | externalId | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kisi-physical-security-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Kisi Physical Security を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kisi-physical-security-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kisi Physical Security の間でシングル サインオンを構成する方法について説明します。

この記事では、Kisi Physical Security と Microsoft Entra ID を統合する方法について説明します。 Kisi Physical Security を Microsoft Entra ID と統合すると、次のことができます。

- Kisi Physical Security にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Kisi Physical Security に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kisi Physical Security でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Kisi Physical Security では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Kisi Physical Security では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Kisi Physical Security では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kisi-physical-security-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Kisi Physical Security の追加

Kisi Physical Security の Microsoft Entra への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Kisi Physical Security を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Kisi Physical Security**」と入力します。
4. 結果のパネルから **[Kisi Physical Security]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Kisi Physical Security に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Kisi Physical Security の関連ユーザーとの間にリンク関係を確立する必要があります。

Kisi Physical Security に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kisi Physical Security の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Kisi Physical Securityのテストユーザーを作成** - Microsoft Entraのユーザー表現にリンクされたKisi Physical Security内のB.Simonの対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Enterprise アプリ]**&gt;**[Kisi Physical Security]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://api.kisi.io/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.kisi.io/saml/consume/<DOMAIN>`

    注

    `DOMAIN` は、Kisi によって組織に割り当てられる小文字の英数字識別子であり、組織の DNS ドメイン名と同じものでは**ありません**。\*
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://web.kisi.io/organizations/sign_in?domain=<DOMAIN>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、[Kisi Physical Security クライアント サポート チーム](mailto:support@getkisi.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Kisi Physical Security アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Kisi Physical Security アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの名字 |
    | Email | user.userprincipalname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kisi Physical Security の SSO の構成

**Kisi Physical Security** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Kisi Physical Security サポート チーム](mailto:support@getkisi.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Kisi Physical Security のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Kisi Physical Security 内に作成します。 Kisi Physical Security では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Kisi Physical Security にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Kisi 物理セキュリティ サインオン URL にリダイレクトされます。
- Kisi Physical Security のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Kisi Physical Security に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Kisi Physical Security] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Kisi Physical Security に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kiteworks-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Kiteworks を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kiteworks-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kiteworks の間にシングル サインオンを構成する方法について説明します。

この記事では、Kiteworks と Microsoft Entra ID を統合する方法について説明します。 Kiteworks と Microsoft Entra ID を統合すると、次のことができます:

- Kiteworks にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Kiteworks に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kiteworks でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Kiteworks では、 **SP** Initiated SSO がサポートされます。
- Kiteworks では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Kiteworks の追加

Microsoft Entra ID への Kiteworks の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Kiteworks を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Kiteworks**」と入力します。
4. 結果パネルから **Kiteworks** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kiteworks 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Kiteworks に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Kiteworks の関連ユーザーとの間にリンク関係を確立する必要があります。

Kiteworks に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kiteworks の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Kiteworks のテストユーザーを作成する** - Microsoft Entra の B.Simon ユーザー表現とリンクされた Kiteworks の B.Simon に対応するユーザーを設定します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Kiteworks** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<kiteworksURL>.kiteworks.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<kiteworksURL>/sp/module.php/saml/sp/saml2-acs.php/sp-sso`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、Kiteworks クライアント サポート チーム](https://accellion.com/support) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Kiteworks のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kiteworks SSO の構成

1. Kiteworks 企業サイトに管理者としてサインオンします。
2. 上部のツール バーで、[ **設定]** を選択します。

    [Image: 選択したツール バーの [設定] アイコンを示すスクリーンショット。]
3. [ **認証と承認** ] セクションで、[ **SSO セットアップ**] を選択します。

    [Image: [Authentication and Authorization](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証と承認) セクションで選択された [S S O Setup](S S O セットアップ) を示すスクリーンショット。]
4. [SSO Setup] ページで、次の手順に従います。

    [Image: シングル サインオンの構成]

    ある。 **[SSO による認証]** を選択します。

    b。 **AuthnRequestの開始**を選択します。

    c. **[IDP エンティティ ID**] ボックスに、**Microsoft Entra Identifier** の値を貼り付けます。

    d. **Single Sign-On Service URL** ボックスに**ログイン URL**の値を貼り付けます。

    え **[Single Logout Service URL]\(単一ログアウト サービス URL**\) ボックスに、**ログアウト URL** の値を貼り付けます。

    f. ダウンロードした証明書をメモ帳で開き、内容をコピーして、[ **RSA 公開キー証明書** ] ボックスに貼り付けます。

    ジー **[保存] を選択します**。

#### Kiteworks テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Kiteworks に作成します。 Kiteworks では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Kiteworks にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Kiteworks のサインオン URL にリダイレクトされます。
- Kiteworks のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Kiteworks] タイルを選択すると、このオプションは Kiteworks のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/klaxoon-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Klaxoon を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/klaxoon-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-12
- Summary: ユーザー アカウントを Klaxoon に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Klaxoon ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Klaxoon](https://www.Klaxoon.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Klaxoon でユーザーを作成する。
- アクセスが不要になった場合は、Klaxoon のユーザーを無効にします。
- Microsoft Entra ID と Klaxoon の間でユーザー属性の同期を維持する。
- Microsoft Entra グループに基づいて Klaxoon のユーザーにライセンスを提供する。
- Klaxoon に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 既存の [Klaxoon コントラクト](https://klaxoon.com/solutions-enterprise-excellence)。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Klaxoon の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように Klaxoon を構成する

- 一意の[テナント URL](https://klaxoon.com/) と**シークレット トークン**を受け取るために **Klaxoon** に問い合わせてください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Klaxoon を追加する

Microsoft Entra アプリケーション ギャラリーから Klaxoon を追加して、Klaxoon へのプロビジョニングの管理を開始します。 以前に、SSO 用に Klaxoon を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Klaxoon への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて Klaxoon 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Klaxoon の自動ユーザー プロビジョニングを構成するには、以下の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **[Klaxoon**] を選択します。

    [Image: アプリケーションの一覧の Klaxoon のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Klaxoon テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Klaxoon に接続できることを確認します。 接続に失敗した場合は、Klaxoon アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Klaxoon に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Klaxoon のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Klaxoon API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Klaxoon で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "仕事"].value | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | 活動中 | ブール値 |  | ✓ |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から Klaxoon に同期されるグループ **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Klaxoon のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Klaxoon で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | members | リファレンス |  |  |
    | urn:ietf:params:scim:schemas:extension:klaxoon:2.0:Group:license | 糸 |  |  |
14. **urn:ietf:params:scim:schemas:extension:klaxoon:2.0:Group:license** 属性を定義して、グループにリンクされているユーザーに Klaxoon PRO ライセンスを提供します。

    | 価値 | Klaxoon PRO のライセンスを持つグループ |
    | --- | --- |
    | ほんとう | ✓ |
    | 偽り | いいえ |
    | 指定されていない (既定値) | ✓ |
15. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
16. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
17. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/klaxoon-saml-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Klaxoon SAML を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/klaxoon-saml-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-12
- Summary: ユーザー アカウントを Klaxoon SAML に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Klaxoon SAML と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Klaxoon SAML](https://www.klaxoon.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Klaxoon でユーザーを作成する。
- アクセスが不要になった場合は、Klaxoon のユーザーを無効にします。
- Microsoft Entra ID と Klaxoon の間でユーザー属性の同期を維持する。
- Microsoft Entra グループに基づいて Klaxoon のユーザーにライセンスを提供する。
- SAML を使用して Klaxoon に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/klaxoon-saml-tutorial)する (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 既存の [Klaxoon コントラクト](https://klaxoon.com/solutions-enterprise-excellence)。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Klaxoon SAML 間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように Klaxoon SAML を構成する

- 一意の[テナント URL](https://klaxoon.com/) と**シークレット トークン**を受け取るために **Klaxoon** に問い合わせてください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Klaxoon SAML を追加する

Microsoft Entra アプリケーション ギャラリーから Klaxoon SAML を追加して、Klaxoon へのプロビジョニングの管理を開始します。 以前に、SSO 用に Klaxoon SAML を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Klaxoon への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて Klaxoon SAML 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Klaxoon SAML の自動ユーザー プロビジョニングを構成するには、以下の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Klaxoon SAML]** を選択します。

    [Image: アプリケーションの一覧の Klaxoon SAML のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Klaxoon テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Klaxoon に接続できることを確認します。 接続に失敗した場合は、Klaxoon アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Klaxoon に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Klaxoon のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Klaxoon API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Klaxoon で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "仕事"].value | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | 活動中 | ブール値 |  | ✓ |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から Klaxoon に同期されるグループ **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Klaxoon のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Klaxoon で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | members | リファレンス |  |  |
    | urn:ietf:params:scim:schemas:extension:klaxoon:2.0:Group:license | 糸 |  |  |
14. **urn:ietf:params:scim:schemas:extension:klaxoon:2.0:Group:license** 属性を定義して、グループにリンクされているユーザーに Klaxoon PRO ライセンスを提供します。

    | 価値 | Klaxoon PRO のライセンスを持つグループ |
    | --- | --- |
    | ほんとう | ✓ |
    | 偽り | いいえ |
    | 指定されていない (既定値) | ✓ |
15. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
16. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
17. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/klaxoon-saml-tutorial"} -->
## Microsoft Entra ID とシングルサインオンを構成するために、Klaxoon SAML を設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/klaxoon-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Klaxoon SAML 間のシングル サインオンを構成する方法について説明します。

この記事では、Klaxoon SAML と Microsoft Entra ID を統合する方法について説明します。 Klaxoon SAML を Microsoft Entra ID と統合すると、次のことが可能になります。

- Klaxoon SAML にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Klaxoon SAML に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Klaxoon SAML サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Klaxoon SAML では、 **SP** によって開始される SSO がサポートされます。
- Klaxoon SAML では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/klaxoon-saml-provisioning-tutorial)。

### ギャラリーからの Klaxoon SAML の追加

Microsoft Entra ID への Klaxoon SAML の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Klaxoon SAML を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Klaxoon SAML**」と入力します。
4. 結果パネルから **[Klaxoon SAML]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Klaxoon SAML 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Klaxoon SAML に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するには、Microsoft Entra ユーザーとそれに対応する Klaxoon SAML. ユーザーをリンクする必要があります。

Klaxoon SAML に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Klaxoon SAML の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Klaxoon SAML のテスト ユーザーの作成** - Klaxoon SAML で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Klaxoon SAML**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.klaxoon.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://enterprise-access.klaxoon.com/aaa/login/sso/<SUBDOMAIN>/callback`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と応答 URL でこれらの値を更新してください。 これらの値を取得するには、[Klaxoon SAML クライアント サポート チーム](mailto:help@klaxoon.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Klaxoon SAML のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Klaxoon SAML の SSO の構成

**Klaxoon SAML** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Klaxoon SAML サポート チーム](mailto:help@klaxoon.com)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Klaxoon SAML のテスト ユーザーの作成

このセクションでは、Klaxoon SAML で Britta Simon というユーザーを作成します。 [Klaxoon SAML サポート チーム](mailto:help@klaxoon.com)と協力して、Klaxoon SAML プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Klaxoon SAML サインオン URL にリダイレクトされます。
- Klaxoon SAML のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Klaxoon SAML] タイルを選択すると、このオプションは Klaxoon SAML サインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/klue-tutorial"} -->
## Microsoft Entra ID で Klue for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/klue-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Klue 間のシングル サインオンを構成する方法について説明します。

この記事では、Klue と Microsoft Entra ID を統合する方法について説明します。 Klue を Microsoft Entra ID と統合すると、次のことが可能になります。

- Klue にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Klue に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Klue でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Klue は、**SP から開始される SSO** と**IDP から開始される SSO** をサポートします。
- Klue では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Klue の追加

Microsoft Entra ID への Klue の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Klue を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Klue**」と入力します。
4. 結果パネルから **Klue** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Klue 用に Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Klue に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Klue の関連ユーザー間にリンク関係を確立する必要があります。

Klue に対して Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Klue SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Klue のテスト ユーザーの作成** - Klue で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Klue**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:klue:<Customer ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.klue.com/account/auth/saml/<Customer UUID>/callback`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.klue.com/account/auth/saml/<Customer UUID>/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Klue クライアント サポート チーム](mailto:support@klue.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Klue アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Klue アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | first\_name | User.givenname |
    | last\_name | ユーザーの名字 |
    | メール | user.userprincipalname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Klue のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Klue の SSO の構成

**Klue** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Klue サポート チーム](mailto:support@klue.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Klue テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Klue に作成します。 Klue では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Klue にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Klue サインオン URL にリダイレクトされます。
- Klue のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Klue に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Klue] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Klue に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kno2fy-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Kno2fy を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kno2fy-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra ID から Kno2fy に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Kno2fy と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [Kno2fy](https://www.kno2.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Kno2fy でユーザーを作成する。
- アクセスが不要になったら、Kno2fy のユーザーを削除します。
- Microsoft Entra ID と Kno2fy の間でユーザー属性の同期を維持する。
- Kno2fy においてグループとグループメンバーシップをプロビジョニングする
- Kno2fy に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kno2fy-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- プロビジョニング サービスが有効になっている 1 つ以上の Kno2 組織。
- ユーザーを Microsoft Entra ID 経由で管理する必要がある組織を管理するためのアクセス許可を持つ Kno2 管理者アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Kno2fy の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Kno2fy を構成する

1. Microsoft Entra ID でのプロビジョニングは、ID プロバイダーとして Microsoft Entra ID を使うシングル サインオンで使用することを目的としています。 Microsoft Entra ID で Kno2fy アプリケーションのシングル サインオンを有効にし、組織の Kno2 設定に適切な発行者の値を追加して、Microsoft Entra ID プロバイダーを追加します。
2. サービスで使用するプロビジョニング トークンと URL の取得については、Kno2 チーム メンバーがサポートします。 手順 5 で使用するために、これらの値を保存します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Kno2fy を追加する

Microsoft Entra アプリケーション ギャラリーから Kno2fy を追加して、Kno2fy へのプロビジョニングの管理を開始します。 SSO のために Kno2fy を既に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Kno2fy への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Kno2fy 用に自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Kno2fy** を選択します。

    [Image: アプリケーションの一覧の [Kno2fy] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Kno2fy テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Kno2fy に接続できることを確認します。 接続に失敗した場合は、Kno2fy アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から Kno2fy に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Kno2fy のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Kno2fy API でサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Kno2fy で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
13. 左側のパネルで **[属性マッピング** ] を選択し、[グループ] を選択 **します**。
14. [属性マッピング] セクションで、Microsoft Entra ID から Kno2fy に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Kno2fy のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Kno2fy で必須 |
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
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kno2fy-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Kno2fy を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kno2fy-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kno2fy の間にシングル サインオンを構成する方法について説明します。

この記事では、Kno2fy を Microsoft Entra ID を統合する方法について説明します。 Kno2fy は、医療エコシステム全体で患者情報を送信、受信、検索する医療組織を支援します。 Kno2fy を Microsoft Entra ID を統合すると、次のことができます。

- Kno2fy にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Kno2fy に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

テスト環境で Kno2fy 用の Microsoft Entra シングル サインオンを構成してテストします。 Kno2fy では、 **SP** によって開始されるシングル サインオンのみがサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### 前提条件

Microsoft Entra ID を Kno2fy と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Kno2fy でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Kno2fy アプリケーションを追加する必要があります。 アプリケーションに割り当て、シングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Kno2fy を追加する

Microsoft Entra アプリケーション ギャラリーから Kno2fy を追加して、Kno2fy に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーを作成して割り当てる

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができます。 このウィザードには、シングル サインオン構成ウィンドウへのリンクも表示されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Kno2fy で Microsoft Entra の情報にアクセスする

1. ネットワーク管理者として https://kno2fy.com にログインします。
2. 画面の右上隅にある設定の歯車を選択します。
3. [ネットワーク] で、[ **ID プロバイダー] を選択します**。
4. ドロップダウンで、[ **Microsoft Entra ID] を選択します**。
5. 以下の「 Microsoft Entra SSO の構成」 セクションでセットアップを続行します。

Kno2fy には、**基本的な SAML 構成**のセットアップに必要な情報が表示されます

[Image: Microsoft Entra Saml セットアップ情報のスクリーンショット。]

### Microsoft Entra SSO の構成

Microsoft Entra シングル サインオンを有効にするには、次の手順を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**の&gt;の**Kno2fy**の&gt;に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]

    **基本的な SAML 構成**をセットアップするための情報にアクセスするには、上記の Microsoft Entra Information セクションを確認してください。
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次の値を貼り付けます。 `Identifier (Entity ID)`

    b。 [ **応答 URL** ] ボックスに、次の URL を貼り付けます。 `Reply URL (Assertion Consumer Service URL)`

    c. [ **サインオン URL** ] ボックスで、次の手順を実行します。

    注意

    この値は、Kno2fy ID プロバイダーが保存されると表示されます。 ここでは空白のままにします。
6. [ **基本的な SAML 構成] セクションを** 保存します。
7. 下にスクロールし、生成された **アプリのフェデレーション メタデータ URL を** コピーします。
8. [Kno2fy SSO の構成] セクションでセットアップを続行する

### Kno2fy SSO を構成する

1. Microsoft Entra ID SSO セットアップの **アプリ フェデレーション メタデータ URL を** 、Kno2fy 内の **[アプリのフェデレーション メタデータ URL** ] フィールドに貼り付けます。
2. **[認証設定]** では、SSO を使用しないログインは既定でオフになっています。

    ログイン テストを実行する時間を許可するために、 **管理者以外が SSO をバイパスし、Kno2 ユーザー名とパスワードの設定を使用してログイン** することを一時的に有効にすることができます。 SSO が完全に有効になり、セットアップされた後も、この設定はオフのままにすることをお勧めします。
3. **[保存**] ボタンを選択してセットアップを完了します。

完了すると、 **SSO Integration Activated バナーが** 画面の上部に表示されます。 URL をコピーして、URL を**[サインオン URL]** のセクションに貼り付けます。これを **基本的な SAML 構成**にあるMicrosoft Entra SSO を構成 します。

#### Kno2fy テスト ユーザーを作成する

このセクションでは、Kno2fy で Britta Simon というユーザーを作成します。 [Kno2fy サポート チーム](mailto:support@kno2.com)と協力して、Kno2fy プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Kno2fy サインオン URL にリダイレクトされます。
- Kno2fy のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Kno2fy] タイルを選択すると、このオプションは Kno2fy のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/knowbe4-security-awareness-training-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に KnowBe4 Security Awareness Training を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/knowbe4-security-awareness-training-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra ID から KnowBe4 Security Awareness Training にユーザー アカウントを自動的にプロビジョニング/プロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために KnowBe4 Security Awareness Training と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [KnowBe4 Security Awareness Training](https://www.knowbe4.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- KnowBe4 Security Awareness Training のユーザーを作成する。
- アクセスが不要になった場合は、KnowBe4 Security Awareness Training のユーザーを削除します。
- Microsoft Entra ID と KnowBe4 Security Awareness Training の間のユーザー属性の同期を維持する。
- KnowBe4 Security Awareness Training のグループとグループ メンバーシップをプロビジョニングする。
- KnowBe4 Security Awareness Training に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/knowbe4-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者のアクセス許可を持つ KnowBe4 Security Awareness Training のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と KnowBe4 Security Awareness Training の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID でのプロビジョニングをサポートするように KnowBe4 Security Awareness Training を構成する

次の手順に従って、コンソールで SCIM 設定を構成します。

注

ADI から SCIM に切り替える場合は、エイリアスのメール アドレスを使用している場合、SCIM との統合ではその接続がサポートされないため、 **テスト モード** を無効にして同期を実行すると、この情報は削除されることに注意してください。

1. KnowBe4 コンソールで、右上隅にあるメール アドレスを選択し **、[アカウント設定]** を選択します。
2. 設定の [ **ユーザー管理 &gt; ユーザー プロビジョニング** ] セクションに移動します。
3. [ **ユーザー プロビジョニングの有効化 (ユーザー同期)]** を選択して、プロビジョニング設定をさらに表示します。

    [Image: ユーザー プロビジョニング (ユーザー同期) のスクリーンショット。]
4. デフォルトでは、トグルは **ADI** に設定されています。 **SCIM** トグルを選択して設定を開始します。
5. [+ SCIM 設定] を選択して **SCIM 設定**を展開します。

    [Image: SCIM テナント URL 構成設定のスクリーンショット。]
6. [ **SCIM トークンの生成]** を選択します。 これにより、トークン ID を含む新しいウィンドウが開きます。 この ID をコピーし、後で簡単にアクセスできる場所に保存します。 このウィンドウを閉じるとトークンを再度表示できないため、このトークンを保存することが重要です。 情報を保存したら、[ **OK] を** 選択してウィンドウを閉じます。

    注

    SCIM トークンが生成されると、このボタンが [ **SCIM トークンの再生成** ] ボタンに変わります。 詳細については、この記事の **「トラブルシューティングのヒント** 」セクションを参照してください。

    注

    KnowBe4 との接続を確立するには、ID プロバイダーにトークン (手順 5) とテナント ID (手順 6) を提供する必要があります。 ID プロバイダーとの接続を設定する準備ができたらすぐに使用できるように、この情報を保存してください。
7. テナント URL をコピーし、後で簡単にアクセスできる場所に保存します。
8. テスト モード オプションが選択されていることを確認します。

    [Image: SCIM テスト モード構成オプションのスクリーンショット。]

    注

    KnowBe4 と ID プロバイダーの間の接続を構成し、正常に同期を実行するまで、 **テスト モード** を有効にしておくことをお勧めします。テスト モードは、SCIM が有効な場合に何が起こるかのレポートを生成するために使用されます。 つまり、コンソールに変更は加えられないため、コンソールの変更を気にすることなくセットアップを構成できます。 準備ができたら、**アカウント設定**から**テスト モード**を無効にして同期を有効にすることができます。 ADIからSCIMに切り替える場合は、**アカウント設定**を保存した後に**テストモード**が自動的に有効になります。
9. **[アカウント設定]** ページの下部まで下にスクロールし、[**変更の保存]** を選択します。 KnowBe4 アカウントで SCIM を有効にしたら、ID プロバイダーとの接続を完了する準備ができました。 使用している ID プロバイダーの SCIM を構成する手順については、以下のいずれかの記事を参照してください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから KnowBe4 Security Awareness Training を追加する

KnowBe4 Security Awareness Training へのプロビジョニングの管理を開始するには、Microsoft Entra アプリケーション ギャラリーから KnowBe4 Security Awareness Training を追加します。 SSO のために nowBe4 Security Awareness Training を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: KnowBe4 Security Awareness Training への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて KnowBe4 Security Awareness Training のユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で KnowBe4 Security Awareness Training の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**にアクセスする

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で、[ **KnowBe4 Security Awareness Training**] を選択します。

    [Image: アプリケーションの一覧の KnowBe4 Security Awareness Training リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: アプリケーション設定の [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、KnowBe4 Security Awareness Training テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が KnowBe4 Security Awareness Training に接続できることを確認します。 接続に失敗した場合は、KnowBe4 Security Awareness Training アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から KnowBe4 Security Awareness Training に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作の KnowBe4 Security Awareness Training のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、KnowBe4 Security Awareness Training API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

注

属性リストの編集が有効になり、ユーザーが必要に応じて新しい KnowBe4 ターゲット属性を作成できるように、一連のターゲット属性を変更できるようになりました。

| 特性 | タイプ | フィルター処理でサポートされます | KnowBe4 Security Awareness Training で必要 |
| --- | --- | --- | --- |
| ユーザー名 | 糸 | ✓ | ✓ |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager.value | リファレンス |  |  |
| 活動中 | ブール値 |  |  |
| タイトル | 糸 |  |  |
| name.givenName | 糸 |  |  |
| name.familyName | 糸 |  |  |
| externalId | 糸 |  |  |
| displayName | 糸 |  |  |
| addresses[type eq "work"].フォーマット済み | 糸 |  |  |
| phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
| 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
| ユーザータイプ | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:customDate1 | 日付と時間 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:customDate2 | 日付と時間 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:customField1 | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:customField2 | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:customField3 | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:customField4 | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:outOfOfficeEnd | 日付と時間 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:phishingLanguage | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:trainingLanguage | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:userRole | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:hostname | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:companyName | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:country | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:mailNickName | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:onPremisesSamAccountName | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:onPremisesSecurityIdentifier | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:userPrincipalName | 糸 |  |  |
| urn:ietf:params:scim:schemas:extension:knowbe4:kmsat:2.0:User:lastPasswordChangeDateTime | 日付と時間 |  |  |
|  |  |  |  |

1. 左側のパネルで **[属性マッピング** ] を選択し、[グループ] を選択 **します**。
2. [属性マッピング] セクションで、Microsoft Entra ID から KnowBe4 Security Awareness Training に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作の KnowBe4 Security Awareness Training のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | KnowBe4 Security Awareness Training で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
    | externalId | 糸 |  |  |
3. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
4. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
5. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### 手順 7: トラブルシューティングのヒント

- SCIM が有効になると、アカウント設定の SCIM セクションに、トラブルシューティングのために使用できる 3 つのボタンが表示されます。 これらのオプションの詳細については、以下一覧を参照してください。

    [Image: SCIM のトラブルシューティングのヒントとボタンのスクリーンショット。]

    - **SCIM トークンを再生成**する: このボタンを使用して、新しい SCIM トークンを生成します。 このトークンは 1 度しか表示できないので、ウィンドウを閉じる前にこの情報を保存しておいてください。 ID プロバイダーと KnowBe4 コンソールの間のリンクは、新しい SCIM トークンを指定するまで無効になります。
    - **SCIM トークンの取り消し**: このボタンを使用して、現在の SCIM トークンを無効にします。 現在このトークンを使用している ID プロバイダーは、KnowBe4 コンソールにリンクされなくなります。
    - **[今すぐ同期を強制**する]: このボタンを使用すると、ID プロバイダーからの変更を必要とせずに、いつでも SCIM 同期を手動で強制できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/knowbe4-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に KnowBe4 Security Awareness Training を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/knowbe4-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: n Microsoft Entra ID と KnowBe4 Security Awareness Training の間でシングル サインオンを構成する方法について説明します。

この記事では、KnowBe4 Security Awareness Training と Microsoft Entra ID を統合する方法について説明します。 KnowBe4 Security Awareness Training を Microsoft Entra ID と統合すると、次のことができるようになります。

- KnowBe4 Security Awareness Training にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して、KnowBe4 Security Awareness Training に自動でサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- KnowBe4 Security Awareness Training シングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- KnowBe4 Security Awareness Training では、**SP** によって開始される SSO がサポートされます。
- KnowBe4 Security Awareness Training では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから KnowBe4 を追加する

Microsoft Entra ID への KnowBe4 の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に KnowBe4 を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**KnowBe4**」と入力します。
4. 結果のパネルから **[KnowBe4]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### KnowBe4 Security Awareness Training 用に Microsoft Entra SSO を構成してテストする

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、KnowBe4 に対する Microsoft Entra シングル サインオンを構成してテストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと KnowBe4 の関連ユーザーの間にリンク関係を確立する必要があります。

KnowBe4 に対して Microsoft Entra シングル サインオンを構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成** する - Britta Simon で Microsoft Entra SSO をテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てて** 、Britta Simon が Microsoft Entra SSO を使用できるようにします。
2. **KnowBe4 Security Awareness Training の SSO の構成**- アプリケーション側で SSO 設定を構成します。
    1. **KnowBe4 Security Awareness Training のテスト ユーザーの作成** - Microsoft Entra におけるユーザー表現とリンクした形で、Britta Simon に対応する KnowBe4 Security Awareness Training のユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**KnowBe4**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.KnowBe4.com/auth/saml/<instancename>`

    注

    サインオン URL の値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには、[KnowBe4 Security Awareness Training Client サポート チーム](mailto:support@KnowBe4.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[KnowBe4 Security Awareness Training のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### KnowBe4 Security Awareness Training の SSO の構成

**KnowBe4 Security Awareness Training** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** とアプリケーション構成からコピーした適切な URL を、[KnowBe4 Security Awareness Training サポート チーム](mailto:support@KnowBe4.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### KnowBe4 Security Awareness Training のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを KnowBe4 内に作成します。 KnowBe4 では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 KnowBe4 にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる KnowBe4 Security Awareness Training のサインオン URL にリダイレクトされます。
- KnowBe4 Security Awareness Training のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [KnowBe4 Security Awareness Training] タイルを選択すると、このオプションは KnowBe4 Security Awareness Training のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/knowledge-anywhere-lms-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Knowledge Anywhere LMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/knowledge-anywhere-lms-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Knowledge Anywhere LMS 間にシングル サインオンを構成する方法について説明します。

この記事では、Knowledge Anywhere LMS と Microsoft Entra ID を統合する方法について説明します。 Knowledge Anywhere LMS を Microsoft Entra ID を統合すると、次のことができます:

- Knowledge Anywhere LMS にアクセスできるユーザー Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Knowledge Anywhere LMS に自動的にサインインする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Knowledge Anywhere LMS サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Knowledge Anywhere LMS では、 **SP** によって開始される SSO がサポートされます。
- Knowledge Anywhere LMS では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Knowledge Anywhere LMS の追加

Microsoft Entra ID への Knowledge Anywhere LMS の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Knowledge Anywhere LMS を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Knowledge Anywhere LMS**」と入力します。
4. 結果パネルから **Knowledge Anywhere LMS** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Knowledge Anywhere LMS の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Knowledge Anywhere LMS に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するために、Microsoft Entra ユーザーと Knowledge Anywhere LMS の関連ユーザーの間で、リンク関係を確立する必要があります。

Knowledge Anywhere LMS に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Knowledge Anywhere LMS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Knowledge Anywhere LMS テストユーザーの作成** - Knowledge Anywhere LMS で B.Simon に対応するユーザーを作成し、これを Microsoft Entra のユーザー表現にリンクさせることを目的とします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Knowledge Anywhere LMS** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    1. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CLIENT_NAME>.knowledgeanywhere.com/`
    2. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CLIENT_NAME>.knowledgeanywhere.com/SSO/SAML/Response.aspx?<IDP_NAME>`

    注

    これらの値は実際の値ではありません。 これらの値は、記事の後半で説明する実際の識別子と応答 URL で更新します。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CLIENTNAME>.knowledgeanywhere.com/`

    注

    サインオン URL の値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには [、Knowledge Anywhere LMS クライアント サポート チーム](https://knowany.zendesk.com/hc/en-us/articles/360000469034-SAML-2-0-Single-Sign-On-SSO-Set-Up-Guide) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Knowledge Anywhere LMS のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Knowledge Anywhere LMS の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Knowledge Anywhere LMS 企業サイトに管理者としてサインインします
2. [ **サイト** ] タブを選択します。

    [Image: [サイト] タブを示すスクリーンショット。]
3. [ **SAML 設定] タブを** 選択します。

    [Image: [SAML 設定] が選択されている [Knowledge anywhere](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ナレッジの任意の場所) ページを示すスクリーンショット。]
4. [ **新規追加]** を選択します。

    [Image: [サービス プロバイダーの設定] の [新しい追加] ボタンを示すスクリーンショット。]
5. [ **SAML 設定の追加/更新]** ページで、次の手順を実行します。

    [Image: [SAML 設定の追加/更新] ページを示すスクリーンショット。ここで説明する変更を行うことができます。]

    a 所属する組織に応じて [IDP Name](IDP 名) に入力します (例: `Azure`)。

    b。 **[IDP エンティティ ID**] ボックスに、Azure portal からコピーした **Microsoft Entra Identifier** 値を貼り付けます。

    c. **[IDP URL**] ボックスに、**ログイン URL** の値を貼り付けます。

    d. ダウンロードした証明書ファイルをメモ帳に開き、証明書の内容をコピーして[ **証明書** ]テキストボックスに貼り付けます。

    え [ **ログアウト URL** ] ボックスに、 **ログアウト URL** の値を貼り付けます。

    f. ドメインのドロップダウンから **メイン サイト** を選択 **します**。

    ジー **SP エンティティ ID** の値をコピーし、[**基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスに貼り付けます。

    h. **SP Response(ACS) URL** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスに貼り付けます。

    一. **[保存] を選択します**。

#### Knowledge Anywhere LMS のテスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Knowledge Anywhere LMS に作成します。 Knowledge Anywhere LMS では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Knowledge Anywhere LMS にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Knowledge Anywhere LMS のサインオン URL にリダイレクトされます。
- Knowledge Anywhere LMS のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Knowledge Anywhere LMS] タイルを選択すると、このオプションは Knowledge Anywhere LMS のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/knowledge-work-tutorial"} -->
## Microsoft Entra ID を使用して Knowledge Work for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/knowledge-work-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-13
- Summary: Microsoft Entra ID と Knowledge Work 間にシングル サインオンを構成する方法について説明します。

この記事では、Knowledge Work を Microsoft Entra ID と統合する方法について説明します。 "ナレッジワーク" は、1 つのツールでセールス イネーブルメントのさまざまな要素を実現し、企業の販売効率を向上させるクラウド サービスです。 具体的には、販売資料や販売ノウハウを共有し、販売のための学習プログラムを提供することが可能です。 Knowledge Work を Microsoft Entra ID を統合すると、次のことができます。

- Knowledge Work にアクセスできるユーザー Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Knowledge Work に自動的にサインインする。
- 1 つの場所でアカウントを管理します。

テスト環境で Knowledge Work 向けのMicrosoft Entra のシングル サインオンを構成してテストします。 Knowledge Work では、 **SP** によって開始されるシングル サインオンと **Just-In-Time** ユーザー プロビジョニングのみがサポートされます。

### 前提条件

Microsoft Entra ID を Knowledge Work と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ナレッジワークでのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Knowledge Work アプリケーションを追加する必要があります。 アプリケーションに割り当て、シングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Knowledge Work を追加する

Microsoft Entra アプリケーション ギャラリーから Knowledge Work を追加して、Knowledge Work でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーを作成して割り当てる

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができます。 このウィザードには、シングル サインオン構成ウィンドウへのリンクも表示されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra シングル サインオンを有効にするには、次の手順を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**Knowledge Work**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.kwork.cloud/saml`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://auth.kwork.cloud/_auth/saml/callback`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.kwork.cloud/redirect`

    注意

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 [Knowledge Work クライアント サポート チーム](mailto:support@knowledgework.com) に問い合わせてください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Knowledge Work のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### ナレッジワーク SSO を構成する

**Knowledge Work** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Knowledge Work サポート チーム](mailto:support@knowledgework.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### ナレッジワーク テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーがナレッジワークで作成されます。 ナレッジワークでは、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションでは、ユーザー側で必要な操作はありません。 ナレッジワークにユーザーがまだ存在していない場合、一般的には認証後に新しいものが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Knowledge Work のサインオン URL にリダイレクトされます。
- ナレッジワークのサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Knowledge Work] タイルを選択すると、このオプションは Knowledge Work のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/knowledgeowl-tutorial"} -->
## Microsoft Entra ID で KnowledgeOwl for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/knowledgeowl-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-19
- Summary: Microsoft Entra ID と KnowledgeOwl の間のシングル サインオンを構成する方法について説明します。

この記事では、KnowledgeOwl と Microsoft Entra ID を統合する方法について説明します。 KnowledgeOwl を Microsoft Entra ID と統合すると、次のことが可能になります。

- KnowledgeOwl にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで KnowledgeOwl に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- KnowledgeOwl でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- KnowledgeOwl では、**SP および IDP による SSO**をサポートしています。
- KnowledgeOwl では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの KnowledgeOwl の追加

Microsoft Entra ID への KnowledgeOwl の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に KnowledgeOwl を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「KnowledgeOwl**」と入力します。
4. 結果パネルから **KnowledgeOwl** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### KnowledgeOwl に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、KnowledgeOwl に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと KnowledgeOwl の関連ユーザー間にリンク関係を確立する必要があります。

KnowledgeOwl に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **KnowledgeOwl SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **KnowledgeOwl のテストユーザーを作成** - Microsoft Entra のユーザー表現としてリンクされる B.Simon に対応したユーザーを KnowledgeOwl で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[KnowledgeOwl]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://app.knowledgeowl.com/sp
    https://app.knowledgeowl.com/sp/id/<unique ID>
    ```

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://subdomain.knowledgeowl.com/help/saml-login
    https://subdomain.knowledgeowl.com/docs/saml-login
    https://subdomain.knowledgeowl.com/home/saml-login
    https://privatedomain.com/help/saml-login
    https://privatedomain.com/docs/saml-login
    https://privatedomain.com/home/saml-login
    ```
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://subdomain.knowledgeowl.com/help/saml-login
    https://subdomain.knowledgeowl.com/docs/saml-login
    https://subdomain.knowledgeowl.com/home/saml-login
    https://privatedomain.com/help/saml-login
    https://privatedomain.com/docs/saml-login
    https://privatedomain.com/home/saml-login
    ```

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、Sign-On URL から更新する必要があります。これについては、後で説明します。
7. KnowledgeOwl アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、KnowledgeOwl アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 | Namespace |
    | --- | --- | --- |
    | ssoid | ユーザーのメールアドレス | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **KnowledgeOwl のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### KnowledgeOwl の SSO の構成

1. 別の Web ブラウザー ウィンドウで、KnowledgeOwl 企業サイトに管理者としてサインインします。
2. [ **セキュリティとアクセス** ] を選択し、[ **シングル サインオン**] を選択します。
3. [ **SAML 設定]** タブで、次の手順を実行します。

    [Image: ここに記載されている SAML SSO リーダー ログインを有効にできる SAML 設定を示すスクリーンショット。]

    1. [ **ENABLE SAML SSO reader logins]\(SAML SSO リーダー ログインを有効にする\) を選択します**。

    [Image: ここに記載されている値をコピーできる [Service provider metadata](サービス プロバイダー メタデータ) セクションを示すスクリーンショット。]

    1. [**サービス プロバイダー メタデータ**] セクションで、**SP エンティティ ID の値を**コピーし、Azure portal の **[基本的な SAML 構成**] セクションの**識別子 (エンティティ ID)** に貼り付けます。
    2. **[サービス プロバイダー メタデータ**] セクションで、**SP ログイン URL** の値をコピーし、Azure portal の [**基本的な SAML 構成]** セクションの **[サインオン URL] ボックスと [応答 URL**] ボックスに貼り付けます。

    [Image: ここで説明する手順を実行できる [ID プロバイダー メタデータ] セクションを示すスクリーンショット。]

    1. [ **ID プロバイダーのメタデータ** ] セクションで、以前にコピーした **Microsoft Entra 識別子** の値を **IdP entityID** テキスト ボックスに貼り付けます。
    2. 以前にコピーした **ログイン URL** 値を **IdP ログイン URL** に貼り付けます。
    3. 以前にコピーした **ログアウト URL を** **IdP ログアウト URL** ボックスに貼り付けます。
    4. **IdP** 証明書の下にある [証明書のアップロード] リンクを選択して、Azure portal からダウンロードした**証明書をアップロード**します。
    5. セクションの下部にある **[保存]** を選択します。
4. **[SAML 属性マップ**] タブを開いて属性をマップし、次の手順を実行します。

    [Image: スクリーンショットは、ここで説明する変更を行うことができる「SAML属性のマッピング」を示しています。]

    1. `https://schemas.xmlsoap.org/ws/2005/05/identity/claims/ssoid`] ボックスに「」と入力します。
    2. [`https://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`]テキストボックスに「」と入力します。
    3. `https://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`ボックスに「」と入力します。
    4. `https://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname` ボックスに、「」と入力します。
    5. ページの下部にある **[保存] を** 選択します。

    [Image: [保存] ボタンを示すスクリーンショット。]

#### KnowledgeOwl のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを KnowledgeOwl に作成します。 KnowledgeOwl では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 KnowledgeOwl にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [KnowledgeOwl サポート チーム](mailto:support@knowledgeowl.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる KnowledgeOwl のサインオン URL にリダイレクトされます。
- KnowledgeOwl のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated

- Azure portal で [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した KnowledgeOwl アプリケーションに自動的にサインインします。

また、Microsoft マイ アプリ ポータルを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリ ポータルで [KnowledgeOwl] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した KnowledgeOwl アプリケーションに自動的にサインインされます。 マイ アプリ ポータルの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/kofax-totalagility-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Kofax TotalAgility を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kofax-totalagility-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kofax TotalAgility の間でシングル サインオンを構成する方法について説明します。

この記事では、Kofax TotalAgility と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Kofax TotalAgility を統合すると、次のことができます。

- Microsoft Entra ID で Kofax TotalAgility へのアクセス制御を行います。
- ユーザーが自分の Microsoft Entra アカウントを使用して Kofax TotalAgility に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Kofax TotalAgility でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Kofax TotalAgility では、**SP イニシエーター SSO** と **IDP イニシエーター SSO** の両方がサポートされます。
- Kofax TotalAgility では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Kofax TotalAgility を追加する

Microsoft Entra ID への Kofax TotalAgility の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Kofax TotalAgility を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Kofax TotalAgility**」と入力します。
4. 結果パネルから **Kofax TotalAgility** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Kofax TotalAgility の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Kofax TotalAgility に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Kofax TotalAgility の関連ユーザーとの間にリンク関係を確立する必要があります。

Kofax TotalAgility に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kofax TotalAgility SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Kofax TotalAgility テストユーザーの作成** - Kofax TotalAgility において、Microsoft Entra ID にリンクされた B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Kofax TotalAgility**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://cloudops.dmoeukta.kofaxcloud.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cloudops.dmoeukta.kofaxcloud.com/FederatedLogin.aspx?Id=<ID>&Protocol=Workspace&Origin=https://cloudops.dmoeukta.kofaxcloud.com/forms/custom/logon.html`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://cloudops.dmoeukta.kofaxcloud.com/forms/custom/logon.html`

    注

    応答 URL は実際のものではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには [、Kofax TotalAgility サポート チーム](mailto:cloud-help@kofax.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Kofax TotalAgility アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. 上記に加えて、Kofax TotalAgility アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 表示名 | ユーザー表示名 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. [ **Kofax TotalAgility のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kofax TotalAgility SSO の構成

**Kofax TotalAgility** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と Microsoft Entra 管理センターからコピーした適切な URL を [Kofax TotalAgility サポート チーム](mailto:cloud-help@kofax.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Kofax TotalAgility テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Kofax TotalAgility に作成します。 Kofax TotalAgility では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Kofax TotalAgility にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Kofax TotalAgility のサインオン URL にリダイレクトします。
- Kofax TotalAgility のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Kofax TotalAgility に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Kofax TotalAgility] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Kofax TotalAgility に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/korn-ferry-360-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Korn Ferry 360 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/korn-ferry-360-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Korn Ferry 360 間にシングル サインオンを構成する方法について説明します。

この記事では、Korn Ferry 360 と Microsoft Entra ID を統合する方法について説明します。 Korn Ferry 360 を Microsoft Entra ID と統合すると、次のことが可能になります。

- Korn Ferry 360 にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Korn Ferry 360 に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Korn Ferry 360 でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Korn Ferry 360 では、 **SP** Initiated SSO がサポートされます。

### ギャラリーから Korn Ferry 360 を追加する

Microsoft Entra ID への Korn Ferry 360 の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Korn Ferry 360 を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリ**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Korn Ferry 360**」と入力します。
4. 結果パネルから **Korn Ferry 360** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Korn Ferry 360 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Korn Ferry 360 に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Korn Ferry 360 の関連ユーザーとの間にリンク関係を確立する必要があります。

Korn Ferry 360 に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Korn Ferry 360 の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Korn Ferry 360 テスト ユーザーの作成 - Korn Ferry** 360 で B.Simon に対応するユーザーを作成し、これを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Korn Ferry 360**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customidentifier>.kornferry.com/`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://surveys.kornferry.com/<customidentifier>`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、Korn Ferry 360 クライアント サポート チーム](mailto:george.gold@kornferry.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Korn Ferry 360 のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Korn Ferry 360 の SSO の構成

**Korn Ferry 360** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Korn Ferry 360 サポート チーム](mailto:george.gold@kornferry.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Korn Ferry 360 テスト ユーザーの作成

このセクションでは、Korn Ferry 360 で B.Simon というユーザーを作成します。 [Korn Ferry 360 サポート チーム](mailto:george.gold@kornferry.com)と協力して、Korn Ferry 360 プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Korn Ferry 360 のサインオン URL にリダイレクトされます。
- Korn Ferry 360 のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Korn Ferry 360] タイルを選択すると、このオプションは Korn Ferry 360 のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->
